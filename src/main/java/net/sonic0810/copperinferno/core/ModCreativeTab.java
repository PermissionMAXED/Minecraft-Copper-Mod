package net.sonic0810.copperinferno.core;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * The mod's creative tab. Features add their entries via
 * {@code ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY)}.
 */
public final class ModCreativeTab {
	private ModCreativeTab() {
	}

	public static final RegistryKey<ItemGroup> MAIN_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, CopperInferno.id("main"));

	public static final ItemGroup MAIN = Registry.register(
			Registries.ITEM_GROUP,
			MAIN_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.copper_inferno.main"))
					.icon(() -> new ItemStack(Items.COPPER_INGOT))
					.build());

	public static final RegistryKey<ItemGroup> BLOCKS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, CopperInferno.id("blocks"));

	public static final ItemGroup BLOCKS = Registry.register(
			Registries.ITEM_GROUP,
			BLOCKS_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.copper_inferno.blocks"))
					.icon(() -> new ItemStack(Items.CUT_COPPER))
					.build());

	public static void init() {
		// Forces static initialization; registration happens in the field initializers.
	}
}
