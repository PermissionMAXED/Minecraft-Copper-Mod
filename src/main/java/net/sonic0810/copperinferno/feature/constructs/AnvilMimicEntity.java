package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.World;

/**
 * A squat, anvil-heavy silverfish cast from Inferno pig iron. Behavior is vanilla silverfish
 * plus ONE tweak: it is far too heavy to be knocked back - see
 * {@link #takeKnockback(double, double, double)}. Drops Anvil Shards
 * ({@code loot_table/entities/anvil_mimic.json}).
 */
public class AnvilMimicEntity extends SilverfishEntity {
	public AnvilMimicEntity(EntityType<? extends SilverfishEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void takeKnockback(double strength, double x, double z) {
		// TWEAK: anvils don't budge. LivingEntity.takeKnockback(double, double, double)
		// verified via javap; a no-op override cancels all knockback.
	}
}
