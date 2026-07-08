package net.sonic0810.copperinferno.feature.infernodim2;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Decorative chiseled corner block for the Infernium portal frame. Accepted anywhere a
 * regular {@code infernium_portal_frame} block is (both the igniter's ring scan and the
 * portal pane's support check treat it as frame), so builders can dress up the ring's four
 * corners without breaking the portal.
 *
 * <p>Ambience mirrors vanilla display-tick blocks (cf. {@code TorchBlock.randomDisplayTick}
 * and the sound roll in {@code NetherPortalBlock.randomDisplayTick}, both verified via
 * javap): occasional small ember flames drifting off the block plus a rare fire crackle.
 * {@link Block#randomDisplayTick} runs client-side only, so no client wiring is needed.
 */
public class InferniumPortalCornerBlock extends Block {
	public InferniumPortalCornerBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (random.nextInt(3) == 0) {
			world.addParticleClient(ParticleTypes.SMALL_FLAME,
					pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 1.1,
					pos.getY() + 0.5 + (random.nextDouble() - 0.5) * 1.1,
					pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 1.1,
					0.0, 0.0, 0.0);
		}
		if (random.nextInt(40) == 0) {
			world.playSoundClient(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
					SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS,
					0.4f, random.nextFloat() * 0.4f + 0.8f, false);
		}
	}
}
