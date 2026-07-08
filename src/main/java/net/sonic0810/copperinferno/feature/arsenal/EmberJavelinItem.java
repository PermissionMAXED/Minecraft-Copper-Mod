package net.sonic0810.copperinferno.feature.arsenal;

import java.util.List;

import net.minecraft.entity.LivingEntity;
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
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

/**
 * "Ember Javelin" — a red-hot throwing spear. Like the v2/v3 throwables (ThrowingFizzCanItem,
 * SlagBombItem) this is deliberately an eye-ray raycast rather than a real ThrownItemEntity:
 * the first living entity along the player's look ray (up to 16 blocks, blocked by terrain)
 * takes 7 damage and catches fire for 3s; a flame trail marks the flight path. Consumes one
 * per throw (unless creative), 1.5s cooldown.
 */
public class EmberJavelinItem extends Item {
	private static final int COOLDOWN_TICKS = 30;
	private static final float DAMAGE = 7.0F;
	private static final int FIRE_TICKS = 3 * 20;
	private static final double THROW_DISTANCE = 16.0;

	public EmberJavelinItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			// Block raycast bounds the flight, then the nearest living entity whose (slightly
			// inflated) bounding box intersects the ray is the hit (Box.raycast via javap).
			Vec3d start = user.getEyePos();
			Vec3d end = start.add(user.getRotationVec(1.0F).multiply(THROW_DISTANCE));
			BlockHitResult blockHit = world.raycast(new RaycastContext(start, end,
					RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user));
			Vec3d landing = blockHit.getType() == HitResult.Type.MISS ? end : blockHit.getPos();

			LivingEntity target = null;
			double bestDistSq = Double.MAX_VALUE;
			List<LivingEntity> candidates = serverWorld.getEntitiesByClass(LivingEntity.class,
					new Box(start, landing).expand(1.0),
					e -> e.isAlive() && e != user);
			for (LivingEntity candidate : candidates) {
				if (candidate.getBoundingBox().expand(0.3).raycast(start, landing).isPresent()) {
					double distSq = candidate.squaredDistanceTo(user);
					if (distSq < bestDistSq) {
						bestDistSq = distSq;
						target = candidate;
					}
				}
			}

			Vec3d impact = target != null ? target.getEntityPos().add(0.0, target.getHeight() * 0.5, 0.0) : landing;
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.8F, 1.3F);

			// Flame trail along the flight path + burst at the impact point.
			Vec3d path = impact.subtract(start);
			int steps = Math.max(1, (int) (path.length() * 2.0));
			for (int i = 1; i <= steps; i++) {
				Vec3d point = start.add(path.multiply((double) i / steps));
				serverWorld.spawnParticles(ParticleTypes.FLAME, point.x, point.y, point.z,
						1, 0.02, 0.02, 0.02, 0.0);
			}
			serverWorld.spawnParticles(ParticleTypes.LAVA, impact.x, impact.y, impact.z,
					8, 0.3, 0.3, 0.3, 0.0);

			if (target != null) {
				target.setOnFireForTicks(FIRE_TICKS);
				target.damage(serverWorld, serverWorld.getDamageSources().playerAttack(user), DAMAGE);
			}
		}

		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		stack.decrementUnlessCreative(1, user);
		return ActionResult.SUCCESS;
	}
}
