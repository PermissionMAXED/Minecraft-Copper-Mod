package net.sonic0810.copperinferno.feature.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

/**
 * Copper Conduction — lightning that strikes a copper block electrifies it for 10 seconds:
 * the block glows (a vanilla {@code minecraft:light} block is placed in the air cell above it
 * and cleaned up afterwards, giving REAL temporary luminance without touching the copper
 * block itself) and periodically zaps nearby mobs with lightning damage.
 *
 * <p>Detection runs on {@link ServerTickEvents#END_WORLD_TICK}: every live
 * {@link LightningEntity} is inspected once (deduped by UUID) and the block column at/below
 * the bolt is checked for a vanilla copper block (anything implementing {@link Oxidizable} —
 * waxed copper does NOT implement it, so waxed copper is "insulated" and never electrifies).
 */
public final class CopperConductionSystem {
	private CopperConductionSystem() {
	}

	/** How long a struck copper block stays electrified. */
	private static final int ELECTRIFIED_TICKS = 200;
	/** A zap fires every 10 ticks against mobs within this radius of the copper block. */
	private static final double ZAP_RADIUS = 3.0;
	private static final float ZAP_DAMAGE = 2.0f;

	/** One electrified copper block; {@code lightPos} is the temporary light block (or null). */
	private static final class Electrified {
		final BlockPos copperPos;
		final BlockPos lightPos;
		final long expiryTick;

		Electrified(BlockPos copperPos, BlockPos lightPos, long expiryTick) {
			this.copperPos = copperPos;
			this.lightPos = lightPos;
			this.expiryTick = expiryTick;
		}
	}

	private static final Map<ServerWorld, List<Electrified>> ELECTRIFIED = new WeakHashMap<>();
	/** Lightning bolts already processed (bolts live ~a second; the set is pruned regularly). */
	private static final Map<ServerWorld, Set<UUID>> HANDLED_BOLTS = new WeakHashMap<>();

	public static void init() {
		ServerTickEvents.END_WORLD_TICK.register(CopperConductionSystem::tick);
	}

	private static void tick(ServerWorld world) {
		detectStrikes(world);
		tickElectrified(world);
	}

	private static void detectStrikes(ServerWorld world) {
		Set<UUID> handled = HANDLED_BOLTS.computeIfAbsent(world, w -> new HashSet<>());
		boolean sawBolt = false;
		for (Entity entity : world.iterateEntities()) {
			if (!(entity instanceof LightningEntity bolt)) {
				continue;
			}
			sawBolt = true;
			if (!handled.add(bolt.getUuid())) {
				continue;
			}
			BlockPos struck = bolt.getBlockPos();
			// The bolt sits ON the struck block; check its cell and the two below it.
			for (BlockPos pos : new BlockPos[] {struck, struck.down(), struck.down(2)}) {
				if (world.getBlockState(pos).getBlock() instanceof Oxidizable) {
					electrify(world, pos);
					break;
				}
			}
		}
		if (!sawBolt && !handled.isEmpty()) {
			handled.clear();
		}
	}

	private static void electrify(ServerWorld world, BlockPos copperPos) {
		BlockPos above = copperPos.up();
		BlockPos lightPos = null;
		if (world.getBlockState(above).isAir()) {
			world.setBlockState(above, Blocks.LIGHT.getDefaultState());
			lightPos = above;
		}
		ELECTRIFIED.computeIfAbsent(world, w -> new ArrayList<>())
				.add(new Electrified(copperPos, lightPos, world.getTime() + ELECTRIFIED_TICKS));
		world.spawnParticles(ParticleTypes.ELECTRIC_SPARK,
				copperPos.getX() + 0.5, copperPos.getY() + 1.2, copperPos.getZ() + 0.5,
				40, 0.6, 0.6, 0.6, 0.15);
	}

	private static void tickElectrified(ServerWorld world) {
		List<Electrified> entries = ELECTRIFIED.get(world);
		if (entries == null || entries.isEmpty()) {
			return;
		}
		long time = world.getTime();
		for (Iterator<Electrified> it = entries.iterator(); it.hasNext(); ) {
			Electrified entry = it.next();
			boolean stillCopper = world.getBlockState(entry.copperPos).getBlock() instanceof Oxidizable;
			if (time >= entry.expiryTick || !stillCopper) {
				cleanUpLight(world, entry);
				it.remove();
				continue;
			}
			double cx = entry.copperPos.getX() + 0.5;
			double cy = entry.copperPos.getY() + 0.5;
			double cz = entry.copperPos.getZ() + 0.5;
			if (time % 5 == 0) {
				world.spawnParticles(ParticleTypes.ELECTRIC_SPARK, cx, cy + 0.7, cz, 6, 0.5, 0.5, 0.5, 0.1);
			}
			if (time % 10 == 0) {
				Box zapBox = new Box(entry.copperPos).expand(ZAP_RADIUS);
				for (MobEntity mob : world.getEntitiesByClass(MobEntity.class, zapBox,
						m -> m.isAlive() && m.squaredDistanceTo(cx, cy, cz) <= ZAP_RADIUS * ZAP_RADIUS)) {
					mob.damage(world, world.getDamageSources().lightningBolt(), ZAP_DAMAGE);
				}
			}
		}
	}

	private static void cleanUpLight(ServerWorld world, Electrified entry) {
		if (entry.lightPos != null && world.getBlockState(entry.lightPos).isOf(Blocks.LIGHT)) {
			world.removeBlock(entry.lightPos, false);
		}
	}
}
