package net.sonic0810.copperinferno.feature.bosspantheon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Pantheon boss 5 "The Mint King": a monarch-sized mint slime (attributes in
 * {@link BossPantheonFeature}: MAX_HEALTH 500, SCALE 1.8, FOLLOW_RANGE 48; the slime SIZE
 * is forced to 6 on the server and the {@link #setSize} override re-asserts the 500-HP /
 * 12-damage royal statline that vanilla setSize would otherwise overwrite with
 * size-derived values). Three mechanics on top of vanilla slime AI: a timed "brain freeze"
 * burst (Slowness II + Mining Fatigue to visible survival players within 6 blocks, slime
 * particles + squish), a phase 2 below 50% health (persisted flag: two elite honor-guard
 * slimes leap out, 100 ticks of Resistance and permanently faster hops via the
 * {@link #getTicksUntilNextJump} override), and a royal death-split - instead of vanilla
 * mint-king children, four ELITE vanilla slimes (size 2, Strength + Speed) burst out; the
 * vanilla split is suppressed by shrinking to size 1 right before removal. Drops Royal
 * Jellies, Mint Crystals and the Mint Crown
 * ({@code loot_table/entities/the_mint_king.json}).
 */
public class TheMintKingEntity extends SlimeEntity {
	public static final int BOSS_SIZE = 6;
	private static final double BOSS_MAX_HEALTH = 500.0;
	private static final double BOSS_ATTACK_DAMAGE = 12.0;
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int BURST_INTERVAL_TICKS = 140;
	private static final double BURST_RANGE = 6.0;
	private static final int BURST_EFFECT_TICKS = 80;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.the_mint_king"),
			BossBar.Color.WHITE, BossBar.Style.NOTCHED_20);

	/** One-shot phase flag, persisted so a reloaded phase-2 king never re-summons his guard. */
	private boolean phaseTwo;

	public TheMintKingEntity(EntityType<? extends SlimeEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon). The
		// royal size is forced server-side only; clients receive it via the data tracker.
		if (!world.isClient()) {
			this.setPersistent();
			this.setSize(BOSS_SIZE, true);
		}
	}

	/**
	 * Vanilla setSize derives MAX_HEALTH (size*size), MOVEMENT_SPEED and ATTACK_DAMAGE
	 * (size) from the size; at the royal size those bases are re-asserted to the boss
	 * statline so the FabricDefaultAttributeRegistry values in
	 * {@link BossPantheonFeature} hold (SlimeEntity.setSize(int, boolean) is public and
	 * also runs on NBT load - verified via javap/bytecode).
	 */
	@Override
	public void setSize(int size, boolean heal) {
		super.setSize(size, heal);
		if (size >= BOSS_SIZE) {
			EntityAttributeInstance maxHealth = this.getAttributeInstance(EntityAttributes.MAX_HEALTH);
			if (maxHealth != null) {
				maxHealth.setBaseValue(BOSS_MAX_HEALTH);
			}
			EntityAttributeInstance attack = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
			if (attack != null) {
				attack.setBaseValue(BOSS_ATTACK_DAMAGE);
			}
			if (heal) {
				this.setHealth(this.getMaxHealth());
			}
		}
	}

	/** Phase 2: the king hops noticeably faster (vanilla waits 10-30 ticks + jump delay). */
	@Override
	protected int getTicksUntilNextJump() {
		int ticks = super.getTicksUntilNextJump();
		return this.phaseTwo ? Math.max(1, ticks / 2) : ticks;
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
		if (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.phaseTwo = true;
			summonHonorGuard(world);
		}
		if (this.age % BURST_INTERVAL_TICKS == 0) {
			mintBurst(world);
		}
	}

	/**
	 * Brain freeze: Slowness II + Mining Fatigue to every survival/adventure player the
	 * king can see within 6 blocks (creative/spectator players and players behind walls
	 * are unaffected), plus a minty slime-particle burst and a royal squish.
	 */
	private void mintBurst(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(BURST_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, BURST_EFFECT_TICKS, 1));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, BURST_EFFECT_TICKS, 0));
		}
		world.spawnParticles(ParticleTypes.ITEM_SLIME,
				this.getX(), this.getBodyY(0.5), this.getZ(), 40, 1.2, 0.8, 1.2, 0.0);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_SLIME_SQUISH,
				SoundCategory.HOSTILE, 2.0f, 0.5f);
	}

	/** Phase 2 burst: two elite honor-guard slimes + 100 ticks of Resistance. */
	private void summonHonorGuard(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			spawnEliteSlime(world, (this.random.nextDouble() - 0.5) * 4.0,
					(this.random.nextDouble() - 0.5) * 4.0);
		}
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
		world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 1.5, 1.0, 1.5, 0.0);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_SLIME_JUMP,
				SoundCategory.HOSTILE, 2.0f, 0.6f);
	}

	/**
	 * Royal death-split: four ELITE vanilla slimes (size 2, Strength + Speed for 60s)
	 * burst out instead of vanilla mint-king children. Vanilla SlimeEntity.remove splits
	 * only while getSize() > 1 (bytecode-verified), so shrinking to size 1 right before
	 * super.remove suppresses the vanilla split; the entity is already dead, so the
	 * statline reset from setSize(1) is moot.
	 */
	@Override
	public void remove(RemovalReason reason) {
		if (!this.getEntityWorld().isClient() && this.isDead() && this.getSize() > 1
				&& this.getEntityWorld() instanceof ServerWorld serverWorld) {
			for (int i = 0; i < 4; i++) {
				spawnEliteSlime(serverWorld, (this.random.nextDouble() - 0.5) * 3.0,
						(this.random.nextDouble() - 0.5) * 3.0);
			}
			serverWorld.spawnParticles(ParticleTypes.ITEM_SLIME,
					this.getX(), this.getBodyY(0.5), this.getZ(), 60, 1.5, 1.0, 1.5, 0.0);
			this.setSize(1, false);
		}
		super.remove(reason);
	}

	/** One elite guard: a vanilla slime, size 2, buffed with Strength + Speed for 60 seconds. */
	private void spawnEliteSlime(ServerWorld world, double dx, double dz) {
		SlimeEntity elite = new SlimeEntity(EntityType.SLIME, world);
		elite.setSize(2, true);
		elite.refreshPositionAndAngles(this.getX() + dx, this.getY(), this.getZ() + dz,
				this.getYaw(), 0.0f);
		elite.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 0));
		elite.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 0));
		world.spawnEntity(elite);
	}

	@Override
	protected void writeCustomData(WriteView view) {
		super.writeCustomData(view);
		view.putBoolean(PHASE_TWO_KEY, this.phaseTwo);
	}

	@Override
	protected void readCustomData(ReadView view) {
		super.readCustomData(view);
		this.phaseTwo = view.getBoolean(PHASE_TWO_KEY, false);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
