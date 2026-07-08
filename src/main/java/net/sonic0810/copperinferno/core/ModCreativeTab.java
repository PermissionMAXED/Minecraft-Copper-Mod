package net.sonic0810.copperinferno.core;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * The mod's creative tabs. Features add their entries via
 * {@code ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.<TAB>_KEY)}:
 * MAIN (gadgets/materials/music/statue/fx + the handbook first), EQUIPMENT (armor/tools/
 * gear/charms), FOODS (foods + drinks), MOBS (spawn eggs/summon items/mob drops), NATURE
 * (worldgen blocks), SETS (the v4 mega block families + forge parts) and BLOCKS (the
 * remaining building blocks).
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
					.icon(iconFor("dr_pepper"))
					.build());

	public static final RegistryKey<ItemGroup> BLOCKS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, CopperInferno.id("blocks"));

	public static final ItemGroup BLOCKS = Registry.register(
			Registries.ITEM_GROUP,
			BLOCKS_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.copper_inferno.blocks"))
					.icon(iconFor("copper_bricks"))
					.build());

	public static final RegistryKey<ItemGroup> EQUIPMENT_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, CopperInferno.id("equipment"));

	public static final ItemGroup EQUIPMENT = Registry.register(
			Registries.ITEM_GROUP,
			EQUIPMENT_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.copper_inferno.equipment"))
					.icon(iconFor("infernium_sword"))
					.build());

	public static final RegistryKey<ItemGroup> FOODS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, CopperInferno.id("foods"));

	public static final ItemGroup FOODS = Registry.register(
			Registries.ITEM_GROUP,
			FOODS_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.copper_inferno.foods"))
					.icon(iconFor("ember_burger"))
					.build());

	public static final RegistryKey<ItemGroup> MOBS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, CopperInferno.id("mobs"));

	public static final ItemGroup MOBS = Registry.register(
			Registries.ITEM_GROUP,
			MOBS_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.copper_inferno.mobs"))
					.icon(iconFor("ash_archer_spawn_egg"))
					.build());

	public static final RegistryKey<ItemGroup> NATURE_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, CopperInferno.id("nature"));

	public static final ItemGroup NATURE = Registry.register(
			Registries.ITEM_GROUP,
			NATURE_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.copper_inferno.nature"))
					.icon(iconFor("smolder_bloom"))
					.build());

	public static final RegistryKey<ItemGroup> SETS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, CopperInferno.id("block_sets"));

	public static final ItemGroup SETS = Registry.register(
			Registries.ITEM_GROUP,
			SETS_KEY,
			FabricItemGroup.builder()
					.displayName(Text.translatable("itemGroup.copper_inferno.block_sets"))
					.icon(iconFor("pyrestone_bricks"))
					.build());

	/**
	 * Registration-order-safe icon supplier: the tabs are registered by
	 * {@code CopperInfernoCore.init()} BEFORE any feature item exists, so the icon item is
	 * resolved from the registry lazily inside the supplier. {@code ItemGroup.getIcon()} only
	 * invokes the supplier on first render (verified via bytecode: the {@code icon} field is
	 * null-checked, then {@code iconSupplier.get()} is cached), long after all features have
	 * registered. {@code Registries.ITEM} is a {@code DefaultedRegistry}, so
	 * {@code get(Identifier)} returns {@code minecraft:air} (never null) for a missing id.
	 */
	private static Supplier<ItemStack> iconFor(String path) {
		return () -> {
			Item item = Registries.ITEM.get(CopperInferno.id(path));
			return new ItemStack(item);
		};
	}

	public static void init() {
		// Forces static initialization; tab registration happens in the field initializers.

		// The handbook must be the FIRST entry of the MAIN tab. This callback is
		// registered here (CopperInfernoCore.init() runs before every feature init, and
		// Fabric runs modify-entries callbacks in registration order), so it fires before
		// all feature callbacks. The item itself is only registered later by
		// HandbookFeature.init(), hence the lazy registry lookup (Registries.ITEM is a
		// DefaultedRegistry, so a missing id would yield minecraft:air, never null).
		ItemGroupEvents.modifyEntriesEvent(MAIN_KEY).register(entries ->
				entries.add(Registries.ITEM.get(CopperInferno.id("copper_inferno_handbook"))));
	}
}
