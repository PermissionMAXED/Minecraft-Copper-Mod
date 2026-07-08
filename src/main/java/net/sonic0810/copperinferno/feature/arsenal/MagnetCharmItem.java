package net.sonic0810.copperinferno.feature.arsenal;

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
 * "Magnet Charm" — the trinket cousin of the v2 Copper Magnet (same distance-scaled item
 * pull, verbatim math), but as an unbreakable charm: a wider 12-block sphere, no durability
 * cost, 5s cooldown. If nothing is in range the use is a no-op (no cooldown).
 */
public class MagnetCharmItem extends Item {
	private static final int COOLDOWN_TICKS = 5 * 20;
	private static final double RANGE = 12.0;
	private static final double RANGE_SQ = RANGE * RANGE;
	private static final double BASE_SPEED = 0.4;
	private static final double SPEED_PER_BLOCK = 0.08;
	private static final double MAX_SPEED = 1.2;

	public MagnetCharmItem(Settings settings) {
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
		}

		return ActionResult.SUCCESS;
	}
}
