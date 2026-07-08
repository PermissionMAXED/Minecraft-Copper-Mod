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
 * "Heat Ward Charm" — use to wrap yourself in {@link HeatWardEffect Heat Ward} for 120s.
 * The charm is not consumed; a 60s cooldown (set via {@code ItemCooldownManager.set(ItemStack,
 * int)}, the same pattern as the v2 gear gadgets) keeps it from being spammed.
 */
public class HeatWardCharmItem extends Item {
	private static final int EFFECT_DURATION_TICKS = 120 * 20;
	private static final int COOLDOWN_TICKS = 60 * 20;

	public HeatWardCharmItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			user.addStatusEffect(new StatusEffectInstance(InfernoFxFeature.HEAT_WARD, EFFECT_DURATION_TICKS, 0));
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.8F, 1.4F);
			serverWorld.spawnParticles(InfernoFxFeature.EMBER_SPARK,
					user.getX(), user.getBodyY(0.5), user.getZ(), 12, 0.4, 0.6, 0.4, 0.02);
		}

		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		return ActionResult.SUCCESS;
	}
}
