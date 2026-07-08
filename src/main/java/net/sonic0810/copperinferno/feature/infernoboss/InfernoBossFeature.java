package net.sonic0810.copperinferno.feature.infernoboss;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BlazeEntity;
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
 * The Inferno dimension's boss fights (boss bars via core.boss.BossBarHolder): The Oxidizer
 * (giant corroded iron golem, summoned on any copper block with an Oxidizer Core) and the
 * Inferno Titan (colossal two-phase blaze, summoned with a Titan Sigil inside the Inferno
 * dimension only). Both reuse vanilla renderers (see {@code InfernoBossFeatureClient});
 * EntityType dimensions are copied from the vanilla registrations (verified via bytecode)
 * and inflated via the SCALE attribute. Summon items, boss drops, trophy, spawn eggs and
 * handbook entries are all registered here.
 */
public final class InfernoBossFeature {
	private InfernoBossFeature() {
	}

	public static EntityType<TheOxidizerEntity> THE_OXIDIZER;
	public static EntityType<InfernoTitanEntity> INFERNO_TITAN;

	public static Item OXIDIZER_CORE;
	public static Item TITAN_SIGIL;
	public static Item OXIDIZER_HEART;
	public static Item VERDIGRIS_SCALE;
	public static Item TITAN_EMBER;
	public static Item INFERNO_CROWN;
	public static Item THE_OXIDIZER_SPAWN_EGG;
	public static Item INFERNO_TITAN_SPAWN_EGG;

	public static void init() {
		registerEntityTypes();
		registerAttributes();
		registerItems();
		registerHandbookEntries();
	}

	private static void registerEntityTypes() {
		// Base dimensions copied from the vanilla registrations (EntityType bytecode:
		// iron_golem = dimensions(1.4f, 2.7f).maxTrackingRange(10); blaze =
		// makeFireImmune().dimensions(0.6f, 1.8f).maxTrackingRange(8)); the bosses' bulk
		// comes from the SCALE attribute, and both get an extended tracking range so the
		// boss bar/model appear well before the fight. Summoned bosses call setPersistent,
		// so MISC (no natural despawn logic) fits both.
		THE_OXIDIZER = ModEntities.register("the_oxidizer",
				EntityType.Builder.create(TheOxidizerEntity::new, SpawnGroup.MISC)
						.dimensions(1.4f, 2.7f)
						.maxTrackingRange(10));
		INFERNO_TITAN = ModEntities.register("inferno_titan",
				EntityType.Builder.create(InfernoTitanEntity::new, SpawnGroup.MISC)
						.makeFireImmune()
						.dimensions(0.6f, 1.8f)
						.maxTrackingRange(10));
	}

	private static void registerAttributes() {
		// EntityAttributes fields have no GENERIC_ prefix in 1.21.9 (verified via javap);
		// DefaultAttributeContainer.Builder.add(...) overrides the base values.
		FabricDefaultAttributeRegistry.register(THE_OXIDIZER, IronGolemEntity.createIronGolemAttributes()
				.add(EntityAttributes.MAX_HEALTH, 300.0)
				.add(EntityAttributes.ATTACK_DAMAGE, 18.0)
				.add(EntityAttributes.SCALE, 2.0)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0));
		FabricDefaultAttributeRegistry.register(INFERNO_TITAN, BlazeEntity.createBlazeAttributes()
				.add(EntityAttributes.MAX_HEALTH, 260.0)
				.add(EntityAttributes.SCALE, 2.5)
				.add(EntityAttributes.FOLLOW_RANGE, 64.0));
	}

	private static void registerItems() {
		OXIDIZER_CORE = ModItems.register("oxidizer_core", OxidizerCoreItem::new, new Item.Settings());
		TITAN_SIGIL = ModItems.register("titan_sigil", TitanSigilItem::new, new Item.Settings());

		OXIDIZER_HEART = ModItems.register("oxidizer_heart", Item::new, new Item.Settings());
		VERDIGRIS_SCALE = ModItems.register("verdigris_scale", Item::new, new Item.Settings());
		TITAN_EMBER = ModItems.register("titan_ember", Item::new, new Item.Settings());
		// Trophy: Item.Settings.rarity(Rarity) verified via javap.
		INFERNO_CROWN = ModItems.register("inferno_crown", Item::new,
				new Item.Settings().rarity(Rarity.EPIC));

		// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the vanilla
		// SpawnEggItem reads its entity type from in 1.21.9 (dr_pepper_golem pattern).
		THE_OXIDIZER_SPAWN_EGG = ModItems.register("the_oxidizer_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(THE_OXIDIZER));
		INFERNO_TITAN_SPAWN_EGG = ModItems.register("inferno_titan_spawn_egg", SpawnEggItem::new,
				new Item.Settings().spawnEgg(INFERNO_TITAN));

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			// Summon items, then drops/trophy, then the spawn eggs.
			entries.add(OXIDIZER_CORE);
			entries.add(TITAN_SIGIL);
			entries.add(OXIDIZER_HEART);
			entries.add(VERDIGRIS_SCALE);
			entries.add(TITAN_EMBER);
			entries.add(INFERNO_CROWN);
			entries.add(THE_OXIDIZER_SPAWN_EGG);
			entries.add(INFERNO_TITAN_SPAWN_EGG);
		});
	}

	private static void registerHandbookEntries() {
		HandbookEntries.add(new HandbookEntry("bosses", "the_oxidizer",
				"copper_inferno:the_oxidizer_spawn_egg", null, null, null, 0,
				"The Oxidizer - a giant corroded iron golem. Summon: click an Oxidizer Core on any copper block. It hunts players on sight and every few seconds vents a corrosive verdigris cloud on anyone it can see within 6 blocks - Slowness, Weakness and the Oxidized slow (-30% speed). Below half health it enrages and speeds up. Drops an Oxidizer Heart and 2-4 Verdigris Scales.",
				"Der Oxidierer - ein riesiger korrodierter Eisengolem. Beschwörung: einen Oxidierer-Kern auf einen beliebigen Kupferblock klicken. Er jagt Spieler auf Sicht und stößt alle paar Sekunden eine ätzende Grünspanwolke auf jeden aus, den er im Umkreis von 6 Blöcken sehen kann - Langsamkeit, Schwäche und die Oxidiert-Verlangsamung (-30% Tempo). Unter halber Gesundheit wird er rasend und schneller. Lässt ein Oxidierer-Herz und 2-4 Grünspanschuppen fallen."));
		HandbookEntries.add(new HandbookEntry("bosses", "inferno_titan",
				"copper_inferno:inferno_titan_spawn_egg", null, null, null, 0,
				"Inferno Titan - a colossal blaze lord. Summon: use a Titan Sigil, but only inside the Inferno dimension. Phase 1 rains fireballs from afar; below half health phase 2 begins - it calls 2 blaze minions, hardens with Resistance and charges into melee. Drops 2-3 Titan Embers and the Inferno Crown trophy.",
				"Inferno-Titan - ein kolossaler Lohenfürst. Beschwörung: ein Titanensiegel benutzen, aber nur in der Inferno-Dimension. Phase 1 regnet Feuerbälle aus der Ferne; unter halber Gesundheit beginnt Phase 2 - er ruft 2 Lohen-Diener, härtet sich mit Resistenz und stürmt in den Nahkampf. Lässt 2-3 Titanenglut und die Trophäe Infernokrone fallen."));

		HandbookEntries.add(new HandbookEntry("bosses", "oxidizer_core_recipe",
				"copper_inferno:oxidizer_core", "infernoboss/oxidizer_core",
				new String[] {
						"minecraft:copper_ingot", "copper_inferno:oxidized_copper_dust", "minecraft:copper_ingot",
						"copper_inferno:oxidized_copper_dust", "copper_inferno:inferno_core", "copper_inferno:oxidized_copper_dust",
						"minecraft:copper_ingot", "copper_inferno:oxidized_copper_dust", "minecraft:copper_ingot"},
				"copper_inferno:oxidizer_core", 1,
				"Oxidizer Core - ring an Inferno Core with 4 Copper Ingots and 4 Oxidized Copper Dust. Click it on any copper block to summon The Oxidizer.",
				"Oxidierer-Kern - einen Inferno-Kern mit 4 Kupferbarren und 4 Oxidiertem Kupferstaub umringen. Auf einen beliebigen Kupferblock klicken, um den Oxidierer zu beschwören."));
		HandbookEntries.add(new HandbookEntry("bosses", "titan_sigil_recipe",
				"copper_inferno:titan_sigil", "infernoboss/titan_sigil",
				new String[] {
						"minecraft:blaze_rod", "copper_inferno:inferno_powder", "minecraft:blaze_rod",
						"copper_inferno:inferno_powder", "minecraft:magma_block", "copper_inferno:inferno_powder",
						"minecraft:blaze_rod", "copper_inferno:inferno_powder", "minecraft:blaze_rod"},
				"copper_inferno:titan_sigil", 1,
				"Titan Sigil - ring a Magma Block with 4 Blaze Rods and 4 Inferno Powder. Use it inside the Inferno dimension to summon the Inferno Titan.",
				"Titanensiegel - einen Magmablock mit 4 Lohenruten und 4 Infernopulver umringen. In der Inferno-Dimension benutzen, um den Inferno-Titan zu beschwören."));
	}
}
