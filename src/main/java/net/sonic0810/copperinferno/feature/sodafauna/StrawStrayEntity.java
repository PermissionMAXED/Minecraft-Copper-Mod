package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.world.World;

/**
 * A stray built out of striped paper drinking straws. Behavioral tweak: MOVEMENT_SPEED 0.3
 * (a vanilla stray walks at 0.25 — it is light as paper) in the default attributes registered
 * by {@link SodaFaunaFeature}. Keeps the vanilla stray's slowness arrows. Drops Zero Syrup
 * ({@code loot_table/entities/straw_stray.json}).
 */
public class StrawStrayEntity extends StrayEntity {
	public StrawStrayEntity(EntityType<? extends StrayEntity> type, World world) {
		super(type, world);
	}
}
