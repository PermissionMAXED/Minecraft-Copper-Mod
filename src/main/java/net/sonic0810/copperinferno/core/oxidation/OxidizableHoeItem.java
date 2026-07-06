package net.sonic0810.copperinferno.core.oxidation;

import java.util.function.Consumer;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

/**
 * Oxidizable hoe: keeps vanilla {@link HoeItem} behavior (tilling) and adds the shared oxidation
 * tooltip lines. Like vanilla, the {@link HoeItem} constructor applies
 * {@code Item.Settings.hoe(...)} itself, so callers pass plain settings.
 */
public class OxidizableHoeItem extends HoeItem {
	public OxidizableHoeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		super.appendTooltip(stack, context, displayComponent, textConsumer, type);
		ItemOxidation.appendOxidationTooltip(stack, textConsumer);
	}
}
