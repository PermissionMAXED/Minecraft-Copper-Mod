package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;

/**
 * A skeleton whose bones wear a blue-green patina plating. Behavioral tweak: ARMOR 4 (vanilla
 * skeleton: 0) in the default attributes registered by {@link CopperFaunaFeature}. Drops
 * Patina Bones ({@code loot_table/entities/patina_skeleton.json}).
 */
public class PatinaSkeletonEntity extends SkeletonEntity {
	public PatinaSkeletonEntity(EntityType<? extends SkeletonEntity> type, World world) {
		super(type, world);
	}
}
