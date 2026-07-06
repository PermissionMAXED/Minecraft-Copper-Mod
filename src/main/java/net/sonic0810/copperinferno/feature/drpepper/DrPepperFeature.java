package net.sonic0810.copperinferno.feature.drpepper;

import java.util.List;
import java.util.Optional;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModEffects;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.ModSounds;

/**
 * Dr.Pepper drink, brewing and the DOOM kick. Content is registered by the Dr.Pepper feature
 * worker, which assigns the fields below in {@link #init()}.
 *
 * <p>Brewing chain (brewing stand, one ingredient per step):
 * Water Bottle + Black Dye -> Dark Soda Base + Sugar -> Sweet Soda Syrup + Gunpowder -> Dr.Pepper.
 *
 * <p>Design notes (all verified against the 1.21.9 bytecode):
 * <ul>
 * <li>Black dye is the first ingredient to avoid colliding with vanilla water+sugar->mundane, and
 * gunpowder is applied to OUR item only, so the vanilla water+gunpowder->splash conversion is
 * untouched. Step 1 technically accepts any potion (not just water bottles) + black dye, because
 * vanilla item recipes only match on the input item ({@code Items.POTION}), not its contents.</li>
 * <li>The vanilla {@code BrewingRecipeRegistry.Builder} requires every item-recipe input/output to
 * be a {@code PotionItem} ({@code assertPotion}), hence {@link BrewPotionItem}.</li>
 * <li>{@code BrewingRecipeRegistry.craft} rebuilds item-recipe outputs via
 * {@code PotionContentsComponent.createStack}, which overwrites the stack's POTION_CONTENTS with
 * the input's potion (water). Brewed Dr.Pepper therefore cannot rely on the default
 * POTION_CONTENTS custom effects; the effects are also carried by the CONSUMABLE component
 * ({@code ApplyEffectsConsumeEffect}), which is an item-level default that brewing never touches.
 * The POTION_CONTENTS default (custom color + custom effects) still provides the tooltip/color for
 * creative-menu cans, and applying the same effect list twice is harmless.</li>
 * <li>Brewing-stand bottle slots hardcode potion/splash/lingering/glass bottle
 * ({@code BrewingStandScreenHandler.PotionSlot#matches}), so the custom brews cannot be re-inserted
 * once taken out; the chain is meant to be brewed in place (outputs stay in the bottle slots).</li>
 * </ul>
 */
public final class DrPepperFeature {
	private DrPepperFeature() {
	}

	/** Dark maroon used for the can label + potion tint. */
	public static final int DR_PEPPER_COLOR = 0x5A0E14;
	/** Near-black brown for the intermediate brews. */
	public static final int BREW_COLOR = 0x1A0D08;

	/** Assigned in init() by the Dr.Pepper feature worker. */
	public static Item DR_PEPPER;
	/** Assigned in init() by the Dr.Pepper feature worker. */
	public static RegistryEntry<StatusEffect> DR_PEPPER_KICK;

	public static Item DARK_BREW;
	public static Item SWEET_DARK_BREW;

	public static void init() {
		DR_PEPPER_KICK = ModEffects.register("dr_pepper_kick", new DrPepperKickEffect());

		DARK_BREW = ModItems.register("dark_brew", BrewPotionItem::new, brewSettings());
		SWEET_DARK_BREW = ModItems.register("sweet_dark_brew", BrewPotionItem::new, brewSettings());

		// Speed V for 20s (400 ticks) + the visible "Dr.Pepper kick" marker effect.
		List<StatusEffectInstance> drinkEffects = List.of(
				new StatusEffectInstance(StatusEffects.SPEED, 400, 4),
				new StatusEffectInstance(DR_PEPPER_KICK, 400, 0));

		RegistryEntry<SoundEvent> openSound = Registries.SOUND_EVENT
				.getEntry(ModSounds.DR_PEPPER_OPEN.id())
				.orElseThrow(() -> new IllegalStateException("dr_pepper_open sound not registered"));

		DR_PEPPER = ModItems.register("dr_pepper", BrewPotionItem::new, new Item.Settings()
				.maxCount(1)
				.rarity(Rarity.RARE)
				.useRemainder(Items.GLASS_BOTTLE)
				.component(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(
						Optional.empty(), Optional.of(DR_PEPPER_COLOR), drinkEffects, Optional.empty()))
				.component(DataComponentTypes.CONSUMABLE, ConsumableComponents.drink()
						.sound(openSound)
						.consumeEffect(new ApplyEffectsConsumeEffect(drinkEffects))
						.build()));

		// All three entries are drinks, listed in brewing-progression order.
		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			entries.add(DARK_BREW);
			entries.add(SWEET_DARK_BREW);
			entries.add(DR_PEPPER);
		});

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			// Vanilla method; lets hasRecipe() accept our brews as the bottle-slot input.
			builder.registerPotionType(DARK_BREW);
			builder.registerPotionType(SWEET_DARK_BREW);
			builder.registerPotionType(DR_PEPPER);

			FabricBrewingRecipeRegistryBuilder fabricBuilder = (FabricBrewingRecipeRegistryBuilder) builder;
			fabricBuilder.registerItemRecipe(Items.POTION, Ingredient.ofItems(Items.BLACK_DYE), DARK_BREW);
			fabricBuilder.registerItemRecipe(DARK_BREW, Ingredient.ofItems(Items.SUGAR), SWEET_DARK_BREW);
			fabricBuilder.registerItemRecipe(SWEET_DARK_BREW, Ingredient.ofItems(Items.GUNPOWDER), DR_PEPPER);
			CopperInferno.LOGGER.info("[COPPER INFERNO 1] Registered Dr.Pepper brewing chain (3 potion types, 3 item recipes)");
		});
	}

	/**
	 * Intermediate brews: water potion contents (required by {@code BrewingRecipeRegistry.craft},
	 * which bails out when the input has no potion) with a near-black custom color.
	 */
	private static Item.Settings brewSettings() {
		return new Item.Settings()
				.maxCount(1)
				.component(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(
						Optional.of(Potions.WATER), Optional.of(BREW_COLOR), List.of(), Optional.empty()));
	}
}
