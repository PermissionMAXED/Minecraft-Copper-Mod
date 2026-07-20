package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A serpentine cave spider glowing along its segments. Tweak: on top of the inherited cave
 * spider poison, its bite ignites the victim for 5 seconds — poison AND fire make it the
 * Inferno's nastiest small predator. Drops spider eyes
 * ({@code loot_table/entities/ember_serpent.json}).
 */
public class EmberSerpentEntity extends CaveSpiderEntity {
	public EmberSerpentEntity(EntityType<? extends CaveSpiderEntity> type, World world) {
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
