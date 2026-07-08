#!/usr/bin/env python3
"""Asset generator for the v4 "depthstone" feature (16 dark-stone palettes x 20 = 320 blocks).

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via lib_gen.seeded). Emits, directly into
src/main/resources:
  - blockstates, block models, item models, items/<id>.json model-definitions
  - loot tables (drop-self; slabs use the vanilla double-drops-2 format)
  - 16x16 block/item textures (Pillow, deterministic seeded noise)
  - recipes (data/copper_inferno/recipe/depthstone/*.json)
  - lang fragments: assets/copper_inferno/lang/fragments/depthstone.json (EN)
    and assets/copper_inferno/lang/fragments_de/depthstone.json (real German)
and, into devtools/tagfrag:
  - depthstone.json (block/mineable/pickaxe for every requiresTool id + block/walls)

Every palette registers the 20-id template of core.content.PaletteSets.registerPaletteSet:
  <p>, <p>_slab, <p>_stairs, <p>_wall,
  <p>_bricks, <p>_brick_slab, <p>_brick_stairs, <p>_brick_wall,
  <p>_tiles, <p>_tile_slab, <p>_tile_stairs, <p>_tile_wall,
  chiseled_<p>, carved_<p>, <p>_pillar, cut_<p>,
  <p>_lamp, <p>_lantern, <p>_glass, <p>_glass_pane

JSON structures come from devtools/gen/lib_gen.py (exact copies of the vanilla 1.21.9
formats shipped by cinderstone_gen.py / copperdeco_gen.py). Recipe/lantern-texture
formats are copied from cinderstone_gen.py. Do NOT "improve" them.

Recipe scheme (unique-input rule): the palette base is crafted 2x2 from 3x
minecraft:cobbled_deepslate + 1 palette-unique vanilla item (16 distinct items across the
16 palettes), and every other shape is crafted/stonecut from the palette's own blocks, so
no two crafting recipes (vanilla or mod) share a canonical input set.
"""

import sys
from pathlib import Path
from random import Random

sys.path.insert(0, str(Path(__file__).resolve().parent))

from PIL import Image

from lib_gen import (NS, brick_overlay, emit_cube, emit_cube_family, emit_glass, emit_lamp,
                     emit_lantern, emit_pane, emit_pillar, glass_frame, lamp_glow, merge,
                     noise_cube, pane_edge, pillar_side, pillar_top, seeded, tile_overlay,
                     write_files, write_json)

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS

# ---------------------------------------------------------------------------
# The 16 dark mineral palettes. Colors are dark->light ramps; "unique" is the
# palette-unique vanilla crafting item (16 distinct across all palettes).
# Order here == registration/creative-tab order in DepthStoneFeature.
# ---------------------------------------------------------------------------
PALETTES = {
    "voidstone": {
        "unique": "minecraft:obsidian", "en": "Voidstone", "de": "Leerenstein",
        "dark": (0x14, 0x10, 0x1E), "mid": (0x22, 0x1C, 0x30), "light": (0x33, 0x2B, 0x45),
        "mortar": (0x0B, 0x08, 0x12), "accent": (0x6B, 0x4F, 0xA8),
    },
    "duskshale": {
        "unique": "minecraft:deepslate", "en": "Duskshale", "de": "D\u00e4mmerschiefer",
        "dark": (0x1E, 0x24, 0x2E), "mid": (0x2C, 0x35, 0x42), "light": (0x40, 0x4C, 0x5C),
        "mortar": (0x12, 0x16, 0x1D), "accent": (0x7A, 0x93, 0xB2),
    },
    "pyroclast": {
        "unique": "minecraft:magma_block", "en": "Pyroclast", "de": "Pyroklastit",
        "dark": (0x26, 0x1B, 0x18), "mid": (0x38, 0x28, 0x22), "light": (0x4C, 0x37, 0x2D),
        "mortar": (0x17, 0x0F, 0x0D), "accent": (0xE2, 0x58, 0x22),
    },
    "cindermarl": {
        "unique": "minecraft:charcoal", "en": "Cindermarl", "de": "Zundermergel",
        "dark": (0x2A, 0x26, 0x21), "mid": (0x3B, 0x36, 0x2F), "light": (0x50, 0x49, 0x40),
        "mortar": (0x1A, 0x17, 0x13), "accent": (0xB9, 0x8E, 0x52),
    },
    "fumarolite": {
        "unique": "minecraft:basalt", "en": "Fumarolite", "de": "Fumarolith",
        "dark": (0x28, 0x28, 0x22), "mid": (0x3A, 0x3A, 0x30), "light": (0x4F, 0x4E, 0x40),
        "mortar": (0x18, 0x18, 0x13), "accent": (0xC9, 0xB4, 0x4B),
    },
    "scorchslate": {
        "unique": "minecraft:blackstone", "en": "Scorchslate", "de": "Sengschiefer",
        "dark": (0x21, 0x1D, 0x1C), "mid": (0x30, 0x2A, 0x28), "light": (0x43, 0x3A, 0x37),
        "mortar": (0x13, 0x10, 0x0F), "accent": (0xD8, 0x6A, 0x2E),
    },
    "emberchert": {
        "unique": "minecraft:flint", "en": "Emberchert", "de": "Gluthornstein",
        "dark": (0x2C, 0x20, 0x1B), "mid": (0x3E, 0x2D, 0x25), "light": (0x54, 0x3D, 0x31),
        "mortar": (0x1B, 0x12, 0x0E), "accent": (0xE8, 0x7A, 0x36),
    },
    "slagbasalt": {
        "unique": "minecraft:smooth_basalt", "en": "Slagbasalt", "de": "Schlackenbasalt",
        "dark": (0x20, 0x26, 0x26), "mid": (0x2F, 0x38, 0x37), "light": (0x41, 0x4D, 0x4B),
        "mortar": (0x13, 0x18, 0x18), "accent": (0x5E, 0x8C, 0x82),
    },
    "ashflint": {
        "unique": "minecraft:gravel", "en": "Ashflint", "de": "Aschenfeuerstein",
        "dark": (0x33, 0x32, 0x30), "mid": (0x47, 0x45, 0x42), "light": (0x5E, 0x5B, 0x57),
        "mortar": (0x20, 0x1F, 0x1D), "accent": (0x8F, 0x8B, 0x85),
    },
    "charwacke": {
        "unique": "minecraft:coal", "en": "Charwacke", "de": "Kohlegrauwacke",
        "de_stem": "Kohlegrauwacken", "de_fem": True,
        "dark": (0x23, 0x1E, 0x19), "mid": (0x33, 0x2C, 0x24), "light": (0x46, 0x3D, 0x33),
        "mortar": (0x14, 0x11, 0x0D), "accent": (0x86, 0x6F, 0x4E),
    },
    "smokestone": {
        "unique": "minecraft:tuff", "en": "Smokestone", "de": "Rauchstein",
        "dark": (0x2A, 0x2A, 0x2E), "mid": (0x3C, 0x3C, 0x41), "light": (0x51, 0x51, 0x57),
        "mortar": (0x1A, 0x1A, 0x1E), "accent": (0x88, 0x88, 0x93),
    },
    "kilnrock": {
        "unique": "minecraft:brick", "en": "Kilnrock", "de": "Brennofenstein",
        "dark": (0x30, 0x20, 0x1B), "mid": (0x44, 0x2D, 0x25), "light": (0x5B, 0x3D, 0x31),
        "mortar": (0x1E, 0x12, 0x0E), "accent": (0xC4, 0x6A, 0x3C),
    },
    "magmarl": {
        "unique": "minecraft:netherrack", "en": "Magmarl", "de": "Magmamergel",
        "dark": (0x28, 0x1C, 0x16), "mid": (0x3A, 0x29, 0x1F), "light": (0x4E, 0x38, 0x2A),
        "mortar": (0x18, 0x0F, 0x0B), "accent": (0xFF, 0x7A, 0x2F),
    },
    "sootstone": {
        "unique": "minecraft:soul_sand", "en": "Sootstone", "de": "Ru\u00dfstein",
        "dark": (0x18, 0x16, 0x17), "mid": (0x25, 0x22, 0x23), "light": (0x34, 0x30, 0x31),
        "mortar": (0x0D, 0x0C, 0x0C), "accent": (0x50, 0x4A, 0x4C),
    },
    "vitricite": {
        "unique": "minecraft:amethyst_shard", "en": "Vitricite", "de": "Vitrizit",
        "dark": (0x16, 0x1C, 0x26), "mid": (0x23, 0x2D, 0x3B), "light": (0x33, 0x41, 0x54),
        "mortar": (0x0C, 0x11, 0x18), "accent": (0x62, 0x90, 0xB8),
    },
    "coalspar": {
        "unique": "minecraft:coal_block", "en": "Coalspar", "de": "Kohlenspat",
        "dark": (0x1C, 0x1A, 0x1B), "mid": (0x2A, 0x27, 0x28), "light": (0x3B, 0x37, 0x38),
        "mortar": (0x0F, 0x0E, 0x0E), "accent": (0xC8, 0xC4, 0xBE),
    },
}

assert len({spec["unique"] for spec in PALETTES.values()}) == 16, "unique items must be distinct"


def palette_ids(p: str) -> list[str]:
    """The 20 ids of one palette, in the PaletteSets canonical order."""
    return [p, f"{p}_slab", f"{p}_stairs", f"{p}_wall",
            f"{p}_bricks", f"{p}_brick_slab", f"{p}_brick_stairs", f"{p}_brick_wall",
            f"{p}_tiles", f"{p}_tile_slab", f"{p}_tile_stairs", f"{p}_tile_wall",
            f"chiseled_{p}", f"carved_{p}", f"{p}_pillar", f"cut_{p}",
            f"{p}_lamp", f"{p}_lantern", f"{p}_glass", f"{p}_glass_pane"]


# ---------------------------------------------------------------------------
# JSON: blockstates/models/item-defs/loot via the shared lib_gen emitters.
# ---------------------------------------------------------------------------


def emit_palette_json(p: str) -> dict:
    return merge(
        emit_cube_family(p, p),
        emit_cube_family(f"{p}_bricks", f"{p}_brick"),
        emit_cube_family(f"{p}_tiles", f"{p}_tile"),
        emit_cube(f"chiseled_{p}"),
        emit_cube(f"carved_{p}"),
        emit_pillar(f"{p}_pillar"),
        emit_cube(f"cut_{p}"),
        emit_lamp(f"{p}_lamp"),
        emit_lantern(f"{p}_lantern"),
        emit_glass(f"{p}_glass"),
        emit_pane(f"{p}_glass_pane", f"{p}_glass"),
    )


# ---------------------------------------------------------------------------
# Recipes (formats copied verbatim from cinderstone_gen.py / vanilla 1.21.9).
# ---------------------------------------------------------------------------


def palette_recipes(p: str, unique: str) -> dict:
    ci = f"{NS}:"
    files = {}

    def path(name: str) -> str:
        return f"data/{NS}/recipe/depthstone/{name}.json"

    def shaped(name, key, pattern, result_id, count, category="building"):
        files[path(name)] = {
            "type": "minecraft:crafting_shaped",
            "category": category,
            "key": key,
            "pattern": pattern,
            "result": {"count": count, "id": result_id},
        }

    def shapeless(name, ingredients, result_id, count):
        files[path(name)] = {
            "type": "minecraft:crafting_shapeless",
            "category": "building",
            "ingredients": ingredients,
            "result": {"count": count, "id": result_id},
        }

    def stonecut(result_name, count):
        files[path(f"{result_name}_from_{p}_stonecutting")] = {
            "type": "minecraft:stonecutting",
            "ingredient": f"{ci}{p}",
            "result": {"count": count, "id": f"{ci}{result_name}"},
        }

    base = f"{ci}{p}"
    bricks = f"{ci}{p}_bricks"
    tiles = f"{ci}{p}_tiles"

    # Base: 2x2 from 3x cobbled deepslate + the palette-unique vanilla item.
    shaped(p, {"D": "minecraft:cobbled_deepslate", "U": unique}, ["UD", "DD"], base, 4)

    # Base family shapes.
    shaped(f"{p}_slab", {"#": base}, ["###"], f"{ci}{p}_slab", 6)
    shaped(f"{p}_stairs", {"#": base}, ["#  ", "## ", "###"], f"{ci}{p}_stairs", 4)
    shaped(f"{p}_wall", {"#": base}, ["###", "###"], f"{ci}{p}_wall", 6, category="misc")

    # Bricks family (bricks from the base cube).
    shaped(f"{p}_bricks", {"#": base}, ["##", "##"], bricks, 4)
    shaped(f"{p}_brick_slab", {"#": bricks}, ["###"], f"{ci}{p}_brick_slab", 6)
    shaped(f"{p}_brick_stairs", {"#": bricks}, ["#  ", "## ", "###"], f"{ci}{p}_brick_stairs", 4)
    shaped(f"{p}_brick_wall", {"#": bricks}, ["###", "###"], f"{ci}{p}_brick_wall", 6,
           category="misc")

    # Tiles family (tiles from the bricks).
    shaped(f"{p}_tiles", {"#": bricks}, ["##", "##"], tiles, 4)
    shaped(f"{p}_tile_slab", {"#": tiles}, ["###"], f"{ci}{p}_tile_slab", 6)
    shaped(f"{p}_tile_stairs", {"#": tiles}, ["#  ", "## ", "###"], f"{ci}{p}_tile_stairs", 4)
    shaped(f"{p}_tile_wall", {"#": tiles}, ["###", "###"], f"{ci}{p}_tile_wall", 6,
           category="misc")

    # Singles (vanilla chiseled/pillar/cut layouts; input sets stay palette-unique).
    shaped(f"chiseled_{p}", {"#": f"{ci}{p}_brick_slab"}, ["#", "#"], f"{ci}chiseled_{p}", 1)
    shaped(f"carved_{p}", {"#": f"{ci}{p}_tile_slab"}, ["#", "#"], f"{ci}carved_{p}", 1)
    shaped(f"{p}_pillar", {"#": base}, ["#", "#"], f"{ci}{p}_pillar", 2)
    shaped(f"cut_{p}", {"#": f"{ci}{p}_slab"}, ["#", "#"], f"{ci}cut_{p}", 1)

    # Light sources + glass.
    shapeless(f"{p}_lamp", ["minecraft:glowstone", base], f"{ci}{p}_lamp", 1)
    shaped(f"{p}_lantern", {"N": "minecraft:iron_nugget", "L": f"{ci}{p}_lamp"},
           ["NNN", "NLN", "NNN"], f"{ci}{p}_lantern", 1)
    shaped(f"{p}_glass", {"G": "minecraft:glass", "#": base},
           ["GGG", "G#G", "GGG"], f"{ci}{p}_glass", 8)
    shaped(f"{p}_glass_pane", {"G": f"{ci}{p}_glass"}, ["GGG", "GGG"],
           f"{ci}{p}_glass_pane", 16)

    # Stonecutting: every stone shape cut straight from the palette base.
    for result, count in ((f"{p}_slab", 2), (f"{p}_stairs", 1), (f"{p}_wall", 1),
                          (f"{p}_bricks", 1), (f"{p}_brick_slab", 2), (f"{p}_brick_stairs", 1),
                          (f"{p}_brick_wall", 1), (f"{p}_tiles", 1), (f"{p}_tile_slab", 2),
                          (f"{p}_tile_stairs", 1), (f"{p}_tile_wall", 1), (f"chiseled_{p}", 1),
                          (f"carved_{p}", 1), (f"{p}_pillar", 1), (f"cut_{p}", 1)):
        stonecut(result, count)

    return files


# ---------------------------------------------------------------------------
# Textures. Base shapes use the shared lib_gen primitives; chiseled/carved/cut
# and the lantern painters are parameterized ports of the cinderstone_gen ones.
# ---------------------------------------------------------------------------


def _clamp(v: int) -> int:
    return max(0, min(255, v))


def lighten(rgb, amount: int):
    return (_clamp(rgb[0] + amount), _clamp(rgb[1] + amount), _clamp(rgb[2] + amount))


# Chiseled diamond glyph (copied from cinderstone_gen.DIAMOND_GLYPH).
DIAMOND_GLYPH = [(8, 4), (7, 5), (8, 5), (9, 5), (6, 6), (7, 6), (9, 6), (10, 6),
                 (5, 7), (6, 7), (10, 7), (11, 7), (5, 8), (6, 8), (10, 8), (11, 8),
                 (6, 9), (7, 9), (9, 9), (10, 9), (7, 10), (8, 10), (9, 10), (8, 11)]


def framed(rng: Random, shades, mortar) -> Image.Image:
    """1px mortar frame with a lighter inner bevel row (cinderstone_gen.framed)."""
    img = noise_cube(rng, shades)
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), mortar)
        if 0 < i < 15:
            img.putpixel((i, 1), shades[-1])
    return img


def tex_chiseled(rng: Random, shades, mortar, accent) -> Image.Image:
    img = framed(rng, shades, mortar)
    for x, y in DIAMOND_GLYPH:
        img.putpixel((x, y), accent)
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), lighten(accent, 40))
    return img


def tex_carved(rng: Random, shades, mortar, accent) -> Image.Image:
    """Concentric carved squares with accent corner dots (cinderstone carved port)."""
    img = noise_cube(rng, [shades[1], shades[1], shades[-1]])
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), mortar)
    for i in range(4, 12):
        for x, y in ((i, 4), (i, 11), (4, i), (11, i)):
            img.putpixel((x, y), shades[0])
    for i in range(7, 9):
        for x, y in ((i, 7), (i, 8), (7, i), (8, i)):
            img.putpixel((x, y), shades[-1])
    for x, y in [(4, 4), (11, 4), (4, 11), (11, 11)]:
        img.putpixel((x, y), accent)
    return img


def tex_cut(rng: Random, shades, mortar) -> Image.Image:
    """Smooth slab face with a 1px bevel frame (cinderstone polished port)."""
    img = noise_cube(rng, [shades[1], shades[1], shades[-1]])
    for i in range(16):
        img.putpixel((i, 0), shades[-1])
        img.putpixel((0, i), shades[-1])
        img.putpixel((i, 15), mortar)
        img.putpixel((15, i), mortar)
    return img


def lantern_texture(rng: Random, metal, flame) -> Image.Image:
    """Lantern texture in the vanilla template_lantern UV layout (transparent RGBA).

    Regions sampled by the vanilla templates: body sides (0,2)-(5,8), body
    top/bottom (0,9)-(5,14), cap sides (1,0)-(4,1), handle column (11..13, 0..12).
    (Copied verbatim from cinderstone_gen.lantern_texture.)
    """
    m_light, m_base, m_dark, m_darker = metal
    f_light, f_base, f_dark = flame
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(2, 9):  # body sides: caged frame around a glowing window
        for x in range(0, 6):
            if x in (0, 5) or y in (2, 8):
                px[x, y] = (*m_darker, 255)
            elif x in (1, 4) or y == 3:
                px[x, y] = (*m_dark, 255) if (x * 31 + y * 17) % 5 < 3 else (*m_base, 255)
            else:
                px[x, y] = (*f_light, 255) if (x * 31 + y * 17 + 7) % 5 == 0 else (*f_base, 255)
    px[2, 7] = (*f_dark, 255)
    px[3, 5] = (*f_light, 255)
    for y in range(9, 15):  # body top/bottom
        for x in range(0, 6):
            if x in (0, 5) or y in (9, 14):
                px[x, y] = (*m_darker, 255)
            else:
                px[x, y] = (*m_base, 255) if (x * 31 + y * 17) % 5 < 3 else (*m_dark, 255)
    for y in range(0, 2):  # cap sides
        for x in range(1, 5):
            px[x, y] = (*m_dark, 255) if y == 0 else (*m_base, 255)
    for y in range(0, 13):  # handle/chain strip
        px[12, y] = (*m_darker, 255)
        if y % 2 == 0:
            px[11, y] = (*m_dark, 255)
        else:
            px[13, y] = (*m_dark, 255)
    return img


def lantern_item_texture(rng: Random, metal, flame) -> Image.Image:
    """Flat 16x16 item sprite: small caged lantern with glowing window
    (copied verbatim from cinderstone_gen.lantern_item_texture)."""
    m_light, m_base, m_dark, m_darker = metal
    f_light, f_base, f_dark = flame
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    px[7, 1] = (*m_dark, 255)
    px[8, 1] = (*m_dark, 255)
    px[7, 2] = (*m_darker, 255)
    px[8, 2] = (*m_darker, 255)
    for x in range(5, 11):
        px[x, 3] = (*m_dark, 255)
        px[x, 4] = (*m_base, 255)
    for y in range(5, 13):
        for x in range(4, 12):
            if x in (4, 11) or y in (5, 12):
                px[x, y] = (*m_darker, 255)
            elif x in (5, 10):
                px[x, y] = (*m_dark, 255) if (x * 31 + y * 17) % 5 < 3 else (*m_base, 255)
            else:
                px[x, y] = (*f_light, 255) if (x * 31 + y * 17 + 3) % 5 == 0 else (*f_base, 255)
    px[7, 11] = (*f_dark, 255)
    px[8, 7] = (*f_light, 255)
    for x in range(5, 11):
        px[x, 13] = (*m_dark, 255)
    return img


def paint_palette_textures(p: str, spec: dict) -> int:
    dark, mid, light = spec["dark"], spec["mid"], spec["light"]
    mortar, accent = spec["mortar"], spec["accent"]
    shades = [dark, mid, mid, light]
    metal = (light, mid, dark, mortar)
    flame = (lighten(accent, 80), lighten(accent, 40), accent)
    lamp_cells = [lighten(accent, 80), lighten(accent, 40), lighten(accent, 40), accent]

    block = {
        p: noise_cube(seeded(p), shades),
        f"{p}_bricks": brick_overlay(seeded(f"{p}_bricks"), shades, mortar,
                                     accents=[accent], accent_prob=0.08),
        f"{p}_tiles": tile_overlay(seeded(f"{p}_tiles"), shades, mortar,
                                   accents=[accent], accent_prob=0.05),
        f"chiseled_{p}": tex_chiseled(seeded(f"chiseled_{p}"), shades, mortar, accent),
        f"carved_{p}": tex_carved(seeded(f"carved_{p}"), shades, mortar, accent),
        f"{p}_pillar_side": pillar_side(seeded(f"{p}_pillar_side"), shades, mortar,
                                        [accent], 0.3),
        f"{p}_pillar_top": pillar_top(seeded(f"{p}_pillar_top"), shades, mortar, [accent]),
        f"cut_{p}": tex_cut(seeded(f"cut_{p}"), shades, mortar),
        f"{p}_lamp": lamp_glow(seeded(f"{p}_lamp"), [dark, mid], mortar, lamp_cells),
        f"{p}_lantern": lantern_texture(seeded(f"{p}_lantern"), metal, flame),
        f"{p}_glass": glass_frame(seeded(f"{p}_glass"), mortar, mid, lighten(mid, 40)),
        f"{p}_glass_pane_top": pane_edge(seeded(f"{p}_glass_pane_top"), mid, dark, mortar),
    }
    block_dir = ASSETS / "textures" / "block"
    block_dir.mkdir(parents=True, exist_ok=True)
    for name, img in block.items():
        img.save(block_dir / f"{name}.png")

    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    lantern_item_texture(seeded(f"item/{p}_lantern"), metal, flame) \
        .save(item_dir / f"{p}_lantern.png")
    return len(block) + 1


# ---------------------------------------------------------------------------
# Lang (EN + real German). Keys use the block-prefixed translation key that
# ModBlocks.register applies to every BlockItem.
# ---------------------------------------------------------------------------


def en_names(p: str, en: str) -> dict:
    return {
        p: en,
        f"{p}_slab": f"{en} Slab",
        f"{p}_stairs": f"{en} Stairs",
        f"{p}_wall": f"{en} Wall",
        f"{p}_bricks": f"{en} Bricks",
        f"{p}_brick_slab": f"{en} Brick Slab",
        f"{p}_brick_stairs": f"{en} Brick Stairs",
        f"{p}_brick_wall": f"{en} Brick Wall",
        f"{p}_tiles": f"{en} Tiles",
        f"{p}_tile_slab": f"{en} Tile Slab",
        f"{p}_tile_stairs": f"{en} Tile Stairs",
        f"{p}_tile_wall": f"{en} Tile Wall",
        f"chiseled_{p}": f"Chiseled {en}",
        f"carved_{p}": f"Carved {en}",
        f"{p}_pillar": f"{en} Pillar",
        f"cut_{p}": f"Cut {en}",
        f"{p}_lamp": f"{en} Lamp",
        f"{p}_lantern": f"{en} Lantern",
        f"{p}_glass": f"{en} Glass",
        f"{p}_glass_pane": f"{en} Glass Pane",
    }


def de_names(p: str, spec: dict) -> dict:
    base = spec["de"]
    stem = spec.get("de_stem", base)
    fem = spec.get("de_fem", False)

    def adj(masc: str, femi: str) -> str:
        return femi if fem else masc

    return {
        p: base,
        f"{p}_slab": f"{stem}stufe",
        f"{p}_stairs": f"{stem}treppe",
        f"{p}_wall": f"{stem}mauer",
        f"{p}_bricks": f"{stem}ziegel",
        f"{p}_brick_slab": f"{stem}ziegelstufe",
        f"{p}_brick_stairs": f"{stem}ziegeltreppe",
        f"{p}_brick_wall": f"{stem}ziegelmauer",
        f"{p}_tiles": f"{stem}fliesen",
        f"{p}_tile_slab": f"{stem}fliesenstufe",
        f"{p}_tile_stairs": f"{stem}fliesentreppe",
        f"{p}_tile_wall": f"{stem}fliesenmauer",
        f"chiseled_{p}": adj("Gemei\u00dfelter ", "Gemei\u00dfelte ") + base,
        f"carved_{p}": adj("Verzierter ", "Verzierte ") + base,
        f"{p}_pillar": f"{stem}s\u00e4ule",
        f"cut_{p}": adj("Geschnittener ", "Geschnittene ") + base,
        f"{p}_lamp": f"{stem}lampe",
        f"{p}_lantern": f"{stem}laterne",
        f"{p}_glass": f"{stem}glas",
        f"{p}_glass_pane": f"{stem}glasscheibe",
    }


# ---------------------------------------------------------------------------
# Tag fragment: pickaxe-mineable for every requiresTool id (glass + pane are
# registered WITHOUT requiresTool, exactly like the cinderstone glass), plus
# the three walls/slabs/stairs per palette for block/walls, block/slabs and
# block/stairs (mirrors chromacopper_gen.tag_fragment).
# ---------------------------------------------------------------------------


def tagfrag() -> dict:
    pickaxe, walls, slabs, stairs = [], [], [], []
    for p in PALETTES:
        for bid in palette_ids(p):
            if bid in (f"{p}_glass", f"{p}_glass_pane"):
                continue
            pickaxe.append(f"{NS}:{bid}")
        walls += [f"{NS}:{p}_wall", f"{NS}:{p}_brick_wall", f"{NS}:{p}_tile_wall"]
        slabs += [f"{NS}:{p}_slab", f"{NS}:{p}_brick_slab", f"{NS}:{p}_tile_slab"]
        stairs += [f"{NS}:{p}_stairs", f"{NS}:{p}_brick_stairs", f"{NS}:{p}_tile_stairs"]
    return {
        "block/mineable/pickaxe": sorted(pickaxe),
        "block/walls": sorted(walls),
        "block/slabs": sorted(slabs),
        "block/stairs": sorted(stairs),
    }


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    files = {}
    ids = []
    lang_en = {}
    lang_de = {}
    tex_count = 0
    for p, spec in PALETTES.items():
        files = merge(files, emit_palette_json(p), palette_recipes(p, spec["unique"]))
        tex_count += paint_palette_textures(p, spec)
        ids += palette_ids(p)
        lang_en.update({f"block.{NS}.{k}": v for k, v in en_names(p, spec["en"]).items()})
        lang_de.update({f"block.{NS}.{k}": v for k, v in de_names(p, spec).items()})

    json_count = write_files(files, RES)
    write_json(ASSETS / "lang" / "fragments" / "depthstone.json", lang_en)
    write_json(ASSETS / "lang" / "fragments_de" / "depthstone.json", lang_de)
    frag = tagfrag()
    write_json(ROOT / "devtools" / "tagfrag" / "depthstone.json", frag)

    assert len(frag["block/slabs"]) == 48, \
        f"expected 48 slabs, got {len(frag['block/slabs'])}"
    assert len(frag["block/stairs"]) == 48, \
        f"expected 48 stairs, got {len(frag['block/stairs'])}"
    assert len(ids) == 320, f"expected 320 block ids, got {len(ids)}"
    assert len(set(ids)) == 320, "duplicate ids across palettes"
    assert sorted(f"block.{NS}.{i}" for i in ids) == sorted(lang_en) == sorted(lang_de)
    blockstates = [f for f in files if f.startswith(f"assets/{NS}/blockstates/")]
    items = [f for f in files if f.startswith(f"assets/{NS}/items/")]
    loot = [f for f in files if f.startswith(f"data/{NS}/loot_table/")]
    recipes = [f for f in files if f.startswith(f"data/{NS}/recipe/")]
    assert len(blockstates) == len(items) == len(loot) == 320, \
        f"expected 320 ids, got {len(blockstates)}/{len(items)}/{len(loot)}"
    assert len(recipes) == 16 * 35, f"expected 560 recipes, got {len(recipes)}"
    print(f"depthstone_gen: {json_count} JSON files + {tex_count} textures for "
          f"{len(ids)} blocks across {len(PALETTES)} palettes (+ lang EN/DE + tagfrag).")


if __name__ == "__main__":
    main()
