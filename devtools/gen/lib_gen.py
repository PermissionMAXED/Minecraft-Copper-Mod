#!/usr/bin/env python3
"""Shared asset-generator library for the COPPER INFERNO v4 mega content update.

Importing this module has NO side effects: nothing is read from or written to the
resource tree at import time. Feature generators (chromacopper_gen.py, ...) import the
texture primitives and JSON emitters below and decide themselves what to write.

JSON emitters return {relative-resource-path: json-object} dicts (paths relative to
src/main/resources); call write_files()/write_json() to persist them. Every emitted
structure is an EXACT copy of the formats already shipped by the v2/v3 generators
(devtools/gen/cinderstone_gen.py, copperdeco_gen.py, utilityblocks_gen.py,
infernoflora_gen.py), which are themselves exact copies of the vanilla 1.21.9 formats
extracted from the minecraft client jar. Do NOT "improve" them.

Texture primitives are ports of the seeded-noise painters from cinderstone_gen.py
(noise cube, brick/tile overlays, pillar side/top, glass frame, lamp glow) and
infernoflora_gen.py (plank grain, log rings, bark side), parameterized on palettes
instead of hard-coded feature colors. All are deterministic: seed via seeded(name).

Self-test: `python3 devtools/gen/lib_gen.py` round-trips every emitter through
json.dumps/json.loads and exercises every texture primitive WITHOUT writing anything
into the tree.
"""

import glob
import io
import json
import zipfile
from pathlib import Path
from random import Random

from PIL import Image

NS = "copper_inferno"

# ---------------------------------------------------------------------------
# Filesystem helpers
# ---------------------------------------------------------------------------


def write_json(path, obj) -> None:
    """Write one JSON file: indent=2, sorted keys, trailing newline (repo format)."""
    path = Path(path)
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True, ensure_ascii=False) + "\n",
                    encoding="utf-8")


def write_files(files: dict, res_root) -> int:
    """Write an emitter result dict ({relpath: obj}) under res_root; returns file count."""
    res_root = Path(res_root)
    for rel, obj in files.items():
        write_json(res_root / rel, obj)
    return len(files)


def merge(*dicts) -> dict:
    """Merge emitter results, refusing silent overwrites of conflicting content."""
    out = {}
    for d in dicts:
        for k, v in d.items():
            if k in out and out[k] != v:
                raise ValueError(f"conflicting emission for {k}")
            out[k] = v
    return out


# ---------------------------------------------------------------------------
# Id/reference helpers (identical to the v3 generators)
# ---------------------------------------------------------------------------


def block_ref(name: str) -> str:
    return f"{NS}:block/{name}"


def item_def(model_ref: str) -> dict:
    """items/<id>.json model-definition (vanilla 1.21.9 format)."""
    return {"model": {"type": "minecraft:model", "model": model_ref}}


def _bs(name: str) -> str:
    return f"assets/{NS}/blockstates/{name}.json"


def _bm(name: str) -> str:
    return f"assets/{NS}/models/block/{name}.json"


def _im(name: str) -> str:
    return f"assets/{NS}/models/item/{name}.json"


def _it(name: str) -> str:
    return f"assets/{NS}/items/{name}.json"


def _lt(name: str) -> str:
    return f"data/{NS}/loot_table/blocks/{name}.json"


# ---------------------------------------------------------------------------
# EXACT vanilla 1.21.9 blockstate templates (copied verbatim from
# cinderstone_gen.py / utilityblocks_gen.py, which extracted them from the
# minecraft client jar).
# ---------------------------------------------------------------------------

STAIRS_BLOCKSTATE_TEMPLATE = """
{
  "variants": {
    "facing=east,half=bottom,shape=inner_left": {"model": "@INNER@", "uvlock": true, "y": 270},
    "facing=east,half=bottom,shape=inner_right": {"model": "@INNER@"},
    "facing=east,half=bottom,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "y": 270},
    "facing=east,half=bottom,shape=outer_right": {"model": "@OUTER@"},
    "facing=east,half=bottom,shape=straight": {"model": "@STAIRS@"},
    "facing=east,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180},
    "facing=east,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 90},
    "facing=east,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180},
    "facing=east,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 90},
    "facing=east,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180},
    "facing=north,half=bottom,shape=inner_left": {"model": "@INNER@", "uvlock": true, "y": 180},
    "facing=north,half=bottom,shape=inner_right": {"model": "@INNER@", "uvlock": true, "y": 270},
    "facing=north,half=bottom,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "y": 180},
    "facing=north,half=bottom,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "y": 270},
    "facing=north,half=bottom,shape=straight": {"model": "@STAIRS@", "uvlock": true, "y": 270},
    "facing=north,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 270},
    "facing=north,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180},
    "facing=north,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 270},
    "facing=north,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180},
    "facing=north,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180, "y": 270},
    "facing=south,half=bottom,shape=inner_left": {"model": "@INNER@"},
    "facing=south,half=bottom,shape=inner_right": {"model": "@INNER@", "uvlock": true, "y": 90},
    "facing=south,half=bottom,shape=outer_left": {"model": "@OUTER@"},
    "facing=south,half=bottom,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "y": 90},
    "facing=south,half=bottom,shape=straight": {"model": "@STAIRS@", "uvlock": true, "y": 90},
    "facing=south,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 90},
    "facing=south,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 180},
    "facing=south,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 90},
    "facing=south,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 180},
    "facing=south,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180, "y": 90},
    "facing=west,half=bottom,shape=inner_left": {"model": "@INNER@", "uvlock": true, "y": 90},
    "facing=west,half=bottom,shape=inner_right": {"model": "@INNER@", "uvlock": true, "y": 180},
    "facing=west,half=bottom,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "y": 90},
    "facing=west,half=bottom,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "y": 180},
    "facing=west,half=bottom,shape=straight": {"model": "@STAIRS@", "uvlock": true, "y": 180},
    "facing=west,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 180},
    "facing=west,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 270},
    "facing=west,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 180},
    "facing=west,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 270},
    "facing=west,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180, "y": 180}
  }
}
"""

WALL_BLOCKSTATE_TEMPLATE = """
{
  "multipart": [
    {"apply": {"model": "@WALL_POST@"}, "when": {"up": "true"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true}, "when": {"north": "low"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true, "y": 90}, "when": {"east": "low"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true, "y": 180}, "when": {"south": "low"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true, "y": 270}, "when": {"west": "low"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true}, "when": {"north": "tall"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true, "y": 90}, "when": {"east": "tall"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true, "y": 180}, "when": {"south": "tall"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true, "y": 270}, "when": {"west": "tall"}}
  ]
}
"""

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


# ---------------------------------------------------------------------------
# Loot emitters (vanilla formats, verbatim from cinderstone_gen.py)
# ---------------------------------------------------------------------------


def loot_drop_self(name: str) -> dict:
    """Drop-self survives_explosion loot table (vanilla stone.json format)."""
    return {_lt(name): {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "conditions": [{"condition": "minecraft:survives_explosion"}],
            "entries": [{"type": "minecraft:item", "name": f"{NS}:{name}"}],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/{name}",
    }}


def loot_slab(name: str) -> dict:
    """Vanilla slab loot table (drop self, x2 when type=double)."""
    return {_lt(name): {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "entries": [{
                "type": "minecraft:item",
                "functions": [
                    {
                        "add": False,
                        "conditions": [{
                            "block": f"{NS}:{name}",
                            "condition": "minecraft:block_state_property",
                            "properties": {"type": "double"},
                        }],
                        "count": 2.0,
                        "function": "minecraft:set_count",
                    },
                    {"function": "minecraft:explosion_decay"},
                ],
                "name": f"{NS}:{name}",
            }],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/{name}",
    }}


# ---------------------------------------------------------------------------
# Shape emitters. Each returns {relative-resource-path: obj} including the
# matching loot table; formats are verbatim copies of the v3 generators.
# ---------------------------------------------------------------------------


def emit_cube(name: str, texture: str | None = None) -> dict:
    """Simple full cube: single-variant blockstate + cube_all model + item def + loot."""
    tex = block_ref(texture or name)
    return merge({
        _bs(name): {"variants": {"": {"model": block_ref(name)}}},
        _bm(name): {"parent": "minecraft:block/cube_all", "textures": {"all": tex}},
        _it(name): item_def(block_ref(name)),
    }, loot_drop_self(name))


def emit_slab(name: str, base: str, texture: str | None = None) -> dict:
    """Slab of the `base` cube (vanilla stone_brick_slab format; double state reuses the
    base cube model) with the double-drop loot table."""
    tex = block_ref(texture or base)
    tex3 = {"bottom": tex, "side": tex, "top": tex}
    return merge({
        _bm(name): {"parent": "minecraft:block/slab", "textures": tex3},
        _bm(f"{name}_top"): {"parent": "minecraft:block/slab_top", "textures": tex3},
        _bs(name): {"variants": {
            "type=bottom": {"model": block_ref(name)},
            "type=double": {"model": block_ref(base)},
            "type=top": {"model": block_ref(f"{name}_top")},
        }},
        _it(name): item_def(block_ref(name)),
    }, loot_slab(name))


def emit_stairs(name: str, texture: str) -> dict:
    """Stairs textured with block/<texture> (vanilla stone_brick_stairs format)."""
    tex = block_ref(texture)
    tex3 = {"bottom": tex, "side": tex, "top": tex}
    state = (STAIRS_BLOCKSTATE_TEMPLATE
             .replace("@STAIRS@", block_ref(name))
             .replace("@INNER@", block_ref(f"{name}_inner"))
             .replace("@OUTER@", block_ref(f"{name}_outer")))
    return merge({
        _bm(name): {"parent": "minecraft:block/stairs", "textures": tex3},
        _bm(f"{name}_inner"): {"parent": "minecraft:block/inner_stairs", "textures": tex3},
        _bm(f"{name}_outer"): {"parent": "minecraft:block/outer_stairs", "textures": tex3},
        _bs(name): json.loads(state),
        _it(name): item_def(block_ref(name)),
    }, loot_drop_self(name))


def emit_wall(name: str, texture: str) -> dict:
    """Wall textured with block/<texture> (vanilla stone_brick_wall format; the item uses
    the wall_inventory model)."""
    wall_tex = {"wall": block_ref(texture)}
    files = {}
    for suffix, parent in (("post", "template_wall_post"), ("side", "template_wall_side"),
                           ("side_tall", "template_wall_side_tall"), ("inventory", "wall_inventory")):
        files[_bm(f"{name}_{suffix}")] = {"parent": f"minecraft:block/{parent}", "textures": wall_tex}
    state = (WALL_BLOCKSTATE_TEMPLATE
             .replace("@WALL_POST@", block_ref(f"{name}_post"))
             .replace("@WALL_SIDE_TALL@", block_ref(f"{name}_side_tall"))
             .replace("@WALL_SIDE@", block_ref(f"{name}_side")))
    files[_bs(name)] = json.loads(state)
    files[_it(name)] = item_def(block_ref(f"{name}_inventory"))
    return merge(files, loot_drop_self(name))


def emit_cube_family(base: str, stem: str, texture: str | None = None) -> dict:
    """Base cube + <stem>_slab + <stem>_stairs + <stem>_wall (all shapes textured with
    block/<base>), exactly like cinderstone_gen.emit_family, loot included."""
    tex = texture or base
    return merge(
        emit_cube(base, tex),
        emit_slab(f"{stem}_slab", base, tex),
        emit_stairs(f"{stem}_stairs", tex),
        emit_wall(f"{stem}_wall", tex),
    )


def emit_pillar(name: str) -> dict:
    """Axis pillar exactly like vanilla basalt: cube_column model over <name>_top /
    <name>_side textures, one model rotated for all three axes."""
    return merge({
        _bs(name): {"variants": {
            "axis=x": {"model": block_ref(name), "x": 90, "y": 90},
            "axis=y": {"model": block_ref(name)},
            "axis=z": {"model": block_ref(name), "x": 90},
        }},
        _bm(name): {
            "parent": "minecraft:block/cube_column",
            "textures": {"end": block_ref(f"{name}_top"), "side": block_ref(f"{name}_side")},
        },
        _it(name): item_def(block_ref(name)),
    }, loot_drop_self(name))


def emit_lamp(name: str) -> dict:
    """Lamp: plain glowing cube (asset-wise identical to emit_cube; the light level lives
    on the Block settings, not in the assets)."""
    return emit_cube(name)


def emit_glass(name: str) -> dict:
    """Glass cube: same asset set as a plain cube (the transparency lives in the texture
    alpha + the TransparentBlock class)."""
    return emit_cube(name)


def emit_pane(name: str, glass: str) -> dict:
    """Glass pane over the `glass` cube texture (vanilla glass_pane format: multipart
    blockstate + 5 pane models + flat item/generated sprite)."""
    m = block_ref(name)
    pane_tex = block_ref(glass)
    edge_tex = block_ref(f"{name}_top")
    return merge({
        _bs(name): {"multipart": [
            {"apply": {"model": f"{m}_post"}},
            {"apply": {"model": f"{m}_side"}, "when": {"north": "true"}},
            {"apply": {"model": f"{m}_side", "y": 90}, "when": {"east": "true"}},
            {"apply": {"model": f"{m}_side_alt"}, "when": {"south": "true"}},
            {"apply": {"model": f"{m}_side_alt", "y": 90}, "when": {"west": "true"}},
            {"apply": {"model": f"{m}_noside"}, "when": {"north": "false"}},
            {"apply": {"model": f"{m}_noside_alt"}, "when": {"east": "false"}},
            {"apply": {"model": f"{m}_noside_alt", "y": 90}, "when": {"south": "false"}},
            {"apply": {"model": f"{m}_noside", "y": 270}, "when": {"west": "false"}},
        ]},
        _bm(f"{name}_post"): {"parent": "minecraft:block/template_glass_pane_post",
                              "textures": {"edge": edge_tex, "pane": pane_tex}},
        _bm(f"{name}_side"): {"parent": "minecraft:block/template_glass_pane_side",
                              "textures": {"edge": edge_tex, "pane": pane_tex}},
        _bm(f"{name}_side_alt"): {"parent": "minecraft:block/template_glass_pane_side_alt",
                                  "textures": {"edge": edge_tex, "pane": pane_tex}},
        _bm(f"{name}_noside"): {"parent": "minecraft:block/template_glass_pane_noside",
                                "textures": {"pane": pane_tex}},
        _bm(f"{name}_noside_alt"): {"parent": "minecraft:block/template_glass_pane_noside_alt",
                                    "textures": {"pane": pane_tex}},
        _im(name): {"parent": "minecraft:item/generated", "textures": {"layer0": pane_tex}},
        _it(name): item_def(f"{NS}:item/{name}"),
    }, loot_drop_self(name))


def emit_lantern(name: str) -> dict:
    """Lantern (vanilla format: hanging=true/false blockstate + template models + flat
    item sprite from textures/item/<name>.png)."""
    return merge({
        _bs(name): {"variants": {
            "hanging=false": {"model": block_ref(name)},
            "hanging=true": {"model": block_ref(f"{name}_hanging")},
        }},
        _bm(name): {"parent": "minecraft:block/template_lantern",
                    "textures": {"lantern": block_ref(name)}},
        _bm(f"{name}_hanging"): {"parent": "minecraft:block/template_hanging_lantern",
                                 "textures": {"lantern": block_ref(name)}},
        _im(name): {"parent": "minecraft:item/generated",
                    "textures": {"layer0": f"{NS}:item/{name}"}},
        _it(name): item_def(f"{NS}:item/{name}"),
    }, loot_drop_self(name))


def emit_fence(name: str, texture: str) -> dict:
    """Fence over block/<texture> (vanilla oak_fence format; item uses fence_inventory)."""
    tex = {"texture": block_ref(texture)}
    return merge({
        _bs(name): json.loads(FENCE_BLOCKSTATE_TEMPLATE.replace("@FENCE@", block_ref(name))),
        _bm(f"{name}_post"): {"parent": "minecraft:block/fence_post", "textures": tex},
        _bm(f"{name}_side"): {"parent": "minecraft:block/fence_side", "textures": tex},
        _bm(f"{name}_inventory"): {"parent": "minecraft:block/fence_inventory", "textures": tex},
        _it(name): item_def(block_ref(f"{name}_inventory")),
    }, loot_drop_self(name))


def emit_fence_gate(name: str, texture: str) -> dict:
    """Fence gate over block/<texture> (vanilla oak_fence_gate format: 16-variant
    blockstate + 4 template_fence_gate* models)."""
    tex = {"texture": block_ref(texture)}
    files = {
        _bs(name): json.loads(FENCE_GATE_BLOCKSTATE_TEMPLATE.replace("@GATE@", block_ref(name))),
        _it(name): item_def(block_ref(name)),
    }
    for suffix, parent in (("", "minecraft:block/template_fence_gate"),
                           ("_open", "minecraft:block/template_fence_gate_open"),
                           ("_wall", "minecraft:block/template_fence_gate_wall"),
                           ("_wall_open", "minecraft:block/template_fence_gate_wall_open")):
        files[_bm(f"{name}{suffix}")] = {"parent": parent, "textures": tex}
    return merge(files, loot_drop_self(name))


def emit_button(name: str, texture: str) -> dict:
    """Button over block/<texture> (vanilla stone_button format: 24-variant blockstate +
    button/button_pressed/button_inventory models; item uses the inventory model)."""
    tex = {"texture": block_ref(texture)}
    return merge({
        _bs(name): json.loads(BUTTON_BLOCKSTATE_TEMPLATE.replace("@BTN@", block_ref(name))),
        _bm(name): {"parent": "minecraft:block/button", "textures": tex},
        _bm(f"{name}_pressed"): {"parent": "minecraft:block/button_pressed", "textures": tex},
        _bm(f"{name}_inventory"): {"parent": "minecraft:block/button_inventory", "textures": tex},
        _it(name): item_def(block_ref(f"{name}_inventory")),
    }, loot_drop_self(name))


def emit_pressure_plate(name: str, texture: str) -> dict:
    """Pressure plate over block/<texture> (vanilla stone_pressure_plate format:
    powered=false/true blockstate + pressure_plate_up/_down models)."""
    tex = {"texture": block_ref(texture)}
    return merge({
        _bs(name): {"variants": {
            "powered=false": {"model": block_ref(name)},
            "powered=true": {"model": block_ref(f"{name}_down")},
        }},
        _bm(name): {"parent": "minecraft:block/pressure_plate_up", "textures": tex},
        _bm(f"{name}_down"): {"parent": "minecraft:block/pressure_plate_down", "textures": tex},
        _it(name): item_def(block_ref(name)),
    }, loot_drop_self(name))


# ---------------------------------------------------------------------------
# Vanilla texture extraction + recoloring
# ---------------------------------------------------------------------------

_client_jar = None


def _find_client_jar() -> Path:
    candidates = sorted(glob.glob(str(Path("~/.gradle/caches/fabric-loom/*/minecraft-client.jar").expanduser())))
    if not candidates:
        raise FileNotFoundError(
            "minecraft-client.jar not found under ~/.gradle/caches/fabric-loom/*/ "
            "(run any ./gradlew task once to populate the loom cache)")
    return Path(candidates[-1])


def extract_vanilla(path_in_jar: str) -> Image.Image:
    """Read one file (usually a texture PNG) straight out of the vanilla client jar,
    e.g. extract_vanilla("assets/minecraft/textures/block/stone.png")."""
    global _client_jar
    if _client_jar is None:
        _client_jar = zipfile.ZipFile(_find_client_jar())
    data = _client_jar.read(path_in_jar)
    return Image.open(io.BytesIO(data)).convert("RGBA")


def recolor(img: Image.Image, palette) -> Image.Image:
    """Remap an image onto a dark->light color ramp by per-pixel luminance, keeping the
    alpha channel. `palette` is a sequence of RGB tuples ordered darkest to lightest."""
    palette = list(palette)
    if len(palette) < 2:
        palette = palette * 2
    src = img.convert("RGBA")
    out = Image.new("RGBA", src.size)
    spx, opx = src.load(), out.load()
    steps = len(palette) - 1
    for y in range(src.size[1]):
        for x in range(src.size[0]):
            r, g, b, a = spx[x, y]
            lum = (0.299 * r + 0.587 * g + 0.114 * b) / 255.0
            pos = lum * steps
            i = min(int(pos), steps - 1)
            t = pos - i
            c0, c1 = palette[i], palette[i + 1]
            opx[x, y] = (round(c0[0] + (c1[0] - c0[0]) * t),
                         round(c0[1] + (c1[1] - c0[1]) * t),
                         round(c0[2] + (c1[2] - c0[2]) * t), a)
    return out


# ---------------------------------------------------------------------------
# Texture primitives (16x16, deterministic). Ports of the cinderstone_gen /
# infernoflora_gen painters, parameterized on palettes. Seed with seeded(name).
# ---------------------------------------------------------------------------


def seeded(name: str) -> Random:
    """Deterministic RNG per texture name (namespaced like the v3 generators)."""
    return Random(f"{NS}:{name}")


def noise_cube(rng: Random, shades) -> Image.Image:
    """Base fill with CLUSTERED 2x2 mottling plus sparse single-pixel accents
    (cinderstone_gen.new_canvas: structured shading, not per-pixel static)."""
    img = Image.new("RGB", (16, 16))
    cells = {(cx, cy): rng.choice(shades) for cy in range(8) for cx in range(8)}
    for y in range(16):
        for x in range(16):
            img.putpixel((x, y), cells[(x // 2, y // 2)])
    for y in range(16):
        for x in range(16):
            if rng.random() < 0.06:
                img.putpixel((x, y), rng.choice(shades))
    return img


def sprinkle(img: Image.Image, rng: Random, pixels, colors, prob: float) -> None:
    for x, y in pixels:
        if rng.random() < prob:
            img.putpixel((x, y), rng.choice(colors))


def brick_overlay(rng: Random, shades, mortar, accents=None, accent_prob: float = 0.0) -> Image.Image:
    """Running-bond bricks (cinderstone_gen.brick_texture): 4px courses, 8px bricks,
    1px mortar; optional glowing accent pixels along the mortar seams."""
    img = noise_cube(rng, shades)
    seams = []
    for y in range(16):
        row = y // 4
        offset = (row % 2) * 4
        for x in range(16):
            if y % 4 == 3 or (x - offset) % 8 == 7:
                img.putpixel((x, y), mortar)
                seams.append((x, y))
    if accent_prob > 0 and accents:
        sprinkle(img, rng, seams, accents, accent_prob)
    return img


def tile_overlay(rng: Random, shades, grout, accents=None, accent_prob: float = 0.0) -> Image.Image:
    """2x2 grid of 8x8 tiles (cinderstone_gen.tex_cinderstone_tiles): dark grout lines
    with optional accent dots along them and at the crossings."""
    img = noise_cube(rng, shades)
    lines = []
    for y in range(16):
        for x in range(16):
            if x % 8 == 7 or y % 8 == 7:
                img.putpixel((x, y), grout)
                lines.append((x, y))
    if accent_prob > 0 and accents:
        sprinkle(img, rng, lines, accents, accent_prob)
        for x, y in [(7, 7), (15, 7), (7, 15), (15, 15)]:
            img.putpixel((x, y), accents[0])
    return img


def pillar_side(rng: Random, shades, mortar, accents, seam_prob: float) -> Image.Image:
    """Vertical column striping with two accent seams (cinderstone_gen.pillar_side,
    basalt-side style). `shades` is dark->light (>= 3 entries used)."""
    dark, mid, light = shades[0], shades[1], shades[-1]
    img = Image.new("RGB", (16, 16))
    for x in range(16):
        if x in (0, 15):
            column = [mortar]
        elif x in (1, 7, 8, 14):
            column = [dark, dark, mid]
        else:
            column = [mid, mid, light, dark]
        shade = rng.choice(column)
        for y in range(16):
            if y % 2 == 0:
                shade = rng.choice(column)
            img.putpixel((x, y), shade)
    seam_px = [(x, y) for x in (4, 11) for y in range(16)]
    sprinkle(img, rng, seam_px, accents, seam_prob)
    return img


def pillar_top(rng: Random, shades, mortar, accents) -> Image.Image:
    """Framed top face with an accent inner ring and bright centre
    (cinderstone_gen.pillar_top, basalt-top style)."""
    img = noise_cube(rng, shades)
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), mortar)
    ring = []
    for i in range(3, 13):
        ring += [(i, 3), (i, 12), (3, i), (12, i)]
    for x, y in ring:
        img.putpixel((x, y), shades[0])
    sprinkle(img, rng, ring, accents, 0.3)
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), accents[-1])
    return img


def glass_frame(rng: Random, frame_dark, frame_mid, tint, glow=None) -> Image.Image:
    """2px stone frame, semi-transparent tinted center with real RGBA alpha
    (cinderstone_gen.glass_texture)."""
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(16):
        for x in range(16):
            edge = min(x, y, 15 - x, 15 - y)
            if edge == 0:
                px[x, y] = (*frame_dark, 255)
            elif edge == 1:
                px[x, y] = (*frame_mid, 255) if (x * 31 + y * 17) % 5 else (*frame_dark, 255)
            else:
                if glow is not None and (x * 7 + y * 11) % 9 == 0:
                    px[x, y] = (*glow, 150)
                elif (x + y) % 7 == 0:
                    px[x, y] = (*tint, 110)
                else:
                    px[x, y] = (*tint, 70)
    return img


def pane_edge(rng: Random, mid, dark, darker) -> Image.Image:
    """Edge texture (<pane>_top.png) for pane post/side models: 2px opaque strip at
    columns 7-8 (cinderstone_gen.pane_top)."""
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(16):
        for x in (7, 8):
            if y in (0, 15):
                px[x, y] = (*darker, 255)
            else:
                px[x, y] = (*mid, 255) if (x * 31 + y * 17) % 5 < 3 else (*dark, 255)
    return img


def lamp_glow(rng: Random, frame_shades, frame_line, cells) -> Image.Image:
    """Warm 3x3-cell grid lamp: stone frame lines, glowing cells
    (cinderstone_gen.lamp_texture)."""
    img = noise_cube(rng, frame_shades)
    for y in range(16):
        for x in range(16):
            if x in (0, 15) or y in (0, 15) or x in (5, 10) or y in (5, 10):
                if not (x in (0, 15) or y in (0, 15)):
                    img.putpixel((x, y), frame_line)
            else:
                img.putpixel((x, y), cells[(x * 31 + y * 17) % len(cells)])
    return img


def plank_grain(rng: Random, base, light, seam, fleck=None, fleck_count: int = 0) -> Image.Image:
    """Planks: 4px courses with deep seams, staggered vertical joints and optional
    accent flecks (infernoflora_gen.make_planks, parameterized colors)."""
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        course, ly = divmod(y, 4)
        for x in range(16):
            if ly == 3:
                c = seam
            elif ly == 0:
                c = light
            else:
                c = base
                if (x + 6 * course) % 8 == 3:  # staggered vertical joints
                    c = seam
            px[x, y] = _jitter(rng, c, 3)
    if fleck is not None:
        for _ in range(fleck_count):
            px[rng.randrange(16), rng.randrange(16)] = fleck
    return img


def log_rings(rng: Random, ring_light, ring_dark, bark_dark, heart=None) -> Image.Image:
    """Log/stem end face: 2px bark rim, concentric growth rings, optional glowing heart
    pixels (infernoflora_gen.make_stem_top)."""
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            e = min(x, y, 15 - x, 15 - y)
            if e <= 1:
                base = bark_dark if (x + y) % 3 else _shade(bark_dark, 10)
            else:
                d = max(abs(x - 7.5), abs(y - 7.5))  # square rings
                base = ring_light if int(d) % 2 == 0 else ring_dark
            px[x, y] = _jitter(rng, base, 3)
    if heart is not None:
        for x, y in ((7, 7), (8, 7), (7, 8), (8, 8)):
            px[x, y] = heart
    return img


def bark_side(rng: Random, base, dark, light, crack=None, crack_bright=None) -> Image.Image:
    """Log side: vertical bark ridges with horizontal breaks and optional glowing cracks
    in the crevices (infernoflora_gen.make_bark, parameterized colors)."""
    img = Image.new("RGB", (16, 16))
    px = img.load()
    col = [rng.choice((-1, 0, 0, 1)) for _ in range(16)]
    for x in range(16):
        breaks = {rng.randrange(16) for _ in range(3)}  # horizontal bark breaks
        for y in range(16):
            if col[x] < 0:
                c = dark
            elif col[x] > 0:
                c = light
            else:
                c = base
            if y in breaks:
                c = _shade(c, -14)
            px[x, y] = _jitter(rng, c, 3)
    if crack is not None:
        for _ in range(3):
            cx = rng.randrange(16)
            cy = rng.randrange(2, 9)
            length = rng.randint(3, 6)
            for i in range(length):
                y = (cy + i) % 16
                px[cx, y] = (crack_bright or crack) if i == length // 2 else crack
    return img


def _clamp(v: int) -> int:
    return max(0, min(255, v))


def _shade(rgb, amount):
    return (_clamp(rgb[0] + amount), _clamp(rgb[1] + amount), _clamp(rgb[2] + amount))


def _jitter(rng: Random, rgb, amount=3):
    d = rng.randint(-amount, amount)
    return (_clamp(rgb[0] + d), _clamp(rgb[1] + d), _clamp(rgb[2] + d))


# ---------------------------------------------------------------------------
# Self-test (`python3 devtools/gen/lib_gen.py`): validates every emitter produces
# parseable JSON and every texture primitive paints a 16x16 image. Writes NOTHING.
# ---------------------------------------------------------------------------


def _self_test() -> None:
    emitted = merge(
        emit_cube_family("testium_bricks", "testium_brick"),
        emit_cube("testium_block"),
        emit_slab("testium_slab", "testium_block"),
        emit_stairs("testium_stairs", "testium_block"),
        emit_wall("testium_wall", "testium_block"),
        emit_pillar("testium_pillar"),
        emit_lamp("testium_lamp"),
        emit_lantern("testium_lantern"),
        emit_glass("testium_glass"),
        emit_pane("testium_glass_pane", "testium_glass"),
        emit_fence("testium_fence", "testium_planks"),
        emit_fence_gate("testium_fence_gate", "testium_planks"),
        emit_button("testium_button", "testium_planks"),
        emit_pressure_plate("testium_pressure_plate", "testium_planks"),
    )
    for rel, obj in emitted.items():
        rebuilt = json.loads(json.dumps(obj, indent=2, sort_keys=True, ensure_ascii=False))
        assert rebuilt == obj, f"JSON round-trip mismatch for {rel}"
        assert rel.startswith((f"assets/{NS}/", f"data/{NS}/")), f"unexpected path {rel}"
    blockstates = [p for p in emitted if "/blockstates/" in p]
    items = [p for p in emitted if f"assets/{NS}/items/" in p]
    loot = [p for p in emitted if "/loot_table/" in p]
    assert len(blockstates) == len(items) == len(loot) == 17, \
        f"expected 17 ids, got {len(blockstates)}/{len(items)}/{len(loot)}"

    dark, mid, light = (0x2B, 0x22, 0x26), (0x3D, 0x2C, 0x2E), (0x4C, 0x38, 0x3B)
    mortar = (0x1C, 0x15, 0x18)
    ember = (0xE2, 0x58, 0x22)
    textures = {
        "noise_cube": noise_cube(seeded("t1"), [dark, mid, mid, light]),
        "brick_overlay": brick_overlay(seeded("t2"), [dark, mid, mid, light], mortar,
                                       accents=[ember], accent_prob=0.1),
        "tile_overlay": tile_overlay(seeded("t3"), [dark, mid, mid, light], mortar,
                                     accents=[ember], accent_prob=0.05),
        "pillar_side": pillar_side(seeded("t4"), [dark, mid, mid, light], mortar, [ember], 0.3),
        "pillar_top": pillar_top(seeded("t5"), [dark, mid, mid, light], mortar, [ember]),
        "glass_frame": glass_frame(seeded("t6"), mortar, mid, (0x6B, 0x5A, 0x5E), glow=ember),
        "pane_edge": pane_edge(seeded("t7"), mid, dark, mortar),
        "lamp_glow": lamp_glow(seeded("t8"), [dark, mid], mortar, [ember, light]),
        "plank_grain": plank_grain(seeded("t9"), mid, light, mortar, fleck=ember, fleck_count=2),
        "log_rings": log_rings(seeded("t10"), light, mid, dark, heart=ember),
        "bark_side": bark_side(seeded("t11"), mid, dark, light, crack=ember),
    }
    for name, img in textures.items():
        assert img.size == (16, 16), f"{name} is {img.size}, expected 16x16"
    # Determinism: same seed name -> identical bytes.
    a = noise_cube(seeded("t1"), [dark, mid, mid, light]).tobytes()
    b = noise_cube(seeded("t1"), [dark, mid, mid, light]).tobytes()
    assert a == b, "seeded primitives must be deterministic"

    recolored = recolor(textures["noise_cube"], [dark, mid, light])
    assert recolored.size == (16, 16) and recolored.mode == "RGBA"

    try:
        stone = extract_vanilla("assets/minecraft/textures/block/stone.png")
        assert stone.size[0] == 16
        jar_note = "extract_vanilla OK"
    except FileNotFoundError:
        jar_note = "extract_vanilla SKIPPED (no loom cache jar)"

    print(f"lib_gen self-test: {len(emitted)} JSON files round-tripped for 17 ids, "
          f"{len(textures)} texture primitives painted, recolor OK, {jar_note}. "
          "Nothing was written.")


if __name__ == "__main__":
    _self_test()
