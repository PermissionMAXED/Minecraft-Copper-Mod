#!/usr/bin/env python3
"""Utility blocks v2 asset generator for COPPER INFERNO 1 (mod id: copper_inferno).

Generates ALL JSON assets/data + 16x16 PNG textures for the 16 functional/redstone
copper blocks (buttons, pressure plates, fence, fence gate, crates, pipe, plating),
directly into src/main/resources so they ship with the mod.

Idempotent: writes a fixed set of files it owns (never deletes/globs), and all
texture noise is seeded per texture name, so re-runs produce identical bytes.

JSON formats are copied from EXACT vanilla 1.21.9 templates extracted from
~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar:
  - blockstates: stone_button.json (24 variants) / stone_pressure_plate.json /
    oak_fence.json (multipart) / oak_fence_gate.json (16 variants) / basalt.json
    (axis pillar) / cut_copper.json (plain cube).
  - models: stone_button(_pressed/_inventory) -> parents block/button*;
    stone_pressure_plate(_down) -> parents block/pressure_plate_up/_down;
    oak_fence_post/_side/_inventory -> parents block/fence_*;
    oak_fence_gate(_open/_wall/_wall_open) -> parents block/template_fence_gate*;
    basalt -> block/cube_column; cut_copper -> block/cube_all.
  - items: vanilla item model-definition format ({"model":{"type":"minecraft:model",...}});
    buttons point at <id>_inventory, the fence at copper_fence_inventory (like
    vanilla items/stone_button.json and items/oak_fence.json).
  - loot: drop-self survives_explosion pool (same format the masonry gen uses,
    identical to vanilla stone.json).

RECIPE CHOICES (vanilla + own ids only; deviations documented):
  - exposed/weathered/oxidized_copper_button: shapeless 1x matching vanilla cube
    (minecraft:exposed_copper / weathered_copper / oxidized_copper), per spec.
  - copper_button: shapeless 1x minecraft:cut_copper. DEVIATION: the specced
    "shapeless 1x minecraft:copper_block" collides with vanilla
    data/minecraft/recipe/copper_ingot.json (shapeless 1x copper_block -> 9 ingots),
    which would make one of the two uncraftable. Cut copper has no single-item
    vanilla recipe, so it is safe.
  - pressure plates: shaped ["##"] of the matching vanilla cube -> 1 (vanilla
    stone_pressure_plate format), per spec.
  - copper_fence: I N I / I N I (I=minecraft:copper_ingot, N=minecraft:iron_nugget) -> 3.
  - copper_fence_gate: N I N / N I N -> 1.
  - copper_crate: P I P / I _ I / P I P (P=#minecraft:planks tag, I=copper_ingot) -> 2
    (4 planks corners + 4 ingot edges, per spec; the planks TAG is a vanilla id).
  - stage crates: shapeless copper_crate + matching vanilla copper cube -> 1, per spec.
  - copper_pipe: I _ I / I _ I / I _ I (two vertical ingot columns, hollow bore) -> 6.
    DEVIATION: the specced "3 vertical copper_ingot columns" is the full 3x3 ingot
    grid, which is EXACTLY vanilla copper_block.json (9 ingots -> copper block).
    Two columns with a hollow middle avoids the collision (copper_door is the
    2-wide II/II/II pattern, distinct from a 3-wide pattern with a gap).
  - copper_plating: S S / S S (S=copper_inferno:copper_sheet, own v1 id) -> 4.
    DEVIATION: the specced "2x2 copper_ingot" collides with this mod's own
    data/copper_inferno/recipe/masonry/copper_bricks.json (2x2 ingots -> 4 bricks).
    2x2 riveted copper sheets is thematic and collision-free.
"""

import json
import random
from pathlib import Path

from PIL import Image

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / "copper_inferno"
DATA = RES / "data" / "copper_inferno"

MODID = "copper_inferno"

STAGE_PREFIXES = ["", "exposed_", "weathered_", "oxidized_"]

# Matching vanilla copper cube per stage (recipe ingredients).
VANILLA_CUBES = {
    "": "minecraft:copper_block",
    "exposed_": "minecraft:exposed_copper",
    "weathered_": "minecraft:weathered_copper",
    "oxidized_": "minecraft:oxidized_copper",
}

# (light, mid, dark) per stage — same copper palette the masonry/decostone gens use.
PALETTES = {
    "": ((0xE0, 0x73, 0x4D), (0xC1, 0x5A, 0x3B), (0x8F, 0x3D, 0x26)),
    "exposed_": ((0xD1, 0x8C, 0x6F), (0xA9, 0x70, 0x5E), (0x6E, 0x49, 0x3D)),
    "weathered_": ((0x8F, 0xA8, 0x83), (0x6F, 0xB0, 0x8E), (0x48, 0x73, 0x5C)),
    "oxidized_": ((0x57, 0xA0, 0x7B), (0x4E, 0x9E, 0x7A), (0x33, 0x67, 0x4F)),
}

# Plank colors for the crate wood fill (stage-independent oak-ish browns).
PLANK_LIGHT = (0xB8, 0x94, 0x5F)
PLANK_MID = (0xA0, 0x7D, 0x4E)
PLANK_DARK = (0x6B, 0x50, 0x30)


def write_json(path: Path, obj) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True) + "\n", encoding="utf-8")


def clamp(v: int) -> int:
    return max(0, min(255, v))


def jitter(rnd: random.Random, rgb, amount=6):
    d = rnd.randint(-amount, amount)
    return (clamp(rgb[0] + d), clamp(rgb[1] + d), clamp(rgb[2] + d))


def shade(rgb, amount):
    return (clamp(rgb[0] + amount), clamp(rgb[1] + amount), clamp(rgb[2] + amount))


# ---------------------------------------------------------------------------
# Textures (16x16, deterministic per name)
# ---------------------------------------------------------------------------

def make_panel_texture(name: str, palette) -> Image.Image:
    """Flat brushed-copper sheet (shared by button/plate/fence/gate of a stage):
    mid fill with faint horizontal brushing, light top/left bevel, dark
    bottom/right bevel."""
    light, mid, dark = palette
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        row_tint = 4 if y % 4 == 1 else (-3 if y % 4 == 3 else 0)  # brushing
        for x in range(16):
            base = shade(mid, row_tint)
            if x == 0 or y == 0:
                base = shade(light, 6)
            elif x == 15 or y == 15:
                base = shade(dark, 6)
            px[x, y] = jitter(rnd, base, 4)
    return img


def make_crate_texture(name: str, palette) -> Image.Image:
    """Crate: horizontal wooden planks framed by a 2px stage-copper border with
    rivet dots at the corners and edge midpoints."""
    light, mid, dark = palette
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            if x < 2 or x > 13 or y < 2 or y > 13:  # copper frame
                base = mid
                if x == 0 or y == 0:
                    base = light
                elif x == 15 or y == 15:
                    base = dark
                px[x, y] = jitter(rnd, base, 4)
            else:  # plank fill: 4px courses with dark seams
                if (y - 2) % 4 == 3:
                    base = PLANK_DARK
                elif (y - 2) % 4 == 0:
                    base = PLANK_LIGHT
                else:
                    base = PLANK_MID
                px[x, y] = jitter(rnd, base, 5)
    # rivets: bright dots on the frame (corners + edge midpoints)
    for rx, ry in [(1, 1), (14, 1), (1, 14), (14, 14), (7, 1), (8, 1),
                   (7, 14), (8, 14), (1, 7), (1, 8), (14, 7), (14, 8)]:
        px[rx, ry] = shade(light, 24)
    return img


def make_pipe_side_texture(name: str, palette) -> Image.Image:
    """Pipe side: vertical cylindrical shading (dark edges, bright center) with
    flange bands at the top and bottom."""
    light, mid, dark = palette
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    # cylindrical column shading by x
    col_shade = [-24, -14, -6, 0, 6, 12, 16, 18, 18, 16, 12, 6, 0, -6, -14, -24]
    for y in range(16):
        flange = y in (0, 1, 14, 15)
        for x in range(16):
            base = shade(mid, col_shade[x])
            if flange:
                base = shade(base, 14 if y in (0, 15) else -10)
            px[x, y] = jitter(rnd, base, 3)
    return img


def make_pipe_end_texture(name: str, palette) -> Image.Image:
    """Pipe end: copper ring with a round dark bore in the middle."""
    light, mid, dark = palette
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d2 = (x - cx) ** 2 + (y - cy) ** 2
            if d2 <= 4.5 ** 2:  # bore (hollow interior)
                px[x, y] = jitter(rnd, (0x28, 0x1A, 0x14), 3)
            elif d2 <= 5.5 ** 2:  # inner rim highlight
                px[x, y] = jitter(rnd, light, 4)
            elif d2 <= 7.5 ** 2:  # ring body
                px[x, y] = jitter(rnd, mid, 4)
            else:  # squared corners of the block face
                px[x, y] = jitter(rnd, dark, 4)
    return img


def make_plating_texture(name: str, palette) -> Image.Image:
    """Plating: 2x2 grid of 8x8 riveted sheets, each beveled (light top/left,
    dark bottom/right) with rivet dots inset at all four sheet corners."""
    light, mid, dark = palette
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            lx, ly = x % 8, y % 8
            base = mid
            if lx == 7 or ly == 7:  # panel seam
                base = dark
            elif lx == 0 or ly == 0:  # bevel highlight
                base = light
            px[x, y] = jitter(rnd, base, 4)
    for py in (0, 8):
        for pxs in (0, 8):
            for rx, ry in [(1, 1), (5, 1), (1, 5), (5, 5)]:
                px[pxs + rx + 0, py + ry] = shade(light, 20)
    return img


# ---------------------------------------------------------------------------
# EXACT vanilla 1.21.9 blockstate templates (extracted from minecraft-client.jar).
# Model references were replaced with @TOKEN@ before embedding; a single string
# replace re-targets them (suffixed refs like @BTN@_pressed resolve too).
# ---------------------------------------------------------------------------

# assets/minecraft/blockstates/stone_button.json, "minecraft:block/stone_button" -> "@BTN@"
BUTTON_BLOCKSTATE_TEMPLATE = """
{
  "variants": {
    "face=ceiling,facing=east,powered=false": { "model": "@BTN@", "x": 180, "y": 270 },
    "face=ceiling,facing=east,powered=true": { "model": "@BTN@_pressed", "x": 180, "y": 270 },
    "face=ceiling,facing=north,powered=false": { "model": "@BTN@", "x": 180, "y": 180 },
    "face=ceiling,facing=north,powered=true": { "model": "@BTN@_pressed", "x": 180, "y": 180 },
    "face=ceiling,facing=south,powered=false": { "model": "@BTN@", "x": 180 },
    "face=ceiling,facing=south,powered=true": { "model": "@BTN@_pressed", "x": 180 },
    "face=ceiling,facing=west,powered=false": { "model": "@BTN@", "x": 180, "y": 90 },
    "face=ceiling,facing=west,powered=true": { "model": "@BTN@_pressed", "x": 180, "y": 90 },
    "face=floor,facing=east,powered=false": { "model": "@BTN@", "y": 90 },
    "face=floor,facing=east,powered=true": { "model": "@BTN@_pressed", "y": 90 },
    "face=floor,facing=north,powered=false": { "model": "@BTN@" },
    "face=floor,facing=north,powered=true": { "model": "@BTN@_pressed" },
    "face=floor,facing=south,powered=false": { "model": "@BTN@", "y": 180 },
    "face=floor,facing=south,powered=true": { "model": "@BTN@_pressed", "y": 180 },
    "face=floor,facing=west,powered=false": { "model": "@BTN@", "y": 270 },
    "face=floor,facing=west,powered=true": { "model": "@BTN@_pressed", "y": 270 },
    "face=wall,facing=east,powered=false": { "model": "@BTN@", "uvlock": true, "x": 90, "y": 90 },
    "face=wall,facing=east,powered=true": { "model": "@BTN@_pressed", "uvlock": true, "x": 90, "y": 90 },
    "face=wall,facing=north,powered=false": { "model": "@BTN@", "uvlock": true, "x": 90 },
    "face=wall,facing=north,powered=true": { "model": "@BTN@_pressed", "uvlock": true, "x": 90 },
    "face=wall,facing=south,powered=false": { "model": "@BTN@", "uvlock": true, "x": 90, "y": 180 },
    "face=wall,facing=south,powered=true": { "model": "@BTN@_pressed", "uvlock": true, "x": 90, "y": 180 },
    "face=wall,facing=west,powered=false": { "model": "@BTN@", "uvlock": true, "x": 90, "y": 270 },
    "face=wall,facing=west,powered=true": { "model": "@BTN@_pressed", "uvlock": true, "x": 90, "y": 270 }
  }
}
"""

# assets/minecraft/blockstates/oak_fence.json, "minecraft:block/oak_fence" -> "@FENCE@"
FENCE_BLOCKSTATE_TEMPLATE = """
{
  "multipart": [
    { "apply": { "model": "@FENCE@_post" } },
    { "apply": { "model": "@FENCE@_side", "uvlock": true }, "when": { "north": "true" } },
    { "apply": { "model": "@FENCE@_side", "uvlock": true, "y": 90 }, "when": { "east": "true" } },
    { "apply": { "model": "@FENCE@_side", "uvlock": true, "y": 180 }, "when": { "south": "true" } },
    { "apply": { "model": "@FENCE@_side", "uvlock": true, "y": 270 }, "when": { "west": "true" } }
  ]
}
"""

# assets/minecraft/blockstates/oak_fence_gate.json, "minecraft:block/oak_fence_gate" -> "@GATE@"
FENCE_GATE_BLOCKSTATE_TEMPLATE = """
{
  "variants": {
    "facing=east,in_wall=false,open=false": { "model": "@GATE@", "uvlock": true, "y": 270 },
    "facing=east,in_wall=false,open=true": { "model": "@GATE@_open", "uvlock": true, "y": 270 },
    "facing=east,in_wall=true,open=false": { "model": "@GATE@_wall", "uvlock": true, "y": 270 },
    "facing=east,in_wall=true,open=true": { "model": "@GATE@_wall_open", "uvlock": true, "y": 270 },
    "facing=north,in_wall=false,open=false": { "model": "@GATE@", "uvlock": true, "y": 180 },
    "facing=north,in_wall=false,open=true": { "model": "@GATE@_open", "uvlock": true, "y": 180 },
    "facing=north,in_wall=true,open=false": { "model": "@GATE@_wall", "uvlock": true, "y": 180 },
    "facing=north,in_wall=true,open=true": { "model": "@GATE@_wall_open", "uvlock": true, "y": 180 },
    "facing=south,in_wall=false,open=false": { "model": "@GATE@", "uvlock": true },
    "facing=south,in_wall=false,open=true": { "model": "@GATE@_open", "uvlock": true },
    "facing=south,in_wall=true,open=false": { "model": "@GATE@_wall", "uvlock": true },
    "facing=south,in_wall=true,open=true": { "model": "@GATE@_wall_open", "uvlock": true },
    "facing=west,in_wall=false,open=false": { "model": "@GATE@", "uvlock": true, "y": 90 },
    "facing=west,in_wall=false,open=true": { "model": "@GATE@_open", "uvlock": true, "y": 90 },
    "facing=west,in_wall=true,open=false": { "model": "@GATE@_wall", "uvlock": true, "y": 90 },
    "facing=west,in_wall=true,open=true": { "model": "@GATE@_wall_open", "uvlock": true, "y": 90 }
  }
}
"""


def block_model(id_: str) -> str:
    return f"{MODID}:block/{id_}"


def block_tex(name: str) -> str:
    return f"{MODID}:block/{name}"


def item_def(model_id: str):
    # vanilla items/stone_button.json format (minecraft:model definition)
    return {"model": {"type": "minecraft:model", "model": block_model(model_id)}}


def drop_self_loot(block_id: str):
    # vanilla stone.json / masonry-gen drop-self format
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


def shaped_recipe(pattern, key, result_id, count, category, group=None):
    obj = {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": f"{MODID}:{result_id}"},
    }
    if group:
        obj["group"] = group
    return obj


def shapeless_recipe(ingredients, result_id, count, category, group=None):
    obj = {
        "type": "minecraft:crafting_shapeless",
        "category": category,
        "ingredients": ingredients,
        "result": {"count": count, "id": f"{MODID}:{result_id}"},
    }
    if group:
        obj["group"] = group
    return obj


def display_name(block_id: str) -> str:
    return " ".join(w.capitalize() for w in block_id.split("_"))


def main() -> None:
    lang = {}
    all_ids = []
    files = 0

    blockstates = {}
    models = {}
    items = {}
    recipes = {}
    textures = {}

    # ------------------------------------------------------------------
    # Per-stage content: button, pressure plate, crate (+ shared flat texture)
    # ------------------------------------------------------------------
    for prefix in STAGE_PREFIXES:
        palette = PALETTES[prefix]
        panel_tex = f"{prefix}copper_panel"
        textures[panel_tex] = make_panel_texture(panel_tex, palette)

        button = f"{prefix}copper_button"
        plate = f"{prefix}copper_pressure_plate"
        crate = f"{prefix}copper_crate"
        all_ids += [button, plate, crate]

        # --- button (vanilla stone_button asset set) ---
        blockstates[button] = json.loads(
            BUTTON_BLOCKSTATE_TEMPLATE.replace("@BTN@", block_model(button)))
        models[button] = {"parent": "minecraft:block/button",
                          "textures": {"texture": block_tex(panel_tex)}}
        models[f"{button}_pressed"] = {"parent": "minecraft:block/button_pressed",
                                       "textures": {"texture": block_tex(panel_tex)}}
        models[f"{button}_inventory"] = {"parent": "minecraft:block/button_inventory",
                                         "textures": {"texture": block_tex(panel_tex)}}
        items[button] = item_def(f"{button}_inventory")
        if prefix == "":
            # DEVIATION (see module docstring): 1x copper_block collides with
            # vanilla copper_ingot.json (copper_block -> 9 ingots).
            button_ingredient = "minecraft:cut_copper"
        else:
            button_ingredient = VANILLA_CUBES[prefix]
        recipes[button] = shapeless_recipe([button_ingredient], button, 1,
                                           "redstone", group="copper_button")

        # --- pressure plate (vanilla stone_pressure_plate asset set) ---
        blockstates[plate] = {
            "variants": {
                "powered=false": {"model": block_model(plate)},
                "powered=true": {"model": block_model(plate) + "_down"},
            }
        }
        models[plate] = {"parent": "minecraft:block/pressure_plate_up",
                         "textures": {"texture": block_tex(panel_tex)}}
        models[f"{plate}_down"] = {"parent": "minecraft:block/pressure_plate_down",
                                   "textures": {"texture": block_tex(panel_tex)}}
        items[plate] = item_def(plate)
        recipes[plate] = shaped_recipe(["##"], {"#": VANILLA_CUBES[prefix]}, plate, 1,
                                       "redstone", group="copper_pressure_plate")

        # --- crate (plain cube) ---
        textures[crate] = make_crate_texture(crate, palette)
        blockstates[crate] = {"variants": {"": {"model": block_model(crate)}}}
        models[crate] = {"parent": "minecraft:block/cube_all",
                         "textures": {"all": block_tex(crate)}}
        items[crate] = item_def(crate)
        if prefix == "":
            recipes[crate] = shaped_recipe(
                ["PIP", "I I", "PIP"],
                {"P": "#minecraft:planks", "I": "minecraft:copper_ingot"},
                crate, 2, "building")
        else:
            recipes[crate] = shapeless_recipe(
                [f"{MODID}:copper_crate", VANILLA_CUBES[prefix]],
                crate, 1, "building", group="copper_crate")

    # ------------------------------------------------------------------
    # copper_fence (vanilla oak_fence asset set, base copper panel texture)
    # ------------------------------------------------------------------
    fence = "copper_fence"
    all_ids.append(fence)
    fence_tex = block_tex("copper_panel")
    blockstates[fence] = json.loads(
        FENCE_BLOCKSTATE_TEMPLATE.replace("@FENCE@", block_model(fence)))
    models[f"{fence}_post"] = {"parent": "minecraft:block/fence_post",
                               "textures": {"texture": fence_tex}}
    models[f"{fence}_side"] = {"parent": "minecraft:block/fence_side",
                               "textures": {"texture": fence_tex}}
    models[f"{fence}_inventory"] = {"parent": "minecraft:block/fence_inventory",
                                    "textures": {"texture": fence_tex}}
    items[fence] = item_def(f"{fence}_inventory")
    recipes[fence] = shaped_recipe(
        ["INI", "INI"],
        {"I": "minecraft:copper_ingot", "N": "minecraft:iron_nugget"},
        fence, 3, "misc")

    # ------------------------------------------------------------------
    # copper_fence_gate (vanilla oak_fence_gate asset set)
    # ------------------------------------------------------------------
    gate = "copper_fence_gate"
    all_ids.append(gate)
    blockstates[gate] = json.loads(
        FENCE_GATE_BLOCKSTATE_TEMPLATE.replace("@GATE@", block_model(gate)))
    for suffix, parent in [("", "minecraft:block/template_fence_gate"),
                           ("_open", "minecraft:block/template_fence_gate_open"),
                           ("_wall", "minecraft:block/template_fence_gate_wall"),
                           ("_wall_open", "minecraft:block/template_fence_gate_wall_open")]:
        models[f"{gate}{suffix}"] = {"parent": parent, "textures": {"texture": fence_tex}}
    items[gate] = item_def(gate)
    recipes[gate] = shaped_recipe(
        ["NIN", "NIN"],
        {"I": "minecraft:copper_ingot", "N": "minecraft:iron_nugget"},
        gate, 1, "redstone")

    # ------------------------------------------------------------------
    # copper_pipe (axis pillar, vanilla basalt blockstate + cube_column model)
    # ------------------------------------------------------------------
    pipe = "copper_pipe"
    all_ids.append(pipe)
    textures["copper_pipe_side"] = make_pipe_side_texture("copper_pipe_side", PALETTES[""])
    textures["copper_pipe_end"] = make_pipe_end_texture("copper_pipe_end", PALETTES[""])
    blockstates[pipe] = {
        "variants": {
            "axis=x": {"model": block_model(pipe), "x": 90, "y": 90},
            "axis=y": {"model": block_model(pipe)},
            "axis=z": {"model": block_model(pipe), "x": 90},
        }
    }
    models[pipe] = {"parent": "minecraft:block/cube_column",
                    "textures": {"end": block_tex("copper_pipe_end"),
                                 "side": block_tex("copper_pipe_side")}}
    items[pipe] = item_def(pipe)
    # DEVIATION (see module docstring): hollow two-column pattern instead of the
    # full 3x3 ingot grid, which is vanilla copper_block.json.
    recipes[pipe] = shaped_recipe(
        ["I I", "I I", "I I"], {"I": "minecraft:copper_ingot"}, pipe, 6, "building")

    # ------------------------------------------------------------------
    # copper_plating (plain cube, riveted sheet)
    # ------------------------------------------------------------------
    plating = "copper_plating"
    all_ids.append(plating)
    textures[plating] = make_plating_texture(plating, PALETTES[""])
    blockstates[plating] = {"variants": {"": {"model": block_model(plating)}}}
    models[plating] = {"parent": "minecraft:block/cube_all",
                       "textures": {"all": block_tex(plating)}}
    items[plating] = item_def(plating)
    # DEVIATION (see module docstring): 2x2 copper_sheet instead of 2x2
    # copper_ingot, which is this mod's own masonry copper_bricks recipe.
    recipes[plating] = shaped_recipe(
        ["SS", "SS"], {"S": f"{MODID}:copper_sheet"}, plating, 4, "building")

    # ------------------------------------------------------------------
    # write everything
    # ------------------------------------------------------------------
    for name, img in textures.items():
        path = ASSETS / "textures" / "block" / f"{name}.png"
        path.parent.mkdir(parents=True, exist_ok=True)
        img.save(path)
        files += 1
    for name, obj in blockstates.items():
        write_json(ASSETS / "blockstates" / f"{name}.json", obj)
        files += 1
    for name, obj in models.items():
        write_json(ASSETS / "models" / "block" / f"{name}.json", obj)
        files += 1
    for name, obj in items.items():
        write_json(ASSETS / "items" / f"{name}.json", obj)
        files += 1
    for bid in all_ids:
        write_json(DATA / "loot_table" / "blocks" / f"{bid}.json", drop_self_loot(bid))
        files += 1
    for name, obj in recipes.items():
        write_json(DATA / "recipe" / "utilityblocks" / f"{name}.json", obj)
        files += 1

    for bid in all_ids:
        lang[f"block.{MODID}.{bid}"] = display_name(bid)
    write_json(ASSETS / "lang" / "fragments" / "utilityblocks.json", dict(sorted(lang.items())))
    files += 1

    assert len(all_ids) == 16, f"expected 16 block ids, got {len(all_ids)}"
    print(f"utilityblocks_gen: wrote {files} files for {len(all_ids)} block ids")


if __name__ == "__main__":
    main()
