package net.sonic0810.copperinferno.feature.infernodim2;

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
 * Inferno dimension expansion: four new data-driven biomes for the
 * {@code copper_inferno:inferno} dimension (verdigris_jungle, molten_delta, soot_dunes,
 * crystal_hollows — biome/dimension/noise_settings JSON under {@code data/copper_inferno/},
 * wired into a {@code minecraft:multi_noise} biome source with non-overlapping climate
 * points) plus the decorative {@link InferniumPortalCornerBlock} accepted as a portal-frame
 * block by {@code InfernoPortalBlock} and {@code InferniumIgniterItem}. Assets are generated
 * by {@code devtools/gen/infernodim2_gen.py}.
 */
public final class InfernoDim2Feature {
	private InfernoDim2Feature() {
	}

	public static Block INFERNIUM_PORTAL_CORNER;

	public static void init() {
		// Same base settings as the infernium_portal_frame it substitutes for, with a
		// slightly warmer glow.
		INFERNIUM_PORTAL_CORNER = ModBlocks.register("infernium_portal_corner",
				InferniumPortalCornerBlock::new,
				AbstractBlock.Settings.create()
						.mapColor(MapColor.ORANGE)
						.requiresTool()
						.strength(3.0f, 9.0f)
						.sounds(BlockSoundGroup.NETHER_BRICKS)
						.luminance(state -> 7),
				true);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.NATURE_KEY).register(entries -> {
			entries.add(INFERNIUM_PORTAL_CORNER);
		});

		registerHandbookEntries();
	}

	private static void registerHandbookEntries() {
		// Recipe entry (one per JSON under data/copper_inferno/recipe/infernodim2/).
		HandbookEntries.add(new HandbookEntry("blocks", "infernodim2_infernium_portal_corner_stonecutting",
				"copper_inferno:infernium_portal_corner", "infernodim2/infernium_portal_corner_from_frame_stonecutting",
				new String[] {"", "", "", "", "copper_inferno:infernium_portal_frame", "", "", "", ""},
				"copper_inferno:infernium_portal_corner", 1,
				"The stonecutter chisels an Infernium Portal Frame into a decorative corner block. It counts as a full frame block, so the ring's four corners can wear it without breaking the portal.",
				"Die Steinsäge meißelt einen Infernium-Portalrahmen zu einem dekorativen Eckblock. Er zählt als vollwertiger Rahmenblock, die vier Ecken des Rings können ihn also tragen, ohne das Portal zu zerstören."));

		// Non-recipe dimension lore entries.
		HandbookEntries.add(new HandbookEntry("dimension", "infernodim2_portal_corners",
				"copper_inferno:infernium_portal_corner", null, null, null, 0,
				"Infernium Portal Corner: a chiseled corner block with copper brackets and glowing ember studs (light level 7). Valid anywhere in the 4x5 frame ring; sheds ember sparks and crackles softly.",
				"Infernium-Portalecke: ein gemeißelter Eckblock mit Kupferklammern und glühenden Glutnieten (Lichtstärke 7). Überall im 4x5-Rahmenring gültig; verströmt Glutfunken und knistert leise."));
		HandbookEntries.add(new HandbookEntry("dimension", "infernodim2_biome_verdigris_jungle",
				"copper_inferno:ember_moss_block", null, null, null, 0,
				"Verdigris Jungle: the Inferno's lush quarter. Mossy floors under green haze, drifting spores and thick ember flora.",
				"Grünspandschungel: das üppige Viertel des Infernos. Moosige Böden unter grünem Dunst, treibende Sporen und dichte Glutflora."));
		HandbookEntries.add(new HandbookEntry("dimension", "infernodim2_biome_molten_delta",
				"copper_inferno:molten_slag", null, null, null, 0,
				"Molten Delta: scorching slagstone flats pocked by lava springs and drifting lava embers. Watch for magma cubes.",
				"Schmelzdelta: sengende Schlackenstein-Ebenen, durchsetzt von Lavaquellen und treibenden Lavafunken. Vorsicht vor Magmawürfeln."));
		HandbookEntries.add(new HandbookEntry("dimension", "infernodim2_biome_soot_dunes",
				"copper_inferno:ash_block", null, null, null, 0,
				"Soot Dunes: rolling dunes of ash and ember soil under a soot-black sky. Skeletons and ghasts haunt the drifts.",
				"Rußdünen: wogende Dünen aus Asche und Glutboden unter rußschwarzem Himmel. Skelette und Ghasts spuken durch die Verwehungen."));
		HandbookEntries.add(new HandbookEntry("dimension", "infernodim2_biome_crystal_hollows",
				"copper_inferno:smolder_crystal_ore", null, null, null, 0,
				"Crystal Hollows: quiet teal caverns rich in smolder crystal, lit by drifting motes. Only endermen wander here.",
				"Kristallhöhlen: stille türkisfarbene Kavernen voller Schwelkristall, erhellt von treibenden Lichtpunkten. Nur Endermen wandern hier."));
	}
}
