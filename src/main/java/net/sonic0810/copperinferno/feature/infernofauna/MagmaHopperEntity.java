package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.world.World;

/**
 * A restless slag cube that barely stays on the ground. Tweak: vanilla
 * {@code MagmaCubeEntity.getTicksUntilNextJump()} quadruples the slime delay
 * (bytecode: {@code super.getTicksUntilNextJump() * 4}); the hopper divides it back down, so it
 * hops roughly sixteen times as often as a magma cube. Everything else (random size on natural
 * spawn, splitting on death) is inherited. Drops Hopper Slag
 * ({@code loot_table/entities/magma_hopper.json}).
 */
public class MagmaHopperEntity extends MagmaCubeEntity {
	public MagmaHopperEntity(EntityType<? extends MagmaCubeEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected int getTicksUntilNextJump() {
		return Math.max(1, super.getTicksUntilNextJump() / 16);
	}
}
