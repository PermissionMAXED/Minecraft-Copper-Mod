package net.sonic0810.copperinferno;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.core.CopperInfernoCore;
import net.sonic0810.copperinferno.feature.advancements.AdvancementsFeature;
import net.sonic0810.copperinferno.feature.armor.CopperArmorFeature;
import net.sonic0810.copperinferno.feature.arsenal.ArsenalFeature;
import net.sonic0810.copperinferno.feature.artifacts.ArtifactsFeature;
import net.sonic0810.copperinferno.feature.bossdoom.BossDoomFeature;
import net.sonic0810.copperinferno.feature.bosslords.BossLordsFeature;
import net.sonic0810.copperinferno.feature.bosspantheon.BossPantheonFeature;
import net.sonic0810.copperinferno.feature.brews.BrewsFeature;
import net.sonic0810.copperinferno.feature.chromacopper.ChromaCopperFeature;
import net.sonic0810.copperinferno.feature.cinderstone.CinderStoneFeature;
import net.sonic0810.copperinferno.feature.companions.CompanionsFeature;
import net.sonic0810.copperinferno.feature.constructs.ConstructsFeature;
import net.sonic0810.copperinferno.feature.copperdeco.CopperDecoFeature;
import net.sonic0810.copperinferno.feature.copperfauna.CopperFaunaFeature;
import net.sonic0810.copperinferno.feature.cuisine.CuisineFeature;
import net.sonic0810.copperinferno.feature.decostone.DecoStoneFeature;
import net.sonic0810.copperinferno.feature.depthstone.DepthStoneFeature;
import net.sonic0810.copperinferno.feature.drpepper.DrPepperFeature;
import net.sonic0810.copperinferno.feature.emberstorm.EmberStormFeature;
import net.sonic0810.copperinferno.feature.enchants.EnchantsFeature;
import net.sonic0810.copperinferno.feature.extras.ExtrasFeature;
import net.sonic0810.copperinferno.feature.fishing.FishingFeature;
import net.sonic0810.copperinferno.feature.foods.FoodsFeature;
import net.sonic0810.copperinferno.feature.gear.GearFeature;
import net.sonic0810.copperinferno.feature.gemalloy.GemAlloyFeature;
import net.sonic0810.copperinferno.feature.glasslight.GlassLightFeature;
import net.sonic0810.copperinferno.feature.golem.DrPepperGolemFeature;
import net.sonic0810.copperinferno.feature.handbook.HandbookFeature;
import net.sonic0810.copperinferno.feature.inferno.InfernoFeature;
import net.sonic0810.copperinferno.feature.infernoboss.InfernoBossFeature;
import net.sonic0810.copperinferno.feature.infernodim.InfernoDimensionFeature;
import net.sonic0810.copperinferno.feature.infernofauna.InfernoFaunaFeature;
import net.sonic0810.copperinferno.feature.infernoflora.InfernoFloraFeature;
import net.sonic0810.copperinferno.feature.infernofoods.InfernoFoodsFeature;
import net.sonic0810.copperinferno.feature.infernofx.InfernoFxFeature;
import net.sonic0810.copperinferno.feature.infernium.InferniumFeature;
import net.sonic0810.copperinferno.feature.infernomobs.InfernoMobsFeature;
import net.sonic0810.copperinferno.feature.masonry.MasonryFeature;
import net.sonic0810.copperinferno.feature.materials.MaterialsFeature;
import net.sonic0810.copperinferno.feature.music.MusicFeature;
import net.sonic0810.copperinferno.feature.scorchwood.ScorchWoodFeature;
import net.sonic0810.copperinferno.feature.shrines.ShrinesFeature;
import net.sonic0810.copperinferno.feature.sodablocks.SodaBlocksFeature;
import net.sonic0810.copperinferno.feature.sodafauna.SodaFaunaFeature;
import net.sonic0810.copperinferno.feature.statue.PlayerStatueFeature;
import net.sonic0810.copperinferno.feature.systems.SystemsFeature;
import net.sonic0810.copperinferno.feature.tools.CopperToolsFeature;
import net.sonic0810.copperinferno.feature.trades.TradesFeature;
import net.sonic0810.copperinferno.feature.utilityblocks.UtilityBlocksFeature;
import net.sonic0810.copperinferno.feature.warhorns.WarHornsFeature;
import net.sonic0810.copperinferno.feature.wildworld.WildWorldFeature;
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
		// v4 mega-content-update wave (fixed order, pre-wired by WP1 so parallel work
		// packages never edit this file again): building palettes first (chromacopper,
		// depthstone, scorchwood), then materials (gemalloy), equipment/food (arsenal,
		// cuisine), the three fauna packs, constructs, the three boss tiers, and finally
		// the cross-cutting packages (systems, wildworld, artifacts). All are empty
		// skeletons until their work package lands. HandbookFeature stays LAST so it can
		// reference every other feature's content.
		ChromaCopperFeature.init();
		DepthStoneFeature.init();
		ScorchWoodFeature.init();
		GemAlloyFeature.init();
		ArsenalFeature.init();
		CuisineFeature.init();
		CopperFaunaFeature.init();
		InfernoFaunaFeature.init();
		SodaFaunaFeature.init();
		ConstructsFeature.init();
		BossLordsFeature.init();
		BossPantheonFeature.init();
		BossDoomFeature.init();
		SystemsFeature.init();
		WildWorldFeature.init();
		ArtifactsFeature.init();
		// v4.1 wave, pre-wired (WP0) so parallel work packages never edit this file again:
		// empty skeletons until their work package lands. HandbookFeature stays LAST so it
		// can reference every other feature's content.
		EnchantsFeature.init();
		TradesFeature.init();
		AdvancementsFeature.init();
		ShrinesFeature.init();
		FishingFeature.init();
		EmberStormFeature.init();
		CompanionsFeature.init();
		WarHornsFeature.init();
		BrewsFeature.init();
		HandbookFeature.init();
	}
}
