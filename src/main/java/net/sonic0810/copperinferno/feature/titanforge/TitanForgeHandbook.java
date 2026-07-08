package net.sonic0810.copperinferno.feature.titanforge;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the TitanForge gear tiers: one "gear" overview plus one grid
 * entry for every recipe JSON under {@code data/copper_inferno/recipe/titanforge/}
 * (alloy ingots, tools, armor, charms and smithing upgrades). Entry texts and grids
 * mirror the recipe JSONs emitted by {@code devtools/gen/titanforge_gen.py};
 * {@code devtools/check_handbook.py} parses the inline {@code new HandbookEntry(...)}
 * literals positionally, so keep them inline.
 */
final class TitanForgeHandbook {
	private TitanForgeHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("gear", "titanforge_overview", "copper_inferno:infernal_alloy_sword", null,
				null,
				null, 0, "TitanForge gear spans six forged tiers \u2014 Ember Steel, Pyrite, Slagsteel, Cinderforge, Molten Titan and Infernal Alloy \u2014 each with a full sword/pickaxe/axe/shovel/hoe tool set, helmet/chestplate/leggings/boots armor and six charm trinkets. Higher tiers are reached by alloying the previous tier's ingot or by smithing-table upgrades with the Infernium Upgrade Smithing Template; Molten Titan and Infernal Alloy gear never burns.", "TitanForge-Ausr\u00fcstung umfasst sechs geschmiedete Stufen \u2014 Glutstahl, Pyrit, Schlackenstahl, Zunderschmiede, Schmelztitan und H\u00f6llenlegierung \u2014 jede mit komplettem Werkzeugsatz (Schwert/Spitzhacke/Axt/Schaufel/Hacke), R\u00fcstung (Helm/Brustpanzer/Beinschutz/Stiefel) und sechs Anh\u00e4ngern. H\u00f6here Stufen entstehen durch Legieren des vorherigen Barrens oder per Schmiedetisch-Aufwertung mit der Infernium-Aufwertungs-Schmiedevorlage; Schmelztitan- und H\u00f6llenlegierungs-Ausr\u00fcstung verbrennt nie."));

		HandbookEntries.add(new HandbookEntry("items", "titanforge/ember_steel_ingot", "copper_inferno:ember_steel_ingot", "titanforge/ember_steel_ingot",
				new String[] {"minecraft:iron_ingot", "minecraft:blaze_powder", "minecraft:coal", "", "", "", "", "", ""},
				"copper_inferno:ember_steel_ingot", 2, "Alloy the shown ingredients into 2x Ember Steel Ingot.", "Die gezeigten Zutaten zu 2x Glutstahl-Barren legieren."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_sword", "copper_inferno:ember_steel_sword", "titanforge/ember_steel_sword",
				new String[] {"", "copper_inferno:ember_steel_ingot", "", "", "copper_inferno:ember_steel_ingot", "", "", "minecraft:stick", ""},
				"copper_inferno:ember_steel_sword", 1, "Craft the Ember Steel Sword from Ember Steel Ingots and sticks.", "Glutstahl-Schwert aus Glutstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_pickaxe", "copper_inferno:ember_steel_pickaxe", "titanforge/ember_steel_pickaxe",
				new String[] {"copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:ember_steel_pickaxe", 1, "Craft the Ember Steel Pickaxe from Ember Steel Ingots and sticks.", "Glutstahl-Spitzhacke aus Glutstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_axe", "copper_inferno:ember_steel_axe", "titanforge/ember_steel_axe",
				new String[] {"copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:ember_steel_axe", 1, "Craft the Ember Steel Axe from Ember Steel Ingots and sticks.", "Glutstahl-Axt aus Glutstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_shovel", "copper_inferno:ember_steel_shovel", "titanforge/ember_steel_shovel",
				new String[] {"", "copper_inferno:ember_steel_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:ember_steel_shovel", 1, "Craft the Ember Steel Shovel from Ember Steel Ingots and sticks.", "Glutstahl-Schaufel aus Glutstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_hoe", "copper_inferno:ember_steel_hoe", "titanforge/ember_steel_hoe",
				new String[] {"copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:ember_steel_hoe", 1, "Craft the Ember Steel Hoe from Ember Steel Ingots and sticks.", "Glutstahl-Hacke aus Glutstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_helmet", "copper_inferno:ember_steel_helmet", "titanforge/ember_steel_helmet",
				new String[] {"copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "", "", ""},
				"copper_inferno:ember_steel_helmet", 1, "Craft the Ember Steel Helmet from Ember Steel Ingots.", "Glutstahl-Helm aus Glutstahl-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_chestplate", "copper_inferno:ember_steel_chestplate", "titanforge/ember_steel_chestplate",
				new String[] {"copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot"},
				"copper_inferno:ember_steel_chestplate", 1, "Craft the Ember Steel Chestplate from Ember Steel Ingots.", "Glutstahl-Brustpanzer aus Glutstahl-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_leggings", "copper_inferno:ember_steel_leggings", "titanforge/ember_steel_leggings",
				new String[] {"copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot"},
				"copper_inferno:ember_steel_leggings", 1, "Craft the Ember Steel Leggings from Ember Steel Ingots.", "Glutstahl-Beinschutz aus Glutstahl-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_boots", "copper_inferno:ember_steel_boots", "titanforge/ember_steel_boots",
				new String[] {"copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "", "", ""},
				"copper_inferno:ember_steel_boots", 1, "Craft the Ember Steel Boots from Ember Steel Ingots.", "Glutstahl-Stiefel aus Glutstahl-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_charm", "copper_inferno:ember_steel_charm", "titanforge/ember_steel_charm",
				new String[] {"", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "minecraft:string", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", ""},
				"copper_inferno:ember_steel_charm", 1, "A pocket Ember Steel charm for luck at the forge.", "Ein Glutstahl-Gl\u00fccksbringer f\u00fcr Gl\u00fcck an der Esse."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_totem", "copper_inferno:ember_steel_totem", "titanforge/ember_steel_totem",
				new String[] {"", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "minecraft:emerald", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", ""},
				"copper_inferno:ember_steel_totem", 1, "A carved Ember Steel totem watching over the smithy.", "Ein geschnitztes Glutstahl-Totem wacht \u00fcber die Schmiede."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_ring", "copper_inferno:ember_steel_ring", "titanforge/ember_steel_ring",
				new String[] {"", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "minecraft:gold_nugget", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", ""},
				"copper_inferno:ember_steel_ring", 1, "A polished Ember Steel ring, warm to the touch.", "Ein polierter Glutstahl-Ring, warm bei Ber\u00fchrung."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_amulet", "copper_inferno:ember_steel_amulet", "titanforge/ember_steel_amulet",
				new String[] {"", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "minecraft:amethyst_shard", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", ""},
				"copper_inferno:ember_steel_amulet", 1, "The Ember Steel amulet swings on a silver chain.", "Das Glutstahl-Amulett schwingt an silberner Kette."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_talisman", "copper_inferno:ember_steel_talisman", "titanforge/ember_steel_talisman",
				new String[] {"", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "minecraft:blaze_powder", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", ""},
				"copper_inferno:ember_steel_talisman", 1, "This Ember Steel talisman is etched with forge runes.", "Dieser Glutstahl-Talisman ist mit Schmiederunen graviert."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/ember_steel_medallion", "copper_inferno:ember_steel_medallion", "titanforge/ember_steel_medallion",
				new String[] {"", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", "minecraft:copper_ingot", "copper_inferno:ember_steel_ingot", "", "copper_inferno:ember_steel_ingot", ""},
				"copper_inferno:ember_steel_medallion", 1, "The Ember Steel medallion is awarded to master smiths.", "Das Glutstahl-Medaillon wird an Meisterschmiede verliehen."));

		HandbookEntries.add(new HandbookEntry("items", "titanforge/pyrite_ingot", "copper_inferno:pyrite_ingot", "titanforge/pyrite_ingot",
				new String[] {"copper_inferno:ember_steel_ingot", "minecraft:gold_ingot", "minecraft:glowstone_dust", "", "", "", "", "", ""},
				"copper_inferno:pyrite_ingot", 2, "Alloy the shown ingredients into 2x Pyrite Ingot.", "Die gezeigten Zutaten zu 2x Pyrit-Barren legieren."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_sword", "copper_inferno:pyrite_sword", "titanforge/pyrite_sword",
				new String[] {"", "copper_inferno:pyrite_ingot", "", "", "copper_inferno:pyrite_ingot", "", "", "minecraft:stick", ""},
				"copper_inferno:pyrite_sword", 1, "Craft the Pyrite Sword from Pyrite Ingots and sticks.", "Pyrit-Schwert aus Pyrit-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_pickaxe", "copper_inferno:pyrite_pickaxe", "titanforge/pyrite_pickaxe",
				new String[] {"copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:pyrite_pickaxe", 1, "Craft the Pyrite Pickaxe from Pyrite Ingots and sticks.", "Pyrit-Spitzhacke aus Pyrit-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_axe", "copper_inferno:pyrite_axe", "titanforge/pyrite_axe",
				new String[] {"copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:pyrite_axe", 1, "Craft the Pyrite Axe from Pyrite Ingots and sticks.", "Pyrit-Axt aus Pyrit-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_shovel", "copper_inferno:pyrite_shovel", "titanforge/pyrite_shovel",
				new String[] {"", "copper_inferno:pyrite_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:pyrite_shovel", 1, "Craft the Pyrite Shovel from Pyrite Ingots and sticks.", "Pyrit-Schaufel aus Pyrit-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_hoe", "copper_inferno:pyrite_hoe", "titanforge/pyrite_hoe",
				new String[] {"copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:pyrite_hoe", 1, "Craft the Pyrite Hoe from Pyrite Ingots and sticks.", "Pyrit-Hacke aus Pyrit-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_helmet", "copper_inferno:pyrite_helmet", "titanforge/pyrite_helmet",
				new String[] {"copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_helmet", 1, "Craft the Pyrite Helmet from Pyrite Ingots.", "Pyrit-Helm aus Pyrit-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_chestplate", "copper_inferno:pyrite_chestplate", "titanforge/pyrite_chestplate",
				new String[] {"copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot"},
				"copper_inferno:pyrite_chestplate", 1, "Craft the Pyrite Chestplate from Pyrite Ingots.", "Pyrit-Brustpanzer aus Pyrit-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_leggings", "copper_inferno:pyrite_leggings", "titanforge/pyrite_leggings",
				new String[] {"copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot"},
				"copper_inferno:pyrite_leggings", 1, "Craft the Pyrite Leggings from Pyrite Ingots.", "Pyrit-Beinschutz aus Pyrit-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_boots", "copper_inferno:pyrite_boots", "titanforge/pyrite_boots",
				new String[] {"copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_boots", 1, "Craft the Pyrite Boots from Pyrite Ingots.", "Pyrit-Stiefel aus Pyrit-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_charm", "copper_inferno:pyrite_charm", "titanforge/pyrite_charm",
				new String[] {"", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "minecraft:string", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", ""},
				"copper_inferno:pyrite_charm", 1, "A pocket Pyrite charm for luck at the forge.", "Ein Pyrit-Gl\u00fccksbringer f\u00fcr Gl\u00fcck an der Esse."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_totem", "copper_inferno:pyrite_totem", "titanforge/pyrite_totem",
				new String[] {"", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "minecraft:emerald", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", ""},
				"copper_inferno:pyrite_totem", 1, "A carved Pyrite totem watching over the smithy.", "Ein geschnitztes Pyrit-Totem wacht \u00fcber die Schmiede."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_ring", "copper_inferno:pyrite_ring", "titanforge/pyrite_ring",
				new String[] {"", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "minecraft:gold_nugget", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", ""},
				"copper_inferno:pyrite_ring", 1, "A polished Pyrite ring, warm to the touch.", "Ein polierter Pyrit-Ring, warm bei Ber\u00fchrung."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_amulet", "copper_inferno:pyrite_amulet", "titanforge/pyrite_amulet",
				new String[] {"", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "minecraft:amethyst_shard", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", ""},
				"copper_inferno:pyrite_amulet", 1, "The Pyrite amulet swings on a silver chain.", "Das Pyrit-Amulett schwingt an silberner Kette."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_talisman", "copper_inferno:pyrite_talisman", "titanforge/pyrite_talisman",
				new String[] {"", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "minecraft:blaze_powder", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", ""},
				"copper_inferno:pyrite_talisman", 1, "This Pyrite talisman is etched with forge runes.", "Dieser Pyrit-Talisman ist mit Schmiederunen graviert."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_medallion", "copper_inferno:pyrite_medallion", "titanforge/pyrite_medallion",
				new String[] {"", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", "minecraft:copper_ingot", "copper_inferno:pyrite_ingot", "", "copper_inferno:pyrite_ingot", ""},
				"copper_inferno:pyrite_medallion", 1, "The Pyrite medallion is awarded to master smiths.", "Das Pyrit-Medaillon wird an Meisterschmiede verliehen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_sword_smithing", "copper_inferno:pyrite_sword", "titanforge/pyrite_sword_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:ember_steel_sword", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_sword", 1, "Smithing table: upgrade the Ember Steel Sword with an Infernium Upgrade Smithing Template and a Pyrite Ingot.", "Schmiedetisch: Glutstahl-Schwert mit Infernium-Aufwertungs-Schmiedevorlage und Pyrit-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_pickaxe_smithing", "copper_inferno:pyrite_pickaxe", "titanforge/pyrite_pickaxe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:ember_steel_pickaxe", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_pickaxe", 1, "Smithing table: upgrade the Ember Steel Pickaxe with an Infernium Upgrade Smithing Template and a Pyrite Ingot.", "Schmiedetisch: Glutstahl-Spitzhacke mit Infernium-Aufwertungs-Schmiedevorlage und Pyrit-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_axe_smithing", "copper_inferno:pyrite_axe", "titanforge/pyrite_axe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:ember_steel_axe", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_axe", 1, "Smithing table: upgrade the Ember Steel Axe with an Infernium Upgrade Smithing Template and a Pyrite Ingot.", "Schmiedetisch: Glutstahl-Axt mit Infernium-Aufwertungs-Schmiedevorlage und Pyrit-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_shovel_smithing", "copper_inferno:pyrite_shovel", "titanforge/pyrite_shovel_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:ember_steel_shovel", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_shovel", 1, "Smithing table: upgrade the Ember Steel Shovel with an Infernium Upgrade Smithing Template and a Pyrite Ingot.", "Schmiedetisch: Glutstahl-Schaufel mit Infernium-Aufwertungs-Schmiedevorlage und Pyrit-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_hoe_smithing", "copper_inferno:pyrite_hoe", "titanforge/pyrite_hoe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:ember_steel_hoe", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_hoe", 1, "Smithing table: upgrade the Ember Steel Hoe with an Infernium Upgrade Smithing Template and a Pyrite Ingot.", "Schmiedetisch: Glutstahl-Hacke mit Infernium-Aufwertungs-Schmiedevorlage und Pyrit-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_helmet_smithing", "copper_inferno:pyrite_helmet", "titanforge/pyrite_helmet_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:ember_steel_helmet", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_helmet", 1, "Smithing table: upgrade the Ember Steel Helmet with an Infernium Upgrade Smithing Template and a Pyrite Ingot.", "Schmiedetisch: Glutstahl-Helm mit Infernium-Aufwertungs-Schmiedevorlage und Pyrit-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_chestplate_smithing", "copper_inferno:pyrite_chestplate", "titanforge/pyrite_chestplate_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:ember_steel_chestplate", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_chestplate", 1, "Smithing table: upgrade the Ember Steel Chestplate with an Infernium Upgrade Smithing Template and a Pyrite Ingot.", "Schmiedetisch: Glutstahl-Brustpanzer mit Infernium-Aufwertungs-Schmiedevorlage und Pyrit-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_leggings_smithing", "copper_inferno:pyrite_leggings", "titanforge/pyrite_leggings_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:ember_steel_leggings", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_leggings", 1, "Smithing table: upgrade the Ember Steel Leggings with an Infernium Upgrade Smithing Template and a Pyrite Ingot.", "Schmiedetisch: Glutstahl-Beinschutz mit Infernium-Aufwertungs-Schmiedevorlage und Pyrit-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/pyrite_boots_smithing", "copper_inferno:pyrite_boots", "titanforge/pyrite_boots_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:ember_steel_boots", "copper_inferno:pyrite_ingot", "", "", ""},
				"copper_inferno:pyrite_boots", 1, "Smithing table: upgrade the Ember Steel Boots with an Infernium Upgrade Smithing Template and a Pyrite Ingot.", "Schmiedetisch: Glutstahl-Stiefel mit Infernium-Aufwertungs-Schmiedevorlage und Pyrit-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("items", "titanforge/slagsteel_ingot", "copper_inferno:slagsteel_ingot", "titanforge/slagsteel_ingot",
				new String[] {"copper_inferno:pyrite_ingot", "minecraft:iron_ingot", "minecraft:charcoal", "", "", "", "", "", ""},
				"copper_inferno:slagsteel_ingot", 2, "Alloy the shown ingredients into 2x Slagsteel Ingot.", "Die gezeigten Zutaten zu 2x Schlackenstahl-Barren legieren."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_sword", "copper_inferno:slagsteel_sword", "titanforge/slagsteel_sword",
				new String[] {"", "copper_inferno:slagsteel_ingot", "", "", "copper_inferno:slagsteel_ingot", "", "", "minecraft:stick", ""},
				"copper_inferno:slagsteel_sword", 1, "Craft the Slagsteel Sword from Slagsteel Ingots and sticks.", "Schlackenstahl-Schwert aus Schlackenstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_pickaxe", "copper_inferno:slagsteel_pickaxe", "titanforge/slagsteel_pickaxe",
				new String[] {"copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:slagsteel_pickaxe", 1, "Craft the Slagsteel Pickaxe from Slagsteel Ingots and sticks.", "Schlackenstahl-Spitzhacke aus Schlackenstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_axe", "copper_inferno:slagsteel_axe", "titanforge/slagsteel_axe",
				new String[] {"copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:slagsteel_axe", 1, "Craft the Slagsteel Axe from Slagsteel Ingots and sticks.", "Schlackenstahl-Axt aus Schlackenstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_shovel", "copper_inferno:slagsteel_shovel", "titanforge/slagsteel_shovel",
				new String[] {"", "copper_inferno:slagsteel_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:slagsteel_shovel", 1, "Craft the Slagsteel Shovel from Slagsteel Ingots and sticks.", "Schlackenstahl-Schaufel aus Schlackenstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_hoe", "copper_inferno:slagsteel_hoe", "titanforge/slagsteel_hoe",
				new String[] {"copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:slagsteel_hoe", 1, "Craft the Slagsteel Hoe from Slagsteel Ingots and sticks.", "Schlackenstahl-Hacke aus Schlackenstahl-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_helmet", "copper_inferno:slagsteel_helmet", "titanforge/slagsteel_helmet",
				new String[] {"copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_helmet", 1, "Craft the Slagsteel Helmet from Slagsteel Ingots.", "Schlackenstahl-Helm aus Schlackenstahl-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_chestplate", "copper_inferno:slagsteel_chestplate", "titanforge/slagsteel_chestplate",
				new String[] {"copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot"},
				"copper_inferno:slagsteel_chestplate", 1, "Craft the Slagsteel Chestplate from Slagsteel Ingots.", "Schlackenstahl-Brustpanzer aus Schlackenstahl-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_leggings", "copper_inferno:slagsteel_leggings", "titanforge/slagsteel_leggings",
				new String[] {"copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot"},
				"copper_inferno:slagsteel_leggings", 1, "Craft the Slagsteel Leggings from Slagsteel Ingots.", "Schlackenstahl-Beinschutz aus Schlackenstahl-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_boots", "copper_inferno:slagsteel_boots", "titanforge/slagsteel_boots",
				new String[] {"copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_boots", 1, "Craft the Slagsteel Boots from Slagsteel Ingots.", "Schlackenstahl-Stiefel aus Schlackenstahl-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_charm", "copper_inferno:slagsteel_charm", "titanforge/slagsteel_charm",
				new String[] {"", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "minecraft:string", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", ""},
				"copper_inferno:slagsteel_charm", 1, "A pocket Slagsteel charm for luck at the forge.", "Ein Schlackenstahl-Gl\u00fccksbringer f\u00fcr Gl\u00fcck an der Esse."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_totem", "copper_inferno:slagsteel_totem", "titanforge/slagsteel_totem",
				new String[] {"", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "minecraft:emerald", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", ""},
				"copper_inferno:slagsteel_totem", 1, "A carved Slagsteel totem watching over the smithy.", "Ein geschnitztes Schlackenstahl-Totem wacht \u00fcber die Schmiede."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_ring", "copper_inferno:slagsteel_ring", "titanforge/slagsteel_ring",
				new String[] {"", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "minecraft:gold_nugget", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", ""},
				"copper_inferno:slagsteel_ring", 1, "A polished Slagsteel ring, warm to the touch.", "Ein polierter Schlackenstahl-Ring, warm bei Ber\u00fchrung."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_amulet", "copper_inferno:slagsteel_amulet", "titanforge/slagsteel_amulet",
				new String[] {"", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "minecraft:amethyst_shard", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", ""},
				"copper_inferno:slagsteel_amulet", 1, "The Slagsteel amulet swings on a silver chain.", "Das Schlackenstahl-Amulett schwingt an silberner Kette."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_talisman", "copper_inferno:slagsteel_talisman", "titanforge/slagsteel_talisman",
				new String[] {"", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "minecraft:blaze_powder", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", ""},
				"copper_inferno:slagsteel_talisman", 1, "This Slagsteel talisman is etched with forge runes.", "Dieser Schlackenstahl-Talisman ist mit Schmiederunen graviert."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_medallion", "copper_inferno:slagsteel_medallion", "titanforge/slagsteel_medallion",
				new String[] {"", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", "minecraft:copper_ingot", "copper_inferno:slagsteel_ingot", "", "copper_inferno:slagsteel_ingot", ""},
				"copper_inferno:slagsteel_medallion", 1, "The Slagsteel medallion is awarded to master smiths.", "Das Schlackenstahl-Medaillon wird an Meisterschmiede verliehen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_sword_smithing", "copper_inferno:slagsteel_sword", "titanforge/slagsteel_sword_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:pyrite_sword", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_sword", 1, "Smithing table: upgrade the Pyrite Sword with an Infernium Upgrade Smithing Template and a Slagsteel Ingot.", "Schmiedetisch: Pyrit-Schwert mit Infernium-Aufwertungs-Schmiedevorlage und Schlackenstahl-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_pickaxe_smithing", "copper_inferno:slagsteel_pickaxe", "titanforge/slagsteel_pickaxe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:pyrite_pickaxe", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_pickaxe", 1, "Smithing table: upgrade the Pyrite Pickaxe with an Infernium Upgrade Smithing Template and a Slagsteel Ingot.", "Schmiedetisch: Pyrit-Spitzhacke mit Infernium-Aufwertungs-Schmiedevorlage und Schlackenstahl-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_axe_smithing", "copper_inferno:slagsteel_axe", "titanforge/slagsteel_axe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:pyrite_axe", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_axe", 1, "Smithing table: upgrade the Pyrite Axe with an Infernium Upgrade Smithing Template and a Slagsteel Ingot.", "Schmiedetisch: Pyrit-Axt mit Infernium-Aufwertungs-Schmiedevorlage und Schlackenstahl-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_shovel_smithing", "copper_inferno:slagsteel_shovel", "titanforge/slagsteel_shovel_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:pyrite_shovel", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_shovel", 1, "Smithing table: upgrade the Pyrite Shovel with an Infernium Upgrade Smithing Template and a Slagsteel Ingot.", "Schmiedetisch: Pyrit-Schaufel mit Infernium-Aufwertungs-Schmiedevorlage und Schlackenstahl-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_hoe_smithing", "copper_inferno:slagsteel_hoe", "titanforge/slagsteel_hoe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:pyrite_hoe", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_hoe", 1, "Smithing table: upgrade the Pyrite Hoe with an Infernium Upgrade Smithing Template and a Slagsteel Ingot.", "Schmiedetisch: Pyrit-Hacke mit Infernium-Aufwertungs-Schmiedevorlage und Schlackenstahl-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_helmet_smithing", "copper_inferno:slagsteel_helmet", "titanforge/slagsteel_helmet_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:pyrite_helmet", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_helmet", 1, "Smithing table: upgrade the Pyrite Helmet with an Infernium Upgrade Smithing Template and a Slagsteel Ingot.", "Schmiedetisch: Pyrit-Helm mit Infernium-Aufwertungs-Schmiedevorlage und Schlackenstahl-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_chestplate_smithing", "copper_inferno:slagsteel_chestplate", "titanforge/slagsteel_chestplate_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:pyrite_chestplate", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_chestplate", 1, "Smithing table: upgrade the Pyrite Chestplate with an Infernium Upgrade Smithing Template and a Slagsteel Ingot.", "Schmiedetisch: Pyrit-Brustpanzer mit Infernium-Aufwertungs-Schmiedevorlage und Schlackenstahl-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_leggings_smithing", "copper_inferno:slagsteel_leggings", "titanforge/slagsteel_leggings_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:pyrite_leggings", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_leggings", 1, "Smithing table: upgrade the Pyrite Leggings with an Infernium Upgrade Smithing Template and a Slagsteel Ingot.", "Schmiedetisch: Pyrit-Beinschutz mit Infernium-Aufwertungs-Schmiedevorlage und Schlackenstahl-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/slagsteel_boots_smithing", "copper_inferno:slagsteel_boots", "titanforge/slagsteel_boots_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:pyrite_boots", "copper_inferno:slagsteel_ingot", "", "", ""},
				"copper_inferno:slagsteel_boots", 1, "Smithing table: upgrade the Pyrite Boots with an Infernium Upgrade Smithing Template and a Slagsteel Ingot.", "Schmiedetisch: Pyrit-Stiefel mit Infernium-Aufwertungs-Schmiedevorlage und Schlackenstahl-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("items", "titanforge/cinderforge_ingot", "copper_inferno:cinderforge_ingot", "titanforge/cinderforge_ingot",
				new String[] {"copper_inferno:slagsteel_ingot", "minecraft:diamond", "minecraft:blaze_rod", "", "", "", "", "", ""},
				"copper_inferno:cinderforge_ingot", 2, "Alloy the shown ingredients into 2x Cinderforge Ingot.", "Die gezeigten Zutaten zu 2x Zunderschmiede-Barren legieren."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_sword", "copper_inferno:cinderforge_sword", "titanforge/cinderforge_sword",
				new String[] {"", "copper_inferno:cinderforge_ingot", "", "", "copper_inferno:cinderforge_ingot", "", "", "minecraft:stick", ""},
				"copper_inferno:cinderforge_sword", 1, "Craft the Cinderforge Sword from Cinderforge Ingots and sticks.", "Zunderschmiede-Schwert aus Zunderschmiede-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_pickaxe", "copper_inferno:cinderforge_pickaxe", "titanforge/cinderforge_pickaxe",
				new String[] {"copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:cinderforge_pickaxe", 1, "Craft the Cinderforge Pickaxe from Cinderforge Ingots and sticks.", "Zunderschmiede-Spitzhacke aus Zunderschmiede-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_axe", "copper_inferno:cinderforge_axe", "titanforge/cinderforge_axe",
				new String[] {"copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:cinderforge_axe", 1, "Craft the Cinderforge Axe from Cinderforge Ingots and sticks.", "Zunderschmiede-Axt aus Zunderschmiede-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_shovel", "copper_inferno:cinderforge_shovel", "titanforge/cinderforge_shovel",
				new String[] {"", "copper_inferno:cinderforge_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:cinderforge_shovel", 1, "Craft the Cinderforge Shovel from Cinderforge Ingots and sticks.", "Zunderschmiede-Schaufel aus Zunderschmiede-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_hoe", "copper_inferno:cinderforge_hoe", "titanforge/cinderforge_hoe",
				new String[] {"copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:cinderforge_hoe", 1, "Craft the Cinderforge Hoe from Cinderforge Ingots and sticks.", "Zunderschmiede-Hacke aus Zunderschmiede-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_helmet", "copper_inferno:cinderforge_helmet", "titanforge/cinderforge_helmet",
				new String[] {"copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_helmet", 1, "Craft the Cinderforge Helmet from Cinderforge Ingots.", "Zunderschmiede-Helm aus Zunderschmiede-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_chestplate", "copper_inferno:cinderforge_chestplate", "titanforge/cinderforge_chestplate",
				new String[] {"copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot"},
				"copper_inferno:cinderforge_chestplate", 1, "Craft the Cinderforge Chestplate from Cinderforge Ingots.", "Zunderschmiede-Brustpanzer aus Zunderschmiede-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_leggings", "copper_inferno:cinderforge_leggings", "titanforge/cinderforge_leggings",
				new String[] {"copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot"},
				"copper_inferno:cinderforge_leggings", 1, "Craft the Cinderforge Leggings from Cinderforge Ingots.", "Zunderschmiede-Beinschutz aus Zunderschmiede-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_boots", "copper_inferno:cinderforge_boots", "titanforge/cinderforge_boots",
				new String[] {"copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_boots", 1, "Craft the Cinderforge Boots from Cinderforge Ingots.", "Zunderschmiede-Stiefel aus Zunderschmiede-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_charm", "copper_inferno:cinderforge_charm", "titanforge/cinderforge_charm",
				new String[] {"", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "minecraft:string", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", ""},
				"copper_inferno:cinderforge_charm", 1, "A pocket Cinderforge charm for luck at the forge.", "Ein Zunderschmiede-Gl\u00fccksbringer f\u00fcr Gl\u00fcck an der Esse."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_totem", "copper_inferno:cinderforge_totem", "titanforge/cinderforge_totem",
				new String[] {"", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "minecraft:emerald", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", ""},
				"copper_inferno:cinderforge_totem", 1, "A carved Cinderforge totem watching over the smithy.", "Ein geschnitztes Zunderschmiede-Totem wacht \u00fcber die Schmiede."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_ring", "copper_inferno:cinderforge_ring", "titanforge/cinderforge_ring",
				new String[] {"", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "minecraft:gold_nugget", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", ""},
				"copper_inferno:cinderforge_ring", 1, "A polished Cinderforge ring, warm to the touch.", "Ein polierter Zunderschmiede-Ring, warm bei Ber\u00fchrung."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_amulet", "copper_inferno:cinderforge_amulet", "titanforge/cinderforge_amulet",
				new String[] {"", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "minecraft:amethyst_shard", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", ""},
				"copper_inferno:cinderforge_amulet", 1, "The Cinderforge amulet swings on a silver chain.", "Das Zunderschmiede-Amulett schwingt an silberner Kette."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_talisman", "copper_inferno:cinderforge_talisman", "titanforge/cinderforge_talisman",
				new String[] {"", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "minecraft:blaze_powder", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", ""},
				"copper_inferno:cinderforge_talisman", 1, "This Cinderforge talisman is etched with forge runes.", "Dieser Zunderschmiede-Talisman ist mit Schmiederunen graviert."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_medallion", "copper_inferno:cinderforge_medallion", "titanforge/cinderforge_medallion",
				new String[] {"", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", "minecraft:copper_ingot", "copper_inferno:cinderforge_ingot", "", "copper_inferno:cinderforge_ingot", ""},
				"copper_inferno:cinderforge_medallion", 1, "The Cinderforge medallion is awarded to master smiths.", "Das Zunderschmiede-Medaillon wird an Meisterschmiede verliehen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_sword_smithing", "copper_inferno:cinderforge_sword", "titanforge/cinderforge_sword_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:slagsteel_sword", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_sword", 1, "Smithing table: upgrade the Slagsteel Sword with an Infernium Upgrade Smithing Template and a Cinderforge Ingot.", "Schmiedetisch: Schlackenstahl-Schwert mit Infernium-Aufwertungs-Schmiedevorlage und Zunderschmiede-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_pickaxe_smithing", "copper_inferno:cinderforge_pickaxe", "titanforge/cinderforge_pickaxe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:slagsteel_pickaxe", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_pickaxe", 1, "Smithing table: upgrade the Slagsteel Pickaxe with an Infernium Upgrade Smithing Template and a Cinderforge Ingot.", "Schmiedetisch: Schlackenstahl-Spitzhacke mit Infernium-Aufwertungs-Schmiedevorlage und Zunderschmiede-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_axe_smithing", "copper_inferno:cinderforge_axe", "titanforge/cinderforge_axe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:slagsteel_axe", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_axe", 1, "Smithing table: upgrade the Slagsteel Axe with an Infernium Upgrade Smithing Template and a Cinderforge Ingot.", "Schmiedetisch: Schlackenstahl-Axt mit Infernium-Aufwertungs-Schmiedevorlage und Zunderschmiede-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_shovel_smithing", "copper_inferno:cinderforge_shovel", "titanforge/cinderforge_shovel_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:slagsteel_shovel", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_shovel", 1, "Smithing table: upgrade the Slagsteel Shovel with an Infernium Upgrade Smithing Template and a Cinderforge Ingot.", "Schmiedetisch: Schlackenstahl-Schaufel mit Infernium-Aufwertungs-Schmiedevorlage und Zunderschmiede-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_hoe_smithing", "copper_inferno:cinderforge_hoe", "titanforge/cinderforge_hoe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:slagsteel_hoe", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_hoe", 1, "Smithing table: upgrade the Slagsteel Hoe with an Infernium Upgrade Smithing Template and a Cinderforge Ingot.", "Schmiedetisch: Schlackenstahl-Hacke mit Infernium-Aufwertungs-Schmiedevorlage und Zunderschmiede-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_helmet_smithing", "copper_inferno:cinderforge_helmet", "titanforge/cinderforge_helmet_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:slagsteel_helmet", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_helmet", 1, "Smithing table: upgrade the Slagsteel Helmet with an Infernium Upgrade Smithing Template and a Cinderforge Ingot.", "Schmiedetisch: Schlackenstahl-Helm mit Infernium-Aufwertungs-Schmiedevorlage und Zunderschmiede-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_chestplate_smithing", "copper_inferno:cinderforge_chestplate", "titanforge/cinderforge_chestplate_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:slagsteel_chestplate", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_chestplate", 1, "Smithing table: upgrade the Slagsteel Chestplate with an Infernium Upgrade Smithing Template and a Cinderforge Ingot.", "Schmiedetisch: Schlackenstahl-Brustpanzer mit Infernium-Aufwertungs-Schmiedevorlage und Zunderschmiede-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_leggings_smithing", "copper_inferno:cinderforge_leggings", "titanforge/cinderforge_leggings_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:slagsteel_leggings", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_leggings", 1, "Smithing table: upgrade the Slagsteel Leggings with an Infernium Upgrade Smithing Template and a Cinderforge Ingot.", "Schmiedetisch: Schlackenstahl-Beinschutz mit Infernium-Aufwertungs-Schmiedevorlage und Zunderschmiede-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/cinderforge_boots_smithing", "copper_inferno:cinderforge_boots", "titanforge/cinderforge_boots_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:slagsteel_boots", "copper_inferno:cinderforge_ingot", "", "", ""},
				"copper_inferno:cinderforge_boots", 1, "Smithing table: upgrade the Slagsteel Boots with an Infernium Upgrade Smithing Template and a Cinderforge Ingot.", "Schmiedetisch: Schlackenstahl-Stiefel mit Infernium-Aufwertungs-Schmiedevorlage und Zunderschmiede-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("items", "titanforge/molten_titan_ingot", "copper_inferno:molten_titan_ingot", "titanforge/molten_titan_ingot",
				new String[] {"copper_inferno:cinderforge_ingot", "minecraft:magma_block", "minecraft:ghast_tear", "", "", "", "", "", ""},
				"copper_inferno:molten_titan_ingot", 2, "Alloy the shown ingredients into 2x Molten Titan Ingot.", "Die gezeigten Zutaten zu 2x Schmelztitan-Barren legieren."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_sword", "copper_inferno:molten_titan_sword", "titanforge/molten_titan_sword",
				new String[] {"", "copper_inferno:molten_titan_ingot", "", "", "copper_inferno:molten_titan_ingot", "", "", "minecraft:stick", ""},
				"copper_inferno:molten_titan_sword", 1, "Craft the Molten Titan Sword from Molten Titan Ingots and sticks.", "Schmelztitan-Schwert aus Schmelztitan-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_pickaxe", "copper_inferno:molten_titan_pickaxe", "titanforge/molten_titan_pickaxe",
				new String[] {"copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:molten_titan_pickaxe", 1, "Craft the Molten Titan Pickaxe from Molten Titan Ingots and sticks.", "Schmelztitan-Spitzhacke aus Schmelztitan-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_axe", "copper_inferno:molten_titan_axe", "titanforge/molten_titan_axe",
				new String[] {"copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:molten_titan_axe", 1, "Craft the Molten Titan Axe from Molten Titan Ingots and sticks.", "Schmelztitan-Axt aus Schmelztitan-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_shovel", "copper_inferno:molten_titan_shovel", "titanforge/molten_titan_shovel",
				new String[] {"", "copper_inferno:molten_titan_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:molten_titan_shovel", 1, "Craft the Molten Titan Shovel from Molten Titan Ingots and sticks.", "Schmelztitan-Schaufel aus Schmelztitan-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_hoe", "copper_inferno:molten_titan_hoe", "titanforge/molten_titan_hoe",
				new String[] {"copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:molten_titan_hoe", 1, "Craft the Molten Titan Hoe from Molten Titan Ingots and sticks.", "Schmelztitan-Hacke aus Schmelztitan-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_helmet", "copper_inferno:molten_titan_helmet", "titanforge/molten_titan_helmet",
				new String[] {"copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_helmet", 1, "Craft the Molten Titan Helmet from Molten Titan Ingots.", "Schmelztitan-Helm aus Schmelztitan-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_chestplate", "copper_inferno:molten_titan_chestplate", "titanforge/molten_titan_chestplate",
				new String[] {"copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot"},
				"copper_inferno:molten_titan_chestplate", 1, "Craft the Molten Titan Chestplate from Molten Titan Ingots.", "Schmelztitan-Brustpanzer aus Schmelztitan-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_leggings", "copper_inferno:molten_titan_leggings", "titanforge/molten_titan_leggings",
				new String[] {"copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot"},
				"copper_inferno:molten_titan_leggings", 1, "Craft the Molten Titan Leggings from Molten Titan Ingots.", "Schmelztitan-Beinschutz aus Schmelztitan-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_boots", "copper_inferno:molten_titan_boots", "titanforge/molten_titan_boots",
				new String[] {"copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_boots", 1, "Craft the Molten Titan Boots from Molten Titan Ingots.", "Schmelztitan-Stiefel aus Schmelztitan-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_charm", "copper_inferno:molten_titan_charm", "titanforge/molten_titan_charm",
				new String[] {"", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "minecraft:string", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", ""},
				"copper_inferno:molten_titan_charm", 1, "A pocket Molten Titan charm for luck at the forge.", "Ein Schmelztitan-Gl\u00fccksbringer f\u00fcr Gl\u00fcck an der Esse."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_totem", "copper_inferno:molten_titan_totem", "titanforge/molten_titan_totem",
				new String[] {"", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "minecraft:emerald", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", ""},
				"copper_inferno:molten_titan_totem", 1, "A carved Molten Titan totem watching over the smithy.", "Ein geschnitztes Schmelztitan-Totem wacht \u00fcber die Schmiede."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_ring", "copper_inferno:molten_titan_ring", "titanforge/molten_titan_ring",
				new String[] {"", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "minecraft:gold_nugget", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", ""},
				"copper_inferno:molten_titan_ring", 1, "A polished Molten Titan ring, warm to the touch.", "Ein polierter Schmelztitan-Ring, warm bei Ber\u00fchrung."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_amulet", "copper_inferno:molten_titan_amulet", "titanforge/molten_titan_amulet",
				new String[] {"", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "minecraft:amethyst_shard", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", ""},
				"copper_inferno:molten_titan_amulet", 1, "The Molten Titan amulet swings on a silver chain.", "Das Schmelztitan-Amulett schwingt an silberner Kette."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_talisman", "copper_inferno:molten_titan_talisman", "titanforge/molten_titan_talisman",
				new String[] {"", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "minecraft:blaze_powder", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", ""},
				"copper_inferno:molten_titan_talisman", 1, "This Molten Titan talisman is etched with forge runes.", "Dieser Schmelztitan-Talisman ist mit Schmiederunen graviert."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_medallion", "copper_inferno:molten_titan_medallion", "titanforge/molten_titan_medallion",
				new String[] {"", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", "minecraft:copper_ingot", "copper_inferno:molten_titan_ingot", "", "copper_inferno:molten_titan_ingot", ""},
				"copper_inferno:molten_titan_medallion", 1, "The Molten Titan medallion is awarded to master smiths.", "Das Schmelztitan-Medaillon wird an Meisterschmiede verliehen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_sword_smithing", "copper_inferno:molten_titan_sword", "titanforge/molten_titan_sword_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:cinderforge_sword", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_sword", 1, "Smithing table: upgrade the Cinderforge Sword with an Infernium Upgrade Smithing Template and a Molten Titan Ingot.", "Schmiedetisch: Zunderschmiede-Schwert mit Infernium-Aufwertungs-Schmiedevorlage und Schmelztitan-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_pickaxe_smithing", "copper_inferno:molten_titan_pickaxe", "titanforge/molten_titan_pickaxe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:cinderforge_pickaxe", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_pickaxe", 1, "Smithing table: upgrade the Cinderforge Pickaxe with an Infernium Upgrade Smithing Template and a Molten Titan Ingot.", "Schmiedetisch: Zunderschmiede-Spitzhacke mit Infernium-Aufwertungs-Schmiedevorlage und Schmelztitan-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_axe_smithing", "copper_inferno:molten_titan_axe", "titanforge/molten_titan_axe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:cinderforge_axe", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_axe", 1, "Smithing table: upgrade the Cinderforge Axe with an Infernium Upgrade Smithing Template and a Molten Titan Ingot.", "Schmiedetisch: Zunderschmiede-Axt mit Infernium-Aufwertungs-Schmiedevorlage und Schmelztitan-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_shovel_smithing", "copper_inferno:molten_titan_shovel", "titanforge/molten_titan_shovel_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:cinderforge_shovel", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_shovel", 1, "Smithing table: upgrade the Cinderforge Shovel with an Infernium Upgrade Smithing Template and a Molten Titan Ingot.", "Schmiedetisch: Zunderschmiede-Schaufel mit Infernium-Aufwertungs-Schmiedevorlage und Schmelztitan-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_hoe_smithing", "copper_inferno:molten_titan_hoe", "titanforge/molten_titan_hoe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:cinderforge_hoe", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_hoe", 1, "Smithing table: upgrade the Cinderforge Hoe with an Infernium Upgrade Smithing Template and a Molten Titan Ingot.", "Schmiedetisch: Zunderschmiede-Hacke mit Infernium-Aufwertungs-Schmiedevorlage und Schmelztitan-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_helmet_smithing", "copper_inferno:molten_titan_helmet", "titanforge/molten_titan_helmet_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:cinderforge_helmet", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_helmet", 1, "Smithing table: upgrade the Cinderforge Helmet with an Infernium Upgrade Smithing Template and a Molten Titan Ingot.", "Schmiedetisch: Zunderschmiede-Helm mit Infernium-Aufwertungs-Schmiedevorlage und Schmelztitan-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_chestplate_smithing", "copper_inferno:molten_titan_chestplate", "titanforge/molten_titan_chestplate_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:cinderforge_chestplate", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_chestplate", 1, "Smithing table: upgrade the Cinderforge Chestplate with an Infernium Upgrade Smithing Template and a Molten Titan Ingot.", "Schmiedetisch: Zunderschmiede-Brustpanzer mit Infernium-Aufwertungs-Schmiedevorlage und Schmelztitan-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_leggings_smithing", "copper_inferno:molten_titan_leggings", "titanforge/molten_titan_leggings_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:cinderforge_leggings", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_leggings", 1, "Smithing table: upgrade the Cinderforge Leggings with an Infernium Upgrade Smithing Template and a Molten Titan Ingot.", "Schmiedetisch: Zunderschmiede-Beinschutz mit Infernium-Aufwertungs-Schmiedevorlage und Schmelztitan-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/molten_titan_boots_smithing", "copper_inferno:molten_titan_boots", "titanforge/molten_titan_boots_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:cinderforge_boots", "copper_inferno:molten_titan_ingot", "", "", ""},
				"copper_inferno:molten_titan_boots", 1, "Smithing table: upgrade the Cinderforge Boots with an Infernium Upgrade Smithing Template and a Molten Titan Ingot.", "Schmiedetisch: Zunderschmiede-Stiefel mit Infernium-Aufwertungs-Schmiedevorlage und Schmelztitan-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("items", "titanforge/infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "titanforge/infernal_alloy_ingot",
				new String[] {"copper_inferno:molten_titan_ingot", "minecraft:netherite_ingot", "minecraft:blaze_rod", "", "", "", "", "", ""},
				"copper_inferno:infernal_alloy_ingot", 1, "Alloy the shown ingredients into 1x Infernal Alloy Ingot.", "Die gezeigten Zutaten zu 1x H\u00f6llenlegierungs-Barren legieren."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_sword", "copper_inferno:infernal_alloy_sword", "titanforge/infernal_alloy_sword",
				new String[] {"", "copper_inferno:infernal_alloy_ingot", "", "", "copper_inferno:infernal_alloy_ingot", "", "", "minecraft:stick", ""},
				"copper_inferno:infernal_alloy_sword", 1, "Craft the Infernal Alloy Sword from Infernal Alloy Ingots and sticks.", "H\u00f6llenlegierungs-Schwert aus H\u00f6llenlegierungs-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_pickaxe", "copper_inferno:infernal_alloy_pickaxe", "titanforge/infernal_alloy_pickaxe",
				new String[] {"copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:infernal_alloy_pickaxe", 1, "Craft the Infernal Alloy Pickaxe from Infernal Alloy Ingots and sticks.", "H\u00f6llenlegierungs-Spitzhacke aus H\u00f6llenlegierungs-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_axe", "copper_inferno:infernal_alloy_axe", "titanforge/infernal_alloy_axe",
				new String[] {"copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:infernal_alloy_axe", 1, "Craft the Infernal Alloy Axe from Infernal Alloy Ingots and sticks.", "H\u00f6llenlegierungs-Axt aus H\u00f6llenlegierungs-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_shovel", "copper_inferno:infernal_alloy_shovel", "titanforge/infernal_alloy_shovel",
				new String[] {"", "copper_inferno:infernal_alloy_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:infernal_alloy_shovel", 1, "Craft the Infernal Alloy Shovel from Infernal Alloy Ingots and sticks.", "H\u00f6llenlegierungs-Schaufel aus H\u00f6llenlegierungs-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_hoe", "copper_inferno:infernal_alloy_hoe", "titanforge/infernal_alloy_hoe",
				new String[] {"copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "", "", "minecraft:stick", "", "", "minecraft:stick", ""},
				"copper_inferno:infernal_alloy_hoe", 1, "Craft the Infernal Alloy Hoe from Infernal Alloy Ingots and sticks.", "H\u00f6llenlegierungs-Hacke aus H\u00f6llenlegierungs-Barren und St\u00f6cken herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_helmet", "copper_inferno:infernal_alloy_helmet", "titanforge/infernal_alloy_helmet",
				new String[] {"copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_helmet", 1, "Craft the Infernal Alloy Helmet from Infernal Alloy Ingots.", "H\u00f6llenlegierungs-Helm aus H\u00f6llenlegierungs-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_chestplate", "copper_inferno:infernal_alloy_chestplate", "titanforge/infernal_alloy_chestplate",
				new String[] {"copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot"},
				"copper_inferno:infernal_alloy_chestplate", 1, "Craft the Infernal Alloy Chestplate from Infernal Alloy Ingots.", "H\u00f6llenlegierungs-Brustpanzer aus H\u00f6llenlegierungs-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_leggings", "copper_inferno:infernal_alloy_leggings", "titanforge/infernal_alloy_leggings",
				new String[] {"copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot"},
				"copper_inferno:infernal_alloy_leggings", 1, "Craft the Infernal Alloy Leggings from Infernal Alloy Ingots.", "H\u00f6llenlegierungs-Beinschutz aus H\u00f6llenlegierungs-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_boots", "copper_inferno:infernal_alloy_boots", "titanforge/infernal_alloy_boots",
				new String[] {"copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_boots", 1, "Craft the Infernal Alloy Boots from Infernal Alloy Ingots.", "H\u00f6llenlegierungs-Stiefel aus H\u00f6llenlegierungs-Barren herstellen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_charm", "copper_inferno:infernal_alloy_charm", "titanforge/infernal_alloy_charm",
				new String[] {"", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "minecraft:string", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", ""},
				"copper_inferno:infernal_alloy_charm", 1, "A pocket Infernal Alloy charm for luck at the forge.", "Ein H\u00f6llenlegierungs-Gl\u00fccksbringer f\u00fcr Gl\u00fcck an der Esse."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_totem", "copper_inferno:infernal_alloy_totem", "titanforge/infernal_alloy_totem",
				new String[] {"", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "minecraft:emerald", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", ""},
				"copper_inferno:infernal_alloy_totem", 1, "A carved Infernal Alloy totem watching over the smithy.", "Ein geschnitztes H\u00f6llenlegierungs-Totem wacht \u00fcber die Schmiede."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_ring", "copper_inferno:infernal_alloy_ring", "titanforge/infernal_alloy_ring",
				new String[] {"", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "minecraft:gold_nugget", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", ""},
				"copper_inferno:infernal_alloy_ring", 1, "A polished Infernal Alloy ring, warm to the touch.", "Ein polierter H\u00f6llenlegierungs-Ring, warm bei Ber\u00fchrung."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_amulet", "copper_inferno:infernal_alloy_amulet", "titanforge/infernal_alloy_amulet",
				new String[] {"", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "minecraft:amethyst_shard", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", ""},
				"copper_inferno:infernal_alloy_amulet", 1, "The Infernal Alloy amulet swings on a silver chain.", "Das H\u00f6llenlegierungs-Amulett schwingt an silberner Kette."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_talisman", "copper_inferno:infernal_alloy_talisman", "titanforge/infernal_alloy_talisman",
				new String[] {"", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "minecraft:blaze_powder", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", ""},
				"copper_inferno:infernal_alloy_talisman", 1, "This Infernal Alloy talisman is etched with forge runes.", "Dieser H\u00f6llenlegierungs-Talisman ist mit Schmiederunen graviert."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_medallion", "copper_inferno:infernal_alloy_medallion", "titanforge/infernal_alloy_medallion",
				new String[] {"", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", "minecraft:copper_ingot", "copper_inferno:infernal_alloy_ingot", "", "copper_inferno:infernal_alloy_ingot", ""},
				"copper_inferno:infernal_alloy_medallion", 1, "The Infernal Alloy medallion is awarded to master smiths.", "Das H\u00f6llenlegierungs-Medaillon wird an Meisterschmiede verliehen."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_sword_smithing", "copper_inferno:infernal_alloy_sword", "titanforge/infernal_alloy_sword_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:molten_titan_sword", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_sword", 1, "Smithing table: upgrade the Molten Titan Sword with an Infernium Upgrade Smithing Template and a Infernal Alloy Ingot.", "Schmiedetisch: Schmelztitan-Schwert mit Infernium-Aufwertungs-Schmiedevorlage und H\u00f6llenlegierungs-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_pickaxe_smithing", "copper_inferno:infernal_alloy_pickaxe", "titanforge/infernal_alloy_pickaxe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:molten_titan_pickaxe", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_pickaxe", 1, "Smithing table: upgrade the Molten Titan Pickaxe with an Infernium Upgrade Smithing Template and a Infernal Alloy Ingot.", "Schmiedetisch: Schmelztitan-Spitzhacke mit Infernium-Aufwertungs-Schmiedevorlage und H\u00f6llenlegierungs-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_axe_smithing", "copper_inferno:infernal_alloy_axe", "titanforge/infernal_alloy_axe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:molten_titan_axe", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_axe", 1, "Smithing table: upgrade the Molten Titan Axe with an Infernium Upgrade Smithing Template and a Infernal Alloy Ingot.", "Schmiedetisch: Schmelztitan-Axt mit Infernium-Aufwertungs-Schmiedevorlage und H\u00f6llenlegierungs-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_shovel_smithing", "copper_inferno:infernal_alloy_shovel", "titanforge/infernal_alloy_shovel_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:molten_titan_shovel", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_shovel", 1, "Smithing table: upgrade the Molten Titan Shovel with an Infernium Upgrade Smithing Template and a Infernal Alloy Ingot.", "Schmiedetisch: Schmelztitan-Schaufel mit Infernium-Aufwertungs-Schmiedevorlage und H\u00f6llenlegierungs-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_hoe_smithing", "copper_inferno:infernal_alloy_hoe", "titanforge/infernal_alloy_hoe_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:molten_titan_hoe", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_hoe", 1, "Smithing table: upgrade the Molten Titan Hoe with an Infernium Upgrade Smithing Template and a Infernal Alloy Ingot.", "Schmiedetisch: Schmelztitan-Hacke mit Infernium-Aufwertungs-Schmiedevorlage und H\u00f6llenlegierungs-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_helmet_smithing", "copper_inferno:infernal_alloy_helmet", "titanforge/infernal_alloy_helmet_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:molten_titan_helmet", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_helmet", 1, "Smithing table: upgrade the Molten Titan Helmet with an Infernium Upgrade Smithing Template and a Infernal Alloy Ingot.", "Schmiedetisch: Schmelztitan-Helm mit Infernium-Aufwertungs-Schmiedevorlage und H\u00f6llenlegierungs-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_chestplate_smithing", "copper_inferno:infernal_alloy_chestplate", "titanforge/infernal_alloy_chestplate_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:molten_titan_chestplate", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_chestplate", 1, "Smithing table: upgrade the Molten Titan Chestplate with an Infernium Upgrade Smithing Template and a Infernal Alloy Ingot.", "Schmiedetisch: Schmelztitan-Brustpanzer mit Infernium-Aufwertungs-Schmiedevorlage und H\u00f6llenlegierungs-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_leggings_smithing", "copper_inferno:infernal_alloy_leggings", "titanforge/infernal_alloy_leggings_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:molten_titan_leggings", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_leggings", 1, "Smithing table: upgrade the Molten Titan Leggings with an Infernium Upgrade Smithing Template and a Infernal Alloy Ingot.", "Schmiedetisch: Schmelztitan-Beinschutz mit Infernium-Aufwertungs-Schmiedevorlage und H\u00f6llenlegierungs-Barren aufwerten."));

		HandbookEntries.add(new HandbookEntry("gear", "titanforge/infernal_alloy_boots_smithing", "copper_inferno:infernal_alloy_boots", "titanforge/infernal_alloy_boots_smithing",
				new String[] {"", "", "", "copper_inferno:infernium_upgrade_smithing_template", "copper_inferno:molten_titan_boots", "copper_inferno:infernal_alloy_ingot", "", "", ""},
				"copper_inferno:infernal_alloy_boots", 1, "Smithing table: upgrade the Molten Titan Boots with an Infernium Upgrade Smithing Template and a Infernal Alloy Ingot.", "Schmiedetisch: Schmelztitan-Stiefel mit Infernium-Aufwertungs-Schmiedevorlage und H\u00f6llenlegierungs-Barren aufwerten."));
	}
}
