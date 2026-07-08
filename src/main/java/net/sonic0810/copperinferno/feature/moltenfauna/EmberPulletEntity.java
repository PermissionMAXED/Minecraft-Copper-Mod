package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Ember Pullet - a small young hen glowing faintly like a banked ember. Behavior is pure vanilla chicken (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces ember pullet babies: vanilla
 * {@code ChickenEntity.createChild} hard-codes {@code EntityType.CHICKEN}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Pullet Breast ({@code loot_table/entities/ember_pullet.json}).
 */
public class EmberPulletEntity extends ChickenEntity {
	public EmberPulletEntity(EntityType<? extends ChickenEntity> type, World world) {
		super(type, world);
	}

	@Override
	public EmberPulletEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new EmberPulletEntity(MoltenFaunaFeature.EMBER_PULLET, world);
	}
}
