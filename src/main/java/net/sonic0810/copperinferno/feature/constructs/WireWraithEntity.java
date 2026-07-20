package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A phantom of tangled live wire haunting the Inferno's skies. Behavior is vanilla phantom plus
 * ONE tweak: its swooping touch tags victims with a glowing "short-circuit" mark - see
 * {@link #tryAttack(ServerWorld, Entity)}. Drops Redstone Filaments
 * ({@code loot_table/entities/wire_wraith.json}).
 */
public class WireWraithEntity extends PhantomEntity {
	public WireWraithEntity(EntityType<? extends PhantomEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		// TWEAK: live-wire mark. tryAttack(ServerWorld, Entity) verified via javap (the
		// phantom's swoop AI funnels through it).
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0), this);
		}
		return hit;
	}
}
