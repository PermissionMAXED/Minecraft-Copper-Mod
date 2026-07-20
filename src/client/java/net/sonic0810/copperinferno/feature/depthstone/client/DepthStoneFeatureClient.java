package net.sonic0810.copperinferno.feature.depthstone.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sonic0810.copperinferno.core.content.PaletteSets.PaletteSet;
import net.sonic0810.copperinferno.feature.depthstone.DepthStoneFeature;

/**
 * Client-side setup for the Depthstone palettes: render layers only (same pattern as the
 * cinderstone/glasslight features). Every palette's glass and pane render TRANSLUCENT (their
 * textures use real alpha), the lantern renders CUTOUT (fully-on/fully-off alpha in the
 * template_lantern layout). Everything else is a plain opaque cube and needs no registration.
 */
public final class DepthStoneFeatureClient {
	private DepthStoneFeatureClient() {
	}

	public static void initClient() {
		PaletteSet[] palettes = {
				DepthStoneFeature.VOIDSTONE, DepthStoneFeature.DUSKSHALE,
				DepthStoneFeature.PYROCLAST, DepthStoneFeature.CINDERMARL,
				DepthStoneFeature.FUMAROLITE, DepthStoneFeature.SCORCHSLATE,
				DepthStoneFeature.EMBERCHERT, DepthStoneFeature.SLAGBASALT,
				DepthStoneFeature.ASHFLINT, DepthStoneFeature.CHARWACKE,
				DepthStoneFeature.SMOKESTONE, DepthStoneFeature.KILNROCK,
				DepthStoneFeature.MAGMARL, DepthStoneFeature.SOOTSTONE,
				DepthStoneFeature.VITRICITE, DepthStoneFeature.COALSPAR};
		for (PaletteSet set : palettes) {
			BlockRenderLayerMap.putBlocks(BlockRenderLayer.TRANSLUCENT, set.glass(), set.glassPane());
			BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, set.lantern());
		}
	}
}
