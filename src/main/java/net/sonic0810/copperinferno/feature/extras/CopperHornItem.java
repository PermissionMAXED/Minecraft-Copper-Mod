package net.sonic0810.copperinferno.feature.extras;

import java.util.function.Consumer;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModSounds;
import net.sonic0810.copperinferno.core.oxidation.ItemOxidation;

/**
 * "Copper Horn" — blows a LOUD brassy blast (volume 4.0 carries roughly 4x the normal 16-block
 * radius). 5s cooldown.
 */
public class CopperHornItem extends Item {
	private static final int COOLDOWN_TICKS = 5 * 20;
	private static final float VOLUME = 4.0F;

	public CopperHornItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient()) {
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					ModSounds.COPPER_HORN_BLOW, SoundCategory.PLAYERS, VOLUME, 1.0F);
		}

		user.getItemCooldownManager().set(user.getStackInHand(hand), COOLDOWN_TICKS);
		return ActionResult.SUCCESS;
	}

	/**
	 * Oxidation tooltip for all 4 horn stages via the shared helper (same pattern as
	 * {@code OxidizableAxeItem} & co). The "Waxed" line comes from the client-side
	 * ItemTooltipCallback in core.client.CoreClient, so it must NOT be appended here.
	 */
	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
		super.appendTooltip(stack, context, displayComponent, textConsumer, type);
		ItemOxidation.appendOxidationTooltip(stack, textConsumer);
	}
}
