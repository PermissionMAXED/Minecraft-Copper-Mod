package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;

/**
 * Ash Weaver - A weaver spinning grey, ash-dusted webs across the Ember Grove canopy. Behavior is pure vanilla
 * {@code SpiderEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 16.0, ATTACK_DAMAGE 2.5, SCALE 1.0). Drops
 * Weaver Silk ({@code loot_table/entities/ash_weaver.json}).
 */
public class AshWeaverEntity extends SpiderEntity {
	public AshWeaverEntity(EntityType<? extends SpiderEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code SpiderEntity.createSpiderAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return SpiderEntity.createSpiderAttributes()
				.add(EntityAttributes.MAX_HEALTH, 16.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 2.5)
				.add(EntityAttributes.SCALE, 1.0);
	}
}
