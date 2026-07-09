package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.world.World;

/**
 * A frog the exact shade of weathered copper roofs. Behavioral tweak: MAX_HEALTH 16 (vanilla
 * frog: 10) in the default attributes registered by {@link CopperFaunaFeature}. Known
 * limitation: frogs breed via frogspawn (which hatches vanilla tadpoles), so offspring grow up
 * as vanilla frogs — {@code createChild} is intentionally NOT overridden here. Drops
 * Verdigris Gel ({@code loot_table/entities/verdigris_frog.json}).
 */
public class VerdigrisFrogEntity extends FrogEntity {
	public VerdigrisFrogEntity(EntityType<? extends FrogEntity> type, World world) {
		super(type, world);
	}
}
