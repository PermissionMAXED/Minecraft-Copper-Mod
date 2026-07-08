package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;

/**
 * A hyper-carbonated lemon-lime slime. Behavioral tweak: it hops twice as often as a vanilla
 * slime ({@link #getTicksUntilNextJump()} halved). Size handling (random size on natural spawn,
 * split on death) is inherited from {@link SlimeEntity}; like vanilla slimes, only the smallest
 * size drops loot ({@code loot_table/entities/fizz_slime.json} -&gt; Fizz Globules).
 */
public class FizzSlimeEntity extends SlimeEntity {
	public FizzSlimeEntity(EntityType<? extends SlimeEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected int getTicksUntilNextJump() {
		return Math.max(1, super.getTicksUntilNextJump() / 2);
	}
}
