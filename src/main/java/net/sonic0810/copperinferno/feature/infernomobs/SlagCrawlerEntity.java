package net.sonic0810.copperinferno.feature.infernomobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.World;

/**
 * An oversized silverfish crusted in slag. Behavior is vanilla silverfish minus the block
 * infestation AI (see {@link #initGoals()}); the stat distinction comes from the default
 * attributes registered in {@link InfernoMobsFeature} (SCALE 1.4, ATTACK_DAMAGE 4). Drops
 * Crawler Fangs ({@code loot_table/entities/slag_crawler.json}).
 */
public class SlagCrawlerEntity extends SilverfishEntity {
	public SlagCrawlerEntity(EntityType<? extends SilverfishEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// Slag crawlers do not infest blocks: drop the two inherited infestation goals that
		// vanilla SilverfishEntity.initGoals adds (bytecode-verified) — CallForHelpGoal
		// (priority 3; pops silverfish out of nearby infested blocks when hurt) and
		// WanderAndInfestGoal (priority 5; also the silverfish's only wander goal — slag
		// crawlers therefore idle in place unless swimming, attacking or taking revenge).
		// Both are PRIVATE inner classes of SilverfishEntity, so instanceof is impossible;
		// they are matched via their declaring class, which is equivalent to matching the
		// class-name strings "...SilverfishEntity$CallForHelpGoal"/"$WanderAndInfestGoal"
		// (the jar contains exactly these two inner classes) but survives remapping.
		// GoalSelector.clear(Predicate<Goal>) tests the wrapped goal, not the
		// PrioritizedGoal (bytecode-verified). SilverfishEntity.damage still calls
		// callForHelpGoal.onHurt() on its own field, but that only bumps a counter; the
		// removed goal never starts.
		this.goalSelector.clear(goal -> goal.getClass().getDeclaringClass() == SilverfishEntity.class);
	}
}
