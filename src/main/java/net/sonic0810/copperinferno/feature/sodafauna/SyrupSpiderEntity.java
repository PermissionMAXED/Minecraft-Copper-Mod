package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A spider dripping with maple-thick syrup. Behavioral tweak: its sticky bite bogs the target
 * down with Slowness II for five seconds ({@link #tryAttack}). Drops Caramel Globs
 * ({@code loot_table/entities/syrup_spider.json}).
 */
public class SyrupSpiderEntity extends SpiderEntity {
	public SyrupSpiderEntity(EntityType<? extends SpiderEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1), this);
		}
		return hit;
	}
}
