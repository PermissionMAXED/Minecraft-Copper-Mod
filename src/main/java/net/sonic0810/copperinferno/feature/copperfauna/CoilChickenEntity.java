package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A chicken with copper-coil tail feathers. Behavioral tweak: MOVEMENT_SPEED 0.3 (vanilla
 * chicken: 0.25) in the default attributes registered by {@link CopperFaunaFeature}.
 * {@code createChild} is overridden only for type consistency (vanilla hard-codes
 * {@code EntityType.CHICKEN}; same fix as the infernomobs Cinder Strider). Drops
 * Gilded Feathers ({@code loot_table/entities/coil_chicken.json}).
 */
public class CoilChickenEntity extends ChickenEntity {
	public CoilChickenEntity(EntityType<? extends ChickenEntity> type, World world) {
		super(type, world);
	}

	@Override
	public CoilChickenEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new CoilChickenEntity(CopperFaunaFeature.COIL_CHICKEN, world);
	}
}
