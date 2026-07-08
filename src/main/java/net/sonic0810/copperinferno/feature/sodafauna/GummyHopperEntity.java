package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.World;

/**
 * A wobbling gummy-candy rabbit. Behavioral tweak: gummy physics — it never takes fall damage
 * ({@link #handleFallDamage} returns false). Drops Gummy Drops
 * ({@code loot_table/entities/gummy_hopper.json}).
 */
public class GummyHopperEntity extends RabbitEntity {
	public GummyHopperEntity(EntityType<? extends RabbitEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean handleFallDamage(double fallDistance, float damagePerDistance, DamageSource damageSource) {
		return false;
	}
}
