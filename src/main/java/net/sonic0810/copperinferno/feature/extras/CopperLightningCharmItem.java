package net.sonic0810.copperinferno.feature.extras;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

/**
 * "Copper Lightning Charm" — calls down a lightning bolt where the player is looking (raycast up
 * to 48 blocks). 16 uses (maxDamage), 5s cooldown.
 */
public class CopperLightningCharmItem extends Item {
	private static final double RAYCAST_DISTANCE = 48.0;
	private static final int COOLDOWN_TICKS = 5 * 20;

	public CopperLightningCharmItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		HitResult hit = user.raycast(RAYCAST_DISTANCE, 0.0F, false);
		if (hit.getType() == HitResult.Type.MISS) {
			return ActionResult.FAIL;
		}

		if (world instanceof ServerWorld serverWorld) {
			LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(serverWorld, SpawnReason.TRIGGERED);
			if (lightning != null) {
				lightning.setPosition(hit.getPos());
				serverWorld.spawnEntity(lightning);
			}
			stack.damage(1, user);
		}

		user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		return ActionResult.SUCCESS;
	}
}
