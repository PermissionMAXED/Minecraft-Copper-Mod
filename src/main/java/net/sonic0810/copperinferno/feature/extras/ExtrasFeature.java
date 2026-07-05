package net.sonic0810.copperinferno.feature.extras;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModItems;

/**
 * Extra content (copper horn, fizz bomb, Minecraft Inferno, ...). Content is registered by the
 * extras feature worker.
 */
public final class ExtrasFeature {
	private ExtrasFeature() {
	}

	/** Display-only trophy; intentionally has no recipe. */
	public static Item MINECRAFT_INFERNO;
	public static Item KATZENLIEBHABER737_CHARM;
	public static Item COPPER_COIN;
	public static Item COPPER_LIGHTNING_CHARM;
	public static Item FIZZ_BOMB;
	public static Item COPPER_HORN;
	/** Decorative full-cube can block; registered with a BlockItem. */
	public static Block DR_PEPPER_CAN_BLOCK;

	public static void init() {
		MINECRAFT_INFERNO = ModItems.register("minecraft_inferno", MinecraftInfernoItem::new,
				new Item.Settings().rarity(Rarity.EPIC).fireproof());

		KATZENLIEBHABER737_CHARM = ModItems.register("katzenliebhaber737_charm", CatCharmItem::new,
				new Item.Settings().maxCount(1));

		COPPER_COIN = ModItems.register("copper_coin", Item::new,
				new Item.Settings().maxCount(64));

		COPPER_LIGHTNING_CHARM = ModItems.register("copper_lightning_charm", CopperLightningCharmItem::new,
				new Item.Settings().maxDamage(16));

		FIZZ_BOMB = ModItems.register("fizz_bomb", FizzBombItem::new,
				new Item.Settings().maxCount(16));

		COPPER_HORN = ModItems.register("copper_horn", CopperHornItem::new,
				new Item.Settings().maxCount(1));

		DR_PEPPER_CAN_BLOCK = ModBlocks.register("dr_pepper_can_block", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.DARK_RED)
						.strength(1.0F, 6.0F)
						.sounds(BlockSoundGroup.METAL),
				true);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			entries.add(MINECRAFT_INFERNO);
			entries.add(KATZENLIEBHABER737_CHARM);
			entries.add(COPPER_COIN);
			entries.add(COPPER_LIGHTNING_CHARM);
			entries.add(FIZZ_BOMB);
			entries.add(COPPER_HORN);
			entries.add(DR_PEPPER_CAN_BLOCK);
		});
	}
}
