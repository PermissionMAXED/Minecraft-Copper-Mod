package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;

/**
 * A spider rebuilt on whirring copper gears. Behavior is vanilla spider plus ONE tweak: its
 * geared legs cannot be slowed - see {@link #canHaveStatusEffect(StatusEffectInstance)}. Drops
 * Copper Gearwheels ({@code loot_table/entities/gear_spider.json}).
 */
public class GearSpiderEntity extends SpiderEntity {
	public GearSpiderEntity(EntityType<? extends SpiderEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean canHaveStatusEffect(StatusEffectInstance effect) {
		// TWEAK: unslowable clockwork. LivingEntity.canHaveStatusEffect(StatusEffectInstance)
		// verified via javap (vanilla spiders use the same hook for poison immunity).
		if (effect.getEffectType() == StatusEffects.SLOWNESS) {
			return false;
		}
		return super.canHaveStatusEffect(effect);
	}
}
