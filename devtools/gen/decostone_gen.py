#!/usr/bin/env python3
"""Idempotent asset/data generator for the COPPER INFERNO 1 "decostone" feature.

Emits, under src/main/resources, everything the 19 static decorative copper-masonry
blocks need: blockstates, block models, item model-definitions (assets/<ns>/items/),
drop-self loot tables, crafting recipes and the decostone lang fragment, plus the
16x16 PNG textures (Pillow), all fully deterministic (safe to re-run).

Vanilla JSON structures are copied 1:1 from the 1.21.9 client jar
(~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar): cube_all / cube_column
models, basalt+quartz_pillar axis blockstate, cut_copper slab/stairs sets,
stone_brick_wall multipart set, wall_inventory, block loot tables (incl. the slab
double-drop variant) and the shaped/shapeless recipe formats.

Run: python3 devtools/gen/decostone_gen.py
"""

import json
import os
import random

from PIL import Image

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
ASSETS = os.path.join(ROOT, "src", "main", "resources", "assets", "copper_inferno")
DATA = os.path.join(ROOT, "src", "main", "resources", "data", "copper_inferno")
NS = "copper_inferno"

# ---------------------------------------------------------------------------
# Copper palette (spec) + moss accents.
BASE = (224, 115, 77)     # #E0734D
MID = (193, 90, 59)       # #C15A3B
DARK = (143, 61, 38)      # #8F3D26
LIGHT = (238, 148, 110)   # highlight derived from BASE
MOSS = (111, 176, 142)    # #6FB08E
MOSS_DARK = (87, 160, 123)  # #57A07B (dev-ref oxidized green)

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
    path = os.path.join(ROOT, "src", "main", "resources", relpath)
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as f:
        json.dump(obj, f, indent=2)
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
    """Drop-self loot table (vanilla cut_copper / dr_pepper_can_block format)."""
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
    write_json(os.path.join("data", NS, "recipe", "decostone", name + ".json"), obj)


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


# ---------------------------------------------------------------------------
# Texture painting (all deterministic; seeded RNG only).

def new_canvas():
    return Image.new("RGBA", (16, 16))


def save_texture(name, img):
    path = os.path.join(ASSETS, "textures", "block", name + ".png")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    img.save(path)


def paint_bricks(rng):
    """Fine brick bond: 4px-high courses of 8px bricks, half-brick offset per course."""
    img = new_canvas()
    px = img.load()
    for y in range(16):
        row = y // 4
        off = 4 * (row % 2)
        for x in range(16):
            if y % 4 == 3 or (x + off) % 8 == 0:
                px[x, y] = DARK + (255,)
            else:
                brick = ((x + off) // 8 + row) % 2
                c = BASE if brick == 0 else MID
                r = rng.random()
                if r < 0.08:
                    c = LIGHT
                elif r < 0.16:
                    c = MID if c == BASE else DARK
                px[x, y] = c + (255,)
    return img


def texture_cut_copper_bricks():
    return paint_bricks(random.Random(1001))


def texture_mossy_copper_bricks():
    img = paint_bricks(random.Random(1001))
    px = img.load()
    rng = random.Random(2002)
    for _ in range(7):
        cx, cy = rng.randrange(16), rng.randrange(16)
        for dx, dy in ((0, 0), (1, 0), (-1, 0), (0, 1), (0, -1), (1, 1)):
            if rng.random() < 0.8:
                x, y = (cx + dx) % 16, (cy + dy) % 16
                px[x, y] = (MOSS if rng.random() < 0.6 else MOSS_DARK) + (255,)
    return img


def texture_copper_mosaic():
    """Diagonal weave: diamond lattice of alternating tiles with dark seams."""
    img = new_canvas()
    px = img.load()
    rng = random.Random(3003)
    for y in range(16):
        for x in range(16):
            if (x + y) % 8 == 0 or (x - y) % 8 == 0:
                c = DARK
            else:
                band_a = ((x + y) // 8) % 2
                band_b = ((x - y + 32) // 8) % 2
                c = BASE if band_a ^ band_b else MID
                if rng.random() < 0.08:
                    c = LIGHT if c == BASE else DARK
            px[x, y] = c + (255,)
    return img


def texture_copper_shingles():
    """Scalloped rows: 4px courses of 8px shingles with a curved lower edge."""
    img = new_canvas()
    px = img.load()
    rng = random.Random(4004)
    edge = [1, 2, 3, 3, 3, 3, 2, 1]  # scallop bottom edge per local x
    for y in range(16):
        row = y // 4
        off = 4 * (row % 2)
        ly = y % 4
        for x in range(16):
            lx = (x + off) % 8
            shingle = ((x + off) // 8 + row) % 2
            c = BASE if shingle == 0 else LIGHT
            if ly == edge[lx]:
                c = DARK
            elif ly > edge[lx]:
                c = MID
            elif rng.random() < 0.06:
                c = MID
            px[x, y] = c + (255,)
    return img


def texture_chiseled_copper_bricks():
    """Framed inset: dark groove frame around a raised inner panel."""
    img = new_canvas()
    px = img.load()
    ring_colors = {0: MID, 1: DARK, 2: BASE, 3: BASE, 4: DARK, 5: MID, 6: LIGHT, 7: LIGHT}
    for y in range(16):
        for x in range(16):
            r = min(x, y, 15 - x, 15 - y)
            px[x, y] = ring_colors[r] + (255,)
    return img


def texture_carved_copper():
    """Relief spiral: square spiral groove with a light relief highlight."""
    img = new_canvas()
    px = img.load()
    for y in range(16):
        for x in range(16):
            px[x, y] = (BASE if (x + y) % 2 == 0 else MID) + (255,)
    # Square spiral path, inset by 2 each turn.
    path = []
    l, t, r, b = 1, 1, 14, 14
    while l <= r and t <= b:
        path += [(x, t) for x in range(l, r + 1)]
        path += [(r, y) for y in range(t + 1, b + 1)]
        if t != b:
            path += [(x, b) for x in range(r - 1, l - 1, -1)]
        if l != r and b - 1 >= t + 2:
            path += [(l, y) for y in range(b - 1, t + 1, -1)]
        l, t, r, b = l + 2, t + 2, r - 2, b - 2
    for x, y in path:
        px[x, y] = DARK + (255,)
    for x, y in path:
        if x + 1 < 16 and y + 1 < 16 and px[x + 1, y + 1][:3] != DARK:
            px[x + 1, y + 1] = LIGHT + (255,)
    return img


def texture_copper_pillar_side():
    """Vertical fluting stripes with dark edges and cap lines."""
    img = new_canvas()
    px = img.load()
    for y in range(16):
        for x in range(16):
            if x in (0, 15) or y in (0, 15):
                c = DARK
            elif x in (1, 14):
                c = MID
            elif x % 4 == 3:
                c = MID
            elif x % 4 == 1:
                c = LIGHT
            else:
                c = BASE
            px[x, y] = c + (255,)
    return img


def texture_copper_pillar_top():
    """Ring end: concentric square rings."""
    img = new_canvas()
    px = img.load()
    ring_colors = {0: DARK, 1: MID, 2: BASE, 3: DARK, 4: MID, 5: BASE, 6: LIGHT, 7: MID}
    for y in range(16):
        for x in range(16):
            r = min(x, y, 15 - x, 15 - y)
            px[x, y] = ring_colors[r] + (255,)
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

    # Base cube (vanilla stone_bricks format).
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


def emit_pillar():
    """copper_pillar: axis blockstate (vanilla quartz_pillar format) + column models."""
    model = f"{NS}:block/copper_pillar"
    blockstate("copper_pillar", {"variants": {
        "axis=x": {"model": model + "_horizontal", "x": 90, "y": 90},
        "axis=y": {"model": model},
        "axis=z": {"model": model + "_horizontal", "x": 90},
    }})
    block_model("copper_pillar", {"parent": "minecraft:block/cube_column", "textures": {
        "end": f"{NS}:block/copper_pillar_top", "side": f"{NS}:block/copper_pillar_side"}})
    block_model("copper_pillar_horizontal", {"parent": "minecraft:block/cube_column_horizontal",
                                             "textures": {"end": f"{NS}:block/copper_pillar_top",
                                                          "side": f"{NS}:block/copper_pillar_side"}})
    item_definition("copper_pillar", model)
    loot_self("copper_pillar")


def emit_lang():
    write_json(os.path.join("assets", NS, "lang", "fragments", "decostone.json"), {
        f"block.{NS}.cut_copper_bricks": "Cut Copper Bricks",
        f"block.{NS}.cut_copper_brick_slab": "Cut Copper Brick Slab",
        f"block.{NS}.cut_copper_brick_stairs": "Cut Copper Brick Stairs",
        f"block.{NS}.cut_copper_brick_wall": "Cut Copper Brick Wall",
        f"block.{NS}.mossy_copper_bricks": "Mossy Copper Bricks",
        f"block.{NS}.mossy_copper_brick_slab": "Mossy Copper Brick Slab",
        f"block.{NS}.mossy_copper_brick_stairs": "Mossy Copper Brick Stairs",
        f"block.{NS}.mossy_copper_brick_wall": "Mossy Copper Brick Wall",
        f"block.{NS}.copper_mosaic": "Copper Mosaic",
        f"block.{NS}.copper_mosaic_slab": "Copper Mosaic Slab",
        f"block.{NS}.copper_mosaic_stairs": "Copper Mosaic Stairs",
        f"block.{NS}.copper_mosaic_wall": "Copper Mosaic Wall",
        f"block.{NS}.copper_shingles": "Copper Shingles",
        f"block.{NS}.copper_shingle_slab": "Copper Shingle Slab",
        f"block.{NS}.copper_shingle_stairs": "Copper Shingle Stairs",
        f"block.{NS}.copper_shingle_wall": "Copper Shingle Wall",
        f"block.{NS}.chiseled_copper_bricks": "Chiseled Copper Bricks",
        f"block.{NS}.carved_copper": "Carved Copper",
        f"block.{NS}.copper_pillar": "Copper Pillar",
    })


def main():
    # --- textures -----------------------------------------------------------
    save_texture("cut_copper_bricks", texture_cut_copper_bricks())
    save_texture("mossy_copper_bricks", texture_mossy_copper_bricks())
    save_texture("copper_mosaic", texture_copper_mosaic())
    save_texture("copper_shingles", texture_copper_shingles())
    save_texture("chiseled_copper_bricks", texture_chiseled_copper_bricks())
    save_texture("carved_copper", texture_carved_copper())
    save_texture("copper_pillar_side", texture_copper_pillar_side())
    save_texture("copper_pillar_top", texture_copper_pillar_top())

    # --- families (4 blocks each) -------------------------------------------
    emit_cube_family("cut_copper_bricks", "cut_copper_brick")
    emit_cube_family("mossy_copper_bricks", "mossy_copper_brick")
    emit_cube_family("copper_mosaic", "copper_mosaic")
    emit_cube_family("copper_shingles", "copper_shingle")

    # --- singles --------------------------------------------------------------
    emit_simple_cube("chiseled_copper_bricks")
    emit_simple_cube("carved_copper")
    emit_pillar()

    # --- feature recipes (vanilla formats; only vanilla/own ids) --------------
    recipe("cut_copper_bricks", shaped(
        f"{NS}:cut_copper_bricks", 4, {"#": "minecraft:cut_copper"}, ["##", "##"]))
    recipe("mossy_copper_bricks_from_vine", shapeless(
        f"{NS}:mossy_copper_bricks", 1, [f"{NS}:cut_copper_bricks", "minecraft:vine"],
        group="mossy_copper_bricks"))
    recipe("mossy_copper_bricks_from_moss_block", shapeless(
        f"{NS}:mossy_copper_bricks", 1, [f"{NS}:cut_copper_bricks", "minecraft:moss_block"],
        group="mossy_copper_bricks"))
    recipe("copper_mosaic", shaped(
        f"{NS}:copper_mosaic", 4, {"#": f"{NS}:cut_copper_bricks"}, ["##", "##"]))
    recipe("copper_shingles", shaped(
        f"{NS}:copper_shingles", 6, {"#": "minecraft:copper_ingot"}, ["###", "###"]))
    recipe("chiseled_copper_bricks", shaped(
        f"{NS}:chiseled_copper_bricks", 1, {"#": f"{NS}:cut_copper_brick_slab"}, ["#", "#"]))
    recipe("carved_copper", shaped(
        f"{NS}:carved_copper", 4, {"#": f"{NS}:chiseled_copper_bricks"}, ["##", "##"]))
    recipe("copper_pillar", shaped(
        f"{NS}:copper_pillar", 3, {"#": "minecraft:cut_copper"}, ["#", "#", "#"]))

    # --- lang fragment --------------------------------------------------------
    emit_lang()

    print("decostone_gen: all decostone assets/data emitted.")


if __name__ == "__main__":
    main()
