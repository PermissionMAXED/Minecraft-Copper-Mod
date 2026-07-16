package net.sonic0810.kupferbienen.core;

import java.util.function.Function;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.sonic0810.kupferbienen.Kupferbienen;

/**
 * Item registration helper. Item fields live in the individual feature classes.
 */
public final class ModItems {
	private ModItems() {
	}

	/**
	 * Registers an item under {@code kupferbienen:<path>}. The registry key is applied to the
	 * settings automatically (required in 1.21.x) before the factory is invoked.
	 */
	public static <T extends Item> T register(String path, Function<Item.Settings, T> factory, Item.Settings settings) {
		RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Kupferbienen.id(path));
		T item = factory.apply(settings.registryKey(key));
		return Registry.register(Registries.ITEM, key, item);
	}
}
