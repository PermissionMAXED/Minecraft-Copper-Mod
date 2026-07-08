package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.world.World;

/**
 * A ghast-shaped cloud of scalding boiler steam drifting over the Inferno. Behavior is vanilla
 * ghast plus ONE tweak: water condenses it - unlike a vanilla ghast, it takes damage in water
 * and rain - see {@link #hurtByWater()}. Drops Boiler Plates
 * ({@code loot_table/entities/steam_ghast.json}).
 */
public class SteamGhastEntity extends GhastEntity {
	public SteamGhastEntity(EntityType<? extends GhastEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean hurtByWater() {
		// TWEAK: condensation. LivingEntity.hurtByWater() verified via javap (vanilla ghasts
		// return false).
		return true;
	}
}
