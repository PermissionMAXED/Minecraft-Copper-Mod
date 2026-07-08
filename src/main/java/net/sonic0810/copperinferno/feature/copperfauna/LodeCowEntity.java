package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.world.World;

/**
 * A cow that grazes near ore seams and carries the weight to prove it. Behavioral tweak:
 * KNOCKBACK_RESISTANCE 0.6 (vanilla cow: 0.0) in the default attributes registered by
 * {@link CopperFaunaFeature}. Drops Lode Hides ({@code loot_table/entities/lode_cow.json}).
 */
public class LodeCowEntity extends CowEntity {
	public LodeCowEntity(EntityType<? extends CowEntity> type, World world) {
		super(type, world);
	}
}
