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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModSounds;

/**
 * "Throwing Fizz Can" — a shaken soda can hurled ahead of the player: it pops where the
 * player is looking (up to 8 blocks away, stopped early by the first block hit), leaving a
 * large fizzy area-effect cloud that grants Jump Boost II + Speed I, plus a fizzy particle
 * trail along the flight path. Consumes one per use (unless creative), 3s cooldown.
 *
 * <p>Deliberately implemented as an eye-ray raycast rather than a true ThrownItemEntity —
 * a real projectile entity was intentionally NOT added in v2.1 to keep the fix minimal.
 */
public class ThrowingFizzCanItem extends Item {
	private static final int COOLDOWN_TICKS = 3 * 20;
	private static final int CLOUD_DURATION_TICKS = 6 * 20;
	private static final int EFFECT_DURATION_TICKS = 10 * 20;
	private static final float CLOUD_RADIUS = 3.5F;
	/** How far (in blocks) the can flies along the player's look vector. */
	private static final double THROW_DISTANCE = 8.0;

	public ThrowingFizzCanItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			// Raycast from the eyes along the look vector; the can lands on the first block hit
			// or at max distance (RaycastContext + World.raycast verified via javap).
			Vec3d start = user.getEyePos();
			Vec3d end = start.add(user.getRotationVec(1.0F).multiply(THROW_DISTANCE));
			BlockHitResult hit = world.raycast(new RaycastContext(start, end,
					RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user));
			Vec3d landing = hit.getType() == HitResult.Type.MISS ? end : hit.getPos();

			world.playSound(null, landing.x, landing.y, landing.z,
					ModSounds.FIZZ_BOMB_POP, SoundCategory.PLAYERS, 1.0F, 1.2F);

			// Small fizzy trail along the flight path.
			Vec3d path = landing.subtract(start);
			int steps = Math.max(1, (int) (path.length() * 2.0));
			for (int i = 1; i <= steps; i++) {
				Vec3d point = start.add(path.multiply((double) i / steps));
				serverWorld.spawnParticles(ParticleTypes.SPLASH, point.x, point.y, point.z,
						2, 0.05, 0.05, 0.05, 0.0);
			}

			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(serverWorld, landing.x, landing.y, landing.z);
			cloud.setOwner(user);
			cloud.setRadius(CLOUD_RADIUS);
			cloud.setDuration(CLOUD_DURATION_TICKS);
			cloud.setWaitTime(0);
			cloud.addEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, EFFECT_DURATION_TICKS, 1));
			cloud.addEffect(new StatusEffectInstance(StatusEffects.SPEED, EFFECT_DURATION_TICKS, 0));
			serverWorld.spawnEntity(cloud);

			serverWorld.spawnParticles(ParticleTypes.CLOUD,
					landing.x, landing.y, landing.z, 24, 1.0, 0.6, 1.0, 0.05);
		}

		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		stack.decrementUnlessCreative(1, user);
		return ActionResult.SUCCESS;
	}
}
