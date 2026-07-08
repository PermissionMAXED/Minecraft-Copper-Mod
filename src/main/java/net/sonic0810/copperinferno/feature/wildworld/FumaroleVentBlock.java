package net.sonic0810.copperinferno.feature.wildworld;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Fumarole Vent: a volcanic chimney block that continuously exhales campfire-style smoke.
 * Purely cosmetic — the smoke is emitted from {@code randomDisplayTick} (client-invoked, same
 * mechanism as vanilla {@code CampfireBlock}; signature verified via javap).
 */
public class FumaroleVentBlock extends Block {
	public FumaroleVentBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (random.nextInt(2) == 0) {
			world.addParticleClient(ParticleTypes.CAMPFIRE_COSY_SMOKE,
					pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.4,
					pos.getY() + 1.0 + random.nextDouble() * 0.3,
					pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.4,
					0.0, 0.07, 0.0);
		}
		if (random.nextInt(10) == 0) {
			world.playSoundAtBlockCenterClient(pos, SoundEvents.BLOCK_CAMPFIRE_CRACKLE,
					SoundCategory.BLOCKS, 0.4f + random.nextFloat() * 0.4f,
					random.nextFloat() * 0.5f + 0.5f, false);
		}
	}
}
