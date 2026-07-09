package net.sonic0810.copperinferno.feature.artifacts;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * "Copper Drill" — a pickaxe (component-driven via {@code Item.Settings.pickaxe}, like the
 * arsenal tier pickaxes) that mines a 3x1 LINE: breaking a block also drills the next two
 * blocks along the player's look axis ({@code Direction.getFacing} of the look vector), each
 * broken through {@code ServerPlayerInteractionManager.tryBreakBlock} so tool checks,
 * gamemode rules and loot drops all behave exactly like a hand-mined block. A ThreadLocal
 * re-entrancy latch stops the extra breaks from chaining forever (tryBreakBlock re-enters
 * {@link #postMine}). Skips air, unbreakables (hardness &lt; 0), block entities and blocks the
 * drill is not suitable for; each extra block costs the normal 1 durability via the vanilla
 * tool component.
 */
public class CopperDrillItem extends Item {
	private static final int EXTRA_BLOCKS = 2;

	/** Re-entrancy latch: true while this drill is breaking its extension blocks. */
	private static final ThreadLocal<Boolean> DRILLING = ThreadLocal.withInitial(() -> false);

	public CopperDrillItem(Settings settings) {
		super(settings);
	}

	@Override
	public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
		boolean result = super.postMine(stack, world, state, pos, miner);
		if (DRILLING.get() || world.isClient() || !(miner instanceof ServerPlayerEntity player)) {
			return result;
		}

		Direction direction = Direction.getFacing(player.getRotationVec(1.0F));
		DRILLING.set(true);
		try {
			for (int i = 1; i <= EXTRA_BLOCKS; i++) {
				if (stack.isEmpty()) {
					break; // the drill broke mid-line
				}
				BlockPos next = pos.offset(direction, i);
				BlockState nextState = world.getBlockState(next);
				if (nextState.isAir()
						|| nextState.getHardness(world, next) < 0.0F
						|| nextState.hasBlockEntity()
						|| !stack.isSuitableFor(nextState)) {
					break;
				}
				player.interactionManager.tryBreakBlock(next);
			}
		} finally {
			DRILLING.set(false);
		}
		return result;
	}
}
