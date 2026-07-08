package net.sonic0810.copperinferno.feature.calamities;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Rarity;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModEntities;
import net.sonic0810.copperinferno.core.ModItems;

/**
 * The Calamities: 10 ultra-powerful summon-only bosses (boss bars via
 * core.boss.BossBarHolder), mirroring the proven infernoboss template. Each boss
 * subclasses a vanilla mob (ravager / iron golem / wither skeleton / blaze /
 * vindicator), keeps its vanilla model but wears its own recolored texture via a
 * renderer subclass (see {@code CalamitiesFeatureClient})
 * and gets its bulk from the SCALE attribute. EntityType dimensions are copied
 * from the vanilla registrations (verified via EntityType bytecode). There is NO
 * natural spawn: every boss is summoned with its {@link CalamitySigilItem}, a
 * spawn egg or /summon. Summon items, drops, EPIC trophies, spawn eggs and
 * handbook entries are all registered here.
 */
public final class CalamitiesFeature {
	private CalamitiesFeature() {
	}

	public static EntityType<EmberlordRavagerEntity> EMBERLORD_RAVAGER;
	public static EntityType<SlagWarlordEntity> SLAG_WARLORD;
	public static EntityType<MoltenColossusEntity> MOLTEN_COLOSSUS;
	public static EntityType<VerdigrisMonarchEntity> VERDIGRIS_MONARCH;
	public static EntityType<AshkingWitherEntity> ASHKING_WITHER;
	public static EntityType<SootReaperEntity> SOOT_REAPER;
	public static EntityType<CinderSovereignEntity> CINDER_SOVEREIGN;
	public static EntityType<PyreTyrantEntity> PYRE_TYRANT;
	public static EntityType<FurnaceFiendEntity> FURNACE_FIEND;
	public static EntityType<CalamityHeraldEntity> CALAMITY_HERALD;

	public static Item EMBERLORD_WARHORN;
	public static Item EMBERLORD_TUSK;
	public static Item CHARRED_HIDE;
	public static Item EMBERLORD_CREST;
	public static Item EMBERLORD_RAVAGER_SPAWN_EGG;
	public static Item SLAG_WAR_BANNER;
	public static Item BROKEN_WAR_AXE;
	public static Item WARLORD_SLAG_CHUNK;
	public static Item WARLORD_TOTEM;
	public static Item SLAG_WARLORD_SPAWN_EGG;
	public static Item COLOSSUS_EFFIGY;
	public static Item COLOSSUS_PLATING;
	public static Item MOLTEN_CORE_SHARD;
	public static Item COLOSSUS_MEDALLION;
	public static Item MOLTEN_COLOSSUS_SPAWN_EGG;
	public static Item VERDIGRIS_REGALIA;
	public static Item MONARCH_PLATE;
	public static Item VERDANT_PATINA;
	public static Item MONARCH_SIGNET;
	public static Item VERDIGRIS_MONARCH_SPAWN_EGG;
	public static Item ASHKING_SKULL_IDOL;
	public static Item CURSED_ASH_CLUMP;
	public static Item ASHKING_RIB;
	public static Item ASHKING_DIADEM;
	public static Item ASHKING_WITHER_SPAWN_EGG;
	public static Item REAPER_KNELL;
	public static Item SOOT_SCYTHE_BLADE;
	public static Item REAPER_SOOT;
	public static Item REAPER_HOURGLASS;
	public static Item SOOT_REAPER_SPAWN_EGG;
	public static Item CINDER_BEACON;
	public static Item CINDER_PLUME;
	public static Item SOVEREIGN_EMBER_SHARD;
	public static Item SOVEREIGN_SCEPTER;
	public static Item CINDER_SOVEREIGN_SPAWN_EGG;
	public static Item TYRANT_PYRE_BRAND;
	public static Item PYRE_FANG;
	public static Item TYRANT_ASH;
	public static Item PYRE_TYRANT_CROWN;
	public static Item PYRE_TYRANT_SPAWN_EGG;
	public static Item FIEND_EMBER_KEY;
	public static Item FIEND_TALON;
	public static Item FIEND_CINDER;
	public static Item FIEND_FURNACE_MASK;
	public static Item FURNACE_FIEND_SPAWN_EGG;
	public static Item HERALD_OMEN_SIGIL;
	public static Item HERALD_EMBERGLASS;
	public static Item OMEN_FRAGMENT;
	public static Item HERALD_WAR_BANNER;
	public static Item CALAMITY_HERALD_SPAWN_EGG;

	public static void init() {
		registerEntityTypes();
		registerAttributes();
		registerItems();
		CalamitiesHandbook.register();
	}

	private static void registerEntityTypes() {
		// Dims from the vanilla EntityType registrations (bytecode-verified);
		// the bosses' bulk comes from the SCALE attribute and all get an extended
		// tracking range so bar/model appear well before the fight. Summoned
		// bosses call setPersistent, so MISC (no natural despawn logic) fits.
		EMBERLORD_RAVAGER = ModEntities.register("emberlord_ravager",
				EntityType.Builder.create(EmberlordRavagerEntity::new, SpawnGroup.MISC)
						.dimensions(1.95f, 2.2f)
						.maxTrackingRange(10));
		SLAG_WARLORD = ModEntities.register("slag_warlord",
				EntityType.Builder.create(SlagWarlordEntity::new, SpawnGroup.MISC)
						.dimensions(1.95f, 2.2f)
						.maxTrackingRange(10));
		MOLTEN_COLOSSUS = ModEntities.register("molten_colossus",
				EntityType.Builder.create(MoltenColossusEntity::new, SpawnGroup.MISC)
						.dimensions(1.4f, 2.7f)
						.maxTrackingRange(10));
		VERDIGRIS_MONARCH = ModEntities.register("verdigris_monarch",
				EntityType.Builder.create(VerdigrisMonarchEntity::new, SpawnGroup.MISC)
						.dimensions(1.4f, 2.7f)
						.maxTrackingRange(10));
		ASHKING_WITHER = ModEntities.register("ashking_wither",
				EntityType.Builder.create(AshkingWitherEntity::new, SpawnGroup.MISC)
						.makeFireImmune()
						.dimensions(0.7f, 2.4f)
						.maxTrackingRange(10));
		SOOT_REAPER = ModEntities.register("soot_reaper",
				EntityType.Builder.create(SootReaperEntity::new, SpawnGroup.MISC)
						.makeFireImmune()
						.dimensions(0.7f, 2.4f)
						.maxTrackingRange(10));
		CINDER_SOVEREIGN = ModEntities.register("cinder_sovereign",
				EntityType.Builder.create(CinderSovereignEntity::new, SpawnGroup.MISC)
						.makeFireImmune()
						.dimensions(0.6f, 1.8f)
						.maxTrackingRange(10));
		PYRE_TYRANT = ModEntities.register("pyre_tyrant",
				EntityType.Builder.create(PyreTyrantEntity::new, SpawnGroup.MISC)
						.makeFireImmune()
						.dimensions(0.6f, 1.8f)
						.maxTrackingRange(10));
		FURNACE_FIEND = ModEntities.register("furnace_fiend",
				EntityType.Builder.create(FurnaceFiendEntity::new, SpawnGroup.MISC)
						.dimensions(0.6f, 1.95f)
						.maxTrackingRange(10));
		CALAMITY_HERALD = ModEntities.register("calamity_herald",
				EntityType.Builder.create(CalamityHeraldEntity::new, SpawnGroup.MISC)
						.dimensions(0.6f, 1.95f)
						.maxTrackingRange(10));
	}

	private static void registerAttributes() {
		// EntityAttributes fields have no GENERIC_ prefix in 1.21.9 (javap);
		// DefaultAttributeContainer.Builder.add(...) overrides the base values.
		FabricDefaultAttributeRegistry.register(EMBERLORD_RAVAGER, RavagerEntity.createRavagerAttributes()
				.add(EntityAttributes.MAX_HEALTH, 400.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 16.0)
				.add(EntityAttributes.SCALE, 2.0)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(SLAG_WARLORD, RavagerEntity.createRavagerAttributes()
				.add(EntityAttributes.MAX_HEALTH, 500.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 18.0)
				.add(EntityAttributes.SCALE, 2.2)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(MOLTEN_COLOSSUS, IronGolemEntity.createIronGolemAttributes()
				.add(EntityAttributes.MAX_HEALTH, 600.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 22.0)
				.add(EntityAttributes.SCALE, 2.5)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(VERDIGRIS_MONARCH, IronGolemEntity.createIronGolemAttributes()
				.add(EntityAttributes.MAX_HEALTH, 550.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 18.0)
				.add(EntityAttributes.SCALE, 2.2)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(ASHKING_WITHER, AbstractSkeletonEntity.createAbstractSkeletonAttributes()
				.add(EntityAttributes.MAX_HEALTH, 350.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 12.0)
				.add(EntityAttributes.SCALE, 2.0)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.6)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(SOOT_REAPER, AbstractSkeletonEntity.createAbstractSkeletonAttributes()
				.add(EntityAttributes.MAX_HEALTH, 300.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 10.0)
				.add(EntityAttributes.SCALE, 1.8)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.4)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(CINDER_SOVEREIGN, BlazeEntity.createBlazeAttributes()
				.add(EntityAttributes.MAX_HEALTH, 450.0)
				.add(EntityAttributes.SCALE, 2.5)
				.add(EntityAttributes.FOLLOW_RANGE, 64.0));
		FabricDefaultAttributeRegistry.register(PYRE_TYRANT, BlazeEntity.createBlazeAttributes()
				.add(EntityAttributes.MAX_HEALTH, 500.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 8.0)
				.add(EntityAttributes.SCALE, 2.8)
				.add(EntityAttributes.FOLLOW_RANGE, 64.0));
		FabricDefaultAttributeRegistry.register(FURNACE_FIEND, VindicatorEntity.createVindicatorAttributes()
				.add(EntityAttributes.MAX_HEALTH, 320.0)
				.add(EntityAttributes.SCALE, 1.8)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.4)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(CALAMITY_HERALD, VindicatorEntity.createVindicatorAttributes()
				.add(EntityAttributes.MAX_HEALTH, 380.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 14.0)
				.add(EntityAttributes.SCALE, 2.0)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.6)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
	}

	private static void registerItems() {
		// Summon items share CalamitySigilItem (TitanSigilItem pattern); the
		// factory lambda runs AFTER registerEntityTypes(), so the type is set.
		EMBERLORD_WARHORN = ModItems.register("emberlord_warhorn",
				settings -> new CalamitySigilItem(settings,
						world -> new EmberlordRavagerEntity(EMBERLORD_RAVAGER, world)),
				new Item.Settings());
		SLAG_WAR_BANNER = ModItems.register("slag_war_banner",
				settings -> new CalamitySigilItem(settings,
						world -> new SlagWarlordEntity(SLAG_WARLORD, world)),
				new Item.Settings());
		COLOSSUS_EFFIGY = ModItems.register("colossus_effigy",
				settings -> new CalamitySigilItem(settings,
						world -> new MoltenColossusEntity(MOLTEN_COLOSSUS, world)),
				new Item.Settings());
		VERDIGRIS_REGALIA = ModItems.register("verdigris_regalia",
				settings -> new CalamitySigilItem(settings,
						world -> new VerdigrisMonarchEntity(VERDIGRIS_MONARCH, world)),
				new Item.Settings());
		ASHKING_SKULL_IDOL = ModItems.register("ashking_skull_idol",
				settings -> new CalamitySigilItem(settings,
						world -> new AshkingWitherEntity(ASHKING_WITHER, world)),
				new Item.Settings());
		REAPER_KNELL = ModItems.register("reaper_knell",
				settings -> new CalamitySigilItem(settings,
						world -> new SootReaperEntity(SOOT_REAPER, world)),
				new Item.Settings());
		CINDER_BEACON = ModItems.register("cinder_beacon",
				settings -> new CalamitySigilItem(settings,
						world -> new CinderSovereignEntity(CINDER_SOVEREIGN, world)),
				new Item.Settings());
		TYRANT_PYRE_BRAND = ModItems.register("tyrant_pyre_brand",
				settings -> new CalamitySigilItem(settings,
						world -> new PyreTyrantEntity(PYRE_TYRANT, world)),
				new Item.Settings());
		FIEND_EMBER_KEY = ModItems.register("fiend_ember_key",
				settings -> new CalamitySigilItem(settings,
						world -> new FurnaceFiendEntity(FURNACE_FIEND, world)),
				new Item.Settings());
		HERALD_OMEN_SIGIL = ModItems.register("herald_omen_sigil",
				settings -> new CalamitySigilItem(settings,
						world -> new CalamityHeraldEntity(CALAMITY_HERALD, world)),
				new Item.Settings());

		EMBERLORD_TUSK = ModItems.register("emberlord_tusk", Item::new, new Item.Settings());
		CHARRED_HIDE = ModItems.register("charred_hide", Item::new, new Item.Settings());
		EMBERLORD_CREST = ModItems.register("emberlord_crest", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		BROKEN_WAR_AXE = ModItems.register("broken_war_axe", Item::new, new Item.Settings());
		WARLORD_SLAG_CHUNK = ModItems.register("warlord_slag_chunk", Item::new, new Item.Settings());
		WARLORD_TOTEM = ModItems.register("warlord_totem", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		COLOSSUS_PLATING = ModItems.register("colossus_plating", Item::new, new Item.Settings());
		MOLTEN_CORE_SHARD = ModItems.register("molten_core_shard", Item::new, new Item.Settings());
		COLOSSUS_MEDALLION = ModItems.register("colossus_medallion", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		MONARCH_PLATE = ModItems.register("monarch_plate", Item::new, new Item.Settings());
		VERDANT_PATINA = ModItems.register("verdant_patina", Item::new, new Item.Settings());
		MONARCH_SIGNET = ModItems.register("monarch_signet", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		CURSED_ASH_CLUMP = ModItems.register("cursed_ash_clump", Item::new, new Item.Settings());
		ASHKING_RIB = ModItems.register("ashking_rib", Item::new, new Item.Settings());
		ASHKING_DIADEM = ModItems.register("ashking_diadem", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		SOOT_SCYTHE_BLADE = ModItems.register("soot_scythe_blade", Item::new, new Item.Settings());
		REAPER_SOOT = ModItems.register("reaper_soot", Item::new, new Item.Settings());
		REAPER_HOURGLASS = ModItems.register("reaper_hourglass", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		CINDER_PLUME = ModItems.register("cinder_plume", Item::new, new Item.Settings());
		SOVEREIGN_EMBER_SHARD = ModItems.register("sovereign_ember_shard", Item::new, new Item.Settings());
		SOVEREIGN_SCEPTER = ModItems.register("sovereign_scepter", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		PYRE_FANG = ModItems.register("pyre_fang", Item::new, new Item.Settings());
		TYRANT_ASH = ModItems.register("tyrant_ash", Item::new, new Item.Settings());
		PYRE_TYRANT_CROWN = ModItems.register("pyre_tyrant_crown", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		FIEND_TALON = ModItems.register("fiend_talon", Item::new, new Item.Settings());
		FIEND_CINDER = ModItems.register("fiend_cinder", Item::new, new Item.Settings());
		FIEND_FURNACE_MASK = ModItems.register("fiend_furnace_mask", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));
		HERALD_EMBERGLASS = ModItems.register("herald_emberglass", Item::new, new Item.Settings());
		OMEN_FRAGMENT = ModItems.register("omen_fragment", Item::new, new Item.Settings());
		HERALD_WAR_BANNER = ModItems.register("herald_war_banner", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));

		// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the
		// vanilla SpawnEggItem reads its entity type from (infernoboss pattern).
		EMBERLORD_RAVAGER_SPAWN_EGG = ModItems.register("emberlord_ravager_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(EMBERLORD_RAVAGER));
		SLAG_WARLORD_SPAWN_EGG = ModItems.register("slag_warlord_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SLAG_WARLORD));
		MOLTEN_COLOSSUS_SPAWN_EGG = ModItems.register("molten_colossus_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(MOLTEN_COLOSSUS));
		VERDIGRIS_MONARCH_SPAWN_EGG = ModItems.register("verdigris_monarch_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(VERDIGRIS_MONARCH));
		ASHKING_WITHER_SPAWN_EGG = ModItems.register("ashking_wither_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(ASHKING_WITHER));
		SOOT_REAPER_SPAWN_EGG = ModItems.register("soot_reaper_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(SOOT_REAPER));
		CINDER_SOVEREIGN_SPAWN_EGG = ModItems.register("cinder_sovereign_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CINDER_SOVEREIGN));
		PYRE_TYRANT_SPAWN_EGG = ModItems.register("pyre_tyrant_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(PYRE_TYRANT));
		FURNACE_FIEND_SPAWN_EGG = ModItems.register("furnace_fiend_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(FURNACE_FIEND));
		CALAMITY_HERALD_SPAWN_EGG = ModItems.register("calamity_herald_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(CALAMITY_HERALD));

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			// Summon items, then per-boss drops + trophy, then the spawn eggs.
			entries.add(EMBERLORD_WARHORN);
			entries.add(SLAG_WAR_BANNER);
			entries.add(COLOSSUS_EFFIGY);
			entries.add(VERDIGRIS_REGALIA);
			entries.add(ASHKING_SKULL_IDOL);
			entries.add(REAPER_KNELL);
			entries.add(CINDER_BEACON);
			entries.add(TYRANT_PYRE_BRAND);
			entries.add(FIEND_EMBER_KEY);
			entries.add(HERALD_OMEN_SIGIL);
			entries.add(EMBERLORD_TUSK);
			entries.add(CHARRED_HIDE);
			entries.add(EMBERLORD_CREST);
			entries.add(BROKEN_WAR_AXE);
			entries.add(WARLORD_SLAG_CHUNK);
			entries.add(WARLORD_TOTEM);
			entries.add(COLOSSUS_PLATING);
			entries.add(MOLTEN_CORE_SHARD);
			entries.add(COLOSSUS_MEDALLION);
			entries.add(MONARCH_PLATE);
			entries.add(VERDANT_PATINA);
			entries.add(MONARCH_SIGNET);
			entries.add(CURSED_ASH_CLUMP);
			entries.add(ASHKING_RIB);
			entries.add(ASHKING_DIADEM);
			entries.add(SOOT_SCYTHE_BLADE);
			entries.add(REAPER_SOOT);
			entries.add(REAPER_HOURGLASS);
			entries.add(CINDER_PLUME);
			entries.add(SOVEREIGN_EMBER_SHARD);
			entries.add(SOVEREIGN_SCEPTER);
			entries.add(PYRE_FANG);
			entries.add(TYRANT_ASH);
			entries.add(PYRE_TYRANT_CROWN);
			entries.add(FIEND_TALON);
			entries.add(FIEND_CINDER);
			entries.add(FIEND_FURNACE_MASK);
			entries.add(HERALD_EMBERGLASS);
			entries.add(OMEN_FRAGMENT);
			entries.add(HERALD_WAR_BANNER);
			entries.add(EMBERLORD_RAVAGER_SPAWN_EGG);
			entries.add(SLAG_WARLORD_SPAWN_EGG);
			entries.add(MOLTEN_COLOSSUS_SPAWN_EGG);
			entries.add(VERDIGRIS_MONARCH_SPAWN_EGG);
			entries.add(ASHKING_WITHER_SPAWN_EGG);
			entries.add(SOOT_REAPER_SPAWN_EGG);
			entries.add(CINDER_SOVEREIGN_SPAWN_EGG);
			entries.add(PYRE_TYRANT_SPAWN_EGG);
			entries.add(FURNACE_FIEND_SPAWN_EGG);
			entries.add(CALAMITY_HERALD_SPAWN_EGG);
		});
	}
}
