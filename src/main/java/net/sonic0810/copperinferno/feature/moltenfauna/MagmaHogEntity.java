package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Magma Hog - a stout hog rooting through warm cinder beds for buried embers. Behavior is pure vanilla pig (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces magma hog babies: vanilla
 * {@code PigEntity.createChild} hard-codes {@code EntityType.PIG}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Hog Belly ({@code loot_table/entities/magma_hog.json}).
 */
public class MagmaHogEntity extends PigEntity {
	public MagmaHogEntity(EntityType<? extends PigEntity> type, World world) {
		super(type, world);
	}

	@Override
	public MagmaHogEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new MagmaHogEntity(MoltenFaunaFeature.MAGMA_HOG, world);
	}
}
