package net.sonic0810.copperinferno.feature.systems;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;
import net.sonic0810.copperinferno.core.oxidation.ItemOxidation;
import net.sonic0810.copperinferno.core.oxidation.OxidationTicker;

/**
 * Oxidation Weather — while it rains, carried copper gear oxidizes twice as fast.
 *
 * <p>Deliberately does NOT touch {@code core/oxidation}: it subscribes to the SAME event
 * ({@link ServerTickEvents#END_SERVER_TICK}) as {@link OxidationTicker} and rolls one EXTRA
 * oxidation pass per tick (same {@link OxidationTicker#OXIDATION_CHANCE} per stack) for every
 * player standing in a raining world, doubling the effective oxidation rate in the rain.
 */
public final class OxidationWeatherSystem {
	private OxidationWeatherSystem() {
	}

	/** Same equipment slots the core ticker scans. */
	private static final EquipmentSlot[] SCANNED_EQUIPMENT_SLOTS = {
			EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.OFFHAND
	};

	public static void init() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
				if (!player.getEntityWorld().isRaining()) {
					continue;
				}
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
				&& random.nextFloat() < OxidationTicker.OXIDATION_CHANCE;
	}
}
