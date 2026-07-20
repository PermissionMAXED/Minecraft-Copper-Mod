package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.world.World;

/**
 * A snow golem molded from hot caramel instead of snow. Behavioral tweak: caramel does not
 * dissolve — {@link #hurtByWater()} returns false, so rain and water no longer damage it.
 * Drops Caramel Globs ({@code loot_table/entities/caramel_golem.json}).
 */
public class CaramelGolemEntity extends SnowGolemEntity {
	public CaramelGolemEntity(EntityType<? extends SnowGolemEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean hurtByWater() {
		return false;
	}
}
