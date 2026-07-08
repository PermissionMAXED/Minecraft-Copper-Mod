package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A wind-up brass bee ticking through the Overworld's meadows. Behavior is vanilla bee plus ONE
 * tweak: a machine has no blood to poison - see
 * {@link #canHaveStatusEffect(StatusEffectInstance)}. {@code createChild} is overridden only
 * for type consistency (vanilla hard-codes {@code EntityType.BEE}; same fix as the infernomobs
 * Cinder Strider). Drops Copper Gearwheels
 * ({@code loot_table/entities/clockwork_bee.json}).
 */
public class ClockworkBeeEntity extends BeeEntity {
	public ClockworkBeeEntity(EntityType<? extends BeeEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean canHaveStatusEffect(StatusEffectInstance effect) {
		// TWEAK: unpoisonable machine. LivingEntity.canHaveStatusEffect(StatusEffectInstance)
		// verified via javap.
		if (effect.getEffectType() == StatusEffects.POISON) {
			return false;
		}
		return super.canHaveStatusEffect(effect);
	}

	@Override
	public ClockworkBeeEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new ClockworkBeeEntity(ConstructsFeature.CLOCKWORK_BEE, world);
	}
}
