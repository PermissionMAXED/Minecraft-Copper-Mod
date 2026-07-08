#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "slagfiends" feature (25 hostile mobs).

25 slag-fiend mobs native to the Inferno dimension, five thin subclasses per vanilla base
(WitherSkeletonEntity, HoglinEntity, PiglinEntity, EndermanEntity, ZombifiedPiglinEntity),
each reusing the vanilla renderer. Every mob ships a spawn egg, a unique drop item, an
entity loot table, a drop recipe and handbook pages.

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via genlib.rng_for). Emits by DEFAULT (no flags):
  - 16x16 RGBA item textures assets/copper_inferno/textures/item/<id>.png (Pillow, seeded)
  - item model-definitions   assets/copper_inferno/items/<id>.json  (infernomobs format)
  - item models              assets/copper_inferno/models/item/<id>.json (item/generated)
  - entity loot tables       data/copper_inferno/loot_table/entities/<mob>.json
                             (infernomobs schema incl. "random_sequence")
  - recipes                  data/copper_inferno/recipe/slagfiends/*.json (each recipe's
                             ingredients include at least one of this feature's own ids)
  - lang fragments           assets/copper_inferno/lang/fragments/slagfiends.json (EN)
                             assets/copper_inferno/lang/fragments_de/slagfiends.json (DE)
  - src/main/java/.../feature/slagfiends/SlagFiendsFeature.java (literal registrations),
    25 entity subclasses and SlagFiendsHandbook.java (genlib.java_handbook_class)
  - src/client/java/.../feature/slagfiends/client/SlagFiendsFeatureClient.java
  - devtools/hooks/slagfiends.txt (integration hook file)

All vanilla facts baked into the generated Java were verified against the 1.21.9 Yarn
bytecode via javap (see the comments in the generated sources):
  - EntityType.Builder chains are copied 1:1 from the vanilla EntityType registrations.
  - Default attribute suppliers match DefaultAttributeRegistry (AbstractSkeletonEntity
    .createAbstractSkeletonAttributes / HoglinEntity.createHoglinAttributes /
    PiglinEntity.createPiglinAttributes / EndermanEntity.createEndermanAttributes /
    ZombifiedPiglinEntity.createZombifiedPiglinAttributes).
  - Spawn predicates mirror the vanilla SpawnRestriction entries: wither skeleton and
    enderman use HostileEntity::canSpawnInDark (typed to `? extends HostileEntity`, so it
    is reused directly); hoglin/piglin/zombified piglin's canSpawn methods are typed to
    the vanilla EntityTypes and are therefore reimplemented 1:1 inline.
  - PiglinEntityRenderer / ZombifiedPiglinEntityRenderer take (Context, EntityModelLayer,
    EntityModelLayer, EquipmentModelData, EquipmentModelData); the vanilla factories pass
    the EntityModelLayers.[ZOMBIFIED_]PIGLIN[_BABY][_EQUIPMENT] constants.
"""

import math
import sys
from collections import namedtuple
from pathlib import Path
from random import Random

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, rng_for, write_json

RECIPES = DATA / "recipe" / "slagfiends"
FEATURE_DIR = ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "slagfiends"
CLIENT_DIR = ROOT / "src" / "client" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "slagfiends" / "client"

# Inferno biomes the fiends haunt: the original trio plus the lava-delta and jungle
# infernodim2 biomes (molten_delta, verdigris_jungle). The canonical 7-biome list of the
# Inferno dimension lives in devtools/gen/infernodim2_gen.py (BIOMES/CLIMATE_POINTS);
# the barren soot_dunes and the quiet crystal_hollows stay fiend-free.
SPAWN_BIOMES = ["cinder_wastes", "ember_grove", "slag_sea",
                "molten_delta", "verdigris_jungle"]

# ---------------------------------------------------------------------------
# Palettes (house ember/ash/charcoal values from infernomobs_gen.py plus per-family hues).
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

BONE = (0xE8, 0xE2, 0xD0)
BONE_SHADE = (0xC4, 0xBC, 0xA4)
BONE_DARK = (0x8E, 0x86, 0x6E)

GOLD = (0xF2, 0xC1, 0x4E)
GOLD_DARK = (0xB8, 0x8A, 0x1E)
GOLD_LIGHT = (0xFF, 0xE0, 0x86)

PIG_PINK = (0xE0, 0x9A, 0x88)
PIG_PINK_DARK = (0xA8, 0x62, 0x54)
PIG_PINK_LIGHT = (0xF2, 0xC0, 0xAE)

FLESH_RED = (0xA8, 0x46, 0x3E)
FLESH_DARK = (0x6E, 0x2C, 0x2A)
FLESH_LIGHT = (0xC8, 0x6A, 0x58)

ENDER_PURPLE = (0x8A, 0x4A, 0xC8)
ENDER_DARK = (0x46, 0x22, 0x6A)
ENDER_LIGHT = (0xB8, 0x84, 0xE8)
ENDER_BLACK = (0x18, 0x12, 0x1E)

TEAL = (0x3E, 0x8A, 0x7E)
TEAL_DARK = (0x26, 0x56, 0x4E)
TEAL_LIGHT = (0x6E, 0xB8, 0xA8)

# ---------------------------------------------------------------------------
# Data model. Per mob:
#   mid/en/de           mob id + display names (DE is real German)
#   base                one of the BASES keys below
#   flavor_en/flavor_de handbook flavor sentence fragments
#   drop                (drop id, EN, DE, loot min, loot max, texture style, palette)
#   attrs               ((EntityAttributes field, java double literal), ...) tuning
#   spawn               (weight, minGroup, maxGroup) for the three Inferno biomes
#   egg                 (base, dark, light, spots, outline) spawn-egg palette
#   recipe              (name, ingredient ids, result id, count, result EN, result DE)
# ---------------------------------------------------------------------------

Mob = namedtuple("Mob", "mid base en de flavor_en flavor_de drop attrs spawn egg recipe")

m = lambda p: f"{NS}:{p}"

MOBS = [
    # ------------------------- WitherSkeletonEntity based -------------------------
    Mob("slag_wraith", "wskel", "Slag Wraith", "Schlackengeist",
        "a slag-crusted wither skeleton spirit",
        "ein schlackenverkrusteter Witherskelett-Geist",
        ("slagbone_shard", "Slagbone Shard", "Schlackenknochen-Splitter", 0.0, 2.0,
         "shard", (BONE, BONE_SHADE, BONE_DARK, CHARCOAL)),
        (("MAX_HEALTH", "24.0"), ("SCALE", "1.1")),
        (10, 1, 3),
        (CHARCOAL, CHARCOAL_DARK, CHARCOAL_LIGHT, BONE, CHARCOAL_DARK),
        ("bone_meal_from_slagbone_shard", [m("slagbone_shard")],
         "minecraft:bone_meal", 3, "Bone Meal", "Knochenmehl")),
    Mob("cinder_reaper", "wskel", "Cinder Reaper", "Aschenschnitter",
        "a swift, scythe-armed skeletal reaper",
        "ein flinker, sensenbewehrter Skelett-Schnitter",
        ("reaper_cinder", "Reaper Cinder", "Schnitterasche", 0.0, 2.0,
         "chunk", (CHARCOAL_DARK, CHARCOAL, CHARCOAL_LIGHT, EMBER, EMBER_BRIGHT)),
        (("MAX_HEALTH", "22.0"), ("MOVEMENT_SPEED", "0.3")),
        (9, 1, 2),
        (ASH_DARK, CHARCOAL, ASH, EMBER, CHARCOAL_DARK),
        ("inferno_powder_from_reaper_cinder", [m("reaper_cinder"), m("copper_dust")],
         m("inferno_powder"), 2, "Inferno Powder", "Infernopulver")),
    Mob("ashbone_knight", "wskel", "Ashbone Knight", "Aschenknochen-Ritter",
        "an armored skeletal knight of ash-bleached bone",
        "ein gepanzerter Skelett-Ritter aus aschgebleichtem Knochen",
        ("ashbone_chip", "Ashbone Chip", "Aschenknochen-Span", 0.0, 2.0,
         "shard", (ASH_LIGHT, ASH, ASH_DARK, CHARCOAL)),
        (("MAX_HEALTH", "30.0"), ("KNOCKBACK_RESISTANCE", "0.5")),
        (7, 1, 2),
        (ASH, ASH_DARK, ASH_LIGHT, BONE, CHARCOAL_DARK),
        ("bone_from_ashbone_chip", [m("ashbone_chip"), m("ashbone_chip")],
         "minecraft:bone", 1, "Bone", "Knochen")),
    Mob("ember_revenant", "wskel", "Ember Revenant", "Glutwiederg\u00e4nger",
        "a smouldering revenant that refuses to stay slain",
        "ein schwelender Wiederg\u00e4nger, der sich nicht bezwingen l\u00e4sst",
        ("revenant_ember", "Revenant Ember", "Wiederg\u00e4nger-Glut", 0.0, 1.0,
         "shard", (EMBER_YELLOW, EMBER_BRIGHT, EMBER, CHARCOAL)),
        (("MAX_HEALTH", "26.0"), ("SCALE", "1.05")),
        (8, 1, 2),
        (EMBER, CHARCOAL_DARK, EMBER_BRIGHT, EMBER_YELLOW, CHARCOAL_DARK),
        ("blaze_powder_from_revenant_ember", [m("revenant_ember")],
         "minecraft:blaze_powder", 2, "Blaze Powder", "Lohenstaub")),
    Mob("sootbone_harrower", "wskel", "Sootbone Harrower", "Ru\u00dfknochen-Peiniger",
        "a stooped, soot-black harrower of the ashfields",
        "ein gebeugter, ru\u00dfschwarzer Peiniger der Aschefelder",
        ("harrower_soot", "Harrower Soot", "Peinigerru\u00df", 0.0, 2.0,
         "chunk", (CHARCOAL_DARK, CHARCOAL, CHARCOAL_LIGHT, ASH_DARK, ASH)),
        (("MOVEMENT_SPEED", "0.28"), ("SCALE", "0.95")),
        (9, 1, 3),
        (CHARCOAL_DARK, ENDER_BLACK, CHARCOAL, ASH_DARK, ENDER_BLACK),
        ("black_dye_from_harrower_soot", [m("harrower_soot")],
         "minecraft:black_dye", 2, "Black Dye", "Schwarzer Farbstoff")),
    # ----------------------------- HoglinEntity based -----------------------------
    Mob("molten_hoglin", "hoglin", "Molten Hoglin", "Schmelzhoglin",
        "a hulking hoglin dripping molten slag",
        "ein massiger Hoglin, von dem geschmolzene Schlacke trieft",
        # NOTE: "molten_tusk" is taken by the archfiends feature -> molten_hoglin_tusk.
        ("molten_hoglin_tusk", "Molten Hoglin Tusk", "Schmelzhoglin-Hauer", 0.0, 2.0,
         "tusk", (EMBER_HOT, EMBER_BRIGHT, EMBER)),
        (("MAX_HEALTH", "48.0"), ("SCALE", "1.1")),
        (8, 1, 3),
        (EMBER_BRIGHT, EMBER, EMBER_YELLOW, CHARCOAL, CHARCOAL_DARK),
        ("gold_nugget_from_molten_hoglin_tusk", [m("molten_hoglin_tusk")],
         "minecraft:gold_nugget", 3, "Gold Nugget", "Goldklumpen")),
    Mob("cinderhide_boar", "hoglin", "Cinderhide Boar", "Aschenfell-Keiler",
        "a fast, cinder-pelted boar",
        "ein schneller Keiler mit Aschenfell",
        ("cinderhide_scrap", "Cinderhide Scrap", "Aschenfell-Fetzen", 0.0, 2.0,
         "hide", (ASH, ASH_DARK, ASH_LIGHT, CHARCOAL, CHARCOAL_DARK)),
        (("MAX_HEALTH", "36.0"), ("MOVEMENT_SPEED", "0.34")),
        (9, 1, 3),
        (ASH_DARK, CHARCOAL, ASH, EMBER_BRIGHT, CHARCOAL_DARK),
        ("leather_from_cinderhide_scrap", [m("cinderhide_scrap"), m("cinderhide_scrap")],
         "minecraft:leather", 1, "Leather", "Leder")),
    Mob("slagtusk_brute", "hoglin", "Slagtusk Brute", "Schlackenhauer-Rohling",
        "a slab-shouldered brute with tusks of hardened slag",
        "ein breitschultriger Rohling mit Hauern aus geh\u00e4rteter Schlacke",
        ("slagtusk_fragment", "Slagtusk Fragment", "Schlackenhauer-Bruchst\u00fcck", 0.0, 2.0,
         "tusk", (ASH_LIGHT, ASH, ASH_DARK)),
        (("ATTACK_DAMAGE", "8.0"), ("KNOCKBACK_RESISTANCE", "0.75")),
        (6, 1, 2),
        (ASH, CHARCOAL_DARK, ASH_LIGHT, EMBER, CHARCOAL_DARK),
        ("orange_dye_from_slagtusk_fragment", [m("slagtusk_fragment")],
         "minecraft:orange_dye", 2, "Orange Dye", "Oranger Farbstoff")),
    Mob("emberback_hog", "hoglin", "Emberback Hog", "Glutr\u00fccken-Eber",
        "a hog whose bristled back glows with embers",
        "ein Eber, dessen borstiger R\u00fccken vor Glut gl\u00fcht",
        ("emberback_bristle", "Emberback Bristle", "Glutr\u00fccken-Borste", 0.0, 2.0,
         "shard", (EMBER_BRIGHT, EMBER, CHARCOAL, CHARCOAL_DARK)),
        (("MAX_HEALTH", "44.0"), ("SCALE", "1.05")),
        (8, 1, 3),
        (EMBER, EMBER_BRIGHT, EMBER_HOT, CHARCOAL_LIGHT, CHARCOAL_DARK),
        ("string_from_emberback_bristle", [m("emberback_bristle")],
         "minecraft:string", 2, "String", "Faden")),
    Mob("ashsnout_gorer", "hoglin", "Ashsnout Gorer", "Aschenschnauzen-Spie\u00dfer",
        "an ill-tempered gorer with an ash-grey snout",
        "ein reizbarer Spie\u00dfer mit aschgrauer Schnauze",
        ("ashsnout_hide", "Ashsnout Hide", "Aschenschnauzen-Haut", 1.0, 2.0,
         "hide", (PIG_PINK, PIG_PINK_DARK, PIG_PINK_LIGHT, ASH, ASH_DARK)),
        (("ATTACK_DAMAGE", "7.0"), ("MOVEMENT_SPEED", "0.32")),
        (7, 1, 2),
        (PIG_PINK_DARK, CHARCOAL, PIG_PINK, ASH, CHARCOAL_DARK),
        ("leather_from_ashsnout_hide", [m("ashsnout_hide"), m("ashsnout_hide")],
         "minecraft:leather", 2, "Leather", "Leder")),
    # ----------------------------- PiglinEntity based -----------------------------
    Mob("cinder_piglin", "piglin", "Cinder Piglin", "Aschenpiglin",
        "a cinder-dusted piglin warrior",
        "ein aschebest\u00e4ubter Piglin-Krieger",
        ("cinder_tooth", "Cinder Tooth", "Aschenzahn", 0.0, 2.0,
         "tusk", (BONE, BONE_SHADE, BONE_DARK)),
        (("MAX_HEALTH", "20.0"),),
        (10, 1, 3),
        (PIG_PINK, PIG_PINK_DARK, PIG_PINK_LIGHT, CHARCOAL, CHARCOAL_DARK),
        ("bone_meal_from_cinder_tooth", [m("cinder_tooth")],
         "minecraft:bone_meal", 2, "Bone Meal", "Knochenmehl")),
    Mob("slagforged_piglin", "piglin", "Slagforged Piglin", "Schlackengeschmiedetes Piglin",
        "a piglin clad in crude slag-forged plates",
        "ein Piglin in grob schlackengeschmiedeten Platten",
        ("slagforged_plate", "Slagforged Plate", "Schlackengeschmiedete Platte", 0.0, 1.0,
         "hide", (ASH_DARK, CHARCOAL, ASH, EMBER, CHARCOAL_DARK)),
        (("MAX_HEALTH", "24.0"), ("KNOCKBACK_RESISTANCE", "0.3")),
        (8, 1, 2),
        (ASH_DARK, CHARCOAL_DARK, ASH, GOLD, CHARCOAL_DARK),
        ("iron_nugget_from_slagforged_plate", [m("slagforged_plate")],
         "minecraft:iron_nugget", 3, "Iron Nugget", "Eisenklumpen")),
    Mob("emberclad_piglin", "piglin", "Emberclad Piglin", "Glutgepanzertes Piglin",
        "a piglin armored in glowing ember shards",
        "ein in gl\u00fchende Glutsplitter gepanzertes Piglin",
        ("emberclad_scrap", "Emberclad Scrap", "Glutpanzer-Fetzen", 0.0, 2.0,
         "shard", (GOLD, GOLD_DARK, EMBER, CHARCOAL)),
        (("MAX_HEALTH", "22.0"), ("SCALE", "1.05")),
        (8, 1, 2),
        (GOLD, GOLD_DARK, GOLD_LIGHT, EMBER, CHARCOAL_DARK),
        ("gold_nugget_from_emberclad_scrap", [m("emberclad_scrap")],
         "minecraft:gold_nugget", 2, "Gold Nugget", "Goldklumpen")),
    Mob("ashen_piglin_marauder", "piglin", "Ashen Piglin Marauder", "Aschen-Piglin-Marodeur",
        "a hard-hitting marauder scouring the ashfields for loot",
        "ein hart zuschlagender Marodeur auf Beutezug durch die Aschefelder",
        ("marauder_tusk", "Marauder Tusk", "Marodeurshauer", 0.0, 1.0,
         "tusk", (GOLD_LIGHT, GOLD, GOLD_DARK)),
        (("ATTACK_DAMAGE", "6.0"),),
        (7, 1, 2),
        (ASH, ASH_DARK, ASH_LIGHT, GOLD, CHARCOAL_DARK),
        ("inferno_powder_from_marauder_tusk", [m("marauder_tusk"), m("copper_dust")],
         m("inferno_powder"), 2, "Inferno Powder", "Infernopulver")),
    Mob("soot_piglin_plunderer", "piglin", "Soot Piglin Plunderer", "Ru\u00df-Piglin-Pl\u00fcnderer",
        "a quick soot-smeared plunderer",
        "ein flinker, ru\u00dfverschmierter Pl\u00fcnderer",
        ("plunderer_soot", "Plunderer Soot", "Pl\u00fcnderer-Ru\u00df", 0.0, 2.0,
         "chunk", (ENDER_BLACK, CHARCOAL_DARK, CHARCOAL, ASH_DARK, ASH)),
        (("MOVEMENT_SPEED", "0.38"),),
        (8, 1, 3),
        (CHARCOAL, ENDER_BLACK, CHARCOAL_LIGHT, GOLD, ENDER_BLACK),
        ("gunpowder_from_plunderer_soot", [m("plunderer_soot")],
         "minecraft:gunpowder", 1, "Gunpowder", "Schwarzpulver")),
    # ---------------------------- EndermanEntity based ----------------------------
    Mob("ember_stalker", "enderman", "Ember Stalker", "Glutpirscher",
        "a tall stalker wreathed in drifting embers",
        "ein hochgewachsener Pirscher, umweht von treibender Glut",
        ("stalker_pearl", "Stalker Pearl", "Pirscherperle", 0.0, 1.0,
         "orb", (EMBER_YELLOW, EMBER_BRIGHT, EMBER, CHARCOAL_DARK)),
        (("MAX_HEALTH", "44.0"), ("SCALE", "1.05")),
        (6, 1, 2),
        (ENDER_BLACK, CHARCOAL_DARK, CHARCOAL, EMBER_BRIGHT, ENDER_BLACK),
        ("ender_pearl_from_stalker_pearl", [m("stalker_pearl")],
         "minecraft:ender_pearl", 1, "Ender Pearl", "Enderperle")),
    Mob("cinder_shade", "enderman", "Cinder Shade", "Aschenschatten",
        "a flickering shade of cinder and smoke",
        "ein flackernder Schatten aus Asche und Rauch",
        ("shade_essence", "Shade Essence", "Schattenessenz", 0.0, 2.0,
         "orb", (ENDER_LIGHT, ENDER_PURPLE, ENDER_DARK, ENDER_BLACK)),
        (("MOVEMENT_SPEED", "0.33"),),
        (6, 1, 2),
        (CHARCOAL_DARK, ENDER_BLACK, CHARCOAL_LIGHT, ENDER_PURPLE, ENDER_BLACK),
        ("black_dye_from_shade_essence", [m("shade_essence")],
         "minecraft:black_dye", 3, "Black Dye", "Schwarzer Farbstoff")),
    Mob("slagveil_lurker", "enderman", "Slagveil Lurker", "Schlackenschleier-Lauerer",
        "a lurker hidden behind a shimmering veil of slag haze",
        "ein Lauerer, verborgen hinter einem flimmernden Schleier aus Schlackendunst",
        ("lurker_veil", "Lurker Veil", "Lauererschleier", 0.0, 1.0,
         "hide", (TEAL, TEAL_DARK, TEAL_LIGHT, ASH, ASH_DARK)),
        (("MAX_HEALTH", "42.0"), ("ATTACK_DAMAGE", "8.0")),
        (5, 1, 1),
        (TEAL_DARK, ENDER_BLACK, TEAL, ASH_LIGHT, ENDER_BLACK),
        ("phantom_membrane_from_lurker_veil", [m("lurker_veil")],
         "minecraft:phantom_membrane", 1, "Phantom Membrane", "Phantomhaut")),
    Mob("ashgaze_warper", "enderman", "Ashgaze Warper", "Aschenblick-Wandler",
        "a warper whose ash-grey gaze bends space",
        "ein Wandler, dessen aschgrauer Blick den Raum kr\u00fcmmt",
        ("warper_iris", "Warper Iris", "Wandleriris", 0.0, 1.0,
         "orb", (TEAL_LIGHT, TEAL, TEAL_DARK, ENDER_BLACK)),
        (("SCALE", "0.95"), ("MOVEMENT_SPEED", "0.32")),
        (5, 1, 1),
        (ASH_DARK, ENDER_BLACK, ASH, TEAL_LIGHT, ENDER_BLACK),
        ("ender_eye_from_warper_iris", [m("warper_iris")],
         "minecraft:ender_eye", 1, "Eye of Ender", "Enderauge")),
    Mob("sootflare_haunt", "enderman", "Sootflare Haunt", "Ru\u00dfflammen-Spuk",
        "a haunt trailing sparks of burning soot",
        "ein Spuk, der Funken brennenden Ru\u00dfes hinter sich herzieht",
        ("haunt_flare", "Haunt Flare", "Spukleuchte", 0.0, 2.0,
         "shard", (EMBER_YELLOW, EMBER_HOT, EMBER_BRIGHT, CHARCOAL_DARK)),
        (("MAX_HEALTH", "38.0"), ("ATTACK_DAMAGE", "7.5")),
        (6, 1, 2),
        (ENDER_BLACK, CHARCOAL_DARK, CHARCOAL_LIGHT, EMBER_YELLOW, ENDER_BLACK),
        ("glowstone_dust_from_haunt_flare", [m("haunt_flare")],
         "minecraft:glowstone_dust", 2, "Glowstone Dust", "Leuchtsteinstaub")),
    # ------------------------- ZombifiedPiglinEntity based ------------------------
    Mob("ashen_zombie_piglin", "zpiglin", "Ashen Zombie Piglin", "Aschen-Zombiepiglin",
        "a zombified piglin bleached grey by falling ash",
        "ein zombifiziertes Piglin, grau gebleicht vom Aschefall",
        ("charred_flesh", "Charred Flesh", "Verkohltes Fleisch", 0.0, 2.0,
         "chunk", (FLESH_DARK, FLESH_RED, FLESH_LIGHT, CHARCOAL_DARK, CHARCOAL)),
        (("MAX_HEALTH", "22.0"),),
        (10, 1, 3),
        (ASH, ASH_DARK, ASH_LIGHT, FLESH_RED, CHARCOAL_DARK),
        ("cooked_porkchop_from_charred_flesh", [m("charred_flesh")],
         "minecraft:cooked_porkchop", 1, "Cooked Porkchop", "Gebratenes Schweinefleisch")),
    Mob("slagrot_piglin", "zpiglin", "Slagrot Piglin", "Schlackenmoder-Piglin",
        "a rotting piglin fused with cooling slag",
        "ein moderndes Piglin, verwachsen mit erkaltender Schlacke",
        ("slagrot_chunk", "Slagrot Chunk", "Schlackenmoder-Brocken", 0.0, 2.0,
         "chunk", (CHARCOAL_DARK, CHARCOAL, FLESH_DARK, FLESH_RED, FLESH_LIGHT)),
        (("MAX_HEALTH", "24.0"), ("MOVEMENT_SPEED", "0.25")),
        (9, 1, 3),
        (FLESH_DARK, CHARCOAL_DARK, FLESH_RED, ASH_DARK, CHARCOAL_DARK),
        ("rotten_flesh_from_slagrot_chunk", [m("slagrot_chunk")],
         "minecraft:rotten_flesh", 2, "Rotten Flesh", "Verrottetes Fleisch")),
    Mob("emberflesh_piglin", "zpiglin", "Emberflesh Piglin", "Glutfleisch-Piglin",
        "a piglin whose dead flesh smoulders from within",
        "ein Piglin, dessen totes Fleisch von innen schwelt",
        ("emberflesh_strip", "Emberflesh Strip", "Glutfleisch-Streifen", 0.0, 2.0,
         "hide", (FLESH_RED, FLESH_DARK, FLESH_LIGHT, EMBER, EMBER_BRIGHT)),
        (("ATTACK_DAMAGE", "6.0"),),
        (9, 1, 2),
        (FLESH_RED, FLESH_DARK, FLESH_LIGHT, EMBER_YELLOW, CHARCOAL_DARK),
        ("magma_cream_from_emberflesh_strip", [m("emberflesh_strip")],
         "minecraft:magma_cream", 1, "Magma Cream", "Magmacreme")),
    Mob("cinderbound_piglin", "zpiglin", "Cinderbound Piglin", "Aschengebundenes Piglin",
        "a shackled piglin bound in cinder-caked irons",
        "ein gefesseltes Piglin in ascheverkrusteten Eisen",
        ("cinderbound_shackle", "Cinderbound Shackle", "Aschenfessel", 0.0, 1.0,
         "orb", (ASH_LIGHT, ASH, ASH_DARK, CHARCOAL_DARK)),
        (("MAX_HEALTH", "26.0"), ("KNOCKBACK_RESISTANCE", "0.4")),
        (7, 1, 2),
        (CHARCOAL_LIGHT, CHARCOAL_DARK, ASH, ASH_LIGHT, CHARCOAL_DARK),
        ("iron_nugget_from_cinderbound_shackle", [m("cinderbound_shackle")],
         "minecraft:iron_nugget", 4, "Iron Nugget", "Eisenklumpen")),
    Mob("sootrot_marauder", "zpiglin", "Sootrot Marauder", "Ru\u00dfmoder-Marodeur",
        "a hulking undead marauder caked in wet soot",
        "ein massiger untoter Marodeur, verklebt mit nassem Ru\u00df",
        ("sootrot_hide", "Sootrot Hide", "Ru\u00dfmoder-Haut", 0.0, 2.0,
         "hide", (CHARCOAL, CHARCOAL_DARK, CHARCOAL_LIGHT, ASH_DARK, ASH)),
        (("MAX_HEALTH", "24.0"), ("SCALE", "1.1")),
        (8, 1, 2),
        (ENDER_BLACK, CHARCOAL_DARK, CHARCOAL_LIGHT, FLESH_RED, ENDER_BLACK),
        ("brown_dye_from_sootrot_hide", [m("sootrot_hide"), m("sootrot_hide")],
         "minecraft:brown_dye", 2, "Brown Dye", "Brauner Farbstoff")),
]

# ---------------------------------------------------------------------------
# Vanilla base facts (all javap/bytecode-verified against the 1.21.9 Yarn jars).
#   builder      EntityType.Builder chain copied 1:1 from the vanilla registration
#   attr_factory the DefaultAttributeRegistry supplier for the base EntityType
#   predicate    which spawn-restriction predicate shape the base uses (see render)
#   vanilla_id   for the copied-from comment in the generated Java
# ---------------------------------------------------------------------------

Base = namedtuple("Base", "cls ctor_bound builder attr_factory predicate renderer comment")

BASES = {
    "wskel": Base(
        "WitherSkeletonEntity", "WitherSkeletonEntity",
        [".makeFireImmune()", ".allowSpawningInside(Blocks.WITHER_ROSE)",
         ".dimensions(0.7f, 2.4f)", ".eyeHeight(2.1f)", ".vehicleAttachment(-0.875f)",
         ".maxTrackingRange(8)", ".notAllowedInPeaceful()"],
        "AbstractSkeletonEntity.createAbstractSkeletonAttributes()",
        "dark", "wskel",
        "wither_skeleton = makeFireImmune().allowSpawningInside(Blocks.WITHER_ROSE)"
        ".dimensions(0.7f, 2.4f).eyeHeight(2.1f).vehicleAttachment(-0.875f)"
        ".maxTrackingRange(8).notAllowedInPeaceful()"),
    "hoglin": Base(
        "HoglinEntity", "HoglinEntity",
        [".dimensions(1.3964844f, 1.4f)", ".passengerAttachments(1.49375f)",
         ".maxTrackingRange(8)"],
        "HoglinEntity.createHoglinAttributes()",
        "netherwart", "hoglin",
        "hoglin = dimensions(1.3964844f, 1.4f).passengerAttachments(1.49375f)"
        ".maxTrackingRange(8)"),
    "piglin": Base(
        "PiglinEntity", "AbstractPiglinEntity",
        [".dimensions(0.6f, 1.95f)", ".eyeHeight(1.79f)", ".passengerAttachments(2.0125f)",
         ".vehicleAttachment(-0.7f)", ".maxTrackingRange(8)"],
        "PiglinEntity.createPiglinAttributes()",
        "netherwart", "piglin",
        "piglin = dimensions(0.6f, 1.95f).eyeHeight(1.79f).passengerAttachments(2.0125f)"
        ".vehicleAttachment(-0.7f).maxTrackingRange(8)"),
    "enderman": Base(
        "EndermanEntity", "EndermanEntity",
        [".dimensions(0.6f, 2.9f)", ".eyeHeight(2.55f)", ".passengerAttachments(2.80625f)",
         ".maxTrackingRange(8)", ".notAllowedInPeaceful()"],
        "EndermanEntity.createEndermanAttributes()",
        "dark", "enderman",
        "enderman = dimensions(0.6f, 2.9f).eyeHeight(2.55f).passengerAttachments(2.80625f)"
        ".maxTrackingRange(8).notAllowedInPeaceful()"),
    "zpiglin": Base(
        "ZombifiedPiglinEntity", "ZombifiedPiglinEntity",
        [".makeFireImmune()", ".dimensions(0.6f, 1.95f)", ".eyeHeight(1.79f)",
         ".passengerAttachments(2.0f)", ".vehicleAttachment(-0.7f)",
         ".maxTrackingRange(8)", ".notAllowedInPeaceful()"],
        "ZombifiedPiglinEntity.createZombifiedPiglinAttributes()",
        "peaceful_netherwart", "zpiglin",
        "zombified_piglin = makeFireImmune().dimensions(0.6f, 1.95f).eyeHeight(1.79f)"
        ".passengerAttachments(2.0f).vehicleAttachment(-0.7f).maxTrackingRange(8)"
        ".notAllowedInPeaceful()"),
}

# Recipe-text display names for non-slagfiends ingredient ids.
NAME_EN = {m("copper_dust"): "Copper Dust"}
NAME_DE = {m("copper_dust"): "Kupferstaub"}


def class_of(mid: str) -> str:
    return "".join(p.capitalize() for p in mid.split("_")) + "Entity"


def field_of(s: str) -> str:
    return s.upper()


assert len(MOBS) == 25
assert len({mob.mid for mob in MOBS}) == 25, "duplicate mob ids"
assert len({mob.drop[0] for mob in MOBS}) == 25, "duplicate drop ids"
assert len({mob.recipe[0] for mob in MOBS}) == 25, "duplicate recipe names"
for mob in MOBS:
    assert m(mob.drop[0]) in mob.recipe[1], f"{mob.recipe[0]}: recipe must include an own id"

EGG_IDS = [f"{mob.mid}_spawn_egg" for mob in MOBS]
DROP_IDS = [mob.drop[0] for mob in MOBS]
ITEM_IDS = EGG_IDS + DROP_IDS


# ---------------------------------------------------------------------------
# Textures (16x16, Pillow, seeded via genlib.rng_for -> deterministic).
# ---------------------------------------------------------------------------

def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def tex_spawn_egg(rng: Random, base, dark, light, spots, outline) -> Image.Image:
    """Classic spawn-egg silhouette with seeded speckles (infernomobs painter)."""
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
            color = light
        elif x >= 10 or y >= 12:
            color = dark
        else:
            color = base
        px(img, x, y, color)
    interior = [(x, y) for x, y, edge in cells if not edge]
    for x, y in rng.sample(interior, 12):
        px(img, x, y, spots)
    return img


def tex_shard(rng: Random, hot, mid, body, edge) -> Image.Image:
    """Jagged shard: bright heart, toned body, dark chipped edge (wraith_ember shape)."""
    img = blank()
    rows = {3: (7, 9), 4: (6, 10), 5: (5, 11), 6: (4, 11), 7: (4, 12), 8: (3, 12),
            9: (4, 12), 10: (4, 11), 11: (5, 11), 12: (6, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            d = math.hypot(x - 7.5, y - 7.5)
            if x in (x0, x1) or y in (3, 12):
                color = edge
            elif d < 2.2:
                color = hot
            elif d < 3.6:
                color = mid if (x + y) % 3 else hot
            else:
                color = body
            px(img, x, y, color)
    for x, y in [(5, 5), (10, 10), (9, 4), (5, 11)]:
        if rng.random() < 0.9:
            px(img, x, y, edge)
    for x, y in [(7, 2), (12, 7), (3, 9), (8, 13)]:
        px(img, x, y, body, 100)
    return img


def tex_tusk(rng: Random, light, shade, dark) -> Image.Image:
    """Curved tusk: broad root top-left tapering to a point (crawler_fang shape)."""
    img = blank()
    for y in (2, 3, 4):
        for x in range(3, 9):
            color = dark if y == 2 or x == 3 else shade
            px(img, x, y, color)
    body = {5: (4, 9), 6: (5, 9), 7: (6, 10), 8: (7, 10), 9: (8, 11), 10: (9, 11),
            11: (10, 12), 12: (11, 12)}
    for y, (x0, x1) in body.items():
        for x in range(x0, x1 + 1):
            if x == x0:
                color = light
            elif x == x1:
                color = dark
            else:
                color = shade
            px(img, x, y, color)
    px(img, 12, 13, dark)
    px(img, 5, 3, light)
    return img


def tex_chunk(rng: Random, rim, crust, crust_light, core, core_hot) -> Image.Image:
    """Round chunk: dark crust cracked open over a contrasting core (slagling_core shape)."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 7.5)
            if d <= 5.6:
                if d > 4.7:
                    color = rim
                elif d > 3.4:
                    color = crust if (x * 3 + y * 5) % 7 else crust_light
                else:
                    color = core if (x + y) % 2 else core_hot
                px(img, x, y, color)
    for x, y in [(4, 6), (5, 5), (10, 5), (11, 6), (4, 10), (11, 10), (7, 3), (8, 12)]:
        px(img, x, y, core_hot)
    px(img, 7, 7, core)
    px(img, 8, 8, core)
    return img


def tex_hide(rng: Random, main, main_dark, main_light, rim, rim_dark) -> Image.Image:
    """Domed hide/plate segment with banding and a contrasting rim (strider_shell shape)."""
    img = blank()
    half = {4: 2.4, 5: 3.4, 6: 4.1, 7: 4.6, 8: 5.0, 9: 5.2, 10: 5.4, 11: 5.4}
    for y, h in half.items():
        x0 = int(math.ceil(7.5 - h))
        x1 = int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y == 4:
                color = main_dark
            elif y in (6, 9):
                color = main_dark
            elif x <= 5 and y <= 8:
                color = main_light
            else:
                color = main
            px(img, x, y, color)
    for x in range(2, 14):
        px(img, x, 12, rim)
        px(img, x, 13, rim_dark)
    px(img, 5, 5, main_light)
    return img


def tex_orb(rng: Random, sheen, core, dark, outline) -> Image.Image:
    """Glossy orb: shaded sphere with a top-left sheen and a dark outline."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 7.5)
            if d <= 5.4:
                if d > 4.6:
                    color = outline
                elif x <= 6 and y <= 6:
                    color = sheen
                elif d > 3.2:
                    color = dark if (x + 2 * y) % 5 == 0 else core
                else:
                    color = core
                px(img, x, y, color)
    px(img, 5, 4, sheen)
    px(img, 4, 5, sheen)
    for x, y in [(10, 10), (11, 9), (9, 11)]:
        px(img, x, y, dark)
    return img


DROP_STYLES = {"shard": tex_shard, "tusk": tex_tusk, "chunk": tex_chunk,
               "hide": tex_hide, "orb": tex_orb}


def emit_textures() -> None:
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for mob in MOBS:
        egg_name = f"{mob.mid}_spawn_egg"
        img = tex_spawn_egg(rng_for(f"slagfiends:{egg_name}"), *mob.egg)
        img.save(tex_dir / f"{egg_name}.png")
        did, style, palette = mob.drop[0], mob.drop[5], mob.drop[6]
        img = DROP_STYLES[style](rng_for(f"slagfiends:{did}"), *palette)
        img.save(tex_dir / f"{did}.png")


# ---------------------------------------------------------------------------
# Item defs/models, entity loot tables (infernomobs schema), recipes, lang.
# ---------------------------------------------------------------------------

def emit_item_assets() -> None:
    for item_id in ITEM_IDS:
        genlib.emit_item_def(ASSETS, item_id)
        genlib.emit_item_model(ASSETS, item_id)


def emit_loot_tables() -> None:
    for mob in MOBS:
        did, cmin, cmax = mob.drop[0], mob.drop[3], mob.drop[4]
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
                                        "max": cmax,
                                        "min": cmin,
                                    },
                                    "function": "minecraft:set_count",
                                }
                            ],
                            "name": m(did),
                        }
                    ],
                    "rolls": 1.0,
                }
            ],
            "random_sequence": f"{NS}:entities/{mob.mid}",
        })


def emit_recipes() -> int:
    count = 0
    for mob in MOBS:
        rname, ingredients, result_id, rcount, _, _ = mob.recipe
        genlib.emit_shapeless(RECIPES, rname, ingredients, result_id, rcount,
                              category="misc")
        count += 1
    return count


def build_lang():
    en, de = {}, {}
    for mob in MOBS:
        did, den, dde = mob.drop[0], mob.drop[1], mob.drop[2]
        en[f"entity.{NS}.{mob.mid}"] = mob.en
        de[f"entity.{NS}.{mob.mid}"] = mob.de
        en[f"item.{NS}.{mob.mid}_spawn_egg"] = f"{mob.en} Spawn Egg"
        de[f"item.{NS}.{mob.mid}_spawn_egg"] = f"{mob.de}-Spawn-Ei"
        en[f"item.{NS}.{did}"] = den
        de[f"item.{NS}.{did}"] = dde
    return en, de


# ---------------------------------------------------------------------------
# Handbook entries: one "mobs" page per mob + one "items" page per drop recipe.
# ---------------------------------------------------------------------------

def ingredient_name(ing: str, mob: Mob, lang: str) -> str:
    if ing == m(mob.drop[0]):
        return mob.drop[1] if lang == "en" else mob.drop[2]
    if lang == "en":
        return NAME_EN.get(ing, ing.split(":")[1].replace("_", " ").title())
    return NAME_DE.get(ing, ing.split(":")[1].replace("_", " ").title())


def build_handbook():
    entries = []
    for mob in MOBS:
        entries.append((
            "mobs", mob.mid, m(f"{mob.mid}_spawn_egg"), None, None, None, 0,
            f"{mob.en} - {mob.flavor_en} haunting the Cinder Wastes, Ember Grove and "
            f"Slag Sea. Hostile. Drops {mob.drop[1]}.",
            f"{mob.de} - {mob.flavor_de}, heimisch in der Aschen\u00f6de, im Gluthain "
            f"und im Schlackenmeer. Feindselig. L\u00e4sst {mob.drop[2]} fallen."))
    for mob in MOBS:
        rname, ingredients, result_id, rcount, result_en, result_de = mob.recipe
        grid = list(ingredients) + [""] * (9 - len(ingredients))
        inputs_en = " + ".join(ingredient_name(i, mob, "en") for i in ingredients)
        inputs_de = " + ".join(ingredient_name(i, mob, "de") for i in ingredients)
        entries.append((
            "items", rname, m(mob.drop[0]), f"slagfiends/{rname}", grid, result_id, rcount,
            f"Craft {rcount}x {result_en} from {inputs_en} at a crafting table.",
            f"Stellt {rcount}x {result_de} aus {inputs_de} an der Werkbank her."))
    return entries


# ---------------------------------------------------------------------------
# Java codegen: SlagFiendsFeature.java, 25 entity subclasses,
# SlagFiendsFeatureClient.java, SlagFiendsHandbook.java (via genlib).
# ---------------------------------------------------------------------------

PKG = "net.sonic0810.copperinferno.feature.slagfiends"


def render_entity_java(mob: Mob) -> str:
    base = BASES[mob.base]
    cls = class_of(mob.mid)
    imports = ["net.minecraft.entity.EntityType"]
    if mob.base == "hoglin":
        imports += ["net.minecraft.entity.SpawnReason",
                    "net.minecraft.entity.mob.HoglinEntity",
                    "net.minecraft.entity.passive.PassiveEntity",
                    "net.minecraft.server.world.ServerWorld"]
    elif mob.base == "piglin":
        imports += ["net.minecraft.entity.mob.AbstractPiglinEntity",
                    "net.minecraft.entity.mob.PiglinEntity"]
    else:
        imports.append(f"net.minecraft.entity.mob.{base.cls}")
    imports.append("net.minecraft.world.World")

    doc = [f"{mob.en} - {mob.flavor_en}, native to the Inferno dimension. Behavior is pure",
           f"vanilla {base.cls}; the stat distinction comes from the tuned default",
           "attributes registered in {@link SlagFiendsFeature}. Drops",
           f"{mob.drop[1]} ({{@code loot_table/entities/{mob.mid}.json}}).",
           "",
           "<p>Generated by {@code devtools/gen/slagfiends_gen.py}; do not edit by hand."]

    out = [f"package {PKG};", ""]
    out += [f"import {imp};" for imp in sorted(imports)]
    out += ["", "/**"] + [f" * {line}".rstrip() for line in doc] + [" */"]
    out.append(f"public class {cls} extends {base.cls} {{")
    out.append(f"\tpublic {cls}(EntityType<? extends {base.ctor_bound}> type, World world) {{")
    out.append("\t\tsuper(type, world);")
    out.append("\t}")
    if mob.base == "hoglin":
        out += ["",
                "\t/**",
                "\t * Vanilla {@code HoglinEntity.canConvert()} (public, javap-verified) gates the zoglin",
                "\t * conversion on {@code !world.getDimension().piglinSafe()}; slag-fiend hoglins never",
                "\t * convert, in the Inferno or anywhere else.",
                "\t */",
                "\t@Override",
                "\tpublic boolean canConvert() {",
                "\t\treturn false;",
                "\t}",
                "",
                "\t/**",
                "\t * Vanilla {@code HoglinEntity.createChild} hard-codes {@code EntityType.HOGLIN}",
                "\t * (bytecode-verified: create -> setPersistent -> return), so bred " + mob.en + "s",
                "\t * would produce vanilla hoglins. Covariant override, same create/setPersistent flow.",
                "\t */",
                "\t@Override",
                f"\tpublic {cls} createChild(ServerWorld world, PassiveEntity entity) {{",
                f"\t\t{cls} child = SlagFiendsFeature.{field_of(mob.mid)}.create(world, SpawnReason.BREEDING);",
                "\t\tif (child != null) {",
                "\t\t\tchild.setPersistent();",
                "\t\t}",
                "\t\treturn child;",
                "\t}"]
    elif mob.base == "piglin":
        out += ["",
                "\t/**",
                "\t * Vanilla {@code AbstractPiglinEntity.mobTick} counts up {@code timeInOverworld} while",
                "\t * {@code shouldZombify()} (public, javap-verified) is true and converts to a vanilla",
                "\t * zombified piglin after 300 ticks; slag-fiend piglins never zombify.",
                "\t */",
                "\t@Override",
                "\tpublic boolean shouldZombify() {",
                "\t\treturn false;",
                "\t}"]
    out.append("}")
    return "\n".join(out) + "\n"


PREDICATE_SNIPPETS = {
    # Vanilla wither_skeleton + enderman entry (SpawnRestriction bytecode:
    # HostileEntity::canSpawnInDark, typed to `? extends HostileEntity` -> reusable).
    "dark": ["\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);"],
    # Vanilla HoglinEntity.canSpawn / PiglinEntity.canSpawn (both typed to the vanilla
    # EntityType, reimplemented 1:1 from bytecode: no nether wart block below).
    "netherwart": [
        "\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES,",
        "\t\t\t\t(type, world, reason, pos, random) -> !world.getBlockState(pos.down()).isOf(Blocks.NETHER_WART_BLOCK));"],
    # Vanilla ZombifiedPiglinEntity.canSpawn (typed, reimplemented 1:1 from bytecode:
    # difficulty != PEACEFUL and no nether wart block below).
    "peaceful_netherwart": [
        "\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES,",
        "\t\t\t\t(type, world, reason, pos, random) -> world.getDifficulty() != Difficulty.PEACEFUL",
        "\t\t\t\t\t\t&& !world.getBlockState(pos.down()).isOf(Blocks.NETHER_WART_BLOCK));"],
}


def render_feature_java() -> str:
    imports = sorted([
        "java.util.function.Predicate",
        "net.fabricmc.fabric.api.biome.v1.BiomeModifications",
        "net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext",
        "net.fabricmc.fabric.api.biome.v1.BiomeSelectors",
        "net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents",
        "net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry",
        "net.minecraft.block.Blocks",
        "net.minecraft.entity.EntityType",
        "net.minecraft.entity.SpawnGroup",
        "net.minecraft.entity.SpawnLocationTypes",
        "net.minecraft.entity.SpawnRestriction",
        "net.minecraft.entity.attribute.EntityAttributes",
        "net.minecraft.entity.mob.AbstractSkeletonEntity",
        "net.minecraft.entity.mob.EndermanEntity",
        "net.minecraft.entity.mob.HoglinEntity",
        "net.minecraft.entity.mob.HostileEntity",
        "net.minecraft.entity.mob.PiglinEntity",
        "net.minecraft.entity.mob.ZombifiedPiglinEntity",
        "net.minecraft.item.Item",
        "net.minecraft.item.SpawnEggItem",
        "net.minecraft.registry.RegistryKey",
        "net.minecraft.registry.RegistryKeys",
        "net.minecraft.world.Difficulty",
        "net.minecraft.world.Heightmap",
        "net.sonic0810.copperinferno.CopperInferno",
        "net.sonic0810.copperinferno.core.ModCreativeTab",
        "net.sonic0810.copperinferno.core.ModEntities",
        "net.sonic0810.copperinferno.core.ModItems",
    ])
    doc = [
        "Slag Fiends: 25 hostile mobs native to the Inferno dimension, five thin subclasses per",
        "vanilla base (wither skeleton, hoglin, piglin, enderman, zombified piglin), each reusing",
        "the vanilla renderer (see {@code SlagFiendsFeatureClient}). EntityType builder chains,",
        "default attribute suppliers and spawn predicates are copied from the vanilla",
        "registrations (javap/bytecode-verified; see the comments below). Spawn eggs, mob-drop",
        "items, natural spawns in the haunted Inferno biomes (cinder_wastes, ember_grove,",
        "slag_sea, molten_delta, verdigris_jungle), drop recipes and handbook pages are",
        "all registered here.",
        "",
        "<p>Generated by {@code devtools/gen/slagfiends_gen.py}; do not edit by hand.",
    ]
    out = [f"package {PKG};", ""]
    out += [f"import {imp};" for imp in imports]
    out += ["", "/**"] + [f" * {line}".rstrip() for line in doc] + [" */"]
    out += ["public final class SlagFiendsFeature {",
            "\tprivate SlagFiendsFeature() {",
            "\t}", ""]

    for mob in MOBS:
        out.append(f"\tpublic static EntityType<{class_of(mob.mid)}> {field_of(mob.mid)};")
    out.append("")
    for mob in MOBS:
        out.append(f"\tpublic static Item {field_of(mob.mid)}_SPAWN_EGG;")
    out.append("")
    for mob in MOBS:
        out.append(f"\tpublic static Item {field_of(mob.drop[0])};")
    out.append("")

    out += ["\tpublic static void init() {",
            "\t\tregisterEntityTypes();",
            "\t\tregisterAttributes();",
            "\t\tregisterItems();",
            "\t\tregisterSpawning();",
            "\t\tSlagFiendsHandbook.register();",
            "\t}", ""]

    # registerEntityTypes: builder chains copied 1:1 from the vanilla EntityType
    # registrations (one comment per base family).
    out.append("\tprivate static void registerEntityTypes() {")
    out.append("\t\t// Builder chains copied 1:1 from the vanilla EntityType registrations")
    out.append("\t\t// (1.21.9 bytecode):")
    seen = set()
    first = True
    for mob in MOBS:
        base = BASES[mob.base]
        if not first:
            out.append("")
        first = False
        if mob.base not in seen:
            seen.add(mob.base)
            out.append(f"\t\t// {base.comment}")
        out.append(f"\t\t{field_of(mob.mid)} = ModEntities.register(\"{mob.mid}\",")
        out.append(f"\t\t\t\tEntityType.Builder.create({class_of(mob.mid)}::new, SpawnGroup.MONSTER)")
        for i, link in enumerate(base.builder):
            suffix = ");" if i == len(base.builder) - 1 else ""
            out.append(f"\t\t\t\t\t\t{link}{suffix}")
    out += ["\t}", ""]

    # registerAttributes: vanilla suppliers (DefaultAttributeRegistry, bytecode-verified)
    # plus per-mob tuning; Builder.add(...) overrides any base value.
    out.append("\tprivate static void registerAttributes() {")
    out.append("\t\t// Base suppliers mirror DefaultAttributeRegistry (bytecode-verified);")
    out.append("\t\t// DefaultAttributeContainer.Builder.add(...) overrides any base value.")
    for mob in MOBS:
        base = BASES[mob.base]
        line = f"\t\tFabricDefaultAttributeRegistry.register({field_of(mob.mid)}, {base.attr_factory}"
        if not mob.attrs:
            out.append(line + ");")
            continue
        out.append(line)
        for i, (attr, val) in enumerate(mob.attrs):
            suffix = ");" if i == len(mob.attrs) - 1 else ""
            out.append(f"\t\t\t\t.add(EntityAttributes.{attr}, {val}){suffix}")
    out += ["\t}", ""]

    # registerItems: spawn eggs (Item.Settings.spawnEgg stores the entity type, same
    # proven pattern as infernomobs), drops, one MAIN_KEY creative-tab callback.
    out.append("\tprivate static void registerItems() {")
    for mob in MOBS:
        out.append(f"\t\t{field_of(mob.mid)}_SPAWN_EGG = ModItems.register(\"{mob.mid}_spawn_egg\", SpawnEggItem::new,")
        out.append(f"\t\t\t\tnew Item.Settings().spawnEgg({field_of(mob.mid)}));")
    out.append("")
    for mob in MOBS:
        out.append(f"\t\t{field_of(mob.drop[0])} = ModItems.register(\"{mob.drop[0]}\", Item::new, new Item.Settings());")
    out.append("")
    out.append("\t\tItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MOBS_KEY).register(entries -> {")
    out.append("\t\t\t// Spawn eggs together, then the mob drops.")
    for mob in MOBS:
        out.append(f"\t\t\tentries.add({field_of(mob.mid)}_SPAWN_EGG);")
    for mob in MOBS:
        out.append(f"\t\t\tentries.add({field_of(mob.drop[0])});")
    out += ["\t\t});", "\t}", ""]

    # registerSpawning: SpawnRestriction entries mirror the vanilla ones per base
    # (SpawnRestriction bytecode); natural spawns go to the SPAWN_BIOMES Inferno biomes.
    out.append("\tprivate static void registerSpawning() {")
    out.append("\t\t// SpawnRestriction.register is access-widened by Fabric's transitive access")
    out.append("\t\t// wideners. Per-base predicates mirror the vanilla SpawnRestriction entries")
    out.append("\t\t// (bytecode): wither skeleton + enderman use HostileEntity::canSpawnInDark")
    out.append("\t\t// (typed to `? extends HostileEntity`, so reusable for subclasses); hoglin/")
    out.append("\t\t// piglin/zombified piglin canSpawn are typed to the vanilla EntityTypes and")
    out.append("\t\t// are reimplemented 1:1 inline (no-nether-wart-below, plus the zombified")
    out.append("\t\t// piglin difficulty gate). All spawn ON_GROUND + MOTION_BLOCKING_NO_LEAVES.")
    for mob in MOBS:
        base = BASES[mob.base]
        out.append(f"\t\tSpawnRestriction.register({field_of(mob.mid)}, SpawnLocationTypes.ON_GROUND,")
        out += PREDICATE_SNIPPETS[base.predicate]
    out.append("")
    out.append("\t\t// Natural spawns in the haunted Inferno biomes (SPAWN_BIOMES in the generator;")
    out.append("\t\t// biome JSONs are owned by infernodim/infernodim2 and includeByKey simply")
    out.append("\t\t// matches nothing until loaded).")
    out.append("\t\tPredicate<BiomeSelectionContext> infernoBiomes = BiomeSelectors.includeByKey(")
    for i, biome in enumerate(SPAWN_BIOMES):
        suffix = ");" if i == len(SPAWN_BIOMES) - 1 else ","
        out.append(f"\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"{biome}\")){suffix}")
    for mob in MOBS:
        weight, gmin, gmax = mob.spawn
        out.append(f"\t\tBiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, "
                   f"{field_of(mob.mid)}, {weight}, {gmin}, {gmax});")
    out += ["\t}", "}"]
    return "\n".join(out) + "\n"


RENDERER_SNIPPETS = {
    "wskel": ["\t\tEntityRendererFactories.register(SlagFiendsFeature.{F}, WitherSkeletonEntityRenderer::new);"],
    "hoglin": ["\t\tEntityRendererFactories.register(SlagFiendsFeature.{F}, HoglinEntityRenderer::new);"],
    "enderman": ["\t\tEntityRendererFactories.register(SlagFiendsFeature.{F}, EndermanEntityRenderer::new);"],
    "piglin": [
        "\t\tEntityRendererFactories.<AbstractPiglinEntity>register(SlagFiendsFeature.{F},",
        "\t\t\t\tcontext -> new PiglinEntityRenderer(context, EntityModelLayers.PIGLIN,",
        "\t\t\t\t\t\tEntityModelLayers.PIGLIN_BABY, EntityModelLayers.PIGLIN_EQUIPMENT,",
        "\t\t\t\t\t\tEntityModelLayers.PIGLIN_BABY_EQUIPMENT));"],
    "zpiglin": [
        "\t\tEntityRendererFactories.<ZombifiedPiglinEntity>register(SlagFiendsFeature.{F},",
        "\t\t\t\tcontext -> new ZombifiedPiglinEntityRenderer(context, EntityModelLayers.ZOMBIFIED_PIGLIN,",
        "\t\t\t\t\t\tEntityModelLayers.ZOMBIFIED_PIGLIN_BABY, EntityModelLayers.ZOMBIFIED_PIGLIN_EQUIPMENT,",
        "\t\t\t\t\t\tEntityModelLayers.ZOMBIFIED_PIGLIN_BABY_EQUIPMENT));"],
}


def render_client_java() -> str:
    doc = [
        "Client-side setup for the Slag Fiends: every type reuses its vanilla renderer.",
        "WitherSkeleton/Hoglin/Enderman renderer ctors are Context-only (javap-verified);",
        "PiglinEntityRenderer / ZombifiedPiglinEntityRenderer take (Context, EntityModelLayer,",
        "EntityModelLayer, EquipmentModelData, EquipmentModelData) and receive the same",
        "EntityModelLayers constants the vanilla factories pass (bytecode-verified). The",
        "explicit type witnesses pin T to the renderer's entity type bound (the implicit",
        "lambda would otherwise infer T as the subclass and fail).",
        "",
        "<p>Generated by {@code devtools/gen/slagfiends_gen.py}; do not edit by hand.",
    ]
    imports = sorted([
        "net.minecraft.client.render.entity.EndermanEntityRenderer",
        "net.minecraft.client.render.entity.EntityRendererFactories",
        "net.minecraft.client.render.entity.HoglinEntityRenderer",
        "net.minecraft.client.render.entity.PiglinEntityRenderer",
        "net.minecraft.client.render.entity.WitherSkeletonEntityRenderer",
        "net.minecraft.client.render.entity.ZombifiedPiglinEntityRenderer",
        "net.minecraft.client.render.entity.model.EntityModelLayers",
        "net.minecraft.entity.mob.AbstractPiglinEntity",
        "net.minecraft.entity.mob.ZombifiedPiglinEntity",
        f"{PKG}.SlagFiendsFeature",
    ])
    out = [f"package {PKG}.client;", ""]
    out += [f"import {imp};" for imp in imports]
    out += ["", "/**"] + [f" * {line}".rstrip() for line in doc] + [" */"]
    out += ["public final class SlagFiendsFeatureClient {",
            "\tprivate SlagFiendsFeatureClient() {",
            "\t}", "",
            "\tpublic static void initClient() {"]
    for mob in MOBS:
        for line in RENDERER_SNIPPETS[mob.base]:
            out.append(line.replace("{F}", field_of(mob.mid)))
    out += ["\t}", "}"]
    return "\n".join(out) + "\n"


def emit_java(handbook_entries) -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)
    CLIENT_DIR.mkdir(parents=True, exist_ok=True)
    (FEATURE_DIR / "SlagFiendsFeature.java").write_text(render_feature_java(),
                                                        encoding="utf-8")
    for mob in MOBS:
        (FEATURE_DIR / f"{class_of(mob.mid)}.java").write_text(render_entity_java(mob),
                                                               encoding="utf-8")
    (CLIENT_DIR / "SlagFiendsFeatureClient.java").write_text(render_client_java(),
                                                             encoding="utf-8")
    handbook_doc = [
        "Handbook pages for the Slag Fiends: one \"mobs\" overview per mob plus one recipe",
        "page for every JSON under {@code data/copper_inferno/recipe/slagfiends/}. Entry",
        "texts and grids mirror the recipe JSONs emitted by",
        "{@code devtools/gen/slagfiends_gen.py}; {@code devtools/check_handbook.py} parses",
        "the inline {@code new HandbookEntry(...)} literals positionally, so keep them",
        "inline.",
    ]
    handbook_src = genlib.java_handbook_class("slagfiends", "SlagFiendsHandbook",
                                              handbook_doc, handbook_entries)
    (FEATURE_DIR / "SlagFiendsHandbook.java").write_text(handbook_src, encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/slagfiends.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks(recipe_count: int, handbook_count: int) -> None:
    lines = [
        "# slagfiends feature hooks (format: devtools/hooks/README.md)", "",
        "[init]",
        "import net.sonic0810.copperinferno.feature.slagfiends.SlagFiendsFeature;",
        "\t\tSlagFiendsFeature.init();", "",
        "[client-init]",
        "import net.sonic0810.copperinferno.feature.slagfiends.client.SlagFiendsFeatureClient;",
        "\t\tSlagFiendsFeatureClient.initClient();", "",
        "[recipe-dir]",
        "slagfiends", "",
        "[counts]",
        f"mobs: {len(MOBS)}",
        f"items: {len(ITEM_IDS)}",
        f"recipes: {recipe_count}",
        f"handbook-entries: {handbook_count}", "",
    ]
    path = ROOT / "devtools" / "hooks" / "slagfiends.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_textures()
    emit_item_assets()
    emit_loot_tables()
    recipe_count = emit_recipes()

    lang_en, lang_de = build_lang()
    genlib.lang_fragments(ASSETS, "slagfiends", lang_en, lang_de)

    handbook = build_handbook()
    emit_java(handbook)
    emit_hooks(recipe_count, len(handbook))

    assert len(MOBS) == 25, f"expected 25 mobs, got {len(MOBS)}"
    assert len(EGG_IDS) == 25 and len(set(EGG_IDS)) == 25
    assert len(ITEM_IDS) == 50 and len(set(ITEM_IDS)) == 50, "duplicate item ids"
    assert sorted(lang_en) == sorted(lang_de), "EN/DE lang key sets differ"
    assert len(lang_en) == 75, f"expected 75 lang keys, got {len(lang_en)}"
    assert recipe_count == 25, f"expected 25 recipes, got {recipe_count}"
    assert len(handbook) == 50, f"expected 50 handbook entries, got {len(handbook)}"
    print(f"slagfiends_gen: assets for {len(MOBS)} mobs / {len(ITEM_IDS)} items generated "
          f"({recipe_count} recipes, {len(handbook)} handbook entries).")


if __name__ == "__main__":
    main()
