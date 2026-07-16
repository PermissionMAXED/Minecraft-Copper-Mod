#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "infernomobs" feature (5 mobs, 9 items).

Idempotent: running it any number of times produces byte-identical files. Emits, directly
into src/main/resources:
  - 16x16 RGBA item textures assets/copper_inferno/textures/item/<id>.png   (Pillow, seeded)
and, with --write-json (legacy scaffolding; the JSON in src/main/resources is authoritative):
  - item model-definitions   assets/copper_inferno/items/<id>.json          (dr_pepper_golem format)
  - item models              assets/copper_inferno/models/item/<id>.json    (item/generated, layer0)
  - entity loot tables       data/copper_inferno/loot_table/entities/<mob>.json
                             (dr_pepper_golem schema incl. "random_sequence")
  - recipes                  data/copper_inferno/recipe/infernomobs/*.json
  - lang fragments           assets/copper_inferno/lang/fragments/infernomobs.json (EN)
                             assets/copper_inferno/lang/fragments_de/infernomobs.json (DE)

Items: 5 spawn eggs (ember_wraith/slag_crawler/molten_slagling/cinder_strider/ash_bat
_spawn_egg) + 4 mob drops (wraith_ember, crawler_fang, slagling_core, strider_shell).
Recipes reference ONLY vanilla ids, this feature's own ids and materials' copper_dust /
inferno_powder (registered by MaterialsFeature).
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

# mob id -> (drop item id in loot table, min count, max count)
MOBS = {
    "ember_wraith": (f"{NS}:wraith_ember", 0.0, 2.0),
    "slag_crawler": (f"{NS}:crawler_fang", 0.0, 2.0),
    "molten_slagling": (f"{NS}:slagling_core", 0.0, 1.0),
    "cinder_strider": (f"{NS}:strider_shell", 1.0, 2.0),
    "ash_bat": ("minecraft:leather", 0.0, 1.0),
}

ITEM_IDS = [
    "ember_wraith_spawn_egg",
    "slag_crawler_spawn_egg",
    "molten_slagling_spawn_egg",
    "cinder_strider_spawn_egg",
    "ash_bat_spawn_egg",
    "wraith_ember",
    "crawler_fang",
    "slagling_core",
    "strider_shell",
]

LANG_EN = {
    "entity.copper_inferno.ember_wraith": "Ember Wraith",
    "entity.copper_inferno.slag_crawler": "Slag Crawler",
    "entity.copper_inferno.molten_slagling": "Molten Slagling",
    "entity.copper_inferno.cinder_strider": "Cinder Strider",
    "entity.copper_inferno.ash_bat": "Ash Bat",
    "item.copper_inferno.ember_wraith_spawn_egg": "Ember Wraith Spawn Egg",
    "item.copper_inferno.slag_crawler_spawn_egg": "Slag Crawler Spawn Egg",
    "item.copper_inferno.molten_slagling_spawn_egg": "Molten Slagling Spawn Egg",
    "item.copper_inferno.cinder_strider_spawn_egg": "Cinder Strider Spawn Egg",
    "item.copper_inferno.ash_bat_spawn_egg": "Ash Bat Spawn Egg",
    "item.copper_inferno.wraith_ember": "Wraith Ember",
    "item.copper_inferno.crawler_fang": "Crawler Fang",
    "item.copper_inferno.slagling_core": "Slagling Core",
    "item.copper_inferno.strider_shell": "Strider Shell",
}

LANG_DE = {
    "entity.copper_inferno.ember_wraith": "Glutschleier",
    "entity.copper_inferno.slag_crawler": "Schlackenkriecher",
    "entity.copper_inferno.molten_slagling": "Schmelzschlackling",
    "entity.copper_inferno.cinder_strider": "Zunderschreiter",
    "entity.copper_inferno.ash_bat": "Aschenfledermaus",
    "item.copper_inferno.ember_wraith_spawn_egg": "Glutschleier-Spawn-Ei",
    "item.copper_inferno.slag_crawler_spawn_egg": "Schlackenkriecher-Spawn-Ei",
    "item.copper_inferno.molten_slagling_spawn_egg": "Schmelzschlackling-Spawn-Ei",
    "item.copper_inferno.cinder_strider_spawn_egg": "Zunderschreiter-Spawn-Ei",
    "item.copper_inferno.ash_bat_spawn_egg": "Aschenfledermaus-Spawn-Ei",
    "item.copper_inferno.wraith_ember": "Schleierglut",
    "item.copper_inferno.crawler_fang": "Kriecherzahn",
    "item.copper_inferno.slagling_core": "Schlackling-Kern",
    "item.copper_inferno.strider_shell": "Schreiterpanzer",
}

# ---------------------------------------------------------------------------
# Palettes
# ---------------------------------------------------------------------------

EMBER = (0xE2, 0x58, 0x22)
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)
EMBER_HOT = (0xFF, 0xB1, 0x6B)
EMBER_YELLOW = (0xFF, 0xD8, 0x66)

CHARCOAL_LIGHT = (0x4A, 0x36, 0x3A)
CHARCOAL = (0x3D, 0x2C, 0x2E)
CHARCOAL_DARK = (0x2B, 0x22, 0x26)

ASH_LIGHT = (0xB9, 0xB4, 0xAE)
ASH = (0x8F, 0x8A, 0x84)
ASH_DARK = (0x5F, 0x5B, 0x57)

BLAZE_GOLD = (0xF6, 0xB2, 0x01)
BLAZE_GOLD_DARK = (0xC6, 0x8A, 0x00)

STRIDER_RED = (0x9A, 0x34, 0x34)
STRIDER_RED_DARK = (0x6B, 0x22, 0x26)
STRIDER_RED_LIGHT = (0xB9, 0x50, 0x48)

FANG_WHITE = (0xF2, 0xEE, 0xE4)
FANG_SHADE = (0xC9, 0xC2, 0xB2)
FANG_DARK = (0x8E, 0x86, 0x74)


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
    # Egg silhouette: per-row half-widths around center x=7.5, rows y=2..14.
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
    # Seeded speckles (interior only), matching the vanilla two-tone egg look.
    interior = [(x, y) for x, y, edge in cells if not edge]
    for x, y in rng.sample(interior, 12):
        px(img, x, y, spots)
    return img


# ---------------------------------------------------------------------------
# Texture painters
# ---------------------------------------------------------------------------

def tex_ember_wraith_spawn_egg(rng: Random) -> Image.Image:
    return spawn_egg(rng, BLAZE_GOLD, BLAZE_GOLD_DARK, EMBER_YELLOW, CHARCOAL, CHARCOAL_DARK)


def tex_slag_crawler_spawn_egg(rng: Random) -> Image.Image:
    return spawn_egg(rng, ASH_DARK, CHARCOAL, ASH, EMBER_BRIGHT, CHARCOAL_DARK)


def tex_molten_slagling_spawn_egg(rng: Random) -> Image.Image:
    return spawn_egg(rng, CHARCOAL, CHARCOAL_DARK, CHARCOAL_LIGHT, EMBER_YELLOW, (0x18, 0x10, 0x13))


def tex_cinder_strider_spawn_egg(rng: Random) -> Image.Image:
    return spawn_egg(rng, STRIDER_RED, STRIDER_RED_DARK, STRIDER_RED_LIGHT, ASH, CHARCOAL_DARK)


def tex_ash_bat_spawn_egg(rng: Random) -> Image.Image:
    return spawn_egg(rng, ASH, ASH_DARK, ASH_LIGHT, CHARCOAL, CHARCOAL_DARK)


def tex_wraith_ember(rng: Random) -> Image.Image:
    """Jagged glowing coal: hot yellow heart, orange body, charred tips, soft halo."""
    img = blank()
    rows = {3: (7, 9), 4: (6, 10), 5: (5, 11), 6: (4, 11), 7: (4, 12), 8: (3, 12),
            9: (4, 12), 10: (4, 11), 11: (5, 11), 12: (6, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            d = math.hypot(x - 7.5, y - 7.5)
            if x in (x0, x1) or y in (3, 12):
                color = CHARCOAL
            elif d < 2.2:
                color = EMBER_YELLOW
            elif d < 3.6:
                color = EMBER_BRIGHT if (x + y) % 3 else EMBER_HOT
            else:
                color = EMBER
            px(img, x, y, color)
    # Charred flecks + glow halo.
    for x, y in [(5, 5), (10, 10), (9, 4), (5, 11)]:
        if rng.random() < 0.9:
            px(img, x, y, CHARCOAL_DARK)
    for x, y in [(7, 2), (12, 7), (3, 9), (8, 13)]:
        px(img, x, y, EMBER, 100)
    return img


def tex_crawler_fang(rng: Random) -> Image.Image:
    """Curved fang: broad root top-left tapering to a point bottom-right."""
    img = blank()
    # Root block.
    for y in (2, 3, 4):
        for x in range(3, 9):
            color = FANG_DARK if y == 2 or x == 3 else FANG_SHADE
            px(img, x, y, color)
    # Tapering curved body (width shrinks as it sweeps down-right).
    body = {5: (4, 9), 6: (5, 9), 7: (6, 10), 8: (7, 10), 9: (8, 11), 10: (9, 11), 11: (10, 12), 12: (11, 12)}
    for y, (x0, x1) in body.items():
        for x in range(x0, x1 + 1):
            if x == x0:
                color = FANG_WHITE  # lit inner curve
            elif x == x1:
                color = FANG_DARK
            else:
                color = FANG_SHADE
            px(img, x, y, color)
    px(img, 12, 13, FANG_DARK)  # tip
    px(img, 5, 3, FANG_WHITE)   # root highlight
    return img


def tex_slagling_core(rng: Random) -> Image.Image:
    """Round molten core: charred crust cracked open over glowing magma."""
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - cx, y - cy)
            if d <= 5.6:
                if d > 4.7:
                    color = CHARCOAL_DARK
                elif d > 3.4:
                    color = CHARCOAL if (x * 3 + y * 5) % 7 else CHARCOAL_LIGHT
                else:
                    color = EMBER_BRIGHT if (x + y) % 2 else EMBER
                px(img, x, y, color)
    # Glowing cracks radiating through the crust.
    for x, y in [(4, 6), (5, 5), (10, 5), (11, 6), (4, 10), (11, 10), (7, 3), (8, 12)]:
        px(img, x, y, EMBER_HOT)
    px(img, 7, 7, EMBER_YELLOW)
    px(img, 8, 8, EMBER_YELLOW)
    return img


def tex_strider_shell(rng: Random) -> Image.Image:
    """Domed carapace segment: banded strider-red shell with an ashen rim."""
    img = blank()
    # Dome rows y=4..12, widening downward.
    half = {4: 2.4, 5: 3.4, 6: 4.1, 7: 4.6, 8: 5.0, 9: 5.2, 10: 5.4, 11: 5.4}
    for y, h in half.items():
        x0 = int(math.ceil(7.5 - h))
        x1 = int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y == 4:
                color = STRIDER_RED_DARK
            elif y in (6, 9):
                color = STRIDER_RED_DARK  # growth bands
            elif x <= 5 and y <= 8:
                color = STRIDER_RED_LIGHT
            else:
                color = STRIDER_RED
            px(img, x, y, color)
    # Ashen bottom rim.
    for x in range(2, 14):
        px(img, x, 12, ASH)
        px(img, x, 13, ASH_DARK)
    px(img, 5, 5, STRIDER_RED_LIGHT)
    return img


TEXTURES = {
    "ember_wraith_spawn_egg": tex_ember_wraith_spawn_egg,
    "slag_crawler_spawn_egg": tex_slag_crawler_spawn_egg,
    "molten_slagling_spawn_egg": tex_molten_slagling_spawn_egg,
    "cinder_strider_spawn_egg": tex_cinder_strider_spawn_egg,
    "ash_bat_spawn_egg": tex_ash_bat_spawn_egg,
    "wraith_ember": tex_wraith_ember,
    "crawler_fang": tex_crawler_fang,
    "slagling_core": tex_slagling_core,
    "strider_shell": tex_strider_shell,
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
        img = fn(Random(f"copper_inferno:infernomobs:{name}"))
        img.save(tex_dir / f"{name}.png")


# ---------------------------------------------------------------------------
# Entity loot tables (dr_pepper_golem schema incl. "random_sequence")
# ---------------------------------------------------------------------------

def emit_loot_tables() -> None:
    for mob_id, (drop_id, cmin, cmax) in MOBS.items():
        write_json(DATA / "loot_table" / "entities" / f"{mob_id}.json", {
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
            ],
            "random_sequence": f"{NS}:entities/{mob_id}",
        })


# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 formats; collision-checked via check_recipe_collisions.py)
# ---------------------------------------------------------------------------

def emit_recipes() -> None:
    m = lambda p: f"{NS}:{p}"
    rdir = DATA / "recipe" / "infernomobs"

    # Wraith Ember ground with Copper Dust -> 2 Inferno Powder (both materials-feature ids
    # verified registered; input set distinct from materials' inferno_powder recipe).
    write_json(rdir / "inferno_powder_from_wraith_ember.json", {
        "type": "minecraft:crafting_shapeless",
        "category": "misc",
        "ingredients": [m("wraith_ember"), m("copper_dust")],
        "result": {"count": 2, "id": m("inferno_powder")},
    })
    # Crawler Fang arrowhead (vanilla arrow uses flint on top -> distinct input set).
    write_json(rdir / "arrow_from_crawler_fang.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {"F": m("crawler_fang"), "S": "minecraft:stick", "E": "minecraft:feather"},
        "pattern": ["F", "S", "E"],
        "result": {"count": 4, "id": "minecraft:arrow"},
    })
    # Slagling Core kneaded into 2 Magma Cream (vanilla magma_cream is blaze powder +
    # slime ball -> distinct input set).
    write_json(rdir / "magma_cream_from_slagling_core.json", {
        "type": "minecraft:crafting_shapeless",
        "category": "misc",
        "ingredients": [m("slagling_core")],
        "result": {"count": 2, "id": "minecraft:magma_cream"},
    })
    # Two Strider Shells soften into 2 leather (vanilla leather is 4 rabbit hide -> distinct).
    write_json(rdir / "leather_from_strider_shell.json", {
        "type": "minecraft:crafting_shapeless",
        "category": "misc",
        "ingredients": [m("strider_shell"), m("strider_shell")],
        "result": {"count": 2, "id": "minecraft:leather"},
    })


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_item_assets()
    emit_textures()
    emit_loot_tables()
    emit_recipes()
    write_json(ASSETS / "lang" / "fragments" / "infernomobs.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "infernomobs.json", LANG_DE)
    mode = "textures + JSON" if WRITE_JSON else "textures only (pass --write-json for legacy JSON)"
    print(f"infernomobs_gen: assets for {len(MOBS)} mobs / {len(ITEM_IDS)} items generated ({mode}).")


if __name__ == "__main__":
    main()
