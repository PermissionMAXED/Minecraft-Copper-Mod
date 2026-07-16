package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Ash Fowl - a pale, dusty fowl blending into the ash drifts of the Cinder Wastes. Behavior is pure vanilla chicken (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces ash fowl babies: vanilla
 * {@code ChickenEntity.createChild} hard-codes {@code EntityType.CHICKEN}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Fowl Giblets ({@code loot_table/entities/ash_fowl.json}).
 */
public class AshFowlEntity extends ChickenEntity {
	public AshFowlEntity(EntityType<? extends ChickenEntity> type, World world) {
		super(type, world);
	}

	@Override
	public AshFowlEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new AshFowlEntity(MoltenFaunaFeature.ASH_FOWL, world);
	}
}
