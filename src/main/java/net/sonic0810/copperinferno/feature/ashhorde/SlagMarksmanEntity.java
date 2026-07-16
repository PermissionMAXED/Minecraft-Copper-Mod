package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;

/**
 * Slag Marksman - A slag-plated marksman, taller and steadier than a common skeleton. Behavior is pure vanilla
 * {@code SkeletonEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 26.0, ATTACK_DAMAGE 3.0, SCALE 1.1). Drops
 * Marksman Bones ({@code loot_table/entities/slag_marksman.json}).
 */
public class SlagMarksmanEntity extends SkeletonEntity {
	public SlagMarksmanEntity(EntityType<? extends SkeletonEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code AbstractSkeletonEntity.createAbstractSkeletonAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return AbstractSkeletonEntity.createAbstractSkeletonAttributes()
				.add(EntityAttributes.MAX_HEALTH, 26.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.SCALE, 1.1);
	}
}
