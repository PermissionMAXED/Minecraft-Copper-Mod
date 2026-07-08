package net.sonic0810.copperinferno.feature.pyrestone;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Pyrestone block set: one "blocks" overview per material
 * plus one recipe page for every JSON under {@code data/copper_inferno/recipe/pyrestone/}
 * (crafting, smelting and stonecutting). Entry texts and grids mirror the recipe JSONs
 * emitted by {@code devtools/gen/pyrestone_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep them
 * inline.
 */
final class PyrestoneHandbook {
	private PyrestoneHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_pyrestone", "copper_inferno:pyrestone", null,
				null,
				null, 0, "The Pyrestone set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Pyrostein-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_emberstone", "copper_inferno:emberstone", null,
				null,
				null, 0, "The Emberstone set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Glutstein-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_sootstone", "copper_inferno:sootstone", null,
				null,
				null, 0, "The Sootstone set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Ru\u00dfstein-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_charflint", "copper_inferno:charflint", null,
				null,
				null, 0, "The Charflint set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Schwarzflint-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_kindlerock", "copper_inferno:kindlerock", null,
				null,
				null, 0, "The Kindlerock set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Z\u00fcndelstein-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_cindershale", "copper_inferno:cindershale", null,
				null,
				null, 0, "The Cindershale set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Aschenschiefer-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_coalspar", "copper_inferno:coalspar", null,
				null,
				null, 0, "The Coalspar set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Kohlenspat-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_tindergrit", "copper_inferno:tindergrit", null,
				null,
				null, 0, "The Tindergrit set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Zundergrus-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_flarebasalt", "copper_inferno:flarebasalt", null,
				null,
				null, 0, "The Flarebasalt set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Flackerbasalt-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_magmite", "copper_inferno:magmite", null,
				null,
				null, 0, "The Magmite set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Magmit-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_fumarole_rock", "copper_inferno:fumarole_rock", null,
				null,
				null, 0, "The Fumarole Rock set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Fumarolenstein-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_pyroclast_stone", "copper_inferno:pyroclast_stone", null,
				null,
				null, 0, "The Pyroclast Stone set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Pyroklaststein-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_vitrified_ash", "copper_inferno:vitrified_ash", null,
				null,
				null, 0, "The Vitrified Ash set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Glasasche-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/set_blazewrought_stone", "copper_inferno:blazewrought_stone", null,
				null,
				null, 0, "The Blazewrought Stone set for Inferno builds: rough, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Flammschmiedestein-Set f\u00fcr Inferno-Bauten: raue, polierte und Ziegel-Familien (Block, Treppe, Stufe, Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone", "copper_inferno:pyrestone", "pyrestone/pyrestone",
				new String[] {"copper_inferno:cinderstone", "", "copper_inferno:cinderstone", "", "copper_inferno:ember_dust", "", "copper_inferno:cinderstone", "", "copper_inferno:cinderstone"},
				"copper_inferno:pyrestone", 4, "Craft 4x Pyrestone at a crafting table.", "Stellt 4x Pyrostein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_bricks", "copper_inferno:pyrestone_bricks", "pyrestone/pyrestone_bricks",
				new String[] {"copper_inferno:pyrestone", "copper_inferno:pyrestone", "", "copper_inferno:pyrestone", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_bricks", 4, "Craft 4x Pyrestone Bricks at a crafting table.", "Stellt 4x Pyrosteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_tiles", "copper_inferno:pyrestone_tiles", "pyrestone/pyrestone_tiles",
				new String[] {"copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "", "copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "", "", "", ""},
				"copper_inferno:pyrestone_tiles", 4, "Craft 4x Pyrestone Tiles at a crafting table.", "Stellt 4x Pyrosteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyrestone", "copper_inferno:polished_pyrestone", "pyrestone/polished_pyrestone",
				new String[] {"copper_inferno:pyrestone_tiles", "copper_inferno:pyrestone_tiles", "", "copper_inferno:pyrestone_tiles", "copper_inferno:pyrestone_tiles", "", "", "", ""},
				"copper_inferno:polished_pyrestone", 4, "Craft 4x Polished Pyrestone at a crafting table.", "Stellt 4x Polierten Pyrostein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_slab", "copper_inferno:pyrestone_slab", "pyrestone/pyrestone_slab",
				new String[] {"copper_inferno:pyrestone", "copper_inferno:pyrestone", "copper_inferno:pyrestone", "", "", "", "", "", ""},
				"copper_inferno:pyrestone_slab", 6, "Craft 6x Pyrestone Slab at a crafting table.", "Stellt 6x Pyrosteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_stairs", "copper_inferno:pyrestone_stairs", "pyrestone/pyrestone_stairs",
				new String[] {"copper_inferno:pyrestone", "", "", "copper_inferno:pyrestone", "copper_inferno:pyrestone", "", "copper_inferno:pyrestone", "copper_inferno:pyrestone", "copper_inferno:pyrestone"},
				"copper_inferno:pyrestone_stairs", 4, "Craft 4x Pyrestone Stairs at a crafting table.", "Stellt 4x Pyrosteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_wall", "copper_inferno:pyrestone_wall", "pyrestone/pyrestone_wall",
				new String[] {"copper_inferno:pyrestone", "copper_inferno:pyrestone", "copper_inferno:pyrestone", "copper_inferno:pyrestone", "copper_inferno:pyrestone", "copper_inferno:pyrestone", "", "", ""},
				"copper_inferno:pyrestone_wall", 6, "Craft 6x Pyrestone Wall at a crafting table.", "Stellt 6x Pyrosteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyrestone_slab", "copper_inferno:polished_pyrestone_slab", "pyrestone/polished_pyrestone_slab",
				new String[] {"copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone", "", "", "", "", "", ""},
				"copper_inferno:polished_pyrestone_slab", 6, "Craft 6x Polished Pyrestone Slab at a crafting table.", "Stellt 6x Polierte Pyrosteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyrestone_stairs", "copper_inferno:polished_pyrestone_stairs", "pyrestone/polished_pyrestone_stairs",
				new String[] {"copper_inferno:polished_pyrestone", "", "", "copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone", "", "copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone"},
				"copper_inferno:polished_pyrestone_stairs", 4, "Craft 4x Polished Pyrestone Stairs at a crafting table.", "Stellt 4x Polierte Pyrosteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyrestone_wall", "copper_inferno:polished_pyrestone_wall", "pyrestone/polished_pyrestone_wall",
				new String[] {"copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone", "copper_inferno:polished_pyrestone", "", "", ""},
				"copper_inferno:polished_pyrestone_wall", 6, "Craft 6x Polished Pyrestone Wall at a crafting table.", "Stellt 6x Polierte Pyrosteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_brick_slab", "copper_inferno:pyrestone_brick_slab", "pyrestone/pyrestone_brick_slab",
				new String[] {"copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "", "", "", "", "", ""},
				"copper_inferno:pyrestone_brick_slab", 6, "Craft 6x Pyrestone Brick Slab at a crafting table.", "Stellt 6x Pyrosteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_brick_stairs", "copper_inferno:pyrestone_brick_stairs", "pyrestone/pyrestone_brick_stairs",
				new String[] {"copper_inferno:pyrestone_bricks", "", "", "copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "", "copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks"},
				"copper_inferno:pyrestone_brick_stairs", 4, "Craft 4x Pyrestone Brick Stairs at a crafting table.", "Stellt 4x Pyrosteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_brick_wall", "copper_inferno:pyrestone_brick_wall", "pyrestone/pyrestone_brick_wall",
				new String[] {"copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "copper_inferno:pyrestone_bricks", "", "", ""},
				"copper_inferno:pyrestone_brick_wall", 6, "Craft 6x Pyrestone Brick Wall at a crafting table.", "Stellt 6x Pyrosteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_pyrestone_bricks", "copper_inferno:cracked_pyrestone_bricks", "pyrestone/cracked_pyrestone_bricks",
				new String[] {"", "", "", "", "copper_inferno:pyrestone_bricks", "", "", "", ""},
				"copper_inferno:cracked_pyrestone_bricks", 1, "Smelting Pyrestone Bricks in a furnace yields Cracked Pyrestone Bricks.", "Pyrosteinziegel im Ofen gebrannt ergibt Rissige Pyrosteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_pyrestone_bricks", "copper_inferno:chiseled_pyrestone_bricks", "pyrestone/chiseled_pyrestone_bricks",
				new String[] {"copper_inferno:pyrestone_brick_slab", "", "", "copper_inferno:pyrestone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_pyrestone_bricks", 1, "Craft 1x Chiseled Pyrestone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Pyrosteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_pillar", "copper_inferno:pyrestone_pillar", "pyrestone/pyrestone_pillar",
				new String[] {"copper_inferno:pyrestone_bricks", "", "", "copper_inferno:pyrestone_bricks", "", "", "", "", ""},
				"copper_inferno:pyrestone_pillar", 2, "Craft 2x Pyrestone Pillar at a crafting table.", "Stellt 2x Pyrosteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_slab_from_pyrestone_stonecutting", "copper_inferno:pyrestone_slab", "pyrestone/pyrestone_slab_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_slab", 2, "Stonecutting: cut 2x Pyrestone Slab from Pyrestone.", "Steins\u00e4ge: 2x Pyrosteinstufe aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_stairs_from_pyrestone_stonecutting", "copper_inferno:pyrestone_stairs", "pyrestone/pyrestone_stairs_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_stairs", 1, "Stonecutting: cut 1x Pyrestone Stairs from Pyrestone.", "Steins\u00e4ge: 1x Pyrosteintreppe aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_wall_from_pyrestone_stonecutting", "copper_inferno:pyrestone_wall", "pyrestone/pyrestone_wall_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_wall", 1, "Stonecutting: cut 1x Pyrestone Wall from Pyrestone.", "Steins\u00e4ge: 1x Pyrosteinmauer aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyrestone_from_pyrestone_stonecutting", "copper_inferno:polished_pyrestone", "pyrestone/polished_pyrestone_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:polished_pyrestone", 1, "Stonecutting: cut 1x Polished Pyrestone from Pyrestone.", "Steins\u00e4ge: 1x Polierten Pyrostein aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyrestone_slab_from_pyrestone_stonecutting", "copper_inferno:polished_pyrestone_slab", "pyrestone/polished_pyrestone_slab_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:polished_pyrestone_slab", 2, "Stonecutting: cut 2x Polished Pyrestone Slab from Pyrestone.", "Steins\u00e4ge: 2x Polierte Pyrosteinstufe aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyrestone_stairs_from_pyrestone_stonecutting", "copper_inferno:polished_pyrestone_stairs", "pyrestone/polished_pyrestone_stairs_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:polished_pyrestone_stairs", 1, "Stonecutting: cut 1x Polished Pyrestone Stairs from Pyrestone.", "Steins\u00e4ge: 1x Polierte Pyrosteintreppe aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyrestone_wall_from_pyrestone_stonecutting", "copper_inferno:polished_pyrestone_wall", "pyrestone/polished_pyrestone_wall_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:polished_pyrestone_wall", 1, "Stonecutting: cut 1x Polished Pyrestone Wall from Pyrestone.", "Steins\u00e4ge: 1x Polierte Pyrosteinmauer aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_bricks_from_pyrestone_stonecutting", "copper_inferno:pyrestone_bricks", "pyrestone/pyrestone_bricks_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_bricks", 1, "Stonecutting: cut 1x Pyrestone Bricks from Pyrestone.", "Steins\u00e4ge: 1x Pyrosteinziegel aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_brick_slab_from_pyrestone_stonecutting", "copper_inferno:pyrestone_brick_slab", "pyrestone/pyrestone_brick_slab_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_brick_slab", 2, "Stonecutting: cut 2x Pyrestone Brick Slab from Pyrestone.", "Steins\u00e4ge: 2x Pyrosteinziegelstufe aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_brick_stairs_from_pyrestone_stonecutting", "copper_inferno:pyrestone_brick_stairs", "pyrestone/pyrestone_brick_stairs_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_brick_stairs", 1, "Stonecutting: cut 1x Pyrestone Brick Stairs from Pyrestone.", "Steins\u00e4ge: 1x Pyrosteinziegeltreppe aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_brick_wall_from_pyrestone_stonecutting", "copper_inferno:pyrestone_brick_wall", "pyrestone/pyrestone_brick_wall_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_brick_wall", 1, "Stonecutting: cut 1x Pyrestone Brick Wall from Pyrestone.", "Steins\u00e4ge: 1x Pyrosteinziegelmauer aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_tiles_from_pyrestone_stonecutting", "copper_inferno:pyrestone_tiles", "pyrestone/pyrestone_tiles_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_tiles", 1, "Stonecutting: cut 1x Pyrestone Tiles from Pyrestone.", "Steins\u00e4ge: 1x Pyrosteinfliesen aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_pyrestone_bricks_from_pyrestone_stonecutting", "copper_inferno:chiseled_pyrestone_bricks", "pyrestone/chiseled_pyrestone_bricks_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:chiseled_pyrestone_bricks", 1, "Stonecutting: cut 1x Chiseled Pyrestone Bricks from Pyrestone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Pyrosteinziegel aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyrestone_pillar_from_pyrestone_stonecutting", "copper_inferno:pyrestone_pillar", "pyrestone/pyrestone_pillar_from_pyrestone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrestone", "", "", "", ""},
				"copper_inferno:pyrestone_pillar", 1, "Stonecutting: cut 1x Pyrestone Pillar from Pyrestone.", "Steins\u00e4ge: 1x Pyrosteins\u00e4ule aus Pyrostein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone", "copper_inferno:emberstone", "pyrestone/emberstone",
				new String[] {"minecraft:netherrack", "", "minecraft:netherrack", "", "copper_inferno:pyrestone", "", "minecraft:netherrack", "", "minecraft:netherrack"},
				"copper_inferno:emberstone", 4, "Craft 4x Emberstone at a crafting table.", "Stellt 4x Glutstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_bricks", "copper_inferno:emberstone_bricks", "pyrestone/emberstone_bricks",
				new String[] {"copper_inferno:emberstone", "copper_inferno:emberstone", "", "copper_inferno:emberstone", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_bricks", 4, "Craft 4x Emberstone Bricks at a crafting table.", "Stellt 4x Glutsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_tiles", "copper_inferno:emberstone_tiles", "pyrestone/emberstone_tiles",
				new String[] {"copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "", "copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "", "", "", ""},
				"copper_inferno:emberstone_tiles", 4, "Craft 4x Emberstone Tiles at a crafting table.", "Stellt 4x Glutsteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_emberstone", "copper_inferno:polished_emberstone", "pyrestone/polished_emberstone",
				new String[] {"copper_inferno:emberstone_tiles", "copper_inferno:emberstone_tiles", "", "copper_inferno:emberstone_tiles", "copper_inferno:emberstone_tiles", "", "", "", ""},
				"copper_inferno:polished_emberstone", 4, "Craft 4x Polished Emberstone at a crafting table.", "Stellt 4x Polierten Glutstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_slab", "copper_inferno:emberstone_slab", "pyrestone/emberstone_slab",
				new String[] {"copper_inferno:emberstone", "copper_inferno:emberstone", "copper_inferno:emberstone", "", "", "", "", "", ""},
				"copper_inferno:emberstone_slab", 6, "Craft 6x Emberstone Slab at a crafting table.", "Stellt 6x Glutsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_stairs", "copper_inferno:emberstone_stairs", "pyrestone/emberstone_stairs",
				new String[] {"copper_inferno:emberstone", "", "", "copper_inferno:emberstone", "copper_inferno:emberstone", "", "copper_inferno:emberstone", "copper_inferno:emberstone", "copper_inferno:emberstone"},
				"copper_inferno:emberstone_stairs", 4, "Craft 4x Emberstone Stairs at a crafting table.", "Stellt 4x Glutsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_wall", "copper_inferno:emberstone_wall", "pyrestone/emberstone_wall",
				new String[] {"copper_inferno:emberstone", "copper_inferno:emberstone", "copper_inferno:emberstone", "copper_inferno:emberstone", "copper_inferno:emberstone", "copper_inferno:emberstone", "", "", ""},
				"copper_inferno:emberstone_wall", 6, "Craft 6x Emberstone Wall at a crafting table.", "Stellt 6x Glutsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_emberstone_slab", "copper_inferno:polished_emberstone_slab", "pyrestone/polished_emberstone_slab",
				new String[] {"copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone", "", "", "", "", "", ""},
				"copper_inferno:polished_emberstone_slab", 6, "Craft 6x Polished Emberstone Slab at a crafting table.", "Stellt 6x Polierte Glutsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_emberstone_stairs", "copper_inferno:polished_emberstone_stairs", "pyrestone/polished_emberstone_stairs",
				new String[] {"copper_inferno:polished_emberstone", "", "", "copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone", "", "copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone"},
				"copper_inferno:polished_emberstone_stairs", 4, "Craft 4x Polished Emberstone Stairs at a crafting table.", "Stellt 4x Polierte Glutsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_emberstone_wall", "copper_inferno:polished_emberstone_wall", "pyrestone/polished_emberstone_wall",
				new String[] {"copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone", "copper_inferno:polished_emberstone", "", "", ""},
				"copper_inferno:polished_emberstone_wall", 6, "Craft 6x Polished Emberstone Wall at a crafting table.", "Stellt 6x Polierte Glutsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_brick_slab", "copper_inferno:emberstone_brick_slab", "pyrestone/emberstone_brick_slab",
				new String[] {"copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "", "", "", "", "", ""},
				"copper_inferno:emberstone_brick_slab", 6, "Craft 6x Emberstone Brick Slab at a crafting table.", "Stellt 6x Glutsteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_brick_stairs", "copper_inferno:emberstone_brick_stairs", "pyrestone/emberstone_brick_stairs",
				new String[] {"copper_inferno:emberstone_bricks", "", "", "copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "", "copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks"},
				"copper_inferno:emberstone_brick_stairs", 4, "Craft 4x Emberstone Brick Stairs at a crafting table.", "Stellt 4x Glutsteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_brick_wall", "copper_inferno:emberstone_brick_wall", "pyrestone/emberstone_brick_wall",
				new String[] {"copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "copper_inferno:emberstone_bricks", "", "", ""},
				"copper_inferno:emberstone_brick_wall", 6, "Craft 6x Emberstone Brick Wall at a crafting table.", "Stellt 6x Glutsteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_emberstone_bricks", "copper_inferno:cracked_emberstone_bricks", "pyrestone/cracked_emberstone_bricks",
				new String[] {"", "", "", "", "copper_inferno:emberstone_bricks", "", "", "", ""},
				"copper_inferno:cracked_emberstone_bricks", 1, "Smelting Emberstone Bricks in a furnace yields Cracked Emberstone Bricks.", "Glutsteinziegel im Ofen gebrannt ergibt Rissige Glutsteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_emberstone_bricks", "copper_inferno:chiseled_emberstone_bricks", "pyrestone/chiseled_emberstone_bricks",
				new String[] {"copper_inferno:emberstone_brick_slab", "", "", "copper_inferno:emberstone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_emberstone_bricks", 1, "Craft 1x Chiseled Emberstone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glutsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_pillar", "copper_inferno:emberstone_pillar", "pyrestone/emberstone_pillar",
				new String[] {"copper_inferno:emberstone_bricks", "", "", "copper_inferno:emberstone_bricks", "", "", "", "", ""},
				"copper_inferno:emberstone_pillar", 2, "Craft 2x Emberstone Pillar at a crafting table.", "Stellt 2x Glutsteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_slab_from_emberstone_stonecutting", "copper_inferno:emberstone_slab", "pyrestone/emberstone_slab_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_slab", 2, "Stonecutting: cut 2x Emberstone Slab from Emberstone.", "Steins\u00e4ge: 2x Glutsteinstufe aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_stairs_from_emberstone_stonecutting", "copper_inferno:emberstone_stairs", "pyrestone/emberstone_stairs_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_stairs", 1, "Stonecutting: cut 1x Emberstone Stairs from Emberstone.", "Steins\u00e4ge: 1x Glutsteintreppe aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_wall_from_emberstone_stonecutting", "copper_inferno:emberstone_wall", "pyrestone/emberstone_wall_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_wall", 1, "Stonecutting: cut 1x Emberstone Wall from Emberstone.", "Steins\u00e4ge: 1x Glutsteinmauer aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_emberstone_from_emberstone_stonecutting", "copper_inferno:polished_emberstone", "pyrestone/polished_emberstone_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:polished_emberstone", 1, "Stonecutting: cut 1x Polished Emberstone from Emberstone.", "Steins\u00e4ge: 1x Polierten Glutstein aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_emberstone_slab_from_emberstone_stonecutting", "copper_inferno:polished_emberstone_slab", "pyrestone/polished_emberstone_slab_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:polished_emberstone_slab", 2, "Stonecutting: cut 2x Polished Emberstone Slab from Emberstone.", "Steins\u00e4ge: 2x Polierte Glutsteinstufe aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_emberstone_stairs_from_emberstone_stonecutting", "copper_inferno:polished_emberstone_stairs", "pyrestone/polished_emberstone_stairs_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:polished_emberstone_stairs", 1, "Stonecutting: cut 1x Polished Emberstone Stairs from Emberstone.", "Steins\u00e4ge: 1x Polierte Glutsteintreppe aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_emberstone_wall_from_emberstone_stonecutting", "copper_inferno:polished_emberstone_wall", "pyrestone/polished_emberstone_wall_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:polished_emberstone_wall", 1, "Stonecutting: cut 1x Polished Emberstone Wall from Emberstone.", "Steins\u00e4ge: 1x Polierte Glutsteinmauer aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_bricks_from_emberstone_stonecutting", "copper_inferno:emberstone_bricks", "pyrestone/emberstone_bricks_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_bricks", 1, "Stonecutting: cut 1x Emberstone Bricks from Emberstone.", "Steins\u00e4ge: 1x Glutsteinziegel aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_brick_slab_from_emberstone_stonecutting", "copper_inferno:emberstone_brick_slab", "pyrestone/emberstone_brick_slab_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_brick_slab", 2, "Stonecutting: cut 2x Emberstone Brick Slab from Emberstone.", "Steins\u00e4ge: 2x Glutsteinziegelstufe aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_brick_stairs_from_emberstone_stonecutting", "copper_inferno:emberstone_brick_stairs", "pyrestone/emberstone_brick_stairs_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_brick_stairs", 1, "Stonecutting: cut 1x Emberstone Brick Stairs from Emberstone.", "Steins\u00e4ge: 1x Glutsteinziegeltreppe aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_brick_wall_from_emberstone_stonecutting", "copper_inferno:emberstone_brick_wall", "pyrestone/emberstone_brick_wall_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_brick_wall", 1, "Stonecutting: cut 1x Emberstone Brick Wall from Emberstone.", "Steins\u00e4ge: 1x Glutsteinziegelmauer aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_tiles_from_emberstone_stonecutting", "copper_inferno:emberstone_tiles", "pyrestone/emberstone_tiles_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_tiles", 1, "Stonecutting: cut 1x Emberstone Tiles from Emberstone.", "Steins\u00e4ge: 1x Glutsteinfliesen aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_emberstone_bricks_from_emberstone_stonecutting", "copper_inferno:chiseled_emberstone_bricks", "pyrestone/chiseled_emberstone_bricks_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:chiseled_emberstone_bricks", 1, "Stonecutting: cut 1x Chiseled Emberstone Bricks from Emberstone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glutsteinziegel aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/emberstone_pillar_from_emberstone_stonecutting", "copper_inferno:emberstone_pillar", "pyrestone/emberstone_pillar_from_emberstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberstone", "", "", "", ""},
				"copper_inferno:emberstone_pillar", 1, "Stonecutting: cut 1x Emberstone Pillar from Emberstone.", "Steins\u00e4ge: 1x Glutsteins\u00e4ule aus Glutstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone", "copper_inferno:sootstone", "pyrestone/sootstone",
				new String[] {"minecraft:tuff", "", "minecraft:tuff", "", "copper_inferno:emberstone", "", "minecraft:tuff", "", "minecraft:tuff"},
				"copper_inferno:sootstone", 4, "Craft 4x Sootstone at a crafting table.", "Stellt 4x Ru\u00dfstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_bricks", "copper_inferno:sootstone_bricks", "pyrestone/sootstone_bricks",
				new String[] {"copper_inferno:sootstone", "copper_inferno:sootstone", "", "copper_inferno:sootstone", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_bricks", 4, "Craft 4x Sootstone Bricks at a crafting table.", "Stellt 4x Ru\u00dfsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_tiles", "copper_inferno:sootstone_tiles", "pyrestone/sootstone_tiles",
				new String[] {"copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "", "copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "", "", "", ""},
				"copper_inferno:sootstone_tiles", 4, "Craft 4x Sootstone Tiles at a crafting table.", "Stellt 4x Ru\u00dfsteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_sootstone", "copper_inferno:polished_sootstone", "pyrestone/polished_sootstone",
				new String[] {"copper_inferno:sootstone_tiles", "copper_inferno:sootstone_tiles", "", "copper_inferno:sootstone_tiles", "copper_inferno:sootstone_tiles", "", "", "", ""},
				"copper_inferno:polished_sootstone", 4, "Craft 4x Polished Sootstone at a crafting table.", "Stellt 4x Polierten Ru\u00dfstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_slab", "copper_inferno:sootstone_slab", "pyrestone/sootstone_slab",
				new String[] {"copper_inferno:sootstone", "copper_inferno:sootstone", "copper_inferno:sootstone", "", "", "", "", "", ""},
				"copper_inferno:sootstone_slab", 6, "Craft 6x Sootstone Slab at a crafting table.", "Stellt 6x Ru\u00dfsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_stairs", "copper_inferno:sootstone_stairs", "pyrestone/sootstone_stairs",
				new String[] {"copper_inferno:sootstone", "", "", "copper_inferno:sootstone", "copper_inferno:sootstone", "", "copper_inferno:sootstone", "copper_inferno:sootstone", "copper_inferno:sootstone"},
				"copper_inferno:sootstone_stairs", 4, "Craft 4x Sootstone Stairs at a crafting table.", "Stellt 4x Ru\u00dfsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_wall", "copper_inferno:sootstone_wall", "pyrestone/sootstone_wall",
				new String[] {"copper_inferno:sootstone", "copper_inferno:sootstone", "copper_inferno:sootstone", "copper_inferno:sootstone", "copper_inferno:sootstone", "copper_inferno:sootstone", "", "", ""},
				"copper_inferno:sootstone_wall", 6, "Craft 6x Sootstone Wall at a crafting table.", "Stellt 6x Ru\u00dfsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_sootstone_slab", "copper_inferno:polished_sootstone_slab", "pyrestone/polished_sootstone_slab",
				new String[] {"copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone", "", "", "", "", "", ""},
				"copper_inferno:polished_sootstone_slab", 6, "Craft 6x Polished Sootstone Slab at a crafting table.", "Stellt 6x Polierte Ru\u00dfsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_sootstone_stairs", "copper_inferno:polished_sootstone_stairs", "pyrestone/polished_sootstone_stairs",
				new String[] {"copper_inferno:polished_sootstone", "", "", "copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone", "", "copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone"},
				"copper_inferno:polished_sootstone_stairs", 4, "Craft 4x Polished Sootstone Stairs at a crafting table.", "Stellt 4x Polierte Ru\u00dfsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_sootstone_wall", "copper_inferno:polished_sootstone_wall", "pyrestone/polished_sootstone_wall",
				new String[] {"copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone", "copper_inferno:polished_sootstone", "", "", ""},
				"copper_inferno:polished_sootstone_wall", 6, "Craft 6x Polished Sootstone Wall at a crafting table.", "Stellt 6x Polierte Ru\u00dfsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_brick_slab", "copper_inferno:sootstone_brick_slab", "pyrestone/sootstone_brick_slab",
				new String[] {"copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "", "", "", "", "", ""},
				"copper_inferno:sootstone_brick_slab", 6, "Craft 6x Sootstone Brick Slab at a crafting table.", "Stellt 6x Ru\u00dfsteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_brick_stairs", "copper_inferno:sootstone_brick_stairs", "pyrestone/sootstone_brick_stairs",
				new String[] {"copper_inferno:sootstone_bricks", "", "", "copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "", "copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks"},
				"copper_inferno:sootstone_brick_stairs", 4, "Craft 4x Sootstone Brick Stairs at a crafting table.", "Stellt 4x Ru\u00dfsteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_brick_wall", "copper_inferno:sootstone_brick_wall", "pyrestone/sootstone_brick_wall",
				new String[] {"copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "copper_inferno:sootstone_bricks", "", "", ""},
				"copper_inferno:sootstone_brick_wall", 6, "Craft 6x Sootstone Brick Wall at a crafting table.", "Stellt 6x Ru\u00dfsteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_sootstone_bricks", "copper_inferno:cracked_sootstone_bricks", "pyrestone/cracked_sootstone_bricks",
				new String[] {"", "", "", "", "copper_inferno:sootstone_bricks", "", "", "", ""},
				"copper_inferno:cracked_sootstone_bricks", 1, "Smelting Sootstone Bricks in a furnace yields Cracked Sootstone Bricks.", "Ru\u00dfsteinziegel im Ofen gebrannt ergibt Rissige Ru\u00dfsteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_sootstone_bricks", "copper_inferno:chiseled_sootstone_bricks", "pyrestone/chiseled_sootstone_bricks",
				new String[] {"copper_inferno:sootstone_brick_slab", "", "", "copper_inferno:sootstone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_sootstone_bricks", 1, "Craft 1x Chiseled Sootstone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Ru\u00dfsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_pillar", "copper_inferno:sootstone_pillar", "pyrestone/sootstone_pillar",
				new String[] {"copper_inferno:sootstone_bricks", "", "", "copper_inferno:sootstone_bricks", "", "", "", "", ""},
				"copper_inferno:sootstone_pillar", 2, "Craft 2x Sootstone Pillar at a crafting table.", "Stellt 2x Ru\u00dfsteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_slab_from_sootstone_stonecutting", "copper_inferno:sootstone_slab", "pyrestone/sootstone_slab_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_slab", 2, "Stonecutting: cut 2x Sootstone Slab from Sootstone.", "Steins\u00e4ge: 2x Ru\u00dfsteinstufe aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_stairs_from_sootstone_stonecutting", "copper_inferno:sootstone_stairs", "pyrestone/sootstone_stairs_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_stairs", 1, "Stonecutting: cut 1x Sootstone Stairs from Sootstone.", "Steins\u00e4ge: 1x Ru\u00dfsteintreppe aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_wall_from_sootstone_stonecutting", "copper_inferno:sootstone_wall", "pyrestone/sootstone_wall_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_wall", 1, "Stonecutting: cut 1x Sootstone Wall from Sootstone.", "Steins\u00e4ge: 1x Ru\u00dfsteinmauer aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_sootstone_from_sootstone_stonecutting", "copper_inferno:polished_sootstone", "pyrestone/polished_sootstone_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:polished_sootstone", 1, "Stonecutting: cut 1x Polished Sootstone from Sootstone.", "Steins\u00e4ge: 1x Polierten Ru\u00dfstein aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_sootstone_slab_from_sootstone_stonecutting", "copper_inferno:polished_sootstone_slab", "pyrestone/polished_sootstone_slab_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:polished_sootstone_slab", 2, "Stonecutting: cut 2x Polished Sootstone Slab from Sootstone.", "Steins\u00e4ge: 2x Polierte Ru\u00dfsteinstufe aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_sootstone_stairs_from_sootstone_stonecutting", "copper_inferno:polished_sootstone_stairs", "pyrestone/polished_sootstone_stairs_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:polished_sootstone_stairs", 1, "Stonecutting: cut 1x Polished Sootstone Stairs from Sootstone.", "Steins\u00e4ge: 1x Polierte Ru\u00dfsteintreppe aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_sootstone_wall_from_sootstone_stonecutting", "copper_inferno:polished_sootstone_wall", "pyrestone/polished_sootstone_wall_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:polished_sootstone_wall", 1, "Stonecutting: cut 1x Polished Sootstone Wall from Sootstone.", "Steins\u00e4ge: 1x Polierte Ru\u00dfsteinmauer aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_bricks_from_sootstone_stonecutting", "copper_inferno:sootstone_bricks", "pyrestone/sootstone_bricks_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_bricks", 1, "Stonecutting: cut 1x Sootstone Bricks from Sootstone.", "Steins\u00e4ge: 1x Ru\u00dfsteinziegel aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_brick_slab_from_sootstone_stonecutting", "copper_inferno:sootstone_brick_slab", "pyrestone/sootstone_brick_slab_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_brick_slab", 2, "Stonecutting: cut 2x Sootstone Brick Slab from Sootstone.", "Steins\u00e4ge: 2x Ru\u00dfsteinziegelstufe aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_brick_stairs_from_sootstone_stonecutting", "copper_inferno:sootstone_brick_stairs", "pyrestone/sootstone_brick_stairs_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_brick_stairs", 1, "Stonecutting: cut 1x Sootstone Brick Stairs from Sootstone.", "Steins\u00e4ge: 1x Ru\u00dfsteinziegeltreppe aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_brick_wall_from_sootstone_stonecutting", "copper_inferno:sootstone_brick_wall", "pyrestone/sootstone_brick_wall_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_brick_wall", 1, "Stonecutting: cut 1x Sootstone Brick Wall from Sootstone.", "Steins\u00e4ge: 1x Ru\u00dfsteinziegelmauer aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_tiles_from_sootstone_stonecutting", "copper_inferno:sootstone_tiles", "pyrestone/sootstone_tiles_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_tiles", 1, "Stonecutting: cut 1x Sootstone Tiles from Sootstone.", "Steins\u00e4ge: 1x Ru\u00dfsteinfliesen aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_sootstone_bricks_from_sootstone_stonecutting", "copper_inferno:chiseled_sootstone_bricks", "pyrestone/chiseled_sootstone_bricks_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:chiseled_sootstone_bricks", 1, "Stonecutting: cut 1x Chiseled Sootstone Bricks from Sootstone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Ru\u00dfsteinziegel aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/sootstone_pillar_from_sootstone_stonecutting", "copper_inferno:sootstone_pillar", "pyrestone/sootstone_pillar_from_sootstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootstone", "", "", "", ""},
				"copper_inferno:sootstone_pillar", 1, "Stonecutting: cut 1x Sootstone Pillar from Sootstone.", "Steins\u00e4ge: 1x Ru\u00dfsteins\u00e4ule aus Ru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint", "copper_inferno:charflint", "pyrestone/charflint",
				new String[] {"minecraft:flint", "", "minecraft:flint", "", "copper_inferno:sootstone", "", "minecraft:flint", "", "minecraft:flint"},
				"copper_inferno:charflint", 4, "Craft 4x Charflint at a crafting table.", "Stellt 4x Schwarzflint an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_bricks", "copper_inferno:charflint_bricks", "pyrestone/charflint_bricks",
				new String[] {"copper_inferno:charflint", "copper_inferno:charflint", "", "copper_inferno:charflint", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_bricks", 4, "Craft 4x Charflint Bricks at a crafting table.", "Stellt 4x Schwarzflintziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_tiles", "copper_inferno:charflint_tiles", "pyrestone/charflint_tiles",
				new String[] {"copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "", "copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "", "", "", ""},
				"copper_inferno:charflint_tiles", 4, "Craft 4x Charflint Tiles at a crafting table.", "Stellt 4x Schwarzflintfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_charflint", "copper_inferno:polished_charflint", "pyrestone/polished_charflint",
				new String[] {"copper_inferno:charflint_tiles", "copper_inferno:charflint_tiles", "", "copper_inferno:charflint_tiles", "copper_inferno:charflint_tiles", "", "", "", ""},
				"copper_inferno:polished_charflint", 4, "Craft 4x Polished Charflint at a crafting table.", "Stellt 4x Polierten Schwarzflint an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_slab", "copper_inferno:charflint_slab", "pyrestone/charflint_slab",
				new String[] {"copper_inferno:charflint", "copper_inferno:charflint", "copper_inferno:charflint", "", "", "", "", "", ""},
				"copper_inferno:charflint_slab", 6, "Craft 6x Charflint Slab at a crafting table.", "Stellt 6x Schwarzflintstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_stairs", "copper_inferno:charflint_stairs", "pyrestone/charflint_stairs",
				new String[] {"copper_inferno:charflint", "", "", "copper_inferno:charflint", "copper_inferno:charflint", "", "copper_inferno:charflint", "copper_inferno:charflint", "copper_inferno:charflint"},
				"copper_inferno:charflint_stairs", 4, "Craft 4x Charflint Stairs at a crafting table.", "Stellt 4x Schwarzflinttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_wall", "copper_inferno:charflint_wall", "pyrestone/charflint_wall",
				new String[] {"copper_inferno:charflint", "copper_inferno:charflint", "copper_inferno:charflint", "copper_inferno:charflint", "copper_inferno:charflint", "copper_inferno:charflint", "", "", ""},
				"copper_inferno:charflint_wall", 6, "Craft 6x Charflint Wall at a crafting table.", "Stellt 6x Schwarzflintmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_charflint_slab", "copper_inferno:polished_charflint_slab", "pyrestone/polished_charflint_slab",
				new String[] {"copper_inferno:polished_charflint", "copper_inferno:polished_charflint", "copper_inferno:polished_charflint", "", "", "", "", "", ""},
				"copper_inferno:polished_charflint_slab", 6, "Craft 6x Polished Charflint Slab at a crafting table.", "Stellt 6x Polierte Schwarzflintstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_charflint_stairs", "copper_inferno:polished_charflint_stairs", "pyrestone/polished_charflint_stairs",
				new String[] {"copper_inferno:polished_charflint", "", "", "copper_inferno:polished_charflint", "copper_inferno:polished_charflint", "", "copper_inferno:polished_charflint", "copper_inferno:polished_charflint", "copper_inferno:polished_charflint"},
				"copper_inferno:polished_charflint_stairs", 4, "Craft 4x Polished Charflint Stairs at a crafting table.", "Stellt 4x Polierte Schwarzflinttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_charflint_wall", "copper_inferno:polished_charflint_wall", "pyrestone/polished_charflint_wall",
				new String[] {"copper_inferno:polished_charflint", "copper_inferno:polished_charflint", "copper_inferno:polished_charflint", "copper_inferno:polished_charflint", "copper_inferno:polished_charflint", "copper_inferno:polished_charflint", "", "", ""},
				"copper_inferno:polished_charflint_wall", 6, "Craft 6x Polished Charflint Wall at a crafting table.", "Stellt 6x Polierte Schwarzflintmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_brick_slab", "copper_inferno:charflint_brick_slab", "pyrestone/charflint_brick_slab",
				new String[] {"copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "", "", "", "", "", ""},
				"copper_inferno:charflint_brick_slab", 6, "Craft 6x Charflint Brick Slab at a crafting table.", "Stellt 6x Schwarzflintziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_brick_stairs", "copper_inferno:charflint_brick_stairs", "pyrestone/charflint_brick_stairs",
				new String[] {"copper_inferno:charflint_bricks", "", "", "copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "", "copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks"},
				"copper_inferno:charflint_brick_stairs", 4, "Craft 4x Charflint Brick Stairs at a crafting table.", "Stellt 4x Schwarzflintziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_brick_wall", "copper_inferno:charflint_brick_wall", "pyrestone/charflint_brick_wall",
				new String[] {"copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "copper_inferno:charflint_bricks", "", "", ""},
				"copper_inferno:charflint_brick_wall", 6, "Craft 6x Charflint Brick Wall at a crafting table.", "Stellt 6x Schwarzflintziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_charflint_bricks", "copper_inferno:cracked_charflint_bricks", "pyrestone/cracked_charflint_bricks",
				new String[] {"", "", "", "", "copper_inferno:charflint_bricks", "", "", "", ""},
				"copper_inferno:cracked_charflint_bricks", 1, "Smelting Charflint Bricks in a furnace yields Cracked Charflint Bricks.", "Schwarzflintziegel im Ofen gebrannt ergibt Rissige Schwarzflintziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_charflint_bricks", "copper_inferno:chiseled_charflint_bricks", "pyrestone/chiseled_charflint_bricks",
				new String[] {"copper_inferno:charflint_brick_slab", "", "", "copper_inferno:charflint_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_charflint_bricks", 1, "Craft 1x Chiseled Charflint Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schwarzflintziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_pillar", "copper_inferno:charflint_pillar", "pyrestone/charflint_pillar",
				new String[] {"copper_inferno:charflint_bricks", "", "", "copper_inferno:charflint_bricks", "", "", "", "", ""},
				"copper_inferno:charflint_pillar", 2, "Craft 2x Charflint Pillar at a crafting table.", "Stellt 2x Schwarzflints\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_slab_from_charflint_stonecutting", "copper_inferno:charflint_slab", "pyrestone/charflint_slab_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_slab", 2, "Stonecutting: cut 2x Charflint Slab from Charflint.", "Steins\u00e4ge: 2x Schwarzflintstufe aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_stairs_from_charflint_stonecutting", "copper_inferno:charflint_stairs", "pyrestone/charflint_stairs_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_stairs", 1, "Stonecutting: cut 1x Charflint Stairs from Charflint.", "Steins\u00e4ge: 1x Schwarzflinttreppe aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_wall_from_charflint_stonecutting", "copper_inferno:charflint_wall", "pyrestone/charflint_wall_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_wall", 1, "Stonecutting: cut 1x Charflint Wall from Charflint.", "Steins\u00e4ge: 1x Schwarzflintmauer aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_charflint_from_charflint_stonecutting", "copper_inferno:polished_charflint", "pyrestone/polished_charflint_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:polished_charflint", 1, "Stonecutting: cut 1x Polished Charflint from Charflint.", "Steins\u00e4ge: 1x Polierten Schwarzflint aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_charflint_slab_from_charflint_stonecutting", "copper_inferno:polished_charflint_slab", "pyrestone/polished_charflint_slab_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:polished_charflint_slab", 2, "Stonecutting: cut 2x Polished Charflint Slab from Charflint.", "Steins\u00e4ge: 2x Polierte Schwarzflintstufe aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_charflint_stairs_from_charflint_stonecutting", "copper_inferno:polished_charflint_stairs", "pyrestone/polished_charflint_stairs_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:polished_charflint_stairs", 1, "Stonecutting: cut 1x Polished Charflint Stairs from Charflint.", "Steins\u00e4ge: 1x Polierte Schwarzflinttreppe aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_charflint_wall_from_charflint_stonecutting", "copper_inferno:polished_charflint_wall", "pyrestone/polished_charflint_wall_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:polished_charflint_wall", 1, "Stonecutting: cut 1x Polished Charflint Wall from Charflint.", "Steins\u00e4ge: 1x Polierte Schwarzflintmauer aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_bricks_from_charflint_stonecutting", "copper_inferno:charflint_bricks", "pyrestone/charflint_bricks_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_bricks", 1, "Stonecutting: cut 1x Charflint Bricks from Charflint.", "Steins\u00e4ge: 1x Schwarzflintziegel aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_brick_slab_from_charflint_stonecutting", "copper_inferno:charflint_brick_slab", "pyrestone/charflint_brick_slab_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_brick_slab", 2, "Stonecutting: cut 2x Charflint Brick Slab from Charflint.", "Steins\u00e4ge: 2x Schwarzflintziegelstufe aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_brick_stairs_from_charflint_stonecutting", "copper_inferno:charflint_brick_stairs", "pyrestone/charflint_brick_stairs_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_brick_stairs", 1, "Stonecutting: cut 1x Charflint Brick Stairs from Charflint.", "Steins\u00e4ge: 1x Schwarzflintziegeltreppe aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_brick_wall_from_charflint_stonecutting", "copper_inferno:charflint_brick_wall", "pyrestone/charflint_brick_wall_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_brick_wall", 1, "Stonecutting: cut 1x Charflint Brick Wall from Charflint.", "Steins\u00e4ge: 1x Schwarzflintziegelmauer aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_tiles_from_charflint_stonecutting", "copper_inferno:charflint_tiles", "pyrestone/charflint_tiles_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_tiles", 1, "Stonecutting: cut 1x Charflint Tiles from Charflint.", "Steins\u00e4ge: 1x Schwarzflintfliesen aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_charflint_bricks_from_charflint_stonecutting", "copper_inferno:chiseled_charflint_bricks", "pyrestone/chiseled_charflint_bricks_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:chiseled_charflint_bricks", 1, "Stonecutting: cut 1x Chiseled Charflint Bricks from Charflint.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schwarzflintziegel aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/charflint_pillar_from_charflint_stonecutting", "copper_inferno:charflint_pillar", "pyrestone/charflint_pillar_from_charflint_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:charflint", "", "", "", ""},
				"copper_inferno:charflint_pillar", 1, "Stonecutting: cut 1x Charflint Pillar from Charflint.", "Steins\u00e4ge: 1x Schwarzflints\u00e4ule aus Schwarzflint schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock", "copper_inferno:kindlerock", "pyrestone/kindlerock",
				new String[] {"minecraft:packed_mud", "", "minecraft:packed_mud", "", "copper_inferno:charflint", "", "minecraft:packed_mud", "", "minecraft:packed_mud"},
				"copper_inferno:kindlerock", 4, "Craft 4x Kindlerock at a crafting table.", "Stellt 4x Z\u00fcndelstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_bricks", "copper_inferno:kindlerock_bricks", "pyrestone/kindlerock_bricks",
				new String[] {"copper_inferno:kindlerock", "copper_inferno:kindlerock", "", "copper_inferno:kindlerock", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_bricks", 4, "Craft 4x Kindlerock Bricks at a crafting table.", "Stellt 4x Z\u00fcndelsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_tiles", "copper_inferno:kindlerock_tiles", "pyrestone/kindlerock_tiles",
				new String[] {"copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "", "copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "", "", "", ""},
				"copper_inferno:kindlerock_tiles", 4, "Craft 4x Kindlerock Tiles at a crafting table.", "Stellt 4x Z\u00fcndelsteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_kindlerock", "copper_inferno:polished_kindlerock", "pyrestone/polished_kindlerock",
				new String[] {"copper_inferno:kindlerock_tiles", "copper_inferno:kindlerock_tiles", "", "copper_inferno:kindlerock_tiles", "copper_inferno:kindlerock_tiles", "", "", "", ""},
				"copper_inferno:polished_kindlerock", 4, "Craft 4x Polished Kindlerock at a crafting table.", "Stellt 4x Polierten Z\u00fcndelstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_slab", "copper_inferno:kindlerock_slab", "pyrestone/kindlerock_slab",
				new String[] {"copper_inferno:kindlerock", "copper_inferno:kindlerock", "copper_inferno:kindlerock", "", "", "", "", "", ""},
				"copper_inferno:kindlerock_slab", 6, "Craft 6x Kindlerock Slab at a crafting table.", "Stellt 6x Z\u00fcndelsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_stairs", "copper_inferno:kindlerock_stairs", "pyrestone/kindlerock_stairs",
				new String[] {"copper_inferno:kindlerock", "", "", "copper_inferno:kindlerock", "copper_inferno:kindlerock", "", "copper_inferno:kindlerock", "copper_inferno:kindlerock", "copper_inferno:kindlerock"},
				"copper_inferno:kindlerock_stairs", 4, "Craft 4x Kindlerock Stairs at a crafting table.", "Stellt 4x Z\u00fcndelsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_wall", "copper_inferno:kindlerock_wall", "pyrestone/kindlerock_wall",
				new String[] {"copper_inferno:kindlerock", "copper_inferno:kindlerock", "copper_inferno:kindlerock", "copper_inferno:kindlerock", "copper_inferno:kindlerock", "copper_inferno:kindlerock", "", "", ""},
				"copper_inferno:kindlerock_wall", 6, "Craft 6x Kindlerock Wall at a crafting table.", "Stellt 6x Z\u00fcndelsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_kindlerock_slab", "copper_inferno:polished_kindlerock_slab", "pyrestone/polished_kindlerock_slab",
				new String[] {"copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock", "", "", "", "", "", ""},
				"copper_inferno:polished_kindlerock_slab", 6, "Craft 6x Polished Kindlerock Slab at a crafting table.", "Stellt 6x Polierte Z\u00fcndelsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_kindlerock_stairs", "copper_inferno:polished_kindlerock_stairs", "pyrestone/polished_kindlerock_stairs",
				new String[] {"copper_inferno:polished_kindlerock", "", "", "copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock", "", "copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock"},
				"copper_inferno:polished_kindlerock_stairs", 4, "Craft 4x Polished Kindlerock Stairs at a crafting table.", "Stellt 4x Polierte Z\u00fcndelsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_kindlerock_wall", "copper_inferno:polished_kindlerock_wall", "pyrestone/polished_kindlerock_wall",
				new String[] {"copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock", "copper_inferno:polished_kindlerock", "", "", ""},
				"copper_inferno:polished_kindlerock_wall", 6, "Craft 6x Polished Kindlerock Wall at a crafting table.", "Stellt 6x Polierte Z\u00fcndelsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_brick_slab", "copper_inferno:kindlerock_brick_slab", "pyrestone/kindlerock_brick_slab",
				new String[] {"copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "", "", "", "", "", ""},
				"copper_inferno:kindlerock_brick_slab", 6, "Craft 6x Kindlerock Brick Slab at a crafting table.", "Stellt 6x Z\u00fcndelsteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_brick_stairs", "copper_inferno:kindlerock_brick_stairs", "pyrestone/kindlerock_brick_stairs",
				new String[] {"copper_inferno:kindlerock_bricks", "", "", "copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "", "copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks"},
				"copper_inferno:kindlerock_brick_stairs", 4, "Craft 4x Kindlerock Brick Stairs at a crafting table.", "Stellt 4x Z\u00fcndelsteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_brick_wall", "copper_inferno:kindlerock_brick_wall", "pyrestone/kindlerock_brick_wall",
				new String[] {"copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "copper_inferno:kindlerock_bricks", "", "", ""},
				"copper_inferno:kindlerock_brick_wall", 6, "Craft 6x Kindlerock Brick Wall at a crafting table.", "Stellt 6x Z\u00fcndelsteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_kindlerock_bricks", "copper_inferno:cracked_kindlerock_bricks", "pyrestone/cracked_kindlerock_bricks",
				new String[] {"", "", "", "", "copper_inferno:kindlerock_bricks", "", "", "", ""},
				"copper_inferno:cracked_kindlerock_bricks", 1, "Smelting Kindlerock Bricks in a furnace yields Cracked Kindlerock Bricks.", "Z\u00fcndelsteinziegel im Ofen gebrannt ergibt Rissige Z\u00fcndelsteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_kindlerock_bricks", "copper_inferno:chiseled_kindlerock_bricks", "pyrestone/chiseled_kindlerock_bricks",
				new String[] {"copper_inferno:kindlerock_brick_slab", "", "", "copper_inferno:kindlerock_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_kindlerock_bricks", 1, "Craft 1x Chiseled Kindlerock Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Z\u00fcndelsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_pillar", "copper_inferno:kindlerock_pillar", "pyrestone/kindlerock_pillar",
				new String[] {"copper_inferno:kindlerock_bricks", "", "", "copper_inferno:kindlerock_bricks", "", "", "", "", ""},
				"copper_inferno:kindlerock_pillar", 2, "Craft 2x Kindlerock Pillar at a crafting table.", "Stellt 2x Z\u00fcndelsteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_slab_from_kindlerock_stonecutting", "copper_inferno:kindlerock_slab", "pyrestone/kindlerock_slab_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_slab", 2, "Stonecutting: cut 2x Kindlerock Slab from Kindlerock.", "Steins\u00e4ge: 2x Z\u00fcndelsteinstufe aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_stairs_from_kindlerock_stonecutting", "copper_inferno:kindlerock_stairs", "pyrestone/kindlerock_stairs_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_stairs", 1, "Stonecutting: cut 1x Kindlerock Stairs from Kindlerock.", "Steins\u00e4ge: 1x Z\u00fcndelsteintreppe aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_wall_from_kindlerock_stonecutting", "copper_inferno:kindlerock_wall", "pyrestone/kindlerock_wall_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_wall", 1, "Stonecutting: cut 1x Kindlerock Wall from Kindlerock.", "Steins\u00e4ge: 1x Z\u00fcndelsteinmauer aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_kindlerock_from_kindlerock_stonecutting", "copper_inferno:polished_kindlerock", "pyrestone/polished_kindlerock_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:polished_kindlerock", 1, "Stonecutting: cut 1x Polished Kindlerock from Kindlerock.", "Steins\u00e4ge: 1x Polierten Z\u00fcndelstein aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_kindlerock_slab_from_kindlerock_stonecutting", "copper_inferno:polished_kindlerock_slab", "pyrestone/polished_kindlerock_slab_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:polished_kindlerock_slab", 2, "Stonecutting: cut 2x Polished Kindlerock Slab from Kindlerock.", "Steins\u00e4ge: 2x Polierte Z\u00fcndelsteinstufe aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_kindlerock_stairs_from_kindlerock_stonecutting", "copper_inferno:polished_kindlerock_stairs", "pyrestone/polished_kindlerock_stairs_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:polished_kindlerock_stairs", 1, "Stonecutting: cut 1x Polished Kindlerock Stairs from Kindlerock.", "Steins\u00e4ge: 1x Polierte Z\u00fcndelsteintreppe aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_kindlerock_wall_from_kindlerock_stonecutting", "copper_inferno:polished_kindlerock_wall", "pyrestone/polished_kindlerock_wall_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:polished_kindlerock_wall", 1, "Stonecutting: cut 1x Polished Kindlerock Wall from Kindlerock.", "Steins\u00e4ge: 1x Polierte Z\u00fcndelsteinmauer aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_bricks_from_kindlerock_stonecutting", "copper_inferno:kindlerock_bricks", "pyrestone/kindlerock_bricks_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_bricks", 1, "Stonecutting: cut 1x Kindlerock Bricks from Kindlerock.", "Steins\u00e4ge: 1x Z\u00fcndelsteinziegel aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_brick_slab_from_kindlerock_stonecutting", "copper_inferno:kindlerock_brick_slab", "pyrestone/kindlerock_brick_slab_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_brick_slab", 2, "Stonecutting: cut 2x Kindlerock Brick Slab from Kindlerock.", "Steins\u00e4ge: 2x Z\u00fcndelsteinziegelstufe aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_brick_stairs_from_kindlerock_stonecutting", "copper_inferno:kindlerock_brick_stairs", "pyrestone/kindlerock_brick_stairs_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_brick_stairs", 1, "Stonecutting: cut 1x Kindlerock Brick Stairs from Kindlerock.", "Steins\u00e4ge: 1x Z\u00fcndelsteinziegeltreppe aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_brick_wall_from_kindlerock_stonecutting", "copper_inferno:kindlerock_brick_wall", "pyrestone/kindlerock_brick_wall_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_brick_wall", 1, "Stonecutting: cut 1x Kindlerock Brick Wall from Kindlerock.", "Steins\u00e4ge: 1x Z\u00fcndelsteinziegelmauer aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_tiles_from_kindlerock_stonecutting", "copper_inferno:kindlerock_tiles", "pyrestone/kindlerock_tiles_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_tiles", 1, "Stonecutting: cut 1x Kindlerock Tiles from Kindlerock.", "Steins\u00e4ge: 1x Z\u00fcndelsteinfliesen aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_kindlerock_bricks_from_kindlerock_stonecutting", "copper_inferno:chiseled_kindlerock_bricks", "pyrestone/chiseled_kindlerock_bricks_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:chiseled_kindlerock_bricks", 1, "Stonecutting: cut 1x Chiseled Kindlerock Bricks from Kindlerock.", "Steins\u00e4ge: 1x Gemei\u00dfelte Z\u00fcndelsteinziegel aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/kindlerock_pillar_from_kindlerock_stonecutting", "copper_inferno:kindlerock_pillar", "pyrestone/kindlerock_pillar_from_kindlerock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kindlerock", "", "", "", ""},
				"copper_inferno:kindlerock_pillar", 1, "Stonecutting: cut 1x Kindlerock Pillar from Kindlerock.", "Steins\u00e4ge: 1x Z\u00fcndelsteins\u00e4ule aus Z\u00fcndelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale", "copper_inferno:cindershale", "pyrestone/cindershale",
				new String[] {"minecraft:deepslate", "", "minecraft:deepslate", "", "copper_inferno:kindlerock", "", "minecraft:deepslate", "", "minecraft:deepslate"},
				"copper_inferno:cindershale", 4, "Craft 4x Cindershale at a crafting table.", "Stellt 4x Aschenschiefer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_bricks", "copper_inferno:cindershale_bricks", "pyrestone/cindershale_bricks",
				new String[] {"copper_inferno:cindershale", "copper_inferno:cindershale", "", "copper_inferno:cindershale", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_bricks", 4, "Craft 4x Cindershale Bricks at a crafting table.", "Stellt 4x Aschenschieferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_tiles", "copper_inferno:cindershale_tiles", "pyrestone/cindershale_tiles",
				new String[] {"copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "", "copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "", "", "", ""},
				"copper_inferno:cindershale_tiles", 4, "Craft 4x Cindershale Tiles at a crafting table.", "Stellt 4x Aschenschieferfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_cindershale", "copper_inferno:polished_cindershale", "pyrestone/polished_cindershale",
				new String[] {"copper_inferno:cindershale_tiles", "copper_inferno:cindershale_tiles", "", "copper_inferno:cindershale_tiles", "copper_inferno:cindershale_tiles", "", "", "", ""},
				"copper_inferno:polished_cindershale", 4, "Craft 4x Polished Cindershale at a crafting table.", "Stellt 4x Polierten Aschenschiefer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_slab", "copper_inferno:cindershale_slab", "pyrestone/cindershale_slab",
				new String[] {"copper_inferno:cindershale", "copper_inferno:cindershale", "copper_inferno:cindershale", "", "", "", "", "", ""},
				"copper_inferno:cindershale_slab", 6, "Craft 6x Cindershale Slab at a crafting table.", "Stellt 6x Aschenschieferstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_stairs", "copper_inferno:cindershale_stairs", "pyrestone/cindershale_stairs",
				new String[] {"copper_inferno:cindershale", "", "", "copper_inferno:cindershale", "copper_inferno:cindershale", "", "copper_inferno:cindershale", "copper_inferno:cindershale", "copper_inferno:cindershale"},
				"copper_inferno:cindershale_stairs", 4, "Craft 4x Cindershale Stairs at a crafting table.", "Stellt 4x Aschenschiefertreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_wall", "copper_inferno:cindershale_wall", "pyrestone/cindershale_wall",
				new String[] {"copper_inferno:cindershale", "copper_inferno:cindershale", "copper_inferno:cindershale", "copper_inferno:cindershale", "copper_inferno:cindershale", "copper_inferno:cindershale", "", "", ""},
				"copper_inferno:cindershale_wall", 6, "Craft 6x Cindershale Wall at a crafting table.", "Stellt 6x Aschenschiefermauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_cindershale_slab", "copper_inferno:polished_cindershale_slab", "pyrestone/polished_cindershale_slab",
				new String[] {"copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale", "", "", "", "", "", ""},
				"copper_inferno:polished_cindershale_slab", 6, "Craft 6x Polished Cindershale Slab at a crafting table.", "Stellt 6x Polierte Aschenschieferstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_cindershale_stairs", "copper_inferno:polished_cindershale_stairs", "pyrestone/polished_cindershale_stairs",
				new String[] {"copper_inferno:polished_cindershale", "", "", "copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale", "", "copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale"},
				"copper_inferno:polished_cindershale_stairs", 4, "Craft 4x Polished Cindershale Stairs at a crafting table.", "Stellt 4x Polierte Aschenschiefertreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_cindershale_wall", "copper_inferno:polished_cindershale_wall", "pyrestone/polished_cindershale_wall",
				new String[] {"copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale", "copper_inferno:polished_cindershale", "", "", ""},
				"copper_inferno:polished_cindershale_wall", 6, "Craft 6x Polished Cindershale Wall at a crafting table.", "Stellt 6x Polierte Aschenschiefermauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_brick_slab", "copper_inferno:cindershale_brick_slab", "pyrestone/cindershale_brick_slab",
				new String[] {"copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "", "", "", "", "", ""},
				"copper_inferno:cindershale_brick_slab", 6, "Craft 6x Cindershale Brick Slab at a crafting table.", "Stellt 6x Aschenschieferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_brick_stairs", "copper_inferno:cindershale_brick_stairs", "pyrestone/cindershale_brick_stairs",
				new String[] {"copper_inferno:cindershale_bricks", "", "", "copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "", "copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks"},
				"copper_inferno:cindershale_brick_stairs", 4, "Craft 4x Cindershale Brick Stairs at a crafting table.", "Stellt 4x Aschenschieferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_brick_wall", "copper_inferno:cindershale_brick_wall", "pyrestone/cindershale_brick_wall",
				new String[] {"copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "copper_inferno:cindershale_bricks", "", "", ""},
				"copper_inferno:cindershale_brick_wall", 6, "Craft 6x Cindershale Brick Wall at a crafting table.", "Stellt 6x Aschenschieferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_cindershale_bricks", "copper_inferno:cracked_cindershale_bricks", "pyrestone/cracked_cindershale_bricks",
				new String[] {"", "", "", "", "copper_inferno:cindershale_bricks", "", "", "", ""},
				"copper_inferno:cracked_cindershale_bricks", 1, "Smelting Cindershale Bricks in a furnace yields Cracked Cindershale Bricks.", "Aschenschieferziegel im Ofen gebrannt ergibt Rissige Aschenschieferziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_cindershale_bricks", "copper_inferno:chiseled_cindershale_bricks", "pyrestone/chiseled_cindershale_bricks",
				new String[] {"copper_inferno:cindershale_brick_slab", "", "", "copper_inferno:cindershale_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_cindershale_bricks", 1, "Craft 1x Chiseled Cindershale Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Aschenschieferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_pillar", "copper_inferno:cindershale_pillar", "pyrestone/cindershale_pillar",
				new String[] {"copper_inferno:cindershale_bricks", "", "", "copper_inferno:cindershale_bricks", "", "", "", "", ""},
				"copper_inferno:cindershale_pillar", 2, "Craft 2x Cindershale Pillar at a crafting table.", "Stellt 2x Aschenschiefers\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_slab_from_cindershale_stonecutting", "copper_inferno:cindershale_slab", "pyrestone/cindershale_slab_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_slab", 2, "Stonecutting: cut 2x Cindershale Slab from Cindershale.", "Steins\u00e4ge: 2x Aschenschieferstufe aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_stairs_from_cindershale_stonecutting", "copper_inferno:cindershale_stairs", "pyrestone/cindershale_stairs_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_stairs", 1, "Stonecutting: cut 1x Cindershale Stairs from Cindershale.", "Steins\u00e4ge: 1x Aschenschiefertreppe aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_wall_from_cindershale_stonecutting", "copper_inferno:cindershale_wall", "pyrestone/cindershale_wall_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_wall", 1, "Stonecutting: cut 1x Cindershale Wall from Cindershale.", "Steins\u00e4ge: 1x Aschenschiefermauer aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_cindershale_from_cindershale_stonecutting", "copper_inferno:polished_cindershale", "pyrestone/polished_cindershale_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:polished_cindershale", 1, "Stonecutting: cut 1x Polished Cindershale from Cindershale.", "Steins\u00e4ge: 1x Polierten Aschenschiefer aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_cindershale_slab_from_cindershale_stonecutting", "copper_inferno:polished_cindershale_slab", "pyrestone/polished_cindershale_slab_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:polished_cindershale_slab", 2, "Stonecutting: cut 2x Polished Cindershale Slab from Cindershale.", "Steins\u00e4ge: 2x Polierte Aschenschieferstufe aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_cindershale_stairs_from_cindershale_stonecutting", "copper_inferno:polished_cindershale_stairs", "pyrestone/polished_cindershale_stairs_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:polished_cindershale_stairs", 1, "Stonecutting: cut 1x Polished Cindershale Stairs from Cindershale.", "Steins\u00e4ge: 1x Polierte Aschenschiefertreppe aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_cindershale_wall_from_cindershale_stonecutting", "copper_inferno:polished_cindershale_wall", "pyrestone/polished_cindershale_wall_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:polished_cindershale_wall", 1, "Stonecutting: cut 1x Polished Cindershale Wall from Cindershale.", "Steins\u00e4ge: 1x Polierte Aschenschiefermauer aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_bricks_from_cindershale_stonecutting", "copper_inferno:cindershale_bricks", "pyrestone/cindershale_bricks_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_bricks", 1, "Stonecutting: cut 1x Cindershale Bricks from Cindershale.", "Steins\u00e4ge: 1x Aschenschieferziegel aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_brick_slab_from_cindershale_stonecutting", "copper_inferno:cindershale_brick_slab", "pyrestone/cindershale_brick_slab_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_brick_slab", 2, "Stonecutting: cut 2x Cindershale Brick Slab from Cindershale.", "Steins\u00e4ge: 2x Aschenschieferziegelstufe aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_brick_stairs_from_cindershale_stonecutting", "copper_inferno:cindershale_brick_stairs", "pyrestone/cindershale_brick_stairs_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_brick_stairs", 1, "Stonecutting: cut 1x Cindershale Brick Stairs from Cindershale.", "Steins\u00e4ge: 1x Aschenschieferziegeltreppe aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_brick_wall_from_cindershale_stonecutting", "copper_inferno:cindershale_brick_wall", "pyrestone/cindershale_brick_wall_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_brick_wall", 1, "Stonecutting: cut 1x Cindershale Brick Wall from Cindershale.", "Steins\u00e4ge: 1x Aschenschieferziegelmauer aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_tiles_from_cindershale_stonecutting", "copper_inferno:cindershale_tiles", "pyrestone/cindershale_tiles_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_tiles", 1, "Stonecutting: cut 1x Cindershale Tiles from Cindershale.", "Steins\u00e4ge: 1x Aschenschieferfliesen aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_cindershale_bricks_from_cindershale_stonecutting", "copper_inferno:chiseled_cindershale_bricks", "pyrestone/chiseled_cindershale_bricks_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:chiseled_cindershale_bricks", 1, "Stonecutting: cut 1x Chiseled Cindershale Bricks from Cindershale.", "Steins\u00e4ge: 1x Gemei\u00dfelte Aschenschieferziegel aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cindershale_pillar_from_cindershale_stonecutting", "copper_inferno:cindershale_pillar", "pyrestone/cindershale_pillar_from_cindershale_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cindershale", "", "", "", ""},
				"copper_inferno:cindershale_pillar", 1, "Stonecutting: cut 1x Cindershale Pillar from Cindershale.", "Steins\u00e4ge: 1x Aschenschiefers\u00e4ule aus Aschenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar", "copper_inferno:coalspar", "pyrestone/coalspar",
				new String[] {"minecraft:coal_block", "", "minecraft:coal_block", "", "copper_inferno:cindershale", "", "minecraft:coal_block", "", "minecraft:coal_block"},
				"copper_inferno:coalspar", 4, "Craft 4x Coalspar at a crafting table.", "Stellt 4x Kohlenspat an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_bricks", "copper_inferno:coalspar_bricks", "pyrestone/coalspar_bricks",
				new String[] {"copper_inferno:coalspar", "copper_inferno:coalspar", "", "copper_inferno:coalspar", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_bricks", 4, "Craft 4x Coalspar Bricks at a crafting table.", "Stellt 4x Kohlenspatziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_tiles", "copper_inferno:coalspar_tiles", "pyrestone/coalspar_tiles",
				new String[] {"copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "", "copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "", "", "", ""},
				"copper_inferno:coalspar_tiles", 4, "Craft 4x Coalspar Tiles at a crafting table.", "Stellt 4x Kohlenspatfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_coalspar", "copper_inferno:polished_coalspar", "pyrestone/polished_coalspar",
				new String[] {"copper_inferno:coalspar_tiles", "copper_inferno:coalspar_tiles", "", "copper_inferno:coalspar_tiles", "copper_inferno:coalspar_tiles", "", "", "", ""},
				"copper_inferno:polished_coalspar", 4, "Craft 4x Polished Coalspar at a crafting table.", "Stellt 4x Polierten Kohlenspat an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_slab", "copper_inferno:coalspar_slab", "pyrestone/coalspar_slab",
				new String[] {"copper_inferno:coalspar", "copper_inferno:coalspar", "copper_inferno:coalspar", "", "", "", "", "", ""},
				"copper_inferno:coalspar_slab", 6, "Craft 6x Coalspar Slab at a crafting table.", "Stellt 6x Kohlenspatstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_stairs", "copper_inferno:coalspar_stairs", "pyrestone/coalspar_stairs",
				new String[] {"copper_inferno:coalspar", "", "", "copper_inferno:coalspar", "copper_inferno:coalspar", "", "copper_inferno:coalspar", "copper_inferno:coalspar", "copper_inferno:coalspar"},
				"copper_inferno:coalspar_stairs", 4, "Craft 4x Coalspar Stairs at a crafting table.", "Stellt 4x Kohlenspattreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_wall", "copper_inferno:coalspar_wall", "pyrestone/coalspar_wall",
				new String[] {"copper_inferno:coalspar", "copper_inferno:coalspar", "copper_inferno:coalspar", "copper_inferno:coalspar", "copper_inferno:coalspar", "copper_inferno:coalspar", "", "", ""},
				"copper_inferno:coalspar_wall", 6, "Craft 6x Coalspar Wall at a crafting table.", "Stellt 6x Kohlenspatmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_coalspar_slab", "copper_inferno:polished_coalspar_slab", "pyrestone/polished_coalspar_slab",
				new String[] {"copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar", "", "", "", "", "", ""},
				"copper_inferno:polished_coalspar_slab", 6, "Craft 6x Polished Coalspar Slab at a crafting table.", "Stellt 6x Polierte Kohlenspatstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_coalspar_stairs", "copper_inferno:polished_coalspar_stairs", "pyrestone/polished_coalspar_stairs",
				new String[] {"copper_inferno:polished_coalspar", "", "", "copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar", "", "copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar"},
				"copper_inferno:polished_coalspar_stairs", 4, "Craft 4x Polished Coalspar Stairs at a crafting table.", "Stellt 4x Polierte Kohlenspattreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_coalspar_wall", "copper_inferno:polished_coalspar_wall", "pyrestone/polished_coalspar_wall",
				new String[] {"copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar", "copper_inferno:polished_coalspar", "", "", ""},
				"copper_inferno:polished_coalspar_wall", 6, "Craft 6x Polished Coalspar Wall at a crafting table.", "Stellt 6x Polierte Kohlenspatmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_brick_slab", "copper_inferno:coalspar_brick_slab", "pyrestone/coalspar_brick_slab",
				new String[] {"copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "", "", "", "", "", ""},
				"copper_inferno:coalspar_brick_slab", 6, "Craft 6x Coalspar Brick Slab at a crafting table.", "Stellt 6x Kohlenspatziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_brick_stairs", "copper_inferno:coalspar_brick_stairs", "pyrestone/coalspar_brick_stairs",
				new String[] {"copper_inferno:coalspar_bricks", "", "", "copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "", "copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks"},
				"copper_inferno:coalspar_brick_stairs", 4, "Craft 4x Coalspar Brick Stairs at a crafting table.", "Stellt 4x Kohlenspatziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_brick_wall", "copper_inferno:coalspar_brick_wall", "pyrestone/coalspar_brick_wall",
				new String[] {"copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "copper_inferno:coalspar_bricks", "", "", ""},
				"copper_inferno:coalspar_brick_wall", 6, "Craft 6x Coalspar Brick Wall at a crafting table.", "Stellt 6x Kohlenspatziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_coalspar_bricks", "copper_inferno:cracked_coalspar_bricks", "pyrestone/cracked_coalspar_bricks",
				new String[] {"", "", "", "", "copper_inferno:coalspar_bricks", "", "", "", ""},
				"copper_inferno:cracked_coalspar_bricks", 1, "Smelting Coalspar Bricks in a furnace yields Cracked Coalspar Bricks.", "Kohlenspatziegel im Ofen gebrannt ergibt Rissige Kohlenspatziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_coalspar_bricks", "copper_inferno:chiseled_coalspar_bricks", "pyrestone/chiseled_coalspar_bricks",
				new String[] {"copper_inferno:coalspar_brick_slab", "", "", "copper_inferno:coalspar_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_coalspar_bricks", 1, "Craft 1x Chiseled Coalspar Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Kohlenspatziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_pillar", "copper_inferno:coalspar_pillar", "pyrestone/coalspar_pillar",
				new String[] {"copper_inferno:coalspar_bricks", "", "", "copper_inferno:coalspar_bricks", "", "", "", "", ""},
				"copper_inferno:coalspar_pillar", 2, "Craft 2x Coalspar Pillar at a crafting table.", "Stellt 2x Kohlenspats\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_slab_from_coalspar_stonecutting", "copper_inferno:coalspar_slab", "pyrestone/coalspar_slab_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_slab", 2, "Stonecutting: cut 2x Coalspar Slab from Coalspar.", "Steins\u00e4ge: 2x Kohlenspatstufe aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_stairs_from_coalspar_stonecutting", "copper_inferno:coalspar_stairs", "pyrestone/coalspar_stairs_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_stairs", 1, "Stonecutting: cut 1x Coalspar Stairs from Coalspar.", "Steins\u00e4ge: 1x Kohlenspattreppe aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_wall_from_coalspar_stonecutting", "copper_inferno:coalspar_wall", "pyrestone/coalspar_wall_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_wall", 1, "Stonecutting: cut 1x Coalspar Wall from Coalspar.", "Steins\u00e4ge: 1x Kohlenspatmauer aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_coalspar_from_coalspar_stonecutting", "copper_inferno:polished_coalspar", "pyrestone/polished_coalspar_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:polished_coalspar", 1, "Stonecutting: cut 1x Polished Coalspar from Coalspar.", "Steins\u00e4ge: 1x Polierten Kohlenspat aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_coalspar_slab_from_coalspar_stonecutting", "copper_inferno:polished_coalspar_slab", "pyrestone/polished_coalspar_slab_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:polished_coalspar_slab", 2, "Stonecutting: cut 2x Polished Coalspar Slab from Coalspar.", "Steins\u00e4ge: 2x Polierte Kohlenspatstufe aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_coalspar_stairs_from_coalspar_stonecutting", "copper_inferno:polished_coalspar_stairs", "pyrestone/polished_coalspar_stairs_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:polished_coalspar_stairs", 1, "Stonecutting: cut 1x Polished Coalspar Stairs from Coalspar.", "Steins\u00e4ge: 1x Polierte Kohlenspattreppe aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_coalspar_wall_from_coalspar_stonecutting", "copper_inferno:polished_coalspar_wall", "pyrestone/polished_coalspar_wall_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:polished_coalspar_wall", 1, "Stonecutting: cut 1x Polished Coalspar Wall from Coalspar.", "Steins\u00e4ge: 1x Polierte Kohlenspatmauer aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_bricks_from_coalspar_stonecutting", "copper_inferno:coalspar_bricks", "pyrestone/coalspar_bricks_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_bricks", 1, "Stonecutting: cut 1x Coalspar Bricks from Coalspar.", "Steins\u00e4ge: 1x Kohlenspatziegel aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_brick_slab_from_coalspar_stonecutting", "copper_inferno:coalspar_brick_slab", "pyrestone/coalspar_brick_slab_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_brick_slab", 2, "Stonecutting: cut 2x Coalspar Brick Slab from Coalspar.", "Steins\u00e4ge: 2x Kohlenspatziegelstufe aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_brick_stairs_from_coalspar_stonecutting", "copper_inferno:coalspar_brick_stairs", "pyrestone/coalspar_brick_stairs_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_brick_stairs", 1, "Stonecutting: cut 1x Coalspar Brick Stairs from Coalspar.", "Steins\u00e4ge: 1x Kohlenspatziegeltreppe aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_brick_wall_from_coalspar_stonecutting", "copper_inferno:coalspar_brick_wall", "pyrestone/coalspar_brick_wall_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_brick_wall", 1, "Stonecutting: cut 1x Coalspar Brick Wall from Coalspar.", "Steins\u00e4ge: 1x Kohlenspatziegelmauer aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_tiles_from_coalspar_stonecutting", "copper_inferno:coalspar_tiles", "pyrestone/coalspar_tiles_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_tiles", 1, "Stonecutting: cut 1x Coalspar Tiles from Coalspar.", "Steins\u00e4ge: 1x Kohlenspatfliesen aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_coalspar_bricks_from_coalspar_stonecutting", "copper_inferno:chiseled_coalspar_bricks", "pyrestone/chiseled_coalspar_bricks_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:chiseled_coalspar_bricks", 1, "Stonecutting: cut 1x Chiseled Coalspar Bricks from Coalspar.", "Steins\u00e4ge: 1x Gemei\u00dfelte Kohlenspatziegel aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/coalspar_pillar_from_coalspar_stonecutting", "copper_inferno:coalspar_pillar", "pyrestone/coalspar_pillar_from_coalspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:coalspar", "", "", "", ""},
				"copper_inferno:coalspar_pillar", 1, "Stonecutting: cut 1x Coalspar Pillar from Coalspar.", "Steins\u00e4ge: 1x Kohlenspats\u00e4ule aus Kohlenspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit", "copper_inferno:tindergrit", "pyrestone/tindergrit",
				new String[] {"minecraft:sandstone", "", "minecraft:sandstone", "", "copper_inferno:coalspar", "", "minecraft:sandstone", "", "minecraft:sandstone"},
				"copper_inferno:tindergrit", 4, "Craft 4x Tindergrit at a crafting table.", "Stellt 4x Zundergrus an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_bricks", "copper_inferno:tindergrit_bricks", "pyrestone/tindergrit_bricks",
				new String[] {"copper_inferno:tindergrit", "copper_inferno:tindergrit", "", "copper_inferno:tindergrit", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_bricks", 4, "Craft 4x Tindergrit Bricks at a crafting table.", "Stellt 4x Zundergrusziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_tiles", "copper_inferno:tindergrit_tiles", "pyrestone/tindergrit_tiles",
				new String[] {"copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "", "copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "", "", "", ""},
				"copper_inferno:tindergrit_tiles", 4, "Craft 4x Tindergrit Tiles at a crafting table.", "Stellt 4x Zundergrusfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_tindergrit", "copper_inferno:polished_tindergrit", "pyrestone/polished_tindergrit",
				new String[] {"copper_inferno:tindergrit_tiles", "copper_inferno:tindergrit_tiles", "", "copper_inferno:tindergrit_tiles", "copper_inferno:tindergrit_tiles", "", "", "", ""},
				"copper_inferno:polished_tindergrit", 4, "Craft 4x Polished Tindergrit at a crafting table.", "Stellt 4x Polierten Zundergrus an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_slab", "copper_inferno:tindergrit_slab", "pyrestone/tindergrit_slab",
				new String[] {"copper_inferno:tindergrit", "copper_inferno:tindergrit", "copper_inferno:tindergrit", "", "", "", "", "", ""},
				"copper_inferno:tindergrit_slab", 6, "Craft 6x Tindergrit Slab at a crafting table.", "Stellt 6x Zundergrusstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_stairs", "copper_inferno:tindergrit_stairs", "pyrestone/tindergrit_stairs",
				new String[] {"copper_inferno:tindergrit", "", "", "copper_inferno:tindergrit", "copper_inferno:tindergrit", "", "copper_inferno:tindergrit", "copper_inferno:tindergrit", "copper_inferno:tindergrit"},
				"copper_inferno:tindergrit_stairs", 4, "Craft 4x Tindergrit Stairs at a crafting table.", "Stellt 4x Zundergrustreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_wall", "copper_inferno:tindergrit_wall", "pyrestone/tindergrit_wall",
				new String[] {"copper_inferno:tindergrit", "copper_inferno:tindergrit", "copper_inferno:tindergrit", "copper_inferno:tindergrit", "copper_inferno:tindergrit", "copper_inferno:tindergrit", "", "", ""},
				"copper_inferno:tindergrit_wall", 6, "Craft 6x Tindergrit Wall at a crafting table.", "Stellt 6x Zundergrusmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_tindergrit_slab", "copper_inferno:polished_tindergrit_slab", "pyrestone/polished_tindergrit_slab",
				new String[] {"copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit", "", "", "", "", "", ""},
				"copper_inferno:polished_tindergrit_slab", 6, "Craft 6x Polished Tindergrit Slab at a crafting table.", "Stellt 6x Polierte Zundergrusstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_tindergrit_stairs", "copper_inferno:polished_tindergrit_stairs", "pyrestone/polished_tindergrit_stairs",
				new String[] {"copper_inferno:polished_tindergrit", "", "", "copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit", "", "copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit"},
				"copper_inferno:polished_tindergrit_stairs", 4, "Craft 4x Polished Tindergrit Stairs at a crafting table.", "Stellt 4x Polierte Zundergrustreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_tindergrit_wall", "copper_inferno:polished_tindergrit_wall", "pyrestone/polished_tindergrit_wall",
				new String[] {"copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit", "copper_inferno:polished_tindergrit", "", "", ""},
				"copper_inferno:polished_tindergrit_wall", 6, "Craft 6x Polished Tindergrit Wall at a crafting table.", "Stellt 6x Polierte Zundergrusmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_brick_slab", "copper_inferno:tindergrit_brick_slab", "pyrestone/tindergrit_brick_slab",
				new String[] {"copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "", "", "", "", "", ""},
				"copper_inferno:tindergrit_brick_slab", 6, "Craft 6x Tindergrit Brick Slab at a crafting table.", "Stellt 6x Zundergrusziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_brick_stairs", "copper_inferno:tindergrit_brick_stairs", "pyrestone/tindergrit_brick_stairs",
				new String[] {"copper_inferno:tindergrit_bricks", "", "", "copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "", "copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks"},
				"copper_inferno:tindergrit_brick_stairs", 4, "Craft 4x Tindergrit Brick Stairs at a crafting table.", "Stellt 4x Zundergrusziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_brick_wall", "copper_inferno:tindergrit_brick_wall", "pyrestone/tindergrit_brick_wall",
				new String[] {"copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "copper_inferno:tindergrit_bricks", "", "", ""},
				"copper_inferno:tindergrit_brick_wall", 6, "Craft 6x Tindergrit Brick Wall at a crafting table.", "Stellt 6x Zundergrusziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_tindergrit_bricks", "copper_inferno:cracked_tindergrit_bricks", "pyrestone/cracked_tindergrit_bricks",
				new String[] {"", "", "", "", "copper_inferno:tindergrit_bricks", "", "", "", ""},
				"copper_inferno:cracked_tindergrit_bricks", 1, "Smelting Tindergrit Bricks in a furnace yields Cracked Tindergrit Bricks.", "Zundergrusziegel im Ofen gebrannt ergibt Rissige Zundergrusziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_tindergrit_bricks", "copper_inferno:chiseled_tindergrit_bricks", "pyrestone/chiseled_tindergrit_bricks",
				new String[] {"copper_inferno:tindergrit_brick_slab", "", "", "copper_inferno:tindergrit_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_tindergrit_bricks", 1, "Craft 1x Chiseled Tindergrit Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Zundergrusziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_pillar", "copper_inferno:tindergrit_pillar", "pyrestone/tindergrit_pillar",
				new String[] {"copper_inferno:tindergrit_bricks", "", "", "copper_inferno:tindergrit_bricks", "", "", "", "", ""},
				"copper_inferno:tindergrit_pillar", 2, "Craft 2x Tindergrit Pillar at a crafting table.", "Stellt 2x Zundergruss\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_slab_from_tindergrit_stonecutting", "copper_inferno:tindergrit_slab", "pyrestone/tindergrit_slab_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_slab", 2, "Stonecutting: cut 2x Tindergrit Slab from Tindergrit.", "Steins\u00e4ge: 2x Zundergrusstufe aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_stairs_from_tindergrit_stonecutting", "copper_inferno:tindergrit_stairs", "pyrestone/tindergrit_stairs_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_stairs", 1, "Stonecutting: cut 1x Tindergrit Stairs from Tindergrit.", "Steins\u00e4ge: 1x Zundergrustreppe aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_wall_from_tindergrit_stonecutting", "copper_inferno:tindergrit_wall", "pyrestone/tindergrit_wall_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_wall", 1, "Stonecutting: cut 1x Tindergrit Wall from Tindergrit.", "Steins\u00e4ge: 1x Zundergrusmauer aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_tindergrit_from_tindergrit_stonecutting", "copper_inferno:polished_tindergrit", "pyrestone/polished_tindergrit_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:polished_tindergrit", 1, "Stonecutting: cut 1x Polished Tindergrit from Tindergrit.", "Steins\u00e4ge: 1x Polierten Zundergrus aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_tindergrit_slab_from_tindergrit_stonecutting", "copper_inferno:polished_tindergrit_slab", "pyrestone/polished_tindergrit_slab_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:polished_tindergrit_slab", 2, "Stonecutting: cut 2x Polished Tindergrit Slab from Tindergrit.", "Steins\u00e4ge: 2x Polierte Zundergrusstufe aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_tindergrit_stairs_from_tindergrit_stonecutting", "copper_inferno:polished_tindergrit_stairs", "pyrestone/polished_tindergrit_stairs_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:polished_tindergrit_stairs", 1, "Stonecutting: cut 1x Polished Tindergrit Stairs from Tindergrit.", "Steins\u00e4ge: 1x Polierte Zundergrustreppe aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_tindergrit_wall_from_tindergrit_stonecutting", "copper_inferno:polished_tindergrit_wall", "pyrestone/polished_tindergrit_wall_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:polished_tindergrit_wall", 1, "Stonecutting: cut 1x Polished Tindergrit Wall from Tindergrit.", "Steins\u00e4ge: 1x Polierte Zundergrusmauer aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_bricks_from_tindergrit_stonecutting", "copper_inferno:tindergrit_bricks", "pyrestone/tindergrit_bricks_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_bricks", 1, "Stonecutting: cut 1x Tindergrit Bricks from Tindergrit.", "Steins\u00e4ge: 1x Zundergrusziegel aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_brick_slab_from_tindergrit_stonecutting", "copper_inferno:tindergrit_brick_slab", "pyrestone/tindergrit_brick_slab_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_brick_slab", 2, "Stonecutting: cut 2x Tindergrit Brick Slab from Tindergrit.", "Steins\u00e4ge: 2x Zundergrusziegelstufe aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_brick_stairs_from_tindergrit_stonecutting", "copper_inferno:tindergrit_brick_stairs", "pyrestone/tindergrit_brick_stairs_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_brick_stairs", 1, "Stonecutting: cut 1x Tindergrit Brick Stairs from Tindergrit.", "Steins\u00e4ge: 1x Zundergrusziegeltreppe aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_brick_wall_from_tindergrit_stonecutting", "copper_inferno:tindergrit_brick_wall", "pyrestone/tindergrit_brick_wall_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_brick_wall", 1, "Stonecutting: cut 1x Tindergrit Brick Wall from Tindergrit.", "Steins\u00e4ge: 1x Zundergrusziegelmauer aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_tiles_from_tindergrit_stonecutting", "copper_inferno:tindergrit_tiles", "pyrestone/tindergrit_tiles_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_tiles", 1, "Stonecutting: cut 1x Tindergrit Tiles from Tindergrit.", "Steins\u00e4ge: 1x Zundergrusfliesen aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_tindergrit_bricks_from_tindergrit_stonecutting", "copper_inferno:chiseled_tindergrit_bricks", "pyrestone/chiseled_tindergrit_bricks_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:chiseled_tindergrit_bricks", 1, "Stonecutting: cut 1x Chiseled Tindergrit Bricks from Tindergrit.", "Steins\u00e4ge: 1x Gemei\u00dfelte Zundergrusziegel aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/tindergrit_pillar_from_tindergrit_stonecutting", "copper_inferno:tindergrit_pillar", "pyrestone/tindergrit_pillar_from_tindergrit_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:tindergrit", "", "", "", ""},
				"copper_inferno:tindergrit_pillar", 1, "Stonecutting: cut 1x Tindergrit Pillar from Tindergrit.", "Steins\u00e4ge: 1x Zundergruss\u00e4ule aus Zundergrus schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt", "copper_inferno:flarebasalt", "pyrestone/flarebasalt",
				new String[] {"minecraft:basalt", "", "minecraft:basalt", "", "copper_inferno:tindergrit", "", "minecraft:basalt", "", "minecraft:basalt"},
				"copper_inferno:flarebasalt", 4, "Craft 4x Flarebasalt at a crafting table.", "Stellt 4x Flackerbasalt an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "pyrestone/flarebasalt_bricks",
				new String[] {"copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "", "copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_bricks", 4, "Craft 4x Flarebasalt Bricks at a crafting table.", "Stellt 4x Flackerbasaltziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_tiles", "copper_inferno:flarebasalt_tiles", "pyrestone/flarebasalt_tiles",
				new String[] {"copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "", "copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "", "", "", ""},
				"copper_inferno:flarebasalt_tiles", 4, "Craft 4x Flarebasalt Tiles at a crafting table.", "Stellt 4x Flackerbasaltfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_flarebasalt", "copper_inferno:polished_flarebasalt", "pyrestone/polished_flarebasalt",
				new String[] {"copper_inferno:flarebasalt_tiles", "copper_inferno:flarebasalt_tiles", "", "copper_inferno:flarebasalt_tiles", "copper_inferno:flarebasalt_tiles", "", "", "", ""},
				"copper_inferno:polished_flarebasalt", 4, "Craft 4x Polished Flarebasalt at a crafting table.", "Stellt 4x Polierten Flackerbasalt an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_slab", "copper_inferno:flarebasalt_slab", "pyrestone/flarebasalt_slab",
				new String[] {"copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "", "", "", "", "", ""},
				"copper_inferno:flarebasalt_slab", 6, "Craft 6x Flarebasalt Slab at a crafting table.", "Stellt 6x Flackerbasaltstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_stairs", "copper_inferno:flarebasalt_stairs", "pyrestone/flarebasalt_stairs",
				new String[] {"copper_inferno:flarebasalt", "", "", "copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "", "copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "copper_inferno:flarebasalt"},
				"copper_inferno:flarebasalt_stairs", 4, "Craft 4x Flarebasalt Stairs at a crafting table.", "Stellt 4x Flackerbasalttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_wall", "copper_inferno:flarebasalt_wall", "pyrestone/flarebasalt_wall",
				new String[] {"copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "copper_inferno:flarebasalt", "", "", ""},
				"copper_inferno:flarebasalt_wall", 6, "Craft 6x Flarebasalt Wall at a crafting table.", "Stellt 6x Flackerbasaltmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_flarebasalt_slab", "copper_inferno:polished_flarebasalt_slab", "pyrestone/polished_flarebasalt_slab",
				new String[] {"copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt", "", "", "", "", "", ""},
				"copper_inferno:polished_flarebasalt_slab", 6, "Craft 6x Polished Flarebasalt Slab at a crafting table.", "Stellt 6x Polierte Flackerbasaltstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_flarebasalt_stairs", "copper_inferno:polished_flarebasalt_stairs", "pyrestone/polished_flarebasalt_stairs",
				new String[] {"copper_inferno:polished_flarebasalt", "", "", "copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt", "", "copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt"},
				"copper_inferno:polished_flarebasalt_stairs", 4, "Craft 4x Polished Flarebasalt Stairs at a crafting table.", "Stellt 4x Polierte Flackerbasalttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_flarebasalt_wall", "copper_inferno:polished_flarebasalt_wall", "pyrestone/polished_flarebasalt_wall",
				new String[] {"copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt", "copper_inferno:polished_flarebasalt", "", "", ""},
				"copper_inferno:polished_flarebasalt_wall", 6, "Craft 6x Polished Flarebasalt Wall at a crafting table.", "Stellt 6x Polierte Flackerbasaltmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_brick_slab", "copper_inferno:flarebasalt_brick_slab", "pyrestone/flarebasalt_brick_slab",
				new String[] {"copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "", "", "", "", "", ""},
				"copper_inferno:flarebasalt_brick_slab", 6, "Craft 6x Flarebasalt Brick Slab at a crafting table.", "Stellt 6x Flackerbasaltziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_brick_stairs", "copper_inferno:flarebasalt_brick_stairs", "pyrestone/flarebasalt_brick_stairs",
				new String[] {"copper_inferno:flarebasalt_bricks", "", "", "copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "", "copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks"},
				"copper_inferno:flarebasalt_brick_stairs", 4, "Craft 4x Flarebasalt Brick Stairs at a crafting table.", "Stellt 4x Flackerbasaltziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_brick_wall", "copper_inferno:flarebasalt_brick_wall", "pyrestone/flarebasalt_brick_wall",
				new String[] {"copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "copper_inferno:flarebasalt_bricks", "", "", ""},
				"copper_inferno:flarebasalt_brick_wall", 6, "Craft 6x Flarebasalt Brick Wall at a crafting table.", "Stellt 6x Flackerbasaltziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_flarebasalt_bricks", "copper_inferno:cracked_flarebasalt_bricks", "pyrestone/cracked_flarebasalt_bricks",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt_bricks", "", "", "", ""},
				"copper_inferno:cracked_flarebasalt_bricks", 1, "Smelting Flarebasalt Bricks in a furnace yields Cracked Flarebasalt Bricks.", "Flackerbasaltziegel im Ofen gebrannt ergibt Rissige Flackerbasaltziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_flarebasalt_bricks", "copper_inferno:chiseled_flarebasalt_bricks", "pyrestone/chiseled_flarebasalt_bricks",
				new String[] {"copper_inferno:flarebasalt_brick_slab", "", "", "copper_inferno:flarebasalt_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_flarebasalt_bricks", 1, "Craft 1x Chiseled Flarebasalt Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Flackerbasaltziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_pillar", "copper_inferno:flarebasalt_pillar", "pyrestone/flarebasalt_pillar",
				new String[] {"copper_inferno:flarebasalt_bricks", "", "", "copper_inferno:flarebasalt_bricks", "", "", "", "", ""},
				"copper_inferno:flarebasalt_pillar", 2, "Craft 2x Flarebasalt Pillar at a crafting table.", "Stellt 2x Flackerbasalts\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_slab_from_flarebasalt_stonecutting", "copper_inferno:flarebasalt_slab", "pyrestone/flarebasalt_slab_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_slab", 2, "Stonecutting: cut 2x Flarebasalt Slab from Flarebasalt.", "Steins\u00e4ge: 2x Flackerbasaltstufe aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_stairs_from_flarebasalt_stonecutting", "copper_inferno:flarebasalt_stairs", "pyrestone/flarebasalt_stairs_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_stairs", 1, "Stonecutting: cut 1x Flarebasalt Stairs from Flarebasalt.", "Steins\u00e4ge: 1x Flackerbasalttreppe aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_wall_from_flarebasalt_stonecutting", "copper_inferno:flarebasalt_wall", "pyrestone/flarebasalt_wall_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_wall", 1, "Stonecutting: cut 1x Flarebasalt Wall from Flarebasalt.", "Steins\u00e4ge: 1x Flackerbasaltmauer aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_flarebasalt_from_flarebasalt_stonecutting", "copper_inferno:polished_flarebasalt", "pyrestone/polished_flarebasalt_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:polished_flarebasalt", 1, "Stonecutting: cut 1x Polished Flarebasalt from Flarebasalt.", "Steins\u00e4ge: 1x Polierten Flackerbasalt aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_flarebasalt_slab_from_flarebasalt_stonecutting", "copper_inferno:polished_flarebasalt_slab", "pyrestone/polished_flarebasalt_slab_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:polished_flarebasalt_slab", 2, "Stonecutting: cut 2x Polished Flarebasalt Slab from Flarebasalt.", "Steins\u00e4ge: 2x Polierte Flackerbasaltstufe aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_flarebasalt_stairs_from_flarebasalt_stonecutting", "copper_inferno:polished_flarebasalt_stairs", "pyrestone/polished_flarebasalt_stairs_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:polished_flarebasalt_stairs", 1, "Stonecutting: cut 1x Polished Flarebasalt Stairs from Flarebasalt.", "Steins\u00e4ge: 1x Polierte Flackerbasalttreppe aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_flarebasalt_wall_from_flarebasalt_stonecutting", "copper_inferno:polished_flarebasalt_wall", "pyrestone/polished_flarebasalt_wall_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:polished_flarebasalt_wall", 1, "Stonecutting: cut 1x Polished Flarebasalt Wall from Flarebasalt.", "Steins\u00e4ge: 1x Polierte Flackerbasaltmauer aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_bricks_from_flarebasalt_stonecutting", "copper_inferno:flarebasalt_bricks", "pyrestone/flarebasalt_bricks_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_bricks", 1, "Stonecutting: cut 1x Flarebasalt Bricks from Flarebasalt.", "Steins\u00e4ge: 1x Flackerbasaltziegel aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_brick_slab_from_flarebasalt_stonecutting", "copper_inferno:flarebasalt_brick_slab", "pyrestone/flarebasalt_brick_slab_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_brick_slab", 2, "Stonecutting: cut 2x Flarebasalt Brick Slab from Flarebasalt.", "Steins\u00e4ge: 2x Flackerbasaltziegelstufe aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_brick_stairs_from_flarebasalt_stonecutting", "copper_inferno:flarebasalt_brick_stairs", "pyrestone/flarebasalt_brick_stairs_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_brick_stairs", 1, "Stonecutting: cut 1x Flarebasalt Brick Stairs from Flarebasalt.", "Steins\u00e4ge: 1x Flackerbasaltziegeltreppe aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_brick_wall_from_flarebasalt_stonecutting", "copper_inferno:flarebasalt_brick_wall", "pyrestone/flarebasalt_brick_wall_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_brick_wall", 1, "Stonecutting: cut 1x Flarebasalt Brick Wall from Flarebasalt.", "Steins\u00e4ge: 1x Flackerbasaltziegelmauer aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_tiles_from_flarebasalt_stonecutting", "copper_inferno:flarebasalt_tiles", "pyrestone/flarebasalt_tiles_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_tiles", 1, "Stonecutting: cut 1x Flarebasalt Tiles from Flarebasalt.", "Steins\u00e4ge: 1x Flackerbasaltfliesen aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_flarebasalt_bricks_from_flarebasalt_stonecutting", "copper_inferno:chiseled_flarebasalt_bricks", "pyrestone/chiseled_flarebasalt_bricks_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:chiseled_flarebasalt_bricks", 1, "Stonecutting: cut 1x Chiseled Flarebasalt Bricks from Flarebasalt.", "Steins\u00e4ge: 1x Gemei\u00dfelte Flackerbasaltziegel aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/flarebasalt_pillar_from_flarebasalt_stonecutting", "copper_inferno:flarebasalt_pillar", "pyrestone/flarebasalt_pillar_from_flarebasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flarebasalt", "", "", "", ""},
				"copper_inferno:flarebasalt_pillar", 1, "Stonecutting: cut 1x Flarebasalt Pillar from Flarebasalt.", "Steins\u00e4ge: 1x Flackerbasalts\u00e4ule aus Flackerbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite", "copper_inferno:magmite", "pyrestone/magmite",
				new String[] {"minecraft:magma_block", "", "minecraft:magma_block", "", "copper_inferno:flarebasalt", "", "minecraft:magma_block", "", "minecraft:magma_block"},
				"copper_inferno:magmite", 4, "Craft 4x Magmite at a crafting table.", "Stellt 4x Magmit an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_bricks", "copper_inferno:magmite_bricks", "pyrestone/magmite_bricks",
				new String[] {"copper_inferno:magmite", "copper_inferno:magmite", "", "copper_inferno:magmite", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_bricks", 4, "Craft 4x Magmite Bricks at a crafting table.", "Stellt 4x Magmitziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_tiles", "copper_inferno:magmite_tiles", "pyrestone/magmite_tiles",
				new String[] {"copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "", "copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "", "", "", ""},
				"copper_inferno:magmite_tiles", 4, "Craft 4x Magmite Tiles at a crafting table.", "Stellt 4x Magmitfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_magmite", "copper_inferno:polished_magmite", "pyrestone/polished_magmite",
				new String[] {"copper_inferno:magmite_tiles", "copper_inferno:magmite_tiles", "", "copper_inferno:magmite_tiles", "copper_inferno:magmite_tiles", "", "", "", ""},
				"copper_inferno:polished_magmite", 4, "Craft 4x Polished Magmite at a crafting table.", "Stellt 4x Polierten Magmit an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_slab", "copper_inferno:magmite_slab", "pyrestone/magmite_slab",
				new String[] {"copper_inferno:magmite", "copper_inferno:magmite", "copper_inferno:magmite", "", "", "", "", "", ""},
				"copper_inferno:magmite_slab", 6, "Craft 6x Magmite Slab at a crafting table.", "Stellt 6x Magmitstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_stairs", "copper_inferno:magmite_stairs", "pyrestone/magmite_stairs",
				new String[] {"copper_inferno:magmite", "", "", "copper_inferno:magmite", "copper_inferno:magmite", "", "copper_inferno:magmite", "copper_inferno:magmite", "copper_inferno:magmite"},
				"copper_inferno:magmite_stairs", 4, "Craft 4x Magmite Stairs at a crafting table.", "Stellt 4x Magmittreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_wall", "copper_inferno:magmite_wall", "pyrestone/magmite_wall",
				new String[] {"copper_inferno:magmite", "copper_inferno:magmite", "copper_inferno:magmite", "copper_inferno:magmite", "copper_inferno:magmite", "copper_inferno:magmite", "", "", ""},
				"copper_inferno:magmite_wall", 6, "Craft 6x Magmite Wall at a crafting table.", "Stellt 6x Magmitmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_magmite_slab", "copper_inferno:polished_magmite_slab", "pyrestone/polished_magmite_slab",
				new String[] {"copper_inferno:polished_magmite", "copper_inferno:polished_magmite", "copper_inferno:polished_magmite", "", "", "", "", "", ""},
				"copper_inferno:polished_magmite_slab", 6, "Craft 6x Polished Magmite Slab at a crafting table.", "Stellt 6x Polierte Magmitstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_magmite_stairs", "copper_inferno:polished_magmite_stairs", "pyrestone/polished_magmite_stairs",
				new String[] {"copper_inferno:polished_magmite", "", "", "copper_inferno:polished_magmite", "copper_inferno:polished_magmite", "", "copper_inferno:polished_magmite", "copper_inferno:polished_magmite", "copper_inferno:polished_magmite"},
				"copper_inferno:polished_magmite_stairs", 4, "Craft 4x Polished Magmite Stairs at a crafting table.", "Stellt 4x Polierte Magmittreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_magmite_wall", "copper_inferno:polished_magmite_wall", "pyrestone/polished_magmite_wall",
				new String[] {"copper_inferno:polished_magmite", "copper_inferno:polished_magmite", "copper_inferno:polished_magmite", "copper_inferno:polished_magmite", "copper_inferno:polished_magmite", "copper_inferno:polished_magmite", "", "", ""},
				"copper_inferno:polished_magmite_wall", 6, "Craft 6x Polished Magmite Wall at a crafting table.", "Stellt 6x Polierte Magmitmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_brick_slab", "copper_inferno:magmite_brick_slab", "pyrestone/magmite_brick_slab",
				new String[] {"copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "", "", "", "", "", ""},
				"copper_inferno:magmite_brick_slab", 6, "Craft 6x Magmite Brick Slab at a crafting table.", "Stellt 6x Magmitziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_brick_stairs", "copper_inferno:magmite_brick_stairs", "pyrestone/magmite_brick_stairs",
				new String[] {"copper_inferno:magmite_bricks", "", "", "copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "", "copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks"},
				"copper_inferno:magmite_brick_stairs", 4, "Craft 4x Magmite Brick Stairs at a crafting table.", "Stellt 4x Magmitziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_brick_wall", "copper_inferno:magmite_brick_wall", "pyrestone/magmite_brick_wall",
				new String[] {"copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "copper_inferno:magmite_bricks", "", "", ""},
				"copper_inferno:magmite_brick_wall", 6, "Craft 6x Magmite Brick Wall at a crafting table.", "Stellt 6x Magmitziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_magmite_bricks", "copper_inferno:cracked_magmite_bricks", "pyrestone/cracked_magmite_bricks",
				new String[] {"", "", "", "", "copper_inferno:magmite_bricks", "", "", "", ""},
				"copper_inferno:cracked_magmite_bricks", 1, "Smelting Magmite Bricks in a furnace yields Cracked Magmite Bricks.", "Magmitziegel im Ofen gebrannt ergibt Rissige Magmitziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_magmite_bricks", "copper_inferno:chiseled_magmite_bricks", "pyrestone/chiseled_magmite_bricks",
				new String[] {"copper_inferno:magmite_brick_slab", "", "", "copper_inferno:magmite_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_magmite_bricks", 1, "Craft 1x Chiseled Magmite Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Magmitziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_pillar", "copper_inferno:magmite_pillar", "pyrestone/magmite_pillar",
				new String[] {"copper_inferno:magmite_bricks", "", "", "copper_inferno:magmite_bricks", "", "", "", "", ""},
				"copper_inferno:magmite_pillar", 2, "Craft 2x Magmite Pillar at a crafting table.", "Stellt 2x Magmits\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_slab_from_magmite_stonecutting", "copper_inferno:magmite_slab", "pyrestone/magmite_slab_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_slab", 2, "Stonecutting: cut 2x Magmite Slab from Magmite.", "Steins\u00e4ge: 2x Magmitstufe aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_stairs_from_magmite_stonecutting", "copper_inferno:magmite_stairs", "pyrestone/magmite_stairs_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_stairs", 1, "Stonecutting: cut 1x Magmite Stairs from Magmite.", "Steins\u00e4ge: 1x Magmittreppe aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_wall_from_magmite_stonecutting", "copper_inferno:magmite_wall", "pyrestone/magmite_wall_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_wall", 1, "Stonecutting: cut 1x Magmite Wall from Magmite.", "Steins\u00e4ge: 1x Magmitmauer aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_magmite_from_magmite_stonecutting", "copper_inferno:polished_magmite", "pyrestone/polished_magmite_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:polished_magmite", 1, "Stonecutting: cut 1x Polished Magmite from Magmite.", "Steins\u00e4ge: 1x Polierten Magmit aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_magmite_slab_from_magmite_stonecutting", "copper_inferno:polished_magmite_slab", "pyrestone/polished_magmite_slab_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:polished_magmite_slab", 2, "Stonecutting: cut 2x Polished Magmite Slab from Magmite.", "Steins\u00e4ge: 2x Polierte Magmitstufe aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_magmite_stairs_from_magmite_stonecutting", "copper_inferno:polished_magmite_stairs", "pyrestone/polished_magmite_stairs_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:polished_magmite_stairs", 1, "Stonecutting: cut 1x Polished Magmite Stairs from Magmite.", "Steins\u00e4ge: 1x Polierte Magmittreppe aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_magmite_wall_from_magmite_stonecutting", "copper_inferno:polished_magmite_wall", "pyrestone/polished_magmite_wall_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:polished_magmite_wall", 1, "Stonecutting: cut 1x Polished Magmite Wall from Magmite.", "Steins\u00e4ge: 1x Polierte Magmitmauer aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_bricks_from_magmite_stonecutting", "copper_inferno:magmite_bricks", "pyrestone/magmite_bricks_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_bricks", 1, "Stonecutting: cut 1x Magmite Bricks from Magmite.", "Steins\u00e4ge: 1x Magmitziegel aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_brick_slab_from_magmite_stonecutting", "copper_inferno:magmite_brick_slab", "pyrestone/magmite_brick_slab_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_brick_slab", 2, "Stonecutting: cut 2x Magmite Brick Slab from Magmite.", "Steins\u00e4ge: 2x Magmitziegelstufe aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_brick_stairs_from_magmite_stonecutting", "copper_inferno:magmite_brick_stairs", "pyrestone/magmite_brick_stairs_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_brick_stairs", 1, "Stonecutting: cut 1x Magmite Brick Stairs from Magmite.", "Steins\u00e4ge: 1x Magmitziegeltreppe aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_brick_wall_from_magmite_stonecutting", "copper_inferno:magmite_brick_wall", "pyrestone/magmite_brick_wall_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_brick_wall", 1, "Stonecutting: cut 1x Magmite Brick Wall from Magmite.", "Steins\u00e4ge: 1x Magmitziegelmauer aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_tiles_from_magmite_stonecutting", "copper_inferno:magmite_tiles", "pyrestone/magmite_tiles_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_tiles", 1, "Stonecutting: cut 1x Magmite Tiles from Magmite.", "Steins\u00e4ge: 1x Magmitfliesen aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_magmite_bricks_from_magmite_stonecutting", "copper_inferno:chiseled_magmite_bricks", "pyrestone/chiseled_magmite_bricks_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:chiseled_magmite_bricks", 1, "Stonecutting: cut 1x Chiseled Magmite Bricks from Magmite.", "Steins\u00e4ge: 1x Gemei\u00dfelte Magmitziegel aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/magmite_pillar_from_magmite_stonecutting", "copper_inferno:magmite_pillar", "pyrestone/magmite_pillar_from_magmite_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magmite", "", "", "", ""},
				"copper_inferno:magmite_pillar", 1, "Stonecutting: cut 1x Magmite Pillar from Magmite.", "Steins\u00e4ge: 1x Magmits\u00e4ule aus Magmit schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock", "copper_inferno:fumarole_rock", "pyrestone/fumarole_rock",
				new String[] {"minecraft:andesite", "", "minecraft:andesite", "", "copper_inferno:magmite", "", "minecraft:andesite", "", "minecraft:andesite"},
				"copper_inferno:fumarole_rock", 4, "Craft 4x Fumarole Rock at a crafting table.", "Stellt 4x Fumarolenstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "pyrestone/fumarole_rock_bricks",
				new String[] {"copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "", "copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_bricks", 4, "Craft 4x Fumarole Rock Bricks at a crafting table.", "Stellt 4x Fumarolensteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_tiles", "copper_inferno:fumarole_rock_tiles", "pyrestone/fumarole_rock_tiles",
				new String[] {"copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "", "copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "", "", "", ""},
				"copper_inferno:fumarole_rock_tiles", 4, "Craft 4x Fumarole Rock Tiles at a crafting table.", "Stellt 4x Fumarolensteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "pyrestone/polished_fumarole_rock",
				new String[] {"copper_inferno:fumarole_rock_tiles", "copper_inferno:fumarole_rock_tiles", "", "copper_inferno:fumarole_rock_tiles", "copper_inferno:fumarole_rock_tiles", "", "", "", ""},
				"copper_inferno:polished_fumarole_rock", 4, "Craft 4x Polished Fumarole Rock at a crafting table.", "Stellt 4x Polierten Fumarolenstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_slab", "copper_inferno:fumarole_rock_slab", "pyrestone/fumarole_rock_slab",
				new String[] {"copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "", "", "", "", "", ""},
				"copper_inferno:fumarole_rock_slab", 6, "Craft 6x Fumarole Rock Slab at a crafting table.", "Stellt 6x Fumarolensteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_stairs", "copper_inferno:fumarole_rock_stairs", "pyrestone/fumarole_rock_stairs",
				new String[] {"copper_inferno:fumarole_rock", "", "", "copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "", "copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock"},
				"copper_inferno:fumarole_rock_stairs", 4, "Craft 4x Fumarole Rock Stairs at a crafting table.", "Stellt 4x Fumarolensteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_wall", "copper_inferno:fumarole_rock_wall", "pyrestone/fumarole_rock_wall",
				new String[] {"copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "copper_inferno:fumarole_rock", "", "", ""},
				"copper_inferno:fumarole_rock_wall", 6, "Craft 6x Fumarole Rock Wall at a crafting table.", "Stellt 6x Fumarolensteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_fumarole_rock_slab", "copper_inferno:polished_fumarole_rock_slab", "pyrestone/polished_fumarole_rock_slab",
				new String[] {"copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "", "", "", "", "", ""},
				"copper_inferno:polished_fumarole_rock_slab", 6, "Craft 6x Polished Fumarole Rock Slab at a crafting table.", "Stellt 6x Polierte Fumarolensteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_fumarole_rock_stairs", "copper_inferno:polished_fumarole_rock_stairs", "pyrestone/polished_fumarole_rock_stairs",
				new String[] {"copper_inferno:polished_fumarole_rock", "", "", "copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "", "copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock"},
				"copper_inferno:polished_fumarole_rock_stairs", 4, "Craft 4x Polished Fumarole Rock Stairs at a crafting table.", "Stellt 4x Polierte Fumarolensteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_fumarole_rock_wall", "copper_inferno:polished_fumarole_rock_wall", "pyrestone/polished_fumarole_rock_wall",
				new String[] {"copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "copper_inferno:polished_fumarole_rock", "", "", ""},
				"copper_inferno:polished_fumarole_rock_wall", 6, "Craft 6x Polished Fumarole Rock Wall at a crafting table.", "Stellt 6x Polierte Fumarolensteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_brick_slab", "copper_inferno:fumarole_rock_brick_slab", "pyrestone/fumarole_rock_brick_slab",
				new String[] {"copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "", "", "", "", "", ""},
				"copper_inferno:fumarole_rock_brick_slab", 6, "Craft 6x Fumarole Rock Brick Slab at a crafting table.", "Stellt 6x Fumarolensteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_brick_stairs", "copper_inferno:fumarole_rock_brick_stairs", "pyrestone/fumarole_rock_brick_stairs",
				new String[] {"copper_inferno:fumarole_rock_bricks", "", "", "copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "", "copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks"},
				"copper_inferno:fumarole_rock_brick_stairs", 4, "Craft 4x Fumarole Rock Brick Stairs at a crafting table.", "Stellt 4x Fumarolensteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_brick_wall", "copper_inferno:fumarole_rock_brick_wall", "pyrestone/fumarole_rock_brick_wall",
				new String[] {"copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "copper_inferno:fumarole_rock_bricks", "", "", ""},
				"copper_inferno:fumarole_rock_brick_wall", 6, "Craft 6x Fumarole Rock Brick Wall at a crafting table.", "Stellt 6x Fumarolensteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_fumarole_rock_bricks", "copper_inferno:cracked_fumarole_rock_bricks", "pyrestone/cracked_fumarole_rock_bricks",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock_bricks", "", "", "", ""},
				"copper_inferno:cracked_fumarole_rock_bricks", 1, "Smelting Fumarole Rock Bricks in a furnace yields Cracked Fumarole Rock Bricks.", "Fumarolensteinziegel im Ofen gebrannt ergibt Rissige Fumarolensteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_fumarole_rock_bricks", "copper_inferno:chiseled_fumarole_rock_bricks", "pyrestone/chiseled_fumarole_rock_bricks",
				new String[] {"copper_inferno:fumarole_rock_brick_slab", "", "", "copper_inferno:fumarole_rock_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_fumarole_rock_bricks", 1, "Craft 1x Chiseled Fumarole Rock Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Fumarolensteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_pillar", "copper_inferno:fumarole_rock_pillar", "pyrestone/fumarole_rock_pillar",
				new String[] {"copper_inferno:fumarole_rock_bricks", "", "", "copper_inferno:fumarole_rock_bricks", "", "", "", "", ""},
				"copper_inferno:fumarole_rock_pillar", 2, "Craft 2x Fumarole Rock Pillar at a crafting table.", "Stellt 2x Fumarolensteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_slab_from_fumarole_rock_stonecutting", "copper_inferno:fumarole_rock_slab", "pyrestone/fumarole_rock_slab_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_slab", 2, "Stonecutting: cut 2x Fumarole Rock Slab from Fumarole Rock.", "Steins\u00e4ge: 2x Fumarolensteinstufe aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_stairs_from_fumarole_rock_stonecutting", "copper_inferno:fumarole_rock_stairs", "pyrestone/fumarole_rock_stairs_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_stairs", 1, "Stonecutting: cut 1x Fumarole Rock Stairs from Fumarole Rock.", "Steins\u00e4ge: 1x Fumarolensteintreppe aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_wall_from_fumarole_rock_stonecutting", "copper_inferno:fumarole_rock_wall", "pyrestone/fumarole_rock_wall_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_wall", 1, "Stonecutting: cut 1x Fumarole Rock Wall from Fumarole Rock.", "Steins\u00e4ge: 1x Fumarolensteinmauer aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_fumarole_rock_from_fumarole_rock_stonecutting", "copper_inferno:polished_fumarole_rock", "pyrestone/polished_fumarole_rock_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:polished_fumarole_rock", 1, "Stonecutting: cut 1x Polished Fumarole Rock from Fumarole Rock.", "Steins\u00e4ge: 1x Polierten Fumarolenstein aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_fumarole_rock_slab_from_fumarole_rock_stonecutting", "copper_inferno:polished_fumarole_rock_slab", "pyrestone/polished_fumarole_rock_slab_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:polished_fumarole_rock_slab", 2, "Stonecutting: cut 2x Polished Fumarole Rock Slab from Fumarole Rock.", "Steins\u00e4ge: 2x Polierte Fumarolensteinstufe aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_fumarole_rock_stairs_from_fumarole_rock_stonecutting", "copper_inferno:polished_fumarole_rock_stairs", "pyrestone/polished_fumarole_rock_stairs_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:polished_fumarole_rock_stairs", 1, "Stonecutting: cut 1x Polished Fumarole Rock Stairs from Fumarole Rock.", "Steins\u00e4ge: 1x Polierte Fumarolensteintreppe aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_fumarole_rock_wall_from_fumarole_rock_stonecutting", "copper_inferno:polished_fumarole_rock_wall", "pyrestone/polished_fumarole_rock_wall_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:polished_fumarole_rock_wall", 1, "Stonecutting: cut 1x Polished Fumarole Rock Wall from Fumarole Rock.", "Steins\u00e4ge: 1x Polierte Fumarolensteinmauer aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_bricks_from_fumarole_rock_stonecutting", "copper_inferno:fumarole_rock_bricks", "pyrestone/fumarole_rock_bricks_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_bricks", 1, "Stonecutting: cut 1x Fumarole Rock Bricks from Fumarole Rock.", "Steins\u00e4ge: 1x Fumarolensteinziegel aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_brick_slab_from_fumarole_rock_stonecutting", "copper_inferno:fumarole_rock_brick_slab", "pyrestone/fumarole_rock_brick_slab_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_brick_slab", 2, "Stonecutting: cut 2x Fumarole Rock Brick Slab from Fumarole Rock.", "Steins\u00e4ge: 2x Fumarolensteinziegelstufe aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_brick_stairs_from_fumarole_rock_stonecutting", "copper_inferno:fumarole_rock_brick_stairs", "pyrestone/fumarole_rock_brick_stairs_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_brick_stairs", 1, "Stonecutting: cut 1x Fumarole Rock Brick Stairs from Fumarole Rock.", "Steins\u00e4ge: 1x Fumarolensteinziegeltreppe aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_brick_wall_from_fumarole_rock_stonecutting", "copper_inferno:fumarole_rock_brick_wall", "pyrestone/fumarole_rock_brick_wall_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_brick_wall", 1, "Stonecutting: cut 1x Fumarole Rock Brick Wall from Fumarole Rock.", "Steins\u00e4ge: 1x Fumarolensteinziegelmauer aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_tiles_from_fumarole_rock_stonecutting", "copper_inferno:fumarole_rock_tiles", "pyrestone/fumarole_rock_tiles_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_tiles", 1, "Stonecutting: cut 1x Fumarole Rock Tiles from Fumarole Rock.", "Steins\u00e4ge: 1x Fumarolensteinfliesen aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_fumarole_rock_bricks_from_fumarole_rock_stonecutting", "copper_inferno:chiseled_fumarole_rock_bricks", "pyrestone/chiseled_fumarole_rock_bricks_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:chiseled_fumarole_rock_bricks", 1, "Stonecutting: cut 1x Chiseled Fumarole Rock Bricks from Fumarole Rock.", "Steins\u00e4ge: 1x Gemei\u00dfelte Fumarolensteinziegel aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/fumarole_rock_pillar_from_fumarole_rock_stonecutting", "copper_inferno:fumarole_rock_pillar", "pyrestone/fumarole_rock_pillar_from_fumarole_rock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fumarole_rock", "", "", "", ""},
				"copper_inferno:fumarole_rock_pillar", 1, "Stonecutting: cut 1x Fumarole Rock Pillar from Fumarole Rock.", "Steins\u00e4ge: 1x Fumarolensteins\u00e4ule aus Fumarolenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone", "copper_inferno:pyroclast_stone", "pyrestone/pyroclast_stone",
				new String[] {"minecraft:cobblestone", "", "minecraft:cobblestone", "", "copper_inferno:fumarole_rock", "", "minecraft:cobblestone", "", "minecraft:cobblestone"},
				"copper_inferno:pyroclast_stone", 4, "Craft 4x Pyroclast Stone at a crafting table.", "Stellt 4x Pyroklaststein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "pyrestone/pyroclast_stone_bricks",
				new String[] {"copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "", "copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_bricks", 4, "Craft 4x Pyroclast Stone Bricks at a crafting table.", "Stellt 4x Pyroklaststeinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_tiles", "copper_inferno:pyroclast_stone_tiles", "pyrestone/pyroclast_stone_tiles",
				new String[] {"copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "", "copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "", "", "", ""},
				"copper_inferno:pyroclast_stone_tiles", 4, "Craft 4x Pyroclast Stone Tiles at a crafting table.", "Stellt 4x Pyroklaststeinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "pyrestone/polished_pyroclast_stone",
				new String[] {"copper_inferno:pyroclast_stone_tiles", "copper_inferno:pyroclast_stone_tiles", "", "copper_inferno:pyroclast_stone_tiles", "copper_inferno:pyroclast_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_pyroclast_stone", 4, "Craft 4x Polished Pyroclast Stone at a crafting table.", "Stellt 4x Polierten Pyroklaststein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_slab", "copper_inferno:pyroclast_stone_slab", "pyrestone/pyroclast_stone_slab",
				new String[] {"copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "", "", "", "", "", ""},
				"copper_inferno:pyroclast_stone_slab", 6, "Craft 6x Pyroclast Stone Slab at a crafting table.", "Stellt 6x Pyroklaststeinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_stairs", "copper_inferno:pyroclast_stone_stairs", "pyrestone/pyroclast_stone_stairs",
				new String[] {"copper_inferno:pyroclast_stone", "", "", "copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "", "copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone"},
				"copper_inferno:pyroclast_stone_stairs", 4, "Craft 4x Pyroclast Stone Stairs at a crafting table.", "Stellt 4x Pyroklaststeintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_wall", "copper_inferno:pyroclast_stone_wall", "pyrestone/pyroclast_stone_wall",
				new String[] {"copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "copper_inferno:pyroclast_stone", "", "", ""},
				"copper_inferno:pyroclast_stone_wall", 6, "Craft 6x Pyroclast Stone Wall at a crafting table.", "Stellt 6x Pyroklaststeinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyroclast_stone_slab", "copper_inferno:polished_pyroclast_stone_slab", "pyrestone/polished_pyroclast_stone_slab",
				new String[] {"copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_pyroclast_stone_slab", 6, "Craft 6x Polished Pyroclast Stone Slab at a crafting table.", "Stellt 6x Polierte Pyroklaststeinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyroclast_stone_stairs", "copper_inferno:polished_pyroclast_stone_stairs", "pyrestone/polished_pyroclast_stone_stairs",
				new String[] {"copper_inferno:polished_pyroclast_stone", "", "", "copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "", "copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone"},
				"copper_inferno:polished_pyroclast_stone_stairs", 4, "Craft 4x Polished Pyroclast Stone Stairs at a crafting table.", "Stellt 4x Polierte Pyroklaststeintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyroclast_stone_wall", "copper_inferno:polished_pyroclast_stone_wall", "pyrestone/polished_pyroclast_stone_wall",
				new String[] {"copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "copper_inferno:polished_pyroclast_stone", "", "", ""},
				"copper_inferno:polished_pyroclast_stone_wall", 6, "Craft 6x Polished Pyroclast Stone Wall at a crafting table.", "Stellt 6x Polierte Pyroklaststeinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_brick_slab", "copper_inferno:pyroclast_stone_brick_slab", "pyrestone/pyroclast_stone_brick_slab",
				new String[] {"copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:pyroclast_stone_brick_slab", 6, "Craft 6x Pyroclast Stone Brick Slab at a crafting table.", "Stellt 6x Pyroklaststeinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_brick_stairs", "copper_inferno:pyroclast_stone_brick_stairs", "pyrestone/pyroclast_stone_brick_stairs",
				new String[] {"copper_inferno:pyroclast_stone_bricks", "", "", "copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "", "copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks"},
				"copper_inferno:pyroclast_stone_brick_stairs", 4, "Craft 4x Pyroclast Stone Brick Stairs at a crafting table.", "Stellt 4x Pyroklaststeinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_brick_wall", "copper_inferno:pyroclast_stone_brick_wall", "pyrestone/pyroclast_stone_brick_wall",
				new String[] {"copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "copper_inferno:pyroclast_stone_bricks", "", "", ""},
				"copper_inferno:pyroclast_stone_brick_wall", 6, "Craft 6x Pyroclast Stone Brick Wall at a crafting table.", "Stellt 6x Pyroklaststeinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_pyroclast_stone_bricks", "copper_inferno:cracked_pyroclast_stone_bricks", "pyrestone/cracked_pyroclast_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_pyroclast_stone_bricks", 1, "Smelting Pyroclast Stone Bricks in a furnace yields Cracked Pyroclast Stone Bricks.", "Pyroklaststeinziegel im Ofen gebrannt ergibt Rissige Pyroklaststeinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_pyroclast_stone_bricks", "copper_inferno:chiseled_pyroclast_stone_bricks", "pyrestone/chiseled_pyroclast_stone_bricks",
				new String[] {"copper_inferno:pyroclast_stone_brick_slab", "", "", "copper_inferno:pyroclast_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_pyroclast_stone_bricks", 1, "Craft 1x Chiseled Pyroclast Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Pyroklaststeinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_pillar", "copper_inferno:pyroclast_stone_pillar", "pyrestone/pyroclast_stone_pillar",
				new String[] {"copper_inferno:pyroclast_stone_bricks", "", "", "copper_inferno:pyroclast_stone_bricks", "", "", "", "", ""},
				"copper_inferno:pyroclast_stone_pillar", 2, "Craft 2x Pyroclast Stone Pillar at a crafting table.", "Stellt 2x Pyroklaststeins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_slab_from_pyroclast_stone_stonecutting", "copper_inferno:pyroclast_stone_slab", "pyrestone/pyroclast_stone_slab_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_slab", 2, "Stonecutting: cut 2x Pyroclast Stone Slab from Pyroclast Stone.", "Steins\u00e4ge: 2x Pyroklaststeinstufe aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_stairs_from_pyroclast_stone_stonecutting", "copper_inferno:pyroclast_stone_stairs", "pyrestone/pyroclast_stone_stairs_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_stairs", 1, "Stonecutting: cut 1x Pyroclast Stone Stairs from Pyroclast Stone.", "Steins\u00e4ge: 1x Pyroklaststeintreppe aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_wall_from_pyroclast_stone_stonecutting", "copper_inferno:pyroclast_stone_wall", "pyrestone/pyroclast_stone_wall_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_wall", 1, "Stonecutting: cut 1x Pyroclast Stone Wall from Pyroclast Stone.", "Steins\u00e4ge: 1x Pyroklaststeinmauer aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyroclast_stone_from_pyroclast_stone_stonecutting", "copper_inferno:polished_pyroclast_stone", "pyrestone/polished_pyroclast_stone_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:polished_pyroclast_stone", 1, "Stonecutting: cut 1x Polished Pyroclast Stone from Pyroclast Stone.", "Steins\u00e4ge: 1x Polierten Pyroklaststein aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyroclast_stone_slab_from_pyroclast_stone_stonecutting", "copper_inferno:polished_pyroclast_stone_slab", "pyrestone/polished_pyroclast_stone_slab_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:polished_pyroclast_stone_slab", 2, "Stonecutting: cut 2x Polished Pyroclast Stone Slab from Pyroclast Stone.", "Steins\u00e4ge: 2x Polierte Pyroklaststeinstufe aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyroclast_stone_stairs_from_pyroclast_stone_stonecutting", "copper_inferno:polished_pyroclast_stone_stairs", "pyrestone/polished_pyroclast_stone_stairs_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:polished_pyroclast_stone_stairs", 1, "Stonecutting: cut 1x Polished Pyroclast Stone Stairs from Pyroclast Stone.", "Steins\u00e4ge: 1x Polierte Pyroklaststeintreppe aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_pyroclast_stone_wall_from_pyroclast_stone_stonecutting", "copper_inferno:polished_pyroclast_stone_wall", "pyrestone/polished_pyroclast_stone_wall_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:polished_pyroclast_stone_wall", 1, "Stonecutting: cut 1x Polished Pyroclast Stone Wall from Pyroclast Stone.", "Steins\u00e4ge: 1x Polierte Pyroklaststeinmauer aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_bricks_from_pyroclast_stone_stonecutting", "copper_inferno:pyroclast_stone_bricks", "pyrestone/pyroclast_stone_bricks_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_bricks", 1, "Stonecutting: cut 1x Pyroclast Stone Bricks from Pyroclast Stone.", "Steins\u00e4ge: 1x Pyroklaststeinziegel aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_brick_slab_from_pyroclast_stone_stonecutting", "copper_inferno:pyroclast_stone_brick_slab", "pyrestone/pyroclast_stone_brick_slab_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_brick_slab", 2, "Stonecutting: cut 2x Pyroclast Stone Brick Slab from Pyroclast Stone.", "Steins\u00e4ge: 2x Pyroklaststeinziegelstufe aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_brick_stairs_from_pyroclast_stone_stonecutting", "copper_inferno:pyroclast_stone_brick_stairs", "pyrestone/pyroclast_stone_brick_stairs_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_brick_stairs", 1, "Stonecutting: cut 1x Pyroclast Stone Brick Stairs from Pyroclast Stone.", "Steins\u00e4ge: 1x Pyroklaststeinziegeltreppe aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_brick_wall_from_pyroclast_stone_stonecutting", "copper_inferno:pyroclast_stone_brick_wall", "pyrestone/pyroclast_stone_brick_wall_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_brick_wall", 1, "Stonecutting: cut 1x Pyroclast Stone Brick Wall from Pyroclast Stone.", "Steins\u00e4ge: 1x Pyroklaststeinziegelmauer aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_tiles_from_pyroclast_stone_stonecutting", "copper_inferno:pyroclast_stone_tiles", "pyrestone/pyroclast_stone_tiles_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_tiles", 1, "Stonecutting: cut 1x Pyroclast Stone Tiles from Pyroclast Stone.", "Steins\u00e4ge: 1x Pyroklaststeinfliesen aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_pyroclast_stone_bricks_from_pyroclast_stone_stonecutting", "copper_inferno:chiseled_pyroclast_stone_bricks", "pyrestone/chiseled_pyroclast_stone_bricks_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:chiseled_pyroclast_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Pyroclast Stone Bricks from Pyroclast Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Pyroklaststeinziegel aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/pyroclast_stone_pillar_from_pyroclast_stone_stonecutting", "copper_inferno:pyroclast_stone_pillar", "pyrestone/pyroclast_stone_pillar_from_pyroclast_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroclast_stone", "", "", "", ""},
				"copper_inferno:pyroclast_stone_pillar", 1, "Stonecutting: cut 1x Pyroclast Stone Pillar from Pyroclast Stone.", "Steins\u00e4ge: 1x Pyroklaststeins\u00e4ule aus Pyroklaststein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash", "copper_inferno:vitrified_ash", "pyrestone/vitrified_ash",
				new String[] {"minecraft:calcite", "", "minecraft:calcite", "", "copper_inferno:pyroclast_stone", "", "minecraft:calcite", "", "minecraft:calcite"},
				"copper_inferno:vitrified_ash", 4, "Craft 4x Vitrified Ash at a crafting table.", "Stellt 4x Glasasche an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "pyrestone/vitrified_ash_bricks",
				new String[] {"copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "", "copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_bricks", 4, "Craft 4x Vitrified Ash Bricks at a crafting table.", "Stellt 4x Glasaschenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_tiles", "copper_inferno:vitrified_ash_tiles", "pyrestone/vitrified_ash_tiles",
				new String[] {"copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "", "copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "", "", "", ""},
				"copper_inferno:vitrified_ash_tiles", 4, "Craft 4x Vitrified Ash Tiles at a crafting table.", "Stellt 4x Glasaschenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "pyrestone/polished_vitrified_ash",
				new String[] {"copper_inferno:vitrified_ash_tiles", "copper_inferno:vitrified_ash_tiles", "", "copper_inferno:vitrified_ash_tiles", "copper_inferno:vitrified_ash_tiles", "", "", "", ""},
				"copper_inferno:polished_vitrified_ash", 4, "Craft 4x Polished Vitrified Ash at a crafting table.", "Stellt 4x Polierte Glasasche an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_slab", "copper_inferno:vitrified_ash_slab", "pyrestone/vitrified_ash_slab",
				new String[] {"copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "", "", "", "", "", ""},
				"copper_inferno:vitrified_ash_slab", 6, "Craft 6x Vitrified Ash Slab at a crafting table.", "Stellt 6x Glasaschenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_stairs", "copper_inferno:vitrified_ash_stairs", "pyrestone/vitrified_ash_stairs",
				new String[] {"copper_inferno:vitrified_ash", "", "", "copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "", "copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash"},
				"copper_inferno:vitrified_ash_stairs", 4, "Craft 4x Vitrified Ash Stairs at a crafting table.", "Stellt 4x Glasaschentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_wall", "copper_inferno:vitrified_ash_wall", "pyrestone/vitrified_ash_wall",
				new String[] {"copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "copper_inferno:vitrified_ash", "", "", ""},
				"copper_inferno:vitrified_ash_wall", 6, "Craft 6x Vitrified Ash Wall at a crafting table.", "Stellt 6x Glasaschenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_vitrified_ash_slab", "copper_inferno:polished_vitrified_ash_slab", "pyrestone/polished_vitrified_ash_slab",
				new String[] {"copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "", "", "", "", "", ""},
				"copper_inferno:polished_vitrified_ash_slab", 6, "Craft 6x Polished Vitrified Ash Slab at a crafting table.", "Stellt 6x Polierte Glasaschenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_vitrified_ash_stairs", "copper_inferno:polished_vitrified_ash_stairs", "pyrestone/polished_vitrified_ash_stairs",
				new String[] {"copper_inferno:polished_vitrified_ash", "", "", "copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "", "copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash"},
				"copper_inferno:polished_vitrified_ash_stairs", 4, "Craft 4x Polished Vitrified Ash Stairs at a crafting table.", "Stellt 4x Polierte Glasaschentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_vitrified_ash_wall", "copper_inferno:polished_vitrified_ash_wall", "pyrestone/polished_vitrified_ash_wall",
				new String[] {"copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "copper_inferno:polished_vitrified_ash", "", "", ""},
				"copper_inferno:polished_vitrified_ash_wall", 6, "Craft 6x Polished Vitrified Ash Wall at a crafting table.", "Stellt 6x Polierte Glasaschenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_brick_slab", "copper_inferno:vitrified_ash_brick_slab", "pyrestone/vitrified_ash_brick_slab",
				new String[] {"copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "", "", "", "", "", ""},
				"copper_inferno:vitrified_ash_brick_slab", 6, "Craft 6x Vitrified Ash Brick Slab at a crafting table.", "Stellt 6x Glasaschenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_brick_stairs", "copper_inferno:vitrified_ash_brick_stairs", "pyrestone/vitrified_ash_brick_stairs",
				new String[] {"copper_inferno:vitrified_ash_bricks", "", "", "copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "", "copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks"},
				"copper_inferno:vitrified_ash_brick_stairs", 4, "Craft 4x Vitrified Ash Brick Stairs at a crafting table.", "Stellt 4x Glasaschenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_brick_wall", "copper_inferno:vitrified_ash_brick_wall", "pyrestone/vitrified_ash_brick_wall",
				new String[] {"copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "copper_inferno:vitrified_ash_bricks", "", "", ""},
				"copper_inferno:vitrified_ash_brick_wall", 6, "Craft 6x Vitrified Ash Brick Wall at a crafting table.", "Stellt 6x Glasaschenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_vitrified_ash_bricks", "copper_inferno:cracked_vitrified_ash_bricks", "pyrestone/cracked_vitrified_ash_bricks",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash_bricks", "", "", "", ""},
				"copper_inferno:cracked_vitrified_ash_bricks", 1, "Smelting Vitrified Ash Bricks in a furnace yields Cracked Vitrified Ash Bricks.", "Glasaschenziegel im Ofen gebrannt ergibt Rissige Glasaschenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_vitrified_ash_bricks", "copper_inferno:chiseled_vitrified_ash_bricks", "pyrestone/chiseled_vitrified_ash_bricks",
				new String[] {"copper_inferno:vitrified_ash_brick_slab", "", "", "copper_inferno:vitrified_ash_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_vitrified_ash_bricks", 1, "Craft 1x Chiseled Vitrified Ash Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glasaschenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_pillar", "copper_inferno:vitrified_ash_pillar", "pyrestone/vitrified_ash_pillar",
				new String[] {"copper_inferno:vitrified_ash_bricks", "", "", "copper_inferno:vitrified_ash_bricks", "", "", "", "", ""},
				"copper_inferno:vitrified_ash_pillar", 2, "Craft 2x Vitrified Ash Pillar at a crafting table.", "Stellt 2x Glasaschens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_slab_from_vitrified_ash_stonecutting", "copper_inferno:vitrified_ash_slab", "pyrestone/vitrified_ash_slab_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_slab", 2, "Stonecutting: cut 2x Vitrified Ash Slab from Vitrified Ash.", "Steins\u00e4ge: 2x Glasaschenstufe aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_stairs_from_vitrified_ash_stonecutting", "copper_inferno:vitrified_ash_stairs", "pyrestone/vitrified_ash_stairs_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_stairs", 1, "Stonecutting: cut 1x Vitrified Ash Stairs from Vitrified Ash.", "Steins\u00e4ge: 1x Glasaschentreppe aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_wall_from_vitrified_ash_stonecutting", "copper_inferno:vitrified_ash_wall", "pyrestone/vitrified_ash_wall_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_wall", 1, "Stonecutting: cut 1x Vitrified Ash Wall from Vitrified Ash.", "Steins\u00e4ge: 1x Glasaschenmauer aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_vitrified_ash_from_vitrified_ash_stonecutting", "copper_inferno:polished_vitrified_ash", "pyrestone/polished_vitrified_ash_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:polished_vitrified_ash", 1, "Stonecutting: cut 1x Polished Vitrified Ash from Vitrified Ash.", "Steins\u00e4ge: 1x Polierte Glasasche aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_vitrified_ash_slab_from_vitrified_ash_stonecutting", "copper_inferno:polished_vitrified_ash_slab", "pyrestone/polished_vitrified_ash_slab_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:polished_vitrified_ash_slab", 2, "Stonecutting: cut 2x Polished Vitrified Ash Slab from Vitrified Ash.", "Steins\u00e4ge: 2x Polierte Glasaschenstufe aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_vitrified_ash_stairs_from_vitrified_ash_stonecutting", "copper_inferno:polished_vitrified_ash_stairs", "pyrestone/polished_vitrified_ash_stairs_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:polished_vitrified_ash_stairs", 1, "Stonecutting: cut 1x Polished Vitrified Ash Stairs from Vitrified Ash.", "Steins\u00e4ge: 1x Polierte Glasaschentreppe aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_vitrified_ash_wall_from_vitrified_ash_stonecutting", "copper_inferno:polished_vitrified_ash_wall", "pyrestone/polished_vitrified_ash_wall_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:polished_vitrified_ash_wall", 1, "Stonecutting: cut 1x Polished Vitrified Ash Wall from Vitrified Ash.", "Steins\u00e4ge: 1x Polierte Glasaschenmauer aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_bricks_from_vitrified_ash_stonecutting", "copper_inferno:vitrified_ash_bricks", "pyrestone/vitrified_ash_bricks_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_bricks", 1, "Stonecutting: cut 1x Vitrified Ash Bricks from Vitrified Ash.", "Steins\u00e4ge: 1x Glasaschenziegel aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_brick_slab_from_vitrified_ash_stonecutting", "copper_inferno:vitrified_ash_brick_slab", "pyrestone/vitrified_ash_brick_slab_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_brick_slab", 2, "Stonecutting: cut 2x Vitrified Ash Brick Slab from Vitrified Ash.", "Steins\u00e4ge: 2x Glasaschenziegelstufe aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_brick_stairs_from_vitrified_ash_stonecutting", "copper_inferno:vitrified_ash_brick_stairs", "pyrestone/vitrified_ash_brick_stairs_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_brick_stairs", 1, "Stonecutting: cut 1x Vitrified Ash Brick Stairs from Vitrified Ash.", "Steins\u00e4ge: 1x Glasaschenziegeltreppe aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_brick_wall_from_vitrified_ash_stonecutting", "copper_inferno:vitrified_ash_brick_wall", "pyrestone/vitrified_ash_brick_wall_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_brick_wall", 1, "Stonecutting: cut 1x Vitrified Ash Brick Wall from Vitrified Ash.", "Steins\u00e4ge: 1x Glasaschenziegelmauer aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_tiles_from_vitrified_ash_stonecutting", "copper_inferno:vitrified_ash_tiles", "pyrestone/vitrified_ash_tiles_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_tiles", 1, "Stonecutting: cut 1x Vitrified Ash Tiles from Vitrified Ash.", "Steins\u00e4ge: 1x Glasaschenfliesen aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_vitrified_ash_bricks_from_vitrified_ash_stonecutting", "copper_inferno:chiseled_vitrified_ash_bricks", "pyrestone/chiseled_vitrified_ash_bricks_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:chiseled_vitrified_ash_bricks", 1, "Stonecutting: cut 1x Chiseled Vitrified Ash Bricks from Vitrified Ash.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glasaschenziegel aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/vitrified_ash_pillar_from_vitrified_ash_stonecutting", "copper_inferno:vitrified_ash_pillar", "pyrestone/vitrified_ash_pillar_from_vitrified_ash_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:vitrified_ash", "", "", "", ""},
				"copper_inferno:vitrified_ash_pillar", 1, "Stonecutting: cut 1x Vitrified Ash Pillar from Vitrified Ash.", "Steins\u00e4ge: 1x Glasaschens\u00e4ule aus Glasasche schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone", "copper_inferno:blazewrought_stone", "pyrestone/blazewrought_stone",
				new String[] {"minecraft:gilded_blackstone", "", "minecraft:gilded_blackstone", "", "copper_inferno:vitrified_ash", "", "minecraft:gilded_blackstone", "", "minecraft:gilded_blackstone"},
				"copper_inferno:blazewrought_stone", 4, "Craft 4x Blazewrought Stone at a crafting table.", "Stellt 4x Flammschmiedestein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "pyrestone/blazewrought_stone_bricks",
				new String[] {"copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "", "copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_bricks", 4, "Craft 4x Blazewrought Stone Bricks at a crafting table.", "Stellt 4x Flammschmiedesteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_tiles", "copper_inferno:blazewrought_stone_tiles", "pyrestone/blazewrought_stone_tiles",
				new String[] {"copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "", "copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "", "", "", ""},
				"copper_inferno:blazewrought_stone_tiles", 4, "Craft 4x Blazewrought Stone Tiles at a crafting table.", "Stellt 4x Flammschmiedesteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "pyrestone/polished_blazewrought_stone",
				new String[] {"copper_inferno:blazewrought_stone_tiles", "copper_inferno:blazewrought_stone_tiles", "", "copper_inferno:blazewrought_stone_tiles", "copper_inferno:blazewrought_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_blazewrought_stone", 4, "Craft 4x Polished Blazewrought Stone at a crafting table.", "Stellt 4x Polierten Flammschmiedestein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_slab", "copper_inferno:blazewrought_stone_slab", "pyrestone/blazewrought_stone_slab",
				new String[] {"copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "", "", "", "", "", ""},
				"copper_inferno:blazewrought_stone_slab", 6, "Craft 6x Blazewrought Stone Slab at a crafting table.", "Stellt 6x Flammschmiedesteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_stairs", "copper_inferno:blazewrought_stone_stairs", "pyrestone/blazewrought_stone_stairs",
				new String[] {"copper_inferno:blazewrought_stone", "", "", "copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "", "copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone"},
				"copper_inferno:blazewrought_stone_stairs", 4, "Craft 4x Blazewrought Stone Stairs at a crafting table.", "Stellt 4x Flammschmiedesteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_wall", "copper_inferno:blazewrought_stone_wall", "pyrestone/blazewrought_stone_wall",
				new String[] {"copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "copper_inferno:blazewrought_stone", "", "", ""},
				"copper_inferno:blazewrought_stone_wall", 6, "Craft 6x Blazewrought Stone Wall at a crafting table.", "Stellt 6x Flammschmiedesteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_blazewrought_stone_slab", "copper_inferno:polished_blazewrought_stone_slab", "pyrestone/polished_blazewrought_stone_slab",
				new String[] {"copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_blazewrought_stone_slab", 6, "Craft 6x Polished Blazewrought Stone Slab at a crafting table.", "Stellt 6x Polierte Flammschmiedesteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_blazewrought_stone_stairs", "copper_inferno:polished_blazewrought_stone_stairs", "pyrestone/polished_blazewrought_stone_stairs",
				new String[] {"copper_inferno:polished_blazewrought_stone", "", "", "copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "", "copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone"},
				"copper_inferno:polished_blazewrought_stone_stairs", 4, "Craft 4x Polished Blazewrought Stone Stairs at a crafting table.", "Stellt 4x Polierte Flammschmiedesteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_blazewrought_stone_wall", "copper_inferno:polished_blazewrought_stone_wall", "pyrestone/polished_blazewrought_stone_wall",
				new String[] {"copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "copper_inferno:polished_blazewrought_stone", "", "", ""},
				"copper_inferno:polished_blazewrought_stone_wall", 6, "Craft 6x Polished Blazewrought Stone Wall at a crafting table.", "Stellt 6x Polierte Flammschmiedesteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_brick_slab", "copper_inferno:blazewrought_stone_brick_slab", "pyrestone/blazewrought_stone_brick_slab",
				new String[] {"copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:blazewrought_stone_brick_slab", 6, "Craft 6x Blazewrought Stone Brick Slab at a crafting table.", "Stellt 6x Flammschmiedesteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_brick_stairs", "copper_inferno:blazewrought_stone_brick_stairs", "pyrestone/blazewrought_stone_brick_stairs",
				new String[] {"copper_inferno:blazewrought_stone_bricks", "", "", "copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "", "copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks"},
				"copper_inferno:blazewrought_stone_brick_stairs", 4, "Craft 4x Blazewrought Stone Brick Stairs at a crafting table.", "Stellt 4x Flammschmiedesteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_brick_wall", "copper_inferno:blazewrought_stone_brick_wall", "pyrestone/blazewrought_stone_brick_wall",
				new String[] {"copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "copper_inferno:blazewrought_stone_bricks", "", "", ""},
				"copper_inferno:blazewrought_stone_brick_wall", 6, "Craft 6x Blazewrought Stone Brick Wall at a crafting table.", "Stellt 6x Flammschmiedesteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/cracked_blazewrought_stone_bricks", "copper_inferno:cracked_blazewrought_stone_bricks", "pyrestone/cracked_blazewrought_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_blazewrought_stone_bricks", 1, "Smelting Blazewrought Stone Bricks in a furnace yields Cracked Blazewrought Stone Bricks.", "Flammschmiedesteinziegel im Ofen gebrannt ergibt Rissige Flammschmiedesteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_blazewrought_stone_bricks", "copper_inferno:chiseled_blazewrought_stone_bricks", "pyrestone/chiseled_blazewrought_stone_bricks",
				new String[] {"copper_inferno:blazewrought_stone_brick_slab", "", "", "copper_inferno:blazewrought_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_blazewrought_stone_bricks", 1, "Craft 1x Chiseled Blazewrought Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Flammschmiedesteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_pillar", "copper_inferno:blazewrought_stone_pillar", "pyrestone/blazewrought_stone_pillar",
				new String[] {"copper_inferno:blazewrought_stone_bricks", "", "", "copper_inferno:blazewrought_stone_bricks", "", "", "", "", ""},
				"copper_inferno:blazewrought_stone_pillar", 2, "Craft 2x Blazewrought Stone Pillar at a crafting table.", "Stellt 2x Flammschmiedesteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_slab_from_blazewrought_stone_stonecutting", "copper_inferno:blazewrought_stone_slab", "pyrestone/blazewrought_stone_slab_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_slab", 2, "Stonecutting: cut 2x Blazewrought Stone Slab from Blazewrought Stone.", "Steins\u00e4ge: 2x Flammschmiedesteinstufe aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_stairs_from_blazewrought_stone_stonecutting", "copper_inferno:blazewrought_stone_stairs", "pyrestone/blazewrought_stone_stairs_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_stairs", 1, "Stonecutting: cut 1x Blazewrought Stone Stairs from Blazewrought Stone.", "Steins\u00e4ge: 1x Flammschmiedesteintreppe aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_wall_from_blazewrought_stone_stonecutting", "copper_inferno:blazewrought_stone_wall", "pyrestone/blazewrought_stone_wall_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_wall", 1, "Stonecutting: cut 1x Blazewrought Stone Wall from Blazewrought Stone.", "Steins\u00e4ge: 1x Flammschmiedesteinmauer aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_blazewrought_stone_from_blazewrought_stone_stonecutting", "copper_inferno:polished_blazewrought_stone", "pyrestone/polished_blazewrought_stone_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:polished_blazewrought_stone", 1, "Stonecutting: cut 1x Polished Blazewrought Stone from Blazewrought Stone.", "Steins\u00e4ge: 1x Polierten Flammschmiedestein aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_blazewrought_stone_slab_from_blazewrought_stone_stonecutting", "copper_inferno:polished_blazewrought_stone_slab", "pyrestone/polished_blazewrought_stone_slab_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:polished_blazewrought_stone_slab", 2, "Stonecutting: cut 2x Polished Blazewrought Stone Slab from Blazewrought Stone.", "Steins\u00e4ge: 2x Polierte Flammschmiedesteinstufe aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_blazewrought_stone_stairs_from_blazewrought_stone_stonecutting", "copper_inferno:polished_blazewrought_stone_stairs", "pyrestone/polished_blazewrought_stone_stairs_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:polished_blazewrought_stone_stairs", 1, "Stonecutting: cut 1x Polished Blazewrought Stone Stairs from Blazewrought Stone.", "Steins\u00e4ge: 1x Polierte Flammschmiedesteintreppe aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/polished_blazewrought_stone_wall_from_blazewrought_stone_stonecutting", "copper_inferno:polished_blazewrought_stone_wall", "pyrestone/polished_blazewrought_stone_wall_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:polished_blazewrought_stone_wall", 1, "Stonecutting: cut 1x Polished Blazewrought Stone Wall from Blazewrought Stone.", "Steins\u00e4ge: 1x Polierte Flammschmiedesteinmauer aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_bricks_from_blazewrought_stone_stonecutting", "copper_inferno:blazewrought_stone_bricks", "pyrestone/blazewrought_stone_bricks_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_bricks", 1, "Stonecutting: cut 1x Blazewrought Stone Bricks from Blazewrought Stone.", "Steins\u00e4ge: 1x Flammschmiedesteinziegel aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_brick_slab_from_blazewrought_stone_stonecutting", "copper_inferno:blazewrought_stone_brick_slab", "pyrestone/blazewrought_stone_brick_slab_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_brick_slab", 2, "Stonecutting: cut 2x Blazewrought Stone Brick Slab from Blazewrought Stone.", "Steins\u00e4ge: 2x Flammschmiedesteinziegelstufe aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_brick_stairs_from_blazewrought_stone_stonecutting", "copper_inferno:blazewrought_stone_brick_stairs", "pyrestone/blazewrought_stone_brick_stairs_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_brick_stairs", 1, "Stonecutting: cut 1x Blazewrought Stone Brick Stairs from Blazewrought Stone.", "Steins\u00e4ge: 1x Flammschmiedesteinziegeltreppe aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_brick_wall_from_blazewrought_stone_stonecutting", "copper_inferno:blazewrought_stone_brick_wall", "pyrestone/blazewrought_stone_brick_wall_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_brick_wall", 1, "Stonecutting: cut 1x Blazewrought Stone Brick Wall from Blazewrought Stone.", "Steins\u00e4ge: 1x Flammschmiedesteinziegelmauer aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_tiles_from_blazewrought_stone_stonecutting", "copper_inferno:blazewrought_stone_tiles", "pyrestone/blazewrought_stone_tiles_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_tiles", 1, "Stonecutting: cut 1x Blazewrought Stone Tiles from Blazewrought Stone.", "Steins\u00e4ge: 1x Flammschmiedesteinfliesen aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/chiseled_blazewrought_stone_bricks_from_blazewrought_stone_stonecutting", "copper_inferno:chiseled_blazewrought_stone_bricks", "pyrestone/chiseled_blazewrought_stone_bricks_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:chiseled_blazewrought_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Blazewrought Stone Bricks from Blazewrought Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Flammschmiedesteinziegel aus Flammschmiedestein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "pyrestone/blazewrought_stone_pillar_from_blazewrought_stone_stonecutting", "copper_inferno:blazewrought_stone_pillar", "pyrestone/blazewrought_stone_pillar_from_blazewrought_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blazewrought_stone", "", "", "", ""},
				"copper_inferno:blazewrought_stone_pillar", 1, "Stonecutting: cut 1x Blazewrought Stone Pillar from Blazewrought Stone.", "Steins\u00e4ge: 1x Flammschmiedesteins\u00e4ule aus Flammschmiedestein schneiden."));
	}
}
