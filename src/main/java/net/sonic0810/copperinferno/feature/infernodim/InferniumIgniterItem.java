package net.sonic0810.copperinferno.feature.infernodim;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Flint-and-steel-like igniter for the Inferno portal. Clicking an infernium portal frame
 * block scans (both horizontal axes) for an upright 4-wide x 5-tall frame rectangle whose
 * 2x3 interior is air; when found, the interior is filled with inferno portal panes matching
 * the frame's axis, the end-portal-spawn sound plays and the igniter takes 1 durability.
 */
public class InferniumIgniterItem extends Item {
	public InferniumIgniterItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		if (!world.getBlockState(pos).isOf(InfernoDimensionFeature.INFERNIUM_PORTAL_FRAME)) {
			return ActionResult.PASS;
		}
		for (Direction.Axis axis : new Direction.Axis[] {Direction.Axis.X, Direction.Axis.Z}) {
			Direction right = axis == Direction.Axis.X ? Direction.EAST : Direction.SOUTH;
			// The clicked frame block is somewhere on the ring; try every interior
			// origin (bottom cell of the left interior column) it could belong to.
			for (int da = -2; da <= 2; da++) {
				for (int dy = -4; dy <= 2; dy++) {
					BlockPos origin = pos.offset(right, da).up(dy);
					if (!isValidFrame(world, origin, right)) {
						continue;
					}
					if (!world.isClient()) {
						fillPortal(world, origin, right, axis);
						world.playSound(null, origin, SoundEvents.BLOCK_END_PORTAL_SPAWN,
								SoundCategory.BLOCKS, 1.0f, 1.0f);
						PlayerEntity player = context.getPlayer();
						if (player != null) {
							context.getStack().damage(1, player);
						}
					}
					return ActionResult.SUCCESS;
				}
			}
		}
		return ActionResult.PASS;
	}

	/**
	 * True when {@code origin} (bottom-left interior cell, low coordinate along {@code right})
	 * sits inside a complete 4x5 infernium frame ring with an all-air 2x3 interior.
	 */
	private static boolean isValidFrame(World world, BlockPos origin, Direction right) {
		for (int i = -1; i <= 2; i++) {
			if (!isFrame(world, origin.offset(right, i).down())
					|| !isFrame(world, origin.offset(right, i).up(3))) {
				return false;
			}
		}
		for (int j = 0; j <= 2; j++) {
			if (!isFrame(world, origin.offset(right, -1).up(j))
					|| !isFrame(world, origin.offset(right, 2).up(j))) {
				return false;
			}
		}
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 2; j++) {
				if (!world.getBlockState(origin.offset(right, i).up(j)).isAir()) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isFrame(World world, BlockPos pos) {
		return world.getBlockState(pos).isOf(InfernoDimensionFeature.INFERNIUM_PORTAL_FRAME);
	}

	private static void fillPortal(World world, BlockPos origin, Direction right, Direction.Axis axis) {
		BlockState portal = InfernoDimensionFeature.INFERNO_PORTAL.getDefaultState()
				.with(InfernoPortalBlock.AXIS, axis);
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 2; j++) {
				// FORCE_STATE skips shape updates so the half-placed pane doesn't collapse
				// (mirrors vanilla AreaHelper's placement flag 18).
				world.setBlockState(origin.offset(right, i).up(j), portal,
						Block.NOTIFY_LISTENERS | Block.FORCE_STATE);
			}
		}
	}
}
