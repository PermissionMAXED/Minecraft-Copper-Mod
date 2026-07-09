package net.sonic0810.copperinferno.feature.arsenal;

import java.util.function.Consumer;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * Arsenal crafting material — a plain item plus one gray/italic flavor tooltip line. The
 * tooltip key is derived from the item's own translation key
 * ({@code item.copper_inferno.<id>.tooltip}), same pattern as CinderCompassItem; the lang
 * fragments are emitted by {@code devtools/gen/arsenal_gen.py}.
 */
public class ArsenalMaterialItem extends Item {
	public ArsenalMaterialItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		super.appendTooltip(stack, context, displayComponent, textConsumer, type);
		textConsumer.accept(Text.translatable(getTranslationKey() + ".tooltip").formatted(Formatting.GRAY, Formatting.ITALIC));
	}
}
