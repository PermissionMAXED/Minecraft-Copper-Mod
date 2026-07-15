package net.sonic0810.kupferbienen.feature.potions;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

/**
 * "Patina-Haut" marker effect applied by the Potion of Oxidation and the oxidation splash
 * vial. Purely a visible marker (the gameplay payload is a separate Resistance effect on the
 * drink), mirroring the DrPepperKickEffect pattern; ctor
 * {@code StatusEffect(StatusEffectCategory, int)} verified via javap.
 */
public class PatinaHautEffect extends StatusEffect {
	/** Verdigris green, matching the oxidation potion tint. */
	public static final int COLOR = 0x43A047;

	public PatinaHautEffect() {
		super(StatusEffectCategory.BENEFICIAL, COLOR);
	}
}
