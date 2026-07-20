package net.sonic0810.kupferbienen.feature.flora.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.kupferbienen.feature.flora.FloraFeature;

/**
 * Client-side setup for the flora feature: cross/crop models need the CUTOUT render layer
 * (BeesFeatureClient pattern: {@code BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, ...)}).
 */
public final class FloraFeatureClient {
	private FloraFeatureClient() {
	}

	public static void initClient() {
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
				FloraFeature.WACHSBLUME,
				FloraFeature.HONIGKELCH,
				FloraFeature.BLITZBLUME,
				FloraFeature.GRUENSPANROESCHEN,
				FloraFeature.TIEFENGLOCKE,
				FloraFeature.KUPFERKLEE);
	}
}
