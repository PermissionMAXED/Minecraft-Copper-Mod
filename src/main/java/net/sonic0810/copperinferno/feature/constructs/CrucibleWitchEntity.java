package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.world.World;

/**
 * A witch who stirs a crucible of molten metal instead of a cauldron, at home in the Inferno.
 * Behavior is vanilla witch (potion lobbing included) plus ONE tweak: a lifetime over the
 * crucible has made her fireproof - see {@link #isFireImmune()}. Drops Crucible Dross
 * ({@code loot_table/entities/crucible_witch.json}).
 */
public class CrucibleWitchEntity extends WitchEntity {
	public CrucibleWitchEntity(EntityType<? extends WitchEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean isFireImmune() {
		// TWEAK: crucible-hardened. Entity.isFireImmune() verified via javap.
		return true;
	}
}
