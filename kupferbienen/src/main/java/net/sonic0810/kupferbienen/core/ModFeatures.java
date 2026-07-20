package net.sonic0810.kupferbienen.core;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.sonic0810.kupferbienen.Kupferbienen;

/**
 * Worldgen {@link Feature} registration helper. Feature fields live in the individual
 * feature classes.
 *
 * <p>Vanilla's own {@code Feature.register(String, F)} is private (verified via javap), so
 * this mirrors the ModItems/ModEffects pattern and registers directly into
 * {@code Registries.FEATURE} ({@code Registry<Feature<?>>}, key {@code RegistryKeys.FEATURE}).
 * Configured/placed features referencing the registered id are data-driven JSON under
 * {@code data/kupferbienen/worldgen/}.
 */
public final class ModFeatures {
	private ModFeatures() {
	}

	/**
	 * Registers a worldgen feature under {@code kupferbienen:<path>} and returns it.
	 */
	public static <C extends FeatureConfig, F extends Feature<C>> F register(String path, F feature) {
		return Registry.register(Registries.FEATURE, Kupferbienen.id(path), feature);
	}
}
