package net.sonic0810.copperinferno.feature.systems;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.oxidation.ItemOxidation;

/**
 * Waxing Station — right-click with a honeycomb to bulk-wax EVERY un-waxed oxidizable copper
 * item in the player's inventory (main inventory + armor + offhand) at once, for a single
 * honeycomb. Uses the shared item-oxidation engine's waxed marker
 * ({@code core.ModComponents.WAXED} via {@link ItemOxidation#setWaxed}).
 */
public class WaxingStationBlock extends Block {
	public static final MapCodec<WaxingStationBlock> CODEC = createCodec(WaxingStationBlock::new);

	private static final EquipmentSlot[] SCANNED_EQUIPMENT_SLOTS = {
			EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.OFFHAND
	};

	public WaxingStationBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends WaxingStationBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
			PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!stack.isOf(Items.HONEYCOMB)) {
			return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
		}
		if (world.isClient()) {
			return ActionResult.SUCCESS;
		}

		int waxed = 0;
		for (ItemStack invStack : player.getInventory().getMainStacks()) {
			if (waxable(invStack)) {
				ItemOxidation.setWaxed(invStack, true);
				waxed++;
			}
		}
		for (EquipmentSlot slot : SCANNED_EQUIPMENT_SLOTS) {
			ItemStack equipped = player.getEquippedStack(slot);
			if (waxable(equipped)) {
				ItemOxidation.setWaxed(equipped, true);
				waxed++;
			}
		}

		if (waxed == 0) {
			player.sendMessage(Text.translatable("message.copper_inferno.waxing_station.nothing"), true);
			return ActionResult.SUCCESS;
		}

		stack.decrementUnlessCreative(1, player);
		world.playSound(null, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
		if (world instanceof ServerWorld serverWorld) {
			serverWorld.spawnParticles(ParticleTypes.WAX_ON,
					pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5, 12, 0.3, 0.2, 0.3, 0.0);
		}
		player.sendMessage(Text.translatable("message.copper_inferno.waxing_station.waxed", waxed), true);
		return ActionResult.SUCCESS;
	}

	private static boolean waxable(ItemStack stack) {
		return !stack.isEmpty() && ItemOxidation.isInChain(stack.getItem()) && !ItemOxidation.isWaxed(stack);
	}
}
