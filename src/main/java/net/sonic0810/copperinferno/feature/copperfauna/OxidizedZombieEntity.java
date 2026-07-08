package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A zombie whose flesh has gone green with oxide. Behavioral tweak: its hit inflicts Hunger,
 * husk-style (corroded flesh is not nutritious). Drops Tarnish Dust
 * ({@code loot_table/entities/oxidized_zombie.json}).
 */
public class OxidizedZombieEntity extends ZombieEntity {
	public OxidizedZombieEntity(EntityType<? extends ZombieEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 140, 0), this);
		}
		return hit;
	}
}
