package net.sonic0810.copperinferno.core;

import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Shared registry keys + helpers for the Inferno dimension ({@code copper_inferno:inferno}).
 * The dimension itself is defined via datapack JSON (dimension + dimension_type) by the
 * infernodim feature worker; this class only holds the keys and teleport plumbing.
 */
public final class ModDimensions {
	private ModDimensions() {
	}

	public static final RegistryKey<World> INFERNO_WORLD = RegistryKey.of(RegistryKeys.WORLD, CopperInferno.id("inferno"));
	public static final RegistryKey<DimensionType> INFERNO_DIMENSION_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, CopperInferno.id("inferno"));

	/**
	 * Resolves the Inferno {@link ServerWorld}. Null if the dimension JSON is not loaded
	 * (mirrors {@code MinecraftServer.getWorld}).
	 */
	public static ServerWorld infernoWorld(MinecraftServer server) {
		return server.getWorld(INFERNO_WORLD);
	}

	/**
	 * Teleports an entity to {@code pos} in {@code destination} (works across dimensions),
	 * preserving yaw/pitch and zeroing velocity.
	 */
	public static void teleport(Entity entity, ServerWorld destination, Vec3d pos) {
		// NO_OP is a static constant on TeleportTarget itself (of type PostDimensionTransition);
		// verified via javap - the inner interface has no NO_OP field.
		entity.teleportTo(new TeleportTarget(destination, pos, Vec3d.ZERO, entity.getYaw(), entity.getPitch(),
				TeleportTarget.NO_OP));
	}
}
