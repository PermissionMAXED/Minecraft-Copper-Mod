#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "moltenfauna" feature (25 passive mobs).

The Inferno's grazing herds: 25 passive/neutral mobs (all SpawnGroup.CREATURE), thin
subclasses of five vanilla farm/lava animals reusing the vanilla renderers -
5 x StriderEntity, 5 x CowEntity, 5 x ChickenEntity, 5 x PigEntity, 5 x SheepEntity.

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via genlib.rng_for). Emits by DEFAULT (no flags):
  - assets/copper_inferno/items/<id>.json           (1.21.9 item model definition)
  - assets/copper_inferno/models/item/<id>.json     (item/generated + layer0)
  - assets/copper_inferno/textures/item/<id>.png    (deterministic 16x16 Pillow art)
  - data/copper_inferno/loot_table/entities/<mob>.json (infernomobs schema incl.
    "random_sequence")
  - data/copper_inferno/recipe/moltenfauna/*.json   (25 shapeless drop recipes; every
    input set contains one of this feature's own drop ids -> collision-free)
  - lang fragments: assets/.../lang/fragments/moltenfauna.json (EN)
    and assets/.../lang/fragments_de/moltenfauna.json (real German)
  - src/main/java/.../feature/moltenfauna/MoltenFaunaFeature.java (literal ids only)
    + 25 entity subclasses + MoltenFaunaHandbook.java (genlib.java_handbook_class)
  - src/client/java/.../feature/moltenfauna/client/MoltenFaunaFeatureClient.java
  - devtools/hooks/moltenfauna.txt (integration hook file)

Entity API verified with javap against the loom 1.21.9 yarn-mapped jars:
  - ctors: all five bases take (EntityType<? extends Base>, World).
  - attributes: ChickenEntity.createChickenAttributes / AbstractCowEntity
    .createCowAttributes / PigEntity.createPigAttributes / SheepEntity
    .createSheepAttributes / StriderEntity.createStriderAttributes.
  - EntityType dims copied from the vanilla EntityType registrations (bytecode):
    chicken = dimensions(0.4f, 0.7f).eyeHeight(0.644f)
      .passengerAttachments(new Vec3d(0.0, 0.7, -0.1)).maxTrackingRange(10);
    cow = dimensions(0.9f, 1.4f).eyeHeight(1.3f).passengerAttachments(1.36875f)
      .maxTrackingRange(10);
    pig = dimensions(0.9f, 0.9f).passengerAttachments(0.86875f).maxTrackingRange(10);
    sheep = dimensions(0.9f, 1.3f).eyeHeight(1.235f).passengerAttachments(1.2375f)
      .maxTrackingRange(10);
    strider = makeFireImmune().dimensions(0.9f, 1.7f).maxTrackingRange(10).
    All five vanilla bases are SpawnGroup.CREATURE.
  - spawn restrictions (SpawnRestriction bytecode): chicken/cow/pig/sheep use ON_GROUND
    + MOTION_BLOCKING_NO_LEAVES + AnimalEntity::isValidNaturalSpawn (BootstrapMethods
    entry resolves to that method); strider uses IN_LAVA + MOTION_BLOCKING_NO_LEAVES +
    StriderEntity::canSpawn. Both vanilla predicates are typed to the vanilla
    EntityTypes / take WorldAccess, so they are reimplemented 1:1 in MoltenFaunaFeature.
  - renderers (client jar): Cow/Chicken/Pig/Sheep/StriderEntityRenderer all have
    Context-only ctors and are typed to the vanilla entity classes our mobs extend.
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, rng_for, write_json
from PIL import Image

RECIPES = DATA / "recipe" / "moltenfauna"
FEATURE_DIR = (ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno"
               / "feature" / "moltenfauna")
CLIENT_DIR = (ROOT / "src" / "client" / "java" / "net" / "sonic0810" / "copperinferno"
              / "feature" / "moltenfauna" / "client")

# Inferno biomes the herds graze in: the original trio plus the vegetated/lava-delta
# infernodim2 biomes (verdigris_jungle, molten_delta). The canonical 7-biome list of the
# Inferno dimension lives in devtools/gen/infernodim2_gen.py (BIOMES/CLIMATE_POINTS);
# the barren soot_dunes and the quiet crystal_hollows stay herd-free.
SPAWN_BIOMES = ["cinder_wastes", "ember_grove", "slag_sea",
                "verdigris_jungle", "molten_delta"]

# ---------------------------------------------------------------------------
# Palette (infernomobs house style)
# ---------------------------------------------------------------------------
EMBER = (0xE2, 0x58, 0x22)
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)
EMBER_HOT = (0xFF, 0xB1, 0x6B)
EMBER_YELLOW = (0xFF, 0xD8, 0x66)

CHARCOAL_LIGHT = (0x4A, 0x36, 0x3A)
CHARCOAL = (0x3D, 0x2C, 0x2E)
CHARCOAL_DARK = (0x2B, 0x22, 0x26)

ASH_LIGHT = (0xB9, 0xB4, 0xAE)
ASH = (0x8F, 0x8A, 0x84)
ASH_DARK = (0x5F, 0x5B, 0x57)

MAGMA_RED = (0xC0, 0x3A, 0x1E)
MAGMA_DARK = (0x83, 0x26, 0x14)
MAGMA_LIGHT = (0xE8, 0x6A, 0x38)

BASALT_LIGHT = (0x86, 0x86, 0x8C)
BASALT = (0x63, 0x63, 0x69)
BASALT_DARK = (0x46, 0x46, 0x4C)

SLAG_LIGHT = (0x9A, 0x86, 0x6E)
SLAG = (0x74, 0x62, 0x4E)
SLAG_DARK = (0x52, 0x44, 0x36)

OBSIDIAN_LIGHT = (0x5C, 0x48, 0x7A)
OBSIDIAN = (0x3A, 0x2C, 0x50)
OBSIDIAN_DARK = (0x24, 0x1B, 0x33)

SMOLDER_LIGHT = (0xD8, 0xC6, 0xB4)
SMOLDER = (0xB2, 0x9E, 0x8C)
SMOLDER_DARK = (0x7E, 0x6E, 0x60)

BEEF_RAW = (0xC8, 0x4A, 0x42)
BEEF_DARK = (0x8E, 0x2E, 0x2A)
PORK_RAW = (0xE8, 0x9E, 0x96)
PORK_DARK = (0xC0, 0x6E, 0x68)
MUTTON_RAW = (0xB4, 0x3E, 0x4E)
MUTTON_DARK = (0x7E, 0x28, 0x36)
POULTRY = (0xE8, 0xC8, 0xA8)
POULTRY_DARK = (0xC0, 0x98, 0x74)
BONE = (0xEE, 0xE6, 0xD2)
FAT = (0xF2, 0xE2, 0xC8)

T = (0, 0, 0, 0)


# ---------------------------------------------------------------------------
# Mob table. Row: (mob_id, ClassName, base, EN, DE, drop_id, drop EN, drop DE,
#                  loot min, loot max, attr tweaks, spawn (weight, min, max),
#                  flavor EN, flavor DE)
# base in {"strider", "cow", "chicken", "pig", "sheep"}. Attr tweaks are
# (EntityAttributes field, java double literal) pairs applied on top of the
# vanilla create*Attributes() builder (same pattern as InfernoMobsFeature).
# ---------------------------------------------------------------------------
MOBS = [
    # ---- Strider variants (lava waders) ----
    ("magma_strider", "MagmaStriderEntity", "strider", "Magma Strider", "Magmaschreiter",
     "magma_carapace", "Magma Carapace", "Magmapanzer", 1.0, 2.0,
     [("MAX_HEALTH", "24.0")], (8, 1, 2),
     "a magma-crusted strider wading the lava of the Slag Sea and its neighbors. Can be saddled and ridden.",
     "ein magmaverkrusteter Schreiter, der durch die Lava des Schlackenmeers watet. Kann gesattelt und geritten werden."),
    ("soot_strider", "SootStriderEntity", "strider", "Soot Strider", "Ru\u00dfschreiter",
     "soot_bristles", "Soot Bristles", "Ru\u00dfborsten", 1.0, 2.0,
     [("MOVEMENT_SPEED", "0.2")], (8, 1, 2),
     "a soot-black strider, quicker on its feet than its kin, skimming the Inferno's lava channels.",
     "ein ru\u00dfschwarzer Schreiter, flinker als seine Verwandten, der \u00fcber die Lavarinnen des Infernos gleitet."),
    ("basalt_strider", "BasaltStriderEntity", "strider", "Basalt Strider", "Basaltschreiter",
     "basalt_scale", "Basalt Scale", "Basaltschuppe", 1.0, 2.0,
     [("MAX_HEALTH", "28.0"), ("SCALE", "1.1")], (8, 1, 2),
     "a heavyset strider armored in basalt plates, plodding through the deepest lava pools.",
     "ein massiger, mit Basaltplatten gepanzerter Schreiter, der durch die tiefsten Lavabecken stapft."),
    ("slag_strider", "SlagStriderEntity", "strider", "Slag Strider", "Schlackenschreiter",
     "slag_husk", "Slag Husk", "Schlackenh\u00fclle", 1.0, 2.0,
     [("MAX_HEALTH", "22.0")], (8, 1, 2),
     "a strider caked in cooling slag, at home wherever the Slag Sea churns.",
     "ein mit erkaltender Schlacke bedeckter Schreiter, \u00fcberall dort zu Hause, wo das Schlackenmeer brodelt."),
    ("obsidian_strider", "ObsidianStriderEntity", "strider", "Obsidian Strider", "Obsidianschreiter",
     "obsidian_plating", "Obsidian Plating", "Obsidianplattierung", 1.0, 2.0,
     [("MAX_HEALTH", "30.0"), ("SCALE", "1.15")], (8, 1, 2),
     "a rare strider sheathed in glassy obsidian, the toughest of the lava waders.",
     "ein seltener, in glasigen Obsidian geh\u00fcllter Schreiter, der z\u00e4heste aller Lavawater."),
    # ---- Cow variants (heavy grazers) ----
    ("ember_grazer", "EmberGrazerEntity", "cow", "Ember Grazer", "Glutgraser",
     "grazer_brisket", "Grazer Brisket", "Graser-Brustkern", 1.0, 3.0,
     [("MAX_HEALTH", "12.0")], (10, 2, 4),
     "a placid bovine that crops smoldering tufts across the Cinder Wastes, Ember Grove and Slag Sea.",
     "ein friedliches Rind, das schwelende B\u00fcschel in der Aschen\u00f6de, im Gluthain und am Schlackenmeer abweidet."),
    ("ash_yak", "AshYakEntity", "cow", "Ash Yak", "Aschenyak",
     "yak_haunch", "Yak Haunch", "Yak-Keule", 1.0, 3.0,
     [("MAX_HEALTH", "16.0"), ("SCALE", "1.1")], (10, 2, 4),
     "a shaggy, ash-dusted yak roaming the Inferno's grey plains in small herds.",
     "ein zotteliger, aschebest\u00e4ubter Yak, der in kleinen Herden \u00fcber die grauen Ebenen des Infernos zieht."),
    ("magma_ox", "MagmaOxEntity", "cow", "Magma Ox", "Magmaochse",
     "ox_loin", "Ox Loin", "Ochsenlende", 1.0, 3.0,
     [("MAX_HEALTH", "20.0"), ("SCALE", "1.2")], (10, 2, 4),
     "a hulking ox with magma-veined hide, unbothered by the heat shimmering off the ground.",
     "ein w\u00fcchtiger Ochse mit magmage\u00e4derter Haut, den die flirrende Hitze des Bodens nicht st\u00f6rt."),
    ("slag_buffalo", "SlagBuffaloEntity", "cow", "Slag Buffalo", "Schlackenb\u00fcffel",
     "buffalo_hump", "Buffalo Hump", "B\u00fcffelh\u00f6cker", 1.0, 3.0,
     [("MAX_HEALTH", "18.0"), ("SCALE", "1.15")], (10, 2, 4),
     "a broad-shouldered buffalo wallowing in warm slag pits along the Slag Sea's shores.",
     "ein breitschultriger B\u00fcffel, der sich in warmen Schlackengruben an den Ufern des Schlackenmeers suhlt."),
    ("cinder_aurochs", "CinderAurochsEntity", "cow", "Cinder Aurochs", "Zinder-Auerochse",
     "aurochs_shank", "Aurochs Shank", "Auerochsenhachse", 1.0, 3.0,
     [("MAX_HEALTH", "22.0"), ("SCALE", "1.25")], (10, 2, 4),
     "a primeval wild ox with cinder-crusted horns, the largest grazer of the Inferno.",
     "ein urt\u00fcmlicher Wildochse mit zinderverkrusteten H\u00f6rnern, der gr\u00f6\u00dfte Graser des Infernos."),
    # ---- Chicken variants (ground fowl) ----
    ("soot_hen", "SootHenEntity", "chicken", "Soot Hen", "Ru\u00dfhenne",
     "hen_drumstick", "Hen Drumstick", "Hennenkeule", 0.0, 2.0,
     [("MAX_HEALTH", "5.0")], (12, 2, 4),
     "a soot-grey hen scratching through warm ash for embers and grubs.",
     "eine ru\u00dfgraue Henne, die in warmer Asche nach Glut und Larven scharrt."),
    ("cinder_rooster", "CinderRoosterEntity", "chicken", "Cinder Rooster", "Zinderhahn",
     "rooster_wing", "Rooster Wing", "Hahnenfl\u00fcgel", 0.0, 2.0,
     [("MAX_HEALTH", "6.0"), ("MOVEMENT_SPEED", "0.3")], (12, 2, 4),
     "a strutting rooster with cinder-red plumage, quick to dart across the wastes.",
     "ein stolzierender Hahn mit zinderrotem Gefieder, der flink \u00fcber die \u00d6de huscht."),
    ("ember_pullet", "EmberPulletEntity", "chicken", "Ember Pullet", "Glutjunghenne",
     "pullet_breast", "Pullet Breast", "Junghennenbrust", 0.0, 2.0,
     [("SCALE", "0.9")], (12, 2, 4),
     "a small young hen glowing faintly like a banked ember.",
     "eine kleine Junghenne, die schwach wie gebettete Glut gl\u00fcht."),
    ("ash_fowl", "AshFowlEntity", "chicken", "Ash Fowl", "Aschenhuhn",
     "fowl_giblets", "Fowl Giblets", "Gefl\u00fcgelklein", 0.0, 2.0,
     [("MAX_HEALTH", "5.0")], (12, 2, 4),
     "a pale, dusty fowl blending into the ash drifts of the Cinder Wastes.",
     "ein blasses, staubiges Huhn, das in den Aschenwehen der Aschen\u00f6de kaum auff\u00e4llt."),
    ("magma_bantam", "MagmaBantamEntity", "chicken", "Magma Bantam", "Magma-Zwerghuhn",
     "bantam_thigh", "Bantam Thigh", "Zwerghuhnschenkel", 0.0, 2.0,
     [("SCALE", "0.8"), ("MOVEMENT_SPEED", "0.3")], (12, 2, 4),
     "a tiny, feisty bantam with magma-orange speckles, always underfoot.",
     "ein winziges, keckes Zwerghuhn mit magmaorangen Sprenkeln, das einem st\u00e4ndig zwischen die F\u00fc\u00dfe l\u00e4uft."),
    # ---- Pig variants (rooters) ----
    ("magma_hog", "MagmaHogEntity", "pig", "Magma Hog", "Magmakeiler",
     "hog_belly", "Hog Belly", "Keilerbauch", 1.0, 3.0,
     [("MAX_HEALTH", "14.0"), ("SCALE", "1.1")], (10, 2, 4),
     "a stout hog rooting through warm cinder beds for buried embers.",
     "ein stämmiger Keiler, der in warmen Zinderbetten nach vergrabener Glut w\u00fchlt."),
    ("soot_swine", "SootSwineEntity", "pig", "Soot Swine", "Ru\u00dfschwein",
     "swine_hock", "Swine Hock", "Schweinshaxe", 1.0, 3.0,
     [("MAX_HEALTH", "12.0")], (10, 2, 4),
     "a soot-caked swine happily wallowing in cool ash hollows.",
     "ein ru\u00dfverkrustetes Schwein, das sich vergn\u00fcgt in k\u00fchlen Aschenmulden suhlt."),
    ("cinder_boar", "CinderBoarEntity", "pig", "Cinder Boar", "Zindereber",
     "boar_shoulder", "Boar Shoulder", "Eberschulter", 1.0, 3.0,
     [("MAX_HEALTH", "16.0"), ("MOVEMENT_SPEED", "0.28")], (10, 2, 4),
     "a bristly boar trotting briskly between the Ember Grove's charred trunks.",
     "ein borstiger Eber, der z\u00fcgig zwischen den verkohlten St\u00e4mmen des Gluthains trabt."),
    ("slag_sow", "SlagSowEntity", "pig", "Slag Sow", "Schlackensau",
     "sow_jowl", "Sow Jowl", "Saub\u00e4ckchen", 1.0, 3.0,
     [("MAX_HEALTH", "14.0"), ("SCALE", "1.05")], (10, 2, 4),
     "a round sow dozing beside slag pools, unhurried by anything.",
     "eine rundliche Sau, die neben Schlackent\u00fcmpeln d\u00f6st und sich von nichts hetzen l\u00e4sst."),
    ("ember_porker", "EmberPorkerEntity", "pig", "Ember Porker", "Glutmastschwein",
     "porker_ham", "Porker Ham", "Mastschinken", 1.0, 3.0,
     [("MAX_HEALTH", "12.0"), ("SCALE", "1.15")], (10, 2, 4),
     "a plump, well-fed porker glowing warmly around the snout.",
     "ein rundes, wohlgen\u00e4hrtes Mastschwein, dessen R\u00fcssel warm gl\u00fcht."),
    # ---- Sheep variants (wool flock) ----
    ("ash_ewe", "AshEweEntity", "sheep", "Ash Ewe", "Aschenmutterschaf",
     "ewe_shank", "Ewe Shank", "Schafshaxe", 1.0, 2.0,
     [("MAX_HEALTH", "10.0")], (10, 2, 4),
     "a gentle ewe with ash-grey fleece, grazing in small flocks. Can be sheared.",
     "ein sanftes Mutterschaf mit aschgrauem Vlies, das in kleinen Herden weidet. Kann geschoren werden."),
    ("cinder_ram", "CinderRamEntity", "sheep", "Cinder Ram", "Zinderwidder",
     "ram_rack", "Ram Rack", "Widderkarree", 1.0, 2.0,
     [("MAX_HEALTH", "12.0"), ("MOVEMENT_SPEED", "0.25")], (10, 2, 4),
     "a headstrong ram patrolling the flock's edge with cinder-dark curls.",
     "ein dickk\u00f6pfiger Widder mit zinderdunklen Locken, der den Rand der Herde bewacht."),
    ("ember_lamb", "EmberLambEntity", "sheep", "Ember Lamb", "Glutlamm",
     "lamb_cutlet", "Lamb Cutlet", "Lammkotelett", 1.0, 2.0,
     [("SCALE", "0.85"), ("MOVEMENT_SPEED", "0.25")], (10, 2, 4),
     "a small, skittish lamb whose fleece sparks faintly when it bolts.",
     "ein kleines, scheues Lamm, dessen Vlies leicht funkelt, wenn es davonspringt."),
    ("soot_wether", "SootWetherEntity", "sheep", "Soot Wether", "Ru\u00dfhammel",
     "wether_ribs", "Wether Ribs", "Hammelrippchen", 1.0, 2.0,
     [("MAX_HEALTH", "12.0"), ("SCALE", "1.1")], (10, 2, 4),
     "a heavy, even-tempered wether carrying a thick soot-dark fleece.",
     "ein schwerer, gutm\u00fctiger Hammel mit dichtem, ru\u00dfdunklem Vlies."),
    ("smolder_sheep", "SmolderSheepEntity", "sheep", "Smolder Sheep", "Schwelschaf",
     "smolder_fleece", "Smolder Fleece", "Schwelvlies", 1.0, 2.0,
     [("MAX_HEALTH", "10.0")], (10, 2, 4),
     "a sheep whose fleece smolders without ever burning away. Can be sheared.",
     "ein Schaf, dessen Vlies schwelt, ohne je zu verbrennen. Kann geschoren werden."),
]

MOB_IDS = [m[0] for m in MOBS]
DROP_IDS = [m[5] for m in MOBS]
EGG_IDS = [f"{m}_spawn_egg" for m in MOB_IDS]
ITEM_IDS = EGG_IDS + DROP_IDS

# ---------------------------------------------------------------------------
# Recipes: (recipe name, ingredient ids, result id, result count, result EN,
#           result DE). One shapeless recipe per mob; every input set contains
#           the mob's own drop id, so input sets can never collide with vanilla
#           or other features' recipes.
# ---------------------------------------------------------------------------
VANILLA_NAMES = {
    "minecraft:beef": ("Raw Beef", "Rohes Rindfleisch"),
    "minecraft:chicken": ("Raw Chicken", "Rohes H\u00fchnchen"),
    "minecraft:porkchop": ("Raw Porkchop", "Rohes Schweinefleisch"),
    "minecraft:mutton": ("Raw Mutton", "Rohes Hammelfleisch"),
    "minecraft:magma_cream": ("Magma Cream", "Magmacreme"),
    "minecraft:string": ("String", "Faden"),
    "minecraft:basalt": ("Basalt", "Basalt"),
    "minecraft:leather": ("Leather", "Leder"),
    "minecraft:black_dye": ("Black Dye", "Schwarzer Farbstoff"),
    "minecraft:white_wool": ("White Wool", "Wei\u00dfe Wolle"),
}

RECIPE_TABLE = [
    ("magma_cream_from_magma_carapace", ["magma_carapace"], "minecraft:magma_cream", 2),
    ("string_from_soot_bristles", ["soot_bristles"], "minecraft:string", 3),
    ("basalt_from_basalt_scale", ["basalt_scale"] * 4, "minecraft:basalt", 1),
    ("leather_from_slag_husk", ["slag_husk"], "minecraft:leather", 1),
    ("black_dye_from_obsidian_plating", ["obsidian_plating"], "minecraft:black_dye", 2),
    ("beef_from_grazer_brisket", ["grazer_brisket"], "minecraft:beef", 2),
    ("beef_from_yak_haunch", ["yak_haunch"], "minecraft:beef", 2),
    ("beef_from_ox_loin", ["ox_loin"], "minecraft:beef", 2),
    ("beef_from_buffalo_hump", ["buffalo_hump"], "minecraft:beef", 2),
    ("beef_from_aurochs_shank", ["aurochs_shank"], "minecraft:beef", 2),
    ("chicken_from_hen_drumstick", ["hen_drumstick"], "minecraft:chicken", 1),
    ("chicken_from_rooster_wing", ["rooster_wing"], "minecraft:chicken", 1),
    ("chicken_from_pullet_breast", ["pullet_breast"], "minecraft:chicken", 1),
    ("chicken_from_fowl_giblets", ["fowl_giblets"], "minecraft:chicken", 1),
    ("chicken_from_bantam_thigh", ["bantam_thigh"], "minecraft:chicken", 1),
    ("porkchop_from_hog_belly", ["hog_belly"], "minecraft:porkchop", 2),
    ("porkchop_from_swine_hock", ["swine_hock"], "minecraft:porkchop", 2),
    ("porkchop_from_boar_shoulder", ["boar_shoulder"], "minecraft:porkchop", 2),
    ("porkchop_from_sow_jowl", ["sow_jowl"], "minecraft:porkchop", 2),
    ("porkchop_from_porker_ham", ["porker_ham"], "minecraft:porkchop", 2),
    ("mutton_from_ewe_shank", ["ewe_shank"], "minecraft:mutton", 2),
    ("mutton_from_ram_rack", ["ram_rack"], "minecraft:mutton", 2),
    ("mutton_from_lamb_cutlet", ["lamb_cutlet"], "minecraft:mutton", 2),
    ("mutton_from_wether_ribs", ["wether_ribs"], "minecraft:mutton", 2),
    ("white_wool_from_smolder_fleece", ["smolder_fleece"], "minecraft:white_wool", 1),
]

# ---------------------------------------------------------------------------
# Base-kind config (dims verified from EntityType bytecode; see module docstring).
# builder_lines are the EntityType.Builder chain lines after the create(...) call.
# ---------------------------------------------------------------------------
BASES = {
    "strider": {
        "java_base": "StriderEntity",
        "attrs": "StriderEntity.createStriderAttributes()",
        "renderer": "StriderEntityRenderer",
        "builder_lines": [".makeFireImmune()", ".dimensions(0.9f, 1.7f)", ".maxTrackingRange(10)"],
        "comment": "strider = makeFireImmune().dimensions(0.9f, 1.7f).maxTrackingRange(10)",
    },
    "cow": {
        "java_base": "CowEntity",
        "attrs": "AbstractCowEntity.createCowAttributes()",
        "renderer": "CowEntityRenderer",
        "builder_lines": [".dimensions(0.9f, 1.4f)", ".eyeHeight(1.3f)",
                          ".passengerAttachments(1.36875f)", ".maxTrackingRange(10)"],
        "comment": ("cow = dimensions(0.9f, 1.4f).eyeHeight(1.3f)"
                    ".passengerAttachments(1.36875f).maxTrackingRange(10)"),
    },
    "chicken": {
        "java_base": "ChickenEntity",
        "attrs": "ChickenEntity.createChickenAttributes()",
        "renderer": "ChickenEntityRenderer",
        "builder_lines": [".dimensions(0.4f, 0.7f)", ".eyeHeight(0.644f)",
                          ".passengerAttachments(new Vec3d(0.0, 0.7, -0.1))",
                          ".maxTrackingRange(10)"],
        "comment": ("chicken = dimensions(0.4f, 0.7f).eyeHeight(0.644f)"
                    ".passengerAttachments(new Vec3d(0.0, 0.7, -0.1)).maxTrackingRange(10)"),
    },
    "pig": {
        "java_base": "PigEntity",
        "attrs": "PigEntity.createPigAttributes()",
        "renderer": "PigEntityRenderer",
        "builder_lines": [".dimensions(0.9f, 0.9f)", ".passengerAttachments(0.86875f)",
                          ".maxTrackingRange(10)"],
        "comment": ("pig = dimensions(0.9f, 0.9f).passengerAttachments(0.86875f)"
                    ".maxTrackingRange(10)"),
    },
    "sheep": {
        "java_base": "SheepEntity",
        "attrs": "SheepEntity.createSheepAttributes()",
        "renderer": "SheepEntityRenderer",
        "builder_lines": [".dimensions(0.9f, 1.3f)", ".eyeHeight(1.235f)",
                          ".passengerAttachments(1.2375f)", ".maxTrackingRange(10)"],
        "comment": ("sheep = dimensions(0.9f, 1.3f).eyeHeight(1.235f)"
                    ".passengerAttachments(1.2375f).maxTrackingRange(10)"),
    },
}

# ---------------------------------------------------------------------------
# Textures
# ---------------------------------------------------------------------------

def blank():
    return Image.new("RGBA", (16, 16), T)


def px(img, x, y, color, alpha=255):
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def rect(img, x0, y0, x1, y1, c):
    for y in range(y0, y1 + 1):
        for x in range(x0, x1 + 1):
            px(img, x, y, c)


def spawn_egg(rng, base, base_dark, base_light, spots, outline):
    """Classic spawn-egg silhouette with seeded speckles (infernomobs_gen shape)."""
    import math
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


def t_meat(rng, base, dark):
    """Oval raw-cut slab with grain lines and a fat cap."""
    img = blank()
    rect(img, 4, 4, 11, 4, base)
    rect(img, 3, 5, 12, 10, base)
    rect(img, 4, 11, 11, 11, dark)
    for y in range(5, 11):
        px(img, 12, y, dark)
    for x in (5, 8, 11):
        for y in range(5, 11):
            px(img, x, y, dark)
    rect(img, 4, 4, 10, 4, FAT)
    px(img, 4, 5, FAT)
    for _ in range(3):
        px(img, rng.randint(4, 11), rng.randint(5, 10), dark)
    return img


def t_drumstick(rng, base, dark):
    """Poultry leg: meaty teardrop bottom-left, bone knob top-right."""
    img = blank()
    rows = {6: (4, 8), 7: (3, 9), 8: (3, 10), 9: (3, 10), 10: (4, 10), 11: (5, 9)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            color = dark if x in (x0, x1) or y in (6, 11) else base
            px(img, x, y, color)
    for i in range(4):
        px(img, 9 + i, 6 - i, BONE)
        px(img, 10 + i, 6 - i, dark if i == 0 else BONE)
    px(img, 12, 2, BONE)
    px(img, 13, 3, BONE)
    px(img, 5, 8, FAT)
    for _ in range(2):
        px(img, rng.randint(4, 9), rng.randint(7, 10), dark)
    return img


def t_shell(rng, base, dark, light):
    """Domed carapace segment with growth bands (infernomobs strider_shell shape)."""
    import math
    img = blank()
    half = {4: 2.4, 5: 3.4, 6: 4.1, 7: 4.6, 8: 5.0, 9: 5.2, 10: 5.4, 11: 5.4}
    for y, h in half.items():
        x0 = int(math.ceil(7.5 - h))
        x1 = int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y == 4:
                color = dark
            elif y in (6, 9):
                color = dark
            elif x <= 5 and y <= 8:
                color = light
            else:
                color = base
            px(img, x, y, color)
    for x in range(2, 14):
        px(img, x, 12, ASH)
        px(img, x, 13, ASH_DARK)
    px(img, 5, 5, light)
    for _ in range(2):
        px(img, rng.randint(4, 11), rng.randint(5, 11), dark)
    return img


def t_sticks(rng, color, hi, dark):
    """Three hanging bristle strips."""
    img = blank()
    for i, cx in enumerate((4, 8, 12)):
        top = 2 + (i % 2)
        for y in range(top, top + 11):
            px(img, cx - 1, y, color)
            px(img, cx, y, dark if y % 3 == i % 3 else color)
            if (y + i) % 4 == 0:
                px(img, cx - 1, y, hi)
        px(img, cx, top + 10, dark)
    for _ in range(3):
        px(img, rng.randint(3, 13), rng.randint(3, 12), dark)
    return img


def t_scale(rng, base, dark, light):
    """Diamond scale plate with a bright keel line."""
    img = blank()
    for y in range(2, 14):
        h = 5 - abs(y - 8) if y >= 4 else y - 2
        h = max(0, min(5, h))
        x0, x1 = 7 - h, 8 + h
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (2, 13):
                color = dark
            elif x <= 6 and y <= 8:
                color = light
            else:
                color = base
            px(img, x, y, color)
    for y in range(4, 12):
        px(img, 7, y, light)
    for _ in range(3):
        px(img, rng.randint(6, 10), rng.randint(5, 11), dark)
    return img


def t_plate(rng, base, dark, light):
    """Riveted rectangular plating segment."""
    img = blank()
    rect(img, 2, 4, 13, 11, base)
    rect(img, 2, 4, 13, 4, light)
    rect(img, 2, 11, 13, 11, dark)
    for y in range(4, 12):
        px(img, 2, y, light)
        px(img, 13, y, dark)
    for x, y in ((4, 6), (11, 6), (4, 9), (11, 9)):
        px(img, x, y, dark)
        px(img, x + 1, y, light)
    for x in range(5, 11):
        px(img, x, 7, dark if x % 2 else base)
    for _ in range(3):
        px(img, rng.randint(3, 12), rng.randint(5, 10), dark)
    return img


def t_fluff(rng, base, dark, hi):
    """Fluffy fleece tuft with glowing pinpricks."""
    img = blank()
    lobes = [(5, 6, 2), (9, 5, 2), (11, 8, 2), (6, 10, 2), (9, 9, 3)]
    for cx, cy, r in lobes:
        rect(img, cx - r, cy - r, cx + r, cy + r, base)
    for cx, cy, r in lobes:
        px(img, cx - r, cy + r, dark)
        px(img, cx + r, cy + r, dark)
        px(img, cx - r + 1, cy - r + 1, hi)
    rect(img, 5, 12, 11, 12, dark)
    for x, y in ((6, 7), (10, 6), (8, 10)):
        px(img, x, y, EMBER_BRIGHT)
    for _ in range(3):
        px(img, rng.randint(4, 11), rng.randint(5, 11), dark)
    return img


# Egg palettes keyed by the mob id's theme prefix; the spots color comes from the
# base kind so same-prefix eggs still read differently.
PREFIX_PALETTES = {
    "magma": (MAGMA_RED, MAGMA_DARK, MAGMA_LIGHT),
    "soot": (CHARCOAL, CHARCOAL_DARK, CHARCOAL_LIGHT),
    "basalt": (BASALT, BASALT_DARK, BASALT_LIGHT),
    "slag": (SLAG, SLAG_DARK, SLAG_LIGHT),
    "obsidian": (OBSIDIAN, OBSIDIAN_DARK, OBSIDIAN_LIGHT),
    "ember": (EMBER, MAGMA_DARK, EMBER_BRIGHT),
    "ash": (ASH, ASH_DARK, ASH_LIGHT),
    "cinder": (CHARCOAL_LIGHT, CHARCOAL_DARK, ASH),
    "smolder": (SMOLDER, SMOLDER_DARK, SMOLDER_LIGHT),
}

BASE_SPOTS = {
    "strider": EMBER_BRIGHT,
    "cow": SLAG_DARK,
    "chicken": EMBER_YELLOW,
    "pig": PORK_RAW,
    "sheep": ASH_LIGHT,
}

DROP_TEX = {
    "magma_carapace": lambda r: t_shell(r, MAGMA_RED, MAGMA_DARK, MAGMA_LIGHT),
    "soot_bristles": lambda r: t_sticks(r, CHARCOAL, ASH, CHARCOAL_DARK),
    "basalt_scale": lambda r: t_scale(r, BASALT, BASALT_DARK, BASALT_LIGHT),
    "slag_husk": lambda r: t_shell(r, SLAG, SLAG_DARK, SLAG_LIGHT),
    "obsidian_plating": lambda r: t_plate(r, OBSIDIAN, OBSIDIAN_DARK, OBSIDIAN_LIGHT),
    "grazer_brisket": lambda r: t_meat(r, BEEF_RAW, BEEF_DARK),
    "yak_haunch": lambda r: t_meat(r, (0xB4, 0x40, 0x38), (0x7E, 0x28, 0x24)),
    "ox_loin": lambda r: t_meat(r, (0xD2, 0x54, 0x4A), BEEF_DARK),
    "buffalo_hump": lambda r: t_meat(r, (0xBE, 0x46, 0x3E), (0x88, 0x2C, 0x28)),
    "aurochs_shank": lambda r: t_meat(r, (0xAA, 0x38, 0x32), (0x74, 0x24, 0x20)),
    "hen_drumstick": lambda r: t_drumstick(r, POULTRY, POULTRY_DARK),
    "rooster_wing": lambda r: t_drumstick(r, (0xE0, 0xB4, 0x8E), (0xB4, 0x84, 0x5E)),
    "pullet_breast": lambda r: t_meat(r, (0xF0, 0xD2, 0xB4), POULTRY_DARK),
    "fowl_giblets": lambda r: t_meat(r, (0xB8, 0x62, 0x58), (0x86, 0x42, 0x3A)),
    "bantam_thigh": lambda r: t_drumstick(r, (0xEE, 0xC0, 0x9A), (0xC6, 0x8E, 0x66)),
    "hog_belly": lambda r: t_meat(r, PORK_RAW, PORK_DARK),
    "swine_hock": lambda r: t_drumstick(r, PORK_RAW, PORK_DARK),
    "boar_shoulder": lambda r: t_meat(r, (0xDE, 0x90, 0x88), (0xB2, 0x62, 0x5C)),
    "sow_jowl": lambda r: t_meat(r, (0xF0, 0xAC, 0xA4), PORK_DARK),
    "porker_ham": lambda r: t_meat(r, (0xE2, 0x96, 0x8E), (0xB6, 0x68, 0x60)),
    "ewe_shank": lambda r: t_meat(r, MUTTON_RAW, MUTTON_DARK),
    "ram_rack": lambda r: t_meat(r, (0xC0, 0x46, 0x54), MUTTON_DARK),
    "lamb_cutlet": lambda r: t_meat(r, (0xCA, 0x50, 0x5E), (0x8E, 0x30, 0x3E)),
    "wether_ribs": lambda r: t_meat(r, (0xA8, 0x36, 0x46), (0x72, 0x22, 0x30)),
    "smolder_fleece": lambda r: t_fluff(r, SMOLDER_LIGHT, SMOLDER_DARK, (0xEC, 0xE0, 0xD2)),
}


def emit_textures():
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for mob in MOBS:
        mob_id, base = mob[0], mob[2]
        prefix = mob_id.split("_")[0]
        b, d, l = PREFIX_PALETTES[prefix]
        img = spawn_egg(rng_for(f"{mob_id}_spawn_egg"), b, d, l, BASE_SPOTS[base],
                        CHARCOAL_DARK)
        img.save(tex_dir / f"{mob_id}_spawn_egg.png")
    for drop_id, fn in DROP_TEX.items():
        fn(rng_for(drop_id)).save(tex_dir / f"{drop_id}.png")


# ---------------------------------------------------------------------------
# Loot tables (infernomobs schema incl. "random_sequence")
# ---------------------------------------------------------------------------

def emit_loot_tables():
    for mob in MOBS:
        mob_id, drop_id, cmin, cmax = mob[0], mob[5], mob[8], mob[9]
        write_json(DATA / "loot_table" / "entities" / f"{mob_id}.json", {
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
                                        "max": cmax,
                                        "min": cmin,
                                    },
                                    "function": "minecraft:set_count",
                                }
                            ],
                            "name": f"{NS}:{drop_id}",
                        }
                    ],
                    "rolls": 1.0,
                }
            ],
            "random_sequence": f"{NS}:entities/{mob_id}",
        })


# ---------------------------------------------------------------------------
# Recipes + handbook entries
# ---------------------------------------------------------------------------
HANDBOOK = []

EN_ITEMS = {m[5]: m[6] for m in MOBS}
DE_ITEMS = {m[5]: m[7] for m in MOBS}


def emit_recipes():
    for name, ingredients, result_id, count in RECIPE_TABLE:
        genlib.emit_shapeless(RECIPES, name, [f"{NS}:{i}" for i in ingredients],
                              result_id, count, category="misc")
        drop = ingredients[0]
        n = len(ingredients)
        grid = [f"{NS}:{i}" for i in ingredients] + [""] * (9 - n)
        result_en, result_de = VANILLA_NAMES[result_id]
        HANDBOOK.append(("items", f"moltenfauna_{name}", f"{NS}:{drop}",
                         f"moltenfauna/{name}", grid, result_id, count,
                         f"Craft {count}x {result_en} from {n}x {EN_ITEMS[drop]} "
                         "at a crafting table.",
                         f"Stellt aus {n}x {DE_ITEMS[drop]} {count}x {result_de} "
                         "an der Werkbank her."))


def build_mob_handbook():
    for mob in MOBS:
        mob_id, _, base, en, de, drop_id, drop_en, drop_de = mob[:8]
        flavor_en, flavor_de = mob[12], mob[13]
        HANDBOOK.append(("mobs", mob_id, f"{NS}:{mob_id}_spawn_egg", None, None,
                         None, 0,
                         f"{en} - {flavor_en} Drops {drop_en}.",
                         f"{de} - {flavor_de} L\u00e4sst {drop_de} fallen."))


# ---------------------------------------------------------------------------
# Lang fragments
# ---------------------------------------------------------------------------

def build_lang():
    lang_en, lang_de = {}, {}
    for mob in MOBS:
        mob_id, en, de, drop_id, drop_en, drop_de = mob[0], mob[3], mob[4], mob[5], mob[6], mob[7]
        lang_en[f"entity.{NS}.{mob_id}"] = en
        lang_de[f"entity.{NS}.{mob_id}"] = de
        lang_en[f"item.{NS}.{mob_id}_spawn_egg"] = f"{en} Spawn Egg"
        lang_de[f"item.{NS}.{mob_id}_spawn_egg"] = f"{de}-Spawn-Ei"
        lang_en[f"item.{NS}.{drop_id}"] = drop_en
        lang_de[f"item.{NS}.{drop_id}"] = drop_de
    return lang_en, lang_de


# ---------------------------------------------------------------------------
# Java codegen: MoltenFaunaFeature + 25 entity subclasses + client class.
# All registration ids are plain string literals (audit check (f) requirement).
# ---------------------------------------------------------------------------
PKG = f"{genlib.PKG_ROOT}.feature.moltenfauna"


def gen_feature_java():
    out = [f"package {PKG};", ""]
    imports = [
        "java.util.function.Predicate",
        "",
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
        "net.minecraft.entity.attribute.EntityAttributes",
        "net.minecraft.entity.mob.MobEntity",
        "net.minecraft.entity.passive.AbstractCowEntity",
        "net.minecraft.entity.passive.AnimalEntity",
        "net.minecraft.entity.passive.ChickenEntity",
        "net.minecraft.entity.passive.PigEntity",
        "net.minecraft.entity.passive.SheepEntity",
        "net.minecraft.entity.passive.StriderEntity",
        "net.minecraft.item.Item",
        "net.minecraft.item.SpawnEggItem",
        "net.minecraft.registry.RegistryKey",
        "net.minecraft.registry.RegistryKeys",
        "net.minecraft.registry.tag.BlockTags",
        "net.minecraft.registry.tag.FluidTags",
        "net.minecraft.util.math.BlockPos",
        "net.minecraft.util.math.Direction",
        "net.minecraft.util.math.Vec3d",
        "net.minecraft.util.math.random.Random",
        "net.minecraft.world.Heightmap",
        "net.minecraft.world.ServerWorldAccess",
        f"{genlib.PKG_ROOT}.CopperInferno",
        f"{genlib.PKG_ROOT}.core.ModCreativeTab",
        f"{genlib.PKG_ROOT}.core.ModDimensions",
        f"{genlib.PKG_ROOT}.core.ModEntities",
        f"{genlib.PKG_ROOT}.core.ModItems",
    ]
    for imp in imports:
        out.append(f"import {imp};" if imp else "")
    out += ["", "/**",
            " * Molten Fauna: 25 passive/neutral mobs (all SpawnGroup.CREATURE) native to the Inferno",
            " * dimension - 5 lava-wading strider variants, 5 cow variants, 5 chicken variants, 5 pig",
            " * variants and 5 sheep variants. Each is a thin subclass of the vanilla base reusing the",
            " * vanilla renderer (see {@code MoltenFaunaFeatureClient}). EntityType dimensions/eye",
            " * heights are copied from the vanilla EntityType registrations (verified via bytecode);",
            " * attributes start from the vanilla create*Attributes() builders with per-mob tweaks.",
            " * Spawn eggs + drop items, natural spawns in the grazeable Inferno biomes",
            " * (cinder_wastes, ember_grove, slag_sea, verdigris_jungle, molten_delta), and drop",
            " * recipes are all registered here. Generated by {@code devtools/gen/moltenfauna_gen.py}.",
            " */",
            "public final class MoltenFaunaFeature {",
            "\tprivate MoltenFaunaFeature() {",
            "\t}", ""]

    for mob in MOBS:
        out.append(f"\tpublic static EntityType<{mob[1]}> {mob[0].upper()};")
    out.append("")
    for mob_id in MOB_IDS:
        out.append(f"\tpublic static Item {mob_id.upper()}_SPAWN_EGG;")
    out.append("")
    for drop_id in DROP_IDS:
        out.append(f"\tpublic static Item {drop_id.upper()};")
    out += ["",
            "\tpublic static void init() {",
            "\t\tregisterEntityTypes();",
            "\t\tregisterAttributes();",
            "\t\tregisterItems();",
            "\t\tregisterSpawning();",
            "\t\tMoltenFaunaHandbook.register();",
            "\t}", ""]

    # registerEntityTypes
    out.append("\tprivate static void registerEntityTypes() {")
    out.append("\t\t// Dimensions/eye heights/attachments/tracking ranges copied 1:1 from the vanilla")
    out.append("\t\t// EntityType registrations (1.21.9 bytecode):")
    for base_key in ("strider", "cow", "chicken", "pig", "sheep"):
        out.append(f"\t\t// {BASES[base_key]['comment']}.")
    for i, mob in enumerate(MOBS):
        mob_id, cls, base = mob[0], mob[1], mob[2]
        if i:
            out.append("")
        out.append(f"\t\t{mob_id.upper()} = ModEntities.register(\"{mob_id}\",")
        out.append(f"\t\t\t\tEntityType.Builder.create({cls}::new, SpawnGroup.CREATURE)")
        lines = BASES[base]["builder_lines"]
        for j, line in enumerate(lines):
            suffix = ");" if j == len(lines) - 1 else ""
            out.append(f"\t\t\t\t\t\t{line}{suffix}")
    out += ["\t}", ""]

    # registerAttributes
    out.append("\tprivate static void registerAttributes() {")
    out.append("\t\t// Vanilla create*Attributes() builders (verified via javap; createCowAttributes")
    out.append("\t\t// lives on AbstractCowEntity in 1.21.9) plus per-mob tweaks; EntityAttributes")
    out.append("\t\t// fields have no GENERIC_ prefix in 1.21.9 and Builder.add(...) overrides the")
    out.append("\t\t// base value (same proven pattern as InfernoMobsFeature).")
    for mob in MOBS:
        mob_id, base, attrs = mob[0], mob[2], mob[10]
        expr = BASES[base]["attrs"]
        if attrs:
            out.append(f"\t\tFabricDefaultAttributeRegistry.register({mob_id.upper()}, {expr}")
            for j, (field, value) in enumerate(attrs):
                suffix = ");" if j == len(attrs) - 1 else ""
                out.append(f"\t\t\t\t.add(EntityAttributes.{field}, {value}){suffix}")
        else:
            out.append(f"\t\tFabricDefaultAttributeRegistry.register({mob_id.upper()}, {expr});")
    out += ["\t}", ""]

    # registerItems
    out.append("\tprivate static void registerItems() {")
    out.append("\t\t// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the vanilla")
    out.append("\t\t// SpawnEggItem reads its entity type from in 1.21.9 (same proven pattern as")
    out.append("\t\t// the infernomobs eggs).")
    for mob_id in MOB_IDS:
        out.append(f"\t\t{mob_id.upper()}_SPAWN_EGG = ModItems.register(\"{mob_id}_spawn_egg\", SpawnEggItem::new,")
        out.append(f"\t\t\t\tnew Item.Settings().spawnEgg({mob_id.upper()}));")
    out.append("")
    for drop_id in DROP_IDS:
        out.append(f"\t\t{drop_id.upper()} = ModItems.register(\"{drop_id}\", Item::new, new Item.Settings());")
    out.append("")
    out.append("\t\tItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MOBS_KEY).register(entries -> {")
    out.append("\t\t\t// Spawn eggs together, then the mob drops.")
    for mob_id in MOB_IDS:
        out.append(f"\t\t\tentries.add({mob_id.upper()}_SPAWN_EGG);")
    for drop_id in DROP_IDS:
        out.append(f"\t\t\tentries.add({drop_id.upper()});")
    out.append("\t\t});")
    out += ["\t}", ""]

    # registerSpawning
    out.append("\tprivate static void registerSpawning() {")
    out.append("\t\t// SpawnRestriction.register is private in vanilla but access-widened by Fabric's")
    out.append("\t\t// transitive access wideners (same proven pattern as InfernoMobsFeature).")
    out.append("\t\t// Locations/heightmaps mirror the vanilla entries (SpawnRestriction bytecode):")
    out.append("\t\t// chicken/cow/pig/sheep use ON_GROUND + MOTION_BLOCKING_NO_LEAVES +")
    out.append("\t\t// AnimalEntity::isValidNaturalSpawn, strider uses IN_LAVA +")
    out.append("\t\t// MOTION_BLOCKING_NO_LEAVES + StriderEntity::canSpawn. Both vanilla predicates")
    out.append("\t\t// are typed to the vanilla EntityTypes (and take WorldAccess), so they are")
    out.append("\t\t// reimplemented 1:1 below (canInfernoAnimalSpawn / canInfernoStriderSpawn).")
    for mob in MOBS:
        mob_id, base = mob[0], mob[2]
        if base == "strider":
            out.append(f"\t\tSpawnRestriction.register({mob_id.upper()}, SpawnLocationTypes.IN_LAVA,")
            out.append("\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES,")
            out.append("\t\t\t\tMoltenFaunaFeature::canInfernoStriderSpawn);")
        else:
            out.append(f"\t\tSpawnRestriction.register({mob_id.upper()}, SpawnLocationTypes.ON_GROUND,")
            out.append("\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES,")
            out.append("\t\t\t\tMoltenFaunaFeature::canInfernoAnimalSpawn);")
    out.append("")
    out.append("\t\t// Natural spawns in the grazeable Inferno biomes (SPAWN_BIOMES in the")
    out.append("\t\t// generator; biome JSONs are owned by infernodim/infernodim2 and includeByKey")
    out.append("\t\t// simply matches nothing until they are loaded).")
    out.append("\t\tPredicate<BiomeSelectionContext> infernoBiomes = BiomeSelectors.includeByKey(")
    for i, biome in enumerate(SPAWN_BIOMES):
        suffix = ");" if i == len(SPAWN_BIOMES) - 1 else ","
        out.append(f"\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"{biome}\")){suffix}")
    for mob in MOBS:
        mob_id, (weight, gmin, gmax) = mob[0], mob[11]
        out.append(f"\t\tBiomeModifications.addSpawn(infernoBiomes, SpawnGroup.CREATURE, "
                   f"{mob_id.upper()}, {weight}, {gmin}, {gmax});")
    out += ["\t}", ""]

    # spawn predicates
    out += [
        "\t/**",
        "\t * Mirrors vanilla {@code AnimalEntity.isValidNaturalSpawn} (verified against the 1.21.9",
        "\t * bytecode; the vanilla method takes {@code WorldAccess} and cannot be used as a",
        "\t * {@code SpawnRestriction.SpawnPredicate} method reference here without reimplementing",
        "\t * the Inferno branch anyway): spawnable when the block below is ANIMALS_SPAWNABLE_ON and",
        "\t * the light gate passes ({@code getBaseLightLevel(pos, 0) > 8}, skipped for trial-spawner",
        "\t * spawns - {@code AnimalEntity.isLightLevelValidForNaturalSpawn} is protected, so its",
        "\t * one-line body is inlined).",
        "\t *",
        "\t * <p>In the Inferno the ANIMALS_SPAWNABLE_ON gate would starve spawns entirely - the",
        "\t * ground there is cinderstone/ash/ember soil/basalt/blackstone, none of which are in the",
        "\t * tag (it only contains {@code minecraft:grass_block}) - so the Inferno branch keeps just",
        "\t * the base {@code MobEntity.canMobSpawn} checks (same proven pattern as the ash bat).",
        "\t */",
        "\tprivate static boolean canInfernoAnimalSpawn(EntityType<? extends AnimalEntity> type,",
        "\t\t\tServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {",
        "\t\tif (world.toServerWorld().getRegistryKey() == ModDimensions.INFERNO_WORLD) {",
        "\t\t\treturn MobEntity.canMobSpawn(type, world, reason, pos, random);",
        "\t\t}",
        "\t\tboolean lightOk = SpawnReason.isTrialSpawner(reason) || world.getBaseLightLevel(pos, 0) > 8;",
        "\t\treturn world.getBlockState(pos.down()).isIn(BlockTags.ANIMALS_SPAWNABLE_ON) && lightOk;",
        "\t}",
        "",
        "\t/**",
        "\t * Mirrors vanilla {@code StriderEntity.canSpawn} (verified against the 1.21.9 bytecode;",
        "\t * the vanilla method is typed to {@code EntityType<StriderEntity>} so it cannot be reused",
        "\t * directly - same reimplementation as the cinder strider): walk upward through the lava",
        "\t * column above the spawn pos, then require the first non-lava block to be air.",
        "\t */",
        "\tprivate static boolean canInfernoStriderSpawn(EntityType<? extends StriderEntity> type,",
        "\t\t\tServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {",
        "\t\tBlockPos.Mutable mutable = pos.mutableCopy();",
        "\t\tdo {",
        "\t\t\tmutable.move(Direction.UP);",
        "\t\t} while (world.getFluidState(mutable).isIn(FluidTags.LAVA));",
        "\t\treturn world.getBlockState(mutable).isAir();",
        "\t}",
        "}",
    ]
    return "\n".join(out) + "\n"


STRIDER_ENTITY_TEMPLATE = '''package {pkg};

import java.util.Objects;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

/**
 * {flavor} Behavior is vanilla strider (lava walking, saddling, warped-fungus breeding are all
 * inherited) except that breeding produces {name_lower} babies (not vanilla striders) and natural
 * spawns never roll the vanilla zombified-piglin/baby-strider jockeys (same proven pattern as
 * the cinder strider). Drops {drop_en} ({{@code loot_table/entities/{mob_id}.json}}).
 */
public class {cls} extends StriderEntity {{
	public {cls}(EntityType<? extends StriderEntity> type, World world) {{
		super(type, world);
	}}

	/**
	 * Vanilla {{@code StriderEntity.createChild(ServerWorld, PassiveEntity)}} hard-codes
	 * {{@code EntityType.STRIDER}} (bytecode-verified), so bred {name_lower} pairs would produce
	 * vanilla striders. Covariant override returning our own type instead.
	 */
	@Override
	public {cls} createChild(ServerWorld world, PassiveEntity entity) {{
		return new {cls}(MoltenFaunaFeature.{const}, world);
	}}

	/**
	 * Reimplements {{@code StriderEntity.initialize}} minus the jockey rolls.
	 *
	 * <p>DROPPED super behavior (StriderEntity.initialize, 1.21.9 bytecode-verified): for
	 * non-baby spawns, a 1/30 roll that saddles this strider and mounts a zombified piglin
	 * holding a warped fungus on a stick, and a further 1/10 roll that mounts a <em>vanilla</em>
	 * baby strider as a jockey - both would attach vanilla mobs to a {name_lower}. Only the
	 * fallback branch ({{@code entityData = new PassiveData(0.5f)}}) is kept.
	 *
	 * <p>Because Java has no {{@code super.super}}, the adult path below also reimplements the
	 * grandparent chain 1:1 from bytecode: {{@code PassiveEntity.initialize}} (PassiveData baby
	 * roll + countSpawned) and {{@code MobEntity.initialize}} (random FOLLOW_RANGE spawn bonus +
	 * 5% left-handedness). {{@code AnimalEntity}}/{{@code PathAwareEntity}} do not override
	 * {{@code initialize}} in 1.21.9.
	 */
	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
			SpawnReason spawnReason, EntityData entityData) {{
		if (this.isBaby()) {{
			// Vanilla's baby path performs no jockey roll (it delegates straight up the
			// chain), so super is safe here.
			return super.initialize(world, difficulty, spawnReason, entityData);
		}}
		PassiveEntity.PassiveData passiveData = new PassiveEntity.PassiveData(0.5f);
		// PassiveEntity.initialize: a fresh PassiveData has spawnedCount == 0, so (as in
		// vanilla striders) the baby roll below never triggers for the first pack member.
		if (passiveData.canSpawnBaby() && passiveData.getSpawnedCount() > 0
				&& world.getRandom().nextFloat() <= passiveData.getBabyChance()) {{
			this.setBreedingAge(-24000);
		}}
		passiveData.countSpawned();
		// MobEntity.initialize: RANDOM_SPAWN_BONUS_MODIFIER_ID is protected static on
		// MobEntity, so it is directly accessible here.
		Random random = world.getRandom();
		EntityAttributeInstance followRange = Objects.requireNonNull(
				this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE));
		if (!followRange.hasModifier(RANDOM_SPAWN_BONUS_MODIFIER_ID)) {{
			followRange.addPersistentModifier(new EntityAttributeModifier(
					RANDOM_SPAWN_BONUS_MODIFIER_ID, random.nextTriangular(0.0, 0.11485),
					EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
		}}
		this.setLeftHanded(random.nextFloat() < 0.05f);
		return passiveData;
	}}
}}
'''

SIMPLE_ENTITY_TEMPLATE = '''package {pkg};

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.{base};
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * {flavor} Behavior is pure vanilla {base_lower} (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces {name_lower} babies: vanilla
 * {{@code {base}.createChild}} hard-codes {{@code EntityType.{base_const}}}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * {drop_en} ({{@code loot_table/entities/{mob_id}.json}}).
 */
public class {cls} extends {base} {{
	public {cls}(EntityType<? extends {base}> type, World world) {{
		super(type, world);
	}}

	@Override
	public {cls} createChild(ServerWorld world, PassiveEntity entity) {{
		return new {cls}(MoltenFaunaFeature.{const}, world);
	}}
}}
'''

SHEEP_ENTITY_TEMPLATE = '''package {pkg};

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

/**
 * {flavor} Behavior is pure vanilla sheep (grazing, shearing, dyeing and breeding are all
 * inherited) except that breeding produces {name_lower} babies: vanilla
 * {{@code SheepEntity.createChild}} hard-codes {{@code EntityType.SHEEP}} (bytecode-verified),
 * so it is overridden covariantly, mirroring the vanilla parent-color mix via the public
 * {{@code DyeColor.mixColors(ServerWorld, DyeColor, DyeColor)}} (verified via javap). Drops
 * {drop_en} ({{@code loot_table/entities/{mob_id}.json}}).
 */
public class {cls} extends SheepEntity {{
	public {cls}(EntityType<? extends SheepEntity> type, World world) {{
		super(type, world);
	}}

	@Override
	public {cls} createChild(ServerWorld world, PassiveEntity entity) {{
		{cls} child = new {cls}(MoltenFaunaFeature.{const}, world);
		child.setColor(DyeColor.mixColors(world, this.getColor(), ((SheepEntity) entity).getColor()));
		return child;
	}}
}}
'''

BASE_CONSTS = {"cow": "COW", "chicken": "CHICKEN", "pig": "PIG"}


def gen_entity_java(mob):
    mob_id, cls, base, en, de, drop_id, drop_en = mob[:7]
    flavor = f"{en} - {mob[12]}"
    name_lower = en.lower()
    if base == "strider":
        return STRIDER_ENTITY_TEMPLATE.format(pkg=PKG, cls=cls, const=mob_id.upper(),
                                              flavor=flavor, name_lower=name_lower,
                                              drop_en=drop_en, mob_id=mob_id)
    if base == "sheep":
        return SHEEP_ENTITY_TEMPLATE.format(pkg=PKG, cls=cls, const=mob_id.upper(),
                                            flavor=flavor, name_lower=name_lower,
                                            drop_en=drop_en, mob_id=mob_id)
    java_base = BASES[base]["java_base"]
    return SIMPLE_ENTITY_TEMPLATE.format(pkg=PKG, cls=cls, const=mob_id.upper(),
                                         base=java_base, base_lower=base,
                                         base_const=BASE_CONSTS[base], flavor=flavor,
                                         name_lower=name_lower, drop_en=drop_en,
                                         mob_id=mob_id)


def gen_client_java():
    renderers = sorted({BASES[m[2]]["renderer"] for m in MOBS})
    imports = sorted([f"net.minecraft.client.render.entity.{r}" for r in renderers]
                     + ["net.minecraft.client.render.entity.EntityRendererFactories"])
    out = [f"package {PKG}.client;", ""]
    out += [f"import {imp};" for imp in imports]
    out.append(f"import {PKG}.MoltenFaunaFeature;")
    out += ["",
            "/**",
            " * Client-side setup for the Molten Fauna mobs: each type reuses its vanilla renderer",
            " * (all five ctors are Context-only, verified via javap against the 1.21.9 client jar).",
            " * The mobs extend the matching vanilla entities, so the factories fit the",
            " * register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;) bound; the",
            " * vanilla register method is access-widened by Fabric's transitive access wideners",
            " * (same proven pattern as the infernomobs renderers).",
            " */",
            "public final class MoltenFaunaFeatureClient {",
            "\tprivate MoltenFaunaFeatureClient() {",
            "\t}", "",
            "\tpublic static void initClient() {"]
    for mob in MOBS:
        out.append(f"\t\tEntityRendererFactories.register(MoltenFaunaFeature.{mob[0].upper()}, "
                   f"{BASES[mob[2]]['renderer']}::new);")
    out += ["\t}", "}"]
    return "\n".join(out) + "\n"


def emit_java():
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)
    CLIENT_DIR.mkdir(parents=True, exist_ok=True)
    (FEATURE_DIR / "MoltenFaunaFeature.java").write_text(gen_feature_java(),
                                                         encoding="utf-8")
    for mob in MOBS:
        (FEATURE_DIR / f"{mob[1]}.java").write_text(gen_entity_java(mob),
                                                    encoding="utf-8")
    (CLIENT_DIR / "MoltenFaunaFeatureClient.java").write_text(gen_client_java(),
                                                              encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the Molten Fauna feature: one \"mobs\" page per mob (25) plus one",
        "\"items\" recipe page for every JSON under",
        "{@code data/copper_inferno/recipe/moltenfauna/} (25). Entry texts and grids mirror the",
        "recipe JSONs emitted by {@code devtools/gen/moltenfauna_gen.py};",
        "{@code devtools/check_handbook.py} parses the inline {@code new HandbookEntry(...)}",
        "literals positionally, so keep them inline.",
    ]
    handbook_src = genlib.java_handbook_class("moltenfauna", "MoltenFaunaHandbook",
                                              handbook_doc, HANDBOOK)
    (FEATURE_DIR / "MoltenFaunaHandbook.java").write_text(handbook_src, encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/moltenfauna.txt; format per devtools/hooks/README.md)
# ---------------------------------------------------------------------------

def emit_hooks():
    lines = [
        "# moltenfauna feature hooks (format: devtools/hooks/README.md)", "",
        "[init]",
        "import net.sonic0810.copperinferno.feature.moltenfauna.MoltenFaunaFeature;",
        "\t\tMoltenFaunaFeature.init();", "",
        "[client-init]",
        "import net.sonic0810.copperinferno.feature.moltenfauna.client.MoltenFaunaFeatureClient;",
        "\t\tMoltenFaunaFeatureClient.initClient();", "",
        "[recipe-dir]",
        "moltenfauna", "",
        "[counts]",
        f"mobs: {len(MOBS)}",
        f"items: {len(ITEM_IDS)}",
        f"recipes: {len(RECIPE_TABLE)}",
        f"handbook-entries: {len(HANDBOOK)}", "",
    ]
    path = ROOT / "devtools" / "hooks" / "moltenfauna.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main():
    assert len(MOBS) == 25, f"expected 25 mobs, got {len(MOBS)}"
    assert len(set(MOB_IDS)) == 25, "duplicate mob ids"
    assert len(EGG_IDS) == 25, f"expected 25 spawn eggs, got {len(EGG_IDS)}"
    assert len(set(ITEM_IDS)) == 50, "duplicate item ids"
    assert set(DROP_IDS) == set(DROP_TEX), "DROP_TEX table out of sync with MOBS"
    assert len(RECIPE_TABLE) == 25, f"expected 25 recipes, got {len(RECIPE_TABLE)}"
    recipe_drops = {r[1][0] for r in RECIPE_TABLE}
    assert recipe_drops == set(DROP_IDS), "every drop needs exactly one recipe"
    for name, ingredients, result_id, count in RECIPE_TABLE:
        assert result_id in VANILLA_NAMES, f"missing vanilla name for {result_id}"
        assert any(i in DROP_IDS for i in ingredients), f"recipe {name} has no own id"

    # Item defs + models (1.21.9 two-file contract) + textures.
    for item_id in ITEM_IDS:
        genlib.emit_item_def(ASSETS, item_id)
        genlib.emit_item_model(ASSETS, item_id)
    emit_textures()

    emit_loot_tables()
    build_mob_handbook()
    emit_recipes()

    lang_en, lang_de = build_lang()
    genlib.lang_fragments(ASSETS, "moltenfauna", lang_en, lang_de)

    emit_java()
    emit_hooks()

    # Consistency asserts.
    assert sorted(lang_en) == sorted(lang_de), "EN/DE lang key sets differ"
    assert len(lang_en) == 75, f"expected 75 lang keys, got {len(lang_en)}"
    assert len(HANDBOOK) == 50, f"expected 50 handbook entries, got {len(HANDBOOK)}"
    tex_dir = ASSETS / "textures" / "item"
    for item_id in ITEM_IDS:
        assert (ASSETS / "items" / f"{item_id}.json").is_file()
        assert (ASSETS / "models" / "item" / f"{item_id}.json").is_file()
        assert (tex_dir / f"{item_id}.png").is_file()
    for mob_id in MOB_IDS:
        assert (DATA / "loot_table" / "entities" / f"{mob_id}.json").is_file()
        assert (FEATURE_DIR / f"{dict((m[0], m[1]) for m in MOBS)[mob_id]}.java").is_file()
    print(f"moltenfauna_gen: {len(MOBS)} mobs, {len(ITEM_IDS)} items (defs, models, "
          f"textures), {len(RECIPE_TABLE)} recipes, {len(HANDBOOK)} handbook entries, "
          "EN+DE lang fragments, Feature+entities+client+Handbook java, hook file.")


if __name__ == "__main__":
    main()
