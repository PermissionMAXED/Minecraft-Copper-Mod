package net.sonic0810.copperinferno.core.oxidation;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * Axe scraping / honeycomb waxing for oxidizable items, mirroring the copper block interactions.
 * Hold the oxidizable item in the OFFHAND and use an axe or honeycomb from the MAIN hand.
 */
public final class OxidationInteractions {
	private OxidationInteractions() {
	}

	public static void init() {
		UseItemCallback.EVENT.register(OxidationInteractions::onUseItem);
	}

	private static ActionResult onUseItem(PlayerEntity player, World world, Hand hand) {
		if (hand != Hand.MAIN_HAND) {
			return ActionResult.PASS;
		}
		ItemStack held = player.getStackInHand(Hand.MAIN_HAND);
		ItemStack offhand = player.getStackInHand(Hand.OFF_HAND);
		if (offhand.isEmpty() || !ItemOxidation.isInChain(offhand.getItem())) {
			return ActionResult.PASS;
		}

		if (held.isIn(ItemTags.AXES)) {
			if (ItemOxidation.isWaxed(offhand)) {
				if (!world.isClient()) {
					ItemOxidation.setWaxed(offhand, false);
					held.damage(1, player, Hand.MAIN_HAND);
					playFeedback(world, player, SoundEvents.ITEM_AXE_WAX_OFF, ParticleTypes.WAX_OFF);
				}
				return ActionResult.SUCCESS;
			}
			if (ItemOxidation.previous(offhand.getItem()) != null) {
				if (!world.isClient()) {
					player.setStackInHand(Hand.OFF_HAND, ItemOxidation.toPreviousStage(offhand));
					held.damage(1, player, Hand.MAIN_HAND);
					playFeedback(world, player, SoundEvents.ITEM_AXE_SCRAPE, ParticleTypes.SCRAPE);
				}
				return ActionResult.SUCCESS;
			}
			return ActionResult.PASS;
		}

		if (held.isOf(Items.HONEYCOMB) && !ItemOxidation.isWaxed(offhand)) {
			if (!world.isClient()) {
				ItemOxidation.setWaxed(offhand, true);
				held.decrementUnlessCreative(1, player);
				playFeedback(world, player, SoundEvents.ITEM_HONEYCOMB_WAX_ON, ParticleTypes.WAX_ON);
			}
			return ActionResult.SUCCESS;
		}

		return ActionResult.PASS;
	}

	private static void playFeedback(World world, PlayerEntity player, SoundEvent sound, ParticleEffect particle) {
		world.playSound(null, player.getBlockPos(), sound, SoundCategory.PLAYERS, 1.0f, 1.0f);
		if (world instanceof ServerWorld serverWorld) {
			serverWorld.spawnParticles(particle,
					player.getX(), player.getBodyY(0.7), player.getZ(),
					8, 0.3, 0.3, 0.3, 0.0);
		}
	}
}
