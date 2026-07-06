package net.sonic0810.copperinferno.feature.masonry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.OxidizableBlock;
import net.minecraft.block.OxidizableSlabBlock;
import net.minecraft.block.OxidizableStairsBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.sonic0810.copperinferno.core.ModBlockFamilies;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;

/**
 * Masonry v2: two REAL-oxidizing building families — Copper Bricks and Copper Tiles — mirroring
 * vanilla copper behavior (56 blocks total, 28 per family).
 *
 * <p>Per family: 4 oxidation stages x (cube + slab + stairs) using
 * {@link OxidizableBlock}/{@link OxidizableSlabBlock}/{@link OxidizableStairsBlock} (these
 * random-tick themselves; verified via javap), 4 waxed counterparts per shape using plain
 * {@link Block}/{@link SlabBlock}/{@link ModBlockFamilies.PublicStairsBlock}, and 4 STATIC walls
 * (one per stage). Walls do NOT oxidize and have no waxed variants because vanilla 1.21.9 has no
 * {@code OxidizableWallBlock} (the cut-copper family likewise has no walls).
 *
 * <p>Real oxidation/waxing is wired through Fabric's {@link OxidizableBlocksRegistry}:
 * next-stage pairs for cubes/slabs/stairs plus waxable pairs for all four stages of each shape,
 * which gives vanilla-identical random-tick weathering, axe scraping and honeycomb waxing.
 */
public final class MasonryFeature {
	private MasonryFeature() {
	}

	private static final Oxidizable.OxidationLevel[] STAGES = {
			Oxidizable.OxidationLevel.UNAFFECTED,
			Oxidizable.OxidationLevel.EXPOSED,
			Oxidizable.OxidationLevel.WEATHERED,
			Oxidizable.OxidationLevel.OXIDIZED
	};
	private static final String[] STAGE_PREFIXES = {"", "exposed_", "weathered_", "oxidized_"};
	/** Vanilla copper map colors per stage (verified against the 1.21.9 Blocks bytecode). */
	private static final MapColor[] STAGE_MAP_COLORS = {
			MapColor.ORANGE, MapColor.TERRACOTTA_LIGHT_GRAY, MapColor.DARK_AQUA, MapColor.TEAL
	};

	/** One masonry family; every array is indexed by oxidation stage (0=unaffected .. 3=oxidized). */
	public static final class MasonryFamily {
		public final Block[] cubes = new Block[4];
		public final Block[] slabs = new Block[4];
		public final Block[] stairs = new Block[4];
		public final Block[] walls = new Block[4];
		public final Block[] waxedCubes = new Block[4];
		public final Block[] waxedSlabs = new Block[4];
		public final Block[] waxedStairs = new Block[4];
	}

	public static MasonryFamily COPPER_BRICKS;
	public static MasonryFamily COPPER_TILES;

	public static void init() {
		COPPER_BRICKS = registerFamily("copper_bricks", "copper_brick");
		COPPER_TILES = registerFamily("copper_tiles", "copper_tile");

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			for (MasonryFamily family : new MasonryFamily[] {COPPER_BRICKS, COPPER_TILES}) {
				for (int stage = 0; stage < 4; stage++) {
					entries.add(family.cubes[stage]);
					entries.add(family.stairs[stage]);
					entries.add(family.slabs[stage]);
					entries.add(family.walls[stage]);
				}
				for (int stage = 0; stage < 4; stage++) {
					entries.add(family.waxedCubes[stage]);
					entries.add(family.waxedStairs[stage]);
					entries.add(family.waxedSlabs[stage]);
				}
			}
		});
	}

	private static MasonryFamily registerFamily(String familyName, String stem) {
		MasonryFamily family = new MasonryFamily();
		for (int i = 0; i < 4; i++) {
			Oxidizable.OxidationLevel stage = STAGES[i];
			String prefix = STAGE_PREFIXES[i];

			// A FRESH Settings instance per registration: ModBlocks.register writes the registry
			// key into the settings, so instances must never be shared.
			family.cubes[i] = ModBlocks.register(prefix + familyName,
					s -> new OxidizableBlock(stage, s), settings(i), true);
			family.slabs[i] = ModBlocks.register(prefix + stem + "_slab",
					s -> new OxidizableSlabBlock(stage, s), settings(i), true);
			Block base = family.cubes[i];
			family.stairs[i] = ModBlocks.register(prefix + stem + "_stairs",
					s -> new OxidizableStairsBlock(stage, base.getDefaultState(), s), settings(i), true);
			// Static wall (no OxidizableWallBlock in vanilla 1.21.9, so no oxidation/waxing).
			family.walls[i] = ModBlocks.register(prefix + stem + "_wall",
					WallBlock::new, settings(i), true);

			family.waxedCubes[i] = ModBlocks.register("waxed_" + prefix + familyName,
					Block::new, settings(i), true);
			family.waxedSlabs[i] = ModBlocks.register("waxed_" + prefix + stem + "_slab",
					SlabBlock::new, settings(i), true);
			Block waxedBase = family.waxedCubes[i];
			family.waxedStairs[i] = ModBlocks.register("waxed_" + prefix + stem + "_stairs",
					s -> new ModBlockFamilies.PublicStairsBlock(waxedBase.getDefaultState(), s), settings(i), true);
		}

		for (int i = 0; i < 3; i++) {
			OxidizableBlocksRegistry.registerOxidizableBlockPair(family.cubes[i], family.cubes[i + 1]);
			OxidizableBlocksRegistry.registerOxidizableBlockPair(family.slabs[i], family.slabs[i + 1]);
			OxidizableBlocksRegistry.registerOxidizableBlockPair(family.stairs[i], family.stairs[i + 1]);
		}
		for (int i = 0; i < 4; i++) {
			OxidizableBlocksRegistry.registerWaxableBlockPair(family.cubes[i], family.waxedCubes[i]);
			OxidizableBlocksRegistry.registerWaxableBlockPair(family.slabs[i], family.waxedSlabs[i]);
			OxidizableBlocksRegistry.registerWaxableBlockPair(family.stairs[i], family.waxedStairs[i]);
		}
		return family;
	}

	private static AbstractBlock.Settings settings(int stageIndex) {
		return AbstractBlock.Settings.create()
				.mapColor(STAGE_MAP_COLORS[stageIndex])
				.strength(3.0F, 6.0F)
				.requiresTool()
				.sounds(BlockSoundGroup.COPPER);
	}
}
