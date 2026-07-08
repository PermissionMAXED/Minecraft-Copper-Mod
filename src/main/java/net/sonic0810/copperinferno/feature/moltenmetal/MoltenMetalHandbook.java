package net.sonic0810.copperinferno.feature.moltenmetal;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Molten Metal block set: one "blocks" overview per material
 * plus one recipe page for every JSON under {@code data/copper_inferno/recipe/moltenmetal/}
 * (crafting, smelting and stonecutting). Entry texts and grids mirror the recipe JSONs
 * emitted by {@code devtools/gen/moltenmetal_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep them
 * inline.
 */
final class MoltenMetalHandbook {
	private MoltenMetalHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_ember_brass", "copper_inferno:ember_brass", null,
				null,
				null, 0, "The Ember Brass set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Glutmessing-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_slag_bronze", "copper_inferno:slag_bronze", null,
				null,
				null, 0, "The Slag Bronze set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Schlackenbronze-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_molten_brass", "copper_inferno:molten_brass", null,
				null,
				null, 0, "The Molten Brass set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Schmelzmessing-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_patina_steel", "copper_inferno:patina_steel", null,
				null,
				null, 0, "The Patina Steel set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Patinastahl-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_cinder_iron", "copper_inferno:cinder_iron", null,
				null,
				null, 0, "The Cinder Iron set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Zundereisen-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_forgeworn_metal", "copper_inferno:forgeworn_metal", null,
				null,
				null, 0, "The Forgeworn Metal set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Essenmetall-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_blistered_bronze", "copper_inferno:blistered_bronze", null,
				null,
				null, 0, "The Blistered Bronze set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Blasenbronze-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_scalding_steel", "copper_inferno:scalding_steel", null,
				null,
				null, 0, "The Scalding Steel set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Gl\u00fchstahl-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_smelters_alloy", "copper_inferno:smelters_alloy", null,
				null,
				null, 0, "The Smelter's Alloy set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Schmelzerlegierung-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_furnace_steel", "copper_inferno:furnace_steel", null,
				null,
				null, 0, "The Furnace Steel set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Ofenstahl-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_quench_iron", "copper_inferno:quench_iron", null,
				null,
				null, 0, "The Quench Iron set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das H\u00e4rteeisen-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_emberchrome", "copper_inferno:emberchrome", null,
				null,
				null, 0, "The Emberchrome set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Glutchrom-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_molten_cobalt", "copper_inferno:molten_cobalt", null,
				null,
				null, 0, "The Molten Cobalt set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Schmelzkobalt-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/set_ashiron", "copper_inferno:ashiron", null,
				null,
				null, 0, "The Ashiron set for Inferno builds: base, polished and bricks families (each with slab, stairs and wall) plus tiles, cracked and chiseled bricks and a pillar.", "Das Ascheneisen-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie (je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte Ziegel und eine S\u00e4ule."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass", "copper_inferno:ember_brass", "moltenmetal/ember_brass",
				new String[] {"minecraft:copper_ingot", "", "minecraft:copper_ingot", "", "copper_inferno:slag_chunk", "", "minecraft:copper_ingot", "", "minecraft:copper_ingot"},
				"copper_inferno:ember_brass", 4, "Craft 4x Ember Brass at a crafting table.", "Stellt 4x Glutmessing an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_bricks", "copper_inferno:ember_brass_bricks", "moltenmetal/ember_brass_bricks",
				new String[] {"copper_inferno:ember_brass", "copper_inferno:ember_brass", "", "copper_inferno:ember_brass", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_bricks", 4, "Craft 4x Ember Brass Bricks at a crafting table.", "Stellt 4x Glutmessingziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_tiles", "copper_inferno:ember_brass_tiles", "moltenmetal/ember_brass_tiles",
				new String[] {"copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "", "copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "", "", "", ""},
				"copper_inferno:ember_brass_tiles", 4, "Craft 4x Ember Brass Tiles at a crafting table.", "Stellt 4x Glutmessingfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ember_brass", "copper_inferno:polished_ember_brass", "moltenmetal/polished_ember_brass",
				new String[] {"copper_inferno:ember_brass_tiles", "copper_inferno:ember_brass_tiles", "", "copper_inferno:ember_brass_tiles", "copper_inferno:ember_brass_tiles", "", "", "", ""},
				"copper_inferno:polished_ember_brass", 4, "Craft 4x Polished Ember Brass at a crafting table.", "Stellt 4x Poliertes Glutmessing an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_slab", "copper_inferno:ember_brass_slab", "moltenmetal/ember_brass_slab",
				new String[] {"copper_inferno:ember_brass", "copper_inferno:ember_brass", "copper_inferno:ember_brass", "", "", "", "", "", ""},
				"copper_inferno:ember_brass_slab", 6, "Craft 6x Ember Brass Slab at a crafting table.", "Stellt 6x Glutmessingstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_stairs", "copper_inferno:ember_brass_stairs", "moltenmetal/ember_brass_stairs",
				new String[] {"copper_inferno:ember_brass", "", "", "copper_inferno:ember_brass", "copper_inferno:ember_brass", "", "copper_inferno:ember_brass", "copper_inferno:ember_brass", "copper_inferno:ember_brass"},
				"copper_inferno:ember_brass_stairs", 4, "Craft 4x Ember Brass Stairs at a crafting table.", "Stellt 4x Glutmessingtreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_wall", "copper_inferno:ember_brass_wall", "moltenmetal/ember_brass_wall",
				new String[] {"copper_inferno:ember_brass", "copper_inferno:ember_brass", "copper_inferno:ember_brass", "copper_inferno:ember_brass", "copper_inferno:ember_brass", "copper_inferno:ember_brass", "", "", ""},
				"copper_inferno:ember_brass_wall", 6, "Craft 6x Ember Brass Wall at a crafting table.", "Stellt 6x Glutmessingmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ember_brass_slab", "copper_inferno:polished_ember_brass_slab", "moltenmetal/polished_ember_brass_slab",
				new String[] {"copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass", "", "", "", "", "", ""},
				"copper_inferno:polished_ember_brass_slab", 6, "Craft 6x Polished Ember Brass Slab at a crafting table.", "Stellt 6x Polierte Glutmessingstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ember_brass_stairs", "copper_inferno:polished_ember_brass_stairs", "moltenmetal/polished_ember_brass_stairs",
				new String[] {"copper_inferno:polished_ember_brass", "", "", "copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass", "", "copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass"},
				"copper_inferno:polished_ember_brass_stairs", 4, "Craft 4x Polished Ember Brass Stairs at a crafting table.", "Stellt 4x Polierte Glutmessingtreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ember_brass_wall", "copper_inferno:polished_ember_brass_wall", "moltenmetal/polished_ember_brass_wall",
				new String[] {"copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass", "copper_inferno:polished_ember_brass", "", "", ""},
				"copper_inferno:polished_ember_brass_wall", 6, "Craft 6x Polished Ember Brass Wall at a crafting table.", "Stellt 6x Polierte Glutmessingmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_brick_slab", "copper_inferno:ember_brass_brick_slab", "moltenmetal/ember_brass_brick_slab",
				new String[] {"copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "", "", "", "", "", ""},
				"copper_inferno:ember_brass_brick_slab", 6, "Craft 6x Ember Brass Brick Slab at a crafting table.", "Stellt 6x Glutmessingziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_brick_stairs", "copper_inferno:ember_brass_brick_stairs", "moltenmetal/ember_brass_brick_stairs",
				new String[] {"copper_inferno:ember_brass_bricks", "", "", "copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "", "copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks"},
				"copper_inferno:ember_brass_brick_stairs", 4, "Craft 4x Ember Brass Brick Stairs at a crafting table.", "Stellt 4x Glutmessingziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_brick_wall", "copper_inferno:ember_brass_brick_wall", "moltenmetal/ember_brass_brick_wall",
				new String[] {"copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "copper_inferno:ember_brass_bricks", "", "", ""},
				"copper_inferno:ember_brass_brick_wall", 6, "Craft 6x Ember Brass Brick Wall at a crafting table.", "Stellt 6x Glutmessingziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_ember_brass_bricks", "copper_inferno:cracked_ember_brass_bricks", "moltenmetal/cracked_ember_brass_bricks",
				new String[] {"", "", "", "", "copper_inferno:ember_brass_bricks", "", "", "", ""},
				"copper_inferno:cracked_ember_brass_bricks", 1, "Smelting Ember Brass Bricks in a furnace yields Cracked Ember Brass Bricks.", "Glutmessingziegel im Ofen gebrannt ergibt Rissige Glutmessingziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_ember_brass_bricks", "copper_inferno:chiseled_ember_brass_bricks", "moltenmetal/chiseled_ember_brass_bricks",
				new String[] {"copper_inferno:ember_brass_brick_slab", "", "", "copper_inferno:ember_brass_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_ember_brass_bricks", 1, "Craft 1x Chiseled Ember Brass Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glutmessingziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_pillar", "copper_inferno:ember_brass_pillar", "moltenmetal/ember_brass_pillar",
				new String[] {"copper_inferno:ember_brass", "", "", "copper_inferno:ember_brass", "", "", "", "", ""},
				"copper_inferno:ember_brass_pillar", 2, "Craft 2x Ember Brass Pillar at a crafting table.", "Stellt 2x Glutmessings\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_slab_from_ember_brass_stonecutting", "copper_inferno:ember_brass_slab", "moltenmetal/ember_brass_slab_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_slab", 2, "Stonecutting: cut 2x Ember Brass Slab from Ember Brass.", "Steins\u00e4ge: 2x Glutmessingstufe aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_stairs_from_ember_brass_stonecutting", "copper_inferno:ember_brass_stairs", "moltenmetal/ember_brass_stairs_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_stairs", 1, "Stonecutting: cut 1x Ember Brass Stairs from Ember Brass.", "Steins\u00e4ge: 1x Glutmessingtreppe aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_wall_from_ember_brass_stonecutting", "copper_inferno:ember_brass_wall", "moltenmetal/ember_brass_wall_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_wall", 1, "Stonecutting: cut 1x Ember Brass Wall from Ember Brass.", "Steins\u00e4ge: 1x Glutmessingmauer aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ember_brass_from_ember_brass_stonecutting", "copper_inferno:polished_ember_brass", "moltenmetal/polished_ember_brass_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:polished_ember_brass", 1, "Stonecutting: cut 1x Polished Ember Brass from Ember Brass.", "Steins\u00e4ge: 1x Poliertes Glutmessing aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ember_brass_slab_from_ember_brass_stonecutting", "copper_inferno:polished_ember_brass_slab", "moltenmetal/polished_ember_brass_slab_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:polished_ember_brass_slab", 2, "Stonecutting: cut 2x Polished Ember Brass Slab from Ember Brass.", "Steins\u00e4ge: 2x Polierte Glutmessingstufe aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ember_brass_stairs_from_ember_brass_stonecutting", "copper_inferno:polished_ember_brass_stairs", "moltenmetal/polished_ember_brass_stairs_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:polished_ember_brass_stairs", 1, "Stonecutting: cut 1x Polished Ember Brass Stairs from Ember Brass.", "Steins\u00e4ge: 1x Polierte Glutmessingtreppe aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ember_brass_wall_from_ember_brass_stonecutting", "copper_inferno:polished_ember_brass_wall", "moltenmetal/polished_ember_brass_wall_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:polished_ember_brass_wall", 1, "Stonecutting: cut 1x Polished Ember Brass Wall from Ember Brass.", "Steins\u00e4ge: 1x Polierte Glutmessingmauer aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_bricks_from_ember_brass_stonecutting", "copper_inferno:ember_brass_bricks", "moltenmetal/ember_brass_bricks_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_bricks", 1, "Stonecutting: cut 1x Ember Brass Bricks from Ember Brass.", "Steins\u00e4ge: 1x Glutmessingziegel aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_brick_slab_from_ember_brass_stonecutting", "copper_inferno:ember_brass_brick_slab", "moltenmetal/ember_brass_brick_slab_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_brick_slab", 2, "Stonecutting: cut 2x Ember Brass Brick Slab from Ember Brass.", "Steins\u00e4ge: 2x Glutmessingziegelstufe aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_brick_stairs_from_ember_brass_stonecutting", "copper_inferno:ember_brass_brick_stairs", "moltenmetal/ember_brass_brick_stairs_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_brick_stairs", 1, "Stonecutting: cut 1x Ember Brass Brick Stairs from Ember Brass.", "Steins\u00e4ge: 1x Glutmessingziegeltreppe aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_brick_wall_from_ember_brass_stonecutting", "copper_inferno:ember_brass_brick_wall", "moltenmetal/ember_brass_brick_wall_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_brick_wall", 1, "Stonecutting: cut 1x Ember Brass Brick Wall from Ember Brass.", "Steins\u00e4ge: 1x Glutmessingziegelmauer aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_tiles_from_ember_brass_stonecutting", "copper_inferno:ember_brass_tiles", "moltenmetal/ember_brass_tiles_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_tiles", 1, "Stonecutting: cut 1x Ember Brass Tiles from Ember Brass.", "Steins\u00e4ge: 1x Glutmessingfliesen aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_ember_brass_bricks_from_ember_brass_stonecutting", "copper_inferno:chiseled_ember_brass_bricks", "moltenmetal/chiseled_ember_brass_bricks_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:chiseled_ember_brass_bricks", 1, "Stonecutting: cut 1x Chiseled Ember Brass Bricks from Ember Brass.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glutmessingziegel aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ember_brass_pillar_from_ember_brass_stonecutting", "copper_inferno:ember_brass_pillar", "moltenmetal/ember_brass_pillar_from_ember_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ember_brass", "", "", "", ""},
				"copper_inferno:ember_brass_pillar", 1, "Stonecutting: cut 1x Ember Brass Pillar from Ember Brass.", "Steins\u00e4ge: 1x Glutmessings\u00e4ule aus Glutmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze", "copper_inferno:slag_bronze", "moltenmetal/slag_bronze",
				new String[] {"minecraft:raw_copper", "", "minecraft:raw_copper", "", "copper_inferno:ember_brass", "", "minecraft:raw_copper", "", "minecraft:raw_copper"},
				"copper_inferno:slag_bronze", 4, "Craft 4x Slag Bronze at a crafting table.", "Stellt 4x Schlackenbronze an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "moltenmetal/slag_bronze_bricks",
				new String[] {"copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "", "copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_bricks", 4, "Craft 4x Slag Bronze Bricks at a crafting table.", "Stellt 4x Schlackenbronzeziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_tiles", "copper_inferno:slag_bronze_tiles", "moltenmetal/slag_bronze_tiles",
				new String[] {"copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "", "copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "", "", "", ""},
				"copper_inferno:slag_bronze_tiles", 4, "Craft 4x Slag Bronze Tiles at a crafting table.", "Stellt 4x Schlackenbronzefliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_slag_bronze", "copper_inferno:polished_slag_bronze", "moltenmetal/polished_slag_bronze",
				new String[] {"copper_inferno:slag_bronze_tiles", "copper_inferno:slag_bronze_tiles", "", "copper_inferno:slag_bronze_tiles", "copper_inferno:slag_bronze_tiles", "", "", "", ""},
				"copper_inferno:polished_slag_bronze", 4, "Craft 4x Polished Slag Bronze at a crafting table.", "Stellt 4x Polierte Schlackenbronze an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_slab", "copper_inferno:slag_bronze_slab", "moltenmetal/slag_bronze_slab",
				new String[] {"copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "", "", "", "", "", ""},
				"copper_inferno:slag_bronze_slab", 6, "Craft 6x Slag Bronze Slab at a crafting table.", "Stellt 6x Schlackenbronzestufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_stairs", "copper_inferno:slag_bronze_stairs", "moltenmetal/slag_bronze_stairs",
				new String[] {"copper_inferno:slag_bronze", "", "", "copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "", "copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "copper_inferno:slag_bronze"},
				"copper_inferno:slag_bronze_stairs", 4, "Craft 4x Slag Bronze Stairs at a crafting table.", "Stellt 4x Schlackenbronzetreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_wall", "copper_inferno:slag_bronze_wall", "moltenmetal/slag_bronze_wall",
				new String[] {"copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "copper_inferno:slag_bronze", "", "", ""},
				"copper_inferno:slag_bronze_wall", 6, "Craft 6x Slag Bronze Wall at a crafting table.", "Stellt 6x Schlackenbronzemauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_slag_bronze_slab", "copper_inferno:polished_slag_bronze_slab", "moltenmetal/polished_slag_bronze_slab",
				new String[] {"copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze", "", "", "", "", "", ""},
				"copper_inferno:polished_slag_bronze_slab", 6, "Craft 6x Polished Slag Bronze Slab at a crafting table.", "Stellt 6x Polierte Schlackenbronzestufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_slag_bronze_stairs", "copper_inferno:polished_slag_bronze_stairs", "moltenmetal/polished_slag_bronze_stairs",
				new String[] {"copper_inferno:polished_slag_bronze", "", "", "copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze", "", "copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze"},
				"copper_inferno:polished_slag_bronze_stairs", 4, "Craft 4x Polished Slag Bronze Stairs at a crafting table.", "Stellt 4x Polierte Schlackenbronzetreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_slag_bronze_wall", "copper_inferno:polished_slag_bronze_wall", "moltenmetal/polished_slag_bronze_wall",
				new String[] {"copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze", "copper_inferno:polished_slag_bronze", "", "", ""},
				"copper_inferno:polished_slag_bronze_wall", 6, "Craft 6x Polished Slag Bronze Wall at a crafting table.", "Stellt 6x Polierte Schlackenbronzemauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_brick_slab", "copper_inferno:slag_bronze_brick_slab", "moltenmetal/slag_bronze_brick_slab",
				new String[] {"copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "", "", "", "", "", ""},
				"copper_inferno:slag_bronze_brick_slab", 6, "Craft 6x Slag Bronze Brick Slab at a crafting table.", "Stellt 6x Schlackenbronzeziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_brick_stairs", "copper_inferno:slag_bronze_brick_stairs", "moltenmetal/slag_bronze_brick_stairs",
				new String[] {"copper_inferno:slag_bronze_bricks", "", "", "copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "", "copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks"},
				"copper_inferno:slag_bronze_brick_stairs", 4, "Craft 4x Slag Bronze Brick Stairs at a crafting table.", "Stellt 4x Schlackenbronzeziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_brick_wall", "copper_inferno:slag_bronze_brick_wall", "moltenmetal/slag_bronze_brick_wall",
				new String[] {"copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "copper_inferno:slag_bronze_bricks", "", "", ""},
				"copper_inferno:slag_bronze_brick_wall", 6, "Craft 6x Slag Bronze Brick Wall at a crafting table.", "Stellt 6x Schlackenbronzeziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_slag_bronze_bricks", "copper_inferno:cracked_slag_bronze_bricks", "moltenmetal/cracked_slag_bronze_bricks",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze_bricks", "", "", "", ""},
				"copper_inferno:cracked_slag_bronze_bricks", 1, "Smelting Slag Bronze Bricks in a furnace yields Cracked Slag Bronze Bricks.", "Schlackenbronzeziegel im Ofen gebrannt ergibt Rissige Schlackenbronzeziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_slag_bronze_bricks", "copper_inferno:chiseled_slag_bronze_bricks", "moltenmetal/chiseled_slag_bronze_bricks",
				new String[] {"copper_inferno:slag_bronze_brick_slab", "", "", "copper_inferno:slag_bronze_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_slag_bronze_bricks", 1, "Craft 1x Chiseled Slag Bronze Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schlackenbronzeziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_pillar", "copper_inferno:slag_bronze_pillar", "moltenmetal/slag_bronze_pillar",
				new String[] {"copper_inferno:slag_bronze", "", "", "copper_inferno:slag_bronze", "", "", "", "", ""},
				"copper_inferno:slag_bronze_pillar", 2, "Craft 2x Slag Bronze Pillar at a crafting table.", "Stellt 2x Schlackenbronzes\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_slab_from_slag_bronze_stonecutting", "copper_inferno:slag_bronze_slab", "moltenmetal/slag_bronze_slab_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_slab", 2, "Stonecutting: cut 2x Slag Bronze Slab from Slag Bronze.", "Steins\u00e4ge: 2x Schlackenbronzestufe aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_stairs_from_slag_bronze_stonecutting", "copper_inferno:slag_bronze_stairs", "moltenmetal/slag_bronze_stairs_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_stairs", 1, "Stonecutting: cut 1x Slag Bronze Stairs from Slag Bronze.", "Steins\u00e4ge: 1x Schlackenbronzetreppe aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_wall_from_slag_bronze_stonecutting", "copper_inferno:slag_bronze_wall", "moltenmetal/slag_bronze_wall_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_wall", 1, "Stonecutting: cut 1x Slag Bronze Wall from Slag Bronze.", "Steins\u00e4ge: 1x Schlackenbronzemauer aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_slag_bronze_from_slag_bronze_stonecutting", "copper_inferno:polished_slag_bronze", "moltenmetal/polished_slag_bronze_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:polished_slag_bronze", 1, "Stonecutting: cut 1x Polished Slag Bronze from Slag Bronze.", "Steins\u00e4ge: 1x Polierte Schlackenbronze aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_slag_bronze_slab_from_slag_bronze_stonecutting", "copper_inferno:polished_slag_bronze_slab", "moltenmetal/polished_slag_bronze_slab_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:polished_slag_bronze_slab", 2, "Stonecutting: cut 2x Polished Slag Bronze Slab from Slag Bronze.", "Steins\u00e4ge: 2x Polierte Schlackenbronzestufe aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_slag_bronze_stairs_from_slag_bronze_stonecutting", "copper_inferno:polished_slag_bronze_stairs", "moltenmetal/polished_slag_bronze_stairs_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:polished_slag_bronze_stairs", 1, "Stonecutting: cut 1x Polished Slag Bronze Stairs from Slag Bronze.", "Steins\u00e4ge: 1x Polierte Schlackenbronzetreppe aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_slag_bronze_wall_from_slag_bronze_stonecutting", "copper_inferno:polished_slag_bronze_wall", "moltenmetal/polished_slag_bronze_wall_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:polished_slag_bronze_wall", 1, "Stonecutting: cut 1x Polished Slag Bronze Wall from Slag Bronze.", "Steins\u00e4ge: 1x Polierte Schlackenbronzemauer aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_bricks_from_slag_bronze_stonecutting", "copper_inferno:slag_bronze_bricks", "moltenmetal/slag_bronze_bricks_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_bricks", 1, "Stonecutting: cut 1x Slag Bronze Bricks from Slag Bronze.", "Steins\u00e4ge: 1x Schlackenbronzeziegel aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_brick_slab_from_slag_bronze_stonecutting", "copper_inferno:slag_bronze_brick_slab", "moltenmetal/slag_bronze_brick_slab_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_brick_slab", 2, "Stonecutting: cut 2x Slag Bronze Brick Slab from Slag Bronze.", "Steins\u00e4ge: 2x Schlackenbronzeziegelstufe aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_brick_stairs_from_slag_bronze_stonecutting", "copper_inferno:slag_bronze_brick_stairs", "moltenmetal/slag_bronze_brick_stairs_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_brick_stairs", 1, "Stonecutting: cut 1x Slag Bronze Brick Stairs from Slag Bronze.", "Steins\u00e4ge: 1x Schlackenbronzeziegeltreppe aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_brick_wall_from_slag_bronze_stonecutting", "copper_inferno:slag_bronze_brick_wall", "moltenmetal/slag_bronze_brick_wall_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_brick_wall", 1, "Stonecutting: cut 1x Slag Bronze Brick Wall from Slag Bronze.", "Steins\u00e4ge: 1x Schlackenbronzeziegelmauer aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_tiles_from_slag_bronze_stonecutting", "copper_inferno:slag_bronze_tiles", "moltenmetal/slag_bronze_tiles_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_tiles", 1, "Stonecutting: cut 1x Slag Bronze Tiles from Slag Bronze.", "Steins\u00e4ge: 1x Schlackenbronzefliesen aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_slag_bronze_bricks_from_slag_bronze_stonecutting", "copper_inferno:chiseled_slag_bronze_bricks", "moltenmetal/chiseled_slag_bronze_bricks_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:chiseled_slag_bronze_bricks", 1, "Stonecutting: cut 1x Chiseled Slag Bronze Bricks from Slag Bronze.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schlackenbronzeziegel aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/slag_bronze_pillar_from_slag_bronze_stonecutting", "copper_inferno:slag_bronze_pillar", "moltenmetal/slag_bronze_pillar_from_slag_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:slag_bronze", "", "", "", ""},
				"copper_inferno:slag_bronze_pillar", 1, "Stonecutting: cut 1x Slag Bronze Pillar from Slag Bronze.", "Steins\u00e4ge: 1x Schlackenbronzes\u00e4ule aus Schlackenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass", "copper_inferno:molten_brass", "moltenmetal/molten_brass",
				new String[] {"minecraft:gold_nugget", "", "minecraft:gold_nugget", "", "copper_inferno:slag_bronze", "", "minecraft:gold_nugget", "", "minecraft:gold_nugget"},
				"copper_inferno:molten_brass", 4, "Craft 4x Molten Brass at a crafting table.", "Stellt 4x Schmelzmessing an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_bricks", "copper_inferno:molten_brass_bricks", "moltenmetal/molten_brass_bricks",
				new String[] {"copper_inferno:molten_brass", "copper_inferno:molten_brass", "", "copper_inferno:molten_brass", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_bricks", 4, "Craft 4x Molten Brass Bricks at a crafting table.", "Stellt 4x Schmelzmessingziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_tiles", "copper_inferno:molten_brass_tiles", "moltenmetal/molten_brass_tiles",
				new String[] {"copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "", "copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "", "", "", ""},
				"copper_inferno:molten_brass_tiles", 4, "Craft 4x Molten Brass Tiles at a crafting table.", "Stellt 4x Schmelzmessingfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_brass", "copper_inferno:polished_molten_brass", "moltenmetal/polished_molten_brass",
				new String[] {"copper_inferno:molten_brass_tiles", "copper_inferno:molten_brass_tiles", "", "copper_inferno:molten_brass_tiles", "copper_inferno:molten_brass_tiles", "", "", "", ""},
				"copper_inferno:polished_molten_brass", 4, "Craft 4x Polished Molten Brass at a crafting table.", "Stellt 4x Poliertes Schmelzmessing an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_slab", "copper_inferno:molten_brass_slab", "moltenmetal/molten_brass_slab",
				new String[] {"copper_inferno:molten_brass", "copper_inferno:molten_brass", "copper_inferno:molten_brass", "", "", "", "", "", ""},
				"copper_inferno:molten_brass_slab", 6, "Craft 6x Molten Brass Slab at a crafting table.", "Stellt 6x Schmelzmessingstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_stairs", "copper_inferno:molten_brass_stairs", "moltenmetal/molten_brass_stairs",
				new String[] {"copper_inferno:molten_brass", "", "", "copper_inferno:molten_brass", "copper_inferno:molten_brass", "", "copper_inferno:molten_brass", "copper_inferno:molten_brass", "copper_inferno:molten_brass"},
				"copper_inferno:molten_brass_stairs", 4, "Craft 4x Molten Brass Stairs at a crafting table.", "Stellt 4x Schmelzmessingtreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_wall", "copper_inferno:molten_brass_wall", "moltenmetal/molten_brass_wall",
				new String[] {"copper_inferno:molten_brass", "copper_inferno:molten_brass", "copper_inferno:molten_brass", "copper_inferno:molten_brass", "copper_inferno:molten_brass", "copper_inferno:molten_brass", "", "", ""},
				"copper_inferno:molten_brass_wall", 6, "Craft 6x Molten Brass Wall at a crafting table.", "Stellt 6x Schmelzmessingmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_brass_slab", "copper_inferno:polished_molten_brass_slab", "moltenmetal/polished_molten_brass_slab",
				new String[] {"copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass", "", "", "", "", "", ""},
				"copper_inferno:polished_molten_brass_slab", 6, "Craft 6x Polished Molten Brass Slab at a crafting table.", "Stellt 6x Polierte Schmelzmessingstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_brass_stairs", "copper_inferno:polished_molten_brass_stairs", "moltenmetal/polished_molten_brass_stairs",
				new String[] {"copper_inferno:polished_molten_brass", "", "", "copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass", "", "copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass"},
				"copper_inferno:polished_molten_brass_stairs", 4, "Craft 4x Polished Molten Brass Stairs at a crafting table.", "Stellt 4x Polierte Schmelzmessingtreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_brass_wall", "copper_inferno:polished_molten_brass_wall", "moltenmetal/polished_molten_brass_wall",
				new String[] {"copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass", "copper_inferno:polished_molten_brass", "", "", ""},
				"copper_inferno:polished_molten_brass_wall", 6, "Craft 6x Polished Molten Brass Wall at a crafting table.", "Stellt 6x Polierte Schmelzmessingmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_brick_slab", "copper_inferno:molten_brass_brick_slab", "moltenmetal/molten_brass_brick_slab",
				new String[] {"copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "", "", "", "", "", ""},
				"copper_inferno:molten_brass_brick_slab", 6, "Craft 6x Molten Brass Brick Slab at a crafting table.", "Stellt 6x Schmelzmessingziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_brick_stairs", "copper_inferno:molten_brass_brick_stairs", "moltenmetal/molten_brass_brick_stairs",
				new String[] {"copper_inferno:molten_brass_bricks", "", "", "copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "", "copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks"},
				"copper_inferno:molten_brass_brick_stairs", 4, "Craft 4x Molten Brass Brick Stairs at a crafting table.", "Stellt 4x Schmelzmessingziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_brick_wall", "copper_inferno:molten_brass_brick_wall", "moltenmetal/molten_brass_brick_wall",
				new String[] {"copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "copper_inferno:molten_brass_bricks", "", "", ""},
				"copper_inferno:molten_brass_brick_wall", 6, "Craft 6x Molten Brass Brick Wall at a crafting table.", "Stellt 6x Schmelzmessingziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_molten_brass_bricks", "copper_inferno:cracked_molten_brass_bricks", "moltenmetal/cracked_molten_brass_bricks",
				new String[] {"", "", "", "", "copper_inferno:molten_brass_bricks", "", "", "", ""},
				"copper_inferno:cracked_molten_brass_bricks", 1, "Smelting Molten Brass Bricks in a furnace yields Cracked Molten Brass Bricks.", "Schmelzmessingziegel im Ofen gebrannt ergibt Rissige Schmelzmessingziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_molten_brass_bricks", "copper_inferno:chiseled_molten_brass_bricks", "moltenmetal/chiseled_molten_brass_bricks",
				new String[] {"copper_inferno:molten_brass_brick_slab", "", "", "copper_inferno:molten_brass_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_molten_brass_bricks", 1, "Craft 1x Chiseled Molten Brass Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schmelzmessingziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_pillar", "copper_inferno:molten_brass_pillar", "moltenmetal/molten_brass_pillar",
				new String[] {"copper_inferno:molten_brass", "", "", "copper_inferno:molten_brass", "", "", "", "", ""},
				"copper_inferno:molten_brass_pillar", 2, "Craft 2x Molten Brass Pillar at a crafting table.", "Stellt 2x Schmelzmessings\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_slab_from_molten_brass_stonecutting", "copper_inferno:molten_brass_slab", "moltenmetal/molten_brass_slab_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_slab", 2, "Stonecutting: cut 2x Molten Brass Slab from Molten Brass.", "Steins\u00e4ge: 2x Schmelzmessingstufe aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_stairs_from_molten_brass_stonecutting", "copper_inferno:molten_brass_stairs", "moltenmetal/molten_brass_stairs_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_stairs", 1, "Stonecutting: cut 1x Molten Brass Stairs from Molten Brass.", "Steins\u00e4ge: 1x Schmelzmessingtreppe aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_wall_from_molten_brass_stonecutting", "copper_inferno:molten_brass_wall", "moltenmetal/molten_brass_wall_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_wall", 1, "Stonecutting: cut 1x Molten Brass Wall from Molten Brass.", "Steins\u00e4ge: 1x Schmelzmessingmauer aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_brass_from_molten_brass_stonecutting", "copper_inferno:polished_molten_brass", "moltenmetal/polished_molten_brass_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:polished_molten_brass", 1, "Stonecutting: cut 1x Polished Molten Brass from Molten Brass.", "Steins\u00e4ge: 1x Poliertes Schmelzmessing aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_brass_slab_from_molten_brass_stonecutting", "copper_inferno:polished_molten_brass_slab", "moltenmetal/polished_molten_brass_slab_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:polished_molten_brass_slab", 2, "Stonecutting: cut 2x Polished Molten Brass Slab from Molten Brass.", "Steins\u00e4ge: 2x Polierte Schmelzmessingstufe aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_brass_stairs_from_molten_brass_stonecutting", "copper_inferno:polished_molten_brass_stairs", "moltenmetal/polished_molten_brass_stairs_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:polished_molten_brass_stairs", 1, "Stonecutting: cut 1x Polished Molten Brass Stairs from Molten Brass.", "Steins\u00e4ge: 1x Polierte Schmelzmessingtreppe aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_brass_wall_from_molten_brass_stonecutting", "copper_inferno:polished_molten_brass_wall", "moltenmetal/polished_molten_brass_wall_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:polished_molten_brass_wall", 1, "Stonecutting: cut 1x Polished Molten Brass Wall from Molten Brass.", "Steins\u00e4ge: 1x Polierte Schmelzmessingmauer aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_bricks_from_molten_brass_stonecutting", "copper_inferno:molten_brass_bricks", "moltenmetal/molten_brass_bricks_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_bricks", 1, "Stonecutting: cut 1x Molten Brass Bricks from Molten Brass.", "Steins\u00e4ge: 1x Schmelzmessingziegel aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_brick_slab_from_molten_brass_stonecutting", "copper_inferno:molten_brass_brick_slab", "moltenmetal/molten_brass_brick_slab_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_brick_slab", 2, "Stonecutting: cut 2x Molten Brass Brick Slab from Molten Brass.", "Steins\u00e4ge: 2x Schmelzmessingziegelstufe aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_brick_stairs_from_molten_brass_stonecutting", "copper_inferno:molten_brass_brick_stairs", "moltenmetal/molten_brass_brick_stairs_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_brick_stairs", 1, "Stonecutting: cut 1x Molten Brass Brick Stairs from Molten Brass.", "Steins\u00e4ge: 1x Schmelzmessingziegeltreppe aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_brick_wall_from_molten_brass_stonecutting", "copper_inferno:molten_brass_brick_wall", "moltenmetal/molten_brass_brick_wall_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_brick_wall", 1, "Stonecutting: cut 1x Molten Brass Brick Wall from Molten Brass.", "Steins\u00e4ge: 1x Schmelzmessingziegelmauer aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_tiles_from_molten_brass_stonecutting", "copper_inferno:molten_brass_tiles", "moltenmetal/molten_brass_tiles_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_tiles", 1, "Stonecutting: cut 1x Molten Brass Tiles from Molten Brass.", "Steins\u00e4ge: 1x Schmelzmessingfliesen aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_molten_brass_bricks_from_molten_brass_stonecutting", "copper_inferno:chiseled_molten_brass_bricks", "moltenmetal/chiseled_molten_brass_bricks_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:chiseled_molten_brass_bricks", 1, "Stonecutting: cut 1x Chiseled Molten Brass Bricks from Molten Brass.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schmelzmessingziegel aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_brass_pillar_from_molten_brass_stonecutting", "copper_inferno:molten_brass_pillar", "moltenmetal/molten_brass_pillar_from_molten_brass_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_brass", "", "", "", ""},
				"copper_inferno:molten_brass_pillar", 1, "Stonecutting: cut 1x Molten Brass Pillar from Molten Brass.", "Steins\u00e4ge: 1x Schmelzmessings\u00e4ule aus Schmelzmessing schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel", "copper_inferno:patina_steel", "moltenmetal/patina_steel",
				new String[] {"minecraft:oxidized_copper", "", "minecraft:oxidized_copper", "", "copper_inferno:molten_brass", "", "minecraft:oxidized_copper", "", "minecraft:oxidized_copper"},
				"copper_inferno:patina_steel", 4, "Craft 4x Patina Steel at a crafting table.", "Stellt 4x Patinastahl an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_bricks", "copper_inferno:patina_steel_bricks", "moltenmetal/patina_steel_bricks",
				new String[] {"copper_inferno:patina_steel", "copper_inferno:patina_steel", "", "copper_inferno:patina_steel", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_bricks", 4, "Craft 4x Patina Steel Bricks at a crafting table.", "Stellt 4x Patinastahlziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_tiles", "copper_inferno:patina_steel_tiles", "moltenmetal/patina_steel_tiles",
				new String[] {"copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "", "copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "", "", "", ""},
				"copper_inferno:patina_steel_tiles", 4, "Craft 4x Patina Steel Tiles at a crafting table.", "Stellt 4x Patinastahlfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_patina_steel", "copper_inferno:polished_patina_steel", "moltenmetal/polished_patina_steel",
				new String[] {"copper_inferno:patina_steel_tiles", "copper_inferno:patina_steel_tiles", "", "copper_inferno:patina_steel_tiles", "copper_inferno:patina_steel_tiles", "", "", "", ""},
				"copper_inferno:polished_patina_steel", 4, "Craft 4x Polished Patina Steel at a crafting table.", "Stellt 4x Polierten Patinastahl an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_slab", "copper_inferno:patina_steel_slab", "moltenmetal/patina_steel_slab",
				new String[] {"copper_inferno:patina_steel", "copper_inferno:patina_steel", "copper_inferno:patina_steel", "", "", "", "", "", ""},
				"copper_inferno:patina_steel_slab", 6, "Craft 6x Patina Steel Slab at a crafting table.", "Stellt 6x Patinastahlstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_stairs", "copper_inferno:patina_steel_stairs", "moltenmetal/patina_steel_stairs",
				new String[] {"copper_inferno:patina_steel", "", "", "copper_inferno:patina_steel", "copper_inferno:patina_steel", "", "copper_inferno:patina_steel", "copper_inferno:patina_steel", "copper_inferno:patina_steel"},
				"copper_inferno:patina_steel_stairs", 4, "Craft 4x Patina Steel Stairs at a crafting table.", "Stellt 4x Patinastahltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_wall", "copper_inferno:patina_steel_wall", "moltenmetal/patina_steel_wall",
				new String[] {"copper_inferno:patina_steel", "copper_inferno:patina_steel", "copper_inferno:patina_steel", "copper_inferno:patina_steel", "copper_inferno:patina_steel", "copper_inferno:patina_steel", "", "", ""},
				"copper_inferno:patina_steel_wall", 6, "Craft 6x Patina Steel Wall at a crafting table.", "Stellt 6x Patinastahlmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_patina_steel_slab", "copper_inferno:polished_patina_steel_slab", "moltenmetal/polished_patina_steel_slab",
				new String[] {"copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel", "", "", "", "", "", ""},
				"copper_inferno:polished_patina_steel_slab", 6, "Craft 6x Polished Patina Steel Slab at a crafting table.", "Stellt 6x Polierte Patinastahlstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_patina_steel_stairs", "copper_inferno:polished_patina_steel_stairs", "moltenmetal/polished_patina_steel_stairs",
				new String[] {"copper_inferno:polished_patina_steel", "", "", "copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel", "", "copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel"},
				"copper_inferno:polished_patina_steel_stairs", 4, "Craft 4x Polished Patina Steel Stairs at a crafting table.", "Stellt 4x Polierte Patinastahltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_patina_steel_wall", "copper_inferno:polished_patina_steel_wall", "moltenmetal/polished_patina_steel_wall",
				new String[] {"copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel", "copper_inferno:polished_patina_steel", "", "", ""},
				"copper_inferno:polished_patina_steel_wall", 6, "Craft 6x Polished Patina Steel Wall at a crafting table.", "Stellt 6x Polierte Patinastahlmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_brick_slab", "copper_inferno:patina_steel_brick_slab", "moltenmetal/patina_steel_brick_slab",
				new String[] {"copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "", "", "", "", "", ""},
				"copper_inferno:patina_steel_brick_slab", 6, "Craft 6x Patina Steel Brick Slab at a crafting table.", "Stellt 6x Patinastahlziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_brick_stairs", "copper_inferno:patina_steel_brick_stairs", "moltenmetal/patina_steel_brick_stairs",
				new String[] {"copper_inferno:patina_steel_bricks", "", "", "copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "", "copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks"},
				"copper_inferno:patina_steel_brick_stairs", 4, "Craft 4x Patina Steel Brick Stairs at a crafting table.", "Stellt 4x Patinastahlziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_brick_wall", "copper_inferno:patina_steel_brick_wall", "moltenmetal/patina_steel_brick_wall",
				new String[] {"copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "copper_inferno:patina_steel_bricks", "", "", ""},
				"copper_inferno:patina_steel_brick_wall", 6, "Craft 6x Patina Steel Brick Wall at a crafting table.", "Stellt 6x Patinastahlziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_patina_steel_bricks", "copper_inferno:cracked_patina_steel_bricks", "moltenmetal/cracked_patina_steel_bricks",
				new String[] {"", "", "", "", "copper_inferno:patina_steel_bricks", "", "", "", ""},
				"copper_inferno:cracked_patina_steel_bricks", 1, "Smelting Patina Steel Bricks in a furnace yields Cracked Patina Steel Bricks.", "Patinastahlziegel im Ofen gebrannt ergibt Rissige Patinastahlziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_patina_steel_bricks", "copper_inferno:chiseled_patina_steel_bricks", "moltenmetal/chiseled_patina_steel_bricks",
				new String[] {"copper_inferno:patina_steel_brick_slab", "", "", "copper_inferno:patina_steel_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_patina_steel_bricks", 1, "Craft 1x Chiseled Patina Steel Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Patinastahlziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_pillar", "copper_inferno:patina_steel_pillar", "moltenmetal/patina_steel_pillar",
				new String[] {"copper_inferno:patina_steel", "", "", "copper_inferno:patina_steel", "", "", "", "", ""},
				"copper_inferno:patina_steel_pillar", 2, "Craft 2x Patina Steel Pillar at a crafting table.", "Stellt 2x Patinastahls\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_slab_from_patina_steel_stonecutting", "copper_inferno:patina_steel_slab", "moltenmetal/patina_steel_slab_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_slab", 2, "Stonecutting: cut 2x Patina Steel Slab from Patina Steel.", "Steins\u00e4ge: 2x Patinastahlstufe aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_stairs_from_patina_steel_stonecutting", "copper_inferno:patina_steel_stairs", "moltenmetal/patina_steel_stairs_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_stairs", 1, "Stonecutting: cut 1x Patina Steel Stairs from Patina Steel.", "Steins\u00e4ge: 1x Patinastahltreppe aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_wall_from_patina_steel_stonecutting", "copper_inferno:patina_steel_wall", "moltenmetal/patina_steel_wall_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_wall", 1, "Stonecutting: cut 1x Patina Steel Wall from Patina Steel.", "Steins\u00e4ge: 1x Patinastahlmauer aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_patina_steel_from_patina_steel_stonecutting", "copper_inferno:polished_patina_steel", "moltenmetal/polished_patina_steel_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:polished_patina_steel", 1, "Stonecutting: cut 1x Polished Patina Steel from Patina Steel.", "Steins\u00e4ge: 1x Polierten Patinastahl aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_patina_steel_slab_from_patina_steel_stonecutting", "copper_inferno:polished_patina_steel_slab", "moltenmetal/polished_patina_steel_slab_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:polished_patina_steel_slab", 2, "Stonecutting: cut 2x Polished Patina Steel Slab from Patina Steel.", "Steins\u00e4ge: 2x Polierte Patinastahlstufe aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_patina_steel_stairs_from_patina_steel_stonecutting", "copper_inferno:polished_patina_steel_stairs", "moltenmetal/polished_patina_steel_stairs_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:polished_patina_steel_stairs", 1, "Stonecutting: cut 1x Polished Patina Steel Stairs from Patina Steel.", "Steins\u00e4ge: 1x Polierte Patinastahltreppe aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_patina_steel_wall_from_patina_steel_stonecutting", "copper_inferno:polished_patina_steel_wall", "moltenmetal/polished_patina_steel_wall_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:polished_patina_steel_wall", 1, "Stonecutting: cut 1x Polished Patina Steel Wall from Patina Steel.", "Steins\u00e4ge: 1x Polierte Patinastahlmauer aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_bricks_from_patina_steel_stonecutting", "copper_inferno:patina_steel_bricks", "moltenmetal/patina_steel_bricks_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_bricks", 1, "Stonecutting: cut 1x Patina Steel Bricks from Patina Steel.", "Steins\u00e4ge: 1x Patinastahlziegel aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_brick_slab_from_patina_steel_stonecutting", "copper_inferno:patina_steel_brick_slab", "moltenmetal/patina_steel_brick_slab_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_brick_slab", 2, "Stonecutting: cut 2x Patina Steel Brick Slab from Patina Steel.", "Steins\u00e4ge: 2x Patinastahlziegelstufe aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_brick_stairs_from_patina_steel_stonecutting", "copper_inferno:patina_steel_brick_stairs", "moltenmetal/patina_steel_brick_stairs_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_brick_stairs", 1, "Stonecutting: cut 1x Patina Steel Brick Stairs from Patina Steel.", "Steins\u00e4ge: 1x Patinastahlziegeltreppe aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_brick_wall_from_patina_steel_stonecutting", "copper_inferno:patina_steel_brick_wall", "moltenmetal/patina_steel_brick_wall_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_brick_wall", 1, "Stonecutting: cut 1x Patina Steel Brick Wall from Patina Steel.", "Steins\u00e4ge: 1x Patinastahlziegelmauer aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_tiles_from_patina_steel_stonecutting", "copper_inferno:patina_steel_tiles", "moltenmetal/patina_steel_tiles_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_tiles", 1, "Stonecutting: cut 1x Patina Steel Tiles from Patina Steel.", "Steins\u00e4ge: 1x Patinastahlfliesen aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_patina_steel_bricks_from_patina_steel_stonecutting", "copper_inferno:chiseled_patina_steel_bricks", "moltenmetal/chiseled_patina_steel_bricks_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:chiseled_patina_steel_bricks", 1, "Stonecutting: cut 1x Chiseled Patina Steel Bricks from Patina Steel.", "Steins\u00e4ge: 1x Gemei\u00dfelte Patinastahlziegel aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/patina_steel_pillar_from_patina_steel_stonecutting", "copper_inferno:patina_steel_pillar", "moltenmetal/patina_steel_pillar_from_patina_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:patina_steel", "", "", "", ""},
				"copper_inferno:patina_steel_pillar", 1, "Stonecutting: cut 1x Patina Steel Pillar from Patina Steel.", "Steins\u00e4ge: 1x Patinastahls\u00e4ule aus Patinastahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron", "copper_inferno:cinder_iron", "moltenmetal/cinder_iron",
				new String[] {"minecraft:iron_nugget", "", "minecraft:iron_nugget", "", "copper_inferno:patina_steel", "", "minecraft:iron_nugget", "", "minecraft:iron_nugget"},
				"copper_inferno:cinder_iron", 4, "Craft 4x Cinder Iron at a crafting table.", "Stellt 4x Zundereisen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "moltenmetal/cinder_iron_bricks",
				new String[] {"copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "", "copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_bricks", 4, "Craft 4x Cinder Iron Bricks at a crafting table.", "Stellt 4x Zundereisenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_tiles", "copper_inferno:cinder_iron_tiles", "moltenmetal/cinder_iron_tiles",
				new String[] {"copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "", "copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "", "", "", ""},
				"copper_inferno:cinder_iron_tiles", 4, "Craft 4x Cinder Iron Tiles at a crafting table.", "Stellt 4x Zundereisenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_cinder_iron", "copper_inferno:polished_cinder_iron", "moltenmetal/polished_cinder_iron",
				new String[] {"copper_inferno:cinder_iron_tiles", "copper_inferno:cinder_iron_tiles", "", "copper_inferno:cinder_iron_tiles", "copper_inferno:cinder_iron_tiles", "", "", "", ""},
				"copper_inferno:polished_cinder_iron", 4, "Craft 4x Polished Cinder Iron at a crafting table.", "Stellt 4x Poliertes Zundereisen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_slab", "copper_inferno:cinder_iron_slab", "moltenmetal/cinder_iron_slab",
				new String[] {"copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "", "", "", "", "", ""},
				"copper_inferno:cinder_iron_slab", 6, "Craft 6x Cinder Iron Slab at a crafting table.", "Stellt 6x Zundereisenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_stairs", "copper_inferno:cinder_iron_stairs", "moltenmetal/cinder_iron_stairs",
				new String[] {"copper_inferno:cinder_iron", "", "", "copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "", "copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "copper_inferno:cinder_iron"},
				"copper_inferno:cinder_iron_stairs", 4, "Craft 4x Cinder Iron Stairs at a crafting table.", "Stellt 4x Zundereisentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_wall", "copper_inferno:cinder_iron_wall", "moltenmetal/cinder_iron_wall",
				new String[] {"copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "copper_inferno:cinder_iron", "", "", ""},
				"copper_inferno:cinder_iron_wall", 6, "Craft 6x Cinder Iron Wall at a crafting table.", "Stellt 6x Zundereisenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_cinder_iron_slab", "copper_inferno:polished_cinder_iron_slab", "moltenmetal/polished_cinder_iron_slab",
				new String[] {"copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron", "", "", "", "", "", ""},
				"copper_inferno:polished_cinder_iron_slab", 6, "Craft 6x Polished Cinder Iron Slab at a crafting table.", "Stellt 6x Polierte Zundereisenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_cinder_iron_stairs", "copper_inferno:polished_cinder_iron_stairs", "moltenmetal/polished_cinder_iron_stairs",
				new String[] {"copper_inferno:polished_cinder_iron", "", "", "copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron", "", "copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron"},
				"copper_inferno:polished_cinder_iron_stairs", 4, "Craft 4x Polished Cinder Iron Stairs at a crafting table.", "Stellt 4x Polierte Zundereisentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_cinder_iron_wall", "copper_inferno:polished_cinder_iron_wall", "moltenmetal/polished_cinder_iron_wall",
				new String[] {"copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron", "copper_inferno:polished_cinder_iron", "", "", ""},
				"copper_inferno:polished_cinder_iron_wall", 6, "Craft 6x Polished Cinder Iron Wall at a crafting table.", "Stellt 6x Polierte Zundereisenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_brick_slab", "copper_inferno:cinder_iron_brick_slab", "moltenmetal/cinder_iron_brick_slab",
				new String[] {"copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "", "", "", "", "", ""},
				"copper_inferno:cinder_iron_brick_slab", 6, "Craft 6x Cinder Iron Brick Slab at a crafting table.", "Stellt 6x Zundereisenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_brick_stairs", "copper_inferno:cinder_iron_brick_stairs", "moltenmetal/cinder_iron_brick_stairs",
				new String[] {"copper_inferno:cinder_iron_bricks", "", "", "copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "", "copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks"},
				"copper_inferno:cinder_iron_brick_stairs", 4, "Craft 4x Cinder Iron Brick Stairs at a crafting table.", "Stellt 4x Zundereisenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_brick_wall", "copper_inferno:cinder_iron_brick_wall", "moltenmetal/cinder_iron_brick_wall",
				new String[] {"copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "copper_inferno:cinder_iron_bricks", "", "", ""},
				"copper_inferno:cinder_iron_brick_wall", 6, "Craft 6x Cinder Iron Brick Wall at a crafting table.", "Stellt 6x Zundereisenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_cinder_iron_bricks", "copper_inferno:cracked_cinder_iron_bricks", "moltenmetal/cracked_cinder_iron_bricks",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron_bricks", "", "", "", ""},
				"copper_inferno:cracked_cinder_iron_bricks", 1, "Smelting Cinder Iron Bricks in a furnace yields Cracked Cinder Iron Bricks.", "Zundereisenziegel im Ofen gebrannt ergibt Rissige Zundereisenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_cinder_iron_bricks", "copper_inferno:chiseled_cinder_iron_bricks", "moltenmetal/chiseled_cinder_iron_bricks",
				new String[] {"copper_inferno:cinder_iron_brick_slab", "", "", "copper_inferno:cinder_iron_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_cinder_iron_bricks", 1, "Craft 1x Chiseled Cinder Iron Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Zundereisenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_pillar", "copper_inferno:cinder_iron_pillar", "moltenmetal/cinder_iron_pillar",
				new String[] {"copper_inferno:cinder_iron", "", "", "copper_inferno:cinder_iron", "", "", "", "", ""},
				"copper_inferno:cinder_iron_pillar", 2, "Craft 2x Cinder Iron Pillar at a crafting table.", "Stellt 2x Zundereisens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_slab_from_cinder_iron_stonecutting", "copper_inferno:cinder_iron_slab", "moltenmetal/cinder_iron_slab_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_slab", 2, "Stonecutting: cut 2x Cinder Iron Slab from Cinder Iron.", "Steins\u00e4ge: 2x Zundereisenstufe aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_stairs_from_cinder_iron_stonecutting", "copper_inferno:cinder_iron_stairs", "moltenmetal/cinder_iron_stairs_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_stairs", 1, "Stonecutting: cut 1x Cinder Iron Stairs from Cinder Iron.", "Steins\u00e4ge: 1x Zundereisentreppe aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_wall_from_cinder_iron_stonecutting", "copper_inferno:cinder_iron_wall", "moltenmetal/cinder_iron_wall_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_wall", 1, "Stonecutting: cut 1x Cinder Iron Wall from Cinder Iron.", "Steins\u00e4ge: 1x Zundereisenmauer aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_cinder_iron_from_cinder_iron_stonecutting", "copper_inferno:polished_cinder_iron", "moltenmetal/polished_cinder_iron_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:polished_cinder_iron", 1, "Stonecutting: cut 1x Polished Cinder Iron from Cinder Iron.", "Steins\u00e4ge: 1x Poliertes Zundereisen aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_cinder_iron_slab_from_cinder_iron_stonecutting", "copper_inferno:polished_cinder_iron_slab", "moltenmetal/polished_cinder_iron_slab_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:polished_cinder_iron_slab", 2, "Stonecutting: cut 2x Polished Cinder Iron Slab from Cinder Iron.", "Steins\u00e4ge: 2x Polierte Zundereisenstufe aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_cinder_iron_stairs_from_cinder_iron_stonecutting", "copper_inferno:polished_cinder_iron_stairs", "moltenmetal/polished_cinder_iron_stairs_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:polished_cinder_iron_stairs", 1, "Stonecutting: cut 1x Polished Cinder Iron Stairs from Cinder Iron.", "Steins\u00e4ge: 1x Polierte Zundereisentreppe aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_cinder_iron_wall_from_cinder_iron_stonecutting", "copper_inferno:polished_cinder_iron_wall", "moltenmetal/polished_cinder_iron_wall_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:polished_cinder_iron_wall", 1, "Stonecutting: cut 1x Polished Cinder Iron Wall from Cinder Iron.", "Steins\u00e4ge: 1x Polierte Zundereisenmauer aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_bricks_from_cinder_iron_stonecutting", "copper_inferno:cinder_iron_bricks", "moltenmetal/cinder_iron_bricks_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_bricks", 1, "Stonecutting: cut 1x Cinder Iron Bricks from Cinder Iron.", "Steins\u00e4ge: 1x Zundereisenziegel aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_brick_slab_from_cinder_iron_stonecutting", "copper_inferno:cinder_iron_brick_slab", "moltenmetal/cinder_iron_brick_slab_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_brick_slab", 2, "Stonecutting: cut 2x Cinder Iron Brick Slab from Cinder Iron.", "Steins\u00e4ge: 2x Zundereisenziegelstufe aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_brick_stairs_from_cinder_iron_stonecutting", "copper_inferno:cinder_iron_brick_stairs", "moltenmetal/cinder_iron_brick_stairs_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_brick_stairs", 1, "Stonecutting: cut 1x Cinder Iron Brick Stairs from Cinder Iron.", "Steins\u00e4ge: 1x Zundereisenziegeltreppe aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_brick_wall_from_cinder_iron_stonecutting", "copper_inferno:cinder_iron_brick_wall", "moltenmetal/cinder_iron_brick_wall_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_brick_wall", 1, "Stonecutting: cut 1x Cinder Iron Brick Wall from Cinder Iron.", "Steins\u00e4ge: 1x Zundereisenziegelmauer aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_tiles_from_cinder_iron_stonecutting", "copper_inferno:cinder_iron_tiles", "moltenmetal/cinder_iron_tiles_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_tiles", 1, "Stonecutting: cut 1x Cinder Iron Tiles from Cinder Iron.", "Steins\u00e4ge: 1x Zundereisenfliesen aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_cinder_iron_bricks_from_cinder_iron_stonecutting", "copper_inferno:chiseled_cinder_iron_bricks", "moltenmetal/chiseled_cinder_iron_bricks_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:chiseled_cinder_iron_bricks", 1, "Stonecutting: cut 1x Chiseled Cinder Iron Bricks from Cinder Iron.", "Steins\u00e4ge: 1x Gemei\u00dfelte Zundereisenziegel aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cinder_iron_pillar_from_cinder_iron_stonecutting", "copper_inferno:cinder_iron_pillar", "moltenmetal/cinder_iron_pillar_from_cinder_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:cinder_iron", "", "", "", ""},
				"copper_inferno:cinder_iron_pillar", 1, "Stonecutting: cut 1x Cinder Iron Pillar from Cinder Iron.", "Steins\u00e4ge: 1x Zundereisens\u00e4ule aus Zundereisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal", "copper_inferno:forgeworn_metal", "moltenmetal/forgeworn_metal",
				new String[] {"minecraft:iron_bars", "", "minecraft:iron_bars", "", "copper_inferno:cinder_iron", "", "minecraft:iron_bars", "", "minecraft:iron_bars"},
				"copper_inferno:forgeworn_metal", 4, "Craft 4x Forgeworn Metal at a crafting table.", "Stellt 4x Essenmetall an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "moltenmetal/forgeworn_metal_bricks",
				new String[] {"copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "", "copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_bricks", 4, "Craft 4x Forgeworn Metal Bricks at a crafting table.", "Stellt 4x Essenmetallziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_tiles", "copper_inferno:forgeworn_metal_tiles", "moltenmetal/forgeworn_metal_tiles",
				new String[] {"copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "", "copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "", "", "", ""},
				"copper_inferno:forgeworn_metal_tiles", 4, "Craft 4x Forgeworn Metal Tiles at a crafting table.", "Stellt 4x Essenmetallfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "moltenmetal/polished_forgeworn_metal",
				new String[] {"copper_inferno:forgeworn_metal_tiles", "copper_inferno:forgeworn_metal_tiles", "", "copper_inferno:forgeworn_metal_tiles", "copper_inferno:forgeworn_metal_tiles", "", "", "", ""},
				"copper_inferno:polished_forgeworn_metal", 4, "Craft 4x Polished Forgeworn Metal at a crafting table.", "Stellt 4x Poliertes Essenmetall an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_slab", "copper_inferno:forgeworn_metal_slab", "moltenmetal/forgeworn_metal_slab",
				new String[] {"copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "", "", "", "", "", ""},
				"copper_inferno:forgeworn_metal_slab", 6, "Craft 6x Forgeworn Metal Slab at a crafting table.", "Stellt 6x Essenmetallstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_stairs", "copper_inferno:forgeworn_metal_stairs", "moltenmetal/forgeworn_metal_stairs",
				new String[] {"copper_inferno:forgeworn_metal", "", "", "copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "", "copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal"},
				"copper_inferno:forgeworn_metal_stairs", 4, "Craft 4x Forgeworn Metal Stairs at a crafting table.", "Stellt 4x Essenmetalltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_wall", "copper_inferno:forgeworn_metal_wall", "moltenmetal/forgeworn_metal_wall",
				new String[] {"copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "copper_inferno:forgeworn_metal", "", "", ""},
				"copper_inferno:forgeworn_metal_wall", 6, "Craft 6x Forgeworn Metal Wall at a crafting table.", "Stellt 6x Essenmetallmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_forgeworn_metal_slab", "copper_inferno:polished_forgeworn_metal_slab", "moltenmetal/polished_forgeworn_metal_slab",
				new String[] {"copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "", "", "", "", "", ""},
				"copper_inferno:polished_forgeworn_metal_slab", 6, "Craft 6x Polished Forgeworn Metal Slab at a crafting table.", "Stellt 6x Polierte Essenmetallstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_forgeworn_metal_stairs", "copper_inferno:polished_forgeworn_metal_stairs", "moltenmetal/polished_forgeworn_metal_stairs",
				new String[] {"copper_inferno:polished_forgeworn_metal", "", "", "copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "", "copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal"},
				"copper_inferno:polished_forgeworn_metal_stairs", 4, "Craft 4x Polished Forgeworn Metal Stairs at a crafting table.", "Stellt 4x Polierte Essenmetalltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_forgeworn_metal_wall", "copper_inferno:polished_forgeworn_metal_wall", "moltenmetal/polished_forgeworn_metal_wall",
				new String[] {"copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "copper_inferno:polished_forgeworn_metal", "", "", ""},
				"copper_inferno:polished_forgeworn_metal_wall", 6, "Craft 6x Polished Forgeworn Metal Wall at a crafting table.", "Stellt 6x Polierte Essenmetallmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_brick_slab", "copper_inferno:forgeworn_metal_brick_slab", "moltenmetal/forgeworn_metal_brick_slab",
				new String[] {"copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "", "", "", "", "", ""},
				"copper_inferno:forgeworn_metal_brick_slab", 6, "Craft 6x Forgeworn Metal Brick Slab at a crafting table.", "Stellt 6x Essenmetallziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_brick_stairs", "copper_inferno:forgeworn_metal_brick_stairs", "moltenmetal/forgeworn_metal_brick_stairs",
				new String[] {"copper_inferno:forgeworn_metal_bricks", "", "", "copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "", "copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks"},
				"copper_inferno:forgeworn_metal_brick_stairs", 4, "Craft 4x Forgeworn Metal Brick Stairs at a crafting table.", "Stellt 4x Essenmetallziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_brick_wall", "copper_inferno:forgeworn_metal_brick_wall", "moltenmetal/forgeworn_metal_brick_wall",
				new String[] {"copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "copper_inferno:forgeworn_metal_bricks", "", "", ""},
				"copper_inferno:forgeworn_metal_brick_wall", 6, "Craft 6x Forgeworn Metal Brick Wall at a crafting table.", "Stellt 6x Essenmetallziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_forgeworn_metal_bricks", "copper_inferno:cracked_forgeworn_metal_bricks", "moltenmetal/cracked_forgeworn_metal_bricks",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal_bricks", "", "", "", ""},
				"copper_inferno:cracked_forgeworn_metal_bricks", 1, "Smelting Forgeworn Metal Bricks in a furnace yields Cracked Forgeworn Metal Bricks.", "Essenmetallziegel im Ofen gebrannt ergibt Rissige Essenmetallziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_forgeworn_metal_bricks", "copper_inferno:chiseled_forgeworn_metal_bricks", "moltenmetal/chiseled_forgeworn_metal_bricks",
				new String[] {"copper_inferno:forgeworn_metal_brick_slab", "", "", "copper_inferno:forgeworn_metal_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_forgeworn_metal_bricks", 1, "Craft 1x Chiseled Forgeworn Metal Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Essenmetallziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_pillar", "copper_inferno:forgeworn_metal_pillar", "moltenmetal/forgeworn_metal_pillar",
				new String[] {"copper_inferno:forgeworn_metal", "", "", "copper_inferno:forgeworn_metal", "", "", "", "", ""},
				"copper_inferno:forgeworn_metal_pillar", 2, "Craft 2x Forgeworn Metal Pillar at a crafting table.", "Stellt 2x Essenmetalls\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_slab_from_forgeworn_metal_stonecutting", "copper_inferno:forgeworn_metal_slab", "moltenmetal/forgeworn_metal_slab_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_slab", 2, "Stonecutting: cut 2x Forgeworn Metal Slab from Forgeworn Metal.", "Steins\u00e4ge: 2x Essenmetallstufe aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_stairs_from_forgeworn_metal_stonecutting", "copper_inferno:forgeworn_metal_stairs", "moltenmetal/forgeworn_metal_stairs_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_stairs", 1, "Stonecutting: cut 1x Forgeworn Metal Stairs from Forgeworn Metal.", "Steins\u00e4ge: 1x Essenmetalltreppe aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_wall_from_forgeworn_metal_stonecutting", "copper_inferno:forgeworn_metal_wall", "moltenmetal/forgeworn_metal_wall_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_wall", 1, "Stonecutting: cut 1x Forgeworn Metal Wall from Forgeworn Metal.", "Steins\u00e4ge: 1x Essenmetallmauer aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_forgeworn_metal_from_forgeworn_metal_stonecutting", "copper_inferno:polished_forgeworn_metal", "moltenmetal/polished_forgeworn_metal_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:polished_forgeworn_metal", 1, "Stonecutting: cut 1x Polished Forgeworn Metal from Forgeworn Metal.", "Steins\u00e4ge: 1x Poliertes Essenmetall aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_forgeworn_metal_slab_from_forgeworn_metal_stonecutting", "copper_inferno:polished_forgeworn_metal_slab", "moltenmetal/polished_forgeworn_metal_slab_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:polished_forgeworn_metal_slab", 2, "Stonecutting: cut 2x Polished Forgeworn Metal Slab from Forgeworn Metal.", "Steins\u00e4ge: 2x Polierte Essenmetallstufe aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_forgeworn_metal_stairs_from_forgeworn_metal_stonecutting", "copper_inferno:polished_forgeworn_metal_stairs", "moltenmetal/polished_forgeworn_metal_stairs_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:polished_forgeworn_metal_stairs", 1, "Stonecutting: cut 1x Polished Forgeworn Metal Stairs from Forgeworn Metal.", "Steins\u00e4ge: 1x Polierte Essenmetalltreppe aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_forgeworn_metal_wall_from_forgeworn_metal_stonecutting", "copper_inferno:polished_forgeworn_metal_wall", "moltenmetal/polished_forgeworn_metal_wall_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:polished_forgeworn_metal_wall", 1, "Stonecutting: cut 1x Polished Forgeworn Metal Wall from Forgeworn Metal.", "Steins\u00e4ge: 1x Polierte Essenmetallmauer aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_bricks_from_forgeworn_metal_stonecutting", "copper_inferno:forgeworn_metal_bricks", "moltenmetal/forgeworn_metal_bricks_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_bricks", 1, "Stonecutting: cut 1x Forgeworn Metal Bricks from Forgeworn Metal.", "Steins\u00e4ge: 1x Essenmetallziegel aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_brick_slab_from_forgeworn_metal_stonecutting", "copper_inferno:forgeworn_metal_brick_slab", "moltenmetal/forgeworn_metal_brick_slab_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_brick_slab", 2, "Stonecutting: cut 2x Forgeworn Metal Brick Slab from Forgeworn Metal.", "Steins\u00e4ge: 2x Essenmetallziegelstufe aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_brick_stairs_from_forgeworn_metal_stonecutting", "copper_inferno:forgeworn_metal_brick_stairs", "moltenmetal/forgeworn_metal_brick_stairs_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_brick_stairs", 1, "Stonecutting: cut 1x Forgeworn Metal Brick Stairs from Forgeworn Metal.", "Steins\u00e4ge: 1x Essenmetallziegeltreppe aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_brick_wall_from_forgeworn_metal_stonecutting", "copper_inferno:forgeworn_metal_brick_wall", "moltenmetal/forgeworn_metal_brick_wall_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_brick_wall", 1, "Stonecutting: cut 1x Forgeworn Metal Brick Wall from Forgeworn Metal.", "Steins\u00e4ge: 1x Essenmetallziegelmauer aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_tiles_from_forgeworn_metal_stonecutting", "copper_inferno:forgeworn_metal_tiles", "moltenmetal/forgeworn_metal_tiles_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_tiles", 1, "Stonecutting: cut 1x Forgeworn Metal Tiles from Forgeworn Metal.", "Steins\u00e4ge: 1x Essenmetallfliesen aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_forgeworn_metal_bricks_from_forgeworn_metal_stonecutting", "copper_inferno:chiseled_forgeworn_metal_bricks", "moltenmetal/chiseled_forgeworn_metal_bricks_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:chiseled_forgeworn_metal_bricks", 1, "Stonecutting: cut 1x Chiseled Forgeworn Metal Bricks from Forgeworn Metal.", "Steins\u00e4ge: 1x Gemei\u00dfelte Essenmetallziegel aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/forgeworn_metal_pillar_from_forgeworn_metal_stonecutting", "copper_inferno:forgeworn_metal_pillar", "moltenmetal/forgeworn_metal_pillar_from_forgeworn_metal_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:forgeworn_metal", "", "", "", ""},
				"copper_inferno:forgeworn_metal_pillar", 1, "Stonecutting: cut 1x Forgeworn Metal Pillar from Forgeworn Metal.", "Steins\u00e4ge: 1x Essenmetalls\u00e4ule aus Essenmetall schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze", "copper_inferno:blistered_bronze", "moltenmetal/blistered_bronze",
				new String[] {"minecraft:magma_cream", "", "minecraft:magma_cream", "", "copper_inferno:forgeworn_metal", "", "minecraft:magma_cream", "", "minecraft:magma_cream"},
				"copper_inferno:blistered_bronze", 4, "Craft 4x Blistered Bronze at a crafting table.", "Stellt 4x Blasenbronze an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "moltenmetal/blistered_bronze_bricks",
				new String[] {"copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "", "copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_bricks", 4, "Craft 4x Blistered Bronze Bricks at a crafting table.", "Stellt 4x Blasenbronzeziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_tiles", "copper_inferno:blistered_bronze_tiles", "moltenmetal/blistered_bronze_tiles",
				new String[] {"copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "", "copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "", "", "", ""},
				"copper_inferno:blistered_bronze_tiles", 4, "Craft 4x Blistered Bronze Tiles at a crafting table.", "Stellt 4x Blasenbronzefliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "moltenmetal/polished_blistered_bronze",
				new String[] {"copper_inferno:blistered_bronze_tiles", "copper_inferno:blistered_bronze_tiles", "", "copper_inferno:blistered_bronze_tiles", "copper_inferno:blistered_bronze_tiles", "", "", "", ""},
				"copper_inferno:polished_blistered_bronze", 4, "Craft 4x Polished Blistered Bronze at a crafting table.", "Stellt 4x Polierte Blasenbronze an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_slab", "copper_inferno:blistered_bronze_slab", "moltenmetal/blistered_bronze_slab",
				new String[] {"copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "", "", "", "", "", ""},
				"copper_inferno:blistered_bronze_slab", 6, "Craft 6x Blistered Bronze Slab at a crafting table.", "Stellt 6x Blasenbronzestufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_stairs", "copper_inferno:blistered_bronze_stairs", "moltenmetal/blistered_bronze_stairs",
				new String[] {"copper_inferno:blistered_bronze", "", "", "copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "", "copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze"},
				"copper_inferno:blistered_bronze_stairs", 4, "Craft 4x Blistered Bronze Stairs at a crafting table.", "Stellt 4x Blasenbronzetreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_wall", "copper_inferno:blistered_bronze_wall", "moltenmetal/blistered_bronze_wall",
				new String[] {"copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "copper_inferno:blistered_bronze", "", "", ""},
				"copper_inferno:blistered_bronze_wall", 6, "Craft 6x Blistered Bronze Wall at a crafting table.", "Stellt 6x Blasenbronzemauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_blistered_bronze_slab", "copper_inferno:polished_blistered_bronze_slab", "moltenmetal/polished_blistered_bronze_slab",
				new String[] {"copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "", "", "", "", "", ""},
				"copper_inferno:polished_blistered_bronze_slab", 6, "Craft 6x Polished Blistered Bronze Slab at a crafting table.", "Stellt 6x Polierte Blasenbronzestufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_blistered_bronze_stairs", "copper_inferno:polished_blistered_bronze_stairs", "moltenmetal/polished_blistered_bronze_stairs",
				new String[] {"copper_inferno:polished_blistered_bronze", "", "", "copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "", "copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze"},
				"copper_inferno:polished_blistered_bronze_stairs", 4, "Craft 4x Polished Blistered Bronze Stairs at a crafting table.", "Stellt 4x Polierte Blasenbronzetreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_blistered_bronze_wall", "copper_inferno:polished_blistered_bronze_wall", "moltenmetal/polished_blistered_bronze_wall",
				new String[] {"copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "copper_inferno:polished_blistered_bronze", "", "", ""},
				"copper_inferno:polished_blistered_bronze_wall", 6, "Craft 6x Polished Blistered Bronze Wall at a crafting table.", "Stellt 6x Polierte Blasenbronzemauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_brick_slab", "copper_inferno:blistered_bronze_brick_slab", "moltenmetal/blistered_bronze_brick_slab",
				new String[] {"copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "", "", "", "", "", ""},
				"copper_inferno:blistered_bronze_brick_slab", 6, "Craft 6x Blistered Bronze Brick Slab at a crafting table.", "Stellt 6x Blasenbronzeziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_brick_stairs", "copper_inferno:blistered_bronze_brick_stairs", "moltenmetal/blistered_bronze_brick_stairs",
				new String[] {"copper_inferno:blistered_bronze_bricks", "", "", "copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "", "copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks"},
				"copper_inferno:blistered_bronze_brick_stairs", 4, "Craft 4x Blistered Bronze Brick Stairs at a crafting table.", "Stellt 4x Blasenbronzeziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_brick_wall", "copper_inferno:blistered_bronze_brick_wall", "moltenmetal/blistered_bronze_brick_wall",
				new String[] {"copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "copper_inferno:blistered_bronze_bricks", "", "", ""},
				"copper_inferno:blistered_bronze_brick_wall", 6, "Craft 6x Blistered Bronze Brick Wall at a crafting table.", "Stellt 6x Blasenbronzeziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_blistered_bronze_bricks", "copper_inferno:cracked_blistered_bronze_bricks", "moltenmetal/cracked_blistered_bronze_bricks",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze_bricks", "", "", "", ""},
				"copper_inferno:cracked_blistered_bronze_bricks", 1, "Smelting Blistered Bronze Bricks in a furnace yields Cracked Blistered Bronze Bricks.", "Blasenbronzeziegel im Ofen gebrannt ergibt Rissige Blasenbronzeziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_blistered_bronze_bricks", "copper_inferno:chiseled_blistered_bronze_bricks", "moltenmetal/chiseled_blistered_bronze_bricks",
				new String[] {"copper_inferno:blistered_bronze_brick_slab", "", "", "copper_inferno:blistered_bronze_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_blistered_bronze_bricks", 1, "Craft 1x Chiseled Blistered Bronze Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Blasenbronzeziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_pillar", "copper_inferno:blistered_bronze_pillar", "moltenmetal/blistered_bronze_pillar",
				new String[] {"copper_inferno:blistered_bronze", "", "", "copper_inferno:blistered_bronze", "", "", "", "", ""},
				"copper_inferno:blistered_bronze_pillar", 2, "Craft 2x Blistered Bronze Pillar at a crafting table.", "Stellt 2x Blasenbronzes\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_slab_from_blistered_bronze_stonecutting", "copper_inferno:blistered_bronze_slab", "moltenmetal/blistered_bronze_slab_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_slab", 2, "Stonecutting: cut 2x Blistered Bronze Slab from Blistered Bronze.", "Steins\u00e4ge: 2x Blasenbronzestufe aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_stairs_from_blistered_bronze_stonecutting", "copper_inferno:blistered_bronze_stairs", "moltenmetal/blistered_bronze_stairs_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_stairs", 1, "Stonecutting: cut 1x Blistered Bronze Stairs from Blistered Bronze.", "Steins\u00e4ge: 1x Blasenbronzetreppe aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_wall_from_blistered_bronze_stonecutting", "copper_inferno:blistered_bronze_wall", "moltenmetal/blistered_bronze_wall_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_wall", 1, "Stonecutting: cut 1x Blistered Bronze Wall from Blistered Bronze.", "Steins\u00e4ge: 1x Blasenbronzemauer aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_blistered_bronze_from_blistered_bronze_stonecutting", "copper_inferno:polished_blistered_bronze", "moltenmetal/polished_blistered_bronze_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:polished_blistered_bronze", 1, "Stonecutting: cut 1x Polished Blistered Bronze from Blistered Bronze.", "Steins\u00e4ge: 1x Polierte Blasenbronze aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_blistered_bronze_slab_from_blistered_bronze_stonecutting", "copper_inferno:polished_blistered_bronze_slab", "moltenmetal/polished_blistered_bronze_slab_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:polished_blistered_bronze_slab", 2, "Stonecutting: cut 2x Polished Blistered Bronze Slab from Blistered Bronze.", "Steins\u00e4ge: 2x Polierte Blasenbronzestufe aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_blistered_bronze_stairs_from_blistered_bronze_stonecutting", "copper_inferno:polished_blistered_bronze_stairs", "moltenmetal/polished_blistered_bronze_stairs_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:polished_blistered_bronze_stairs", 1, "Stonecutting: cut 1x Polished Blistered Bronze Stairs from Blistered Bronze.", "Steins\u00e4ge: 1x Polierte Blasenbronzetreppe aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_blistered_bronze_wall_from_blistered_bronze_stonecutting", "copper_inferno:polished_blistered_bronze_wall", "moltenmetal/polished_blistered_bronze_wall_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:polished_blistered_bronze_wall", 1, "Stonecutting: cut 1x Polished Blistered Bronze Wall from Blistered Bronze.", "Steins\u00e4ge: 1x Polierte Blasenbronzemauer aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_bricks_from_blistered_bronze_stonecutting", "copper_inferno:blistered_bronze_bricks", "moltenmetal/blistered_bronze_bricks_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_bricks", 1, "Stonecutting: cut 1x Blistered Bronze Bricks from Blistered Bronze.", "Steins\u00e4ge: 1x Blasenbronzeziegel aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_brick_slab_from_blistered_bronze_stonecutting", "copper_inferno:blistered_bronze_brick_slab", "moltenmetal/blistered_bronze_brick_slab_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_brick_slab", 2, "Stonecutting: cut 2x Blistered Bronze Brick Slab from Blistered Bronze.", "Steins\u00e4ge: 2x Blasenbronzeziegelstufe aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_brick_stairs_from_blistered_bronze_stonecutting", "copper_inferno:blistered_bronze_brick_stairs", "moltenmetal/blistered_bronze_brick_stairs_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_brick_stairs", 1, "Stonecutting: cut 1x Blistered Bronze Brick Stairs from Blistered Bronze.", "Steins\u00e4ge: 1x Blasenbronzeziegeltreppe aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_brick_wall_from_blistered_bronze_stonecutting", "copper_inferno:blistered_bronze_brick_wall", "moltenmetal/blistered_bronze_brick_wall_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_brick_wall", 1, "Stonecutting: cut 1x Blistered Bronze Brick Wall from Blistered Bronze.", "Steins\u00e4ge: 1x Blasenbronzeziegelmauer aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_tiles_from_blistered_bronze_stonecutting", "copper_inferno:blistered_bronze_tiles", "moltenmetal/blistered_bronze_tiles_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_tiles", 1, "Stonecutting: cut 1x Blistered Bronze Tiles from Blistered Bronze.", "Steins\u00e4ge: 1x Blasenbronzefliesen aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_blistered_bronze_bricks_from_blistered_bronze_stonecutting", "copper_inferno:chiseled_blistered_bronze_bricks", "moltenmetal/chiseled_blistered_bronze_bricks_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:chiseled_blistered_bronze_bricks", 1, "Stonecutting: cut 1x Chiseled Blistered Bronze Bricks from Blistered Bronze.", "Steins\u00e4ge: 1x Gemei\u00dfelte Blasenbronzeziegel aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/blistered_bronze_pillar_from_blistered_bronze_stonecutting", "copper_inferno:blistered_bronze_pillar", "moltenmetal/blistered_bronze_pillar_from_blistered_bronze_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:blistered_bronze", "", "", "", ""},
				"copper_inferno:blistered_bronze_pillar", 1, "Stonecutting: cut 1x Blistered Bronze Pillar from Blistered Bronze.", "Steins\u00e4ge: 1x Blasenbronzes\u00e4ule aus Blasenbronze schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel", "copper_inferno:scalding_steel", "moltenmetal/scalding_steel",
				new String[] {"minecraft:blaze_powder", "", "minecraft:blaze_powder", "", "copper_inferno:blistered_bronze", "", "minecraft:blaze_powder", "", "minecraft:blaze_powder"},
				"copper_inferno:scalding_steel", 4, "Craft 4x Scalding Steel at a crafting table.", "Stellt 4x Gl\u00fchstahl an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "moltenmetal/scalding_steel_bricks",
				new String[] {"copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "", "copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_bricks", 4, "Craft 4x Scalding Steel Bricks at a crafting table.", "Stellt 4x Gl\u00fchstahlziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_tiles", "copper_inferno:scalding_steel_tiles", "moltenmetal/scalding_steel_tiles",
				new String[] {"copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "", "copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "", "", "", ""},
				"copper_inferno:scalding_steel_tiles", 4, "Craft 4x Scalding Steel Tiles at a crafting table.", "Stellt 4x Gl\u00fchstahlfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_scalding_steel", "copper_inferno:polished_scalding_steel", "moltenmetal/polished_scalding_steel",
				new String[] {"copper_inferno:scalding_steel_tiles", "copper_inferno:scalding_steel_tiles", "", "copper_inferno:scalding_steel_tiles", "copper_inferno:scalding_steel_tiles", "", "", "", ""},
				"copper_inferno:polished_scalding_steel", 4, "Craft 4x Polished Scalding Steel at a crafting table.", "Stellt 4x Polierten Gl\u00fchstahl an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_slab", "copper_inferno:scalding_steel_slab", "moltenmetal/scalding_steel_slab",
				new String[] {"copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "", "", "", "", "", ""},
				"copper_inferno:scalding_steel_slab", 6, "Craft 6x Scalding Steel Slab at a crafting table.", "Stellt 6x Gl\u00fchstahlstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_stairs", "copper_inferno:scalding_steel_stairs", "moltenmetal/scalding_steel_stairs",
				new String[] {"copper_inferno:scalding_steel", "", "", "copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "", "copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "copper_inferno:scalding_steel"},
				"copper_inferno:scalding_steel_stairs", 4, "Craft 4x Scalding Steel Stairs at a crafting table.", "Stellt 4x Gl\u00fchstahltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_wall", "copper_inferno:scalding_steel_wall", "moltenmetal/scalding_steel_wall",
				new String[] {"copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "copper_inferno:scalding_steel", "", "", ""},
				"copper_inferno:scalding_steel_wall", 6, "Craft 6x Scalding Steel Wall at a crafting table.", "Stellt 6x Gl\u00fchstahlmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_scalding_steel_slab", "copper_inferno:polished_scalding_steel_slab", "moltenmetal/polished_scalding_steel_slab",
				new String[] {"copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel", "", "", "", "", "", ""},
				"copper_inferno:polished_scalding_steel_slab", 6, "Craft 6x Polished Scalding Steel Slab at a crafting table.", "Stellt 6x Polierte Gl\u00fchstahlstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_scalding_steel_stairs", "copper_inferno:polished_scalding_steel_stairs", "moltenmetal/polished_scalding_steel_stairs",
				new String[] {"copper_inferno:polished_scalding_steel", "", "", "copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel", "", "copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel"},
				"copper_inferno:polished_scalding_steel_stairs", 4, "Craft 4x Polished Scalding Steel Stairs at a crafting table.", "Stellt 4x Polierte Gl\u00fchstahltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_scalding_steel_wall", "copper_inferno:polished_scalding_steel_wall", "moltenmetal/polished_scalding_steel_wall",
				new String[] {"copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel", "copper_inferno:polished_scalding_steel", "", "", ""},
				"copper_inferno:polished_scalding_steel_wall", 6, "Craft 6x Polished Scalding Steel Wall at a crafting table.", "Stellt 6x Polierte Gl\u00fchstahlmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_brick_slab", "copper_inferno:scalding_steel_brick_slab", "moltenmetal/scalding_steel_brick_slab",
				new String[] {"copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "", "", "", "", "", ""},
				"copper_inferno:scalding_steel_brick_slab", 6, "Craft 6x Scalding Steel Brick Slab at a crafting table.", "Stellt 6x Gl\u00fchstahlziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_brick_stairs", "copper_inferno:scalding_steel_brick_stairs", "moltenmetal/scalding_steel_brick_stairs",
				new String[] {"copper_inferno:scalding_steel_bricks", "", "", "copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "", "copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks"},
				"copper_inferno:scalding_steel_brick_stairs", 4, "Craft 4x Scalding Steel Brick Stairs at a crafting table.", "Stellt 4x Gl\u00fchstahlziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_brick_wall", "copper_inferno:scalding_steel_brick_wall", "moltenmetal/scalding_steel_brick_wall",
				new String[] {"copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "copper_inferno:scalding_steel_bricks", "", "", ""},
				"copper_inferno:scalding_steel_brick_wall", 6, "Craft 6x Scalding Steel Brick Wall at a crafting table.", "Stellt 6x Gl\u00fchstahlziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_scalding_steel_bricks", "copper_inferno:cracked_scalding_steel_bricks", "moltenmetal/cracked_scalding_steel_bricks",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel_bricks", "", "", "", ""},
				"copper_inferno:cracked_scalding_steel_bricks", 1, "Smelting Scalding Steel Bricks in a furnace yields Cracked Scalding Steel Bricks.", "Gl\u00fchstahlziegel im Ofen gebrannt ergibt Rissige Gl\u00fchstahlziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_scalding_steel_bricks", "copper_inferno:chiseled_scalding_steel_bricks", "moltenmetal/chiseled_scalding_steel_bricks",
				new String[] {"copper_inferno:scalding_steel_brick_slab", "", "", "copper_inferno:scalding_steel_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_scalding_steel_bricks", 1, "Craft 1x Chiseled Scalding Steel Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Gl\u00fchstahlziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_pillar", "copper_inferno:scalding_steel_pillar", "moltenmetal/scalding_steel_pillar",
				new String[] {"copper_inferno:scalding_steel", "", "", "copper_inferno:scalding_steel", "", "", "", "", ""},
				"copper_inferno:scalding_steel_pillar", 2, "Craft 2x Scalding Steel Pillar at a crafting table.", "Stellt 2x Gl\u00fchstahls\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_slab_from_scalding_steel_stonecutting", "copper_inferno:scalding_steel_slab", "moltenmetal/scalding_steel_slab_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_slab", 2, "Stonecutting: cut 2x Scalding Steel Slab from Scalding Steel.", "Steins\u00e4ge: 2x Gl\u00fchstahlstufe aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_stairs_from_scalding_steel_stonecutting", "copper_inferno:scalding_steel_stairs", "moltenmetal/scalding_steel_stairs_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_stairs", 1, "Stonecutting: cut 1x Scalding Steel Stairs from Scalding Steel.", "Steins\u00e4ge: 1x Gl\u00fchstahltreppe aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_wall_from_scalding_steel_stonecutting", "copper_inferno:scalding_steel_wall", "moltenmetal/scalding_steel_wall_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_wall", 1, "Stonecutting: cut 1x Scalding Steel Wall from Scalding Steel.", "Steins\u00e4ge: 1x Gl\u00fchstahlmauer aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_scalding_steel_from_scalding_steel_stonecutting", "copper_inferno:polished_scalding_steel", "moltenmetal/polished_scalding_steel_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:polished_scalding_steel", 1, "Stonecutting: cut 1x Polished Scalding Steel from Scalding Steel.", "Steins\u00e4ge: 1x Polierten Gl\u00fchstahl aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_scalding_steel_slab_from_scalding_steel_stonecutting", "copper_inferno:polished_scalding_steel_slab", "moltenmetal/polished_scalding_steel_slab_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:polished_scalding_steel_slab", 2, "Stonecutting: cut 2x Polished Scalding Steel Slab from Scalding Steel.", "Steins\u00e4ge: 2x Polierte Gl\u00fchstahlstufe aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_scalding_steel_stairs_from_scalding_steel_stonecutting", "copper_inferno:polished_scalding_steel_stairs", "moltenmetal/polished_scalding_steel_stairs_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:polished_scalding_steel_stairs", 1, "Stonecutting: cut 1x Polished Scalding Steel Stairs from Scalding Steel.", "Steins\u00e4ge: 1x Polierte Gl\u00fchstahltreppe aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_scalding_steel_wall_from_scalding_steel_stonecutting", "copper_inferno:polished_scalding_steel_wall", "moltenmetal/polished_scalding_steel_wall_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:polished_scalding_steel_wall", 1, "Stonecutting: cut 1x Polished Scalding Steel Wall from Scalding Steel.", "Steins\u00e4ge: 1x Polierte Gl\u00fchstahlmauer aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_bricks_from_scalding_steel_stonecutting", "copper_inferno:scalding_steel_bricks", "moltenmetal/scalding_steel_bricks_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_bricks", 1, "Stonecutting: cut 1x Scalding Steel Bricks from Scalding Steel.", "Steins\u00e4ge: 1x Gl\u00fchstahlziegel aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_brick_slab_from_scalding_steel_stonecutting", "copper_inferno:scalding_steel_brick_slab", "moltenmetal/scalding_steel_brick_slab_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_brick_slab", 2, "Stonecutting: cut 2x Scalding Steel Brick Slab from Scalding Steel.", "Steins\u00e4ge: 2x Gl\u00fchstahlziegelstufe aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_brick_stairs_from_scalding_steel_stonecutting", "copper_inferno:scalding_steel_brick_stairs", "moltenmetal/scalding_steel_brick_stairs_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_brick_stairs", 1, "Stonecutting: cut 1x Scalding Steel Brick Stairs from Scalding Steel.", "Steins\u00e4ge: 1x Gl\u00fchstahlziegeltreppe aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_brick_wall_from_scalding_steel_stonecutting", "copper_inferno:scalding_steel_brick_wall", "moltenmetal/scalding_steel_brick_wall_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_brick_wall", 1, "Stonecutting: cut 1x Scalding Steel Brick Wall from Scalding Steel.", "Steins\u00e4ge: 1x Gl\u00fchstahlziegelmauer aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_tiles_from_scalding_steel_stonecutting", "copper_inferno:scalding_steel_tiles", "moltenmetal/scalding_steel_tiles_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_tiles", 1, "Stonecutting: cut 1x Scalding Steel Tiles from Scalding Steel.", "Steins\u00e4ge: 1x Gl\u00fchstahlfliesen aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_scalding_steel_bricks_from_scalding_steel_stonecutting", "copper_inferno:chiseled_scalding_steel_bricks", "moltenmetal/chiseled_scalding_steel_bricks_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:chiseled_scalding_steel_bricks", 1, "Stonecutting: cut 1x Chiseled Scalding Steel Bricks from Scalding Steel.", "Steins\u00e4ge: 1x Gemei\u00dfelte Gl\u00fchstahlziegel aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/scalding_steel_pillar_from_scalding_steel_stonecutting", "copper_inferno:scalding_steel_pillar", "moltenmetal/scalding_steel_pillar_from_scalding_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:scalding_steel", "", "", "", ""},
				"copper_inferno:scalding_steel_pillar", 1, "Stonecutting: cut 1x Scalding Steel Pillar from Scalding Steel.", "Steins\u00e4ge: 1x Gl\u00fchstahls\u00e4ule aus Gl\u00fchstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy", "copper_inferno:smelters_alloy", "moltenmetal/smelters_alloy",
				new String[] {"minecraft:raw_iron", "", "minecraft:raw_iron", "", "copper_inferno:scalding_steel", "", "minecraft:raw_iron", "", "minecraft:raw_iron"},
				"copper_inferno:smelters_alloy", 4, "Craft 4x Smelter's Alloy at a crafting table.", "Stellt 4x Schmelzerlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "moltenmetal/smelters_alloy_bricks",
				new String[] {"copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "", "copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_bricks", 4, "Craft 4x Smelter's Alloy Bricks at a crafting table.", "Stellt 4x Schmelzerlegierungsziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_tiles", "copper_inferno:smelters_alloy_tiles", "moltenmetal/smelters_alloy_tiles",
				new String[] {"copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "", "copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "", "", "", ""},
				"copper_inferno:smelters_alloy_tiles", 4, "Craft 4x Smelter's Alloy Tiles at a crafting table.", "Stellt 4x Schmelzerlegierungsfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "moltenmetal/polished_smelters_alloy",
				new String[] {"copper_inferno:smelters_alloy_tiles", "copper_inferno:smelters_alloy_tiles", "", "copper_inferno:smelters_alloy_tiles", "copper_inferno:smelters_alloy_tiles", "", "", "", ""},
				"copper_inferno:polished_smelters_alloy", 4, "Craft 4x Polished Smelter's Alloy at a crafting table.", "Stellt 4x Polierte Schmelzerlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_slab", "copper_inferno:smelters_alloy_slab", "moltenmetal/smelters_alloy_slab",
				new String[] {"copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "", "", "", "", "", ""},
				"copper_inferno:smelters_alloy_slab", 6, "Craft 6x Smelter's Alloy Slab at a crafting table.", "Stellt 6x Schmelzerlegierungsstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_stairs", "copper_inferno:smelters_alloy_stairs", "moltenmetal/smelters_alloy_stairs",
				new String[] {"copper_inferno:smelters_alloy", "", "", "copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "", "copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy"},
				"copper_inferno:smelters_alloy_stairs", 4, "Craft 4x Smelter's Alloy Stairs at a crafting table.", "Stellt 4x Schmelzerlegierungstreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_wall", "copper_inferno:smelters_alloy_wall", "moltenmetal/smelters_alloy_wall",
				new String[] {"copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "copper_inferno:smelters_alloy", "", "", ""},
				"copper_inferno:smelters_alloy_wall", 6, "Craft 6x Smelter's Alloy Wall at a crafting table.", "Stellt 6x Schmelzerlegierungsmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_smelters_alloy_slab", "copper_inferno:polished_smelters_alloy_slab", "moltenmetal/polished_smelters_alloy_slab",
				new String[] {"copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "", "", "", "", "", ""},
				"copper_inferno:polished_smelters_alloy_slab", 6, "Craft 6x Polished Smelter's Alloy Slab at a crafting table.", "Stellt 6x Polierte Schmelzerlegierungsstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_smelters_alloy_stairs", "copper_inferno:polished_smelters_alloy_stairs", "moltenmetal/polished_smelters_alloy_stairs",
				new String[] {"copper_inferno:polished_smelters_alloy", "", "", "copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "", "copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy"},
				"copper_inferno:polished_smelters_alloy_stairs", 4, "Craft 4x Polished Smelter's Alloy Stairs at a crafting table.", "Stellt 4x Polierte Schmelzerlegierungstreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_smelters_alloy_wall", "copper_inferno:polished_smelters_alloy_wall", "moltenmetal/polished_smelters_alloy_wall",
				new String[] {"copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "copper_inferno:polished_smelters_alloy", "", "", ""},
				"copper_inferno:polished_smelters_alloy_wall", 6, "Craft 6x Polished Smelter's Alloy Wall at a crafting table.", "Stellt 6x Polierte Schmelzerlegierungsmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_brick_slab", "copper_inferno:smelters_alloy_brick_slab", "moltenmetal/smelters_alloy_brick_slab",
				new String[] {"copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "", "", "", "", "", ""},
				"copper_inferno:smelters_alloy_brick_slab", 6, "Craft 6x Smelter's Alloy Brick Slab at a crafting table.", "Stellt 6x Schmelzerlegierungsziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_brick_stairs", "copper_inferno:smelters_alloy_brick_stairs", "moltenmetal/smelters_alloy_brick_stairs",
				new String[] {"copper_inferno:smelters_alloy_bricks", "", "", "copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "", "copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks"},
				"copper_inferno:smelters_alloy_brick_stairs", 4, "Craft 4x Smelter's Alloy Brick Stairs at a crafting table.", "Stellt 4x Schmelzerlegierungsziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_brick_wall", "copper_inferno:smelters_alloy_brick_wall", "moltenmetal/smelters_alloy_brick_wall",
				new String[] {"copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "copper_inferno:smelters_alloy_bricks", "", "", ""},
				"copper_inferno:smelters_alloy_brick_wall", 6, "Craft 6x Smelter's Alloy Brick Wall at a crafting table.", "Stellt 6x Schmelzerlegierungsziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_smelters_alloy_bricks", "copper_inferno:cracked_smelters_alloy_bricks", "moltenmetal/cracked_smelters_alloy_bricks",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy_bricks", "", "", "", ""},
				"copper_inferno:cracked_smelters_alloy_bricks", 1, "Smelting Smelter's Alloy Bricks in a furnace yields Cracked Smelter's Alloy Bricks.", "Schmelzerlegierungsziegel im Ofen gebrannt ergibt Rissige Schmelzerlegierungsziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_smelters_alloy_bricks", "copper_inferno:chiseled_smelters_alloy_bricks", "moltenmetal/chiseled_smelters_alloy_bricks",
				new String[] {"copper_inferno:smelters_alloy_brick_slab", "", "", "copper_inferno:smelters_alloy_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_smelters_alloy_bricks", 1, "Craft 1x Chiseled Smelter's Alloy Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schmelzerlegierungsziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_pillar", "copper_inferno:smelters_alloy_pillar", "moltenmetal/smelters_alloy_pillar",
				new String[] {"copper_inferno:smelters_alloy", "", "", "copper_inferno:smelters_alloy", "", "", "", "", ""},
				"copper_inferno:smelters_alloy_pillar", 2, "Craft 2x Smelter's Alloy Pillar at a crafting table.", "Stellt 2x Schmelzerlegierungss\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_slab_from_smelters_alloy_stonecutting", "copper_inferno:smelters_alloy_slab", "moltenmetal/smelters_alloy_slab_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_slab", 2, "Stonecutting: cut 2x Smelter's Alloy Slab from Smelter's Alloy.", "Steins\u00e4ge: 2x Schmelzerlegierungsstufe aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_stairs_from_smelters_alloy_stonecutting", "copper_inferno:smelters_alloy_stairs", "moltenmetal/smelters_alloy_stairs_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_stairs", 1, "Stonecutting: cut 1x Smelter's Alloy Stairs from Smelter's Alloy.", "Steins\u00e4ge: 1x Schmelzerlegierungstreppe aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_wall_from_smelters_alloy_stonecutting", "copper_inferno:smelters_alloy_wall", "moltenmetal/smelters_alloy_wall_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_wall", 1, "Stonecutting: cut 1x Smelter's Alloy Wall from Smelter's Alloy.", "Steins\u00e4ge: 1x Schmelzerlegierungsmauer aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_smelters_alloy_from_smelters_alloy_stonecutting", "copper_inferno:polished_smelters_alloy", "moltenmetal/polished_smelters_alloy_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:polished_smelters_alloy", 1, "Stonecutting: cut 1x Polished Smelter's Alloy from Smelter's Alloy.", "Steins\u00e4ge: 1x Polierte Schmelzerlegierung aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_smelters_alloy_slab_from_smelters_alloy_stonecutting", "copper_inferno:polished_smelters_alloy_slab", "moltenmetal/polished_smelters_alloy_slab_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:polished_smelters_alloy_slab", 2, "Stonecutting: cut 2x Polished Smelter's Alloy Slab from Smelter's Alloy.", "Steins\u00e4ge: 2x Polierte Schmelzerlegierungsstufe aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_smelters_alloy_stairs_from_smelters_alloy_stonecutting", "copper_inferno:polished_smelters_alloy_stairs", "moltenmetal/polished_smelters_alloy_stairs_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:polished_smelters_alloy_stairs", 1, "Stonecutting: cut 1x Polished Smelter's Alloy Stairs from Smelter's Alloy.", "Steins\u00e4ge: 1x Polierte Schmelzerlegierungstreppe aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_smelters_alloy_wall_from_smelters_alloy_stonecutting", "copper_inferno:polished_smelters_alloy_wall", "moltenmetal/polished_smelters_alloy_wall_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:polished_smelters_alloy_wall", 1, "Stonecutting: cut 1x Polished Smelter's Alloy Wall from Smelter's Alloy.", "Steins\u00e4ge: 1x Polierte Schmelzerlegierungsmauer aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_bricks_from_smelters_alloy_stonecutting", "copper_inferno:smelters_alloy_bricks", "moltenmetal/smelters_alloy_bricks_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_bricks", 1, "Stonecutting: cut 1x Smelter's Alloy Bricks from Smelter's Alloy.", "Steins\u00e4ge: 1x Schmelzerlegierungsziegel aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_brick_slab_from_smelters_alloy_stonecutting", "copper_inferno:smelters_alloy_brick_slab", "moltenmetal/smelters_alloy_brick_slab_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_brick_slab", 2, "Stonecutting: cut 2x Smelter's Alloy Brick Slab from Smelter's Alloy.", "Steins\u00e4ge: 2x Schmelzerlegierungsziegelstufe aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_brick_stairs_from_smelters_alloy_stonecutting", "copper_inferno:smelters_alloy_brick_stairs", "moltenmetal/smelters_alloy_brick_stairs_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_brick_stairs", 1, "Stonecutting: cut 1x Smelter's Alloy Brick Stairs from Smelter's Alloy.", "Steins\u00e4ge: 1x Schmelzerlegierungsziegeltreppe aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_brick_wall_from_smelters_alloy_stonecutting", "copper_inferno:smelters_alloy_brick_wall", "moltenmetal/smelters_alloy_brick_wall_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_brick_wall", 1, "Stonecutting: cut 1x Smelter's Alloy Brick Wall from Smelter's Alloy.", "Steins\u00e4ge: 1x Schmelzerlegierungsziegelmauer aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_tiles_from_smelters_alloy_stonecutting", "copper_inferno:smelters_alloy_tiles", "moltenmetal/smelters_alloy_tiles_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_tiles", 1, "Stonecutting: cut 1x Smelter's Alloy Tiles from Smelter's Alloy.", "Steins\u00e4ge: 1x Schmelzerlegierungsfliesen aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_smelters_alloy_bricks_from_smelters_alloy_stonecutting", "copper_inferno:chiseled_smelters_alloy_bricks", "moltenmetal/chiseled_smelters_alloy_bricks_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:chiseled_smelters_alloy_bricks", 1, "Stonecutting: cut 1x Chiseled Smelter's Alloy Bricks from Smelter's Alloy.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schmelzerlegierungsziegel aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/smelters_alloy_pillar_from_smelters_alloy_stonecutting", "copper_inferno:smelters_alloy_pillar", "moltenmetal/smelters_alloy_pillar_from_smelters_alloy_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:smelters_alloy", "", "", "", ""},
				"copper_inferno:smelters_alloy_pillar", 1, "Stonecutting: cut 1x Smelter's Alloy Pillar from Smelter's Alloy.", "Steins\u00e4ge: 1x Schmelzerlegierungss\u00e4ule aus Schmelzerlegierung schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel", "copper_inferno:furnace_steel", "moltenmetal/furnace_steel",
				new String[] {"minecraft:iron_ingot", "", "minecraft:iron_ingot", "", "copper_inferno:smelters_alloy", "", "minecraft:iron_ingot", "", "minecraft:iron_ingot"},
				"copper_inferno:furnace_steel", 4, "Craft 4x Furnace Steel at a crafting table.", "Stellt 4x Ofenstahl an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "moltenmetal/furnace_steel_bricks",
				new String[] {"copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "", "copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_bricks", 4, "Craft 4x Furnace Steel Bricks at a crafting table.", "Stellt 4x Ofenstahlziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_tiles", "copper_inferno:furnace_steel_tiles", "moltenmetal/furnace_steel_tiles",
				new String[] {"copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "", "copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "", "", "", ""},
				"copper_inferno:furnace_steel_tiles", 4, "Craft 4x Furnace Steel Tiles at a crafting table.", "Stellt 4x Ofenstahlfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_furnace_steel", "copper_inferno:polished_furnace_steel", "moltenmetal/polished_furnace_steel",
				new String[] {"copper_inferno:furnace_steel_tiles", "copper_inferno:furnace_steel_tiles", "", "copper_inferno:furnace_steel_tiles", "copper_inferno:furnace_steel_tiles", "", "", "", ""},
				"copper_inferno:polished_furnace_steel", 4, "Craft 4x Polished Furnace Steel at a crafting table.", "Stellt 4x Polierten Ofenstahl an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_slab", "copper_inferno:furnace_steel_slab", "moltenmetal/furnace_steel_slab",
				new String[] {"copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "", "", "", "", "", ""},
				"copper_inferno:furnace_steel_slab", 6, "Craft 6x Furnace Steel Slab at a crafting table.", "Stellt 6x Ofenstahlstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_stairs", "copper_inferno:furnace_steel_stairs", "moltenmetal/furnace_steel_stairs",
				new String[] {"copper_inferno:furnace_steel", "", "", "copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "", "copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "copper_inferno:furnace_steel"},
				"copper_inferno:furnace_steel_stairs", 4, "Craft 4x Furnace Steel Stairs at a crafting table.", "Stellt 4x Ofenstahltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_wall", "copper_inferno:furnace_steel_wall", "moltenmetal/furnace_steel_wall",
				new String[] {"copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "copper_inferno:furnace_steel", "", "", ""},
				"copper_inferno:furnace_steel_wall", 6, "Craft 6x Furnace Steel Wall at a crafting table.", "Stellt 6x Ofenstahlmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_furnace_steel_slab", "copper_inferno:polished_furnace_steel_slab", "moltenmetal/polished_furnace_steel_slab",
				new String[] {"copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel", "", "", "", "", "", ""},
				"copper_inferno:polished_furnace_steel_slab", 6, "Craft 6x Polished Furnace Steel Slab at a crafting table.", "Stellt 6x Polierte Ofenstahlstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_furnace_steel_stairs", "copper_inferno:polished_furnace_steel_stairs", "moltenmetal/polished_furnace_steel_stairs",
				new String[] {"copper_inferno:polished_furnace_steel", "", "", "copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel", "", "copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel"},
				"copper_inferno:polished_furnace_steel_stairs", 4, "Craft 4x Polished Furnace Steel Stairs at a crafting table.", "Stellt 4x Polierte Ofenstahltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_furnace_steel_wall", "copper_inferno:polished_furnace_steel_wall", "moltenmetal/polished_furnace_steel_wall",
				new String[] {"copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel", "copper_inferno:polished_furnace_steel", "", "", ""},
				"copper_inferno:polished_furnace_steel_wall", 6, "Craft 6x Polished Furnace Steel Wall at a crafting table.", "Stellt 6x Polierte Ofenstahlmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_brick_slab", "copper_inferno:furnace_steel_brick_slab", "moltenmetal/furnace_steel_brick_slab",
				new String[] {"copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "", "", "", "", "", ""},
				"copper_inferno:furnace_steel_brick_slab", 6, "Craft 6x Furnace Steel Brick Slab at a crafting table.", "Stellt 6x Ofenstahlziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_brick_stairs", "copper_inferno:furnace_steel_brick_stairs", "moltenmetal/furnace_steel_brick_stairs",
				new String[] {"copper_inferno:furnace_steel_bricks", "", "", "copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "", "copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks"},
				"copper_inferno:furnace_steel_brick_stairs", 4, "Craft 4x Furnace Steel Brick Stairs at a crafting table.", "Stellt 4x Ofenstahlziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_brick_wall", "copper_inferno:furnace_steel_brick_wall", "moltenmetal/furnace_steel_brick_wall",
				new String[] {"copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "copper_inferno:furnace_steel_bricks", "", "", ""},
				"copper_inferno:furnace_steel_brick_wall", 6, "Craft 6x Furnace Steel Brick Wall at a crafting table.", "Stellt 6x Ofenstahlziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_furnace_steel_bricks", "copper_inferno:cracked_furnace_steel_bricks", "moltenmetal/cracked_furnace_steel_bricks",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel_bricks", "", "", "", ""},
				"copper_inferno:cracked_furnace_steel_bricks", 1, "Smelting Furnace Steel Bricks in a furnace yields Cracked Furnace Steel Bricks.", "Ofenstahlziegel im Ofen gebrannt ergibt Rissige Ofenstahlziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_furnace_steel_bricks", "copper_inferno:chiseled_furnace_steel_bricks", "moltenmetal/chiseled_furnace_steel_bricks",
				new String[] {"copper_inferno:furnace_steel_brick_slab", "", "", "copper_inferno:furnace_steel_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_furnace_steel_bricks", 1, "Craft 1x Chiseled Furnace Steel Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Ofenstahlziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_pillar", "copper_inferno:furnace_steel_pillar", "moltenmetal/furnace_steel_pillar",
				new String[] {"copper_inferno:furnace_steel", "", "", "copper_inferno:furnace_steel", "", "", "", "", ""},
				"copper_inferno:furnace_steel_pillar", 2, "Craft 2x Furnace Steel Pillar at a crafting table.", "Stellt 2x Ofenstahls\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_slab_from_furnace_steel_stonecutting", "copper_inferno:furnace_steel_slab", "moltenmetal/furnace_steel_slab_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_slab", 2, "Stonecutting: cut 2x Furnace Steel Slab from Furnace Steel.", "Steins\u00e4ge: 2x Ofenstahlstufe aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_stairs_from_furnace_steel_stonecutting", "copper_inferno:furnace_steel_stairs", "moltenmetal/furnace_steel_stairs_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_stairs", 1, "Stonecutting: cut 1x Furnace Steel Stairs from Furnace Steel.", "Steins\u00e4ge: 1x Ofenstahltreppe aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_wall_from_furnace_steel_stonecutting", "copper_inferno:furnace_steel_wall", "moltenmetal/furnace_steel_wall_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_wall", 1, "Stonecutting: cut 1x Furnace Steel Wall from Furnace Steel.", "Steins\u00e4ge: 1x Ofenstahlmauer aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_furnace_steel_from_furnace_steel_stonecutting", "copper_inferno:polished_furnace_steel", "moltenmetal/polished_furnace_steel_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:polished_furnace_steel", 1, "Stonecutting: cut 1x Polished Furnace Steel from Furnace Steel.", "Steins\u00e4ge: 1x Polierten Ofenstahl aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_furnace_steel_slab_from_furnace_steel_stonecutting", "copper_inferno:polished_furnace_steel_slab", "moltenmetal/polished_furnace_steel_slab_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:polished_furnace_steel_slab", 2, "Stonecutting: cut 2x Polished Furnace Steel Slab from Furnace Steel.", "Steins\u00e4ge: 2x Polierte Ofenstahlstufe aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_furnace_steel_stairs_from_furnace_steel_stonecutting", "copper_inferno:polished_furnace_steel_stairs", "moltenmetal/polished_furnace_steel_stairs_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:polished_furnace_steel_stairs", 1, "Stonecutting: cut 1x Polished Furnace Steel Stairs from Furnace Steel.", "Steins\u00e4ge: 1x Polierte Ofenstahltreppe aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_furnace_steel_wall_from_furnace_steel_stonecutting", "copper_inferno:polished_furnace_steel_wall", "moltenmetal/polished_furnace_steel_wall_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:polished_furnace_steel_wall", 1, "Stonecutting: cut 1x Polished Furnace Steel Wall from Furnace Steel.", "Steins\u00e4ge: 1x Polierte Ofenstahlmauer aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_bricks_from_furnace_steel_stonecutting", "copper_inferno:furnace_steel_bricks", "moltenmetal/furnace_steel_bricks_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_bricks", 1, "Stonecutting: cut 1x Furnace Steel Bricks from Furnace Steel.", "Steins\u00e4ge: 1x Ofenstahlziegel aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_brick_slab_from_furnace_steel_stonecutting", "copper_inferno:furnace_steel_brick_slab", "moltenmetal/furnace_steel_brick_slab_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_brick_slab", 2, "Stonecutting: cut 2x Furnace Steel Brick Slab from Furnace Steel.", "Steins\u00e4ge: 2x Ofenstahlziegelstufe aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_brick_stairs_from_furnace_steel_stonecutting", "copper_inferno:furnace_steel_brick_stairs", "moltenmetal/furnace_steel_brick_stairs_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_brick_stairs", 1, "Stonecutting: cut 1x Furnace Steel Brick Stairs from Furnace Steel.", "Steins\u00e4ge: 1x Ofenstahlziegeltreppe aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_brick_wall_from_furnace_steel_stonecutting", "copper_inferno:furnace_steel_brick_wall", "moltenmetal/furnace_steel_brick_wall_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_brick_wall", 1, "Stonecutting: cut 1x Furnace Steel Brick Wall from Furnace Steel.", "Steins\u00e4ge: 1x Ofenstahlziegelmauer aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_tiles_from_furnace_steel_stonecutting", "copper_inferno:furnace_steel_tiles", "moltenmetal/furnace_steel_tiles_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_tiles", 1, "Stonecutting: cut 1x Furnace Steel Tiles from Furnace Steel.", "Steins\u00e4ge: 1x Ofenstahlfliesen aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_furnace_steel_bricks_from_furnace_steel_stonecutting", "copper_inferno:chiseled_furnace_steel_bricks", "moltenmetal/chiseled_furnace_steel_bricks_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:chiseled_furnace_steel_bricks", 1, "Stonecutting: cut 1x Chiseled Furnace Steel Bricks from Furnace Steel.", "Steins\u00e4ge: 1x Gemei\u00dfelte Ofenstahlziegel aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/furnace_steel_pillar_from_furnace_steel_stonecutting", "copper_inferno:furnace_steel_pillar", "moltenmetal/furnace_steel_pillar_from_furnace_steel_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:furnace_steel", "", "", "", ""},
				"copper_inferno:furnace_steel_pillar", 1, "Stonecutting: cut 1x Furnace Steel Pillar from Furnace Steel.", "Steins\u00e4ge: 1x Ofenstahls\u00e4ule aus Ofenstahl schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron", "copper_inferno:quench_iron", "moltenmetal/quench_iron",
				new String[] {"minecraft:prismarine_shard", "", "minecraft:prismarine_shard", "", "copper_inferno:furnace_steel", "", "minecraft:prismarine_shard", "", "minecraft:prismarine_shard"},
				"copper_inferno:quench_iron", 4, "Craft 4x Quench Iron at a crafting table.", "Stellt 4x H\u00e4rteeisen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_bricks", "copper_inferno:quench_iron_bricks", "moltenmetal/quench_iron_bricks",
				new String[] {"copper_inferno:quench_iron", "copper_inferno:quench_iron", "", "copper_inferno:quench_iron", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_bricks", 4, "Craft 4x Quench Iron Bricks at a crafting table.", "Stellt 4x H\u00e4rteeisenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_tiles", "copper_inferno:quench_iron_tiles", "moltenmetal/quench_iron_tiles",
				new String[] {"copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "", "copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "", "", "", ""},
				"copper_inferno:quench_iron_tiles", 4, "Craft 4x Quench Iron Tiles at a crafting table.", "Stellt 4x H\u00e4rteeisenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_quench_iron", "copper_inferno:polished_quench_iron", "moltenmetal/polished_quench_iron",
				new String[] {"copper_inferno:quench_iron_tiles", "copper_inferno:quench_iron_tiles", "", "copper_inferno:quench_iron_tiles", "copper_inferno:quench_iron_tiles", "", "", "", ""},
				"copper_inferno:polished_quench_iron", 4, "Craft 4x Polished Quench Iron at a crafting table.", "Stellt 4x Poliertes H\u00e4rteeisen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_slab", "copper_inferno:quench_iron_slab", "moltenmetal/quench_iron_slab",
				new String[] {"copper_inferno:quench_iron", "copper_inferno:quench_iron", "copper_inferno:quench_iron", "", "", "", "", "", ""},
				"copper_inferno:quench_iron_slab", 6, "Craft 6x Quench Iron Slab at a crafting table.", "Stellt 6x H\u00e4rteeisenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_stairs", "copper_inferno:quench_iron_stairs", "moltenmetal/quench_iron_stairs",
				new String[] {"copper_inferno:quench_iron", "", "", "copper_inferno:quench_iron", "copper_inferno:quench_iron", "", "copper_inferno:quench_iron", "copper_inferno:quench_iron", "copper_inferno:quench_iron"},
				"copper_inferno:quench_iron_stairs", 4, "Craft 4x Quench Iron Stairs at a crafting table.", "Stellt 4x H\u00e4rteeisentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_wall", "copper_inferno:quench_iron_wall", "moltenmetal/quench_iron_wall",
				new String[] {"copper_inferno:quench_iron", "copper_inferno:quench_iron", "copper_inferno:quench_iron", "copper_inferno:quench_iron", "copper_inferno:quench_iron", "copper_inferno:quench_iron", "", "", ""},
				"copper_inferno:quench_iron_wall", 6, "Craft 6x Quench Iron Wall at a crafting table.", "Stellt 6x H\u00e4rteeisenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_quench_iron_slab", "copper_inferno:polished_quench_iron_slab", "moltenmetal/polished_quench_iron_slab",
				new String[] {"copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron", "", "", "", "", "", ""},
				"copper_inferno:polished_quench_iron_slab", 6, "Craft 6x Polished Quench Iron Slab at a crafting table.", "Stellt 6x Polierte H\u00e4rteeisenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_quench_iron_stairs", "copper_inferno:polished_quench_iron_stairs", "moltenmetal/polished_quench_iron_stairs",
				new String[] {"copper_inferno:polished_quench_iron", "", "", "copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron", "", "copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron"},
				"copper_inferno:polished_quench_iron_stairs", 4, "Craft 4x Polished Quench Iron Stairs at a crafting table.", "Stellt 4x Polierte H\u00e4rteeisentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_quench_iron_wall", "copper_inferno:polished_quench_iron_wall", "moltenmetal/polished_quench_iron_wall",
				new String[] {"copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron", "copper_inferno:polished_quench_iron", "", "", ""},
				"copper_inferno:polished_quench_iron_wall", 6, "Craft 6x Polished Quench Iron Wall at a crafting table.", "Stellt 6x Polierte H\u00e4rteeisenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_brick_slab", "copper_inferno:quench_iron_brick_slab", "moltenmetal/quench_iron_brick_slab",
				new String[] {"copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "", "", "", "", "", ""},
				"copper_inferno:quench_iron_brick_slab", 6, "Craft 6x Quench Iron Brick Slab at a crafting table.", "Stellt 6x H\u00e4rteeisenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_brick_stairs", "copper_inferno:quench_iron_brick_stairs", "moltenmetal/quench_iron_brick_stairs",
				new String[] {"copper_inferno:quench_iron_bricks", "", "", "copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "", "copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks"},
				"copper_inferno:quench_iron_brick_stairs", 4, "Craft 4x Quench Iron Brick Stairs at a crafting table.", "Stellt 4x H\u00e4rteeisenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_brick_wall", "copper_inferno:quench_iron_brick_wall", "moltenmetal/quench_iron_brick_wall",
				new String[] {"copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "copper_inferno:quench_iron_bricks", "", "", ""},
				"copper_inferno:quench_iron_brick_wall", 6, "Craft 6x Quench Iron Brick Wall at a crafting table.", "Stellt 6x H\u00e4rteeisenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_quench_iron_bricks", "copper_inferno:cracked_quench_iron_bricks", "moltenmetal/cracked_quench_iron_bricks",
				new String[] {"", "", "", "", "copper_inferno:quench_iron_bricks", "", "", "", ""},
				"copper_inferno:cracked_quench_iron_bricks", 1, "Smelting Quench Iron Bricks in a furnace yields Cracked Quench Iron Bricks.", "H\u00e4rteeisenziegel im Ofen gebrannt ergibt Rissige H\u00e4rteeisenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_quench_iron_bricks", "copper_inferno:chiseled_quench_iron_bricks", "moltenmetal/chiseled_quench_iron_bricks",
				new String[] {"copper_inferno:quench_iron_brick_slab", "", "", "copper_inferno:quench_iron_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_quench_iron_bricks", 1, "Craft 1x Chiseled Quench Iron Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte H\u00e4rteeisenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_pillar", "copper_inferno:quench_iron_pillar", "moltenmetal/quench_iron_pillar",
				new String[] {"copper_inferno:quench_iron", "", "", "copper_inferno:quench_iron", "", "", "", "", ""},
				"copper_inferno:quench_iron_pillar", 2, "Craft 2x Quench Iron Pillar at a crafting table.", "Stellt 2x H\u00e4rteeisens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_slab_from_quench_iron_stonecutting", "copper_inferno:quench_iron_slab", "moltenmetal/quench_iron_slab_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_slab", 2, "Stonecutting: cut 2x Quench Iron Slab from Quench Iron.", "Steins\u00e4ge: 2x H\u00e4rteeisenstufe aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_stairs_from_quench_iron_stonecutting", "copper_inferno:quench_iron_stairs", "moltenmetal/quench_iron_stairs_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_stairs", 1, "Stonecutting: cut 1x Quench Iron Stairs from Quench Iron.", "Steins\u00e4ge: 1x H\u00e4rteeisentreppe aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_wall_from_quench_iron_stonecutting", "copper_inferno:quench_iron_wall", "moltenmetal/quench_iron_wall_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_wall", 1, "Stonecutting: cut 1x Quench Iron Wall from Quench Iron.", "Steins\u00e4ge: 1x H\u00e4rteeisenmauer aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_quench_iron_from_quench_iron_stonecutting", "copper_inferno:polished_quench_iron", "moltenmetal/polished_quench_iron_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:polished_quench_iron", 1, "Stonecutting: cut 1x Polished Quench Iron from Quench Iron.", "Steins\u00e4ge: 1x Poliertes H\u00e4rteeisen aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_quench_iron_slab_from_quench_iron_stonecutting", "copper_inferno:polished_quench_iron_slab", "moltenmetal/polished_quench_iron_slab_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:polished_quench_iron_slab", 2, "Stonecutting: cut 2x Polished Quench Iron Slab from Quench Iron.", "Steins\u00e4ge: 2x Polierte H\u00e4rteeisenstufe aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_quench_iron_stairs_from_quench_iron_stonecutting", "copper_inferno:polished_quench_iron_stairs", "moltenmetal/polished_quench_iron_stairs_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:polished_quench_iron_stairs", 1, "Stonecutting: cut 1x Polished Quench Iron Stairs from Quench Iron.", "Steins\u00e4ge: 1x Polierte H\u00e4rteeisentreppe aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_quench_iron_wall_from_quench_iron_stonecutting", "copper_inferno:polished_quench_iron_wall", "moltenmetal/polished_quench_iron_wall_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:polished_quench_iron_wall", 1, "Stonecutting: cut 1x Polished Quench Iron Wall from Quench Iron.", "Steins\u00e4ge: 1x Polierte H\u00e4rteeisenmauer aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_bricks_from_quench_iron_stonecutting", "copper_inferno:quench_iron_bricks", "moltenmetal/quench_iron_bricks_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_bricks", 1, "Stonecutting: cut 1x Quench Iron Bricks from Quench Iron.", "Steins\u00e4ge: 1x H\u00e4rteeisenziegel aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_brick_slab_from_quench_iron_stonecutting", "copper_inferno:quench_iron_brick_slab", "moltenmetal/quench_iron_brick_slab_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_brick_slab", 2, "Stonecutting: cut 2x Quench Iron Brick Slab from Quench Iron.", "Steins\u00e4ge: 2x H\u00e4rteeisenziegelstufe aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_brick_stairs_from_quench_iron_stonecutting", "copper_inferno:quench_iron_brick_stairs", "moltenmetal/quench_iron_brick_stairs_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_brick_stairs", 1, "Stonecutting: cut 1x Quench Iron Brick Stairs from Quench Iron.", "Steins\u00e4ge: 1x H\u00e4rteeisenziegeltreppe aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_brick_wall_from_quench_iron_stonecutting", "copper_inferno:quench_iron_brick_wall", "moltenmetal/quench_iron_brick_wall_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_brick_wall", 1, "Stonecutting: cut 1x Quench Iron Brick Wall from Quench Iron.", "Steins\u00e4ge: 1x H\u00e4rteeisenziegelmauer aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_tiles_from_quench_iron_stonecutting", "copper_inferno:quench_iron_tiles", "moltenmetal/quench_iron_tiles_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_tiles", 1, "Stonecutting: cut 1x Quench Iron Tiles from Quench Iron.", "Steins\u00e4ge: 1x H\u00e4rteeisenfliesen aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_quench_iron_bricks_from_quench_iron_stonecutting", "copper_inferno:chiseled_quench_iron_bricks", "moltenmetal/chiseled_quench_iron_bricks_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:chiseled_quench_iron_bricks", 1, "Stonecutting: cut 1x Chiseled Quench Iron Bricks from Quench Iron.", "Steins\u00e4ge: 1x Gemei\u00dfelte H\u00e4rteeisenziegel aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/quench_iron_pillar_from_quench_iron_stonecutting", "copper_inferno:quench_iron_pillar", "moltenmetal/quench_iron_pillar_from_quench_iron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:quench_iron", "", "", "", ""},
				"copper_inferno:quench_iron_pillar", 1, "Stonecutting: cut 1x Quench Iron Pillar from Quench Iron.", "Steins\u00e4ge: 1x H\u00e4rteeisens\u00e4ule aus H\u00e4rteeisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome", "copper_inferno:emberchrome", "moltenmetal/emberchrome",
				new String[] {"minecraft:quartz", "", "minecraft:quartz", "", "copper_inferno:quench_iron", "", "minecraft:quartz", "", "minecraft:quartz"},
				"copper_inferno:emberchrome", 4, "Craft 4x Emberchrome at a crafting table.", "Stellt 4x Glutchrom an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_bricks", "copper_inferno:emberchrome_bricks", "moltenmetal/emberchrome_bricks",
				new String[] {"copper_inferno:emberchrome", "copper_inferno:emberchrome", "", "copper_inferno:emberchrome", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_bricks", 4, "Craft 4x Emberchrome Bricks at a crafting table.", "Stellt 4x Glutchromziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_tiles", "copper_inferno:emberchrome_tiles", "moltenmetal/emberchrome_tiles",
				new String[] {"copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "", "copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "", "", "", ""},
				"copper_inferno:emberchrome_tiles", 4, "Craft 4x Emberchrome Tiles at a crafting table.", "Stellt 4x Glutchromfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_emberchrome", "copper_inferno:polished_emberchrome", "moltenmetal/polished_emberchrome",
				new String[] {"copper_inferno:emberchrome_tiles", "copper_inferno:emberchrome_tiles", "", "copper_inferno:emberchrome_tiles", "copper_inferno:emberchrome_tiles", "", "", "", ""},
				"copper_inferno:polished_emberchrome", 4, "Craft 4x Polished Emberchrome at a crafting table.", "Stellt 4x Poliertes Glutchrom an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_slab", "copper_inferno:emberchrome_slab", "moltenmetal/emberchrome_slab",
				new String[] {"copper_inferno:emberchrome", "copper_inferno:emberchrome", "copper_inferno:emberchrome", "", "", "", "", "", ""},
				"copper_inferno:emberchrome_slab", 6, "Craft 6x Emberchrome Slab at a crafting table.", "Stellt 6x Glutchromstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_stairs", "copper_inferno:emberchrome_stairs", "moltenmetal/emberchrome_stairs",
				new String[] {"copper_inferno:emberchrome", "", "", "copper_inferno:emberchrome", "copper_inferno:emberchrome", "", "copper_inferno:emberchrome", "copper_inferno:emberchrome", "copper_inferno:emberchrome"},
				"copper_inferno:emberchrome_stairs", 4, "Craft 4x Emberchrome Stairs at a crafting table.", "Stellt 4x Glutchromtreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_wall", "copper_inferno:emberchrome_wall", "moltenmetal/emberchrome_wall",
				new String[] {"copper_inferno:emberchrome", "copper_inferno:emberchrome", "copper_inferno:emberchrome", "copper_inferno:emberchrome", "copper_inferno:emberchrome", "copper_inferno:emberchrome", "", "", ""},
				"copper_inferno:emberchrome_wall", 6, "Craft 6x Emberchrome Wall at a crafting table.", "Stellt 6x Glutchrommauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_emberchrome_slab", "copper_inferno:polished_emberchrome_slab", "moltenmetal/polished_emberchrome_slab",
				new String[] {"copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome", "", "", "", "", "", ""},
				"copper_inferno:polished_emberchrome_slab", 6, "Craft 6x Polished Emberchrome Slab at a crafting table.", "Stellt 6x Polierte Glutchromstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_emberchrome_stairs", "copper_inferno:polished_emberchrome_stairs", "moltenmetal/polished_emberchrome_stairs",
				new String[] {"copper_inferno:polished_emberchrome", "", "", "copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome", "", "copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome"},
				"copper_inferno:polished_emberchrome_stairs", 4, "Craft 4x Polished Emberchrome Stairs at a crafting table.", "Stellt 4x Polierte Glutchromtreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_emberchrome_wall", "copper_inferno:polished_emberchrome_wall", "moltenmetal/polished_emberchrome_wall",
				new String[] {"copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome", "copper_inferno:polished_emberchrome", "", "", ""},
				"copper_inferno:polished_emberchrome_wall", 6, "Craft 6x Polished Emberchrome Wall at a crafting table.", "Stellt 6x Polierte Glutchrommauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_brick_slab", "copper_inferno:emberchrome_brick_slab", "moltenmetal/emberchrome_brick_slab",
				new String[] {"copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "", "", "", "", "", ""},
				"copper_inferno:emberchrome_brick_slab", 6, "Craft 6x Emberchrome Brick Slab at a crafting table.", "Stellt 6x Glutchromziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_brick_stairs", "copper_inferno:emberchrome_brick_stairs", "moltenmetal/emberchrome_brick_stairs",
				new String[] {"copper_inferno:emberchrome_bricks", "", "", "copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "", "copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks"},
				"copper_inferno:emberchrome_brick_stairs", 4, "Craft 4x Emberchrome Brick Stairs at a crafting table.", "Stellt 4x Glutchromziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_brick_wall", "copper_inferno:emberchrome_brick_wall", "moltenmetal/emberchrome_brick_wall",
				new String[] {"copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "copper_inferno:emberchrome_bricks", "", "", ""},
				"copper_inferno:emberchrome_brick_wall", 6, "Craft 6x Emberchrome Brick Wall at a crafting table.", "Stellt 6x Glutchromziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_emberchrome_bricks", "copper_inferno:cracked_emberchrome_bricks", "moltenmetal/cracked_emberchrome_bricks",
				new String[] {"", "", "", "", "copper_inferno:emberchrome_bricks", "", "", "", ""},
				"copper_inferno:cracked_emberchrome_bricks", 1, "Smelting Emberchrome Bricks in a furnace yields Cracked Emberchrome Bricks.", "Glutchromziegel im Ofen gebrannt ergibt Rissige Glutchromziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_emberchrome_bricks", "copper_inferno:chiseled_emberchrome_bricks", "moltenmetal/chiseled_emberchrome_bricks",
				new String[] {"copper_inferno:emberchrome_brick_slab", "", "", "copper_inferno:emberchrome_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_emberchrome_bricks", 1, "Craft 1x Chiseled Emberchrome Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Glutchromziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_pillar", "copper_inferno:emberchrome_pillar", "moltenmetal/emberchrome_pillar",
				new String[] {"copper_inferno:emberchrome", "", "", "copper_inferno:emberchrome", "", "", "", "", ""},
				"copper_inferno:emberchrome_pillar", 2, "Craft 2x Emberchrome Pillar at a crafting table.", "Stellt 2x Glutchroms\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_slab_from_emberchrome_stonecutting", "copper_inferno:emberchrome_slab", "moltenmetal/emberchrome_slab_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_slab", 2, "Stonecutting: cut 2x Emberchrome Slab from Emberchrome.", "Steins\u00e4ge: 2x Glutchromstufe aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_stairs_from_emberchrome_stonecutting", "copper_inferno:emberchrome_stairs", "moltenmetal/emberchrome_stairs_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_stairs", 1, "Stonecutting: cut 1x Emberchrome Stairs from Emberchrome.", "Steins\u00e4ge: 1x Glutchromtreppe aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_wall_from_emberchrome_stonecutting", "copper_inferno:emberchrome_wall", "moltenmetal/emberchrome_wall_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_wall", 1, "Stonecutting: cut 1x Emberchrome Wall from Emberchrome.", "Steins\u00e4ge: 1x Glutchrommauer aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_emberchrome_from_emberchrome_stonecutting", "copper_inferno:polished_emberchrome", "moltenmetal/polished_emberchrome_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:polished_emberchrome", 1, "Stonecutting: cut 1x Polished Emberchrome from Emberchrome.", "Steins\u00e4ge: 1x Poliertes Glutchrom aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_emberchrome_slab_from_emberchrome_stonecutting", "copper_inferno:polished_emberchrome_slab", "moltenmetal/polished_emberchrome_slab_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:polished_emberchrome_slab", 2, "Stonecutting: cut 2x Polished Emberchrome Slab from Emberchrome.", "Steins\u00e4ge: 2x Polierte Glutchromstufe aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_emberchrome_stairs_from_emberchrome_stonecutting", "copper_inferno:polished_emberchrome_stairs", "moltenmetal/polished_emberchrome_stairs_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:polished_emberchrome_stairs", 1, "Stonecutting: cut 1x Polished Emberchrome Stairs from Emberchrome.", "Steins\u00e4ge: 1x Polierte Glutchromtreppe aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_emberchrome_wall_from_emberchrome_stonecutting", "copper_inferno:polished_emberchrome_wall", "moltenmetal/polished_emberchrome_wall_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:polished_emberchrome_wall", 1, "Stonecutting: cut 1x Polished Emberchrome Wall from Emberchrome.", "Steins\u00e4ge: 1x Polierte Glutchrommauer aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_bricks_from_emberchrome_stonecutting", "copper_inferno:emberchrome_bricks", "moltenmetal/emberchrome_bricks_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_bricks", 1, "Stonecutting: cut 1x Emberchrome Bricks from Emberchrome.", "Steins\u00e4ge: 1x Glutchromziegel aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_brick_slab_from_emberchrome_stonecutting", "copper_inferno:emberchrome_brick_slab", "moltenmetal/emberchrome_brick_slab_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_brick_slab", 2, "Stonecutting: cut 2x Emberchrome Brick Slab from Emberchrome.", "Steins\u00e4ge: 2x Glutchromziegelstufe aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_brick_stairs_from_emberchrome_stonecutting", "copper_inferno:emberchrome_brick_stairs", "moltenmetal/emberchrome_brick_stairs_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_brick_stairs", 1, "Stonecutting: cut 1x Emberchrome Brick Stairs from Emberchrome.", "Steins\u00e4ge: 1x Glutchromziegeltreppe aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_brick_wall_from_emberchrome_stonecutting", "copper_inferno:emberchrome_brick_wall", "moltenmetal/emberchrome_brick_wall_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_brick_wall", 1, "Stonecutting: cut 1x Emberchrome Brick Wall from Emberchrome.", "Steins\u00e4ge: 1x Glutchromziegelmauer aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_tiles_from_emberchrome_stonecutting", "copper_inferno:emberchrome_tiles", "moltenmetal/emberchrome_tiles_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_tiles", 1, "Stonecutting: cut 1x Emberchrome Tiles from Emberchrome.", "Steins\u00e4ge: 1x Glutchromfliesen aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_emberchrome_bricks_from_emberchrome_stonecutting", "copper_inferno:chiseled_emberchrome_bricks", "moltenmetal/chiseled_emberchrome_bricks_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:chiseled_emberchrome_bricks", 1, "Stonecutting: cut 1x Chiseled Emberchrome Bricks from Emberchrome.", "Steins\u00e4ge: 1x Gemei\u00dfelte Glutchromziegel aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/emberchrome_pillar_from_emberchrome_stonecutting", "copper_inferno:emberchrome_pillar", "moltenmetal/emberchrome_pillar_from_emberchrome_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:emberchrome", "", "", "", ""},
				"copper_inferno:emberchrome_pillar", 1, "Stonecutting: cut 1x Emberchrome Pillar from Emberchrome.", "Steins\u00e4ge: 1x Glutchroms\u00e4ule aus Glutchrom schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt", "copper_inferno:molten_cobalt", "moltenmetal/molten_cobalt",
				new String[] {"minecraft:lapis_lazuli", "", "minecraft:lapis_lazuli", "", "copper_inferno:emberchrome", "", "minecraft:lapis_lazuli", "", "minecraft:lapis_lazuli"},
				"copper_inferno:molten_cobalt", 4, "Craft 4x Molten Cobalt at a crafting table.", "Stellt 4x Schmelzkobalt an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "moltenmetal/molten_cobalt_bricks",
				new String[] {"copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "", "copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_bricks", 4, "Craft 4x Molten Cobalt Bricks at a crafting table.", "Stellt 4x Schmelzkobaltziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_tiles", "copper_inferno:molten_cobalt_tiles", "moltenmetal/molten_cobalt_tiles",
				new String[] {"copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "", "copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "", "", "", ""},
				"copper_inferno:molten_cobalt_tiles", 4, "Craft 4x Molten Cobalt Tiles at a crafting table.", "Stellt 4x Schmelzkobaltfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "moltenmetal/polished_molten_cobalt",
				new String[] {"copper_inferno:molten_cobalt_tiles", "copper_inferno:molten_cobalt_tiles", "", "copper_inferno:molten_cobalt_tiles", "copper_inferno:molten_cobalt_tiles", "", "", "", ""},
				"copper_inferno:polished_molten_cobalt", 4, "Craft 4x Polished Molten Cobalt at a crafting table.", "Stellt 4x Poliertes Schmelzkobalt an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_slab", "copper_inferno:molten_cobalt_slab", "moltenmetal/molten_cobalt_slab",
				new String[] {"copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "", "", "", "", "", ""},
				"copper_inferno:molten_cobalt_slab", 6, "Craft 6x Molten Cobalt Slab at a crafting table.", "Stellt 6x Schmelzkobaltstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_stairs", "copper_inferno:molten_cobalt_stairs", "moltenmetal/molten_cobalt_stairs",
				new String[] {"copper_inferno:molten_cobalt", "", "", "copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "", "copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt"},
				"copper_inferno:molten_cobalt_stairs", 4, "Craft 4x Molten Cobalt Stairs at a crafting table.", "Stellt 4x Schmelzkobalttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_wall", "copper_inferno:molten_cobalt_wall", "moltenmetal/molten_cobalt_wall",
				new String[] {"copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "copper_inferno:molten_cobalt", "", "", ""},
				"copper_inferno:molten_cobalt_wall", 6, "Craft 6x Molten Cobalt Wall at a crafting table.", "Stellt 6x Schmelzkobaltmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_cobalt_slab", "copper_inferno:polished_molten_cobalt_slab", "moltenmetal/polished_molten_cobalt_slab",
				new String[] {"copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "", "", "", "", "", ""},
				"copper_inferno:polished_molten_cobalt_slab", 6, "Craft 6x Polished Molten Cobalt Slab at a crafting table.", "Stellt 6x Polierte Schmelzkobaltstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_cobalt_stairs", "copper_inferno:polished_molten_cobalt_stairs", "moltenmetal/polished_molten_cobalt_stairs",
				new String[] {"copper_inferno:polished_molten_cobalt", "", "", "copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "", "copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt"},
				"copper_inferno:polished_molten_cobalt_stairs", 4, "Craft 4x Polished Molten Cobalt Stairs at a crafting table.", "Stellt 4x Polierte Schmelzkobalttreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_cobalt_wall", "copper_inferno:polished_molten_cobalt_wall", "moltenmetal/polished_molten_cobalt_wall",
				new String[] {"copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "copper_inferno:polished_molten_cobalt", "", "", ""},
				"copper_inferno:polished_molten_cobalt_wall", 6, "Craft 6x Polished Molten Cobalt Wall at a crafting table.", "Stellt 6x Polierte Schmelzkobaltmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_brick_slab", "copper_inferno:molten_cobalt_brick_slab", "moltenmetal/molten_cobalt_brick_slab",
				new String[] {"copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "", "", "", "", "", ""},
				"copper_inferno:molten_cobalt_brick_slab", 6, "Craft 6x Molten Cobalt Brick Slab at a crafting table.", "Stellt 6x Schmelzkobaltziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_brick_stairs", "copper_inferno:molten_cobalt_brick_stairs", "moltenmetal/molten_cobalt_brick_stairs",
				new String[] {"copper_inferno:molten_cobalt_bricks", "", "", "copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "", "copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks"},
				"copper_inferno:molten_cobalt_brick_stairs", 4, "Craft 4x Molten Cobalt Brick Stairs at a crafting table.", "Stellt 4x Schmelzkobaltziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_brick_wall", "copper_inferno:molten_cobalt_brick_wall", "moltenmetal/molten_cobalt_brick_wall",
				new String[] {"copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "copper_inferno:molten_cobalt_bricks", "", "", ""},
				"copper_inferno:molten_cobalt_brick_wall", 6, "Craft 6x Molten Cobalt Brick Wall at a crafting table.", "Stellt 6x Schmelzkobaltziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_molten_cobalt_bricks", "copper_inferno:cracked_molten_cobalt_bricks", "moltenmetal/cracked_molten_cobalt_bricks",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt_bricks", "", "", "", ""},
				"copper_inferno:cracked_molten_cobalt_bricks", 1, "Smelting Molten Cobalt Bricks in a furnace yields Cracked Molten Cobalt Bricks.", "Schmelzkobaltziegel im Ofen gebrannt ergibt Rissige Schmelzkobaltziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_molten_cobalt_bricks", "copper_inferno:chiseled_molten_cobalt_bricks", "moltenmetal/chiseled_molten_cobalt_bricks",
				new String[] {"copper_inferno:molten_cobalt_brick_slab", "", "", "copper_inferno:molten_cobalt_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_molten_cobalt_bricks", 1, "Craft 1x Chiseled Molten Cobalt Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Schmelzkobaltziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_pillar", "copper_inferno:molten_cobalt_pillar", "moltenmetal/molten_cobalt_pillar",
				new String[] {"copper_inferno:molten_cobalt", "", "", "copper_inferno:molten_cobalt", "", "", "", "", ""},
				"copper_inferno:molten_cobalt_pillar", 2, "Craft 2x Molten Cobalt Pillar at a crafting table.", "Stellt 2x Schmelzkobalts\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_slab_from_molten_cobalt_stonecutting", "copper_inferno:molten_cobalt_slab", "moltenmetal/molten_cobalt_slab_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_slab", 2, "Stonecutting: cut 2x Molten Cobalt Slab from Molten Cobalt.", "Steins\u00e4ge: 2x Schmelzkobaltstufe aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_stairs_from_molten_cobalt_stonecutting", "copper_inferno:molten_cobalt_stairs", "moltenmetal/molten_cobalt_stairs_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_stairs", 1, "Stonecutting: cut 1x Molten Cobalt Stairs from Molten Cobalt.", "Steins\u00e4ge: 1x Schmelzkobalttreppe aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_wall_from_molten_cobalt_stonecutting", "copper_inferno:molten_cobalt_wall", "moltenmetal/molten_cobalt_wall_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_wall", 1, "Stonecutting: cut 1x Molten Cobalt Wall from Molten Cobalt.", "Steins\u00e4ge: 1x Schmelzkobaltmauer aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_cobalt_from_molten_cobalt_stonecutting", "copper_inferno:polished_molten_cobalt", "moltenmetal/polished_molten_cobalt_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:polished_molten_cobalt", 1, "Stonecutting: cut 1x Polished Molten Cobalt from Molten Cobalt.", "Steins\u00e4ge: 1x Poliertes Schmelzkobalt aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_cobalt_slab_from_molten_cobalt_stonecutting", "copper_inferno:polished_molten_cobalt_slab", "moltenmetal/polished_molten_cobalt_slab_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:polished_molten_cobalt_slab", 2, "Stonecutting: cut 2x Polished Molten Cobalt Slab from Molten Cobalt.", "Steins\u00e4ge: 2x Polierte Schmelzkobaltstufe aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_cobalt_stairs_from_molten_cobalt_stonecutting", "copper_inferno:polished_molten_cobalt_stairs", "moltenmetal/polished_molten_cobalt_stairs_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:polished_molten_cobalt_stairs", 1, "Stonecutting: cut 1x Polished Molten Cobalt Stairs from Molten Cobalt.", "Steins\u00e4ge: 1x Polierte Schmelzkobalttreppe aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_molten_cobalt_wall_from_molten_cobalt_stonecutting", "copper_inferno:polished_molten_cobalt_wall", "moltenmetal/polished_molten_cobalt_wall_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:polished_molten_cobalt_wall", 1, "Stonecutting: cut 1x Polished Molten Cobalt Wall from Molten Cobalt.", "Steins\u00e4ge: 1x Polierte Schmelzkobaltmauer aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_bricks_from_molten_cobalt_stonecutting", "copper_inferno:molten_cobalt_bricks", "moltenmetal/molten_cobalt_bricks_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_bricks", 1, "Stonecutting: cut 1x Molten Cobalt Bricks from Molten Cobalt.", "Steins\u00e4ge: 1x Schmelzkobaltziegel aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_brick_slab_from_molten_cobalt_stonecutting", "copper_inferno:molten_cobalt_brick_slab", "moltenmetal/molten_cobalt_brick_slab_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_brick_slab", 2, "Stonecutting: cut 2x Molten Cobalt Brick Slab from Molten Cobalt.", "Steins\u00e4ge: 2x Schmelzkobaltziegelstufe aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_brick_stairs_from_molten_cobalt_stonecutting", "copper_inferno:molten_cobalt_brick_stairs", "moltenmetal/molten_cobalt_brick_stairs_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_brick_stairs", 1, "Stonecutting: cut 1x Molten Cobalt Brick Stairs from Molten Cobalt.", "Steins\u00e4ge: 1x Schmelzkobaltziegeltreppe aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_brick_wall_from_molten_cobalt_stonecutting", "copper_inferno:molten_cobalt_brick_wall", "moltenmetal/molten_cobalt_brick_wall_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_brick_wall", 1, "Stonecutting: cut 1x Molten Cobalt Brick Wall from Molten Cobalt.", "Steins\u00e4ge: 1x Schmelzkobaltziegelmauer aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_tiles_from_molten_cobalt_stonecutting", "copper_inferno:molten_cobalt_tiles", "moltenmetal/molten_cobalt_tiles_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_tiles", 1, "Stonecutting: cut 1x Molten Cobalt Tiles from Molten Cobalt.", "Steins\u00e4ge: 1x Schmelzkobaltfliesen aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_molten_cobalt_bricks_from_molten_cobalt_stonecutting", "copper_inferno:chiseled_molten_cobalt_bricks", "moltenmetal/chiseled_molten_cobalt_bricks_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:chiseled_molten_cobalt_bricks", 1, "Stonecutting: cut 1x Chiseled Molten Cobalt Bricks from Molten Cobalt.", "Steins\u00e4ge: 1x Gemei\u00dfelte Schmelzkobaltziegel aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/molten_cobalt_pillar_from_molten_cobalt_stonecutting", "copper_inferno:molten_cobalt_pillar", "moltenmetal/molten_cobalt_pillar_from_molten_cobalt_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:molten_cobalt", "", "", "", ""},
				"copper_inferno:molten_cobalt_pillar", 1, "Stonecutting: cut 1x Molten Cobalt Pillar from Molten Cobalt.", "Steins\u00e4ge: 1x Schmelzkobalts\u00e4ule aus Schmelzkobalt schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron", "copper_inferno:ashiron", "moltenmetal/ashiron",
				new String[] {"minecraft:charcoal", "", "minecraft:charcoal", "", "copper_inferno:molten_cobalt", "", "minecraft:charcoal", "", "minecraft:charcoal"},
				"copper_inferno:ashiron", 4, "Craft 4x Ashiron at a crafting table.", "Stellt 4x Ascheneisen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_bricks", "copper_inferno:ashiron_bricks", "moltenmetal/ashiron_bricks",
				new String[] {"copper_inferno:ashiron", "copper_inferno:ashiron", "", "copper_inferno:ashiron", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_bricks", 4, "Craft 4x Ashiron Bricks at a crafting table.", "Stellt 4x Ascheneisenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_tiles", "copper_inferno:ashiron_tiles", "moltenmetal/ashiron_tiles",
				new String[] {"copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "", "copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "", "", "", ""},
				"copper_inferno:ashiron_tiles", 4, "Craft 4x Ashiron Tiles at a crafting table.", "Stellt 4x Ascheneisenfliesen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ashiron", "copper_inferno:polished_ashiron", "moltenmetal/polished_ashiron",
				new String[] {"copper_inferno:ashiron_tiles", "copper_inferno:ashiron_tiles", "", "copper_inferno:ashiron_tiles", "copper_inferno:ashiron_tiles", "", "", "", ""},
				"copper_inferno:polished_ashiron", 4, "Craft 4x Polished Ashiron at a crafting table.", "Stellt 4x Poliertes Ascheneisen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_slab", "copper_inferno:ashiron_slab", "moltenmetal/ashiron_slab",
				new String[] {"copper_inferno:ashiron", "copper_inferno:ashiron", "copper_inferno:ashiron", "", "", "", "", "", ""},
				"copper_inferno:ashiron_slab", 6, "Craft 6x Ashiron Slab at a crafting table.", "Stellt 6x Ascheneisenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_stairs", "copper_inferno:ashiron_stairs", "moltenmetal/ashiron_stairs",
				new String[] {"copper_inferno:ashiron", "", "", "copper_inferno:ashiron", "copper_inferno:ashiron", "", "copper_inferno:ashiron", "copper_inferno:ashiron", "copper_inferno:ashiron"},
				"copper_inferno:ashiron_stairs", 4, "Craft 4x Ashiron Stairs at a crafting table.", "Stellt 4x Ascheneisentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_wall", "copper_inferno:ashiron_wall", "moltenmetal/ashiron_wall",
				new String[] {"copper_inferno:ashiron", "copper_inferno:ashiron", "copper_inferno:ashiron", "copper_inferno:ashiron", "copper_inferno:ashiron", "copper_inferno:ashiron", "", "", ""},
				"copper_inferno:ashiron_wall", 6, "Craft 6x Ashiron Wall at a crafting table.", "Stellt 6x Ascheneisenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ashiron_slab", "copper_inferno:polished_ashiron_slab", "moltenmetal/polished_ashiron_slab",
				new String[] {"copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron", "", "", "", "", "", ""},
				"copper_inferno:polished_ashiron_slab", 6, "Craft 6x Polished Ashiron Slab at a crafting table.", "Stellt 6x Polierte Ascheneisenstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ashiron_stairs", "copper_inferno:polished_ashiron_stairs", "moltenmetal/polished_ashiron_stairs",
				new String[] {"copper_inferno:polished_ashiron", "", "", "copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron", "", "copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron"},
				"copper_inferno:polished_ashiron_stairs", 4, "Craft 4x Polished Ashiron Stairs at a crafting table.", "Stellt 4x Polierte Ascheneisentreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ashiron_wall", "copper_inferno:polished_ashiron_wall", "moltenmetal/polished_ashiron_wall",
				new String[] {"copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron", "copper_inferno:polished_ashiron", "", "", ""},
				"copper_inferno:polished_ashiron_wall", 6, "Craft 6x Polished Ashiron Wall at a crafting table.", "Stellt 6x Polierte Ascheneisenmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_brick_slab", "copper_inferno:ashiron_brick_slab", "moltenmetal/ashiron_brick_slab",
				new String[] {"copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "", "", "", "", "", ""},
				"copper_inferno:ashiron_brick_slab", 6, "Craft 6x Ashiron Brick Slab at a crafting table.", "Stellt 6x Ascheneisenziegelstufe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_brick_stairs", "copper_inferno:ashiron_brick_stairs", "moltenmetal/ashiron_brick_stairs",
				new String[] {"copper_inferno:ashiron_bricks", "", "", "copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "", "copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks"},
				"copper_inferno:ashiron_brick_stairs", 4, "Craft 4x Ashiron Brick Stairs at a crafting table.", "Stellt 4x Ascheneisenziegeltreppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_brick_wall", "copper_inferno:ashiron_brick_wall", "moltenmetal/ashiron_brick_wall",
				new String[] {"copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "copper_inferno:ashiron_bricks", "", "", ""},
				"copper_inferno:ashiron_brick_wall", 6, "Craft 6x Ashiron Brick Wall at a crafting table.", "Stellt 6x Ascheneisenziegelmauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/cracked_ashiron_bricks", "copper_inferno:cracked_ashiron_bricks", "moltenmetal/cracked_ashiron_bricks",
				new String[] {"", "", "", "", "copper_inferno:ashiron_bricks", "", "", "", ""},
				"copper_inferno:cracked_ashiron_bricks", 1, "Smelting Ashiron Bricks in a furnace yields Cracked Ashiron Bricks.", "Ascheneisenziegel im Ofen gebrannt ergibt Rissige Ascheneisenziegel."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_ashiron_bricks", "copper_inferno:chiseled_ashiron_bricks", "moltenmetal/chiseled_ashiron_bricks",
				new String[] {"copper_inferno:ashiron_brick_slab", "", "", "copper_inferno:ashiron_brick_slab", "", "", "", "", ""},
				"copper_inferno:chiseled_ashiron_bricks", 1, "Craft 1x Chiseled Ashiron Bricks at a crafting table.", "Stellt 1x Gemei\u00dfelte Ascheneisenziegel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_pillar", "copper_inferno:ashiron_pillar", "moltenmetal/ashiron_pillar",
				new String[] {"copper_inferno:ashiron", "", "", "copper_inferno:ashiron", "", "", "", "", ""},
				"copper_inferno:ashiron_pillar", 2, "Craft 2x Ashiron Pillar at a crafting table.", "Stellt 2x Ascheneisens\u00e4ule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_slab_from_ashiron_stonecutting", "copper_inferno:ashiron_slab", "moltenmetal/ashiron_slab_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_slab", 2, "Stonecutting: cut 2x Ashiron Slab from Ashiron.", "Steins\u00e4ge: 2x Ascheneisenstufe aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_stairs_from_ashiron_stonecutting", "copper_inferno:ashiron_stairs", "moltenmetal/ashiron_stairs_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_stairs", 1, "Stonecutting: cut 1x Ashiron Stairs from Ashiron.", "Steins\u00e4ge: 1x Ascheneisentreppe aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_wall_from_ashiron_stonecutting", "copper_inferno:ashiron_wall", "moltenmetal/ashiron_wall_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_wall", 1, "Stonecutting: cut 1x Ashiron Wall from Ashiron.", "Steins\u00e4ge: 1x Ascheneisenmauer aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ashiron_from_ashiron_stonecutting", "copper_inferno:polished_ashiron", "moltenmetal/polished_ashiron_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:polished_ashiron", 1, "Stonecutting: cut 1x Polished Ashiron from Ashiron.", "Steins\u00e4ge: 1x Poliertes Ascheneisen aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ashiron_slab_from_ashiron_stonecutting", "copper_inferno:polished_ashiron_slab", "moltenmetal/polished_ashiron_slab_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:polished_ashiron_slab", 2, "Stonecutting: cut 2x Polished Ashiron Slab from Ashiron.", "Steins\u00e4ge: 2x Polierte Ascheneisenstufe aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ashiron_stairs_from_ashiron_stonecutting", "copper_inferno:polished_ashiron_stairs", "moltenmetal/polished_ashiron_stairs_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:polished_ashiron_stairs", 1, "Stonecutting: cut 1x Polished Ashiron Stairs from Ashiron.", "Steins\u00e4ge: 1x Polierte Ascheneisentreppe aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/polished_ashiron_wall_from_ashiron_stonecutting", "copper_inferno:polished_ashiron_wall", "moltenmetal/polished_ashiron_wall_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:polished_ashiron_wall", 1, "Stonecutting: cut 1x Polished Ashiron Wall from Ashiron.", "Steins\u00e4ge: 1x Polierte Ascheneisenmauer aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_bricks_from_ashiron_stonecutting", "copper_inferno:ashiron_bricks", "moltenmetal/ashiron_bricks_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_bricks", 1, "Stonecutting: cut 1x Ashiron Bricks from Ashiron.", "Steins\u00e4ge: 1x Ascheneisenziegel aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_brick_slab_from_ashiron_stonecutting", "copper_inferno:ashiron_brick_slab", "moltenmetal/ashiron_brick_slab_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_brick_slab", 2, "Stonecutting: cut 2x Ashiron Brick Slab from Ashiron.", "Steins\u00e4ge: 2x Ascheneisenziegelstufe aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_brick_stairs_from_ashiron_stonecutting", "copper_inferno:ashiron_brick_stairs", "moltenmetal/ashiron_brick_stairs_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_brick_stairs", 1, "Stonecutting: cut 1x Ashiron Brick Stairs from Ashiron.", "Steins\u00e4ge: 1x Ascheneisenziegeltreppe aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_brick_wall_from_ashiron_stonecutting", "copper_inferno:ashiron_brick_wall", "moltenmetal/ashiron_brick_wall_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_brick_wall", 1, "Stonecutting: cut 1x Ashiron Brick Wall from Ashiron.", "Steins\u00e4ge: 1x Ascheneisenziegelmauer aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_tiles_from_ashiron_stonecutting", "copper_inferno:ashiron_tiles", "moltenmetal/ashiron_tiles_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_tiles", 1, "Stonecutting: cut 1x Ashiron Tiles from Ashiron.", "Steins\u00e4ge: 1x Ascheneisenfliesen aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/chiseled_ashiron_bricks_from_ashiron_stonecutting", "copper_inferno:chiseled_ashiron_bricks", "moltenmetal/chiseled_ashiron_bricks_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:chiseled_ashiron_bricks", 1, "Stonecutting: cut 1x Chiseled Ashiron Bricks from Ashiron.", "Steins\u00e4ge: 1x Gemei\u00dfelte Ascheneisenziegel aus Ascheneisen schneiden."));

		HandbookEntries.add(new HandbookEntry("blocks", "moltenmetal/ashiron_pillar_from_ashiron_stonecutting", "copper_inferno:ashiron_pillar", "moltenmetal/ashiron_pillar_from_ashiron_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:ashiron", "", "", "", ""},
				"copper_inferno:ashiron_pillar", 1, "Stonecutting: cut 1x Ashiron Pillar from Ashiron.", "Steins\u00e4ge: 1x Ascheneisens\u00e4ule aus Ascheneisen schneiden."));
	}
}
