package net.sonic0810.copperinferno.feature.emberswarm;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Ember Swarm: one "mobs" page per mob plus one "items"
 * recipe page for every JSON under {@code data/copper_inferno/recipe/emberswarm/}.
 * Entry texts and grids mirror the recipe JSONs emitted by
 * {@code devtools/gen/emberswarm_gen.py}; {@code devtools/check_handbook.py} parses
 * the inline {@code new HandbookEntry(...)} literals positionally, so keep them inline.
 */
final class EmberSwarmHandbook {
	private EmberSwarmHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("mobs", "ember_blaze", "copper_inferno:ember_blaze_spawn_egg", null,
				null,
				null, 0, "Ember Blaze - a blaze spirit of the ember swarm, whirling through the Cinder Wastes, Ember Grove and Slag Sea. Drops Ember Blaze Rod.", "Glutlohe - ein Lohengeist des Glutschwarms, der durch Zunder\u00f6de, Gluthain und Schlackenmeer wirbelt. L\u00e4sst Glutlohenrute fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_blaze", "copper_inferno:soot_blaze_spawn_egg", null,
				null,
				null, 0, "Soot Blaze - a blaze spirit of the ember swarm, whirling through the Cinder Wastes, Ember Grove and Slag Sea. Drops Soot Plume.", "Ru\u00dflohe - ein Lohengeist des Glutschwarms, der durch Zunder\u00f6de, Gluthain und Schlackenmeer wirbelt. L\u00e4sst Ru\u00dffahne fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "pyre_blaze", "copper_inferno:pyre_blaze_spawn_egg", null,
				null,
				null, 0, "Pyre Blaze - a blaze spirit of the ember swarm, whirling through the Cinder Wastes, Ember Grove and Slag Sea. Drops Pyre Ash.", "Brandlohe - ein Lohengeist des Glutschwarms, der durch Zunder\u00f6de, Gluthain und Schlackenmeer wirbelt. L\u00e4sst Brandasche fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_blaze", "copper_inferno:cinder_blaze_spawn_egg", null,
				null,
				null, 0, "Cinder Blaze - a blaze spirit of the ember swarm, whirling through the Cinder Wastes, Ember Grove and Slag Sea. Drops Cinder Spark.", "Zunderlohe - ein Lohengeist des Glutschwarms, der durch Zunder\u00f6de, Gluthain und Schlackenmeer wirbelt. L\u00e4sst Zunderfunke fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slagfire_blaze", "copper_inferno:slagfire_blaze_spawn_egg", null,
				null,
				null, 0, "Slagfire Blaze - a blaze spirit of the ember swarm, whirling through the Cinder Wastes, Ember Grove and Slag Sea. Drops Slagfire Droplet.", "Schlackenfeuerlohe - ein Lohengeist des Glutschwarms, der durch Zunder\u00f6de, Gluthain und Schlackenmeer wirbelt. L\u00e4sst Schlackenfeuertropfen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "molten_cube", "copper_inferno:molten_cube_spawn_egg", null,
				null,
				null, 0, "Molten Cube - a bouncing swarm cube that splits like a magma cube when slain. Roams the Inferno biomes. Drops Molten Globule.", "Schmelzw\u00fcrfel - ein h\u00fcpfender Schwarmw\u00fcrfel, der sich wie ein Magmaw\u00fcrfel teilt. Streift durch die Inferno-Biome. L\u00e4sst Schmelzk\u00fcgelchen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_cube", "copper_inferno:ember_cube_spawn_egg", null,
				null,
				null, 0, "Ember Cube - a bouncing swarm cube that splits like a magma cube when slain. Roams the Inferno biomes. Drops Ember Globule.", "Glutw\u00fcrfel - ein h\u00fcpfender Schwarmw\u00fcrfel, der sich wie ein Magmaw\u00fcrfel teilt. Streift durch die Inferno-Biome. L\u00e4sst Glutk\u00fcgelchen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_cube", "copper_inferno:soot_cube_spawn_egg", null,
				null,
				null, 0, "Soot Cube - a bouncing swarm cube that splits like a magma cube when slain. Roams the Inferno biomes. Drops Soot Globule.", "Ru\u00dfw\u00fcrfel - ein h\u00fcpfender Schwarmw\u00fcrfel, der sich wie ein Magmaw\u00fcrfel teilt. Streift durch die Inferno-Biome. L\u00e4sst Ru\u00dfk\u00fcgelchen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "scoria_cube", "copper_inferno:scoria_cube_spawn_egg", null,
				null,
				null, 0, "Scoria Cube - a bouncing swarm cube that splits like a magma cube when slain. Roams the Inferno biomes. Drops Scoria Chunk.", "Lavaschlackenw\u00fcrfel - ein h\u00fcpfender Schwarmw\u00fcrfel, der sich wie ein Magmaw\u00fcrfel teilt. Streift durch die Inferno-Biome. L\u00e4sst Lavaschlackenbrocken fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "pyroclast_cube", "copper_inferno:pyroclast_cube_spawn_egg", null,
				null,
				null, 0, "Pyroclast Cube - a bouncing swarm cube that splits like a magma cube when slain. Roams the Inferno biomes. Drops Pyroclast Shard.", "Pyroklastw\u00fcrfel - ein h\u00fcpfender Schwarmw\u00fcrfel, der sich wie ein Magmaw\u00fcrfel teilt. Streift durch die Inferno-Biome. L\u00e4sst Pyroklastscherbe fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_silverfish", "copper_inferno:cinder_silverfish_spawn_egg", null,
				null,
				null, 0, "Cinder Silverfish - a skittering swarm silverfish of the Inferno biomes. Does not infest blocks. Drops Cinder Chitin.", "Zundersilberfischchen - ein huschendes Schwarm-Silberfischchen der Inferno-Biome. Bef\u00e4llt keine Bl\u00f6cke. L\u00e4sst Zunderchitin fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_silverfish", "copper_inferno:ash_silverfish_spawn_egg", null,
				null,
				null, 0, "Ash Silverfish - a skittering swarm silverfish of the Inferno biomes. Does not infest blocks. Drops Ash Chitin.", "Aschensilberfischchen - ein huschendes Schwarm-Silberfischchen der Inferno-Biome. Bef\u00e4llt keine Bl\u00f6cke. L\u00e4sst Aschenchitin fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_silverfish", "copper_inferno:ember_silverfish_spawn_egg", null,
				null,
				null, 0, "Ember Silverfish - a skittering swarm silverfish of the Inferno biomes. Does not infest blocks. Drops Ember Chitin.", "Glutsilberfischchen - ein huschendes Schwarm-Silberfischchen der Inferno-Biome. Bef\u00e4llt keine Bl\u00f6cke. L\u00e4sst Glutchitin fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_silverfish", "copper_inferno:soot_silverfish_spawn_egg", null,
				null,
				null, 0, "Soot Silverfish - a skittering swarm silverfish of the Inferno biomes. Does not infest blocks. Drops Soot Chitin.", "Ru\u00dfsilberfischchen - ein huschendes Schwarm-Silberfischchen der Inferno-Biome. Bef\u00e4llt keine Bl\u00f6cke. L\u00e4sst Ru\u00dfchitin fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_silverfish", "copper_inferno:slag_silverfish_spawn_egg", null,
				null,
				null, 0, "Slag Silverfish - a skittering swarm silverfish of the Inferno biomes. Does not infest blocks. Drops Slag Chitin.", "Schlackensilberfischchen - ein huschendes Schwarm-Silberfischchen der Inferno-Biome. Bef\u00e4llt keine Bl\u00f6cke. L\u00e4sst Schlackenchitin fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "flame_vex", "copper_inferno:flame_vex_spawn_egg", null,
				null,
				null, 0, "Flame Vex - a fiery vex flitting through the air of the Inferno biomes. Phases through blocks. Drops Flame Essence.", "Flammenplagegeist - ein feuriger Plagegeist, der durch die Luft der Inferno-Biome schwirrt. Fliegt durch Bl\u00f6cke. L\u00e4sst Flammenessenz fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_vex", "copper_inferno:ember_vex_spawn_egg", null,
				null,
				null, 0, "Ember Vex - a fiery vex flitting through the air of the Inferno biomes. Phases through blocks. Drops Ember Essence.", "Glutplagegeist - ein feuriger Plagegeist, der durch die Luft der Inferno-Biome schwirrt. Fliegt durch Bl\u00f6cke. L\u00e4sst Glutessenz fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_vex", "copper_inferno:cinder_vex_spawn_egg", null,
				null,
				null, 0, "Cinder Vex - a fiery vex flitting through the air of the Inferno biomes. Phases through blocks. Drops Cinder Essence.", "Zunderplagegeist - ein feuriger Plagegeist, der durch die Luft der Inferno-Biome schwirrt. Fliegt durch Bl\u00f6cke. L\u00e4sst Zunderessenz fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "smoke_vex", "copper_inferno:smoke_vex_spawn_egg", null,
				null,
				null, 0, "Smoke Vex - a fiery vex flitting through the air of the Inferno biomes. Phases through blocks. Drops Smoke Essence.", "Rauchplagegeist - ein feuriger Plagegeist, der durch die Luft der Inferno-Biome schwirrt. Fliegt durch Bl\u00f6cke. L\u00e4sst Rauchessenz fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "spark_vex", "copper_inferno:spark_vex_spawn_egg", null,
				null,
				null, 0, "Spark Vex - a fiery vex flitting through the air of the Inferno biomes. Phases through blocks. Drops Spark Essence.", "Funkenplagegeist - ein feuriger Plagegeist, der durch die Luft der Inferno-Biome schwirrt. Fliegt durch Bl\u00f6cke. L\u00e4sst Funkenessenz fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_mite", "copper_inferno:ash_mite_spawn_egg", null,
				null,
				null, 0, "Ash Mite - a tiny swarm mite scuttling across the Inferno biomes. Drops Ash Mite Husk.", "Aschenmilbe - eine winzige Schwarmmilbe, die durch die Inferno-Biome krabbelt. L\u00e4sst Aschenmilbenpanzer fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_mite", "copper_inferno:ember_mite_spawn_egg", null,
				null,
				null, 0, "Ember Mite - a tiny swarm mite scuttling across the Inferno biomes. Drops Ember Mite Husk.", "Glutmilbe - eine winzige Schwarmmilbe, die durch die Inferno-Biome krabbelt. L\u00e4sst Glutmilbenpanzer fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_mite", "copper_inferno:cinder_mite_spawn_egg", null,
				null,
				null, 0, "Cinder Mite - a tiny swarm mite scuttling across the Inferno biomes. Drops Cinder Mite Husk.", "Zundermilbe - eine winzige Schwarmmilbe, die durch die Inferno-Biome krabbelt. L\u00e4sst Zundermilbenpanzer fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_mite", "copper_inferno:soot_mite_spawn_egg", null,
				null,
				null, 0, "Soot Mite - a tiny swarm mite scuttling across the Inferno biomes. Drops Soot Mite Husk.", "Ru\u00dfmilbe - eine winzige Schwarmmilbe, die durch die Inferno-Biome krabbelt. L\u00e4sst Ru\u00dfmilbenpanzer fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_mite", "copper_inferno:slag_mite_spawn_egg", null,
				null,
				null, 0, "Slag Mite - a tiny swarm mite scuttling across the Inferno biomes. Drops Slag Mite Husk.", "Schlackenmilbe - eine winzige Schwarmmilbe, die durch die Inferno-Biome krabbelt. L\u00e4sst Schlackenmilbenpanzer fallen."));

		HandbookEntries.add(new HandbookEntry("items", "blaze_powder_from_ember_blaze_rod", "copper_inferno:ember_blaze_rod", "emberswarm/blaze_powder_from_ember_blaze_rod",
				new String[] {"copper_inferno:ember_blaze_rod", "", "", "", "", "", "", "", ""},
				"minecraft:blaze_powder", 2, "Grind an Ember Blaze Rod into two Blaze Powder.", "Mahle eine Glutlohenrute zu zwei Lohenstaub."));

		HandbookEntries.add(new HandbookEntry("items", "black_dye_from_soot_plume", "copper_inferno:soot_plume", "emberswarm/black_dye_from_soot_plume",
				new String[] {"copper_inferno:soot_plume", "", "", "", "", "", "", "", ""},
				"minecraft:black_dye", 2, "A Soot Plume settles into two Black Dye.", "Eine Ru\u00dffahne ergibt zwei Schwarzen Farbstoff."));

		HandbookEntries.add(new HandbookEntry("items", "gunpowder_from_pyre_ash", "copper_inferno:pyre_ash", "emberswarm/gunpowder_from_pyre_ash",
				new String[] {"copper_inferno:pyre_ash", "", "", "", "", "", "", "", ""},
				"minecraft:gunpowder", 2, "Sift Pyre Ash into two Gunpowder.", "Siebe Brandasche zu zwei Schwarzpulver."));

		HandbookEntries.add(new HandbookEntry("items", "fire_charge_from_cinder_spark", "copper_inferno:cinder_spark", "emberswarm/fire_charge_from_cinder_spark",
				new String[] {"copper_inferno:cinder_spark", "", "", "", "", "", "", "", ""},
				"minecraft:fire_charge", 1, "A Cinder Spark ignites into a Fire Charge.", "Ein Zunderfunke entz\u00fcndet sich zu einer Feuerkugel."));

		HandbookEntries.add(new HandbookEntry("items", "magma_cream_from_slagfire_droplet", "copper_inferno:slagfire_droplet", "emberswarm/magma_cream_from_slagfire_droplet",
				new String[] {"copper_inferno:slagfire_droplet", "", "", "", "", "", "", "", ""},
				"minecraft:magma_cream", 2, "Knead a Slagfire Droplet into two Magma Cream.", "Knete einen Schlackenfeuertropfen zu zwei Magmacreme."));

		HandbookEntries.add(new HandbookEntry("items", "magma_cream_from_molten_globule", "copper_inferno:molten_globule", "emberswarm/magma_cream_from_molten_globule",
				new String[] {"copper_inferno:molten_globule", "", "", "", "", "", "", "", ""},
				"minecraft:magma_cream", 2, "Knead a Molten Globule into two Magma Cream.", "Knete ein Schmelzk\u00fcgelchen zu zwei Magmacreme."));

		HandbookEntries.add(new HandbookEntry("items", "magma_cream_from_ember_globule", "copper_inferno:ember_globule", "emberswarm/magma_cream_from_ember_globule",
				new String[] {"copper_inferno:ember_globule", "", "", "", "", "", "", "", ""},
				"minecraft:magma_cream", 2, "Knead an Ember Globule into two Magma Cream.", "Knete ein Glutk\u00fcgelchen zu zwei Magmacreme."));

		HandbookEntries.add(new HandbookEntry("items", "black_dye_from_soot_globule", "copper_inferno:soot_globule", "emberswarm/black_dye_from_soot_globule",
				new String[] {"copper_inferno:soot_globule", "", "", "", "", "", "", "", ""},
				"minecraft:black_dye", 2, "A Soot Globule smears into two Black Dye.", "Ein Ru\u00dfk\u00fcgelchen ergibt zwei Schwarzen Farbstoff."));

		HandbookEntries.add(new HandbookEntry("items", "magma_block_from_scoria_chunks", "copper_inferno:scoria_chunk", "emberswarm/magma_block_from_scoria_chunks",
				new String[] {"copper_inferno:scoria_chunk", "copper_inferno:scoria_chunk", "copper_inferno:scoria_chunk", "copper_inferno:scoria_chunk", "", "", "", "", ""},
				"minecraft:magma_block", 1, "Four Scoria Chunks pack into a Magma Block.", "Vier Lavaschlackenbrocken werden zu einem Magmablock gepresst."));

		HandbookEntries.add(new HandbookEntry("items", "blackstone_from_pyroclast_shards", "copper_inferno:pyroclast_shard", "emberswarm/blackstone_from_pyroclast_shards",
				new String[] {"copper_inferno:pyroclast_shard", "copper_inferno:pyroclast_shard", "", "", "", "", "", "", ""},
				"minecraft:blackstone", 1, "Two Pyroclast Shards fuse into Blackstone.", "Zwei Pyroklastscherben verschmelzen zu Schwarzstein."));

		HandbookEntries.add(new HandbookEntry("items", "string_from_cinder_chitin", "copper_inferno:cinder_chitin", "emberswarm/string_from_cinder_chitin",
				new String[] {"copper_inferno:cinder_chitin", "copper_inferno:cinder_chitin", "", "", "", "", "", "", ""},
				"minecraft:string", 2, "Two pieces of Cinder Chitin twist into two String.", "Zwei St\u00fcck Zunderchitin ergeben zwei F\u00e4den."));

		HandbookEntries.add(new HandbookEntry("items", "string_from_ash_chitin", "copper_inferno:ash_chitin", "emberswarm/string_from_ash_chitin",
				new String[] {"copper_inferno:ash_chitin", "copper_inferno:ash_chitin", "", "", "", "", "", "", ""},
				"minecraft:string", 2, "Two pieces of Ash Chitin twist into two String.", "Zwei St\u00fcck Aschenchitin ergeben zwei F\u00e4den."));

		HandbookEntries.add(new HandbookEntry("items", "string_from_ember_chitin", "copper_inferno:ember_chitin", "emberswarm/string_from_ember_chitin",
				new String[] {"copper_inferno:ember_chitin", "copper_inferno:ember_chitin", "", "", "", "", "", "", ""},
				"minecraft:string", 2, "Two pieces of Ember Chitin twist into two String.", "Zwei St\u00fcck Glutchitin ergeben zwei F\u00e4den."));

		HandbookEntries.add(new HandbookEntry("items", "string_from_soot_chitin", "copper_inferno:soot_chitin", "emberswarm/string_from_soot_chitin",
				new String[] {"copper_inferno:soot_chitin", "copper_inferno:soot_chitin", "", "", "", "", "", "", ""},
				"minecraft:string", 2, "Two pieces of Soot Chitin twist into two String.", "Zwei St\u00fcck Ru\u00dfchitin ergeben zwei F\u00e4den."));

		HandbookEntries.add(new HandbookEntry("items", "string_from_slag_chitin", "copper_inferno:slag_chitin", "emberswarm/string_from_slag_chitin",
				new String[] {"copper_inferno:slag_chitin", "copper_inferno:slag_chitin", "", "", "", "", "", "", ""},
				"minecraft:string", 2, "Two pieces of Slag Chitin twist into two String.", "Zwei St\u00fcck Schlackenchitin ergeben zwei F\u00e4den."));

		HandbookEntries.add(new HandbookEntry("items", "glowstone_dust_from_flame_essence", "copper_inferno:flame_essence", "emberswarm/glowstone_dust_from_flame_essence",
				new String[] {"copper_inferno:flame_essence", "", "", "", "", "", "", "", ""},
				"minecraft:glowstone_dust", 2, "Condense a Flame Essence into two Glowstone Dust.", "Verdichte eine Flammenessenz zu zwei Leuchtsteinstaub."));

		HandbookEntries.add(new HandbookEntry("items", "glowstone_dust_from_ember_essence", "copper_inferno:ember_essence", "emberswarm/glowstone_dust_from_ember_essence",
				new String[] {"copper_inferno:ember_essence", "", "", "", "", "", "", "", ""},
				"minecraft:glowstone_dust", 2, "Condense an Ember Essence into two Glowstone Dust.", "Verdichte eine Glutessenz zu zwei Leuchtsteinstaub."));

		HandbookEntries.add(new HandbookEntry("items", "glowstone_dust_from_cinder_essence", "copper_inferno:cinder_essence", "emberswarm/glowstone_dust_from_cinder_essence",
				new String[] {"copper_inferno:cinder_essence", "", "", "", "", "", "", "", ""},
				"minecraft:glowstone_dust", 2, "Condense a Cinder Essence into two Glowstone Dust.", "Verdichte eine Zunderessenz zu zwei Leuchtsteinstaub."));

		HandbookEntries.add(new HandbookEntry("items", "glowstone_dust_from_smoke_essence", "copper_inferno:smoke_essence", "emberswarm/glowstone_dust_from_smoke_essence",
				new String[] {"copper_inferno:smoke_essence", "", "", "", "", "", "", "", ""},
				"minecraft:glowstone_dust", 2, "Condense a Smoke Essence into two Glowstone Dust.", "Verdichte eine Rauchessenz zu zwei Leuchtsteinstaub."));

		HandbookEntries.add(new HandbookEntry("items", "glowstone_dust_from_spark_essence", "copper_inferno:spark_essence", "emberswarm/glowstone_dust_from_spark_essence",
				new String[] {"copper_inferno:spark_essence", "", "", "", "", "", "", "", ""},
				"minecraft:glowstone_dust", 2, "Condense a Spark Essence into two Glowstone Dust.", "Verdichte eine Funkenessenz zu zwei Leuchtsteinstaub."));

		HandbookEntries.add(new HandbookEntry("items", "gray_dye_from_ash_mite_husk", "copper_inferno:ash_mite_husk", "emberswarm/gray_dye_from_ash_mite_husk",
				new String[] {"copper_inferno:ash_mite_husk", "", "", "", "", "", "", "", ""},
				"minecraft:gray_dye", 2, "An Ash Mite Husk grinds into two Gray Dye.", "Ein Aschenmilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."));

		HandbookEntries.add(new HandbookEntry("items", "gray_dye_from_ember_mite_husk", "copper_inferno:ember_mite_husk", "emberswarm/gray_dye_from_ember_mite_husk",
				new String[] {"copper_inferno:ember_mite_husk", "", "", "", "", "", "", "", ""},
				"minecraft:gray_dye", 2, "An Ember Mite Husk grinds into two Gray Dye.", "Ein Glutmilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."));

		HandbookEntries.add(new HandbookEntry("items", "gray_dye_from_cinder_mite_husk", "copper_inferno:cinder_mite_husk", "emberswarm/gray_dye_from_cinder_mite_husk",
				new String[] {"copper_inferno:cinder_mite_husk", "", "", "", "", "", "", "", ""},
				"minecraft:gray_dye", 2, "A Cinder Mite Husk grinds into two Gray Dye.", "Ein Zundermilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."));

		HandbookEntries.add(new HandbookEntry("items", "gray_dye_from_soot_mite_husk", "copper_inferno:soot_mite_husk", "emberswarm/gray_dye_from_soot_mite_husk",
				new String[] {"copper_inferno:soot_mite_husk", "", "", "", "", "", "", "", ""},
				"minecraft:gray_dye", 2, "A Soot Mite Husk grinds into two Gray Dye.", "Ein Ru\u00dfmilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."));

		HandbookEntries.add(new HandbookEntry("items", "gray_dye_from_slag_mite_husk", "copper_inferno:slag_mite_husk", "emberswarm/gray_dye_from_slag_mite_husk",
				new String[] {"copper_inferno:slag_mite_husk", "", "", "", "", "", "", "", ""},
				"minecraft:gray_dye", 2, "A Slag Mite Husk grinds into two Gray Dye.", "Ein Schlackenmilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."));
	}
}
