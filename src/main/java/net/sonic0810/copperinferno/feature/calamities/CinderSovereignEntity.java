package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 7 "Cinder Sovereign": a blazing tyrant of cinders (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 450, SCALE 2.5). Summoned with a Cinder Beacon.
 * On top of the vanilla blaze volleys, every 70 ticks with a target it erupts in a ring
 * of 8 small fireballs (SmallFireballEntity(World, LivingEntity, Vec3d), the vanilla
 * blaze projectile). Drops per {@code loot_table/entities/cinder_sovereign.json}.
 */
public class CinderSovereignEntity extends BlazeEntity {
	private static final int BARRAGE_INTERVAL_TICKS = 70;
	private static final int BARRAGE_COUNT = 8;
	private static final double BARRAGE_SPEED = 0.4;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.cinder_sovereign"),
			BossBar.Color.YELLOW, BossBar.Style.NOTCHED_10);

	public CinderSovereignEntity(EntityType<? extends BlazeEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.getTarget() != null && this.age % BARRAGE_INTERVAL_TICKS == 0) {
			fireballRing(world);
		}
	}

	/** Ring of 8 small fireballs radiating horizontally from the boss, plus a flame burst. */
	private void fireballRing(ServerWorld world) {
		for (int i = 0; i < BARRAGE_COUNT; i++) {
			double angle = Math.PI * 2.0 * i / BARRAGE_COUNT;
			Vec3d velocity = new Vec3d(Math.cos(angle) * BARRAGE_SPEED, 0.05, Math.sin(angle) * BARRAGE_SPEED);
			world.spawnEntity(new SmallFireballEntity(world, this, velocity));
		}
		world.spawnParticles(ParticleTypes.FLAME,
				this.getX(), this.getBodyY(0.5), this.getZ(), 20, 0.3, 0.4, 0.3, 0.02);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
