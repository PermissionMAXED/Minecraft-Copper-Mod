package net.sonic0810.copperinferno.feature.systems;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.feature.gear.GearFeature;

/**
 * Magnet Bench — right-click while holding the gear feature's copper magnet to trade it (plus
 * {@value #COPPER_INGOT_COST} copper ingots taken from the inventory) for a Reinforced Copper
 * Magnet. Damage/enchantments/name survive the upgrade ({@link ItemStack#withItem}).
 */
public class MagnetBenchBlock extends Block {
	public static final MapCodec<MagnetBenchBlock> CODEC = createCodec(MagnetBenchBlock::new);

	/** Ingot cost of one upgrade trade (skipped in creative mode). */
	public static final int COPPER_INGOT_COST = 8;

	public MagnetBenchBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends MagnetBenchBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
			PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (stack.isOf(SystemsFeature.REINFORCED_COPPER_MAGNET)) {
			if (!world.isClient()) {
				player.sendMessage(Text.translatable("message.copper_inferno.magnet_bench.already_upgraded"), true);
			}
			return ActionResult.SUCCESS;
		}
		if (!stack.isOf(GearFeature.COPPER_MAGNET)) {
			return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
		}
		if (world.isClient()) {
			return ActionResult.SUCCESS;
		}

		if (!player.isCreative() && !takeIngots(player, COPPER_INGOT_COST)) {
			player.sendMessage(Text.translatable("message.copper_inferno.magnet_bench.need_ingots",
					COPPER_INGOT_COST), true);
			return ActionResult.SUCCESS;
		}

		player.setStackInHand(hand, stack.withItem(SystemsFeature.REINFORCED_COPPER_MAGNET));
		world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.8f, 1.2f);
		player.sendMessage(Text.translatable("message.copper_inferno.magnet_bench.upgraded"), true);
		return ActionResult.SUCCESS;
	}

	/** Removes {@code count} copper ingots from the main inventory; false (and no-op) if short. */
	private static boolean takeIngots(PlayerEntity player, int count) {
		var stacks = player.getInventory().getMainStacks();
		int found = 0;
		for (ItemStack invStack : stacks) {
			if (invStack.isOf(Items.COPPER_INGOT)) {
				found += invStack.getCount();
			}
		}
		if (found < count) {
			return false;
		}
		int remaining = count;
		for (ItemStack invStack : stacks) {
			if (remaining > 0 && invStack.isOf(Items.COPPER_INGOT)) {
				int take = Math.min(remaining, invStack.getCount());
				invStack.decrement(take);
				remaining -= take;
			}
		}
		return true;
	}
}
