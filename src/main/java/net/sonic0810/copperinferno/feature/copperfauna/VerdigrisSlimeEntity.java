package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;

/**
 * A slime the color of weathered copper. Behavioral tweak: it is itself immune to Poison — it IS
 * verdigris. Size handling (random size on natural spawn, split on death) is inherited from
 * {@link SlimeEntity}; like vanilla slimes, only the smallest size drops loot
 * ({@code loot_table/entities/verdigris_slime.json}).
 */
public class VerdigrisSlimeEntity extends SlimeEntity {
	public VerdigrisSlimeEntity(EntityType<? extends SlimeEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean canHaveStatusEffect(StatusEffectInstance effect) {
		if (effect.getEffectType() == StatusEffects.POISON) {
			return false;
		}
		return super.canHaveStatusEffect(effect);
	}
}
