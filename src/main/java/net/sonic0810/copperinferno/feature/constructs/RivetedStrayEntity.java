package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.world.World;

/**
 * A stray whose bones are riveted with cold-forged steel bands. Behavior is vanilla stray
 * (slowness arrows included) plus ONE tweak: powder snow cannot freeze it - see
 * {@link #canFreeze()}. Drops Rivet Bolts
 * ({@code loot_table/entities/riveted_stray.json}).
 */
public class RivetedStrayEntity extends StrayEntity {
	public RivetedStrayEntity(EntityType<? extends StrayEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean canFreeze() {
		// TWEAK: cold-proof rivets. Entity.canFreeze() verified via javap.
		return false;
	}
}
