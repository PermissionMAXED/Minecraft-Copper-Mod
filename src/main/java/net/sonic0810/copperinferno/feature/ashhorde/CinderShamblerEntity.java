package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Cinder Shambler - A cinder-crusted zombie that shambles through drifting embers, hitting harder than its overworld kin. Behavior is pure vanilla
 * {@code ZombieEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 26.0, ATTACK_DAMAGE 4.0, SCALE 1.05). Drops
 * Shambler Rags ({@code loot_table/entities/cinder_shambler.json}).
 */
public class CinderShamblerEntity extends ZombieEntity {
	public CinderShamblerEntity(EntityType<? extends ZombieEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code ZombieEntity.createZombieAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return ZombieEntity.createZombieAttributes()
				.add(EntityAttributes.MAX_HEALTH, 26.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 4.0)
				.add(EntityAttributes.SCALE, 1.05);
	}
}
