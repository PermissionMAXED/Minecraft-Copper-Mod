package net.sonic0810.copperinferno.feature.arsenal;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModSounds;

/**
 * "Signal Horn" — the field-army sibling of the Copper Whistle: the same brassy shared
 * sample (core ModSounds.COPPER_HORN_BLOW), but LOUD (3.0) and pitched way down (0.6) so it
 * carries across a battlefield. 4s cooldown.
 */
public class SignalHornItem extends Item {
	private static final int COOLDOWN_TICKS = 4 * 20;
	private static final float VOLUME = 3.0F;
	private static final float PITCH = 0.6F;

	public SignalHornItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient()) {
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					ModSounds.COPPER_HORN_BLOW, SoundCategory.PLAYERS, VOLUME, PITCH);
		}

		user.getItemCooldownManager().set(user.getStackInHand(hand), COOLDOWN_TICKS);
		return ActionResult.SUCCESS;
	}
}
