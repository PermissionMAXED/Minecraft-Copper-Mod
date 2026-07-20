package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A vindicator sworn to the DOOM rites. Behavior is vanilla vindicator plus ONE tweak: its axe
 * blows carry a withering curse - see {@link #tryAttack(ServerWorld, Entity)}. Drops Doom
 * Emblems ({@code loot_table/entities/doom_acolyte.json}).
 */
public class DoomAcolyteEntity extends VindicatorEntity {
	public DoomAcolyteEntity(EntityType<? extends VindicatorEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		// TWEAK: withering axe. StatusEffectInstance(RegistryEntry, duration, amplifier) and
		// LivingEntity.addStatusEffect(StatusEffectInstance, Entity) verified via javap.
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 0), this);
		}
		return hit;
	}
}
