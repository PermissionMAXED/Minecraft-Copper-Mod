#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "materials" feature (18 crafting-material items).

Idempotent: running it any number of times produces byte-identical files. Emits, directly
into src/main/resources:
  - item model-definitions   assets/copper_inferno/items/<id>.json          (copper_coin format)
  - item models              assets/copper_inferno/models/item/<id>.json    (item/generated, layer0)
  - 16x16 RGBA item textures assets/copper_inferno/textures/item/<id>.png   (Pillow, seeded)
  - recipes                  data/copper_inferno/recipe/materials/*.json
  - lang fragment            assets/copper_inferno/lang/fragments/materials.json

Recipe JSON structures are exact copies of the vanilla 1.21.9 formats extracted from
fabric-loom's minecraft-client.jar data/minecraft/recipe/ (crafting_shaped: bucket/compass,
crafting_shapeless: fermented_spider_eye/blaze_powder, smelting:
copper_ingot_from_smelting_raw_copper). Recipes reference ONLY vanilla ids and this
feature's own ids.
"""

import json
import math
from pathlib import Path
from random import Random

from PIL import Image

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
NS = "copper_inferno"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

ITEM_IDS = [
    "copper_dust",
    "oxidized_copper_dust",
    "copper_rod",
    "copper_gear",
    "copper_sheet",
    "copper_wire",
    "copper_spring",
    "copper_mesh",
    "copper_rivet",
    "copper_screw",
    "copper_coil",
    "copper_alloy_ingot",
    "charred_copper_ingot",
    "inferno_shard",
    "inferno_powder",
    "inferno_alloy_ingot",
    "bottle_cap",
    "soda_essence",
]

LANG = {
    "item.copper_inferno.copper_dust": "Copper Dust",
    "item.copper_inferno.oxidized_copper_dust": "Oxidized Copper Dust",
    "item.copper_inferno.copper_rod": "Copper Rod",
    "item.copper_inferno.copper_gear": "Copper Gear",
    "item.copper_inferno.copper_sheet": "Copper Sheet",
    "item.copper_inferno.copper_wire": "Copper Wire",
    "item.copper_inferno.copper_spring": "Copper Spring",
    "item.copper_inferno.copper_mesh": "Copper Mesh",
    "item.copper_inferno.copper_rivet": "Copper Rivet",
    "item.copper_inferno.copper_screw": "Copper Screw",
    "item.copper_inferno.copper_coil": "Copper Coil",
    "item.copper_inferno.copper_alloy_ingot": "Copper Alloy Ingot",
    "item.copper_inferno.charred_copper_ingot": "Charred Copper Ingot",
    "item.copper_inferno.inferno_shard": "Inferno Shard",
    "item.copper_inferno.inferno_powder": "Inferno Powder",
    "item.copper_inferno.inferno_alloy_ingot": "Inferno Alloy Ingot",
    "item.copper_inferno.bottle_cap": "Bottle Cap",
    "item.copper_inferno.soda_essence": "Soda Essence",
}

# ---------------------------------------------------------------------------
# Palettes
# ---------------------------------------------------------------------------

COPPER_HI = (0xFF, 0xB4, 0x98)
COPPER_LIGHT = (0xF0, 0x90, 0x6B)
COPPER = (0xE0, 0x73, 0x4D)
COPPER_DARK = (0xC1, 0x5A, 0x3B)
COPPER_DARKER = (0x8E, 0x40, 0x2A)
COPPER_OUTLINE = (0x62, 0x2B, 0x1D)

OXID_LIGHT = (0x8F, 0xC4, 0xA4)
OXID = (0x6F, 0xB0, 0x8E)
OXID_MID = (0x57, 0xA0, 0x7B)
OXID_DARK = (0x4E, 0x9E, 0x7A)
OXID_DARKER = (0x35, 0x6E, 0x54)

CHARCOAL_LIGHT = (0x4A, 0x36, 0x3A)
CHARCOAL = (0x3D, 0x2C, 0x2E)
CHARCOAL_DARK = (0x2B, 0x22, 0x26)
EMBER = (0xE2, 0x58, 0x22)
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)
EMBER_HOT = (0xFF, 0xB1, 0x6B)

IRON_LIGHT = (0xE8, 0xE8, 0xE8)
IRON = (0xC8, 0xC8, 0xC8)
IRON_DARK = (0x9A, 0x9A, 0x9A)

# Copper+iron blend for the alloy ingot (rose bronze).
ALLOY_HI = (0xF5, 0xC4, 0xA9)
ALLOY_LIGHT = (0xE8, 0xA5, 0x80)
ALLOY = (0xD6, 0x8B, 0x66)
ALLOY_DARK = (0xB0, 0x6E, 0x50)
ALLOY_OUTLINE = (0x70, 0x41, 0x2E)

SODA_LIGHT = (0xA8, 0x3E, 0x45)
SODA = (0x8C, 0x2A, 0x33)
SODA_DARK = (0x6B, 0x1F, 0x26)
SODA_OUTLINE = (0x45, 0x12, 0x18)


# ---------------------------------------------------------------------------
# Generic helpers
# ---------------------------------------------------------------------------

def write_json(path: Path, obj) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True) + "\n", encoding="utf-8")


def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def pile(rng: Random, base_palette, speck_palette) -> Image.Image:
    """Rounded mound of powder with a few loose specks above it."""
    img = blank()
    rows = {13: (2, 13), 12: (3, 12), 11: (3, 12), 10: (4, 11), 9: (5, 10), 8: (6, 9), 7: (7, 8)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            edge = x in (x0, x1) or y == 13
            color = base_palette[-1] if edge else rng.choice(base_palette)
            px(img, x, y, color)
    for x, y in [(4, 6), (10, 5), (6, 5), (12, 8), (8, 4)]:
        if rng.random() < 0.9:
            px(img, x, y, rng.choice(speck_palette))
    return img


def ingot(rng: Random, hi, light, base, dark, outline, speck=None) -> Image.Image:
    """Classic vanilla-style ingot silhouette with a slanted top face."""
    img = blank()
    # Top face (parallelogram sloping down-left).
    for y, (x0, x1) in {4: (5, 12), 5: (4, 12), 6: (3, 12)}.items():
        for x in range(x0, x1 + 1):
            px(img, x, y, hi if y == 4 or x in (x0,) else light)
    # Body.
    for y in range(7, 12):
        for x in range(2, 14):
            color = base
            if y >= 10:
                color = dark
            if x in (2, 13):
                color = dark
            px(img, x, y, color)
    # Outline.
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
    # Optional glowing/metallic specks on the body.
    if speck:
        for x, y in [(5, 8), (9, 9), (11, 8), (7, 10)]:
            if rng.random() < 0.85:
                px(img, x, y, rng.choice(speck))
    return img


# ---------------------------------------------------------------------------
# Texture painters (one per item, recognizable silhouettes)
# ---------------------------------------------------------------------------

def tex_copper_dust(rng: Random) -> Image.Image:
    return pile(rng, [COPPER, COPPER, COPPER_LIGHT, COPPER_DARK, COPPER_DARKER],
                [COPPER, COPPER_DARK])


def tex_oxidized_copper_dust(rng: Random) -> Image.Image:
    return pile(rng, [OXID, OXID_MID, OXID_LIGHT, OXID_DARK, OXID_DARKER],
                [OXID_MID, OXID_DARK])


def tex_copper_rod(rng: Random) -> Image.Image:
    """Diagonal 2px rod, highlight on the upper edge, rounded caps."""
    img = blank()
    for i in range(12):
        x, y = 2 + i, 13 - i
        px(img, x, y, COPPER_DARK)
        px(img, x + 1, y, COPPER)
        px(img, x + 1, y - 1, COPPER_LIGHT)
    px(img, 2, 14, COPPER_DARKER)
    px(img, 14, 1, COPPER_HI)
    px(img, 3, 14, COPPER_DARKER)
    return img


def tex_copper_gear(rng: Random) -> Image.Image:
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
                    color = COPPER_LIGHT
                elif dx + dy > 4:
                    color = COPPER_DARKER
                else:
                    color = COPPER if ring else COPPER_DARK
                px(img, x, y, color)
            elif r < 2.9 and r >= 2.1:
                px(img, x, y, COPPER_OUTLINE)
    return img


def tex_copper_sheet(rng: Random) -> Image.Image:
    """Flat plate with a bright rolled top edge and a diagonal sheen."""
    img = blank()
    for y in range(5, 12):
        for x in range(2, 14):
            if (x, y) in [(2, 5), (13, 5), (2, 11), (13, 11)]:
                continue
            if y == 5:
                color = COPPER_LIGHT
            elif y == 11 or x == 13:
                color = COPPER_DARKER
            elif x == 2:
                color = COPPER_DARK
            else:
                color = COPPER
            px(img, x, y, color)
    for i in range(5):
        px(img, 4 + i * 2, 6 + i, COPPER_HI if i < 2 else COPPER_LIGHT)
    return img


def tex_copper_wire(rng: Random) -> Image.Image:
    """A loose coiled loop of wire with two straight tails."""
    img = blank()
    cx = cy = 8.0
    for deg in range(0, 360, 4):
        a = math.radians(deg)
        x = int(round(cx + 4.2 * math.cos(a)))
        y = int(round(cy + 4.2 * math.sin(a)))
        color = COPPER_LIGHT if deg in range(180, 300) else COPPER
        px(img, x, y, color)
        # Inner second winding, slightly offset.
        x2 = int(round(cx + 2.8 * math.cos(a + 0.35)))
        y2 = int(round(cy + 2.8 * math.sin(a + 0.35)))
        px(img, x2, y2, COPPER_DARK)
    for i in range(3):
        px(img, 12 + i, 4 - i, COPPER)
        px(img, 3 - i if 3 - i >= 0 else 0, 12 + i, COPPER_DARK)
    px(img, 14, 2, COPPER_HI)
    return img


def tex_copper_spring(rng: Random) -> Image.Image:
    """Vertical zigzag spring with flat end caps."""
    img = blank()
    xs = [5, 7, 9, 10, 9, 7, 5, 4, 5, 7, 9, 10]
    for i, y in enumerate(range(2, 14)):
        x = xs[i]
        px(img, x, y, COPPER)
        px(img, x + 1, y, COPPER_DARK)
        if i % 4 == 0:
            px(img, x, y, COPPER_LIGHT)
    for x in range(4, 12):
        px(img, x, 1, COPPER_DARKER)
        px(img, x, 14, COPPER_DARKER)
    return img


def tex_copper_mesh(rng: Random) -> Image.Image:
    """Cross-hatch woven grid."""
    img = blank()
    for y in range(2, 14):
        for x in range(2, 14):
            hline = y % 3 == 2
            vline = x % 3 == 2
            if hline and vline:
                px(img, x, y, COPPER_DARKER)
            elif hline:
                px(img, x, y, COPPER if (x // 3) % 2 == 0 else COPPER_LIGHT)
            elif vline:
                px(img, x, y, COPPER_DARK)
    return img


def tex_copper_rivet(rng: Random) -> Image.Image:
    """Dome head on a flat flange."""
    img = blank()
    cx, cy, r = 7.5, 8.5, 4.6
    for y in range(3, 9):
        for x in range(3, 13):
            if math.hypot(x - cx, y - cy) <= r:
                if x - cx + (y - cy) < -2.5:
                    color = COPPER_HI
                elif x - cx + (y - cy) < 0.5:
                    color = COPPER_LIGHT
                else:
                    color = COPPER
                px(img, x, y, color)
    for x in range(3, 13):
        px(img, x, 9, COPPER_DARK)
        px(img, x, 10, COPPER_DARKER)
    for x in range(2, 14):
        px(img, x, 11, COPPER_OUTLINE)
    return img


def tex_copper_screw(rng: Random) -> Image.Image:
    """Slotted head, threaded shaft, pointed tip."""
    img = blank()
    for y in (1, 2, 3):
        for x in range(4, 12):
            px(img, x, y, COPPER_LIGHT if y == 1 else COPPER)
    for x in range(5, 11):
        px(img, x, 2, COPPER_OUTLINE)  # slot
    for y in range(4, 12):
        px(img, 7, y, COPPER)
        px(img, 8, y, COPPER_DARK)
    for y in (5, 7, 9, 11):
        side = 6 if (y // 2) % 2 == 0 else 9
        px(img, side, y, COPPER_DARKER)
    px(img, 7, 12, COPPER_DARK)
    px(img, 8, 12, COPPER_DARKER)
    px(img, 7, 13, COPPER_DARKER)
    return img


def tex_copper_coil(rng: Random) -> Image.Image:
    """Donut of tightly wound wire; winding stripes alternate light/dark."""
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            r = math.hypot(x - cx, y - cy)
            if 2.6 <= r <= 6.4:
                ang = (math.degrees(math.atan2(y - cy, x - cx)) + 360.0) % 360.0
                band = int(ang / 20.0) % 2
                if x - cx + (y - cy) < -3:
                    color = COPPER_LIGHT if band else COPPER_HI
                else:
                    color = COPPER_DARK if band else COPPER
                px(img, x, y, color)
            elif 1.8 <= r < 2.6:
                px(img, x, y, COPPER_OUTLINE)
    return img


def tex_copper_alloy_ingot(rng: Random) -> Image.Image:
    img = ingot(rng, ALLOY_HI, ALLOY_LIGHT, ALLOY, ALLOY_DARK, ALLOY_OUTLINE,
                speck=[IRON, IRON_LIGHT])
    return img


def tex_charred_copper_ingot(rng: Random) -> Image.Image:
    return ingot(rng, CHARCOAL_LIGHT, CHARCOAL, CHARCOAL, CHARCOAL_DARK, (0x18, 0x10, 0x13),
                 speck=[COPPER_DARKER, EMBER, COPPER_DARK])


def tex_inferno_alloy_ingot(rng: Random) -> Image.Image:
    return ingot(rng, EMBER_HOT, EMBER_BRIGHT, CHARCOAL, CHARCOAL_DARK, (0x18, 0x10, 0x13),
                 speck=[EMBER, EMBER_BRIGHT, EMBER_HOT])


def tex_inferno_shard(rng: Random) -> Image.Image:
    """Slanted crystal shard with facet highlight and a soft ember glow halo."""
    img = blank()
    rows = {2: (9, 9), 3: (8, 10), 4: (8, 10), 5: (7, 10), 6: (7, 10), 7: (6, 9),
            8: (6, 9), 9: (5, 9), 10: (5, 8), 11: (5, 8), 12: (6, 7), 13: (6, 6)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x == x0:
                color = EMBER_HOT
            elif x == x1:
                color = CHARCOAL
            else:
                color = EMBER_BRIGHT if (x + y) % 3 else EMBER
            px(img, x, y, color)
    # Facet line.
    for y in range(4, 12):
        px(img, rows[y][0] + 1, y, EMBER_HOT if y % 2 else EMBER_BRIGHT)
    # Glow halo (semi-transparent).
    for y, (x0, x1) in rows.items():
        px(img, x0 - 1, y, EMBER, 90)
        px(img, x1 + 1, y, EMBER, 90)
    px(img, 9, 1, EMBER, 110)
    px(img, 6, 14, EMBER, 110)
    return img


def tex_inferno_powder(rng: Random) -> Image.Image:
    img = pile(rng, [EMBER, EMBER, EMBER_BRIGHT, CHARCOAL, EMBER_HOT], [EMBER_BRIGHT, EMBER_HOT])
    return img


def tex_bottle_cap(rng: Random) -> Image.Image:
    """Crimped-edge disc with a star stamp in the middle."""
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            r = math.hypot(x - cx, y - cy)
            if r <= 6.2:
                if r > 4.9:
                    ang = (math.degrees(math.atan2(y - cy, x - cx)) + 360.0) % 30.0
                    color = COPPER_DARKER if ang < 15.0 else COPPER_LIGHT  # crimps
                elif r > 3.6:
                    color = COPPER
                else:
                    color = COPPER_LIGHT
                px(img, x, y, color)
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8), (6, 6), (9, 9), (9, 6), (6, 9)]:
        px(img, x, y, SODA)  # soda-maroon stamp
    px(img, 5, 5, COPPER_HI)
    return img


def tex_soda_essence(rng: Random) -> Image.Image:
    """Maroon essence droplet with a white shine."""
    img = blank()
    rows = {2: (8, 8), 3: (8, 8), 4: (7, 9), 5: (7, 9), 6: (6, 10), 7: (6, 10),
            8: (5, 11), 9: (5, 11), 10: (5, 11), 11: (5, 11), 12: (6, 10), 13: (7, 9)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (2, 13):
                color = SODA_OUTLINE
            elif x <= x0 + 1 and y <= 9:
                color = SODA_LIGHT
            elif x >= x1 - 1 or y >= 11:
                color = SODA_DARK
            else:
                color = SODA
            px(img, x, y, color)
    px(img, 6, 8, (0xFF, 0xE8, 0xEA))
    px(img, 7, 9, SODA_LIGHT)
    return img


TEXTURES = {
    "copper_dust": tex_copper_dust,
    "oxidized_copper_dust": tex_oxidized_copper_dust,
    "copper_rod": tex_copper_rod,
    "copper_gear": tex_copper_gear,
    "copper_sheet": tex_copper_sheet,
    "copper_wire": tex_copper_wire,
    "copper_spring": tex_copper_spring,
    "copper_mesh": tex_copper_mesh,
    "copper_rivet": tex_copper_rivet,
    "copper_screw": tex_copper_screw,
    "copper_coil": tex_copper_coil,
    "copper_alloy_ingot": tex_copper_alloy_ingot,
    "charred_copper_ingot": tex_charred_copper_ingot,
    "inferno_shard": tex_inferno_shard,
    "inferno_powder": tex_inferno_powder,
    "inferno_alloy_ingot": tex_inferno_alloy_ingot,
    "bottle_cap": tex_bottle_cap,
    "soda_essence": tex_soda_essence,
}


# ---------------------------------------------------------------------------
# Item model-definitions + models
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
        img = fn(Random(f"copper_inferno:materials:{name}"))
        img.save(tex_dir / f"{name}.png")


# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 formats; only vanilla + own ids)
# ---------------------------------------------------------------------------

def recipe_path(name: str) -> Path:
    return DATA / "recipe" / "materials" / f"{name}.json"


def emit_shaped(name: str, key: dict, pattern: list, result_id: str, count: int) -> None:
    write_json(recipe_path(name), {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": result_id},
    })


def emit_shapeless(name: str, ingredients: list, result_id: str, count: int) -> None:
    write_json(recipe_path(name), {
        "type": "minecraft:crafting_shapeless",
        "category": "misc",
        "ingredients": ingredients,
        "result": {"count": count, "id": result_id},
    })


def emit_recipes() -> None:
    m = lambda p: f"{NS}:{p}"

    # copper_dust: 1 raw copper ground into 2 dust (shapeless).
    emit_shapeless("copper_dust", ["minecraft:raw_copper"], m("copper_dust"), 2)
    # Bonus sink: smelt dust back into a vanilla copper ingot (vanilla smelting format).
    write_json(recipe_path("copper_ingot_from_smelting_copper_dust"), {
        "type": "minecraft:smelting",
        "category": "misc",
        "cookingtime": 200,
        "experience": 0.7,
        "group": "copper_ingot",
        "ingredient": m("copper_dust"),
        "result": {"id": "minecraft:copper_ingot"},
    })
    # oxidized_copper_dust: honeycomb is the only vanilla "oxidation-chemistry" item and is
    # renewable; used here as the patina reagent (documented choice per spec).
    emit_shapeless("oxidized_copper_dust", [m("copper_dust"), "minecraft:honeycomb"],
                   m("oxidized_copper_dust"), 1)
    # copper_rod: 2 ingots in a column -> 4 rods.
    emit_shaped("copper_rod", {"#": "minecraft:copper_ingot"}, ["#", "#"], m("copper_rod"), 4)
    # copper_sheet: 2 ingots in a row -> 2 sheets.
    emit_shaped("copper_sheet", {"#": "minecraft:copper_ingot"}, ["##"], m("copper_sheet"), 2)
    # copper_wire: 3 rods in a column drawn into 4 wires.
    emit_shaped("copper_wire", {"#": m("copper_rod")}, ["#", "#", "#"], m("copper_wire"), 4)
    # copper_gear: 4 rods in a diamond around an iron nugget axle.
    emit_shaped("copper_gear", {"#": m("copper_rod"), "N": "minecraft:iron_nugget"},
                [" # ", "#N#", " # "], m("copper_gear"), 1)
    # copper_spring: 2 wires in a column -> 2 springs.
    emit_shaped("copper_spring", {"#": m("copper_wire")}, ["#", "#"], m("copper_spring"), 2)
    # copper_mesh: 2x2 wires woven into a mesh.
    emit_shaped("copper_mesh", {"#": m("copper_wire")}, ["##", "##"], m("copper_mesh"), 1)
    # copper_rivet: rod + iron nugget head -> 4 rivets.
    emit_shapeless("copper_rivet", [m("copper_rod"), "minecraft:iron_nugget"], m("copper_rivet"), 4)
    # copper_screw: rod + dust (thread grinding) -> 4 screws.
    emit_shapeless("copper_screw", [m("copper_rod"), m("copper_dust")], m("copper_screw"), 4)
    # copper_coil: 8 wires wound in a 3x3 ring.
    emit_shaped("copper_coil", {"#": m("copper_wire")}, ["###", "# #", "###"], m("copper_coil"), 1)
    # copper_alloy_ingot: shapeless copper + iron + dust flux.
    emit_shapeless("copper_alloy_ingot",
                   ["minecraft:copper_ingot", "minecraft:iron_ingot", m("copper_dust")],
                   m("copper_alloy_ingot"), 1)
    # charred_copper_ingot: shapeless copper ingot + charcoal.
    emit_shapeless("charred_copper_ingot", ["minecraft:copper_ingot", "minecraft:charcoal"],
                   m("charred_copper_ingot"), 1)
    # inferno_shard: shapeless amethyst shard + blaze powder + copper dust.
    emit_shapeless("inferno_shard",
                   ["minecraft:amethyst_shard", "minecraft:blaze_powder", m("copper_dust")],
                   m("inferno_shard"), 1)
    # inferno_powder: 1 shard crushed into 2 powder.
    emit_shapeless("inferno_powder", [m("inferno_shard")], m("inferno_powder"), 2)
    # inferno_alloy_ingot: shapeless charred ingot + inferno powder + blaze rod.
    emit_shapeless("inferno_alloy_ingot",
                   [m("charred_copper_ingot"), m("inferno_powder"), "minecraft:blaze_rod"],
                   m("inferno_alloy_ingot"), 1)
    # bottle_cap: iron nugget + copper dust stamped into 4 caps.
    emit_shapeless("bottle_cap", ["minecraft:iron_nugget", m("copper_dust")], m("bottle_cap"), 4)
    # soda_essence: shapeless sugar + cocoa beans + honey bottle (bottle returned by vanilla
    # crafting-remainder behavior).
    emit_shapeless("soda_essence",
                   ["minecraft:sugar", "minecraft:cocoa_beans", "minecraft:honey_bottle"],
                   m("soda_essence"), 1)


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_item_assets()
    emit_textures()
    emit_recipes()
    write_json(ASSETS / "lang" / "fragments" / "materials.json", LANG)
    print(f"materials_gen: assets for {len(ITEM_IDS)} items generated.")


if __name__ == "__main__":
    main()
