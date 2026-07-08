package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Slag Buffalo - a broad-shouldered buffalo wallowing in warm slag pits along the Slag Sea's shores. Behavior is pure vanilla cow (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces slag buffalo babies: vanilla
 * {@code CowEntity.createChild} hard-codes {@code EntityType.COW}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Buffalo Hump ({@code loot_table/entities/slag_buffalo.json}).
 */
public class SlagBuffaloEntity extends CowEntity {
	public SlagBuffaloEntity(EntityType<? extends CowEntity> type, World world) {
		super(type, world);
	}

	@Override
	public SlagBuffaloEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new SlagBuffaloEntity(MoltenFaunaFeature.SLAG_BUFFALO, world);
	}
}
