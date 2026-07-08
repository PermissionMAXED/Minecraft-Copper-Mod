package net.sonic0810.copperinferno.feature.ashhorde;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Ash Horde: one "mobs" entry per mob (spawn egg icon, where it
 * spawns and what it drops) plus one "items" grid entry for every drop-conversion recipe
 * under {@code data/copper_inferno/recipe/ashhorde/}. Entry texts and grids mirror the
 * recipe JSONs emitted by {@code devtools/gen/ashhorde_gen.py};
 * {@code devtools/check_handbook.py} parses the inline {@code new HandbookEntry(...)}
 * literals positionally, so keep them inline.
 */
final class AshHordeHandbook {
	private AshHordeHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("mobs", "cinder_shambler", "copper_inferno:cinder_shambler_spawn_egg", null,
				null,
				null, 0, "Cinder Shambler - A cinder-crusted zombie that shambles through drifting embers, hitting harder than its overworld kin. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Shambler Rags.", "Zunderschlurfer - Ein zunderverkrusteter Zombie, der durch treibende Glut schlurft und h\u00e4rter zuschl\u00e4gt als seine Oberwelt-Verwandten. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Schlurferlumpen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_ghoul", "copper_inferno:ash_ghoul_spawn_egg", null,
				null,
				null, 0, "Ash Ghoul - A gaunt, grey ghoul caked in ash, quick to claw at anything warm. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Ghoul Tatters.", "Aschenghul - Ein hagerer, grauer Ghul voller Asche, der nach allem Warmen krallt. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Ghul-Fetzen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_rotter", "copper_inferno:slag_rotter_spawn_egg", null,
				null,
				null, 0, "Slag Rotter - A bloated rotter oozing molten slag, slow but brutally strong. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Rotter Sludge.", "Schlackenmoderer - Ein aufgedunsener Moderer, aus dem geschmolzene Schlacke sickert - langsam, aber brutal stark. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Moderschlamm fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_thrall", "copper_inferno:ember_thrall_spawn_egg", null,
				null,
				null, 0, "Ember Thrall - A shackled thrall bound to the Inferno's forges, still dragging its chains. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Thrall Shackles.", "Glutknecht - Ein gefesselter Knecht der Inferno-Essen, der noch immer seine Ketten schleift. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Knechtsfesseln fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_walker", "copper_inferno:soot_walker_spawn_egg", null,
				null,
				null, 0, "Soot Walker - A soot-black walker that leaves smudged footprints wherever it prowls. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Walker Grime.", "Ru\u00dfwandler - Ein ru\u00dfschwarzer Wandler, der \u00fcberall verschmierte Fu\u00dfspuren hinterl\u00e4sst. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Wandlerru\u00df fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_archer", "copper_inferno:ash_archer_spawn_egg", null,
				null,
				null, 0, "Ash Archer - An ash-bleached skeleton archer whose arrows whistle through the cinder haze. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Archer Arrowheads.", "Aschensch\u00fctze - Ein aschgebleichter Skelettsch\u00fctze, dessen Pfeile durch den Zunderdunst pfeifen. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Sch\u00fctzen-Pfeilspitzen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_bowman", "copper_inferno:cinder_bowman_spawn_egg", null,
				null,
				null, 0, "Cinder Bowman - A bowman of scorched bone that looses smouldering shots from the ridgelines. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Bowman Strings.", "Zunderbogner - Ein Bogner aus versengtem Knochen, der glimmende Sch\u00fcsse von den Graten abfeuert. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Bognersehnen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_marksman", "copper_inferno:slag_marksman_spawn_egg", null,
				null,
				null, 0, "Slag Marksman - A slag-plated marksman, taller and steadier than a common skeleton. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Marksman Bones.", "Schlackenscharfsch\u00fctze - Ein schlackengepanzerter Scharfsch\u00fctze, gr\u00f6\u00dfer und ruhiger als ein gew\u00f6hnliches Skelett. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Scharfsch\u00fctzenknochen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_rattler", "copper_inferno:ember_rattler_spawn_egg", null,
				null,
				null, 0, "Ember Rattler - A rattling skeleton with embers glowing between its ribs. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Rattler Ribs.", "Glutklapperer - Ein klapperndes Skelett, zwischen dessen Rippen Glut gl\u00fcht. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Klapperrippen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_skirmisher", "copper_inferno:soot_skirmisher_spawn_egg", null,
				null,
				null, 0, "Soot Skirmisher - A small, fast skirmisher that harries travellers in sooty packs. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Skirmisher Quivers.", "Ru\u00dfpl\u00e4nkler - Ein kleiner, schneller Pl\u00e4nkler, der Reisende in ru\u00dfigen Trupps bedr\u00e4ngt. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Pl\u00e4nklerk\u00f6cher fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_lurker", "copper_inferno:ember_lurker_spawn_egg", null,
				null,
				null, 0, "Ember Lurker - A big spider with ember-lit eyes, lurking in the glow of lava pools. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Lurker Eyes.", "Glutlauerer - Eine gro\u00dfe Spinne mit glut-erhellten Augen, die im Schein der Lavabecken lauert. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Laueraugen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_weaver", "copper_inferno:ash_weaver_spawn_egg", null,
				null,
				null, 0, "Ash Weaver - A weaver spinning grey, ash-dusted webs across the Ember Grove canopy. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Weaver Silk.", "Aschenweber - Ein Weber, der graue, aschbest\u00e4ubte Netze durch das Kronendach des Gluthains spannt. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Weberseide fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_spinner", "copper_inferno:slag_spinner_spawn_egg", null,
				null,
				null, 0, "Slag Spinner - A spinner whose threads set hard as slag the moment they cool. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Spinner Threads.", "Schlackenspinner - Ein Spinner, dessen F\u00e4den beim Abk\u00fchlen schlackenhart werden. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Spinnerf\u00e4den fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_stalker", "copper_inferno:soot_stalker_spawn_egg", null,
				null,
				null, 0, "Soot Stalker - A lean stalker, near-invisible against the black soot fields. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Stalker Claws.", "Ru\u00dfpirscher - Ein hagerer Pirscher, vor den schwarzen Ru\u00dffeldern kaum zu erkennen. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Pirscherklauen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_broodling", "copper_inferno:cinder_broodling_spawn_egg", null,
				null,
				null, 0, "Cinder Broodling - A tiny broodling that swarms from cracked cinder nests. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Broodling Fangs.", "Zunderbr\u00fctling - Ein winziger Br\u00fctling, der aus geborstenen Zundernestern schw\u00e4rmt. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Br\u00fctlingsz\u00e4hne fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_creeper", "copper_inferno:slag_creeper_spawn_egg", null,
				null,
				null, 0, "Slag Creeper - A creeper crusted in cooled slag; its blast flings molten spatter. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Creeper Slag.", "Schlackencreeper - Ein mit erkalteter Schlacke verkrusteter Creeper; seine Explosion schleudert Schmelzspritzer. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Creeper-Schlacke fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_bomber", "copper_inferno:ash_bomber_spawn_egg", null,
				null,
				null, 0, "Ash Bomber - A pale bomber that detonates into a blinding cloud of ash. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Bomber Fuses.", "Aschenbomber - Ein fahler Bomber, der in einer blendenden Aschewolke detoniert. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Bomberlunten fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_burster", "copper_inferno:ember_burster_spawn_egg", null,
				null,
				null, 0, "Ember Burster - A burster wound tight with embers, quicker to pop than most creepers. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Burster Powder.", "Glutberster - Ein mit Glut vollgestopfter Berster, der schneller hochgeht als die meisten Creeper. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Bersterpulver fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_detonator", "copper_inferno:soot_detonator_spawn_egg", null,
				null,
				null, 0, "Soot Detonator - A heavy detonator that stalks silently under cover of soot. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Detonator Charges.", "Ru\u00dfsprenger - Ein schwerer Sprenger, der lautlos im Schutz des Ru\u00dfes pirscht. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Sprengerladungen fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "cinder_cracker", "copper_inferno:cinder_cracker_spawn_egg", null,
				null,
				null, 0, "Cinder Cracker - A small cracker that goes off with a sharp, glowing snap. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Cracker Shards.", "Zunderknaller - Ein kleiner Knaller, der mit einem scharfen, gl\u00fchenden Knall losgeht. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Knallerscherben fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "char_husk", "copper_inferno:char_husk_spawn_egg", null,
				null,
				null, 0, "Char Husk - A charred husk baked black by the Inferno's heat. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Char Hides.", "Kohlezombie - Ein verkohlter W\u00fcstenzombie, von der Hitze des Infernos schwarz gebacken. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Kohleh\u00e4ute fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ash_mummy", "copper_inferno:ash_mummy_spawn_egg", null,
				null,
				null, 0, "Ash Mummy - A towering mummy wound in ash-grey wrappings, dry as old bone. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Mummy Wraps.", "Aschenmumie - Eine riesige Mumie in aschgrauen Binden, trocken wie alter Knochen. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Mumienbinden fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "slag_husk", "copper_inferno:slag_husk_spawn_egg", null,
				null,
				null, 0, "Slag Husk - A husk armoured in a cracked crust of cooled slag. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Husk Crusts.", "Schlackenzombie - Ein W\u00fcstenzombie mit einer rissigen Kruste aus erkalteter Schlacke. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Panzerkrusten fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "ember_scorchling", "copper_inferno:ember_scorchling_spawn_egg", null,
				null,
				null, 0, "Ember Scorchling - A shrivelled scorchling that sizzles as it lunges. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Scorchling Char.", "Glutsengling - Ein verschrumpelter Sengling, der beim Ausfallschritt zischt. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Senglingskohle fallen."));

		HandbookEntries.add(new HandbookEntry("mobs", "soot_wanderer", "copper_inferno:soot_wanderer_spawn_egg", null,
				null,
				null, 0, "Soot Wanderer - A shrouded wanderer trudging the soot dunes in endless circles. Marches with the Ash Horde through the Cinder Wastes, Ember Grove and Slag Sea. Drops Wanderer Shrouds.", "Ru\u00dfwanderer - Ein verh\u00fcllter Wanderer, der in endlosen Kreisen durch die Ru\u00dfd\u00fcnen stapft. Zieht mit der Aschenhorde durch Zunder\u00f6de, Gluthain und Schlackenmeer. L\u00e4sst Wandererschleier fallen."));

		HandbookEntries.add(new HandbookEntry("items", "string_from_shambler_rag", "copper_inferno:shambler_rag", "ashhorde/string_from_shambler_rag",
				new String[] {"copper_inferno:shambler_rag", "", "", "", "", "", "", "", ""},
				"minecraft:string", 2, "Unravel a Shambler Rag into two string.", "Einen Schlurferlumpen zu zwei F\u00e4den aufribbeln."));

		HandbookEntries.add(new HandbookEntry("items", "leather_from_ghoul_tatter", "copper_inferno:ghoul_tatter", "ashhorde/leather_from_ghoul_tatter",
				new String[] {"copper_inferno:ghoul_tatter", "copper_inferno:ghoul_tatter", "", "", "", "", "", "", ""},
				"minecraft:leather", 1, "Press two Ghoul Tatters into a piece of leather.", "Zwei Ghul-Fetzen zu einem St\u00fcck Leder pressen."));

		HandbookEntries.add(new HandbookEntry("items", "slime_ball_from_rotter_sludge", "copper_inferno:rotter_sludge", "ashhorde/slime_ball_from_rotter_sludge",
				new String[] {"copper_inferno:rotter_sludge", "", "", "", "", "", "", "", ""},
				"minecraft:slime_ball", 1, "Knead Rotter Sludge into a slime ball.", "Moderschlamm zu einem Schleimball kneten."));

		HandbookEntries.add(new HandbookEntry("items", "iron_nugget_from_thrall_shackle", "copper_inferno:thrall_shackle", "ashhorde/iron_nugget_from_thrall_shackle",
				new String[] {"copper_inferno:thrall_shackle", "", "", "", "", "", "", "", ""},
				"minecraft:iron_nugget", 3, "Break a Thrall Shackle into three iron nuggets.", "Eine Knechtsfessel in drei Eisenklumpen zerlegen."));

		HandbookEntries.add(new HandbookEntry("items", "black_dye_from_walker_grime", "copper_inferno:walker_grime", "ashhorde/black_dye_from_walker_grime",
				new String[] {"copper_inferno:walker_grime", "", "", "", "", "", "", "", ""},
				"minecraft:black_dye", 2, "Grind Walker Grime into two black dye.", "Wandlerru\u00df zu zwei schwarzen Farbstoffen mahlen."));

		HandbookEntries.add(new HandbookEntry("items", "arrow_from_archer_arrowhead", "copper_inferno:archer_arrowhead", "ashhorde/arrow_from_archer_arrowhead",
				new String[] {"copper_inferno:archer_arrowhead", "", "", "minecraft:stick", "", "", "minecraft:feather", "", ""},
				"minecraft:arrow", 4, "An Archer Arrowhead tips a stick and feather into four arrows.", "Eine Sch\u00fctzen-Pfeilspitze macht aus Stock und Feder vier Pfeile."));

		HandbookEntries.add(new HandbookEntry("items", "bow_from_bowman_string", "copper_inferno:bowman_string", "ashhorde/bow_from_bowman_string",
				new String[] {"copper_inferno:bowman_string", "minecraft:stick", "minecraft:stick", "", "", "", "", "", ""},
				"minecraft:bow", 1, "String two sticks with a Bowman String to make a bow.", "Zwei St\u00f6cke mit einer Bognersehne zu einem Bogen bespannen."));

		HandbookEntries.add(new HandbookEntry("items", "bone_meal_from_marksman_bone", "copper_inferno:marksman_bone", "ashhorde/bone_meal_from_marksman_bone",
				new String[] {"copper_inferno:marksman_bone", "", "", "", "", "", "", "", ""},
				"minecraft:bone_meal", 3, "Crush a Marksman Bone into three bone meal.", "Einen Scharfsch\u00fctzenknochen zu drei Knochenmehl zersto\u00dfen."));

		HandbookEntries.add(new HandbookEntry("items", "bone_from_rattler_rib", "copper_inferno:rattler_rib", "ashhorde/bone_from_rattler_rib",
				new String[] {"copper_inferno:rattler_rib", "copper_inferno:rattler_rib", "", "", "", "", "", "", ""},
				"minecraft:bone", 1, "Splint two Rattler Ribs into a whole bone.", "Zwei Klapperrippen zu einem ganzen Knochen schienen."));

		HandbookEntries.add(new HandbookEntry("items", "arrow_from_skirmisher_quiver", "copper_inferno:skirmisher_quiver", "ashhorde/arrow_from_skirmisher_quiver",
				new String[] {"copper_inferno:skirmisher_quiver", "", "", "", "", "", "", "", ""},
				"minecraft:arrow", 4, "Empty a Skirmisher Quiver for four arrows.", "Einen Pl\u00e4nklerk\u00f6cher f\u00fcr vier Pfeile leeren."));

		HandbookEntries.add(new HandbookEntry("items", "spider_eye_from_lurker_eye", "copper_inferno:lurker_eye", "ashhorde/spider_eye_from_lurker_eye",
				new String[] {"copper_inferno:lurker_eye", "", "", "", "", "", "", "", ""},
				"minecraft:spider_eye", 2, "Split a Lurker Eye into two spider eyes.", "Ein Lauererauge in zwei Spinnenaugen teilen."));

		HandbookEntries.add(new HandbookEntry("items", "cobweb_from_weaver_silk", "copper_inferno:weaver_silk", "ashhorde/cobweb_from_weaver_silk",
				new String[] {"copper_inferno:weaver_silk", "copper_inferno:weaver_silk", "", "", "", "", "", "", ""},
				"minecraft:cobweb", 1, "Weave two Weaver Silk into a cobweb.", "Zwei Weberseiden zu einem Spinnennetz verweben."));

		HandbookEntries.add(new HandbookEntry("items", "string_from_spinner_thread", "copper_inferno:spinner_thread", "ashhorde/string_from_spinner_thread",
				new String[] {"copper_inferno:spinner_thread", "", "", "", "", "", "", "", ""},
				"minecraft:string", 3, "Wind a Spinner Thread off into three string.", "Einen Spinnerfaden zu drei F\u00e4den abwickeln."));

		HandbookEntries.add(new HandbookEntry("items", "flint_from_stalker_claw", "copper_inferno:stalker_claw", "ashhorde/flint_from_stalker_claw",
				new String[] {"copper_inferno:stalker_claw", "", "", "", "", "", "", "", ""},
				"minecraft:flint", 2, "Knap a Stalker Claw into two flint.", "Eine Pirscherklaue zu zwei Feuersteinen schlagen."));

		HandbookEntries.add(new HandbookEntry("items", "fermented_spider_eye_from_broodling_fang", "copper_inferno:broodling_fang", "ashhorde/fermented_spider_eye_from_broodling_fang",
				new String[] {"copper_inferno:broodling_fang", "copper_inferno:lurker_eye", "", "", "", "", "", "", ""},
				"minecraft:fermented_spider_eye", 1, "A Broodling Fang's venom ferments a Lurker Eye on the spot.", "Das Gift eines Br\u00fctlingszahns fermentiert ein Lauererauge auf der Stelle."));

		HandbookEntries.add(new HandbookEntry("items", "gunpowder_from_creeper_slag", "copper_inferno:creeper_slag", "ashhorde/gunpowder_from_creeper_slag",
				new String[] {"copper_inferno:creeper_slag", "", "", "", "", "", "", "", ""},
				"minecraft:gunpowder", 2, "Crumble Creeper Slag into two gunpowder.", "Creeper-Schlacke zu zwei Schwarzpulver zerbr\u00f6seln."));

		HandbookEntries.add(new HandbookEntry("items", "tnt_from_bomber_fuse", "copper_inferno:bomber_fuse", "ashhorde/tnt_from_bomber_fuse",
				new String[] {"minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand", "copper_inferno:bomber_fuse", "minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"},
				"minecraft:tnt", 1, "Pack sand around a Bomber Fuse for a block of TNT.", "Sand um eine Bomberlunte packen ergibt einen TNT-Block."));

		HandbookEntries.add(new HandbookEntry("items", "blaze_powder_from_burster_powder", "copper_inferno:burster_powder", "ashhorde/blaze_powder_from_burster_powder",
				new String[] {"copper_inferno:burster_powder", "", "", "", "", "", "", "", ""},
				"minecraft:blaze_powder", 1, "Refine Burster Powder into blaze powder.", "Bersterpulver zu Lohenstaub verfeinern."));

		HandbookEntries.add(new HandbookEntry("items", "firework_rocket_from_detonator_charge", "copper_inferno:detonator_charge", "ashhorde/firework_rocket_from_detonator_charge",
				new String[] {"copper_inferno:detonator_charge", "minecraft:paper", "", "", "", "", "", "", ""},
				"minecraft:firework_rocket", 3, "Wrap a Detonator Charge in paper for three firework rockets.", "Eine Sprengerladung in Papier wickeln ergibt drei Feuerwerksraketen."));

		HandbookEntries.add(new HandbookEntry("items", "glowstone_dust_from_cracker_shard", "copper_inferno:cracker_shard", "ashhorde/glowstone_dust_from_cracker_shard",
				new String[] {"copper_inferno:cracker_shard", "", "", "", "", "", "", "", ""},
				"minecraft:glowstone_dust", 2, "Grind a Cracker Shard into two glowstone dust.", "Eine Knallerscherbe zu zwei Leuchtsteinstaub mahlen."));

		HandbookEntries.add(new HandbookEntry("items", "leather_from_char_hide", "copper_inferno:char_hide", "ashhorde/leather_from_char_hide",
				new String[] {"copper_inferno:char_hide", "", "", "", "", "", "", "", ""},
				"minecraft:leather", 1, "Scrape a Char Hide down to usable leather.", "Eine Kohlehaut zu brauchbarem Leder abschaben."));

		HandbookEntries.add(new HandbookEntry("items", "paper_from_mummy_wrap", "copper_inferno:mummy_wrap", "ashhorde/paper_from_mummy_wrap",
				new String[] {"copper_inferno:mummy_wrap", "", "", "", "", "", "", "", ""},
				"minecraft:paper", 2, "Flatten a Mummy Wrap into two paper.", "Eine Mumienbinde zu zwei Papier gl\u00e4tten."));

		HandbookEntries.add(new HandbookEntry("items", "sand_from_husk_crust", "copper_inferno:husk_crust", "ashhorde/sand_from_husk_crust",
				new String[] {"copper_inferno:husk_crust", "", "", "", "", "", "", "", ""},
				"minecraft:sand", 2, "Crush a Husk Crust into two sand.", "Eine Panzerkruste zu zwei Sand zermahlen."));

		HandbookEntries.add(new HandbookEntry("items", "charcoal_from_scorchling_char", "copper_inferno:scorchling_char", "ashhorde/charcoal_from_scorchling_char",
				new String[] {"copper_inferno:scorchling_char", "", "", "", "", "", "", "", ""},
				"minecraft:charcoal", 2, "Break Scorchling Char into two charcoal.", "Senglingskohle in zwei Holzkohle brechen."));

		HandbookEntries.add(new HandbookEntry("items", "gray_wool_from_wanderer_shroud", "copper_inferno:wanderer_shroud", "ashhorde/gray_wool_from_wanderer_shroud",
				new String[] {"copper_inferno:wanderer_shroud", "", "", "", "", "", "", "", ""},
				"minecraft:gray_wool", 2, "Cut a Wanderer Shroud into two gray wool.", "Einen Wandererschleier zu zwei grauer Wolle zerschneiden."));
	}
}
