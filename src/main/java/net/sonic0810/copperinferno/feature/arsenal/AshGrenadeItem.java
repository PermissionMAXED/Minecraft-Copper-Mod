package net.sonic0810.copperinferno.feature.arsenal;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

/**
 * "Ash Grenade" — a packed shell of ash and blast powder hurled ahead of the player (same
 * deliberate eye-ray raycast as ThrowingFizzCanItem, no ThrownItemEntity). It bursts on the
 * first block hit (or at 10 blocks) into a choking ash cloud that blinds and slows anything
 * standing in it. Consumes one per use (unless creative), 3s cooldown.
 */
public class AshGrenadeItem extends Item {
	private static final int COOLDOWN_TICKS = 3 * 20;
	private static final int CLOUD_DURATION_TICKS = 5 * 20;
	private static final int EFFECT_DURATION_TICKS = 6 * 20;
	private static final float CLOUD_RADIUS = 3.0F;
	private static final double THROW_DISTANCE = 10.0;

	public AshGrenadeItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			Vec3d start = user.getEyePos();
			Vec3d end = start.add(user.getRotationVec(1.0F).multiply(THROW_DISTANCE));
			BlockHitResult hit = world.raycast(new RaycastContext(start, end,
					RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user));
			Vec3d landing = hit.getType() == HitResult.Type.MISS ? end : hit.getPos();

			world.playSound(null, landing.x, landing.y, landing.z,
					SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 0.8F);

			// Smoke trail along the flight path.
			Vec3d path = landing.subtract(start);
			int steps = Math.max(1, (int) (path.length() * 2.0));
			for (int i = 1; i <= steps; i++) {
				Vec3d point = start.add(path.multiply((double) i / steps));
				serverWorld.spawnParticles(ParticleTypes.SMOKE, point.x, point.y, point.z,
						1, 0.05, 0.05, 0.05, 0.0);
			}

			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(serverWorld, landing.x, landing.y, landing.z);
			cloud.setOwner(user);
			cloud.setRadius(CLOUD_RADIUS);
			cloud.setDuration(CLOUD_DURATION_TICKS);
			cloud.setWaitTime(0);
			cloud.addEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, EFFECT_DURATION_TICKS, 0));
			cloud.addEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, EFFECT_DURATION_TICKS, 1));
			serverWorld.spawnEntity(cloud);

			serverWorld.spawnParticles(ParticleTypes.LARGE_SMOKE,
					landing.x, landing.y + 0.4, landing.z, 20, 1.0, 0.5, 1.0, 0.02);
		}

		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		stack.decrementUnlessCreative(1, user);
		return ActionResult.SUCCESS;
	}
}
