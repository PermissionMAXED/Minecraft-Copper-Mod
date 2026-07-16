package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "sodablocks" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/sodablocks/} (30 entries, category
 * "blocks"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacySodablocksHandbook {
	private LegacySodablocksHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/blueberry_soda_can_block", "copper_inferno:blueberry_soda_can_block", "sodablocks/blueberry_soda_can_block",
				new String[] {"copper_inferno:dr_pepper_can_block", "minecraft:blue_dye", "", "", "", "", "", "", ""},
				"copper_inferno:blueberry_soda_can_block", 1, "Craft 1x Blueberry Soda Can Block at a crafting table.", "Stellt 1x Blaubeerlimonaden-Dosenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/bottle_cap_block", "copper_inferno:bottle_cap_block", "sodablocks/bottle_cap_block",
				new String[] {"copper_inferno:bottle_cap", "copper_inferno:bottle_cap", "copper_inferno:bottle_cap", "copper_inferno:bottle_cap", "copper_inferno:bottle_cap", "copper_inferno:bottle_cap", "copper_inferno:bottle_cap", "copper_inferno:bottle_cap", "copper_inferno:bottle_cap"},
				"copper_inferno:bottle_cap_block", 1, "Craft 1x Bottle Cap Block at a crafting table.", "Stellt 1x Kronkorkenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/caramel_block", "copper_inferno:caramel_block", "sodablocks/caramel_block",
				new String[] {"", "", "", "", "copper_inferno:sugar_block", "", "", "", ""},
				"copper_inferno:caramel_block", 1, "Smelting Sugar Block in a furnace yields Caramel Block.", "Zuckerblock im Ofen gebrannt ergibt Karamellblock."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/caramel_block_from_blasting", "copper_inferno:caramel_block", "sodablocks/caramel_block_from_blasting",
				new String[] {"", "", "", "", "copper_inferno:sugar_block", "", "", "", ""},
				"copper_inferno:caramel_block", 1, "Blasting Sugar Block in a blast furnace yields Caramel Block.", "Zuckerblock im Schmelzofen gebrannt ergibt Karamellblock."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/cherry_soda_can_block", "copper_inferno:cherry_soda_can_block", "sodablocks/cherry_soda_can_block",
				new String[] {"copper_inferno:dr_pepper_can_block", "minecraft:red_dye", "", "", "", "", "", "", ""},
				"copper_inferno:cherry_soda_can_block", 1, "Craft 1x Cherry Soda Can Block at a crafting table.", "Stellt 1x Kirschlimonaden-Dosenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/cream_soda_can_block", "copper_inferno:cream_soda_can_block", "sodablocks/cream_soda_can_block",
				new String[] {"copper_inferno:dr_pepper_can_block", "minecraft:yellow_dye", "", "", "", "", "", "", ""},
				"copper_inferno:cream_soda_can_block", 1, "Craft 1x Cream Soda Can Block at a crafting table.", "Stellt 1x Cremelimonaden-Dosenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/crushed_can_block", "copper_inferno:crushed_can_block", "sodablocks/crushed_can_block",
				new String[] {"copper_inferno:dr_pepper_can_block", "", "", "copper_inferno:dr_pepper_can_block", "", "", "", "", ""},
				"copper_inferno:crushed_can_block", 2, "Craft 2x Crushed Can Block at a crafting table.", "Stellt 2x Zerdr\u00fcckten Dosenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/diet_dr_pepper_can_block", "copper_inferno:diet_dr_pepper_can_block", "sodablocks/diet_dr_pepper_can_block",
				new String[] {"copper_inferno:dr_pepper_can_block", "minecraft:light_gray_dye", "", "", "", "", "", "", ""},
				"copper_inferno:diet_dr_pepper_can_block", 1, "Craft 1x Diet Dr.Pepper Can Block at a crafting table.", "Stellt 1x Diet-Dr.Pepper-Dosenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/dr_pepper_can_brick_slab", "copper_inferno:dr_pepper_can_brick_slab", "sodablocks/dr_pepper_can_brick_slab",
				new String[] {"copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "", "", "", "", "", ""},
				"copper_inferno:dr_pepper_can_brick_slab", 6, "Craft 6x Dr.Pepper Can Brick Slab at a crafting table.", "Stellt 6x Dr.Pepper-Dosenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/dr_pepper_can_brick_stairs", "copper_inferno:dr_pepper_can_brick_stairs", "sodablocks/dr_pepper_can_brick_stairs",
				new String[] {"copper_inferno:dr_pepper_can_bricks", "", "", "copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "", "copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks"},
				"copper_inferno:dr_pepper_can_brick_stairs", 4, "Craft 4x Dr.Pepper Can Brick Stairs at a crafting table.", "Stellt 4x Dr.Pepper-Dosenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/dr_pepper_can_brick_wall", "copper_inferno:dr_pepper_can_brick_wall", "sodablocks/dr_pepper_can_brick_wall",
				new String[] {"copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "", "", ""},
				"copper_inferno:dr_pepper_can_brick_wall", 6, "Craft 6x Dr.Pepper Can Brick Wall at a crafting table.", "Stellt 6x Dr.Pepper-Dosenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "sodablocks/dr_pepper_can_bricks",
				new String[] {"copper_inferno:dr_pepper_can_block", "copper_inferno:dr_pepper_can_block", "", "copper_inferno:dr_pepper_can_block", "copper_inferno:dr_pepper_can_block", "", "", "", ""},
				"copper_inferno:dr_pepper_can_bricks", 4, "Craft 4x Dr.Pepper Can Bricks at a crafting table.", "Stellt 4x Dr.Pepper-Dosenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/dr_pepper_can_tile_slab", "copper_inferno:dr_pepper_can_tile_slab", "sodablocks/dr_pepper_can_tile_slab",
				new String[] {"copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "", "", "", "", "", ""},
				"copper_inferno:dr_pepper_can_tile_slab", 6, "Craft 6x Dr.Pepper Can Tile Slab at a crafting table.", "Stellt 6x Dr.Pepper-Dosenfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/dr_pepper_can_tile_stairs", "copper_inferno:dr_pepper_can_tile_stairs", "sodablocks/dr_pepper_can_tile_stairs",
				new String[] {"copper_inferno:dr_pepper_can_tiles", "", "", "copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "", "copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles"},
				"copper_inferno:dr_pepper_can_tile_stairs", 4, "Craft 4x Dr.Pepper Can Tile Stairs at a crafting table.", "Stellt 4x Dr.Pepper-Dosenfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/dr_pepper_can_tile_wall", "copper_inferno:dr_pepper_can_tile_wall", "sodablocks/dr_pepper_can_tile_wall",
				new String[] {"copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "", "", ""},
				"copper_inferno:dr_pepper_can_tile_wall", 6, "Craft 6x Dr.Pepper Can Tile Wall at a crafting table.", "Stellt 6x Dr.Pepper-Dosenfliesenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/dr_pepper_can_tiles", "copper_inferno:dr_pepper_can_tiles", "sodablocks/dr_pepper_can_tiles",
				new String[] {"copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "", "copper_inferno:dr_pepper_can_bricks", "copper_inferno:dr_pepper_can_bricks", "", "", "", ""},
				"copper_inferno:dr_pepper_can_tiles", 4, "Craft 4x Dr.Pepper Can Tiles at a crafting table.", "Stellt 4x Dr.Pepper-Dosenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/dr_pepper_crate", "copper_inferno:dr_pepper_crate", "sodablocks/dr_pepper_crate",
				new String[] {"minecraft:oak_planks", "minecraft:oak_planks", "minecraft:oak_planks", "minecraft:oak_planks", "copper_inferno:dr_pepper_can_block", "minecraft:oak_planks", "minecraft:oak_planks", "minecraft:oak_planks", "minecraft:oak_planks"},
				"copper_inferno:dr_pepper_crate", 1, "Craft 1x Dr.Pepper Crate at a crafting table.", "Stellt 1x Dr.Pepper-Kiste an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/fizzy_soda_block", "copper_inferno:fizzy_soda_block", "sodablocks/fizzy_soda_block",
				new String[] {"copper_inferno:sugar_block", "minecraft:gunpowder", "", "", "", "", "", "", ""},
				"copper_inferno:fizzy_soda_block", 1, "Craft 1x Fizzy Soda Block at a crafting table.", "Stellt 1x Sprudellimonadenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/grape_soda_can_block", "copper_inferno:grape_soda_can_block", "sodablocks/grape_soda_can_block",
				new String[] {"copper_inferno:dr_pepper_can_block", "minecraft:purple_dye", "", "", "", "", "", "", ""},
				"copper_inferno:grape_soda_can_block", 1, "Craft 1x Grape Soda Can Block at a crafting table.", "Stellt 1x Traubenlimonaden-Dosenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/lime_soda_can_block", "copper_inferno:lime_soda_can_block", "sodablocks/lime_soda_can_block",
				new String[] {"copper_inferno:dr_pepper_can_block", "minecraft:lime_dye", "", "", "", "", "", "", ""},
				"copper_inferno:lime_soda_can_block", 1, "Craft 1x Lime Soda Can Block at a crafting table.", "Stellt 1x Limettenlimonaden-Dosenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/orange_soda_can_block", "copper_inferno:orange_soda_can_block", "sodablocks/orange_soda_can_block",
				new String[] {"copper_inferno:dr_pepper_can_block", "minecraft:orange_dye", "", "", "", "", "", "", ""},
				"copper_inferno:orange_soda_can_block", 1, "Craft 1x Orange Soda Can Block at a crafting table.", "Stellt 1x Orangenlimonaden-Dosenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/soda_ice_block", "copper_inferno:soda_ice_block", "sodablocks/soda_ice_block",
				new String[] {"minecraft:ice", "minecraft:sugar", "", "", "", "", "", "", ""},
				"copper_inferno:soda_ice_block", 1, "Craft 1x Soda Ice Block at a crafting table.", "Stellt 1x Limonadeneisblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/soda_syrup_block", "copper_inferno:soda_syrup_block", "sodablocks/soda_syrup_block",
				new String[] {"copper_inferno:sugar_block", "minecraft:honey_bottle", "", "", "", "", "", "", ""},
				"copper_inferno:soda_syrup_block", 1, "Craft 1x Soda Syrup Block at a crafting table.", "Stellt 1x Limonadensirupblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/sugar_block", "copper_inferno:sugar_block", "sodablocks/sugar_block",
				new String[] {"minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar"},
				"copper_inferno:sugar_block", 1, "Craft 1x Sugar Block at a crafting table.", "Stellt 1x Zuckerblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/sugar_brick_slab", "copper_inferno:sugar_brick_slab", "sodablocks/sugar_brick_slab",
				new String[] {"copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks", "", "", "", "", "", ""},
				"copper_inferno:sugar_brick_slab", 6, "Craft 6x Sugar Brick Slab at a crafting table.", "Stellt 6x Zuckerziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/sugar_brick_stairs", "copper_inferno:sugar_brick_stairs", "sodablocks/sugar_brick_stairs",
				new String[] {"copper_inferno:sugar_bricks", "", "", "copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks", "", "copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks"},
				"copper_inferno:sugar_brick_stairs", 4, "Craft 4x Sugar Brick Stairs at a crafting table.", "Stellt 4x Zuckerziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/sugar_brick_wall", "copper_inferno:sugar_brick_wall", "sodablocks/sugar_brick_wall",
				new String[] {"copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks", "copper_inferno:sugar_bricks", "", "", ""},
				"copper_inferno:sugar_brick_wall", 6, "Craft 6x Sugar Brick Wall at a crafting table.", "Stellt 6x Zuckerziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/sugar_bricks", "copper_inferno:sugar_bricks", "sodablocks/sugar_bricks",
				new String[] {"copper_inferno:sugar_block", "copper_inferno:sugar_block", "", "copper_inferno:sugar_block", "copper_inferno:sugar_block", "", "", "", ""},
				"copper_inferno:sugar_bricks", 4, "Craft 4x Sugar Bricks at a crafting table.", "Stellt 4x Zuckerziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/sugar_from_sugar_block", "minecraft:sugar", "sodablocks/sugar_from_sugar_block",
				new String[] {"copper_inferno:sugar_block", "", "", "", "", "", "", "", ""},
				"minecraft:sugar", 9, "Craft 9x Sugar at a crafting table.", "Stellt 9x Zucker an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "sodablocks/vanilla_soda_can_block", "copper_inferno:vanilla_soda_can_block", "sodablocks/vanilla_soda_can_block",
				new String[] {"copper_inferno:dr_pepper_can_block", "minecraft:white_dye", "", "", "", "", "", "", ""},
				"copper_inferno:vanilla_soda_can_block", 1, "Craft 1x Vanilla Soda Can Block at a crafting table.", "Stellt 1x Vanillelimonaden-Dosenblock an der Werkbank her."));
	}
}
