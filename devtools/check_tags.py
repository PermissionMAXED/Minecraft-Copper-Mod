#!/usr/bin/env python3
"""Tag audit for COPPER INFERNO 1 (permanent; run alongside devtools/audit_assets.py).

Checks (exit non-zero and print findings if any fail):
  (t1) every JSON under data/minecraft/tags parses and follows the vanilla tag format:
       a top-level object whose "values" is a list of strings (ids or #tag refs),
       with an optional boolean "replace"
  (t2) every copper_inferno: id in a BLOCK tag has assets/copper_inferno/blockstates/<id>.json,
       and every copper_inferno: id in an ITEM tag has assets/copper_inferno/items/<id>.json
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
"""
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
TAGS = os.path.join(ROOT, "src/main/resources/data/minecraft/tags")
BLOCKSTATES = os.path.join(ROOT, "src/main/resources/assets/copper_inferno/blockstates")
ITEM_DEFS = os.path.join(ROOT, "src/main/resources/assets/copper_inferno/items")

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
    if not (is_block or is_item):
        finding("t2", f"{rel}: unexpected tag registry (expected block/ or item/)")
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
print(f"[check_tags] (t2) {checked_ids} copper_inferno ids resolved against blockstates/items")

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

# ---------- report ----------
print()
if findings:
    print(f"[check_tags] {len(findings)} FINDINGS:")
    for f in findings:
        print("  - " + f)
    sys.exit(1)
print("[check_tags] ZERO findings")
