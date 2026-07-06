#!/usr/bin/env python3
"""Masonry v2 asset generator for COPPER INFERNO 1 (mod id: copper_inferno).

Generates ALL JSON assets/data + 16x16 PNG textures for the two REAL-oxidizing
masonry families (copper_bricks / copper_tiles), 56 block ids total, directly
into src/main/resources so they ship with the mod.

Idempotent: writes a fixed set of files it owns (never deletes/globs), and all
texture noise is seeded per texture name, so re-runs produce identical bytes.

JSON formats are copied from EXACT vanilla 1.21.9 templates extracted from
~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar:
  - blockstates: cut_copper.json / cut_copper_slab.json / cut_copper_stairs.json /
    brick_wall.json (multipart); waxed blockstates point at the UNWAXED models
    exactly like vanilla waxed_cut_copper*.json do.
  - models: cut_copper(_slab/_slab_top/_stairs/_stairs_inner/_stairs_outer),
    brick_wall_post/_side/_side_tall/_inventory.
  - items: cut_copper.json / brick_wall.json (minecraft:model definitions).
  - loot: dr_pepper_can_block drop-self format (matches vanilla stone.json
    survives_explosion pool); slabs use the vanilla stone_brick_slab.json format
    (type=double drops 2).
  - recipes: cut_copper.json (2x2 -> 4), stone_brick_slab/stairs/wall,
    waxed_copper_block_from_honeycomb.json (shapeless waxing).
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

FAMILIES = [
    # (family/base cube name, stem)
    ("copper_bricks", "copper_brick"),
    ("copper_tiles", "copper_tile"),
]

STAGE_PREFIXES = ["", "exposed_", "weathered_", "oxidized_"]

# (light, mid, dark) per stage; light/mid straight from the task palette, dark
# is the mortar/grout shade (given for base, derived by darkening mid otherwise).
PALETTES = {
    "": ((0xE0, 0x73, 0x4D), (0xC1, 0x5A, 0x3B), (0x8F, 0x3D, 0x26)),
    "exposed_": ((0xD1, 0x8C, 0x6F), (0xA9, 0x70, 0x5E), (0x6E, 0x49, 0x3D)),
    "weathered_": ((0x8F, 0xA8, 0x83), (0x6F, 0xB0, 0x8E), (0x48, 0x73, 0x5C)),
    "oxidized_": ((0x57, 0xA0, 0x7B), (0x4E, 0x9E, 0x7A), (0x33, 0x67, 0x4F)),
}


def write_json(path: Path, obj) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True) + "\n", encoding="utf-8")


def clamp(v: int) -> int:
    return max(0, min(255, v))


def jitter(rnd: random.Random, rgb, amount=7):
    d = rnd.randint(-amount, amount)
    return (clamp(rgb[0] + d), clamp(rgb[1] + d), clamp(rgb[2] + d))


def lighten(rgb, amount=18):
    return (clamp(rgb[0] + amount), clamp(rgb[1] + amount), clamp(rgb[2] + amount))


def make_brick_texture(name: str, palette) -> Image.Image:
    """16x16 running-bond brick pattern: 4px-tall courses, 8px-wide bricks,
    1px dark mortar, alternate courses offset by half a brick."""
    light, mid, dark = palette
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        course = y // 4
        offset = 4 if course % 2 else 0
        for x in range(16):
            if y % 4 == 3:  # horizontal mortar line
                px[x, y] = jitter(rnd, dark, 4)
                continue
            if (x + offset) % 8 == 7:  # vertical mortar joint
                px[x, y] = jitter(rnd, dark, 4)
                continue
            brick = ((x + offset) // 8 + course) % 2
            base = light if brick == 0 else mid
            if y % 4 == 0:  # top edge of the brick gets a subtle highlight
                base = lighten(base, 12)
            px[x, y] = jitter(rnd, base)
    return img


def make_tile_texture(name: str, palette) -> Image.Image:
    """16x16 2x2 tile grid: 8x8 tiles with 1px dark grout on right/bottom edges
    and a light bevel on top/left edges, checkerboard light/mid fill."""
    light, mid, dark = palette
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            if x % 8 == 7 or y % 8 == 7:  # grout lines
                px[x, y] = jitter(rnd, dark, 4)
                continue
            tile = (x // 8 + y // 8) % 2
            base = light if tile == 0 else mid
            if x % 8 == 0 or y % 8 == 0:  # bevel highlight
                base = lighten(base, 14)
            px[x, y] = jitter(rnd, base)
    return img


# ---------------------------------------------------------------------------
# EXACT vanilla 1.21.9 templates (extracted from minecraft-client.jar).
# Model references are rewritten via string replacement before json.loads.
# ---------------------------------------------------------------------------

# assets/minecraft/blockstates/cut_copper_stairs.json with
# "minecraft:block/cut_copper_stairs" -> "@STAIRS@" (also covers _inner/_outer prefixes).
STAIRS_BLOCKSTATE_TEMPLATE = """
{
  "variants": {
    "facing=east,half=bottom,shape=inner_left": { "model": "@STAIRS@_inner", "uvlock": true, "y": 270 },
    "facing=east,half=bottom,shape=inner_right": { "model": "@STAIRS@_inner" },
    "facing=east,half=bottom,shape=outer_left": { "model": "@STAIRS@_outer", "uvlock": true, "y": 270 },
    "facing=east,half=bottom,shape=outer_right": { "model": "@STAIRS@_outer" },
    "facing=east,half=bottom,shape=straight": { "model": "@STAIRS@" },
    "facing=east,half=top,shape=inner_left": { "model": "@STAIRS@_inner", "uvlock": true, "x": 180 },
    "facing=east,half=top,shape=inner_right": { "model": "@STAIRS@_inner", "uvlock": true, "x": 180, "y": 90 },
    "facing=east,half=top,shape=outer_left": { "model": "@STAIRS@_outer", "uvlock": true, "x": 180 },
    "facing=east,half=top,shape=outer_right": { "model": "@STAIRS@_outer", "uvlock": true, "x": 180, "y": 90 },
    "facing=east,half=top,shape=straight": { "model": "@STAIRS@", "uvlock": true, "x": 180 },
    "facing=north,half=bottom,shape=inner_left": { "model": "@STAIRS@_inner", "uvlock": true, "y": 180 },
    "facing=north,half=bottom,shape=inner_right": { "model": "@STAIRS@_inner", "uvlock": true, "y": 270 },
    "facing=north,half=bottom,shape=outer_left": { "model": "@STAIRS@_outer", "uvlock": true, "y": 180 },
    "facing=north,half=bottom,shape=outer_right": { "model": "@STAIRS@_outer", "uvlock": true, "y": 270 },
    "facing=north,half=bottom,shape=straight": { "model": "@STAIRS@", "uvlock": true, "y": 270 },
    "facing=north,half=top,shape=inner_left": { "model": "@STAIRS@_inner", "uvlock": true, "x": 180, "y": 270 },
    "facing=north,half=top,shape=inner_right": { "model": "@STAIRS@_inner", "uvlock": true, "x": 180 },
    "facing=north,half=top,shape=outer_left": { "model": "@STAIRS@_outer", "uvlock": true, "x": 180, "y": 270 },
    "facing=north,half=top,shape=outer_right": { "model": "@STAIRS@_outer", "uvlock": true, "x": 180 },
    "facing=north,half=top,shape=straight": { "model": "@STAIRS@", "uvlock": true, "x": 180, "y": 270 },
    "facing=south,half=bottom,shape=inner_left": { "model": "@STAIRS@_inner" },
    "facing=south,half=bottom,shape=inner_right": { "model": "@STAIRS@_inner", "uvlock": true, "y": 90 },
    "facing=south,half=bottom,shape=outer_left": { "model": "@STAIRS@_outer" },
    "facing=south,half=bottom,shape=outer_right": { "model": "@STAIRS@_outer", "uvlock": true, "y": 90 },
    "facing=south,half=bottom,shape=straight": { "model": "@STAIRS@", "uvlock": true, "y": 90 },
    "facing=south,half=top,shape=inner_left": { "model": "@STAIRS@_inner", "uvlock": true, "x": 180, "y": 90 },
    "facing=south,half=top,shape=inner_right": { "model": "@STAIRS@_inner", "uvlock": true, "x": 180, "y": 180 },
    "facing=south,half=top,shape=outer_left": { "model": "@STAIRS@_outer", "uvlock": true, "x": 180, "y": 90 },
    "facing=south,half=top,shape=outer_right": { "model": "@STAIRS@_outer", "uvlock": true, "x": 180, "y": 180 },
    "facing=south,half=top,shape=straight": { "model": "@STAIRS@", "uvlock": true, "x": 180, "y": 90 },
    "facing=west,half=bottom,shape=inner_left": { "model": "@STAIRS@_inner", "uvlock": true, "y": 90 },
    "facing=west,half=bottom,shape=inner_right": { "model": "@STAIRS@_inner", "uvlock": true, "y": 180 },
    "facing=west,half=bottom,shape=outer_left": { "model": "@STAIRS@_outer", "uvlock": true, "y": 90 },
    "facing=west,half=bottom,shape=outer_right": { "model": "@STAIRS@_outer", "uvlock": true, "y": 180 },
    "facing=west,half=bottom,shape=straight": { "model": "@STAIRS@", "uvlock": true, "y": 180 },
    "facing=west,half=top,shape=inner_left": { "model": "@STAIRS@_inner", "uvlock": true, "x": 180, "y": 180 },
    "facing=west,half=top,shape=inner_right": { "model": "@STAIRS@_inner", "uvlock": true, "x": 180, "y": 270 },
    "facing=west,half=top,shape=outer_left": { "model": "@STAIRS@_outer", "uvlock": true, "x": 180, "y": 180 },
    "facing=west,half=top,shape=outer_right": { "model": "@STAIRS@_outer", "uvlock": true, "x": 180, "y": 270 },
    "facing=west,half=top,shape=straight": { "model": "@STAIRS@", "uvlock": true, "x": 180, "y": 180 }
  }
}
"""

# assets/minecraft/blockstates/brick_wall.json with
# "minecraft:block/brick_wall" -> "@WALL@" (covers _post/_side/_side_tall).
WALL_BLOCKSTATE_TEMPLATE = """
{
  "multipart": [
    { "apply": { "model": "@WALL@_post" }, "when": { "up": "true" } },
    { "apply": { "model": "@WALL@_side", "uvlock": true }, "when": { "north": "low" } },
    { "apply": { "model": "@WALL@_side", "uvlock": true, "y": 90 }, "when": { "east": "low" } },
    { "apply": { "model": "@WALL@_side", "uvlock": true, "y": 180 }, "when": { "south": "low" } },
    { "apply": { "model": "@WALL@_side", "uvlock": true, "y": 270 }, "when": { "west": "low" } },
    { "apply": { "model": "@WALL@_side_tall", "uvlock": true }, "when": { "north": "tall" } },
    { "apply": { "model": "@WALL@_side_tall", "uvlock": true, "y": 90 }, "when": { "east": "tall" } },
    { "apply": { "model": "@WALL@_side_tall", "uvlock": true, "y": 180 }, "when": { "south": "tall" } },
    { "apply": { "model": "@WALL@_side_tall", "uvlock": true, "y": 270 }, "when": { "west": "tall" } }
  ]
}
"""


def block_model(id_: str) -> str:
    return f"{MODID}:block/{id_}"


def stairs_blockstate(stairs_model_id: str):
    return json.loads(STAIRS_BLOCKSTATE_TEMPLATE.replace("@STAIRS@", block_model(stairs_model_id)))


def wall_blockstate(wall_id: str):
    return json.loads(WALL_BLOCKSTATE_TEMPLATE.replace("@WALL@", block_model(wall_id)))


def slab_blockstate(slab_model_id: str, cube_model_id: str):
    # vanilla cut_copper_slab.json format
    return {
        "variants": {
            "type=bottom": {"model": block_model(slab_model_id)},
            "type=double": {"model": block_model(cube_model_id)},
            "type=top": {"model": block_model(slab_model_id) + "_top"},
        }
    }


def cube_blockstate(cube_model_id: str):
    # vanilla cut_copper.json format
    return {"variants": {"": {"model": block_model(cube_model_id)}}}


def item_def(model_id: str):
    # vanilla items/cut_copper.json + existing items/dr_pepper_can_block.json format
    return {"model": {"type": "minecraft:model", "model": block_model(model_id)}}


def drop_self_loot(block_id: str):
    # EXACT format of the existing dr_pepper_can_block loot table.
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


def slab_loot(block_id: str):
    # vanilla stone_brick_slab.json format (double slab drops 2).
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "bonus_rolls": 0.0,
                "entries": [
                    {
                        "type": "minecraft:item",
                        "functions": [
                            {
                                "add": False,
                                "conditions": [
                                    {
                                        "block": f"{MODID}:{block_id}",
                                        "condition": "minecraft:block_state_property",
                                        "properties": {"type": "double"},
                                    }
                                ],
                                "count": 2.0,
                                "function": "minecraft:set_count",
                            },
                            {"function": "minecraft:explosion_decay"},
                        ],
                        "name": f"{MODID}:{block_id}",
                    }
                ],
                "rolls": 1.0,
            }
        ],
        "random_sequence": f"{MODID}:blocks/{block_id}",
    }


def shaped_recipe(pattern, key, result_id, count, category="building", group=None):
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


def waxing_recipe(unwaxed_id: str, waxed_id: str):
    # vanilla waxed_copper_block_from_honeycomb.json format
    return {
        "type": "minecraft:crafting_shapeless",
        "category": "building",
        "group": waxed_id,
        "ingredients": [f"{MODID}:{unwaxed_id}", "minecraft:honeycomb"],
        "result": {"count": 1, "id": f"{MODID}:{waxed_id}"},
    }


def display_name(block_id: str) -> str:
    return " ".join(w.capitalize() for w in block_id.split("_"))


def main() -> None:
    lang = {}
    all_ids = []
    files = 0

    for family, stem in FAMILIES:
        for prefix in STAGE_PREFIXES:
            cube = prefix + family
            slab = f"{prefix}{stem}_slab"
            stairs = f"{prefix}{stem}_stairs"
            wall = f"{prefix}{stem}_wall"
            waxed_cube = "waxed_" + cube
            waxed_slab = "waxed_" + slab
            waxed_stairs = "waxed_" + stairs
            stage_ids = [cube, slab, stairs, wall, waxed_cube, waxed_slab, waxed_stairs]
            all_ids.extend(stage_ids)

            # --- texture: one 16x16 per family-stage; waxed reuses it ---
            tex = (make_brick_texture if family == "copper_bricks" else make_tile_texture)(
                cube, PALETTES[prefix])
            tex_path = ASSETS / "textures" / "block" / f"{cube}.png"
            tex_path.parent.mkdir(parents=True, exist_ok=True)
            tex.save(tex_path)
            files += 1

            # --- block models (waxed blocks reuse these, like vanilla) ---
            t = block_model(cube)
            models = {
                cube: {"parent": "minecraft:block/cube_all", "textures": {"all": t}},
                slab: {"parent": "minecraft:block/slab",
                       "textures": {"bottom": t, "side": t, "top": t}},
                f"{slab}_top": {"parent": "minecraft:block/slab_top",
                                "textures": {"bottom": t, "side": t, "top": t}},
                stairs: {"parent": "minecraft:block/stairs",
                         "textures": {"bottom": t, "side": t, "top": t}},
                f"{stairs}_inner": {"parent": "minecraft:block/inner_stairs",
                                    "textures": {"bottom": t, "side": t, "top": t}},
                f"{stairs}_outer": {"parent": "minecraft:block/outer_stairs",
                                    "textures": {"bottom": t, "side": t, "top": t}},
                f"{wall}_post": {"parent": "minecraft:block/template_wall_post",
                                 "textures": {"wall": t}},
                f"{wall}_side": {"parent": "minecraft:block/template_wall_side",
                                 "textures": {"wall": t}},
                f"{wall}_side_tall": {"parent": "minecraft:block/template_wall_side_tall",
                                      "textures": {"wall": t}},
                f"{wall}_inventory": {"parent": "minecraft:block/wall_inventory",
                                      "textures": {"wall": t}},
            }
            for name, obj in models.items():
                write_json(ASSETS / "models" / "block" / f"{name}.json", obj)
                files += 1

            # --- blockstates (waxed point at the UNWAXED models, vanilla style) ---
            blockstates = {
                cube: cube_blockstate(cube),
                slab: slab_blockstate(slab, cube),
                stairs: stairs_blockstate(stairs),
                wall: wall_blockstate(wall),
                waxed_cube: cube_blockstate(cube),
                waxed_slab: slab_blockstate(slab, cube),
                waxed_stairs: stairs_blockstate(stairs),
            }
            for name, obj in blockstates.items():
                write_json(ASSETS / "blockstates" / f"{name}.json", obj)
                files += 1

            # --- item definitions (1.21.9: every BlockItem needs items/<id>.json) ---
            items = {
                cube: item_def(cube),
                slab: item_def(slab),
                stairs: item_def(stairs),
                wall: item_def(f"{wall}_inventory"),
                waxed_cube: item_def(cube),
                waxed_slab: item_def(slab),
                waxed_stairs: item_def(stairs),
            }
            for name, obj in items.items():
                write_json(ASSETS / "items" / f"{name}.json", obj)
                files += 1

            # --- loot tables: every block drops itself ---
            for bid in stage_ids:
                loot = slab_loot(bid) if bid.endswith("_slab") else drop_self_loot(bid)
                write_json(DATA / "loot_table" / "blocks" / f"{bid}.json", loot)
                files += 1

            # --- recipes (this stage's cube -> slab/stairs/wall; honeycomb waxing) ---
            cube_key = {"#": f"{MODID}:{cube}"}
            recipes = {
                slab: shaped_recipe(["###"], cube_key, slab, 6, group=f"{stem}_slab"),
                stairs: shaped_recipe(["#  ", "## ", "###"], cube_key, stairs, 4,
                                      group=f"{stem}_stairs"),
                wall: shaped_recipe(["###", "###"], cube_key, wall, 6, category="misc",
                                    group=f"{stem}_wall"),
                f"{waxed_cube}_from_honeycomb": waxing_recipe(cube, waxed_cube),
                f"{waxed_slab}_from_honeycomb": waxing_recipe(slab, waxed_slab),
                f"{waxed_stairs}_from_honeycomb": waxing_recipe(stairs, waxed_stairs),
            }
            for name, obj in recipes.items():
                write_json(DATA / "recipe" / "masonry" / f"{name}.json", obj)
                files += 1

            for bid in stage_ids:
                lang[f"block.{MODID}.{bid}"] = display_name(bid)

    # --- base cube crafting recipes (only the unaffected stage is craftable) ---
    write_json(DATA / "recipe" / "masonry" / "copper_bricks.json",
               shaped_recipe(["##", "##"], {"#": "minecraft:copper_ingot"}, "copper_bricks", 4))
    write_json(DATA / "recipe" / "masonry" / "copper_tiles.json",
               shaped_recipe(["##", "##"], {"#": f"{MODID}:copper_bricks"}, "copper_tiles", 4))
    files += 2

    # --- lang fragment (merged into en_us.json by the integration step) ---
    write_json(ASSETS / "lang" / "fragments" / "masonry.json",
               dict(sorted(lang.items())))
    files += 1

    assert len(all_ids) == 56, f"expected 56 block ids, got {len(all_ids)}"
    print(f"masonry_gen: wrote {files} files for {len(all_ids)} block ids")


if __name__ == "__main__":
    main()
