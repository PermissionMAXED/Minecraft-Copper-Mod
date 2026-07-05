package net.sonic0810.copperinferno.core;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Shared sound events. Entries must match assets/copper_inferno/sounds.json.
 */
public final class ModSounds {
	private ModSounds() {
	}

	public static final SoundEvent DOOM_KICK = register("music.doom_kick");
	public static final SoundEvent DR_PEPPER_OPEN = register("item.dr_pepper.open");
	public static final SoundEvent COPPER_HORN_BLOW = register("item.copper_horn.blow");
	public static final SoundEvent FIZZ_BOMB_POP = register("item.fizz_bomb.pop");

	private static SoundEvent register(String path) {
		Identifier id = CopperInferno.id(path);
		return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	}

	public static void init() {
		// Forces static initialization; registration happens in the field initializers.
	}
}
