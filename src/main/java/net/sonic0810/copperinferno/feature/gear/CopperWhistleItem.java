package net.sonic0810.copperinferno.feature.gear;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModSounds;

/**
 * "Copper Whistle" — a shrill little sibling of the Copper Horn: same brassy sample, but
 * quieter (1.5) and pitched way up (1.8). 1s cooldown.
 */
public class CopperWhistleItem extends Item {
	private static final int COOLDOWN_TICKS = 20;
	private static final float VOLUME = 1.5F;
	private static final float PITCH = 1.8F;

	public CopperWhistleItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient()) {
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					ModSounds.COPPER_HORN_BLOW, SoundCategory.RECORDS, VOLUME, PITCH);
		}

		user.getItemCooldownManager().set(user.getStackInHand(hand), COOLDOWN_TICKS);
		return ActionResult.SUCCESS;
	}
}
