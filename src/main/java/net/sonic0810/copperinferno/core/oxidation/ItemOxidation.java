package net.sonic0810.copperinferno.core.oxidation;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.sonic0810.copperinferno.core.ModComponents;
import org.jetbrains.annotations.Nullable;

/**
 * THE shared item-oxidation system used by copper armor, tools and any other oxidizable item.
 * Each chain has 4 stages: unaffected (usually the vanilla item), exposed, weathered, oxidized.
 * Stage transitions use {@link ItemStack#withItem} so enchantments, damage, custom names and all
 * other components survive.
 */
public final class ItemOxidation {
	private ItemOxidation() {
	}

	/** Stage display order; index into this array is the stage index. */
	public static final String[] STAGE_TRANSLATION_KEYS = {
			"tooltip.copper_inferno.stage.unaffected",
			"tooltip.copper_inferno.stage.exposed",
			"tooltip.copper_inferno.stage.weathered",
			"tooltip.copper_inferno.stage.oxidized"
	};

	private static final Map<Item, Item> NEXT = new IdentityHashMap<>();
	private static final Map<Item, Item> PREVIOUS = new IdentityHashMap<>();
	private static final Map<Item, Integer> STAGE = new IdentityHashMap<>();

	/**
	 * Registers a 4-stage oxidation chain (stage 0 is typically the vanilla item).
	 *
	 * @throws IllegalStateException if any of the four items is already part of a chain
	 */
	public static void registerChain(Item unaffected, Item exposed, Item weathered, Item oxidized) {
		for (Item item : new Item[] {unaffected, exposed, weathered, oxidized}) {
			if (STAGE.containsKey(item)) {
				throw new IllegalStateException("Item already registered in an oxidation chain: " + item);
			}
		}
		NEXT.put(unaffected, exposed);
		NEXT.put(exposed, weathered);
		NEXT.put(weathered, oxidized);
		PREVIOUS.put(exposed, unaffected);
		PREVIOUS.put(weathered, exposed);
		PREVIOUS.put(oxidized, weathered);
		STAGE.put(unaffected, 0);
		STAGE.put(exposed, 1);
		STAGE.put(weathered, 2);
		STAGE.put(oxidized, 3);
	}

	/** The next (more oxidized) item in the chain, or null if fully oxidized / not in a chain. */
	@Nullable
	public static Item next(Item item) {
		return NEXT.get(item);
	}

	/** The previous (less oxidized) item in the chain, or null if unaffected / not in a chain. */
	@Nullable
	public static Item previous(Item item) {
		return PREVIOUS.get(item);
	}

	public static boolean isInChain(Item item) {
		return STAGE.containsKey(item);
	}

	/** Stage index 0-3 for chain items, or -1 if the item is not in any chain. */
	public static int stageIndex(Item item) {
		Integer stage = STAGE.get(item);
		return stage == null ? -1 : stage;
	}

	public static boolean isWaxed(ItemStack stack) {
		return stack.getOrDefault(ModComponents.WAXED, Boolean.FALSE);
	}

	public static void setWaxed(ItemStack stack, boolean waxed) {
		if (waxed) {
			stack.set(ModComponents.WAXED, Boolean.TRUE);
		} else {
			stack.remove(ModComponents.WAXED);
		}
	}

	/**
	 * Shared tooltip body for all oxidizable equipment items: the gray "Oxidation: &lt;Stage&gt;"
	 * line, the gray "Waxed" line, and (only while un-waxed and in a chain) a dark-gray hint
	 * about axe scraping / honeycomb waxing. Called from every oxidizable item class's
	 * {@code appendTooltip} override.
	 *
	 * <p>The "Waxed" line is only appended for {@link OxidizableEquipmentItem} stacks: the
	 * client-side ItemTooltipCallback in core.client.CoreClient already adds that line for every
	 * OTHER waxed stack (vanilla stage-0 items and the AxeItem/ShovelItem/HoeItem subclasses),
	 * so appending it here too would duplicate it.
	 */
	public static void appendOxidationTooltip(ItemStack stack, Consumer<Text> textConsumer) {
		int stage = stageIndex(stack.getItem());
		if (stage >= 0) {
			textConsumer.accept(Text.translatable("tooltip.copper_inferno.oxidation",
					Text.translatable(STAGE_TRANSLATION_KEYS[stage])).formatted(Formatting.GRAY));
		}
		if (isWaxed(stack)) {
			if (stack.getItem() instanceof OxidizableEquipmentItem) {
				textConsumer.accept(Text.translatable("tooltip.copper_inferno.waxed").formatted(Formatting.GRAY));
			}
		} else if (stage >= 0) {
			textConsumer.accept(Text.translatable("tooltip.copper_inferno.oxidation_hint").formatted(Formatting.DARK_GRAY));
		}
	}

	/**
	 * Returns a copy of the stack advanced to the next oxidation stage (components preserved).
	 * Returns the stack unchanged if there is no next stage.
	 */
	public static ItemStack toNextStage(ItemStack stack) {
		Item next = next(stack.getItem());
		return next == null ? stack : stack.withItem(next);
	}

	/**
	 * Returns a copy of the stack reverted to the previous oxidation stage (components preserved).
	 * Returns the stack unchanged if there is no previous stage.
	 */
	public static ItemStack toPreviousStage(ItemStack stack) {
		Item previous = previous(stack.getItem());
		return previous == null ? stack : stack.withItem(previous);
	}
}
