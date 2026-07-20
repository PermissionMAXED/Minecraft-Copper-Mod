package net.sonic0810.kupferbienen.feature.flora;

import com.mojang.serialization.MapCodec;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.sonic0810.kupferbienen.Kupferbienen;
import net.sonic0810.kupferbienen.core.ModBlocks;
import net.sonic0810.kupferbienen.core.ModCreativeTab;
import net.sonic0810.kupferbienen.core.ModFeatures;
import net.sonic0810.kupferbienen.core.ModItems;
import net.sonic0810.kupferbienen.feature.bees.BeesFeature;

/**
 * Copper flora: five bee-attractive flowers, four-stage Kupferklee and their worldgen.
 */
public final class FloraFeature {
	private static final String[] PATCH_FEATURES = {
			"kupferbluete_patch",
			"wachsblume_patch",
			"blitzblume_patch",
			"honigkelch_patch",
			"gruenspanroeschen_patch",
			"tiefenglocke_patch"
	};

	public static Block WACHSBLUME;
	public static Block HONIGKELCH;
	public static Block BLITZBLUME;
	public static Block GRUENSPANROESCHEN;
	public static Block TIEFENGLOCKE;
	public static Block KUPFERKLEE;

	public static Item KUPFERKLEE_SAMEN;
	public static Item KUPFERKLEEBLATT;

	public static Feature<DefaultFeatureConfig> KUPFERNEST_BLOB;

	private FloraFeature() {
	}

	public static void init() {
		registerBlocks();
		registerItems();
		registerWorldgen();
	}

	private static void registerBlocks() {
		AbstractBlock.Settings flowerSettings = AbstractBlock.Settings.create()
				.noCollision()
				.breakInstantly()
				.sounds(BlockSoundGroup.GRASS);
		WACHSBLUME = ModBlocks.register("wachsblume", DirtFlowerBlock::new, flowerSettings,
				"tooltip.kupferbienen.wachsblume");
		HONIGKELCH = ModBlocks.register("honigkelch", DirtFlowerBlock::new,
				AbstractBlock.Settings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS),
				"tooltip.kupferbienen.honigkelch");
		BLITZBLUME = ModBlocks.register("blitzblume", BlitzblumeBlock::new,
				AbstractBlock.Settings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS),
				"tooltip.kupferbienen.blitzblume");
		GRUENSPANROESCHEN = ModBlocks.register("gruenspanroeschen", GruenspanroeschenBlock::new,
				AbstractBlock.Settings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS),
				"tooltip.kupferbienen.gruenspanroeschen");
		TIEFENGLOCKE = ModBlocks.register("tiefenglocke", TiefenglockeBlock::new,
				AbstractBlock.Settings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS),
				"tooltip.kupferbienen.tiefenglocke");
		KUPFERKLEE = ModBlocks.register("kupferklee", KupferkleeCropBlock::new,
				AbstractBlock.Settings.create()
						.noCollision()
						.breakInstantly()
						.sounds(BlockSoundGroup.CROP),
				false);
	}

	private static void registerItems() {
		KUPFERKLEE_SAMEN = ModItems.register("kupferklee_samen",
				settings -> new BlockItem(KUPFERKLEE, settings.useItemPrefixedTranslationKey()),
				new Item.Settings());
		KUPFERKLEEBLATT = ModItems.register("kupferkleeblatt", Item::new, new Item.Settings());

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BIENEN_KEY).register(entries -> {
			entries.add(WACHSBLUME);
			entries.add(HONIGKELCH);
			entries.add(BLITZBLUME);
			entries.add(GRUENSPANROESCHEN);
			entries.add(TIEFENGLOCKE);
			entries.add(KUPFERKLEE_SAMEN);
			entries.add(KUPFERKLEEBLATT);
		});
	}

	private static void registerWorldgen() {
		KUPFERNEST_BLOB = ModFeatures.register("kupfernest_blob", new KupfernestBlobFeature());

		addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.MEADOW),
				PATCH_FEATURES[0]);
		addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS),
				PATCH_FEATURES[1]);
		addFeature(BiomeSelectors.includeByKey(
				BiomeKeys.STONY_PEAKS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.MEADOW),
				PATCH_FEATURES[2]);
		addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.PLAINS),
				PATCH_FEATURES[3]);
		addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), PATCH_FEATURES[4]);
		addFeature(BiomeSelectors.includeByKey(BiomeKeys.DRIPSTONE_CAVES), PATCH_FEATURES[5]);
		addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW, BiomeKeys.STONY_PEAKS),
				"kupfernest_blob");
	}

	private static void addFeature(
			java.util.function.Predicate<net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext> selector,
			String path) {
		RegistryKey<PlacedFeature> key = RegistryKey.of(
				RegistryKeys.PLACED_FEATURE, Kupferbienen.id(path));
		BiomeModifications.addFeature(selector, GenerationStep.Feature.VEGETAL_DECORATION, key);
	}

	private static class DirtFlowerBlock extends PlantBlock {
		private static final MapCodec<DirtFlowerBlock> CODEC = createCodec(DirtFlowerBlock::new);

		DirtFlowerBlock(Settings settings) {
			super(settings);
		}

		@Override
		protected MapCodec<? extends DirtFlowerBlock> getCodec() {
			return CODEC;
		}
	}

	private static final class BlitzblumeBlock extends PlantBlock {
		private static final MapCodec<BlitzblumeBlock> CODEC = createCodec(BlitzblumeBlock::new);

		BlitzblumeBlock(Settings settings) {
			super(settings);
		}

		@Override
		protected MapCodec<? extends BlitzblumeBlock> getCodec() {
			return CODEC;
		}

		@Override
		protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
			return super.canPlantOnTop(floor, world, pos)
					|| floor.isIn(BlockTags.BASE_STONE_OVERWORLD);
		}
	}

	private static final class GruenspanroeschenBlock extends PlantBlock {
		private static final MapCodec<GruenspanroeschenBlock> CODEC =
				createCodec(GruenspanroeschenBlock::new);

		GruenspanroeschenBlock(Settings settings) {
			super(settings);
		}

		@Override
		protected MapCodec<? extends GruenspanroeschenBlock> getCodec() {
			return CODEC;
		}

		@Override
		protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
			return super.canPlantOnTop(floor, world, pos) || floor.isOf(Blocks.MOSS_BLOCK);
		}
	}

	private static final class TiefenglockeBlock extends PlantBlock {
		private static final MapCodec<TiefenglockeBlock> CODEC = createCodec(TiefenglockeBlock::new);

		TiefenglockeBlock(Settings settings) {
			super(settings);
		}

		@Override
		protected MapCodec<? extends TiefenglockeBlock> getCodec() {
			return CODEC;
		}

		@Override
		protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
			return super.canPlantOnTop(floor, world, pos)
					|| floor.isIn(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
		}
	}

	private static final class KupferkleeCropBlock extends CropBlock {
		private static final MapCodec<KupferkleeCropBlock> CODEC =
				createCodec(KupferkleeCropBlock::new);
		private static final IntProperty AGE = Properties.AGE_3;

		KupferkleeCropBlock(Settings settings) {
			super(settings);
		}

		@Override
		public MapCodec<? extends KupferkleeCropBlock> getCodec() {
			return CODEC;
		}

		@Override
		protected IntProperty getAgeProperty() {
			return AGE;
		}

		@Override
		public int getMaxAge() {
			return 3;
		}

		@Override
		protected ItemConvertible getSeedsItem() {
			return KUPFERKLEE_SAMEN;
		}

		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
			builder.add(AGE);
		}
	}

	private static final class KupfernestBlobFeature extends Feature<DefaultFeatureConfig> {
		private static final BlockPos[] BOULDER_OFFSETS = {
				BlockPos.ORIGIN,
				BlockPos.ORIGIN.up(),
				BlockPos.ORIGIN.east(),
				BlockPos.ORIGIN.south(),
				BlockPos.ORIGIN.up().west()
		};
		private static final BlockPos[] FLOWER_OFFSETS = {
				BlockPos.ORIGIN.north(),
				BlockPos.ORIGIN.west(),
				BlockPos.ORIGIN.east(2),
				BlockPos.ORIGIN.south(2)
		};

		KupfernestBlobFeature() {
			super(DefaultFeatureConfig.CODEC);
		}

		@Override
		public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
			StructureWorldAccess world = context.getWorld();
			BlockPos origin = context.getOrigin();
			int boulderSize = 3 + context.getRandom().nextInt(3);

			for (int i = 0; i < boulderSize; i++) {
				BlockState state = (i == 0 || (i > 1 && context.getRandom().nextBoolean()))
						? Blocks.WEATHERED_COPPER.getDefaultState()
						: Blocks.OXIDIZED_COPPER.getDefaultState();
				world.setBlockState(origin.add(BOULDER_OFFSETS[i]), state, Block.NOTIFY_ALL);
			}

			int flowerCount = 2 + context.getRandom().nextInt(3);
			for (int i = 0; i < flowerCount; i++) {
				BlockPos flowerPos = origin.add(FLOWER_OFFSETS[i]);
				BlockPos supportPos = flowerPos.down();
				BlockState support = context.getRandom().nextBoolean()
						? Blocks.GRASS_BLOCK.getDefaultState()
						: Blocks.DIRT.getDefaultState();
				world.setBlockState(supportPos, support, Block.NOTIFY_ALL);
				world.setBlockState(flowerPos, BeesFeature.KUPFERBLUETE.getDefaultState(),
						Block.NOTIFY_ALL);
			}
			return true;
		}
	}
}
