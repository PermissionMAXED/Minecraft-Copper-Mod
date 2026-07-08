package net.sonic0810.copperinferno.feature.wildworld;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Smolder Crystal Cave: geode-like pockets that cluster the EXISTING smolder_crystal_ore into
 * huge veins (a {@code minecraft:ore} configured feature with size 24 and a rarity-gated
 * placement, emitted by {@code devtools/gen/wildworld_gen.py}). Injected into all five Inferno
 * biomes — the three shipped ones plus the two new WP16 biomes — via
 * {@code BiomeModifications.addFeature} at UNDERGROUND_ORES (same Fabric pattern as
 * {@code GemAlloyFeature}); the addition lands at the end of the step list in every selected
 * biome, keeping the placed-feature ordering cycle-free.
 */
public final class SmolderCrystalCaveFeature {
	private SmolderCrystalCaveFeature() {
	}

	public static void init() {
		RegistryKey<PlacedFeature> cave =
				RegistryKey.of(RegistryKeys.PLACED_FEATURE, CopperInferno.id("smolder_crystal_cave"));
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("cinder_wastes")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("ember_grove")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("slag_sea")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("verdigris_jungle")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("doom_basin"))),
				GenerationStep.Feature.UNDERGROUND_ORES, cave);

		HandbookEntries.add(new HandbookEntry("dimension", "wildworld_smolder_crystal_cave",
				"copper_inferno:smolder_crystal_ore", null, null, null, 0,
				"Smolder Crystal Caves: rare geode pockets deep in the Inferno's cinderstone where"
						+ " smolder crystal ore clusters into massive verdigris veins — one lucky strike"
						+ " can out-yield a whole night of scattered prospecting.",
				"Schwelkristall-Höhlen: seltene Geodentaschen tief im Zunderstein des Infernos, in"
						+ " denen sich Schwelkristall-Erz zu gewaltigen Grünspanadern ballt — ein"
						+ " Glückstreffer kann eine ganze Nacht verstreuten Schürfens übertreffen."));
	}
}
