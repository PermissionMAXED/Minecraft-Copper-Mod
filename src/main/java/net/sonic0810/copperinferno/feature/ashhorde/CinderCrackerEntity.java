package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

/**
 * Cinder Cracker - A small cracker that goes off with a sharp, glowing snap. Behavior is pure vanilla
 * {@code CreeperEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 16.0, ATTACK_DAMAGE 3.0, SCALE 0.85). Drops
 * Cracker Shards ({@code loot_table/entities/cinder_cracker.json}).
 */
public class CinderCrackerEntity extends CreeperEntity {
	public CinderCrackerEntity(EntityType<? extends CreeperEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code CreeperEntity.createCreeperAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return CreeperEntity.createCreeperAttributes()
				.add(EntityAttributes.MAX_HEALTH, 16.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.SCALE, 0.85);
	}
}
