#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "kilnstone" feature (224 building blocks).

14 kiln/ceramic/fired-clay materials (kilnstone, firebrick_stone, glazed_cinder,
terracotta_slag, kiln_ceramic, emberware, sootware, glazed_ember, baked_ash_block,
foundry_brick, crucible_stone, slagware, ash_ceramic, pyroceramic); each ships a 16-block
set: the base cube family, the polished family and the bricks family (base/slab/stairs/
wall each), plus tiles, cracked bricks, chiseled bricks and a pillar.

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via genlib.rng_for). Emits by DEFAULT (no flags):
  - blockstates, block models, items/<id>.json model-definitions (genlib emitters)
  - 16x16 block textures (Pillow, deterministic seeded noise, glazed-ceramic sheen,
    distinct palette per material)
  - loot tables (drop-self; slabs use the vanilla double-drops-2 format)
  - recipes (data/copper_inferno/recipe/kilnstone/*.json, 30 per material = 420; every
    recipe's inputs include at least one copper_inferno id)
  - lang fragments: assets/copper_inferno/lang/fragments/kilnstone.json (EN)
    and assets/copper_inferno/lang/fragments_de/kilnstone.json (real German)
  - devtools/tagfrag/kilnstone.json (mineable/pickaxe for all 224, walls for the 42 walls)
  - src/main/java/.../feature/kilnstone/KilnstoneFeature.java + KilnstoneHandbook.java
    (genlib.java_feature_class / genlib.java_handbook_class; literal ids only)
  - devtools/hooks/kilnstone.txt (integration hook file)

All JSON structures come from genlib and are byte-identical to the vanilla 1.21.9 formats
used by devtools/gen/cinderstone_gen.py. Do NOT "improve" them.
"""

import sys
from collections import namedtuple
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, rng_for, write_json

RECIPES = DATA / "recipe" / "kilnstone"
FEATURE_DIR = ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "kilnstone"

# ---------------------------------------------------------------------------
# Materials. Each gets a DISTINCT deterministic glazed-ceramic palette:
#   shades = [dark, mid, mid, light] (genlib stone-look 4-tuple), mortar, two accents,
#   plus a bright "gloss" specular color for the glaze sheen.
# de/de_stem/de_pol drive the German lang conventions (Stufe/Treppe/Mauer/Ziegel/
# Fliesen/Saeule compounds; "Polierter"/"Polierte" by grammatical gender).
# vanilla = the flavor vanilla ingredient of the base recipe (materials i >= 1); the
# recipe's centre slot is the PREVIOUS material's base block, forming an open 14-link
# CHAIN (no cycle): material[0] (kilnstone) is the ENTRY, crafted from obtainable
# materials (brick corners + Inferno-terrain cinderstone centre) instead of
# material[13]. Every crafting recipe still contains at least one copper_inferno id,
# so it can never collide with vanilla or other features' recipes.
# ---------------------------------------------------------------------------
Mat = namedtuple("Mat", "mid en de de_stem de_pol vanilla map_color sounds "
                        "shades mortar accents gloss base_prob brick_prob")

MATERIALS = [
    Mat("kilnstone", "Kilnstone", "Brennofenstein", "Brennofenstein", "Polierter",
        "minecraft:terracotta", "TERRACOTTA_ORANGE", "MUD_BRICKS",
        [(0x7A, 0x46, 0x2A), (0x94, 0x58, 0x36), (0x94, 0x58, 0x36), (0xB0, 0x6E, 0x44)],
        (0x4E, 0x2B, 0x19), [(0xD9, 0x8A, 0x4C), (0xE2, 0x58, 0x22)],
        (0xE8, 0xC8, 0xA8), 0.03, 0.08),
    Mat("firebrick_stone", "Firebrick Stone", "Schamottstein", "Schamottstein", "Polierter",
        "minecraft:bricks", "TERRACOTTA_RED", "NETHER_BRICKS",
        [(0x8A, 0x30, 0x22), (0xA6, 0x3E, 0x2A), (0xA6, 0x3E, 0x2A), (0xC2, 0x52, 0x34)],
        (0x55, 0x1D, 0x14), [(0xE2, 0x58, 0x22), (0xE8, 0xB4, 0x8C)],
        (0xF0, 0xC0, 0x9C), 0.03, 0.12),
    Mat("glazed_cinder", "Glazed Cinder", "Glasurzunder", "Glasurzunder", "Polierter",
        "minecraft:charcoal", "TERRACOTTA_GRAY", "DEEPSLATE_TILES",
        [(0x2E, 0x2A, 0x2E), (0x3E, 0x38, 0x3E), (0x3E, 0x38, 0x3E), (0x52, 0x4A, 0x52)],
        (0x1C, 0x18, 0x1C), [(0x5E, 0x8C, 0x82), (0x86, 0xB8, 0xAC)],
        (0x9A, 0xCC, 0xC2), 0.04, 0.10),
    Mat("terracotta_slag", "Terracotta Slag", "Terrakottaschlacke", "Terrakottaschlacken",
        "Polierte", "minecraft:red_terracotta", "TERRACOTTA_BROWN", "MUD_BRICKS",
        [(0x6E, 0x42, 0x30), (0x86, 0x52, 0x3C), (0x86, 0x52, 0x3C), (0x9E, 0x64, 0x48)],
        (0x42, 0x26, 0x1A), [(0xB8, 0x6A, 0x3E), (0xE2, 0x58, 0x22)],
        (0xD4, 0xA0, 0x7C), 0.04, 0.10),
    Mat("kiln_ceramic", "Kiln Ceramic", "Ofenkeramik", "Ofenkeramik", "Polierte",
        "minecraft:brick", "TERRACOTTA_WHITE", "CALCITE",
        [(0xB4, 0xA4, 0x8E), (0xCC, 0xBC, 0xA4), (0xCC, 0xBC, 0xA4), (0xE0, 0xD2, 0xBA)],
        (0x86, 0x76, 0x60), [(0xEE, 0xE4, 0xD0), (0xC8, 0x8A, 0x54)],
        (0xF6, 0xF0, 0xE2), 0.02, 0.05),
    Mat("emberware", "Emberware", "Glutware", "Glutwaren", "Polierte",
        "minecraft:blaze_powder", "ORANGE", "NETHER_BRICKS",
        [(0x8C, 0x42, 0x1E), (0xA8, 0x52, 0x26), (0xA8, 0x52, 0x26), (0xC4, 0x66, 0x30)],
        (0x52, 0x24, 0x0F), [(0xFF, 0x7A, 0x2F), (0xFF, 0xB1, 0x6B)],
        (0xFF, 0xC8, 0x8E), 0.05, 0.18),
    Mat("sootware", "Sootware", "Ru\u00dfware", "Ru\u00dfwaren", "Polierte",
        "minecraft:coal", "TERRACOTTA_BLACK", "TUFF_BRICKS",
        [(0x24, 0x20, 0x20), (0x32, 0x2C, 0x2C), (0x32, 0x2C, 0x2C), (0x44, 0x3C, 0x3C)],
        (0x14, 0x10, 0x10), [(0x5E, 0x54, 0x50), (0x7A, 0x6E, 0x68)],
        (0x8C, 0x82, 0x7C), 0.03, 0.06),
    Mat("glazed_ember", "Glazed Ember", "Glasurglut", "Glasurglut", "Polierte",
        "minecraft:glowstone_dust", "TERRACOTTA_YELLOW", "RESIN_BRICKS",
        [(0x9A, 0x5C, 0x18), (0xB6, 0x72, 0x20), (0xB6, 0x72, 0x20), (0xD2, 0x8A, 0x2C)],
        (0x5E, 0x36, 0x0E), [(0xF2, 0xB4, 0x48), (0xFF, 0xD8, 0x7A)],
        (0xFF, 0xE4, 0xA0), 0.05, 0.16),
    Mat("baked_ash_block", "Baked Ash Block", "Brandaschenblock", "Brandaschenblock",
        "Polierter", "minecraft:bone_meal", "TERRACOTTA_LIGHT_GRAY", "CALCITE",
        [(0x78, 0x74, 0x6E), (0x90, 0x8C, 0x84), (0x90, 0x8C, 0x84), (0xA8, 0xA4, 0x9A)],
        (0x54, 0x50, 0x4A), [(0xC0, 0xBC, 0xB2), (0xE2, 0x58, 0x22)],
        (0xD2, 0xCE, 0xC4), 0.02, 0.05),
    Mat("foundry_brick", "Foundry Brick", "Gie\u00dfereistein", "Gie\u00dfereistein",
        "Polierter", "minecraft:iron_nugget", "DULL_RED", "NETHER_BRICKS",
        [(0x5E, 0x30, 0x28), (0x74, 0x3C, 0x30), (0x74, 0x3C, 0x30), (0x8C, 0x4C, 0x3A)],
        (0x38, 0x1C, 0x16), [(0xB8, 0x6A, 0x3E), (0xE2, 0x58, 0x22)],
        (0xC8, 0x94, 0x6E), 0.04, 0.12),
    Mat("crucible_stone", "Crucible Stone", "Tiegelstein", "Tiegelstein", "Polierter",
        "minecraft:stone", "STONE_GRAY", "STONE",
        [(0x4A, 0x46, 0x44), (0x5E, 0x5A, 0x56), (0x5E, 0x5A, 0x56), (0x74, 0x6E, 0x68)],
        (0x2C, 0x2A, 0x28), [(0xE2, 0x58, 0x22), (0xFF, 0xB1, 0x6B)],
        (0x9C, 0x96, 0x8E), 0.04, 0.10),
    Mat("slagware", "Slagware", "Schlackenware", "Schlackenwaren", "Polierte",
        "minecraft:tuff", "TERRACOTTA_CYAN", "POLISHED_TUFF",
        [(0x35, 0x48, 0x44), (0x44, 0x5C, 0x56), (0x44, 0x5C, 0x56), (0x58, 0x74, 0x6C)],
        (0x20, 0x2E, 0x2A), [(0x5E, 0x8C, 0x82), (0x8A, 0xC0, 0xB2)],
        (0xA2, 0xD2, 0xC6), 0.03, 0.08),
    Mat("ash_ceramic", "Ash Ceramic", "Aschenkeramik", "Aschenkeramik", "Polierte",
        "minecraft:clay_ball", "LIGHT_GRAY", "CALCITE",
        [(0x9A, 0x92, 0x86), (0xB2, 0xAA, 0x9C), (0xB2, 0xAA, 0x9C), (0xC8, 0xC0, 0xB2)],
        (0x6E, 0x66, 0x5C), [(0xDE, 0xD6, 0xC8), (0xB8, 0x6A, 0x3E)],
        (0xEC, 0xE6, 0xDA), 0.02, 0.04),
    Mat("pyroceramic", "Pyroceramic", "Pyrokeramik", "Pyrokeramik", "Polierte",
        "minecraft:magma_cream", "DARK_RED", "DRIPSTONE_BLOCK",
        [(0x4E, 0x1E, 0x1A), (0x66, 0x28, 0x20), (0x66, 0x28, 0x20), (0x80, 0x34, 0x28)],
        (0x2E, 0x10, 0x0E), [(0xE2, 0x58, 0x22), (0xFF, 0x7A, 0x2F)],
        (0xD8, 0x8A, 0x6C), 0.04, 0.14),
]

assert len(MATERIALS) == 14
assert len({m.mid for m in MATERIALS}) == 14


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
# ---------------------------------------------------------------------------
HANDBOOK = []


def hb_family(base: str) -> None:
    de = DE[base]
    de_text = (f"Die Familie {de} f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer."
               if " " in de else
               f"Die {de}-Familie f\u00fcr Inferno-Bauten: Block, Treppe, Stufe und Mauer.")
    HANDBOOK.append(("blocks", f"kilnstone/family_{base}", f"{NS}:{base}", None, None, None, 0,
                     f"The {EN[base]} family for Inferno builds: block, stairs, slab and wall.",
                     de_text))


def hb_shaped(name: str, grid: list, result: str, count: int) -> None:
    HANDBOOK.append(("blocks", f"kilnstone/{name}", f"{NS}:{result}", f"kilnstone/{name}",
                     grid, f"{NS}:{result}", count,
                     f"Craft {count}x {EN[result]} at a crafting table.",
                     f"Stellt {count}x {de_acc(DE[result])} an der Werkbank her."))


def hb_smelting(name: str, ingredient: str, result: str) -> None:
    grid = ["", "", "", "", f"{NS}:{ingredient}", "", "", "", ""]
    HANDBOOK.append(("blocks", f"kilnstone/{name}", f"{NS}:{result}", f"kilnstone/{name}",
                     grid, f"{NS}:{result}", 1,
                     f"Smelting {EN[ingredient]} in a furnace yields {EN[result]}.",
                     f"{DE[ingredient]} im Ofen gebrannt ergibt {DE[result]}."))


def hb_stonecutting(name: str, ingredient: str, result: str, count: int) -> None:
    grid = ["", "", "", "", f"{NS}:{ingredient}", "", "", "", ""]
    HANDBOOK.append(("blocks", f"kilnstone/{name}", f"{NS}:{result}", f"kilnstone/{name}",
                     grid, f"{NS}:{result}", count,
                     f"Stonecutting: cut {count}x {EN[result]} from {EN[ingredient]}.",
                     f"Steins\u00e4ge: {count}x {de_acc(DE[result])} aus {DE[ingredient]} schneiden."))


# ---------------------------------------------------------------------------
# Recipes (30 per material; every crafting recipe's INPUTS include at least one
# copper_inferno id). Mirrors cinderstone_gen.py emit_recipes: base shaped 4x (open
# chain with an entry recipe, no cycle), 2x2 conversions 4x, slab 6x / stairs 4x /
# wall 6x (misc), cracked via smelting, chiseled from two slabs, pillar 2x vertical,
# plus stonecutting from the base for every family member.
# ---------------------------------------------------------------------------

def emit_recipes() -> int:
    ci = f"{NS}:"
    count = 0
    for i, mat in enumerate(MATERIALS):
        m = mat.mid

        # Base cube, always ["S S", " P ", "S S"] -> 4 under the same file name.
        # material[0] (kilnstone) is the chain ENTRY: crafted from already-obtainable
        # materials (vanilla brick corners + Inferno-terrain cinderstone centre) so
        # the 14-link chain has an entry point and no crafting cycle. Materials i >= 1
        # use 4x vanilla flavor ingredient around the PREVIOUS material's base block.
        if i == 0:
            corner, centre = "minecraft:brick", f"{ci}cinderstone"
        else:
            corner, centre = mat.vanilla, f"{ci}{MATERIALS[i - 1].mid}"
        genlib.emit_shaped(RECIPES, m, {"S": corner, "P": centre},
                           ["S S", " P ", "S S"], f"{ci}{m}", 4)
        hb_shaped(m, [corner, "", corner, "", centre, "",
                      corner, "", corner], m, 4)
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

        # Pillar: two vertical bricks -> 2 (vanilla quartz_pillar layout).
        genlib.emit_shaped(RECIPES, f"{m}_pillar", {"#": f"{ci}{m}_bricks"},
                           ["#", "#"], f"{ci}{m}_pillar", 2)
        hb_shaped(f"{m}_pillar", [f"{ci}{m}_bricks", "", "", f"{ci}{m}_bricks", "", "",
                                  "", "", ""], f"{m}_pillar", 2)
        count += 1

        # Stonecutting from the base rough stone for every family member.
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
# Textures (16x16, deterministic; distinct per-material glazed-ceramic palettes;
# genlib helpers + a glaze-sheen pass of diagonal specular highlight streaks).
# ---------------------------------------------------------------------------

# Kiln glyph stamped on chiseled bricks: a domed kiln arch with a fire-mouth opening
# at the bottom and a glowing core inside.
KILN_GLYPH = [(6, 4), (7, 4), (8, 4), (9, 4), (5, 5), (10, 5), (4, 6), (11, 6),
              (4, 7), (11, 7), (4, 8), (11, 8), (4, 9), (11, 9), (4, 10), (11, 10),
              (4, 11), (5, 11), (6, 11), (9, 11), (10, 11), (11, 11)]
KILN_CORE = [(8, 7), (7, 8), (8, 8), (7, 9), (8, 9)]


def glaze_sheen(img, rng, gloss, prob: float = 0.5):
    """Two wrapping diagonal specular streaks — the glazed-ceramic gloss signature."""
    for d in (4, 11):
        for x in range(16):
            y = (x + d) % 16
            if rng.random() < prob:
                img.putpixel((x, y), gloss)
    return img


def tex_base(mat: Mat, rng):
    """Mottled fired-clay base with accent speckles and a subtle glaze sheen."""
    img = genlib.new_canvas(rng, mat.shades)
    all_px = [(x, y) for y in range(16) for x in range(16)]
    genlib.sprinkle(img, rng, all_px, mat.accents, mat.base_prob)
    return glaze_sheen(img, rng, mat.gloss, 0.30)


def tex_polished(mat: Mat, rng):
    """Smooth glazed slab: bevel frame (cinderstone polished look) + strong sheen."""
    img = genlib.new_canvas(rng, [mat.shades[1], mat.shades[1], mat.shades[-1]])
    for i in range(16):
        img.putpixel((i, 0), mat.shades[-1])
        img.putpixel((0, i), mat.shades[-1])
        img.putpixel((i, 15), mat.mortar)
        img.putpixel((15, i), mat.mortar)
    return glaze_sheen(img, rng, mat.gloss, 0.55)


def tex_bricks(mat: Mat, rng):
    """Kiln-fired running-bond bricks with glossy glaze dots along the courses."""
    img = genlib.brick_texture(rng, mat.shades, mat.mortar, mat.brick_prob, mat.accents)
    all_px = [(x, y) for y in range(16) for x in range(16)]
    genlib.sprinkle(img, rng, all_px, [mat.gloss], 0.04)
    return img


def tex_tiles(mat: Mat, rng):
    """2x2 grid of 8x8 glazed tiles: dark grout, gloss highlight in every tile."""
    img = genlib.new_canvas(rng, mat.shades)
    grout = []
    for y in range(16):
        for x in range(16):
            if x % 8 == 7 or y % 8 == 7:
                img.putpixel((x, y), mat.mortar)
                grout.append((x, y))
    genlib.sprinkle(img, rng, grout, mat.accents, 0.05)
    for x, y in [(7, 7), (15, 7), (7, 15), (15, 15)]:
        img.putpixel((x, y), mat.accents[0])
    for cx, cy in [(2, 2), (10, 2), (2, 10), (10, 10)]:  # per-tile specular corner
        img.putpixel((cx, cy), mat.gloss)
        img.putpixel((cx + 1, cy + 1), mat.gloss)
    return img


def tex_chiseled(mat: Mat, rng):
    img = genlib.framed(rng, mat.shades, mat.mortar)
    for x, y in KILN_GLYPH:
        img.putpixel((x, y), mat.accents[0])
    for x, y in KILN_CORE:
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
                                [mat.accents[0], mat.gloss], 0.3))
        save(f"{m}_pillar_top",
             genlib.pillar_top(rng_for(f"{m}_pillar_top"), mat.shades, mat.mortar,
                               [mat.accents[0], mat.gloss]))


# ---------------------------------------------------------------------------
# Java codegen (genlib.java_feature_class / java_handbook_class; literal ids only so
# devtools/audit_assets.py check (f) and devtools/check_handbook.py can parse them).
# ---------------------------------------------------------------------------

def settings_methods_src() -> str:
    lines = [
        "\t/**",
        "\t * Fresh settings per block ({@link ModBlocks#register} writes a registry key into the",
        "\t * instance, so settings must never be shared). Standard requires-tool stone profile",
        "\t * (same numbers as the cinderstone feature), ceramic-like map colors.",
        "\t */",
        "\tprivate static AbstractBlock.Settings stoneSettings(MapColor color, BlockSoundGroup sounds) {",
        "\t\treturn AbstractBlock.Settings.create()",
        "\t\t\t\t.mapColor(color)",
        "\t\t\t\t.strength(3.5F, 8.0F)",
        "\t\t\t\t.requiresTool()",
        "\t\t\t\t.sounds(sounds);",
        "\t}",
    ]
    for mat in MATERIALS:
        lines += ["",
                  f"\tprivate static AbstractBlock.Settings {method_of(mat)}() {{",
                  f"\t\treturn stoneSettings(MapColor.{mat.map_color}, BlockSoundGroup.{mat.sounds});",
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
        "Kilnstone block set: 224 building blocks across 14 kiln/ceramic/fired-clay materials",
        "(kilnstone, firebrick stone, glazed cinder, terracotta slag, kiln ceramic, emberware,",
        "sootware, glazed ember, baked ash block, foundry brick, crucible stone, slagware,",
        "ash ceramic, pyroceramic). Each material ships three full cube families (base,",
        "polished, bricks; each base/slab/stairs/wall) plus tiles, cracked bricks, chiseled",
        "bricks and a pillar.",
        "",
        "<p>Assets (blockstates, models, textures, item definitions, loot tables, recipes,",
        "EN+DE lang fragments) are generated by {@code devtools/gen/kilnstone_gen.py}; the tag",
        "fragment lives at {@code devtools/tagfrag/kilnstone.json}; handbook pages are",
        "registered by {@link KilnstoneHandbook}.",
    ]
    feature_src = genlib.java_feature_class(
        "kilnstone", "KilnstoneFeature", feature_doc,
        families=families,
        blocks=blocks,
        settings_methods=settings_methods_src(),
        tabs=[("BLOCKS_KEY", tab_entries)],
        extra_imports=("net.minecraft.block.MapColor",
                       "net.minecraft.block.PillarBlock",
                       "net.minecraft.sound.BlockSoundGroup"),
        handbook_class="KilnstoneHandbook",
    )
    (FEATURE_DIR / "KilnstoneFeature.java").write_text(feature_src, encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the Kilnstone block set: one \"blocks\" overview per cube family",
        "plus one recipe page for every JSON under {@code data/copper_inferno/recipe/kilnstone/}",
        "(crafting, smelting and stonecutting). Entry texts and grids mirror the recipe JSONs",
        "emitted by {@code devtools/gen/kilnstone_gen.py}; {@code devtools/check_handbook.py}",
        "parses the inline {@code new HandbookEntry(...)} literals positionally, so keep them",
        "inline.",
    ]
    handbook_src = genlib.java_handbook_class("kilnstone", "KilnstoneHandbook", handbook_doc,
                                              HANDBOOK)
    (FEATURE_DIR / "KilnstoneHandbook.java").write_text(handbook_src, encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/kilnstone.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks(ids: list, recipe_count: int) -> None:
    lines = ["# kilnstone feature hooks (format: devtools/hooks/README.md)", "",
             "[init]",
             "import net.sonic0810.copperinferno.feature.kilnstone.KilnstoneFeature;",
             "\t\tKilnstoneFeature.init();", "",
             "[families]"]
    for mat in MATERIALS:
        for base, stem in families_of(mat.mid):
            lines.append(f"{base}, {stem}")
    lines += ["", "[requires-tool]"]
    for mat in MATERIALS:
        lines += singles_of(mat.mid)
    lines += ["", "[recipe-dir]", "kilnstone", "",
              "[counts]",
              f"blocks: {len(ids)}",
              "items: 0",
              f"recipes: {recipe_count}",
              f"handbook-entries: {len(HANDBOOK)}", ""]
    path = ROOT / "devtools" / "hooks" / "kilnstone.txt"
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
        for base, _stem in families_of(mat.mid):
            hb_family(base)
    recipe_count = emit_recipes()
    emit_textures()

    lang_en = {f"block.{NS}.{i}": EN[i] for i in ids}
    lang_de = {f"block.{NS}.{i}": DE[i] for i in ids}
    genlib.lang_fragments(ASSETS, "kilnstone", lang_en, lang_de)

    write_json(ROOT / "devtools" / "tagfrag" / "kilnstone.json", {
        "block/mineable/pickaxe": sorted(f"{NS}:{i}" for i in ids),
        "block/walls": sorted(f"{NS}:{i}" for i in ids if i.endswith("_wall")),
    })

    emit_java()
    emit_hooks(ids, recipe_count)

    assert len(ids) == 224, f"expected 224 block ids, got {len(ids)}"
    assert len(set(ids)) == 224, "duplicate block ids emitted"
    assert sorted(f"block.{NS}.{i}" for i in ids) == sorted(lang_en) == sorted(lang_de)
    assert recipe_count == 420, f"expected 420 recipes, got {recipe_count}"
    assert len(HANDBOOK) == 462, f"expected 462 handbook entries, got {len(HANDBOOK)}"
    print(f"kilnstone_gen: assets generated for {len(ids)} blocks "
          f"({recipe_count} recipes, {len(HANDBOOK)} handbook entries).")


if __name__ == "__main__":
    main()
