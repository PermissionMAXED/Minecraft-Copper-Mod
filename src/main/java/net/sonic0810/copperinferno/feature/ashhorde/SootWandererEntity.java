package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Soot Wanderer - A shrouded wanderer trudging the soot dunes in endless circles. Behavior is pure vanilla
 * {@code HuskEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 22.0, ATTACK_DAMAGE 3.5, SCALE 1.0). Drops
 * Wanderer Shrouds ({@code loot_table/entities/soot_wanderer.json}).
 */
public class SootWandererEntity extends HuskEntity {
	public SootWandererEntity(EntityType<? extends HuskEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code ZombieEntity.createZombieAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return ZombieEntity.createZombieAttributes()
				.add(EntityAttributes.MAX_HEALTH, 22.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.5)
				.add(EntityAttributes.SCALE, 1.0);
	}
}
