package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.world.World;

/**
 * A pillager that tends the Inferno's forges and never abandons its post. Behavior is vanilla
 * pillager (crossbow AI included) plus ONE tweak: it never despawns, however far away the
 * players roam - see {@link #canImmediatelyDespawn(double)}. Drops Forge Bellows
 * ({@code loot_table/entities/forge_keeper.json}).
 */
public class ForgeKeeperEntity extends PillagerEntity {
	public ForgeKeeperEntity(EntityType<? extends PillagerEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean canImmediatelyDespawn(double distanceSquared) {
		// TWEAK: eternal watch. MobEntity.canImmediatelyDespawn(double) verified via javap.
		return false;
	}
}
