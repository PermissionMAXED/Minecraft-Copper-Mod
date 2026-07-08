package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Ash Mummy - A towering mummy wound in ash-grey wrappings, dry as old bone. Behavior is pure vanilla
 * {@code HuskEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 28.0, ATTACK_DAMAGE 4.5, SCALE 1.1). Drops
 * Mummy Wraps ({@code loot_table/entities/ash_mummy.json}).
 */
public class AshMummyEntity extends HuskEntity {
	public AshMummyEntity(EntityType<? extends HuskEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code ZombieEntity.createZombieAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return ZombieEntity.createZombieAttributes()
				.add(EntityAttributes.MAX_HEALTH, 28.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 4.5)
				.add(EntityAttributes.SCALE, 1.1);
	}
}
