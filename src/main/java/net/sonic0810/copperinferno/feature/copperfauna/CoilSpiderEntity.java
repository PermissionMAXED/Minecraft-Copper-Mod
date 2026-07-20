package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A spider strung with live copper coils. Behavioral tweak: its shock-bite lights the target up
 * with Glowing. Drops Live Wires ({@code loot_table/entities/coil_spider.json}).
 */
public class CoilSpiderEntity extends SpiderEntity {
	public CoilSpiderEntity(EntityType<? extends SpiderEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0), this);
		}
		return hit;
	}
}
