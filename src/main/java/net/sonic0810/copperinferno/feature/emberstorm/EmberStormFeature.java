package net.sonic0810.copperinferno.feature.emberstorm;

import java.util.Locale;

import com.mojang.brigadier.Command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModDimensions;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * v4.1 "Ember Storm" feature — periodic weather events for the Inferno dimension
 * ({@code copper_inferno:inferno}).
 *
 * <p>A tick-driven state machine on {@link ServerTickEvents#END_WORLD_TICK} (inferno world
 * only) alternates calm phases (8-14 min) with storm phases (90-150 s). While a storm rages,
 * every second each player in the dimension gets dense ash/flame particle bursts, a low
 * ambient rumble every ~10 s, and — if standing under open sky — 3 s of Slowness I plus 1 s
 * of fire (sheltering under any roof stops both).
 *
 * <p>{@code /emberstorm <start|stop|status>} (permission level 2) force-starts/stops a storm
 * and reports the current phase; it doubles as the test hook.
 *
 * <p>Storm state lives in static fields for THE inferno world (a server has exactly one; the
 * ticker ignores every other world). Deliberately NOT persisted: storms do not survive a
 * server restart — every boot begins with a fresh calm roll, which is acceptable for an
 * ambient weather event. Lang fragments come from {@code devtools/gen/emberstorm_gen.py}.
 */
public final class EmberStormFeature {
	private EmberStormFeature() {
	}

	// Phase durations (ticks): calm 8-14 min, storm 90-150 s.
	private static final int CALM_MIN_TICKS = 8 * 60 * 20;
	private static final int CALM_MAX_TICKS = 14 * 60 * 20;
	private static final int STORM_MIN_TICKS = 90 * 20;
	private static final int STORM_MAX_TICKS = 150 * 20;

	/** Per-player storm effects run once a second; the ambient rumble every ~10 s. */
	private static final int EFFECT_PERIOD_TICKS = 20;
	private static final int AMBIENCE_PERIOD_TICKS = 200;

	private static final int SLOWNESS_TICKS = 3 * 20; // 3 s Slowness I
	private static final int FIRE_TICKS = 20; // 1 s on fire (stops once sheltered)

	private static final int PERMISSION_LEVEL = 2;

	// In-memory state for the single inferno world; see class javadoc (not persisted).
	private static boolean storming;
	private static int phaseTicksLeft = -1; // -1 = first inferno tick/command rolls the initial calm

	public static void init() {
		ServerTickEvents.END_WORLD_TICK.register(EmberStormFeature::onEndWorldTick);
		registerCommand();
		registerHandbookEntry();
	}

	// ------------------------------------------------------------------
	// State machine
	// ------------------------------------------------------------------

	private static void onEndWorldTick(ServerWorld world) {
		if (!world.getRegistryKey().getValue().equals(ModDimensions.INFERNO_WORLD.getValue())) {
			return;
		}
		ensureInitialized(world);
		phaseTicksLeft--;
		if (phaseTicksLeft <= 0) {
			if (storming) {
				endStorm(world);
			} else {
				beginStorm(world);
			}
		}
		if (storming) {
			tickStorm(world);
		}
	}

	/** Rolls the initial calm phase lazily (first inferno tick or first command). */
	private static void ensureInitialized(ServerWorld world) {
		if (phaseTicksLeft < 0) {
			phaseTicksLeft = world.random.nextBetween(CALM_MIN_TICKS, CALM_MAX_TICKS);
		}
	}

	private static void beginStorm(ServerWorld world) {
		storming = true;
		phaseTicksLeft = world.random.nextBetween(STORM_MIN_TICKS, STORM_MAX_TICKS);
		CopperInferno.LOGGER.info("[emberstorm] Ember storm STARTED in {} for {} ticks (~{})",
				world.getRegistryKey().getValue(), phaseTicksLeft, formatTicks(phaseTicksLeft));
		for (ServerPlayerEntity player : world.getPlayers()) {
			player.sendMessage(Text.translatable("message.copper_inferno.emberstorm.begin"), true);
		}
	}

	private static void endStorm(ServerWorld world) {
		storming = false;
		phaseTicksLeft = world.random.nextBetween(CALM_MIN_TICKS, CALM_MAX_TICKS);
		CopperInferno.LOGGER.info("[emberstorm] Ember storm ENDED in {}; next storm in {} ticks (~{})",
				world.getRegistryKey().getValue(), phaseTicksLeft, formatTicks(phaseTicksLeft));
		for (ServerPlayerEntity player : world.getPlayers()) {
			player.sendMessage(Text.translatable("message.copper_inferno.emberstorm.end"), true);
		}
	}

	/** Storm-tick effects: once a second per player in the inferno world. */
	private static void tickStorm(ServerWorld world) {
		if (world.getTime() % EFFECT_PERIOD_TICKS != 0L) {
			return;
		}
		boolean playAmbience = world.getTime() % AMBIENCE_PERIOD_TICKS == 0L;
		for (ServerPlayerEntity player : world.getPlayers()) {
			// Dense ash whipped by sparse flame around the player.
			world.spawnParticles(ParticleTypes.ASH,
					player.getX(), player.getY() + 1.0, player.getZ(), 80, 6.0, 3.0, 6.0, 0.15);
			world.spawnParticles(ParticleTypes.FLAME,
					player.getX(), player.getY() + 1.0, player.getZ(), 24, 5.0, 2.5, 5.0, 0.05);
			if (playAmbience) {
				// Low rumble: reuses vanilla basalt-deltas mood ambience, pitched down.
				world.playSound(null, player.getX(), player.getY(), player.getZ(),
						SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, SoundCategory.WEATHER, 1.4f, 0.55f);
			}
			if (world.isSkyVisible(player.getBlockPos())) {
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, SLOWNESS_TICKS, 0));
				player.setOnFireForTicks(FIRE_TICKS);
			}
		}
	}

	// ------------------------------------------------------------------
	// /emberstorm <start|stop|status> (permission level 2; also the test hook)
	// ------------------------------------------------------------------

	private static void registerCommand() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
				dispatcher.register(CommandManager.literal("emberstorm")
						.requires(source -> source.hasPermissionLevel(PERMISSION_LEVEL))
						.then(CommandManager.literal("start")
								.executes(context -> executeStart(context.getSource())))
						.then(CommandManager.literal("stop")
								.executes(context -> executeStop(context.getSource())))
						.then(CommandManager.literal("status")
								.executes(context -> executeStatus(context.getSource())))));
	}

	/** (Re)starts a storm with a fresh 90-150 s roll, from any dimension/console. */
	private static int executeStart(ServerCommandSource source) {
		ServerWorld world = ModDimensions.infernoWorld(source.getServer());
		if (world == null) {
			source.sendError(Text.translatable("command.copper_inferno.emberstorm.no_world"));
			return 0;
		}
		beginStorm(world);
		String remaining = formatTicks(phaseTicksLeft);
		source.sendFeedback(() ->
				Text.translatable("command.copper_inferno.emberstorm.started", remaining), true);
		return Command.SINGLE_SUCCESS;
	}

	private static int executeStop(ServerCommandSource source) {
		ServerWorld world = ModDimensions.infernoWorld(source.getServer());
		if (world == null) {
			source.sendError(Text.translatable("command.copper_inferno.emberstorm.no_world"));
			return 0;
		}
		if (!storming) {
			source.sendFeedback(() ->
					Text.translatable("command.copper_inferno.emberstorm.not_storming"), false);
			return 0;
		}
		endStorm(world);
		String nextIn = formatTicks(phaseTicksLeft);
		source.sendFeedback(() ->
				Text.translatable("command.copper_inferno.emberstorm.stopped", nextIn), true);
		return Command.SINGLE_SUCCESS;
	}

	private static int executeStatus(ServerCommandSource source) {
		ServerWorld world = ModDimensions.infernoWorld(source.getServer());
		if (world == null) {
			source.sendError(Text.translatable("command.copper_inferno.emberstorm.no_world"));
			return 0;
		}
		ensureInitialized(world);
		String remaining = formatTicks(phaseTicksLeft);
		String key = storming
				? "command.copper_inferno.emberstorm.status.storm"
				: "command.copper_inferno.emberstorm.status.calm";
		source.sendFeedback(() -> Text.translatable(key, remaining), false);
		return Command.SINGLE_SUCCESS;
	}

	/** Formats a tick count as mm:ss for command feedback and logs. */
	private static String formatTicks(int ticks) {
		int totalSeconds = Math.max(0, ticks) / 20;
		return String.format(Locale.ROOT, "%02d:%02d", totalSeconds / 60, totalSeconds % 60);
	}

	// ------------------------------------------------------------------
	// Handbook
	// ------------------------------------------------------------------

	private static void registerHandbookEntry() {
		HandbookEntries.add(new HandbookEntry("dimension", "emberstorm_ember_storm",
				"copper_inferno:ash_block", null, null, null, 0,
				"Ember Storm: the Inferno's weather turned violent. After 8-14 minutes of calm"
						+ " the ash ignites into a 90-150 second storm - ash and flame whip around"
						+ " you, a low rumble rolls over the wastes, and anyone under open sky is"
						+ " slowed and set alight. Duck under any roof to shelter. Operators can"
						+ " force or query one with /emberstorm.",
				"Glutsturm: das Wetter des Infernos, wenn es wütend wird. Nach 8-14 Minuten Ruhe"
						+ " entfacht sich die Asche zu einem 90-150 Sekunden langen Sturm - Asche"
						+ " und Flammen peitschen um dich, ein tiefes Grollen rollt über die Öde,"
						+ " und wer unter freiem Himmel steht, wird verlangsamt und in Brand"
						+ " gesetzt. Stell dich unter ein Dach, um Schutz zu finden. Operatoren"
						+ " können einen Sturm mit /emberstorm erzwingen oder abfragen."));
	}
}
