package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "utilityblocks" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/utilityblocks/} (16 entries, category
 * "blocks"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyUtilityblocksHandbook {
	private LegacyUtilityblocksHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/copper_button", "copper_inferno:copper_button", "utilityblocks/copper_button",
				new String[] {"minecraft:cut_copper", "", "", "", "", "", "", "", ""},
				"copper_inferno:copper_button", 1, "Craft 1x Copper Button at a crafting table.", "Stellt 1x Kupferknopf an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/copper_crate", "copper_inferno:copper_crate", "utilityblocks/copper_crate",
				new String[] {"minecraft:oak_planks", "minecraft:copper_ingot", "minecraft:oak_planks", "minecraft:copper_ingot", "", "minecraft:copper_ingot", "minecraft:oak_planks", "minecraft:copper_ingot", "minecraft:oak_planks"},
				"copper_inferno:copper_crate", 2, "Craft 2x Copper Crate at a crafting table.", "Stellt 2x Kupferkiste an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/copper_fence", "copper_inferno:copper_fence", "utilityblocks/copper_fence",
				new String[] {"minecraft:copper_ingot", "minecraft:iron_nugget", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:iron_nugget", "minecraft:copper_ingot", "", "", ""},
				"copper_inferno:copper_fence", 3, "Craft 3x Copper Fence at a crafting table.", "Stellt 3x Kupferzaun an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/copper_fence_gate", "copper_inferno:copper_fence_gate", "utilityblocks/copper_fence_gate",
				new String[] {"minecraft:iron_nugget", "minecraft:copper_ingot", "minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:copper_ingot", "minecraft:iron_nugget", "", "", ""},
				"copper_inferno:copper_fence_gate", 1, "Craft 1x Copper Fence Gate at a crafting table.", "Stellt 1x Kupferzauntor an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/copper_pipe", "copper_inferno:copper_pipe", "utilityblocks/copper_pipe",
				new String[] {"minecraft:copper_ingot", "", "minecraft:copper_ingot", "minecraft:copper_ingot", "", "minecraft:copper_ingot", "minecraft:copper_ingot", "", "minecraft:copper_ingot"},
				"copper_inferno:copper_pipe", 6, "Craft 6x Copper Pipe at a crafting table.", "Stellt 6x Kupferrohr an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/copper_plating", "copper_inferno:copper_plating", "utilityblocks/copper_plating",
				new String[] {"copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "", "copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "", "", "", ""},
				"copper_inferno:copper_plating", 4, "Craft 4x Copper Plating at a crafting table.", "Stellt 4x Kupferplattierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/copper_pressure_plate", "copper_inferno:copper_pressure_plate", "utilityblocks/copper_pressure_plate",
				new String[] {"minecraft:copper_block", "minecraft:copper_block", "", "", "", "", "", "", ""},
				"copper_inferno:copper_pressure_plate", 1, "Craft 1x Copper Pressure Plate at a crafting table.", "Stellt 1x Kupferdruckplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/exposed_copper_button", "copper_inferno:exposed_copper_button", "utilityblocks/exposed_copper_button",
				new String[] {"minecraft:exposed_copper", "", "", "", "", "", "", "", ""},
				"copper_inferno:exposed_copper_button", 1, "Craft 1x Exposed Copper Button at a crafting table.", "Stellt 1x Angelaufenen Kupferknopf an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/exposed_copper_crate", "copper_inferno:exposed_copper_crate", "utilityblocks/exposed_copper_crate",
				new String[] {"copper_inferno:copper_crate", "minecraft:exposed_copper", "", "", "", "", "", "", ""},
				"copper_inferno:exposed_copper_crate", 1, "Craft 1x Exposed Copper Crate at a crafting table.", "Stellt 1x Angelaufene Kupferkiste an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/exposed_copper_pressure_plate", "copper_inferno:exposed_copper_pressure_plate", "utilityblocks/exposed_copper_pressure_plate",
				new String[] {"minecraft:exposed_copper", "minecraft:exposed_copper", "", "", "", "", "", "", ""},
				"copper_inferno:exposed_copper_pressure_plate", 1, "Craft 1x Exposed Copper Pressure Plate at a crafting table.", "Stellt 1x Angelaufene Kupferdruckplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/oxidized_copper_button", "copper_inferno:oxidized_copper_button", "utilityblocks/oxidized_copper_button",
				new String[] {"minecraft:oxidized_copper", "", "", "", "", "", "", "", ""},
				"copper_inferno:oxidized_copper_button", 1, "Craft 1x Oxidized Copper Button at a crafting table.", "Stellt 1x Oxidierten Kupferknopf an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/oxidized_copper_crate", "copper_inferno:oxidized_copper_crate", "utilityblocks/oxidized_copper_crate",
				new String[] {"copper_inferno:copper_crate", "minecraft:oxidized_copper", "", "", "", "", "", "", ""},
				"copper_inferno:oxidized_copper_crate", 1, "Craft 1x Oxidized Copper Crate at a crafting table.", "Stellt 1x Oxidierte Kupferkiste an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/oxidized_copper_pressure_plate", "copper_inferno:oxidized_copper_pressure_plate", "utilityblocks/oxidized_copper_pressure_plate",
				new String[] {"minecraft:oxidized_copper", "minecraft:oxidized_copper", "", "", "", "", "", "", ""},
				"copper_inferno:oxidized_copper_pressure_plate", 1, "Craft 1x Oxidized Copper Pressure Plate at a crafting table.", "Stellt 1x Oxidierte Kupferdruckplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/weathered_copper_button", "copper_inferno:weathered_copper_button", "utilityblocks/weathered_copper_button",
				new String[] {"minecraft:weathered_copper", "", "", "", "", "", "", "", ""},
				"copper_inferno:weathered_copper_button", 1, "Craft 1x Weathered Copper Button at a crafting table.", "Stellt 1x Verwitterten Kupferknopf an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/weathered_copper_crate", "copper_inferno:weathered_copper_crate", "utilityblocks/weathered_copper_crate",
				new String[] {"copper_inferno:copper_crate", "minecraft:weathered_copper", "", "", "", "", "", "", ""},
				"copper_inferno:weathered_copper_crate", 1, "Craft 1x Weathered Copper Crate at a crafting table.", "Stellt 1x Verwitterte Kupferkiste an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "utilityblocks/weathered_copper_pressure_plate", "copper_inferno:weathered_copper_pressure_plate", "utilityblocks/weathered_copper_pressure_plate",
				new String[] {"minecraft:weathered_copper", "minecraft:weathered_copper", "", "", "", "", "", "", ""},
				"copper_inferno:weathered_copper_pressure_plate", 1, "Craft 1x Weathered Copper Pressure Plate at a crafting table.", "Stellt 1x Verwitterte Kupferdruckplatte an der Werkbank her."));
	}
}
