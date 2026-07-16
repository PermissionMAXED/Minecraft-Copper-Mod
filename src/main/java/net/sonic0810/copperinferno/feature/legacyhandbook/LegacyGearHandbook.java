package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "gear" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/gear/} (9 entries, category
 * "gear"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyGearHandbook {
	private LegacyGearHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("gear", "gear/copper_badge", "copper_inferno:copper_badge", "gear/copper_badge",
				new String[] {"", "minecraft:copper_ingot", "", "minecraft:copper_ingot", "minecraft:raw_copper", "minecraft:copper_ingot", "", "minecraft:copper_ingot", ""},
				"copper_inferno:copper_badge", 1, "Craft 1x Copper Badge at a crafting table.", "Stellt 1x Kupferabzeichen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("gear", "gear/copper_buckler", "copper_inferno:copper_buckler", "gear/copper_buckler",
				new String[] {"copper_inferno:copper_sheet", "copper_inferno:copper_rivet", "copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "", "copper_inferno:copper_sheet", ""},
				"copper_inferno:copper_buckler", 1, "Craft 1x Copper Buckler at a crafting table.", "Stellt 1x Kupfer-Faustschild an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("gear", "gear/copper_key", "copper_inferno:copper_key", "gear/copper_key",
				new String[] {"minecraft:iron_nugget", "", "", "minecraft:copper_ingot", "", "", "minecraft:copper_ingot", "", ""},
				"copper_inferno:copper_key", 1, "Craft 1x Copper Key at a crafting table.", "Stellt 1x Kupferschl\u00fcssel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("gear", "gear/copper_magnet", "copper_inferno:copper_magnet", "gear/copper_magnet",
				new String[] {"copper_inferno:copper_alloy_ingot", "", "copper_inferno:copper_alloy_ingot", "copper_inferno:copper_alloy_ingot", "", "copper_inferno:copper_alloy_ingot", "copper_inferno:copper_coil", "copper_inferno:copper_alloy_ingot", "copper_inferno:copper_coil"},
				"copper_inferno:copper_magnet", 1, "Craft 1x Copper Magnet at a crafting table.", "Stellt 1x Kupfermagnet an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("gear", "gear/copper_monocle", "copper_inferno:copper_monocle", "gear/copper_monocle",
				new String[] {"", "copper_inferno:copper_spring", "", "minecraft:copper_ingot", "minecraft:glass", "minecraft:copper_ingot", "", "", ""},
				"copper_inferno:copper_monocle", 1, "Craft 1x Copper Monocle at a crafting table.", "Stellt 1x Kupfermonokel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("gear", "gear/copper_whistle", "copper_inferno:copper_whistle", "gear/copper_whistle",
				new String[] {"minecraft:copper_ingot", "minecraft:copper_ingot", "", "", "copper_inferno:copper_screw", "", "", "", ""},
				"copper_inferno:copper_whistle", 1, "Craft 1x Copper Whistle at a crafting table.", "Stellt 1x Kupferpfeife an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("gear", "gear/inferno_badge", "copper_inferno:inferno_badge", "gear/inferno_badge",
				new String[] {"", "copper_inferno:charred_copper_ingot", "", "copper_inferno:charred_copper_ingot", "minecraft:blaze_powder", "copper_inferno:charred_copper_ingot", "", "copper_inferno:charred_copper_ingot", ""},
				"copper_inferno:inferno_badge", 1, "Craft 1x Inferno Badge at a crafting table.", "Stellt 1x Infernoabzeichen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("gear", "gear/soda_badge", "copper_inferno:soda_badge", "gear/soda_badge",
				new String[] {"", "minecraft:copper_ingot", "", "minecraft:copper_ingot", "minecraft:sugar", "minecraft:copper_ingot", "", "minecraft:copper_ingot", ""},
				"copper_inferno:soda_badge", 1, "Craft 1x Soda Badge at a crafting table.", "Stellt 1x Limonadenabzeichen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("gear", "gear/throwing_fizz_can", "copper_inferno:throwing_fizz_can", "gear/throwing_fizz_can",
				new String[] {"copper_inferno:fizz_bomb", "copper_inferno:copper_mesh", "", "", "", "", "", "", ""},
				"copper_inferno:throwing_fizz_can", 2, "Craft 2x Throwing Fizz Can at a crafting table.", "Stellt 2x Wurf-Brausedose an der Werkbank her."));
	}
}
