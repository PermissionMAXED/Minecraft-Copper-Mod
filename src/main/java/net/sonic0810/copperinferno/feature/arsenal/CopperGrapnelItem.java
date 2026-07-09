package net.sonic0810.copperinferno.feature.arsenal;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * "Copper Grapnel" — a spring-loaded grappling hook: use launches the player along their look
 * vector (with a small upward assist so ground-level lunges still clear obstacles). Server-side
 * velocity change is delivered via {@code Entity.velocityModified} (public field, verified via
 * javap), the standard way to push a ServerPlayerEntity. 1 durability per launch, 2s cooldown.
 */
public class CopperGrapnelItem extends Item {
	private static final int COOLDOWN_TICKS = 2 * 20;
	private static final double LAUNCH_SPEED = 1.6;
	private static final double UP_ASSIST = 0.3;

	public CopperGrapnelItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			Vec3d look = user.getRotationVec(1.0F);
			user.setVelocity(look.x * LAUNCH_SPEED, look.y * LAUNCH_SPEED + UP_ASSIST, look.z * LAUNCH_SPEED);
			user.velocityModified = true;

			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.PLAYERS, 1.0F, 0.7F);
			serverWorld.spawnParticles(ParticleTypes.CRIT,
					user.getX(), user.getBodyY(0.5), user.getZ(), 10, 0.3, 0.3, 0.3, 0.1);

			user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
			stack.damage(1, user, hand);
		}

		return ActionResult.SUCCESS;
	}
}
