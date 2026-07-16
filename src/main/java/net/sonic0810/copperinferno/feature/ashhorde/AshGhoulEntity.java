package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Ash Ghoul - A gaunt, grey ghoul caked in ash, quick to claw at anything warm. Behavior is pure vanilla
 * {@code ZombieEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 22.0, ATTACK_DAMAGE 3.5, SCALE 0.98). Drops
 * Ghoul Tatters ({@code loot_table/entities/ash_ghoul.json}).
 */
public class AshGhoulEntity extends ZombieEntity {
	public AshGhoulEntity(EntityType<? extends ZombieEntity> type, World world) {
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
				.add(EntityAttributes.SCALE, 0.98);
	}
}
