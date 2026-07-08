package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Slag Husk - A husk armoured in a cracked crust of cooled slag. Behavior is pure vanilla
 * {@code HuskEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 24.0, ATTACK_DAMAGE 4.0, SCALE 1.0). Drops
 * Husk Crusts ({@code loot_table/entities/slag_husk.json}).
 */
public class SlagHuskEntity extends HuskEntity {
	public SlagHuskEntity(EntityType<? extends HuskEntity> type, World world) {
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
