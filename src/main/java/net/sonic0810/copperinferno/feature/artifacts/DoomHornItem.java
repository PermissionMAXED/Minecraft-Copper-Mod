package net.sonic0810.copperinferno.feature.artifacts;

import java.util.List;

import net.minecraft.entity.LivingEntity;
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
 * "Doom Horn" — a doom-alloy war horn that blasts the existing {@code ModSounds.DOOM_KICK}
 * (the mod's DOOM music kick sample) and radiates AoE FEAR: every other living creature
 * within 8 blocks gets Slowness II + Darkness for 8 seconds. Sculk-soul wisps mark the dread
 * wavefront. 15s cooldown, no durability (it's a horn).
 */
public class DoomHornItem extends Item {
	private static final int COOLDOWN_TICKS = 15 * 20;
	private static final int FEAR_TICKS = 8 * 20;
	private static final double FEAR_RADIUS = 8.0;

	public DoomHornItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					ModSounds.DOOM_KICK, SoundCategory.PLAYERS, 2.0F, 1.0F);

			// True-sphere fear pulse: everything alive but the hornblower is terrified.
			List<LivingEntity> targets = serverWorld.getEntitiesByClass(LivingEntity.class,
					user.getBoundingBox().expand(FEAR_RADIUS),
					e -> e.isAlive() && e != user && e.squaredDistanceTo(user) <= FEAR_RADIUS * FEAR_RADIUS);
			for (LivingEntity target : targets) {
				target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, FEAR_TICKS, 1));
				target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, FEAR_TICKS, 0));
			}

			serverWorld.spawnParticles(ParticleTypes.SCULK_SOUL,
					user.getX(), user.getBodyY(0.5), user.getZ(), 32, 3.0, 1.0, 3.0, 0.02);
			serverWorld.spawnParticles(ParticleTypes.LARGE_SMOKE,
					user.getX(), user.getBodyY(0.5), user.getZ(), 16, 2.0, 0.8, 2.0, 0.01);

			user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		}

		return ActionResult.SUCCESS;
	}
}
