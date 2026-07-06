package net.sonic0810.copperinferno.feature.gear;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.oxidation.ItemOxidation;
import net.sonic0810.copperinferno.feature.extras.CopperHornItem;
import net.sonic0810.copperinferno.feature.extras.ExtrasFeature;

/**
 * Gear feature — 12 utility/gadget items (throwing fizz can, oxidizing copper horns, whistle,
 * magnet, buckler, monocle, key, badges). Filled in by the gear v2 worker.
 */
public final class GearFeature {
	private GearFeature() {
	}

	public static Item THROWING_FIZZ_CAN;
	public static Item EXPOSED_COPPER_HORN;
	public static Item WEATHERED_COPPER_HORN;
	public static Item OXIDIZED_COPPER_HORN;
	public static Item COPPER_WHISTLE;
	public static Item COPPER_MAGNET;
	public static Item COPPER_BUCKLER;
	public static Item COPPER_MONOCLE;
	public static Item COPPER_KEY;
	public static Item COPPER_BADGE;
	public static Item INFERNO_BADGE;
	public static Item SODA_BADGE;

	public static void init() {
		THROWING_FIZZ_CAN = ModItems.register("throwing_fizz_can", ThrowingFizzCanItem::new,
				new Item.Settings().maxCount(16));

		// Oxidation stages of the v1 copper horn (feature/extras); no crafting recipes — they
		// are obtained by letting a copper horn oxidize (or by axe-scraping backwards).
		EXPOSED_COPPER_HORN = ModItems.register("exposed_copper_horn", CopperHornItem::new,
				new Item.Settings().maxCount(1));
		WEATHERED_COPPER_HORN = ModItems.register("weathered_copper_horn", CopperHornItem::new,
				new Item.Settings().maxCount(1));
		OXIDIZED_COPPER_HORN = ModItems.register("oxidized_copper_horn", CopperHornItem::new,
				new Item.Settings().maxCount(1));

		// ExtrasFeature.init() runs before GearFeature.init() (see CopperInferno.onInitialize).
		ItemOxidation.registerChain(
				Objects.requireNonNull(ExtrasFeature.COPPER_HORN,
						"ExtrasFeature.COPPER_HORN must be registered before GearFeature.init()"),
				EXPOSED_COPPER_HORN, WEATHERED_COPPER_HORN, OXIDIZED_COPPER_HORN);

		COPPER_WHISTLE = ModItems.register("copper_whistle", CopperWhistleItem::new,
				new Item.Settings().maxCount(1));

		COPPER_MAGNET = ModItems.register("copper_magnet", CopperMagnetItem::new,
				new Item.Settings().maxDamage(128));

		// Mirrors the vanilla SHIELD registration (Items.SHIELD bytecode) minus banner patterns:
		// blocking is driven by the BLOCKS_ATTACKS component + equippableUnswappable(OFFHAND).
		COPPER_BUCKLER = ModItems.register("copper_buckler", ShieldItem::new,
				new Item.Settings()
						.maxDamage(336)
						.repairable(Items.COPPER_INGOT)
						.equippableUnswappable(EquipmentSlot.OFFHAND)
						.component(DataComponentTypes.BLOCKS_ATTACKS, new BlocksAttacksComponent(
								0.25F,
								1.0F,
								List.of(new BlocksAttacksComponent.DamageReduction(
										90.0F, Optional.empty(), 0.0F, 1.0F)),
								new BlocksAttacksComponent.ItemDamage(3.0F, 1.0F, 1.0F),
								Optional.of(DamageTypeTags.BYPASSES_SHIELD),
								Optional.of(SoundEvents.ITEM_SHIELD_BLOCK),
								Optional.of(SoundEvents.ITEM_SHIELD_BREAK)))
						.component(DataComponentTypes.BREAK_SOUND, SoundEvents.ITEM_SHIELD_BREAK));

		COPPER_MONOCLE = ModItems.register("copper_monocle", Item::new,
				new Item.Settings().maxCount(1).equippable(EquipmentSlot.HEAD));

		COPPER_KEY = ModItems.register("copper_key", Item::new,
				new Item.Settings().maxCount(16));

		COPPER_BADGE = ModItems.register("copper_badge", Item::new,
				new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1));

		INFERNO_BADGE = ModItems.register("inferno_badge", Item::new,
				new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1));

		SODA_BADGE = ModItems.register("soda_badge", Item::new,
				new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1));

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			entries.add(THROWING_FIZZ_CAN);
			entries.add(EXPOSED_COPPER_HORN);
			entries.add(WEATHERED_COPPER_HORN);
			entries.add(OXIDIZED_COPPER_HORN);
			entries.add(COPPER_WHISTLE);
			entries.add(COPPER_MAGNET);
			entries.add(COPPER_BUCKLER);
			entries.add(COPPER_MONOCLE);
			entries.add(COPPER_KEY);
			entries.add(COPPER_BADGE);
			entries.add(INFERNO_BADGE);
			entries.add(SODA_BADGE);
		});
	}
}
