package net.sonic0810.copperinferno.core.oxidation;

import java.util.function.Consumer;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

/**
 * Oxidizable axe: keeps vanilla {@link AxeItem} behavior (log stripping, copper scraping,
 * wax-off) and adds the shared oxidation tooltip lines. Like vanilla, the {@link AxeItem}
 * constructor applies {@code Item.Settings.axe(...)} itself, so callers pass plain settings.
 */
public class OxidizableAxeItem extends AxeItem {
	public OxidizableAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		super.appendTooltip(stack, context, displayComponent, textConsumer, type);
		ItemOxidation.appendOxidationTooltip(stack, textConsumer);
	}
}
