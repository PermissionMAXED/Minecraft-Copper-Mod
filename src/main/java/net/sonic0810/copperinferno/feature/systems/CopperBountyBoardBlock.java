package net.sonic0810.copperinferno.feature.systems;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.feature.extras.ExtrasFeature;

/**
 * Copper Bounty Board — posts a daily "kill X of mob Y" bounty (stored in NBT on the block
 * entity). Right-click to check progress or collect the copper-coin payout once complete;
 * sneak-right-click to cycle to the next posted bounty (resetting progress).
 */
public class CopperBountyBoardBlock extends BlockWithEntity {
	public static final MapCodec<CopperBountyBoardBlock> CODEC = createCodec(CopperBountyBoardBlock::new);

	public CopperBountyBoardBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends CopperBountyBoardBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CopperBountyBoardBlockEntity(pos, state);
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (world.isClient()) {
			return ActionResult.SUCCESS;
		}
		if (!(world.getBlockEntity(pos) instanceof CopperBountyBoardBlockEntity board)) {
			return ActionResult.PASS;
		}

		if (player.isSneaking()) {
			board.cycleBounty();
			CopperBountyBoardBlockEntity.Bounty next = board.currentBounty();
			world.playSound(null, pos, SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, SoundCategory.BLOCKS, 0.8f, 1.0f);
			player.sendMessage(Text.translatable("message.copper_inferno.bounty_board.cycled",
					next.type().getName(), next.required(), next.rewardCoins()), true);
			return ActionResult.SUCCESS;
		}

		CopperBountyBoardBlockEntity.Bounty bounty = board.currentBounty();
		if (board.isComplete()) {
			player.giveItemStack(new ItemStack(ExtrasFeature.COPPER_COIN, bounty.rewardCoins()));
			board.completeAndAdvance();
			world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_YES, SoundCategory.BLOCKS, 1.0f, 1.0f);
			player.sendMessage(Text.translatable("message.copper_inferno.bounty_board.complete",
					bounty.rewardCoins()), true);
		} else {
			player.sendMessage(Text.translatable("message.copper_inferno.bounty_board.progress",
					bounty.type().getName(), board.getKills(), bounty.required()), true);
		}
		return ActionResult.SUCCESS;
	}
}
