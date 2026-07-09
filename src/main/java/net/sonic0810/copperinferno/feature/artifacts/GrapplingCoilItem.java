package net.sonic0810.copperinferno.feature.artifacts;

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
 * "Grappling Coil" — a wound copper cable with a barbed anchor: use raycasts along the look
 * vector (up to 24 blocks, same eye-ray pattern as {@code ThrowingFizzCanItem}) and, when a
 * block is hit, REELS THE PLAYER IN toward the hit point with a distance-scaled velocity
 * impulse (small upward assist so low shots still clear ledges). Server-side velocity is
 * delivered via {@code Entity.velocityModified}, exactly like the arsenal's
 * {@code CopperGrapnelItem}. A miss is a free no-op (no durability, no cooldown); a hit costs
 * 1 durability and starts a 2s cooldown.
 */
public class GrapplingCoilItem extends Item {
	private static final int COOLDOWN_TICKS = 2 * 20;
	private static final double MAX_RANGE = 24.0;
	private static final double SPEED_PER_BLOCK = 0.16;
	private static final double MAX_SPEED = 2.4;
	private static final double UP_ASSIST = 0.25;

	public GrapplingCoilItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		// Raycast from the eyes along the look vector; only a block hit engages the coil.
		Vec3d start = user.getEyePos();
		Vec3d end = start.add(user.getRotationVec(1.0F).multiply(MAX_RANGE));
		BlockHitResult hit = world.raycast(new RaycastContext(start, end,
				RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user));
		if (hit.getType() == HitResult.Type.MISS) {
			// Nothing to latch onto: no durability damage, no cooldown.
			return ActionResult.PASS;
		}

		if (world instanceof ServerWorld serverWorld) {
			Vec3d pull = hit.getPos().subtract(user.getEntityPos());
			double distance = pull.length();
			if (distance > 1.0E-2) {
				double speed = Math.min(SPEED_PER_BLOCK * distance, MAX_SPEED);
				Vec3d velocity = pull.multiply(speed / distance);
				user.setVelocity(velocity.x, velocity.y + UP_ASSIST, velocity.z);
				user.velocityModified = true;
			}

			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.PLAYERS, 1.0F, 0.6F);
			// Crit sparks along the cable path.
			int steps = Math.max(1, (int) (distance * 2.0));
			for (int i = 1; i <= steps; i++) {
				Vec3d point = start.add(pull.multiply((double) i / steps));
				serverWorld.spawnParticles(ParticleTypes.CRIT, point.x, point.y, point.z,
						1, 0.05, 0.05, 0.05, 0.0);
			}

			user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
			stack.damage(1, user, hand);
		}

		return ActionResult.SUCCESS;
	}
}
