#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO 1 "glasslight" feature (14 blocks).

Idempotent: running it any number of times produces byte-identical output. Emits, into
src/main/resources:
  - blockstates, block models, item models, items/<id>.json model-definitions
  - loot tables (ALL blocks drop themselves unconditionally -- deliberately NO silk-touch
    condition, even for glass, so survival players never lose the block)
  - recipes under data/copper_inferno/recipe/glasslight/
  - lang fragment assets/copper_inferno/lang/fragments/glasslight.json
  - 16x16 RGBA textures (Pillow), real alpha for the glass blocks

JSON formats copied from the vanilla 1.21.9 client jar (glass, glass_pane, redstone_lamp,
lantern blockstates/models/items/loot tables) -- do not "improve" them.

NOTE: JSON emission is legacy scaffolding (opt-in via --write-json); the JSON in
src/main/resources is authoritative -- by default this script writes ONLY PNGs.
"""

import json
import sys
from pathlib import Path

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> textures/*.png only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / "copper_inferno"
DATA = RES / "data" / "copper_inferno"
MODID = "copper_inferno"

# Copper oxidation palette (light, base, dark, darker) per stage.
# Stage ramp: copper (bright) -> exposed (pale dull copper) -> weathered
# (dulled copper + clustered green patina patches, see `patina`) -> oxidized
# (fully green, DARKENED so the ramp ends clearly darker than weathered).
STAGES = {
    "copper": ((0xF0, 0x9A, 0x70), (0xE0, 0x73, 0x4D), (0xC1, 0x5A, 0x3B), (0x91, 0x43, 0x2C)),
    "exposed": ((0xD8, 0xA5, 0x84), (0xC1, 0x86, 0x62), (0xA5, 0x6B, 0x50), (0x7D, 0x51, 0x3D)),
    "weathered": ((0xB2, 0x7E, 0x60), (0x93, 0x66, 0x4E), (0x75, 0x52, 0x40), (0x4F, 0x39, 0x2E)),
    "oxidized": ((0x5E, 0x9C, 0x7B), (0x4C, 0x8B, 0x6B), (0x3F, 0x78, 0x5B), (0x26, 0x4C, 0x39)),
}

# Clustered patina greens overlaid on the weathered stage (mod greens).
PATINA = ((0x6F, 0xB0, 0x8E), (0x57, 0xA0, 0x7B), (0x41, 0x7A, 0x5E))

EMBER = ((0xFF, 0xD9, 0x8C), (0xFF, 0x9A, 0x3C), (0xE0, 0x5C, 0x20), (0x8A, 0x2E, 0x12))
MAROON = ((0xE8, 0x8A, 0x8A), (0xB3, 0x3A, 0x3F), (0x7A, 0x1E, 0x2B), (0x4A, 0x10, 0x1B))
AMBER = ((0xFF, 0xC9, 0x6B), (0xE0, 0x8A, 0x2E), (0xB0, 0x5E, 0x1E), (0x6E, 0x2A, 0x18))


def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2) + "\n", encoding="utf-8")


def write_png(rel: str, img: Image.Image) -> None:
    path = ASSETS / "textures" / (rel + ".png")
    path.parent.mkdir(parents=True, exist_ok=True)
    img.save(path)


def h(x: int, y: int, salt: int = 0) -> int:
    """Tiny deterministic pixel hash for texture variation (no RNG => idempotent)."""
    return (x * 31 + y * 17 + salt * 7) % 5


def patina(x: int, y: int) -> bool:
    """Blocky clustered patch mask for the weathered stage: whole 3x3 cells go
    green together (patchwork of clustered blobs, not per-pixel noise)."""
    return h(x // 3, y // 3, 8) < 2


# ---------------------------------------------------------------------------
# Textures
# ---------------------------------------------------------------------------

def tex_glass(stage: str) -> Image.Image:
    """2px copper frame, semi-transparent tinted center (real RGBA alpha).
    The weathered stage gets clustered green patina patches on the frame and
    a patchy green tint in the glass."""
    light, base, dark, darker = STAGES[stage]
    p_light, p_mid, p_dark = PATINA
    weathered = stage == "weathered"
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(16):
        for x in range(16):
            edge = min(x, y, 15 - x, 15 - y)
            patch = weathered and patina(x, y)
            if edge == 0:
                px[x, y] = (*(p_dark if patch else darker), 255)
            elif edge == 1:
                if patch:
                    px[x, y] = (*p_light, 255) if h(x, y) == 0 else (*p_mid, 255)
                else:
                    px[x, y] = (*light, 255) if h(x, y) == 0 else (*base, 255)
            else:
                # Transparent tinted center with a faint diagonal streak.
                tint = p_light if patch else light
                if (x + y) % 7 == 0:
                    px[x, y] = (*tint, 110)
                else:
                    px[x, y] = (*tint, 70)
    return img


def tex_pane_top(stage: str) -> Image.Image:
    """Edge texture for pane post/side models: 2px opaque strip at columns 7-8."""
    _, base, dark, darker = STAGES[stage]
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(16):
        for x in (7, 8):
            if y in (0, 15):
                px[x, y] = (*darker, 255)
            else:
                px[x, y] = (*base, 255) if h(x, y) < 3 else (*dark, 255)
    return img


def tex_lamp(stage: str, lit: bool) -> Image.Image:
    """Warm 3x3-cell grid lamp; copper grid lines, amber cells (bright when lit).
    Weathered frames get clustered green patina patches (cells stay amber)."""
    light, base, dark, darker = STAGES[stage]
    p_light, p_mid, p_dark = PATINA
    weathered = stage == "weathered"
    on = ((0xFF, 0xE2, 0xA0), (0xFF, 0xC9, 0x6B), (0xF2, 0xA6, 0x4A))
    off = ((0x6E, 0x4A, 0x2E), (0x5A, 0x3A, 0x22), (0x47, 0x2D, 0x1B))
    cell = on if lit else off
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(16):
        for x in range(16):
            patch = weathered and patina(x, y)
            if x in (0, 15) or y in (0, 15):
                px[x, y] = (*(p_dark if patch else darker), 255)
            elif x in (5, 10) or y in (5, 10):
                if patch:
                    px[x, y] = (*p_mid, 255) if h(x, y) < 4 else (*p_light, 255)
                else:
                    px[x, y] = (*dark, 255) if h(x, y) < 4 else (*base, 255)
            else:
                px[x, y] = (*cell[h(x, y, 3) % 3], 255)
    return img


def tex_lantern(stage: str, flame) -> Image.Image:
    """Lantern texture in the vanilla template_lantern UV layout (transparent RGBA).

    Regions sampled by the vanilla templates:
      body sides (0,2)-(5,8), body top/bottom (0,9)-(5,14), cap top (1,10)-(4,13),
      cap sides (1,0)-(4,1), handle/chain column (11..13, 0..12).
    """
    light, base, dark, darker = STAGES[stage]
    f_light, f_base, f_dark, _ = flame
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    # Body sides: caged frame around a glowing flame window.
    for y in range(2, 9):
        for x in range(0, 6):
            if x in (0, 5) or y in (2, 8):
                px[x, y] = (*darker, 255)
            elif x in (1, 4) or y == 3:
                px[x, y] = (*dark, 255) if h(x, y) < 3 else (*base, 255)
            else:
                # Flame window (2x4 inner area).
                px[x, y] = (*f_light, 255) if h(x, y, 1) == 0 else (*f_base, 255)
    # Center hot spot / darker flame base.
    px[2, 7] = (*f_dark, 255)
    px[3, 5] = (*f_light, 255)
    # Body top/bottom (also holds the cap-top sub-region).
    for y in range(9, 15):
        for x in range(0, 6):
            if x in (0, 5) or y in (9, 14):
                px[x, y] = (*darker, 255)
            else:
                px[x, y] = (*base, 255) if h(x, y) < 3 else (*dark, 255)
    # Cap sides.
    for y in range(0, 2):
        for x in range(1, 5):
            px[x, y] = (*dark, 255) if y == 0 else (*base, 255)
    # Handle / chain strip (sampled at (11..13, 0..12)).
    for y in range(0, 13):
        px[12, y] = (*darker, 255)
        if y % 2 == 0:
            px[11, y] = (*dark, 255)
        else:
            px[13, y] = (*dark, 255)
    return img


def tex_lantern_item(stage: str, flame) -> Image.Image:
    """Flat 16x16 item sprite: small caged lantern with glowing window."""
    light, base, dark, darker = STAGES[stage]
    f_light, f_base, f_dark, _ = flame
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    # Hanging ring.
    px[7, 1] = (*dark, 255)
    px[8, 1] = (*dark, 255)
    px[7, 2] = (*darker, 255)
    px[8, 2] = (*darker, 255)
    # Cap.
    for x in range(5, 11):
        px[x, 3] = (*dark, 255)
    for x in range(5, 11):
        px[x, 4] = (*base, 255)
    # Body (4..11 rows 5..12).
    for y in range(5, 13):
        for x in range(4, 12):
            if x in (4, 11) or y in (5, 12):
                px[x, y] = (*darker, 255)
            elif x in (5, 10):
                px[x, y] = (*dark, 255) if h(x, y) < 3 else (*base, 255)
            else:
                px[x, y] = (*f_light, 255) if h(x, y, 2) == 0 else (*f_base, 255)
    px[7, 11] = (*f_dark, 255)
    px[8, 7] = (*f_light, 255)
    # Base foot.
    for x in range(5, 11):
        px[x, 13] = (*dark, 255)
    return img


# Multi-arm chandelier sprite: hanging ring, central stem (C body / L lit
# edge), two out-curving arms with drip pans (KLK), candles and flame dots
# (F tip / W base), and a bottom finial.
CHANDELIER_ITEM_ROWS = [
    ".......KK.......",
    "......K..K......",
    ".......KK.......",
    ".......CL.......",
    "..F....CL....F..",
    "..W....CL....W..",
    "..C....CL....C..",
    "..C....CL....C..",
    ".KLK...CL...KLK.",
    "..K....CL....K..",
    "..KC...CL...CK..",
    "...CCC.CL.CCC...",
    ".....CCCLCC.....",
    ".......CL.......",
    "......KCLK......",
    ".......KK.......",
]


def tex_chandelier_item(stage: str, flame) -> Image.Image:
    """Flat item sprite: multi-arm chandelier silhouette (central stem plus two
    candle arms with flame dots), replacing the old lantern recolor."""
    light, base, dark, darker = STAGES[stage]
    f_light, f_base, _f_dark, _ = flame
    palette = {"K": darker, "C": base, "L": light, "F": f_light, "W": f_base}
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y, row in enumerate(CHANDELIER_ITEM_ROWS):
        for x, ch in enumerate(row):
            if ch != ".":
                px[x, y] = (*palette[ch], 255)
    return img


def tex_chandelier_block() -> Image.Image:
    """Chandelier block texture (still the vanilla template_lantern UV layout,
    TEXTURE-only change): the body-side window shows a central stem and two
    candle arms with flame dots instead of the caged flame window."""
    light, base, dark, darker = STAGES["copper"]
    f_light, f_base, _f_dark, _ = AMBER
    img = tex_lantern("copper", AMBER)
    px = img.load()
    # Redraw the body-side region (0,2)-(5,8) as the multi-arm silhouette.
    for y in range(2, 9):
        for x in range(0, 6):
            px[x, y] = (*darker, 255)  # dark backdrop
    for y in range(2, 9):
        px[2, y] = (*base, 255)  # central stem
        px[3, y] = (*light, 255)
    for x in range(0, 6):
        px[x, 7] = (*base, 255)  # arm bar
    for cx in (0, 5):
        px[cx, 6] = (*light, 255)  # candle on each arm tip
        px[cx, 5] = (*f_base, 255)  # flame base
        px[cx, 4] = (*f_light, 255)  # flame tip
    return img


def tex_glowing_syrup() -> Image.Image:
    """Amber base with maroon swirls and bright glow spots."""
    a_light, a_base, a_dark, _ = AMBER
    m_base = MAROON[2]
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(16):
        for x in range(16):
            if (x * x + y * 3 + x * y) % 11 == 0:
                px[x, y] = (*m_base, 255)
            elif h(x, y, 4) == 0:
                px[x, y] = (*a_light, 255)
            elif h(x, y, 4) == 1:
                px[x, y] = (*a_dark, 255)
            else:
                px[x, y] = (*a_base, 255)
    # A few bright glow droplets.
    for gx, gy in ((3, 4), (11, 2), (7, 9), (13, 12), (2, 13)):
        px[gx, gy] = (0xFF, 0xE8, 0xB0, 255)
    return img


# ---------------------------------------------------------------------------
# JSON helpers (formats extracted from the vanilla 1.21.9 client jar)
# ---------------------------------------------------------------------------

def blockstate_single(block_id: str) -> dict:
    return {"variants": {"": {"model": f"{MODID}:block/{block_id}"}}}


def blockstate_pane(block_id: str) -> dict:
    m = f"{MODID}:block/{block_id}"
    return {
        "multipart": [
            {"apply": {"model": f"{m}_post"}},
            {"apply": {"model": f"{m}_side"}, "when": {"north": "true"}},
            {"apply": {"model": f"{m}_side", "y": 90}, "when": {"east": "true"}},
            {"apply": {"model": f"{m}_side_alt"}, "when": {"south": "true"}},
            {"apply": {"model": f"{m}_side_alt", "y": 90}, "when": {"west": "true"}},
            {"apply": {"model": f"{m}_noside"}, "when": {"north": "false"}},
            {"apply": {"model": f"{m}_noside_alt"}, "when": {"east": "false"}},
            {"apply": {"model": f"{m}_noside_alt", "y": 90}, "when": {"south": "false"}},
            {"apply": {"model": f"{m}_noside", "y": 270}, "when": {"west": "false"}},
        ]
    }


def blockstate_lamp(block_id: str) -> dict:
    return {
        "variants": {
            "lit=false": {"model": f"{MODID}:block/{block_id}"},
            "lit=true": {"model": f"{MODID}:block/{block_id}_on"},
        }
    }


def blockstate_lantern(block_id: str) -> dict:
    return {
        "variants": {
            "hanging=false": {"model": f"{MODID}:block/{block_id}"},
            "hanging=true": {"model": f"{MODID}:block/{block_id}_hanging"},
        }
    }


def model_cube_all(texture: str) -> dict:
    return {"parent": "minecraft:block/cube_all", "textures": {"all": f"{MODID}:block/{texture}"}}


def item_def(model: str) -> dict:
    return {"model": {"type": "minecraft:model", "model": model}}


def loot_self(block_id: str) -> dict:
    # Vanilla redstone_lamp/lantern format. Deliberately NO silk-touch condition even for the
    # glass blocks: every glasslight block drops itself.
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "bonus_rolls": 0.0,
                "conditions": [{"condition": "minecraft:survives_explosion"}],
                "entries": [{"type": "minecraft:item", "name": f"{MODID}:{block_id}"}],
                "rolls": 1.0,
            }
        ],
        "random_sequence": f"{MODID}:blocks/{block_id}",
    }


def recipe_shaped(pattern, key, result_id, count) -> dict:
    return {
        "type": "minecraft:crafting_shaped",
        "category": "building",
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": f"{MODID}:{result_id}"},
    }


def recipe_shapeless(ingredients, result_id, count) -> dict:
    return {
        "type": "minecraft:crafting_shapeless",
        "category": "building",
        "ingredients": ingredients,
        "result": {"count": count, "id": f"{MODID}:{result_id}"},
    }


# ---------------------------------------------------------------------------
# Emission
# ---------------------------------------------------------------------------

def main() -> None:
    glass_stages = ["copper", "exposed", "weathered", "oxidized"]
    glass_ids = {s: (f"{s}_copper_glass" if s != "copper" else "copper_glass") for s in glass_stages}
    lamp_ids = {s: (f"{s}_copper_lamp" if s != "copper" else "copper_lamp") for s in glass_stages}
    pane_stages = ["copper", "oxidized"]
    pane_ids = {s: glass_ids[s] + "_pane" for s in pane_stages}

    # --- Glass cubes -------------------------------------------------------
    for stage in glass_stages:
        bid = glass_ids[stage]
        write_png(f"block/{bid}", tex_glass(stage))
        write_json(ASSETS / "blockstates" / f"{bid}.json", blockstate_single(bid))
        write_json(ASSETS / "models" / "block" / f"{bid}.json", model_cube_all(bid))
        # Like vanilla glass: the item definition points straight at the block model.
        write_json(ASSETS / "items" / f"{bid}.json", item_def(f"{MODID}:block/{bid}"))
        write_json(DATA / "loot_table" / "blocks" / f"{bid}.json", loot_self(bid))

    # --- Glass panes -------------------------------------------------------
    for stage in pane_stages:
        bid = pane_ids[stage]
        glass = glass_ids[stage]  # pane reuses the glass cube texture, like vanilla
        write_png(f"block/{bid}_top", tex_pane_top(stage))
        write_json(ASSETS / "blockstates" / f"{bid}.json", blockstate_pane(bid))
        pane_tex = f"{MODID}:block/{glass}"
        edge_tex = f"{MODID}:block/{bid}_top"
        write_json(ASSETS / "models" / "block" / f"{bid}_post.json",
                   {"parent": "minecraft:block/template_glass_pane_post",
                    "textures": {"edge": edge_tex, "pane": pane_tex}})
        write_json(ASSETS / "models" / "block" / f"{bid}_side.json",
                   {"parent": "minecraft:block/template_glass_pane_side",
                    "textures": {"edge": edge_tex, "pane": pane_tex}})
        write_json(ASSETS / "models" / "block" / f"{bid}_side_alt.json",
                   {"parent": "minecraft:block/template_glass_pane_side_alt",
                    "textures": {"edge": edge_tex, "pane": pane_tex}})
        write_json(ASSETS / "models" / "block" / f"{bid}_noside.json",
                   {"parent": "minecraft:block/template_glass_pane_noside",
                    "textures": {"pane": pane_tex}})
        write_json(ASSETS / "models" / "block" / f"{bid}_noside_alt.json",
                   {"parent": "minecraft:block/template_glass_pane_noside_alt",
                    "textures": {"pane": pane_tex}})
        # Flat item sprite (vanilla glass_pane style: item/generated over the glass texture).
        write_json(ASSETS / "models" / "item" / f"{bid}.json",
                   {"parent": "minecraft:item/generated", "textures": {"layer0": pane_tex}})
        write_json(ASSETS / "items" / f"{bid}.json", item_def(f"{MODID}:item/{bid}"))
        write_json(DATA / "loot_table" / "blocks" / f"{bid}.json", loot_self(bid))

    # --- Redstone lamps ----------------------------------------------------
    for stage in glass_stages:
        bid = lamp_ids[stage]
        write_png(f"block/{bid}", tex_lamp(stage, lit=False))
        write_png(f"block/{bid}_on", tex_lamp(stage, lit=True))
        write_json(ASSETS / "blockstates" / f"{bid}.json", blockstate_lamp(bid))
        write_json(ASSETS / "models" / "block" / f"{bid}.json", model_cube_all(bid))
        write_json(ASSETS / "models" / "block" / f"{bid}_on.json", model_cube_all(f"{bid}_on"))
        # Like vanilla redstone_lamp: item definition uses the unlit block model.
        write_json(ASSETS / "items" / f"{bid}.json", item_def(f"{MODID}:block/{bid}"))
        write_json(DATA / "loot_table" / "blocks" / f"{bid}.json", loot_self(bid))

    # --- Lanterns (incl. chandelier) ----------------------------------------
    lanterns = {
        "inferno_lantern": ("copper", EMBER),
        "soda_lantern": ("copper", MAROON),
        "copper_chandelier": ("copper", AMBER),
    }
    for bid, (stage, flame) in lanterns.items():
        if bid == "copper_chandelier":  # multi-arm silhouette, not a recolor
            write_png(f"block/{bid}", tex_chandelier_block())
            write_png(f"item/{bid}", tex_chandelier_item(stage, flame))
        else:
            write_png(f"block/{bid}", tex_lantern(stage, flame))
            write_png(f"item/{bid}", tex_lantern_item(stage, flame))
        write_json(ASSETS / "blockstates" / f"{bid}.json", blockstate_lantern(bid))
        write_json(ASSETS / "models" / "block" / f"{bid}.json",
                   {"parent": "minecraft:block/template_lantern",
                    "textures": {"lantern": f"{MODID}:block/{bid}"}})
        write_json(ASSETS / "models" / "block" / f"{bid}_hanging.json",
                   {"parent": "minecraft:block/template_hanging_lantern",
                    "textures": {"lantern": f"{MODID}:block/{bid}"}})
        write_json(ASSETS / "models" / "item" / f"{bid}.json",
                   {"parent": "minecraft:item/generated",
                    "textures": {"layer0": f"{MODID}:item/{bid}"}})
        write_json(ASSETS / "items" / f"{bid}.json", item_def(f"{MODID}:item/{bid}"))
        write_json(DATA / "loot_table" / "blocks" / f"{bid}.json", loot_self(bid))

    # --- Glowing syrup block -------------------------------------------------
    write_png("block/glowing_syrup_block", tex_glowing_syrup())
    write_json(ASSETS / "blockstates" / "glowing_syrup_block.json", blockstate_single("glowing_syrup_block"))
    write_json(ASSETS / "models" / "block" / "glowing_syrup_block.json", model_cube_all("glowing_syrup_block"))
    write_json(ASSETS / "items" / "glowing_syrup_block.json", item_def(f"{MODID}:block/glowing_syrup_block"))
    write_json(DATA / "loot_table" / "blocks" / "glowing_syrup_block.json", loot_self("glowing_syrup_block"))

    # --- Recipes (vanilla + own ids + drpepper v1 only) ----------------------
    rdir = DATA / "recipe" / "glasslight"
    # copper_glass: 8 glass around 1 copper ingot -> 8 (like vanilla tinted-glass layout).
    write_json(rdir / "copper_glass.json", recipe_shaped(
        ["GGG", "GCG", "GGG"], {"G": "minecraft:glass", "C": "minecraft:copper_ingot"},
        "copper_glass", 8))
    # Oxidation stages: shapeless copper_glass + the matching vanilla copper cube -> 1.
    for stage in ("exposed", "weathered", "oxidized"):
        write_json(rdir / f"{glass_ids[stage]}.json", recipe_shapeless(
            [f"{MODID}:copper_glass", f"minecraft:{stage}_copper"], glass_ids[stage], 1))
    # Panes: 3x2 of the matching glass -> 16 (vanilla glass_pane layout/yield).
    for stage in pane_stages:
        write_json(rdir / f"{pane_ids[stage]}.json", recipe_shaped(
            ["GGG", "GGG"], {"G": f"{MODID}:{glass_ids[stage]}"}, pane_ids[stage], 16))
    # Lamps: shapeless redstone lamp + matching vanilla copper cube -> 1.
    lamp_cubes = {"copper": "minecraft:copper_block", "exposed": "minecraft:exposed_copper",
                  "weathered": "minecraft:weathered_copper", "oxidized": "minecraft:oxidized_copper"}
    for stage in glass_stages:
        write_json(rdir / f"{lamp_ids[stage]}.json", recipe_shapeless(
            ["minecraft:redstone_lamp", lamp_cubes[stage]], lamp_ids[stage], 1))
    # inferno_lantern: 8 copper ingots around blaze powder -> 1.
    write_json(rdir / "inferno_lantern.json", recipe_shaped(
        ["III", "IBI", "III"], {"I": "minecraft:copper_ingot", "B": "minecraft:blaze_powder"},
        "inferno_lantern", 1))
    # soda_lantern: 8 copper ingots around a Dr.Pepper (v1 id, allowed) -> 1.
    write_json(rdir / "soda_lantern.json", recipe_shaped(
        ["III", "IDI", "III"], {"I": "minecraft:copper_ingot", "D": f"{MODID}:dr_pepper"},
        "soda_lantern", 1))
    # copper_chandelier: hanging stem of ingots over an inferno lantern with ingot arms -> 1.
    write_json(rdir / "copper_chandelier.json", recipe_shaped(
        [" I ", "ILI", "III"], {"I": "minecraft:copper_ingot", "L": f"{MODID}:inferno_lantern"},
        "copper_chandelier", 1))
    # glowing_syrup_block: glowstone dust stirred into a honey block -> 1 (sensible vanilla mix).
    write_json(rdir / "glowing_syrup_block.json", recipe_shapeless(
        ["minecraft:honey_block", "minecraft:glowstone_dust"], "glowing_syrup_block", 1))

    # --- Lang fragment --------------------------------------------------------
    write_json(ASSETS / "lang" / "fragments" / "glasslight.json", {
        "block.copper_inferno.copper_glass": "Copper Glass",
        "block.copper_inferno.exposed_copper_glass": "Exposed Copper Glass",
        "block.copper_inferno.weathered_copper_glass": "Weathered Copper Glass",
        "block.copper_inferno.oxidized_copper_glass": "Oxidized Copper Glass",
        "block.copper_inferno.copper_glass_pane": "Copper Glass Pane",
        "block.copper_inferno.oxidized_copper_glass_pane": "Oxidized Copper Glass Pane",
        "block.copper_inferno.copper_lamp": "Copper Lamp",
        "block.copper_inferno.exposed_copper_lamp": "Exposed Copper Lamp",
        "block.copper_inferno.weathered_copper_lamp": "Weathered Copper Lamp",
        "block.copper_inferno.oxidized_copper_lamp": "Oxidized Copper Lamp",
        "block.copper_inferno.inferno_lantern": "Inferno Lantern",
        "block.copper_inferno.soda_lantern": "Soda Lantern",
        "block.copper_inferno.copper_chandelier": "Copper Chandelier",
        "block.copper_inferno.glowing_syrup_block": "Glowing Syrup Block",
    })

    mode = "textures + JSON" if WRITE_JSON else "textures only (pass --write-json for legacy JSON)"
    print(f"glasslight assets generated OK ({mode})")


if __name__ == "__main__":
    main()
