package net.sonic0810.copperinferno.feature.kilnstone;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Kilnstone block set: one "blocks" overview per cube family
 * plus one recipe page for every JSON under {@code data/copper_inferno/recipe/kilnstone/}
 * (crafting, smelting and stonecutting). Entry texts and grids mirror the recipe JSONs
 * emitted by {@code devtools/gen/kilnstone_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep them
 * inline.
 */
final class KilnstoneHandbook {
	private KilnstoneHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_kilnstone", "copper_inferno:kilnstone", null,
				null,
				null, 0, "The Kilnstone family for Inferno builds: block, stairs, slab and wall.", "Die Brennofenstein-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_kilnstone", "copper_inferno:polished_kilnstone", null,
				null,
				null, 0, "The Polished Kilnstone family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Brennofenstein f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_kilnstone_bricks", "copper_inferno:kilnstone_bricks", null,
				null,
				null, 0, "The Kilnstone Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Brennofensteinziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_firebrick_stone", "copper_inferno:firebrick_stone", null,
				null,
				null, 0, "The Firebrick Stone family for Inferno builds: block, stairs, slab and wall.", "Die Schamottstein-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", null,
				null,
				null, 0, "The Polished Firebrick Stone family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Schamottstein f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", null,
				null,
				null, 0, "The Firebrick Stone Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Schamottsteinziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_glazed_cinder", "copper_inferno:glazed_cinder", null,
				null,
				null, 0, "The Glazed Cinder family for Inferno builds: block, stairs, slab and wall.", "Die Glasurzunder-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", null,
				null,
				null, 0, "The Polished Glazed Cinder family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Glasurzunder f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", null,
				null,
				null, 0, "The Glazed Cinder Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Glasurzunderziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_terracotta_slag", "copper_inferno:terracotta_slag", null,
				null,
				null, 0, "The Terracotta Slag family for Inferno builds: block, stairs, slab and wall.", "Die Terrakottaschlacke-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", null,
				null,
				null, 0, "The Polished Terracotta Slag family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Terrakottaschlacke f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", null,
				null,
				null, 0, "The Terracotta Slag Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Terrakottaschlackenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_kiln_ceramic", "copper_inferno:kiln_ceramic", null,
				null,
				null, 0, "The Kiln Ceramic family for Inferno builds: block, stairs, slab and wall.", "Die Ofenkeramik-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", null,
				null,
				null, 0, "The Polished Kiln Ceramic family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Ofenkeramik f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", null,
				null,
				null, 0, "The Kiln Ceramic Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Ofenkeramikziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_emberware", "copper_inferno:emberware", null,
				null,
				null, 0, "The Emberware family for Inferno builds: block, stairs, slab and wall.", "Die Glutware-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_emberware", "copper_inferno:polished_emberware", null,
				null,
				null, 0, "The Polished Emberware family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Glutware f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_emberware_bricks", "copper_inferno:emberware_bricks", null,
				null,
				null, 0, "The Emberware Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Glutwarenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_sootware", "copper_inferno:sootware", null,
				null,
				null, 0, "The Sootware family for Inferno builds: block, stairs, slab and wall.", "Die Ru\u00dfware-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_sootware", "copper_inferno:polished_sootware", null,
				null,
				null, 0, "The Polished Sootware family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Ru\u00dfware f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_sootware_bricks", "copper_inferno:sootware_bricks", null,
				null,
				null, 0, "The Sootware Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Ru\u00dfwarenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_glazed_ember", "copper_inferno:glazed_ember", null,
				null,
				null, 0, "The Glazed Ember family for Inferno builds: block, stairs, slab and wall.", "Die Glasurglut-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_glazed_ember", "copper_inferno:polished_glazed_ember", null,
				null,
				null, 0, "The Polished Glazed Ember family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Glasurglut f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", null,
				null,
				null, 0, "The Glazed Ember Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Glasurglutziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_baked_ash_block", "copper_inferno:baked_ash_block", null,
				null,
				null, 0, "The Baked Ash Block family for Inferno builds: block, stairs, slab and wall.", "Die Brandaschenblock-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", null,
				null,
				null, 0, "The Polished Baked Ash Block family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Brandaschenblock f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", null,
				null,
				null, 0, "The Baked Ash Block Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Brandaschenblockziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_foundry_brick", "copper_inferno:foundry_brick", null,
				null,
				null, 0, "The Foundry Brick family for Inferno builds: block, stairs, slab and wall.", "Die Gie\u00dfereistein-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_foundry_brick", "copper_inferno:polished_foundry_brick", null,
				null,
				null, 0, "The Polished Foundry Brick family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Gie\u00dfereistein f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", null,
				null,
				null, 0, "The Foundry Brick Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Gie\u00dfereisteinziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_crucible_stone", "copper_inferno:crucible_stone", null,
				null,
				null, 0, "The Crucible Stone family for Inferno builds: block, stairs, slab and wall.", "Die Tiegelstein-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_crucible_stone", "copper_inferno:polished_crucible_stone", null,
				null,
				null, 0, "The Polished Crucible Stone family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Tiegelstein f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", null,
				null,
				null, 0, "The Crucible Stone Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Tiegelsteinziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_slagware", "copper_inferno:slagware", null,
				null,
				null, 0, "The Slagware family for Inferno builds: block, stairs, slab and wall.", "Die Schlackenware-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_slagware", "copper_inferno:polished_slagware", null,
				null,
				null, 0, "The Polished Slagware family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Schlackenware f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_slagware_bricks", "copper_inferno:slagware_bricks", null,
				null,
				null, 0, "The Slagware Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Schlackenwarenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_ash_ceramic", "copper_inferno:ash_ceramic", null,
				null,
				null, 0, "The Ash Ceramic family for Inferno builds: block, stairs, slab and wall.", "Die Aschenkeramik-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", null,
				null,
				null, 0, "The Polished Ash Ceramic family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Aschenkeramik f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", null,
				null,
				null, 0, "The Ash Ceramic Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Aschenkeramikziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_pyroceramic", "copper_inferno:pyroceramic", null,
				null,
				null, 0, "The Pyroceramic family for Inferno builds: block, stairs, slab and wall.", "Die Pyrokeramik-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_polished_pyroceramic", "copper_inferno:polished_pyroceramic", null,
				null,
				null, 0, "The Polished Pyroceramic family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Pyrokeramik f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/family_pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", null,
				null,
				null, 0, "The Pyroceramic Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Pyrokeramikziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone", "copper_inferno:kilnstone", "kilnstone/kilnstone",
				new String[] {"minecraft:terracotta", "", "minecraft:terracotta", "", "copper_inferno:pyroceramic", "", "minecraft:terracotta", "", "minecraft:terracotta"},
				"copper_inferno:kilnstone", 4, "Craft 4x Kilnstone at a crafting table.", "Stellt 4x Brennofenstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_bricks", "copper_inferno:kilnstone_bricks", "kilnstone/kilnstone_bricks",
				new String[] {"copper_inferno:kilnstone", "copper_inferno:kilnstone", "", "copper_inferno:kilnstone", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_bricks", 4, "Craft 4x Kilnstone Bricks at a crafting table.", "Stellt 4x Brennofensteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_tiles", "copper_inferno:kilnstone_tiles", "kilnstone/kilnstone_tiles",
				new String[] {"copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "", "copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "", "", "", ""},
				"copper_inferno:kilnstone_tiles", 4, "Craft 4x Kilnstone Tiles at a crafting table.", "Stellt 4x Brennofensteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kilnstone", "copper_inferno:polished_kilnstone", "kilnstone/polished_kilnstone",
				new String[] {"copper_inferno:kilnstone_tiles", "copper_inferno:kilnstone_tiles", "", "copper_inferno:kilnstone_tiles", "copper_inferno:kilnstone_tiles", "", "", "", ""},
				"copper_inferno:polished_kilnstone", 4, "Craft 4x Polished Kilnstone at a crafting table.", "Stellt 4x Polierten Brennofenstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_slab", "copper_inferno:kilnstone_slab", "kilnstone/kilnstone_slab",
				new String[] {"copper_inferno:kilnstone", "copper_inferno:kilnstone", "copper_inferno:kilnstone", "", "", "", "", "", ""},
				"copper_inferno:kilnstone_slab", 6, "Craft 6x Kilnstone Slab at a crafting table.", "Stellt 6x Brennofensteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_stairs", "copper_inferno:kilnstone_stairs", "kilnstone/kilnstone_stairs",
				new String[] {"copper_inferno:kilnstone", "", "", "copper_inferno:kilnstone", "copper_inferno:kilnstone", "", "copper_inferno:kilnstone", "copper_inferno:kilnstone", "copper_inferno:kilnstone"},
				"copper_inferno:kilnstone_stairs", 4, "Craft 4x Kilnstone Stairs at a crafting table.", "Stellt 4x Brennofensteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_wall", "copper_inferno:kilnstone_wall", "kilnstone/kilnstone_wall",
				new String[] {"copper_inferno:kilnstone", "copper_inferno:kilnstone", "copper_inferno:kilnstone", "copper_inferno:kilnstone", "copper_inferno:kilnstone", "copper_inferno:kilnstone", "", "", ""},
				"copper_inferno:kilnstone_wall", 6, "Craft 6x Kilnstone Wall at a crafting table.", "Stellt 6x Brennofensteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kilnstone_slab", "copper_inferno:polished_kilnstone_slab", "kilnstone/polished_kilnstone_slab",
				new String[] {"copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone", "", "", "", "", "", ""},
				"copper_inferno:polished_kilnstone_slab", 6, "Craft 6x Polished Kilnstone Slab at a crafting table.", "Stellt 6x Polierte Brennofensteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kilnstone_stairs", "copper_inferno:polished_kilnstone_stairs", "kilnstone/polished_kilnstone_stairs",
				new String[] {"copper_inferno:polished_kilnstone", "", "", "copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone", "", "copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone"},
				"copper_inferno:polished_kilnstone_stairs", 4, "Craft 4x Polished Kilnstone Stairs at a crafting table.", "Stellt 4x Polierte Brennofensteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kilnstone_wall", "copper_inferno:polished_kilnstone_wall", "kilnstone/polished_kilnstone_wall",
				new String[] {"copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone", "copper_inferno:polished_kilnstone", "", "", ""},
				"copper_inferno:polished_kilnstone_wall", 6, "Craft 6x Polished Kilnstone Wall at a crafting table.", "Stellt 6x Polierte Brennofensteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_brick_slab", "copper_inferno:kilnstone_brick_slab", "kilnstone/kilnstone_brick_slab",
				new String[] {"copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "", "", "", "", "", ""},
				"copper_inferno:kilnstone_brick_slab", 6, "Craft 6x Kilnstone Brick Slab at a crafting table.", "Stellt 6x Brennofensteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_brick_stairs", "copper_inferno:kilnstone_brick_stairs", "kilnstone/kilnstone_brick_stairs",
				new String[] {"copper_inferno:kilnstone_bricks", "", "", "copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "", "copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks"},
				"copper_inferno:kilnstone_brick_stairs", 4, "Craft 4x Kilnstone Brick Stairs at a crafting table.", "Stellt 4x Brennofensteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_brick_wall", "copper_inferno:kilnstone_brick_wall", "kilnstone/kilnstone_brick_wall",
				new String[] {"copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "copper_inferno:kilnstone_bricks", "", "", ""},
				"copper_inferno:kilnstone_brick_wall", 6, "Craft 6x Kilnstone Brick Wall at a crafting table.", "Stellt 6x Brennofensteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_kilnstone_bricks", "copper_inferno:cracked_kilnstone_bricks", "kilnstone/cracked_kilnstone_bricks",
				new String[] {"", "", "", "", "copper_inferno:kilnstone_bricks", "", "", "", ""},
				"copper_inferno:cracked_kilnstone_bricks", 1, "Smelting Kilnstone Bricks in a furnace yields Cracked Kilnstone Bricks.", "Brennofensteinziegel im Ofen gebrannt ergibt Rissige Brennofensteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_kilnstone_bricks", "copper_inferno:chiseled_kilnstone_bricks", "kilnstone/chiseled_kilnstone_bricks",
				new String[] {"copper_inferno:kilnstone_brick_slab", "", "", "copper_inferno:kilnstone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_kilnstone_bricks", 1, "Craft 1x Chiseled Kilnstone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Brennofensteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_pillar", "copper_inferno:kilnstone_pillar", "kilnstone/kilnstone_pillar",
				new String[] {"copper_inferno:kilnstone_bricks", "", "", "copper_inferno:kilnstone_bricks", "", "", "", "", ""},
				"copper_inferno:kilnstone_pillar", 2, "Craft 2x Kilnstone Pillar at a crafting table.", "Stellt 2x Brennofensteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_slab_from_kilnstone_stonecutting", "copper_inferno:kilnstone_slab", "kilnstone/kilnstone_slab_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_slab", 2, "Stonecutting: cut 2x Kilnstone Slab from Kilnstone.", "Steins\u00e4ge: 2x Brennofensteinstufe aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_stairs_from_kilnstone_stonecutting", "copper_inferno:kilnstone_stairs", "kilnstone/kilnstone_stairs_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_stairs", 1, "Stonecutting: cut 1x Kilnstone Stairs from Kilnstone.", "Steins\u00e4ge: 1x Brennofensteintreppe aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_wall_from_kilnstone_stonecutting", "copper_inferno:kilnstone_wall", "kilnstone/kilnstone_wall_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_wall", 1, "Stonecutting: cut 1x Kilnstone Wall from Kilnstone.", "Steins\u00e4ge: 1x Brennofensteinmauer aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kilnstone_from_kilnstone_stonecutting", "copper_inferno:polished_kilnstone", "kilnstone/polished_kilnstone_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:polished_kilnstone", 1, "Stonecutting: cut 1x Polished Kilnstone from Kilnstone.", "Steins\u00e4ge: 1x Polierten Brennofenstein aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kilnstone_slab_from_kilnstone_stonecutting", "copper_inferno:polished_kilnstone_slab", "kilnstone/polished_kilnstone_slab_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:polished_kilnstone_slab", 2, "Stonecutting: cut 2x Polished Kilnstone Slab from Kilnstone.", "Steins\u00e4ge: 2x Polierte Brennofensteinstufe aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kilnstone_stairs_from_kilnstone_stonecutting", "copper_inferno:polished_kilnstone_stairs", "kilnstone/polished_kilnstone_stairs_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:polished_kilnstone_stairs", 1, "Stonecutting: cut 1x Polished Kilnstone Stairs from Kilnstone.", "Steins\u00e4ge: 1x Polierte Brennofensteintreppe aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kilnstone_wall_from_kilnstone_stonecutting", "copper_inferno:polished_kilnstone_wall", "kilnstone/polished_kilnstone_wall_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:polished_kilnstone_wall", 1, "Stonecutting: cut 1x Polished Kilnstone Wall from Kilnstone.", "Steins\u00e4ge: 1x Polierte Brennofensteinmauer aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_bricks_from_kilnstone_stonecutting", "copper_inferno:kilnstone_bricks", "kilnstone/kilnstone_bricks_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_bricks", 1, "Stonecutting: cut 1x Kilnstone Bricks from Kilnstone.", "Steins\u00e4ge: 1x Brennofensteinziegel aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_brick_slab_from_kilnstone_stonecutting", "copper_inferno:kilnstone_brick_slab", "kilnstone/kilnstone_brick_slab_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_brick_slab", 2, "Stonecutting: cut 2x Kilnstone Brick Slab from Kilnstone.", "Steins\u00e4ge: 2x Brennofensteinziegelstufe aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_brick_stairs_from_kilnstone_stonecutting", "copper_inferno:kilnstone_brick_stairs", "kilnstone/kilnstone_brick_stairs_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_brick_stairs", 1, "Stonecutting: cut 1x Kilnstone Brick Stairs from Kilnstone.", "Steins\u00e4ge: 1x Brennofensteinziegeltreppe aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_brick_wall_from_kilnstone_stonecutting", "copper_inferno:kilnstone_brick_wall", "kilnstone/kilnstone_brick_wall_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_brick_wall", 1, "Stonecutting: cut 1x Kilnstone Brick Wall from Kilnstone.", "Steins\u00e4ge: 1x Brennofensteinziegelmauer aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_tiles_from_kilnstone_stonecutting", "copper_inferno:kilnstone_tiles", "kilnstone/kilnstone_tiles_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_tiles", 1, "Stonecutting: cut 1x Kilnstone Tiles from Kilnstone.", "Steins\u00e4ge: 1x Brennofensteinfliesen aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_kilnstone_bricks_from_kilnstone_stonecutting", "copper_inferno:chiseled_kilnstone_bricks", "kilnstone/chiseled_kilnstone_bricks_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:chiseled_kilnstone_bricks", 1, "Stonecutting: cut 1x Chiseled Kilnstone Bricks from Kilnstone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Brennofensteinziegel aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kilnstone_pillar_from_kilnstone_stonecutting", "copper_inferno:kilnstone_pillar", "kilnstone/kilnstone_pillar_from_kilnstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kilnstone", "", "", "", ""},
				"copper_inferno:kilnstone_pillar", 1, "Stonecutting: cut 1x Kilnstone Pillar from Kilnstone.", "Steins\u00e4ge: 1x Brennofensteins\u00e4ule aus Brennofenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone", "copper_inferno:firebrick_stone", "kilnstone/firebrick_stone",
				new String[] {"minecraft:bricks", "", "minecraft:bricks", "", "copper_inferno:kilnstone", "", "minecraft:bricks", "", "minecraft:bricks"},
				"copper_inferno:firebrick_stone", 4, "Craft 4x Firebrick Stone at a crafting table.", "Stellt 4x Schamottstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "kilnstone/firebrick_stone_bricks",
				new String[] {"copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "", "copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_bricks", 4, "Craft 4x Firebrick Stone Bricks at a crafting table.", "Stellt 4x Schamottsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_tiles", "copper_inferno:firebrick_stone_tiles", "kilnstone/firebrick_stone_tiles",
				new String[] {"copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "", "copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "", "", "", ""},
				"copper_inferno:firebrick_stone_tiles", 4, "Craft 4x Firebrick Stone Tiles at a crafting table.", "Stellt 4x Schamottsteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "kilnstone/polished_firebrick_stone",
				new String[] {"copper_inferno:firebrick_stone_tiles", "copper_inferno:firebrick_stone_tiles", "", "copper_inferno:firebrick_stone_tiles", "copper_inferno:firebrick_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_firebrick_stone", 4, "Craft 4x Polished Firebrick Stone at a crafting table.", "Stellt 4x Polierten Schamottstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_slab", "copper_inferno:firebrick_stone_slab", "kilnstone/firebrick_stone_slab",
				new String[] {"copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "", "", "", "", "", ""},
				"copper_inferno:firebrick_stone_slab", 6, "Craft 6x Firebrick Stone Slab at a crafting table.", "Stellt 6x Schamottsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_stairs", "copper_inferno:firebrick_stone_stairs", "kilnstone/firebrick_stone_stairs",
				new String[] {"copper_inferno:firebrick_stone", "", "", "copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "", "copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone"},
				"copper_inferno:firebrick_stone_stairs", 4, "Craft 4x Firebrick Stone Stairs at a crafting table.", "Stellt 4x Schamottsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_wall", "copper_inferno:firebrick_stone_wall", "kilnstone/firebrick_stone_wall",
				new String[] {"copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "copper_inferno:firebrick_stone", "", "", ""},
				"copper_inferno:firebrick_stone_wall", 6, "Craft 6x Firebrick Stone Wall at a crafting table.", "Stellt 6x Schamottsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_firebrick_stone_slab", "copper_inferno:polished_firebrick_stone_slab", "kilnstone/polished_firebrick_stone_slab",
				new String[] {"copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_firebrick_stone_slab", 6, "Craft 6x Polished Firebrick Stone Slab at a crafting table.", "Stellt 6x Polierte Schamottsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_firebrick_stone_stairs", "copper_inferno:polished_firebrick_stone_stairs", "kilnstone/polished_firebrick_stone_stairs",
				new String[] {"copper_inferno:polished_firebrick_stone", "", "", "copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "", "copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone"},
				"copper_inferno:polished_firebrick_stone_stairs", 4, "Craft 4x Polished Firebrick Stone Stairs at a crafting table.", "Stellt 4x Polierte Schamottsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_firebrick_stone_wall", "copper_inferno:polished_firebrick_stone_wall", "kilnstone/polished_firebrick_stone_wall",
				new String[] {"copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "copper_inferno:polished_firebrick_stone", "", "", ""},
				"copper_inferno:polished_firebrick_stone_wall", 6, "Craft 6x Polished Firebrick Stone Wall at a crafting table.", "Stellt 6x Polierte Schamottsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_brick_slab", "copper_inferno:firebrick_stone_brick_slab", "kilnstone/firebrick_stone_brick_slab",
				new String[] {"copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:firebrick_stone_brick_slab", 6, "Craft 6x Firebrick Stone Brick Slab at a crafting table.", "Stellt 6x Schamottsteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_brick_stairs", "copper_inferno:firebrick_stone_brick_stairs", "kilnstone/firebrick_stone_brick_stairs",
				new String[] {"copper_inferno:firebrick_stone_bricks", "", "", "copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "", "copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks"},
				"copper_inferno:firebrick_stone_brick_stairs", 4, "Craft 4x Firebrick Stone Brick Stairs at a crafting table.", "Stellt 4x Schamottsteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_brick_wall", "copper_inferno:firebrick_stone_brick_wall", "kilnstone/firebrick_stone_brick_wall",
				new String[] {"copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "copper_inferno:firebrick_stone_bricks", "", "", ""},
				"copper_inferno:firebrick_stone_brick_wall", 6, "Craft 6x Firebrick Stone Brick Wall at a crafting table.", "Stellt 6x Schamottsteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_firebrick_stone_bricks", "copper_inferno:cracked_firebrick_stone_bricks", "kilnstone/cracked_firebrick_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_firebrick_stone_bricks", 1, "Smelting Firebrick Stone Bricks in a furnace yields Cracked Firebrick Stone Bricks.", "Schamottsteinziegel im Ofen gebrannt ergibt Rissige Schamottsteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_firebrick_stone_bricks", "copper_inferno:chiseled_firebrick_stone_bricks", "kilnstone/chiseled_firebrick_stone_bricks",
				new String[] {"copper_inferno:firebrick_stone_brick_slab", "", "", "copper_inferno:firebrick_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_firebrick_stone_bricks", 1, "Craft 1x Chiseled Firebrick Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schamottsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_pillar", "copper_inferno:firebrick_stone_pillar", "kilnstone/firebrick_stone_pillar",
				new String[] {"copper_inferno:firebrick_stone_bricks", "", "", "copper_inferno:firebrick_stone_bricks", "", "", "", "", ""},
				"copper_inferno:firebrick_stone_pillar", 2, "Craft 2x Firebrick Stone Pillar at a crafting table.", "Stellt 2x Schamottsteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_slab_from_firebrick_stone_stonecutting", "copper_inferno:firebrick_stone_slab", "kilnstone/firebrick_stone_slab_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_slab", 2, "Stonecutting: cut 2x Firebrick Stone Slab from Firebrick Stone.", "Steins\u00e4ge: 2x Schamottsteinstufe aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_stairs_from_firebrick_stone_stonecutting", "copper_inferno:firebrick_stone_stairs", "kilnstone/firebrick_stone_stairs_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_stairs", 1, "Stonecutting: cut 1x Firebrick Stone Stairs from Firebrick Stone.", "Steins\u00e4ge: 1x Schamottsteintreppe aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_wall_from_firebrick_stone_stonecutting", "copper_inferno:firebrick_stone_wall", "kilnstone/firebrick_stone_wall_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_wall", 1, "Stonecutting: cut 1x Firebrick Stone Wall from Firebrick Stone.", "Steins\u00e4ge: 1x Schamottsteinmauer aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_firebrick_stone_from_firebrick_stone_stonecutting", "copper_inferno:polished_firebrick_stone", "kilnstone/polished_firebrick_stone_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:polished_firebrick_stone", 1, "Stonecutting: cut 1x Polished Firebrick Stone from Firebrick Stone.", "Steins\u00e4ge: 1x Polierten Schamottstein aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_firebrick_stone_slab_from_firebrick_stone_stonecutting", "copper_inferno:polished_firebrick_stone_slab", "kilnstone/polished_firebrick_stone_slab_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:polished_firebrick_stone_slab", 2, "Stonecutting: cut 2x Polished Firebrick Stone Slab from Firebrick Stone.", "Steins\u00e4ge: 2x Polierte Schamottsteinstufe aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_firebrick_stone_stairs_from_firebrick_stone_stonecutting", "copper_inferno:polished_firebrick_stone_stairs", "kilnstone/polished_firebrick_stone_stairs_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:polished_firebrick_stone_stairs", 1, "Stonecutting: cut 1x Polished Firebrick Stone Stairs from Firebrick Stone.", "Steins\u00e4ge: 1x Polierte Schamottsteintreppe aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_firebrick_stone_wall_from_firebrick_stone_stonecutting", "copper_inferno:polished_firebrick_stone_wall", "kilnstone/polished_firebrick_stone_wall_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:polished_firebrick_stone_wall", 1, "Stonecutting: cut 1x Polished Firebrick Stone Wall from Firebrick Stone.", "Steins\u00e4ge: 1x Polierte Schamottsteinmauer aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_bricks_from_firebrick_stone_stonecutting", "copper_inferno:firebrick_stone_bricks", "kilnstone/firebrick_stone_bricks_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_bricks", 1, "Stonecutting: cut 1x Firebrick Stone Bricks from Firebrick Stone.", "Steins\u00e4ge: 1x Schamottsteinziegel aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_brick_slab_from_firebrick_stone_stonecutting", "copper_inferno:firebrick_stone_brick_slab", "kilnstone/firebrick_stone_brick_slab_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_brick_slab", 2, "Stonecutting: cut 2x Firebrick Stone Brick Slab from Firebrick Stone.", "Steins\u00e4ge: 2x Schamottsteinziegelstufe aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_brick_stairs_from_firebrick_stone_stonecutting", "copper_inferno:firebrick_stone_brick_stairs", "kilnstone/firebrick_stone_brick_stairs_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_brick_stairs", 1, "Stonecutting: cut 1x Firebrick Stone Brick Stairs from Firebrick Stone.", "Steins\u00e4ge: 1x Schamottsteinziegeltreppe aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_brick_wall_from_firebrick_stone_stonecutting", "copper_inferno:firebrick_stone_brick_wall", "kilnstone/firebrick_stone_brick_wall_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_brick_wall", 1, "Stonecutting: cut 1x Firebrick Stone Brick Wall from Firebrick Stone.", "Steins\u00e4ge: 1x Schamottsteinziegelmauer aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_tiles_from_firebrick_stone_stonecutting", "copper_inferno:firebrick_stone_tiles", "kilnstone/firebrick_stone_tiles_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_tiles", 1, "Stonecutting: cut 1x Firebrick Stone Tiles from Firebrick Stone.", "Steins\u00e4ge: 1x Schamottsteinfliesen aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_firebrick_stone_bricks_from_firebrick_stone_stonecutting", "copper_inferno:chiseled_firebrick_stone_bricks", "kilnstone/chiseled_firebrick_stone_bricks_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:chiseled_firebrick_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Firebrick Stone Bricks from Firebrick Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schamottsteinziegel aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/firebrick_stone_pillar_from_firebrick_stone_stonecutting", "copper_inferno:firebrick_stone_pillar", "kilnstone/firebrick_stone_pillar_from_firebrick_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:firebrick_stone", "", "", "", ""},
				"copper_inferno:firebrick_stone_pillar", 1, "Stonecutting: cut 1x Firebrick Stone Pillar from Firebrick Stone.", "Steins\u00e4ge: 1x Schamottsteins\u00e4ule aus Schamottstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder", "copper_inferno:glazed_cinder", "kilnstone/glazed_cinder",
				new String[] {"minecraft:charcoal", "", "minecraft:charcoal", "", "copper_inferno:firebrick_stone", "", "minecraft:charcoal", "", "minecraft:charcoal"},
				"copper_inferno:glazed_cinder", 4, "Craft 4x Glazed Cinder at a crafting table.", "Stellt 4x Glasurzunder an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "kilnstone/glazed_cinder_bricks",
				new String[] {"copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "", "copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_bricks", 4, "Craft 4x Glazed Cinder Bricks at a crafting table.", "Stellt 4x Glasurzunderziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_tiles", "copper_inferno:glazed_cinder_tiles", "kilnstone/glazed_cinder_tiles",
				new String[] {"copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "", "copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "", "", "", ""},
				"copper_inferno:glazed_cinder_tiles", 4, "Craft 4x Glazed Cinder Tiles at a crafting table.", "Stellt 4x Glasurzunderfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "kilnstone/polished_glazed_cinder",
				new String[] {"copper_inferno:glazed_cinder_tiles", "copper_inferno:glazed_cinder_tiles", "", "copper_inferno:glazed_cinder_tiles", "copper_inferno:glazed_cinder_tiles", "", "", "", ""},
				"copper_inferno:polished_glazed_cinder", 4, "Craft 4x Polished Glazed Cinder at a crafting table.", "Stellt 4x Polierten Glasurzunder an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_slab", "copper_inferno:glazed_cinder_slab", "kilnstone/glazed_cinder_slab",
				new String[] {"copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "", "", "", "", "", ""},
				"copper_inferno:glazed_cinder_slab", 6, "Craft 6x Glazed Cinder Slab at a crafting table.", "Stellt 6x Glasurzunderstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_stairs", "copper_inferno:glazed_cinder_stairs", "kilnstone/glazed_cinder_stairs",
				new String[] {"copper_inferno:glazed_cinder", "", "", "copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "", "copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder"},
				"copper_inferno:glazed_cinder_stairs", 4, "Craft 4x Glazed Cinder Stairs at a crafting table.", "Stellt 4x Glasurzundertreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_wall", "copper_inferno:glazed_cinder_wall", "kilnstone/glazed_cinder_wall",
				new String[] {"copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "copper_inferno:glazed_cinder", "", "", ""},
				"copper_inferno:glazed_cinder_wall", 6, "Craft 6x Glazed Cinder Wall at a crafting table.", "Stellt 6x Glasurzundermauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_cinder_slab", "copper_inferno:polished_glazed_cinder_slab", "kilnstone/polished_glazed_cinder_slab",
				new String[] {"copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "", "", "", "", "", ""},
				"copper_inferno:polished_glazed_cinder_slab", 6, "Craft 6x Polished Glazed Cinder Slab at a crafting table.", "Stellt 6x Polierte Glasurzunderstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_cinder_stairs", "copper_inferno:polished_glazed_cinder_stairs", "kilnstone/polished_glazed_cinder_stairs",
				new String[] {"copper_inferno:polished_glazed_cinder", "", "", "copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "", "copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder"},
				"copper_inferno:polished_glazed_cinder_stairs", 4, "Craft 4x Polished Glazed Cinder Stairs at a crafting table.", "Stellt 4x Polierte Glasurzundertreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_cinder_wall", "copper_inferno:polished_glazed_cinder_wall", "kilnstone/polished_glazed_cinder_wall",
				new String[] {"copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "copper_inferno:polished_glazed_cinder", "", "", ""},
				"copper_inferno:polished_glazed_cinder_wall", 6, "Craft 6x Polished Glazed Cinder Wall at a crafting table.", "Stellt 6x Polierte Glasurzundermauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_brick_slab", "copper_inferno:glazed_cinder_brick_slab", "kilnstone/glazed_cinder_brick_slab",
				new String[] {"copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "", "", "", "", "", ""},
				"copper_inferno:glazed_cinder_brick_slab", 6, "Craft 6x Glazed Cinder Brick Slab at a crafting table.", "Stellt 6x Glasurzunderziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_brick_stairs", "copper_inferno:glazed_cinder_brick_stairs", "kilnstone/glazed_cinder_brick_stairs",
				new String[] {"copper_inferno:glazed_cinder_bricks", "", "", "copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "", "copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks"},
				"copper_inferno:glazed_cinder_brick_stairs", 4, "Craft 4x Glazed Cinder Brick Stairs at a crafting table.", "Stellt 4x Glasurzunderziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_brick_wall", "copper_inferno:glazed_cinder_brick_wall", "kilnstone/glazed_cinder_brick_wall",
				new String[] {"copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "copper_inferno:glazed_cinder_bricks", "", "", ""},
				"copper_inferno:glazed_cinder_brick_wall", 6, "Craft 6x Glazed Cinder Brick Wall at a crafting table.", "Stellt 6x Glasurzunderziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_glazed_cinder_bricks", "copper_inferno:cracked_glazed_cinder_bricks", "kilnstone/cracked_glazed_cinder_bricks",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder_bricks", "", "", "", ""},
				"copper_inferno:cracked_glazed_cinder_bricks", 1, "Smelting Glazed Cinder Bricks in a furnace yields Cracked Glazed Cinder Bricks.", "Glasurzunderziegel im Ofen gebrannt ergibt Rissige Glasurzunderziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_glazed_cinder_bricks", "copper_inferno:chiseled_glazed_cinder_bricks", "kilnstone/chiseled_glazed_cinder_bricks",
				new String[] {"copper_inferno:glazed_cinder_brick_slab", "", "", "copper_inferno:glazed_cinder_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_glazed_cinder_bricks", 1, "Craft 1x Chiseled Glazed Cinder Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glasurzunderziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_pillar", "copper_inferno:glazed_cinder_pillar", "kilnstone/glazed_cinder_pillar",
				new String[] {"copper_inferno:glazed_cinder_bricks", "", "", "copper_inferno:glazed_cinder_bricks", "", "", "", "", ""},
				"copper_inferno:glazed_cinder_pillar", 2, "Craft 2x Glazed Cinder Pillar at a crafting table.", "Stellt 2x Glasurzunders\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_slab_from_glazed_cinder_stonecutting", "copper_inferno:glazed_cinder_slab", "kilnstone/glazed_cinder_slab_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_slab", 2, "Stonecutting: cut 2x Glazed Cinder Slab from Glazed Cinder.", "Steins\u00e4ge: 2x Glasurzunderstufe aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_stairs_from_glazed_cinder_stonecutting", "copper_inferno:glazed_cinder_stairs", "kilnstone/glazed_cinder_stairs_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_stairs", 1, "Stonecutting: cut 1x Glazed Cinder Stairs from Glazed Cinder.", "Steins\u00e4ge: 1x Glasurzundertreppe aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_wall_from_glazed_cinder_stonecutting", "copper_inferno:glazed_cinder_wall", "kilnstone/glazed_cinder_wall_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_wall", 1, "Stonecutting: cut 1x Glazed Cinder Wall from Glazed Cinder.", "Steins\u00e4ge: 1x Glasurzundermauer aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_cinder_from_glazed_cinder_stonecutting", "copper_inferno:polished_glazed_cinder", "kilnstone/polished_glazed_cinder_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:polished_glazed_cinder", 1, "Stonecutting: cut 1x Polished Glazed Cinder from Glazed Cinder.", "Steins\u00e4ge: 1x Polierten Glasurzunder aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_cinder_slab_from_glazed_cinder_stonecutting", "copper_inferno:polished_glazed_cinder_slab", "kilnstone/polished_glazed_cinder_slab_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:polished_glazed_cinder_slab", 2, "Stonecutting: cut 2x Polished Glazed Cinder Slab from Glazed Cinder.", "Steins\u00e4ge: 2x Polierte Glasurzunderstufe aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_cinder_stairs_from_glazed_cinder_stonecutting", "copper_inferno:polished_glazed_cinder_stairs", "kilnstone/polished_glazed_cinder_stairs_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:polished_glazed_cinder_stairs", 1, "Stonecutting: cut 1x Polished Glazed Cinder Stairs from Glazed Cinder.", "Steins\u00e4ge: 1x Polierte Glasurzundertreppe aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_cinder_wall_from_glazed_cinder_stonecutting", "copper_inferno:polished_glazed_cinder_wall", "kilnstone/polished_glazed_cinder_wall_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:polished_glazed_cinder_wall", 1, "Stonecutting: cut 1x Polished Glazed Cinder Wall from Glazed Cinder.", "Steins\u00e4ge: 1x Polierte Glasurzundermauer aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_bricks_from_glazed_cinder_stonecutting", "copper_inferno:glazed_cinder_bricks", "kilnstone/glazed_cinder_bricks_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_bricks", 1, "Stonecutting: cut 1x Glazed Cinder Bricks from Glazed Cinder.", "Steins\u00e4ge: 1x Glasurzunderziegel aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_brick_slab_from_glazed_cinder_stonecutting", "copper_inferno:glazed_cinder_brick_slab", "kilnstone/glazed_cinder_brick_slab_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_brick_slab", 2, "Stonecutting: cut 2x Glazed Cinder Brick Slab from Glazed Cinder.", "Steins\u00e4ge: 2x Glasurzunderziegelstufe aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_brick_stairs_from_glazed_cinder_stonecutting", "copper_inferno:glazed_cinder_brick_stairs", "kilnstone/glazed_cinder_brick_stairs_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_brick_stairs", 1, "Stonecutting: cut 1x Glazed Cinder Brick Stairs from Glazed Cinder.", "Steins\u00e4ge: 1x Glasurzunderziegeltreppe aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_brick_wall_from_glazed_cinder_stonecutting", "copper_inferno:glazed_cinder_brick_wall", "kilnstone/glazed_cinder_brick_wall_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_brick_wall", 1, "Stonecutting: cut 1x Glazed Cinder Brick Wall from Glazed Cinder.", "Steins\u00e4ge: 1x Glasurzunderziegelmauer aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_tiles_from_glazed_cinder_stonecutting", "copper_inferno:glazed_cinder_tiles", "kilnstone/glazed_cinder_tiles_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_tiles", 1, "Stonecutting: cut 1x Glazed Cinder Tiles from Glazed Cinder.", "Steins\u00e4ge: 1x Glasurzunderfliesen aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_glazed_cinder_bricks_from_glazed_cinder_stonecutting", "copper_inferno:chiseled_glazed_cinder_bricks", "kilnstone/chiseled_glazed_cinder_bricks_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:chiseled_glazed_cinder_bricks", 1, "Stonecutting: cut 1x Chiseled Glazed Cinder Bricks from Glazed Cinder.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glasurzunderziegel aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_cinder_pillar_from_glazed_cinder_stonecutting", "copper_inferno:glazed_cinder_pillar", "kilnstone/glazed_cinder_pillar_from_glazed_cinder_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_cinder", "", "", "", ""},
				"copper_inferno:glazed_cinder_pillar", 1, "Stonecutting: cut 1x Glazed Cinder Pillar from Glazed Cinder.", "Steins\u00e4ge: 1x Glasurzunders\u00e4ule aus Glasurzunder schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag", "copper_inferno:terracotta_slag", "kilnstone/terracotta_slag",
				new String[] {"minecraft:red_terracotta", "", "minecraft:red_terracotta", "", "copper_inferno:glazed_cinder", "", "minecraft:red_terracotta", "", "minecraft:red_terracotta"},
				"copper_inferno:terracotta_slag", 4, "Craft 4x Terracotta Slag at a crafting table.", "Stellt 4x Terrakottaschlacke an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "kilnstone/terracotta_slag_bricks",
				new String[] {"copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "", "copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_bricks", 4, "Craft 4x Terracotta Slag Bricks at a crafting table.", "Stellt 4x Terrakottaschlackenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_tiles", "copper_inferno:terracotta_slag_tiles", "kilnstone/terracotta_slag_tiles",
				new String[] {"copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "", "copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "", "", "", ""},
				"copper_inferno:terracotta_slag_tiles", 4, "Craft 4x Terracotta Slag Tiles at a crafting table.", "Stellt 4x Terrakottaschlackenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "kilnstone/polished_terracotta_slag",
				new String[] {"copper_inferno:terracotta_slag_tiles", "copper_inferno:terracotta_slag_tiles", "", "copper_inferno:terracotta_slag_tiles", "copper_inferno:terracotta_slag_tiles", "", "", "", ""},
				"copper_inferno:polished_terracotta_slag", 4, "Craft 4x Polished Terracotta Slag at a crafting table.", "Stellt 4x Polierte Terrakottaschlacke an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_slab", "copper_inferno:terracotta_slag_slab", "kilnstone/terracotta_slag_slab",
				new String[] {"copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "", "", "", "", "", ""},
				"copper_inferno:terracotta_slag_slab", 6, "Craft 6x Terracotta Slag Slab at a crafting table.", "Stellt 6x Terrakottaschlackenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_stairs", "copper_inferno:terracotta_slag_stairs", "kilnstone/terracotta_slag_stairs",
				new String[] {"copper_inferno:terracotta_slag", "", "", "copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "", "copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag"},
				"copper_inferno:terracotta_slag_stairs", 4, "Craft 4x Terracotta Slag Stairs at a crafting table.", "Stellt 4x Terrakottaschlackentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_wall", "copper_inferno:terracotta_slag_wall", "kilnstone/terracotta_slag_wall",
				new String[] {"copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "copper_inferno:terracotta_slag", "", "", ""},
				"copper_inferno:terracotta_slag_wall", 6, "Craft 6x Terracotta Slag Wall at a crafting table.", "Stellt 6x Terrakottaschlackenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_terracotta_slag_slab", "copper_inferno:polished_terracotta_slag_slab", "kilnstone/polished_terracotta_slag_slab",
				new String[] {"copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "", "", "", "", "", ""},
				"copper_inferno:polished_terracotta_slag_slab", 6, "Craft 6x Polished Terracotta Slag Slab at a crafting table.", "Stellt 6x Polierte Terrakottaschlackenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_terracotta_slag_stairs", "copper_inferno:polished_terracotta_slag_stairs", "kilnstone/polished_terracotta_slag_stairs",
				new String[] {"copper_inferno:polished_terracotta_slag", "", "", "copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "", "copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag"},
				"copper_inferno:polished_terracotta_slag_stairs", 4, "Craft 4x Polished Terracotta Slag Stairs at a crafting table.", "Stellt 4x Polierte Terrakottaschlackentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_terracotta_slag_wall", "copper_inferno:polished_terracotta_slag_wall", "kilnstone/polished_terracotta_slag_wall",
				new String[] {"copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "copper_inferno:polished_terracotta_slag", "", "", ""},
				"copper_inferno:polished_terracotta_slag_wall", 6, "Craft 6x Polished Terracotta Slag Wall at a crafting table.", "Stellt 6x Polierte Terrakottaschlackenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_brick_slab", "copper_inferno:terracotta_slag_brick_slab", "kilnstone/terracotta_slag_brick_slab",
				new String[] {"copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "", "", "", "", "", ""},
				"copper_inferno:terracotta_slag_brick_slab", 6, "Craft 6x Terracotta Slag Brick Slab at a crafting table.", "Stellt 6x Terrakottaschlackenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_brick_stairs", "copper_inferno:terracotta_slag_brick_stairs", "kilnstone/terracotta_slag_brick_stairs",
				new String[] {"copper_inferno:terracotta_slag_bricks", "", "", "copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "", "copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks"},
				"copper_inferno:terracotta_slag_brick_stairs", 4, "Craft 4x Terracotta Slag Brick Stairs at a crafting table.", "Stellt 4x Terrakottaschlackenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_brick_wall", "copper_inferno:terracotta_slag_brick_wall", "kilnstone/terracotta_slag_brick_wall",
				new String[] {"copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "copper_inferno:terracotta_slag_bricks", "", "", ""},
				"copper_inferno:terracotta_slag_brick_wall", 6, "Craft 6x Terracotta Slag Brick Wall at a crafting table.", "Stellt 6x Terrakottaschlackenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_terracotta_slag_bricks", "copper_inferno:cracked_terracotta_slag_bricks", "kilnstone/cracked_terracotta_slag_bricks",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag_bricks", "", "", "", ""},
				"copper_inferno:cracked_terracotta_slag_bricks", 1, "Smelting Terracotta Slag Bricks in a furnace yields Cracked Terracotta Slag Bricks.", "Terrakottaschlackenziegel im Ofen gebrannt ergibt Rissige Terrakottaschlackenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_terracotta_slag_bricks", "copper_inferno:chiseled_terracotta_slag_bricks", "kilnstone/chiseled_terracotta_slag_bricks",
				new String[] {"copper_inferno:terracotta_slag_brick_slab", "", "", "copper_inferno:terracotta_slag_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_terracotta_slag_bricks", 1, "Craft 1x Chiseled Terracotta Slag Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Terrakottaschlackenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_pillar", "copper_inferno:terracotta_slag_pillar", "kilnstone/terracotta_slag_pillar",
				new String[] {"copper_inferno:terracotta_slag_bricks", "", "", "copper_inferno:terracotta_slag_bricks", "", "", "", "", ""},
				"copper_inferno:terracotta_slag_pillar", 2, "Craft 2x Terracotta Slag Pillar at a crafting table.", "Stellt 2x Terrakottaschlackens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_slab_from_terracotta_slag_stonecutting", "copper_inferno:terracotta_slag_slab", "kilnstone/terracotta_slag_slab_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_slab", 2, "Stonecutting: cut 2x Terracotta Slag Slab from Terracotta Slag.", "Steins\u00e4ge: 2x Terrakottaschlackenstufe aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_stairs_from_terracotta_slag_stonecutting", "copper_inferno:terracotta_slag_stairs", "kilnstone/terracotta_slag_stairs_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_stairs", 1, "Stonecutting: cut 1x Terracotta Slag Stairs from Terracotta Slag.", "Steins\u00e4ge: 1x Terrakottaschlackentreppe aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_wall_from_terracotta_slag_stonecutting", "copper_inferno:terracotta_slag_wall", "kilnstone/terracotta_slag_wall_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_wall", 1, "Stonecutting: cut 1x Terracotta Slag Wall from Terracotta Slag.", "Steins\u00e4ge: 1x Terrakottaschlackenmauer aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_terracotta_slag_from_terracotta_slag_stonecutting", "copper_inferno:polished_terracotta_slag", "kilnstone/polished_terracotta_slag_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:polished_terracotta_slag", 1, "Stonecutting: cut 1x Polished Terracotta Slag from Terracotta Slag.", "Steins\u00e4ge: 1x Polierte Terrakottaschlacke aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_terracotta_slag_slab_from_terracotta_slag_stonecutting", "copper_inferno:polished_terracotta_slag_slab", "kilnstone/polished_terracotta_slag_slab_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:polished_terracotta_slag_slab", 2, "Stonecutting: cut 2x Polished Terracotta Slag Slab from Terracotta Slag.", "Steins\u00e4ge: 2x Polierte Terrakottaschlackenstufe aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_terracotta_slag_stairs_from_terracotta_slag_stonecutting", "copper_inferno:polished_terracotta_slag_stairs", "kilnstone/polished_terracotta_slag_stairs_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:polished_terracotta_slag_stairs", 1, "Stonecutting: cut 1x Polished Terracotta Slag Stairs from Terracotta Slag.", "Steins\u00e4ge: 1x Polierte Terrakottaschlackentreppe aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_terracotta_slag_wall_from_terracotta_slag_stonecutting", "copper_inferno:polished_terracotta_slag_wall", "kilnstone/polished_terracotta_slag_wall_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:polished_terracotta_slag_wall", 1, "Stonecutting: cut 1x Polished Terracotta Slag Wall from Terracotta Slag.", "Steins\u00e4ge: 1x Polierte Terrakottaschlackenmauer aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_bricks_from_terracotta_slag_stonecutting", "copper_inferno:terracotta_slag_bricks", "kilnstone/terracotta_slag_bricks_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_bricks", 1, "Stonecutting: cut 1x Terracotta Slag Bricks from Terracotta Slag.", "Steins\u00e4ge: 1x Terrakottaschlackenziegel aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_brick_slab_from_terracotta_slag_stonecutting", "copper_inferno:terracotta_slag_brick_slab", "kilnstone/terracotta_slag_brick_slab_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_brick_slab", 2, "Stonecutting: cut 2x Terracotta Slag Brick Slab from Terracotta Slag.", "Steins\u00e4ge: 2x Terrakottaschlackenziegelstufe aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_brick_stairs_from_terracotta_slag_stonecutting", "copper_inferno:terracotta_slag_brick_stairs", "kilnstone/terracotta_slag_brick_stairs_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_brick_stairs", 1, "Stonecutting: cut 1x Terracotta Slag Brick Stairs from Terracotta Slag.", "Steins\u00e4ge: 1x Terrakottaschlackenziegeltreppe aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_brick_wall_from_terracotta_slag_stonecutting", "copper_inferno:terracotta_slag_brick_wall", "kilnstone/terracotta_slag_brick_wall_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_brick_wall", 1, "Stonecutting: cut 1x Terracotta Slag Brick Wall from Terracotta Slag.", "Steins\u00e4ge: 1x Terrakottaschlackenziegelmauer aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_tiles_from_terracotta_slag_stonecutting", "copper_inferno:terracotta_slag_tiles", "kilnstone/terracotta_slag_tiles_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_tiles", 1, "Stonecutting: cut 1x Terracotta Slag Tiles from Terracotta Slag.", "Steins\u00e4ge: 1x Terrakottaschlackenfliesen aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_terracotta_slag_bricks_from_terracotta_slag_stonecutting", "copper_inferno:chiseled_terracotta_slag_bricks", "kilnstone/chiseled_terracotta_slag_bricks_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:chiseled_terracotta_slag_bricks", 1, "Stonecutting: cut 1x Chiseled Terracotta Slag Bricks from Terracotta Slag.", "Steins\u00e4ge: 1x Gemei\u00dfelte Terrakottaschlackenziegel aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/terracotta_slag_pillar_from_terracotta_slag_stonecutting", "copper_inferno:terracotta_slag_pillar", "kilnstone/terracotta_slag_pillar_from_terracotta_slag_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:terracotta_slag", "", "", "", ""},
				"copper_inferno:terracotta_slag_pillar", 1, "Stonecutting: cut 1x Terracotta Slag Pillar from Terracotta Slag.", "Steins\u00e4ge: 1x Terrakottaschlackens\u00e4ule aus Terrakottaschlacke schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic", "copper_inferno:kiln_ceramic", "kilnstone/kiln_ceramic",
				new String[] {"minecraft:brick", "", "minecraft:brick", "", "copper_inferno:terracotta_slag", "", "minecraft:brick", "", "minecraft:brick"},
				"copper_inferno:kiln_ceramic", 4, "Craft 4x Kiln Ceramic at a crafting table.", "Stellt 4x Ofenkeramik an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "kilnstone/kiln_ceramic_bricks",
				new String[] {"copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "", "copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_bricks", 4, "Craft 4x Kiln Ceramic Bricks at a crafting table.", "Stellt 4x Ofenkeramikziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_tiles", "copper_inferno:kiln_ceramic_tiles", "kilnstone/kiln_ceramic_tiles",
				new String[] {"copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "", "copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "", "", "", ""},
				"copper_inferno:kiln_ceramic_tiles", 4, "Craft 4x Kiln Ceramic Tiles at a crafting table.", "Stellt 4x Ofenkeramikfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "kilnstone/polished_kiln_ceramic",
				new String[] {"copper_inferno:kiln_ceramic_tiles", "copper_inferno:kiln_ceramic_tiles", "", "copper_inferno:kiln_ceramic_tiles", "copper_inferno:kiln_ceramic_tiles", "", "", "", ""},
				"copper_inferno:polished_kiln_ceramic", 4, "Craft 4x Polished Kiln Ceramic at a crafting table.", "Stellt 4x Polierte Ofenkeramik an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_slab", "copper_inferno:kiln_ceramic_slab", "kilnstone/kiln_ceramic_slab",
				new String[] {"copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "", "", "", "", "", ""},
				"copper_inferno:kiln_ceramic_slab", 6, "Craft 6x Kiln Ceramic Slab at a crafting table.", "Stellt 6x Ofenkeramikstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_stairs", "copper_inferno:kiln_ceramic_stairs", "kilnstone/kiln_ceramic_stairs",
				new String[] {"copper_inferno:kiln_ceramic", "", "", "copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "", "copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic"},
				"copper_inferno:kiln_ceramic_stairs", 4, "Craft 4x Kiln Ceramic Stairs at a crafting table.", "Stellt 4x Ofenkeramiktreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_wall", "copper_inferno:kiln_ceramic_wall", "kilnstone/kiln_ceramic_wall",
				new String[] {"copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "copper_inferno:kiln_ceramic", "", "", ""},
				"copper_inferno:kiln_ceramic_wall", 6, "Craft 6x Kiln Ceramic Wall at a crafting table.", "Stellt 6x Ofenkeramikmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kiln_ceramic_slab", "copper_inferno:polished_kiln_ceramic_slab", "kilnstone/polished_kiln_ceramic_slab",
				new String[] {"copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "", "", "", "", "", ""},
				"copper_inferno:polished_kiln_ceramic_slab", 6, "Craft 6x Polished Kiln Ceramic Slab at a crafting table.", "Stellt 6x Polierte Ofenkeramikstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kiln_ceramic_stairs", "copper_inferno:polished_kiln_ceramic_stairs", "kilnstone/polished_kiln_ceramic_stairs",
				new String[] {"copper_inferno:polished_kiln_ceramic", "", "", "copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "", "copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic"},
				"copper_inferno:polished_kiln_ceramic_stairs", 4, "Craft 4x Polished Kiln Ceramic Stairs at a crafting table.", "Stellt 4x Polierte Ofenkeramiktreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kiln_ceramic_wall", "copper_inferno:polished_kiln_ceramic_wall", "kilnstone/polished_kiln_ceramic_wall",
				new String[] {"copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "copper_inferno:polished_kiln_ceramic", "", "", ""},
				"copper_inferno:polished_kiln_ceramic_wall", 6, "Craft 6x Polished Kiln Ceramic Wall at a crafting table.", "Stellt 6x Polierte Ofenkeramikmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_brick_slab", "copper_inferno:kiln_ceramic_brick_slab", "kilnstone/kiln_ceramic_brick_slab",
				new String[] {"copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "", "", "", "", "", ""},
				"copper_inferno:kiln_ceramic_brick_slab", 6, "Craft 6x Kiln Ceramic Brick Slab at a crafting table.", "Stellt 6x Ofenkeramikziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_brick_stairs", "copper_inferno:kiln_ceramic_brick_stairs", "kilnstone/kiln_ceramic_brick_stairs",
				new String[] {"copper_inferno:kiln_ceramic_bricks", "", "", "copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "", "copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks"},
				"copper_inferno:kiln_ceramic_brick_stairs", 4, "Craft 4x Kiln Ceramic Brick Stairs at a crafting table.", "Stellt 4x Ofenkeramikziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_brick_wall", "copper_inferno:kiln_ceramic_brick_wall", "kilnstone/kiln_ceramic_brick_wall",
				new String[] {"copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "copper_inferno:kiln_ceramic_bricks", "", "", ""},
				"copper_inferno:kiln_ceramic_brick_wall", 6, "Craft 6x Kiln Ceramic Brick Wall at a crafting table.", "Stellt 6x Ofenkeramikziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_kiln_ceramic_bricks", "copper_inferno:cracked_kiln_ceramic_bricks", "kilnstone/cracked_kiln_ceramic_bricks",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic_bricks", "", "", "", ""},
				"copper_inferno:cracked_kiln_ceramic_bricks", 1, "Smelting Kiln Ceramic Bricks in a furnace yields Cracked Kiln Ceramic Bricks.", "Ofenkeramikziegel im Ofen gebrannt ergibt Rissige Ofenkeramikziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_kiln_ceramic_bricks", "copper_inferno:chiseled_kiln_ceramic_bricks", "kilnstone/chiseled_kiln_ceramic_bricks",
				new String[] {"copper_inferno:kiln_ceramic_brick_slab", "", "", "copper_inferno:kiln_ceramic_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_kiln_ceramic_bricks", 1, "Craft 1x Chiseled Kiln Ceramic Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Ofenkeramikziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_pillar", "copper_inferno:kiln_ceramic_pillar", "kilnstone/kiln_ceramic_pillar",
				new String[] {"copper_inferno:kiln_ceramic_bricks", "", "", "copper_inferno:kiln_ceramic_bricks", "", "", "", "", ""},
				"copper_inferno:kiln_ceramic_pillar", 2, "Craft 2x Kiln Ceramic Pillar at a crafting table.", "Stellt 2x Ofenkeramiks\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_slab_from_kiln_ceramic_stonecutting", "copper_inferno:kiln_ceramic_slab", "kilnstone/kiln_ceramic_slab_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_slab", 2, "Stonecutting: cut 2x Kiln Ceramic Slab from Kiln Ceramic.", "Steins\u00e4ge: 2x Ofenkeramikstufe aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_stairs_from_kiln_ceramic_stonecutting", "copper_inferno:kiln_ceramic_stairs", "kilnstone/kiln_ceramic_stairs_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_stairs", 1, "Stonecutting: cut 1x Kiln Ceramic Stairs from Kiln Ceramic.", "Steins\u00e4ge: 1x Ofenkeramiktreppe aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_wall_from_kiln_ceramic_stonecutting", "copper_inferno:kiln_ceramic_wall", "kilnstone/kiln_ceramic_wall_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_wall", 1, "Stonecutting: cut 1x Kiln Ceramic Wall from Kiln Ceramic.", "Steins\u00e4ge: 1x Ofenkeramikmauer aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kiln_ceramic_from_kiln_ceramic_stonecutting", "copper_inferno:polished_kiln_ceramic", "kilnstone/polished_kiln_ceramic_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:polished_kiln_ceramic", 1, "Stonecutting: cut 1x Polished Kiln Ceramic from Kiln Ceramic.", "Steins\u00e4ge: 1x Polierte Ofenkeramik aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kiln_ceramic_slab_from_kiln_ceramic_stonecutting", "copper_inferno:polished_kiln_ceramic_slab", "kilnstone/polished_kiln_ceramic_slab_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:polished_kiln_ceramic_slab", 2, "Stonecutting: cut 2x Polished Kiln Ceramic Slab from Kiln Ceramic.", "Steins\u00e4ge: 2x Polierte Ofenkeramikstufe aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kiln_ceramic_stairs_from_kiln_ceramic_stonecutting", "copper_inferno:polished_kiln_ceramic_stairs", "kilnstone/polished_kiln_ceramic_stairs_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:polished_kiln_ceramic_stairs", 1, "Stonecutting: cut 1x Polished Kiln Ceramic Stairs from Kiln Ceramic.", "Steins\u00e4ge: 1x Polierte Ofenkeramiktreppe aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_kiln_ceramic_wall_from_kiln_ceramic_stonecutting", "copper_inferno:polished_kiln_ceramic_wall", "kilnstone/polished_kiln_ceramic_wall_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:polished_kiln_ceramic_wall", 1, "Stonecutting: cut 1x Polished Kiln Ceramic Wall from Kiln Ceramic.", "Steins\u00e4ge: 1x Polierte Ofenkeramikmauer aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_bricks_from_kiln_ceramic_stonecutting", "copper_inferno:kiln_ceramic_bricks", "kilnstone/kiln_ceramic_bricks_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_bricks", 1, "Stonecutting: cut 1x Kiln Ceramic Bricks from Kiln Ceramic.", "Steins\u00e4ge: 1x Ofenkeramikziegel aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_brick_slab_from_kiln_ceramic_stonecutting", "copper_inferno:kiln_ceramic_brick_slab", "kilnstone/kiln_ceramic_brick_slab_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_brick_slab", 2, "Stonecutting: cut 2x Kiln Ceramic Brick Slab from Kiln Ceramic.", "Steins\u00e4ge: 2x Ofenkeramikziegelstufe aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_brick_stairs_from_kiln_ceramic_stonecutting", "copper_inferno:kiln_ceramic_brick_stairs", "kilnstone/kiln_ceramic_brick_stairs_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_brick_stairs", 1, "Stonecutting: cut 1x Kiln Ceramic Brick Stairs from Kiln Ceramic.", "Steins\u00e4ge: 1x Ofenkeramikziegeltreppe aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_brick_wall_from_kiln_ceramic_stonecutting", "copper_inferno:kiln_ceramic_brick_wall", "kilnstone/kiln_ceramic_brick_wall_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_brick_wall", 1, "Stonecutting: cut 1x Kiln Ceramic Brick Wall from Kiln Ceramic.", "Steins\u00e4ge: 1x Ofenkeramikziegelmauer aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_tiles_from_kiln_ceramic_stonecutting", "copper_inferno:kiln_ceramic_tiles", "kilnstone/kiln_ceramic_tiles_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_tiles", 1, "Stonecutting: cut 1x Kiln Ceramic Tiles from Kiln Ceramic.", "Steins\u00e4ge: 1x Ofenkeramikfliesen aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_kiln_ceramic_bricks_from_kiln_ceramic_stonecutting", "copper_inferno:chiseled_kiln_ceramic_bricks", "kilnstone/chiseled_kiln_ceramic_bricks_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:chiseled_kiln_ceramic_bricks", 1, "Stonecutting: cut 1x Chiseled Kiln Ceramic Bricks from Kiln Ceramic.", "Steins\u00e4ge: 1x Gemei\u00dfelte Ofenkeramikziegel aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/kiln_ceramic_pillar_from_kiln_ceramic_stonecutting", "copper_inferno:kiln_ceramic_pillar", "kilnstone/kiln_ceramic_pillar_from_kiln_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:kiln_ceramic", "", "", "", ""},
				"copper_inferno:kiln_ceramic_pillar", 1, "Stonecutting: cut 1x Kiln Ceramic Pillar from Kiln Ceramic.", "Steins\u00e4ge: 1x Ofenkeramiks\u00e4ule aus Ofenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware", "copper_inferno:emberware", "kilnstone/emberware",
				new String[] {"minecraft:blaze_powder", "", "minecraft:blaze_powder", "", "copper_inferno:kiln_ceramic", "", "minecraft:blaze_powder", "", "minecraft:blaze_powder"},
				"copper_inferno:emberware", 4, "Craft 4x Emberware at a crafting table.", "Stellt 4x Glutware an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_bricks", "copper_inferno:emberware_bricks", "kilnstone/emberware_bricks",
				new String[] {"copper_inferno:emberware", "copper_inferno:emberware", "", "copper_inferno:emberware", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_bricks", 4, "Craft 4x Emberware Bricks at a crafting table.", "Stellt 4x Glutwarenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_tiles", "copper_inferno:emberware_tiles", "kilnstone/emberware_tiles",
				new String[] {"copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "", "copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "", "", "", ""},
				"copper_inferno:emberware_tiles", 4, "Craft 4x Emberware Tiles at a crafting table.", "Stellt 4x Glutwarenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_emberware", "copper_inferno:polished_emberware", "kilnstone/polished_emberware",
				new String[] {"copper_inferno:emberware_tiles", "copper_inferno:emberware_tiles", "", "copper_inferno:emberware_tiles", "copper_inferno:emberware_tiles", "", "", "", ""},
				"copper_inferno:polished_emberware", 4, "Craft 4x Polished Emberware at a crafting table.", "Stellt 4x Polierte Glutware an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_slab", "copper_inferno:emberware_slab", "kilnstone/emberware_slab",
				new String[] {"copper_inferno:emberware", "copper_inferno:emberware", "copper_inferno:emberware", "", "", "", "", "", ""},
				"copper_inferno:emberware_slab", 6, "Craft 6x Emberware Slab at a crafting table.", "Stellt 6x Glutwarenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_stairs", "copper_inferno:emberware_stairs", "kilnstone/emberware_stairs",
				new String[] {"copper_inferno:emberware", "", "", "copper_inferno:emberware", "copper_inferno:emberware", "", "copper_inferno:emberware", "copper_inferno:emberware", "copper_inferno:emberware"},
				"copper_inferno:emberware_stairs", 4, "Craft 4x Emberware Stairs at a crafting table.", "Stellt 4x Glutwarentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_wall", "copper_inferno:emberware_wall", "kilnstone/emberware_wall",
				new String[] {"copper_inferno:emberware", "copper_inferno:emberware", "copper_inferno:emberware", "copper_inferno:emberware", "copper_inferno:emberware", "copper_inferno:emberware", "", "", ""},
				"copper_inferno:emberware_wall", 6, "Craft 6x Emberware Wall at a crafting table.", "Stellt 6x Glutwarenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_emberware_slab", "copper_inferno:polished_emberware_slab", "kilnstone/polished_emberware_slab",
				new String[] {"copper_inferno:polished_emberware", "copper_inferno:polished_emberware", "copper_inferno:polished_emberware", "", "", "", "", "", ""},
				"copper_inferno:polished_emberware_slab", 6, "Craft 6x Polished Emberware Slab at a crafting table.", "Stellt 6x Polierte Glutwarenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_emberware_stairs", "copper_inferno:polished_emberware_stairs", "kilnstone/polished_emberware_stairs",
				new String[] {"copper_inferno:polished_emberware", "", "", "copper_inferno:polished_emberware", "copper_inferno:polished_emberware", "", "copper_inferno:polished_emberware", "copper_inferno:polished_emberware", "copper_inferno:polished_emberware"},
				"copper_inferno:polished_emberware_stairs", 4, "Craft 4x Polished Emberware Stairs at a crafting table.", "Stellt 4x Polierte Glutwarentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_emberware_wall", "copper_inferno:polished_emberware_wall", "kilnstone/polished_emberware_wall",
				new String[] {"copper_inferno:polished_emberware", "copper_inferno:polished_emberware", "copper_inferno:polished_emberware", "copper_inferno:polished_emberware", "copper_inferno:polished_emberware", "copper_inferno:polished_emberware", "", "", ""},
				"copper_inferno:polished_emberware_wall", 6, "Craft 6x Polished Emberware Wall at a crafting table.", "Stellt 6x Polierte Glutwarenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_brick_slab", "copper_inferno:emberware_brick_slab", "kilnstone/emberware_brick_slab",
				new String[] {"copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "", "", "", "", "", ""},
				"copper_inferno:emberware_brick_slab", 6, "Craft 6x Emberware Brick Slab at a crafting table.", "Stellt 6x Glutwarenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_brick_stairs", "copper_inferno:emberware_brick_stairs", "kilnstone/emberware_brick_stairs",
				new String[] {"copper_inferno:emberware_bricks", "", "", "copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "", "copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks"},
				"copper_inferno:emberware_brick_stairs", 4, "Craft 4x Emberware Brick Stairs at a crafting table.", "Stellt 4x Glutwarenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_brick_wall", "copper_inferno:emberware_brick_wall", "kilnstone/emberware_brick_wall",
				new String[] {"copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "copper_inferno:emberware_bricks", "", "", ""},
				"copper_inferno:emberware_brick_wall", 6, "Craft 6x Emberware Brick Wall at a crafting table.", "Stellt 6x Glutwarenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_emberware_bricks", "copper_inferno:cracked_emberware_bricks", "kilnstone/cracked_emberware_bricks",
				new String[] {"", "", "", "", "copper_inferno:emberware_bricks", "", "", "", ""},
				"copper_inferno:cracked_emberware_bricks", 1, "Smelting Emberware Bricks in a furnace yields Cracked Emberware Bricks.", "Glutwarenziegel im Ofen gebrannt ergibt Rissige Glutwarenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_emberware_bricks", "copper_inferno:chiseled_emberware_bricks", "kilnstone/chiseled_emberware_bricks",
				new String[] {"copper_inferno:emberware_brick_slab", "", "", "copper_inferno:emberware_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_emberware_bricks", 1, "Craft 1x Chiseled Emberware Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glutwarenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_pillar", "copper_inferno:emberware_pillar", "kilnstone/emberware_pillar",
				new String[] {"copper_inferno:emberware_bricks", "", "", "copper_inferno:emberware_bricks", "", "", "", "", ""},
				"copper_inferno:emberware_pillar", 2, "Craft 2x Emberware Pillar at a crafting table.", "Stellt 2x Glutwarens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_slab_from_emberware_stonecutting", "copper_inferno:emberware_slab", "kilnstone/emberware_slab_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_slab", 2, "Stonecutting: cut 2x Emberware Slab from Emberware.", "Steins\u00e4ge: 2x Glutwarenstufe aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_stairs_from_emberware_stonecutting", "copper_inferno:emberware_stairs", "kilnstone/emberware_stairs_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_stairs", 1, "Stonecutting: cut 1x Emberware Stairs from Emberware.", "Steins\u00e4ge: 1x Glutwarentreppe aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_wall_from_emberware_stonecutting", "copper_inferno:emberware_wall", "kilnstone/emberware_wall_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_wall", 1, "Stonecutting: cut 1x Emberware Wall from Emberware.", "Steins\u00e4ge: 1x Glutwarenmauer aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_emberware_from_emberware_stonecutting", "copper_inferno:polished_emberware", "kilnstone/polished_emberware_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:polished_emberware", 1, "Stonecutting: cut 1x Polished Emberware from Emberware.", "Steins\u00e4ge: 1x Polierte Glutware aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_emberware_slab_from_emberware_stonecutting", "copper_inferno:polished_emberware_slab", "kilnstone/polished_emberware_slab_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:polished_emberware_slab", 2, "Stonecutting: cut 2x Polished Emberware Slab from Emberware.", "Steins\u00e4ge: 2x Polierte Glutwarenstufe aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_emberware_stairs_from_emberware_stonecutting", "copper_inferno:polished_emberware_stairs", "kilnstone/polished_emberware_stairs_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:polished_emberware_stairs", 1, "Stonecutting: cut 1x Polished Emberware Stairs from Emberware.", "Steins\u00e4ge: 1x Polierte Glutwarentreppe aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_emberware_wall_from_emberware_stonecutting", "copper_inferno:polished_emberware_wall", "kilnstone/polished_emberware_wall_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:polished_emberware_wall", 1, "Stonecutting: cut 1x Polished Emberware Wall from Emberware.", "Steins\u00e4ge: 1x Polierte Glutwarenmauer aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_bricks_from_emberware_stonecutting", "copper_inferno:emberware_bricks", "kilnstone/emberware_bricks_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_bricks", 1, "Stonecutting: cut 1x Emberware Bricks from Emberware.", "Steins\u00e4ge: 1x Glutwarenziegel aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_brick_slab_from_emberware_stonecutting", "copper_inferno:emberware_brick_slab", "kilnstone/emberware_brick_slab_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_brick_slab", 2, "Stonecutting: cut 2x Emberware Brick Slab from Emberware.", "Steins\u00e4ge: 2x Glutwarenziegelstufe aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_brick_stairs_from_emberware_stonecutting", "copper_inferno:emberware_brick_stairs", "kilnstone/emberware_brick_stairs_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_brick_stairs", 1, "Stonecutting: cut 1x Emberware Brick Stairs from Emberware.", "Steins\u00e4ge: 1x Glutwarenziegeltreppe aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_brick_wall_from_emberware_stonecutting", "copper_inferno:emberware_brick_wall", "kilnstone/emberware_brick_wall_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_brick_wall", 1, "Stonecutting: cut 1x Emberware Brick Wall from Emberware.", "Steins\u00e4ge: 1x Glutwarenziegelmauer aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_tiles_from_emberware_stonecutting", "copper_inferno:emberware_tiles", "kilnstone/emberware_tiles_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_tiles", 1, "Stonecutting: cut 1x Emberware Tiles from Emberware.", "Steins\u00e4ge: 1x Glutwarenfliesen aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_emberware_bricks_from_emberware_stonecutting", "copper_inferno:chiseled_emberware_bricks", "kilnstone/chiseled_emberware_bricks_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:chiseled_emberware_bricks", 1, "Stonecutting: cut 1x Chiseled Emberware Bricks from Emberware.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glutwarenziegel aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/emberware_pillar_from_emberware_stonecutting", "copper_inferno:emberware_pillar", "kilnstone/emberware_pillar_from_emberware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberware", "", "", "", ""},
				"copper_inferno:emberware_pillar", 1, "Stonecutting: cut 1x Emberware Pillar from Emberware.", "Steins\u00e4ge: 1x Glutwarens\u00e4ule aus Glutware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware", "copper_inferno:sootware", "kilnstone/sootware",
				new String[] {"minecraft:coal", "", "minecraft:coal", "", "copper_inferno:emberware", "", "minecraft:coal", "", "minecraft:coal"},
				"copper_inferno:sootware", 4, "Craft 4x Sootware at a crafting table.", "Stellt 4x Ru\u00dfware an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_bricks", "copper_inferno:sootware_bricks", "kilnstone/sootware_bricks",
				new String[] {"copper_inferno:sootware", "copper_inferno:sootware", "", "copper_inferno:sootware", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_bricks", 4, "Craft 4x Sootware Bricks at a crafting table.", "Stellt 4x Ru\u00dfwarenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_tiles", "copper_inferno:sootware_tiles", "kilnstone/sootware_tiles",
				new String[] {"copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "", "copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "", "", "", ""},
				"copper_inferno:sootware_tiles", 4, "Craft 4x Sootware Tiles at a crafting table.", "Stellt 4x Ru\u00dfwarenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_sootware", "copper_inferno:polished_sootware", "kilnstone/polished_sootware",
				new String[] {"copper_inferno:sootware_tiles", "copper_inferno:sootware_tiles", "", "copper_inferno:sootware_tiles", "copper_inferno:sootware_tiles", "", "", "", ""},
				"copper_inferno:polished_sootware", 4, "Craft 4x Polished Sootware at a crafting table.", "Stellt 4x Polierte Ru\u00dfware an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_slab", "copper_inferno:sootware_slab", "kilnstone/sootware_slab",
				new String[] {"copper_inferno:sootware", "copper_inferno:sootware", "copper_inferno:sootware", "", "", "", "", "", ""},
				"copper_inferno:sootware_slab", 6, "Craft 6x Sootware Slab at a crafting table.", "Stellt 6x Ru\u00dfwarenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_stairs", "copper_inferno:sootware_stairs", "kilnstone/sootware_stairs",
				new String[] {"copper_inferno:sootware", "", "", "copper_inferno:sootware", "copper_inferno:sootware", "", "copper_inferno:sootware", "copper_inferno:sootware", "copper_inferno:sootware"},
				"copper_inferno:sootware_stairs", 4, "Craft 4x Sootware Stairs at a crafting table.", "Stellt 4x Ru\u00dfwarentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_wall", "copper_inferno:sootware_wall", "kilnstone/sootware_wall",
				new String[] {"copper_inferno:sootware", "copper_inferno:sootware", "copper_inferno:sootware", "copper_inferno:sootware", "copper_inferno:sootware", "copper_inferno:sootware", "", "", ""},
				"copper_inferno:sootware_wall", 6, "Craft 6x Sootware Wall at a crafting table.", "Stellt 6x Ru\u00dfwarenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_sootware_slab", "copper_inferno:polished_sootware_slab", "kilnstone/polished_sootware_slab",
				new String[] {"copper_inferno:polished_sootware", "copper_inferno:polished_sootware", "copper_inferno:polished_sootware", "", "", "", "", "", ""},
				"copper_inferno:polished_sootware_slab", 6, "Craft 6x Polished Sootware Slab at a crafting table.", "Stellt 6x Polierte Ru\u00dfwarenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_sootware_stairs", "copper_inferno:polished_sootware_stairs", "kilnstone/polished_sootware_stairs",
				new String[] {"copper_inferno:polished_sootware", "", "", "copper_inferno:polished_sootware", "copper_inferno:polished_sootware", "", "copper_inferno:polished_sootware", "copper_inferno:polished_sootware", "copper_inferno:polished_sootware"},
				"copper_inferno:polished_sootware_stairs", 4, "Craft 4x Polished Sootware Stairs at a crafting table.", "Stellt 4x Polierte Ru\u00dfwarentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_sootware_wall", "copper_inferno:polished_sootware_wall", "kilnstone/polished_sootware_wall",
				new String[] {"copper_inferno:polished_sootware", "copper_inferno:polished_sootware", "copper_inferno:polished_sootware", "copper_inferno:polished_sootware", "copper_inferno:polished_sootware", "copper_inferno:polished_sootware", "", "", ""},
				"copper_inferno:polished_sootware_wall", 6, "Craft 6x Polished Sootware Wall at a crafting table.", "Stellt 6x Polierte Ru\u00dfwarenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_brick_slab", "copper_inferno:sootware_brick_slab", "kilnstone/sootware_brick_slab",
				new String[] {"copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "", "", "", "", "", ""},
				"copper_inferno:sootware_brick_slab", 6, "Craft 6x Sootware Brick Slab at a crafting table.", "Stellt 6x Ru\u00dfwarenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_brick_stairs", "copper_inferno:sootware_brick_stairs", "kilnstone/sootware_brick_stairs",
				new String[] {"copper_inferno:sootware_bricks", "", "", "copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "", "copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks"},
				"copper_inferno:sootware_brick_stairs", 4, "Craft 4x Sootware Brick Stairs at a crafting table.", "Stellt 4x Ru\u00dfwarenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_brick_wall", "copper_inferno:sootware_brick_wall", "kilnstone/sootware_brick_wall",
				new String[] {"copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "copper_inferno:sootware_bricks", "", "", ""},
				"copper_inferno:sootware_brick_wall", 6, "Craft 6x Sootware Brick Wall at a crafting table.", "Stellt 6x Ru\u00dfwarenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_sootware_bricks", "copper_inferno:cracked_sootware_bricks", "kilnstone/cracked_sootware_bricks",
				new String[] {"", "", "", "", "copper_inferno:sootware_bricks", "", "", "", ""},
				"copper_inferno:cracked_sootware_bricks", 1, "Smelting Sootware Bricks in a furnace yields Cracked Sootware Bricks.", "Ru\u00dfwarenziegel im Ofen gebrannt ergibt Rissige Ru\u00dfwarenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_sootware_bricks", "copper_inferno:chiseled_sootware_bricks", "kilnstone/chiseled_sootware_bricks",
				new String[] {"copper_inferno:sootware_brick_slab", "", "", "copper_inferno:sootware_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_sootware_bricks", 1, "Craft 1x Chiseled Sootware Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Ru\u00dfwarenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_pillar", "copper_inferno:sootware_pillar", "kilnstone/sootware_pillar",
				new String[] {"copper_inferno:sootware_bricks", "", "", "copper_inferno:sootware_bricks", "", "", "", "", ""},
				"copper_inferno:sootware_pillar", 2, "Craft 2x Sootware Pillar at a crafting table.", "Stellt 2x Ru\u00dfwarens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_slab_from_sootware_stonecutting", "copper_inferno:sootware_slab", "kilnstone/sootware_slab_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_slab", 2, "Stonecutting: cut 2x Sootware Slab from Sootware.", "Steins\u00e4ge: 2x Ru\u00dfwarenstufe aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_stairs_from_sootware_stonecutting", "copper_inferno:sootware_stairs", "kilnstone/sootware_stairs_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_stairs", 1, "Stonecutting: cut 1x Sootware Stairs from Sootware.", "Steins\u00e4ge: 1x Ru\u00dfwarentreppe aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_wall_from_sootware_stonecutting", "copper_inferno:sootware_wall", "kilnstone/sootware_wall_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_wall", 1, "Stonecutting: cut 1x Sootware Wall from Sootware.", "Steins\u00e4ge: 1x Ru\u00dfwarenmauer aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_sootware_from_sootware_stonecutting", "copper_inferno:polished_sootware", "kilnstone/polished_sootware_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:polished_sootware", 1, "Stonecutting: cut 1x Polished Sootware from Sootware.", "Steins\u00e4ge: 1x Polierte Ru\u00dfware aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_sootware_slab_from_sootware_stonecutting", "copper_inferno:polished_sootware_slab", "kilnstone/polished_sootware_slab_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:polished_sootware_slab", 2, "Stonecutting: cut 2x Polished Sootware Slab from Sootware.", "Steins\u00e4ge: 2x Polierte Ru\u00dfwarenstufe aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_sootware_stairs_from_sootware_stonecutting", "copper_inferno:polished_sootware_stairs", "kilnstone/polished_sootware_stairs_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:polished_sootware_stairs", 1, "Stonecutting: cut 1x Polished Sootware Stairs from Sootware.", "Steins\u00e4ge: 1x Polierte Ru\u00dfwarentreppe aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_sootware_wall_from_sootware_stonecutting", "copper_inferno:polished_sootware_wall", "kilnstone/polished_sootware_wall_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:polished_sootware_wall", 1, "Stonecutting: cut 1x Polished Sootware Wall from Sootware.", "Steins\u00e4ge: 1x Polierte Ru\u00dfwarenmauer aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_bricks_from_sootware_stonecutting", "copper_inferno:sootware_bricks", "kilnstone/sootware_bricks_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_bricks", 1, "Stonecutting: cut 1x Sootware Bricks from Sootware.", "Steins\u00e4ge: 1x Ru\u00dfwarenziegel aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_brick_slab_from_sootware_stonecutting", "copper_inferno:sootware_brick_slab", "kilnstone/sootware_brick_slab_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_brick_slab", 2, "Stonecutting: cut 2x Sootware Brick Slab from Sootware.", "Steins\u00e4ge: 2x Ru\u00dfwarenziegelstufe aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_brick_stairs_from_sootware_stonecutting", "copper_inferno:sootware_brick_stairs", "kilnstone/sootware_brick_stairs_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_brick_stairs", 1, "Stonecutting: cut 1x Sootware Brick Stairs from Sootware.", "Steins\u00e4ge: 1x Ru\u00dfwarenziegeltreppe aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_brick_wall_from_sootware_stonecutting", "copper_inferno:sootware_brick_wall", "kilnstone/sootware_brick_wall_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_brick_wall", 1, "Stonecutting: cut 1x Sootware Brick Wall from Sootware.", "Steins\u00e4ge: 1x Ru\u00dfwarenziegelmauer aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_tiles_from_sootware_stonecutting", "copper_inferno:sootware_tiles", "kilnstone/sootware_tiles_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_tiles", 1, "Stonecutting: cut 1x Sootware Tiles from Sootware.", "Steins\u00e4ge: 1x Ru\u00dfwarenfliesen aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_sootware_bricks_from_sootware_stonecutting", "copper_inferno:chiseled_sootware_bricks", "kilnstone/chiseled_sootware_bricks_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:chiseled_sootware_bricks", 1, "Stonecutting: cut 1x Chiseled Sootware Bricks from Sootware.", "Steins\u00e4ge: 1x Gemei\u00dfelte Ru\u00dfwarenziegel aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/sootware_pillar_from_sootware_stonecutting", "copper_inferno:sootware_pillar", "kilnstone/sootware_pillar_from_sootware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sootware", "", "", "", ""},
				"copper_inferno:sootware_pillar", 1, "Stonecutting: cut 1x Sootware Pillar from Sootware.", "Steins\u00e4ge: 1x Ru\u00dfwarens\u00e4ule aus Ru\u00dfware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember", "copper_inferno:glazed_ember", "kilnstone/glazed_ember",
				new String[] {"minecraft:glowstone_dust", "", "minecraft:glowstone_dust", "", "copper_inferno:sootware", "", "minecraft:glowstone_dust", "", "minecraft:glowstone_dust"},
				"copper_inferno:glazed_ember", 4, "Craft 4x Glazed Ember at a crafting table.", "Stellt 4x Glasurglut an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "kilnstone/glazed_ember_bricks",
				new String[] {"copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "", "copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_bricks", 4, "Craft 4x Glazed Ember Bricks at a crafting table.", "Stellt 4x Glasurglutziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_tiles", "copper_inferno:glazed_ember_tiles", "kilnstone/glazed_ember_tiles",
				new String[] {"copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "", "copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "", "", "", ""},
				"copper_inferno:glazed_ember_tiles", 4, "Craft 4x Glazed Ember Tiles at a crafting table.", "Stellt 4x Glasurglutfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_ember", "copper_inferno:polished_glazed_ember", "kilnstone/polished_glazed_ember",
				new String[] {"copper_inferno:glazed_ember_tiles", "copper_inferno:glazed_ember_tiles", "", "copper_inferno:glazed_ember_tiles", "copper_inferno:glazed_ember_tiles", "", "", "", ""},
				"copper_inferno:polished_glazed_ember", 4, "Craft 4x Polished Glazed Ember at a crafting table.", "Stellt 4x Polierte Glasurglut an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_slab", "copper_inferno:glazed_ember_slab", "kilnstone/glazed_ember_slab",
				new String[] {"copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "", "", "", "", "", ""},
				"copper_inferno:glazed_ember_slab", 6, "Craft 6x Glazed Ember Slab at a crafting table.", "Stellt 6x Glasurglutstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_stairs", "copper_inferno:glazed_ember_stairs", "kilnstone/glazed_ember_stairs",
				new String[] {"copper_inferno:glazed_ember", "", "", "copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "", "copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "copper_inferno:glazed_ember"},
				"copper_inferno:glazed_ember_stairs", 4, "Craft 4x Glazed Ember Stairs at a crafting table.", "Stellt 4x Glasurgluttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_wall", "copper_inferno:glazed_ember_wall", "kilnstone/glazed_ember_wall",
				new String[] {"copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "copper_inferno:glazed_ember", "", "", ""},
				"copper_inferno:glazed_ember_wall", 6, "Craft 6x Glazed Ember Wall at a crafting table.", "Stellt 6x Glasurglutmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_ember_slab", "copper_inferno:polished_glazed_ember_slab", "kilnstone/polished_glazed_ember_slab",
				new String[] {"copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember", "", "", "", "", "", ""},
				"copper_inferno:polished_glazed_ember_slab", 6, "Craft 6x Polished Glazed Ember Slab at a crafting table.", "Stellt 6x Polierte Glasurglutstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_ember_stairs", "copper_inferno:polished_glazed_ember_stairs", "kilnstone/polished_glazed_ember_stairs",
				new String[] {"copper_inferno:polished_glazed_ember", "", "", "copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember", "", "copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember"},
				"copper_inferno:polished_glazed_ember_stairs", 4, "Craft 4x Polished Glazed Ember Stairs at a crafting table.", "Stellt 4x Polierte Glasurgluttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_ember_wall", "copper_inferno:polished_glazed_ember_wall", "kilnstone/polished_glazed_ember_wall",
				new String[] {"copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember", "copper_inferno:polished_glazed_ember", "", "", ""},
				"copper_inferno:polished_glazed_ember_wall", 6, "Craft 6x Polished Glazed Ember Wall at a crafting table.", "Stellt 6x Polierte Glasurglutmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_brick_slab", "copper_inferno:glazed_ember_brick_slab", "kilnstone/glazed_ember_brick_slab",
				new String[] {"copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "", "", "", "", "", ""},
				"copper_inferno:glazed_ember_brick_slab", 6, "Craft 6x Glazed Ember Brick Slab at a crafting table.", "Stellt 6x Glasurglutziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_brick_stairs", "copper_inferno:glazed_ember_brick_stairs", "kilnstone/glazed_ember_brick_stairs",
				new String[] {"copper_inferno:glazed_ember_bricks", "", "", "copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "", "copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks"},
				"copper_inferno:glazed_ember_brick_stairs", 4, "Craft 4x Glazed Ember Brick Stairs at a crafting table.", "Stellt 4x Glasurglutziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_brick_wall", "copper_inferno:glazed_ember_brick_wall", "kilnstone/glazed_ember_brick_wall",
				new String[] {"copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "copper_inferno:glazed_ember_bricks", "", "", ""},
				"copper_inferno:glazed_ember_brick_wall", 6, "Craft 6x Glazed Ember Brick Wall at a crafting table.", "Stellt 6x Glasurglutziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_glazed_ember_bricks", "copper_inferno:cracked_glazed_ember_bricks", "kilnstone/cracked_glazed_ember_bricks",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember_bricks", "", "", "", ""},
				"copper_inferno:cracked_glazed_ember_bricks", 1, "Smelting Glazed Ember Bricks in a furnace yields Cracked Glazed Ember Bricks.", "Glasurglutziegel im Ofen gebrannt ergibt Rissige Glasurglutziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_glazed_ember_bricks", "copper_inferno:chiseled_glazed_ember_bricks", "kilnstone/chiseled_glazed_ember_bricks",
				new String[] {"copper_inferno:glazed_ember_brick_slab", "", "", "copper_inferno:glazed_ember_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_glazed_ember_bricks", 1, "Craft 1x Chiseled Glazed Ember Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glasurglutziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_pillar", "copper_inferno:glazed_ember_pillar", "kilnstone/glazed_ember_pillar",
				new String[] {"copper_inferno:glazed_ember_bricks", "", "", "copper_inferno:glazed_ember_bricks", "", "", "", "", ""},
				"copper_inferno:glazed_ember_pillar", 2, "Craft 2x Glazed Ember Pillar at a crafting table.", "Stellt 2x Glasurgluts\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_slab_from_glazed_ember_stonecutting", "copper_inferno:glazed_ember_slab", "kilnstone/glazed_ember_slab_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_slab", 2, "Stonecutting: cut 2x Glazed Ember Slab from Glazed Ember.", "Steins\u00e4ge: 2x Glasurglutstufe aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_stairs_from_glazed_ember_stonecutting", "copper_inferno:glazed_ember_stairs", "kilnstone/glazed_ember_stairs_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_stairs", 1, "Stonecutting: cut 1x Glazed Ember Stairs from Glazed Ember.", "Steins\u00e4ge: 1x Glasurgluttreppe aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_wall_from_glazed_ember_stonecutting", "copper_inferno:glazed_ember_wall", "kilnstone/glazed_ember_wall_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_wall", 1, "Stonecutting: cut 1x Glazed Ember Wall from Glazed Ember.", "Steins\u00e4ge: 1x Glasurglutmauer aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_ember_from_glazed_ember_stonecutting", "copper_inferno:polished_glazed_ember", "kilnstone/polished_glazed_ember_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:polished_glazed_ember", 1, "Stonecutting: cut 1x Polished Glazed Ember from Glazed Ember.", "Steins\u00e4ge: 1x Polierte Glasurglut aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_ember_slab_from_glazed_ember_stonecutting", "copper_inferno:polished_glazed_ember_slab", "kilnstone/polished_glazed_ember_slab_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:polished_glazed_ember_slab", 2, "Stonecutting: cut 2x Polished Glazed Ember Slab from Glazed Ember.", "Steins\u00e4ge: 2x Polierte Glasurglutstufe aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_ember_stairs_from_glazed_ember_stonecutting", "copper_inferno:polished_glazed_ember_stairs", "kilnstone/polished_glazed_ember_stairs_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:polished_glazed_ember_stairs", 1, "Stonecutting: cut 1x Polished Glazed Ember Stairs from Glazed Ember.", "Steins\u00e4ge: 1x Polierte Glasurgluttreppe aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_glazed_ember_wall_from_glazed_ember_stonecutting", "copper_inferno:polished_glazed_ember_wall", "kilnstone/polished_glazed_ember_wall_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:polished_glazed_ember_wall", 1, "Stonecutting: cut 1x Polished Glazed Ember Wall from Glazed Ember.", "Steins\u00e4ge: 1x Polierte Glasurglutmauer aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_bricks_from_glazed_ember_stonecutting", "copper_inferno:glazed_ember_bricks", "kilnstone/glazed_ember_bricks_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_bricks", 1, "Stonecutting: cut 1x Glazed Ember Bricks from Glazed Ember.", "Steins\u00e4ge: 1x Glasurglutziegel aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_brick_slab_from_glazed_ember_stonecutting", "copper_inferno:glazed_ember_brick_slab", "kilnstone/glazed_ember_brick_slab_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_brick_slab", 2, "Stonecutting: cut 2x Glazed Ember Brick Slab from Glazed Ember.", "Steins\u00e4ge: 2x Glasurglutziegelstufe aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_brick_stairs_from_glazed_ember_stonecutting", "copper_inferno:glazed_ember_brick_stairs", "kilnstone/glazed_ember_brick_stairs_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_brick_stairs", 1, "Stonecutting: cut 1x Glazed Ember Brick Stairs from Glazed Ember.", "Steins\u00e4ge: 1x Glasurglutziegeltreppe aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_brick_wall_from_glazed_ember_stonecutting", "copper_inferno:glazed_ember_brick_wall", "kilnstone/glazed_ember_brick_wall_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_brick_wall", 1, "Stonecutting: cut 1x Glazed Ember Brick Wall from Glazed Ember.", "Steins\u00e4ge: 1x Glasurglutziegelmauer aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_tiles_from_glazed_ember_stonecutting", "copper_inferno:glazed_ember_tiles", "kilnstone/glazed_ember_tiles_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_tiles", 1, "Stonecutting: cut 1x Glazed Ember Tiles from Glazed Ember.", "Steins\u00e4ge: 1x Glasurglutfliesen aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_glazed_ember_bricks_from_glazed_ember_stonecutting", "copper_inferno:chiseled_glazed_ember_bricks", "kilnstone/chiseled_glazed_ember_bricks_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:chiseled_glazed_ember_bricks", 1, "Stonecutting: cut 1x Chiseled Glazed Ember Bricks from Glazed Ember.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glasurglutziegel aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/glazed_ember_pillar_from_glazed_ember_stonecutting", "copper_inferno:glazed_ember_pillar", "kilnstone/glazed_ember_pillar_from_glazed_ember_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glazed_ember", "", "", "", ""},
				"copper_inferno:glazed_ember_pillar", 1, "Stonecutting: cut 1x Glazed Ember Pillar from Glazed Ember.", "Steins\u00e4ge: 1x Glasurgluts\u00e4ule aus Glasurglut schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block", "copper_inferno:baked_ash_block", "kilnstone/baked_ash_block",
				new String[] {"minecraft:bone_meal", "", "minecraft:bone_meal", "", "copper_inferno:glazed_ember", "", "minecraft:bone_meal", "", "minecraft:bone_meal"},
				"copper_inferno:baked_ash_block", 4, "Craft 4x Baked Ash Block at a crafting table.", "Stellt 4x Brandaschenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "kilnstone/baked_ash_block_bricks",
				new String[] {"copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "", "copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_bricks", 4, "Craft 4x Baked Ash Block Bricks at a crafting table.", "Stellt 4x Brandaschenblockziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_tiles", "copper_inferno:baked_ash_block_tiles", "kilnstone/baked_ash_block_tiles",
				new String[] {"copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "", "copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "", "", "", ""},
				"copper_inferno:baked_ash_block_tiles", 4, "Craft 4x Baked Ash Block Tiles at a crafting table.", "Stellt 4x Brandaschenblockfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "kilnstone/polished_baked_ash_block",
				new String[] {"copper_inferno:baked_ash_block_tiles", "copper_inferno:baked_ash_block_tiles", "", "copper_inferno:baked_ash_block_tiles", "copper_inferno:baked_ash_block_tiles", "", "", "", ""},
				"copper_inferno:polished_baked_ash_block", 4, "Craft 4x Polished Baked Ash Block at a crafting table.", "Stellt 4x Polierten Brandaschenblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_slab", "copper_inferno:baked_ash_block_slab", "kilnstone/baked_ash_block_slab",
				new String[] {"copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "", "", "", "", "", ""},
				"copper_inferno:baked_ash_block_slab", 6, "Craft 6x Baked Ash Block Slab at a crafting table.", "Stellt 6x Brandaschenblockstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_stairs", "copper_inferno:baked_ash_block_stairs", "kilnstone/baked_ash_block_stairs",
				new String[] {"copper_inferno:baked_ash_block", "", "", "copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "", "copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block"},
				"copper_inferno:baked_ash_block_stairs", 4, "Craft 4x Baked Ash Block Stairs at a crafting table.", "Stellt 4x Brandaschenblocktreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_wall", "copper_inferno:baked_ash_block_wall", "kilnstone/baked_ash_block_wall",
				new String[] {"copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "copper_inferno:baked_ash_block", "", "", ""},
				"copper_inferno:baked_ash_block_wall", 6, "Craft 6x Baked Ash Block Wall at a crafting table.", "Stellt 6x Brandaschenblockmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_baked_ash_block_slab", "copper_inferno:polished_baked_ash_block_slab", "kilnstone/polished_baked_ash_block_slab",
				new String[] {"copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "", "", "", "", "", ""},
				"copper_inferno:polished_baked_ash_block_slab", 6, "Craft 6x Polished Baked Ash Block Slab at a crafting table.", "Stellt 6x Polierte Brandaschenblockstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_baked_ash_block_stairs", "copper_inferno:polished_baked_ash_block_stairs", "kilnstone/polished_baked_ash_block_stairs",
				new String[] {"copper_inferno:polished_baked_ash_block", "", "", "copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "", "copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block"},
				"copper_inferno:polished_baked_ash_block_stairs", 4, "Craft 4x Polished Baked Ash Block Stairs at a crafting table.", "Stellt 4x Polierte Brandaschenblocktreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_baked_ash_block_wall", "copper_inferno:polished_baked_ash_block_wall", "kilnstone/polished_baked_ash_block_wall",
				new String[] {"copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "copper_inferno:polished_baked_ash_block", "", "", ""},
				"copper_inferno:polished_baked_ash_block_wall", 6, "Craft 6x Polished Baked Ash Block Wall at a crafting table.", "Stellt 6x Polierte Brandaschenblockmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_brick_slab", "copper_inferno:baked_ash_block_brick_slab", "kilnstone/baked_ash_block_brick_slab",
				new String[] {"copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "", "", "", "", "", ""},
				"copper_inferno:baked_ash_block_brick_slab", 6, "Craft 6x Baked Ash Block Brick Slab at a crafting table.", "Stellt 6x Brandaschenblockziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_brick_stairs", "copper_inferno:baked_ash_block_brick_stairs", "kilnstone/baked_ash_block_brick_stairs",
				new String[] {"copper_inferno:baked_ash_block_bricks", "", "", "copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "", "copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks"},
				"copper_inferno:baked_ash_block_brick_stairs", 4, "Craft 4x Baked Ash Block Brick Stairs at a crafting table.", "Stellt 4x Brandaschenblockziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_brick_wall", "copper_inferno:baked_ash_block_brick_wall", "kilnstone/baked_ash_block_brick_wall",
				new String[] {"copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "copper_inferno:baked_ash_block_bricks", "", "", ""},
				"copper_inferno:baked_ash_block_brick_wall", 6, "Craft 6x Baked Ash Block Brick Wall at a crafting table.", "Stellt 6x Brandaschenblockziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_baked_ash_block_bricks", "copper_inferno:cracked_baked_ash_block_bricks", "kilnstone/cracked_baked_ash_block_bricks",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block_bricks", "", "", "", ""},
				"copper_inferno:cracked_baked_ash_block_bricks", 1, "Smelting Baked Ash Block Bricks in a furnace yields Cracked Baked Ash Block Bricks.", "Brandaschenblockziegel im Ofen gebrannt ergibt Rissige Brandaschenblockziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_baked_ash_block_bricks", "copper_inferno:chiseled_baked_ash_block_bricks", "kilnstone/chiseled_baked_ash_block_bricks",
				new String[] {"copper_inferno:baked_ash_block_brick_slab", "", "", "copper_inferno:baked_ash_block_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_baked_ash_block_bricks", 1, "Craft 1x Chiseled Baked Ash Block Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Brandaschenblockziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_pillar", "copper_inferno:baked_ash_block_pillar", "kilnstone/baked_ash_block_pillar",
				new String[] {"copper_inferno:baked_ash_block_bricks", "", "", "copper_inferno:baked_ash_block_bricks", "", "", "", "", ""},
				"copper_inferno:baked_ash_block_pillar", 2, "Craft 2x Baked Ash Block Pillar at a crafting table.", "Stellt 2x Brandaschenblocks\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_slab_from_baked_ash_block_stonecutting", "copper_inferno:baked_ash_block_slab", "kilnstone/baked_ash_block_slab_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_slab", 2, "Stonecutting: cut 2x Baked Ash Block Slab from Baked Ash Block.", "Steins\u00e4ge: 2x Brandaschenblockstufe aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_stairs_from_baked_ash_block_stonecutting", "copper_inferno:baked_ash_block_stairs", "kilnstone/baked_ash_block_stairs_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_stairs", 1, "Stonecutting: cut 1x Baked Ash Block Stairs from Baked Ash Block.", "Steins\u00e4ge: 1x Brandaschenblocktreppe aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_wall_from_baked_ash_block_stonecutting", "copper_inferno:baked_ash_block_wall", "kilnstone/baked_ash_block_wall_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_wall", 1, "Stonecutting: cut 1x Baked Ash Block Wall from Baked Ash Block.", "Steins\u00e4ge: 1x Brandaschenblockmauer aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_baked_ash_block_from_baked_ash_block_stonecutting", "copper_inferno:polished_baked_ash_block", "kilnstone/polished_baked_ash_block_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:polished_baked_ash_block", 1, "Stonecutting: cut 1x Polished Baked Ash Block from Baked Ash Block.", "Steins\u00e4ge: 1x Polierten Brandaschenblock aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_baked_ash_block_slab_from_baked_ash_block_stonecutting", "copper_inferno:polished_baked_ash_block_slab", "kilnstone/polished_baked_ash_block_slab_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:polished_baked_ash_block_slab", 2, "Stonecutting: cut 2x Polished Baked Ash Block Slab from Baked Ash Block.", "Steins\u00e4ge: 2x Polierte Brandaschenblockstufe aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_baked_ash_block_stairs_from_baked_ash_block_stonecutting", "copper_inferno:polished_baked_ash_block_stairs", "kilnstone/polished_baked_ash_block_stairs_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:polished_baked_ash_block_stairs", 1, "Stonecutting: cut 1x Polished Baked Ash Block Stairs from Baked Ash Block.", "Steins\u00e4ge: 1x Polierte Brandaschenblocktreppe aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_baked_ash_block_wall_from_baked_ash_block_stonecutting", "copper_inferno:polished_baked_ash_block_wall", "kilnstone/polished_baked_ash_block_wall_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:polished_baked_ash_block_wall", 1, "Stonecutting: cut 1x Polished Baked Ash Block Wall from Baked Ash Block.", "Steins\u00e4ge: 1x Polierte Brandaschenblockmauer aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_bricks_from_baked_ash_block_stonecutting", "copper_inferno:baked_ash_block_bricks", "kilnstone/baked_ash_block_bricks_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_bricks", 1, "Stonecutting: cut 1x Baked Ash Block Bricks from Baked Ash Block.", "Steins\u00e4ge: 1x Brandaschenblockziegel aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_brick_slab_from_baked_ash_block_stonecutting", "copper_inferno:baked_ash_block_brick_slab", "kilnstone/baked_ash_block_brick_slab_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_brick_slab", 2, "Stonecutting: cut 2x Baked Ash Block Brick Slab from Baked Ash Block.", "Steins\u00e4ge: 2x Brandaschenblockziegelstufe aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_brick_stairs_from_baked_ash_block_stonecutting", "copper_inferno:baked_ash_block_brick_stairs", "kilnstone/baked_ash_block_brick_stairs_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_brick_stairs", 1, "Stonecutting: cut 1x Baked Ash Block Brick Stairs from Baked Ash Block.", "Steins\u00e4ge: 1x Brandaschenblockziegeltreppe aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_brick_wall_from_baked_ash_block_stonecutting", "copper_inferno:baked_ash_block_brick_wall", "kilnstone/baked_ash_block_brick_wall_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_brick_wall", 1, "Stonecutting: cut 1x Baked Ash Block Brick Wall from Baked Ash Block.", "Steins\u00e4ge: 1x Brandaschenblockziegelmauer aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_tiles_from_baked_ash_block_stonecutting", "copper_inferno:baked_ash_block_tiles", "kilnstone/baked_ash_block_tiles_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_tiles", 1, "Stonecutting: cut 1x Baked Ash Block Tiles from Baked Ash Block.", "Steins\u00e4ge: 1x Brandaschenblockfliesen aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_baked_ash_block_bricks_from_baked_ash_block_stonecutting", "copper_inferno:chiseled_baked_ash_block_bricks", "kilnstone/chiseled_baked_ash_block_bricks_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:chiseled_baked_ash_block_bricks", 1, "Stonecutting: cut 1x Chiseled Baked Ash Block Bricks from Baked Ash Block.", "Steins\u00e4ge: 1x Gemei\u00dfelte Brandaschenblockziegel aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/baked_ash_block_pillar_from_baked_ash_block_stonecutting", "copper_inferno:baked_ash_block_pillar", "kilnstone/baked_ash_block_pillar_from_baked_ash_block_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:baked_ash_block", "", "", "", ""},
				"copper_inferno:baked_ash_block_pillar", 1, "Stonecutting: cut 1x Baked Ash Block Pillar from Baked Ash Block.", "Steins\u00e4ge: 1x Brandaschenblocks\u00e4ule aus Brandaschenblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick", "copper_inferno:foundry_brick", "kilnstone/foundry_brick",
				new String[] {"minecraft:iron_nugget", "", "minecraft:iron_nugget", "", "copper_inferno:baked_ash_block", "", "minecraft:iron_nugget", "", "minecraft:iron_nugget"},
				"copper_inferno:foundry_brick", 4, "Craft 4x Foundry Brick at a crafting table.", "Stellt 4x Gie\u00dfereistein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "kilnstone/foundry_brick_bricks",
				new String[] {"copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "", "copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_bricks", 4, "Craft 4x Foundry Brick Bricks at a crafting table.", "Stellt 4x Gie\u00dfereisteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_tiles", "copper_inferno:foundry_brick_tiles", "kilnstone/foundry_brick_tiles",
				new String[] {"copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "", "copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "", "", "", ""},
				"copper_inferno:foundry_brick_tiles", 4, "Craft 4x Foundry Brick Tiles at a crafting table.", "Stellt 4x Gie\u00dfereisteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_foundry_brick", "copper_inferno:polished_foundry_brick", "kilnstone/polished_foundry_brick",
				new String[] {"copper_inferno:foundry_brick_tiles", "copper_inferno:foundry_brick_tiles", "", "copper_inferno:foundry_brick_tiles", "copper_inferno:foundry_brick_tiles", "", "", "", ""},
				"copper_inferno:polished_foundry_brick", 4, "Craft 4x Polished Foundry Brick at a crafting table.", "Stellt 4x Polierten Gie\u00dfereistein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_slab", "copper_inferno:foundry_brick_slab", "kilnstone/foundry_brick_slab",
				new String[] {"copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "", "", "", "", "", ""},
				"copper_inferno:foundry_brick_slab", 6, "Craft 6x Foundry Brick Slab at a crafting table.", "Stellt 6x Gie\u00dfereisteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_stairs", "copper_inferno:foundry_brick_stairs", "kilnstone/foundry_brick_stairs",
				new String[] {"copper_inferno:foundry_brick", "", "", "copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "", "copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "copper_inferno:foundry_brick"},
				"copper_inferno:foundry_brick_stairs", 4, "Craft 4x Foundry Brick Stairs at a crafting table.", "Stellt 4x Gie\u00dfereisteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_wall", "copper_inferno:foundry_brick_wall", "kilnstone/foundry_brick_wall",
				new String[] {"copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "copper_inferno:foundry_brick", "", "", ""},
				"copper_inferno:foundry_brick_wall", 6, "Craft 6x Foundry Brick Wall at a crafting table.", "Stellt 6x Gie\u00dfereisteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_foundry_brick_slab", "copper_inferno:polished_foundry_brick_slab", "kilnstone/polished_foundry_brick_slab",
				new String[] {"copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick", "", "", "", "", "", ""},
				"copper_inferno:polished_foundry_brick_slab", 6, "Craft 6x Polished Foundry Brick Slab at a crafting table.", "Stellt 6x Polierte Gie\u00dfereisteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_foundry_brick_stairs", "copper_inferno:polished_foundry_brick_stairs", "kilnstone/polished_foundry_brick_stairs",
				new String[] {"copper_inferno:polished_foundry_brick", "", "", "copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick", "", "copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick"},
				"copper_inferno:polished_foundry_brick_stairs", 4, "Craft 4x Polished Foundry Brick Stairs at a crafting table.", "Stellt 4x Polierte Gie\u00dfereisteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_foundry_brick_wall", "copper_inferno:polished_foundry_brick_wall", "kilnstone/polished_foundry_brick_wall",
				new String[] {"copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick", "copper_inferno:polished_foundry_brick", "", "", ""},
				"copper_inferno:polished_foundry_brick_wall", 6, "Craft 6x Polished Foundry Brick Wall at a crafting table.", "Stellt 6x Polierte Gie\u00dfereisteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_brick_slab", "copper_inferno:foundry_brick_brick_slab", "kilnstone/foundry_brick_brick_slab",
				new String[] {"copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "", "", "", "", "", ""},
				"copper_inferno:foundry_brick_brick_slab", 6, "Craft 6x Foundry Brick Brick Slab at a crafting table.", "Stellt 6x Gie\u00dfereisteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_brick_stairs", "copper_inferno:foundry_brick_brick_stairs", "kilnstone/foundry_brick_brick_stairs",
				new String[] {"copper_inferno:foundry_brick_bricks", "", "", "copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "", "copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks"},
				"copper_inferno:foundry_brick_brick_stairs", 4, "Craft 4x Foundry Brick Brick Stairs at a crafting table.", "Stellt 4x Gie\u00dfereisteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_brick_wall", "copper_inferno:foundry_brick_brick_wall", "kilnstone/foundry_brick_brick_wall",
				new String[] {"copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "copper_inferno:foundry_brick_bricks", "", "", ""},
				"copper_inferno:foundry_brick_brick_wall", 6, "Craft 6x Foundry Brick Brick Wall at a crafting table.", "Stellt 6x Gie\u00dfereisteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_foundry_brick_bricks", "copper_inferno:cracked_foundry_brick_bricks", "kilnstone/cracked_foundry_brick_bricks",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick_bricks", "", "", "", ""},
				"copper_inferno:cracked_foundry_brick_bricks", 1, "Smelting Foundry Brick Bricks in a furnace yields Cracked Foundry Brick Bricks.", "Gie\u00dfereisteinziegel im Ofen gebrannt ergibt Rissige Gie\u00dfereisteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_foundry_brick_bricks", "copper_inferno:chiseled_foundry_brick_bricks", "kilnstone/chiseled_foundry_brick_bricks",
				new String[] {"copper_inferno:foundry_brick_brick_slab", "", "", "copper_inferno:foundry_brick_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_foundry_brick_bricks", 1, "Craft 1x Chiseled Foundry Brick Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Gie\u00dfereisteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_pillar", "copper_inferno:foundry_brick_pillar", "kilnstone/foundry_brick_pillar",
				new String[] {"copper_inferno:foundry_brick_bricks", "", "", "copper_inferno:foundry_brick_bricks", "", "", "", "", ""},
				"copper_inferno:foundry_brick_pillar", 2, "Craft 2x Foundry Brick Pillar at a crafting table.", "Stellt 2x Gie\u00dfereisteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_slab_from_foundry_brick_stonecutting", "copper_inferno:foundry_brick_slab", "kilnstone/foundry_brick_slab_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_slab", 2, "Stonecutting: cut 2x Foundry Brick Slab from Foundry Brick.", "Steins\u00e4ge: 2x Gie\u00dfereisteinstufe aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_stairs_from_foundry_brick_stonecutting", "copper_inferno:foundry_brick_stairs", "kilnstone/foundry_brick_stairs_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_stairs", 1, "Stonecutting: cut 1x Foundry Brick Stairs from Foundry Brick.", "Steins\u00e4ge: 1x Gie\u00dfereisteintreppe aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_wall_from_foundry_brick_stonecutting", "copper_inferno:foundry_brick_wall", "kilnstone/foundry_brick_wall_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_wall", 1, "Stonecutting: cut 1x Foundry Brick Wall from Foundry Brick.", "Steins\u00e4ge: 1x Gie\u00dfereisteinmauer aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_foundry_brick_from_foundry_brick_stonecutting", "copper_inferno:polished_foundry_brick", "kilnstone/polished_foundry_brick_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:polished_foundry_brick", 1, "Stonecutting: cut 1x Polished Foundry Brick from Foundry Brick.", "Steins\u00e4ge: 1x Polierten Gie\u00dfereistein aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_foundry_brick_slab_from_foundry_brick_stonecutting", "copper_inferno:polished_foundry_brick_slab", "kilnstone/polished_foundry_brick_slab_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:polished_foundry_brick_slab", 2, "Stonecutting: cut 2x Polished Foundry Brick Slab from Foundry Brick.", "Steins\u00e4ge: 2x Polierte Gie\u00dfereisteinstufe aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_foundry_brick_stairs_from_foundry_brick_stonecutting", "copper_inferno:polished_foundry_brick_stairs", "kilnstone/polished_foundry_brick_stairs_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:polished_foundry_brick_stairs", 1, "Stonecutting: cut 1x Polished Foundry Brick Stairs from Foundry Brick.", "Steins\u00e4ge: 1x Polierte Gie\u00dfereisteintreppe aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_foundry_brick_wall_from_foundry_brick_stonecutting", "copper_inferno:polished_foundry_brick_wall", "kilnstone/polished_foundry_brick_wall_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:polished_foundry_brick_wall", 1, "Stonecutting: cut 1x Polished Foundry Brick Wall from Foundry Brick.", "Steins\u00e4ge: 1x Polierte Gie\u00dfereisteinmauer aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_bricks_from_foundry_brick_stonecutting", "copper_inferno:foundry_brick_bricks", "kilnstone/foundry_brick_bricks_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_bricks", 1, "Stonecutting: cut 1x Foundry Brick Bricks from Foundry Brick.", "Steins\u00e4ge: 1x Gie\u00dfereisteinziegel aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_brick_slab_from_foundry_brick_stonecutting", "copper_inferno:foundry_brick_brick_slab", "kilnstone/foundry_brick_brick_slab_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_brick_slab", 2, "Stonecutting: cut 2x Foundry Brick Brick Slab from Foundry Brick.", "Steins\u00e4ge: 2x Gie\u00dfereisteinziegelstufe aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_brick_stairs_from_foundry_brick_stonecutting", "copper_inferno:foundry_brick_brick_stairs", "kilnstone/foundry_brick_brick_stairs_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_brick_stairs", 1, "Stonecutting: cut 1x Foundry Brick Brick Stairs from Foundry Brick.", "Steins\u00e4ge: 1x Gie\u00dfereisteinziegeltreppe aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_brick_wall_from_foundry_brick_stonecutting", "copper_inferno:foundry_brick_brick_wall", "kilnstone/foundry_brick_brick_wall_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_brick_wall", 1, "Stonecutting: cut 1x Foundry Brick Brick Wall from Foundry Brick.", "Steins\u00e4ge: 1x Gie\u00dfereisteinziegelmauer aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_tiles_from_foundry_brick_stonecutting", "copper_inferno:foundry_brick_tiles", "kilnstone/foundry_brick_tiles_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_tiles", 1, "Stonecutting: cut 1x Foundry Brick Tiles from Foundry Brick.", "Steins\u00e4ge: 1x Gie\u00dfereisteinfliesen aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_foundry_brick_bricks_from_foundry_brick_stonecutting", "copper_inferno:chiseled_foundry_brick_bricks", "kilnstone/chiseled_foundry_brick_bricks_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:chiseled_foundry_brick_bricks", 1, "Stonecutting: cut 1x Chiseled Foundry Brick Bricks from Foundry Brick.", "Steins\u00e4ge: 1x Gemei\u00dfelte Gie\u00dfereisteinziegel aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/foundry_brick_pillar_from_foundry_brick_stonecutting", "copper_inferno:foundry_brick_pillar", "kilnstone/foundry_brick_pillar_from_foundry_brick_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:foundry_brick", "", "", "", ""},
				"copper_inferno:foundry_brick_pillar", 1, "Stonecutting: cut 1x Foundry Brick Pillar from Foundry Brick.", "Steins\u00e4ge: 1x Gie\u00dfereisteins\u00e4ule aus Gie\u00dfereistein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone", "copper_inferno:crucible_stone", "kilnstone/crucible_stone",
				new String[] {"minecraft:stone", "", "minecraft:stone", "", "copper_inferno:foundry_brick", "", "minecraft:stone", "", "minecraft:stone"},
				"copper_inferno:crucible_stone", 4, "Craft 4x Crucible Stone at a crafting table.", "Stellt 4x Tiegelstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "kilnstone/crucible_stone_bricks",
				new String[] {"copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "", "copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_bricks", 4, "Craft 4x Crucible Stone Bricks at a crafting table.", "Stellt 4x Tiegelsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_tiles", "copper_inferno:crucible_stone_tiles", "kilnstone/crucible_stone_tiles",
				new String[] {"copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "", "copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "", "", "", ""},
				"copper_inferno:crucible_stone_tiles", 4, "Craft 4x Crucible Stone Tiles at a crafting table.", "Stellt 4x Tiegelsteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_crucible_stone", "copper_inferno:polished_crucible_stone", "kilnstone/polished_crucible_stone",
				new String[] {"copper_inferno:crucible_stone_tiles", "copper_inferno:crucible_stone_tiles", "", "copper_inferno:crucible_stone_tiles", "copper_inferno:crucible_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_crucible_stone", 4, "Craft 4x Polished Crucible Stone at a crafting table.", "Stellt 4x Polierten Tiegelstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_slab", "copper_inferno:crucible_stone_slab", "kilnstone/crucible_stone_slab",
				new String[] {"copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "", "", "", "", "", ""},
				"copper_inferno:crucible_stone_slab", 6, "Craft 6x Crucible Stone Slab at a crafting table.", "Stellt 6x Tiegelsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_stairs", "copper_inferno:crucible_stone_stairs", "kilnstone/crucible_stone_stairs",
				new String[] {"copper_inferno:crucible_stone", "", "", "copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "", "copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "copper_inferno:crucible_stone"},
				"copper_inferno:crucible_stone_stairs", 4, "Craft 4x Crucible Stone Stairs at a crafting table.", "Stellt 4x Tiegelsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_wall", "copper_inferno:crucible_stone_wall", "kilnstone/crucible_stone_wall",
				new String[] {"copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "copper_inferno:crucible_stone", "", "", ""},
				"copper_inferno:crucible_stone_wall", 6, "Craft 6x Crucible Stone Wall at a crafting table.", "Stellt 6x Tiegelsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_crucible_stone_slab", "copper_inferno:polished_crucible_stone_slab", "kilnstone/polished_crucible_stone_slab",
				new String[] {"copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_crucible_stone_slab", 6, "Craft 6x Polished Crucible Stone Slab at a crafting table.", "Stellt 6x Polierte Tiegelsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_crucible_stone_stairs", "copper_inferno:polished_crucible_stone_stairs", "kilnstone/polished_crucible_stone_stairs",
				new String[] {"copper_inferno:polished_crucible_stone", "", "", "copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone", "", "copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone"},
				"copper_inferno:polished_crucible_stone_stairs", 4, "Craft 4x Polished Crucible Stone Stairs at a crafting table.", "Stellt 4x Polierte Tiegelsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_crucible_stone_wall", "copper_inferno:polished_crucible_stone_wall", "kilnstone/polished_crucible_stone_wall",
				new String[] {"copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone", "copper_inferno:polished_crucible_stone", "", "", ""},
				"copper_inferno:polished_crucible_stone_wall", 6, "Craft 6x Polished Crucible Stone Wall at a crafting table.", "Stellt 6x Polierte Tiegelsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_brick_slab", "copper_inferno:crucible_stone_brick_slab", "kilnstone/crucible_stone_brick_slab",
				new String[] {"copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:crucible_stone_brick_slab", 6, "Craft 6x Crucible Stone Brick Slab at a crafting table.", "Stellt 6x Tiegelsteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_brick_stairs", "copper_inferno:crucible_stone_brick_stairs", "kilnstone/crucible_stone_brick_stairs",
				new String[] {"copper_inferno:crucible_stone_bricks", "", "", "copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "", "copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks"},
				"copper_inferno:crucible_stone_brick_stairs", 4, "Craft 4x Crucible Stone Brick Stairs at a crafting table.", "Stellt 4x Tiegelsteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_brick_wall", "copper_inferno:crucible_stone_brick_wall", "kilnstone/crucible_stone_brick_wall",
				new String[] {"copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "copper_inferno:crucible_stone_bricks", "", "", ""},
				"copper_inferno:crucible_stone_brick_wall", 6, "Craft 6x Crucible Stone Brick Wall at a crafting table.", "Stellt 6x Tiegelsteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_crucible_stone_bricks", "copper_inferno:cracked_crucible_stone_bricks", "kilnstone/cracked_crucible_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_crucible_stone_bricks", 1, "Smelting Crucible Stone Bricks in a furnace yields Cracked Crucible Stone Bricks.", "Tiegelsteinziegel im Ofen gebrannt ergibt Rissige Tiegelsteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_crucible_stone_bricks", "copper_inferno:chiseled_crucible_stone_bricks", "kilnstone/chiseled_crucible_stone_bricks",
				new String[] {"copper_inferno:crucible_stone_brick_slab", "", "", "copper_inferno:crucible_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_crucible_stone_bricks", 1, "Craft 1x Chiseled Crucible Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Tiegelsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_pillar", "copper_inferno:crucible_stone_pillar", "kilnstone/crucible_stone_pillar",
				new String[] {"copper_inferno:crucible_stone_bricks", "", "", "copper_inferno:crucible_stone_bricks", "", "", "", "", ""},
				"copper_inferno:crucible_stone_pillar", 2, "Craft 2x Crucible Stone Pillar at a crafting table.", "Stellt 2x Tiegelsteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_slab_from_crucible_stone_stonecutting", "copper_inferno:crucible_stone_slab", "kilnstone/crucible_stone_slab_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_slab", 2, "Stonecutting: cut 2x Crucible Stone Slab from Crucible Stone.", "Steins\u00e4ge: 2x Tiegelsteinstufe aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_stairs_from_crucible_stone_stonecutting", "copper_inferno:crucible_stone_stairs", "kilnstone/crucible_stone_stairs_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_stairs", 1, "Stonecutting: cut 1x Crucible Stone Stairs from Crucible Stone.", "Steins\u00e4ge: 1x Tiegelsteintreppe aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_wall_from_crucible_stone_stonecutting", "copper_inferno:crucible_stone_wall", "kilnstone/crucible_stone_wall_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_wall", 1, "Stonecutting: cut 1x Crucible Stone Wall from Crucible Stone.", "Steins\u00e4ge: 1x Tiegelsteinmauer aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_crucible_stone_from_crucible_stone_stonecutting", "copper_inferno:polished_crucible_stone", "kilnstone/polished_crucible_stone_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:polished_crucible_stone", 1, "Stonecutting: cut 1x Polished Crucible Stone from Crucible Stone.", "Steins\u00e4ge: 1x Polierten Tiegelstein aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_crucible_stone_slab_from_crucible_stone_stonecutting", "copper_inferno:polished_crucible_stone_slab", "kilnstone/polished_crucible_stone_slab_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:polished_crucible_stone_slab", 2, "Stonecutting: cut 2x Polished Crucible Stone Slab from Crucible Stone.", "Steins\u00e4ge: 2x Polierte Tiegelsteinstufe aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_crucible_stone_stairs_from_crucible_stone_stonecutting", "copper_inferno:polished_crucible_stone_stairs", "kilnstone/polished_crucible_stone_stairs_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:polished_crucible_stone_stairs", 1, "Stonecutting: cut 1x Polished Crucible Stone Stairs from Crucible Stone.", "Steins\u00e4ge: 1x Polierte Tiegelsteintreppe aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_crucible_stone_wall_from_crucible_stone_stonecutting", "copper_inferno:polished_crucible_stone_wall", "kilnstone/polished_crucible_stone_wall_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:polished_crucible_stone_wall", 1, "Stonecutting: cut 1x Polished Crucible Stone Wall from Crucible Stone.", "Steins\u00e4ge: 1x Polierte Tiegelsteinmauer aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_bricks_from_crucible_stone_stonecutting", "copper_inferno:crucible_stone_bricks", "kilnstone/crucible_stone_bricks_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_bricks", 1, "Stonecutting: cut 1x Crucible Stone Bricks from Crucible Stone.", "Steins\u00e4ge: 1x Tiegelsteinziegel aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_brick_slab_from_crucible_stone_stonecutting", "copper_inferno:crucible_stone_brick_slab", "kilnstone/crucible_stone_brick_slab_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_brick_slab", 2, "Stonecutting: cut 2x Crucible Stone Brick Slab from Crucible Stone.", "Steins\u00e4ge: 2x Tiegelsteinziegelstufe aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_brick_stairs_from_crucible_stone_stonecutting", "copper_inferno:crucible_stone_brick_stairs", "kilnstone/crucible_stone_brick_stairs_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_brick_stairs", 1, "Stonecutting: cut 1x Crucible Stone Brick Stairs from Crucible Stone.", "Steins\u00e4ge: 1x Tiegelsteinziegeltreppe aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_brick_wall_from_crucible_stone_stonecutting", "copper_inferno:crucible_stone_brick_wall", "kilnstone/crucible_stone_brick_wall_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_brick_wall", 1, "Stonecutting: cut 1x Crucible Stone Brick Wall from Crucible Stone.", "Steins\u00e4ge: 1x Tiegelsteinziegelmauer aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_tiles_from_crucible_stone_stonecutting", "copper_inferno:crucible_stone_tiles", "kilnstone/crucible_stone_tiles_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_tiles", 1, "Stonecutting: cut 1x Crucible Stone Tiles from Crucible Stone.", "Steins\u00e4ge: 1x Tiegelsteinfliesen aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_crucible_stone_bricks_from_crucible_stone_stonecutting", "copper_inferno:chiseled_crucible_stone_bricks", "kilnstone/chiseled_crucible_stone_bricks_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:chiseled_crucible_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Crucible Stone Bricks from Crucible Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Tiegelsteinziegel aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/crucible_stone_pillar_from_crucible_stone_stonecutting", "copper_inferno:crucible_stone_pillar", "kilnstone/crucible_stone_pillar_from_crucible_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:crucible_stone", "", "", "", ""},
				"copper_inferno:crucible_stone_pillar", 1, "Stonecutting: cut 1x Crucible Stone Pillar from Crucible Stone.", "Steins\u00e4ge: 1x Tiegelsteins\u00e4ule aus Tiegelstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware", "copper_inferno:slagware", "kilnstone/slagware",
				new String[] {"minecraft:tuff", "", "minecraft:tuff", "", "copper_inferno:crucible_stone", "", "minecraft:tuff", "", "minecraft:tuff"},
				"copper_inferno:slagware", 4, "Craft 4x Slagware at a crafting table.", "Stellt 4x Schlackenware an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_bricks", "copper_inferno:slagware_bricks", "kilnstone/slagware_bricks",
				new String[] {"copper_inferno:slagware", "copper_inferno:slagware", "", "copper_inferno:slagware", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_bricks", 4, "Craft 4x Slagware Bricks at a crafting table.", "Stellt 4x Schlackenwarenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_tiles", "copper_inferno:slagware_tiles", "kilnstone/slagware_tiles",
				new String[] {"copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "", "copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "", "", "", ""},
				"copper_inferno:slagware_tiles", 4, "Craft 4x Slagware Tiles at a crafting table.", "Stellt 4x Schlackenwarenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_slagware", "copper_inferno:polished_slagware", "kilnstone/polished_slagware",
				new String[] {"copper_inferno:slagware_tiles", "copper_inferno:slagware_tiles", "", "copper_inferno:slagware_tiles", "copper_inferno:slagware_tiles", "", "", "", ""},
				"copper_inferno:polished_slagware", 4, "Craft 4x Polished Slagware at a crafting table.", "Stellt 4x Polierte Schlackenware an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_slab", "copper_inferno:slagware_slab", "kilnstone/slagware_slab",
				new String[] {"copper_inferno:slagware", "copper_inferno:slagware", "copper_inferno:slagware", "", "", "", "", "", ""},
				"copper_inferno:slagware_slab", 6, "Craft 6x Slagware Slab at a crafting table.", "Stellt 6x Schlackenwarenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_stairs", "copper_inferno:slagware_stairs", "kilnstone/slagware_stairs",
				new String[] {"copper_inferno:slagware", "", "", "copper_inferno:slagware", "copper_inferno:slagware", "", "copper_inferno:slagware", "copper_inferno:slagware", "copper_inferno:slagware"},
				"copper_inferno:slagware_stairs", 4, "Craft 4x Slagware Stairs at a crafting table.", "Stellt 4x Schlackenwarentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_wall", "copper_inferno:slagware_wall", "kilnstone/slagware_wall",
				new String[] {"copper_inferno:slagware", "copper_inferno:slagware", "copper_inferno:slagware", "copper_inferno:slagware", "copper_inferno:slagware", "copper_inferno:slagware", "", "", ""},
				"copper_inferno:slagware_wall", 6, "Craft 6x Slagware Wall at a crafting table.", "Stellt 6x Schlackenwarenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_slagware_slab", "copper_inferno:polished_slagware_slab", "kilnstone/polished_slagware_slab",
				new String[] {"copper_inferno:polished_slagware", "copper_inferno:polished_slagware", "copper_inferno:polished_slagware", "", "", "", "", "", ""},
				"copper_inferno:polished_slagware_slab", 6, "Craft 6x Polished Slagware Slab at a crafting table.", "Stellt 6x Polierte Schlackenwarenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_slagware_stairs", "copper_inferno:polished_slagware_stairs", "kilnstone/polished_slagware_stairs",
				new String[] {"copper_inferno:polished_slagware", "", "", "copper_inferno:polished_slagware", "copper_inferno:polished_slagware", "", "copper_inferno:polished_slagware", "copper_inferno:polished_slagware", "copper_inferno:polished_slagware"},
				"copper_inferno:polished_slagware_stairs", 4, "Craft 4x Polished Slagware Stairs at a crafting table.", "Stellt 4x Polierte Schlackenwarentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_slagware_wall", "copper_inferno:polished_slagware_wall", "kilnstone/polished_slagware_wall",
				new String[] {"copper_inferno:polished_slagware", "copper_inferno:polished_slagware", "copper_inferno:polished_slagware", "copper_inferno:polished_slagware", "copper_inferno:polished_slagware", "copper_inferno:polished_slagware", "", "", ""},
				"copper_inferno:polished_slagware_wall", 6, "Craft 6x Polished Slagware Wall at a crafting table.", "Stellt 6x Polierte Schlackenwarenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_brick_slab", "copper_inferno:slagware_brick_slab", "kilnstone/slagware_brick_slab",
				new String[] {"copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "", "", "", "", "", ""},
				"copper_inferno:slagware_brick_slab", 6, "Craft 6x Slagware Brick Slab at a crafting table.", "Stellt 6x Schlackenwarenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_brick_stairs", "copper_inferno:slagware_brick_stairs", "kilnstone/slagware_brick_stairs",
				new String[] {"copper_inferno:slagware_bricks", "", "", "copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "", "copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks"},
				"copper_inferno:slagware_brick_stairs", 4, "Craft 4x Slagware Brick Stairs at a crafting table.", "Stellt 4x Schlackenwarenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_brick_wall", "copper_inferno:slagware_brick_wall", "kilnstone/slagware_brick_wall",
				new String[] {"copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "copper_inferno:slagware_bricks", "", "", ""},
				"copper_inferno:slagware_brick_wall", 6, "Craft 6x Slagware Brick Wall at a crafting table.", "Stellt 6x Schlackenwarenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_slagware_bricks", "copper_inferno:cracked_slagware_bricks", "kilnstone/cracked_slagware_bricks",
				new String[] {"", "", "", "", "copper_inferno:slagware_bricks", "", "", "", ""},
				"copper_inferno:cracked_slagware_bricks", 1, "Smelting Slagware Bricks in a furnace yields Cracked Slagware Bricks.", "Schlackenwarenziegel im Ofen gebrannt ergibt Rissige Schlackenwarenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_slagware_bricks", "copper_inferno:chiseled_slagware_bricks", "kilnstone/chiseled_slagware_bricks",
				new String[] {"copper_inferno:slagware_brick_slab", "", "", "copper_inferno:slagware_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_slagware_bricks", 1, "Craft 1x Chiseled Slagware Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schlackenwarenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_pillar", "copper_inferno:slagware_pillar", "kilnstone/slagware_pillar",
				new String[] {"copper_inferno:slagware_bricks", "", "", "copper_inferno:slagware_bricks", "", "", "", "", ""},
				"copper_inferno:slagware_pillar", 2, "Craft 2x Slagware Pillar at a crafting table.", "Stellt 2x Schlackenwarens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_slab_from_slagware_stonecutting", "copper_inferno:slagware_slab", "kilnstone/slagware_slab_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_slab", 2, "Stonecutting: cut 2x Slagware Slab from Slagware.", "Steins\u00e4ge: 2x Schlackenwarenstufe aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_stairs_from_slagware_stonecutting", "copper_inferno:slagware_stairs", "kilnstone/slagware_stairs_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_stairs", 1, "Stonecutting: cut 1x Slagware Stairs from Slagware.", "Steins\u00e4ge: 1x Schlackenwarentreppe aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_wall_from_slagware_stonecutting", "copper_inferno:slagware_wall", "kilnstone/slagware_wall_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_wall", 1, "Stonecutting: cut 1x Slagware Wall from Slagware.", "Steins\u00e4ge: 1x Schlackenwarenmauer aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_slagware_from_slagware_stonecutting", "copper_inferno:polished_slagware", "kilnstone/polished_slagware_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:polished_slagware", 1, "Stonecutting: cut 1x Polished Slagware from Slagware.", "Steins\u00e4ge: 1x Polierte Schlackenware aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_slagware_slab_from_slagware_stonecutting", "copper_inferno:polished_slagware_slab", "kilnstone/polished_slagware_slab_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:polished_slagware_slab", 2, "Stonecutting: cut 2x Polished Slagware Slab from Slagware.", "Steins\u00e4ge: 2x Polierte Schlackenwarenstufe aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_slagware_stairs_from_slagware_stonecutting", "copper_inferno:polished_slagware_stairs", "kilnstone/polished_slagware_stairs_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:polished_slagware_stairs", 1, "Stonecutting: cut 1x Polished Slagware Stairs from Slagware.", "Steins\u00e4ge: 1x Polierte Schlackenwarentreppe aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_slagware_wall_from_slagware_stonecutting", "copper_inferno:polished_slagware_wall", "kilnstone/polished_slagware_wall_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:polished_slagware_wall", 1, "Stonecutting: cut 1x Polished Slagware Wall from Slagware.", "Steins\u00e4ge: 1x Polierte Schlackenwarenmauer aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_bricks_from_slagware_stonecutting", "copper_inferno:slagware_bricks", "kilnstone/slagware_bricks_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_bricks", 1, "Stonecutting: cut 1x Slagware Bricks from Slagware.", "Steins\u00e4ge: 1x Schlackenwarenziegel aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_brick_slab_from_slagware_stonecutting", "copper_inferno:slagware_brick_slab", "kilnstone/slagware_brick_slab_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_brick_slab", 2, "Stonecutting: cut 2x Slagware Brick Slab from Slagware.", "Steins\u00e4ge: 2x Schlackenwarenziegelstufe aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_brick_stairs_from_slagware_stonecutting", "copper_inferno:slagware_brick_stairs", "kilnstone/slagware_brick_stairs_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_brick_stairs", 1, "Stonecutting: cut 1x Slagware Brick Stairs from Slagware.", "Steins\u00e4ge: 1x Schlackenwarenziegeltreppe aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_brick_wall_from_slagware_stonecutting", "copper_inferno:slagware_brick_wall", "kilnstone/slagware_brick_wall_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_brick_wall", 1, "Stonecutting: cut 1x Slagware Brick Wall from Slagware.", "Steins\u00e4ge: 1x Schlackenwarenziegelmauer aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_tiles_from_slagware_stonecutting", "copper_inferno:slagware_tiles", "kilnstone/slagware_tiles_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_tiles", 1, "Stonecutting: cut 1x Slagware Tiles from Slagware.", "Steins\u00e4ge: 1x Schlackenwarenfliesen aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_slagware_bricks_from_slagware_stonecutting", "copper_inferno:chiseled_slagware_bricks", "kilnstone/chiseled_slagware_bricks_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:chiseled_slagware_bricks", 1, "Stonecutting: cut 1x Chiseled Slagware Bricks from Slagware.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schlackenwarenziegel aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/slagware_pillar_from_slagware_stonecutting", "copper_inferno:slagware_pillar", "kilnstone/slagware_pillar_from_slagware_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagware", "", "", "", ""},
				"copper_inferno:slagware_pillar", 1, "Stonecutting: cut 1x Slagware Pillar from Slagware.", "Steins\u00e4ge: 1x Schlackenwarens\u00e4ule aus Schlackenware schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic", "copper_inferno:ash_ceramic", "kilnstone/ash_ceramic",
				new String[] {"minecraft:clay_ball", "", "minecraft:clay_ball", "", "copper_inferno:slagware", "", "minecraft:clay_ball", "", "minecraft:clay_ball"},
				"copper_inferno:ash_ceramic", 4, "Craft 4x Ash Ceramic at a crafting table.", "Stellt 4x Aschenkeramik an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "kilnstone/ash_ceramic_bricks",
				new String[] {"copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "", "copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_bricks", 4, "Craft 4x Ash Ceramic Bricks at a crafting table.", "Stellt 4x Aschenkeramikziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_tiles", "copper_inferno:ash_ceramic_tiles", "kilnstone/ash_ceramic_tiles",
				new String[] {"copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "", "copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "", "", "", ""},
				"copper_inferno:ash_ceramic_tiles", 4, "Craft 4x Ash Ceramic Tiles at a crafting table.", "Stellt 4x Aschenkeramikfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "kilnstone/polished_ash_ceramic",
				new String[] {"copper_inferno:ash_ceramic_tiles", "copper_inferno:ash_ceramic_tiles", "", "copper_inferno:ash_ceramic_tiles", "copper_inferno:ash_ceramic_tiles", "", "", "", ""},
				"copper_inferno:polished_ash_ceramic", 4, "Craft 4x Polished Ash Ceramic at a crafting table.", "Stellt 4x Polierte Aschenkeramik an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_slab", "copper_inferno:ash_ceramic_slab", "kilnstone/ash_ceramic_slab",
				new String[] {"copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "", "", "", "", "", ""},
				"copper_inferno:ash_ceramic_slab", 6, "Craft 6x Ash Ceramic Slab at a crafting table.", "Stellt 6x Aschenkeramikstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_stairs", "copper_inferno:ash_ceramic_stairs", "kilnstone/ash_ceramic_stairs",
				new String[] {"copper_inferno:ash_ceramic", "", "", "copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "", "copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic"},
				"copper_inferno:ash_ceramic_stairs", 4, "Craft 4x Ash Ceramic Stairs at a crafting table.", "Stellt 4x Aschenkeramiktreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_wall", "copper_inferno:ash_ceramic_wall", "kilnstone/ash_ceramic_wall",
				new String[] {"copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "copper_inferno:ash_ceramic", "", "", ""},
				"copper_inferno:ash_ceramic_wall", 6, "Craft 6x Ash Ceramic Wall at a crafting table.", "Stellt 6x Aschenkeramikmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_ash_ceramic_slab", "copper_inferno:polished_ash_ceramic_slab", "kilnstone/polished_ash_ceramic_slab",
				new String[] {"copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "", "", "", "", "", ""},
				"copper_inferno:polished_ash_ceramic_slab", 6, "Craft 6x Polished Ash Ceramic Slab at a crafting table.", "Stellt 6x Polierte Aschenkeramikstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_ash_ceramic_stairs", "copper_inferno:polished_ash_ceramic_stairs", "kilnstone/polished_ash_ceramic_stairs",
				new String[] {"copper_inferno:polished_ash_ceramic", "", "", "copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "", "copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic"},
				"copper_inferno:polished_ash_ceramic_stairs", 4, "Craft 4x Polished Ash Ceramic Stairs at a crafting table.", "Stellt 4x Polierte Aschenkeramiktreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_ash_ceramic_wall", "copper_inferno:polished_ash_ceramic_wall", "kilnstone/polished_ash_ceramic_wall",
				new String[] {"copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "copper_inferno:polished_ash_ceramic", "", "", ""},
				"copper_inferno:polished_ash_ceramic_wall", 6, "Craft 6x Polished Ash Ceramic Wall at a crafting table.", "Stellt 6x Polierte Aschenkeramikmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_brick_slab", "copper_inferno:ash_ceramic_brick_slab", "kilnstone/ash_ceramic_brick_slab",
				new String[] {"copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "", "", "", "", "", ""},
				"copper_inferno:ash_ceramic_brick_slab", 6, "Craft 6x Ash Ceramic Brick Slab at a crafting table.", "Stellt 6x Aschenkeramikziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_brick_stairs", "copper_inferno:ash_ceramic_brick_stairs", "kilnstone/ash_ceramic_brick_stairs",
				new String[] {"copper_inferno:ash_ceramic_bricks", "", "", "copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "", "copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks"},
				"copper_inferno:ash_ceramic_brick_stairs", 4, "Craft 4x Ash Ceramic Brick Stairs at a crafting table.", "Stellt 4x Aschenkeramikziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_brick_wall", "copper_inferno:ash_ceramic_brick_wall", "kilnstone/ash_ceramic_brick_wall",
				new String[] {"copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "copper_inferno:ash_ceramic_bricks", "", "", ""},
				"copper_inferno:ash_ceramic_brick_wall", 6, "Craft 6x Ash Ceramic Brick Wall at a crafting table.", "Stellt 6x Aschenkeramikziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_ash_ceramic_bricks", "copper_inferno:cracked_ash_ceramic_bricks", "kilnstone/cracked_ash_ceramic_bricks",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic_bricks", "", "", "", ""},
				"copper_inferno:cracked_ash_ceramic_bricks", 1, "Smelting Ash Ceramic Bricks in a furnace yields Cracked Ash Ceramic Bricks.", "Aschenkeramikziegel im Ofen gebrannt ergibt Rissige Aschenkeramikziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_ash_ceramic_bricks", "copper_inferno:chiseled_ash_ceramic_bricks", "kilnstone/chiseled_ash_ceramic_bricks",
				new String[] {"copper_inferno:ash_ceramic_brick_slab", "", "", "copper_inferno:ash_ceramic_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_ash_ceramic_bricks", 1, "Craft 1x Chiseled Ash Ceramic Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Aschenkeramikziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_pillar", "copper_inferno:ash_ceramic_pillar", "kilnstone/ash_ceramic_pillar",
				new String[] {"copper_inferno:ash_ceramic_bricks", "", "", "copper_inferno:ash_ceramic_bricks", "", "", "", "", ""},
				"copper_inferno:ash_ceramic_pillar", 2, "Craft 2x Ash Ceramic Pillar at a crafting table.", "Stellt 2x Aschenkeramiks\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_slab_from_ash_ceramic_stonecutting", "copper_inferno:ash_ceramic_slab", "kilnstone/ash_ceramic_slab_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_slab", 2, "Stonecutting: cut 2x Ash Ceramic Slab from Ash Ceramic.", "Steins\u00e4ge: 2x Aschenkeramikstufe aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_stairs_from_ash_ceramic_stonecutting", "copper_inferno:ash_ceramic_stairs", "kilnstone/ash_ceramic_stairs_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_stairs", 1, "Stonecutting: cut 1x Ash Ceramic Stairs from Ash Ceramic.", "Steins\u00e4ge: 1x Aschenkeramiktreppe aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_wall_from_ash_ceramic_stonecutting", "copper_inferno:ash_ceramic_wall", "kilnstone/ash_ceramic_wall_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_wall", 1, "Stonecutting: cut 1x Ash Ceramic Wall from Ash Ceramic.", "Steins\u00e4ge: 1x Aschenkeramikmauer aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_ash_ceramic_from_ash_ceramic_stonecutting", "copper_inferno:polished_ash_ceramic", "kilnstone/polished_ash_ceramic_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:polished_ash_ceramic", 1, "Stonecutting: cut 1x Polished Ash Ceramic from Ash Ceramic.", "Steins\u00e4ge: 1x Polierte Aschenkeramik aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_ash_ceramic_slab_from_ash_ceramic_stonecutting", "copper_inferno:polished_ash_ceramic_slab", "kilnstone/polished_ash_ceramic_slab_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:polished_ash_ceramic_slab", 2, "Stonecutting: cut 2x Polished Ash Ceramic Slab from Ash Ceramic.", "Steins\u00e4ge: 2x Polierte Aschenkeramikstufe aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_ash_ceramic_stairs_from_ash_ceramic_stonecutting", "copper_inferno:polished_ash_ceramic_stairs", "kilnstone/polished_ash_ceramic_stairs_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:polished_ash_ceramic_stairs", 1, "Stonecutting: cut 1x Polished Ash Ceramic Stairs from Ash Ceramic.", "Steins\u00e4ge: 1x Polierte Aschenkeramiktreppe aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_ash_ceramic_wall_from_ash_ceramic_stonecutting", "copper_inferno:polished_ash_ceramic_wall", "kilnstone/polished_ash_ceramic_wall_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:polished_ash_ceramic_wall", 1, "Stonecutting: cut 1x Polished Ash Ceramic Wall from Ash Ceramic.", "Steins\u00e4ge: 1x Polierte Aschenkeramikmauer aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_bricks_from_ash_ceramic_stonecutting", "copper_inferno:ash_ceramic_bricks", "kilnstone/ash_ceramic_bricks_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_bricks", 1, "Stonecutting: cut 1x Ash Ceramic Bricks from Ash Ceramic.", "Steins\u00e4ge: 1x Aschenkeramikziegel aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_brick_slab_from_ash_ceramic_stonecutting", "copper_inferno:ash_ceramic_brick_slab", "kilnstone/ash_ceramic_brick_slab_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_brick_slab", 2, "Stonecutting: cut 2x Ash Ceramic Brick Slab from Ash Ceramic.", "Steins\u00e4ge: 2x Aschenkeramikziegelstufe aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_brick_stairs_from_ash_ceramic_stonecutting", "copper_inferno:ash_ceramic_brick_stairs", "kilnstone/ash_ceramic_brick_stairs_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_brick_stairs", 1, "Stonecutting: cut 1x Ash Ceramic Brick Stairs from Ash Ceramic.", "Steins\u00e4ge: 1x Aschenkeramikziegeltreppe aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_brick_wall_from_ash_ceramic_stonecutting", "copper_inferno:ash_ceramic_brick_wall", "kilnstone/ash_ceramic_brick_wall_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_brick_wall", 1, "Stonecutting: cut 1x Ash Ceramic Brick Wall from Ash Ceramic.", "Steins\u00e4ge: 1x Aschenkeramikziegelmauer aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_tiles_from_ash_ceramic_stonecutting", "copper_inferno:ash_ceramic_tiles", "kilnstone/ash_ceramic_tiles_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_tiles", 1, "Stonecutting: cut 1x Ash Ceramic Tiles from Ash Ceramic.", "Steins\u00e4ge: 1x Aschenkeramikfliesen aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_ash_ceramic_bricks_from_ash_ceramic_stonecutting", "copper_inferno:chiseled_ash_ceramic_bricks", "kilnstone/chiseled_ash_ceramic_bricks_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:chiseled_ash_ceramic_bricks", 1, "Stonecutting: cut 1x Chiseled Ash Ceramic Bricks from Ash Ceramic.", "Steins\u00e4ge: 1x Gemei\u00dfelte Aschenkeramikziegel aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/ash_ceramic_pillar_from_ash_ceramic_stonecutting", "copper_inferno:ash_ceramic_pillar", "kilnstone/ash_ceramic_pillar_from_ash_ceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ash_ceramic", "", "", "", ""},
				"copper_inferno:ash_ceramic_pillar", 1, "Stonecutting: cut 1x Ash Ceramic Pillar from Ash Ceramic.", "Steins\u00e4ge: 1x Aschenkeramiks\u00e4ule aus Aschenkeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic", "copper_inferno:pyroceramic", "kilnstone/pyroceramic",
				new String[] {"minecraft:magma_cream", "", "minecraft:magma_cream", "", "copper_inferno:ash_ceramic", "", "minecraft:magma_cream", "", "minecraft:magma_cream"},
				"copper_inferno:pyroceramic", 4, "Craft 4x Pyroceramic at a crafting table.", "Stellt 4x Pyrokeramik an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "kilnstone/pyroceramic_bricks",
				new String[] {"copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "", "copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_bricks", 4, "Craft 4x Pyroceramic Bricks at a crafting table.", "Stellt 4x Pyrokeramikziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_tiles", "copper_inferno:pyroceramic_tiles", "kilnstone/pyroceramic_tiles",
				new String[] {"copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "", "copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "", "", "", ""},
				"copper_inferno:pyroceramic_tiles", 4, "Craft 4x Pyroceramic Tiles at a crafting table.", "Stellt 4x Pyrokeramikfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_pyroceramic", "copper_inferno:polished_pyroceramic", "kilnstone/polished_pyroceramic",
				new String[] {"copper_inferno:pyroceramic_tiles", "copper_inferno:pyroceramic_tiles", "", "copper_inferno:pyroceramic_tiles", "copper_inferno:pyroceramic_tiles", "", "", "", ""},
				"copper_inferno:polished_pyroceramic", 4, "Craft 4x Polished Pyroceramic at a crafting table.", "Stellt 4x Polierte Pyrokeramik an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_slab", "copper_inferno:pyroceramic_slab", "kilnstone/pyroceramic_slab",
				new String[] {"copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "", "", "", "", "", ""},
				"copper_inferno:pyroceramic_slab", 6, "Craft 6x Pyroceramic Slab at a crafting table.", "Stellt 6x Pyrokeramikstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_stairs", "copper_inferno:pyroceramic_stairs", "kilnstone/pyroceramic_stairs",
				new String[] {"copper_inferno:pyroceramic", "", "", "copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "", "copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "copper_inferno:pyroceramic"},
				"copper_inferno:pyroceramic_stairs", 4, "Craft 4x Pyroceramic Stairs at a crafting table.", "Stellt 4x Pyrokeramiktreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_wall", "copper_inferno:pyroceramic_wall", "kilnstone/pyroceramic_wall",
				new String[] {"copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "copper_inferno:pyroceramic", "", "", ""},
				"copper_inferno:pyroceramic_wall", 6, "Craft 6x Pyroceramic Wall at a crafting table.", "Stellt 6x Pyrokeramikmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_pyroceramic_slab", "copper_inferno:polished_pyroceramic_slab", "kilnstone/polished_pyroceramic_slab",
				new String[] {"copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic", "", "", "", "", "", ""},
				"copper_inferno:polished_pyroceramic_slab", 6, "Craft 6x Polished Pyroceramic Slab at a crafting table.", "Stellt 6x Polierte Pyrokeramikstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_pyroceramic_stairs", "copper_inferno:polished_pyroceramic_stairs", "kilnstone/polished_pyroceramic_stairs",
				new String[] {"copper_inferno:polished_pyroceramic", "", "", "copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic", "", "copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic"},
				"copper_inferno:polished_pyroceramic_stairs", 4, "Craft 4x Polished Pyroceramic Stairs at a crafting table.", "Stellt 4x Polierte Pyrokeramiktreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_pyroceramic_wall", "copper_inferno:polished_pyroceramic_wall", "kilnstone/polished_pyroceramic_wall",
				new String[] {"copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic", "copper_inferno:polished_pyroceramic", "", "", ""},
				"copper_inferno:polished_pyroceramic_wall", 6, "Craft 6x Polished Pyroceramic Wall at a crafting table.", "Stellt 6x Polierte Pyrokeramikmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_brick_slab", "copper_inferno:pyroceramic_brick_slab", "kilnstone/pyroceramic_brick_slab",
				new String[] {"copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "", "", "", "", "", ""},
				"copper_inferno:pyroceramic_brick_slab", 6, "Craft 6x Pyroceramic Brick Slab at a crafting table.", "Stellt 6x Pyrokeramikziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_brick_stairs", "copper_inferno:pyroceramic_brick_stairs", "kilnstone/pyroceramic_brick_stairs",
				new String[] {"copper_inferno:pyroceramic_bricks", "", "", "copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "", "copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks"},
				"copper_inferno:pyroceramic_brick_stairs", 4, "Craft 4x Pyroceramic Brick Stairs at a crafting table.", "Stellt 4x Pyrokeramikziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_brick_wall", "copper_inferno:pyroceramic_brick_wall", "kilnstone/pyroceramic_brick_wall",
				new String[] {"copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "copper_inferno:pyroceramic_bricks", "", "", ""},
				"copper_inferno:pyroceramic_brick_wall", 6, "Craft 6x Pyroceramic Brick Wall at a crafting table.", "Stellt 6x Pyrokeramikziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/cracked_pyroceramic_bricks", "copper_inferno:cracked_pyroceramic_bricks", "kilnstone/cracked_pyroceramic_bricks",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic_bricks", "", "", "", ""},
				"copper_inferno:cracked_pyroceramic_bricks", 1, "Smelting Pyroceramic Bricks in a furnace yields Cracked Pyroceramic Bricks.", "Pyrokeramikziegel im Ofen gebrannt ergibt Rissige Pyrokeramikziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_pyroceramic_bricks", "copper_inferno:chiseled_pyroceramic_bricks", "kilnstone/chiseled_pyroceramic_bricks",
				new String[] {"copper_inferno:pyroceramic_brick_slab", "", "", "copper_inferno:pyroceramic_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_pyroceramic_bricks", 1, "Craft 1x Chiseled Pyroceramic Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Pyrokeramikziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_pillar", "copper_inferno:pyroceramic_pillar", "kilnstone/pyroceramic_pillar",
				new String[] {"copper_inferno:pyroceramic_bricks", "", "", "copper_inferno:pyroceramic_bricks", "", "", "", "", ""},
				"copper_inferno:pyroceramic_pillar", 2, "Craft 2x Pyroceramic Pillar at a crafting table.", "Stellt 2x Pyrokeramiks\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_slab_from_pyroceramic_stonecutting", "copper_inferno:pyroceramic_slab", "kilnstone/pyroceramic_slab_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_slab", 2, "Stonecutting: cut 2x Pyroceramic Slab from Pyroceramic.", "Steins\u00e4ge: 2x Pyrokeramikstufe aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_stairs_from_pyroceramic_stonecutting", "copper_inferno:pyroceramic_stairs", "kilnstone/pyroceramic_stairs_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_stairs", 1, "Stonecutting: cut 1x Pyroceramic Stairs from Pyroceramic.", "Steins\u00e4ge: 1x Pyrokeramiktreppe aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_wall_from_pyroceramic_stonecutting", "copper_inferno:pyroceramic_wall", "kilnstone/pyroceramic_wall_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_wall", 1, "Stonecutting: cut 1x Pyroceramic Wall from Pyroceramic.", "Steins\u00e4ge: 1x Pyrokeramikmauer aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_pyroceramic_from_pyroceramic_stonecutting", "copper_inferno:polished_pyroceramic", "kilnstone/polished_pyroceramic_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:polished_pyroceramic", 1, "Stonecutting: cut 1x Polished Pyroceramic from Pyroceramic.", "Steins\u00e4ge: 1x Polierte Pyrokeramik aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_pyroceramic_slab_from_pyroceramic_stonecutting", "copper_inferno:polished_pyroceramic_slab", "kilnstone/polished_pyroceramic_slab_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:polished_pyroceramic_slab", 2, "Stonecutting: cut 2x Polished Pyroceramic Slab from Pyroceramic.", "Steins\u00e4ge: 2x Polierte Pyrokeramikstufe aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_pyroceramic_stairs_from_pyroceramic_stonecutting", "copper_inferno:polished_pyroceramic_stairs", "kilnstone/polished_pyroceramic_stairs_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:polished_pyroceramic_stairs", 1, "Stonecutting: cut 1x Polished Pyroceramic Stairs from Pyroceramic.", "Steins\u00e4ge: 1x Polierte Pyrokeramiktreppe aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/polished_pyroceramic_wall_from_pyroceramic_stonecutting", "copper_inferno:polished_pyroceramic_wall", "kilnstone/polished_pyroceramic_wall_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:polished_pyroceramic_wall", 1, "Stonecutting: cut 1x Polished Pyroceramic Wall from Pyroceramic.", "Steins\u00e4ge: 1x Polierte Pyrokeramikmauer aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_bricks_from_pyroceramic_stonecutting", "copper_inferno:pyroceramic_bricks", "kilnstone/pyroceramic_bricks_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_bricks", 1, "Stonecutting: cut 1x Pyroceramic Bricks from Pyroceramic.", "Steins\u00e4ge: 1x Pyrokeramikziegel aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_brick_slab_from_pyroceramic_stonecutting", "copper_inferno:pyroceramic_brick_slab", "kilnstone/pyroceramic_brick_slab_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_brick_slab", 2, "Stonecutting: cut 2x Pyroceramic Brick Slab from Pyroceramic.", "Steins\u00e4ge: 2x Pyrokeramikziegelstufe aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_brick_stairs_from_pyroceramic_stonecutting", "copper_inferno:pyroceramic_brick_stairs", "kilnstone/pyroceramic_brick_stairs_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_brick_stairs", 1, "Stonecutting: cut 1x Pyroceramic Brick Stairs from Pyroceramic.", "Steins\u00e4ge: 1x Pyrokeramikziegeltreppe aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_brick_wall_from_pyroceramic_stonecutting", "copper_inferno:pyroceramic_brick_wall", "kilnstone/pyroceramic_brick_wall_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_brick_wall", 1, "Stonecutting: cut 1x Pyroceramic Brick Wall from Pyroceramic.", "Steins\u00e4ge: 1x Pyrokeramikziegelmauer aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_tiles_from_pyroceramic_stonecutting", "copper_inferno:pyroceramic_tiles", "kilnstone/pyroceramic_tiles_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_tiles", 1, "Stonecutting: cut 1x Pyroceramic Tiles from Pyroceramic.", "Steins\u00e4ge: 1x Pyrokeramikfliesen aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/chiseled_pyroceramic_bricks_from_pyroceramic_stonecutting", "copper_inferno:chiseled_pyroceramic_bricks", "kilnstone/chiseled_pyroceramic_bricks_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:chiseled_pyroceramic_bricks", 1, "Stonecutting: cut 1x Chiseled Pyroceramic Bricks from Pyroceramic.", "Steins\u00e4ge: 1x Gemei\u00dfelte Pyrokeramikziegel aus Pyrokeramik schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "kilnstone/pyroceramic_pillar_from_pyroceramic_stonecutting", "copper_inferno:pyroceramic_pillar", "kilnstone/pyroceramic_pillar_from_pyroceramic_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyroceramic", "", "", "", ""},
				"copper_inferno:pyroceramic_pillar", 1, "Stonecutting: cut 1x Pyroceramic Pillar from Pyroceramic.", "Steins\u00e4ge: 1x Pyrokeramiks\u00e4ule aus Pyrokeramik schneiden."));
	}
}
