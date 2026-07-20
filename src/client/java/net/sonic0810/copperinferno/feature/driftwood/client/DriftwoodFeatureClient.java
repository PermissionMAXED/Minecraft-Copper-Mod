package net.sonic0810.copperinferno.feature.driftwood.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.feature.driftwood.DriftwoodFeature;

/**
 * Cutout render layers for the five sprite-shaped coastal decorations and three custom leaf
 * blocks. Foam Moss and all wood-set blocks are opaque cubes and need no registration.
 */
public final class DriftwoodFeatureClient {
	private DriftwoodFeatureClient() {
	}

	public static void initClient() {
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
				DriftwoodFeature.VERDIGRIS_KELP,
				DriftwoodFeature.SALT_SPROUT,
				DriftwoodFeature.TIDE_LILY,
				DriftwoodFeature.BARNACLE_CLUSTER,
				DriftwoodFeature.PEARL_CLUSTER);
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT_MIPPED,
				DriftwoodFeature.DRIFTWOOD_LEAVES,
				DriftwoodFeature.TIDEWILLOW_LEAVES,
				DriftwoodFeature.BRINEPINE_LEAVES);
	}
}
