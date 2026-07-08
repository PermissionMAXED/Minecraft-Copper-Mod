package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.world.World;

/**
 * A desiccated wanderer of the Cinder Wastes. Tweak: unlike a husk (which merely resists
 * sunlight) or a zombie, it never burns in daylight at all — {@code burnsInDaylight()}
 * (protected on HuskEntity/ZombieEntity, verified via javap) returns false, so wanderers keep
 * shambling under any sky, including the Overworld's if dragged through a portal. Drops rotten
 * flesh ({@code loot_table/entities/ash_wanderer.json}).
 */
public class AshWandererEntity extends HuskEntity {
	public AshWandererEntity(EntityType<? extends HuskEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected boolean burnsInDaylight() {
		return false;
	}
}
