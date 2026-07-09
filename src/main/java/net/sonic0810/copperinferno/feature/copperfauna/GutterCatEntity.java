package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A scruffy alley cat that prowls for vermin. Behavioral tweak: it actively hunts silverfish —
 * including {@link CopperBeetleEntity Copper Beetles}, which extend {@code SilverfishEntity} —
 * on top of the vanilla cat prey goals. {@code createChild} is overridden only for type
 * consistency (vanilla hard-codes {@code EntityType.CAT}; same fix as the infernomobs Cinder
 * Strider). Drops Rust Fangs
 * ({@code loot_table/entities/gutter_cat.json}).
 */
public class GutterCatEntity extends CatEntity {
	public GutterCatEntity(EntityType<? extends CatEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, SilverfishEntity.class, true));
	}

	@Override
	public GutterCatEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new GutterCatEntity(CopperFaunaFeature.GUTTER_CAT, world);
	}
}
