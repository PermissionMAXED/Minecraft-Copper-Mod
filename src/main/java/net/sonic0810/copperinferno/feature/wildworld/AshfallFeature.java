package net.sonic0810.copperinferno.feature.wildworld;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Ashfall: dimension-wide drifting ash while the player is anywhere in the Inferno. The
 * visuals are entirely client-side — {@code feature.wildworld.client.AshfallAmbienceClient}
 * spawns the existing {@code copper_inferno:ash_fall} particles (type + sprite factory owned
 * by the infernofx feature) around the local player every tick. This class only contributes
 * the server-safe handbook entry.
 */
public final class AshfallFeature {
	private AshfallFeature() {
	}

	public static void init() {
		HandbookEntries.add(new HandbookEntry("dimension", "wildworld_ashfall",
				"copper_inferno:ash_block", null, null, null, 0,
				"Ashfall: the Inferno's weather. Gray ash flakes drift down endlessly everywhere in"
						+ " the dimension, thickening the air from the Cinder Wastes to the Doom Basin."
						+ " Purely atmospheric — it never settles into blocks.",
				"Aschefall: das Wetter des Infernos. Graue Ascheflocken rieseln überall in der"
						+ " Dimension endlos herab und verdichten die Luft von der Aschenöde bis zum"
						+ " Doom-Becken. Reine Atmosphäre — sie lagert sich nie als Block ab."));
	}
}
