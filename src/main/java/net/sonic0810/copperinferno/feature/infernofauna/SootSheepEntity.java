package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

/**
 * A sheep whose fleece is stained with soot. Tweak: every soot sheep spawns with BLACK wool —
 * {@code initialize} runs the vanilla spawn setup (which rolls the usual white-heavy color
 * table) and then pins the color via {@code setColor(DyeColor)} (public on SheepEntity,
 * verified via javap), so shearing yields black wool. {@code createChild} is overridden only
 * for type consistency (vanilla hard-codes {@code EntityType.SHEEP}; same fix as the
 * infernomobs Cinder Strider) — lambs are born soot-black too. Drops black wool
 * ({@code loot_table/entities/soot_sheep.json}).
 */
public class SootSheepEntity extends SheepEntity {
	public SootSheepEntity(EntityType<? extends SheepEntity> type, World world) {
		super(type, world);
	}

	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
			SpawnReason spawnReason, EntityData entityData) {
		EntityData data = super.initialize(world, difficulty, spawnReason, entityData);
		this.setColor(DyeColor.BLACK);
		return data;
	}

	@Override
	public SootSheepEntity createChild(ServerWorld world, PassiveEntity entity) {
		SootSheepEntity lamb = new SootSheepEntity(InfernoFaunaFeature.SOOT_SHEEP, world);
		lamb.setColor(DyeColor.BLACK);
		return lamb;
	}
}
