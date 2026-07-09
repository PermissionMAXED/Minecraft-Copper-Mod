package net.sonic0810.copperinferno.feature.warhorns;

import java.util.List;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.ModSounds;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * v4.1 "War Horns" feature — three {@link HornItem} battle horns, each an area effect in a
 * 12-block sphere around the hornblower plus a per-item cooldown:
 * <ol>
 * <li>{@code copper_war_horn} — goat-horn blast; hostiles are knocked back from the player
 *     (velocity-sync treatment copied from {@code TheCarbonatedOneEntity} fizz detonation).
 *     30s cooldown.</li>
 * <li>{@code verdant_calm_horn} — allies (players + tamed pets) get Regeneration II for 5s
 *     and are extinguished. 45s cooldown.</li>
 * <li>{@code doom_war_horn} — hostiles get Slowness II + Darkness for 5s to the existing
 *     {@code ModSounds.DOOM_KICK} sample. 60s cooldown. (The id {@code doom_horn} is already
 *     taken by feature/artifacts, hence the war-horn spelling.)</li>
 * </ol>
 *
 * <p>All sounds are EXISTING events (vanilla goat horn variants + {@code ModSounds.DOOM_KICK});
 * no new .ogg, no sounds.json change. Assets (goat-horn recolor textures, item defs/models,
 * recipes under {@code recipe/warhorns/}, lang fragments EN + DE) come from
 * {@code devtools/gen/warhorns_gen.py}. Every crafting recipe includes a mod-unique input
 * (horn_valve / verdigris_pearl / doom_alloy_shard), so no vanilla/mod input collisions are
 * possible (checked by devtools/check_recipe_collisions.py).
 */
public final class WarHornsFeature {
	private WarHornsFeature() {
	}

	/** Area-effect radius (blocks) shared by all three horns. */
	private static final double RADIUS = 12.0;
	private static final int EFFECT_TICKS = 5 * 20;

	public static Item COPPER_WAR_HORN;
	public static Item VERDANT_CALM_HORN;
	public static Item DOOM_WAR_HORN;

	public static void init() {
		// ----- items (every registration uses a FRESH Settings and a LITERAL id)
		COPPER_WAR_HORN = ModItems.register("copper_war_horn",
				s -> new HornItem(SoundEvents.GOAT_HORN_SOUNDS.get(0).value(), 3.0F, 0.9F,
						30 * 20, WarHornsFeature::warBlast, s),
				new Item.Settings().maxCount(1));
		VERDANT_CALM_HORN = ModItems.register("verdant_calm_horn",
				s -> new HornItem(SoundEvents.GOAT_HORN_SOUNDS.get(2).value(), 2.5F, 1.2F,
						45 * 20, WarHornsFeature::verdantCalm, s),
				new Item.Settings().maxCount(1));
		DOOM_WAR_HORN = ModItems.register("doom_war_horn",
				s -> new HornItem(ModSounds.DOOM_KICK, 2.0F, 1.0F,
						60 * 20, WarHornsFeature::doomDread, s),
				new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON));

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {
			entries.add(COPPER_WAR_HORN);
			entries.add(VERDANT_CALM_HORN);
			entries.add(DOOM_WAR_HORN);
		});

		registerHandbookEntries();
	}

	/**
	 * Copper War Horn core: every hostile within {@link #RADIUS} is blasted away from the
	 * hornblower. takeKnockback/addVelocity only mutate the server-side velocity, so the
	 * targets are flagged with {@code velocityModified} for sync — the exact treatment of
	 * {@code TheCarbonatedOneEntity.fizzDetonation}.
	 */
	static void warBlast(ServerWorld world, LivingEntity user) {
		for (HostileEntity hostile : hostilesAround(world, user)) {
			hostile.takeKnockback(1.6,
					user.getX() - hostile.getX(), user.getZ() - hostile.getZ());
			hostile.addVelocity(0.0, 0.4, 0.0);
			// takeKnockback/addVelocity only mutate the server-side velocity; flag for sync.
			hostile.velocityModified = true;
		}
		world.spawnParticles(ParticleTypes.GUST,
				user.getX(), user.getBodyY(0.5), user.getZ(), 8, 2.0, 0.4, 2.0, 0.0);
		world.spawnParticles(ParticleTypes.EXPLOSION,
				user.getX(), user.getBodyY(0.5), user.getZ(), 3, 1.2, 0.5, 1.2, 0.0);
	}

	/**
	 * Verdant Calm Horn core: every ally within {@link #RADIUS} — players (the hornblower
	 * included) and tamed pets — gets Regeneration II for 5s and any fire put out.
	 */
	static void verdantCalm(ServerWorld world, LivingEntity user) {
		List<LivingEntity> allies = world.getEntitiesByClass(LivingEntity.class,
				user.getBoundingBox().expand(RADIUS),
				e -> e.isAlive() && e.squaredDistanceTo(user) <= RADIUS * RADIUS
						&& (e instanceof PlayerEntity
								|| (e instanceof TameableEntity pet && pet.isTamed())));
		for (LivingEntity ally : allies) {
			ally.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, EFFECT_TICKS, 1));
			ally.extinguish();
		}
		world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
				user.getX(), user.getBodyY(0.5), user.getZ(), 30, 3.0, 1.0, 3.0, 0.0);
		world.spawnParticles(ParticleTypes.HEART,
				user.getX(), user.getBodyY(0.8), user.getZ(), 6, 2.0, 0.8, 2.0, 0.0);
	}

	/**
	 * Doom War Horn core: every hostile within {@link #RADIUS} is gripped by dread —
	 * Slowness II + Darkness for 5s (the DOOM_KICK sample plays from {@link HornItem}).
	 */
	static void doomDread(ServerWorld world, LivingEntity user) {
		for (HostileEntity hostile : hostilesAround(world, user)) {
			hostile.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, EFFECT_TICKS, 1));
			hostile.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, EFFECT_TICKS, 0));
		}
		world.spawnParticles(ParticleTypes.SCULK_SOUL,
				user.getX(), user.getBodyY(0.5), user.getZ(), 24, 3.0, 1.0, 3.0, 0.02);
		world.spawnParticles(ParticleTypes.LARGE_SMOKE,
				user.getX(), user.getBodyY(0.5), user.getZ(), 12, 2.0, 0.8, 2.0, 0.01);
	}

	/** True-sphere hostile lookup around the hornblower (as in {@code DoomHornItem}). */
	private static List<HostileEntity> hostilesAround(ServerWorld world, LivingEntity user) {
		return world.getEntitiesByClass(HostileEntity.class,
				user.getBoundingBox().expand(RADIUS),
				e -> e.isAlive() && e.squaredDistanceTo(user) <= RADIUS * RADIUS);
	}

	private static void registerHandbookEntries() {
		final String CU = "minecraft:copper_ingot";
		final String HORN = "minecraft:goat_horn";

		HandbookEntries.add(new HandbookEntry("gear", "warhorn_copper_war_horn",
				"copper_inferno:copper_war_horn", "warhorns/copper_war_horn",
				new String[]{"", CU, "", CU, HORN, CU, "", "copper_inferno:horn_valve", ""},
				"copper_inferno:copper_war_horn", 1,
				"The Copper War Horn opens every charge: one blast hurls all hostiles within 12 blocks away from you. 30s cooldown.",
				"Das Kupfer-Kriegshorn er\u00f6ffnet jeden Sturmangriff: ein Sto\u00df schleudert alle Feinde im Umkreis von 12 Bl\u00f6cken von dir fort. 30s Abklingzeit."));
		HandbookEntries.add(new HandbookEntry("gear", "warhorn_verdant_calm_horn",
				"copper_inferno:verdant_calm_horn", "warhorns/verdant_calm_horn",
				new String[]{"", "copper_inferno:verdigris_pearl", "", CU, HORN, CU, "", CU, ""},
				"copper_inferno:verdant_calm_horn", 1,
				"The Verdant Calm Horn soothes the battlefield: allies - players and tamed pets - within 12 blocks receive Regeneration II for 5 seconds and any fire is put out. 45s cooldown.",
				"Das Horn der Ruhe bes\u00e4nftigt das Schlachtfeld: Verb\u00fcndete - Spieler und gez\u00e4hmte Tiere - im Umkreis von 12 Bl\u00f6cken erhalten Regeneration II f\u00fcr 5 Sekunden, und jedes Feuer wird gel\u00f6scht. 45s Abklingzeit."));
		HandbookEntries.add(new HandbookEntry("gear", "warhorn_doom_war_horn",
				"copper_inferno:doom_war_horn", "warhorns/doom_war_horn",
				new String[]{"", "copper_inferno:doom_alloy_shard", "", CU, HORN, CU, "", CU, ""},
				"copper_inferno:doom_war_horn", 1,
				"The Doom War Horn blasts the DOOM kick itself: hostiles within 12 blocks are gripped by dread - Slowness II and Darkness for 5 seconds. 60s cooldown.",
				"Das Doom-Kriegshorn schmettert den DOOM-Kick h\u00f6chstpers\u00f6nlich: Feinde im Umkreis von 12 Bl\u00f6cken packt das Grauen - Langsamkeit II und Dunkelheit f\u00fcr 5 Sekunden. 60s Abklingzeit."));
	}
}
