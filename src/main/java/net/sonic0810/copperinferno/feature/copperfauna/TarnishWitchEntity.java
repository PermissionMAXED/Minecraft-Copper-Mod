package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.world.World;

/**
 * A witch steeped in tarnish fumes. Behavioral tweak: MAX_HEALTH 36 (vanilla witch: 26) in
 * the default attributes registered by {@link CopperFaunaFeature}. Drops Tarnish Dust
 * ({@code loot_table/entities/tarnish_witch.json}).
 */
public class TarnishWitchEntity extends WitchEntity {
	public TarnishWitchEntity(EntityType<? extends WitchEntity> type, World world) {
		super(type, world);
	}
}
