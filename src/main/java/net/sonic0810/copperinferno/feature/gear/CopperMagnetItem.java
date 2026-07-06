package net.sonic0810.copperinferno.feature.gear;

import java.util.List;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * "Copper Magnet" — yanks every dropped item within a true 8-block sphere toward the player
 * with a distance-scaled impulse (farther items get a stronger pull). If nothing is in range
 * the use is a no-op: no durability damage, no cooldown. Otherwise the pull costs 1 durability
 * (same damage pattern as {@link net.sonic0810.copperinferno.core.oxidation.OxidationInteractions})
 * and starts a 3s cooldown.
 */
public class CopperMagnetItem extends Item {
	private static final int COOLDOWN_TICKS = 3 * 20;
	private static final double RANGE = 8.0;
	private static final double RANGE_SQ = RANGE * RANGE;
	private static final double BASE_SPEED = 0.4;
	private static final double SPEED_PER_BLOCK = 0.08;
	private static final double MAX_SPEED = 1.2;

	public CopperMagnetItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		// True sphere check: the cube box is only the broad phase, the predicate trims corners.
		Box searchBox = user.getBoundingBox().expand(RANGE);
		List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, searchBox,
				e -> e.isAlive() && e.squaredDistanceTo(user) <= RANGE_SQ);
		if (items.isEmpty()) {
			// Nothing to pull: no durability damage, no cooldown.
			return ActionResult.PASS;
		}

		if (!world.isClient()) {
			Vec3d target = user.getEntityPos().add(0.0, 0.5, 0.0);
			for (ItemEntity itemEntity : items) {
				Vec3d pull = target.subtract(itemEntity.getEntityPos());
				double distance = pull.length();
				if (distance > 1.0E-2) {
					double speed = Math.min(BASE_SPEED + SPEED_PER_BLOCK * distance, MAX_SPEED);
					itemEntity.setVelocity(pull.multiply(speed / distance));
				}
			}
			user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
			stack.damage(1, user, hand);
		}

		return ActionResult.SUCCESS;
	}
}
