package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A bee that hums with static charge. Behavioral tweak: on top of the vanilla sting (poison,
 * stinger loss), the electric discharge leaves the target reeling with Nausea.
 * {@code createChild} is overridden only for type consistency (vanilla hard-codes
 * {@code EntityType.BEE}; same fix as the infernomobs Cinder Strider). Drops Storm
 * Cells ({@code loot_table/entities/ampere_bee.json}).
 */
public class AmpereBeeEntity extends BeeEntity {
	public AmpereBeeEntity(EntityType<? extends BeeEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0), this);
		}
		return hit;
	}

	@Override
	public AmpereBeeEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new AmpereBeeEntity(CopperFaunaFeature.AMPERE_BEE, world);
	}
}
