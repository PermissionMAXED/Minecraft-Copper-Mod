package net.sonic0810.copperinferno.core.oxidation;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;

/**
 * Slowly oxidizes every non-waxed chain item carried by players (main inventory, armor, offhand).
 */
public final class OxidationTicker {
	private OxidationTicker() {
	}

	/**
	 * Per-stack, per-tick chance to advance one oxidation stage. 1/6000 means a stack advances on
	 * average once every 6000 ticks (= 5 minutes), so a full unaffected->oxidized cycle takes
	 * roughly 15 minutes of carrying the item around. THE single tuning knob for oxidation speed.
	 */
	public static final float OXIDATION_CHANCE = 1f / 6000f;

	/** Equipment slots scanned in addition to the main inventory. */
	private static final EquipmentSlot[] SCANNED_EQUIPMENT_SLOTS = {
			EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.OFFHAND
	};

	public static void init() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
				Random random = player.getEntityWorld().random;

				DefaultedList<ItemStack> main = player.getInventory().getMainStacks();
				for (int i = 0; i < main.size(); i++) {
					ItemStack stack = main.get(i);
					if (shouldOxidize(stack, random)) {
						main.set(i, ItemOxidation.toNextStage(stack));
					}
				}

				for (EquipmentSlot slot : SCANNED_EQUIPMENT_SLOTS) {
					ItemStack stack = player.getEquippedStack(slot);
					if (shouldOxidize(stack, random)) {
						player.equipStack(slot, ItemOxidation.toNextStage(stack));
					}
				}
			}
		});
	}

	private static boolean shouldOxidize(ItemStack stack, Random random) {
		return !stack.isEmpty()
				&& ItemOxidation.isInChain(stack.getItem())
				&& !ItemOxidation.isWaxed(stack)
				&& ItemOxidation.next(stack.getItem()) != null
				&& random.nextFloat() < OXIDATION_CHANCE;
	}
}
