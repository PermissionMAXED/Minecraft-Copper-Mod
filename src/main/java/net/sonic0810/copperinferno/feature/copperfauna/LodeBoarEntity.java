package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.world.World;

/**
 * A boar with lodestone-heavy tusks. Behavioral tweak: KNOCKBACK_RESISTANCE 0.8 (vanilla pig:
 * 0.0) in the default attributes registered by {@link CopperFaunaFeature} — it barely budges
 * when hit. Drops Lode Hides ({@code loot_table/entities/lode_boar.json}).
 */
public class LodeBoarEntity extends PigEntity {
	public LodeBoarEntity(EntityType<? extends PigEntity> type, World world) {
		super(type, world);
	}
}
