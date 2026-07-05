package net.sonic0810.copperinferno.feature.drpepper;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * Dr.Pepper drink, brewing and the DOOM kick. Content is registered by the Dr.Pepper feature
 * worker, which assigns the fields below in {@link #init()}.
 */
public final class DrPepperFeature {
	private DrPepperFeature() {
	}

	/** Assigned in init() by the Dr.Pepper feature worker. */
	public static Item DR_PEPPER;
	/** Assigned in init() by the Dr.Pepper feature worker. */
	public static RegistryEntry<StatusEffect> DR_PEPPER_KICK;

	public static void init() {
	}
}
