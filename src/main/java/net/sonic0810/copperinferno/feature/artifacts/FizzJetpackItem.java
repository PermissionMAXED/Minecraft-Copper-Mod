package net.sonic0810.copperinferno.feature.artifacts;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModSounds;

/**
 * "Fizz Jetpack" — two shaken Dr.Pepper kegs strapped to the back. Worn in the CHEST slot
 * (plain equippable trinket, like the gear feature's copper monocle); every tick the server
 * runs {@link #inventoryTick} for equipped stacks with the slot it sits in (verified via
 * javap: {@code EntityEquipment.tick} calls {@code ItemStack.inventoryTick(World, Entity,
 * EquipmentSlot)} for every equipment slot). When the wearer CROUCH-JUMPS — sneaking, airborne
 * and rising, detected by comparing against the previous tick's Y since player motion is
 * client-authoritative on the server — the jetpack fires: a strong upward launch with a fizzy
 * splash-particle plume and the fizz-pop sound. 1 durability per launch, 3s cooldown.
 */
public class FizzJetpackItem extends Item {
	private static final int COOLDOWN_TICKS = 3 * 20;
	private static final double LAUNCH_SPEED = 1.4;
	private static final double RISE_EPSILON = 0.01;

	/** Previous-tick Y per wearer, for the server-side "just jumped" check. */
	private static final Map<UUID, Double> LAST_Y = new HashMap<>();

	public FizzJetpackItem(Settings settings) {
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, EquipmentSlot slot) {
		super.inventoryTick(stack, world, entity, slot);
		// Equipment-slot check each tick: the jetpack only fires from the chest slot.
		if (slot != EquipmentSlot.CHEST || !(entity instanceof PlayerEntity player)) {
			return;
		}

		double lastY = LAST_Y.getOrDefault(player.getUuid(), player.getY());
		boolean rising = player.getY() - lastY > RISE_EPSILON;
		LAST_Y.put(player.getUuid(), player.getY());

		if (!player.isSneaking() || player.isOnGround() || !rising
				|| player.getItemCooldownManager().isCoolingDown(stack)) {
			return;
		}

		Vec3d velocity = player.getVelocity();
		player.setVelocity(velocity.x, LAUNCH_SPEED, velocity.z);
		player.velocityModified = true;

		world.playSound(null, player.getX(), player.getY(), player.getZ(),
				ModSounds.FIZZ_BOMB_POP, SoundCategory.PLAYERS, 1.0F, 0.9F);
		world.spawnParticles(ParticleTypes.SPLASH,
				player.getX(), player.getY(), player.getZ(), 30, 0.4, 0.3, 0.4, 0.1);
		world.spawnParticles(ParticleTypes.BUBBLE_POP,
				player.getX(), player.getY(), player.getZ(), 20, 0.3, 0.3, 0.3, 0.05);
		world.spawnParticles(ParticleTypes.CLOUD,
				player.getX(), player.getY(), player.getZ(), 8, 0.2, 0.1, 0.2, 0.02);

		player.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		stack.damage(1, player, EquipmentSlot.CHEST);
	}
}
