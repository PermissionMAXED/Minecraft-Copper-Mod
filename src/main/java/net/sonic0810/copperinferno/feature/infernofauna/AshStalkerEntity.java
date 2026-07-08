package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A spider that hunts through drifting ash. Tweak: its bite whips up a blinding ash cloud —
 * 4 seconds of BLINDNESS on the victim ({@code addStatusEffect(StatusEffectInstance, Entity)}
 * verified via javap). Drops string ({@code loot_table/entities/ash_stalker.json}).
 */
public class AshStalkerEntity extends SpiderEntity {
	public AshStalkerEntity(EntityType<? extends SpiderEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 80), this);
		}
		return hit;
	}
}
