package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Ember Grazer - a placid bovine that crops smoldering tufts across the Cinder Wastes, Ember Grove and Slag Sea. Behavior is pure vanilla cow (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces ember grazer babies: vanilla
 * {@code CowEntity.createChild} hard-codes {@code EntityType.COW}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Grazer Brisket ({@code loot_table/entities/ember_grazer.json}).
 */
public class EmberGrazerEntity extends CowEntity {
	public EmberGrazerEntity(EntityType<? extends CowEntity> type, World world) {
		super(type, world);
	}

	@Override
	public EmberGrazerEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new EmberGrazerEntity(MoltenFaunaFeature.EMBER_GRAZER, world);
	}
}
