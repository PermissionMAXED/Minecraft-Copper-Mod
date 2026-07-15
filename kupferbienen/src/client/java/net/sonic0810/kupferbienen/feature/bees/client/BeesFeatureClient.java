package net.sonic0810.kupferbienen.feature.bees.client;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.sonic0810.kupferbienen.feature.bees.BeesFeature;

/**
 * Client-side setup for the bees feature: both bees reuse the vanilla bee renderer with a fixed
 * recolored texture (vanilla {@code EntityRendererFactories.register} is access-widened by
 * Fabric's transitive access wideners — InfernoMobsFeatureClient pattern), and the Kupferbluete
 * cross model needs the CUTOUT render layer for its alpha-masked texture.
 */
public final class BeesFeatureClient {
	private BeesFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(BeesFeature.KUPFERBIENE, KupferbieneRenderer::new);
		EntityRendererFactories.register(BeesFeature.GRUENSPANBIENE, GruenspanbieneRenderer::new);
		BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, BeesFeature.KUPFERBLUETE);
	}
}
