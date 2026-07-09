package net.sonic0810.copperinferno.feature.copperfauna;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AbstractCowEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModEntities;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * v4 "Copper Fauna" (WP8): 24 custom Overworld mobs, each extending a vanilla entity and reusing
 * a vanilla renderer with a copper_inferno entity texture (see {@code CopperFaunaFeatureClient}).
 * EntityType dimensions/eye heights are copied from the vanilla {@code EntityType} registrations
 * (verified via bytecode). Every mob gets a spawn egg, an entity loot table, natural Overworld
 * spawns and ONE behavioral tweak (a goal, an on-hit effect or an attribute change — see the
 * entity class / {@link #registerAttributes()}). 12 shared drop items (one per two mobs) feed
 * the recipes under {@code data/copper_inferno/recipe/copperfauna/}.
 */
public final class CopperFaunaFeature {
	private CopperFaunaFeature() {
	}

	public static EntityType<CopperBeetleEntity> COPPER_BEETLE;
	public static EntityType<VerdigrisSlimeEntity> VERDIGRIS_SLIME;
	public static EntityType<PatinaBatEntity> PATINA_BAT;
	public static EntityType<SparkHareEntity> SPARK_HARE;
	public static EntityType<LodeBoarEntity> LODE_BOAR;
	public static EntityType<GildedFinchEntity> GILDED_FINCH;
	public static EntityType<CoilSpiderEntity> COIL_SPIDER;
	public static EntityType<RustWolfEntity> RUST_WOLF;
	public static EntityType<StatueMiteEntity> STATUE_MITE;
	public static EntityType<TarnishWitchEntity> TARNISH_WITCH;
	public static EntityType<ConductorCreeperEntity> CONDUCTOR_CREEPER;
	public static EntityType<AmpereBeeEntity> AMPERE_BEE;
	public static EntityType<OxidizedZombieEntity> OXIDIZED_ZOMBIE;
	public static EntityType<PatinaSkeletonEntity> PATINA_SKELETON;
	public static EntityType<CopperGolemiteEntity> COPPER_GOLEMITE;
	public static EntityType<SparkFoxEntity> SPARK_FOX;
	public static EntityType<VerdigrisFrogEntity> VERDIGRIS_FROG;
	public static EntityType<CoilChickenEntity> COIL_CHICKEN;
	public static EntityType<LodeCowEntity> LODE_COW;
	public static EntityType<PatinaSheepEntity> PATINA_SHEEP;
	public static EntityType<GutterCatEntity> GUTTER_CAT;
	public static EntityType<ThunderGoatEntity> THUNDER_GOAT;
	public static EntityType<ScrapPhantomEntity> SCRAP_PHANTOM;
	public static EntityType<CoinOcelotEntity> COIN_OCELOT;

	private static final List<Item> TAB_ITEMS = new ArrayList<>();

	// 12 shared drop items, one per two mobs.
	public static Item COPPER_CHITIN;
	public static Item VERDIGRIS_GEL;
	public static Item PATINA_MEMBRANE;
	public static Item SPARK_TUFT;
	public static Item LODE_HIDE;
	public static Item GILDED_FEATHER;
	public static Item LIVE_WIRE;
	public static Item RUST_FANG;
	public static Item TARNISH_DUST;
	public static Item STORM_CELL;
	public static Item PATINA_BONE;
	public static Item BURNISHED_COIN;

	public static void init() {
		registerEntityTypes();
		registerAttributes();
		registerItems();
		registerSpawning();
		registerHandbookEntries();
	}

	// ------------------------------------------------------------------
	// Entity types. Dimensions/eye heights/tracking ranges are copied from the vanilla
	// EntityType registrations of each base mob (extracted from the 1.21.9 EntityType
	// static-initializer bytecode via javap -c).
	// ------------------------------------------------------------------

	private static void registerEntityTypes() {
		// silverfish = dimensions(0.4, 0.3).eyeHeight(0.13).passengerAttachments(0.2375)
		COPPER_BEETLE = ModEntities.register("copper_beetle",
				EntityType.Builder.create(CopperBeetleEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.4f, 0.3f).eyeHeight(0.13f).passengerAttachments(0.2375f)
						.maxTrackingRange(8).notAllowedInPeaceful());
		// slime = dimensions(0.52, 0.52).eyeHeight(0.325).spawnBoxScale(4.0)
		VERDIGRIS_SLIME = ModEntities.register("verdigris_slime",
				EntityType.Builder.create(VerdigrisSlimeEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.52f, 0.52f).eyeHeight(0.325f).spawnBoxScale(4.0f)
						.maxTrackingRange(10).notAllowedInPeaceful());
		// bat = dimensions(0.5, 0.9).eyeHeight(0.45).maxTrackingRange(5)
		PATINA_BAT = ModEntities.register("patina_bat",
				EntityType.Builder.create(PatinaBatEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.5f, 0.9f).eyeHeight(0.45f).maxTrackingRange(5));
		// rabbit = dimensions(0.4, 0.5)
		SPARK_HARE = ModEntities.register("spark_hare",
				EntityType.Builder.create(SparkHareEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.4f, 0.5f).maxTrackingRange(8));
		// pig = dimensions(0.9, 0.9).passengerAttachments(0.86875)
		LODE_BOAR = ModEntities.register("lode_boar",
				EntityType.Builder.create(LodeBoarEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.9f, 0.9f).passengerAttachments(0.86875f).maxTrackingRange(10));
		// parrot = dimensions(0.5, 0.9).eyeHeight(0.54).passengerAttachments(0.4625)
		GILDED_FINCH = ModEntities.register("gilded_finch",
				EntityType.Builder.create(GildedFinchEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.5f, 0.9f).eyeHeight(0.54f).passengerAttachments(0.4625f)
						.maxTrackingRange(8));
		// spider = dimensions(1.4, 0.9).eyeHeight(0.65).passengerAttachments(0.765)
		COIL_SPIDER = ModEntities.register("coil_spider",
				EntityType.Builder.create(CoilSpiderEntity::new, SpawnGroup.MONSTER)
						.dimensions(1.4f, 0.9f).eyeHeight(0.65f).passengerAttachments(0.765f)
						.maxTrackingRange(8).notAllowedInPeaceful());
		// wolf = dimensions(0.6, 0.85).eyeHeight(0.68)
		RUST_WOLF = ModEntities.register("rust_wolf",
				EntityType.Builder.create(RustWolfEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.6f, 0.85f).eyeHeight(0.68f).maxTrackingRange(10));
		// endermite = dimensions(0.4, 0.3).eyeHeight(0.13).passengerAttachments(0.2375)
		STATUE_MITE = ModEntities.register("statue_mite",
				EntityType.Builder.create(StatueMiteEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.4f, 0.3f).eyeHeight(0.13f).passengerAttachments(0.2375f)
						.maxTrackingRange(8).notAllowedInPeaceful());
		// witch = dimensions(0.6, 1.95).eyeHeight(1.62).passengerAttachments(2.2625)
		TARNISH_WITCH = ModEntities.register("tarnish_witch",
				EntityType.Builder.create(TarnishWitchEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.95f).eyeHeight(1.62f).passengerAttachments(2.2625f)
						.maxTrackingRange(8).notAllowedInPeaceful());
		// creeper = dimensions(0.6, 1.7)
		CONDUCTOR_CREEPER = ModEntities.register("conductor_creeper",
				EntityType.Builder.create(ConductorCreeperEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.7f).maxTrackingRange(8).notAllowedInPeaceful());
		// bee = dimensions(0.7, 0.6).eyeHeight(0.3)
		AMPERE_BEE = ModEntities.register("ampere_bee",
				EntityType.Builder.create(AmpereBeeEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.7f, 0.6f).eyeHeight(0.3f).maxTrackingRange(8));
		// zombie = dimensions(0.6, 1.95).eyeHeight(1.74).passengerAttachments(2.0125)
		OXIDIZED_ZOMBIE = ModEntities.register("oxidized_zombie",
				EntityType.Builder.create(OxidizedZombieEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.95f).eyeHeight(1.74f).passengerAttachments(2.0125f)
						.maxTrackingRange(8).notAllowedInPeaceful());
		// skeleton = dimensions(0.6, 1.99).eyeHeight(1.74)
		PATINA_SKELETON = ModEntities.register("patina_skeleton",
				EntityType.Builder.create(PatinaSkeletonEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.99f).eyeHeight(1.74f)
						.maxTrackingRange(8).notAllowedInPeaceful());
		// snow_golem = dimensions(0.7, 1.9).eyeHeight(1.7)
		COPPER_GOLEMITE = ModEntities.register("copper_golemite",
				EntityType.Builder.create(CopperGolemiteEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.7f, 1.9f).eyeHeight(1.7f).maxTrackingRange(8));
		// fox = dimensions(0.6, 0.7).eyeHeight(0.4)
		SPARK_FOX = ModEntities.register("spark_fox",
				EntityType.Builder.create(SparkFoxEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.6f, 0.7f).eyeHeight(0.4f).maxTrackingRange(8));
		// frog = dimensions(0.5, 0.5)
		VERDIGRIS_FROG = ModEntities.register("verdigris_frog",
				EntityType.Builder.create(VerdigrisFrogEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.5f, 0.5f).maxTrackingRange(10));
		// chicken = dimensions(0.4, 0.7).eyeHeight(0.644)
		COIL_CHICKEN = ModEntities.register("coil_chicken",
				EntityType.Builder.create(CoilChickenEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.4f, 0.7f).eyeHeight(0.644f).maxTrackingRange(10));
		// cow = dimensions(0.9, 1.4).eyeHeight(1.3).passengerAttachments(1.36875)
		LODE_COW = ModEntities.register("lode_cow",
				EntityType.Builder.create(LodeCowEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.9f, 1.4f).eyeHeight(1.3f).passengerAttachments(1.36875f)
						.maxTrackingRange(10));
		// sheep = dimensions(0.9, 1.3).eyeHeight(1.235).passengerAttachments(1.2375)
		PATINA_SHEEP = ModEntities.register("patina_sheep",
				EntityType.Builder.create(PatinaSheepEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.9f, 1.3f).eyeHeight(1.235f).passengerAttachments(1.2375f)
						.maxTrackingRange(10));
		// cat = dimensions(0.6, 0.7).eyeHeight(0.35).passengerAttachments(0.5125)
		GUTTER_CAT = ModEntities.register("gutter_cat",
				EntityType.Builder.create(GutterCatEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.6f, 0.7f).eyeHeight(0.35f).passengerAttachments(0.5125f)
						.maxTrackingRange(8));
		// goat = dimensions(0.9, 1.3).passengerAttachments(1.1125)
		THUNDER_GOAT = ModEntities.register("thunder_goat",
				EntityType.Builder.create(ThunderGoatEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.9f, 1.3f).passengerAttachments(1.1125f).maxTrackingRange(10));
		// phantom = dimensions(0.9, 0.5).eyeHeight(0.175).passengerAttachments(0.3375)
		SCRAP_PHANTOM = ModEntities.register("scrap_phantom",
				EntityType.Builder.create(ScrapPhantomEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.9f, 0.5f).eyeHeight(0.175f).passengerAttachments(0.3375f)
						.maxTrackingRange(8).notAllowedInPeaceful());
		// ocelot = dimensions(0.6, 0.7).passengerAttachments(0.6375)
		COIN_OCELOT = ModEntities.register("coin_ocelot",
				EntityType.Builder.create(CoinOcelotEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.6f, 0.7f).passengerAttachments(0.6375f).maxTrackingRange(10));
	}

	// ------------------------------------------------------------------
	// Default attributes (MANDATORY for every custom EntityType or spawning crashes). Where the
	// mob's single behavioral tweak is an attribute change, it is applied here via .add(...);
	// SLIME and PHANTOM use HostileEntity.createHostileAttributes() exactly like the vanilla
	// DefaultAttributeRegistry entries (verified via bytecode — neither class has its own
	// create*Attributes factory).
	// ------------------------------------------------------------------

	private static void registerAttributes() {
		FabricDefaultAttributeRegistry.register(COPPER_BEETLE, SilverfishEntity.createSilverfishAttributes());
		FabricDefaultAttributeRegistry.register(VERDIGRIS_SLIME, HostileEntity.createHostileAttributes());
		FabricDefaultAttributeRegistry.register(PATINA_BAT, BatEntity.createBatAttributes());
		FabricDefaultAttributeRegistry.register(SPARK_HARE, RabbitEntity.createRabbitAttributes()
				.add(EntityAttributes.MOVEMENT_SPEED, 0.45)); // tweak: vanilla rabbit is 0.3
		FabricDefaultAttributeRegistry.register(LODE_BOAR, PigEntity.createPigAttributes()
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.8)); // tweak: vanilla pig is 0.0
		FabricDefaultAttributeRegistry.register(GILDED_FINCH, ParrotEntity.createParrotAttributes()
				.add(EntityAttributes.MAX_HEALTH, 10.0)); // tweak: vanilla parrot is 6
		FabricDefaultAttributeRegistry.register(COIL_SPIDER, SpiderEntity.createSpiderAttributes());
		FabricDefaultAttributeRegistry.register(RUST_WOLF, WolfEntity.createWolfAttributes());
		FabricDefaultAttributeRegistry.register(STATUE_MITE, EndermiteEntity.createEndermiteAttributes()
				.add(EntityAttributes.SCALE, 1.3)); // tweak: an oversized endermite
		FabricDefaultAttributeRegistry.register(TARNISH_WITCH, WitchEntity.createWitchAttributes()
				.add(EntityAttributes.MAX_HEALTH, 36.0)); // tweak: vanilla witch is 26
		FabricDefaultAttributeRegistry.register(CONDUCTOR_CREEPER, CreeperEntity.createCreeperAttributes()
				.add(EntityAttributes.MOVEMENT_SPEED, 0.3)); // tweak: vanilla creeper is 0.25
		FabricDefaultAttributeRegistry.register(AMPERE_BEE, BeeEntity.createBeeAttributes());
		FabricDefaultAttributeRegistry.register(OXIDIZED_ZOMBIE, ZombieEntity.createZombieAttributes());
		FabricDefaultAttributeRegistry.register(PATINA_SKELETON, AbstractSkeletonEntity.createAbstractSkeletonAttributes()
				.add(EntityAttributes.ARMOR, 4.0)); // tweak: vanilla skeleton is 0
		FabricDefaultAttributeRegistry.register(COPPER_GOLEMITE, SnowGolemEntity.createSnowGolemAttributes());
		FabricDefaultAttributeRegistry.register(SPARK_FOX, FoxEntity.createFoxAttributes()
				.add(EntityAttributes.MOVEMENT_SPEED, 0.4)); // tweak: vanilla fox is 0.3
		FabricDefaultAttributeRegistry.register(VERDIGRIS_FROG, FrogEntity.createFrogAttributes()
				.add(EntityAttributes.MAX_HEALTH, 16.0)); // tweak: vanilla frog is 10
		FabricDefaultAttributeRegistry.register(COIL_CHICKEN, ChickenEntity.createChickenAttributes()
				.add(EntityAttributes.MOVEMENT_SPEED, 0.3)); // tweak: vanilla chicken is 0.25
		FabricDefaultAttributeRegistry.register(LODE_COW, AbstractCowEntity.createCowAttributes()
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.6)); // tweak: vanilla cow is 0.0
		FabricDefaultAttributeRegistry.register(PATINA_SHEEP, SheepEntity.createSheepAttributes()
				.add(EntityAttributes.MAX_HEALTH, 12.0)); // tweak: vanilla sheep is 8
		FabricDefaultAttributeRegistry.register(GUTTER_CAT, CatEntity.createCatAttributes());
		FabricDefaultAttributeRegistry.register(THUNDER_GOAT, GoatEntity.createGoatAttributes()
				.add(EntityAttributes.ATTACK_DAMAGE, 4.0)); // tweak: vanilla goat is 2
		FabricDefaultAttributeRegistry.register(SCRAP_PHANTOM, HostileEntity.createHostileAttributes()
				.add(EntityAttributes.SCALE, 1.2)); // tweak: a bigger wingspan
		FabricDefaultAttributeRegistry.register(COIN_OCELOT, OcelotEntity.createOcelotAttributes());
	}

	// ------------------------------------------------------------------
	// Items: 24 spawn eggs + 12 shared drops, all in the FAUNA creative tab.
	// ------------------------------------------------------------------

	private static Item registerSpawnEgg(String path, EntityType<? extends MobEntity> type) {
		// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the vanilla SpawnEggItem
		// reads its entity type from in 1.21.9 (same proven pattern as the infernomobs eggs).
		// NOTE: the ("<mob id>" + "_spawn_egg") concatenation keeps the audit's literal-id rule
		// satisfied while making the egg/mob pairing obvious at the call site.
		Item egg = ModItems.register(path + "_spawn_egg", SpawnEggItem::new, new Item.Settings().spawnEgg(type));
		TAB_ITEMS.add(egg);
		return egg;
	}

	private static Item registerDrop(String path) {
		Item item = ModItems.register(path, Item::new, new Item.Settings());
		TAB_ITEMS.add(item);
		return item;
	}

	private static void registerItems() {
		registerSpawnEgg("copper_beetle", COPPER_BEETLE);
		registerSpawnEgg("verdigris_slime", VERDIGRIS_SLIME);
		registerSpawnEgg("patina_bat", PATINA_BAT);
		registerSpawnEgg("spark_hare", SPARK_HARE);
		registerSpawnEgg("lode_boar", LODE_BOAR);
		registerSpawnEgg("gilded_finch", GILDED_FINCH);
		registerSpawnEgg("coil_spider", COIL_SPIDER);
		registerSpawnEgg("rust_wolf", RUST_WOLF);
		registerSpawnEgg("statue_mite", STATUE_MITE);
		registerSpawnEgg("tarnish_witch", TARNISH_WITCH);
		registerSpawnEgg("conductor_creeper", CONDUCTOR_CREEPER);
		registerSpawnEgg("ampere_bee", AMPERE_BEE);
		registerSpawnEgg("oxidized_zombie", OXIDIZED_ZOMBIE);
		registerSpawnEgg("patina_skeleton", PATINA_SKELETON);
		registerSpawnEgg("copper_golemite", COPPER_GOLEMITE);
		registerSpawnEgg("spark_fox", SPARK_FOX);
		registerSpawnEgg("verdigris_frog", VERDIGRIS_FROG);
		registerSpawnEgg("coil_chicken", COIL_CHICKEN);
		registerSpawnEgg("lode_cow", LODE_COW);
		registerSpawnEgg("patina_sheep", PATINA_SHEEP);
		registerSpawnEgg("gutter_cat", GUTTER_CAT);
		registerSpawnEgg("thunder_goat", THUNDER_GOAT);
		registerSpawnEgg("scrap_phantom", SCRAP_PHANTOM);
		registerSpawnEgg("coin_ocelot", COIN_OCELOT);

		COPPER_CHITIN = registerDrop("copper_chitin");
		VERDIGRIS_GEL = registerDrop("verdigris_gel");
		PATINA_MEMBRANE = registerDrop("patina_membrane");
		SPARK_TUFT = registerDrop("spark_tuft");
		LODE_HIDE = registerDrop("lode_hide");
		GILDED_FEATHER = registerDrop("gilded_feather");
		LIVE_WIRE = registerDrop("live_wire");
		RUST_FANG = registerDrop("rust_fang");
		TARNISH_DUST = registerDrop("tarnish_dust");
		STORM_CELL = registerDrop("storm_cell");
		PATINA_BONE = registerDrop("patina_bone");
		BURNISHED_COIN = registerDrop("burnished_coin");

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.FAUNA_KEY).register(entries -> {
			for (Item item : TAB_ITEMS) {
				entries.add(item);
			}
		});
	}

	// ------------------------------------------------------------------
	// Natural spawning (same proven pattern as InfernoMobsFeature.registerSpawning):
	// SpawnRestriction.register is private in vanilla but access-widened by Fabric's transitive
	// access wideners. Hostiles reuse HostileEntity::canSpawnInDark (typed to
	// EntityType<? extends HostileEntity>, so it fits every HostileEntity subclass); animals
	// reuse AnimalEntity::isValidNaturalSpawn; bat + snow-golem bases (neither hostile nor
	// animal) use MobEntity::canMobSpawn. SlimeEntity/PhantomEntity implement Monster WITHOUT
	// extending HostileEntity, so their predicates are reimplemented below (difficulty + dark).
	// ------------------------------------------------------------------

	private static void registerSpawning() {
		SpawnRestriction.register(COPPER_BEETLE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(VERDIGRIS_SLIME, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CopperFaunaFeature::canDarkMonsterSpawn);
		SpawnRestriction.register(PATINA_BAT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
		SpawnRestriction.register(SPARK_HARE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(LODE_BOAR, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(GILDED_FINCH, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(COIL_SPIDER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(RUST_WOLF, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(STATUE_MITE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(TARNISH_WITCH, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(CONDUCTOR_CREEPER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(AMPERE_BEE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(OXIDIZED_ZOMBIE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(PATINA_SKELETON, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(COPPER_GOLEMITE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
		SpawnRestriction.register(SPARK_FOX, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(VERDIGRIS_FROG, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(COIL_CHICKEN, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(LODE_COW, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(PATINA_SHEEP, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(GUTTER_CAT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(THUNDER_GOAT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(SCRAP_PHANTOM, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CopperFaunaFeature::canDarkMonsterSpawn);
		SpawnRestriction.register(COIN_OCELOT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);

		// Natural Overworld spawns. addSpawn signature verified via javap:
		// (Predicate<BiomeSelectionContext>, SpawnGroup, EntityType, weight, minGroup, maxGroup).
		// Weights are kept modest so the vanilla Overworld mob mix is complemented, not flooded.
		var overworld = BiomeSelectors.foundInOverworld();
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, COPPER_BEETLE, 12, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, VERDIGRIS_SLIME, 8, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, PATINA_BAT, 5, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, SPARK_HARE, 6, 2, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, LODE_BOAR, 6, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, GILDED_FINCH, 4, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, COIL_SPIDER, 10, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, RUST_WOLF, 4, 2, 4);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, STATUE_MITE, 10, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, TARNISH_WITCH, 4, 1, 1);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, CONDUCTOR_CREEPER, 8, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, AMPERE_BEE, 4, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, OXIDIZED_ZOMBIE, 12, 2, 4);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, PATINA_SKELETON, 12, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, COPPER_GOLEMITE, 2, 1, 1);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, SPARK_FOX, 5, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, VERDIGRIS_FROG, 5, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, COIL_CHICKEN, 6, 2, 4);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, LODE_COW, 6, 2, 4);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, PATINA_SHEEP, 6, 2, 4);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, GUTTER_CAT, 4, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, THUNDER_GOAT, 4, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, SCRAP_PHANTOM, 3, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, COIN_OCELOT, 3, 1, 2);
	}

	/**
	 * Dark-and-not-peaceful spawn gate for the two Monster-but-not-HostileEntity bases
	 * (SlimeEntity, PhantomEntity — their vanilla canSpawn predicates are typed to the vanilla
	 * EntityTypes and cannot be reused directly). Mirrors the standard hostile gate:
	 * difficulty != PEACEFUL, HostileEntity.isSpawnDark, base MobEntity.canMobSpawn checks.
	 */
	private static boolean canDarkMonsterSpawn(EntityType<? extends MobEntity> type, ServerWorldAccess world,
			SpawnReason reason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL
				&& HostileEntity.isSpawnDark(world, pos, random)
				&& MobEntity.canMobSpawn(type, world, reason, pos, random);
	}

	// ------------------------------------------------------------------
	// Handbook: one "mobs" entry per mob (icon = spawn egg, no recipe grid).
	// ------------------------------------------------------------------

	private static void addMobEntry(String id, String textEn, String textDe) {
		HandbookEntries.add(new HandbookEntry("mobs", id,
				"copper_inferno:" + id + "_spawn_egg", null, null, null, 0, textEn, textDe));
	}

	private static void registerHandbookEntries() {
		addMobEntry("copper_beetle",
				"Copper Beetle - a copper-plated silverfish of the Overworld. Its bite briefly slows you. Drops Copper Chitin.",
				"Kupferkäfer - ein kupfergepanzerter Silberfisch der Oberwelt. Sein Biss verlangsamt kurz. Lässt Kupferchitin fallen.");
		addMobEntry("verdigris_slime",
				"Verdigris Slime - a slime the color of weathered copper. Immune to poison; it IS verdigris. Drops Verdigris Gel.",
				"Grünspanschleim - ein Schleim in der Farbe verwitterten Kupfers. Immun gegen Gift; er IST Grünspan. Lässt Grünspangel fallen.");
		addMobEntry("patina_bat",
				"Patina Bat - a bat crusted in blue-green patina, trailing verdigris dust as it flies. Drops Patina Membranes.",
				"Patina-Fledermaus - eine Fledermaus mit blaugrüner Patina, die im Flug Grünspanstaub verliert. Lässt Patinamembranen fallen.");
		addMobEntry("spark_hare",
				"Spark Hare - a static-charged rabbit that is nearly impossible to chase down. Drops Spark Tufts.",
				"Funkenhase - ein statisch geladener Hase, der kaum einzuholen ist. Lässt Funkenbüschel fallen.");
		addMobEntry("lode_boar",
				"Lode Boar - a boar with lodestone-heavy tusks that barely budges when hit. Drops Lode Hides.",
				"Erzkeiler - ein Keiler mit magnetschweren Hauern, der sich kaum zurückstoßen lässt. Lässt Erzhäute fallen.");
		addMobEntry("gilded_finch",
				"Gilded Finch - a hardy finch-sized parrot in gilded plumage. Drops Gilded Feathers.",
				"Vergoldeter Fink - ein zäher, finkengroßer Papagei mit vergoldetem Gefieder. Lässt Vergoldete Federn fallen.");
		addMobEntry("coil_spider",
				"Coil Spider - a spider strung with live copper coils; its shock-bite makes you glow. Drops Live Wires.",
				"Spulenspinne - eine Spinne voller stromführender Kupferspulen; ihr Schockbiss lässt dich leuchten. Lässt Stromdrähte fallen.");
		addMobEntry("rust_wolf",
				"Rust Wolf - a wolf with a rust-matted coat whose bite saps your strength. Tameable like any wolf. Drops Rust Fangs.",
				"Rostwolf - ein Wolf mit rostverfilztem Fell, dessen Biss die Kraft raubt. Zähmbar wie jeder Wolf. Lässt Rostzähne fallen.");
		addMobEntry("statue_mite",
				"Statue Mite - an oversized mite that gnaws on copper statues. Drops Copper Chitin.",
				"Statuenmilbe - eine übergroße Milbe, die an Kupferstatuen nagt. Lässt Kupferchitin fallen.");
		addMobEntry("tarnish_witch",
				"Tarnish Witch - a witch steeped in tarnish fumes, far tougher than her swamp cousin. Drops Tarnish Dust.",
				"Anlaufhexe - eine in Anlaufdämpfen gegerbte Hexe, deutlich zäher als ihre Sumpfverwandte. Lässt Anlaufstaub fallen.");
		addMobEntry("conductor_creeper",
				"Conductor Creeper - a creeper wound with conductive bands that closes distance fast. Drops Live Wires.",
				"Leiter-Creeper - ein mit Leiterbändern umwickelter Creeper, der schnell aufschließt. Lässt Stromdrähte fallen.");
		addMobEntry("ampere_bee",
				"Ampere Bee - a bee humming with static; its sting leaves you reeling. Drops Storm Cells.",
				"Ampere-Biene - eine statisch summende Biene; ihr Stich macht benommen. Lässt Sturmzellen fallen.");
		addMobEntry("oxidized_zombie",
				"Oxidized Zombie - a zombie gone green with oxide whose hit inflicts hunger. Drops Tarnish Dust.",
				"Oxidierter Zombie - ein oxidgrüner Zombie, dessen Schlag Hunger verursacht. Lässt Anlaufstaub fallen.");
		addMobEntry("patina_skeleton",
				"Patina Skeleton - a skeleton in blue-green patina plating that shrugs off arrows. Drops Patina Bones.",
				"Patina-Skelett - ein Skelett mit blaugrüner Patinapanzerung, an der Pfeile abprallen. Lässt Patinaknochen fallen.");
		addMobEntry("copper_golemite",
				"Copper Golemite - a snow-golem-shaped construct cast from copper. Water cannot hurt it. Drops Patina Bones.",
				"Kupfergolemit - ein schneegolemförmiges Konstrukt aus Kupferguss. Wasser kann ihm nichts anhaben. Lässt Patinaknochen fallen.");
		addMobEntry("spark_fox",
				"Spark Fox - a fox with a coat like hot copper filings, quicker than its forest cousin. Drops Spark Tufts.",
				"Funkenfuchs - ein Fuchs mit einem Fell wie heiße Kupferspäne, schneller als sein Waldvetter. Lässt Funkenbüschel fallen.");
		addMobEntry("verdigris_frog",
				"Verdigris Frog - a frog the exact shade of weathered copper roofs, twice as hardy as usual. Drops Verdigris Gel.",
				"Grünspanfrosch - ein Frosch im Farbton verwitterter Kupferdächer, doppelt so robust wie üblich. Lässt Grünspangel fallen.");
		addMobEntry("coil_chicken",
				"Coil Chicken - a quick-footed chicken with copper-coil tail feathers. Drops Gilded Feathers.",
				"Spulenhuhn - ein flinkes Huhn mit Kupferspulen-Schwanzfedern. Lässt Vergoldete Federn fallen.");
		addMobEntry("lode_cow",
				"Lode Cow - a cow that grazes near ore seams and carries the weight to prove it. Drops Lode Hides.",
				"Erzkuh - eine Kuh, die an Erzadern weidet und entsprechend schwer steht. Lässt Erzhäute fallen.");
		addMobEntry("patina_sheep",
				"Patina Sheep - a sheep weathered to patina under the fleece, hardier than it looks. Drops Burnished Coins.",
				"Patina-Schaf - ein Schaf, unter dessen Vlies sich Patina gebildet hat; robuster, als es aussieht. Lässt Polierte Münzen fallen.");
		addMobEntry("gutter_cat",
				"Gutter Cat - a scruffy alley cat that actively hunts silverfish and Copper Beetles. Drops Rust Fangs.",
				"Gossenkatze - eine struppige Straßenkatze, die aktiv Silberfische und Kupferkäfer jagt. Lässt Rostzähne fallen.");
		addMobEntry("thunder_goat",
				"Thunder Goat - a goat that headbutts like a thunderclap, twice as hard as any other goat. Drops Storm Cells.",
				"Donnerziege - eine Ziege, deren Rammstoß wie ein Donnerschlag trifft, doppelt so hart wie üblich. Lässt Sturmzellen fallen.");
		addMobEntry("scrap_phantom",
				"Scrap Phantom - a phantom stitched together from rusted scrap, with a visibly bigger wingspan. Drops Patina Membranes.",
				"Schrottphantom - ein aus rostigem Schrott zusammengeflicktes Phantom mit sichtbar größerer Spannweite. Lässt Patinamembranen fallen.");
		addMobEntry("coin_ocelot",
				"Coin Ocelot - an ocelot with a gleaming coin-gold pelt that glitters as it moves. Drops Burnished Coins.",
				"Münzozelot - ein Ozelot mit münzgoldenem Fell, das beim Laufen glitzert. Lässt Polierte Münzen fallen.");
	}
}
