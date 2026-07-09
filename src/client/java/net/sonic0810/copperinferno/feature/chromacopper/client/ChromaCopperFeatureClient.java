package net.sonic0810.copperinferno.feature.chromacopper.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.core.content.PaletteSets.PaletteSet;
import net.sonic0810.copperinferno.feature.chromacopper.ChromaCopperFeature;

/**
 * Client registrations for the Chromatic Copper palettes: render layers only (same
 * mechanism as the glasslight/cinderstone features). The 16 glass cubes and 16 glass panes
 * render TRANSLUCENT (their textures use real alpha); the 16 lanterns render CUTOUT
 * (fully-on/fully-off alpha in the vanilla template_lantern layout). Everything else in the
 * palettes is an opaque cube and needs no render-layer registration.
 */
public final class ChromaCopperFeatureClient {
	private ChromaCopperFeatureClient() {
	}

	public static void initClient() {
		for (PaletteSet set : ChromaCopperFeature.all()) {
			BlockRenderLayerMap.putBlocks(BlockRenderLayer.TRANSLUCENT, set.glass(), set.glassPane());
			BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, set.lantern());
		}
	}
}
