package net.sonic0810.copperinferno.feature.drpepper.client;

import java.lang.reflect.Method;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModSounds;
import net.sonic0810.copperinferno.feature.drpepper.DrPepperFeature;

/**
 * Client-side setup for Dr.Pepper: while the local player has the "Dr.Pepper kick" status effect,
 * a DOOM-style track plays at full master volume and a ~40% radial-smear motion blur post effect
 * ({@code copper_inferno:motion_blur}) is applied. Both stop on the falling edge of the effect.
 *
 * <p>Motion blur note: 1.21.9 exposes {@code GameRenderer#clearPostProcessor()} and
 * {@code #getPostProcessorId()} publicly, but {@code setPostProcessor(Identifier)} is PRIVATE
 * (verified via javap; it is only invoked by vanilla when spectating certain mobs). The shared
 * mixin configs are core-owned, so this feature calls the setter reflectively, resolving the
 * runtime method name through Fabric's {@code MappingResolver} (intermediary
 * {@code method_62904}), which works both in dev (yarn names) and in production (intermediary).
 * If the lookup ever fails the blur is skipped gracefully; sound and effects are unaffected.
 */
public final class DrPepperFeatureClient {
	private DrPepperFeatureClient() {
	}

	private static final Identifier MOTION_BLUR_ID = CopperInferno.id("motion_blur");
	/** Intermediary name of {@code GameRenderer#setPostProcessor(Identifier)}. */
	private static final String SET_POST_PROCESSOR_INTERMEDIARY = "method_62904";

	private static boolean kickActive;
	private static SoundInstance doomTrack;
	private static Method setPostProcessor;
	private static boolean setPostProcessorUnavailable;

	public static void initClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			boolean hasKick = client.player != null
					&& DrPepperFeature.DR_PEPPER_KICK != null
					&& client.player.hasStatusEffect(DrPepperFeature.DR_PEPPER_KICK);
			if (hasKick && !kickActive) {
				start(client);
			} else if (!hasKick && kickActive) {
				stop(client);
			}
			kickActive = hasKick;
		});
	}

	private static void start(MinecraftClient client) {
		doomTrack = PositionedSoundInstance.master(ModSounds.DOOM_KICK, 1.0f);
		client.getSoundManager().play(doomTrack);
		enableMotionBlur(client);
	}

	private static void stop(MinecraftClient client) {
		if (doomTrack != null) {
			client.getSoundManager().stop(doomTrack);
			doomTrack = null;
		}
		// Only clear if the active post processor is ours (don't stomp creeper/spider vision etc).
		if (MOTION_BLUR_ID.equals(client.gameRenderer.getPostProcessorId())) {
			client.gameRenderer.clearPostProcessor();
		}
	}

	private static void enableMotionBlur(MinecraftClient client) {
		if (setPostProcessorUnavailable) {
			return;
		}
		try {
			if (setPostProcessor == null) {
				String name = FabricLoader.getInstance().getMappingResolver().mapMethodName(
						"intermediary",
						"net.minecraft.class_757",
						SET_POST_PROCESSOR_INTERMEDIARY,
						"(Lnet/minecraft/class_2960;)V");
				Method method;
				try {
					method = GameRenderer.class.getDeclaredMethod(name, Identifier.class);
				} catch (NoSuchMethodException e) {
					// Dev-environment fallback: yarn name.
					method = GameRenderer.class.getDeclaredMethod("setPostProcessor", Identifier.class);
				}
				method.setAccessible(true);
				setPostProcessor = method;
			}
			setPostProcessor.invoke(client.gameRenderer, MOTION_BLUR_ID);
		} catch (ReflectiveOperationException e) {
			setPostProcessorUnavailable = true;
			CopperInferno.LOGGER.warn(
					"[COPPER INFERNO 1] Could not access GameRenderer#setPostProcessor; Dr.Pepper motion blur disabled",
					e);
		}
	}
}
