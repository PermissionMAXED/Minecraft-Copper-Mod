package net.sonic0810.copperinferno.feature.extras;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModSounds;

/**
 * "Fizz Bomb" — a shaken soda grenade. Pops in place (no block damage!), leaving a fizzy
 * area-effect cloud that grants Speed II for 10s. Consumes one per use (unless creative),
 * 2s cooldown.
 */
public class FizzBombItem extends Item {
	/** Dr.Pepper maroon, shared with the fizz dust particles. */
	private static final int MAROON = 0x5A0E14;
	private static final int COOLDOWN_TICKS = 2 * 20;
	private static final int SPEED_DURATION_TICKS = 10 * 20;
	private static final int CLOUD_DURATION_TICKS = 5 * 20;

	public FizzBombItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					ModSounds.FIZZ_BOMB_POP, SoundCategory.PLAYERS, 1.0F, 1.0F);

			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(serverWorld, user.getX(), user.getY(), user.getZ());
			cloud.setOwner(user);
			cloud.setRadius(3.0F);
			cloud.setDuration(CLOUD_DURATION_TICKS);
			cloud.setWaitTime(0);
			cloud.addEffect(new StatusEffectInstance(StatusEffects.SPEED, SPEED_DURATION_TICKS, 1));
			serverWorld.spawnEntity(cloud);

			double x = user.getX();
			double y = user.getBodyY(0.5);
			double z = user.getZ();
			serverWorld.spawnParticles(ParticleTypes.CLOUD, x, y, z, 24, 0.8, 0.6, 0.8, 0.05);
			serverWorld.spawnParticles(ParticleTypes.POOF, x, y, z, 12, 0.5, 0.4, 0.5, 0.02);
			serverWorld.spawnParticles(new DustParticleEffect(MAROON, 1.0F), x, y, z, 40, 1.2, 0.8, 1.2, 0.0);
		}

		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		stack.decrementUnlessCreative(1, user);
		return ActionResult.SUCCESS;
	}
}
