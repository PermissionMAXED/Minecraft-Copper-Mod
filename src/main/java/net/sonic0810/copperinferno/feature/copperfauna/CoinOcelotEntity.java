package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * An ocelot with a gleaming, coin-gold pelt. Behavioral tweak: it glitters — a client-side
 * golden dust sparkle follows it around (same particle pattern as {@code DrPepperGolemEntity}).
 * {@code createChild} is overridden only for type consistency (vanilla hard-codes
 * {@code EntityType.OCELOT}; same fix as the infernomobs Cinder Strider).
 * Drops Burnished Coins ({@code loot_table/entities/coin_ocelot.json}).
 */
public class CoinOcelotEntity extends OcelotEntity {
	private static final DustParticleEffect COIN_PARTICLE = new DustParticleEffect(0xE8C34A, 0.7f);

	public CoinOcelotEntity(EntityType<? extends OcelotEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tick() {
		super.tick();
		World world = this.getEntityWorld();
		if (world.isClient() && this.getRandom().nextInt(5) == 0) {
			world.addParticleClient(
					COIN_PARTICLE,
					this.getParticleX(0.5),
					this.getRandomBodyY() + 0.1,
					this.getParticleZ(0.5),
					0.0,
					0.01,
					0.0);
		}
	}

	@Override
	public CoinOcelotEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new CoinOcelotEntity(CopperFaunaFeature.COIN_OCELOT, world);
	}
}
