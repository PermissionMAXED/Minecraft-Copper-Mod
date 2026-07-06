package net.sonic0810.copperinferno.feature.gear;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModSounds;

/**
 * "Throwing Fizz Can" — a shaken soda can that pops in place (modeled on
 * {@link net.sonic0810.copperinferno.feature.extras.FizzBombItem}), leaving a large fizzy
 * area-effect cloud that grants Jump Boost II + Speed I. Consumes one per use (unless
 * creative), 3s cooldown.
 */
public class ThrowingFizzCanItem extends Item {
	private static final int COOLDOWN_TICKS = 3 * 20;
	private static final int CLOUD_DURATION_TICKS = 6 * 20;
	private static final int EFFECT_DURATION_TICKS = 10 * 20;
	private static final float CLOUD_RADIUS = 3.5F;

	public ThrowingFizzCanItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					ModSounds.FIZZ_BOMB_POP, SoundCategory.PLAYERS, 1.0F, 1.2F);

			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(serverWorld, user.getX(), user.getY(), user.getZ());
			cloud.setOwner(user);
			cloud.setRadius(CLOUD_RADIUS);
			cloud.setDuration(CLOUD_DURATION_TICKS);
			cloud.setWaitTime(0);
			cloud.addEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, EFFECT_DURATION_TICKS, 1));
			cloud.addEffect(new StatusEffectInstance(StatusEffects.SPEED, EFFECT_DURATION_TICKS, 0));
			serverWorld.spawnEntity(cloud);

			serverWorld.spawnParticles(ParticleTypes.CLOUD,
					user.getX(), user.getBodyY(0.5), user.getZ(), 24, 1.0, 0.6, 1.0, 0.05);
		}

		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		stack.decrementUnlessCreative(1, user);
		return ActionResult.SUCCESS;
	}
}
