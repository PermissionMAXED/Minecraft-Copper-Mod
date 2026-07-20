package net.sonic0810.copperinferno.feature.systems;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.feature.sodablocks.SodaBlocksFeature;

/**
 * Soda Fountain — right-click with an empty glass bottle to tap a RANDOM bottled fizz brew
 * from the cuisine feature (WP7), but only while the fountain sits directly on top of a
 * {@code soda_syrup_block} (the sodablocks feature's syrup block acts as the syrup tank).
 *
 * <p>The 20 cuisine drinks are resolved lazily by id from the item registry (they are
 * registered by {@code CuisineFeature.init()}, which runs before {@code SystemsFeature.init()};
 * the ids are not exposed as fields, hence the registry lookup).
 */
public class SodaFountainBlock extends Block {
	public static final MapCodec<SodaFountainBlock> CODEC = createCodec(SodaFountainBlock::new);

	/** The cuisine feature's 20 bottled fizz brews (registration order of CuisineFeature). */
	private static final String[] DRINK_IDS = {
			"sugar_rush_fizz", "ember_belly_fizz", "cinder_skin_fizz", "ember_fizz", "cinder_fizz",
			"ash_fizz", "magma_fizz", "soda_fizz", "smoke_fizz", "spice_fizz",
			"leaping_fizz", "mending_fizz", "hearty_fizz", "lucky_fizz", "tidal_fizz",
			"feather_fizz", "dolphin_fizz", "iced_ember_tea", "molten_mocha", "cindercream_shake"
	};

	public SodaFountainBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends SodaFountainBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
			PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!stack.isOf(Items.GLASS_BOTTLE)) {
			return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
		}
		if (world.isClient()) {
			return ActionResult.SUCCESS;
		}

		if (!world.getBlockState(pos.down()).isOf(SodaBlocksFeature.SODA_SYRUP_BLOCK)) {
			player.sendMessage(Text.translatable("message.copper_inferno.soda_fountain.no_syrup"), true);
			return ActionResult.SUCCESS;
		}

		String drinkId = DRINK_IDS[world.random.nextInt(DRINK_IDS.length)];
		Item drink = Registries.ITEM.get(Identifier.of("copper_inferno", drinkId));
		if (drink == Items.AIR) {
			// Defensive: cuisine content missing would leave the registry default (air).
			return ActionResult.PASS;
		}

		stack.decrementUnlessCreative(1, player);
		player.giveItemStack(new ItemStack(drink));
		world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
		if (world instanceof ServerWorld serverWorld) {
			serverWorld.spawnParticles(ParticleTypes.SPLASH,
					pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5, 10, 0.25, 0.2, 0.25, 0.0);
		}
		return ActionResult.SUCCESS;
	}
}
