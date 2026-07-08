package net.sonic0810.copperinferno.feature.systems;

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
 * "Reinforced Copper Magnet" — the Magnet Bench upgrade of the gear feature's copper magnet
 * (same use pattern as {@link net.sonic0810.copperinferno.feature.gear.CopperMagnetItem}, whose
 * tuning constants are private): double the pull radius (16 blocks), a stronger impulse, a
 * shorter cooldown (2s vs 3s) and far more durability (512 vs 128).
 */
public class ReinforcedCopperMagnetItem extends Item {
	private static final int COOLDOWN_TICKS = 2 * 20;
	private static final double RANGE = 16.0;
	private static final double RANGE_SQ = RANGE * RANGE;
	private static final double BASE_SPEED = 0.5;
	private static final double SPEED_PER_BLOCK = 0.08;
	private static final double MAX_SPEED = 1.6;

	public ReinforcedCopperMagnetItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

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
			stack.damage(1, user, hand);
		}

		return ActionResult.SUCCESS;
	}
}
