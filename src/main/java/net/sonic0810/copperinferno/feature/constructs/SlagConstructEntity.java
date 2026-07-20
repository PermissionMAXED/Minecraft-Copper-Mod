package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A golem welded together from still-glowing slag, native to the Inferno. Behavior is vanilla
 * iron golem plus ONE tweak: its punches set the victim on fire - see
 * {@link #tryAttack(ServerWorld, Entity)}. Drops Slag Grit
 * ({@code loot_table/entities/slag_construct.json}).
 */
public class SlagConstructEntity extends IronGolemEntity {
	public SlagConstructEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		// TWEAK: molten fists. tryAttack(ServerWorld, Entity) + Entity.setOnFireFor(float
		// seconds) verified via javap.
		boolean hit = super.tryAttack(world, target);
		if (hit) {
			target.setOnFireFor(4.0f);
		}
		return hit;
	}
}
