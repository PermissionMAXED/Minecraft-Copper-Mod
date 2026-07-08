package net.sonic0810.copperinferno.feature.infernomobs;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.function.Predicate;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModDimensions;
import net.sonic0810.copperinferno.core.ModEntities;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Hostile and ambient mobs native to the Inferno dimension: 3 hostile (Ember Wraith, Slag
 * Crawler, Molten Slagling), 2 passive (Cinder Strider, Ash Bat). Each extends a vanilla entity
 * and reuses the vanilla renderer (see {@code InfernoMobsFeatureClient}); EntityType
 * dimensions/eye heights are copied from the vanilla {@code EntityType} registrations (verified
 * via bytecode). Spawn eggs + mob-drop items, natural spawns in the Inferno biomes, and drop
 * recipes are all registered here.
 */
public final class InfernoMobsFeature {
	private InfernoMobsFeature() {
	}

	public static EntityType<EmberWraithEntity> EMBER_WRAITH;
	public static EntityType<SlagCrawlerEntity> SLAG_CRAWLER;
	public static EntityType<MoltenSlaglingEntity> MOLTEN_SLAGLING;
	public static EntityType<CinderStriderEntity> CINDER_STRIDER;
	public static EntityType<AshBatEntity> ASH_BAT;

	public static Item EMBER_WRAITH_SPAWN_EGG;
	public static Item SLAG_CRAWLER_SPAWN_EGG;
	public static Item MOLTEN_SLAGLING_SPAWN_EGG;
	public static Item CINDER_STRIDER_SPAWN_EGG;
	public static Item ASH_BAT_SPAWN_EGG;

	public static Item WRAITH_EMBER;
	public static Item CRAWLER_FANG;
	public static Item SLAGLING_CORE;
	public static Item STRIDER_SHELL;

	public static void init() {
		registerEntityTypes();
		registerAttributes();
		registerItems();
		registerSpawning();
		registerHandbookEntries();
	}

	private static void registerEntityTypes() {
		// Dimensions/eye heights/tracking ranges copied from the vanilla EntityType
		// registrations (bytecode: blaze = makeFireImmune().dimensions(0.6f, 1.8f)
		// .maxTrackingRange(8).notAllowedInPeaceful()).
		EMBER_WRAITH = ModEntities.register("ember_wraith",
				EntityType.Builder.create(EmberWraithEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(0.6f, 1.8f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// silverfish = dimensions(0.4f, 0.3f).eyeHeight(0.13f).passengerAttachments(0.2375f)
		// .maxTrackingRange(8).notAllowedInPeaceful().
		SLAG_CRAWLER = ModEntities.register("slag_crawler",
				EntityType.Builder.create(SlagCrawlerEntity::new, SpawnGroup.MONSTER)
						.dimensions(0.4f, 0.3f)
						.eyeHeight(0.13f)
						.passengerAttachments(0.2375f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// magma_cube = makeFireImmune().dimensions(0.52f, 0.52f).eyeHeight(0.325f)
		// .spawnBoxScale(4.0f).maxTrackingRange(8).notAllowedInPeaceful(). The size handling
		// (random size on natural spawn, health/dimension rescale, split on death) is inherited
		// from SlimeEntity/MagmaCubeEntity.
		MOLTEN_SLAGLING = ModEntities.register("molten_slagling",
				EntityType.Builder.create(MoltenSlaglingEntity::new, SpawnGroup.MONSTER)
						.makeFireImmune()
						.dimensions(0.52f, 0.52f)
						.eyeHeight(0.325f)
						.spawnBoxScale(4.0f)
						.maxTrackingRange(8)
						.notAllowedInPeaceful());
		// strider = makeFireImmune().dimensions(0.9f, 1.7f).maxTrackingRange(10); lava walking
		// is built into StriderEntity.
		CINDER_STRIDER = ModEntities.register("cinder_strider",
				EntityType.Builder.create(CinderStriderEntity::new, SpawnGroup.CREATURE)
						.makeFireImmune()
						.dimensions(0.9f, 1.7f)
						.maxTrackingRange(10));
		// bat = dimensions(0.5f, 0.9f).eyeHeight(0.45f).maxTrackingRange(5).
		ASH_BAT = ModEntities.register("ash_bat",
				EntityType.Builder.create(AshBatEntity::new, SpawnGroup.AMBIENT)
						.dimensions(0.5f, 0.9f)
						.eyeHeight(0.45f)
						.maxTrackingRange(5));
	}

	private static void registerAttributes() {
		// EntityAttributes fields have no GENERIC_ prefix in 1.21.9 (verified via javap);
		// DefaultAttributeContainer.Builder.add(...) overrides any base value.
		FabricDefaultAttributeRegistry.register(EMBER_WRAITH, BlazeEntity.createBlazeAttributes()
				.add(EntityAttributes.SCALE, 1.15)
				.add(EntityAttributes.MAX_HEALTH, 24.0));
		FabricDefaultAttributeRegistry.register(SLAG_CRAWLER, SilverfishEntity.createSilverfishAttributes()
				.add(EntityAttributes.SCALE, 1.4)
				.add(EntityAttributes.ATTACK_DAMAGE, 4.0));
		FabricDefaultAttributeRegistry.register(MOLTEN_SLAGLING, MagmaCubeEntity.createMagmaCubeAttributes());
		FabricDefaultAttributeRegistry.register(CINDER_STRIDER, StriderEntity.createStriderAttributes());
		FabricDefaultAttributeRegistry.register(ASH_BAT, BatEntity.createBatAttributes());
	}

	private static void registerItems() {
		// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the vanilla SpawnEggItem
		// reads its entity type from in 1.21.9 (same proven pattern as dr_pepper_golem).
		EMBER_WRAITH_SPAWN_EGG = ModItems.register("ember_wraith_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(EMBER_WRAITH));
		SLAG_CRAWLER_SPAWN_EGG = ModItems.register("slag_crawler_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SLAG_CRAWLER));
		MOLTEN_SLAGLING_SPAWN_EGG = ModItems.register("molten_slagling_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(MOLTEN_SLAGLING));
		CINDER_STRIDER_SPAWN_EGG = ModItems.register("cinder_strider_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CINDER_STRIDER));
		ASH_BAT_SPAWN_EGG = ModItems.register("ash_bat_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(ASH_BAT));

		WRAITH_EMBER = ModItems.register("wraith_ember", Item::new, new Item.Settings());
		CRAWLER_FANG = ModItems.register("crawler_fang", Item::new, new Item.Settings());
		SLAGLING_CORE = ModItems.register("slagling_core", Item::new, new Item.Settings());
		STRIDER_SHELL = ModItems.register("strider_shell", Item::new, new Item.Settings());

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MOBS_KEY).register(entries -> {
			// Spawn eggs together, then the mob drops.
			entries.add(EMBER_WRAITH_SPAWN_EGG);
			entries.add(SLAG_CRAWLER_SPAWN_EGG);
			entries.add(MOLTEN_SLAGLING_SPAWN_EGG);
			entries.add(CINDER_STRIDER_SPAWN_EGG);
			entries.add(ASH_BAT_SPAWN_EGG);
			entries.add(WRAITH_EMBER);
			entries.add(CRAWLER_FANG);
			entries.add(SLAGLING_CORE);
			entries.add(STRIDER_SHELL);
		});
	}

	private static void registerSpawning() {
		// SpawnRestriction.register(EntityType, SpawnLocation, Heightmap.Type, SpawnPredicate) is
		// private in vanilla but access-widened by Fabric's transitive access wideners (verified
		// in fabric-transitive-access-wideners-v1). Locations/heightmaps/predicates mirror the
		// vanilla entries (SpawnRestriction bytecode): blaze uses ON_GROUND +
		// MOTION_BLOCKING_NO_LEAVES + HostileEntity::canSpawnIgnoreLightLevel (nether hostiles
		// ignore light), magma_cube's canMagmaCubeSpawn is only a difficulty != PEACEFUL check
		// (inlined below because the vanilla method is typed to EntityType<MagmaCubeEntity>),
		// strider uses IN_LAVA. The passives' vanilla canSpawn predicates are likewise typed to
		// the vanilla EntityTypes, so they are reimplemented 1:1 from the 1.21.9 bytecode in
		// canCinderStriderSpawn / canAshBatSpawn below.
		SpawnRestriction.register(EMBER_WRAITH, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
		SpawnRestriction.register(SLAG_CRAWLER, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
		SpawnRestriction.register(MOLTEN_SLAGLING, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(type, world, reason, pos, random) -> world.getDifficulty() != Difficulty.PEACEFUL);
		SpawnRestriction.register(CINDER_STRIDER, SpawnLocationTypes.IN_LAVA,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				InfernoMobsFeature::canCinderStriderSpawn);
		SpawnRestriction.register(ASH_BAT, SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				InfernoMobsFeature::canAshBatSpawn);

		// Natural spawns in the Inferno biomes (biome JSONs are owned by the infernodim and
		// infernodim2 features; includeByKey simply matches nothing until they are loaded).
		// verdigris_jungle and soot_dunes joined with the infernodim2 expansion; molten_delta
		// and crystal_hollows keep their own curated rosters. addSpawn signature verified via
		// javap: (Predicate<BiomeSelectionContext>, SpawnGroup, EntityType, weight, minGroup,
		// maxGroup).
		Predicate<BiomeSelectionContext> infernoBiomes = BiomeSelectors.includeByKey(
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("cinder_wastes")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("ember_grove")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("slag_sea")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("verdigris_jungle")),
				RegistryKey.of(RegistryKeys.BIOME, CopperInferno.id("soot_dunes")));
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, EMBER_WRAITH, 15, 1, 3);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, SLAG_CRAWLER, 30, 1, 4);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, MOLTEN_SLAGLING, 25, 2, 4);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.CREATURE, CINDER_STRIDER, 30, 1, 2);
		BiomeModifications.addSpawn(infernoBiomes, SpawnGroup.AMBIENT, ASH_BAT, 10, 2, 4);
		// Ash bats also flit around the Overworld, rarely; canAshBatSpawn night/cave-gates them
		// exactly like vanilla bats, so this entry does not flood daylight surfaces.
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.AMBIENT, ASH_BAT, 3, 1, 2);
	}

	/**
	 * Mirrors vanilla {@code StriderEntity.canSpawn} (verified against the 1.21.9 bytecode; the
	 * vanilla method is typed to {@code EntityType<StriderEntity>} so it cannot be reused
	 * directly): walk upward through the lava column above the spawn pos, then require the first
	 * non-lava block to be air.
	 */
	private static boolean canCinderStriderSpawn(EntityType<CinderStriderEntity> type, ServerWorldAccess world,
			SpawnReason reason, BlockPos pos, Random random) {
		BlockPos.Mutable mutable = pos.mutableCopy();
		do {
			mutable.move(Direction.UP);
		} while (world.getFluidState(mutable).isIn(FluidTags.LAVA));
		return world.getBlockState(mutable).isAir();
	}

	/**
	 * Mirrors vanilla {@code BatEntity.canSpawn} (verified against the 1.21.9 bytecode; the
	 * vanilla method is typed to {@code EntityType<BatEntity>} so it cannot be reused directly):
	 * below the WORLD_SURFACE heightmap (i.e. caves — never in open daylight), a light gate of
	 * {@code light <= random.nextInt(4)} (relaxed to 7 around Halloween, otherwise a 50% cull),
	 * a BATS_SPAWNABLE_ON block below, and the base {@code MobEntity.canMobSpawn} checks.
	 *
	 * <p>In the Inferno the surface/light gates are satisfied anyway (ceiling, no skylight), but
	 * the BATS_SPAWNABLE_ON gate would starve spawns entirely — the ground there is
	 * cinderstone/ash/ember soil/basalt/blackstone, none of which are in the tag (it only
	 * contains {@code #minecraft:base_stone_overworld}) — so the Inferno branch keeps just the
	 * base mob checks.
	 */
	private static boolean canAshBatSpawn(EntityType<AshBatEntity> type, ServerWorldAccess world,
			SpawnReason reason, BlockPos pos, Random random) {
		if (world.toServerWorld().getRegistryKey() == ModDimensions.INFERNO_WORLD) {
			return MobEntity.canMobSpawn(type, world, reason, pos, random);
		}
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
		HandbookEntries.add(new HandbookEntry("mobs", "ember_wraith",
				"copper_inferno:ember_wraith_spawn_egg", null, null, null, 0,
				"Ember Wraith - a towering blaze spirit haunting the Cinder Wastes, Ember Grove and Slag Sea. Tougher and larger than a common blaze. Drops Wraith Embers.",
				"Glutschleier - ein riesiger Lohengeist, der die Zunderöde, den Gluthain und das Schlackenmeer heimsucht. Zäher und größer als eine gewöhnliche Lohe. Lässt Schleierglut fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "slag_crawler",
				"copper_inferno:slag_crawler_spawn_egg", null, null, null, 0,
				"Slag Crawler - an oversized silverfish crusted in slag, skittering across the Inferno biomes. Bites hard. Drops Crawler Fangs.",
				"Schlackenkriecher - ein übergroßer, schlackenverkrusteter Silberfisch, der durch die Inferno-Biome huscht. Beißt kräftig zu. Lässt Kriecherzähne fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "molten_slagling",
				"copper_inferno:molten_slagling_spawn_egg", null, null, null, 0,
				"Molten Slagling - a bouncing cube of molten slag found throughout the Inferno biomes. Splits like a magma cube when slain. Drops Slagling Cores.",
				"Schmelzschlackling - ein hüpfender Würfel aus geschmolzener Schlacke, überall in den Inferno-Biomen zu finden. Teilt sich wie ein Magmawürfel. Lässt Schlackling-Kerne fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "cinder_strider",
				"copper_inferno:cinder_strider_spawn_egg", null, null, null, 0,
				"Cinder Strider - a placid, cinder-crusted strider that wades the lava of the Slag Sea and its neighbors. Can be saddled and ridden. Drops Strider Shells.",
				"Zunderschreiter - ein friedlicher, zunderverkrusteter Schreiter, der durch die Lava des Schlackenmeers watet. Kann gesattelt und geritten werden. Lässt Schreiterpanzer fallen."));
		HandbookEntries.add(new HandbookEntry("mobs", "ash_bat",
				"copper_inferno:ash_bat_spawn_egg", null, null, null, 0,
				"Ash Bat - an ash-grey bat roosting in the Inferno biomes, with a few straying into the Overworld. Harmless. Drops leather.",
				"Aschenfledermaus - eine aschgraue Fledermaus, die in den Inferno-Biomen nistet; einige verirren sich in die Oberwelt. Harmlos. Lässt Leder fallen."));

		HandbookEntries.add(new HandbookEntry("items", "inferno_powder_from_wraith_ember",
				"copper_inferno:wraith_ember", "infernomobs/inferno_powder_from_wraith_ember",
				new String[] {"copper_inferno:wraith_ember", "copper_inferno:copper_dust", "", "", "", "", "", "", ""},
				"copper_inferno:inferno_powder", 2,
				"Grind a Wraith Ember with Copper Dust into two Inferno Powder.",
				"Mahle Schleierglut mit Kupferstaub zu zwei Infernopulver."));
		HandbookEntries.add(new HandbookEntry("items", "arrow_from_crawler_fang",
				"copper_inferno:crawler_fang", "infernomobs/arrow_from_crawler_fang",
				new String[] {"copper_inferno:crawler_fang", "", "", "minecraft:stick", "", "", "minecraft:feather", "", ""},
				"minecraft:arrow", 4,
				"A Crawler Fang tips a stick and feather into four arrows.",
				"Ein Kriecherzahn macht aus Stock und Feder vier Pfeile."));
		HandbookEntries.add(new HandbookEntry("items", "magma_cream_from_slagling_core",
				"copper_inferno:slagling_core", "infernomobs/magma_cream_from_slagling_core",
				new String[] {"copper_inferno:slagling_core", "", "", "", "", "", "", "", ""},
				"minecraft:magma_cream", 2,
				"Knead a Slagling Core into two Magma Cream.",
				"Knete einen Schlackling-Kern zu zwei Magmacreme."));
		HandbookEntries.add(new HandbookEntry("items", "leather_from_strider_shell",
				"copper_inferno:strider_shell", "infernomobs/leather_from_strider_shell",
				new String[] {"copper_inferno:strider_shell", "copper_inferno:strider_shell", "", "", "", "", "", "", ""},
				"minecraft:leather", 2,
				"Two Strider Shells soften into two leather.",
				"Zwei Schreiterpanzer werden zu zwei Leder."));
	}
}
