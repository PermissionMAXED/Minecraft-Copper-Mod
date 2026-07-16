package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Magma Ox - a hulking ox with magma-veined hide, unbothered by the heat shimmering off the ground. Behavior is pure vanilla cow (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces magma ox babies: vanilla
 * {@code CowEntity.createChild} hard-codes {@code EntityType.COW}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Ox Loin ({@code loot_table/entities/magma_ox.json}).
 */
public class MagmaOxEntity extends CowEntity {
	public MagmaOxEntity(EntityType<? extends CowEntity> type, World world) {
		super(type, world);
	}

	@Override
	public MagmaOxEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new MagmaOxEntity(MoltenFaunaFeature.MAGMA_OX, world);
	}
}
