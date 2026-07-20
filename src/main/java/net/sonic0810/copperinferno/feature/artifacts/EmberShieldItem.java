package net.sonic0810.copperinferno.feature.artifacts;

import java.util.List;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
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
 * "Ember Shield" — a tempered-plate buckler with a molten core. Use raises a short PARRY
 * WINDOW: the player gets 5s of Fire Resistance, and every hostile within 4 blocks is flash-
 * ignited for 4s by the erupting embers (fire on entities only, no fire blocks — same
 * restraint as {@code SlagBombItem}). 1 durability per parry, 8s cooldown.
 */
public class EmberShieldItem extends Item {
	private static final int COOLDOWN_TICKS = 8 * 20;
	private static final int PARRY_WINDOW_TICKS = 5 * 20;
	private static final int IGNITE_TICKS = 4 * 20;
	private static final double IGNITE_RADIUS = 4.0;

	public EmberShieldItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			user.addStatusEffect(new StatusEffectInstance(
					StatusEffects.FIRE_RESISTANCE, PARRY_WINDOW_TICKS, 0));

			// Flash-ignite hostiles inside a true sphere around the player.
			List<HostileEntity> hostiles = serverWorld.getEntitiesByClass(HostileEntity.class,
					user.getBoundingBox().expand(IGNITE_RADIUS),
					e -> e.isAlive() && e.squaredDistanceTo(user) <= IGNITE_RADIUS * IGNITE_RADIUS);
			for (HostileEntity hostile : hostiles) {
				hostile.setOnFireForTicks(IGNITE_TICKS);
			}

			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 0.8F, 1.1F);
			serverWorld.spawnParticles(ParticleTypes.FLAME,
					user.getX(), user.getBodyY(0.5), user.getZ(), 24, 1.2, 0.8, 1.2, 0.03);
			serverWorld.spawnParticles(ParticleTypes.LAVA,
					user.getX(), user.getBodyY(0.5), user.getZ(), 4, 0.6, 0.4, 0.6, 0.0);

			user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
			stack.damage(1, user, hand);
		}

		return ActionResult.SUCCESS;
	}
}
