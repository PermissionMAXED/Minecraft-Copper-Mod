package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Ember Porker - a plump, well-fed porker glowing warmly around the snout. Behavior is pure vanilla pig (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces ember porker babies: vanilla
 * {@code PigEntity.createChild} hard-codes {@code EntityType.PIG}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Porker Ham ({@code loot_table/entities/ember_porker.json}).
 */
public class EmberPorkerEntity extends PigEntity {
	public EmberPorkerEntity(EntityType<? extends PigEntity> type, World world) {
		super(type, world);
	}

	@Override
	public EmberPorkerEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new EmberPorkerEntity(MoltenFaunaFeature.EMBER_PORKER, world);
	}
}
