#!/usr/bin/env python3
"""Idempotent asset/data generator for the COPPER INFERNO 1 "copperdeco" feature.

Emits, under src/main/resources, everything the 28 NON-oxidizing decorative copper
blocks need: blockstates, block models, item model-definitions (assets/<ns>/items/),
drop-self loot tables, crafting + stonecutting recipes and BOTH lang fragments
(EN fragments/copperdeco.json + DE fragments_de/copperdeco.json), plus the
16x16 PNG textures (Pillow), all fully deterministic (safe to re-run).

Vanilla JSON structures are copied 1:1 from the 1.21.9 client jar
(~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar): cube_all / cube_column
models, the basalt axis blockstate (single model rotated x90/y90|x90), the
cut_copper slab/stairs sets, stone_brick_wall multipart set, wall_inventory,
block loot tables (incl. the slab double-drop variant) and the shaped/shapeless/
stonecutting recipe formats (e.g. cut_copper_from_copper_block_stonecutting).

NOTE: JSON emission is legacy scaffolding (opt-in via --write-json); the JSON in
src/main/resources is authoritative — by default this script writes ONLY PNGs.

Run: python3 devtools/gen/copperdeco_gen.py [--write-json]
"""

import json
import os
import random
import sys

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> textures/*.png only

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
ASSETS = os.path.join(ROOT, "src", "main", "resources", "assets", "copper_inferno")
NS = "copper_inferno"

# ---------------------------------------------------------------------------
# Palettes (spec): rose #E0734D warm pink-copper, burnished bright polished
# #F0915E, gilded copper + gold flecks, verdigris oxidized green #6FB08E/#57A07B.
# Each palette: (BASE, LIGHT, MID, DARK).
ROSE = ((224, 115, 77), (240, 154, 133), (196, 86, 72), (150, 55, 50))
BURNISHED = ((240, 145, 94), (255, 181, 132), (213, 117, 69), (168, 84, 48))
COPPER = ((224, 115, 77), (238, 148, 110), (193, 90, 59), (143, 61, 38))
VERDIGRIS = ((111, 176, 142), (143, 199, 166), (87, 160, 123), (63, 122, 92))
GOLD = (245, 200, 66)        # gilded fleck
GOLD_LIGHT = (255, 224, 130)  # gilded fleck highlight

# ---------------------------------------------------------------------------
# EXACT vanilla templates (extracted from the 1.21.9 client jar, see module doc).

# assets/minecraft/blockstates/cut_copper_stairs.json
STAIRS_BLOCKSTATE_TEMPLATE = """
{
  "variants": {
    "facing=east,half=bottom,shape=inner_left": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "y": 270
    },
    "facing=east,half=bottom,shape=inner_right": {
      "model": "%STAIRS%_inner"
    },
    "facing=east,half=bottom,shape=outer_left": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "y": 270
    },
    "facing=east,half=bottom,shape=outer_right": {
      "model": "%STAIRS%_outer"
    },
    "facing=east,half=bottom,shape=straight": {
      "model": "%STAIRS%"
    },
    "facing=east,half=top,shape=inner_left": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "x": 180
    },
    "facing=east,half=top,shape=inner_right": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=east,half=top,shape=outer_left": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "x": 180
    },
    "facing=east,half=top,shape=outer_right": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=east,half=top,shape=straight": {
      "model": "%STAIRS%",
      "uvlock": true,
      "x": 180
    },
    "facing=north,half=bottom,shape=inner_left": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "y": 180
    },
    "facing=north,half=bottom,shape=inner_right": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "y": 270
    },
    "facing=north,half=bottom,shape=outer_left": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "y": 180
    },
    "facing=north,half=bottom,shape=outer_right": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "y": 270
    },
    "facing=north,half=bottom,shape=straight": {
      "model": "%STAIRS%",
      "uvlock": true,
      "y": 270
    },
    "facing=north,half=top,shape=inner_left": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=north,half=top,shape=inner_right": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "x": 180
    },
    "facing=north,half=top,shape=outer_left": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=north,half=top,shape=outer_right": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "x": 180
    },
    "facing=north,half=top,shape=straight": {
      "model": "%STAIRS%",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=south,half=bottom,shape=inner_left": {
      "model": "%STAIRS%_inner"
    },
    "facing=south,half=bottom,shape=inner_right": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "y": 90
    },
    "facing=south,half=bottom,shape=outer_left": {
      "model": "%STAIRS%_outer"
    },
    "facing=south,half=bottom,shape=outer_right": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "y": 90
    },
    "facing=south,half=bottom,shape=straight": {
      "model": "%STAIRS%",
      "uvlock": true,
      "y": 90
    },
    "facing=south,half=top,shape=inner_left": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=south,half=top,shape=inner_right": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "x": 180,
      "y": 180
    },
    "facing=south,half=top,shape=outer_left": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=south,half=top,shape=outer_right": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "x": 180,
      "y": 180
    },
    "facing=south,half=top,shape=straight": {
      "model": "%STAIRS%",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=west,half=bottom,shape=inner_left": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "y": 90
    },
    "facing=west,half=bottom,shape=inner_right": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "y": 180
    },
    "facing=west,half=bottom,shape=outer_left": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "y": 90
    },
    "facing=west,half=bottom,shape=outer_right": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "y": 180
    },
    "facing=west,half=bottom,shape=straight": {
      "model": "%STAIRS%",
      "uvlock": true,
      "y": 180
    },
    "facing=west,half=top,shape=inner_left": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "x": 180,
      "y": 180
    },
    "facing=west,half=top,shape=inner_right": {
      "model": "%STAIRS%_inner",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=west,half=top,shape=outer_left": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "x": 180,
      "y": 180
    },
    "facing=west,half=top,shape=outer_right": {
      "model": "%STAIRS%_outer",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=west,half=top,shape=straight": {
      "model": "%STAIRS%",
      "uvlock": true,
      "x": 180,
      "y": 180
    }
  }
}
"""

# assets/minecraft/blockstates/stone_brick_wall.json
WALL_BLOCKSTATE_TEMPLATE = """
{
  "multipart": [
    {
      "apply": {
        "model": "%WALL%_post"
      },
      "when": {
        "up": "true"
      }
    },
    {
      "apply": {
        "model": "%WALL%_side",
        "uvlock": true
      },
      "when": {
        "north": "low"
      }
    },
    {
      "apply": {
        "model": "%WALL%_side",
        "uvlock": true,
        "y": 90
      },
      "when": {
        "east": "low"
      }
    },
    {
      "apply": {
        "model": "%WALL%_side",
        "uvlock": true,
        "y": 180
      },
      "when": {
        "south": "low"
      }
    },
    {
      "apply": {
        "model": "%WALL%_side",
        "uvlock": true,
        "y": 270
      },
      "when": {
        "west": "low"
      }
    },
    {
      "apply": {
        "model": "%WALL%_side_tall",
        "uvlock": true
      },
      "when": {
        "north": "tall"
      }
    },
    {
      "apply": {
        "model": "%WALL%_side_tall",
        "uvlock": true,
        "y": 90
      },
      "when": {
        "east": "tall"
      }
    },
    {
      "apply": {
        "model": "%WALL%_side_tall",
        "uvlock": true,
        "y": 180
      },
      "when": {
        "south": "tall"
      }
    },
    {
      "apply": {
        "model": "%WALL%_side_tall",
        "uvlock": true,
        "y": 270
      },
      "when": {
        "west": "tall"
      }
    }
  ]
}
"""


def write_json(relpath, obj):
    if not WRITE_JSON:
        return
    path = os.path.join(ROOT, "src", "main", "resources", relpath)
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as f:
        json.dump(obj, f, indent=2, sort_keys=True)
        f.write("\n")


def blockstate(name, obj):
    write_json(os.path.join("assets", NS, "blockstates", name + ".json"), obj)


def block_model(name, obj):
    write_json(os.path.join("assets", NS, "models", "block", name + ".json"), obj)


def item_definition(item_id, model):
    """assets/<ns>/items/<id>.json — 1.21.9 item model definition (vanilla format)."""
    write_json(os.path.join("assets", NS, "items", item_id + ".json"),
               {"model": {"type": "minecraft:model", "model": model}})


def loot_self(block_id):
    """Drop-self loot table (vanilla cut_copper format)."""
    write_json(os.path.join("data", NS, "loot_table", "blocks", block_id + ".json"), {
        "type": "minecraft:block",
        "pools": [
            {
                "bonus_rolls": 0.0,
                "conditions": [{"condition": "minecraft:survives_explosion"}],
                "entries": [{"type": "minecraft:item", "name": f"{NS}:{block_id}"}],
                "rolls": 1.0,
            }
        ],
        "random_sequence": f"{NS}:blocks/{block_id}",
    })


def loot_slab(block_id):
    """Slab loot table (vanilla cut_copper_slab format: double slab drops 2)."""
    write_json(os.path.join("data", NS, "loot_table", "blocks", block_id + ".json"), {
        "type": "minecraft:block",
        "pools": [
            {
                "bonus_rolls": 0.0,
                "entries": [
                    {
                        "type": "minecraft:item",
                        "functions": [
                            {
                                "add": False,
                                "conditions": [
                                    {
                                        "block": f"{NS}:{block_id}",
                                        "condition": "minecraft:block_state_property",
                                        "properties": {"type": "double"},
                                    }
                                ],
                                "count": 2.0,
                                "function": "minecraft:set_count",
                            },
                            {"function": "minecraft:explosion_decay"},
                        ],
                        "name": f"{NS}:{block_id}",
                    }
                ],
                "rolls": 1.0,
            }
        ],
        "random_sequence": f"{NS}:blocks/{block_id}",
    })


def recipe(name, obj):
    write_json(os.path.join("data", NS, "recipe", "copperdeco", name + ".json"), obj)


def shaped(result_id, count, key, pattern, category="building"):
    return {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": result_id},
    }


def shapeless(result_id, count, ingredients, category="building", group=None):
    obj = {"type": "minecraft:crafting_shapeless", "category": category}
    if group:
        obj["group"] = group
    obj["ingredients"] = ingredients
    obj["result"] = {"count": count, "id": result_id}
    return obj


def stonecutting(result_id, count, ingredient):
    """Vanilla cut_copper_from_copper_block_stonecutting format."""
    return {
        "type": "minecraft:stonecutting",
        "ingredient": ingredient,
        "result": {"count": count, "id": result_id},
    }


# ---------------------------------------------------------------------------
# Texture painting (all deterministic; seeded RNG only).

def new_canvas():
    return Image.new("RGBA", (16, 16))


def save_texture(name, img):
    path = os.path.join(ASSETS, "textures", "block", name + ".png")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    img.save(path)


def paint_bricks(palette, seed, flecks=None):
    """Classic running-bond masonry: 4px courses of 8px bricks with 1px dark
    mortar, a light top bevel per brick and sparse tarnish noise. Clearly
    distinct from decostone's large 8x8 cut panels. Optional flecks=(c1, c2)
    sprinkles metallic flecks into the brick faces (used for gilded)."""
    base, light, mid, dark = palette
    img = new_canvas()
    px = img.load()
    rng = random.Random(seed)
    for y in range(16):
        row = y // 4
        ly = y % 4
        off = 4 * (row % 2)
        for x in range(16):
            lx = (x + off) % 8
            if ly == 3 or lx == 7:
                c = dark  # mortar joint
            elif ly == 0:
                c = light  # top bevel catching the light
            elif lx == 6:
                c = mid  # shadowed right brick edge
            else:
                c = base
                if rng.random() < 0.08:
                    c = mid
            px[x, y] = c + (255,)
    if flecks:
        c1, c2 = flecks
        rng = random.Random(seed + 1)
        placed = 0
        while placed < 14:
            x, y = rng.randrange(16), rng.randrange(16)
            row = y // 4
            lx = (x + 4 * (row % 2)) % 8
            if y % 4 == 3 or lx == 7:
                continue  # keep flecks off the mortar
            px[x, y] = (c1 if rng.random() < 0.6 else c2) + (255,)
            placed += 1
    return img


def texture_rose_copper_bricks():
    return paint_bricks(ROSE, 5001)


def texture_burnished_copper_bricks():
    return paint_bricks(BURNISHED, 5002)


def texture_gilded_copper_bricks():
    return paint_bricks(COPPER, 5003, flecks=(GOLD, GOLD_LIGHT))


def texture_verdigris_bricks():
    return paint_bricks(VERDIGRIS, 5004)


def texture_burnished_copper():
    """Bright polished plate: smooth field with two diagonal sheen streaks and
    a 1px shaded frame — reads as a mirror-finish block."""
    base, light, mid, dark = BURNISHED
    img = new_canvas()
    px = img.load()
    rng = random.Random(6001)
    for y in range(16):
        for x in range(16):
            edge = min(x, y, 15 - x, 15 - y)
            d = (x + y) % 16
            if edge == 0:
                c = mid if x + y < 16 else dark  # frame, darker bottom-right
            elif d in (4, 5):
                c = light  # main sheen streak
            elif d == 6:
                c = base
            elif d in (11, 12):
                c = light  # secondary streak
            elif d == 13:
                c = mid
            else:
                c = base
                if rng.random() < 0.04:
                    c = mid
            px[x, y] = c + (255,)
    return img


def texture_copper_panels():
    """Riveted sheet metal: two 8px vertical panels with dark seams, a light
    left bevel, a shadowed right edge and rivet dots down the seams."""
    base, light, mid, dark = COPPER
    img = new_canvas()
    px = img.load()
    rng = random.Random(6002)
    for y in range(16):
        for x in range(16):
            lx = x % 8
            if lx == 0 or y in (0, 8):
                c = dark  # panel seams (vertical + two horizontal courses)
            elif lx == 1 or y in (1, 9):
                c = light  # bevel along the seam
            elif lx == 7 or y in (7, 15):
                c = mid  # shadowed panel edge
            else:
                c = base
                if rng.random() < 0.05:
                    c = mid
            px[x, y] = c + (255,)
    # Rivets inside each panel quadrant corner.
    for ry in (3, 12):
        for rx in (3, 12):
            px[rx, ry] = light + (255,)
            px[rx + 1, ry + 1] = dark + (255,)
    return img


def texture_chiseled_rose_copper_bricks():
    """Chiseled ornament: dark outer frame with a lit bevel, a recessed square
    ring groove and a raised faceted centre boss — square motif, distinct from
    decostone's diamond lozenge."""
    base, light, mid, dark = ROSE
    img = new_canvas()
    px = img.load()
    for y in range(16):
        for x in range(16):
            edge = min(x, y, 15 - x, 15 - y)
            if edge == 0:
                c = dark  # outer frame
            elif edge == 1:
                c = light if x + y < 16 else mid  # frame bevel, lit top-left
            elif edge in (2, 3):
                c = base  # flat field
            elif edge == 4:
                c = dark  # recessed square ring groove
            elif edge == 5:
                c = mid  # groove shadow
            else:
                c = light if x + y <= 15 else base  # raised faceted boss
            px[x, y] = c + (255,)
    return img


def paint_pillar_side(palette):
    """Vertical fluting stripes with dark edges (per-palette recolor)."""
    base, light, mid, dark = palette
    img = new_canvas()
    px = img.load()
    for y in range(16):
        for x in range(16):
            if x in (0, 15) or y in (0, 15):
                c = dark
            elif x in (1, 14):
                c = mid
            elif x % 4 == 3:
                c = mid
            elif x % 4 == 1:
                c = light
            else:
                c = base
            px[x, y] = c + (255,)
    return img


def paint_pillar_top(palette):
    """Ringed cap: beveled rim, flat band, recessed ring groove and a domed
    centre boss (per-palette recolor)."""
    base, light, mid, dark = palette
    img = new_canvas()
    px = img.load()
    for y in range(16):
        for x in range(16):
            e = min(x, y, 15 - x, 15 - y)
            if e == 0:
                c = dark  # outer edge
            elif e == 1:
                c = light if x + y < 16 else mid  # rim bevel, lit top-left
            elif e in (2, 3):
                c = base  # flat band
            elif e == 4:
                c = dark  # recessed ring groove
            elif e == 5:
                c = mid  # groove shadow
            else:
                c = base
            px[x, y] = c + (255,)
    # Domed centre boss (4x4) with a top-left facet.
    for y in range(6, 10):
        for x in range(6, 10):
            px[x, y] = (light if x + y <= 15 else mid) + (255,)
    return img


# ---------------------------------------------------------------------------
# Family / single emission.

def emit_cube_family(block_name, stem):
    """Base cube + slab + stairs + wall, all textured with block/<block_name>."""
    tex = f"{NS}:block/{block_name}"
    base_model = f"{NS}:block/{block_name}"
    slab_model = f"{NS}:block/{stem}_slab"
    stairs_model = f"{NS}:block/{stem}_stairs"
    wall_model = f"{NS}:block/{stem}_wall"

    # Base cube (vanilla cut_copper format).
    blockstate(block_name, {"variants": {"": {"model": base_model}}})
    block_model(block_name, {"parent": "minecraft:block/cube_all", "textures": {"all": tex}})
    item_definition(block_name, base_model)
    loot_self(block_name)

    # Slab (vanilla cut_copper_slab format).
    blockstate(f"{stem}_slab", {"variants": {
        "type=bottom": {"model": slab_model},
        "type=double": {"model": base_model},
        "type=top": {"model": slab_model + "_top"},
    }})
    block_model(f"{stem}_slab", {"parent": "minecraft:block/slab",
                                 "textures": {"bottom": tex, "side": tex, "top": tex}})
    block_model(f"{stem}_slab_top", {"parent": "minecraft:block/slab_top",
                                     "textures": {"bottom": tex, "side": tex, "top": tex}})
    item_definition(f"{stem}_slab", slab_model)
    loot_slab(f"{stem}_slab")

    # Stairs (vanilla cut_copper_stairs format).
    blockstate(f"{stem}_stairs",
               json.loads(STAIRS_BLOCKSTATE_TEMPLATE.replace("%STAIRS%", stairs_model)))
    block_model(f"{stem}_stairs", {"parent": "minecraft:block/stairs",
                                   "textures": {"bottom": tex, "side": tex, "top": tex}})
    block_model(f"{stem}_stairs_inner", {"parent": "minecraft:block/inner_stairs",
                                         "textures": {"bottom": tex, "side": tex, "top": tex}})
    block_model(f"{stem}_stairs_outer", {"parent": "minecraft:block/outer_stairs",
                                         "textures": {"bottom": tex, "side": tex, "top": tex}})
    item_definition(f"{stem}_stairs", stairs_model)
    loot_self(f"{stem}_stairs")

    # Wall (vanilla stone_brick_wall format).
    blockstate(f"{stem}_wall",
               json.loads(WALL_BLOCKSTATE_TEMPLATE.replace("%WALL%", wall_model)))
    block_model(f"{stem}_wall_post", {"parent": "minecraft:block/template_wall_post",
                                      "textures": {"wall": tex}})
    block_model(f"{stem}_wall_side", {"parent": "minecraft:block/template_wall_side",
                                      "textures": {"wall": tex}})
    block_model(f"{stem}_wall_side_tall", {"parent": "minecraft:block/template_wall_side_tall",
                                           "textures": {"wall": tex}})
    block_model(f"{stem}_wall_inventory", {"parent": "minecraft:block/wall_inventory",
                                           "textures": {"wall": tex}})
    item_definition(f"{stem}_wall", wall_model + "_inventory")
    loot_self(f"{stem}_wall")

    # Slab / stairs / wall recipes from the family cube (vanilla counts & categories).
    cube = f"{NS}:{block_name}"
    recipe(f"{stem}_slab", shaped(f"{NS}:{stem}_slab", 6, {"#": cube}, ["###"]))
    recipe(f"{stem}_stairs", shaped(f"{NS}:{stem}_stairs", 4, {"#": cube}, ["#  ", "## ", "###"]))
    recipe(f"{stem}_wall", shaped(f"{NS}:{stem}_wall", 6, {"#": cube}, ["###", "###"], category="misc"))


def emit_simple_cube(block_name):
    """Single full cube block: blockstate + cube_all model + item + drop-self loot."""
    model = f"{NS}:block/{block_name}"
    blockstate(block_name, {"variants": {"": {"model": model}}})
    block_model(block_name, {"parent": "minecraft:block/cube_all",
                             "textures": {"all": model}})
    item_definition(block_name, model)
    loot_self(block_name)


def emit_pillar(block_name):
    """Axis pillar in the vanilla basalt format: ONE cube_column model reused
    for all three axes via x/y rotations (x90+y90 for axis=x, x90 for axis=z)."""
    model = f"{NS}:block/{block_name}"
    blockstate(block_name, {"variants": {
        "axis=x": {"model": model, "x": 90, "y": 90},
        "axis=y": {"model": model},
        "axis=z": {"model": model, "x": 90},
    }})
    block_model(block_name, {"parent": "minecraft:block/cube_column", "textures": {
        "end": f"{NS}:block/{block_name}_top", "side": f"{NS}:block/{block_name}_side"}})
    item_definition(block_name, model)
    loot_self(block_name)


# ---------------------------------------------------------------------------
# Lang fragments (EN + DE, same keys).

NAMES = [
    # (id, EN, DE)
    ("rose_copper_bricks", "Rose Copper Bricks", "Ros\u00e9kupferziegel"),
    ("rose_copper_brick_slab", "Rose Copper Brick Slab", "Ros\u00e9kupferziegelstufe"),
    ("rose_copper_brick_stairs", "Rose Copper Brick Stairs", "Ros\u00e9kupferziegeltreppe"),
    ("rose_copper_brick_wall", "Rose Copper Brick Wall", "Ros\u00e9kupferziegelmauer"),
    ("burnished_copper", "Burnished Copper", "Poliertes Kupfer"),
    ("burnished_copper_slab", "Burnished Copper Slab", "Polierte Kupferstufe"),
    ("burnished_copper_stairs", "Burnished Copper Stairs", "Polierte Kupfertreppe"),
    ("burnished_copper_wall", "Burnished Copper Wall", "Polierte Kupfermauer"),
    ("burnished_copper_bricks", "Burnished Copper Bricks", "Polierte Kupferziegel"),
    ("burnished_copper_brick_slab", "Burnished Copper Brick Slab", "Polierte Kupferziegelstufe"),
    ("burnished_copper_brick_stairs", "Burnished Copper Brick Stairs", "Polierte Kupferziegeltreppe"),
    ("burnished_copper_brick_wall", "Burnished Copper Brick Wall", "Polierte Kupferziegelmauer"),
    ("copper_panels", "Copper Panels", "Kupferpaneele"),
    ("copper_panel_slab", "Copper Panel Slab", "Kupferpaneelstufe"),
    ("copper_panel_stairs", "Copper Panel Stairs", "Kupferpaneeltreppe"),
    ("copper_panel_wall", "Copper Panel Wall", "Kupferpaneelmauer"),
    ("gilded_copper_bricks", "Gilded Copper Bricks", "Vergoldete Kupferziegel"),
    ("gilded_copper_brick_slab", "Gilded Copper Brick Slab", "Vergoldete Kupferziegelstufe"),
    ("gilded_copper_brick_stairs", "Gilded Copper Brick Stairs", "Vergoldete Kupferziegeltreppe"),
    ("gilded_copper_brick_wall", "Gilded Copper Brick Wall", "Vergoldete Kupferziegelmauer"),
    ("verdigris_bricks", "Verdigris Bricks", "Gr\u00fcnspanziegel"),
    ("verdigris_brick_slab", "Verdigris Brick Slab", "Gr\u00fcnspanziegelstufe"),
    ("verdigris_brick_stairs", "Verdigris Brick Stairs", "Gr\u00fcnspanziegeltreppe"),
    ("verdigris_brick_wall", "Verdigris Brick Wall", "Gr\u00fcnspanziegelmauer"),
    ("chiseled_rose_copper_bricks", "Chiseled Rose Copper Bricks", "Gemei\u00dfelte Ros\u00e9kupferziegel"),
    ("rose_copper_pillar", "Rose Copper Pillar", "Ros\u00e9kupfers\u00e4ule"),
    ("burnished_copper_pillar", "Burnished Copper Pillar", "Polierte Kupfers\u00e4ule"),
    ("verdigris_pillar", "Verdigris Pillar", "Gr\u00fcnspans\u00e4ule"),
]


def emit_lang():
    write_json(os.path.join("assets", NS, "lang", "fragments", "copperdeco.json"),
               {f"block.{NS}.{bid}": en for bid, en, _de in NAMES})
    write_json(os.path.join("assets", NS, "lang", "fragments_de", "copperdeco.json"),
               {f"block.{NS}.{bid}": de for bid, _en, de in NAMES})


def main():
    # --- textures (13) --------------------------------------------------------
    save_texture("rose_copper_bricks", texture_rose_copper_bricks())
    save_texture("burnished_copper", texture_burnished_copper())
    save_texture("burnished_copper_bricks", texture_burnished_copper_bricks())
    save_texture("copper_panels", texture_copper_panels())
    save_texture("gilded_copper_bricks", texture_gilded_copper_bricks())
    save_texture("verdigris_bricks", texture_verdigris_bricks())
    save_texture("chiseled_rose_copper_bricks", texture_chiseled_rose_copper_bricks())
    save_texture("rose_copper_pillar_side", paint_pillar_side(ROSE))
    save_texture("rose_copper_pillar_top", paint_pillar_top(ROSE))
    save_texture("burnished_copper_pillar_side", paint_pillar_side(BURNISHED))
    save_texture("burnished_copper_pillar_top", paint_pillar_top(BURNISHED))
    save_texture("verdigris_pillar_side", paint_pillar_side(VERDIGRIS))
    save_texture("verdigris_pillar_top", paint_pillar_top(VERDIGRIS))

    # --- families (4 blocks each; includes slab/stairs/wall recipes) ----------
    emit_cube_family("rose_copper_bricks", "rose_copper_brick")
    emit_cube_family("burnished_copper", "burnished_copper")
    emit_cube_family("burnished_copper_bricks", "burnished_copper_brick")
    emit_cube_family("copper_panels", "copper_panel")
    emit_cube_family("gilded_copper_bricks", "gilded_copper_brick")
    emit_cube_family("verdigris_bricks", "verdigris_brick")

    # --- singles ---------------------------------------------------------------
    emit_simple_cube("chiseled_rose_copper_bricks")
    emit_pillar("rose_copper_pillar")
    emit_pillar("burnished_copper_pillar")
    emit_pillar("verdigris_pillar")

    # --- feature entry recipes -------------------------------------------------
    # Collision dodging (verified against vanilla + mod via
    # devtools/check_recipe_collisions.py):
    #   2x2 cut_copper        -> decostone cut_copper_bricks (taken)
    #   2x2 copper_ingot      -> vanilla copper_trapdoor (taken)
    #   2x2 copper_block      -> vanilla cut_copper (taken)
    # So the tinted brick families use "4 cut copper + tint agent" shapeless
    # alloying, verdigris starts from vanilla oxidized_cut_copper, and the
    # plain shape variants (burnished/panels) are stonecutter-only.
    recipe("rose_copper_bricks", shapeless(
        f"{NS}:rose_copper_bricks", 4,
        ["minecraft:cut_copper", "minecraft:cut_copper", "minecraft:cut_copper",
         "minecraft:cut_copper", "minecraft:redstone"]))
    recipe("gilded_copper_bricks", shapeless(
        f"{NS}:gilded_copper_bricks", 4,
        ["minecraft:cut_copper", "minecraft:cut_copper", "minecraft:cut_copper",
         "minecraft:cut_copper", "minecraft:gold_nugget"]))
    recipe("verdigris_bricks", shaped(
        f"{NS}:verdigris_bricks", 4, {"#": "minecraft:oxidized_cut_copper"}, ["##", "##"]))
    recipe("burnished_copper_bricks", shaped(
        f"{NS}:burnished_copper_bricks", 4, {"#": f"{NS}:burnished_copper"}, ["##", "##"]))
    recipe("chiseled_rose_copper_bricks", shaped(
        f"{NS}:chiseled_rose_copper_bricks", 1, {"#": f"{NS}:rose_copper_brick_slab"}, ["#", "#"]))
    recipe("rose_copper_pillar", shaped(
        f"{NS}:rose_copper_pillar", 2, {"#": f"{NS}:rose_copper_bricks"}, ["#", "#"]))
    recipe("burnished_copper_pillar", shaped(
        f"{NS}:burnished_copper_pillar", 2, {"#": f"{NS}:burnished_copper"}, ["#", "#"]))
    recipe("verdigris_pillar", shaped(
        f"{NS}:verdigris_pillar", 2, {"#": f"{NS}:verdigris_bricks"}, ["#", "#"]))

    # --- stonecutting chains from vanilla copper_block / cut_copper -------------
    # (vanilla counts: copper_block -> 4 full blocks, cut_copper -> 1)
    recipe("burnished_copper_from_copper_block_stonecutting",
           stonecutting(f"{NS}:burnished_copper", 4, "minecraft:copper_block"))
    recipe("burnished_copper_from_cut_copper_stonecutting",
           stonecutting(f"{NS}:burnished_copper", 1, "minecraft:cut_copper"))
    recipe("copper_panels_from_copper_block_stonecutting",
           stonecutting(f"{NS}:copper_panels", 4, "minecraft:copper_block"))
    recipe("copper_panels_from_cut_copper_stonecutting",
           stonecutting(f"{NS}:copper_panels", 1, "minecraft:cut_copper"))
    recipe("burnished_copper_bricks_from_copper_block_stonecutting",
           stonecutting(f"{NS}:burnished_copper_bricks", 4, "minecraft:copper_block"))
    recipe("burnished_copper_bricks_from_burnished_copper_stonecutting",
           stonecutting(f"{NS}:burnished_copper_bricks", 1, f"{NS}:burnished_copper"))
    recipe("chiseled_rose_copper_bricks_from_rose_copper_bricks_stonecutting",
           stonecutting(f"{NS}:chiseled_rose_copper_bricks", 1, f"{NS}:rose_copper_bricks"))
    recipe("rose_copper_pillar_from_rose_copper_bricks_stonecutting",
           stonecutting(f"{NS}:rose_copper_pillar", 1, f"{NS}:rose_copper_bricks"))
    recipe("burnished_copper_pillar_from_burnished_copper_stonecutting",
           stonecutting(f"{NS}:burnished_copper_pillar", 1, f"{NS}:burnished_copper"))
    recipe("verdigris_pillar_from_verdigris_bricks_stonecutting",
           stonecutting(f"{NS}:verdigris_pillar", 1, f"{NS}:verdigris_bricks"))

    # --- lang fragments (EN + DE) ------------------------------------------------
    emit_lang()

    mode = "textures + JSON" if WRITE_JSON else "textures only (pass --write-json for legacy JSON)"
    print(f"copperdeco_gen: emitted {mode}.")


if __name__ == "__main__":
    main()
