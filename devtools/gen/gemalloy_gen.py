#!/usr/bin/env python3
"""Asset generator for the v4 "Gems & Alloys" feature (WP5).

12 materials (pyrium, emberite, cindralite, slagbronze, ashsteel, voidsteel, doomium,
pepperite, fizzium, vitrium, smokequartz, kilnite) x the 22-block alloy template registered
by core.content.AlloySets.registerAlloySet plus the 3 companion items from
registerAlloyItems = 264 blocks + 36 items.

Emits (all deterministic, idempotent — run any number of times, same bytes):
  - textures: 20 block PNGs + 4 item PNGs per material (ore textures are the vanilla
    stone/deepslate faces resp. the shipped cinderstone face with material nugget
    speckles; ingot/nugget/raw sprites are the vanilla iron sprites recolored onto the
    material ramp via lib_gen.recolor)
  - blockstates + block models + items/<id>.json + loot for all 264 blocks (formats via
    devtools/gen/lib_gen.py, which are verbatim copies of the vanilla 1.21.9 formats);
    the 36 ores get the silk-touch/fortune ore loot table copied from the shipped
    infernium_ore loot JSON, dropping raw_<m>
  - models/item/<id>.json + items/<id>.json for the 36 standalone items
  - worldgen: configured_feature/<m>_ore.json (stone + deepslate targets, vanilla
    minecraft:ore config copied from the shipped infernium_ore pair) + placed_feature,
    and cinder_<m>_ore.json targeting copper_inferno:cinderstone for the inferno biomes
  - recipes under data/copper_inferno/recipe/gemalloy/ (vanilla 1.21.9 formats copied
    from devtools/gen/infernium_gen.py): ore/raw smelting+blasting, nugget/ingot/block
    and raw/raw-block storage cycles, and every deco shape from <m>_block via crafting
    AND stonecutting — every crafting input set contains a material-unique id, so no
    vanilla or cross-material collisions are possible
  - lang fragments EN + DE (assets/copper_inferno/lang/fragments{,_de}/gemalloy.json),
    300 keys, real German
  - the vanilla-tag fragment devtools/tagfrag/gemalloy.json for merge_tags.py
    (mineable/pickaxe for everything except glass+pane, needs_stone_tool for the ores,
    walls/slabs/stairs shape tags)
"""

import sys
from pathlib import Path
from random import Random

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import (  # noqa: E402
    NS, _bs, _bm, _im, _it, _lt, _shade, block_ref, brick_overlay, emit_cube,
    emit_cube_family, emit_glass, emit_lamp, emit_lantern, emit_pane, emit_pillar,
    extract_vanilla, glass_frame, item_def, lamp_glow, loot_drop_self, merge,
    noise_cube, pane_edge, pillar_side, pillar_top, recolor, seeded, tile_overlay,
    write_files, write_json,
)

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
TAGFRAG = ROOT / "devtools" / "tagfrag"

# ---------------------------------------------------------------------------
# Materials: canonical order + one mid-tone RGB each; every other color of the
# material's look (ramp, mortar, accents, glow, glass tint) is derived from it.
# ---------------------------------------------------------------------------

MATERIALS = [
    "pyrium", "emberite", "cindralite", "slagbronze", "ashsteel", "voidsteel",
    "doomium", "pepperite", "fizzium", "vitrium", "smokequartz", "kilnite",
]

BASE_RGB = {
    "pyrium": (0xE0, 0xA6, 0x2E),       # fire-gold
    "emberite": (0xD9, 0x5B, 0x2A),     # ember orange
    "cindralite": (0x9E, 0x4F, 0x46),   # cinder rose
    "slagbronze": (0xA8, 0x74, 0x3A),   # slag bronze
    "ashsteel": (0x9C, 0xA0, 0xA4),     # pale ash steel
    "voidsteel": (0x5A, 0x4A, 0x78),    # void purple
    "doomium": (0x9A, 0x22, 0x28),      # doom crimson
    "pepperite": (0x7E, 0x2E, 0x38),    # soda burgundy
    "fizzium": (0xD8, 0xB4, 0x5C),      # fizzy amber
    "vitrium": (0x4E, 0xA8, 0x96),      # glassy teal
    "smokequartz": (0x78, 0x66, 0x58),  # smoky quartz
    "kilnite": (0xB4, 0x5C, 0x30),      # kiln terracotta
}

EN_NAME = {m: m.capitalize() for m in MATERIALS}

REDSTONE = (0xB0, 0x24, 0x14)


def ramp(base):
    """Dark -> light 4-step shade ramp used by the stone-shape painters."""
    return [_shade(base, -56), _shade(base, -24), base, _shade(base, 36)]


def mortar(base):
    return _shade(base, -88)


def accents(base):
    return [_shade(base, 56), _shade(base, 96)]


# ---------------------------------------------------------------------------
# Texture painters (16x16, deterministic via lib_gen.seeded)
# ---------------------------------------------------------------------------


def ore_speckle(base_img: Image.Image, rng: Random, colors, highlight) -> Image.Image:
    """Host-stone face with 5 2x2 material nuggets (infernodim_gen ore style)."""
    img = base_img.convert("RGB").copy()
    for _ in range(5):
        px, py = rng.randrange(1, 13), rng.randrange(1, 13)
        color = rng.choice(colors)
        for dx, dy in ((0, 0), (1, 0), (0, 1), (1, 1)):
            img.putpixel((px + dx, py + dy), color)
        img.putpixel((px + 1, py), highlight)
    return img


def metal_cube(rng: Random, shades) -> Image.Image:
    """Storage-block face: bright mottle with a light sheen row and dark base row."""
    img = noise_cube(rng, [shades[1], shades[2], shades[2], shades[3]])
    for x in range(16):
        img.putpixel((x, 0), shades[3])
        img.putpixel((x, 15), shades[0])
    return img


def cut_cube(rng: Random, shades, edge) -> Image.Image:
    """Cut face: mottle with a 1px frame plus an inner seam ring (cut-copper style)."""
    img = noise_cube(rng, [shades[1], shades[2], shades[2], shades[3]])
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), edge)
    for i in range(3, 13):
        for x, y in ((i, 3), (i, 12), (3, i), (12, i)):
            img.putpixel((x, y), shades[0])
    return img


def raw_cube(rng: Random, base) -> Image.Image:
    """Raw-block face: duller, rougher mottle with dark pits."""
    img = noise_cube(rng, [_shade(base, -44), _shade(base, -20), base, _shade(base, 8)])
    px = [(x, y) for y in range(16) for x in range(16)]
    rng2 = Random(rng.random())
    for x, y in px:
        if rng2.random() < 0.05:
            img.putpixel((x, y), _shade(base, -64))
    return img


def grate_texture(rng: Random, shades, edge) -> Image.Image:
    """Copper-grate style RGBA lattice: 2x2 checkerboard holes, solid rim (CUTOUT)."""
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    for y in range(16):
        for x in range(16):
            if (x // 2 + y // 2) % 2 == 0:
                img.putpixel((x, y), (*rng.choice(shades[1:]), 255))
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), (*edge, 255))
    return img


def bulb_texture(rng: Random, shades, edge, center, lit: bool, powered: bool) -> Image.Image:
    """Copper-bulb style face: framed metal shell around a square glow window."""
    img = noise_cube(rng, [shades[0], shades[1], shades[1]])
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), edge)
    for y in range(4, 12):
        for x in range(4, 12):
            if lit:
                img.putpixel((x, y), center if (x + y) % 2 == 0 else _shade(center, 24))
            else:
                img.putpixel((x, y), _shade(center, -72) if (x + y) % 2 == 0 else _shade(center, -88))
    if lit:
        for x, y in ((7, 7), (8, 7), (7, 8), (8, 8)):
            img.putpixel((x, y), _shade(center, 56))
    if powered:
        for x, y in ((1, 1), (14, 1), (1, 14), (14, 14)):
            img.putpixel((x, y), REDSTONE)
    return img


def lantern_texture(rng: Random, metal, flame) -> Image.Image:
    """Lantern texture in the vanilla template_lantern UV layout (transparent RGBA);
    verbatim port of cinderstone_gen.lantern_texture, parameterized on palettes."""
    m_light, m_base, m_dark, m_darker = metal
    f_light, f_base, f_dark = flame
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(2, 9):  # body sides: caged frame around a glowing window
        for x in range(0, 6):
            if x in (0, 5) or y in (2, 8):
                px[x, y] = (*m_darker, 255)
            elif x in (1, 4) or y == 3:
                px[x, y] = (*m_dark, 255) if (x * 31 + y * 17) % 5 < 3 else (*m_base, 255)
            else:
                px[x, y] = (*f_light, 255) if (x * 31 + y * 17 + 7) % 5 == 0 else (*f_base, 255)
    px[2, 7] = (*f_dark, 255)
    px[3, 5] = (*f_light, 255)
    for y in range(9, 15):  # body top/bottom
        for x in range(0, 6):
            if x in (0, 5) or y in (9, 14):
                px[x, y] = (*m_darker, 255)
            else:
                px[x, y] = (*m_base, 255) if (x * 31 + y * 17) % 5 < 3 else (*m_dark, 255)
    for y in range(0, 2):  # cap sides
        for x in range(1, 5):
            px[x, y] = (*m_dark, 255) if y == 0 else (*m_base, 255)
    for y in range(0, 13):  # handle/chain strip
        px[12, y] = (*m_darker, 255)
        if y % 2 == 0:
            px[11, y] = (*m_dark, 255)
        else:
            px[13, y] = (*m_dark, 255)
    return img


def lantern_item_texture(rng: Random, metal, flame) -> Image.Image:
    """Flat 16x16 item sprite: small caged lantern with glowing window; verbatim port of
    cinderstone_gen.lantern_item_texture."""
    m_light, m_base, m_dark, m_darker = metal
    f_light, f_base, f_dark = flame
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    px[7, 1] = (*m_dark, 255)
    px[8, 1] = (*m_dark, 255)
    px[7, 2] = (*m_darker, 255)
    px[8, 2] = (*m_darker, 255)
    for x in range(5, 11):
        px[x, 3] = (*m_dark, 255)
        px[x, 4] = (*m_base, 255)
    for y in range(5, 13):
        for x in range(4, 12):
            if x in (4, 11) or y in (5, 12):
                px[x, y] = (*m_darker, 255)
            elif x in (5, 10):
                px[x, y] = (*m_dark, 255) if (x * 31 + y * 17) % 5 < 3 else (*m_base, 255)
            else:
                px[x, y] = (*f_light, 255) if (x * 31 + y * 17 + 3) % 5 == 0 else (*f_base, 255)
    px[7, 11] = (*f_dark, 255)
    px[8, 7] = (*f_light, 255)
    for x in range(5, 11):
        px[x, 13] = (*m_dark, 255)
    return img


def paint_material_textures(m: str) -> int:
    """Write the 20 block + 4 item PNGs of one material; returns the file count."""
    base = BASE_RGB[m]
    shades = ramp(base)
    mort = mortar(base)
    acc = accents(base)
    block_dir = ASSETS / "textures" / "block"
    item_dir = ASSETS / "textures" / "item"
    block_dir.mkdir(parents=True, exist_ok=True)
    item_dir.mkdir(parents=True, exist_ok=True)

    stone_base = extract_vanilla("assets/minecraft/textures/block/stone.png")
    deepslate_base = extract_vanilla("assets/minecraft/textures/block/deepslate.png")
    cinder_base = Image.open(block_dir / "cinderstone.png").convert("RGBA")
    nugget_colors = [base, _shade(base, -28), _shade(base, 40)]
    highlight = _shade(base, 84)

    blocks = {
        f"{m}_ore": ore_speckle(stone_base, seeded(f"{m}_ore"), nugget_colors, highlight),
        f"deepslate_{m}_ore": ore_speckle(deepslate_base, seeded(f"deepslate_{m}_ore"),
                                          nugget_colors, highlight),
        f"cinder_{m}_ore": ore_speckle(cinder_base, seeded(f"cinder_{m}_ore"),
                                       nugget_colors, highlight),
        f"raw_{m}_block": raw_cube(seeded(f"raw_{m}_block"), base),
        f"{m}_block": metal_cube(seeded(f"{m}_block"), shades),
        f"{m}_bricks": brick_overlay(seeded(f"{m}_bricks"), shades, mort,
                                     accents=acc, accent_prob=0.04),
        f"{m}_tiles": tile_overlay(seeded(f"{m}_tiles"), shades, mort,
                                   accents=acc, accent_prob=0.04),
        f"cut_{m}": cut_cube(seeded(f"cut_{m}"), shades, mort),
        f"chiseled_{m}": pillar_top(seeded(f"chiseled_{m}"), shades, mort, acc),
        f"{m}_pillar_side": pillar_side(seeded(f"{m}_pillar_side"), shades, mort, acc, 0.25),
        f"{m}_pillar_top": pillar_top(seeded(f"{m}_pillar_top"), shades, mort, acc),
        f"{m}_lamp": lamp_glow(seeded(f"{m}_lamp"), [shades[0], shades[1]], mort,
                               [acc[1], shades[3], acc[0]]),
        f"{m}_bulb": bulb_texture(seeded(f"{m}_bulb"), shades, mort, acc[1], False, False),
        f"{m}_bulb_lit": bulb_texture(seeded(f"{m}_bulb_lit"), shades, mort, acc[1], True, False),
        f"{m}_bulb_powered": bulb_texture(seeded(f"{m}_bulb_powered"), shades, mort,
                                          acc[1], False, True),
        f"{m}_bulb_lit_powered": bulb_texture(seeded(f"{m}_bulb_lit_powered"), shades, mort,
                                              acc[1], True, True),
        f"{m}_grate": grate_texture(seeded(f"{m}_grate"), shades, mort),
        f"{m}_glass": glass_frame(seeded(f"{m}_glass"), mort, shades[1],
                                  _shade(base, 18), glow=None),
        f"{m}_glass_pane_top": pane_edge(seeded(f"{m}_glass_pane_top"),
                                         shades[2], shades[1], mort),
        f"{m}_lantern": lantern_texture(seeded(f"{m}_lantern"),
                                        (shades[3], shades[2], shades[1], mort),
                                        (acc[1], acc[0], _shade(base, 12))),
    }
    for name, img in blocks.items():
        img.save(block_dir / f"{name}.png")

    item_ramp = [_shade(base, -72), _shade(base, -32), base, _shade(base, 40), _shade(base, 84)]
    items = {
        f"raw_{m}": recolor(extract_vanilla("assets/minecraft/textures/item/raw_iron.png"),
                            item_ramp),
        f"{m}_ingot": recolor(extract_vanilla("assets/minecraft/textures/item/iron_ingot.png"),
                              item_ramp),
        f"{m}_nugget": recolor(extract_vanilla("assets/minecraft/textures/item/iron_nugget.png"),
                               item_ramp),
        f"{m}_lantern": lantern_item_texture(seeded(f"item/{m}_lantern"),
                                             (shades[3], shades[2], shades[1], mort),
                                             (acc[1], acc[0], _shade(base, 12))),
    }
    for name, img in items.items():
        img.save(item_dir / f"{name}.png")
    return len(blocks) + len(items)


# ---------------------------------------------------------------------------
# JSON emitters
# ---------------------------------------------------------------------------


def ore_loot(name: str, raw_item: str) -> dict:
    """Silk-touch/fortune ore loot table dropping raw_<m>; exact copy of the shipped
    data/copper_inferno/loot_table/blocks/infernium_ore.json structure."""
    return {
        "pools": [{
            "bonus_rolls": 0.0,
            "entries": [{
                "type": "minecraft:alternatives",
                "children": [
                    {
                        "type": "minecraft:item",
                        "conditions": [{
                            "condition": "minecraft:match_tool",
                            "predicate": {
                                "predicates": {
                                    "minecraft:enchantments": [{
                                        "enchantments": "minecraft:silk_touch",
                                        "levels": {"min": 1},
                                    }],
                                },
                            },
                        }],
                        "name": f"{NS}:{name}",
                    },
                    {
                        "type": "minecraft:item",
                        "functions": [
                            {
                                "enchantment": "minecraft:fortune",
                                "formula": "minecraft:ore_drops",
                                "function": "minecraft:apply_bonus",
                            },
                            {"function": "minecraft:explosion_decay"},
                        ],
                        "name": f"{NS}:{raw_item}",
                    },
                ],
            }],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/{name}",
        "type": "minecraft:block",
    }


def emit_bulb(name: str) -> dict:
    """Copper-bulb format: lit/powered 4-variant blockstate + 4 cube_all models."""
    files = {
        _bs(name): {"variants": {
            "lit=false,powered=false": {"model": block_ref(name)},
            "lit=false,powered=true": {"model": block_ref(f"{name}_powered")},
            "lit=true,powered=false": {"model": block_ref(f"{name}_lit")},
            "lit=true,powered=true": {"model": block_ref(f"{name}_lit_powered")},
        }},
        _it(name): item_def(block_ref(name)),
    }
    for suffix in ("", "_lit", "_powered", "_lit_powered"):
        files[_bm(f"{name}{suffix}")] = {
            "parent": "minecraft:block/cube_all",
            "textures": {"all": block_ref(f"{name}{suffix}")},
        }
    return merge(files, loot_drop_self(name))


def emit_material_assets(m: str) -> dict:
    """All blockstates/models/item defs/loot of one material's 22 blocks + 3 items."""
    files = {}
    for ore in (f"{m}_ore", f"deepslate_{m}_ore", f"cinder_{m}_ore"):
        d = emit_cube(ore)
        d[_lt(ore)] = ore_loot(ore, f"raw_{m}")  # replace drop-self with the ore table
        files = merge(files, d)
    files = merge(
        files,
        emit_cube(f"raw_{m}_block"),
        emit_cube(f"{m}_block"),
        emit_cube_family(f"{m}_bricks", f"{m}_brick"),
        emit_cube_family(f"{m}_tiles", f"{m}_tile"),
        emit_cube(f"cut_{m}"),
        emit_cube(f"chiseled_{m}"),
        emit_pillar(f"{m}_pillar"),
        emit_lamp(f"{m}_lamp"),
        emit_bulb(f"{m}_bulb"),
        emit_cube(f"{m}_grate"),
        emit_glass(f"{m}_glass"),
        emit_pane(f"{m}_glass_pane", f"{m}_glass"),
        emit_lantern(f"{m}_lantern"),
    )
    for it in (f"raw_{m}", f"{m}_ingot", f"{m}_nugget"):
        files = merge(files, {
            _im(it): {"parent": "minecraft:item/generated",
                      "textures": {"layer0": f"{NS}:item/{it}"}},
            _it(it): item_def(f"{NS}:item/{it}"),
        })
    return files


# ---------------------------------------------------------------------------
# Worldgen (formats copied from the shipped infernium_ore configured/placed pair)
# ---------------------------------------------------------------------------


def emit_worldgen(m: str, count_overworld: int, count_cinder: int) -> None:
    cf = DATA / "worldgen" / "configured_feature"
    pf = DATA / "worldgen" / "placed_feature"

    write_json(cf / f"{m}_ore.json", {
        "config": {
            "discard_chance_on_air_exposure": 0.0,
            "size": 9,
            "targets": [
                {
                    "state": {"Name": f"{NS}:{m}_ore"},
                    "target": {"block": "minecraft:stone",
                               "predicate_type": "minecraft:block_match"},
                },
                {
                    "state": {"Name": f"{NS}:deepslate_{m}_ore"},
                    "target": {"block": "minecraft:deepslate",
                               "predicate_type": "minecraft:block_match"},
                },
            ],
        },
        "type": "minecraft:ore",
    })
    write_json(pf / f"{m}_ore.json", {
        "feature": f"{NS}:{m}_ore",
        "placement": [
            {"count": count_overworld, "type": "minecraft:count"},
            {"type": "minecraft:in_square"},
            {
                "height": {
                    "max_inclusive": {"below_top": 10},
                    "min_inclusive": {"above_bottom": 10},
                    "type": "minecraft:uniform",
                },
                "type": "minecraft:height_range",
            },
            {"type": "minecraft:biome"},
        ],
    })

    write_json(cf / f"cinder_{m}_ore.json", {
        "config": {
            "discard_chance_on_air_exposure": 0.0,
            "size": 10,
            "targets": [{
                "state": {"Name": f"{NS}:cinder_{m}_ore"},
                "target": {"block": f"{NS}:cinderstone",
                           "predicate_type": "minecraft:block_match"},
            }],
        },
        "type": "minecraft:ore",
    })
    write_json(pf / f"cinder_{m}_ore.json", {
        "feature": f"{NS}:cinder_{m}_ore",
        "placement": [
            {"count": count_cinder, "type": "minecraft:count"},
            {"type": "minecraft:in_square"},
            {
                "height": {
                    "max_inclusive": {"below_top": 10},
                    "min_inclusive": {"above_bottom": 10},
                    "type": "minecraft:uniform",
                },
                "type": "minecraft:height_range",
            },
            {"type": "minecraft:biome"},
        ],
    })


# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 formats, mirroring devtools/gen/infernium_gen.py).
# Unique-input rule: every crafting input set contains at least one id unique to
# the material (its ore, raw, ingot, nugget or a derived block), so no collision
# with vanilla or with the other 11 materials is possible.
# ---------------------------------------------------------------------------


def rp(name: str) -> Path:
    return DATA / "recipe" / "gemalloy" / f"{name}.json"


def emit_recipes(m: str) -> int:
    ing = f"{NS}:{m}_ingot"
    nug = f"{NS}:{m}_nugget"
    raw = f"{NS}:raw_{m}"
    n = 0

    def w(name: str, obj: dict) -> None:
        nonlocal n
        write_json(rp(name), obj)
        n += 1

    # --- smelting / blasting: raw chunk + all three ores -> ingot
    for src_name, src in ((f"raw_{m}", raw), (f"{m}_ore", f"{NS}:{m}_ore"),
                          (f"deepslate_{m}_ore", f"{NS}:deepslate_{m}_ore"),
                          (f"cinder_{m}_ore", f"{NS}:cinder_{m}_ore")):
        w(f"{m}_ingot_from_smelting_{src_name}", {
            "type": "minecraft:smelting", "category": "misc", "cookingtime": 200,
            "experience": 0.7, "group": f"{m}_ingot", "ingredient": src,
            "result": {"id": ing},
        })
        w(f"{m}_ingot_from_blasting_{src_name}", {
            "type": "minecraft:blasting", "category": "misc", "cookingtime": 100,
            "experience": 0.7, "group": f"{m}_ingot", "ingredient": src,
            "result": {"id": ing},
        })

    # --- storage cycles: 9 nuggets <-> ingot, 9 ingots <-> block, 9 raw <-> raw block
    w(f"{m}_ingot_from_nuggets", {
        "type": "minecraft:crafting_shaped", "category": "misc", "group": f"{m}_ingot",
        "key": {"#": nug}, "pattern": ["###", "###", "###"],
        "result": {"count": 1, "id": ing},
    })
    w(f"{m}_nugget", {
        "type": "minecraft:crafting_shapeless", "category": "misc",
        "ingredients": [ing], "result": {"count": 9, "id": nug},
    })
    w(f"{m}_block", {
        "type": "minecraft:crafting_shaped", "category": "building",
        "key": {"#": ing}, "pattern": ["###", "###", "###"],
        "result": {"count": 1, "id": f"{NS}:{m}_block"},
    })
    w(f"{m}_ingot_from_{m}_block", {
        "type": "minecraft:crafting_shapeless", "category": "misc", "group": f"{m}_ingot",
        "ingredients": [f"{NS}:{m}_block"], "result": {"count": 9, "id": ing},
    })
    w(f"raw_{m}_block", {
        "type": "minecraft:crafting_shaped", "category": "building",
        "key": {"#": raw}, "pattern": ["###", "###", "###"],
        "result": {"count": 1, "id": f"{NS}:raw_{m}_block"},
    })
    w(f"raw_{m}_from_raw_{m}_block", {
        "type": "minecraft:crafting_shapeless", "category": "misc",
        "ingredients": [f"{NS}:raw_{m}_block"], "result": {"count": 9, "id": raw},
    })

    # --- deco shapes by crafting (2x2 chain block -> bricks -> tiles -> cut)
    def shaped(name: str, key: dict, pattern: list, result: str, count: int,
               category: str = "building") -> None:
        w(name, {
            "type": "minecraft:crafting_shaped", "category": category,
            "key": key, "pattern": pattern,
            "result": {"count": count, "id": f"{NS}:{result}"},
        })

    shaped(f"{m}_bricks", {"#": f"{NS}:{m}_block"}, ["##", "##"], f"{m}_bricks", 4)
    shaped(f"{m}_tiles", {"#": f"{NS}:{m}_bricks"}, ["##", "##"], f"{m}_tiles", 4)
    shaped(f"cut_{m}", {"#": f"{NS}:{m}_tiles"}, ["##", "##"], f"cut_{m}", 4)
    shaped(f"chiseled_{m}", {"#": f"{NS}:{m}_brick_slab"}, ["#", "#"], f"chiseled_{m}", 1)
    shaped(f"{m}_pillar", {"#": f"{NS}:cut_{m}"}, ["#", "#"], f"{m}_pillar", 2)
    shaped(f"{m}_brick_slab", {"#": f"{NS}:{m}_bricks"}, ["###"], f"{m}_brick_slab", 6)
    shaped(f"{m}_brick_stairs", {"#": f"{NS}:{m}_bricks"}, ["#  ", "## ", "###"],
           f"{m}_brick_stairs", 4)
    shaped(f"{m}_brick_wall", {"#": f"{NS}:{m}_bricks"}, ["###", "###"], f"{m}_brick_wall", 6)
    shaped(f"{m}_tile_slab", {"#": f"{NS}:{m}_tiles"}, ["###"], f"{m}_tile_slab", 6)
    shaped(f"{m}_tile_stairs", {"#": f"{NS}:{m}_tiles"}, ["#  ", "## ", "###"],
           f"{m}_tile_stairs", 4)
    shaped(f"{m}_tile_wall", {"#": f"{NS}:{m}_tiles"}, ["###", "###"], f"{m}_tile_wall", 6)
    shaped(f"{m}_lamp", {"#": ing, "G": "minecraft:glowstone"}, [" # ", "#G#", " # "],
           f"{m}_lamp", 1, category="redstone")
    shaped(f"{m}_bulb", {"C": f"{NS}:{m}_block", "B": "minecraft:blaze_rod",
                         "R": "minecraft:redstone"}, [" C ", "CBC", " R "],
           f"{m}_bulb", 4, category="redstone")
    shaped(f"{m}_grate", {"M": f"{NS}:{m}_block"}, [" M ", "M M", " M "], f"{m}_grate", 4)
    shaped(f"{m}_glass", {"N": nug, "G": "minecraft:glass"}, ["NGN", "G G", "NGN"],
           f"{m}_glass", 8)
    shaped(f"{m}_glass_pane", {"#": f"{NS}:{m}_glass"}, ["###", "###"], f"{m}_glass_pane", 16)
    shaped(f"{m}_lantern", {"X": nug, "#": "minecraft:torch"}, ["XXX", "X#X", "XXX"],
           f"{m}_lantern", 1, category="misc")

    # --- stonecutting: every deco shape straight from <m>_block
    for result, count in ((f"{m}_bricks", 1), (f"{m}_tiles", 1), (f"cut_{m}", 1),
                          (f"chiseled_{m}", 1), (f"{m}_pillar", 1),
                          (f"{m}_brick_slab", 2), (f"{m}_brick_stairs", 1),
                          (f"{m}_brick_wall", 1), (f"{m}_tile_slab", 2),
                          (f"{m}_tile_stairs", 1), (f"{m}_tile_wall", 1),
                          (f"{m}_grate", 4)):
        w(f"{result}_from_{m}_block_stonecutting", {
            "type": "minecraft:stonecutting", "ingredient": f"{NS}:{m}_block",
            "result": {"count": count, "id": f"{NS}:{result}"},
        })
    return n


# ---------------------------------------------------------------------------
# Lang fragments (EN + real German; 25 keys per material = 300 total)
# ---------------------------------------------------------------------------


def lang_en(m: str) -> dict:
    n = EN_NAME[m]
    b = f"block.{NS}."
    i = f"item.{NS}."
    return {
        f"{b}{m}_ore": f"{n} Ore",
        f"{b}deepslate_{m}_ore": f"Deepslate {n} Ore",
        f"{b}cinder_{m}_ore": f"Cinder {n} Ore",
        f"{b}raw_{m}_block": f"Block of Raw {n}",
        f"{b}{m}_block": f"Block of {n}",
        f"{b}{m}_bricks": f"{n} Bricks",
        f"{b}{m}_brick_slab": f"{n} Brick Slab",
        f"{b}{m}_brick_stairs": f"{n} Brick Stairs",
        f"{b}{m}_brick_wall": f"{n} Brick Wall",
        f"{b}{m}_tiles": f"{n} Tiles",
        f"{b}{m}_tile_slab": f"{n} Tile Slab",
        f"{b}{m}_tile_stairs": f"{n} Tile Stairs",
        f"{b}{m}_tile_wall": f"{n} Tile Wall",
        f"{b}cut_{m}": f"Cut {n}",
        f"{b}chiseled_{m}": f"Chiseled {n}",
        f"{b}{m}_pillar": f"{n} Pillar",
        f"{b}{m}_lamp": f"{n} Lamp",
        f"{b}{m}_bulb": f"{n} Bulb",
        f"{b}{m}_grate": f"{n} Grate",
        f"{b}{m}_glass": f"{n} Glass",
        f"{b}{m}_glass_pane": f"{n} Glass Pane",
        f"{b}{m}_lantern": f"{n} Lantern",
        f"{i}raw_{m}": f"Raw {n}",
        f"{i}{m}_ingot": f"{n} Ingot",
        f"{i}{m}_nugget": f"{n} Nugget",
    }


def lang_de(m: str) -> dict:
    n = EN_NAME[m]  # the fantasy material name stays untranslated (neuter metal noun)
    b = f"block.{NS}."
    i = f"item.{NS}."
    return {
        f"{b}{m}_ore": f"{n}-Erz",
        f"{b}deepslate_{m}_ore": f"Tiefenschiefer-{n}-Erz",
        f"{b}cinder_{m}_ore": f"Zinder-{n}-Erz",
        f"{b}raw_{m}_block": f"Roh-{n}-Block",
        f"{b}{m}_block": f"{n}-Block",
        f"{b}{m}_bricks": f"{n}-Ziegel",
        f"{b}{m}_brick_slab": f"{n}-Ziegelstufe",
        f"{b}{m}_brick_stairs": f"{n}-Ziegeltreppe",
        f"{b}{m}_brick_wall": f"{n}-Ziegelmauer",
        f"{b}{m}_tiles": f"{n}-Fliesen",
        f"{b}{m}_tile_slab": f"{n}-Fliesenstufe",
        f"{b}{m}_tile_stairs": f"{n}-Fliesentreppe",
        f"{b}{m}_tile_wall": f"{n}-Fliesenmauer",
        f"{b}cut_{m}": f"Geschnittenes {n}",
        f"{b}chiseled_{m}": f"Gemei\u00dfeltes {n}",
        f"{b}{m}_pillar": f"{n}-S\u00e4ule",
        f"{b}{m}_lamp": f"{n}-Lampe",
        f"{b}{m}_bulb": f"{n}-Gl\u00fchlampe",
        f"{b}{m}_grate": f"{n}-Gitter",
        f"{b}{m}_glass": f"{n}-Glas",
        f"{b}{m}_glass_pane": f"{n}-Glasscheibe",
        f"{b}{m}_lantern": f"{n}-Laterne",
        f"{i}raw_{m}": f"Roh-{n}",
        f"{i}{m}_ingot": f"{n}-Barren",
        f"{i}{m}_nugget": f"{n}-Klumpen",
    }


# ---------------------------------------------------------------------------
# Tag fragment (merged into data/minecraft/tags by devtools/merge_tags.py)
# ---------------------------------------------------------------------------


def block_ids(m: str) -> list[str]:
    """The 22 block ids of one material, in AlloySets registration order."""
    return [
        f"{m}_ore", f"deepslate_{m}_ore", f"cinder_{m}_ore",
        f"raw_{m}_block", f"{m}_block", f"{m}_bricks", f"{m}_brick_slab",
        f"{m}_brick_stairs", f"{m}_brick_wall", f"{m}_tiles", f"{m}_tile_slab",
        f"{m}_tile_stairs", f"{m}_tile_wall", f"cut_{m}", f"chiseled_{m}",
        f"{m}_pillar", f"{m}_lamp", f"{m}_bulb", f"{m}_grate", f"{m}_glass",
        f"{m}_glass_pane", f"{m}_lantern",
    ]


def emit_tagfrag() -> None:
    pickaxe = []
    needs_stone = []
    walls = []
    slabs = []
    stairs = []
    for m in MATERIALS:
        for bid in block_ids(m):
            if bid in (f"{m}_glass", f"{m}_glass_pane"):
                continue  # glass settings drop nothing by design (vanilla glass semantics)
            pickaxe.append(f"{NS}:{bid}")
        needs_stone += [f"{NS}:{m}_ore", f"{NS}:deepslate_{m}_ore", f"{NS}:cinder_{m}_ore"]
        walls += [f"{NS}:{m}_brick_wall", f"{NS}:{m}_tile_wall"]
        slabs += [f"{NS}:{m}_brick_slab", f"{NS}:{m}_tile_slab"]
        stairs += [f"{NS}:{m}_brick_stairs", f"{NS}:{m}_tile_stairs"]
    write_json(TAGFRAG / "gemalloy.json", {
        "block/mineable/pickaxe": sorted(pickaxe),
        "block/needs_stone_tool": sorted(needs_stone),
        "block/slabs": sorted(slabs),
        "block/stairs": sorted(stairs),
        "block/walls": sorted(walls),
    })


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    all_files = {}
    lang_en_all = {}
    lang_de_all = {}
    png_count = 0
    recipe_count = 0
    for idx, m in enumerate(MATERIALS):
        all_files = merge(all_files, emit_material_assets(m))
        emit_worldgen(m, count_overworld=7 + idx % 4, count_cinder=9 + idx % 3)
        recipe_count += emit_recipes(m)
        png_count += paint_material_textures(m)
        lang_en_all.update(lang_en(m))
        lang_de_all.update(lang_de(m))
    json_count = write_files(all_files, RES)
    write_json(ASSETS / "lang" / "fragments" / "gemalloy.json", lang_en_all)
    write_json(ASSETS / "lang" / "fragments_de" / "gemalloy.json", lang_de_all)
    emit_tagfrag()

    blockstates = [p for p in all_files if "/blockstates/" in p]
    items = [p for p in all_files if f"assets/{NS}/items/" in p]
    loot = [p for p in all_files if "/loot_table/" in p]
    assert len(blockstates) == 264, f"expected 264 blockstates, got {len(blockstates)}"
    assert len(loot) == 264, f"expected 264 loot tables, got {len(loot)}"
    assert len(items) == 300, f"expected 300 item definitions, got {len(items)}"
    assert len(lang_en_all) == len(lang_de_all) == 300, \
        f"expected 300 lang keys, got {len(lang_en_all)}/{len(lang_de_all)}"
    print(f"gemalloy_gen: {len(MATERIALS)} materials -> {len(blockstates)} blocks, "
          f"{len(items)} item defs, {json_count} asset/loot JSON files, "
          f"{recipe_count} recipes, {4 * len(MATERIALS)} worldgen JSON, "
          f"{png_count} textures, 2 lang fragments, 1 tag fragment.")


if __name__ == "__main__":
    main()
