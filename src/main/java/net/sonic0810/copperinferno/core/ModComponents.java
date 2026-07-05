package net.sonic0810.copperinferno.core;

import com.mojang.serialization.Codec;

import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Shared data component types.
 */
public final class ModComponents {
	private ModComponents() {
	}

	/** Marks an item stack as waxed, halting its oxidation (mirrors waxed copper blocks). */
	public static final ComponentType<Boolean> WAXED = Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			CopperInferno.id("waxed"),
			ComponentType.<Boolean>builder().codec(Codec.BOOL).packetCodec(PacketCodecs.BOOLEAN).build());

	public static void init() {
		// Forces static initialization; registration happens in the field initializers.
	}
}
