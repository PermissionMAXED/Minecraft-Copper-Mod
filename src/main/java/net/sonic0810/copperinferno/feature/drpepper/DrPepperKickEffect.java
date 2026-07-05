package net.sonic0810.copperinferno.feature.drpepper;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

/**
 * The visible "Dr.Pepper kick" status effect. It is purely a marker: the gameplay payload
 * (Speed V) is a separate effect on the drink, and the client watches for this effect to drive
 * the DOOM soundtrack and the motion-blur post effect.
 */
public class DrPepperKickEffect extends StatusEffect {
	/** Dark maroon, matching the can. */
	public static final int COLOR = 0x6E0F1A;

	public DrPepperKickEffect() {
		super(StatusEffectCategory.BENEFICIAL, COLOR);
	}
}
