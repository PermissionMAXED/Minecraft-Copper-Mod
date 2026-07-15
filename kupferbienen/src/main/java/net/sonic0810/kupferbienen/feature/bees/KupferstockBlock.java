package net.sonic0810.kupferbienen.feature.bees;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Copper apiary block. The production logic lives in {@link KupferstockBlockEntity} (server
 * ticker); this block wires the 1.21.9 {@code BlockWithEntity} contract (mandatory
 * {@code getCodec}, {@code validateTicker} helper — both verified via javap), an empty-hand
 * eject interaction, comparator output from the 3-slot inventory, and content scattering on
 * break ({@code ItemScatterer.onStateReplaced}, the vanilla barrel pattern).
 */
public class KupferstockBlock extends BlockWithEntity {
	public static final MapCodec<KupferstockBlock> CODEC = createCodec(KupferstockBlock::new);

	public KupferstockBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends KupferstockBlock> getCodec() {
		return CODEC;
	}

	/** BlockWithEntity defaults to INVISIBLE; the apiary has a real cube model. */
	@Override
	protected BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new KupferstockBlockEntity(pos, state);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		if (world.isClient()) {
			return null;
		}
		return validateTicker(type, BeesFeature.KUPFERSTOCK_BLOCK_ENTITY, KupferstockBlockEntity::serverTick);
	}

	/** Empty main hand: pop one stored stack out of the apiary. */
	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (player.isSpectator() || !player.getMainHandStack().isEmpty()) {
			return ActionResult.PASS;
		}
		if (!world.isClient() && world.getBlockEntity(pos) instanceof KupferstockBlockEntity stock) {
			ItemStack ejected = stock.ejectFirstStack();
			if (!ejected.isEmpty()) {
				ItemScatterer.spawn(world, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, ejected);
			}
		}
		return ActionResult.SUCCESS;
	}

	@Override
	protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
		ItemScatterer.onStateReplaced(state, world, pos);
	}

	@Override
	protected boolean hasComparatorOutput(BlockState state) {
		return true;
	}

	@Override
	protected int getComparatorOutput(BlockState state, World world, BlockPos pos, Direction direction) {
		return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
	}
}
