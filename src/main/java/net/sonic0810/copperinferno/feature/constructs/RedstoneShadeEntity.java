package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.world.World;

/**
 * A vex-like spirit of loose redstone charge. Behavior is vanilla vex plus ONE tweak: it
 * permanently crackles with a visible glow - see {@link #tick()}. Drops Redstone Filaments
 * ({@code loot_table/entities/redstone_shade.json}).
 */
public class RedstoneShadeEntity extends VexEntity {
	public RedstoneShadeEntity(EntityType<? extends VexEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tick() {
		super.tick();
		// TWEAK: live redstone spark. Refreshed every 5s server-side, so the glow outline
		// never lapses. addStatusEffect(StatusEffectInstance) verified via javap.
		if (!this.getEntityWorld().isClient() && this.age % 100 == 0) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 140, 0));
		}
	}
}
