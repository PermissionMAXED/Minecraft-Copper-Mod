package net.sonic0810.kupferbienen.feature.potions;

import java.util.List;
import java.util.Optional;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.sonic0810.kupferbienen.Kupferbienen;
import net.sonic0810.kupferbienen.core.ModCreativeTab;
import net.sonic0810.kupferbienen.core.ModEffects;
import net.sonic0810.kupferbienen.core.ModEntities;
import net.sonic0810.kupferbienen.core.ModItems;
import net.sonic0810.kupferbienen.feature.bees.BeesFeature;

/**
 * The oxidation brewing chain: Kupfersud from bee produce, drinkable oxidation/cleansing
 * potions, and throwable splash vials that weather/scrape copper on impact.
 *
 * <p>Brewing chain (brewing stand, one ingredient per step):
 * Water Bottle + Kupferwabe -&gt; Kupfersud; Kupfersud + Gruenspanpollen -&gt; Trank der
 * Oxidation; Kupfersud + Honeycomb -&gt; Trank der Entoxidation; each Trank + Gunpowder -&gt;
 * the matching Wurfphiole.
 *
 * <p>Design notes (inherited from the proven DrPepperFeature pattern, verified against the
 * 1.21.9 bytecode):
 * <ul>
 * <li>Every item-recipe input/output must be a {@code PotionItem} ({@code assertPotion}),
 * hence {@link BrewPotionItem}/{@link WurfphioleItem}.</li>
 * <li>{@code BrewingRecipeRegistry.craft} rebuilds item-recipe outputs via
 * {@code PotionContentsComponent.createStack}, which overwrites the stack's POTION_CONTENTS
 * with the input's potion (water). Drink effects therefore live in the CONSUMABLE component
 * ({@code ApplyEffectsConsumeEffect}), an item-level default that brewing never touches; the
 * POTION_CONTENTS default (custom color + effects) only provides the creative-menu
 * tooltip/tint.</li>
 * <li>Kupfersud carries water potion contents because {@code craft} bails out when the input
 * stack has no potion.</li>
 * <li>Brewing-stand bottle slots hardcode potion/splash/lingering/glass bottle, so the chain
 * is meant to be brewed in place (outputs stay in the bottle slots).</li>
 * </ul>
 */
public final class PotionsFeature {
	private PotionsFeature() {
	}

	/** Murky copper for the Kupfersud intermediate. */
	public static final int KUPFERSUD_COLOR = 0x8C5A28;
	/** Verdigris green for the oxidation potion/vial. */
	public static final int OXIDATION_COLOR = 0x43A047;
	/** Pale polished gold for the cleansing potion/vial. */
	public static final int ENTOXIDATION_COLOR = 0xFFD9A0;

	private static final int DRINK_EFFECT_TICKS = 600;

	public static RegistryEntry<StatusEffect> PATINA_HAUT;
	public static RegistryEntry<StatusEffect> BLITZBLANK;

	public static Item KUPFERSUD;
	public static Item TRANK_DER_OXIDATION;
	public static Item TRANK_DER_ENTOXIDATION;
	public static Item WURFPHIOLE_OXIDATION;
	public static Item WURFPHIOLE_ENTOXIDATION;

	public static EntityType<GeworfenePhioleEntity> GEWORFENE_PHIOLE;

	public static void init() {
		PATINA_HAUT = ModEffects.register("patina_haut", new PatinaHautEffect());
		BLITZBLANK = ModEffects.register("blitzblank", new BlitzblankEffect());

		// Snowball-style builder values (verified via javap -c on the vanilla SNOWBALL
		// EntityType entry: MISC, dropsNothing, 0.25x0.25, tracking range 4, interval 10).
		GEWORFENE_PHIOLE = ModEntities.register("geworfene_phiole",
				EntityType.Builder.<GeworfenePhioleEntity>create(GeworfenePhioleEntity::new, SpawnGroup.MISC)
						.dropsNothing()
						.dimensions(0.25f, 0.25f)
						.maxTrackingRange(4)
						.trackingTickInterval(10));

		KUPFERSUD = ModItems.register("kupfersud", BrewPotionItem::new, new Item.Settings()
				.maxCount(1)
				.component(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(
						Optional.of(Potions.WATER), Optional.of(KUPFERSUD_COLOR), List.of(), Optional.empty())));

		List<StatusEffectInstance> oxidationEffects = List.of(
				new StatusEffectInstance(PATINA_HAUT, DRINK_EFFECT_TICKS, 0),
				new StatusEffectInstance(StatusEffects.RESISTANCE, DRINK_EFFECT_TICKS, 0));
		TRANK_DER_OXIDATION = ModItems.register("trank_der_oxidation", BrewPotionItem::new,
				trankSettings(OXIDATION_COLOR, oxidationEffects));

		List<StatusEffectInstance> entoxidationEffects = List.of(
				new StatusEffectInstance(BLITZBLANK, DRINK_EFFECT_TICKS, 0),
				new StatusEffectInstance(StatusEffects.HASTE, DRINK_EFFECT_TICKS, 0));
		TRANK_DER_ENTOXIDATION = ModItems.register("trank_der_entoxidation", BrewPotionItem::new,
				trankSettings(ENTOXIDATION_COLOR, entoxidationEffects));

		WURFPHIOLE_OXIDATION = ModItems.register("wurfphiole_oxidation", WurfphioleItem::new,
				wurfphioleSettings(OXIDATION_COLOR));
		WURFPHIOLE_ENTOXIDATION = ModItems.register("wurfphiole_entoxidation", WurfphioleItem::new,
				wurfphioleSettings(ENTOXIDATION_COLOR));

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.ALCHEMIE_KEY).register(entries -> {
			entries.add(KUPFERSUD);
			entries.add(TRANK_DER_OXIDATION);
			entries.add(TRANK_DER_ENTOXIDATION);
			entries.add(WURFPHIOLE_OXIDATION);
			entries.add(WURFPHIOLE_ENTOXIDATION);
		});

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			// Vanilla method; lets hasRecipe() accept our brews as the bottle-slot input.
			builder.registerPotionType(KUPFERSUD);
			builder.registerPotionType(TRANK_DER_OXIDATION);
			builder.registerPotionType(TRANK_DER_ENTOXIDATION);
			builder.registerPotionType(WURFPHIOLE_OXIDATION);
			builder.registerPotionType(WURFPHIOLE_ENTOXIDATION);

			FabricBrewingRecipeRegistryBuilder fabricBuilder = (FabricBrewingRecipeRegistryBuilder) builder;
			fabricBuilder.registerItemRecipe(Items.POTION,
					Ingredient.ofItems(BeesFeature.KUPFERWABE), KUPFERSUD);
			fabricBuilder.registerItemRecipe(KUPFERSUD,
					Ingredient.ofItems(BeesFeature.GRUENSPANPOLLEN), TRANK_DER_OXIDATION);
			fabricBuilder.registerItemRecipe(KUPFERSUD,
					Ingredient.ofItems(Items.HONEYCOMB), TRANK_DER_ENTOXIDATION);
			fabricBuilder.registerItemRecipe(TRANK_DER_OXIDATION,
					Ingredient.ofItems(Items.GUNPOWDER), WURFPHIOLE_OXIDATION);
			fabricBuilder.registerItemRecipe(TRANK_DER_ENTOXIDATION,
					Ingredient.ofItems(Items.GUNPOWDER), WURFPHIOLE_ENTOXIDATION);
			Kupferbienen.LOGGER.info("[KUPFERBIENEN] Registered oxidation brewing chain (5 item recipes)");
		});
	}

	/** Drinkable Trank: DR_PEPPER pattern — POTION_CONTENTS tint + CONSUMABLE drink effects. */
	private static Item.Settings trankSettings(int color, List<StatusEffectInstance> drinkEffects) {
		return new Item.Settings()
				.maxCount(1)
				.useRemainder(Items.GLASS_BOTTLE)
				.component(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(
						Optional.empty(), Optional.of(color), drinkEffects, Optional.empty()))
				.component(DataComponentTypes.CONSUMABLE, ConsumableComponents.drink()
						.consumeEffect(new ApplyEffectsConsumeEffect(drinkEffects))
						.build());
	}

	/** Thrown vial: tint-only POTION_CONTENTS (the payload is the impact behavior). */
	private static Item.Settings wurfphioleSettings(int color) {
		return new Item.Settings()
				.maxCount(1)
				.component(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(
						Optional.empty(), Optional.of(color), List.of(), Optional.empty()));
	}
}
