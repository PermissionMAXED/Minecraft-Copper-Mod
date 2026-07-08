package net.sonic0810.copperinferno.feature.infernofx;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
 * "Slag Bomb" — a still-molten slag lump hurled ahead of the player. Like the v2 Throwing
 * Fizz Can this is deliberately an eye-ray raycast rather than a real ThrownItemEntity: it
 * pops on the first block hit (or at 8 blocks), bursting into ember sparks and briefly
 * igniting living entities near the landing point (3s). No fire blocks are placed and no
 * blocks are damaged. Consumes one per use (unless creative), 3s cooldown.
 */
public class SlagBombItem extends Item {
	private static final int COOLDOWN_TICKS = 3 * 20;
	private static final int FIRE_TICKS = 3 * 20;
	private static final double THROW_DISTANCE = 8.0;
	private static final double BURN_RADIUS = 2.5;

	public SlagBombItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			// Raycast from the eyes along the look vector (same pattern as ThrowingFizzCanItem).
			Vec3d start = user.getEyePos();
			Vec3d end = start.add(user.getRotationVec(1.0F).multiply(THROW_DISTANCE));
			BlockHitResult hit = world.raycast(new RaycastContext(start, end,
					RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user));
			Vec3d landing = hit.getType() == HitResult.Type.MISS ? end : hit.getPos();

			world.playSound(null, landing.x, landing.y, landing.z,
					SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.9F, 0.8F);

			// Ember trail along the flight path + a spark burst at the landing point.
			Vec3d path = landing.subtract(start);
			int steps = Math.max(1, (int) (path.length() * 2.0));
			for (int i = 1; i <= steps; i++) {
				Vec3d point = start.add(path.multiply((double) i / steps));
				serverWorld.spawnParticles(InfernoFxFeature.EMBER_SPARK,
						point.x, point.y, point.z, 1, 0.05, 0.05, 0.05, 0.0);
			}
			serverWorld.spawnParticles(InfernoFxFeature.EMBER_SPARK,
					landing.x, landing.y, landing.z, 32, 0.8, 0.5, 0.8, 0.08);
			serverWorld.spawnParticles(InfernoFxFeature.ASH_FALL,
					landing.x, landing.y + 0.5, landing.z, 12, 0.7, 0.4, 0.7, 0.01);

			// Short fire on nearby living entities only — no fire blocks, no block damage.
			List<LivingEntity> targets = serverWorld.getEntitiesByClass(LivingEntity.class,
					Box.of(landing, BURN_RADIUS * 2.0, BURN_RADIUS * 2.0, BURN_RADIUS * 2.0),
					e -> e.isAlive() && e != user && e.getEntityPos().distanceTo(landing) <= BURN_RADIUS);
			for (LivingEntity target : targets) {
				target.setOnFireForTicks(FIRE_TICKS);
			}
		}

		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		stack.decrementUnlessCreative(1, user);
		return ActionResult.SUCCESS;
	}
}
