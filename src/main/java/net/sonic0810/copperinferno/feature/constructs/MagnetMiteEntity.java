package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.world.World;

/**
 * An endermite with a magnetite core that clamps it safely onto any surface. Behavior is
 * vanilla endermite plus ONE tweak: it magnetically latches on landing and takes no fall damage
 * - see {@link #handleFallDamage(double, float, DamageSource)}. Drops Magnetite Shards
 * ({@code loot_table/entities/magnet_mite.json}).
 */
public class MagnetMiteEntity extends EndermiteEntity {
	public MagnetMiteEntity(EntityType<? extends EndermiteEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean handleFallDamage(double fallDistance, float damagePerDistance, DamageSource source) {
		// TWEAK: magnetic landing. handleFallDamage(double, float, DamageSource) verified via
		// javap; returning false skips all fall damage.
		return false;
	}
}
