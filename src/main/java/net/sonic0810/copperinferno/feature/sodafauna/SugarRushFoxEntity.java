package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A candy-pink fox permanently on a sugar rush. Behavioral tweak: MOVEMENT_SPEED 0.42 (a vanilla
 * fox runs at 0.3) in the default attributes registered by {@link SodaFaunaFeature}.
 * {@code createChild} is overridden only for type consistency (vanilla hard-codes
 * {@code EntityType.FOX}; same fix as the infernomobs Cinder Strider). Drops Sugar
 * Crystals ({@code loot_table/entities/sugar_rush_fox.json}).
 */
public class SugarRushFoxEntity extends FoxEntity {
	public SugarRushFoxEntity(EntityType<? extends FoxEntity> type, World world) {
		super(type, world);
	}

	@Override
	public SugarRushFoxEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new SugarRushFoxEntity(SodaFaunaFeature.SUGAR_RUSH_FOX, world);
	}
}
