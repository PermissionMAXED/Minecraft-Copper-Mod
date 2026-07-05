package net.sonic0810.copperinferno.feature.statue.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.sonic0810.copperinferno.feature.statue.PlayerStatueFeature;

/**
 * Client-side setup for copper player statues (renderers, models). Content is registered by the
 * statue feature worker.
 */
public final class PlayerStatueFeatureClient {
	private PlayerStatueFeatureClient() {
	}

	public static void initClient() {
		// Vanilla registration hook, made public by Fabric's transitive access wideners
		// (the fabric-rendering-v1 BlockEntityRendererRegistry is deprecated in favor of this).
		BlockEntityRendererFactories.register(
				PlayerStatueFeature.COPPER_PLAYER_STATUE_BLOCK_ENTITY,
				CopperPlayerStatueBlockEntityRenderer::new);

		PlayerStatueFeature.statueScreenOpener =
				pos -> MinecraftClient.getInstance().setScreen(new CopperPlayerStatueScreen(pos));
	}
}
