package net.sonic0810.copperinferno.feature.tools;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.oxidation.ItemOxidation;
import net.sonic0810.copperinferno.core.oxidation.OxidizableAxeItem;
import net.sonic0810.copperinferno.core.oxidation.OxidizableEquipmentItem;
import net.sonic0810.copperinferno.core.oxidation.OxidizableHoeItem;
import net.sonic0810.copperinferno.core.oxidation.OxidizableShovelItem;

/**
 * Oxidizing copper tools. Stage 0 of each chain is the vanilla 1.21.9 copper tool
 * (COPPER_SWORD/PICKAXE/AXE/SHOVEL/HOE); this feature adds the exposed, weathered and
 * oxidized stages and wires them into the shared {@link ItemOxidation} system.
 *
 * <p>Axes/shovels/hoes use the Oxidizable{Axe,Shovel,Hoe}Item subclasses so they keep the
 * vanilla strip/path/till behavior; SwordItem/PickaxeItem no longer exist in 1.21.9 (sword and
 * pickaxe behavior is component-driven), so those stay on {@link OxidizableEquipmentItem}.
 */
public final class CopperToolsFeature {
	private CopperToolsFeature() {
	}

	// Attack damage / attack speed follow the standard vanilla tool templates
	// (sword 3.0/-2.4, pickaxe 1.0/-2.8, axe 6.0/-3.1, shovel 1.5/-3.0, hoe -1.0/-2.0),
	// approximating the vanilla copper tools; ToolMaterial.COPPER supplies the rest.
	public static final Item EXPOSED_COPPER_SWORD = registerSword("exposed_copper_sword");
	public static final Item WEATHERED_COPPER_SWORD = registerSword("weathered_copper_sword");
	public static final Item OXIDIZED_COPPER_SWORD = registerSword("oxidized_copper_sword");

	public static final Item EXPOSED_COPPER_PICKAXE = registerPickaxe("exposed_copper_pickaxe");
	public static final Item WEATHERED_COPPER_PICKAXE = registerPickaxe("weathered_copper_pickaxe");
	public static final Item OXIDIZED_COPPER_PICKAXE = registerPickaxe("oxidized_copper_pickaxe");

	public static final Item EXPOSED_COPPER_AXE = registerAxe("exposed_copper_axe");
	public static final Item WEATHERED_COPPER_AXE = registerAxe("weathered_copper_axe");
	public static final Item OXIDIZED_COPPER_AXE = registerAxe("oxidized_copper_axe");

	public static final Item EXPOSED_COPPER_SHOVEL = registerShovel("exposed_copper_shovel");
	public static final Item WEATHERED_COPPER_SHOVEL = registerShovel("weathered_copper_shovel");
	public static final Item OXIDIZED_COPPER_SHOVEL = registerShovel("oxidized_copper_shovel");

	public static final Item EXPOSED_COPPER_HOE = registerHoe("exposed_copper_hoe");
	public static final Item WEATHERED_COPPER_HOE = registerHoe("weathered_copper_hoe");
	public static final Item OXIDIZED_COPPER_HOE = registerHoe("oxidized_copper_hoe");

	private static Item registerSword(String path) {
		return ModItems.register(path, OxidizableEquipmentItem::new,
				new Item.Settings().sword(ToolMaterial.COPPER, 3.0F, -2.4F));
	}

	private static Item registerPickaxe(String path) {
		return ModItems.register(path, OxidizableEquipmentItem::new,
				new Item.Settings().pickaxe(ToolMaterial.COPPER, 1.0F, -2.8F));
	}

	// Vanilla registers axes/shovels/hoes with PLAIN settings: the AxeItem/ShovelItem/HoeItem
	// constructor applies Settings.axe(...)/shovel(...)/hoe(...) itself (verified via the 1.21.9
	// Items + AxeItem bytecode), so the tool profile must not be pre-applied here.
	private static Item registerAxe(String path) {
		return ModItems.register(path, s -> new OxidizableAxeItem(ToolMaterial.COPPER, 6.0F, -3.1F, s),
				new Item.Settings());
	}

	private static Item registerShovel(String path) {
		return ModItems.register(path, s -> new OxidizableShovelItem(ToolMaterial.COPPER, 1.5F, -3.0F, s),
				new Item.Settings());
	}

	private static Item registerHoe(String path) {
		return ModItems.register(path, s -> new OxidizableHoeItem(ToolMaterial.COPPER, -1.0F, -2.0F, s),
				new Item.Settings());
	}

	public static void init() {
		ItemOxidation.registerChain(Items.COPPER_SWORD, EXPOSED_COPPER_SWORD, WEATHERED_COPPER_SWORD, OXIDIZED_COPPER_SWORD);
		ItemOxidation.registerChain(Items.COPPER_PICKAXE, EXPOSED_COPPER_PICKAXE, WEATHERED_COPPER_PICKAXE, OXIDIZED_COPPER_PICKAXE);
		ItemOxidation.registerChain(Items.COPPER_AXE, EXPOSED_COPPER_AXE, WEATHERED_COPPER_AXE, OXIDIZED_COPPER_AXE);
		ItemOxidation.registerChain(Items.COPPER_SHOVEL, EXPOSED_COPPER_SHOVEL, WEATHERED_COPPER_SHOVEL, OXIDIZED_COPPER_SHOVEL);
		ItemOxidation.registerChain(Items.COPPER_HOE, EXPOSED_COPPER_HOE, WEATHERED_COPPER_HOE, OXIDIZED_COPPER_HOE);

		// Stage-major order, with the vanilla stage-0 tools first so each chain reads fully.
		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			entries.add(Items.COPPER_SWORD);
			entries.add(Items.COPPER_PICKAXE);
			entries.add(Items.COPPER_AXE);
			entries.add(Items.COPPER_SHOVEL);
			entries.add(Items.COPPER_HOE);
			entries.add(EXPOSED_COPPER_SWORD);
			entries.add(EXPOSED_COPPER_PICKAXE);
			entries.add(EXPOSED_COPPER_AXE);
			entries.add(EXPOSED_COPPER_SHOVEL);
			entries.add(EXPOSED_COPPER_HOE);
			entries.add(WEATHERED_COPPER_SWORD);
			entries.add(WEATHERED_COPPER_PICKAXE);
			entries.add(WEATHERED_COPPER_AXE);
			entries.add(WEATHERED_COPPER_SHOVEL);
			entries.add(WEATHERED_COPPER_HOE);
			entries.add(OXIDIZED_COPPER_SWORD);
			entries.add(OXIDIZED_COPPER_PICKAXE);
			entries.add(OXIDIZED_COPPER_AXE);
			entries.add(OXIDIZED_COPPER_SHOVEL);
			entries.add(OXIDIZED_COPPER_HOE);
		});
	}
}
