package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

/**
 * A creeper wound with conductive copper bands. Behavioral tweak: MOVEMENT_SPEED 0.3
 * (vanilla creeper: 0.25) in the default attributes registered by {@link CopperFaunaFeature} —
 * it closes distance noticeably faster. Drops Live Wires
 * ({@code loot_table/entities/conductor_creeper.json}).
 */
public class ConductorCreeperEntity extends CreeperEntity {
	public ConductorCreeperEntity(EntityType<? extends CreeperEntity> type, World world) {
		super(type, world);
	}
}
