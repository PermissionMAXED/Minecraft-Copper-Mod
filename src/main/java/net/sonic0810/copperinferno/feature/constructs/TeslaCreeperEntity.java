package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A creeper wound around an overcharged tesla capacitor. Behavior is vanilla creeper plus ONE
 * tweak: ANY hit destabilizes the capacitor and primes the fuse, flint-and-steel style - see
 * {@link #damage(ServerWorld, DamageSource, float)}. Drops Redstone Filaments
 * ({@code loot_table/entities/tesla_creeper.json}).
 */
public class TeslaCreeperEntity extends CreeperEntity {
	public TeslaCreeperEntity(EntityType<? extends CreeperEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean damage(ServerWorld world, DamageSource source, float amount) {
		// TWEAK: unstable capacitor. CreeperEntity.ignite() is public (verified via javap)
		// and starts the vanilla flint-and-steel fuse.
		boolean damaged = super.damage(world, source, amount);
		if (damaged && this.isAlive() && !this.isIgnited()) {
			this.ignite();
		}
		return damaged;
	}
}
