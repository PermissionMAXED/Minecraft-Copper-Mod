package net.sonic0810.copperinferno.feature.wildworld.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.random.Random;
import net.sonic0810.copperinferno.core.ModDimensions;
import net.sonic0810.copperinferno.feature.infernofx.InfernoFxFeature;

/**
 * Client half of the Ashfall feature: while the local player is anywhere in the Inferno
 * dimension, gray ash flakes drift down around them. Reuses the existing
 * {@code copper_inferno:ash_fall} particle — the type is registered by
 * {@code InfernoFxFeature} and its sprite factory by
 * {@code feature.infernofx.client.InfernoFxFeatureClient} (AshFallParticle), so this class only
 * has to emit particles (same {@code ClientTickEvents.END_CLIENT_TICK} pattern as
 * {@code DrPepperFeatureClient}).
 */
public final class AshfallAmbienceClient {
	private AshfallAmbienceClient() {
	}

	/** Ash flakes spawned per client tick around the player. */
	private static final int FLAKES_PER_TICK = 3;

	public static void initClient() {
		ClientTickEvents.END_CLIENT_TICK.register(AshfallAmbienceClient::onEndTick);
	}

	private static void onEndTick(MinecraftClient client) {
		if (client.isPaused() || client.player == null || client.world == null
				|| client.world.getRegistryKey() != ModDimensions.INFERNO_WORLD) {
			return;
		}
		Random random = client.world.random;
		for (int i = 0; i < FLAKES_PER_TICK; i++) {
			double x = client.player.getX() + (random.nextDouble() - 0.5) * 24.0;
			double y = client.player.getY() + 3.0 + random.nextDouble() * 9.0;
			double z = client.player.getZ() + (random.nextDouble() - 0.5) * 24.0;
			client.world.addParticleClient(InfernoFxFeature.ASH_FALL, x, y, z, 0.0, 0.0, 0.0);
		}
	}
}
