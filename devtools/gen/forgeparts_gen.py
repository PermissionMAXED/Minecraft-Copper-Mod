#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "forgeparts" feature (120 crafting items).

10 fictional forge metals (emberite, cindrium, pyrium, scorium, brazium, volkanite,
fumarite, ignitium, calderium, obsidium); each ships a 12-part crafting chain:
dust, powder, ingot, alloy, gem, shard, rod, plate, gear, coil, core, catalyst.

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via genlib.rng_for). Emits by DEFAULT (no flags):
  - item model-definitions   assets/copper_inferno/items/<id>.json          (genlib.emit_item_def)
  - item models              assets/copper_inferno/models/item/<id>.json    (genlib.emit_item_model)
  - 16x16 RGBA item textures assets/copper_inferno/textures/item/<id>.png   (Pillow, seeded)
  - recipes                  data/copper_inferno/recipe/forgeparts/*.json   (12 per metal = 120)
  - lang fragments: assets/copper_inferno/lang/fragments/forgeparts.json (EN)
    and assets/copper_inferno/lang/fragments_de/forgeparts.json (real German)
  - src/main/java/.../feature/forgeparts/ForgePartsFeature.java + ForgePartsHandbook.java
    (genlib.java_feature_class / genlib.java_handbook_class; literal ids only)
  - devtools/hooks/forgeparts.txt (integration hook file)

Every recipe includes at least one copper_inferno id among its INPUTS, so no recipe can
collide with vanilla or other features' recipes. The dust recipes form an open 10-link
CHAIN (no cycle): metal[0] (emberite) is the ENTRY, its dust crafted from obtainable
materials (magma_cream corners + infernium ember_dust centre); each later metal's dust
uses the previous metal's dust in the centre.
All JSON structures come from genlib and are byte-identical to the vanilla 1.21.9 formats.
Do NOT "improve" them.
"""

import math
import sys
from collections import namedtuple
from pathlib import Path
from random import Random

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, rng_for, write_json

RECIPES = DATA / "recipe" / "forgeparts"
FEATURE_DIR = (ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno"
               / "feature" / "forgeparts")

# ---------------------------------------------------------------------------
# Metals. Each gets a DISTINCT deterministic base color (the 6-shade ramp and the two
# accents are derived arithmetically, so palettes stay deterministic) plus a vanilla
# flavor reagent for the dust recipe. de is the Germanized metal stem used for the
# closed German compounds (Emberitstaub, Cindriumbarren, ...).
# ---------------------------------------------------------------------------
Metal = namedtuple("Metal", "mid en de vanilla base")

METALS = [
    Metal("emberite", "Emberite", "Emberit", "minecraft:magma_cream", (0xE0, 0x62, 0x28)),
    Metal("cindrium", "Cindrium", "Cindrium", "minecraft:charcoal", (0x6E, 0x5A, 0x52)),
    Metal("pyrium", "Pyrium", "Pyrium", "minecraft:blaze_powder", (0xE8, 0x9C, 0x2C)),
    Metal("scorium", "Scorium", "Skorium", "minecraft:gunpowder", (0x8C, 0x30, 0x24)),
    Metal("brazium", "Brazium", "Brazium", "minecraft:gold_nugget", (0xC8, 0x8A, 0x3A)),
    Metal("volkanite", "Volkanite", "Volkanit", "minecraft:basalt", (0x54, 0x4A, 0x5E)),
    Metal("fumarite", "Fumarite", "Fumarit", "minecraft:glowstone_dust", (0xB0, 0xA6, 0x58)),
    Metal("ignitium", "Ignitium", "Ignitium", "minecraft:flint", (0xD8, 0x38, 0x30)),
    Metal("calderium", "Calderium", "Calderium", "minecraft:quartz", (0x4E, 0x86, 0x8E)),
    Metal("obsidium", "Obsidium", "Obsidium", "minecraft:obsidian", (0x38, 0x30, 0x48)),
]

assert len(METALS) == 10
assert len({m.mid for m in METALS}) == 10
assert len({m.base for m in METALS}) == 10

# Chain order == creative-tab order == handbook order.
PARTS = ["dust", "powder", "ingot", "alloy", "gem", "shard",
         "rod", "plate", "gear", "coil", "core", "catalyst"]

PART_EN = {"dust": "Dust", "powder": "Powder", "ingot": "Ingot", "alloy": "Alloy",
           "gem": "Gem", "shard": "Shard", "rod": "Rod", "plate": "Plate",
           "gear": "Gear", "coil": "Coil", "core": "Core", "catalyst": "Catalyst"}

# Real German part nouns; compounds close up with the metal stem (like Kupferstaub).
PART_DE = {"dust": "staub", "powder": "pulver", "ingot": "barren", "alloy": "legierung",
           "gem": "juwel", "shard": "splitter", "rod": "stab", "plate": "platte",
           "gear": "zahnrad", "coil": "spule", "core": "kern", "catalyst": "katalysator"}

ITEM_IDS = [f"{m.mid}_{p}" for m in METALS for p in PARTS]

EN = {f"{m.mid}_{p}": f"{m.en} {PART_EN[p]}" for m in METALS for p in PARTS}
DE = {f"{m.mid}_{p}": f"{m.de}{PART_DE[p]}" for m in METALS for p in PARTS}


# ---------------------------------------------------------------------------
# Palettes (derived deterministically from each metal's base color).
# ---------------------------------------------------------------------------

def _lighten(c, t):
    return tuple(min(255, round(v + (255 - v) * t)) for v in c)


def _darken(c, t):
    return tuple(max(0, round(v * (1.0 - t))) for v in c)


def palette_of(metal: Metal) -> dict:
    b = metal.base
    return {
        "hi": _lighten(b, 0.55),
        "light": _lighten(b, 0.30),
        "base": b,
        "dark": _darken(b, 0.25),
        "darker": _darken(b, 0.45),
        "outline": _darken(b, 0.65),
        "accent": _lighten(b, 0.50),
        "accent_hot": _lighten(b, 0.78),
    }


# Copper tones for the alloy specks (the alloys are copper alloys, this is Copper Inferno).
COPPER = (0xE0, 0x73, 0x4D)
COPPER_DARK = (0xC1, 0x5A, 0x3B)


# ---------------------------------------------------------------------------
# Texture painters (16x16 RGBA, one per part type; recognizable silhouettes adapted from
# the PROVEN painters in devtools/gen/materials_gen.py, parameterized by palette).
# ---------------------------------------------------------------------------

def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def tex_dust(rng: Random, p: dict) -> Image.Image:
    """Rounded mound of coarse dust with a few loose specks above it."""
    img = blank()
    rows = {13: (2, 13), 12: (3, 12), 11: (3, 12), 10: (4, 11), 9: (5, 10), 8: (6, 9), 7: (7, 8)}
    base_palette = [p["base"], p["base"], p["light"], p["dark"], p["darker"]]
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            edge = x in (x0, x1) or y == 13
            color = base_palette[-1] if edge else rng.choice(base_palette)
            px(img, x, y, color)
    for x, y in [(4, 6), (10, 5), (6, 5), (12, 8), (8, 4)]:
        if rng.random() < 0.9:
            px(img, x, y, rng.choice([p["base"], p["dark"]]))
    return img


def tex_powder(rng: Random, p: dict) -> Image.Image:
    """Flatter, refined mound with bright sparkle specks (distinct from the dust pile)."""
    img = blank()
    rows = {13: (3, 12), 12: (4, 11), 11: (4, 11), 10: (5, 10), 9: (6, 9)}
    base_palette = [p["light"], p["base"], p["light"], p["accent"], p["dark"]]
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            edge = x in (x0, x1) or y == 13
            color = p["darker"] if edge else rng.choice(base_palette)
            px(img, x, y, color)
    for x, y in [(5, 8), (10, 7), (7, 6), (12, 9), (3, 10)]:
        if rng.random() < 0.9:
            px(img, x, y, rng.choice([p["accent"], p["accent_hot"]]))
    return img


def _ingot(rng: Random, hi, light, base, dark, outline, speck=None) -> Image.Image:
    """Classic vanilla-style ingot silhouette with a slanted top face."""
    img = blank()
    for y, (x0, x1) in {4: (5, 12), 5: (4, 12), 6: (3, 12)}.items():
        for x in range(x0, x1 + 1):
            px(img, x, y, hi if y == 4 or x in (x0,) else light)
    for y in range(7, 12):
        for x in range(2, 14):
            color = base
            if y >= 10:
                color = dark
            if x in (2, 13):
                color = dark
            px(img, x, y, color)
    for x in range(5, 13):
        px(img, x, 3, outline)
    px(img, 4, 4, outline)
    px(img, 3, 5, outline)
    px(img, 2, 6, outline)
    px(img, 13, 4, outline)
    px(img, 13, 5, outline)
    px(img, 14, 6, outline)
    for y in range(7, 12):
        px(img, 1, y, outline)
        px(img, 14, y, outline)
    for x in range(2, 14):
        px(img, x, 12, outline)
    if speck:
        for x, y in [(5, 8), (9, 9), (11, 8), (7, 10)]:
            if rng.random() < 0.85:
                px(img, x, y, rng.choice(speck))
    return img


def tex_ingot(rng: Random, p: dict) -> Image.Image:
    return _ingot(rng, p["hi"], p["light"], p["base"], p["dark"], p["outline"])


def tex_alloy(rng: Random, p: dict) -> Image.Image:
    """Ingot with copper marbling specks (the copper-alloy variant)."""
    img = _ingot(rng, p["hi"], p["light"], p["base"], p["dark"], p["outline"],
                 speck=[COPPER, COPPER_DARK])
    for x in (4, 8, 12):
        px(img, x, 7, COPPER)
    return img


def tex_gem(rng: Random, p: dict) -> Image.Image:
    """Faceted rhombus with an upper-left highlight facet and outlined edges."""
    img = blank()
    rows = {2: (7, 8), 3: (6, 9), 4: (5, 10), 5: (4, 11), 6: (3, 12), 7: (2, 13),
            8: (2, 13), 9: (3, 12), 10: (4, 11), 11: (5, 10), 12: (6, 9), 13: (7, 8)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x in (x0, x1):
                color = p["outline"]
            elif y <= 5 and x <= 8:
                color = p["hi"]
            elif x <= x0 + 2 or y <= 5:
                color = p["light"]
            elif x >= x1 - 2 and y >= 9:
                color = p["dark"]
            else:
                color = p["base"] if (x + y) % 3 else p["accent"]
            px(img, x, y, color)
    for y in range(4, 12):  # central facet line
        px(img, 8, y, p["accent"] if y % 2 else p["light"])
    px(img, 6, 4, p["accent_hot"])
    return img


def tex_shard(rng: Random, p: dict) -> Image.Image:
    """Slanted crystal shard with a facet highlight and a soft accent halo."""
    img = blank()
    rows = {2: (9, 9), 3: (8, 10), 4: (8, 10), 5: (7, 10), 6: (7, 10), 7: (6, 9),
            8: (6, 9), 9: (5, 9), 10: (5, 8), 11: (5, 8), 12: (6, 7), 13: (6, 6)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x == x0:
                color = p["hi"]
            elif x == x1:
                color = p["darker"]
            else:
                color = p["light"] if (x + y) % 3 else p["base"]
            px(img, x, y, color)
    for y in range(4, 12):  # facet line
        px(img, rows[y][0] + 1, y, p["accent_hot"] if y % 2 else p["accent"])
    for y, (x0, x1) in rows.items():  # glow halo (semi-transparent)
        px(img, x0 - 1, y, p["accent"], 90)
        px(img, x1 + 1, y, p["accent"], 90)
    px(img, 9, 1, p["accent"], 110)
    px(img, 6, 14, p["accent"], 110)
    return img


def tex_rod(rng: Random, p: dict) -> Image.Image:
    """Diagonal 2px rod, highlight on the upper edge, rounded caps."""
    img = blank()
    for i in range(12):
        x, y = 2 + i, 13 - i
        px(img, x, y, p["dark"])
        px(img, x + 1, y, p["base"])
        px(img, x + 1, y - 1, p["light"])
    px(img, 2, 14, p["darker"])
    px(img, 14, 1, p["hi"])
    px(img, 3, 14, p["darker"])
    return img


def tex_plate(rng: Random, p: dict) -> Image.Image:
    """Flat riveted plate with a bright rolled top edge and a diagonal sheen."""
    img = blank()
    for y in range(5, 12):
        for x in range(2, 14):
            if (x, y) in [(2, 5), (13, 5), (2, 11), (13, 11)]:
                continue
            if y == 5:
                color = p["light"]
            elif y == 11 or x == 13:
                color = p["darker"]
            elif x == 2:
                color = p["dark"]
            else:
                color = p["base"]
            px(img, x, y, color)
    for i in range(5):
        px(img, 4 + i * 2, 6 + i, p["hi"] if i < 2 else p["light"])
    for x, y in [(3, 6), (12, 6), (3, 10), (12, 10)]:  # corner rivets
        px(img, x, y, p["outline"])
    return img


def tex_gear(rng: Random, p: dict) -> Image.Image:
    """Toothed ring with a transparent axle hole."""
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            dx, dy = x - cx, y - cy
            r = math.hypot(dx, dy)
            ang = (math.degrees(math.atan2(dy, dx)) + 360.0) % 45.0
            tooth = 5.2 <= r <= 7.2 and (ang < 9.0 or ang > 36.0)
            ring = 2.9 <= r <= 5.2
            if tooth or ring:
                if dx + dy < -3:
                    color = p["light"]
                elif dx + dy > 4:
                    color = p["darker"]
                else:
                    color = p["base"] if ring else p["dark"]
                px(img, x, y, color)
            elif 2.1 <= r < 2.9:
                px(img, x, y, p["outline"])
    return img


def tex_coil(rng: Random, p: dict) -> Image.Image:
    """Tight coil: stacked wire loops on a vertical cylinder with exit tails."""
    img = blank()
    for y in range(3, 13):
        loop_top = (y - 3) % 2 == 0
        for x in range(4, 12):
            if x in (4, 11):
                c = p["darker"]
            elif x in (5, 10):
                c = p["dark"]
            elif loop_top:
                c = p["light"] if x in (6, 7) else p["base"]
            else:
                c = p["dark"]
            px(img, x, y, c)
    for x in range(4, 12):  # end caps
        px(img, x, 2, p["outline"])
        px(img, x, 13, p["outline"])
    px(img, 12, 2, p["base"])
    px(img, 13, 1, p["hi"])
    px(img, 3, 13, p["dark"])
    px(img, 2, 14, p["darker"])
    return img


def tex_core(rng: Random, p: dict) -> Image.Image:
    """Housed energy orb: outlined casing ring, metal shell, glowing accent centre."""
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            r = math.hypot(x - cx, y - cy)
            if r <= 6.4:
                if r > 5.4:
                    color = p["outline"]
                elif r > 4.2:
                    color = p["dark"] if x + y > 15 else p["base"]
                elif r > 2.4:
                    color = p["accent"]
                else:
                    color = p["accent_hot"]
                px(img, x, y, color)
    for x, y in [(7, 1), (14, 7), (7, 14), (1, 7), (8, 1), (14, 8), (8, 14), (1, 8)]:
        px(img, x, y, p["darker"])  # casing studs
    px(img, 5, 5, p["hi"])
    return img


def tex_catalyst(rng: Random, p: dict) -> Image.Image:
    """Round flask with a corked neck, filled with bubbling accent reagent."""
    img = blank()
    for x in (6, 9):  # neck walls
        for y in (2, 3, 4):
            px(img, x, y, p["outline"])
    for x in (7, 8):
        px(img, x, 1, p["darker"])  # cork
        for y in (2, 3, 4):
            px(img, x, y, p["hi"], 170)
    cx, cy = 7.5, 9.5
    for y in range(5, 15):
        for x in range(2, 14):
            r = math.hypot(x - cx, y - cy)
            if r <= 4.9:
                if r > 3.9:
                    color, alpha = p["outline"], 255
                elif y >= 8:
                    color, alpha = (p["accent"] if (x + y) % 3 else p["base"]), 255
                else:
                    color, alpha = p["hi"], 150
                px(img, x, y, color, alpha)
    for x, y in [(6, 9), (9, 11), (7, 12), (10, 9)]:  # bubbles
        if rng.random() < 0.9:
            px(img, x, y, p["accent_hot"])
    return img


PAINTERS = {"dust": tex_dust, "powder": tex_powder, "ingot": tex_ingot, "alloy": tex_alloy,
            "gem": tex_gem, "shard": tex_shard, "rod": tex_rod, "plate": tex_plate,
            "gear": tex_gear, "coil": tex_coil, "core": tex_core, "catalyst": tex_catalyst}


def emit_textures() -> None:
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for metal in METALS:
        pal = palette_of(metal)
        for part in PARTS:
            item_id = f"{metal.mid}_{part}"
            img = PAINTERS[part](rng_for(item_id), pal)
            img.save(tex_dir / f"{item_id}.png")


# ---------------------------------------------------------------------------
# Handbook entries (collected while emitting recipes; rendered by java_handbook_class).
# ---------------------------------------------------------------------------
HANDBOOK = []


def hb_overview(metal: Metal) -> None:
    """One "items" overview entry per metal covering its whole 12-part chain."""
    HANDBOOK.append((
        "items", f"forgeparts/set_{metal.mid}", f"{NS}:{metal.mid}_ingot", None, None,
        None, 0,
        f"The {metal.en} forge-part chain: grind {metal.en} Dust, refine it into powder "
        "and smelt ingots, then work them into alloys, gems, shards, rods, plates, "
        "gears, coils, cores and catalysts.",
        f"Die {metal.de}-Schmiedeteilkette: {metal.de}staub mahlen, zu Pulver verfeinern "
        "und zu Barren schmelzen, dann zu Legierungen, Juwelen, Splittern, St\u00e4ben, "
        "Platten, Zahnr\u00e4dern, Spulen, Kernen und Katalysatoren verarbeiten."))


def hb_crafting(name: str, grid: list, result: str, count: int) -> None:
    HANDBOOK.append(("items", f"forgeparts/{name}", f"{NS}:{result}", f"forgeparts/{name}",
                     grid, f"{NS}:{result}", count,
                     f"Craft {count}x {EN[result]} at a crafting table.",
                     f"Stellt {count}x {DE[result]} an der Werkbank her."))


def hb_smelting(name: str, ingredient: str, result: str) -> None:
    grid = ["", "", "", "", f"{NS}:{ingredient}", "", "", "", ""]
    HANDBOOK.append(("items", f"forgeparts/{name}", f"{NS}:{result}", f"forgeparts/{name}",
                     grid, f"{NS}:{result}", 1,
                     f"Smelting {EN[ingredient]} in a furnace yields {EN[result]}.",
                     f"{DE[ingredient]} im Ofen geschmolzen ergibt {DE[result]}."))


# ---------------------------------------------------------------------------
# Recipes (12 per metal = 120; every recipe has at least one copper_inferno id among
# its ingredients — the dust recipes form an open chain with an entry recipe (no
# cycle), so inputs never collide with other recipes).
# ---------------------------------------------------------------------------

def shapeless_grid(ingredients: list) -> list:
    return ingredients + [""] * (9 - len(ingredients))


def emit_recipes() -> int:
    ci = f"{NS}:"
    count = 0
    for i, metal in enumerate(METALS):
        m = metal.mid
        dust, powder, ingot = f"{ci}{m}_dust", f"{ci}{m}_powder", f"{ci}{m}_ingot"
        gem, shard, rod = f"{ci}{m}_gem", f"{ci}{m}_shard", f"{ci}{m}_rod"
        plate = f"{ci}{m}_plate"

        # dust: always ["V V", " P ", "V V"] -> 4 under the same file name.
        # metal[0] (emberite) is the chain ENTRY: its dust is crafted from already-
        # obtainable materials (magma_cream corners + infernium ember_dust centre) so
        # the 10-link dust chain has an entry point and no crafting cycle. Metals
        # i >= 1 use 4 vanilla flavor reagents around the PREVIOUS metal's dust.
        centre = f"{ci}ember_dust" if i == 0 else f"{ci}{METALS[i - 1].mid}_dust"
        genlib.emit_shaped(RECIPES, f"{m}_dust", {"V": metal.vanilla, "P": centre},
                           ["V V", " P ", "V V"], dust, 4, category="misc")
        hb_crafting(f"{m}_dust", [metal.vanilla, "", metal.vanilla, "", centre, "",
                                  metal.vanilla, "", metal.vanilla], f"{m}_dust", 4)
        count += 1

        # powder: dust refined with blaze powder -> 2.
        genlib.emit_shapeless(RECIPES, f"{m}_powder", [dust, "minecraft:blaze_powder"],
                              powder, 2, category="misc")
        hb_crafting(f"{m}_powder", shapeless_grid([dust, "minecraft:blaze_powder"]),
                    f"{m}_powder", 2)
        count += 1

        # ingot: smelt the dust (vanilla ore-dust smelting profile).
        genlib.emit_smelting(RECIPES, f"{m}_ingot", dust, ingot,
                             category="misc", experience=0.7)
        hb_smelting(f"{m}_ingot", f"{m}_dust", f"{m}_ingot")
        count += 1

        # alloy: ingot fused with vanilla copper -> 2.
        genlib.emit_shapeless(RECIPES, f"{m}_alloy", [ingot, "minecraft:copper_ingot"],
                              f"{ci}{m}_alloy", 2, category="misc")
        hb_crafting(f"{m}_alloy", shapeless_grid([ingot, "minecraft:copper_ingot"]),
                    f"{m}_alloy", 2)
        count += 1

        # gem: 4 powder pressed around an amethyst seed crystal -> 1.
        genlib.emit_shaped(RECIPES, f"{m}_gem",
                           {"#": powder, "A": "minecraft:amethyst_shard"},
                           [" # ", "#A#", " # "], gem, 1, category="misc")
        hb_crafting(f"{m}_gem", ["", powder, "", powder, "minecraft:amethyst_shard",
                                 powder, "", powder, ""], f"{m}_gem", 1)
        count += 1

        # shard: crack one gem into 4 shards.
        genlib.emit_shapeless(RECIPES, f"{m}_shard", [gem], shard, 4, category="misc")
        hb_crafting(f"{m}_shard", shapeless_grid([gem]), f"{m}_shard", 4)
        count += 1

        # rod: 2 ingots in a column -> 4.
        genlib.emit_shaped(RECIPES, f"{m}_rod", {"#": ingot}, ["#", "#"], rod, 4,
                           category="misc")
        hb_crafting(f"{m}_rod", [ingot, "", "", ingot, "", "", "", "", ""], f"{m}_rod", 4)
        count += 1

        # plate: 2 ingots in a row -> 2.
        genlib.emit_shaped(RECIPES, f"{m}_plate", {"#": ingot}, ["##"], plate, 2,
                           category="misc")
        hb_crafting(f"{m}_plate", [ingot, ingot, "", "", "", "", "", "", ""],
                    f"{m}_plate", 2)
        count += 1

        # gear: 4 rods in a diamond around an ingot hub -> 1.
        genlib.emit_shaped(RECIPES, f"{m}_gear", {"#": rod, "N": ingot},
                           [" # ", "#N#", " # "], f"{ci}{m}_gear", 1, category="misc")
        hb_crafting(f"{m}_gear", ["", rod, "", rod, ingot, rod, "", rod, ""],
                    f"{m}_gear", 1)
        count += 1

        # coil: 8 rods wound in a ring -> 2.
        genlib.emit_shaped(RECIPES, f"{m}_coil", {"#": rod}, ["###", "# #", "###"],
                           f"{ci}{m}_coil", 2, category="misc")
        hb_crafting(f"{m}_coil", [rod, rod, rod, rod, "", rod, rod, rod, rod],
                    f"{m}_coil", 2)
        count += 1

        # core: 4 plates housing a gem -> 1.
        genlib.emit_shaped(RECIPES, f"{m}_core", {"P": plate, "G": gem},
                           [" P ", "PGP", " P "], f"{ci}{m}_core", 1, category="misc")
        hb_crafting(f"{m}_core", ["", plate, "", plate, gem, plate, "", plate, ""],
                    f"{m}_core", 1)
        count += 1

        # catalyst: powder + shard activated with redstone -> 2.
        genlib.emit_shapeless(RECIPES, f"{m}_catalyst",
                              [powder, shard, "minecraft:redstone"],
                              f"{ci}{m}_catalyst", 2, category="misc")
        hb_crafting(f"{m}_catalyst",
                    shapeless_grid([powder, shard, "minecraft:redstone"]),
                    f"{m}_catalyst", 2)
        count += 1
    return count


# ---------------------------------------------------------------------------
# Java codegen (genlib.java_feature_class / java_handbook_class; literal ids only so
# devtools/audit_assets.py check (f) and devtools/check_handbook.py can parse them).
# ---------------------------------------------------------------------------

def field_of(item_id: str) -> str:
    return item_id.upper()


def emit_java() -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)

    items = [(field_of(i), i, "Item::new", "new Item.Settings()") for i in ITEM_IDS]
    tab_entries = [field_of(i) for i in ITEM_IDS]

    feature_doc = [
        "Forge parts: 120 crafting materials across 10 fictional forge metals (emberite,",
        "cindrium, pyrium, scorium, brazium, volkanite, fumarite, ignitium, calderium,",
        "obsidium). Each metal ships a 12-part chain: dust, powder, ingot, alloy, gem,",
        "shard, rod, plate, gear, coil, core and catalyst. Plain items; all flavor comes",
        "from the lang entries and the recipe chains.",
        "",
        "<p>Assets (item definitions, models, textures, recipes, EN+DE lang fragments) are",
        "generated by {@code devtools/gen/forgeparts_gen.py}; handbook pages are registered",
        "by {@link ForgePartsHandbook}.",
    ]
    feature_src = genlib.java_feature_class(
        "forgeparts", "ForgePartsFeature", feature_doc,
        items=items,
        tabs=[("MAIN_KEY", tab_entries)],
        handbook_class="ForgePartsHandbook",
    )
    (FEATURE_DIR / "ForgePartsFeature.java").write_text(feature_src, encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the forge parts: one \"items\" overview per metal plus one",
        "recipe page for every JSON under {@code data/copper_inferno/recipe/forgeparts/}",
        "(crafting and smelting). Entry texts and grids mirror the recipe JSONs emitted by",
        "{@code devtools/gen/forgeparts_gen.py}; {@code devtools/check_handbook.py} parses",
        "the inline {@code new HandbookEntry(...)} literals positionally, so keep them",
        "inline.",
    ]
    handbook_src = genlib.java_handbook_class("forgeparts", "ForgePartsHandbook",
                                              handbook_doc, HANDBOOK)
    (FEATURE_DIR / "ForgePartsHandbook.java").write_text(handbook_src, encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/forgeparts.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks(recipe_count: int) -> None:
    lines = ["# forgeparts feature hooks (format: devtools/hooks/README.md)", "",
             "[init]",
             "# Insert BEFORE HandbookFeature.init(); no other ordering constraint",
             "# (recipes consume only vanilla ids, forgeparts' own ids and infernium's",
             "# ember_dust — recipes are data, so no init-order dependency).",
             "import net.sonic0810.copperinferno.feature.forgeparts.ForgePartsFeature;",
             "\t\tForgePartsFeature.init();", "",
             "[recipe-dir]",
             "forgeparts", "",
             "[counts]",
             f"items: {len(ITEM_IDS)}",
             f"recipes: {recipe_count}",
             f"handbook-entries: {len(HANDBOOK)}", ""]
    path = ROOT / "devtools" / "hooks" / "forgeparts.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    for item_id in ITEM_IDS:
        genlib.emit_item_def(ASSETS, item_id)
        genlib.emit_item_model(ASSETS, item_id)
    emit_textures()

    for metal in METALS:
        hb_overview(metal)
    recipe_count = emit_recipes()

    lang_en = {f"item.{NS}.{i}": EN[i] for i in ITEM_IDS}
    lang_de = {f"item.{NS}.{i}": DE[i] for i in ITEM_IDS}
    genlib.lang_fragments(ASSETS, "forgeparts", lang_en, lang_de)

    emit_java()
    emit_hooks(recipe_count)

    assert len(ITEM_IDS) == 120, f"expected 120 item ids, got {len(ITEM_IDS)}"
    assert len(set(ITEM_IDS)) == 120, "duplicate item ids emitted"
    expected_keys = sorted(f"item.{NS}.{i}" for i in ITEM_IDS)
    assert expected_keys == sorted(lang_en) == sorted(lang_de)
    assert recipe_count == 120, f"expected 120 recipes, got {recipe_count}"
    # 10 metal overviews + one entry per recipe JSON.
    assert len(HANDBOOK) == 10 + recipe_count, \
        f"expected {10 + recipe_count} handbook entries, got {len(HANDBOOK)}"
    print(f"forgeparts_gen: assets generated for {len(ITEM_IDS)} items "
          f"({recipe_count} recipes, {len(HANDBOOK)} handbook entries).")


if __name__ == "__main__":
    main()
