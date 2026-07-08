package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A husk bolted into scavenged boiler plate. Behavior is vanilla husk (hunger bite included)
 * plus ONE tweak: the plating deflects projectiles for half damage - see
 * {@link #damage(ServerWorld, DamageSource, float)}. Drops Rivet Bolts
 * ({@code loot_table/entities/plated_husk.json}).
 */
public class PlatedHuskEntity extends HuskEntity {
	public PlatedHuskEntity(EntityType<? extends HuskEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean damage(ServerWorld world, DamageSource source, float amount) {
		// TWEAK: arrow-shedding plate. DamageSource.isIn(TagKey) + DamageTypeTags
		// .IS_PROJECTILE verified via javap.
		if (source.isIn(DamageTypeTags.IS_PROJECTILE)) {
			amount *= 0.5f;
		}
		return super.damage(world, source, amount);
	}
}
