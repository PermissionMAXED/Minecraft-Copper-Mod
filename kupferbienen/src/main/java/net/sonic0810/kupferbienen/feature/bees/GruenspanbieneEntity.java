package net.sonic0810.kupferbienen.feature.bees;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Verdigris bee, the oxidized cave-dwelling cousin of the Kupferbiene. Near-pure
 * {@link BeeEntity} subclass (see {@code KupferbieneEntity}); its presence near a Kupferstock
 * gives the apiary a 20% chance to produce Gruenspanpollen instead of a Kupferwabe (see
 * {@code KupferstockBlockEntity#serverTick}). Only {@link #createChild} is overridden so
 * breeding yields a Gruenspanbiene instead of a vanilla bee.
 */
public class GruenspanbieneEntity extends BeeEntity {
	public GruenspanbieneEntity(EntityType<? extends BeeEntity> type, World world) {
		super(type, world);
	}

	@Override
	public GruenspanbieneEntity createChild(ServerWorld world, PassiveEntity entity) {
		return BeesFeature.GRUENSPANBIENE.create(world, SpawnReason.BREEDING);
	}
}
