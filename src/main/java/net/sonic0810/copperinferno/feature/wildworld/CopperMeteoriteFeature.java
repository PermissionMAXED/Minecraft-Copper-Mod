package net.sonic0810.copperinferno.feature.wildworld;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Copper Meteorite: a rare Overworld surface impact site. Data-driven worldgen — the
 * {@code copper_meteorite} configured/placed feature pair (emitted by
 * {@code devtools/gen/wildworld_gen.py}) scatters a debris field of vanilla raw copper blocks
 * and the new meteoric_iron block; injected into every Overworld biome via
 * {@code BiomeModifications.addFeature} at SURFACE_STRUCTURES (same Fabric pattern as
 * {@code GemAlloyFeature}). Mining the meteoric iron yields meteoric iron chunks.
 */
public final class CopperMeteoriteFeature {
	private CopperMeteoriteFeature() {
	}

	public static Block METEORIC_IRON;
	public static Item METEORIC_IRON_CHUNK;

	public static void init() {
		METEORIC_IRON = ModBlocks.register("meteoric_iron", Block::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.GRAY)
						.requiresTool()
						.strength(4.0f, 9.0f)
						.sounds(BlockSoundGroup.NETHER_ORE),
				true);
		METEORIC_IRON_CHUNK = ModItems.register("meteoric_iron_chunk", Item::new,
				new Item.Settings());

		RegistryKey<PlacedFeature> meteorite =
				RegistryKey.of(RegistryKeys.PLACED_FEATURE, CopperInferno.id("copper_meteorite"));
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
				GenerationStep.Feature.SURFACE_STRUCTURES, meteorite);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			entries.add(METEORIC_IRON);
		});
		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			entries.add(METEORIC_IRON_CHUNK);
		});

		HandbookEntries.add(new HandbookEntry("dimension", "wildworld_copper_meteorite",
				"copper_inferno:meteoric_iron", "wildworld/meteoric_iron",
				new String[] {"copper_inferno:meteoric_iron_chunk", "copper_inferno:meteoric_iron_chunk", "copper_inferno:meteoric_iron_chunk",
						"copper_inferno:meteoric_iron_chunk", "copper_inferno:meteoric_iron_chunk", "copper_inferno:meteoric_iron_chunk",
						"copper_inferno:meteoric_iron_chunk", "copper_inferno:meteoric_iron_chunk", "copper_inferno:meteoric_iron_chunk"},
				"copper_inferno:meteoric_iron", 1,
				"Copper Meteorite: a rare impact scar on the Overworld surface — a debris field of"
						+ " raw copper blocks laced with meteoric iron. The meteoric iron shatters into"
						+ " chunks when mined; nine chunks press back into a block, and a chunk smelts"
						+ " into an iron ingot.",
				"Kupfermeteorit: eine seltene Einschlagnarbe an der Oberwelt-Oberfläche — ein"
						+ " Trümmerfeld aus Rohkupferblöcken, durchzogen von Meteoreisen. Das Meteoreisen"
						+ " zerspringt beim Abbau in Brocken; neun Brocken lassen sich wieder zu einem Block"
						+ " pressen, und ein Brocken schmilzt zu einem Eisenbarren."));
	}
}
