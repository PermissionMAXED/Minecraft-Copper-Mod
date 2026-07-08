package net.sonic0810.copperinferno.feature.infernofx.client;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.sonic0810.copperinferno.feature.infernofx.InfernoFxFeature;

/**
 * Client-side setup for Inferno ambience: registers the sprite-backed factories for the two
 * infernofx particles (same ParticleFactoryRegistry pattern as {@code core.client.CoreClient}).
 */
public final class InfernoFxFeatureClient {
	private InfernoFxFeatureClient() {
	}

	public static void initClient() {
		ParticleFactoryRegistry.getInstance().register(InfernoFxFeature.EMBER_SPARK, EmberSparkParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(InfernoFxFeature.ASH_FALL, AshFallParticle.Factory::new);
	}
}
