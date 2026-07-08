package net.sonic0810.copperinferno.feature.systems;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.sonic0810.copperinferno.core.ModBlockEntities;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * v4 "Systems" feature (WP15) — 8 named gameplay systems:
 *
 * <ol>
 *   <li><b>copper_conduction</b> ({@link CopperConductionSystem}) — lightning electrifies
 *       copper blocks: temporary luminance + zaps on nearby mobs.</li>
 *   <li><b>magnet_bench</b> ({@link MagnetBenchBlock}) — trades the gear feature's copper
 *       magnet + 8 copper ingots for a {@link ReinforcedCopperMagnetItem}.</li>
 *   <li><b>oxidation_weather</b> ({@link OxidationWeatherSystem}) — carried copper gear
 *       oxidizes 2x as fast in the rain (extra listener, core untouched).</li>
 *   <li><b>waxing_station</b> ({@link WaxingStationBlock}) — one honeycomb bulk-waxes every
 *       copper item in the inventory.</li>
 *   <li><b>copper_bounty_board</b> ({@link CopperBountyBoardBlock}) — daily kill bounties
 *       paying out copper coins, progress persisted in block-entity NBT.</li>
 *   <li><b>tempering_forge</b> ({@link TemperingForgeBlock}) — burns ember dust to repair
 *       held gear.</li>
 *   <li><b>soda_fountain</b> ({@link SodaFountainBlock}) — bottles a random cuisine fizz brew
 *       while standing on the sodablocks feature's syrup block. NOTE: {@code soda_syrup_block}
 *       already ships with the sodablocks feature on this branch, so this feature deliberately
 *       REUSES it instead of double-registering the id (which would crash).</li>
 *   <li><b>statue_emotes</b> ({@link EmoteCopperStatueBlock}) — a new wrapper statue with 4
 *       poses on a cycling blockstate property (original statue untouched).</li>
 * </ol>
 */
public final class SystemsFeature {
	private SystemsFeature() {
	}

	public static Block MAGNET_BENCH;
	public static Block WAXING_STATION;
	public static Block COPPER_BOUNTY_BOARD;
	public static Block TEMPERING_FORGE;
	public static Block SODA_FOUNTAIN;
	public static Block EMOTE_COPPER_STATUE;

	public static BlockEntityType<CopperBountyBoardBlockEntity> COPPER_BOUNTY_BOARD_BLOCK_ENTITY;

	public static Item REINFORCED_COPPER_MAGNET;

	public static void init() {
		// ---- blocks (fresh Settings per registration; ids stay literal for the audit) ----
		MAGNET_BENCH = ModBlocks.register("magnet_bench",
				MagnetBenchBlock::new, copperSettings(), true);
		WAXING_STATION = ModBlocks.register("waxing_station",
				WaxingStationBlock::new, copperSettings(), true);
		COPPER_BOUNTY_BOARD = ModBlocks.register("copper_bounty_board",
				CopperBountyBoardBlock::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.OAK_TAN)
						.strength(2.0F)
						.sounds(BlockSoundGroup.WOOD),
				true);
		TEMPERING_FORGE = ModBlocks.register("tempering_forge",
				TemperingForgeBlock::new, copperSettings().luminance(state -> 7), true);
		SODA_FOUNTAIN = ModBlocks.register("soda_fountain",
				SodaFountainBlock::new, copperSettings(), true);
		EMOTE_COPPER_STATUE = ModBlocks.register("emote_copper_statue",
				EmoteCopperStatueBlock::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.ORANGE)
						.strength(3.0F, 6.0F)
						.sounds(BlockSoundGroup.COPPER)
						.nonOpaque(),
				true);

		COPPER_BOUNTY_BOARD_BLOCK_ENTITY = ModBlockEntities.register("copper_bounty_board",
				CopperBountyBoardBlockEntity::new, COPPER_BOUNTY_BOARD);

		// ---- items ----
		REINFORCED_COPPER_MAGNET = ModItems.register("reinforced_copper_magnet",
				ReinforcedCopperMagnetItem::new, new Item.Settings().maxDamage(512));

		// ---- world-event systems ----
		CopperConductionSystem.init();
		OxidationWeatherSystem.init();
		CopperBountyBoardBlockEntity.initKillTracking();

		// ---- creative tabs ----
		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY)
				.register(entries -> entries.add(REINFORCED_COPPER_MAGNET));
		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			entries.add(MAGNET_BENCH);
			entries.add(WAXING_STATION);
			entries.add(COPPER_BOUNTY_BOARD);
			entries.add(TEMPERING_FORGE);
			entries.add(SODA_FOUNTAIN);
			entries.add(EMOTE_COPPER_STATUE);
		});

		registerHandbookEntries();
	}

	/** Vanilla copper-block-like settings (strength 3/6, tool required, copper sounds). */
	private static AbstractBlock.Settings copperSettings() {
		return AbstractBlock.Settings.create()
				.mapColor(MapColor.ORANGE)
				.strength(3.0F, 6.0F)
				.requiresTool()
				.sounds(BlockSoundGroup.COPPER);
	}

	// ------------------------------------------------------------------
	// Handbook (8 entries, one per system, category "items")
	// ------------------------------------------------------------------

	private static void registerHandbookEntries() {
		final String ING = "minecraft:copper_ingot";

		HandbookEntries.add(new HandbookEntry("items", "systems_copper_conduction",
				"minecraft:lightning_rod", null, null, null, 0,
				"Copper Conduction: lightning that strikes bare copper electrifies it for ten seconds - the block glows and arcs zap any mob that wanders within three blocks. Waxed copper is insulated and never conducts. Summon a storm near a copper roof and watch it spark.",
				"Kupferleitung: Ein Blitz, der blankes Kupfer trifft, elektrisiert es f\u00fcr zehn Sekunden - der Block leuchtet und Funken treffen jedes Monster im Umkreis von drei Bl\u00f6cken. Gewachstes Kupfer ist isoliert und leitet nie. Beschw\u00f6re ein Gewitter \u00fcber einem Kupferdach und sieh es funkeln."));

		HandbookEntries.add(new HandbookEntry("items", "systems_magnet_bench",
				"copper_inferno:magnet_bench", "systems/magnet_bench",
				new String[]{"", "copper_inferno:copper_magnet", "", ING, ING, ING, ING, "minecraft:redstone_block", ING},
				"copper_inferno:magnet_bench", 1,
				"The Magnet Bench upgrades your gear: right-click it while holding a Copper Magnet and 8 copper ingots to trade them for a Reinforced Copper Magnet - double the pull range (16 blocks), a shorter cooldown and 512 durability. Damage and enchantments carry over.",
				"Die Magnetwerkbank verbessert deine Ausr\u00fcstung: Rechtsklicke sie mit einem Kupfermagneten und 8 Kupferbarren, um sie gegen einen Verst\u00e4rkten Kupfermagneten zu tauschen - doppelte Reichweite (16 Bl\u00f6cke), k\u00fcrzere Abklingzeit und 512 Haltbarkeit. Schaden und Verzauberungen bleiben erhalten."));

		HandbookEntries.add(new HandbookEntry("items", "systems_oxidation_weather",
				"minecraft:water_bucket", null, null, null, 0,
				"Oxidation Weather: while it rains, every unwaxed copper tool, weapon and armor piece you carry oxidizes TWICE as fast. Wax your gear (or visit a Waxing Station) before heading out into a storm.",
				"Oxidationswetter: Solange es regnet, oxidiert jedes ungewachste Kupferwerkzeug und jede Kupferr\u00fcstung, die du tr\u00e4gst, DOPPELT so schnell. Wachse deine Ausr\u00fcstung (oder besuche eine Wachsstation), bevor du in den Sturm ziehst."));

		HandbookEntries.add(new HandbookEntry("items", "systems_waxing_station",
				"copper_inferno:waxing_station", "systems/waxing_station",
				new String[]{"minecraft:honeycomb", "minecraft:honeycomb", "minecraft:honeycomb", ING, "minecraft:copper_block", ING, ING, ING, ING},
				"copper_inferno:waxing_station", 1,
				"The Waxing Station bulk-waxes your kit: right-click with a single honeycomb and EVERY unwaxed copper item in your inventory, armor and offhand is waxed at once, freezing its oxidation stage for good.",
				"Die Wachsstation wachst dein ganzes Gep\u00e4ck: Rechtsklicke mit einer einzigen Honigwabe und JEDER ungewachste Kupfergegenstand in Inventar, R\u00fcstung und Zweithand wird auf einmal gewachst - seine Oxidationsstufe ist damit dauerhaft eingefroren."));

		HandbookEntries.add(new HandbookEntry("items", "systems_bounty_board",
				"copper_inferno:copper_bounty_board", "systems/copper_bounty_board",
				new String[]{"minecraft:paper", "minecraft:paper", "minecraft:paper", "minecraft:paper", "copper_inferno:copper_coin", "minecraft:paper", ING, ING, ING},
				"copper_inferno:copper_bounty_board", 1,
				"The Copper Bounty Board posts a daily kill contract - slay the listed mobs within 64 blocks of the board, then right-click to collect your copper coins. Sneak-right-click to re-roll to the next posted bounty.",
				"Die Kupfer-Kopfgeldtafel schl\u00e4gt t\u00e4glich einen Jagdauftrag an - erlege die gelisteten Monster im Umkreis von 64 Bl\u00f6cken der Tafel und rechtsklicke dann, um deine Kupferm\u00fcnzen abzuholen. Schleich-Rechtsklick w\u00fcrfelt zum n\u00e4chsten Kopfgeld weiter."));

		HandbookEntries.add(new HandbookEntry("items", "systems_tempering_forge",
				"copper_inferno:tempering_forge", "systems/tempering_forge",
				new String[]{"copper_inferno:ember_dust", "copper_inferno:ember_dust", "copper_inferno:ember_dust", ING, "minecraft:blast_furnace", ING, ING, ING, ING},
				"copper_inferno:tempering_forge", 1,
				"The Tempering Forge repairs without an anvil: right-click with damaged gear and the forge burns one Ember Dust from your inventory to restore up to 50 durability. No experience cost, no rename tax - just fuel.",
				"Die H\u00e4rteschmiede repariert ohne Amboss: Rechtsklicke mit besch\u00e4digter Ausr\u00fcstung und die Schmiede verbrennt einen Glutstaub aus deinem Inventar, um bis zu 50 Haltbarkeit wiederherzustellen. Keine Erfahrungskosten - nur Brennstoff."));

		HandbookEntries.add(new HandbookEntry("items", "systems_soda_fountain",
				"copper_inferno:soda_fountain", "systems/soda_fountain",
				new String[]{"minecraft:glass", "", "minecraft:glass", ING, "copper_inferno:soda_syrup", ING, ING, ING, ING},
				"copper_inferno:soda_fountain", 1,
				"The Soda Fountain taps the cuisine cellar: place it directly ON TOP of a Soda Syrup Block, right-click with an empty glass bottle and it fills with one of the twenty bottled fizz brews - at random. No syrup tank below, no fizz.",
				"Der Brausebrunnen zapft den K\u00fcchenkeller an: Setze ihn direkt AUF einen Sodasirupblock, rechtsklicke mit einer leeren Glasflasche und sie f\u00fcllt sich mit einer der zwanzig Brauseflaschen - zuf\u00e4llig. Ohne Siruptank darunter gibt es keine Brause."));

		HandbookEntries.add(new HandbookEntry("items", "systems_statue_emotes",
				"copper_inferno:emote_copper_statue", "systems/emote_copper_statue",
				new String[]{"", ING, "", ING, "copper_inferno:copper_player_statue", ING, "", ING, ""},
				"copper_inferno:emote_copper_statue", 1,
				"Statue Emotes: cast a Copper Player Statue in a copper ingot frame and you get the Emote Copper Statue - a little copper figure with four poses. Right-click to cycle Salute, Wave, Cheer and Facepalm.",
				"Statuen-Emotes: Giesse eine Kupfer-Spielerstatue in einen Rahmen aus Kupferbarren und du erh\u00e4ltst die Kupfer-Emote-Statue - eine kleine Kupferfigur mit vier Posen. Rechtsklicken wechselt zwischen Salut, Winken, Jubeln und Facepalm."));
	}
}
