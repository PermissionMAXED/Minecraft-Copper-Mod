package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;

/**
 * Ember Lurker - A big spider with ember-lit eyes, lurking in the glow of lava pools. Behavior is pure vanilla
 * {@code SpiderEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 20.0, ATTACK_DAMAGE 3.0, SCALE 1.1). Drops
 * Lurker Eyes ({@code loot_table/entities/ember_lurker.json}).
 */
public class EmberLurkerEntity extends SpiderEntity {
	public EmberLurkerEntity(EntityType<? extends SpiderEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code SpiderEntity.createSpiderAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return SpiderEntity.createSpiderAttributes()
				.add(EntityAttributes.MAX_HEALTH, 20.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.SCALE, 1.1);
	}
}
