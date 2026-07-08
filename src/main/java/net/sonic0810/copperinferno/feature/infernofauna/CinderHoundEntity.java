package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A wolf whose fangs still smolder. Tweak: its bite sets the target on fire for 4 seconds
 * ({@code tryAttack(ServerWorld, Entity)} verified via javap on LivingEntity). Taming/breeding
 * are inherited; {@code createChild} is overridden only for type consistency because vanilla
 * {@code WolfEntity.createChild} hard-codes {@code EntityType.WOLF} (same fix as the
 * infernomobs Cinder Strider). Drops Singed Fangs
 * ({@code loot_table/entities/cinder_hound.json}).
 */
public class CinderHoundEntity extends WolfEntity {
	public CinderHoundEntity(EntityType<? extends WolfEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && !target.isFireImmune()) {
			target.setOnFireFor(4.0f);
		}
		return hit;
	}

	@Override
	public CinderHoundEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new CinderHoundEntity(InfernoFaunaFeature.CINDER_HOUND, world);
	}
}
