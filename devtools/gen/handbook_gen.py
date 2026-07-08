#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "handbook" feature (the in-game bilingual mod
guide: 1 item, no blocks).

Idempotent: running it any number of times produces the same files. By default this script
writes ONLY PNGs (the handbook item texture; the screen itself is drawn with DrawContext
fill/text primitives, so no GUI textures are needed under textures/gui/handbook/); pass
--write-json to also (re)emit the JSON scaffolding:
  - item model-definition + item model (assets/copper_inferno/items + models/item)
  - the shapeless crafting recipe (data/copper_inferno/recipe/handbook/
    copper_inferno_handbook.json: book + copper ingot; collision-checked via
    devtools/check_recipe_collisions.py)
  - lang fragments EN + DE (assets/copper_inferno/lang/fragments{,_de}/handbook.json) with
    the item name and every screen.copper_inferno.handbook.* label
"""

import json
import sys
from pathlib import Path

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> PNGs only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
NS = "copper_inferno"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

ITEM = "copper_inferno_handbook"

# Copper cover palette (project copper base #E0734D / #C15A3B) + ember flame accents.
K = (0x1C, 0x12, 0x16)   # outline / near-black
S = (0x6E, 0x38, 0x24)   # spine / cover shadow
B = (0xC1, 0x5A, 0x3B)   # copper cover dark
b = (0xE0, 0x73, 0x4D)   # copper cover base
P = (0xF2, 0xE6, 0xD0)   # page edge
p = (0xD8, 0xC8, 0xAC)   # page edge shadow
E = (0xE2, 0x58, 0x22)   # ember
e = (0xFF, 0x7A, 0x2F)   # ember bright
H = (0xFF, 0xB1, 0x6B)   # ember hot

LANG_EN = {
    "item.copper_inferno.copper_inferno_handbook": "COPPER INFERNO Handbook",
    "screen.copper_inferno.handbook.title": "COPPER INFERNO Handbook",
    "screen.copper_inferno.handbook.category.blocks": "Blocks",
    "screen.copper_inferno.handbook.category.items": "Items",
    "screen.copper_inferno.handbook.category.gear": "Tools & Armor",
    "screen.copper_inferno.handbook.category.dimension": "Dimension",
    "screen.copper_inferno.handbook.category.mobs": "Mobs",
    "screen.copper_inferno.handbook.category.bosses": "Bosses",
    "screen.copper_inferno.handbook.category.recipes": "Recipes",
    "screen.copper_inferno.handbook.prev": "<",
    "screen.copper_inferno.handbook.next": ">",
    "screen.copper_inferno.handbook.page": "Page %s/%s",
    "screen.copper_inferno.handbook.empty": "No entries in this category yet.",
    "screen.copper_inferno.handbook.lang_de": "DE",
    "screen.copper_inferno.handbook.lang_en": "EN",
}
LANG_DE = {
    "item.copper_inferno.copper_inferno_handbook": "COPPER-INFERNO-Handbuch",
    "screen.copper_inferno.handbook.title": "COPPER-INFERNO-Handbuch",
    "screen.copper_inferno.handbook.category.blocks": "Bl\u00f6cke",
    "screen.copper_inferno.handbook.category.items": "Gegenst\u00e4nde",
    "screen.copper_inferno.handbook.category.gear": "Werkzeuge & R\u00fcstung",
    "screen.copper_inferno.handbook.category.dimension": "Dimension",
    "screen.copper_inferno.handbook.category.mobs": "Kreaturen",
    "screen.copper_inferno.handbook.category.bosses": "Bosse",
    "screen.copper_inferno.handbook.category.recipes": "Rezepte",
    "screen.copper_inferno.handbook.prev": "<",
    "screen.copper_inferno.handbook.next": ">",
    "screen.copper_inferno.handbook.page": "Seite %s/%s",
    "screen.copper_inferno.handbook.empty": "Noch keine Eintr\u00e4ge in dieser Kategorie.",
    "screen.copper_inferno.handbook.lang_de": "DE",
    "screen.copper_inferno.handbook.lang_en": "EN",
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
    write_json(ASSETS / "models" / "item" / f"{ITEM}.json",
               {"parent": "minecraft:item/generated", "textures": {"layer0": f"{NS}:item/{ITEM}"}})
    write_json(ASSETS / "items" / f"{ITEM}.json",
               {"model": {"type": "minecraft:model", "model": f"{NS}:item/{ITEM}"}})


def emit_recipe() -> None:
    write_json(DATA / "recipe" / "handbook" / f"{ITEM}.json", {
        "type": "minecraft:crafting_shapeless", "category": "misc",
        "ingredients": ["minecraft:book", "minecraft:copper_ingot"],
        "result": {"count": 1, "id": f"{NS}:{ITEM}"},
    })


def emit_lang() -> None:
    write_json(ASSETS / "lang" / "fragments" / "handbook.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "handbook.json", LANG_DE)


# ---------------------------------------------------------------------------
# Item texture (16x16, deterministic pixel art): copper-bound book, flame emblem
# ---------------------------------------------------------------------------

def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def put(img, x, y, color):
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (*color, 255))


def fill_rect(img, x0, y0, x1, y1, color):
    for y in range(y0, y1 + 1):
        for x in range(x0, x1 + 1):
            put(img, x, y, color)


def outline(img, color=K):
    """MC-style sprite outline drawn OUTSIDE the shape (mirrors the other gen scripts)."""
    src = img.copy()
    for y in range(16):
        for x in range(16):
            if src.getpixel((x, y))[3] != 0:
                continue
            for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)):
                nx, ny = x + dx, y + dy
                if 0 <= nx < 16 and 0 <= ny < 16 and src.getpixel((nx, ny))[3] != 0:
                    put(img, x, y, color)
                    break
    return img


def tex_handbook() -> Image.Image:
    img = blank()
    # page block peeking out on the right and bottom (book viewed from the front cover)
    fill_rect(img, 4, 3, 13, 13, p)
    fill_rect(img, 13, 4, 13, 12, P)
    fill_rect(img, 5, 13, 12, 13, P)
    # copper front cover, slightly offset up-left
    fill_rect(img, 3, 2, 12, 12, b)
    fill_rect(img, 10, 2, 12, 4, B)   # top-right corner shading
    fill_rect(img, 3, 10, 5, 12, B)   # bottom-left corner shading
    # spine on the left with two clasp bands
    fill_rect(img, 2, 2, 3, 12, S)
    fill_rect(img, 2, 4, 4, 4, B)
    fill_rect(img, 2, 10, 4, 10, B)
    # ember flame emblem in the cover center
    put(img, 8, 4, E)
    put(img, 7, 5, E)
    put(img, 8, 5, e)
    put(img, 9, 5, E)
    fill_rect(img, 7, 6, 9, 7, e)
    put(img, 8, 6, H)
    put(img, 8, 7, H)
    fill_rect(img, 6, 8, 10, 8, E)
    put(img, 7, 8, e)
    put(img, 9, 8, e)
    put(img, 8, 9, E)
    # cover highlight along the top edge
    fill_rect(img, 4, 2, 9, 2, (0xF0, 0x8A, 0x62))
    return outline(img)


def emit_textures() -> None:
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    tex_handbook().save(item_dir / f"{ITEM}.png")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_item_assets()
    emit_recipe()
    emit_lang()
    emit_textures()
    mode = "textures + JSON" if WRITE_JSON else "textures only (pass --write-json for JSON)"
    print(f"handbook_gen: assets generated ({mode}).")


if __name__ == "__main__":
    main()
