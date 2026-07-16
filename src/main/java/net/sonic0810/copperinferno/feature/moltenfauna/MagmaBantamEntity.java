package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Magma Bantam - a tiny, feisty bantam with magma-orange speckles, always underfoot. Behavior is pure vanilla chicken (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces magma bantam babies: vanilla
 * {@code ChickenEntity.createChild} hard-codes {@code EntityType.CHICKEN}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Bantam Thigh ({@code loot_table/entities/magma_bantam.json}).
 */
public class MagmaBantamEntity extends ChickenEntity {
	public MagmaBantamEntity(EntityType<? extends ChickenEntity> type, World world) {
		super(type, world);
	}

	@Override
	public MagmaBantamEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new MagmaBantamEntity(MoltenFaunaFeature.MAGMA_BANTAM, world);
	}
}
