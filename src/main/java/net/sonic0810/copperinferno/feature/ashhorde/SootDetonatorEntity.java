package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

/**
 * Soot Detonator - A heavy detonator that stalks silently under cover of soot. Behavior is pure vanilla
 * {@code CreeperEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 22.0, ATTACK_DAMAGE 3.0, SCALE 1.0). Drops
 * Detonator Charges ({@code loot_table/entities/soot_detonator.json}).
 */
public class SootDetonatorEntity extends CreeperEntity {
	public SootDetonatorEntity(EntityType<? extends CreeperEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code CreeperEntity.createCreeperAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return CreeperEntity.createCreeperAttributes()
				.add(EntityAttributes.MAX_HEALTH, 22.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.SCALE, 1.0);
	}
}
