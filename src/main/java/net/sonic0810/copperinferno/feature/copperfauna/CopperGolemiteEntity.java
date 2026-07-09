package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.world.World;

/**
 * A stubby snow-golem-shaped construct cast from copper. Behavioral tweak: unlike its snowy
 * cousin it is metal, so water does not hurt it ({@link #hurtByWater()} returns false — rain and
 * swimming are harmless; it still throws snowballs at monsters). Drops Patina Bones
 * ({@code loot_table/entities/copper_golemite.json}).
 */
public class CopperGolemiteEntity extends SnowGolemEntity {
	public CopperGolemiteEntity(EntityType<? extends SnowGolemEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean hurtByWater() {
		return false;
	}
}
