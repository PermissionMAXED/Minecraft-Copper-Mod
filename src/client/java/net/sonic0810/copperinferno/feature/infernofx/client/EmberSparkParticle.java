package net.sonic0810.copperinferno.feature.infernofx.client;

import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

/**
 * Rising orange ember spark (cloned from {@code core.client.CopperSparkleParticle}): a hot
 * bright speck that drifts upward (positive upwards acceleration + upward starting velocity)
 * and cools toward deep ember red as it fades.
 */
public class EmberSparkParticle extends AnimatedParticle {
	EmberSparkParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
		super(world, x, y, z, spriteProvider, 0.004f);
		this.velocityX = velocityX * 0.5;
		this.velocityY = Math.abs(velocityY) * 0.5 + 0.04;
		this.velocityZ = velocityZ * 0.5;
		this.scale *= 0.75f;
		this.maxAge = 20 + this.random.nextInt(12);
		this.setColor(0xFFB16B);
		this.setTargetColor(0xE25822);
	}

	public static class Factory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
			return new EmberSparkParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
		}
	}
}
