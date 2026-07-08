package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * A pale, zero-calorie zombie. Behavioral tweak: zero sugar, zero burn —
 * {@link #burnsInDaylight()} returns false, so it shambles on through the day.
 * Drops Zero Syrup ({@code loot_table/entities/diet_zombie.json}).
 */
public class DietZombieEntity extends ZombieEntity {
	public DietZombieEntity(EntityType<? extends ZombieEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected boolean burnsInDaylight() {
		return false;
	}
}
