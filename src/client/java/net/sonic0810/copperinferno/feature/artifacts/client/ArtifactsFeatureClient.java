package net.sonic0810.copperinferno.feature.artifacts.client;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.sonic0810.copperinferno.feature.artifacts.ArtifactsFeature;

/**
 * v4 "Artifacts" client hooks (WP17): registers the trophy pedestal's block-entity renderer
 * (the same vanilla {@code BlockEntityRendererFactories} hook the statue feature uses).
 */
public final class ArtifactsFeatureClient {
	private ArtifactsFeatureClient() {
	}

	public static void initClient() {
		BlockEntityRendererFactories.register(
				ArtifactsFeature.TROPHY_PEDESTAL_BLOCK_ENTITY,
				TrophyPedestalBlockEntityRenderer::new);
	}
}
