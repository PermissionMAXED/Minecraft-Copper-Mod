package net.sonic0810.copperinferno.feature.infernofx;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * "Ash Talisman" — crush it (use) for 30s of heat-ward-style protection.
 *
 * <p>Documented simplification: instead of a dedicated short-lived protection effect, the
 * talisman simply applies the existing {@link HeatWardEffect Heat Ward} status effect for
 * 30 seconds. The talisman crumbles away on use (one is consumed unless in creative); a short
 * 5s cooldown prevents accidental double-crushes.
 */
public class AshTalismanItem extends Item {
	private static final int EFFECT_DURATION_TICKS = 30 * 20;
	private static final int COOLDOWN_TICKS = 5 * 20;

	public AshTalismanItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			user.addStatusEffect(new StatusEffectInstance(InfernoFxFeature.HEAT_WARD, EFFECT_DURATION_TICKS, 0));
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.6F, 0.9F);
			serverWorld.spawnParticles(InfernoFxFeature.ASH_FALL,
					user.getX(), user.getBodyY(0.8), user.getZ(), 16, 0.4, 0.5, 0.4, 0.02);
		}

		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		stack.decrementUnlessCreative(1, user);
		return ActionResult.SUCCESS;
	}
}
