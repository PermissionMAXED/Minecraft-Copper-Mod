package net.sonic0810.copperinferno;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.core.CopperInfernoCore;
import net.sonic0810.copperinferno.feature.armor.CopperArmorFeature;
import net.sonic0810.copperinferno.feature.charwood.CharwoodFeature;
import net.sonic0810.copperinferno.feature.cinderstone.CinderStoneFeature;
import net.sonic0810.copperinferno.feature.copperdeco.CopperDecoFeature;
import net.sonic0810.copperinferno.feature.decostone.DecoStoneFeature;
import net.sonic0810.copperinferno.feature.drpepper.DrPepperFeature;
import net.sonic0810.copperinferno.feature.extras.ExtrasFeature;
import net.sonic0810.copperinferno.feature.foods.FoodsFeature;
import net.sonic0810.copperinferno.feature.forgeparts.ForgePartsFeature;
import net.sonic0810.copperinferno.feature.gear.GearFeature;
import net.sonic0810.copperinferno.feature.glasslight.GlassLightFeature;
import net.sonic0810.copperinferno.feature.golem.DrPepperGolemFeature;
import net.sonic0810.copperinferno.feature.handbook.HandbookFeature;
import net.sonic0810.copperinferno.feature.inferno.InfernoFeature;
import net.sonic0810.copperinferno.feature.infernoboss.InfernoBossFeature;
import net.sonic0810.copperinferno.feature.infernocuisine.InfernoCuisineFeature;
import net.sonic0810.copperinferno.feature.infernodim.InfernoDimensionFeature;
import net.sonic0810.copperinferno.feature.infernoflora.InfernoFloraFeature;
import net.sonic0810.copperinferno.feature.infernofoods.InfernoFoodsFeature;
import net.sonic0810.copperinferno.feature.infernofx.InfernoFxFeature;
import net.sonic0810.copperinferno.feature.infernium.InferniumFeature;
import net.sonic0810.copperinferno.feature.infernomobs.InfernoMobsFeature;
import net.sonic0810.copperinferno.feature.kilnstone.KilnstoneFeature;
import net.sonic0810.copperinferno.feature.masonry.MasonryFeature;
import net.sonic0810.copperinferno.feature.materials.MaterialsFeature;
import net.sonic0810.copperinferno.feature.moltenmetal.MoltenMetalFeature;
import net.sonic0810.copperinferno.feature.music.MusicFeature;
import net.sonic0810.copperinferno.feature.nightslate.NightslateFeature;
import net.sonic0810.copperinferno.feature.pyrestone.PyrestoneFeature;
import net.sonic0810.copperinferno.feature.smolderquartz.SmolderQuartzFeature;
import net.sonic0810.copperinferno.feature.sodablocks.SodaBlocksFeature;
import net.sonic0810.copperinferno.feature.statue.PlayerStatueFeature;
import net.sonic0810.copperinferno.feature.titanforge.TitanForgeFeature;
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
		// This order curates the creative-tab flow: each feature registers its
		// ItemGroupEvents callback in init(), and Fabric runs the callbacks in
		// registration order (equipment -> gadgets -> materials -> drinks/foods ->
		// music/lore -> statue, then the building-block features for the BLOCKS tab).
		// Hard dependency constraints that MUST be respected when reordering:
		// - CopperInfernoCore.init() FIRST (shared registries, sounds, creative tabs).
		// - ExtrasFeature BEFORE GearFeature (GearFeature hooks the copper-horn
		//   oxidation chain onto ExtrasFeature.COPPER_HORN via a requireNonNull guard).
		// - DrPepperFeature BEFORE DrPepperGolemFeature (golem consumes Dr.Pepper content).
		CopperInfernoCore.init();
		CopperArmorFeature.init();
		CopperToolsFeature.init();
		ExtrasFeature.init();
		GearFeature.init();
		MaterialsFeature.init();
		DrPepperFeature.init();
		DrPepperGolemFeature.init();
		FoodsFeature.init();
		MusicFeature.init();
		PlayerStatueFeature.init();
		MasonryFeature.init();
		DecoStoneFeature.init();
		InfernoFeature.init();
		SodaBlocksFeature.init();
		GlassLightFeature.init();
		UtilityBlocksFeature.init();
		// v3 "The Inferno Dimension" features (fixed order; HandbookFeature LAST so it can
		// reference every other feature's content).
		InfernoDimensionFeature.init();
		CinderStoneFeature.init();
		InfernoFloraFeature.init();
		CopperDecoFeature.init();
		InferniumFeature.init();
		InfernoMobsFeature.init();
		InfernoBossFeature.init();
		InfernoFoodsFeature.init();
		InfernoFxFeature.init();
		// v4 content wave (order within this group is not constrained; all consumed
		// ids are registered by the features above).
		PyrestoneFeature.init();
		MoltenMetalFeature.init();
		SmolderQuartzFeature.init();
		NightslateFeature.init();
		CharwoodFeature.init();
		KilnstoneFeature.init();
		ForgePartsFeature.init();
		InfernoCuisineFeature.init();
		TitanForgeFeature.init();
		HandbookFeature.init();
	}
}
