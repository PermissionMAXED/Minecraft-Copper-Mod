package net.sonic0810.copperinferno.feature.golem.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.CopperGolemEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.world.ClientWorld;
import net.sonic0810.copperinferno.feature.golem.DrPepperGolemEntity;
import net.sonic0810.copperinferno.feature.golem.DrPepperGolemFeature;

/**
 * Client-side setup for Dr.Pepper golems: reuses the vanilla Copper Golem renderer (the maroon
 * dust aura spawned in {@link DrPepperGolemEntity#tick()} tells them apart) and runs the proximity
 * DOOM aura — while a player is within {@link DoomAuraSoundInstance#RANGE} blocks of a Dr.Pepper
 * golem, a looping DOOM track plays ultra loud, fading with distance.
 */
public final class DrPepperGolemFeatureClient {
	private DrPepperGolemFeatureClient() {
	}

	/** Scan every 10 ticks; sound instances tick every tick on their own. */
	private static final int SCAN_INTERVAL_TICKS = 10;
	/** Scan slightly beyond the aura so golems walking toward the player are picked up promptly. */
	private static final double SCAN_RANGE = 12.0;

	private static final Map<UUID, DoomAuraSoundInstance> ACTIVE_AURAS = new HashMap<>();
	private static ClientWorld lastWorld;
	private static int ticks;

	public static void initClient() {
		// DrPepperGolemEntity extends CopperGolemEntity, so the vanilla renderer factory fits the
		// register(EntityType<? extends T>, EntityRendererFactory<T>) bound. The vanilla register
		// method is access-widened by Fabric's transitive access wideners (the Fabric
		// EntityRendererRegistry wrapper is deprecated in favor of it).
		EntityRendererFactories.register(DrPepperGolemFeature.DR_PEPPER_GOLEM, CopperGolemEntityRenderer::new);

		ClientTickEvents.END_CLIENT_TICK.register(DrPepperGolemFeatureClient::onEndTick);
	}

	private static void onEndTick(MinecraftClient client) {
		ClientWorld world = client.world;
		if (world != lastWorld) {
			stopAll(client);
			lastWorld = world;
		}
		if (world == null || client.player == null) {
			return;
		}
		// Drop finished instances (golem removed / out of range) so re-entering range restarts them.
		Iterator<Map.Entry<UUID, DoomAuraSoundInstance>> iterator = ACTIVE_AURAS.entrySet().iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getValue().isDone()) {
				iterator.remove();
			}
		}
		if (++ticks % SCAN_INTERVAL_TICKS != 0) {
			return;
		}
		for (DrPepperGolemEntity golem : world.getEntitiesByClass(
				DrPepperGolemEntity.class,
				client.player.getBoundingBox().expand(SCAN_RANGE),
				golem -> true)) {
			double rangeSq = DoomAuraSoundInstance.RANGE * DoomAuraSoundInstance.RANGE;
			if (client.player.squaredDistanceTo(golem) > rangeSq || ACTIVE_AURAS.containsKey(golem.getUuid())) {
				continue;
			}
			DoomAuraSoundInstance instance = new DoomAuraSoundInstance(golem);
			ACTIVE_AURAS.put(golem.getUuid(), instance);
			client.getSoundManager().play(instance);
		}
	}

	private static void stopAll(MinecraftClient client) {
		for (DoomAuraSoundInstance instance : ACTIVE_AURAS.values()) {
			instance.forceStop();
			client.getSoundManager().stop(instance);
		}
		ACTIVE_AURAS.clear();
	}
}
