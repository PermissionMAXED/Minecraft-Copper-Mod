package net.sonic0810.copperinferno.feature.nightslate;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Nightslate block set: one "blocks" overview per material
 * plus one recipe page for every JSON under {@code data/copper_inferno/recipe/nightslate/}
 * (crafting, smelting and stonecutting). Entry texts and grids mirror the recipe JSONs
 * emitted by {@code devtools/gen/nightslate_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep them
 * inline.
 */
final class NightslateHandbook {
	private NightslateHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_nightslate", "copper_inferno:nightslate", null,
				null,
				null, 0, "The Nightslate set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Nachtschiefer-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_voidbasalt", "copper_inferno:voidbasalt", null,
				null,
				null, 0, "The Voidbasalt set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Leerenbasalt-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_duskstone", "copper_inferno:duskstone", null,
				null,
				null, 0, "The Duskstone set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das D\u00e4mmerstein-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_gloomstone", "copper_inferno:gloomstone", null,
				null,
				null, 0, "The Gloomstone set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das D\u00fcsterstein-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_shadow_tuff", "copper_inferno:shadow_tuff", null,
				null,
				null, 0, "The Shadow Tuff set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Schattentuff-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_umbral_deepslate", "copper_inferno:umbral_deepslate", null,
				null,
				null, 0, "The Umbral Deepslate set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Umbra-Tiefenschiefer-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_blacksoot_stone", "copper_inferno:blacksoot_stone", null,
				null,
				null, 0, "The Blacksoot Stone set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Schwarzru\u00dfstein-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_char_obsidian", "copper_inferno:char_obsidian", null,
				null,
				null, 0, "The Char Obsidian set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Brandobsidian-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_dark_pumice", "copper_inferno:dark_pumice", null,
				null,
				null, 0, "The Dark Pumice set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Dunkelbims-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_ebonstone", "copper_inferno:ebonstone", null,
				null,
				null, 0, "The Ebonstone set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Pechstein-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_murkrock", "copper_inferno:murkrock", null,
				null,
				null, 0, "The Murkrock set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Tr\u00fcbfels-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_cinderdark_stone", "copper_inferno:cinderdark_stone", null,
				null,
				null, 0, "The Cinderdark Stone set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Dunkelzunderstein-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_netherveil_stone", "copper_inferno:netherveil_stone", null,
				null,
				null, 0, "The Netherveil Stone set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Netherschleierstein-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/set_obscura_stone", "copper_inferno:obscura_stone", null,
				null,
				null, 0, "The Obscura Stone set for dark volcanic builds: base, polished and brick families (block, stairs, slab, wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Obskurastein-Set f\u00fcr dunkle Vulkanbauten: Grund-, Polier- und Ziegelfamilie (Block, Treppe, Stufe, Mauer) dazu Fliesen, rissige und gemei\u00dfelte Ziegel sowie eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate", "copper_inferno:nightslate", "nightslate/nightslate",
				new String[] {"minecraft:deepslate", "", "minecraft:deepslate", "", "copper_inferno:ash_block", "", "minecraft:deepslate", "", "minecraft:deepslate"},
				"copper_inferno:nightslate", 4, "Craft 4x Nightslate at a crafting table.", "Stellt 4x Nachtschiefer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_bricks", "copper_inferno:nightslate_bricks", "nightslate/nightslate_bricks",
				new String[] {"copper_inferno:nightslate", "copper_inferno:nightslate", "", "copper_inferno:nightslate", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_bricks", 4, "Craft 4x Nightslate Bricks at a crafting table.", "Stellt 4x Nachtschieferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_tiles", "copper_inferno:nightslate_tiles", "nightslate/nightslate_tiles",
				new String[] {"copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "", "copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "", "", "", ""},
				"copper_inferno:nightslate_tiles", 4, "Craft 4x Nightslate Tiles at a crafting table.", "Stellt 4x Nachtschieferfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_nightslate", "copper_inferno:polished_nightslate", "nightslate/polished_nightslate",
				new String[] {"copper_inferno:nightslate_tiles", "copper_inferno:nightslate_tiles", "", "copper_inferno:nightslate_tiles", "copper_inferno:nightslate_tiles", "", "", "", ""},
				"copper_inferno:polished_nightslate", 4, "Craft 4x Polished Nightslate at a crafting table.", "Stellt 4x Polierten Nachtschiefer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_slab", "copper_inferno:nightslate_slab", "nightslate/nightslate_slab",
				new String[] {"copper_inferno:nightslate", "copper_inferno:nightslate", "copper_inferno:nightslate", "", "", "", "", "", ""},
				"copper_inferno:nightslate_slab", 6, "Craft 6x Nightslate Slab at a crafting table.", "Stellt 6x Nachtschieferstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_stairs", "copper_inferno:nightslate_stairs", "nightslate/nightslate_stairs",
				new String[] {"copper_inferno:nightslate", "", "", "copper_inferno:nightslate", "copper_inferno:nightslate", "", "copper_inferno:nightslate", "copper_inferno:nightslate", "copper_inferno:nightslate"},
				"copper_inferno:nightslate_stairs", 4, "Craft 4x Nightslate Stairs at a crafting table.", "Stellt 4x Nachtschiefertreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_wall", "copper_inferno:nightslate_wall", "nightslate/nightslate_wall",
				new String[] {"copper_inferno:nightslate", "copper_inferno:nightslate", "copper_inferno:nightslate", "copper_inferno:nightslate", "copper_inferno:nightslate", "copper_inferno:nightslate", "", "", ""},
				"copper_inferno:nightslate_wall", 6, "Craft 6x Nightslate Wall at a crafting table.", "Stellt 6x Nachtschiefermauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_nightslate_slab", "copper_inferno:polished_nightslate_slab", "nightslate/polished_nightslate_slab",
				new String[] {"copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate", "", "", "", "", "", ""},
				"copper_inferno:polished_nightslate_slab", 6, "Craft 6x Polished Nightslate Slab at a crafting table.", "Stellt 6x Polierte Nachtschieferstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_nightslate_stairs", "copper_inferno:polished_nightslate_stairs", "nightslate/polished_nightslate_stairs",
				new String[] {"copper_inferno:polished_nightslate", "", "", "copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate", "", "copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate"},
				"copper_inferno:polished_nightslate_stairs", 4, "Craft 4x Polished Nightslate Stairs at a crafting table.", "Stellt 4x Polierte Nachtschiefertreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_nightslate_wall", "copper_inferno:polished_nightslate_wall", "nightslate/polished_nightslate_wall",
				new String[] {"copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate", "copper_inferno:polished_nightslate", "", "", ""},
				"copper_inferno:polished_nightslate_wall", 6, "Craft 6x Polished Nightslate Wall at a crafting table.", "Stellt 6x Polierte Nachtschiefermauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_brick_slab", "copper_inferno:nightslate_brick_slab", "nightslate/nightslate_brick_slab",
				new String[] {"copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "", "", "", "", "", ""},
				"copper_inferno:nightslate_brick_slab", 6, "Craft 6x Nightslate Brick Slab at a crafting table.", "Stellt 6x Nachtschieferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_brick_stairs", "copper_inferno:nightslate_brick_stairs", "nightslate/nightslate_brick_stairs",
				new String[] {"copper_inferno:nightslate_bricks", "", "", "copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "", "copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks"},
				"copper_inferno:nightslate_brick_stairs", 4, "Craft 4x Nightslate Brick Stairs at a crafting table.", "Stellt 4x Nachtschieferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_brick_wall", "copper_inferno:nightslate_brick_wall", "nightslate/nightslate_brick_wall",
				new String[] {"copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "copper_inferno:nightslate_bricks", "", "", ""},
				"copper_inferno:nightslate_brick_wall", 6, "Craft 6x Nightslate Brick Wall at a crafting table.", "Stellt 6x Nachtschieferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_nightslate_bricks", "copper_inferno:cracked_nightslate_bricks", "nightslate/cracked_nightslate_bricks",
				new String[] {"", "", "", "", "copper_inferno:nightslate_bricks", "", "", "", ""},
				"copper_inferno:cracked_nightslate_bricks", 1, "Smelting Nightslate Bricks in a furnace yields Cracked Nightslate Bricks.", "Nachtschieferziegel im Ofen gebrannt ergibt Rissige Nachtschieferziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_nightslate_bricks", "copper_inferno:chiseled_nightslate_bricks", "nightslate/chiseled_nightslate_bricks",
				new String[] {"copper_inferno:nightslate_brick_slab", "", "", "copper_inferno:nightslate_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_nightslate_bricks", 1, "Craft 1x Chiseled Nightslate Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Nachtschieferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_pillar", "copper_inferno:nightslate_pillar", "nightslate/nightslate_pillar",
				new String[] {"copper_inferno:nightslate_bricks", "", "", "copper_inferno:nightslate_bricks", "", "", "", "", ""},
				"copper_inferno:nightslate_pillar", 2, "Craft 2x Nightslate Pillar at a crafting table.", "Stellt 2x Nachtschiefers\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_slab_from_nightslate_stonecutting", "copper_inferno:nightslate_slab", "nightslate/nightslate_slab_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_slab", 2, "Stonecutting: cut 2x Nightslate Slab from Nightslate.", "Steins\u00e4ge: 2x Nachtschieferstufe aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_stairs_from_nightslate_stonecutting", "copper_inferno:nightslate_stairs", "nightslate/nightslate_stairs_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_stairs", 1, "Stonecutting: cut 1x Nightslate Stairs from Nightslate.", "Steins\u00e4ge: 1x Nachtschiefertreppe aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_wall_from_nightslate_stonecutting", "copper_inferno:nightslate_wall", "nightslate/nightslate_wall_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_wall", 1, "Stonecutting: cut 1x Nightslate Wall from Nightslate.", "Steins\u00e4ge: 1x Nachtschiefermauer aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_nightslate_from_nightslate_stonecutting", "copper_inferno:polished_nightslate", "nightslate/polished_nightslate_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:polished_nightslate", 1, "Stonecutting: cut 1x Polished Nightslate from Nightslate.", "Steins\u00e4ge: 1x Polierten Nachtschiefer aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_nightslate_slab_from_nightslate_stonecutting", "copper_inferno:polished_nightslate_slab", "nightslate/polished_nightslate_slab_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:polished_nightslate_slab", 2, "Stonecutting: cut 2x Polished Nightslate Slab from Nightslate.", "Steins\u00e4ge: 2x Polierte Nachtschieferstufe aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_nightslate_stairs_from_nightslate_stonecutting", "copper_inferno:polished_nightslate_stairs", "nightslate/polished_nightslate_stairs_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:polished_nightslate_stairs", 1, "Stonecutting: cut 1x Polished Nightslate Stairs from Nightslate.", "Steins\u00e4ge: 1x Polierte Nachtschiefertreppe aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_nightslate_wall_from_nightslate_stonecutting", "copper_inferno:polished_nightslate_wall", "nightslate/polished_nightslate_wall_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:polished_nightslate_wall", 1, "Stonecutting: cut 1x Polished Nightslate Wall from Nightslate.", "Steins\u00e4ge: 1x Polierte Nachtschiefermauer aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_bricks_from_nightslate_stonecutting", "copper_inferno:nightslate_bricks", "nightslate/nightslate_bricks_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_bricks", 1, "Stonecutting: cut 1x Nightslate Bricks from Nightslate.", "Steins\u00e4ge: 1x Nachtschieferziegel aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_brick_slab_from_nightslate_stonecutting", "copper_inferno:nightslate_brick_slab", "nightslate/nightslate_brick_slab_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_brick_slab", 2, "Stonecutting: cut 2x Nightslate Brick Slab from Nightslate.", "Steins\u00e4ge: 2x Nachtschieferziegelstufe aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_brick_stairs_from_nightslate_stonecutting", "copper_inferno:nightslate_brick_stairs", "nightslate/nightslate_brick_stairs_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_brick_stairs", 1, "Stonecutting: cut 1x Nightslate Brick Stairs from Nightslate.", "Steins\u00e4ge: 1x Nachtschieferziegeltreppe aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_brick_wall_from_nightslate_stonecutting", "copper_inferno:nightslate_brick_wall", "nightslate/nightslate_brick_wall_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_brick_wall", 1, "Stonecutting: cut 1x Nightslate Brick Wall from Nightslate.", "Steins\u00e4ge: 1x Nachtschieferziegelmauer aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_tiles_from_nightslate_stonecutting", "copper_inferno:nightslate_tiles", "nightslate/nightslate_tiles_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_tiles", 1, "Stonecutting: cut 1x Nightslate Tiles from Nightslate.", "Steins\u00e4ge: 1x Nachtschieferfliesen aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_nightslate_bricks_from_nightslate_stonecutting", "copper_inferno:chiseled_nightslate_bricks", "nightslate/chiseled_nightslate_bricks_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:chiseled_nightslate_bricks", 1, "Stonecutting: cut 1x Chiseled Nightslate Bricks from Nightslate.", "Steins\u00e4ge: 1x Gemei\u00dfelte Nachtschieferziegel aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/nightslate_pillar_from_nightslate_stonecutting", "copper_inferno:nightslate_pillar", "nightslate/nightslate_pillar_from_nightslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:nightslate", "", "", "", ""},
				"copper_inferno:nightslate_pillar", 1, "Stonecutting: cut 1x Nightslate Pillar from Nightslate.", "Steins\u00e4ge: 1x Nachtschiefers\u00e4ule aus Nachtschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt", "copper_inferno:voidbasalt", "nightslate/voidbasalt",
				new String[] {"minecraft:basalt", "", "minecraft:basalt", "", "copper_inferno:nightslate", "", "minecraft:basalt", "", "minecraft:basalt"},
				"copper_inferno:voidbasalt", 4, "Craft 4x Voidbasalt at a crafting table.", "Stellt 4x Leerenbasalt an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "nightslate/voidbasalt_bricks",
				new String[] {"copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "", "copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_bricks", 4, "Craft 4x Voidbasalt Bricks at a crafting table.", "Stellt 4x Leerenbasaltziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_tiles", "copper_inferno:voidbasalt_tiles", "nightslate/voidbasalt_tiles",
				new String[] {"copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "", "copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "", "", "", ""},
				"copper_inferno:voidbasalt_tiles", 4, "Craft 4x Voidbasalt Tiles at a crafting table.", "Stellt 4x Leerenbasaltfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_voidbasalt", "copper_inferno:polished_voidbasalt", "nightslate/polished_voidbasalt",
				new String[] {"copper_inferno:voidbasalt_tiles", "copper_inferno:voidbasalt_tiles", "", "copper_inferno:voidbasalt_tiles", "copper_inferno:voidbasalt_tiles", "", "", "", ""},
				"copper_inferno:polished_voidbasalt", 4, "Craft 4x Polished Voidbasalt at a crafting table.", "Stellt 4x Polierten Leerenbasalt an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_slab", "copper_inferno:voidbasalt_slab", "nightslate/voidbasalt_slab",
				new String[] {"copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "", "", "", "", "", ""},
				"copper_inferno:voidbasalt_slab", 6, "Craft 6x Voidbasalt Slab at a crafting table.", "Stellt 6x Leerenbasaltstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_stairs", "copper_inferno:voidbasalt_stairs", "nightslate/voidbasalt_stairs",
				new String[] {"copper_inferno:voidbasalt", "", "", "copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "", "copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "copper_inferno:voidbasalt"},
				"copper_inferno:voidbasalt_stairs", 4, "Craft 4x Voidbasalt Stairs at a crafting table.", "Stellt 4x Leerenbasalttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_wall", "copper_inferno:voidbasalt_wall", "nightslate/voidbasalt_wall",
				new String[] {"copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "copper_inferno:voidbasalt", "", "", ""},
				"copper_inferno:voidbasalt_wall", 6, "Craft 6x Voidbasalt Wall at a crafting table.", "Stellt 6x Leerenbasaltmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_voidbasalt_slab", "copper_inferno:polished_voidbasalt_slab", "nightslate/polished_voidbasalt_slab",
				new String[] {"copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt", "", "", "", "", "", ""},
				"copper_inferno:polished_voidbasalt_slab", 6, "Craft 6x Polished Voidbasalt Slab at a crafting table.", "Stellt 6x Polierte Leerenbasaltstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_voidbasalt_stairs", "copper_inferno:polished_voidbasalt_stairs", "nightslate/polished_voidbasalt_stairs",
				new String[] {"copper_inferno:polished_voidbasalt", "", "", "copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt", "", "copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt"},
				"copper_inferno:polished_voidbasalt_stairs", 4, "Craft 4x Polished Voidbasalt Stairs at a crafting table.", "Stellt 4x Polierte Leerenbasalttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_voidbasalt_wall", "copper_inferno:polished_voidbasalt_wall", "nightslate/polished_voidbasalt_wall",
				new String[] {"copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt", "copper_inferno:polished_voidbasalt", "", "", ""},
				"copper_inferno:polished_voidbasalt_wall", 6, "Craft 6x Polished Voidbasalt Wall at a crafting table.", "Stellt 6x Polierte Leerenbasaltmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_brick_slab", "copper_inferno:voidbasalt_brick_slab", "nightslate/voidbasalt_brick_slab",
				new String[] {"copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "", "", "", "", "", ""},
				"copper_inferno:voidbasalt_brick_slab", 6, "Craft 6x Voidbasalt Brick Slab at a crafting table.", "Stellt 6x Leerenbasaltziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_brick_stairs", "copper_inferno:voidbasalt_brick_stairs", "nightslate/voidbasalt_brick_stairs",
				new String[] {"copper_inferno:voidbasalt_bricks", "", "", "copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "", "copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks"},
				"copper_inferno:voidbasalt_brick_stairs", 4, "Craft 4x Voidbasalt Brick Stairs at a crafting table.", "Stellt 4x Leerenbasaltziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_brick_wall", "copper_inferno:voidbasalt_brick_wall", "nightslate/voidbasalt_brick_wall",
				new String[] {"copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "copper_inferno:voidbasalt_bricks", "", "", ""},
				"copper_inferno:voidbasalt_brick_wall", 6, "Craft 6x Voidbasalt Brick Wall at a crafting table.", "Stellt 6x Leerenbasaltziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_voidbasalt_bricks", "copper_inferno:cracked_voidbasalt_bricks", "nightslate/cracked_voidbasalt_bricks",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt_bricks", "", "", "", ""},
				"copper_inferno:cracked_voidbasalt_bricks", 1, "Smelting Voidbasalt Bricks in a furnace yields Cracked Voidbasalt Bricks.", "Leerenbasaltziegel im Ofen gebrannt ergibt Rissige Leerenbasaltziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_voidbasalt_bricks", "copper_inferno:chiseled_voidbasalt_bricks", "nightslate/chiseled_voidbasalt_bricks",
				new String[] {"copper_inferno:voidbasalt_brick_slab", "", "", "copper_inferno:voidbasalt_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_voidbasalt_bricks", 1, "Craft 1x Chiseled Voidbasalt Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Leerenbasaltziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_pillar", "copper_inferno:voidbasalt_pillar", "nightslate/voidbasalt_pillar",
				new String[] {"copper_inferno:voidbasalt_bricks", "", "", "copper_inferno:voidbasalt_bricks", "", "", "", "", ""},
				"copper_inferno:voidbasalt_pillar", 2, "Craft 2x Voidbasalt Pillar at a crafting table.", "Stellt 2x Leerenbasalts\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_slab_from_voidbasalt_stonecutting", "copper_inferno:voidbasalt_slab", "nightslate/voidbasalt_slab_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_slab", 2, "Stonecutting: cut 2x Voidbasalt Slab from Voidbasalt.", "Steins\u00e4ge: 2x Leerenbasaltstufe aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_stairs_from_voidbasalt_stonecutting", "copper_inferno:voidbasalt_stairs", "nightslate/voidbasalt_stairs_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_stairs", 1, "Stonecutting: cut 1x Voidbasalt Stairs from Voidbasalt.", "Steins\u00e4ge: 1x Leerenbasalttreppe aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_wall_from_voidbasalt_stonecutting", "copper_inferno:voidbasalt_wall", "nightslate/voidbasalt_wall_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_wall", 1, "Stonecutting: cut 1x Voidbasalt Wall from Voidbasalt.", "Steins\u00e4ge: 1x Leerenbasaltmauer aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_voidbasalt_from_voidbasalt_stonecutting", "copper_inferno:polished_voidbasalt", "nightslate/polished_voidbasalt_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:polished_voidbasalt", 1, "Stonecutting: cut 1x Polished Voidbasalt from Voidbasalt.", "Steins\u00e4ge: 1x Polierten Leerenbasalt aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_voidbasalt_slab_from_voidbasalt_stonecutting", "copper_inferno:polished_voidbasalt_slab", "nightslate/polished_voidbasalt_slab_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:polished_voidbasalt_slab", 2, "Stonecutting: cut 2x Polished Voidbasalt Slab from Voidbasalt.", "Steins\u00e4ge: 2x Polierte Leerenbasaltstufe aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_voidbasalt_stairs_from_voidbasalt_stonecutting", "copper_inferno:polished_voidbasalt_stairs", "nightslate/polished_voidbasalt_stairs_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:polished_voidbasalt_stairs", 1, "Stonecutting: cut 1x Polished Voidbasalt Stairs from Voidbasalt.", "Steins\u00e4ge: 1x Polierte Leerenbasalttreppe aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_voidbasalt_wall_from_voidbasalt_stonecutting", "copper_inferno:polished_voidbasalt_wall", "nightslate/polished_voidbasalt_wall_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:polished_voidbasalt_wall", 1, "Stonecutting: cut 1x Polished Voidbasalt Wall from Voidbasalt.", "Steins\u00e4ge: 1x Polierte Leerenbasaltmauer aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_bricks_from_voidbasalt_stonecutting", "copper_inferno:voidbasalt_bricks", "nightslate/voidbasalt_bricks_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_bricks", 1, "Stonecutting: cut 1x Voidbasalt Bricks from Voidbasalt.", "Steins\u00e4ge: 1x Leerenbasaltziegel aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_brick_slab_from_voidbasalt_stonecutting", "copper_inferno:voidbasalt_brick_slab", "nightslate/voidbasalt_brick_slab_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_brick_slab", 2, "Stonecutting: cut 2x Voidbasalt Brick Slab from Voidbasalt.", "Steins\u00e4ge: 2x Leerenbasaltziegelstufe aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_brick_stairs_from_voidbasalt_stonecutting", "copper_inferno:voidbasalt_brick_stairs", "nightslate/voidbasalt_brick_stairs_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_brick_stairs", 1, "Stonecutting: cut 1x Voidbasalt Brick Stairs from Voidbasalt.", "Steins\u00e4ge: 1x Leerenbasaltziegeltreppe aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_brick_wall_from_voidbasalt_stonecutting", "copper_inferno:voidbasalt_brick_wall", "nightslate/voidbasalt_brick_wall_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_brick_wall", 1, "Stonecutting: cut 1x Voidbasalt Brick Wall from Voidbasalt.", "Steins\u00e4ge: 1x Leerenbasaltziegelmauer aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_tiles_from_voidbasalt_stonecutting", "copper_inferno:voidbasalt_tiles", "nightslate/voidbasalt_tiles_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_tiles", 1, "Stonecutting: cut 1x Voidbasalt Tiles from Voidbasalt.", "Steins\u00e4ge: 1x Leerenbasaltfliesen aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_voidbasalt_bricks_from_voidbasalt_stonecutting", "copper_inferno:chiseled_voidbasalt_bricks", "nightslate/chiseled_voidbasalt_bricks_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:chiseled_voidbasalt_bricks", 1, "Stonecutting: cut 1x Chiseled Voidbasalt Bricks from Voidbasalt.", "Steins\u00e4ge: 1x Gemei\u00dfelte Leerenbasaltziegel aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/voidbasalt_pillar_from_voidbasalt_stonecutting", "copper_inferno:voidbasalt_pillar", "nightslate/voidbasalt_pillar_from_voidbasalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:voidbasalt", "", "", "", ""},
				"copper_inferno:voidbasalt_pillar", 1, "Stonecutting: cut 1x Voidbasalt Pillar from Voidbasalt.", "Steins\u00e4ge: 1x Leerenbasalts\u00e4ule aus Leerenbasalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone", "copper_inferno:duskstone", "nightslate/duskstone",
				new String[] {"minecraft:smooth_basalt", "", "minecraft:smooth_basalt", "", "copper_inferno:voidbasalt", "", "minecraft:smooth_basalt", "", "minecraft:smooth_basalt"},
				"copper_inferno:duskstone", 4, "Craft 4x Duskstone at a crafting table.", "Stellt 4x D\u00e4mmerstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_bricks", "copper_inferno:duskstone_bricks", "nightslate/duskstone_bricks",
				new String[] {"copper_inferno:duskstone", "copper_inferno:duskstone", "", "copper_inferno:duskstone", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_bricks", 4, "Craft 4x Duskstone Bricks at a crafting table.", "Stellt 4x D\u00e4mmersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_tiles", "copper_inferno:duskstone_tiles", "nightslate/duskstone_tiles",
				new String[] {"copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "", "copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "", "", "", ""},
				"copper_inferno:duskstone_tiles", 4, "Craft 4x Duskstone Tiles at a crafting table.", "Stellt 4x D\u00e4mmersteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_duskstone", "copper_inferno:polished_duskstone", "nightslate/polished_duskstone",
				new String[] {"copper_inferno:duskstone_tiles", "copper_inferno:duskstone_tiles", "", "copper_inferno:duskstone_tiles", "copper_inferno:duskstone_tiles", "", "", "", ""},
				"copper_inferno:polished_duskstone", 4, "Craft 4x Polished Duskstone at a crafting table.", "Stellt 4x Polierten D\u00e4mmerstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_slab", "copper_inferno:duskstone_slab", "nightslate/duskstone_slab",
				new String[] {"copper_inferno:duskstone", "copper_inferno:duskstone", "copper_inferno:duskstone", "", "", "", "", "", ""},
				"copper_inferno:duskstone_slab", 6, "Craft 6x Duskstone Slab at a crafting table.", "Stellt 6x D\u00e4mmersteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_stairs", "copper_inferno:duskstone_stairs", "nightslate/duskstone_stairs",
				new String[] {"copper_inferno:duskstone", "", "", "copper_inferno:duskstone", "copper_inferno:duskstone", "", "copper_inferno:duskstone", "copper_inferno:duskstone", "copper_inferno:duskstone"},
				"copper_inferno:duskstone_stairs", 4, "Craft 4x Duskstone Stairs at a crafting table.", "Stellt 4x D\u00e4mmersteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_wall", "copper_inferno:duskstone_wall", "nightslate/duskstone_wall",
				new String[] {"copper_inferno:duskstone", "copper_inferno:duskstone", "copper_inferno:duskstone", "copper_inferno:duskstone", "copper_inferno:duskstone", "copper_inferno:duskstone", "", "", ""},
				"copper_inferno:duskstone_wall", 6, "Craft 6x Duskstone Wall at a crafting table.", "Stellt 6x D\u00e4mmersteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_duskstone_slab", "copper_inferno:polished_duskstone_slab", "nightslate/polished_duskstone_slab",
				new String[] {"copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone", "", "", "", "", "", ""},
				"copper_inferno:polished_duskstone_slab", 6, "Craft 6x Polished Duskstone Slab at a crafting table.", "Stellt 6x Polierte D\u00e4mmersteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_duskstone_stairs", "copper_inferno:polished_duskstone_stairs", "nightslate/polished_duskstone_stairs",
				new String[] {"copper_inferno:polished_duskstone", "", "", "copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone", "", "copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone"},
				"copper_inferno:polished_duskstone_stairs", 4, "Craft 4x Polished Duskstone Stairs at a crafting table.", "Stellt 4x Polierte D\u00e4mmersteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_duskstone_wall", "copper_inferno:polished_duskstone_wall", "nightslate/polished_duskstone_wall",
				new String[] {"copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone", "copper_inferno:polished_duskstone", "", "", ""},
				"copper_inferno:polished_duskstone_wall", 6, "Craft 6x Polished Duskstone Wall at a crafting table.", "Stellt 6x Polierte D\u00e4mmersteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_brick_slab", "copper_inferno:duskstone_brick_slab", "nightslate/duskstone_brick_slab",
				new String[] {"copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "", "", "", "", "", ""},
				"copper_inferno:duskstone_brick_slab", 6, "Craft 6x Duskstone Brick Slab at a crafting table.", "Stellt 6x D\u00e4mmersteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_brick_stairs", "copper_inferno:duskstone_brick_stairs", "nightslate/duskstone_brick_stairs",
				new String[] {"copper_inferno:duskstone_bricks", "", "", "copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "", "copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks"},
				"copper_inferno:duskstone_brick_stairs", 4, "Craft 4x Duskstone Brick Stairs at a crafting table.", "Stellt 4x D\u00e4mmersteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_brick_wall", "copper_inferno:duskstone_brick_wall", "nightslate/duskstone_brick_wall",
				new String[] {"copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "copper_inferno:duskstone_bricks", "", "", ""},
				"copper_inferno:duskstone_brick_wall", 6, "Craft 6x Duskstone Brick Wall at a crafting table.", "Stellt 6x D\u00e4mmersteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_duskstone_bricks", "copper_inferno:cracked_duskstone_bricks", "nightslate/cracked_duskstone_bricks",
				new String[] {"", "", "", "", "copper_inferno:duskstone_bricks", "", "", "", ""},
				"copper_inferno:cracked_duskstone_bricks", 1, "Smelting Duskstone Bricks in a furnace yields Cracked Duskstone Bricks.", "D\u00e4mmersteinziegel im Ofen gebrannt ergibt Rissige D\u00e4mmersteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_duskstone_bricks", "copper_inferno:chiseled_duskstone_bricks", "nightslate/chiseled_duskstone_bricks",
				new String[] {"copper_inferno:duskstone_brick_slab", "", "", "copper_inferno:duskstone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_duskstone_bricks", 1, "Craft 1x Chiseled Duskstone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte D\u00e4mmersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_pillar", "copper_inferno:duskstone_pillar", "nightslate/duskstone_pillar",
				new String[] {"copper_inferno:duskstone_bricks", "", "", "copper_inferno:duskstone_bricks", "", "", "", "", ""},
				"copper_inferno:duskstone_pillar", 2, "Craft 2x Duskstone Pillar at a crafting table.", "Stellt 2x D\u00e4mmersteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_slab_from_duskstone_stonecutting", "copper_inferno:duskstone_slab", "nightslate/duskstone_slab_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_slab", 2, "Stonecutting: cut 2x Duskstone Slab from Duskstone.", "Steins\u00e4ge: 2x D\u00e4mmersteinstufe aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_stairs_from_duskstone_stonecutting", "copper_inferno:duskstone_stairs", "nightslate/duskstone_stairs_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_stairs", 1, "Stonecutting: cut 1x Duskstone Stairs from Duskstone.", "Steins\u00e4ge: 1x D\u00e4mmersteintreppe aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_wall_from_duskstone_stonecutting", "copper_inferno:duskstone_wall", "nightslate/duskstone_wall_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_wall", 1, "Stonecutting: cut 1x Duskstone Wall from Duskstone.", "Steins\u00e4ge: 1x D\u00e4mmersteinmauer aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_duskstone_from_duskstone_stonecutting", "copper_inferno:polished_duskstone", "nightslate/polished_duskstone_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:polished_duskstone", 1, "Stonecutting: cut 1x Polished Duskstone from Duskstone.", "Steins\u00e4ge: 1x Polierten D\u00e4mmerstein aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_duskstone_slab_from_duskstone_stonecutting", "copper_inferno:polished_duskstone_slab", "nightslate/polished_duskstone_slab_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:polished_duskstone_slab", 2, "Stonecutting: cut 2x Polished Duskstone Slab from Duskstone.", "Steins\u00e4ge: 2x Polierte D\u00e4mmersteinstufe aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_duskstone_stairs_from_duskstone_stonecutting", "copper_inferno:polished_duskstone_stairs", "nightslate/polished_duskstone_stairs_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:polished_duskstone_stairs", 1, "Stonecutting: cut 1x Polished Duskstone Stairs from Duskstone.", "Steins\u00e4ge: 1x Polierte D\u00e4mmersteintreppe aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_duskstone_wall_from_duskstone_stonecutting", "copper_inferno:polished_duskstone_wall", "nightslate/polished_duskstone_wall_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:polished_duskstone_wall", 1, "Stonecutting: cut 1x Polished Duskstone Wall from Duskstone.", "Steins\u00e4ge: 1x Polierte D\u00e4mmersteinmauer aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_bricks_from_duskstone_stonecutting", "copper_inferno:duskstone_bricks", "nightslate/duskstone_bricks_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_bricks", 1, "Stonecutting: cut 1x Duskstone Bricks from Duskstone.", "Steins\u00e4ge: 1x D\u00e4mmersteinziegel aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_brick_slab_from_duskstone_stonecutting", "copper_inferno:duskstone_brick_slab", "nightslate/duskstone_brick_slab_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_brick_slab", 2, "Stonecutting: cut 2x Duskstone Brick Slab from Duskstone.", "Steins\u00e4ge: 2x D\u00e4mmersteinziegelstufe aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_brick_stairs_from_duskstone_stonecutting", "copper_inferno:duskstone_brick_stairs", "nightslate/duskstone_brick_stairs_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_brick_stairs", 1, "Stonecutting: cut 1x Duskstone Brick Stairs from Duskstone.", "Steins\u00e4ge: 1x D\u00e4mmersteinziegeltreppe aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_brick_wall_from_duskstone_stonecutting", "copper_inferno:duskstone_brick_wall", "nightslate/duskstone_brick_wall_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_brick_wall", 1, "Stonecutting: cut 1x Duskstone Brick Wall from Duskstone.", "Steins\u00e4ge: 1x D\u00e4mmersteinziegelmauer aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_tiles_from_duskstone_stonecutting", "copper_inferno:duskstone_tiles", "nightslate/duskstone_tiles_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_tiles", 1, "Stonecutting: cut 1x Duskstone Tiles from Duskstone.", "Steins\u00e4ge: 1x D\u00e4mmersteinfliesen aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_duskstone_bricks_from_duskstone_stonecutting", "copper_inferno:chiseled_duskstone_bricks", "nightslate/chiseled_duskstone_bricks_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:chiseled_duskstone_bricks", 1, "Stonecutting: cut 1x Chiseled Duskstone Bricks from Duskstone.", "Steins\u00e4ge: 1x Gemei\u00dfelte D\u00e4mmersteinziegel aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/duskstone_pillar_from_duskstone_stonecutting", "copper_inferno:duskstone_pillar", "nightslate/duskstone_pillar_from_duskstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:duskstone", "", "", "", ""},
				"copper_inferno:duskstone_pillar", 1, "Stonecutting: cut 1x Duskstone Pillar from Duskstone.", "Steins\u00e4ge: 1x D\u00e4mmersteins\u00e4ule aus D\u00e4mmerstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone", "copper_inferno:gloomstone", "nightslate/gloomstone",
				new String[] {"minecraft:blackstone", "", "minecraft:blackstone", "", "copper_inferno:duskstone", "", "minecraft:blackstone", "", "minecraft:blackstone"},
				"copper_inferno:gloomstone", 4, "Craft 4x Gloomstone at a crafting table.", "Stellt 4x D\u00fcsterstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_bricks", "copper_inferno:gloomstone_bricks", "nightslate/gloomstone_bricks",
				new String[] {"copper_inferno:gloomstone", "copper_inferno:gloomstone", "", "copper_inferno:gloomstone", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_bricks", 4, "Craft 4x Gloomstone Bricks at a crafting table.", "Stellt 4x D\u00fcstersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_tiles", "copper_inferno:gloomstone_tiles", "nightslate/gloomstone_tiles",
				new String[] {"copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "", "copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "", "", "", ""},
				"copper_inferno:gloomstone_tiles", 4, "Craft 4x Gloomstone Tiles at a crafting table.", "Stellt 4x D\u00fcstersteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_gloomstone", "copper_inferno:polished_gloomstone", "nightslate/polished_gloomstone",
				new String[] {"copper_inferno:gloomstone_tiles", "copper_inferno:gloomstone_tiles", "", "copper_inferno:gloomstone_tiles", "copper_inferno:gloomstone_tiles", "", "", "", ""},
				"copper_inferno:polished_gloomstone", 4, "Craft 4x Polished Gloomstone at a crafting table.", "Stellt 4x Polierten D\u00fcsterstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_slab", "copper_inferno:gloomstone_slab", "nightslate/gloomstone_slab",
				new String[] {"copper_inferno:gloomstone", "copper_inferno:gloomstone", "copper_inferno:gloomstone", "", "", "", "", "", ""},
				"copper_inferno:gloomstone_slab", 6, "Craft 6x Gloomstone Slab at a crafting table.", "Stellt 6x D\u00fcstersteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_stairs", "copper_inferno:gloomstone_stairs", "nightslate/gloomstone_stairs",
				new String[] {"copper_inferno:gloomstone", "", "", "copper_inferno:gloomstone", "copper_inferno:gloomstone", "", "copper_inferno:gloomstone", "copper_inferno:gloomstone", "copper_inferno:gloomstone"},
				"copper_inferno:gloomstone_stairs", 4, "Craft 4x Gloomstone Stairs at a crafting table.", "Stellt 4x D\u00fcstersteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_wall", "copper_inferno:gloomstone_wall", "nightslate/gloomstone_wall",
				new String[] {"copper_inferno:gloomstone", "copper_inferno:gloomstone", "copper_inferno:gloomstone", "copper_inferno:gloomstone", "copper_inferno:gloomstone", "copper_inferno:gloomstone", "", "", ""},
				"copper_inferno:gloomstone_wall", 6, "Craft 6x Gloomstone Wall at a crafting table.", "Stellt 6x D\u00fcstersteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_gloomstone_slab", "copper_inferno:polished_gloomstone_slab", "nightslate/polished_gloomstone_slab",
				new String[] {"copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone", "", "", "", "", "", ""},
				"copper_inferno:polished_gloomstone_slab", 6, "Craft 6x Polished Gloomstone Slab at a crafting table.", "Stellt 6x Polierte D\u00fcstersteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_gloomstone_stairs", "copper_inferno:polished_gloomstone_stairs", "nightslate/polished_gloomstone_stairs",
				new String[] {"copper_inferno:polished_gloomstone", "", "", "copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone", "", "copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone"},
				"copper_inferno:polished_gloomstone_stairs", 4, "Craft 4x Polished Gloomstone Stairs at a crafting table.", "Stellt 4x Polierte D\u00fcstersteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_gloomstone_wall", "copper_inferno:polished_gloomstone_wall", "nightslate/polished_gloomstone_wall",
				new String[] {"copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone", "copper_inferno:polished_gloomstone", "", "", ""},
				"copper_inferno:polished_gloomstone_wall", 6, "Craft 6x Polished Gloomstone Wall at a crafting table.", "Stellt 6x Polierte D\u00fcstersteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_brick_slab", "copper_inferno:gloomstone_brick_slab", "nightslate/gloomstone_brick_slab",
				new String[] {"copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "", "", "", "", "", ""},
				"copper_inferno:gloomstone_brick_slab", 6, "Craft 6x Gloomstone Brick Slab at a crafting table.", "Stellt 6x D\u00fcstersteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_brick_stairs", "copper_inferno:gloomstone_brick_stairs", "nightslate/gloomstone_brick_stairs",
				new String[] {"copper_inferno:gloomstone_bricks", "", "", "copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "", "copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks"},
				"copper_inferno:gloomstone_brick_stairs", 4, "Craft 4x Gloomstone Brick Stairs at a crafting table.", "Stellt 4x D\u00fcstersteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_brick_wall", "copper_inferno:gloomstone_brick_wall", "nightslate/gloomstone_brick_wall",
				new String[] {"copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "copper_inferno:gloomstone_bricks", "", "", ""},
				"copper_inferno:gloomstone_brick_wall", 6, "Craft 6x Gloomstone Brick Wall at a crafting table.", "Stellt 6x D\u00fcstersteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_gloomstone_bricks", "copper_inferno:cracked_gloomstone_bricks", "nightslate/cracked_gloomstone_bricks",
				new String[] {"", "", "", "", "copper_inferno:gloomstone_bricks", "", "", "", ""},
				"copper_inferno:cracked_gloomstone_bricks", 1, "Smelting Gloomstone Bricks in a furnace yields Cracked Gloomstone Bricks.", "D\u00fcstersteinziegel im Ofen gebrannt ergibt Rissige D\u00fcstersteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_gloomstone_bricks", "copper_inferno:chiseled_gloomstone_bricks", "nightslate/chiseled_gloomstone_bricks",
				new String[] {"copper_inferno:gloomstone_brick_slab", "", "", "copper_inferno:gloomstone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_gloomstone_bricks", 1, "Craft 1x Chiseled Gloomstone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte D\u00fcstersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_pillar", "copper_inferno:gloomstone_pillar", "nightslate/gloomstone_pillar",
				new String[] {"copper_inferno:gloomstone_bricks", "", "", "copper_inferno:gloomstone_bricks", "", "", "", "", ""},
				"copper_inferno:gloomstone_pillar", 2, "Craft 2x Gloomstone Pillar at a crafting table.", "Stellt 2x D\u00fcstersteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_slab_from_gloomstone_stonecutting", "copper_inferno:gloomstone_slab", "nightslate/gloomstone_slab_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_slab", 2, "Stonecutting: cut 2x Gloomstone Slab from Gloomstone.", "Steins\u00e4ge: 2x D\u00fcstersteinstufe aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_stairs_from_gloomstone_stonecutting", "copper_inferno:gloomstone_stairs", "nightslate/gloomstone_stairs_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_stairs", 1, "Stonecutting: cut 1x Gloomstone Stairs from Gloomstone.", "Steins\u00e4ge: 1x D\u00fcstersteintreppe aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_wall_from_gloomstone_stonecutting", "copper_inferno:gloomstone_wall", "nightslate/gloomstone_wall_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_wall", 1, "Stonecutting: cut 1x Gloomstone Wall from Gloomstone.", "Steins\u00e4ge: 1x D\u00fcstersteinmauer aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_gloomstone_from_gloomstone_stonecutting", "copper_inferno:polished_gloomstone", "nightslate/polished_gloomstone_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:polished_gloomstone", 1, "Stonecutting: cut 1x Polished Gloomstone from Gloomstone.", "Steins\u00e4ge: 1x Polierten D\u00fcsterstein aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_gloomstone_slab_from_gloomstone_stonecutting", "copper_inferno:polished_gloomstone_slab", "nightslate/polished_gloomstone_slab_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:polished_gloomstone_slab", 2, "Stonecutting: cut 2x Polished Gloomstone Slab from Gloomstone.", "Steins\u00e4ge: 2x Polierte D\u00fcstersteinstufe aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_gloomstone_stairs_from_gloomstone_stonecutting", "copper_inferno:polished_gloomstone_stairs", "nightslate/polished_gloomstone_stairs_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:polished_gloomstone_stairs", 1, "Stonecutting: cut 1x Polished Gloomstone Stairs from Gloomstone.", "Steins\u00e4ge: 1x Polierte D\u00fcstersteintreppe aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_gloomstone_wall_from_gloomstone_stonecutting", "copper_inferno:polished_gloomstone_wall", "nightslate/polished_gloomstone_wall_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:polished_gloomstone_wall", 1, "Stonecutting: cut 1x Polished Gloomstone Wall from Gloomstone.", "Steins\u00e4ge: 1x Polierte D\u00fcstersteinmauer aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_bricks_from_gloomstone_stonecutting", "copper_inferno:gloomstone_bricks", "nightslate/gloomstone_bricks_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_bricks", 1, "Stonecutting: cut 1x Gloomstone Bricks from Gloomstone.", "Steins\u00e4ge: 1x D\u00fcstersteinziegel aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_brick_slab_from_gloomstone_stonecutting", "copper_inferno:gloomstone_brick_slab", "nightslate/gloomstone_brick_slab_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_brick_slab", 2, "Stonecutting: cut 2x Gloomstone Brick Slab from Gloomstone.", "Steins\u00e4ge: 2x D\u00fcstersteinziegelstufe aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_brick_stairs_from_gloomstone_stonecutting", "copper_inferno:gloomstone_brick_stairs", "nightslate/gloomstone_brick_stairs_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_brick_stairs", 1, "Stonecutting: cut 1x Gloomstone Brick Stairs from Gloomstone.", "Steins\u00e4ge: 1x D\u00fcstersteinziegeltreppe aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_brick_wall_from_gloomstone_stonecutting", "copper_inferno:gloomstone_brick_wall", "nightslate/gloomstone_brick_wall_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_brick_wall", 1, "Stonecutting: cut 1x Gloomstone Brick Wall from Gloomstone.", "Steins\u00e4ge: 1x D\u00fcstersteinziegelmauer aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_tiles_from_gloomstone_stonecutting", "copper_inferno:gloomstone_tiles", "nightslate/gloomstone_tiles_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_tiles", 1, "Stonecutting: cut 1x Gloomstone Tiles from Gloomstone.", "Steins\u00e4ge: 1x D\u00fcstersteinfliesen aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_gloomstone_bricks_from_gloomstone_stonecutting", "copper_inferno:chiseled_gloomstone_bricks", "nightslate/chiseled_gloomstone_bricks_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:chiseled_gloomstone_bricks", 1, "Stonecutting: cut 1x Chiseled Gloomstone Bricks from Gloomstone.", "Steins\u00e4ge: 1x Gemei\u00dfelte D\u00fcstersteinziegel aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/gloomstone_pillar_from_gloomstone_stonecutting", "copper_inferno:gloomstone_pillar", "nightslate/gloomstone_pillar_from_gloomstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:gloomstone", "", "", "", ""},
				"copper_inferno:gloomstone_pillar", 1, "Stonecutting: cut 1x Gloomstone Pillar from Gloomstone.", "Steins\u00e4ge: 1x D\u00fcstersteins\u00e4ule aus D\u00fcsterstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff", "copper_inferno:shadow_tuff", "nightslate/shadow_tuff",
				new String[] {"minecraft:tuff", "", "minecraft:tuff", "", "copper_inferno:gloomstone", "", "minecraft:tuff", "", "minecraft:tuff"},
				"copper_inferno:shadow_tuff", 4, "Craft 4x Shadow Tuff at a crafting table.", "Stellt 4x Schattentuff an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "nightslate/shadow_tuff_bricks",
				new String[] {"copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "", "copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_bricks", 4, "Craft 4x Shadow Tuff Bricks at a crafting table.", "Stellt 4x Schattentuffziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_tiles", "copper_inferno:shadow_tuff_tiles", "nightslate/shadow_tuff_tiles",
				new String[] {"copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "", "copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "", "", "", ""},
				"copper_inferno:shadow_tuff_tiles", 4, "Craft 4x Shadow Tuff Tiles at a crafting table.", "Stellt 4x Schattentufffliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "nightslate/polished_shadow_tuff",
				new String[] {"copper_inferno:shadow_tuff_tiles", "copper_inferno:shadow_tuff_tiles", "", "copper_inferno:shadow_tuff_tiles", "copper_inferno:shadow_tuff_tiles", "", "", "", ""},
				"copper_inferno:polished_shadow_tuff", 4, "Craft 4x Polished Shadow Tuff at a crafting table.", "Stellt 4x Polierten Schattentuff an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_slab", "copper_inferno:shadow_tuff_slab", "nightslate/shadow_tuff_slab",
				new String[] {"copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "", "", "", "", "", ""},
				"copper_inferno:shadow_tuff_slab", 6, "Craft 6x Shadow Tuff Slab at a crafting table.", "Stellt 6x Schattentuffstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_stairs", "copper_inferno:shadow_tuff_stairs", "nightslate/shadow_tuff_stairs",
				new String[] {"copper_inferno:shadow_tuff", "", "", "copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "", "copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff"},
				"copper_inferno:shadow_tuff_stairs", 4, "Craft 4x Shadow Tuff Stairs at a crafting table.", "Stellt 4x Schattentufftreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_wall", "copper_inferno:shadow_tuff_wall", "nightslate/shadow_tuff_wall",
				new String[] {"copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "copper_inferno:shadow_tuff", "", "", ""},
				"copper_inferno:shadow_tuff_wall", 6, "Craft 6x Shadow Tuff Wall at a crafting table.", "Stellt 6x Schattentuffmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_shadow_tuff_slab", "copper_inferno:polished_shadow_tuff_slab", "nightslate/polished_shadow_tuff_slab",
				new String[] {"copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "", "", "", "", "", ""},
				"copper_inferno:polished_shadow_tuff_slab", 6, "Craft 6x Polished Shadow Tuff Slab at a crafting table.", "Stellt 6x Polierte Schattentuffstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_shadow_tuff_stairs", "copper_inferno:polished_shadow_tuff_stairs", "nightslate/polished_shadow_tuff_stairs",
				new String[] {"copper_inferno:polished_shadow_tuff", "", "", "copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "", "copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff"},
				"copper_inferno:polished_shadow_tuff_stairs", 4, "Craft 4x Polished Shadow Tuff Stairs at a crafting table.", "Stellt 4x Polierte Schattentufftreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_shadow_tuff_wall", "copper_inferno:polished_shadow_tuff_wall", "nightslate/polished_shadow_tuff_wall",
				new String[] {"copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "copper_inferno:polished_shadow_tuff", "", "", ""},
				"copper_inferno:polished_shadow_tuff_wall", 6, "Craft 6x Polished Shadow Tuff Wall at a crafting table.", "Stellt 6x Polierte Schattentuffmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_brick_slab", "copper_inferno:shadow_tuff_brick_slab", "nightslate/shadow_tuff_brick_slab",
				new String[] {"copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "", "", "", "", "", ""},
				"copper_inferno:shadow_tuff_brick_slab", 6, "Craft 6x Shadow Tuff Brick Slab at a crafting table.", "Stellt 6x Schattentuffziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_brick_stairs", "copper_inferno:shadow_tuff_brick_stairs", "nightslate/shadow_tuff_brick_stairs",
				new String[] {"copper_inferno:shadow_tuff_bricks", "", "", "copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "", "copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks"},
				"copper_inferno:shadow_tuff_brick_stairs", 4, "Craft 4x Shadow Tuff Brick Stairs at a crafting table.", "Stellt 4x Schattentuffziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_brick_wall", "copper_inferno:shadow_tuff_brick_wall", "nightslate/shadow_tuff_brick_wall",
				new String[] {"copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "copper_inferno:shadow_tuff_bricks", "", "", ""},
				"copper_inferno:shadow_tuff_brick_wall", 6, "Craft 6x Shadow Tuff Brick Wall at a crafting table.", "Stellt 6x Schattentuffziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_shadow_tuff_bricks", "copper_inferno:cracked_shadow_tuff_bricks", "nightslate/cracked_shadow_tuff_bricks",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff_bricks", "", "", "", ""},
				"copper_inferno:cracked_shadow_tuff_bricks", 1, "Smelting Shadow Tuff Bricks in a furnace yields Cracked Shadow Tuff Bricks.", "Schattentuffziegel im Ofen gebrannt ergibt Rissige Schattentuffziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_shadow_tuff_bricks", "copper_inferno:chiseled_shadow_tuff_bricks", "nightslate/chiseled_shadow_tuff_bricks",
				new String[] {"copper_inferno:shadow_tuff_brick_slab", "", "", "copper_inferno:shadow_tuff_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_shadow_tuff_bricks", 1, "Craft 1x Chiseled Shadow Tuff Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schattentuffziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_pillar", "copper_inferno:shadow_tuff_pillar", "nightslate/shadow_tuff_pillar",
				new String[] {"copper_inferno:shadow_tuff_bricks", "", "", "copper_inferno:shadow_tuff_bricks", "", "", "", "", ""},
				"copper_inferno:shadow_tuff_pillar", 2, "Craft 2x Shadow Tuff Pillar at a crafting table.", "Stellt 2x Schattentuffs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_slab_from_shadow_tuff_stonecutting", "copper_inferno:shadow_tuff_slab", "nightslate/shadow_tuff_slab_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_slab", 2, "Stonecutting: cut 2x Shadow Tuff Slab from Shadow Tuff.", "Steins\u00e4ge: 2x Schattentuffstufe aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_stairs_from_shadow_tuff_stonecutting", "copper_inferno:shadow_tuff_stairs", "nightslate/shadow_tuff_stairs_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_stairs", 1, "Stonecutting: cut 1x Shadow Tuff Stairs from Shadow Tuff.", "Steins\u00e4ge: 1x Schattentufftreppe aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_wall_from_shadow_tuff_stonecutting", "copper_inferno:shadow_tuff_wall", "nightslate/shadow_tuff_wall_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_wall", 1, "Stonecutting: cut 1x Shadow Tuff Wall from Shadow Tuff.", "Steins\u00e4ge: 1x Schattentuffmauer aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_shadow_tuff_from_shadow_tuff_stonecutting", "copper_inferno:polished_shadow_tuff", "nightslate/polished_shadow_tuff_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:polished_shadow_tuff", 1, "Stonecutting: cut 1x Polished Shadow Tuff from Shadow Tuff.", "Steins\u00e4ge: 1x Polierten Schattentuff aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_shadow_tuff_slab_from_shadow_tuff_stonecutting", "copper_inferno:polished_shadow_tuff_slab", "nightslate/polished_shadow_tuff_slab_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:polished_shadow_tuff_slab", 2, "Stonecutting: cut 2x Polished Shadow Tuff Slab from Shadow Tuff.", "Steins\u00e4ge: 2x Polierte Schattentuffstufe aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_shadow_tuff_stairs_from_shadow_tuff_stonecutting", "copper_inferno:polished_shadow_tuff_stairs", "nightslate/polished_shadow_tuff_stairs_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:polished_shadow_tuff_stairs", 1, "Stonecutting: cut 1x Polished Shadow Tuff Stairs from Shadow Tuff.", "Steins\u00e4ge: 1x Polierte Schattentufftreppe aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_shadow_tuff_wall_from_shadow_tuff_stonecutting", "copper_inferno:polished_shadow_tuff_wall", "nightslate/polished_shadow_tuff_wall_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:polished_shadow_tuff_wall", 1, "Stonecutting: cut 1x Polished Shadow Tuff Wall from Shadow Tuff.", "Steins\u00e4ge: 1x Polierte Schattentuffmauer aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_bricks_from_shadow_tuff_stonecutting", "copper_inferno:shadow_tuff_bricks", "nightslate/shadow_tuff_bricks_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_bricks", 1, "Stonecutting: cut 1x Shadow Tuff Bricks from Shadow Tuff.", "Steins\u00e4ge: 1x Schattentuffziegel aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_brick_slab_from_shadow_tuff_stonecutting", "copper_inferno:shadow_tuff_brick_slab", "nightslate/shadow_tuff_brick_slab_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_brick_slab", 2, "Stonecutting: cut 2x Shadow Tuff Brick Slab from Shadow Tuff.", "Steins\u00e4ge: 2x Schattentuffziegelstufe aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_brick_stairs_from_shadow_tuff_stonecutting", "copper_inferno:shadow_tuff_brick_stairs", "nightslate/shadow_tuff_brick_stairs_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_brick_stairs", 1, "Stonecutting: cut 1x Shadow Tuff Brick Stairs from Shadow Tuff.", "Steins\u00e4ge: 1x Schattentuffziegeltreppe aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_brick_wall_from_shadow_tuff_stonecutting", "copper_inferno:shadow_tuff_brick_wall", "nightslate/shadow_tuff_brick_wall_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_brick_wall", 1, "Stonecutting: cut 1x Shadow Tuff Brick Wall from Shadow Tuff.", "Steins\u00e4ge: 1x Schattentuffziegelmauer aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_tiles_from_shadow_tuff_stonecutting", "copper_inferno:shadow_tuff_tiles", "nightslate/shadow_tuff_tiles_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_tiles", 1, "Stonecutting: cut 1x Shadow Tuff Tiles from Shadow Tuff.", "Steins\u00e4ge: 1x Schattentufffliesen aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_shadow_tuff_bricks_from_shadow_tuff_stonecutting", "copper_inferno:chiseled_shadow_tuff_bricks", "nightslate/chiseled_shadow_tuff_bricks_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:chiseled_shadow_tuff_bricks", 1, "Stonecutting: cut 1x Chiseled Shadow Tuff Bricks from Shadow Tuff.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schattentuffziegel aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/shadow_tuff_pillar_from_shadow_tuff_stonecutting", "copper_inferno:shadow_tuff_pillar", "nightslate/shadow_tuff_pillar_from_shadow_tuff_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:shadow_tuff", "", "", "", ""},
				"copper_inferno:shadow_tuff_pillar", 1, "Stonecutting: cut 1x Shadow Tuff Pillar from Shadow Tuff.", "Steins\u00e4ge: 1x Schattentuffs\u00e4ule aus Schattentuff schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate", "copper_inferno:umbral_deepslate", "nightslate/umbral_deepslate",
				new String[] {"minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "", "copper_inferno:shadow_tuff", "", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate"},
				"copper_inferno:umbral_deepslate", 4, "Craft 4x Umbral Deepslate at a crafting table.", "Stellt 4x Umbra-Tiefenschiefer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "nightslate/umbral_deepslate_bricks",
				new String[] {"copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "", "copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_bricks", 4, "Craft 4x Umbral Deepslate Bricks at a crafting table.", "Stellt 4x Umbra-Tiefenschieferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_tiles", "copper_inferno:umbral_deepslate_tiles", "nightslate/umbral_deepslate_tiles",
				new String[] {"copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "", "copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "", "", "", ""},
				"copper_inferno:umbral_deepslate_tiles", 4, "Craft 4x Umbral Deepslate Tiles at a crafting table.", "Stellt 4x Umbra-Tiefenschieferfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "nightslate/polished_umbral_deepslate",
				new String[] {"copper_inferno:umbral_deepslate_tiles", "copper_inferno:umbral_deepslate_tiles", "", "copper_inferno:umbral_deepslate_tiles", "copper_inferno:umbral_deepslate_tiles", "", "", "", ""},
				"copper_inferno:polished_umbral_deepslate", 4, "Craft 4x Polished Umbral Deepslate at a crafting table.", "Stellt 4x Polierten Umbra-Tiefenschiefer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_slab", "copper_inferno:umbral_deepslate_slab", "nightslate/umbral_deepslate_slab",
				new String[] {"copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "", "", "", "", "", ""},
				"copper_inferno:umbral_deepslate_slab", 6, "Craft 6x Umbral Deepslate Slab at a crafting table.", "Stellt 6x Umbra-Tiefenschieferstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_stairs", "copper_inferno:umbral_deepslate_stairs", "nightslate/umbral_deepslate_stairs",
				new String[] {"copper_inferno:umbral_deepslate", "", "", "copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "", "copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate"},
				"copper_inferno:umbral_deepslate_stairs", 4, "Craft 4x Umbral Deepslate Stairs at a crafting table.", "Stellt 4x Umbra-Tiefenschiefertreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_wall", "copper_inferno:umbral_deepslate_wall", "nightslate/umbral_deepslate_wall",
				new String[] {"copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "copper_inferno:umbral_deepslate", "", "", ""},
				"copper_inferno:umbral_deepslate_wall", 6, "Craft 6x Umbral Deepslate Wall at a crafting table.", "Stellt 6x Umbra-Tiefenschiefermauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_umbral_deepslate_slab", "copper_inferno:polished_umbral_deepslate_slab", "nightslate/polished_umbral_deepslate_slab",
				new String[] {"copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "", "", "", "", "", ""},
				"copper_inferno:polished_umbral_deepslate_slab", 6, "Craft 6x Polished Umbral Deepslate Slab at a crafting table.", "Stellt 6x Polierte Umbra-Tiefenschieferstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_umbral_deepslate_stairs", "copper_inferno:polished_umbral_deepslate_stairs", "nightslate/polished_umbral_deepslate_stairs",
				new String[] {"copper_inferno:polished_umbral_deepslate", "", "", "copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "", "copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate"},
				"copper_inferno:polished_umbral_deepslate_stairs", 4, "Craft 4x Polished Umbral Deepslate Stairs at a crafting table.", "Stellt 4x Polierte Umbra-Tiefenschiefertreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_umbral_deepslate_wall", "copper_inferno:polished_umbral_deepslate_wall", "nightslate/polished_umbral_deepslate_wall",
				new String[] {"copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "copper_inferno:polished_umbral_deepslate", "", "", ""},
				"copper_inferno:polished_umbral_deepslate_wall", 6, "Craft 6x Polished Umbral Deepslate Wall at a crafting table.", "Stellt 6x Polierte Umbra-Tiefenschiefermauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_brick_slab", "copper_inferno:umbral_deepslate_brick_slab", "nightslate/umbral_deepslate_brick_slab",
				new String[] {"copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "", "", "", "", "", ""},
				"copper_inferno:umbral_deepslate_brick_slab", 6, "Craft 6x Umbral Deepslate Brick Slab at a crafting table.", "Stellt 6x Umbra-Tiefenschieferziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_brick_stairs", "copper_inferno:umbral_deepslate_brick_stairs", "nightslate/umbral_deepslate_brick_stairs",
				new String[] {"copper_inferno:umbral_deepslate_bricks", "", "", "copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "", "copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks"},
				"copper_inferno:umbral_deepslate_brick_stairs", 4, "Craft 4x Umbral Deepslate Brick Stairs at a crafting table.", "Stellt 4x Umbra-Tiefenschieferziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_brick_wall", "copper_inferno:umbral_deepslate_brick_wall", "nightslate/umbral_deepslate_brick_wall",
				new String[] {"copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "copper_inferno:umbral_deepslate_bricks", "", "", ""},
				"copper_inferno:umbral_deepslate_brick_wall", 6, "Craft 6x Umbral Deepslate Brick Wall at a crafting table.", "Stellt 6x Umbra-Tiefenschieferziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_umbral_deepslate_bricks", "copper_inferno:cracked_umbral_deepslate_bricks", "nightslate/cracked_umbral_deepslate_bricks",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate_bricks", "", "", "", ""},
				"copper_inferno:cracked_umbral_deepslate_bricks", 1, "Smelting Umbral Deepslate Bricks in a furnace yields Cracked Umbral Deepslate Bricks.", "Umbra-Tiefenschieferziegel im Ofen gebrannt ergibt Rissige Umbra-Tiefenschieferziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_umbral_deepslate_bricks", "copper_inferno:chiseled_umbral_deepslate_bricks", "nightslate/chiseled_umbral_deepslate_bricks",
				new String[] {"copper_inferno:umbral_deepslate_brick_slab", "", "", "copper_inferno:umbral_deepslate_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_umbral_deepslate_bricks", 1, "Craft 1x Chiseled Umbral Deepslate Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Umbra-Tiefenschieferziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_pillar", "copper_inferno:umbral_deepslate_pillar", "nightslate/umbral_deepslate_pillar",
				new String[] {"copper_inferno:umbral_deepslate_bricks", "", "", "copper_inferno:umbral_deepslate_bricks", "", "", "", "", ""},
				"copper_inferno:umbral_deepslate_pillar", 2, "Craft 2x Umbral Deepslate Pillar at a crafting table.", "Stellt 2x Umbra-Tiefenschiefers\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_slab_from_umbral_deepslate_stonecutting", "copper_inferno:umbral_deepslate_slab", "nightslate/umbral_deepslate_slab_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_slab", 2, "Stonecutting: cut 2x Umbral Deepslate Slab from Umbral Deepslate.", "Steins\u00e4ge: 2x Umbra-Tiefenschieferstufe aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_stairs_from_umbral_deepslate_stonecutting", "copper_inferno:umbral_deepslate_stairs", "nightslate/umbral_deepslate_stairs_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_stairs", 1, "Stonecutting: cut 1x Umbral Deepslate Stairs from Umbral Deepslate.", "Steins\u00e4ge: 1x Umbra-Tiefenschiefertreppe aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_wall_from_umbral_deepslate_stonecutting", "copper_inferno:umbral_deepslate_wall", "nightslate/umbral_deepslate_wall_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_wall", 1, "Stonecutting: cut 1x Umbral Deepslate Wall from Umbral Deepslate.", "Steins\u00e4ge: 1x Umbra-Tiefenschiefermauer aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_umbral_deepslate_from_umbral_deepslate_stonecutting", "copper_inferno:polished_umbral_deepslate", "nightslate/polished_umbral_deepslate_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:polished_umbral_deepslate", 1, "Stonecutting: cut 1x Polished Umbral Deepslate from Umbral Deepslate.", "Steins\u00e4ge: 1x Polierten Umbra-Tiefenschiefer aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_umbral_deepslate_slab_from_umbral_deepslate_stonecutting", "copper_inferno:polished_umbral_deepslate_slab", "nightslate/polished_umbral_deepslate_slab_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:polished_umbral_deepslate_slab", 2, "Stonecutting: cut 2x Polished Umbral Deepslate Slab from Umbral Deepslate.", "Steins\u00e4ge: 2x Polierte Umbra-Tiefenschieferstufe aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_umbral_deepslate_stairs_from_umbral_deepslate_stonecutting", "copper_inferno:polished_umbral_deepslate_stairs", "nightslate/polished_umbral_deepslate_stairs_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:polished_umbral_deepslate_stairs", 1, "Stonecutting: cut 1x Polished Umbral Deepslate Stairs from Umbral Deepslate.", "Steins\u00e4ge: 1x Polierte Umbra-Tiefenschiefertreppe aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_umbral_deepslate_wall_from_umbral_deepslate_stonecutting", "copper_inferno:polished_umbral_deepslate_wall", "nightslate/polished_umbral_deepslate_wall_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:polished_umbral_deepslate_wall", 1, "Stonecutting: cut 1x Polished Umbral Deepslate Wall from Umbral Deepslate.", "Steins\u00e4ge: 1x Polierte Umbra-Tiefenschiefermauer aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_bricks_from_umbral_deepslate_stonecutting", "copper_inferno:umbral_deepslate_bricks", "nightslate/umbral_deepslate_bricks_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_bricks", 1, "Stonecutting: cut 1x Umbral Deepslate Bricks from Umbral Deepslate.", "Steins\u00e4ge: 1x Umbra-Tiefenschieferziegel aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_brick_slab_from_umbral_deepslate_stonecutting", "copper_inferno:umbral_deepslate_brick_slab", "nightslate/umbral_deepslate_brick_slab_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_brick_slab", 2, "Stonecutting: cut 2x Umbral Deepslate Brick Slab from Umbral Deepslate.", "Steins\u00e4ge: 2x Umbra-Tiefenschieferziegelstufe aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_brick_stairs_from_umbral_deepslate_stonecutting", "copper_inferno:umbral_deepslate_brick_stairs", "nightslate/umbral_deepslate_brick_stairs_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_brick_stairs", 1, "Stonecutting: cut 1x Umbral Deepslate Brick Stairs from Umbral Deepslate.", "Steins\u00e4ge: 1x Umbra-Tiefenschieferziegeltreppe aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_brick_wall_from_umbral_deepslate_stonecutting", "copper_inferno:umbral_deepslate_brick_wall", "nightslate/umbral_deepslate_brick_wall_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_brick_wall", 1, "Stonecutting: cut 1x Umbral Deepslate Brick Wall from Umbral Deepslate.", "Steins\u00e4ge: 1x Umbra-Tiefenschieferziegelmauer aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_tiles_from_umbral_deepslate_stonecutting", "copper_inferno:umbral_deepslate_tiles", "nightslate/umbral_deepslate_tiles_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_tiles", 1, "Stonecutting: cut 1x Umbral Deepslate Tiles from Umbral Deepslate.", "Steins\u00e4ge: 1x Umbra-Tiefenschieferfliesen aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_umbral_deepslate_bricks_from_umbral_deepslate_stonecutting", "copper_inferno:chiseled_umbral_deepslate_bricks", "nightslate/chiseled_umbral_deepslate_bricks_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:chiseled_umbral_deepslate_bricks", 1, "Stonecutting: cut 1x Chiseled Umbral Deepslate Bricks from Umbral Deepslate.", "Steins\u00e4ge: 1x Gemei\u00dfelte Umbra-Tiefenschieferziegel aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/umbral_deepslate_pillar_from_umbral_deepslate_stonecutting", "copper_inferno:umbral_deepslate_pillar", "nightslate/umbral_deepslate_pillar_from_umbral_deepslate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:umbral_deepslate", "", "", "", ""},
				"copper_inferno:umbral_deepslate_pillar", 1, "Stonecutting: cut 1x Umbral Deepslate Pillar from Umbral Deepslate.", "Steins\u00e4ge: 1x Umbra-Tiefenschiefers\u00e4ule aus Umbra-Tiefenschiefer schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone", "copper_inferno:blacksoot_stone", "nightslate/blacksoot_stone",
				new String[] {"minecraft:charcoal", "", "minecraft:charcoal", "", "copper_inferno:umbral_deepslate", "", "minecraft:charcoal", "", "minecraft:charcoal"},
				"copper_inferno:blacksoot_stone", 4, "Craft 4x Blacksoot Stone at a crafting table.", "Stellt 4x Schwarzru\u00dfstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "nightslate/blacksoot_stone_bricks",
				new String[] {"copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "", "copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_bricks", 4, "Craft 4x Blacksoot Stone Bricks at a crafting table.", "Stellt 4x Schwarzru\u00dfsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_tiles", "copper_inferno:blacksoot_stone_tiles", "nightslate/blacksoot_stone_tiles",
				new String[] {"copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "", "copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "", "", "", ""},
				"copper_inferno:blacksoot_stone_tiles", 4, "Craft 4x Blacksoot Stone Tiles at a crafting table.", "Stellt 4x Schwarzru\u00dfsteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "nightslate/polished_blacksoot_stone",
				new String[] {"copper_inferno:blacksoot_stone_tiles", "copper_inferno:blacksoot_stone_tiles", "", "copper_inferno:blacksoot_stone_tiles", "copper_inferno:blacksoot_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_blacksoot_stone", 4, "Craft 4x Polished Blacksoot Stone at a crafting table.", "Stellt 4x Polierten Schwarzru\u00dfstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_slab", "copper_inferno:blacksoot_stone_slab", "nightslate/blacksoot_stone_slab",
				new String[] {"copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "", "", "", "", "", ""},
				"copper_inferno:blacksoot_stone_slab", 6, "Craft 6x Blacksoot Stone Slab at a crafting table.", "Stellt 6x Schwarzru\u00dfsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_stairs", "copper_inferno:blacksoot_stone_stairs", "nightslate/blacksoot_stone_stairs",
				new String[] {"copper_inferno:blacksoot_stone", "", "", "copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "", "copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone"},
				"copper_inferno:blacksoot_stone_stairs", 4, "Craft 4x Blacksoot Stone Stairs at a crafting table.", "Stellt 4x Schwarzru\u00dfsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_wall", "copper_inferno:blacksoot_stone_wall", "nightslate/blacksoot_stone_wall",
				new String[] {"copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "copper_inferno:blacksoot_stone", "", "", ""},
				"copper_inferno:blacksoot_stone_wall", 6, "Craft 6x Blacksoot Stone Wall at a crafting table.", "Stellt 6x Schwarzru\u00dfsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_blacksoot_stone_slab", "copper_inferno:polished_blacksoot_stone_slab", "nightslate/polished_blacksoot_stone_slab",
				new String[] {"copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_blacksoot_stone_slab", 6, "Craft 6x Polished Blacksoot Stone Slab at a crafting table.", "Stellt 6x Polierte Schwarzru\u00dfsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_blacksoot_stone_stairs", "copper_inferno:polished_blacksoot_stone_stairs", "nightslate/polished_blacksoot_stone_stairs",
				new String[] {"copper_inferno:polished_blacksoot_stone", "", "", "copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "", "copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone"},
				"copper_inferno:polished_blacksoot_stone_stairs", 4, "Craft 4x Polished Blacksoot Stone Stairs at a crafting table.", "Stellt 4x Polierte Schwarzru\u00dfsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_blacksoot_stone_wall", "copper_inferno:polished_blacksoot_stone_wall", "nightslate/polished_blacksoot_stone_wall",
				new String[] {"copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "copper_inferno:polished_blacksoot_stone", "", "", ""},
				"copper_inferno:polished_blacksoot_stone_wall", 6, "Craft 6x Polished Blacksoot Stone Wall at a crafting table.", "Stellt 6x Polierte Schwarzru\u00dfsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_brick_slab", "copper_inferno:blacksoot_stone_brick_slab", "nightslate/blacksoot_stone_brick_slab",
				new String[] {"copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:blacksoot_stone_brick_slab", 6, "Craft 6x Blacksoot Stone Brick Slab at a crafting table.", "Stellt 6x Schwarzru\u00dfsteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_brick_stairs", "copper_inferno:blacksoot_stone_brick_stairs", "nightslate/blacksoot_stone_brick_stairs",
				new String[] {"copper_inferno:blacksoot_stone_bricks", "", "", "copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "", "copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks"},
				"copper_inferno:blacksoot_stone_brick_stairs", 4, "Craft 4x Blacksoot Stone Brick Stairs at a crafting table.", "Stellt 4x Schwarzru\u00dfsteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_brick_wall", "copper_inferno:blacksoot_stone_brick_wall", "nightslate/blacksoot_stone_brick_wall",
				new String[] {"copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "copper_inferno:blacksoot_stone_bricks", "", "", ""},
				"copper_inferno:blacksoot_stone_brick_wall", 6, "Craft 6x Blacksoot Stone Brick Wall at a crafting table.", "Stellt 6x Schwarzru\u00dfsteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_blacksoot_stone_bricks", "copper_inferno:cracked_blacksoot_stone_bricks", "nightslate/cracked_blacksoot_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_blacksoot_stone_bricks", 1, "Smelting Blacksoot Stone Bricks in a furnace yields Cracked Blacksoot Stone Bricks.", "Schwarzru\u00dfsteinziegel im Ofen gebrannt ergibt Rissige Schwarzru\u00dfsteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_blacksoot_stone_bricks", "copper_inferno:chiseled_blacksoot_stone_bricks", "nightslate/chiseled_blacksoot_stone_bricks",
				new String[] {"copper_inferno:blacksoot_stone_brick_slab", "", "", "copper_inferno:blacksoot_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_blacksoot_stone_bricks", 1, "Craft 1x Chiseled Blacksoot Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schwarzru\u00dfsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_pillar", "copper_inferno:blacksoot_stone_pillar", "nightslate/blacksoot_stone_pillar",
				new String[] {"copper_inferno:blacksoot_stone_bricks", "", "", "copper_inferno:blacksoot_stone_bricks", "", "", "", "", ""},
				"copper_inferno:blacksoot_stone_pillar", 2, "Craft 2x Blacksoot Stone Pillar at a crafting table.", "Stellt 2x Schwarzru\u00dfsteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_slab_from_blacksoot_stone_stonecutting", "copper_inferno:blacksoot_stone_slab", "nightslate/blacksoot_stone_slab_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_slab", 2, "Stonecutting: cut 2x Blacksoot Stone Slab from Blacksoot Stone.", "Steins\u00e4ge: 2x Schwarzru\u00dfsteinstufe aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_stairs_from_blacksoot_stone_stonecutting", "copper_inferno:blacksoot_stone_stairs", "nightslate/blacksoot_stone_stairs_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_stairs", 1, "Stonecutting: cut 1x Blacksoot Stone Stairs from Blacksoot Stone.", "Steins\u00e4ge: 1x Schwarzru\u00dfsteintreppe aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_wall_from_blacksoot_stone_stonecutting", "copper_inferno:blacksoot_stone_wall", "nightslate/blacksoot_stone_wall_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_wall", 1, "Stonecutting: cut 1x Blacksoot Stone Wall from Blacksoot Stone.", "Steins\u00e4ge: 1x Schwarzru\u00dfsteinmauer aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_blacksoot_stone_from_blacksoot_stone_stonecutting", "copper_inferno:polished_blacksoot_stone", "nightslate/polished_blacksoot_stone_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:polished_blacksoot_stone", 1, "Stonecutting: cut 1x Polished Blacksoot Stone from Blacksoot Stone.", "Steins\u00e4ge: 1x Polierten Schwarzru\u00dfstein aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_blacksoot_stone_slab_from_blacksoot_stone_stonecutting", "copper_inferno:polished_blacksoot_stone_slab", "nightslate/polished_blacksoot_stone_slab_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:polished_blacksoot_stone_slab", 2, "Stonecutting: cut 2x Polished Blacksoot Stone Slab from Blacksoot Stone.", "Steins\u00e4ge: 2x Polierte Schwarzru\u00dfsteinstufe aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_blacksoot_stone_stairs_from_blacksoot_stone_stonecutting", "copper_inferno:polished_blacksoot_stone_stairs", "nightslate/polished_blacksoot_stone_stairs_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:polished_blacksoot_stone_stairs", 1, "Stonecutting: cut 1x Polished Blacksoot Stone Stairs from Blacksoot Stone.", "Steins\u00e4ge: 1x Polierte Schwarzru\u00dfsteintreppe aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_blacksoot_stone_wall_from_blacksoot_stone_stonecutting", "copper_inferno:polished_blacksoot_stone_wall", "nightslate/polished_blacksoot_stone_wall_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:polished_blacksoot_stone_wall", 1, "Stonecutting: cut 1x Polished Blacksoot Stone Wall from Blacksoot Stone.", "Steins\u00e4ge: 1x Polierte Schwarzru\u00dfsteinmauer aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_bricks_from_blacksoot_stone_stonecutting", "copper_inferno:blacksoot_stone_bricks", "nightslate/blacksoot_stone_bricks_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_bricks", 1, "Stonecutting: cut 1x Blacksoot Stone Bricks from Blacksoot Stone.", "Steins\u00e4ge: 1x Schwarzru\u00dfsteinziegel aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_brick_slab_from_blacksoot_stone_stonecutting", "copper_inferno:blacksoot_stone_brick_slab", "nightslate/blacksoot_stone_brick_slab_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_brick_slab", 2, "Stonecutting: cut 2x Blacksoot Stone Brick Slab from Blacksoot Stone.", "Steins\u00e4ge: 2x Schwarzru\u00dfsteinziegelstufe aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_brick_stairs_from_blacksoot_stone_stonecutting", "copper_inferno:blacksoot_stone_brick_stairs", "nightslate/blacksoot_stone_brick_stairs_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_brick_stairs", 1, "Stonecutting: cut 1x Blacksoot Stone Brick Stairs from Blacksoot Stone.", "Steins\u00e4ge: 1x Schwarzru\u00dfsteinziegeltreppe aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_brick_wall_from_blacksoot_stone_stonecutting", "copper_inferno:blacksoot_stone_brick_wall", "nightslate/blacksoot_stone_brick_wall_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_brick_wall", 1, "Stonecutting: cut 1x Blacksoot Stone Brick Wall from Blacksoot Stone.", "Steins\u00e4ge: 1x Schwarzru\u00dfsteinziegelmauer aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_tiles_from_blacksoot_stone_stonecutting", "copper_inferno:blacksoot_stone_tiles", "nightslate/blacksoot_stone_tiles_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_tiles", 1, "Stonecutting: cut 1x Blacksoot Stone Tiles from Blacksoot Stone.", "Steins\u00e4ge: 1x Schwarzru\u00dfsteinfliesen aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_blacksoot_stone_bricks_from_blacksoot_stone_stonecutting", "copper_inferno:chiseled_blacksoot_stone_bricks", "nightslate/chiseled_blacksoot_stone_bricks_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:chiseled_blacksoot_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Blacksoot Stone Bricks from Blacksoot Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schwarzru\u00dfsteinziegel aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/blacksoot_stone_pillar_from_blacksoot_stone_stonecutting", "copper_inferno:blacksoot_stone_pillar", "nightslate/blacksoot_stone_pillar_from_blacksoot_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blacksoot_stone", "", "", "", ""},
				"copper_inferno:blacksoot_stone_pillar", 1, "Stonecutting: cut 1x Blacksoot Stone Pillar from Blacksoot Stone.", "Steins\u00e4ge: 1x Schwarzru\u00dfsteins\u00e4ule aus Schwarzru\u00dfstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian", "copper_inferno:char_obsidian", "nightslate/char_obsidian",
				new String[] {"minecraft:obsidian", "", "minecraft:obsidian", "", "copper_inferno:blacksoot_stone", "", "minecraft:obsidian", "", "minecraft:obsidian"},
				"copper_inferno:char_obsidian", 4, "Craft 4x Char Obsidian at a crafting table.", "Stellt 4x Brandobsidian an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "nightslate/char_obsidian_bricks",
				new String[] {"copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "", "copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_bricks", 4, "Craft 4x Char Obsidian Bricks at a crafting table.", "Stellt 4x Brandobsidianziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_tiles", "copper_inferno:char_obsidian_tiles", "nightslate/char_obsidian_tiles",
				new String[] {"copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "", "copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "", "", "", ""},
				"copper_inferno:char_obsidian_tiles", 4, "Craft 4x Char Obsidian Tiles at a crafting table.", "Stellt 4x Brandobsidianfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_char_obsidian", "copper_inferno:polished_char_obsidian", "nightslate/polished_char_obsidian",
				new String[] {"copper_inferno:char_obsidian_tiles", "copper_inferno:char_obsidian_tiles", "", "copper_inferno:char_obsidian_tiles", "copper_inferno:char_obsidian_tiles", "", "", "", ""},
				"copper_inferno:polished_char_obsidian", 4, "Craft 4x Polished Char Obsidian at a crafting table.", "Stellt 4x Polierten Brandobsidian an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_slab", "copper_inferno:char_obsidian_slab", "nightslate/char_obsidian_slab",
				new String[] {"copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "", "", "", "", "", ""},
				"copper_inferno:char_obsidian_slab", 6, "Craft 6x Char Obsidian Slab at a crafting table.", "Stellt 6x Brandobsidianstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_stairs", "copper_inferno:char_obsidian_stairs", "nightslate/char_obsidian_stairs",
				new String[] {"copper_inferno:char_obsidian", "", "", "copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "", "copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "copper_inferno:char_obsidian"},
				"copper_inferno:char_obsidian_stairs", 4, "Craft 4x Char Obsidian Stairs at a crafting table.", "Stellt 4x Brandobsidiantreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_wall", "copper_inferno:char_obsidian_wall", "nightslate/char_obsidian_wall",
				new String[] {"copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "copper_inferno:char_obsidian", "", "", ""},
				"copper_inferno:char_obsidian_wall", 6, "Craft 6x Char Obsidian Wall at a crafting table.", "Stellt 6x Brandobsidianmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_char_obsidian_slab", "copper_inferno:polished_char_obsidian_slab", "nightslate/polished_char_obsidian_slab",
				new String[] {"copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian", "", "", "", "", "", ""},
				"copper_inferno:polished_char_obsidian_slab", 6, "Craft 6x Polished Char Obsidian Slab at a crafting table.", "Stellt 6x Polierte Brandobsidianstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_char_obsidian_stairs", "copper_inferno:polished_char_obsidian_stairs", "nightslate/polished_char_obsidian_stairs",
				new String[] {"copper_inferno:polished_char_obsidian", "", "", "copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian", "", "copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian"},
				"copper_inferno:polished_char_obsidian_stairs", 4, "Craft 4x Polished Char Obsidian Stairs at a crafting table.", "Stellt 4x Polierte Brandobsidiantreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_char_obsidian_wall", "copper_inferno:polished_char_obsidian_wall", "nightslate/polished_char_obsidian_wall",
				new String[] {"copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian", "copper_inferno:polished_char_obsidian", "", "", ""},
				"copper_inferno:polished_char_obsidian_wall", 6, "Craft 6x Polished Char Obsidian Wall at a crafting table.", "Stellt 6x Polierte Brandobsidianmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_brick_slab", "copper_inferno:char_obsidian_brick_slab", "nightslate/char_obsidian_brick_slab",
				new String[] {"copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "", "", "", "", "", ""},
				"copper_inferno:char_obsidian_brick_slab", 6, "Craft 6x Char Obsidian Brick Slab at a crafting table.", "Stellt 6x Brandobsidianziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_brick_stairs", "copper_inferno:char_obsidian_brick_stairs", "nightslate/char_obsidian_brick_stairs",
				new String[] {"copper_inferno:char_obsidian_bricks", "", "", "copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "", "copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks"},
				"copper_inferno:char_obsidian_brick_stairs", 4, "Craft 4x Char Obsidian Brick Stairs at a crafting table.", "Stellt 4x Brandobsidianziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_brick_wall", "copper_inferno:char_obsidian_brick_wall", "nightslate/char_obsidian_brick_wall",
				new String[] {"copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "copper_inferno:char_obsidian_bricks", "", "", ""},
				"copper_inferno:char_obsidian_brick_wall", 6, "Craft 6x Char Obsidian Brick Wall at a crafting table.", "Stellt 6x Brandobsidianziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_char_obsidian_bricks", "copper_inferno:cracked_char_obsidian_bricks", "nightslate/cracked_char_obsidian_bricks",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian_bricks", "", "", "", ""},
				"copper_inferno:cracked_char_obsidian_bricks", 1, "Smelting Char Obsidian Bricks in a furnace yields Cracked Char Obsidian Bricks.", "Brandobsidianziegel im Ofen gebrannt ergibt Rissige Brandobsidianziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_char_obsidian_bricks", "copper_inferno:chiseled_char_obsidian_bricks", "nightslate/chiseled_char_obsidian_bricks",
				new String[] {"copper_inferno:char_obsidian_brick_slab", "", "", "copper_inferno:char_obsidian_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_char_obsidian_bricks", 1, "Craft 1x Chiseled Char Obsidian Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Brandobsidianziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_pillar", "copper_inferno:char_obsidian_pillar", "nightslate/char_obsidian_pillar",
				new String[] {"copper_inferno:char_obsidian_bricks", "", "", "copper_inferno:char_obsidian_bricks", "", "", "", "", ""},
				"copper_inferno:char_obsidian_pillar", 2, "Craft 2x Char Obsidian Pillar at a crafting table.", "Stellt 2x Brandobsidians\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_slab_from_char_obsidian_stonecutting", "copper_inferno:char_obsidian_slab", "nightslate/char_obsidian_slab_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_slab", 2, "Stonecutting: cut 2x Char Obsidian Slab from Char Obsidian.", "Steins\u00e4ge: 2x Brandobsidianstufe aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_stairs_from_char_obsidian_stonecutting", "copper_inferno:char_obsidian_stairs", "nightslate/char_obsidian_stairs_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_stairs", 1, "Stonecutting: cut 1x Char Obsidian Stairs from Char Obsidian.", "Steins\u00e4ge: 1x Brandobsidiantreppe aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_wall_from_char_obsidian_stonecutting", "copper_inferno:char_obsidian_wall", "nightslate/char_obsidian_wall_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_wall", 1, "Stonecutting: cut 1x Char Obsidian Wall from Char Obsidian.", "Steins\u00e4ge: 1x Brandobsidianmauer aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_char_obsidian_from_char_obsidian_stonecutting", "copper_inferno:polished_char_obsidian", "nightslate/polished_char_obsidian_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:polished_char_obsidian", 1, "Stonecutting: cut 1x Polished Char Obsidian from Char Obsidian.", "Steins\u00e4ge: 1x Polierten Brandobsidian aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_char_obsidian_slab_from_char_obsidian_stonecutting", "copper_inferno:polished_char_obsidian_slab", "nightslate/polished_char_obsidian_slab_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:polished_char_obsidian_slab", 2, "Stonecutting: cut 2x Polished Char Obsidian Slab from Char Obsidian.", "Steins\u00e4ge: 2x Polierte Brandobsidianstufe aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_char_obsidian_stairs_from_char_obsidian_stonecutting", "copper_inferno:polished_char_obsidian_stairs", "nightslate/polished_char_obsidian_stairs_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:polished_char_obsidian_stairs", 1, "Stonecutting: cut 1x Polished Char Obsidian Stairs from Char Obsidian.", "Steins\u00e4ge: 1x Polierte Brandobsidiantreppe aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_char_obsidian_wall_from_char_obsidian_stonecutting", "copper_inferno:polished_char_obsidian_wall", "nightslate/polished_char_obsidian_wall_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:polished_char_obsidian_wall", 1, "Stonecutting: cut 1x Polished Char Obsidian Wall from Char Obsidian.", "Steins\u00e4ge: 1x Polierte Brandobsidianmauer aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_bricks_from_char_obsidian_stonecutting", "copper_inferno:char_obsidian_bricks", "nightslate/char_obsidian_bricks_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_bricks", 1, "Stonecutting: cut 1x Char Obsidian Bricks from Char Obsidian.", "Steins\u00e4ge: 1x Brandobsidianziegel aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_brick_slab_from_char_obsidian_stonecutting", "copper_inferno:char_obsidian_brick_slab", "nightslate/char_obsidian_brick_slab_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_brick_slab", 2, "Stonecutting: cut 2x Char Obsidian Brick Slab from Char Obsidian.", "Steins\u00e4ge: 2x Brandobsidianziegelstufe aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_brick_stairs_from_char_obsidian_stonecutting", "copper_inferno:char_obsidian_brick_stairs", "nightslate/char_obsidian_brick_stairs_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_brick_stairs", 1, "Stonecutting: cut 1x Char Obsidian Brick Stairs from Char Obsidian.", "Steins\u00e4ge: 1x Brandobsidianziegeltreppe aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_brick_wall_from_char_obsidian_stonecutting", "copper_inferno:char_obsidian_brick_wall", "nightslate/char_obsidian_brick_wall_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_brick_wall", 1, "Stonecutting: cut 1x Char Obsidian Brick Wall from Char Obsidian.", "Steins\u00e4ge: 1x Brandobsidianziegelmauer aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_tiles_from_char_obsidian_stonecutting", "copper_inferno:char_obsidian_tiles", "nightslate/char_obsidian_tiles_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_tiles", 1, "Stonecutting: cut 1x Char Obsidian Tiles from Char Obsidian.", "Steins\u00e4ge: 1x Brandobsidianfliesen aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_char_obsidian_bricks_from_char_obsidian_stonecutting", "copper_inferno:chiseled_char_obsidian_bricks", "nightslate/chiseled_char_obsidian_bricks_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:chiseled_char_obsidian_bricks", 1, "Stonecutting: cut 1x Chiseled Char Obsidian Bricks from Char Obsidian.", "Steins\u00e4ge: 1x Gemei\u00dfelte Brandobsidianziegel aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/char_obsidian_pillar_from_char_obsidian_stonecutting", "copper_inferno:char_obsidian_pillar", "nightslate/char_obsidian_pillar_from_char_obsidian_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:char_obsidian", "", "", "", ""},
				"copper_inferno:char_obsidian_pillar", 1, "Stonecutting: cut 1x Char Obsidian Pillar from Char Obsidian.", "Steins\u00e4ge: 1x Brandobsidians\u00e4ule aus Brandobsidian schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice", "copper_inferno:dark_pumice", "nightslate/dark_pumice",
				new String[] {"minecraft:gravel", "", "minecraft:gravel", "", "copper_inferno:char_obsidian", "", "minecraft:gravel", "", "minecraft:gravel"},
				"copper_inferno:dark_pumice", 4, "Craft 4x Dark Pumice at a crafting table.", "Stellt 4x Dunkelbims an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "nightslate/dark_pumice_bricks",
				new String[] {"copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "", "copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_bricks", 4, "Craft 4x Dark Pumice Bricks at a crafting table.", "Stellt 4x Dunkelbimsziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_tiles", "copper_inferno:dark_pumice_tiles", "nightslate/dark_pumice_tiles",
				new String[] {"copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "", "copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "", "", "", ""},
				"copper_inferno:dark_pumice_tiles", 4, "Craft 4x Dark Pumice Tiles at a crafting table.", "Stellt 4x Dunkelbimsfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_dark_pumice", "copper_inferno:polished_dark_pumice", "nightslate/polished_dark_pumice",
				new String[] {"copper_inferno:dark_pumice_tiles", "copper_inferno:dark_pumice_tiles", "", "copper_inferno:dark_pumice_tiles", "copper_inferno:dark_pumice_tiles", "", "", "", ""},
				"copper_inferno:polished_dark_pumice", 4, "Craft 4x Polished Dark Pumice at a crafting table.", "Stellt 4x Polierten Dunkelbims an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_slab", "copper_inferno:dark_pumice_slab", "nightslate/dark_pumice_slab",
				new String[] {"copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "", "", "", "", "", ""},
				"copper_inferno:dark_pumice_slab", 6, "Craft 6x Dark Pumice Slab at a crafting table.", "Stellt 6x Dunkelbimsstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_stairs", "copper_inferno:dark_pumice_stairs", "nightslate/dark_pumice_stairs",
				new String[] {"copper_inferno:dark_pumice", "", "", "copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "", "copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "copper_inferno:dark_pumice"},
				"copper_inferno:dark_pumice_stairs", 4, "Craft 4x Dark Pumice Stairs at a crafting table.", "Stellt 4x Dunkelbimstreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_wall", "copper_inferno:dark_pumice_wall", "nightslate/dark_pumice_wall",
				new String[] {"copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "copper_inferno:dark_pumice", "", "", ""},
				"copper_inferno:dark_pumice_wall", 6, "Craft 6x Dark Pumice Wall at a crafting table.", "Stellt 6x Dunkelbimsmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_dark_pumice_slab", "copper_inferno:polished_dark_pumice_slab", "nightslate/polished_dark_pumice_slab",
				new String[] {"copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice", "", "", "", "", "", ""},
				"copper_inferno:polished_dark_pumice_slab", 6, "Craft 6x Polished Dark Pumice Slab at a crafting table.", "Stellt 6x Polierte Dunkelbimsstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_dark_pumice_stairs", "copper_inferno:polished_dark_pumice_stairs", "nightslate/polished_dark_pumice_stairs",
				new String[] {"copper_inferno:polished_dark_pumice", "", "", "copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice", "", "copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice"},
				"copper_inferno:polished_dark_pumice_stairs", 4, "Craft 4x Polished Dark Pumice Stairs at a crafting table.", "Stellt 4x Polierte Dunkelbimstreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_dark_pumice_wall", "copper_inferno:polished_dark_pumice_wall", "nightslate/polished_dark_pumice_wall",
				new String[] {"copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice", "copper_inferno:polished_dark_pumice", "", "", ""},
				"copper_inferno:polished_dark_pumice_wall", 6, "Craft 6x Polished Dark Pumice Wall at a crafting table.", "Stellt 6x Polierte Dunkelbimsmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_brick_slab", "copper_inferno:dark_pumice_brick_slab", "nightslate/dark_pumice_brick_slab",
				new String[] {"copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "", "", "", "", "", ""},
				"copper_inferno:dark_pumice_brick_slab", 6, "Craft 6x Dark Pumice Brick Slab at a crafting table.", "Stellt 6x Dunkelbimsziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_brick_stairs", "copper_inferno:dark_pumice_brick_stairs", "nightslate/dark_pumice_brick_stairs",
				new String[] {"copper_inferno:dark_pumice_bricks", "", "", "copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "", "copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks"},
				"copper_inferno:dark_pumice_brick_stairs", 4, "Craft 4x Dark Pumice Brick Stairs at a crafting table.", "Stellt 4x Dunkelbimsziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_brick_wall", "copper_inferno:dark_pumice_brick_wall", "nightslate/dark_pumice_brick_wall",
				new String[] {"copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "copper_inferno:dark_pumice_bricks", "", "", ""},
				"copper_inferno:dark_pumice_brick_wall", 6, "Craft 6x Dark Pumice Brick Wall at a crafting table.", "Stellt 6x Dunkelbimsziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_dark_pumice_bricks", "copper_inferno:cracked_dark_pumice_bricks", "nightslate/cracked_dark_pumice_bricks",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice_bricks", "", "", "", ""},
				"copper_inferno:cracked_dark_pumice_bricks", 1, "Smelting Dark Pumice Bricks in a furnace yields Cracked Dark Pumice Bricks.", "Dunkelbimsziegel im Ofen gebrannt ergibt Rissige Dunkelbimsziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_dark_pumice_bricks", "copper_inferno:chiseled_dark_pumice_bricks", "nightslate/chiseled_dark_pumice_bricks",
				new String[] {"copper_inferno:dark_pumice_brick_slab", "", "", "copper_inferno:dark_pumice_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_dark_pumice_bricks", 1, "Craft 1x Chiseled Dark Pumice Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Dunkelbimsziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_pillar", "copper_inferno:dark_pumice_pillar", "nightslate/dark_pumice_pillar",
				new String[] {"copper_inferno:dark_pumice_bricks", "", "", "copper_inferno:dark_pumice_bricks", "", "", "", "", ""},
				"copper_inferno:dark_pumice_pillar", 2, "Craft 2x Dark Pumice Pillar at a crafting table.", "Stellt 2x Dunkelbimss\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_slab_from_dark_pumice_stonecutting", "copper_inferno:dark_pumice_slab", "nightslate/dark_pumice_slab_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_slab", 2, "Stonecutting: cut 2x Dark Pumice Slab from Dark Pumice.", "Steins\u00e4ge: 2x Dunkelbimsstufe aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_stairs_from_dark_pumice_stonecutting", "copper_inferno:dark_pumice_stairs", "nightslate/dark_pumice_stairs_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_stairs", 1, "Stonecutting: cut 1x Dark Pumice Stairs from Dark Pumice.", "Steins\u00e4ge: 1x Dunkelbimstreppe aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_wall_from_dark_pumice_stonecutting", "copper_inferno:dark_pumice_wall", "nightslate/dark_pumice_wall_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_wall", 1, "Stonecutting: cut 1x Dark Pumice Wall from Dark Pumice.", "Steins\u00e4ge: 1x Dunkelbimsmauer aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_dark_pumice_from_dark_pumice_stonecutting", "copper_inferno:polished_dark_pumice", "nightslate/polished_dark_pumice_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:polished_dark_pumice", 1, "Stonecutting: cut 1x Polished Dark Pumice from Dark Pumice.", "Steins\u00e4ge: 1x Polierten Dunkelbims aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_dark_pumice_slab_from_dark_pumice_stonecutting", "copper_inferno:polished_dark_pumice_slab", "nightslate/polished_dark_pumice_slab_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:polished_dark_pumice_slab", 2, "Stonecutting: cut 2x Polished Dark Pumice Slab from Dark Pumice.", "Steins\u00e4ge: 2x Polierte Dunkelbimsstufe aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_dark_pumice_stairs_from_dark_pumice_stonecutting", "copper_inferno:polished_dark_pumice_stairs", "nightslate/polished_dark_pumice_stairs_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:polished_dark_pumice_stairs", 1, "Stonecutting: cut 1x Polished Dark Pumice Stairs from Dark Pumice.", "Steins\u00e4ge: 1x Polierte Dunkelbimstreppe aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_dark_pumice_wall_from_dark_pumice_stonecutting", "copper_inferno:polished_dark_pumice_wall", "nightslate/polished_dark_pumice_wall_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:polished_dark_pumice_wall", 1, "Stonecutting: cut 1x Polished Dark Pumice Wall from Dark Pumice.", "Steins\u00e4ge: 1x Polierte Dunkelbimsmauer aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_bricks_from_dark_pumice_stonecutting", "copper_inferno:dark_pumice_bricks", "nightslate/dark_pumice_bricks_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_bricks", 1, "Stonecutting: cut 1x Dark Pumice Bricks from Dark Pumice.", "Steins\u00e4ge: 1x Dunkelbimsziegel aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_brick_slab_from_dark_pumice_stonecutting", "copper_inferno:dark_pumice_brick_slab", "nightslate/dark_pumice_brick_slab_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_brick_slab", 2, "Stonecutting: cut 2x Dark Pumice Brick Slab from Dark Pumice.", "Steins\u00e4ge: 2x Dunkelbimsziegelstufe aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_brick_stairs_from_dark_pumice_stonecutting", "copper_inferno:dark_pumice_brick_stairs", "nightslate/dark_pumice_brick_stairs_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_brick_stairs", 1, "Stonecutting: cut 1x Dark Pumice Brick Stairs from Dark Pumice.", "Steins\u00e4ge: 1x Dunkelbimsziegeltreppe aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_brick_wall_from_dark_pumice_stonecutting", "copper_inferno:dark_pumice_brick_wall", "nightslate/dark_pumice_brick_wall_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_brick_wall", 1, "Stonecutting: cut 1x Dark Pumice Brick Wall from Dark Pumice.", "Steins\u00e4ge: 1x Dunkelbimsziegelmauer aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_tiles_from_dark_pumice_stonecutting", "copper_inferno:dark_pumice_tiles", "nightslate/dark_pumice_tiles_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_tiles", 1, "Stonecutting: cut 1x Dark Pumice Tiles from Dark Pumice.", "Steins\u00e4ge: 1x Dunkelbimsfliesen aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_dark_pumice_bricks_from_dark_pumice_stonecutting", "copper_inferno:chiseled_dark_pumice_bricks", "nightslate/chiseled_dark_pumice_bricks_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:chiseled_dark_pumice_bricks", 1, "Stonecutting: cut 1x Chiseled Dark Pumice Bricks from Dark Pumice.", "Steins\u00e4ge: 1x Gemei\u00dfelte Dunkelbimsziegel aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/dark_pumice_pillar_from_dark_pumice_stonecutting", "copper_inferno:dark_pumice_pillar", "nightslate/dark_pumice_pillar_from_dark_pumice_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:dark_pumice", "", "", "", ""},
				"copper_inferno:dark_pumice_pillar", 1, "Stonecutting: cut 1x Dark Pumice Pillar from Dark Pumice.", "Steins\u00e4ge: 1x Dunkelbimss\u00e4ule aus Dunkelbims schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone", "copper_inferno:ebonstone", "nightslate/ebonstone",
				new String[] {"minecraft:coal", "", "minecraft:coal", "", "copper_inferno:dark_pumice", "", "minecraft:coal", "", "minecraft:coal"},
				"copper_inferno:ebonstone", 4, "Craft 4x Ebonstone at a crafting table.", "Stellt 4x Pechstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_bricks", "copper_inferno:ebonstone_bricks", "nightslate/ebonstone_bricks",
				new String[] {"copper_inferno:ebonstone", "copper_inferno:ebonstone", "", "copper_inferno:ebonstone", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_bricks", 4, "Craft 4x Ebonstone Bricks at a crafting table.", "Stellt 4x Pechsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_tiles", "copper_inferno:ebonstone_tiles", "nightslate/ebonstone_tiles",
				new String[] {"copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "", "copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "", "", "", ""},
				"copper_inferno:ebonstone_tiles", 4, "Craft 4x Ebonstone Tiles at a crafting table.", "Stellt 4x Pechsteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_ebonstone", "copper_inferno:polished_ebonstone", "nightslate/polished_ebonstone",
				new String[] {"copper_inferno:ebonstone_tiles", "copper_inferno:ebonstone_tiles", "", "copper_inferno:ebonstone_tiles", "copper_inferno:ebonstone_tiles", "", "", "", ""},
				"copper_inferno:polished_ebonstone", 4, "Craft 4x Polished Ebonstone at a crafting table.", "Stellt 4x Polierten Pechstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_slab", "copper_inferno:ebonstone_slab", "nightslate/ebonstone_slab",
				new String[] {"copper_inferno:ebonstone", "copper_inferno:ebonstone", "copper_inferno:ebonstone", "", "", "", "", "", ""},
				"copper_inferno:ebonstone_slab", 6, "Craft 6x Ebonstone Slab at a crafting table.", "Stellt 6x Pechsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_stairs", "copper_inferno:ebonstone_stairs", "nightslate/ebonstone_stairs",
				new String[] {"copper_inferno:ebonstone", "", "", "copper_inferno:ebonstone", "copper_inferno:ebonstone", "", "copper_inferno:ebonstone", "copper_inferno:ebonstone", "copper_inferno:ebonstone"},
				"copper_inferno:ebonstone_stairs", 4, "Craft 4x Ebonstone Stairs at a crafting table.", "Stellt 4x Pechsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_wall", "copper_inferno:ebonstone_wall", "nightslate/ebonstone_wall",
				new String[] {"copper_inferno:ebonstone", "copper_inferno:ebonstone", "copper_inferno:ebonstone", "copper_inferno:ebonstone", "copper_inferno:ebonstone", "copper_inferno:ebonstone", "", "", ""},
				"copper_inferno:ebonstone_wall", 6, "Craft 6x Ebonstone Wall at a crafting table.", "Stellt 6x Pechsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_ebonstone_slab", "copper_inferno:polished_ebonstone_slab", "nightslate/polished_ebonstone_slab",
				new String[] {"copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone", "", "", "", "", "", ""},
				"copper_inferno:polished_ebonstone_slab", 6, "Craft 6x Polished Ebonstone Slab at a crafting table.", "Stellt 6x Polierte Pechsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_ebonstone_stairs", "copper_inferno:polished_ebonstone_stairs", "nightslate/polished_ebonstone_stairs",
				new String[] {"copper_inferno:polished_ebonstone", "", "", "copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone", "", "copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone"},
				"copper_inferno:polished_ebonstone_stairs", 4, "Craft 4x Polished Ebonstone Stairs at a crafting table.", "Stellt 4x Polierte Pechsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_ebonstone_wall", "copper_inferno:polished_ebonstone_wall", "nightslate/polished_ebonstone_wall",
				new String[] {"copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone", "copper_inferno:polished_ebonstone", "", "", ""},
				"copper_inferno:polished_ebonstone_wall", 6, "Craft 6x Polished Ebonstone Wall at a crafting table.", "Stellt 6x Polierte Pechsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_brick_slab", "copper_inferno:ebonstone_brick_slab", "nightslate/ebonstone_brick_slab",
				new String[] {"copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "", "", "", "", "", ""},
				"copper_inferno:ebonstone_brick_slab", 6, "Craft 6x Ebonstone Brick Slab at a crafting table.", "Stellt 6x Pechsteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_brick_stairs", "copper_inferno:ebonstone_brick_stairs", "nightslate/ebonstone_brick_stairs",
				new String[] {"copper_inferno:ebonstone_bricks", "", "", "copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "", "copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks"},
				"copper_inferno:ebonstone_brick_stairs", 4, "Craft 4x Ebonstone Brick Stairs at a crafting table.", "Stellt 4x Pechsteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_brick_wall", "copper_inferno:ebonstone_brick_wall", "nightslate/ebonstone_brick_wall",
				new String[] {"copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "copper_inferno:ebonstone_bricks", "", "", ""},
				"copper_inferno:ebonstone_brick_wall", 6, "Craft 6x Ebonstone Brick Wall at a crafting table.", "Stellt 6x Pechsteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_ebonstone_bricks", "copper_inferno:cracked_ebonstone_bricks", "nightslate/cracked_ebonstone_bricks",
				new String[] {"", "", "", "", "copper_inferno:ebonstone_bricks", "", "", "", ""},
				"copper_inferno:cracked_ebonstone_bricks", 1, "Smelting Ebonstone Bricks in a furnace yields Cracked Ebonstone Bricks.", "Pechsteinziegel im Ofen gebrannt ergibt Rissige Pechsteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_ebonstone_bricks", "copper_inferno:chiseled_ebonstone_bricks", "nightslate/chiseled_ebonstone_bricks",
				new String[] {"copper_inferno:ebonstone_brick_slab", "", "", "copper_inferno:ebonstone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_ebonstone_bricks", 1, "Craft 1x Chiseled Ebonstone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Pechsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_pillar", "copper_inferno:ebonstone_pillar", "nightslate/ebonstone_pillar",
				new String[] {"copper_inferno:ebonstone_bricks", "", "", "copper_inferno:ebonstone_bricks", "", "", "", "", ""},
				"copper_inferno:ebonstone_pillar", 2, "Craft 2x Ebonstone Pillar at a crafting table.", "Stellt 2x Pechsteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_slab_from_ebonstone_stonecutting", "copper_inferno:ebonstone_slab", "nightslate/ebonstone_slab_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_slab", 2, "Stonecutting: cut 2x Ebonstone Slab from Ebonstone.", "Steins\u00e4ge: 2x Pechsteinstufe aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_stairs_from_ebonstone_stonecutting", "copper_inferno:ebonstone_stairs", "nightslate/ebonstone_stairs_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_stairs", 1, "Stonecutting: cut 1x Ebonstone Stairs from Ebonstone.", "Steins\u00e4ge: 1x Pechsteintreppe aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_wall_from_ebonstone_stonecutting", "copper_inferno:ebonstone_wall", "nightslate/ebonstone_wall_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_wall", 1, "Stonecutting: cut 1x Ebonstone Wall from Ebonstone.", "Steins\u00e4ge: 1x Pechsteinmauer aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_ebonstone_from_ebonstone_stonecutting", "copper_inferno:polished_ebonstone", "nightslate/polished_ebonstone_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:polished_ebonstone", 1, "Stonecutting: cut 1x Polished Ebonstone from Ebonstone.", "Steins\u00e4ge: 1x Polierten Pechstein aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_ebonstone_slab_from_ebonstone_stonecutting", "copper_inferno:polished_ebonstone_slab", "nightslate/polished_ebonstone_slab_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:polished_ebonstone_slab", 2, "Stonecutting: cut 2x Polished Ebonstone Slab from Ebonstone.", "Steins\u00e4ge: 2x Polierte Pechsteinstufe aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_ebonstone_stairs_from_ebonstone_stonecutting", "copper_inferno:polished_ebonstone_stairs", "nightslate/polished_ebonstone_stairs_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:polished_ebonstone_stairs", 1, "Stonecutting: cut 1x Polished Ebonstone Stairs from Ebonstone.", "Steins\u00e4ge: 1x Polierte Pechsteintreppe aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_ebonstone_wall_from_ebonstone_stonecutting", "copper_inferno:polished_ebonstone_wall", "nightslate/polished_ebonstone_wall_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:polished_ebonstone_wall", 1, "Stonecutting: cut 1x Polished Ebonstone Wall from Ebonstone.", "Steins\u00e4ge: 1x Polierte Pechsteinmauer aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_bricks_from_ebonstone_stonecutting", "copper_inferno:ebonstone_bricks", "nightslate/ebonstone_bricks_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_bricks", 1, "Stonecutting: cut 1x Ebonstone Bricks from Ebonstone.", "Steins\u00e4ge: 1x Pechsteinziegel aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_brick_slab_from_ebonstone_stonecutting", "copper_inferno:ebonstone_brick_slab", "nightslate/ebonstone_brick_slab_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_brick_slab", 2, "Stonecutting: cut 2x Ebonstone Brick Slab from Ebonstone.", "Steins\u00e4ge: 2x Pechsteinziegelstufe aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_brick_stairs_from_ebonstone_stonecutting", "copper_inferno:ebonstone_brick_stairs", "nightslate/ebonstone_brick_stairs_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_brick_stairs", 1, "Stonecutting: cut 1x Ebonstone Brick Stairs from Ebonstone.", "Steins\u00e4ge: 1x Pechsteinziegeltreppe aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_brick_wall_from_ebonstone_stonecutting", "copper_inferno:ebonstone_brick_wall", "nightslate/ebonstone_brick_wall_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_brick_wall", 1, "Stonecutting: cut 1x Ebonstone Brick Wall from Ebonstone.", "Steins\u00e4ge: 1x Pechsteinziegelmauer aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_tiles_from_ebonstone_stonecutting", "copper_inferno:ebonstone_tiles", "nightslate/ebonstone_tiles_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_tiles", 1, "Stonecutting: cut 1x Ebonstone Tiles from Ebonstone.", "Steins\u00e4ge: 1x Pechsteinfliesen aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_ebonstone_bricks_from_ebonstone_stonecutting", "copper_inferno:chiseled_ebonstone_bricks", "nightslate/chiseled_ebonstone_bricks_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:chiseled_ebonstone_bricks", 1, "Stonecutting: cut 1x Chiseled Ebonstone Bricks from Ebonstone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Pechsteinziegel aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/ebonstone_pillar_from_ebonstone_stonecutting", "copper_inferno:ebonstone_pillar", "nightslate/ebonstone_pillar_from_ebonstone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ebonstone", "", "", "", ""},
				"copper_inferno:ebonstone_pillar", 1, "Stonecutting: cut 1x Ebonstone Pillar from Ebonstone.", "Steins\u00e4ge: 1x Pechsteins\u00e4ule aus Pechstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock", "copper_inferno:murkrock", "nightslate/murkrock",
				new String[] {"minecraft:cobblestone", "", "minecraft:cobblestone", "", "copper_inferno:ebonstone", "", "minecraft:cobblestone", "", "minecraft:cobblestone"},
				"copper_inferno:murkrock", 4, "Craft 4x Murkrock at a crafting table.", "Stellt 4x Tr\u00fcbfels an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_bricks", "copper_inferno:murkrock_bricks", "nightslate/murkrock_bricks",
				new String[] {"copper_inferno:murkrock", "copper_inferno:murkrock", "", "copper_inferno:murkrock", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_bricks", 4, "Craft 4x Murkrock Bricks at a crafting table.", "Stellt 4x Tr\u00fcbfelsziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_tiles", "copper_inferno:murkrock_tiles", "nightslate/murkrock_tiles",
				new String[] {"copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "", "copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "", "", "", ""},
				"copper_inferno:murkrock_tiles", 4, "Craft 4x Murkrock Tiles at a crafting table.", "Stellt 4x Tr\u00fcbfelsfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_murkrock", "copper_inferno:polished_murkrock", "nightslate/polished_murkrock",
				new String[] {"copper_inferno:murkrock_tiles", "copper_inferno:murkrock_tiles", "", "copper_inferno:murkrock_tiles", "copper_inferno:murkrock_tiles", "", "", "", ""},
				"copper_inferno:polished_murkrock", 4, "Craft 4x Polished Murkrock at a crafting table.", "Stellt 4x Polierten Tr\u00fcbfels an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_slab", "copper_inferno:murkrock_slab", "nightslate/murkrock_slab",
				new String[] {"copper_inferno:murkrock", "copper_inferno:murkrock", "copper_inferno:murkrock", "", "", "", "", "", ""},
				"copper_inferno:murkrock_slab", 6, "Craft 6x Murkrock Slab at a crafting table.", "Stellt 6x Tr\u00fcbfelsstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_stairs", "copper_inferno:murkrock_stairs", "nightslate/murkrock_stairs",
				new String[] {"copper_inferno:murkrock", "", "", "copper_inferno:murkrock", "copper_inferno:murkrock", "", "copper_inferno:murkrock", "copper_inferno:murkrock", "copper_inferno:murkrock"},
				"copper_inferno:murkrock_stairs", 4, "Craft 4x Murkrock Stairs at a crafting table.", "Stellt 4x Tr\u00fcbfelstreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_wall", "copper_inferno:murkrock_wall", "nightslate/murkrock_wall",
				new String[] {"copper_inferno:murkrock", "copper_inferno:murkrock", "copper_inferno:murkrock", "copper_inferno:murkrock", "copper_inferno:murkrock", "copper_inferno:murkrock", "", "", ""},
				"copper_inferno:murkrock_wall", 6, "Craft 6x Murkrock Wall at a crafting table.", "Stellt 6x Tr\u00fcbfelsmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_murkrock_slab", "copper_inferno:polished_murkrock_slab", "nightslate/polished_murkrock_slab",
				new String[] {"copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock", "", "", "", "", "", ""},
				"copper_inferno:polished_murkrock_slab", 6, "Craft 6x Polished Murkrock Slab at a crafting table.", "Stellt 6x Polierte Tr\u00fcbfelsstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_murkrock_stairs", "copper_inferno:polished_murkrock_stairs", "nightslate/polished_murkrock_stairs",
				new String[] {"copper_inferno:polished_murkrock", "", "", "copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock", "", "copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock"},
				"copper_inferno:polished_murkrock_stairs", 4, "Craft 4x Polished Murkrock Stairs at a crafting table.", "Stellt 4x Polierte Tr\u00fcbfelstreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_murkrock_wall", "copper_inferno:polished_murkrock_wall", "nightslate/polished_murkrock_wall",
				new String[] {"copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock", "copper_inferno:polished_murkrock", "", "", ""},
				"copper_inferno:polished_murkrock_wall", 6, "Craft 6x Polished Murkrock Wall at a crafting table.", "Stellt 6x Polierte Tr\u00fcbfelsmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_brick_slab", "copper_inferno:murkrock_brick_slab", "nightslate/murkrock_brick_slab",
				new String[] {"copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "", "", "", "", "", ""},
				"copper_inferno:murkrock_brick_slab", 6, "Craft 6x Murkrock Brick Slab at a crafting table.", "Stellt 6x Tr\u00fcbfelsziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_brick_stairs", "copper_inferno:murkrock_brick_stairs", "nightslate/murkrock_brick_stairs",
				new String[] {"copper_inferno:murkrock_bricks", "", "", "copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "", "copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks"},
				"copper_inferno:murkrock_brick_stairs", 4, "Craft 4x Murkrock Brick Stairs at a crafting table.", "Stellt 4x Tr\u00fcbfelsziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_brick_wall", "copper_inferno:murkrock_brick_wall", "nightslate/murkrock_brick_wall",
				new String[] {"copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "copper_inferno:murkrock_bricks", "", "", ""},
				"copper_inferno:murkrock_brick_wall", 6, "Craft 6x Murkrock Brick Wall at a crafting table.", "Stellt 6x Tr\u00fcbfelsziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_murkrock_bricks", "copper_inferno:cracked_murkrock_bricks", "nightslate/cracked_murkrock_bricks",
				new String[] {"", "", "", "", "copper_inferno:murkrock_bricks", "", "", "", ""},
				"copper_inferno:cracked_murkrock_bricks", 1, "Smelting Murkrock Bricks in a furnace yields Cracked Murkrock Bricks.", "Tr\u00fcbfelsziegel im Ofen gebrannt ergibt Rissige Tr\u00fcbfelsziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_murkrock_bricks", "copper_inferno:chiseled_murkrock_bricks", "nightslate/chiseled_murkrock_bricks",
				new String[] {"copper_inferno:murkrock_brick_slab", "", "", "copper_inferno:murkrock_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_murkrock_bricks", 1, "Craft 1x Chiseled Murkrock Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Tr\u00fcbfelsziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_pillar", "copper_inferno:murkrock_pillar", "nightslate/murkrock_pillar",
				new String[] {"copper_inferno:murkrock_bricks", "", "", "copper_inferno:murkrock_bricks", "", "", "", "", ""},
				"copper_inferno:murkrock_pillar", 2, "Craft 2x Murkrock Pillar at a crafting table.", "Stellt 2x Tr\u00fcbfelss\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_slab_from_murkrock_stonecutting", "copper_inferno:murkrock_slab", "nightslate/murkrock_slab_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_slab", 2, "Stonecutting: cut 2x Murkrock Slab from Murkrock.", "Steins\u00e4ge: 2x Tr\u00fcbfelsstufe aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_stairs_from_murkrock_stonecutting", "copper_inferno:murkrock_stairs", "nightslate/murkrock_stairs_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_stairs", 1, "Stonecutting: cut 1x Murkrock Stairs from Murkrock.", "Steins\u00e4ge: 1x Tr\u00fcbfelstreppe aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_wall_from_murkrock_stonecutting", "copper_inferno:murkrock_wall", "nightslate/murkrock_wall_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_wall", 1, "Stonecutting: cut 1x Murkrock Wall from Murkrock.", "Steins\u00e4ge: 1x Tr\u00fcbfelsmauer aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_murkrock_from_murkrock_stonecutting", "copper_inferno:polished_murkrock", "nightslate/polished_murkrock_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:polished_murkrock", 1, "Stonecutting: cut 1x Polished Murkrock from Murkrock.", "Steins\u00e4ge: 1x Polierten Tr\u00fcbfels aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_murkrock_slab_from_murkrock_stonecutting", "copper_inferno:polished_murkrock_slab", "nightslate/polished_murkrock_slab_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:polished_murkrock_slab", 2, "Stonecutting: cut 2x Polished Murkrock Slab from Murkrock.", "Steins\u00e4ge: 2x Polierte Tr\u00fcbfelsstufe aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_murkrock_stairs_from_murkrock_stonecutting", "copper_inferno:polished_murkrock_stairs", "nightslate/polished_murkrock_stairs_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:polished_murkrock_stairs", 1, "Stonecutting: cut 1x Polished Murkrock Stairs from Murkrock.", "Steins\u00e4ge: 1x Polierte Tr\u00fcbfelstreppe aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_murkrock_wall_from_murkrock_stonecutting", "copper_inferno:polished_murkrock_wall", "nightslate/polished_murkrock_wall_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:polished_murkrock_wall", 1, "Stonecutting: cut 1x Polished Murkrock Wall from Murkrock.", "Steins\u00e4ge: 1x Polierte Tr\u00fcbfelsmauer aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_bricks_from_murkrock_stonecutting", "copper_inferno:murkrock_bricks", "nightslate/murkrock_bricks_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_bricks", 1, "Stonecutting: cut 1x Murkrock Bricks from Murkrock.", "Steins\u00e4ge: 1x Tr\u00fcbfelsziegel aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_brick_slab_from_murkrock_stonecutting", "copper_inferno:murkrock_brick_slab", "nightslate/murkrock_brick_slab_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_brick_slab", 2, "Stonecutting: cut 2x Murkrock Brick Slab from Murkrock.", "Steins\u00e4ge: 2x Tr\u00fcbfelsziegelstufe aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_brick_stairs_from_murkrock_stonecutting", "copper_inferno:murkrock_brick_stairs", "nightslate/murkrock_brick_stairs_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_brick_stairs", 1, "Stonecutting: cut 1x Murkrock Brick Stairs from Murkrock.", "Steins\u00e4ge: 1x Tr\u00fcbfelsziegeltreppe aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_brick_wall_from_murkrock_stonecutting", "copper_inferno:murkrock_brick_wall", "nightslate/murkrock_brick_wall_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_brick_wall", 1, "Stonecutting: cut 1x Murkrock Brick Wall from Murkrock.", "Steins\u00e4ge: 1x Tr\u00fcbfelsziegelmauer aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_tiles_from_murkrock_stonecutting", "copper_inferno:murkrock_tiles", "nightslate/murkrock_tiles_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_tiles", 1, "Stonecutting: cut 1x Murkrock Tiles from Murkrock.", "Steins\u00e4ge: 1x Tr\u00fcbfelsfliesen aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_murkrock_bricks_from_murkrock_stonecutting", "copper_inferno:chiseled_murkrock_bricks", "nightslate/chiseled_murkrock_bricks_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:chiseled_murkrock_bricks", 1, "Stonecutting: cut 1x Chiseled Murkrock Bricks from Murkrock.", "Steins\u00e4ge: 1x Gemei\u00dfelte Tr\u00fcbfelsziegel aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/murkrock_pillar_from_murkrock_stonecutting", "copper_inferno:murkrock_pillar", "nightslate/murkrock_pillar_from_murkrock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:murkrock", "", "", "", ""},
				"copper_inferno:murkrock_pillar", 1, "Stonecutting: cut 1x Murkrock Pillar from Murkrock.", "Steins\u00e4ge: 1x Tr\u00fcbfelss\u00e4ule aus Tr\u00fcbfels schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone", "copper_inferno:cinderdark_stone", "nightslate/cinderdark_stone",
				new String[] {"minecraft:polished_blackstone", "", "minecraft:polished_blackstone", "", "copper_inferno:murkrock", "", "minecraft:polished_blackstone", "", "minecraft:polished_blackstone"},
				"copper_inferno:cinderdark_stone", 4, "Craft 4x Cinderdark Stone at a crafting table.", "Stellt 4x Dunkelzunderstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "nightslate/cinderdark_stone_bricks",
				new String[] {"copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "", "copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_bricks", 4, "Craft 4x Cinderdark Stone Bricks at a crafting table.", "Stellt 4x Dunkelzundersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_tiles", "copper_inferno:cinderdark_stone_tiles", "nightslate/cinderdark_stone_tiles",
				new String[] {"copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "", "copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "", "", "", ""},
				"copper_inferno:cinderdark_stone_tiles", 4, "Craft 4x Cinderdark Stone Tiles at a crafting table.", "Stellt 4x Dunkelzundersteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "nightslate/polished_cinderdark_stone",
				new String[] {"copper_inferno:cinderdark_stone_tiles", "copper_inferno:cinderdark_stone_tiles", "", "copper_inferno:cinderdark_stone_tiles", "copper_inferno:cinderdark_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_cinderdark_stone", 4, "Craft 4x Polished Cinderdark Stone at a crafting table.", "Stellt 4x Polierten Dunkelzunderstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_slab", "copper_inferno:cinderdark_stone_slab", "nightslate/cinderdark_stone_slab",
				new String[] {"copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "", "", "", "", "", ""},
				"copper_inferno:cinderdark_stone_slab", 6, "Craft 6x Cinderdark Stone Slab at a crafting table.", "Stellt 6x Dunkelzundersteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_stairs", "copper_inferno:cinderdark_stone_stairs", "nightslate/cinderdark_stone_stairs",
				new String[] {"copper_inferno:cinderdark_stone", "", "", "copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "", "copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone"},
				"copper_inferno:cinderdark_stone_stairs", 4, "Craft 4x Cinderdark Stone Stairs at a crafting table.", "Stellt 4x Dunkelzundersteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_wall", "copper_inferno:cinderdark_stone_wall", "nightslate/cinderdark_stone_wall",
				new String[] {"copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "copper_inferno:cinderdark_stone", "", "", ""},
				"copper_inferno:cinderdark_stone_wall", 6, "Craft 6x Cinderdark Stone Wall at a crafting table.", "Stellt 6x Dunkelzundersteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_cinderdark_stone_slab", "copper_inferno:polished_cinderdark_stone_slab", "nightslate/polished_cinderdark_stone_slab",
				new String[] {"copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_cinderdark_stone_slab", 6, "Craft 6x Polished Cinderdark Stone Slab at a crafting table.", "Stellt 6x Polierte Dunkelzundersteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_cinderdark_stone_stairs", "copper_inferno:polished_cinderdark_stone_stairs", "nightslate/polished_cinderdark_stone_stairs",
				new String[] {"copper_inferno:polished_cinderdark_stone", "", "", "copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "", "copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone"},
				"copper_inferno:polished_cinderdark_stone_stairs", 4, "Craft 4x Polished Cinderdark Stone Stairs at a crafting table.", "Stellt 4x Polierte Dunkelzundersteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_cinderdark_stone_wall", "copper_inferno:polished_cinderdark_stone_wall", "nightslate/polished_cinderdark_stone_wall",
				new String[] {"copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "copper_inferno:polished_cinderdark_stone", "", "", ""},
				"copper_inferno:polished_cinderdark_stone_wall", 6, "Craft 6x Polished Cinderdark Stone Wall at a crafting table.", "Stellt 6x Polierte Dunkelzundersteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_brick_slab", "copper_inferno:cinderdark_stone_brick_slab", "nightslate/cinderdark_stone_brick_slab",
				new String[] {"copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:cinderdark_stone_brick_slab", 6, "Craft 6x Cinderdark Stone Brick Slab at a crafting table.", "Stellt 6x Dunkelzundersteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_brick_stairs", "copper_inferno:cinderdark_stone_brick_stairs", "nightslate/cinderdark_stone_brick_stairs",
				new String[] {"copper_inferno:cinderdark_stone_bricks", "", "", "copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "", "copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks"},
				"copper_inferno:cinderdark_stone_brick_stairs", 4, "Craft 4x Cinderdark Stone Brick Stairs at a crafting table.", "Stellt 4x Dunkelzundersteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_brick_wall", "copper_inferno:cinderdark_stone_brick_wall", "nightslate/cinderdark_stone_brick_wall",
				new String[] {"copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "copper_inferno:cinderdark_stone_bricks", "", "", ""},
				"copper_inferno:cinderdark_stone_brick_wall", 6, "Craft 6x Cinderdark Stone Brick Wall at a crafting table.", "Stellt 6x Dunkelzundersteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_cinderdark_stone_bricks", "copper_inferno:cracked_cinderdark_stone_bricks", "nightslate/cracked_cinderdark_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_cinderdark_stone_bricks", 1, "Smelting Cinderdark Stone Bricks in a furnace yields Cracked Cinderdark Stone Bricks.", "Dunkelzundersteinziegel im Ofen gebrannt ergibt Rissige Dunkelzundersteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_cinderdark_stone_bricks", "copper_inferno:chiseled_cinderdark_stone_bricks", "nightslate/chiseled_cinderdark_stone_bricks",
				new String[] {"copper_inferno:cinderdark_stone_brick_slab", "", "", "copper_inferno:cinderdark_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_cinderdark_stone_bricks", 1, "Craft 1x Chiseled Cinderdark Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Dunkelzundersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_pillar", "copper_inferno:cinderdark_stone_pillar", "nightslate/cinderdark_stone_pillar",
				new String[] {"copper_inferno:cinderdark_stone_bricks", "", "", "copper_inferno:cinderdark_stone_bricks", "", "", "", "", ""},
				"copper_inferno:cinderdark_stone_pillar", 2, "Craft 2x Cinderdark Stone Pillar at a crafting table.", "Stellt 2x Dunkelzundersteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_slab_from_cinderdark_stone_stonecutting", "copper_inferno:cinderdark_stone_slab", "nightslate/cinderdark_stone_slab_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_slab", 2, "Stonecutting: cut 2x Cinderdark Stone Slab from Cinderdark Stone.", "Steins\u00e4ge: 2x Dunkelzundersteinstufe aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_stairs_from_cinderdark_stone_stonecutting", "copper_inferno:cinderdark_stone_stairs", "nightslate/cinderdark_stone_stairs_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_stairs", 1, "Stonecutting: cut 1x Cinderdark Stone Stairs from Cinderdark Stone.", "Steins\u00e4ge: 1x Dunkelzundersteintreppe aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_wall_from_cinderdark_stone_stonecutting", "copper_inferno:cinderdark_stone_wall", "nightslate/cinderdark_stone_wall_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_wall", 1, "Stonecutting: cut 1x Cinderdark Stone Wall from Cinderdark Stone.", "Steins\u00e4ge: 1x Dunkelzundersteinmauer aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_cinderdark_stone_from_cinderdark_stone_stonecutting", "copper_inferno:polished_cinderdark_stone", "nightslate/polished_cinderdark_stone_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:polished_cinderdark_stone", 1, "Stonecutting: cut 1x Polished Cinderdark Stone from Cinderdark Stone.", "Steins\u00e4ge: 1x Polierten Dunkelzunderstein aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_cinderdark_stone_slab_from_cinderdark_stone_stonecutting", "copper_inferno:polished_cinderdark_stone_slab", "nightslate/polished_cinderdark_stone_slab_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:polished_cinderdark_stone_slab", 2, "Stonecutting: cut 2x Polished Cinderdark Stone Slab from Cinderdark Stone.", "Steins\u00e4ge: 2x Polierte Dunkelzundersteinstufe aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_cinderdark_stone_stairs_from_cinderdark_stone_stonecutting", "copper_inferno:polished_cinderdark_stone_stairs", "nightslate/polished_cinderdark_stone_stairs_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:polished_cinderdark_stone_stairs", 1, "Stonecutting: cut 1x Polished Cinderdark Stone Stairs from Cinderdark Stone.", "Steins\u00e4ge: 1x Polierte Dunkelzundersteintreppe aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_cinderdark_stone_wall_from_cinderdark_stone_stonecutting", "copper_inferno:polished_cinderdark_stone_wall", "nightslate/polished_cinderdark_stone_wall_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:polished_cinderdark_stone_wall", 1, "Stonecutting: cut 1x Polished Cinderdark Stone Wall from Cinderdark Stone.", "Steins\u00e4ge: 1x Polierte Dunkelzundersteinmauer aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_bricks_from_cinderdark_stone_stonecutting", "copper_inferno:cinderdark_stone_bricks", "nightslate/cinderdark_stone_bricks_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_bricks", 1, "Stonecutting: cut 1x Cinderdark Stone Bricks from Cinderdark Stone.", "Steins\u00e4ge: 1x Dunkelzundersteinziegel aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_brick_slab_from_cinderdark_stone_stonecutting", "copper_inferno:cinderdark_stone_brick_slab", "nightslate/cinderdark_stone_brick_slab_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_brick_slab", 2, "Stonecutting: cut 2x Cinderdark Stone Brick Slab from Cinderdark Stone.", "Steins\u00e4ge: 2x Dunkelzundersteinziegelstufe aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_brick_stairs_from_cinderdark_stone_stonecutting", "copper_inferno:cinderdark_stone_brick_stairs", "nightslate/cinderdark_stone_brick_stairs_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_brick_stairs", 1, "Stonecutting: cut 1x Cinderdark Stone Brick Stairs from Cinderdark Stone.", "Steins\u00e4ge: 1x Dunkelzundersteinziegeltreppe aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_brick_wall_from_cinderdark_stone_stonecutting", "copper_inferno:cinderdark_stone_brick_wall", "nightslate/cinderdark_stone_brick_wall_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_brick_wall", 1, "Stonecutting: cut 1x Cinderdark Stone Brick Wall from Cinderdark Stone.", "Steins\u00e4ge: 1x Dunkelzundersteinziegelmauer aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_tiles_from_cinderdark_stone_stonecutting", "copper_inferno:cinderdark_stone_tiles", "nightslate/cinderdark_stone_tiles_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_tiles", 1, "Stonecutting: cut 1x Cinderdark Stone Tiles from Cinderdark Stone.", "Steins\u00e4ge: 1x Dunkelzundersteinfliesen aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_cinderdark_stone_bricks_from_cinderdark_stone_stonecutting", "copper_inferno:chiseled_cinderdark_stone_bricks", "nightslate/chiseled_cinderdark_stone_bricks_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:chiseled_cinderdark_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Cinderdark Stone Bricks from Cinderdark Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Dunkelzundersteinziegel aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cinderdark_stone_pillar_from_cinderdark_stone_stonecutting", "copper_inferno:cinderdark_stone_pillar", "nightslate/cinderdark_stone_pillar_from_cinderdark_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinderdark_stone", "", "", "", ""},
				"copper_inferno:cinderdark_stone_pillar", 1, "Stonecutting: cut 1x Cinderdark Stone Pillar from Cinderdark Stone.", "Steins\u00e4ge: 1x Dunkelzundersteins\u00e4ule aus Dunkelzunderstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone", "copper_inferno:netherveil_stone", "nightslate/netherveil_stone",
				new String[] {"minecraft:netherrack", "", "minecraft:netherrack", "", "copper_inferno:cinderdark_stone", "", "minecraft:netherrack", "", "minecraft:netherrack"},
				"copper_inferno:netherveil_stone", 4, "Craft 4x Netherveil Stone at a crafting table.", "Stellt 4x Netherschleierstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "nightslate/netherveil_stone_bricks",
				new String[] {"copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "", "copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_bricks", 4, "Craft 4x Netherveil Stone Bricks at a crafting table.", "Stellt 4x Netherschleiersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_tiles", "copper_inferno:netherveil_stone_tiles", "nightslate/netherveil_stone_tiles",
				new String[] {"copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "", "copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "", "", "", ""},
				"copper_inferno:netherveil_stone_tiles", 4, "Craft 4x Netherveil Stone Tiles at a crafting table.", "Stellt 4x Netherschleiersteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "nightslate/polished_netherveil_stone",
				new String[] {"copper_inferno:netherveil_stone_tiles", "copper_inferno:netherveil_stone_tiles", "", "copper_inferno:netherveil_stone_tiles", "copper_inferno:netherveil_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_netherveil_stone", 4, "Craft 4x Polished Netherveil Stone at a crafting table.", "Stellt 4x Polierten Netherschleierstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_slab", "copper_inferno:netherveil_stone_slab", "nightslate/netherveil_stone_slab",
				new String[] {"copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "", "", "", "", "", ""},
				"copper_inferno:netherveil_stone_slab", 6, "Craft 6x Netherveil Stone Slab at a crafting table.", "Stellt 6x Netherschleiersteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_stairs", "copper_inferno:netherveil_stone_stairs", "nightslate/netherveil_stone_stairs",
				new String[] {"copper_inferno:netherveil_stone", "", "", "copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "", "copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone"},
				"copper_inferno:netherveil_stone_stairs", 4, "Craft 4x Netherveil Stone Stairs at a crafting table.", "Stellt 4x Netherschleiersteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_wall", "copper_inferno:netherveil_stone_wall", "nightslate/netherveil_stone_wall",
				new String[] {"copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "copper_inferno:netherveil_stone", "", "", ""},
				"copper_inferno:netherveil_stone_wall", 6, "Craft 6x Netherveil Stone Wall at a crafting table.", "Stellt 6x Netherschleiersteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_netherveil_stone_slab", "copper_inferno:polished_netherveil_stone_slab", "nightslate/polished_netherveil_stone_slab",
				new String[] {"copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_netherveil_stone_slab", 6, "Craft 6x Polished Netherveil Stone Slab at a crafting table.", "Stellt 6x Polierte Netherschleiersteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_netherveil_stone_stairs", "copper_inferno:polished_netherveil_stone_stairs", "nightslate/polished_netherveil_stone_stairs",
				new String[] {"copper_inferno:polished_netherveil_stone", "", "", "copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "", "copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone"},
				"copper_inferno:polished_netherveil_stone_stairs", 4, "Craft 4x Polished Netherveil Stone Stairs at a crafting table.", "Stellt 4x Polierte Netherschleiersteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_netherveil_stone_wall", "copper_inferno:polished_netherveil_stone_wall", "nightslate/polished_netherveil_stone_wall",
				new String[] {"copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "copper_inferno:polished_netherveil_stone", "", "", ""},
				"copper_inferno:polished_netherveil_stone_wall", 6, "Craft 6x Polished Netherveil Stone Wall at a crafting table.", "Stellt 6x Polierte Netherschleiersteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_brick_slab", "copper_inferno:netherveil_stone_brick_slab", "nightslate/netherveil_stone_brick_slab",
				new String[] {"copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:netherveil_stone_brick_slab", 6, "Craft 6x Netherveil Stone Brick Slab at a crafting table.", "Stellt 6x Netherschleiersteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_brick_stairs", "copper_inferno:netherveil_stone_brick_stairs", "nightslate/netherveil_stone_brick_stairs",
				new String[] {"copper_inferno:netherveil_stone_bricks", "", "", "copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "", "copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks"},
				"copper_inferno:netherveil_stone_brick_stairs", 4, "Craft 4x Netherveil Stone Brick Stairs at a crafting table.", "Stellt 4x Netherschleiersteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_brick_wall", "copper_inferno:netherveil_stone_brick_wall", "nightslate/netherveil_stone_brick_wall",
				new String[] {"copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "copper_inferno:netherveil_stone_bricks", "", "", ""},
				"copper_inferno:netherveil_stone_brick_wall", 6, "Craft 6x Netherveil Stone Brick Wall at a crafting table.", "Stellt 6x Netherschleiersteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_netherveil_stone_bricks", "copper_inferno:cracked_netherveil_stone_bricks", "nightslate/cracked_netherveil_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_netherveil_stone_bricks", 1, "Smelting Netherveil Stone Bricks in a furnace yields Cracked Netherveil Stone Bricks.", "Netherschleiersteinziegel im Ofen gebrannt ergibt Rissige Netherschleiersteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_netherveil_stone_bricks", "copper_inferno:chiseled_netherveil_stone_bricks", "nightslate/chiseled_netherveil_stone_bricks",
				new String[] {"copper_inferno:netherveil_stone_brick_slab", "", "", "copper_inferno:netherveil_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_netherveil_stone_bricks", 1, "Craft 1x Chiseled Netherveil Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Netherschleiersteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_pillar", "copper_inferno:netherveil_stone_pillar", "nightslate/netherveil_stone_pillar",
				new String[] {"copper_inferno:netherveil_stone_bricks", "", "", "copper_inferno:netherveil_stone_bricks", "", "", "", "", ""},
				"copper_inferno:netherveil_stone_pillar", 2, "Craft 2x Netherveil Stone Pillar at a crafting table.", "Stellt 2x Netherschleiersteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_slab_from_netherveil_stone_stonecutting", "copper_inferno:netherveil_stone_slab", "nightslate/netherveil_stone_slab_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_slab", 2, "Stonecutting: cut 2x Netherveil Stone Slab from Netherveil Stone.", "Steins\u00e4ge: 2x Netherschleiersteinstufe aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_stairs_from_netherveil_stone_stonecutting", "copper_inferno:netherveil_stone_stairs", "nightslate/netherveil_stone_stairs_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_stairs", 1, "Stonecutting: cut 1x Netherveil Stone Stairs from Netherveil Stone.", "Steins\u00e4ge: 1x Netherschleiersteintreppe aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_wall_from_netherveil_stone_stonecutting", "copper_inferno:netherveil_stone_wall", "nightslate/netherveil_stone_wall_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_wall", 1, "Stonecutting: cut 1x Netherveil Stone Wall from Netherveil Stone.", "Steins\u00e4ge: 1x Netherschleiersteinmauer aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_netherveil_stone_from_netherveil_stone_stonecutting", "copper_inferno:polished_netherveil_stone", "nightslate/polished_netherveil_stone_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:polished_netherveil_stone", 1, "Stonecutting: cut 1x Polished Netherveil Stone from Netherveil Stone.", "Steins\u00e4ge: 1x Polierten Netherschleierstein aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_netherveil_stone_slab_from_netherveil_stone_stonecutting", "copper_inferno:polished_netherveil_stone_slab", "nightslate/polished_netherveil_stone_slab_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:polished_netherveil_stone_slab", 2, "Stonecutting: cut 2x Polished Netherveil Stone Slab from Netherveil Stone.", "Steins\u00e4ge: 2x Polierte Netherschleiersteinstufe aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_netherveil_stone_stairs_from_netherveil_stone_stonecutting", "copper_inferno:polished_netherveil_stone_stairs", "nightslate/polished_netherveil_stone_stairs_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:polished_netherveil_stone_stairs", 1, "Stonecutting: cut 1x Polished Netherveil Stone Stairs from Netherveil Stone.", "Steins\u00e4ge: 1x Polierte Netherschleiersteintreppe aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_netherveil_stone_wall_from_netherveil_stone_stonecutting", "copper_inferno:polished_netherveil_stone_wall", "nightslate/polished_netherveil_stone_wall_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:polished_netherveil_stone_wall", 1, "Stonecutting: cut 1x Polished Netherveil Stone Wall from Netherveil Stone.", "Steins\u00e4ge: 1x Polierte Netherschleiersteinmauer aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_bricks_from_netherveil_stone_stonecutting", "copper_inferno:netherveil_stone_bricks", "nightslate/netherveil_stone_bricks_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_bricks", 1, "Stonecutting: cut 1x Netherveil Stone Bricks from Netherveil Stone.", "Steins\u00e4ge: 1x Netherschleiersteinziegel aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_brick_slab_from_netherveil_stone_stonecutting", "copper_inferno:netherveil_stone_brick_slab", "nightslate/netherveil_stone_brick_slab_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_brick_slab", 2, "Stonecutting: cut 2x Netherveil Stone Brick Slab from Netherveil Stone.", "Steins\u00e4ge: 2x Netherschleiersteinziegelstufe aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_brick_stairs_from_netherveil_stone_stonecutting", "copper_inferno:netherveil_stone_brick_stairs", "nightslate/netherveil_stone_brick_stairs_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_brick_stairs", 1, "Stonecutting: cut 1x Netherveil Stone Brick Stairs from Netherveil Stone.", "Steins\u00e4ge: 1x Netherschleiersteinziegeltreppe aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_brick_wall_from_netherveil_stone_stonecutting", "copper_inferno:netherveil_stone_brick_wall", "nightslate/netherveil_stone_brick_wall_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_brick_wall", 1, "Stonecutting: cut 1x Netherveil Stone Brick Wall from Netherveil Stone.", "Steins\u00e4ge: 1x Netherschleiersteinziegelmauer aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_tiles_from_netherveil_stone_stonecutting", "copper_inferno:netherveil_stone_tiles", "nightslate/netherveil_stone_tiles_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_tiles", 1, "Stonecutting: cut 1x Netherveil Stone Tiles from Netherveil Stone.", "Steins\u00e4ge: 1x Netherschleiersteinfliesen aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_netherveil_stone_bricks_from_netherveil_stone_stonecutting", "copper_inferno:chiseled_netherveil_stone_bricks", "nightslate/chiseled_netherveil_stone_bricks_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:chiseled_netherveil_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Netherveil Stone Bricks from Netherveil Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Netherschleiersteinziegel aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/netherveil_stone_pillar_from_netherveil_stone_stonecutting", "copper_inferno:netherveil_stone_pillar", "nightslate/netherveil_stone_pillar_from_netherveil_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:netherveil_stone", "", "", "", ""},
				"copper_inferno:netherveil_stone_pillar", 1, "Stonecutting: cut 1x Netherveil Stone Pillar from Netherveil Stone.", "Steins\u00e4ge: 1x Netherschleiersteins\u00e4ule aus Netherschleierstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone", "copper_inferno:obscura_stone", "nightslate/obscura_stone",
				new String[] {"minecraft:sculk", "", "minecraft:sculk", "", "copper_inferno:netherveil_stone", "", "minecraft:sculk", "", "minecraft:sculk"},
				"copper_inferno:obscura_stone", 4, "Craft 4x Obscura Stone at a crafting table.", "Stellt 4x Obskurastein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "nightslate/obscura_stone_bricks",
				new String[] {"copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "", "copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_bricks", 4, "Craft 4x Obscura Stone Bricks at a crafting table.", "Stellt 4x Obskurasteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_tiles", "copper_inferno:obscura_stone_tiles", "nightslate/obscura_stone_tiles",
				new String[] {"copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "", "copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "", "", "", ""},
				"copper_inferno:obscura_stone_tiles", 4, "Craft 4x Obscura Stone Tiles at a crafting table.", "Stellt 4x Obskurasteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_obscura_stone", "copper_inferno:polished_obscura_stone", "nightslate/polished_obscura_stone",
				new String[] {"copper_inferno:obscura_stone_tiles", "copper_inferno:obscura_stone_tiles", "", "copper_inferno:obscura_stone_tiles", "copper_inferno:obscura_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_obscura_stone", 4, "Craft 4x Polished Obscura Stone at a crafting table.", "Stellt 4x Polierten Obskurastein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_slab", "copper_inferno:obscura_stone_slab", "nightslate/obscura_stone_slab",
				new String[] {"copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "", "", "", "", "", ""},
				"copper_inferno:obscura_stone_slab", 6, "Craft 6x Obscura Stone Slab at a crafting table.", "Stellt 6x Obskurasteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_stairs", "copper_inferno:obscura_stone_stairs", "nightslate/obscura_stone_stairs",
				new String[] {"copper_inferno:obscura_stone", "", "", "copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "", "copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "copper_inferno:obscura_stone"},
				"copper_inferno:obscura_stone_stairs", 4, "Craft 4x Obscura Stone Stairs at a crafting table.", "Stellt 4x Obskurasteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_wall", "copper_inferno:obscura_stone_wall", "nightslate/obscura_stone_wall",
				new String[] {"copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "copper_inferno:obscura_stone", "", "", ""},
				"copper_inferno:obscura_stone_wall", 6, "Craft 6x Obscura Stone Wall at a crafting table.", "Stellt 6x Obskurasteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_obscura_stone_slab", "copper_inferno:polished_obscura_stone_slab", "nightslate/polished_obscura_stone_slab",
				new String[] {"copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_obscura_stone_slab", 6, "Craft 6x Polished Obscura Stone Slab at a crafting table.", "Stellt 6x Polierte Obskurasteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_obscura_stone_stairs", "copper_inferno:polished_obscura_stone_stairs", "nightslate/polished_obscura_stone_stairs",
				new String[] {"copper_inferno:polished_obscura_stone", "", "", "copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone", "", "copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone"},
				"copper_inferno:polished_obscura_stone_stairs", 4, "Craft 4x Polished Obscura Stone Stairs at a crafting table.", "Stellt 4x Polierte Obskurasteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_obscura_stone_wall", "copper_inferno:polished_obscura_stone_wall", "nightslate/polished_obscura_stone_wall",
				new String[] {"copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone", "copper_inferno:polished_obscura_stone", "", "", ""},
				"copper_inferno:polished_obscura_stone_wall", 6, "Craft 6x Polished Obscura Stone Wall at a crafting table.", "Stellt 6x Polierte Obskurasteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_brick_slab", "copper_inferno:obscura_stone_brick_slab", "nightslate/obscura_stone_brick_slab",
				new String[] {"copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:obscura_stone_brick_slab", 6, "Craft 6x Obscura Stone Brick Slab at a crafting table.", "Stellt 6x Obskurasteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_brick_stairs", "copper_inferno:obscura_stone_brick_stairs", "nightslate/obscura_stone_brick_stairs",
				new String[] {"copper_inferno:obscura_stone_bricks", "", "", "copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "", "copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks"},
				"copper_inferno:obscura_stone_brick_stairs", 4, "Craft 4x Obscura Stone Brick Stairs at a crafting table.", "Stellt 4x Obskurasteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_brick_wall", "copper_inferno:obscura_stone_brick_wall", "nightslate/obscura_stone_brick_wall",
				new String[] {"copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "copper_inferno:obscura_stone_bricks", "", "", ""},
				"copper_inferno:obscura_stone_brick_wall", 6, "Craft 6x Obscura Stone Brick Wall at a crafting table.", "Stellt 6x Obskurasteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/cracked_obscura_stone_bricks", "copper_inferno:cracked_obscura_stone_bricks", "nightslate/cracked_obscura_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_obscura_stone_bricks", 1, "Smelting Obscura Stone Bricks in a furnace yields Cracked Obscura Stone Bricks.", "Obskurasteinziegel im Ofen gebrannt ergibt Rissige Obskurasteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_obscura_stone_bricks", "copper_inferno:chiseled_obscura_stone_bricks", "nightslate/chiseled_obscura_stone_bricks",
				new String[] {"copper_inferno:obscura_stone_brick_slab", "", "", "copper_inferno:obscura_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_obscura_stone_bricks", 1, "Craft 1x Chiseled Obscura Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Obskurasteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_pillar", "copper_inferno:obscura_stone_pillar", "nightslate/obscura_stone_pillar",
				new String[] {"copper_inferno:obscura_stone_bricks", "", "", "copper_inferno:obscura_stone_bricks", "", "", "", "", ""},
				"copper_inferno:obscura_stone_pillar", 2, "Craft 2x Obscura Stone Pillar at a crafting table.", "Stellt 2x Obskurasteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_slab_from_obscura_stone_stonecutting", "copper_inferno:obscura_stone_slab", "nightslate/obscura_stone_slab_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_slab", 2, "Stonecutting: cut 2x Obscura Stone Slab from Obscura Stone.", "Steins\u00e4ge: 2x Obskurasteinstufe aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_stairs_from_obscura_stone_stonecutting", "copper_inferno:obscura_stone_stairs", "nightslate/obscura_stone_stairs_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_stairs", 1, "Stonecutting: cut 1x Obscura Stone Stairs from Obscura Stone.", "Steins\u00e4ge: 1x Obskurasteintreppe aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_wall_from_obscura_stone_stonecutting", "copper_inferno:obscura_stone_wall", "nightslate/obscura_stone_wall_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_wall", 1, "Stonecutting: cut 1x Obscura Stone Wall from Obscura Stone.", "Steins\u00e4ge: 1x Obskurasteinmauer aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_obscura_stone_from_obscura_stone_stonecutting", "copper_inferno:polished_obscura_stone", "nightslate/polished_obscura_stone_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:polished_obscura_stone", 1, "Stonecutting: cut 1x Polished Obscura Stone from Obscura Stone.", "Steins\u00e4ge: 1x Polierten Obskurastein aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_obscura_stone_slab_from_obscura_stone_stonecutting", "copper_inferno:polished_obscura_stone_slab", "nightslate/polished_obscura_stone_slab_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:polished_obscura_stone_slab", 2, "Stonecutting: cut 2x Polished Obscura Stone Slab from Obscura Stone.", "Steins\u00e4ge: 2x Polierte Obskurasteinstufe aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_obscura_stone_stairs_from_obscura_stone_stonecutting", "copper_inferno:polished_obscura_stone_stairs", "nightslate/polished_obscura_stone_stairs_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:polished_obscura_stone_stairs", 1, "Stonecutting: cut 1x Polished Obscura Stone Stairs from Obscura Stone.", "Steins\u00e4ge: 1x Polierte Obskurasteintreppe aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/polished_obscura_stone_wall_from_obscura_stone_stonecutting", "copper_inferno:polished_obscura_stone_wall", "nightslate/polished_obscura_stone_wall_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:polished_obscura_stone_wall", 1, "Stonecutting: cut 1x Polished Obscura Stone Wall from Obscura Stone.", "Steins\u00e4ge: 1x Polierte Obskurasteinmauer aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_bricks_from_obscura_stone_stonecutting", "copper_inferno:obscura_stone_bricks", "nightslate/obscura_stone_bricks_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_bricks", 1, "Stonecutting: cut 1x Obscura Stone Bricks from Obscura Stone.", "Steins\u00e4ge: 1x Obskurasteinziegel aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_brick_slab_from_obscura_stone_stonecutting", "copper_inferno:obscura_stone_brick_slab", "nightslate/obscura_stone_brick_slab_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_brick_slab", 2, "Stonecutting: cut 2x Obscura Stone Brick Slab from Obscura Stone.", "Steins\u00e4ge: 2x Obskurasteinziegelstufe aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_brick_stairs_from_obscura_stone_stonecutting", "copper_inferno:obscura_stone_brick_stairs", "nightslate/obscura_stone_brick_stairs_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_brick_stairs", 1, "Stonecutting: cut 1x Obscura Stone Brick Stairs from Obscura Stone.", "Steins\u00e4ge: 1x Obskurasteinziegeltreppe aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_brick_wall_from_obscura_stone_stonecutting", "copper_inferno:obscura_stone_brick_wall", "nightslate/obscura_stone_brick_wall_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_brick_wall", 1, "Stonecutting: cut 1x Obscura Stone Brick Wall from Obscura Stone.", "Steins\u00e4ge: 1x Obskurasteinziegelmauer aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_tiles_from_obscura_stone_stonecutting", "copper_inferno:obscura_stone_tiles", "nightslate/obscura_stone_tiles_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_tiles", 1, "Stonecutting: cut 1x Obscura Stone Tiles from Obscura Stone.", "Steins\u00e4ge: 1x Obskurasteinfliesen aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/chiseled_obscura_stone_bricks_from_obscura_stone_stonecutting", "copper_inferno:chiseled_obscura_stone_bricks", "nightslate/chiseled_obscura_stone_bricks_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:chiseled_obscura_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Obscura Stone Bricks from Obscura Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Obskurasteinziegel aus Obskurastein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "nightslate/obscura_stone_pillar_from_obscura_stone_stonecutting", "copper_inferno:obscura_stone_pillar", "nightslate/obscura_stone_pillar_from_obscura_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obscura_stone", "", "", "", ""},
				"copper_inferno:obscura_stone_pillar", 1, "Stonecutting: cut 1x Obscura Stone Pillar from Obscura Stone.", "Steins\u00e4ge: 1x Obskurasteins\u00e4ule aus Obskurastein schneiden."));
	}
}
