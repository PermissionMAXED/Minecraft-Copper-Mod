package net.sonic0810.copperinferno.feature.cuisine;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

/**
 * "Sugar Rush" — a fizzy-sugar overdose: move faster AND break blocks faster at once.
 *
 * <p>The payload is pure attribute modifiers ({@code EntityAttributes.MOVEMENT_SPEED} +20%
 * and {@code EntityAttributes.BLOCK_BREAK_SPEED} +25% per level,
 * {@code Operation.ADD_MULTIPLIED_TOTAL}); they are attached at registration in
 * {@link CuisineFeature#init()} because {@code StatusEffect.addAttributeModifier} is a
 * chaining instance method (signature verified via javap, same pattern as
 * {@code feature/infernofx}'s Oxidized effect).
 */
public class SugarRushEffect extends StatusEffect {
	/** Fizzy candy pink. */
	public static final int COLOR = 0xF06EAA;

	public SugarRushEffect() {
		super(StatusEffectCategory.BENEFICIAL, COLOR);
	}
}
