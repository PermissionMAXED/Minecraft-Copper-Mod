package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A hulking golem fused from pyroclastic rock. Tweak: its fists are furnace-hot — the vanilla
 * fling-attack additionally sets the victim on fire for 5 seconds
 * ({@code tryAttack(ServerWorld, Entity)} verified via javap on IronGolemEntity). It is wild:
 * no village bookkeeping is attached, it simply guards its patch of the Inferno. Drops
 * Pyroclast Cores ({@code loot_table/entities/pyroclast_golem.json}).
 */
public class PyroclastGolemEntity extends IronGolemEntity {
	public PyroclastGolemEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && !target.isFireImmune()) {
			target.setOnFireFor(5.0f);
		}
		return hit;
	}
}
