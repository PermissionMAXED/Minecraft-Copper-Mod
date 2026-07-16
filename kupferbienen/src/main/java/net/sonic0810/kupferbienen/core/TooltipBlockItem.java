package net.sonic0810.kupferbienen.core;

import java.util.function.Consumer;

import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * {@link BlockItem} that appends one gray translatable tooltip line per key. There is no
 * tooltip hook on Block/BlockItem in 1.21.9, only
 * {@code Item.appendTooltip(ItemStack, TooltipContext, TooltipDisplayComponent, Consumer,
 * TooltipType)} (verified via javap), hence this item-level subclass.
 */
public class TooltipBlockItem extends BlockItem {
	private final String[] tooltipKeys;

	public TooltipBlockItem(Block block, Settings settings, String... tooltipKeys) {
		super(block, settings);
		this.tooltipKeys = tooltipKeys;
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context,
			TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		super.appendTooltip(stack, context, displayComponent, textConsumer, type);
		for (String key : this.tooltipKeys) {
			textConsumer.accept(Text.translatable(key).formatted(Formatting.GRAY));
		}
	}
}
