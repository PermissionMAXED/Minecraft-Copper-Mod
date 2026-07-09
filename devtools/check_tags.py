#!/usr/bin/env python3
"""Tag audit for COPPER INFERNO 1 (permanent; run alongside devtools/audit_assets.py).

Checks (exit non-zero and print findings if any fail):
  (t1) every JSON under data/minecraft/tags parses and follows the vanilla tag format:
       a top-level object whose "values" is a list of strings (ids or #tag refs),
       with an optional boolean "replace"
  (t2) every copper_inferno: id in a BLOCK tag has assets/copper_inferno/blockstates/<id>.json,
       every copper_inferno: id in an ITEM tag has assets/copper_inferno/items/<id>.json, and
       every copper_inferno: id in an ENCHANTMENT tag (the 1.21+ data-driven enchantment
       registry) has data/copper_inferno/enchantment/<id>.json
  (t3) generative rules for every block family registered with requiresTool() (masonry 56,
       decostone 19, inferno 17, utilityblocks 8, sodablocks 10 = 110 ids): each MUST be listed
       in tags/block/mineable/pickaxe.json, or the block drops nothing in survival
  (t4) copper_inferno:copper_fence is in tags/block/fences.json
  (t5) all 18 mod walls are in tags/block/walls.json
  (t6) generative rules for every v3 block registered with requiresTool() (infernodim 7,
       cinderstone 52, copperdeco 28, infernium 2 = 89 ids): each MUST be listed in
       tags/block/mineable/pickaxe.json; the infernodim shovel-family blocks (ash_block,
       ember_soil, scorched_sand, cinder_gravel) MUST be in tags/block/mineable/shovel.json
  (t7) all 14 v3 walls (cinderstone 8 + copperdeco 6) are in tags/block/walls.json
  (t8) generative rules for the v4 PaletteSets families (chromacopper 16 palettes +
       depthstone 16 palettes, 20-id template each): every id EXCEPT <p>_glass/<p>_glass_pane
       (registered WITHOUT requiresTool) MUST be in tags/block/mineable/pickaxe.json (18 ids
       per palette = 576), and the 3 walls/slabs/stairs per palette MUST be in
       tags/block/walls.json / block/slabs.json / block/stairs.json (96 each)
  (t9) generative rules for the v4 AlloySets families (gemalloy, 12 materials, 22-id
       template each): every id EXCEPT <m>_glass/<m>_glass_pane (registered WITHOUT
       requiresTool in GemAlloyFeature.alloyGlassSettings) MUST be in
       tags/block/mineable/pickaxe.json (20 ids per material = 240), and the 3 ores per
       material MUST be in tags/block/needs_stone_tool.json (36)
  (t10) generative rules for the v4 WoodSets families (scorchwood, 8 woods): all 13 wood-set
       ids per wood MUST be in tags/block/mineable/axe.json (104), <w>_leaves MUST be in
       tags/block/mineable/hoe.json (8), and the 4 log-family ids per wood MUST be in
       tags/block/logs_that_burn.json (32)
"""
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
TAGS = os.path.join(ROOT, "src/main/resources/data/minecraft/tags")
BLOCKSTATES = os.path.join(ROOT, "src/main/resources/assets/copper_inferno/blockstates")
ITEM_DEFS = os.path.join(ROOT, "src/main/resources/assets/copper_inferno/items")
ENCHANT_DEFS = os.path.join(ROOT, "src/main/resources/data/copper_inferno/enchantment")

findings = []


def finding(check: str, msg: str) -> None:
    findings.append(f"({check}) {msg}")


# ---------- generative rules mirroring the requiresTool() registrations ----------
STAGES = ["", "exposed_", "weathered_", "oxidized_"]


def cube_family(name: str, stem: str) -> list[str]:
    return [name, stem + "_slab", stem + "_stairs", stem + "_wall"]


def masonry_ids() -> list[str]:
    """MasonryFeature: 2 oxidizing families x 4 stages x (cube/slab/stairs/wall + 3 waxed)."""
    ids = []
    for name, stem in [("copper_bricks", "copper_brick"), ("copper_tiles", "copper_tile")]:
        for p in STAGES:
            ids += [p + name, p + stem + "_slab", p + stem + "_stairs", p + stem + "_wall",
                    "waxed_" + p + name, "waxed_" + p + stem + "_slab", "waxed_" + p + stem + "_stairs"]
    return ids


def decostone_ids() -> list[str]:
    ids = []
    for name, stem in [("cut_copper_bricks", "cut_copper_brick"),
                       ("mossy_copper_bricks", "mossy_copper_brick"),
                       ("copper_mosaic", "copper_mosaic"),
                       ("copper_shingles", "copper_shingle")]:
        ids += cube_family(name, stem)
    return ids + ["chiseled_copper_bricks", "carved_copper", "copper_pillar"]


def inferno_ids() -> list[str]:
    ids = []
    for name, stem in [("inferno_bricks", "inferno_brick"),
                       ("inferno_tiles", "inferno_tile"),
                       ("charred_copper_bricks", "charred_copper_brick")]:
        ids += cube_family(name, stem)
    return ids + ["cracked_inferno_bricks", "chiseled_inferno_bricks", "charred_copper",
                  "inferno_pillar", "inferno_core"]


def utility_ids() -> list[str]:
    return [p + "copper_pressure_plate" for p in STAGES] + [
        "copper_fence", "copper_fence_gate", "copper_pipe", "copper_plating"]


def sodablocks_ids() -> list[str]:
    ids = []
    for name, stem in [("dr_pepper_can_bricks", "dr_pepper_can_brick"),
                       ("dr_pepper_can_tiles", "dr_pepper_can_tile")]:
        ids += cube_family(name, stem)
    return ids + ["crushed_can_block", "bottle_cap_block"]


REQUIRES_TOOL = {
    "masonry (56)": masonry_ids(),
    "decostone (19)": decostone_ids(),
    "inferno (17)": inferno_ids(),
    "utilityblocks (8)": utility_ids(),
    "sodablocks (10)": sodablocks_ids(),
}
assert [len(v) for v in REQUIRES_TOOL.values()] == [56, 19, 17, 8, 10]

ALL_WALLS = (
    [p + stem + "_wall" for stem in ("copper_brick", "copper_tile") for p in STAGES]  # masonry 8
    + [s + "_wall" for s in ("cut_copper_brick", "mossy_copper_brick", "copper_mosaic", "copper_shingle")]  # deco 4
    + [s + "_wall" for s in ("inferno_brick", "inferno_tile", "charred_copper_brick")]  # inferno 3
    + [s + "_wall" for s in ("dr_pepper_can_brick", "dr_pepper_can_tile", "sugar_brick")]  # sodablocks 3
)
assert len(ALL_WALLS) == 18

# ---------- v3 generative rules mirroring the v3 feature registrations ----------
# Family stems as passed to ModBlockFamilies.registerCubeFamily(name, stem, ...) in
# CinderStoneFeature / CopperDecoFeature (each family = base + slab + stairs + wall).
CINDERSTONE_FAMILIES = [
    ("cinderstone_bricks", "cinderstone_brick"),
    ("cinderstone_tiles", "cinderstone_tile"),
    ("polished_cinderstone", "polished_cinderstone"),
    ("slagstone_bricks", "slagstone_brick"),
    ("ashen_bricks", "ashen_brick"),
    ("smoldering_bricks", "smoldering_brick"),
    ("forge_bricks", "forge_brick"),
    ("quenched_slag", "quenched_slag"),
]
COPPERDECO_FAMILIES = [
    ("rose_copper_bricks", "rose_copper_brick"),
    ("burnished_copper", "burnished_copper"),
    ("burnished_copper_bricks", "burnished_copper_brick"),
    ("copper_panels", "copper_panel"),
    ("gilded_copper_bricks", "gilded_copper_brick"),
    ("verdigris_bricks", "verdigris_brick"),
]


def infernodim_v3_ids() -> list[str]:
    """InfernoDimensionFeature stoneSettings() (requiresTool) registrations. NOT included:
    the shovel-family soils (SHOVEL_V3 below) and the unbreakable inferno_portal."""
    return ["cinderstone", "cobbled_cinderstone", "slagstone", "infernium_ore",
            "smolder_crystal_ore", "molten_slag", "infernium_portal_frame"]


def cinderstone_v3_ids() -> list[str]:
    """CinderStoneFeature: 8 cube families (32) + 15 stone singles + 2 lanterns + 2 lamps
    + ember_coal_block, all requiresTool. NOT included: cinder/smolder glass + panes
    (glassSettings() has no requiresTool)."""
    ids = []
    for name, stem in CINDERSTONE_FAMILIES:
        ids += cube_family(name, stem)
    return ids + [
        "cracked_cinderstone_bricks", "chiseled_cinderstone_bricks", "cinderstone_pillar",
        "carved_cinderstone", "cracked_slagstone_bricks", "chiseled_slagstone_bricks",
        "slagstone_pillar", "quenched_slag_pillar", "cracked_forge_bricks",
        "chiseled_forge_bricks", "forge_pillar", "forge_heart", "ashen_pillar",
        "chiseled_ashen_bricks", "ashen_mosaic", "ember_lantern", "ashen_lantern",
        "smolder_lamp", "ashen_lamp", "ember_coal_block"]


def copperdeco_v3_ids() -> list[str]:
    """CopperDecoFeature: 6 cube families (24) + 4 singles, every block requiresTool."""
    ids = []
    for name, stem in COPPERDECO_FAMILIES:
        ids += cube_family(name, stem)
    return ids + ["chiseled_rose_copper_bricks", "rose_copper_pillar",
                  "burnished_copper_pillar", "verdigris_pillar"]


def infernium_v3_ids() -> list[str]:
    """InferniumFeature: both blocks requiresTool (see the tagfrag comment in the source)."""
    return ["infernium_block", "smolder_crystal_block"]


REQUIRES_TOOL_V3 = {
    "infernodim (7)": infernodim_v3_ids(),
    "cinderstone (52)": cinderstone_v3_ids(),
    "copperdeco (28)": copperdeco_v3_ids(),
    "infernium (2)": infernium_v3_ids(),
}
assert [len(v) for v in REQUIRES_TOOL_V3.values()] == [7, 52, 28, 2]

# InfernoDimensionFeature soil blocks: no requiresTool, but dig fastest with a shovel.
SHOVEL_V3 = ["ash_block", "ember_soil", "scorched_sand", "cinder_gravel"]

V3_WALLS = (
    [stem + "_wall" for _name, stem in CINDERSTONE_FAMILIES]  # cinderstone 8
    + [stem + "_wall" for _name, stem in COPPERDECO_FAMILIES]  # copperdeco 6
)
assert len(V3_WALLS) == 14

# ---------- v4 generative rules mirroring the v4 wrapper-registrar registrations ----------
# Palette names as passed (as string literals) to core.content.PaletteSets.registerPaletteSet
# in ChromaCopperFeature.init(); list mirrors PALETTES in devtools/gen/chromacopper_gen.py.
CHROMACOPPER_PALETTES = [
    "azure_copper", "crimson_copper", "tinted_gilded_copper", "cobalt_copper",
    "emerald_copper", "amethyst_copper", "obsidian_copper", "ivory_copper",
    "jade_copper", "umber_copper", "scarlet_copper", "indigo_copper",
    "viridian_copper", "onyx_copper", "pearl_copper", "saffron_copper",
]
# Palette names as passed to PaletteSets.registerPaletteSet in DepthStoneFeature.init();
# list mirrors PALETTES in devtools/gen/depthstone_gen.py.
DEPTHSTONE_PALETTES = [
    "voidstone", "duskshale", "pyroclast", "cindermarl", "fumarolite", "scorchslate",
    "emberchert", "slagbasalt", "ashflint", "charwacke", "smokestone", "kilnrock",
    "magmarl", "sootstone", "vitricite", "coalspar",
]
# Material names as passed to core.content.AlloySets.registerAlloySet in
# GemAlloyFeature.init(); list mirrors MATERIALS in devtools/gen/gemalloy_gen.py.
GEMALLOY_MATERIALS = [
    "pyrium", "emberite", "cindralite", "slagbronze", "ashsteel", "voidsteel",
    "doomium", "pepperite", "fizzium", "vitrium", "smokequartz", "kilnite",
]
# Wood names as passed to core.content.WoodSets.registerWoodSet in
# ScorchWoodFeature.init(); list mirrors WOODS in devtools/gen/scorchwood_gen.py.
SCORCHWOOD_WOODS = [
    "emberwood", "ashwillow", "cinderpine", "charoak",
    "glowbirch", "sootmaple", "duskthorn", "pyrewood",
]

assert len(CHROMACOPPER_PALETTES) == len(DEPTHSTONE_PALETTES) == 16
assert len(GEMALLOY_MATERIALS) == 12
assert len(SCORCHWOOD_WOODS) == 8


def palette_set_ids(p: str) -> list[str]:
    """The 20 ids of one PaletteSets.registerPaletteSet call, in registration order."""
    return [p, f"{p}_slab", f"{p}_stairs", f"{p}_wall",
            f"{p}_bricks", f"{p}_brick_slab", f"{p}_brick_stairs", f"{p}_brick_wall",
            f"{p}_tiles", f"{p}_tile_slab", f"{p}_tile_stairs", f"{p}_tile_wall",
            f"chiseled_{p}", f"carved_{p}", f"{p}_pillar", f"cut_{p}",
            f"{p}_lamp", f"{p}_lantern", f"{p}_glass", f"{p}_glass_pane"]


def alloy_set_ids(m: str) -> list[str]:
    """The 22 ids of one AlloySets.registerAlloySet call, in registration order."""
    return [f"{m}_ore", f"deepslate_{m}_ore", f"cinder_{m}_ore",
            f"raw_{m}_block", f"{m}_block", f"{m}_bricks", f"{m}_brick_slab",
            f"{m}_brick_stairs", f"{m}_brick_wall", f"{m}_tiles", f"{m}_tile_slab",
            f"{m}_tile_stairs", f"{m}_tile_wall", f"cut_{m}", f"chiseled_{m}",
            f"{m}_pillar", f"{m}_lamp", f"{m}_bulb", f"{m}_grate", f"{m}_glass",
            f"{m}_glass_pane", f"{m}_lantern"]


def wood_set_ids(w: str) -> list[str]:
    """The 13 ids of one WoodSets.registerWoodSet call, in registration order."""
    return [f"{w}_planks", f"{w}_plank_slab", f"{w}_plank_stairs", f"{w}_fence",
            f"{w}_fence_gate", f"{w}_button", f"{w}_pressure_plate", f"{w}_log",
            f"stripped_{w}_log", f"{w}_wood", f"stripped_{w}_wood", f"{w}_mosaic",
            f"{w}_pillar"]


def palette_pickaxe_ids(p: str) -> list[str]:
    """Every palette id with requiresTool(): all 20 except glass + glass pane, which the
    chroma/depth glass settings register WITHOUT requiresTool (hand-mineable)."""
    return [bid for bid in palette_set_ids(p)
            if bid not in (f"{p}_glass", f"{p}_glass_pane")]


def alloy_pickaxe_ids(m: str) -> list[str]:
    """Every alloy id with requiresTool(): all 22 except glass + glass pane
    (GemAlloyFeature.alloyGlassSettings has NO requiresTool, vanilla glass semantics)."""
    return [bid for bid in alloy_set_ids(m)
            if bid not in (f"{m}_glass", f"{m}_glass_pane")]


REQUIRES_TOOL_V4 = {}
for _p in CHROMACOPPER_PALETTES:
    REQUIRES_TOOL_V4[f"chromacopper {_p} (18)"] = palette_pickaxe_ids(_p)
for _p in DEPTHSTONE_PALETTES:
    REQUIRES_TOOL_V4[f"depthstone {_p} (18)"] = palette_pickaxe_ids(_p)
for _m in GEMALLOY_MATERIALS:
    REQUIRES_TOOL_V4[f"gemalloy {_m} (20)"] = alloy_pickaxe_ids(_m)
assert sum(len(v) for v in REQUIRES_TOOL_V4.values()) == 16 * 18 * 2 + 12 * 20  # 816

# Per palette: base/brick/tile wall, slab and stairs each join their vanilla shape tag.
V4_PALETTE_WALLS = [f"{p}{mid}_wall" for p in CHROMACOPPER_PALETTES + DEPTHSTONE_PALETTES
                    for mid in ("", "_brick", "_tile")]
V4_PALETTE_SLABS = [f"{p}{mid}_slab" for p in CHROMACOPPER_PALETTES + DEPTHSTONE_PALETTES
                    for mid in ("", "_brick", "_tile")]
V4_PALETTE_STAIRS = [f"{p}{mid}_stairs" for p in CHROMACOPPER_PALETTES + DEPTHSTONE_PALETTES
                     for mid in ("", "_brick", "_tile")]
assert len(V4_PALETTE_WALLS) == len(V4_PALETTE_SLABS) == len(V4_PALETTE_STAIRS) == 96

# Per material: the 3 ore variants use the vanilla iron-ore profile -> needs_stone_tool.
GEMALLOY_ORES = [oid for m in GEMALLOY_MATERIALS
                 for oid in (f"{m}_ore", f"deepslate_{m}_ore", f"cinder_{m}_ore")]
assert len(GEMALLOY_ORES) == 36

# Scorchwood: 13-id wood sets are axe-mineable; leaves are hoe-mineable; the 4 log-family
# ids per wood join logs_that_burn (charcoal smelting + vanilla fire behavior).
SCORCHWOOD_AXE = [bid for w in SCORCHWOOD_WOODS for bid in wood_set_ids(w)]
SCORCHWOOD_HOE = [f"{w}_leaves" for w in SCORCHWOOD_WOODS]
SCORCHWOOD_LOGS_THAT_BURN = [f"{prefix}{w}{suffix}" for w in SCORCHWOOD_WOODS
                             for prefix, suffix in (("", "_log"), ("stripped_", "_log"),
                                                    ("", "_wood"), ("stripped_", "_wood"))]
assert len(SCORCHWOOD_AXE) == 104
assert len(SCORCHWOOD_HOE) == 8
assert len(SCORCHWOOD_LOGS_THAT_BURN) == 32

# ---------- (t1) parse + vanilla-format validation ----------
tag_values: dict[str, list[str]] = {}  # tags-relative path (fwd slashes) -> values
tag_count = 0
if not os.path.isdir(TAGS):
    finding("t1", f"missing tag directory {os.path.relpath(TAGS, ROOT)}")
for dirpath, _dirs, files in os.walk(TAGS):
    for fn in sorted(files):
        if not fn.endswith(".json"):
            finding("t1", f"non-JSON file in tags tree: {fn}")
            continue
        path = os.path.join(dirpath, fn)
        rel = os.path.relpath(path, TAGS).replace(os.sep, "/")
        tag_count += 1
        try:
            with open(path, encoding="utf-8") as f:
                data = json.load(f)
        except (json.JSONDecodeError, UnicodeDecodeError) as e:
            finding("t1", f"JSON parse error in {rel}: {e}")
            continue
        if not isinstance(data, dict):
            finding("t1", f"{rel}: top level is not an object")
            continue
        for key in data:
            if key not in ("values", "replace"):
                finding("t1", f"{rel}: unexpected key {key!r} (vanilla tags only use values/replace)")
        if "replace" in data and not isinstance(data["replace"], bool):
            finding("t1", f"{rel}: \"replace\" is not a boolean")
        values = data.get("values")
        if not isinstance(values, list):
            finding("t1", f"{rel}: \"values\" missing or not a list")
            continue
        ok = []
        for v in values:
            if isinstance(v, str):
                ok.append(v)
            else:
                finding("t1", f"{rel}: non-string entry {v!r}")
        for d in sorted({v for v in ok if ok.count(v) > 1}):
            finding("t1", f"{rel}: duplicate entry {d}")
        tag_values[rel] = ok
print(f"[check_tags] (t1) {tag_count} tag files parsed/validated")

# ---------- (t2) every copper_inferno: id resolves to a blockstate / item definition ----------
checked_ids = 0
for rel, values in tag_values.items():
    is_block = rel.startswith("block/")
    is_item = rel.startswith("item/")
    is_enchant = rel.startswith("enchantment/")
    if not (is_block or is_item or is_enchant):
        finding("t2", f"{rel}: unexpected tag registry (expected block/, item/ or enchantment/)")
        continue
    for v in values:
        if v.startswith("#"):
            continue  # tag reference, nothing to resolve on disk
        ns, _, path = v.rpartition(":")
        if ns == "minecraft" or ns == "":
            continue
        if ns != "copper_inferno":
            finding("t2", f"{rel}: unexpected namespace in {v}")
            continue
        checked_ids += 1
        if is_block and not os.path.isfile(os.path.join(BLOCKSTATES, path + ".json")):
            finding("t2", f"{rel}: {v} has no blockstates/{path}.json")
        if is_item and not os.path.isfile(os.path.join(ITEM_DEFS, path + ".json")):
            finding("t2", f"{rel}: {v} has no items/{path}.json")
        if is_enchant and not os.path.isfile(os.path.join(ENCHANT_DEFS, path + ".json")):
            finding("t2", f"{rel}: {v} has no data/copper_inferno/enchantment/{path}.json")
print(f"[check_tags] (t2) {checked_ids} copper_inferno ids resolved against blockstates/items/enchantments")

# ---------- (t3) every requiresTool() block is pickaxe-mineable ----------
pickaxe = set(tag_values.get("block/mineable/pickaxe.json", []))
missing_total = 0
for group, ids in REQUIRES_TOOL.items():
    for bid in ids:
        if "copper_inferno:" + bid not in pickaxe:
            finding("t3", f"requiresTool block {group}: copper_inferno:{bid} missing from mineable/pickaxe.json (would drop NOTHING in survival)")
            missing_total += 1
print(f"[check_tags] (t3) {sum(len(v) for v in REQUIRES_TOOL.values())} requiresTool blocks checked, {missing_total} missing from mineable/pickaxe")

# ---------- (t4) copper_fence in fences.json ----------
if "copper_inferno:copper_fence" not in tag_values.get("block/fences.json", []):
    finding("t4", "copper_inferno:copper_fence missing from tags/block/fences.json")
print("[check_tags] (t4) fences.json checked")

# ---------- (t5) all 18 walls in walls.json ----------
walls = set(tag_values.get("block/walls.json", []))
for wid in ALL_WALLS:
    if "copper_inferno:" + wid not in walls:
        finding("t5", f"wall copper_inferno:{wid} missing from tags/block/walls.json")
print(f"[check_tags] (t5) {len(ALL_WALLS)} walls checked")

# ---------- (t6) every v3 requiresTool() block is pickaxe-mineable + shovel family ----------
missing_v3 = 0
for group, ids in REQUIRES_TOOL_V3.items():
    for bid in ids:
        if "copper_inferno:" + bid not in pickaxe:
            finding("t6", f"v3 requiresTool block {group}: copper_inferno:{bid} missing from mineable/pickaxe.json (would drop NOTHING in survival)")
            missing_v3 += 1
shovel = set(tag_values.get("block/mineable/shovel.json", []))
for bid in SHOVEL_V3:
    if "copper_inferno:" + bid not in shovel:
        finding("t6", f"infernodim soil block copper_inferno:{bid} missing from mineable/shovel.json")
        missing_v3 += 1
print(f"[check_tags] (t6) {sum(len(v) for v in REQUIRES_TOOL_V3.values())} v3 requiresTool blocks "
      f"+ {len(SHOVEL_V3)} shovel-family blocks checked, {missing_v3} missing")

# ---------- (t7) all 14 v3 walls in walls.json ----------
for wid in V3_WALLS:
    if "copper_inferno:" + wid not in walls:
        finding("t7", f"v3 wall copper_inferno:{wid} missing from tags/block/walls.json")
print(f"[check_tags] (t7) {len(V3_WALLS)} v3 walls checked")

# ---------- (t8/t9) every v4 requiresTool() block is pickaxe-mineable + shape/ore tags ----------
missing_v4 = 0
for group, ids in REQUIRES_TOOL_V4.items():
    for bid in ids:
        if "copper_inferno:" + bid not in pickaxe:
            finding("t8" if not group.startswith("gemalloy") else "t9",
                    f"v4 requiresTool block {group}: copper_inferno:{bid} missing from "
                    "mineable/pickaxe.json (would drop NOTHING in survival)")
            missing_v4 += 1
slabs = set(tag_values.get("block/slabs.json", []))
stairs = set(tag_values.get("block/stairs.json", []))
for wid in V4_PALETTE_WALLS:
    if "copper_inferno:" + wid not in walls:
        finding("t8", f"v4 palette wall copper_inferno:{wid} missing from tags/block/walls.json")
for sid in V4_PALETTE_SLABS:
    if "copper_inferno:" + sid not in slabs:
        finding("t8", f"v4 palette slab copper_inferno:{sid} missing from tags/block/slabs.json")
for sid in V4_PALETTE_STAIRS:
    if "copper_inferno:" + sid not in stairs:
        finding("t8", f"v4 palette stairs copper_inferno:{sid} missing from tags/block/stairs.json")
print(f"[check_tags] (t8) {16 * 18 * 2} palette requiresTool blocks + "
      f"{len(V4_PALETTE_WALLS)} walls + {len(V4_PALETTE_SLABS)} slabs + "
      f"{len(V4_PALETTE_STAIRS)} stairs checked")

needs_stone = set(tag_values.get("block/needs_stone_tool.json", []))
for oid in GEMALLOY_ORES:
    if "copper_inferno:" + oid not in needs_stone:
        finding("t9", f"gemalloy ore copper_inferno:{oid} missing from tags/block/needs_stone_tool.json")
print(f"[check_tags] (t9) {12 * 20} gemalloy requiresTool blocks + "
      f"{len(GEMALLOY_ORES)} ores checked, {missing_v4} v4 blocks missing from mineable/pickaxe")

# ---------- (t10) scorchwood axe/hoe mineability + logs_that_burn ----------
axe = set(tag_values.get("block/mineable/axe.json", []))
hoe = set(tag_values.get("block/mineable/hoe.json", []))
logs_that_burn = set(tag_values.get("block/logs_that_burn.json", []))
for bid in SCORCHWOOD_AXE:
    if "copper_inferno:" + bid not in axe:
        finding("t10", f"scorchwood block copper_inferno:{bid} missing from mineable/axe.json")
for bid in SCORCHWOOD_HOE:
    if "copper_inferno:" + bid not in hoe:
        finding("t10", f"scorchwood leaves copper_inferno:{bid} missing from mineable/hoe.json")
for bid in SCORCHWOOD_LOGS_THAT_BURN:
    if "copper_inferno:" + bid not in logs_that_burn:
        finding("t10", f"scorchwood log copper_inferno:{bid} missing from logs_that_burn.json")
print(f"[check_tags] (t10) {len(SCORCHWOOD_AXE)} scorchwood axe blocks + "
      f"{len(SCORCHWOOD_HOE)} hoe leaves + {len(SCORCHWOOD_LOGS_THAT_BURN)} burning logs checked")

# ---------- report ----------
print()
if findings:
    print(f"[check_tags] {len(findings)} FINDINGS:")
    for f in findings:
        print("  - " + f)
    sys.exit(1)
print("[check_tags] ZERO findings")
