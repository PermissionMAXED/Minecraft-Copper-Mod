package net.sonic0810.copperinferno.feature.moltenfauna;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Molten Fauna feature: one "mobs" page per mob (25) plus one
 * "items" recipe page for every JSON under
 * {@code data/copper_inferno/recipe/moltenfauna/} (25). Entry texts and grids mirror the
 * recipe JSONs emitted by {@code devtools/gen/moltenfauna_gen.py};
 * {@code devtools/check_handbook.py} parses the inline {@code new HandbookEntry(...)}
 * literals positionally, so keep them inline.
 */
final class MoltenFaunaHandbook {
	private MoltenFaunaHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("mobs", "magma_strider", "copper_inferno:magma_strider_spawn_egg", null,
				null,
				null, 0, "Magma Strider - a magma-crusted strider wading the lava of the Slag Sea and its neighbors. Can be saddled and ridden. Drops Magma Carapace.", "Magmaschreiter - ein magmaverkrusteter Schreiter, der durch die Lava des Schlackenmeers watet. Kann gesattelt und geritten werden. L\u00e4sst Magmapanzer fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_strider", "copper_inferno:soot_strider_spawn_egg", null,
				null,
				null, 0, "Soot Strider - a soot-black strider, quicker on its feet than its kin, skimming the Inferno's lava channels. Drops Soot Bristles.", "Ru\u00dfschreiter - ein ru\u00dfschwarzer Schreiter, flinker als seine Verwandten, der \u00fcber die Lavarinnen des Infernos gleitet. L\u00e4sst Ru\u00dfborsten fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "basalt_strider", "copper_inferno:basalt_strider_spawn_egg", null,
				null,
				null, 0, "Basalt Strider - a heavyset strider armored in basalt plates, plodding through the deepest lava pools. Drops Basalt Scale.", "Basaltschreiter - ein massiger, mit Basaltplatten gepanzerter Schreiter, der durch die tiefsten Lavabecken stapft. L\u00e4sst Basaltschuppe fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_strider", "copper_inferno:slag_strider_spawn_egg", null,
				null,
				null, 0, "Slag Strider - a strider caked in cooling slag, at home wherever the Slag Sea churns. Drops Slag Husk.", "Schlackenschreiter - ein mit erkaltender Schlacke bedeckter Schreiter, \u00fcberall dort zu Hause, wo das Schlackenmeer brodelt. L\u00e4sst Schlackenh\u00fclle fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "obsidian_strider", "copper_inferno:obsidian_strider_spawn_egg", null,
				null,
				null, 0, "Obsidian Strider - a rare strider sheathed in glassy obsidian, the toughest of the lava waders. Drops Obsidian Plating.", "Obsidianschreiter - ein seltener, in glasigen Obsidian geh\u00fcllter Schreiter, der z\u00e4heste aller Lavawater. L\u00e4sst Obsidianplattierung fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_grazer", "copper_inferno:ember_grazer_spawn_egg", null,
				null,
				null, 0, "Ember Grazer - a placid bovine that crops smoldering tufts across the Cinder Wastes, Ember Grove and Slag Sea. Drops Grazer Brisket.", "Glutgraser - ein friedliches Rind, das schwelende B\u00fcschel in der Aschen\u00f6de, im Gluthain und am Schlackenmeer abweidet. L\u00e4sst Graser-Brustkern fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_yak", "copper_inferno:ash_yak_spawn_egg", null,
				null,
				null, 0, "Ash Yak - a shaggy, ash-dusted yak roaming the Inferno's grey plains in small herds. Drops Yak Haunch.", "Aschenyak - ein zotteliger, aschebest\u00e4ubter Yak, der in kleinen Herden \u00fcber die grauen Ebenen des Infernos zieht. L\u00e4sst Yak-Keule fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "magma_ox", "copper_inferno:magma_ox_spawn_egg", null,
				null,
				null, 0, "Magma Ox - a hulking ox with magma-veined hide, unbothered by the heat shimmering off the ground. Drops Ox Loin.", "Magmaochse - ein w\u00fcchtiger Ochse mit magmage\u00e4derter Haut, den die flirrende Hitze des Bodens nicht st\u00f6rt. L\u00e4sst Ochsenlende fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_buffalo", "copper_inferno:slag_buffalo_spawn_egg", null,
				null,
				null, 0, "Slag Buffalo - a broad-shouldered buffalo wallowing in warm slag pits along the Slag Sea's shores. Drops Buffalo Hump.", "Schlackenb\u00fcffel - ein breitschultriger B\u00fcffel, der sich in warmen Schlackengruben an den Ufern des Schlackenmeers suhlt. L\u00e4sst B\u00fcffelh\u00f6cker fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_aurochs", "copper_inferno:cinder_aurochs_spawn_egg", null,
				null,
				null, 0, "Cinder Aurochs - a primeval wild ox with cinder-crusted horns, the largest grazer of the Inferno. Drops Aurochs Shank.", "Zinder-Auerochse - ein urt\u00fcmlicher Wildochse mit zinderverkrusteten H\u00f6rnern, der gr\u00f6\u00dfte Graser des Infernos. L\u00e4sst Auerochsenhachse fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_hen", "copper_inferno:soot_hen_spawn_egg", null,
				null,
				null, 0, "Soot Hen - a soot-grey hen scratching through warm ash for embers and grubs. Drops Hen Drumstick.", "Ru\u00dfhenne - eine ru\u00dfgraue Henne, die in warmer Asche nach Glut und Larven scharrt. L\u00e4sst Hennenkeule fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_rooster", "copper_inferno:cinder_rooster_spawn_egg", null,
				null,
				null, 0, "Cinder Rooster - a strutting rooster with cinder-red plumage, quick to dart across the wastes. Drops Rooster Wing.", "Zinderhahn - ein stolzierender Hahn mit zinderrotem Gefieder, der flink \u00fcber die \u00d6de huscht. L\u00e4sst Hahnenfl\u00fcgel fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_pullet", "copper_inferno:ember_pullet_spawn_egg", null,
				null,
				null, 0, "Ember Pullet - a small young hen glowing faintly like a banked ember. Drops Pullet Breast.", "Glutjunghenne - eine kleine Junghenne, die schwach wie gebettete Glut gl\u00fcht. L\u00e4sst Junghennenbrust fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_fowl", "copper_inferno:ash_fowl_spawn_egg", null,
				null,
				null, 0, "Ash Fowl - a pale, dusty fowl blending into the ash drifts of the Cinder Wastes. Drops Fowl Giblets.", "Aschenhuhn - ein blasses, staubiges Huhn, das in den Aschenwehen der Aschen\u00f6de kaum auff\u00e4llt. L\u00e4sst Gefl\u00fcgelklein fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "magma_bantam", "copper_inferno:magma_bantam_spawn_egg", null,
				null,
				null, 0, "Magma Bantam - a tiny, feisty bantam with magma-orange speckles, always underfoot. Drops Bantam Thigh.", "Magma-Zwerghuhn - ein winziges, keckes Zwerghuhn mit magmaorangen Sprenkeln, das einem st\u00e4ndig zwischen die F\u00fc\u00dfe l\u00e4uft. L\u00e4sst Zwerghuhnschenkel fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "magma_hog", "copper_inferno:magma_hog_spawn_egg", null,
				null,
				null, 0, "Magma Hog - a stout hog rooting through warm cinder beds for buried embers. Drops Hog Belly.", "Magmakeiler - ein st\u00e4mmiger Keiler, der in warmen Zinderbetten nach vergrabener Glut w\u00fchlt. L\u00e4sst Keilerbauch fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_swine", "copper_inferno:soot_swine_spawn_egg", null,
				null,
				null, 0, "Soot Swine - a soot-caked swine happily wallowing in cool ash hollows. Drops Swine Hock.", "Ru\u00dfschwein - ein ru\u00dfverkrustetes Schwein, das sich vergn\u00fcgt in k\u00fchlen Aschenmulden suhlt. L\u00e4sst Schweinshaxe fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_boar", "copper_inferno:cinder_boar_spawn_egg", null,
				null,
				null, 0, "Cinder Boar - a bristly boar trotting briskly between the Ember Grove's charred trunks. Drops Boar Shoulder.", "Zindereber - ein borstiger Eber, der z\u00fcgig zwischen den verkohlten St\u00e4mmen des Gluthains trabt. L\u00e4sst Eberschulter fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_sow", "copper_inferno:slag_sow_spawn_egg", null,
				null,
				null, 0, "Slag Sow - a round sow dozing beside slag pools, unhurried by anything. Drops Sow Jowl.", "Schlackensau - eine rundliche Sau, die neben Schlackent\u00fcmpeln d\u00f6st und sich von nichts hetzen l\u00e4sst. L\u00e4sst Saub\u00e4ckchen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_porker", "copper_inferno:ember_porker_spawn_egg", null,
				null,
				null, 0, "Ember Porker - a plump, well-fed porker glowing warmly around the snout. Drops Porker Ham.", "Glutmastschwein - ein rundes, wohlgen\u00e4hrtes Mastschwein, dessen R\u00fcssel warm gl\u00fcht. L\u00e4sst Mastschinken fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_ewe", "copper_inferno:ash_ewe_spawn_egg", null,
				null,
				null, 0, "Ash Ewe - a gentle ewe with ash-grey fleece, grazing in small flocks. Can be sheared. Drops Ewe Shank.", "Aschenmutterschaf - ein sanftes Mutterschaf mit aschgrauem Vlies, das in kleinen Herden weidet. Kann geschoren werden. L\u00e4sst Schafshaxe fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_ram", "copper_inferno:cinder_ram_spawn_egg", null,
				null,
				null, 0, "Cinder Ram - a headstrong ram patrolling the flock's edge with cinder-dark curls. Drops Ram Rack.", "Zinderwidder - ein dickk\u00f6pfiger Widder mit zinderdunklen Locken, der den Rand der Herde bewacht. L\u00e4sst Widderkarree fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_lamb", "copper_inferno:ember_lamb_spawn_egg", null,
				null,
				null, 0, "Ember Lamb - a small, skittish lamb whose fleece sparks faintly when it bolts. Drops Lamb Cutlet.", "Glutlamm - ein kleines, scheues Lamm, dessen Vlies leicht funkelt, wenn es davonspringt. L\u00e4sst Lammkotelett fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_wether", "copper_inferno:soot_wether_spawn_egg", null,
				null,
				null, 0, "Soot Wether - a heavy, even-tempered wether carrying a thick soot-dark fleece. Drops Wether Ribs.", "Ru\u00dfhammel - ein schwerer, gutm\u00fctiger Hammel mit dichtem, ru\u00dfdunklem Vlies. L\u00e4sst Hammelrippchen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "smolder_sheep", "copper_inferno:smolder_sheep_spawn_egg", null,
				null,
				null, 0, "Smolder Sheep - a sheep whose fleece smolders without ever burning away. Can be sheared. Drops Smolder Fleece.", "Schwelschaf - ein Schaf, dessen Vlies schwelt, ohne je zu verbrennen. Kann geschoren werden. L\u00e4sst Schwelvlies fallen."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_magma_cream_from_magma_carapace", "copper_inferno:magma_carapace", "moltenfauna/magma_cream_from_magma_carapace",
				new String[] {"copper_inferno:magma_carapace", "", "", "", "", "", "", "", ""},
				"minecraft:magma_cream", 2, "Craft 2x Magma Cream from 1x Magma Carapace at a crafting table.", "Stellt aus 1x Magmapanzer 2x Magmacreme an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_string_from_soot_bristles", "copper_inferno:soot_bristles", "moltenfauna/string_from_soot_bristles",
				new String[] {"copper_inferno:soot_bristles", "", "", "", "", "", "", "", ""},
				"minecraft:string", 3, "Craft 3x String from 1x Soot Bristles at a crafting table.", "Stellt aus 1x Ru\u00dfborsten 3x Faden an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_basalt_from_basalt_scale", "copper_inferno:basalt_scale", "moltenfauna/basalt_from_basalt_scale",
				new String[] {"copper_inferno:basalt_scale", "copper_inferno:basalt_scale", "copper_inferno:basalt_scale", "copper_inferno:basalt_scale", "", "", "", "", ""},
				"minecraft:basalt", 1, "Craft 1x Basalt from 4x Basalt Scale at a crafting table.", "Stellt aus 4x Basaltschuppe 1x Basalt an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_leather_from_slag_husk", "copper_inferno:slag_husk", "moltenfauna/leather_from_slag_husk",
				new String[] {"copper_inferno:slag_husk", "", "", "", "", "", "", "", ""},
				"minecraft:leather", 1, "Craft 1x Leather from 1x Slag Husk at a crafting table.", "Stellt aus 1x Schlackenh\u00fclle 1x Leder an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_black_dye_from_obsidian_plating", "copper_inferno:obsidian_plating", "moltenfauna/black_dye_from_obsidian_plating",
				new String[] {"copper_inferno:obsidian_plating", "", "", "", "", "", "", "", ""},
				"minecraft:black_dye", 2, "Craft 2x Black Dye from 1x Obsidian Plating at a crafting table.", "Stellt aus 1x Obsidianplattierung 2x Schwarzer Farbstoff an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_beef_from_grazer_brisket", "copper_inferno:grazer_brisket", "moltenfauna/beef_from_grazer_brisket",
				new String[] {"copper_inferno:grazer_brisket", "", "", "", "", "", "", "", ""},
				"minecraft:beef", 2, "Craft 2x Raw Beef from 1x Grazer Brisket at a crafting table.", "Stellt aus 1x Graser-Brustkern 2x Rohes Rindfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_beef_from_yak_haunch", "copper_inferno:yak_haunch", "moltenfauna/beef_from_yak_haunch",
				new String[] {"copper_inferno:yak_haunch", "", "", "", "", "", "", "", ""},
				"minecraft:beef", 2, "Craft 2x Raw Beef from 1x Yak Haunch at a crafting table.", "Stellt aus 1x Yak-Keule 2x Rohes Rindfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_beef_from_ox_loin", "copper_inferno:ox_loin", "moltenfauna/beef_from_ox_loin",
				new String[] {"copper_inferno:ox_loin", "", "", "", "", "", "", "", ""},
				"minecraft:beef", 2, "Craft 2x Raw Beef from 1x Ox Loin at a crafting table.", "Stellt aus 1x Ochsenlende 2x Rohes Rindfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_beef_from_buffalo_hump", "copper_inferno:buffalo_hump", "moltenfauna/beef_from_buffalo_hump",
				new String[] {"copper_inferno:buffalo_hump", "", "", "", "", "", "", "", ""},
				"minecraft:beef", 2, "Craft 2x Raw Beef from 1x Buffalo Hump at a crafting table.", "Stellt aus 1x B\u00fcffelh\u00f6cker 2x Rohes Rindfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_beef_from_aurochs_shank", "copper_inferno:aurochs_shank", "moltenfauna/beef_from_aurochs_shank",
				new String[] {"copper_inferno:aurochs_shank", "", "", "", "", "", "", "", ""},
				"minecraft:beef", 2, "Craft 2x Raw Beef from 1x Aurochs Shank at a crafting table.", "Stellt aus 1x Auerochsenhachse 2x Rohes Rindfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_chicken_from_hen_drumstick", "copper_inferno:hen_drumstick", "moltenfauna/chicken_from_hen_drumstick",
				new String[] {"copper_inferno:hen_drumstick", "", "", "", "", "", "", "", ""},
				"minecraft:chicken", 1, "Craft 1x Raw Chicken from 1x Hen Drumstick at a crafting table.", "Stellt aus 1x Hennenkeule 1x Rohes H\u00fchnchen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_chicken_from_rooster_wing", "copper_inferno:rooster_wing", "moltenfauna/chicken_from_rooster_wing",
				new String[] {"copper_inferno:rooster_wing", "", "", "", "", "", "", "", ""},
				"minecraft:chicken", 1, "Craft 1x Raw Chicken from 1x Rooster Wing at a crafting table.", "Stellt aus 1x Hahnenfl\u00fcgel 1x Rohes H\u00fchnchen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_chicken_from_pullet_breast", "copper_inferno:pullet_breast", "moltenfauna/chicken_from_pullet_breast",
				new String[] {"copper_inferno:pullet_breast", "", "", "", "", "", "", "", ""},
				"minecraft:chicken", 1, "Craft 1x Raw Chicken from 1x Pullet Breast at a crafting table.", "Stellt aus 1x Junghennenbrust 1x Rohes H\u00fchnchen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_chicken_from_fowl_giblets", "copper_inferno:fowl_giblets", "moltenfauna/chicken_from_fowl_giblets",
				new String[] {"copper_inferno:fowl_giblets", "", "", "", "", "", "", "", ""},
				"minecraft:chicken", 1, "Craft 1x Raw Chicken from 1x Fowl Giblets at a crafting table.", "Stellt aus 1x Gefl\u00fcgelklein 1x Rohes H\u00fchnchen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_chicken_from_bantam_thigh", "copper_inferno:bantam_thigh", "moltenfauna/chicken_from_bantam_thigh",
				new String[] {"copper_inferno:bantam_thigh", "", "", "", "", "", "", "", ""},
				"minecraft:chicken", 1, "Craft 1x Raw Chicken from 1x Bantam Thigh at a crafting table.", "Stellt aus 1x Zwerghuhnschenkel 1x Rohes H\u00fchnchen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_porkchop_from_hog_belly", "copper_inferno:hog_belly", "moltenfauna/porkchop_from_hog_belly",
				new String[] {"copper_inferno:hog_belly", "", "", "", "", "", "", "", ""},
				"minecraft:porkchop", 2, "Craft 2x Raw Porkchop from 1x Hog Belly at a crafting table.", "Stellt aus 1x Keilerbauch 2x Rohes Schweinefleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_porkchop_from_swine_hock", "copper_inferno:swine_hock", "moltenfauna/porkchop_from_swine_hock",
				new String[] {"copper_inferno:swine_hock", "", "", "", "", "", "", "", ""},
				"minecraft:porkchop", 2, "Craft 2x Raw Porkchop from 1x Swine Hock at a crafting table.", "Stellt aus 1x Schweinshaxe 2x Rohes Schweinefleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_porkchop_from_boar_shoulder", "copper_inferno:boar_shoulder", "moltenfauna/porkchop_from_boar_shoulder",
				new String[] {"copper_inferno:boar_shoulder", "", "", "", "", "", "", "", ""},
				"minecraft:porkchop", 2, "Craft 2x Raw Porkchop from 1x Boar Shoulder at a crafting table.", "Stellt aus 1x Eberschulter 2x Rohes Schweinefleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_porkchop_from_sow_jowl", "copper_inferno:sow_jowl", "moltenfauna/porkchop_from_sow_jowl",
				new String[] {"copper_inferno:sow_jowl", "", "", "", "", "", "", "", ""},
				"minecraft:porkchop", 2, "Craft 2x Raw Porkchop from 1x Sow Jowl at a crafting table.", "Stellt aus 1x Saub\u00e4ckchen 2x Rohes Schweinefleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_porkchop_from_porker_ham", "copper_inferno:porker_ham", "moltenfauna/porkchop_from_porker_ham",
				new String[] {"copper_inferno:porker_ham", "", "", "", "", "", "", "", ""},
				"minecraft:porkchop", 2, "Craft 2x Raw Porkchop from 1x Porker Ham at a crafting table.", "Stellt aus 1x Mastschinken 2x Rohes Schweinefleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_mutton_from_ewe_shank", "copper_inferno:ewe_shank", "moltenfauna/mutton_from_ewe_shank",
				new String[] {"copper_inferno:ewe_shank", "", "", "", "", "", "", "", ""},
				"minecraft:mutton", 2, "Craft 2x Raw Mutton from 1x Ewe Shank at a crafting table.", "Stellt aus 1x Schafshaxe 2x Rohes Hammelfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_mutton_from_ram_rack", "copper_inferno:ram_rack", "moltenfauna/mutton_from_ram_rack",
				new String[] {"copper_inferno:ram_rack", "", "", "", "", "", "", "", ""},
				"minecraft:mutton", 2, "Craft 2x Raw Mutton from 1x Ram Rack at a crafting table.", "Stellt aus 1x Widderkarree 2x Rohes Hammelfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_mutton_from_lamb_cutlet", "copper_inferno:lamb_cutlet", "moltenfauna/mutton_from_lamb_cutlet",
				new String[] {"copper_inferno:lamb_cutlet", "", "", "", "", "", "", "", ""},
				"minecraft:mutton", 2, "Craft 2x Raw Mutton from 1x Lamb Cutlet at a crafting table.", "Stellt aus 1x Lammkotelett 2x Rohes Hammelfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_mutton_from_wether_ribs", "copper_inferno:wether_ribs", "moltenfauna/mutton_from_wether_ribs",
				new String[] {"copper_inferno:wether_ribs", "", "", "", "", "", "", "", ""},
				"minecraft:mutton", 2, "Craft 2x Raw Mutton from 1x Wether Ribs at a crafting table.", "Stellt aus 1x Hammelrippchen 2x Rohes Hammelfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "moltenfauna_white_wool_from_smolder_fleece", "copper_inferno:smolder_fleece", "moltenfauna/white_wool_from_smolder_fleece",
				new String[] {"copper_inferno:smolder_fleece", "", "", "", "", "", "", "", ""},
				"minecraft:white_wool", 1, "Craft 1x White Wool from 1x Smolder Fleece at a crafting table.", "Stellt aus 1x Schwelvlies 1x Wei\u00dfe Wolle an der Werkbank her."));
	}
}
