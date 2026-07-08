package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A goat with scorch-marked flanks that rams like the vanilla one. Tweak: breeding stays in the
 * family — vanilla {@code GoatEntity.createChild} hard-codes {@code EntityType.GOAT} (plus
 * screaming-goat inheritance), so without this covariant override two scorch goats would
 * produce a vanilla goat (same fix as the infernomobs Cinder Strider). Brain-driven ram AI is
 * inherited. Drops Scorched Horns ({@code loot_table/entities/scorch_goat.json}).
 */
public class ScorchGoatEntity extends GoatEntity {
	public ScorchGoatEntity(EntityType<? extends GoatEntity> type, World world) {
		super(type, world);
	}

	@Override
	public ScorchGoatEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new ScorchGoatEntity(InfernoFaunaFeature.SCORCH_GOAT, world);
	}
}
