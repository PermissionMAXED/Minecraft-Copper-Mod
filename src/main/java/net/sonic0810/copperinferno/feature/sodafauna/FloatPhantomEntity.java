package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;

/**
 * A foam-white phantom bobbing along like the ice cream on a root beer float. Behavioral tweak:
 * SCALE 0.8 (a dessert-sized phantom, visibly smaller than vanilla) in the default attributes
 * registered by {@link SodaFaunaFeature}. Drops Float Foam
 * ({@code loot_table/entities/float_phantom.json}).
 */
public class FloatPhantomEntity extends PhantomEntity {
	public FloatPhantomEntity(EntityType<? extends PhantomEntity> type, World world) {
		super(type, world);
	}
}
