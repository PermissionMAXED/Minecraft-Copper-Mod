package net.sonic0810.copperinferno.feature.infernodim.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.feature.infernodim.InfernoDimensionFeature;

/**
 * Client-side setup for the Inferno dimension: the portal pane renders TRANSLUCENT (its
 * texture uses real alpha). Uses the Fabric API 0.134.x
 * {@code net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap} (the legacy
 * blockrenderlayer.v1 class no longer exists).
 */
public final class InfernoDimensionFeatureClient {
	private InfernoDimensionFeatureClient() {
	}

	public static void initClient() {
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.TRANSLUCENT,
				InfernoDimensionFeature.INFERNO_PORTAL);
	}
}
