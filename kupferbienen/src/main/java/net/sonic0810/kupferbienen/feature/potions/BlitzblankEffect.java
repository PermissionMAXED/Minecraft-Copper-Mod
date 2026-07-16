package net.sonic0810.kupferbienen.feature.potions;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

/**
 * "Blitzblank" marker effect applied by the Potion of Cleansing and the cleansing splash
 * vial. Purely a visible marker (the gameplay payload is a separate Haste effect on the
 * drink), mirroring the DrPepperKickEffect pattern; ctor
 * {@code StatusEffect(StatusEffectCategory, int)} verified via javap.
 */
public class BlitzblankEffect extends StatusEffect {
	/** Pale polished-copper gold, matching the cleansing potion tint (ENTOXIDATION_COLOR). */
	public static final int COLOR = 0xFFD9A0;

	public BlitzblankEffect() {
		super(StatusEffectCategory.BENEFICIAL, COLOR);
	}
}
