package net.sonic0810.kupferbienen;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.sonic0810.kupferbienen.core.ModCreativeTab;
import net.sonic0810.kupferbienen.feature.bees.BeesFeature;
import net.sonic0810.kupferbienen.feature.flora.FloraFeature;
import net.sonic0810.kupferbienen.feature.machines.MachinesFeature;
import net.sonic0810.kupferbienen.feature.potions.PotionsFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kupferbienen implements ModInitializer {
	public static final String MOD_ID = "kupferbienen";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("[KUPFERBIENEN] Initializing - by Sonic0810");
		// ModCreativeTab.init() MUST run first: features register their ItemGroupEvents
		// callbacks against the tab keys created there (BIENEN/ALCHEMIE/MASCHINEN).
		ModCreativeTab.init();
		// FloraFeature before BeesFeature: flora will register the extra bee-attractive
		// flowers/crops that bee-side content (worldgen, apiary checks) may reference.
		FloraFeature.init();
		BeesFeature.init();
		// MachinesFeature after BeesFeature: machinery processes bee produce
		// (KUPFERWABE/GRUENSPANPOLLEN item fields must already be non-null).
		MachinesFeature.init();
		// PotionsFeature last: the brewing chain references KUPFERWABE/GRUENSPANPOLLEN and
		// may later reference machine outputs as brewing ingredients.
		PotionsFeature.init();
	}
}
