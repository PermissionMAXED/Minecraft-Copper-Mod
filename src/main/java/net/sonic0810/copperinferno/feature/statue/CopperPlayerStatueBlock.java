package net.sonic0810.copperinferno.feature.statue;

import java.util.function.Consumer;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModParticles;

/**
 * A copper statue of an arbitrary player. Right-clicking with an empty hand opens a client-side
 * screen to enter the player name; the actual figure is drawn by the block entity renderer, the
 * static block model is just a small copper pedestal.
 */
public class CopperPlayerStatueBlock extends BlockWithEntity {
	public static final MapCodec<CopperPlayerStatueBlock> CODEC = createCodec(CopperPlayerStatueBlock::new);
	public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
	/** Roughly player-sized (the rendered figure is ~0.6 blocks wide on the pedestal). */
	private static final VoxelShape SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);

	public CopperPlayerStatueBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends CopperPlayerStatueBlock> getCodec() {
		return CODEC;
	}

	/**
	 * Explicitly render the static pedestal model. {@code AbstractBlock.getRenderType(BlockState)}
	 * controls this in 1.21.9 (verified via javap); pinning it to MODEL guarantees the pedestal
	 * JSON model renders alongside the block-entity-rendered figure.
	 */
	@Override
	protected BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	protected BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext context) {
		return SHAPE;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CopperPlayerStatueBlockEntity(pos, state);
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (player.isSpectator() || !player.getMainHandStack().isEmpty()) {
			return ActionResult.PASS;
		}
		if (world.isClient()) {
			// The client feature installs this hook; it opens the name-entry screen.
			Consumer<BlockPos> opener = PlayerStatueFeature.statueScreenOpener;
			if (opener != null) {
				opener.accept(pos);
			}
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (random.nextInt(3) != 0) {
			return;
		}
		double x = pos.getX() + 0.2 + random.nextDouble() * 0.6;
		double y = pos.getY() + random.nextDouble() * 1.9;
		double z = pos.getZ() + 0.2 + random.nextDouble() * 0.6;
		world.addParticleClient(ModParticles.COPPER_SPARKLE, x, y, z, 0.0, 0.02, 0.0);
		if (random.nextInt(4) == 0) {
			world.addParticleClient(ParticleTypes.ELECTRIC_SPARK, x, y, z,
					(random.nextDouble() - 0.5) * 0.05, random.nextDouble() * 0.05, (random.nextDouble() - 0.5) * 0.05);
		}
		if (random.nextInt(4) == 0) {
			world.addParticleClient(ParticleTypes.END_ROD, x, y, z, 0.0, 0.01, 0.0);
		}
	}
}
