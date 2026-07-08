package net.sonic0810.copperinferno.core.boss;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Composition helper wrapping a {@link ServerBossBar} for boss mobs. The v3 bosses extend
 * different vanilla mob classes, so there is no common boss base class; each boss entity holds
 * one of these and forwards {@code onStartedTrackingBy} / {@code onStoppedTrackingBy} /
 * {@code mobTick} (via {@link #update}) to it.
 */
public final class BossBarHolder {
	private final ServerBossBar bar;

	public BossBarHolder(Text name, BossBar.Color color, BossBar.Style style) {
		this.bar = new ServerBossBar(name, color, style);
	}

	/** Forward from {@code Entity.onStartedTrackingBy(ServerPlayerEntity)}. */
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		this.bar.addPlayer(player);
	}

	/** Forward from {@code Entity.onStoppedTrackingBy(ServerPlayerEntity)}. */
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		this.bar.removePlayer(player);
	}

	/** Sync the bar's percentage with the boss's current health; call from the boss's tick. */
	public void update(LivingEntity boss) {
		this.bar.setPercent(boss.getHealth() / boss.getMaxHealth());
	}

	/** The wrapped bar, for extra tweaks (visibility, darken sky, ...). */
	public ServerBossBar bar() {
		return this.bar;
	}
}
