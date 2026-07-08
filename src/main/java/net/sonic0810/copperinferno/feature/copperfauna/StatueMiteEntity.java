package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.world.World;

/**
 * A mite that gnaws on copper statues. Behavioral tweak: SCALE 1.3 (an oversized endermite)
 * in the default attributes registered by {@link CopperFaunaFeature}. Drops Copper Chitin
 * ({@code loot_table/entities/statue_mite.json}).
 */
public class StatueMiteEntity extends EndermiteEntity {
	public StatueMiteEntity(EntityType<? extends EndermiteEntity> type, World world) {
		super(type, world);
	}
}
