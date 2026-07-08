package net.sonic0810.copperinferno.feature.copperdeco;

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
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Copper decoration blocks themed for the Inferno dimension update: 28 NON-oxidizing
 * decorative copper blocks (overworld content). Six cube families (rose copper bricks,
 * burnished copper, burnished copper bricks, copper panels, gilded copper bricks,
 * verdigris bricks — each with slab, stairs and wall) plus four singles (chiseled rose
 * copper bricks and three axis pillars).
 */
public final class CopperDecoFeature {
	private CopperDecoFeature() {
	}

	public static BlockFamily roseCopperBricks;
	public static BlockFamily burnishedCopper;
	public static BlockFamily burnishedCopperBricks;
	public static BlockFamily copperPanels;
	public static BlockFamily gildedCopperBricks;
	public static BlockFamily verdigrisBricks;

	public static Block chiseledRoseCopperBricks;
	public static Block roseCopperPillar;
	public static Block burnishedCopperPillar;
	public static Block verdigrisPillar;

	/**
	 * Fresh settings per block: {@link ModBlocks#register} writes the registry key into the
	 * settings instance, so a shared instance would corrupt every block after the first.
	 */
	private static Supplier<AbstractBlock.Settings> settings(MapColor color) {
		return () -> AbstractBlock.Settings.create()
				.mapColor(color)
				.strength(3.0F, 6.0F)
				.requiresTool()
				.sounds(BlockSoundGroup.COPPER);
	}

	public static void init() {
		roseCopperBricks = ModBlockFamilies.registerCubeFamily("rose_copper_bricks", "rose_copper_brick",
				settings(MapColor.TERRACOTTA_ORANGE), true);
		burnishedCopper = ModBlockFamilies.registerCubeFamily("burnished_copper", "burnished_copper",
				settings(MapColor.ORANGE), true);
		burnishedCopperBricks = ModBlockFamilies.registerCubeFamily("burnished_copper_bricks", "burnished_copper_brick",
				settings(MapColor.ORANGE), true);
		copperPanels = ModBlockFamilies.registerCubeFamily("copper_panels", "copper_panel",
				settings(MapColor.ORANGE), true);
		gildedCopperBricks = ModBlockFamilies.registerCubeFamily("gilded_copper_bricks", "gilded_copper_brick",
				settings(MapColor.GOLD), true);
		verdigrisBricks = ModBlockFamilies.registerCubeFamily("verdigris_bricks", "verdigris_brick",
				settings(MapColor.TEAL), true);

		chiseledRoseCopperBricks = ModBlocks.register("chiseled_rose_copper_bricks", Block::new,
				settings(MapColor.TERRACOTTA_ORANGE).get(), true);
		roseCopperPillar = ModBlocks.register("rose_copper_pillar", PillarBlock::new,
				settings(MapColor.TERRACOTTA_ORANGE).get(), true);
		burnishedCopperPillar = ModBlocks.register("burnished_copper_pillar", PillarBlock::new,
				settings(MapColor.ORANGE).get(), true);
		verdigrisPillar = ModBlocks.register("verdigris_pillar", PillarBlock::new,
				settings(MapColor.TEAL).get(), true);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			addFamily(entries, roseCopperBricks);
			entries.add(chiseledRoseCopperBricks);
			entries.add(roseCopperPillar);
			addFamily(entries, burnishedCopper);
			entries.add(burnishedCopperPillar);
			addFamily(entries, burnishedCopperBricks);
			addFamily(entries, copperPanels);
			addFamily(entries, gildedCopperBricks);
			addFamily(entries, verdigrisBricks);
			entries.add(verdigrisPillar);
		});

		registerHandbook();
	}

	/** Vanilla family shape order: block -> stairs -> slab -> wall. */
	private static void addFamily(FabricItemGroupEntries entries, BlockFamily family) {
		entries.add(family.block());
		entries.add(family.stairs());
		entries.add(family.slab());
		entries.add(family.wall());
	}

	// Item ids used by the handbook grids below (recipeId args stay inline string
	// literals: devtools/check_handbook.py parses those positionally).
	private static final String CUT = "minecraft:cut_copper";
	private static final String ROSE = "copper_inferno:rose_copper_bricks";
	private static final String BURN = "copper_inferno:burnished_copper";
	private static final String BURNB = "copper_inferno:burnished_copper_bricks";
	private static final String PANEL = "copper_inferno:copper_panels";
	private static final String GILD = "copper_inferno:gilded_copper_bricks";
	private static final String VERD = "copper_inferno:verdigris_bricks";

	private static void registerHandbook() {
		// ----- overview
		HandbookEntries.add(new HandbookEntry("blocks", "copperdeco_overview",
				ROSE, null, null, null, 0,
				"The copper decoration set adds 28 non-oxidizing blocks for overworld builds: six families (Rose Copper Bricks, Burnished Copper, Burnished Copper Bricks, Copper Panels, Gilded Copper Bricks and Verdigris Bricks, each with slab, stairs and wall) plus Chiseled Rose Copper Bricks and three pillars. All of them need a pickaxe and never weather.",
				"Das Kupferdekor-Set bringt 28 nicht oxidierende Bl\u00f6cke f\u00fcr Oberwelt-Bauten: sechs Familien (Ros\u00e9kupferziegel, Poliertes Kupfer, Polierte Kupferziegel, Kupferpaneele, Vergoldete Kupferziegel und Gr\u00fcnspanziegel, jeweils mit Stufe, Treppe und Mauer) sowie Gemei\u00dfelte Ros\u00e9kupferziegel und drei S\u00e4ulen. Alle brauchen eine Spitzhacke und verwittern nie."));

		// ----- family entry points
		HandbookEntries.add(new HandbookEntry("blocks", "rose_copper_bricks",
				ROSE, "copperdeco/rose_copper_bricks",
				new String[]{CUT, CUT, "", CUT, CUT, "", "minecraft:redstone", "", ""}, ROSE, 4,
				"Blush four Cut Copper with a pinch of Redstone into four Rose Copper Bricks.",
				"Vier geschnittenes Kupfer mit einer Prise Redstone ergeben vier Ros\u00e9kupferziegel."));
		HandbookEntries.add(new HandbookEntry("blocks", "gilded_copper_bricks",
				GILD, "copperdeco/gilded_copper_bricks",
				new String[]{CUT, CUT, "", CUT, CUT, "", "minecraft:gold_nugget", "", ""}, GILD, 4,
				"A Gold Nugget speckles four Cut Copper into four Gilded Copper Bricks.",
				"Ein Goldklumpen sprenkelt vier geschnittenes Kupfer zu vier Vergoldeten Kupferziegeln."));
		HandbookEntries.add(new HandbookEntry("blocks", "verdigris_bricks",
				VERD, "copperdeco/verdigris_bricks",
				new String[]{"minecraft:oxidized_cut_copper", "minecraft:oxidized_cut_copper", "",
						"minecraft:oxidized_cut_copper", "minecraft:oxidized_cut_copper", "", "", "", ""}, VERD, 4,
				"Four Oxidized Cut Copper craft into four Verdigris Bricks that keep their green forever.",
				"Vier oxidiertes geschnittenes Kupfer ergeben vier Gr\u00fcnspanziegel, die ihr Gr\u00fcn f\u00fcr immer behalten."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_bricks",
				BURNB, "copperdeco/burnished_copper_bricks",
				new String[]{BURN, BURN, "", BURN, BURN, "", "", "", ""}, BURNB, 4,
				"Four Burnished Copper craft into four Burnished Copper Bricks.",
				"Vier Poliertes Kupfer ergeben vier Polierte Kupferziegel."));

		// ----- singles
		HandbookEntries.add(new HandbookEntry("blocks", "chiseled_rose_copper_bricks",
				"copper_inferno:chiseled_rose_copper_bricks", "copperdeco/chiseled_rose_copper_bricks",
				new String[]{"", "copper_inferno:rose_copper_brick_slab", "", "", "copper_inferno:rose_copper_brick_slab", "", "", "", ""},
				"copper_inferno:chiseled_rose_copper_bricks", 1,
				"Two Rose Copper Brick Slabs stack into one Chiseled Rose Copper Bricks.",
				"Zwei Ros\u00e9kupferziegelstufen ergeben gestapelt einmal Gemei\u00dfelte Ros\u00e9kupferziegel."));
		HandbookEntries.add(new HandbookEntry("blocks", "rose_copper_pillar",
				"copper_inferno:rose_copper_pillar", "copperdeco/rose_copper_pillar",
				new String[]{"", ROSE, "", "", ROSE, "", "", "", ""}, "copper_inferno:rose_copper_pillar", 2,
				"Two stacked Rose Copper Bricks craft two fluted Rose Copper Pillars.",
				"Zwei gestapelte Ros\u00e9kupferziegel ergeben zwei kannelierte Ros\u00e9kupfers\u00e4ulen."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_pillar",
				"copper_inferno:burnished_copper_pillar", "copperdeco/burnished_copper_pillar",
				new String[]{"", BURN, "", "", BURN, "", "", "", ""}, "copper_inferno:burnished_copper_pillar", 2,
				"Two stacked Burnished Copper craft two fluted Burnished Copper Pillars.",
				"Zwei gestapeltes Poliertes Kupfer ergibt zwei kannelierte Polierte Kupfers\u00e4ulen."));
		HandbookEntries.add(new HandbookEntry("blocks", "verdigris_pillar",
				"copper_inferno:verdigris_pillar", "copperdeco/verdigris_pillar",
				new String[]{"", VERD, "", "", VERD, "", "", "", ""}, "copper_inferno:verdigris_pillar", 2,
				"Two stacked Verdigris Bricks craft two fluted Verdigris Pillars.",
				"Zwei gestapelte Gr\u00fcnspanziegel ergeben zwei kannelierte Gr\u00fcnspans\u00e4ulen."));

		// ----- stonecutting chains from vanilla copper_block / cut_copper
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_from_copper_block_stonecutting",
				BURN, "copperdeco/burnished_copper_from_copper_block_stonecutting",
				null, BURN, 4,
				"The stonecutter polishes one Copper Block into four Burnished Copper.",
				"Die Steins\u00e4ge poliert einen Kupferblock zu vier Poliertem Kupfer."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_from_cut_copper_stonecutting",
				BURN, "copperdeco/burnished_copper_from_cut_copper_stonecutting",
				null, BURN, 1,
				"The stonecutter also polishes Cut Copper into Burnished Copper one for one.",
				"Die Steins\u00e4ge poliert auch geschnittenes Kupfer eins zu eins zu Poliertem Kupfer."));
		HandbookEntries.add(new HandbookEntry("blocks", "copper_panels_from_copper_block_stonecutting",
				PANEL, "copperdeco/copper_panels_from_copper_block_stonecutting",
				null, PANEL, 4,
				"The stonecutter cuts one Copper Block into four riveted Copper Panels.",
				"Die Steins\u00e4ge schneidet einen Kupferblock zu vier vernieteten Kupferpaneelen."));
		HandbookEntries.add(new HandbookEntry("blocks", "copper_panels_from_cut_copper_stonecutting",
				PANEL, "copperdeco/copper_panels_from_cut_copper_stonecutting",
				null, PANEL, 1,
				"The stonecutter cuts Cut Copper into Copper Panels one for one.",
				"Die Steins\u00e4ge schneidet geschnittenes Kupfer eins zu eins zu Kupferpaneelen."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_bricks_from_copper_block_stonecutting",
				BURNB, "copperdeco/burnished_copper_bricks_from_copper_block_stonecutting",
				null, BURNB, 4,
				"The stonecutter cuts one Copper Block straight into four Burnished Copper Bricks.",
				"Die Steins\u00e4ge schneidet einen Kupferblock direkt zu vier Polierten Kupferziegeln."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_bricks_from_burnished_copper_stonecutting",
				BURNB, "copperdeco/burnished_copper_bricks_from_burnished_copper_stonecutting",
				null, BURNB, 1,
				"The stonecutter cuts Burnished Copper into Burnished Copper Bricks one for one.",
				"Die Steins\u00e4ge schneidet Poliertes Kupfer eins zu eins zu Polierten Kupferziegeln."));
		HandbookEntries.add(new HandbookEntry("blocks", "chiseled_rose_copper_bricks_from_rose_copper_bricks_stonecutting",
				"copper_inferno:chiseled_rose_copper_bricks", "copperdeco/chiseled_rose_copper_bricks_from_rose_copper_bricks_stonecutting",
				null, "copper_inferno:chiseled_rose_copper_bricks", 1,
				"The stonecutter chisels Rose Copper Bricks one for one.",
				"Die Steins\u00e4ge mei\u00dfelt Ros\u00e9kupferziegel eins zu eins."));
		HandbookEntries.add(new HandbookEntry("blocks", "rose_copper_pillar_from_rose_copper_bricks_stonecutting",
				"copper_inferno:rose_copper_pillar", "copperdeco/rose_copper_pillar_from_rose_copper_bricks_stonecutting",
				null, "copper_inferno:rose_copper_pillar", 1,
				"The stonecutter turns Rose Copper Bricks into Rose Copper Pillars one for one.",
				"Die Steins\u00e4ge macht aus Ros\u00e9kupferziegeln eins zu eins Ros\u00e9kupfers\u00e4ulen."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_pillar_from_burnished_copper_stonecutting",
				"copper_inferno:burnished_copper_pillar", "copperdeco/burnished_copper_pillar_from_burnished_copper_stonecutting",
				null, "copper_inferno:burnished_copper_pillar", 1,
				"The stonecutter turns Burnished Copper into Burnished Copper Pillars one for one.",
				"Die Steins\u00e4ge macht aus Poliertem Kupfer eins zu eins Polierte Kupfers\u00e4ulen."));
		HandbookEntries.add(new HandbookEntry("blocks", "verdigris_pillar_from_verdigris_bricks_stonecutting",
				"copper_inferno:verdigris_pillar", "copperdeco/verdigris_pillar_from_verdigris_bricks_stonecutting",
				null, "copper_inferno:verdigris_pillar", 1,
				"The stonecutter turns Verdigris Bricks into Verdigris Pillars one for one.",
				"Die Steins\u00e4ge macht aus Gr\u00fcnspanziegeln eins zu eins Gr\u00fcnspans\u00e4ulen."));

		// ----- rose copper brick shapes
		HandbookEntries.add(new HandbookEntry("blocks", "rose_copper_brick_slab",
				"copper_inferno:rose_copper_brick_slab", "copperdeco/rose_copper_brick_slab",
				new String[]{"", "", "", "", "", "", ROSE, ROSE, ROSE}, "copper_inferno:rose_copper_brick_slab", 6,
				"Three Rose Copper Bricks craft six slabs.",
				"Drei Ros\u00e9kupferziegel ergeben sechs Stufen."));
		HandbookEntries.add(new HandbookEntry("blocks", "rose_copper_brick_stairs",
				"copper_inferno:rose_copper_brick_stairs", "copperdeco/rose_copper_brick_stairs",
				new String[]{ROSE, "", "", ROSE, ROSE, "", ROSE, ROSE, ROSE}, "copper_inferno:rose_copper_brick_stairs", 4,
				"Six Rose Copper Bricks arranged as steps craft four stairs.",
				"Sechs Ros\u00e9kupferziegel in Treppenform ergeben vier Treppen."));
		HandbookEntries.add(new HandbookEntry("blocks", "rose_copper_brick_wall",
				"copper_inferno:rose_copper_brick_wall", "copperdeco/rose_copper_brick_wall",
				new String[]{"", "", "", ROSE, ROSE, ROSE, ROSE, ROSE, ROSE}, "copper_inferno:rose_copper_brick_wall", 6,
				"Six Rose Copper Bricks craft six walls.",
				"Sechs Ros\u00e9kupferziegel ergeben sechs Mauern."));

		// ----- burnished copper shapes
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_slab",
				"copper_inferno:burnished_copper_slab", "copperdeco/burnished_copper_slab",
				new String[]{"", "", "", "", "", "", BURN, BURN, BURN}, "copper_inferno:burnished_copper_slab", 6,
				"Three Burnished Copper craft six slabs.",
				"Drei Poliertes Kupfer ergeben sechs Stufen."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_stairs",
				"copper_inferno:burnished_copper_stairs", "copperdeco/burnished_copper_stairs",
				new String[]{BURN, "", "", BURN, BURN, "", BURN, BURN, BURN}, "copper_inferno:burnished_copper_stairs", 4,
				"Six Burnished Copper arranged as steps craft four stairs.",
				"Sechs Poliertes Kupfer in Treppenform ergeben vier Treppen."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_wall",
				"copper_inferno:burnished_copper_wall", "copperdeco/burnished_copper_wall",
				new String[]{"", "", "", BURN, BURN, BURN, BURN, BURN, BURN}, "copper_inferno:burnished_copper_wall", 6,
				"Six Burnished Copper craft six walls.",
				"Sechs Poliertes Kupfer ergeben sechs Mauern."));

		// ----- burnished copper brick shapes
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_brick_slab",
				"copper_inferno:burnished_copper_brick_slab", "copperdeco/burnished_copper_brick_slab",
				new String[]{"", "", "", "", "", "", BURNB, BURNB, BURNB}, "copper_inferno:burnished_copper_brick_slab", 6,
				"Three Burnished Copper Bricks craft six slabs.",
				"Drei Polierte Kupferziegel ergeben sechs Stufen."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_brick_stairs",
				"copper_inferno:burnished_copper_brick_stairs", "copperdeco/burnished_copper_brick_stairs",
				new String[]{BURNB, "", "", BURNB, BURNB, "", BURNB, BURNB, BURNB}, "copper_inferno:burnished_copper_brick_stairs", 4,
				"Six Burnished Copper Bricks arranged as steps craft four stairs.",
				"Sechs Polierte Kupferziegel in Treppenform ergeben vier Treppen."));
		HandbookEntries.add(new HandbookEntry("blocks", "burnished_copper_brick_wall",
				"copper_inferno:burnished_copper_brick_wall", "copperdeco/burnished_copper_brick_wall",
				new String[]{"", "", "", BURNB, BURNB, BURNB, BURNB, BURNB, BURNB}, "copper_inferno:burnished_copper_brick_wall", 6,
				"Six Burnished Copper Bricks craft six walls.",
				"Sechs Polierte Kupferziegel ergeben sechs Mauern."));

		// ----- copper panel shapes
		HandbookEntries.add(new HandbookEntry("blocks", "copper_panel_slab",
				"copper_inferno:copper_panel_slab", "copperdeco/copper_panel_slab",
				new String[]{"", "", "", "", "", "", PANEL, PANEL, PANEL}, "copper_inferno:copper_panel_slab", 6,
				"Three Copper Panels craft six slabs.",
				"Drei Kupferpaneele ergeben sechs Stufen."));
		HandbookEntries.add(new HandbookEntry("blocks", "copper_panel_stairs",
				"copper_inferno:copper_panel_stairs", "copperdeco/copper_panel_stairs",
				new String[]{PANEL, "", "", PANEL, PANEL, "", PANEL, PANEL, PANEL}, "copper_inferno:copper_panel_stairs", 4,
				"Six Copper Panels arranged as steps craft four stairs.",
				"Sechs Kupferpaneele in Treppenform ergeben vier Treppen."));
		HandbookEntries.add(new HandbookEntry("blocks", "copper_panel_wall",
				"copper_inferno:copper_panel_wall", "copperdeco/copper_panel_wall",
				new String[]{"", "", "", PANEL, PANEL, PANEL, PANEL, PANEL, PANEL}, "copper_inferno:copper_panel_wall", 6,
				"Six Copper Panels craft six walls.",
				"Sechs Kupferpaneele ergeben sechs Mauern."));

		// ----- gilded copper brick shapes
		HandbookEntries.add(new HandbookEntry("blocks", "gilded_copper_brick_slab",
				"copper_inferno:gilded_copper_brick_slab", "copperdeco/gilded_copper_brick_slab",
				new String[]{"", "", "", "", "", "", GILD, GILD, GILD}, "copper_inferno:gilded_copper_brick_slab", 6,
				"Three Gilded Copper Bricks craft six slabs.",
				"Drei Vergoldete Kupferziegel ergeben sechs Stufen."));
		HandbookEntries.add(new HandbookEntry("blocks", "gilded_copper_brick_stairs",
				"copper_inferno:gilded_copper_brick_stairs", "copperdeco/gilded_copper_brick_stairs",
				new String[]{GILD, "", "", GILD, GILD, "", GILD, GILD, GILD}, "copper_inferno:gilded_copper_brick_stairs", 4,
				"Six Gilded Copper Bricks arranged as steps craft four stairs.",
				"Sechs Vergoldete Kupferziegel in Treppenform ergeben vier Treppen."));
		HandbookEntries.add(new HandbookEntry("blocks", "gilded_copper_brick_wall",
				"copper_inferno:gilded_copper_brick_wall", "copperdeco/gilded_copper_brick_wall",
				new String[]{"", "", "", GILD, GILD, GILD, GILD, GILD, GILD}, "copper_inferno:gilded_copper_brick_wall", 6,
				"Six Gilded Copper Bricks craft six walls.",
				"Sechs Vergoldete Kupferziegel ergeben sechs Mauern."));

		// ----- verdigris brick shapes
		HandbookEntries.add(new HandbookEntry("blocks", "verdigris_brick_slab",
				"copper_inferno:verdigris_brick_slab", "copperdeco/verdigris_brick_slab",
				new String[]{"", "", "", "", "", "", VERD, VERD, VERD}, "copper_inferno:verdigris_brick_slab", 6,
				"Three Verdigris Bricks craft six slabs.",
				"Drei Gr\u00fcnspanziegel ergeben sechs Stufen."));
		HandbookEntries.add(new HandbookEntry("blocks", "verdigris_brick_stairs",
				"copper_inferno:verdigris_brick_stairs", "copperdeco/verdigris_brick_stairs",
				new String[]{VERD, "", "", VERD, VERD, "", VERD, VERD, VERD}, "copper_inferno:verdigris_brick_stairs", 4,
				"Six Verdigris Bricks arranged as steps craft four stairs.",
				"Sechs Gr\u00fcnspanziegel in Treppenform ergeben vier Treppen."));
		HandbookEntries.add(new HandbookEntry("blocks", "verdigris_brick_wall",
				"copper_inferno:verdigris_brick_wall", "copperdeco/verdigris_brick_wall",
				new String[]{"", "", "", VERD, VERD, VERD, VERD, VERD, VERD}, "copper_inferno:verdigris_brick_wall", 6,
				"Six Verdigris Bricks craft six walls.",
				"Sechs Gr\u00fcnspanziegel ergeben sechs Mauern."));
	}
}
