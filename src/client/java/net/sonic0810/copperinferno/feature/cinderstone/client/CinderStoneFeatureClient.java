package net.sonic0810.copperinferno.feature.cinderstone.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.feature.cinderstone.CinderStoneFeature;

/**
 * Client-side setup for the Cinder Stone block set: render layers only (see the glasslight
 * feature for the same pattern). Glass and panes render TRANSLUCENT (their textures use real
 * alpha), the two lanterns render CUTOUT (fully-on/fully-off alpha in the template_lantern
 * layout). Everything else is a plain opaque cube and needs no registration.
 */
public final class CinderStoneFeatureClient {
	private CinderStoneFeatureClient() {
	}

	public static void initClient() {
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.TRANSLUCENT,
				CinderStoneFeature.CINDER_GLASS,
				CinderStoneFeature.CINDER_GLASS_PANE,
				CinderStoneFeature.SMOLDER_GLASS,
				CinderStoneFeature.SMOLDER_GLASS_PANE);

		BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
				CinderStoneFeature.EMBER_LANTERN,
				CinderStoneFeature.ASHEN_LANTERN);
	}
}
