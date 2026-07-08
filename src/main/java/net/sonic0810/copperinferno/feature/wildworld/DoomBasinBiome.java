package net.sonic0810.copperinferno.feature.wildworld;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Doom Basin: a new fifth Inferno biome — a blood-red ash basin dotted with erupting ember
 * geysers and smoking fumarole vents. Fully data-driven:
 * {@code data/copper_inferno/worldgen/biome/doom_basin.json} (cloned from the shipped
 * ember_grove.json structure by {@code devtools/gen/wildworld_gen.py}) with the
 * {@code patch_ember_geysers} / {@code patch_fumarole_vents} placed features appended to its
 * underground-decoration step; the biome id is appended to the extensible checkerboard biome
 * list in {@code data/copper_inferno/dimension/inferno.json}.
 */
public final class DoomBasinBiome {
	private DoomBasinBiome() {
	}

	public static void init() {
		HandbookEntries.add(new HandbookEntry("dimension", "wildworld_doom_basin",
				"copper_inferno:molten_slag", null, null, null, 0,
				"Doom Basin: a blood-red depression under drifting ash. Barren of flora, but alive"
						+ " with geology — ember geysers erupt in synchronized pulses and fumarole vents"
						+ " smoke day and night.",
				"Doom-Becken: eine blutrote Senke unter treibender Asche. Ohne Flora, aber geologisch"
						+ " quicklebendig — Glutgeysire brechen in synchronen Pulsen aus, und"
						+ " Fumarolenschlote rauchen Tag und Nacht."));
	}
}
