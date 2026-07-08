package net.sonic0810.copperinferno.feature.infernofx;

import java.util.function.Consumer;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * "Cinder Compass" — a tooltip-only flavor item, documented as such: the needle is pure
 * decoration and performs NO tracking, pointing or use behavior. It exists as an Inferno
 * souvenir/crafting curiosity; the two tooltip lines explain that in-game.
 */
public class CinderCompassItem extends Item {
	public CinderCompassItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		super.appendTooltip(stack, context, displayComponent, textConsumer, type);
		textConsumer.accept(Text.translatable("item.copper_inferno.cinder_compass.tooltip.flavor").formatted(Formatting.GOLD));
		textConsumer.accept(Text.translatable("item.copper_inferno.cinder_compass.tooltip.note").formatted(Formatting.GRAY, Formatting.ITALIC));
	}
}
