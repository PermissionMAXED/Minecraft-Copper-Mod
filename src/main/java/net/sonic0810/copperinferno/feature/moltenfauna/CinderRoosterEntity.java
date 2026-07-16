package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Cinder Rooster - a strutting rooster with cinder-red plumage, quick to dart across the wastes. Behavior is pure vanilla chicken (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces cinder rooster babies: vanilla
 * {@code ChickenEntity.createChild} hard-codes {@code EntityType.CHICKEN}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Rooster Wing ({@code loot_table/entities/cinder_rooster.json}).
 */
public class CinderRoosterEntity extends ChickenEntity {
	public CinderRoosterEntity(EntityType<? extends ChickenEntity> type, World world) {
		super(type, world);
	}

	@Override
	public CinderRoosterEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new CinderRoosterEntity(MoltenFaunaFeature.CINDER_ROOSTER, world);
	}
}
