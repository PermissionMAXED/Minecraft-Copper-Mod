package net.sonic0810.copperinferno.feature.glasslight;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;

/**
 * Glass & light feature: copper glass (4 oxidation stages), copper glass panes, redstone-driven
 * copper lamps (4 stages), two lanterns, a copper chandelier and a glowing syrup block.
 *
 * <p>All blocks drop themselves unconditionally (deliberate: no silk-touch requirement, even for
 * the glass blocks — see the generated loot tables).
 */
public final class GlassLightFeature {
	private GlassLightFeature() {
	}

	// Glass cubes (TransparentBlock; ctor is protected -> CopperGlassBlock subclass).
	public static Block COPPER_GLASS;
	public static Block EXPOSED_COPPER_GLASS;
	public static Block WEATHERED_COPPER_GLASS;
	public static Block OXIDIZED_COPPER_GLASS;

	// Glass panes (PaneBlock; ctor is protected -> CopperGlassPaneBlock subclass).
	public static Block COPPER_GLASS_PANE;
	public static Block OXIDIZED_COPPER_GLASS_PANE;

	// Redstone lamps (RedstoneLampBlock has a public ctor in 1.21.9).
	public static Block COPPER_LAMP;
	public static Block EXPOSED_COPPER_LAMP;
	public static Block WEATHERED_COPPER_LAMP;
	public static Block OXIDIZED_COPPER_LAMP;

	// Lanterns (LanternBlock has a public ctor in 1.21.9).
	public static Block INFERNO_LANTERN;
	public static Block SODA_LANTERN;
	public static Block COPPER_CHANDELIER;

	// Plain glowing block.
	public static Block GLOWING_SYRUP_BLOCK;

	/** Fresh settings per block: ModBlocks.register writes a registry key into the instance. */
	private static AbstractBlock.Settings glassSettings() {
		return AbstractBlock.Settings.create()
				.strength(0.4F)
				.sounds(BlockSoundGroup.GLASS)
				.nonOpaque();
	}

	/** Mirrors vanilla REDSTONE_LAMP: luminance 15 when LIT, glass sounds, strength 0.3. */
	private static AbstractBlock.Settings lampSettings() {
		return AbstractBlock.Settings.create()
				.luminance(state -> state.get(Properties.LIT) ? 15 : 0)
				.strength(0.3F)
				.sounds(BlockSoundGroup.GLASS);
	}

	private static AbstractBlock.Settings lanternSettings() {
		return AbstractBlock.Settings.create()
				.luminance(state -> 15)
				.nonOpaque()
				.strength(3.5F)
				.sounds(BlockSoundGroup.LANTERN);
	}

	public static void init() {
		COPPER_GLASS = ModBlocks.register("copper_glass", CopperGlassBlock::new, glassSettings(), true);
		EXPOSED_COPPER_GLASS = ModBlocks.register("exposed_copper_glass", CopperGlassBlock::new, glassSettings(), true);
		WEATHERED_COPPER_GLASS = ModBlocks.register("weathered_copper_glass", CopperGlassBlock::new, glassSettings(), true);
		OXIDIZED_COPPER_GLASS = ModBlocks.register("oxidized_copper_glass", CopperGlassBlock::new, glassSettings(), true);

		COPPER_GLASS_PANE = ModBlocks.register("copper_glass_pane", CopperGlassPaneBlock::new, glassSettings(), true);
		OXIDIZED_COPPER_GLASS_PANE = ModBlocks.register("oxidized_copper_glass_pane", CopperGlassPaneBlock::new, glassSettings(), true);

		COPPER_LAMP = ModBlocks.register("copper_lamp", RedstoneLampBlock::new, lampSettings(), true);
		EXPOSED_COPPER_LAMP = ModBlocks.register("exposed_copper_lamp", RedstoneLampBlock::new, lampSettings(), true);
		WEATHERED_COPPER_LAMP = ModBlocks.register("weathered_copper_lamp", RedstoneLampBlock::new, lampSettings(), true);
		OXIDIZED_COPPER_LAMP = ModBlocks.register("oxidized_copper_lamp", RedstoneLampBlock::new, lampSettings(), true);

		INFERNO_LANTERN = ModBlocks.register("inferno_lantern", LanternBlock::new, lanternSettings(), true);
		SODA_LANTERN = ModBlocks.register("soda_lantern", LanternBlock::new, lanternSettings(), true);
		COPPER_CHANDELIER = ModBlocks.register("copper_chandelier", LanternBlock::new, lanternSettings(), true);

		GLOWING_SYRUP_BLOCK = ModBlocks.register(
				"glowing_syrup_block",
				Block::new,
				AbstractBlock.Settings.create()
						.luminance(state -> 10)
						.strength(1.0F)
						.sounds(BlockSoundGroup.HONEY),
				true);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			entries.add(COPPER_GLASS);
			entries.add(EXPOSED_COPPER_GLASS);
			entries.add(WEATHERED_COPPER_GLASS);
			entries.add(OXIDIZED_COPPER_GLASS);
			entries.add(COPPER_GLASS_PANE);
			entries.add(OXIDIZED_COPPER_GLASS_PANE);
			entries.add(COPPER_LAMP);
			entries.add(EXPOSED_COPPER_LAMP);
			entries.add(WEATHERED_COPPER_LAMP);
			entries.add(OXIDIZED_COPPER_LAMP);
			entries.add(INFERNO_LANTERN);
			entries.add(SODA_LANTERN);
			entries.add(COPPER_CHANDELIER);
			entries.add(GLOWING_SYRUP_BLOCK);
		});
	}
}
