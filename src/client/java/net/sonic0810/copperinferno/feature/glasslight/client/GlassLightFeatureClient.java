package net.sonic0810.copperinferno.feature.glasslight.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.feature.glasslight.GlassLightFeature;

/**
 * Client registrations for the glass & light feature: render layers only.
 *
 * <p>Uses {@code net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap} (the current
 * Fabric API 0.134.x class; the legacy {@code ...api.blockrenderlayer.v1.BlockRenderLayerMap} no
 * longer exists) together with the vanilla {@link BlockRenderLayer} enum. Glass and panes render
 * TRANSLUCENT (real alpha), lanterns and the chandelier render CUTOUT (their textures only use
 * fully-on/fully-off alpha). Lamps and the glowing syrup block are plain opaque cubes and need no
 * render-layer registration.
 */
public final class GlassLightFeatureClient {
	private GlassLightFeatureClient() {
	}

	public static void initClient() {
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.TRANSLUCENT,
				GlassLightFeature.COPPER_GLASS,
				GlassLightFeature.EXPOSED_COPPER_GLASS,
				GlassLightFeature.WEATHERED_COPPER_GLASS,
				GlassLightFeature.OXIDIZED_COPPER_GLASS,
				GlassLightFeature.COPPER_GLASS_PANE,
				GlassLightFeature.OXIDIZED_COPPER_GLASS_PANE);

		BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
				GlassLightFeature.INFERNO_LANTERN,
				GlassLightFeature.SODA_LANTERN,
				GlassLightFeature.COPPER_CHANDELIER);
	}
}
