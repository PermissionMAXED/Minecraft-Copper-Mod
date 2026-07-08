package net.sonic0810.copperinferno.feature.forgeparts;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the forge parts: one "items" overview per metal plus one
 * recipe page for every JSON under {@code data/copper_inferno/recipe/forgeparts/}
 * (crafting and smelting). Entry texts and grids mirror the recipe JSONs emitted by
 * {@code devtools/gen/forgeparts_gen.py}; {@code devtools/check_handbook.py} parses
 * the inline {@code new HandbookEntry(...)} literals positionally, so keep them
 * inline.
 */
final class ForgePartsHandbook {
	private ForgePartsHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_emberite", "copper_inferno:emberite_ingot", null,
				null,
				null, 0, "The Emberite forge-part chain: grind Emberite Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Emberit-Schmiedeteilkette: Emberitstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_cindrium", "copper_inferno:cindrium_ingot", null,
				null,
				null, 0, "The Cindrium forge-part chain: grind Cindrium Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Cindrium-Schmiedeteilkette: Cindriumstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_pyrium", "copper_inferno:pyrium_ingot", null,
				null,
				null, 0, "The Pyrium forge-part chain: grind Pyrium Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Pyrium-Schmiedeteilkette: Pyriumstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_scorium", "copper_inferno:scorium_ingot", null,
				null,
				null, 0, "The Scorium forge-part chain: grind Scorium Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Skorium-Schmiedeteilkette: Skoriumstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_brazium", "copper_inferno:brazium_ingot", null,
				null,
				null, 0, "The Brazium forge-part chain: grind Brazium Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Brazium-Schmiedeteilkette: Braziumstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_volkanite", "copper_inferno:volkanite_ingot", null,
				null,
				null, 0, "The Volkanite forge-part chain: grind Volkanite Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Volkanit-Schmiedeteilkette: Volkanitstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_fumarite", "copper_inferno:fumarite_ingot", null,
				null,
				null, 0, "The Fumarite forge-part chain: grind Fumarite Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Fumarit-Schmiedeteilkette: Fumaritstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_ignitium", "copper_inferno:ignitium_ingot", null,
				null,
				null, 0, "The Ignitium forge-part chain: grind Ignitium Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Ignitium-Schmiedeteilkette: Ignitiumstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_calderium", "copper_inferno:calderium_ingot", null,
				null,
				null, 0, "The Calderium forge-part chain: grind Calderium Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Calderium-Schmiedeteilkette: Calderiumstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/set_obsidium", "copper_inferno:obsidium_ingot", null,
				null,
				null, 0, "The Obsidium forge-part chain: grind Obsidium Dust, refine it into powder and smelt ingots, then work them into alloys, gems, shards, rods, plates, gears, coils, cores and catalysts.", "Die Obsidium-Schmiedeteilkette: Obsidiumstaub mahlen, zu Pulver verfeinern und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_dust", "copper_inferno:emberite_dust", "forgeparts/emberite_dust",
				new String[] {"minecraft:magma_cream", "", "minecraft:magma_cream", "", "copper_inferno:obsidium_dust", "", "minecraft:magma_cream", "", "minecraft:magma_cream"},
				"copper_inferno:emberite_dust", 4, "Craft 4x Emberite Dust at a crafting table.", "Stellt 4x Emberitstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_powder", "copper_inferno:emberite_powder", "forgeparts/emberite_powder",
				new String[] {"copper_inferno:emberite_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:emberite_powder", 2, "Craft 2x Emberite Powder at a crafting table.", "Stellt 2x Emberitpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_ingot", "copper_inferno:emberite_ingot", "forgeparts/emberite_ingot",
				new String[] {"", "", "", "", "copper_inferno:emberite_dust", "", "", "", ""},
				"copper_inferno:emberite_ingot", 1, "Smelting Emberite Dust in a furnace yields Emberite Ingot.", "Emberitstaub im Ofen geschmolzen ergibt Emberitbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_alloy", "copper_inferno:emberite_alloy", "forgeparts/emberite_alloy",
				new String[] {"copper_inferno:emberite_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:emberite_alloy", 2, "Craft 2x Emberite Alloy at a crafting table.", "Stellt 2x Emberitlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_gem", "copper_inferno:emberite_gem", "forgeparts/emberite_gem",
				new String[] {"", "copper_inferno:emberite_powder", "", "copper_inferno:emberite_powder", "minecraft:amethyst_shard", "copper_inferno:emberite_powder", "", "copper_inferno:emberite_powder", ""},
				"copper_inferno:emberite_gem", 1, "Craft 1x Emberite Gem at a crafting table.", "Stellt 1x Emberitjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_shard", "copper_inferno:emberite_shard", "forgeparts/emberite_shard",
				new String[] {"copper_inferno:emberite_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:emberite_shard", 4, "Craft 4x Emberite Shard at a crafting table.", "Stellt 4x Emberitsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_rod", "copper_inferno:emberite_rod", "forgeparts/emberite_rod",
				new String[] {"copper_inferno:emberite_ingot", "", "", "copper_inferno:emberite_ingot", "", "", "", "", ""},
				"copper_inferno:emberite_rod", 4, "Craft 4x Emberite Rod at a crafting table.", "Stellt 4x Emberitstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_plate", "copper_inferno:emberite_plate", "forgeparts/emberite_plate",
				new String[] {"copper_inferno:emberite_ingot", "copper_inferno:emberite_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:emberite_plate", 2, "Craft 2x Emberite Plate at a crafting table.", "Stellt 2x Emberitplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_gear", "copper_inferno:emberite_gear", "forgeparts/emberite_gear",
				new String[] {"", "copper_inferno:emberite_rod", "", "copper_inferno:emberite_rod", "copper_inferno:emberite_ingot", "copper_inferno:emberite_rod", "", "copper_inferno:emberite_rod", ""},
				"copper_inferno:emberite_gear", 1, "Craft 1x Emberite Gear at a crafting table.", "Stellt 1x Emberitzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_coil", "copper_inferno:emberite_coil", "forgeparts/emberite_coil",
				new String[] {"copper_inferno:emberite_rod", "copper_inferno:emberite_rod", "copper_inferno:emberite_rod", "copper_inferno:emberite_rod", "", "copper_inferno:emberite_rod", "copper_inferno:emberite_rod", "copper_inferno:emberite_rod", "copper_inferno:emberite_rod"},
				"copper_inferno:emberite_coil", 2, "Craft 2x Emberite Coil at a crafting table.", "Stellt 2x Emberitspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_core", "copper_inferno:emberite_core", "forgeparts/emberite_core",
				new String[] {"", "copper_inferno:emberite_plate", "", "copper_inferno:emberite_plate", "copper_inferno:emberite_gem", "copper_inferno:emberite_plate", "", "copper_inferno:emberite_plate", ""},
				"copper_inferno:emberite_core", 1, "Craft 1x Emberite Core at a crafting table.", "Stellt 1x Emberitkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/emberite_catalyst", "copper_inferno:emberite_catalyst", "forgeparts/emberite_catalyst",
				new String[] {"copper_inferno:emberite_powder", "copper_inferno:emberite_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:emberite_catalyst", 2, "Craft 2x Emberite Catalyst at a crafting table.", "Stellt 2x Emberitkatalysator an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_dust", "copper_inferno:cindrium_dust", "forgeparts/cindrium_dust",
				new String[] {"minecraft:charcoal", "", "minecraft:charcoal", "", "copper_inferno:emberite_dust", "", "minecraft:charcoal", "", "minecraft:charcoal"},
				"copper_inferno:cindrium_dust", 4, "Craft 4x Cindrium Dust at a crafting table.", "Stellt 4x Cindriumstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_powder", "copper_inferno:cindrium_powder", "forgeparts/cindrium_powder",
				new String[] {"copper_inferno:cindrium_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:cindrium_powder", 2, "Craft 2x Cindrium Powder at a crafting table.", "Stellt 2x Cindriumpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_ingot", "copper_inferno:cindrium_ingot", "forgeparts/cindrium_ingot",
				new String[] {"", "", "", "", "copper_inferno:cindrium_dust", "", "", "", ""},
				"copper_inferno:cindrium_ingot", 1, "Smelting Cindrium Dust in a furnace yields Cindrium Ingot.", "Cindriumstaub im Ofen geschmolzen ergibt Cindriumbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_alloy", "copper_inferno:cindrium_alloy", "forgeparts/cindrium_alloy",
				new String[] {"copper_inferno:cindrium_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:cindrium_alloy", 2, "Craft 2x Cindrium Alloy at a crafting table.", "Stellt 2x Cindriumlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_gem", "copper_inferno:cindrium_gem", "forgeparts/cindrium_gem",
				new String[] {"", "copper_inferno:cindrium_powder", "", "copper_inferno:cindrium_powder", "minecraft:amethyst_shard", "copper_inferno:cindrium_powder", "", "copper_inferno:cindrium_powder", ""},
				"copper_inferno:cindrium_gem", 1, "Craft 1x Cindrium Gem at a crafting table.", "Stellt 1x Cindriumjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_shard", "copper_inferno:cindrium_shard", "forgeparts/cindrium_shard",
				new String[] {"copper_inferno:cindrium_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:cindrium_shard", 4, "Craft 4x Cindrium Shard at a crafting table.", "Stellt 4x Cindriumsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_rod", "copper_inferno:cindrium_rod", "forgeparts/cindrium_rod",
				new String[] {"copper_inferno:cindrium_ingot", "", "", "copper_inferno:cindrium_ingot", "", "", "", "", ""},
				"copper_inferno:cindrium_rod", 4, "Craft 4x Cindrium Rod at a crafting table.", "Stellt 4x Cindriumstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_plate", "copper_inferno:cindrium_plate", "forgeparts/cindrium_plate",
				new String[] {"copper_inferno:cindrium_ingot", "copper_inferno:cindrium_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:cindrium_plate", 2, "Craft 2x Cindrium Plate at a crafting table.", "Stellt 2x Cindriumplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_gear", "copper_inferno:cindrium_gear", "forgeparts/cindrium_gear",
				new String[] {"", "copper_inferno:cindrium_rod", "", "copper_inferno:cindrium_rod", "copper_inferno:cindrium_ingot", "copper_inferno:cindrium_rod", "", "copper_inferno:cindrium_rod", ""},
				"copper_inferno:cindrium_gear", 1, "Craft 1x Cindrium Gear at a crafting table.", "Stellt 1x Cindriumzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_coil", "copper_inferno:cindrium_coil", "forgeparts/cindrium_coil",
				new String[] {"copper_inferno:cindrium_rod", "copper_inferno:cindrium_rod", "copper_inferno:cindrium_rod", "copper_inferno:cindrium_rod", "", "copper_inferno:cindrium_rod", "copper_inferno:cindrium_rod", "copper_inferno:cindrium_rod", "copper_inferno:cindrium_rod"},
				"copper_inferno:cindrium_coil", 2, "Craft 2x Cindrium Coil at a crafting table.", "Stellt 2x Cindriumspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_core", "copper_inferno:cindrium_core", "forgeparts/cindrium_core",
				new String[] {"", "copper_inferno:cindrium_plate", "", "copper_inferno:cindrium_plate", "copper_inferno:cindrium_gem", "copper_inferno:cindrium_plate", "", "copper_inferno:cindrium_plate", ""},
				"copper_inferno:cindrium_core", 1, "Craft 1x Cindrium Core at a crafting table.", "Stellt 1x Cindriumkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/cindrium_catalyst", "copper_inferno:cindrium_catalyst", "forgeparts/cindrium_catalyst",
				new String[] {"copper_inferno:cindrium_powder", "copper_inferno:cindrium_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:cindrium_catalyst", 2, "Craft 2x Cindrium Catalyst at a crafting table.", "Stellt 2x Cindriumkatalysator an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_dust", "copper_inferno:pyrium_dust", "forgeparts/pyrium_dust",
				new String[] {"minecraft:blaze_powder", "", "minecraft:blaze_powder", "", "copper_inferno:cindrium_dust", "", "minecraft:blaze_powder", "", "minecraft:blaze_powder"},
				"copper_inferno:pyrium_dust", 4, "Craft 4x Pyrium Dust at a crafting table.", "Stellt 4x Pyriumstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_powder", "copper_inferno:pyrium_powder", "forgeparts/pyrium_powder",
				new String[] {"copper_inferno:pyrium_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:pyrium_powder", 2, "Craft 2x Pyrium Powder at a crafting table.", "Stellt 2x Pyriumpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_ingot", "copper_inferno:pyrium_ingot", "forgeparts/pyrium_ingot",
				new String[] {"", "", "", "", "copper_inferno:pyrium_dust", "", "", "", ""},
				"copper_inferno:pyrium_ingot", 1, "Smelting Pyrium Dust in a furnace yields Pyrium Ingot.", "Pyriumstaub im Ofen geschmolzen ergibt Pyriumbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_alloy", "copper_inferno:pyrium_alloy", "forgeparts/pyrium_alloy",
				new String[] {"copper_inferno:pyrium_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:pyrium_alloy", 2, "Craft 2x Pyrium Alloy at a crafting table.", "Stellt 2x Pyriumlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_gem", "copper_inferno:pyrium_gem", "forgeparts/pyrium_gem",
				new String[] {"", "copper_inferno:pyrium_powder", "", "copper_inferno:pyrium_powder", "minecraft:amethyst_shard", "copper_inferno:pyrium_powder", "", "copper_inferno:pyrium_powder", ""},
				"copper_inferno:pyrium_gem", 1, "Craft 1x Pyrium Gem at a crafting table.", "Stellt 1x Pyriumjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_shard", "copper_inferno:pyrium_shard", "forgeparts/pyrium_shard",
				new String[] {"copper_inferno:pyrium_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:pyrium_shard", 4, "Craft 4x Pyrium Shard at a crafting table.", "Stellt 4x Pyriumsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_rod", "copper_inferno:pyrium_rod", "forgeparts/pyrium_rod",
				new String[] {"copper_inferno:pyrium_ingot", "", "", "copper_inferno:pyrium_ingot", "", "", "", "", ""},
				"copper_inferno:pyrium_rod", 4, "Craft 4x Pyrium Rod at a crafting table.", "Stellt 4x Pyriumstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_plate", "copper_inferno:pyrium_plate", "forgeparts/pyrium_plate",
				new String[] {"copper_inferno:pyrium_ingot", "copper_inferno:pyrium_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:pyrium_plate", 2, "Craft 2x Pyrium Plate at a crafting table.", "Stellt 2x Pyriumplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_gear", "copper_inferno:pyrium_gear", "forgeparts/pyrium_gear",
				new String[] {"", "copper_inferno:pyrium_rod", "", "copper_inferno:pyrium_rod", "copper_inferno:pyrium_ingot", "copper_inferno:pyrium_rod", "", "copper_inferno:pyrium_rod", ""},
				"copper_inferno:pyrium_gear", 1, "Craft 1x Pyrium Gear at a crafting table.", "Stellt 1x Pyriumzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_coil", "copper_inferno:pyrium_coil", "forgeparts/pyrium_coil",
				new String[] {"copper_inferno:pyrium_rod", "copper_inferno:pyrium_rod", "copper_inferno:pyrium_rod", "copper_inferno:pyrium_rod", "", "copper_inferno:pyrium_rod", "copper_inferno:pyrium_rod", "copper_inferno:pyrium_rod", "copper_inferno:pyrium_rod"},
				"copper_inferno:pyrium_coil", 2, "Craft 2x Pyrium Coil at a crafting table.", "Stellt 2x Pyriumspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_core", "copper_inferno:pyrium_core", "forgeparts/pyrium_core",
				new String[] {"", "copper_inferno:pyrium_plate", "", "copper_inferno:pyrium_plate", "copper_inferno:pyrium_gem", "copper_inferno:pyrium_plate", "", "copper_inferno:pyrium_plate", ""},
				"copper_inferno:pyrium_core", 1, "Craft 1x Pyrium Core at a crafting table.", "Stellt 1x Pyriumkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/pyrium_catalyst", "copper_inferno:pyrium_catalyst", "forgeparts/pyrium_catalyst",
				new String[] {"copper_inferno:pyrium_powder", "copper_inferno:pyrium_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:pyrium_catalyst", 2, "Craft 2x Pyrium Catalyst at a crafting table.", "Stellt 2x Pyriumkatalysator an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_dust", "copper_inferno:scorium_dust", "forgeparts/scorium_dust",
				new String[] {"minecraft:gunpowder", "", "minecraft:gunpowder", "", "copper_inferno:pyrium_dust", "", "minecraft:gunpowder", "", "minecraft:gunpowder"},
				"copper_inferno:scorium_dust", 4, "Craft 4x Scorium Dust at a crafting table.", "Stellt 4x Skoriumstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_powder", "copper_inferno:scorium_powder", "forgeparts/scorium_powder",
				new String[] {"copper_inferno:scorium_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:scorium_powder", 2, "Craft 2x Scorium Powder at a crafting table.", "Stellt 2x Skoriumpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_ingot", "copper_inferno:scorium_ingot", "forgeparts/scorium_ingot",
				new String[] {"", "", "", "", "copper_inferno:scorium_dust", "", "", "", ""},
				"copper_inferno:scorium_ingot", 1, "Smelting Scorium Dust in a furnace yields Scorium Ingot.", "Skoriumstaub im Ofen geschmolzen ergibt Skoriumbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_alloy", "copper_inferno:scorium_alloy", "forgeparts/scorium_alloy",
				new String[] {"copper_inferno:scorium_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:scorium_alloy", 2, "Craft 2x Scorium Alloy at a crafting table.", "Stellt 2x Skoriumlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_gem", "copper_inferno:scorium_gem", "forgeparts/scorium_gem",
				new String[] {"", "copper_inferno:scorium_powder", "", "copper_inferno:scorium_powder", "minecraft:amethyst_shard", "copper_inferno:scorium_powder", "", "copper_inferno:scorium_powder", ""},
				"copper_inferno:scorium_gem", 1, "Craft 1x Scorium Gem at a crafting table.", "Stellt 1x Skoriumjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_shard", "copper_inferno:scorium_shard", "forgeparts/scorium_shard",
				new String[] {"copper_inferno:scorium_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:scorium_shard", 4, "Craft 4x Scorium Shard at a crafting table.", "Stellt 4x Skoriumsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_rod", "copper_inferno:scorium_rod", "forgeparts/scorium_rod",
				new String[] {"copper_inferno:scorium_ingot", "", "", "copper_inferno:scorium_ingot", "", "", "", "", ""},
				"copper_inferno:scorium_rod", 4, "Craft 4x Scorium Rod at a crafting table.", "Stellt 4x Skoriumstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_plate", "copper_inferno:scorium_plate", "forgeparts/scorium_plate",
				new String[] {"copper_inferno:scorium_ingot", "copper_inferno:scorium_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:scorium_plate", 2, "Craft 2x Scorium Plate at a crafting table.", "Stellt 2x Skoriumplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_gear", "copper_inferno:scorium_gear", "forgeparts/scorium_gear",
				new String[] {"", "copper_inferno:scorium_rod", "", "copper_inferno:scorium_rod", "copper_inferno:scorium_ingot", "copper_inferno:scorium_rod", "", "copper_inferno:scorium_rod", ""},
				"copper_inferno:scorium_gear", 1, "Craft 1x Scorium Gear at a crafting table.", "Stellt 1x Skoriumzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_coil", "copper_inferno:scorium_coil", "forgeparts/scorium_coil",
				new String[] {"copper_inferno:scorium_rod", "copper_inferno:scorium_rod", "copper_inferno:scorium_rod", "copper_inferno:scorium_rod", "", "copper_inferno:scorium_rod", "copper_inferno:scorium_rod", "copper_inferno:scorium_rod", "copper_inferno:scorium_rod"},
				"copper_inferno:scorium_coil", 2, "Craft 2x Scorium Coil at a crafting table.", "Stellt 2x Skoriumspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_core", "copper_inferno:scorium_core", "forgeparts/scorium_core",
				new String[] {"", "copper_inferno:scorium_plate", "", "copper_inferno:scorium_plate", "copper_inferno:scorium_gem", "copper_inferno:scorium_plate", "", "copper_inferno:scorium_plate", ""},
				"copper_inferno:scorium_core", 1, "Craft 1x Scorium Core at a crafting table.", "Stellt 1x Skoriumkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/scorium_catalyst", "copper_inferno:scorium_catalyst", "forgeparts/scorium_catalyst",
				new String[] {"copper_inferno:scorium_powder", "copper_inferno:scorium_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:scorium_catalyst", 2, "Craft 2x Scorium Catalyst at a crafting table.", "Stellt 2x Skoriumkatalysator an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_dust", "copper_inferno:brazium_dust", "forgeparts/brazium_dust",
				new String[] {"minecraft:gold_nugget", "", "minecraft:gold_nugget", "", "copper_inferno:scorium_dust", "", "minecraft:gold_nugget", "", "minecraft:gold_nugget"},
				"copper_inferno:brazium_dust", 4, "Craft 4x Brazium Dust at a crafting table.", "Stellt 4x Braziumstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_powder", "copper_inferno:brazium_powder", "forgeparts/brazium_powder",
				new String[] {"copper_inferno:brazium_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:brazium_powder", 2, "Craft 2x Brazium Powder at a crafting table.", "Stellt 2x Braziumpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_ingot", "copper_inferno:brazium_ingot", "forgeparts/brazium_ingot",
				new String[] {"", "", "", "", "copper_inferno:brazium_dust", "", "", "", ""},
				"copper_inferno:brazium_ingot", 1, "Smelting Brazium Dust in a furnace yields Brazium Ingot.", "Braziumstaub im Ofen geschmolzen ergibt Braziumbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_alloy", "copper_inferno:brazium_alloy", "forgeparts/brazium_alloy",
				new String[] {"copper_inferno:brazium_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:brazium_alloy", 2, "Craft 2x Brazium Alloy at a crafting table.", "Stellt 2x Braziumlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_gem", "copper_inferno:brazium_gem", "forgeparts/brazium_gem",
				new String[] {"", "copper_inferno:brazium_powder", "", "copper_inferno:brazium_powder", "minecraft:amethyst_shard", "copper_inferno:brazium_powder", "", "copper_inferno:brazium_powder", ""},
				"copper_inferno:brazium_gem", 1, "Craft 1x Brazium Gem at a crafting table.", "Stellt 1x Braziumjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_shard", "copper_inferno:brazium_shard", "forgeparts/brazium_shard",
				new String[] {"copper_inferno:brazium_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:brazium_shard", 4, "Craft 4x Brazium Shard at a crafting table.", "Stellt 4x Braziumsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_rod", "copper_inferno:brazium_rod", "forgeparts/brazium_rod",
				new String[] {"copper_inferno:brazium_ingot", "", "", "copper_inferno:brazium_ingot", "", "", "", "", ""},
				"copper_inferno:brazium_rod", 4, "Craft 4x Brazium Rod at a crafting table.", "Stellt 4x Braziumstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_plate", "copper_inferno:brazium_plate", "forgeparts/brazium_plate",
				new String[] {"copper_inferno:brazium_ingot", "copper_inferno:brazium_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:brazium_plate", 2, "Craft 2x Brazium Plate at a crafting table.", "Stellt 2x Braziumplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_gear", "copper_inferno:brazium_gear", "forgeparts/brazium_gear",
				new String[] {"", "copper_inferno:brazium_rod", "", "copper_inferno:brazium_rod", "copper_inferno:brazium_ingot", "copper_inferno:brazium_rod", "", "copper_inferno:brazium_rod", ""},
				"copper_inferno:brazium_gear", 1, "Craft 1x Brazium Gear at a crafting table.", "Stellt 1x Braziumzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_coil", "copper_inferno:brazium_coil", "forgeparts/brazium_coil",
				new String[] {"copper_inferno:brazium_rod", "copper_inferno:brazium_rod", "copper_inferno:brazium_rod", "copper_inferno:brazium_rod", "", "copper_inferno:brazium_rod", "copper_inferno:brazium_rod", "copper_inferno:brazium_rod", "copper_inferno:brazium_rod"},
				"copper_inferno:brazium_coil", 2, "Craft 2x Brazium Coil at a crafting table.", "Stellt 2x Braziumspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_core", "copper_inferno:brazium_core", "forgeparts/brazium_core",
				new String[] {"", "copper_inferno:brazium_plate", "", "copper_inferno:brazium_plate", "copper_inferno:brazium_gem", "copper_inferno:brazium_plate", "", "copper_inferno:brazium_plate", ""},
				"copper_inferno:brazium_core", 1, "Craft 1x Brazium Core at a crafting table.", "Stellt 1x Braziumkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/brazium_catalyst", "copper_inferno:brazium_catalyst", "forgeparts/brazium_catalyst",
				new String[] {"copper_inferno:brazium_powder", "copper_inferno:brazium_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:brazium_catalyst", 2, "Craft 2x Brazium Catalyst at a crafting table.", "Stellt 2x Braziumkatalysator an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_dust", "copper_inferno:volkanite_dust", "forgeparts/volkanite_dust",
				new String[] {"minecraft:basalt", "", "minecraft:basalt", "", "copper_inferno:brazium_dust", "", "minecraft:basalt", "", "minecraft:basalt"},
				"copper_inferno:volkanite_dust", 4, "Craft 4x Volkanite Dust at a crafting table.", "Stellt 4x Volkanitstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_powder", "copper_inferno:volkanite_powder", "forgeparts/volkanite_powder",
				new String[] {"copper_inferno:volkanite_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:volkanite_powder", 2, "Craft 2x Volkanite Powder at a crafting table.", "Stellt 2x Volkanitpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_ingot", "copper_inferno:volkanite_ingot", "forgeparts/volkanite_ingot",
				new String[] {"", "", "", "", "copper_inferno:volkanite_dust", "", "", "", ""},
				"copper_inferno:volkanite_ingot", 1, "Smelting Volkanite Dust in a furnace yields Volkanite Ingot.", "Volkanitstaub im Ofen geschmolzen ergibt Volkanitbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_alloy", "copper_inferno:volkanite_alloy", "forgeparts/volkanite_alloy",
				new String[] {"copper_inferno:volkanite_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:volkanite_alloy", 2, "Craft 2x Volkanite Alloy at a crafting table.", "Stellt 2x Volkanitlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_gem", "copper_inferno:volkanite_gem", "forgeparts/volkanite_gem",
				new String[] {"", "copper_inferno:volkanite_powder", "", "copper_inferno:volkanite_powder", "minecraft:amethyst_shard", "copper_inferno:volkanite_powder", "", "copper_inferno:volkanite_powder", ""},
				"copper_inferno:volkanite_gem", 1, "Craft 1x Volkanite Gem at a crafting table.", "Stellt 1x Volkanitjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_shard", "copper_inferno:volkanite_shard", "forgeparts/volkanite_shard",
				new String[] {"copper_inferno:volkanite_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:volkanite_shard", 4, "Craft 4x Volkanite Shard at a crafting table.", "Stellt 4x Volkanitsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_rod", "copper_inferno:volkanite_rod", "forgeparts/volkanite_rod",
				new String[] {"copper_inferno:volkanite_ingot", "", "", "copper_inferno:volkanite_ingot", "", "", "", "", ""},
				"copper_inferno:volkanite_rod", 4, "Craft 4x Volkanite Rod at a crafting table.", "Stellt 4x Volkanitstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_plate", "copper_inferno:volkanite_plate", "forgeparts/volkanite_plate",
				new String[] {"copper_inferno:volkanite_ingot", "copper_inferno:volkanite_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:volkanite_plate", 2, "Craft 2x Volkanite Plate at a crafting table.", "Stellt 2x Volkanitplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_gear", "copper_inferno:volkanite_gear", "forgeparts/volkanite_gear",
				new String[] {"", "copper_inferno:volkanite_rod", "", "copper_inferno:volkanite_rod", "copper_inferno:volkanite_ingot", "copper_inferno:volkanite_rod", "", "copper_inferno:volkanite_rod", ""},
				"copper_inferno:volkanite_gear", 1, "Craft 1x Volkanite Gear at a crafting table.", "Stellt 1x Volkanitzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_coil", "copper_inferno:volkanite_coil", "forgeparts/volkanite_coil",
				new String[] {"copper_inferno:volkanite_rod", "copper_inferno:volkanite_rod", "copper_inferno:volkanite_rod", "copper_inferno:volkanite_rod", "", "copper_inferno:volkanite_rod", "copper_inferno:volkanite_rod", "copper_inferno:volkanite_rod", "copper_inferno:volkanite_rod"},
				"copper_inferno:volkanite_coil", 2, "Craft 2x Volkanite Coil at a crafting table.", "Stellt 2x Volkanitspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_core", "copper_inferno:volkanite_core", "forgeparts/volkanite_core",
				new String[] {"", "copper_inferno:volkanite_plate", "", "copper_inferno:volkanite_plate", "copper_inferno:volkanite_gem", "copper_inferno:volkanite_plate", "", "copper_inferno:volkanite_plate", ""},
				"copper_inferno:volkanite_core", 1, "Craft 1x Volkanite Core at a crafting table.", "Stellt 1x Volkanitkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/volkanite_catalyst", "copper_inferno:volkanite_catalyst", "forgeparts/volkanite_catalyst",
				new String[] {"copper_inferno:volkanite_powder", "copper_inferno:volkanite_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:volkanite_catalyst", 2, "Craft 2x Volkanite Catalyst at a crafting table.", "Stellt 2x Volkanitkatalysator an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_dust", "copper_inferno:fumarite_dust", "forgeparts/fumarite_dust",
				new String[] {"minecraft:glowstone_dust", "", "minecraft:glowstone_dust", "", "copper_inferno:volkanite_dust", "", "minecraft:glowstone_dust", "", "minecraft:glowstone_dust"},
				"copper_inferno:fumarite_dust", 4, "Craft 4x Fumarite Dust at a crafting table.", "Stellt 4x Fumaritstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_powder", "copper_inferno:fumarite_powder", "forgeparts/fumarite_powder",
				new String[] {"copper_inferno:fumarite_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:fumarite_powder", 2, "Craft 2x Fumarite Powder at a crafting table.", "Stellt 2x Fumaritpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_ingot", "copper_inferno:fumarite_ingot", "forgeparts/fumarite_ingot",
				new String[] {"", "", "", "", "copper_inferno:fumarite_dust", "", "", "", ""},
				"copper_inferno:fumarite_ingot", 1, "Smelting Fumarite Dust in a furnace yields Fumarite Ingot.", "Fumaritstaub im Ofen geschmolzen ergibt Fumaritbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_alloy", "copper_inferno:fumarite_alloy", "forgeparts/fumarite_alloy",
				new String[] {"copper_inferno:fumarite_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:fumarite_alloy", 2, "Craft 2x Fumarite Alloy at a crafting table.", "Stellt 2x Fumaritlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_gem", "copper_inferno:fumarite_gem", "forgeparts/fumarite_gem",
				new String[] {"", "copper_inferno:fumarite_powder", "", "copper_inferno:fumarite_powder", "minecraft:amethyst_shard", "copper_inferno:fumarite_powder", "", "copper_inferno:fumarite_powder", ""},
				"copper_inferno:fumarite_gem", 1, "Craft 1x Fumarite Gem at a crafting table.", "Stellt 1x Fumaritjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_shard", "copper_inferno:fumarite_shard", "forgeparts/fumarite_shard",
				new String[] {"copper_inferno:fumarite_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:fumarite_shard", 4, "Craft 4x Fumarite Shard at a crafting table.", "Stellt 4x Fumaritsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_rod", "copper_inferno:fumarite_rod", "forgeparts/fumarite_rod",
				new String[] {"copper_inferno:fumarite_ingot", "", "", "copper_inferno:fumarite_ingot", "", "", "", "", ""},
				"copper_inferno:fumarite_rod", 4, "Craft 4x Fumarite Rod at a crafting table.", "Stellt 4x Fumaritstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_plate", "copper_inferno:fumarite_plate", "forgeparts/fumarite_plate",
				new String[] {"copper_inferno:fumarite_ingot", "copper_inferno:fumarite_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:fumarite_plate", 2, "Craft 2x Fumarite Plate at a crafting table.", "Stellt 2x Fumaritplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_gear", "copper_inferno:fumarite_gear", "forgeparts/fumarite_gear",
				new String[] {"", "copper_inferno:fumarite_rod", "", "copper_inferno:fumarite_rod", "copper_inferno:fumarite_ingot", "copper_inferno:fumarite_rod", "", "copper_inferno:fumarite_rod", ""},
				"copper_inferno:fumarite_gear", 1, "Craft 1x Fumarite Gear at a crafting table.", "Stellt 1x Fumaritzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_coil", "copper_inferno:fumarite_coil", "forgeparts/fumarite_coil",
				new String[] {"copper_inferno:fumarite_rod", "copper_inferno:fumarite_rod", "copper_inferno:fumarite_rod", "copper_inferno:fumarite_rod", "", "copper_inferno:fumarite_rod", "copper_inferno:fumarite_rod", "copper_inferno:fumarite_rod", "copper_inferno:fumarite_rod"},
				"copper_inferno:fumarite_coil", 2, "Craft 2x Fumarite Coil at a crafting table.", "Stellt 2x Fumaritspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_core", "copper_inferno:fumarite_core", "forgeparts/fumarite_core",
				new String[] {"", "copper_inferno:fumarite_plate", "", "copper_inferno:fumarite_plate", "copper_inferno:fumarite_gem", "copper_inferno:fumarite_plate", "", "copper_inferno:fumarite_plate", ""},
				"copper_inferno:fumarite_core", 1, "Craft 1x Fumarite Core at a crafting table.", "Stellt 1x Fumaritkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/fumarite_catalyst", "copper_inferno:fumarite_catalyst", "forgeparts/fumarite_catalyst",
				new String[] {"copper_inferno:fumarite_powder", "copper_inferno:fumarite_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:fumarite_catalyst", 2, "Craft 2x Fumarite Catalyst at a crafting table.", "Stellt 2x Fumaritkatalysator an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_dust", "copper_inferno:ignitium_dust", "forgeparts/ignitium_dust",
				new String[] {"minecraft:flint", "", "minecraft:flint", "", "copper_inferno:fumarite_dust", "", "minecraft:flint", "", "minecraft:flint"},
				"copper_inferno:ignitium_dust", 4, "Craft 4x Ignitium Dust at a crafting table.", "Stellt 4x Ignitiumstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_powder", "copper_inferno:ignitium_powder", "forgeparts/ignitium_powder",
				new String[] {"copper_inferno:ignitium_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:ignitium_powder", 2, "Craft 2x Ignitium Powder at a crafting table.", "Stellt 2x Ignitiumpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_ingot", "copper_inferno:ignitium_ingot", "forgeparts/ignitium_ingot",
				new String[] {"", "", "", "", "copper_inferno:ignitium_dust", "", "", "", ""},
				"copper_inferno:ignitium_ingot", 1, "Smelting Ignitium Dust in a furnace yields Ignitium Ingot.", "Ignitiumstaub im Ofen geschmolzen ergibt Ignitiumbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_alloy", "copper_inferno:ignitium_alloy", "forgeparts/ignitium_alloy",
				new String[] {"copper_inferno:ignitium_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:ignitium_alloy", 2, "Craft 2x Ignitium Alloy at a crafting table.", "Stellt 2x Ignitiumlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_gem", "copper_inferno:ignitium_gem", "forgeparts/ignitium_gem",
				new String[] {"", "copper_inferno:ignitium_powder", "", "copper_inferno:ignitium_powder", "minecraft:amethyst_shard", "copper_inferno:ignitium_powder", "", "copper_inferno:ignitium_powder", ""},
				"copper_inferno:ignitium_gem", 1, "Craft 1x Ignitium Gem at a crafting table.", "Stellt 1x Ignitiumjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_shard", "copper_inferno:ignitium_shard", "forgeparts/ignitium_shard",
				new String[] {"copper_inferno:ignitium_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:ignitium_shard", 4, "Craft 4x Ignitium Shard at a crafting table.", "Stellt 4x Ignitiumsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_rod", "copper_inferno:ignitium_rod", "forgeparts/ignitium_rod",
				new String[] {"copper_inferno:ignitium_ingot", "", "", "copper_inferno:ignitium_ingot", "", "", "", "", ""},
				"copper_inferno:ignitium_rod", 4, "Craft 4x Ignitium Rod at a crafting table.", "Stellt 4x Ignitiumstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_plate", "copper_inferno:ignitium_plate", "forgeparts/ignitium_plate",
				new String[] {"copper_inferno:ignitium_ingot", "copper_inferno:ignitium_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:ignitium_plate", 2, "Craft 2x Ignitium Plate at a crafting table.", "Stellt 2x Ignitiumplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_gear", "copper_inferno:ignitium_gear", "forgeparts/ignitium_gear",
				new String[] {"", "copper_inferno:ignitium_rod", "", "copper_inferno:ignitium_rod", "copper_inferno:ignitium_ingot", "copper_inferno:ignitium_rod", "", "copper_inferno:ignitium_rod", ""},
				"copper_inferno:ignitium_gear", 1, "Craft 1x Ignitium Gear at a crafting table.", "Stellt 1x Ignitiumzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_coil", "copper_inferno:ignitium_coil", "forgeparts/ignitium_coil",
				new String[] {"copper_inferno:ignitium_rod", "copper_inferno:ignitium_rod", "copper_inferno:ignitium_rod", "copper_inferno:ignitium_rod", "", "copper_inferno:ignitium_rod", "copper_inferno:ignitium_rod", "copper_inferno:ignitium_rod", "copper_inferno:ignitium_rod"},
				"copper_inferno:ignitium_coil", 2, "Craft 2x Ignitium Coil at a crafting table.", "Stellt 2x Ignitiumspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_core", "copper_inferno:ignitium_core", "forgeparts/ignitium_core",
				new String[] {"", "copper_inferno:ignitium_plate", "", "copper_inferno:ignitium_plate", "copper_inferno:ignitium_gem", "copper_inferno:ignitium_plate", "", "copper_inferno:ignitium_plate", ""},
				"copper_inferno:ignitium_core", 1, "Craft 1x Ignitium Core at a crafting table.", "Stellt 1x Ignitiumkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/ignitium_catalyst", "copper_inferno:ignitium_catalyst", "forgeparts/ignitium_catalyst",
				new String[] {"copper_inferno:ignitium_powder", "copper_inferno:ignitium_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:ignitium_catalyst", 2, "Craft 2x Ignitium Catalyst at a crafting table.", "Stellt 2x Ignitiumkatalysator an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_dust", "copper_inferno:calderium_dust", "forgeparts/calderium_dust",
				new String[] {"minecraft:quartz", "", "minecraft:quartz", "", "copper_inferno:ignitium_dust", "", "minecraft:quartz", "", "minecraft:quartz"},
				"copper_inferno:calderium_dust", 4, "Craft 4x Calderium Dust at a crafting table.", "Stellt 4x Calderiumstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_powder", "copper_inferno:calderium_powder", "forgeparts/calderium_powder",
				new String[] {"copper_inferno:calderium_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:calderium_powder", 2, "Craft 2x Calderium Powder at a crafting table.", "Stellt 2x Calderiumpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_ingot", "copper_inferno:calderium_ingot", "forgeparts/calderium_ingot",
				new String[] {"", "", "", "", "copper_inferno:calderium_dust", "", "", "", ""},
				"copper_inferno:calderium_ingot", 1, "Smelting Calderium Dust in a furnace yields Calderium Ingot.", "Calderiumstaub im Ofen geschmolzen ergibt Calderiumbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_alloy", "copper_inferno:calderium_alloy", "forgeparts/calderium_alloy",
				new String[] {"copper_inferno:calderium_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:calderium_alloy", 2, "Craft 2x Calderium Alloy at a crafting table.", "Stellt 2x Calderiumlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_gem", "copper_inferno:calderium_gem", "forgeparts/calderium_gem",
				new String[] {"", "copper_inferno:calderium_powder", "", "copper_inferno:calderium_powder", "minecraft:amethyst_shard", "copper_inferno:calderium_powder", "", "copper_inferno:calderium_powder", ""},
				"copper_inferno:calderium_gem", 1, "Craft 1x Calderium Gem at a crafting table.", "Stellt 1x Calderiumjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_shard", "copper_inferno:calderium_shard", "forgeparts/calderium_shard",
				new String[] {"copper_inferno:calderium_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:calderium_shard", 4, "Craft 4x Calderium Shard at a crafting table.", "Stellt 4x Calderiumsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_rod", "copper_inferno:calderium_rod", "forgeparts/calderium_rod",
				new String[] {"copper_inferno:calderium_ingot", "", "", "copper_inferno:calderium_ingot", "", "", "", "", ""},
				"copper_inferno:calderium_rod", 4, "Craft 4x Calderium Rod at a crafting table.", "Stellt 4x Calderiumstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_plate", "copper_inferno:calderium_plate", "forgeparts/calderium_plate",
				new String[] {"copper_inferno:calderium_ingot", "copper_inferno:calderium_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:calderium_plate", 2, "Craft 2x Calderium Plate at a crafting table.", "Stellt 2x Calderiumplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_gear", "copper_inferno:calderium_gear", "forgeparts/calderium_gear",
				new String[] {"", "copper_inferno:calderium_rod", "", "copper_inferno:calderium_rod", "copper_inferno:calderium_ingot", "copper_inferno:calderium_rod", "", "copper_inferno:calderium_rod", ""},
				"copper_inferno:calderium_gear", 1, "Craft 1x Calderium Gear at a crafting table.", "Stellt 1x Calderiumzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_coil", "copper_inferno:calderium_coil", "forgeparts/calderium_coil",
				new String[] {"copper_inferno:calderium_rod", "copper_inferno:calderium_rod", "copper_inferno:calderium_rod", "copper_inferno:calderium_rod", "", "copper_inferno:calderium_rod", "copper_inferno:calderium_rod", "copper_inferno:calderium_rod", "copper_inferno:calderium_rod"},
				"copper_inferno:calderium_coil", 2, "Craft 2x Calderium Coil at a crafting table.", "Stellt 2x Calderiumspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_core", "copper_inferno:calderium_core", "forgeparts/calderium_core",
				new String[] {"", "copper_inferno:calderium_plate", "", "copper_inferno:calderium_plate", "copper_inferno:calderium_gem", "copper_inferno:calderium_plate", "", "copper_inferno:calderium_plate", ""},
				"copper_inferno:calderium_core", 1, "Craft 1x Calderium Core at a crafting table.", "Stellt 1x Calderiumkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/calderium_catalyst", "copper_inferno:calderium_catalyst", "forgeparts/calderium_catalyst",
				new String[] {"copper_inferno:calderium_powder", "copper_inferno:calderium_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:calderium_catalyst", 2, "Craft 2x Calderium Catalyst at a crafting table.", "Stellt 2x Calderiumkatalysator an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_dust", "copper_inferno:obsidium_dust", "forgeparts/obsidium_dust",
				new String[] {"minecraft:obsidian", "", "minecraft:obsidian", "", "copper_inferno:calderium_dust", "", "minecraft:obsidian", "", "minecraft:obsidian"},
				"copper_inferno:obsidium_dust", 4, "Craft 4x Obsidium Dust at a crafting table.", "Stellt 4x Obsidiumstaub an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_powder", "copper_inferno:obsidium_powder", "forgeparts/obsidium_powder",
				new String[] {"copper_inferno:obsidium_dust", "minecraft:blaze_powder", "", "", "", "", "", "", ""},
				"copper_inferno:obsidium_powder", 2, "Craft 2x Obsidium Powder at a crafting table.", "Stellt 2x Obsidiumpulver an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_ingot", "copper_inferno:obsidium_ingot", "forgeparts/obsidium_ingot",
				new String[] {"", "", "", "", "copper_inferno:obsidium_dust", "", "", "", ""},
				"copper_inferno:obsidium_ingot", 1, "Smelting Obsidium Dust in a furnace yields Obsidium Ingot.", "Obsidiumstaub im Ofen geschmolzen ergibt Obsidiumbarren."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_alloy", "copper_inferno:obsidium_alloy", "forgeparts/obsidium_alloy",
				new String[] {"copper_inferno:obsidium_ingot", "minecraft:copper_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:obsidium_alloy", 2, "Craft 2x Obsidium Alloy at a crafting table.", "Stellt 2x Obsidiumlegierung an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_gem", "copper_inferno:obsidium_gem", "forgeparts/obsidium_gem",
				new String[] {"", "copper_inferno:obsidium_powder", "", "copper_inferno:obsidium_powder", "minecraft:amethyst_shard", "copper_inferno:obsidium_powder", "", "copper_inferno:obsidium_powder", ""},
				"copper_inferno:obsidium_gem", 1, "Craft 1x Obsidium Gem at a crafting table.", "Stellt 1x Obsidiumjuwel an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_shard", "copper_inferno:obsidium_shard", "forgeparts/obsidium_shard",
				new String[] {"copper_inferno:obsidium_gem", "", "", "", "", "", "", "", ""},
				"copper_inferno:obsidium_shard", 4, "Craft 4x Obsidium Shard at a crafting table.", "Stellt 4x Obsidiumsplitter an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_rod", "copper_inferno:obsidium_rod", "forgeparts/obsidium_rod",
				new String[] {"copper_inferno:obsidium_ingot", "", "", "copper_inferno:obsidium_ingot", "", "", "", "", ""},
				"copper_inferno:obsidium_rod", 4, "Craft 4x Obsidium Rod at a crafting table.", "Stellt 4x Obsidiumstab an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_plate", "copper_inferno:obsidium_plate", "forgeparts/obsidium_plate",
				new String[] {"copper_inferno:obsidium_ingot", "copper_inferno:obsidium_ingot", "", "", "", "", "", "", ""},
				"copper_inferno:obsidium_plate", 2, "Craft 2x Obsidium Plate at a crafting table.", "Stellt 2x Obsidiumplatte an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_gear", "copper_inferno:obsidium_gear", "forgeparts/obsidium_gear",
				new String[] {"", "copper_inferno:obsidium_rod", "", "copper_inferno:obsidium_rod", "copper_inferno:obsidium_ingot", "copper_inferno:obsidium_rod", "", "copper_inferno:obsidium_rod", ""},
				"copper_inferno:obsidium_gear", 1, "Craft 1x Obsidium Gear at a crafting table.", "Stellt 1x Obsidiumzahnrad an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_coil", "copper_inferno:obsidium_coil", "forgeparts/obsidium_coil",
				new String[] {"copper_inferno:obsidium_rod", "copper_inferno:obsidium_rod", "copper_inferno:obsidium_rod", "copper_inferno:obsidium_rod", "", "copper_inferno:obsidium_rod", "copper_inferno:obsidium_rod", "copper_inferno:obsidium_rod", "copper_inferno:obsidium_rod"},
				"copper_inferno:obsidium_coil", 2, "Craft 2x Obsidium Coil at a crafting table.", "Stellt 2x Obsidiumspule an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_core", "copper_inferno:obsidium_core", "forgeparts/obsidium_core",
				new String[] {"", "copper_inferno:obsidium_plate", "", "copper_inferno:obsidium_plate", "copper_inferno:obsidium_gem", "copper_inferno:obsidium_plate", "", "copper_inferno:obsidium_plate", ""},
				"copper_inferno:obsidium_core", 1, "Craft 1x Obsidium Core at a crafting table.", "Stellt 1x Obsidiumkern an der Werkbank her."));

		HandbookEntries.add(new HandbookEntry("items", "forgeparts/obsidium_catalyst", "copper_inferno:obsidium_catalyst", "forgeparts/obsidium_catalyst",
				new String[] {"copper_inferno:obsidium_powder", "copper_inferno:obsidium_shard", "minecraft:redstone", "", "", "", "", "", ""},
				"copper_inferno:obsidium_catalyst", 2, "Craft 2x Obsidium Catalyst at a crafting table.", "Stellt 2x Obsidiumkatalysator an der Werkbank her."));
	}
}
