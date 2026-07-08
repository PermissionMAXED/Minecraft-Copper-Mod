package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A maroon wolf pup that has clearly been in the Dr.Pepper again. Behavioral tweak: its
 * pepper-hot bite ignites the target for three seconds ({@link #tryAttack} +
 * {@code setOnFireForTicks(60)}). Tameable like any wolf. Drops Pepper Spice
 * ({@code loot_table/entities/pepper_pup.json}).
 */
public class PepperPupEntity extends WolfEntity {
	public PepperPupEntity(EntityType<? extends WolfEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.setOnFireForTicks(60);
		}
		return hit;
	}
}
