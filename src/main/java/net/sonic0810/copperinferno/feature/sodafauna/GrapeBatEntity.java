package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.world.World;

/**
 * A plump grape-purple bat that hangs in caves like a bunch of grapes. Behavioral tweak:
 * MAX_HEALTH 10 (a vanilla bat has 6 — grape soda is fortifying) in the default attributes
 * registered by {@link SodaFaunaFeature}. Drops Gummy Drops
 * ({@code loot_table/entities/grape_bat.json}).
 */
public class GrapeBatEntity extends BatEntity {
	public GrapeBatEntity(EntityType<? extends BatEntity> type, World world) {
		super(type, world);
	}
}
