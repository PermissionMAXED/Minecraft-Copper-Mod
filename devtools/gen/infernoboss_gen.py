#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "infernoboss" feature (2 bosses, 8 items).

Idempotent: running it any number of times produces byte-identical files. Emits, directly
into src/main/resources:
  - 16x16 RGBA item textures assets/copper_inferno/textures/item/<id>.png   (Pillow, seeded)
and, with --write-json (legacy scaffolding; the JSON in src/main/resources is authoritative):
  - item model-definitions   assets/copper_inferno/items/<id>.json          (dr_pepper_golem format)
  - item models              assets/copper_inferno/models/item/<id>.json    (item/generated, layer0)
  - entity loot tables       data/copper_inferno/loot_table/entities/<boss>.json
                             (dr_pepper_golem schema incl. "random_sequence")
  - recipes                  data/copper_inferno/recipe/infernoboss/*.json
  - lang fragments           assets/copper_inferno/lang/fragments/infernoboss.json (EN)
                             assets/copper_inferno/lang/fragments_de/infernoboss.json (DE)

Items: 2 summon items (oxidizer_core, titan_sigil), 4 boss drops (oxidizer_heart,
verdigris_scale, titan_ember, inferno_crown) and 2 spawn eggs (the_oxidizer_spawn_egg,
inferno_titan_spawn_egg). Recipes reference ONLY vanilla ids, this feature's own ids and
existing v2 ids (materials' oxidized_copper_dust / inferno_powder, inferno's inferno_core).
"""

import json
import math
import sys
from pathlib import Path
from random import Random

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> textures/*.png only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
NS = "copper_inferno"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# boss id -> list of (drop item id, min count, max count); one loot pool per drop.
BOSSES = {
    "the_oxidizer": [(f"{NS}:oxidizer_heart", 1.0, 1.0), (f"{NS}:verdigris_scale", 2.0, 4.0)],
    "inferno_titan": [(f"{NS}:titan_ember", 2.0, 3.0), (f"{NS}:inferno_crown", 1.0, 1.0)],
}

ITEM_IDS = [
    "oxidizer_core",
    "titan_sigil",
    "oxidizer_heart",
    "verdigris_scale",
    "titan_ember",
    "inferno_crown",
    "the_oxidizer_spawn_egg",
    "inferno_titan_spawn_egg",
]

LANG_EN = {
    "entity.copper_inferno.the_oxidizer": "The Oxidizer",
    "entity.copper_inferno.inferno_titan": "Inferno Titan",
    "item.copper_inferno.oxidizer_core": "Oxidizer Core",
    "item.copper_inferno.titan_sigil": "Titan Sigil",
    "item.copper_inferno.oxidizer_heart": "Oxidizer Heart",
    "item.copper_inferno.verdigris_scale": "Verdigris Scale",
    "item.copper_inferno.titan_ember": "Titan Ember",
    "item.copper_inferno.inferno_crown": "Inferno Crown",
    "item.copper_inferno.the_oxidizer_spawn_egg": "The Oxidizer Spawn Egg",
    "item.copper_inferno.inferno_titan_spawn_egg": "Inferno Titan Spawn Egg",
    "message.copper_inferno.titan_sigil.wrong_dimension": "The Titan Sigil only answers within the Inferno dimension.",
}

LANG_DE = {
    "entity.copper_inferno.the_oxidizer": "Der Oxidierer",
    "entity.copper_inferno.inferno_titan": "Inferno-Titan",
    "item.copper_inferno.oxidizer_core": "Oxidierer-Kern",
    "item.copper_inferno.titan_sigil": "Titanensiegel",
    "item.copper_inferno.oxidizer_heart": "Oxidierer-Herz",
    "item.copper_inferno.verdigris_scale": "Grünspanschuppe",
    "item.copper_inferno.titan_ember": "Titanenglut",
    "item.copper_inferno.inferno_crown": "Infernokrone",
    "item.copper_inferno.the_oxidizer_spawn_egg": "Oxidierer-Spawn-Ei",
    "item.copper_inferno.inferno_titan_spawn_egg": "Inferno-Titan-Spawn-Ei",
    "message.copper_inferno.titan_sigil.wrong_dimension": "Das Titanensiegel wirkt nur in der Inferno-Dimension.",
}

# ---------------------------------------------------------------------------
# Palettes
# ---------------------------------------------------------------------------

COPPER = (0xE0, 0x73, 0x4D)
COPPER_DARK = (0xC1, 0x5A, 0x3B)
COPPER_DEEP = (0x8F, 0x40, 0x2A)

OXIDE_LIGHT = (0x6F, 0xB0, 0x8E)
OXIDE = (0x57, 0xA0, 0x7B)
OXIDE_DEEP = (0x4E, 0x9E, 0x7A)
OXIDE_DARK = (0x37, 0x6E, 0x55)
OXIDE_SHADOW = (0x27, 0x4E, 0x3D)

EMBER = (0xE2, 0x58, 0x22)
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)
EMBER_HOT = (0xFF, 0xB1, 0x6B)
EMBER_YELLOW = (0xFF, 0xD8, 0x66)

CHARCOAL_LIGHT = (0x4A, 0x36, 0x3A)
CHARCOAL = (0x3D, 0x2C, 0x2E)
CHARCOAL_DARK = (0x2B, 0x22, 0x26)

GOLD = (0xF6, 0xC1, 0x2B)
GOLD_DARK = (0xC6, 0x8A, 0x00)
GOLD_LIGHT = (0xFF, 0xE0, 0x82)


# ---------------------------------------------------------------------------
# Generic helpers
# ---------------------------------------------------------------------------

def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True, ensure_ascii=False) + "\n",
                    encoding="utf-8")


def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def spawn_egg(rng: Random, base, base_dark, base_light, spots, outline) -> Image.Image:
    """Classic spawn-egg silhouette (narrow top, wide bottom) with seeded speckles."""
    img = blank()
    half = {2: 1.6, 3: 2.4, 4: 3.0, 5: 3.5, 6: 4.0, 7: 4.4, 8: 4.7, 9: 4.9,
            10: 5.0, 11: 5.0, 12: 4.7, 13: 4.0, 14: 2.8}
    cells = []
    for y, h in half.items():
        x0 = int(math.ceil(7.5 - h))
        x1 = int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            cells.append((x, y, x == x0 or x == x1 or y in (2, 14)))
    for x, y, edge in cells:
        if edge:
            color = outline
        elif x <= 5 and y <= 9:
            color = base_light  # top-left sheen
        elif x >= 10 or y >= 12:
            color = base_dark
        else:
            color = base
        px(img, x, y, color)
    interior = [(x, y) for x, y, edge in cells if not edge]
    for x, y in rng.sample(interior, 12):
        px(img, x, y, spots)
    return img


# ---------------------------------------------------------------------------
# Texture painters
# ---------------------------------------------------------------------------

def tex_oxidizer_core(rng: Random) -> Image.Image:
    """Oxidized-green orb caged in a copper ring: the Oxidizer's summoning core."""
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - cx, y - cy)
            if d <= 6.2:
                if d > 5.2:
                    color = COPPER_DEEP
                elif d > 4.3:
                    color = COPPER if (x + y) % 2 else COPPER_DARK
                elif d > 2.4:
                    color = OXIDE if (x * 3 + y * 5) % 7 else OXIDE_DEEP
                else:
                    color = OXIDE_LIGHT
                px(img, x, y, color)
    # Verdigris veins crawling over the copper ring.
    for x, y in [(3, 4), (12, 4), (2, 9), (13, 9), (5, 13), (10, 13)]:
        px(img, x, y, OXIDE_DARK)
    # Glinting heart.
    px(img, 7, 7, (0xD8, 0xF5, 0xE6))
    px(img, 8, 6, OXIDE_LIGHT)
    return img


def tex_titan_sigil(rng: Random) -> Image.Image:
    """Charred stone tablet carved with a blazing titan rune."""
    img = blank()
    for y in range(2, 14):
        for x in range(3, 13):
            if x in (3, 12) or y in (2, 13):
                color = CHARCOAL_DARK
            elif (x * 7 + y * 3) % 11 == 0:
                color = CHARCOAL_LIGHT
            else:
                color = CHARCOAL
            px(img, x, y, color)
    # Rune: vertical stroke with two arms and a crown point, glowing hot.
    for y in range(4, 12):
        px(img, 7, y, EMBER_BRIGHT if y % 2 else EMBER)
    for x, y in [(5, 5), (6, 4), (9, 5), (8, 4), (5, 9), (6, 10), (9, 9), (8, 10)]:
        px(img, x, y, EMBER)
    px(img, 7, 3, EMBER_YELLOW)
    px(img, 7, 12, EMBER_HOT)
    return img


def tex_oxidizer_heart(rng: Random) -> Image.Image:
    """Still-beating heart of oxidized copper: green heart shape, copper aorta."""
    img = blank()
    rows = {3: [(4, 6), (9, 11)], 4: [(3, 12)], 5: [(3, 12)], 6: [(3, 12)],
            7: [(4, 11)], 8: [(5, 10)], 9: [(6, 9)], 10: [(6, 9)], 11: [(7, 8)], 12: [(7, 8)]}
    for y, spans in rows.items():
        for x0, x1 in spans:
            for x in range(x0, x1 + 1):
                edge = x in (x0, x1) or y in (3, 12)
                if edge:
                    color = OXIDE_SHADOW
                elif x <= 6 and y <= 7:
                    color = OXIDE_LIGHT
                elif y >= 9:
                    color = OXIDE_DARK
                else:
                    color = OXIDE
                px(img, x, y, color)
    # Copper aorta stub + glint.
    px(img, 7, 2, COPPER)
    px(img, 8, 2, COPPER_DARK)
    px(img, 5, 5, (0xD8, 0xF5, 0xE6))
    return img


def tex_verdigris_scale(rng: Random) -> Image.Image:
    """Teardrop armour scale shed by the Oxidizer, banded in verdigris greens."""
    img = blank()
    half = {2: 0.6, 3: 1.4, 4: 2.1, 5: 2.7, 6: 3.3, 7: 3.8, 8: 4.2, 9: 4.5,
            10: 4.7, 11: 4.7, 12: 4.2, 13: 3.2}
    for y, h in half.items():
        x0 = int(math.ceil(7.5 - h))
        x1 = int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (2, 13):
                color = OXIDE_SHADOW
            elif y in (5, 8, 11):
                color = OXIDE_DARK  # growth bands
            elif x <= 6 and y <= 9:
                color = OXIDE_LIGHT
            else:
                color = OXIDE
            px(img, x, y, color)
    # Coppery base rim not yet oxidized.
    for x in range(6, 10):
        px(img, x, 14, COPPER_DARK)
    px(img, 6, 4, (0xD8, 0xF5, 0xE6))
    return img


def tex_titan_ember(rng: Random) -> Image.Image:
    """Fist-sized ember torn from the Titan: angular slag shard over white-hot core."""
    img = blank()
    rows = {2: (6, 9), 3: (5, 11), 4: (4, 12), 5: (3, 12), 6: (3, 13), 7: (2, 13),
            8: (2, 13), 9: (3, 13), 10: (3, 12), 11: (4, 11), 12: (5, 10), 13: (6, 9)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            d = math.hypot(x - 7.5, y - 7.5)
            if x in (x0, x1) or y in (2, 13):
                color = CHARCOAL_DARK
            elif d < 2.0:
                color = EMBER_YELLOW
            elif d < 3.4:
                color = EMBER_HOT if (x + y) % 2 else EMBER_BRIGHT
            elif (x * 5 + y * 7) % 9 == 0:
                color = CHARCOAL  # crusted slag flecks
            else:
                color = EMBER
            px(img, x, y, color)
    # White-hot heart + radiating glow.
    px(img, 7, 7, (0xFF, 0xF4, 0xC9))
    px(img, 8, 8, (0xFF, 0xF4, 0xC9))
    for x, y in [(7, 0), (15, 7), (0, 8), (8, 15)]:
        px(img, x, y, EMBER, 90)
    return img


def tex_inferno_crown(rng: Random) -> Image.Image:
    """The Titan's trophy crown: gold band, flame-tipped points, ember jewels."""
    img = blank()
    # Band.
    for y in (10, 11, 12):
        for x in range(2, 14):
            if y == 12 or x in (2, 13):
                color = GOLD_DARK
            elif y == 10:
                color = GOLD_LIGHT
            else:
                color = GOLD
            px(img, x, y, color)
    # Three points with flame tips.
    for cx in (3, 7, 12):
        px(img, cx, 9, GOLD)
        px(img, cx, 8, GOLD)
        px(img, cx, 7, GOLD_LIGHT)
        px(img, cx, 6, EMBER_BRIGHT)
        px(img, cx, 5, EMBER_YELLOW)
    for cx in (5, 9):
        px(img, cx, 9, GOLD_DARK)
        px(img, cx, 8, GOLD)
    # Ember jewels set in the band.
    px(img, 5, 11, EMBER)
    px(img, 8, 11, EMBER_BRIGHT)
    px(img, 11, 11, EMBER)
    return img


def tex_the_oxidizer_spawn_egg(rng: Random) -> Image.Image:
    return spawn_egg(rng, OXIDE, OXIDE_DARK, OXIDE_LIGHT, COPPER_DARK, OXIDE_SHADOW)


def tex_inferno_titan_spawn_egg(rng: Random) -> Image.Image:
    return spawn_egg(rng, CHARCOAL, CHARCOAL_DARK, CHARCOAL_LIGHT, EMBER_BRIGHT, (0x18, 0x10, 0x13))


TEXTURES = {
    "oxidizer_core": tex_oxidizer_core,
    "titan_sigil": tex_titan_sigil,
    "oxidizer_heart": tex_oxidizer_heart,
    "verdigris_scale": tex_verdigris_scale,
    "titan_ember": tex_titan_ember,
    "inferno_crown": tex_inferno_crown,
    "the_oxidizer_spawn_egg": tex_the_oxidizer_spawn_egg,
    "inferno_titan_spawn_egg": tex_inferno_titan_spawn_egg,
}


# ---------------------------------------------------------------------------
# Item model-definitions + models + textures
# ---------------------------------------------------------------------------

def emit_item_assets() -> None:
    for item_id in ITEM_IDS:
        write_json(ASSETS / "items" / f"{item_id}.json", {
            "model": {"type": "minecraft:model", "model": f"{NS}:item/{item_id}"},
        })
        write_json(ASSETS / "models" / "item" / f"{item_id}.json", {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"{NS}:item/{item_id}"},
        })


def emit_textures() -> None:
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in TEXTURES.items():
        # Per-texture fixed seed keeps output byte-identical across runs.
        img = fn(Random(f"copper_inferno:infernoboss:{name}"))
        img.save(tex_dir / f"{name}.png")


# ---------------------------------------------------------------------------
# Entity loot tables (dr_pepper_golem schema incl. "random_sequence")
# ---------------------------------------------------------------------------

def emit_loot_tables() -> None:
    for boss_id, drops in BOSSES.items():
        write_json(DATA / "loot_table" / "entities" / f"{boss_id}.json", {
            "type": "minecraft:entity",
            "pools": [
                {
                    "bonus_rolls": 0.0,
                    "entries": [
                        {
                            "type": "minecraft:item",
                            "functions": [
                                {
                                    "add": False,
                                    "count": {
                                        "type": "minecraft:uniform",
                                        "max": cmax,
                                        "min": cmin,
                                    },
                                    "function": "minecraft:set_count",
                                }
                            ],
                            "name": drop_id,
                        }
                    ],
                    "rolls": 1.0,
                }
                for drop_id, cmin, cmax in drops
            ],
            "random_sequence": f"{NS}:entities/{boss_id}",
        })


# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 formats; collision-checked via check_recipe_collisions.py)
# ---------------------------------------------------------------------------

def emit_recipes() -> None:
    m = lambda p: f"{NS}:{p}"
    rdir = DATA / "recipe" / "infernoboss"

    # 4 copper ingots + 4 oxidized copper dust (materials feature, v2) ringing an
    # inferno core block (inferno feature, v2). Unique input set: no vanilla or mod
    # recipe rings an inferno core.
    write_json(rdir / "oxidizer_core.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {
            "I": "minecraft:copper_ingot",
            "D": m("oxidized_copper_dust"),
            "C": m("inferno_core"),
        },
        "pattern": ["IDI", "DCD", "IDI"],
        "result": {"count": 1, "id": m("oxidizer_core")},
    })
    # 4 blaze rods + 4 inferno powder (materials feature, v2) ringing a magma block.
    # Unique input set: no vanilla or mod recipe rings a magma block with blaze rods.
    write_json(rdir / "titan_sigil.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {
            "B": "minecraft:blaze_rod",
            "P": m("inferno_powder"),
            "M": "minecraft:magma_block",
        },
        "pattern": ["BPB", "PMP", "BPB"],
        "result": {"count": 1, "id": m("titan_sigil")},
    })


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_item_assets()
    emit_textures()
    emit_loot_tables()
    emit_recipes()
    write_json(ASSETS / "lang" / "fragments" / "infernoboss.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "infernoboss.json", LANG_DE)
    mode = "textures + JSON" if WRITE_JSON else "textures only (pass --write-json for legacy JSON)"
    print(f"infernoboss_gen: assets for {len(BOSSES)} bosses / {len(ITEM_IDS)} items generated ({mode}).")


if __name__ == "__main__":
    main()
