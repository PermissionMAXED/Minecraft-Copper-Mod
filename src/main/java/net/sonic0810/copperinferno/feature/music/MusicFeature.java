package net.sonic0810.copperinferno.feature.music;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModItems;

/**
 * MUSIC &amp; LORE (v2): three playable music discs with original synthesized tracks, two
 * smithing templates and two trophy items.
 *
 * <ul>
 * <li>Discs are plain {@link Item}s made jukebox-playable via
 * {@code Item.Settings.jukeboxPlayable(RegistryKey<JukeboxSong>)}; the songs themselves are
 * data-driven ({@code data/copper_inferno/jukebox_song/&lt;song&gt;.json}) and reference the
 * {@code music_disc.*} sound events registered here (entries in
 * {@code assets/copper_inferno/sounds.json}, streamed oggs under
 * {@code assets/copper_inferno/sounds/music/}).</li>
 * <li>{@code copper_upgrade_smithing_template} powers 9 {@code smithing_transform} recipes
 * (iron gear + copper ingot -&gt; vanilla copper gear) under
 * {@code data/copper_inferno/recipe/music/}.</li>
 * <li>{@code inferno_upgrade_smithing_template} is a craftable display/collectible only; it
 * deliberately has NO smithing recipe.</li>
 * </ul>
 *
 * <p>Sound events are registered here (mirroring the {@code ModSounds} pattern) so this feature
 * stays self-contained; assets are mass-produced by {@code devtools/gen/music_gen.py}.
 */
public final class MusicFeature {
	private MusicFeature() {
	}

	public static SoundEvent MUSIC_DISC_COPPER_INFERNO_SOUND;
	public static SoundEvent MUSIC_DISC_SODA_POP_SOUND;
	public static SoundEvent MUSIC_DISC_OXIDATION_SOUND;

	public static Item MUSIC_DISC_COPPER_INFERNO;
	public static Item MUSIC_DISC_SODA_POP;
	public static Item MUSIC_DISC_OXIDATION;
	public static Item COPPER_UPGRADE_SMITHING_TEMPLATE;
	public static Item INFERNO_UPGRADE_SMITHING_TEMPLATE;
	public static Item COPPER_POCKET_WATCH;
	public static Item SONIC0810_MEDALLION;

	public static void init() {
		MUSIC_DISC_COPPER_INFERNO_SOUND = registerSound("music_disc.copper_inferno");
		MUSIC_DISC_SODA_POP_SOUND = registerSound("music_disc.soda_pop");
		MUSIC_DISC_OXIDATION_SOUND = registerSound("music_disc.oxidation");

		MUSIC_DISC_COPPER_INFERNO = registerDisc("music_disc_copper_inferno", "copper_inferno");
		MUSIC_DISC_SODA_POP = registerDisc("music_disc_soda_pop", "soda_pop");
		MUSIC_DISC_OXIDATION = registerDisc("music_disc_oxidation", "oxidation");

		COPPER_UPGRADE_SMITHING_TEMPLATE = ModItems.register("copper_upgrade_smithing_template",
				SmithingTemplateItem::of, new Item.Settings());
		INFERNO_UPGRADE_SMITHING_TEMPLATE = ModItems.register("inferno_upgrade_smithing_template",
				SmithingTemplateItem::of, new Item.Settings().rarity(Rarity.EPIC));

		COPPER_POCKET_WATCH = ModItems.register("copper_pocket_watch", Item::new,
				new Item.Settings().maxCount(1));
		SONIC0810_MEDALLION = ModItems.register("sonic0810_medallion", Item::new,
				new Item.Settings().maxCount(1).rarity(Rarity.EPIC));

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			entries.add(MUSIC_DISC_COPPER_INFERNO);
			entries.add(MUSIC_DISC_SODA_POP);
			entries.add(MUSIC_DISC_OXIDATION);
			entries.add(COPPER_UPGRADE_SMITHING_TEMPLATE);
			entries.add(INFERNO_UPGRADE_SMITHING_TEMPLATE);
			entries.add(COPPER_POCKET_WATCH);
			entries.add(SONIC0810_MEDALLION);
		});

		CopperInferno.LOGGER.info("[COPPER INFERNO 1] Registered music & lore content (3 discs, 2 templates, 2 trophies)");
	}

	private static SoundEvent registerSound(String path) {
		Identifier id = CopperInferno.id(path);
		return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	}

	private static Item registerDisc(String itemPath, String songPath) {
		RegistryKey<JukeboxSong> song = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, CopperInferno.id(songPath));
		return ModItems.register(itemPath, Item::new, new Item.Settings()
				.maxCount(1)
				.rarity(Rarity.RARE)
				.jukeboxPlayable(song));
	}
}
