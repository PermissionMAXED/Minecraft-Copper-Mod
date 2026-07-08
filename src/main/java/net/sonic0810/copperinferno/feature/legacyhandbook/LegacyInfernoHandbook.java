package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "inferno" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/inferno/} (18 entries, category
 * "blocks"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyInfernoHandbook {
	private LegacyInfernoHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "inferno/charred_copper", "copper_inferno:charred_copper", "inferno/charred_copper",
				new String[] {"minecraft:copper_block", "minecraft:charcoal", "", "", "", "", "", "", ""},
				"copper_inferno:charred_copper", 1, "Craft 1x Charred Copper at a crafting table.", "Stellt 1x Verkohltes Kupfer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/charred_copper_brick_slab", "copper_inferno:charred_copper_brick_slab", "inferno/charred_copper_brick_slab",
				new String[] {"copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks", "", "", "", "", "", ""},
				"copper_inferno:charred_copper_brick_slab", 6, "Craft 6x Charred Copper Brick Slab at a crafting table.", "Stellt 6x Verkohlte Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/charred_copper_brick_stairs", "copper_inferno:charred_copper_brick_stairs", "inferno/charred_copper_brick_stairs",
				new String[] {"copper_inferno:charred_copper_bricks", "", "", "copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks", "", "copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks"},
				"copper_inferno:charred_copper_brick_stairs", 4, "Craft 4x Charred Copper Brick Stairs at a crafting table.", "Stellt 4x Verkohlte Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/charred_copper_brick_wall", "copper_inferno:charred_copper_brick_wall", "inferno/charred_copper_brick_wall",
				new String[] {"copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks", "copper_inferno:charred_copper_bricks", "", "", ""},
				"copper_inferno:charred_copper_brick_wall", 6, "Craft 6x Charred Copper Brick Wall at a crafting table.", "Stellt 6x Verkohlte Kupferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/charred_copper_bricks", "copper_inferno:charred_copper_bricks", "inferno/charred_copper_bricks",
				new String[] {"copper_inferno:charred_copper", "copper_inferno:charred_copper", "", "copper_inferno:charred_copper", "copper_inferno:charred_copper", "", "", "", ""},
				"copper_inferno:charred_copper_bricks", 4, "Craft 4x Charred Copper Bricks at a crafting table.", "Stellt 4x Verkohlte Kupferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/chiseled_inferno_bricks", "copper_inferno:chiseled_inferno_bricks", "inferno/chiseled_inferno_bricks",
				new String[] {"copper_inferno:inferno_brick_slab", "", "", "copper_inferno:inferno_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_inferno_bricks", 1, "Craft 1x Chiseled Inferno Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Infernoziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/cracked_inferno_bricks", "copper_inferno:cracked_inferno_bricks", "inferno/cracked_inferno_bricks",
				new String[] {"", "", "", "", "copper_inferno:inferno_bricks", "", "", "", ""},
				"copper_inferno:cracked_inferno_bricks", 1, "Smelting Inferno Bricks in a furnace yields Cracked Inferno Bricks.", "Infernoziegel im Ofen gebrannt ergibt Rissige Infernoziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/cracked_inferno_bricks_from_blasting", "copper_inferno:cracked_inferno_bricks", "inferno/cracked_inferno_bricks_from_blasting",
				new String[] {"", "", "", "", "copper_inferno:inferno_bricks", "", "", "", ""},
				"copper_inferno:cracked_inferno_bricks", 1, "Blasting Inferno Bricks in a blast furnace yields Cracked Inferno Bricks.", "Infernoziegel im Schmelzofen gebrannt ergibt Rissige Infernoziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_brick_slab", "copper_inferno:inferno_brick_slab", "inferno/inferno_brick_slab",
				new String[] {"copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "", "", "", "", "", ""},
				"copper_inferno:inferno_brick_slab", 6, "Craft 6x Inferno Brick Slab at a crafting table.", "Stellt 6x Infernoziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_brick_stairs", "copper_inferno:inferno_brick_stairs", "inferno/inferno_brick_stairs",
				new String[] {"copper_inferno:inferno_bricks", "", "", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks"},
				"copper_inferno:inferno_brick_stairs", 4, "Craft 4x Inferno Brick Stairs at a crafting table.", "Stellt 4x Infernoziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_brick_wall", "copper_inferno:inferno_brick_wall", "inferno/inferno_brick_wall",
				new String[] {"copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "", "", ""},
				"copper_inferno:inferno_brick_wall", 6, "Craft 6x Inferno Brick Wall at a crafting table.", "Stellt 6x Infernoziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_bricks", "copper_inferno:inferno_bricks", "inferno/inferno_bricks",
				new String[] {"minecraft:blackstone", "", "minecraft:blackstone", "", "minecraft:magma_cream", "", "minecraft:blackstone", "", "minecraft:blackstone"},
				"copper_inferno:inferno_bricks", 4, "Craft 4x Inferno Bricks at a crafting table.", "Stellt 4x Infernoziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_core", "copper_inferno:inferno_core", "inferno/inferno_core",
				new String[] {"copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "minecraft:magma_block", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks"},
				"copper_inferno:inferno_core", 1, "Craft 1x Inferno Core at a crafting table.", "Stellt 1x Infernokern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_pillar", "copper_inferno:inferno_pillar", "inferno/inferno_pillar",
				new String[] {"copper_inferno:inferno_bricks", "", "", "copper_inferno:inferno_bricks", "", "", "copper_inferno:inferno_bricks", "", ""},
				"copper_inferno:inferno_pillar", 3, "Craft 3x Inferno Pillar at a crafting table.", "Stellt 3x Infernos\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_tile_slab", "copper_inferno:inferno_tile_slab", "inferno/inferno_tile_slab",
				new String[] {"copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles", "", "", "", "", "", ""},
				"copper_inferno:inferno_tile_slab", 6, "Craft 6x Inferno Tile Slab at a crafting table.", "Stellt 6x Infernofliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_tile_stairs", "copper_inferno:inferno_tile_stairs", "inferno/inferno_tile_stairs",
				new String[] {"copper_inferno:inferno_tiles", "", "", "copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles", "", "copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles"},
				"copper_inferno:inferno_tile_stairs", 4, "Craft 4x Inferno Tile Stairs at a crafting table.", "Stellt 4x Infernofliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_tile_wall", "copper_inferno:inferno_tile_wall", "inferno/inferno_tile_wall",
				new String[] {"copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles", "copper_inferno:inferno_tiles", "", "", ""},
				"copper_inferno:inferno_tile_wall", 6, "Craft 6x Inferno Tile Wall at a crafting table.", "Stellt 6x Infernofliesenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "inferno/inferno_tiles", "copper_inferno:inferno_tiles", "inferno/inferno_tiles",
				new String[] {"copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "", "copper_inferno:inferno_bricks", "copper_inferno:inferno_bricks", "", "", "", ""},
				"copper_inferno:inferno_tiles", 4, "Craft 4x Inferno Tiles at a crafting table.", "Stellt 4x Infernofliesen an der Werkbank her."));
	}
}
