package net.sonic0810.copperinferno.feature.depthstone;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Depthstone palettes: one "blocks" entry per palette documenting the
 * palette's base-block recipe (2x2: 3x Cobbled Deepslate + the palette-unique vanilla item),
 * from which the whole 20-block palette is crafted/stonecut. Entry texts and grids mirror the
 * recipe JSONs emitted by {@code devtools/gen/depthstone_gen.py}; keep the
 * {@code new HandbookEntry(...)} literals inline (parsed positionally by tooling).
 */
final class DepthStoneHandbook {
	private DepthStoneHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/voidstone", "copper_inferno:voidstone", "depthstone/voidstone",
				new String[] {"minecraft:obsidian", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:voidstone", 4, "Craft 4x Voidstone from Cobbled Deepslate and Obsidian; the whole 20-block Voidstone palette builds from it.", "Stellt 4x Leerenstein aus Bruchtiefenschiefer und Obsidian her; die gesamte Leerenstein-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/duskshale", "copper_inferno:duskshale", "depthstone/duskshale",
				new String[] {"minecraft:deepslate", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:duskshale", 4, "Craft 4x Duskshale from Cobbled Deepslate and Deepslate; the whole 20-block Duskshale palette builds from it.", "Stellt 4x D\u00e4mmerschiefer aus Bruchtiefenschiefer und Tiefenschiefer her; die gesamte D\u00e4mmerschiefer-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/pyroclast", "copper_inferno:pyroclast", "depthstone/pyroclast",
				new String[] {"minecraft:magma_block", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:pyroclast", 4, "Craft 4x Pyroclast from Cobbled Deepslate and a Magma Block; the whole 20-block Pyroclast palette builds from it.", "Stellt 4x Pyroklastit aus Bruchtiefenschiefer und einem Magmablock her; die gesamte Pyroklastit-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/cindermarl", "copper_inferno:cindermarl", "depthstone/cindermarl",
				new String[] {"minecraft:charcoal", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:cindermarl", 4, "Craft 4x Cindermarl from Cobbled Deepslate and Charcoal; the whole 20-block Cindermarl palette builds from it.", "Stellt 4x Zundermergel aus Bruchtiefenschiefer und Holzkohle her; die gesamte Zundermergel-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/fumarolite", "copper_inferno:fumarolite", "depthstone/fumarolite",
				new String[] {"minecraft:basalt", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:fumarolite", 4, "Craft 4x Fumarolite from Cobbled Deepslate and Basalt; the whole 20-block Fumarolite palette builds from it.", "Stellt 4x Fumarolith aus Bruchtiefenschiefer und Basalt her; die gesamte Fumarolith-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/scorchslate", "copper_inferno:scorchslate", "depthstone/scorchslate",
				new String[] {"minecraft:blackstone", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:scorchslate", 4, "Craft 4x Scorchslate from Cobbled Deepslate and Blackstone; the whole 20-block Scorchslate palette builds from it.", "Stellt 4x Sengschiefer aus Bruchtiefenschiefer und Schwarzstein her; die gesamte Sengschiefer-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/emberchert", "copper_inferno:emberchert", "depthstone/emberchert",
				new String[] {"minecraft:flint", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:emberchert", 4, "Craft 4x Emberchert from Cobbled Deepslate and Flint; the whole 20-block Emberchert palette builds from it.", "Stellt 4x Gluthornstein aus Bruchtiefenschiefer und Feuerstein her; die gesamte Gluthornstein-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/slagbasalt", "copper_inferno:slagbasalt", "depthstone/slagbasalt",
				new String[] {"minecraft:smooth_basalt", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:slagbasalt", 4, "Craft 4x Slagbasalt from Cobbled Deepslate and Smooth Basalt; the whole 20-block Slagbasalt palette builds from it.", "Stellt 4x Schlackenbasalt aus Bruchtiefenschiefer und glattem Basalt her; die gesamte Schlackenbasalt-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/ashflint", "copper_inferno:ashflint", "depthstone/ashflint",
				new String[] {"minecraft:gravel", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:ashflint", 4, "Craft 4x Ashflint from Cobbled Deepslate and Gravel; the whole 20-block Ashflint palette builds from it.", "Stellt 4x Aschenfeuerstein aus Bruchtiefenschiefer und Kies her; die gesamte Aschenfeuerstein-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/charwacke", "copper_inferno:charwacke", "depthstone/charwacke",
				new String[] {"minecraft:coal", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:charwacke", 4, "Craft 4x Charwacke from Cobbled Deepslate and Coal; the whole 20-block Charwacke palette builds from it.", "Stellt 4x Kohlegrauwacke aus Bruchtiefenschiefer und Kohle her; die gesamte Kohlegrauwacke-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/smokestone", "copper_inferno:smokestone", "depthstone/smokestone",
				new String[] {"minecraft:tuff", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:smokestone", 4, "Craft 4x Smokestone from Cobbled Deepslate and Tuff; the whole 20-block Smokestone palette builds from it.", "Stellt 4x Rauchstein aus Bruchtiefenschiefer und Tuffstein her; die gesamte Rauchstein-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/kilnrock", "copper_inferno:kilnrock", "depthstone/kilnrock",
				new String[] {"minecraft:brick", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:kilnrock", 4, "Craft 4x Kilnrock from Cobbled Deepslate and a Brick; the whole 20-block Kilnrock palette builds from it.", "Stellt 4x Brennofenstein aus Bruchtiefenschiefer und einem Ziegel her; die gesamte Brennofenstein-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/magmarl", "copper_inferno:magmarl", "depthstone/magmarl",
				new String[] {"minecraft:netherrack", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:magmarl", 4, "Craft 4x Magmarl from Cobbled Deepslate and Netherrack; the whole 20-block Magmarl palette builds from it.", "Stellt 4x Magmamergel aus Bruchtiefenschiefer und Netherrack her; die gesamte Magmamergel-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/sootstone", "copper_inferno:sootstone", "depthstone/sootstone",
				new String[] {"minecraft:soul_sand", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:sootstone", 4, "Craft 4x Sootstone from Cobbled Deepslate and Soul Sand; the whole 20-block Sootstone palette builds from it.", "Stellt 4x Ru\u00dfstein aus Bruchtiefenschiefer und Seelensand her; die gesamte Ru\u00dfstein-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/vitricite", "copper_inferno:vitricite", "depthstone/vitricite",
				new String[] {"minecraft:amethyst_shard", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:vitricite", 4, "Craft 4x Vitricite from Cobbled Deepslate and an Amethyst Shard; the whole 20-block Vitricite palette builds from it.", "Stellt 4x Vitrizit aus Bruchtiefenschiefer und einer Amethystscherbe her; die gesamte Vitrizit-Palette aus 20 Bl\u00f6cken baut darauf auf."));

		HandbookEntries.add(new HandbookEntry("blocks", "depthstone/coalspar", "copper_inferno:coalspar", "depthstone/coalspar",
				new String[] {"minecraft:coal_block", "minecraft:cobbled_deepslate", "", "minecraft:cobbled_deepslate", "minecraft:cobbled_deepslate", "", "", "", ""},
				"copper_inferno:coalspar", 4, "Craft 4x Coalspar from Cobbled Deepslate and a Block of Coal; the whole 20-block Coalspar palette builds from it.", "Stellt 4x Kohlenspat aus Bruchtiefenschiefer und einem Kohleblock her; die gesamte Kohlenspat-Palette aus 20 Bl\u00f6cken baut darauf auf."));
	}
}
