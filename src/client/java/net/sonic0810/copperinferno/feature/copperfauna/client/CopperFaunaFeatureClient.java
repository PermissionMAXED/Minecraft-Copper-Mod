package net.sonic0810.copperinferno.feature.copperfauna.client;

import net.minecraft.client.render.entity.BatEntityRenderer;
import net.minecraft.client.render.entity.BeeEntityRenderer;
import net.minecraft.client.render.entity.CatEntityRenderer;
import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EndermiteEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.FoxEntityRenderer;
import net.minecraft.client.render.entity.FrogEntityRenderer;
import net.minecraft.client.render.entity.GoatEntityRenderer;
import net.minecraft.client.render.entity.OcelotEntityRenderer;
import net.minecraft.client.render.entity.ParrotEntityRenderer;
import net.minecraft.client.render.entity.PhantomEntityRenderer;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.RabbitEntityRenderer;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.client.render.entity.SilverfishEntityRenderer;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.SlimeEntityRenderer;
import net.minecraft.client.render.entity.SnowGolemEntityRenderer;
import net.minecraft.client.render.entity.SpiderEntityRenderer;
import net.minecraft.client.render.entity.WitchEntityRenderer;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.state.BatEntityRenderState;
import net.minecraft.client.render.entity.state.BeeEntityRenderState;
import net.minecraft.client.render.entity.state.CatEntityRenderState;
import net.minecraft.client.render.entity.state.ChickenEntityRenderState;
import net.minecraft.client.render.entity.state.CowEntityRenderState;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.client.render.entity.state.FelineEntityRenderState;
import net.minecraft.client.render.entity.state.FoxEntityRenderState;
import net.minecraft.client.render.entity.state.FrogEntityRenderState;
import net.minecraft.client.render.entity.state.GoatEntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.ParrotEntityRenderState;
import net.minecraft.client.render.entity.state.PhantomEntityRenderState;
import net.minecraft.client.render.entity.state.PigEntityRenderState;
import net.minecraft.client.render.entity.state.RabbitEntityRenderState;
import net.minecraft.client.render.entity.state.SheepEntityRenderState;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;
import net.minecraft.client.render.entity.state.SlimeEntityRenderState;
import net.minecraft.client.render.entity.state.SnowGolemEntityRenderState;
import net.minecraft.client.render.entity.state.WitchEntityRenderState;
import net.minecraft.client.render.entity.state.WolfEntityRenderState;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.feature.copperfauna.CopperFaunaFeature;

/**
 * Client-side setup for the 24 Copper Fauna mobs. Each type reuses its vanilla renderer
 * (Context-only ctors, verified via javap) SUBCLASSED anonymously to override the renderer's
 * public {@code getTexture(<state>)} method (the 1.21.9 render-state contract:
 * {@code LivingEntityRenderer.getTexture(S)} feeds {@code getRenderLayer}, so overriding it
 * swaps the whole body texture) so every mob uses its own
 * {@code copper_inferno:textures/entity/<id>.png}. The explicit type witness on each
 * {@code EntityRendererFactories.<VanillaBase>register} call pins the factory's type parameter
 * to the vanilla base entity — exactly the bound the vanilla renderers are typed to — the same
 * proven pattern as {@code InfernoMobsFeatureClient}, plus the subclassing.
 *
 * <p>Feature-renderer layers keep their vanilla textures (sheep wool, wolf collar, creeper
 * charge, snow-golem pumpkin, phantom eyes, slime overlay): only the base skin is remapped,
 * which is the intended look.
 */
public final class CopperFaunaFeatureClient {
	private CopperFaunaFeatureClient() {
	}

	private static Identifier tex(String id) {
		return CopperInferno.id("textures/entity/" + id + ".png");
	}

	public static void initClient() {
		Identifier copperBeetle = tex("copper_beetle");
		EntityRendererFactories.<SilverfishEntity>register(CopperFaunaFeature.COPPER_BEETLE,
				ctx -> new SilverfishEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(LivingEntityRenderState state) {
						return copperBeetle;
					}
				});
		Identifier verdigrisSlime = tex("verdigris_slime");
		EntityRendererFactories.<SlimeEntity>register(CopperFaunaFeature.VERDIGRIS_SLIME,
				ctx -> new SlimeEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(SlimeEntityRenderState state) {
						return verdigrisSlime;
					}
				});
		Identifier patinaBat = tex("patina_bat");
		EntityRendererFactories.<BatEntity>register(CopperFaunaFeature.PATINA_BAT,
				ctx -> new BatEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(BatEntityRenderState state) {
						return patinaBat;
					}
				});
		Identifier sparkHare = tex("spark_hare");
		EntityRendererFactories.<RabbitEntity>register(CopperFaunaFeature.SPARK_HARE,
				ctx -> new RabbitEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(RabbitEntityRenderState state) {
						return sparkHare;
					}
				});
		Identifier lodeBoar = tex("lode_boar");
		EntityRendererFactories.<PigEntity>register(CopperFaunaFeature.LODE_BOAR,
				ctx -> new PigEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(PigEntityRenderState state) {
						return lodeBoar;
					}
				});
		Identifier gildedFinch = tex("gilded_finch");
		EntityRendererFactories.<ParrotEntity>register(CopperFaunaFeature.GILDED_FINCH,
				ctx -> new ParrotEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(ParrotEntityRenderState state) {
						return gildedFinch;
					}
				});
		Identifier coilSpider = tex("coil_spider");
		EntityRendererFactories.<SpiderEntity>register(CopperFaunaFeature.COIL_SPIDER,
				ctx -> new SpiderEntityRenderer<SpiderEntity>(ctx) {
					@Override
					public Identifier getTexture(LivingEntityRenderState state) {
						return coilSpider;
					}
				});
		Identifier rustWolf = tex("rust_wolf");
		EntityRendererFactories.<WolfEntity>register(CopperFaunaFeature.RUST_WOLF,
				ctx -> new WolfEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(WolfEntityRenderState state) {
						return rustWolf;
					}
				});
		Identifier statueMite = tex("statue_mite");
		EntityRendererFactories.<EndermiteEntity>register(CopperFaunaFeature.STATUE_MITE,
				ctx -> new EndermiteEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(LivingEntityRenderState state) {
						return statueMite;
					}
				});
		Identifier tarnishWitch = tex("tarnish_witch");
		EntityRendererFactories.<WitchEntity>register(CopperFaunaFeature.TARNISH_WITCH,
				ctx -> new WitchEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(WitchEntityRenderState state) {
						return tarnishWitch;
					}
				});
		Identifier conductorCreeper = tex("conductor_creeper");
		EntityRendererFactories.<CreeperEntity>register(CopperFaunaFeature.CONDUCTOR_CREEPER,
				ctx -> new CreeperEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(CreeperEntityRenderState state) {
						return conductorCreeper;
					}
				});
		Identifier ampereBee = tex("ampere_bee");
		EntityRendererFactories.<BeeEntity>register(CopperFaunaFeature.AMPERE_BEE,
				ctx -> new BeeEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(BeeEntityRenderState state) {
						return ampereBee;
					}
				});
		Identifier oxidizedZombie = tex("oxidized_zombie");
		EntityRendererFactories.<ZombieEntity>register(CopperFaunaFeature.OXIDIZED_ZOMBIE,
				ctx -> new ZombieEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(ZombieEntityRenderState state) {
						return oxidizedZombie;
					}
				});
		Identifier patinaSkeleton = tex("patina_skeleton");
		EntityRendererFactories.<SkeletonEntity>register(CopperFaunaFeature.PATINA_SKELETON,
				ctx -> new SkeletonEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(SkeletonEntityRenderState state) {
						return patinaSkeleton;
					}
				});
		Identifier copperGolemite = tex("copper_golemite");
		EntityRendererFactories.<SnowGolemEntity>register(CopperFaunaFeature.COPPER_GOLEMITE,
				ctx -> new SnowGolemEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(SnowGolemEntityRenderState state) {
						return copperGolemite;
					}
				});
		Identifier sparkFox = tex("spark_fox");
		EntityRendererFactories.<FoxEntity>register(CopperFaunaFeature.SPARK_FOX,
				ctx -> new FoxEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(FoxEntityRenderState state) {
						return sparkFox;
					}
				});
		Identifier verdigrisFrog = tex("verdigris_frog");
		EntityRendererFactories.<FrogEntity>register(CopperFaunaFeature.VERDIGRIS_FROG,
				ctx -> new FrogEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(FrogEntityRenderState state) {
						return verdigrisFrog;
					}
				});
		Identifier coilChicken = tex("coil_chicken");
		EntityRendererFactories.<ChickenEntity>register(CopperFaunaFeature.COIL_CHICKEN,
				ctx -> new ChickenEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(ChickenEntityRenderState state) {
						return coilChicken;
					}
				});
		Identifier lodeCow = tex("lode_cow");
		EntityRendererFactories.<CowEntity>register(CopperFaunaFeature.LODE_COW,
				ctx -> new CowEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(CowEntityRenderState state) {
						return lodeCow;
					}
				});
		Identifier patinaSheep = tex("patina_sheep");
		EntityRendererFactories.<SheepEntity>register(CopperFaunaFeature.PATINA_SHEEP,
				ctx -> new SheepEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(SheepEntityRenderState state) {
						return patinaSheep;
					}
				});
		Identifier gutterCat = tex("gutter_cat");
		EntityRendererFactories.<CatEntity>register(CopperFaunaFeature.GUTTER_CAT,
				ctx -> new CatEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(CatEntityRenderState state) {
						return gutterCat;
					}
				});
		Identifier thunderGoat = tex("thunder_goat");
		EntityRendererFactories.<GoatEntity>register(CopperFaunaFeature.THUNDER_GOAT,
				ctx -> new GoatEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(GoatEntityRenderState state) {
						return thunderGoat;
					}
				});
		Identifier scrapPhantom = tex("scrap_phantom");
		EntityRendererFactories.<PhantomEntity>register(CopperFaunaFeature.SCRAP_PHANTOM,
				ctx -> new PhantomEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(PhantomEntityRenderState state) {
						return scrapPhantom;
					}
				});
		Identifier coinOcelot = tex("coin_ocelot");
		EntityRendererFactories.<OcelotEntity>register(CopperFaunaFeature.COIN_OCELOT,
				ctx -> new OcelotEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(FelineEntityRenderState state) {
						return coinOcelot;
					}
				});
	}
}
