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
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

/**
 * "Slag Hammer" — a heavy two-handed maul. Use (right-click) slams the ground: every living
 * entity within 3 blocks (true sphere, same broad-phase-then-trim pattern as
 * CopperMagnetItem) takes 5 damage and is knocked away from the player. No targets = no-op
 * (no durability, no cooldown); otherwise 1 durability and a 5s cooldown.
 */
public class SlagHammerItem extends Item {
	private static final int COOLDOWN_TICKS = 5 * 20;
	private static final double RANGE = 3.0;
	private static final double RANGE_SQ = RANGE * RANGE;
	private static final float DAMAGE = 5.0F;
	private static final double KNOCKBACK_STRENGTH = 1.2;

	public SlagHammerItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		Box searchBox = user.getBoundingBox().expand(RANGE);
		List<LivingEntity> targets = world.getEntitiesByClass(LivingEntity.class, searchBox,
				e -> e.isAlive() && e != user && e.squaredDistanceTo(user) <= RANGE_SQ);
		if (targets.isEmpty()) {
			return ActionResult.PASS;
		}

		if (world instanceof ServerWorld serverWorld) {
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.9F, 0.7F);
			serverWorld.spawnParticles(ParticleTypes.CLOUD,
					user.getX(), user.getY() + 0.2, user.getZ(), 24, 1.5, 0.2, 1.5, 0.05);

			for (LivingEntity target : targets) {
				target.damage(serverWorld, serverWorld.getDamageSources().playerAttack(user), DAMAGE);
				// takeKnockback pushes the target AWAY along (attacker - target), the vanilla
				// melee-knockback argument order.
				target.takeKnockback(KNOCKBACK_STRENGTH,
						user.getX() - target.getX(), user.getZ() - target.getZ());
			}

			user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
			stack.damage(1, user, hand);
		}

		return ActionResult.SUCCESS;
	}
}
