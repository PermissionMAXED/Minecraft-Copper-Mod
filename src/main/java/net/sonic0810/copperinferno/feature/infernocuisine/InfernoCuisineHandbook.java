package net.sonic0810.copperinferno.feature.infernocuisine;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Inferno Cuisine feature: one "items" overview plus one
 * recipe page for every JSON under {@code data/copper_inferno/recipe/infernocuisine/}
 * (crafting, smelting and campfire cooking). Entry texts and grids mirror the recipe
 * JSONs emitted by {@code devtools/gen/infernocuisine_gen.py};
 * {@code devtools/check_handbook.py} parses the inline {@code new HandbookEntry(...)}
 * literals positionally, so keep them inline.
 */
final class InfernoCuisineHandbook {
	private InfernoCuisineHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_overview", "copper_inferno:cinder_pie", null,
				null,
				null, 0, "The Inferno kitchen serves 100 dishes and drinks: bottled sodas and brews in returnable bottles, steaming bowl meals (the bowl comes back), jams and syrups, raw cuts that roast in furnace or campfire, pastries, candies, hearty snacks and fire-kissed fruit.", "Die Inferno-K\u00fcche serviert 100 Gerichte und Getr\u00e4nke: Limonaden und Sude in Pfandflaschen, dampfende Sch\u00fcsselgerichte (die Sch\u00fcssel kommt zur\u00fcck), Marmeladen und Sirupe, rohe St\u00fccke zum R\u00f6sten in Ofen oder Lagerfeuer, Geb\u00e4ck, S\u00fc\u00dfigkeiten, deftige Snacks und feuergek\u00fcsste Fr\u00fcchte."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_cola", "copper_inferno:magma_cola", "infernocuisine/magma_cola",
				new String[] {"minecraft:glass_bottle", "minecraft:sugar", "minecraft:magma_cream", "", "", "", "", "", ""},
				"copper_inferno:magma_cola", 1, "Craft 1x Magma Cola at a crafting table.", "Stellt 1x Magma-Cola an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_tea", "copper_inferno:ember_tea", "infernocuisine/ember_tea",
				new String[] {"minecraft:glass_bottle", "minecraft:dried_kelp", "copper_inferno:ember_dust", "", "", "", "", "", ""},
				"copper_inferno:ember_tea", 1, "Craft 1x Ember Tea at a crafting table.", "Stellt 1x Gluttee an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_lava_latte", "copper_inferno:lava_latte", "infernocuisine/lava_latte",
				new String[] {"minecraft:glass_bottle", "minecraft:milk_bucket", "minecraft:blaze_powder", "", "", "", "", "", ""},
				"copper_inferno:lava_latte", 1, "Craft 1x Lava Latte at a crafting table.", "Stellt 1x Lava-Milchkaffee an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_cider", "copper_inferno:cinder_cider", "infernocuisine/cinder_cider",
				new String[] {"minecraft:glass_bottle", "minecraft:apple", "copper_inferno:ash_pile", "", "", "", "", "", ""},
				"copper_inferno:cinder_cider", 1, "Craft 1x Cinder Cider at a crafting table.", "Stellt 1x Zunder-Apfelmost an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_soot_smoothie", "copper_inferno:soot_smoothie", "infernocuisine/soot_smoothie",
				new String[] {"minecraft:glass_bottle", "minecraft:sweet_berries", "minecraft:charcoal", "", "", "", "", "", ""},
				"copper_inferno:soot_smoothie", 1, "Craft 1x Soot Smoothie at a crafting table.", "Stellt 1x Ru\u00df-Smoothie an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_blaze_brew", "copper_inferno:blaze_brew", "infernocuisine/blaze_brew",
				new String[] {"minecraft:glass_bottle", "minecraft:blaze_powder", "minecraft:nether_wart", "", "", "", "", "", ""},
				"copper_inferno:blaze_brew", 1, "Craft 1x Blaze Brew at a crafting table.", "Stellt 1x Lohentrunk an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_flame_nectar", "copper_inferno:flame_nectar", "infernocuisine/flame_nectar",
				new String[] {"minecraft:glass_bottle", "minecraft:honey_bottle", "minecraft:blaze_powder", "", "", "", "", "", ""},
				"copper_inferno:flame_nectar", 1, "Craft 1x Flame Nectar at a crafting table.", "Stellt 1x Flammennektar an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ashen_ale", "copper_inferno:ashen_ale", "infernocuisine/ashen_ale",
				new String[] {"minecraft:glass_bottle", "minecraft:wheat", "copper_inferno:ash_pile", "", "", "", "", "", ""},
				"copper_inferno:ashen_ale", 1, "Craft 1x Ashen Ale at a crafting table.", "Stellt 1x Aschen-Ale an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_molten_mocha", "copper_inferno:molten_mocha", "infernocuisine/molten_mocha",
				new String[] {"minecraft:glass_bottle", "minecraft:cocoa_beans", "minecraft:magma_cream", "", "", "", "", "", ""},
				"copper_inferno:molten_mocha", 1, "Craft 1x Molten Mocha at a crafting table.", "Stellt 1x Geschmolzenen Mokka an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_espresso", "copper_inferno:ember_espresso", "infernocuisine/ember_espresso",
				new String[] {"minecraft:glass_bottle", "minecraft:cocoa_beans", "copper_inferno:ember_dust", "", "", "", "", "", ""},
				"copper_inferno:ember_espresso", 1, "Craft 1x Ember Espresso at a crafting table.", "Stellt 1x Glut-Espresso an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_inferno_punch", "copper_inferno:inferno_punch", "infernocuisine/inferno_punch",
				new String[] {"minecraft:glass_bottle", "minecraft:glow_berries", "minecraft:sweet_berries", "minecraft:blaze_powder", "", "", "", "", ""},
				"copper_inferno:inferno_punch", 1, "Craft 1x Inferno Punch at a crafting table.", "Stellt 1x Inferno-Punsch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_smoke_soda", "copper_inferno:smoke_soda", "infernocuisine/smoke_soda",
				new String[] {"minecraft:glass_bottle", "minecraft:sugar", "minecraft:charcoal", "", "", "", "", "", ""},
				"copper_inferno:smoke_soda", 1, "Craft 1x Smoke Soda at a crafting table.", "Stellt 1x Rauchbrause an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_slag_shake", "copper_inferno:slag_shake", "infernocuisine/slag_shake",
				new String[] {"minecraft:glass_bottle", "minecraft:milk_bucket", "copper_inferno:slag_chunk", "", "", "", "", "", ""},
				"copper_inferno:slag_shake", 1, "Craft 1x Slag Shake at a crafting table.", "Stellt 1x Schlacken-Shake an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_obsidian_oolong", "copper_inferno:obsidian_oolong", "infernocuisine/obsidian_oolong",
				new String[] {"minecraft:glass_bottle", "minecraft:dried_kelp", "minecraft:obsidian", "", "", "", "", "", ""},
				"copper_inferno:obsidian_oolong", 1, "Craft 1x Obsidian Oolong at a crafting table.", "Stellt 1x Obsidian-Oolong an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_charcoal_lemonade", "copper_inferno:charcoal_lemonade", "infernocuisine/charcoal_lemonade",
				new String[] {"minecraft:glass_bottle", "minecraft:sugar", "minecraft:charcoal", "minecraft:glow_berries", "", "", "", "", ""},
				"copper_inferno:charcoal_lemonade", 1, "Craft 1x Charcoal Lemonade at a crafting table.", "Stellt 1x Holzkohlen-Limonade an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_soup", "copper_inferno:magma_soup", "infernocuisine/magma_soup",
				new String[] {"minecraft:bowl", "minecraft:magma_cream", "minecraft:red_mushroom", "", "", "", "", "", ""},
				"copper_inferno:magma_soup", 1, "Craft 1x Magma Soup at a crafting table.", "Stellt 1x Magmasuppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_slag_stew", "copper_inferno:slag_stew", "infernocuisine/slag_stew",
				new String[] {"minecraft:bowl", "copper_inferno:slag_chunk", "minecraft:potato", "minecraft:carrot", "", "", "", "", ""},
				"copper_inferno:slag_stew", 1, "Craft 1x Slag Stew at a crafting table.", "Stellt 1x Schlackeneintopf an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_chowder", "copper_inferno:cinder_chowder", "infernocuisine/cinder_chowder",
				new String[] {"minecraft:bowl", "minecraft:cod", "copper_inferno:ash_pile", "", "", "", "", "", ""},
				"copper_inferno:cinder_chowder", 1, "Craft 1x Cinder Chowder at a crafting table.", "Stellt 1x Zunder-Fischsuppe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_blaze_broth", "copper_inferno:blaze_broth", "infernocuisine/blaze_broth",
				new String[] {"minecraft:bowl", "minecraft:blaze_powder", "minecraft:chicken", "", "", "", "", "", ""},
				"copper_inferno:blaze_broth", 1, "Craft 1x Blaze Broth at a crafting table.", "Stellt 1x Lohenbr\u00fche an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_porridge", "copper_inferno:ember_porridge", "infernocuisine/ember_porridge",
				new String[] {"minecraft:bowl", "minecraft:wheat", "copper_inferno:ember_dust", "", "", "", "", "", ""},
				"copper_inferno:ember_porridge", 1, "Craft 1x Ember Porridge at a crafting table.", "Stellt 1x Glutbrei an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_gruel", "copper_inferno:ash_gruel", "infernocuisine/ash_gruel",
				new String[] {"minecraft:bowl", "minecraft:wheat", "copper_inferno:ash_pile", "", "", "", "", "", ""},
				"copper_inferno:ash_gruel", 1, "Craft 1x Ash Gruel at a crafting table.", "Stellt 1x Aschengr\u00fctze an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_lava_ramen", "copper_inferno:lava_ramen", "infernocuisine/lava_ramen",
				new String[] {"minecraft:bowl", "copper_inferno:lava_noodles", "minecraft:blaze_powder", "", "", "", "", "", ""},
				"copper_inferno:lava_ramen", 1, "Craft 1x Lava Ramen at a crafting table.", "Stellt 1x Lava-Ramen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_coal_curry", "copper_inferno:coal_curry", "infernocuisine/coal_curry",
				new String[] {"minecraft:bowl", "minecraft:charcoal", "minecraft:potato", "minecraft:carrot", "", "", "", "", ""},
				"copper_inferno:coal_curry", 1, "Craft 1x Coal Curry at a crafting table.", "Stellt 1x Kohlencurry an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_scorched_goulash", "copper_inferno:scorched_goulash", "infernocuisine/scorched_goulash",
				new String[] {"minecraft:bowl", "minecraft:beef", "minecraft:beetroot", "", "", "", "", "", ""},
				"copper_inferno:scorched_goulash", 1, "Craft 1x Scorched Goulash at a crafting table.", "Stellt 1x Versengtes Gulasch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_furnace_fondue", "copper_inferno:furnace_fondue", "infernocuisine/furnace_fondue",
				new String[] {"minecraft:bowl", "minecraft:milk_bucket", "copper_inferno:ember_dust", "", "", "", "", "", ""},
				"copper_inferno:furnace_fondue", 1, "Craft 1x Furnace Fondue at a crafting table.", "Stellt 1x Ofenfondue an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_marmalade", "copper_inferno:magma_marmalade", "infernocuisine/magma_marmalade",
				new String[] {"minecraft:glass_bottle", "minecraft:glow_berries", "minecraft:glow_berries", "minecraft:sugar", "", "", "", "", ""},
				"copper_inferno:magma_marmalade", 1, "Craft 1x Magma Marmalade at a crafting table.", "Stellt 1x Magma-Marmelade an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_honey", "copper_inferno:ember_honey", "infernocuisine/ember_honey",
				new String[] {"minecraft:glass_bottle", "minecraft:honey_bottle", "copper_inferno:ember_dust", "", "", "", "", "", ""},
				"copper_inferno:ember_honey", 1, "Craft 1x Ember Honey at a crafting table.", "Stellt 1x Gluthonig an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_syrup", "copper_inferno:cinder_syrup", "infernocuisine/cinder_syrup",
				new String[] {"minecraft:glass_bottle", "minecraft:sugar", "minecraft:sugar", "copper_inferno:ash_pile", "", "", "", "", ""},
				"copper_inferno:cinder_syrup", 1, "Craft 1x Cinder Syrup at a crafting table.", "Stellt 1x Zundersirup an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_yogurt", "copper_inferno:ash_yogurt", "infernocuisine/ash_yogurt",
				new String[] {"minecraft:glass_bottle", "minecraft:milk_bucket", "copper_inferno:ash_pile", "", "", "", "", "", ""},
				"copper_inferno:ash_yogurt", 1, "Craft 1x Ash Yogurt at a crafting table.", "Stellt 1x Asche-Joghurt an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_flame_chutney", "copper_inferno:flame_chutney", "infernocuisine/flame_chutney",
				new String[] {"minecraft:glass_bottle", "minecraft:apple", "minecraft:blaze_powder", "minecraft:sugar", "", "", "", "", ""},
				"copper_inferno:flame_chutney", 1, "Craft 1x Flame Chutney at a crafting table.", "Stellt 1x Flammen-Chutney an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_kimchi", "copper_inferno:cinder_kimchi", "infernocuisine/cinder_kimchi",
				new String[] {"minecraft:glass_bottle", "minecraft:beetroot", "copper_inferno:ash_pile", "minecraft:sugar", "", "", "", "", ""},
				"copper_inferno:cinder_kimchi", 1, "Craft 1x Cinder Kimchi at a crafting table.", "Stellt 1x Zunder-Kimchi an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_salsa", "copper_inferno:magma_salsa", "infernocuisine/magma_salsa",
				new String[] {"minecraft:glass_bottle", "minecraft:beetroot", "minecraft:magma_cream", "minecraft:blaze_powder", "", "", "", "", ""},
				"copper_inferno:magma_salsa", 1, "Craft 1x Magma Salsa at a crafting table.", "Stellt 1x Magma-Salsa an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_raw_ember_sausage", "copper_inferno:raw_ember_sausage", "infernocuisine/raw_ember_sausage",
				new String[] {"minecraft:porkchop", "copper_inferno:ember_dust", "minecraft:string", "", "", "", "", "", ""},
				"copper_inferno:raw_ember_sausage", 2, "Craft 2x Raw Ember Sausage at a crafting table.", "Stellt 2x Rohe Glutwurst an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_raw_magma_steak", "copper_inferno:raw_magma_steak", "infernocuisine/raw_magma_steak",
				new String[] {"minecraft:beef", "minecraft:magma_cream", "", "", "", "", "", "", ""},
				"copper_inferno:raw_magma_steak", 1, "Craft 1x Raw Magma Steak at a crafting table.", "Stellt 1x Rohes Magmasteak an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_raw_cinder_bacon", "copper_inferno:raw_cinder_bacon", "infernocuisine/raw_cinder_bacon",
				new String[] {"minecraft:porkchop", "copper_inferno:ash_pile", "", "", "", "", "", "", ""},
				"copper_inferno:raw_cinder_bacon", 2, "Craft 2x Raw Cinder Bacon at a crafting table.", "Stellt 2x Rohen Zunderspeck an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_raw_slag_ribs", "copper_inferno:raw_slag_ribs", "infernocuisine/raw_slag_ribs",
				new String[] {"minecraft:mutton", "copper_inferno:slag_chunk", "", "", "", "", "", "", ""},
				"copper_inferno:raw_slag_ribs", 1, "Craft 1x Raw Slag Ribs at a crafting table.", "Stellt 1x Rohe Schlackenrippchen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_raw_ash_fillet", "copper_inferno:raw_ash_fillet", "infernocuisine/raw_ash_fillet",
				new String[] {"minecraft:cod", "copper_inferno:ash_pile", "", "", "", "", "", "", ""},
				"copper_inferno:raw_ash_fillet", 1, "Craft 1x Raw Ash Fillet at a crafting table.", "Stellt 1x Rohes Aschenfilet an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_raw_lava_eel", "copper_inferno:raw_lava_eel", "infernocuisine/raw_lava_eel",
				new String[] {"minecraft:salmon", "minecraft:magma_cream", "", "", "", "", "", "", ""},
				"copper_inferno:raw_lava_eel", 1, "Craft 1x Raw Lava Eel at a crafting table.", "Stellt 1x Rohen Lava-Aal an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_dough", "copper_inferno:ember_dough", "infernocuisine/ember_dough",
				new String[] {"minecraft:wheat", "minecraft:wheat", "copper_inferno:ember_dust", "", "", "", "", "", ""},
				"copper_inferno:ember_dough", 2, "Craft 2x Ember Dough at a crafting table.", "Stellt 2x Glutteig an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_batter", "copper_inferno:cinder_batter", "infernocuisine/cinder_batter",
				new String[] {"minecraft:wheat", "minecraft:egg", "minecraft:milk_bucket", "copper_inferno:ash_pile", "", "", "", "", ""},
				"copper_inferno:cinder_batter", 2, "Craft 2x Cinder Batter at a crafting table.", "Stellt 2x Zunder-Backteig an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_kernels", "copper_inferno:magma_kernels", "infernocuisine/magma_kernels",
				new String[] {"minecraft:wheat_seeds", "minecraft:magma_cream", "", "", "", "", "", "", ""},
				"copper_inferno:magma_kernels", 2, "Craft 2x Magma Kernels at a crafting table.", "Stellt 2x Magmak\u00f6rner an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_molten_marshmallow", "copper_inferno:molten_marshmallow", "infernocuisine/molten_marshmallow",
				new String[] {"minecraft:sugar", "minecraft:sugar", "minecraft:slime_ball", "minecraft:blaze_powder", "", "", "", "", ""},
				"copper_inferno:molten_marshmallow", 3, "Craft 3x Molten Marshmallow at a crafting table.", "Stellt 3x Geschmolzenes Marshmallow an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_raw_blaze_chop", "copper_inferno:raw_blaze_chop", "infernocuisine/raw_blaze_chop",
				new String[] {"minecraft:porkchop", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:raw_blaze_chop", 1, "Craft 1x Raw Blaze Chop at a crafting table.", "Stellt 1x Rohes Lohenkotelett an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_soot_spud", "copper_inferno:soot_spud", "infernocuisine/soot_spud",
				new String[] {"minecraft:potato", "minecraft:charcoal", "", "", "", "", "", "", ""},
				"copper_inferno:soot_spud", 2, "Craft 2x Soot Spud at a crafting table.", "Stellt 2x Ru\u00dfkartoffel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_sausage", "copper_inferno:ember_sausage", "infernocuisine/ember_sausage",
				new String[] {"", "", "", "", "copper_inferno:raw_ember_sausage", "", "", "", ""},
				"copper_inferno:ember_sausage", 1, "Smelt Raw Ember Sausage in a furnace into Ember Sausage.", "Rohe Glutwurst im Ofen garen, um Glutwurst zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_sausage_from_campfire_cooking", "copper_inferno:ember_sausage", "infernocuisine/ember_sausage_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:raw_ember_sausage", "", "", "", ""},
				"copper_inferno:ember_sausage", 1, "A campfire also cooks Raw Ember Sausage - slower, but without fuel.", "Auch das Lagerfeuer gart Rohe Glutwurst - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_steak", "copper_inferno:magma_steak", "infernocuisine/magma_steak",
				new String[] {"", "", "", "", "copper_inferno:raw_magma_steak", "", "", "", ""},
				"copper_inferno:magma_steak", 1, "Smelt Raw Magma Steak in a furnace into Magma Steak.", "Rohes Magmasteak im Ofen garen, um Magmasteak zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_steak_from_campfire_cooking", "copper_inferno:magma_steak", "infernocuisine/magma_steak_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:raw_magma_steak", "", "", "", ""},
				"copper_inferno:magma_steak", 1, "A campfire also cooks Raw Magma Steak - slower, but without fuel.", "Auch das Lagerfeuer gart Rohes Magmasteak - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_bacon", "copper_inferno:cinder_bacon", "infernocuisine/cinder_bacon",
				new String[] {"", "", "", "", "copper_inferno:raw_cinder_bacon", "", "", "", ""},
				"copper_inferno:cinder_bacon", 1, "Smelt Raw Cinder Bacon in a furnace into Cinder Bacon.", "Rohen Zunderspeck im Ofen garen, um Zunderspeck zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_bacon_from_campfire_cooking", "copper_inferno:cinder_bacon", "infernocuisine/cinder_bacon_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:raw_cinder_bacon", "", "", "", ""},
				"copper_inferno:cinder_bacon", 1, "A campfire also cooks Raw Cinder Bacon - slower, but without fuel.", "Auch das Lagerfeuer gart Rohen Zunderspeck - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_slag_ribs", "copper_inferno:slag_ribs", "infernocuisine/slag_ribs",
				new String[] {"", "", "", "", "copper_inferno:raw_slag_ribs", "", "", "", ""},
				"copper_inferno:slag_ribs", 1, "Smelt Raw Slag Ribs in a furnace into Slag Ribs.", "Rohe Schlackenrippchen im Ofen garen, um Schlackenrippchen zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_slag_ribs_from_campfire_cooking", "copper_inferno:slag_ribs", "infernocuisine/slag_ribs_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:raw_slag_ribs", "", "", "", ""},
				"copper_inferno:slag_ribs", 1, "A campfire also cooks Raw Slag Ribs - slower, but without fuel.", "Auch das Lagerfeuer gart Rohe Schlackenrippchen - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_fillet", "copper_inferno:ash_fillet", "infernocuisine/ash_fillet",
				new String[] {"", "", "", "", "copper_inferno:raw_ash_fillet", "", "", "", ""},
				"copper_inferno:ash_fillet", 1, "Smelt Raw Ash Fillet in a furnace into Ash Fillet.", "Rohes Aschenfilet im Ofen garen, um Aschenfilet zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_fillet_from_campfire_cooking", "copper_inferno:ash_fillet", "infernocuisine/ash_fillet_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:raw_ash_fillet", "", "", "", ""},
				"copper_inferno:ash_fillet", 1, "A campfire also cooks Raw Ash Fillet - slower, but without fuel.", "Auch das Lagerfeuer gart Rohes Aschenfilet - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_grilled_lava_eel", "copper_inferno:grilled_lava_eel", "infernocuisine/grilled_lava_eel",
				new String[] {"", "", "", "", "copper_inferno:raw_lava_eel", "", "", "", ""},
				"copper_inferno:grilled_lava_eel", 1, "Smelt Raw Lava Eel in a furnace into Grilled Lava Eel.", "Rohen Lava-Aal im Ofen garen, um Gegrillten Lava-Aal zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_grilled_lava_eel_from_campfire_cooking", "copper_inferno:grilled_lava_eel", "infernocuisine/grilled_lava_eel_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:raw_lava_eel", "", "", "", ""},
				"copper_inferno:grilled_lava_eel", 1, "A campfire also cooks Raw Lava Eel - slower, but without fuel.", "Auch das Lagerfeuer gart Rohen Lava-Aal - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_biscuit", "copper_inferno:ash_biscuit", "infernocuisine/ash_biscuit",
				new String[] {"", "", "", "", "copper_inferno:ember_dough", "", "", "", ""},
				"copper_inferno:ash_biscuit", 1, "Smelt Ember Dough in a furnace into Ash Biscuit.", "Glutteig im Ofen garen, um Aschenkeks zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_biscuit_from_campfire_cooking", "copper_inferno:ash_biscuit", "infernocuisine/ash_biscuit_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:ember_dough", "", "", "", ""},
				"copper_inferno:ash_biscuit", 1, "A campfire also cooks Ember Dough - slower, but without fuel.", "Auch das Lagerfeuer gart Glutteig - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_pancake", "copper_inferno:cinder_pancake", "infernocuisine/cinder_pancake",
				new String[] {"", "", "", "", "copper_inferno:cinder_batter", "", "", "", ""},
				"copper_inferno:cinder_pancake", 1, "Smelt Cinder Batter in a furnace into Cinder Pancake.", "Zunder-Backteig im Ofen garen, um Zunder-Pfannkuchen zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_pancake_from_campfire_cooking", "copper_inferno:cinder_pancake", "infernocuisine/cinder_pancake_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:cinder_batter", "", "", "", ""},
				"copper_inferno:cinder_pancake", 1, "A campfire also cooks Cinder Batter - slower, but without fuel.", "Auch das Lagerfeuer gart Zunder-Backteig - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_popcorn", "copper_inferno:magma_popcorn", "infernocuisine/magma_popcorn",
				new String[] {"", "", "", "", "copper_inferno:magma_kernels", "", "", "", ""},
				"copper_inferno:magma_popcorn", 1, "Smelt Magma Kernels in a furnace into Magma Popcorn.", "Magmak\u00f6rner im Ofen garen, um Magma-Popcorn zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_popcorn_from_campfire_cooking", "copper_inferno:magma_popcorn", "infernocuisine/magma_popcorn_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:magma_kernels", "", "", "", ""},
				"copper_inferno:magma_popcorn", 1, "A campfire also cooks Magma Kernels - slower, but without fuel.", "Auch das Lagerfeuer gart Magmak\u00f6rner - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_toasted_marshmallow", "copper_inferno:toasted_marshmallow", "infernocuisine/toasted_marshmallow",
				new String[] {"", "", "", "", "copper_inferno:molten_marshmallow", "", "", "", ""},
				"copper_inferno:toasted_marshmallow", 1, "Smelt Molten Marshmallow in a furnace into Toasted Marshmallow.", "Geschmolzenes Marshmallow im Ofen garen, um Ger\u00f6stetes Marshmallow zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_toasted_marshmallow_from_campfire_cooking", "copper_inferno:toasted_marshmallow", "infernocuisine/toasted_marshmallow_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:molten_marshmallow", "", "", "", ""},
				"copper_inferno:toasted_marshmallow", 1, "A campfire also cooks Molten Marshmallow - slower, but without fuel.", "Auch das Lagerfeuer gart Geschmolzenes Marshmallow - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_blaze_chop", "copper_inferno:blaze_chop", "infernocuisine/blaze_chop",
				new String[] {"", "", "", "", "copper_inferno:raw_blaze_chop", "", "", "", ""},
				"copper_inferno:blaze_chop", 1, "Smelt Raw Blaze Chop in a furnace into Blaze Chop.", "Rohes Lohenkotelett im Ofen garen, um Lohenkotelett zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_blaze_chop_from_campfire_cooking", "copper_inferno:blaze_chop", "infernocuisine/blaze_chop_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:raw_blaze_chop", "", "", "", ""},
				"copper_inferno:blaze_chop", 1, "A campfire also cooks Raw Blaze Chop - slower, but without fuel.", "Auch das Lagerfeuer gart Rohes Lohenkotelett - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_baked_soot_spud", "copper_inferno:baked_soot_spud", "infernocuisine/baked_soot_spud",
				new String[] {"", "", "", "", "copper_inferno:soot_spud", "", "", "", ""},
				"copper_inferno:baked_soot_spud", 1, "Smelt Soot Spud in a furnace into Baked Soot Spud.", "Ru\u00dfkartoffel im Ofen garen, um Gebackene Ru\u00dfkartoffel zu erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_baked_soot_spud_from_campfire_cooking", "copper_inferno:baked_soot_spud", "infernocuisine/baked_soot_spud_from_campfire_cooking",
				new String[] {"", "", "", "", "copper_inferno:soot_spud", "", "", "", ""},
				"copper_inferno:baked_soot_spud", 1, "A campfire also cooks Soot Spud - slower, but without fuel.", "Auch das Lagerfeuer gart Ru\u00dfkartoffel - langsamer, aber ohne Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_pie", "copper_inferno:cinder_pie", "infernocuisine/cinder_pie",
				new String[] {"copper_inferno:ember_dough", "minecraft:sugar", "minecraft:egg", "copper_inferno:ember_berries", "", "", "", "", ""},
				"copper_inferno:cinder_pie", 1, "Craft 1x Cinder Pie at a crafting table.", "Stellt 1x Zunderpastete an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_tart", "copper_inferno:magma_tart", "infernocuisine/magma_tart",
				new String[] {"copper_inferno:ember_dough", "minecraft:glow_berries", "minecraft:sugar", "", "", "", "", "", ""},
				"copper_inferno:magma_tart", 1, "Craft 1x Magma Tart at a crafting table.", "Stellt 1x Magma-T\u00f6rtchen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_muffin", "copper_inferno:ember_muffin", "infernocuisine/ember_muffin",
				new String[] {"copper_inferno:ember_dough", "minecraft:sweet_berries", "minecraft:egg", "", "", "", "", "", ""},
				"copper_inferno:ember_muffin", 1, "Craft 1x Ember Muffin at a crafting table.", "Stellt 1x Glut-Muffin an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_cupcake", "copper_inferno:ash_cupcake", "infernocuisine/ash_cupcake",
				new String[] {"copper_inferno:cinder_batter", "minecraft:sugar", "copper_inferno:ash_pile", "", "", "", "", "", ""},
				"copper_inferno:ash_cupcake", 1, "Craft 1x Ash Cupcake at a crafting table.", "Stellt 1x Aschen-Cupcake an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_lava_brownie", "copper_inferno:lava_brownie", "infernocuisine/lava_brownie",
				new String[] {"copper_inferno:cinder_batter", "minecraft:cocoa_beans", "minecraft:magma_cream", "", "", "", "", "", ""},
				"copper_inferno:lava_brownie", 1, "Craft 1x Lava Brownie at a crafting table.", "Stellt 1x Lava-Brownie an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_soot_scone", "copper_inferno:soot_scone", "infernocuisine/soot_scone",
				new String[] {"copper_inferno:ember_dough", "minecraft:charcoal", "minecraft:sugar", "", "", "", "", "", ""},
				"copper_inferno:soot_scone", 2, "Craft 2x Soot Scone at a crafting table.", "Stellt 2x Ru\u00df-Scone an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_flame_fritter", "copper_inferno:flame_fritter", "infernocuisine/flame_fritter",
				new String[] {"copper_inferno:cinder_batter", "minecraft:apple", "minecraft:blaze_powder", "", "", "", "", "", ""},
				"copper_inferno:flame_fritter", 2, "Craft 2x Flame Fritter at a crafting table.", "Stellt 2x Flammenkrapfen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_croissant", "copper_inferno:cinder_croissant", "infernocuisine/cinder_croissant",
				new String[] {"copper_inferno:ember_dough", "copper_inferno:ember_dough", "minecraft:milk_bucket", "", "", "", "", "", ""},
				"copper_inferno:cinder_croissant", 3, "Craft 3x Cinder Croissant at a crafting table.", "Stellt 3x Zunder-Croissant an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_waffle", "copper_inferno:ember_waffle", "infernocuisine/ember_waffle",
				new String[] {"copper_inferno:cinder_batter", "minecraft:honey_bottle", "", "", "", "", "", "", ""},
				"copper_inferno:ember_waffle", 2, "Craft 2x Ember Waffle at a crafting table.", "Stellt 2x Glutwaffel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_donut", "copper_inferno:magma_donut", "infernocuisine/magma_donut",
				new String[] {"copper_inferno:ember_dough", "copper_inferno:ember_dough", "copper_inferno:ember_dough", "copper_inferno:ember_dough", "", "copper_inferno:ember_dough", "copper_inferno:ember_dough", "copper_inferno:ember_dough", "copper_inferno:ember_dough"},
				"copper_inferno:magma_donut", 6, "Craft 6x Magma Donut at a crafting table.", "Stellt 6x Magma-Donut an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_jerky", "copper_inferno:ember_jerky", "infernocuisine/ember_jerky",
				new String[] {"copper_inferno:magma_steak", "copper_inferno:ember_dust", "", "", "", "", "", "", ""},
				"copper_inferno:ember_jerky", 3, "Craft 3x Ember Jerky at a crafting table.", "Stellt 3x Glut-D\u00f6rrfleisch an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_toffee", "copper_inferno:cinder_toffee", "infernocuisine/cinder_toffee",
				new String[] {"minecraft:sugar", "minecraft:sugar", "minecraft:milk_bucket", "copper_inferno:ash_pile", "", "", "", "", ""},
				"copper_inferno:cinder_toffee", 4, "Craft 4x Cinder Toffee at a crafting table.", "Stellt 4x Zunderkaramell an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_fudge", "copper_inferno:magma_fudge", "infernocuisine/magma_fudge",
				new String[] {"minecraft:cocoa_beans", "minecraft:sugar", "minecraft:milk_bucket", "minecraft:magma_cream", "", "", "", "", ""},
				"copper_inferno:magma_fudge", 4, "Craft 4x Magma Fudge at a crafting table.", "Stellt 4x Magma-Konfekt an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_licorice", "copper_inferno:ash_licorice", "infernocuisine/ash_licorice",
				new String[] {"minecraft:sugar", "copper_inferno:ash_pile", "minecraft:slime_ball", "", "", "", "", "", ""},
				"copper_inferno:ash_licorice", 3, "Craft 3x Ash Licorice at a crafting table.", "Stellt 3x Aschenlakritz an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_brittle", "copper_inferno:ember_brittle", "infernocuisine/ember_brittle",
				new String[] {"minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "copper_inferno:ember_dust", "", "", "", "", ""},
				"copper_inferno:ember_brittle", 3, "Craft 3x Ember Brittle at a crafting table.", "Stellt 3x Glutkrokant an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_lava_lollipop", "copper_inferno:lava_lollipop", "infernocuisine/lava_lollipop",
				new String[] {"minecraft:sugar", "minecraft:magma_cream", "minecraft:stick", "", "", "", "", "", ""},
				"copper_inferno:lava_lollipop", 2, "Craft 2x Lava Lollipop at a crafting table.", "Stellt 2x Lava-Lutscher an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_soot_truffle", "copper_inferno:soot_truffle", "infernocuisine/soot_truffle",
				new String[] {"minecraft:cocoa_beans", "minecraft:cocoa_beans", "minecraft:charcoal", "", "", "", "", "", ""},
				"copper_inferno:soot_truffle", 3, "Craft 3x Soot Truffle at a crafting table.", "Stellt 3x Ru\u00dftr\u00fcffel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_blaze_bonbon", "copper_inferno:blaze_bonbon", "infernocuisine/blaze_bonbon",
				new String[] {"minecraft:sugar", "minecraft:blaze_powder", "minecraft:honey_bottle", "", "", "", "", "", ""},
				"copper_inferno:blaze_bonbon", 4, "Craft 4x Blaze Bonbon at a crafting table.", "Stellt 4x Lohenbonbon an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_chocolate", "copper_inferno:cinder_chocolate", "infernocuisine/cinder_chocolate",
				new String[] {"minecraft:cocoa_beans", "minecraft:cocoa_beans", "minecraft:milk_bucket", "copper_inferno:ash_pile", "", "", "", "", ""},
				"copper_inferno:cinder_chocolate", 2, "Craft 2x Cinder Chocolate at a crafting table.", "Stellt 2x Zunderschokolade an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_molten_caramel", "copper_inferno:molten_caramel", "infernocuisine/molten_caramel",
				new String[] {"minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:magma_cream", "", "", "", "", ""},
				"copper_inferno:molten_caramel", 3, "Craft 3x Molten Caramel at a crafting table.", "Stellt 3x Geschmolzenes Karamell an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_gingerbread", "copper_inferno:ember_gingerbread", "infernocuisine/ember_gingerbread",
				new String[] {"copper_inferno:ember_dough", "minecraft:honey_bottle", "copper_inferno:ember_dust", "", "", "", "", "", ""},
				"copper_inferno:ember_gingerbread", 2, "Craft 2x Ember Gingerbread at a crafting table.", "Stellt 2x Glutlebkuchen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_slag_sandwich", "copper_inferno:slag_sandwich", "infernocuisine/slag_sandwich",
				new String[] {"minecraft:bread", "", "", "copper_inferno:slag_ribs", "", "", "minecraft:bread", "", ""},
				"copper_inferno:slag_sandwich", 1, "Craft 1x Slag Sandwich at a crafting table.", "Stellt 1x Schlacken-Sandwich an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_burger", "copper_inferno:ember_burger", "infernocuisine/ember_burger",
				new String[] {"minecraft:bread", "", "", "copper_inferno:magma_steak", "", "", "minecraft:bread", "", ""},
				"copper_inferno:ember_burger", 1, "Craft 1x Ember Burger at a crafting table.", "Stellt 1x Glutburger an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_taco", "copper_inferno:cinder_taco", "infernocuisine/cinder_taco",
				new String[] {"minecraft:bread", "copper_inferno:cinder_bacon", "copper_inferno:ash_pile", "", "", "", "", "", ""},
				"copper_inferno:cinder_taco", 1, "Craft 1x Cinder Taco at a crafting table.", "Stellt 1x Zunder-Taco an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_pizza", "copper_inferno:magma_pizza", "infernocuisine/magma_pizza",
				new String[] {"copper_inferno:ember_dough", "minecraft:milk_bucket", "minecraft:magma_cream", "minecraft:red_mushroom", "", "", "", "", ""},
				"copper_inferno:magma_pizza", 1, "Craft 1x Magma Pizza at a crafting table.", "Stellt 1x Magma-Pizza an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_pretzel", "copper_inferno:ash_pretzel", "infernocuisine/ash_pretzel",
				new String[] {"copper_inferno:ember_dough", "copper_inferno:ash_pile", "", "", "", "", "", "", ""},
				"copper_inferno:ash_pretzel", 2, "Craft 2x Ash Pretzel at a crafting table.", "Stellt 2x Aschenbrezel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_soot_cracker", "copper_inferno:soot_cracker", "infernocuisine/soot_cracker",
				new String[] {"minecraft:wheat", "minecraft:charcoal", "", "", "", "", "", "", ""},
				"copper_inferno:soot_cracker", 4, "Craft 4x Soot Cracker at a crafting table.", "Stellt 4x Ru\u00dfcracker an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_omelette", "copper_inferno:ember_omelette", "infernocuisine/ember_omelette",
				new String[] {"minecraft:egg", "minecraft:egg", "copper_inferno:ember_dust", "", "", "", "", "", ""},
				"copper_inferno:ember_omelette", 1, "Craft 1x Ember Omelette at a crafting table.", "Stellt 1x Glutomelett an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_lava_noodles", "copper_inferno:lava_noodles", "infernocuisine/lava_noodles",
				new String[] {"minecraft:wheat", "minecraft:wheat", "minecraft:egg", "minecraft:magma_cream", "", "", "", "", ""},
				"copper_inferno:lava_noodles", 2, "Craft 2x Lava Noodles at a crafting table.", "Stellt 2x Lava-Nudeln an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_dumpling", "copper_inferno:cinder_dumpling", "infernocuisine/cinder_dumpling",
				new String[] {"copper_inferno:ember_dough", "minecraft:porkchop", "", "", "", "", "", "", ""},
				"copper_inferno:cinder_dumpling", 2, "Craft 2x Cinder Dumpling at a crafting table.", "Stellt 2x Zunderkn\u00f6del an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_blaze_kebab", "copper_inferno:blaze_kebab", "infernocuisine/blaze_kebab",
				new String[] {"copper_inferno:blaze_chop", "", "", "copper_inferno:blaze_chop", "", "", "minecraft:stick", "", ""},
				"copper_inferno:blaze_kebab", 1, "Craft 1x Blaze Kebab at a crafting table.", "Stellt 1x Lohen-Kebab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_skewer", "copper_inferno:ember_skewer", "infernocuisine/ember_skewer",
				new String[] {"copper_inferno:ember_sausage", "", "", "minecraft:stick", "", "", "", "", ""},
				"copper_inferno:ember_skewer", 1, "Craft 1x Ember Skewer at a crafting table.", "Stellt 1x Glutspie\u00df an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_meatballs", "copper_inferno:magma_meatballs", "infernocuisine/magma_meatballs",
				new String[] {"minecraft:beef", "minecraft:egg", "minecraft:magma_cream", "", "", "", "", "", ""},
				"copper_inferno:magma_meatballs", 3, "Craft 3x Magma Meatballs at a crafting table.", "Stellt 3x Magma-Fleischb\u00e4llchen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_falafel", "copper_inferno:ash_falafel", "infernocuisine/ash_falafel",
				new String[] {"minecraft:wheat_seeds", "minecraft:wheat_seeds", "copper_inferno:ash_pile", "", "", "", "", "", ""},
				"copper_inferno:ash_falafel", 3, "Craft 3x Ash Falafel at a crafting table.", "Stellt 3x Aschenfalafel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_quiche", "copper_inferno:cinder_quiche", "infernocuisine/cinder_quiche",
				new String[] {"copper_inferno:ember_dough", "minecraft:egg", "minecraft:milk_bucket", "copper_inferno:ash_pile", "", "", "", "", ""},
				"copper_inferno:cinder_quiche", 1, "Craft 1x Cinder Quiche at a crafting table.", "Stellt 1x Zunder-Quiche an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_slag_burrito", "copper_inferno:slag_burrito", "infernocuisine/slag_burrito",
				new String[] {"minecraft:bread", "copper_inferno:slag_ribs", "copper_inferno:magma_salsa", "", "", "", "", "", ""},
				"copper_inferno:slag_burrito", 1, "Craft 1x Slag Burrito at a crafting table.", "Stellt 1x Schlacken-Burrito an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_fig", "copper_inferno:ember_fig", "infernocuisine/ember_fig",
				new String[] {"minecraft:sweet_berries", "copper_inferno:ember_dust", "", "", "", "", "", "", ""},
				"copper_inferno:ember_fig", 2, "Craft 2x Ember Fig at a crafting table.", "Stellt 2x Glutfeige an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_cinder_plum", "copper_inferno:cinder_plum", "infernocuisine/cinder_plum",
				new String[] {"minecraft:apple", "copper_inferno:ash_pile", "", "", "", "", "", "", ""},
				"copper_inferno:cinder_plum", 2, "Craft 2x Cinder Plum at a crafting table.", "Stellt 2x Zunderpflaume an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_magma_melon_slice", "copper_inferno:magma_melon_slice", "infernocuisine/magma_melon_slice",
				new String[] {"minecraft:melon_slice", "minecraft:magma_cream", "", "", "", "", "", "", ""},
				"copper_inferno:magma_melon_slice", 2, "Craft 2x Magma Melon Slice at a crafting table.", "Stellt 2x Magmamelonenscheibe an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ash_date", "copper_inferno:ash_date", "infernocuisine/ash_date",
				new String[] {"minecraft:sweet_berries", "copper_inferno:ash_pile", "", "", "", "", "", "", ""},
				"copper_inferno:ash_date", 2, "Craft 2x Ash Date at a crafting table.", "Stellt 2x Aschendattel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_scorched_grapes", "copper_inferno:scorched_grapes", "infernocuisine/scorched_grapes",
				new String[] {"minecraft:sweet_berries", "minecraft:sweet_berries", "minecraft:blaze_powder", "", "", "", "", "", ""},
				"copper_inferno:scorched_grapes", 2, "Craft 2x Scorched Grapes at a crafting table.", "Stellt 2x Versengte Trauben an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_flame_raisins", "copper_inferno:flame_raisins", "infernocuisine/flame_raisins",
				new String[] {"copper_inferno:scorched_grapes", "", "", "", "", "", "", "", ""},
				"copper_inferno:flame_raisins", 2, "Craft 2x Flame Raisins at a crafting table.", "Stellt 2x Flammenrosinen an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_soot_olives", "copper_inferno:soot_olives", "infernocuisine/soot_olives",
				new String[] {"minecraft:glow_berries", "minecraft:charcoal", "", "", "", "", "", "", ""},
				"copper_inferno:soot_olives", 2, "Craft 2x Soot Olives at a crafting table.", "Stellt 2x Ru\u00dfoliven an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "infernocuisine_ember_pickle", "copper_inferno:ember_pickle", "infernocuisine/ember_pickle",
				new String[] {"minecraft:sea_pickle", "copper_inferno:ember_dust", "", "", "", "", "", "", ""},
				"copper_inferno:ember_pickle", 2, "Craft 2x Ember Pickle at a crafting table.", "Stellt 2x Glutgurke an der Werkbank her."));
	}
}
