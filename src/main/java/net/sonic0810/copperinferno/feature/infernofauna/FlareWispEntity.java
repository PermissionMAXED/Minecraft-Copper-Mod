package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A free-roaming wisp of living flame (a vex with no evoker master). Tweak: striking it burns —
 * whoever damages it is set on fire for 3 seconds
 * ({@code damage(ServerWorld, DamageSource, float)} and {@code DamageSource.getAttacker()}
 * verified via javap). Naturally spawned vexes have no life-tick bound, so wisps do not decay.
 * Drops Flare Essence ({@code loot_table/entities/flare_wisp.json}).
 */
public class FlareWispEntity extends VexEntity {
	public FlareWispEntity(EntityType<? extends VexEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean damage(ServerWorld world, DamageSource source, float amount) {
		boolean damaged = super.damage(world, source, amount);
		if (damaged && source.getAttacker() instanceof LivingEntity attacker && !attacker.isFireImmune()) {
			attacker.setOnFireFor(3.0f);
		}
		return damaged;
	}
}
