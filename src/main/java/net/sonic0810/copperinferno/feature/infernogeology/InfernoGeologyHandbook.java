package net.sonic0810.copperinferno.feature.infernogeology;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Inferno geology worldgen set: one "dimension" entry per
 * worldgen feature explaining what generates where. No recipe pages (the feature
 * emits no recipes). Texts mirror devtools/gen/infernogeology_gen.py;
 * {@code devtools/check_handbook.py} parses the inline
 * {@code new HandbookEntry(...)} literals positionally, so keep them inline.
 */
final class InfernoGeologyHandbook {
	private InfernoGeologyHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_ember_iron", "copper_inferno:ember_iron_ore", null,
				null,
				null, 0, "Ember Iron Ore generates in cinderstone: common veins throughout all three Inferno biomes. Mine with a pickaxe.", "Gluteisenerz generiert im Zunderstein: h\u00e4ufige Adern in allen drei Inferno-Biomen. Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_ash_gold", "copper_inferno:ash_gold_ore", null,
				null,
				null, 0, "Ash Gold Ore generates in cinderstone: gold-bearing veins throughout the Inferno, like Nether gold. Mine with a pickaxe.", "Aschgolderz generiert im Zunderstein: goldhaltige Adern \u00fcberall im Inferno, wie Nethergold. Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_slag_copper", "copper_inferno:slag_copper_ore", null,
				null,
				null, 0, "Slag Copper Ore generates in cinderstone: plentiful copper veins - the Inferno's signature metal. Mine with a pickaxe.", "Schlackenkupfererz generiert im Zunderstein: reichliche Kupferadern - das Leitmetall des Infernos. Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_cinder_quartz", "copper_inferno:cinder_quartz_ore", null,
				null,
				null, 0, "Cinder Quartz Ore generates in cinderstone: large, frequent quartz veins (the vanilla Nether quartz numbers). Mine with a pickaxe.", "Zinderquarzerz generiert im Zunderstein: gro\u00dfe, h\u00e4ufige Quarzadern (die Vanilla-Netherquarz-Werte). Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_brimstone", "copper_inferno:brimstone_ore", null,
				null,
				null, 0, "Brimstone Ore generates in cinderstone: sulfurous veins in the mid band, y=10 to y=60. Mine with a pickaxe.", "Schwefelsteinerz generiert im Zunderstein: schwefelige Adern im mittleren Band, y=10 bis y=60. Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_cinder_lapis", "copper_inferno:cinder_lapis_ore", null,
				null,
				null, 0, "Cinder Lapis Ore generates in cinderstone: scarce lapis pockets between y=10 and y=40. Mine with a pickaxe.", "Zinderlapiserz generiert im Zunderstein: seltene Lapis-Nester zwischen y=10 und y=40. Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_smolder_redstone", "copper_inferno:smolder_redstone_ore", null,
				null,
				null, 0, "Smolder Redstone Ore generates in cinderstone: redstone veins in the lower half, y=5 to y=40. Mine with a pickaxe.", "Schwel-Redstone-Erz generiert im Zunderstein: Redstone-Adern in der unteren H\u00e4lfte, y=5 bis y=40. Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_deep_infernium", "copper_inferno:deep_infernium_ore", null,
				null,
				null, 0, "Deep Infernium Ore generates in cinderstone: small, rich infernium pockets deep down, y=5 to y=20. Mine with a pickaxe.", "Tiefen-Infernium-Erz generiert im Zunderstein: kleine, reiche Infernium-Nester in der Tiefe, y=5 bis y=20. Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_ashfall_tuff", "copper_inferno:ashfall_tuff", null,
				null,
				null, 0, "Ashfall Tuff generates in cinderstone: big soft tuff pockets (the vanilla Nether gravel numbers). Mine with a pickaxe.", "Aschentuff generiert im Zunderstein: gro\u00dfe weiche Tuff-Taschen (die Vanilla-Netherkies-Werte). Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_ember_pumice", "copper_inferno:ember_pumice", null,
				null,
				null, 0, "Ember Pumice generates in cinderstone: porous pumice pockets low down (the vanilla blackstone numbers). Mine with a pickaxe.", "Glutbims generiert im Zunderstein: por\u00f6se Bims-Taschen weiter unten (die Vanilla-Schwarzstein-Werte). Mit der Spitzhacke abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_scorched_debris_large", "copper_inferno:scorched_debris", null,
				null,
				null, 0, "Scorched Debris (large): blast-proof debris scattered fully enclosed in cinderstone, up to 3 per vein, centred around y=16 like ancient debris.", "Versengte Tr\u00fcmmer (gro\u00df): explosionsfeste Tr\u00fcmmer, v\u00f6llig im Zunderstein eingeschlossen, bis zu 3 pro Ader, um y=16 wie Uralte Tr\u00fcmmer."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_ore_scorched_debris_small", "copper_inferno:scorched_debris", null,
				null,
				null, 0, "Scorched Debris (small): a second, height-independent sprinkle of 1-2 blast-proof debris blocks per chunk, always buried in cinderstone.", "Versengte Tr\u00fcmmer (klein): eine zweite, h\u00f6henunabh\u00e4ngige Streuung von 1-2 explosionsfesten Tr\u00fcmmerbl\u00f6cken pro Chunk, stets im Zunderstein vergraben."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_scoria_blobs", "copper_inferno:scoria", null,
				null,
				null, 0, "Scoria blobs: dark volcanic cinder spheres (radius 3-7) replace cinderstone, 25 tries per chunk across the whole height range.", "Skoria-Blasen: dunkle vulkanische Schlackenkugeln (Radius 3-7) ersetzen Zunderstein, 25 Versuche pro Chunk \u00fcber die gesamte H\u00f6he."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_geyserite_blobs", "copper_inferno:geyserite", null,
				null,
				null, 0, "Geyserite blobs: pale sinter spheres (radius 3-5) replace cinderstone, 20 tries per chunk - quarry them near geyser basins.", "Geysirit-Blasen: blasse Sinterkugeln (Radius 3-5) ersetzen Zunderstein, 20 Versuche pro Chunk - in der N\u00e4he von Geysirbecken abbauen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_hardened_slag_blobs", "copper_inferno:hardened_slag", null,
				null,
				null, 0, "Hardened slag blobs: cooled slag spheres (radius 3-7) replace cinderstone, 30 tries per chunk - the Inferno's basalt analog.", "Geh\u00e4rtete-Schlacke-Blasen: erkaltete Schlackenkugeln (Radius 3-7) ersetzen Zunderstein, 30 Versuche pro Chunk - das Basalt-Analog des Infernos."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_sulfur_blobs", "copper_inferno:sulfur_block", null,
				null,
				null, 0, "Sulfur blobs: small yellow spheres (radius 2-5) replace cinderstone, 15 tries per chunk.", "Schwefel-Blasen: kleine gelbe Kugeln (Radius 2-5) ersetzen Zunderstein, 15 Versuche pro Chunk."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_disk_sulfur_sand", "copper_inferno:sulfur_sand", null,
				null,
				null, 0, "Sulfur sand disks: yellow sand banks (radius 2-6) replacing the shore blocks around the lava-sea level, y=30 to y=35.", "Schwefelsand-Scheiben: gelbe Sandb\u00e4nke (Radius 2-6) ersetzen die Uferbl\u00f6cke um den Lavasee-Pegel, y=30 bis y=35."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_disk_ember_grit", "copper_inferno:ember_grit", null,
				null,
				null, 0, "Ember grit disks: glowing-orange gravel banks (radius 2-5) in the shore band, y=28 to y=36.", "Glutgrus-Scheiben: gl\u00fchend-orange Kiesb\u00e4nke (Radius 2-5) im Uferband, y=28 bis y=36."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_disk_cinder_silt", "copper_inferno:cinder_silt", null,
				null,
				null, 0, "Cinder silt disks: soft dark mud flats (radius 2-5) slightly above the lava line, y=30 to y=38.", "Zinderschlick-Scheiben: weiche dunkle Schlammfl\u00e4chen (Radius 2-5) knapp \u00fcber der Lavalinie, y=30 bis y=38."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_spring_sulfur", "copper_inferno:sulfur_block", null,
				null,
				null, 0, "Sulfur springs: open lava spouts (8 per chunk, mid heights) leaking from cinderstone, sulfur and geyserite walls.", "Schwefelquellen: offene Lava-Ausl\u00e4sse (8 pro Chunk, mittlere H\u00f6hen) aus W\u00e4nden von Zunderstein, Schwefel und Geysirit."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_spring_geyserite", "copper_inferno:geyserite", null,
				null,
				null, 0, "Geyserite springs: enclosed lava pockets (12 per chunk) sealed inside cinderstone and geyserite, away from the terrain surface.", "Geysirit-Quellen: eingeschlossene Lavataschen (12 pro Chunk), versiegelt in Zunderstein und Geysirit, abseits der Oberfl\u00e4che."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_spring_scoria", "copper_inferno:scoria", null,
				null,
				null, 0, "Scoria springs: floor-fed lava falls (16 per chunk, strongly biased to the bottom) rising through scoria, hardened slag and geyserite.", "Skoria-Quellen: bodengespeiste Lavaf\u00e4lle (16 pro Chunk, stark nach unten gewichtet), aufsteigend durch Skoria, geh\u00e4rtete Schlacke und Geysirit."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_geyser_basin", "copper_inferno:geyserite", null,
				null,
				null, 0, "Geyser basins: lava pools (size 3-7) rimmed with pale geyserite, 8 tries on every terrain layer - the Inferno's delta analog.", "Geysirbecken: Lavabecken (Gr\u00f6\u00dfe 3-7) mit blassem Geysirit-Rand, 8 Versuche auf jeder Gel\u00e4ndeschicht - das Delta-Analog des Infernos."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_sulfur_basin", "copper_inferno:sulfur_block", null,
				null,
				null, 0, "Sulfur basins: small lava pools (size 2-5) with a thick sulfur rim, 4 tries on every terrain layer.", "Schwefelbecken: kleine Lavabecken (Gr\u00f6\u00dfe 2-5) mit dickem Schwefelrand, 4 Versuche auf jeder Gel\u00e4ndeschicht."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogeology_slag_basin", "copper_inferno:hardened_slag", null,
				null,
				null, 0, "Slag basins: lava pools (size 3-6) edged in hardened slag, 6 tries on every terrain layer.", "Schlackenbecken: Lavabecken (Gr\u00f6\u00dfe 3-6) mit Rand aus geh\u00e4rteter Schlacke, 6 Versuche auf jeder Gel\u00e4ndeschicht."));
	}
}
