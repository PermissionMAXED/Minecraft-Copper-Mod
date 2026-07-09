package net.sonic0810.copperinferno.feature.wildworld;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Verdigris Jungle: a new fourth Inferno biome — an overgrown, verdigris-tinted fungus jungle.
 * Fully data-driven: {@code data/copper_inferno/worldgen/biome/verdigris_jungle.json} (cloned
 * from the shipped ember_grove.json structure by {@code devtools/gen/wildworld_gen.py}) plus
 * the extra {@code patch_verdigris_flora} placed feature for its dense undergrowth; the biome
 * id is appended to the extensible checkerboard biome list in
 * {@code data/copper_inferno/dimension/inferno.json}.
 */
public final class VerdigrisJungleBiome {
	private VerdigrisJungleBiome() {
	}

	public static void init() {
		HandbookEntries.add(new HandbookEntry("dimension", "wildworld_verdigris_jungle",
				"copper_inferno:ember_fungus", null, null, null, 0,
				"Verdigris Jungle: the Inferno's overgrown quarter. Teal fog hangs between fungus"
						+ " thickets far denser than the Ember Grove's, and the ground crawls with ember"
						+ " moss, spore clusters and glowing smolder blooms.",
				"Grünspandschungel: das überwucherte Viertel des Infernos. Türkiser Nebel hängt zwischen"
						+ " Pilzdickichten, die weit dichter sind als im Gluthain, und der Boden wimmelt von"
						+ " Glutmoos, Sporengewächsen und leuchtenden Schwelblüten."));
	}
}
