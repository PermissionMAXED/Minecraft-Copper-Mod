package net.sonic0810.copperinferno.feature.charwood;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Charwood block set: one "blocks" overview per cube family
 * plus one recipe page for every JSON under {@code data/copper_inferno/recipe/charwood/}
 * (crafting, smelting and stonecutting). Entry texts and grids mirror the recipe JSONs
 * emitted by {@code devtools/gen/charwood_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep them
 * inline.
 */
final class CharwoodHandbook {
	private CharwoodHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_charwood", "copper_inferno:charwood", null,
				null,
				null, 0, "The Charwood family for Inferno builds: block, stairs, slab and wall.", "Die Kohleholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_charwood", "copper_inferno:polished_charwood", null,
				null,
				null, 0, "The Polished Charwood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Kohleholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_charwood_bricks", "copper_inferno:charwood_bricks", null,
				null,
				null, 0, "The Charwood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Kohleholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_emberbark", "copper_inferno:emberbark", null,
				null,
				null, 0, "The Emberbark family for Inferno builds: block, stairs, slab and wall.", "Die Glutrinde-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_emberbark", "copper_inferno:polished_emberbark", null,
				null,
				null, 0, "The Polished Emberbark family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Glutrinde f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_emberbark_bricks", "copper_inferno:emberbark_bricks", null,
				null,
				null, 0, "The Emberbark Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Glutrindenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_sootbark", "copper_inferno:sootbark", null,
				null,
				null, 0, "The Sootbark family for Inferno builds: block, stairs, slab and wall.", "Die Ru\u00dfrinde-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_sootbark", "copper_inferno:polished_sootbark", null,
				null,
				null, 0, "The Polished Sootbark family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Ru\u00dfrinde f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_sootbark_bricks", "copper_inferno:sootbark_bricks", null,
				null,
				null, 0, "The Sootbark Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Ru\u00dfrindenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_cinderlog_block", "copper_inferno:cinderlog_block", null,
				null,
				null, 0, "The Cinderlog family for Inferno builds: block, stairs, slab and wall.", "Die Zunderscheit-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", null,
				null,
				null, 0, "The Polished Cinderlog family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Zunderscheit f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", null,
				null,
				null, 0, "The Cinderlog Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Zunderscheitziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_blazewood", "copper_inferno:blazewood", null,
				null,
				null, 0, "The Blazewood family for Inferno builds: block, stairs, slab and wall.", "Die Lohenholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_blazewood", "copper_inferno:polished_blazewood", null,
				null,
				null, 0, "The Polished Blazewood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Lohenholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_blazewood_bricks", "copper_inferno:blazewood_bricks", null,
				null,
				null, 0, "The Blazewood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Lohenholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_magmawood", "copper_inferno:magmawood", null,
				null,
				null, 0, "The Magmawood family for Inferno builds: block, stairs, slab and wall.", "Die Magmaholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_magmawood", "copper_inferno:polished_magmawood", null,
				null,
				null, 0, "The Polished Magmawood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Magmaholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_magmawood_bricks", "copper_inferno:magmawood_bricks", null,
				null,
				null, 0, "The Magmawood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Magmaholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_kindlewood", "copper_inferno:kindlewood", null,
				null,
				null, 0, "The Kindlewood family for Inferno builds: block, stairs, slab and wall.", "Die Z\u00fcndelholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_kindlewood", "copper_inferno:polished_kindlewood", null,
				null,
				null, 0, "The Polished Kindlewood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Z\u00fcndelholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_kindlewood_bricks", "copper_inferno:kindlewood_bricks", null,
				null,
				null, 0, "The Kindlewood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Z\u00fcndelholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_pyrewood", "copper_inferno:pyrewood", null,
				null,
				null, 0, "The Pyrewood family for Inferno builds: block, stairs, slab and wall.", "Die Scheiterholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_pyrewood", "copper_inferno:polished_pyrewood", null,
				null,
				null, 0, "The Polished Pyrewood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Scheiterholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_pyrewood_bricks", "copper_inferno:pyrewood_bricks", null,
				null,
				null, 0, "The Pyrewood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Scheiterholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_smolderwood", "copper_inferno:smolderwood", null,
				null,
				null, 0, "The Smolderwood family for Inferno builds: block, stairs, slab and wall.", "Die Schwelholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_smolderwood", "copper_inferno:polished_smolderwood", null,
				null,
				null, 0, "The Polished Smolderwood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Schwelholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_smolderwood_bricks", "copper_inferno:smolderwood_bricks", null,
				null,
				null, 0, "The Smolderwood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Schwelholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_glowbark", "copper_inferno:glowbark", null,
				null,
				null, 0, "The Glowbark family for Inferno builds: block, stairs, slab and wall.", "Die Leuchtrinde-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_glowbark", "copper_inferno:polished_glowbark", null,
				null,
				null, 0, "The Polished Glowbark family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Leuchtrinde f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_glowbark_bricks", "copper_inferno:glowbark_bricks", null,
				null,
				null, 0, "The Glowbark Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Leuchtrindenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_coalwood", "copper_inferno:coalwood", null,
				null,
				null, 0, "The Coalwood family for Inferno builds: block, stairs, slab and wall.", "Die Anthrazitholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_coalwood", "copper_inferno:polished_coalwood", null,
				null,
				null, 0, "The Polished Coalwood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Anthrazitholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_coalwood_bricks", "copper_inferno:coalwood_bricks", null,
				null,
				null, 0, "The Coalwood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Anthrazitholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_tarwood", "copper_inferno:tarwood", null,
				null,
				null, 0, "The Tarwood family for Inferno builds: block, stairs, slab and wall.", "Die Teerholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_tarwood", "copper_inferno:polished_tarwood", null,
				null,
				null, 0, "The Polished Tarwood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Teerholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_tarwood_bricks", "copper_inferno:tarwood_bricks", null,
				null,
				null, 0, "The Tarwood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Teerholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_brimwood", "copper_inferno:brimwood", null,
				null,
				null, 0, "The Brimwood family for Inferno builds: block, stairs, slab and wall.", "Die Schwefelholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_brimwood", "copper_inferno:polished_brimwood", null,
				null,
				null, 0, "The Polished Brimwood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Schwefelholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_brimwood_bricks", "copper_inferno:brimwood_bricks", null,
				null,
				null, 0, "The Brimwood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Schwefelholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_flarewood", "copper_inferno:flarewood", null,
				null,
				null, 0, "The Flarewood family for Inferno builds: block, stairs, slab and wall.", "Die Flackerholz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_polished_flarewood", "copper_inferno:polished_flarewood", null,
				null,
				null, 0, "The Polished Flarewood family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Flackerholz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/family_flarewood_bricks", "copper_inferno:flarewood_bricks", null,
				null,
				null, 0, "The Flarewood Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Flackerholzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood", "copper_inferno:charwood", "charwood/charwood",
				new String[] {"minecraft:charcoal", "", "minecraft:charcoal", "", "copper_inferno:scorched_planks", "", "minecraft:charcoal", "", "minecraft:charcoal"},
				"copper_inferno:charwood", 4, "Craft 4x Charwood at a crafting table.", "Stellt 4x Kohleholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_bricks", "copper_inferno:charwood_bricks", "charwood/charwood_bricks",
				new String[] {"copper_inferno:charwood", "copper_inferno:charwood", "", "copper_inferno:charwood", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_bricks", 4, "Craft 4x Charwood Bricks at a crafting table.", "Stellt 4x Kohleholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_tiles", "copper_inferno:charwood_tiles", "charwood/charwood_tiles",
				new String[] {"copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "", "copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "", "", "", ""},
				"copper_inferno:charwood_tiles", 4, "Craft 4x Charwood Tiles at a crafting table.", "Stellt 4x Kohleholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_charwood", "copper_inferno:polished_charwood", "charwood/polished_charwood",
				new String[] {"copper_inferno:charwood_tiles", "copper_inferno:charwood_tiles", "", "copper_inferno:charwood_tiles", "copper_inferno:charwood_tiles", "", "", "", ""},
				"copper_inferno:polished_charwood", 4, "Craft 4x Polished Charwood at a crafting table.", "Stellt 4x Poliertes Kohleholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_slab", "copper_inferno:charwood_slab", "charwood/charwood_slab",
				new String[] {"copper_inferno:charwood", "copper_inferno:charwood", "copper_inferno:charwood", "", "", "", "", "", ""},
				"copper_inferno:charwood_slab", 6, "Craft 6x Charwood Slab at a crafting table.", "Stellt 6x Kohleholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_stairs", "copper_inferno:charwood_stairs", "charwood/charwood_stairs",
				new String[] {"copper_inferno:charwood", "", "", "copper_inferno:charwood", "copper_inferno:charwood", "", "copper_inferno:charwood", "copper_inferno:charwood", "copper_inferno:charwood"},
				"copper_inferno:charwood_stairs", 4, "Craft 4x Charwood Stairs at a crafting table.", "Stellt 4x Kohleholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_wall", "copper_inferno:charwood_wall", "charwood/charwood_wall",
				new String[] {"copper_inferno:charwood", "copper_inferno:charwood", "copper_inferno:charwood", "copper_inferno:charwood", "copper_inferno:charwood", "copper_inferno:charwood", "", "", ""},
				"copper_inferno:charwood_wall", 6, "Craft 6x Charwood Wall at a crafting table.", "Stellt 6x Kohleholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_charwood_slab", "copper_inferno:polished_charwood_slab", "charwood/polished_charwood_slab",
				new String[] {"copper_inferno:polished_charwood", "copper_inferno:polished_charwood", "copper_inferno:polished_charwood", "", "", "", "", "", ""},
				"copper_inferno:polished_charwood_slab", 6, "Craft 6x Polished Charwood Slab at a crafting table.", "Stellt 6x Polierte Kohleholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_charwood_stairs", "copper_inferno:polished_charwood_stairs", "charwood/polished_charwood_stairs",
				new String[] {"copper_inferno:polished_charwood", "", "", "copper_inferno:polished_charwood", "copper_inferno:polished_charwood", "", "copper_inferno:polished_charwood", "copper_inferno:polished_charwood", "copper_inferno:polished_charwood"},
				"copper_inferno:polished_charwood_stairs", 4, "Craft 4x Polished Charwood Stairs at a crafting table.", "Stellt 4x Polierte Kohleholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_charwood_wall", "copper_inferno:polished_charwood_wall", "charwood/polished_charwood_wall",
				new String[] {"copper_inferno:polished_charwood", "copper_inferno:polished_charwood", "copper_inferno:polished_charwood", "copper_inferno:polished_charwood", "copper_inferno:polished_charwood", "copper_inferno:polished_charwood", "", "", ""},
				"copper_inferno:polished_charwood_wall", 6, "Craft 6x Polished Charwood Wall at a crafting table.", "Stellt 6x Polierte Kohleholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_brick_slab", "copper_inferno:charwood_brick_slab", "charwood/charwood_brick_slab",
				new String[] {"copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "", "", "", "", "", ""},
				"copper_inferno:charwood_brick_slab", 6, "Craft 6x Charwood Brick Slab at a crafting table.", "Stellt 6x Kohleholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_brick_stairs", "copper_inferno:charwood_brick_stairs", "charwood/charwood_brick_stairs",
				new String[] {"copper_inferno:charwood_bricks", "", "", "copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "", "copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks"},
				"copper_inferno:charwood_brick_stairs", 4, "Craft 4x Charwood Brick Stairs at a crafting table.", "Stellt 4x Kohleholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_brick_wall", "copper_inferno:charwood_brick_wall", "charwood/charwood_brick_wall",
				new String[] {"copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "copper_inferno:charwood_bricks", "", "", ""},
				"copper_inferno:charwood_brick_wall", 6, "Craft 6x Charwood Brick Wall at a crafting table.", "Stellt 6x Kohleholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_charwood_bricks", "copper_inferno:cracked_charwood_bricks", "charwood/cracked_charwood_bricks",
				new String[] {"", "", "", "", "copper_inferno:charwood_bricks", "", "", "", ""},
				"copper_inferno:cracked_charwood_bricks", 1, "Smelting Charwood Bricks in a furnace yields Cracked Charwood Bricks.", "Kohleholzziegel im Ofen gebrannt ergibt Rissige Kohleholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_charwood_bricks", "copper_inferno:chiseled_charwood_bricks", "charwood/chiseled_charwood_bricks",
				new String[] {"copper_inferno:charwood_brick_slab", "", "", "copper_inferno:charwood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_charwood_bricks", 1, "Craft 1x Chiseled Charwood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Kohleholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_pillar", "copper_inferno:charwood_pillar", "charwood/charwood_pillar",
				new String[] {"copper_inferno:charwood_bricks", "", "", "copper_inferno:charwood_bricks", "", "", "", "", ""},
				"copper_inferno:charwood_pillar", 2, "Craft 2x Charwood Pillar at a crafting table.", "Stellt 2x Kohleholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_slab_from_charwood_stonecutting", "copper_inferno:charwood_slab", "charwood/charwood_slab_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_slab", 2, "Stonecutting: cut 2x Charwood Slab from Charwood.", "Steins\u00e4ge: 2x Kohleholzstufe aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_stairs_from_charwood_stonecutting", "copper_inferno:charwood_stairs", "charwood/charwood_stairs_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_stairs", 1, "Stonecutting: cut 1x Charwood Stairs from Charwood.", "Steins\u00e4ge: 1x Kohleholztreppe aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_wall_from_charwood_stonecutting", "copper_inferno:charwood_wall", "charwood/charwood_wall_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_wall", 1, "Stonecutting: cut 1x Charwood Wall from Charwood.", "Steins\u00e4ge: 1x Kohleholzmauer aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_charwood_from_charwood_stonecutting", "copper_inferno:polished_charwood", "charwood/polished_charwood_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:polished_charwood", 1, "Stonecutting: cut 1x Polished Charwood from Charwood.", "Steins\u00e4ge: 1x Poliertes Kohleholz aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_charwood_slab_from_charwood_stonecutting", "copper_inferno:polished_charwood_slab", "charwood/polished_charwood_slab_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:polished_charwood_slab", 2, "Stonecutting: cut 2x Polished Charwood Slab from Charwood.", "Steins\u00e4ge: 2x Polierte Kohleholzstufe aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_charwood_stairs_from_charwood_stonecutting", "copper_inferno:polished_charwood_stairs", "charwood/polished_charwood_stairs_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:polished_charwood_stairs", 1, "Stonecutting: cut 1x Polished Charwood Stairs from Charwood.", "Steins\u00e4ge: 1x Polierte Kohleholztreppe aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_charwood_wall_from_charwood_stonecutting", "copper_inferno:polished_charwood_wall", "charwood/polished_charwood_wall_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:polished_charwood_wall", 1, "Stonecutting: cut 1x Polished Charwood Wall from Charwood.", "Steins\u00e4ge: 1x Polierte Kohleholzmauer aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_bricks_from_charwood_stonecutting", "copper_inferno:charwood_bricks", "charwood/charwood_bricks_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_bricks", 1, "Stonecutting: cut 1x Charwood Bricks from Charwood.", "Steins\u00e4ge: 1x Kohleholzziegel aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_brick_slab_from_charwood_stonecutting", "copper_inferno:charwood_brick_slab", "charwood/charwood_brick_slab_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_brick_slab", 2, "Stonecutting: cut 2x Charwood Brick Slab from Charwood.", "Steins\u00e4ge: 2x Kohleholzziegelstufe aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_brick_stairs_from_charwood_stonecutting", "copper_inferno:charwood_brick_stairs", "charwood/charwood_brick_stairs_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_brick_stairs", 1, "Stonecutting: cut 1x Charwood Brick Stairs from Charwood.", "Steins\u00e4ge: 1x Kohleholzziegeltreppe aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_brick_wall_from_charwood_stonecutting", "copper_inferno:charwood_brick_wall", "charwood/charwood_brick_wall_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_brick_wall", 1, "Stonecutting: cut 1x Charwood Brick Wall from Charwood.", "Steins\u00e4ge: 1x Kohleholzziegelmauer aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_tiles_from_charwood_stonecutting", "copper_inferno:charwood_tiles", "charwood/charwood_tiles_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_tiles", 1, "Stonecutting: cut 1x Charwood Tiles from Charwood.", "Steins\u00e4ge: 1x Kohleholzfliesen aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_charwood_bricks_from_charwood_stonecutting", "copper_inferno:chiseled_charwood_bricks", "charwood/chiseled_charwood_bricks_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:chiseled_charwood_bricks", 1, "Stonecutting: cut 1x Chiseled Charwood Bricks from Charwood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Kohleholzziegel aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/charwood_pillar_from_charwood_stonecutting", "copper_inferno:charwood_pillar", "charwood/charwood_pillar_from_charwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charwood", "", "", "", ""},
				"copper_inferno:charwood_pillar", 1, "Stonecutting: cut 1x Charwood Pillar from Charwood.", "Steins\u00e4ge: 1x Kohleholzs\u00e4ule aus Kohleholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark", "copper_inferno:emberbark", "charwood/emberbark",
				new String[] {"minecraft:blaze_powder", "", "minecraft:blaze_powder", "", "copper_inferno:charwood", "", "minecraft:blaze_powder", "", "minecraft:blaze_powder"},
				"copper_inferno:emberbark", 4, "Craft 4x Emberbark at a crafting table.", "Stellt 4x Glutrinde an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_bricks", "copper_inferno:emberbark_bricks", "charwood/emberbark_bricks",
				new String[] {"copper_inferno:emberbark", "copper_inferno:emberbark", "", "copper_inferno:emberbark", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_bricks", 4, "Craft 4x Emberbark Bricks at a crafting table.", "Stellt 4x Glutrindenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_tiles", "copper_inferno:emberbark_tiles", "charwood/emberbark_tiles",
				new String[] {"copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "", "copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "", "", "", ""},
				"copper_inferno:emberbark_tiles", 4, "Craft 4x Emberbark Tiles at a crafting table.", "Stellt 4x Glutrindenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_emberbark", "copper_inferno:polished_emberbark", "charwood/polished_emberbark",
				new String[] {"copper_inferno:emberbark_tiles", "copper_inferno:emberbark_tiles", "", "copper_inferno:emberbark_tiles", "copper_inferno:emberbark_tiles", "", "", "", ""},
				"copper_inferno:polished_emberbark", 4, "Craft 4x Polished Emberbark at a crafting table.", "Stellt 4x Polierte Glutrinde an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_slab", "copper_inferno:emberbark_slab", "charwood/emberbark_slab",
				new String[] {"copper_inferno:emberbark", "copper_inferno:emberbark", "copper_inferno:emberbark", "", "", "", "", "", ""},
				"copper_inferno:emberbark_slab", 6, "Craft 6x Emberbark Slab at a crafting table.", "Stellt 6x Glutrindenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_stairs", "copper_inferno:emberbark_stairs", "charwood/emberbark_stairs",
				new String[] {"copper_inferno:emberbark", "", "", "copper_inferno:emberbark", "copper_inferno:emberbark", "", "copper_inferno:emberbark", "copper_inferno:emberbark", "copper_inferno:emberbark"},
				"copper_inferno:emberbark_stairs", 4, "Craft 4x Emberbark Stairs at a crafting table.", "Stellt 4x Glutrindentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_wall", "copper_inferno:emberbark_wall", "charwood/emberbark_wall",
				new String[] {"copper_inferno:emberbark", "copper_inferno:emberbark", "copper_inferno:emberbark", "copper_inferno:emberbark", "copper_inferno:emberbark", "copper_inferno:emberbark", "", "", ""},
				"copper_inferno:emberbark_wall", 6, "Craft 6x Emberbark Wall at a crafting table.", "Stellt 6x Glutrindenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_emberbark_slab", "copper_inferno:polished_emberbark_slab", "charwood/polished_emberbark_slab",
				new String[] {"copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark", "", "", "", "", "", ""},
				"copper_inferno:polished_emberbark_slab", 6, "Craft 6x Polished Emberbark Slab at a crafting table.", "Stellt 6x Polierte Glutrindenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_emberbark_stairs", "copper_inferno:polished_emberbark_stairs", "charwood/polished_emberbark_stairs",
				new String[] {"copper_inferno:polished_emberbark", "", "", "copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark", "", "copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark"},
				"copper_inferno:polished_emberbark_stairs", 4, "Craft 4x Polished Emberbark Stairs at a crafting table.", "Stellt 4x Polierte Glutrindentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_emberbark_wall", "copper_inferno:polished_emberbark_wall", "charwood/polished_emberbark_wall",
				new String[] {"copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark", "copper_inferno:polished_emberbark", "", "", ""},
				"copper_inferno:polished_emberbark_wall", 6, "Craft 6x Polished Emberbark Wall at a crafting table.", "Stellt 6x Polierte Glutrindenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_brick_slab", "copper_inferno:emberbark_brick_slab", "charwood/emberbark_brick_slab",
				new String[] {"copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "", "", "", "", "", ""},
				"copper_inferno:emberbark_brick_slab", 6, "Craft 6x Emberbark Brick Slab at a crafting table.", "Stellt 6x Glutrindenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_brick_stairs", "copper_inferno:emberbark_brick_stairs", "charwood/emberbark_brick_stairs",
				new String[] {"copper_inferno:emberbark_bricks", "", "", "copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "", "copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks"},
				"copper_inferno:emberbark_brick_stairs", 4, "Craft 4x Emberbark Brick Stairs at a crafting table.", "Stellt 4x Glutrindenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_brick_wall", "copper_inferno:emberbark_brick_wall", "charwood/emberbark_brick_wall",
				new String[] {"copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "copper_inferno:emberbark_bricks", "", "", ""},
				"copper_inferno:emberbark_brick_wall", 6, "Craft 6x Emberbark Brick Wall at a crafting table.", "Stellt 6x Glutrindenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_emberbark_bricks", "copper_inferno:cracked_emberbark_bricks", "charwood/cracked_emberbark_bricks",
				new String[] {"", "", "", "", "copper_inferno:emberbark_bricks", "", "", "", ""},
				"copper_inferno:cracked_emberbark_bricks", 1, "Smelting Emberbark Bricks in a furnace yields Cracked Emberbark Bricks.", "Glutrindenziegel im Ofen gebrannt ergibt Rissige Glutrindenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_emberbark_bricks", "copper_inferno:chiseled_emberbark_bricks", "charwood/chiseled_emberbark_bricks",
				new String[] {"copper_inferno:emberbark_brick_slab", "", "", "copper_inferno:emberbark_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_emberbark_bricks", 1, "Craft 1x Chiseled Emberbark Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glutrindenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_pillar", "copper_inferno:emberbark_pillar", "charwood/emberbark_pillar",
				new String[] {"copper_inferno:emberbark_bricks", "", "", "copper_inferno:emberbark_bricks", "", "", "", "", ""},
				"copper_inferno:emberbark_pillar", 2, "Craft 2x Emberbark Pillar at a crafting table.", "Stellt 2x Glutrindens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_slab_from_emberbark_stonecutting", "copper_inferno:emberbark_slab", "charwood/emberbark_slab_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_slab", 2, "Stonecutting: cut 2x Emberbark Slab from Emberbark.", "Steins\u00e4ge: 2x Glutrindenstufe aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_stairs_from_emberbark_stonecutting", "copper_inferno:emberbark_stairs", "charwood/emberbark_stairs_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_stairs", 1, "Stonecutting: cut 1x Emberbark Stairs from Emberbark.", "Steins\u00e4ge: 1x Glutrindentreppe aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_wall_from_emberbark_stonecutting", "copper_inferno:emberbark_wall", "charwood/emberbark_wall_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_wall", 1, "Stonecutting: cut 1x Emberbark Wall from Emberbark.", "Steins\u00e4ge: 1x Glutrindenmauer aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_emberbark_from_emberbark_stonecutting", "copper_inferno:polished_emberbark", "charwood/polished_emberbark_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:polished_emberbark", 1, "Stonecutting: cut 1x Polished Emberbark from Emberbark.", "Steins\u00e4ge: 1x Polierte Glutrinde aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_emberbark_slab_from_emberbark_stonecutting", "copper_inferno:polished_emberbark_slab", "charwood/polished_emberbark_slab_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:polished_emberbark_slab", 2, "Stonecutting: cut 2x Polished Emberbark Slab from Emberbark.", "Steins\u00e4ge: 2x Polierte Glutrindenstufe aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_emberbark_stairs_from_emberbark_stonecutting", "copper_inferno:polished_emberbark_stairs", "charwood/polished_emberbark_stairs_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:polished_emberbark_stairs", 1, "Stonecutting: cut 1x Polished Emberbark Stairs from Emberbark.", "Steins\u00e4ge: 1x Polierte Glutrindentreppe aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_emberbark_wall_from_emberbark_stonecutting", "copper_inferno:polished_emberbark_wall", "charwood/polished_emberbark_wall_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:polished_emberbark_wall", 1, "Stonecutting: cut 1x Polished Emberbark Wall from Emberbark.", "Steins\u00e4ge: 1x Polierte Glutrindenmauer aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_bricks_from_emberbark_stonecutting", "copper_inferno:emberbark_bricks", "charwood/emberbark_bricks_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_bricks", 1, "Stonecutting: cut 1x Emberbark Bricks from Emberbark.", "Steins\u00e4ge: 1x Glutrindenziegel aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_brick_slab_from_emberbark_stonecutting", "copper_inferno:emberbark_brick_slab", "charwood/emberbark_brick_slab_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_brick_slab", 2, "Stonecutting: cut 2x Emberbark Brick Slab from Emberbark.", "Steins\u00e4ge: 2x Glutrindenziegelstufe aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_brick_stairs_from_emberbark_stonecutting", "copper_inferno:emberbark_brick_stairs", "charwood/emberbark_brick_stairs_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_brick_stairs", 1, "Stonecutting: cut 1x Emberbark Brick Stairs from Emberbark.", "Steins\u00e4ge: 1x Glutrindenziegeltreppe aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_brick_wall_from_emberbark_stonecutting", "copper_inferno:emberbark_brick_wall", "charwood/emberbark_brick_wall_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_brick_wall", 1, "Stonecutting: cut 1x Emberbark Brick Wall from Emberbark.", "Steins\u00e4ge: 1x Glutrindenziegelmauer aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_tiles_from_emberbark_stonecutting", "copper_inferno:emberbark_tiles", "charwood/emberbark_tiles_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_tiles", 1, "Stonecutting: cut 1x Emberbark Tiles from Emberbark.", "Steins\u00e4ge: 1x Glutrindenfliesen aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_emberbark_bricks_from_emberbark_stonecutting", "copper_inferno:chiseled_emberbark_bricks", "charwood/chiseled_emberbark_bricks_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:chiseled_emberbark_bricks", 1, "Stonecutting: cut 1x Chiseled Emberbark Bricks from Emberbark.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glutrindenziegel aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/emberbark_pillar_from_emberbark_stonecutting", "copper_inferno:emberbark_pillar", "charwood/emberbark_pillar_from_emberbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberbark", "", "", "", ""},
				"copper_inferno:emberbark_pillar", 1, "Stonecutting: cut 1x Emberbark Pillar from Emberbark.", "Steins\u00e4ge: 1x Glutrindens\u00e4ule aus Glutrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark", "copper_inferno:sootbark", "charwood/sootbark",
				new String[] {"minecraft:coal", "", "minecraft:coal", "", "copper_inferno:emberbark", "", "minecraft:coal", "", "minecraft:coal"},
				"copper_inferno:sootbark", 4, "Craft 4x Sootbark at a crafting table.", "Stellt 4x Ru\u00dfrinde an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_bricks", "copper_inferno:sootbark_bricks", "charwood/sootbark_bricks",
				new String[] {"copper_inferno:sootbark", "copper_inferno:sootbark", "", "copper_inferno:sootbark", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_bricks", 4, "Craft 4x Sootbark Bricks at a crafting table.", "Stellt 4x Ru\u00dfrindenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_tiles", "copper_inferno:sootbark_tiles", "charwood/sootbark_tiles",
				new String[] {"copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "", "copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "", "", "", ""},
				"copper_inferno:sootbark_tiles", 4, "Craft 4x Sootbark Tiles at a crafting table.", "Stellt 4x Ru\u00dfrindenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_sootbark", "copper_inferno:polished_sootbark", "charwood/polished_sootbark",
				new String[] {"copper_inferno:sootbark_tiles", "copper_inferno:sootbark_tiles", "", "copper_inferno:sootbark_tiles", "copper_inferno:sootbark_tiles", "", "", "", ""},
				"copper_inferno:polished_sootbark", 4, "Craft 4x Polished Sootbark at a crafting table.", "Stellt 4x Polierte Ru\u00dfrinde an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_slab", "copper_inferno:sootbark_slab", "charwood/sootbark_slab",
				new String[] {"copper_inferno:sootbark", "copper_inferno:sootbark", "copper_inferno:sootbark", "", "", "", "", "", ""},
				"copper_inferno:sootbark_slab", 6, "Craft 6x Sootbark Slab at a crafting table.", "Stellt 6x Ru\u00dfrindenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_stairs", "copper_inferno:sootbark_stairs", "charwood/sootbark_stairs",
				new String[] {"copper_inferno:sootbark", "", "", "copper_inferno:sootbark", "copper_inferno:sootbark", "", "copper_inferno:sootbark", "copper_inferno:sootbark", "copper_inferno:sootbark"},
				"copper_inferno:sootbark_stairs", 4, "Craft 4x Sootbark Stairs at a crafting table.", "Stellt 4x Ru\u00dfrindentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_wall", "copper_inferno:sootbark_wall", "charwood/sootbark_wall",
				new String[] {"copper_inferno:sootbark", "copper_inferno:sootbark", "copper_inferno:sootbark", "copper_inferno:sootbark", "copper_inferno:sootbark", "copper_inferno:sootbark", "", "", ""},
				"copper_inferno:sootbark_wall", 6, "Craft 6x Sootbark Wall at a crafting table.", "Stellt 6x Ru\u00dfrindenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_sootbark_slab", "copper_inferno:polished_sootbark_slab", "charwood/polished_sootbark_slab",
				new String[] {"copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark", "", "", "", "", "", ""},
				"copper_inferno:polished_sootbark_slab", 6, "Craft 6x Polished Sootbark Slab at a crafting table.", "Stellt 6x Polierte Ru\u00dfrindenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_sootbark_stairs", "copper_inferno:polished_sootbark_stairs", "charwood/polished_sootbark_stairs",
				new String[] {"copper_inferno:polished_sootbark", "", "", "copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark", "", "copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark"},
				"copper_inferno:polished_sootbark_stairs", 4, "Craft 4x Polished Sootbark Stairs at a crafting table.", "Stellt 4x Polierte Ru\u00dfrindentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_sootbark_wall", "copper_inferno:polished_sootbark_wall", "charwood/polished_sootbark_wall",
				new String[] {"copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark", "copper_inferno:polished_sootbark", "", "", ""},
				"copper_inferno:polished_sootbark_wall", 6, "Craft 6x Polished Sootbark Wall at a crafting table.", "Stellt 6x Polierte Ru\u00dfrindenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_brick_slab", "copper_inferno:sootbark_brick_slab", "charwood/sootbark_brick_slab",
				new String[] {"copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "", "", "", "", "", ""},
				"copper_inferno:sootbark_brick_slab", 6, "Craft 6x Sootbark Brick Slab at a crafting table.", "Stellt 6x Ru\u00dfrindenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_brick_stairs", "copper_inferno:sootbark_brick_stairs", "charwood/sootbark_brick_stairs",
				new String[] {"copper_inferno:sootbark_bricks", "", "", "copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "", "copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks"},
				"copper_inferno:sootbark_brick_stairs", 4, "Craft 4x Sootbark Brick Stairs at a crafting table.", "Stellt 4x Ru\u00dfrindenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_brick_wall", "copper_inferno:sootbark_brick_wall", "charwood/sootbark_brick_wall",
				new String[] {"copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "copper_inferno:sootbark_bricks", "", "", ""},
				"copper_inferno:sootbark_brick_wall", 6, "Craft 6x Sootbark Brick Wall at a crafting table.", "Stellt 6x Ru\u00dfrindenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_sootbark_bricks", "copper_inferno:cracked_sootbark_bricks", "charwood/cracked_sootbark_bricks",
				new String[] {"", "", "", "", "copper_inferno:sootbark_bricks", "", "", "", ""},
				"copper_inferno:cracked_sootbark_bricks", 1, "Smelting Sootbark Bricks in a furnace yields Cracked Sootbark Bricks.", "Ru\u00dfrindenziegel im Ofen gebrannt ergibt Rissige Ru\u00dfrindenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_sootbark_bricks", "copper_inferno:chiseled_sootbark_bricks", "charwood/chiseled_sootbark_bricks",
				new String[] {"copper_inferno:sootbark_brick_slab", "", "", "copper_inferno:sootbark_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_sootbark_bricks", 1, "Craft 1x Chiseled Sootbark Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Ru\u00dfrindenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_pillar", "copper_inferno:sootbark_pillar", "charwood/sootbark_pillar",
				new String[] {"copper_inferno:sootbark_bricks", "", "", "copper_inferno:sootbark_bricks", "", "", "", "", ""},
				"copper_inferno:sootbark_pillar", 2, "Craft 2x Sootbark Pillar at a crafting table.", "Stellt 2x Ru\u00dfrindens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_slab_from_sootbark_stonecutting", "copper_inferno:sootbark_slab", "charwood/sootbark_slab_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_slab", 2, "Stonecutting: cut 2x Sootbark Slab from Sootbark.", "Steins\u00e4ge: 2x Ru\u00dfrindenstufe aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_stairs_from_sootbark_stonecutting", "copper_inferno:sootbark_stairs", "charwood/sootbark_stairs_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_stairs", 1, "Stonecutting: cut 1x Sootbark Stairs from Sootbark.", "Steins\u00e4ge: 1x Ru\u00dfrindentreppe aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_wall_from_sootbark_stonecutting", "copper_inferno:sootbark_wall", "charwood/sootbark_wall_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_wall", 1, "Stonecutting: cut 1x Sootbark Wall from Sootbark.", "Steins\u00e4ge: 1x Ru\u00dfrindenmauer aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_sootbark_from_sootbark_stonecutting", "copper_inferno:polished_sootbark", "charwood/polished_sootbark_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:polished_sootbark", 1, "Stonecutting: cut 1x Polished Sootbark from Sootbark.", "Steins\u00e4ge: 1x Polierte Ru\u00dfrinde aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_sootbark_slab_from_sootbark_stonecutting", "copper_inferno:polished_sootbark_slab", "charwood/polished_sootbark_slab_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:polished_sootbark_slab", 2, "Stonecutting: cut 2x Polished Sootbark Slab from Sootbark.", "Steins\u00e4ge: 2x Polierte Ru\u00dfrindenstufe aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_sootbark_stairs_from_sootbark_stonecutting", "copper_inferno:polished_sootbark_stairs", "charwood/polished_sootbark_stairs_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:polished_sootbark_stairs", 1, "Stonecutting: cut 1x Polished Sootbark Stairs from Sootbark.", "Steins\u00e4ge: 1x Polierte Ru\u00dfrindentreppe aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_sootbark_wall_from_sootbark_stonecutting", "copper_inferno:polished_sootbark_wall", "charwood/polished_sootbark_wall_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:polished_sootbark_wall", 1, "Stonecutting: cut 1x Polished Sootbark Wall from Sootbark.", "Steins\u00e4ge: 1x Polierte Ru\u00dfrindenmauer aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_bricks_from_sootbark_stonecutting", "copper_inferno:sootbark_bricks", "charwood/sootbark_bricks_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_bricks", 1, "Stonecutting: cut 1x Sootbark Bricks from Sootbark.", "Steins\u00e4ge: 1x Ru\u00dfrindenziegel aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_brick_slab_from_sootbark_stonecutting", "copper_inferno:sootbark_brick_slab", "charwood/sootbark_brick_slab_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_brick_slab", 2, "Stonecutting: cut 2x Sootbark Brick Slab from Sootbark.", "Steins\u00e4ge: 2x Ru\u00dfrindenziegelstufe aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_brick_stairs_from_sootbark_stonecutting", "copper_inferno:sootbark_brick_stairs", "charwood/sootbark_brick_stairs_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_brick_stairs", 1, "Stonecutting: cut 1x Sootbark Brick Stairs from Sootbark.", "Steins\u00e4ge: 1x Ru\u00dfrindenziegeltreppe aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_brick_wall_from_sootbark_stonecutting", "copper_inferno:sootbark_brick_wall", "charwood/sootbark_brick_wall_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_brick_wall", 1, "Stonecutting: cut 1x Sootbark Brick Wall from Sootbark.", "Steins\u00e4ge: 1x Ru\u00dfrindenziegelmauer aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_tiles_from_sootbark_stonecutting", "copper_inferno:sootbark_tiles", "charwood/sootbark_tiles_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_tiles", 1, "Stonecutting: cut 1x Sootbark Tiles from Sootbark.", "Steins\u00e4ge: 1x Ru\u00dfrindenfliesen aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_sootbark_bricks_from_sootbark_stonecutting", "copper_inferno:chiseled_sootbark_bricks", "charwood/chiseled_sootbark_bricks_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:chiseled_sootbark_bricks", 1, "Stonecutting: cut 1x Chiseled Sootbark Bricks from Sootbark.", "Steins\u00e4ge: 1x Gemei\u00dfelte Ru\u00dfrindenziegel aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/sootbark_pillar_from_sootbark_stonecutting", "copper_inferno:sootbark_pillar", "charwood/sootbark_pillar_from_sootbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootbark", "", "", "", ""},
				"copper_inferno:sootbark_pillar", 1, "Stonecutting: cut 1x Sootbark Pillar from Sootbark.", "Steins\u00e4ge: 1x Ru\u00dfrindens\u00e4ule aus Ru\u00dfrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block", "copper_inferno:cinderlog_block", "charwood/cinderlog_block",
				new String[] {"minecraft:oak_log", "", "minecraft:oak_log", "", "copper_inferno:sootbark", "", "minecraft:oak_log", "", "minecraft:oak_log"},
				"copper_inferno:cinderlog_block", 4, "Craft 4x Cinderlog at a crafting table.", "Stellt 4x Zunderscheit an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "charwood/cinderlog_block_bricks",
				new String[] {"copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "", "copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_bricks", 4, "Craft 4x Cinderlog Bricks at a crafting table.", "Stellt 4x Zunderscheitziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_tiles", "copper_inferno:cinderlog_block_tiles", "charwood/cinderlog_block_tiles",
				new String[] {"copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "", "copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "", "", "", ""},
				"copper_inferno:cinderlog_block_tiles", 4, "Craft 4x Cinderlog Tiles at a crafting table.", "Stellt 4x Zunderscheitfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "charwood/polished_cinderlog_block",
				new String[] {"copper_inferno:cinderlog_block_tiles", "copper_inferno:cinderlog_block_tiles", "", "copper_inferno:cinderlog_block_tiles", "copper_inferno:cinderlog_block_tiles", "", "", "", ""},
				"copper_inferno:polished_cinderlog_block", 4, "Craft 4x Polished Cinderlog at a crafting table.", "Stellt 4x Poliertes Zunderscheit an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_slab", "copper_inferno:cinderlog_block_slab", "charwood/cinderlog_block_slab",
				new String[] {"copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "", "", "", "", "", ""},
				"copper_inferno:cinderlog_block_slab", 6, "Craft 6x Cinderlog Slab at a crafting table.", "Stellt 6x Zunderscheitstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_stairs", "copper_inferno:cinderlog_block_stairs", "charwood/cinderlog_block_stairs",
				new String[] {"copper_inferno:cinderlog_block", "", "", "copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "", "copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block"},
				"copper_inferno:cinderlog_block_stairs", 4, "Craft 4x Cinderlog Stairs at a crafting table.", "Stellt 4x Zunderscheittreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_wall", "copper_inferno:cinderlog_block_wall", "charwood/cinderlog_block_wall",
				new String[] {"copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "copper_inferno:cinderlog_block", "", "", ""},
				"copper_inferno:cinderlog_block_wall", 6, "Craft 6x Cinderlog Wall at a crafting table.", "Stellt 6x Zunderscheitmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_cinderlog_block_slab", "copper_inferno:polished_cinderlog_block_slab", "charwood/polished_cinderlog_block_slab",
				new String[] {"copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "", "", "", "", "", ""},
				"copper_inferno:polished_cinderlog_block_slab", 6, "Craft 6x Polished Cinderlog Slab at a crafting table.", "Stellt 6x Polierte Zunderscheitstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_cinderlog_block_stairs", "copper_inferno:polished_cinderlog_block_stairs", "charwood/polished_cinderlog_block_stairs",
				new String[] {"copper_inferno:polished_cinderlog_block", "", "", "copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "", "copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block"},
				"copper_inferno:polished_cinderlog_block_stairs", 4, "Craft 4x Polished Cinderlog Stairs at a crafting table.", "Stellt 4x Polierte Zunderscheittreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_cinderlog_block_wall", "copper_inferno:polished_cinderlog_block_wall", "charwood/polished_cinderlog_block_wall",
				new String[] {"copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "copper_inferno:polished_cinderlog_block", "", "", ""},
				"copper_inferno:polished_cinderlog_block_wall", 6, "Craft 6x Polished Cinderlog Wall at a crafting table.", "Stellt 6x Polierte Zunderscheitmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_brick_slab", "copper_inferno:cinderlog_block_brick_slab", "charwood/cinderlog_block_brick_slab",
				new String[] {"copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "", "", "", "", "", ""},
				"copper_inferno:cinderlog_block_brick_slab", 6, "Craft 6x Cinderlog Brick Slab at a crafting table.", "Stellt 6x Zunderscheitziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_brick_stairs", "copper_inferno:cinderlog_block_brick_stairs", "charwood/cinderlog_block_brick_stairs",
				new String[] {"copper_inferno:cinderlog_block_bricks", "", "", "copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "", "copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks"},
				"copper_inferno:cinderlog_block_brick_stairs", 4, "Craft 4x Cinderlog Brick Stairs at a crafting table.", "Stellt 4x Zunderscheitziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_brick_wall", "copper_inferno:cinderlog_block_brick_wall", "charwood/cinderlog_block_brick_wall",
				new String[] {"copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "copper_inferno:cinderlog_block_bricks", "", "", ""},
				"copper_inferno:cinderlog_block_brick_wall", 6, "Craft 6x Cinderlog Brick Wall at a crafting table.", "Stellt 6x Zunderscheitziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_cinderlog_block_bricks", "copper_inferno:cracked_cinderlog_block_bricks", "charwood/cracked_cinderlog_block_bricks",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block_bricks", "", "", "", ""},
				"copper_inferno:cracked_cinderlog_block_bricks", 1, "Smelting Cinderlog Bricks in a furnace yields Cracked Cinderlog Bricks.", "Zunderscheitziegel im Ofen gebrannt ergibt Rissige Zunderscheitziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_cinderlog_block_bricks", "copper_inferno:chiseled_cinderlog_block_bricks", "charwood/chiseled_cinderlog_block_bricks",
				new String[] {"copper_inferno:cinderlog_block_brick_slab", "", "", "copper_inferno:cinderlog_block_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_cinderlog_block_bricks", 1, "Craft 1x Chiseled Cinderlog Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Zunderscheitziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_pillar", "copper_inferno:cinderlog_block_pillar", "charwood/cinderlog_block_pillar",
				new String[] {"copper_inferno:cinderlog_block_bricks", "", "", "copper_inferno:cinderlog_block_bricks", "", "", "", "", ""},
				"copper_inferno:cinderlog_block_pillar", 2, "Craft 2x Cinderlog Pillar at a crafting table.", "Stellt 2x Zunderscheits\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_slab_from_cinderlog_block_stonecutting", "copper_inferno:cinderlog_block_slab", "charwood/cinderlog_block_slab_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_slab", 2, "Stonecutting: cut 2x Cinderlog Slab from Cinderlog.", "Steins\u00e4ge: 2x Zunderscheitstufe aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_stairs_from_cinderlog_block_stonecutting", "copper_inferno:cinderlog_block_stairs", "charwood/cinderlog_block_stairs_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_stairs", 1, "Stonecutting: cut 1x Cinderlog Stairs from Cinderlog.", "Steins\u00e4ge: 1x Zunderscheittreppe aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_wall_from_cinderlog_block_stonecutting", "copper_inferno:cinderlog_block_wall", "charwood/cinderlog_block_wall_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_wall", 1, "Stonecutting: cut 1x Cinderlog Wall from Cinderlog.", "Steins\u00e4ge: 1x Zunderscheitmauer aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_cinderlog_block_from_cinderlog_block_stonecutting", "copper_inferno:polished_cinderlog_block", "charwood/polished_cinderlog_block_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:polished_cinderlog_block", 1, "Stonecutting: cut 1x Polished Cinderlog from Cinderlog.", "Steins\u00e4ge: 1x Poliertes Zunderscheit aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_cinderlog_block_slab_from_cinderlog_block_stonecutting", "copper_inferno:polished_cinderlog_block_slab", "charwood/polished_cinderlog_block_slab_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:polished_cinderlog_block_slab", 2, "Stonecutting: cut 2x Polished Cinderlog Slab from Cinderlog.", "Steins\u00e4ge: 2x Polierte Zunderscheitstufe aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_cinderlog_block_stairs_from_cinderlog_block_stonecutting", "copper_inferno:polished_cinderlog_block_stairs", "charwood/polished_cinderlog_block_stairs_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:polished_cinderlog_block_stairs", 1, "Stonecutting: cut 1x Polished Cinderlog Stairs from Cinderlog.", "Steins\u00e4ge: 1x Polierte Zunderscheittreppe aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_cinderlog_block_wall_from_cinderlog_block_stonecutting", "copper_inferno:polished_cinderlog_block_wall", "charwood/polished_cinderlog_block_wall_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:polished_cinderlog_block_wall", 1, "Stonecutting: cut 1x Polished Cinderlog Wall from Cinderlog.", "Steins\u00e4ge: 1x Polierte Zunderscheitmauer aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_bricks_from_cinderlog_block_stonecutting", "copper_inferno:cinderlog_block_bricks", "charwood/cinderlog_block_bricks_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_bricks", 1, "Stonecutting: cut 1x Cinderlog Bricks from Cinderlog.", "Steins\u00e4ge: 1x Zunderscheitziegel aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_brick_slab_from_cinderlog_block_stonecutting", "copper_inferno:cinderlog_block_brick_slab", "charwood/cinderlog_block_brick_slab_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_brick_slab", 2, "Stonecutting: cut 2x Cinderlog Brick Slab from Cinderlog.", "Steins\u00e4ge: 2x Zunderscheitziegelstufe aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_brick_stairs_from_cinderlog_block_stonecutting", "copper_inferno:cinderlog_block_brick_stairs", "charwood/cinderlog_block_brick_stairs_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_brick_stairs", 1, "Stonecutting: cut 1x Cinderlog Brick Stairs from Cinderlog.", "Steins\u00e4ge: 1x Zunderscheitziegeltreppe aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_brick_wall_from_cinderlog_block_stonecutting", "copper_inferno:cinderlog_block_brick_wall", "charwood/cinderlog_block_brick_wall_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_brick_wall", 1, "Stonecutting: cut 1x Cinderlog Brick Wall from Cinderlog.", "Steins\u00e4ge: 1x Zunderscheitziegelmauer aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_tiles_from_cinderlog_block_stonecutting", "copper_inferno:cinderlog_block_tiles", "charwood/cinderlog_block_tiles_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_tiles", 1, "Stonecutting: cut 1x Cinderlog Tiles from Cinderlog.", "Steins\u00e4ge: 1x Zunderscheitfliesen aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_cinderlog_block_bricks_from_cinderlog_block_stonecutting", "copper_inferno:chiseled_cinderlog_block_bricks", "charwood/chiseled_cinderlog_block_bricks_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:chiseled_cinderlog_block_bricks", 1, "Stonecutting: cut 1x Chiseled Cinderlog Bricks from Cinderlog.", "Steins\u00e4ge: 1x Gemei\u00dfelte Zunderscheitziegel aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cinderlog_block_pillar_from_cinderlog_block_stonecutting", "copper_inferno:cinderlog_block_pillar", "charwood/cinderlog_block_pillar_from_cinderlog_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderlog_block", "", "", "", ""},
				"copper_inferno:cinderlog_block_pillar", 1, "Stonecutting: cut 1x Cinderlog Pillar from Cinderlog.", "Steins\u00e4ge: 1x Zunderscheits\u00e4ule aus Zunderscheit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood", "copper_inferno:blazewood", "charwood/blazewood",
				new String[] {"minecraft:blaze_rod", "", "minecraft:blaze_rod", "", "copper_inferno:cinderlog_block", "", "minecraft:blaze_rod", "", "minecraft:blaze_rod"},
				"copper_inferno:blazewood", 4, "Craft 4x Blazewood at a crafting table.", "Stellt 4x Lohenholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_bricks", "copper_inferno:blazewood_bricks", "charwood/blazewood_bricks",
				new String[] {"copper_inferno:blazewood", "copper_inferno:blazewood", "", "copper_inferno:blazewood", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_bricks", 4, "Craft 4x Blazewood Bricks at a crafting table.", "Stellt 4x Lohenholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_tiles", "copper_inferno:blazewood_tiles", "charwood/blazewood_tiles",
				new String[] {"copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "", "copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "", "", "", ""},
				"copper_inferno:blazewood_tiles", 4, "Craft 4x Blazewood Tiles at a crafting table.", "Stellt 4x Lohenholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_blazewood", "copper_inferno:polished_blazewood", "charwood/polished_blazewood",
				new String[] {"copper_inferno:blazewood_tiles", "copper_inferno:blazewood_tiles", "", "copper_inferno:blazewood_tiles", "copper_inferno:blazewood_tiles", "", "", "", ""},
				"copper_inferno:polished_blazewood", 4, "Craft 4x Polished Blazewood at a crafting table.", "Stellt 4x Poliertes Lohenholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_slab", "copper_inferno:blazewood_slab", "charwood/blazewood_slab",
				new String[] {"copper_inferno:blazewood", "copper_inferno:blazewood", "copper_inferno:blazewood", "", "", "", "", "", ""},
				"copper_inferno:blazewood_slab", 6, "Craft 6x Blazewood Slab at a crafting table.", "Stellt 6x Lohenholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_stairs", "copper_inferno:blazewood_stairs", "charwood/blazewood_stairs",
				new String[] {"copper_inferno:blazewood", "", "", "copper_inferno:blazewood", "copper_inferno:blazewood", "", "copper_inferno:blazewood", "copper_inferno:blazewood", "copper_inferno:blazewood"},
				"copper_inferno:blazewood_stairs", 4, "Craft 4x Blazewood Stairs at a crafting table.", "Stellt 4x Lohenholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_wall", "copper_inferno:blazewood_wall", "charwood/blazewood_wall",
				new String[] {"copper_inferno:blazewood", "copper_inferno:blazewood", "copper_inferno:blazewood", "copper_inferno:blazewood", "copper_inferno:blazewood", "copper_inferno:blazewood", "", "", ""},
				"copper_inferno:blazewood_wall", 6, "Craft 6x Blazewood Wall at a crafting table.", "Stellt 6x Lohenholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_blazewood_slab", "copper_inferno:polished_blazewood_slab", "charwood/polished_blazewood_slab",
				new String[] {"copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood", "", "", "", "", "", ""},
				"copper_inferno:polished_blazewood_slab", 6, "Craft 6x Polished Blazewood Slab at a crafting table.", "Stellt 6x Polierte Lohenholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_blazewood_stairs", "copper_inferno:polished_blazewood_stairs", "charwood/polished_blazewood_stairs",
				new String[] {"copper_inferno:polished_blazewood", "", "", "copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood", "", "copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood"},
				"copper_inferno:polished_blazewood_stairs", 4, "Craft 4x Polished Blazewood Stairs at a crafting table.", "Stellt 4x Polierte Lohenholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_blazewood_wall", "copper_inferno:polished_blazewood_wall", "charwood/polished_blazewood_wall",
				new String[] {"copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood", "copper_inferno:polished_blazewood", "", "", ""},
				"copper_inferno:polished_blazewood_wall", 6, "Craft 6x Polished Blazewood Wall at a crafting table.", "Stellt 6x Polierte Lohenholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_brick_slab", "copper_inferno:blazewood_brick_slab", "charwood/blazewood_brick_slab",
				new String[] {"copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "", "", "", "", "", ""},
				"copper_inferno:blazewood_brick_slab", 6, "Craft 6x Blazewood Brick Slab at a crafting table.", "Stellt 6x Lohenholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_brick_stairs", "copper_inferno:blazewood_brick_stairs", "charwood/blazewood_brick_stairs",
				new String[] {"copper_inferno:blazewood_bricks", "", "", "copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "", "copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks"},
				"copper_inferno:blazewood_brick_stairs", 4, "Craft 4x Blazewood Brick Stairs at a crafting table.", "Stellt 4x Lohenholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_brick_wall", "copper_inferno:blazewood_brick_wall", "charwood/blazewood_brick_wall",
				new String[] {"copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "copper_inferno:blazewood_bricks", "", "", ""},
				"copper_inferno:blazewood_brick_wall", 6, "Craft 6x Blazewood Brick Wall at a crafting table.", "Stellt 6x Lohenholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_blazewood_bricks", "copper_inferno:cracked_blazewood_bricks", "charwood/cracked_blazewood_bricks",
				new String[] {"", "", "", "", "copper_inferno:blazewood_bricks", "", "", "", ""},
				"copper_inferno:cracked_blazewood_bricks", 1, "Smelting Blazewood Bricks in a furnace yields Cracked Blazewood Bricks.", "Lohenholzziegel im Ofen gebrannt ergibt Rissige Lohenholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_blazewood_bricks", "copper_inferno:chiseled_blazewood_bricks", "charwood/chiseled_blazewood_bricks",
				new String[] {"copper_inferno:blazewood_brick_slab", "", "", "copper_inferno:blazewood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_blazewood_bricks", 1, "Craft 1x Chiseled Blazewood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Lohenholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_pillar", "copper_inferno:blazewood_pillar", "charwood/blazewood_pillar",
				new String[] {"copper_inferno:blazewood_bricks", "", "", "copper_inferno:blazewood_bricks", "", "", "", "", ""},
				"copper_inferno:blazewood_pillar", 2, "Craft 2x Blazewood Pillar at a crafting table.", "Stellt 2x Lohenholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_slab_from_blazewood_stonecutting", "copper_inferno:blazewood_slab", "charwood/blazewood_slab_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_slab", 2, "Stonecutting: cut 2x Blazewood Slab from Blazewood.", "Steins\u00e4ge: 2x Lohenholzstufe aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_stairs_from_blazewood_stonecutting", "copper_inferno:blazewood_stairs", "charwood/blazewood_stairs_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_stairs", 1, "Stonecutting: cut 1x Blazewood Stairs from Blazewood.", "Steins\u00e4ge: 1x Lohenholztreppe aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_wall_from_blazewood_stonecutting", "copper_inferno:blazewood_wall", "charwood/blazewood_wall_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_wall", 1, "Stonecutting: cut 1x Blazewood Wall from Blazewood.", "Steins\u00e4ge: 1x Lohenholzmauer aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_blazewood_from_blazewood_stonecutting", "copper_inferno:polished_blazewood", "charwood/polished_blazewood_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:polished_blazewood", 1, "Stonecutting: cut 1x Polished Blazewood from Blazewood.", "Steins\u00e4ge: 1x Poliertes Lohenholz aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_blazewood_slab_from_blazewood_stonecutting", "copper_inferno:polished_blazewood_slab", "charwood/polished_blazewood_slab_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:polished_blazewood_slab", 2, "Stonecutting: cut 2x Polished Blazewood Slab from Blazewood.", "Steins\u00e4ge: 2x Polierte Lohenholzstufe aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_blazewood_stairs_from_blazewood_stonecutting", "copper_inferno:polished_blazewood_stairs", "charwood/polished_blazewood_stairs_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:polished_blazewood_stairs", 1, "Stonecutting: cut 1x Polished Blazewood Stairs from Blazewood.", "Steins\u00e4ge: 1x Polierte Lohenholztreppe aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_blazewood_wall_from_blazewood_stonecutting", "copper_inferno:polished_blazewood_wall", "charwood/polished_blazewood_wall_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:polished_blazewood_wall", 1, "Stonecutting: cut 1x Polished Blazewood Wall from Blazewood.", "Steins\u00e4ge: 1x Polierte Lohenholzmauer aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_bricks_from_blazewood_stonecutting", "copper_inferno:blazewood_bricks", "charwood/blazewood_bricks_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_bricks", 1, "Stonecutting: cut 1x Blazewood Bricks from Blazewood.", "Steins\u00e4ge: 1x Lohenholzziegel aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_brick_slab_from_blazewood_stonecutting", "copper_inferno:blazewood_brick_slab", "charwood/blazewood_brick_slab_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_brick_slab", 2, "Stonecutting: cut 2x Blazewood Brick Slab from Blazewood.", "Steins\u00e4ge: 2x Lohenholzziegelstufe aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_brick_stairs_from_blazewood_stonecutting", "copper_inferno:blazewood_brick_stairs", "charwood/blazewood_brick_stairs_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_brick_stairs", 1, "Stonecutting: cut 1x Blazewood Brick Stairs from Blazewood.", "Steins\u00e4ge: 1x Lohenholzziegeltreppe aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_brick_wall_from_blazewood_stonecutting", "copper_inferno:blazewood_brick_wall", "charwood/blazewood_brick_wall_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_brick_wall", 1, "Stonecutting: cut 1x Blazewood Brick Wall from Blazewood.", "Steins\u00e4ge: 1x Lohenholzziegelmauer aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_tiles_from_blazewood_stonecutting", "copper_inferno:blazewood_tiles", "charwood/blazewood_tiles_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_tiles", 1, "Stonecutting: cut 1x Blazewood Tiles from Blazewood.", "Steins\u00e4ge: 1x Lohenholzfliesen aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_blazewood_bricks_from_blazewood_stonecutting", "copper_inferno:chiseled_blazewood_bricks", "charwood/chiseled_blazewood_bricks_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:chiseled_blazewood_bricks", 1, "Stonecutting: cut 1x Chiseled Blazewood Bricks from Blazewood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Lohenholzziegel aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/blazewood_pillar_from_blazewood_stonecutting", "copper_inferno:blazewood_pillar", "charwood/blazewood_pillar_from_blazewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewood", "", "", "", ""},
				"copper_inferno:blazewood_pillar", 1, "Stonecutting: cut 1x Blazewood Pillar from Blazewood.", "Steins\u00e4ge: 1x Lohenholzs\u00e4ule aus Lohenholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood", "copper_inferno:magmawood", "charwood/magmawood",
				new String[] {"minecraft:magma_block", "", "minecraft:magma_block", "", "copper_inferno:blazewood", "", "minecraft:magma_block", "", "minecraft:magma_block"},
				"copper_inferno:magmawood", 4, "Craft 4x Magmawood at a crafting table.", "Stellt 4x Magmaholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_bricks", "copper_inferno:magmawood_bricks", "charwood/magmawood_bricks",
				new String[] {"copper_inferno:magmawood", "copper_inferno:magmawood", "", "copper_inferno:magmawood", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_bricks", 4, "Craft 4x Magmawood Bricks at a crafting table.", "Stellt 4x Magmaholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_tiles", "copper_inferno:magmawood_tiles", "charwood/magmawood_tiles",
				new String[] {"copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "", "copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "", "", "", ""},
				"copper_inferno:magmawood_tiles", 4, "Craft 4x Magmawood Tiles at a crafting table.", "Stellt 4x Magmaholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_magmawood", "copper_inferno:polished_magmawood", "charwood/polished_magmawood",
				new String[] {"copper_inferno:magmawood_tiles", "copper_inferno:magmawood_tiles", "", "copper_inferno:magmawood_tiles", "copper_inferno:magmawood_tiles", "", "", "", ""},
				"copper_inferno:polished_magmawood", 4, "Craft 4x Polished Magmawood at a crafting table.", "Stellt 4x Poliertes Magmaholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_slab", "copper_inferno:magmawood_slab", "charwood/magmawood_slab",
				new String[] {"copper_inferno:magmawood", "copper_inferno:magmawood", "copper_inferno:magmawood", "", "", "", "", "", ""},
				"copper_inferno:magmawood_slab", 6, "Craft 6x Magmawood Slab at a crafting table.", "Stellt 6x Magmaholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_stairs", "copper_inferno:magmawood_stairs", "charwood/magmawood_stairs",
				new String[] {"copper_inferno:magmawood", "", "", "copper_inferno:magmawood", "copper_inferno:magmawood", "", "copper_inferno:magmawood", "copper_inferno:magmawood", "copper_inferno:magmawood"},
				"copper_inferno:magmawood_stairs", 4, "Craft 4x Magmawood Stairs at a crafting table.", "Stellt 4x Magmaholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_wall", "copper_inferno:magmawood_wall", "charwood/magmawood_wall",
				new String[] {"copper_inferno:magmawood", "copper_inferno:magmawood", "copper_inferno:magmawood", "copper_inferno:magmawood", "copper_inferno:magmawood", "copper_inferno:magmawood", "", "", ""},
				"copper_inferno:magmawood_wall", 6, "Craft 6x Magmawood Wall at a crafting table.", "Stellt 6x Magmaholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_magmawood_slab", "copper_inferno:polished_magmawood_slab", "charwood/polished_magmawood_slab",
				new String[] {"copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood", "", "", "", "", "", ""},
				"copper_inferno:polished_magmawood_slab", 6, "Craft 6x Polished Magmawood Slab at a crafting table.", "Stellt 6x Polierte Magmaholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_magmawood_stairs", "copper_inferno:polished_magmawood_stairs", "charwood/polished_magmawood_stairs",
				new String[] {"copper_inferno:polished_magmawood", "", "", "copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood", "", "copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood"},
				"copper_inferno:polished_magmawood_stairs", 4, "Craft 4x Polished Magmawood Stairs at a crafting table.", "Stellt 4x Polierte Magmaholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_magmawood_wall", "copper_inferno:polished_magmawood_wall", "charwood/polished_magmawood_wall",
				new String[] {"copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood", "copper_inferno:polished_magmawood", "", "", ""},
				"copper_inferno:polished_magmawood_wall", 6, "Craft 6x Polished Magmawood Wall at a crafting table.", "Stellt 6x Polierte Magmaholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_brick_slab", "copper_inferno:magmawood_brick_slab", "charwood/magmawood_brick_slab",
				new String[] {"copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "", "", "", "", "", ""},
				"copper_inferno:magmawood_brick_slab", 6, "Craft 6x Magmawood Brick Slab at a crafting table.", "Stellt 6x Magmaholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_brick_stairs", "copper_inferno:magmawood_brick_stairs", "charwood/magmawood_brick_stairs",
				new String[] {"copper_inferno:magmawood_bricks", "", "", "copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "", "copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks"},
				"copper_inferno:magmawood_brick_stairs", 4, "Craft 4x Magmawood Brick Stairs at a crafting table.", "Stellt 4x Magmaholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_brick_wall", "copper_inferno:magmawood_brick_wall", "charwood/magmawood_brick_wall",
				new String[] {"copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "copper_inferno:magmawood_bricks", "", "", ""},
				"copper_inferno:magmawood_brick_wall", 6, "Craft 6x Magmawood Brick Wall at a crafting table.", "Stellt 6x Magmaholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_magmawood_bricks", "copper_inferno:cracked_magmawood_bricks", "charwood/cracked_magmawood_bricks",
				new String[] {"", "", "", "", "copper_inferno:magmawood_bricks", "", "", "", ""},
				"copper_inferno:cracked_magmawood_bricks", 1, "Smelting Magmawood Bricks in a furnace yields Cracked Magmawood Bricks.", "Magmaholzziegel im Ofen gebrannt ergibt Rissige Magmaholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_magmawood_bricks", "copper_inferno:chiseled_magmawood_bricks", "charwood/chiseled_magmawood_bricks",
				new String[] {"copper_inferno:magmawood_brick_slab", "", "", "copper_inferno:magmawood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_magmawood_bricks", 1, "Craft 1x Chiseled Magmawood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Magmaholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_pillar", "copper_inferno:magmawood_pillar", "charwood/magmawood_pillar",
				new String[] {"copper_inferno:magmawood_bricks", "", "", "copper_inferno:magmawood_bricks", "", "", "", "", ""},
				"copper_inferno:magmawood_pillar", 2, "Craft 2x Magmawood Pillar at a crafting table.", "Stellt 2x Magmaholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_slab_from_magmawood_stonecutting", "copper_inferno:magmawood_slab", "charwood/magmawood_slab_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_slab", 2, "Stonecutting: cut 2x Magmawood Slab from Magmawood.", "Steins\u00e4ge: 2x Magmaholzstufe aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_stairs_from_magmawood_stonecutting", "copper_inferno:magmawood_stairs", "charwood/magmawood_stairs_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_stairs", 1, "Stonecutting: cut 1x Magmawood Stairs from Magmawood.", "Steins\u00e4ge: 1x Magmaholztreppe aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_wall_from_magmawood_stonecutting", "copper_inferno:magmawood_wall", "charwood/magmawood_wall_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_wall", 1, "Stonecutting: cut 1x Magmawood Wall from Magmawood.", "Steins\u00e4ge: 1x Magmaholzmauer aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_magmawood_from_magmawood_stonecutting", "copper_inferno:polished_magmawood", "charwood/polished_magmawood_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:polished_magmawood", 1, "Stonecutting: cut 1x Polished Magmawood from Magmawood.", "Steins\u00e4ge: 1x Poliertes Magmaholz aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_magmawood_slab_from_magmawood_stonecutting", "copper_inferno:polished_magmawood_slab", "charwood/polished_magmawood_slab_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:polished_magmawood_slab", 2, "Stonecutting: cut 2x Polished Magmawood Slab from Magmawood.", "Steins\u00e4ge: 2x Polierte Magmaholzstufe aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_magmawood_stairs_from_magmawood_stonecutting", "copper_inferno:polished_magmawood_stairs", "charwood/polished_magmawood_stairs_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:polished_magmawood_stairs", 1, "Stonecutting: cut 1x Polished Magmawood Stairs from Magmawood.", "Steins\u00e4ge: 1x Polierte Magmaholztreppe aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_magmawood_wall_from_magmawood_stonecutting", "copper_inferno:polished_magmawood_wall", "charwood/polished_magmawood_wall_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:polished_magmawood_wall", 1, "Stonecutting: cut 1x Polished Magmawood Wall from Magmawood.", "Steins\u00e4ge: 1x Polierte Magmaholzmauer aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_bricks_from_magmawood_stonecutting", "copper_inferno:magmawood_bricks", "charwood/magmawood_bricks_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_bricks", 1, "Stonecutting: cut 1x Magmawood Bricks from Magmawood.", "Steins\u00e4ge: 1x Magmaholzziegel aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_brick_slab_from_magmawood_stonecutting", "copper_inferno:magmawood_brick_slab", "charwood/magmawood_brick_slab_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_brick_slab", 2, "Stonecutting: cut 2x Magmawood Brick Slab from Magmawood.", "Steins\u00e4ge: 2x Magmaholzziegelstufe aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_brick_stairs_from_magmawood_stonecutting", "copper_inferno:magmawood_brick_stairs", "charwood/magmawood_brick_stairs_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_brick_stairs", 1, "Stonecutting: cut 1x Magmawood Brick Stairs from Magmawood.", "Steins\u00e4ge: 1x Magmaholzziegeltreppe aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_brick_wall_from_magmawood_stonecutting", "copper_inferno:magmawood_brick_wall", "charwood/magmawood_brick_wall_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_brick_wall", 1, "Stonecutting: cut 1x Magmawood Brick Wall from Magmawood.", "Steins\u00e4ge: 1x Magmaholzziegelmauer aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_tiles_from_magmawood_stonecutting", "copper_inferno:magmawood_tiles", "charwood/magmawood_tiles_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_tiles", 1, "Stonecutting: cut 1x Magmawood Tiles from Magmawood.", "Steins\u00e4ge: 1x Magmaholzfliesen aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_magmawood_bricks_from_magmawood_stonecutting", "copper_inferno:chiseled_magmawood_bricks", "charwood/chiseled_magmawood_bricks_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:chiseled_magmawood_bricks", 1, "Stonecutting: cut 1x Chiseled Magmawood Bricks from Magmawood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Magmaholzziegel aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/magmawood_pillar_from_magmawood_stonecutting", "copper_inferno:magmawood_pillar", "charwood/magmawood_pillar_from_magmawood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmawood", "", "", "", ""},
				"copper_inferno:magmawood_pillar", 1, "Stonecutting: cut 1x Magmawood Pillar from Magmawood.", "Steins\u00e4ge: 1x Magmaholzs\u00e4ule aus Magmaholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood", "copper_inferno:kindlewood", "charwood/kindlewood",
				new String[] {"minecraft:stick", "", "minecraft:stick", "", "copper_inferno:magmawood", "", "minecraft:stick", "", "minecraft:stick"},
				"copper_inferno:kindlewood", 4, "Craft 4x Kindlewood at a crafting table.", "Stellt 4x Z\u00fcndelholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_bricks", "copper_inferno:kindlewood_bricks", "charwood/kindlewood_bricks",
				new String[] {"copper_inferno:kindlewood", "copper_inferno:kindlewood", "", "copper_inferno:kindlewood", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_bricks", 4, "Craft 4x Kindlewood Bricks at a crafting table.", "Stellt 4x Z\u00fcndelholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_tiles", "copper_inferno:kindlewood_tiles", "charwood/kindlewood_tiles",
				new String[] {"copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "", "copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "", "", "", ""},
				"copper_inferno:kindlewood_tiles", 4, "Craft 4x Kindlewood Tiles at a crafting table.", "Stellt 4x Z\u00fcndelholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_kindlewood", "copper_inferno:polished_kindlewood", "charwood/polished_kindlewood",
				new String[] {"copper_inferno:kindlewood_tiles", "copper_inferno:kindlewood_tiles", "", "copper_inferno:kindlewood_tiles", "copper_inferno:kindlewood_tiles", "", "", "", ""},
				"copper_inferno:polished_kindlewood", 4, "Craft 4x Polished Kindlewood at a crafting table.", "Stellt 4x Poliertes Z\u00fcndelholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_slab", "copper_inferno:kindlewood_slab", "charwood/kindlewood_slab",
				new String[] {"copper_inferno:kindlewood", "copper_inferno:kindlewood", "copper_inferno:kindlewood", "", "", "", "", "", ""},
				"copper_inferno:kindlewood_slab", 6, "Craft 6x Kindlewood Slab at a crafting table.", "Stellt 6x Z\u00fcndelholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_stairs", "copper_inferno:kindlewood_stairs", "charwood/kindlewood_stairs",
				new String[] {"copper_inferno:kindlewood", "", "", "copper_inferno:kindlewood", "copper_inferno:kindlewood", "", "copper_inferno:kindlewood", "copper_inferno:kindlewood", "copper_inferno:kindlewood"},
				"copper_inferno:kindlewood_stairs", 4, "Craft 4x Kindlewood Stairs at a crafting table.", "Stellt 4x Z\u00fcndelholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_wall", "copper_inferno:kindlewood_wall", "charwood/kindlewood_wall",
				new String[] {"copper_inferno:kindlewood", "copper_inferno:kindlewood", "copper_inferno:kindlewood", "copper_inferno:kindlewood", "copper_inferno:kindlewood", "copper_inferno:kindlewood", "", "", ""},
				"copper_inferno:kindlewood_wall", 6, "Craft 6x Kindlewood Wall at a crafting table.", "Stellt 6x Z\u00fcndelholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_kindlewood_slab", "copper_inferno:polished_kindlewood_slab", "charwood/polished_kindlewood_slab",
				new String[] {"copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood", "", "", "", "", "", ""},
				"copper_inferno:polished_kindlewood_slab", 6, "Craft 6x Polished Kindlewood Slab at a crafting table.", "Stellt 6x Polierte Z\u00fcndelholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_kindlewood_stairs", "copper_inferno:polished_kindlewood_stairs", "charwood/polished_kindlewood_stairs",
				new String[] {"copper_inferno:polished_kindlewood", "", "", "copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood", "", "copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood"},
				"copper_inferno:polished_kindlewood_stairs", 4, "Craft 4x Polished Kindlewood Stairs at a crafting table.", "Stellt 4x Polierte Z\u00fcndelholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_kindlewood_wall", "copper_inferno:polished_kindlewood_wall", "charwood/polished_kindlewood_wall",
				new String[] {"copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood", "copper_inferno:polished_kindlewood", "", "", ""},
				"copper_inferno:polished_kindlewood_wall", 6, "Craft 6x Polished Kindlewood Wall at a crafting table.", "Stellt 6x Polierte Z\u00fcndelholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_brick_slab", "copper_inferno:kindlewood_brick_slab", "charwood/kindlewood_brick_slab",
				new String[] {"copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "", "", "", "", "", ""},
				"copper_inferno:kindlewood_brick_slab", 6, "Craft 6x Kindlewood Brick Slab at a crafting table.", "Stellt 6x Z\u00fcndelholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_brick_stairs", "copper_inferno:kindlewood_brick_stairs", "charwood/kindlewood_brick_stairs",
				new String[] {"copper_inferno:kindlewood_bricks", "", "", "copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "", "copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks"},
				"copper_inferno:kindlewood_brick_stairs", 4, "Craft 4x Kindlewood Brick Stairs at a crafting table.", "Stellt 4x Z\u00fcndelholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_brick_wall", "copper_inferno:kindlewood_brick_wall", "charwood/kindlewood_brick_wall",
				new String[] {"copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "copper_inferno:kindlewood_bricks", "", "", ""},
				"copper_inferno:kindlewood_brick_wall", 6, "Craft 6x Kindlewood Brick Wall at a crafting table.", "Stellt 6x Z\u00fcndelholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_kindlewood_bricks", "copper_inferno:cracked_kindlewood_bricks", "charwood/cracked_kindlewood_bricks",
				new String[] {"", "", "", "", "copper_inferno:kindlewood_bricks", "", "", "", ""},
				"copper_inferno:cracked_kindlewood_bricks", 1, "Smelting Kindlewood Bricks in a furnace yields Cracked Kindlewood Bricks.", "Z\u00fcndelholzziegel im Ofen gebrannt ergibt Rissige Z\u00fcndelholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_kindlewood_bricks", "copper_inferno:chiseled_kindlewood_bricks", "charwood/chiseled_kindlewood_bricks",
				new String[] {"copper_inferno:kindlewood_brick_slab", "", "", "copper_inferno:kindlewood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_kindlewood_bricks", 1, "Craft 1x Chiseled Kindlewood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Z\u00fcndelholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_pillar", "copper_inferno:kindlewood_pillar", "charwood/kindlewood_pillar",
				new String[] {"copper_inferno:kindlewood_bricks", "", "", "copper_inferno:kindlewood_bricks", "", "", "", "", ""},
				"copper_inferno:kindlewood_pillar", 2, "Craft 2x Kindlewood Pillar at a crafting table.", "Stellt 2x Z\u00fcndelholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_slab_from_kindlewood_stonecutting", "copper_inferno:kindlewood_slab", "charwood/kindlewood_slab_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_slab", 2, "Stonecutting: cut 2x Kindlewood Slab from Kindlewood.", "Steins\u00e4ge: 2x Z\u00fcndelholzstufe aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_stairs_from_kindlewood_stonecutting", "copper_inferno:kindlewood_stairs", "charwood/kindlewood_stairs_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_stairs", 1, "Stonecutting: cut 1x Kindlewood Stairs from Kindlewood.", "Steins\u00e4ge: 1x Z\u00fcndelholztreppe aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_wall_from_kindlewood_stonecutting", "copper_inferno:kindlewood_wall", "charwood/kindlewood_wall_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_wall", 1, "Stonecutting: cut 1x Kindlewood Wall from Kindlewood.", "Steins\u00e4ge: 1x Z\u00fcndelholzmauer aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_kindlewood_from_kindlewood_stonecutting", "copper_inferno:polished_kindlewood", "charwood/polished_kindlewood_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:polished_kindlewood", 1, "Stonecutting: cut 1x Polished Kindlewood from Kindlewood.", "Steins\u00e4ge: 1x Poliertes Z\u00fcndelholz aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_kindlewood_slab_from_kindlewood_stonecutting", "copper_inferno:polished_kindlewood_slab", "charwood/polished_kindlewood_slab_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:polished_kindlewood_slab", 2, "Stonecutting: cut 2x Polished Kindlewood Slab from Kindlewood.", "Steins\u00e4ge: 2x Polierte Z\u00fcndelholzstufe aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_kindlewood_stairs_from_kindlewood_stonecutting", "copper_inferno:polished_kindlewood_stairs", "charwood/polished_kindlewood_stairs_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:polished_kindlewood_stairs", 1, "Stonecutting: cut 1x Polished Kindlewood Stairs from Kindlewood.", "Steins\u00e4ge: 1x Polierte Z\u00fcndelholztreppe aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_kindlewood_wall_from_kindlewood_stonecutting", "copper_inferno:polished_kindlewood_wall", "charwood/polished_kindlewood_wall_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:polished_kindlewood_wall", 1, "Stonecutting: cut 1x Polished Kindlewood Wall from Kindlewood.", "Steins\u00e4ge: 1x Polierte Z\u00fcndelholzmauer aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_bricks_from_kindlewood_stonecutting", "copper_inferno:kindlewood_bricks", "charwood/kindlewood_bricks_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_bricks", 1, "Stonecutting: cut 1x Kindlewood Bricks from Kindlewood.", "Steins\u00e4ge: 1x Z\u00fcndelholzziegel aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_brick_slab_from_kindlewood_stonecutting", "copper_inferno:kindlewood_brick_slab", "charwood/kindlewood_brick_slab_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_brick_slab", 2, "Stonecutting: cut 2x Kindlewood Brick Slab from Kindlewood.", "Steins\u00e4ge: 2x Z\u00fcndelholzziegelstufe aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_brick_stairs_from_kindlewood_stonecutting", "copper_inferno:kindlewood_brick_stairs", "charwood/kindlewood_brick_stairs_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_brick_stairs", 1, "Stonecutting: cut 1x Kindlewood Brick Stairs from Kindlewood.", "Steins\u00e4ge: 1x Z\u00fcndelholzziegeltreppe aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_brick_wall_from_kindlewood_stonecutting", "copper_inferno:kindlewood_brick_wall", "charwood/kindlewood_brick_wall_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_brick_wall", 1, "Stonecutting: cut 1x Kindlewood Brick Wall from Kindlewood.", "Steins\u00e4ge: 1x Z\u00fcndelholzziegelmauer aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_tiles_from_kindlewood_stonecutting", "copper_inferno:kindlewood_tiles", "charwood/kindlewood_tiles_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_tiles", 1, "Stonecutting: cut 1x Kindlewood Tiles from Kindlewood.", "Steins\u00e4ge: 1x Z\u00fcndelholzfliesen aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_kindlewood_bricks_from_kindlewood_stonecutting", "copper_inferno:chiseled_kindlewood_bricks", "charwood/chiseled_kindlewood_bricks_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:chiseled_kindlewood_bricks", 1, "Stonecutting: cut 1x Chiseled Kindlewood Bricks from Kindlewood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Z\u00fcndelholzziegel aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/kindlewood_pillar_from_kindlewood_stonecutting", "copper_inferno:kindlewood_pillar", "charwood/kindlewood_pillar_from_kindlewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlewood", "", "", "", ""},
				"copper_inferno:kindlewood_pillar", 1, "Stonecutting: cut 1x Kindlewood Pillar from Kindlewood.", "Steins\u00e4ge: 1x Z\u00fcndelholzs\u00e4ule aus Z\u00fcndelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood", "copper_inferno:pyrewood", "charwood/pyrewood",
				new String[] {"minecraft:spruce_log", "", "minecraft:spruce_log", "", "copper_inferno:kindlewood", "", "minecraft:spruce_log", "", "minecraft:spruce_log"},
				"copper_inferno:pyrewood", 4, "Craft 4x Pyrewood at a crafting table.", "Stellt 4x Scheiterholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_bricks", "copper_inferno:pyrewood_bricks", "charwood/pyrewood_bricks",
				new String[] {"copper_inferno:pyrewood", "copper_inferno:pyrewood", "", "copper_inferno:pyrewood", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_bricks", 4, "Craft 4x Pyrewood Bricks at a crafting table.", "Stellt 4x Scheiterholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_tiles", "copper_inferno:pyrewood_tiles", "charwood/pyrewood_tiles",
				new String[] {"copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "", "copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "", "", "", ""},
				"copper_inferno:pyrewood_tiles", 4, "Craft 4x Pyrewood Tiles at a crafting table.", "Stellt 4x Scheiterholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_pyrewood", "copper_inferno:polished_pyrewood", "charwood/polished_pyrewood",
				new String[] {"copper_inferno:pyrewood_tiles", "copper_inferno:pyrewood_tiles", "", "copper_inferno:pyrewood_tiles", "copper_inferno:pyrewood_tiles", "", "", "", ""},
				"copper_inferno:polished_pyrewood", 4, "Craft 4x Polished Pyrewood at a crafting table.", "Stellt 4x Poliertes Scheiterholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_slab", "copper_inferno:pyrewood_slab", "charwood/pyrewood_slab",
				new String[] {"copper_inferno:pyrewood", "copper_inferno:pyrewood", "copper_inferno:pyrewood", "", "", "", "", "", ""},
				"copper_inferno:pyrewood_slab", 6, "Craft 6x Pyrewood Slab at a crafting table.", "Stellt 6x Scheiterholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_stairs", "copper_inferno:pyrewood_stairs", "charwood/pyrewood_stairs",
				new String[] {"copper_inferno:pyrewood", "", "", "copper_inferno:pyrewood", "copper_inferno:pyrewood", "", "copper_inferno:pyrewood", "copper_inferno:pyrewood", "copper_inferno:pyrewood"},
				"copper_inferno:pyrewood_stairs", 4, "Craft 4x Pyrewood Stairs at a crafting table.", "Stellt 4x Scheiterholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_wall", "copper_inferno:pyrewood_wall", "charwood/pyrewood_wall",
				new String[] {"copper_inferno:pyrewood", "copper_inferno:pyrewood", "copper_inferno:pyrewood", "copper_inferno:pyrewood", "copper_inferno:pyrewood", "copper_inferno:pyrewood", "", "", ""},
				"copper_inferno:pyrewood_wall", 6, "Craft 6x Pyrewood Wall at a crafting table.", "Stellt 6x Scheiterholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_pyrewood_slab", "copper_inferno:polished_pyrewood_slab", "charwood/polished_pyrewood_slab",
				new String[] {"copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood", "", "", "", "", "", ""},
				"copper_inferno:polished_pyrewood_slab", 6, "Craft 6x Polished Pyrewood Slab at a crafting table.", "Stellt 6x Polierte Scheiterholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_pyrewood_stairs", "copper_inferno:polished_pyrewood_stairs", "charwood/polished_pyrewood_stairs",
				new String[] {"copper_inferno:polished_pyrewood", "", "", "copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood", "", "copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood"},
				"copper_inferno:polished_pyrewood_stairs", 4, "Craft 4x Polished Pyrewood Stairs at a crafting table.", "Stellt 4x Polierte Scheiterholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_pyrewood_wall", "copper_inferno:polished_pyrewood_wall", "charwood/polished_pyrewood_wall",
				new String[] {"copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood", "copper_inferno:polished_pyrewood", "", "", ""},
				"copper_inferno:polished_pyrewood_wall", 6, "Craft 6x Polished Pyrewood Wall at a crafting table.", "Stellt 6x Polierte Scheiterholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_brick_slab", "copper_inferno:pyrewood_brick_slab", "charwood/pyrewood_brick_slab",
				new String[] {"copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "", "", "", "", "", ""},
				"copper_inferno:pyrewood_brick_slab", 6, "Craft 6x Pyrewood Brick Slab at a crafting table.", "Stellt 6x Scheiterholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_brick_stairs", "copper_inferno:pyrewood_brick_stairs", "charwood/pyrewood_brick_stairs",
				new String[] {"copper_inferno:pyrewood_bricks", "", "", "copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "", "copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks"},
				"copper_inferno:pyrewood_brick_stairs", 4, "Craft 4x Pyrewood Brick Stairs at a crafting table.", "Stellt 4x Scheiterholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_brick_wall", "copper_inferno:pyrewood_brick_wall", "charwood/pyrewood_brick_wall",
				new String[] {"copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "copper_inferno:pyrewood_bricks", "", "", ""},
				"copper_inferno:pyrewood_brick_wall", 6, "Craft 6x Pyrewood Brick Wall at a crafting table.", "Stellt 6x Scheiterholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_pyrewood_bricks", "copper_inferno:cracked_pyrewood_bricks", "charwood/cracked_pyrewood_bricks",
				new String[] {"", "", "", "", "copper_inferno:pyrewood_bricks", "", "", "", ""},
				"copper_inferno:cracked_pyrewood_bricks", 1, "Smelting Pyrewood Bricks in a furnace yields Cracked Pyrewood Bricks.", "Scheiterholzziegel im Ofen gebrannt ergibt Rissige Scheiterholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_pyrewood_bricks", "copper_inferno:chiseled_pyrewood_bricks", "charwood/chiseled_pyrewood_bricks",
				new String[] {"copper_inferno:pyrewood_brick_slab", "", "", "copper_inferno:pyrewood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_pyrewood_bricks", 1, "Craft 1x Chiseled Pyrewood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Scheiterholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_pillar", "copper_inferno:pyrewood_pillar", "charwood/pyrewood_pillar",
				new String[] {"copper_inferno:pyrewood_bricks", "", "", "copper_inferno:pyrewood_bricks", "", "", "", "", ""},
				"copper_inferno:pyrewood_pillar", 2, "Craft 2x Pyrewood Pillar at a crafting table.", "Stellt 2x Scheiterholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_slab_from_pyrewood_stonecutting", "copper_inferno:pyrewood_slab", "charwood/pyrewood_slab_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_slab", 2, "Stonecutting: cut 2x Pyrewood Slab from Pyrewood.", "Steins\u00e4ge: 2x Scheiterholzstufe aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_stairs_from_pyrewood_stonecutting", "copper_inferno:pyrewood_stairs", "charwood/pyrewood_stairs_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_stairs", 1, "Stonecutting: cut 1x Pyrewood Stairs from Pyrewood.", "Steins\u00e4ge: 1x Scheiterholztreppe aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_wall_from_pyrewood_stonecutting", "copper_inferno:pyrewood_wall", "charwood/pyrewood_wall_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_wall", 1, "Stonecutting: cut 1x Pyrewood Wall from Pyrewood.", "Steins\u00e4ge: 1x Scheiterholzmauer aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_pyrewood_from_pyrewood_stonecutting", "copper_inferno:polished_pyrewood", "charwood/polished_pyrewood_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:polished_pyrewood", 1, "Stonecutting: cut 1x Polished Pyrewood from Pyrewood.", "Steins\u00e4ge: 1x Poliertes Scheiterholz aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_pyrewood_slab_from_pyrewood_stonecutting", "copper_inferno:polished_pyrewood_slab", "charwood/polished_pyrewood_slab_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:polished_pyrewood_slab", 2, "Stonecutting: cut 2x Polished Pyrewood Slab from Pyrewood.", "Steins\u00e4ge: 2x Polierte Scheiterholzstufe aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_pyrewood_stairs_from_pyrewood_stonecutting", "copper_inferno:polished_pyrewood_stairs", "charwood/polished_pyrewood_stairs_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:polished_pyrewood_stairs", 1, "Stonecutting: cut 1x Polished Pyrewood Stairs from Pyrewood.", "Steins\u00e4ge: 1x Polierte Scheiterholztreppe aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_pyrewood_wall_from_pyrewood_stonecutting", "copper_inferno:polished_pyrewood_wall", "charwood/polished_pyrewood_wall_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:polished_pyrewood_wall", 1, "Stonecutting: cut 1x Polished Pyrewood Wall from Pyrewood.", "Steins\u00e4ge: 1x Polierte Scheiterholzmauer aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_bricks_from_pyrewood_stonecutting", "copper_inferno:pyrewood_bricks", "charwood/pyrewood_bricks_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_bricks", 1, "Stonecutting: cut 1x Pyrewood Bricks from Pyrewood.", "Steins\u00e4ge: 1x Scheiterholzziegel aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_brick_slab_from_pyrewood_stonecutting", "copper_inferno:pyrewood_brick_slab", "charwood/pyrewood_brick_slab_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_brick_slab", 2, "Stonecutting: cut 2x Pyrewood Brick Slab from Pyrewood.", "Steins\u00e4ge: 2x Scheiterholzziegelstufe aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_brick_stairs_from_pyrewood_stonecutting", "copper_inferno:pyrewood_brick_stairs", "charwood/pyrewood_brick_stairs_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_brick_stairs", 1, "Stonecutting: cut 1x Pyrewood Brick Stairs from Pyrewood.", "Steins\u00e4ge: 1x Scheiterholzziegeltreppe aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_brick_wall_from_pyrewood_stonecutting", "copper_inferno:pyrewood_brick_wall", "charwood/pyrewood_brick_wall_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_brick_wall", 1, "Stonecutting: cut 1x Pyrewood Brick Wall from Pyrewood.", "Steins\u00e4ge: 1x Scheiterholzziegelmauer aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_tiles_from_pyrewood_stonecutting", "copper_inferno:pyrewood_tiles", "charwood/pyrewood_tiles_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_tiles", 1, "Stonecutting: cut 1x Pyrewood Tiles from Pyrewood.", "Steins\u00e4ge: 1x Scheiterholzfliesen aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_pyrewood_bricks_from_pyrewood_stonecutting", "copper_inferno:chiseled_pyrewood_bricks", "charwood/chiseled_pyrewood_bricks_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:chiseled_pyrewood_bricks", 1, "Stonecutting: cut 1x Chiseled Pyrewood Bricks from Pyrewood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Scheiterholzziegel aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/pyrewood_pillar_from_pyrewood_stonecutting", "copper_inferno:pyrewood_pillar", "charwood/pyrewood_pillar_from_pyrewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrewood", "", "", "", ""},
				"copper_inferno:pyrewood_pillar", 1, "Stonecutting: cut 1x Pyrewood Pillar from Pyrewood.", "Steins\u00e4ge: 1x Scheiterholzs\u00e4ule aus Scheiterholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood", "copper_inferno:smolderwood", "charwood/smolderwood",
				new String[] {"minecraft:magma_cream", "", "minecraft:magma_cream", "", "copper_inferno:pyrewood", "", "minecraft:magma_cream", "", "minecraft:magma_cream"},
				"copper_inferno:smolderwood", 4, "Craft 4x Smolderwood at a crafting table.", "Stellt 4x Schwelholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_bricks", "copper_inferno:smolderwood_bricks", "charwood/smolderwood_bricks",
				new String[] {"copper_inferno:smolderwood", "copper_inferno:smolderwood", "", "copper_inferno:smolderwood", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_bricks", 4, "Craft 4x Smolderwood Bricks at a crafting table.", "Stellt 4x Schwelholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_tiles", "copper_inferno:smolderwood_tiles", "charwood/smolderwood_tiles",
				new String[] {"copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "", "copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "", "", "", ""},
				"copper_inferno:smolderwood_tiles", 4, "Craft 4x Smolderwood Tiles at a crafting table.", "Stellt 4x Schwelholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_smolderwood", "copper_inferno:polished_smolderwood", "charwood/polished_smolderwood",
				new String[] {"copper_inferno:smolderwood_tiles", "copper_inferno:smolderwood_tiles", "", "copper_inferno:smolderwood_tiles", "copper_inferno:smolderwood_tiles", "", "", "", ""},
				"copper_inferno:polished_smolderwood", 4, "Craft 4x Polished Smolderwood at a crafting table.", "Stellt 4x Poliertes Schwelholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_slab", "copper_inferno:smolderwood_slab", "charwood/smolderwood_slab",
				new String[] {"copper_inferno:smolderwood", "copper_inferno:smolderwood", "copper_inferno:smolderwood", "", "", "", "", "", ""},
				"copper_inferno:smolderwood_slab", 6, "Craft 6x Smolderwood Slab at a crafting table.", "Stellt 6x Schwelholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_stairs", "copper_inferno:smolderwood_stairs", "charwood/smolderwood_stairs",
				new String[] {"copper_inferno:smolderwood", "", "", "copper_inferno:smolderwood", "copper_inferno:smolderwood", "", "copper_inferno:smolderwood", "copper_inferno:smolderwood", "copper_inferno:smolderwood"},
				"copper_inferno:smolderwood_stairs", 4, "Craft 4x Smolderwood Stairs at a crafting table.", "Stellt 4x Schwelholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_wall", "copper_inferno:smolderwood_wall", "charwood/smolderwood_wall",
				new String[] {"copper_inferno:smolderwood", "copper_inferno:smolderwood", "copper_inferno:smolderwood", "copper_inferno:smolderwood", "copper_inferno:smolderwood", "copper_inferno:smolderwood", "", "", ""},
				"copper_inferno:smolderwood_wall", 6, "Craft 6x Smolderwood Wall at a crafting table.", "Stellt 6x Schwelholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_smolderwood_slab", "copper_inferno:polished_smolderwood_slab", "charwood/polished_smolderwood_slab",
				new String[] {"copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood", "", "", "", "", "", ""},
				"copper_inferno:polished_smolderwood_slab", 6, "Craft 6x Polished Smolderwood Slab at a crafting table.", "Stellt 6x Polierte Schwelholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_smolderwood_stairs", "copper_inferno:polished_smolderwood_stairs", "charwood/polished_smolderwood_stairs",
				new String[] {"copper_inferno:polished_smolderwood", "", "", "copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood", "", "copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood"},
				"copper_inferno:polished_smolderwood_stairs", 4, "Craft 4x Polished Smolderwood Stairs at a crafting table.", "Stellt 4x Polierte Schwelholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_smolderwood_wall", "copper_inferno:polished_smolderwood_wall", "charwood/polished_smolderwood_wall",
				new String[] {"copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood", "copper_inferno:polished_smolderwood", "", "", ""},
				"copper_inferno:polished_smolderwood_wall", 6, "Craft 6x Polished Smolderwood Wall at a crafting table.", "Stellt 6x Polierte Schwelholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_brick_slab", "copper_inferno:smolderwood_brick_slab", "charwood/smolderwood_brick_slab",
				new String[] {"copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "", "", "", "", "", ""},
				"copper_inferno:smolderwood_brick_slab", 6, "Craft 6x Smolderwood Brick Slab at a crafting table.", "Stellt 6x Schwelholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_brick_stairs", "copper_inferno:smolderwood_brick_stairs", "charwood/smolderwood_brick_stairs",
				new String[] {"copper_inferno:smolderwood_bricks", "", "", "copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "", "copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks"},
				"copper_inferno:smolderwood_brick_stairs", 4, "Craft 4x Smolderwood Brick Stairs at a crafting table.", "Stellt 4x Schwelholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_brick_wall", "copper_inferno:smolderwood_brick_wall", "charwood/smolderwood_brick_wall",
				new String[] {"copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "copper_inferno:smolderwood_bricks", "", "", ""},
				"copper_inferno:smolderwood_brick_wall", 6, "Craft 6x Smolderwood Brick Wall at a crafting table.", "Stellt 6x Schwelholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_smolderwood_bricks", "copper_inferno:cracked_smolderwood_bricks", "charwood/cracked_smolderwood_bricks",
				new String[] {"", "", "", "", "copper_inferno:smolderwood_bricks", "", "", "", ""},
				"copper_inferno:cracked_smolderwood_bricks", 1, "Smelting Smolderwood Bricks in a furnace yields Cracked Smolderwood Bricks.", "Schwelholzziegel im Ofen gebrannt ergibt Rissige Schwelholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_smolderwood_bricks", "copper_inferno:chiseled_smolderwood_bricks", "charwood/chiseled_smolderwood_bricks",
				new String[] {"copper_inferno:smolderwood_brick_slab", "", "", "copper_inferno:smolderwood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_smolderwood_bricks", 1, "Craft 1x Chiseled Smolderwood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schwelholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_pillar", "copper_inferno:smolderwood_pillar", "charwood/smolderwood_pillar",
				new String[] {"copper_inferno:smolderwood_bricks", "", "", "copper_inferno:smolderwood_bricks", "", "", "", "", ""},
				"copper_inferno:smolderwood_pillar", 2, "Craft 2x Smolderwood Pillar at a crafting table.", "Stellt 2x Schwelholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_slab_from_smolderwood_stonecutting", "copper_inferno:smolderwood_slab", "charwood/smolderwood_slab_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_slab", 2, "Stonecutting: cut 2x Smolderwood Slab from Smolderwood.", "Steins\u00e4ge: 2x Schwelholzstufe aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_stairs_from_smolderwood_stonecutting", "copper_inferno:smolderwood_stairs", "charwood/smolderwood_stairs_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_stairs", 1, "Stonecutting: cut 1x Smolderwood Stairs from Smolderwood.", "Steins\u00e4ge: 1x Schwelholztreppe aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_wall_from_smolderwood_stonecutting", "copper_inferno:smolderwood_wall", "charwood/smolderwood_wall_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_wall", 1, "Stonecutting: cut 1x Smolderwood Wall from Smolderwood.", "Steins\u00e4ge: 1x Schwelholzmauer aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_smolderwood_from_smolderwood_stonecutting", "copper_inferno:polished_smolderwood", "charwood/polished_smolderwood_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:polished_smolderwood", 1, "Stonecutting: cut 1x Polished Smolderwood from Smolderwood.", "Steins\u00e4ge: 1x Poliertes Schwelholz aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_smolderwood_slab_from_smolderwood_stonecutting", "copper_inferno:polished_smolderwood_slab", "charwood/polished_smolderwood_slab_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:polished_smolderwood_slab", 2, "Stonecutting: cut 2x Polished Smolderwood Slab from Smolderwood.", "Steins\u00e4ge: 2x Polierte Schwelholzstufe aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_smolderwood_stairs_from_smolderwood_stonecutting", "copper_inferno:polished_smolderwood_stairs", "charwood/polished_smolderwood_stairs_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:polished_smolderwood_stairs", 1, "Stonecutting: cut 1x Polished Smolderwood Stairs from Smolderwood.", "Steins\u00e4ge: 1x Polierte Schwelholztreppe aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_smolderwood_wall_from_smolderwood_stonecutting", "copper_inferno:polished_smolderwood_wall", "charwood/polished_smolderwood_wall_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:polished_smolderwood_wall", 1, "Stonecutting: cut 1x Polished Smolderwood Wall from Smolderwood.", "Steins\u00e4ge: 1x Polierte Schwelholzmauer aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_bricks_from_smolderwood_stonecutting", "copper_inferno:smolderwood_bricks", "charwood/smolderwood_bricks_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_bricks", 1, "Stonecutting: cut 1x Smolderwood Bricks from Smolderwood.", "Steins\u00e4ge: 1x Schwelholzziegel aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_brick_slab_from_smolderwood_stonecutting", "copper_inferno:smolderwood_brick_slab", "charwood/smolderwood_brick_slab_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_brick_slab", 2, "Stonecutting: cut 2x Smolderwood Brick Slab from Smolderwood.", "Steins\u00e4ge: 2x Schwelholzziegelstufe aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_brick_stairs_from_smolderwood_stonecutting", "copper_inferno:smolderwood_brick_stairs", "charwood/smolderwood_brick_stairs_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_brick_stairs", 1, "Stonecutting: cut 1x Smolderwood Brick Stairs from Smolderwood.", "Steins\u00e4ge: 1x Schwelholzziegeltreppe aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_brick_wall_from_smolderwood_stonecutting", "copper_inferno:smolderwood_brick_wall", "charwood/smolderwood_brick_wall_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_brick_wall", 1, "Stonecutting: cut 1x Smolderwood Brick Wall from Smolderwood.", "Steins\u00e4ge: 1x Schwelholzziegelmauer aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_tiles_from_smolderwood_stonecutting", "copper_inferno:smolderwood_tiles", "charwood/smolderwood_tiles_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_tiles", 1, "Stonecutting: cut 1x Smolderwood Tiles from Smolderwood.", "Steins\u00e4ge: 1x Schwelholzfliesen aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_smolderwood_bricks_from_smolderwood_stonecutting", "copper_inferno:chiseled_smolderwood_bricks", "charwood/chiseled_smolderwood_bricks_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:chiseled_smolderwood_bricks", 1, "Stonecutting: cut 1x Chiseled Smolderwood Bricks from Smolderwood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schwelholzziegel aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/smolderwood_pillar_from_smolderwood_stonecutting", "copper_inferno:smolderwood_pillar", "charwood/smolderwood_pillar_from_smolderwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolderwood", "", "", "", ""},
				"copper_inferno:smolderwood_pillar", 1, "Stonecutting: cut 1x Smolderwood Pillar from Smolderwood.", "Steins\u00e4ge: 1x Schwelholzs\u00e4ule aus Schwelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark", "copper_inferno:glowbark", "charwood/glowbark",
				new String[] {"minecraft:glowstone_dust", "", "minecraft:glowstone_dust", "", "copper_inferno:smolderwood", "", "minecraft:glowstone_dust", "", "minecraft:glowstone_dust"},
				"copper_inferno:glowbark", 4, "Craft 4x Glowbark at a crafting table.", "Stellt 4x Leuchtrinde an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_bricks", "copper_inferno:glowbark_bricks", "charwood/glowbark_bricks",
				new String[] {"copper_inferno:glowbark", "copper_inferno:glowbark", "", "copper_inferno:glowbark", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_bricks", 4, "Craft 4x Glowbark Bricks at a crafting table.", "Stellt 4x Leuchtrindenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_tiles", "copper_inferno:glowbark_tiles", "charwood/glowbark_tiles",
				new String[] {"copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "", "copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "", "", "", ""},
				"copper_inferno:glowbark_tiles", 4, "Craft 4x Glowbark Tiles at a crafting table.", "Stellt 4x Leuchtrindenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_glowbark", "copper_inferno:polished_glowbark", "charwood/polished_glowbark",
				new String[] {"copper_inferno:glowbark_tiles", "copper_inferno:glowbark_tiles", "", "copper_inferno:glowbark_tiles", "copper_inferno:glowbark_tiles", "", "", "", ""},
				"copper_inferno:polished_glowbark", 4, "Craft 4x Polished Glowbark at a crafting table.", "Stellt 4x Polierte Leuchtrinde an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_slab", "copper_inferno:glowbark_slab", "charwood/glowbark_slab",
				new String[] {"copper_inferno:glowbark", "copper_inferno:glowbark", "copper_inferno:glowbark", "", "", "", "", "", ""},
				"copper_inferno:glowbark_slab", 6, "Craft 6x Glowbark Slab at a crafting table.", "Stellt 6x Leuchtrindenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_stairs", "copper_inferno:glowbark_stairs", "charwood/glowbark_stairs",
				new String[] {"copper_inferno:glowbark", "", "", "copper_inferno:glowbark", "copper_inferno:glowbark", "", "copper_inferno:glowbark", "copper_inferno:glowbark", "copper_inferno:glowbark"},
				"copper_inferno:glowbark_stairs", 4, "Craft 4x Glowbark Stairs at a crafting table.", "Stellt 4x Leuchtrindentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_wall", "copper_inferno:glowbark_wall", "charwood/glowbark_wall",
				new String[] {"copper_inferno:glowbark", "copper_inferno:glowbark", "copper_inferno:glowbark", "copper_inferno:glowbark", "copper_inferno:glowbark", "copper_inferno:glowbark", "", "", ""},
				"copper_inferno:glowbark_wall", 6, "Craft 6x Glowbark Wall at a crafting table.", "Stellt 6x Leuchtrindenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_glowbark_slab", "copper_inferno:polished_glowbark_slab", "charwood/polished_glowbark_slab",
				new String[] {"copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark", "", "", "", "", "", ""},
				"copper_inferno:polished_glowbark_slab", 6, "Craft 6x Polished Glowbark Slab at a crafting table.", "Stellt 6x Polierte Leuchtrindenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_glowbark_stairs", "copper_inferno:polished_glowbark_stairs", "charwood/polished_glowbark_stairs",
				new String[] {"copper_inferno:polished_glowbark", "", "", "copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark", "", "copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark"},
				"copper_inferno:polished_glowbark_stairs", 4, "Craft 4x Polished Glowbark Stairs at a crafting table.", "Stellt 4x Polierte Leuchtrindentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_glowbark_wall", "copper_inferno:polished_glowbark_wall", "charwood/polished_glowbark_wall",
				new String[] {"copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark", "copper_inferno:polished_glowbark", "", "", ""},
				"copper_inferno:polished_glowbark_wall", 6, "Craft 6x Polished Glowbark Wall at a crafting table.", "Stellt 6x Polierte Leuchtrindenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_brick_slab", "copper_inferno:glowbark_brick_slab", "charwood/glowbark_brick_slab",
				new String[] {"copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "", "", "", "", "", ""},
				"copper_inferno:glowbark_brick_slab", 6, "Craft 6x Glowbark Brick Slab at a crafting table.", "Stellt 6x Leuchtrindenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_brick_stairs", "copper_inferno:glowbark_brick_stairs", "charwood/glowbark_brick_stairs",
				new String[] {"copper_inferno:glowbark_bricks", "", "", "copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "", "copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks"},
				"copper_inferno:glowbark_brick_stairs", 4, "Craft 4x Glowbark Brick Stairs at a crafting table.", "Stellt 4x Leuchtrindenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_brick_wall", "copper_inferno:glowbark_brick_wall", "charwood/glowbark_brick_wall",
				new String[] {"copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "copper_inferno:glowbark_bricks", "", "", ""},
				"copper_inferno:glowbark_brick_wall", 6, "Craft 6x Glowbark Brick Wall at a crafting table.", "Stellt 6x Leuchtrindenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_glowbark_bricks", "copper_inferno:cracked_glowbark_bricks", "charwood/cracked_glowbark_bricks",
				new String[] {"", "", "", "", "copper_inferno:glowbark_bricks", "", "", "", ""},
				"copper_inferno:cracked_glowbark_bricks", 1, "Smelting Glowbark Bricks in a furnace yields Cracked Glowbark Bricks.", "Leuchtrindenziegel im Ofen gebrannt ergibt Rissige Leuchtrindenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_glowbark_bricks", "copper_inferno:chiseled_glowbark_bricks", "charwood/chiseled_glowbark_bricks",
				new String[] {"copper_inferno:glowbark_brick_slab", "", "", "copper_inferno:glowbark_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_glowbark_bricks", 1, "Craft 1x Chiseled Glowbark Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Leuchtrindenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_pillar", "copper_inferno:glowbark_pillar", "charwood/glowbark_pillar",
				new String[] {"copper_inferno:glowbark_bricks", "", "", "copper_inferno:glowbark_bricks", "", "", "", "", ""},
				"copper_inferno:glowbark_pillar", 2, "Craft 2x Glowbark Pillar at a crafting table.", "Stellt 2x Leuchtrindens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_slab_from_glowbark_stonecutting", "copper_inferno:glowbark_slab", "charwood/glowbark_slab_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_slab", 2, "Stonecutting: cut 2x Glowbark Slab from Glowbark.", "Steins\u00e4ge: 2x Leuchtrindenstufe aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_stairs_from_glowbark_stonecutting", "copper_inferno:glowbark_stairs", "charwood/glowbark_stairs_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_stairs", 1, "Stonecutting: cut 1x Glowbark Stairs from Glowbark.", "Steins\u00e4ge: 1x Leuchtrindentreppe aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_wall_from_glowbark_stonecutting", "copper_inferno:glowbark_wall", "charwood/glowbark_wall_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_wall", 1, "Stonecutting: cut 1x Glowbark Wall from Glowbark.", "Steins\u00e4ge: 1x Leuchtrindenmauer aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_glowbark_from_glowbark_stonecutting", "copper_inferno:polished_glowbark", "charwood/polished_glowbark_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:polished_glowbark", 1, "Stonecutting: cut 1x Polished Glowbark from Glowbark.", "Steins\u00e4ge: 1x Polierte Leuchtrinde aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_glowbark_slab_from_glowbark_stonecutting", "copper_inferno:polished_glowbark_slab", "charwood/polished_glowbark_slab_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:polished_glowbark_slab", 2, "Stonecutting: cut 2x Polished Glowbark Slab from Glowbark.", "Steins\u00e4ge: 2x Polierte Leuchtrindenstufe aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_glowbark_stairs_from_glowbark_stonecutting", "copper_inferno:polished_glowbark_stairs", "charwood/polished_glowbark_stairs_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:polished_glowbark_stairs", 1, "Stonecutting: cut 1x Polished Glowbark Stairs from Glowbark.", "Steins\u00e4ge: 1x Polierte Leuchtrindentreppe aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_glowbark_wall_from_glowbark_stonecutting", "copper_inferno:polished_glowbark_wall", "charwood/polished_glowbark_wall_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:polished_glowbark_wall", 1, "Stonecutting: cut 1x Polished Glowbark Wall from Glowbark.", "Steins\u00e4ge: 1x Polierte Leuchtrindenmauer aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_bricks_from_glowbark_stonecutting", "copper_inferno:glowbark_bricks", "charwood/glowbark_bricks_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_bricks", 1, "Stonecutting: cut 1x Glowbark Bricks from Glowbark.", "Steins\u00e4ge: 1x Leuchtrindenziegel aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_brick_slab_from_glowbark_stonecutting", "copper_inferno:glowbark_brick_slab", "charwood/glowbark_brick_slab_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_brick_slab", 2, "Stonecutting: cut 2x Glowbark Brick Slab from Glowbark.", "Steins\u00e4ge: 2x Leuchtrindenziegelstufe aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_brick_stairs_from_glowbark_stonecutting", "copper_inferno:glowbark_brick_stairs", "charwood/glowbark_brick_stairs_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_brick_stairs", 1, "Stonecutting: cut 1x Glowbark Brick Stairs from Glowbark.", "Steins\u00e4ge: 1x Leuchtrindenziegeltreppe aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_brick_wall_from_glowbark_stonecutting", "copper_inferno:glowbark_brick_wall", "charwood/glowbark_brick_wall_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_brick_wall", 1, "Stonecutting: cut 1x Glowbark Brick Wall from Glowbark.", "Steins\u00e4ge: 1x Leuchtrindenziegelmauer aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_tiles_from_glowbark_stonecutting", "copper_inferno:glowbark_tiles", "charwood/glowbark_tiles_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_tiles", 1, "Stonecutting: cut 1x Glowbark Tiles from Glowbark.", "Steins\u00e4ge: 1x Leuchtrindenfliesen aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_glowbark_bricks_from_glowbark_stonecutting", "copper_inferno:chiseled_glowbark_bricks", "charwood/chiseled_glowbark_bricks_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:chiseled_glowbark_bricks", 1, "Stonecutting: cut 1x Chiseled Glowbark Bricks from Glowbark.", "Steins\u00e4ge: 1x Gemei\u00dfelte Leuchtrindenziegel aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/glowbark_pillar_from_glowbark_stonecutting", "copper_inferno:glowbark_pillar", "charwood/glowbark_pillar_from_glowbark_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowbark", "", "", "", ""},
				"copper_inferno:glowbark_pillar", 1, "Stonecutting: cut 1x Glowbark Pillar from Glowbark.", "Steins\u00e4ge: 1x Leuchtrindens\u00e4ule aus Leuchtrinde schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood", "copper_inferno:coalwood", "charwood/coalwood",
				new String[] {"minecraft:coal_block", "", "minecraft:coal_block", "", "copper_inferno:glowbark", "", "minecraft:coal_block", "", "minecraft:coal_block"},
				"copper_inferno:coalwood", 4, "Craft 4x Coalwood at a crafting table.", "Stellt 4x Anthrazitholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_bricks", "copper_inferno:coalwood_bricks", "charwood/coalwood_bricks",
				new String[] {"copper_inferno:coalwood", "copper_inferno:coalwood", "", "copper_inferno:coalwood", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_bricks", 4, "Craft 4x Coalwood Bricks at a crafting table.", "Stellt 4x Anthrazitholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_tiles", "copper_inferno:coalwood_tiles", "charwood/coalwood_tiles",
				new String[] {"copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "", "copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "", "", "", ""},
				"copper_inferno:coalwood_tiles", 4, "Craft 4x Coalwood Tiles at a crafting table.", "Stellt 4x Anthrazitholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_coalwood", "copper_inferno:polished_coalwood", "charwood/polished_coalwood",
				new String[] {"copper_inferno:coalwood_tiles", "copper_inferno:coalwood_tiles", "", "copper_inferno:coalwood_tiles", "copper_inferno:coalwood_tiles", "", "", "", ""},
				"copper_inferno:polished_coalwood", 4, "Craft 4x Polished Coalwood at a crafting table.", "Stellt 4x Poliertes Anthrazitholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_slab", "copper_inferno:coalwood_slab", "charwood/coalwood_slab",
				new String[] {"copper_inferno:coalwood", "copper_inferno:coalwood", "copper_inferno:coalwood", "", "", "", "", "", ""},
				"copper_inferno:coalwood_slab", 6, "Craft 6x Coalwood Slab at a crafting table.", "Stellt 6x Anthrazitholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_stairs", "copper_inferno:coalwood_stairs", "charwood/coalwood_stairs",
				new String[] {"copper_inferno:coalwood", "", "", "copper_inferno:coalwood", "copper_inferno:coalwood", "", "copper_inferno:coalwood", "copper_inferno:coalwood", "copper_inferno:coalwood"},
				"copper_inferno:coalwood_stairs", 4, "Craft 4x Coalwood Stairs at a crafting table.", "Stellt 4x Anthrazitholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_wall", "copper_inferno:coalwood_wall", "charwood/coalwood_wall",
				new String[] {"copper_inferno:coalwood", "copper_inferno:coalwood", "copper_inferno:coalwood", "copper_inferno:coalwood", "copper_inferno:coalwood", "copper_inferno:coalwood", "", "", ""},
				"copper_inferno:coalwood_wall", 6, "Craft 6x Coalwood Wall at a crafting table.", "Stellt 6x Anthrazitholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_coalwood_slab", "copper_inferno:polished_coalwood_slab", "charwood/polished_coalwood_slab",
				new String[] {"copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood", "", "", "", "", "", ""},
				"copper_inferno:polished_coalwood_slab", 6, "Craft 6x Polished Coalwood Slab at a crafting table.", "Stellt 6x Polierte Anthrazitholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_coalwood_stairs", "copper_inferno:polished_coalwood_stairs", "charwood/polished_coalwood_stairs",
				new String[] {"copper_inferno:polished_coalwood", "", "", "copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood", "", "copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood"},
				"copper_inferno:polished_coalwood_stairs", 4, "Craft 4x Polished Coalwood Stairs at a crafting table.", "Stellt 4x Polierte Anthrazitholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_coalwood_wall", "copper_inferno:polished_coalwood_wall", "charwood/polished_coalwood_wall",
				new String[] {"copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood", "copper_inferno:polished_coalwood", "", "", ""},
				"copper_inferno:polished_coalwood_wall", 6, "Craft 6x Polished Coalwood Wall at a crafting table.", "Stellt 6x Polierte Anthrazitholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_brick_slab", "copper_inferno:coalwood_brick_slab", "charwood/coalwood_brick_slab",
				new String[] {"copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "", "", "", "", "", ""},
				"copper_inferno:coalwood_brick_slab", 6, "Craft 6x Coalwood Brick Slab at a crafting table.", "Stellt 6x Anthrazitholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_brick_stairs", "copper_inferno:coalwood_brick_stairs", "charwood/coalwood_brick_stairs",
				new String[] {"copper_inferno:coalwood_bricks", "", "", "copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "", "copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks"},
				"copper_inferno:coalwood_brick_stairs", 4, "Craft 4x Coalwood Brick Stairs at a crafting table.", "Stellt 4x Anthrazitholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_brick_wall", "copper_inferno:coalwood_brick_wall", "charwood/coalwood_brick_wall",
				new String[] {"copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "copper_inferno:coalwood_bricks", "", "", ""},
				"copper_inferno:coalwood_brick_wall", 6, "Craft 6x Coalwood Brick Wall at a crafting table.", "Stellt 6x Anthrazitholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_coalwood_bricks", "copper_inferno:cracked_coalwood_bricks", "charwood/cracked_coalwood_bricks",
				new String[] {"", "", "", "", "copper_inferno:coalwood_bricks", "", "", "", ""},
				"copper_inferno:cracked_coalwood_bricks", 1, "Smelting Coalwood Bricks in a furnace yields Cracked Coalwood Bricks.", "Anthrazitholzziegel im Ofen gebrannt ergibt Rissige Anthrazitholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_coalwood_bricks", "copper_inferno:chiseled_coalwood_bricks", "charwood/chiseled_coalwood_bricks",
				new String[] {"copper_inferno:coalwood_brick_slab", "", "", "copper_inferno:coalwood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_coalwood_bricks", 1, "Craft 1x Chiseled Coalwood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Anthrazitholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_pillar", "copper_inferno:coalwood_pillar", "charwood/coalwood_pillar",
				new String[] {"copper_inferno:coalwood_bricks", "", "", "copper_inferno:coalwood_bricks", "", "", "", "", ""},
				"copper_inferno:coalwood_pillar", 2, "Craft 2x Coalwood Pillar at a crafting table.", "Stellt 2x Anthrazitholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_slab_from_coalwood_stonecutting", "copper_inferno:coalwood_slab", "charwood/coalwood_slab_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_slab", 2, "Stonecutting: cut 2x Coalwood Slab from Coalwood.", "Steins\u00e4ge: 2x Anthrazitholzstufe aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_stairs_from_coalwood_stonecutting", "copper_inferno:coalwood_stairs", "charwood/coalwood_stairs_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_stairs", 1, "Stonecutting: cut 1x Coalwood Stairs from Coalwood.", "Steins\u00e4ge: 1x Anthrazitholztreppe aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_wall_from_coalwood_stonecutting", "copper_inferno:coalwood_wall", "charwood/coalwood_wall_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_wall", 1, "Stonecutting: cut 1x Coalwood Wall from Coalwood.", "Steins\u00e4ge: 1x Anthrazitholzmauer aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_coalwood_from_coalwood_stonecutting", "copper_inferno:polished_coalwood", "charwood/polished_coalwood_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:polished_coalwood", 1, "Stonecutting: cut 1x Polished Coalwood from Coalwood.", "Steins\u00e4ge: 1x Poliertes Anthrazitholz aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_coalwood_slab_from_coalwood_stonecutting", "copper_inferno:polished_coalwood_slab", "charwood/polished_coalwood_slab_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:polished_coalwood_slab", 2, "Stonecutting: cut 2x Polished Coalwood Slab from Coalwood.", "Steins\u00e4ge: 2x Polierte Anthrazitholzstufe aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_coalwood_stairs_from_coalwood_stonecutting", "copper_inferno:polished_coalwood_stairs", "charwood/polished_coalwood_stairs_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:polished_coalwood_stairs", 1, "Stonecutting: cut 1x Polished Coalwood Stairs from Coalwood.", "Steins\u00e4ge: 1x Polierte Anthrazitholztreppe aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_coalwood_wall_from_coalwood_stonecutting", "copper_inferno:polished_coalwood_wall", "charwood/polished_coalwood_wall_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:polished_coalwood_wall", 1, "Stonecutting: cut 1x Polished Coalwood Wall from Coalwood.", "Steins\u00e4ge: 1x Polierte Anthrazitholzmauer aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_bricks_from_coalwood_stonecutting", "copper_inferno:coalwood_bricks", "charwood/coalwood_bricks_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_bricks", 1, "Stonecutting: cut 1x Coalwood Bricks from Coalwood.", "Steins\u00e4ge: 1x Anthrazitholzziegel aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_brick_slab_from_coalwood_stonecutting", "copper_inferno:coalwood_brick_slab", "charwood/coalwood_brick_slab_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_brick_slab", 2, "Stonecutting: cut 2x Coalwood Brick Slab from Coalwood.", "Steins\u00e4ge: 2x Anthrazitholzziegelstufe aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_brick_stairs_from_coalwood_stonecutting", "copper_inferno:coalwood_brick_stairs", "charwood/coalwood_brick_stairs_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_brick_stairs", 1, "Stonecutting: cut 1x Coalwood Brick Stairs from Coalwood.", "Steins\u00e4ge: 1x Anthrazitholzziegeltreppe aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_brick_wall_from_coalwood_stonecutting", "copper_inferno:coalwood_brick_wall", "charwood/coalwood_brick_wall_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_brick_wall", 1, "Stonecutting: cut 1x Coalwood Brick Wall from Coalwood.", "Steins\u00e4ge: 1x Anthrazitholzziegelmauer aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_tiles_from_coalwood_stonecutting", "copper_inferno:coalwood_tiles", "charwood/coalwood_tiles_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_tiles", 1, "Stonecutting: cut 1x Coalwood Tiles from Coalwood.", "Steins\u00e4ge: 1x Anthrazitholzfliesen aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_coalwood_bricks_from_coalwood_stonecutting", "copper_inferno:chiseled_coalwood_bricks", "charwood/chiseled_coalwood_bricks_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:chiseled_coalwood_bricks", 1, "Stonecutting: cut 1x Chiseled Coalwood Bricks from Coalwood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Anthrazitholzziegel aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/coalwood_pillar_from_coalwood_stonecutting", "copper_inferno:coalwood_pillar", "charwood/coalwood_pillar_from_coalwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalwood", "", "", "", ""},
				"copper_inferno:coalwood_pillar", 1, "Stonecutting: cut 1x Coalwood Pillar from Coalwood.", "Steins\u00e4ge: 1x Anthrazitholzs\u00e4ule aus Anthrazitholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood", "copper_inferno:tarwood", "charwood/tarwood",
				new String[] {"minecraft:dark_oak_log", "", "minecraft:dark_oak_log", "", "copper_inferno:coalwood", "", "minecraft:dark_oak_log", "", "minecraft:dark_oak_log"},
				"copper_inferno:tarwood", 4, "Craft 4x Tarwood at a crafting table.", "Stellt 4x Teerholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_bricks", "copper_inferno:tarwood_bricks", "charwood/tarwood_bricks",
				new String[] {"copper_inferno:tarwood", "copper_inferno:tarwood", "", "copper_inferno:tarwood", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_bricks", 4, "Craft 4x Tarwood Bricks at a crafting table.", "Stellt 4x Teerholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_tiles", "copper_inferno:tarwood_tiles", "charwood/tarwood_tiles",
				new String[] {"copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "", "copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "", "", "", ""},
				"copper_inferno:tarwood_tiles", 4, "Craft 4x Tarwood Tiles at a crafting table.", "Stellt 4x Teerholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_tarwood", "copper_inferno:polished_tarwood", "charwood/polished_tarwood",
				new String[] {"copper_inferno:tarwood_tiles", "copper_inferno:tarwood_tiles", "", "copper_inferno:tarwood_tiles", "copper_inferno:tarwood_tiles", "", "", "", ""},
				"copper_inferno:polished_tarwood", 4, "Craft 4x Polished Tarwood at a crafting table.", "Stellt 4x Poliertes Teerholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_slab", "copper_inferno:tarwood_slab", "charwood/tarwood_slab",
				new String[] {"copper_inferno:tarwood", "copper_inferno:tarwood", "copper_inferno:tarwood", "", "", "", "", "", ""},
				"copper_inferno:tarwood_slab", 6, "Craft 6x Tarwood Slab at a crafting table.", "Stellt 6x Teerholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_stairs", "copper_inferno:tarwood_stairs", "charwood/tarwood_stairs",
				new String[] {"copper_inferno:tarwood", "", "", "copper_inferno:tarwood", "copper_inferno:tarwood", "", "copper_inferno:tarwood", "copper_inferno:tarwood", "copper_inferno:tarwood"},
				"copper_inferno:tarwood_stairs", 4, "Craft 4x Tarwood Stairs at a crafting table.", "Stellt 4x Teerholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_wall", "copper_inferno:tarwood_wall", "charwood/tarwood_wall",
				new String[] {"copper_inferno:tarwood", "copper_inferno:tarwood", "copper_inferno:tarwood", "copper_inferno:tarwood", "copper_inferno:tarwood", "copper_inferno:tarwood", "", "", ""},
				"copper_inferno:tarwood_wall", 6, "Craft 6x Tarwood Wall at a crafting table.", "Stellt 6x Teerholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_tarwood_slab", "copper_inferno:polished_tarwood_slab", "charwood/polished_tarwood_slab",
				new String[] {"copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood", "", "", "", "", "", ""},
				"copper_inferno:polished_tarwood_slab", 6, "Craft 6x Polished Tarwood Slab at a crafting table.", "Stellt 6x Polierte Teerholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_tarwood_stairs", "copper_inferno:polished_tarwood_stairs", "charwood/polished_tarwood_stairs",
				new String[] {"copper_inferno:polished_tarwood", "", "", "copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood", "", "copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood"},
				"copper_inferno:polished_tarwood_stairs", 4, "Craft 4x Polished Tarwood Stairs at a crafting table.", "Stellt 4x Polierte Teerholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_tarwood_wall", "copper_inferno:polished_tarwood_wall", "charwood/polished_tarwood_wall",
				new String[] {"copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood", "copper_inferno:polished_tarwood", "", "", ""},
				"copper_inferno:polished_tarwood_wall", 6, "Craft 6x Polished Tarwood Wall at a crafting table.", "Stellt 6x Polierte Teerholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_brick_slab", "copper_inferno:tarwood_brick_slab", "charwood/tarwood_brick_slab",
				new String[] {"copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "", "", "", "", "", ""},
				"copper_inferno:tarwood_brick_slab", 6, "Craft 6x Tarwood Brick Slab at a crafting table.", "Stellt 6x Teerholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_brick_stairs", "copper_inferno:tarwood_brick_stairs", "charwood/tarwood_brick_stairs",
				new String[] {"copper_inferno:tarwood_bricks", "", "", "copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "", "copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks"},
				"copper_inferno:tarwood_brick_stairs", 4, "Craft 4x Tarwood Brick Stairs at a crafting table.", "Stellt 4x Teerholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_brick_wall", "copper_inferno:tarwood_brick_wall", "charwood/tarwood_brick_wall",
				new String[] {"copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "copper_inferno:tarwood_bricks", "", "", ""},
				"copper_inferno:tarwood_brick_wall", 6, "Craft 6x Tarwood Brick Wall at a crafting table.", "Stellt 6x Teerholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_tarwood_bricks", "copper_inferno:cracked_tarwood_bricks", "charwood/cracked_tarwood_bricks",
				new String[] {"", "", "", "", "copper_inferno:tarwood_bricks", "", "", "", ""},
				"copper_inferno:cracked_tarwood_bricks", 1, "Smelting Tarwood Bricks in a furnace yields Cracked Tarwood Bricks.", "Teerholzziegel im Ofen gebrannt ergibt Rissige Teerholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_tarwood_bricks", "copper_inferno:chiseled_tarwood_bricks", "charwood/chiseled_tarwood_bricks",
				new String[] {"copper_inferno:tarwood_brick_slab", "", "", "copper_inferno:tarwood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_tarwood_bricks", 1, "Craft 1x Chiseled Tarwood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Teerholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_pillar", "copper_inferno:tarwood_pillar", "charwood/tarwood_pillar",
				new String[] {"copper_inferno:tarwood_bricks", "", "", "copper_inferno:tarwood_bricks", "", "", "", "", ""},
				"copper_inferno:tarwood_pillar", 2, "Craft 2x Tarwood Pillar at a crafting table.", "Stellt 2x Teerholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_slab_from_tarwood_stonecutting", "copper_inferno:tarwood_slab", "charwood/tarwood_slab_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_slab", 2, "Stonecutting: cut 2x Tarwood Slab from Tarwood.", "Steins\u00e4ge: 2x Teerholzstufe aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_stairs_from_tarwood_stonecutting", "copper_inferno:tarwood_stairs", "charwood/tarwood_stairs_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_stairs", 1, "Stonecutting: cut 1x Tarwood Stairs from Tarwood.", "Steins\u00e4ge: 1x Teerholztreppe aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_wall_from_tarwood_stonecutting", "copper_inferno:tarwood_wall", "charwood/tarwood_wall_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_wall", 1, "Stonecutting: cut 1x Tarwood Wall from Tarwood.", "Steins\u00e4ge: 1x Teerholzmauer aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_tarwood_from_tarwood_stonecutting", "copper_inferno:polished_tarwood", "charwood/polished_tarwood_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:polished_tarwood", 1, "Stonecutting: cut 1x Polished Tarwood from Tarwood.", "Steins\u00e4ge: 1x Poliertes Teerholz aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_tarwood_slab_from_tarwood_stonecutting", "copper_inferno:polished_tarwood_slab", "charwood/polished_tarwood_slab_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:polished_tarwood_slab", 2, "Stonecutting: cut 2x Polished Tarwood Slab from Tarwood.", "Steins\u00e4ge: 2x Polierte Teerholzstufe aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_tarwood_stairs_from_tarwood_stonecutting", "copper_inferno:polished_tarwood_stairs", "charwood/polished_tarwood_stairs_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:polished_tarwood_stairs", 1, "Stonecutting: cut 1x Polished Tarwood Stairs from Tarwood.", "Steins\u00e4ge: 1x Polierte Teerholztreppe aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_tarwood_wall_from_tarwood_stonecutting", "copper_inferno:polished_tarwood_wall", "charwood/polished_tarwood_wall_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:polished_tarwood_wall", 1, "Stonecutting: cut 1x Polished Tarwood Wall from Tarwood.", "Steins\u00e4ge: 1x Polierte Teerholzmauer aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_bricks_from_tarwood_stonecutting", "copper_inferno:tarwood_bricks", "charwood/tarwood_bricks_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_bricks", 1, "Stonecutting: cut 1x Tarwood Bricks from Tarwood.", "Steins\u00e4ge: 1x Teerholzziegel aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_brick_slab_from_tarwood_stonecutting", "copper_inferno:tarwood_brick_slab", "charwood/tarwood_brick_slab_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_brick_slab", 2, "Stonecutting: cut 2x Tarwood Brick Slab from Tarwood.", "Steins\u00e4ge: 2x Teerholzziegelstufe aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_brick_stairs_from_tarwood_stonecutting", "copper_inferno:tarwood_brick_stairs", "charwood/tarwood_brick_stairs_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_brick_stairs", 1, "Stonecutting: cut 1x Tarwood Brick Stairs from Tarwood.", "Steins\u00e4ge: 1x Teerholzziegeltreppe aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_brick_wall_from_tarwood_stonecutting", "copper_inferno:tarwood_brick_wall", "charwood/tarwood_brick_wall_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_brick_wall", 1, "Stonecutting: cut 1x Tarwood Brick Wall from Tarwood.", "Steins\u00e4ge: 1x Teerholzziegelmauer aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_tiles_from_tarwood_stonecutting", "copper_inferno:tarwood_tiles", "charwood/tarwood_tiles_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_tiles", 1, "Stonecutting: cut 1x Tarwood Tiles from Tarwood.", "Steins\u00e4ge: 1x Teerholzfliesen aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_tarwood_bricks_from_tarwood_stonecutting", "copper_inferno:chiseled_tarwood_bricks", "charwood/chiseled_tarwood_bricks_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:chiseled_tarwood_bricks", 1, "Stonecutting: cut 1x Chiseled Tarwood Bricks from Tarwood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Teerholzziegel aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/tarwood_pillar_from_tarwood_stonecutting", "copper_inferno:tarwood_pillar", "charwood/tarwood_pillar_from_tarwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tarwood", "", "", "", ""},
				"copper_inferno:tarwood_pillar", 1, "Stonecutting: cut 1x Tarwood Pillar from Tarwood.", "Steins\u00e4ge: 1x Teerholzs\u00e4ule aus Teerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood", "copper_inferno:brimwood", "charwood/brimwood",
				new String[] {"minecraft:gunpowder", "", "minecraft:gunpowder", "", "copper_inferno:tarwood", "", "minecraft:gunpowder", "", "minecraft:gunpowder"},
				"copper_inferno:brimwood", 4, "Craft 4x Brimwood at a crafting table.", "Stellt 4x Schwefelholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_bricks", "copper_inferno:brimwood_bricks", "charwood/brimwood_bricks",
				new String[] {"copper_inferno:brimwood", "copper_inferno:brimwood", "", "copper_inferno:brimwood", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_bricks", 4, "Craft 4x Brimwood Bricks at a crafting table.", "Stellt 4x Schwefelholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_tiles", "copper_inferno:brimwood_tiles", "charwood/brimwood_tiles",
				new String[] {"copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "", "copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "", "", "", ""},
				"copper_inferno:brimwood_tiles", 4, "Craft 4x Brimwood Tiles at a crafting table.", "Stellt 4x Schwefelholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_brimwood", "copper_inferno:polished_brimwood", "charwood/polished_brimwood",
				new String[] {"copper_inferno:brimwood_tiles", "copper_inferno:brimwood_tiles", "", "copper_inferno:brimwood_tiles", "copper_inferno:brimwood_tiles", "", "", "", ""},
				"copper_inferno:polished_brimwood", 4, "Craft 4x Polished Brimwood at a crafting table.", "Stellt 4x Poliertes Schwefelholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_slab", "copper_inferno:brimwood_slab", "charwood/brimwood_slab",
				new String[] {"copper_inferno:brimwood", "copper_inferno:brimwood", "copper_inferno:brimwood", "", "", "", "", "", ""},
				"copper_inferno:brimwood_slab", 6, "Craft 6x Brimwood Slab at a crafting table.", "Stellt 6x Schwefelholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_stairs", "copper_inferno:brimwood_stairs", "charwood/brimwood_stairs",
				new String[] {"copper_inferno:brimwood", "", "", "copper_inferno:brimwood", "copper_inferno:brimwood", "", "copper_inferno:brimwood", "copper_inferno:brimwood", "copper_inferno:brimwood"},
				"copper_inferno:brimwood_stairs", 4, "Craft 4x Brimwood Stairs at a crafting table.", "Stellt 4x Schwefelholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_wall", "copper_inferno:brimwood_wall", "charwood/brimwood_wall",
				new String[] {"copper_inferno:brimwood", "copper_inferno:brimwood", "copper_inferno:brimwood", "copper_inferno:brimwood", "copper_inferno:brimwood", "copper_inferno:brimwood", "", "", ""},
				"copper_inferno:brimwood_wall", 6, "Craft 6x Brimwood Wall at a crafting table.", "Stellt 6x Schwefelholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_brimwood_slab", "copper_inferno:polished_brimwood_slab", "charwood/polished_brimwood_slab",
				new String[] {"copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood", "", "", "", "", "", ""},
				"copper_inferno:polished_brimwood_slab", 6, "Craft 6x Polished Brimwood Slab at a crafting table.", "Stellt 6x Polierte Schwefelholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_brimwood_stairs", "copper_inferno:polished_brimwood_stairs", "charwood/polished_brimwood_stairs",
				new String[] {"copper_inferno:polished_brimwood", "", "", "copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood", "", "copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood"},
				"copper_inferno:polished_brimwood_stairs", 4, "Craft 4x Polished Brimwood Stairs at a crafting table.", "Stellt 4x Polierte Schwefelholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_brimwood_wall", "copper_inferno:polished_brimwood_wall", "charwood/polished_brimwood_wall",
				new String[] {"copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood", "copper_inferno:polished_brimwood", "", "", ""},
				"copper_inferno:polished_brimwood_wall", 6, "Craft 6x Polished Brimwood Wall at a crafting table.", "Stellt 6x Polierte Schwefelholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_brick_slab", "copper_inferno:brimwood_brick_slab", "charwood/brimwood_brick_slab",
				new String[] {"copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "", "", "", "", "", ""},
				"copper_inferno:brimwood_brick_slab", 6, "Craft 6x Brimwood Brick Slab at a crafting table.", "Stellt 6x Schwefelholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_brick_stairs", "copper_inferno:brimwood_brick_stairs", "charwood/brimwood_brick_stairs",
				new String[] {"copper_inferno:brimwood_bricks", "", "", "copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "", "copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks"},
				"copper_inferno:brimwood_brick_stairs", 4, "Craft 4x Brimwood Brick Stairs at a crafting table.", "Stellt 4x Schwefelholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_brick_wall", "copper_inferno:brimwood_brick_wall", "charwood/brimwood_brick_wall",
				new String[] {"copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "copper_inferno:brimwood_bricks", "", "", ""},
				"copper_inferno:brimwood_brick_wall", 6, "Craft 6x Brimwood Brick Wall at a crafting table.", "Stellt 6x Schwefelholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_brimwood_bricks", "copper_inferno:cracked_brimwood_bricks", "charwood/cracked_brimwood_bricks",
				new String[] {"", "", "", "", "copper_inferno:brimwood_bricks", "", "", "", ""},
				"copper_inferno:cracked_brimwood_bricks", 1, "Smelting Brimwood Bricks in a furnace yields Cracked Brimwood Bricks.", "Schwefelholzziegel im Ofen gebrannt ergibt Rissige Schwefelholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_brimwood_bricks", "copper_inferno:chiseled_brimwood_bricks", "charwood/chiseled_brimwood_bricks",
				new String[] {"copper_inferno:brimwood_brick_slab", "", "", "copper_inferno:brimwood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_brimwood_bricks", 1, "Craft 1x Chiseled Brimwood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schwefelholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_pillar", "copper_inferno:brimwood_pillar", "charwood/brimwood_pillar",
				new String[] {"copper_inferno:brimwood_bricks", "", "", "copper_inferno:brimwood_bricks", "", "", "", "", ""},
				"copper_inferno:brimwood_pillar", 2, "Craft 2x Brimwood Pillar at a crafting table.", "Stellt 2x Schwefelholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_slab_from_brimwood_stonecutting", "copper_inferno:brimwood_slab", "charwood/brimwood_slab_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_slab", 2, "Stonecutting: cut 2x Brimwood Slab from Brimwood.", "Steins\u00e4ge: 2x Schwefelholzstufe aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_stairs_from_brimwood_stonecutting", "copper_inferno:brimwood_stairs", "charwood/brimwood_stairs_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_stairs", 1, "Stonecutting: cut 1x Brimwood Stairs from Brimwood.", "Steins\u00e4ge: 1x Schwefelholztreppe aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_wall_from_brimwood_stonecutting", "copper_inferno:brimwood_wall", "charwood/brimwood_wall_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_wall", 1, "Stonecutting: cut 1x Brimwood Wall from Brimwood.", "Steins\u00e4ge: 1x Schwefelholzmauer aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_brimwood_from_brimwood_stonecutting", "copper_inferno:polished_brimwood", "charwood/polished_brimwood_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:polished_brimwood", 1, "Stonecutting: cut 1x Polished Brimwood from Brimwood.", "Steins\u00e4ge: 1x Poliertes Schwefelholz aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_brimwood_slab_from_brimwood_stonecutting", "copper_inferno:polished_brimwood_slab", "charwood/polished_brimwood_slab_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:polished_brimwood_slab", 2, "Stonecutting: cut 2x Polished Brimwood Slab from Brimwood.", "Steins\u00e4ge: 2x Polierte Schwefelholzstufe aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_brimwood_stairs_from_brimwood_stonecutting", "copper_inferno:polished_brimwood_stairs", "charwood/polished_brimwood_stairs_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:polished_brimwood_stairs", 1, "Stonecutting: cut 1x Polished Brimwood Stairs from Brimwood.", "Steins\u00e4ge: 1x Polierte Schwefelholztreppe aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_brimwood_wall_from_brimwood_stonecutting", "copper_inferno:polished_brimwood_wall", "charwood/polished_brimwood_wall_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:polished_brimwood_wall", 1, "Stonecutting: cut 1x Polished Brimwood Wall from Brimwood.", "Steins\u00e4ge: 1x Polierte Schwefelholzmauer aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_bricks_from_brimwood_stonecutting", "copper_inferno:brimwood_bricks", "charwood/brimwood_bricks_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_bricks", 1, "Stonecutting: cut 1x Brimwood Bricks from Brimwood.", "Steins\u00e4ge: 1x Schwefelholzziegel aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_brick_slab_from_brimwood_stonecutting", "copper_inferno:brimwood_brick_slab", "charwood/brimwood_brick_slab_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_brick_slab", 2, "Stonecutting: cut 2x Brimwood Brick Slab from Brimwood.", "Steins\u00e4ge: 2x Schwefelholzziegelstufe aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_brick_stairs_from_brimwood_stonecutting", "copper_inferno:brimwood_brick_stairs", "charwood/brimwood_brick_stairs_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_brick_stairs", 1, "Stonecutting: cut 1x Brimwood Brick Stairs from Brimwood.", "Steins\u00e4ge: 1x Schwefelholzziegeltreppe aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_brick_wall_from_brimwood_stonecutting", "copper_inferno:brimwood_brick_wall", "charwood/brimwood_brick_wall_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_brick_wall", 1, "Stonecutting: cut 1x Brimwood Brick Wall from Brimwood.", "Steins\u00e4ge: 1x Schwefelholzziegelmauer aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_tiles_from_brimwood_stonecutting", "copper_inferno:brimwood_tiles", "charwood/brimwood_tiles_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_tiles", 1, "Stonecutting: cut 1x Brimwood Tiles from Brimwood.", "Steins\u00e4ge: 1x Schwefelholzfliesen aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_brimwood_bricks_from_brimwood_stonecutting", "copper_inferno:chiseled_brimwood_bricks", "charwood/chiseled_brimwood_bricks_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:chiseled_brimwood_bricks", 1, "Stonecutting: cut 1x Chiseled Brimwood Bricks from Brimwood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schwefelholzziegel aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/brimwood_pillar_from_brimwood_stonecutting", "copper_inferno:brimwood_pillar", "charwood/brimwood_pillar_from_brimwood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:brimwood", "", "", "", ""},
				"copper_inferno:brimwood_pillar", 1, "Stonecutting: cut 1x Brimwood Pillar from Brimwood.", "Steins\u00e4ge: 1x Schwefelholzs\u00e4ule aus Schwefelholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood", "copper_inferno:flarewood", "charwood/flarewood",
				new String[] {"minecraft:fire_charge", "", "minecraft:fire_charge", "", "copper_inferno:brimwood", "", "minecraft:fire_charge", "", "minecraft:fire_charge"},
				"copper_inferno:flarewood", 4, "Craft 4x Flarewood at a crafting table.", "Stellt 4x Flackerholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_bricks", "copper_inferno:flarewood_bricks", "charwood/flarewood_bricks",
				new String[] {"copper_inferno:flarewood", "copper_inferno:flarewood", "", "copper_inferno:flarewood", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_bricks", 4, "Craft 4x Flarewood Bricks at a crafting table.", "Stellt 4x Flackerholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_tiles", "copper_inferno:flarewood_tiles", "charwood/flarewood_tiles",
				new String[] {"copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "", "copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "", "", "", ""},
				"copper_inferno:flarewood_tiles", 4, "Craft 4x Flarewood Tiles at a crafting table.", "Stellt 4x Flackerholzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_flarewood", "copper_inferno:polished_flarewood", "charwood/polished_flarewood",
				new String[] {"copper_inferno:flarewood_tiles", "copper_inferno:flarewood_tiles", "", "copper_inferno:flarewood_tiles", "copper_inferno:flarewood_tiles", "", "", "", ""},
				"copper_inferno:polished_flarewood", 4, "Craft 4x Polished Flarewood at a crafting table.", "Stellt 4x Poliertes Flackerholz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_slab", "copper_inferno:flarewood_slab", "charwood/flarewood_slab",
				new String[] {"copper_inferno:flarewood", "copper_inferno:flarewood", "copper_inferno:flarewood", "", "", "", "", "", ""},
				"copper_inferno:flarewood_slab", 6, "Craft 6x Flarewood Slab at a crafting table.", "Stellt 6x Flackerholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_stairs", "copper_inferno:flarewood_stairs", "charwood/flarewood_stairs",
				new String[] {"copper_inferno:flarewood", "", "", "copper_inferno:flarewood", "copper_inferno:flarewood", "", "copper_inferno:flarewood", "copper_inferno:flarewood", "copper_inferno:flarewood"},
				"copper_inferno:flarewood_stairs", 4, "Craft 4x Flarewood Stairs at a crafting table.", "Stellt 4x Flackerholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_wall", "copper_inferno:flarewood_wall", "charwood/flarewood_wall",
				new String[] {"copper_inferno:flarewood", "copper_inferno:flarewood", "copper_inferno:flarewood", "copper_inferno:flarewood", "copper_inferno:flarewood", "copper_inferno:flarewood", "", "", ""},
				"copper_inferno:flarewood_wall", 6, "Craft 6x Flarewood Wall at a crafting table.", "Stellt 6x Flackerholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_flarewood_slab", "copper_inferno:polished_flarewood_slab", "charwood/polished_flarewood_slab",
				new String[] {"copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood", "", "", "", "", "", ""},
				"copper_inferno:polished_flarewood_slab", 6, "Craft 6x Polished Flarewood Slab at a crafting table.", "Stellt 6x Polierte Flackerholzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_flarewood_stairs", "copper_inferno:polished_flarewood_stairs", "charwood/polished_flarewood_stairs",
				new String[] {"copper_inferno:polished_flarewood", "", "", "copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood", "", "copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood"},
				"copper_inferno:polished_flarewood_stairs", 4, "Craft 4x Polished Flarewood Stairs at a crafting table.", "Stellt 4x Polierte Flackerholztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_flarewood_wall", "copper_inferno:polished_flarewood_wall", "charwood/polished_flarewood_wall",
				new String[] {"copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood", "copper_inferno:polished_flarewood", "", "", ""},
				"copper_inferno:polished_flarewood_wall", 6, "Craft 6x Polished Flarewood Wall at a crafting table.", "Stellt 6x Polierte Flackerholzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_brick_slab", "copper_inferno:flarewood_brick_slab", "charwood/flarewood_brick_slab",
				new String[] {"copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "", "", "", "", "", ""},
				"copper_inferno:flarewood_brick_slab", 6, "Craft 6x Flarewood Brick Slab at a crafting table.", "Stellt 6x Flackerholzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_brick_stairs", "copper_inferno:flarewood_brick_stairs", "charwood/flarewood_brick_stairs",
				new String[] {"copper_inferno:flarewood_bricks", "", "", "copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "", "copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks"},
				"copper_inferno:flarewood_brick_stairs", 4, "Craft 4x Flarewood Brick Stairs at a crafting table.", "Stellt 4x Flackerholzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_brick_wall", "copper_inferno:flarewood_brick_wall", "charwood/flarewood_brick_wall",
				new String[] {"copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "copper_inferno:flarewood_bricks", "", "", ""},
				"copper_inferno:flarewood_brick_wall", 6, "Craft 6x Flarewood Brick Wall at a crafting table.", "Stellt 6x Flackerholzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/cracked_flarewood_bricks", "copper_inferno:cracked_flarewood_bricks", "charwood/cracked_flarewood_bricks",
				new String[] {"", "", "", "", "copper_inferno:flarewood_bricks", "", "", "", ""},
				"copper_inferno:cracked_flarewood_bricks", 1, "Smelting Flarewood Bricks in a furnace yields Cracked Flarewood Bricks.", "Flackerholzziegel im Ofen gebrannt ergibt Rissige Flackerholzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_flarewood_bricks", "copper_inferno:chiseled_flarewood_bricks", "charwood/chiseled_flarewood_bricks",
				new String[] {"copper_inferno:flarewood_brick_slab", "", "", "copper_inferno:flarewood_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_flarewood_bricks", 1, "Craft 1x Chiseled Flarewood Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Flackerholzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_pillar", "copper_inferno:flarewood_pillar", "charwood/flarewood_pillar",
				new String[] {"copper_inferno:flarewood_bricks", "", "", "copper_inferno:flarewood_bricks", "", "", "", "", ""},
				"copper_inferno:flarewood_pillar", 2, "Craft 2x Flarewood Pillar at a crafting table.", "Stellt 2x Flackerholzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_slab_from_flarewood_stonecutting", "copper_inferno:flarewood_slab", "charwood/flarewood_slab_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_slab", 2, "Stonecutting: cut 2x Flarewood Slab from Flarewood.", "Steins\u00e4ge: 2x Flackerholzstufe aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_stairs_from_flarewood_stonecutting", "copper_inferno:flarewood_stairs", "charwood/flarewood_stairs_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_stairs", 1, "Stonecutting: cut 1x Flarewood Stairs from Flarewood.", "Steins\u00e4ge: 1x Flackerholztreppe aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_wall_from_flarewood_stonecutting", "copper_inferno:flarewood_wall", "charwood/flarewood_wall_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_wall", 1, "Stonecutting: cut 1x Flarewood Wall from Flarewood.", "Steins\u00e4ge: 1x Flackerholzmauer aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_flarewood_from_flarewood_stonecutting", "copper_inferno:polished_flarewood", "charwood/polished_flarewood_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:polished_flarewood", 1, "Stonecutting: cut 1x Polished Flarewood from Flarewood.", "Steins\u00e4ge: 1x Poliertes Flackerholz aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_flarewood_slab_from_flarewood_stonecutting", "copper_inferno:polished_flarewood_slab", "charwood/polished_flarewood_slab_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:polished_flarewood_slab", 2, "Stonecutting: cut 2x Polished Flarewood Slab from Flarewood.", "Steins\u00e4ge: 2x Polierte Flackerholzstufe aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_flarewood_stairs_from_flarewood_stonecutting", "copper_inferno:polished_flarewood_stairs", "charwood/polished_flarewood_stairs_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:polished_flarewood_stairs", 1, "Stonecutting: cut 1x Polished Flarewood Stairs from Flarewood.", "Steins\u00e4ge: 1x Polierte Flackerholztreppe aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/polished_flarewood_wall_from_flarewood_stonecutting", "copper_inferno:polished_flarewood_wall", "charwood/polished_flarewood_wall_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:polished_flarewood_wall", 1, "Stonecutting: cut 1x Polished Flarewood Wall from Flarewood.", "Steins\u00e4ge: 1x Polierte Flackerholzmauer aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_bricks_from_flarewood_stonecutting", "copper_inferno:flarewood_bricks", "charwood/flarewood_bricks_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_bricks", 1, "Stonecutting: cut 1x Flarewood Bricks from Flarewood.", "Steins\u00e4ge: 1x Flackerholzziegel aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_brick_slab_from_flarewood_stonecutting", "copper_inferno:flarewood_brick_slab", "charwood/flarewood_brick_slab_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_brick_slab", 2, "Stonecutting: cut 2x Flarewood Brick Slab from Flarewood.", "Steins\u00e4ge: 2x Flackerholzziegelstufe aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_brick_stairs_from_flarewood_stonecutting", "copper_inferno:flarewood_brick_stairs", "charwood/flarewood_brick_stairs_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_brick_stairs", 1, "Stonecutting: cut 1x Flarewood Brick Stairs from Flarewood.", "Steins\u00e4ge: 1x Flackerholzziegeltreppe aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_brick_wall_from_flarewood_stonecutting", "copper_inferno:flarewood_brick_wall", "charwood/flarewood_brick_wall_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_brick_wall", 1, "Stonecutting: cut 1x Flarewood Brick Wall from Flarewood.", "Steins\u00e4ge: 1x Flackerholzziegelmauer aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_tiles_from_flarewood_stonecutting", "copper_inferno:flarewood_tiles", "charwood/flarewood_tiles_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_tiles", 1, "Stonecutting: cut 1x Flarewood Tiles from Flarewood.", "Steins\u00e4ge: 1x Flackerholzfliesen aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/chiseled_flarewood_bricks_from_flarewood_stonecutting", "copper_inferno:chiseled_flarewood_bricks", "charwood/chiseled_flarewood_bricks_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:chiseled_flarewood_bricks", 1, "Stonecutting: cut 1x Chiseled Flarewood Bricks from Flarewood.", "Steins\u00e4ge: 1x Gemei\u00dfelte Flackerholzziegel aus Flackerholz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "charwood/flarewood_pillar_from_flarewood_stonecutting", "copper_inferno:flarewood_pillar", "charwood/flarewood_pillar_from_flarewood_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarewood", "", "", "", ""},
				"copper_inferno:flarewood_pillar", 1, "Stonecutting: cut 1x Flarewood Pillar from Flarewood.", "Steins\u00e4ge: 1x Flackerholzs\u00e4ule aus Flackerholz schneiden."));
	}
}
