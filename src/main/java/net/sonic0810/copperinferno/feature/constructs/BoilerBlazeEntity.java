package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.world.World;

/**
 * A blaze sealed inside a riveted boiler shell. Behavior is vanilla blaze plus ONE tweak: the
 * watertight shell means water and rain no longer hurt it - see {@link #hurtByWater()}. Drops
 * Boiler Plates ({@code loot_table/entities/boiler_blaze.json}).
 */
public class BoilerBlazeEntity extends BlazeEntity {
	public BoilerBlazeEntity(EntityType<? extends BlazeEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean hurtByWater() {
		// TWEAK: watertight boiler. LivingEntity.hurtByWater() verified via javap (vanilla
		// BlazeEntity returns true).
		return false;
	}
}
