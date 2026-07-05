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

		// "Waxed" tooltip line for ANY stack carrying the component (covers vanilla stage-0 items).
		// OxidizableEquipmentItem already appends its own line via appendTooltip, so skip those.
		ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
			if (!(stack.getItem() instanceof OxidizableEquipmentItem)
					&& stack.getOrDefault(ModComponents.WAXED, Boolean.FALSE)) {
				lines.add(Text.translatable("tooltip.copper_inferno.waxed").formatted(Formatting.GRAY));
			}
		});
	}
}
