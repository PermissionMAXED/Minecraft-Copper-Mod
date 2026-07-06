package net.sonic0810.copperinferno.feature.sodablocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.sonic0810.copperinferno.core.ModBlockFamilies;
import net.sonic0810.copperinferno.core.ModBlockFamilies.BlockFamily;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;

/**
 * Dr.Pepper / soda-factory building blocks: three cube families (can bricks, can tiles, sugar
 * bricks — each with slab/stairs/wall), eight flavored can blocks and eight themed single blocks.
 * 28 blocks total, all with block items in the mod's Blocks creative tab.
 */
public final class SodaBlocksFeature {
	private SodaBlocksFeature() {
	}

	public static BlockFamily DR_PEPPER_CAN_BRICKS;
	public static BlockFamily DR_PEPPER_CAN_TILES;
	public static BlockFamily SUGAR_BRICKS;

	public static Block CHERRY_SODA_CAN_BLOCK;
	public static Block VANILLA_SODA_CAN_BLOCK;
	public static Block CREAM_SODA_CAN_BLOCK;
	public static Block DIET_DR_PEPPER_CAN_BLOCK;
	public static Block ORANGE_SODA_CAN_BLOCK;
	public static Block GRAPE_SODA_CAN_BLOCK;
	public static Block LIME_SODA_CAN_BLOCK;
	public static Block BLUEBERRY_SODA_CAN_BLOCK;

	public static Block SODA_SYRUP_BLOCK;
	public static Block SUGAR_BLOCK;
	public static Block CARAMEL_BLOCK;
	public static Block FIZZY_SODA_BLOCK;
	public static Block CRUSHED_CAN_BLOCK;
	public static Block DR_PEPPER_CRATE;
	public static Block BOTTLE_CAP_BLOCK;
	public static Block SODA_ICE_BLOCK;

	/** Fresh settings per block: registration writes a registry key into the instance. */
	private static AbstractBlock.Settings canBrickSettings() {
		return AbstractBlock.Settings.create()
				.mapColor(MapColor.DARK_RED)
				.strength(1.5F, 6.0F)
				.sounds(BlockSoundGroup.METAL)
				.requiresTool();
	}

	private static AbstractBlock.Settings sugarBrickSettings() {
		return AbstractBlock.Settings.create()
				.mapColor(MapColor.WHITE)
				.strength(1.2F)
				.sounds(BlockSoundGroup.SAND);
	}

	/** Same feel as the v1 dr_pepper_can_block (no tool required). */
	private static AbstractBlock.Settings flavorCanSettings(MapColor mapColor) {
		return AbstractBlock.Settings.create()
				.mapColor(mapColor)
				.strength(1.0F, 6.0F)
				.sounds(BlockSoundGroup.METAL);
	}

	public static void init() {
		DR_PEPPER_CAN_BRICKS = ModBlockFamilies.registerCubeFamily(
				"dr_pepper_can_bricks", "dr_pepper_can_brick", SodaBlocksFeature::canBrickSettings, true);
		DR_PEPPER_CAN_TILES = ModBlockFamilies.registerCubeFamily(
				"dr_pepper_can_tiles", "dr_pepper_can_tile", SodaBlocksFeature::canBrickSettings, true);
		SUGAR_BRICKS = ModBlockFamilies.registerCubeFamily(
				"sugar_bricks", "sugar_brick", SodaBlocksFeature::sugarBrickSettings, true);

		CHERRY_SODA_CAN_BLOCK = ModBlocks.register("cherry_soda_can_block", Block::new,
				flavorCanSettings(MapColor.RED), true);
		VANILLA_SODA_CAN_BLOCK = ModBlocks.register("vanilla_soda_can_block", Block::new,
				flavorCanSettings(MapColor.OFF_WHITE), true);
		CREAM_SODA_CAN_BLOCK = ModBlocks.register("cream_soda_can_block", Block::new,
				flavorCanSettings(MapColor.PALE_YELLOW), true);
		DIET_DR_PEPPER_CAN_BLOCK = ModBlocks.register("diet_dr_pepper_can_block", Block::new,
				flavorCanSettings(MapColor.LIGHT_GRAY), true);
		ORANGE_SODA_CAN_BLOCK = ModBlocks.register("orange_soda_can_block", Block::new,
				flavorCanSettings(MapColor.ORANGE), true);
		GRAPE_SODA_CAN_BLOCK = ModBlocks.register("grape_soda_can_block", Block::new,
				flavorCanSettings(MapColor.PURPLE), true);
		LIME_SODA_CAN_BLOCK = ModBlocks.register("lime_soda_can_block", Block::new,
				flavorCanSettings(MapColor.LIME), true);
		BLUEBERRY_SODA_CAN_BLOCK = ModBlocks.register("blueberry_soda_can_block", Block::new,
				flavorCanSettings(MapColor.BLUE), true);

		SODA_SYRUP_BLOCK = ModBlocks.register("soda_syrup_block", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.DARK_RED)
						.strength(1.0F)
						.sounds(BlockSoundGroup.HONEY),
				true);
		SUGAR_BLOCK = ModBlocks.register("sugar_block", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.WHITE)
						.strength(1.0F)
						.sounds(BlockSoundGroup.SAND),
				true);
		CARAMEL_BLOCK = ModBlocks.register("caramel_block", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.TERRACOTTA_ORANGE)
						.strength(1.0F)
						.sounds(BlockSoundGroup.HONEY),
				true);
		FIZZY_SODA_BLOCK = ModBlocks.register("fizzy_soda_block", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.DARK_RED)
						.strength(1.0F)
						.sounds(BlockSoundGroup.WET_GRASS),
				true);
		CRUSHED_CAN_BLOCK = ModBlocks.register("crushed_can_block", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.IRON_GRAY)
						.strength(1.5F, 6.0F)
						.sounds(BlockSoundGroup.METAL)
						.requiresTool(),
				true);
		DR_PEPPER_CRATE = ModBlocks.register("dr_pepper_crate", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.OAK_TAN)
						.strength(2.0F, 3.0F)
						.sounds(BlockSoundGroup.WOOD),
				true);
		BOTTLE_CAP_BLOCK = ModBlocks.register("bottle_cap_block", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.DULL_RED)
						.strength(1.0F, 6.0F)
						.sounds(BlockSoundGroup.METAL)
						.requiresTool(),
				true);
		SODA_ICE_BLOCK = ModBlocks.register("soda_ice_block", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.LIGHT_BLUE)
						.strength(1.0F)
						.sounds(BlockSoundGroup.GLASS)
						.slipperiness(0.98F),
				true);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			for (BlockFamily family : new BlockFamily[] {DR_PEPPER_CAN_BRICKS, DR_PEPPER_CAN_TILES, SUGAR_BRICKS}) {
				entries.add(family.block());
				entries.add(family.slab());
				entries.add(family.stairs());
				entries.add(family.wall());
			}
			entries.add(CHERRY_SODA_CAN_BLOCK);
			entries.add(VANILLA_SODA_CAN_BLOCK);
			entries.add(CREAM_SODA_CAN_BLOCK);
			entries.add(DIET_DR_PEPPER_CAN_BLOCK);
			entries.add(ORANGE_SODA_CAN_BLOCK);
			entries.add(GRAPE_SODA_CAN_BLOCK);
			entries.add(LIME_SODA_CAN_BLOCK);
			entries.add(BLUEBERRY_SODA_CAN_BLOCK);
			entries.add(SODA_SYRUP_BLOCK);
			entries.add(SUGAR_BLOCK);
			entries.add(CARAMEL_BLOCK);
			entries.add(FIZZY_SODA_BLOCK);
			entries.add(CRUSHED_CAN_BLOCK);
			entries.add(DR_PEPPER_CRATE);
			entries.add(BOTTLE_CAP_BLOCK);
			entries.add(SODA_ICE_BLOCK);
		});
	}
}
