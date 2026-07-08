package net.sonic0810.copperinferno.feature.infernodim;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Portal;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import net.sonic0810.copperinferno.core.ModDimensions;

/**
 * The Inferno portal interior block. Mirrors {@link net.minecraft.block.NetherPortalBlock}:
 * an AXIS-oriented flat pane that implements {@link Portal} — entities colliding with it call
 * {@link Entity#tryUsePortal}, and the vanilla portal plumbing then invokes
 * {@link #createTeleportTarget} (Overworld &lt;-&gt; Inferno, 1:1 x/z clamped to the
 * destination world border, like vanilla's {@code WorldBorder.clampFloored} call).
 *
 * <p>Arrival resolution: the destination is first scanned (horizontal radius
 * {@link #PORTAL_SEARCH_RADIUS} around the 1:1 target, over the destination's FULL valid Y
 * range) for the nearest existing inferno portal; if one is found the entity arrives standing
 * in its lowest pane cell and no blocks are placed or modified. Only when no portal exists is
 * a 5x5 cinderstone platform with a pre-lit 4x5 return frame built — at a safe Y: scan down
 * from {@link #PLATFORM_Y} for solid ground with a clear 5x5x6 volume, else offset upward
 * from y=70 until the volume is clear (capped at the logical build height), else fall back to
 * a y=70 build. The entity arrives inside the new pane's bottom interior cell, so later trips
 * re-find this exact portal via the scan and arrival coordinates do not drift. The final
 * arrival cell is always validated: two stacked passable blocks (air or portal pane) over
 * non-air, non-fluid support, adjusted to the nearest safe cell if needed.
 *
 * <p>Collapse behaviour: every in-plane neighbor (vertical, or horizontal along the portal's
 * AXIS) must be another portal pane on the SAME axis or an infernium portal frame block,
 * otherwise the portal pane turns to air (which cascades through the whole pane, mirroring
 * vanilla's AreaHelper re-validation in {@code NetherPortalBlock.getStateForNeighborUpdate}).
 */
public class InfernoPortalBlock extends Block implements Portal {
	public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;

	/** Preferred/fallback Y level of the arrival platform built in the destination dimension. */
	public static final int PLATFORM_Y = 70;

	/** Horizontal radius scanned around the 1:1 target for an existing return portal. */
	private static final int PORTAL_SEARCH_RADIUS = 16;

	/** Platform footprint radius (5x5) and volume height (floor + 5 blocks of clearance). */
	private static final int PLATFORM_RADIUS = 2;
	private static final int PLATFORM_HEIGHT = 6;

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
		// A neighboring pane only counts as support when it shares this pane's axis, so
		// perpendicular panes crossing at a corner cannot prop each other up.
		boolean supported = (neighborState.isOf(this) && neighborState.get(AXIS) == portalAxis)
				|| neighborState.isOf(InfernoDimensionFeature.INFERNIUM_PORTAL_FRAME);
		if (inPlane && !supported) {
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
		// Mirrors NetherPortalBlock.getPortalDelay (verified via javap -c): players wait out
		// the nether-portal game-rule delay (creative variant when invulnerable, i.e. the
		// confusion effect has time to build up), all other entities teleport instantly.
		if (entity instanceof PlayerEntity player) {
			return Math.max(0, world.getGameRules().getInt(player.getAbilities().invulnerable
					? GameRules.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY
					: GameRules.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY));
		}
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
		// 1:1 x/z, clamped into the destination world border (same WorldBorder.clampFloored
		// call vanilla NetherPortalBlock.createTeleportTarget makes) and valid Y range.
		int minY = dest.getBottomY();
		int maxY = Math.min(minY + dest.getLogicalHeight() - 1, dest.getTopYInclusive());
		BlockPos clamped = dest.getWorldBorder().clampFloored(entity.getX(), entity.getY(), entity.getZ());
		BlockPos target = new BlockPos(clamped.getX(), MathHelper.clamp(clamped.getY(), minY, maxY), clamped.getZ());

		// 1) Reuse the nearest existing portal; never build or modify blocks in that case.
		BlockPos existing = findNearestPortal(dest, target, minY, maxY);
		if (existing != null) {
			return targetAt(dest, entity, lowestPaneCell(dest, existing));
		}

		// 2) No portal nearby: build the arrival platform at a safe Y. Arrival is the pane's
		// bottom-left interior cell, so subsequent trips re-find this portal via step 1.
		BlockPos center = choosePlatformCenter(dest, target.getX(), target.getZ());
		buildArrivalPlatform(dest, center);
		return targetAt(dest, entity, center.add(0, 1, -2));
	}

	@Override
	public Portal.Effect getPortalEffect() {
		return Portal.Effect.CONFUSION;
	}

	/** Validates/adjusts the arrival cell and wraps it in a {@link TeleportTarget}. */
	private static TeleportTarget targetAt(ServerWorld dest, Entity entity, BlockPos cell) {
		BlockPos safe = validateArrival(dest, cell);
		Vec3d arrival = new Vec3d(safe.getX() + 0.5, safe.getY(), safe.getZ() + 0.5);
		return new TeleportTarget(dest, arrival, Vec3d.ZERO, entity.getYaw(), entity.getPitch(),
				TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET);
	}

	/**
	 * Nearest inferno portal block within {@link #PORTAL_SEARCH_RADIUS} horizontally of
	 * {@code target}, scanning the destination's FULL valid Y range ({@code minY..maxY}), so
	 * e.g. a ground-level y~63 Overworld return portal is found from a y=70 platform target.
	 */
	private static BlockPos findNearestPortal(ServerWorld dest, BlockPos target, int minY, int maxY) {
		BlockPos best = null;
		double bestDistSq = Double.MAX_VALUE;
		for (BlockPos p : BlockPos.iterate(
				target.getX() - PORTAL_SEARCH_RADIUS, minY, target.getZ() - PORTAL_SEARCH_RADIUS,
				target.getX() + PORTAL_SEARCH_RADIUS, maxY, target.getZ() + PORTAL_SEARCH_RADIUS)) {
			if (dest.getBlockState(p).isOf(InfernoDimensionFeature.INFERNO_PORTAL)) {
				double distSq = p.getSquaredDistance(target);
				if (distSq < bestDistSq) {
					bestDistSq = distSq;
					best = p.toImmutable(); // iterate() reuses one mutable pos
				}
			}
		}
		return best;
	}

	/** Walks down a pane column to its lowest portal cell (the frame sits below that). */
	private static BlockPos lowestPaneCell(ServerWorld dest, BlockPos paneBlock) {
		BlockPos cell = paneBlock;
		while (cell.getY() > dest.getBottomY()
				&& dest.getBlockState(cell.down()).isOf(InfernoDimensionFeature.INFERNO_PORTAL)) {
			cell = cell.down();
		}
		return cell;
	}

	/**
	 * Picks the platform center Y for a fresh build at {@code x/z} (already border-clamped):
	 * scan down from {@link #PLATFORM_Y} for solid, non-fluid ground with a clear 5x5x6
	 * volume above it; if blocks (terrain, player builds, lava sea) are in the way, offset
	 * upward from y=70 until the volume is clear, capped at the logical build height; as the
	 * last resort fall back to the historic y=70 build.
	 */
	private static BlockPos choosePlatformCenter(ServerWorld dest, int x, int z) {
		int minCenterY = dest.getBottomY() + 1; // needs a support block below the floor
		int maxCenterY = Math.min(dest.getBottomY() + dest.getLogicalHeight() - 1, dest.getTopYInclusive())
				- (PLATFORM_HEIGHT - 1);
		for (int y = Math.min(PLATFORM_Y, maxCenterY); y >= minCenterY; y--) {
			BlockPos center = new BlockPos(x, y, z);
			if (hasSolidSupport(dest, center) && isVolumeClear(dest, center)) {
				return center;
			}
		}
		for (int y = PLATFORM_Y; y <= maxCenterY; y++) {
			BlockPos center = new BlockPos(x, y, z);
			if (isVolumeClear(dest, center)) {
				return center;
			}
		}
		return new BlockPos(x, PLATFORM_Y, z);
	}

	/** Solid, non-air, non-fluid block right under the platform's center floor cell. */
	private static boolean hasSolidSupport(ServerWorld dest, BlockPos center) {
		BlockState below = dest.getBlockState(center.down());
		return !below.isAir() && below.getFluidState().isEmpty() && below.blocksMovement();
	}

	/**
	 * True when placing the 5x5x6 platform volume at {@code center} would only overwrite air
	 * or replaceable dry blocks (grass, snow, ...) — never player builds, terrain, or fluids
	 * (fluid blocks count as replaceable but building an air pocket in a lava sea is unsafe).
	 */
	private static boolean isVolumeClear(ServerWorld dest, BlockPos center) {
		for (BlockPos p : BlockPos.iterate(center.add(-PLATFORM_RADIUS, 0, -PLATFORM_RADIUS),
				center.add(PLATFORM_RADIUS, PLATFORM_HEIGHT - 1, PLATFORM_RADIUS))) {
			BlockState state = dest.getBlockState(p);
			if (!state.isAir() && !(state.isReplaceable() && state.getFluidState().isEmpty())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Ensures the arrival cell has two stacked passable blocks (air or portal pane) over
	 * non-air, non-fluid support; otherwise the nearest safe cell (deterministic outward
	 * search across the platform footprint) is used, falling back to the requested cell.
	 */
	private static BlockPos validateArrival(ServerWorld dest, BlockPos cell) {
		if (isSafeArrival(dest, cell)) {
			return cell;
		}
		for (BlockPos p : BlockPos.iterateOutwards(cell, PLATFORM_RADIUS, 3, PLATFORM_RADIUS)) {
			if (isSafeArrival(dest, p)) {
				return p.toImmutable();
			}
		}
		return cell;
	}

	private static boolean isSafeArrival(ServerWorld dest, BlockPos cell) {
		BlockState feet = dest.getBlockState(cell);
		BlockState head = dest.getBlockState(cell.up());
		BlockState below = dest.getBlockState(cell.down());
		boolean passable = (feet.isAir() || feet.isOf(InfernoDimensionFeature.INFERNO_PORTAL))
				&& (head.isAir() || head.isOf(InfernoDimensionFeature.INFERNO_PORTAL));
		return passable && !below.isAir() && below.getFluidState().isEmpty();
	}

	/**
	 * Builds a 5x5 cinderstone platform centered at {@code center} plus a pre-lit 4-wide x
	 * 5-tall return frame (X axis) on its north edge. Only called when
	 * {@link #findNearestPortal} found no existing portal to reuse.
	 */
	private static void buildArrivalPlatform(ServerWorld dest, BlockPos center) {
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
