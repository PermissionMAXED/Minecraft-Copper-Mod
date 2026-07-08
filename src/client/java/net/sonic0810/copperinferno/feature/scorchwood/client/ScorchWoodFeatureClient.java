package net.sonic0810.copperinferno.feature.scorchwood.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.feature.scorchwood.ScorchWoodFeature;

/**
 * Client-side setup for the Scorched Woods: render layers only, following the gemalloy
 * pattern. Saplings are cross models with fully-transparent pixels (CUTOUT, like vanilla
 * saplings); leaves render CUTOUT_MIPPED exactly like vanilla leaves. The 13 building
 * blocks of each set are opaque cubes and need no registration.
 */
public final class ScorchWoodFeatureClient {
	private ScorchWoodFeatureClient() {
	}

	public static void initClient() {
		for (Block sapling : ScorchWoodFeature.SAPLINGS) {
			BlockRenderLayerMap.putBlock(sapling, BlockRenderLayer.CUTOUT);
		}
		for (Block leaves : ScorchWoodFeature.LEAVES) {
			BlockRenderLayerMap.putBlock(leaves, BlockRenderLayer.CUTOUT_MIPPED);
		}
	}
}
