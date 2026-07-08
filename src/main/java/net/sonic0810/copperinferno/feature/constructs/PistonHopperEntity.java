package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

/**
 * A rabbit rebuilt around a piston chassis - and it does NOT like being watched. Behavior is
 * vanilla rabbit plus ONE tweak: it rams players (melee attack + player targeting, in the
 * spirit of the killer bunny) - see {@link #initGoals()}. The ATTACK_DAMAGE attribute the melee
 * AI needs is added in {@code ConstructsFeature.registerAttributes}. Drops Piston Springs
 * ({@code loot_table/entities/piston_hopper.json}).
 */
public class PistonHopperEntity extends RabbitEntity {
	public PistonHopperEntity(EntityType<? extends RabbitEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// TWEAK: piston ram. MeleeAttackGoal(PathAwareEntity, double, boolean) and
		// ActiveTargetGoal(MobEntity, Class, boolean) ctors verified via javap; MobEntity
		// .tryAttack (inherited - RabbitEntity does not override it, javap-verified) deals the
		// damage from the ATTACK_DAMAGE attribute.
		this.goalSelector.add(2, new MeleeAttackGoal(this, 1.4, true));
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}
}
