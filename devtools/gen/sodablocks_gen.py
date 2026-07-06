#!/usr/bin/env python3
"""Asset generator for the SODABLOCKS feature (COPPER INFERNO 1 v2).

Emits ALL JSON (blockstates, block/item models, item definitions, loot tables,
recipes, lang fragment) and all 16x16 PNG textures for the 28 soda-factory
blocks, directly into src/main/resources. Idempotent: running it twice
produces byte-identical output (textures are deterministic; no RNG).

JSON formats are copied from EXACT vanilla 1.21.9 templates extracted from
~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar (brick family for
slab/stairs/wall, bricks for cube_all + drop-self loot, brick_slab for slab
loot, glass.json for smelting, packed_ice.json for shapeless) and from the v1
dr_pepper_can_block files (cube_column + item model indirection).

NOTE: JSON emission is legacy scaffolding (opt-in via --write-json); the JSON in
src/main/resources is authoritative — by default this script writes ONLY PNGs.

Usage: python3 devtools/gen/sodablocks_gen.py [--write-json]
"""

import json
import sys
from pathlib import Path

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> textures/*.png only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / "copper_inferno"
DATA = RES / "data" / "copper_inferno"

MOD = "copper_inferno"

# ---------------------------------------------------------------------------
# Palette (Dr.Pepper maroon #5A0E14 / #7A1B22, white #F2EFEA + can silvers
# matching the v1 dr_pepper_can_block textures).
# ---------------------------------------------------------------------------
MAROON = (90, 14, 20)
MAROON_LIGHT = (122, 27, 34)
MAROON_DARK = (58, 8, 12)
WHITE = (242, 239, 234)
SILVER = (154, 160, 166)
SILVER_DARK = (110, 115, 120)
SILVER_LIGHT = (198, 203, 209)


def blend(a, b, t):
    return tuple(int(round(a[i] + (b[i] - a[i]) * t)) for i in range(3))


# ---------------------------------------------------------------------------
# Content tables
# ---------------------------------------------------------------------------

# (cube id, stem) -> cube, <stem>_slab, <stem>_stairs, <stem>_wall
FAMILIES = [
    ("dr_pepper_can_bricks", "dr_pepper_can_brick"),
    ("dr_pepper_can_tiles", "dr_pepper_can_tile"),
    ("sugar_bricks", "sugar_brick"),
]

# id -> (display name, vanilla dye for the recipe, strong accent, pale band)
FLAVOR_CANS = {
    "cherry_soda_can_block": ("Cherry Soda Can Block", "red_dye", (196, 32, 48), (250, 210, 216)),
    "vanilla_soda_can_block": ("Vanilla Soda Can Block", "white_dye", (214, 178, 106), (248, 240, 214)),
    "cream_soda_can_block": ("Cream Soda Can Block", "yellow_dye", (226, 164, 54), (250, 232, 196)),
    "diet_dr_pepper_can_block": ("Diet Dr.Pepper Can Block", "light_gray_dye", (150, 155, 162), (240, 242, 244)),
    "orange_soda_can_block": ("Orange Soda Can Block", "orange_dye", (235, 128, 24), (252, 220, 180)),
    "grape_soda_can_block": ("Grape Soda Can Block", "purple_dye", (122, 48, 160), (228, 204, 244)),
    "lime_soda_can_block": ("Lime Soda Can Block", "lime_dye", (100, 190, 50), (216, 244, 198)),
    "blueberry_soda_can_block": ("Blueberry Soda Can Block", "blue_dye", (48, 88, 200), (200, 216, 248)),
}

SINGLES = [
    "soda_syrup_block",
    "sugar_block",
    "caramel_block",
    "fizzy_soda_block",
    "crushed_can_block",
    "dr_pepper_crate",
    "bottle_cap_block",
    "soda_ice_block",
]

LANG = {
    "dr_pepper_can_bricks": "Dr.Pepper Can Bricks",
    "dr_pepper_can_brick_slab": "Dr.Pepper Can Brick Slab",
    "dr_pepper_can_brick_stairs": "Dr.Pepper Can Brick Stairs",
    "dr_pepper_can_brick_wall": "Dr.Pepper Can Brick Wall",
    "dr_pepper_can_tiles": "Dr.Pepper Can Tiles",
    "dr_pepper_can_tile_slab": "Dr.Pepper Can Tile Slab",
    "dr_pepper_can_tile_stairs": "Dr.Pepper Can Tile Stairs",
    "dr_pepper_can_tile_wall": "Dr.Pepper Can Tile Wall",
    "sugar_bricks": "Sugar Bricks",
    "sugar_brick_slab": "Sugar Brick Slab",
    "sugar_brick_stairs": "Sugar Brick Stairs",
    "sugar_brick_wall": "Sugar Brick Wall",
    "soda_syrup_block": "Soda Syrup Block",
    "sugar_block": "Sugar Block",
    "caramel_block": "Caramel Block",
    "fizzy_soda_block": "Fizzy Soda Block",
    "crushed_can_block": "Crushed Can Block",
    "dr_pepper_crate": "Dr.Pepper Crate",
    "bottle_cap_block": "Bottle Cap Block",
    "soda_ice_block": "Soda Ice Block",
}
for _id, (_name, _dye, _acc, _band) in FLAVOR_CANS.items():
    LANG[_id] = _name


# ---------------------------------------------------------------------------
# IO helpers
# ---------------------------------------------------------------------------

def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=False) + "\n", encoding="utf-8")


def save_tex(name: str, img: Image.Image) -> None:
    path = ASSETS / "textures" / "block" / f"{name}.png"
    path.parent.mkdir(parents=True, exist_ok=True)
    img.save(path)


# ---------------------------------------------------------------------------
# Texture painters (all deterministic pixel art, 16x16)
# ---------------------------------------------------------------------------

def new_img(color):
    return Image.new("RGB", (16, 16), color)


def hash_noise(x, y, salt=0):
    """Cheap deterministic pseudo-noise in [0, 16)."""
    return (x * 31 + y * 17 + salt * 7 + (x * y) % 13) % 16


def can_side(body_dark, body_light, band, accent):
    """Vertical can side: silver rims, cylinder-shaded body, label band."""
    img = new_img(body_dark)
    p = img.load()
    for y in range(16):
        for x in range(16):
            if y == 0 or y == 15:
                p[x, y] = SILVER
            elif y == 1 or y == 14:
                p[x, y] = SILVER_DARK
            else:
                if x in (0, 15):
                    c = blend(body_dark, (0, 0, 0), 0.35)
                elif 4 <= x <= 10:
                    c = body_light
                else:
                    c = body_dark
                p[x, y] = c
    # Label band (rows 5..10) with a wavy accent "logo" stripe.
    for y in range(5, 11):
        for x in range(16):
            c = band
            if x in (0, 15):
                c = blend(band, (0, 0, 0), 0.25)
            p[x, y] = c
    for x in range(1, 15):
        wave = 7 + (1 if x % 4 in (1, 2) else 0)
        p[x, wave] = accent
        if x % 5 == 2:
            p[x, wave + 1] = accent
    return img


def can_top(accent):
    """Aluminum lid with concentric rings + pull tab, faint accent ring."""
    img = new_img(SILVER)
    p = img.load()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d = ((x - cx) ** 2 + (y - cy) ** 2) ** 0.5
            if d > 7.2:
                p[x, y] = SILVER_DARK
            elif 6.2 < d <= 7.2:
                p[x, y] = blend(SILVER_DARK, accent, 0.35)
            elif 3.6 < d <= 4.4:
                p[x, y] = SILVER_DARK
    for x in range(6, 10):
        p[x, 7] = SILVER_LIGHT
        p[x, 8] = SILVER_DARK
    p[9, 6] = SILVER_LIGHT
    return img


def bricks_tex(brick_a, brick_b, mortar, fleck=None, fleck_b=None):
    """Classic 4-row running-bond brick pattern with optional flecks."""
    img = new_img(mortar)
    p = img.load()
    for row in range(4):
        y0 = row * 4
        offset = 0 if row % 2 == 0 else 4
        for y in range(y0, y0 + 3):
            for x in range(16):
                bx = (x + offset) % 16
                if bx % 8 == 7:
                    continue  # vertical mortar joint
                c = brick_a if ((x + offset) // 8 + row) % 2 == 0 else brick_b
                if hash_noise(x, y, 3) < 2:
                    c = blend(c, (0, 0, 0), 0.12)
                p[x, y] = c
    if fleck:
        for (x, y) in [(2, 1), (12, 5), (6, 9), (14, 13), (4, 13), (9, 1)]:
            p[x, y] = fleck
    if fleck_b:
        for (x, y) in [(5, 2), (10, 10), (1, 6), (13, 9)]:
            p[x, y] = fleck_b
    return img


def tiles_tex(tile, tile_light, grout, speck):
    """2x2 grid of 8x8 tiles with top-left highlight and center speck."""
    img = new_img(grout)
    p = img.load()
    for ty in (0, 8):
        for tx in (0, 8):
            for y in range(ty + 1, ty + 8):
                for x in range(tx + 1, tx + 8):
                    c = tile
                    if x == tx + 1 or y == ty + 1:
                        c = tile_light
                    if hash_noise(x, y, 5) < 2:
                        c = blend(c, (0, 0, 0), 0.1)
                    p[x, y] = c
            p[tx + 4, ty + 4] = speck
    return img


def grainy_tex(base, light, dark, sparkle):
    img = new_img(base)
    p = img.load()
    for y in range(16):
        for x in range(16):
            n = hash_noise(x, y, 11)
            if n < 3:
                p[x, y] = dark
            elif n > 12:
                p[x, y] = light
    for (x, y) in [(3, 4), (11, 2), (7, 9), (13, 12), (1, 13)]:
        p[x, y] = sparkle
    return img


def glossy_tex(base, deep, gloss, shine):
    """Glossy goo: dark base with diagonal highlight streaks."""
    img = new_img(base)
    p = img.load()
    for y in range(16):
        for x in range(16):
            if (x + y) % 8 in (0, 1):
                p[x, y] = deep
            elif (x + y) % 8 == 4:
                p[x, y] = gloss
    for (x, y) in [(4, 2), (5, 2), (12, 7), (3, 11), (10, 13), (11, 13)]:
        p[x, y] = shine
    return img


def fizzy_tex():
    """Maroon soda with rising pale bubbles."""
    img = new_img(MAROON)
    p = img.load()
    for y in range(16):
        for x in range(16):
            if hash_noise(x, y, 2) < 4:
                p[x, y] = MAROON_LIGHT
            elif hash_noise(x, y, 9) > 13:
                p[x, y] = MAROON_DARK
    bubbles = [(3, 12), (7, 9), (11, 13), (13, 5), (5, 4), (9, 2), (2, 6), (14, 10)]
    for (x, y) in bubbles:
        p[x, y] = (222, 150, 156)
        if x + 1 < 16:
            p[x + 1, y] = (200, 108, 116)
    for (x, y) in [(6, 14), (12, 8), (1, 2), (10, 6)]:
        p[x, y] = WHITE
    return img


def crushed_can_tex():
    """Crumpled silver cans with maroon label fragments."""
    img = new_img(SILVER)
    p = img.load()
    for y in range(16):
        for x in range(16):
            wave = (x + (y * 3)) % 6
            if wave == 0:
                p[x, y] = SILVER_DARK
            elif wave == 3:
                p[x, y] = SILVER_LIGHT
    frags = [(1, 2, 4, 3), (9, 5, 13, 7), (3, 10, 6, 12), (11, 12, 15, 14)]
    for (x0, y0, x1, y1) in frags:
        for y in range(y0, y1 + 1):
            for x in range(x0, x1 + 1):
                p[x, y] = MAROON if (x + y) % 2 == 0 else MAROON_LIGHT
        p[x0 + 1, y0 + 1] = WHITE
    return img


def crate_tex():
    """Wooden crate frame with a Dr.Pepper can print in the center."""
    wood = (168, 122, 68)
    wood_dark = (143, 101, 53)
    wood_line = (96, 66, 32)
    img = new_img(wood)
    p = img.load()
    for y in range(16):
        for x in range(16):
            c = wood if (y // 2) % 2 == 0 else wood_dark
            if hash_noise(x, y, 7) < 2:
                c = blend(c, wood_line, 0.4)
            p[x, y] = c
    for i in range(16):
        p[i, 0] = wood_line
        p[i, 15] = wood_line
        p[0, i] = wood_line
        p[15, i] = wood_line
    # 6x8 can print: maroon body + white band + silver lid.
    for y in range(4, 12):
        for x in range(5, 11):
            p[x, y] = MAROON if x in (5, 10) else MAROON_LIGHT
    for x in range(5, 11):
        p[x, 4] = SILVER
        p[x, 7] = WHITE
        p[x, 8] = WHITE
    p[7, 8] = MAROON
    p[8, 8] = MAROON
    return img


def bottle_cap_tex():
    """2x2 grid of crimped maroon bottle caps on dark tray."""
    tray = (58, 58, 62)
    img = new_img(tray)
    p = img.load()
    for cy in (3.5, 11.5):
        for cx in (3.5, 11.5):
            for y in range(16):
                for x in range(16):
                    d = ((x - cx) ** 2 + (y - cy) ** 2) ** 0.5
                    if d <= 1.2:
                        p[x, y] = WHITE
                    elif d <= 2.4:
                        p[x, y] = MAROON_LIGHT
                    elif d <= 3.4:
                        # crimped rim: alternate light/dark teeth
                        p[x, y] = MAROON if (x + y) % 2 == 0 else MAROON_DARK
    return img


def soda_ice_tex():
    """Pale pink-blue soda ice with glints and cracks."""
    base = (214, 222, 232)
    pink = (232, 212, 218)
    crack = (178, 192, 206)
    glint = (247, 250, 252)
    img = new_img(base)
    p = img.load()
    for y in range(16):
        for x in range(16):
            n = hash_noise(x, y, 13)
            if n < 3:
                p[x, y] = pink
            elif n > 13:
                p[x, y] = glint
    for i in range(5):
        p[min(15, 2 + i), min(15, 3 + i)] = crack
        p[min(15, 9 + i), min(15, 8 + i)] = crack
    for (x, y) in [(12, 2), (4, 10), (13, 13)]:
        p[x, y] = (255, 255, 255)
    return img


def gen_textures():
    # Family cubes.
    save_tex("dr_pepper_can_bricks",
             bricks_tex(MAROON_LIGHT, MAROON, MAROON_DARK, fleck=WHITE, fleck_b=SILVER))
    save_tex("dr_pepper_can_tiles",
             tiles_tex(MAROON, MAROON_LIGHT, MAROON_DARK, WHITE))
    save_tex("sugar_bricks",
             bricks_tex(WHITE, (250, 249, 246), (208, 202, 192), fleck=(255, 255, 255)))
    # Flavor cans (side + top), maroon body tinted toward the flavor accent.
    for fid, (_name, _dye, accent, band) in FLAVOR_CANS.items():
        body_dark = blend(MAROON, accent, 0.25)
        body_light = blend(MAROON_LIGHT, accent, 0.25)
        save_tex(f"{fid}_side", can_side(body_dark, body_light, band, accent))
        save_tex(f"{fid}_top", can_top(accent))
    # Singles.
    save_tex("soda_syrup_block",
             glossy_tex(MAROON_DARK, (40, 5, 8), MAROON_LIGHT, (204, 122, 128)))
    save_tex("sugar_block",
             grainy_tex(WHITE, (252, 251, 249), (222, 216, 206), (255, 255, 255)))
    save_tex("caramel_block",
             glossy_tex((176, 110, 36), (140, 82, 24), (222, 168, 84), (245, 214, 150)))
    save_tex("fizzy_soda_block", fizzy_tex())
    save_tex("crushed_can_block", crushed_can_tex())
    save_tex("dr_pepper_crate", crate_tex())
    save_tex("bottle_cap_block", bottle_cap_tex())
    save_tex("soda_ice_block", soda_ice_tex())


# ---------------------------------------------------------------------------
# Blockstates + models + item definitions (exact vanilla template shapes)
# ---------------------------------------------------------------------------

def block_ref(name):
    return f"{MOD}:block/{name}"


def emit_simple_blockstate(name, model=None):
    write_json(ASSETS / "blockstates" / f"{name}.json",
               {"variants": {"": {"model": model or block_ref(name)}}})


def emit_item_def(name, model):
    """items/<id>.json — 1.21.9 item model definition (vanilla format)."""
    write_json(ASSETS / "items" / f"{name}.json",
               {"model": {"type": "minecraft:model", "model": model}})


def emit_cube_all(name, texture=None):
    emit_simple_blockstate(name)
    write_json(ASSETS / "models" / "block" / f"{name}.json",
               {"parent": "minecraft:block/cube_all",
                "textures": {"all": texture or block_ref(name)}})
    emit_item_def(name, block_ref(name))


def emit_cube_column(name):
    """cube_column with side+top textures, like v1 dr_pepper_can_block
    (including its models/item indirection)."""
    emit_simple_blockstate(name)
    write_json(ASSETS / "models" / "block" / f"{name}.json",
               {"parent": "minecraft:block/cube_column",
                "textures": {"end": f"{MOD}:block/{name}_top",
                             "side": f"{MOD}:block/{name}_side"}})
    write_json(ASSETS / "models" / "item" / f"{name}.json",
               {"parent": block_ref(name)})
    emit_item_def(name, f"{MOD}:item/{name}")


def emit_slab(stem, cube):
    slab = f"{stem}_slab"
    tex = block_ref(cube)
    write_json(ASSETS / "blockstates" / f"{slab}.json", {
        "variants": {
            "type=bottom": {"model": block_ref(slab)},
            "type=double": {"model": block_ref(cube)},
            "type=top": {"model": block_ref(f"{slab}_top")},
        }
    })
    for suffix, parent in (("", "minecraft:block/slab"), ("_top", "minecraft:block/slab_top")):
        write_json(ASSETS / "models" / "block" / f"{slab}{suffix}.json",
                   {"parent": parent,
                    "textures": {"bottom": tex, "side": tex, "top": tex}})
    emit_item_def(slab, block_ref(slab))


def stairs_blockstate(stairs):
    """Exact vanilla brick_stairs.json variant table."""
    straight = block_ref(stairs)
    inner = block_ref(f"{stairs}_inner")
    outer = block_ref(f"{stairs}_outer")

    def v(model, x=None, y=None, uvlock=False):
        out = {"model": model}
        if uvlock:
            out["uvlock"] = True
        if x is not None:
            out["x"] = x
        if y is not None:
            out["y"] = y
        return out

    return {"variants": {
        "facing=east,half=bottom,shape=inner_left": v(inner, y=270, uvlock=True),
        "facing=east,half=bottom,shape=inner_right": v(inner),
        "facing=east,half=bottom,shape=outer_left": v(outer, y=270, uvlock=True),
        "facing=east,half=bottom,shape=outer_right": v(outer),
        "facing=east,half=bottom,shape=straight": v(straight),
        "facing=east,half=top,shape=inner_left": v(inner, x=180, uvlock=True),
        "facing=east,half=top,shape=inner_right": v(inner, x=180, y=90, uvlock=True),
        "facing=east,half=top,shape=outer_left": v(outer, x=180, uvlock=True),
        "facing=east,half=top,shape=outer_right": v(outer, x=180, y=90, uvlock=True),
        "facing=east,half=top,shape=straight": v(straight, x=180, uvlock=True),
        "facing=north,half=bottom,shape=inner_left": v(inner, y=180, uvlock=True),
        "facing=north,half=bottom,shape=inner_right": v(inner, y=270, uvlock=True),
        "facing=north,half=bottom,shape=outer_left": v(outer, y=180, uvlock=True),
        "facing=north,half=bottom,shape=outer_right": v(outer, y=270, uvlock=True),
        "facing=north,half=bottom,shape=straight": v(straight, y=270, uvlock=True),
        "facing=north,half=top,shape=inner_left": v(inner, x=180, y=270, uvlock=True),
        "facing=north,half=top,shape=inner_right": v(inner, x=180, uvlock=True),
        "facing=north,half=top,shape=outer_left": v(outer, x=180, y=270, uvlock=True),
        "facing=north,half=top,shape=outer_right": v(outer, x=180, uvlock=True),
        "facing=north,half=top,shape=straight": v(straight, x=180, y=270, uvlock=True),
        "facing=south,half=bottom,shape=inner_left": v(inner),
        "facing=south,half=bottom,shape=inner_right": v(inner, y=90, uvlock=True),
        "facing=south,half=bottom,shape=outer_left": v(outer),
        "facing=south,half=bottom,shape=outer_right": v(outer, y=90, uvlock=True),
        "facing=south,half=bottom,shape=straight": v(straight, y=90, uvlock=True),
        "facing=south,half=top,shape=inner_left": v(inner, x=180, y=90, uvlock=True),
        "facing=south,half=top,shape=inner_right": v(inner, x=180, y=180, uvlock=True),
        "facing=south,half=top,shape=outer_left": v(outer, x=180, y=90, uvlock=True),
        "facing=south,half=top,shape=outer_right": v(outer, x=180, y=180, uvlock=True),
        "facing=south,half=top,shape=straight": v(straight, x=180, y=90, uvlock=True),
        "facing=west,half=bottom,shape=inner_left": v(inner, y=90, uvlock=True),
        "facing=west,half=bottom,shape=inner_right": v(inner, y=180, uvlock=True),
        "facing=west,half=bottom,shape=outer_left": v(outer, y=90, uvlock=True),
        "facing=west,half=bottom,shape=outer_right": v(outer, y=180, uvlock=True),
        "facing=west,half=bottom,shape=straight": v(straight, y=180, uvlock=True),
        "facing=west,half=top,shape=inner_left": v(inner, x=180, y=180, uvlock=True),
        "facing=west,half=top,shape=inner_right": v(inner, x=180, y=270, uvlock=True),
        "facing=west,half=top,shape=outer_left": v(outer, x=180, y=180, uvlock=True),
        "facing=west,half=top,shape=outer_right": v(outer, x=180, y=270, uvlock=True),
        "facing=west,half=top,shape=straight": v(straight, x=180, y=180, uvlock=True),
    }}


def emit_stairs(stem, cube):
    stairs = f"{stem}_stairs"
    tex = block_ref(cube)
    write_json(ASSETS / "blockstates" / f"{stairs}.json", stairs_blockstate(stairs))
    for suffix, parent in (("", "minecraft:block/stairs"),
                           ("_inner", "minecraft:block/inner_stairs"),
                           ("_outer", "minecraft:block/outer_stairs")):
        write_json(ASSETS / "models" / "block" / f"{stairs}{suffix}.json",
                   {"parent": parent,
                    "textures": {"bottom": tex, "side": tex, "top": tex}})
    emit_item_def(stairs, block_ref(stairs))


def emit_wall(stem, cube):
    wall = f"{stem}_wall"
    tex = block_ref(cube)
    post = block_ref(f"{wall}_post")
    side = block_ref(f"{wall}_side")
    tall = block_ref(f"{wall}_side_tall")
    write_json(ASSETS / "blockstates" / f"{wall}.json", {"multipart": [
        {"apply": {"model": post}, "when": {"up": "true"}},
        {"apply": {"model": side, "uvlock": True}, "when": {"north": "low"}},
        {"apply": {"model": side, "uvlock": True, "y": 90}, "when": {"east": "low"}},
        {"apply": {"model": side, "uvlock": True, "y": 180}, "when": {"south": "low"}},
        {"apply": {"model": side, "uvlock": True, "y": 270}, "when": {"west": "low"}},
        {"apply": {"model": tall, "uvlock": True}, "when": {"north": "tall"}},
        {"apply": {"model": tall, "uvlock": True, "y": 90}, "when": {"east": "tall"}},
        {"apply": {"model": tall, "uvlock": True, "y": 180}, "when": {"south": "tall"}},
        {"apply": {"model": tall, "uvlock": True, "y": 270}, "when": {"west": "tall"}},
    ]})
    for suffix, parent in (("_post", "minecraft:block/template_wall_post"),
                           ("_side", "minecraft:block/template_wall_side"),
                           ("_side_tall", "minecraft:block/template_wall_side_tall"),
                           ("_inventory", "minecraft:block/wall_inventory")):
        write_json(ASSETS / "models" / "block" / f"{wall}{suffix}.json",
                   {"parent": parent, "textures": {"wall": tex}})
    emit_item_def(wall, block_ref(f"{wall}_inventory"))


# ---------------------------------------------------------------------------
# Loot tables
# ---------------------------------------------------------------------------

def emit_drop_self_loot(name):
    write_json(DATA / "loot_table" / "blocks" / f"{name}.json", {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "conditions": [{"condition": "minecraft:survives_explosion"}],
            "entries": [{"type": "minecraft:item", "name": f"{MOD}:{name}"}],
            "rolls": 1.0,
        }],
        "random_sequence": f"{MOD}:blocks/{name}",
    })


def emit_slab_loot(slab):
    """Exact vanilla brick_slab loot shape (2x drop when type=double)."""
    write_json(DATA / "loot_table" / "blocks" / f"{slab}.json", {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "entries": [{
                "type": "minecraft:item",
                "functions": [
                    {
                        "add": False,
                        "conditions": [{
                            "block": f"{MOD}:{slab}",
                            "condition": "minecraft:block_state_property",
                            "properties": {"type": "double"},
                        }],
                        "count": 2.0,
                        "function": "minecraft:set_count",
                    },
                    {"function": "minecraft:explosion_decay"},
                ],
                "name": f"{MOD}:{slab}",
            }],
            "rolls": 1.0,
        }],
        "random_sequence": f"{MOD}:blocks/{slab}",
    })


# ---------------------------------------------------------------------------
# Recipes (data/copper_inferno/recipe/sodablocks/<result>.json)
# ---------------------------------------------------------------------------

def recipe_path(name):
    return DATA / "recipe" / "sodablocks" / f"{name}.json"


def emit_shaped(name, pattern, key, count=1, category="building"):
    write_json(recipe_path(name), {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": f"{MOD}:{name}"},
    })


def emit_shapeless(name, ingredients, count=1, category="building"):
    write_json(recipe_path(name), {
        "type": "minecraft:crafting_shapeless",
        "category": category,
        "ingredients": ingredients,
        "result": {"count": count, "id": f"{MOD}:{name}"},
    })


def emit_smelting(name, ingredient):
    write_json(recipe_path(name), {
        "type": "minecraft:smelting",
        "category": "blocks",
        "cookingtime": 200,
        "experience": 0.1,
        "ingredient": ingredient,
        "result": {"id": f"{MOD}:{name}"},
    })


def emit_family_variant_recipes(stem, cube):
    cube_id = f"{MOD}:{cube}"
    emit_shaped(f"{stem}_slab", ["###"], {"#": cube_id}, count=6)
    emit_shaped(f"{stem}_stairs", ["#  ", "## ", "###"], {"#": cube_id}, count=4)
    emit_shaped(f"{stem}_wall", ["###", "###"], {"#": cube_id}, count=6, category="misc")


def gen_recipes():
    v1_can = f"{MOD}:dr_pepper_can_block"
    # Family cubes.
    emit_shaped("dr_pepper_can_bricks", ["##", "##"], {"#": v1_can}, count=4)
    emit_shaped("dr_pepper_can_tiles", ["##", "##"],
                {"#": f"{MOD}:dr_pepper_can_bricks"}, count=4)
    emit_shaped("sugar_block", ["###", "###", "###"], {"#": "minecraft:sugar"})
    emit_shaped("sugar_bricks", ["##", "##"], {"#": f"{MOD}:sugar_block"}, count=4)
    # Family slab/stairs/wall.
    for cube, stem in FAMILIES:
        emit_family_variant_recipes(stem, cube)
    # Flavored cans: v1 can block + matching vanilla dye.
    for fid, (_name, dye, _acc, _band) in FLAVOR_CANS.items():
        emit_shapeless(fid, [v1_can, f"minecraft:{dye}"])
    # Singles.
    emit_shapeless("soda_syrup_block", [f"{MOD}:sugar_block", "minecraft:honey_bottle"])
    emit_smelting("caramel_block", f"{MOD}:sugar_block")
    emit_shapeless("fizzy_soda_block", [f"{MOD}:sugar_block", "minecraft:gunpowder"])
    emit_shaped("crushed_can_block", ["#", "#"], {"#": v1_can}, count=2)
    emit_shaped("bottle_cap_block", ["NNN", "NDN", "NNN"],
                {"N": "minecraft:iron_nugget", "D": "minecraft:red_dye"})
    emit_shaped("dr_pepper_crate", ["PPP", "PCP", "PPP"],
                {"P": "#minecraft:planks", "C": v1_can})
    emit_shapeless("soda_ice_block", ["minecraft:ice", "minecraft:sugar"])


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main():
    gen_textures()

    # Families: cube (cube_all) + slab + stairs + wall.
    for cube, stem in FAMILIES:
        emit_cube_all(cube)
        emit_slab(stem, cube)
        emit_stairs(stem, cube)
        emit_wall(stem, cube)
        emit_drop_self_loot(cube)
        emit_slab_loot(f"{stem}_slab")
        emit_drop_self_loot(f"{stem}_stairs")
        emit_drop_self_loot(f"{stem}_wall")

    # Flavored can blocks: cube_column, side+top textures.
    for fid in FLAVOR_CANS:
        emit_cube_column(fid)
        emit_drop_self_loot(fid)

    # Singles: cube_all.
    for sid in SINGLES:
        emit_cube_all(sid)
        emit_drop_self_loot(sid)

    gen_recipes()

    # Lang fragment (merged into en_us.json by the integration step).
    lang = {f"block.{MOD}.{bid}": name for bid, name in sorted(LANG.items())}
    write_json(ASSETS / "lang" / "fragments" / "sodablocks.json", lang)

    print(f"sodablocks_gen: emitted assets for {len(LANG)} blocks")


if __name__ == "__main__":
    main()
