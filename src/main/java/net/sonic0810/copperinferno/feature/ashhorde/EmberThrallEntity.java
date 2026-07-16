package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Ember Thrall - A shackled thrall bound to the Inferno's forges, still dragging its chains. Behavior is pure vanilla
 * {@code ZombieEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 24.0, ATTACK_DAMAGE 4.0, SCALE 1.0). Drops
 * Thrall Shackles ({@code loot_table/entities/ember_thrall.json}).
 */
public class EmberThrallEntity extends ZombieEntity {
	public EmberThrallEntity(EntityType<? extends ZombieEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code ZombieEntity.createZombieAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return ZombieEntity.createZombieAttributes()
				.add(EntityAttributes.MAX_HEALTH, 24.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 4.0)
				.add(EntityAttributes.SCALE, 1.0);
	}
}
