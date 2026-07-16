package net.sonic0810.kupferbienen.feature.bees;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * The Kupferstock's brain: every 20 ticks it counts the living Kupferbienen/Gruenspanbienen
 * within 4 blocks and, if at least one Kupferbluete grows within 4 blocks, adds
 * {@code 20 * min(beeCount, 5)} work units. At {@value #WORK_TARGET} units it produces one
 * Kupferwabe (20% chance of one Gruenspanpollen instead when a Gruenspanbiene contributed to
 * the cycle) into the first free slot of a 3-slot inventory.
 *
 * <p>Anti-stacking: a bee contributes to at most ONE apiary per scan — this block entity only
 * counts a bee if it is the NEAREST Kupferstock within the bee's reach (ties broken by
 * {@code BlockPos.asLong()}), see {@link #isNearestKupferstock}.
 *
 * <p>{@link SidedInventory}: extraction is allowed from every side for everything, insertion
 * never (verified signatures: getAvailableSlots/canInsert/canExtract) — so a hopper below
 * drains the produce but nothing can be piped in.
 *
 * <p>NBT uses the 1.21.9 ReadView/WriteView contract (statue BE pattern);
 * {@code Inventories.writeData(WriteView, DefaultedList)} stores the stacks under the vanilla
 * {@code Items} key.
 */
public class KupferstockBlockEntity extends BlockEntity implements SidedInventory {
	public static final int SLOT_COUNT = 3;
	/** Work units per finished product; 5 bees close a cycle in 6 seconds. */
	public static final int WORK_TARGET = 600;
	private static final int SCAN_INTERVAL_TICKS = 20;
	private static final int MAX_COUNTED_BEES = 5;
	private static final double RANGE = 4.0;
	private static final float GRUENSPAN_CHANCE = 0.2f;
	private static final int[] ALL_SLOTS = {0, 1, 2};
	private static final String PROGRESS_KEY = "progress";
	private static final String GRUENSPAN_KEY = "gruenspan_contributed";

	private final DefaultedList<ItemStack> items = DefaultedList.ofSize(SLOT_COUNT, ItemStack.EMPTY);
	private int progress;
	/** Whether a Gruenspanbiene was seen during the current work cycle. */
	private boolean gruenspanContributed;
	private int scanCooldown = SCAN_INTERVAL_TICKS;

	public KupferstockBlockEntity(BlockPos pos, BlockState state) {
		super(BeesFeature.KUPFERSTOCK_BLOCK_ENTITY, pos, state);
	}

	public static void serverTick(World world, BlockPos pos, BlockState state, KupferstockBlockEntity stock) {
		if (--stock.scanCooldown > 0) {
			return;
		}
		stock.scanCooldown = SCAN_INTERVAL_TICKS;

		List<BeeEntity> bees = world.getEntitiesByClass(BeeEntity.class, new Box(pos).expand(RANGE),
				bee -> bee.isAlive() && (bee instanceof KupferbieneEntity || bee instanceof GruenspanbieneEntity)
						&& stock.isNearestKupferstock(world, bee));
		if (bees.isEmpty() || !hasKupferblueteNearby(world, pos)) {
			return;
		}
		boolean dirty = false;
		if (!stock.gruenspanContributed && bees.stream().anyMatch(bee -> bee instanceof GruenspanbieneEntity)) {
			stock.gruenspanContributed = true;
			dirty = true;
		}
		int oldProgress = stock.progress;
		stock.progress += SCAN_INTERVAL_TICKS * Math.min(bees.size(), MAX_COUNTED_BEES);
		if (stock.progress >= WORK_TARGET) {
			ItemStack product = stock.gruenspanContributed && world.getRandom().nextFloat() < GRUENSPAN_CHANCE
					? new ItemStack(BeesFeature.GRUENSPANPOLLEN)
					: new ItemStack(BeesFeature.KUPFERWABE);
			if (stock.insert(product)) {
				stock.progress = 0;
				stock.gruenspanContributed = false;
				dirty = true;
				if (world instanceof ServerWorld serverWorld) {
					serverWorld.spawnParticles(ParticleTypes.WAX_ON,
							pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
							8, 0.3, 0.2, 0.3, 0.0);
					serverWorld.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_WORK,
							SoundCategory.BLOCKS, 1.0F, 1.0F);
				}
			} else {
				// Inventory full: hold the finished cycle until a slot frees up. NOT dirty —
				// held-at-target is already the persisted state, so no markDirty every second.
				stock.progress = WORK_TARGET;
			}
		}
		if (stock.progress != oldProgress) {
			dirty = true;
		}
		if (dirty) {
			stock.markDirty();
		}
	}

	/**
	 * True when this block entity is the nearest Kupferstock within the bee's reach, so each
	 * bee contributes to at most one apiary per scan. Radius is ±5 because the entity scan
	 * ({@code new Box(pos).expand(4)}) reaches past 4 blocks at box corners. Ties are broken
	 * deterministically by {@code BlockPos.asLong()}. {@code BlockPos.iterate} reuses a
	 * Mutable — fine here, positions are only compared, never stored.
	 */
	private boolean isNearestKupferstock(World world, BeeEntity bee) {
		Vec3d beePos = bee.getEntityPos();
		double selfDist = this.pos.getSquaredDistance(beePos);
		BlockPos beeBlock = bee.getBlockPos();
		for (BlockPos p : BlockPos.iterate(beeBlock.add(-5, -5, -5), beeBlock.add(5, 5, 5))) {
			if (p.equals(this.pos) || !world.getBlockState(p).isOf(BeesFeature.KUPFERSTOCK)) {
				continue;
			}
			double d = p.getSquaredDistance(beePos);
			if (d < selfDist || (d == selfDist && p.asLong() < this.pos.asLong())) {
				return false;
			}
		}
		return true;
	}

	private static boolean hasKupferblueteNearby(World world, BlockPos pos) {
		for (BlockPos p : BlockPos.iterate(pos.add(-(int) RANGE, -(int) RANGE, -(int) RANGE),
				pos.add((int) RANGE, (int) RANGE, (int) RANGE))) {
			if (world.getBlockState(p).isOf(BeesFeature.KUPFERBLUETE)) {
				return true;
			}
		}
		return false;
	}

	/** Adds one item to the first slot that can take it; false when everything is full. */
	private boolean insert(ItemStack product) {
		for (int slot = 0; slot < SLOT_COUNT; slot++) {
			ItemStack existing = this.items.get(slot);
			if (existing.isEmpty()) {
				this.items.set(slot, product);
				return true;
			}
			if (ItemStack.areItemsAndComponentsEqual(existing, product)
					&& existing.getCount() < existing.getMaxCount()) {
				existing.increment(product.getCount());
				return true;
			}
		}
		return false;
	}

	/** Removes and returns the first stored stack (empty stack when nothing is stored). */
	public ItemStack ejectFirstStack() {
		for (int slot = 0; slot < SLOT_COUNT; slot++) {
			if (!this.items.get(slot).isEmpty()) {
				ItemStack stack = Inventories.removeStack(this.items, slot);
				this.markDirty();
				return stack;
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	protected void writeData(WriteView view) {
		super.writeData(view);
		view.putInt(PROGRESS_KEY, this.progress);
		view.putBoolean(GRUENSPAN_KEY, this.gruenspanContributed);
		Inventories.writeData(view, this.items);
	}

	@Override
	protected void readData(ReadView view) {
		super.readData(view);
		this.progress = view.getInt(PROGRESS_KEY, 0);
		this.gruenspanContributed = view.getBoolean(GRUENSPAN_KEY, false);
		this.items.clear();
		Inventories.readData(view, this.items);
	}

	// --- Inventory ---

	@Override
	public int size() {
		return SLOT_COUNT;
	}

	@Override
	public boolean isEmpty() {
		return this.items.stream().allMatch(ItemStack::isEmpty);
	}

	@Override
	public ItemStack getStack(int slot) {
		return this.items.get(slot);
	}

	@Override
	public ItemStack removeStack(int slot, int amount) {
		ItemStack stack = Inventories.splitStack(this.items, slot, amount);
		if (!stack.isEmpty()) {
			this.markDirty();
		}
		return stack;
	}

	@Override
	public ItemStack removeStack(int slot) {
		return Inventories.removeStack(this.items, slot);
	}

	@Override
	public void setStack(int slot, ItemStack stack) {
		this.items.set(slot, stack);
		stack.capCount(this.getMaxCount(stack));
		this.markDirty();
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return Inventory.canPlayerUse(this, player);
	}

	@Override
	public void clear() {
		this.items.clear();
	}

	// --- SidedInventory: hoppers may extract everything, insert nothing ---

	@Override
	public int[] getAvailableSlots(Direction side) {
		return ALL_SLOTS;
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, Direction dir) {
		return false;
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return true;
	}
}
