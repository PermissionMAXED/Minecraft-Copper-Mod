package net.sonic0810.kupferbienen.feature.bees;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;

/**
 * Verdigris bee, the oxidized cave-dwelling cousin of the Kupferbiene. Pure {@link BeeEntity}
 * subclass (see {@code KupferbieneEntity}); its presence near a Kupferstock gives the apiary a
 * 20% chance to produce Gruenspanpollen instead of a Kupferwabe (see
 * {@code KupferstockBlockEntity#serverTick}).
 */
public class GruenspanbieneEntity extends BeeEntity {
	public GruenspanbieneEntity(EntityType<? extends BeeEntity> type, World world) {
		super(type, world);
	}
}
