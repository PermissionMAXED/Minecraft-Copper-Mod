package net.sonic0810.copperinferno.core.client;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.sonic0810.copperinferno.core.ModComponents;
import net.sonic0810.copperinferno.core.ModParticles;
import net.sonic0810.copperinferno.core.oxidation.OxidizableEquipmentItem;

/**
 * Client bootstrap for the shared core infrastructure.
 */
public final class CoreClient {
	private CoreClient() {
	}

	public static void initClient() {
		ParticleFactoryRegistry.getInstance().register(ModParticles.COPPER_SPARKLE, CopperSparkleParticle.Factory::new);

		// "Waxed" tooltip line for ANY waxed stack whose item does not render it itself: vanilla
		// stage-0 items, the OxidizableAxe/Shovel/HoeItem subclasses and CopperHornItem (their
		// ItemOxidation.appendOxidationTooltip call skips the "Waxed" line for non-
		// OxidizableEquipmentItem stacks). Only OxidizableEquipmentItem appends its own line,
		// so skip those here — this keeps exactly ONE "Waxed" line per stack.
		ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
			if (!(stack.getItem() instanceof OxidizableEquipmentItem)
					&& stack.getOrDefault(ModComponents.WAXED, Boolean.FALSE)) {
				lines.add(Text.translatable("tooltip.copper_inferno.waxed").formatted(Formatting.GRAY));
			}
		});
	}
}
