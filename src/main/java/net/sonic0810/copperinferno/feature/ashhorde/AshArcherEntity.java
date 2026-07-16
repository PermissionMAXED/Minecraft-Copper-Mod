package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;

/**
 * Ash Archer - An ash-bleached skeleton archer whose arrows whistle through the cinder haze. Behavior is pure vanilla
 * {@code SkeletonEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 22.0, ATTACK_DAMAGE 2.5, SCALE 1.0). Drops
 * Archer Arrowheads ({@code loot_table/entities/ash_archer.json}).
 */
public class AshArcherEntity extends SkeletonEntity {
	public AshArcherEntity(EntityType<? extends SkeletonEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code AbstractSkeletonEntity.createAbstractSkeletonAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return AbstractSkeletonEntity.createAbstractSkeletonAttributes()
				.add(EntityAttributes.MAX_HEALTH, 22.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 2.5)
				.add(EntityAttributes.SCALE, 1.0);
	}
}
