package net.sonic0810.copperinferno.feature.gemalloy.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.core.content.AlloySets;
import net.sonic0810.copperinferno.feature.gemalloy.GemAlloyFeature;

/**
 * Client-side setup for the Gems &amp; Alloys wave: render layers only, following the
 * cinderstone/glasslight pattern. Every material's glass + glass pane renders TRANSLUCENT
 * (their textures use real alpha); grates and lanterns render CUTOUT (fully-on/fully-off
 * alpha, matching vanilla copper_grate/lantern). Everything else is an opaque cube and needs
 * no registration.
 */
public final class GemAlloyFeatureClient {
	private GemAlloyFeatureClient() {
	}

	public static void initClient() {
		for (AlloySets.AlloySet set : GemAlloyFeature.SETS) {
			BlockRenderLayerMap.putBlocks(BlockRenderLayer.TRANSLUCENT,
					set.glass(),
					set.glassPane());
			BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
					set.grate(),
					set.lantern());
		}
	}
}
