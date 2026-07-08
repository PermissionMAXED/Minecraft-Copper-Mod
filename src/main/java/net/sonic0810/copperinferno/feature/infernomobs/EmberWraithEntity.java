package net.sonic0810.copperinferno.feature.infernomobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.world.World;

/**
 * A larger, tougher blaze native to the Inferno dimension. Behavior is pure vanilla blaze; the
 * visual/stat distinction comes from the default attributes registered in
 * {@link InfernoMobsFeature} (SCALE 1.15, MAX_HEALTH 24). Drops Wraith Embers
 * ({@code loot_table/entities/ember_wraith.json}).
 */
public class EmberWraithEntity extends BlazeEntity {
	public EmberWraithEntity(EntityType<? extends BlazeEntity> type, World world) {
		super(type, world);
	}
}
