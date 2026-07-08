package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "extras" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/extras/} (6 entries, category
 * "items"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyExtrasHandbook {
	private LegacyExtrasHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("items", "extras/copper_coin", "copper_inferno:copper_coin", "extras/copper_coin",
				new String[] {"minecraft:copper_nugget", "minecraft:copper_nugget", "", "minecraft:copper_nugget", "minecraft:copper_nugget", "", "", "", ""},
				"copper_inferno:copper_coin", 4, "Craft 4x Copper Coin at a crafting table.", "Stellt 4x Kupferm\u00fcnze an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "extras/copper_horn", "copper_inferno:copper_horn", "extras/copper_horn",
				new String[] {"minecraft:copper_ingot", "minecraft:copper_ingot", "", "copper_inferno:copper_coin", "minecraft:copper_ingot", "", "", "", ""},
				"copper_inferno:copper_horn", 1, "Craft 1x Copper Horn at a crafting table.", "Stellt 1x Kupferhorn an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "extras/copper_lightning_charm", "copper_inferno:copper_lightning_charm", "extras/copper_lightning_charm",
				new String[] {"", "minecraft:lightning_rod", "", "minecraft:copper_ingot", "copper_inferno:copper_coin", "minecraft:copper_ingot", "", "minecraft:copper_ingot", ""},
				"copper_inferno:copper_lightning_charm", 1, "Craft 1x Copper Lightning Charm at a crafting table.", "Stellt 1x Kupfer-Blitzamulett an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "extras/dr_pepper_can_block", "copper_inferno:dr_pepper_can_block", "extras/dr_pepper_can_block",
				new String[] {"minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:red_dye", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot"},
				"copper_inferno:dr_pepper_can_block", 1, "Craft 1x Dr.Pepper Can Block at a crafting table.", "Stellt 1x Dr.Pepper-Dosenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "extras/fizz_bomb", "copper_inferno:fizz_bomb", "extras/fizz_bomb",
				new String[] {"minecraft:gunpowder", "minecraft:sugar", "minecraft:copper_nugget", "", "", "", "", "", ""},
				"copper_inferno:fizz_bomb", 1, "Craft 1x Fizz Bomb at a crafting table.", "Stellt 1x Brausebombe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "extras/katzenliebhaber737_charm", "copper_inferno:katzenliebhaber737_charm", "extras/katzenliebhaber737_charm",
				new String[] {"minecraft:copper_ingot", "minecraft:string", "minecraft:cod", "", "", "", "", "", ""},
				"copper_inferno:katzenliebhaber737_charm", 1, "Craft 1x Katzenliebhaber737's Cat Charm at a crafting table.", "Stellt 1x Katzenliebhaber737s Katzenamulett an der Werkbank her."));
	}
}
