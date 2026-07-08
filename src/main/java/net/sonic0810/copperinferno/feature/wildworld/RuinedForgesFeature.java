package net.sonic0810.copperinferno.feature.wildworld;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Ruined Forges: small abandoned smithies scattered across all five Inferno biomes. The ruin
 * itself is built in code by {@link RuinedForgeWorldgenFeature}, a custom
 * {@code Feature<DefaultFeatureConfig>} registered here in {@code Registries.FEATURE}
 * (signature verified via javap) and referenced from
 * {@code data/copper_inferno/worldgen/configured_feature/ruined_forge.json}; the placement is
 * data-driven ({@code placed_feature/ruined_forge.json}) and injected via
 * {@code BiomeModifications.addFeature} at SURFACE_STRUCTURES.
 */
public final class RuinedForgesFeature {
	private RuinedForgesFeature() {
	}

	public static Feature<DefaultFeatureConfig> RUINED_FORGE;

	public static void init() {
		RUINED_FORGE = Registry.register(Registries.FEATURE,
				CopperInferno.id("ruined_forge"),
				new RuinedForgeWorldgenFeature(DefaultFeatureConfig.CODEC));

		RegistryKey<PlacedFeature> forge =
				RegistryKey.of(RegistryKeys.PLACED_FEATURE, CopperInferno.id("ruined_forge"));
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("cinder_wastes")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("ember_grove")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("slag_sea")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("verdigris_jungle")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("doom_basin"))),
				GenerationStep.Feature.SURFACE_STRUCTURES, forge);

		HandbookEntries.add(new HandbookEntry("dimension", "wildworld_ruined_forges",
				"copper_inferno:cracked_inferno_bricks", null, null, null, 0,
				"Ruined Forges: crumbling smithies left behind by whoever worked the Inferno before"
						+ " you. Look for broken inferno-brick walls, a leaning chimney and a hearth of"
						+ " molten slag — sometimes still crowned by a glowing inferno core.",
				"Verfallene Schmieden: zerbröckelnde Werkstätten, zurückgelassen von jenen, die das"
						+ " Inferno vor dir bearbeiteten. Halte Ausschau nach geborstenen"
						+ " Infernoziegel-Mauern, einem schiefen Kamin und einer Esse aus geschmolzener"
						+ " Schlacke — manchmal noch von einem glühenden Infernokern gekrönt."));
	}
}
