package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.world.World;

/**
 * A witch who swapped her cauldron for a soda fountain. Behavioral tweak: MOVEMENT_SPEED 0.3
 * (a vanilla witch walks at 0.25 — pure caffeine) in the default attributes registered by
 * {@link SodaFaunaFeature}. Drops Pepper Spice ({@code loot_table/entities/soda_witch.json}).
 */
public class SodaWitchEntity extends WitchEntity {
	public SodaWitchEntity(EntityType<? extends WitchEntity> type, World world) {
		super(type, world);
	}
}
