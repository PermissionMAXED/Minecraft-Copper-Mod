package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "glasslight" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/glasslight/} (14 entries, category
 * "blocks"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyGlasslightHandbook {
	private LegacyGlasslightHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/copper_chandelier", "copper_inferno:copper_chandelier", "glasslight/copper_chandelier",
				new String[] {"", "minecraft:copper_ingot", "", "minecraft:copper_ingot", "copper_inferno:inferno_lantern", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot"},
				"copper_inferno:copper_chandelier", 1, "Craft 1x Copper Chandelier at a crafting table.", "Stellt 1x Kupferkronleuchter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/copper_glass", "copper_inferno:copper_glass", "glasslight/copper_glass",
				new String[] {"minecraft:glass", "minecraft:glass", "minecraft:glass", "minecraft:glass", "minecraft:copper_ingot", "minecraft:glass", "minecraft:glass", "minecraft:glass", "minecraft:glass"},
				"copper_inferno:copper_glass", 8, "Craft 8x Copper Glass at a crafting table.", "Stellt 8x Kupferglas an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/copper_glass_pane", "copper_inferno:copper_glass_pane", "glasslight/copper_glass_pane",
				new String[] {"copper_inferno:copper_glass", "copper_inferno:copper_glass", "copper_inferno:copper_glass", "copper_inferno:copper_glass", "copper_inferno:copper_glass", "copper_inferno:copper_glass", "", "", ""},
				"copper_inferno:copper_glass_pane", 16, "Craft 16x Copper Glass Pane at a crafting table.", "Stellt 16x Kupferglasscheibe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/copper_lamp", "copper_inferno:copper_lamp", "glasslight/copper_lamp",
				new String[] {"minecraft:redstone_lamp", "minecraft:copper_block", "", "", "", "", "", "", ""},
				"copper_inferno:copper_lamp", 1, "Craft 1x Copper Lamp at a crafting table.", "Stellt 1x Kupferlampe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/exposed_copper_glass", "copper_inferno:exposed_copper_glass", "glasslight/exposed_copper_glass",
				new String[] {"copper_inferno:copper_glass", "minecraft:exposed_copper", "", "", "", "", "", "", ""},
				"copper_inferno:exposed_copper_glass", 1, "Craft 1x Exposed Copper Glass at a crafting table.", "Stellt 1x Angelaufenes Kupferglas an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/exposed_copper_lamp", "copper_inferno:exposed_copper_lamp", "glasslight/exposed_copper_lamp",
				new String[] {"minecraft:redstone_lamp", "minecraft:exposed_copper", "", "", "", "", "", "", ""},
				"copper_inferno:exposed_copper_lamp", 1, "Craft 1x Exposed Copper Lamp at a crafting table.", "Stellt 1x Angelaufene Kupferlampe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/glowing_syrup_block", "copper_inferno:glowing_syrup_block", "glasslight/glowing_syrup_block",
				new String[] {"minecraft:honey_block", "minecraft:glowstone_dust", "", "", "", "", "", "", ""},
				"copper_inferno:glowing_syrup_block", 1, "Craft 1x Glowing Syrup Block at a crafting table.", "Stellt 1x Leuchtenden Sirupblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/inferno_lantern", "copper_inferno:inferno_lantern", "glasslight/inferno_lantern",
				new String[] {"minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:blaze_powder", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot"},
				"copper_inferno:inferno_lantern", 1, "Craft 1x Inferno Lantern at a crafting table.", "Stellt 1x Infernolaterne an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/oxidized_copper_glass", "copper_inferno:oxidized_copper_glass", "glasslight/oxidized_copper_glass",
				new String[] {"copper_inferno:copper_glass", "minecraft:oxidized_copper", "", "", "", "", "", "", ""},
				"copper_inferno:oxidized_copper_glass", 1, "Craft 1x Oxidized Copper Glass at a crafting table.", "Stellt 1x Oxidiertes Kupferglas an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/oxidized_copper_glass_pane", "copper_inferno:oxidized_copper_glass_pane", "glasslight/oxidized_copper_glass_pane",
				new String[] {"copper_inferno:oxidized_copper_glass", "copper_inferno:oxidized_copper_glass", "copper_inferno:oxidized_copper_glass", "copper_inferno:oxidized_copper_glass", "copper_inferno:oxidized_copper_glass", "copper_inferno:oxidized_copper_glass", "", "", ""},
				"copper_inferno:oxidized_copper_glass_pane", 16, "Craft 16x Oxidized Copper Glass Pane at a crafting table.", "Stellt 16x Oxidierte Kupferglasscheibe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/oxidized_copper_lamp", "copper_inferno:oxidized_copper_lamp", "glasslight/oxidized_copper_lamp",
				new String[] {"minecraft:redstone_lamp", "minecraft:oxidized_copper", "", "", "", "", "", "", ""},
				"copper_inferno:oxidized_copper_lamp", 1, "Craft 1x Oxidized Copper Lamp at a crafting table.", "Stellt 1x Oxidierte Kupferlampe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/soda_lantern", "copper_inferno:soda_lantern", "glasslight/soda_lantern",
				new String[] {"minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "copper_inferno:dr_pepper", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot"},
				"copper_inferno:soda_lantern", 1, "Craft 1x Soda Lantern at a crafting table.", "Stellt 1x Limonadenlaterne an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/weathered_copper_glass", "copper_inferno:weathered_copper_glass", "glasslight/weathered_copper_glass",
				new String[] {"copper_inferno:copper_glass", "minecraft:weathered_copper", "", "", "", "", "", "", ""},
				"copper_inferno:weathered_copper_glass", 1, "Craft 1x Weathered Copper Glass at a crafting table.", "Stellt 1x Verwittertes Kupferglas an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "glasslight/weathered_copper_lamp", "copper_inferno:weathered_copper_lamp", "glasslight/weathered_copper_lamp",
				new String[] {"minecraft:redstone_lamp", "minecraft:weathered_copper", "", "", "", "", "", "", ""},
				"copper_inferno:weathered_copper_lamp", 1, "Craft 1x Weathered Copper Lamp at a crafting table.", "Stellt 1x Verwitterte Kupferlampe an der Werkbank her."));
	}
}
