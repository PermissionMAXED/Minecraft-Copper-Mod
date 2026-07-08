package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A walking blast furnace, its grate white-hot. Behavior is vanilla iron golem plus ONE tweak:
 * anyone who strikes it is scorched by the open firebox (thorns-like burn) - see
 * {@link #damage(ServerWorld, DamageSource, float)}. Drops Sentinel Plating
 * ({@code loot_table/entities/furnace_golem.json}).
 */
public class FurnaceGolemEntity extends IronGolemEntity {
	public FurnaceGolemEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean damage(ServerWorld world, DamageSource source, float amount) {
		// TWEAK: white-hot casing. damage(ServerWorld, DamageSource, float),
		// DamageSource.getAttacker()/isDirect() verified via javap; only direct (melee-range)
		// attackers get burned, not distant shooters.
		boolean damaged = super.damage(world, source, amount);
		if (damaged && source.isDirect() && source.getAttacker() instanceof Entity attacker
				&& !attacker.isFireImmune()) {
			attacker.setOnFireFor(3.0f);
		}
		return damaged;
	}
}
