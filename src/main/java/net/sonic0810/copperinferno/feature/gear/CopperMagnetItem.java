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
 * "Copper Magnet" — yanks every dropped item within 8 blocks toward the player. Each pull
 * costs 1 durability (same damage pattern as
 * {@link net.sonic0810.copperinferno.core.oxidation.OxidationInteractions}). 3s cooldown.
 */
public class CopperMagnetItem extends Item {
	private static final int COOLDOWN_TICKS = 3 * 20;
	private static final double RANGE = 8.0;
	private static final double PULL_SPEED = 0.6;

	public CopperMagnetItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);

		if (!world.isClient()) {
			Box box = user.getBoundingBox().expand(RANGE);
			Vec3d target = user.getEntityPos().add(0.0, 0.5, 0.0);
			List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, box, e -> true);
			for (ItemEntity itemEntity : items) {
				Vec3d pull = target.subtract(itemEntity.getEntityPos());
				if (pull.lengthSquared() > 1.0E-4) {
					itemEntity.setVelocity(pull.normalize().multiply(PULL_SPEED));
				}
			}
			stack.damage(1, user, hand);
		}

		return ActionResult.SUCCESS;
	}
}
