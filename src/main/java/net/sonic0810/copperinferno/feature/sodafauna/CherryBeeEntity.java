package net.sonic0810.copperinferno.feature.sodafauna;

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
 * A cherry-red bee brewing cherry syrup instead of honey. Behavioral tweak: the sweetest sting
 * in the game — after the vanilla sting (poison and all) it grants the target five seconds of
 * Regeneration as an apology ({@link #tryAttack}). {@code createChild} is overridden only for
 * type consistency (vanilla hard-codes {@code EntityType.BEE}; same fix as the infernomobs
 * Cinder Strider). Drops Cherry Syrup
 * ({@code loot_table/entities/cherry_bee.json}).
 */
public class CherryBeeEntity extends BeeEntity {
	public CherryBeeEntity(EntityType<? extends BeeEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), this);
		}
		return hit;
	}

	@Override
	public CherryBeeEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new CherryBeeEntity(SodaFaunaFeature.CHERRY_BEE, world);
	}
}
