package net.sonic0810.copperinferno.feature.infernofx;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

/**
 * "Oxidized" — verdigris crust stiffens the joints. The -30% movement-speed payload is an
 * attribute modifier ({@code EntityAttributes.MOVEMENT_SPEED},
 * {@code EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL}, verified via javap); it is
 * attached at registration in {@link InfernoFxFeature#init()} because
 * {@code StatusEffect.addAttributeModifier} is a chaining instance method.
 */
public class OxidizedEffect extends StatusEffect {
	/** Oxidized-copper verdigris green. */
	public static final int COLOR = 0x6FB08E;

	public OxidizedEffect() {
		super(StatusEffectCategory.HARMFUL, COLOR);
	}
}
