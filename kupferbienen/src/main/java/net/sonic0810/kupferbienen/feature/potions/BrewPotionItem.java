package net.sonic0810.kupferbienen.feature.potions;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.text.Text;

/**
 * Base class for the oxidation brewing chain items. Extends {@link PotionItem} because the
 * vanilla {@code BrewingRecipeRegistry.Builder} asserts (via {@code assertPotion}) that every
 * item-recipe input/output and registered potion type is a {@code PotionItem}.
 *
 * <p>Two vanilla behaviours are undone here:
 * <ul>
 * <li>{@code PotionItem#getDefaultStack} force-sets water potion contents, which would clobber
 * the default {@code POTION_CONTENTS} component configured in the item settings; we return the
 * plain default stack instead so the configured components survive (e.g. in the creative menu).</li>
 * <li>{@code PotionItem#getName} derives the name from the potion contents
 * ("item...effect.&lt;potion&gt;"); these items always use their own fixed translation key.</li>
 * </ul>
 */
public class BrewPotionItem extends PotionItem {
	public BrewPotionItem(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack getDefaultStack() {
		return new ItemStack(this);
	}

	@Override
	public Text getName(ItemStack stack) {
		return Text.translatable(this.getTranslationKey());
	}
}
