package net.sonic0810.copperinferno.feature.gemalloy;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Gems &amp; Alloys wave: one curated "items" entry per material,
 * documenting the raw-chunk smelting recipe (the entry grid mirrors
 * {@code data/copper_inferno/recipe/gemalloy/<m>_ingot_from_smelting_raw_<m>.json}). Keep the
 * {@code new HandbookEntry(...)} literals inline and positional.
 */
final class GemAlloyHandbook {
	private GemAlloyHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("items", "gemalloy_pyrium",
				"copper_inferno:pyrium_ingot", "gemalloy/pyrium_ingot_from_smelting_raw_pyrium",
				new String[]{"", "", "", "", "copper_inferno:raw_pyrium", "", "", "", ""},
				"copper_inferno:pyrium_ingot", 1,
				"Pyrium glints like trapped fire. Smelt Raw Pyrium (or any Pyrium Ore) into ingots, then build the full brick, tile, lamp and glass family from Pyrium Blocks.",
				"Pyrium glitzert wie eingeschlossenes Feuer. Schmilz Roh-Pyrium (oder jedes Pyrium-Erz) zu Barren und baue aus Pyrium-Bl\u00f6cken die komplette Ziegel-, Fliesen-, Lampen- und Glasfamilie."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_emberite",
				"copper_inferno:emberite_ingot", "gemalloy/emberite_ingot_from_smelting_raw_emberite",
				new String[]{"", "", "", "", "copper_inferno:raw_emberite", "", "", "", ""},
				"copper_inferno:emberite_ingot", 1,
				"Emberite stays furnace-warm long after casting. Smelt Raw Emberite into ingots; its ores also hide in the Inferno's cinderstone.",
				"Emberit bleibt lange nach dem Guss ofenwarm. Schmilz Roh-Emberit zu Barren; sein Erz versteckt sich auch im Zunderstein des Infernos."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_cindralite",
				"copper_inferno:cindralite_ingot", "gemalloy/cindralite_ingot_from_smelting_raw_cindralite",
				new String[]{"", "", "", "", "copper_inferno:raw_cindralite", "", "", "", ""},
				"copper_inferno:cindralite_ingot", 1,
				"Cindralite is quenched cinder pressed into rosy metal. Smelt Raw Cindralite into ingots for warm-toned masonry.",
				"Cindralit ist abgeschreckte Asche, zu rosigem Metall gepresst. Schmilz Roh-Cindralit zu Barren f\u00fcr warmt\u00f6niges Mauerwerk."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_slagbronze",
				"copper_inferno:slagbronze_ingot", "gemalloy/slagbronze_ingot_from_smelting_raw_slagbronze",
				new String[]{"", "", "", "", "copper_inferno:raw_slagbronze", "", "", "", ""},
				"copper_inferno:slagbronze_ingot", 1,
				"Slagbronze is smelted straight out of furnace waste. Turn Raw Slagbronze into ingots, nuggets and sturdy storage blocks.",
				"Schlackenbronze wird direkt aus Ofenabfall erschmolzen. Verwandle Roh-Slagbronze in Barren, Klumpen und robuste Lagerbl\u00f6cke."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_ashsteel",
				"copper_inferno:ashsteel_ingot", "gemalloy/ashsteel_ingot_from_smelting_raw_ashsteel",
				new String[]{"", "", "", "", "copper_inferno:raw_ashsteel", "", "", "", ""},
				"copper_inferno:ashsteel_ingot", 1,
				"Ashsteel is pale, hard and utterly heat-proof. Smelt Raw Ashsteel into ingots; the grate and glass shapes suit industrial builds.",
				"Aschenstahl ist blass, hart und v\u00f6llig hitzefest. Schmilz Roh-Aschenstahl zu Barren; Gitter- und Glasformen passen zu Industriebauten."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_voidsteel",
				"copper_inferno:voidsteel_ingot", "gemalloy/voidsteel_ingot_from_smelting_raw_voidsteel",
				new String[]{"", "", "", "", "copper_inferno:raw_voidsteel", "", "", "", ""},
				"copper_inferno:voidsteel_ingot", 1,
				"Voidsteel drinks the light around it. Smelt Raw Voidsteel into ingots and cast deep-purple pillars, bulbs and lanterns.",
				"Leerenstahl schluckt das Licht um sich herum. Schmilz Roh-Leerenstahl zu Barren und gie\u00dfe tiefviolette S\u00e4ulen, Gl\u00fchlampen und Laternen."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_doomium",
				"copper_inferno:doomium_ingot", "gemalloy/doomium_ingot_from_smelting_raw_doomium",
				new String[]{"", "", "", "", "copper_inferno:raw_doomium", "", "", "", ""},
				"copper_inferno:doomium_ingot", 1,
				"Doomium hums with a menacing bass line. Smelt Raw Doomium into crimson ingots — rip and tear your masonry into shape.",
				"Doomium brummt mit bedrohlicher Basslinie. Schmilz Roh-Doomium zu blutroten Barren — und bring dein Mauerwerk gnadenlos in Form."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_pepperite",
				"copper_inferno:pepperite_ingot", "gemalloy/pepperite_ingot_from_smelting_raw_pepperite",
				new String[]{"", "", "", "", "copper_inferno:raw_pepperite", "", "", "", ""},
				"copper_inferno:pepperite_ingot", 1,
				"Pepperite carries the deep burgundy of a certain 23-flavor soda. Smelt Raw Pepperite into ingots for fizzy-dark builds.",
				"Pepperit tr\u00e4gt das tiefe Burgunderrot einer gewissen Limonade mit 23 Geschmacksrichtungen. Schmilz Roh-Pepperit zu Barren f\u00fcr sprudelig-dunkle Bauten."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_fizzium",
				"copper_inferno:fizzium_ingot", "gemalloy/fizzium_ingot_from_smelting_raw_fizzium",
				new String[]{"", "", "", "", "copper_inferno:raw_fizzium", "", "", "", ""},
				"copper_inferno:fizzium_ingot", 1,
				"Fizzium tingles on the tongue and sparkles in the wall. Smelt Raw Fizzium into amber ingots; its glass keeps the bubbles visible.",
				"Fizzium prickelt auf der Zunge und funkelt in der Wand. Schmilz Roh-Fizzium zu Bernsteinbarren; sein Glas l\u00e4sst die Bl\u00e4schen sichtbar."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_vitrium",
				"copper_inferno:vitrium_ingot", "gemalloy/vitrium_ingot_from_smelting_raw_vitrium",
				new String[]{"", "", "", "", "copper_inferno:raw_vitrium", "", "", "", ""},
				"copper_inferno:vitrium_ingot", 1,
				"Vitrium is metal on the verge of becoming glass. Smelt Raw Vitrium into teal ingots — the glass and pane shapes are its true calling.",
				"Vitrium ist Metall kurz davor, Glas zu werden. Schmilz Roh-Vitrium zu t\u00fcrkisen Barren — Glas und Scheiben sind seine wahre Bestimmung."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_smokequartz",
				"copper_inferno:smokequartz_ingot", "gemalloy/smokequartz_ingot_from_smelting_raw_smokequartz",
				new String[]{"", "", "", "", "copper_inferno:raw_smokequartz", "", "", "", ""},
				"copper_inferno:smokequartz_ingot", 1,
				"Smokequartz traps a wisp of chimney smoke in every crystal. Smelt Raw Smokequartz into ingots for muted, moody stonework.",
				"Rauchquarz f\u00e4ngt in jedem Kristall eine Schwade Schornsteinrauch ein. Schmilz Roh-Rauchquarz zu Barren f\u00fcr ged\u00e4mpftes, stimmungsvolles Mauerwerk."));

		HandbookEntries.add(new HandbookEntry("items", "gemalloy_kilnite",
				"copper_inferno:kilnite_ingot", "gemalloy/kilnite_ingot_from_smelting_raw_kilnite",
				new String[]{"", "", "", "", "copper_inferno:raw_kilnite", "", "", "", ""},
				"copper_inferno:kilnite_ingot", 1,
				"Kilnite is terracotta's metallic cousin, born in overworked kilns. Smelt Raw Kilnite into ingots for warm earthen builds.",
				"Kilnit ist der metallische Vetter der Keramik, geboren in \u00fcberhitzten Brenn\u00f6fen. Schmilz Roh-Kilnit zu Barren f\u00fcr warme, erdige Bauten."));
	}
}
