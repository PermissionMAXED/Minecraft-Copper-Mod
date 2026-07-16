package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;

/**
 * Ember Rattler - A rattling skeleton with embers glowing between its ribs. Behavior is pure vanilla
 * {@code SkeletonEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 20.0, ATTACK_DAMAGE 2.0, SCALE 0.95). Drops
 * Rattler Ribs ({@code loot_table/entities/ember_rattler.json}).
 */
public class EmberRattlerEntity extends SkeletonEntity {
	public EmberRattlerEntity(EntityType<? extends SkeletonEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code AbstractSkeletonEntity.createAbstractSkeletonAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return AbstractSkeletonEntity.createAbstractSkeletonAttributes()
				.add(EntityAttributes.MAX_HEALTH, 20.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 2.0)
				.add(EntityAttributes.SCALE, 0.95);
	}
}
