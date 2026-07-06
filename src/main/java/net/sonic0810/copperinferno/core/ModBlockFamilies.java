package net.sonic0810.copperinferno.core;

import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import org.jetbrains.annotations.Nullable;

/**
 * Helper for registering "block families": a base cube block plus its slab, stairs and
 * (optionally) wall variants, all sharing the same settings template. Family fields live in the
 * individual feature classes.
 */
public final class ModBlockFamilies {
	private ModBlockFamilies() {
	}

	/** A registered block family. {@code wall} is {@code null} when the family has no wall. */
	public record BlockFamily(Block block, Block slab, Block stairs, @Nullable Block wall) {
	}

	/**
	 * {@link StairsBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it
	 * publicly constructible.
	 */
	public static class PublicStairsBlock extends StairsBlock {
		public PublicStairsBlock(BlockState baseBlockState, AbstractBlock.Settings settings) {
			super(baseBlockState, settings);
		}
	}

	/**
	 * Registers a full-cube block family under {@code copper_inferno:<blockName>} (base block),
	 * {@code <stem>_slab}, {@code <stem>_stairs} and, when {@code withWall} is true,
	 * {@code <stem>_wall}. Each block gets a plain {@link net.minecraft.item.BlockItem}.
	 *
	 * <p>The {@code settings} supplier is invoked FRESH for every registration:
	 * {@link ModBlocks#register} writes a registry key into the settings instance, so sharing one
	 * {@link AbstractBlock.Settings} across blocks would corrupt every block after the first.
	 */
	public static BlockFamily registerCubeFamily(String blockName, String stem, Supplier<AbstractBlock.Settings> settings, boolean withWall) {
		Block block = ModBlocks.register(blockName, Block::new, settings.get(), true);
		Block slab = ModBlocks.register(stem + "_slab", SlabBlock::new, settings.get(), true);
		Block stairs = ModBlocks.register(stem + "_stairs", s -> new PublicStairsBlock(block.getDefaultState(), s), settings.get(), true);
		Block wall = withWall ? ModBlocks.register(stem + "_wall", WallBlock::new, settings.get(), true) : null;
		return new BlockFamily(block, slab, stairs, wall);
	}
}
