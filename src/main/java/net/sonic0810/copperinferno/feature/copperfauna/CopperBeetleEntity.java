package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A silverfish plated in copper chitin. Behavioral tweak: its bite briefly stiffens the target
 * with Slowness (the "oxidation seizes your joints" bit). Drops Copper Chitin
 * ({@code loot_table/entities/copper_beetle.json}).
 */
public class CopperBeetleEntity extends SilverfishEntity {
	public CopperBeetleEntity(EntityType<? extends SilverfishEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0), this);
		}
		return hit;
	}
}
