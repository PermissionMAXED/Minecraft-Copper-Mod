package net.sonic0810.copperinferno.core.oxidation;

import java.util.function.Consumer;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

/**
 * Base item for oxidizable equipment without a dedicated vanilla item class (armor pieces,
 * swords, pickaxes, ...). Adds the shared oxidation tooltip lines via
 * {@link ItemOxidation#appendOxidationTooltip}. The vanilla stage-0 items get their "Waxed" line
 * from the client-side ItemTooltipCallback in core.client.CoreClient instead.
 *
 * <p>Axes/shovels/hoes use {@link OxidizableAxeItem}/{@link OxidizableShovelItem}/
 * {@link OxidizableHoeItem} instead so they keep strip/path/till behavior; swords and pickaxes
 * have no vanilla item class in 1.21.9 (their behavior is component-driven), so they stay here.
 */
public class OxidizableEquipmentItem extends Item {
	public OxidizableEquipmentItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		super.appendTooltip(stack, context, displayComponent, textConsumer, type);
		ItemOxidation.appendOxidationTooltip(stack, textConsumer);
	}
}
