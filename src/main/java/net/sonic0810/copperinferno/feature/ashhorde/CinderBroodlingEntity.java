package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;

/**
 * Cinder Broodling - A tiny broodling that swarms from cracked cinder nests. Behavior is pure vanilla
 * {@code SpiderEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 12.0, ATTACK_DAMAGE 2.0, SCALE 0.7). Drops
 * Broodling Fangs ({@code loot_table/entities/cinder_broodling.json}).
 */
public class CinderBroodlingEntity extends SpiderEntity {
	public CinderBroodlingEntity(EntityType<? extends SpiderEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code SpiderEntity.createSpiderAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return SpiderEntity.createSpiderAttributes()
				.add(EntityAttributes.MAX_HEALTH, 12.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 2.0)
				.add(EntityAttributes.SCALE, 0.7);
	}
}
