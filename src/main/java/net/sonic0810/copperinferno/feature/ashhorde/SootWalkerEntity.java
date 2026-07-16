package net.sonic0810.copperinferno.feature.ashhorde;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

/**
 * Soot Walker - A soot-black walker that leaves smudged footprints wherever it prowls. Behavior is pure vanilla
 * {@code ZombieEntity}; the stat distinction comes from {@link #createAttributes()}
 * (MAX_HEALTH 20.0, ATTACK_DAMAGE 3.0, SCALE 0.95). Drops
 * Walker Grime ({@code loot_table/entities/soot_walker.json}).
 */
public class SootWalkerEntity extends ZombieEntity {
	public SootWalkerEntity(EntityType<? extends ZombieEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code ZombieEntity.createZombieAttributes()} with tuned values;
	 * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value
	 * (same proven pattern as InfernoMobsFeature).
	 */
	public static DefaultAttributeContainer.Builder createAttributes() {
		return ZombieEntity.createZombieAttributes()
				.add(EntityAttributes.MAX_HEALTH, 20.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.SCALE, 0.95);
	}
}
