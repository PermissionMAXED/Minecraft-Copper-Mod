package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Slag Sow - a round sow dozing beside slag pools, unhurried by anything. Behavior is pure vanilla pig (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces slag sow babies: vanilla
 * {@code PigEntity.createChild} hard-codes {@code EntityType.PIG}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Sow Jowl ({@code loot_table/entities/slag_sow.json}).
 */
public class SlagSowEntity extends PigEntity {
	public SlagSowEntity(EntityType<? extends PigEntity> type, World world) {
		super(type, world);
	}

	@Override
	public SlagSowEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new SlagSowEntity(MoltenFaunaFeature.SLAG_SOW, world);
	}
}
