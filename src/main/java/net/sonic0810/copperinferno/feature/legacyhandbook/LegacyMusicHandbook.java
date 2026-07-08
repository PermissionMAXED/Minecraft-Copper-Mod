package net.sonic0810.copperinferno.feature.legacyhandbook;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Legacy handbook pages for the "music" recipe dir: one entry per JSON under
 * {@code data/copper_inferno/recipe/music/} (17 entries, category
 * "items"). Grids/texts are derived from the recipe JSONs by
 * {@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class LegacyMusicHandbook {
	private LegacyMusicHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("items", "music/copper_axe_smithing", "minecraft:copper_axe", "music/copper_axe_smithing",
				new String[] {"", "", "", "", "minecraft:iron_axe", "", "", "", ""},
				"minecraft:copper_axe", 1, "Upgrade Iron Axe with Copper Ingot at a smithing table using the Copper Upgrade Smithing Template to obtain Copper Axe.", "Wertet Eisenaxt am Schmiedetisch mit Kupferbarren und der Kupfer-Aufwertungs-Schmiedevorlage zu Kupferaxt auf."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_boots_smithing", "minecraft:copper_boots", "music/copper_boots_smithing",
				new String[] {"", "", "", "", "minecraft:iron_boots", "", "", "", ""},
				"minecraft:copper_boots", 1, "Upgrade Iron Boots with Copper Ingot at a smithing table using the Copper Upgrade Smithing Template to obtain Copper Boots.", "Wertet Eisenstiefel am Schmiedetisch mit Kupferbarren und der Kupfer-Aufwertungs-Schmiedevorlage zu Kupferstiefel auf."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_chestplate_smithing", "minecraft:copper_chestplate", "music/copper_chestplate_smithing",
				new String[] {"", "", "", "", "minecraft:iron_chestplate", "", "", "", ""},
				"minecraft:copper_chestplate", 1, "Upgrade Iron Chestplate with Copper Ingot at a smithing table using the Copper Upgrade Smithing Template to obtain Copper Chestplate.", "Wertet Eisenharnisch am Schmiedetisch mit Kupferbarren und der Kupfer-Aufwertungs-Schmiedevorlage zu Kupferharnisch auf."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_helmet_smithing", "minecraft:copper_helmet", "music/copper_helmet_smithing",
				new String[] {"", "", "", "", "minecraft:iron_helmet", "", "", "", ""},
				"minecraft:copper_helmet", 1, "Upgrade Iron Helmet with Copper Ingot at a smithing table using the Copper Upgrade Smithing Template to obtain Copper Helmet.", "Wertet Eisenhelm am Schmiedetisch mit Kupferbarren und der Kupfer-Aufwertungs-Schmiedevorlage zu Kupferhelm auf."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_hoe_smithing", "minecraft:copper_hoe", "music/copper_hoe_smithing",
				new String[] {"", "", "", "", "minecraft:iron_hoe", "", "", "", ""},
				"minecraft:copper_hoe", 1, "Upgrade Iron Hoe with Copper Ingot at a smithing table using the Copper Upgrade Smithing Template to obtain Copper Hoe.", "Wertet Eisenhacke am Schmiedetisch mit Kupferbarren und der Kupfer-Aufwertungs-Schmiedevorlage zu Kupferhacke auf."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_leggings_smithing", "minecraft:copper_leggings", "music/copper_leggings_smithing",
				new String[] {"", "", "", "", "minecraft:iron_leggings", "", "", "", ""},
				"minecraft:copper_leggings", 1, "Upgrade Iron Leggings with Copper Ingot at a smithing table using the Copper Upgrade Smithing Template to obtain Copper Leggings.", "Wertet Eisenbeinschutz am Schmiedetisch mit Kupferbarren und der Kupfer-Aufwertungs-Schmiedevorlage zu Kupferbeinschutz auf."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_pickaxe_smithing", "minecraft:copper_pickaxe", "music/copper_pickaxe_smithing",
				new String[] {"", "", "", "", "minecraft:iron_pickaxe", "", "", "", ""},
				"minecraft:copper_pickaxe", 1, "Upgrade Iron Pickaxe with Copper Ingot at a smithing table using the Copper Upgrade Smithing Template to obtain Copper Pickaxe.", "Wertet Eisenspitzhacke am Schmiedetisch mit Kupferbarren und der Kupfer-Aufwertungs-Schmiedevorlage zu Kupferspitzhacke auf."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_pocket_watch", "copper_inferno:copper_pocket_watch", "music/copper_pocket_watch",
				new String[] {"minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "copper_inferno:copper_gear", "minecraft:copper_ingot", "minecraft:copper_ingot", "copper_inferno:copper_spring", "minecraft:copper_ingot"},
				"copper_inferno:copper_pocket_watch", 1, "Craft 1x Copper Pocket Watch at a crafting table.", "Stellt 1x Kupfertaschenuhr an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_shovel_smithing", "minecraft:copper_shovel", "music/copper_shovel_smithing",
				new String[] {"", "", "", "", "minecraft:iron_shovel", "", "", "", ""},
				"minecraft:copper_shovel", 1, "Upgrade Iron Shovel with Copper Ingot at a smithing table using the Copper Upgrade Smithing Template to obtain Copper Shovel.", "Wertet Eisenschaufel am Schmiedetisch mit Kupferbarren und der Kupfer-Aufwertungs-Schmiedevorlage zu Kupferschaufel auf."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_sword_smithing", "minecraft:copper_sword", "music/copper_sword_smithing",
				new String[] {"", "", "", "", "minecraft:iron_sword", "", "", "", ""},
				"minecraft:copper_sword", 1, "Upgrade Iron Sword with Copper Ingot at a smithing table using the Copper Upgrade Smithing Template to obtain Copper Sword.", "Wertet Eisenschwert am Schmiedetisch mit Kupferbarren und der Kupfer-Aufwertungs-Schmiedevorlage zu Kupferschwert auf."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_upgrade_smithing_template", "copper_inferno:copper_upgrade_smithing_template", "music/copper_upgrade_smithing_template",
				new String[] {"minecraft:copper_ingot", "minecraft:diamond", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:blackstone", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot"},
				"copper_inferno:copper_upgrade_smithing_template", 1, "Craft 1x Copper Upgrade Smithing Template at a crafting table.", "Stellt 1x Kupfer-Aufwertungs-Schmiedevorlage an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "music/copper_upgrade_smithing_template_duplication", "copper_inferno:copper_upgrade_smithing_template", "music/copper_upgrade_smithing_template_duplication",
				new String[] {"minecraft:copper_ingot", "copper_inferno:copper_upgrade_smithing_template", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:blackstone", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot", "minecraft:copper_ingot"},
				"copper_inferno:copper_upgrade_smithing_template", 2, "Craft 2x Copper Upgrade Smithing Template at a crafting table.", "Stellt 2x Kupfer-Aufwertungs-Schmiedevorlage an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "music/inferno_upgrade_smithing_template", "copper_inferno:inferno_upgrade_smithing_template", "music/inferno_upgrade_smithing_template",
				new String[] {"minecraft:blackstone", "minecraft:diamond", "minecraft:blackstone", "minecraft:blackstone", "minecraft:blaze_rod", "minecraft:blackstone", "minecraft:blackstone", "minecraft:blackstone", "minecraft:blackstone"},
				"copper_inferno:inferno_upgrade_smithing_template", 1, "Craft 1x Inferno Upgrade Smithing Template at a crafting table.", "Stellt 1x Inferno-Aufwertungs-Schmiedevorlage an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "music/music_disc_copper_inferno", "copper_inferno:music_disc_copper_inferno", "music/music_disc_copper_inferno",
				new String[] {"copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_alloy_ingot", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard"},
				"copper_inferno:music_disc_copper_inferno", 1, "Craft 1x Music Disc at a crafting table.", "Stellt 1x Schallplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "music/music_disc_oxidation", "copper_inferno:music_disc_oxidation", "music/music_disc_oxidation",
				new String[] {"copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:oxidized_copper_dust", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard"},
				"copper_inferno:music_disc_oxidation", 1, "Craft 1x Music Disc at a crafting table.", "Stellt 1x Schallplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "music/music_disc_soda_pop", "copper_inferno:music_disc_soda_pop", "music/music_disc_soda_pop",
				new String[] {"copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:soda_essence", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard", "copper_inferno:inferno_shard"},
				"copper_inferno:music_disc_soda_pop", 1, "Craft 1x Music Disc at a crafting table.", "Stellt 1x Schallplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "music/sonic0810_medallion", "copper_inferno:sonic0810_medallion", "music/sonic0810_medallion",
				new String[] {"minecraft:gold_nugget", "minecraft:copper_ingot", "minecraft:gold_nugget", "minecraft:copper_ingot", "copper_inferno:copper_coin", "minecraft:copper_ingot", "minecraft:gold_nugget", "minecraft:copper_ingot", "minecraft:gold_nugget"},
				"copper_inferno:sonic0810_medallion", 1, "Craft 1x Sonic0810's Medallion at a crafting table.", "Stellt 1x Sonic0810s Medaillon an der Werkbank her."));
	}
}
