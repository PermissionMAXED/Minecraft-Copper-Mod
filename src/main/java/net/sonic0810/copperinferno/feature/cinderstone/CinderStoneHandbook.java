package net.sonic0810.copperinferno.feature.cinderstone;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Cinder Stone block set: one "blocks" overview per cube family plus one
 * recipe page for every JSON under {@code data/copper_inferno/recipe/cinderstone/} (crafting,
 * smelting and stonecutting). Entry texts and grids mirror the recipe JSONs emitted by
 * {@code devtools/gen/cinderstone_gen.py}; {@code devtools/check_handbook.py} parses the inline
 * {@code new HandbookEntry(...)} literals positionally, so keep them inline.
 */
final class CinderStoneHandbook {
	private CinderStoneHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/family_cinderstone_bricks", "copper_inferno:cinderstone_bricks", null,
				null,
				null, 0, "The Cinderstone Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Zundersteinziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/family_cinderstone_tiles", "copper_inferno:cinderstone_tiles", null,
				null,
				null, 0, "The Cinderstone Tiles family for Inferno builds: block, stairs, slab and wall.", "Die Zundersteinfliesen-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/family_polished_cinderstone", "copper_inferno:polished_cinderstone", null,
				null,
				null, 0, "The Polished Cinderstone family for Inferno builds: block, stairs, slab and wall.", "Die Polierter Zunderstein-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/family_slagstone_bricks", "copper_inferno:slagstone_bricks", null,
				null,
				null, 0, "The Slagstone Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Schlackensteinziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/family_ashen_bricks", "copper_inferno:ashen_bricks", null,
				null,
				null, 0, "The Ashen Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Aschenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/family_smoldering_bricks", "copper_inferno:smoldering_bricks", null,
				null,
				null, 0, "The Smoldering Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Schwelziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/family_forge_bricks", "copper_inferno:forge_bricks", null,
				null,
				null, 0, "The Forge Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Schmiedeziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/family_quenched_slag", "copper_inferno:quenched_slag", null,
				null,
				null, 0, "The Quenched Slag family for Inferno builds: block, stairs, slab and wall.", "Die Abgeschreckte Schlacke-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_bricks", "copper_inferno:cinderstone_bricks", "cinderstone/cinderstone_bricks",
				new String[] {"minecraft:deepslate", "", "minecraft:deepslate", "", "minecraft:charcoal", "", "minecraft:deepslate", "", "minecraft:deepslate"},
				"copper_inferno:cinderstone_bricks", 4, "Craft 4x Cinderstone Bricks at a crafting table.", "Stellt 4x Zundersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_tiles", "copper_inferno:cinderstone_tiles", "cinderstone/cinderstone_tiles",
				new String[] {"copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "", "copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "", "", "", ""},
				"copper_inferno:cinderstone_tiles", 4, "Craft 4x Cinderstone Tiles at a crafting table.", "Stellt 4x Zundersteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/polished_cinderstone", "copper_inferno:polished_cinderstone", "cinderstone/polished_cinderstone",
				new String[] {"copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "", "copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "", "", "", ""},
				"copper_inferno:polished_cinderstone", 4, "Craft 4x Polished Cinderstone at a crafting table.", "Stellt 4x Polierter Zunderstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/slagstone_bricks", "copper_inferno:slagstone_bricks", "cinderstone/slagstone_bricks",
				new String[] {"minecraft:blackstone", "", "minecraft:blackstone", "", "minecraft:prismarine_shard", "", "minecraft:blackstone", "", "minecraft:blackstone"},
				"copper_inferno:slagstone_bricks", 4, "Craft 4x Slagstone Bricks at a crafting table.", "Stellt 4x Schlackensteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ashen_bricks", "copper_inferno:ashen_bricks", "cinderstone/ashen_bricks",
				new String[] {"minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "", "minecraft:bone_meal", "", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate"},
				"copper_inferno:ashen_bricks", 4, "Craft 4x Ashen Bricks at a crafting table.", "Stellt 4x Aschenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/smoldering_bricks", "copper_inferno:smoldering_bricks", "cinderstone/smoldering_bricks",
				new String[] {"copper_inferno:cinderstone_bricks", "", "copper_inferno:cinderstone_bricks", "", "minecraft:magma_block", "", "copper_inferno:cinderstone_bricks", "", "copper_inferno:cinderstone_bricks"},
				"copper_inferno:smoldering_bricks", 4, "Craft 4x Smoldering Bricks at a crafting table.", "Stellt 4x Schwelziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/forge_bricks", "copper_inferno:forge_bricks", "cinderstone/forge_bricks",
				new String[] {"copper_inferno:cinderstone_bricks", "", "copper_inferno:cinderstone_bricks", "", "minecraft:copper_ingot", "", "copper_inferno:cinderstone_bricks", "", "copper_inferno:cinderstone_bricks"},
				"copper_inferno:forge_bricks", 4, "Craft 4x Forge Bricks at a crafting table.", "Stellt 4x Schmiedeziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/quenched_slag", "copper_inferno:quenched_slag", "cinderstone/quenched_slag",
				new String[] {"copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "", "copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "", "", "", ""},
				"copper_inferno:quenched_slag", 4, "Craft 4x Quenched Slag at a crafting table.", "Stellt 4x Abgeschreckte Schlacke an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_brick_stairs", "copper_inferno:cinderstone_brick_stairs", "cinderstone/cinderstone_brick_stairs",
				new String[] {"copper_inferno:cinderstone_bricks", "", "", "copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "", "copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks"},
				"copper_inferno:cinderstone_brick_stairs", 4, "Craft 4x Cinderstone Brick Stairs at a crafting table.", "Stellt 4x Zundersteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_brick_slab", "copper_inferno:cinderstone_brick_slab", "cinderstone/cinderstone_brick_slab",
				new String[] {"copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "", "", "", "", "", ""},
				"copper_inferno:cinderstone_brick_slab", 6, "Craft 6x Cinderstone Brick Slab at a crafting table.", "Stellt 6x Zundersteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_brick_wall", "copper_inferno:cinderstone_brick_wall", "cinderstone/cinderstone_brick_wall",
				new String[] {"copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "copper_inferno:cinderstone_bricks", "", "", ""},
				"copper_inferno:cinderstone_brick_wall", 6, "Craft 6x Cinderstone Brick Wall at a crafting table.", "Stellt 6x Zundersteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_tile_stairs", "copper_inferno:cinderstone_tile_stairs", "cinderstone/cinderstone_tile_stairs",
				new String[] {"copper_inferno:cinderstone_tiles", "", "", "copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "", "copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles"},
				"copper_inferno:cinderstone_tile_stairs", 4, "Craft 4x Cinderstone Tile Stairs at a crafting table.", "Stellt 4x Zundersteinfliesentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_tile_slab", "copper_inferno:cinderstone_tile_slab", "cinderstone/cinderstone_tile_slab",
				new String[] {"copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "", "", "", "", "", ""},
				"copper_inferno:cinderstone_tile_slab", 6, "Craft 6x Cinderstone Tile Slab at a crafting table.", "Stellt 6x Zundersteinfliesenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_tile_wall", "copper_inferno:cinderstone_tile_wall", "cinderstone/cinderstone_tile_wall",
				new String[] {"copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "copper_inferno:cinderstone_tiles", "", "", ""},
				"copper_inferno:cinderstone_tile_wall", 6, "Craft 6x Cinderstone Tile Wall at a crafting table.", "Stellt 6x Zundersteinfliesenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/polished_cinderstone_stairs", "copper_inferno:polished_cinderstone_stairs", "cinderstone/polished_cinderstone_stairs",
				new String[] {"copper_inferno:polished_cinderstone", "", "", "copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone", "", "copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone"},
				"copper_inferno:polished_cinderstone_stairs", 4, "Craft 4x Polished Cinderstone Stairs at a crafting table.", "Stellt 4x Polierte Zundersteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/polished_cinderstone_slab", "copper_inferno:polished_cinderstone_slab", "cinderstone/polished_cinderstone_slab",
				new String[] {"copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone", "", "", "", "", "", ""},
				"copper_inferno:polished_cinderstone_slab", 6, "Craft 6x Polished Cinderstone Slab at a crafting table.", "Stellt 6x Polierte Zundersteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/polished_cinderstone_wall", "copper_inferno:polished_cinderstone_wall", "cinderstone/polished_cinderstone_wall",
				new String[] {"copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone", "copper_inferno:polished_cinderstone", "", "", ""},
				"copper_inferno:polished_cinderstone_wall", 6, "Craft 6x Polished Cinderstone Wall at a crafting table.", "Stellt 6x Polierte Zundersteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/slagstone_brick_stairs", "copper_inferno:slagstone_brick_stairs", "cinderstone/slagstone_brick_stairs",
				new String[] {"copper_inferno:slagstone_bricks", "", "", "copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "", "copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks"},
				"copper_inferno:slagstone_brick_stairs", 4, "Craft 4x Slagstone Brick Stairs at a crafting table.", "Stellt 4x Schlackensteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/slagstone_brick_slab", "copper_inferno:slagstone_brick_slab", "cinderstone/slagstone_brick_slab",
				new String[] {"copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "", "", "", "", "", ""},
				"copper_inferno:slagstone_brick_slab", 6, "Craft 6x Slagstone Brick Slab at a crafting table.", "Stellt 6x Schlackensteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/slagstone_brick_wall", "copper_inferno:slagstone_brick_wall", "cinderstone/slagstone_brick_wall",
				new String[] {"copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "copper_inferno:slagstone_bricks", "", "", ""},
				"copper_inferno:slagstone_brick_wall", 6, "Craft 6x Slagstone Brick Wall at a crafting table.", "Stellt 6x Schlackensteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ashen_brick_stairs", "copper_inferno:ashen_brick_stairs", "cinderstone/ashen_brick_stairs",
				new String[] {"copper_inferno:ashen_bricks", "", "", "copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "", "copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks"},
				"copper_inferno:ashen_brick_stairs", 4, "Craft 4x Ashen Brick Stairs at a crafting table.", "Stellt 4x Aschenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ashen_brick_slab", "copper_inferno:ashen_brick_slab", "cinderstone/ashen_brick_slab",
				new String[] {"copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "", "", "", "", "", ""},
				"copper_inferno:ashen_brick_slab", 6, "Craft 6x Ashen Brick Slab at a crafting table.", "Stellt 6x Aschenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ashen_brick_wall", "copper_inferno:ashen_brick_wall", "cinderstone/ashen_brick_wall",
				new String[] {"copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "", "", ""},
				"copper_inferno:ashen_brick_wall", 6, "Craft 6x Ashen Brick Wall at a crafting table.", "Stellt 6x Aschenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/smoldering_brick_stairs", "copper_inferno:smoldering_brick_stairs", "cinderstone/smoldering_brick_stairs",
				new String[] {"copper_inferno:smoldering_bricks", "", "", "copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks", "", "copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks"},
				"copper_inferno:smoldering_brick_stairs", 4, "Craft 4x Smoldering Brick Stairs at a crafting table.", "Stellt 4x Schwelziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/smoldering_brick_slab", "copper_inferno:smoldering_brick_slab", "cinderstone/smoldering_brick_slab",
				new String[] {"copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks", "", "", "", "", "", ""},
				"copper_inferno:smoldering_brick_slab", 6, "Craft 6x Smoldering Brick Slab at a crafting table.", "Stellt 6x Schwelziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/smoldering_brick_wall", "copper_inferno:smoldering_brick_wall", "cinderstone/smoldering_brick_wall",
				new String[] {"copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks", "copper_inferno:smoldering_bricks", "", "", ""},
				"copper_inferno:smoldering_brick_wall", 6, "Craft 6x Smoldering Brick Wall at a crafting table.", "Stellt 6x Schwelziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/forge_brick_stairs", "copper_inferno:forge_brick_stairs", "cinderstone/forge_brick_stairs",
				new String[] {"copper_inferno:forge_bricks", "", "", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks"},
				"copper_inferno:forge_brick_stairs", 4, "Craft 4x Forge Brick Stairs at a crafting table.", "Stellt 4x Schmiedeziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/forge_brick_slab", "copper_inferno:forge_brick_slab", "cinderstone/forge_brick_slab",
				new String[] {"copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "", "", "", "", "", ""},
				"copper_inferno:forge_brick_slab", 6, "Craft 6x Forge Brick Slab at a crafting table.", "Stellt 6x Schmiedeziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/forge_brick_wall", "copper_inferno:forge_brick_wall", "cinderstone/forge_brick_wall",
				new String[] {"copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "", "", ""},
				"copper_inferno:forge_brick_wall", 6, "Craft 6x Forge Brick Wall at a crafting table.", "Stellt 6x Schmiedeziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/quenched_slag_stairs", "copper_inferno:quenched_slag_stairs", "cinderstone/quenched_slag_stairs",
				new String[] {"copper_inferno:quenched_slag", "", "", "copper_inferno:quenched_slag", "copper_inferno:quenched_slag", "", "copper_inferno:quenched_slag", "copper_inferno:quenched_slag", "copper_inferno:quenched_slag"},
				"copper_inferno:quenched_slag_stairs", 4, "Craft 4x Quenched Slag Stairs at a crafting table.", "Stellt 4x Abgeschreckte Schlackentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/quenched_slag_slab", "copper_inferno:quenched_slag_slab", "cinderstone/quenched_slag_slab",
				new String[] {"copper_inferno:quenched_slag", "copper_inferno:quenched_slag", "copper_inferno:quenched_slag", "", "", "", "", "", ""},
				"copper_inferno:quenched_slag_slab", 6, "Craft 6x Quenched Slag Slab at a crafting table.", "Stellt 6x Abgeschreckte Schlackenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/quenched_slag_wall", "copper_inferno:quenched_slag_wall", "cinderstone/quenched_slag_wall",
				new String[] {"copper_inferno:quenched_slag", "copper_inferno:quenched_slag", "copper_inferno:quenched_slag", "copper_inferno:quenched_slag", "copper_inferno:quenched_slag", "copper_inferno:quenched_slag", "", "", ""},
				"copper_inferno:quenched_slag_wall", 6, "Craft 6x Quenched Slag Wall at a crafting table.", "Stellt 6x Abgeschreckte Schlackenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cracked_cinderstone_bricks", "copper_inferno:cracked_cinderstone_bricks", "cinderstone/cracked_cinderstone_bricks",
				new String[] {"", "", "", "", "copper_inferno:cinderstone_bricks", "", "", "", ""},
				"copper_inferno:cracked_cinderstone_bricks", 1, "Smelting Cinderstone Bricks in a furnace yields Cracked Cinderstone Bricks.", "Zundersteinziegel im Ofen gebrannt ergibt Rissige Zundersteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cracked_slagstone_bricks", "copper_inferno:cracked_slagstone_bricks", "cinderstone/cracked_slagstone_bricks",
				new String[] {"", "", "", "", "copper_inferno:slagstone_bricks", "", "", "", ""},
				"copper_inferno:cracked_slagstone_bricks", 1, "Smelting Slagstone Bricks in a furnace yields Cracked Slagstone Bricks.", "Schlackensteinziegel im Ofen gebrannt ergibt Rissige Schlackensteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cracked_forge_bricks", "copper_inferno:cracked_forge_bricks", "cinderstone/cracked_forge_bricks",
				new String[] {"", "", "", "", "copper_inferno:forge_bricks", "", "", "", ""},
				"copper_inferno:cracked_forge_bricks", 1, "Smelting Forge Bricks in a furnace yields Cracked Forge Bricks.", "Schmiedeziegel im Ofen gebrannt ergibt Rissige Schmiedeziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/chiseled_cinderstone_bricks", "copper_inferno:chiseled_cinderstone_bricks", "cinderstone/chiseled_cinderstone_bricks",
				new String[] {"copper_inferno:cinderstone_brick_slab", "", "", "copper_inferno:cinderstone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_cinderstone_bricks", 1, "Craft 1x Chiseled Cinderstone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Zundersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/chiseled_slagstone_bricks", "copper_inferno:chiseled_slagstone_bricks", "cinderstone/chiseled_slagstone_bricks",
				new String[] {"copper_inferno:slagstone_brick_slab", "", "", "copper_inferno:slagstone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_slagstone_bricks", 1, "Craft 1x Chiseled Slagstone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schlackensteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/chiseled_forge_bricks", "copper_inferno:chiseled_forge_bricks", "cinderstone/chiseled_forge_bricks",
				new String[] {"copper_inferno:forge_brick_slab", "", "", "copper_inferno:forge_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_forge_bricks", 1, "Craft 1x Chiseled Forge Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schmiedeziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/chiseled_ashen_bricks", "copper_inferno:chiseled_ashen_bricks", "cinderstone/chiseled_ashen_bricks",
				new String[] {"copper_inferno:ashen_brick_slab", "", "", "copper_inferno:ashen_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_ashen_bricks", 1, "Craft 1x Chiseled Ashen Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Aschenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/carved_cinderstone", "copper_inferno:carved_cinderstone", "cinderstone/carved_cinderstone",
				new String[] {"copper_inferno:polished_cinderstone_slab", "", "", "copper_inferno:polished_cinderstone_slab", "", "", "", "", ""},
				"copper_inferno:carved_cinderstone", 1, "Craft 1x Carved Cinderstone at a crafting table.", "Stellt 1x Verzierter Zunderstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_pillar", "copper_inferno:cinderstone_pillar", "cinderstone/cinderstone_pillar",
				new String[] {"copper_inferno:cinderstone_bricks", "", "", "copper_inferno:cinderstone_bricks", "", "", "", "", ""},
				"copper_inferno:cinderstone_pillar", 2, "Craft 2x Cinderstone Pillar at a crafting table.", "Stellt 2x Zundersteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/slagstone_pillar", "copper_inferno:slagstone_pillar", "cinderstone/slagstone_pillar",
				new String[] {"copper_inferno:slagstone_bricks", "", "", "copper_inferno:slagstone_bricks", "", "", "", "", ""},
				"copper_inferno:slagstone_pillar", 2, "Craft 2x Slagstone Pillar at a crafting table.", "Stellt 2x Schlackensteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/forge_pillar", "copper_inferno:forge_pillar", "cinderstone/forge_pillar",
				new String[] {"copper_inferno:forge_bricks", "", "", "copper_inferno:forge_bricks", "", "", "", "", ""},
				"copper_inferno:forge_pillar", 2, "Craft 2x Forge Pillar at a crafting table.", "Stellt 2x Schmiedes\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ashen_pillar", "copper_inferno:ashen_pillar", "cinderstone/ashen_pillar",
				new String[] {"copper_inferno:ashen_bricks", "", "", "copper_inferno:ashen_bricks", "", "", "", "", ""},
				"copper_inferno:ashen_pillar", 2, "Craft 2x Ashen Pillar at a crafting table.", "Stellt 2x Aschens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/quenched_slag_pillar", "copper_inferno:quenched_slag_pillar", "cinderstone/quenched_slag_pillar",
				new String[] {"copper_inferno:quenched_slag", "", "", "copper_inferno:quenched_slag", "", "", "", "", ""},
				"copper_inferno:quenched_slag_pillar", 2, "Craft 2x Quenched Slag Pillar at a crafting table.", "Stellt 2x Abgeschreckte Schlackens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ashen_mosaic", "copper_inferno:ashen_mosaic", "cinderstone/ashen_mosaic",
				new String[] {"copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "", "copper_inferno:ashen_bricks", "copper_inferno:ashen_bricks", "", "", "", ""},
				"copper_inferno:ashen_mosaic", 4, "Craft 4x Ashen Mosaic at a crafting table.", "Stellt 4x Aschenmosaik an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ember_coal_block", "copper_inferno:ember_coal_block", "cinderstone/ember_coal_block",
				new String[] {"minecraft:coal_block", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:ember_coal_block", 1, "Craft 1x Ember Coal Block at a crafting table.", "Stellt 1x Glutkohleblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ember_lantern", "copper_inferno:ember_lantern", "cinderstone/ember_lantern",
				new String[] {"minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:blaze_powder", "minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:iron_nugget"},
				"copper_inferno:ember_lantern", 1, "Craft 1x Ember Lantern at a crafting table.", "Stellt 1x Glutlaterne an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ashen_lantern", "copper_inferno:ashen_lantern", "cinderstone/ashen_lantern",
				new String[] {"minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:glow_ink_sac", "minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:iron_nugget", "minecraft:iron_nugget"},
				"copper_inferno:ashen_lantern", 1, "Craft 1x Ashen Lantern at a crafting table.", "Stellt 1x Aschenlaterne an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/smolder_lamp", "copper_inferno:smolder_lamp", "cinderstone/smolder_lamp",
				new String[] {"minecraft:glowstone", "minecraft:magma_block", "", "", "", "", "", "", ""},
				"copper_inferno:smolder_lamp", 1, "Craft 1x Smolder Lamp at a crafting table.", "Stellt 1x Schwellampe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/ashen_lamp", "copper_inferno:ashen_lamp", "cinderstone/ashen_lamp",
				new String[] {"minecraft:glowstone", "copper_inferno:ashen_bricks", "", "", "", "", "", "", ""},
				"copper_inferno:ashen_lamp", 1, "Craft 1x Ashen Lamp at a crafting table.", "Stellt 1x Aschenlampe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinder_glass", "copper_inferno:cinder_glass", "cinderstone/cinder_glass",
				new String[] {"minecraft:glass", "minecraft:glass", "minecraft:glass", "minecraft:glass", "minecraft:charcoal", "minecraft:glass", "minecraft:glass", "minecraft:glass", "minecraft:glass"},
				"copper_inferno:cinder_glass", 8, "Craft 8x Cinder Glass at a crafting table.", "Stellt 8x Zunderglas an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/smolder_glass", "copper_inferno:smolder_glass", "cinderstone/smolder_glass",
				new String[] {"minecraft:glass", "minecraft:glass", "minecraft:glass", "minecraft:glass", "minecraft:magma_cream", "minecraft:glass", "minecraft:glass", "minecraft:glass", "minecraft:glass"},
				"copper_inferno:smolder_glass", 8, "Craft 8x Smolder Glass at a crafting table.", "Stellt 8x Schwelglas an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinder_glass_pane", "copper_inferno:cinder_glass_pane", "cinderstone/cinder_glass_pane",
				new String[] {"copper_inferno:cinder_glass", "copper_inferno:cinder_glass", "copper_inferno:cinder_glass", "copper_inferno:cinder_glass", "copper_inferno:cinder_glass", "copper_inferno:cinder_glass", "", "", ""},
				"copper_inferno:cinder_glass_pane", 16, "Craft 16x Cinder Glass Pane at a crafting table.", "Stellt 16x Zunderglasscheibe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/smolder_glass_pane", "copper_inferno:smolder_glass_pane", "cinderstone/smolder_glass_pane",
				new String[] {"copper_inferno:smolder_glass", "copper_inferno:smolder_glass", "copper_inferno:smolder_glass", "copper_inferno:smolder_glass", "copper_inferno:smolder_glass", "copper_inferno:smolder_glass", "", "", ""},
				"copper_inferno:smolder_glass_pane", 16, "Craft 16x Smolder Glass Pane at a crafting table.", "Stellt 16x Schwelglasscheibe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/forge_heart", "copper_inferno:forge_heart", "cinderstone/forge_heart",
				new String[] {"copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "minecraft:copper_block", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks", "copper_inferno:forge_bricks"},
				"copper_inferno:forge_heart", 1, "Craft 1x Forge Heart at a crafting table.", "Stellt 1x Schmiedeherz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/chiseled_cinderstone_bricks_from_cinderstone_bricks_stonecutting", "copper_inferno:chiseled_cinderstone_bricks", "cinderstone/chiseled_cinderstone_bricks_from_cinderstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderstone_bricks", "", "", "", ""},
				"copper_inferno:chiseled_cinderstone_bricks", 1, "Stonecutting: cut 1x Chiseled Cinderstone Bricks from Cinderstone Bricks.", "Steins\u00e4ge: 1x Gemei\u00dfelte Zundersteinziegel aus Zundersteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/chiseled_slagstone_bricks_from_slagstone_bricks_stonecutting", "copper_inferno:chiseled_slagstone_bricks", "cinderstone/chiseled_slagstone_bricks_from_slagstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagstone_bricks", "", "", "", ""},
				"copper_inferno:chiseled_slagstone_bricks", 1, "Stonecutting: cut 1x Chiseled Slagstone Bricks from Slagstone Bricks.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schlackensteinziegel aus Schlackensteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_brick_slab_from_cinderstone_bricks_stonecutting", "copper_inferno:cinderstone_brick_slab", "cinderstone/cinderstone_brick_slab_from_cinderstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderstone_bricks", "", "", "", ""},
				"copper_inferno:cinderstone_brick_slab", 2, "Stonecutting: cut 2x Cinderstone Brick Slab from Cinderstone Bricks.", "Steins\u00e4ge: 2x Zundersteinziegelstufe aus Zundersteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_brick_stairs_from_cinderstone_bricks_stonecutting", "copper_inferno:cinderstone_brick_stairs", "cinderstone/cinderstone_brick_stairs_from_cinderstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderstone_bricks", "", "", "", ""},
				"copper_inferno:cinderstone_brick_stairs", 1, "Stonecutting: cut 1x Cinderstone Brick Stairs from Cinderstone Bricks.", "Steins\u00e4ge: 1x Zundersteinziegeltreppe aus Zundersteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_brick_wall_from_cinderstone_bricks_stonecutting", "copper_inferno:cinderstone_brick_wall", "cinderstone/cinderstone_brick_wall_from_cinderstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderstone_bricks", "", "", "", ""},
				"copper_inferno:cinderstone_brick_wall", 1, "Stonecutting: cut 1x Cinderstone Brick Wall from Cinderstone Bricks.", "Steins\u00e4ge: 1x Zundersteinziegelmauer aus Zundersteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_pillar_from_cinderstone_bricks_stonecutting", "copper_inferno:cinderstone_pillar", "cinderstone/cinderstone_pillar_from_cinderstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderstone_bricks", "", "", "", ""},
				"copper_inferno:cinderstone_pillar", 1, "Stonecutting: cut 1x Cinderstone Pillar from Cinderstone Bricks.", "Steins\u00e4ge: 1x Zundersteins\u00e4ule aus Zundersteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/cinderstone_tiles_from_cinderstone_bricks_stonecutting", "copper_inferno:cinderstone_tiles", "cinderstone/cinderstone_tiles_from_cinderstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderstone_bricks", "", "", "", ""},
				"copper_inferno:cinderstone_tiles", 1, "Stonecutting: cut 1x Cinderstone Tiles from Cinderstone Bricks.", "Steins\u00e4ge: 1x Zundersteinfliesen aus Zundersteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/polished_cinderstone_from_cinderstone_bricks_stonecutting", "copper_inferno:polished_cinderstone", "cinderstone/polished_cinderstone_from_cinderstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderstone_bricks", "", "", "", ""},
				"copper_inferno:polished_cinderstone", 1, "Stonecutting: cut 1x Polished Cinderstone from Cinderstone Bricks.", "Steins\u00e4ge: 1x Polierter Zunderstein aus Zundersteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/quenched_slag_from_slagstone_bricks_stonecutting", "copper_inferno:quenched_slag", "cinderstone/quenched_slag_from_slagstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagstone_bricks", "", "", "", ""},
				"copper_inferno:quenched_slag", 1, "Stonecutting: cut 1x Quenched Slag from Slagstone Bricks.", "Steins\u00e4ge: 1x Abgeschreckte Schlacke aus Schlackensteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/slagstone_brick_slab_from_slagstone_bricks_stonecutting", "copper_inferno:slagstone_brick_slab", "cinderstone/slagstone_brick_slab_from_slagstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagstone_bricks", "", "", "", ""},
				"copper_inferno:slagstone_brick_slab", 2, "Stonecutting: cut 2x Slagstone Brick Slab from Slagstone Bricks.", "Steins\u00e4ge: 2x Schlackensteinziegelstufe aus Schlackensteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/slagstone_brick_stairs_from_slagstone_bricks_stonecutting", "copper_inferno:slagstone_brick_stairs", "cinderstone/slagstone_brick_stairs_from_slagstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagstone_bricks", "", "", "", ""},
				"copper_inferno:slagstone_brick_stairs", 1, "Stonecutting: cut 1x Slagstone Brick Stairs from Slagstone Bricks.", "Steins\u00e4ge: 1x Schlackensteinziegeltreppe aus Schlackensteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/slagstone_brick_wall_from_slagstone_bricks_stonecutting", "copper_inferno:slagstone_brick_wall", "cinderstone/slagstone_brick_wall_from_slagstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagstone_bricks", "", "", "", ""},
				"copper_inferno:slagstone_brick_wall", 1, "Stonecutting: cut 1x Slagstone Brick Wall from Slagstone Bricks.", "Steins\u00e4ge: 1x Schlackensteinziegelmauer aus Schlackensteinziegel schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "cinderstone/slagstone_pillar_from_slagstone_bricks_stonecutting", "copper_inferno:slagstone_pillar", "cinderstone/slagstone_pillar_from_slagstone_bricks_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slagstone_bricks", "", "", "", ""},
				"copper_inferno:slagstone_pillar", 1, "Stonecutting: cut 1x Slagstone Pillar from Slagstone Bricks.", "Steins\u00e4ge: 1x Schlackensteins\u00e4ule aus Schlackensteinziegel schneiden."));
	}
}
