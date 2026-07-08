package net.sonic0810.copperinferno.feature.infernogardens.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.feature.infernogardens.InfernoGardensFeature;

/**
 * Client-side setup for the Inferno gardens: CUTOUT render layers for the 15 cross-model
 * garden plants and the multiface verdigris lichen (their textures use full alpha
 * transparency around the growth shape). Same {@code BlockRenderLayerMap.putBlocks}
 * pattern as InfernoFloraFeatureClient.
 */
public final class InfernoGardensFeatureClient {
	private InfernoGardensFeatureClient() {
	}

	public static void initClient() {
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
				InfernoGardensFeature.EMBER_LILY,
				InfernoGardensFeature.CINDER_FERN,
				InfernoGardensFeature.SEAR_SPRIGS,
				InfernoGardensFeature.MOLTEN_BUD,
				InfernoGardensFeature.ASH_BRAMBLE,
				InfernoGardensFeature.GLOW_TENDRILS,
				InfernoGardensFeature.SOOT_PUFF,
				InfernoGardensFeature.SLAG_THISTLE,
				InfernoGardensFeature.PYRE_REED,
				InfernoGardensFeature.COPPER_ROSE,
				InfernoGardensFeature.INFERNO_ORCHID,
				InfernoGardensFeature.CHARRED_SHRUB,
				InfernoGardensFeature.GILDED_CLOVER,
				InfernoGardensFeature.HANGING_EMBER_ROOTS,
				InfernoGardensFeature.ASH_VEIL,
				InfernoGardensFeature.VERDIGRIS_LICHEN);
	}
}
