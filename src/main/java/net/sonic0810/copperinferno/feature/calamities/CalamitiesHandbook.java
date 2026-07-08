package net.sonic0810.copperinferno.feature.calamities;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the calamity bosses: one "bosses" lore page per boss
 * (summon ritual, phases/mechanic, drops) plus one page for every recipe JSON under
 * {@code data/copper_inferno/recipe/calamities/} (summon ring crafts + shapeless
 * trophy crafts). Texts and grids mirror the JSONs emitted by
 * {@code devtools/gen/calamities_gen.py}; {@code devtools/check_handbook.py} parses
 * the inline {@code new HandbookEntry(...)} literals positionally, so keep them
 * inline.
 */
final class CalamitiesHandbook {
	private CalamitiesHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("bosses", "emberlord_ravager", "copper_inferno:emberlord_ravager_spawn_egg", null,
				null,
				null, 0, "Emberlord Ravager - a colossal war beast wreathed in embers. Summon: use an Emberlord Warhorn (consumed; refused on peaceful). Every 4 seconds it ignites every player it can see within 5 blocks, and below half health it enrages and speeds up. Drops 1-2 Emberlord Tusks and 2-4 Charred Hides.", "Glutf\u00fcrst-Verw\u00fcster - eine kolossale, in Glut geh\u00fcllte Kriegsbestie. Beschw\u00f6rung: ein Glutf\u00fcrst-Kriegshorn benutzen (wird verbraucht; auf Friedlich verweigert). Alle 4 Sekunden entz\u00fcndet er jeden Spieler, den er im Umkreis von 5 Bl\u00f6cken sehen kann; unter halber Gesundheit wird er rasend und schneller. L\u00e4sst 1-2 Glutf\u00fcrst-Sto\u00dfz\u00e4hne und 2-4 Verkohlte H\u00e4ute fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "slag_warlord", "copper_inferno:slag_warlord_spawn_egg", null,
				null,
				null, 0, "Slag Warlord - a slag-armored ravager warchief. Summon: use a Slag War Banner. Below half health it enters phase two exactly once - it calls 2 vindicator lieutenants, hardens with Resistance and its blows gain +6 damage. Drops 1-2 Broken War Axes and 2-4 Warlord Slag Chunks.", "Schlacken-Kriegsherr - ein schlackengepanzerter Verw\u00fcster-Kriegsh\u00e4uptling. Beschw\u00f6rung: ein Schlacken-Kriegsbanner benutzen. Unter halber Gesundheit beginnt genau einmal Phase zwei - er ruft 2 Diener als Verst\u00e4rkung, h\u00e4rtet sich mit Resistenz und seine Hiebe erhalten +6 Schaden. L\u00e4sst 1-2 Zerbrochene Kriegs\u00e4xte und 2-4 Kriegsherren-Schlackenbrocken fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "molten_colossus", "copper_inferno:molten_colossus_spawn_egg", null,
				null,
				null, 0, "Molten Colossus - a magma-veined iron colossus. Summon: use a Colossus Effigy. It hunts players on sight; every 5 seconds it vents a molten wave that ignites and slows everyone it can see within 6 blocks, and below half health its fists gain +8 damage. Drops 1-2 Colossus Platings and 2-4 Molten Core Shards.", "Schmelzkoloss - ein von Magmaadern durchzogener Eisenkoloss. Beschw\u00f6rung: ein Koloss-Bildnis benutzen. Er jagt Spieler auf Sicht; alle 5 Sekunden st\u00f6\u00dft er eine Schmelzwelle aus, die jeden entz\u00fcndet und verlangsamt, den er im Umkreis von 6 Bl\u00f6cken sehen kann; unter halber Gesundheit erhalten seine F\u00e4uste +8 Schaden. L\u00e4sst 1-2 Koloss-Panzerplatten und 2-4 Schmelzkern-Scherben fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "verdigris_monarch", "copper_inferno:verdigris_monarch_spawn_egg", null,
				null,
				null, 0, "Verdigris Monarch - a regal, patina-crowned golem. Summon: use the Verdigris Regalia. Every 5 seconds it saps everyone it can see within 6 blocks with Weakness and Mining Fatigue, and below half health it slowly regenerates. Drops 1-2 Monarch Plates and 2-4 Verdant Patinas.", "Gr\u00fcnspan-Monarch - ein k\u00f6niglicher, patinagekr\u00f6nter Golem. Beschw\u00f6rung: die Gr\u00fcnspan-Insignien benutzen. Alle 5 Sekunden schw\u00e4cht er jeden, den er im Umkreis von 6 Bl\u00f6cken sehen kann, mit Schw\u00e4che und Abbaul\u00e4hmung; unter halber Gesundheit regeneriert er sich langsam. L\u00e4sst 1-2 Monarchenplatten und 2-4 Gr\u00fcne Patinas fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "ashking_wither", "copper_inferno:ashking_wither_spawn_egg", null,
				null,
				null, 0, "Ashking Wither - the skeletal king of the ash. Summon: use an Ashking Skull Idol. Every 5 seconds it withers everyone it can see within 6 blocks; below half health it hardens with Resistance once and gains speed. Drops 1-2 Cursed Ash Clumps and 2-4 Ashking Ribs.", "Aschek\u00f6nig-Wither - der Skelettk\u00f6nig der Asche. Beschw\u00f6rung: ein Aschek\u00f6nig-Sch\u00e4delidol benutzen. Alle 5 Sekunden belegt er jeden, den er im Umkreis von 6 Bl\u00f6cken sehen kann, mit Verdorren; unter halber Gesundheit h\u00e4rtet er sich einmal mit Resistenz und wird schneller. L\u00e4sst 1-2 Verfluchte Ascheklumpen und 2-4 Aschek\u00f6nig-Rippen fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "soot_reaper", "copper_inferno:soot_reaper_spawn_egg", null,
				null,
				null, 0, "Soot Reaper - a spectral wither harvester. Summon: use a Reaper Knell. Its scythe strikes blind and wither their victim, and below half health it enrages and speeds up. Drops 1-2 Soot Scythe Blades and 2-4 Reaper Soot.", "Ru\u00dfschnitter - ein spektraler Wither-Schnitter. Beschw\u00f6rung: eine Schnitter-Totenglocke benutzen. Seine Sensenhiebe blenden ihr Opfer und lassen es verdorren; unter halber Gesundheit wird er rasend und schneller. L\u00e4sst 1-2 Ru\u00dfsensenklingen und 2-4 Schnitterru\u00df fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "cinder_sovereign", "copper_inferno:cinder_sovereign_spawn_egg", null,
				null,
				null, 0, "Cinder Sovereign - a blazing tyrant of cinders. Summon: use a Cinder Beacon. While it has a target it periodically erupts in a ring of eight small fireballs on top of its regular volleys. Drops 1-2 Cinder Plumes and 2-4 Sovereign Ember Shards.", "Zunderherrscher - ein lodernder Herrscher der Zunder. Beschw\u00f6rung: ein Zunder-Leuchtfeuer benutzen. Solange er ein Ziel hat, bricht er regelm\u00e4\u00dfig in einen Ring aus acht kleinen Feuerb\u00e4llen aus, zus\u00e4tzlich zu seinen \u00fcblichen Salven. L\u00e4sst 1-2 Zunderfedern und 2-4 Herrscher-Glutscherben fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "pyre_tyrant", "copper_inferno:pyre_tyrant_spawn_egg", null,
				null,
				null, 0, "Pyre Tyrant - a colossal blaze warlord. Summon: use a Tyrant Pyre Brand. Below half health it enters phase two exactly once - it calls 3 blaze minions, hardens with Resistance and charges into melee with +4 damage. Drops 1-2 Pyre Fangs and 2-4 Tyrant Ash.", "Scheiterhaufen-Tyrann - ein kolossaler Lohen-Kriegsherr. Beschw\u00f6rung: einen Tyrannen-Feuerbrand benutzen. Unter halber Gesundheit beginnt genau einmal Phase zwei - er ruft 3 Lohen-Diener, h\u00e4rtet sich mit Resistenz und st\u00fcrmt mit +4 Schaden in den Nahkampf. L\u00e4sst 1-2 Scheiterhaufen-Fangz\u00e4hne und 2-4 Tyrannenasche fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "furnace_fiend", "copper_inferno:furnace_fiend_spawn_egg", null,
				null,
				null, 0, "Furnace Fiend - an axe-swinging fiend from the furnace depths. Summon: use a Fiend Ember Key. Its axe blows set the victim on fire, and below half health it enrages and speeds up. Drops 1-2 Fiend Talons and 2-4 Fiend Cinders.", "Ofenunhold - ein axtschwingender Unhold aus den Ofentiefen. Beschw\u00f6rung: einen Unhold-Glutschl\u00fcssel benutzen. Seine Axthiebe setzen das Opfer in Brand; unter halber Gesundheit wird er rasend und schneller. L\u00e4sst 1-2 Unhold-Klauen und 2-4 Unhold-Zunder fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "calamity_herald", "copper_inferno:calamity_herald_spawn_egg", null,
				null,
				null, 0, "Calamity Herald - the doom-crier of the calamities. Summon: use a Herald Omen Sigil. Every 6 seconds it marks everyone it can see within 8 blocks with Glowing and Slowness; below half health it calls 2 vindicator adds exactly once. Drops 1-2 Herald Emberglass and 2-4 Omen Fragments.", "Herold des Unheils - der Unheilsrufer der Katastrophen. Beschw\u00f6rung: ein Herold-Omensiegel benutzen. Alle 6 Sekunden zeichnet er jeden, den er im Umkreis von 8 Bl\u00f6cken sehen kann, mit Leuchten und Langsamkeit; unter halber Gesundheit ruft er genau einmal 2 Diener herbei. L\u00e4sst 1-2 Herold-Glutglas und 2-4 Omenfragmente fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "emberlord_warhorn_recipe", "copper_inferno:emberlord_warhorn", "calamities/emberlord_warhorn",
				new String[] {"minecraft:copper_ingot", "copper_inferno:inferno_powder", "minecraft:copper_ingot", "copper_inferno:inferno_powder", "minecraft:goat_horn", "copper_inferno:inferno_powder", "minecraft:copper_ingot", "copper_inferno:inferno_powder", "minecraft:copper_ingot"},
				"copper_inferno:emberlord_warhorn", 1, "Emberlord Warhorn - ring Goat Horn with 4x Copper Ingot and 4x Inferno Powder. Use it to summon the Emberlord Ravager; consumed on success, refused on peaceful.", "Glutf\u00fcrst-Kriegshorn - Ziegenhorn mit 4x Kupferbarren und 4x Infernopulver umringen. Benutzen, um den Glutf\u00fcrst-Verw\u00fcster zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "emberlord_crest_recipe", "copper_inferno:emberlord_crest", "calamities/emberlord_crest",
				new String[] {"copper_inferno:emberlord_tusk", "copper_inferno:charred_hide", "copper_inferno:charred_hide", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:emberlord_crest", 1, "Emberlord Crest - shapeless: 1x Emberlord Tusk + 2x Charred Hide + 1x Gold Block. The epic trophy of the Emberlord Ravager.", "Glutf\u00fcrst-Wappen - formlos: 1x Glutf\u00fcrst-Sto\u00dfzahn + 2x Verkohlte Haut + 1x Goldblock. Die epische Troph\u00e4e zum Boss Glutf\u00fcrst-Verw\u00fcster."));

		HandbookEntries.add(new HandbookEntry("bosses", "slag_war_banner_recipe", "copper_inferno:slag_war_banner", "calamities/slag_war_banner",
				new String[] {"minecraft:iron_ingot", "copper_inferno:oxidized_copper_dust", "minecraft:iron_ingot", "copper_inferno:oxidized_copper_dust", "minecraft:iron_block", "copper_inferno:oxidized_copper_dust", "minecraft:iron_ingot", "copper_inferno:oxidized_copper_dust", "minecraft:iron_ingot"},
				"copper_inferno:slag_war_banner", 1, "Slag War Banner - ring Iron Block with 4x Iron Ingot and 4x Oxidized Copper Dust. Use it to summon the Slag Warlord; consumed on success, refused on peaceful.", "Schlacken-Kriegsbanner - Eisenblock mit 4x Eisenbarren und 4x Oxidierter Kupferstaub umringen. Benutzen, um den Schlacken-Kriegsherr zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "warlord_totem_recipe", "copper_inferno:warlord_totem", "calamities/warlord_totem",
				new String[] {"copper_inferno:broken_war_axe", "copper_inferno:warlord_slag_chunk", "copper_inferno:warlord_slag_chunk", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:warlord_totem", 1, "Warlord Totem - shapeless: 1x Broken War Axe + 2x Warlord Slag Chunk + 1x Gold Block. The epic trophy of the Slag Warlord.", "Kriegsherren-Totem - formlos: 1x Zerbrochene Kriegsaxt + 2x Kriegsherren-Schlackenbrocken + 1x Goldblock. Die epische Troph\u00e4e zum Boss Schlacken-Kriegsherr."));

		HandbookEntries.add(new HandbookEntry("bosses", "colossus_effigy_recipe", "copper_inferno:colossus_effigy", "calamities/colossus_effigy",
				new String[] {"copper_inferno:copper_dust", "minecraft:iron_ingot", "copper_inferno:copper_dust", "minecraft:iron_ingot", "minecraft:copper_block", "minecraft:iron_ingot", "copper_inferno:copper_dust", "minecraft:iron_ingot", "copper_inferno:copper_dust"},
				"copper_inferno:colossus_effigy", 1, "Colossus Effigy - ring Copper Block with 4x Copper Dust and 4x Iron Ingot. Use it to summon the Molten Colossus; consumed on success, refused on peaceful.", "Koloss-Bildnis - Kupferblock mit 4x Kupferstaub und 4x Eisenbarren umringen. Benutzen, um den Schmelzkoloss zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "colossus_medallion_recipe", "copper_inferno:colossus_medallion", "calamities/colossus_medallion",
				new String[] {"copper_inferno:colossus_plating", "copper_inferno:molten_core_shard", "copper_inferno:molten_core_shard", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:colossus_medallion", 1, "Colossus Medallion - shapeless: 1x Colossus Plating + 2x Molten Core Shard + 1x Gold Block. The epic trophy of the Molten Colossus.", "Koloss-Medaillon - formlos: 1x Koloss-Panzerplatte + 2x Schmelzkern-Scherbe + 1x Goldblock. Die epische Troph\u00e4e zum Boss Schmelzkoloss."));

		HandbookEntries.add(new HandbookEntry("bosses", "verdigris_regalia_recipe", "copper_inferno:verdigris_regalia", "calamities/verdigris_regalia",
				new String[] {"minecraft:emerald", "copper_inferno:oxidized_copper_dust", "minecraft:emerald", "copper_inferno:oxidized_copper_dust", "minecraft:oxidized_copper", "copper_inferno:oxidized_copper_dust", "minecraft:emerald", "copper_inferno:oxidized_copper_dust", "minecraft:emerald"},
				"copper_inferno:verdigris_regalia", 1, "Verdigris Regalia - ring Oxidized Copper with 4x Emerald and 4x Oxidized Copper Dust. Use it to summon the Verdigris Monarch; consumed on success, refused on peaceful.", "Gr\u00fcnspan-Insignien - Oxidiertes Kupfer mit 4x Smaragd und 4x Oxidierter Kupferstaub umringen. Benutzen, um den Gr\u00fcnspan-Monarch zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "monarch_signet_recipe", "copper_inferno:monarch_signet", "calamities/monarch_signet",
				new String[] {"copper_inferno:monarch_plate", "copper_inferno:verdant_patina", "copper_inferno:verdant_patina", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:monarch_signet", 1, "Monarch Signet - shapeless: 1x Monarch Plate + 2x Verdant Patina + 1x Gold Block. The epic trophy of the Verdigris Monarch.", "Monarchen-Siegelring - formlos: 1x Monarchenplatte + 2x Gr\u00fcne Patina + 1x Goldblock. Die epische Troph\u00e4e zum Boss Gr\u00fcnspan-Monarch."));

		HandbookEntries.add(new HandbookEntry("bosses", "ashking_skull_idol_recipe", "copper_inferno:ashking_skull_idol", "calamities/ashking_skull_idol",
				new String[] {"minecraft:bone", "copper_inferno:inferno_powder", "minecraft:bone", "copper_inferno:inferno_powder", "minecraft:wither_skeleton_skull", "copper_inferno:inferno_powder", "minecraft:bone", "copper_inferno:inferno_powder", "minecraft:bone"},
				"copper_inferno:ashking_skull_idol", 1, "Ashking Skull Idol - ring Wither Skeleton Skull with 4x Bone and 4x Inferno Powder. Use it to summon the Ashking Wither; consumed on success, refused on peaceful.", "Aschek\u00f6nig-Sch\u00e4delidol - Witherskelettsch\u00e4del mit 4x Knochen und 4x Infernopulver umringen. Benutzen, um den Aschek\u00f6nig-Wither zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "ashking_diadem_recipe", "copper_inferno:ashking_diadem", "calamities/ashking_diadem",
				new String[] {"copper_inferno:cursed_ash_clump", "copper_inferno:ashking_rib", "copper_inferno:ashking_rib", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:ashking_diadem", 1, "Ashking Diadem - shapeless: 1x Cursed Ash Clump + 2x Ashking Rib + 1x Gold Block. The epic trophy of the Ashking Wither.", "Aschek\u00f6nig-Diadem - formlos: 1x Verfluchter Ascheklumpen + 2x Aschek\u00f6nig-Rippe + 1x Goldblock. Die epische Troph\u00e4e zum Boss Aschek\u00f6nig-Wither."));

		HandbookEntries.add(new HandbookEntry("bosses", "reaper_knell_recipe", "copper_inferno:reaper_knell", "calamities/reaper_knell",
				new String[] {"minecraft:charcoal", "copper_inferno:inferno_powder", "minecraft:charcoal", "copper_inferno:inferno_powder", "minecraft:bell", "copper_inferno:inferno_powder", "minecraft:charcoal", "copper_inferno:inferno_powder", "minecraft:charcoal"},
				"copper_inferno:reaper_knell", 1, "Reaper Knell - ring Bell with 4x Charcoal and 4x Inferno Powder. Use it to summon the Soot Reaper; consumed on success, refused on peaceful.", "Schnitter-Totenglocke - Glocke mit 4x Holzkohle und 4x Infernopulver umringen. Benutzen, um den Ru\u00dfschnitter zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "reaper_hourglass_recipe", "copper_inferno:reaper_hourglass", "calamities/reaper_hourglass",
				new String[] {"copper_inferno:soot_scythe_blade", "copper_inferno:reaper_soot", "copper_inferno:reaper_soot", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:reaper_hourglass", 1, "Reaper Hourglass - shapeless: 1x Soot Scythe Blade + 2x Reaper Soot + 1x Gold Block. The epic trophy of the Soot Reaper.", "Schnitter-Stundenglas - formlos: 1x Ru\u00dfsensenklinge + 2x Schnitterru\u00df + 1x Goldblock. Die epische Troph\u00e4e zum Boss Ru\u00dfschnitter."));

		HandbookEntries.add(new HandbookEntry("bosses", "cinder_beacon_recipe", "copper_inferno:cinder_beacon", "calamities/cinder_beacon",
				new String[] {"minecraft:blaze_powder", "copper_inferno:inferno_powder", "minecraft:blaze_powder", "copper_inferno:inferno_powder", "minecraft:lantern", "copper_inferno:inferno_powder", "minecraft:blaze_powder", "copper_inferno:inferno_powder", "minecraft:blaze_powder"},
				"copper_inferno:cinder_beacon", 1, "Cinder Beacon - ring Lantern with 4x Blaze Powder and 4x Inferno Powder. Use it to summon the Cinder Sovereign; consumed on success, refused on peaceful.", "Zunder-Leuchtfeuer - Laterne mit 4x Lohenstaub und 4x Infernopulver umringen. Benutzen, um den Zunderherrscher zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "sovereign_scepter_recipe", "copper_inferno:sovereign_scepter", "calamities/sovereign_scepter",
				new String[] {"copper_inferno:cinder_plume", "copper_inferno:sovereign_ember_shard", "copper_inferno:sovereign_ember_shard", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:sovereign_scepter", 1, "Sovereign Scepter - shapeless: 1x Cinder Plume + 2x Sovereign Ember Shard + 1x Gold Block. The epic trophy of the Cinder Sovereign.", "Herrscherzepter - formlos: 1x Zunderfeder + 2x Herrscher-Glutscherbe + 1x Goldblock. Die epische Troph\u00e4e zum Boss Zunderherrscher."));

		HandbookEntries.add(new HandbookEntry("bosses", "tyrant_pyre_brand_recipe", "copper_inferno:tyrant_pyre_brand", "calamities/tyrant_pyre_brand",
				new String[] {"minecraft:blaze_rod", "copper_inferno:inferno_powder", "minecraft:blaze_rod", "copper_inferno:inferno_powder", "minecraft:campfire", "copper_inferno:inferno_powder", "minecraft:blaze_rod", "copper_inferno:inferno_powder", "minecraft:blaze_rod"},
				"copper_inferno:tyrant_pyre_brand", 1, "Tyrant Pyre Brand - ring Campfire with 4x Blaze Rod and 4x Inferno Powder. Use it to summon the Pyre Tyrant; consumed on success, refused on peaceful.", "Tyrannen-Feuerbrand - Lagerfeuer mit 4x Lohenrute und 4x Infernopulver umringen. Benutzen, um den Scheiterhaufen-Tyrann zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "pyre_tyrant_crown_recipe", "copper_inferno:pyre_tyrant_crown", "calamities/pyre_tyrant_crown",
				new String[] {"copper_inferno:pyre_fang", "copper_inferno:tyrant_ash", "copper_inferno:tyrant_ash", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:pyre_tyrant_crown", 1, "Pyre Tyrant Crown - shapeless: 1x Pyre Fang + 2x Tyrant Ash + 1x Gold Block. The epic trophy of the Pyre Tyrant.", "Scheiterhaufen-Tyrannenkrone - formlos: 1x Scheiterhaufen-Fangzahn + 2x Tyrannenasche + 1x Goldblock. Die epische Troph\u00e4e zum Boss Scheiterhaufen-Tyrann."));

		HandbookEntries.add(new HandbookEntry("bosses", "fiend_ember_key_recipe", "copper_inferno:fiend_ember_key", "calamities/fiend_ember_key",
				new String[] {"minecraft:gold_nugget", "copper_inferno:copper_dust", "minecraft:gold_nugget", "copper_inferno:copper_dust", "minecraft:coal_block", "copper_inferno:copper_dust", "minecraft:gold_nugget", "copper_inferno:copper_dust", "minecraft:gold_nugget"},
				"copper_inferno:fiend_ember_key", 1, "Fiend Ember Key - ring Coal Block with 4x Gold Nugget and 4x Copper Dust. Use it to summon the Furnace Fiend; consumed on success, refused on peaceful.", "Unhold-Glutschl\u00fcssel - Kohleblock mit 4x Goldklumpen und 4x Kupferstaub umringen. Benutzen, um den Ofenunhold zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "fiend_furnace_mask_recipe", "copper_inferno:fiend_furnace_mask", "calamities/fiend_furnace_mask",
				new String[] {"copper_inferno:fiend_talon", "copper_inferno:fiend_cinder", "copper_inferno:fiend_cinder", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:fiend_furnace_mask", 1, "Fiend Furnace Mask - shapeless: 1x Fiend Talon + 2x Fiend Cinder + 1x Gold Block. The epic trophy of the Furnace Fiend.", "Unhold-Ofenmaske - formlos: 1x Unhold-Klaue + 2x Unhold-Zunder + 1x Goldblock. Die epische Troph\u00e4e zum Boss Ofenunhold."));

		HandbookEntries.add(new HandbookEntry("bosses", "herald_omen_sigil_recipe", "copper_inferno:herald_omen_sigil", "calamities/herald_omen_sigil",
				new String[] {"minecraft:copper_ingot", "copper_inferno:oxidized_copper_dust", "minecraft:copper_ingot", "copper_inferno:oxidized_copper_dust", "minecraft:ominous_bottle", "copper_inferno:oxidized_copper_dust", "minecraft:copper_ingot", "copper_inferno:oxidized_copper_dust", "minecraft:copper_ingot"},
				"copper_inferno:herald_omen_sigil", 1, "Herald Omen Sigil - ring Ominous Bottle with 4x Copper Ingot and 4x Oxidized Copper Dust. Use it to summon the Calamity Herald; consumed on success, refused on peaceful.", "Herold-Omensiegel - Unheilvolle Flasche mit 4x Kupferbarren und 4x Oxidierter Kupferstaub umringen. Benutzen, um den Herold des Unheils zu beschw\u00f6ren; wird bei Erfolg verbraucht, auf Friedlich verweigert."));

		HandbookEntries.add(new HandbookEntry("bosses", "herald_war_banner_recipe", "copper_inferno:herald_war_banner", "calamities/herald_war_banner",
				new String[] {"copper_inferno:herald_emberglass", "copper_inferno:omen_fragment", "copper_inferno:omen_fragment", "minecraft:gold_block", "", "", "", "", ""},
				"copper_inferno:herald_war_banner", 1, "Herald War Banner - shapeless: 1x Herald Emberglass + 2x Omen Fragment + 1x Gold Block. The epic trophy of the Calamity Herald.", "Herold-Kriegsbanner - formlos: 1x Herold-Glutglas + 2x Omenfragment + 1x Goldblock. Die epische Troph\u00e4e zum Boss Herold des Unheils."));
	}
}
