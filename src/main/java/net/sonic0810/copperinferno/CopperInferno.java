package net.sonic0810.copperinferno;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.core.CopperInfernoCore;
import net.sonic0810.copperinferno.feature.armor.CopperArmorFeature;
import net.sonic0810.copperinferno.feature.drpepper.DrPepperFeature;
import net.sonic0810.copperinferno.feature.extras.ExtrasFeature;
import net.sonic0810.copperinferno.feature.golem.DrPepperGolemFeature;
import net.sonic0810.copperinferno.feature.statue.PlayerStatueFeature;
import net.sonic0810.copperinferno.feature.tools.CopperToolsFeature;
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
	}
}
