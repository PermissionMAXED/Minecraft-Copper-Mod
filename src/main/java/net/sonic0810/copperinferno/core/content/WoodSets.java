package net.sonic0810.copperinferno.core.content;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WoodType;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModBlockFamilies;
import net.sonic0810.copperinferno.core.ModBlocks;

/**
 * v4 scale registrar: a full 13-block wood set registered from a single call.
 *
 * <p>AUDIT CONTRACT (devtools/audit_assets.py check (f)): every id below is a {@code wood}
 * parameter concatenation with string literals, and call sites MUST pass a string literal (never
 * a variable) as the leading {@code wood} argument. The audit then expects
 * items/blockstates/models/loot for all 13 derived ids.
 */
public final class WoodSets {
	private WoodSets() {
	}

	/** All 13 blocks of a wood set, in canonical (registration/creative-tab) order. */
	public record WoodSet(Block planks, Block plankSlab, Block plankStairs,
			Block fence, Block fenceGate, Block button, Block pressurePlate,
			Block log, Block strippedLog, Block wood, Block strippedWood,
			Block mosaic, Block pillar) {
	}

	/**
	 * Registers the 13 wood-set blocks (each with a BlockItem) and appends them to {@code tab} in
	 * canonical order. The {@code woodSettings} supplier is invoked FRESH per registration
	 * ({@link ModBlocks#register} writes a registry key into each instance); the button and
	 * pressure plate additionally get {@code noCollision()} (required for their hitboxes, exactly
	 * like the utilityblocks copper buttons/plates).
	 *
	 * <p>The fence gate uses a per-wood {@link WoodType} registered via Fabric's
	 * {@link WoodTypeBuilder} (same pattern as the utilityblocks copper_fence_gate) so
	 * {@code FenceGateBlock}'s codec stays functional; button/plate use {@link BlockSetType#OAK}
	 * with the vanilla wooden 30-tick press time.
	 *
	 * <p>Ids: {@code <wood>_planks}, {@code <wood>_plank_slab}, {@code <wood>_plank_stairs},
	 * {@code <wood>_fence}, {@code <wood>_fence_gate}, {@code <wood>_button},
	 * {@code <wood>_pressure_plate}, {@code <wood>_log}, {@code stripped_<wood>_log},
	 * {@code <wood>_wood}, {@code stripped_<wood>_wood}, {@code <wood>_mosaic},
	 * {@code <wood>_pillar}.
	 */
	public static WoodSet registerWoodSet(String wood, Supplier<AbstractBlock.Settings> woodSettings,
			RegistryKey<ItemGroup> tab) {
		// Registered WoodType keeps FenceGateBlock's codec functional (see utilityblocks).
		WoodType woodType = WoodTypeBuilder.copyOf(WoodType.OAK)
				.register(CopperInferno.id(wood), BlockSetType.OAK);

		Block planks = ModBlocks.register(wood + "_planks", Block::new, woodSettings.get(), true);
		Block plankSlab = ModBlocks.register(wood + "_plank_slab", SlabBlock::new, woodSettings.get(), true);
		Block plankStairs = ModBlocks.register(wood + "_plank_stairs",
				s -> new ModBlockFamilies.PublicStairsBlock(planks.getDefaultState(), s), woodSettings.get(), true);
		Block fence = ModBlocks.register(wood + "_fence", FenceBlock::new, woodSettings.get(), true);
		Block fenceGate = ModBlocks.register(wood + "_fence_gate",
				s -> new FenceGateBlock(woodType, s), woodSettings.get(), true);
		Block button = ModBlocks.register(wood + "_button",
				s -> new ContentButtonBlock(BlockSetType.OAK, 30, s), woodSettings.get().noCollision(), true);
		Block pressurePlate = ModBlocks.register(wood + "_pressure_plate",
				s -> new ContentPressurePlateBlock(BlockSetType.OAK, s), woodSettings.get().noCollision(), true);
		Block log = ModBlocks.register(wood + "_log", PillarBlock::new, woodSettings.get(), true);
		Block strippedLog = ModBlocks.register("stripped_" + wood + "_log", PillarBlock::new, woodSettings.get(), true);
		Block woodBlock = ModBlocks.register(wood + "_wood", PillarBlock::new, woodSettings.get(), true);
		Block strippedWood = ModBlocks.register("stripped_" + wood + "_wood", PillarBlock::new, woodSettings.get(), true);
		Block mosaic = ModBlocks.register(wood + "_mosaic", Block::new, woodSettings.get(), true);
		Block pillar = ModBlocks.register(wood + "_pillar", PillarBlock::new, woodSettings.get(), true);

		WoodSet set = new WoodSet(planks, plankSlab, plankStairs, fence, fenceGate, button, pressurePlate,
				log, strippedLog, woodBlock, strippedWood, mosaic, pillar);

		ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> {
			entries.add(set.planks());
			entries.add(set.plankSlab());
			entries.add(set.plankStairs());
			entries.add(set.fence());
			entries.add(set.fenceGate());
			entries.add(set.button());
			entries.add(set.pressurePlate());
			entries.add(set.log());
			entries.add(set.strippedLog());
			entries.add(set.wood());
			entries.add(set.strippedWood());
			entries.add(set.mosaic());
			entries.add(set.pillar());
		});
		return set;
	}
}
