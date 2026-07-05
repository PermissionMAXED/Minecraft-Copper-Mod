package net.sonic0810.copperinferno.core;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Shared particle types. The client factory is registered in core.client.CoreClient.
 */
public final class ModParticles {
	private ModParticles() {
	}

	public static final SimpleParticleType COPPER_SPARKLE = Registry.register(
			Registries.PARTICLE_TYPE,
			CopperInferno.id("copper_sparkle"),
			FabricParticleTypes.simple());

	public static void init() {
		// Forces static initialization; registration happens in the field initializers.
	}
}
