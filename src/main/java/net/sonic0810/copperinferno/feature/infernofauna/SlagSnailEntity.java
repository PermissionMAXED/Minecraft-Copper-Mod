package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.World;

/**
 * A slow slag-shelled snail. Tweak: it does not infest blocks — the two inherited infestation
 * goals that vanilla {@code SilverfishEntity.initGoals} adds (CallForHelpGoal and
 * WanderAndInfestGoal, both PRIVATE inner classes of SilverfishEntity) are dropped by matching
 * their declaring class, exactly like the proven infernomobs Slag Crawler. Without
 * WanderAndInfestGoal (the silverfish's only wander goal) the snail idles in place unless
 * swimming, attacking or taking revenge — fittingly snail-like. Drops Slag Shells
 * ({@code loot_table/entities/slag_snail.json}).
 */
public class SlagSnailEntity extends SilverfishEntity {
	public SlagSnailEntity(EntityType<? extends SilverfishEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// GoalSelector.clear(Predicate<Goal>) tests the wrapped goal, not the PrioritizedGoal
		// (bytecode-verified in the infernomobs work package).
		this.goalSelector.clear(goal -> goal.getClass().getDeclaringClass() == SilverfishEntity.class);
	}
}
