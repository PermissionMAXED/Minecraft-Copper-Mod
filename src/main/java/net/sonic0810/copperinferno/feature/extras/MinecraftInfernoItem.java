package net.sonic0810.copperinferno.feature.extras;

import java.util.function.Consumer;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * "Minecraft Inferno" — a display-only trophy item that is literally fire. It cannot be crafted
 * (no recipe file exists on purpose); it only shows up in the creative tab / recipe viewers as an
 * item with no source.
 */
public class MinecraftInfernoItem extends Item {
	public MinecraftInfernoItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		super.appendTooltip(stack, context, displayComponent, textConsumer, type);
		textConsumer.accept(Text.translatable("item.copper_inferno.minecraft_inferno.tooltip.fire").formatted(Formatting.GOLD));
		textConsumer.accept(Text.translatable("item.copper_inferno.minecraft_inferno.tooltip.uncraftable").formatted(Formatting.GRAY, Formatting.ITALIC));
	}
}
