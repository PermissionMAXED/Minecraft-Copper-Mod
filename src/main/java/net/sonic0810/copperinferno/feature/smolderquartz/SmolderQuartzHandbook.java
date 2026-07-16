package net.sonic0810.copperinferno.feature.smolderquartz;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Smolder Quartz block set: one "blocks" overview per cube
 * family plus one recipe page for every JSON under
 * {@code data/copper_inferno/recipe/smolderquartz/} (crafting, smelting and
 * stonecutting). Entry texts and grids mirror the recipe JSONs emitted by
 * {@code devtools/gen/smolderquartz_gen.py}; {@code devtools/check_handbook.py} parses
 * the inline {@code new HandbookEntry(...)} literals positionally, so keep them inline.
 */
final class SmolderQuartzHandbook {
	private SmolderQuartzHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_smolder_quartz", "copper_inferno:smolder_quartz", null,
				null,
				null, 0, "The Smolder Quartz family for Inferno builds: block, stairs, slab and wall.", "Die Schwelquarz-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", null,
				null,
				null, 0, "The Polished Smolder Quartz family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Schwelquarz f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", null,
				null,
				null, 0, "The Smolder Quartz Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Schwelquarzziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_cinder_crystal", "copper_inferno:cinder_crystal", null,
				null,
				null, 0, "The Cinder Crystal family for Inferno builds: block, stairs, slab and wall.", "Die Zunderkristall-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", null,
				null,
				null, 0, "The Polished Cinder Crystal family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Zunderkristall f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", null,
				null,
				null, 0, "The Cinder Crystal Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Zunderkristallziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_flame_opal", "copper_inferno:flame_opal", null,
				null,
				null, 0, "The Flame Opal family for Inferno builds: block, stairs, slab and wall.", "Die Flammenopal-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_flame_opal", "copper_inferno:polished_flame_opal", null,
				null,
				null, 0, "The Polished Flame Opal family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Flammenopal f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_flame_opal_bricks", "copper_inferno:flame_opal_bricks", null,
				null,
				null, 0, "The Flame Opal Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Flammenopalziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_ember_prism", "copper_inferno:ember_prism", null,
				null,
				null, 0, "The Ember Prism family for Inferno builds: block, stairs, slab and wall.", "Die Glutprisma-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_ember_prism", "copper_inferno:polished_ember_prism", null,
				null,
				null, 0, "The Polished Ember Prism family for Inferno builds: block, stairs, slab and wall.", "Die Familie Poliertes Glutprisma f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_ember_prism_bricks", "copper_inferno:ember_prism_bricks", null,
				null,
				null, 0, "The Ember Prism Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Glutprismenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_ashglass_stone", "copper_inferno:ashglass_stone", null,
				null,
				null, 0, "The Ashglass Stone family for Inferno builds: block, stairs, slab and wall.", "Die Aschglasstein-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", null,
				null,
				null, 0, "The Polished Ashglass Stone family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Aschglasstein f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", null,
				null,
				null, 0, "The Ashglass Stone Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Aschglassteinziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_glowspar", "copper_inferno:glowspar", null,
				null,
				null, 0, "The Glowspar family for Inferno builds: block, stairs, slab and wall.", "Die Leuchtspat-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_glowspar", "copper_inferno:polished_glowspar", null,
				null,
				null, 0, "The Polished Glowspar family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Leuchtspat f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_glowspar_bricks", "copper_inferno:glowspar_bricks", null,
				null,
				null, 0, "The Glowspar Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Leuchtspatziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_heatlens_stone", "copper_inferno:heatlens_stone", null,
				null,
				null, 0, "The Heatlens Stone family for Inferno builds: block, stairs, slab and wall.", "Die Hitzelinsenstein-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", null,
				null,
				null, 0, "The Polished Heatlens Stone family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Hitzelinsenstein f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", null,
				null,
				null, 0, "The Heatlens Stone Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Hitzelinsensteinziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_magma_geode", "copper_inferno:magma_geode", null,
				null,
				null, 0, "The Magma Geode family for Inferno builds: block, stairs, slab and wall.", "Die Magmageode-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_magma_geode", "copper_inferno:polished_magma_geode", null,
				null,
				null, 0, "The Polished Magma Geode family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Magmageode f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_magma_geode_bricks", "copper_inferno:magma_geode_bricks", null,
				null,
				null, 0, "The Magma Geode Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Magmageodenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_pyrite_crystal", "copper_inferno:pyrite_crystal", null,
				null,
				null, 0, "The Pyrite Crystal family for Inferno builds: block, stairs, slab and wall.", "Die Pyritkristall-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", null,
				null,
				null, 0, "The Polished Pyrite Crystal family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Pyritkristall f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", null,
				null,
				null, 0, "The Pyrite Crystal Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Pyritkristallziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_sulfur_crystal", "copper_inferno:sulfur_crystal", null,
				null,
				null, 0, "The Sulfur Crystal family for Inferno builds: block, stairs, slab and wall.", "Die Schwefelkristall-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", null,
				null,
				null, 0, "The Polished Sulfur Crystal family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Schwefelkristall f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", null,
				null,
				null, 0, "The Sulfur Crystal Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Schwefelkristallziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_obsid_glassblock", "copper_inferno:obsid_glassblock", null,
				null,
				null, 0, "The Obsid Glassblock family for Inferno builds: block, stairs, slab and wall.", "Die Obsidglasblock-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", null,
				null,
				null, 0, "The Polished Obsid Glassblock family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Obsidglasblock f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", null,
				null,
				null, 0, "The Obsid Glassblock Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Obsidglasziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_ember_amber", "copper_inferno:ember_amber", null,
				null,
				null, 0, "The Ember Amber family for Inferno builds: block, stairs, slab and wall.", "Die Glutbernstein-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_ember_amber", "copper_inferno:polished_ember_amber", null,
				null,
				null, 0, "The Polished Ember Amber family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Glutbernstein f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_ember_amber_bricks", "copper_inferno:ember_amber_bricks", null,
				null,
				null, 0, "The Ember Amber Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Glutbernsteinziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_fire_agate", "copper_inferno:fire_agate", null,
				null,
				null, 0, "The Fire Agate family for Inferno builds: block, stairs, slab and wall.", "Die Feuerachat-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_fire_agate", "copper_inferno:polished_fire_agate", null,
				null,
				null, 0, "The Polished Fire Agate family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierter Feuerachat f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_fire_agate_bricks", "copper_inferno:fire_agate_bricks", null,
				null,
				null, 0, "The Fire Agate Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Feuerachatziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_lava_pearl", "copper_inferno:lava_pearl", null,
				null,
				null, 0, "The Lava Pearl family for Inferno builds: block, stairs, slab and wall.", "Die Lavaperle-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_polished_lava_pearl", "copper_inferno:polished_lava_pearl", null,
				null,
				null, 0, "The Polished Lava Pearl family for Inferno builds: block, stairs, slab and wall.", "Die Familie Polierte Lavaperle f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/family_lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", null,
				null,
				null, 0, "The Lava Pearl Bricks family for Inferno builds: block, stairs, slab and wall.", "Die Lavaperlenziegel-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz", "copper_inferno:smolder_quartz", "smolderquartz/smolder_quartz",
				new String[] {"minecraft:quartz", "", "minecraft:quartz", "", "copper_inferno:smolder_crystal", "", "minecraft:quartz", "", "minecraft:quartz"},
				"copper_inferno:smolder_quartz", 4, "Craft 4x Smolder Quartz at a crafting table.", "Stellt 4x Schwelquarz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "smolderquartz/smolder_quartz_bricks",
				new String[] {"copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "", "copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_bricks", 4, "Craft 4x Smolder Quartz Bricks at a crafting table.", "Stellt 4x Schwelquarzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_tiles", "copper_inferno:smolder_quartz_tiles", "smolderquartz/smolder_quartz_tiles",
				new String[] {"copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "", "copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "", "", "", ""},
				"copper_inferno:smolder_quartz_tiles", 4, "Craft 4x Smolder Quartz Tiles at a crafting table.", "Stellt 4x Schwelquarzfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "smolderquartz/polished_smolder_quartz",
				new String[] {"copper_inferno:smolder_quartz_tiles", "copper_inferno:smolder_quartz_tiles", "", "copper_inferno:smolder_quartz_tiles", "copper_inferno:smolder_quartz_tiles", "", "", "", ""},
				"copper_inferno:polished_smolder_quartz", 4, "Craft 4x Polished Smolder Quartz at a crafting table.", "Stellt 4x Polierten Schwelquarz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_slab", "copper_inferno:smolder_quartz_slab", "smolderquartz/smolder_quartz_slab",
				new String[] {"copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "", "", "", "", "", ""},
				"copper_inferno:smolder_quartz_slab", 6, "Craft 6x Smolder Quartz Slab at a crafting table.", "Stellt 6x Schwelquarzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_stairs", "copper_inferno:smolder_quartz_stairs", "smolderquartz/smolder_quartz_stairs",
				new String[] {"copper_inferno:smolder_quartz", "", "", "copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "", "copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz"},
				"copper_inferno:smolder_quartz_stairs", 4, "Craft 4x Smolder Quartz Stairs at a crafting table.", "Stellt 4x Schwelquarztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_wall", "copper_inferno:smolder_quartz_wall", "smolderquartz/smolder_quartz_wall",
				new String[] {"copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "copper_inferno:smolder_quartz", "", "", ""},
				"copper_inferno:smolder_quartz_wall", 6, "Craft 6x Smolder Quartz Wall at a crafting table.", "Stellt 6x Schwelquarzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_smolder_quartz_slab", "copper_inferno:polished_smolder_quartz_slab", "smolderquartz/polished_smolder_quartz_slab",
				new String[] {"copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "", "", "", "", "", ""},
				"copper_inferno:polished_smolder_quartz_slab", 6, "Craft 6x Polished Smolder Quartz Slab at a crafting table.", "Stellt 6x Polierte Schwelquarzstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_smolder_quartz_stairs", "copper_inferno:polished_smolder_quartz_stairs", "smolderquartz/polished_smolder_quartz_stairs",
				new String[] {"copper_inferno:polished_smolder_quartz", "", "", "copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "", "copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz"},
				"copper_inferno:polished_smolder_quartz_stairs", 4, "Craft 4x Polished Smolder Quartz Stairs at a crafting table.", "Stellt 4x Polierte Schwelquarztreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_smolder_quartz_wall", "copper_inferno:polished_smolder_quartz_wall", "smolderquartz/polished_smolder_quartz_wall",
				new String[] {"copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "copper_inferno:polished_smolder_quartz", "", "", ""},
				"copper_inferno:polished_smolder_quartz_wall", 6, "Craft 6x Polished Smolder Quartz Wall at a crafting table.", "Stellt 6x Polierte Schwelquarzmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_brick_slab", "copper_inferno:smolder_quartz_brick_slab", "smolderquartz/smolder_quartz_brick_slab",
				new String[] {"copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "", "", "", "", "", ""},
				"copper_inferno:smolder_quartz_brick_slab", 6, "Craft 6x Smolder Quartz Brick Slab at a crafting table.", "Stellt 6x Schwelquarzziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_brick_stairs", "copper_inferno:smolder_quartz_brick_stairs", "smolderquartz/smolder_quartz_brick_stairs",
				new String[] {"copper_inferno:smolder_quartz_bricks", "", "", "copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "", "copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks"},
				"copper_inferno:smolder_quartz_brick_stairs", 4, "Craft 4x Smolder Quartz Brick Stairs at a crafting table.", "Stellt 4x Schwelquarzziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_brick_wall", "copper_inferno:smolder_quartz_brick_wall", "smolderquartz/smolder_quartz_brick_wall",
				new String[] {"copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "copper_inferno:smolder_quartz_bricks", "", "", ""},
				"copper_inferno:smolder_quartz_brick_wall", 6, "Craft 6x Smolder Quartz Brick Wall at a crafting table.", "Stellt 6x Schwelquarzziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_smolder_quartz_bricks", "copper_inferno:cracked_smolder_quartz_bricks", "smolderquartz/cracked_smolder_quartz_bricks",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz_bricks", "", "", "", ""},
				"copper_inferno:cracked_smolder_quartz_bricks", 1, "Smelting Smolder Quartz Bricks in a furnace yields Cracked Smolder Quartz Bricks.", "Schwelquarzziegel im Ofen gebrannt ergibt Rissige Schwelquarzziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_smolder_quartz_bricks", "copper_inferno:chiseled_smolder_quartz_bricks", "smolderquartz/chiseled_smolder_quartz_bricks",
				new String[] {"copper_inferno:smolder_quartz_brick_slab", "", "", "copper_inferno:smolder_quartz_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_smolder_quartz_bricks", 1, "Craft 1x Chiseled Smolder Quartz Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schwelquarzziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_pillar", "copper_inferno:smolder_quartz_pillar", "smolderquartz/smolder_quartz_pillar",
				new String[] {"copper_inferno:smolder_quartz_bricks", "", "", "copper_inferno:smolder_quartz_bricks", "", "", "", "", ""},
				"copper_inferno:smolder_quartz_pillar", 2, "Craft 2x Smolder Quartz Pillar at a crafting table.", "Stellt 2x Schwelquarzs\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_slab_from_smolder_quartz_stonecutting", "copper_inferno:smolder_quartz_slab", "smolderquartz/smolder_quartz_slab_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_slab", 2, "Stonecutting: cut 2x Smolder Quartz Slab from Smolder Quartz.", "Steins\u00e4ge: 2x Schwelquarzstufe aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_stairs_from_smolder_quartz_stonecutting", "copper_inferno:smolder_quartz_stairs", "smolderquartz/smolder_quartz_stairs_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_stairs", 1, "Stonecutting: cut 1x Smolder Quartz Stairs from Smolder Quartz.", "Steins\u00e4ge: 1x Schwelquarztreppe aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_wall_from_smolder_quartz_stonecutting", "copper_inferno:smolder_quartz_wall", "smolderquartz/smolder_quartz_wall_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_wall", 1, "Stonecutting: cut 1x Smolder Quartz Wall from Smolder Quartz.", "Steins\u00e4ge: 1x Schwelquarzmauer aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_smolder_quartz_from_smolder_quartz_stonecutting", "copper_inferno:polished_smolder_quartz", "smolderquartz/polished_smolder_quartz_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:polished_smolder_quartz", 1, "Stonecutting: cut 1x Polished Smolder Quartz from Smolder Quartz.", "Steins\u00e4ge: 1x Polierten Schwelquarz aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_smolder_quartz_slab_from_smolder_quartz_stonecutting", "copper_inferno:polished_smolder_quartz_slab", "smolderquartz/polished_smolder_quartz_slab_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:polished_smolder_quartz_slab", 2, "Stonecutting: cut 2x Polished Smolder Quartz Slab from Smolder Quartz.", "Steins\u00e4ge: 2x Polierte Schwelquarzstufe aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_smolder_quartz_stairs_from_smolder_quartz_stonecutting", "copper_inferno:polished_smolder_quartz_stairs", "smolderquartz/polished_smolder_quartz_stairs_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:polished_smolder_quartz_stairs", 1, "Stonecutting: cut 1x Polished Smolder Quartz Stairs from Smolder Quartz.", "Steins\u00e4ge: 1x Polierte Schwelquarztreppe aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_smolder_quartz_wall_from_smolder_quartz_stonecutting", "copper_inferno:polished_smolder_quartz_wall", "smolderquartz/polished_smolder_quartz_wall_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:polished_smolder_quartz_wall", 1, "Stonecutting: cut 1x Polished Smolder Quartz Wall from Smolder Quartz.", "Steins\u00e4ge: 1x Polierte Schwelquarzmauer aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_bricks_from_smolder_quartz_stonecutting", "copper_inferno:smolder_quartz_bricks", "smolderquartz/smolder_quartz_bricks_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_bricks", 1, "Stonecutting: cut 1x Smolder Quartz Bricks from Smolder Quartz.", "Steins\u00e4ge: 1x Schwelquarzziegel aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_brick_slab_from_smolder_quartz_stonecutting", "copper_inferno:smolder_quartz_brick_slab", "smolderquartz/smolder_quartz_brick_slab_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_brick_slab", 2, "Stonecutting: cut 2x Smolder Quartz Brick Slab from Smolder Quartz.", "Steins\u00e4ge: 2x Schwelquarzziegelstufe aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_brick_stairs_from_smolder_quartz_stonecutting", "copper_inferno:smolder_quartz_brick_stairs", "smolderquartz/smolder_quartz_brick_stairs_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_brick_stairs", 1, "Stonecutting: cut 1x Smolder Quartz Brick Stairs from Smolder Quartz.", "Steins\u00e4ge: 1x Schwelquarzziegeltreppe aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_brick_wall_from_smolder_quartz_stonecutting", "copper_inferno:smolder_quartz_brick_wall", "smolderquartz/smolder_quartz_brick_wall_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_brick_wall", 1, "Stonecutting: cut 1x Smolder Quartz Brick Wall from Smolder Quartz.", "Steins\u00e4ge: 1x Schwelquarzziegelmauer aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_tiles_from_smolder_quartz_stonecutting", "copper_inferno:smolder_quartz_tiles", "smolderquartz/smolder_quartz_tiles_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_tiles", 1, "Stonecutting: cut 1x Smolder Quartz Tiles from Smolder Quartz.", "Steins\u00e4ge: 1x Schwelquarzfliesen aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_smolder_quartz_bricks_from_smolder_quartz_stonecutting", "copper_inferno:chiseled_smolder_quartz_bricks", "smolderquartz/chiseled_smolder_quartz_bricks_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:chiseled_smolder_quartz_bricks", 1, "Stonecutting: cut 1x Chiseled Smolder Quartz Bricks from Smolder Quartz.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schwelquarzziegel aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/smolder_quartz_pillar_from_smolder_quartz_stonecutting", "copper_inferno:smolder_quartz_pillar", "smolderquartz/smolder_quartz_pillar_from_smolder_quartz_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smolder_quartz", "", "", "", ""},
				"copper_inferno:smolder_quartz_pillar", 1, "Stonecutting: cut 1x Smolder Quartz Pillar from Smolder Quartz.", "Steins\u00e4ge: 1x Schwelquarzs\u00e4ule aus Schwelquarz schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal", "copper_inferno:cinder_crystal", "smolderquartz/cinder_crystal",
				new String[] {"minecraft:charcoal", "", "minecraft:charcoal", "", "copper_inferno:smolder_quartz", "", "minecraft:charcoal", "", "minecraft:charcoal"},
				"copper_inferno:cinder_crystal", 4, "Craft 4x Cinder Crystal at a crafting table.", "Stellt 4x Zunderkristall an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "smolderquartz/cinder_crystal_bricks",
				new String[] {"copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "", "copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_bricks", 4, "Craft 4x Cinder Crystal Bricks at a crafting table.", "Stellt 4x Zunderkristallziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_tiles", "copper_inferno:cinder_crystal_tiles", "smolderquartz/cinder_crystal_tiles",
				new String[] {"copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "", "copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "", "", "", ""},
				"copper_inferno:cinder_crystal_tiles", 4, "Craft 4x Cinder Crystal Tiles at a crafting table.", "Stellt 4x Zunderkristallfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "smolderquartz/polished_cinder_crystal",
				new String[] {"copper_inferno:cinder_crystal_tiles", "copper_inferno:cinder_crystal_tiles", "", "copper_inferno:cinder_crystal_tiles", "copper_inferno:cinder_crystal_tiles", "", "", "", ""},
				"copper_inferno:polished_cinder_crystal", 4, "Craft 4x Polished Cinder Crystal at a crafting table.", "Stellt 4x Polierten Zunderkristall an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_slab", "copper_inferno:cinder_crystal_slab", "smolderquartz/cinder_crystal_slab",
				new String[] {"copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "", "", "", "", "", ""},
				"copper_inferno:cinder_crystal_slab", 6, "Craft 6x Cinder Crystal Slab at a crafting table.", "Stellt 6x Zunderkristallstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_stairs", "copper_inferno:cinder_crystal_stairs", "smolderquartz/cinder_crystal_stairs",
				new String[] {"copper_inferno:cinder_crystal", "", "", "copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "", "copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal"},
				"copper_inferno:cinder_crystal_stairs", 4, "Craft 4x Cinder Crystal Stairs at a crafting table.", "Stellt 4x Zunderkristalltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_wall", "copper_inferno:cinder_crystal_wall", "smolderquartz/cinder_crystal_wall",
				new String[] {"copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "copper_inferno:cinder_crystal", "", "", ""},
				"copper_inferno:cinder_crystal_wall", 6, "Craft 6x Cinder Crystal Wall at a crafting table.", "Stellt 6x Zunderkristallmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_cinder_crystal_slab", "copper_inferno:polished_cinder_crystal_slab", "smolderquartz/polished_cinder_crystal_slab",
				new String[] {"copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "", "", "", "", "", ""},
				"copper_inferno:polished_cinder_crystal_slab", 6, "Craft 6x Polished Cinder Crystal Slab at a crafting table.", "Stellt 6x Polierte Zunderkristallstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_cinder_crystal_stairs", "copper_inferno:polished_cinder_crystal_stairs", "smolderquartz/polished_cinder_crystal_stairs",
				new String[] {"copper_inferno:polished_cinder_crystal", "", "", "copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "", "copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal"},
				"copper_inferno:polished_cinder_crystal_stairs", 4, "Craft 4x Polished Cinder Crystal Stairs at a crafting table.", "Stellt 4x Polierte Zunderkristalltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_cinder_crystal_wall", "copper_inferno:polished_cinder_crystal_wall", "smolderquartz/polished_cinder_crystal_wall",
				new String[] {"copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "copper_inferno:polished_cinder_crystal", "", "", ""},
				"copper_inferno:polished_cinder_crystal_wall", 6, "Craft 6x Polished Cinder Crystal Wall at a crafting table.", "Stellt 6x Polierte Zunderkristallmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_brick_slab", "copper_inferno:cinder_crystal_brick_slab", "smolderquartz/cinder_crystal_brick_slab",
				new String[] {"copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "", "", "", "", "", ""},
				"copper_inferno:cinder_crystal_brick_slab", 6, "Craft 6x Cinder Crystal Brick Slab at a crafting table.", "Stellt 6x Zunderkristallziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_brick_stairs", "copper_inferno:cinder_crystal_brick_stairs", "smolderquartz/cinder_crystal_brick_stairs",
				new String[] {"copper_inferno:cinder_crystal_bricks", "", "", "copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "", "copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks"},
				"copper_inferno:cinder_crystal_brick_stairs", 4, "Craft 4x Cinder Crystal Brick Stairs at a crafting table.", "Stellt 4x Zunderkristallziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_brick_wall", "copper_inferno:cinder_crystal_brick_wall", "smolderquartz/cinder_crystal_brick_wall",
				new String[] {"copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "copper_inferno:cinder_crystal_bricks", "", "", ""},
				"copper_inferno:cinder_crystal_brick_wall", 6, "Craft 6x Cinder Crystal Brick Wall at a crafting table.", "Stellt 6x Zunderkristallziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_cinder_crystal_bricks", "copper_inferno:cracked_cinder_crystal_bricks", "smolderquartz/cracked_cinder_crystal_bricks",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal_bricks", "", "", "", ""},
				"copper_inferno:cracked_cinder_crystal_bricks", 1, "Smelting Cinder Crystal Bricks in a furnace yields Cracked Cinder Crystal Bricks.", "Zunderkristallziegel im Ofen gebrannt ergibt Rissige Zunderkristallziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_cinder_crystal_bricks", "copper_inferno:chiseled_cinder_crystal_bricks", "smolderquartz/chiseled_cinder_crystal_bricks",
				new String[] {"copper_inferno:cinder_crystal_brick_slab", "", "", "copper_inferno:cinder_crystal_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_cinder_crystal_bricks", 1, "Craft 1x Chiseled Cinder Crystal Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Zunderkristallziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_pillar", "copper_inferno:cinder_crystal_pillar", "smolderquartz/cinder_crystal_pillar",
				new String[] {"copper_inferno:cinder_crystal_bricks", "", "", "copper_inferno:cinder_crystal_bricks", "", "", "", "", ""},
				"copper_inferno:cinder_crystal_pillar", 2, "Craft 2x Cinder Crystal Pillar at a crafting table.", "Stellt 2x Zunderkristalls\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_slab_from_cinder_crystal_stonecutting", "copper_inferno:cinder_crystal_slab", "smolderquartz/cinder_crystal_slab_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_slab", 2, "Stonecutting: cut 2x Cinder Crystal Slab from Cinder Crystal.", "Steins\u00e4ge: 2x Zunderkristallstufe aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_stairs_from_cinder_crystal_stonecutting", "copper_inferno:cinder_crystal_stairs", "smolderquartz/cinder_crystal_stairs_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_stairs", 1, "Stonecutting: cut 1x Cinder Crystal Stairs from Cinder Crystal.", "Steins\u00e4ge: 1x Zunderkristalltreppe aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_wall_from_cinder_crystal_stonecutting", "copper_inferno:cinder_crystal_wall", "smolderquartz/cinder_crystal_wall_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_wall", 1, "Stonecutting: cut 1x Cinder Crystal Wall from Cinder Crystal.", "Steins\u00e4ge: 1x Zunderkristallmauer aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_cinder_crystal_from_cinder_crystal_stonecutting", "copper_inferno:polished_cinder_crystal", "smolderquartz/polished_cinder_crystal_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:polished_cinder_crystal", 1, "Stonecutting: cut 1x Polished Cinder Crystal from Cinder Crystal.", "Steins\u00e4ge: 1x Polierten Zunderkristall aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_cinder_crystal_slab_from_cinder_crystal_stonecutting", "copper_inferno:polished_cinder_crystal_slab", "smolderquartz/polished_cinder_crystal_slab_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:polished_cinder_crystal_slab", 2, "Stonecutting: cut 2x Polished Cinder Crystal Slab from Cinder Crystal.", "Steins\u00e4ge: 2x Polierte Zunderkristallstufe aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_cinder_crystal_stairs_from_cinder_crystal_stonecutting", "copper_inferno:polished_cinder_crystal_stairs", "smolderquartz/polished_cinder_crystal_stairs_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:polished_cinder_crystal_stairs", 1, "Stonecutting: cut 1x Polished Cinder Crystal Stairs from Cinder Crystal.", "Steins\u00e4ge: 1x Polierte Zunderkristalltreppe aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_cinder_crystal_wall_from_cinder_crystal_stonecutting", "copper_inferno:polished_cinder_crystal_wall", "smolderquartz/polished_cinder_crystal_wall_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:polished_cinder_crystal_wall", 1, "Stonecutting: cut 1x Polished Cinder Crystal Wall from Cinder Crystal.", "Steins\u00e4ge: 1x Polierte Zunderkristallmauer aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_bricks_from_cinder_crystal_stonecutting", "copper_inferno:cinder_crystal_bricks", "smolderquartz/cinder_crystal_bricks_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_bricks", 1, "Stonecutting: cut 1x Cinder Crystal Bricks from Cinder Crystal.", "Steins\u00e4ge: 1x Zunderkristallziegel aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_brick_slab_from_cinder_crystal_stonecutting", "copper_inferno:cinder_crystal_brick_slab", "smolderquartz/cinder_crystal_brick_slab_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_brick_slab", 2, "Stonecutting: cut 2x Cinder Crystal Brick Slab from Cinder Crystal.", "Steins\u00e4ge: 2x Zunderkristallziegelstufe aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_brick_stairs_from_cinder_crystal_stonecutting", "copper_inferno:cinder_crystal_brick_stairs", "smolderquartz/cinder_crystal_brick_stairs_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_brick_stairs", 1, "Stonecutting: cut 1x Cinder Crystal Brick Stairs from Cinder Crystal.", "Steins\u00e4ge: 1x Zunderkristallziegeltreppe aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_brick_wall_from_cinder_crystal_stonecutting", "copper_inferno:cinder_crystal_brick_wall", "smolderquartz/cinder_crystal_brick_wall_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_brick_wall", 1, "Stonecutting: cut 1x Cinder Crystal Brick Wall from Cinder Crystal.", "Steins\u00e4ge: 1x Zunderkristallziegelmauer aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_tiles_from_cinder_crystal_stonecutting", "copper_inferno:cinder_crystal_tiles", "smolderquartz/cinder_crystal_tiles_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_tiles", 1, "Stonecutting: cut 1x Cinder Crystal Tiles from Cinder Crystal.", "Steins\u00e4ge: 1x Zunderkristallfliesen aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_cinder_crystal_bricks_from_cinder_crystal_stonecutting", "copper_inferno:chiseled_cinder_crystal_bricks", "smolderquartz/chiseled_cinder_crystal_bricks_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:chiseled_cinder_crystal_bricks", 1, "Stonecutting: cut 1x Chiseled Cinder Crystal Bricks from Cinder Crystal.", "Steins\u00e4ge: 1x Gemei\u00dfelte Zunderkristallziegel aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cinder_crystal_pillar_from_cinder_crystal_stonecutting", "copper_inferno:cinder_crystal_pillar", "smolderquartz/cinder_crystal_pillar_from_cinder_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_crystal", "", "", "", ""},
				"copper_inferno:cinder_crystal_pillar", 1, "Stonecutting: cut 1x Cinder Crystal Pillar from Cinder Crystal.", "Steins\u00e4ge: 1x Zunderkristalls\u00e4ule aus Zunderkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal", "copper_inferno:flame_opal", "smolderquartz/flame_opal",
				new String[] {"minecraft:fire_charge", "", "minecraft:fire_charge", "", "copper_inferno:cinder_crystal", "", "minecraft:fire_charge", "", "minecraft:fire_charge"},
				"copper_inferno:flame_opal", 4, "Craft 4x Flame Opal at a crafting table.", "Stellt 4x Flammenopal an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_bricks", "copper_inferno:flame_opal_bricks", "smolderquartz/flame_opal_bricks",
				new String[] {"copper_inferno:flame_opal", "copper_inferno:flame_opal", "", "copper_inferno:flame_opal", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_bricks", 4, "Craft 4x Flame Opal Bricks at a crafting table.", "Stellt 4x Flammenopalziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_tiles", "copper_inferno:flame_opal_tiles", "smolderquartz/flame_opal_tiles",
				new String[] {"copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "", "copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "", "", "", ""},
				"copper_inferno:flame_opal_tiles", 4, "Craft 4x Flame Opal Tiles at a crafting table.", "Stellt 4x Flammenopalfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_flame_opal", "copper_inferno:polished_flame_opal", "smolderquartz/polished_flame_opal",
				new String[] {"copper_inferno:flame_opal_tiles", "copper_inferno:flame_opal_tiles", "", "copper_inferno:flame_opal_tiles", "copper_inferno:flame_opal_tiles", "", "", "", ""},
				"copper_inferno:polished_flame_opal", 4, "Craft 4x Polished Flame Opal at a crafting table.", "Stellt 4x Polierten Flammenopal an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_slab", "copper_inferno:flame_opal_slab", "smolderquartz/flame_opal_slab",
				new String[] {"copper_inferno:flame_opal", "copper_inferno:flame_opal", "copper_inferno:flame_opal", "", "", "", "", "", ""},
				"copper_inferno:flame_opal_slab", 6, "Craft 6x Flame Opal Slab at a crafting table.", "Stellt 6x Flammenopalstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_stairs", "copper_inferno:flame_opal_stairs", "smolderquartz/flame_opal_stairs",
				new String[] {"copper_inferno:flame_opal", "", "", "copper_inferno:flame_opal", "copper_inferno:flame_opal", "", "copper_inferno:flame_opal", "copper_inferno:flame_opal", "copper_inferno:flame_opal"},
				"copper_inferno:flame_opal_stairs", 4, "Craft 4x Flame Opal Stairs at a crafting table.", "Stellt 4x Flammenopaltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_wall", "copper_inferno:flame_opal_wall", "smolderquartz/flame_opal_wall",
				new String[] {"copper_inferno:flame_opal", "copper_inferno:flame_opal", "copper_inferno:flame_opal", "copper_inferno:flame_opal", "copper_inferno:flame_opal", "copper_inferno:flame_opal", "", "", ""},
				"copper_inferno:flame_opal_wall", 6, "Craft 6x Flame Opal Wall at a crafting table.", "Stellt 6x Flammenopalmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_flame_opal_slab", "copper_inferno:polished_flame_opal_slab", "smolderquartz/polished_flame_opal_slab",
				new String[] {"copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal", "", "", "", "", "", ""},
				"copper_inferno:polished_flame_opal_slab", 6, "Craft 6x Polished Flame Opal Slab at a crafting table.", "Stellt 6x Polierte Flammenopalstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_flame_opal_stairs", "copper_inferno:polished_flame_opal_stairs", "smolderquartz/polished_flame_opal_stairs",
				new String[] {"copper_inferno:polished_flame_opal", "", "", "copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal", "", "copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal"},
				"copper_inferno:polished_flame_opal_stairs", 4, "Craft 4x Polished Flame Opal Stairs at a crafting table.", "Stellt 4x Polierte Flammenopaltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_flame_opal_wall", "copper_inferno:polished_flame_opal_wall", "smolderquartz/polished_flame_opal_wall",
				new String[] {"copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal", "copper_inferno:polished_flame_opal", "", "", ""},
				"copper_inferno:polished_flame_opal_wall", 6, "Craft 6x Polished Flame Opal Wall at a crafting table.", "Stellt 6x Polierte Flammenopalmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_brick_slab", "copper_inferno:flame_opal_brick_slab", "smolderquartz/flame_opal_brick_slab",
				new String[] {"copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "", "", "", "", "", ""},
				"copper_inferno:flame_opal_brick_slab", 6, "Craft 6x Flame Opal Brick Slab at a crafting table.", "Stellt 6x Flammenopalziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_brick_stairs", "copper_inferno:flame_opal_brick_stairs", "smolderquartz/flame_opal_brick_stairs",
				new String[] {"copper_inferno:flame_opal_bricks", "", "", "copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "", "copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks"},
				"copper_inferno:flame_opal_brick_stairs", 4, "Craft 4x Flame Opal Brick Stairs at a crafting table.", "Stellt 4x Flammenopalziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_brick_wall", "copper_inferno:flame_opal_brick_wall", "smolderquartz/flame_opal_brick_wall",
				new String[] {"copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "copper_inferno:flame_opal_bricks", "", "", ""},
				"copper_inferno:flame_opal_brick_wall", 6, "Craft 6x Flame Opal Brick Wall at a crafting table.", "Stellt 6x Flammenopalziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_flame_opal_bricks", "copper_inferno:cracked_flame_opal_bricks", "smolderquartz/cracked_flame_opal_bricks",
				new String[] {"", "", "", "", "copper_inferno:flame_opal_bricks", "", "", "", ""},
				"copper_inferno:cracked_flame_opal_bricks", 1, "Smelting Flame Opal Bricks in a furnace yields Cracked Flame Opal Bricks.", "Flammenopalziegel im Ofen gebrannt ergibt Rissige Flammenopalziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_flame_opal_bricks", "copper_inferno:chiseled_flame_opal_bricks", "smolderquartz/chiseled_flame_opal_bricks",
				new String[] {"copper_inferno:flame_opal_brick_slab", "", "", "copper_inferno:flame_opal_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_flame_opal_bricks", 1, "Craft 1x Chiseled Flame Opal Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Flammenopalziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_pillar", "copper_inferno:flame_opal_pillar", "smolderquartz/flame_opal_pillar",
				new String[] {"copper_inferno:flame_opal_bricks", "", "", "copper_inferno:flame_opal_bricks", "", "", "", "", ""},
				"copper_inferno:flame_opal_pillar", 2, "Craft 2x Flame Opal Pillar at a crafting table.", "Stellt 2x Flammenopals\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_slab_from_flame_opal_stonecutting", "copper_inferno:flame_opal_slab", "smolderquartz/flame_opal_slab_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_slab", 2, "Stonecutting: cut 2x Flame Opal Slab from Flame Opal.", "Steins\u00e4ge: 2x Flammenopalstufe aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_stairs_from_flame_opal_stonecutting", "copper_inferno:flame_opal_stairs", "smolderquartz/flame_opal_stairs_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_stairs", 1, "Stonecutting: cut 1x Flame Opal Stairs from Flame Opal.", "Steins\u00e4ge: 1x Flammenopaltreppe aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_wall_from_flame_opal_stonecutting", "copper_inferno:flame_opal_wall", "smolderquartz/flame_opal_wall_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_wall", 1, "Stonecutting: cut 1x Flame Opal Wall from Flame Opal.", "Steins\u00e4ge: 1x Flammenopalmauer aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_flame_opal_from_flame_opal_stonecutting", "copper_inferno:polished_flame_opal", "smolderquartz/polished_flame_opal_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:polished_flame_opal", 1, "Stonecutting: cut 1x Polished Flame Opal from Flame Opal.", "Steins\u00e4ge: 1x Polierten Flammenopal aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_flame_opal_slab_from_flame_opal_stonecutting", "copper_inferno:polished_flame_opal_slab", "smolderquartz/polished_flame_opal_slab_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:polished_flame_opal_slab", 2, "Stonecutting: cut 2x Polished Flame Opal Slab from Flame Opal.", "Steins\u00e4ge: 2x Polierte Flammenopalstufe aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_flame_opal_stairs_from_flame_opal_stonecutting", "copper_inferno:polished_flame_opal_stairs", "smolderquartz/polished_flame_opal_stairs_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:polished_flame_opal_stairs", 1, "Stonecutting: cut 1x Polished Flame Opal Stairs from Flame Opal.", "Steins\u00e4ge: 1x Polierte Flammenopaltreppe aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_flame_opal_wall_from_flame_opal_stonecutting", "copper_inferno:polished_flame_opal_wall", "smolderquartz/polished_flame_opal_wall_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:polished_flame_opal_wall", 1, "Stonecutting: cut 1x Polished Flame Opal Wall from Flame Opal.", "Steins\u00e4ge: 1x Polierte Flammenopalmauer aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_bricks_from_flame_opal_stonecutting", "copper_inferno:flame_opal_bricks", "smolderquartz/flame_opal_bricks_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_bricks", 1, "Stonecutting: cut 1x Flame Opal Bricks from Flame Opal.", "Steins\u00e4ge: 1x Flammenopalziegel aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_brick_slab_from_flame_opal_stonecutting", "copper_inferno:flame_opal_brick_slab", "smolderquartz/flame_opal_brick_slab_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_brick_slab", 2, "Stonecutting: cut 2x Flame Opal Brick Slab from Flame Opal.", "Steins\u00e4ge: 2x Flammenopalziegelstufe aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_brick_stairs_from_flame_opal_stonecutting", "copper_inferno:flame_opal_brick_stairs", "smolderquartz/flame_opal_brick_stairs_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_brick_stairs", 1, "Stonecutting: cut 1x Flame Opal Brick Stairs from Flame Opal.", "Steins\u00e4ge: 1x Flammenopalziegeltreppe aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_brick_wall_from_flame_opal_stonecutting", "copper_inferno:flame_opal_brick_wall", "smolderquartz/flame_opal_brick_wall_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_brick_wall", 1, "Stonecutting: cut 1x Flame Opal Brick Wall from Flame Opal.", "Steins\u00e4ge: 1x Flammenopalziegelmauer aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_tiles_from_flame_opal_stonecutting", "copper_inferno:flame_opal_tiles", "smolderquartz/flame_opal_tiles_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_tiles", 1, "Stonecutting: cut 1x Flame Opal Tiles from Flame Opal.", "Steins\u00e4ge: 1x Flammenopalfliesen aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_flame_opal_bricks_from_flame_opal_stonecutting", "copper_inferno:chiseled_flame_opal_bricks", "smolderquartz/chiseled_flame_opal_bricks_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:chiseled_flame_opal_bricks", 1, "Stonecutting: cut 1x Chiseled Flame Opal Bricks from Flame Opal.", "Steins\u00e4ge: 1x Gemei\u00dfelte Flammenopalziegel aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/flame_opal_pillar_from_flame_opal_stonecutting", "copper_inferno:flame_opal_pillar", "smolderquartz/flame_opal_pillar_from_flame_opal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:flame_opal", "", "", "", ""},
				"copper_inferno:flame_opal_pillar", 1, "Stonecutting: cut 1x Flame Opal Pillar from Flame Opal.", "Steins\u00e4ge: 1x Flammenopals\u00e4ule aus Flammenopal schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism", "copper_inferno:ember_prism", "smolderquartz/ember_prism",
				new String[] {"minecraft:amethyst_shard", "", "minecraft:amethyst_shard", "", "copper_inferno:flame_opal", "", "minecraft:amethyst_shard", "", "minecraft:amethyst_shard"},
				"copper_inferno:ember_prism", 4, "Craft 4x Ember Prism at a crafting table.", "Stellt 4x Glutprisma an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_bricks", "copper_inferno:ember_prism_bricks", "smolderquartz/ember_prism_bricks",
				new String[] {"copper_inferno:ember_prism", "copper_inferno:ember_prism", "", "copper_inferno:ember_prism", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_bricks", 4, "Craft 4x Ember Prism Bricks at a crafting table.", "Stellt 4x Glutprismenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_tiles", "copper_inferno:ember_prism_tiles", "smolderquartz/ember_prism_tiles",
				new String[] {"copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "", "copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "", "", "", ""},
				"copper_inferno:ember_prism_tiles", 4, "Craft 4x Ember Prism Tiles at a crafting table.", "Stellt 4x Glutprismenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_prism", "copper_inferno:polished_ember_prism", "smolderquartz/polished_ember_prism",
				new String[] {"copper_inferno:ember_prism_tiles", "copper_inferno:ember_prism_tiles", "", "copper_inferno:ember_prism_tiles", "copper_inferno:ember_prism_tiles", "", "", "", ""},
				"copper_inferno:polished_ember_prism", 4, "Craft 4x Polished Ember Prism at a crafting table.", "Stellt 4x Poliertes Glutprisma an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_slab", "copper_inferno:ember_prism_slab", "smolderquartz/ember_prism_slab",
				new String[] {"copper_inferno:ember_prism", "copper_inferno:ember_prism", "copper_inferno:ember_prism", "", "", "", "", "", ""},
				"copper_inferno:ember_prism_slab", 6, "Craft 6x Ember Prism Slab at a crafting table.", "Stellt 6x Glutprismenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_stairs", "copper_inferno:ember_prism_stairs", "smolderquartz/ember_prism_stairs",
				new String[] {"copper_inferno:ember_prism", "", "", "copper_inferno:ember_prism", "copper_inferno:ember_prism", "", "copper_inferno:ember_prism", "copper_inferno:ember_prism", "copper_inferno:ember_prism"},
				"copper_inferno:ember_prism_stairs", 4, "Craft 4x Ember Prism Stairs at a crafting table.", "Stellt 4x Glutprismentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_wall", "copper_inferno:ember_prism_wall", "smolderquartz/ember_prism_wall",
				new String[] {"copper_inferno:ember_prism", "copper_inferno:ember_prism", "copper_inferno:ember_prism", "copper_inferno:ember_prism", "copper_inferno:ember_prism", "copper_inferno:ember_prism", "", "", ""},
				"copper_inferno:ember_prism_wall", 6, "Craft 6x Ember Prism Wall at a crafting table.", "Stellt 6x Glutprismenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_prism_slab", "copper_inferno:polished_ember_prism_slab", "smolderquartz/polished_ember_prism_slab",
				new String[] {"copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism", "", "", "", "", "", ""},
				"copper_inferno:polished_ember_prism_slab", 6, "Craft 6x Polished Ember Prism Slab at a crafting table.", "Stellt 6x Polierte Glutprismenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_prism_stairs", "copper_inferno:polished_ember_prism_stairs", "smolderquartz/polished_ember_prism_stairs",
				new String[] {"copper_inferno:polished_ember_prism", "", "", "copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism", "", "copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism"},
				"copper_inferno:polished_ember_prism_stairs", 4, "Craft 4x Polished Ember Prism Stairs at a crafting table.", "Stellt 4x Polierte Glutprismentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_prism_wall", "copper_inferno:polished_ember_prism_wall", "smolderquartz/polished_ember_prism_wall",
				new String[] {"copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism", "copper_inferno:polished_ember_prism", "", "", ""},
				"copper_inferno:polished_ember_prism_wall", 6, "Craft 6x Polished Ember Prism Wall at a crafting table.", "Stellt 6x Polierte Glutprismenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_brick_slab", "copper_inferno:ember_prism_brick_slab", "smolderquartz/ember_prism_brick_slab",
				new String[] {"copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "", "", "", "", "", ""},
				"copper_inferno:ember_prism_brick_slab", 6, "Craft 6x Ember Prism Brick Slab at a crafting table.", "Stellt 6x Glutprismenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_brick_stairs", "copper_inferno:ember_prism_brick_stairs", "smolderquartz/ember_prism_brick_stairs",
				new String[] {"copper_inferno:ember_prism_bricks", "", "", "copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "", "copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks"},
				"copper_inferno:ember_prism_brick_stairs", 4, "Craft 4x Ember Prism Brick Stairs at a crafting table.", "Stellt 4x Glutprismenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_brick_wall", "copper_inferno:ember_prism_brick_wall", "smolderquartz/ember_prism_brick_wall",
				new String[] {"copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "copper_inferno:ember_prism_bricks", "", "", ""},
				"copper_inferno:ember_prism_brick_wall", 6, "Craft 6x Ember Prism Brick Wall at a crafting table.", "Stellt 6x Glutprismenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_ember_prism_bricks", "copper_inferno:cracked_ember_prism_bricks", "smolderquartz/cracked_ember_prism_bricks",
				new String[] {"", "", "", "", "copper_inferno:ember_prism_bricks", "", "", "", ""},
				"copper_inferno:cracked_ember_prism_bricks", 1, "Smelting Ember Prism Bricks in a furnace yields Cracked Ember Prism Bricks.", "Glutprismenziegel im Ofen gebrannt ergibt Rissige Glutprismenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_ember_prism_bricks", "copper_inferno:chiseled_ember_prism_bricks", "smolderquartz/chiseled_ember_prism_bricks",
				new String[] {"copper_inferno:ember_prism_brick_slab", "", "", "copper_inferno:ember_prism_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_ember_prism_bricks", 1, "Craft 1x Chiseled Ember Prism Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glutprismenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_pillar", "copper_inferno:ember_prism_pillar", "smolderquartz/ember_prism_pillar",
				new String[] {"copper_inferno:ember_prism_bricks", "", "", "copper_inferno:ember_prism_bricks", "", "", "", "", ""},
				"copper_inferno:ember_prism_pillar", 2, "Craft 2x Ember Prism Pillar at a crafting table.", "Stellt 2x Glutprismens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_slab_from_ember_prism_stonecutting", "copper_inferno:ember_prism_slab", "smolderquartz/ember_prism_slab_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_slab", 2, "Stonecutting: cut 2x Ember Prism Slab from Ember Prism.", "Steins\u00e4ge: 2x Glutprismenstufe aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_stairs_from_ember_prism_stonecutting", "copper_inferno:ember_prism_stairs", "smolderquartz/ember_prism_stairs_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_stairs", 1, "Stonecutting: cut 1x Ember Prism Stairs from Ember Prism.", "Steins\u00e4ge: 1x Glutprismentreppe aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_wall_from_ember_prism_stonecutting", "copper_inferno:ember_prism_wall", "smolderquartz/ember_prism_wall_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_wall", 1, "Stonecutting: cut 1x Ember Prism Wall from Ember Prism.", "Steins\u00e4ge: 1x Glutprismenmauer aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_prism_from_ember_prism_stonecutting", "copper_inferno:polished_ember_prism", "smolderquartz/polished_ember_prism_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:polished_ember_prism", 1, "Stonecutting: cut 1x Polished Ember Prism from Ember Prism.", "Steins\u00e4ge: 1x Poliertes Glutprisma aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_prism_slab_from_ember_prism_stonecutting", "copper_inferno:polished_ember_prism_slab", "smolderquartz/polished_ember_prism_slab_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:polished_ember_prism_slab", 2, "Stonecutting: cut 2x Polished Ember Prism Slab from Ember Prism.", "Steins\u00e4ge: 2x Polierte Glutprismenstufe aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_prism_stairs_from_ember_prism_stonecutting", "copper_inferno:polished_ember_prism_stairs", "smolderquartz/polished_ember_prism_stairs_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:polished_ember_prism_stairs", 1, "Stonecutting: cut 1x Polished Ember Prism Stairs from Ember Prism.", "Steins\u00e4ge: 1x Polierte Glutprismentreppe aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_prism_wall_from_ember_prism_stonecutting", "copper_inferno:polished_ember_prism_wall", "smolderquartz/polished_ember_prism_wall_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:polished_ember_prism_wall", 1, "Stonecutting: cut 1x Polished Ember Prism Wall from Ember Prism.", "Steins\u00e4ge: 1x Polierte Glutprismenmauer aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_bricks_from_ember_prism_stonecutting", "copper_inferno:ember_prism_bricks", "smolderquartz/ember_prism_bricks_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_bricks", 1, "Stonecutting: cut 1x Ember Prism Bricks from Ember Prism.", "Steins\u00e4ge: 1x Glutprismenziegel aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_brick_slab_from_ember_prism_stonecutting", "copper_inferno:ember_prism_brick_slab", "smolderquartz/ember_prism_brick_slab_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_brick_slab", 2, "Stonecutting: cut 2x Ember Prism Brick Slab from Ember Prism.", "Steins\u00e4ge: 2x Glutprismenziegelstufe aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_brick_stairs_from_ember_prism_stonecutting", "copper_inferno:ember_prism_brick_stairs", "smolderquartz/ember_prism_brick_stairs_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_brick_stairs", 1, "Stonecutting: cut 1x Ember Prism Brick Stairs from Ember Prism.", "Steins\u00e4ge: 1x Glutprismenziegeltreppe aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_brick_wall_from_ember_prism_stonecutting", "copper_inferno:ember_prism_brick_wall", "smolderquartz/ember_prism_brick_wall_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_brick_wall", 1, "Stonecutting: cut 1x Ember Prism Brick Wall from Ember Prism.", "Steins\u00e4ge: 1x Glutprismenziegelmauer aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_tiles_from_ember_prism_stonecutting", "copper_inferno:ember_prism_tiles", "smolderquartz/ember_prism_tiles_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_tiles", 1, "Stonecutting: cut 1x Ember Prism Tiles from Ember Prism.", "Steins\u00e4ge: 1x Glutprismenfliesen aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_ember_prism_bricks_from_ember_prism_stonecutting", "copper_inferno:chiseled_ember_prism_bricks", "smolderquartz/chiseled_ember_prism_bricks_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:chiseled_ember_prism_bricks", 1, "Stonecutting: cut 1x Chiseled Ember Prism Bricks from Ember Prism.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glutprismenziegel aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_prism_pillar_from_ember_prism_stonecutting", "copper_inferno:ember_prism_pillar", "smolderquartz/ember_prism_pillar_from_ember_prism_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_prism", "", "", "", ""},
				"copper_inferno:ember_prism_pillar", 1, "Stonecutting: cut 1x Ember Prism Pillar from Ember Prism.", "Steins\u00e4ge: 1x Glutprismens\u00e4ule aus Glutprisma schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone", "copper_inferno:ashglass_stone", "smolderquartz/ashglass_stone",
				new String[] {"minecraft:glass", "", "minecraft:glass", "", "copper_inferno:ember_prism", "", "minecraft:glass", "", "minecraft:glass"},
				"copper_inferno:ashglass_stone", 4, "Craft 4x Ashglass Stone at a crafting table.", "Stellt 4x Aschglasstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "smolderquartz/ashglass_stone_bricks",
				new String[] {"copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "", "copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_bricks", 4, "Craft 4x Ashglass Stone Bricks at a crafting table.", "Stellt 4x Aschglassteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_tiles", "copper_inferno:ashglass_stone_tiles", "smolderquartz/ashglass_stone_tiles",
				new String[] {"copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "", "copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "", "", "", ""},
				"copper_inferno:ashglass_stone_tiles", 4, "Craft 4x Ashglass Stone Tiles at a crafting table.", "Stellt 4x Aschglassteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "smolderquartz/polished_ashglass_stone",
				new String[] {"copper_inferno:ashglass_stone_tiles", "copper_inferno:ashglass_stone_tiles", "", "copper_inferno:ashglass_stone_tiles", "copper_inferno:ashglass_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_ashglass_stone", 4, "Craft 4x Polished Ashglass Stone at a crafting table.", "Stellt 4x Polierten Aschglasstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_slab", "copper_inferno:ashglass_stone_slab", "smolderquartz/ashglass_stone_slab",
				new String[] {"copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "", "", "", "", "", ""},
				"copper_inferno:ashglass_stone_slab", 6, "Craft 6x Ashglass Stone Slab at a crafting table.", "Stellt 6x Aschglassteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_stairs", "copper_inferno:ashglass_stone_stairs", "smolderquartz/ashglass_stone_stairs",
				new String[] {"copper_inferno:ashglass_stone", "", "", "copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "", "copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone"},
				"copper_inferno:ashglass_stone_stairs", 4, "Craft 4x Ashglass Stone Stairs at a crafting table.", "Stellt 4x Aschglassteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_wall", "copper_inferno:ashglass_stone_wall", "smolderquartz/ashglass_stone_wall",
				new String[] {"copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "copper_inferno:ashglass_stone", "", "", ""},
				"copper_inferno:ashglass_stone_wall", 6, "Craft 6x Ashglass Stone Wall at a crafting table.", "Stellt 6x Aschglassteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ashglass_stone_slab", "copper_inferno:polished_ashglass_stone_slab", "smolderquartz/polished_ashglass_stone_slab",
				new String[] {"copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_ashglass_stone_slab", 6, "Craft 6x Polished Ashglass Stone Slab at a crafting table.", "Stellt 6x Polierte Aschglassteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ashglass_stone_stairs", "copper_inferno:polished_ashglass_stone_stairs", "smolderquartz/polished_ashglass_stone_stairs",
				new String[] {"copper_inferno:polished_ashglass_stone", "", "", "copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "", "copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone"},
				"copper_inferno:polished_ashglass_stone_stairs", 4, "Craft 4x Polished Ashglass Stone Stairs at a crafting table.", "Stellt 4x Polierte Aschglassteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ashglass_stone_wall", "copper_inferno:polished_ashglass_stone_wall", "smolderquartz/polished_ashglass_stone_wall",
				new String[] {"copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "copper_inferno:polished_ashglass_stone", "", "", ""},
				"copper_inferno:polished_ashglass_stone_wall", 6, "Craft 6x Polished Ashglass Stone Wall at a crafting table.", "Stellt 6x Polierte Aschglassteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_brick_slab", "copper_inferno:ashglass_stone_brick_slab", "smolderquartz/ashglass_stone_brick_slab",
				new String[] {"copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:ashglass_stone_brick_slab", 6, "Craft 6x Ashglass Stone Brick Slab at a crafting table.", "Stellt 6x Aschglassteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_brick_stairs", "copper_inferno:ashglass_stone_brick_stairs", "smolderquartz/ashglass_stone_brick_stairs",
				new String[] {"copper_inferno:ashglass_stone_bricks", "", "", "copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "", "copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks"},
				"copper_inferno:ashglass_stone_brick_stairs", 4, "Craft 4x Ashglass Stone Brick Stairs at a crafting table.", "Stellt 4x Aschglassteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_brick_wall", "copper_inferno:ashglass_stone_brick_wall", "smolderquartz/ashglass_stone_brick_wall",
				new String[] {"copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "copper_inferno:ashglass_stone_bricks", "", "", ""},
				"copper_inferno:ashglass_stone_brick_wall", 6, "Craft 6x Ashglass Stone Brick Wall at a crafting table.", "Stellt 6x Aschglassteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_ashglass_stone_bricks", "copper_inferno:cracked_ashglass_stone_bricks", "smolderquartz/cracked_ashglass_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_ashglass_stone_bricks", 1, "Smelting Ashglass Stone Bricks in a furnace yields Cracked Ashglass Stone Bricks.", "Aschglassteinziegel im Ofen gebrannt ergibt Rissige Aschglassteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_ashglass_stone_bricks", "copper_inferno:chiseled_ashglass_stone_bricks", "smolderquartz/chiseled_ashglass_stone_bricks",
				new String[] {"copper_inferno:ashglass_stone_brick_slab", "", "", "copper_inferno:ashglass_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_ashglass_stone_bricks", 1, "Craft 1x Chiseled Ashglass Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Aschglassteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_pillar", "copper_inferno:ashglass_stone_pillar", "smolderquartz/ashglass_stone_pillar",
				new String[] {"copper_inferno:ashglass_stone_bricks", "", "", "copper_inferno:ashglass_stone_bricks", "", "", "", "", ""},
				"copper_inferno:ashglass_stone_pillar", 2, "Craft 2x Ashglass Stone Pillar at a crafting table.", "Stellt 2x Aschglassteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_slab_from_ashglass_stone_stonecutting", "copper_inferno:ashglass_stone_slab", "smolderquartz/ashglass_stone_slab_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_slab", 2, "Stonecutting: cut 2x Ashglass Stone Slab from Ashglass Stone.", "Steins\u00e4ge: 2x Aschglassteinstufe aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_stairs_from_ashglass_stone_stonecutting", "copper_inferno:ashglass_stone_stairs", "smolderquartz/ashglass_stone_stairs_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_stairs", 1, "Stonecutting: cut 1x Ashglass Stone Stairs from Ashglass Stone.", "Steins\u00e4ge: 1x Aschglassteintreppe aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_wall_from_ashglass_stone_stonecutting", "copper_inferno:ashglass_stone_wall", "smolderquartz/ashglass_stone_wall_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_wall", 1, "Stonecutting: cut 1x Ashglass Stone Wall from Ashglass Stone.", "Steins\u00e4ge: 1x Aschglassteinmauer aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ashglass_stone_from_ashglass_stone_stonecutting", "copper_inferno:polished_ashglass_stone", "smolderquartz/polished_ashglass_stone_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:polished_ashglass_stone", 1, "Stonecutting: cut 1x Polished Ashglass Stone from Ashglass Stone.", "Steins\u00e4ge: 1x Polierten Aschglasstein aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ashglass_stone_slab_from_ashglass_stone_stonecutting", "copper_inferno:polished_ashglass_stone_slab", "smolderquartz/polished_ashglass_stone_slab_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:polished_ashglass_stone_slab", 2, "Stonecutting: cut 2x Polished Ashglass Stone Slab from Ashglass Stone.", "Steins\u00e4ge: 2x Polierte Aschglassteinstufe aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ashglass_stone_stairs_from_ashglass_stone_stonecutting", "copper_inferno:polished_ashglass_stone_stairs", "smolderquartz/polished_ashglass_stone_stairs_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:polished_ashglass_stone_stairs", 1, "Stonecutting: cut 1x Polished Ashglass Stone Stairs from Ashglass Stone.", "Steins\u00e4ge: 1x Polierte Aschglassteintreppe aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ashglass_stone_wall_from_ashglass_stone_stonecutting", "copper_inferno:polished_ashglass_stone_wall", "smolderquartz/polished_ashglass_stone_wall_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:polished_ashglass_stone_wall", 1, "Stonecutting: cut 1x Polished Ashglass Stone Wall from Ashglass Stone.", "Steins\u00e4ge: 1x Polierte Aschglassteinmauer aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_bricks_from_ashglass_stone_stonecutting", "copper_inferno:ashglass_stone_bricks", "smolderquartz/ashglass_stone_bricks_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_bricks", 1, "Stonecutting: cut 1x Ashglass Stone Bricks from Ashglass Stone.", "Steins\u00e4ge: 1x Aschglassteinziegel aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_brick_slab_from_ashglass_stone_stonecutting", "copper_inferno:ashglass_stone_brick_slab", "smolderquartz/ashglass_stone_brick_slab_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_brick_slab", 2, "Stonecutting: cut 2x Ashglass Stone Brick Slab from Ashglass Stone.", "Steins\u00e4ge: 2x Aschglassteinziegelstufe aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_brick_stairs_from_ashglass_stone_stonecutting", "copper_inferno:ashglass_stone_brick_stairs", "smolderquartz/ashglass_stone_brick_stairs_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_brick_stairs", 1, "Stonecutting: cut 1x Ashglass Stone Brick Stairs from Ashglass Stone.", "Steins\u00e4ge: 1x Aschglassteinziegeltreppe aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_brick_wall_from_ashglass_stone_stonecutting", "copper_inferno:ashglass_stone_brick_wall", "smolderquartz/ashglass_stone_brick_wall_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_brick_wall", 1, "Stonecutting: cut 1x Ashglass Stone Brick Wall from Ashglass Stone.", "Steins\u00e4ge: 1x Aschglassteinziegelmauer aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_tiles_from_ashglass_stone_stonecutting", "copper_inferno:ashglass_stone_tiles", "smolderquartz/ashglass_stone_tiles_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_tiles", 1, "Stonecutting: cut 1x Ashglass Stone Tiles from Ashglass Stone.", "Steins\u00e4ge: 1x Aschglassteinfliesen aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_ashglass_stone_bricks_from_ashglass_stone_stonecutting", "copper_inferno:chiseled_ashglass_stone_bricks", "smolderquartz/chiseled_ashglass_stone_bricks_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:chiseled_ashglass_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Ashglass Stone Bricks from Ashglass Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Aschglassteinziegel aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ashglass_stone_pillar_from_ashglass_stone_stonecutting", "copper_inferno:ashglass_stone_pillar", "smolderquartz/ashglass_stone_pillar_from_ashglass_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashglass_stone", "", "", "", ""},
				"copper_inferno:ashglass_stone_pillar", 1, "Stonecutting: cut 1x Ashglass Stone Pillar from Ashglass Stone.", "Steins\u00e4ge: 1x Aschglassteins\u00e4ule aus Aschglasstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar", "copper_inferno:glowspar", "smolderquartz/glowspar",
				new String[] {"minecraft:glowstone_dust", "", "minecraft:glowstone_dust", "", "copper_inferno:ashglass_stone", "", "minecraft:glowstone_dust", "", "minecraft:glowstone_dust"},
				"copper_inferno:glowspar", 4, "Craft 4x Glowspar at a crafting table.", "Stellt 4x Leuchtspat an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_bricks", "copper_inferno:glowspar_bricks", "smolderquartz/glowspar_bricks",
				new String[] {"copper_inferno:glowspar", "copper_inferno:glowspar", "", "copper_inferno:glowspar", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_bricks", 4, "Craft 4x Glowspar Bricks at a crafting table.", "Stellt 4x Leuchtspatziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_tiles", "copper_inferno:glowspar_tiles", "smolderquartz/glowspar_tiles",
				new String[] {"copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "", "copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "", "", "", ""},
				"copper_inferno:glowspar_tiles", 4, "Craft 4x Glowspar Tiles at a crafting table.", "Stellt 4x Leuchtspatfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_glowspar", "copper_inferno:polished_glowspar", "smolderquartz/polished_glowspar",
				new String[] {"copper_inferno:glowspar_tiles", "copper_inferno:glowspar_tiles", "", "copper_inferno:glowspar_tiles", "copper_inferno:glowspar_tiles", "", "", "", ""},
				"copper_inferno:polished_glowspar", 4, "Craft 4x Polished Glowspar at a crafting table.", "Stellt 4x Polierten Leuchtspat an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_slab", "copper_inferno:glowspar_slab", "smolderquartz/glowspar_slab",
				new String[] {"copper_inferno:glowspar", "copper_inferno:glowspar", "copper_inferno:glowspar", "", "", "", "", "", ""},
				"copper_inferno:glowspar_slab", 6, "Craft 6x Glowspar Slab at a crafting table.", "Stellt 6x Leuchtspatstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_stairs", "copper_inferno:glowspar_stairs", "smolderquartz/glowspar_stairs",
				new String[] {"copper_inferno:glowspar", "", "", "copper_inferno:glowspar", "copper_inferno:glowspar", "", "copper_inferno:glowspar", "copper_inferno:glowspar", "copper_inferno:glowspar"},
				"copper_inferno:glowspar_stairs", 4, "Craft 4x Glowspar Stairs at a crafting table.", "Stellt 4x Leuchtspattreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_wall", "copper_inferno:glowspar_wall", "smolderquartz/glowspar_wall",
				new String[] {"copper_inferno:glowspar", "copper_inferno:glowspar", "copper_inferno:glowspar", "copper_inferno:glowspar", "copper_inferno:glowspar", "copper_inferno:glowspar", "", "", ""},
				"copper_inferno:glowspar_wall", 6, "Craft 6x Glowspar Wall at a crafting table.", "Stellt 6x Leuchtspatmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_glowspar_slab", "copper_inferno:polished_glowspar_slab", "smolderquartz/polished_glowspar_slab",
				new String[] {"copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar", "", "", "", "", "", ""},
				"copper_inferno:polished_glowspar_slab", 6, "Craft 6x Polished Glowspar Slab at a crafting table.", "Stellt 6x Polierte Leuchtspatstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_glowspar_stairs", "copper_inferno:polished_glowspar_stairs", "smolderquartz/polished_glowspar_stairs",
				new String[] {"copper_inferno:polished_glowspar", "", "", "copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar", "", "copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar"},
				"copper_inferno:polished_glowspar_stairs", 4, "Craft 4x Polished Glowspar Stairs at a crafting table.", "Stellt 4x Polierte Leuchtspattreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_glowspar_wall", "copper_inferno:polished_glowspar_wall", "smolderquartz/polished_glowspar_wall",
				new String[] {"copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar", "copper_inferno:polished_glowspar", "", "", ""},
				"copper_inferno:polished_glowspar_wall", 6, "Craft 6x Polished Glowspar Wall at a crafting table.", "Stellt 6x Polierte Leuchtspatmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_brick_slab", "copper_inferno:glowspar_brick_slab", "smolderquartz/glowspar_brick_slab",
				new String[] {"copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "", "", "", "", "", ""},
				"copper_inferno:glowspar_brick_slab", 6, "Craft 6x Glowspar Brick Slab at a crafting table.", "Stellt 6x Leuchtspatziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_brick_stairs", "copper_inferno:glowspar_brick_stairs", "smolderquartz/glowspar_brick_stairs",
				new String[] {"copper_inferno:glowspar_bricks", "", "", "copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "", "copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks"},
				"copper_inferno:glowspar_brick_stairs", 4, "Craft 4x Glowspar Brick Stairs at a crafting table.", "Stellt 4x Leuchtspatziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_brick_wall", "copper_inferno:glowspar_brick_wall", "smolderquartz/glowspar_brick_wall",
				new String[] {"copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "copper_inferno:glowspar_bricks", "", "", ""},
				"copper_inferno:glowspar_brick_wall", 6, "Craft 6x Glowspar Brick Wall at a crafting table.", "Stellt 6x Leuchtspatziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_glowspar_bricks", "copper_inferno:cracked_glowspar_bricks", "smolderquartz/cracked_glowspar_bricks",
				new String[] {"", "", "", "", "copper_inferno:glowspar_bricks", "", "", "", ""},
				"copper_inferno:cracked_glowspar_bricks", 1, "Smelting Glowspar Bricks in a furnace yields Cracked Glowspar Bricks.", "Leuchtspatziegel im Ofen gebrannt ergibt Rissige Leuchtspatziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_glowspar_bricks", "copper_inferno:chiseled_glowspar_bricks", "smolderquartz/chiseled_glowspar_bricks",
				new String[] {"copper_inferno:glowspar_brick_slab", "", "", "copper_inferno:glowspar_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_glowspar_bricks", 1, "Craft 1x Chiseled Glowspar Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Leuchtspatziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_pillar", "copper_inferno:glowspar_pillar", "smolderquartz/glowspar_pillar",
				new String[] {"copper_inferno:glowspar_bricks", "", "", "copper_inferno:glowspar_bricks", "", "", "", "", ""},
				"copper_inferno:glowspar_pillar", 2, "Craft 2x Glowspar Pillar at a crafting table.", "Stellt 2x Leuchtspats\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_slab_from_glowspar_stonecutting", "copper_inferno:glowspar_slab", "smolderquartz/glowspar_slab_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_slab", 2, "Stonecutting: cut 2x Glowspar Slab from Glowspar.", "Steins\u00e4ge: 2x Leuchtspatstufe aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_stairs_from_glowspar_stonecutting", "copper_inferno:glowspar_stairs", "smolderquartz/glowspar_stairs_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_stairs", 1, "Stonecutting: cut 1x Glowspar Stairs from Glowspar.", "Steins\u00e4ge: 1x Leuchtspattreppe aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_wall_from_glowspar_stonecutting", "copper_inferno:glowspar_wall", "smolderquartz/glowspar_wall_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_wall", 1, "Stonecutting: cut 1x Glowspar Wall from Glowspar.", "Steins\u00e4ge: 1x Leuchtspatmauer aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_glowspar_from_glowspar_stonecutting", "copper_inferno:polished_glowspar", "smolderquartz/polished_glowspar_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:polished_glowspar", 1, "Stonecutting: cut 1x Polished Glowspar from Glowspar.", "Steins\u00e4ge: 1x Polierten Leuchtspat aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_glowspar_slab_from_glowspar_stonecutting", "copper_inferno:polished_glowspar_slab", "smolderquartz/polished_glowspar_slab_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:polished_glowspar_slab", 2, "Stonecutting: cut 2x Polished Glowspar Slab from Glowspar.", "Steins\u00e4ge: 2x Polierte Leuchtspatstufe aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_glowspar_stairs_from_glowspar_stonecutting", "copper_inferno:polished_glowspar_stairs", "smolderquartz/polished_glowspar_stairs_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:polished_glowspar_stairs", 1, "Stonecutting: cut 1x Polished Glowspar Stairs from Glowspar.", "Steins\u00e4ge: 1x Polierte Leuchtspattreppe aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_glowspar_wall_from_glowspar_stonecutting", "copper_inferno:polished_glowspar_wall", "smolderquartz/polished_glowspar_wall_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:polished_glowspar_wall", 1, "Stonecutting: cut 1x Polished Glowspar Wall from Glowspar.", "Steins\u00e4ge: 1x Polierte Leuchtspatmauer aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_bricks_from_glowspar_stonecutting", "copper_inferno:glowspar_bricks", "smolderquartz/glowspar_bricks_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_bricks", 1, "Stonecutting: cut 1x Glowspar Bricks from Glowspar.", "Steins\u00e4ge: 1x Leuchtspatziegel aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_brick_slab_from_glowspar_stonecutting", "copper_inferno:glowspar_brick_slab", "smolderquartz/glowspar_brick_slab_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_brick_slab", 2, "Stonecutting: cut 2x Glowspar Brick Slab from Glowspar.", "Steins\u00e4ge: 2x Leuchtspatziegelstufe aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_brick_stairs_from_glowspar_stonecutting", "copper_inferno:glowspar_brick_stairs", "smolderquartz/glowspar_brick_stairs_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_brick_stairs", 1, "Stonecutting: cut 1x Glowspar Brick Stairs from Glowspar.", "Steins\u00e4ge: 1x Leuchtspatziegeltreppe aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_brick_wall_from_glowspar_stonecutting", "copper_inferno:glowspar_brick_wall", "smolderquartz/glowspar_brick_wall_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_brick_wall", 1, "Stonecutting: cut 1x Glowspar Brick Wall from Glowspar.", "Steins\u00e4ge: 1x Leuchtspatziegelmauer aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_tiles_from_glowspar_stonecutting", "copper_inferno:glowspar_tiles", "smolderquartz/glowspar_tiles_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_tiles", 1, "Stonecutting: cut 1x Glowspar Tiles from Glowspar.", "Steins\u00e4ge: 1x Leuchtspatfliesen aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_glowspar_bricks_from_glowspar_stonecutting", "copper_inferno:chiseled_glowspar_bricks", "smolderquartz/chiseled_glowspar_bricks_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:chiseled_glowspar_bricks", 1, "Stonecutting: cut 1x Chiseled Glowspar Bricks from Glowspar.", "Steins\u00e4ge: 1x Gemei\u00dfelte Leuchtspatziegel aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/glowspar_pillar_from_glowspar_stonecutting", "copper_inferno:glowspar_pillar", "smolderquartz/glowspar_pillar_from_glowspar_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:glowspar", "", "", "", ""},
				"copper_inferno:glowspar_pillar", 1, "Stonecutting: cut 1x Glowspar Pillar from Glowspar.", "Steins\u00e4ge: 1x Leuchtspats\u00e4ule aus Leuchtspat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone", "copper_inferno:heatlens_stone", "smolderquartz/heatlens_stone",
				new String[] {"minecraft:gold_nugget", "", "minecraft:gold_nugget", "", "copper_inferno:glowspar", "", "minecraft:gold_nugget", "", "minecraft:gold_nugget"},
				"copper_inferno:heatlens_stone", 4, "Craft 4x Heatlens Stone at a crafting table.", "Stellt 4x Hitzelinsenstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "smolderquartz/heatlens_stone_bricks",
				new String[] {"copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "", "copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_bricks", 4, "Craft 4x Heatlens Stone Bricks at a crafting table.", "Stellt 4x Hitzelinsensteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_tiles", "copper_inferno:heatlens_stone_tiles", "smolderquartz/heatlens_stone_tiles",
				new String[] {"copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "", "copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "", "", "", ""},
				"copper_inferno:heatlens_stone_tiles", 4, "Craft 4x Heatlens Stone Tiles at a crafting table.", "Stellt 4x Hitzelinsensteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "smolderquartz/polished_heatlens_stone",
				new String[] {"copper_inferno:heatlens_stone_tiles", "copper_inferno:heatlens_stone_tiles", "", "copper_inferno:heatlens_stone_tiles", "copper_inferno:heatlens_stone_tiles", "", "", "", ""},
				"copper_inferno:polished_heatlens_stone", 4, "Craft 4x Polished Heatlens Stone at a crafting table.", "Stellt 4x Polierten Hitzelinsenstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_slab", "copper_inferno:heatlens_stone_slab", "smolderquartz/heatlens_stone_slab",
				new String[] {"copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "", "", "", "", "", ""},
				"copper_inferno:heatlens_stone_slab", 6, "Craft 6x Heatlens Stone Slab at a crafting table.", "Stellt 6x Hitzelinsensteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_stairs", "copper_inferno:heatlens_stone_stairs", "smolderquartz/heatlens_stone_stairs",
				new String[] {"copper_inferno:heatlens_stone", "", "", "copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "", "copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone"},
				"copper_inferno:heatlens_stone_stairs", 4, "Craft 4x Heatlens Stone Stairs at a crafting table.", "Stellt 4x Hitzelinsensteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_wall", "copper_inferno:heatlens_stone_wall", "smolderquartz/heatlens_stone_wall",
				new String[] {"copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "copper_inferno:heatlens_stone", "", "", ""},
				"copper_inferno:heatlens_stone_wall", 6, "Craft 6x Heatlens Stone Wall at a crafting table.", "Stellt 6x Hitzelinsensteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_heatlens_stone_slab", "copper_inferno:polished_heatlens_stone_slab", "smolderquartz/polished_heatlens_stone_slab",
				new String[] {"copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "", "", "", "", "", ""},
				"copper_inferno:polished_heatlens_stone_slab", 6, "Craft 6x Polished Heatlens Stone Slab at a crafting table.", "Stellt 6x Polierte Hitzelinsensteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_heatlens_stone_stairs", "copper_inferno:polished_heatlens_stone_stairs", "smolderquartz/polished_heatlens_stone_stairs",
				new String[] {"copper_inferno:polished_heatlens_stone", "", "", "copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "", "copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone"},
				"copper_inferno:polished_heatlens_stone_stairs", 4, "Craft 4x Polished Heatlens Stone Stairs at a crafting table.", "Stellt 4x Polierte Hitzelinsensteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_heatlens_stone_wall", "copper_inferno:polished_heatlens_stone_wall", "smolderquartz/polished_heatlens_stone_wall",
				new String[] {"copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "copper_inferno:polished_heatlens_stone", "", "", ""},
				"copper_inferno:polished_heatlens_stone_wall", 6, "Craft 6x Polished Heatlens Stone Wall at a crafting table.", "Stellt 6x Polierte Hitzelinsensteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_brick_slab", "copper_inferno:heatlens_stone_brick_slab", "smolderquartz/heatlens_stone_brick_slab",
				new String[] {"copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "", "", "", "", "", ""},
				"copper_inferno:heatlens_stone_brick_slab", 6, "Craft 6x Heatlens Stone Brick Slab at a crafting table.", "Stellt 6x Hitzelinsensteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_brick_stairs", "copper_inferno:heatlens_stone_brick_stairs", "smolderquartz/heatlens_stone_brick_stairs",
				new String[] {"copper_inferno:heatlens_stone_bricks", "", "", "copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "", "copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks"},
				"copper_inferno:heatlens_stone_brick_stairs", 4, "Craft 4x Heatlens Stone Brick Stairs at a crafting table.", "Stellt 4x Hitzelinsensteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_brick_wall", "copper_inferno:heatlens_stone_brick_wall", "smolderquartz/heatlens_stone_brick_wall",
				new String[] {"copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "copper_inferno:heatlens_stone_bricks", "", "", ""},
				"copper_inferno:heatlens_stone_brick_wall", 6, "Craft 6x Heatlens Stone Brick Wall at a crafting table.", "Stellt 6x Hitzelinsensteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_heatlens_stone_bricks", "copper_inferno:cracked_heatlens_stone_bricks", "smolderquartz/cracked_heatlens_stone_bricks",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone_bricks", "", "", "", ""},
				"copper_inferno:cracked_heatlens_stone_bricks", 1, "Smelting Heatlens Stone Bricks in a furnace yields Cracked Heatlens Stone Bricks.", "Hitzelinsensteinziegel im Ofen gebrannt ergibt Rissige Hitzelinsensteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_heatlens_stone_bricks", "copper_inferno:chiseled_heatlens_stone_bricks", "smolderquartz/chiseled_heatlens_stone_bricks",
				new String[] {"copper_inferno:heatlens_stone_brick_slab", "", "", "copper_inferno:heatlens_stone_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_heatlens_stone_bricks", 1, "Craft 1x Chiseled Heatlens Stone Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Hitzelinsensteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_pillar", "copper_inferno:heatlens_stone_pillar", "smolderquartz/heatlens_stone_pillar",
				new String[] {"copper_inferno:heatlens_stone_bricks", "", "", "copper_inferno:heatlens_stone_bricks", "", "", "", "", ""},
				"copper_inferno:heatlens_stone_pillar", 2, "Craft 2x Heatlens Stone Pillar at a crafting table.", "Stellt 2x Hitzelinsensteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_slab_from_heatlens_stone_stonecutting", "copper_inferno:heatlens_stone_slab", "smolderquartz/heatlens_stone_slab_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_slab", 2, "Stonecutting: cut 2x Heatlens Stone Slab from Heatlens Stone.", "Steins\u00e4ge: 2x Hitzelinsensteinstufe aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_stairs_from_heatlens_stone_stonecutting", "copper_inferno:heatlens_stone_stairs", "smolderquartz/heatlens_stone_stairs_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_stairs", 1, "Stonecutting: cut 1x Heatlens Stone Stairs from Heatlens Stone.", "Steins\u00e4ge: 1x Hitzelinsensteintreppe aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_wall_from_heatlens_stone_stonecutting", "copper_inferno:heatlens_stone_wall", "smolderquartz/heatlens_stone_wall_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_wall", 1, "Stonecutting: cut 1x Heatlens Stone Wall from Heatlens Stone.", "Steins\u00e4ge: 1x Hitzelinsensteinmauer aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_heatlens_stone_from_heatlens_stone_stonecutting", "copper_inferno:polished_heatlens_stone", "smolderquartz/polished_heatlens_stone_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:polished_heatlens_stone", 1, "Stonecutting: cut 1x Polished Heatlens Stone from Heatlens Stone.", "Steins\u00e4ge: 1x Polierten Hitzelinsenstein aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_heatlens_stone_slab_from_heatlens_stone_stonecutting", "copper_inferno:polished_heatlens_stone_slab", "smolderquartz/polished_heatlens_stone_slab_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:polished_heatlens_stone_slab", 2, "Stonecutting: cut 2x Polished Heatlens Stone Slab from Heatlens Stone.", "Steins\u00e4ge: 2x Polierte Hitzelinsensteinstufe aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_heatlens_stone_stairs_from_heatlens_stone_stonecutting", "copper_inferno:polished_heatlens_stone_stairs", "smolderquartz/polished_heatlens_stone_stairs_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:polished_heatlens_stone_stairs", 1, "Stonecutting: cut 1x Polished Heatlens Stone Stairs from Heatlens Stone.", "Steins\u00e4ge: 1x Polierte Hitzelinsensteintreppe aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_heatlens_stone_wall_from_heatlens_stone_stonecutting", "copper_inferno:polished_heatlens_stone_wall", "smolderquartz/polished_heatlens_stone_wall_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:polished_heatlens_stone_wall", 1, "Stonecutting: cut 1x Polished Heatlens Stone Wall from Heatlens Stone.", "Steins\u00e4ge: 1x Polierte Hitzelinsensteinmauer aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_bricks_from_heatlens_stone_stonecutting", "copper_inferno:heatlens_stone_bricks", "smolderquartz/heatlens_stone_bricks_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_bricks", 1, "Stonecutting: cut 1x Heatlens Stone Bricks from Heatlens Stone.", "Steins\u00e4ge: 1x Hitzelinsensteinziegel aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_brick_slab_from_heatlens_stone_stonecutting", "copper_inferno:heatlens_stone_brick_slab", "smolderquartz/heatlens_stone_brick_slab_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_brick_slab", 2, "Stonecutting: cut 2x Heatlens Stone Brick Slab from Heatlens Stone.", "Steins\u00e4ge: 2x Hitzelinsensteinziegelstufe aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_brick_stairs_from_heatlens_stone_stonecutting", "copper_inferno:heatlens_stone_brick_stairs", "smolderquartz/heatlens_stone_brick_stairs_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_brick_stairs", 1, "Stonecutting: cut 1x Heatlens Stone Brick Stairs from Heatlens Stone.", "Steins\u00e4ge: 1x Hitzelinsensteinziegeltreppe aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_brick_wall_from_heatlens_stone_stonecutting", "copper_inferno:heatlens_stone_brick_wall", "smolderquartz/heatlens_stone_brick_wall_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_brick_wall", 1, "Stonecutting: cut 1x Heatlens Stone Brick Wall from Heatlens Stone.", "Steins\u00e4ge: 1x Hitzelinsensteinziegelmauer aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_tiles_from_heatlens_stone_stonecutting", "copper_inferno:heatlens_stone_tiles", "smolderquartz/heatlens_stone_tiles_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_tiles", 1, "Stonecutting: cut 1x Heatlens Stone Tiles from Heatlens Stone.", "Steins\u00e4ge: 1x Hitzelinsensteinfliesen aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_heatlens_stone_bricks_from_heatlens_stone_stonecutting", "copper_inferno:chiseled_heatlens_stone_bricks", "smolderquartz/chiseled_heatlens_stone_bricks_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:chiseled_heatlens_stone_bricks", 1, "Stonecutting: cut 1x Chiseled Heatlens Stone Bricks from Heatlens Stone.", "Steins\u00e4ge: 1x Gemei\u00dfelte Hitzelinsensteinziegel aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/heatlens_stone_pillar_from_heatlens_stone_stonecutting", "copper_inferno:heatlens_stone_pillar", "smolderquartz/heatlens_stone_pillar_from_heatlens_stone_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:heatlens_stone", "", "", "", ""},
				"copper_inferno:heatlens_stone_pillar", 1, "Stonecutting: cut 1x Heatlens Stone Pillar from Heatlens Stone.", "Steins\u00e4ge: 1x Hitzelinsensteins\u00e4ule aus Hitzelinsenstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode", "copper_inferno:magma_geode", "smolderquartz/magma_geode",
				new String[] {"minecraft:magma_cream", "", "minecraft:magma_cream", "", "copper_inferno:heatlens_stone", "", "minecraft:magma_cream", "", "minecraft:magma_cream"},
				"copper_inferno:magma_geode", 4, "Craft 4x Magma Geode at a crafting table.", "Stellt 4x Magmageode an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_bricks", "copper_inferno:magma_geode_bricks", "smolderquartz/magma_geode_bricks",
				new String[] {"copper_inferno:magma_geode", "copper_inferno:magma_geode", "", "copper_inferno:magma_geode", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_bricks", 4, "Craft 4x Magma Geode Bricks at a crafting table.", "Stellt 4x Magmageodenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_tiles", "copper_inferno:magma_geode_tiles", "smolderquartz/magma_geode_tiles",
				new String[] {"copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "", "copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "", "", "", ""},
				"copper_inferno:magma_geode_tiles", 4, "Craft 4x Magma Geode Tiles at a crafting table.", "Stellt 4x Magmageodenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_magma_geode", "copper_inferno:polished_magma_geode", "smolderquartz/polished_magma_geode",
				new String[] {"copper_inferno:magma_geode_tiles", "copper_inferno:magma_geode_tiles", "", "copper_inferno:magma_geode_tiles", "copper_inferno:magma_geode_tiles", "", "", "", ""},
				"copper_inferno:polished_magma_geode", 4, "Craft 4x Polished Magma Geode at a crafting table.", "Stellt 4x Polierte Magmageode an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_slab", "copper_inferno:magma_geode_slab", "smolderquartz/magma_geode_slab",
				new String[] {"copper_inferno:magma_geode", "copper_inferno:magma_geode", "copper_inferno:magma_geode", "", "", "", "", "", ""},
				"copper_inferno:magma_geode_slab", 6, "Craft 6x Magma Geode Slab at a crafting table.", "Stellt 6x Magmageodenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_stairs", "copper_inferno:magma_geode_stairs", "smolderquartz/magma_geode_stairs",
				new String[] {"copper_inferno:magma_geode", "", "", "copper_inferno:magma_geode", "copper_inferno:magma_geode", "", "copper_inferno:magma_geode", "copper_inferno:magma_geode", "copper_inferno:magma_geode"},
				"copper_inferno:magma_geode_stairs", 4, "Craft 4x Magma Geode Stairs at a crafting table.", "Stellt 4x Magmageodentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_wall", "copper_inferno:magma_geode_wall", "smolderquartz/magma_geode_wall",
				new String[] {"copper_inferno:magma_geode", "copper_inferno:magma_geode", "copper_inferno:magma_geode", "copper_inferno:magma_geode", "copper_inferno:magma_geode", "copper_inferno:magma_geode", "", "", ""},
				"copper_inferno:magma_geode_wall", 6, "Craft 6x Magma Geode Wall at a crafting table.", "Stellt 6x Magmageodenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_magma_geode_slab", "copper_inferno:polished_magma_geode_slab", "smolderquartz/polished_magma_geode_slab",
				new String[] {"copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode", "", "", "", "", "", ""},
				"copper_inferno:polished_magma_geode_slab", 6, "Craft 6x Polished Magma Geode Slab at a crafting table.", "Stellt 6x Polierte Magmageodenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_magma_geode_stairs", "copper_inferno:polished_magma_geode_stairs", "smolderquartz/polished_magma_geode_stairs",
				new String[] {"copper_inferno:polished_magma_geode", "", "", "copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode", "", "copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode"},
				"copper_inferno:polished_magma_geode_stairs", 4, "Craft 4x Polished Magma Geode Stairs at a crafting table.", "Stellt 4x Polierte Magmageodentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_magma_geode_wall", "copper_inferno:polished_magma_geode_wall", "smolderquartz/polished_magma_geode_wall",
				new String[] {"copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode", "copper_inferno:polished_magma_geode", "", "", ""},
				"copper_inferno:polished_magma_geode_wall", 6, "Craft 6x Polished Magma Geode Wall at a crafting table.", "Stellt 6x Polierte Magmageodenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_brick_slab", "copper_inferno:magma_geode_brick_slab", "smolderquartz/magma_geode_brick_slab",
				new String[] {"copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "", "", "", "", "", ""},
				"copper_inferno:magma_geode_brick_slab", 6, "Craft 6x Magma Geode Brick Slab at a crafting table.", "Stellt 6x Magmageodenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_brick_stairs", "copper_inferno:magma_geode_brick_stairs", "smolderquartz/magma_geode_brick_stairs",
				new String[] {"copper_inferno:magma_geode_bricks", "", "", "copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "", "copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks"},
				"copper_inferno:magma_geode_brick_stairs", 4, "Craft 4x Magma Geode Brick Stairs at a crafting table.", "Stellt 4x Magmageodenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_brick_wall", "copper_inferno:magma_geode_brick_wall", "smolderquartz/magma_geode_brick_wall",
				new String[] {"copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "copper_inferno:magma_geode_bricks", "", "", ""},
				"copper_inferno:magma_geode_brick_wall", 6, "Craft 6x Magma Geode Brick Wall at a crafting table.", "Stellt 6x Magmageodenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_magma_geode_bricks", "copper_inferno:cracked_magma_geode_bricks", "smolderquartz/cracked_magma_geode_bricks",
				new String[] {"", "", "", "", "copper_inferno:magma_geode_bricks", "", "", "", ""},
				"copper_inferno:cracked_magma_geode_bricks", 1, "Smelting Magma Geode Bricks in a furnace yields Cracked Magma Geode Bricks.", "Magmageodenziegel im Ofen gebrannt ergibt Rissige Magmageodenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_magma_geode_bricks", "copper_inferno:chiseled_magma_geode_bricks", "smolderquartz/chiseled_magma_geode_bricks",
				new String[] {"copper_inferno:magma_geode_brick_slab", "", "", "copper_inferno:magma_geode_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_magma_geode_bricks", 1, "Craft 1x Chiseled Magma Geode Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Magmageodenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_pillar", "copper_inferno:magma_geode_pillar", "smolderquartz/magma_geode_pillar",
				new String[] {"copper_inferno:magma_geode_bricks", "", "", "copper_inferno:magma_geode_bricks", "", "", "", "", ""},
				"copper_inferno:magma_geode_pillar", 2, "Craft 2x Magma Geode Pillar at a crafting table.", "Stellt 2x Magmageodens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_slab_from_magma_geode_stonecutting", "copper_inferno:magma_geode_slab", "smolderquartz/magma_geode_slab_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_slab", 2, "Stonecutting: cut 2x Magma Geode Slab from Magma Geode.", "Steins\u00e4ge: 2x Magmageodenstufe aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_stairs_from_magma_geode_stonecutting", "copper_inferno:magma_geode_stairs", "smolderquartz/magma_geode_stairs_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_stairs", 1, "Stonecutting: cut 1x Magma Geode Stairs from Magma Geode.", "Steins\u00e4ge: 1x Magmageodentreppe aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_wall_from_magma_geode_stonecutting", "copper_inferno:magma_geode_wall", "smolderquartz/magma_geode_wall_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_wall", 1, "Stonecutting: cut 1x Magma Geode Wall from Magma Geode.", "Steins\u00e4ge: 1x Magmageodenmauer aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_magma_geode_from_magma_geode_stonecutting", "copper_inferno:polished_magma_geode", "smolderquartz/polished_magma_geode_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:polished_magma_geode", 1, "Stonecutting: cut 1x Polished Magma Geode from Magma Geode.", "Steins\u00e4ge: 1x Polierte Magmageode aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_magma_geode_slab_from_magma_geode_stonecutting", "copper_inferno:polished_magma_geode_slab", "smolderquartz/polished_magma_geode_slab_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:polished_magma_geode_slab", 2, "Stonecutting: cut 2x Polished Magma Geode Slab from Magma Geode.", "Steins\u00e4ge: 2x Polierte Magmageodenstufe aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_magma_geode_stairs_from_magma_geode_stonecutting", "copper_inferno:polished_magma_geode_stairs", "smolderquartz/polished_magma_geode_stairs_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:polished_magma_geode_stairs", 1, "Stonecutting: cut 1x Polished Magma Geode Stairs from Magma Geode.", "Steins\u00e4ge: 1x Polierte Magmageodentreppe aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_magma_geode_wall_from_magma_geode_stonecutting", "copper_inferno:polished_magma_geode_wall", "smolderquartz/polished_magma_geode_wall_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:polished_magma_geode_wall", 1, "Stonecutting: cut 1x Polished Magma Geode Wall from Magma Geode.", "Steins\u00e4ge: 1x Polierte Magmageodenmauer aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_bricks_from_magma_geode_stonecutting", "copper_inferno:magma_geode_bricks", "smolderquartz/magma_geode_bricks_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_bricks", 1, "Stonecutting: cut 1x Magma Geode Bricks from Magma Geode.", "Steins\u00e4ge: 1x Magmageodenziegel aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_brick_slab_from_magma_geode_stonecutting", "copper_inferno:magma_geode_brick_slab", "smolderquartz/magma_geode_brick_slab_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_brick_slab", 2, "Stonecutting: cut 2x Magma Geode Brick Slab from Magma Geode.", "Steins\u00e4ge: 2x Magmageodenziegelstufe aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_brick_stairs_from_magma_geode_stonecutting", "copper_inferno:magma_geode_brick_stairs", "smolderquartz/magma_geode_brick_stairs_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_brick_stairs", 1, "Stonecutting: cut 1x Magma Geode Brick Stairs from Magma Geode.", "Steins\u00e4ge: 1x Magmageodenziegeltreppe aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_brick_wall_from_magma_geode_stonecutting", "copper_inferno:magma_geode_brick_wall", "smolderquartz/magma_geode_brick_wall_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_brick_wall", 1, "Stonecutting: cut 1x Magma Geode Brick Wall from Magma Geode.", "Steins\u00e4ge: 1x Magmageodenziegelmauer aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_tiles_from_magma_geode_stonecutting", "copper_inferno:magma_geode_tiles", "smolderquartz/magma_geode_tiles_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_tiles", 1, "Stonecutting: cut 1x Magma Geode Tiles from Magma Geode.", "Steins\u00e4ge: 1x Magmageodenfliesen aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_magma_geode_bricks_from_magma_geode_stonecutting", "copper_inferno:chiseled_magma_geode_bricks", "smolderquartz/chiseled_magma_geode_bricks_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:chiseled_magma_geode_bricks", 1, "Stonecutting: cut 1x Chiseled Magma Geode Bricks from Magma Geode.", "Steins\u00e4ge: 1x Gemei\u00dfelte Magmageodenziegel aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/magma_geode_pillar_from_magma_geode_stonecutting", "copper_inferno:magma_geode_pillar", "smolderquartz/magma_geode_pillar_from_magma_geode_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:magma_geode", "", "", "", ""},
				"copper_inferno:magma_geode_pillar", 1, "Stonecutting: cut 1x Magma Geode Pillar from Magma Geode.", "Steins\u00e4ge: 1x Magmageodens\u00e4ule aus Magmageode schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal", "copper_inferno:pyrite_crystal", "smolderquartz/pyrite_crystal",
				new String[] {"minecraft:raw_gold", "", "minecraft:raw_gold", "", "copper_inferno:magma_geode", "", "minecraft:raw_gold", "", "minecraft:raw_gold"},
				"copper_inferno:pyrite_crystal", 4, "Craft 4x Pyrite Crystal at a crafting table.", "Stellt 4x Pyritkristall an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "smolderquartz/pyrite_crystal_bricks",
				new String[] {"copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "", "copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_bricks", 4, "Craft 4x Pyrite Crystal Bricks at a crafting table.", "Stellt 4x Pyritkristallziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_tiles", "copper_inferno:pyrite_crystal_tiles", "smolderquartz/pyrite_crystal_tiles",
				new String[] {"copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "", "copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "", "", "", ""},
				"copper_inferno:pyrite_crystal_tiles", 4, "Craft 4x Pyrite Crystal Tiles at a crafting table.", "Stellt 4x Pyritkristallfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "smolderquartz/polished_pyrite_crystal",
				new String[] {"copper_inferno:pyrite_crystal_tiles", "copper_inferno:pyrite_crystal_tiles", "", "copper_inferno:pyrite_crystal_tiles", "copper_inferno:pyrite_crystal_tiles", "", "", "", ""},
				"copper_inferno:polished_pyrite_crystal", 4, "Craft 4x Polished Pyrite Crystal at a crafting table.", "Stellt 4x Polierten Pyritkristall an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_slab", "copper_inferno:pyrite_crystal_slab", "smolderquartz/pyrite_crystal_slab",
				new String[] {"copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "", "", "", "", "", ""},
				"copper_inferno:pyrite_crystal_slab", 6, "Craft 6x Pyrite Crystal Slab at a crafting table.", "Stellt 6x Pyritkristallstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_stairs", "copper_inferno:pyrite_crystal_stairs", "smolderquartz/pyrite_crystal_stairs",
				new String[] {"copper_inferno:pyrite_crystal", "", "", "copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "", "copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal"},
				"copper_inferno:pyrite_crystal_stairs", 4, "Craft 4x Pyrite Crystal Stairs at a crafting table.", "Stellt 4x Pyritkristalltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_wall", "copper_inferno:pyrite_crystal_wall", "smolderquartz/pyrite_crystal_wall",
				new String[] {"copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "copper_inferno:pyrite_crystal", "", "", ""},
				"copper_inferno:pyrite_crystal_wall", 6, "Craft 6x Pyrite Crystal Wall at a crafting table.", "Stellt 6x Pyritkristallmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_pyrite_crystal_slab", "copper_inferno:polished_pyrite_crystal_slab", "smolderquartz/polished_pyrite_crystal_slab",
				new String[] {"copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "", "", "", "", "", ""},
				"copper_inferno:polished_pyrite_crystal_slab", 6, "Craft 6x Polished Pyrite Crystal Slab at a crafting table.", "Stellt 6x Polierte Pyritkristallstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_pyrite_crystal_stairs", "copper_inferno:polished_pyrite_crystal_stairs", "smolderquartz/polished_pyrite_crystal_stairs",
				new String[] {"copper_inferno:polished_pyrite_crystal", "", "", "copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "", "copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal"},
				"copper_inferno:polished_pyrite_crystal_stairs", 4, "Craft 4x Polished Pyrite Crystal Stairs at a crafting table.", "Stellt 4x Polierte Pyritkristalltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_pyrite_crystal_wall", "copper_inferno:polished_pyrite_crystal_wall", "smolderquartz/polished_pyrite_crystal_wall",
				new String[] {"copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "copper_inferno:polished_pyrite_crystal", "", "", ""},
				"copper_inferno:polished_pyrite_crystal_wall", 6, "Craft 6x Polished Pyrite Crystal Wall at a crafting table.", "Stellt 6x Polierte Pyritkristallmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_brick_slab", "copper_inferno:pyrite_crystal_brick_slab", "smolderquartz/pyrite_crystal_brick_slab",
				new String[] {"copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "", "", "", "", "", ""},
				"copper_inferno:pyrite_crystal_brick_slab", 6, "Craft 6x Pyrite Crystal Brick Slab at a crafting table.", "Stellt 6x Pyritkristallziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_brick_stairs", "copper_inferno:pyrite_crystal_brick_stairs", "smolderquartz/pyrite_crystal_brick_stairs",
				new String[] {"copper_inferno:pyrite_crystal_bricks", "", "", "copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "", "copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks"},
				"copper_inferno:pyrite_crystal_brick_stairs", 4, "Craft 4x Pyrite Crystal Brick Stairs at a crafting table.", "Stellt 4x Pyritkristallziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_brick_wall", "copper_inferno:pyrite_crystal_brick_wall", "smolderquartz/pyrite_crystal_brick_wall",
				new String[] {"copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "copper_inferno:pyrite_crystal_bricks", "", "", ""},
				"copper_inferno:pyrite_crystal_brick_wall", 6, "Craft 6x Pyrite Crystal Brick Wall at a crafting table.", "Stellt 6x Pyritkristallziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_pyrite_crystal_bricks", "copper_inferno:cracked_pyrite_crystal_bricks", "smolderquartz/cracked_pyrite_crystal_bricks",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal_bricks", "", "", "", ""},
				"copper_inferno:cracked_pyrite_crystal_bricks", 1, "Smelting Pyrite Crystal Bricks in a furnace yields Cracked Pyrite Crystal Bricks.", "Pyritkristallziegel im Ofen gebrannt ergibt Rissige Pyritkristallziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_pyrite_crystal_bricks", "copper_inferno:chiseled_pyrite_crystal_bricks", "smolderquartz/chiseled_pyrite_crystal_bricks",
				new String[] {"copper_inferno:pyrite_crystal_brick_slab", "", "", "copper_inferno:pyrite_crystal_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_pyrite_crystal_bricks", 1, "Craft 1x Chiseled Pyrite Crystal Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Pyritkristallziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_pillar", "copper_inferno:pyrite_crystal_pillar", "smolderquartz/pyrite_crystal_pillar",
				new String[] {"copper_inferno:pyrite_crystal_bricks", "", "", "copper_inferno:pyrite_crystal_bricks", "", "", "", "", ""},
				"copper_inferno:pyrite_crystal_pillar", 2, "Craft 2x Pyrite Crystal Pillar at a crafting table.", "Stellt 2x Pyritkristalls\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_slab_from_pyrite_crystal_stonecutting", "copper_inferno:pyrite_crystal_slab", "smolderquartz/pyrite_crystal_slab_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_slab", 2, "Stonecutting: cut 2x Pyrite Crystal Slab from Pyrite Crystal.", "Steins\u00e4ge: 2x Pyritkristallstufe aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_stairs_from_pyrite_crystal_stonecutting", "copper_inferno:pyrite_crystal_stairs", "smolderquartz/pyrite_crystal_stairs_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_stairs", 1, "Stonecutting: cut 1x Pyrite Crystal Stairs from Pyrite Crystal.", "Steins\u00e4ge: 1x Pyritkristalltreppe aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_wall_from_pyrite_crystal_stonecutting", "copper_inferno:pyrite_crystal_wall", "smolderquartz/pyrite_crystal_wall_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_wall", 1, "Stonecutting: cut 1x Pyrite Crystal Wall from Pyrite Crystal.", "Steins\u00e4ge: 1x Pyritkristallmauer aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_pyrite_crystal_from_pyrite_crystal_stonecutting", "copper_inferno:polished_pyrite_crystal", "smolderquartz/polished_pyrite_crystal_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:polished_pyrite_crystal", 1, "Stonecutting: cut 1x Polished Pyrite Crystal from Pyrite Crystal.", "Steins\u00e4ge: 1x Polierten Pyritkristall aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_pyrite_crystal_slab_from_pyrite_crystal_stonecutting", "copper_inferno:polished_pyrite_crystal_slab", "smolderquartz/polished_pyrite_crystal_slab_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:polished_pyrite_crystal_slab", 2, "Stonecutting: cut 2x Polished Pyrite Crystal Slab from Pyrite Crystal.", "Steins\u00e4ge: 2x Polierte Pyritkristallstufe aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_pyrite_crystal_stairs_from_pyrite_crystal_stonecutting", "copper_inferno:polished_pyrite_crystal_stairs", "smolderquartz/polished_pyrite_crystal_stairs_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:polished_pyrite_crystal_stairs", 1, "Stonecutting: cut 1x Polished Pyrite Crystal Stairs from Pyrite Crystal.", "Steins\u00e4ge: 1x Polierte Pyritkristalltreppe aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_pyrite_crystal_wall_from_pyrite_crystal_stonecutting", "copper_inferno:polished_pyrite_crystal_wall", "smolderquartz/polished_pyrite_crystal_wall_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:polished_pyrite_crystal_wall", 1, "Stonecutting: cut 1x Polished Pyrite Crystal Wall from Pyrite Crystal.", "Steins\u00e4ge: 1x Polierte Pyritkristallmauer aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_bricks_from_pyrite_crystal_stonecutting", "copper_inferno:pyrite_crystal_bricks", "smolderquartz/pyrite_crystal_bricks_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_bricks", 1, "Stonecutting: cut 1x Pyrite Crystal Bricks from Pyrite Crystal.", "Steins\u00e4ge: 1x Pyritkristallziegel aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_brick_slab_from_pyrite_crystal_stonecutting", "copper_inferno:pyrite_crystal_brick_slab", "smolderquartz/pyrite_crystal_brick_slab_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_brick_slab", 2, "Stonecutting: cut 2x Pyrite Crystal Brick Slab from Pyrite Crystal.", "Steins\u00e4ge: 2x Pyritkristallziegelstufe aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_brick_stairs_from_pyrite_crystal_stonecutting", "copper_inferno:pyrite_crystal_brick_stairs", "smolderquartz/pyrite_crystal_brick_stairs_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_brick_stairs", 1, "Stonecutting: cut 1x Pyrite Crystal Brick Stairs from Pyrite Crystal.", "Steins\u00e4ge: 1x Pyritkristallziegeltreppe aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_brick_wall_from_pyrite_crystal_stonecutting", "copper_inferno:pyrite_crystal_brick_wall", "smolderquartz/pyrite_crystal_brick_wall_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_brick_wall", 1, "Stonecutting: cut 1x Pyrite Crystal Brick Wall from Pyrite Crystal.", "Steins\u00e4ge: 1x Pyritkristallziegelmauer aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_tiles_from_pyrite_crystal_stonecutting", "copper_inferno:pyrite_crystal_tiles", "smolderquartz/pyrite_crystal_tiles_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_tiles", 1, "Stonecutting: cut 1x Pyrite Crystal Tiles from Pyrite Crystal.", "Steins\u00e4ge: 1x Pyritkristallfliesen aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_pyrite_crystal_bricks_from_pyrite_crystal_stonecutting", "copper_inferno:chiseled_pyrite_crystal_bricks", "smolderquartz/chiseled_pyrite_crystal_bricks_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:chiseled_pyrite_crystal_bricks", 1, "Stonecutting: cut 1x Chiseled Pyrite Crystal Bricks from Pyrite Crystal.", "Steins\u00e4ge: 1x Gemei\u00dfelte Pyritkristallziegel aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/pyrite_crystal_pillar_from_pyrite_crystal_stonecutting", "copper_inferno:pyrite_crystal_pillar", "smolderquartz/pyrite_crystal_pillar_from_pyrite_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:pyrite_crystal", "", "", "", ""},
				"copper_inferno:pyrite_crystal_pillar", 1, "Stonecutting: cut 1x Pyrite Crystal Pillar from Pyrite Crystal.", "Steins\u00e4ge: 1x Pyritkristalls\u00e4ule aus Pyritkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal", "copper_inferno:sulfur_crystal", "smolderquartz/sulfur_crystal",
				new String[] {"minecraft:gunpowder", "", "minecraft:gunpowder", "", "copper_inferno:pyrite_crystal", "", "minecraft:gunpowder", "", "minecraft:gunpowder"},
				"copper_inferno:sulfur_crystal", 4, "Craft 4x Sulfur Crystal at a crafting table.", "Stellt 4x Schwefelkristall an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "smolderquartz/sulfur_crystal_bricks",
				new String[] {"copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "", "copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_bricks", 4, "Craft 4x Sulfur Crystal Bricks at a crafting table.", "Stellt 4x Schwefelkristallziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_tiles", "copper_inferno:sulfur_crystal_tiles", "smolderquartz/sulfur_crystal_tiles",
				new String[] {"copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "", "copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "", "", "", ""},
				"copper_inferno:sulfur_crystal_tiles", 4, "Craft 4x Sulfur Crystal Tiles at a crafting table.", "Stellt 4x Schwefelkristallfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "smolderquartz/polished_sulfur_crystal",
				new String[] {"copper_inferno:sulfur_crystal_tiles", "copper_inferno:sulfur_crystal_tiles", "", "copper_inferno:sulfur_crystal_tiles", "copper_inferno:sulfur_crystal_tiles", "", "", "", ""},
				"copper_inferno:polished_sulfur_crystal", 4, "Craft 4x Polished Sulfur Crystal at a crafting table.", "Stellt 4x Polierten Schwefelkristall an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_slab", "copper_inferno:sulfur_crystal_slab", "smolderquartz/sulfur_crystal_slab",
				new String[] {"copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "", "", "", "", "", ""},
				"copper_inferno:sulfur_crystal_slab", 6, "Craft 6x Sulfur Crystal Slab at a crafting table.", "Stellt 6x Schwefelkristallstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_stairs", "copper_inferno:sulfur_crystal_stairs", "smolderquartz/sulfur_crystal_stairs",
				new String[] {"copper_inferno:sulfur_crystal", "", "", "copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "", "copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal"},
				"copper_inferno:sulfur_crystal_stairs", 4, "Craft 4x Sulfur Crystal Stairs at a crafting table.", "Stellt 4x Schwefelkristalltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_wall", "copper_inferno:sulfur_crystal_wall", "smolderquartz/sulfur_crystal_wall",
				new String[] {"copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "copper_inferno:sulfur_crystal", "", "", ""},
				"copper_inferno:sulfur_crystal_wall", 6, "Craft 6x Sulfur Crystal Wall at a crafting table.", "Stellt 6x Schwefelkristallmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_sulfur_crystal_slab", "copper_inferno:polished_sulfur_crystal_slab", "smolderquartz/polished_sulfur_crystal_slab",
				new String[] {"copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "", "", "", "", "", ""},
				"copper_inferno:polished_sulfur_crystal_slab", 6, "Craft 6x Polished Sulfur Crystal Slab at a crafting table.", "Stellt 6x Polierte Schwefelkristallstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_sulfur_crystal_stairs", "copper_inferno:polished_sulfur_crystal_stairs", "smolderquartz/polished_sulfur_crystal_stairs",
				new String[] {"copper_inferno:polished_sulfur_crystal", "", "", "copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "", "copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal"},
				"copper_inferno:polished_sulfur_crystal_stairs", 4, "Craft 4x Polished Sulfur Crystal Stairs at a crafting table.", "Stellt 4x Polierte Schwefelkristalltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_sulfur_crystal_wall", "copper_inferno:polished_sulfur_crystal_wall", "smolderquartz/polished_sulfur_crystal_wall",
				new String[] {"copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "copper_inferno:polished_sulfur_crystal", "", "", ""},
				"copper_inferno:polished_sulfur_crystal_wall", 6, "Craft 6x Polished Sulfur Crystal Wall at a crafting table.", "Stellt 6x Polierte Schwefelkristallmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_brick_slab", "copper_inferno:sulfur_crystal_brick_slab", "smolderquartz/sulfur_crystal_brick_slab",
				new String[] {"copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "", "", "", "", "", ""},
				"copper_inferno:sulfur_crystal_brick_slab", 6, "Craft 6x Sulfur Crystal Brick Slab at a crafting table.", "Stellt 6x Schwefelkristallziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_brick_stairs", "copper_inferno:sulfur_crystal_brick_stairs", "smolderquartz/sulfur_crystal_brick_stairs",
				new String[] {"copper_inferno:sulfur_crystal_bricks", "", "", "copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "", "copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks"},
				"copper_inferno:sulfur_crystal_brick_stairs", 4, "Craft 4x Sulfur Crystal Brick Stairs at a crafting table.", "Stellt 4x Schwefelkristallziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_brick_wall", "copper_inferno:sulfur_crystal_brick_wall", "smolderquartz/sulfur_crystal_brick_wall",
				new String[] {"copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "copper_inferno:sulfur_crystal_bricks", "", "", ""},
				"copper_inferno:sulfur_crystal_brick_wall", 6, "Craft 6x Sulfur Crystal Brick Wall at a crafting table.", "Stellt 6x Schwefelkristallziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_sulfur_crystal_bricks", "copper_inferno:cracked_sulfur_crystal_bricks", "smolderquartz/cracked_sulfur_crystal_bricks",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal_bricks", "", "", "", ""},
				"copper_inferno:cracked_sulfur_crystal_bricks", 1, "Smelting Sulfur Crystal Bricks in a furnace yields Cracked Sulfur Crystal Bricks.", "Schwefelkristallziegel im Ofen gebrannt ergibt Rissige Schwefelkristallziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_sulfur_crystal_bricks", "copper_inferno:chiseled_sulfur_crystal_bricks", "smolderquartz/chiseled_sulfur_crystal_bricks",
				new String[] {"copper_inferno:sulfur_crystal_brick_slab", "", "", "copper_inferno:sulfur_crystal_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_sulfur_crystal_bricks", 1, "Craft 1x Chiseled Sulfur Crystal Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schwefelkristallziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_pillar", "copper_inferno:sulfur_crystal_pillar", "smolderquartz/sulfur_crystal_pillar",
				new String[] {"copper_inferno:sulfur_crystal_bricks", "", "", "copper_inferno:sulfur_crystal_bricks", "", "", "", "", ""},
				"copper_inferno:sulfur_crystal_pillar", 2, "Craft 2x Sulfur Crystal Pillar at a crafting table.", "Stellt 2x Schwefelkristalls\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_slab_from_sulfur_crystal_stonecutting", "copper_inferno:sulfur_crystal_slab", "smolderquartz/sulfur_crystal_slab_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_slab", 2, "Stonecutting: cut 2x Sulfur Crystal Slab from Sulfur Crystal.", "Steins\u00e4ge: 2x Schwefelkristallstufe aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_stairs_from_sulfur_crystal_stonecutting", "copper_inferno:sulfur_crystal_stairs", "smolderquartz/sulfur_crystal_stairs_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_stairs", 1, "Stonecutting: cut 1x Sulfur Crystal Stairs from Sulfur Crystal.", "Steins\u00e4ge: 1x Schwefelkristalltreppe aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_wall_from_sulfur_crystal_stonecutting", "copper_inferno:sulfur_crystal_wall", "smolderquartz/sulfur_crystal_wall_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_wall", 1, "Stonecutting: cut 1x Sulfur Crystal Wall from Sulfur Crystal.", "Steins\u00e4ge: 1x Schwefelkristallmauer aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_sulfur_crystal_from_sulfur_crystal_stonecutting", "copper_inferno:polished_sulfur_crystal", "smolderquartz/polished_sulfur_crystal_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:polished_sulfur_crystal", 1, "Stonecutting: cut 1x Polished Sulfur Crystal from Sulfur Crystal.", "Steins\u00e4ge: 1x Polierten Schwefelkristall aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_sulfur_crystal_slab_from_sulfur_crystal_stonecutting", "copper_inferno:polished_sulfur_crystal_slab", "smolderquartz/polished_sulfur_crystal_slab_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:polished_sulfur_crystal_slab", 2, "Stonecutting: cut 2x Polished Sulfur Crystal Slab from Sulfur Crystal.", "Steins\u00e4ge: 2x Polierte Schwefelkristallstufe aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_sulfur_crystal_stairs_from_sulfur_crystal_stonecutting", "copper_inferno:polished_sulfur_crystal_stairs", "smolderquartz/polished_sulfur_crystal_stairs_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:polished_sulfur_crystal_stairs", 1, "Stonecutting: cut 1x Polished Sulfur Crystal Stairs from Sulfur Crystal.", "Steins\u00e4ge: 1x Polierte Schwefelkristalltreppe aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_sulfur_crystal_wall_from_sulfur_crystal_stonecutting", "copper_inferno:polished_sulfur_crystal_wall", "smolderquartz/polished_sulfur_crystal_wall_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:polished_sulfur_crystal_wall", 1, "Stonecutting: cut 1x Polished Sulfur Crystal Wall from Sulfur Crystal.", "Steins\u00e4ge: 1x Polierte Schwefelkristallmauer aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_bricks_from_sulfur_crystal_stonecutting", "copper_inferno:sulfur_crystal_bricks", "smolderquartz/sulfur_crystal_bricks_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_bricks", 1, "Stonecutting: cut 1x Sulfur Crystal Bricks from Sulfur Crystal.", "Steins\u00e4ge: 1x Schwefelkristallziegel aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_brick_slab_from_sulfur_crystal_stonecutting", "copper_inferno:sulfur_crystal_brick_slab", "smolderquartz/sulfur_crystal_brick_slab_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_brick_slab", 2, "Stonecutting: cut 2x Sulfur Crystal Brick Slab from Sulfur Crystal.", "Steins\u00e4ge: 2x Schwefelkristallziegelstufe aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_brick_stairs_from_sulfur_crystal_stonecutting", "copper_inferno:sulfur_crystal_brick_stairs", "smolderquartz/sulfur_crystal_brick_stairs_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_brick_stairs", 1, "Stonecutting: cut 1x Sulfur Crystal Brick Stairs from Sulfur Crystal.", "Steins\u00e4ge: 1x Schwefelkristallziegeltreppe aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_brick_wall_from_sulfur_crystal_stonecutting", "copper_inferno:sulfur_crystal_brick_wall", "smolderquartz/sulfur_crystal_brick_wall_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_brick_wall", 1, "Stonecutting: cut 1x Sulfur Crystal Brick Wall from Sulfur Crystal.", "Steins\u00e4ge: 1x Schwefelkristallziegelmauer aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_tiles_from_sulfur_crystal_stonecutting", "copper_inferno:sulfur_crystal_tiles", "smolderquartz/sulfur_crystal_tiles_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_tiles", 1, "Stonecutting: cut 1x Sulfur Crystal Tiles from Sulfur Crystal.", "Steins\u00e4ge: 1x Schwefelkristallfliesen aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_sulfur_crystal_bricks_from_sulfur_crystal_stonecutting", "copper_inferno:chiseled_sulfur_crystal_bricks", "smolderquartz/chiseled_sulfur_crystal_bricks_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:chiseled_sulfur_crystal_bricks", 1, "Stonecutting: cut 1x Chiseled Sulfur Crystal Bricks from Sulfur Crystal.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schwefelkristallziegel aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/sulfur_crystal_pillar_from_sulfur_crystal_stonecutting", "copper_inferno:sulfur_crystal_pillar", "smolderquartz/sulfur_crystal_pillar_from_sulfur_crystal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:sulfur_crystal", "", "", "", ""},
				"copper_inferno:sulfur_crystal_pillar", 1, "Stonecutting: cut 1x Sulfur Crystal Pillar from Sulfur Crystal.", "Steins\u00e4ge: 1x Schwefelkristalls\u00e4ule aus Schwefelkristall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock", "copper_inferno:obsid_glassblock", "smolderquartz/obsid_glassblock",
				new String[] {"minecraft:obsidian", "", "minecraft:obsidian", "", "copper_inferno:sulfur_crystal", "", "minecraft:obsidian", "", "minecraft:obsidian"},
				"copper_inferno:obsid_glassblock", 4, "Craft 4x Obsid Glassblock at a crafting table.", "Stellt 4x Obsidglasblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "smolderquartz/obsid_glassblock_bricks",
				new String[] {"copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "", "copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_bricks", 4, "Craft 4x Obsid Glassblock Bricks at a crafting table.", "Stellt 4x Obsidglasziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_tiles", "copper_inferno:obsid_glassblock_tiles", "smolderquartz/obsid_glassblock_tiles",
				new String[] {"copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "", "copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "", "", "", ""},
				"copper_inferno:obsid_glassblock_tiles", 4, "Craft 4x Obsid Glassblock Tiles at a crafting table.", "Stellt 4x Obsidglasfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "smolderquartz/polished_obsid_glassblock",
				new String[] {"copper_inferno:obsid_glassblock_tiles", "copper_inferno:obsid_glassblock_tiles", "", "copper_inferno:obsid_glassblock_tiles", "copper_inferno:obsid_glassblock_tiles", "", "", "", ""},
				"copper_inferno:polished_obsid_glassblock", 4, "Craft 4x Polished Obsid Glassblock at a crafting table.", "Stellt 4x Polierten Obsidglasblock an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_slab", "copper_inferno:obsid_glassblock_slab", "smolderquartz/obsid_glassblock_slab",
				new String[] {"copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "", "", "", "", "", ""},
				"copper_inferno:obsid_glassblock_slab", 6, "Craft 6x Obsid Glassblock Slab at a crafting table.", "Stellt 6x Obsidglasstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_stairs", "copper_inferno:obsid_glassblock_stairs", "smolderquartz/obsid_glassblock_stairs",
				new String[] {"copper_inferno:obsid_glassblock", "", "", "copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "", "copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock"},
				"copper_inferno:obsid_glassblock_stairs", 4, "Craft 4x Obsid Glassblock Stairs at a crafting table.", "Stellt 4x Obsidglastreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_wall", "copper_inferno:obsid_glassblock_wall", "smolderquartz/obsid_glassblock_wall",
				new String[] {"copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "copper_inferno:obsid_glassblock", "", "", ""},
				"copper_inferno:obsid_glassblock_wall", 6, "Craft 6x Obsid Glassblock Wall at a crafting table.", "Stellt 6x Obsidglasmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_obsid_glassblock_slab", "copper_inferno:polished_obsid_glassblock_slab", "smolderquartz/polished_obsid_glassblock_slab",
				new String[] {"copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "", "", "", "", "", ""},
				"copper_inferno:polished_obsid_glassblock_slab", 6, "Craft 6x Polished Obsid Glassblock Slab at a crafting table.", "Stellt 6x Polierte Obsidglasstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_obsid_glassblock_stairs", "copper_inferno:polished_obsid_glassblock_stairs", "smolderquartz/polished_obsid_glassblock_stairs",
				new String[] {"copper_inferno:polished_obsid_glassblock", "", "", "copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "", "copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock"},
				"copper_inferno:polished_obsid_glassblock_stairs", 4, "Craft 4x Polished Obsid Glassblock Stairs at a crafting table.", "Stellt 4x Polierte Obsidglastreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_obsid_glassblock_wall", "copper_inferno:polished_obsid_glassblock_wall", "smolderquartz/polished_obsid_glassblock_wall",
				new String[] {"copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "copper_inferno:polished_obsid_glassblock", "", "", ""},
				"copper_inferno:polished_obsid_glassblock_wall", 6, "Craft 6x Polished Obsid Glassblock Wall at a crafting table.", "Stellt 6x Polierte Obsidglasmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_brick_slab", "copper_inferno:obsid_glassblock_brick_slab", "smolderquartz/obsid_glassblock_brick_slab",
				new String[] {"copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "", "", "", "", "", ""},
				"copper_inferno:obsid_glassblock_brick_slab", 6, "Craft 6x Obsid Glassblock Brick Slab at a crafting table.", "Stellt 6x Obsidglasziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_brick_stairs", "copper_inferno:obsid_glassblock_brick_stairs", "smolderquartz/obsid_glassblock_brick_stairs",
				new String[] {"copper_inferno:obsid_glassblock_bricks", "", "", "copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "", "copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks"},
				"copper_inferno:obsid_glassblock_brick_stairs", 4, "Craft 4x Obsid Glassblock Brick Stairs at a crafting table.", "Stellt 4x Obsidglasziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_brick_wall", "copper_inferno:obsid_glassblock_brick_wall", "smolderquartz/obsid_glassblock_brick_wall",
				new String[] {"copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "copper_inferno:obsid_glassblock_bricks", "", "", ""},
				"copper_inferno:obsid_glassblock_brick_wall", 6, "Craft 6x Obsid Glassblock Brick Wall at a crafting table.", "Stellt 6x Obsidglasziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_obsid_glassblock_bricks", "copper_inferno:cracked_obsid_glassblock_bricks", "smolderquartz/cracked_obsid_glassblock_bricks",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock_bricks", "", "", "", ""},
				"copper_inferno:cracked_obsid_glassblock_bricks", 1, "Smelting Obsid Glassblock Bricks in a furnace yields Cracked Obsid Glassblock Bricks.", "Obsidglasziegel im Ofen gebrannt ergibt Rissige Obsidglasziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_obsid_glassblock_bricks", "copper_inferno:chiseled_obsid_glassblock_bricks", "smolderquartz/chiseled_obsid_glassblock_bricks",
				new String[] {"copper_inferno:obsid_glassblock_brick_slab", "", "", "copper_inferno:obsid_glassblock_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_obsid_glassblock_bricks", 1, "Craft 1x Chiseled Obsid Glassblock Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Obsidglasziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_pillar", "copper_inferno:obsid_glassblock_pillar", "smolderquartz/obsid_glassblock_pillar",
				new String[] {"copper_inferno:obsid_glassblock_bricks", "", "", "copper_inferno:obsid_glassblock_bricks", "", "", "", "", ""},
				"copper_inferno:obsid_glassblock_pillar", 2, "Craft 2x Obsid Glassblock Pillar at a crafting table.", "Stellt 2x Obsidglass\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_slab_from_obsid_glassblock_stonecutting", "copper_inferno:obsid_glassblock_slab", "smolderquartz/obsid_glassblock_slab_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_slab", 2, "Stonecutting: cut 2x Obsid Glassblock Slab from Obsid Glassblock.", "Steins\u00e4ge: 2x Obsidglasstufe aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_stairs_from_obsid_glassblock_stonecutting", "copper_inferno:obsid_glassblock_stairs", "smolderquartz/obsid_glassblock_stairs_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_stairs", 1, "Stonecutting: cut 1x Obsid Glassblock Stairs from Obsid Glassblock.", "Steins\u00e4ge: 1x Obsidglastreppe aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_wall_from_obsid_glassblock_stonecutting", "copper_inferno:obsid_glassblock_wall", "smolderquartz/obsid_glassblock_wall_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_wall", 1, "Stonecutting: cut 1x Obsid Glassblock Wall from Obsid Glassblock.", "Steins\u00e4ge: 1x Obsidglasmauer aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_obsid_glassblock_from_obsid_glassblock_stonecutting", "copper_inferno:polished_obsid_glassblock", "smolderquartz/polished_obsid_glassblock_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:polished_obsid_glassblock", 1, "Stonecutting: cut 1x Polished Obsid Glassblock from Obsid Glassblock.", "Steins\u00e4ge: 1x Polierten Obsidglasblock aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_obsid_glassblock_slab_from_obsid_glassblock_stonecutting", "copper_inferno:polished_obsid_glassblock_slab", "smolderquartz/polished_obsid_glassblock_slab_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:polished_obsid_glassblock_slab", 2, "Stonecutting: cut 2x Polished Obsid Glassblock Slab from Obsid Glassblock.", "Steins\u00e4ge: 2x Polierte Obsidglasstufe aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_obsid_glassblock_stairs_from_obsid_glassblock_stonecutting", "copper_inferno:polished_obsid_glassblock_stairs", "smolderquartz/polished_obsid_glassblock_stairs_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:polished_obsid_glassblock_stairs", 1, "Stonecutting: cut 1x Polished Obsid Glassblock Stairs from Obsid Glassblock.", "Steins\u00e4ge: 1x Polierte Obsidglastreppe aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_obsid_glassblock_wall_from_obsid_glassblock_stonecutting", "copper_inferno:polished_obsid_glassblock_wall", "smolderquartz/polished_obsid_glassblock_wall_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:polished_obsid_glassblock_wall", 1, "Stonecutting: cut 1x Polished Obsid Glassblock Wall from Obsid Glassblock.", "Steins\u00e4ge: 1x Polierte Obsidglasmauer aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_bricks_from_obsid_glassblock_stonecutting", "copper_inferno:obsid_glassblock_bricks", "smolderquartz/obsid_glassblock_bricks_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_bricks", 1, "Stonecutting: cut 1x Obsid Glassblock Bricks from Obsid Glassblock.", "Steins\u00e4ge: 1x Obsidglasziegel aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_brick_slab_from_obsid_glassblock_stonecutting", "copper_inferno:obsid_glassblock_brick_slab", "smolderquartz/obsid_glassblock_brick_slab_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_brick_slab", 2, "Stonecutting: cut 2x Obsid Glassblock Brick Slab from Obsid Glassblock.", "Steins\u00e4ge: 2x Obsidglasziegelstufe aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_brick_stairs_from_obsid_glassblock_stonecutting", "copper_inferno:obsid_glassblock_brick_stairs", "smolderquartz/obsid_glassblock_brick_stairs_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_brick_stairs", 1, "Stonecutting: cut 1x Obsid Glassblock Brick Stairs from Obsid Glassblock.", "Steins\u00e4ge: 1x Obsidglasziegeltreppe aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_brick_wall_from_obsid_glassblock_stonecutting", "copper_inferno:obsid_glassblock_brick_wall", "smolderquartz/obsid_glassblock_brick_wall_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_brick_wall", 1, "Stonecutting: cut 1x Obsid Glassblock Brick Wall from Obsid Glassblock.", "Steins\u00e4ge: 1x Obsidglasziegelmauer aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_tiles_from_obsid_glassblock_stonecutting", "copper_inferno:obsid_glassblock_tiles", "smolderquartz/obsid_glassblock_tiles_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_tiles", 1, "Stonecutting: cut 1x Obsid Glassblock Tiles from Obsid Glassblock.", "Steins\u00e4ge: 1x Obsidglasfliesen aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_obsid_glassblock_bricks_from_obsid_glassblock_stonecutting", "copper_inferno:chiseled_obsid_glassblock_bricks", "smolderquartz/chiseled_obsid_glassblock_bricks_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:chiseled_obsid_glassblock_bricks", 1, "Stonecutting: cut 1x Chiseled Obsid Glassblock Bricks from Obsid Glassblock.", "Steins\u00e4ge: 1x Gemei\u00dfelte Obsidglasziegel aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/obsid_glassblock_pillar_from_obsid_glassblock_stonecutting", "copper_inferno:obsid_glassblock_pillar", "smolderquartz/obsid_glassblock_pillar_from_obsid_glassblock_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:obsid_glassblock", "", "", "", ""},
				"copper_inferno:obsid_glassblock_pillar", 1, "Stonecutting: cut 1x Obsid Glassblock Pillar from Obsid Glassblock.", "Steins\u00e4ge: 1x Obsidglass\u00e4ule aus Obsidglasblock schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber", "copper_inferno:ember_amber", "smolderquartz/ember_amber",
				new String[] {"minecraft:resin_clump", "", "minecraft:resin_clump", "", "copper_inferno:obsid_glassblock", "", "minecraft:resin_clump", "", "minecraft:resin_clump"},
				"copper_inferno:ember_amber", 4, "Craft 4x Ember Amber at a crafting table.", "Stellt 4x Glutbernstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_bricks", "copper_inferno:ember_amber_bricks", "smolderquartz/ember_amber_bricks",
				new String[] {"copper_inferno:ember_amber", "copper_inferno:ember_amber", "", "copper_inferno:ember_amber", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_bricks", 4, "Craft 4x Ember Amber Bricks at a crafting table.", "Stellt 4x Glutbernsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_tiles", "copper_inferno:ember_amber_tiles", "smolderquartz/ember_amber_tiles",
				new String[] {"copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "", "copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "", "", "", ""},
				"copper_inferno:ember_amber_tiles", 4, "Craft 4x Ember Amber Tiles at a crafting table.", "Stellt 4x Glutbernsteinfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_amber", "copper_inferno:polished_ember_amber", "smolderquartz/polished_ember_amber",
				new String[] {"copper_inferno:ember_amber_tiles", "copper_inferno:ember_amber_tiles", "", "copper_inferno:ember_amber_tiles", "copper_inferno:ember_amber_tiles", "", "", "", ""},
				"copper_inferno:polished_ember_amber", 4, "Craft 4x Polished Ember Amber at a crafting table.", "Stellt 4x Polierten Glutbernstein an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_slab", "copper_inferno:ember_amber_slab", "smolderquartz/ember_amber_slab",
				new String[] {"copper_inferno:ember_amber", "copper_inferno:ember_amber", "copper_inferno:ember_amber", "", "", "", "", "", ""},
				"copper_inferno:ember_amber_slab", 6, "Craft 6x Ember Amber Slab at a crafting table.", "Stellt 6x Glutbernsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_stairs", "copper_inferno:ember_amber_stairs", "smolderquartz/ember_amber_stairs",
				new String[] {"copper_inferno:ember_amber", "", "", "copper_inferno:ember_amber", "copper_inferno:ember_amber", "", "copper_inferno:ember_amber", "copper_inferno:ember_amber", "copper_inferno:ember_amber"},
				"copper_inferno:ember_amber_stairs", 4, "Craft 4x Ember Amber Stairs at a crafting table.", "Stellt 4x Glutbernsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_wall", "copper_inferno:ember_amber_wall", "smolderquartz/ember_amber_wall",
				new String[] {"copper_inferno:ember_amber", "copper_inferno:ember_amber", "copper_inferno:ember_amber", "copper_inferno:ember_amber", "copper_inferno:ember_amber", "copper_inferno:ember_amber", "", "", ""},
				"copper_inferno:ember_amber_wall", 6, "Craft 6x Ember Amber Wall at a crafting table.", "Stellt 6x Glutbernsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_amber_slab", "copper_inferno:polished_ember_amber_slab", "smolderquartz/polished_ember_amber_slab",
				new String[] {"copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber", "", "", "", "", "", ""},
				"copper_inferno:polished_ember_amber_slab", 6, "Craft 6x Polished Ember Amber Slab at a crafting table.", "Stellt 6x Polierte Glutbernsteinstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_amber_stairs", "copper_inferno:polished_ember_amber_stairs", "smolderquartz/polished_ember_amber_stairs",
				new String[] {"copper_inferno:polished_ember_amber", "", "", "copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber", "", "copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber"},
				"copper_inferno:polished_ember_amber_stairs", 4, "Craft 4x Polished Ember Amber Stairs at a crafting table.", "Stellt 4x Polierte Glutbernsteintreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_amber_wall", "copper_inferno:polished_ember_amber_wall", "smolderquartz/polished_ember_amber_wall",
				new String[] {"copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber", "copper_inferno:polished_ember_amber", "", "", ""},
				"copper_inferno:polished_ember_amber_wall", 6, "Craft 6x Polished Ember Amber Wall at a crafting table.", "Stellt 6x Polierte Glutbernsteinmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_brick_slab", "copper_inferno:ember_amber_brick_slab", "smolderquartz/ember_amber_brick_slab",
				new String[] {"copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "", "", "", "", "", ""},
				"copper_inferno:ember_amber_brick_slab", 6, "Craft 6x Ember Amber Brick Slab at a crafting table.", "Stellt 6x Glutbernsteinziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_brick_stairs", "copper_inferno:ember_amber_brick_stairs", "smolderquartz/ember_amber_brick_stairs",
				new String[] {"copper_inferno:ember_amber_bricks", "", "", "copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "", "copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks"},
				"copper_inferno:ember_amber_brick_stairs", 4, "Craft 4x Ember Amber Brick Stairs at a crafting table.", "Stellt 4x Glutbernsteinziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_brick_wall", "copper_inferno:ember_amber_brick_wall", "smolderquartz/ember_amber_brick_wall",
				new String[] {"copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "copper_inferno:ember_amber_bricks", "", "", ""},
				"copper_inferno:ember_amber_brick_wall", 6, "Craft 6x Ember Amber Brick Wall at a crafting table.", "Stellt 6x Glutbernsteinziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_ember_amber_bricks", "copper_inferno:cracked_ember_amber_bricks", "smolderquartz/cracked_ember_amber_bricks",
				new String[] {"", "", "", "", "copper_inferno:ember_amber_bricks", "", "", "", ""},
				"copper_inferno:cracked_ember_amber_bricks", 1, "Smelting Ember Amber Bricks in a furnace yields Cracked Ember Amber Bricks.", "Glutbernsteinziegel im Ofen gebrannt ergibt Rissige Glutbernsteinziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_ember_amber_bricks", "copper_inferno:chiseled_ember_amber_bricks", "smolderquartz/chiseled_ember_amber_bricks",
				new String[] {"copper_inferno:ember_amber_brick_slab", "", "", "copper_inferno:ember_amber_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_ember_amber_bricks", 1, "Craft 1x Chiseled Ember Amber Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glutbernsteinziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_pillar", "copper_inferno:ember_amber_pillar", "smolderquartz/ember_amber_pillar",
				new String[] {"copper_inferno:ember_amber_bricks", "", "", "copper_inferno:ember_amber_bricks", "", "", "", "", ""},
				"copper_inferno:ember_amber_pillar", 2, "Craft 2x Ember Amber Pillar at a crafting table.", "Stellt 2x Glutbernsteins\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_slab_from_ember_amber_stonecutting", "copper_inferno:ember_amber_slab", "smolderquartz/ember_amber_slab_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_slab", 2, "Stonecutting: cut 2x Ember Amber Slab from Ember Amber.", "Steins\u00e4ge: 2x Glutbernsteinstufe aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_stairs_from_ember_amber_stonecutting", "copper_inferno:ember_amber_stairs", "smolderquartz/ember_amber_stairs_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_stairs", 1, "Stonecutting: cut 1x Ember Amber Stairs from Ember Amber.", "Steins\u00e4ge: 1x Glutbernsteintreppe aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_wall_from_ember_amber_stonecutting", "copper_inferno:ember_amber_wall", "smolderquartz/ember_amber_wall_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_wall", 1, "Stonecutting: cut 1x Ember Amber Wall from Ember Amber.", "Steins\u00e4ge: 1x Glutbernsteinmauer aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_amber_from_ember_amber_stonecutting", "copper_inferno:polished_ember_amber", "smolderquartz/polished_ember_amber_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:polished_ember_amber", 1, "Stonecutting: cut 1x Polished Ember Amber from Ember Amber.", "Steins\u00e4ge: 1x Polierten Glutbernstein aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_amber_slab_from_ember_amber_stonecutting", "copper_inferno:polished_ember_amber_slab", "smolderquartz/polished_ember_amber_slab_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:polished_ember_amber_slab", 2, "Stonecutting: cut 2x Polished Ember Amber Slab from Ember Amber.", "Steins\u00e4ge: 2x Polierte Glutbernsteinstufe aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_amber_stairs_from_ember_amber_stonecutting", "copper_inferno:polished_ember_amber_stairs", "smolderquartz/polished_ember_amber_stairs_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:polished_ember_amber_stairs", 1, "Stonecutting: cut 1x Polished Ember Amber Stairs from Ember Amber.", "Steins\u00e4ge: 1x Polierte Glutbernsteintreppe aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_ember_amber_wall_from_ember_amber_stonecutting", "copper_inferno:polished_ember_amber_wall", "smolderquartz/polished_ember_amber_wall_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:polished_ember_amber_wall", 1, "Stonecutting: cut 1x Polished Ember Amber Wall from Ember Amber.", "Steins\u00e4ge: 1x Polierte Glutbernsteinmauer aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_bricks_from_ember_amber_stonecutting", "copper_inferno:ember_amber_bricks", "smolderquartz/ember_amber_bricks_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_bricks", 1, "Stonecutting: cut 1x Ember Amber Bricks from Ember Amber.", "Steins\u00e4ge: 1x Glutbernsteinziegel aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_brick_slab_from_ember_amber_stonecutting", "copper_inferno:ember_amber_brick_slab", "smolderquartz/ember_amber_brick_slab_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_brick_slab", 2, "Stonecutting: cut 2x Ember Amber Brick Slab from Ember Amber.", "Steins\u00e4ge: 2x Glutbernsteinziegelstufe aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_brick_stairs_from_ember_amber_stonecutting", "copper_inferno:ember_amber_brick_stairs", "smolderquartz/ember_amber_brick_stairs_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_brick_stairs", 1, "Stonecutting: cut 1x Ember Amber Brick Stairs from Ember Amber.", "Steins\u00e4ge: 1x Glutbernsteinziegeltreppe aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_brick_wall_from_ember_amber_stonecutting", "copper_inferno:ember_amber_brick_wall", "smolderquartz/ember_amber_brick_wall_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_brick_wall", 1, "Stonecutting: cut 1x Ember Amber Brick Wall from Ember Amber.", "Steins\u00e4ge: 1x Glutbernsteinziegelmauer aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_tiles_from_ember_amber_stonecutting", "copper_inferno:ember_amber_tiles", "smolderquartz/ember_amber_tiles_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_tiles", 1, "Stonecutting: cut 1x Ember Amber Tiles from Ember Amber.", "Steins\u00e4ge: 1x Glutbernsteinfliesen aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_ember_amber_bricks_from_ember_amber_stonecutting", "copper_inferno:chiseled_ember_amber_bricks", "smolderquartz/chiseled_ember_amber_bricks_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:chiseled_ember_amber_bricks", 1, "Stonecutting: cut 1x Chiseled Ember Amber Bricks from Ember Amber.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glutbernsteinziegel aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/ember_amber_pillar_from_ember_amber_stonecutting", "copper_inferno:ember_amber_pillar", "smolderquartz/ember_amber_pillar_from_ember_amber_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_amber", "", "", "", ""},
				"copper_inferno:ember_amber_pillar", 1, "Stonecutting: cut 1x Ember Amber Pillar from Ember Amber.", "Steins\u00e4ge: 1x Glutbernsteins\u00e4ule aus Glutbernstein schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate", "copper_inferno:fire_agate", "smolderquartz/fire_agate",
				new String[] {"minecraft:blaze_powder", "", "minecraft:blaze_powder", "", "copper_inferno:ember_amber", "", "minecraft:blaze_powder", "", "minecraft:blaze_powder"},
				"copper_inferno:fire_agate", 4, "Craft 4x Fire Agate at a crafting table.", "Stellt 4x Feuerachat an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_bricks", "copper_inferno:fire_agate_bricks", "smolderquartz/fire_agate_bricks",
				new String[] {"copper_inferno:fire_agate", "copper_inferno:fire_agate", "", "copper_inferno:fire_agate", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_bricks", 4, "Craft 4x Fire Agate Bricks at a crafting table.", "Stellt 4x Feuerachatziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_tiles", "copper_inferno:fire_agate_tiles", "smolderquartz/fire_agate_tiles",
				new String[] {"copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "", "copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "", "", "", ""},
				"copper_inferno:fire_agate_tiles", 4, "Craft 4x Fire Agate Tiles at a crafting table.", "Stellt 4x Feuerachatfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_fire_agate", "copper_inferno:polished_fire_agate", "smolderquartz/polished_fire_agate",
				new String[] {"copper_inferno:fire_agate_tiles", "copper_inferno:fire_agate_tiles", "", "copper_inferno:fire_agate_tiles", "copper_inferno:fire_agate_tiles", "", "", "", ""},
				"copper_inferno:polished_fire_agate", 4, "Craft 4x Polished Fire Agate at a crafting table.", "Stellt 4x Polierten Feuerachat an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_slab", "copper_inferno:fire_agate_slab", "smolderquartz/fire_agate_slab",
				new String[] {"copper_inferno:fire_agate", "copper_inferno:fire_agate", "copper_inferno:fire_agate", "", "", "", "", "", ""},
				"copper_inferno:fire_agate_slab", 6, "Craft 6x Fire Agate Slab at a crafting table.", "Stellt 6x Feuerachatstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_stairs", "copper_inferno:fire_agate_stairs", "smolderquartz/fire_agate_stairs",
				new String[] {"copper_inferno:fire_agate", "", "", "copper_inferno:fire_agate", "copper_inferno:fire_agate", "", "copper_inferno:fire_agate", "copper_inferno:fire_agate", "copper_inferno:fire_agate"},
				"copper_inferno:fire_agate_stairs", 4, "Craft 4x Fire Agate Stairs at a crafting table.", "Stellt 4x Feuerachattreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_wall", "copper_inferno:fire_agate_wall", "smolderquartz/fire_agate_wall",
				new String[] {"copper_inferno:fire_agate", "copper_inferno:fire_agate", "copper_inferno:fire_agate", "copper_inferno:fire_agate", "copper_inferno:fire_agate", "copper_inferno:fire_agate", "", "", ""},
				"copper_inferno:fire_agate_wall", 6, "Craft 6x Fire Agate Wall at a crafting table.", "Stellt 6x Feuerachatmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_fire_agate_slab", "copper_inferno:polished_fire_agate_slab", "smolderquartz/polished_fire_agate_slab",
				new String[] {"copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate", "", "", "", "", "", ""},
				"copper_inferno:polished_fire_agate_slab", 6, "Craft 6x Polished Fire Agate Slab at a crafting table.", "Stellt 6x Polierte Feuerachatstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_fire_agate_stairs", "copper_inferno:polished_fire_agate_stairs", "smolderquartz/polished_fire_agate_stairs",
				new String[] {"copper_inferno:polished_fire_agate", "", "", "copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate", "", "copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate"},
				"copper_inferno:polished_fire_agate_stairs", 4, "Craft 4x Polished Fire Agate Stairs at a crafting table.", "Stellt 4x Polierte Feuerachattreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_fire_agate_wall", "copper_inferno:polished_fire_agate_wall", "smolderquartz/polished_fire_agate_wall",
				new String[] {"copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate", "copper_inferno:polished_fire_agate", "", "", ""},
				"copper_inferno:polished_fire_agate_wall", 6, "Craft 6x Polished Fire Agate Wall at a crafting table.", "Stellt 6x Polierte Feuerachatmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_brick_slab", "copper_inferno:fire_agate_brick_slab", "smolderquartz/fire_agate_brick_slab",
				new String[] {"copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "", "", "", "", "", ""},
				"copper_inferno:fire_agate_brick_slab", 6, "Craft 6x Fire Agate Brick Slab at a crafting table.", "Stellt 6x Feuerachatziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_brick_stairs", "copper_inferno:fire_agate_brick_stairs", "smolderquartz/fire_agate_brick_stairs",
				new String[] {"copper_inferno:fire_agate_bricks", "", "", "copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "", "copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks"},
				"copper_inferno:fire_agate_brick_stairs", 4, "Craft 4x Fire Agate Brick Stairs at a crafting table.", "Stellt 4x Feuerachatziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_brick_wall", "copper_inferno:fire_agate_brick_wall", "smolderquartz/fire_agate_brick_wall",
				new String[] {"copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "copper_inferno:fire_agate_bricks", "", "", ""},
				"copper_inferno:fire_agate_brick_wall", 6, "Craft 6x Fire Agate Brick Wall at a crafting table.", "Stellt 6x Feuerachatziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_fire_agate_bricks", "copper_inferno:cracked_fire_agate_bricks", "smolderquartz/cracked_fire_agate_bricks",
				new String[] {"", "", "", "", "copper_inferno:fire_agate_bricks", "", "", "", ""},
				"copper_inferno:cracked_fire_agate_bricks", 1, "Smelting Fire Agate Bricks in a furnace yields Cracked Fire Agate Bricks.", "Feuerachatziegel im Ofen gebrannt ergibt Rissige Feuerachatziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_fire_agate_bricks", "copper_inferno:chiseled_fire_agate_bricks", "smolderquartz/chiseled_fire_agate_bricks",
				new String[] {"copper_inferno:fire_agate_brick_slab", "", "", "copper_inferno:fire_agate_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_fire_agate_bricks", 1, "Craft 1x Chiseled Fire Agate Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Feuerachatziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_pillar", "copper_inferno:fire_agate_pillar", "smolderquartz/fire_agate_pillar",
				new String[] {"copper_inferno:fire_agate_bricks", "", "", "copper_inferno:fire_agate_bricks", "", "", "", "", ""},
				"copper_inferno:fire_agate_pillar", 2, "Craft 2x Fire Agate Pillar at a crafting table.", "Stellt 2x Feuerachats\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_slab_from_fire_agate_stonecutting", "copper_inferno:fire_agate_slab", "smolderquartz/fire_agate_slab_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_slab", 2, "Stonecutting: cut 2x Fire Agate Slab from Fire Agate.", "Steins\u00e4ge: 2x Feuerachatstufe aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_stairs_from_fire_agate_stonecutting", "copper_inferno:fire_agate_stairs", "smolderquartz/fire_agate_stairs_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_stairs", 1, "Stonecutting: cut 1x Fire Agate Stairs from Fire Agate.", "Steins\u00e4ge: 1x Feuerachattreppe aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_wall_from_fire_agate_stonecutting", "copper_inferno:fire_agate_wall", "smolderquartz/fire_agate_wall_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_wall", 1, "Stonecutting: cut 1x Fire Agate Wall from Fire Agate.", "Steins\u00e4ge: 1x Feuerachatmauer aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_fire_agate_from_fire_agate_stonecutting", "copper_inferno:polished_fire_agate", "smolderquartz/polished_fire_agate_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:polished_fire_agate", 1, "Stonecutting: cut 1x Polished Fire Agate from Fire Agate.", "Steins\u00e4ge: 1x Polierten Feuerachat aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_fire_agate_slab_from_fire_agate_stonecutting", "copper_inferno:polished_fire_agate_slab", "smolderquartz/polished_fire_agate_slab_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:polished_fire_agate_slab", 2, "Stonecutting: cut 2x Polished Fire Agate Slab from Fire Agate.", "Steins\u00e4ge: 2x Polierte Feuerachatstufe aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_fire_agate_stairs_from_fire_agate_stonecutting", "copper_inferno:polished_fire_agate_stairs", "smolderquartz/polished_fire_agate_stairs_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:polished_fire_agate_stairs", 1, "Stonecutting: cut 1x Polished Fire Agate Stairs from Fire Agate.", "Steins\u00e4ge: 1x Polierte Feuerachattreppe aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_fire_agate_wall_from_fire_agate_stonecutting", "copper_inferno:polished_fire_agate_wall", "smolderquartz/polished_fire_agate_wall_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:polished_fire_agate_wall", 1, "Stonecutting: cut 1x Polished Fire Agate Wall from Fire Agate.", "Steins\u00e4ge: 1x Polierte Feuerachatmauer aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_bricks_from_fire_agate_stonecutting", "copper_inferno:fire_agate_bricks", "smolderquartz/fire_agate_bricks_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_bricks", 1, "Stonecutting: cut 1x Fire Agate Bricks from Fire Agate.", "Steins\u00e4ge: 1x Feuerachatziegel aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_brick_slab_from_fire_agate_stonecutting", "copper_inferno:fire_agate_brick_slab", "smolderquartz/fire_agate_brick_slab_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_brick_slab", 2, "Stonecutting: cut 2x Fire Agate Brick Slab from Fire Agate.", "Steins\u00e4ge: 2x Feuerachatziegelstufe aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_brick_stairs_from_fire_agate_stonecutting", "copper_inferno:fire_agate_brick_stairs", "smolderquartz/fire_agate_brick_stairs_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_brick_stairs", 1, "Stonecutting: cut 1x Fire Agate Brick Stairs from Fire Agate.", "Steins\u00e4ge: 1x Feuerachatziegeltreppe aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_brick_wall_from_fire_agate_stonecutting", "copper_inferno:fire_agate_brick_wall", "smolderquartz/fire_agate_brick_wall_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_brick_wall", 1, "Stonecutting: cut 1x Fire Agate Brick Wall from Fire Agate.", "Steins\u00e4ge: 1x Feuerachatziegelmauer aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_tiles_from_fire_agate_stonecutting", "copper_inferno:fire_agate_tiles", "smolderquartz/fire_agate_tiles_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_tiles", 1, "Stonecutting: cut 1x Fire Agate Tiles from Fire Agate.", "Steins\u00e4ge: 1x Feuerachatfliesen aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_fire_agate_bricks_from_fire_agate_stonecutting", "copper_inferno:chiseled_fire_agate_bricks", "smolderquartz/chiseled_fire_agate_bricks_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:chiseled_fire_agate_bricks", 1, "Stonecutting: cut 1x Chiseled Fire Agate Bricks from Fire Agate.", "Steins\u00e4ge: 1x Gemei\u00dfelte Feuerachatziegel aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/fire_agate_pillar_from_fire_agate_stonecutting", "copper_inferno:fire_agate_pillar", "smolderquartz/fire_agate_pillar_from_fire_agate_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:fire_agate", "", "", "", ""},
				"copper_inferno:fire_agate_pillar", 1, "Stonecutting: cut 1x Fire Agate Pillar from Fire Agate.", "Steins\u00e4ge: 1x Feuerachats\u00e4ule aus Feuerachat schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl", "copper_inferno:lava_pearl", "smolderquartz/lava_pearl",
				new String[] {"minecraft:ender_pearl", "", "minecraft:ender_pearl", "", "copper_inferno:fire_agate", "", "minecraft:ender_pearl", "", "minecraft:ender_pearl"},
				"copper_inferno:lava_pearl", 4, "Craft 4x Lava Pearl at a crafting table.", "Stellt 4x Lavaperle an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "smolderquartz/lava_pearl_bricks",
				new String[] {"copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "", "copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_bricks", 4, "Craft 4x Lava Pearl Bricks at a crafting table.", "Stellt 4x Lavaperlenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_tiles", "copper_inferno:lava_pearl_tiles", "smolderquartz/lava_pearl_tiles",
				new String[] {"copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "", "copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "", "", "", ""},
				"copper_inferno:lava_pearl_tiles", 4, "Craft 4x Lava Pearl Tiles at a crafting table.", "Stellt 4x Lavaperlenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_lava_pearl", "copper_inferno:polished_lava_pearl", "smolderquartz/polished_lava_pearl",
				new String[] {"copper_inferno:lava_pearl_tiles", "copper_inferno:lava_pearl_tiles", "", "copper_inferno:lava_pearl_tiles", "copper_inferno:lava_pearl_tiles", "", "", "", ""},
				"copper_inferno:polished_lava_pearl", 4, "Craft 4x Polished Lava Pearl at a crafting table.", "Stellt 4x Polierte Lavaperle an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_slab", "copper_inferno:lava_pearl_slab", "smolderquartz/lava_pearl_slab",
				new String[] {"copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "", "", "", "", "", ""},
				"copper_inferno:lava_pearl_slab", 6, "Craft 6x Lava Pearl Slab at a crafting table.", "Stellt 6x Lavaperlenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_stairs", "copper_inferno:lava_pearl_stairs", "smolderquartz/lava_pearl_stairs",
				new String[] {"copper_inferno:lava_pearl", "", "", "copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "", "copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "copper_inferno:lava_pearl"},
				"copper_inferno:lava_pearl_stairs", 4, "Craft 4x Lava Pearl Stairs at a crafting table.", "Stellt 4x Lavaperlentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_wall", "copper_inferno:lava_pearl_wall", "smolderquartz/lava_pearl_wall",
				new String[] {"copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "copper_inferno:lava_pearl", "", "", ""},
				"copper_inferno:lava_pearl_wall", 6, "Craft 6x Lava Pearl Wall at a crafting table.", "Stellt 6x Lavaperlenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_lava_pearl_slab", "copper_inferno:polished_lava_pearl_slab", "smolderquartz/polished_lava_pearl_slab",
				new String[] {"copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl", "", "", "", "", "", ""},
				"copper_inferno:polished_lava_pearl_slab", 6, "Craft 6x Polished Lava Pearl Slab at a crafting table.", "Stellt 6x Polierte Lavaperlenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_lava_pearl_stairs", "copper_inferno:polished_lava_pearl_stairs", "smolderquartz/polished_lava_pearl_stairs",
				new String[] {"copper_inferno:polished_lava_pearl", "", "", "copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl", "", "copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl"},
				"copper_inferno:polished_lava_pearl_stairs", 4, "Craft 4x Polished Lava Pearl Stairs at a crafting table.", "Stellt 4x Polierte Lavaperlentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_lava_pearl_wall", "copper_inferno:polished_lava_pearl_wall", "smolderquartz/polished_lava_pearl_wall",
				new String[] {"copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl", "copper_inferno:polished_lava_pearl", "", "", ""},
				"copper_inferno:polished_lava_pearl_wall", 6, "Craft 6x Polished Lava Pearl Wall at a crafting table.", "Stellt 6x Polierte Lavaperlenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_brick_slab", "copper_inferno:lava_pearl_brick_slab", "smolderquartz/lava_pearl_brick_slab",
				new String[] {"copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "", "", "", "", "", ""},
				"copper_inferno:lava_pearl_brick_slab", 6, "Craft 6x Lava Pearl Brick Slab at a crafting table.", "Stellt 6x Lavaperlenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_brick_stairs", "copper_inferno:lava_pearl_brick_stairs", "smolderquartz/lava_pearl_brick_stairs",
				new String[] {"copper_inferno:lava_pearl_bricks", "", "", "copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "", "copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks"},
				"copper_inferno:lava_pearl_brick_stairs", 4, "Craft 4x Lava Pearl Brick Stairs at a crafting table.", "Stellt 4x Lavaperlenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_brick_wall", "copper_inferno:lava_pearl_brick_wall", "smolderquartz/lava_pearl_brick_wall",
				new String[] {"copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "copper_inferno:lava_pearl_bricks", "", "", ""},
				"copper_inferno:lava_pearl_brick_wall", 6, "Craft 6x Lava Pearl Brick Wall at a crafting table.", "Stellt 6x Lavaperlenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/cracked_lava_pearl_bricks", "copper_inferno:cracked_lava_pearl_bricks", "smolderquartz/cracked_lava_pearl_bricks",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl_bricks", "", "", "", ""},
				"copper_inferno:cracked_lava_pearl_bricks", 1, "Smelting Lava Pearl Bricks in a furnace yields Cracked Lava Pearl Bricks.", "Lavaperlenziegel im Ofen gebrannt ergibt Rissige Lavaperlenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_lava_pearl_bricks", "copper_inferno:chiseled_lava_pearl_bricks", "smolderquartz/chiseled_lava_pearl_bricks",
				new String[] {"copper_inferno:lava_pearl_brick_slab", "", "", "copper_inferno:lava_pearl_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_lava_pearl_bricks", 1, "Craft 1x Chiseled Lava Pearl Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Lavaperlenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_pillar", "copper_inferno:lava_pearl_pillar", "smolderquartz/lava_pearl_pillar",
				new String[] {"copper_inferno:lava_pearl_bricks", "", "", "copper_inferno:lava_pearl_bricks", "", "", "", "", ""},
				"copper_inferno:lava_pearl_pillar", 2, "Craft 2x Lava Pearl Pillar at a crafting table.", "Stellt 2x Lavaperlens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_slab_from_lava_pearl_stonecutting", "copper_inferno:lava_pearl_slab", "smolderquartz/lava_pearl_slab_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_slab", 2, "Stonecutting: cut 2x Lava Pearl Slab from Lava Pearl.", "Steins\u00e4ge: 2x Lavaperlenstufe aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_stairs_from_lava_pearl_stonecutting", "copper_inferno:lava_pearl_stairs", "smolderquartz/lava_pearl_stairs_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_stairs", 1, "Stonecutting: cut 1x Lava Pearl Stairs from Lava Pearl.", "Steins\u00e4ge: 1x Lavaperlentreppe aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_wall_from_lava_pearl_stonecutting", "copper_inferno:lava_pearl_wall", "smolderquartz/lava_pearl_wall_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_wall", 1, "Stonecutting: cut 1x Lava Pearl Wall from Lava Pearl.", "Steins\u00e4ge: 1x Lavaperlenmauer aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_lava_pearl_from_lava_pearl_stonecutting", "copper_inferno:polished_lava_pearl", "smolderquartz/polished_lava_pearl_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:polished_lava_pearl", 1, "Stonecutting: cut 1x Polished Lava Pearl from Lava Pearl.", "Steins\u00e4ge: 1x Polierte Lavaperle aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_lava_pearl_slab_from_lava_pearl_stonecutting", "copper_inferno:polished_lava_pearl_slab", "smolderquartz/polished_lava_pearl_slab_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:polished_lava_pearl_slab", 2, "Stonecutting: cut 2x Polished Lava Pearl Slab from Lava Pearl.", "Steins\u00e4ge: 2x Polierte Lavaperlenstufe aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_lava_pearl_stairs_from_lava_pearl_stonecutting", "copper_inferno:polished_lava_pearl_stairs", "smolderquartz/polished_lava_pearl_stairs_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:polished_lava_pearl_stairs", 1, "Stonecutting: cut 1x Polished Lava Pearl Stairs from Lava Pearl.", "Steins\u00e4ge: 1x Polierte Lavaperlentreppe aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/polished_lava_pearl_wall_from_lava_pearl_stonecutting", "copper_inferno:polished_lava_pearl_wall", "smolderquartz/polished_lava_pearl_wall_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:polished_lava_pearl_wall", 1, "Stonecutting: cut 1x Polished Lava Pearl Wall from Lava Pearl.", "Steins\u00e4ge: 1x Polierte Lavaperlenmauer aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_bricks_from_lava_pearl_stonecutting", "copper_inferno:lava_pearl_bricks", "smolderquartz/lava_pearl_bricks_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_bricks", 1, "Stonecutting: cut 1x Lava Pearl Bricks from Lava Pearl.", "Steins\u00e4ge: 1x Lavaperlenziegel aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_brick_slab_from_lava_pearl_stonecutting", "copper_inferno:lava_pearl_brick_slab", "smolderquartz/lava_pearl_brick_slab_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_brick_slab", 2, "Stonecutting: cut 2x Lava Pearl Brick Slab from Lava Pearl.", "Steins\u00e4ge: 2x Lavaperlenziegelstufe aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_brick_stairs_from_lava_pearl_stonecutting", "copper_inferno:lava_pearl_brick_stairs", "smolderquartz/lava_pearl_brick_stairs_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_brick_stairs", 1, "Stonecutting: cut 1x Lava Pearl Brick Stairs from Lava Pearl.", "Steins\u00e4ge: 1x Lavaperlenziegeltreppe aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_brick_wall_from_lava_pearl_stonecutting", "copper_inferno:lava_pearl_brick_wall", "smolderquartz/lava_pearl_brick_wall_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_brick_wall", 1, "Stonecutting: cut 1x Lava Pearl Brick Wall from Lava Pearl.", "Steins\u00e4ge: 1x Lavaperlenziegelmauer aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_tiles_from_lava_pearl_stonecutting", "copper_inferno:lava_pearl_tiles", "smolderquartz/lava_pearl_tiles_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_tiles", 1, "Stonecutting: cut 1x Lava Pearl Tiles from Lava Pearl.", "Steins\u00e4ge: 1x Lavaperlenfliesen aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/chiseled_lava_pearl_bricks_from_lava_pearl_stonecutting", "copper_inferno:chiseled_lava_pearl_bricks", "smolderquartz/chiseled_lava_pearl_bricks_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:chiseled_lava_pearl_bricks", 1, "Stonecutting: cut 1x Chiseled Lava Pearl Bricks from Lava Pearl.", "Steins\u00e4ge: 1x Gemei\u00dfelte Lavaperlenziegel aus Lavaperle schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "smolderquartz/lava_pearl_pillar_from_lava_pearl_stonecutting", "copper_inferno:lava_pearl_pillar", "smolderquartz/lava_pearl_pillar_from_lava_pearl_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:lava_pearl", "", "", "", ""},
				"copper_inferno:lava_pearl_pillar", 1, "Stonecutting: cut 1x Lava Pearl Pillar from Lava Pearl.", "Steins\u00e4ge: 1x Lavaperlens\u00e4ule aus Lavaperle schneiden."));
	}
}
