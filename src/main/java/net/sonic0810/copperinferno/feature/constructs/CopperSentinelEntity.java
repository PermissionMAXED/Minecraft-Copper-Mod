package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

/**
 * A rogue copper-plated golem patrolling the Overworld night. Behavior is vanilla iron golem
 * plus ONE tweak: it actively hunts players (vanilla golems only retaliate when angered) - see
 * {@link #initGoals()}. Drops Sentinel Plating
 * ({@code loot_table/entities/copper_sentinel.json}).
 */
public class CopperSentinelEntity extends IronGolemEntity {
	public CopperSentinelEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// TWEAK: hostile sentinel. ActiveTargetGoal(MobEntity, Class, checkVisibility) ctor
		// verified via javap; vanilla IronGolemEntity already has the melee attack goal, so
		// adding a player target goal is all that is needed to make it hostile.
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}
}
