package net.sonic0810.copperinferno.feature.infernomobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.World;

/**
 * An oversized silverfish crusted in slag. Behavior is pure vanilla silverfish; the distinction
 * comes from the default attributes registered in {@link InfernoMobsFeature} (SCALE 1.4,
 * ATTACK_DAMAGE 4). Drops Crawler Fangs ({@code loot_table/entities/slag_crawler.json}).
 */
public class SlagCrawlerEntity extends SilverfishEntity {
	public SlagCrawlerEntity(EntityType<? extends SilverfishEntity> type, World world) {
		super(type, world);
	}
}
