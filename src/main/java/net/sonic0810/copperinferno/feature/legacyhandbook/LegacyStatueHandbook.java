package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "statue" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/statue/} (1 entry, category
 * "items"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyStatueHandbook {
	private LegacyStatueHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("items", "statue/copper_player_statue", "copper_inferno:copper_player_statue", "statue/copper_player_statue",
				new String[] {"minecraft:carved_pumpkin", "", "", "minecraft:copper_block", "", "", "minecraft:copper_block", "", ""},
				"copper_inferno:copper_player_statue", 1, "Craft 1x Copper Player Statue at a crafting table.", "Stellt 1x Kupfer-Spielerstatue an der Werkbank her."));
	}
}
