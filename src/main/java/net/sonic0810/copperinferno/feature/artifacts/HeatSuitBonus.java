package net.sonic0810.copperinferno.feature.artifacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * "Heat Suit Bonus" — a full-set bonus for the WP6 arsenal armor tiers: wearing all 4 pieces
 * of ONE tier (pyrium, emberite, ashsteel or voidsteel) grants fire immunity (a rolling
 * ambient Fire Resistance effect, refreshed by a periodic server-tick check). The arsenal
 * package is NOT touched: the equipped pieces are detected purely by their item REGISTRY IDS
 * ({@code copper_inferno:<tier>_helmet/_chestplate/_leggings/_boots}). An action-bar message
 * announces the seal the moment the set completes.
 */
public final class HeatSuitBonus {
	private HeatSuitBonus() {
	}

	/** The WP6 arsenal armor tiers, detected by id only (do NOT import the arsenal package). */
	private static final List<String> TIERS = List.of("pyrium", "emberite", "ashsteel", "voidsteel");
	/** Check every second; the effect lasts 3s, so immunity never flickers. */
	private static final int CHECK_INTERVAL_TICKS = 20;
	private static final int EFFECT_TICKS = 3 * 20;

	/** Players whose set bonus is currently active (for the one-shot action-bar message). */
	private static final Set<UUID> ACTIVE = new HashSet<>();

	public static void init() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			if (server.getTicks() % CHECK_INTERVAL_TICKS != 0) {
				return;
			}
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
				if (wearsFullTier(player)) {
					player.addStatusEffect(new StatusEffectInstance(
							StatusEffects.FIRE_RESISTANCE, EFFECT_TICKS, 0, true, false));
					if (ACTIVE.add(player.getUuid())) {
						player.sendMessage(
								Text.translatable("message.copper_inferno.heat_suit_bonus.active"), true);
					}
				} else {
					ACTIVE.remove(player.getUuid());
				}
			}
		});
	}

	private static boolean wearsFullTier(ServerPlayerEntity player) {
		for (String tier : TIERS) {
			if (matches(player, EquipmentSlot.HEAD, tier + "_helmet")
					&& matches(player, EquipmentSlot.CHEST, tier + "_chestplate")
					&& matches(player, EquipmentSlot.LEGS, tier + "_leggings")
					&& matches(player, EquipmentSlot.FEET, tier + "_boots")) {
				return true;
			}
		}
		return false;
	}

	private static boolean matches(ServerPlayerEntity player, EquipmentSlot slot, String path) {
		ItemStack stack = player.getEquippedStack(slot);
		if (stack.isEmpty()) {
			return false;
		}
		Identifier id = Registries.ITEM.getId(stack.getItem());
		return id.equals(CopperInferno.id(path));
	}
}
