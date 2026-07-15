package net.sonic0810.kupferbienen.core;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.sonic0810.kupferbienen.Kupferbienen;

/**
 * The mod's single creative tab. Features add their entries via
 * {@code ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY)}.
 */
public final class ModCreativeTab {
	private ModCreativeTab() {
	}

	public static final RegistryKey<ItemGroup> MAIN_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Kupferbienen.id("main"));

	public static final ItemGroup MAIN = Registry.register(
			Registries.ITEM_GROUP,
			MAIN_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.kupferbienen.main"))
					.icon(iconFor("kupferwabe"))
					.build());

	/**
	 * Registration-order-safe icon supplier: the tab is registered by
	 * {@code ModCreativeTab.init()} BEFORE any feature item exists, so the icon item is
	 * resolved from the registry lazily inside the supplier. {@code ItemGroup.getIcon()} only
	 * invokes the supplier on first render, long after all features have registered.
	 * {@code Registries.ITEM} is a {@code DefaultedRegistry}, so {@code get(Identifier)}
	 * returns {@code minecraft:air} (never null) for a missing id.
	 */
	private static Supplier<ItemStack> iconFor(String path) {
		return () -> {
			Item item = Registries.ITEM.get(Kupferbienen.id(path));
			return new ItemStack(item);
		};
	}

	public static void init() {
		// Forces static initialization; tab registration happens in the field initializers.
	}
}
