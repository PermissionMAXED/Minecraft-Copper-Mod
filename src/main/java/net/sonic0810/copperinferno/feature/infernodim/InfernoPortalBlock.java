package net.sonic0810.copperinferno.feature.infernodim;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Portal;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import net.sonic0810.copperinferno.core.ModDimensions;

/**
 * The Inferno portal interior block. Mirrors {@link net.minecraft.block.NetherPortalBlock}:
 * an AXIS-oriented flat pane that implements {@link Portal} — entities colliding with it call
 * {@link Entity#tryUsePortal}, and the vanilla portal plumbing then invokes
 * {@link #createTeleportTarget} (Overworld &lt;-&gt; Inferno, 1:1 coordinates). The arrival side
 * gets a 5x5 cinderstone platform at y=70 with a pre-lit 4x5 return frame.
 *
 * <p>Collapse behaviour: every in-plane neighbor (vertical, or horizontal along the portal's
 * AXIS) must be another portal block or an infernium portal frame block, otherwise the portal
 * pane turns to air (which cascades through the whole pane, mirroring vanilla's AreaHelper
 * re-validation in {@code NetherPortalBlock.getStateForNeighborUpdate}).
 */
public class InfernoPortalBlock extends Block implements Portal {
	public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;

	/** Y level of the arrival platform built in the destination dimension. */
	public static final int PLATFORM_Y = 70;

	// Same pane thickness as vanilla nether_portal (4/16, centered).
	private static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
	private static final VoxelShape Z_SHAPE = Block.createCuboidShape(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);

	public InfernoPortalBlock(Settings settings) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(AXIS, Direction.Axis.X));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return state.get(AXIS) == Direction.Axis.Z ? Z_SHAPE : X_SHAPE;
	}

	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView,
			BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
		Direction.Axis portalAxis = state.get(AXIS);
		boolean inPlane = direction.getAxis() == Direction.Axis.Y || direction.getAxis() == portalAxis;
		if (inPlane && !neighborState.isOf(this)
				&& !neighborState.isOf(InfernoDimensionFeature.INFERNIUM_PORTAL_FRAME)) {
			return Blocks.AIR.getDefaultState();
		}
		return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity,
			EntityCollisionHandler handler) {
		if (entity.canUsePortals(false)) {
			entity.tryUsePortal(this, pos);
		}
	}

	@Override
	public int getPortalDelay(ServerWorld world, Entity entity) {
		return 0;
	}

	@Override
	public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
		MinecraftServer server = world.getServer();
		ServerWorld dest = world.getRegistryKey() == ModDimensions.INFERNO_WORLD
				? server.getOverworld()
				: ModDimensions.infernoWorld(server);
		if (dest == null) {
			return null; // Inferno dimension JSON not loaded; vanilla plumbing tolerates null.
		}
		// Arrival keeps the 1:1 x/z coordinates, standing on the y=70 platform.
		BlockPos center = BlockPos.ofFloored(entity.getX(), PLATFORM_Y, entity.getZ());
		buildArrivalPlatform(dest, center);
		Vec3d arrival = new Vec3d(center.getX() + 0.5, PLATFORM_Y + 1, center.getZ() + 0.5);
		return new TeleportTarget(dest, arrival, Vec3d.ZERO, entity.getYaw(), entity.getPitch(),
				TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET);
	}

	@Override
	public Portal.Effect getPortalEffect() {
		return Portal.Effect.CONFUSION;
	}

	/**
	 * Builds a 5x5 cinderstone platform centered at {@code center} plus a pre-lit 4-wide x
	 * 5-tall return frame (X axis) on its north edge. Skipped when a lit inferno portal is
	 * already standing in the arrival box (keeps previously-built return portals intact).
	 */
	private static void buildArrivalPlatform(ServerWorld dest, BlockPos center) {
		for (int dx = -2; dx <= 2; dx++) {
			for (int dy = 0; dy <= 5; dy++) {
				for (int dz = -2; dz <= 2; dz++) {
					if (dest.getBlockState(center.add(dx, dy, dz)).isOf(InfernoDimensionFeature.INFERNO_PORTAL)) {
						return;
					}
				}
			}
		}
		BlockState platform = InfernoDimensionFeature.CINDERSTONE.getDefaultState();
		BlockState frame = InfernoDimensionFeature.INFERNIUM_PORTAL_FRAME.getDefaultState();
		BlockState air = Blocks.AIR.getDefaultState();
		for (int dx = -2; dx <= 2; dx++) {
			for (int dz = -2; dz <= 2; dz++) {
				dest.setBlockState(center.add(dx, 0, dz), platform);
				for (int dy = 1; dy <= 5; dy++) {
					dest.setBlockState(center.add(dx, dy, dz), air);
				}
			}
		}
		// Frame ring on the north edge (z = -2): 4 wide (dx -1..2) x 5 tall (dy 0..4).
		for (int dx = -1; dx <= 2; dx++) {
			dest.setBlockState(center.add(dx, 0, -2), frame);
			dest.setBlockState(center.add(dx, 4, -2), frame);
		}
		for (int dy = 1; dy <= 3; dy++) {
			dest.setBlockState(center.add(-1, dy, -2), frame);
			dest.setBlockState(center.add(2, dy, -2), frame);
		}
		// Pre-lit 2x3 interior; FORCE_STATE skips shape updates so the pane doesn't
		// self-collapse while half-placed (mirrors vanilla AreaHelper's flag 18).
		BlockState portal = InfernoDimensionFeature.INFERNO_PORTAL.getDefaultState().with(AXIS, Direction.Axis.X);
		for (int dx = 0; dx <= 1; dx++) {
			for (int dy = 1; dy <= 3; dy++) {
				dest.setBlockState(center.add(dx, dy, -2), portal, Block.NOTIFY_LISTENERS | Block.FORCE_STATE);
			}
		}
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		// Mirrors vanilla NetherPortalBlock ambience: occasional portal hum + swirl particles.
		if (random.nextInt(100) == 0) {
			world.playSoundClient(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
					SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS,
					0.5f, random.nextFloat() * 0.4f + 0.8f, false);
		}
		for (int i = 0; i < 4; i++) {
			double x = pos.getX() + random.nextDouble();
			double y = pos.getY() + random.nextDouble();
			double z = pos.getZ() + random.nextDouble();
			double vx = (random.nextDouble() - 0.5) * 0.5;
			double vy = (random.nextDouble() - 0.5) * 0.5;
			double vz = (random.nextDouble() - 0.5) * 0.5;
			int j = random.nextInt(2) * 2 - 1;
			if (world.getBlockState(pos.west()).isOf(this) || world.getBlockState(pos.east()).isOf(this)) {
				z = pos.getZ() + 0.5 + 0.25 * j;
				vz = random.nextDouble() * 2.0 * j;
			} else {
				x = pos.getX() + 0.5 + 0.25 * j;
				vx = random.nextDouble() * 2.0 * j;
			}
			world.addParticleClient(ParticleTypes.PORTAL, x, y, z, vx, vy, vz);
		}
	}
}
