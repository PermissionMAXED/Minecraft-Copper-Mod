package net.sonic0810.copperinferno.feature.wildworld;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Ember Geyser: a vent block that periodically erupts, launching entities standing on it into
 * the air. Eruptions run on a shared world-time pulse (a 20-tick eruption window out of every
 * 100 ticks), so all geysers in a world blow in sync like a geyser field. Signatures verified
 * via javap: {@code Block#onSteppedOn(World, BlockPos, BlockState, Entity)} and
 * {@code Block#randomDisplayTick(BlockState, World, BlockPos, Random)}.
 */
public class EmberGeyserBlock extends Block {
	/** Length of the full eruption cycle in ticks. */
	private static final long CYCLE_TICKS = 100L;
	/** Ticks at the start of each cycle during which the geyser is erupting. */
	private static final long ERUPTION_TICKS = 20L;

	public EmberGeyserBlock(Settings settings) {
		super(settings);
	}

	private static boolean isErupting(World world) {
		return world.getTime() % CYCLE_TICKS < ERUPTION_TICKS;
	}

	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (isErupting(world)) {
			entity.addVelocity(0.0, 1.1, 0.0);
			entity.velocityModified = true;
			if (world instanceof ServerWorld serverWorld && world.getTime() % 5L == 0L) {
				serverWorld.spawnParticles(ParticleTypes.LAVA,
						pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5,
						6, 0.25, 0.1, 0.25, 0.02);
				serverWorld.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH,
						SoundCategory.BLOCKS, 0.4f, 0.6f);
			}
		}
		super.onSteppedOn(world, pos, state, entity);
	}

	/** Client-side ambience: steam wisps, denser while the eruption window is open. */
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		int chance = isErupting(world) ? 1 : 4;
		if (random.nextInt(chance) == 0) {
			world.addParticleClient(ParticleTypes.WHITE_SMOKE,
					pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.5,
					pos.getY() + 1.0,
					pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.5,
					0.0, 0.08, 0.0);
		}
	}
}
