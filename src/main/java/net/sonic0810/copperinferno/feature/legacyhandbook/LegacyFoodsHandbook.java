package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "foods" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/foods/} (18 entries, category
 * "items"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyFoodsHandbook {
	private LegacyFoodsHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("items", "foods/candied_copper_apple", "copper_inferno:candied_copper_apple", "foods/candied_copper_apple",
				new String[] {"copper_inferno:copper_apple", "minecraft:honey_bottle", "minecraft:sugar", "", "", "", "", "", ""},
				"copper_inferno:candied_copper_apple", 1, "Craft 1x Candied Copper Apple at a crafting table.", "Stellt 1x Kandierten Kupferapfel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/caramel_syrup", "copper_inferno:caramel_syrup", "foods/caramel_syrup",
				new String[] {"copper_inferno:soda_syrup", "minecraft:sugar", "minecraft:sugar", "", "", "", "", "", ""},
				"copper_inferno:caramel_syrup", 1, "Craft 1x Caramel Syrup at a crafting table.", "Stellt 1x Karamellsirup an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/cherry_dr_pepper", "copper_inferno:cherry_dr_pepper", "foods/cherry_dr_pepper",
				new String[] {"minecraft:glass_bottle", "copper_inferno:soda_essence", "minecraft:sweet_berries", "", "", "", "", "", ""},
				"copper_inferno:cherry_dr_pepper", 1, "Craft 1x Cherry Dr.Pepper at a crafting table.", "Stellt 1x Kirsch-Dr.Pepper an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/copper_apple", "copper_inferno:copper_apple", "foods/copper_apple",
				new String[] {"minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:apple", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot"},
				"copper_inferno:copper_apple", 1, "Craft 1x Copper Apple at a crafting table.", "Stellt 1x Kupferapfel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/copper_carrot", "copper_inferno:copper_carrot", "foods/copper_carrot",
				new String[] {"minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:carrot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot"},
				"copper_inferno:copper_carrot", 1, "Craft 1x Copper Carrot at a crafting table.", "Stellt 1x Kupferkarotte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/cream_soda", "copper_inferno:cream_soda", "foods/cream_soda",
				new String[] {"minecraft:glass_bottle", "copper_inferno:soda_essence", "minecraft:honey_bottle", "", "", "", "", "", ""},
				"copper_inferno:cream_soda", 1, "Craft 1x Cream Soda at a crafting table.", "Stellt 1x Cremelimonade an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/dr_pepper_cake_slice", "copper_inferno:dr_pepper_cake_slice", "foods/dr_pepper_cake_slice",
				new String[] {"minecraft:wheat", "minecraft:sugar", "minecraft:egg", "copper_inferno:soda_syrup", "", "", "", "", ""},
				"copper_inferno:dr_pepper_cake_slice", 3, "Craft 3x Dr.Pepper Cake Slice at a crafting table.", "Stellt 3x Dr.Pepper-Kuchenst\u00fcck an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/dr_pepper_cookie", "copper_inferno:dr_pepper_cookie", "foods/dr_pepper_cookie",
				new String[] {"minecraft:wheat", "copper_inferno:soda_syrup", "minecraft:wheat", "", "", "", "", "", ""},
				"copper_inferno:dr_pepper_cookie", 8, "Craft 8x Dr.Pepper Cookie at a crafting table.", "Stellt 8x Dr.Pepper-Keks an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/dr_pepper_ice_cube", "copper_inferno:dr_pepper_ice_cube", "foods/dr_pepper_ice_cube",
				new String[] {"minecraft:ice", "copper_inferno:soda_syrup", "", "", "", "", "", "", ""},
				"copper_inferno:dr_pepper_ice_cube", 4, "Craft 4x Dr.Pepper Ice Cube at a crafting table.", "Stellt 4x Dr.Pepper-Eisw\u00fcrfel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/dr_pepper_zero", "copper_inferno:dr_pepper_zero", "foods/dr_pepper_zero",
				new String[] {"minecraft:glass_bottle", "copper_inferno:soda_essence", "minecraft:cocoa_beans", "", "", "", "", "", ""},
				"copper_inferno:dr_pepper_zero", 1, "Craft 1x Dr.Pepper Zero at a crafting table.", "Stellt 1x Dr.Pepper Zero an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/fizzy_sugar_crystals", "copper_inferno:fizzy_sugar_crystals", "foods/fizzy_sugar_crystals",
				new String[] {"minecraft:sugar", "minecraft:sugar", "minecraft:gunpowder", "", "", "", "", "", ""},
				"copper_inferno:fizzy_sugar_crystals", 2, "Craft 2x Fizzy Sugar Crystals at a crafting table.", "Stellt 2x Sprudelnde Zuckerkristalle an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/grape_soda", "copper_inferno:grape_soda", "foods/grape_soda",
				new String[] {"minecraft:glass_bottle", "copper_inferno:soda_essence", "minecraft:chorus_fruit", "", "", "", "", "", ""},
				"copper_inferno:grape_soda", 1, "Craft 1x Grape Soda at a crafting table.", "Stellt 1x Traubenlimonade an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/inferno_pepper", "copper_inferno:inferno_pepper", "foods/inferno_pepper",
				new String[] {"minecraft:carrot", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:inferno_pepper", 1, "Craft 1x Inferno Pepper at a crafting table.", "Stellt 1x Infernopfeffer an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/orange_soda", "copper_inferno:orange_soda", "foods/orange_soda",
				new String[] {"minecraft:glass_bottle", "copper_inferno:soda_essence", "minecraft:glow_berries", "", "", "", "", "", ""},
				"copper_inferno:orange_soda", 1, "Craft 1x Orange Soda at a crafting table.", "Stellt 1x Orangenlimonade an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/soda_float", "copper_inferno:soda_float", "foods/soda_float",
				new String[] {"copper_inferno:cream_soda", "minecraft:snowball", "minecraft:snowball", "", "", "", "", "", ""},
				"copper_inferno:soda_float", 1, "Craft 1x Soda Float at a crafting table.", "Stellt 1x Limonaden-Float an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/soda_gummy", "copper_inferno:soda_gummy", "foods/soda_gummy",
				new String[] {"copper_inferno:soda_syrup", "minecraft:slime_ball", "", "", "", "", "", "", ""},
				"copper_inferno:soda_gummy", 2, "Craft 2x Soda Gummy at a crafting table.", "Stellt 2x Limonadengummi an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/soda_syrup", "copper_inferno:soda_syrup", "foods/soda_syrup",
				new String[] {"minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:glass_bottle", "", "", "", "", ""},
				"copper_inferno:soda_syrup", 1, "Craft 1x Soda Syrup at a crafting table.", "Stellt 1x Limonadensirup an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "foods/vanilla_dr_pepper", "copper_inferno:vanilla_dr_pepper", "foods/vanilla_dr_pepper",
				new String[] {"minecraft:glass_bottle", "copper_inferno:soda_essence", "minecraft:milk_bucket", "", "", "", "", "", ""},
				"copper_inferno:vanilla_dr_pepper", 1, "Craft 1x Vanilla Dr.Pepper at a crafting table.", "Stellt 1x Vanille-Dr.Pepper an der Werkbank her."));
	}
}
