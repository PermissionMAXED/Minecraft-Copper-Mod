package net.sonic0810.copperinferno.core;

import net.sonic0810.copperinferno.core.oxidation.OxidationInteractions;
import net.sonic0810.copperinferno.core.oxidation.OxidationTicker;

/**
 * Central bootstrap for the shared core infrastructure. Must run before any feature init.
 */
public final class CopperInfernoCore {
	private CopperInfernoCore() {
	}

	public static void init() {
		ModComponents.init();
		ModSounds.init();
		ModParticles.init();
		ModCreativeTab.init();
		OxidationTicker.init();
		OxidationInteractions.init();
	}
}
