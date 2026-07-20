package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;

/**
 * A phantom stitched together from rusted scrap. Behavioral tweak: SCALE 1.2 (a visibly
 * bigger wingspan than the vanilla phantom) in the default attributes registered by
 * {@link CopperFaunaFeature}. Drops Patina Membranes
 * ({@code loot_table/entities/scrap_phantom.json}).
 */
public class ScrapPhantomEntity extends PhantomEntity {
	public ScrapPhantomEntity(EntityType<? extends PhantomEntity> type, World world) {
		super(type, world);
	}
}
