package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Ash Yak - a shaggy, ash-dusted yak roaming the Inferno's grey plains in small herds. Behavior is pure vanilla cow (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces ash yak babies: vanilla
 * {@code CowEntity.createChild} hard-codes {@code EntityType.COW}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Yak Haunch ({@code loot_table/entities/ash_yak.json}).
 */
public class AshYakEntity extends CowEntity {
	public AshYakEntity(EntityType<? extends CowEntity> type, World world) {
		super(type, world);
	}

	@Override
	public AshYakEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new AshYakEntity(MoltenFaunaFeature.ASH_YAK, world);
	}
}
