package net.sonic0810.kupferbienen.feature.potions;

import java.util.List;
import java.util.Optional;

import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.conversion.EntityConversionContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.kupferbienen.Kupferbienen;
import net.sonic0810.kupferbienen.feature.bees.BeesFeature;
import net.sonic0810.kupferbienen.feature.bees.GruenspanbieneEntity;
import net.sonic0810.kupferbienen.feature.bees.KupferbieneEntity;

/**
 * The thrown splash vial. On impact (server side) it weathers copper in a 5x5x5 cube:
 * <ul>
 * <li>OXIDIZE (wurfphiole_oxidation): advances every oxidizable block one stage via the static
 * {@code Oxidizable.getIncreasedOxidationBlock(Block)} + {@code Block.getStateWithProperties}
 * (block-based like the cleanse path, so both directions traverse the same BiMap
 * symmetrically). Waxed copper is absent from the BiMap and stays protected — intended.</li>
 * <li>CLEANSE (wurfphiole_entoxidation): applies the static
 * {@code Oxidizable.getDecreasedOxidationState(BlockState)} (empty Optional for non-copper).</li>
 * </ul>
 * Fabric's {@code OxidizableBlocksRegistry} feeds the same BiMaps, so modded oxidizable copper
 * weathers too. Splash-affected living entities within 2.5 blocks get the matching marker
 * effect plus the paired real effect (oxidation -&gt; Resistance, cleanse -&gt; Haste; 200
 * ticks each, mirroring the drinkable Trank payloads), and the vial converts Kupferbienen
 * &lt;-&gt; Gruenspanbienen caught in the blast via
 * {@code MobEntity.convertTo(EntityType, EntityConversionContext, Finalizer)} (verified via
 * javap). Converting at least one bee grants the thrower the
 * {@code kupferbienen:kupferbienen/verwandlung} advancement. The collision hook is
 * {@code ProjectileEntity#onCollision(HitResult)}; the super-then-discard shape mirrors the
 * vanilla SnowballEntity bytecode.
 */
public class GeworfenePhioleEntity extends ThrownItemEntity {
	/** 5x5x5 cube => +/- 2 blocks around the impact block. */
	private static final int CUBE_RADIUS = 2;
	private static final double EFFECT_RADIUS = 2.5;
	private static final int EFFECT_DURATION_TICKS = 200;

	public GeworfenePhioleEntity(EntityType<? extends GeworfenePhioleEntity> type, World world) {
		super(type, world);
	}

	public GeworfenePhioleEntity(World world, LivingEntity owner, ItemStack stack) {
		super(PotionsFeature.GEWORFENE_PHIOLE, owner, world, stack);
	}

	@Override
	protected Item getDefaultItem() {
		return PotionsFeature.WURFPHIOLE_OXIDATION;
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
			boolean cleanse = this.getStack().isOf(PotionsFeature.WURFPHIOLE_ENTOXIDATION);
			Vec3d impact = hitResult.getPos();
			BlockPos center = BlockPos.ofFloored(impact.x, impact.y, impact.z);

			int changed = 0;
			for (BlockPos pos : BlockPos.iterate(
					center.add(-CUBE_RADIUS, -CUBE_RADIUS, -CUBE_RADIUS),
					center.add(CUBE_RADIUS, CUBE_RADIUS, CUBE_RADIUS))) {
				BlockState state = serverWorld.getBlockState(pos);
				Optional<BlockState> next;
				if (cleanse) {
					next = Oxidizable.getDecreasedOxidationState(state);
				} else {
					next = Oxidizable.getIncreasedOxidationBlock(state.getBlock())
							.map(block -> block.getStateWithProperties(state));
				}
				if (next.isPresent()) {
					serverWorld.setBlockState(pos, next.get(), Block.NOTIFY_ALL);
					changed++;
				}
			}

			// Marker effect plus the paired real effect (matches the drinkable Trank payload).
			RegistryEntry<StatusEffect> marker = cleanse ? PotionsFeature.BLITZBLANK : PotionsFeature.PATINA_HAUT;
			RegistryEntry<StatusEffect> payload = cleanse ? StatusEffects.HASTE : StatusEffects.RESISTANCE;
			List<LivingEntity> targets = serverWorld.getEntitiesByClass(LivingEntity.class,
					Box.of(impact, EFFECT_RADIUS * 2.0, EFFECT_RADIUS * 2.0, EFFECT_RADIUS * 2.0),
					e -> e.isAlive() && e.isAffectedBySplashPotions()
							&& e.getEntityPos().distanceTo(impact) <= EFFECT_RADIUS);
			for (LivingEntity target : targets) {
				target.addStatusEffect(new StatusEffectInstance(marker, EFFECT_DURATION_TICKS, 0));
				target.addStatusEffect(new StatusEffectInstance(payload, EFFECT_DURATION_TICKS, 0));
			}

			convertBees(serverWorld, impact, cleanse);

			// SCRAPE axe-flecks for the scrape-clean look, HAPPY_VILLAGER green sparkles for
			// verdigris.
			serverWorld.spawnParticles(cleanse ? ParticleTypes.SCRAPE : ParticleTypes.HAPPY_VILLAGER,
					impact.x, impact.y, impact.z, 40 + changed * 2, 1.6, 1.2, 1.6, 0.0);
			serverWorld.playSound(null, impact.x, impact.y, impact.z,
					SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			this.discard();
		}
	}

	/**
	 * Stretch goal: the oxidation vial verdigrises copper bees, the cleansing vial polishes
	 * verdigris bees back. Uses the 3-arg {@code convertTo} overload (default conversion
	 * SpawnReason) with a no-op finalizer; conversion keeps no equipment (bees carry none).
	 * Converting at least one bee grants the thrower (projectile owner) the
	 * {@code kupferbienen:kupferbienen/verwandlung} advancement via its code-only
	 * {@code converted_bee} impossible-trigger criterion.
	 */
	private void convertBees(ServerWorld serverWorld, Vec3d impact, boolean cleanse) {
		Box blast = Box.of(impact, EFFECT_RADIUS * 2.0, EFFECT_RADIUS * 2.0, EFFECT_RADIUS * 2.0);
		int converted = 0;
		if (cleanse) {
			for (GruenspanbieneEntity bee : serverWorld.getEntitiesByClass(GruenspanbieneEntity.class, blast,
					e -> e.isAlive() && e.getEntityPos().distanceTo(impact) <= EFFECT_RADIUS)) {
				if (bee.convertTo(BeesFeature.KUPFERBIENE,
						EntityConversionContext.create(bee, false, false), result -> {
						}) != null) {
					converted++;
				}
			}
		} else {
			for (KupferbieneEntity bee : serverWorld.getEntitiesByClass(KupferbieneEntity.class, blast,
					e -> e.isAlive() && e.getEntityPos().distanceTo(impact) <= EFFECT_RADIUS)) {
				if (bee.convertTo(BeesFeature.GRUENSPANBIENE,
						EntityConversionContext.create(bee, false, false), result -> {
						}) != null) {
					converted++;
				}
			}
		}
		if (converted >= 1 && this.getOwner() instanceof ServerPlayerEntity player) {
			AdvancementEntry entry = serverWorld.getServer().getAdvancementLoader()
					.get(Kupferbienen.id("kupferbienen/verwandlung"));
			if (entry != null) {
				player.getAdvancementTracker().grantCriterion(entry, "converted_bee");
			}
		}
	}
}
