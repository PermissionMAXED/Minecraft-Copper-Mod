package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A sulfur-crusted hoglin bull. Tweak: it never zombifies — vanilla hoglins convert to zoglins
 * after 300 ticks outside a piglin-safe dimension, which would silently replace every
 * brimstone bull in the Inferno; {@code canConvert()} (public on HoglinEntity, verified via
 * javap) is pinned to false. {@code createChild} is overridden only for type consistency
 * (vanilla hard-codes {@code EntityType.HOGLIN}; same fix as the infernomobs Cinder Strider).
 * Drops Brimstone Hide ({@code loot_table/entities/brimstone_bull.json}).
 */
public class BrimstoneBullEntity extends HoglinEntity {
	public BrimstoneBullEntity(EntityType<? extends HoglinEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean canConvert() {
		return false;
	}

	@Override
	public BrimstoneBullEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new BrimstoneBullEntity(InfernoFaunaFeature.BRIMSTONE_BULL, world);
	}
}
