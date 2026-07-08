package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.world.World;

/**
 * A fox with a coat like hot copper filings. Behavioral tweak: MOVEMENT_SPEED 0.4 (vanilla
 * fox: 0.3) in the default attributes registered by {@link CopperFaunaFeature}. Drops Spark
 * Tufts ({@code loot_table/entities/spark_fox.json}).
 */
public class SparkFoxEntity extends FoxEntity {
	public SparkFoxEntity(EntityType<? extends FoxEntity> type, World world) {
		super(type, world);
	}
}
