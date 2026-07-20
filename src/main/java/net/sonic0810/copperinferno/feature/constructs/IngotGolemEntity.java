package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.world.World;

/**
 * A snow-golem-shaped stack of cast ingots that lobs its projectiles just the same. Behavior is
 * vanilla snow golem plus ONE tweak: cast metal does not melt in rain or water - see
 * {@link #hurtByWater()}. Drops iron nuggets
 * ({@code loot_table/entities/ingot_golem.json}).
 */
public class IngotGolemEntity extends SnowGolemEntity {
	public IngotGolemEntity(EntityType<? extends SnowGolemEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean hurtByWater() {
		// TWEAK: rustproof cast. LivingEntity.hurtByWater() verified via javap (vanilla snow
		// golems return true and die in the rain).
		return false;
	}
}
