package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;

/**
 * Soot Skirmisher - A small, fast skirmisher that harries travellers in sooty packs. Behavior is pure vanilla
 * {@code SkeletonEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 18.0, ATTACK_DAMAGE 2.5, SCALE 0.9). Drops
 * Skirmisher Quivers ({@code loot_table/entities/soot_skirmisher.json}).
 */
public class SootSkirmisherEntity extends SkeletonEntity {
	public SootSkirmisherEntity(EntityType<? extends SkeletonEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code AbstractSkeletonEntity.createAbstractSkeletonAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return AbstractSkeletonEntity.createAbstractSkeletonAttributes()
				.add(EntityAttributes.MAX_HEALTH, 18.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 2.5)
				.add(EntityAttributes.SCALE, 0.9);
	}
}
