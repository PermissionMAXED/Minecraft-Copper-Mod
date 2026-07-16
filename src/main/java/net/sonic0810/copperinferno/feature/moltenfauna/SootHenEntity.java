package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Soot Hen - a soot-grey hen scratching through warm ash for embers and grubs. Behavior is pure vanilla chicken (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces soot hen babies: vanilla
 * {@code ChickenEntity.createChild} hard-codes {@code EntityType.CHICKEN}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Hen Drumstick ({@code loot_table/entities/soot_hen.json}).
 */
public class SootHenEntity extends ChickenEntity {
	public SootHenEntity(EntityType<? extends ChickenEntity> type, World world) {
		super(type, world);
	}

	@Override
	public SootHenEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new SootHenEntity(MoltenFaunaFeature.SOOT_HEN, world);
	}
}
