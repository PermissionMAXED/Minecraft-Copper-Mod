package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A cave spider that slithers through Overworld pipework like a serpent. Behavior is vanilla
 * cave spider (venom included) plus ONE tweak: its crushing coils also apply Slowness - see
 * {@link #tryAttack(ServerWorld, Entity)}. Drops string
 * ({@code loot_table/entities/pipe_serpent.json}).
 */
public class PipeSerpentEntity extends CaveSpiderEntity {
	public PipeSerpentEntity(EntityType<? extends CaveSpiderEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		// TWEAK: crushing coils. CaveSpiderEntity.tryAttack(ServerWorld, Entity) verified via
		// javap (it layers the vanilla poison; the slowness stacks on top).
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1), this);
		}
		return hit;
	}
}
