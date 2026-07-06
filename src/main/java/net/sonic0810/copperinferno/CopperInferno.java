package net.sonic0810.copperinferno;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.core.CopperInfernoCore;
import net.sonic0810.copperinferno.feature.armor.CopperArmorFeature;
import net.sonic0810.copperinferno.feature.decostone.DecoStoneFeature;
import net.sonic0810.copperinferno.feature.drpepper.DrPepperFeature;
import net.sonic0810.copperinferno.feature.extras.ExtrasFeature;
import net.sonic0810.copperinferno.feature.foods.FoodsFeature;
import net.sonic0810.copperinferno.feature.gear.GearFeature;
import net.sonic0810.copperinferno.feature.glasslight.GlassLightFeature;
import net.sonic0810.copperinferno.feature.golem.DrPepperGolemFeature;
import net.sonic0810.copperinferno.feature.inferno.InfernoFeature;
import net.sonic0810.copperinferno.feature.masonry.MasonryFeature;
import net.sonic0810.copperinferno.feature.materials.MaterialsFeature;
import net.sonic0810.copperinferno.feature.music.MusicFeature;
import net.sonic0810.copperinferno.feature.sodablocks.SodaBlocksFeature;
import net.sonic0810.copperinferno.feature.statue.PlayerStatueFeature;
import net.sonic0810.copperinferno.feature.tools.CopperToolsFeature;
import net.sonic0810.copperinferno.feature.utilityblocks.UtilityBlocksFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopperInferno implements ModInitializer {
	public static final String MOD_ID = "copper_inferno";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("[COPPER INFERNO 1] Initializing - by Sonic0810");
		CopperInfernoCore.init();
		CopperArmorFeature.init();
		CopperToolsFeature.init();
		PlayerStatueFeature.init();
		DrPepperFeature.init();
		DrPepperGolemFeature.init();
		ExtrasFeature.init();
		MasonryFeature.init();
		DecoStoneFeature.init();
		InfernoFeature.init();
		SodaBlocksFeature.init();
		GlassLightFeature.init();
		UtilityBlocksFeature.init();
		MaterialsFeature.init();
		FoodsFeature.init();
		GearFeature.init();
		MusicFeature.init();
	}
}
