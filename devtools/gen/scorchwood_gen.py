#!/usr/bin/env python3
"""Scorched Woods asset generator for COPPER INFERNO 1 (v4 WP4, mod id: copper_inferno).

Generates ALL assets/data for the 8 scorched wood sets registered by
feature/scorchwood/ScorchWoodFeature via core.content.WoodSets.registerWoodSet:
emberwood, ashwillow, cinderpine, charoak, glowbirch, sootmaple, duskthorn, pyrewood
— 13 set ids each plus <w>_sapling and <w>_leaves (survival progression) = 15 x 8 =
120 block ids total:
  <w>_planks, <w>_plank_slab, <w>_plank_stairs, <w>_fence, <w>_fence_gate, <w>_button,
  <w>_pressure_plate, <w>_log, stripped_<w>_log, <w>_wood, stripped_<w>_wood,
  <w>_mosaic, <w>_pillar, <w>_sapling, <w>_leaves

Emits (per run, idempotent — deterministic seeded textures + sorted-key JSON):
  - 8 x 10 = 80 16x16 PNGs (planks, log side/top, stripped log side/top, mosaic,
    pillar side/top, sapling cross sprite, leaves; the wood/stripped-wood blocks
    reuse the log side textures exactly like vanilla oak_wood)
  - blockstates + models + items/<id>.json + loot for all 120 ids, via
    devtools/gen/lib_gen.py emitters (fence/gate/button/pressure-plate multipart &
    multi-variant formats are the exact vanilla formats already used by
    utilityblocks/infernoflora). Saplings use the vanilla cross model + flat item
    sprite and drop-self loot; leaves use the vanilla leaves model (cube_all-shaped,
    parent minecraft:block/leaves) and the vanilla oak_leaves loot table with the
    apple pool dropped and <w>_sapling substituted (byte-identical to the 1.21.9
    birch_leaves.json structure extracted from the client jar)
  - worldgen JSON under data/copper_inferno/worldgen/: configured_feature/<w>_tree
    (vanilla oak.json structure: straight_trunk_placer 4-6, blob_foliage_placer
    radius 2, two_layers_feature_size; trunk/foliage providers are the wood's own
    log/leaves, dirt_provider minecraft:dirt) + placed_feature/<w>_trees
    (count_on_every_layer 2-3 + biome filter — the nether-style layered placement
    the mod's patch_* features already use, REQUIRED because the Inferno dimension
    has a bedrock roof so heightmap placement would put trees on the ceiling)
  - 12 recipes per wood (96 total) under data/copper_inferno/recipe/scorchwood/:
    logs->4 planks (shapeless via the #copper_inferno:<w>_logs item tag, same pattern
    as infernoflora's scorched_planks), the vanilla plank->slab/stairs/fence/gate/
    button/pressure-plate patterns, 2x2 logs -> 3 wood (vanilla bark pattern),
    2x2 plank slabs -> 2 mosaic (material parity), and stonecutting for the pillar
    (from planks) and both stripped variants (from log/wood — stripping is a
    stonecutter recipe, NOT an axe interaction; documented simplification)
  - data/copper_inferno/tags/item/<w>_logs.json (4 log/wood variants, format of
    the existing scorched_logs.json)
  - devtools/tagfrag/scorchwood.json (merged later by devtools/merge_tags.py):
    planks in block+item planks, fences/gates/buttons/plates/slabs/stairs in their
    vanilla wooden tags, the 13-block sets in block/mineable/axe, all 4 log-family
    ids per wood in block+item logs AND logs_that_burn (charcoal smelting +
    flammability), leaves in block+item leaves and block/mineable/hoe, saplings in
    block+item saplings
  - lang fragments (EN + real German) for all 120 ids under
    assets/copper_inferno/lang/fragments{,_de}/scorchwood.json (merged later by
    devtools/merge_lang.py)

The unique-input recipe rule holds because every crafting input is a mod-unique
planks/log/slab id (or the mod-only <w>_logs tag); stonecutting recipes are not
crafting-grid recipes and cannot collide.
"""

from pathlib import Path

import lib_gen as lib

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / lib.NS
DATA = RES / "data" / lib.NS
TAGFRAG = ROOT / "devtools" / "tagfrag" / "scorchwood.json"

WOODS = ["emberwood", "ashwillow", "cinderpine", "charoak",
         "glowbirch", "sootmaple", "duskthorn", "pyrewood"]

# ---------------------------------------------------------------------------
# 8 distinct palettes. Keys:
#   plank  = (base, light, seam)          fleck  = accent pixel color (or None)
#   bark   = (base, dark, light)          crack  = glowing bark crack (or None)
#   ring   = (light, dark)  log-end growth rings
#   strip  = (base, dark, light)          stripped log side / end palette
#   heart  = glowing log-end heart pixels (or None)
# ---------------------------------------------------------------------------
PALETTES = {
    "emberwood": {  # warm ember brown, orange-glow flecks and bark cracks
        "plank": ((0x6B, 0x46, 0x2E), (0x7C, 0x54, 0x38), (0x3E, 0x27, 0x19)),
        "fleck": (0xE2, 0x58, 0x22),
        "bark": ((0x4A, 0x30, 0x22), (0x33, 0x20, 0x16), (0x5E, 0x40, 0x2C)),
        "crack": ((0xD9, 0x5B, 0x23), (0xF5, 0x8A, 0x2E)),
        "ring": ((0x77, 0x50, 0x35), (0x5E, 0x3E, 0x29)),
        "strip": ((0x8A, 0x60, 0x40), (0x74, 0x4F, 0x34), (0x9C, 0x70, 0x4C)),
        "heart": (0xF5, 0x8A, 0x2E),
    },
    "ashwillow": {  # pale ashen taupe, no glow
        "plank": ((0x8F, 0x85, 0x78), (0xA1, 0x97, 0x89), (0x5C, 0x54, 0x4A)),
        "fleck": None,
        "bark": ((0x6E, 0x66, 0x5C), (0x54, 0x4D, 0x45), (0x84, 0x7B, 0x6F)),
        "crack": None,
        "ring": ((0x9C, 0x92, 0x84), (0x80, 0x77, 0x6A)),
        "strip": ((0xA8, 0x9E, 0x8F), (0x92, 0x88, 0x7A), (0xBA, 0xB0, 0xA0)),
        "heart": None,
    },
    "cinderpine": {  # deep auburn red-brown, faint ember flecks
        "plank": ((0x6E, 0x3A, 0x2A), (0x80, 0x46, 0x33), (0x42, 0x20, 0x16)),
        "fleck": (0xC2, 0x45, 0x20),
        "bark": ((0x4E, 0x28, 0x1D), (0x38, 0x1B, 0x13), (0x62, 0x34, 0x25)),
        "crack": ((0xC2, 0x45, 0x20), (0xE0, 0x6A, 0x2C)),
        "ring": ((0x7C, 0x44, 0x31), (0x60, 0x32, 0x24)),
        "strip": ((0x8E, 0x50, 0x3A), (0x78, 0x41, 0x2E), (0xA0, 0x5E, 0x45)),
        "heart": None,
    },
    "charoak": {  # charcoal near-black
        "plank": ((0x3A, 0x33, 0x2E), (0x47, 0x3F, 0x39), (0x21, 0x1C, 0x18)),
        "fleck": None,
        "bark": ((0x2C, 0x26, 0x22), (0x1E, 0x1A, 0x16), (0x3B, 0x34, 0x2E)),
        "crack": ((0xD9, 0x5B, 0x23), (0xF5, 0x8A, 0x2E)),
        "ring": ((0x45, 0x3D, 0x36), (0x33, 0x2C, 0x26)),
        "strip": ((0x55, 0x4B, 0x42), (0x45, 0x3C, 0x34), (0x64, 0x59, 0x4E)),
        "heart": (0xD9, 0x5B, 0x23),
    },
    "glowbirch": {  # pale cream, teal glints (matches the teal fungus flora)
        "plank": ((0xC9, 0xBF, 0xA4), (0xD8, 0xCF, 0xB6), (0x8E, 0x84, 0x6C)),
        "fleck": (0x63, 0xD1, 0xBC),
        "bark": ((0xB4, 0xAC, 0x98), (0x8C, 0x84, 0x72), (0xCC, 0xC4, 0xB0)),
        "crack": ((0x2E, 0x9C, 0x8C), (0x63, 0xD1, 0xBC)),
        "ring": ((0xD2, 0xC8, 0xAE), (0xB2, 0xA8, 0x8E)),
        "strip": ((0xDA, 0xD0, 0xB8), (0xC4, 0xBA, 0xA2), (0xE8, 0xDF, 0xC8)),
        "heart": (0x63, 0xD1, 0xBC),
    },
    "sootmaple": {  # smoky mid gray-brown
        "plank": ((0x5C, 0x52, 0x48), (0x6B, 0x60, 0x55), (0x36, 0x2F, 0x28)),
        "fleck": None,
        "bark": ((0x45, 0x3D, 0x35), (0x31, 0x2B, 0x25), (0x57, 0x4E, 0x44)),
        "crack": None,
        "ring": ((0x69, 0x5E, 0x52), (0x52, 0x48, 0x3E)),
        "strip": ((0x76, 0x69, 0x5B), (0x63, 0x58, 0x4B), (0x88, 0x7A, 0x6A)),
        "heart": None,
    },
    "duskthorn": {  # dusky purple-gray
        "plank": ((0x52, 0x46, 0x5C), (0x60, 0x53, 0x6B), (0x30, 0x28, 0x38)),
        "fleck": (0x9A, 0x7A, 0xC8),
        "bark": ((0x3E, 0x34, 0x48), (0x2B, 0x23, 0x34), (0x4F, 0x43, 0x5B)),
        "crack": ((0x7A, 0x5C, 0xA8), (0x9A, 0x7A, 0xC8)),
        "ring": ((0x5E, 0x51, 0x6A), (0x48, 0x3D, 0x53)),
        "strip": ((0x6C, 0x5E, 0x7A), (0x5A, 0x4D, 0x67), (0x7E, 0x6F, 0x8D)),
        "heart": (0x9A, 0x7A, 0xC8),
    },
    "pyrewood": {  # bright amber-orange
        "plank": ((0x8A, 0x5A, 0x24), (0x9E, 0x6B, 0x2E), (0x59, 0x38, 0x14)),
        "fleck": (0xFF, 0xD8, 0x7A),
        "bark": ((0x6B, 0x42, 0x1B), (0x50, 0x30, 0x12), (0x80, 0x52, 0x24)),
        "crack": ((0xF5, 0x8A, 0x2E), (0xFF, 0xD8, 0x7A)),
        "ring": ((0x9A, 0x68, 0x2C), (0x7C, 0x50, 0x20)),
        "strip": ((0xAE, 0x7C, 0x3C), (0x96, 0x69, 0x30), (0xC2, 0x8E, 0x4B)),
        "heart": (0xFF, 0xD8, 0x7A),
    },
}

# Foliage palettes for the sapling/leaves textures: (dark, base, light) per wood,
# in the wood's scorched hue; the plank fleck doubles as a glow accent where set.
LEAF_PALETTES = {
    "emberwood": ((0x4E, 0x2A, 0x12), (0x6E, 0x3C, 0x18), (0x9A, 0x54, 0x1E)),
    "ashwillow": ((0x5C, 0x60, 0x52), (0x76, 0x7C, 0x6A), (0x93, 0x99, 0x84)),
    "cinderpine": ((0x4E, 0x22, 0x14), (0x6C, 0x30, 0x1C), (0x8A, 0x42, 0x24)),
    "charoak": ((0x26, 0x22, 0x1E), (0x3A, 0x34, 0x2E), (0x51, 0x49, 0x40)),
    "glowbirch": ((0x3E, 0x77, 0x66), (0x58, 0x9C, 0x88), (0x7F, 0xC4, 0xAE)),
    "sootmaple": ((0x4A, 0x42, 0x38), (0x60, 0x57, 0x4A), (0x79, 0x6E, 0x5E)),
    "duskthorn": ((0x3C, 0x2F, 0x4C), (0x52, 0x42, 0x66), (0x6E, 0x5A, 0x86)),
    "pyrewood": ((0x8A, 0x4A, 0x14), (0xAE, 0x62, 0x1C), (0xD2, 0x84, 0x2C)),
}

# Home Inferno biome per wood for the <w>_trees placed-feature injection (mirrors
# ScorchWoodFeature.init(): 4 woods in ember_grove, 4 in verdigris_jungle).
TREE_BIOMES = {
    "emberwood": "ember_grove", "cinderpine": "ember_grove",
    "charoak": "ember_grove", "pyrewood": "ember_grove",
    "ashwillow": "verdigris_jungle", "glowbirch": "verdigris_jungle",
    "sootmaple": "verdigris_jungle", "duskthorn": "verdigris_jungle",
}

# ---------------------------------------------------------------------------
# EN + real-German lang names, derived vanilla-style. tree = log compound stem
# (birch_log -> "Birkenstamm"); holz = wood/plank compound stem (birch_planks ->
# "Birkenholzbretter", oak_wood -> "Eichenholz").
# ---------------------------------------------------------------------------
EN_NAMES = {
    "emberwood": "Emberwood", "ashwillow": "Ashwillow", "cinderpine": "Cinderpine",
    "charoak": "Charoak", "glowbirch": "Glowbirch", "sootmaple": "Sootmaple",
    "duskthorn": "Duskthorn", "pyrewood": "Pyrewood",
}
DE_STEMS = {  # (tree stem, holz stem)
    "emberwood": ("Glutholz", "Glutholz"),
    "ashwillow": ("Aschenweiden", "Aschenweidenholz"),
    "cinderpine": ("Schlackenkiefern", "Schlackenkiefernholz"),
    "charoak": ("Kohleneichen", "Kohleneichenholz"),
    "glowbirch": ("Leuchtbirken", "Leuchtbirkenholz"),
    "sootmaple": ("Ru\u00dfahorn", "Ru\u00dfahornholz"),
    "duskthorn": ("D\u00e4mmerdorn", "D\u00e4mmerdornholz"),
    "pyrewood": ("Scheiterholz", "Scheiterholz"),
}


def set_ids(w: str) -> list[str]:
    """The 13 ids of one wood set, in WoodSets.registerWoodSet registration order."""
    return [f"{w}_planks", f"{w}_plank_slab", f"{w}_plank_stairs", f"{w}_fence",
            f"{w}_fence_gate", f"{w}_button", f"{w}_pressure_plate", f"{w}_log",
            f"stripped_{w}_log", f"{w}_wood", f"stripped_{w}_wood", f"{w}_mosaic",
            f"{w}_pillar"]


def tree_ids(w: str) -> list[str]:
    """The 2 survival-progression ids of one wood (ScorchWoodFeature.registerTreeContent)."""
    return [f"{w}_sapling", f"{w}_leaves"]


ALL_IDS = [bid for w in WOODS for bid in set_ids(w)]
assert len(ALL_IDS) == 104
ALL_TREE_IDS = [bid for w in WOODS for bid in tree_ids(w)]
assert len(ALL_TREE_IDS) == 16


# ---------------------------------------------------------------------------
# Extra texture painters (deterministic; lib_gen primitives cover the rest)
# ---------------------------------------------------------------------------


def make_stripped_side(name: str, base, dark, light):
    """Stripped log side: smooth wood with sparse vertical grain lines
    (infernoflora_gen.make_stripped, parameterized colors)."""
    from PIL import Image
    rng = lib.seeded(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    grain = {rng.randrange(16) for _ in range(4)}
    for x in range(16):
        for y in range(16):
            c = dark if x in grain else (light if x % 5 == 2 else base)
            if rng.random() < 0.04:
                c = lib._shade(c, -10)
            px[x, y] = lib._jitter(rng, c, 3)
    return img


def make_leaves(name: str, dark, base, light, fleck=None):
    """Leaves: RGBA foliage mottle with fully-transparent holes (CUTOUT_MIPPED, the
    vanilla leaves texture style) and optional glow flecks."""
    from PIL import Image
    rng = lib.seeded(name)
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(16):
        for x in range(16):
            r = rng.random()
            if r < 0.14:
                continue  # transparent hole
            if r < 0.32:
                c = dark
            elif r < 0.84:
                c = base
            else:
                c = light
            px[x, y] = (*lib._jitter(rng, c, 4), 255)
    if fleck is not None:
        for _ in range(3):
            px[rng.randrange(16), rng.randrange(16)] = (*fleck, 255)
    return img


def make_sapling(name: str, trunk, dark, base, light):
    """Sapling: transparent-background cross sprite — 2px trunk with a diamond leaf
    canopy (vanilla oak_sapling silhouette) from the wood's bark + leaf palettes."""
    from PIL import Image
    rng = lib.seeded(name)
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(9, 16):  # trunk
        for x in (7, 8):
            px[x, y] = (*lib._jitter(rng, trunk, 4), 255)
    for y in range(0, 12):  # canopy
        for x in range(2, 14):
            d = abs(x - 7.5) + abs(y - 5.5)
            if d <= 5.0 and rng.random() > 0.18:
                c = dark if d > 3.6 else (light if rng.random() < 0.22 else base)
                px[x, y] = (*lib._jitter(rng, c, 4), 255)
    return img


def make_mosaic(name: str, base, light, seam):
    """Mosaic: parquet of four 8x8 quadrants with alternating horizontal/vertical
    slats (bamboo-mosaic style) from the plank palette."""
    from PIL import Image
    rng = lib.seeded(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            horizontal = ((x // 8) + (y // 8)) % 2 == 0
            along, across = (x, y) if horizontal else (y, x)
            if across % 4 == 3 or across % 8 == 7:
                c = seam
            elif across % 4 == 0:
                c = light
            else:
                c = base
            if along % 8 == 7:  # slat end joints
                c = seam
            px[x, y] = lib._jitter(rng, c, 3)
    return img


def paint_wood(w: str) -> dict:
    """All 10 textures of one wood set ({texture name: Image})."""
    p = PALETTES[w]
    plank_base, plank_light, plank_seam = p["plank"]
    bark_base, bark_dark, bark_light = p["bark"]
    ring_light, ring_dark = p["ring"]
    strip_base, strip_dark, strip_light = p["strip"]
    crack, crack_bright = p["crack"] if p["crack"] else (None, None)
    fleck = p["fleck"]
    pillar_accents = [fleck] if fleck else [plank_light]
    leaf_dark, leaf_base, leaf_light = LEAF_PALETTES[w]
    return {
        f"{w}_sapling": make_sapling(f"{w}_sapling", bark_base,
                                     leaf_dark, leaf_base, leaf_light),
        f"{w}_leaves": make_leaves(f"{w}_leaves", leaf_dark, leaf_base, leaf_light,
                                   fleck=fleck),
        f"{w}_planks": lib.plank_grain(lib.seeded(f"{w}_planks"), plank_base, plank_light,
                                       plank_seam, fleck=fleck, fleck_count=2 if fleck else 0),
        f"{w}_log": lib.bark_side(lib.seeded(f"{w}_log"), bark_base, bark_dark, bark_light,
                                  crack=crack, crack_bright=crack_bright),
        f"{w}_log_top": lib.log_rings(lib.seeded(f"{w}_log_top"), ring_light, ring_dark,
                                      bark_dark, heart=p["heart"]),
        f"stripped_{w}_log": make_stripped_side(f"stripped_{w}_log",
                                                strip_base, strip_dark, strip_light),
        f"stripped_{w}_log_top": lib.log_rings(lib.seeded(f"stripped_{w}_log_top"),
                                               strip_light, strip_dark, strip_dark, heart=None),
        f"{w}_mosaic": make_mosaic(f"{w}_mosaic", plank_base, plank_light, plank_seam),
        f"{w}_pillar_side": lib.pillar_side(lib.seeded(f"{w}_pillar_side"),
                                            [plank_seam, plank_base, plank_light],
                                            plank_seam, pillar_accents, 0.15),
        f"{w}_pillar_top": lib.pillar_top(lib.seeded(f"{w}_pillar_top"),
                                          [plank_seam, plank_base, plank_base, plank_light],
                                          plank_seam, pillar_accents),
    }


# ---------------------------------------------------------------------------
# JSON emitters
# ---------------------------------------------------------------------------


def emit_column(name: str, end: str, side: str) -> dict:
    """Axis pillar with explicit end/side textures (vanilla oak_log/oak_wood format:
    same axis blockstate as lib_gen.emit_pillar, cube_column model)."""
    return lib.merge({
        lib._bs(name): {"variants": {
            "axis=x": {"model": lib.block_ref(name), "x": 90, "y": 90},
            "axis=y": {"model": lib.block_ref(name)},
            "axis=z": {"model": lib.block_ref(name), "x": 90},
        }},
        lib._bm(name): {
            "parent": "minecraft:block/cube_column",
            "textures": {"end": lib.block_ref(end), "side": lib.block_ref(side)},
        },
        lib._it(name): lib.item_def(lib.block_ref(name)),
    }, lib.loot_drop_self(name))


def _shears_or_silk_touch() -> dict:
    """The oak_leaves.json shears-or-silk-touch any_of condition (vanilla 1.21.9)."""
    return {
        "condition": "minecraft:any_of",
        "terms": [
            {"condition": "minecraft:match_tool",
             "predicate": {"items": "minecraft:shears"}},
            {"condition": "minecraft:match_tool",
             "predicate": {"predicates": {"minecraft:enchantments": [
                 {"enchantments": "minecraft:silk_touch", "levels": {"min": 1}}]}}},
        ],
    }


def loot_leaves(name: str, sapling: str) -> dict:
    """Vanilla leaves loot table (1.21.9 oak_leaves.json with the apple pool dropped —
    byte-identical to birch_leaves.json — and the leaves/sapling ids substituted):
    shears/silk touch drop the leaves, otherwise fortune-scaled sapling + stick drops."""
    return {lib._lt(name): {
        "type": "minecraft:block",
        "pools": [
            {
                "bonus_rolls": 0.0,
                "entries": [{
                    "type": "minecraft:alternatives",
                    "children": [
                        {
                            "type": "minecraft:item",
                            "conditions": [_shears_or_silk_touch()],
                            "name": f"{lib.NS}:{name}",
                        },
                        {
                            "type": "minecraft:item",
                            "conditions": [
                                {"condition": "minecraft:survives_explosion"},
                                {"chances": [0.05, 0.0625, 0.083333336, 0.1],
                                 "condition": "minecraft:table_bonus",
                                 "enchantment": "minecraft:fortune"},
                            ],
                            "name": f"{lib.NS}:{sapling}",
                        },
                    ],
                }],
                "rolls": 1.0,
            },
            {
                "bonus_rolls": 0.0,
                "conditions": [{
                    "condition": "minecraft:inverted",
                    "term": _shears_or_silk_touch(),
                }],
                "entries": [{
                    "type": "minecraft:item",
                    "conditions": [
                        {"chances": [0.02, 0.022222223, 0.025, 0.033333335, 0.1],
                         "condition": "minecraft:table_bonus",
                         "enchantment": "minecraft:fortune"},
                    ],
                    "functions": [
                        {"add": False,
                         "count": {"type": "minecraft:uniform", "max": 2.0, "min": 1.0},
                         "function": "minecraft:set_count"},
                        {"function": "minecraft:explosion_decay"},
                    ],
                    "name": "minecraft:stick",
                }],
                "rolls": 1.0,
            },
        ],
        "random_sequence": f"{lib.NS}:blocks/{name}",
    }}


def emit_sapling(name: str) -> dict:
    """Sapling: cross blockstate/model (vanilla oak_sapling format) + flat item sprite
    over the block texture + drop-self loot."""
    return lib.merge({
        lib._bs(name): {"variants": {"": {"model": lib.block_ref(name)}}},
        lib._bm(name): {"parent": "minecraft:block/cross",
                        "textures": {"cross": lib.block_ref(name)}},
        lib._im(name): {"parent": "minecraft:item/generated",
                        "textures": {"layer0": lib.block_ref(name)}},
        lib._it(name): lib.item_def(f"{lib.NS}:item/{name}"),
    }, lib.loot_drop_self(name))


def emit_leaves(name: str, sapling: str) -> dict:
    """Leaves: single-variant blockstate + vanilla leaves model (parent
    minecraft:block/leaves over an "all" texture, oak_leaves format; untinted so the
    item def references the block model directly) + the vanilla leaves loot table."""
    return lib.merge({
        lib._bs(name): {"variants": {"": {"model": lib.block_ref(name)}}},
        lib._bm(name): {"parent": "minecraft:block/leaves",
                        "textures": {"all": lib.block_ref(name)}},
        lib._it(name): lib.item_def(lib.block_ref(name)),
    }, loot_leaves(name, sapling))


def emit_wood_assets(w: str) -> dict:
    """Blockstates/models/items/loot for all 15 ids of one wood set."""
    planks = f"{w}_planks"
    return lib.merge(
        lib.emit_cube(planks),
        lib.emit_slab(f"{w}_plank_slab", planks),
        lib.emit_stairs(f"{w}_plank_stairs", planks),
        lib.emit_fence(f"{w}_fence", planks),
        lib.emit_fence_gate(f"{w}_fence_gate", planks),
        lib.emit_button(f"{w}_button", planks),
        lib.emit_pressure_plate(f"{w}_pressure_plate", planks),
        emit_column(f"{w}_log", f"{w}_log_top", f"{w}_log"),
        emit_column(f"stripped_{w}_log", f"stripped_{w}_log_top", f"stripped_{w}_log"),
        # wood/stripped-wood reuse the log side texture on all faces, like vanilla oak_wood
        emit_column(f"{w}_wood", f"{w}_log", f"{w}_log"),
        emit_column(f"stripped_{w}_wood", f"stripped_{w}_log", f"stripped_{w}_log"),
        lib.emit_cube(f"{w}_mosaic"),
        lib.emit_pillar(f"{w}_pillar"),
        emit_sapling(f"{w}_sapling"),
        emit_leaves(f"{w}_leaves", f"{w}_sapling"),
    )


def tree_worldgen(w: str) -> tuple[dict, dict]:
    """(configured_feature <w>_tree, placed_feature <w>_trees) for one wood.

    The configured feature copies the vanilla 1.21.9 oak.json structure (extracted from
    the client jar): straight_trunk_placer base 4 + rand 0-2 (4-6 tall),
    blob_foliage_placer radius 2, two_layers_feature_size, with the wood's own
    log/leaves as trunk/foliage providers and minecraft:dirt as dirt_provider.

    The placed feature uses count_on_every_layer (uniform 2-3) + biome — the layered
    nether-style placement the mod's patch_* placed features already use, because the
    Inferno dimension has a bedrock roof, so heightmap placement would target the
    ceiling instead of the cavern floors."""
    configured = {
        "type": "minecraft:tree",
        "config": {
            "decorators": [],
            "dirt_provider": {
                "type": "minecraft:simple_state_provider",
                "state": {"Name": "minecraft:dirt"},
            },
            "foliage_placer": {
                "type": "minecraft:blob_foliage_placer",
                "height": 3, "offset": 0, "radius": 2,
            },
            "foliage_provider": {
                "type": "minecraft:simple_state_provider",
                "state": {
                    "Name": f"{lib.NS}:{w}_leaves",
                    "Properties": {"distance": "7", "persistent": "false",
                                   "waterlogged": "false"},
                },
            },
            "force_dirt": False,
            "ignore_vines": True,
            "minimum_size": {
                "type": "minecraft:two_layers_feature_size",
                "limit": 1, "lower_size": 0, "upper_size": 1,
            },
            "trunk_placer": {
                "type": "minecraft:straight_trunk_placer",
                "base_height": 4, "height_rand_a": 2, "height_rand_b": 0,
            },
            "trunk_provider": {
                "type": "minecraft:simple_state_provider",
                "state": {"Name": f"{lib.NS}:{w}_log", "Properties": {"axis": "y"}},
            },
        },
    }
    placed = {
        "feature": f"{lib.NS}:{w}_tree",
        "placement": [
            {
                "count": {"type": "minecraft:uniform",
                          "max_inclusive": 3, "min_inclusive": 2},
                "type": "minecraft:count_on_every_layer",
            },
            {"type": "minecraft:biome"},
        ],
    }
    return configured, placed


def wood_recipes(w: str) -> dict:
    """12 recipes per wood ({recipe name: obj}); formats copied from the existing
    infernoflora/utilityblocks/decostone recipe JSONs."""
    ci = f"{lib.NS}:"
    planks, slab = ci + f"{w}_planks", ci + f"{w}_plank_slab"
    log, wood = ci + f"{w}_log", ci + f"{w}_wood"
    recipes = {
        f"{w}_planks": {
            "category": "building", "group": "planks",
            "ingredients": [f"#{lib.NS}:{w}_logs"],
            "result": {"count": 4, "id": planks},
            "type": "minecraft:crafting_shapeless",
        },
        f"{w}_plank_slab": {
            "category": "building", "group": "wooden_slab",
            "key": {"#": planks}, "pattern": ["###"],
            "result": {"count": 6, "id": slab},
            "type": "minecraft:crafting_shaped",
        },
        f"{w}_plank_stairs": {
            "category": "building", "group": "wooden_stairs",
            "key": {"#": planks}, "pattern": ["#  ", "## ", "###"],
            "result": {"count": 4, "id": ci + f"{w}_plank_stairs"},
            "type": "minecraft:crafting_shaped",
        },
        f"{w}_fence": {
            "category": "misc", "group": "wooden_fence",
            "key": {"#": "minecraft:stick", "W": planks}, "pattern": ["W#W", "W#W"],
            "result": {"count": 3, "id": ci + f"{w}_fence"},
            "type": "minecraft:crafting_shaped",
        },
        f"{w}_fence_gate": {
            "category": "redstone", "group": "wooden_fence_gate",
            "key": {"#": "minecraft:stick", "W": planks}, "pattern": ["#W#", "#W#"],
            "result": {"count": 1, "id": ci + f"{w}_fence_gate"},
            "type": "minecraft:crafting_shaped",
        },
        f"{w}_button": {
            "category": "redstone", "group": "wooden_button",
            "ingredients": [planks],
            "result": {"count": 1, "id": ci + f"{w}_button"},
            "type": "minecraft:crafting_shapeless",
        },
        f"{w}_pressure_plate": {
            "category": "redstone", "group": "wooden_pressure_plate",
            "key": {"#": planks}, "pattern": ["##"],
            "result": {"count": 1, "id": ci + f"{w}_pressure_plate"},
            "type": "minecraft:crafting_shaped",
        },
        f"{w}_wood": {
            "category": "building", "group": "bark",
            "key": {"#": log}, "pattern": ["##", "##"],
            "result": {"count": 3, "id": wood},
            "type": "minecraft:crafting_shaped",
        },
        # 4 slabs = 2 blocks of material -> 2 mosaic (material parity)
        f"{w}_mosaic": {
            "category": "building",
            "key": {"#": slab}, "pattern": ["##", "##"],
            "result": {"count": 2, "id": ci + f"{w}_mosaic"},
            "type": "minecraft:crafting_shaped",
        },
        f"{w}_pillar_from_{w}_planks_stonecutting": {
            "ingredient": planks,
            "result": {"count": 1, "id": ci + f"{w}_pillar"},
            "type": "minecraft:stonecutting",
        },
        f"stripped_{w}_log_from_{w}_log_stonecutting": {
            "ingredient": log,
            "result": {"count": 1, "id": ci + f"stripped_{w}_log"},
            "type": "minecraft:stonecutting",
        },
        f"stripped_{w}_wood_from_{w}_wood_stonecutting": {
            "ingredient": wood,
            "result": {"count": 1, "id": ci + f"stripped_{w}_wood"},
            "type": "minecraft:stonecutting",
        },
    }
    assert len(recipes) == 12
    return recipes


def tagfrag() -> dict:
    """devtools/tagfrag/scorchwood.json contents (merged by devtools/merge_tags.py)."""
    def ids(suffix: str) -> list[str]:
        return [f"{lib.NS}:{w}{suffix}" for w in WOODS]

    planks = ids("_planks")
    fences = ids("_fence")
    gates = ids("_fence_gate")
    buttons = ids("_button")
    plates = ids("_pressure_plate")
    slabs = ids("_plank_slab")
    stairs = ids("_plank_stairs")
    # All 4 log-family ids per wood: joining logs_that_burn gives charcoal smelting +
    # vanilla fire behavior; logs itself keeps leaf decay + tree logic consistent.
    logs = [f"{lib.NS}:{prefix}{w}{suffix}" for w in WOODS
            for prefix, suffix in (("", "_log"), ("stripped_", "_log"),
                                   ("", "_wood"), ("stripped_", "_wood"))]
    leaves = ids("_leaves")
    saplings = ids("_sapling")
    return {
        "block/planks": planks,
        "item/planks": planks,
        "block/wooden_fences": fences,
        "block/fences": fences,
        "block/fence_gates": gates,
        "block/buttons": buttons,
        "block/wooden_buttons": buttons,
        "block/pressure_plates": plates,
        "block/wooden_pressure_plates": plates,
        "block/wooden_slabs": slabs,
        "block/slabs": slabs,
        "block/wooden_stairs": stairs,
        "block/stairs": stairs,
        "block/logs": logs,
        "block/logs_that_burn": logs,
        "item/logs": logs,
        "item/logs_that_burn": logs,
        "block/leaves": leaves,
        "item/leaves": leaves,
        "block/saplings": saplings,
        "item/saplings": saplings,
        "block/mineable/axe": [f"{lib.NS}:{bid}" for bid in ALL_IDS],
        "block/mineable/hoe": leaves,
    }


def lang_names(w: str) -> tuple[dict, dict]:
    """(EN, DE) lang key dicts for one wood set."""
    en_w = EN_NAMES[w]
    tree, holz = DE_STEMS[w]
    en = {
        f"{w}_planks": f"{en_w} Planks",
        f"{w}_plank_slab": f"{en_w} Plank Slab",
        f"{w}_plank_stairs": f"{en_w} Plank Stairs",
        f"{w}_fence": f"{en_w} Fence",
        f"{w}_fence_gate": f"{en_w} Fence Gate",
        f"{w}_button": f"{en_w} Button",
        f"{w}_pressure_plate": f"{en_w} Pressure Plate",
        f"{w}_log": f"{en_w} Log",
        f"stripped_{w}_log": f"Stripped {en_w} Log",
        f"{w}_wood": f"{en_w} Wood",
        f"stripped_{w}_wood": f"Stripped {en_w} Wood",
        f"{w}_mosaic": f"{en_w} Mosaic",
        f"{w}_pillar": f"{en_w} Pillar",
        f"{w}_sapling": f"{en_w} Sapling",
        f"{w}_leaves": f"{en_w} Leaves",
    }
    de = {
        f"{w}_planks": f"{holz}bretter",
        f"{w}_plank_slab": f"{holz}stufe",
        f"{w}_plank_stairs": f"{holz}treppe",
        f"{w}_fence": f"{holz}zaun",
        f"{w}_fence_gate": f"{holz}zauntor",
        f"{w}_button": f"{holz}knopf",
        f"{w}_pressure_plate": f"{holz}druckplatte",
        f"{w}_log": f"{tree}stamm",
        f"stripped_{w}_log": f"Entrindeter {tree}stamm",
        f"{w}_wood": holz,
        f"stripped_{w}_wood": f"Entrindetes {holz}",
        f"{w}_mosaic": f"{holz}mosaik",
        f"{w}_pillar": f"{holz}s\u00e4ule",
        # Vanilla-style tree-stem compounds: birch_sapling -> "Birkensetzling",
        # birch_leaves -> "Birkenlaub" (emberwood -> Glutholzsetzling / Glutholzlaub).
        f"{w}_sapling": f"{tree}setzling",
        f"{w}_leaves": f"{tree}laub",
    }
    return en, de


# ---------------------------------------------------------------------------
# main
# ---------------------------------------------------------------------------


def main() -> None:
    # textures
    texture_count = 0
    for w in WOODS:
        for name, img in paint_wood(w).items():
            path = ASSETS / "textures" / "block" / f"{name}.png"
            path.parent.mkdir(parents=True, exist_ok=True)
            img.save(path)
            texture_count += 1

    # blockstates + models + item defs + loot
    files = lib.merge(*[emit_wood_assets(w) for w in WOODS])
    blockstates = [p for p in files if "/blockstates/" in p]
    items = [p for p in files if f"assets/{lib.NS}/items/" in p]
    loot = [p for p in files if "/loot_table/" in p]
    assert len(blockstates) == len(items) == len(loot) == 120, \
        f"expected 120 ids, got {len(blockstates)}/{len(items)}/{len(loot)}"
    json_count = lib.write_files(files, RES)

    # recipes
    recipe_count = 0
    for w in WOODS:
        for name, obj in wood_recipes(w).items():
            lib.write_json(DATA / "recipe" / "scorchwood" / f"{name}.json", obj)
            recipe_count += 1
    assert recipe_count == 96

    # tree worldgen (configured + placed feature per wood; injected into the wood's
    # home biome by ScorchWoodFeature.registerTreeContent)
    worldgen_count = 0
    for w in WOODS:
        assert w in TREE_BIOMES
        configured, placed = tree_worldgen(w)
        lib.write_json(DATA / "worldgen" / "configured_feature" / f"{w}_tree.json", configured)
        lib.write_json(DATA / "worldgen" / "placed_feature" / f"{w}_trees.json", placed)
        worldgen_count += 2
    assert worldgen_count == 16

    # mod item tags: <w>_logs (format of the existing scorched_logs.json)
    for w in WOODS:
        lib.write_json(DATA / "tags" / "item" / f"{w}_logs.json", {"values": [
            f"{lib.NS}:{w}_log", f"{lib.NS}:stripped_{w}_log",
            f"{lib.NS}:{w}_wood", f"{lib.NS}:stripped_{w}_wood",
        ]})

    # vanilla-tag fragment
    lib.write_json(TAGFRAG, tagfrag())

    # lang fragments
    lang_en: dict[str, str] = {}
    lang_de: dict[str, str] = {}
    for w in WOODS:
        en, de = lang_names(w)
        lang_en.update({f"block.{lib.NS}.{k}": v for k, v in en.items()})
        lang_de.update({f"block.{lib.NS}.{k}": v for k, v in de.items()})
    assert len(lang_en) == len(lang_de) == 120
    lib.write_json(ASSETS / "lang" / "fragments" / "scorchwood.json", lang_en)
    lib.write_json(ASSETS / "lang" / "fragments_de" / "scorchwood.json", lang_de)

    print(f"scorchwood_gen: {texture_count} textures, {json_count} asset/loot JSONs, "
          f"{recipe_count} recipes, {worldgen_count} tree worldgen JSONs, "
          f"{len(WOODS)} log item tags, 1 tagfrag, 2 lang fragments "
          f"for {len(ALL_IDS) + len(ALL_TREE_IDS)} block ids across {len(WOODS)} woods")


if __name__ == "__main__":
    main()
