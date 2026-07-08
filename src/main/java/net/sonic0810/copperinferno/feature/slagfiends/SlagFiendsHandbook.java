package net.sonic0810.copperinferno.feature.slagfiends;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Slag Fiends: one "mobs" overview per mob plus one recipe
 * page for every JSON under {@code data/copper_inferno/recipe/slagfiends/}. Entry
 * texts and grids mirror the recipe JSONs emitted by
 * {@code devtools/gen/slagfiends_gen.py}; {@code devtools/check_handbook.py} parses
 * the inline {@code new HandbookEntry(...)} literals positionally, so keep them
 * inline.
 */
final class SlagFiendsHandbook {
	private SlagFiendsHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("mobs", "slag_wraith", "copper_inferno:slag_wraith_spawn_egg", null,
				null,
				null, 0, "Slag Wraith - a slag-crusted wither skeleton spirit haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Slagbone Shard.", "Schlackengeist - ein schlackenverkrusteter Witherskelett-Geist, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Schlackenknochen-Splitter fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_reaper", "copper_inferno:cinder_reaper_spawn_egg", null,
				null,
				null, 0, "Cinder Reaper - a swift, scythe-armed skeletal reaper haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Reaper Cinder.", "Zunderschnitter - ein flinker, sensenbewehrter Skelett-Schnitter, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Schnitter-Zunder fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ashbone_knight", "copper_inferno:ashbone_knight_spawn_egg", null,
				null,
				null, 0, "Ashbone Knight - an armored skeletal knight of ash-bleached bone haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Ashbone Chip.", "Aschenknochen-Ritter - ein gepanzerter Skelett-Ritter aus aschgebleichtem Knochen, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Aschenknochen-Span fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_revenant", "copper_inferno:ember_revenant_spawn_egg", null,
				null,
				null, 0, "Ember Revenant - a smouldering revenant that refuses to stay slain haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Revenant Ember.", "Glutwiederg\u00e4nger - ein schwelender Wiederg\u00e4nger, der sich nicht bezwingen l\u00e4sst, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Wiederg\u00e4nger-Glut fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "sootbone_harrower", "copper_inferno:sootbone_harrower_spawn_egg", null,
				null,
				null, 0, "Sootbone Harrower - a stooped, soot-black harrower of the ashfields haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Harrower Soot.", "Ru\u00dfknochen-Peiniger - ein gebeugter, ru\u00dfschwarzer Peiniger der Aschefelder, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Peinigerru\u00df fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "molten_hoglin", "copper_inferno:molten_hoglin_spawn_egg", null,
				null,
				null, 0, "Molten Hoglin - a hulking hoglin dripping molten slag haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Molten Hoglin Tusk.", "Schmelzhoglin - ein massiger Hoglin, von dem geschmolzene Schlacke trieft, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Schmelzhoglin-Hauer fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinderhide_boar", "copper_inferno:cinderhide_boar_spawn_egg", null,
				null,
				null, 0, "Cinderhide Boar - a fast, cinder-pelted boar haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Cinderhide Scrap.", "Zunderfell-Keiler - ein schneller Keiler mit Zunderfell, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Zunderfell-Fetzen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slagtusk_brute", "copper_inferno:slagtusk_brute_spawn_egg", null,
				null,
				null, 0, "Slagtusk Brute - a slab-shouldered brute with tusks of hardened slag haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Slagtusk Fragment.", "Schlackenhauer-Rohling - ein breitschultriger Rohling mit Hauern aus geh\u00e4rteter Schlacke, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Schlackenhauer-Bruchst\u00fcck fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "emberback_hog", "copper_inferno:emberback_hog_spawn_egg", null,
				null,
				null, 0, "Emberback Hog - a hog whose bristled back glows with embers haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Emberback Bristle.", "Glutr\u00fccken-Eber - ein Eber, dessen borstiger R\u00fccken vor Glut gl\u00fcht, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Glutr\u00fccken-Borste fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ashsnout_gorer", "copper_inferno:ashsnout_gorer_spawn_egg", null,
				null,
				null, 0, "Ashsnout Gorer - an ill-tempered gorer with an ash-grey snout haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Ashsnout Hide.", "Aschenschnauzen-Spie\u00dfer - ein reizbarer Spie\u00dfer mit aschgrauer Schnauze, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Aschenschnauzen-Haut fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_piglin", "copper_inferno:cinder_piglin_spawn_egg", null,
				null,
				null, 0, "Cinder Piglin - a cinder-dusted piglin warrior haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Cinder Tooth.", "Zunderpiglin - ein zunderbest\u00e4ubter Piglin-Krieger, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Zunderzahn fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slagforged_piglin", "copper_inferno:slagforged_piglin_spawn_egg", null,
				null,
				null, 0, "Slagforged Piglin - a piglin clad in crude slag-forged plates haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Slagforged Plate.", "Schlackengeschmiedeter Piglin - ein Piglin in grob schlackengeschmiedeten Platten, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Schlackengeschmiedete Platte fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "emberclad_piglin", "copper_inferno:emberclad_piglin_spawn_egg", null,
				null,
				null, 0, "Emberclad Piglin - a piglin armored in glowing ember shards haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Emberclad Scrap.", "Glutgepanzerter Piglin - ein in gl\u00fchende Glutsplitter gepanzerter Piglin, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Glutpanzer-Fetzen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ashen_piglin_marauder", "copper_inferno:ashen_piglin_marauder_spawn_egg", null,
				null,
				null, 0, "Ashen Piglin Marauder - a hard-hitting marauder scouring the ashfields for loot haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Marauder Tusk.", "Aschen-Piglin-Marodeur - ein hart zuschlagender Marodeur auf Beutezug durch die Aschefelder, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Marodeurshauer fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_piglin_plunderer", "copper_inferno:soot_piglin_plunderer_spawn_egg", null,
				null,
				null, 0, "Soot Piglin Plunderer - a quick soot-smeared plunderer haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Plunderer Soot.", "Ru\u00df-Piglin-Pl\u00fcnderer - ein flinker, ru\u00dfverschmierter Pl\u00fcnderer, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Pl\u00fcnderer-Ru\u00df fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_stalker", "copper_inferno:ember_stalker_spawn_egg", null,
				null,
				null, 0, "Ember Stalker - a tall stalker wreathed in drifting embers haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Stalker Pearl.", "Glutpirscher - ein hochgewachsener Pirscher, umweht von treibender Glut, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Pirscherperle fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_shade", "copper_inferno:cinder_shade_spawn_egg", null,
				null,
				null, 0, "Cinder Shade - a flickering shade of cinder and smoke haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Shade Essence.", "Zunderschatten - ein flackernder Schatten aus Zunder und Rauch, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Schattenessenz fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slagveil_lurker", "copper_inferno:slagveil_lurker_spawn_egg", null,
				null,
				null, 0, "Slagveil Lurker - a lurker hidden behind a shimmering veil of slag haze haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Lurker Veil.", "Schlackenschleier-Lauerer - ein Lauerer, verborgen hinter einem flimmernden Schleier aus Schlackendunst, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Lauererschleier fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ashgaze_warper", "copper_inferno:ashgaze_warper_spawn_egg", null,
				null,
				null, 0, "Ashgaze Warper - a warper whose ash-grey gaze bends space haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Warper Iris.", "Aschenblick-Wandler - ein Wandler, dessen aschgrauer Blick den Raum kr\u00fcmmt, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Wandleriris fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "sootflare_haunt", "copper_inferno:sootflare_haunt_spawn_egg", null,
				null,
				null, 0, "Sootflare Haunt - a haunt trailing sparks of burning soot haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Haunt Flare.", "Ru\u00dfflammen-Spuk - ein Spuk, der Funken brennenden Ru\u00dfes hinter sich herzieht, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Spukleuchte fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ashen_zombie_piglin", "copper_inferno:ashen_zombie_piglin_spawn_egg", null,
				null,
				null, 0, "Ashen Zombie Piglin - a zombified piglin bleached grey by falling ash haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Charred Flesh.", "Aschen-Zombiepiglin - ein zombifizierter Piglin, grau gebleicht vom Aschefall, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Verkohltes Fleisch fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slagrot_piglin", "copper_inferno:slagrot_piglin_spawn_egg", null,
				null,
				null, 0, "Slagrot Piglin - a rotting piglin fused with cooling slag haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Slagrot Chunk.", "Schlackenmoder-Piglin - ein modernder Piglin, verwachsen mit erkaltender Schlacke, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Schlackenmoder-Brocken fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "emberflesh_piglin", "copper_inferno:emberflesh_piglin_spawn_egg", null,
				null,
				null, 0, "Emberflesh Piglin - a piglin whose dead flesh smoulders from within haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Emberflesh Strip.", "Glutfleisch-Piglin - ein Piglin, dessen totes Fleisch von innen schwelt, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Glutfleisch-Streifen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinderbound_piglin", "copper_inferno:cinderbound_piglin_spawn_egg", null,
				null,
				null, 0, "Cinderbound Piglin - a shackled piglin bound in cinder-caked irons haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Cinderbound Shackle.", "Zundergebundener Piglin - ein gefesselter Piglin in zunderverkrusteten Eisen, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Zunderfessel fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "sootrot_marauder", "copper_inferno:sootrot_marauder_spawn_egg", null,
				null,
				null, 0, "Sootrot Marauder - a hulking undead marauder caked in wet soot haunting the Cinder Wastes, Ember Grove and Slag Sea. Hostile. Drops Sootrot Hide.", "Ru\u00dfmoder-Marodeur - ein massiger untoter Marodeur, verklebt mit nassem Ru\u00df, heimisch in der Zunder\u00f6de, im Gluthain und im Schlackenmeer. Feindselig. L\u00e4sst Ru\u00dfmoder-Haut fallen."));

		HandbookEntries.add(new HandbookEntry("items", "bone_meal_from_slagbone_shard", "copper_inferno:slagbone_shard", "slagfiends/bone_meal_from_slagbone_shard",
				new String[] {"copper_inferno:slagbone_shard", "", "", "", "", "", "", "", ""},
				"minecraft:bone_meal", 3, "Craft 3x Bone Meal from Slagbone Shard at a crafting table.", "Stellt 3x Knochenmehl aus Schlackenknochen-Splitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "inferno_powder_from_reaper_cinder", "copper_inferno:reaper_cinder", "slagfiends/inferno_powder_from_reaper_cinder",
				new String[] {"copper_inferno:reaper_cinder", "copper_inferno:copper_dust", "", "", "", "", "", "", ""},
				"copper_inferno:inferno_powder", 2, "Craft 2x Inferno Powder from Reaper Cinder + Copper Dust at a crafting table.", "Stellt 2x Infernopulver aus Schnitter-Zunder + Kupferstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "bone_from_ashbone_chip", "copper_inferno:ashbone_chip", "slagfiends/bone_from_ashbone_chip",
				new String[] {"copper_inferno:ashbone_chip", "copper_inferno:ashbone_chip", "", "", "", "", "", "", ""},
				"minecraft:bone", 1, "Craft 1x Bone from Ashbone Chip + Ashbone Chip at a crafting table.", "Stellt 1x Knochen aus Aschenknochen-Span + Aschenknochen-Span an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "blaze_powder_from_revenant_ember", "copper_inferno:revenant_ember", "slagfiends/blaze_powder_from_revenant_ember",
				new String[] {"copper_inferno:revenant_ember", "", "", "", "", "", "", "", ""},
				"minecraft:blaze_powder", 2, "Craft 2x Blaze Powder from Revenant Ember at a crafting table.", "Stellt 2x Lohenstaub aus Wiederg\u00e4nger-Glut an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "black_dye_from_harrower_soot", "copper_inferno:harrower_soot", "slagfiends/black_dye_from_harrower_soot",
				new String[] {"copper_inferno:harrower_soot", "", "", "", "", "", "", "", ""},
				"minecraft:black_dye", 2, "Craft 2x Black Dye from Harrower Soot at a crafting table.", "Stellt 2x Schwarzer Farbstoff aus Peinigerru\u00df an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "gold_nugget_from_molten_hoglin_tusk", "copper_inferno:molten_hoglin_tusk", "slagfiends/gold_nugget_from_molten_hoglin_tusk",
				new String[] {"copper_inferno:molten_hoglin_tusk", "", "", "", "", "", "", "", ""},
				"minecraft:gold_nugget", 3, "Craft 3x Gold Nugget from Molten Hoglin Tusk at a crafting table.", "Stellt 3x Goldklumpen aus Schmelzhoglin-Hauer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "leather_from_cinderhide_scrap", "copper_inferno:cinderhide_scrap", "slagfiends/leather_from_cinderhide_scrap",
				new String[] {"copper_inferno:cinderhide_scrap", "copper_inferno:cinderhide_scrap", "", "", "", "", "", "", ""},
				"minecraft:leather", 1, "Craft 1x Leather from Cinderhide Scrap + Cinderhide Scrap at a crafting table.", "Stellt 1x Leder aus Zunderfell-Fetzen + Zunderfell-Fetzen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "orange_dye_from_slagtusk_fragment", "copper_inferno:slagtusk_fragment", "slagfiends/orange_dye_from_slagtusk_fragment",
				new String[] {"copper_inferno:slagtusk_fragment", "", "", "", "", "", "", "", ""},
				"minecraft:orange_dye", 2, "Craft 2x Orange Dye from Slagtusk Fragment at a crafting table.", "Stellt 2x Oranger Farbstoff aus Schlackenhauer-Bruchst\u00fcck an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "string_from_emberback_bristle", "copper_inferno:emberback_bristle", "slagfiends/string_from_emberback_bristle",
				new String[] {"copper_inferno:emberback_bristle", "", "", "", "", "", "", "", ""},
				"minecraft:string", 2, "Craft 2x String from Emberback Bristle at a crafting table.", "Stellt 2x Faden aus Glutr\u00fccken-Borste an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "leather_from_ashsnout_hide", "copper_inferno:ashsnout_hide", "slagfiends/leather_from_ashsnout_hide",
				new String[] {"copper_inferno:ashsnout_hide", "copper_inferno:ashsnout_hide", "", "", "", "", "", "", ""},
				"minecraft:leather", 2, "Craft 2x Leather from Ashsnout Hide + Ashsnout Hide at a crafting table.", "Stellt 2x Leder aus Aschenschnauzen-Haut + Aschenschnauzen-Haut an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "bone_meal_from_cinder_tooth", "copper_inferno:cinder_tooth", "slagfiends/bone_meal_from_cinder_tooth",
				new String[] {"copper_inferno:cinder_tooth", "", "", "", "", "", "", "", ""},
				"minecraft:bone_meal", 2, "Craft 2x Bone Meal from Cinder Tooth at a crafting table.", "Stellt 2x Knochenmehl aus Zunderzahn an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "iron_nugget_from_slagforged_plate", "copper_inferno:slagforged_plate", "slagfiends/iron_nugget_from_slagforged_plate",
				new String[] {"copper_inferno:slagforged_plate", "", "", "", "", "", "", "", ""},
				"minecraft:iron_nugget", 3, "Craft 3x Iron Nugget from Slagforged Plate at a crafting table.", "Stellt 3x Eisenklumpen aus Schlackengeschmiedete Platte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "gold_nugget_from_emberclad_scrap", "copper_inferno:emberclad_scrap", "slagfiends/gold_nugget_from_emberclad_scrap",
				new String[] {"copper_inferno:emberclad_scrap", "", "", "", "", "", "", "", ""},
				"minecraft:gold_nugget", 2, "Craft 2x Gold Nugget from Emberclad Scrap at a crafting table.", "Stellt 2x Goldklumpen aus Glutpanzer-Fetzen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "inferno_powder_from_marauder_tusk", "copper_inferno:marauder_tusk", "slagfiends/inferno_powder_from_marauder_tusk",
				new String[] {"copper_inferno:marauder_tusk", "copper_inferno:copper_dust", "", "", "", "", "", "", ""},
				"copper_inferno:inferno_powder", 2, "Craft 2x Inferno Powder from Marauder Tusk + Copper Dust at a crafting table.", "Stellt 2x Infernopulver aus Marodeurshauer + Kupferstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "gunpowder_from_plunderer_soot", "copper_inferno:plunderer_soot", "slagfiends/gunpowder_from_plunderer_soot",
				new String[] {"copper_inferno:plunderer_soot", "", "", "", "", "", "", "", ""},
				"minecraft:gunpowder", 1, "Craft 1x Gunpowder from Plunderer Soot at a crafting table.", "Stellt 1x Schwarzpulver aus Pl\u00fcnderer-Ru\u00df an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "ender_pearl_from_stalker_pearl", "copper_inferno:stalker_pearl", "slagfiends/ender_pearl_from_stalker_pearl",
				new String[] {"copper_inferno:stalker_pearl", "", "", "", "", "", "", "", ""},
				"minecraft:ender_pearl", 1, "Craft 1x Ender Pearl from Stalker Pearl at a crafting table.", "Stellt 1x Enderperle aus Pirscherperle an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "black_dye_from_shade_essence", "copper_inferno:shade_essence", "slagfiends/black_dye_from_shade_essence",
				new String[] {"copper_inferno:shade_essence", "", "", "", "", "", "", "", ""},
				"minecraft:black_dye", 3, "Craft 3x Black Dye from Shade Essence at a crafting table.", "Stellt 3x Schwarzer Farbstoff aus Schattenessenz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "phantom_membrane_from_lurker_veil", "copper_inferno:lurker_veil", "slagfiends/phantom_membrane_from_lurker_veil",
				new String[] {"copper_inferno:lurker_veil", "", "", "", "", "", "", "", ""},
				"minecraft:phantom_membrane", 1, "Craft 1x Phantom Membrane from Lurker Veil at a crafting table.", "Stellt 1x Phantomhaut aus Lauererschleier an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "ender_eye_from_warper_iris", "copper_inferno:warper_iris", "slagfiends/ender_eye_from_warper_iris",
				new String[] {"copper_inferno:warper_iris", "", "", "", "", "", "", "", ""},
				"minecraft:ender_eye", 1, "Craft 1x Eye of Ender from Warper Iris at a crafting table.", "Stellt 1x Enderauge aus Wandleriris an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "glowstone_dust_from_haunt_flare", "copper_inferno:haunt_flare", "slagfiends/glowstone_dust_from_haunt_flare",
				new String[] {"copper_inferno:haunt_flare", "", "", "", "", "", "", "", ""},
				"minecraft:glowstone_dust", 2, "Craft 2x Glowstone Dust from Haunt Flare at a crafting table.", "Stellt 2x Leuchtsteinstaub aus Spukleuchte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "cooked_porkchop_from_charred_flesh", "copper_inferno:charred_flesh", "slagfiends/cooked_porkchop_from_charred_flesh",
				new String[] {"copper_inferno:charred_flesh", "", "", "", "", "", "", "", ""},
				"minecraft:cooked_porkchop", 1, "Craft 1x Cooked Porkchop from Charred Flesh at a crafting table.", "Stellt 1x Gebratenes Schweinefleisch aus Verkohltes Fleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "rotten_flesh_from_slagrot_chunk", "copper_inferno:slagrot_chunk", "slagfiends/rotten_flesh_from_slagrot_chunk",
				new String[] {"copper_inferno:slagrot_chunk", "", "", "", "", "", "", "", ""},
				"minecraft:rotten_flesh", 2, "Craft 2x Rotten Flesh from Slagrot Chunk at a crafting table.", "Stellt 2x Verrottetes Fleisch aus Schlackenmoder-Brocken an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "magma_cream_from_emberflesh_strip", "copper_inferno:emberflesh_strip", "slagfiends/magma_cream_from_emberflesh_strip",
				new String[] {"copper_inferno:emberflesh_strip", "", "", "", "", "", "", "", ""},
				"minecraft:magma_cream", 1, "Craft 1x Magma Cream from Emberflesh Strip at a crafting table.", "Stellt 1x Magmacreme aus Glutfleisch-Streifen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "iron_nugget_from_cinderbound_shackle", "copper_inferno:cinderbound_shackle", "slagfiends/iron_nugget_from_cinderbound_shackle",
				new String[] {"copper_inferno:cinderbound_shackle", "", "", "", "", "", "", "", ""},
				"minecraft:iron_nugget", 4, "Craft 4x Iron Nugget from Cinderbound Shackle at a crafting table.", "Stellt 4x Eisenklumpen aus Zunderfessel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "brown_dye_from_sootrot_hide", "copper_inferno:sootrot_hide", "slagfiends/brown_dye_from_sootrot_hide",
				new String[] {"copper_inferno:sootrot_hide", "copper_inferno:sootrot_hide", "", "", "", "", "", "", ""},
				"minecraft:brown_dye", 2, "Craft 2x Brown Dye from Sootrot Hide + Sootrot Hide at a crafting table.", "Stellt 2x Brauner Farbstoff aus Ru\u00dfmoder-Haut + Ru\u00dfmoder-Haut an der Werkbank her."));
	}
}
