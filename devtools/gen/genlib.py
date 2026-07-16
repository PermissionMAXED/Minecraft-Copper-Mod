#!/usr/bin/env python3
"""genlib — shared asset-generation library for COPPER INFERNO feature gen scripts.

Extracted from the PROVEN emitters in devtools/gen/cinderstone_gen.py (block/loot/recipe
emitters + texture helpers), devtools/gen/materials_gen.py (item-only emitters) and
devtools/gen/infernomobs_gen.py (item def/model pattern shared by spawn eggs & drops).
Every JSON structure is byte-identical to what those scripts emit; the underlying formats
are exact copies of the vanilla 1.21.9 formats extracted from
~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar. Do NOT "improve" them.

This is a LIBRARY: importing it has no side effects and nothing writes into
src/main/resources unless the caller passes those paths. Feature gen scripts should keep
the repo convention of gating JSON emission behind --write-json (PNG textures are always
written; the JSON in src/main/resources stays authoritative).

Path conventions (callers usually pass these):
    assets  = RES / "assets" / NS          (genlib.ASSETS)
    data    = RES / "data" / NS            (genlib.DATA)
    recipes = DATA / "recipe" / "<feature>"

Determinism: all texture noise must be seeded per texture name. Use
rng_for("<texture name>") (== Random(f"copper_inferno:<name>")) so re-runs produce
byte-identical PNGs, exactly like the existing gen scripts.

1.21.9 asset contract reminder (enforced by devtools/audit_assets.py): every item —
including every BlockItem and spawn egg — needs BOTH assets/<ns>/items/<id>.json (the
model-definition; emit_item_def or the block emitters below) AND the referenced model
under assets/<ns>/models/ (emit_item_model or the block model emitters). Every block
needs a blockstate + a loot table data/<ns>/loot_table/blocks/<id>.json.
"""

import json
from pathlib import Path
from random import Random

from PIL import Image

NS = "copper_inferno"
ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# House palette shared by the texture helpers' defaults (values identical to
# cinderstone_gen.py: ember accents + the fissure black used by cracked()).
EMBER = (0xE2, 0x58, 0x22)
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)
EMBER_HOT = (0xFF, 0xB1, 0x6B)
FISSURE = (0x14, 0x0B, 0x0E)


# ---------------------------------------------------------------------------
# Core JSON helpers
# ---------------------------------------------------------------------------

def write_json(path: Path, obj) -> None:
    """Deterministic JSON write: 2-space indent, sorted keys, raw unicode, trailing \\n.

    Byte-identical to the write_json in cinderstone_gen.py (with WRITE_JSON on).
    """
    path = Path(path)
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True, ensure_ascii=False) + "\n",
                    encoding="utf-8")


def block_ref(name: str) -> str:
    return f"{NS}:block/{name}"


def item_def(model_ref: str) -> dict:
    """items/<id>.json body: the 1.21.9 item model-definition."""
    return {"model": {"type": "minecraft:model", "model": model_ref}}


def rng_for(name: str) -> Random:
    """Per-texture seeded Random; the ONLY approved seeding scheme for textures."""
    return Random(f"{NS}:{name}")


# ---------------------------------------------------------------------------
# Vanilla blockstate templates (exact structure from minecraft-client.jar 1.21.9:
# stone_brick_stairs blockstate, brick_wall multipart) — copied from cinderstone_gen.py.
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


# ---------------------------------------------------------------------------
# Block emitters (vanilla formats; assets = .../assets/copper_inferno)
# ---------------------------------------------------------------------------

def emit_cube(assets: Path, name: str) -> None:
    """Blockstate + cube_all model + item definition for a simple full cube."""
    write_json(assets / "blockstates" / f"{name}.json",
               {"variants": {"": {"model": block_ref(name)}}})
    write_json(assets / "models" / "block" / f"{name}.json",
               {"parent": "minecraft:block/cube_all", "textures": {"all": block_ref(name)}})
    write_json(assets / "items" / f"{name}.json", item_def(block_ref(name)))


def emit_family(assets: Path, base: str, stem: str) -> None:
    """Base cube + <stem>_slab + <stem>_stairs + <stem>_wall (vanilla stone_bricks family)."""
    emit_cube(assets, base)
    tex = block_ref(base)
    tex3 = {"bottom": tex, "side": tex, "top": tex}

    # Slab (vanilla stone_brick_slab format; double state reuses the base cube model).
    slab = f"{stem}_slab"
    write_json(assets / "models" / "block" / f"{slab}.json",
               {"parent": "minecraft:block/slab", "textures": tex3})
    write_json(assets / "models" / "block" / f"{slab}_top.json",
               {"parent": "minecraft:block/slab_top", "textures": tex3})
    write_json(assets / "blockstates" / f"{slab}.json", {"variants": {
        "type=bottom": {"model": block_ref(slab)},
        "type=double": {"model": block_ref(base)},
        "type=top": {"model": block_ref(f"{slab}_top")},
    }})
    write_json(assets / "items" / f"{slab}.json", item_def(block_ref(slab)))

    # Stairs (vanilla stone_brick_stairs format).
    stairs = f"{stem}_stairs"
    write_json(assets / "models" / "block" / f"{stairs}.json",
               {"parent": "minecraft:block/stairs", "textures": tex3})
    write_json(assets / "models" / "block" / f"{stairs}_inner.json",
               {"parent": "minecraft:block/inner_stairs", "textures": tex3})
    write_json(assets / "models" / "block" / f"{stairs}_outer.json",
               {"parent": "minecraft:block/outer_stairs", "textures": tex3})
    stairs_state = (STAIRS_BLOCKSTATE_TEMPLATE
                    .replace("@STAIRS@", block_ref(stairs))
                    .replace("@INNER@", block_ref(f"{stairs}_inner"))
                    .replace("@OUTER@", block_ref(f"{stairs}_outer")))
    write_json(assets / "blockstates" / f"{stairs}.json", json.loads(stairs_state))
    write_json(assets / "items" / f"{stairs}.json", item_def(block_ref(stairs)))

    # Wall (vanilla stone_brick_wall format; item uses the wall_inventory model).
    wall = f"{stem}_wall"
    wall_tex = {"wall": tex}
    for suffix, parent in (("post", "template_wall_post"), ("side", "template_wall_side"),
                           ("side_tall", "template_wall_side_tall"), ("inventory", "wall_inventory")):
        write_json(assets / "models" / "block" / f"{wall}_{suffix}.json",
                   {"parent": f"minecraft:block/{parent}", "textures": wall_tex})
    wall_state = (WALL_BLOCKSTATE_TEMPLATE
                  .replace("@WALL_POST@", block_ref(f"{wall}_post"))
                  .replace("@WALL_SIDE_TALL@", block_ref(f"{wall}_side_tall"))
                  .replace("@WALL_SIDE@", block_ref(f"{wall}_side")))
    write_json(assets / "blockstates" / f"{wall}.json", json.loads(wall_state))
    write_json(assets / "items" / f"{wall}.json", item_def(block_ref(f"{wall}_inventory")))


def emit_pillar(assets: Path, name: str) -> None:
    """Axis blockstate + cube_column model, exactly like vanilla basalt.

    Textures expected on disk: <name>_top.png and <name>_side.png (see pillar_side /
    pillar_top below).
    """
    write_json(assets / "blockstates" / f"{name}.json", {"variants": {
        "axis=x": {"model": block_ref(name), "x": 90, "y": 90},
        "axis=y": {"model": block_ref(name)},
        "axis=z": {"model": block_ref(name), "x": 90},
    }})
    write_json(assets / "models" / "block" / f"{name}.json", {
        "parent": "minecraft:block/cube_column",
        "textures": {"end": block_ref(f"{name}_top"), "side": block_ref(f"{name}_side")},
    })
    write_json(assets / "items" / f"{name}.json", item_def(block_ref(name)))


def emit_glass(assets: Path, name: str) -> None:
    """Vanilla glass: single-variant blockstate, cube_all, item def -> block model."""
    emit_cube(assets, name)


def emit_pane(assets: Path, name: str, glass: str) -> None:
    """Vanilla glass_pane: multipart blockstate + 5 pane models + flat item sprite.

    Textures expected on disk: <glass>.png (the pane face) and <name>_top.png (edge).
    """
    m = block_ref(name)
    write_json(assets / "blockstates" / f"{name}.json", {"multipart": [
        {"apply": {"model": f"{m}_post"}},
        {"apply": {"model": f"{m}_side"}, "when": {"north": "true"}},
        {"apply": {"model": f"{m}_side", "y": 90}, "when": {"east": "true"}},
        {"apply": {"model": f"{m}_side_alt"}, "when": {"south": "true"}},
        {"apply": {"model": f"{m}_side_alt", "y": 90}, "when": {"west": "true"}},
        {"apply": {"model": f"{m}_noside"}, "when": {"north": "false"}},
        {"apply": {"model": f"{m}_noside_alt"}, "when": {"east": "false"}},
        {"apply": {"model": f"{m}_noside_alt", "y": 90}, "when": {"south": "false"}},
        {"apply": {"model": f"{m}_noside", "y": 270}, "when": {"west": "false"}},
    ]})
    pane_tex = block_ref(glass)
    edge_tex = block_ref(f"{name}_top")
    write_json(assets / "models" / "block" / f"{name}_post.json",
               {"parent": "minecraft:block/template_glass_pane_post",
                "textures": {"edge": edge_tex, "pane": pane_tex}})
    write_json(assets / "models" / "block" / f"{name}_side.json",
               {"parent": "minecraft:block/template_glass_pane_side",
                "textures": {"edge": edge_tex, "pane": pane_tex}})
    write_json(assets / "models" / "block" / f"{name}_side_alt.json",
               {"parent": "minecraft:block/template_glass_pane_side_alt",
                "textures": {"edge": edge_tex, "pane": pane_tex}})
    write_json(assets / "models" / "block" / f"{name}_noside.json",
               {"parent": "minecraft:block/template_glass_pane_noside",
                "textures": {"pane": pane_tex}})
    write_json(assets / "models" / "block" / f"{name}_noside_alt.json",
               {"parent": "minecraft:block/template_glass_pane_noside_alt",
                "textures": {"pane": pane_tex}})
    # Vanilla glass_pane item: item/generated over the glass cube texture.
    write_json(assets / "models" / "item" / f"{name}.json",
               {"parent": "minecraft:item/generated", "textures": {"layer0": pane_tex}})
    write_json(assets / "items" / f"{name}.json", item_def(f"{NS}:item/{name}"))


def emit_lantern(assets: Path, name: str) -> None:
    """Vanilla lantern: hanging=true/false blockstate + template models + flat item.

    Textures expected on disk: block/<name>.png (template_lantern UV layout) and
    item/<name>.png (flat sprite).
    """
    write_json(assets / "blockstates" / f"{name}.json", {"variants": {
        "hanging=false": {"model": block_ref(name)},
        "hanging=true": {"model": block_ref(f"{name}_hanging")},
    }})
    write_json(assets / "models" / "block" / f"{name}.json",
               {"parent": "minecraft:block/template_lantern",
                "textures": {"lantern": block_ref(name)}})
    write_json(assets / "models" / "block" / f"{name}_hanging.json",
               {"parent": "minecraft:block/template_hanging_lantern",
                "textures": {"lantern": block_ref(name)}})
    write_json(assets / "models" / "item" / f"{name}.json",
               {"parent": "minecraft:item/generated",
                "textures": {"layer0": f"{NS}:item/{name}"}})
    write_json(assets / "items" / f"{name}.json", item_def(f"{NS}:item/{name}"))


# ---------------------------------------------------------------------------
# Loot emitters (data = .../data/copper_inferno)
# ---------------------------------------------------------------------------

def emit_drop_self_loot(data: Path, name: str) -> None:
    write_json(data / "loot_table" / "blocks" / f"{name}.json", {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "conditions": [{"condition": "minecraft:survives_explosion"}],
            "entries": [{"type": "minecraft:item", "name": f"{NS}:{name}"}],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/{name}",
    })


def emit_slab_loot(data: Path, name: str) -> None:
    """Vanilla slab loot (drop self, x2 when type=double)."""
    write_json(data / "loot_table" / "blocks" / f"{name}.json", {
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
    })


# ---------------------------------------------------------------------------
# Recipe emitters (vanilla formats: crafting_shaped/shapeless, smelting,
# stonecutting). recipes = the feature's recipe dir, e.g. DATA/"recipe"/"cinderstone".
# ---------------------------------------------------------------------------

def emit_shaped(recipes: Path, name: str, key: dict, pattern: list, result_id: str,
                count: int, category: str = "building") -> None:
    write_json(recipes / f"{name}.json", {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": result_id},
    })


def emit_shapeless(recipes: Path, name: str, ingredients: list, result_id: str,
                   count: int, category: str = "building") -> None:
    write_json(recipes / f"{name}.json", {
        "type": "minecraft:crafting_shapeless",
        "category": category,
        "ingredients": ingredients,
        "result": {"count": count, "id": result_id},
    })


def emit_smelting(recipes: Path, name: str, ingredient: str, result_id: str,
                  category: str = "blocks", cookingtime: int = 200,
                  experience: float = 0.1) -> None:
    # Format copied from vanilla cracked_stone_bricks.json (defaults match it exactly).
    write_json(recipes / f"{name}.json", {
        "type": "minecraft:smelting",
        "category": category,
        "cookingtime": cookingtime,
        "experience": experience,
        "ingredient": ingredient,
        "result": {"id": result_id},
    })


def emit_stonecutting(recipes: Path, name: str, ingredient: str, result_id: str,
                      count: int) -> None:
    # Format == vanilla stone_brick_wall_from_stone_bricks_stonecutting.json.
    write_json(recipes / f"{name}.json", {
        "type": "minecraft:stonecutting",
        "ingredient": ingredient,
        "result": {"count": count, "id": result_id},
    })


# ---------------------------------------------------------------------------
# Item emitters (the 1.21.9 two-file contract for plain sprite items)
# ---------------------------------------------------------------------------

def emit_item_def(assets: Path, item_id: str, model_ref: str | None = None) -> None:
    """assets/items/<id>.json model-definition (default model: <ns>:item/<id>)."""
    write_json(assets / "items" / f"{item_id}.json",
               item_def(model_ref or f"{NS}:item/{item_id}"))


def emit_item_model(assets: Path, item_id: str, texture_ref: str | None = None) -> None:
    """assets/models/item/<id>.json: item/generated with layer0 (default <ns>:item/<id>)."""
    write_json(assets / "models" / "item" / f"{item_id}.json", {
        "parent": "minecraft:item/generated",
        "textures": {"layer0": texture_ref or f"{NS}:item/{item_id}"},
    })


# ---------------------------------------------------------------------------
# Texture helpers (16x16, Pillow). Bodies are byte-identical to cinderstone_gen.py,
# with the look-name indirection replaced by explicit (shades, mortar) arguments.
# ALWAYS seed with rng_for(<texture name>) so output is deterministic.
# ---------------------------------------------------------------------------

def new_canvas(rng: Random, shades) -> Image.Image:
    """Base fill with CLUSTERED 2x2 mottling plus sparse single-pixel accents
    (structured shading rather than raw per-pixel static)."""
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


def brick_texture(rng: Random, shades, mortar, ember_prob: float = 0.0,
                  accents=None) -> Image.Image:
    """Running-bond bricks: 4px courses, 8px bricks, 1px mortar; optional glowing
    accent pixels along the mortar seams (default accents: the house ember pair)."""
    img = new_canvas(rng, shades)
    seams = []
    for y in range(16):
        row = y // 4
        offset = (row % 2) * 4
        for x in range(16):
            if y % 4 == 3 or (x - offset) % 8 == 7:
                img.putpixel((x, y), mortar)
                seams.append((x, y))
    if ember_prob > 0:
        sprinkle(img, rng, seams, accents or [EMBER, EMBER_BRIGHT], ember_prob)
    return img


def framed(rng: Random, shades, mortar) -> Image.Image:
    """1px mortar frame with a lighter inner bevel row — chiseled base plate."""
    img = new_canvas(rng, shades)
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), mortar)
        if 0 < i < 15:
            img.putpixel((i, 1), shades[-1])
    return img


def cracked(base_fn, rng: Random) -> Image.Image:
    """Base texture plus a jagged glowing fissure running top to bottom.

    base_fn(rng) paints the base; the fissure/ember colors are the house palette
    (FISSURE / EMBER / EMBER_BRIGHT), exactly like cinderstone's cracked bricks.
    """
    img = base_fn(rng)
    x = 6
    for y in range(16):
        x = max(1, min(14, x + rng.choice([-1, 0, 0, 1])))
        img.putpixel((x, y), FISSURE)
        if rng.random() < 0.4:
            img.putpixel((x + rng.choice([-1, 1]), y), EMBER)
        if rng.random() < 0.2:
            img.putpixel((x, y), EMBER_BRIGHT)
    return img


def pillar_side(rng: Random, shades, mortar, accents, seam_prob: float) -> Image.Image:
    """Vertical column striping with two accent seams (basalt-side style).

    shades is the 4-tuple/list (dark, mid, mid, light) used by the stone looks.
    """
    dark, mid, _, light = shades[0], shades[1], shades[2], shades[-1]
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
    """Framed top face with an accent inner ring and bright centre (basalt-top style)."""
    img = new_canvas(rng, shades)
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


# ---------------------------------------------------------------------------
# Lang fragments (merged into en_us.json / de_de.json by devtools/merge_lang.py)
# ---------------------------------------------------------------------------

def lang_fragments(assets: Path, feature: str, lang_en: dict,
                   lang_de: dict | None = None) -> None:
    """Writes assets/lang/fragments/<feature>.json (EN) and, when lang_de is given,
    assets/lang/fragments_de/<feature>.json (German)."""
    write_json(assets / "lang" / "fragments" / f"{feature}.json", lang_en)
    if lang_de is not None:
        write_json(assets / "lang" / "fragments_de" / f"{feature}.json", lang_de)


# ---------------------------------------------------------------------------
# Java codegen. Renders <Feature>.java / <Handbook>.java source with ONLY literal
# registration calls (plain string-literal ids) so devtools/audit_assets.py check (f)
# and devtools/check_handbook.py can parse them symbolically. Signatures match the
# real core APIs (core/ModBlocks.register, core/ModItems.register,
# core/ModBlockFamilies.registerCubeFamily, ItemGroupEvents + ModCreativeTab keys,
# core/handbook/HandbookEntries.add(new HandbookEntry(...))).
# ---------------------------------------------------------------------------

PKG_ROOT = "net.sonic0810.copperinferno"


def java_str(s: str) -> str:
    """A double-quoted Java string literal; non-ASCII escaped as \\uXXXX (matches the
    hand-written feature sources, e.g. German sharp s -> \\u00df)."""
    out = []
    for ch in s:
        if ch == "\\":
            out.append("\\\\")
        elif ch == '"':
            out.append('\\"')
        elif ch == "\n":
            out.append("\\n")
        elif ord(ch) < 0x20 or ord(ch) > 0x7E:
            out.append(f"\\u{ord(ch):04x}")
        else:
            out.append(ch)
    return '"' + "".join(out) + '"'


def _javadoc(doc_lines) -> str:
    body = "\n".join(f" * {line}".rstrip() for line in doc_lines)
    return f"/**\n{body}\n */"


def java_feature_class(feature_pkg: str, class_name: str, doc_lines,
                       *,
                       families=(),
                       blocks=(),
                       items=(),
                       settings_methods: str = "",
                       tabs=(),
                       extra_imports=(),
                       extra_init_lines=(),
                       handbook_class: str | None = None) -> str:
    """Renders a Feature.java source string for src/main/java/.../feature/<feature_pkg>/.

    Arguments:
      feature_pkg       package leaf, e.g. "cinderstone".
      class_name        e.g. "CinderStoneFeature".
      doc_lines         list of javadoc body lines (plain text, no leading ' * ').
      families          iterable of (FIELD, name, stem, settings_method, with_wall):
                        emits `public static BlockFamily FIELD;` and
                        `FIELD = ModBlockFamilies.registerCubeFamily(
                                "name", "stem", ClassName::settings_method, with_wall);`.
                        name/stem MUST be plain literals (audit check (f)).
      blocks            iterable of (FIELD, name, factory_expr, settings_expr):
                        emits `public static Block FIELD;` and
                        `FIELD = ModBlocks.register("name", factory_expr, settings_expr, true);`.
                        settings_expr MUST construct a FRESH AbstractBlock.Settings per
                        registration (e.g. a `stoneSettings()` helper call) — settings
                        instances must NEVER be shared between registrations.
      items             iterable of (FIELD, name, factory_expr, settings_expr):
                        emits `public static Item FIELD;` and
                        `FIELD = ModItems.register("name", factory_expr, settings_expr);`.
                        Same freshness rule: e.g. `new Item.Settings()` per line.
      settings_methods  verbatim Java source (tab-indented, `private static
                        AbstractBlock.Settings ...` helpers) inserted before init().
      tabs              iterable of (tab_key, entry_exprs): one
                        `ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.<tab_key>)
                        .register(entries -> { entries.add(<expr>); ... });`
                        block per pair. tab_key is any ModCreativeTab key field name
                        (e.g. "BLOCKS_KEY", "MAIN_KEY", "EQUIPMENT_KEY", "FOODS_KEY",
                        "MOBS_KEY", "NATURE_KEY", "SETS_KEY").
      extra_imports     fully-qualified imports for types referenced only inside
                        factory/settings expressions or verbatim code (e.g.
                        net.minecraft.block.MapColor, net.minecraft.sound.BlockSoundGroup,
                        net.minecraft.block.PillarBlock).
      extra_init_lines  verbatim Java lines (WITH leading tabs) inserted after the
                        registrations, before the item-group callbacks.
      handbook_class    when set, appends `<handbook_class>.register();` as the last
                        init() statement.
    """
    families, blocks, items = list(families), list(blocks), list(items)
    tabs = list(tabs)

    imports = set(extra_imports)
    if tabs:
        imports.add("net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents")
        imports.add(f"{PKG_ROOT}.core.ModCreativeTab")
    if settings_methods:
        imports.add("net.minecraft.block.AbstractBlock")
    if families:
        imports.add(f"{PKG_ROOT}.core.ModBlockFamilies")
        imports.add(f"{PKG_ROOT}.core.ModBlockFamilies.BlockFamily")
    if blocks:
        imports.add("net.minecraft.block.Block")
        imports.add(f"{PKG_ROOT}.core.ModBlocks")
    if items:
        imports.add("net.minecraft.item.Item")
        imports.add(f"{PKG_ROOT}.core.ModItems")

    out = [f"package {PKG_ROOT}.feature.{feature_pkg};", ""]
    out += [f"import {imp};" for imp in sorted(imports)]
    out += ["", _javadoc(doc_lines),
            f"public final class {class_name} {{",
            f"\tprivate {class_name}() {{",
            "\t}", ""]

    if families:
        out.append("\t// Full cube families (base + slab + stairs + wall).")
        out += [f"\tpublic static BlockFamily {field};" for field, *_ in families]
        out.append("")
    if blocks:
        out.append("\t// Single blocks.")
        out += [f"\tpublic static Block {field};" for field, *_ in blocks]
        out.append("")
    if items:
        out.append("\t// Items.")
        out += [f"\tpublic static Item {field};" for field, *_ in items]
        out.append("")

    if settings_methods:
        out.append(settings_methods.rstrip("\n"))
        out.append("")

    out.append("\tpublic static void init() {")
    for field, name, stem, settings_method, with_wall in families:
        out.append(f"\t\t{field} = ModBlockFamilies.registerCubeFamily(")
        out.append(f'\t\t\t\t"{name}", "{stem}", {class_name}::{settings_method}, '
                   f'{"true" if with_wall else "false"});')
    if families and (blocks or items):
        out.append("")
    for field, name, factory, settings in blocks:
        out.append(f'\t\t{field} = ModBlocks.register("{name}", {factory}, {settings}, true);')
    if blocks and items:
        out.append("")
    for field, name, factory, settings in items:
        out.append(f'\t\t{field} = ModItems.register("{name}", {factory}, {settings});')

    for line in extra_init_lines:
        out.append(line)

    for tab_key, entry_exprs in tabs:
        out.append("")
        out.append(f"\t\tItemGroupEvents.modifyEntriesEvent(ModCreativeTab.{tab_key})"
                   ".register(entries -> {")
        out += [f"\t\t\tentries.add({expr});" for expr in entry_exprs]
        out.append("\t\t});")

    if handbook_class:
        out.append("")
        out.append(f"\t\t{handbook_class}.register();")
    out += ["\t}", "}"]
    return "\n".join(out) + "\n"


def family_tab_entries(field: str, with_wall: bool = True) -> list:
    """Creative-tab entry expressions for one BlockFamily, in the vanilla family shape
    order (block -> stairs -> slab -> wall)."""
    exprs = [f"{field}.block()", f"{field}.stairs()", f"{field}.slab()"]
    if with_wall:
        exprs.append(f"{field}.wall()")
    return exprs


def java_handbook_class(feature_pkg: str, class_name: str, doc_lines, entries) -> str:
    """Renders a Handbook.java source string (package-private, one static register()).

    entries: iterable of 9-tuples mirroring core/handbook/HandbookEntry's record
    components — (category, id, icon_item_id, recipe_id, grid, result_item_id,
    result_count, text_en, text_de) where recipe_id/result_item_id may be None and grid
    is either None or a list of 9 item-id strings ("" = empty slot).

    Every entry is rendered as an INLINE `HandbookEntries.add(new HandbookEntry(...))`
    literal — devtools/check_handbook.py parses these positionally, so keep them inline.
    """
    out = [f"package {PKG_ROOT}.feature.{feature_pkg};", "",
           f"import {PKG_ROOT}.core.handbook.HandbookEntries;",
           f"import {PKG_ROOT}.core.handbook.HandbookEntry;",
           "", _javadoc(doc_lines),
           f"final class {class_name} {{",
           f"\tprivate {class_name}() {{",
           "\t}", "",
           "\tstatic void register() {"]
    first = True
    for category, entry_id, icon, recipe_id, grid, result_id, result_count, text_en, text_de in entries:
        if not first:
            out.append("")
        first = False
        recipe_lit = java_str(recipe_id) if recipe_id is not None else "null"
        if grid is not None:
            if len(grid) != 9:
                raise ValueError(f"handbook entry {entry_id!r}: grid must have 9 slots")
            grid_lit = "new String[] {" + ", ".join(java_str(g) for g in grid) + "}"
        else:
            grid_lit = "null"
        result_lit = java_str(result_id) if result_id is not None else "null"
        out.append(f"\t\tHandbookEntries.add(new HandbookEntry({java_str(category)}, "
                   f"{java_str(entry_id)}, {java_str(icon)}, {recipe_lit},")
        out.append(f"\t\t\t\t{grid_lit},")
        out.append(f"\t\t\t\t{result_lit}, {int(result_count)}, {java_str(text_en)}, "
                   f"{java_str(text_de)}));")
    out += ["\t}", "}"]
    return "\n".join(out) + "\n"
