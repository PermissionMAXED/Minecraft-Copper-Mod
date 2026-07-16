package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "masonry" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/masonry/} (51 entries, category
 * "blocks"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyMasonryHandbook {
	private LegacyMasonryHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "masonry/copper_brick_slab", "copper_inferno:copper_brick_slab", "masonry/copper_brick_slab",
				new String[] {"copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "", "", "", "", "", ""},
				"copper_inferno:copper_brick_slab", 6, "Craft 6x Copper Brick Slab at a crafting table.", "Stellt 6x Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/copper_brick_stairs", "copper_inferno:copper_brick_stairs", "masonry/copper_brick_stairs",
				new String[] {"copper_inferno:copper_bricks", "", "", "copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "", "copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "copper_inferno:copper_bricks"},
				"copper_inferno:copper_brick_stairs", 4, "Craft 4x Copper Brick Stairs at a crafting table.", "Stellt 4x Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/copper_brick_wall", "copper_inferno:copper_brick_wall", "masonry/copper_brick_wall",
				new String[] {"copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "", "", ""},
				"copper_inferno:copper_brick_wall", 6, "Craft 6x Copper Brick Wall at a crafting table.", "Stellt 6x Kupferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/copper_bricks", "copper_inferno:copper_bricks", "masonry/copper_bricks",
				new String[] {"copper_inferno:copper_dust", "copper_inferno:copper_dust", "", "copper_inferno:copper_dust", "copper_inferno:copper_dust", "", "", "", ""},
				"copper_inferno:copper_bricks", 4, "Craft 4x Copper Bricks at a crafting table.", "Stellt 4x Kupferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/copper_bricks_from_copper_block_stonecutting", "copper_inferno:copper_bricks", "masonry/copper_bricks_from_copper_block_stonecutting",
				new String[] {"", "", "", "", "minecraft:copper_block", "", "", "", ""},
				"copper_inferno:copper_bricks", 4, "Stonecutting: cut 4x Copper Bricks from Block of Copper.", "Steins\u00e4ge: 4x Kupferziegel aus Kupferblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/copper_tile_slab", "copper_inferno:copper_tile_slab", "masonry/copper_tile_slab",
				new String[] {"copper_inferno:copper_tiles", "copper_inferno:copper_tiles", "copper_inferno:copper_tiles", "", "", "", "", "", ""},
				"copper_inferno:copper_tile_slab", 6, "Craft 6x Copper Tile Slab at a crafting table.", "Stellt 6x Kupferfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/copper_tile_stairs", "copper_inferno:copper_tile_stairs", "masonry/copper_tile_stairs",
				new String[] {"copper_inferno:copper_tiles", "", "", "copper_inferno:copper_tiles", "copper_inferno:copper_tiles", "", "copper_inferno:copper_tiles", "copper_inferno:copper_tiles", "copper_inferno:copper_tiles"},
				"copper_inferno:copper_tile_stairs", 4, "Craft 4x Copper Tile Stairs at a crafting table.", "Stellt 4x Kupferfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/copper_tile_wall", "copper_inferno:copper_tile_wall", "masonry/copper_tile_wall",
				new String[] {"copper_inferno:copper_tiles", "copper_inferno:copper_tiles", "copper_inferno:copper_tiles", "copper_inferno:copper_tiles", "copper_inferno:copper_tiles", "copper_inferno:copper_tiles", "", "", ""},
				"copper_inferno:copper_tile_wall", 6, "Craft 6x Copper Tile Wall at a crafting table.", "Stellt 6x Kupferfliesenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/copper_tiles", "copper_inferno:copper_tiles", "masonry/copper_tiles",
				new String[] {"copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "", "copper_inferno:copper_bricks", "copper_inferno:copper_bricks", "", "", "", ""},
				"copper_inferno:copper_tiles", 4, "Craft 4x Copper Tiles at a crafting table.", "Stellt 4x Kupferfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/exposed_copper_brick_slab", "copper_inferno:exposed_copper_brick_slab", "masonry/exposed_copper_brick_slab",
				new String[] {"copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks", "", "", "", "", "", ""},
				"copper_inferno:exposed_copper_brick_slab", 6, "Craft 6x Exposed Copper Brick Slab at a crafting table.", "Stellt 6x Angelaufene Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/exposed_copper_brick_stairs", "copper_inferno:exposed_copper_brick_stairs", "masonry/exposed_copper_brick_stairs",
				new String[] {"copper_inferno:exposed_copper_bricks", "", "", "copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks", "", "copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks"},
				"copper_inferno:exposed_copper_brick_stairs", 4, "Craft 4x Exposed Copper Brick Stairs at a crafting table.", "Stellt 4x Angelaufene Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/exposed_copper_brick_wall", "copper_inferno:exposed_copper_brick_wall", "masonry/exposed_copper_brick_wall",
				new String[] {"copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks", "copper_inferno:exposed_copper_bricks", "", "", ""},
				"copper_inferno:exposed_copper_brick_wall", 6, "Craft 6x Exposed Copper Brick Wall at a crafting table.", "Stellt 6x Angelaufene Kupferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/exposed_copper_tile_slab", "copper_inferno:exposed_copper_tile_slab", "masonry/exposed_copper_tile_slab",
				new String[] {"copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles", "", "", "", "", "", ""},
				"copper_inferno:exposed_copper_tile_slab", 6, "Craft 6x Exposed Copper Tile Slab at a crafting table.", "Stellt 6x Angelaufene Kupferfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/exposed_copper_tile_stairs", "copper_inferno:exposed_copper_tile_stairs", "masonry/exposed_copper_tile_stairs",
				new String[] {"copper_inferno:exposed_copper_tiles", "", "", "copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles", "", "copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles"},
				"copper_inferno:exposed_copper_tile_stairs", 4, "Craft 4x Exposed Copper Tile Stairs at a crafting table.", "Stellt 4x Angelaufene Kupferfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/exposed_copper_tile_wall", "copper_inferno:exposed_copper_tile_wall", "masonry/exposed_copper_tile_wall",
				new String[] {"copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles", "copper_inferno:exposed_copper_tiles", "", "", ""},
				"copper_inferno:exposed_copper_tile_wall", 6, "Craft 6x Exposed Copper Tile Wall at a crafting table.", "Stellt 6x Angelaufene Kupferfliesenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/oxidized_copper_brick_slab", "copper_inferno:oxidized_copper_brick_slab", "masonry/oxidized_copper_brick_slab",
				new String[] {"copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks", "", "", "", "", "", ""},
				"copper_inferno:oxidized_copper_brick_slab", 6, "Craft 6x Oxidized Copper Brick Slab at a crafting table.", "Stellt 6x Oxidierte Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/oxidized_copper_brick_stairs", "copper_inferno:oxidized_copper_brick_stairs", "masonry/oxidized_copper_brick_stairs",
				new String[] {"copper_inferno:oxidized_copper_bricks", "", "", "copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks", "", "copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks"},
				"copper_inferno:oxidized_copper_brick_stairs", 4, "Craft 4x Oxidized Copper Brick Stairs at a crafting table.", "Stellt 4x Oxidierte Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/oxidized_copper_brick_wall", "copper_inferno:oxidized_copper_brick_wall", "masonry/oxidized_copper_brick_wall",
				new String[] {"copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks", "copper_inferno:oxidized_copper_bricks", "", "", ""},
				"copper_inferno:oxidized_copper_brick_wall", 6, "Craft 6x Oxidized Copper Brick Wall at a crafting table.", "Stellt 6x Oxidierte Kupferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/oxidized_copper_tile_slab", "copper_inferno:oxidized_copper_tile_slab", "masonry/oxidized_copper_tile_slab",
				new String[] {"copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles", "", "", "", "", "", ""},
				"copper_inferno:oxidized_copper_tile_slab", 6, "Craft 6x Oxidized Copper Tile Slab at a crafting table.", "Stellt 6x Oxidierte Kupferfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/oxidized_copper_tile_stairs", "copper_inferno:oxidized_copper_tile_stairs", "masonry/oxidized_copper_tile_stairs",
				new String[] {"copper_inferno:oxidized_copper_tiles", "", "", "copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles", "", "copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles"},
				"copper_inferno:oxidized_copper_tile_stairs", 4, "Craft 4x Oxidized Copper Tile Stairs at a crafting table.", "Stellt 4x Oxidierte Kupferfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/oxidized_copper_tile_wall", "copper_inferno:oxidized_copper_tile_wall", "masonry/oxidized_copper_tile_wall",
				new String[] {"copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles", "copper_inferno:oxidized_copper_tiles", "", "", ""},
				"copper_inferno:oxidized_copper_tile_wall", 6, "Craft 6x Oxidized Copper Tile Wall at a crafting table.", "Stellt 6x Oxidierte Kupferfliesenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_copper_brick_slab_from_honeycomb", "copper_inferno:waxed_copper_brick_slab", "masonry/waxed_copper_brick_slab_from_honeycomb",
				new String[] {"copper_inferno:copper_brick_slab", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_copper_brick_slab", 1, "Craft 1x Waxed Copper Brick Slab at a crafting table.", "Stellt 1x Gewachste Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_copper_brick_stairs_from_honeycomb", "copper_inferno:waxed_copper_brick_stairs", "masonry/waxed_copper_brick_stairs_from_honeycomb",
				new String[] {"copper_inferno:copper_brick_stairs", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_copper_brick_stairs", 1, "Craft 1x Waxed Copper Brick Stairs at a crafting table.", "Stellt 1x Gewachste Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_copper_bricks_from_honeycomb", "copper_inferno:waxed_copper_bricks", "masonry/waxed_copper_bricks_from_honeycomb",
				new String[] {"copper_inferno:copper_bricks", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_copper_bricks", 1, "Craft 1x Waxed Copper Bricks at a crafting table.", "Stellt 1x Gewachste Kupferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_copper_tile_slab_from_honeycomb", "copper_inferno:waxed_copper_tile_slab", "masonry/waxed_copper_tile_slab_from_honeycomb",
				new String[] {"copper_inferno:copper_tile_slab", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_copper_tile_slab", 1, "Craft 1x Waxed Copper Tile Slab at a crafting table.", "Stellt 1x Gewachste Kupferfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_copper_tile_stairs_from_honeycomb", "copper_inferno:waxed_copper_tile_stairs", "masonry/waxed_copper_tile_stairs_from_honeycomb",
				new String[] {"copper_inferno:copper_tile_stairs", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_copper_tile_stairs", 1, "Craft 1x Waxed Copper Tile Stairs at a crafting table.", "Stellt 1x Gewachste Kupferfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_copper_tiles_from_honeycomb", "copper_inferno:waxed_copper_tiles", "masonry/waxed_copper_tiles_from_honeycomb",
				new String[] {"copper_inferno:copper_tiles", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_copper_tiles", 1, "Craft 1x Waxed Copper Tiles at a crafting table.", "Stellt 1x Gewachste Kupferfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_exposed_copper_brick_slab_from_honeycomb", "copper_inferno:waxed_exposed_copper_brick_slab", "masonry/waxed_exposed_copper_brick_slab_from_honeycomb",
				new String[] {"copper_inferno:exposed_copper_brick_slab", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_exposed_copper_brick_slab", 1, "Craft 1x Waxed Exposed Copper Brick Slab at a crafting table.", "Stellt 1x Gewachste angelaufene Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_exposed_copper_brick_stairs_from_honeycomb", "copper_inferno:waxed_exposed_copper_brick_stairs", "masonry/waxed_exposed_copper_brick_stairs_from_honeycomb",
				new String[] {"copper_inferno:exposed_copper_brick_stairs", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_exposed_copper_brick_stairs", 1, "Craft 1x Waxed Exposed Copper Brick Stairs at a crafting table.", "Stellt 1x Gewachste angelaufene Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_exposed_copper_bricks_from_honeycomb", "copper_inferno:waxed_exposed_copper_bricks", "masonry/waxed_exposed_copper_bricks_from_honeycomb",
				new String[] {"copper_inferno:exposed_copper_bricks", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_exposed_copper_bricks", 1, "Craft 1x Waxed Exposed Copper Bricks at a crafting table.", "Stellt 1x Gewachste angelaufene Kupferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_exposed_copper_tile_slab_from_honeycomb", "copper_inferno:waxed_exposed_copper_tile_slab", "masonry/waxed_exposed_copper_tile_slab_from_honeycomb",
				new String[] {"copper_inferno:exposed_copper_tile_slab", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_exposed_copper_tile_slab", 1, "Craft 1x Waxed Exposed Copper Tile Slab at a crafting table.", "Stellt 1x Gewachste angelaufene Kupferfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_exposed_copper_tile_stairs_from_honeycomb", "copper_inferno:waxed_exposed_copper_tile_stairs", "masonry/waxed_exposed_copper_tile_stairs_from_honeycomb",
				new String[] {"copper_inferno:exposed_copper_tile_stairs", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_exposed_copper_tile_stairs", 1, "Craft 1x Waxed Exposed Copper Tile Stairs at a crafting table.", "Stellt 1x Gewachste angelaufene Kupferfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_exposed_copper_tiles_from_honeycomb", "copper_inferno:waxed_exposed_copper_tiles", "masonry/waxed_exposed_copper_tiles_from_honeycomb",
				new String[] {"copper_inferno:exposed_copper_tiles", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_exposed_copper_tiles", 1, "Craft 1x Waxed Exposed Copper Tiles at a crafting table.", "Stellt 1x Gewachste angelaufene Kupferfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_oxidized_copper_brick_slab_from_honeycomb", "copper_inferno:waxed_oxidized_copper_brick_slab", "masonry/waxed_oxidized_copper_brick_slab_from_honeycomb",
				new String[] {"copper_inferno:oxidized_copper_brick_slab", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_oxidized_copper_brick_slab", 1, "Craft 1x Waxed Oxidized Copper Brick Slab at a crafting table.", "Stellt 1x Gewachste oxidierte Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_oxidized_copper_brick_stairs_from_honeycomb", "copper_inferno:waxed_oxidized_copper_brick_stairs", "masonry/waxed_oxidized_copper_brick_stairs_from_honeycomb",
				new String[] {"copper_inferno:oxidized_copper_brick_stairs", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_oxidized_copper_brick_stairs", 1, "Craft 1x Waxed Oxidized Copper Brick Stairs at a crafting table.", "Stellt 1x Gewachste oxidierte Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_oxidized_copper_bricks_from_honeycomb", "copper_inferno:waxed_oxidized_copper_bricks", "masonry/waxed_oxidized_copper_bricks_from_honeycomb",
				new String[] {"copper_inferno:oxidized_copper_bricks", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_oxidized_copper_bricks", 1, "Craft 1x Waxed Oxidized Copper Bricks at a crafting table.", "Stellt 1x Gewachste oxidierte Kupferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_oxidized_copper_tile_slab_from_honeycomb", "copper_inferno:waxed_oxidized_copper_tile_slab", "masonry/waxed_oxidized_copper_tile_slab_from_honeycomb",
				new String[] {"copper_inferno:oxidized_copper_tile_slab", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_oxidized_copper_tile_slab", 1, "Craft 1x Waxed Oxidized Copper Tile Slab at a crafting table.", "Stellt 1x Gewachste oxidierte Kupferfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_oxidized_copper_tile_stairs_from_honeycomb", "copper_inferno:waxed_oxidized_copper_tile_stairs", "masonry/waxed_oxidized_copper_tile_stairs_from_honeycomb",
				new String[] {"copper_inferno:oxidized_copper_tile_stairs", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_oxidized_copper_tile_stairs", 1, "Craft 1x Waxed Oxidized Copper Tile Stairs at a crafting table.", "Stellt 1x Gewachste oxidierte Kupferfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_oxidized_copper_tiles_from_honeycomb", "copper_inferno:waxed_oxidized_copper_tiles", "masonry/waxed_oxidized_copper_tiles_from_honeycomb",
				new String[] {"copper_inferno:oxidized_copper_tiles", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_oxidized_copper_tiles", 1, "Craft 1x Waxed Oxidized Copper Tiles at a crafting table.", "Stellt 1x Gewachste oxidierte Kupferfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_weathered_copper_brick_slab_from_honeycomb", "copper_inferno:waxed_weathered_copper_brick_slab", "masonry/waxed_weathered_copper_brick_slab_from_honeycomb",
				new String[] {"copper_inferno:weathered_copper_brick_slab", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_weathered_copper_brick_slab", 1, "Craft 1x Waxed Weathered Copper Brick Slab at a crafting table.", "Stellt 1x Gewachste verwitterte Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_weathered_copper_brick_stairs_from_honeycomb", "copper_inferno:waxed_weathered_copper_brick_stairs", "masonry/waxed_weathered_copper_brick_stairs_from_honeycomb",
				new String[] {"copper_inferno:weathered_copper_brick_stairs", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_weathered_copper_brick_stairs", 1, "Craft 1x Waxed Weathered Copper Brick Stairs at a crafting table.", "Stellt 1x Gewachste verwitterte Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_weathered_copper_bricks_from_honeycomb", "copper_inferno:waxed_weathered_copper_bricks", "masonry/waxed_weathered_copper_bricks_from_honeycomb",
				new String[] {"copper_inferno:weathered_copper_bricks", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_weathered_copper_bricks", 1, "Craft 1x Waxed Weathered Copper Bricks at a crafting table.", "Stellt 1x Gewachste verwitterte Kupferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_weathered_copper_tile_slab_from_honeycomb", "copper_inferno:waxed_weathered_copper_tile_slab", "masonry/waxed_weathered_copper_tile_slab_from_honeycomb",
				new String[] {"copper_inferno:weathered_copper_tile_slab", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_weathered_copper_tile_slab", 1, "Craft 1x Waxed Weathered Copper Tile Slab at a crafting table.", "Stellt 1x Gewachste verwitterte Kupferfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_weathered_copper_tile_stairs_from_honeycomb", "copper_inferno:waxed_weathered_copper_tile_stairs", "masonry/waxed_weathered_copper_tile_stairs_from_honeycomb",
				new String[] {"copper_inferno:weathered_copper_tile_stairs", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_weathered_copper_tile_stairs", 1, "Craft 1x Waxed Weathered Copper Tile Stairs at a crafting table.", "Stellt 1x Gewachste verwitterte Kupferfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/waxed_weathered_copper_tiles_from_honeycomb", "copper_inferno:waxed_weathered_copper_tiles", "masonry/waxed_weathered_copper_tiles_from_honeycomb",
				new String[] {"copper_inferno:weathered_copper_tiles", "minecraft:honeycomb", "", "", "", "", "", "", ""},
				"copper_inferno:waxed_weathered_copper_tiles", 1, "Craft 1x Waxed Weathered Copper Tiles at a crafting table.", "Stellt 1x Gewachste verwitterte Kupferfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/weathered_copper_brick_slab", "copper_inferno:weathered_copper_brick_slab", "masonry/weathered_copper_brick_slab",
				new String[] {"copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks", "", "", "", "", "", ""},
				"copper_inferno:weathered_copper_brick_slab", 6, "Craft 6x Weathered Copper Brick Slab at a crafting table.", "Stellt 6x Verwitterte Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/weathered_copper_brick_stairs", "copper_inferno:weathered_copper_brick_stairs", "masonry/weathered_copper_brick_stairs",
				new String[] {"copper_inferno:weathered_copper_bricks", "", "", "copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks", "", "copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks"},
				"copper_inferno:weathered_copper_brick_stairs", 4, "Craft 4x Weathered Copper Brick Stairs at a crafting table.", "Stellt 4x Verwitterte Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/weathered_copper_brick_wall", "copper_inferno:weathered_copper_brick_wall", "masonry/weathered_copper_brick_wall",
				new String[] {"copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks", "copper_inferno:weathered_copper_bricks", "", "", ""},
				"copper_inferno:weathered_copper_brick_wall", 6, "Craft 6x Weathered Copper Brick Wall at a crafting table.", "Stellt 6x Verwitterte Kupferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/weathered_copper_tile_slab", "copper_inferno:weathered_copper_tile_slab", "masonry/weathered_copper_tile_slab",
				new String[] {"copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles", "", "", "", "", "", ""},
				"copper_inferno:weathered_copper_tile_slab", 6, "Craft 6x Weathered Copper Tile Slab at a crafting table.", "Stellt 6x Verwitterte Kupferfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/weathered_copper_tile_stairs", "copper_inferno:weathered_copper_tile_stairs", "masonry/weathered_copper_tile_stairs",
				new String[] {"copper_inferno:weathered_copper_tiles", "", "", "copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles", "", "copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles"},
				"copper_inferno:weathered_copper_tile_stairs", 4, "Craft 4x Weathered Copper Tile Stairs at a crafting table.", "Stellt 4x Verwitterte Kupferfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "masonry/weathered_copper_tile_wall", "copper_inferno:weathered_copper_tile_wall", "masonry/weathered_copper_tile_wall",
				new String[] {"copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles", "copper_inferno:weathered_copper_tiles", "", "", ""},
				"copper_inferno:weathered_copper_tile_wall", 6, "Craft 6x Weathered Copper Tile Wall at a crafting table.", "Stellt 6x Verwitterte Kupferfliesenmauer an der Werkbank her."));
	}
}
