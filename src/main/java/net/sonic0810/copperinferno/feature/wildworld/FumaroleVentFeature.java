package net.sonic0810.copperinferno.feature.wildworld;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Fumarole Vent: the {@link FumaroleVentBlock} smoking chimney block plus its worldgen — the
 * {@code patch_fumarole_vents} configured/placed feature pair (random_patch on Doom Basin
 * floors, emitted by {@code devtools/gen/wildworld_gen.py}) is wired into the Doom Basin biome
 * JSON. Assets, loot, recipe ({@code recipe/wildworld/fumarole_vent.json}) and lang are
 * generated too.
 */
public final class FumaroleVentFeature {
	private FumaroleVentFeature() {
	}

	public static Block FUMAROLE_VENT;

	public static void init() {
		FUMAROLE_VENT = ModBlocks.register("fumarole_vent", FumaroleVentBlock::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.GRAY)
						.requiresTool()
						.strength(1.5f, 6.0f)
						.sounds(BlockSoundGroup.BASALT)
						.luminance(state -> 3),
				true);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			entries.add(FUMAROLE_VENT);
		});

		HandbookEntries.add(new HandbookEntry("dimension", "wildworld_fumarole_vent",
				"copper_inferno:fumarole_vent", "wildworld/fumarole_vent",
				new String[] {"copper_inferno:cobbled_cinderstone", "copper_inferno:meteoric_iron_chunk", "copper_inferno:cobbled_cinderstone",
						"copper_inferno:cobbled_cinderstone", "", "copper_inferno:cobbled_cinderstone",
						"copper_inferno:cobbled_cinderstone", "copper_inferno:cobbled_cinderstone", "copper_inferno:cobbled_cinderstone"},
				"copper_inferno:fumarole_vent", 1,
				"Fumarole Vent: a volcanic chimney dotting the Doom Basin. It endlessly exhales"
						+ " campfire-style smoke columns and crackles softly — purely atmospheric, and"
						+ " craftable for smokestacks and hot-spring builds.",
				"Fumarolenschlot: ein vulkanischer Kamin, der das Doom-Becken sprenkelt. Er stößt"
						+ " endlos Rauchsäulen im Lagerfeuer-Stil aus und knistert leise — reine"
						+ " Atmosphäre, und baubar für Schornsteine und Thermalquellen."));
	}
}
