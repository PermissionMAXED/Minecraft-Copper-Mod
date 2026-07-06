package net.sonic0810.copperinferno.feature.decostone;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.sonic0810.copperinferno.core.ModBlockFamilies;
import net.sonic0810.copperinferno.core.ModBlockFamilies.BlockFamily;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;

/**
 * Static decorative copper-masonry blocks (no oxidation logic): four cube families
 * (cut copper bricks, mossy copper bricks, copper mosaic, copper shingles — each with slab,
 * stairs and wall) plus three singles (chiseled copper bricks, carved copper, copper pillar).
 * 19 blocks total.
 */
public final class DecoStoneFeature {
	private DecoStoneFeature() {
	}

	public static BlockFamily cutCopperBricks;
	public static BlockFamily mossyCopperBricks;
	public static BlockFamily copperMosaic;
	public static BlockFamily copperShingles;

	public static Block chiseledCopperBricks;
	public static Block carvedCopper;
	public static Block copperPillar;

	/**
	 * Fresh settings per block: {@link ModBlocks#register} writes the registry key into the
	 * settings instance, so a shared instance would corrupt every block after the first.
	 */
	private static Supplier<AbstractBlock.Settings> settings() {
		return () -> AbstractBlock.Settings.create()
				.mapColor(MapColor.ORANGE)
				.strength(3.0F, 6.0F)
				.requiresTool()
				.sounds(BlockSoundGroup.COPPER);
	}

	public static void init() {
		cutCopperBricks = ModBlockFamilies.registerCubeFamily("cut_copper_bricks", "cut_copper_brick", settings(), true);
		mossyCopperBricks = ModBlockFamilies.registerCubeFamily("mossy_copper_bricks", "mossy_copper_brick", settings(), true);
		copperMosaic = ModBlockFamilies.registerCubeFamily("copper_mosaic", "copper_mosaic", settings(), true);
		copperShingles = ModBlockFamilies.registerCubeFamily("copper_shingles", "copper_shingle", settings(), true);

		chiseledCopperBricks = ModBlocks.register("chiseled_copper_bricks", Block::new, settings().get(), true);
		carvedCopper = ModBlocks.register("carved_copper", Block::new, settings().get(), true);
		copperPillar = ModBlocks.register("copper_pillar", PillarBlock::new, settings().get(), true);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			addFamily(entries, cutCopperBricks);
			addFamily(entries, mossyCopperBricks);
			addFamily(entries, copperMosaic);
			addFamily(entries, copperShingles);
			entries.add(chiseledCopperBricks);
			entries.add(carvedCopper);
			entries.add(copperPillar);
		});
	}

	/** Vanilla family shape order: block -> stairs -> slab -> wall. */
	private static void addFamily(FabricItemGroupEntries entries, BlockFamily family) {
		entries.add(family.block());
		entries.add(family.stairs());
		entries.add(family.slab());
		entries.add(family.wall());
	}
}
