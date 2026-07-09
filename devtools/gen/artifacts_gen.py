#!/usr/bin/env python3
"""Asset generator for the v4 "Artifacts" feature (WP17).

8 named gadget/artifact features registered by feature/artifacts/ArtifactsFeature.java:
5 items (grappling_coil, ember_shield, fizz_jetpack, doom_horn, copper_drill), 2 block-
entity blocks (brew_keg, trophy_pedestal) and the itemless heat_suit_bonus set bonus.

Emits (all deterministic, idempotent — run any number of times, same bytes):
  - 5 item texture PNGs + 3 block texture PNGs (16x16, painted with the same sprite
    primitives the arsenal_gen painters use)
  - items/<id>.json + models/item/<id>.json for the 5 items (handheld parent for the
    drill, item/generated otherwise); items/<id>.json + blockstate + block model + loot
    (drop self) for the 2 blocks — vanilla 1.21.9 formats via devtools/gen/lib_gen.py
    (the trophy pedestal uses a custom elements model like the statue pedestal)
  - 7 recipes under data/copper_inferno/recipe/artifacts/. UNIQUE-INPUT RULE: every
    recipe includes at least one copper_inferno-only ingredient (grapnel_hook,
    talisman_cord, tempered_plate, dr_pepper, doom_alloy_shard, horn_valve,
    tool_matrix, gilded_rivet), so no vanilla/mod input collisions are possible
  - the drill repair tag (data/copper_inferno/tags/item/copper_drill_repair.json)
  - lang fragments EN + real DE (assets/copper_inferno/lang/fragments{,_de}/
    artifacts.json): 7 content names + 4 gameplay messages
  - the vanilla-tag fragment devtools/tagfrag/artifacts.json for merge_tags.py
    (item/pickaxes + block mineable tags)
"""

import sys
from pathlib import Path

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import (  # noqa: E402
    NS, _bm, _bs, _im, _it, _shade, item_def, loot_drop_self, merge, plank_grain,
    seeded, write_files, write_json,
)

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
TAGFRAG = ROOT / "devtools" / "tagfrag"

# ---------------------------------------------------------------------------
# Content lists (must match ArtifactsFeature.java registrations 1:1)
# ---------------------------------------------------------------------------

ITEMS = ["grappling_coil", "ember_shield", "fizz_jetpack", "doom_horn", "copper_drill"]
BLOCKS = ["brew_keg", "trophy_pedestal"]
HANDHELD = {"copper_drill"}

# ---------------------------------------------------------------------------
# Palette (arsenal_gen names)
# ---------------------------------------------------------------------------

K = (0x1C, 0x12, 0x16)   # outline / near-black
D = (0x2B, 0x22, 0x26)   # very dark charcoal (handles)
C = (0x3D, 0x2C, 0x2E)   # charcoal
COPPER = (0xC1, 0x6A, 0x3F)
COPPER_L = (0xE0, 0x8C, 0x5C)
IRON = (0xB8, 0xB8, 0xC0)
GOLD = (0xF3, 0xC5, 0x4A)
GOLD_L = (0xFF, 0xE2, 0x8F)
CORD = (0x7A, 0x5C, 0x38)
RED = (0x9A, 0x22, 0x28)
EMBER = (0xE2, 0x58, 0x22)
EMBER_L = (0xFF, 0x7A, 0x2F)
PEPPER = (0x7E, 0x2E, 0x38)   # Dr.Pepper maroon
FOAM = (0xEF, 0xE3, 0xC8)
DOOM = (0x38, 0x2A, 0x30)     # doom alloy dark
STONE = (0x8C, 0x8C, 0x90)

# ---------------------------------------------------------------------------
# Sprite primitives (ports of the arsenal_gen helpers, deterministic)
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
    """MC-style sprite outline drawn OUTSIDE the shape (port of arsenal_gen.outline)."""
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


def ring(img, cx, cy, r2_min, r2_max, color):
    for y in range(16):
        for x in range(16):
            d2 = (x - cx) ** 2 + (y - cy) ** 2
            if r2_min <= d2 <= r2_max:
                put(img, x, y, color)


def diag_handle(img, x0, y0, steps):
    """2-wide charcoal handle running up-right from (x0, y0), arsenal_gen style."""
    for i in range(steps):
        x, y = x0 + i, y0 - i
        put(img, x, y, D)
        put(img, x + 1, y, C)


# ---------------------------------------------------------------------------
# Item painters
# ---------------------------------------------------------------------------


def tex_grappling_coil():
    """Coiled copper cable with a barbed anchor hook hanging off the bottom-right."""
    img = blank()
    ring(img, 6.5, 6.5, 10, 22, COPPER)
    ring(img, 6.5, 6.5, 10, 13, COPPER_L)
    ring(img, 6.5, 6.5, 20, 22, _shade(COPPER, -30))
    # cable tail running out of the coil...
    for x, y in [(10, 9), (11, 10), (12, 11)]:
        put(img, x, y, CORD)
    # ...into the barbed anchor
    for x, y in [(12, 12), (13, 12), (14, 12), (14, 13), (11, 13), (11, 14), (14, 11)]:
        put(img, x, y, IRON)
    put(img, 14, 11, _shade(IRON, 50))
    put(img, 5, 4, _shade(COPPER, 90))
    return outline(img)


def tex_ember_shield():
    """Round tempered-plate buckler with a molten ember core."""
    img = blank()
    ring(img, 7.5, 7.5, 0, 42, IRON)
    ring(img, 7.5, 7.5, 34, 42, _shade(IRON, -45))
    ring(img, 7.5, 7.5, 16, 24, _shade(IRON, -20))
    ring(img, 7.5, 7.5, 0, 6, EMBER)
    for x, y in [(7, 7), (8, 8)]:
        put(img, x, y, EMBER_L)
    for x, y in [(4, 4), (11, 4), (4, 11), (11, 11)]:
        put(img, x, y, _shade(IRON, 45))
    return outline(img)


def tex_fizz_jetpack():
    """Two maroon Dr.Pepper kegs with copper straps and down-firing foam nozzles."""
    img = blank()
    for x0 in (3, 9):
        fill_rect(img, x0, 2, x0 + 3, 10, PEPPER)
        fill_rect(img, x0, 2, x0 + 3, 2, _shade(PEPPER, 40))
        put(img, x0 + 1, 3, _shade(PEPPER, 70))
        fill_rect(img, x0 + 1, 11, x0 + 2, 12, C)          # nozzle
        put(img, x0 + 1, 13, FOAM)                          # foam puff
        put(img, x0 + 2, 14, FOAM)
    fill_rect(img, 3, 5, 12, 5, COPPER)                     # upper strap
    fill_rect(img, 3, 8, 12, 8, COPPER)                     # lower strap
    put(img, 7, 5, _shade(COPPER, 60))
    return outline(img)


def tex_doom_horn():
    """Dark doom-alloy war horn, red glow smoldering in the bell (arsenal horn shape)."""
    img = blank()
    for i, w in [(0, 1), (1, 1), (2, 2), (3, 2), (4, 3), (5, 3), (6, 4)]:
        x = 3 + i
        for dy in range(w):
            put(img, x, 10 - i + dy, DOOM)
    fill_rect(img, 10, 3, 12, 7, DOOM)
    fill_rect(img, 10, 3, 12, 3, _shade(DOOM, 40))
    put(img, 3, 10, RED)                                    # mouthpiece ember
    put(img, 11, 4, RED)                                    # bell glow
    put(img, 11, 5, _shade(RED, 60))
    put(img, 4, 9, _shade(DOOM, -20))
    return outline(img)


def tex_copper_drill():
    """Charcoal grip, copper motor block, iron drill bit tapering up-right."""
    img = blank()
    diag_handle(img, 2, 13, 5)
    fill_rect(img, 6, 6, 10, 10, COPPER)                    # motor block
    fill_rect(img, 6, 6, 10, 6, COPPER_L)
    put(img, 7, 7, _shade(COPPER, 80))
    for i, half in [(0, 2), (1, 2), (2, 1), (3, 1), (4, 0)]:  # tapering bit
        x, y = 10 + i, 5 - i
        for d in range(-half, half + 1):
            put(img, x, y + d, IRON if (i + d) % 2 else _shade(IRON, -30))
    put(img, 14, 1, _shade(IRON, 60))
    return outline(img)


ITEM_PAINTERS = {
    "grappling_coil": tex_grappling_coil,
    "ember_shield": tex_ember_shield,
    "fizz_jetpack": tex_fizz_jetpack,
    "doom_horn": tex_doom_horn,
    "copper_drill": tex_copper_drill,
}

# ---------------------------------------------------------------------------
# Block texture painters (16x16 RGB)
# ---------------------------------------------------------------------------


def tex_brew_keg_side():
    """Vertical barrel staves (rotated plank grain) with two copper bands."""
    img = plank_grain(seeded("brew_keg_side"), (0x6E, 0x4A, 0x2A), (0x82, 0x5A, 0x36),
                      (0x4A, 0x30, 0x1C)).rotate(90)
    for y in (2, 3, 11, 12):
        for x in range(16):
            img.putpixel((x, y), COPPER if (x + y) % 5 else _shade(COPPER, 30))
    return img


def tex_brew_keg_top():
    """Barrel lid: concentric stave rings, copper rim, dark bung hole."""
    img = Image.new("RGB", (16, 16))
    rng = seeded("brew_keg_top")
    for y in range(16):
        for x in range(16):
            e = min(x, y, 15 - x, 15 - y)
            if e == 0:
                base = COPPER if (x + y) % 5 else _shade(COPPER, 30)
            elif int(max(abs(x - 7.5), abs(y - 7.5))) % 2 == 0:
                base = (0x7A, 0x52, 0x30)
            else:
                base = (0x64, 0x42, 0x26)
            j = rng.randint(-3, 3)
            img.putpixel((x, y), tuple(max(0, min(255, c + j)) for c in base))
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), (0x30, 0x1E, 0x12))
    return img


def tex_trophy_pedestal():
    """Polished stone with a gilded rivet trim row (used by every model face)."""
    img = Image.new("RGB", (16, 16))
    rng = seeded("trophy_pedestal")
    shades = [(0x74, 0x74, 0x78), STONE, STONE, (0xA6, 0xA6, 0xAA)]
    cells = {(cx, cy): rng.choice(shades) for cy in range(8) for cx in range(8)}
    for y in range(16):
        for x in range(16):
            img.putpixel((x, y), cells[(x // 2, y // 2)])
    for x in range(16):
        img.putpixel((x, 0), _shade(STONE, 25))
        img.putpixel((x, 15), _shade(STONE, -35))
    for x in (2, 6, 10, 14):
        img.putpixel((x, 3), GOLD)
        img.putpixel((x, 12), GOLD)
    img.putpixel((6, 3), GOLD_L)
    return img


BLOCK_TEXTURES = {
    "brew_keg_side": tex_brew_keg_side,
    "brew_keg_top": tex_brew_keg_top,
    "trophy_pedestal": tex_trophy_pedestal,
}

# ---------------------------------------------------------------------------
# Item + block JSON (vanilla 1.21.9 formats via lib_gen)
# ---------------------------------------------------------------------------


def block_ref(name: str) -> str:
    return f"{NS}:block/{name}"


def pedestal_element(from_xyz, to_xyz, cull_down=False):
    faces = {}
    for face in ("down", "up", "north", "south", "west", "east"):
        spec = {"texture": "#pedestal"}
        if face == "down" and cull_down:
            spec["cullface"] = "down"
        faces[face] = spec
    return {"from": list(from_xyz), "to": list(to_xyz), "faces": faces}


def emit_assets() -> dict:
    files = {}
    # ----- the 5 items
    for name in ITEMS:
        parent = "minecraft:item/handheld" if name in HANDHELD else "minecraft:item/generated"
        files = merge(files, {
            _im(name): {"parent": parent, "textures": {"layer0": f"{NS}:item/{name}"}},
            _it(name): item_def(f"{NS}:item/{name}"),
        })

    # ----- brew keg: single-variant cube_bottom_top block (vanilla format)
    files = merge(files, {
        _bs("brew_keg"): {"variants": {"": {"model": block_ref("brew_keg")}}},
        _bm("brew_keg"): {"parent": "minecraft:block/cube_bottom_top", "textures": {
            "bottom": block_ref("brew_keg_top"),
            "side": block_ref("brew_keg_side"),
            "top": block_ref("brew_keg_top"),
        }},
        _it("brew_keg"): item_def(block_ref("brew_keg")),
    }, loot_drop_self("brew_keg"))

    # ----- trophy pedestal: custom elements model (statue-pedestal format: base slab,
    # shaft, top plate; the floating trophy is drawn by the block entity renderer)
    files = merge(files, {
        _bs("trophy_pedestal"): {"variants": {"": {"model": block_ref("trophy_pedestal")}}},
        _bm("trophy_pedestal"): {
            "parent": "minecraft:block/block",
            "textures": {
                "particle": block_ref("trophy_pedestal"),
                "pedestal": block_ref("trophy_pedestal"),
            },
            "elements": [
                pedestal_element((2, 0, 2), (14, 2, 14), cull_down=True),
                pedestal_element((5, 2, 5), (11, 10, 11)),
                pedestal_element((3, 10, 3), (13, 12, 13)),
            ],
        },
        _it("trophy_pedestal"): item_def(block_ref("trophy_pedestal")),
    }, loot_drop_self("trophy_pedestal"))
    return files


def emit_textures() -> int:
    item_dir = ASSETS / "textures" / "item"
    block_dir = ASSETS / "textures" / "block"
    item_dir.mkdir(parents=True, exist_ok=True)
    block_dir.mkdir(parents=True, exist_ok=True)
    n = 0
    for name, painter in ITEM_PAINTERS.items():
        painter().save(item_dir / f"{name}.png")
        n += 1
    for name, painter in BLOCK_TEXTURES.items():
        painter().save(block_dir / f"{name}.png")
        n += 1
    return n


# ---------------------------------------------------------------------------
# Recipes (data/copper_inferno/recipe/artifacts/, vanilla 1.21.9 formats mirroring
# arsenal_gen). UNIQUE-INPUT RULE: every recipe includes a copper_inferno-only item.
# ---------------------------------------------------------------------------


def rp(name: str) -> Path:
    return DATA / "recipe" / "artifacts" / f"{name}.json"


def emit_recipes() -> int:
    n = 0

    def shaped(name: str, key: dict, pattern: list, category: str = "equipment") -> None:
        nonlocal n
        write_json(rp(name), {
            "type": "minecraft:crafting_shaped", "category": category,
            "key": key, "pattern": pattern,
            "result": {"count": 1, "id": f"{NS}:{name}"},
        })
        n += 1

    def shapeless(name: str, ingredients: list, category: str = "equipment") -> None:
        nonlocal n
        write_json(rp(name), {
            "type": "minecraft:crafting_shapeless", "category": category,
            "ingredients": ingredients,
            "result": {"count": 1, "id": f"{NS}:{name}"},
        })
        n += 1

    shaped("grappling_coil",
           {"H": f"{NS}:grapnel_hook", "T": f"{NS}:talisman_cord", "C": "minecraft:copper_ingot"},
           ["H", "T", "C"])
    shaped("ember_shield",
           {"P": f"{NS}:tempered_plate", "M": "minecraft:magma_cream", "C": "minecraft:copper_ingot"},
           [" P ", "PMP", " C "])
    shaped("fizz_jetpack",
           {"T": f"{NS}:tempered_plate", "D": f"{NS}:dr_pepper", "F": "minecraft:feather"},
           ["TDT", "TDT", " F "])
    shapeless("doom_horn",
              [f"{NS}:doom_alloy_shard", f"{NS}:horn_valve", "minecraft:copper_ingot"])
    shaped("copper_drill",
           {"C": "minecraft:copper_ingot", "M": f"{NS}:tool_matrix", "S": "minecraft:stick"},
           ["CCC", "CMC", " S "])
    shaped("brew_keg",
           {"W": "#minecraft:planks", "D": f"{NS}:dr_pepper", "C": "minecraft:copper_ingot"},
           ["WCW", "WDW", "WWW"], category="misc")
    shaped("trophy_pedestal",
           {"S": "minecraft:smooth_stone_slab", "R": f"{NS}:gilded_rivet"},
           ["SSS", " R ", "SSS"], category="misc")
    return n


# ---------------------------------------------------------------------------
# Repair tag + lang fragments + vanilla-tag fragment
# ---------------------------------------------------------------------------


def emit_repair_tag() -> None:
    write_json(DATA / "tags" / "item" / "copper_drill_repair.json",
               {"values": ["minecraft:copper_ingot"]})


LANG_EN = {
    f"item.{NS}.grappling_coil": "Grappling Coil",
    f"item.{NS}.ember_shield": "Ember Shield",
    f"item.{NS}.fizz_jetpack": "Fizz Jetpack",
    f"item.{NS}.doom_horn": "Doom Horn",
    f"item.{NS}.copper_drill": "Copper Drill",
    f"block.{NS}.brew_keg": "Brew Keg",
    f"block.{NS}.trophy_pedestal": "Trophy Pedestal",
    f"message.{NS}.heat_suit_bonus.active": "Heat suit sealed - fire cannot touch you.",
    f"message.{NS}.brew_keg.stored": "Keg: %s/%s drinks",
    f"message.{NS}.brew_keg.full": "The keg is full.",
    f"message.{NS}.brew_keg.empty": "The keg is empty.",
}

LANG_DE = {
    f"item.{NS}.grappling_coil": "Greifhaken-Spule",
    f"item.{NS}.ember_shield": "Glutschild",
    f"item.{NS}.fizz_jetpack": "Brause-Jetpack",
    f"item.{NS}.doom_horn": "Doom-Horn",
    f"item.{NS}.copper_drill": "Kupferbohrer",
    f"block.{NS}.brew_keg": "Braufass",
    f"block.{NS}.trophy_pedestal": "Troph\u00e4ensockel",
    f"message.{NS}.heat_suit_bonus.active": "Hitzeanzug versiegelt - Feuer kann dir nichts anhaben.",
    f"message.{NS}.brew_keg.stored": "Fass: %s/%s Getr\u00e4nke",
    f"message.{NS}.brew_keg.full": "Das Fass ist voll.",
    f"message.{NS}.brew_keg.empty": "Das Fass ist leer.",
}


def emit_lang() -> tuple[int, int]:
    write_json(ASSETS / "lang" / "fragments" / "artifacts.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "artifacts.json", LANG_DE)
    return len(LANG_EN), len(LANG_DE)


def emit_tagfrag() -> None:
    write_json(TAGFRAG / "artifacts.json", {
        "item/pickaxes": [f"{NS}:copper_drill"],
        "block/mineable/axe": [f"{NS}:brew_keg"],
        "block/mineable/pickaxe": [f"{NS}:trophy_pedestal"],
    })


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    assert set(ITEM_PAINTERS) == set(ITEMS), "painter/id mismatch"
    assert len(ITEMS) + len(BLOCKS) == 7, "expected 7 registered ids"

    files = emit_assets()
    json_count = write_files(files, RES)
    tex_count = emit_textures()
    recipe_count = emit_recipes()
    emit_repair_tag()
    en_count, de_count = emit_lang()
    emit_tagfrag()

    item_defs = [p for p in files if f"assets/{NS}/items/" in p]
    blockstates = [p for p in files if f"assets/{NS}/blockstates/" in p]
    loot = [p for p in files if "/loot_table/" in p]
    assert len(item_defs) == 7, f"expected 7 item defs, got {len(item_defs)}"
    assert len(blockstates) == 2, f"expected 2 blockstates, got {len(blockstates)}"
    assert len(loot) == 2, f"expected 2 loot tables, got {len(loot)}"
    assert recipe_count == 7, f"expected 7 recipes, got {recipe_count}"
    assert tex_count == 8, f"expected 8 textures, got {tex_count}"
    assert en_count == de_count == 11, f"lang key count mismatch: {en_count}/{de_count}"

    print(f"artifacts_gen: 7 ids -> {json_count} JSON files, {tex_count} textures, "
          f"{recipe_count} recipes, 1 repair tag, {en_count} EN + {de_count} DE lang keys, "
          "1 tag fragment.")


if __name__ == "__main__":
    main()
