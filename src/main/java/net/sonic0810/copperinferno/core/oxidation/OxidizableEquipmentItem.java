package net.sonic0810.copperinferno.core.oxidation;

import java.util.function.Consumer;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * Base item for oxidizable equipment (armor pieces, tools, ...). Adds gray "Oxidation: &lt;Stage&gt;"
 * and "Waxed" tooltip lines. The vanilla stage-0 items get their "Waxed" line from the client-side
 * ItemTooltipCallback in core.client.CoreClient instead.
 */
public class OxidizableEquipmentItem extends Item {
	public OxidizableEquipmentItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		super.appendTooltip(stack, context, displayComponent, textConsumer, type);
		int stage = ItemOxidation.stageIndex(stack.getItem());
		if (stage >= 0) {
			textConsumer.accept(Text.translatable("tooltip.copper_inferno.oxidation",
					Text.translatable(ItemOxidation.STAGE_TRANSLATION_KEYS[stage])).formatted(Formatting.GRAY));
		}
		if (ItemOxidation.isWaxed(stack)) {
			textConsumer.accept(Text.translatable("tooltip.copper_inferno.waxed").formatted(Formatting.GRAY));
		}
	}
}
