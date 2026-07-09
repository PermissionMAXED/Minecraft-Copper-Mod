package net.sonic0810.copperinferno.feature.scorchwood;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.sonic0810.copperinferno.feature.infernodim.InfernoDimensionFeature;

/**
 * Scorched-wood sapling: a vanilla {@link SaplingBlock} (random-tick growth into the wood's
 * {@code copper_inferno:<wood>_tree} configured feature via its {@link SaplingGenerator}) that
 * can ALSO be planted on the Inferno dimension's ground blocks (ember soil, ash, cinderstone),
 * not just {@code #minecraft:dirt}/farmland, so the scorched woods are replantable both in the
 * overworld and at home in the Inferno.
 *
 * <p>Inherits {@code SaplingBlock.CODEC}; like the other content subclasses (e.g.
 * ContentButtonBlock) no dedicated codec is registered — it is never invoked for mod blocks.
 */
public class ScorchSaplingBlock extends SaplingBlock {
	public ScorchSaplingBlock(SaplingGenerator generator, AbstractBlock.Settings settings) {
		super(generator, settings);
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return super.canPlantOnTop(floor, world, pos)
				|| floor.isOf(InfernoDimensionFeature.EMBER_SOIL)
				|| floor.isOf(InfernoDimensionFeature.ASH_BLOCK)
				|| floor.isOf(InfernoDimensionFeature.CINDERSTONE);
	}
}
