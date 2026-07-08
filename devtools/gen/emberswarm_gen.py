#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "emberswarm" feature (25 hostile mobs).

The Ember Swarm: 25 swarm-sized hostile mobs native to the Inferno dimension, five each
on the vanilla Blaze / MagmaCube / Silverfish / Vex / Endermite bases. Every mob is a
thin subclass of its vanilla base reusing the vanilla renderer; EntityType dimensions,
eye heights, attachments and spawn predicates are copied 1:1 from the vanilla 1.21.9
EntityType / SpawnRestriction registrations (verified via javap bytecode; see the
comments in the generated EmberSwarmFeature.java).

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via genlib.rng_for). Emits by DEFAULT (no flags; unlike
infernomobs_gen.py nothing pre-exists in src, so the generated JSON IS authoritative,
same as moltenmetal_gen.py):
  - 16x16 RGBA item textures  assets/copper_inferno/textures/item/<id>.png (Pillow)
  - item model-definitions    assets/copper_inferno/items/<id>.json
  - item models               assets/copper_inferno/models/item/<id>.json
  - entity loot tables        data/copper_inferno/loot_table/entities/<mob>.json
                              (dr_pepper_golem schema incl. "random_sequence")
  - drop recipes              data/copper_inferno/recipe/emberswarm/*.json
                              (each ingredient list contains one own id -> collision-free)
  - lang fragments            assets/copper_inferno/lang/fragments/emberswarm.json (EN)
                              assets/copper_inferno/lang/fragments_de/emberswarm.json (DE)
  - generated Java            src/main/java/.../feature/emberswarm/EmberSwarmFeature.java
                              + the 25 entity subclasses + EmberSwarmHandbook.java
                              src/client/java/.../feature/emberswarm/client/
                              EmberSwarmFeatureClient.java (literal ids only, so
                              devtools/audit_assets.py check (f) and
                              devtools/check_handbook.py can parse them)
  - hook file                 devtools/hooks/emberswarm.txt (devtools/hooks/README.md)

Items: 25 spawn eggs (<mob>_spawn_egg) + 25 mob drops. Recipes reference ONLY vanilla
ids and this feature's own ids.
"""

import math
import sys
from collections import namedtuple
from pathlib import Path

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, rng_for, write_json

RECIPES = DATA / "recipe" / "emberswarm"
FEATURE_DIR = ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "emberswarm"
CLIENT_DIR = ROOT / "src" / "client" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "emberswarm" / "client"

# ---------------------------------------------------------------------------
# The 25 mobs. base selects the vanilla superclass (and with it the EntityType
# builder chain, default attributes, spawn predicate and renderer — all verified via
# javap against the 1.21.9 yarn-mapped jars):
#   blaze      -> BlazeEntity      (fire immune, 0.6x1.8, range 8, not-peaceful)
#   cube       -> MagmaCubeEntity  (fire immune, 0.52x0.52, eye 0.325, spawnBox 4.0)
#   silverfish -> SilverfishEntity (0.4x0.3, eye 0.13, passenger 0.2375)
#   vex        -> VexEntity        (fire immune, 0.4x0.8, eye 0.51875, passenger
#                                   0.7375, vehicle 0.04; flies, UNRESTRICTED spawn)
#   mite       -> EndermiteEntity  (0.4x0.3, eye 0.13, passenger 0.2375)
# scale/health/attack are Java literals for the tuned default attributes (cubes keep the
# plain vanilla builder: SlimeEntity.setSize overrides health/speed/damage per size, so
# per-type adds would be dead — exactly like infernomobs' molten_slagling).
# weight/gmin/gmax feed BiomeModifications.addSpawn for the three Inferno biomes.
# ---------------------------------------------------------------------------
Mob = namedtuple("Mob", "mid base cls en de drop drop_en drop_de dmin dmax "
                        "weight gmin gmax scale health attack")

MOBS = [
    # --- BlazeEntity bases -------------------------------------------------
    Mob("ember_blaze", "blaze", "EmberBlazeEntity", "Ember Blaze", "Glutlohe",
        "ember_blaze_rod", "Ember Blaze Rod", "Glutlohenrute", 0.0, 2.0,
        10, 1, 2, "0.9", "16.0", None),
    Mob("soot_blaze", "blaze", "SootBlazeEntity", "Soot Blaze", "Ru\u00dflohe",
        "soot_plume", "Soot Plume", "Ru\u00dffahne", 0.0, 2.0,
        10, 1, 2, "0.95", "18.0", None),
    Mob("pyre_blaze", "blaze", "PyreBlazeEntity", "Pyre Blaze", "Brandlohe",
        "pyre_ash", "Pyre Ash", "Brandasche", 0.0, 2.0,
        8, 1, 2, "1.05", "22.0", None),
    Mob("cinder_blaze", "blaze", "CinderBlazeEntity", "Cinder Blaze", "Zunderlohe",
        "cinder_spark", "Cinder Spark", "Zunderfunke", 0.0, 1.0,
        10, 1, 2, "1.0", "20.0", None),
    Mob("slagfire_blaze", "blaze", "SlagfireBlazeEntity", "Slagfire Blaze",
        "Schlackenfeuerlohe",
        "slagfire_droplet", "Slagfire Droplet", "Schlackenfeuertropfen", 0.0, 1.0,
        6, 1, 2, "1.1", "24.0", None),
    # --- MagmaCubeEntity bases ----------------------------------------------
    Mob("molten_cube", "cube", "MoltenCubeEntity", "Molten Cube", "Schmelzw\u00fcrfel",
        "molten_globule", "Molten Globule", "Schmelzk\u00fcgelchen", 0.0, 2.0,
        18, 2, 4, None, None, None),
    Mob("ember_cube", "cube", "EmberCubeEntity", "Ember Cube", "Glutw\u00fcrfel",
        "ember_globule", "Ember Globule", "Glutk\u00fcgelchen", 0.0, 2.0,
        16, 2, 4, None, None, None),
    Mob("soot_cube", "cube", "SootCubeEntity", "Soot Cube", "Ru\u00dfw\u00fcrfel",
        "soot_globule", "Soot Globule", "Ru\u00dfk\u00fcgelchen", 0.0, 2.0,
        16, 2, 4, None, None, None),
    Mob("scoria_cube", "cube", "ScoriaCubeEntity", "Scoria Cube",
        "Lavaschlackenw\u00fcrfel",
        "scoria_chunk", "Scoria Chunk", "Lavaschlackenbrocken", 0.0, 2.0,
        14, 2, 3, None, None, None),
    Mob("pyroclast_cube", "cube", "PyroclastCubeEntity", "Pyroclast Cube",
        "Pyroklastw\u00fcrfel",
        "pyroclast_shard", "Pyroclast Shard", "Pyroklastscherbe", 0.0, 2.0,
        12, 2, 3, None, None, None),
    # --- SilverfishEntity bases ----------------------------------------------
    Mob("cinder_silverfish", "silverfish", "CinderSilverfishEntity", "Cinder Silverfish",
        "Zundersilberfischchen",
        "cinder_chitin", "Cinder Chitin", "Zunderchitin", 0.0, 2.0,
        22, 1, 4, "1.1", "10.0", None),
    Mob("ash_silverfish", "silverfish", "AshSilverfishEntity", "Ash Silverfish",
        "Aschensilberfischchen",
        "ash_chitin", "Ash Chitin", "Aschenchitin", 0.0, 2.0,
        22, 1, 4, "0.9", "8.0", None),
    Mob("ember_silverfish", "silverfish", "EmberSilverfishEntity", "Ember Silverfish",
        "Glutsilberfischchen",
        "ember_chitin", "Ember Chitin", "Glutchitin", 0.0, 2.0,
        18, 1, 3, "1.2", "12.0", "2.0"),
    Mob("soot_silverfish", "silverfish", "SootSilverfishEntity", "Soot Silverfish",
        "Ru\u00dfsilberfischchen",
        "soot_chitin", "Soot Chitin", "Ru\u00dfchitin", 0.0, 2.0,
        20, 1, 4, "1.0", "10.0", None),
    Mob("slag_silverfish", "silverfish", "SlagSilverfishEntity", "Slag Silverfish",
        "Schlackensilberfischchen",
        "slag_chitin", "Slag Chitin", "Schlackenchitin", 0.0, 2.0,
        16, 1, 3, "1.3", "14.0", "3.0"),
    # --- VexEntity bases ------------------------------------------------------
    Mob("flame_vex", "vex", "FlameVexEntity", "Flame Vex", "Flammenplagegeist",
        "flame_essence", "Flame Essence", "Flammenessenz", 0.0, 1.0,
        8, 1, 2, "1.0", "14.0", None),
    Mob("ember_vex", "vex", "EmberVexEntity", "Ember Vex", "Glutplagegeist",
        "ember_essence", "Ember Essence", "Glutessenz", 0.0, 1.0,
        7, 1, 2, "0.9", "12.0", None),
    Mob("cinder_vex", "vex", "CinderVexEntity", "Cinder Vex", "Zunderplagegeist",
        "cinder_essence", "Cinder Essence", "Zunderessenz", 0.0, 1.0,
        7, 1, 2, "1.1", "16.0", None),
    Mob("smoke_vex", "vex", "SmokeVexEntity", "Smoke Vex", "Rauchplagegeist",
        "smoke_essence", "Smoke Essence", "Rauchessenz", 0.0, 1.0,
        6, 1, 2, "0.95", "12.0", None),
    Mob("spark_vex", "vex", "SparkVexEntity", "Spark Vex", "Funkenplagegeist",
        "spark_essence", "Spark Essence", "Funkenessenz", 0.0, 1.0,
        6, 1, 2, "0.85", "10.0", None),
    # --- EndermiteEntity bases -------------------------------------------------
    Mob("ash_mite", "mite", "AshMiteEntity", "Ash Mite", "Aschenmilbe",
        "ash_mite_husk", "Ash Mite Husk", "Aschenmilbenpanzer", 0.0, 1.0,
        18, 1, 3, "1.0", "8.0", None),
    Mob("ember_mite", "mite", "EmberMiteEntity", "Ember Mite", "Glutmilbe",
        "ember_mite_husk", "Ember Mite Husk", "Glutmilbenpanzer", 0.0, 1.0,
        16, 1, 3, "1.1", "10.0", None),
    Mob("cinder_mite", "mite", "CinderMiteEntity", "Cinder Mite", "Zundermilbe",
        "cinder_mite_husk", "Cinder Mite Husk", "Zundermilbenpanzer", 0.0, 1.0,
        14, 1, 3, "1.2", "12.0", None),
    Mob("soot_mite", "mite", "SootMiteEntity", "Soot Mite", "Ru\u00dfmilbe",
        "soot_mite_husk", "Soot Mite Husk", "Ru\u00dfmilbenpanzer", 0.0, 1.0,
        16, 1, 3, "0.9", "8.0", None),
    Mob("slag_mite", "mite", "SlagMiteEntity", "Slag Mite", "Schlackenmilbe",
        "slag_mite_husk", "Slag Mite Husk", "Schlackenmilbenpanzer", 0.0, 1.0,
        12, 1, 2, "1.3", "14.0", None),
]

assert len(MOBS) == 25
assert len({m.mid for m in MOBS}) == 25
assert len({m.drop for m in MOBS}) == 25
assert len({m.cls for m in MOBS}) == 25

BASES = {
    "blaze": dict(sup="BlazeEntity", renderer="BlazeEntityRenderer",
                  attrs="BlazeEntity.createBlazeAttributes()"),
    "cube": dict(sup="MagmaCubeEntity", renderer="MagmaCubeEntityRenderer",
                 attrs="MagmaCubeEntity.createMagmaCubeAttributes()"),
    "silverfish": dict(sup="SilverfishEntity", renderer="SilverfishEntityRenderer",
                       attrs="SilverfishEntity.createSilverfishAttributes()"),
    "vex": dict(sup="VexEntity", renderer="VexEntityRenderer",
                attrs="VexEntity.createVexAttributes()"),
    "mite": dict(sup="EndermiteEntity", renderer="EndermiteEntityRenderer",
                 attrs="EndermiteEntity.createEndermiteAttributes()"),
}


def field(item_id: str) -> str:
    return item_id.upper()


# ---------------------------------------------------------------------------
# Palettes, keyed by the mob id's first token (body, dark, light, spots, outline).
# ---------------------------------------------------------------------------
PALETTES = {
    "ember": ((0xE2, 0x58, 0x22), (0xA8, 0x3C, 0x12), (0xFF, 0x9A, 0x4A),
              (0x3D, 0x2C, 0x2E), (0x2B, 0x22, 0x26)),
    "soot": ((0x4A, 0x46, 0x44), (0x33, 0x30, 0x2E), (0x6A, 0x64, 0x60),
             (0xFF, 0x7A, 0x2F), (0x1E, 0x1B, 0x1A)),
    "pyre": ((0xF6, 0xB2, 0x01), (0xC6, 0x8A, 0x00), (0xFF, 0xD8, 0x66),
             (0x3D, 0x2C, 0x2E), (0x2B, 0x22, 0x26)),
    "cinder": ((0x7A, 0x62, 0x58), (0x59, 0x46, 0x40), (0x9C, 0x82, 0x76),
               (0xE2, 0x58, 0x22), (0x2B, 0x22, 0x26)),
    "slagfire": ((0x9A, 0x34, 0x34), (0x6B, 0x22, 0x26), (0xC2, 0x54, 0x40),
                 (0xFF, 0xB1, 0x6B), (0x2B, 0x22, 0x26)),
    "molten": ((0xFF, 0x7A, 0x2F), (0xD8, 0x54, 0x16), (0xFF, 0xC8, 0x50),
               (0x3D, 0x2C, 0x2E), (0x2B, 0x22, 0x26)),
    "scoria": ((0x3A, 0x32, 0x30), (0x28, 0x22, 0x20), (0x55, 0x4A, 0x46),
               (0xE2, 0x58, 0x22), (0x18, 0x14, 0x12)),
    "pyroclast": ((0x46, 0x3C, 0x42), (0x2F, 0x28, 0x2C), (0x62, 0x54, 0x5C),
                  (0xFF, 0x7A, 0x2F), (0x1C, 0x18, 0x1A)),
    "ash": ((0x8F, 0x8A, 0x84), (0x5F, 0x5B, 0x57), (0xB9, 0xB4, 0xAE),
            (0x3D, 0x2C, 0x2E), (0x2B, 0x22, 0x26)),
    "flame": ((0xFF, 0x9A, 0x30), (0xE2, 0x58, 0x22), (0xFF, 0xE0, 0x7A),
              (0x3D, 0x2C, 0x2E), (0x2B, 0x22, 0x26)),
    "smoke": ((0x6E, 0x6A, 0x70), (0x4C, 0x48, 0x50), (0x92, 0x8C, 0x96),
              (0xE2, 0x58, 0x22), (0x26, 0x22, 0x28)),
    "spark": ((0xFF, 0xD8, 0x66), (0xE0, 0xA8, 0x20), (0xFF, 0xF0, 0xA8),
              (0x3D, 0x2C, 0x2E), (0x2B, 0x22, 0x26)),
    "slag": ((0x74, 0x4A, 0x2C), (0x4E, 0x30, 0x1C), (0x96, 0x66, 0x40),
             (0xFF, 0x7A, 0x2F), (0x2B, 0x22, 0x26)),
}


def palette_of(mob: Mob):
    return PALETTES[mob.mid.split("_")[0]]


# ---------------------------------------------------------------------------
# Texture painters (16x16, Pillow, per-texture seeded rng — byte-identical re-runs).
# spawn_egg() is a 1:1 copy of the proven silhouette in infernomobs_gen.py.
# ---------------------------------------------------------------------------

def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img, x, y, color, alpha=255):
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def row_range(h: float):
    """Inclusive x-range of a row with half-width h around center x=7.5 (as in
    infernomobs_gen.spawn_egg)."""
    return int(math.ceil(7.5 - h)), int(math.floor(7.5 + h))


def egg_texture(rng, pal) -> Image.Image:
    """Classic spawn-egg silhouette (narrow top, wide bottom) with seeded speckles."""
    base, base_dark, base_light, spots, outline = pal
    img = blank()
    half = {2: 1.6, 3: 2.4, 4: 3.0, 5: 3.5, 6: 4.0, 7: 4.4, 8: 4.7, 9: 4.9,
            10: 5.0, 11: 5.0, 12: 4.7, 13: 4.0, 14: 2.8}
    cells = []
    for y, h in half.items():
        x0, x1 = row_range(h)
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


def tex_rod(rng, pal):
    """Diagonal blaze-rod-style baton with a glowing core stripe."""
    base, dark, light, spots, outline = pal
    img = blank()
    for i in range(11):
        x, y = 2 + i, 13 - i
        px(img, x, y, light)
        px(img, x + 1, y, base)
        px(img, x, y - 1, base)
        px(img, x + 2, y, dark)
        px(img, x + 1, y - 1, dark)
    px(img, 2, 14, outline)
    px(img, 13, 2, outline)
    for x, y in [(4, 12), (7, 9), (10, 6)]:
        if rng.random() < 0.9:
            px(img, x, y, spots)
    return img


def tex_plume(rng, pal):
    """Wispy S-curved smoke plume rising to the right."""
    base, dark, light, spots, outline = pal
    img = blank()
    path = [(4, 13), (4, 12), (5, 11), (6, 10), (7, 10), (8, 9), (9, 8), (9, 7),
            (8, 6), (8, 5), (9, 4), (10, 3), (11, 3)]
    for x, y in path:
        px(img, x, y, base)
        px(img, x + 1, y, dark)
        px(img, x - 1, y, light)
    for x, y in [(3, 13), (5, 13), (10, 2), (12, 3)]:
        px(img, x, y, dark, 150)
    for x, y in [(6, 8), (7, 5), (10, 5)]:
        if rng.random() < 0.9:
            px(img, x, y, spots, 180)
    return img


def tex_ashpile(rng, pal):
    """Low mound of ash with faint hot flecks."""
    base, dark, light, spots, outline = pal
    img = blank()
    half = {8: 2.0, 9: 3.2, 10: 4.2, 11: 5.0, 12: 5.6, 13: 6.0}
    for y, h in half.items():
        x0, x1 = row_range(h)
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y == 13:
                color = dark
            elif x <= 6 and y <= 11:
                color = light
            else:
                color = base
            px(img, x, y, color)
    for x, y in [(5, 11), (9, 12), (7, 9), (11, 12)]:
        if rng.random() < 0.8:
            px(img, x, y, spots)
    px(img, 7, 7, light)
    return img


def tex_spark(rng, pal):
    """Four-point star spark with a bright heart."""
    base, dark, light, spots, outline = pal
    img = blank()
    for d in range(1, 6):
        px(img, 7, 7 - d, base if d < 4 else dark)
        px(img, 7, 7 + d, base if d < 4 else dark)
        px(img, 7 - d, 7, base if d < 4 else dark)
        px(img, 7 + d, 7, base if d < 4 else dark)
    for dx, dy in ((-2, -2), (2, -2), (-2, 2), (2, 2)):
        px(img, 7 + dx, 7 + dy, base)
    for dx, dy in ((-1, -1), (1, -1), (-1, 1), (1, 1), (0, 0)):
        px(img, 7 + dx, 7 + dy, light)
    for x, y in [(3, 3), (12, 4), (4, 12)]:
        if rng.random() < 0.8:
            px(img, x, y, light, 140)
    return img


def tex_droplet(rng, pal):
    """Teardrop of molten slag, narrow top, bulbous bottom."""
    base, dark, light, spots, outline = pal
    img = blank()
    half = {3: 0.4, 4: 0.7, 5: 1.0, 6: 1.5, 7: 2.1, 8: 2.7, 9: 3.2, 10: 3.4,
            11: 3.2, 12: 2.5, 13: 1.4}
    for y, h in half.items():
        x0, x1 = row_range(h)
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (3, 13):
                color = dark
            elif x <= 6 and y <= 9:
                color = light
            else:
                color = base
            px(img, x, y, color)
    px(img, 6, 8, light)
    for x, y in [(8, 10), (7, 12)]:
        if rng.random() < 0.9:
            px(img, x, y, spots)
    return img


def tex_globule(rng, pal):
    """Round molten blob with a cracked crust of hot flecks."""
    base, dark, light, spots, outline = pal
    img = blank()
    for y in range(16):
        for x in range(16):
            d = ((x - 7.5) ** 2 + (y - 7.5) ** 2) ** 0.5
            if d <= 5.2:
                if d > 4.3:
                    color = outline
                elif d > 3.0:
                    color = base if (x * 3 + y * 5) % 7 else dark
                else:
                    color = light if (x + y) % 2 else base
                px(img, x, y, color)
    for x, y in [(5, 5), (10, 6), (6, 10), (9, 10)]:
        if rng.random() < 0.85:
            px(img, x, y, spots)
    return img


def tex_chunk(rng, pal):
    """Jagged porous rock chunk with glowing pores."""
    base, dark, light, spots, outline = pal
    img = blank()
    rows = {4: (6, 10), 5: (5, 11), 6: (4, 12), 7: (3, 12), 8: (3, 12), 9: (4, 12),
            10: (4, 11), 11: (5, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (4, 11):
                color = outline
            elif (x * 5 + y * 3) % 6 == 0:
                color = dark
            elif x <= 6 and y <= 7:
                color = light
            else:
                color = base
            px(img, x, y, color)
    for x, y in [(6, 6), (9, 8), (5, 9), (10, 5)]:
        if rng.random() < 0.85:
            px(img, x, y, spots)
    return img


def tex_shard(rng, pal):
    """Angular volcanic shard, point down-right."""
    base, dark, light, spots, outline = pal
    img = blank()
    rows = {3: (3, 7), 4: (3, 8), 5: (4, 9), 6: (4, 10), 7: (5, 10), 8: (6, 11),
            9: (7, 11), 10: (8, 12), 11: (9, 12), 12: (10, 13)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x == x0:
                color = light
            elif x == x1 or y == 3:
                color = outline
            elif x == x0 + 1:
                color = base
            else:
                color = dark
            px(img, x, y, color)
    px(img, 13, 13, outline)
    for x, y in [(6, 5), (8, 8)]:
        if rng.random() < 0.85:
            px(img, x, y, spots)
    return img


def tex_chitin(rng, pal):
    """Segmented chitin plate: three overlapping bands with a lit rim."""
    base, dark, light, spots, outline = pal
    img = blank()
    half = {4: 3.4, 5: 4.2, 6: 4.8, 7: 5.2, 8: 5.2, 9: 4.8, 10: 4.2, 11: 3.4}
    for y, h in half.items():
        x0, x1 = row_range(h)
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (4, 11):
                color = outline
            elif y in (6, 9):
                color = dark
            elif x <= 5:
                color = light
            else:
                color = base
            px(img, x, y, color)
    for x, y in [(5, 5), (9, 7), (7, 10)]:
        if rng.random() < 0.85:
            px(img, x, y, spots)
    return img


def tex_essence(rng, pal):
    """Swirling essence orb: bright spiral core in a wispy shell."""
    base, dark, light, spots, outline = pal
    img = blank()
    for y in range(16):
        for x in range(16):
            d = ((x - 7.5) ** 2 + (y - 7.5) ** 2) ** 0.5
            if 3.6 < d <= 5.0:
                px(img, x, y, dark, 170)
            elif d <= 3.6:
                px(img, x, y, base)
    spiral = [(7, 7), (8, 7), (8, 8), (7, 9), (6, 8), (6, 6), (8, 5), (10, 7)]
    for x, y in spiral:
        px(img, x, y, light)
    for x, y in [(3, 4), (12, 5), (4, 12), (12, 11)]:
        if rng.random() < 0.85:
            px(img, x, y, light, 130)
    px(img, 7, 8, light)
    return img


def tex_husk(rng, pal):
    """Hollow mite husk: domed shell with a dark open underside."""
    base, dark, light, spots, outline = pal
    img = blank()
    half = {5: 2.2, 6: 3.4, 7: 4.2, 8: 4.8, 9: 5.0, 10: 5.0}
    for y, h in half.items():
        x0, x1 = row_range(h)
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y == 5:
                color = outline
            elif y == 8:
                color = dark
            elif x <= 6 and y <= 7:
                color = light
            else:
                color = base
            px(img, x, y, color)
    for x in range(3, 13):
        px(img, x, 11, outline)
        if x % 3 != 0:
            px(img, x, 12, dark)
    for x, y in [(6, 6), (9, 9)]:
        if rng.random() < 0.85:
            px(img, x, y, spots)
    return img


DROP_PAINTERS = {
    "ember_blaze_rod": tex_rod,
    "soot_plume": tex_plume,
    "pyre_ash": tex_ashpile,
    "cinder_spark": tex_spark,
    "slagfire_droplet": tex_droplet,
    "molten_globule": tex_globule,
    "ember_globule": tex_globule,
    "soot_globule": tex_globule,
    "scoria_chunk": tex_chunk,
    "pyroclast_shard": tex_shard,
    "cinder_chitin": tex_chitin,
    "ash_chitin": tex_chitin,
    "ember_chitin": tex_chitin,
    "soot_chitin": tex_chitin,
    "slag_chitin": tex_chitin,
    "flame_essence": tex_essence,
    "ember_essence": tex_essence,
    "cinder_essence": tex_essence,
    "smoke_essence": tex_essence,
    "spark_essence": tex_essence,
    "ash_mite_husk": tex_husk,
    "ember_mite_husk": tex_husk,
    "cinder_mite_husk": tex_husk,
    "soot_mite_husk": tex_husk,
    "slag_mite_husk": tex_husk,
}


def emit_textures() -> None:
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for mob in MOBS:
        pal = palette_of(mob)
        egg = f"{mob.mid}_spawn_egg"
        egg_texture(rng_for(f"emberswarm:{egg}"), pal).save(tex_dir / f"{egg}.png")
        DROP_PAINTERS[mob.drop](rng_for(f"emberswarm:{mob.drop}"), pal).save(
            tex_dir / f"{mob.drop}.png")


# ---------------------------------------------------------------------------
# Item defs/models, entity loot tables (dr_pepper_golem schema), recipes, lang.
# ---------------------------------------------------------------------------

def item_ids() -> list:
    return [f"{m.mid}_spawn_egg" for m in MOBS] + [m.drop for m in MOBS]


def emit_item_assets() -> None:
    for item_id in item_ids():
        genlib.emit_item_def(ASSETS, item_id)
        genlib.emit_item_model(ASSETS, item_id)


def emit_loot_tables() -> None:
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
                                        "max": mob.dmax,
                                        "min": mob.dmin,
                                    },
                                    "function": "minecraft:set_count",
                                }
                            ],
                            "name": f"{NS}:{mob.drop}",
                        }
                    ],
                    "rolls": 1.0,
                }
            ],
            "random_sequence": f"{NS}:entities/{mob.mid}",
        })


# recipe name -> (own-id ingredients ..., vanilla result id, result EN, result DE
# (accusative where needed), count, text templates). Every ingredient list contains at
# least one own id, so the recipes can never collide with vanilla or other features.
Recipe = namedtuple("Recipe", "name ingredients result result_en result_de count en de")

RECIPE_DEFS = [
    Recipe("blaze_powder_from_ember_blaze_rod", ["ember_blaze_rod"],
           "minecraft:blaze_powder", "Blaze Powder", "Lohenstaub", 2,
           "Grind an Ember Blaze Rod into two Blaze Powder.",
           "Mahle eine Glutlohenrute zu zwei Lohenstaub."),
    Recipe("black_dye_from_soot_plume", ["soot_plume"],
           "minecraft:black_dye", "Black Dye", "Schwarzer Farbstoff", 2,
           "A Soot Plume settles into two Black Dye.",
           "Eine Ru\u00dffahne ergibt zwei Schwarzen Farbstoff."),
    Recipe("gunpowder_from_pyre_ash", ["pyre_ash"],
           "minecraft:gunpowder", "Gunpowder", "Schwarzpulver", 2,
           "Sift Pyre Ash into two Gunpowder.",
           "Siebe Brandasche zu zwei Schwarzpulver."),
    Recipe("fire_charge_from_cinder_spark", ["cinder_spark"],
           "minecraft:fire_charge", "Fire Charge", "Feuerkugel", 1,
           "A Cinder Spark ignites into a Fire Charge.",
           "Ein Zunderfunke entz\u00fcndet sich zu einer Feuerkugel."),
    Recipe("magma_cream_from_slagfire_droplet", ["slagfire_droplet"],
           "minecraft:magma_cream", "Magma Cream", "Magmacreme", 2,
           "Knead a Slagfire Droplet into two Magma Cream.",
           "Knete einen Schlackenfeuertropfen zu zwei Magmacreme."),
    Recipe("magma_cream_from_molten_globule", ["molten_globule"],
           "minecraft:magma_cream", "Magma Cream", "Magmacreme", 2,
           "Knead a Molten Globule into two Magma Cream.",
           "Knete ein Schmelzk\u00fcgelchen zu zwei Magmacreme."),
    Recipe("magma_cream_from_ember_globule", ["ember_globule"],
           "minecraft:magma_cream", "Magma Cream", "Magmacreme", 2,
           "Knead an Ember Globule into two Magma Cream.",
           "Knete ein Glutk\u00fcgelchen zu zwei Magmacreme."),
    Recipe("black_dye_from_soot_globule", ["soot_globule"],
           "minecraft:black_dye", "Black Dye", "Schwarzer Farbstoff", 2,
           "A Soot Globule smears into two Black Dye.",
           "Ein Ru\u00dfk\u00fcgelchen ergibt zwei Schwarzen Farbstoff."),
    Recipe("magma_block_from_scoria_chunks",
           ["scoria_chunk", "scoria_chunk", "scoria_chunk", "scoria_chunk"],
           "minecraft:magma_block", "Magma Block", "Magmablock", 1,
           "Four Scoria Chunks pack into a Magma Block.",
           "Vier Lavaschlackenbrocken werden zu einem Magmablock gepresst."),
    Recipe("blackstone_from_pyroclast_shards", ["pyroclast_shard", "pyroclast_shard"],
           "minecraft:blackstone", "Blackstone", "Schwarzstein", 1,
           "Two Pyroclast Shards fuse into Blackstone.",
           "Zwei Pyroklastscherben verschmelzen zu Schwarzstein."),
    Recipe("string_from_cinder_chitin", ["cinder_chitin", "cinder_chitin"],
           "minecraft:string", "String", "Faden", 2,
           "Two pieces of Cinder Chitin twist into two String.",
           "Zwei St\u00fcck Zunderchitin ergeben zwei F\u00e4den."),
    Recipe("string_from_ash_chitin", ["ash_chitin", "ash_chitin"],
           "minecraft:string", "String", "Faden", 2,
           "Two pieces of Ash Chitin twist into two String.",
           "Zwei St\u00fcck Aschenchitin ergeben zwei F\u00e4den."),
    Recipe("string_from_ember_chitin", ["ember_chitin", "ember_chitin"],
           "minecraft:string", "String", "Faden", 2,
           "Two pieces of Ember Chitin twist into two String.",
           "Zwei St\u00fcck Glutchitin ergeben zwei F\u00e4den."),
    Recipe("string_from_soot_chitin", ["soot_chitin", "soot_chitin"],
           "minecraft:string", "String", "Faden", 2,
           "Two pieces of Soot Chitin twist into two String.",
           "Zwei St\u00fcck Ru\u00dfchitin ergeben zwei F\u00e4den."),
    Recipe("string_from_slag_chitin", ["slag_chitin", "slag_chitin"],
           "minecraft:string", "String", "Faden", 2,
           "Two pieces of Slag Chitin twist into two String.",
           "Zwei St\u00fcck Schlackenchitin ergeben zwei F\u00e4den."),
    Recipe("glowstone_dust_from_flame_essence", ["flame_essence"],
           "minecraft:glowstone_dust", "Glowstone Dust", "Leuchtsteinstaub", 2,
           "Condense a Flame Essence into two Glowstone Dust.",
           "Verdichte eine Flammenessenz zu zwei Leuchtsteinstaub."),
    Recipe("glowstone_dust_from_ember_essence", ["ember_essence"],
           "minecraft:glowstone_dust", "Glowstone Dust", "Leuchtsteinstaub", 2,
           "Condense an Ember Essence into two Glowstone Dust.",
           "Verdichte eine Glutessenz zu zwei Leuchtsteinstaub."),
    Recipe("glowstone_dust_from_cinder_essence", ["cinder_essence"],
           "minecraft:glowstone_dust", "Glowstone Dust", "Leuchtsteinstaub", 2,
           "Condense a Cinder Essence into two Glowstone Dust.",
           "Verdichte eine Zunderessenz zu zwei Leuchtsteinstaub."),
    Recipe("glowstone_dust_from_smoke_essence", ["smoke_essence"],
           "minecraft:glowstone_dust", "Glowstone Dust", "Leuchtsteinstaub", 2,
           "Condense a Smoke Essence into two Glowstone Dust.",
           "Verdichte eine Rauchessenz zu zwei Leuchtsteinstaub."),
    Recipe("glowstone_dust_from_spark_essence", ["spark_essence"],
           "minecraft:glowstone_dust", "Glowstone Dust", "Leuchtsteinstaub", 2,
           "Condense a Spark Essence into two Glowstone Dust.",
           "Verdichte eine Funkenessenz zu zwei Leuchtsteinstaub."),
    Recipe("gray_dye_from_ash_mite_husk", ["ash_mite_husk"],
           "minecraft:gray_dye", "Gray Dye", "Grauer Farbstoff", 2,
           "An Ash Mite Husk grinds into two Gray Dye.",
           "Ein Aschenmilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."),
    Recipe("gray_dye_from_ember_mite_husk", ["ember_mite_husk"],
           "minecraft:gray_dye", "Gray Dye", "Grauer Farbstoff", 2,
           "An Ember Mite Husk grinds into two Gray Dye.",
           "Ein Glutmilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."),
    Recipe("gray_dye_from_cinder_mite_husk", ["cinder_mite_husk"],
           "minecraft:gray_dye", "Gray Dye", "Grauer Farbstoff", 2,
           "A Cinder Mite Husk grinds into two Gray Dye.",
           "Ein Zundermilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."),
    Recipe("gray_dye_from_soot_mite_husk", ["soot_mite_husk"],
           "minecraft:gray_dye", "Gray Dye", "Grauer Farbstoff", 2,
           "A Soot Mite Husk grinds into two Gray Dye.",
           "Ein Ru\u00dfmilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."),
    Recipe("gray_dye_from_slag_mite_husk", ["slag_mite_husk"],
           "minecraft:gray_dye", "Gray Dye", "Grauer Farbstoff", 2,
           "A Slag Mite Husk grinds into two Gray Dye.",
           "Ein Schlackenmilbenpanzer wird zu zwei Grauem Farbstoff zermahlen."),
]

assert len(RECIPE_DEFS) == 25
assert len({r.name for r in RECIPE_DEFS}) == 25


def emit_recipes() -> None:
    for r in RECIPE_DEFS:
        genlib.emit_shapeless(RECIPES, r.name, [f"{NS}:{i}" for i in r.ingredients],
                              r.result, r.count, category="misc")


def build_lang():
    en, de = {}, {}
    for mob in MOBS:
        en[f"entity.{NS}.{mob.mid}"] = mob.en
        de[f"entity.{NS}.{mob.mid}"] = mob.de
        en[f"item.{NS}.{mob.mid}_spawn_egg"] = f"{mob.en} Spawn Egg"
        de[f"item.{NS}.{mob.mid}_spawn_egg"] = f"{mob.de}-Spawn-Ei"
        en[f"item.{NS}.{mob.drop}"] = mob.drop_en
        de[f"item.{NS}.{mob.drop}"] = mob.drop_de
    return en, de


# ---------------------------------------------------------------------------
# Handbook entries: one "mobs" page per mob + one "items" recipe page per recipe JSON
# (devtools/check_handbook.py requires the latter once "emberswarm" joins FEATURE_PKGS).
# ---------------------------------------------------------------------------

BASE_FLAVOR_EN = {
    "blaze": "{en} - a blaze spirit of the ember swarm, whirling through the Cinder"
             " Wastes, Ember Grove and Slag Sea. Drops {drop}.",
    "cube": "{en} - a bouncing swarm cube that splits like a magma cube when slain."
            " Roams the Inferno biomes. Drops {drop}.",
    "silverfish": "{en} - a skittering swarm silverfish of the Inferno biomes. Does"
                  " not infest blocks. Drops {drop}.",
    "vex": "{en} - a fiery vex flitting through the air of the Inferno biomes. Phases"
           " through blocks. Drops {drop}.",
    "mite": "{en} - a tiny swarm mite scuttling across the Inferno biomes."
            " Drops {drop}.",
}

BASE_FLAVOR_DE = {
    "blaze": "{de} - ein Lohengeist des Glutschwarms, der durch Aschen\u00f6de,"
             " Gluthain und Schlackenmeer wirbelt. L\u00e4sst {drop} fallen.",
    "cube": "{de} - ein h\u00fcpfender Schwarmw\u00fcrfel, der sich wie ein"
            " Magmaw\u00fcrfel teilt. Streift durch die Inferno-Biome. L\u00e4sst"
            " {drop} fallen.",
    "silverfish": "{de} - ein huschendes Schwarm-Silberfischchen der Inferno-Biome."
                  " Bef\u00e4llt keine Bl\u00f6cke. L\u00e4sst {drop} fallen.",
    "vex": "{de} - ein feuriger Plagegeist, der durch die Luft der Inferno-Biome"
           " schwirrt. Fliegt durch Bl\u00f6cke. L\u00e4sst {drop} fallen.",
    "mite": "{de} - eine winzige Schwarmmilbe, die durch die Inferno-Biome krabbelt."
            " L\u00e4sst {drop} fallen.",
}


def build_handbook():
    entries = []
    for mob in MOBS:
        entries.append((
            "mobs", mob.mid, f"{NS}:{mob.mid}_spawn_egg", None, None, None, 0,
            BASE_FLAVOR_EN[mob.base].format(en=mob.en, drop=mob.drop_en),
            BASE_FLAVOR_DE[mob.base].format(de=mob.de, drop=mob.drop_de)))
    for r in RECIPE_DEFS:
        grid = [f"{NS}:{i}" for i in r.ingredients] + [""] * (9 - len(r.ingredients))
        entries.append((
            "items", r.name, f"{NS}:{r.ingredients[0]}", f"emberswarm/{r.name}",
            grid, r.result, r.count, r.en, r.de))
    return entries


# ---------------------------------------------------------------------------
# Java codegen: EmberSwarmFeature.java, the 25 entity subclasses,
# EmberSwarmFeatureClient.java, EmberSwarmHandbook.java. All registration ids are plain
# string literals (audit check (f)); every vanilla signature referenced below was
# verified via javap against the 1.21.9 yarn jars before generation.
# ---------------------------------------------------------------------------

PKG = "net.sonic0810.copperinferno.feature.emberswarm"

# EntityType.Builder chains copied 1:1 from the vanilla EntityType registrations
# (bytecode): blaze / magma_cube / silverfish / vex / endermite.
BUILDER_CHAINS = {
    "blaze": ["\t\t\t\t\t\t.makeFireImmune()",
              "\t\t\t\t\t\t.dimensions(0.6f, 1.8f)",
              "\t\t\t\t\t\t.maxTrackingRange(8)",
              "\t\t\t\t\t\t.notAllowedInPeaceful());"],
    "cube": ["\t\t\t\t\t\t.makeFireImmune()",
             "\t\t\t\t\t\t.dimensions(0.52f, 0.52f)",
             "\t\t\t\t\t\t.eyeHeight(0.325f)",
             "\t\t\t\t\t\t.spawnBoxScale(4.0f)",
             "\t\t\t\t\t\t.maxTrackingRange(8)",
             "\t\t\t\t\t\t.notAllowedInPeaceful());"],
    "silverfish": ["\t\t\t\t\t\t.dimensions(0.4f, 0.3f)",
                   "\t\t\t\t\t\t.eyeHeight(0.13f)",
                   "\t\t\t\t\t\t.passengerAttachments(0.2375f)",
                   "\t\t\t\t\t\t.maxTrackingRange(8)",
                   "\t\t\t\t\t\t.notAllowedInPeaceful());"],
    "vex": ["\t\t\t\t\t\t.makeFireImmune()",
            "\t\t\t\t\t\t.dimensions(0.4f, 0.8f)",
            "\t\t\t\t\t\t.eyeHeight(0.51875f)",
            "\t\t\t\t\t\t.passengerAttachments(0.7375f)",
            "\t\t\t\t\t\t.vehicleAttachment(0.04f)",
            "\t\t\t\t\t\t.maxTrackingRange(8)",
            "\t\t\t\t\t\t.notAllowedInPeaceful());"],
    "mite": ["\t\t\t\t\t\t.dimensions(0.4f, 0.3f)",
             "\t\t\t\t\t\t.eyeHeight(0.13f)",
             "\t\t\t\t\t\t.passengerAttachments(0.2375f)",
             "\t\t\t\t\t\t.maxTrackingRange(8)",
             "\t\t\t\t\t\t.notAllowedInPeaceful());"],
}

BUILDER_COMMENTS = {
    "blaze": "// blaze = makeFireImmune().dimensions(0.6f, 1.8f).maxTrackingRange(8)"
             ".notAllowedInPeaceful().",
    "cube": "// magma_cube = makeFireImmune().dimensions(0.52f, 0.52f).eyeHeight(0.325f)"
            ".spawnBoxScale(4.0f)\n\t\t// .maxTrackingRange(8).notAllowedInPeaceful();"
            " size handling is inherited from SlimeEntity.",
    "silverfish": "// silverfish = dimensions(0.4f, 0.3f).eyeHeight(0.13f)"
                  ".passengerAttachments(0.2375f)\n\t\t// .maxTrackingRange(8)"
                  ".notAllowedInPeaceful().",
    "vex": "// vex = makeFireImmune().dimensions(0.4f, 0.8f).eyeHeight(0.51875f)"
           ".passengerAttachments(0.7375f)\n\t\t// .vehicleAttachment(0.04f)"
           ".maxTrackingRange(8).notAllowedInPeaceful().",
    "mite": "// endermite = dimensions(0.4f, 0.3f).eyeHeight(0.13f)"
            ".passengerAttachments(0.2375f)\n\t\t// .maxTrackingRange(8)"
            ".notAllowedInPeaceful().",
}


def feature_java() -> str:
    doc = [
        "The Ember Swarm: 25 swarm-sized hostile mobs native to the Inferno dimension, five",
        "each on the vanilla Blaze / MagmaCube / Silverfish / Vex / Endermite bases. Every mob",
        "is a thin subclass reusing the vanilla renderer (see {@code EmberSwarmFeatureClient});",
        "EntityType dimensions/eye heights/attachments are copied from the vanilla",
        "{@code EntityType} registrations (verified via bytecode). Spawn eggs, mob-drop items,",
        "natural spawns in the three Inferno biomes and drop recipes are registered here;",
        "handbook pages live in {@link EmberSwarmHandbook}. Generated by",
        "{@code devtools/gen/emberswarm_gen.py} - do not edit by hand.",
    ]
    out = [f"package {PKG};", ""]
    out += ["import java.util.function.Predicate;", ""]
    imports = [
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
        "net.minecraft.entity.mob.BlazeEntity",
        "net.minecraft.entity.mob.EndermiteEntity",
        "net.minecraft.entity.mob.HostileEntity",
        "net.minecraft.entity.mob.MagmaCubeEntity",
        "net.minecraft.entity.mob.SilverfishEntity",
        "net.minecraft.entity.mob.VexEntity",
        "net.minecraft.entity.player.PlayerEntity",
        "net.minecraft.item.Item",
        "net.minecraft.item.SpawnEggItem",
        "net.minecraft.registry.RegistryKey",
        "net.minecraft.registry.RegistryKeys",
        "net.minecraft.util.math.BlockPos",
        "net.minecraft.util.math.random.Random",
        "net.minecraft.world.Difficulty",
        "net.minecraft.world.Heightmap",
        "net.minecraft.world.ServerWorldAccess",
        "net.sonic0810.copperinferno.CopperInferno",
        "net.sonic0810.copperinferno.core.ModCreativeTab",
        "net.sonic0810.copperinferno.core.ModEntities",
        "net.sonic0810.copperinferno.core.ModItems",
    ]
    out += [f"import {i};" for i in sorted(imports)]
    body = "\n".join(f" * {line}".rstrip() for line in doc)
    out += ["", f"/**\n{body}\n */",
            "public final class EmberSwarmFeature {",
            "\tprivate EmberSwarmFeature() {",
            "\t}", ""]

    for mob in MOBS:
        out.append(f"\tpublic static EntityType<{mob.cls}> {field(mob.mid)};")
    out.append("")
    for mob in MOBS:
        out.append(f"\tpublic static Item {field(mob.mid)}_SPAWN_EGG;")
    out.append("")
    for mob in MOBS:
        out.append(f"\tpublic static Item {field(mob.drop)};")
    out += ["",
            "\tpublic static void init() {",
            "\t\tregisterEntityTypes();",
            "\t\tregisterAttributes();",
            "\t\tregisterItems();",
            "\t\tregisterSpawning();",
            "\t\tEmberSwarmHandbook.register();",
            "\t}", ""]

    # registerEntityTypes
    out += ["\tprivate static void registerEntityTypes() {",
            "\t\t// Dimensions/eye heights/attachments/tracking ranges copied from the vanilla",
            "\t\t// EntityType registrations (bytecode-verified, same method as infernomobs)."]
    seen_bases = set()
    for mob in MOBS:
        if mob.base not in seen_bases:
            seen_bases.add(mob.base)
            for line in BUILDER_COMMENTS[mob.base].split("\n"):
                out.append(f"\t\t{line}" if not line.startswith("\t") else line)
        out.append(f"\t\t{field(mob.mid)} = ModEntities.register(\"{mob.mid}\",")
        out.append(f"\t\t\t\tEntityType.Builder.create({mob.cls}::new, SpawnGroup.MONSTER)")
        out += BUILDER_CHAINS[mob.base]
    out += ["\t}", ""]

    # registerAttributes
    out += ["\tprivate static void registerAttributes() {",
            "\t\t// EntityAttributes fields have no GENERIC_ prefix in 1.21.9 (verified via",
            "\t\t// javap); DefaultAttributeContainer.Builder.add(...) overrides base values.",
            "\t\t// The magma-cube types stay on the plain vanilla builder because",
            "\t\t// SlimeEntity.setSize overrides health/speed/damage per size (like",
            "\t\t// infernomobs' molten_slagling)."]
    for mob in MOBS:
        base_call = BASES[mob.base]["attrs"]
        if mob.scale is None:
            out.append(f"\t\tFabricDefaultAttributeRegistry.register({field(mob.mid)}, {base_call});")
        else:
            out.append(f"\t\tFabricDefaultAttributeRegistry.register({field(mob.mid)}, {base_call}")
            adds = [f"\t\t\t\t.add(EntityAttributes.SCALE, {mob.scale})"]
            adds.append(f"\t\t\t\t.add(EntityAttributes.MAX_HEALTH, {mob.health})")
            if mob.attack is not None:
                adds.append(f"\t\t\t\t.add(EntityAttributes.ATTACK_DAMAGE, {mob.attack})")
            adds[-1] += ");"
            out += adds
    out += ["\t}", ""]

    # registerItems
    out += ["\tprivate static void registerItems() {",
            "\t\t// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the vanilla",
            "\t\t// SpawnEggItem reads its entity type from in 1.21.9 (proven pattern from",
            "\t\t// infernomobs / dr_pepper_golem)."]
    for mob in MOBS:
        out.append(f"\t\t{field(mob.mid)}_SPAWN_EGG = ModItems.register(\"{mob.mid}_spawn_egg\", SpawnEggItem::new,")
        out.append(f"\t\t\t\tnew Item.Settings().spawnEgg({field(mob.mid)}));")
    out.append("")
    for mob in MOBS:
        out.append(f"\t\t{field(mob.drop)} = ModItems.register(\"{mob.drop}\", Item::new, new Item.Settings());")
    out += ["",
            "\t\tItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY).register(entries -> {",
            "\t\t\t// Spawn eggs together, then the mob drops."]
    for mob in MOBS:
        out.append(f"\t\t\tentries.add({field(mob.mid)}_SPAWN_EGG);")
    for mob in MOBS:
        out.append(f"\t\t\tentries.add({field(mob.drop)});")
    out += ["\t\t});", "\t}", ""]

    # registerSpawning
    out += ["\tprivate static void registerSpawning() {",
            "\t\t// SpawnRestriction.register is private in vanilla but access-widened by",
            "\t\t// Fabric's transitive access wideners (proven by infernomobs). Locations/",
            "\t\t// heightmaps/predicates mirror the vanilla entries (SpawnRestriction",
            "\t\t// bytecode): blaze uses ON_GROUND + HostileEntity::canSpawnIgnoreLightLevel,",
            "\t\t// magma_cube's canMagmaCubeSpawn is only a difficulty != PEACEFUL check",
            "\t\t// (inlined; the vanilla method is typed to EntityType<MagmaCubeEntity>),",
            "\t\t// silverfish/endermite use their canSpawn (reimplemented 1:1 in",
            "\t\t// canSwarmBugSpawn below, the vanilla methods are likewise typed), and vex",
            "\t\t// uses UNRESTRICTED + HostileEntity::canSpawnInDark (it flies)."]
    for mob in MOBS:
        f = field(mob.mid)
        if mob.base == "blaze":
            out.append(f"\t\tSpawnRestriction.register({f}, SpawnLocationTypes.ON_GROUND,")
            out.append("\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);")
        elif mob.base == "cube":
            out.append(f"\t\tSpawnRestriction.register({f}, SpawnLocationTypes.ON_GROUND,")
            out.append("\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES,")
            out.append("\t\t\t\t(type, world, reason, pos, random) -> world.getDifficulty() != Difficulty.PEACEFUL);")
        elif mob.base in ("silverfish", "mite"):
            out.append(f"\t\tSpawnRestriction.register({f}, SpawnLocationTypes.ON_GROUND,")
            out.append("\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES, EmberSwarmFeature::canSwarmBugSpawn);")
        else:  # vex
            out.append(f"\t\tSpawnRestriction.register({f}, SpawnLocationTypes.UNRESTRICTED,")
            out.append("\t\t\t\tHeightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);")
    out += ["",
            "\t\t// Natural spawns in the three Inferno biomes (biome JSONs are owned by the",
            "\t\t// infernodim feature; includeByKey matches nothing until they are loaded).",
            "\t\tPredicate<BiomeSelectionContext> infernoBiomes = BiomeSelectors.includeByKey(",
            "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"cinder_wastes\")),",
            "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"ember_grove\")),",
            "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"slag_sea\")));"]
    for mob in MOBS:
        out.append(f"\t\tBiomeModifications.addSpawn(infernoBiomes, SpawnGroup.MONSTER, "
                   f"{field(mob.mid)}, {mob.weight}, {mob.gmin}, {mob.gmax});")
    out += ["\t}", ""]

    # canSwarmBugSpawn helper
    out += [
        "\t/**",
        "\t * Mirrors vanilla {@code SilverfishEntity.canSpawn} / {@code EndermiteEntity.canSpawn}",
        "\t * (verified against the 1.21.9 bytecode - the two vanilla methods are identical but",
        "\t * typed to the vanilla EntityTypes, so they cannot be reused directly): hostile",
        "\t * ignore-light-level checks, spawners bypass the player-distance gate, otherwise no",
        "\t * player may be within 5 blocks of the spawn position.",
        "\t */",
        "\tprivate static <T extends HostileEntity> boolean canSwarmBugSpawn(EntityType<T> type,",
        "\t\t\tServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {",
        "\t\tif (!HostileEntity.canSpawnIgnoreLightLevel(type, world, reason, pos, random)) {",
        "\t\t\treturn false;",
        "\t\t}",
        "\t\tif (SpawnReason.isAnySpawner(reason)) {",
        "\t\t\treturn true;",
        "\t\t}",
        "\t\tPlayerEntity player = world.getClosestPlayer(pos.getX() + 0.5, pos.getY() + 0.5,",
        "\t\t\t\tpos.getZ() + 0.5, 5.0, true);",
        "\t\treturn player == null;",
        "\t}",
        "}",
    ]
    return "\n".join(out) + "\n"


ENTITY_DOC = {
    "blaze": ["A swarm blaze of the Inferno dimension. Behavior is pure vanilla blaze; the",
              "stat distinction comes from the default attributes registered in",
              "{@link EmberSwarmFeature}. Drops are in"],
    "cube": ["A swarm magma cube. Behavior is pure vanilla magma cube, including the vanilla",
             "size handling: {@code SlimeEntity.initialize(...)} rolls a random size (1/2/4) on",
             "natural spawn, {@code setSize(int, boolean)} (public in 1.21.9, verified via",
             "javap) rescales health/damage, and cubes split on death. Drops are in"],
    "silverfish": ["A swarm silverfish. Behavior is vanilla silverfish minus the block",
                   "infestation AI (see {@link #initGoals()}); stats come from",
                   "{@link EmberSwarmFeature}. Drops are in"],
    "vex": ["A swarm vex. Behavior is pure vanilla vex: it flies via the vex move control",
            "installed by the base constructor, phases through blocks and only decays when",
            "{@code setLifeTicks} is called (evoker summons; natural spawns live forever).",
            "Stats come from {@link EmberSwarmFeature}. Drops are in"],
    "mite": ["A swarm endermite. Behavior is pure vanilla endermite; stats come from",
             "{@link EmberSwarmFeature}. Drops are in"],
}


def entity_java(mob: Mob) -> str:
    sup = BASES[mob.base]["sup"]
    doc = ENTITY_DOC[mob.base] + [f"{{@code loot_table/entities/{mob.mid}.json}}.",
                                  "Generated by {@code devtools/gen/emberswarm_gen.py}."]
    body = "\n".join(f" * {line}".rstrip() for line in doc)
    out = [f"package {PKG};", "",
           "import net.minecraft.entity.EntityType;",
           f"import net.minecraft.entity.mob.{sup};",
           "import net.minecraft.world.World;", "",
           f"/**\n{body}\n */",
           f"public class {mob.cls} extends {sup} {{",
           f"\tpublic {mob.cls}(EntityType<? extends {sup}> type, World world) {{",
           "\t\tsuper(type, world);",
           "\t}"]
    if mob.base == "silverfish":
        out += ["",
                "\t@Override",
                "\tprotected void initGoals() {",
                "\t\tsuper.initGoals();",
                "\t\t// Swarm silverfish do not infest blocks: drop the two inherited infestation",
                "\t\t// goals vanilla SilverfishEntity.initGoals adds (CallForHelpGoal +",
                "\t\t// WanderAndInfestGoal, both PRIVATE inner classes of SilverfishEntity),",
                "\t\t// matched via their declaring class exactly like the proven",
                "\t\t// SlagCrawlerEntity (infernomobs).",
                "\t\tthis.goalSelector.clear(goal -> goal.getClass().getDeclaringClass() == SilverfishEntity.class);",
                "\t}"]
    out.append("}")
    return "\n".join(out) + "\n"


def client_java() -> str:
    renderers = sorted({BASES[m.base]["renderer"] for m in MOBS})
    doc = [
        "Client-side setup for the Ember Swarm mobs: each type reuses its vanilla renderer",
        "(all five ctors are Context-only, verified via javap). The mobs extend the matching",
        "vanilla entities, so the factories fit the register(EntityType&lt;? extends T&gt;,",
        "EntityRendererFactory&lt;T&gt;) bound; the vanilla register method is access-widened",
        "by Fabric's transitive access wideners (same proven pattern as infernomobs).",
        "Generated by {@code devtools/gen/emberswarm_gen.py} - do not edit by hand.",
    ]
    body = "\n".join(f" * {line}".rstrip() for line in doc)
    # Single sorted import block: renderers + EntityRendererFactories + feature class.
    imports = ([f"net.minecraft.client.render.entity.{r}" for r in renderers]
               + ["net.minecraft.client.render.entity.EntityRendererFactories",
                  f"{PKG}.EmberSwarmFeature"])
    out = [f"package {PKG}.client;", ""]
    out += [f"import {i};" for i in sorted(imports)]
    out += ["", f"/**\n{body}\n */",
            "public final class EmberSwarmFeatureClient {",
            "\tprivate EmberSwarmFeatureClient() {",
            "\t}", "",
            "\tpublic static void initClient() {"]
    for mob in MOBS:
        out.append(f"\t\tEntityRendererFactories.register(EmberSwarmFeature.{field(mob.mid)}, "
                   f"{BASES[mob.base]['renderer']}::new);")
    out += ["\t}", "}"]
    return "\n".join(out) + "\n"


def emit_java(handbook_entries) -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)
    CLIENT_DIR.mkdir(parents=True, exist_ok=True)
    (FEATURE_DIR / "EmberSwarmFeature.java").write_text(feature_java(), encoding="utf-8")
    for mob in MOBS:
        (FEATURE_DIR / f"{mob.cls}.java").write_text(entity_java(mob), encoding="utf-8")
    (CLIENT_DIR / "EmberSwarmFeatureClient.java").write_text(client_java(), encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the Ember Swarm: one \"mobs\" page per mob plus one \"items\"",
        "recipe page for every JSON under {@code data/copper_inferno/recipe/emberswarm/}.",
        "Entry texts and grids mirror the recipe JSONs emitted by",
        "{@code devtools/gen/emberswarm_gen.py}; {@code devtools/check_handbook.py} parses",
        "the inline {@code new HandbookEntry(...)} literals positionally, so keep them inline.",
    ]
    handbook_src = genlib.java_handbook_class("emberswarm", "EmberSwarmHandbook",
                                              handbook_doc, handbook_entries)
    (FEATURE_DIR / "EmberSwarmHandbook.java").write_text(handbook_src, encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/emberswarm.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks() -> None:
    lines = ["# emberswarm feature hooks (format: devtools/hooks/README.md)", "",
             "[init]",
             f"import {PKG}.EmberSwarmFeature;",
             "\t\tEmberSwarmFeature.init();", "",
             "[client-init]",
             f"import {PKG}.client.EmberSwarmFeatureClient;",
             "\t\tEmberSwarmFeatureClient.initClient();", "",
             "[recipe-dir]",
             "emberswarm", "",
             "[counts]",
             f"mobs: {len(MOBS)}",
             f"items: {len(item_ids())}",
             f"recipes: {len(RECIPE_DEFS)}",
             f"handbook-entries: {len(MOBS) + len(RECIPE_DEFS)}", ""]
    path = ROOT / "devtools" / "hooks" / "emberswarm.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_item_assets()
    emit_textures()
    emit_loot_tables()
    emit_recipes()

    lang_en, lang_de = build_lang()
    genlib.lang_fragments(ASSETS, "emberswarm", lang_en, lang_de)

    handbook_entries = build_handbook()
    emit_java(handbook_entries)
    emit_hooks()

    eggs = [f"{m.mid}_spawn_egg" for m in MOBS]
    assert len(MOBS) == 25, f"expected 25 mobs, got {len(MOBS)}"
    assert len(eggs) == len(set(eggs)) == 25, "expected 25 distinct spawn eggs"
    assert len(item_ids()) == len(set(item_ids())) == 50, "expected 50 distinct items"
    assert sorted(lang_en) == sorted(lang_de), "EN and DE lang key sets must match"
    assert len(lang_en) == 75, f"expected 75 lang keys, got {len(lang_en)}"
    assert len(handbook_entries) == 50, f"expected 50 handbook entries, got {len(handbook_entries)}"
    print(f"emberswarm_gen: assets + Java generated for {len(MOBS)} mobs / "
          f"{len(item_ids())} items ({len(RECIPE_DEFS)} recipes, "
          f"{len(handbook_entries)} handbook entries).")


if __name__ == "__main__":
    main()
