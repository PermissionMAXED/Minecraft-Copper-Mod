package net.sonic0810.copperinferno.feature.companions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A hound with embers glowing under its coat, tameable like any wolf. Fire-immune via the
 * {@code EntityType.Builder.makeFireImmune()} flag on its registration (same mechanism as the
 * infernofauna Magma Hopper); its bite sets the target on fire for 3 seconds
 * ({@code tryAttack(ServerWorld, Entity)} — same pattern as the infernofauna Cinder Hound).
 * {@code createChild} is overridden for type consistency because vanilla
 * {@code WolfEntity.createChild} hard-codes {@code EntityType.WOLF}. Drops Bones
 * ({@code loot_table/entities/ember_hound.json}).
 */
public class EmberHoundEntity extends WolfEntity {
	public EmberHoundEntity(EntityType<? extends WolfEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && !target.isFireImmune()) {
			target.setOnFireFor(3.0f);
		}
		return hit;
	}

	@Override
	public EmberHoundEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new EmberHoundEntity(CompanionsFeature.EMBER_HOUND, world);
	}
}
