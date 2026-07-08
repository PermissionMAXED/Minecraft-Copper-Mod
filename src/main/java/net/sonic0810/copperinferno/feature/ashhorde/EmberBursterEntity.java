package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

/**
 * Ember Burster - A burster wound tight with embers, quicker to pop than most creepers. Behavior is pure vanilla
 * {@code CreeperEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 18.0, ATTACK_DAMAGE 3.0, SCALE 0.95). Drops
 * Burster Powder ({@code loot_table/entities/ember_burster.json}).
 */
public class EmberBursterEntity extends CreeperEntity {
	public EmberBursterEntity(EntityType<? extends CreeperEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code CreeperEntity.createCreeperAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return CreeperEntity.createCreeperAttributes()
				.add(EntityAttributes.MAX_HEALTH, 18.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.SCALE, 0.95);
	}
}
