package net.sonic0810.copperinferno.feature.artifacts;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * "Brew Keg" — a copper-banded barrel that stores up to 16 drinks (anything with the 1.21.9
 * CONSUMABLE component: Dr.Pepper cans, potions, milk ...). Right-click WITH a drink to pour
 * one in; right-click with anything else (or an empty hand) to tap the most recent drink back
 * out. Contents survive in NBT and spill on the floor when the keg breaks.
 */
public class BrewKegBlock extends BlockWithEntity {
	public static final MapCodec<BrewKegBlock> CODEC = createCodec(BrewKegBlock::new);

	public BrewKegBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends BrewKegBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new BrewKegBlockEntity(pos, state);
	}

	@Override
	protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
			PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!stack.contains(DataComponentTypes.CONSUMABLE)) {
			// Not a drink: fall through to onUse, which taps the keg instead.
			return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
		}
		if (!world.isClient() && world.getBlockEntity(pos) instanceof BrewKegBlockEntity keg) {
			if (keg.isFull()) {
				player.sendMessage(Text.translatable("message.copper_inferno.brew_keg.full"), true);
			} else {
				keg.store(stack.copyWithCount(1));
				stack.decrementUnlessCreative(1, player);
				world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				player.sendMessage(Text.translatable("message.copper_inferno.brew_keg.stored",
						keg.getCount(), BrewKegBlockEntity.CAPACITY), true);
			}
		}
		return ActionResult.SUCCESS;
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (!world.isClient() && world.getBlockEntity(pos) instanceof BrewKegBlockEntity keg) {
			ItemStack drink = keg.dispense();
			if (drink.isEmpty()) {
				player.sendMessage(Text.translatable("message.copper_inferno.brew_keg.empty"), true);
			} else {
				if (!player.giveItemStack(drink)) {
					dropStack(world, pos.up(), drink);
				}
				world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
				player.sendMessage(Text.translatable("message.copper_inferno.brew_keg.stored",
						keg.getCount(), BrewKegBlockEntity.CAPACITY), true);
			}
		}
		return ActionResult.SUCCESS;
	}

	@Override
	protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
		// The block entity is still present here (same timing ItemScatterer.onStateReplaced
		// relies on for barrels): spill every stored drink before it is discarded.
		if (world.getBlockEntity(pos) instanceof BrewKegBlockEntity keg) {
			for (ItemStack drink : keg.clearDrinks()) {
				dropStack(world, pos, drink);
			}
		}
		super.onStateReplaced(state, world, pos, moved);
	}
}
