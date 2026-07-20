package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A goat that headbutts like a thunderclap. Behavioral tweak: ATTACK_DAMAGE 4 (vanilla goat:
 * 2) in the default attributes registered by {@link CopperFaunaFeature}. {@code createChild}
 * is overridden only for type consistency (vanilla hard-codes {@code EntityType.GOAT}; same
 * fix as the infernomobs Cinder Strider). Drops Storm Cells
 * ({@code loot_table/entities/thunder_goat.json}).
 */
public class ThunderGoatEntity extends GoatEntity {
	public ThunderGoatEntity(EntityType<? extends GoatEntity> type, World world) {
		super(type, world);
	}

	@Override
	public ThunderGoatEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new ThunderGoatEntity(CopperFaunaFeature.THUNDER_GOAT, world);
	}
}
