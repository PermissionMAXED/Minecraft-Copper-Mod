package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "materials" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/materials/} (20 entries, category
 * "items"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyMaterialsHandbook {
	private LegacyMaterialsHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("items", "materials/bottle_cap", "copper_inferno:bottle_cap", "materials/bottle_cap",
				new String[] {"minecraft:iron_nugget", "copper_inferno:copper_dust", "", "", "", "", "", "", ""},
				"copper_inferno:bottle_cap", 4, "Craft 4x Bottle Cap at a crafting table.", "Stellt 4x Kronkorken an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/charred_copper_ingot", "copper_inferno:charred_copper_ingot", "materials/charred_copper_ingot",
				new String[] {"minecraft:copper_ingot", "minecraft:charcoal", "", "", "", "", "", "", ""},
				"copper_inferno:charred_copper_ingot", 1, "Craft 1x Charred Copper Ingot at a crafting table.", "Stellt 1x Verkohlten Kupferbarren an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_alloy_ingot", "copper_inferno:copper_alloy_ingot", "materials/copper_alloy_ingot",
				new String[] {"minecraft:copper_ingot", "minecraft:iron_ingot", "copper_inferno:copper_dust", "", "", "", "", "", ""},
				"copper_inferno:copper_alloy_ingot", 1, "Craft 1x Copper Alloy Ingot at a crafting table.", "Stellt 1x Kupferlegierungsbarren an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_coil", "copper_inferno:copper_coil", "materials/copper_coil",
				new String[] {"copper_inferno:copper_wire", "copper_inferno:copper_wire", "copper_inferno:copper_wire", "copper_inferno:copper_wire", "", "copper_inferno:copper_wire", "copper_inferno:copper_wire", "copper_inferno:copper_wire", "copper_inferno:copper_wire"},
				"copper_inferno:copper_coil", 1, "Craft 1x Copper Coil at a crafting table.", "Stellt 1x Kupferspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_dust", "copper_inferno:copper_dust", "materials/copper_dust",
				new String[] {"minecraft:raw_copper", "", "", "", "", "", "", "", ""},
				"copper_inferno:copper_dust", 1, "Craft 1x Copper Dust at a crafting table.", "Stellt 1x Kupferstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_gear", "copper_inferno:copper_gear", "materials/copper_gear",
				new String[] {"", "copper_inferno:copper_rod", "", "copper_inferno:copper_rod", "minecraft:iron_nugget", "copper_inferno:copper_rod", "", "copper_inferno:copper_rod", ""},
				"copper_inferno:copper_gear", 1, "Craft 1x Copper Gear at a crafting table.", "Stellt 1x Kupferzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_ingot_from_blasting_copper_dust", "minecraft:copper_ingot", "materials/copper_ingot_from_blasting_copper_dust",
				new String[] {"", "", "", "", "copper_inferno:copper_dust", "", "", "", ""},
				"minecraft:copper_ingot", 1, "Blasting Copper Dust in a blast furnace yields Copper Ingot.", "Kupferstaub im Schmelzofen gebrannt ergibt Kupferbarren."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_ingot_from_smelting_copper_dust", "minecraft:copper_ingot", "materials/copper_ingot_from_smelting_copper_dust",
				new String[] {"", "", "", "", "copper_inferno:copper_dust", "", "", "", ""},
				"minecraft:copper_ingot", 1, "Smelting Copper Dust in a furnace yields Copper Ingot.", "Kupferstaub im Ofen gebrannt ergibt Kupferbarren."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_mesh", "copper_inferno:copper_mesh", "materials/copper_mesh",
				new String[] {"copper_inferno:copper_wire", "copper_inferno:copper_wire", "", "copper_inferno:copper_wire", "copper_inferno:copper_wire", "", "", "", ""},
				"copper_inferno:copper_mesh", 1, "Craft 1x Copper Mesh at a crafting table.", "Stellt 1x Kupfergeflecht an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_rivet", "copper_inferno:copper_rivet", "materials/copper_rivet",
				new String[] {"copper_inferno:copper_rod", "minecraft:iron_nugget", "", "", "", "", "", "", ""},
				"copper_inferno:copper_rivet", 4, "Craft 4x Copper Rivet at a crafting table.", "Stellt 4x Kupferniete an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_rod", "copper_inferno:copper_rod", "materials/copper_rod",
				new String[] {"minecraft:copper_ingot", "", "", "minecraft:copper_ingot", "", "", "", "", ""},
				"copper_inferno:copper_rod", 4, "Craft 4x Copper Rod at a crafting table.", "Stellt 4x Kupferstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_screw", "copper_inferno:copper_screw", "materials/copper_screw",
				new String[] {"copper_inferno:copper_rod", "copper_inferno:copper_dust", "", "", "", "", "", "", ""},
				"copper_inferno:copper_screw", 4, "Craft 4x Copper Screw at a crafting table.", "Stellt 4x Kupferschraube an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_sheet", "copper_inferno:copper_sheet", "materials/copper_sheet",
				new String[] {"minecraft:copper_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:copper_sheet", 2, "Craft 2x Copper Sheet at a crafting table.", "Stellt 2x Kupferblech an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_spring", "copper_inferno:copper_spring", "materials/copper_spring",
				new String[] {"copper_inferno:copper_wire", "", "", "copper_inferno:copper_wire", "", "", "", "", ""},
				"copper_inferno:copper_spring", 2, "Craft 2x Copper Spring at a crafting table.", "Stellt 2x Kupferfeder an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/copper_wire", "copper_inferno:copper_wire", "materials/copper_wire",
				new String[] {"copper_inferno:copper_rod", "", "", "copper_inferno:copper_rod", "", "", "copper_inferno:copper_rod", "", ""},
				"copper_inferno:copper_wire", 4, "Craft 4x Copper Wire at a crafting table.", "Stellt 4x Kupferdraht an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/inferno_alloy_ingot", "copper_inferno:inferno_alloy_ingot", "materials/inferno_alloy_ingot",
				new String[] {"copper_inferno:charred_copper_ingot", "copper_inferno:inferno_powder", "minecraft:blaze_rod", "", "", "", "", "", ""},
				"copper_inferno:inferno_alloy_ingot", 1, "Craft 1x Inferno Alloy Ingot at a crafting table.", "Stellt 1x Infernolegierungsbarren an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/inferno_powder", "copper_inferno:inferno_powder", "materials/inferno_powder",
				new String[] {"copper_inferno:inferno_shard", "", "", "", "", "", "", "", ""},
				"copper_inferno:inferno_powder", 2, "Craft 2x Inferno Powder at a crafting table.", "Stellt 2x Infernopulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/inferno_shard", "copper_inferno:inferno_shard", "materials/inferno_shard",
				new String[] {"minecraft:amethyst_shard", "minecraft:blaze_powder", "copper_inferno:copper_dust", "", "", "", "", "", ""},
				"copper_inferno:inferno_shard", 1, "Craft 1x Inferno Shard at a crafting table.", "Stellt 1x Infernosplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/oxidized_copper_dust", "copper_inferno:oxidized_copper_dust", "materials/oxidized_copper_dust",
				new String[] {"copper_inferno:copper_dust", "minecraft:water_bucket", "", "", "", "", "", "", ""},
				"copper_inferno:oxidized_copper_dust", 1, "Craft 1x Oxidized Copper Dust at a crafting table.", "Stellt 1x Oxidierten Kupferstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "materials/soda_essence", "copper_inferno:soda_essence", "materials/soda_essence",
				new String[] {"minecraft:sugar", "minecraft:cocoa_beans", "minecraft:honey_bottle", "", "", "", "", "", ""},
				"copper_inferno:soda_essence", 1, "Craft 1x Soda Essence at a crafting table.", "Stellt 1x Limonadenessenz an der Werkbank her."));
	}
}
