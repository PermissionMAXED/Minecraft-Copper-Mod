package net.sonic0810.copperinferno.feature.driftwood;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Adds the three data-driven driftwood tree features to their coastal Overworld biomes.
 *
 * <p>The selectors use the vanilla {@link BiomeTags} category tags, so modded beaches, rivers
 * and oceans participate as well. The placed-feature keys resolve the JSON emitted by
 * {@code devtools/gen/driftwood_gen.py} when datapacks load.
 */
public final class DriftwoodTreesFeature {
	private DriftwoodTreesFeature() {
	}

	public static void init() {
		addTree("driftwood_trees", BiomeTags.IS_BEACH);
		addTree("tidewillow_trees", BiomeTags.IS_RIVER);
		addTree("brinepine_trees", BiomeTags.IS_OCEAN);
	}

	private static void addTree(String path,
			net.minecraft.registry.tag.TagKey<net.minecraft.world.biome.Biome> biomeTag) {
		RegistryKey<PlacedFeature> feature =
				RegistryKey.of(RegistryKeys.PLACED_FEATURE, CopperInferno.id(path));
		BiomeModifications.addFeature(BiomeSelectors.tag(biomeTag),
				GenerationStep.Feature.VEGETAL_DECORATION, feature);
	}
}
