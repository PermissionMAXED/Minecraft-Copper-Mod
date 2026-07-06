#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "inferno" feature (17 fire-themed stone blocks).

Idempotent: running it any number of times produces the same files. Emits, directly into
src/main/resources:
  - blockstates, block models, item model-definitions (assets/copper_inferno/...)
  - 16x16 block textures (Pillow, deterministic seeded noise)
  - drop-self loot tables (data/copper_inferno/loot_table/blocks/...)
  - recipes (data/copper_inferno/recipe/inferno/...)
  - lang fragment (assets/copper_inferno/lang/fragments/inferno.json)

Blockstate/model/recipe JSON structures are exact copies of the vanilla 1.21.9 formats
(extracted from fabric-loom's minecraft-client.jar: stone_bricks family, basalt pillar,
cracked_stone_bricks smelting, mossy_stone_bricks shapeless).
"""

import json
from pathlib import Path
from random import Random

from PIL import Image

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
NS = "copper_inferno"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# Inferno palette.
CHARCOAL_DARK = (0x2B, 0x22, 0x26)   # #2B2226
CHARCOAL = (0x3D, 0x2C, 0x2E)        # #3D2C2E
CHARCOAL_LIGHT = (0x4A, 0x36, 0x3A)
MORTAR = (0x1C, 0x15, 0x18)
FISSURE = (0x14, 0x0B, 0x0E)
EMBER = (0xE2, 0x58, 0x22)           # #E25822
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)    # #FF7A2F
EMBER_HOT = (0xFF, 0xB1, 0x6B)
COPPER_DARK = (0x4E, 0x2F, 0x26)
COPPER_DARKER = (0x42, 0x28, 0x21)

FAMILIES = [
    # (base block name, stem)
    ("inferno_bricks", "inferno_brick"),
    ("inferno_tiles", "inferno_tile"),
    ("charred_copper_bricks", "charred_copper_brick"),
]
SINGLE_CUBES = [
    "cracked_inferno_bricks",
    "chiseled_inferno_bricks",
    "charred_copper",
    "inferno_core",
]

LANG = {
    "block.copper_inferno.inferno_bricks": "Inferno Bricks",
    "block.copper_inferno.inferno_brick_slab": "Inferno Brick Slab",
    "block.copper_inferno.inferno_brick_stairs": "Inferno Brick Stairs",
    "block.copper_inferno.inferno_brick_wall": "Inferno Brick Wall",
    "block.copper_inferno.inferno_tiles": "Inferno Tiles",
    "block.copper_inferno.inferno_tile_slab": "Inferno Tile Slab",
    "block.copper_inferno.inferno_tile_stairs": "Inferno Tile Stairs",
    "block.copper_inferno.inferno_tile_wall": "Inferno Tile Wall",
    "block.copper_inferno.charred_copper_bricks": "Charred Copper Bricks",
    "block.copper_inferno.charred_copper_brick_slab": "Charred Copper Brick Slab",
    "block.copper_inferno.charred_copper_brick_stairs": "Charred Copper Brick Stairs",
    "block.copper_inferno.charred_copper_brick_wall": "Charred Copper Brick Wall",
    "block.copper_inferno.cracked_inferno_bricks": "Cracked Inferno Bricks",
    "block.copper_inferno.chiseled_inferno_bricks": "Chiseled Inferno Bricks",
    "block.copper_inferno.charred_copper": "Charred Copper",
    "block.copper_inferno.inferno_pillar": "Inferno Pillar",
    "block.copper_inferno.inferno_core": "Inferno Core",
}

# ---------------------------------------------------------------------------
# Vanilla templates (exact structure from minecraft-client.jar, 1.21.9).
# Tokens @STAIRS@/@INNER@/@OUTER@/@WALL_POST@/@WALL_SIDE@/@WALL_SIDE_TALL@ are
# substituted with the mod's model ids before parsing.
# ---------------------------------------------------------------------------

STAIRS_BLOCKSTATE_TEMPLATE = """
{
  "variants": {
    "facing=east,half=bottom,shape=inner_left": {"model": "@INNER@", "uvlock": true, "y": 270},
    "facing=east,half=bottom,shape=inner_right": {"model": "@INNER@"},
    "facing=east,half=bottom,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "y": 270},
    "facing=east,half=bottom,shape=outer_right": {"model": "@OUTER@"},
    "facing=east,half=bottom,shape=straight": {"model": "@STAIRS@"},
    "facing=east,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180},
    "facing=east,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 90},
    "facing=east,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180},
    "facing=east,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 90},
    "facing=east,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180},
    "facing=north,half=bottom,shape=inner_left": {"model": "@INNER@", "uvlock": true, "y": 180},
    "facing=north,half=bottom,shape=inner_right": {"model": "@INNER@", "uvlock": true, "y": 270},
    "facing=north,half=bottom,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "y": 180},
    "facing=north,half=bottom,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "y": 270},
    "facing=north,half=bottom,shape=straight": {"model": "@STAIRS@", "uvlock": true, "y": 270},
    "facing=north,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 270},
    "facing=north,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180},
    "facing=north,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 270},
    "facing=north,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180},
    "facing=north,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180, "y": 270},
    "facing=south,half=bottom,shape=inner_left": {"model": "@INNER@"},
    "facing=south,half=bottom,shape=inner_right": {"model": "@INNER@", "uvlock": true, "y": 90},
    "facing=south,half=bottom,shape=outer_left": {"model": "@OUTER@"},
    "facing=south,half=bottom,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "y": 90},
    "facing=south,half=bottom,shape=straight": {"model": "@STAIRS@", "uvlock": true, "y": 90},
    "facing=south,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 90},
    "facing=south,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 180},
    "facing=south,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 90},
    "facing=south,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 180},
    "facing=south,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180, "y": 90},
    "facing=west,half=bottom,shape=inner_left": {"model": "@INNER@", "uvlock": true, "y": 90},
    "facing=west,half=bottom,shape=inner_right": {"model": "@INNER@", "uvlock": true, "y": 180},
    "facing=west,half=bottom,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "y": 90},
    "facing=west,half=bottom,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "y": 180},
    "facing=west,half=bottom,shape=straight": {"model": "@STAIRS@", "uvlock": true, "y": 180},
    "facing=west,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 180},
    "facing=west,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 270},
    "facing=west,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 180},
    "facing=west,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 270},
    "facing=west,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180, "y": 180}
  }
}
"""

WALL_BLOCKSTATE_TEMPLATE = """
{
  "multipart": [
    {"apply": {"model": "@WALL_POST@"}, "when": {"up": "true"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true}, "when": {"north": "low"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true, "y": 90}, "when": {"east": "low"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true, "y": 180}, "when": {"south": "low"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true, "y": 270}, "when": {"west": "low"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true}, "when": {"north": "tall"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true, "y": 90}, "when": {"east": "tall"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true, "y": 180}, "when": {"south": "tall"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true, "y": 270}, "when": {"west": "tall"}}
  ]
}
"""


def write_json(path: Path, obj) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True) + "\n", encoding="utf-8")


def block_ref(name: str) -> str:
    return f"{NS}:block/{name}"


# ---------------------------------------------------------------------------
# JSON emitters
# ---------------------------------------------------------------------------

def emit_cube(name: str, texture: str | None = None) -> None:
    """Blockstate + cube_all model + item definition for a simple full cube."""
    texture = texture or name
    write_json(ASSETS / "blockstates" / f"{name}.json",
               {"variants": {"": {"model": block_ref(name)}}})
    write_json(ASSETS / "models" / "block" / f"{name}.json",
               {"parent": "minecraft:block/cube_all", "textures": {"all": block_ref(texture)}})
    write_json(ASSETS / "items" / f"{name}.json",
               {"model": {"type": "minecraft:model", "model": block_ref(name)}})


def emit_family(base: str, stem: str) -> None:
    emit_cube(base)
    tex = block_ref(base)
    tex3 = {"bottom": tex, "side": tex, "top": tex}

    # Slab (vanilla stone_brick_slab format; double state reuses the base cube model).
    slab = f"{stem}_slab"
    write_json(ASSETS / "models" / "block" / f"{slab}.json",
               {"parent": "minecraft:block/slab", "textures": tex3})
    write_json(ASSETS / "models" / "block" / f"{slab}_top.json",
               {"parent": "minecraft:block/slab_top", "textures": tex3})
    write_json(ASSETS / "blockstates" / f"{slab}.json", {"variants": {
        "type=bottom": {"model": block_ref(slab)},
        "type=double": {"model": block_ref(base)},
        "type=top": {"model": block_ref(f"{slab}_top")},
    }})
    write_json(ASSETS / "items" / f"{slab}.json",
               {"model": {"type": "minecraft:model", "model": block_ref(slab)}})

    # Stairs (vanilla stone_brick_stairs format).
    stairs = f"{stem}_stairs"
    write_json(ASSETS / "models" / "block" / f"{stairs}.json",
               {"parent": "minecraft:block/stairs", "textures": tex3})
    write_json(ASSETS / "models" / "block" / f"{stairs}_inner.json",
               {"parent": "minecraft:block/inner_stairs", "textures": tex3})
    write_json(ASSETS / "models" / "block" / f"{stairs}_outer.json",
               {"parent": "minecraft:block/outer_stairs", "textures": tex3})
    stairs_state = (STAIRS_BLOCKSTATE_TEMPLATE
                    .replace("@STAIRS@", block_ref(stairs))
                    .replace("@INNER@", block_ref(f"{stairs}_inner"))
                    .replace("@OUTER@", block_ref(f"{stairs}_outer")))
    write_json(ASSETS / "blockstates" / f"{stairs}.json", json.loads(stairs_state))
    write_json(ASSETS / "items" / f"{stairs}.json",
               {"model": {"type": "minecraft:model", "model": block_ref(stairs)}})

    # Wall (vanilla stone_brick_wall format; item uses the wall_inventory model).
    wall = f"{stem}_wall"
    wall_tex = {"wall": tex}
    for suffix, parent in (("post", "template_wall_post"), ("side", "template_wall_side"),
                           ("side_tall", "template_wall_side_tall"), ("inventory", "wall_inventory")):
        write_json(ASSETS / "models" / "block" / f"{wall}_{suffix}.json",
                   {"parent": f"minecraft:block/{parent}", "textures": wall_tex})
    wall_state = (WALL_BLOCKSTATE_TEMPLATE
                  .replace("@WALL_POST@", block_ref(f"{wall}_post"))
                  .replace("@WALL_SIDE_TALL@", block_ref(f"{wall}_side_tall"))
                  .replace("@WALL_SIDE@", block_ref(f"{wall}_side")))
    write_json(ASSETS / "blockstates" / f"{wall}.json", json.loads(wall_state))
    write_json(ASSETS / "items" / f"{wall}.json",
               {"model": {"type": "minecraft:model", "model": block_ref(f"{wall}_inventory")}})


def emit_pillar() -> None:
    """inferno_pillar: axis blockstate + cube_column model, exactly like vanilla basalt."""
    name = "inferno_pillar"
    write_json(ASSETS / "blockstates" / f"{name}.json", {"variants": {
        "axis=x": {"model": block_ref(name), "x": 90, "y": 90},
        "axis=y": {"model": block_ref(name)},
        "axis=z": {"model": block_ref(name), "x": 90},
    }})
    write_json(ASSETS / "models" / "block" / f"{name}.json", {
        "parent": "minecraft:block/cube_column",
        "textures": {"end": block_ref(f"{name}_top"), "side": block_ref(f"{name}_side")},
    })
    write_json(ASSETS / "items" / f"{name}.json",
               {"model": {"type": "minecraft:model", "model": block_ref(name)}})


def emit_drop_self_loot(name: str) -> None:
    write_json(DATA / "loot_table" / "blocks" / f"{name}.json", {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "conditions": [{"condition": "minecraft:survives_explosion"}],
            "entries": [{"type": "minecraft:item", "name": f"{NS}:{name}"}],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/{name}",
    })


def emit_slab_loot(name: str) -> None:
    """Vanilla slab loot (drop self, x2 when type=double)."""
    write_json(DATA / "loot_table" / "blocks" / f"{name}.json", {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "entries": [{
                "type": "minecraft:item",
                "functions": [
                    {
                        "add": False,
                        "conditions": [{
                            "block": f"{NS}:{name}",
                            "condition": "minecraft:block_state_property",
                            "properties": {"type": "double"},
                        }],
                        "count": 2.0,
                        "function": "minecraft:set_count",
                    },
                    {"function": "minecraft:explosion_decay"},
                ],
                "name": f"{NS}:{name}",
            }],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/{name}",
    })


def recipe_path(name: str) -> Path:
    return DATA / "recipe" / "inferno" / f"{name}.json"


def emit_shaped(name: str, key: dict, pattern: list, result_id: str, count: int,
                category: str = "building") -> None:
    write_json(recipe_path(name), {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": result_id},
    })


def emit_recipes() -> None:
    # Base cubes / singles.
    emit_shaped("inferno_bricks", {"B": "minecraft:blackstone", "M": "minecraft:magma_cream"},
                ["B B", " M ", "B B"], f"{NS}:inferno_bricks", 4)
    emit_shaped("inferno_tiles", {"#": f"{NS}:inferno_bricks"},
                ["##", "##"], f"{NS}:inferno_tiles", 4)
    write_json(recipe_path("charred_copper"), {
        "type": "minecraft:crafting_shapeless",
        "category": "building",
        "ingredients": ["minecraft:copper_block", "minecraft:charcoal"],
        "result": {"count": 1, "id": f"{NS}:charred_copper"},
    })
    emit_shaped("charred_copper_bricks", {"#": f"{NS}:charred_copper"},
                ["##", "##"], f"{NS}:charred_copper_bricks", 4)
    # Smelting format copied from vanilla cracked_stone_bricks.
    write_json(recipe_path("cracked_inferno_bricks"), {
        "type": "minecraft:smelting",
        "category": "blocks",
        "cookingtime": 200,
        "experience": 0.1,
        "ingredient": f"{NS}:inferno_bricks",
        "result": {"id": f"{NS}:cracked_inferno_bricks"},
    })
    emit_shaped("chiseled_inferno_bricks", {"#": f"{NS}:inferno_brick_slab"},
                ["#", "#"], f"{NS}:chiseled_inferno_bricks", 1)
    emit_shaped("inferno_pillar", {"#": f"{NS}:inferno_bricks"},
                ["#", "#", "#"], f"{NS}:inferno_pillar", 3)
    emit_shaped("inferno_core", {"#": f"{NS}:inferno_bricks", "M": "minecraft:magma_block"},
                ["###", "#M#", "###"], f"{NS}:inferno_core", 1)

    # Family slab/stairs/wall recipes from their base cubes (vanilla formats).
    for base, stem in FAMILIES:
        cube = f"{NS}:{base}"
        emit_shaped(f"{stem}_slab", {"#": cube}, ["###"], f"{NS}:{stem}_slab", 6)
        emit_shaped(f"{stem}_stairs", {"#": cube}, ["#  ", "## ", "###"], f"{NS}:{stem}_stairs", 4)
        emit_shaped(f"{stem}_wall", {"#": cube}, ["###", "###"], f"{NS}:{stem}_wall", 6, category="misc")


# ---------------------------------------------------------------------------
# Textures (16x16, deterministic)
# ---------------------------------------------------------------------------

def new_canvas(rng: Random, shades=None) -> Image.Image:
    """Charcoal noise base."""
    shades = shades or [CHARCOAL_DARK, CHARCOAL, CHARCOAL, CHARCOAL_LIGHT]
    img = Image.new("RGB", (16, 16))
    for y in range(16):
        for x in range(16):
            img.putpixel((x, y), rng.choice(shades))
    return img


def sprinkle(img: Image.Image, rng: Random, pixels, colors, prob: float) -> None:
    for x, y in pixels:
        if rng.random() < prob:
            img.putpixel((x, y), rng.choice(colors))


def tex_inferno_bricks(rng: Random) -> Image.Image:
    """Brick bond: 8x4 bricks, dark mortar, ember pixels along the seams."""
    img = new_canvas(rng)
    seams = []
    for y in range(16):
        row = y // 4
        offset = (row % 2) * 4
        for x in range(16):
            if y % 4 == 3 or (x - offset) % 8 == 7:
                img.putpixel((x, y), MORTAR)
                seams.append((x, y))
    sprinkle(img, rng, seams, [EMBER, EMBER_BRIGHT], 0.14)
    return img


def tex_inferno_tiles(rng: Random) -> Image.Image:
    """2x2 grid of 8x8 tiles: dark grout, ember dots at grout intersections."""
    img = new_canvas(rng)
    grout = []
    for y in range(16):
        for x in range(16):
            if x % 8 == 7 or y % 8 == 7:
                img.putpixel((x, y), MORTAR)
                grout.append((x, y))
    sprinkle(img, rng, grout, [EMBER], 0.08)
    for x, y in [(7, 7), (15, 7), (7, 15), (15, 15)]:
        img.putpixel((x, y), EMBER_BRIGHT)
    return img


def tex_charred_copper(rng: Random) -> Image.Image:
    """Mottled charred copper: charcoal with dark-copper tint and sparse ember specks."""
    img = new_canvas(rng, [CHARCOAL_DARK, CHARCOAL, COPPER_DARK, COPPER_DARKER, COPPER_DARK])
    all_px = [(x, y) for y in range(16) for x in range(16)]
    sprinkle(img, rng, all_px, [EMBER], 0.03)
    return img


def tex_charred_copper_bricks(rng: Random) -> Image.Image:
    """Small copper-brick shapes (4x4 bond), darkened, ember-heavy seams."""
    img = new_canvas(rng, [COPPER_DARK, COPPER_DARKER, CHARCOAL, COPPER_DARK])
    seams = []
    for y in range(16):
        row = y // 4
        offset = (row % 2) * 2
        for x in range(16):
            if y % 4 == 3 or (x - offset) % 4 == 3:
                img.putpixel((x, y), FISSURE)
                seams.append((x, y))
    sprinkle(img, rng, seams, [EMBER, EMBER_BRIGHT], 0.22)
    return img


def tex_cracked_inferno_bricks(rng: Random) -> Image.Image:
    """Inferno bricks plus a jagged glowing fissure running top to bottom."""
    img = tex_inferno_bricks(rng)
    x = 6
    for y in range(16):
        x = max(1, min(14, x + rng.choice([-1, 0, 0, 1])))
        img.putpixel((x, y), FISSURE)
        # Ember glow bleeding out of the crack.
        if rng.random() < 0.45:
            img.putpixel((x + rng.choice([-1, 1]), y), EMBER)
        if rng.random() < 0.25:
            img.putpixel((x, y), EMBER_BRIGHT)
    return img


FLAME_GLYPH = {
    4: [8], 5: [8], 6: [7, 8], 7: [7, 8, 9], 8: [6, 7, 8, 9],
    9: [6, 7, 8, 9, 10], 10: [6, 7, 8, 9, 10], 11: [7, 8, 9], 12: [8, 9],
}
FLAME_CORE = [(8, 9), (8, 10), (9, 10)]


def tex_chiseled_inferno_bricks(rng: Random) -> Image.Image:
    """Framed flame glyph: 1px dark frame, charcoal fill, ember flame in the middle."""
    img = new_canvas(rng)
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), MORTAR)
        if 0 < i < 15:
            img.putpixel((i, 1), CHARCOAL_LIGHT)
    for y, xs in FLAME_GLYPH.items():
        for x in xs:
            img.putpixel((x, y), EMBER)
    for x, y in FLAME_CORE:
        img.putpixel((x, y), EMBER_BRIGHT)
    img.putpixel((8, 10), EMBER_HOT)
    return img


def tex_inferno_core(rng: Random) -> Image.Image:
    """Pulsing ember cross — the brightest inferno texture (block emits light 12)."""
    img = new_canvas(rng, [CHARCOAL_DARK, CHARCOAL])
    for i in range(16):
        for x in (7, 8):
            img.putpixel((x, i), EMBER_BRIGHT if 4 <= i <= 11 else EMBER)
        for y in (7, 8):
            img.putpixel((i, y), EMBER_BRIGHT if 4 <= i <= 11 else EMBER)
    # White-hot centre.
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), EMBER_HOT)
    # Faint glow on the diagonals next to the cross.
    for x, y in [(6, 6), (9, 6), (6, 9), (9, 9)]:
        img.putpixel((x, y), EMBER)
    return img


def tex_inferno_pillar_side(rng: Random) -> Image.Image:
    """Vertical column striping with two ember seams (basalt-side style)."""
    img = Image.new("RGB", (16, 16))
    for x in range(16):
        if x in (0, 15):
            column = [MORTAR]
        elif x in (1, 7, 8, 14):
            column = [CHARCOAL_DARK, CHARCOAL_DARK, CHARCOAL]
        else:
            column = [CHARCOAL, CHARCOAL, CHARCOAL_LIGHT, CHARCOAL_DARK]
        for y in range(16):
            img.putpixel((x, y), rng.choice(column))
    seam_px = [(x, y) for x in (4, 11) for y in range(16)]
    sprinkle(img, rng, seam_px, [EMBER, EMBER_BRIGHT], 0.35)
    return img


def tex_inferno_pillar_top(rng: Random) -> Image.Image:
    """Framed top face with an ember inner ring and bright centre (basalt-top style)."""
    img = new_canvas(rng)
    ring = []
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), MORTAR)
    for i in range(3, 13):
        ring += [(i, 3), (i, 12), (3, i), (12, i)]
    for x, y in ring:
        img.putpixel((x, y), CHARCOAL_DARK)
    sprinkle(img, rng, ring, [EMBER], 0.3)
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), EMBER_BRIGHT)
    return img


TEXTURES = {
    "inferno_bricks": tex_inferno_bricks,
    "inferno_tiles": tex_inferno_tiles,
    "charred_copper_bricks": tex_charred_copper_bricks,
    "cracked_inferno_bricks": tex_cracked_inferno_bricks,
    "chiseled_inferno_bricks": tex_chiseled_inferno_bricks,
    "charred_copper": tex_charred_copper,
    "inferno_core": tex_inferno_core,
    "inferno_pillar_side": tex_inferno_pillar_side,
    "inferno_pillar_top": tex_inferno_pillar_top,
}


def emit_textures() -> None:
    tex_dir = ASSETS / "textures" / "block"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in TEXTURES.items():
        # Per-texture fixed seed keeps output byte-identical across runs.
        img = fn(Random(f"copper_inferno:{name}"))
        img.save(tex_dir / f"{name}.png")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    for base, stem in FAMILIES:
        emit_family(base, stem)
        emit_drop_self_loot(base)
        emit_slab_loot(f"{stem}_slab")
        emit_drop_self_loot(f"{stem}_stairs")
        emit_drop_self_loot(f"{stem}_wall")
    for name in SINGLE_CUBES:
        emit_cube(name)
        emit_drop_self_loot(name)
    emit_pillar()
    emit_drop_self_loot("inferno_pillar")
    emit_recipes()
    emit_textures()
    write_json(ASSETS / "lang" / "fragments" / "inferno.json", LANG)
    print("inferno_gen: all inferno assets generated.")


if __name__ == "__main__":
    main()
