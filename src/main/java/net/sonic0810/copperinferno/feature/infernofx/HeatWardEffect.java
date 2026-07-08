package net.sonic0810.copperinferno.feature.infernofx;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

/**
 * "Heat Ward" — while active the entity is continuously extinguished (any fire ticks are
 * cleared every tick) and a warm ember-spark shimmer rises around it.
 *
 * <p>Signatures verified via javap on 1.21.9 Yarn:
 * {@code boolean applyUpdateEffect(ServerWorld, LivingEntity, int)} and
 * {@code boolean canApplyUpdateEffect(int, int)} (returning true keeps the effect ticking).
 */
public class HeatWardEffect extends StatusEffect {
	/** Warm ember orange, matching the ember_spark particle. */
	public static final int COLOR = 0xFF9B45;

	/** How often (in ticks) the shimmer particles are emitted. */
	private static final int SHIMMER_INTERVAL = 10;

	public HeatWardEffect() {
		super(StatusEffectCategory.BENEFICIAL, COLOR);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		// Tick every tick: extinguishing must win against fire re-igniting the entity.
		return true;
	}

	@Override
	public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
		if (entity.isOnFire()) {
			entity.setFireTicks(0);
		}
		if (entity.age % SHIMMER_INTERVAL == 0) {
			world.spawnParticles(InfernoFxFeature.EMBER_SPARK,
					entity.getX(), entity.getBodyY(0.5), entity.getZ(),
					2, entity.getWidth() * 0.4, entity.getHeight() * 0.25, entity.getWidth() * 0.4, 0.01);
		}
		return true;
	}
}
