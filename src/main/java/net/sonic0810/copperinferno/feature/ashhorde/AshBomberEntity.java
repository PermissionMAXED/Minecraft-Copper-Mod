package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

/**
 * Ash Bomber - A pale bomber that detonates into a blinding cloud of ash. Behavior is pure vanilla
 * {@code CreeperEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 20.0, ATTACK_DAMAGE 3.0, SCALE 1.0). Drops
 * Bomber Fuses ({@code loot_table/entities/ash_bomber.json}).
 */
public class AshBomberEntity extends CreeperEntity {
	public AshBomberEntity(EntityType<? extends CreeperEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code CreeperEntity.createCreeperAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return CreeperEntity.createCreeperAttributes()
				.add(EntityAttributes.MAX_HEALTH, 20.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.SCALE, 1.0);
	}
}
