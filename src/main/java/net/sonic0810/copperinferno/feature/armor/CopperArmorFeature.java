package net.sonic0810.copperinferno.feature.armor;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.oxidation.ItemOxidation;
import net.sonic0810.copperinferno.core.oxidation.OxidizableEquipmentItem;

/**
 * Oxidizing copper armor. Stage 0 of each chain is the VANILLA 1.21.9 copper piece
 * (Items.COPPER_HELMET etc.); this feature registers the exposed/weathered/oxidized
 * stages and hooks all four pieces into the shared {@link ItemOxidation} system
 * (oxidation over time, axe scraping and honeycomb waxing are handled centrally).
 *
 * <p>Worn 3D model stays the vanilla copper look for all stages (documented simplification).
 */
public final class CopperArmorFeature {
	private CopperArmorFeature() {
	}

	// Exposed (stage 1)
	public static final Item EXPOSED_COPPER_HELMET = registerPiece("exposed_copper_helmet", EquipmentType.HELMET);
	public static final Item EXPOSED_COPPER_CHESTPLATE = registerPiece("exposed_copper_chestplate", EquipmentType.CHESTPLATE);
	public static final Item EXPOSED_COPPER_LEGGINGS = registerPiece("exposed_copper_leggings", EquipmentType.LEGGINGS);
	public static final Item EXPOSED_COPPER_BOOTS = registerPiece("exposed_copper_boots", EquipmentType.BOOTS);

	// Weathered (stage 2)
	public static final Item WEATHERED_COPPER_HELMET = registerPiece("weathered_copper_helmet", EquipmentType.HELMET);
	public static final Item WEATHERED_COPPER_CHESTPLATE = registerPiece("weathered_copper_chestplate", EquipmentType.CHESTPLATE);
	public static final Item WEATHERED_COPPER_LEGGINGS = registerPiece("weathered_copper_leggings", EquipmentType.LEGGINGS);
	public static final Item WEATHERED_COPPER_BOOTS = registerPiece("weathered_copper_boots", EquipmentType.BOOTS);

	// Oxidized (stage 3)
	public static final Item OXIDIZED_COPPER_HELMET = registerPiece("oxidized_copper_helmet", EquipmentType.HELMET);
	public static final Item OXIDIZED_COPPER_CHESTPLATE = registerPiece("oxidized_copper_chestplate", EquipmentType.CHESTPLATE);
	public static final Item OXIDIZED_COPPER_LEGGINGS = registerPiece("oxidized_copper_leggings", EquipmentType.LEGGINGS);
	public static final Item OXIDIZED_COPPER_BOOTS = registerPiece("oxidized_copper_boots", EquipmentType.BOOTS);

	private static Item registerPiece(String path, EquipmentType type) {
		return ModItems.register(path, OxidizableEquipmentItem::new,
				new Item.Settings().armor(ArmorMaterials.COPPER, type));
	}

	public static void init() {
		// Stage 0 of each chain is the vanilla copper piece.
		ItemOxidation.registerChain(Items.COPPER_HELMET, EXPOSED_COPPER_HELMET, WEATHERED_COPPER_HELMET, OXIDIZED_COPPER_HELMET);
		ItemOxidation.registerChain(Items.COPPER_CHESTPLATE, EXPOSED_COPPER_CHESTPLATE, WEATHERED_COPPER_CHESTPLATE, OXIDIZED_COPPER_CHESTPLATE);
		ItemOxidation.registerChain(Items.COPPER_LEGGINGS, EXPOSED_COPPER_LEGGINGS, WEATHERED_COPPER_LEGGINGS, OXIDIZED_COPPER_LEGGINGS);
		ItemOxidation.registerChain(Items.COPPER_BOOTS, EXPOSED_COPPER_BOOTS, WEATHERED_COPPER_BOOTS, OXIDIZED_COPPER_BOOTS);

		// Stage-major order (a full set per stage), starting with the vanilla stage-0
		// pieces so each oxidation chain reads fully: vanilla -> exposed -> weathered -> oxidized.
		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			entries.add(Items.COPPER_HELMET);
			entries.add(Items.COPPER_CHESTPLATE);
			entries.add(Items.COPPER_LEGGINGS);
			entries.add(Items.COPPER_BOOTS);
			entries.add(EXPOSED_COPPER_HELMET);
			entries.add(EXPOSED_COPPER_CHESTPLATE);
			entries.add(EXPOSED_COPPER_LEGGINGS);
			entries.add(EXPOSED_COPPER_BOOTS);
			entries.add(WEATHERED_COPPER_HELMET);
			entries.add(WEATHERED_COPPER_CHESTPLATE);
			entries.add(WEATHERED_COPPER_LEGGINGS);
			entries.add(WEATHERED_COPPER_BOOTS);
			entries.add(OXIDIZED_COPPER_HELMET);
			entries.add(OXIDIZED_COPPER_CHESTPLATE);
			entries.add(OXIDIZED_COPPER_LEGGINGS);
			entries.add(OXIDIZED_COPPER_BOOTS);
		});
	}
}
