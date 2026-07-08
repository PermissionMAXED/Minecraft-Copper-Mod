#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "infernofx" feature (2 status effects, 2 particles,
4 gadget items).

Idempotent: running it any number of times produces the same files. By default this script
writes ONLY PNGs (4 item textures + 2 particle textures + the 2 status-effect HUD icons
under textures/mob_effect/); pass --write-json to also (re)emit the JSON scaffolding:
  - item model-definitions + item models (assets/copper_inferno/items + models/item)
  - particle definitions (assets/copper_inferno/particles/{ember_spark,ash_fall}.json)
  - recipes (data/copper_inferno/recipe/infernofx/*.json)
  - lang fragments EN + DE (assets/copper_inferno/lang/fragments{,_de}/infernofx.json),
    including the effect.copper_inferno.* keys for the two status effects
"""

import json
import sys
from pathlib import Path
from random import Random

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> PNGs only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
NS = "copper_inferno"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# Palette (shared inferno look: copper + ember + ash).
K = (0x1C, 0x12, 0x16)        # outline / near-black
D = (0x2B, 0x22, 0x26)        # very dark charcoal
C = (0x3D, 0x2C, 0x2E)        # charcoal
P = (0xE0, 0x73, 0x4D)        # copper base
p = (0xC1, 0x5A, 0x3B)        # copper dark
E = (0xE2, 0x58, 0x22)        # ember
e = (0xFF, 0x7A, 0x2F)        # ember bright
H = (0xFF, 0xB1, 0x6B)        # ember hot
W = (0xFF, 0xE0, 0xC0)        # near-white hot
G = (0x9A, 0x8F, 0x8A)        # ash light
g = (0x6E, 0x65, 0x60)        # ash dark
Au = (0xF3, 0xC1, 0x4B)       # gold clasp
S = (0xD8, 0xD2, 0xC8)        # string / bone white
V = (0x6F, 0xB0, 0x8E)        # verdigris light
v = (0x57, 0xA0, 0x7B)        # verdigris
u = (0x4E, 0x9E, 0x7A)        # verdigris deep

ITEMS = ["heat_ward_charm", "slag_bomb", "cinder_compass", "ash_talisman"]
PARTICLES = ["ember_spark", "ash_fall"]

LANG_EN = {
    "effect.copper_inferno.heat_ward": "Heat Ward",
    "effect.copper_inferno.oxidized": "Oxidized",
    "item.copper_inferno.heat_ward_charm": "Heat Ward Charm",
    "item.copper_inferno.slag_bomb": "Slag Bomb",
    "item.copper_inferno.cinder_compass": "Cinder Compass",
    "item.copper_inferno.cinder_compass.tooltip.flavor": "The needle strains toward the nearest warmth.",
    "item.copper_inferno.cinder_compass.tooltip.note": "Pure flavor - it does not actually track anything.",
    "item.copper_inferno.ash_talisman": "Ash Talisman",
}
LANG_DE = {
    "effect.copper_inferno.heat_ward": "Hitzeschutz",
    "effect.copper_inferno.oxidized": "Oxidiert",
    "item.copper_inferno.heat_ward_charm": "Hitzeschutz-Amulett",
    "item.copper_inferno.slag_bomb": "Schlackenbombe",
    "item.copper_inferno.cinder_compass": "Zunderkompass",
    "item.copper_inferno.cinder_compass.tooltip.flavor": "Die Nadel strebt zur n\u00e4chsten W\u00e4rmequelle.",
    "item.copper_inferno.cinder_compass.tooltip.note": "Reine Zierde - er verfolgt in Wirklichkeit nichts.",
    "item.copper_inferno.ash_talisman": "Asche-Talisman",
}


def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True) + "\n", encoding="utf-8")


# ---------------------------------------------------------------------------
# JSON emitters
# ---------------------------------------------------------------------------

def emit_item_assets() -> None:
    for name in ITEMS:
        write_json(ASSETS / "models" / "item" / f"{name}.json",
                   {"parent": "minecraft:item/generated", "textures": {"layer0": f"{NS}:item/{name}"}})
        write_json(ASSETS / "items" / f"{name}.json",
                   {"model": {"type": "minecraft:model", "model": f"{NS}:item/{name}"}})


def emit_particle_defs() -> None:
    for name in PARTICLES:
        write_json(ASSETS / "particles" / f"{name}.json", {"textures": [f"{NS}:{name}"]})


def rp(name: str) -> Path:
    return DATA / "recipe" / "infernofx" / f"{name}.json"


def emit_recipes() -> None:
    # Copper ring + magma cream core. No vanilla/mod recipe shares this canonical grid
    # (verified with devtools/check_recipe_collisions.py).
    write_json(rp("heat_ward_charm"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"C": "minecraft:copper_ingot", "M": "minecraft:magma_cream"},
        "pattern": [" C ", "CMC", " C "],
        "result": {"count": 1, "id": f"{NS}:heat_ward_charm"},
    })
    write_json(rp("slag_bomb"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"S": f"{NS}:slag_chunk", "G": "minecraft:gunpowder"},
        "pattern": ["S", "G"],
        "result": {"count": 2, "id": f"{NS}:slag_bomb"},
    })
    # Same ring as the charm but with an ember-dust center -> distinct input set.
    write_json(rp("cinder_compass"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"C": "minecraft:copper_ingot", "E": f"{NS}:ember_dust"},
        "pattern": [" C ", "CEC", " C "],
        "result": {"count": 1, "id": f"{NS}:cinder_compass"},
    })
    write_json(rp("ash_talisman"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"T": "minecraft:string", "A": f"{NS}:ash_pile", "G": "minecraft:gold_nugget"},
        "pattern": [" T ", "AGA", " A "],
        "result": {"count": 1, "id": f"{NS}:ash_talisman"},
    })


def emit_lang() -> None:
    write_json(ASSETS / "lang" / "fragments" / "infernofx.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "infernofx.json", LANG_DE)


# ---------------------------------------------------------------------------
# Item textures (16x16, deterministic pixel art)
# ---------------------------------------------------------------------------

def blank(size: int = 16) -> Image.Image:
    return Image.new("RGBA", (size, size), (0, 0, 0, 0))


def put(img, x, y, color):
    if 0 <= x < img.size[0] and 0 <= y < img.size[1]:
        img.putpixel((x, y), (*color, 255))


def fill_rect(img, x0, y0, x1, y1, color):
    for y in range(y0, y1 + 1):
        for x in range(x0, x1 + 1):
            put(img, x, y, color)


def ring(img, cx, cy, r2_min, r2_max, color):
    for y in range(img.size[1]):
        for x in range(img.size[0]):
            d2 = (x - cx) ** 2 + (y - cy) ** 2
            if r2_min <= d2 <= r2_max:
                put(img, x, y, color)


def outline(img, color=K):
    """MC-style sprite outline drawn OUTSIDE the shape (same helper as the other gens)."""
    src = img.copy()
    for y in range(img.size[1]):
        for x in range(img.size[0]):
            if src.getpixel((x, y))[3] != 0:
                continue
            for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)):
                nx, ny = x + dx, y + dy
                if 0 <= nx < img.size[0] and 0 <= ny < img.size[1] and src.getpixel((nx, ny))[3] != 0:
                    put(img, x, y, color)
                    break
    return img


def tex_heat_ward_charm(rng: Random) -> Image.Image:
    img = blank()
    # hanging loop + cord
    put(img, 7, 1, p)
    put(img, 8, 1, p)
    put(img, 7, 2, P)
    put(img, 8, 2, P)
    # copper ring pendant
    ring(img, 7.5, 8.5, 10, 20, P)
    ring(img, 7.5, 8.5, 15, 20, p)  # darker outer rim
    # magma-cream core
    for x, y in [(7, 8), (8, 8), (7, 9), (8, 9)]:
        put(img, x, y, E)
    put(img, 7, 8, H)
    put(img, 8, 9, e)
    # sparkle
    put(img, 4, 5, W)
    return outline(img)


def tex_slag_bomb(rng: Random) -> Image.Image:
    img = blank()
    # round dark slag lump
    rows = {5: (6, 10), 6: (5, 11), 7: (4, 12), 8: (4, 12), 9: (4, 12), 10: (5, 11),
            11: (5, 11), 12: (6, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            put(img, x, y, D if rng.random() < 0.7 else C)
    # ember cracks
    for x, y in [(7, 7), (8, 7), (6, 9), (9, 10), (8, 11), (10, 8), (5, 8)]:
        put(img, x, y, E)
    for x, y in [(7, 8), (9, 9)]:
        put(img, x, y, e)
    put(img, 8, 8, H)
    # short sparking fuse on top
    put(img, 9, 4, C)
    put(img, 10, 3, C)
    put(img, 11, 2, e)
    put(img, 12, 1, H)
    return outline(img)


def tex_cinder_compass(rng: Random) -> Image.Image:
    img = blank()
    # copper housing (outer disc) with ash-gray face
    ring(img, 7.5, 7.5, 0, 42, P)
    ring(img, 7.5, 7.5, 30, 42, p)
    ring(img, 7.5, 7.5, 0, 20, g)
    ring(img, 7.5, 7.5, 0, 9, G)
    # cardinal ticks
    for x, y in [(7, 2), (8, 2), (7, 13), (8, 13), (2, 7), (2, 8), (13, 7), (13, 8)]:
        put(img, x, y, D)
    # ember needle pointing north-east
    for i in range(4):
        put(img, 8 + i // 2, 7 - i, E if i < 3 else H)
    for i in range(3):
        put(img, 7 - i // 2, 8 + i, C)  # dark tail
    put(img, 7, 7, e)  # pivot
    put(img, 8, 8, e)
    return outline(img)


def tex_ash_talisman(rng: Random) -> Image.Image:
    img = blank()
    # string loop
    for x, y in [(6, 1), (7, 1), (8, 1), (9, 1), (5, 2), (10, 2), (6, 3), (9, 3)]:
        put(img, x, y, S)
    # gold clasp
    fill_rect(img, 6, 4, 9, 5, Au)
    put(img, 6, 4, W)
    # pressed-ash teardrop body
    rows = {6: (6, 9), 7: (5, 10), 8: (4, 11), 9: (4, 11), 10: (4, 11), 11: (5, 10),
            12: (5, 10), 13: (6, 9)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            put(img, x, y, G if rng.random() < 0.6 else g)
    # dying embers pressed into the ash
    put(img, 7, 9, E)
    put(img, 9, 11, E)
    put(img, 6, 12, e)
    return outline(img)


ITEM_TEXTURES = {
    "heat_ward_charm": tex_heat_ward_charm,
    "slag_bomb": tex_slag_bomb,
    "cinder_compass": tex_cinder_compass,
    "ash_talisman": tex_ash_talisman,
}


# ---------------------------------------------------------------------------
# Particle textures (8x8, matching copper_sparkle's frame size)
# ---------------------------------------------------------------------------

def tex_ember_spark(rng: Random) -> Image.Image:
    img = blank(8)
    # 4-point spark: hot white core, ember arms
    put(img, 3, 3, W)
    put(img, 4, 3, H)
    put(img, 3, 4, H)
    put(img, 4, 4, W)
    for x, y in [(3, 1), (4, 2), (1, 3), (2, 4), (6, 3), (5, 4), (3, 6), (4, 5)]:
        put(img, x, y, e)
    for x, y in [(3, 0), (0, 4), (7, 3), (4, 7)]:
        put(img, x, y, E)
    return img


def tex_ash_fall(rng: Random) -> Image.Image:
    img = blank(8)
    # irregular gray flake with a darker edge
    for x, y in [(3, 2), (4, 2), (2, 3), (3, 3), (4, 3), (5, 3),
                 (2, 4), (3, 4), (4, 4), (5, 4), (3, 5), (4, 5)]:
        put(img, x, y, G)
    for x, y in [(2, 3), (5, 4), (3, 5)]:
        put(img, x, y, g)
    put(img, 4, 3, S)  # pale highlight
    put(img, 3, 4, g)
    return img


PARTICLE_TEXTURES = {
    "ember_spark": tex_ember_spark,
    "ash_fall": tex_ash_fall,
}


# ---------------------------------------------------------------------------
# Status-effect HUD icons (18x18, same size/format as mob_effect/dr_pepper_kick.png)
# ---------------------------------------------------------------------------

def tex_effect_heat_ward(rng: Random) -> Image.Image:
    """Ember-orange shield with a flame burning at its heart."""
    img = blank(18)
    rows = {2: (5, 12), 3: (4, 13), 4: (4, 13), 5: (4, 13), 6: (4, 13), 7: (4, 13),
            8: (4, 13), 9: (5, 12), 10: (5, 12), 11: (6, 11), 12: (7, 10), 13: (7, 10),
            14: (8, 9)}
    # dark charcoal shield face with a copper rim
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            put(img, x, y, D if rng.random() < 0.7 else C)
    for y, (x0, x1) in rows.items():
        put(img, x0, y, P)
        put(img, x1, y, p)
    for x in range(rows[2][0], rows[2][1] + 1):
        put(img, x, 2, P)
    # central flame: ember base rising to a hot white core
    put(img, 8, 4, e)
    put(img, 8, 5, e)
    put(img, 9, 5, E)
    for x, y, c in [(7, 6, E), (8, 6, e), (9, 6, e),
                    (7, 7, e), (8, 7, H), (9, 7, e), (10, 7, E),
                    (6, 8, E), (7, 8, H), (8, 8, W), (9, 8, H),
                    (7, 9, H), (8, 9, W), (9, 9, H), (10, 9, E),
                    (7, 10, e), (8, 10, H), (9, 10, e),
                    (8, 11, E), (9, 11, E), (8, 12, E)]:
        put(img, x, y, c)
    # rim glint
    put(img, 5, 3, H)
    return outline(img)


def tex_effect_oxidized(rng: Random) -> Image.Image:
    """Copper lump being eaten by a verdigris-green corrosion crust."""
    img = blank(18)
    rows = {3: (6, 11), 4: (5, 12), 5: (4, 13), 6: (4, 13), 7: (4, 13), 8: (4, 13),
            9: (4, 13), 10: (4, 13), 11: (5, 12), 12: (6, 11), 13: (7, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            # crust creeps in from the top-left; a raw-copper wedge survives bottom-right
            if x + y + (1 if rng.random() < 0.5 else 0) >= 21:
                put(img, x, y, P if rng.random() < 0.6 else p)
            elif rng.random() < 0.15:
                put(img, x, y, u)
            elif rng.random() < 0.4:
                put(img, x, y, V)
            else:
                put(img, x, y, v)
    # corrosion pox rings on the crust
    for cx, cy in [(7, 5), (10, 8), (6, 10)]:
        put(img, cx, cy, V)
        for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)):
            put(img, cx + dx, cy + dy, u)
    # verdigris drips oozing off the underside
    put(img, 6, 13, v)
    put(img, 6, 14, u)
    put(img, 8, 14, v)
    put(img, 8, 15, u)
    put(img, 11, 13, v)
    # pale mineral glint top-left
    put(img, 6, 4, S)
    return outline(img)


EFFECT_TEXTURES = {
    "heat_ward": tex_effect_heat_ward,
    "oxidized": tex_effect_oxidized,
}


def emit_textures() -> None:
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in ITEM_TEXTURES.items():
        fn(Random(f"{NS}:{name}")).save(item_dir / f"{name}.png")
    particle_dir = ASSETS / "textures" / "particle"
    particle_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in PARTICLE_TEXTURES.items():
        fn(Random(f"{NS}:{name}")).save(particle_dir / f"{name}.png")
    effect_dir = ASSETS / "textures" / "mob_effect"
    effect_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in EFFECT_TEXTURES.items():
        fn(Random(f"{NS}:{name}")).save(effect_dir / f"{name}.png")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_item_assets()
    emit_particle_defs()
    emit_recipes()
    emit_lang()
    emit_textures()
    mode = "textures + JSON" if WRITE_JSON else "textures only (pass --write-json for JSON)"
    print(f"infernofx_gen: assets generated ({mode}).")


if __name__ == "__main__":
    main()
