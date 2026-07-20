#!/usr/bin/env python3
"""Driftwood and coastal-flora generator (Wave 5 WP3).

Generates the three 13-block WoodSets and matching leaves registered by DriftwoodFeature,
six decorative coastal blocks, ten recipes per wood, three configured/placed tree pairs,
vanilla and mod tags, and EN/DE language fragments. Output is deterministic and idempotent.
"""

from pathlib import Path

from PIL import Image

import lib_gen as lib

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / lib.NS
DATA = RES / "data" / lib.NS
TAGFRAG = ROOT / "devtools" / "tagfrag" / "driftwood.json"

WOODS = ["driftwood", "tidewillow", "brinepine"]
DECOR = ["verdigris_kelp", "salt_sprout", "tide_lily",
         "barnacle_cluster", "pearl_cluster", "foam_moss"]

PALETTES = {
    "driftwood": {
        "plank": ((0x8B, 0x78, 0x5E), (0xA5, 0x91, 0x73), (0x57, 0x48, 0x37)),
        "bark": ((0x67, 0x59, 0x48), (0x43, 0x38, 0x2D), (0x82, 0x72, 0x5B)),
        "ring": ((0xB2, 0x9B, 0x7A), (0x75, 0x62, 0x4D)),
        "strip": ((0xB9, 0xA4, 0x83), (0x91, 0x7B, 0x60), (0xCA, 0xB6, 0x94)),
        "accent": (0xD6, 0xCC, 0xAE),
    },
    "tidewillow": {
        "plank": ((0x4F, 0x82, 0x79), (0x69, 0x9D, 0x91), (0x2C, 0x55, 0x50)),
        "bark": ((0x3B, 0x68, 0x61), (0x25, 0x48, 0x43), (0x57, 0x82, 0x78)),
        "ring": ((0x77, 0xAA, 0x9D), (0x45, 0x73, 0x69)),
        "strip": ((0x72, 0xA7, 0x99), (0x54, 0x88, 0x7C), (0x8B, 0xBC, 0xAD)),
        "accent": (0xA8, 0xD8, 0xC9),
    },
    "brinepine": {
        "plank": ((0x42, 0x67, 0x70), (0x5A, 0x7F, 0x87), (0x25, 0x42, 0x49)),
        "bark": ((0x31, 0x4D, 0x54), (0x1D, 0x35, 0x3B), (0x48, 0x66, 0x6D)),
        "ring": ((0x6C, 0x8E, 0x94), (0x3C, 0x5E, 0x66)),
        "strip": ((0x68, 0x8D, 0x91), (0x4A, 0x70, 0x77), (0x80, 0xA3, 0xA5)),
        "accent": (0xB7, 0xD1, 0xCA),
    },
}

LEAF_PALETTES = {
    "driftwood": ((0x55, 0x70, 0x59), (0x72, 0x91, 0x70), (0x9A, 0xB2, 0x91)),
    "tidewillow": ((0x1E, 0x5A, 0x53), (0x2F, 0x7D, 0x70), (0x58, 0xAA, 0x93)),
    "brinepine": ((0x1C, 0x45, 0x4B), (0x2D, 0x63, 0x68), (0x50, 0x86, 0x86)),
}

EN_WOODS = {
    "driftwood": "Driftwood",
    "tidewillow": "Tidewillow",
    "brinepine": "Brinepine",
}
DE_WOODS = {
    "driftwood": ("Treibholz", "Treibholz"),
    "tidewillow": ("Gezeitenweiden", "Gezeitenweidenholz"),
    "brinepine": ("Salzkiefern", "Salzkiefernholz"),
}
EN_DECOR = {
    "verdigris_kelp": "Verdigris Kelp",
    "salt_sprout": "Salt Sprout",
    "tide_lily": "Tide Lily",
    "barnacle_cluster": "Barnacle Cluster",
    "pearl_cluster": "Pearl Cluster",
    "foam_moss": "Foam Moss",
}
DE_DECOR = {
    "verdigris_kelp": "Grünspankelp",
    "salt_sprout": "Salzsprosse",
    "tide_lily": "Gezeitenlilie",
    "barnacle_cluster": "Seepockenkolonie",
    "pearl_cluster": "Perlenkolonie",
    "foam_moss": "Schaummoos",
}


def set_ids(wood: str) -> list[str]:
    return [
        f"{wood}_planks", f"{wood}_plank_slab", f"{wood}_plank_stairs",
        f"{wood}_fence", f"{wood}_fence_gate", f"{wood}_button",
        f"{wood}_pressure_plate", f"{wood}_log", f"stripped_{wood}_log",
        f"{wood}_wood", f"stripped_{wood}_wood", f"{wood}_mosaic",
        f"{wood}_pillar",
    ]


ALL_WOOD_IDS = [block_id for wood in WOODS for block_id in set_ids(wood)]
LEAVES = [f"{wood}_leaves" for wood in WOODS]
ALL_IDS = ALL_WOOD_IDS + LEAVES + DECOR
assert len(ALL_WOOD_IDS) == 39 and len(ALL_IDS) == 48


def make_stripped_side(name: str, base, dark, light) -> Image.Image:
    rng = lib.seeded(name)
    image = Image.new("RGB", (16, 16))
    pixels = image.load()
    grain = {rng.randrange(16) for _ in range(4)}
    for x in range(16):
        for y in range(16):
            color = dark if x in grain else (light if x % 5 == 2 else base)
            pixels[x, y] = lib._jitter(rng, color, 3)
    return image


def make_mosaic(name: str, base, light, seam) -> Image.Image:
    rng = lib.seeded(name)
    image = Image.new("RGB", (16, 16))
    pixels = image.load()
    for y in range(16):
        for x in range(16):
            horizontal = ((x // 8) + (y // 8)) % 2 == 0
            along, across = (x, y) if horizontal else (y, x)
            color = seam if across % 4 == 3 or along % 8 == 7 else (
                light if across % 4 == 0 else base)
            pixels[x, y] = lib._jitter(rng, color, 3)
    return image


def make_leaves(name: str, dark, base, light) -> Image.Image:
    """Untinted RGBA foliage with deterministic mottle and transparent cutout holes."""
    rng = lib.seeded(name)
    image = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    pixels = image.load()
    for y in range(16):
        for x in range(16):
            value = rng.random()
            if value < 0.14:
                continue
            color = dark if value < 0.32 else (light if value > 0.84 else base)
            pixels[x, y] = (*lib._jitter(rng, color, 4), 255)
    return image


def paint_wood(wood: str) -> dict[str, Image.Image]:
    palette = PALETTES[wood]
    plank, plank_light, seam = palette["plank"]
    bark, bark_dark, bark_light = palette["bark"]
    ring_light, ring_dark = palette["ring"]
    strip, strip_dark, strip_light = palette["strip"]
    accent = palette["accent"]
    leaf_dark, leaf_base, leaf_light = LEAF_PALETTES[wood]
    return {
        f"{wood}_leaves": make_leaves(
            f"{wood}_leaves", leaf_dark, leaf_base, leaf_light),
        f"{wood}_planks": lib.plank_grain(
            lib.seeded(f"{wood}_planks"), plank, plank_light, seam,
            fleck=accent, fleck_count=2),
        f"{wood}_log": lib.bark_side(
            lib.seeded(f"{wood}_log"), bark, bark_dark, bark_light),
        f"{wood}_log_top": lib.log_rings(
            lib.seeded(f"{wood}_log_top"), ring_light, ring_dark, bark_dark),
        f"stripped_{wood}_log": make_stripped_side(
            f"stripped_{wood}_log", strip, strip_dark, strip_light),
        f"stripped_{wood}_log_top": lib.log_rings(
            lib.seeded(f"stripped_{wood}_log_top"), strip_light, strip_dark, strip_dark),
        f"{wood}_mosaic": make_mosaic(f"{wood}_mosaic", plank, plank_light, seam),
        f"{wood}_pillar_side": lib.pillar_side(
            lib.seeded(f"{wood}_pillar_side"), [seam, plank, plank_light],
            seam, [accent], 0.15),
        f"{wood}_pillar_top": lib.pillar_top(
            lib.seeded(f"{wood}_pillar_top"), [seam, plank, plank, plank_light],
            seam, [accent]),
    }


def plant_sprite(name: str, dark, base, light) -> Image.Image:
    """Deterministic transparent sprite with a plant/cluster silhouette."""
    rng = lib.seeded(name)
    image = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    pixels = image.load()
    if "cluster" in name:
        centers = [(5, 11, 3), (9, 10, 3), (7, 6, 2), (11, 7, 2)]
        for cx, cy, radius in centers:
            for y in range(cy - radius, cy + radius + 1):
                for x in range(cx - radius, cx + radius + 1):
                    if 0 <= x < 16 and 0 <= y < 16 and (x - cx) ** 2 + (y - cy) ** 2 <= radius ** 2:
                        edge = abs(x - cx) + abs(y - cy) >= radius
                        color = dark if edge else (light if rng.random() < 0.2 else base)
                        pixels[x, y] = (*lib._jitter(rng, color, 3), 255)
    else:
        stems = [6, 8, 10] if name == "salt_sprout" else [7, 8]
        for x in stems:
            top = rng.randrange(3, 8)
            for y in range(top, 16):
                pixels[x, y] = (*lib._jitter(rng, dark, 2), 255)
                if y in (top + 2, top + 5):
                    side = -1 if (x + y) % 2 else 1
                    for step in (1, 2):
                        px = x + side * step
                        if 0 <= px < 16:
                            pixels[px, y - step // 2] = (
                                *lib._jitter(rng, base, 3), 255)
            for dx, dy in ((0, 0), (-1, 0), (1, 0), (0, -1)):
                px, py = x + dx, top + dy
                if 0 <= px < 16 and 0 <= py < 16:
                    pixels[px, py] = (*lib._jitter(rng, light, 2), 255)
    return image


def foam_moss_texture() -> Image.Image:
    rng = lib.seeded("foam_moss")
    image = Image.new("RGB", (16, 16))
    pixels = image.load()
    colors = [(0x69, 0xA9, 0x8E), (0x83, 0xC2, 0xA5), (0xB9, 0xDB, 0xC5)]
    for y in range(16):
        for x in range(16):
            color = colors[2] if rng.random() < 0.12 else colors[(x + y) % 2]
            pixels[x, y] = lib._jitter(rng, color, 5)
    return image


def paint_decor() -> dict[str, Image.Image]:
    return {
        "verdigris_kelp": plant_sprite(
            "verdigris_kelp", (0x1F, 0x61, 0x58), (0x35, 0x8D, 0x79), (0x65, 0xC1, 0xA2)),
        "salt_sprout": plant_sprite(
            "salt_sprout", (0x8E, 0x91, 0x83), (0xC5, 0xC8, 0xB8), (0xF2, 0xF0, 0xD9)),
        "tide_lily": plant_sprite(
            "tide_lily", (0x2D, 0x71, 0x70), (0x5B, 0xAE, 0xA8), (0xA8, 0xE2, 0xD5)),
        "barnacle_cluster": plant_sprite(
            "barnacle_cluster", (0x55, 0x5F, 0x61), (0x8D, 0x99, 0x98), (0xC2, 0xC9, 0xC2)),
        "pearl_cluster": plant_sprite(
            "pearl_cluster", (0x73, 0x91, 0x91), (0xB9, 0xD8, 0xD2), (0xF3, 0xFA, 0xE8)),
        "foam_moss": foam_moss_texture(),
    }


def emit_column(name: str, end: str, side: str) -> dict:
    return lib.merge({
        lib._bs(name): {"variants": {
            "axis=x": {"model": lib.block_ref(name), "x": 90, "y": 90},
            "axis=y": {"model": lib.block_ref(name)},
            "axis=z": {"model": lib.block_ref(name), "x": 90},
        }},
        lib._bm(name): {
            "parent": "minecraft:block/cube_column",
            "textures": {"end": lib.block_ref(end), "side": lib.block_ref(side)},
        },
        lib._it(name): lib.item_def(lib.block_ref(name)),
    }, lib.loot_drop_self(name))


def emit_wood_assets(wood: str) -> dict:
    planks = f"{wood}_planks"
    return lib.merge(
        lib.emit_cube(planks),
        lib.emit_slab(f"{wood}_plank_slab", planks),
        lib.emit_stairs(f"{wood}_plank_stairs", planks),
        lib.emit_fence(f"{wood}_fence", planks),
        lib.emit_fence_gate(f"{wood}_fence_gate", planks),
        lib.emit_button(f"{wood}_button", planks),
        lib.emit_pressure_plate(f"{wood}_pressure_plate", planks),
        emit_column(f"{wood}_log", f"{wood}_log_top", f"{wood}_log"),
        emit_column(f"stripped_{wood}_log",
                    f"stripped_{wood}_log_top", f"stripped_{wood}_log"),
        emit_column(f"{wood}_wood", f"{wood}_log", f"{wood}_log"),
        emit_column(f"stripped_{wood}_wood",
                    f"stripped_{wood}_log", f"stripped_{wood}_log"),
        lib.emit_cube(f"{wood}_mosaic"),
        lib.emit_pillar(f"{wood}_pillar"),
    )


def emit_cross(name: str) -> dict:
    return lib.merge({
        lib._bs(name): {"variants": {"": {"model": lib.block_ref(name)}}},
        lib._bm(name): {
            "parent": "minecraft:block/cross",
            "textures": {"cross": lib.block_ref(name)},
        },
        lib._im(name): {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": lib.block_ref(name)},
        },
        lib._it(name): lib.item_def(f"{lib.NS}:item/{name}"),
    }, lib.loot_drop_self(name))


def emit_leaves(name: str) -> dict:
    """Untinted leaves model and item; leaves drop themselves in this lightweight set."""
    return lib.merge({
        lib._bs(name): {"variants": {"": {"model": lib.block_ref(name)}}},
        lib._bm(name): {
            "parent": "minecraft:block/leaves",
            "textures": {"all": lib.block_ref(name)},
        },
        lib._it(name): lib.item_def(lib.block_ref(name)),
    }, lib.loot_drop_self(name))


def tree_worldgen(wood: str) -> tuple[dict, dict]:
    """Ashwillow tree structure with this wood's own log and leaves."""
    configured = {
        "type": "minecraft:tree",
        "config": {
            "decorators": [],
            "dirt_provider": {
                "type": "minecraft:simple_state_provider",
                "state": {"Name": "minecraft:dirt"},
            },
            "foliage_placer": {
                "type": "minecraft:blob_foliage_placer",
                "height": 3, "offset": 0, "radius": 2,
            },
            "foliage_provider": {
                "type": "minecraft:simple_state_provider",
                "state": {
                    "Name": f"{lib.NS}:{wood}_leaves",
                    "Properties": {
                        "distance": "7", "persistent": "false", "waterlogged": "false",
                    },
                },
            },
            "force_dirt": True,
            "ignore_vines": True,
            "minimum_size": {
                "type": "minecraft:two_layers_feature_size",
                "limit": 1, "lower_size": 0, "upper_size": 1,
            },
            "trunk_placer": {
                "type": "minecraft:straight_trunk_placer",
                "base_height": 4, "height_rand_a": 2, "height_rand_b": 0,
            },
            "trunk_provider": {
                "type": "minecraft:simple_state_provider",
                "state": {
                    "Name": f"{lib.NS}:{wood}_log",
                    "Properties": {"axis": "y"},
                },
            },
        },
    }
    placed = {
        "feature": f"{lib.NS}:{wood}_tree",
        "placement": [
            {
                "count": {
                    "type": "minecraft:uniform",
                    "max_inclusive": 2,
                    "min_inclusive": 1,
                },
                "type": "minecraft:count_on_every_layer",
            },
            {"type": "minecraft:biome"},
        ],
    }
    return configured, placed


def wood_recipes(wood: str) -> dict[str, dict]:
    namespace = f"{lib.NS}:"
    planks = namespace + f"{wood}_planks"
    log = namespace + f"{wood}_log"
    slab = namespace + f"{wood}_plank_slab"
    recipes = {
        f"{wood}_planks": {
            "category": "building", "group": "planks",
            "ingredients": [f"#{lib.NS}:{wood}_logs"],
            "result": {"count": 4, "id": planks},
            "type": "minecraft:crafting_shapeless",
        },
        f"{wood}_plank_slab": {
            "category": "building", "group": "wooden_slab",
            "key": {"#": planks}, "pattern": ["###"],
            "result": {"count": 6, "id": slab},
            "type": "minecraft:crafting_shaped",
        },
        f"{wood}_plank_stairs": {
            "category": "building", "group": "wooden_stairs",
            "key": {"#": planks}, "pattern": ["#  ", "## ", "###"],
            "result": {"count": 4, "id": namespace + f"{wood}_plank_stairs"},
            "type": "minecraft:crafting_shaped",
        },
        f"{wood}_fence": {
            "category": "misc", "group": "wooden_fence",
            "key": {"#": "minecraft:stick", "W": planks}, "pattern": ["W#W", "W#W"],
            "result": {"count": 3, "id": namespace + f"{wood}_fence"},
            "type": "minecraft:crafting_shaped",
        },
        f"{wood}_fence_gate": {
            "category": "redstone", "group": "wooden_fence_gate",
            "key": {"#": "minecraft:stick", "W": planks}, "pattern": ["#W#", "#W#"],
            "result": {"count": 1, "id": namespace + f"{wood}_fence_gate"},
            "type": "minecraft:crafting_shaped",
        },
        f"{wood}_button": {
            "category": "redstone", "group": "wooden_button",
            "ingredients": [planks],
            "result": {"count": 1, "id": namespace + f"{wood}_button"},
            "type": "minecraft:crafting_shapeless",
        },
        f"{wood}_pressure_plate": {
            "category": "redstone", "group": "wooden_pressure_plate",
            "key": {"#": planks}, "pattern": ["##"],
            "result": {"count": 1, "id": namespace + f"{wood}_pressure_plate"},
            "type": "minecraft:crafting_shaped",
        },
        f"{wood}_wood": {
            "category": "building", "group": "bark",
            "key": {"#": log}, "pattern": ["##", "##"],
            "result": {"count": 3, "id": namespace + f"{wood}_wood"},
            "type": "minecraft:crafting_shaped",
        },
        f"{wood}_mosaic": {
            "category": "building",
            "key": {"#": slab}, "pattern": ["##", "##"],
            "result": {"count": 2, "id": namespace + f"{wood}_mosaic"},
            "type": "minecraft:crafting_shaped",
        },
        f"{wood}_pillar_from_{wood}_planks_stonecutting": {
            "ingredient": planks,
            "result": {"count": 1, "id": namespace + f"{wood}_pillar"},
            "type": "minecraft:stonecutting",
        },
    }
    assert len(recipes) == 10
    return recipes


def tag_fragment() -> dict[str, list[str]]:
    def ids(suffix: str) -> list[str]:
        return [f"{lib.NS}:{wood}{suffix}" for wood in WOODS]

    logs = [
        f"{lib.NS}:{prefix}{wood}{suffix}"
        for wood in WOODS
        for prefix, suffix in (
            ("", "_log"), ("stripped_", "_log"),
            ("", "_wood"), ("stripped_", "_wood"),
        )
    ]
    planks = ids("_planks")
    fences = ids("_fence")
    gates = ids("_fence_gate")
    buttons = ids("_button")
    plates = ids("_pressure_plate")
    slabs = ids("_plank_slab")
    stairs = ids("_plank_stairs")
    leaves = ids("_leaves")
    return {
        "block/buttons": buttons,
        "block/fence_gates": gates,
        "block/fences": fences,
        "block/logs": logs,
        "block/logs_that_burn": logs,
        "block/leaves": leaves,
        "block/mineable/axe": [f"{lib.NS}:{block_id}" for block_id in ALL_WOOD_IDS],
        "block/mineable/hoe": leaves,
        "block/planks": planks,
        "block/pressure_plates": plates,
        "block/slabs": slabs,
        "block/stairs": stairs,
        "block/wooden_buttons": buttons,
        "block/wooden_fences": fences,
        "block/wooden_pressure_plates": plates,
        "block/wooden_slabs": slabs,
        "block/wooden_stairs": stairs,
        "item/logs": logs,
        "item/logs_that_burn": logs,
        "item/leaves": leaves,
        "item/planks": planks,
    }


def wood_lang(wood: str) -> tuple[dict[str, str], dict[str, str]]:
    english = EN_WOODS[wood]
    tree, timber = DE_WOODS[wood]
    en = {
        f"{wood}_planks": f"{english} Planks",
        f"{wood}_plank_slab": f"{english} Plank Slab",
        f"{wood}_plank_stairs": f"{english} Plank Stairs",
        f"{wood}_fence": f"{english} Fence",
        f"{wood}_fence_gate": f"{english} Fence Gate",
        f"{wood}_button": f"{english} Button",
        f"{wood}_pressure_plate": f"{english} Pressure Plate",
        f"{wood}_log": f"{english} Log",
        f"stripped_{wood}_log": f"Stripped {english} Log",
        f"{wood}_wood": f"{english} Wood",
        f"stripped_{wood}_wood": f"Stripped {english} Wood",
        f"{wood}_mosaic": f"{english} Mosaic",
        f"{wood}_pillar": f"{english} Pillar",
        f"{wood}_leaves": f"{english} Leaves",
    }
    de = {
        f"{wood}_planks": f"{timber}bretter",
        f"{wood}_plank_slab": f"{timber}stufe",
        f"{wood}_plank_stairs": f"{timber}treppe",
        f"{wood}_fence": f"{timber}zaun",
        f"{wood}_fence_gate": f"{timber}zauntor",
        f"{wood}_button": f"{timber}knopf",
        f"{wood}_pressure_plate": f"{timber}druckplatte",
        f"{wood}_log": f"{tree}stamm",
        f"stripped_{wood}_log": f"Entrindeter {tree}stamm",
        f"{wood}_wood": timber,
        f"stripped_{wood}_wood": f"Entrindetes {timber}",
        f"{wood}_mosaic": f"{timber}mosaik",
        f"{wood}_pillar": f"{timber}säule",
        f"{wood}_leaves": f"{tree}laub",
    }
    return en, de


def main() -> None:
    texture_count = 0
    textures = {}
    for wood in WOODS:
        textures.update(paint_wood(wood))
    textures.update(paint_decor())
    for name, image in textures.items():
        path = ASSETS / "textures" / "block" / f"{name}.png"
        path.parent.mkdir(parents=True, exist_ok=True)
        image.save(path)
        texture_count += 1
    assert texture_count == 33

    files = lib.merge(
        *[emit_wood_assets(wood) for wood in WOODS],
        *[emit_leaves(f"{wood}_leaves") for wood in WOODS],
        *[emit_cross(name) for name in DECOR[:-1]],
        lib.emit_cube("foam_moss"),
    )
    blockstates = [path for path in files if "/blockstates/" in path]
    items = [path for path in files if f"assets/{lib.NS}/items/" in path]
    loot = [path for path in files if "/loot_table/" in path]
    assert len(blockstates) == len(items) == len(loot) == 48
    json_count = lib.write_files(files, RES)

    recipe_count = 0
    for wood in WOODS:
        for name, recipe in wood_recipes(wood).items():
            lib.write_json(DATA / "recipe" / "driftwood" / f"{name}.json", recipe)
            recipe_count += 1
    assert recipe_count == 30

    worldgen_count = 0
    for wood in WOODS:
        configured, placed = tree_worldgen(wood)
        lib.write_json(
            DATA / "worldgen" / "configured_feature" / f"{wood}_tree.json", configured)
        lib.write_json(
            DATA / "worldgen" / "placed_feature" / f"{wood}_trees.json", placed)
        worldgen_count += 2
    assert worldgen_count == 6

    for wood in WOODS:
        lib.write_json(DATA / "tags" / "item" / f"{wood}_logs.json", {"values": [
            f"{lib.NS}:{wood}_log",
            f"{lib.NS}:stripped_{wood}_log",
            f"{lib.NS}:{wood}_wood",
            f"{lib.NS}:stripped_{wood}_wood",
        ]})
    lib.write_json(TAGFRAG, tag_fragment())

    lang_en: dict[str, str] = {}
    lang_de: dict[str, str] = {}
    for wood in WOODS:
        en, de = wood_lang(wood)
        lang_en.update({f"block.{lib.NS}.{key}": value for key, value in en.items()})
        lang_de.update({f"block.{lib.NS}.{key}": value for key, value in de.items()})
    lang_en.update({f"block.{lib.NS}.{key}": value for key, value in EN_DECOR.items()})
    lang_de.update({f"block.{lib.NS}.{key}": value for key, value in DE_DECOR.items()})
    assert len(lang_en) == len(lang_de) == 48
    lib.write_json(ASSETS / "lang" / "fragments" / "driftwood.json", lang_en)
    lib.write_json(ASSETS / "lang" / "fragments_de" / "driftwood.json", lang_de)

    print(
        f"driftwood_gen: {texture_count} textures, {json_count} asset/loot JSONs, "
        f"{recipe_count} recipes, {worldgen_count} tree worldgen JSONs, "
        f"{len(WOODS)} log item tags, 1 tagfrag, 2 lang fragments "
        f"for {len(ALL_IDS)} block ids"
    )


if __name__ == "__main__":
    main()
