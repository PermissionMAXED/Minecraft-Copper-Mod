package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A soot-black pig rooting through the cinders. Tweak: breeding stays in the family — vanilla
 * {@code PigEntity.createChild} hard-codes {@code EntityType.PIG}, so without this covariant
 * override two soot piglets would produce a vanilla pig (same fix as the infernomobs Cinder
 * Strider). Drops cooked porkchops — it lives pre-roasted
 * ({@code loot_table/entities/soot_piglet.json}).
 */
public class SootPigletEntity extends PigEntity {
	public SootPigletEntity(EntityType<? extends PigEntity> type, World world) {
		super(type, world);
	}

	@Override
	public SootPigletEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new SootPigletEntity(InfernoFaunaFeature.SOOT_PIGLET, world);
	}
}
