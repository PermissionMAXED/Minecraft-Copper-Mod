package net.sonic0810.kupferbienen.core;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.sonic0810.kupferbienen.Kupferbienen;

/**
 * Status effect registration helper. Effect entry fields live in the individual feature classes.
 */
public final class ModEffects {
	private ModEffects() {
	}

	/**
	 * Registers a status effect under {@code kupferbienen:<path>} and returns its registry
	 * entry (status effects are referenced by {@link RegistryEntry} everywhere in 1.21.x).
	 */
	public static RegistryEntry<StatusEffect> register(String path, StatusEffect effect) {
		return Registry.registerReference(Registries.STATUS_EFFECT, Kupferbienen.id(path), effect);
	}
}
