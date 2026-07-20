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
 * The mod's creative tabs. Features add their entries via
 * {@code ItemGroupEvents.modifyEntriesEvent(<key>)} against one of the three keys:
 * <ul>
 * <li>{@link #BIENEN_KEY} — bees, blooms, apiaries and their produce (BeesFeature, FloraFeature).</li>
 * <li>{@link #ALCHEMIE_KEY} — the oxidation brewing chain (PotionsFeature).</li>
 * <li>{@link #MASCHINEN_KEY} — machinery blocks (MachinesFeature).</li>
 * </ul>
 */
public final class ModCreativeTab {
	private ModCreativeTab() {
	}

	public static final RegistryKey<ItemGroup> BIENEN_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Kupferbienen.id("bienen"));
	public static final RegistryKey<ItemGroup> ALCHEMIE_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Kupferbienen.id("alchemie"));
	public static final RegistryKey<ItemGroup> MASCHINEN_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Kupferbienen.id("maschinen"));

	public static final ItemGroup BIENEN = Registry.register(
			Registries.ITEM_GROUP,
			BIENEN_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.kupferbienen.bienen"))
					.icon(iconFor("kupferwabe"))
					.build());

	public static final ItemGroup ALCHEMIE = Registry.register(
			Registries.ITEM_GROUP,
			ALCHEMIE_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.kupferbienen.alchemie"))
					.icon(iconFor("trank_der_oxidation"))
					.build());

	public static final ItemGroup MASCHINEN = Registry.register(
			Registries.ITEM_GROUP,
			MASCHINEN_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.kupferbienen.maschinen"))
					.icon(iconFor("wabenpresse"))
					.build());

	/**
	 * Registration-order-safe icon supplier: the tabs are registered by
	 * {@code ModCreativeTab.init()} BEFORE any feature item exists, so the icon item is
	 * resolved from the registry lazily inside the supplier. {@code ItemGroup.getIcon()} only
	 * invokes the supplier on first render, long after all features have registered.
	 * {@code Registries.ITEM} is a {@code DefaultedRegistry}, so {@code get(Identifier)}
	 * returns {@code minecraft:air} (never null) for a missing id — the MASCHINEN icon
	 * ({@code wabenpresse}) tolerates the item not being registered yet.
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
