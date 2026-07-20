package net.sonic0810.copperinferno.feature.artifacts;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;

/**
 * Stores up to {@link #CAPACITY} single drinks for the Brew Keg (LIFO: the last drink poured
 * in is the first tapped out). Persistence uses the 1.21.9 ReadView/WriteView contract with
 * {@code ItemStack.CODEC.listOf()}, mirroring {@code CopperPlayerStatueBlockEntity}. No
 * client sync needed — the keg has no renderer, its state is only touched server-side.
 */
public class BrewKegBlockEntity extends BlockEntity {
	public static final int CAPACITY = 16;
	private static final String DRINKS_KEY = "drinks";

	private final List<ItemStack> drinks = new ArrayList<>();

	public BrewKegBlockEntity(BlockPos pos, BlockState state) {
		super(ArtifactsFeature.BREW_KEG_BLOCK_ENTITY, pos, state);
	}

	public int getCount() {
		return this.drinks.size();
	}

	public boolean isFull() {
		return this.drinks.size() >= CAPACITY;
	}

	/** Stores one drink (a single-count stack); false when the keg is already full. */
	public boolean store(ItemStack drink) {
		if (this.isFull() || drink.isEmpty()) {
			return false;
		}
		this.drinks.add(drink);
		this.markDirty();
		return true;
	}

	/** Taps the most recently stored drink, or {@link ItemStack#EMPTY} when empty. */
	public ItemStack dispense() {
		if (this.drinks.isEmpty()) {
			return ItemStack.EMPTY;
		}
		ItemStack drink = this.drinks.remove(this.drinks.size() - 1);
		this.markDirty();
		return drink;
	}

	/** Removes and returns everything (used to drop the contents when the keg breaks). */
	public List<ItemStack> clearDrinks() {
		List<ItemStack> removed = new ArrayList<>(this.drinks);
		this.drinks.clear();
		this.markDirty();
		return removed;
	}

	@Override
	protected void writeData(WriteView view) {
		super.writeData(view);
		view.put(DRINKS_KEY, ItemStack.CODEC.listOf(), List.copyOf(this.drinks));
	}

	@Override
	protected void readData(ReadView view) {
		super.readData(view);
		this.drinks.clear();
		view.read(DRINKS_KEY, ItemStack.CODEC.listOf()).ifPresent(list -> {
			for (ItemStack stack : list) {
				if (!stack.isEmpty() && this.drinks.size() < CAPACITY) {
					this.drinks.add(stack);
				}
			}
		});
	}
}
