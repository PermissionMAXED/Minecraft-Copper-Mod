package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

/**
 * A creeper sealed inside a soda can. Behavioral tweak: KNOCKBACK_RESISTANCE 0.8 (a vanilla
 * creeper has none — the can is ballast) in the default attributes registered by
 * {@link SodaFaunaFeature}. Explodes like any creeper: shaken, not stirred. Drops Bottlecaps
 * ({@code loot_table/entities/canned_creeper.json}).
 */
public class CannedCreeperEntity extends CreeperEntity {
	public CannedCreeperEntity(EntityType<? extends CreeperEntity> type, World world) {
		super(type, world);
	}
}
