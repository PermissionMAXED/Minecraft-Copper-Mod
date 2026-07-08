package net.sonic0810.copperinferno.feature.bossdoom;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Rarity;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModEntities;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * v4 "Doom Ascendancy" (WP14): six ultra bosses, each an oversized vanilla-based horror with
 * a {@code core.boss.BossBarHolder} bar, two phases, timed specials and a defensive stance
 * (see the entity classes). Per boss: a craftable summon item (recipes in
 * {@code data/copper_inferno/recipe/bossdoom/}), 2 drops + 1 EPIC trophy
 * ({@code loot_table/entities/<boss>.json}), a spawn egg (FAUNA tab) and handbook lore +
 * recipe entries. EntityType dimensions are copied from the vanilla registrations (verified
 * via EntityType bytecode) and inflated via the SCALE attribute; renderers are reused
 * vanilla renderers with custom textures (see {@code BossDoomFeatureClient}).
 */
public final class BossDoomFeature {
	private BossDoomFeature() {
	}

	public static EntityType<DrDoompepperEntity> DR_DOOMPEPPER;
	public static EntityType<TheCarbonatedOneEntity> THE_CARBONATED_ONE;
	public static EntityType<SodaSeraphEntity> SODA_SERAPH;
	public static EntityType<KilnArchonEntity> KILN_ARCHON;
	public static EntityType<VoidstoneBehemothEntity> VOIDSTONE_BEHEMOTH;
	public static EntityType<TheLastSmithEntity> THE_LAST_SMITH;

	public static Item DR_DOOMPEPPER_SIGIL;
	public static Item THE_CARBONATED_ONE_CORE;
	public static Item SODA_SERAPH_SIGIL;
	public static Item KILN_ARCHON_CORE;
	public static Item VOIDSTONE_BEHEMOTH_CORE;
	public static Item THE_LAST_SMITH_SIGIL;

	public static Item DOOM_SYRUP;
	public static Item DOOMPEPPER_HEART;
	public static Item DOOMPEPPER_CROWN;
	public static Item CARBONATION_CRYSTAL;
	public static Item PRESSURIZED_GEL;
	public static Item ETERNAL_BOTTLECAP;
	public static Item SERAPH_PLUME;
	public static Item SUGAR_ESSENCE;
	public static Item SERAPH_HALO;
	public static Item ARCHON_CINDER;
	public static Item MOLTEN_FLUX;
	public static Item KILN_SCEPTER;
	public static Item BEHEMOTH_PLATE;
	public static Item SINGULARITY_PEARL;
	public static Item VOIDSTONE_IDOL;
	public static Item FORGED_SCRAP;
	public static Item MASTER_BLUEPRINT;
	public static Item SMITHS_MASTERWORK;

	public static Item DR_DOOMPEPPER_SPAWN_EGG;
	public static Item THE_CARBONATED_ONE_SPAWN_EGG;
	public static Item SODA_SERAPH_SPAWN_EGG;
	public static Item KILN_ARCHON_SPAWN_EGG;
	public static Item VOIDSTONE_BEHEMOTH_SPAWN_EGG;
	public static Item THE_LAST_SMITH_SPAWN_EGG;

	public static void init() {
		registerEntityTypes();
		registerAttributes();
		registerItems();
		registerHandbookEntries();
	}

	// ------------------------------------------------------------------
	// Entity types. Base dimensions are copied from the vanilla EntityType registrations
	// (1.21.9 EntityType static-initializer bytecode via javap -c: ravager =
	// dimensions(1.95, 2.2); slime = dimensions(0.52, 0.52).eyeHeight(0.325)
	// .spawnBoxScale(4.0); phantom = dimensions(0.9, 0.5).eyeHeight(0.175)
	// .passengerAttachments(0.3375); blaze = makeFireImmune().dimensions(0.6, 1.8);
	// iron_golem = dimensions(1.4, 2.7); evoker = dimensions(0.6, 1.95)). The bosses' bulk
	// comes from the SCALE attribute, and all six get an extended tracking range so the
	// bar/model appear well before the fight. Summoned bosses call setPersistent, so MISC
	// (no natural despawn logic) fits all of them (infernoboss pattern).
	// ------------------------------------------------------------------

	private static void registerEntityTypes() {
		DR_DOOMPEPPER = ModEntities.register("dr_doompepper",
				EntityType.Builder.create(DrDoompepperEntity::new, SpawnGroup.MISC)
						.dimensions(1.95f, 2.2f)
						.maxTrackingRange(10));
		THE_CARBONATED_ONE = ModEntities.register("the_carbonated_one",
				EntityType.Builder.create(TheCarbonatedOneEntity::new, SpawnGroup.MISC)
						.dimensions(0.52f, 0.52f).eyeHeight(0.325f).spawnBoxScale(4.0f)
						.maxTrackingRange(10));
		SODA_SERAPH = ModEntities.register("soda_seraph",
				EntityType.Builder.create(SodaSeraphEntity::new, SpawnGroup.MISC)
						.dimensions(0.9f, 0.5f).eyeHeight(0.175f).passengerAttachments(0.3375f)
						.maxTrackingRange(10));
		KILN_ARCHON = ModEntities.register("kiln_archon",
				EntityType.Builder.create(KilnArchonEntity::new, SpawnGroup.MISC)
						.makeFireImmune()
						.dimensions(0.6f, 1.8f)
						.maxTrackingRange(10));
		VOIDSTONE_BEHEMOTH = ModEntities.register("voidstone_behemoth",
				EntityType.Builder.create(VoidstoneBehemothEntity::new, SpawnGroup.MISC)
						.dimensions(1.4f, 2.7f)
						.maxTrackingRange(10));
		THE_LAST_SMITH = ModEntities.register("the_last_smith",
				EntityType.Builder.create(TheLastSmithEntity::new, SpawnGroup.MISC)
						.dimensions(0.6f, 1.95f)
						.maxTrackingRange(10));
	}

	// ------------------------------------------------------------------
	// Default attributes (MANDATORY for every custom EntityType or spawning crashes).
	// EntityAttributes fields have no GENERIC_ prefix in 1.21.9 (verified via javap);
	// SlimeEntity/PhantomEntity have no create*Attributes factory of their own and use
	// HostileEntity.createHostileAttributes() exactly like the vanilla
	// DefaultAttributeRegistry entries (copperfauna pattern). NOTE: TheCarbonatedOne's
	// health/speed/damage bases are ALSO re-asserted by its setSize override - keep the
	// values here and there in sync.
	// ------------------------------------------------------------------

	private static void registerAttributes() {
		FabricDefaultAttributeRegistry.register(DR_DOOMPEPPER, RavagerEntity.createRavagerAttributes()
				.add(EntityAttributes.MAX_HEALTH, 500.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 16.0)
				.add(EntityAttributes.SCALE, 2.2)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
				.add(EntityAttributes.FOLLOW_RANGE, 64.0));
		FabricDefaultAttributeRegistry.register(THE_CARBONATED_ONE, HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 400.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 10.0)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.4)
				.add(EntityAttributes.SCALE, 3.5)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.6)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(SODA_SERAPH, HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 300.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 8.0)
				.add(EntityAttributes.SCALE, 2.6)
				.add(EntityAttributes.FOLLOW_RANGE, 64.0));
		FabricDefaultAttributeRegistry.register(KILN_ARCHON, BlazeEntity.createBlazeAttributes()
				.add(EntityAttributes.MAX_HEALTH, 350.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 10.0)
				.add(EntityAttributes.SCALE, 2.8)
				.add(EntityAttributes.FOLLOW_RANGE, 64.0));
		FabricDefaultAttributeRegistry.register(VOIDSTONE_BEHEMOTH, IronGolemEntity.createIronGolemAttributes()
				.add(EntityAttributes.MAX_HEALTH, 600.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 22.0)
				.add(EntityAttributes.SCALE, 2.6)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(THE_LAST_SMITH, EvokerEntity.createEvokerAttributes()
				.add(EntityAttributes.MAX_HEALTH, 260.0)
				.add(EntityAttributes.SCALE, 1.8)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
	}

	// ------------------------------------------------------------------
	// Items: 6 summon items, 12 drops, 6 EPIC trophies (MAIN tab) + 6 spawn eggs (FAUNA
	// tab). Every Settings instance is FRESH per registration; every id is a string
	// literal (audit rule).
	// ------------------------------------------------------------------

	private static void registerItems() {
		DR_DOOMPEPPER_SIGIL = ModItems.register("dr_doompepper_sigil",
				s -> new DoomSigilItem(s, world -> new DrDoompepperEntity(DR_DOOMPEPPER, world)),
				new Item.Settings());
		THE_CARBONATED_ONE_CORE = ModItems.register("the_carbonated_one_core",
				s -> new DoomSigilItem(s, world -> new TheCarbonatedOneEntity(THE_CARBONATED_ONE, world)),
				new Item.Settings());
		SODA_SERAPH_SIGIL = ModItems.register("soda_seraph_sigil",
				s -> new DoomSigilItem(s, world -> new SodaSeraphEntity(SODA_SERAPH, world)),
				new Item.Settings());
		KILN_ARCHON_CORE = ModItems.register("kiln_archon_core",
				s -> new DoomSigilItem(s, world -> new KilnArchonEntity(KILN_ARCHON, world)),
				new Item.Settings());
		VOIDSTONE_BEHEMOTH_CORE = ModItems.register("voidstone_behemoth_core",
				s -> new DoomSigilItem(s, world -> new VoidstoneBehemothEntity(VOIDSTONE_BEHEMOTH, world)),
				new Item.Settings());
		THE_LAST_SMITH_SIGIL = ModItems.register("the_last_smith_sigil",
				s -> new DoomSigilItem(s, world -> new TheLastSmithEntity(THE_LAST_SMITH, world)),
				new Item.Settings());

		DOOM_SYRUP = ModItems.register("doom_syrup", Item::new, new Item.Settings());
		DOOMPEPPER_HEART = ModItems.register("doompepper_heart", Item::new, new Item.Settings());
		CARBONATION_CRYSTAL = ModItems.register("carbonation_crystal", Item::new, new Item.Settings());
		PRESSURIZED_GEL = ModItems.register("pressurized_gel", Item::new, new Item.Settings());
		SERAPH_PLUME = ModItems.register("seraph_plume", Item::new, new Item.Settings());
		SUGAR_ESSENCE = ModItems.register("sugar_essence", Item::new, new Item.Settings());
		ARCHON_CINDER = ModItems.register("archon_cinder", Item::new, new Item.Settings());
		MOLTEN_FLUX = ModItems.register("molten_flux", Item::new, new Item.Settings());
		BEHEMOTH_PLATE = ModItems.register("behemoth_plate", Item::new, new Item.Settings());
		SINGULARITY_PEARL = ModItems.register("singularity_pearl", Item::new, new Item.Settings());
		FORGED_SCRAP = ModItems.register("forged_scrap", Item::new, new Item.Settings());
		MASTER_BLUEPRINT = ModItems.register("master_blueprint", Item::new, new Item.Settings());

		// Trophies: Item.Settings.rarity(Rarity) verified via javap (infernoboss pattern).
		DOOMPEPPER_CROWN = ModItems.register("doompepper_crown", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		ETERNAL_BOTTLECAP = ModItems.register("eternal_bottlecap", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		SERAPH_HALO = ModItems.register("seraph_halo", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		KILN_SCEPTER = ModItems.register("kiln_scepter", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		VOIDSTONE_IDOL = ModItems.register("voidstone_idol", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		SMITHS_MASTERWORK = ModItems.register("smiths_masterwork", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));

		// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the vanilla
		// SpawnEggItem reads its entity type from in 1.21.9 (copperfauna pattern).
		DR_DOOMPEPPER_SPAWN_EGG = ModItems.register("dr_doompepper_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(DR_DOOMPEPPER));
		THE_CARBONATED_ONE_SPAWN_EGG = ModItems.register("the_carbonated_one_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(THE_CARBONATED_ONE));
		SODA_SERAPH_SPAWN_EGG = ModItems.register("soda_seraph_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SODA_SERAPH));
		KILN_ARCHON_SPAWN_EGG = ModItems.register("kiln_archon_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(KILN_ARCHON));
		VOIDSTONE_BEHEMOTH_SPAWN_EGG = ModItems.register("voidstone_behemoth_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(VOIDSTONE_BEHEMOTH));
		THE_LAST_SMITH_SPAWN_EGG = ModItems.register("the_last_smith_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(THE_LAST_SMITH));

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			// Per boss: summon item, then drops, then the trophy.
			entries.add(DR_DOOMPEPPER_SIGIL);
			entries.add(DOOM_SYRUP);
			entries.add(DOOMPEPPER_HEART);
			entries.add(DOOMPEPPER_CROWN);
			entries.add(THE_CARBONATED_ONE_CORE);
			entries.add(CARBONATION_CRYSTAL);
			entries.add(PRESSURIZED_GEL);
			entries.add(ETERNAL_BOTTLECAP);
			entries.add(SODA_SERAPH_SIGIL);
			entries.add(SERAPH_PLUME);
			entries.add(SUGAR_ESSENCE);
			entries.add(SERAPH_HALO);
			entries.add(KILN_ARCHON_CORE);
			entries.add(ARCHON_CINDER);
			entries.add(MOLTEN_FLUX);
			entries.add(KILN_SCEPTER);
			entries.add(VOIDSTONE_BEHEMOTH_CORE);
			entries.add(BEHEMOTH_PLATE);
			entries.add(SINGULARITY_PEARL);
			entries.add(VOIDSTONE_IDOL);
			entries.add(THE_LAST_SMITH_SIGIL);
			entries.add(FORGED_SCRAP);
			entries.add(MASTER_BLUEPRINT);
			entries.add(SMITHS_MASTERWORK);
		});
		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.FAUNA_KEY).register(entries -> {
			entries.add(DR_DOOMPEPPER_SPAWN_EGG);
			entries.add(THE_CARBONATED_ONE_SPAWN_EGG);
			entries.add(SODA_SERAPH_SPAWN_EGG);
			entries.add(KILN_ARCHON_SPAWN_EGG);
			entries.add(VOIDSTONE_BEHEMOTH_SPAWN_EGG);
			entries.add(THE_LAST_SMITH_SPAWN_EGG);
		});
	}

	// ------------------------------------------------------------------
	// Handbook: per boss one lore entry (icon = spawn egg) and one summon-recipe entry
	// (icon/result = summon item, grid mirrors data/copper_inferno/recipe/bossdoom/).
	// ------------------------------------------------------------------

	private static void registerHandbookEntries() {
		HandbookEntries.add(new HandbookEntry("bosses", "dr_doompepper",
				"copper_inferno:dr_doompepper_spawn_egg", null, null, null, 0,
				"Dr. Doompepper - a colossal soda-corrupted ravager. Summon: use a Doompepper Sigil. Every few seconds it stomps a DOOM-kick shockwave that hurls everyone within 7 blocks away, and erupts a soda geyser under its target that blasts them skyward. Below half health it enrages: faster, harder, and the kick drops at double tempo. Drops Doom Syrup, a Doompepper Heart and the Doompepper Crown trophy.",
				"Dr. Doompepper - ein kolossaler limonadenverseuchter Verwüster. Beschwörung: ein Doompepper-Siegel benutzen. Alle paar Sekunden stampft er eine DOOM-Kick-Schockwelle, die jeden im Umkreis von 7 Blöcken wegschleudert, und lässt unter seinem Ziel einen Limonaden-Geysir ausbrechen, der es himmelwärts sprengt. Unter halber Gesundheit wird er rasend: schneller, härter, und der Kick fällt im doppelten Tempo. Lässt Doom-Sirup, ein Doompepper-Herz und die Trophäe Doompepper-Krone fallen."));
		HandbookEntries.add(new HandbookEntry("bosses", "the_carbonated_one",
				"copper_inferno:the_carbonated_one_spawn_egg", null, null, null, 0,
				"The Carbonated One - a pressurized soda slime god. Summon: use a Carbonated Core. It periodically detonates a fizz storm that blasts everyone within 8 blocks away, and seals itself under a carbonation shield of Resistance. Below half health it is shaken up: faster, regenerating, detonating at double tempo. It never splits. Drops Carbonation Crystals, Pressurized Gel and the Eternal Bottlecap trophy.",
				"Der Karbonisierte - ein unter Druck stehender Limonadenschleim-Gott. Beschwörung: einen Karbonisierten Kern benutzen. Er zündet regelmäßig einen Sprudelsturm, der jeden im Umkreis von 8 Blöcken wegsprengt, und versiegelt sich unter einem Kohlensäureschild aus Resistenz. Unter halber Gesundheit ist er aufgeschüttelt: schneller, regenerierend, mit doppeltem Detonationstempo. Er teilt sich nie. Lässt Kohlensäurekristalle, Druckgel und die Trophäe Ewiger Kronkorken fallen."));
		HandbookEntries.add(new HandbookEntry("bosses", "soda_seraph",
				"copper_inferno:soda_seraph_spawn_egg", null, null, null, 0,
				"Soda Seraph - a haloed sugar-winged phantom that fears no sunlight. Summon: use a Seraph Sigil. Its dives overdose victims with a sugar rush (Hunger, Weakness, Nausea), and every few seconds a sugar storm sprays Hunger and Slowness over everyone it can see within 10 blocks. Below half health the sugar crash makes it faster and doubles the storm tempo. Drops Seraph Plumes, Sugar Essence and the Seraph Halo trophy.",
				"Limonaden-Seraph - ein Phantom mit Heiligenschein und Zuckerflügeln, das kein Sonnenlicht fürchtet. Beschwörung: ein Seraph-Siegel benutzen. Seine Sturzflüge überdosieren Opfer mit einem Zuckerrausch (Hunger, Schwäche, Übelkeit), und alle paar Sekunden besprüht ein Zuckersturm jeden Sichtbaren im Umkreis von 10 Blöcken mit Hunger und Langsamkeit. Unter halber Gesundheit macht der Zuckerabsturz ihn schneller und verdoppelt das Sturmtempo. Lässt Seraphenfedern, Zuckeressenz und die Trophäe Seraphen-Heiligenschein fallen."));
		HandbookEntries.add(new HandbookEntry("bosses", "kiln_archon",
				"copper_inferno:kiln_archon_spawn_egg", null, null, null, 0,
				"Kiln Archon - a towering master-smelter blaze. Summon: use a Kiln Core. Its furnace beam MELTS ARMOR - every worn piece loses durability - while burning and igniting its target, and it periodically vitrifies under a kiln shield of Resistance. Below half health it runs white-hot: faster, tougher, beaming at double tempo. Drops Archon Cinders, Molten Flux and the Kiln Scepter trophy.",
				"Brennofen-Archon - eine turmhohe Meisterschmelzer-Lohe. Beschwörung: einen Brennofen-Kern benutzen. Sein Ofenstrahl SCHMILZT RÜSTUNG - jedes getragene Teil verliert Haltbarkeit - während er sein Ziel verbrennt und entzündet, und er verglast sich regelmäßig unter einem Ofenschild aus Resistenz. Unter halber Gesundheit läuft er weißglühend: schneller, zäher, mit doppeltem Strahltempo. Lässt Archon-Asche, Schmelzfluss und die Trophäe Brennofen-Zepter fallen."));
		HandbookEntries.add(new HandbookEntry("bosses", "voidstone_behemoth",
				"copper_inferno:voidstone_behemoth_spawn_egg", null, null, null, 0,
				"Voidstone Behemoth - a gravity-cursed colossus. Summon: use a Behemoth Core. Every few seconds it collapses space, dragging everyone within 12 blocks toward it, then slams the ground to crush whoever was caught. Below half health the event horizon hardens it with extra armor and speed and the pull comes at double tempo. Drops Behemoth Plates, a Singularity Pearl and the Voidstone Idol trophy.",
				"Leerenstein-Behemoth - ein schwerkraftverfluchter Koloss. Beschwörung: einen Behemoth-Kern benutzen. Alle paar Sekunden kollabiert er den Raum, zieht jeden im Umkreis von 12 Blöcken zu sich heran und schmettert dann den Boden, um alle Gefangenen zu zermalmen. Unter halber Gesundheit härtet ihn der Ereignishorizont mit zusätzlicher Rüstung und Tempo, und der Sog kommt doppelt so schnell. Lässt Behemoth-Platten, eine Singularitätsperle und die Trophäe Leerenstein-Götze fallen."));
		HandbookEntries.add(new HandbookEntry("bosses", "the_last_smith",
				"copper_inferno:the_last_smith_spawn_egg", null, null, null, 0,
				"The Last Smith - the final master of the doom-forge, an evoker whose spells are hammer blows. Summon: use a Smith's Sigil. It forges a perfect copy of YOUR weapon and wields it against you, casts the doom-forge's fang lines and vex swarms, and at two thirds and one third health it tempers its own gear - each pass adds attack damage and armor. Drops Forged Scrap, a Master Blueprint and the Smith's Masterwork trophy.",
				"Der Letzte Schmied - der letzte Meister der Doom-Schmiede, ein Magier, dessen Zauber Hammerschläge sind. Beschwörung: ein Schmiedesiegel benutzen. Er schmiedet eine perfekte Kopie DEINER Waffe und führt sie gegen dich, wirkt die Fangzahnreihen und Plagegeist-Schwärme der Doom-Schmiede und härtet bei zwei Dritteln und einem Drittel Gesundheit seine eigene Ausrüstung - jeder Durchgang bringt Angriffsschaden und Rüstung. Lässt Geschmiedeten Schrott, einen Meisterbauplan und die Trophäe Meisterwerk des Schmieds fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "dr_doompepper_sigil_recipe",
				"copper_inferno:dr_doompepper_sigil", "bossdoom/dr_doompepper_sigil",
				new String[] {
						"copper_inferno:dr_pepper", "minecraft:iron_ingot", "copper_inferno:dr_pepper",
						"minecraft:iron_ingot", "minecraft:wither_skeleton_skull", "minecraft:iron_ingot",
						"copper_inferno:dr_pepper", "minecraft:iron_ingot", "copper_inferno:dr_pepper"},
				"copper_inferno:dr_doompepper_sigil", 1,
				"Doompepper Sigil - ring a Wither Skeleton Skull with 4 Dr.Pepper and 4 Iron Ingots. Use it to summon Dr. Doompepper.",
				"Doompepper-Siegel - einen Witherskelettschädel mit 4 Dr.Pepper und 4 Eisenbarren umringen. Benutzen, um Dr. Doompepper zu beschwören."));
		HandbookEntries.add(new HandbookEntry("bosses", "the_carbonated_one_core_recipe",
				"copper_inferno:the_carbonated_one_core", "bossdoom/the_carbonated_one_core",
				new String[] {
						"minecraft:sugar", "minecraft:slime_ball", "minecraft:sugar",
						"minecraft:slime_ball", "copper_inferno:dr_pepper", "minecraft:slime_ball",
						"minecraft:sugar", "minecraft:slime_ball", "minecraft:sugar"},
				"copper_inferno:the_carbonated_one_core", 1,
				"Carbonated Core - ring a Dr.Pepper with 4 Sugar and 4 Slime Balls. Use it to summon The Carbonated One.",
				"Karbonisierter Kern - ein Dr.Pepper mit 4 Zucker und 4 Schleimbällen umringen. Benutzen, um den Karbonisierten zu beschwören."));
		HandbookEntries.add(new HandbookEntry("bosses", "soda_seraph_sigil_recipe",
				"copper_inferno:soda_seraph_sigil", "bossdoom/soda_seraph_sigil",
				new String[] {
						"minecraft:phantom_membrane", "minecraft:sugar", "minecraft:phantom_membrane",
						"minecraft:sugar", "copper_inferno:dr_pepper", "minecraft:sugar",
						"minecraft:phantom_membrane", "minecraft:sugar", "minecraft:phantom_membrane"},
				"copper_inferno:soda_seraph_sigil", 1,
				"Seraph Sigil - ring a Dr.Pepper with 4 Phantom Membranes and 4 Sugar. Use it to summon the Soda Seraph.",
				"Seraph-Siegel - ein Dr.Pepper mit 4 Phantomhäuten und 4 Zucker umringen. Benutzen, um den Limonaden-Seraph zu beschwören."));
		HandbookEntries.add(new HandbookEntry("bosses", "kiln_archon_core_recipe",
				"copper_inferno:kiln_archon_core", "bossdoom/kiln_archon_core",
				new String[] {
						"minecraft:blaze_rod", "minecraft:copper_ingot", "minecraft:blaze_rod",
						"minecraft:copper_ingot", "copper_inferno:inferno_powder", "minecraft:copper_ingot",
						"minecraft:blaze_rod", "minecraft:copper_ingot", "minecraft:blaze_rod"},
				"copper_inferno:kiln_archon_core", 1,
				"Kiln Core - ring an Inferno Powder with 4 Blaze Rods and 4 Copper Ingots. Use it to summon the Kiln Archon.",
				"Brennofen-Kern - ein Infernopulver mit 4 Lohenruten und 4 Kupferbarren umringen. Benutzen, um den Brennofen-Archon zu beschwören."));
		HandbookEntries.add(new HandbookEntry("bosses", "voidstone_behemoth_core_recipe",
				"copper_inferno:voidstone_behemoth_core", "bossdoom/voidstone_behemoth_core",
				new String[] {
						"copper_inferno:voidstone", "minecraft:obsidian", "copper_inferno:voidstone",
						"minecraft:obsidian", "minecraft:ender_pearl", "minecraft:obsidian",
						"copper_inferno:voidstone", "minecraft:obsidian", "copper_inferno:voidstone"},
				"copper_inferno:voidstone_behemoth_core", 1,
				"Behemoth Core - ring an Ender Pearl with 4 Voidstone and 4 Obsidian. Use it to summon the Voidstone Behemoth.",
				"Behemoth-Kern - eine Enderperle mit 4 Leerenstein und 4 Obsidian umringen. Benutzen, um den Leerenstein-Behemoth zu beschwören."));
		HandbookEntries.add(new HandbookEntry("bosses", "the_last_smith_sigil_recipe",
				"copper_inferno:the_last_smith_sigil", "bossdoom/the_last_smith_sigil",
				new String[] {
						"copper_inferno:infernium_ingot", "minecraft:iron_ingot", "copper_inferno:infernium_ingot",
						"minecraft:iron_ingot", "minecraft:anvil", "minecraft:iron_ingot",
						"copper_inferno:infernium_ingot", "minecraft:iron_ingot", "copper_inferno:infernium_ingot"},
				"copper_inferno:the_last_smith_sigil", 1,
				"Smith's Sigil - ring an Anvil with 4 Infernium Ingots and 4 Iron Ingots. Use it to summon The Last Smith.",
				"Schmiedesiegel - einen Amboss mit 4 Inferniumbarren und 4 Eisenbarren umringen. Benutzen, um den Letzten Schmied zu beschwören."));
	}
}
