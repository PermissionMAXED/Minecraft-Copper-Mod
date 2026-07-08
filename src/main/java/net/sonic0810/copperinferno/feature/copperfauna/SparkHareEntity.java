package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A rabbit that crackles with static. Behavioral tweak: MOVEMENT_SPEED is raised to 0.45
 * (vanilla rabbit: 0.3) in the default attributes registered by {@link CopperFaunaFeature} —
 * it is nearly impossible to chase down. {@code createChild} is overridden only for type
 * consistency (vanilla hard-codes {@code EntityType.RABBIT}; same fix as the infernomobs
 * Cinder Strider). Drops Spark Tufts
 * ({@code loot_table/entities/spark_hare.json}).
 */
public class SparkHareEntity extends RabbitEntity {
	public SparkHareEntity(EntityType<? extends RabbitEntity> type, World world) {
		super(type, world);
	}

	@Override
	public SparkHareEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new SparkHareEntity(CopperFaunaFeature.SPARK_HARE, world);
	}
}
