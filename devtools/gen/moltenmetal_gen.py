#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "moltenmetal" feature (224 building blocks).

14 molten-metal/alloy materials (ember_brass, slag_bronze, molten_brass, patina_steel,
cinder_iron, forgeworn_metal, blistered_bronze, scalding_steel, smelters_alloy,
furnace_steel, quench_iron, emberchrome, molten_cobalt, ashiron); each ships a 16-block
set: the base cube family, the polished family and the bricks family (base/slab/stairs/
wall each), plus tiles, cracked bricks, chiseled bricks and a pillar.

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via genlib.rng_for). Emits by DEFAULT (no flags):
  - blockstates, block models, items/<id>.json model-definitions (genlib emitters)
  - 16x16 block textures (Pillow, deterministic seeded noise, distinct metallic hue per
    material: brushed plates, rivets, mirror-shine polish, molten fissures)
  - loot tables (drop-self; slabs use the vanilla double-drops-2 format)
  - recipes (data/copper_inferno/recipe/moltenmetal/*.json, 30 per material = 420)
  - lang fragments: assets/copper_inferno/lang/fragments/moltenmetal.json (EN)
    and assets/copper_inferno/lang/fragments_de/moltenmetal.json (real German)
  - devtools/tagfrag/moltenmetal.json (mineable/pickaxe for all 224, walls for the 42 walls)
  - src/main/java/.../feature/moltenmetal/MoltenMetalFeature.java + MoltenMetalHandbook.java
    (genlib.java_feature_class / genlib.java_handbook_class; literal ids only)
  - devtools/hooks/moltenmetal.txt (integration hook file)

All JSON structures come from genlib and are byte-identical to the vanilla 1.21.9 formats
used by devtools/gen/cinderstone_gen.py. Do NOT "improve" them.
"""

import sys
from collections import namedtuple
from pathlib import Path

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, rng_for, write_json

RECIPES = DATA / "recipe" / "moltenmetal"
FEATURE_DIR = ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "moltenmetal"

# ---------------------------------------------------------------------------
# Materials. Each gets a DISTINCT deterministic metallic palette:
#   shades = [dark, mid, mid, light] (genlib stone-look 4-tuple), seam (mortar), two
#   glint accents. de/de_stem/de_pol drive the German lang conventions (Stufe/Treppe/
#   Mauer/Ziegel/Fliesen/Saeule compounds; "Polierter"/"Polierte"/"Poliertes" by
#   grammatical gender; de_stem carries the joining -s- where compounds need it).
# vanilla = the flavor vanilla ingredient of the base recipe; the recipe's centre slot is
# the PREVIOUS material's base block (ring chain), so every crafting recipe contains at
# least one own id and can never collide with vanilla or other features' recipes.
# ---------------------------------------------------------------------------
Mat = namedtuple("Mat", "mid en de de_stem de_pol vanilla map_color sounds "
                        "shades mortar accents base_prob brick_prob")

MATERIALS = [
    Mat("ember_brass", "Ember Brass", "Glutmessing", "Glutmessing", "Poliertes",
        "minecraft:copper_ingot", "GOLD", "COPPER",
        [(0x7A, 0x5A, 0x1E), (0x9C, 0x74, 0x28), (0x9C, 0x74, 0x28), (0xC2, 0x92, 0x38)],
        (0x4A, 0x36, 0x12), [(0xE8, 0xBE, 0x5A), (0xFF, 0xDE, 0x82)], 0.04, 0.10),
    Mat("slag_bronze", "Slag Bronze", "Schlackenbronze", "Schlackenbronze", "Polierte",
        "minecraft:raw_copper", "TERRACOTTA_BROWN", "COPPER",
        [(0x5C, 0x3A, 0x22), (0x74, 0x4A, 0x2C), (0x74, 0x4A, 0x2C), (0x8E, 0x5E, 0x38)],
        (0x38, 0x22, 0x14), [(0xB0, 0x7A, 0x46), (0x6E, 0x8A, 0x6A)], 0.03, 0.08),
    Mat("molten_brass", "Molten Brass", "Schmelzmessing", "Schmelzmessing", "Poliertes",
        "minecraft:gold_nugget", "ORANGE", "COPPER",
        [(0x8A, 0x58, 0x16), (0xAA, 0x70, 0x1E), (0xAA, 0x70, 0x1E), (0xCC, 0x8C, 0x2A)],
        (0x54, 0x34, 0x0E), [(0xFF, 0x9A, 0x30), (0xFF, 0xC8, 0x50)], 0.06, 0.20),
    Mat("patina_steel", "Patina Steel", "Patinastahl", "Patinastahl", "Polierter",
        "minecraft:oxidized_copper", "TEAL", "METAL",
        [(0x3A, 0x54, 0x50), (0x4A, 0x6A, 0x64), (0x4A, 0x6A, 0x64), (0x60, 0x84, 0x7C)],
        (0x24, 0x34, 0x32), [(0x7A, 0xB0, 0x9E), (0x9E, 0xD2, 0xBE)], 0.03, 0.08),
    Mat("cinder_iron", "Cinder Iron", "Zundereisen", "Zundereisen", "Poliertes",
        "minecraft:iron_nugget", "IRON_GRAY", "IRON",
        [(0x38, 0x34, 0x34), (0x4A, 0x44, 0x44), (0x4A, 0x44, 0x44), (0x5E, 0x56, 0x56)],
        (0x22, 0x1F, 0x1F), [(0xE2, 0x58, 0x22), (0xFF, 0x7A, 0x2F)], 0.04, 0.12),
    Mat("forgeworn_metal", "Forgeworn Metal", "Essenmetall", "Essenmetall", "Poliertes",
        "minecraft:iron_bars", "DEEPSLATE_GRAY", "NETHERITE",
        [(0x2E, 0x2C, 0x30), (0x3E, 0x3B, 0x40), (0x3E, 0x3B, 0x40), (0x50, 0x4C, 0x52)],
        (0x1C, 0x1A, 0x1E), [(0x6E, 0x68, 0x72), (0x8C, 0x84, 0x90)], 0.03, 0.05),
    Mat("blistered_bronze", "Blistered Bronze", "Blasenbronze", "Blasenbronze", "Polierte",
        "minecraft:magma_cream", "TERRACOTTA_ORANGE", "COPPER",
        [(0x6E, 0x40, 0x1E), (0x8A, 0x52, 0x26), (0x8A, 0x52, 0x26), (0xA8, 0x66, 0x30)],
        (0x44, 0x26, 0x10), [(0xE2, 0x58, 0x22), (0xD8, 0x8E, 0x4A)], 0.05, 0.14),
    Mat("scalding_steel", "Scalding Steel", "Gl\u00fchstahl", "Gl\u00fchstahl", "Polierter",
        "minecraft:blaze_powder", "DULL_RED", "METAL",
        [(0x4C, 0x2A, 0x28), (0x62, 0x36, 0x32), (0x62, 0x36, 0x32), (0x7A, 0x44, 0x3E)],
        (0x2E, 0x18, 0x16), [(0xE8, 0x4A, 0x2A), (0xFF, 0x8A, 0x4A)], 0.05, 0.18),
    Mat("smelters_alloy", "Smelter's Alloy", "Schmelzerlegierung", "Schmelzerlegierungs",
        "Polierte", "minecraft:raw_iron", "RAW_IRON_PINK", "METAL",
        [(0x7C, 0x54, 0x4A), (0x98, 0x68, 0x5C), (0x98, 0x68, 0x5C), (0xB4, 0x80, 0x70)],
        (0x50, 0x34, 0x2C), [(0xD8, 0xA4, 0x8E), (0xF0, 0xC4, 0xAC)], 0.03, 0.07),
    Mat("furnace_steel", "Furnace Steel", "Ofenstahl", "Ofenstahl", "Polierter",
        "minecraft:iron_ingot", "GRAY", "METAL",
        [(0x4A, 0x4A, 0x4E), (0x5E, 0x5E, 0x64), (0x5E, 0x5E, 0x64), (0x74, 0x74, 0x7C)],
        (0x2E, 0x2E, 0x32), [(0x92, 0x92, 0x9C), (0xE2, 0x58, 0x22)], 0.03, 0.10),
    Mat("quench_iron", "Quench Iron", "H\u00e4rteeisen", "H\u00e4rteeisen", "Poliertes",
        "minecraft:prismarine_shard", "LIGHT_BLUE_GRAY", "IRON",
        [(0x36, 0x42, 0x50), (0x46, 0x56, 0x68), (0x46, 0x56, 0x68), (0x5A, 0x6E, 0x82)],
        (0x20, 0x2A, 0x34), [(0x87, 0x9E, 0xB0), (0xAC, 0xC6, 0xD8)], 0.03, 0.07),
    Mat("emberchrome", "Emberchrome", "Glutchrom", "Glutchrom", "Poliertes",
        "minecraft:quartz", "LIGHT_GRAY", "METAL",
        [(0x74, 0x78, 0x80), (0x8E, 0x92, 0x9C), (0x8E, 0x92, 0x9C), (0xAC, 0xB0, 0xBA)],
        (0x4C, 0x50, 0x58), [(0xD2, 0xD6, 0xE0), (0xFF, 0x7A, 0x2F)], 0.04, 0.10),
    Mat("molten_cobalt", "Molten Cobalt", "Schmelzkobalt", "Schmelzkobalt", "Poliertes",
        "minecraft:lapis_lazuli", "LAPIS_BLUE", "METAL",
        [(0x24, 0x3A, 0x74), (0x2E, 0x4C, 0x94), (0x2E, 0x4C, 0x94), (0x3C, 0x60, 0xB4)],
        (0x16, 0x24, 0x4A), [(0x5A, 0x84, 0xD8), (0x86, 0xAC, 0xEE)], 0.05, 0.15),
    Mat("ashiron", "Ashiron", "Ascheneisen", "Ascheneisen", "Poliertes",
        "minecraft:charcoal", "TERRACOTTA_GRAY", "IRON",
        [(0x4E, 0x4A, 0x46), (0x62, 0x5E, 0x58), (0x62, 0x5E, 0x58), (0x78, 0x72, 0x6C)],
        (0x30, 0x2D, 0x2A), [(0x8A, 0x85, 0x80), (0xA3, 0x9E, 0x98)], 0.03, 0.06),
]

assert len(MATERIALS) == 14
assert len({m.mid for m in MATERIALS}) == 14
assert len({m.map_color for m in MATERIALS}) == 14, "map colors must be distinct"


def families_of(m: str):
    """The three (base, stem) cube families of one material, in creative-tab order."""
    return [(m, m), (f"polished_{m}", f"polished_{m}"), (f"{m}_bricks", f"{m}_brick")]


def singles_of(m: str):
    """The four single blocks of one material, in creative-tab order."""
    return [f"{m}_tiles", f"cracked_{m}_bricks", f"chiseled_{m}_bricks", f"{m}_pillar"]


def method_of(mat: Mat) -> str:
    parts = mat.mid.split("_")
    return parts[0] + "".join(p.capitalize() for p in parts[1:]) + "Settings"


def field_of(block_id: str) -> str:
    return block_id.upper()


# ---------------------------------------------------------------------------
# Display names (EN + real German). Keys are bare block ids; the lang fragment adds the
# block.copper_inferno. prefix that ModBlocks.register applies to every BlockItem.
# ---------------------------------------------------------------------------

def build_names():
    en, de = {}, {}
    for mat in MATERIALS:
        m, e, stem = mat.mid, mat.en, mat.de_stem
        en[m] = e
        en[f"{m}_slab"] = f"{e} Slab"
        en[f"{m}_stairs"] = f"{e} Stairs"
        en[f"{m}_wall"] = f"{e} Wall"
        en[f"polished_{m}"] = f"Polished {e}"
        en[f"polished_{m}_slab"] = f"Polished {e} Slab"
        en[f"polished_{m}_stairs"] = f"Polished {e} Stairs"
        en[f"polished_{m}_wall"] = f"Polished {e} Wall"
        en[f"{m}_bricks"] = f"{e} Bricks"
        en[f"{m}_brick_slab"] = f"{e} Brick Slab"
        en[f"{m}_brick_stairs"] = f"{e} Brick Stairs"
        en[f"{m}_brick_wall"] = f"{e} Brick Wall"
        en[f"{m}_tiles"] = f"{e} Tiles"
        en[f"cracked_{m}_bricks"] = f"Cracked {e} Bricks"
        en[f"chiseled_{m}_bricks"] = f"Chiseled {e} Bricks"
        en[f"{m}_pillar"] = f"{e} Pillar"

        de[m] = mat.de
        de[f"{m}_slab"] = f"{stem}stufe"
        de[f"{m}_stairs"] = f"{stem}treppe"
        de[f"{m}_wall"] = f"{stem}mauer"
        de[f"polished_{m}"] = f"{mat.de_pol} {mat.de}"
        de[f"polished_{m}_slab"] = f"Polierte {stem}stufe"
        de[f"polished_{m}_stairs"] = f"Polierte {stem}treppe"
        de[f"polished_{m}_wall"] = f"Polierte {stem}mauer"
        de[f"{m}_bricks"] = f"{stem}ziegel"
        de[f"{m}_brick_slab"] = f"{stem}ziegelstufe"
        de[f"{m}_brick_stairs"] = f"{stem}ziegeltreppe"
        de[f"{m}_brick_wall"] = f"{stem}ziegelmauer"
        de[f"{m}_tiles"] = f"{stem}fliesen"
        de[f"cracked_{m}_bricks"] = f"Rissige {stem}ziegel"
        de[f"chiseled_{m}_bricks"] = f"Gemei\u00dfelte {stem}ziegel"
        de[f"{m}_pillar"] = f"{stem}s\u00e4ule"
    return en, de


EN, DE = build_names()


def de_acc(name: str) -> str:
    """German accusative for recipe texts (only 'Polierter X' inflects: -> 'Polierten X')."""
    if name.startswith("Polierter "):
        return "Polierten " + name[len("Polierter "):]
    return name


# ---------------------------------------------------------------------------
# Handbook entries (collected while emitting recipes; rendered by java_handbook_class).
# One "blocks" overview per MATERIAL plus one grid entry per recipe.
# ---------------------------------------------------------------------------
HANDBOOK = []


def hb_material(mat: Mat) -> None:
    m = mat.mid
    HANDBOOK.append((
        "blocks", f"moltenmetal/set_{m}", f"{NS}:{m}", None, None, None, 0,
        f"The {mat.en} set for Inferno builds: base, polished and bricks families "
        f"(each with slab, stairs and wall) plus tiles, cracked and chiseled bricks "
        f"and a pillar.",
        f"Das {mat.de}-Set f\u00fcr Inferno-Bauten: Grund-, Polier- und Ziegelfamilie "
        f"(je mit Stufe, Treppe und Mauer) sowie Fliesen, rissige und gemei\u00dfelte "
        f"Ziegel und eine S\u00e4ule."))


def hb_shaped(name: str, grid: list, result: str, count: int) -> None:
    HANDBOOK.append(("blocks", f"moltenmetal/{name}", f"{NS}:{result}", f"moltenmetal/{name}",
                     grid, f"{NS}:{result}", count,
                     f"Craft {count}x {EN[result]} at a crafting table.",
                     f"Stellt {count}x {de_acc(DE[result])} an der Werkbank her."))


def hb_smelting(name: str, ingredient: str, result: str) -> None:
    grid = ["", "", "", "", f"{NS}:{ingredient}", "", "", "", ""]
    HANDBOOK.append(("blocks", f"moltenmetal/{name}", f"{NS}:{result}", f"moltenmetal/{name}",
                     grid, f"{NS}:{result}", 1,
                     f"Smelting {EN[ingredient]} in a furnace yields {EN[result]}.",
                     f"{DE[ingredient]} im Ofen gebrannt ergibt {DE[result]}."))


def hb_stonecutting(name: str, ingredient: str, result: str, count: int) -> None:
    grid = ["", "", "", "", f"{NS}:{ingredient}", "", "", "", ""]
    HANDBOOK.append(("blocks", f"moltenmetal/{name}", f"{NS}:{result}", f"moltenmetal/{name}",
                     grid, f"{NS}:{result}", count,
                     f"Stonecutting: cut {count}x {EN[result]} from {EN[ingredient]}.",
                     f"Steins\u00e4ge: {count}x {de_acc(DE[result])} aus {DE[ingredient]} schneiden."))


# ---------------------------------------------------------------------------
# Recipes (30 per material; every crafting recipe's INPUTS include at least one own id).
# Mirrors cinderstone_gen.py emit_recipes: base shaped 4x, 2x2 conversions 4x,
# slab 6x / stairs 4x / wall 6x (misc), cracked via smelting, chiseled from two slabs,
# pillar 2x vertical, plus stonecutting from the base for every family member.
# ---------------------------------------------------------------------------

def emit_recipes() -> int:
    ci = f"{NS}:"
    count = 0
    for i, mat in enumerate(MATERIALS):
        m = mat.mid
        prev = MATERIALS[i - 1].mid  # ring chain (ember_brass uses ashiron)

        # Base cube: 4x vanilla flavor ingredient around the previous material's base.
        genlib.emit_shaped(RECIPES, m, {"S": mat.vanilla, "P": f"{ci}{prev}"},
                           ["S S", " P ", "S S"], f"{ci}{m}", 4)
        hb_shaped(m, [mat.vanilla, "", mat.vanilla, "", f"{ci}{prev}", "",
                      mat.vanilla, "", mat.vanilla], m, 4)
        count += 1

        # 2x2 conversions: base -> bricks -> tiles -> polished.
        for src, dst in ((m, f"{m}_bricks"), (f"{m}_bricks", f"{m}_tiles"),
                         (f"{m}_tiles", f"polished_{m}")):
            genlib.emit_shaped(RECIPES, dst, {"#": f"{ci}{src}"}, ["##", "##"], f"{ci}{dst}", 4)
            hb_shaped(dst, [f"{ci}{src}", f"{ci}{src}", "", f"{ci}{src}", f"{ci}{src}", "",
                            "", "", ""], dst, 4)
            count += 1

        # Family slab/stairs/wall recipes from their base cubes (vanilla formats).
        for base, stem in families_of(m):
            cube = f"{ci}{base}"
            genlib.emit_shaped(RECIPES, f"{stem}_slab", {"#": cube}, ["###"],
                               f"{ci}{stem}_slab", 6)
            hb_shaped(f"{stem}_slab", [cube, cube, cube, "", "", "", "", "", ""],
                      f"{stem}_slab", 6)
            genlib.emit_shaped(RECIPES, f"{stem}_stairs", {"#": cube}, ["#  ", "## ", "###"],
                               f"{ci}{stem}_stairs", 4)
            hb_shaped(f"{stem}_stairs", [cube, "", "", cube, cube, "", cube, cube, cube],
                      f"{stem}_stairs", 4)
            genlib.emit_shaped(RECIPES, f"{stem}_wall", {"#": cube}, ["###", "###"],
                               f"{ci}{stem}_wall", 6, category="misc")
            hb_shaped(f"{stem}_wall", [cube, cube, cube, cube, cube, cube, "", "", ""],
                      f"{stem}_wall", 6)
            count += 3

        # Cracked bricks via smelting (vanilla cracked_stone_bricks format).
        genlib.emit_smelting(RECIPES, f"cracked_{m}_bricks", f"{ci}{m}_bricks",
                             f"{ci}cracked_{m}_bricks")
        hb_smelting(f"cracked_{m}_bricks", f"{m}_bricks", f"cracked_{m}_bricks")
        count += 1

        # Chiseled: two vertical brick slabs (vanilla chiseled_stone_bricks layout).
        genlib.emit_shaped(RECIPES, f"chiseled_{m}_bricks", {"#": f"{ci}{m}_brick_slab"},
                           ["#", "#"], f"{ci}chiseled_{m}_bricks", 1)
        hb_shaped(f"chiseled_{m}_bricks", [f"{ci}{m}_brick_slab", "", "",
                                           f"{ci}{m}_brick_slab", "", "", "", "", ""],
                  f"chiseled_{m}_bricks", 1)
        count += 1

        # Pillar: two vertical base cubes -> 2 (vanilla quartz_pillar layout).
        genlib.emit_shaped(RECIPES, f"{m}_pillar", {"#": f"{ci}{m}"},
                           ["#", "#"], f"{ci}{m}_pillar", 2)
        hb_shaped(f"{m}_pillar", [f"{ci}{m}", "", "", f"{ci}{m}", "", "",
                                  "", "", ""], f"{m}_pillar", 2)
        count += 1

        # Stonecutting from the base metal block for every family member.
        for result, n in ((f"{m}_slab", 2), (f"{m}_stairs", 1), (f"{m}_wall", 1),
                          (f"polished_{m}", 1), (f"polished_{m}_slab", 2),
                          (f"polished_{m}_stairs", 1), (f"polished_{m}_wall", 1),
                          (f"{m}_bricks", 1), (f"{m}_brick_slab", 2),
                          (f"{m}_brick_stairs", 1), (f"{m}_brick_wall", 1),
                          (f"{m}_tiles", 1), (f"chiseled_{m}_bricks", 1),
                          (f"{m}_pillar", 1)):
            name = f"{result}_from_{m}_stonecutting"
            genlib.emit_stonecutting(RECIPES, name, f"{ci}{m}", f"{ci}{result}", n)
            hb_stonecutting(name, m, result, n)
            count += 1
    return count


# ---------------------------------------------------------------------------
# Textures (16x16, deterministic; distinct metallic hue per material; genlib helpers).
# ---------------------------------------------------------------------------

# Ingot glyph stamped on chiseled bricks (accent outline + sheen highlight).
INGOT_GLYPH = [(5, 6), (6, 6), (7, 6), (8, 6), (9, 6), (10, 6), (4, 7), (11, 7),
               (4, 8), (11, 8), (3, 9), (12, 9), (3, 10), (4, 10), (5, 10), (6, 10),
               (7, 10), (8, 10), (9, 10), (10, 10), (11, 10), (12, 10)]
INGOT_SHEEN = [(6, 7), (7, 7), (6, 8)]


def tex_base(mat: Mat, rng):
    """Brushed-metal plate: horizontal banding with sparse glints and dark pits."""
    dark, mid, _, light = mat.shades
    img = Image.new("RGB", (16, 16))
    for y in range(16):
        band = [mid, mid, light] if (y % 4) in (1, 2) else [dark, mid, mid]
        shade = rng.choice(band)
        for x in range(16):
            if rng.random() < 0.18:
                shade = rng.choice(band)
            img.putpixel((x, y), shade)
    all_px = [(x, y) for y in range(16) for x in range(16)]
    genlib.sprinkle(img, rng, all_px, mat.accents, mat.base_prob)
    genlib.sprinkle(img, rng, all_px, [mat.mortar], 0.02)
    return img


def tex_polished(mat: Mat, rng):
    """Smooth slab with a 1px bevel frame and a diagonal mirror-shine streak."""
    img = genlib.new_canvas(rng, [mat.shades[1], mat.shades[1], mat.shades[-1]])
    for i in range(16):
        img.putpixel((i, 0), mat.shades[-1])
        img.putpixel((0, i), mat.shades[-1])
        img.putpixel((i, 15), mat.mortar)
        img.putpixel((15, i), mat.mortar)
    for d in range(1, 15):
        x, y = d, 15 - d
        if d % 3:
            img.putpixel((x, y), mat.accents[0])
    return img


def tex_bricks(mat: Mat, rng):
    return genlib.brick_texture(rng, mat.shades, mat.mortar, mat.brick_prob, mat.accents)


def tex_tiles(mat: Mat, rng):
    """2x2 grid of 8x8 riveted metal plates: dark seams, glint rivets in the corners."""
    img = genlib.new_canvas(rng, mat.shades)
    for y in range(16):
        for x in range(16):
            if x % 8 == 7 or y % 8 == 7:
                img.putpixel((x, y), mat.mortar)
    for cx in (0, 8):
        for cy in (0, 8):
            for x, y in ((cx + 1, cy + 1), (cx + 5, cy + 1),
                         (cx + 1, cy + 5), (cx + 5, cy + 5)):
                img.putpixel((x, y), mat.accents[-1])
    return img


def tex_chiseled(mat: Mat, rng):
    img = genlib.framed(rng, mat.shades, mat.mortar)
    for x, y in INGOT_GLYPH:
        img.putpixel((x, y), mat.accents[0])
    for x, y in INGOT_SHEEN:
        img.putpixel((x, y), mat.accents[-1])
    return img


def emit_textures() -> None:
    block_dir = ASSETS / "textures" / "block"
    block_dir.mkdir(parents=True, exist_ok=True)

    def save(name, img):
        img.save(block_dir / f"{name}.png")

    for mat in MATERIALS:
        m = mat.mid
        save(m, tex_base(mat, rng_for(m)))
        save(f"polished_{m}", tex_polished(mat, rng_for(f"polished_{m}")))
        save(f"{m}_bricks", tex_bricks(mat, rng_for(f"{m}_bricks")))
        save(f"{m}_tiles", tex_tiles(mat, rng_for(f"{m}_tiles")))
        save(f"cracked_{m}_bricks",
             genlib.cracked(lambda r, mat=mat: tex_bricks(mat, r), rng_for(f"cracked_{m}_bricks")))
        save(f"chiseled_{m}_bricks", tex_chiseled(mat, rng_for(f"chiseled_{m}_bricks")))
        save(f"{m}_pillar_side",
             genlib.pillar_side(rng_for(f"{m}_pillar_side"), mat.shades, mat.mortar,
                                mat.accents, 0.3))
        save(f"{m}_pillar_top",
             genlib.pillar_top(rng_for(f"{m}_pillar_top"), mat.shades, mat.mortar, mat.accents))


# ---------------------------------------------------------------------------
# Java codegen (genlib.java_feature_class / java_handbook_class; literal ids only so
# devtools/audit_assets.py check (f) and devtools/check_handbook.py can parse them).
# ---------------------------------------------------------------------------

def settings_methods_src() -> str:
    lines = [
        "\t/**",
        "\t * Fresh settings per block ({@link ModBlocks#register} writes a registry key into the",
        "\t * instance, so settings must never be shared). Requires-tool metal profile adapted",
        "\t * from the cinderstone stone profile (slightly harder, metal sounds).",
        "\t */",
        "\tprivate static AbstractBlock.Settings metalSettings(MapColor color, BlockSoundGroup sounds) {",
        "\t\treturn AbstractBlock.Settings.create()",
        "\t\t\t\t.mapColor(color)",
        "\t\t\t\t.strength(4.0F, 8.0F)",
        "\t\t\t\t.requiresTool()",
        "\t\t\t\t.sounds(sounds);",
        "\t}",
    ]
    for mat in MATERIALS:
        lines += ["",
                  f"\tprivate static AbstractBlock.Settings {method_of(mat)}() {{",
                  f"\t\treturn metalSettings(MapColor.{mat.map_color}, BlockSoundGroup.{mat.sounds});",
                  "\t}"]
    return "\n".join(lines)


def emit_java() -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)

    families, blocks, tab_entries = [], [], []
    for mat in MATERIALS:
        m, method = mat.mid, method_of(mat)
        for base, stem in families_of(m):
            families.append((field_of(base), base, stem, method, True))
            tab_entries += genlib.family_tab_entries(field_of(base))
        blocks.append((field_of(f"{m}_tiles"), f"{m}_tiles", "Block::new", f"{method}()"))
        blocks.append((field_of(f"cracked_{m}_bricks"), f"cracked_{m}_bricks", "Block::new",
                       f"{method}()"))
        blocks.append((field_of(f"chiseled_{m}_bricks"), f"chiseled_{m}_bricks", "Block::new",
                       f"{method}()"))
        blocks.append((field_of(f"{m}_pillar"), f"{m}_pillar", "PillarBlock::new", f"{method}()"))
        tab_entries += [field_of(f"{m}_tiles"), field_of(f"cracked_{m}_bricks"),
                        field_of(f"chiseled_{m}_bricks"), field_of(f"{m}_pillar")]

    feature_doc = [
        "Molten Metal block set: 224 building blocks across 14 molten-metal/alloy materials",
        "(ember brass, slag bronze, molten brass, patina steel, cinder iron, forgeworn metal,",
        "blistered bronze, scalding steel, smelter's alloy, furnace steel, quench iron,",
        "emberchrome, molten cobalt, ashiron). Each material ships three full cube families",
        "(base, polished, bricks; each base/slab/stairs/wall) plus tiles, cracked bricks,",
        "chiseled bricks and a pillar.",
        "",
        "<p>Assets (blockstates, models, textures, item definitions, loot tables, recipes,",
        "EN+DE lang fragments) are generated by {@code devtools/gen/moltenmetal_gen.py}; the",
        "tag fragment lives at {@code devtools/tagfrag/moltenmetal.json}; handbook pages are",
        "registered by {@link MoltenMetalHandbook}.",
    ]
    feature_src = genlib.java_feature_class(
        "moltenmetal", "MoltenMetalFeature", feature_doc,
        families=families,
        blocks=blocks,
        settings_methods=settings_methods_src(),
        tabs=[("BLOCKS_KEY", tab_entries)],
        extra_imports=("net.minecraft.block.MapColor",
                       "net.minecraft.block.PillarBlock",
                       "net.minecraft.sound.BlockSoundGroup"),
        handbook_class="MoltenMetalHandbook",
    )
    (FEATURE_DIR / "MoltenMetalFeature.java").write_text(feature_src, encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the Molten Metal block set: one \"blocks\" overview per material",
        "plus one recipe page for every JSON under {@code data/copper_inferno/recipe/moltenmetal/}",
        "(crafting, smelting and stonecutting). Entry texts and grids mirror the recipe JSONs",
        "emitted by {@code devtools/gen/moltenmetal_gen.py}; {@code devtools/check_handbook.py}",
        "parses the inline {@code new HandbookEntry(...)} literals positionally, so keep them",
        "inline.",
    ]
    handbook_src = genlib.java_handbook_class("moltenmetal", "MoltenMetalHandbook", handbook_doc,
                                              HANDBOOK)
    (FEATURE_DIR / "MoltenMetalHandbook.java").write_text(handbook_src, encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/moltenmetal.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks(ids: list, recipe_count: int) -> None:
    lines = ["# moltenmetal feature hooks (format: devtools/hooks/README.md)", "",
             "[init]",
             "import net.sonic0810.copperinferno.feature.moltenmetal.MoltenMetalFeature;",
             "\t\tMoltenMetalFeature.init();", "",
             "[families]"]
    for mat in MATERIALS:
        for base, stem in families_of(mat.mid):
            lines.append(f"{base}, {stem}")
    lines += ["", "[requires-tool]"]
    for mat in MATERIALS:
        lines += singles_of(mat.mid)
    lines += ["", "[recipe-dir]", "moltenmetal", "",
              "[counts]",
              f"blocks: {len(ids)}",
              "items: 0",
              f"recipes: {recipe_count}",
              f"handbook-entries: {len(HANDBOOK)}", ""]
    path = ROOT / "devtools" / "hooks" / "moltenmetal.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    ids = []
    for mat in MATERIALS:
        m = mat.mid
        for base, stem in families_of(m):
            genlib.emit_family(ASSETS, base, stem)
            genlib.emit_drop_self_loot(DATA, base)
            genlib.emit_slab_loot(DATA, f"{stem}_slab")
            genlib.emit_drop_self_loot(DATA, f"{stem}_stairs")
            genlib.emit_drop_self_loot(DATA, f"{stem}_wall")
            ids += [base, f"{stem}_slab", f"{stem}_stairs", f"{stem}_wall"]
        for name in (f"{m}_tiles", f"cracked_{m}_bricks", f"chiseled_{m}_bricks"):
            genlib.emit_cube(ASSETS, name)
            genlib.emit_drop_self_loot(DATA, name)
            ids.append(name)
        genlib.emit_pillar(ASSETS, f"{m}_pillar")
        genlib.emit_drop_self_loot(DATA, f"{m}_pillar")
        ids.append(f"{m}_pillar")

    for mat in MATERIALS:
        hb_material(mat)
    recipe_count = emit_recipes()
    emit_textures()

    lang_en = {f"block.{NS}.{i}": EN[i] for i in ids}
    lang_de = {f"block.{NS}.{i}": DE[i] for i in ids}
    genlib.lang_fragments(ASSETS, "moltenmetal", lang_en, lang_de)

    write_json(ROOT / "devtools" / "tagfrag" / "moltenmetal.json", {
        "block/mineable/pickaxe": sorted(f"{NS}:{i}" for i in ids),
        "block/walls": sorted(f"{NS}:{i}" for i in ids if i.endswith("_wall")),
    })

    emit_java()
    emit_hooks(ids, recipe_count)

    assert len(ids) == 224, f"expected 224 block ids, got {len(ids)}"
    assert len(set(ids)) == 224, "duplicate block ids emitted"
    assert sorted(f"block.{NS}.{i}" for i in ids) == sorted(lang_en) == sorted(lang_de)
    assert recipe_count == 420, f"expected 420 recipes, got {recipe_count}"
    assert len(HANDBOOK) == 434, f"expected 434 handbook entries, got {len(HANDBOOK)}"
    print(f"moltenmetal_gen: assets generated for {len(ids)} blocks "
          f"({recipe_count} recipes, {len(HANDBOOK)} handbook entries).")


if __name__ == "__main__":
    main()
