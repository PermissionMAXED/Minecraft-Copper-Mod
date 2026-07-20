package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.world.World;

/**
 * A key-lime frog the size of a dessert plate. Behavioral tweak: SCALE 1.4 (visibly bigger
 * hitbox and model than the vanilla frog) in the default attributes registered by
 * {@link SodaFaunaFeature}. Known limitation: frogs breed via frogspawn (which hatches vanilla
 * tadpoles), so offspring grow up as vanilla frogs — {@code createChild} is intentionally NOT
 * overridden here. Drops Sprite Essence
 * ({@code loot_table/entities/lime_frog.json}).
 */
public class LimeFrogEntity extends FrogEntity {
	public LimeFrogEntity(EntityType<? extends FrogEntity> type, World world) {
		super(type, world);
	}
}
