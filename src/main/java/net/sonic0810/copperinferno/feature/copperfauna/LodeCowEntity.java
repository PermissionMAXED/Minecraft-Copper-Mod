package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A cow that grazes near ore seams and carries the weight to prove it. Behavioral tweak:
 * KNOCKBACK_RESISTANCE 0.6 (vanilla cow: 0.0) in the default attributes registered by
 * {@link CopperFaunaFeature}. {@code createChild} is overridden only for type consistency
 * (vanilla hard-codes {@code EntityType.COW}; same fix as the infernomobs Cinder Strider).
 * Drops Lode Hides ({@code loot_table/entities/lode_cow.json}).
 */
public class LodeCowEntity extends CowEntity {
	public LodeCowEntity(EntityType<? extends CowEntity> type, World world) {
		super(type, world);
	}

	@Override
	public LodeCowEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new LodeCowEntity(CopperFaunaFeature.LODE_COW, world);
	}
}
