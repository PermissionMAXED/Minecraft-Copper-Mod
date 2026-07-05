package net.sonic0810.copperinferno.feature.extras;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModSounds;

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
					ModSounds.COPPER_HORN_BLOW, SoundCategory.RECORDS, VOLUME, 1.0F);
		}

		user.getItemCooldownManager().set(user.getStackInHand(hand), COOLDOWN_TICKS);
		return ActionResult.SUCCESS;
	}
}
