package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;

/**
 * Cinder Bowman - A bowman of scorched bone that looses smouldering shots from the ridgelines. Behavior is pure vanilla
 * {@code SkeletonEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 24.0, ATTACK_DAMAGE 3.0, SCALE 1.05). Drops
 * Bowman Strings ({@code loot_table/entities/cinder_bowman.json}).
 */
public class CinderBowmanEntity extends SkeletonEntity {
	public CinderBowmanEntity(EntityType<? extends SkeletonEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code AbstractSkeletonEntity.createAbstractSkeletonAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return AbstractSkeletonEntity.createAbstractSkeletonAttributes()
				.add(EntityAttributes.MAX_HEALTH, 24.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.SCALE, 1.05);
	}
}
