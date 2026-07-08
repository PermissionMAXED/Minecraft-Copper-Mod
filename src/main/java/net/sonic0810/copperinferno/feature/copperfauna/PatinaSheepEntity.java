package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.world.World;

/**
 * A sheep whose skin has weathered to patina under the fleece. Behavioral tweak: MAX_HEALTH
 * 12 (vanilla sheep: 8) in the default attributes registered by {@link CopperFaunaFeature}.
 * Drops Burnished Coins ({@code loot_table/entities/patina_sheep.json}).
 */
public class PatinaSheepEntity extends SheepEntity {
	public PatinaSheepEntity(EntityType<? extends SheepEntity> type, World world) {
		super(type, world);
	}
}
