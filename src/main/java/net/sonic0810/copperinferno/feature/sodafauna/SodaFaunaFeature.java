package net.sonic0810.copperinferno.feature.sodafauna;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.function.Predicate;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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
 * v4 "Soda &amp; Candy Fauna" (WP10): 24 whimsical Overworld mobs, each extending a vanilla
 * entity with exactly one behavioral tweak (either a method override in the entity class or an
 * attribute change flagged below), plus 24 spawn eggs, 12 mob-drop items (each dropped by
 * exactly two mobs), natural Overworld spawns, and handbook entries. EntityType dimensions /
 * eye heights / attachments are copied 1:1 from the vanilla 1.21.9 {@code EntityType}
 * registrations (verified via bytecode); renderers are custom-texture vanilla-renderer
 * subclasses (see {@code SodaFaunaFeatureClient}).
 */
public final class SodaFaunaFeature {
	private SodaFaunaFeature() {
	}

	public static EntityType<FizzSlimeEntity> FIZZ_SLIME;
	public static EntityType<ColaCubeEntity> COLA_CUBE;
	public static EntityType<SodaSpriteEntity> SODA_SPRITE;
	public static EntityType<GummyHopperEntity> GUMMY_HOPPER;
	public static EntityType<CaramelGolemEntity> CARAMEL_GOLEM;
	public static EntityType<SyrupSpiderEntity> SYRUP_SPIDER;
	public static EntityType<BottlecapBeetleEntity> BOTTLECAP_BEETLE;
	public static EntityType<SugarRushFoxEntity> SUGAR_RUSH_FOX;
	public static EntityType<RootBeerBoarEntity> ROOT_BEER_BOAR;
	public static EntityType<CreamCatEntity> CREAM_CAT;
	public static EntityType<PepperPupEntity> PEPPER_PUP;
	public static EntityType<CherryBeeEntity> CHERRY_BEE;
	public static EntityType<GrapeBatEntity> GRAPE_BAT;
	public static EntityType<LimeFrogEntity> LIME_FROG;
	public static EntityType<VanillaSheepEntity> VANILLA_SHEEP;
	public static EntityType<CannedCreeperEntity> CANNED_CREEPER;
	public static EntityType<SodaWitchEntity> SODA_WITCH;
	public static EntityType<FloatPhantomEntity> FLOAT_PHANTOM;
	public static EntityType<FizzyChickenEntity> FIZZY_CHICKEN;
	public static EntityType<DietZombieEntity> DIET_ZOMBIE;
	public static EntityType<SugarSkeletonEntity> SUGAR_SKELETON;
	public static EntityType<CarbonatedCubeEntity> CARBONATED_CUBE;
	public static EntityType<StrawStrayEntity> STRAW_STRAY;
	public static EntityType<PopParrotEntity> POP_PARROT;

	public static Item FIZZ_SLIME_SPAWN_EGG;
	public static Item COLA_CUBE_SPAWN_EGG;
	public static Item SODA_SPRITE_SPAWN_EGG;
	public static Item GUMMY_HOPPER_SPAWN_EGG;
	public static Item CARAMEL_GOLEM_SPAWN_EGG;
	public static Item SYRUP_SPIDER_SPAWN_EGG;
	public static Item BOTTLECAP_BEETLE_SPAWN_EGG;
	public static Item SUGAR_RUSH_FOX_SPAWN_EGG;
	public static Item ROOT_BEER_BOAR_SPAWN_EGG;
	public static Item CREAM_CAT_SPAWN_EGG;
	public static Item PEPPER_PUP_SPAWN_EGG;
	public static Item CHERRY_BEE_SPAWN_EGG;
	public static Item GRAPE_BAT_SPAWN_EGG;
	public static Item LIME_FROG_SPAWN_EGG;
	public static Item VANILLA_SHEEP_SPAWN_EGG;
	public static Item CANNED_CREEPER_SPAWN_EGG;
	public static Item SODA_WITCH_SPAWN_EGG;
	public static Item FLOAT_PHANTOM_SPAWN_EGG;
	public static Item FIZZY_CHICKEN_SPAWN_EGG;
	public static Item DIET_ZOMBIE_SPAWN_EGG;
	public static Item SUGAR_SKELETON_SPAWN_EGG;
	public static Item CARBONATED_CUBE_SPAWN_EGG;
	public static Item STRAW_STRAY_SPAWN_EGG;
	public static Item POP_PARROT_SPAWN_EGG;

	// 12 drops; each is dropped by exactly two of the 24 mobs (see emit_loot_tables in
	// devtools/gen/sodafauna_gen.py for the mapping).
	public static Item FIZZ_GLOBULE;
	public static Item COLA_CHUNK;
	public static Item SPRITE_ESSENCE;
	public static Item GUMMY_DROP;
	public static Item CARAMEL_GLOB;
	public static Item BOTTLECAP;
	public static Item SUGAR_CRYSTAL;
	public static Item CREAM_SWIRL;
	public static Item PEPPER_SPICE;
	public static Item CHERRY_SYRUP;
	public static Item FLOAT_FOAM;
	public static Item ZERO_SYRUP;

	public static void init() {
		registerEntityTypes();
		registerAttributes();
		registerItems();
		registerSpawning();
		registerHandbookEntries();
	}

	private static void registerEntityTypes() {
		// All dimensions/eye heights/attachments/tracking ranges below are copied from the
		// vanilla 1.21.9 EntityType registrations of each base mob (verified via bytecode).
		// slime = dimensions(0.52f, 0.52f).eyeHeight(0.325f).spawnBoxScale(4.0f)
		// .maxTrackingRange(10).notAllowedInPeaceful()
		FIZZ_SLIME = ModEntities.register("fizz_slime",
				EntityType.Builder.create(FizzSlimeEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.52f, 0.52f)
						.eyeHeight(0.325f)
						.spawnBoxScale(4.0f)
						.maxTrackingRange(10)
						.notAllowedInPeaceful());
		// magma_cube = makeFireImmune().dimensions(0.52f, 0.52f).eyeHeight(0.325f)
		// .spawnBoxScale(4.0f).maxTrackingRange(8).notAllowedInPeaceful()
		COLA_CUBE = ModEntities.register("cola_cube",
				EntityType.Builder.create(ColaCubeEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(0.52f, 0.52f)
						.eyeHeight(0.325f)
						.spawnBoxScale(4.0f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// vex = makeFireImmune().dimensions(0.4f, 0.8f).eyeHeight(0.51875f)
		// .passengerAttachments(0.7375f).vehicleAttachment(0.04f).maxTrackingRange(8)
		// .notAllowedInPeaceful()
		SODA_SPRITE = ModEntities.register("soda_sprite",
				EntityType.Builder.create(SodaSpriteEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(0.4f, 0.8f)
						.eyeHeight(0.51875f)
						.passengerAttachments(0.7375f)
						.vehicleAttachment(0.04f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// rabbit = dimensions(0.4f, 0.5f).maxTrackingRange(8)
		GUMMY_HOPPER = ModEntities.register("gummy_hopper",
				EntityType.Builder.create(GummyHopperEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.4f, 0.5f)
						.maxTrackingRange(8));
		// snow_golem = allowSpawningInside(POWDER_SNOW).dimensions(0.7f, 1.9f).eyeHeight(1.7f)
		// .maxTrackingRange(8). Vanilla registers the snow golem as MISC (it is only built, never
		// spawned); this one spawns naturally, so it joins the CREATURE group instead.
		CARAMEL_GOLEM = ModEntities.register("caramel_golem",
				EntityType.Builder.create(CaramelGolemEntity::new, SpawnGroup.CREATURE)
						.allowSpawningInside(Blocks.POWDER_SNOW)
						.dimensions(0.7f, 1.9f)
						.eyeHeight(1.7f)
						.maxTrackingRange(8));
		// spider = dimensions(1.4f, 0.9f).eyeHeight(0.65f).passengerAttachments(0.765f)
		// .maxTrackingRange(8).notAllowedInPeaceful()
		SYRUP_SPIDER = ModEntities.register("syrup_spider",
				EntityType.Builder.create(SyrupSpiderEntity::new, SpawnGroup.MONSTER)
						.dimensions(1.4f, 0.9f)
						.eyeHeight(0.65f)
						.passengerAttachments(0.765f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// silverfish = dimensions(0.4f, 0.3f).eyeHeight(0.13f).passengerAttachments(0.2375f)
		// .maxTrackingRange(8).notAllowedInPeaceful()
		BOTTLECAP_BEETLE = ModEntities.register("bottlecap_beetle",
				EntityType.Builder.create(BottlecapBeetleEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.4f, 0.3f)
						.eyeHeight(0.13f)
						.passengerAttachments(0.2375f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// fox = dimensions(0.6f, 0.7f).eyeHeight(0.4f)
		// .passengerAttachments(new Vec3d(0.0, 0.6375, -0.25)).maxTrackingRange(8)
		// .allowSpawningInside(SWEET_BERRY_BUSH)
		SUGAR_RUSH_FOX = ModEntities.register("sugar_rush_fox",
				EntityType.Builder.create(SugarRushFoxEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.6f, 0.7f)
						.eyeHeight(0.4f)
						.passengerAttachments(new Vec3d(0.0, 0.6375, -0.25))
						.maxTrackingRange(8)
						.allowSpawningInside(Blocks.SWEET_BERRY_BUSH));
		// hoglin = dimensions(1.3964844f, 1.4f).passengerAttachments(1.49375f)
		// .maxTrackingRange(8)
		ROOT_BEER_BOAR = ModEntities.register("root_beer_boar",
				EntityType.Builder.create(RootBeerBoarEntity::new, SpawnGroup.MONSTER)
						.dimensions(1.3964844f, 1.4f)
						.passengerAttachments(1.49375f)
						.maxTrackingRange(8));
		// cat = dimensions(0.6f, 0.7f).eyeHeight(0.35f).passengerAttachments(0.5125f)
		// .maxTrackingRange(8)
		CREAM_CAT = ModEntities.register("cream_cat",
				EntityType.Builder.create(CreamCatEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.6f, 0.7f)
						.eyeHeight(0.35f)
						.passengerAttachments(0.5125f)
						.maxTrackingRange(8));
		// wolf = dimensions(0.6f, 0.85f).eyeHeight(0.68f)
		// .passengerAttachments(new Vec3d(0.0, 0.81875, -0.0625)).maxTrackingRange(10)
		PEPPER_PUP = ModEntities.register("pepper_pup",
				EntityType.Builder.create(PepperPupEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.6f, 0.85f)
						.eyeHeight(0.68f)
						.passengerAttachments(new Vec3d(0.0, 0.81875, -0.0625))
						.maxTrackingRange(10));
		// bee = dimensions(0.7f, 0.6f).eyeHeight(0.3f).maxTrackingRange(8)
		CHERRY_BEE = ModEntities.register("cherry_bee",
				EntityType.Builder.create(CherryBeeEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.7f, 0.6f)
						.eyeHeight(0.3f)
						.maxTrackingRange(8));
		// bat = dimensions(0.5f, 0.9f).eyeHeight(0.45f).maxTrackingRange(5)
		GRAPE_BAT = ModEntities.register("grape_bat",
				EntityType.Builder.create(GrapeBatEntity::new, SpawnGroup.AMBIENT)
						.dimensions(0.5f, 0.9f)
						.eyeHeight(0.45f)
						.maxTrackingRange(5));
		// frog = dimensions(0.5f, 0.5f).passengerAttachments(new Vec3d(0.0, 0.375, -0.25))
		// .maxTrackingRange(10)
		LIME_FROG = ModEntities.register("lime_frog",
				EntityType.Builder.create(LimeFrogEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.5f, 0.5f)
						.passengerAttachments(new Vec3d(0.0, 0.375, -0.25))
						.maxTrackingRange(10));
		// sheep = dimensions(0.9f, 1.3f).eyeHeight(1.235f).passengerAttachments(1.2375f)
		// .maxTrackingRange(10)
		VANILLA_SHEEP = ModEntities.register("vanilla_sheep",
				EntityType.Builder.create(VanillaSheepEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.9f, 1.3f)
						.eyeHeight(1.235f)
						.passengerAttachments(1.2375f)
						.maxTrackingRange(10));
		// creeper = dimensions(0.6f, 1.7f).maxTrackingRange(8).notAllowedInPeaceful()
		CANNED_CREEPER = ModEntities.register("canned_creeper",
				EntityType.Builder.create(CannedCreeperEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.7f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// witch = dimensions(0.6f, 1.95f).eyeHeight(1.62f).passengerAttachments(2.2625f)
		// .maxTrackingRange(8).notAllowedInPeaceful()
		SODA_WITCH = ModEntities.register("soda_witch",
				EntityType.Builder.create(SodaWitchEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.95f)
						.eyeHeight(1.62f)
						.passengerAttachments(2.2625f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// phantom = dimensions(0.9f, 0.5f).eyeHeight(0.175f).passengerAttachments(0.3375f)
		// .vehicleAttachment(-0.125f).maxTrackingRange(8).notAllowedInPeaceful()
		FLOAT_PHANTOM = ModEntities.register("float_phantom",
				EntityType.Builder.create(FloatPhantomEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.9f, 0.5f)
						.eyeHeight(0.175f)
						.passengerAttachments(0.3375f)
						.vehicleAttachment(-0.125f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// chicken = dimensions(0.4f, 0.7f).eyeHeight(0.644f)
		// .passengerAttachments(new Vec3d(0.0, 0.7, -0.1)).maxTrackingRange(10)
		FIZZY_CHICKEN = ModEntities.register("fizzy_chicken",
				EntityType.Builder.create(FizzyChickenEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.4f, 0.7f)
						.eyeHeight(0.644f)
						.passengerAttachments(new Vec3d(0.0, 0.7, -0.1))
						.maxTrackingRange(10));
		// zombie = dimensions(0.6f, 1.95f).eyeHeight(1.74f).passengerAttachments(2.0125f)
		// .vehicleAttachment(-0.7f).maxTrackingRange(8).notAllowedInPeaceful()
		DIET_ZOMBIE = ModEntities.register("diet_zombie",
				EntityType.Builder.create(DietZombieEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.95f)
						.eyeHeight(1.74f)
						.passengerAttachments(2.0125f)
						.vehicleAttachment(-0.7f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// skeleton = dimensions(0.6f, 1.99f).eyeHeight(1.74f).vehicleAttachment(-0.7f)
		// .maxTrackingRange(8).notAllowedInPeaceful()
		SUGAR_SKELETON = ModEntities.register("sugar_skeleton",
				EntityType.Builder.create(SugarSkeletonEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.99f)
						.eyeHeight(1.74f)
						.vehicleAttachment(-0.7f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// magma_cube base again (see cola_cube above).
		CARBONATED_CUBE = ModEntities.register("carbonated_cube",
				EntityType.Builder.create(CarbonatedCubeEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(0.52f, 0.52f)
						.eyeHeight(0.325f)
						.spawnBoxScale(4.0f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// stray = dimensions(0.6f, 1.99f).eyeHeight(1.74f).vehicleAttachment(-0.7f)
		// .allowSpawningInside(POWDER_SNOW).maxTrackingRange(8).notAllowedInPeaceful()
		STRAW_STRAY = ModEntities.register("straw_stray",
				EntityType.Builder.create(StrawStrayEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.99f)
						.eyeHeight(1.74f)
						.vehicleAttachment(-0.7f)
						.allowSpawningInside(Blocks.POWDER_SNOW)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// parrot = dimensions(0.5f, 0.9f).eyeHeight(0.54f).passengerAttachments(0.4625f)
		// .maxTrackingRange(8)
		POP_PARROT = ModEntities.register("pop_parrot",
				EntityType.Builder.create(PopParrotEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.5f, 0.9f)
						.eyeHeight(0.54f)
						.passengerAttachments(0.4625f)
						.maxTrackingRange(8));
	}

	private static void registerAttributes() {
		// Base builders mirror the vanilla DefaultAttributeRegistry entries (verified via
		// bytecode: slime + phantom use HostileEntity.createHostileAttributes(); skeleton and
		// stray use AbstractSkeletonEntity.createAbstractSkeletonAttributes()). The .add(...)
		// calls flagged "tweak" are the mobs whose one behavioral tweak is an attribute change;
		// all other mobs carry their tweak as a method override in their entity class.
		FabricDefaultAttributeRegistry.register(FIZZ_SLIME, HostileEntity.createHostileAttributes());
		FabricDefaultAttributeRegistry.register(COLA_CUBE, MagmaCubeEntity.createMagmaCubeAttributes());
		FabricDefaultAttributeRegistry.register(SODA_SPRITE, VexEntity.createVexAttributes());
		FabricDefaultAttributeRegistry.register(GUMMY_HOPPER, RabbitEntity.createRabbitAttributes());
		FabricDefaultAttributeRegistry.register(CARAMEL_GOLEM, SnowGolemEntity.createSnowGolemAttributes());
		FabricDefaultAttributeRegistry.register(SYRUP_SPIDER, SpiderEntity.createSpiderAttributes());
		// Tweak: ARMOR 6 (vanilla silverfish has none) - the bottlecap is crimped steel.
		FabricDefaultAttributeRegistry.register(BOTTLECAP_BEETLE, SilverfishEntity.createSilverfishAttributes()
				.add(EntityAttributes.ARMOR, 6.0));
		// Tweak: MOVEMENT_SPEED 0.42 (vanilla fox runs at 0.3) - permanent sugar rush.
		FabricDefaultAttributeRegistry.register(SUGAR_RUSH_FOX, FoxEntity.createFoxAttributes()
				.add(EntityAttributes.MOVEMENT_SPEED, 0.42));
		FabricDefaultAttributeRegistry.register(ROOT_BEER_BOAR, HoglinEntity.createHoglinAttributes());
		// Tweak: MAX_HEALTH 14 (vanilla cat has 10) - raised on soda-fountain cream.
		FabricDefaultAttributeRegistry.register(CREAM_CAT, CatEntity.createCatAttributes()
				.add(EntityAttributes.MAX_HEALTH, 14.0));
		FabricDefaultAttributeRegistry.register(PEPPER_PUP, WolfEntity.createWolfAttributes());
		FabricDefaultAttributeRegistry.register(CHERRY_BEE, BeeEntity.createBeeAttributes());
		// Tweak: MAX_HEALTH 10 (vanilla bat has 6) - grape soda is fortifying.
		FabricDefaultAttributeRegistry.register(GRAPE_BAT, BatEntity.createBatAttributes()
				.add(EntityAttributes.MAX_HEALTH, 10.0));
		// Tweak: SCALE 1.4 - a key-lime frog the size of a dessert plate.
		FabricDefaultAttributeRegistry.register(LIME_FROG, FrogEntity.createFrogAttributes()
				.add(EntityAttributes.SCALE, 1.4));
		FabricDefaultAttributeRegistry.register(VANILLA_SHEEP, SheepEntity.createSheepAttributes());
		// Tweak: KNOCKBACK_RESISTANCE 0.8 (vanilla creeper has none) - the can is ballast.
		FabricDefaultAttributeRegistry.register(CANNED_CREEPER, CreeperEntity.createCreeperAttributes()
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.8));
		// Tweak: MOVEMENT_SPEED 0.3 (vanilla witch walks at 0.25) - pure caffeine.
		FabricDefaultAttributeRegistry.register(SODA_WITCH, WitchEntity.createWitchAttributes()
				.add(EntityAttributes.MOVEMENT_SPEED, 0.3));
		// Tweak: SCALE 0.8 - a dessert-sized phantom bobbing like float ice cream.
		FabricDefaultAttributeRegistry.register(FLOAT_PHANTOM, HostileEntity.createHostileAttributes()
				.add(EntityAttributes.SCALE, 0.8));
		FabricDefaultAttributeRegistry.register(FIZZY_CHICKEN, ChickenEntity.createChickenAttributes());
		FabricDefaultAttributeRegistry.register(DIET_ZOMBIE, ZombieEntity.createZombieAttributes());
		FabricDefaultAttributeRegistry.register(SUGAR_SKELETON, AbstractSkeletonEntity.createAbstractSkeletonAttributes());
		FabricDefaultAttributeRegistry.register(CARBONATED_CUBE, MagmaCubeEntity.createMagmaCubeAttributes());
		// Tweak: MOVEMENT_SPEED 0.3 (vanilla stray walks at 0.25) - light as paper straws.
		FabricDefaultAttributeRegistry.register(STRAW_STRAY, AbstractSkeletonEntity.createAbstractSkeletonAttributes()
				.add(EntityAttributes.MOVEMENT_SPEED, 0.3));
		FabricDefaultAttributeRegistry.register(POP_PARROT, ParrotEntity.createParrotAttributes());
	}

	private static void registerItems() {
		// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the vanilla SpawnEggItem
		// reads its entity type from in 1.21.9 (same proven pattern as infernomobs).
		FIZZ_SLIME_SPAWN_EGG = ModItems.register("fizz_slime_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(FIZZ_SLIME));
		COLA_CUBE_SPAWN_EGG = ModItems.register("cola_cube_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(COLA_CUBE));
		SODA_SPRITE_SPAWN_EGG = ModItems.register("soda_sprite_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SODA_SPRITE));
		GUMMY_HOPPER_SPAWN_EGG = ModItems.register("gummy_hopper_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(GUMMY_HOPPER));
		CARAMEL_GOLEM_SPAWN_EGG = ModItems.register("caramel_golem_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CARAMEL_GOLEM));
		SYRUP_SPIDER_SPAWN_EGG = ModItems.register("syrup_spider_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SYRUP_SPIDER));
		BOTTLECAP_BEETLE_SPAWN_EGG = ModItems.register("bottlecap_beetle_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(BOTTLECAP_BEETLE));
		SUGAR_RUSH_FOX_SPAWN_EGG = ModItems.register("sugar_rush_fox_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SUGAR_RUSH_FOX));
		ROOT_BEER_BOAR_SPAWN_EGG = ModItems.register("root_beer_boar_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(ROOT_BEER_BOAR));
		CREAM_CAT_SPAWN_EGG = ModItems.register("cream_cat_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CREAM_CAT));
		PEPPER_PUP_SPAWN_EGG = ModItems.register("pepper_pup_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(PEPPER_PUP));
		CHERRY_BEE_SPAWN_EGG = ModItems.register("cherry_bee_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CHERRY_BEE));
		GRAPE_BAT_SPAWN_EGG = ModItems.register("grape_bat_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(GRAPE_BAT));
		LIME_FROG_SPAWN_EGG = ModItems.register("lime_frog_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(LIME_FROG));
		VANILLA_SHEEP_SPAWN_EGG = ModItems.register("vanilla_sheep_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(VANILLA_SHEEP));
		CANNED_CREEPER_SPAWN_EGG = ModItems.register("canned_creeper_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CANNED_CREEPER));
		SODA_WITCH_SPAWN_EGG = ModItems.register("soda_witch_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SODA_WITCH));
		FLOAT_PHANTOM_SPAWN_EGG = ModItems.register("float_phantom_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(FLOAT_PHANTOM));
		FIZZY_CHICKEN_SPAWN_EGG = ModItems.register("fizzy_chicken_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(FIZZY_CHICKEN));
		DIET_ZOMBIE_SPAWN_EGG = ModItems.register("diet_zombie_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(DIET_ZOMBIE));
		SUGAR_SKELETON_SPAWN_EGG = ModItems.register("sugar_skeleton_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SUGAR_SKELETON));
		CARBONATED_CUBE_SPAWN_EGG = ModItems.register("carbonated_cube_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CARBONATED_CUBE));
		STRAW_STRAY_SPAWN_EGG = ModItems.register("straw_stray_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(STRAW_STRAY));
		POP_PARROT_SPAWN_EGG = ModItems.register("pop_parrot_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(POP_PARROT));

		FIZZ_GLOBULE = ModItems.register("fizz_globule", Item::new, new Item.Settings());
		COLA_CHUNK = ModItems.register("cola_chunk", Item::new, new Item.Settings());
		SPRITE_ESSENCE = ModItems.register("sprite_essence", Item::new, new Item.Settings());
		GUMMY_DROP = ModItems.register("gummy_drop", Item::new, new Item.Settings());
		CARAMEL_GLOB = ModItems.register("caramel_glob", Item::new, new Item.Settings());
		BOTTLECAP = ModItems.register("bottlecap", Item::new, new Item.Settings());
		SUGAR_CRYSTAL = ModItems.register("sugar_crystal", Item::new, new Item.Settings());
		CREAM_SWIRL = ModItems.register("cream_swirl", Item::new, new Item.Settings());
		PEPPER_SPICE = ModItems.register("pepper_spice", Item::new, new Item.Settings());
		CHERRY_SYRUP = ModItems.register("cherry_syrup", Item::new, new Item.Settings());
		FLOAT_FOAM = ModItems.register("float_foam", Item::new, new Item.Settings());
		ZERO_SYRUP = ModItems.register("zero_syrup", Item::new, new Item.Settings());

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.FAUNA_KEY).register(entries -> {
			// Spawn eggs in roster order, then the twelve drops.
			entries.add(FIZZ_SLIME_SPAWN_EGG);
			entries.add(COLA_CUBE_SPAWN_EGG);
			entries.add(SODA_SPRITE_SPAWN_EGG);
			entries.add(GUMMY_HOPPER_SPAWN_EGG);
			entries.add(CARAMEL_GOLEM_SPAWN_EGG);
			entries.add(SYRUP_SPIDER_SPAWN_EGG);
			entries.add(BOTTLECAP_BEETLE_SPAWN_EGG);
			entries.add(SUGAR_RUSH_FOX_SPAWN_EGG);
			entries.add(ROOT_BEER_BOAR_SPAWN_EGG);
			entries.add(CREAM_CAT_SPAWN_EGG);
			entries.add(PEPPER_PUP_SPAWN_EGG);
			entries.add(CHERRY_BEE_SPAWN_EGG);
			entries.add(GRAPE_BAT_SPAWN_EGG);
			entries.add(LIME_FROG_SPAWN_EGG);
			entries.add(VANILLA_SHEEP_SPAWN_EGG);
			entries.add(CANNED_CREEPER_SPAWN_EGG);
			entries.add(SODA_WITCH_SPAWN_EGG);
			entries.add(FLOAT_PHANTOM_SPAWN_EGG);
			entries.add(FIZZY_CHICKEN_SPAWN_EGG);
			entries.add(DIET_ZOMBIE_SPAWN_EGG);
			entries.add(SUGAR_SKELETON_SPAWN_EGG);
			entries.add(CARBONATED_CUBE_SPAWN_EGG);
			entries.add(STRAW_STRAY_SPAWN_EGG);
			entries.add(POP_PARROT_SPAWN_EGG);
			entries.add(FIZZ_GLOBULE);
			entries.add(COLA_CHUNK);
			entries.add(SPRITE_ESSENCE);
			entries.add(GUMMY_DROP);
			entries.add(CARAMEL_GLOB);
			entries.add(BOTTLECAP);
			entries.add(SUGAR_CRYSTAL);
			entries.add(CREAM_SWIRL);
			entries.add(PEPPER_SPICE);
			entries.add(CHERRY_SYRUP);
			entries.add(FLOAT_FOAM);
			entries.add(ZERO_SYRUP);
		});
	}

	private static void registerSpawning() {
		// SpawnRestriction.register is private in vanilla but access-widened by Fabric's
		// transitive access wideners (same proven pattern as infernomobs). Locations/heightmaps
		// mirror the vanilla entries for each base (SpawnRestriction bytecode; parrot is the one
		// base on MOTION_BLOCKING, fox/vex/phantom are UNRESTRICTED). Where the vanilla predicate
		// is typed to the vanilla EntityType (slime, magma_cube, silverfish, hoglin, rabbit,
		// wolf, bee, frog, bat, parrot, snow_golem, phantom), the closest reusable predicate is
		// substituted: HostileEntity::canSpawnInDark for HostileEntity subclasses,
		// AnimalEntity::isValidNaturalSpawn for animals, MobEntity::canMobSpawn for the rest,
		// and difficulty-gated lambdas for the slime-family cubes (mirroring the vanilla
		// magma-cube predicate, which is only a difficulty != PEACEFUL check).
		SpawnRestriction.register(FIZZ_SLIME, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SodaFaunaFeature::canCubeSpawnInDark);
		SpawnRestriction.register(COLA_CUBE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SodaFaunaFeature::canCubeSpawnInDark);
		SpawnRestriction.register(SODA_SPRITE, SpawnLocationTypes.UNRESTRICTED,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(GUMMY_HOPPER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(CARAMEL_GOLEM, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
		SpawnRestriction.register(SYRUP_SPIDER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(BOTTLECAP_BEETLE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(SUGAR_RUSH_FOX, SpawnLocationTypes.UNRESTRICTED,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(ROOT_BEER_BOAR, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
		SpawnRestriction.register(CREAM_CAT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(PEPPER_PUP, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(CHERRY_BEE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(GRAPE_BAT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SodaFaunaFeature::canGrapeBatSpawn);
		SpawnRestriction.register(LIME_FROG, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(VANILLA_SHEEP, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(CANNED_CREEPER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(SODA_WITCH, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		// Phantom is not a HostileEntity subclass; mirror canSpawnInDark via the public
		// HostileEntity.isSpawnDark helper (verified via javap).
		SpawnRestriction.register(FLOAT_PHANTOM, SpawnLocationTypes.UNRESTRICTED,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(type, world, reason, pos, random) -> world.getDifficulty() != Difficulty.PEACEFUL
						&& HostileEntity.isSpawnDark(world, pos, random)
						&& MobEntity.canMobSpawn(type, world, reason, pos, random));
		SpawnRestriction.register(FIZZY_CHICKEN, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		SpawnRestriction.register(DIET_ZOMBIE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(SUGAR_SKELETON, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(CARBONATED_CUBE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SodaFaunaFeature::canCubeSpawnInDark);
		SpawnRestriction.register(STRAW_STRAY, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(POP_PARROT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);

		// Natural Overworld spawns. Monster weights sit well below the vanilla staples (zombie
		// and skeleton are 95 in most biomes) and creatures below sheep/pig (8-12), so the soda
		// fauna seasons the spawn pool instead of flooding it.
		Predicate<BiomeSelectionContext> overworld = BiomeSelectors.foundInOverworld();
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, FIZZ_SLIME, 12, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, COLA_CUBE, 10, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, SODA_SPRITE, 6, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, GUMMY_HOPPER, 8, 2, 4);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, CARAMEL_GOLEM, 4, 1, 1);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, SYRUP_SPIDER, 12, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, BOTTLECAP_BEETLE, 10, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, SUGAR_RUSH_FOX, 6, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, ROOT_BEER_BOAR, 6, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, CREAM_CAT, 6, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, PEPPER_PUP, 6, 2, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, CHERRY_BEE, 6, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.AMBIENT, GRAPE_BAT, 8, 2, 4);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, LIME_FROG, 6, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, VANILLA_SHEEP, 8, 2, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, CANNED_CREEPER, 10, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, SODA_WITCH, 5, 1, 1);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, FLOAT_PHANTOM, 5, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, FIZZY_CHICKEN, 8, 2, 4);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, DIET_ZOMBIE, 12, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, SUGAR_SKELETON, 12, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, CARBONATED_CUBE, 10, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, STRAW_STRAY, 8, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, POP_PARROT, 6, 1, 2);
	}

	/**
	 * Spawn predicate for the slime-family cubes (fizz slime, cola cube, carbonated cube).
	 * Mirrors the vanilla magma-cube predicate (difficulty != PEACEFUL; verified via bytecode -
	 * the vanilla method is typed to {@code EntityType<MagmaCubeEntity>} so it cannot be reused)
	 * plus the standard hostile darkness gate so the cubes stick to night/cave spawns in the
	 * Overworld instead of popping up in daylight.
	 */
	private static boolean canCubeSpawnInDark(EntityType<? extends MobEntity> type, ServerWorldAccess world,
			SpawnReason reason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL
				&& HostileEntity.isSpawnDark(world, pos, random);
	}

	/**
	 * Mirrors vanilla {@code BatEntity.canSpawn} (verified against the 1.21.9 bytecode; the
	 * vanilla method is typed to {@code EntityType<BatEntity>} so it cannot be reused directly):
	 * below the WORLD_SURFACE heightmap (caves only, never open daylight), a light gate of
	 * {@code light <= random.nextInt(4)} (relaxed to 7 around Halloween, otherwise a 50% cull),
	 * a BATS_SPAWNABLE_ON block below, and the base {@code MobEntity.canMobSpawn} checks.
	 */
	private static boolean canGrapeBatSpawn(EntityType<GrapeBatEntity> type, ServerWorldAccess world,
			SpawnReason reason, BlockPos pos, Random random) {
		if (pos.getY() >= world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos).getY()) {
			return false;
		}
		int light = world.getLightLevel(pos);
		int maxLight = 4;
		if (isTodayAroundHalloween()) {
			maxLight = 7;
		} else if (random.nextBoolean()) {
			return false;
		}
		if (light > random.nextInt(maxLight)) {
			return false;
		}
		if (!world.getBlockState(pos.down()).isIn(BlockTags.BATS_SPAWNABLE_ON)) {
			return false;
		}
		return MobEntity.canMobSpawn(type, world, reason, pos, random);
	}

	/**
	 * Mirrors the private vanilla {@code BatEntity.isTodayAroundHalloween} (bytecode: Oct 20
	 * through Nov 3 inclusive).
	 */
	private static boolean isTodayAroundHalloween() {
		LocalDate date = LocalDate.now();
		int day = date.get(ChronoField.DAY_OF_MONTH);
		int month = date.get(ChronoField.MONTH_OF_YEAR);
		return (month == 10 && day >= 20) || (month == 11 && day <= 3);
	}

	private static void registerHandbookEntries() {
		mobEntry("fizz_slime",
				"Fizz Slime - a hyper-carbonated lemon-lime slime that hops twice as often as its lazy cousin. Splits when slain; the smallest ones drop Fizz Globules.",
				"Brauseschleim - ein überkohlensäurehaltiger Zitronen-Limetten-Schleim, der doppelt so oft hüpft wie sein träger Vetter. Teilt sich beim Tod; die kleinsten lassen Brausekügelchen fallen.");
		mobEntry("cola_cube",
				"Cola Cube - a cube of dark, sticky cola that splashes instead of burning when it lands. Fire-immune and splits like a magma cube. Drops Cola Chunks.",
				"Colawürfel - ein Würfel aus dunkler, klebriger Cola, der beim Landen spritzt statt zu brennen. Feuerfest und teilt sich wie ein Magmawürfel. Lässt Colabrocken fallen.");
		mobEntry("soda_sprite",
				"Soda Sprite - a mischievous lemon-lime spirit that flies through walls and trails lime fizz wherever it goes. Drops Sprite Essence.",
				"Limogeist - ein verspielter Zitronen-Limetten-Geist, der durch Wände fliegt und überall Limettensprudel hinterlässt. Lässt Limoessenz fallen.");
		mobEntry("gummy_hopper",
				"Gummy Hopper - a wobbling gummy-candy rabbit. Gummy physics: it never takes fall damage. Drops Gummy Drops.",
				"Gummihüpfer - ein wabbelndes Gummibonbon-Kaninchen. Gummiphysik: Es nimmt niemals Fallschaden. Lässt Gummitropfen fallen.");
		mobEntry("caramel_golem",
				"Caramel Golem - a golem molded from hot caramel instead of snow. Caramel does not dissolve: rain and water cannot harm it. Drops Caramel Globs.",
				"Karamellgolem - ein Golem aus heißem Karamell statt Schnee geformt. Karamell löst sich nicht auf: Regen und Wasser schaden ihm nicht. Lässt Karamellklumpen fallen.");
		mobEntry("syrup_spider",
				"Syrup Spider - a spider dripping with maple-thick syrup. Its sticky bite bogs victims down with Slowness II. Drops Caramel Globs.",
				"Sirupspinne - eine Spinne, die vor zähem Ahornsirup trieft. Ihr klebriger Biss verlangsamt Opfer mit Langsamkeit II. Lässt Karamellklumpen fallen.");
		mobEntry("bottlecap_beetle",
				"Bottlecap Beetle - a beetle wearing a crimped steel bottlecap for a shell, worth 6 armor points. Drops Bottlecaps.",
				"Kronkorkenkäfer - ein Käfer mit einem gebördelten Stahl-Kronkorken als Panzer, der 6 Rüstungspunkte wert ist. Lässt Kronkorken fallen.");
		mobEntry("sugar_rush_fox",
				"Sugar Rush Fox - a candy-pink fox permanently on a sugar rush, noticeably faster than any wild fox. Drops Sugar Crystals.",
				"Zuckerrausch-Fuchs - ein bonbonrosa Fuchs im ewigen Zuckerrausch, deutlich schneller als jeder wilde Fuchs. Lässt Zuckerkristalle fallen.");
		mobEntry("root_beer_boar",
				"Root Beer Boar - a root-beer-brown hoglin that wandered out of the Nether and stayed for the taste. It never zombifies in the Overworld. Drops Cola Chunks.",
				"Wurzelbier-Keiler - ein wurzelbierbrauner Hoglin, der aus dem Nether wanderte und des Geschmacks wegen blieb. Er verwandelt sich in der Oberwelt nie in einen Zoglin. Lässt Colabrocken fallen.");
		mobEntry("cream_cat",
				"Cream Cat - a plump cream-white cat raised on soda-fountain cream, tougher than any alley cat. Drops Cream Swirls.",
				"Sahnekatze - eine rundliche cremeweiße Katze, aufgezogen mit Sodabrunnen-Sahne, zäher als jede Gassenkatze. Lässt Sahnewirbel fallen.");
		mobEntry("pepper_pup",
				"Pepper Pup - a maroon wolf pup that has clearly been in the Dr.Pepper again. Its pepper-hot bite sets victims on fire. Tameable like any wolf. Drops Pepper Spice.",
				"Pfefferwelpe - ein kastanienbrauner Wolfswelpe, der eindeutig wieder am Dr.Pepper war. Sein pfefferscharfer Biss setzt Opfer in Brand. Zähmbar wie jeder Wolf. Lässt Pfeffergewürz fallen.");
		mobEntry("cherry_bee",
				"Cherry Bee - a cherry-red bee brewing cherry syrup instead of honey. The sweetest sting in the game: after stinging it grants its victim Regeneration as an apology. Drops Cherry Syrup.",
				"Kirschbiene - eine kirschrote Biene, die Kirschsirup statt Honig braut. Der süßeste Stich im Spiel: Nach dem Stechen schenkt sie ihrem Opfer Regeneration als Entschuldigung. Lässt Kirschsirup fallen.");
		mobEntry("grape_bat",
				"Grape Bat - a plump grape-purple bat that hangs in caves like a bunch of grapes. Grape soda is fortifying: it is much hardier than a common bat. Drops Gummy Drops.",
				"Traubenfledermaus - eine pralle traubenlila Fledermaus, die wie eine Weintraube in Höhlen hängt. Traubenlimo stärkt: Sie ist viel robuster als eine gewöhnliche Fledermaus. Lässt Gummitropfen fallen.");
		mobEntry("lime_frog",
				"Lime Frog - a key-lime frog the size of a dessert plate, half again as large as its pond-dwelling kin. Drops Sprite Essence.",
				"Limettenfrosch - ein Limettenfrosch von der Größe eines Desserttellers, anderthalbmal so groß wie seine Teichverwandten. Lässt Limoessenz fallen.");
		mobEntry("vanilla_sheep",
				"Vanilla Sheep - a vanilla-cream sheep with a soft-serve swirl of a fleece. It can also be bred with sugar. Drops Cream Swirls.",
				"Vanilleschaf - ein vanillecremefarbenes Schaf mit einem Softeis-Wirbel als Vlies. Es kann auch mit Zucker gezüchtet werden. Lässt Sahnewirbel fallen.");
		mobEntry("canned_creeper",
				"Canned Creeper - a creeper sealed inside a soda can. The can is ballast: knockback barely moves it. Explodes like any creeper - shaken, not stirred. Drops Bottlecaps.",
				"Dosen-Creeper - ein Creeper, versiegelt in einer Getränkedose. Die Dose ist Ballast: Rückstoß bewegt ihn kaum. Explodiert wie jeder Creeper - geschüttelt, nicht gerührt. Lässt Kronkorken fallen.");
		mobEntry("soda_witch",
				"Soda Witch - a witch who swapped her cauldron for a soda fountain. Pure caffeine keeps her walking faster than her swamp sisters. Drops Pepper Spice.",
				"Limohexe - eine Hexe, die ihren Kessel gegen einen Sodabrunnen tauschte. Pures Koffein lässt sie schneller laufen als ihre Sumpfschwestern. Lässt Pfeffergewürz fallen.");
		mobEntry("float_phantom",
				"Float Phantom - a foam-white phantom bobbing along like the ice cream on a root beer float, dessert-sized and visibly smaller than the real terror. Drops Float Foam.",
				"Schwebschaum-Phantom - ein schaumweißes Phantom, das wie die Eiskugel auf einem Root-Beer-Float dahintreibt, dessertgroß und sichtbar kleiner als der echte Schrecken. Lässt Schwebschaum fallen.");
		mobEntry("fizzy_chicken",
				"Fizzy Chicken - a soda-yellow chicken with a carbonated metabolism that lays eggs at least twice as fast. Drops Fizz Globules.",
				"Sprudelhuhn - ein limogelbes Huhn mit kohlensäurehaltigem Stoffwechsel, das mindestens doppelt so schnell Eier legt. Lässt Brausekügelchen fallen.");
		mobEntry("diet_zombie",
				"Diet Zombie - a pale, zero-calorie zombie. Zero sugar, zero burn: it shambles on through the day without catching fire. Drops Zero Syrup.",
				"Diät-Zombie - ein blasser Zombie ohne Kalorien. Null Zucker, null Brand: Er schlurft durch den Tag, ohne Feuer zu fangen. Lässt Zero-Sirup fallen.");
		mobEntry("sugar_skeleton",
				"Sugar Skeleton - a skeleton cast from pressed white sugar. Sugar never freezes, so powder snow cannot harm it. Drops Sugar Crystals.",
				"Zuckerskelett - ein Skelett aus gepresstem weißem Zucker. Zucker gefriert nie, daher kann Pulverschnee ihm nichts anhaben. Lässt Zuckerkristalle fallen.");
		mobEntry("carbonated_cube",
				"Carbonated Cube - a pale, bubbling cube of pure sparkling water. It is just fizzy water: its contact damage is half that of a magma cube. Drops Float Foam.",
				"Sprudelwürfel - ein blasser, blubbernder Würfel aus purem Sprudelwasser. Es ist nur Sprudel: Sein Kontaktschaden ist halb so hoch wie der eines Magmawürfels. Lässt Schwebschaum fallen.");
		mobEntry("straw_stray",
				"Straw Stray - a stray built out of striped paper drinking straws, light as paper and quicker than its frozen kin. Keeps the slowness arrows. Drops Zero Syrup.",
				"Strohhalm-Eiswanderer - ein Eiswanderer aus gestreiften Papierstrohhalmen, leicht wie Papier und flinker als seine gefrorene Verwandtschaft. Behält die Langsamkeitspfeile. Lässt Zero-Sirup fallen.");
		mobEntry("pop_parrot",
				"Pop Parrot - a soda-orange parrot that pops and crackles like candy in cola, trailing musical notes as it flies. Drops Cherry Syrup.",
				"Knisterpapagei - ein limoorangefarbener Papagei, der knallt und knistert wie Brausebonbons in Cola und im Flug Musiknoten hinterlässt. Lässt Kirschsirup fallen.");
	}

	private static void mobEntry(String id, String textEn, String textDe) {
		HandbookEntries.add(new HandbookEntry("mobs", id,
				"copper_inferno:" + id + "_spawn_egg", null, null, null, 0, textEn, textDe));
	}
}
