package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Soot Swine - a soot-caked swine happily wallowing in cool ash hollows. Behavior is pure vanilla pig (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces soot swine babies: vanilla
 * {@code PigEntity.createChild} hard-codes {@code EntityType.PIG}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Swine Hock ({@code loot_table/entities/soot_swine.json}).
 */
public class SootSwineEntity extends PigEntity {
	public SootSwineEntity(EntityType<? extends PigEntity> type, World world) {
		super(type, world);
	}

	@Override
	public SootSwineEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new SootSwineEntity(MoltenFaunaFeature.SOOT_SWINE, world);
	}
}
