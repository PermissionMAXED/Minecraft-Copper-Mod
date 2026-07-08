package net.sonic0810.copperinferno.feature.artifacts;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * "Trophy Pedestal" — a gilded display column: right-click WITH any item to set it as the
 * trophy (the previous trophy pops back out), right-click with an empty hand to take the
 * trophy down. The trophy itself is drawn floating above the column by
 * {@code TrophyPedestalBlockEntityRenderer} (client source set), which follows the statue
 * renderer's 1.21.9 render-state contract; the static block model is just the pedestal.
 */
public class TrophyPedestalBlock extends BlockWithEntity {
	public static final MapCodec<TrophyPedestalBlock> CODEC = createCodec(TrophyPedestalBlock::new);
	/** Column silhouette: 2px base slab, 8px shaft, 12px top plate (matches the model). */
	private static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 12.0, 14.0);

	public TrophyPedestalBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends TrophyPedestalBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new TrophyPedestalBlockEntity(pos, state);
	}

	@Override
	protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
			PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient() && world.getBlockEntity(pos) instanceof TrophyPedestalBlockEntity pedestal) {
			ItemStack previous = pedestal.getStack();
			pedestal.setStack(stack.copyWithCount(1));
			stack.decrementUnlessCreative(1, player);
			if (!previous.isEmpty() && !player.giveItemStack(previous)) {
				dropStack(world, pos.up(), previous);
			}
			world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		return ActionResult.SUCCESS;
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (!world.isClient() && world.getBlockEntity(pos) instanceof TrophyPedestalBlockEntity pedestal) {
			ItemStack trophy = pedestal.getStack();
			if (trophy.isEmpty()) {
				return ActionResult.PASS;
			}
			pedestal.setStack(ItemStack.EMPTY);
			if (!player.giveItemStack(trophy)) {
				dropStack(world, pos.up(), trophy);
			}
			world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		return ActionResult.SUCCESS;
	}

	@Override
	protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
		if (world.getBlockEntity(pos) instanceof TrophyPedestalBlockEntity pedestal
				&& !pedestal.getStack().isEmpty()) {
			dropStack(world, pos, pedestal.getStack());
			pedestal.setStack(ItemStack.EMPTY);
		}
		super.onStateReplaced(state, world, pos, moved);
	}
}
