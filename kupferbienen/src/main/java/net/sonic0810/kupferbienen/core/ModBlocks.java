package net.sonic0810.kupferbienen.core;

import java.util.function.Function;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.sonic0810.kupferbienen.Kupferbienen;

/**
 * Block registration helper. Block fields live in the individual feature classes.
 */
public final class ModBlocks {
	private ModBlocks() {
	}

	/**
	 * Registers a block under {@code kupferbienen:<path>}. The registry key is applied to the
	 * settings automatically (required in 1.21.x) before the factory is invoked. When
	 * {@code withBlockItem} is true, a plain {@link BlockItem} is registered under the same id
	 * using the block-prefixed translation key ({@code block.kupferbienen.<path>}).
	 */
	public static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, boolean withBlockItem) {
		RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Kupferbienen.id(path));
		Block block = factory.apply(settings.registryKey(key));
		Registry.register(Registries.BLOCK, key, block);
		if (withBlockItem) {
			RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Kupferbienen.id(path));
			Registry.register(Registries.ITEM, itemKey,
					new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey()));
		}
		return block;
	}
}
