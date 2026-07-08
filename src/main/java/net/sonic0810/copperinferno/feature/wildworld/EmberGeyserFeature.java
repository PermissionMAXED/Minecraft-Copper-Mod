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
 * Ember Geyser: the {@link EmberGeyserBlock} vent block plus its worldgen — the
 * {@code patch_ember_geysers} configured/placed feature pair (random_patch of geysers on Doom
 * Basin floors, emitted by {@code devtools/gen/wildworld_gen.py}) is wired into the Doom Basin
 * biome JSON. Assets, loot, recipe ({@code recipe/wildworld/ember_geyser.json}) and lang are
 * generated too.
 */
public final class EmberGeyserFeature {
	private EmberGeyserFeature() {
	}

	public static Block EMBER_GEYSER;

	public static void init() {
		EMBER_GEYSER = ModBlocks.register("ember_geyser", EmberGeyserBlock::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.ORANGE)
						.requiresTool()
						.strength(1.5f, 6.0f)
						.sounds(BlockSoundGroup.NETHERRACK)
						.luminance(state -> 6),
				true);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.BLOCKS_KEY).register(entries -> {
			entries.add(EMBER_GEYSER);
		});

		HandbookEntries.add(new HandbookEntry("dimension", "wildworld_ember_geyser",
				"copper_inferno:ember_geyser", "wildworld/ember_geyser",
				new String[] {"copper_inferno:cobbled_cinderstone", "copper_inferno:meteoric_iron_chunk", "copper_inferno:cobbled_cinderstone",
						"copper_inferno:meteoric_iron_chunk", "minecraft:magma_block", "copper_inferno:meteoric_iron_chunk",
						"copper_inferno:cobbled_cinderstone", "copper_inferno:meteoric_iron_chunk", "copper_inferno:cobbled_cinderstone"},
				"copper_inferno:ember_geyser", 1,
				"Ember Geyser: a pressure vent found on Doom Basin floors. All geysers erupt on the"
						+ " same pulse — a 1-second blast every 5 seconds — hurling anything standing on"
						+ " them into the air. Craft your own for elevators and mob launchers.",
				"Glutgeysir: ein Druckschlot vom Boden des Doom-Beckens. Alle Geysire brechen im selben"
						+ " Takt aus — ein 1-Sekunden-Stoß alle 5 Sekunden — und schleudern alles in die"
						+ " Luft, was auf ihnen steht. Baue eigene für Aufzüge und Mob-Katapulte."));
	}
}
