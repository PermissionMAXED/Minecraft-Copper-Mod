package net.sonic0810.copperinferno.feature.constructs;

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
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModEntities;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * v4 "Constructs &amp; Spirits" (WP11): 24 mostly-hostile machine-mobs split between the
 * Overworld (12) and the Inferno biomes (12). Every construct extends a vanilla entity, carries
 * exactly ONE behavioral tweak (see the individual entity classes), reuses the vanilla renderer
 * with a custom recolored texture (see {@code ConstructsFeatureClient}), and registers a spawn
 * egg, a loot table, natural spawns and a handbook entry. EntityType dimensions/eye heights/
 * attachments are copied 1:1 from the vanilla {@code EntityType} registrations (bytecode-
 * verified via javap, chains quoted inline below).
 */
public final class ConstructsFeature {
	private ConstructsFeature() {
	}

	public static EntityType<CopperSentinelEntity> COPPER_SENTINEL;
	public static EntityType<SlagConstructEntity> SLAG_CONSTRUCT;
	public static EntityType<DoomAcolyteEntity> DOOM_ACOLYTE;
	public static EntityType<ForgeKeeperEntity> FORGE_KEEPER;
	public static EntityType<AnvilMimicEntity> ANVIL_MIMIC;
	public static EntityType<GearSpiderEntity> GEAR_SPIDER;
	public static EntityType<PistonHopperEntity> PISTON_HOPPER;
	public static EntityType<RedstoneShadeEntity> REDSTONE_SHADE;
	public static EntityType<WireWraithEntity> WIRE_WRAITH;
	public static EntityType<BoilerBlazeEntity> BOILER_BLAZE;
	public static EntityType<FurnaceGolemEntity> FURNACE_GOLEM;
	public static EntityType<ScrapVultureEntity> SCRAP_VULTURE;
	public static EntityType<ClockworkBeeEntity> CLOCKWORK_BEE;
	public static EntityType<SteamGhastEntity> STEAM_GHAST;
	public static EntityType<PipeSerpentEntity> PIPE_SERPENT;
	public static EntityType<GrinderZoglinEntity> GRINDER_ZOGLIN;
	public static EntityType<PlatedHuskEntity> PLATED_HUSK;
	public static EntityType<RivetedStrayEntity> RIVETED_STRAY;
	public static EntityType<TeslaCreeperEntity> TESLA_CREEPER;
	public static EntityType<MagnetMiteEntity> MAGNET_MITE;
	public static EntityType<CrucibleWitchEntity> CRUCIBLE_WITCH;
	public static EntityType<BellowsBatEntity> BELLOWS_BAT;
	public static EntityType<IngotGolemEntity> INGOT_GOLEM;
	public static EntityType<DoomMarauderEntity> DOOM_MARAUDER;

	public static Item COPPER_SENTINEL_SPAWN_EGG;
	public static Item SLAG_CONSTRUCT_SPAWN_EGG;
	public static Item DOOM_ACOLYTE_SPAWN_EGG;
	public static Item FORGE_KEEPER_SPAWN_EGG;
	public static Item ANVIL_MIMIC_SPAWN_EGG;
	public static Item GEAR_SPIDER_SPAWN_EGG;
	public static Item PISTON_HOPPER_SPAWN_EGG;
	public static Item REDSTONE_SHADE_SPAWN_EGG;
	public static Item WIRE_WRAITH_SPAWN_EGG;
	public static Item BOILER_BLAZE_SPAWN_EGG;
	public static Item FURNACE_GOLEM_SPAWN_EGG;
	public static Item SCRAP_VULTURE_SPAWN_EGG;
	public static Item CLOCKWORK_BEE_SPAWN_EGG;
	public static Item STEAM_GHAST_SPAWN_EGG;
	public static Item PIPE_SERPENT_SPAWN_EGG;
	public static Item GRINDER_ZOGLIN_SPAWN_EGG;
	public static Item PLATED_HUSK_SPAWN_EGG;
	public static Item RIVETED_STRAY_SPAWN_EGG;
	public static Item TESLA_CREEPER_SPAWN_EGG;
	public static Item MAGNET_MITE_SPAWN_EGG;
	public static Item CRUCIBLE_WITCH_SPAWN_EGG;
	public static Item BELLOWS_BAT_SPAWN_EGG;
	public static Item INGOT_GOLEM_SPAWN_EGG;
	public static Item DOOM_MARAUDER_SPAWN_EGG;

	public static Item SENTINEL_PLATING;
	public static Item SLAG_GRIT;
	public static Item DOOM_EMBLEM;
	public static Item FORGE_BELLOWS;
	public static Item ANVIL_SHARD;
	public static Item COPPER_GEARWHEEL;
	public static Item PISTON_SPRING;
	public static Item REDSTONE_FILAMENT;
	public static Item BOILER_PLATE;
	public static Item RIVET_BOLT;
	public static Item MAGNETITE_SHARD;
	public static Item CRUCIBLE_DROSS;

	public static void init() {
		registerEntityTypes();
		registerAttributes();
		registerItems();
		registerSpawning();
		registerHandbookEntries();
	}

	private static void registerEntityTypes() {
		// All builder chains are copied 1:1 from the vanilla 1.21.9 EntityType registrations
		// (bytecode-verified via javap -c EntityType; the vanilla chain is quoted above each
		// registration). SpawnGroup is MONSTER for all hostiles, including the golem/rabbit
		// based ones; piston_hopper additionally gets notAllowedInPeaceful() (deviation from
		// the vanilla rabbit chain — it is a hostile construct).

		// iron_golem = dimensions(1.4f, 2.7f).maxTrackingRange(10)
		COPPER_SENTINEL = ModEntities.register("copper_sentinel",
				EntityType.Builder.create(CopperSentinelEntity::new, SpawnGroup.MONSTER)
						.dimensions(1.4f, 2.7f)
						.maxTrackingRange(10)
						.notAllowedInPeaceful());
		SLAG_CONSTRUCT = ModEntities.register("slag_construct",
				EntityType.Builder.create(SlagConstructEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(1.4f, 2.7f)
						.maxTrackingRange(10)
						.notAllowedInPeaceful());
		// vindicator = dimensions(0.6f, 1.95f).passengerAttachments(2.0f)
		// .vehicleAttachment(-0.6f).maxTrackingRange(8).notAllowedInPeaceful()
		DOOM_ACOLYTE = ModEntities.register("doom_acolyte",
				EntityType.Builder.create(DoomAcolyteEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.95f)
						.passengerAttachments(2.0f)
						.vehicleAttachment(-0.6f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// pillager = spawnableFarFromPlayer().dimensions(0.6f, 1.95f).passengerAttachments(2.0f)
		// .vehicleAttachment(-0.6f).maxTrackingRange(8).notAllowedInPeaceful()
		FORGE_KEEPER = ModEntities.register("forge_keeper",
				EntityType.Builder.create(ForgeKeeperEntity::new, SpawnGroup.MONSTER)
						.spawnableFarFromPlayer()
						.dimensions(0.6f, 1.95f)
						.passengerAttachments(2.0f)
						.vehicleAttachment(-0.6f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// silverfish = dimensions(0.4f, 0.3f).eyeHeight(0.13f).passengerAttachments(0.2375f)
		// .maxTrackingRange(8).notAllowedInPeaceful()
		ANVIL_MIMIC = ModEntities.register("anvil_mimic",
				EntityType.Builder.create(AnvilMimicEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.4f, 0.3f)
						.eyeHeight(0.13f)
						.passengerAttachments(0.2375f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// spider = dimensions(1.4f, 0.9f).eyeHeight(0.65f).passengerAttachments(0.765f)
		// .maxTrackingRange(8).notAllowedInPeaceful()
		GEAR_SPIDER = ModEntities.register("gear_spider",
				EntityType.Builder.create(GearSpiderEntity::new, SpawnGroup.MONSTER)
						.dimensions(1.4f, 0.9f)
						.eyeHeight(0.65f)
						.passengerAttachments(0.765f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// rabbit = dimensions(0.4f, 0.5f).maxTrackingRange(8)
		PISTON_HOPPER = ModEntities.register("piston_hopper",
				EntityType.Builder.create(PistonHopperEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.4f, 0.5f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// vex = makeFireImmune().dimensions(0.4f, 0.8f).eyeHeight(0.51875f)
		// .passengerAttachments(0.7375f).vehicleAttachment(0.04f).maxTrackingRange(8)
		// .notAllowedInPeaceful()
		REDSTONE_SHADE = ModEntities.register("redstone_shade",
				EntityType.Builder.create(RedstoneShadeEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(0.4f, 0.8f)
						.eyeHeight(0.51875f)
						.passengerAttachments(0.7375f)
						.vehicleAttachment(0.04f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// phantom = dimensions(0.9f, 0.5f).eyeHeight(0.175f).passengerAttachments(0.3375f)
		// .vehicleAttachment(-0.125f).maxTrackingRange(8).notAllowedInPeaceful()
		WIRE_WRAITH = ModEntities.register("wire_wraith",
				EntityType.Builder.create(WireWraithEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.9f, 0.5f)
						.eyeHeight(0.175f)
						.passengerAttachments(0.3375f)
						.vehicleAttachment(-0.125f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// blaze = makeFireImmune().dimensions(0.6f, 1.8f).maxTrackingRange(8)
		// .notAllowedInPeaceful()
		BOILER_BLAZE = ModEntities.register("boiler_blaze",
				EntityType.Builder.create(BoilerBlazeEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(0.6f, 1.8f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		FURNACE_GOLEM = ModEntities.register("furnace_golem",
				EntityType.Builder.create(FurnaceGolemEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(1.4f, 2.7f)
						.maxTrackingRange(10)
						.notAllowedInPeaceful());
		// parrot = dimensions(0.5f, 0.9f).eyeHeight(0.54f).passengerAttachments(0.4625f)
		// .maxTrackingRange(8)
		SCRAP_VULTURE = ModEntities.register("scrap_vulture",
				EntityType.Builder.create(ScrapVultureEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.5f, 0.9f)
						.eyeHeight(0.54f)
						.passengerAttachments(0.4625f)
						.maxTrackingRange(8));
		// bee = dimensions(0.7f, 0.6f).eyeHeight(0.3f).maxTrackingRange(8)
		CLOCKWORK_BEE = ModEntities.register("clockwork_bee",
				EntityType.Builder.create(ClockworkBeeEntity::new, SpawnGroup.CREATURE)
						.dimensions(0.7f, 0.6f)
						.eyeHeight(0.3f)
						.maxTrackingRange(8));
		// ghast = makeFireImmune().dimensions(4.0f, 4.0f).eyeHeight(2.6f)
		// .passengerAttachments(4.0625f).vehicleAttachment(0.5f).maxTrackingRange(10)
		// .notAllowedInPeaceful()
		STEAM_GHAST = ModEntities.register("steam_ghast",
				EntityType.Builder.create(SteamGhastEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(4.0f, 4.0f)
						.eyeHeight(2.6f)
						.passengerAttachments(4.0625f)
						.vehicleAttachment(0.5f)
						.maxTrackingRange(10)
						.notAllowedInPeaceful());
		// cave_spider = dimensions(0.7f, 0.5f).eyeHeight(0.45f).maxTrackingRange(8)
		// .notAllowedInPeaceful()
		PIPE_SERPENT = ModEntities.register("pipe_serpent",
				EntityType.Builder.create(PipeSerpentEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.7f, 0.5f)
						.eyeHeight(0.45f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// zoglin = makeFireImmune().dimensions(1.3964844f, 1.4f).passengerAttachments(1.49375f)
		// .maxTrackingRange(8).notAllowedInPeaceful()
		GRINDER_ZOGLIN = ModEntities.register("grinder_zoglin",
				EntityType.Builder.create(GrinderZoglinEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(1.3964844f, 1.4f)
						.passengerAttachments(1.49375f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// husk = dimensions(0.6f, 1.95f).eyeHeight(1.74f).passengerAttachments(2.075f)
		// .vehicleAttachment(-0.7f).maxTrackingRange(8).notAllowedInPeaceful()
		PLATED_HUSK = ModEntities.register("plated_husk",
				EntityType.Builder.create(PlatedHuskEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.95f)
						.eyeHeight(1.74f)
						.passengerAttachments(2.075f)
						.vehicleAttachment(-0.7f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// stray = dimensions(0.6f, 1.99f).eyeHeight(1.74f).vehicleAttachment(-0.7f)
		// .allowSpawningInside(Blocks.POWDER_SNOW).maxTrackingRange(8).notAllowedInPeaceful()
		RIVETED_STRAY = ModEntities.register("riveted_stray",
				EntityType.Builder.create(RivetedStrayEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.99f)
						.eyeHeight(1.74f)
						.vehicleAttachment(-0.7f)
						.allowSpawningInside(Blocks.POWDER_SNOW)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// creeper = dimensions(0.6f, 1.7f).maxTrackingRange(8).notAllowedInPeaceful()
		TESLA_CREEPER = ModEntities.register("tesla_creeper",
				EntityType.Builder.create(TeslaCreeperEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.7f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// endermite = dimensions(0.4f, 0.3f).eyeHeight(0.13f).passengerAttachments(0.2375f)
		// .maxTrackingRange(8).notAllowedInPeaceful()
		MAGNET_MITE = ModEntities.register("magnet_mite",
				EntityType.Builder.create(MagnetMiteEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.4f, 0.3f)
						.eyeHeight(0.13f)
						.passengerAttachments(0.2375f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// witch = dimensions(0.6f, 1.95f).eyeHeight(1.62f).passengerAttachments(2.2625f)
		// .maxTrackingRange(8).notAllowedInPeaceful()
		CRUCIBLE_WITCH = ModEntities.register("crucible_witch",
				EntityType.Builder.create(CrucibleWitchEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.6f, 1.95f)
						.eyeHeight(1.62f)
						.passengerAttachments(2.2625f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// bat = dimensions(0.5f, 0.9f).eyeHeight(0.45f).maxTrackingRange(5)
		BELLOWS_BAT = ModEntities.register("bellows_bat",
				EntityType.Builder.create(BellowsBatEntity::new, SpawnGroup.AMBIENT)
						.dimensions(0.5f, 0.9f)
						.eyeHeight(0.45f)
						.maxTrackingRange(5));
		// snow_golem = allowSpawningInside(Blocks.POWDER_SNOW).dimensions(0.7f, 1.9f)
		// .eyeHeight(1.7f).maxTrackingRange(8)
		INGOT_GOLEM = ModEntities.register("ingot_golem",
				EntityType.Builder.create(IngotGolemEntity::new, SpawnGroup.CREATURE)
						.allowSpawningInside(Blocks.POWDER_SNOW)
						.dimensions(0.7f, 1.9f)
						.eyeHeight(1.7f)
						.maxTrackingRange(8));
		DOOM_MARAUDER = ModEntities.register("doom_marauder",
				EntityType.Builder.create(DoomMarauderEntity::new, SpawnGroup.MONSTER)
						.spawnableFarFromPlayer()
						.dimensions(0.6f, 1.95f)
						.passengerAttachments(2.0f)
						.vehicleAttachment(-0.6f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
	}

	private static void registerAttributes() {
		// MANDATORY per mob. Vanilla create*Attributes factories verified via javap (husk uses
		// ZombieEntity.createZombieAttributes, stray uses AbstractSkeletonEntity
		// .createAbstractSkeletonAttributes, phantom registers plain HostileEntity
		// .createHostileAttributes - all read from the vanilla DefaultAttributeRegistry
		// bytecode). Builders are FRESH per registration; EntityAttributes fields carry no
		// GENERIC_ prefix in 1.21.9.
		FabricDefaultAttributeRegistry.register(COPPER_SENTINEL, IronGolemEntity.createIronGolemAttributes());
		FabricDefaultAttributeRegistry.register(SLAG_CONSTRUCT, IronGolemEntity.createIronGolemAttributes()
				.add(EntityAttributes.MAX_HEALTH, 80.0));
		FabricDefaultAttributeRegistry.register(DOOM_ACOLYTE, VindicatorEntity.createVindicatorAttributes());
		FabricDefaultAttributeRegistry.register(FORGE_KEEPER, PillagerEntity.createPillagerAttributes());
		FabricDefaultAttributeRegistry.register(ANVIL_MIMIC, SilverfishEntity.createSilverfishAttributes()
				.add(EntityAttributes.SCALE, 1.5)
				.add(EntityAttributes.MAX_HEALTH, 16.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0));
		FabricDefaultAttributeRegistry.register(GEAR_SPIDER, SpiderEntity.createSpiderAttributes());
		// The piston hopper's melee AI (PistonHopperEntity.initGoals) requires ATTACK_DAMAGE,
		// which the vanilla rabbit attribute set does not guarantee.
		FabricDefaultAttributeRegistry.register(PISTON_HOPPER, RabbitEntity.createRabbitAttributes()
				.add(EntityAttributes.MAX_HEALTH, 12.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0));
		FabricDefaultAttributeRegistry.register(REDSTONE_SHADE, VexEntity.createVexAttributes());
		FabricDefaultAttributeRegistry.register(WIRE_WRAITH, HostileEntity.createHostileAttributes()
				.add(EntityAttributes.ATTACK_DAMAGE, 6.0));
		FabricDefaultAttributeRegistry.register(BOILER_BLAZE, BlazeEntity.createBlazeAttributes());
		FabricDefaultAttributeRegistry.register(FURNACE_GOLEM, IronGolemEntity.createIronGolemAttributes());
		FabricDefaultAttributeRegistry.register(SCRAP_VULTURE, ParrotEntity.createParrotAttributes());
		FabricDefaultAttributeRegistry.register(CLOCKWORK_BEE, BeeEntity.createBeeAttributes());
		FabricDefaultAttributeRegistry.register(STEAM_GHAST, GhastEntity.createGhastAttributes());
		FabricDefaultAttributeRegistry.register(PIPE_SERPENT, CaveSpiderEntity.createCaveSpiderAttributes());
		FabricDefaultAttributeRegistry.register(GRINDER_ZOGLIN, ZoglinEntity.createZoglinAttributes());
		FabricDefaultAttributeRegistry.register(PLATED_HUSK, ZombieEntity.createZombieAttributes());
		FabricDefaultAttributeRegistry.register(RIVETED_STRAY, AbstractSkeletonEntity.createAbstractSkeletonAttributes());
		FabricDefaultAttributeRegistry.register(TESLA_CREEPER, CreeperEntity.createCreeperAttributes());
		FabricDefaultAttributeRegistry.register(MAGNET_MITE, EndermiteEntity.createEndermiteAttributes());
		FabricDefaultAttributeRegistry.register(CRUCIBLE_WITCH, WitchEntity.createWitchAttributes());
		FabricDefaultAttributeRegistry.register(BELLOWS_BAT, BatEntity.createBatAttributes());
		FabricDefaultAttributeRegistry.register(INGOT_GOLEM, SnowGolemEntity.createSnowGolemAttributes());
		FabricDefaultAttributeRegistry.register(DOOM_MARAUDER, PillagerEntity.createPillagerAttributes());
	}

	private static void registerItems() {
		// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the vanilla
		// SpawnEggItem reads its entity type from in 1.21.9 (proven pattern: dr_pepper_golem,
		// infernomobs). Settings are FRESH per registration.
		COPPER_SENTINEL_SPAWN_EGG = ModItems.register("copper_sentinel_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(COPPER_SENTINEL));
		SLAG_CONSTRUCT_SPAWN_EGG = ModItems.register("slag_construct_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SLAG_CONSTRUCT));
		DOOM_ACOLYTE_SPAWN_EGG = ModItems.register("doom_acolyte_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(DOOM_ACOLYTE));
		FORGE_KEEPER_SPAWN_EGG = ModItems.register("forge_keeper_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(FORGE_KEEPER));
		ANVIL_MIMIC_SPAWN_EGG = ModItems.register("anvil_mimic_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(ANVIL_MIMIC));
		GEAR_SPIDER_SPAWN_EGG = ModItems.register("gear_spider_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(GEAR_SPIDER));
		PISTON_HOPPER_SPAWN_EGG = ModItems.register("piston_hopper_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(PISTON_HOPPER));
		REDSTONE_SHADE_SPAWN_EGG = ModItems.register("redstone_shade_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(REDSTONE_SHADE));
		WIRE_WRAITH_SPAWN_EGG = ModItems.register("wire_wraith_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(WIRE_WRAITH));
		BOILER_BLAZE_SPAWN_EGG = ModItems.register("boiler_blaze_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(BOILER_BLAZE));
		FURNACE_GOLEM_SPAWN_EGG = ModItems.register("furnace_golem_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(FURNACE_GOLEM));
		SCRAP_VULTURE_SPAWN_EGG = ModItems.register("scrap_vulture_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SCRAP_VULTURE));
		CLOCKWORK_BEE_SPAWN_EGG = ModItems.register("clockwork_bee_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CLOCKWORK_BEE));
		STEAM_GHAST_SPAWN_EGG = ModItems.register("steam_ghast_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(STEAM_GHAST));
		PIPE_SERPENT_SPAWN_EGG = ModItems.register("pipe_serpent_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(PIPE_SERPENT));
		GRINDER_ZOGLIN_SPAWN_EGG = ModItems.register("grinder_zoglin_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(GRINDER_ZOGLIN));
		PLATED_HUSK_SPAWN_EGG = ModItems.register("plated_husk_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(PLATED_HUSK));
		RIVETED_STRAY_SPAWN_EGG = ModItems.register("riveted_stray_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(RIVETED_STRAY));
		TESLA_CREEPER_SPAWN_EGG = ModItems.register("tesla_creeper_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(TESLA_CREEPER));
		MAGNET_MITE_SPAWN_EGG = ModItems.register("magnet_mite_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(MAGNET_MITE));
		CRUCIBLE_WITCH_SPAWN_EGG = ModItems.register("crucible_witch_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CRUCIBLE_WITCH));
		BELLOWS_BAT_SPAWN_EGG = ModItems.register("bellows_bat_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(BELLOWS_BAT));
		INGOT_GOLEM_SPAWN_EGG = ModItems.register("ingot_golem_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(INGOT_GOLEM));
		DOOM_MARAUDER_SPAWN_EGG = ModItems.register("doom_marauder_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(DOOM_MARAUDER));

		SENTINEL_PLATING = ModItems.register("sentinel_plating", Item::new, new Item.Settings());
		SLAG_GRIT = ModItems.register("slag_grit", Item::new, new Item.Settings());
		DOOM_EMBLEM = ModItems.register("doom_emblem", Item::new, new Item.Settings());
		FORGE_BELLOWS = ModItems.register("forge_bellows", Item::new, new Item.Settings());
		ANVIL_SHARD = ModItems.register("anvil_shard", Item::new, new Item.Settings());
		COPPER_GEARWHEEL = ModItems.register("copper_gearwheel", Item::new, new Item.Settings());
		PISTON_SPRING = ModItems.register("piston_spring", Item::new, new Item.Settings());
		REDSTONE_FILAMENT = ModItems.register("redstone_filament", Item::new, new Item.Settings());
		BOILER_PLATE = ModItems.register("boiler_plate", Item::new, new Item.Settings());
		RIVET_BOLT = ModItems.register("rivet_bolt", Item::new, new Item.Settings());
		MAGNETITE_SHARD = ModItems.register("magnetite_shard", Item::new, new Item.Settings());
		CRUCIBLE_DROSS = ModItems.register("crucible_dross", Item::new, new Item.Settings());

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.FAUNA_KEY).register(entries -> {
			// Spawn eggs in roster order, then the mob drops.
			entries.add(COPPER_SENTINEL_SPAWN_EGG);
			entries.add(SLAG_CONSTRUCT_SPAWN_EGG);
			entries.add(DOOM_ACOLYTE_SPAWN_EGG);
			entries.add(FORGE_KEEPER_SPAWN_EGG);
			entries.add(ANVIL_MIMIC_SPAWN_EGG);
			entries.add(GEAR_SPIDER_SPAWN_EGG);
			entries.add(PISTON_HOPPER_SPAWN_EGG);
			entries.add(REDSTONE_SHADE_SPAWN_EGG);
			entries.add(WIRE_WRAITH_SPAWN_EGG);
			entries.add(BOILER_BLAZE_SPAWN_EGG);
			entries.add(FURNACE_GOLEM_SPAWN_EGG);
			entries.add(SCRAP_VULTURE_SPAWN_EGG);
			entries.add(CLOCKWORK_BEE_SPAWN_EGG);
			entries.add(STEAM_GHAST_SPAWN_EGG);
			entries.add(PIPE_SERPENT_SPAWN_EGG);
			entries.add(GRINDER_ZOGLIN_SPAWN_EGG);
			entries.add(PLATED_HUSK_SPAWN_EGG);
			entries.add(RIVETED_STRAY_SPAWN_EGG);
			entries.add(TESLA_CREEPER_SPAWN_EGG);
			entries.add(MAGNET_MITE_SPAWN_EGG);
			entries.add(CRUCIBLE_WITCH_SPAWN_EGG);
			entries.add(BELLOWS_BAT_SPAWN_EGG);
			entries.add(INGOT_GOLEM_SPAWN_EGG);
			entries.add(DOOM_MARAUDER_SPAWN_EGG);
			entries.add(SENTINEL_PLATING);
			entries.add(SLAG_GRIT);
			entries.add(DOOM_EMBLEM);
			entries.add(FORGE_BELLOWS);
			entries.add(ANVIL_SHARD);
			entries.add(COPPER_GEARWHEEL);
			entries.add(PISTON_SPRING);
			entries.add(REDSTONE_FILAMENT);
			entries.add(BOILER_PLATE);
			entries.add(RIVET_BOLT);
			entries.add(MAGNETITE_SHARD);
			entries.add(CRUCIBLE_DROSS);
		});
	}

	private static void registerSpawning() {
		// SpawnRestriction.register is private in vanilla but access-widened by Fabric's
		// transitive access wideners (proven pattern: infernomobs). Locations/heightmaps
		// mirror the vanilla base-mob entries (SpawnRestriction bytecode: all bases ON_GROUND
		// + MOTION_BLOCKING_NO_LEAVES except parrot's MOTION_BLOCKING and the flyers
		// vex/phantom which are UNRESTRICTED; vindicator/pillager are vanilla-UNRESTRICTED
		// only because they spawn from raids - these constructs spawn naturally, so they get
		// ON_GROUND). Predicates: HostileEntity::canSpawnInDark / canSpawnIgnoreLightLevel
		// where the base IS a HostileEntity; the golem/rabbit/phantom/ghast/bat/snow-golem
		// based constructs use the equivalents below, reimplemented 1:1 from the HostileEntity
		// bytecode because the vanilla methods are typed to HostileEntity subtypes.
		SpawnRestriction.register(COPPER_SENTINEL, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ConstructsFeature::canConstructSpawnInDark);
		SpawnRestriction.register(SLAG_CONSTRUCT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ConstructsFeature::canConstructSpawnIgnoreLight);
		SpawnRestriction.register(DOOM_ACOLYTE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(FORGE_KEEPER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
		SpawnRestriction.register(ANVIL_MIMIC, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
		SpawnRestriction.register(GEAR_SPIDER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(PISTON_HOPPER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ConstructsFeature::canConstructSpawnInDark);
		SpawnRestriction.register(REDSTONE_SHADE, SpawnLocationTypes.UNRESTRICTED,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(WIRE_WRAITH, SpawnLocationTypes.UNRESTRICTED,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ConstructsFeature::canConstructSpawnIgnoreLight);
		SpawnRestriction.register(BOILER_BLAZE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
		SpawnRestriction.register(FURNACE_GOLEM, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ConstructsFeature::canConstructSpawnIgnoreLight);
		SpawnRestriction.register(SCRAP_VULTURE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING, MobEntity::canMobSpawn);
		SpawnRestriction.register(CLOCKWORK_BEE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
		SpawnRestriction.register(STEAM_GHAST, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ConstructsFeature::canConstructSpawnIgnoreLight);
		SpawnRestriction.register(PIPE_SERPENT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(GRINDER_ZOGLIN, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
		SpawnRestriction.register(PLATED_HUSK, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(RIVETED_STRAY, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(TESLA_CREEPER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		SpawnRestriction.register(MAGNET_MITE, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
		SpawnRestriction.register(CRUCIBLE_WITCH, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
		SpawnRestriction.register(BELLOWS_BAT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
		SpawnRestriction.register(INGOT_GOLEM, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
		SpawnRestriction.register(DOOM_MARAUDER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);

		// Natural spawns: 12 Overworld constructs + 12 Inferno constructs. The Inferno biome
		// JSONs are owned by the infernodim feature; includeByKey matches nothing until they
		// are loaded. addSpawn signature verified via javap: (Predicate<BiomeSelectionContext>,
		// SpawnGroup, EntityType, weight, minGroup, maxGroup).
		Predicate<BiomeSelectionContext> overworld = BiomeSelectors.foundInOverworld();
		Predicate<BiomeSelectionContext> infernoBiomes = BiomeSelectors.includeByKey(
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("cinder_wastes")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("ember_grove")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("slag_sea")));

		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, COPPER_SENTINEL, 8, 1, 1);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, DOOM_ACOLYTE, 10, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, GEAR_SPIDER, 15, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, PISTON_HOPPER, 12, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, REDSTONE_SHADE, 6, 1, 1);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, SCRAP_VULTURE, 6, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, CLOCKWORK_BEE, 5, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, PIPE_SERPENT, 12, 1, 3);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, PLATED_HUSK, 12, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, RIVETED_STRAY, 10, 1, 2);
		BiomeModifications.addSpawn(overworld, SpawnGroup.MONSTER, TESLA_CREEPER, 10, 1, 1);
		BiomeModifications.addSpawn(overworld, SpawnGroup.CREATURE, INGOT_GOLEM, 4, 1, 1);

		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, SLAG_CONSTRUCT, 10, 1, 1);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, FORGE_KEEPER, 12, 1, 2);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, ANVIL_MIMIC, 20, 1, 3);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, WIRE_WRAITH, 8, 1, 2);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, BOILER_BLAZE, 12, 1, 2);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, FURNACE_GOLEM, 8, 1, 1);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, STEAM_GHAST, 4, 1, 1);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, GRINDER_ZOGLIN, 8, 1, 1);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, MAGNET_MITE, 15, 1, 3);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, CRUCIBLE_WITCH, 6, 1, 1);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.AMBIENT, BELLOWS_BAT, 12, 2, 4);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, DOOM_MARAUDER, 10, 1, 2);
	}

	/**
	 * Mirrors vanilla {@code HostileEntity.canSpawnInDark} (1.21.9 bytecode: difficulty !=
	 * PEACEFUL &amp;&amp; (trial-spawner OR dark) &amp;&amp; canMobSpawn) for constructs whose
	 * vanilla base is NOT a {@code HostileEntity} (iron/snow golems, rabbit, phantom, ghast),
	 * since the vanilla method is typed to {@code EntityType<? extends HostileEntity>}.
	 */
	private static boolean canConstructSpawnInDark(EntityType<? extends MobEntity> type,
			ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL
				&& (SpawnReason.isTrialSpawner(reason) || HostileEntity.isSpawnDark(world, pos, random))
				&& MobEntity.canMobSpawn(type, world, reason, pos, random);
	}

	/**
	 * Mirrors vanilla {@code HostileEntity.canSpawnIgnoreLightLevel} (1.21.9 bytecode:
	 * difficulty != PEACEFUL &amp;&amp; canMobSpawn) for the non-HostileEntity-based Inferno
	 * constructs - the Inferno has no skylight, so the light gate is dropped exactly like the
	 * vanilla nether hostiles drop it.
	 */
	private static boolean canConstructSpawnIgnoreLight(EntityType<? extends MobEntity> type,
			ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL
				&& MobEntity.canMobSpawn(type, world, reason, pos, random);
	}

	private static void registerHandbookEntries() {
		HandbookEntries.add(new HandbookEntry("mobs", "copper_sentinel",
				"copper_inferno:copper_sentinel_spawn_egg", null, null, null, 0,
				"Copper Sentinel - a rogue copper-plated golem that hunts players through the Overworld night. Drops Sentinel Plating.",
				"Kupferwächter - ein abtrünniger kupferbeschlagener Golem, der nachts in der Oberwelt Spieler jagt. Lässt Wächterplattierung fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "slag_construct",
				"copper_inferno:slag_construct_spawn_egg", null, null, null, 0,
				"Slag Construct - a golem welded from glowing Inferno slag whose punches set you ablaze. Drops Slag Grit.",
				"Schlackenkonstrukt - ein aus glühender Inferno-Schlacke geschweißter Golem, dessen Schläge dich entzünden. Lässt Schlackensplitt fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "doom_acolyte",
				"copper_inferno:doom_acolyte_spawn_egg", null, null, null, 0,
				"Doom Acolyte - a vindicator of the DOOM rites whose axe carries a withering curse. Drops Doom Emblems.",
				"DOOM-Akolyth - ein Diener der DOOM-Riten, dessen Axt einen zehrenden Fluch trägt. Lässt DOOM-Embleme fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "forge_keeper",
				"copper_inferno:forge_keeper_spawn_egg", null, null, null, 0,
				"Forge Keeper - a crossbow pillager that tends the Inferno's forges and never, ever despawns. Drops Forge Bellows.",
				"Schmiedewart - ein Armbrust-Plünderer, der die Schmieden des Infernos hütet und niemals despawnt. Lässt Schmiedeblasebälge fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "anvil_mimic",
				"copper_inferno:anvil_mimic_spawn_egg", null, null, null, 0,
				"Anvil Mimic - a squat pig-iron silverfish far too heavy to be knocked back. Drops Anvil Shards.",
				"Amboss-Mimik - ein gedrungener Roheisen-Silberfisch, viel zu schwer für jeden Rückstoß. Lässt Amboss-Splitter fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "gear_spider",
				"copper_inferno:gear_spider_spawn_egg", null, null, null, 0,
				"Gear Spider - a spider rebuilt on whirring copper gears; its legs cannot be slowed. Drops Copper Gearwheels.",
				"Zahnradspinne - eine auf surrenden Kupferzahnrädern neu gebaute Spinne; ihre Beine lassen sich nicht verlangsamen. Lässt Kupferzahnräder fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "piston_hopper",
				"copper_inferno:piston_hopper_spawn_egg", null, null, null, 0,
				"Piston Hopper - a rabbit on a piston chassis that rams players like the killer bunny of legend. Drops Piston Springs.",
				"Kolbenhoppler - ein Kaninchen auf Kolbenfahrwerk, das Spieler rammt wie das legendäre Killerkaninchen. Lässt Kolbenfedern fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "redstone_shade",
				"copper_inferno:redstone_shade_spawn_egg", null, null, null, 0,
				"Redstone Shade - a vex-like spirit of loose redstone charge, permanently crackling with a visible glow. Drops Redstone Filaments.",
				"Redstone-Schemen - ein Plagegeist aus loser Redstone-Ladung, der dauerhaft sichtbar glimmt. Lässt Redstone-Glühfäden fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "wire_wraith",
				"copper_inferno:wire_wraith_spawn_egg", null, null, null, 0,
				"Wire Wraith - a phantom of tangled live wire over the Inferno; its touch marks victims with a glowing short-circuit. Drops Redstone Filaments.",
				"Drahtgespenst - ein Phantom aus verhedderten stromführenden Drähten über dem Inferno; seine Berührung markiert Opfer mit einem leuchtenden Kurzschluss. Lässt Redstone-Glühfäden fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "boiler_blaze",
				"copper_inferno:boiler_blaze_spawn_egg", null, null, null, 0,
				"Boiler Blaze - a blaze sealed in a riveted, watertight boiler shell; water no longer hurts it. Drops Boiler Plates.",
				"Kessellohe - eine in einen vernieteten, wasserdichten Kessel eingeschlossene Lohe; Wasser schadet ihr nicht mehr. Lässt Kesselplatten fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "furnace_golem",
				"copper_inferno:furnace_golem_spawn_egg", null, null, null, 0,
				"Furnace Golem - a walking blast furnace; strike it and its white-hot casing burns you back. Drops Sentinel Plating.",
				"Ofengolem - ein wandelnder Hochofen; wer ihn schlägt, verbrennt sich am weißglühenden Gehäuse. Lässt Wächterplattierung fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "scrap_vulture",
				"copper_inferno:scrap_vulture_spawn_egg", null, null, null, 0,
				"Scrap Vulture - a scavenger bird riveted from scrapyard offcuts; completely untameable. Drops raw copper.",
				"Schrottgeier - ein aus Schrottplatzresten vernieteter Aasvogel; völlig unzähmbar. Lässt Rohkupfer fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "clockwork_bee",
				"copper_inferno:clockwork_bee_spawn_egg", null, null, null, 0,
				"Clockwork Bee - a wind-up brass bee; a machine has no blood to poison. Drops Copper Gearwheels.",
				"Uhrwerkbiene - eine aufziehbare Messingbiene; eine Maschine hat kein Blut zum Vergiften. Lässt Kupferzahnräder fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "steam_ghast",
				"copper_inferno:steam_ghast_spawn_egg", null, null, null, 0,
				"Steam Ghast - a ghast-shaped cloud of scalding boiler steam; water condenses (and hurts) it. Drops Boiler Plates.",
				"Dampfghast - eine ghastförmige Wolke aus brühendem Kesseldampf; Wasser kondensiert (und verletzt) ihn. Lässt Kesselplatten fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "pipe_serpent",
				"copper_inferno:pipe_serpent_spawn_egg", null, null, null, 0,
				"Pipe Serpent - a cave spider slithering through Overworld pipework; its crushing coils also slow you. Drops string.",
				"Rohrschlange - eine Höhlenspinne, die durch das Rohrwerk der Oberwelt gleitet; ihre Würgeschlingen verlangsamen zusätzlich. Lässt Fäden fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "grinder_zoglin",
				"copper_inferno:grinder_zoglin_spawn_egg", null, null, null, 0,
				"Grinder Zoglin - a zoglin with grinding drums for tusks; grinding it down pays triple experience. Drops leather.",
				"Mahlwerk-Zoglin - ein Zoglin mit Mahltrommeln statt Hauern; ihn niederzuringen zahlt dreifache Erfahrung. Lässt Leder fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "plated_husk",
				"copper_inferno:plated_husk_spawn_egg", null, null, null, 0,
				"Plated Husk - a husk bolted into boiler plate that shrugs off half of all projectile damage. Drops Rivet Bolts.",
				"Plattierter Wüstenzombie - ein in Kesselblech verschraubter Wüstenzombie, der die Hälfte allen Geschossschadens abschüttelt. Lässt Nietbolzen fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "riveted_stray",
				"copper_inferno:riveted_stray_spawn_egg", null, null, null, 0,
				"Riveted Stray - a stray banded in cold-forged steel; powder snow cannot freeze it. Drops Rivet Bolts.",
				"Vernieteter Eiswanderer - ein mit kaltgeschmiedeten Stahlbändern beschlagener Eiswanderer; Pulverschnee kann ihn nicht einfrieren. Lässt Nietbolzen fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "tesla_creeper",
				"copper_inferno:tesla_creeper_spawn_egg", null, null, null, 0,
				"Tesla Creeper - a creeper wound around an overcharged capacitor; ANY hit primes its fuse. Drops Redstone Filaments.",
				"Tesla-Creeper - ein um einen überladenen Kondensator gewickelter Creeper; JEDER Treffer zündet seine Lunte. Lässt Redstone-Glühfäden fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "magnet_mite",
				"copper_inferno:magnet_mite_spawn_egg", null, null, null, 0,
				"Magnet Mite - an endermite with a magnetite core that latches onto any surface and takes no fall damage. Drops Magnetite Shards.",
				"Magnetmilbe - eine Endermite mit Magnetitkern, die sich an jeder Oberfläche festklammert und keinen Fallschaden nimmt. Lässt Magnetitscherben fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "crucible_witch",
				"copper_inferno:crucible_witch_spawn_egg", null, null, null, 0,
				"Crucible Witch - a witch who stirs molten metal instead of potions; a lifetime at the crucible made her fireproof. Drops Crucible Dross.",
				"Tiegelhexe - eine Hexe, die statt Tränken geschmolzenes Metall rührt; ein Leben am Tiegel machte sie feuerfest. Lässt Tiegelkrätze fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "bellows_bat",
				"copper_inferno:bellows_bat_spawn_egg", null, null, null, 0,
				"Bellows Bat - a leather-winged bellows that feeds the Inferno's forges and never stops to roost. Drops Forge Bellows.",
				"Blasebalg-Fledermaus - ein lederbeschwingter Blasebalg, der die Schmieden des Infernos speist und nie zum Hängen kommt. Lässt Schmiedeblasebälge fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "ingot_golem",
				"copper_inferno:ingot_golem_spawn_egg", null, null, null, 0,
				"Ingot Golem - a snow-golem-shaped stack of cast ingots that never melts in rain or water. Drops iron nuggets.",
				"Barrengolem - ein schneegolemförmiger Stapel gegossener Barren, der in Regen und Wasser niemals schmilzt. Lässt Eisenklumpen fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "doom_marauder",
				"copper_inferno:doom_marauder_spawn_egg", null, null, null, 0,
				"Doom Marauder - a pillager locked in a DOOM-fueled frenzy, permanently boosted with Speed. Drops Doom Emblems.",
				"DOOM-Marodeur - ein Plünderer in DOOM-befeuerter Raserei, dauerhaft mit Schnelligkeit verstärkt. Lässt DOOM-Embleme fallen."));
	}
}
