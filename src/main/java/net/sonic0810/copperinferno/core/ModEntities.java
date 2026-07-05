package net.sonic0810.copperinferno.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Entity type registration helper. EntityType fields live in the individual feature classes.
 */
public final class ModEntities {
	private ModEntities() {
	}

	/**
	 * Registers an entity type under {@code copper_inferno:<path>}. The builder is built with the
	 * matching registry key (required in 1.21.x).
	 */
	public static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> builder) {
		RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, CopperInferno.id(path));
		return Registry.register(Registries.ENTITY_TYPE, key, builder.build(key));
	}
}
