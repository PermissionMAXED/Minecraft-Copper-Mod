package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.world.World;

/**
 * A raven-dark parrot hatched in pyre smoke. Tweak: its feathers are fireproof —
 * {@code isFireImmune()} (public on Entity, verified via javap) returns true, so it shrugs off
 * the Inferno's flames and lava splashes. Taming/shoulder-perching are inherited. Drops Pyre
 * Feathers ({@code loot_table/entities/pyre_raven.json}).
 */
public class PyreRavenEntity extends ParrotEntity {
	public PyreRavenEntity(EntityType<? extends ParrotEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean isFireImmune() {
		return true;
	}
}
