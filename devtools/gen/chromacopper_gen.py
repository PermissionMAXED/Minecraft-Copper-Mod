#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4 "Chromatic Copper" feature (WP2).

16 tinted-copper palettes x the 20-block PaletteSets template = 320 blocks. Emits, directly
into src/main/resources (plus the tag fragment under devtools/tagfrag):
  - blockstates, block models, item models, items/<id>.json model-definitions
  - 16x16 deterministic textures (lib_gen seeded primitives; 13 PNGs per palette)
  - loot tables (drop-self; slabs use the vanilla double-drops-2 format)
  - recipes (data/copper_inferno/recipe/chromacopper/*.json): base = 8 copper blocks ringed
    around one palette-distinct vanilla dye -> 8; bricks 2x2 from base; tiles 2x2 from
    bricks; slab/stairs/wall shaped from base; lamp = 4 base blocks in a plus around
    glowstone; lantern = 8 iron nuggets ringed around base; glass by smelting base; pane
    6 glass -> 16; stonecutting from base to every non-luminous stone shape (incl.
    chiseled/carved/cut/pillar; slabs cut 2 -- lamps/lanterns are crafted, not stonecut)
  - lang fragments: assets/copper_inferno/lang/fragments/chromacopper.json (EN) and
    fragments_de/chromacopper.json (real German)
  - tag fragment: devtools/tagfrag/chromacopper.json (mineable/pickaxe for everything
    except glass+panes, plus walls/slabs/stairs)

All JSON structures come from devtools/gen/lib_gen.py, whose emitters are exact copies of
the vanilla 1.21.9 formats already shipped by the v3 generators. Idempotent: every texture
is seeded per texture name and PNGs are only rewritten when their bytes change, so running
the script any number of times produces byte-identical output.

NOTE: the "gilded" palette is emitted as tinted_gilded_copper because gilded_copper_bricks
(+ slab/stairs/wall) already exist in the copperdeco feature.
"""

import io
import sys
from pathlib import Path
from random import Random

sys.path.insert(0, str(Path(__file__).resolve().parent))
import lib_gen  # noqa: E402
from lib_gen import (  # noqa: E402
    NS, brick_overlay, glass_frame, lamp_glow, merge, noise_cube, pane_edge,
    pillar_side, pillar_top, seeded, tile_overlay, write_json, _shade,
)

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
TAGFRAG = ROOT / "devtools" / "tagfrag" / "chromacopper.json"

# ---------------------------------------------------------------------------
# The 16 palettes: 3-shade tint ramp (dark -> mid -> light), a palette-distinct
# vanilla dye (all 16 dyes used exactly once) and EN/DE display names.
# Order matches ChromaCopperFeature.init() registration order.
# ---------------------------------------------------------------------------
PALETTES = {
    "azure_copper": {
        "en": "Azure Copper", "de": "Azurkupfer", "dye": "light_blue_dye",
        "shades": ((0x1F, 0x4A, 0x73), (0x2E, 0x6B, 0x9E), (0x55, 0x93, 0xC4)),
    },
    "crimson_copper": {
        "en": "Crimson Copper", "de": "Karmesinkupfer", "dye": "red_dye",
        "shades": ((0x6E, 0x1B, 0x24), (0x9C, 0x2A, 0x33), (0xC8, 0x4A, 0x4C)),
    },
    "tinted_gilded_copper": {
        "en": "Tinted Gilded Copper", "de": "Goldtonkupfer", "dye": "yellow_dye",
        "shades": ((0x8A, 0x62, 0x14), (0xC2, 0x92, 0x2A), (0xE8, 0xC0, 0x5A)),
    },
    "cobalt_copper": {
        "en": "Cobalt Copper", "de": "Kobaltkupfer", "dye": "blue_dye",
        "shades": ((0x1C, 0x2C, 0x6E), (0x2B, 0x41, 0x9C), (0x4C, 0x66, 0xC8)),
    },
    "emerald_copper": {
        "en": "Emerald Copper", "de": "Smaragdkupfer", "dye": "green_dye",
        "shades": ((0x14, 0x5A, 0x30), (0x1E, 0x84, 0x46), (0x46, 0xB0, 0x6A)),
    },
    "amethyst_copper": {
        "en": "Amethyst Copper", "de": "Amethystkupfer", "dye": "purple_dye",
        "shades": ((0x4E, 0x2A, 0x6E), (0x71, 0x41, 0x9C), (0x9A, 0x6A, 0xC4)),
    },
    "obsidian_copper": {
        "en": "Obsidian Copper", "de": "Obsidiankupfer", "dye": "black_dye",
        "shades": ((0x15, 0x10, 0x1E), (0x24, 0x1C, 0x33), (0x3C, 0x32, 0x50)),
    },
    "ivory_copper": {
        "en": "Ivory Copper", "de": "Elfenbeinkupfer", "dye": "white_dye",
        "shades": ((0xA8, 0x9E, 0x88), (0xD0, 0xC8, 0xB0), (0xEC, 0xE6, 0xD4)),
    },
    "jade_copper": {
        "en": "Jade Copper", "de": "Jadekupfer", "dye": "lime_dye",
        "shades": ((0x2A, 0x6E, 0x4A), (0x3F, 0x9C, 0x69), (0x6E, 0xC4, 0x93)),
    },
    "umber_copper": {
        "en": "Umber Copper", "de": "Umbrakupfer", "dye": "brown_dye",
        "shades": ((0x4A, 0x32, 0x1E), (0x6E, 0x4A, 0x2C), (0x94, 0x68, 0x42)),
    },
    "scarlet_copper": {
        "en": "Scarlet Copper", "de": "Scharlachkupfer", "dye": "pink_dye",
        "shades": ((0x8A, 0x1E, 0x14), (0xC2, 0x30, 0x1E), (0xE8, 0x5A, 0x3C)),
    },
    "indigo_copper": {
        "en": "Indigo Copper", "de": "Indigokupfer", "dye": "magenta_dye",
        "shades": ((0x2C, 0x1E, 0x5A), (0x41, 0x2C, 0x84), (0x64, 0x4C, 0xB0)),
    },
    "viridian_copper": {
        "en": "Viridian Copper", "de": "Viridiankupfer", "dye": "cyan_dye",
        "shades": ((0x14, 0x50, 0x50), (0x1E, 0x78, 0x74), (0x3C, 0xA0, 0x98)),
    },
    "onyx_copper": {
        "en": "Onyx Copper", "de": "Onyxkupfer", "dye": "gray_dye",
        "shades": ((0x1A, 0x1A, 0x1E), (0x2C, 0x2C, 0x33), (0x46, 0x46, 0x50)),
    },
    "pearl_copper": {
        "en": "Pearl Copper", "de": "Perlkupfer", "dye": "light_gray_dye",
        "shades": ((0x9E, 0x9A, 0xA0), (0xC4, 0xC2, 0xC8), (0xE4, 0xE2, 0xE8)),
    },
    "saffron_copper": {
        "en": "Saffron Copper", "de": "Safrankupfer", "dye": "orange_dye",
        "shades": ((0x9C, 0x5A, 0x14), (0xC8, 0x82, 0x1E), (0xE8, 0xAC, 0x46)),
    },
}

assert len(PALETTES) == 16
assert len({spec["dye"] for spec in PALETTES.values()}) == 16, "dyes must be distinct"


def palette_ids(p: str) -> list[str]:
    """The 20 ids of one palette, in PaletteSets.registerPaletteSet order."""
    return [p, f"{p}_slab", f"{p}_stairs", f"{p}_wall",
            f"{p}_bricks", f"{p}_brick_slab", f"{p}_brick_stairs", f"{p}_brick_wall",
            f"{p}_tiles", f"{p}_tile_slab", f"{p}_tile_stairs", f"{p}_tile_wall",
            f"chiseled_{p}", f"carved_{p}", f"{p}_pillar", f"cut_{p}",
            f"{p}_lamp", f"{p}_lantern", f"{p}_glass", f"{p}_glass_pane"]


# ---------------------------------------------------------------------------
# Lang (EN + real German), shape templates applied to the per-palette base name.
# German compounds follow the cinderstone fragment style (-stufe/-treppe/-mauer/
# -ziegel/-fliesen...); Kupfer is neuter, hence "Gemeisseltes/Verziertes/
# Geschnittenes <X>".
# ---------------------------------------------------------------------------
SHAPE_LANG = [
    # (id builder key aligned with palette_ids order, EN template, DE template)
    ("{p}",             "{n}",              "{n}"),
    ("{p}_slab",        "{n} Slab",         "{n}stufe"),
    ("{p}_stairs",      "{n} Stairs",       "{n}treppe"),
    ("{p}_wall",        "{n} Wall",         "{n}mauer"),
    ("{p}_bricks",      "{n} Bricks",       "{n}ziegel"),
    ("{p}_brick_slab",  "{n} Brick Slab",   "{n}ziegelstufe"),
    ("{p}_brick_stairs", "{n} Brick Stairs", "{n}ziegeltreppe"),
    ("{p}_brick_wall",  "{n} Brick Wall",   "{n}ziegelmauer"),
    ("{p}_tiles",       "{n} Tiles",        "{n}fliesen"),
    ("{p}_tile_slab",   "{n} Tile Slab",    "{n}fliesenstufe"),
    ("{p}_tile_stairs", "{n} Tile Stairs",  "{n}fliesentreppe"),
    ("{p}_tile_wall",   "{n} Tile Wall",    "{n}fliesenmauer"),
    ("chiseled_{p}",    "Chiseled {n}",     "Gemei\u00dfeltes {n}"),
    ("carved_{p}",      "Carved {n}",       "Verziertes {n}"),
    ("{p}_pillar",      "{n} Pillar",       "{n}s\u00e4ule"),
    ("cut_{p}",         "Cut {n}",          "Geschnittenes {n}"),
    ("{p}_lamp",        "{n} Lamp",         "{n}lampe"),
    ("{p}_lantern",     "{n} Lantern",      "{n}laterne"),
    ("{p}_glass",       "{n} Glass",        "{n}glas"),
    ("{p}_glass_pane",  "{n} Glass Pane",   "{n}glasscheibe"),
]


def palette_lang(p: str, spec: dict) -> tuple[dict, dict]:
    en, de = {}, {}
    for id_tpl, en_tpl, de_tpl in SHAPE_LANG:
        key = f"block.{NS}." + id_tpl.format(p=p)
        en[key] = en_tpl.format(n=spec["en"])
        de[key] = de_tpl.format(n=spec["de"])
    return en, de


# ---------------------------------------------------------------------------
# JSON (blockstates/models/items/loot) via the shared lib_gen emitters.
# ---------------------------------------------------------------------------


def emit_palette_json(p: str) -> dict:
    return merge(
        lib_gen.emit_cube_family(p, p),                              # base + slab/stairs/wall
        lib_gen.emit_cube_family(f"{p}_bricks", f"{p}_brick"),       # bricks family
        lib_gen.emit_cube_family(f"{p}_tiles", f"{p}_tile"),         # tiles family
        lib_gen.emit_cube(f"chiseled_{p}"),
        lib_gen.emit_cube(f"carved_{p}"),
        lib_gen.emit_pillar(f"{p}_pillar"),
        lib_gen.emit_cube(f"cut_{p}"),
        lib_gen.emit_lamp(f"{p}_lamp"),
        lib_gen.emit_lantern(f"{p}_lantern"),
        lib_gen.emit_glass(f"{p}_glass"),
        lib_gen.emit_pane(f"{p}_glass_pane", f"{p}_glass"),
    )


# ---------------------------------------------------------------------------
# Recipes (vanilla formats, verbatim from cinderstone_gen.py).
# ---------------------------------------------------------------------------


def _recipe(name: str) -> str:
    return f"data/{NS}/recipe/chromacopper/{name}.json"


def shaped(name: str, key: dict, pattern: list, result_id: str, count: int,
           category: str = "building") -> dict:
    return {_recipe(name): {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": result_id},
    }}


def smelting(name: str, ingredient: str, result_id: str) -> dict:
    # Format copied from vanilla cracked_stone_bricks.json.
    return {_recipe(name): {
        "type": "minecraft:smelting",
        "category": "blocks",
        "cookingtime": 200,
        "experience": 0.1,
        "ingredient": ingredient,
        "result": {"id": result_id},
    }}


def stonecutting(name: str, ingredient: str, result_id: str, count: int) -> dict:
    # Format == vanilla stone_brick_wall_from_stone_bricks_stonecutting.json.
    return {_recipe(name): {
        "type": "minecraft:stonecutting",
        "ingredient": ingredient,
        "result": {"count": count, "id": result_id},
    }}


# Every non-glass, non-luminous shape is stonecuttable straight from the base block
# (slabs cut 2). Lamps/lanterns are deliberately NOT stonecut -- a 1:1 cut would be a
# free light source; they have proper shaped recipes in emit_palette_recipes instead.
STONECUT_TARGETS = [
    ("{p}_slab", 2), ("{p}_stairs", 1), ("{p}_wall", 1),
    ("{p}_bricks", 1), ("{p}_brick_slab", 2), ("{p}_brick_stairs", 1), ("{p}_brick_wall", 1),
    ("{p}_tiles", 1), ("{p}_tile_slab", 2), ("{p}_tile_stairs", 1), ("{p}_tile_wall", 1),
    ("chiseled_{p}", 1), ("carved_{p}", 1), ("{p}_pillar", 1), ("cut_{p}", 1),
]


def emit_palette_recipes(p: str, spec: dict) -> dict:
    ci = f"{NS}:"
    base = f"{ci}{p}"
    files = merge(
        # UNIQUE-INPUT ring: 8 copper blocks around this palette's distinct dye.
        shaped(p, {"C": "minecraft:copper_block", "D": f"minecraft:{spec['dye']}"},
               ["CCC", "CDC", "CCC"], base, 8),
        shaped(f"{p}_bricks", {"#": base}, ["##", "##"], f"{ci}{p}_bricks", 4),
        shaped(f"{p}_tiles", {"#": f"{ci}{p}_bricks"}, ["##", "##"], f"{ci}{p}_tiles", 4),
        shaped(f"{p}_slab", {"#": base}, ["###"], f"{ci}{p}_slab", 6),
        shaped(f"{p}_stairs", {"#": base}, ["#  ", "## ", "###"], f"{ci}{p}_stairs", 4),
        shaped(f"{p}_wall", {"#": base}, ["###", "###"], f"{ci}{p}_wall", 6, category="misc"),
        # Light sources cost extra (unique input: the palette base block).
        shaped(f"{p}_lamp", {"#": base, "G": "minecraft:glowstone"},
               [" # ", "#G#", " # "], f"{ci}{p}_lamp", 1, category="redstone"),
        shaped(f"{p}_lantern", {"N": "minecraft:iron_nugget", "#": base},
               ["NNN", "N#N", "NNN"], f"{ci}{p}_lantern", 1, category="misc"),
        smelting(f"{p}_glass", base, f"{ci}{p}_glass"),
        shaped(f"{p}_glass_pane", {"G": f"{ci}{p}_glass"}, ["GGG", "GGG"],
               f"{ci}{p}_glass_pane", 16),
    )
    for target_tpl, count in STONECUT_TARGETS:
        target = target_tpl.format(p=p)
        files = merge(files, stonecutting(f"{target}_from_{p}_stonecutting",
                                          base, f"{ci}{target}", count))
    return files


# ---------------------------------------------------------------------------
# Textures (16x16, deterministic, seeded per texture name). Base shapes come
# straight from the lib_gen primitives; the framed/carved/cut/lantern painters
# are ports of the cinderstone_gen painters parameterized on the tint ramp.
# ---------------------------------------------------------------------------

from PIL import Image  # noqa: E402


def framed(rng: Random, shades, mortar) -> Image.Image:
    """1px mortar frame with a lighter inner bevel row (chiseled base plate)."""
    img = noise_cube(rng, shades)
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), mortar)
        if 0 < i < 15:
            img.putpixel((i, 1), shades[-1])
    return img


DIAMOND_GLYPH = [(8, 4), (7, 5), (8, 5), (9, 5), (6, 6), (7, 6), (9, 6), (10, 6),
                 (5, 7), (6, 7), (10, 7), (11, 7), (5, 8), (6, 8), (10, 8), (11, 8),
                 (6, 9), (7, 9), (9, 9), (10, 9), (7, 10), (8, 10), (9, 10), (8, 11)]


def chiseled_tex(rng: Random, shades, mortar, accent, accent_hot) -> Image.Image:
    img = framed(rng, shades, mortar)
    for x, y in DIAMOND_GLYPH:
        img.putpixel((x, y), accent)
    for x, y in [(8, 7), (8, 8), (7, 8), (7, 7)]:
        img.putpixel((x, y), accent_hot)
    return img


def carved_tex(rng: Random, dark, mid, light, mortar, accent) -> Image.Image:
    """Concentric carved squares with accent corner dots (carved_cinderstone port)."""
    img = noise_cube(rng, [mid, mid, light])
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), mortar)
    for i in range(4, 12):
        for x, y in ((i, 4), (i, 11), (4, i), (11, i)):
            img.putpixel((x, y), dark)
    for i in range(7, 9):
        for x, y in ((i, 7), (i, 8), (7, i), (8, i)):
            img.putpixel((x, y), light)
    for x, y in [(4, 4), (11, 4), (4, 11), (11, 11)]:
        img.putpixel((x, y), accent)
    return img


def cut_tex(rng: Random, mid, light, mortar) -> Image.Image:
    """Smooth slab with a 1px bevel frame (polished_cinderstone port)."""
    img = noise_cube(rng, [mid, mid, light])
    for i in range(16):
        img.putpixel((i, 0), light)
        img.putpixel((0, i), light)
        img.putpixel((i, 15), mortar)
        img.putpixel((15, i), mortar)
    return img


def lantern_tex(rng: Random, metal, flame) -> Image.Image:
    """Lantern texture in the vanilla template_lantern UV layout (transparent RGBA);
    port of cinderstone_gen.lantern_texture parameterized on metal/flame ramps."""
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


def lantern_item_tex(rng: Random, metal, flame) -> Image.Image:
    """Flat 16x16 item sprite: small caged lantern with glowing window (port of
    cinderstone_gen.lantern_item_texture)."""
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


def palette_textures(p: str, spec: dict) -> dict:
    """{texture-path-relative-to-textures/: PIL image} for one palette (13 PNGs)."""
    dark, mid, light = spec["shades"]
    shades = [dark, mid, mid, light]
    mortar = _shade(dark, -20)
    accent = _shade(light, 30)
    accent_hot = _shade(light, 60)
    flame = (accent_hot, accent, light)
    metal = (light, mid, dark, mortar)
    lamp_cells = [accent_hot, accent, accent, light]
    return {
        f"block/{p}": noise_cube(seeded(p), shades),
        f"block/{p}_bricks": brick_overlay(seeded(f"{p}_bricks"), shades, mortar,
                                           accents=[accent], accent_prob=0.06),
        f"block/{p}_tiles": tile_overlay(seeded(f"{p}_tiles"), shades, mortar,
                                         accents=[accent], accent_prob=0.05),
        f"block/chiseled_{p}": chiseled_tex(seeded(f"chiseled_{p}"), shades, mortar,
                                            accent, accent_hot),
        f"block/carved_{p}": carved_tex(seeded(f"carved_{p}"), dark, mid, light,
                                        mortar, accent),
        f"block/cut_{p}": cut_tex(seeded(f"cut_{p}"), mid, light, mortar),
        f"block/{p}_pillar_side": pillar_side(seeded(f"{p}_pillar_side"),
                                              [dark, mid, light], mortar, [accent], 0.25),
        f"block/{p}_pillar_top": pillar_top(seeded(f"{p}_pillar_top"), shades,
                                            mortar, [accent, accent_hot]),
        f"block/{p}_lamp": lamp_glow(seeded(f"{p}_lamp"), [dark, mid], mortar, lamp_cells),
        f"block/{p}_lantern": lantern_tex(seeded(f"{p}_lantern"), metal, flame),
        f"block/{p}_glass": glass_frame(seeded(f"{p}_glass"), mortar, mid, mid),
        f"block/{p}_glass_pane_top": pane_edge(seeded(f"{p}_glass_pane_top"),
                                               mid, dark, mortar),
        f"item/{p}_lantern": lantern_item_tex(seeded(f"item/{p}_lantern"), metal, flame),
    }


def save_png(img: Image.Image, path: Path) -> bool:
    """Write the PNG only when its bytes change (keeps re-runs a true no-op)."""
    path.parent.mkdir(parents=True, exist_ok=True)
    buf = io.BytesIO()
    img.save(buf, format="PNG")
    data = buf.getvalue()
    if path.is_file() and path.read_bytes() == data:
        return False
    path.write_bytes(data)
    return True


# ---------------------------------------------------------------------------
# Tag fragment (merged into data/minecraft/tags by devtools/merge_tags.py).
# ---------------------------------------------------------------------------


def tag_fragment(all_ids_by_palette: dict) -> dict:
    pickaxe, walls, slabs, stairs = [], [], [], []
    for p, ids in all_ids_by_palette.items():
        for bid in ids:
            if bid.endswith("_glass") or bid.endswith("_glass_pane"):
                continue  # no requiresTool -> hand-mineable, keep out of pickaxe
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
    lang_en, lang_de = {}, {}
    ids_by_palette = {}
    for p, spec in PALETTES.items():
        files = merge(files, emit_palette_json(p), emit_palette_recipes(p, spec))
        en, de = palette_lang(p, spec)
        lang_en.update(en)
        lang_de.update(de)
        ids_by_palette[p] = palette_ids(p)

    json_count = lib_gen.write_files(files, RES)

    png_written = 0
    png_total = 0
    for p, spec in PALETTES.items():
        for rel, img in palette_textures(p, spec).items():
            png_total += 1
            if save_png(img, ASSETS / "textures" / f"{rel}.png"):
                png_written += 1

    write_json(ASSETS / "lang" / "fragments" / "chromacopper.json", lang_en)
    write_json(ASSETS / "lang" / "fragments_de" / "chromacopper.json", lang_de)

    frag = tag_fragment(ids_by_palette)
    write_json(TAGFRAG, frag)

    all_ids = [bid for ids in ids_by_palette.values() for bid in ids]
    assert len(all_ids) == 320 and len(set(all_ids)) == 320, f"expected 320 ids, got {len(all_ids)}"
    assert sorted(f"block.{NS}.{i}" for i in all_ids) == sorted(lang_en) == sorted(lang_de)
    assert len(frag["block/mineable/pickaxe"]) == 288
    assert len(frag["block/walls"]) == len(frag["block/slabs"]) == len(frag["block/stairs"]) == 48
    blockstates = [f for f in files if "/blockstates/" in f]
    items = [f for f in files if f"assets/{NS}/items/" in f]
    loot = [f for f in files if "/loot_table/" in f]
    recipes = [f for f in files if "/recipe/" in f]
    assert len(blockstates) == len(items) == len(loot) == 320, \
        f"expected 320 ids, got {len(blockstates)}/{len(items)}/{len(loot)}"
    # 10 crafting/smelting (base, bricks, tiles, slab, stairs, wall, lamp, lantern,
    # glass, pane) + 15 stonecutting per palette.
    assert len(recipes) == 16 * (10 + len(STONECUT_TARGETS)), \
        f"expected {16 * (10 + len(STONECUT_TARGETS))} recipes, got {len(recipes)}"

    print(f"chromacopper_gen: {json_count} JSON files (320 blocks, {len(recipes)} recipes), "
          f"{png_written}/{png_total} PNGs written, lang EN+DE {len(lang_en)}+{len(lang_de)} keys, "
          f"tag fragment {sum(len(v) for v in frag.values())} entries.")


if __name__ == "__main__":
    main()
