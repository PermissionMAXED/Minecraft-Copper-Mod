package net.sonic0810.copperinferno.feature.brews;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;
import net.sonic0810.copperinferno.feature.drpepper.DrPepperFeature;

/**
 * v4.1 "Brews" feature — three brewable {@link Potion}s registered under
 * {@code copper_inferno:<id>} in {@link Registries#POTION}, each reachable from an Awkward
 * Potion with an existing mod item as the brewing-stand ingredient:
 *
 * <ul>
 * <li>Awkward + Fizz Crystal (cuisine) -> <b>Fizz Potion</b> (Jump Boost II + Speed I, 90s)</li>
 * <li>Awkward + Charred Copper Ingot (materials) -> <b>Copperskin Potion</b> (Resistance I, 60s)</li>
 * <li>Awkward + Dr.Pepper (drpepper) -> <b>Doompepper Brew</b> (Dr.Pepper kick, 30s)</li>
 * </ul>
 *
 * <p>Design notes (verified against the 1.21.9 bytecode):
 * <ul>
 * <li>These are proper potion-registry recipes ({@code registerPotionRecipe}), unlike the
 * Dr.Pepper chain's item recipes, so splash/lingering conversion and tipped arrows work for
 * free and the vanilla brewing chains are untouched (no other feature brews from Awkward).</li>
 * <li>The {@code Potion} ctor's first argument is the base name used for the vanilla stack
 * translation keys {@code item.minecraft.{potion,splash_potion,lingering_potion,tipped_arrow}
 * .effect.<baseName>}; those keys live in the brews lang fragments.</li>
 * <li>{@code DrPepperFeature.DR_PEPPER_KICK} is reused (NOT re-registered); it is assigned in
 * {@code DrPepperFeature.init()}, which {@code CopperInferno.onInitialize} runs before us.</li>
 * </ul>
 */
public final class BrewsFeature {
	private BrewsFeature() {
	}

	/** Assigned in init(). */
	public static RegistryEntry<Potion> FIZZ_POTION;
	public static RegistryEntry<Potion> COPPERSKIN_POTION;
	public static RegistryEntry<Potion> DOOMPEPPER_BREW;

	public static void init() {
		if (DrPepperFeature.DR_PEPPER_KICK == null) {
			throw new IllegalStateException("BrewsFeature.init() must run after DrPepperFeature.init()");
		}

		// Jump Boost II + Speed I for 90s (1800 ticks).
		FIZZ_POTION = registerPotion("fizz_potion",
				new StatusEffectInstance(StatusEffects.JUMP_BOOST, 1800, 1),
				new StatusEffectInstance(StatusEffects.SPEED, 1800, 0));
		// Resistance I for 60s (1200 ticks).
		COPPERSKIN_POTION = registerPotion("copperskin_potion",
				new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 0));
		// The existing Dr.Pepper kick marker effect (drpepper feature) for 30s (600 ticks).
		DOOMPEPPER_BREW = registerPotion("doompepper_brew",
				new StatusEffectInstance(DrPepperFeature.DR_PEPPER_KICK, 600, 0));

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			FabricBrewingRecipeRegistryBuilder fabricBuilder = (FabricBrewingRecipeRegistryBuilder) builder;
			// Ingredients are EXISTING mod items registered by other features (owner in comment).
			fabricBuilder.registerPotionRecipe(Potions.AWKWARD,
					Ingredient.ofItems(modItem("fizz_crystal")), FIZZ_POTION); // cuisine
			fabricBuilder.registerPotionRecipe(Potions.AWKWARD,
					Ingredient.ofItems(modItem("charred_copper_ingot")), COPPERSKIN_POTION); // materials
			fabricBuilder.registerPotionRecipe(Potions.AWKWARD,
					Ingredient.ofItems(modItem("dr_pepper")), DOOMPEPPER_BREW); // drpepper
			CopperInferno.LOGGER.info("[COPPER INFERNO 1] Registered brews potion chains (3 potions from awkward)");
		});

		HandbookEntries.add(new HandbookEntry("items", "brews_fizz_potion",
				"copper_inferno:fizz_crystal", null, null, null, 0,
				"Brew an Awkward Potion with a Fizz Crystal and it erupts into the Fizz Potion: 90 seconds of Jump Boost II and Speed I, carbonation you can feel in your knees.",
				"Braue einen Seltsamen Trank mit einem Brausekristall und er sprudelt zum Sprudeltrank auf: 90 Sekunden Sprungkraft II und Schnelligkeit I \u2014 Kohlens\u00e4ure, die man in den Knien sp\u00fcrt."));
		HandbookEntries.add(new HandbookEntry("items", "brews_copperskin_potion",
				"copper_inferno:charred_copper_ingot", null, null, null, 0,
				"A Charred Copper Ingot brewed into an Awkward Potion coats you from within: the Copperskin Potion grants Resistance I for 60 seconds.",
				"Ein Verkohlter Kupferbarren, in einen Seltsamen Trank gebraut, \u00fcberzieht dich von innen: Der Kupferhauttrank gew\u00e4hrt 60 Sekunden Resistenz I."));
		HandbookEntries.add(new HandbookEntry("items", "brews_doompepper_brew",
				"copper_inferno:dr_pepper", null, null, null, 0,
				"Sacrifice a whole Dr.Pepper to an Awkward Potion and the Doompepper Brew stares back \u2014 30 seconds of the Dr.Pepper kick in bottled, splashable, lingering form.",
				"Opfere einen ganzen Dr.Pepper f\u00fcr einen Seltsamen Trank und das Doompepper-Gebr\u00e4u starrt zur\u00fcck \u2014 30 Sekunden Dr.Pepper-Kick in Flaschen-, Wurf- und Verweilform."));
	}

	/** Registers a potion under {@code copper_inferno:<path>}; the path doubles as the base name. */
	private static RegistryEntry<Potion> registerPotion(String path, StatusEffectInstance... effects) {
		return Registry.registerReference(Registries.POTION, CopperInferno.id(path), new Potion(path, effects));
	}

	/** Resolves a copper_inferno item registered by another feature. */
	private static Item modItem(String path) {
		return Registries.ITEM.get(Identifier.of(CopperInferno.MOD_ID, path));
	}
}
