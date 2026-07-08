package net.sonic0810.copperinferno.feature.archfiends;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the ten archfiend bosses: one "bosses" entry per boss
 * (summon, mechanics, drops) plus one grid entry for every ring recipe under
 * {@code data/copper_inferno/recipe/archfiends/}. Entry texts and grids mirror
 * the recipe JSONs emitted by {@code devtools/gen/archfiends_gen.py};
 * {@code devtools/check_handbook.py} parses the inline
 * {@code new HandbookEntry(...)} literals positionally, so keep them inline.
 */
final class ArchfiendsHandbook {
	private ArchfiendsHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("bosses", "dread_ghast_sovereign", "copper_inferno:dread_ghast_sovereign_spawn_egg", null,
				null,
				null, 0, "Dread Ghast Sovereign - a colossal spectral ghast archfiend. Summon: use a Dread Sovereign Sigil (never on peaceful). Its dread veil shrouds every player it can see within 10 blocks in Darkness and Slowness; below half health it enters a frenzy, venting the veil twice as often and hardening with Resistance. Drops 2-4 Dread Ghast Tears, 1-2 Sovereign Veil Shards and the Dread Sovereign Crown.", "Schreckens-Ghast-F\u00fcrst - ein kolossaler Geister-Ghast-Erzd\u00e4mon. Beschw\u00f6rung: ein Schreckensf\u00fcrsten-Siegel benutzen (nie auf Friedlich). Sein Schreckensschleier h\u00fcllt jeden sichtbaren Spieler im Umkreis von 10 Bl\u00f6cken in Dunkelheit und Langsamkeit; unter halber Gesundheit verf\u00e4llt er in Raserei, st\u00f6\u00dft den Schleier doppelt so oft aus und h\u00e4rtet sich mit Resistenz. L\u00e4sst 2-4 Schreckens-Ghast-Tr\u00e4nen, 1-2 Schleierscherben des F\u00fcrsten und die Krone des Schreckensf\u00fcrsten fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "dread_sovereign_sigil_recipe", "copper_inferno:dread_sovereign_sigil", "archfiends/dread_sovereign_sigil",
				new String[] {"minecraft:ghast_tear", "minecraft:soul_sand", "minecraft:ghast_tear", "minecraft:soul_sand", "minecraft:crying_obsidian", "minecraft:soul_sand", "minecraft:ghast_tear", "minecraft:soul_sand", "minecraft:ghast_tear"},
				"copper_inferno:dread_sovereign_sigil", 1, "Dread Sovereign Sigil - ring a Crying Obsidian with 4 Ghast Tears and 4 Soul Sand. Use it to summon the Dread Ghast Sovereign (never on peaceful).", "Schreckensf\u00fcrsten-Siegel - Weinender Obsidian mit 4 Ghast-Tr\u00e4nen und 4 Seelensand umringen. Benutzen, um den Schreckens-Ghast-F\u00fcrsten zu beschw\u00f6ren (nie auf Friedlich)."));

		HandbookEntries.add(new HandbookEntry("bosses", "cinder_ghast_matriarch", "copper_inferno:cinder_ghast_matriarch_spawn_egg", null,
				null,
				null, 0, "Cinder Ghast Matriarch - a burning ghast archfiend. Summon: use a Cinder Matriarch Sigil. Every few seconds she rains cinders, igniting every player she can see within 8 blocks; below half health she calls 2 blaze minions and hardens with Resistance. Drops 2-4 Cinder Tearstones, 1-2 Matriarch Ember Sacs and the Matriarch Cinder Diadem.", "Zinder-Ghast-Matriarchin - ein brennender Ghast-Erzd\u00e4mon. Beschw\u00f6rung: ein Zinder-Matriarchin-Siegel benutzen. Alle paar Sekunden regnet sie Zinder und entz\u00fcndet jeden sichtbaren Spieler im Umkreis von 8 Bl\u00f6cken; unter halber Gesundheit ruft sie 2 Lohen-Diener und h\u00e4rtet sich mit Resistenz. L\u00e4sst 2-4 Zinder-Tr\u00e4nensteine, 1-2 Glutbeutel der Matriarchin und das Zinderdiadem der Matriarchin fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "cinder_matriarch_sigil_recipe", "copper_inferno:cinder_matriarch_sigil", "archfiends/cinder_matriarch_sigil",
				new String[] {"minecraft:ghast_tear", "minecraft:magma_cream", "minecraft:ghast_tear", "minecraft:magma_cream", "minecraft:magma_block", "minecraft:magma_cream", "minecraft:ghast_tear", "minecraft:magma_cream", "minecraft:ghast_tear"},
				"copper_inferno:cinder_matriarch_sigil", 1, "Cinder Matriarch Sigil - ring a Magma Block with 4 Ghast Tears and 4 Magma Cream. Use it to summon the Cinder Ghast Matriarch (never on peaceful).", "Zinder-Matriarchin-Siegel - Magmablock mit 4 Ghast-Tr\u00e4nen und 4 Magmacreme umringen. Benutzen, um die Zinder-Ghast-Matriarchin zu beschw\u00f6ren (nie auf Friedlich)."));

		HandbookEntries.add(new HandbookEntry("bosses", "molten_hoglin_tyrant", "copper_inferno:molten_hoglin_tyrant_spawn_egg", null,
				null,
				null, 0, "Molten Hoglin Tyrant - a magma-clad hoglin archfiend that never zombifies. Summon: use a Molten Tyrant Sigil. Its molten stomp ignites and slows every player it can see within 5 blocks; below half health it enrages and speeds up. Drops 2-4 Molten Tusks, 1-2 Seared Tyrant Hides and the Molten Tyrant Idol.", "Schmelz-Hoglin-Tyrann - ein magmagepanzerter Hoglin-Erzd\u00e4mon, der nie zombifiziert. Beschw\u00f6rung: ein Schmelztyrannen-Siegel benutzen. Sein Schmelzstampfer entz\u00fcndet und verlangsamt jeden sichtbaren Spieler im Umkreis von 5 Bl\u00f6cken; unter halber Gesundheit wird er rasend und schneller. L\u00e4sst 2-4 Schmelzhauer, 1-2 Versengte Tyrannenh\u00e4ute und den Schmelztyrannen-G\u00f6tzen fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "molten_tyrant_sigil_recipe", "copper_inferno:molten_tyrant_sigil", "archfiends/molten_tyrant_sigil",
				new String[] {"minecraft:crimson_fungus", "minecraft:magma_cream", "minecraft:crimson_fungus", "minecraft:magma_cream", "minecraft:shroomlight", "minecraft:magma_cream", "minecraft:crimson_fungus", "minecraft:magma_cream", "minecraft:crimson_fungus"},
				"copper_inferno:molten_tyrant_sigil", 1, "Molten Tyrant Sigil - ring a Shroomlight with 4 Crimson Fungus and 4 Magma Cream. Use it to summon the Molten Hoglin Tyrant (never on peaceful).", "Schmelztyrannen-Siegel - Pilzlicht mit 4 Karmesinpilze und 4 Magmacreme umringen. Benutzen, um den Schmelz-Hoglin-Tyrannen zu beschw\u00f6ren (nie auf Friedlich)."));

		HandbookEntries.add(new HandbookEntry("bosses", "ashen_hoglin_gorefiend", "copper_inferno:ashen_hoglin_gorefiend_spawn_egg", null,
				null,
				null, 0, "Ashen Hoglin Gorefiend - an ash-crusted hoglin archfiend. Summon: use an Ashen Gorefiend Sigil. Every few seconds it knits its wounds shut in a burst of ash; below half health it calls 2 hoglin minions and hardens with Resistance. Drops 2-4 Gorefiend Bristles, 1-2 Ashen Gore Hearts and the Gorefiend Ash Totem.", "Aschen-Hoglin-Schl\u00e4chter - ein aschverkrusteter Hoglin-Erzd\u00e4mon. Beschw\u00f6rung: ein Aschenschl\u00e4chter-Siegel benutzen. Alle paar Sekunden schlie\u00dft er seine Wunden in einem Aschesto\u00df; unter halber Gesundheit ruft er 2 Hoglin-Diener und h\u00e4rtet sich mit Resistenz. L\u00e4sst 2-4 Schl\u00e4chterborsten, 1-2 Aschenblutherzen und das Aschentotem des Schl\u00e4chters fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "ashen_gorefiend_sigil_recipe", "copper_inferno:ashen_gorefiend_sigil", "archfiends/ashen_gorefiend_sigil",
				new String[] {"minecraft:bone", "minecraft:crimson_fungus", "minecraft:bone", "minecraft:crimson_fungus", "minecraft:bone_block", "minecraft:crimson_fungus", "minecraft:bone", "minecraft:crimson_fungus", "minecraft:bone"},
				"copper_inferno:ashen_gorefiend_sigil", 1, "Ashen Gorefiend Sigil - ring a Bone Block with 4 Bones and 4 Crimson Fungus. Use it to summon the Ashen Hoglin Gorefiend (never on peaceful).", "Aschenschl\u00e4chter-Siegel - Knochenblock mit 4 Knochen und 4 Karmesinpilze umringen. Benutzen, um den Aschen-Hoglin-Schl\u00e4chter zu beschw\u00f6ren (nie auf Friedlich)."));

		HandbookEntries.add(new HandbookEntry("bosses", "ash_evoker_archon", "copper_inferno:ash_evoker_archon_spawn_egg", null,
				null,
				null, 0, "Ash Evoker Archon - a fang-conjuring illager archfiend. Summon: use an Ash Archon Sigil. On top of its inherited evoker fangs and vexes, its ash pall saps every player it can see within 10 blocks with Mining Fatigue and Weakness; below half health it enrages and speeds up. Drops 2-4 Archon Ash Tomes, 1-2 Ash Rune Shards and the Archon Cinder Mitre.", "Asche-Magier-Archon - ein z\u00e4hnebeschw\u00f6render Illager-Erzd\u00e4mon. Beschw\u00f6rung: ein Aschenarchon-Siegel benutzen. Zus\u00e4tzlich zu seinen ererbten Magier-Z\u00e4hnen und Plagegeistern laugt sein Ascheschleier jeden sichtbaren Spieler im Umkreis von 10 Bl\u00f6cken mit Abbaul\u00e4hmung und Schw\u00e4che aus; unter halber Gesundheit wird er rasend und schneller. L\u00e4sst 2-4 Aschenfolianten des Archons, 1-2 Aschenrunenscherben und die Zindermitra des Archons fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "ash_archon_sigil_recipe", "copper_inferno:ash_archon_sigil", "archfiends/ash_archon_sigil",
				new String[] {"minecraft:coal", "minecraft:emerald", "minecraft:coal", "minecraft:emerald", "minecraft:book", "minecraft:emerald", "minecraft:coal", "minecraft:emerald", "minecraft:coal"},
				"copper_inferno:ash_archon_sigil", 1, "Ash Archon Sigil - ring a Book with 4 Coal and 4 Emeralds. Use it to summon the Ash Evoker Archon (never on peaceful).", "Aschenarchon-Siegel - Buch mit 4 Kohle und 4 Smaragde umringen. Benutzen, um den Asche-Magier-Archon zu beschw\u00f6ren (nie auf Friedlich)."));

		HandbookEntries.add(new HandbookEntry("bosses", "soot_evoker_highlord", "copper_inferno:soot_evoker_highlord_spawn_egg", null,
				null,
				null, 0, "Soot Evoker Highlord - a soot-wreathed illager archfiend. Summon: use a Soot Highlord Sigil. Its soot veil blinds and slows every player it can see within 8 blocks; below half health it hardens with Resistance and quickens permanently. Drops 2-4 Highlord Soot Pearls, 1-2 Soot Grimoire Pages and the Highlord Soot Sceptre.", "Ru\u00df-Magier-Hochf\u00fcrst - ein ru\u00dfumwobener Illager-Erzd\u00e4mon. Beschw\u00f6rung: ein Ru\u00dfhochf\u00fcrsten-Siegel benutzen. Sein Ru\u00dfschleier blendet und verlangsamt jeden sichtbaren Spieler im Umkreis von 8 Bl\u00f6cken; unter halber Gesundheit h\u00e4rtet er sich mit Resistenz und wird dauerhaft schneller. L\u00e4sst 2-4 Ru\u00dfperlen des Hochf\u00fcrsten, 1-2 Ru\u00dfgrimoire-Seiten und das Ru\u00dfzepter des Hochf\u00fcrsten fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "soot_highlord_sigil_recipe", "copper_inferno:soot_highlord_sigil", "archfiends/soot_highlord_sigil",
				new String[] {"minecraft:coal_block", "minecraft:emerald", "minecraft:coal_block", "minecraft:emerald", "minecraft:lapis_block", "minecraft:emerald", "minecraft:coal_block", "minecraft:emerald", "minecraft:coal_block"},
				"copper_inferno:soot_highlord_sigil", 1, "Soot Highlord Sigil - ring a Block of Lapis Lazuli with 4 Coal Blocks and 4 Emeralds. Use it to summon the Soot Evoker Highlord (never on peaceful).", "Ru\u00dfhochf\u00fcrsten-Siegel - Lapislazuliblock mit 4 Kohlebl\u00f6cke und 4 Smaragde umringen. Benutzen, um den Ru\u00df-Magier-Hochf\u00fcrsten zu beschw\u00f6ren (nie auf Friedlich)."));

		HandbookEntries.add(new HandbookEntry("bosses", "cinder_brute_warlord", "copper_inferno:cinder_brute_warlord_spawn_egg", null,
				null,
				null, 0, "Cinder Brute Warlord - a piglin brute archfiend that never zombifies. Summon: use a Cinder Warlord Sigil. Its war cry hastens itself and weakens every player it can see within 6 blocks; below half health its axe arm gains +4 attack damage. Drops 2-4 Warlord Cinder Axeheads, 1-2 Gilded Warlord Tusks and the Warlord War Crown.", "Zinder-Barbar-Kriegsherr - ein Piglin-Barbar-Erzd\u00e4mon, der nie zombifiziert. Beschw\u00f6rung: ein Zinderkriegsherren-Siegel benutzen. Sein Kriegsschrei beschleunigt ihn selbst und schw\u00e4cht jeden sichtbaren Spieler im Umkreis von 6 Bl\u00f6cken; unter halber Gesundheit erh\u00e4lt sein Axtarm +4 Angriffsschaden. L\u00e4sst 2-4 Zinderaxtklingen des Kriegsherrn, 1-2 Vergoldete Kriegsherrenhauer und die Kriegskrone des Kriegsherrn fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "cinder_warlord_sigil_recipe", "copper_inferno:cinder_warlord_sigil", "archfiends/cinder_warlord_sigil",
				new String[] {"minecraft:gold_ingot", "minecraft:blaze_powder", "minecraft:gold_ingot", "minecraft:blaze_powder", "minecraft:gilded_blackstone", "minecraft:blaze_powder", "minecraft:gold_ingot", "minecraft:blaze_powder", "minecraft:gold_ingot"},
				"copper_inferno:cinder_warlord_sigil", 1, "Cinder Warlord Sigil - ring a Gilded Blackstone with 4 Gold Ingots and 4 Blaze Powder. Use it to summon the Cinder Brute Warlord (never on peaceful).", "Zinderkriegsherren-Siegel - Vergoldeter Schwarzstein mit 4 Goldbarren und 4 Lohenstaub umringen. Benutzen, um den Zinder-Barbar-Kriegsherrn zu beschw\u00f6ren (nie auf Friedlich)."));

		HandbookEntries.add(new HandbookEntry("bosses", "gilded_brute_executioner", "copper_inferno:gilded_brute_executioner_spawn_egg", null,
				null,
				null, 0, "Gilded Brute Executioner - a gold-plated piglin brute archfiend that never zombifies. Summon: use a Gilded Executioner Sigil. Below half health it calls 2 axe-wielding piglin brute minions, hardens with Resistance and quickens permanently. Drops 2-4 Gilded Executioner Plates, 1-2 Executioner Chain Links and the Executioner Gold Visage.", "Vergoldeter Barbar-Scharfrichter - ein goldgepanzerter Piglin-Barbar-Erzd\u00e4mon, der nie zombifiziert. Beschw\u00f6rung: ein Scharfrichter-Siegel benutzen. Unter halber Gesundheit ruft er 2 axtschwingende Piglin-Barbar-Diener, h\u00e4rtet sich mit Resistenz und wird dauerhaft schneller. L\u00e4sst 2-4 Vergoldete Scharfrichterplatten, 1-2 Scharfrichter-Kettenglieder und das Goldantlitz des Scharfrichters fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "gilded_executioner_sigil_recipe", "copper_inferno:gilded_executioner_sigil", "archfiends/gilded_executioner_sigil",
				new String[] {"minecraft:gold_block", "minecraft:gold_ingot", "minecraft:gold_block", "minecraft:gold_ingot", "minecraft:golden_axe", "minecraft:gold_ingot", "minecraft:gold_block", "minecraft:gold_ingot", "minecraft:gold_block"},
				"copper_inferno:gilded_executioner_sigil", 1, "Gilded Executioner Sigil - ring a Golden Axe with 4 Gold Blocks and 4 Gold Ingots. Use it to summon the Gilded Brute Executioner (never on peaceful).", "Scharfrichter-Siegel - Goldaxt mit 4 Goldbl\u00f6cke und 4 Goldbarren umringen. Benutzen, um den Vergoldeten Barbar-Scharfrichter zu beschw\u00f6ren (nie auf Friedlich)."));

		HandbookEntries.add(new HandbookEntry("bosses", "slag_wither_monarch", "copper_inferno:slag_wither_monarch_spawn_egg", null,
				null,
				null, 0, "Slag Wither Monarch - a slag-armored wither skeleton archfiend wielding a stone sword. Summon: use a Slag Monarch Sigil. Its withering aura decays and slows every player it can see within 6 blocks; below half health it enrages and speeds up. Drops 2-4 Monarch Slag Ribs, 1-2 Withered Slag Chunks and the Monarch Wither Crown.", "Schlacken-Wither-Monarch - ein schlackengepanzerter Witherskelett-Erzd\u00e4mon mit Steinschwert. Beschw\u00f6rung: ein Schlackenmonarchen-Siegel benutzen. Seine Wither-Aura zersetzt und verlangsamt jeden sichtbaren Spieler im Umkreis von 6 Bl\u00f6cken; unter halber Gesundheit wird er rasend und schneller. L\u00e4sst 2-4 Schlackenrippen des Monarchen, 1-2 Verdorrte Schlackenbrocken und die Witherkrone des Monarchen fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "slag_monarch_sigil_recipe", "copper_inferno:slag_monarch_sigil", "archfiends/slag_monarch_sigil",
				new String[] {"minecraft:bone", "minecraft:coal", "minecraft:bone", "minecraft:coal", "minecraft:wither_skeleton_skull", "minecraft:coal", "minecraft:bone", "minecraft:coal", "minecraft:bone"},
				"copper_inferno:slag_monarch_sigil", 1, "Slag Monarch Sigil - ring a Wither Skeleton Skull with 4 Bones and 4 Coal. Use it to summon the Slag Wither Monarch (never on peaceful).", "Schlackenmonarchen-Siegel - Witherskelettsch\u00e4del mit 4 Knochen und 4 Kohle umringen. Benutzen, um den Schlacken-Wither-Monarchen zu beschw\u00f6ren (nie auf Friedlich)."));

		HandbookEntries.add(new HandbookEntry("bosses", "blight_wither_emperor", "copper_inferno:blight_wither_emperor_spawn_egg", null,
				null,
				null, 0, "Blight Wither Emperor - an iron-sworded wither skeleton archfiend. Summon: use a Blight Emperor Sigil. Below half health it calls 2 sword-bearing wither skeleton minions, hardens with Resistance and its blade gains +4 attack damage. Drops 2-4 Blight Bone Shards, 1-2 Emperor Blight Marrows and the Emperor Blight Diadem.", "Seuchen-Wither-Imperator - ein Witherskelett-Erzd\u00e4mon mit Eisenschwert. Beschw\u00f6rung: ein Seuchenimperator-Siegel benutzen. Unter halber Gesundheit ruft er 2 schwerttragende Witherskelett-Diener, h\u00e4rtet sich mit Resistenz und seine Klinge erh\u00e4lt +4 Angriffsschaden. L\u00e4sst 2-4 Seuchenknochensplitter, 1-2 Seuchenmark des Imperators und das Seuchendiadem des Imperators fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "blight_emperor_sigil_recipe", "copper_inferno:blight_emperor_sigil", "archfiends/blight_emperor_sigil",
				new String[] {"minecraft:coal_block", "minecraft:bone", "minecraft:coal_block", "minecraft:bone", "minecraft:wither_skeleton_skull", "minecraft:bone", "minecraft:coal_block", "minecraft:bone", "minecraft:coal_block"},
				"copper_inferno:blight_emperor_sigil", 1, "Blight Emperor Sigil - ring a Wither Skeleton Skull with 4 Coal Blocks and 4 Bones. Use it to summon the Blight Wither Emperor (never on peaceful).", "Seuchenimperator-Siegel - Witherskelettsch\u00e4del mit 4 Kohlebl\u00f6cke und 4 Knochen umringen. Benutzen, um den Seuchen-Wither-Imperator zu beschw\u00f6ren (nie auf Friedlich)."));
	}
}
