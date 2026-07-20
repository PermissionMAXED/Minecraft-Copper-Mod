package net.sonic0810.copperinferno.feature.cuisine;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

/**
 * "Cinder Skin" — a crust of baked cinder hardens the skin. The payload is a flat
 * {@code EntityAttributes.ARMOR} +4 per level ({@code Operation.ADD_VALUE}) attribute
 * modifier attached at registration in {@link CuisineFeature#init()} (chaining
 * {@code StatusEffect.addAttributeModifier}, signature verified via javap; same pattern as
 * {@code feature/infernofx}'s Oxidized effect).
 */
public class CinderSkinEffect extends StatusEffect {
	/** Baked cinder brown. */
	public static final int COLOR = 0x8A5A44;

	public CinderSkinEffect() {
		super(StatusEffectCategory.BENEFICIAL, COLOR);
	}
}
