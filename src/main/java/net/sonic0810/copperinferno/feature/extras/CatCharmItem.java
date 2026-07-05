package net.sonic0810.copperinferno.feature.extras;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * "Katzenliebhaber737's Cat Charm" — purrs like a cat, surrounds the user with hearts and grants
 * a short burst of feline agility (Speed I, 5s). 3s cooldown.
 */
public class CatCharmItem extends Item {
	private static final int COOLDOWN_TICKS = 3 * 20;
	private static final int SPEED_DURATION_TICKS = 5 * 20;

	public CatCharmItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		if (world instanceof ServerWorld serverWorld) {
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.ENTITY_CAT_PURREOW, SoundCategory.PLAYERS, 1.0F,
					0.9F + world.getRandom().nextFloat() * 0.2F);
			serverWorld.spawnParticles(ParticleTypes.HEART,
					user.getX(), user.getBodyY(0.7), user.getZ(),
					8, 0.6, 0.5, 0.6, 0.0);
			// Feline agility: Speed I for 5 seconds.
			user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, SPEED_DURATION_TICKS, 0));
		}

		user.getItemCooldownManager().set(user.getStackInHand(hand), COOLDOWN_TICKS);
		return ActionResult.SUCCESS;
	}
}
