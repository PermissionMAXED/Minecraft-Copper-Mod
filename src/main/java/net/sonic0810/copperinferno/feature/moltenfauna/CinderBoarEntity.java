package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Cinder Boar - a bristly boar trotting briskly between the Ember Grove's charred trunks. Behavior is pure vanilla pig (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces cinder boar babies: vanilla
 * {@code PigEntity.createChild} hard-codes {@code EntityType.PIG}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Boar Shoulder ({@code loot_table/entities/cinder_boar.json}).
 */
public class CinderBoarEntity extends PigEntity {
	public CinderBoarEntity(EntityType<? extends PigEntity> type, World world) {
		super(type, world);
	}

	@Override
	public CinderBoarEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new CinderBoarEntity(MoltenFaunaFeature.CINDER_BOAR, world);
	}
}
