package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Ember Scorchling - A shrivelled scorchling that sizzles as it lunges. Behavior is pure vanilla
 * {@code HuskEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 18.0, ATTACK_DAMAGE 3.5, SCALE 0.85). Drops
 * Scorchling Char ({@code loot_table/entities/ember_scorchling.json}).
 */
public class EmberScorchlingEntity extends HuskEntity {
	public EmberScorchlingEntity(EntityType<? extends HuskEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code ZombieEntity.createZombieAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return ZombieEntity.createZombieAttributes()
				.add(EntityAttributes.MAX_HEALTH, 18.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.5)
				.add(EntityAttributes.SCALE, 0.85);
	}
}
