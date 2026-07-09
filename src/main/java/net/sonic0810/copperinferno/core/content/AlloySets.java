package net.sonic0810.copperinferno.core.content;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BulbBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.sonic0810.copperinferno.core.ModBlockFamilies;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModItems;

/**
 * v4 scale registrar: a full 22-block metal/alloy set (plus the 3 companion items) registered
 * from a single call each.
 *
 * <p>AUDIT CONTRACT (devtools/audit_assets.py check (f)): every id below is an {@code m}
 * parameter concatenation with string literals, and call sites MUST pass a string literal (never
 * a variable) as the leading {@code m} argument. The audit then expects
 * items/blockstates/models/loot for all derived ids.
 */
public final class AlloySets {
	private AlloySets() {
	}

	/** All 22 blocks of an alloy set, in canonical (registration/creative-tab) order. */
	public record AlloySet(Block ore, Block deepslateOre, Block cinderOre,
			Block rawBlock, Block block, Block bricks, Block brickSlab, Block brickStairs, Block brickWall,
			Block tiles, Block tileSlab, Block tileStairs, Block tileWall,
			Block cut, Block chiseled, Block pillar, Block lamp, Block bulb,
			Block grate, Block glass, Block glassPane, Block lantern) {
	}

	/** The 3 companion items of an alloy: raw chunk, ingot, nugget. */
	public record AlloyItems(Item raw, Item ingot, Item nugget) {
	}

	/**
	 * Registers the 22 alloy blocks (each with a BlockItem) and appends them to {@code tab} in
	 * canonical order. Settings suppliers are invoked FRESH per registration
	 * ({@link ModBlocks#register} writes a registry key into each instance): {@code ore} for the
	 * three ore variants, {@code glass} for glass + pane (nonOpaque is applied here; the glass
	 * profile is hand-mineable, matching vanilla glass semantics — glass and pane are NOT in the
	 * pickaxe tag), {@code lamp} for the lamp and lantern (the lantern additionally gets
	 * nonOpaque; the lamp profile carries the constant luminance), {@code stone} for everything
	 * else (the grate additionally gets {@code nonOpaque()}, matching its vanilla render
	 * profile).
	 *
	 * <p>Ids: {@code <m>_ore}, {@code deepslate_<m>_ore}, {@code cinder_<m>_ore},
	 * {@code raw_<m>_block}, {@code <m>_block}, {@code <m>_bricks}, {@code <m>_brick_slab},
	 * {@code <m>_brick_stairs}, {@code <m>_brick_wall}, {@code <m>_tiles}, {@code <m>_tile_slab},
	 * {@code <m>_tile_stairs}, {@code <m>_tile_wall}, {@code cut_<m>}, {@code chiseled_<m>},
	 * {@code <m>_pillar}, {@code <m>_lamp}, {@code <m>_bulb}, {@code <m>_grate},
	 * {@code <m>_glass}, {@code <m>_glass_pane}, {@code <m>_lantern}.
	 */
	public static AlloySet registerAlloySet(String m, Supplier<AbstractBlock.Settings> stone,
			Supplier<AbstractBlock.Settings> ore, Supplier<AbstractBlock.Settings> glass,
			Supplier<AbstractBlock.Settings> lamp, RegistryKey<ItemGroup> tab) {
		Block oreBlock = ModBlocks.register(m + "_ore", Block::new, ore.get(), true);
		Block deepslateOre = ModBlocks.register("deepslate_" + m + "_ore", Block::new, ore.get(), true);
		Block cinderOre = ModBlocks.register("cinder_" + m + "_ore", Block::new, ore.get(), true);

		Block rawBlock = ModBlocks.register("raw_" + m + "_block", Block::new, stone.get(), true);
		Block block = ModBlocks.register(m + "_block", Block::new, stone.get(), true);
		Block bricks = ModBlocks.register(m + "_bricks", Block::new, stone.get(), true);
		Block brickSlab = ModBlocks.register(m + "_brick_slab", SlabBlock::new, stone.get(), true);
		Block brickStairs = ModBlocks.register(m + "_brick_stairs",
				s -> new ModBlockFamilies.PublicStairsBlock(bricks.getDefaultState(), s), stone.get(), true);
		Block brickWall = ModBlocks.register(m + "_brick_wall", WallBlock::new, stone.get(), true);

		Block tiles = ModBlocks.register(m + "_tiles", Block::new, stone.get(), true);
		Block tileSlab = ModBlocks.register(m + "_tile_slab", SlabBlock::new, stone.get(), true);
		Block tileStairs = ModBlocks.register(m + "_tile_stairs",
				s -> new ModBlockFamilies.PublicStairsBlock(tiles.getDefaultState(), s), stone.get(), true);
		Block tileWall = ModBlocks.register(m + "_tile_wall", WallBlock::new, stone.get(), true);

		Block cut = ModBlocks.register("cut_" + m, Block::new, stone.get(), true);
		Block chiseled = ModBlocks.register("chiseled_" + m, Block::new, stone.get(), true);
		Block pillar = ModBlocks.register(m + "_pillar", PillarBlock::new, stone.get(), true);
		Block lampBlock = ModBlocks.register(m + "_lamp", Block::new, lamp.get(), true);
		Block bulb = ModBlocks.register(m + "_bulb", BulbBlock::new, stone.get(), true);

		Block grate = ModBlocks.register(m + "_grate", ContentGrateBlock::new, stone.get().nonOpaque(), true);
		Block glassBlock = ModBlocks.register(m + "_glass", ContentGlassBlock::new, glass.get().nonOpaque(), true);
		Block glassPane = ModBlocks.register(m + "_glass_pane", ContentGlassPaneBlock::new,
				glass.get().nonOpaque(), true);
		Block lantern = ModBlocks.register(m + "_lantern", LanternBlock::new, lamp.get().nonOpaque(), true);

		AlloySet set = new AlloySet(oreBlock, deepslateOre, cinderOre,
				rawBlock, block, bricks, brickSlab, brickStairs, brickWall,
				tiles, tileSlab, tileStairs, tileWall,
				cut, chiseled, pillar, lampBlock, bulb,
				grate, glassBlock, glassPane, lantern);

		ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> {
			entries.add(set.ore());
			entries.add(set.deepslateOre());
			entries.add(set.cinderOre());
			entries.add(set.rawBlock());
			entries.add(set.block());
			entries.add(set.bricks());
			entries.add(set.brickSlab());
			entries.add(set.brickStairs());
			entries.add(set.brickWall());
			entries.add(set.tiles());
			entries.add(set.tileSlab());
			entries.add(set.tileStairs());
			entries.add(set.tileWall());
			entries.add(set.cut());
			entries.add(set.chiseled());
			entries.add(set.pillar());
			entries.add(set.lamp());
			entries.add(set.bulb());
			entries.add(set.grate());
			entries.add(set.glass());
			entries.add(set.glassPane());
			entries.add(set.lantern());
		});
		return set;
	}

	/**
	 * Registers the 3 alloy companion items: {@code raw_<m>}, {@code <m>_ingot},
	 * {@code <m>_nugget} (plain items, fresh {@link Item.Settings} per registration). Creative-tab
	 * placement is left to the calling feature.
	 */
	public static AlloyItems registerAlloyItems(String m) {
		Item raw = ModItems.register("raw_" + m, Item::new, new Item.Settings());
		Item ingot = ModItems.register(m + "_ingot", Item::new, new Item.Settings());
		Item nugget = ModItems.register(m + "_nugget", Item::new, new Item.Settings());
		return new AlloyItems(raw, ingot, nugget);
	}
}
