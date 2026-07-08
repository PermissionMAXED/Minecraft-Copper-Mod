package net.sonic0810.copperinferno.feature.cuisine;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

/**
 * "Ember Belly" — a warm coal glowing in the stomach: any fire on the entity is smothered
 * every tick (the {@code feature/infernofx} Heat Ward pattern) and players slowly digest the
 * ember, regaining a sliver of hunger every 10 seconds.
 *
 * <p>Signatures verified via javap on 1.21.9 Yarn:
 * {@code boolean applyUpdateEffect(ServerWorld, LivingEntity, int)},
 * {@code boolean canApplyUpdateEffect(int, int)} and
 * {@code HungerManager.add(int, float)}.
 */
public class EmberBellyEffect extends StatusEffect {
	/** Glowing ember orange. */
	public static final int COLOR = 0xE2581E;

	/** One food point is restored every 200 ticks (10 s). */
	private static final int DIGEST_INTERVAL = 200;

	public EmberBellyEffect() {
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
		if (entity instanceof PlayerEntity player && entity.age % DIGEST_INTERVAL == 0) {
			player.getHungerManager().add(1 + amplifier, 0.2f);
		}
		return true;
	}
}
