package net.sonic0810.copperinferno.feature.utilityblocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.WoodType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;

/**
 * Utility blocks v2: 16 functional/redstone copper blocks.
 *
 * <ul>
 *   <li>4 copper buttons (one per oxidation stage) — {@link ButtonBlock} with
 *       {@link BlockSetType#COPPER} and 15 press ticks (snappier than stone's 20).</li>
 *   <li>4 copper pressure plates (one per stage) — {@link PressurePlateBlock} with
 *       {@link BlockSetType#COPPER}.</li>
 *   <li>copper_fence ({@link FenceBlock}) and copper_fence_gate ({@link FenceGateBlock} with a
 *       registered copper {@link WoodType}).</li>
 *   <li>4 copper crates (one per stage), copper_pipe ({@link PillarBlock}, axis-aligned like
 *       basalt) and copper_plating — plain cubes.</li>
 * </ul>
 *
 * <p>These are STATIC stage variants (no random-tick oxidation), matching the task spec.
 *
 * <p>The copper {@link WoodType} for the fence gate is created via Fabric's
 * {@link WoodTypeBuilder}{@code .register(...)} rather than the raw
 * {@code new WoodType("copper_inferno_copper", BlockSetType.COPPER)} constructor (both compile;
 * verified via javap): the builder registers the type in {@code WoodType.VALUES}, keeping
 * {@code FenceGateBlock}'s codec functional, and lets us swap the oak gate sounds for the vanilla
 * copper-door sounds.
 */
public final class UtilityBlocksFeature {
	private UtilityBlocksFeature() {
	}

	private static final String[] STAGE_PREFIXES = {"", "exposed_", "weathered_", "oxidized_"};
	/** Vanilla copper map colors per stage (same values the masonry feature uses). */
	private static final MapColor[] STAGE_MAP_COLORS = {
			MapColor.ORANGE, MapColor.TERRACOTTA_LIGHT_GRAY, MapColor.DARK_AQUA, MapColor.TEAL
	};

	/** {@link ButtonBlock}'s constructor is protected in 1.21.9; trivial public subclass. */
	public static class PublicButtonBlock extends ButtonBlock {
		public PublicButtonBlock(BlockSetType blockSetType, int pressTicks, AbstractBlock.Settings settings) {
			super(blockSetType, pressTicks, settings);
		}
	}

	/** {@link PressurePlateBlock}'s constructor is protected in 1.21.9; trivial public subclass. */
	public static class PublicPressurePlateBlock extends PressurePlateBlock {
		public PublicPressurePlateBlock(BlockSetType blockSetType, AbstractBlock.Settings settings) {
			super(blockSetType, settings);
		}
	}

	/** Copper wood type for the fence gate; registered so {@code WoodType.CODEC} can resolve it. */
	public static final WoodType COPPER_WOOD_TYPE = WoodTypeBuilder.copyOf(WoodType.OAK)
			.soundGroup(BlockSoundGroup.COPPER)
			.fenceGateOpenSound(SoundEvents.BLOCK_COPPER_DOOR_OPEN)
			.fenceGateCloseSound(SoundEvents.BLOCK_COPPER_DOOR_CLOSE)
			.register(CopperInferno.id("copper"), BlockSetType.COPPER);

	/** Indexed by oxidation stage: 0=unaffected, 1=exposed, 2=weathered, 3=oxidized. */
	public static final Block[] BUTTONS = new Block[4];
	public static final Block[] PRESSURE_PLATES = new Block[4];
	public static final Block[] CRATES = new Block[4];

	public static Block COPPER_FENCE;
	public static Block COPPER_FENCE_GATE;
	public static Block COPPER_PIPE;
	public static Block COPPER_PLATING;

	public static void init() {
		// A FRESH Settings instance per registration: ModBlocks.register writes the registry key
		// into the settings, so instances must never be shared.
		for (int i = 0; i < 4; i++) {
			String prefix = STAGE_PREFIXES[i];
			MapColor mapColor = STAGE_MAP_COLORS[i];

			BUTTONS[i] = ModBlocks.register(prefix + "copper_button",
					s -> new PublicButtonBlock(BlockSetType.COPPER, 15, s),
					AbstractBlock.Settings.create()
							.mapColor(mapColor)
							.noCollision()
							.strength(0.5F),
					true);

			PRESSURE_PLATES[i] = ModBlocks.register(prefix + "copper_pressure_plate",
					s -> new PublicPressurePlateBlock(BlockSetType.COPPER, s),
					AbstractBlock.Settings.create()
							.mapColor(mapColor)
							.noCollision()
							.strength(0.5F)
							.requiresTool(),
					true);

			CRATES[i] = ModBlocks.register(prefix + "copper_crate",
					Block::new,
					AbstractBlock.Settings.create()
							.mapColor(mapColor)
							.strength(2.5F)
							.sounds(BlockSoundGroup.COPPER),
					true);
		}

		COPPER_FENCE = ModBlocks.register("copper_fence",
				FenceBlock::new, copperSettings(), true);
		COPPER_FENCE_GATE = ModBlocks.register("copper_fence_gate",
				s -> new FenceGateBlock(COPPER_WOOD_TYPE, s), copperSettings(), true);
		COPPER_PIPE = ModBlocks.register("copper_pipe",
				PillarBlock::new, copperSettings(), true);
		COPPER_PLATING = ModBlocks.register("copper_plating",
				Block::new, copperSettings(), true);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			for (int i = 0; i < 4; i++) {
				entries.add(BUTTONS[i]);
			}
			for (int i = 0; i < 4; i++) {
				entries.add(PRESSURE_PLATES[i]);
			}
			entries.add(COPPER_FENCE);
			entries.add(COPPER_FENCE_GATE);
			for (int i = 0; i < 4; i++) {
				entries.add(CRATES[i]);
			}
			entries.add(COPPER_PIPE);
			entries.add(COPPER_PLATING);
		});
	}

	/** Vanilla copper-block-like settings (strength 3/6, tool required, copper sounds). */
	private static AbstractBlock.Settings copperSettings() {
		return AbstractBlock.Settings.create()
				.mapColor(MapColor.ORANGE)
				.strength(3.0F, 6.0F)
				.requiresTool()
				.sounds(BlockSoundGroup.COPPER);
	}
}
