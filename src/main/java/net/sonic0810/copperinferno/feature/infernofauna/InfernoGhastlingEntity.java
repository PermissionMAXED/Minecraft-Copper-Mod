package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.world.World;

/**
 * A ghast hatchling that never grew up. Tweak: it is a runt — the default attributes registered
 * in {@link InfernoFaunaFeature} pin SCALE to 0.35 and MAX_HEALTH to 6 (the attribute-tweak
 * pattern proven by the infernomobs Ember Wraith), so it is a cat-sized, two-hit ghast whose
 * fireballs are far easier to dodge at its size. Fireball AI is inherited unchanged. Drops
 * ghast tears ({@code loot_table/entities/inferno_ghastling.json}).
 */
public class InfernoGhastlingEntity extends GhastEntity {
	public InfernoGhastlingEntity(EntityType<? extends GhastEntity> type, World world) {
		super(type, world);
	}
}
