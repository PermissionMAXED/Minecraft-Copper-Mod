package net.sonic0810.copperinferno.core.content;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.sonic0810.copperinferno.core.ModBlockFamilies;
import net.sonic0810.copperinferno.core.ModBlocks;

/**
 * v4 scale registrar: a full 20-block stone "palette" set registered from a single call.
 *
 * <p>AUDIT CONTRACT (devtools/audit_assets.py check (f)): every id below is a
 * {@code palette}-parameter concatenation with string literals, and call sites MUST pass a string
 * literal (never a variable) as the leading {@code palette} argument. The audit then expects
 * items/blockstates/models/loot for all 20 derived ids.
 */
public final class PaletteSets {
	private PaletteSets() {
	}

	/** All 20 blocks of a palette set, in canonical (registration/creative-tab) order. */
	public record PaletteSet(Block block, Block slab, Block stairs, Block wall,
			Block bricks, Block brickSlab, Block brickStairs, Block brickWall,
			Block tiles, Block tileSlab, Block tileStairs, Block tileWall,
			Block chiseled, Block carved, Block pillar, Block cut,
			Block lamp, Block lantern, Block glass, Block glassPane) {
	}

	/**
	 * Registers the 20 palette blocks (each with a BlockItem) and appends them to {@code tab} in
	 * canonical order. Settings suppliers are invoked FRESH per registration
	 * ({@link ModBlocks#register} writes a registry key into each instance): {@code stone} for the
	 * 16 stone shapes, {@code glass} for glass + pane (nonOpaque is applied here), {@code lamp}
	 * for the lamp and lantern (the lantern additionally gets nonOpaque).
	 *
	 * <p>Ids: {@code <palette>}, {@code <palette>_slab}, {@code <palette>_stairs},
	 * {@code <palette>_wall}, {@code <palette>_bricks}, {@code <palette>_brick_slab},
	 * {@code <palette>_brick_stairs}, {@code <palette>_brick_wall}, {@code <palette>_tiles},
	 * {@code <palette>_tile_slab}, {@code <palette>_tile_stairs}, {@code <palette>_tile_wall},
	 * {@code chiseled_<palette>}, {@code carved_<palette>}, {@code <palette>_pillar},
	 * {@code cut_<palette>}, {@code <palette>_lamp}, {@code <palette>_lantern},
	 * {@code <palette>_glass}, {@code <palette>_glass_pane}.
	 */
	public static PaletteSet registerPaletteSet(String palette, Supplier<AbstractBlock.Settings> stone,
			Supplier<AbstractBlock.Settings> glass, Supplier<AbstractBlock.Settings> lamp,
			RegistryKey<ItemGroup> tab) {
		Block block = ModBlocks.register(palette, Block::new, stone.get(), true);
		Block slab = ModBlocks.register(palette + "_slab", SlabBlock::new, stone.get(), true);
		Block stairs = ModBlocks.register(palette + "_stairs",
				s -> new ModBlockFamilies.PublicStairsBlock(block.getDefaultState(), s), stone.get(), true);
		Block wall = ModBlocks.register(palette + "_wall", WallBlock::new, stone.get(), true);

		Block bricks = ModBlocks.register(palette + "_bricks", Block::new, stone.get(), true);
		Block brickSlab = ModBlocks.register(palette + "_brick_slab", SlabBlock::new, stone.get(), true);
		Block brickStairs = ModBlocks.register(palette + "_brick_stairs",
				s -> new ModBlockFamilies.PublicStairsBlock(bricks.getDefaultState(), s), stone.get(), true);
		Block brickWall = ModBlocks.register(palette + "_brick_wall", WallBlock::new, stone.get(), true);

		Block tiles = ModBlocks.register(palette + "_tiles", Block::new, stone.get(), true);
		Block tileSlab = ModBlocks.register(palette + "_tile_slab", SlabBlock::new, stone.get(), true);
		Block tileStairs = ModBlocks.register(palette + "_tile_stairs",
				s -> new ModBlockFamilies.PublicStairsBlock(tiles.getDefaultState(), s), stone.get(), true);
		Block tileWall = ModBlocks.register(palette + "_tile_wall", WallBlock::new, stone.get(), true);

		Block chiseled = ModBlocks.register("chiseled_" + palette, Block::new, stone.get(), true);
		Block carved = ModBlocks.register("carved_" + palette, Block::new, stone.get(), true);
		Block pillar = ModBlocks.register(palette + "_pillar", PillarBlock::new, stone.get(), true);
		Block cut = ModBlocks.register("cut_" + palette, Block::new, stone.get(), true);

		Block lampBlock = ModBlocks.register(palette + "_lamp", Block::new, lamp.get(), true);
		Block lantern = ModBlocks.register(palette + "_lantern", LanternBlock::new, lamp.get().nonOpaque(), true);
		Block glassBlock = ModBlocks.register(palette + "_glass", ContentGlassBlock::new, glass.get().nonOpaque(), true);
		Block glassPane = ModBlocks.register(palette + "_glass_pane", ContentGlassPaneBlock::new,
				glass.get().nonOpaque(), true);

		PaletteSet set = new PaletteSet(block, slab, stairs, wall,
				bricks, brickSlab, brickStairs, brickWall,
				tiles, tileSlab, tileStairs, tileWall,
				chiseled, carved, pillar, cut,
				lampBlock, lantern, glassBlock, glassPane);

		ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> {
			entries.add(set.block());
			entries.add(set.slab());
			entries.add(set.stairs());
			entries.add(set.wall());
			entries.add(set.bricks());
			entries.add(set.brickSlab());
			entries.add(set.brickStairs());
			entries.add(set.brickWall());
			entries.add(set.tiles());
			entries.add(set.tileSlab());
			entries.add(set.tileStairs());
			entries.add(set.tileWall());
			entries.add(set.chiseled());
			entries.add(set.carved());
			entries.add(set.pillar());
			entries.add(set.cut());
			entries.add(set.lamp());
			entries.add(set.lantern());
			entries.add(set.glass());
			entries.add(set.glassPane());
		});
		return set;
	}
}
