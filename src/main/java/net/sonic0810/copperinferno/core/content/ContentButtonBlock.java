package net.sonic0810.copperinferno.core.content;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.ButtonBlock;

/**
 * {@link ButtonBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it
 * publicly constructible for the v4 wood-set buttons (same pattern as the utilityblocks feature's
 * PublicButtonBlock).
 */
public class ContentButtonBlock extends ButtonBlock {
	public ContentButtonBlock(BlockSetType blockSetType, int pressTicks, AbstractBlock.Settings settings) {
		super(blockSetType, pressTicks, settings);
	}
}
