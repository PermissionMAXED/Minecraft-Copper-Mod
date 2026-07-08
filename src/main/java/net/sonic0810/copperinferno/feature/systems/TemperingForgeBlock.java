package net.sonic0810.copperinferno.feature.systems;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
import net.sonic0810.copperinferno.feature.infernium.InferniumFeature;

/**
 * Tempering Forge — right-click with damaged gear to temper it: one Ember Dust
 * (the infernium feature's ember item) is consumed from the inventory and up to
 * {@value #DURABILITY_PER_EMBER} durability is restored to the held item.
 */
public class TemperingForgeBlock extends Block {
	public static final MapCodec<TemperingForgeBlock> CODEC = createCodec(TemperingForgeBlock::new);

	/** Durability restored per consumed ember dust. */
	public static final int DURABILITY_PER_EMBER = 50;

	public TemperingForgeBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends TemperingForgeBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
			PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!stack.isDamageable()) {
			return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
		}
		if (world.isClient()) {
			return ActionResult.SUCCESS;
		}
		if (!stack.isDamaged()) {
			player.sendMessage(Text.translatable("message.copper_inferno.tempering_forge.undamaged"), true);
			return ActionResult.SUCCESS;
		}

		if (!player.isCreative() && !takeEmber(player)) {
			player.sendMessage(Text.translatable("message.copper_inferno.tempering_forge.no_ember"), true);
			return ActionResult.SUCCESS;
		}

		int repaired = Math.min(DURABILITY_PER_EMBER, stack.getDamage());
		stack.setDamage(stack.getDamage() - repaired);
		world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.7f, 1.4f);
		if (world instanceof ServerWorld serverWorld) {
			serverWorld.spawnParticles(ParticleTypes.LAVA,
					pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5, 8, 0.3, 0.2, 0.3, 0.0);
		}
		player.sendMessage(Text.translatable("message.copper_inferno.tempering_forge.repaired", repaired), true);
		return ActionResult.SUCCESS;
	}

	/** Removes one ember dust from the player's main inventory; false (and no-op) if absent. */
	private static boolean takeEmber(PlayerEntity player) {
		for (ItemStack invStack : player.getInventory().getMainStacks()) {
			if (invStack.isOf(InferniumFeature.EMBER_DUST)) {
				invStack.decrement(1);
				return true;
			}
		}
		return false;
	}
}
