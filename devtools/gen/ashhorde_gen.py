#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "ashhorde" feature (25 hostile mobs).

The Ash Horde: 25 hostile mobs native to the Inferno biomes, each a THIN subclass of a
vanilla base reusing the vanilla renderer (keeps vanilla textures), 5 per base:
ZombieEntity, SkeletonEntity, SpiderEntity, CreeperEntity, HuskEntity. Every mob ships a
spawn egg + a unique drop item + an entity loot table + one drop-conversion recipe.

Idempotent: running it any number of times produces byte-identical output (all pixel art
is deterministically seeded via genlib.rng_for). Emits by DEFAULT (no flags), mirroring
devtools/gen/titanforge_gen.py:
  - items/<id>.json model-definitions + models/item/<id>.json (genlib emitters; the
    1.21.9 two-file contract, exactly like infernomobs_gen.py)
  - 16x16 item textures (Pillow, deterministic pixel art; the spawn-egg silhouette is
    copied verbatim from infernomobs_gen.spawn_egg)
  - entity loot tables data/copper_inferno/loot_table/entities/<mob>.json
    (dr_pepper_golem/ash_bat schema incl. "random_sequence")
  - recipes data/copper_inferno/recipe/ashhorde/*.json (25 drop conversions; every
    recipe contains at least one ashhorde id, so input sets cannot collide with
    vanilla or other features)
  - lang fragments EN + DE (assets/copper_inferno/lang/fragments{,_de}/ashhorde.json)
  - src/main/java/.../feature/ashhorde/: AshHordeFeature.java, AshHordeHandbook.java
    and the 25 entity subclasses (literal ids only, so devtools/audit_assets.py check
    (f) and devtools/check_handbook.py can parse them)
  - src/client/java/.../feature/ashhorde/client/AshHordeFeatureClient.java
  - devtools/hooks/ashhorde.txt (integration hook file)

1.21.9 API notes (ALL verified with javap against the loom yarn-mapped jars in
/workspace/.gradle/loom-cache/minecraftMaven, mirroring feature/infernomobs):
  ZombieEntity(EntityType<? extends ZombieEntity>, World)          .createZombieAttributes()
  SkeletonEntity(EntityType<? extends SkeletonEntity>, World)
  AbstractSkeletonEntity.createAbstractSkeletonAttributes()
  SpiderEntity(EntityType<? extends SpiderEntity>, World)          .createSpiderAttributes()
  CreeperEntity(EntityType<? extends CreeperEntity>, World)        .createCreeperAttributes()
  HuskEntity(EntityType<? extends HuskEntity>, World)  (vanilla registers HUSK with
    ZombieEntity.createZombieAttributes(), DefaultAttributeRegistry bytecode-verified)
  EntityType builder chains copied 1:1 from the vanilla EntityType bytecode:
    zombie   dimensions(0.6f, 1.95f).eyeHeight(1.74f).passengerAttachments(2.0125f)
             .vehicleAttachment(-0.7f).maxTrackingRange(8).notAllowedInPeaceful()
    skeleton dimensions(0.6f, 1.99f).eyeHeight(1.74f).vehicleAttachment(-0.7f)
             .maxTrackingRange(8).notAllowedInPeaceful()
    spider   dimensions(1.4f, 0.9f).eyeHeight(0.65f).passengerAttachments(0.765f)
             .maxTrackingRange(8).notAllowedInPeaceful()
    creeper  dimensions(0.6f, 1.7f).maxTrackingRange(8).notAllowedInPeaceful()
    husk     dimensions(0.6f, 1.95f).eyeHeight(1.74f).passengerAttachments(2.075f)
             .vehicleAttachment(-0.7f).maxTrackingRange(8).notAllowedInPeaceful()
  SpawnRestriction static block (bytecode): zombie/skeleton/spider/creeper use
    ON_GROUND + MOTION_BLOCKING_NO_LEAVES + HostileEntity::canSpawnInDark; husk uses
    HuskEntity::canSpawn == canSpawnInDark && (SpawnReason.isAnySpawner(reason)
    || world.isSkyVisible(pos)) — typed to EntityType<HuskEntity>, so reimplemented
    generically in AshHordeFeature.canAshHuskSpawn.
  Renderers (clientOnly jar): ZombieEntityRenderer/SkeletonEntityRenderer/
    SpiderEntityRenderer/CreeperEntityRenderer/HuskEntityRenderer all have
    Context-only ctors; EntityRendererFactories.register(EntityType<? extends T>,
    EntityRendererFactory<T>) is access-widened by Fabric's TAWs.
"""

import math
import sys
from collections import Counter, namedtuple
from pathlib import Path
from random import Random

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, write_json

RECIPES = DATA / "recipe" / "ashhorde"
FEATURE_DIR = ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "ashhorde"
CLIENT_DIR = ROOT / "src" / "client" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "ashhorde" / "client"

# Inferno biomes the Ash Horde spawns in: the original trio plus the harsh open
# infernodim2 biomes (soot_dunes, molten_delta). The canonical 7-biome list of the
# Inferno dimension lives in devtools/gen/infernodim2_gen.py (BIOMES/CLIMATE_POINTS);
# the lush verdigris_jungle and the quiet crystal_hollows are deliberately horde-free.
SPAWN_BIOMES = ["cinder_wastes", "ember_grove", "slag_sea", "soot_dunes", "molten_delta"]

# ---------------------------------------------------------------------------
# Vanilla bases (all signatures javap-verified, see module docstring).
# builder = the EntityType.Builder chain lines AFTER create(...), copied from the
# vanilla EntityType registration bytecode. attrs_owner/attrs_call = the vanilla
# create*Attributes() the subclass createAttributes() delegates to.
# ---------------------------------------------------------------------------
Base = namedtuple("Base", "cls attrs_owner attrs_call renderer builder predicate")

BASES = {
    "zombie": Base("ZombieEntity", "ZombieEntity", "createZombieAttributes", "ZombieEntityRenderer",
                   [".dimensions(0.6f, 1.95f)", ".eyeHeight(1.74f)", ".passengerAttachments(2.0125f)",
                    ".vehicleAttachment(-0.7f)", ".maxTrackingRange(8)", ".notAllowedInPeaceful()"],
                   "dark"),
    "skeleton": Base("SkeletonEntity", "AbstractSkeletonEntity", "createAbstractSkeletonAttributes",
                     "SkeletonEntityRenderer",
                     [".dimensions(0.6f, 1.99f)", ".eyeHeight(1.74f)", ".vehicleAttachment(-0.7f)",
                      ".maxTrackingRange(8)", ".notAllowedInPeaceful()"],
                     "dark"),
    "spider": Base("SpiderEntity", "SpiderEntity", "createSpiderAttributes", "SpiderEntityRenderer",
                   [".dimensions(1.4f, 0.9f)", ".eyeHeight(0.65f)", ".passengerAttachments(0.765f)",
                    ".maxTrackingRange(8)", ".notAllowedInPeaceful()"],
                   "dark"),
    "creeper": Base("CreeperEntity", "CreeperEntity", "createCreeperAttributes", "CreeperEntityRenderer",
                    [".dimensions(0.6f, 1.7f)", ".maxTrackingRange(8)", ".notAllowedInPeaceful()"],
                    "dark"),
    "husk": Base("HuskEntity", "ZombieEntity", "createZombieAttributes", "HuskEntityRenderer",
                 [".dimensions(0.6f, 1.95f)", ".eyeHeight(1.74f)", ".passengerAttachments(2.075f)",
                  ".vehicleAttachment(-0.7f)", ".maxTrackingRange(8)", ".notAllowedInPeaceful()"],
                 "husk"),
}

# ---------------------------------------------------------------------------
# The 25 mobs (5 per base). Fields:
#   mid       mob id                        base      BASES key
#   en/de     display names                 desc_en/de handbook flavor sentence
#   drop      drop item id                  den/dde    drop display names
#   dpl_en/de drop plural (handbook text)   tex        drop texture painter key
#   pal       drop texture palette key      hp/dmg/sc  tuned attribute values
#   w/gmin/gmax spawn weight + group size   cmin/cmax  loot count range
# ---------------------------------------------------------------------------
Mob = namedtuple("Mob", "mid base en de desc_en desc_de drop den dde dpl_en dpl_de "
                        "tex pal hp dmg sc w gmin gmax cmin cmax")

MOBS = [
    # --- ZombieEntity base -------------------------------------------------
    Mob("cinder_shambler", "zombie", "Cinder Shambler", "Zinderschlurfer",
        "A cinder-crusted zombie that shambles through drifting embers, hitting harder than its overworld kin.",
        "Ein zinderverkrusteter Zombie, der durch treibende Glut schlurft und härter zuschlägt als seine Oberwelt-Verwandten.",
        "shambler_rag", "Shambler Rag", "Schlurferlumpen", "Shambler Rags", "Schlurferlumpen",
        "cloth", "drab", 26.0, 4.0, 1.05, 12, 2, 4, 0.0, 2.0),
    Mob("ash_ghoul", "zombie", "Ash Ghoul", "Aschenghul",
        "A gaunt, grey ghoul caked in ash, quick to claw at anything warm.",
        "Ein hagerer, grauer Ghul voller Asche, der nach allem Warmen krallt.",
        "ghoul_tatter", "Ghoul Tatter", "Ghul-Fetzen", "Ghoul Tatters", "Ghul-Fetzen",
        "cloth", "pale", 22.0, 3.5, 0.98, 10, 1, 3, 0.0, 2.0),
    Mob("slag_rotter", "zombie", "Slag Rotter", "Schlackenmoderer",
        "A bloated rotter oozing molten slag, slow but brutally strong.",
        "Ein aufgedunsener Moderer, aus dem geschmolzene Schlacke sickert - langsam, aber brutal stark.",
        "rotter_sludge", "Rotter Sludge", "Moderschlamm", "Rotter Sludge", "Moderschlamm",
        "blob", "sludge", 30.0, 4.5, 1.1, 8, 1, 2, 0.0, 2.0),
    Mob("ember_thrall", "zombie", "Ember Thrall", "Glutknecht",
        "A shackled thrall bound to the Inferno's forges, still dragging its chains.",
        "Ein gefesselter Knecht der Inferno-Essen, der noch immer seine Ketten schleift.",
        "thrall_shackle", "Thrall Shackle", "Knechtsfessel", "Thrall Shackles", "Knechtsfesseln",
        "ring", "iron", 24.0, 4.0, 1.0, 9, 1, 3, 0.0, 2.0),
    Mob("soot_walker", "zombie", "Soot Walker", "Ru\u00dfwandler",
        "A soot-black walker that leaves smudged footprints wherever it prowls.",
        "Ein ru\u00dfschwarzer Wandler, der \u00fcberall verschmierte Fu\u00dfspuren hinterl\u00e4sst.",
        "walker_grime", "Walker Grime", "Wandlerru\u00df", "Walker Grime", "Wandlerru\u00df",
        "blob", "soot", 20.0, 3.0, 0.95, 11, 2, 4, 0.0, 2.0),
    # --- SkeletonEntity base -----------------------------------------------
    Mob("ash_archer", "skeleton", "Ash Archer", "Aschensch\u00fctze",
        "An ash-bleached skeleton archer whose arrows whistle through the cinder haze.",
        "Ein aschgebleichter Skelettsch\u00fctze, dessen Pfeile durch den Zinderdunst pfeifen.",
        "archer_arrowhead", "Archer Arrowhead", "Sch\u00fctzen-Pfeilspitze", "Archer Arrowheads", "Sch\u00fctzen-Pfeilspitzen",
        "arrowhead", "flint", 22.0, 2.5, 1.0, 12, 1, 3, 0.0, 2.0),
    Mob("cinder_bowman", "skeleton", "Cinder Bowman", "Zinderbogner",
        "A bowman of scorched bone that looses smouldering shots from the ridgelines.",
        "Ein Bogner aus versengtem Knochen, der glimmende Sch\u00fcsse von den Graten abfeuert.",
        "bowman_string", "Bowman String", "Bognersehne", "Bowman Strings", "Bognersehnen",
        "coil", "tan", 24.0, 3.0, 1.05, 10, 1, 3, 0.0, 2.0),
    Mob("slag_marksman", "skeleton", "Slag Marksman", "Schlackenscharfsch\u00fctze",
        "A slag-plated marksman, taller and steadier than a common skeleton.",
        "Ein schlackengepanzerter Scharfsch\u00fctze, gr\u00f6\u00dfer und ruhiger als ein gew\u00f6hnliches Skelett.",
        "marksman_bone", "Marksman Bone", "Scharfsch\u00fctzenknochen", "Marksman Bones", "Scharfsch\u00fctzenknochen",
        "bone", "bone", 26.0, 3.0, 1.1, 8, 1, 2, 0.0, 2.0),
    Mob("ember_rattler", "skeleton", "Ember Rattler", "Glutklapperer",
        "A rattling skeleton with embers glowing between its ribs.",
        "Ein klapperndes Skelett, zwischen dessen Rippen Glut gl\u00fcht.",
        "rattler_rib", "Rattler Rib", "Klapperrippe", "Rattler Ribs", "Klapperrippen",
        "bone", "warmbone", 20.0, 2.0, 0.95, 9, 1, 3, 0.0, 2.0),
    Mob("soot_skirmisher", "skeleton", "Soot Skirmisher", "Ru\u00dfpl\u00e4nkler",
        "A small, fast skirmisher that harries travellers in sooty packs.",
        "Ein kleiner, schneller Pl\u00e4nkler, der Reisende in ru\u00dfigen Trupps bedr\u00e4ngt.",
        "skirmisher_quiver", "Skirmisher Quiver", "Pl\u00e4nklerk\u00f6cher", "Skirmisher Quivers", "Pl\u00e4nklerk\u00f6cher",
        "quiver", "leather", 18.0, 2.5, 0.9, 11, 2, 4, 0.0, 2.0),
    # --- SpiderEntity base -------------------------------------------------
    Mob("ember_lurker", "spider", "Ember Lurker", "Glutlauerer",
        "A big spider with ember-lit eyes, lurking in the glow of lava pools.",
        "Eine gro\u00dfe Spinne mit glut-erhellten Augen, die im Schein der Lavabecken lauert.",
        "lurker_eye", "Lurker Eye", "Lauererauge", "Lurker Eyes", "Laueraugen",
        "eye", "emberye", 20.0, 3.0, 1.1, 9, 1, 2, 0.0, 1.0),
    Mob("ash_weaver", "spider", "Ash Weaver", "Aschenweber",
        "A weaver spinning grey, ash-dusted webs across the Ember Grove canopy.",
        "Ein Weber, der graue, aschbest\u00e4ubte Netze durch das Kronendach des Gluthains spannt.",
        "weaver_silk", "Weaver Silk", "Weberseide", "Weaver Silk", "Weberseide",
        "coil", "silk", 16.0, 2.5, 1.0, 8, 1, 2, 0.0, 1.0),
    Mob("slag_spinner", "spider", "Slag Spinner", "Schlackenspinner",
        "A spinner whose threads set hard as slag the moment they cool.",
        "Ein Spinner, dessen F\u00e4den beim Abk\u00fchlen schlackenhart werden.",
        "spinner_thread", "Spinner Thread", "Spinnerfaden", "Spinner Threads", "Spinnerf\u00e4den",
        "coil", "ashen", 18.0, 3.0, 1.05, 8, 1, 2, 0.0, 1.0),
    Mob("soot_stalker", "spider", "Soot Stalker", "Ru\u00dfpirscher",
        "A lean stalker, near-invisible against the black soot fields.",
        "Ein hagerer Pirscher, vor den schwarzen Ru\u00dffeldern kaum zu erkennen.",
        "stalker_claw", "Stalker Claw", "Pirscherklaue", "Stalker Claws", "Pirscherklauen",
        "fang", "horn", 14.0, 2.5, 0.9, 9, 1, 3, 0.0, 1.0),
    Mob("cinder_broodling", "spider", "Cinder Broodling", "Zinderbr\u00fctling",
        "A tiny broodling that swarms from cracked cinder nests.",
        "Ein winziger Br\u00fctling, der aus geborstenen Zindernestern schw\u00e4rmt.",
        "broodling_fang", "Broodling Fang", "Br\u00fctlingszahn", "Broodling Fangs", "Br\u00fctlingsz\u00e4hne",
        "fang", "fang", 12.0, 2.0, 0.7, 7, 2, 4, 0.0, 1.0),
    # --- CreeperEntity base ------------------------------------------------
    Mob("slag_creeper", "creeper", "Slag Creeper", "Schlackencreeper",
        "A creeper crusted in cooled slag; its blast flings molten spatter.",
        "Ein mit erkalteter Schlacke verkrusteter Creeper; seine Explosion schleudert Schmelzspritzer.",
        "creeper_slag", "Creeper Slag", "Creeper-Schlacke", "Creeper Slag", "Creeper-Schlacke",
        "shard", "slagshard", 24.0, 3.0, 1.05, 8, 1, 2, 0.0, 1.0),
    Mob("ash_bomber", "creeper", "Ash Bomber", "Aschenbomber",
        "A pale bomber that detonates into a blinding cloud of ash.",
        "Ein fahler Bomber, der in einer blendenden Aschewolke detoniert.",
        "bomber_fuse", "Bomber Fuse", "Bomberlunte", "Bomber Fuses", "Bomberlunten",
        "coil", "fuse", 20.0, 3.0, 1.0, 7, 1, 2, 0.0, 1.0),
    Mob("ember_burster", "creeper", "Ember Burster", "Glutberster",
        "A burster wound tight with embers, quicker to pop than most creepers.",
        "Ein mit Glut vollgestopfter Berster, der schneller hochgeht als die meisten Creeper.",
        "burster_powder", "Burster Powder", "Bersterpulver", "Burster Powder", "Bersterpulver",
        "pile", "blazedust", 18.0, 3.0, 0.95, 7, 1, 2, 0.0, 1.0),
    Mob("soot_detonator", "creeper", "Soot Detonator", "Ru\u00dfsprenger",
        "A heavy detonator that stalks silently under cover of soot.",
        "Ein schwerer Sprenger, der lautlos im Schutz des Ru\u00dfes pirscht.",
        "detonator_charge", "Detonator Charge", "Sprengerladung", "Detonator Charges", "Sprengerladungen",
        "charge", "charge", 22.0, 3.0, 1.0, 6, 1, 1, 0.0, 1.0),
    Mob("cinder_cracker", "creeper", "Cinder Cracker", "Zinderknaller",
        "A small cracker that goes off with a sharp, glowing snap.",
        "Ein kleiner Knaller, der mit einem scharfen, gl\u00fchenden Knall losgeht.",
        "cracker_shard", "Cracker Shard", "Knallerscherbe", "Cracker Shards", "Knallerscherben",
        "shard", "glowshard", 16.0, 3.0, 0.85, 8, 1, 3, 0.0, 1.0),
    # --- HuskEntity base ---------------------------------------------------
    Mob("char_husk", "husk", "Char Husk", "Kohlezombie",
        "A charred husk baked black by the Inferno's heat.",
        "Ein verkohlter W\u00fcstenzombie, von der Hitze des Infernos schwarz gebacken.",
        "char_hide", "Char Hide", "Kohlehaut", "Char Hides", "Kohleh\u00e4ute",
        "cloth", "hide", 26.0, 4.0, 1.05, 10, 1, 3, 0.0, 2.0),
    Mob("ash_mummy", "husk", "Ash Mummy", "Aschenmumie",
        "A towering mummy wound in ash-grey wrappings, dry as old bone.",
        "Eine riesige Mumie in aschgrauen Binden, trocken wie alter Knochen.",
        "mummy_wrap", "Mummy Wrap", "Mumienbinde", "Mummy Wraps", "Mumienbinden",
        "cloth", "wrap", 28.0, 4.5, 1.1, 8, 1, 2, 0.0, 2.0),
    Mob("slag_husk", "husk", "Slag Husk", "Schlackenzombie",
        "A husk armoured in a cracked crust of cooled slag.",
        "Ein W\u00fcstenzombie mit einer rissigen Kruste aus erkalteter Schlacke.",
        "husk_crust", "Husk Crust", "Schlackenkruste", "Husk Crusts", "Schlackenkrusten",
        "crust", "sandy", 24.0, 4.0, 1.0, 9, 1, 3, 0.0, 2.0),
    Mob("ember_scorchling", "husk", "Ember Scorchling", "Glutsengling",
        "A shrivelled scorchling that sizzles as it lunges.",
        "Ein verschrumpelter Sengling, der beim Ausfallschritt zischt.",
        "scorchling_char", "Scorchling Char", "Senglingskohle", "Scorchling Char", "Senglingskohle",
        "shard", "charshard", 18.0, 3.5, 0.85, 7, 1, 2, 0.0, 2.0),
    Mob("soot_wanderer", "husk", "Soot Wanderer", "Ru\u00dfwanderer",
        "A shrouded wanderer trudging the soot dunes in endless circles.",
        "Ein verh\u00fcllter Wanderer, der in endlosen Kreisen durch die Ru\u00dfd\u00fcnen stapft.",
        "wanderer_shroud", "Wanderer Shroud", "Wandererschleier", "Wanderer Shrouds", "Wandererschleier",
        "cloth", "shroud", 22.0, 3.5, 1.0, 10, 2, 4, 0.0, 2.0),
]

EGGS = [f"{m.mid}_spawn_egg" for m in MOBS]
DROPS = [m.drop for m in MOBS]
ALL_ITEM_IDS = EGGS + DROPS

# ---------------------------------------------------------------------------
# Recipes: 25 drop conversions (category "misc" like infernomobs_gen). Fields:
#   (name, kind, payload, result_id, count, grid, text_en, text_de)
# kind "shapeless": payload = ingredient id list.
# kind "shaped":    payload = (key dict, pattern list).
# grid = 9 row-major item ids for the handbook preview ("" = empty).
# ---------------------------------------------------------------------------

def m(p):
    return f"{NS}:{p}"


RECIPE_DEFS = [
    ("string_from_shambler_rag", "shapeless", [m("shambler_rag")], "minecraft:string", 2,
     [m("shambler_rag"), "", "", "", "", "", "", "", ""],
     "Unravel a Shambler Rag into two string.",
     "Einen Schlurferlumpen zu zwei F\u00e4den aufribbeln."),
    ("leather_from_ghoul_tatter", "shapeless", [m("ghoul_tatter"), m("ghoul_tatter")], "minecraft:leather", 1,
     [m("ghoul_tatter"), m("ghoul_tatter"), "", "", "", "", "", "", ""],
     "Press two Ghoul Tatters into a piece of leather.",
     "Zwei Ghul-Fetzen zu einem St\u00fcck Leder pressen."),
    ("slime_ball_from_rotter_sludge", "shapeless", [m("rotter_sludge")], "minecraft:slime_ball", 1,
     [m("rotter_sludge"), "", "", "", "", "", "", "", ""],
     "Knead Rotter Sludge into a slime ball.",
     "Moderschlamm zu einem Schleimball kneten."),
    ("iron_nugget_from_thrall_shackle", "shapeless", [m("thrall_shackle")], "minecraft:iron_nugget", 3,
     [m("thrall_shackle"), "", "", "", "", "", "", "", ""],
     "Break a Thrall Shackle into three iron nuggets.",
     "Eine Knechtsfessel in drei Eisenklumpen zerlegen."),
    ("black_dye_from_walker_grime", "shapeless", [m("walker_grime")], "minecraft:black_dye", 2,
     [m("walker_grime"), "", "", "", "", "", "", "", ""],
     "Grind Walker Grime into two black dye.",
     "Wandlerru\u00df zu zwei schwarzen Farbstoffen mahlen."),
    ("arrow_from_archer_arrowhead", "shaped",
     ({"A": m("archer_arrowhead"), "S": "minecraft:stick", "F": "minecraft:feather"}, ["A", "S", "F"]),
     "minecraft:arrow", 4,
     [m("archer_arrowhead"), "", "", "minecraft:stick", "", "", "minecraft:feather", "", ""],
     "An Archer Arrowhead tips a stick and feather into four arrows.",
     "Eine Sch\u00fctzen-Pfeilspitze macht aus Stock und Feder vier Pfeile."),
    ("bow_from_bowman_string", "shapeless",
     [m("bowman_string"), "minecraft:stick", "minecraft:stick"], "minecraft:bow", 1,
     [m("bowman_string"), "minecraft:stick", "minecraft:stick", "", "", "", "", "", ""],
     "String two sticks with a Bowman String to make a bow.",
     "Zwei St\u00f6cke mit einer Bognersehne zu einem Bogen bespannen."),
    ("bone_meal_from_marksman_bone", "shapeless", [m("marksman_bone")], "minecraft:bone_meal", 3,
     [m("marksman_bone"), "", "", "", "", "", "", "", ""],
     "Crush a Marksman Bone into three bone meal.",
     "Einen Scharfsch\u00fctzenknochen zu drei Knochenmehl zersto\u00dfen."),
    ("bone_from_rattler_rib", "shapeless", [m("rattler_rib"), m("rattler_rib")], "minecraft:bone", 1,
     [m("rattler_rib"), m("rattler_rib"), "", "", "", "", "", "", ""],
     "Splint two Rattler Ribs into a whole bone.",
     "Zwei Klapperrippen zu einem ganzen Knochen schienen."),
    ("arrow_from_skirmisher_quiver", "shapeless", [m("skirmisher_quiver")], "minecraft:arrow", 4,
     [m("skirmisher_quiver"), "", "", "", "", "", "", "", ""],
     "Empty a Skirmisher Quiver for four arrows.",
     "Einen Pl\u00e4nklerk\u00f6cher f\u00fcr vier Pfeile leeren."),
    ("spider_eye_from_lurker_eye", "shapeless", [m("lurker_eye")], "minecraft:spider_eye", 2,
     [m("lurker_eye"), "", "", "", "", "", "", "", ""],
     "Split a Lurker Eye into two spider eyes.",
     "Ein Lauererauge in zwei Spinnenaugen teilen."),
    ("cobweb_from_weaver_silk", "shapeless", [m("weaver_silk"), m("weaver_silk")], "minecraft:cobweb", 1,
     [m("weaver_silk"), m("weaver_silk"), "", "", "", "", "", "", ""],
     "Weave two Weaver Silk into a cobweb.",
     "Zwei Weberseiden zu einem Spinnennetz verweben."),
    ("string_from_spinner_thread", "shapeless", [m("spinner_thread")], "minecraft:string", 3,
     [m("spinner_thread"), "", "", "", "", "", "", "", ""],
     "Wind a Spinner Thread off into three string.",
     "Einen Spinnerfaden zu drei F\u00e4den abwickeln."),
    ("flint_from_stalker_claw", "shapeless", [m("stalker_claw")], "minecraft:flint", 2,
     [m("stalker_claw"), "", "", "", "", "", "", "", ""],
     "Knap a Stalker Claw into two flint.",
     "Eine Pirscherklaue zu zwei Feuersteinen schlagen."),
    ("fermented_spider_eye_from_broodling_fang", "shapeless",
     [m("broodling_fang"), m("lurker_eye")], "minecraft:fermented_spider_eye", 1,
     [m("broodling_fang"), m("lurker_eye"), "", "", "", "", "", "", ""],
     "A Broodling Fang's venom ferments a Lurker Eye on the spot.",
     "Das Gift eines Br\u00fctlingszahns fermentiert ein Lauererauge auf der Stelle."),
    ("gunpowder_from_creeper_slag", "shapeless", [m("creeper_slag")], "minecraft:gunpowder", 2,
     [m("creeper_slag"), "", "", "", "", "", "", "", ""],
     "Crumble Creeper Slag into two gunpowder.",
     "Creeper-Schlacke zu zwei Schwarzpulver zerbr\u00f6seln."),
    ("tnt_from_bomber_fuse", "shaped",
     ({"S": "minecraft:sand", "F": m("bomber_fuse")}, ["SSS", "SFS", "SSS"]),
     "minecraft:tnt", 1,
     ["minecraft:sand", "minecraft:sand", "minecraft:sand",
      "minecraft:sand", m("bomber_fuse"), "minecraft:sand",
      "minecraft:sand", "minecraft:sand", "minecraft:sand"],
     "Pack sand around a Bomber Fuse for a block of TNT.",
     "Sand um eine Bomberlunte packen ergibt einen TNT-Block."),
    ("blaze_powder_from_burster_powder", "shapeless", [m("burster_powder")], "minecraft:blaze_powder", 1,
     [m("burster_powder"), "", "", "", "", "", "", "", ""],
     "Refine Burster Powder into blaze powder.",
     "Bersterpulver zu Lohenstaub verfeinern."),
    ("firework_rocket_from_detonator_charge", "shapeless",
     [m("detonator_charge"), "minecraft:paper"], "minecraft:firework_rocket", 3,
     [m("detonator_charge"), "minecraft:paper", "", "", "", "", "", "", ""],
     "Wrap a Detonator Charge in paper for three firework rockets.",
     "Eine Sprengerladung in Papier wickeln ergibt drei Feuerwerksraketen."),
    ("glowstone_dust_from_cracker_shard", "shapeless", [m("cracker_shard")], "minecraft:glowstone_dust", 2,
     [m("cracker_shard"), "", "", "", "", "", "", "", ""],
     "Grind a Cracker Shard into two glowstone dust.",
     "Eine Knallerscherbe zu zwei Leuchtsteinstaub mahlen."),
    ("leather_from_char_hide", "shapeless", [m("char_hide")], "minecraft:leather", 1,
     [m("char_hide"), "", "", "", "", "", "", "", ""],
     "Scrape a Char Hide down to usable leather.",
     "Eine Kohlehaut zu brauchbarem Leder abschaben."),
    ("paper_from_mummy_wrap", "shapeless", [m("mummy_wrap")], "minecraft:paper", 2,
     [m("mummy_wrap"), "", "", "", "", "", "", "", ""],
     "Flatten a Mummy Wrap into two paper.",
     "Eine Mumienbinde zu zwei Papier gl\u00e4tten."),
    ("sand_from_husk_crust", "shapeless", [m("husk_crust")], "minecraft:sand", 2,
     [m("husk_crust"), "", "", "", "", "", "", "", ""],
     "Crush a Husk Crust into two sand.",
     "Eine Schlackenkruste zu zwei Sand zermahlen."),
    ("charcoal_from_scorchling_char", "shapeless", [m("scorchling_char")], "minecraft:charcoal", 2,
     [m("scorchling_char"), "", "", "", "", "", "", "", ""],
     "Break Scorchling Char into two charcoal.",
     "Senglingskohle in zwei Holzkohle brechen."),
    ("gray_wool_from_wanderer_shroud", "shapeless", [m("wanderer_shroud")], "minecraft:gray_wool", 2,
     [m("wanderer_shroud"), "", "", "", "", "", "", "", ""],
     "Cut a Wanderer Shroud into two gray wool.",
     "Einen Wandererschleier zu zwei grauer Wolle zerschneiden."),
]


# ---------------------------------------------------------------------------
# Lang fragments (merged into en_us.json / de_de.json by devtools/merge_lang.py).
# ---------------------------------------------------------------------------

def build_lang():
    en, de = {}, {}
    for mob in MOBS:
        en[f"entity.{NS}.{mob.mid}"] = mob.en
        de[f"entity.{NS}.{mob.mid}"] = mob.de
        en[f"item.{NS}.{mob.mid}_spawn_egg"] = f"{mob.en} Spawn Egg"
        de[f"item.{NS}.{mob.mid}_spawn_egg"] = f"{mob.de}-Spawn-Ei"
        en[f"item.{NS}.{mob.drop}"] = mob.den
        de[f"item.{NS}.{mob.drop}"] = mob.dde
    return en, de


# ---------------------------------------------------------------------------
# Textures. Spawn-egg silhouette copied verbatim from infernomobs_gen.spawn_egg;
# drop painters are new but follow the same 16x16 seeded-Pillow conventions.
# ---------------------------------------------------------------------------

CHARCOAL = (0x3D, 0x2C, 0x2E)
CHARCOAL_DARK = (0x2B, 0x22, 0x26)
OUTLINE_K = (0x1C, 0x12, 0x16)

# Shell palettes by mob-name prefix; speckle color by vanilla base (so every egg
# is visually distinct: shell = theme, spots = what it is).
PREFIX_PAL = {
    "cinder": ((0xC8, 0x4A, 0x1E), (0x8A, 0x30, 0x12), (0xE8, 0x70, 0x38)),
    "ash": ((0x8F, 0x8A, 0x84), (0x5F, 0x5B, 0x57), (0xB9, 0xB4, 0xAE)),
    "slag": ((0x5A, 0x50, 0x46), (0x3B, 0x33, 0x2C), (0x7C, 0x71, 0x64)),
    "ember": ((0xE2, 0x58, 0x22), (0xA3, 0x3D, 0x14), (0xFF, 0xB1, 0x6B)),
    "soot": ((0x2E, 0x2A, 0x28), (0x1C, 0x18, 0x17), (0x4A, 0x44, 0x40)),
    "char": ((0x46, 0x32, 0x26), (0x2E, 0x20, 0x18), (0x64, 0x48, 0x36)),
}
BASE_SPOTS = {
    "zombie": (0x6B, 0x8F, 0x3E),
    "skeleton": (0xE8, 0xE2, 0xD2),
    "spider": (0x9A, 0x20, 0x20),
    "creeper": (0x4A, 0xA8, 0x4A),
    "husk": (0xC8, 0xB2, 0x78),
}

# Drop palettes: base / dark / light (+ accent where a painter uses it).
DROP_PALS = {
    "drab": {"base": (0x6E, 0x6A, 0x52), "dark": (0x4A, 0x47, 0x36), "light": (0x8F, 0x8A, 0x6E)},
    "pale": {"base": (0x9A, 0xA0, 0x8C), "dark": (0x6A, 0x70, 0x5E), "light": (0xBE, 0xC4, 0xB0)},
    "sludge": {"base": (0x5E, 0x74, 0x2E), "dark": (0x3E, 0x4E, 0x1E), "light": (0x86, 0x9E, 0x46)},
    "iron": {"base": (0x8A, 0x8D, 0x92), "dark": (0x55, 0x58, 0x5E), "light": (0xB8, 0xBB, 0xC0)},
    "soot": {"base": (0x30, 0x2C, 0x2A), "dark": (0x1A, 0x16, 0x15), "light": (0x4E, 0x48, 0x44)},
    "flint": {"base": (0x62, 0x60, 0x5E), "dark": (0x3E, 0x3C, 0x3A), "light": (0x8C, 0x89, 0x86)},
    "tan": {"base": (0xC9, 0xB0, 0x82), "dark": (0x97, 0x7F, 0x56), "light": (0xE6, 0xD2, 0xA8)},
    "bone": {"base": (0xE3, 0xDD, 0xCB), "dark": (0xAF, 0xA8, 0x92), "light": (0xF6, 0xF2, 0xE6)},
    "warmbone": {"base": (0xE0, 0xD2, 0xB4), "dark": (0xAC, 0x9C, 0x7C), "light": (0xF4, 0xEA, 0xD4)},
    "leather": {"base": (0x8C, 0x5E, 0x38), "dark": (0x60, 0x3E, 0x22), "light": (0xB0, 0x80, 0x52)},
    "emberye": {"base": (0xE2, 0x58, 0x22), "dark": (0x8A, 0x30, 0x12), "light": (0xFF, 0xB1, 0x6B),
                "accent": (0x14, 0x0B, 0x0E)},
    "silk": {"base": (0xCE, 0xCC, 0xC2), "dark": (0x9A, 0x98, 0x8E), "light": (0xEC, 0xEA, 0xE2)},
    "ashen": {"base": (0xB9, 0xB4, 0xAE), "dark": (0x8F, 0x8A, 0x84), "light": (0xDD, 0xD8, 0xD2)},
    "horn": {"base": (0x4E, 0x40, 0x38), "dark": (0x32, 0x28, 0x22), "light": (0x72, 0x60, 0x52)},
    "fang": {"base": (0xC9, 0xC2, 0xB2), "dark": (0x8E, 0x86, 0x74), "light": (0xF2, 0xEE, 0xE4)},
    "slagshard": {"base": (0x5A, 0x50, 0x46), "dark": (0x3B, 0x33, 0x2C), "light": (0x7C, 0x71, 0x64),
                  "accent": (0xFF, 0x7A, 0x2F)},
    "fuse": {"base": (0x9A, 0x8A, 0x6E), "dark": (0x6E, 0x60, 0x48), "light": (0xC2, 0xB2, 0x92),
             "accent": (0xFF, 0xD8, 0x66)},
    "blazedust": {"base": (0xF6, 0xB2, 0x01), "dark": (0xC6, 0x8A, 0x00), "light": (0xFF, 0xD8, 0x66)},
    "charge": {"base": (0xA8, 0x28, 0x1C), "dark": (0x6E, 0x18, 0x10), "light": (0xD8, 0x50, 0x2C),
               "accent": (0xC9, 0xB0, 0x82)},
    "glowshard": {"base": (0xFF, 0xD8, 0x66), "dark": (0xC6, 0x8A, 0x00), "light": (0xFF, 0xEC, 0x96),
                  "accent": (0xE2, 0x58, 0x22)},
    "hide": {"base": (0x58, 0x40, 0x30), "dark": (0x38, 0x28, 0x1C), "light": (0x7A, 0x5A, 0x42)},
    "wrap": {"base": (0xD8, 0xD2, 0xC0), "dark": (0xA6, 0xA0, 0x8E), "light": (0xF0, 0xEC, 0xDE)},
    "sandy": {"base": (0xC8, 0xB2, 0x78), "dark": (0x96, 0x84, 0x54), "light": (0xE4, 0xD4, 0xA0)},
    "charshard": {"base": (0x3D, 0x2C, 0x2E), "dark": (0x2B, 0x22, 0x26), "light": (0x4A, 0x36, 0x3A),
                  "accent": (0xE2, 0x58, 0x22)},
    "shroud": {"base": (0x76, 0x72, 0x6E), "dark": (0x4E, 0x4A, 0x47), "light": (0x9C, 0x97, 0x92)},
}


def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def spawn_egg(rng: Random, base, base_dark, base_light, spots, outline) -> Image.Image:
    """Classic spawn-egg silhouette; copied verbatim from infernomobs_gen.spawn_egg."""
    img = blank()
    half = {2: 1.6, 3: 2.4, 4: 3.0, 5: 3.5, 6: 4.0, 7: 4.4, 8: 4.7, 9: 4.9,
            10: 5.0, 11: 5.0, 12: 4.7, 13: 4.0, 14: 2.8}
    cells = []
    for y, h in half.items():
        x0 = int(math.ceil(7.5 - h))
        x1 = int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            cells.append((x, y, x == x0 or x == x1 or y in (2, 14)))
    for x, y, edge in cells:
        if edge:
            color = outline
        elif x <= 5 and y <= 9:
            color = base_light
        elif x >= 10 or y >= 12:
            color = base_dark
        else:
            color = base
        px(img, x, y, color)
    interior = [(x, y) for x, y, edge in cells if not edge]
    for x, y in rng.sample(interior, 12):
        px(img, x, y, spots)
    return img


def tex_cloth(rng: Random, pal) -> Image.Image:
    """Folded rag/wrap: bright top-left edge, dark bottom-right, crease lines."""
    img = blank()
    for y in range(3, 13):
        for x in range(3, 13):
            if (x in (3, 12) or y in (3, 12)) and rng.random() < 0.12:
                continue  # torn edge nicks
            if x == 3 or y == 3:
                c = pal["light"]
            elif x == 12 or y == 12:
                c = pal["dark"]
            elif (x + y) in (12, 18):
                c = pal["dark"]
            else:
                c = pal["base"]
            px(img, x, y, c)
    px(img, 5, 5, pal["light"])
    px(img, 10, 10, pal["dark"])
    return img


def tex_blob(rng: Random, pal) -> Image.Image:
    """Round sludge blob with drips off the bottom edge."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 7.0)
            if d <= 4.9:
                if d > 4.0:
                    c = pal["dark"]
                elif (x * 3 + y * 5) % 7 == 0:
                    c = pal["light"]
                else:
                    c = pal["base"]
                px(img, x, y, c)
    for dx in (5, 8, 10):
        px(img, dx, 12, pal["base"])
        px(img, dx, 13, pal["dark"])
    px(img, 8, 14, pal["dark"])
    return img


def tex_ring(rng: Random, pal) -> Image.Image:
    """Shackle: open metal ring with a hasp bar across the top."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 8.5)
            if 3.2 <= d <= 5.2:
                if x <= 6 and y <= 8:
                    c = pal["light"]
                elif d > 4.6:
                    c = pal["dark"]
                else:
                    c = pal["base"]
                px(img, x, y, c)
    for x in (6, 7, 8, 9):
        px(img, x, 2, pal["dark"])
        px(img, x, 3, pal["base"])
    return img


def tex_arrowhead(rng: Random, pal) -> Image.Image:
    """Knapped point: triangle tapering upward with a lit left facet."""
    img = blank()
    rows = {2: (7, 8), 3: (7, 8), 4: (6, 9), 5: (6, 9), 6: (5, 10), 7: (5, 10),
            8: (4, 11), 9: (4, 11), 10: (5, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x == x0:
                c = pal["light"]
            elif x == x1 or y == 10:
                c = pal["dark"]
            else:
                c = pal["base"]
            px(img, x, y, c)
    for y in (11, 12, 13):  # short haft stub
        px(img, 7, y, pal["dark"])
        px(img, 8, y, pal["base"])
    return img


def tex_coil(rng: Random, pal) -> Image.Image:
    """Coiled cord: three stacked loops; optional accent spark at the loose end."""
    img = blank()
    for cy in (5, 8, 11):
        for x in range(4, 12):
            px(img, x, cy, pal["base"])
            px(img, x, cy + 1, pal["dark"])
        px(img, 3, cy, pal["dark"])
        px(img, 12, cy, pal["dark"])
        px(img, 4, cy, pal["light"])
        px(img, 5, cy, pal["light"])
    px(img, 12, 12, pal["base"])
    px(img, 13, 13, pal["dark"])
    if "accent" in pal:
        px(img, 14, 12, pal["accent"])
        px(img, 13, 11, pal["accent"])
    return img


def tex_bone(rng: Random, pal) -> Image.Image:
    """Bone: diagonal shaft with knobbed ends (classic MC bone posture)."""
    img = blank()
    for i in range(7):
        x, y = 4 + i, 11 - i
        px(img, x, y, pal["base"])
        px(img, x + 1, y, pal["light"])
        px(img, x, y + 1, pal["dark"])
    for cx, cy in ((3, 12), (5, 13), (11, 4), (13, 5)):
        for dx in (0, 1):
            for dy in (0, 1):
                px(img, cx + dx, cy + dy, pal["base"])
        px(img, cx, cy, pal["light"])
        px(img, cx + 1, cy + 1, pal["dark"])
    return img


def tex_quiver(rng: Random, pal) -> Image.Image:
    """Slanted leather quiver with two arrow shafts poking out the top."""
    img = blank()
    for y in range(5, 14):
        w = 3 if y < 8 else 4
        x0 = 5 - (y - 5) // 4
        for x in range(x0, x0 + w + 1):
            if x == x0:
                c = pal["light"]
            elif x == x0 + w or y == 13:
                c = pal["dark"]
            else:
                c = pal["base"]
            px(img, x, y, c)
    for x in range(4, 9):  # rim
        px(img, x, 5, pal["dark"])
    for sx in (5, 7):  # arrows
        for i in range(3):
            px(img, sx + i, 4 - i, (0x97, 0x7F, 0x56))
        px(img, sx + 3, 1, (0xE8, 0xE2, 0xD2))
    return img


def tex_eye(rng: Random, pal) -> Image.Image:
    """Round eye: glowing iris ring around a dark vertical slit pupil."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 7.5)
            if d <= 5.0:
                if d > 4.2:
                    c = pal["dark"]
                elif d > 2.4:
                    c = pal["base"] if (x + y) % 3 else pal["light"]
                else:
                    c = pal["light"]
                px(img, x, y, c)
    slit = pal.get("accent", (0x14, 0x0B, 0x0E))
    for y in range(5, 11):
        px(img, 7, y, slit)
        px(img, 8, y, slit)
    px(img, 5, 5, (0xFF, 0xFF, 0xFF), 180)
    return img


def tex_fang(rng: Random, pal) -> Image.Image:
    """Curved fang: broad root top-left tapering to a point bottom-right
    (layout mirrors infernomobs_gen.tex_crawler_fang, recolored per palette)."""
    img = blank()
    for y in (2, 3, 4):
        for x in range(3, 9):
            c = pal["dark"] if y == 2 or x == 3 else pal["base"]
            px(img, x, y, c)
    body = {5: (4, 9), 6: (5, 9), 7: (6, 10), 8: (7, 10), 9: (8, 11), 10: (9, 11),
            11: (10, 12), 12: (11, 12)}
    for y, (x0, x1) in body.items():
        for x in range(x0, x1 + 1):
            if x == x0:
                c = pal["light"]
            elif x == x1:
                c = pal["dark"]
            else:
                c = pal["base"]
            px(img, x, y, c)
    px(img, 12, 13, pal["dark"])
    px(img, 5, 3, pal["light"])
    return img


def tex_shard(rng: Random, pal) -> Image.Image:
    """Jagged chunk with accent cracks (accent optional)."""
    img = blank()
    rows = {3: (7, 9), 4: (6, 10), 5: (5, 11), 6: (4, 11), 7: (4, 12), 8: (3, 12),
            9: (4, 12), 10: (4, 11), 11: (5, 11), 12: (6, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (3, 12):
                c = pal["dark"]
            elif x <= 6 and y <= 8:
                c = pal["light"]
            else:
                c = pal["base"]
            px(img, x, y, c)
    if "accent" in pal:
        for x, y in ((6, 6), (7, 7), (8, 8), (9, 6), (6, 10)):
            if rng.random() < 0.9:
                px(img, x, y, pal["accent"])
    return img


def tex_pile(rng: Random, pal) -> Image.Image:
    """Powder mound: low heap with seeded sparkle grains."""
    img = blank()
    rows = {8: (6, 9), 9: (5, 10), 10: (4, 11), 11: (3, 12), 12: (2, 13)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y == 12:
                c = pal["dark"]
            elif (x * 5 + y * 3) % 6 == 0:
                c = pal["light"]
            else:
                c = pal["base"]
            px(img, x, y, c)
    for _ in range(4):
        px(img, rng.randint(4, 11), rng.randint(9, 11), pal["light"])
    px(img, 7, 7, pal["base"])
    px(img, 8, 7, pal["light"])
    return img


def tex_charge(rng: Random, pal) -> Image.Image:
    """Blast charge: red cylinder, banded, with a short fuse on top."""
    img = blank()
    for y in range(5, 14):
        for x in range(5, 11):
            if x == 5:
                c = pal["light"]
            elif x == 10 or y == 13:
                c = pal["dark"]
            elif y in (7, 10):
                c = pal["dark"]
            else:
                c = pal["base"]
            px(img, x, y, c)
    for x in (6, 7, 8, 9):
        px(img, x, 5, pal["dark"])
    fuse = pal.get("accent", (0xC9, 0xB0, 0x82))
    px(img, 8, 4, fuse)
    px(img, 9, 3, fuse)
    px(img, 10, 2, (0xFF, 0xD8, 0x66))
    return img


def tex_crust(rng: Random, pal) -> Image.Image:
    """Cracked crust slab: flat plate with fissure lines."""
    img = blank()
    for y in range(5, 12):
        for x in range(2, 14):
            if x in (2, 13) or y in (5, 11):
                c = pal["dark"]
            elif y == 6 and x <= 8:
                c = pal["light"]
            else:
                c = pal["base"]
            px(img, x, y, c)
    x = 5
    for y in range(6, 11):  # seeded fissure
        x = max(3, min(12, x + rng.choice([-1, 0, 1])))
        px(img, x, y, pal["dark"])
    px(img, 9, 8, pal["dark"])
    px(img, 4, 9, pal["light"])
    return img


DROP_PAINTERS = {
    "cloth": tex_cloth, "blob": tex_blob, "ring": tex_ring, "arrowhead": tex_arrowhead,
    "coil": tex_coil, "bone": tex_bone, "quiver": tex_quiver, "eye": tex_eye,
    "fang": tex_fang, "shard": tex_shard, "pile": tex_pile, "charge": tex_charge,
    "crust": tex_crust,
}


def emit_textures() -> None:
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for mob in MOBS:
        prefix = mob.mid.split("_", 1)[0]
        base, dark, light = PREFIX_PAL[prefix]
        egg = f"{mob.mid}_spawn_egg"
        img = spawn_egg(genlib.rng_for(f"ashhorde:{egg}"), base, dark, light,
                        BASE_SPOTS[mob.base], OUTLINE_K)
        img.save(tex_dir / f"{egg}.png")
        painter = DROP_PAINTERS[mob.tex]
        img = painter(genlib.rng_for(f"ashhorde:{mob.drop}"), DROP_PALS[mob.pal])
        img.save(tex_dir / f"{mob.drop}.png")


# ---------------------------------------------------------------------------
# Item defs/models, loot tables, recipes
# ---------------------------------------------------------------------------

def emit_item_assets() -> None:
    for item_id in ALL_ITEM_IDS:
        genlib.emit_item_def(ASSETS, item_id)
        genlib.emit_item_model(ASSETS, item_id)


def emit_loot_tables() -> None:
    # dr_pepper_golem/ash_bat schema incl. "random_sequence" (matches
    # infernomobs_gen.emit_loot_tables byte-for-byte in structure).
    for mob in MOBS:
        write_json(DATA / "loot_table" / "entities" / f"{mob.mid}.json", {
            "type": "minecraft:entity",
            "pools": [
                {
                    "bonus_rolls": 0.0,
                    "entries": [
                        {
                            "type": "minecraft:item",
                            "functions": [
                                {
                                    "add": False,
                                    "count": {
                                        "type": "minecraft:uniform",
                                        "max": mob.cmax,
                                        "min": mob.cmin,
                                    },
                                    "function": "minecraft:set_count",
                                }
                            ],
                            "name": m(mob.drop),
                        }
                    ],
                    "rolls": 1.0,
                }
            ],
            "random_sequence": f"{NS}:entities/{mob.mid}",
        })


def emit_recipes() -> int:
    count = 0
    for name, kind, payload, result_id, rcount, _grid, _ten, _tde in RECIPE_DEFS:
        if kind == "shapeless":
            genlib.emit_shapeless(RECIPES, name, list(payload), result_id, rcount,
                                  category="misc")
        else:
            key, pattern = payload
            genlib.emit_shaped(RECIPES, name, key, pattern, result_id, rcount,
                               category="misc")
        count += 1
    return count


# ---------------------------------------------------------------------------
# Java codegen: entity subclasses, feature, client, handbook. All registration
# ids are plain string literals (audit check (f) / check_handbook.py contract).
# ---------------------------------------------------------------------------

def class_name(mid: str) -> str:
    return "".join(part.capitalize() for part in mid.split("_")) + "Entity"


def field_of(item_id: str) -> str:
    return item_id.upper()


def fnum(v: float) -> str:
    """A Java double literal like 26.0 (attributes use doubles)."""
    return f"{v:.1f}" if v == int(v) or round(v, 1) == v else f"{v}"


def entity_class_src(mob: Mob) -> str:
    b = BASES[mob.base]
    cname = class_name(mob.mid)
    imports = {
        "net.minecraft.entity.EntityType",
        "net.minecraft.entity.attribute.DefaultAttributeContainer",
        "net.minecraft.entity.attribute.EntityAttributes",
        f"net.minecraft.entity.mob.{b.cls}",
        f"net.minecraft.entity.mob.{b.attrs_owner}",
        "net.minecraft.world.World",
    }
    doc = [
        f"{mob.en} - {mob.desc_en.rstrip('.')}. Behavior is pure vanilla",
        f"{{@code {b.cls}}}; the stat distinction comes from {{@link #createAttributes()}}",
        f"(MAX_HEALTH {fnum(mob.hp)}, ATTACK_DAMAGE {fnum(mob.dmg)}, SCALE {fnum(mob.sc)}). Drops",
        f"{mob.dpl_en} ({{@code loot_table/entities/{mob.mid}.json}}).",
    ]
    body = "\n".join(f" * {line}" for line in doc)
    src = [f"package {genlib.PKG_ROOT}.feature.ashhorde;", ""]
    src += [f"import {imp};" for imp in sorted(imports)]
    src += ["", "/**", body, " */",
            f"public class {cname} extends {b.cls} {{",
            f"\tpublic {cname}(EntityType<? extends {b.cls}> type, World world) {{",
            "\t\tsuper(type, world);",
            "\t}",
            "",
            "\t/**",
            f"\t * Vanilla {{@code {b.attrs_owner}.{b.attrs_call}()}} with tuned values;",
            "\t * {@code DefaultAttributeContainer.Builder.add(...)} overrides any base value",
            "\t * (same proven pattern as InfernoMobsFeature).",
            "\t */",
            "\tpublic static DefaultAttributeContainer.Builder createAttributes() {",
            f"\t\treturn {b.attrs_owner}.{b.attrs_call}()",
            f"\t\t\t\t.add(EntityAttributes.MAX_HEALTH, {fnum(mob.hp)})",
            f"\t\t\t\t.add(EntityAttributes.ATTACK_DAMAGE, {fnum(mob.dmg)})",
            f"\t\t\t\t.add(EntityAttributes.SCALE, {fnum(mob.sc)});",
            "\t}",
            "}"]
    return "\n".join(src) + "\n"


FEATURE_DOC = [
    "The Ash Horde: 25 hostile mobs native to the Inferno biomes, each a thin subclass of a",
    "vanilla base reusing the vanilla renderer (see {@code AshHordeFeatureClient}) \u2014 5 each on",
    "ZombieEntity, SkeletonEntity, SpiderEntity, CreeperEntity and HuskEntity. EntityType",
    "dimensions/eye heights/attachments/tracking ranges are copied from the vanilla EntityType",
    "registrations (verified via bytecode). Every mob ships a spawn egg, a unique drop item",
    "(loot tables under {@code loot_table/entities/}), natural spawns in the harsh Inferno",
    "biomes (cinder_wastes, ember_grove, slag_sea, soot_dunes, molten_delta), and a",
    "drop-conversion recipe under {@code recipe/ashhorde/}.",
    "",
    "<p>Assets and this class are generated by {@code devtools/gen/ashhorde_gen.py}; handbook",
    "pages are registered by {@link AshHordeHandbook}.",
]


def feature_src() -> str:
    imports = [
        "java.util.function.Predicate",
        "net.fabricmc.fabric.api.biome.v1.BiomeModifications",
        "net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext",
        "net.fabricmc.fabric.api.biome.v1.BiomeSelectors",
        "net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents",
        "net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry",
        "net.minecraft.entity.EntityType",
        "net.minecraft.entity.SpawnGroup",
        "net.minecraft.entity.SpawnLocationTypes",
        "net.minecraft.entity.SpawnReason",
        "net.minecraft.entity.SpawnRestriction",
        "net.minecraft.entity.mob.HostileEntity",
        "net.minecraft.item.Item",
        "net.minecraft.item.SpawnEggItem",
        "net.minecraft.registry.RegistryKey",
        "net.minecraft.registry.RegistryKeys",
        "net.minecraft.util.math.BlockPos",
        "net.minecraft.util.math.random.Random",
        "net.minecraft.world.Heightmap",
        "net.minecraft.world.ServerWorldAccess",
        "net.sonic0810.copperinferno.CopperInferno",
        "net.sonic0810.copperinferno.core.ModCreativeTab",
        "net.sonic0810.copperinferno.core.ModDimensions",
        "net.sonic0810.copperinferno.core.ModEntities",
        "net.sonic0810.copperinferno.core.ModItems",
    ]
    out = [f"package {genlib.PKG_ROOT}.feature.ashhorde;", ""]
    out += [f"import {imp};" for imp in sorted(imports)]
    out += ["", "/**"]
    out += [f" * {line}".rstrip() for line in FEATURE_DOC]
    out += [" */", "public final class AshHordeFeature {", "\tprivate AshHordeFeature() {", "\t}", ""]

    for mob in MOBS:
        out.append(f"\tpublic static EntityType<{class_name(mob.mid)}> {field_of(mob.mid)};")
    out.append("")
    for mob in MOBS:
        out.append(f"\tpublic static Item {field_of(mob.mid)}_SPAWN_EGG;")
    out.append("")
    for mob in MOBS:
        out.append(f"\tpublic static Item {field_of(mob.drop)};")
    out += ["", "\tpublic static void init() {",
            "\t\tregisterEntityTypes();",
            "\t\tregisterAttributes();",
            "\t\tregisterItems();",
            "\t\tregisterSpawning();",
            "\t\tAshHordeHandbook.register();",
            "\t}", ""]

    # --- registerEntityTypes ---
    out += ["\tprivate static void registerEntityTypes() {",
            "\t\t// Builder chains copied 1:1 from the vanilla EntityType registrations (bytecode:",
            "\t\t// zombie/husk = dimensions(0.6f, 1.95f).eyeHeight(1.74f).passengerAttachments(2.0125f",
            "\t\t// resp. 2.075f).vehicleAttachment(-0.7f); skeleton = dimensions(0.6f, 1.99f)",
            "\t\t// .eyeHeight(1.74f).vehicleAttachment(-0.7f); spider = dimensions(1.4f, 0.9f)",
            "\t\t// .eyeHeight(0.65f).passengerAttachments(0.765f); creeper = dimensions(0.6f, 1.7f);",
            "\t\t// all .maxTrackingRange(8).notAllowedInPeaceful())."]
    for i, mob in enumerate(MOBS):
        b = BASES[mob.base]
        if i:
            out.append("")
        out.append(f"\t\t{field_of(mob.mid)} = ModEntities.register(\"{mob.mid}\",")
        out.append(f"\t\t\t\tEntityType.Builder.create({class_name(mob.mid)}::new, SpawnGroup.MONSTER)")
        for j, chain in enumerate(b.builder):
            suffix = ");" if j == len(b.builder) - 1 else ""
            out.append(f"\t\t\t\t\t\t{chain}{suffix}")
    out += ["\t}", ""]

    # --- registerAttributes ---
    out += ["\tprivate static void registerAttributes() {",
            "\t\t// Each createAttributes() delegates to the vanilla base create*Attributes() with",
            "\t\t// tuned MAX_HEALTH/ATTACK_DAMAGE/SCALE (javap-verified; add() overrides base values)."]
    for mob in MOBS:
        out.append(f"\t\tFabricDefaultAttributeRegistry.register({field_of(mob.mid)}, "
                   f"{class_name(mob.mid)}.createAttributes());")
    out += ["\t}", ""]

    # --- registerItems ---
    out += ["\tprivate static void registerItems() {",
            "\t\t// Item.Settings.spawnEgg(type) stores the entity type the vanilla SpawnEggItem",
            "\t\t// reads in 1.21.9 (same proven pattern as InfernoMobsFeature/dr_pepper_golem)."]
    for mob in MOBS:
        out.append(f"\t\t{field_of(mob.mid)}_SPAWN_EGG = ModItems.register(\"{mob.mid}_spawn_egg\", SpawnEggItem::new,")
        out.append(f"\t\t\t\tnew Item.Settings().spawnEgg({field_of(mob.mid)}));")
    out.append("")
    for mob in MOBS:
        out.append(f"\t\t{field_of(mob.drop)} = ModItems.register(\"{mob.drop}\", Item::new, new Item.Settings());")
    out.append("")
    out.append("\t\tItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {")
    out.append("\t\t\t// Spawn eggs together, then the mob drops.")
    for mob in MOBS:
        out.append(f"\t\t\tentries.add({field_of(mob.mid)}_SPAWN_EGG);")
    for mob in MOBS:
        out.append(f"\t\t\tentries.add({field_of(mob.drop)});")
    out += ["\t\t});", "\t}", ""]

    # --- registerSpawning ---
    out += ["\tprivate static void registerSpawning() {",
            "\t\t// SpawnRestriction.register is access-widened by Fabric's transitive access",
            "\t\t// wideners (same as InfernoMobsFeature). Locations/heightmaps/predicates mirror",
            "\t\t// the vanilla SpawnRestriction entries (bytecode): zombie/skeleton/spider/creeper",
            "\t\t// use ON_GROUND + MOTION_BLOCKING_NO_LEAVES + HostileEntity::canSpawnInDark; husk",
            "\t\t// uses HuskEntity.canSpawn, which is typed to EntityType<HuskEntity> and therefore",
            "\t\t// reimplemented 1:1 in canAshHuskSpawn below."]
    for mob in MOBS:
        pred = ("AshHordeFeature::canAshHuskSpawn" if BASES[mob.base].predicate == "husk"
                else "HostileEntity::canSpawnInDark")
        out.append(f"\t\tSpawnRestriction.register({field_of(mob.mid)}, SpawnLocationTypes.ON_GROUND,")
        out.append(f"\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES, {pred});")
    out += ["",
            "\t\t// Natural spawns in the harsh Inferno biomes (SPAWN_BIOMES in the generator;",
            "\t\t// biome JSONs are owned by infernodim/infernodim2 and includeByKey simply",
            "\t\t// matches nothing until they are loaded). addSpawn signature verified via",
            "\t\t// javap: (Predicate<BiomeSelectionContext>, SpawnGroup, EntityType, weight,",
            "\t\t// minGroup, maxGroup).",
            "\t\tPredicate<BiomeSelectionContext> hordeBiomes = BiomeSelectors.includeByKey("]
    for i, biome in enumerate(SPAWN_BIOMES):
        suffix = ");" if i == len(SPAWN_BIOMES) - 1 else ","
        out.append(f"\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"{biome}\")){suffix}")
    for mob in MOBS:
        out.append(f"\t\tBiomeModifications.addSpawn(hordeBiomes, SpawnGroup.MONSTER, "
                   f"{field_of(mob.mid)}, {mob.w}, {mob.gmin}, {mob.gmax});")
    out += ["\t}", ""]

    # --- canAshHuskSpawn ---
    out += [
        "\t/**",
        "\t * Mirrors vanilla {@code HuskEntity.canSpawn} (verified against the 1.21.9 bytecode:",
        "\t * {@code canSpawnInDark && (SpawnReason.isAnySpawner(reason) || world.isSkyVisible(pos))});",
        "\t * the vanilla method is typed to {@code EntityType<HuskEntity>} so it cannot be reused",
        "\t * directly. In the Inferno the sky-visibility gate would starve natural spawns entirely",
        "\t * (the dimension has a ceiling, like the vanilla Nether), so \u2014 exactly like the ash",
        "\t * bat's Inferno branch in {@code InfernoMobsFeature} \u2014 only the dark-spawn rules are",
        "\t * kept there.",
        "\t */",
        "\tprivate static <T extends HostileEntity> boolean canAshHuskSpawn(EntityType<T> type,",
        "\t\t\tServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {",
        "\t\tif (world.toServerWorld().getRegistryKey() == ModDimensions.INFERNO_WORLD) {",
        "\t\t\treturn HostileEntity.canSpawnInDark(type, world, reason, pos, random);",
        "\t\t}",
        "\t\treturn HostileEntity.canSpawnInDark(type, world, reason, pos, random)",
        "\t\t\t\t&& (SpawnReason.isAnySpawner(reason) || world.isSkyVisible(pos));",
        "\t}",
        "}",
    ]
    return "\n".join(out) + "\n"


CLIENT_DOC = [
    "Client-side setup for the Ash Horde: every mob reuses its vanilla base renderer (all five",
    "ctors are Context-only, verified via javap), keeping the vanilla textures. The mobs extend",
    "the matching vanilla entities, so the factories fit the",
    "register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;) bound; the vanilla",
    "register method is access-widened by Fabric's transitive access wideners (same proven",
    "pattern as InfernoMobsFeatureClient).",
]


def client_src() -> str:
    renderers = sorted({BASES[mob.base].renderer for mob in MOBS})
    imports = [f"net.minecraft.client.render.entity.{r}" for r in renderers]
    imports.append("net.minecraft.client.render.entity.EntityRendererFactories")
    imports.append(f"{genlib.PKG_ROOT}.feature.ashhorde.AshHordeFeature")
    out = [f"package {genlib.PKG_ROOT}.feature.ashhorde.client;", ""]
    out += [f"import {imp};" for imp in sorted(imports)]
    out += ["", "/**"]
    out += [f" * {line}".rstrip() for line in CLIENT_DOC]
    out += [" */", "public final class AshHordeFeatureClient {",
            "\tprivate AshHordeFeatureClient() {", "\t}", "",
            "\tpublic static void initClient() {"]
    for mob in MOBS:
        out.append(f"\t\tEntityRendererFactories.register(AshHordeFeature.{field_of(mob.mid)}, "
                   f"{BASES[mob.base].renderer}::new);")
    out += ["\t}", "}"]
    return "\n".join(out) + "\n"


BIOMES_EN = "the Cinder Wastes, Ember Grove and Slag Sea"
BIOMES_DE = "Aschen\u00f6de, Gluthain und Schlackenmeer"


def handbook_entries() -> list:
    entries = []
    for mob in MOBS:
        text_en = (f"{mob.en} - {mob.desc_en} Marches with the Ash Horde through "
                   f"{BIOMES_EN}. Drops {mob.dpl_en}.")
        text_de = (f"{mob.de} - {mob.desc_de} Zieht mit der Aschenhorde durch "
                   f"{BIOMES_DE}. L\u00e4sst {mob.dpl_de} fallen.")
        entries.append(("mobs", mob.mid, m(f"{mob.mid}_spawn_egg"), None, None, None, 0,
                        text_en, text_de))
    # Every recipe is named "<result>_from_<drop>"; the drop is the entry icon.
    drop_by_recipe = {}
    for name, _k, _p, _r, _c, _g, _te, _td in RECIPE_DEFS:
        matches = [mob.drop for mob in MOBS if name.endswith(f"_from_{mob.drop}")]
        assert len(matches) == 1, f"recipe {name!r} does not map to exactly one drop: {matches}"
        drop_by_recipe[name] = matches[0]
    for name, _kind, _payload, result_id, count, grid, text_en, text_de in RECIPE_DEFS:
        icon = m(drop_by_recipe[name])
        entries.append(("items", name, icon, f"ashhorde/{name}", list(grid),
                        result_id, count, text_en, text_de))
    return entries


HANDBOOK_DOC = [
    "Handbook pages for the Ash Horde: one \"mobs\" entry per mob (spawn egg icon, where it",
    "spawns and what it drops) plus one \"items\" grid entry for every drop-conversion recipe",
    "under {@code data/copper_inferno/recipe/ashhorde/}. Entry texts and grids mirror the",
    "recipe JSONs emitted by {@code devtools/gen/ashhorde_gen.py};",
    "{@code devtools/check_handbook.py} parses the inline {@code new HandbookEntry(...)}",
    "literals positionally, so keep them inline.",
]


def emit_java() -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)
    CLIENT_DIR.mkdir(parents=True, exist_ok=True)
    for mob in MOBS:
        (FEATURE_DIR / f"{class_name(mob.mid)}.java").write_text(entity_class_src(mob),
                                                                 encoding="utf-8")
    (FEATURE_DIR / "AshHordeFeature.java").write_text(feature_src(), encoding="utf-8")
    handbook = genlib.java_handbook_class("ashhorde", "AshHordeHandbook", HANDBOOK_DOC,
                                          handbook_entries())
    (FEATURE_DIR / "AshHordeHandbook.java").write_text(handbook, encoding="utf-8")
    (CLIENT_DIR / "AshHordeFeatureClient.java").write_text(client_src(), encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/ashhorde.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks(recipe_count: int, handbook_count: int) -> None:
    lines = ["# ashhorde feature hooks (format: devtools/hooks/README.md)", "",
             "[init]",
             "# No Java init-order constraint: natural spawns use includeByKey on the Inferno",
             "# biome keys (biome JSONs are owned by infernodim; the selector matches nothing",
             "# until those biomes are loaded).",
             "import net.sonic0810.copperinferno.feature.ashhorde.AshHordeFeature;",
             "\t\tAshHordeFeature.init();", "",
             "[client-init]",
             "import net.sonic0810.copperinferno.feature.ashhorde.client.AshHordeFeatureClient;",
             "\t\tAshHordeFeatureClient.initClient();", "",
             "[recipe-dir]", "ashhorde", "",
             "[counts]",
             f"mobs: {len(MOBS)}",
             f"items: {len(ALL_ITEM_IDS)}",
             f"recipes: {recipe_count}",
             f"handbook-entries: {handbook_count}", ""]
    path = ROOT / "devtools" / "hooks" / "ashhorde.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_item_assets()
    emit_textures()
    emit_loot_tables()
    recipe_count = emit_recipes()

    lang_en, lang_de = build_lang()
    genlib.lang_fragments(ASSETS, "ashhorde", lang_en, lang_de)

    entries = handbook_entries()
    emit_java()
    emit_hooks(recipe_count, len(entries))

    # --- acceptance asserts ---
    assert len(MOBS) == 25, f"expected 25 mobs, got {len(MOBS)}"
    assert len({mob.mid for mob in MOBS}) == 25, "duplicate mob ids"
    assert Counter(mob.base for mob in MOBS) == Counter(
        {"zombie": 5, "skeleton": 5, "spider": 5, "creeper": 5, "husk": 5}), \
        "expected 5 mobs per vanilla base"
    assert len(EGGS) == 25 and len(set(EGGS)) == 25, "expected 25 unique spawn eggs"
    assert len(set(ALL_ITEM_IDS)) == 50, "duplicate item ids emitted"
    expected_keys = ({f"entity.{NS}.{mob.mid}" for mob in MOBS}
                     | {f"item.{NS}.{i}" for i in ALL_ITEM_IDS})
    assert set(lang_en) == set(lang_de) == expected_keys, "EN/DE/id lang key sets differ"
    assert recipe_count == 25, f"expected 25 recipes, got {recipe_count}"
    assert len({n for n, *_ in RECIPE_DEFS}) == 25, "duplicate recipe names"
    assert len(entries) == 50, f"expected 50 handbook entries, got {len(entries)}"
    for _cat, _eid, _icon, rid, grid, _res, _cnt, _te, _td in entries:
        assert grid is None or len(grid) == 9, f"bad grid for {_eid}"
    print(f"ashhorde_gen: assets + Java generated for {len(MOBS)} mobs / "
          f"{len(ALL_ITEM_IDS)} items ({recipe_count} recipes, {len(entries)} handbook entries).")


if __name__ == "__main__":
    main()
