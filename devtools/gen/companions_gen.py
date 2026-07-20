#!/usr/bin/env python3
"""Asset generator for the v4.1 "Companions" feature: 2 tameable pets, 2 spawn eggs.

Idempotent: running it any number of times produces byte-identical files. Emits, directly
into src/main/resources (same approach as copperfauna_gen.py):
  - 2 entity textures      assets/copper_inferno/textures/entity/<pet>.png
                           (lib_gen.extract_vanilla of the base mob's vanilla texture,
                           remapped onto the pet's palette via lib_gen.recolor)
  - 2 item textures        assets/copper_inferno/textures/item/<pet>_spawn_egg.png
                           (16x16, Pillow, seeded — copperfauna egg painter)
  - item model-definitions assets/copper_inferno/items/<pet>_spawn_egg.json (1.21.9 format)
  - item models            assets/copper_inferno/models/item/<pet>_spawn_egg.json
  - entity loot tables     data/copper_inferno/loot_table/entities/<pet>.json
                           (copper_kit drops vanilla string, ember_hound vanilla bones)
  - lang fragments         assets/copper_inferno/lang/fragments/companions.json (EN)
                           assets/copper_inferno/lang/fragments_de/companions.json (DE)

Pet roster:
  copper_kit   a copper-furred cat (vanilla cat/red.png recolored copper); drops string
  ember_hound  a fire-immune ember wolf (vanilla wolf/wolf.png recolored ember); drops bones
"""

import math
import sys
from pathlib import Path
from random import Random

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import (  # noqa: E402
    NS, extract_vanilla, item_def, recolor, write_json,
)
from PIL import Image  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# ---------------------------------------------------------------------------
# Palettes (dark -> light luminance ramps for lib_gen.recolor)
# ---------------------------------------------------------------------------

COPPER = [(0x3A, 0x22, 0x18), (0x7A, 0x43, 0x2B), (0xB4, 0x68, 0x4D), (0xE7, 0x9C, 0x6F), (0xFF, 0xD0, 0xA8)]
EMBER = [(0x30, 0x10, 0x0A), (0x77, 0x28, 0x10), (0xC2, 0x4A, 0x1C), (0xF0, 0x83, 0x35), (0xFF, 0xC8, 0x84)]

# ---------------------------------------------------------------------------
# Roster: pet id -> (vanilla entity texture in the client jar, recolor palette,
#                    (loot drop id, loot min, loot max))
# ---------------------------------------------------------------------------

E = "assets/minecraft/textures/entity"

PETS = {
    "copper_kit": (f"{E}/cat/red.png", COPPER, ("minecraft:string", 0.0, 2.0)),
    "ember_hound": (f"{E}/wolf/wolf.png", EMBER, ("minecraft:bone", 0.0, 2.0)),
}

ITEM_IDS = [f"{pet}_spawn_egg" for pet in PETS]

# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

PET_NAMES = {
    "copper_kit": ("Copper Kit", "Kupferkätzchen"),
    "ember_hound": ("Ember Hound", "Gluthund"),
}


def emit_lang() -> None:
    en, de = {}, {}
    for pet, (name_en, name_de) in PET_NAMES.items():
        en[f"entity.{NS}.{pet}"] = name_en
        de[f"entity.{NS}.{pet}"] = name_de
        en[f"item.{NS}.{pet}_spawn_egg"] = f"{name_en} Spawn Egg"
        de[f"item.{NS}.{pet}_spawn_egg"] = f"{name_de}-Spawn-Ei"
    write_json(ASSETS / "lang" / "fragments" / "companions.json", en)
    write_json(ASSETS / "lang" / "fragments_de" / "companions.json", de)


# ---------------------------------------------------------------------------
# Texture helpers (16x16 item sprites — copperfauna egg painter, verbatim)
# ---------------------------------------------------------------------------


def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def spawn_egg(rng: Random, base, base_dark, base_light, spots, outline) -> Image.Image:
    """Classic spawn-egg silhouette with seeded speckles (copperfauna_gen painter)."""
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
            color = base_light
        elif x >= 10 or y >= 12:
            color = base_dark
        else:
            color = base
        px(img, x, y, color)
    interior = [(x, y) for x, y, edge in cells if not edge]
    for x, y in rng.sample(interior, 12):
        px(img, x, y, spots)
    return img


def egg_for(pet: str) -> Image.Image:
    """Egg colors derived from the pet's entity palette: body = mid tones, spots = the
    palette's brightest stop, outline = its darkest stop."""
    pal = PETS[pet][1]
    rng = Random(f"{NS}:companions:{pet}_spawn_egg")
    return spawn_egg(rng, pal[2], pal[1], pal[3], pal[4], pal[0])


def emit_textures() -> None:
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    for pet in PETS:
        egg_for(pet).save(item_dir / f"{pet}_spawn_egg.png")

    entity_dir = ASSETS / "textures" / "entity"
    entity_dir.mkdir(parents=True, exist_ok=True)
    for pet, (vanilla_path, palette, _drop) in PETS.items():
        recolor(extract_vanilla(vanilla_path), palette).save(entity_dir / f"{pet}.png")


# ---------------------------------------------------------------------------
# Item model-definitions + models
# ---------------------------------------------------------------------------


def emit_item_assets() -> None:
    for item_id in ITEM_IDS:
        write_json(ASSETS / "items" / f"{item_id}.json", item_def(f"{NS}:item/{item_id}"))
        write_json(ASSETS / "models" / "item" / f"{item_id}.json", {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"{NS}:item/{item_id}"},
        })


# ---------------------------------------------------------------------------
# Entity loot tables (copperfauna schema incl. "random_sequence"; both pets drop
# vanilla items — string for the cat, bones for the hound)
# ---------------------------------------------------------------------------


def emit_loot_tables() -> None:
    for pet_id, (_tex, _pal, (drop_id, cmin, cmax)) in PETS.items():
        write_json(DATA / "loot_table" / "entities" / f"{pet_id}.json", {
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
            "random_sequence": f"{NS}:entities/{pet_id}",
        })


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    emit_textures()
    emit_item_assets()
    emit_loot_tables()
    emit_lang()
    print(f"companions_gen: assets for {len(PETS)} pets generated "
          f"({len(PETS)} entity textures, {len(ITEM_IDS)} egg textures + item assets, "
          f"{len(PETS)} loot tables, lang EN+DE).")


if __name__ == "__main__":
    main()
