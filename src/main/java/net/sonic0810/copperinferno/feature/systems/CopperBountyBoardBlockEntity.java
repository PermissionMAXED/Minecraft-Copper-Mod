package net.sonic0810.copperinferno.feature.systems;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Bounty state of one Copper Bounty Board: which bounty is posted and how many target mobs
 * have been slain so far. Persistence copies the statue block entity's ReadView/WriteView
 * pattern ({@code CopperPlayerStatueBlockEntity}).
 *
 * <p>The daily rotation: a freshly placed board (persisted index -1) posts the bounty picked
 * by the current world day, so all boards placed on the same day agree; right-clicking while
 * sneaking re-rolls to the next bounty on the list.
 *
 * <p>Kill tracking subscribes to Fabric's {@link ServerLivingEntityEvents#AFTER_DEATH}: every
 * loaded board within {@value #TRACK_RADIUS} blocks of the killing player counts a matching
 * kill. Loaded boards register themselves in {@link #setWorld} and deregister in
 * {@link #markRemoved} (no core-file edits needed).
 */
public class CopperBountyBoardBlockEntity extends BlockEntity {
	private static final String INDEX_KEY = "bounty_index";
	private static final String KILLS_KEY = "bounty_kills";
	private static final double TRACK_RADIUS = 64.0;

	/** One posted bounty: kill {@code required} of {@code type} for {@code rewardCoins} coins. */
	public record Bounty(EntityType<?> type, int required, int rewardCoins) {
	}

	public static final List<Bounty> BOUNTIES = List.of(
			new Bounty(EntityType.ZOMBIE, 5, 3),
			new Bounty(EntityType.SKELETON, 5, 3),
			new Bounty(EntityType.CREEPER, 3, 4),
			new Bounty(EntityType.SPIDER, 4, 2),
			new Bounty(EntityType.DROWNED, 4, 3),
			new Bounty(EntityType.WITCH, 2, 5),
			new Bounty(EntityType.ENDERMAN, 2, 6));

	/** All loaded boards (weak keys: unloaded block entities fall out on GC). */
	private static final Set<CopperBountyBoardBlockEntity> LOADED =
			Collections.newSetFromMap(new WeakHashMap<>());

	/** -1 = not initialized yet -> derive from the world day on first access. */
	private int bountyIndex = -1;
	private int kills;

	public CopperBountyBoardBlockEntity(BlockPos pos, BlockState state) {
		super(SystemsFeature.COPPER_BOUNTY_BOARD_BLOCK_ENTITY, pos, state);
	}

	/** Wires the global kill listener; called once from {@code SystemsFeature.init()}. */
	public static void initKillTracking() {
		ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
			if (!(damageSource.getAttacker() instanceof ServerPlayerEntity player)) {
				return;
			}
			for (CopperBountyBoardBlockEntity board : List.copyOf(LOADED)) {
				board.onKill(player, entity);
			}
		});
	}

	@Override
	public void setWorld(World world) {
		super.setWorld(world);
		if (world != null && !world.isClient()) {
			LOADED.add(this);
		}
	}

	@Override
	public void markRemoved() {
		super.markRemoved();
		LOADED.remove(this);
	}

	private void onKill(ServerPlayerEntity player, LivingEntity victim) {
		World world = this.getWorld();
		if (world == null || world != player.getEntityWorld()) {
			return;
		}
		BlockPos pos = this.getPos();
		if (player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5)
				> TRACK_RADIUS * TRACK_RADIUS) {
			return;
		}
		Bounty bounty = this.currentBounty();
		if (victim.getType() != bounty.type() || this.kills >= bounty.required()) {
			return;
		}
		this.kills++;
		this.markDirty();
		player.sendMessage(Text.translatable("message.copper_inferno.bounty_board.progress",
				bounty.type().getName(), this.kills, bounty.required()), true);
	}

	public Bounty currentBounty() {
		return BOUNTIES.get(this.currentIndex());
	}

	private int currentIndex() {
		if (this.bountyIndex < 0) {
			// Daily bounty: derive the posted bounty from the world day on first access.
			World world = this.getWorld();
			long day = world == null ? 0 : world.getTimeOfDay() / 24000L;
			this.bountyIndex = (int) (day % BOUNTIES.size());
			this.markDirty();
		}
		return this.bountyIndex % BOUNTIES.size();
	}

	public int getKills() {
		return this.kills;
	}

	public boolean isComplete() {
		return this.kills >= this.currentBounty().required();
	}

	/** Advances to the next posted bounty and resets progress. */
	public void cycleBounty() {
		this.bountyIndex = (this.currentIndex() + 1) % BOUNTIES.size();
		this.kills = 0;
		this.markDirty();
	}

	/** Resets progress after a payout and moves on to the next bounty. */
	public void completeAndAdvance() {
		this.cycleBounty();
	}

	@Override
	protected void writeData(WriteView view) {
		super.writeData(view);
		view.putInt(INDEX_KEY, this.bountyIndex);
		view.putInt(KILLS_KEY, this.kills);
	}

	@Override
	protected void readData(ReadView view) {
		super.readData(view);
		this.bountyIndex = view.getInt(INDEX_KEY, -1);
		this.kills = view.getInt(KILLS_KEY, 0);
	}
}
