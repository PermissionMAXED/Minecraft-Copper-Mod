package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Slag Rotter - A bloated rotter oozing molten slag, slow but brutally strong. Behavior is pure vanilla
 * {@code ZombieEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 30.0, ATTACK_DAMAGE 4.5, SCALE 1.1). Drops
 * Rotter Sludge ({@code loot_table/entities/slag_rotter.json}).
 */
public class SlagRotterEntity extends ZombieEntity {
	public SlagRotterEntity(EntityType<? extends ZombieEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code ZombieEntity.createZombieAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return ZombieEntity.createZombieAttributes()
				.add(EntityAttributes.MAX_HEALTH, 30.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 4.5)
				.add(EntityAttributes.SCALE, 1.1);
	}
}
