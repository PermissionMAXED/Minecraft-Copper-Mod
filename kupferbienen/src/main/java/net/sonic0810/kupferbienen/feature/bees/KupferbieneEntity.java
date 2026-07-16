package net.sonic0810.kupferbienen.feature.bees;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Copper bee. Near-pure {@link BeeEntity} subclass: all AI (pollination, hive homing, anger) is
 * inherited; the copper look comes from {@code KupferbieneRenderer}'s fixed recolored texture.
 * The vanilla pollinate goal accepts the Kupferbluete because it is injected into
 * {@code #minecraft:bee_attractive} (verified: {@code BeeEntity.isAttractive} checks
 * {@code BlockTags.BEE_ATTRACTIVE}). Only {@link #createChild} is overridden so breeding yields
 * a Kupferbiene instead of a vanilla bee.
 */
public class KupferbieneEntity extends BeeEntity {
	public KupferbieneEntity(EntityType<? extends BeeEntity> type, World world) {
		super(type, world);
	}

	@Override
	public KupferbieneEntity createChild(ServerWorld world, PassiveEntity entity) {
		return BeesFeature.KUPFERBIENE.create(world, SpawnReason.BREEDING);
	}
}
