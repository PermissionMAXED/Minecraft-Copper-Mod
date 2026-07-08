package net.sonic0810.copperinferno.feature.infernoflora.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.feature.infernoflora.InfernoFloraFeature;

/**
 * Client-side setup for Inferno flora: CUTOUT render layers for the six cross-model plants
 * (their textures use full alpha transparency around the plant shape) and for the fungal
 * light (its cube texture has fully transparent vent holes). Same
 * {@code BlockRenderLayerMap.putBlocks} pattern the glasslight feature uses.
 */
public final class InfernoFloraFeatureClient {
	private InfernoFloraFeatureClient() {
	}

	public static void initClient() {
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
				InfernoFloraFeature.EMBER_FUNGUS,
				InfernoFloraFeature.ASH_SPROUTS,
				InfernoFloraFeature.CINDER_ROOTS,
				InfernoFloraFeature.SMOLDER_BLOOM,
				InfernoFloraFeature.ASHEN_GRASS,
				InfernoFloraFeature.SPORE_CLUSTER,
				InfernoFloraFeature.FUNGAL_LIGHT);
	}
}
