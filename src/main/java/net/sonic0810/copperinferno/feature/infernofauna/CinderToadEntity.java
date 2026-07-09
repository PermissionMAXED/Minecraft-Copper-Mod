package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A squat toad crusted in cinders. Tweak: every 3 seconds stray sparks flicker off its back —
 * FLAME particles via {@code ServerWorld.spawnParticles} (distinct from the Fumarole Frog's
 * smoke plume). Frog AI is inherited; bred offspring lay vanilla frogspawn (the tadpole
 * pipeline hard-codes vanilla types), accepted and documented here. Drops slime balls
 * ({@code loot_table/entities/cinder_toad.json}).
 */
public class CinderToadEntity extends FrogEntity {
	public CinderToadEntity(EntityType<? extends FrogEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		if (this.getEntityWorld() instanceof ServerWorld serverWorld && this.age % 60 == 0) {
			serverWorld.spawnParticles(ParticleTypes.FLAME,
					this.getX(), this.getY() + 0.3, this.getZ(), 3, 0.15, 0.1, 0.15, 0.005);
		}
	}
}
