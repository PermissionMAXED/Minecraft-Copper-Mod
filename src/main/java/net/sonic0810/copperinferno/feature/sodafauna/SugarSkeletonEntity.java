package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;

/**
 * A skeleton cast from pressed white sugar. Behavioral tweak: sugar never freezes —
 * {@link #canFreeze()} returns false, so powder snow cannot damage it (which also blocks the
 * vanilla skeleton-to-stray conversion, since that is driven by freezing ticks). Drops Sugar
 * Crystals ({@code loot_table/entities/sugar_skeleton.json}).
 */
public class SugarSkeletonEntity extends SkeletonEntity {
	public SugarSkeletonEntity(EntityType<? extends SkeletonEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean canFreeze() {
		return false;
	}
}
