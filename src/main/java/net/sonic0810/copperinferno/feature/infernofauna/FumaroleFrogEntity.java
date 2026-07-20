package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A frog that squats on fumaroles and vents steam. Tweak: every 2 seconds it puffs a little
 * column of campfire smoke ({@code ServerWorld.spawnParticles(T, x, y, z, count, dx, dy, dz,
 * speed)} verified via javap). Brain-driven frog AI (long jumps, tongue attacks) is inherited;
 * bred offspring lay vanilla frogspawn (the tadpole pipeline hard-codes vanilla types), which
 * is accepted and documented here. Drops Fumarole Glands
 * ({@code loot_table/entities/fumarole_frog.json}).
 */
public class FumaroleFrogEntity extends FrogEntity {
	public FumaroleFrogEntity(EntityType<? extends FrogEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		if (this.getEntityWorld() instanceof ServerWorld serverWorld && this.age % 40 == 0) {
			serverWorld.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
					this.getX(), this.getY() + 0.4, this.getZ(), 2, 0.1, 0.1, 0.1, 0.01);
		}
	}
}
