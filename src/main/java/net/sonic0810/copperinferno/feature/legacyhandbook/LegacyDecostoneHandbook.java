package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "decostone" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/decostone/} (20 entries, category
 * "blocks"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyDecostoneHandbook {
	private LegacyDecostoneHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "decostone/carved_copper", "copper_inferno:carved_copper", "decostone/carved_copper",
				new String[] {"copper_inferno:chiseled_copper_bricks", "copper_inferno:chiseled_copper_bricks", "", "copper_inferno:chiseled_copper_bricks", "copper_inferno:chiseled_copper_bricks", "", "", "", ""},
				"copper_inferno:carved_copper", 4, "Craft 4x Carved Copper at a crafting table.", "Stellt 4x Geschnitztes Kupfer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/chiseled_copper_bricks", "copper_inferno:chiseled_copper_bricks", "decostone/chiseled_copper_bricks",
				new String[] {"copper_inferno:cut_copper_brick_slab", "", "", "copper_inferno:cut_copper_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_copper_bricks", 1, "Craft 1x Chiseled Copper Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Kupferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/copper_mosaic", "copper_inferno:copper_mosaic", "decostone/copper_mosaic",
				new String[] {"copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "", "copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "", "", "", ""},
				"copper_inferno:copper_mosaic", 4, "Craft 4x Copper Mosaic at a crafting table.", "Stellt 4x Kupfermosaik an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/copper_mosaic_slab", "copper_inferno:copper_mosaic_slab", "decostone/copper_mosaic_slab",
				new String[] {"copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic", "", "", "", "", "", ""},
				"copper_inferno:copper_mosaic_slab", 6, "Craft 6x Copper Mosaic Slab at a crafting table.", "Stellt 6x Kupfermosaikstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/copper_mosaic_stairs", "copper_inferno:copper_mosaic_stairs", "decostone/copper_mosaic_stairs",
				new String[] {"copper_inferno:copper_mosaic", "", "", "copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic", "", "copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic"},
				"copper_inferno:copper_mosaic_stairs", 4, "Craft 4x Copper Mosaic Stairs at a crafting table.", "Stellt 4x Kupfermosaiktreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/copper_mosaic_wall", "copper_inferno:copper_mosaic_wall", "decostone/copper_mosaic_wall",
				new String[] {"copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic", "copper_inferno:copper_mosaic", "", "", ""},
				"copper_inferno:copper_mosaic_wall", 6, "Craft 6x Copper Mosaic Wall at a crafting table.", "Stellt 6x Kupfermosaikmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/copper_pillar", "copper_inferno:copper_pillar", "decostone/copper_pillar",
				new String[] {"minecraft:cut_copper", "", "", "minecraft:cut_copper", "", "", "minecraft:cut_copper", "", ""},
				"copper_inferno:copper_pillar", 3, "Craft 3x Copper Pillar at a crafting table.", "Stellt 3x Kupfers\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/copper_shingle_slab", "copper_inferno:copper_shingle_slab", "decostone/copper_shingle_slab",
				new String[] {"copper_inferno:copper_shingles", "copper_inferno:copper_shingles", "copper_inferno:copper_shingles", "", "", "", "", "", ""},
				"copper_inferno:copper_shingle_slab", 6, "Craft 6x Copper Shingle Slab at a crafting table.", "Stellt 6x Kupferschindelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/copper_shingle_stairs", "copper_inferno:copper_shingle_stairs", "decostone/copper_shingle_stairs",
				new String[] {"copper_inferno:copper_shingles", "", "", "copper_inferno:copper_shingles", "copper_inferno:copper_shingles", "", "copper_inferno:copper_shingles", "copper_inferno:copper_shingles", "copper_inferno:copper_shingles"},
				"copper_inferno:copper_shingle_stairs", 4, "Craft 4x Copper Shingle Stairs at a crafting table.", "Stellt 4x Kupferschindeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/copper_shingle_wall", "copper_inferno:copper_shingle_wall", "decostone/copper_shingle_wall",
				new String[] {"copper_inferno:copper_shingles", "copper_inferno:copper_shingles", "copper_inferno:copper_shingles", "copper_inferno:copper_shingles", "copper_inferno:copper_shingles", "copper_inferno:copper_shingles", "", "", ""},
				"copper_inferno:copper_shingle_wall", 6, "Craft 6x Copper Shingle Wall at a crafting table.", "Stellt 6x Kupferschindelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/copper_shingles", "copper_inferno:copper_shingles", "decostone/copper_shingles",
				new String[] {"copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "copper_inferno:copper_sheet", "", "", ""},
				"copper_inferno:copper_shingles", 6, "Craft 6x Copper Shingles at a crafting table.", "Stellt 6x Kupferschindeln an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/cut_copper_brick_slab", "copper_inferno:cut_copper_brick_slab", "decostone/cut_copper_brick_slab",
				new String[] {"copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "", "", "", "", "", ""},
				"copper_inferno:cut_copper_brick_slab", 6, "Craft 6x Cut Copper Brick Slab at a crafting table.", "Stellt 6x Geschnittene Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/cut_copper_brick_stairs", "copper_inferno:cut_copper_brick_stairs", "decostone/cut_copper_brick_stairs",
				new String[] {"copper_inferno:cut_copper_bricks", "", "", "copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "", "copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks"},
				"copper_inferno:cut_copper_brick_stairs", 4, "Craft 4x Cut Copper Brick Stairs at a crafting table.", "Stellt 4x Geschnittene Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/cut_copper_brick_wall", "copper_inferno:cut_copper_brick_wall", "decostone/cut_copper_brick_wall",
				new String[] {"copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "copper_inferno:cut_copper_bricks", "", "", ""},
				"copper_inferno:cut_copper_brick_wall", 6, "Craft 6x Cut Copper Brick Wall at a crafting table.", "Stellt 6x Geschnittene Kupferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/cut_copper_bricks", "copper_inferno:cut_copper_bricks", "decostone/cut_copper_bricks",
				new String[] {"minecraft:cut_copper", "minecraft:cut_copper", "", "minecraft:cut_copper", "minecraft:cut_copper", "", "", "", ""},
				"copper_inferno:cut_copper_bricks", 4, "Craft 4x Cut Copper Bricks at a crafting table.", "Stellt 4x Geschnittene Kupferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/mossy_copper_brick_slab", "copper_inferno:mossy_copper_brick_slab", "decostone/mossy_copper_brick_slab",
				new String[] {"copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks", "", "", "", "", "", ""},
				"copper_inferno:mossy_copper_brick_slab", 6, "Craft 6x Mossy Copper Brick Slab at a crafting table.", "Stellt 6x Bemooste Kupferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/mossy_copper_brick_stairs", "copper_inferno:mossy_copper_brick_stairs", "decostone/mossy_copper_brick_stairs",
				new String[] {"copper_inferno:mossy_copper_bricks", "", "", "copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks", "", "copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks"},
				"copper_inferno:mossy_copper_brick_stairs", 4, "Craft 4x Mossy Copper Brick Stairs at a crafting table.", "Stellt 4x Bemooste Kupferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/mossy_copper_brick_wall", "copper_inferno:mossy_copper_brick_wall", "decostone/mossy_copper_brick_wall",
				new String[] {"copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks", "copper_inferno:mossy_copper_bricks", "", "", ""},
				"copper_inferno:mossy_copper_brick_wall", 6, "Craft 6x Mossy Copper Brick Wall at a crafting table.", "Stellt 6x Bemooste Kupferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/mossy_copper_bricks_from_moss_block", "copper_inferno:mossy_copper_bricks", "decostone/mossy_copper_bricks_from_moss_block",
				new String[] {"copper_inferno:cut_copper_bricks", "minecraft:moss_block", "", "", "", "", "", "", ""},
				"copper_inferno:mossy_copper_bricks", 1, "Craft 1x Mossy Copper Bricks at a crafting table.", "Stellt 1x Bemooste Kupferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "decostone/mossy_copper_bricks_from_vine", "copper_inferno:mossy_copper_bricks", "decostone/mossy_copper_bricks_from_vine",
				new String[] {"copper_inferno:cut_copper_bricks", "minecraft:vine", "", "", "", "", "", "", ""},
				"copper_inferno:mossy_copper_bricks", 1, "Craft 1x Mossy Copper Bricks at a crafting table.", "Stellt 1x Bemooste Kupferziegel an der Werkbank her."));
	}
}
