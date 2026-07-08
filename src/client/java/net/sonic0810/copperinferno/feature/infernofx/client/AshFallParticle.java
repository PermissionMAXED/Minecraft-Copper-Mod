package net.sonic0810.copperinferno.feature.infernofx.client;

import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

/**
 * Gray drifting ash flake (cloned from {@code core.client.CopperSparkleParticle}): sinks
 * slowly with a little sideways wander and darkens as it settles.
 */
public class AshFallParticle extends AnimatedParticle {
	AshFallParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
		super(world, x, y, z, spriteProvider, -8.0E-4f);
		this.velocityX = velocityX * 0.3 + (this.random.nextDouble() - 0.5) * 0.02;
		this.velocityY = -0.02 - this.random.nextDouble() * 0.02;
		this.velocityZ = velocityZ * 0.3 + (this.random.nextDouble() - 0.5) * 0.02;
		this.scale *= 0.9f;
		this.maxAge = 40 + this.random.nextInt(25);
		this.setColor(0x9A8F8A);
		this.setTargetColor(0x6E6560);
	}

	public static class Factory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
			return new AshFallParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
		}
	}
}
