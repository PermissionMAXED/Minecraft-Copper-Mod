package net.sonic0810.copperinferno.feature.wildworld;

import java.util.function.Predicate;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.feature.constructs.ConstructsFeature;
import net.sonic0810.copperinferno.feature.infernofauna.InfernoFaunaFeature;
import net.sonic0810.copperinferno.feature.infernomobs.InfernoMobsFeature;

/**
 * Natural-spawn injection for the two WP16 Inferno biomes, {@code verdigris_jungle} and
 * {@code doom_basin}.
 *
 * <p>The three mob features ({@code infernomobs}, {@code infernofauna}, {@code constructs})
 * each register their Inferno spawns with a {@code BiomeSelectors.includeByKey} selector that
 * lists only the three original Inferno biomes (cinder_wastes / ember_grove / slag_sea), so
 * the two biomes added by this feature would otherwise be completely lifeless (their biome
 * JSONs ship empty {@code spawners} lists on purpose — ALL Inferno spawns are injected via
 * Fabric, exactly like the original three biomes).
 *
 * <p>This class lives in {@code feature.wildworld} — NOT in the mob packages — because the
 * wildworld feature is the OWNER of the two new biomes: package-ownership rules forbid
 * editing the other features' selector lists, whereas referencing their public static
 * {@link net.minecraft.entity.EntityType} constants is a read-only cross-package dependency.
 * When a biome owner adds a biome, it is the biome owner's job to say who lives there.
 * Weights/group sizes are copied verbatim from the donor features' own
 * {@code BiomeModifications.addSpawn} blocks so mob density matches the shipped biomes
 * (addSpawn signature verified via javap: {@code (Predicate<BiomeSelectionContext>,
 * SpawnGroup, EntityType<?>, int weight, int minGroup, int maxGroup)}).
 *
 * <p>Curation: the Verdigris Jungle is the Inferno's overgrown quarter, so it gets the full
 * passive-fauna roster plus only a light monster presence; the Doom Basin is the hostile
 * industrial wasteland, so it gets the full inferno-construct roster and the nastiest fauna,
 * with just a token pair of hardy creatures.
 */
public final class NewInfernoBiomeSpawns {
	private NewInfernoBiomeSpawns() {
	}

	public static void init() {
		// Runs from WildWorldFeature.init(), which CopperInferno.onInitialize() calls AFTER
		// InfernoMobsFeature/InfernoFaunaFeature/ConstructsFeature.init(), so every EntityType
		// constant referenced below is already registered and non-null.
		Predicate<BiomeSelectionContext> verdigrisJungle = BiomeSelectors.includeByKey(
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("verdigris_jungle")));
		Predicate<BiomeSelectionContext> doomBasin = BiomeSelectors.includeByKey(
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("doom_basin")));

		addVerdigrisJungleSpawns(verdigrisJungle);
		addDoomBasinSpawns(doomBasin);
	}

	/**
	 * Verdigris Jungle — overgrown: every CREATURE from InfernoFaunaFeature, the passive pair
	 * from InfernoMobsFeature (cinder strider + ash bat), and a light monster set.
	 */
	private static void addVerdigrisJungleSpawns(Predicate<BiomeSelectionContext> selector) {
		// Full passive-fauna roster (weights copied from InfernoFaunaFeature's CREATURE block).
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.CINDER_HOUND, 8, 2, 4);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.PYRE_RAVEN, 6, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.FUMAROLE_FROG, 8, 2, 4);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.SOOT_PIGLET, 8, 2, 4);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.SCORCH_GOAT, 6, 1, 3);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.EMBER_OWL, 6, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.CINDER_TOAD, 8, 2, 4);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.SMOLDER_TORTOISE, 5, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.PYROCLAST_GOLEM, 2, 1, 1);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.SOOT_SHEEP, 8, 2, 4);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.GLOW_STRIDER, 10, 1, 2);
		// Passive pair from InfernoMobsFeature (weights from its addSpawn block).
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoMobsFeature.CINDER_STRIDER, 30, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.AMBIENT, InfernoMobsFeature.ASH_BAT, 10, 2, 4);
		// Light monster set only — the jungle is dangerous, not overrun.
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, InfernoFaunaFeature.ASH_STALKER, 15, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, InfernoFaunaFeature.KILN_SPIDER, 15, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, InfernoMobsFeature.EMBER_WRAITH, 15, 1, 3);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, InfernoMobsFeature.SLAG_CRAWLER, 30, 1, 4);
	}

	/**
	 * Doom Basin — hostile/industrial: all 12 ConstructsFeature inferno constructs, the
	 * meanest infernomobs/infernofauna monsters, and two hardy creatures as sparse wildlife.
	 */
	private static void addDoomBasinSpawns(Predicate<BiomeSelectionContext> selector) {
		// The 12 inferno constructs (weights copied from ConstructsFeature's inferno block).
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.SLAG_CONSTRUCT, 10, 1, 1);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.FORGE_KEEPER, 12, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.ANVIL_MIMIC, 20, 1, 3);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.WIRE_WRAITH, 8, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.BOILER_BLAZE, 12, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.FURNACE_GOLEM, 8, 1, 1);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.STEAM_GHAST, 4, 1, 1);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.GRINDER_ZOGLIN, 8, 1, 1);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.MAGNET_MITE, 15, 1, 3);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.CRUCIBLE_WITCH, 6, 1, 1);
		BiomeModifications.addSpawn(selector, SpawnGroup.AMBIENT, ConstructsFeature.BELLOWS_BAT, 12, 2, 4);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, ConstructsFeature.DOOM_MARAUDER, 10, 1, 2);
		// The basin's molten vermin and haunters (weights from the donor features).
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, InfernoMobsFeature.MOLTEN_SLAGLING, 25, 2, 4);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, InfernoFaunaFeature.MOLTEN_MITE, 15, 1, 3);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, InfernoFaunaFeature.CHAR_PHANTOM, 6, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, InfernoFaunaFeature.INFERNO_GHASTLING, 5, 1, 1);
		// Sparse hardy wildlife. Same groups/weights as the old biomes: the brimstone bull is
		// registered (and spawned) in the MONSTER group by InfernoFaunaFeature, so it keeps
		// that group here; the soot sheep is the basin's lone true CREATURE entry.
		BiomeModifications.addSpawn(selector, SpawnGroup.MONSTER, InfernoFaunaFeature.BRIMSTONE_BULL, 8, 1, 2);
		BiomeModifications.addSpawn(selector, SpawnGroup.CREATURE, InfernoFaunaFeature.SOOT_SHEEP, 8, 2, 4);
	}
}
