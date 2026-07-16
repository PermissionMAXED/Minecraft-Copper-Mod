#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "infernogeology" feature.

25 NEW worldgen features for the Inferno dimension (12 ore/scattered-ore veins, 4
replace-blobs, 3 disks, 3 lava springs, 3 geyser/delta basins) plus the 18 NEW ore/deco
blocks they place, the brimstone item, silk-touch/fortune ore loot and 4 consuming
recipes. Wired in code (InfernoGeologyFeature) via Fabric BiomeModifications.addFeature
using two literal biome predicates: veins + replace-blobs into ALL SEVEN copper_inferno
biomes (cinder_wastes, ember_grove, slag_sea, verdigris_jungle, molten_delta, soot_dunes,
crystal_hollows); disks, springs and basins only into the five lava-shore biomes (the
old three + molten_delta + soot_dunes; crystal_hollows stays clean, verdigris_jungle
keeps its jungle floor). The biome JSONs under data/copper_inferno/worldgen/biome/ are
owned by infernodim/infernodim2 and are NOT touched.

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via genlib.rng_for; JSON via genlib.write_json). Emits by
DEFAULT (no flags):
  - 25 data/copper_inferno/worldgen/configured_feature/<name>.json and the 25 matching
    placed_feature/<name>.json. The worldgen JSON is NOT invented: every file is derived
    from the real vanilla 1.21.9 schemas extracted straight out of fabric-loom's
    minecraft-client.jar with zipfile (configured features ore_blackstone,
    ore_ancient_debris_large, ore_ancient_debris_small, basalt_blobs, disk_gravel,
    spring_nether_open, spring_nether_closed, spring_lava_nether, delta; placed features
    ore_quartz_nether, ore_gravel_nether, ore_blackstone, ore_magma,
    ore_ancient_debris_large, ore_debris_small, basalt_blobs, spring_open, spring_closed,
    spring_lava, delta), with only the documented substitutions applied (block names,
    sizes/radii, counts, height bands, valid_blocks lists). Documented deviations, both
    following devtools/gen/infernodim_gen.py:
      * scattered-ore target: vanilla tag_match base_stone_nether -> block_match
        copper_inferno:cinderstone (the mod stone is not in the vanilla tag); the
        block_match predicate schema is the one from vanilla ore_blackstone.
      * disk placement: the vanilla disk placements use overworld heightmap/water-filter
        modifiers that are dead under the Inferno's ceiling, so disks use the vanilla
        ore_magma placement (count + in_square + absolute height band + biome) exactly
        like infernodim's disk_scorched_sand.
      * blob state: vanilla basalt_blobs carries Properties {axis: y}; the mod blob
        blocks are plain axis-less cubes, so the Properties dict is dropped.
  - blockstates, cube_all block models, items/<id>.json model-definitions, 16x16
    textures for the 18 new blocks (genlib emitters)
  - loot tables: the 8 real ores get silk-touch/fortune tables that are the REAL vanilla
    1.21.9 loot JSON (data/minecraft/loot_table/blocks/<name>.json out of the client jar)
    with only the block/drop names, counts and random_sequence substituted —
    ember_iron_ore<-iron_ore (raw_iron), slag_copper_ore<-copper_ore (2-5 raw_copper),
    ash_gold_ore<-nether_gold_ore (2-6 gold_nugget), cinder_quartz_ore<-nether_quartz_ore
    (quartz), cinder_lapis_ore<-lapis_ore (4-9 lapis_lazuli),
    smolder_redstone_ore<-redstone_ore (4-5 redstone), brimstone_ore<-nether_quartz_ore
    (the new copper_inferno:brimstone). deep_infernium_ore retargets the mod's on-disk
    data/copper_inferno/loot_table/blocks/infernium_ore.json template (uniform 2-3
    copper_inferno:raw_infernium; set_count schema from vanilla copper_ore).
    scorched_debris and the 9 non-ore deco/sediment blocks keep drop-self.
  - the brimstone ITEM (item def + item/generated model + deterministic 16x16 sprite),
    registered via ModItems and added to the MAIN creative tab
  - 4 consuming recipes under data/copper_inferno/recipe/infernogeology/ (sulfur block
    <-> brimstone compression pair, fire charges, gunpowder; every input set contains a
    copper_inferno id so devtools/check_recipe_collisions.py stays green)
  - lang fragments: assets/copper_inferno/lang/fragments/infernogeology.json (EN) and
    fragments_de/infernogeology.json (real German); 18 block keys + 1 item key
  - devtools/tagfrag/infernogeology.json (mineable/pickaxe for the 15 requiresTool
    blocks, mineable/shovel for the 3 soft blocks; same shape as infernodim's fragment)
  - src/main/java/.../feature/infernogeology/InfernoGeologyFeature.java +
    InfernoGeologyHandbook.java (genlib.java_feature_class / java_handbook_class;
    literal ids only). The feature class carries the 25 literal
    BiomeModifications.addFeature calls (signature verified via javap on
    fabric-biome-api-v1: (Predicate<BiomeSelectionContext>, GenerationStep.Feature,
    RegistryKey<PlacedFeature>)); the handbook class carries 25 "dimension" entries
    (the ore entries name their drops) + 4 recipe entries = 29.
  - devtools/hooks/infernogeology.txt (integration hook file)

All block/loot/lang JSON structures come from genlib and are byte-identical to the
vanilla 1.21.9 formats. Do NOT "improve" them.
"""

import json
import os
import sys
import zipfile
from collections import namedtuple
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, rng_for, write_json

CLIENT_JAR = Path(os.path.expanduser("~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"))
FEATURE_DIR = (ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno"
               / "feature" / "infernogeology")
CF_DIR = DATA / "worldgen" / "configured_feature"
PF_DIR = DATA / "worldgen" / "placed_feature"

# Existing blocks (owned by infernodim) that these features may reference as targets /
# valid_blocks. Their blockstates are asserted to exist on disk in main().
EXISTING_BLOCKS = ["cinderstone", "ash_block", "cinder_gravel"]

# Existing items (owned by the infernium feature) that the loot tables / recipes
# reference. Their items/<id>.json defs are asserted to exist on disk in main().
EXISTING_ITEMS = ["raw_infernium", "ash_pile"]


def jar_json(entry: str):
    """Load a vanilla JSON file straight out of the 1.21.9 minecraft-client.jar
    (same extraction approach as devtools/gen/infernodim_gen.py)."""
    with zipfile.ZipFile(CLIENT_JAR) as jar:
        return json.loads(jar.read(entry).decode("utf-8"))


def vanilla_cf(name: str):
    return jar_json(f"data/minecraft/worldgen/configured_feature/{name}.json")


def vanilla_pf(name: str):
    return jar_json(f"data/minecraft/worldgen/placed_feature/{name}.json")


# ---------------------------------------------------------------------------
# Palette (house colors from genlib/infernodim_gen) + block table
# ---------------------------------------------------------------------------
EMBER = genlib.EMBER
EMBER_BRIGHT = genlib.EMBER_BRIGHT
EMBER_HOT = genlib.EMBER_HOT
CINDER_DARK = (0x2E, 0x24, 0x22)
CINDER = (0x3B, 0x2E, 0x2B)
CINDER_LIGHT = (0x4A, 0x39, 0x34)

# (bid, EN, DE, settings_expr, tool). settings_expr constructs a FRESH
# AbstractBlock.Settings per registration via the stoneSettings/softSettings helpers
# rendered into the feature class (never shared instances). tool: pickaxe = requiresTool
# (MUST be tagged mineable/pickaxe or it drops nothing), shovel = soft block (no
# requiresTool; tagged mineable/shovel for dig speed like infernodim's ash_block).
Blk = namedtuple("Blk", "bid en de settings tool")

BLOCKS = [
    # Ore veins in cinderstone (ore-block profile == infernodim's infernium_ore).
    Blk("ember_iron_ore", "Ember Iron Ore", "Gluteisenerz",
        "stoneSettings(MapColor.IRON_GRAY, BlockSoundGroup.NETHER_ORE, 3.0F, 3.0F)", "pickaxe"),
    Blk("ash_gold_ore", "Ash Gold Ore", "Aschgolderz",
        "stoneSettings(MapColor.GOLD, BlockSoundGroup.NETHER_ORE, 3.0F, 3.0F)", "pickaxe"),
    Blk("slag_copper_ore", "Slag Copper Ore", "Schlackenkupfererz",
        "stoneSettings(MapColor.TERRACOTTA_ORANGE, BlockSoundGroup.NETHER_ORE, 3.0F, 3.0F)", "pickaxe"),
    Blk("cinder_quartz_ore", "Cinder Quartz Ore", "Zunderquarzerz",
        "stoneSettings(MapColor.OFF_WHITE, BlockSoundGroup.NETHER_ORE, 3.0F, 3.0F)", "pickaxe"),
    Blk("brimstone_ore", "Brimstone Ore", "Schwefelsteinerz",
        "stoneSettings(MapColor.YELLOW, BlockSoundGroup.NETHER_ORE, 3.0F, 3.0F)", "pickaxe"),
    Blk("cinder_lapis_ore", "Cinder Lapis Ore", "Zunderlapiserz",
        "stoneSettings(MapColor.LAPIS_BLUE, BlockSoundGroup.NETHER_ORE, 3.0F, 3.0F)", "pickaxe"),
    Blk("smolder_redstone_ore", "Smolder Redstone Ore", "Schwel-Redstone-Erz",
        "stoneSettings(MapColor.BRIGHT_RED, BlockSoundGroup.NETHER_ORE, 3.0F, 3.0F)", "pickaxe"),
    Blk("deep_infernium_ore", "Deep Infernium Ore", "Tiefen-Infernium-Erz",
        "stoneSettings(MapColor.ORANGE, BlockSoundGroup.NETHER_ORE, 4.5F, 4.5F)", "pickaxe"),
    # Scattered debris (ancient-debris profile: blast-proof, ANCIENT_DEBRIS sounds).
    Blk("scorched_debris", "Scorched Debris", "Versengte Tr\u00fcmmer",
        "stoneSettings(MapColor.BLACK, BlockSoundGroup.ANCIENT_DEBRIS, 30.0F, 1200.0F)", "pickaxe"),
    # Deco stones seeded through cinderstone as ore-shaped pockets / blobs / rims.
    Blk("ashfall_tuff", "Ashfall Tuff", "Aschentuff",
        "stoneSettings(MapColor.TERRACOTTA_GRAY, BlockSoundGroup.TUFF, 1.5F, 6.0F)", "pickaxe"),
    Blk("ember_pumice", "Ember Pumice", "Glutbims",
        "stoneSettings(MapColor.TERRACOTTA_ORANGE, BlockSoundGroup.BASALT, 1.25F, 4.2F)", "pickaxe"),
    Blk("scoria", "Scoria", "Skoria",
        "stoneSettings(MapColor.DARK_RED, BlockSoundGroup.BASALT, 1.25F, 4.2F)", "pickaxe"),
    Blk("geyserite", "Geyserite", "Geysirit",
        "stoneSettings(MapColor.PALE_YELLOW, BlockSoundGroup.CALCITE, 0.75F, 0.75F)", "pickaxe"),
    Blk("hardened_slag", "Hardened Slag", "Geh\u00e4rtete Schlacke",
        "stoneSettings(MapColor.GRAY, BlockSoundGroup.NETHER_BRICKS, 2.0F, 6.0F)", "pickaxe"),
    Blk("sulfur_block", "Sulfur Block", "Schwefelblock",
        "stoneSettings(MapColor.YELLOW, BlockSoundGroup.CALCITE, 1.5F, 2.0F)", "pickaxe"),
    # Soft sediments (no requiresTool, exactly like infernodim's scorched_sand).
    Blk("sulfur_sand", "Sulfur Sand", "Schwefelsand",
        "softSettings(MapColor.PALE_YELLOW, BlockSoundGroup.SAND, 0.5F)", "shovel"),
    Blk("ember_grit", "Ember Grit", "Glutgrus",
        "softSettings(MapColor.ORANGE, BlockSoundGroup.GRAVEL, 0.6F)", "shovel"),
    Blk("cinder_silt", "Cinder Silt", "Zunderschlick",
        "softSettings(MapColor.GRAY, BlockSoundGroup.SOUL_SAND, 0.5F)", "shovel"),
]

BLOCK_IDS = [b.bid for b in BLOCKS]

# The one NEW item: what brimstone_ore drops and what the 4 recipes consume.
# (iid, EN, DE)
Itm = namedtuple("Itm", "iid en de")

ITEMS = [
    Itm("brimstone", "Brimstone", "Schwefelbrocken"),
]

ITEM_IDS = [i.iid for i in ITEMS]

# Silk-touch/fortune ore loot: block id -> (vanilla loot source | None for the mod's
# own infernium_ore template, drop item id, (min,max) set_count substitution | None to
# keep the source's count function untouched/absent). Ore blocks NOT listed here
# (scorched_debris) and all deco/sediment blocks keep drop-self.
ORE_LOOT = {
    "ember_iron_ore":       ("iron_ore",          "minecraft:raw_iron",       None),
    "slag_copper_ore":      ("copper_ore",        "minecraft:raw_copper",     (2, 5)),
    "ash_gold_ore":         ("nether_gold_ore",   "minecraft:gold_nugget",    (2, 6)),
    "cinder_quartz_ore":    ("nether_quartz_ore", "minecraft:quartz",         None),
    "cinder_lapis_ore":     ("lapis_ore",         "minecraft:lapis_lazuli",   (4, 9)),
    "smolder_redstone_ore": ("redstone_ore",      "minecraft:redstone",       (4, 5)),
    "deep_infernium_ore":   (None,                f"{NS}:raw_infernium",      (2, 3)),
    "brimstone_ore":        ("nether_quartz_ore", f"{NS}:brimstone",          None),
}

# Drop sentences appended to the ore "dimension" handbook entries (EN, DE).
ORE_DROPS_TEXT = {
    "ember_iron_ore": (
        "Drops 1 Raw Iron; Fortune raises the yield, Silk Touch drops the ore block.",
        "L\u00e4sst 1 Roheisen fallen; Gl\u00fcck erh\u00f6ht die Ausbeute, "
        "Behutsamkeit l\u00e4sst den Erzblock fallen."),
    "slag_copper_ore": (
        "Drops 2-5 Raw Copper; Fortune raises the yield, Silk Touch drops the ore block.",
        "L\u00e4sst 2-5 Rohkupfer fallen; Gl\u00fcck erh\u00f6ht die Ausbeute, "
        "Behutsamkeit l\u00e4sst den Erzblock fallen."),
    "ash_gold_ore": (
        "Drops 2-6 Gold Nuggets; Fortune raises the yield, Silk Touch drops the ore "
        "block.",
        "L\u00e4sst 2-6 Goldklumpen fallen; Gl\u00fcck erh\u00f6ht die Ausbeute, "
        "Behutsamkeit l\u00e4sst den Erzblock fallen."),
    "cinder_quartz_ore": (
        "Drops 1 Nether Quartz; Fortune raises the yield, Silk Touch drops the ore "
        "block.",
        "L\u00e4sst 1 Netherquarz fallen; Gl\u00fcck erh\u00f6ht die Ausbeute, "
        "Behutsamkeit l\u00e4sst den Erzblock fallen."),
    "cinder_lapis_ore": (
        "Drops 4-9 Lapis Lazuli; Fortune raises the yield, Silk Touch drops the ore "
        "block.",
        "L\u00e4sst 4-9 Lapislazuli fallen; Gl\u00fcck erh\u00f6ht die Ausbeute, "
        "Behutsamkeit l\u00e4sst den Erzblock fallen."),
    "smolder_redstone_ore": (
        "Drops 4-5 Redstone Dust; Fortune raises the yield, Silk Touch drops the ore "
        "block.",
        "L\u00e4sst 4-5 Redstone-Staub fallen; Gl\u00fcck erh\u00f6ht die Ausbeute, "
        "Behutsamkeit l\u00e4sst den Erzblock fallen."),
    "deep_infernium_ore": (
        "Drops 2-3 Raw Infernium; Fortune raises the yield, Silk Touch drops the ore "
        "block.",
        "L\u00e4sst 2-3 Roh-Infernium fallen; Gl\u00fcck erh\u00f6ht die Ausbeute, "
        "Behutsamkeit l\u00e4sst den Erzblock fallen."),
    "brimstone_ore": (
        "Drops 1 Brimstone; Fortune raises the yield, Silk Touch drops the ore block.",
        "L\u00e4sst 1 Schwefelbrocken fallen; Gl\u00fcck erh\u00f6ht die Ausbeute, "
        "Behutsamkeit l\u00e4sst den Erzblock fallen."),
}


def field_of(block_id: str) -> str:
    return block_id.upper()


# ---------------------------------------------------------------------------
# Worldgen derivation helpers (vanilla schema + documented substitutions only)
# ---------------------------------------------------------------------------

def ore_cf(state: str, size: int):
    """minecraft:ore schema from vanilla ore_blackstone (discard 0.0, block_match
    netherrack); netherrack -> cinderstone, state/size substituted."""
    cf = vanilla_cf("ore_blackstone")
    cfg = cf["config"]
    cfg["size"] = size
    cfg["targets"][0]["state"]["Name"] = state
    cfg["targets"][0]["target"]["block"] = f"{NS}:cinderstone"
    return cf


def scattered_cf(state: str, size: int):
    """minecraft:scattered_ore schema from vanilla ore_ancient_debris_large (discard
    1.0); size/state substituted; tag_match base_stone_nether -> block_match cinderstone
    (documented deviation, predicate schema from vanilla ore_blackstone)."""
    cf = vanilla_cf("ore_ancient_debris_large")
    cfg = cf["config"]
    cfg["size"] = size
    cfg["targets"][0]["state"]["Name"] = state
    cfg["targets"][0]["target"] = dict(
        vanilla_cf("ore_blackstone")["config"]["targets"][0]["target"])
    cfg["targets"][0]["target"]["block"] = f"{NS}:cinderstone"
    return cf


def blob_cf(state: str, r_min: int, r_max: int):
    """minecraft:netherrack_replace_blobs schema from vanilla basalt_blobs; radius/state
    substituted, netherrack target -> cinderstone, basalt axis Properties dropped (the
    mod blocks are plain cubes)."""
    cf = vanilla_cf("basalt_blobs")
    cfg = cf["config"]
    cfg["radius"]["min_inclusive"] = r_min
    cfg["radius"]["max_inclusive"] = r_max
    cfg["state"] = {"Name": state}
    cfg["target"]["Name"] = f"{NS}:cinderstone"
    return cf


def disk_cf(state: str, targets: list, r_min: int, r_max: int, half_height: int = 2):
    """minecraft:disk schema from vanilla disk_gravel (simple_state fallback, empty
    rules); state/targets/radius/half_height substituted."""
    cf = vanilla_cf("disk_gravel")
    cfg = cf["config"]
    cfg["half_height"] = half_height
    cfg["radius"]["min_inclusive"] = r_min
    cfg["radius"]["max_inclusive"] = r_max
    cfg["state_provider"]["fallback"]["state"]["Name"] = state
    cfg["target"]["blocks"] = targets
    return cf


def spring_cf(source: str, valid_blocks):
    """minecraft:spring_feature schema from the named vanilla nether spring; only
    valid_blocks substituted (state stays lava falling=true, hole/rock counts kept)."""
    cf = vanilla_cf(source)
    cf["config"]["valid_blocks"] = valid_blocks
    return cf


def delta_cf(rim: str, size_min: int, size_max: int, rim_min: int, rim_max: int):
    """minecraft:delta_feature schema from vanilla delta (contents stays lava level=0);
    rim block and size/rim_size ranges substituted."""
    cf = vanilla_cf("delta")
    cfg = cf["config"]
    cfg["rim"] = {"Name": rim}
    cfg["size"]["min_inclusive"] = size_min
    cfg["size"]["max_inclusive"] = size_max
    cfg["rim_size"]["min_inclusive"] = rim_min
    cfg["rim_size"]["max_inclusive"] = rim_max
    return cf


def uniform(min_spec, max_spec):
    """uniform height schema from vanilla ore_quartz_nether placement."""
    return {"type": "minecraft:uniform", "min_inclusive": min_spec, "max_inclusive": max_spec}


def pf_from(source: str, feature: str, count=None, height=None):
    """Placement from the named vanilla placed feature; only the feature reference and
    (when given) the count / height_range values are substituted; the modifier list and
    order are kept verbatim."""
    pf = vanilla_pf(source)
    pf["feature"] = f"{NS}:{feature}"
    for mod in pf["placement"]:
        if count is not None and mod["type"] in ("minecraft:count",
                                                 "minecraft:count_on_every_layer"):
            mod["count"] = count
        if height is not None and mod["type"] == "minecraft:height_range":
            mod["height"] = height
    return pf


# ---------------------------------------------------------------------------
# Ore loot derivation (vanilla loot schema + documented substitutions only)
# ---------------------------------------------------------------------------

def vanilla_set_count(min_count: int, max_count: int) -> dict:
    """The minecraft:set_count function schema from the real vanilla copper_ore loot
    table (add/count/uniform structure kept verbatim), values substituted."""
    tbl = jar_json("data/minecraft/loot_table/blocks/copper_ore.json")
    fn = next(f for f in tbl["pools"][0]["entries"][0]["children"][1]["functions"]
              if f["function"] == "minecraft:set_count")
    fn["count"]["min"] = float(min_count)
    fn["count"]["max"] = float(max_count)
    return fn


def ore_loot(block_id: str, source: str | None, drop_id: str, count) -> dict:
    """Silk-touch/fortune ore loot table for one mod ore block.

    source given: the REAL vanilla 1.21.9 loot table
    data/minecraft/loot_table/blocks/<source>.json out of the client jar, with only the
    silk-touch drop (-> the mod ore block), the fortune drop (-> drop_id), the
    set_count (min,max) values (when count is given; the source table must already
    carry a set_count function) and the random_sequence substituted.

    source None: the mod's own on-disk infernium_ore template (same shape as the
    vanilla tables; see data/copper_inferno/loot_table/blocks/infernium_ore.json)
    retargeted the same way, with a vanilla-schema set_count inserted before
    apply_bonus exactly where vanilla copper_ore carries it.
    """
    if source is None:
        template = DATA / "loot_table" / "blocks" / "infernium_ore.json"
        tbl = json.loads(template.read_text(encoding="utf-8"))
    else:
        tbl = jar_json(f"data/minecraft/loot_table/blocks/{source}.json")
    silk, drop = tbl["pools"][0]["entries"][0]["children"]
    silk["name"] = f"{NS}:{block_id}"
    drop["name"] = drop_id
    if count is not None:
        existing = [f for f in drop["functions"] if f["function"] == "minecraft:set_count"]
        if existing:
            existing[0]["count"]["min"] = float(count[0])
            existing[0]["count"]["max"] = float(count[1])
        else:
            drop["functions"].insert(0, vanilla_set_count(*count))
    tbl["random_sequence"] = f"{NS}:blocks/{block_id}"
    return tbl


# ---------------------------------------------------------------------------
# The 25 features: (name, GenerationStep.Feature constant, configured, placed,
# icon block, handbook EN, handbook DE, biome selector "all" | "shore")
# ---------------------------------------------------------------------------
Feat = namedtuple("Feat", "name step cf pf icon en de sel")

C = f"{NS}:"


def build_features():
    feats = []

    # --- Ores (UNDERGROUND_ORES; CF from ore_blackstone, PFs as named) ---
    ores = [
        # (name, block, size, pf_source, count, height, EN where, DE where)
        ("ore_ember_iron", "ember_iron_ore", 10, "ore_quartz_nether", 12,
         uniform({"above_bottom": 10}, {"below_top": 10}),
         "common veins throughout all seven Inferno biomes",
         "h\u00e4ufige Adern in allen sieben Inferno-Biomen"),
        ("ore_ash_gold", "ash_gold_ore", 10, "ore_quartz_nether", 10,
         uniform({"above_bottom": 10}, {"below_top": 10}),
         "gold-bearing veins throughout the Inferno, like Nether gold",
         "goldhaltige Adern \u00fcberall im Inferno, wie Nethergold"),
        ("ore_slag_copper", "slag_copper_ore", 10, "ore_quartz_nether", 14,
         uniform({"above_bottom": 10}, {"below_top": 10}),
         "plentiful copper veins - the Inferno's signature metal",
         "reichliche Kupferadern - das Leitmetall des Infernos"),
        ("ore_cinder_quartz", "cinder_quartz_ore", 14, "ore_quartz_nether", 16,
         uniform({"above_bottom": 10}, {"below_top": 10}),
         "large, frequent quartz veins (the vanilla Nether quartz numbers)",
         "gro\u00dfe, h\u00e4ufige Quarzadern (die Vanilla-Netherquarz-Werte)"),
        ("ore_brimstone", "brimstone_ore", 8, "ore_quartz_nether", 8,
         uniform({"absolute": 10}, {"absolute": 60}),
         "sulfurous veins in the mid band, y=10 to y=60",
         "schwefelige Adern im mittleren Band, y=10 bis y=60"),
        ("ore_cinder_lapis", "cinder_lapis_ore", 6, "ore_quartz_nether", 4,
         uniform({"absolute": 10}, {"absolute": 40}),
         "scarce lapis pockets between y=10 and y=40",
         "seltene Lapis-Nester zwischen y=10 und y=40"),
        ("ore_smolder_redstone", "smolder_redstone_ore", 8, "ore_quartz_nether", 10,
         uniform({"absolute": 5}, {"absolute": 40}),
         "redstone veins in the lower half, y=5 to y=40",
         "Redstone-Adern in der unteren H\u00e4lfte, y=5 bis y=40"),
        ("ore_deep_infernium", "deep_infernium_ore", 4, "ore_quartz_nether", 6,
         uniform({"absolute": 5}, {"absolute": 20}),
         "small, rich infernium pockets deep down, y=5 to y=20",
         "kleine, reiche Infernium-Nester in der Tiefe, y=5 bis y=20"),
        ("ore_ashfall_tuff", "ashfall_tuff", 33, "ore_gravel_nether", 2,
         uniform({"absolute": 5}, {"absolute": 41}),
         "big soft tuff pockets (the vanilla Nether gravel numbers)",
         "gro\u00dfe weiche Tuff-Taschen (die Vanilla-Netherkies-Werte)"),
        ("ore_ember_pumice", "ember_pumice", 33, "ore_blackstone", 2,
         uniform({"absolute": 5}, {"absolute": 31}),
         "porous pumice pockets low down (the vanilla blackstone numbers)",
         "por\u00f6se Bims-Taschen weiter unten (die Vanilla-Schwarzstein-Werte)"),
    ]
    for name, block, size, pf_src, count, height, en_w, de_w in ores:
        # Real ores carry a drops sentence (their loot is silk-touch/fortune, see
        # ORE_LOOT); the tuff/pumice pockets drop themselves and get none.
        drop_en, drop_de = ORE_DROPS_TEXT.get(block, ("", ""))
        feats.append(Feat(name, "UNDERGROUND_ORES",
                          ore_cf(f"{C}{block}", size),
                          pf_from(pf_src, name, count=count, height=height),
                          block,
                          f"{dict((b.bid, b.en) for b in BLOCKS)[block]} generates in "
                          f"cinderstone: {en_w}. Mine with a pickaxe."
                          + (f" {drop_en}" if drop_en else ""),
                          f"{dict((b.bid, b.de) for b in BLOCKS)[block]} generiert im "
                          f"Zunderstein: {de_w}. Mit der Spitzhacke abbauen."
                          + (f" {drop_de}" if drop_de else ""),
                          "all"))

    # --- Scattered debris (UNDERGROUND_ORES; CF/PF from the ancient-debris pair) ---
    feats.append(Feat(
        "ore_scorched_debris_large", "UNDERGROUND_ORES",
        scattered_cf(f"{C}scorched_debris", 3),
        pf_from("ore_ancient_debris_large", "ore_scorched_debris_large"),
        "scorched_debris",
        "Scorched Debris (large): blast-proof debris scattered fully enclosed in "
        "cinderstone, up to 3 per vein, centred around y=16 like ancient debris.",
        "Versengte Tr\u00fcmmer (gro\u00df): explosionsfeste Tr\u00fcmmer, v\u00f6llig "
        "im Zunderstein eingeschlossen, bis zu 3 pro Ader, um y=16 wie Uralte "
        "Tr\u00fcmmer.", "all"))
    feats.append(Feat(
        "ore_scorched_debris_small", "UNDERGROUND_ORES",
        scattered_cf(f"{C}scorched_debris", 2),
        pf_from("ore_debris_small", "ore_scorched_debris_small"),
        "scorched_debris",
        "Scorched Debris (small): a second, height-independent sprinkle of 1-2 "
        "blast-proof debris blocks per chunk, always buried in cinderstone.",
        "Versengte Tr\u00fcmmer (klein): eine zweite, h\u00f6henunabh\u00e4ngige Streuung "
        "von 1-2 explosionsfesten Tr\u00fcmmerbl\u00f6cken pro Chunk, stets im "
        "Zunderstein vergraben.", "all"))

    # --- Replace blobs (UNDERGROUND_DECORATION; CF/PF from basalt_blobs) ---
    blobs = [
        ("scoria_blobs", "scoria", 3, 7, 25,
         "Scoria blobs: dark volcanic cinder spheres (radius 3-7) replace cinderstone, "
         "25 tries per chunk across the whole height range.",
         "Skoria-Blasen: dunkle vulkanische Schlackenkugeln (Radius 3-7) ersetzen "
         "Zunderstein, 25 Versuche pro Chunk \u00fcber die gesamte H\u00f6he."),
        ("geyserite_blobs", "geyserite", 3, 5, 20,
         "Geyserite blobs: pale sinter spheres (radius 3-5) replace cinderstone, 20 "
         "tries per chunk - quarry them near geyser basins.",
         "Geysirit-Blasen: blasse Sinterkugeln (Radius 3-5) ersetzen Zunderstein, 20 "
         "Versuche pro Chunk - in der N\u00e4he von Geysirbecken abbauen."),
        ("hardened_slag_blobs", "hardened_slag", 3, 7, 30,
         "Hardened slag blobs: cooled slag spheres (radius 3-7) replace cinderstone, 30 "
         "tries per chunk - the Inferno's basalt analog.",
         "Geh\u00e4rtete-Schlacke-Blasen: erkaltete Schlackenkugeln (Radius 3-7) "
         "ersetzen Zunderstein, 30 Versuche pro Chunk - das Basalt-Analog des Infernos."),
        ("sulfur_blobs", "sulfur_block", 2, 5, 15,
         "Sulfur blobs: small yellow spheres (radius 2-5) replace cinderstone, 15 tries "
         "per chunk.",
         "Schwefel-Blasen: kleine gelbe Kugeln (Radius 2-5) ersetzen Zunderstein, 15 "
         "Versuche pro Chunk."),
    ]
    for name, block, r_min, r_max, count, en, de in blobs:
        feats.append(Feat(name, "UNDERGROUND_DECORATION",
                          blob_cf(f"{C}{block}", r_min, r_max),
                          pf_from("basalt_blobs", name, count=count),
                          block, en, de, "all"))

    # --- Disks (UNDERGROUND_DECORATION; CF from disk_gravel, PF from ore_magma:
    #     absolute band around the lava-sea level, see module docstring) ---
    disks = [
        ("disk_sulfur_sand", "sulfur_sand",
         [f"{C}cinderstone", f"{C}ash_block", f"{C}cinder_gravel"], 2, 6, 3, 30, 35,
         "Sulfur sand disks: yellow sand banks (radius 2-6) replacing the shore blocks "
         "around the lava-sea level, y=30 to y=35.",
         "Schwefelsand-Scheiben: gelbe Sandb\u00e4nke (Radius 2-6) ersetzen die "
         "Uferbl\u00f6cke um den Lavasee-Pegel, y=30 bis y=35."),
        ("disk_ember_grit", "ember_grit",
         [f"{C}cinderstone", f"{C}cinder_gravel"], 2, 5, 3, 28, 36,
         "Ember grit disks: glowing-orange gravel banks (radius 2-5) in the shore band, "
         "y=28 to y=36.",
         "Glutgrus-Scheiben: gl\u00fchend-orange Kiesb\u00e4nke (Radius 2-5) im "
         "Uferband, y=28 bis y=36."),
        ("disk_cinder_silt", "cinder_silt",
         [f"{C}cinderstone", f"{C}ash_block"], 2, 5, 2, 30, 38,
         "Cinder silt disks: soft dark mud flats (radius 2-5) slightly above the lava "
         "line, y=30 to y=38.",
         "Zunderschlick-Scheiben: weiche dunkle Schlammfl\u00e4chen (Radius 2-5) knapp "
         "\u00fcber der Lavalinie, y=30 bis y=38."),
    ]
    for name, block, targets, r_min, r_max, count, y0, y1, en, de in disks:
        feats.append(Feat(name, "UNDERGROUND_DECORATION",
                          disk_cf(f"{C}{block}", targets, r_min, r_max),
                          pf_from("ore_magma", name, count=count,
                                  height=uniform({"absolute": y0}, {"absolute": y1})),
                          block, en, de, "shore"))

    # --- Springs (FLUID_SPRINGS; CFs from the vanilla nether springs, PFs as named) ---
    feats.append(Feat(
        "spring_sulfur", "FLUID_SPRINGS",
        spring_cf("spring_nether_open",
                  [f"{C}cinderstone", f"{C}sulfur_block", f"{C}geyserite"]),
        pf_from("spring_open", "spring_sulfur", count=8),
        "sulfur_block",
        "Sulfur springs: open lava spouts (8 per chunk, mid heights) leaking from "
        "cinderstone, sulfur and geyserite walls.",
        "Schwefelquellen: offene Lava-Ausl\u00e4sse (8 pro Chunk, mittlere H\u00f6hen) "
        "aus W\u00e4nden von Zunderstein, Schwefel und Geysirit.", "shore"))
    feats.append(Feat(
        "spring_geyserite", "FLUID_SPRINGS",
        spring_cf("spring_nether_closed", [f"{C}cinderstone", f"{C}geyserite"]),
        pf_from("spring_closed", "spring_geyserite", count=12),
        "geyserite",
        "Geyserite springs: enclosed lava pockets (12 per chunk) sealed inside "
        "cinderstone and geyserite, away from the terrain surface.",
        "Geysirit-Quellen: eingeschlossene Lavataschen (12 pro Chunk), versiegelt in "
        "Zunderstein und Geysirit, abseits der Oberfl\u00e4che.", "shore"))
    feats.append(Feat(
        "spring_scoria", "FLUID_SPRINGS",
        spring_cf("spring_lava_nether",
                  [f"{C}cinderstone", f"{C}scoria", f"{C}hardened_slag", f"{C}geyserite"]),
        pf_from("spring_lava", "spring_scoria", count=16),
        "scoria",
        "Scoria springs: floor-fed lava falls (16 per chunk, strongly biased to the "
        "bottom) rising through scoria, hardened slag and geyserite.",
        "Skoria-Quellen: bodengespeiste Lavaf\u00e4lle (16 pro Chunk, stark nach unten "
        "gewichtet), aufsteigend durch Skoria, geh\u00e4rtete Schlacke und Geysirit.",
        "shore"))

    # --- Geyser basins (SURFACE_STRUCTURES, the vanilla delta step; CF/PF from delta) ---
    basins = [
        ("geyser_basin", "geyserite", 3, 7, 0, 2, 8,
         "Geyser basins: lava pools (size 3-7) rimmed with pale geyserite, 8 tries on "
         "every terrain layer - the Inferno's delta analog.",
         "Geysirbecken: Lavabecken (Gr\u00f6\u00dfe 3-7) mit blassem Geysirit-Rand, 8 "
         "Versuche auf jeder Gel\u00e4ndeschicht - das Delta-Analog des Infernos."),
        ("sulfur_basin", "sulfur_block", 2, 5, 1, 2, 4,
         "Sulfur basins: small lava pools (size 2-5) with a thick sulfur rim, 4 tries "
         "on every terrain layer.",
         "Schwefelbecken: kleine Lavabecken (Gr\u00f6\u00dfe 2-5) mit dickem "
         "Schwefelrand, 4 Versuche auf jeder Gel\u00e4ndeschicht."),
        ("slag_basin", "hardened_slag", 3, 6, 0, 2, 6,
         "Slag basins: lava pools (size 3-6) edged in hardened slag, 6 tries on every "
         "terrain layer.",
         "Schlackenbecken: Lavabecken (Gr\u00f6\u00dfe 3-6) mit Rand aus geh\u00e4rteter "
         "Schlacke, 6 Versuche auf jeder Gel\u00e4ndeschicht."),
    ]
    for name, block, s0, s1, r0, r1, count, en, de in basins:
        feats.append(Feat(name, "SURFACE_STRUCTURES",
                          delta_cf(f"{C}{block}", s0, s1, r0, r1),
                          pf_from("delta", name, count=count),
                          block, en, de, "shore"))

    return feats


# ---------------------------------------------------------------------------
# Textures (16x16, deterministic; genlib helpers, house palette)
# ---------------------------------------------------------------------------

def cinder_base(rng):
    """Cinderstone-style warm charcoal base (palette values from infernodim_gen) so the
    ore blocks visually sit inside their host stone."""
    img = genlib.new_canvas(rng, [CINDER_DARK, CINDER, CINDER, CINDER_LIGHT])
    all_px = [(x, y) for y in range(16) for x in range(16)]
    genlib.sprinkle(img, rng, all_px, [EMBER], 0.02)
    return img


def tex_ore(name: str, nuggets, highlight):
    """Cinder base + five 2x2 nuggets with a single highlight pixel (the
    infernodim_gen ore look)."""
    rng = rng_for(name)
    img = cinder_base(rng)
    for _ in range(5):
        px, py = rng.randrange(1, 13), rng.randrange(1, 13)
        color = rng.choice(nuggets)
        for dx, dy in ((0, 0), (1, 0), (0, 1), (1, 1)):
            img.putpixel((px + dx, py + dy), color)
        img.putpixel((px + 1, py), highlight)
    return img


def tex_canvas(name: str, shades, accents=None, prob=0.0):
    rng = rng_for(name)
    img = genlib.new_canvas(rng, shades)
    if accents and prob > 0:
        all_px = [(x, y) for y in range(16) for x in range(16)]
        genlib.sprinkle(img, rng, all_px, accents, prob)
    return img


def tex_pored(name: str, shades, pore):
    """Canvas with seven 2x2 dark pores (pumice / scoria vesicles)."""
    rng = rng_for(name)
    img = genlib.new_canvas(rng, shades)
    for _ in range(7):
        px, py = rng.randrange(0, 14), rng.randrange(0, 14)
        for dx, dy in ((0, 0), (1, 0), (0, 1), (1, 1)):
            img.putpixel((px + dx, py + dy), pore)
    return img


def tex_banded(name: str, bands, accents, prob):
    """Horizontal 2-row strata (the slagstone/geyserite sinter look)."""
    rng = rng_for(name)
    from PIL import Image
    img = Image.new("RGB", (16, 16))
    for y in range(16):
        base = bands[(y // 2) % len(bands)]
        for x in range(16):
            shade = base
            if rng.random() < 0.15:
                shade = rng.choice(bands)
            img.putpixel((x, y), shade)
    all_px = [(x, y) for y in range(16) for x in range(16)]
    genlib.sprinkle(img, rng, all_px, accents, prob)
    return img


def tex_seamed(name: str, shades, seams):
    """Canvas with glowing grid seams every 5px (cooled-slag crust)."""
    rng = rng_for(name)
    img = genlib.new_canvas(rng, shades)
    for y in range(16):
        for x in range(16):
            if x % 5 == 4 or y % 5 == 4:
                if rng.random() < 0.5:
                    img.putpixel((x, y), rng.choice(seams))
    return img


def build_texture(bid: str):
    # Explicit palettes per block (deterministic; house ember accents).
    if bid == "ember_iron_ore":
        return tex_ore(bid, [(0xC0, 0xAF, 0xA5), (0xA3, 0x91, 0x87)], (0xE6, 0xDA, 0xD2))
    if bid == "ash_gold_ore":
        return tex_ore(bid, [(0xF5, 0xC8, 0x4B), (0xD4, 0xA6, 0x2F)], (0xFF, 0xEC, 0x9E))
    if bid == "slag_copper_ore":
        return tex_ore(bid, [(0xE0, 0x73, 0x4D), (0xC1, 0x5A, 0x3B)], (0xFF, 0xC2, 0x6B))
    if bid == "cinder_quartz_ore":
        return tex_ore(bid, [(0xE8, 0xE2, 0xDC), (0xCF, 0xC6, 0xBE)], (0xFF, 0xFF, 0xF8))
    if bid == "brimstone_ore":
        return tex_ore(bid, [(0xE8, 0xD4, 0x4C), (0xC6, 0xB0, 0x33)], (0xFF, 0xF2, 0x8A))
    if bid == "cinder_lapis_ore":
        return tex_ore(bid, [(0x3C, 0x5B, 0xC0), (0x2A, 0x41, 0x93)], (0x7E, 0x9C, 0xE8))
    if bid == "smolder_redstone_ore":
        return tex_ore(bid, [(0xD8, 0x2A, 0x1E), (0xA8, 0x1A, 0x12)], (0xFF, 0x6A, 0x5A))
    if bid == "deep_infernium_ore":
        return tex_ore(bid, [(0xFF, 0x7A, 0x2F), (0xE2, 0x58, 0x22)], (0xFF, 0xC2, 0x6B))
    if bid == "scorched_debris":
        return tex_seamed(bid, [(0x2A, 0x20, 0x1B), (0x38, 0x2B, 0x24), (0x44, 0x35, 0x2C)],
                          [(0x6B, 0x4A, 0x36), EMBER])
    if bid == "ashfall_tuff":
        return tex_canvas(bid, [(0x5C, 0x5A, 0x50), (0x6C, 0x6A, 0x5E), (0x7C, 0x79, 0x6C)],
                          [(0x8E, 0x8B, 0x7E)], 0.05)
    if bid == "ember_pumice":
        return tex_pored(bid, [(0x8A, 0x54, 0x3C), (0x9C, 0x62, 0x46), (0xAE, 0x71, 0x52)],
                         (0x3A, 0x22, 0x18))
    if bid == "scoria":
        return tex_pored(bid, [(0x4A, 0x1E, 0x16), (0x5C, 0x26, 0x1A), (0x6E, 0x2F, 0x20)],
                         (0x25, 0x0F, 0x0A))
    if bid == "geyserite":
        return tex_banded(bid, [(0xD8, 0xD2, 0xC2), (0xC6, 0xBF, 0xAC), (0xE6, 0xE0, 0xD2),
                                (0xCE, 0xC8, 0xB6)], [(0xF2, 0xEE, 0xE2)], 0.02)
    if bid == "hardened_slag":
        return tex_seamed(bid, [(0x3E, 0x3A, 0x38), (0x4C, 0x48, 0x46), (0x5A, 0x54, 0x50)],
                          [EMBER, (0x6E, 0x66, 0x60)])
    if bid == "sulfur_block":
        return tex_canvas(bid, [(0xC6, 0xB0, 0x33), (0xD8, 0xC2, 0x40), (0xE8, 0xD4, 0x4C)],
                          [(0xFF, 0xF2, 0x8A)], 0.06)
    if bid == "sulfur_sand":
        return tex_canvas(bid, [(0xD4, 0xC6, 0x86), (0xC6, 0xB8, 0x78), (0xE2, 0xD4, 0x96)])
    if bid == "ember_grit":
        return tex_canvas(bid, [(0x7A, 0x52, 0x40), (0x8C, 0x5E, 0x48), (0x6A, 0x46, 0x38)],
                          [EMBER, EMBER_BRIGHT], 0.05)
    if bid == "cinder_silt":
        return tex_canvas(bid, [(0x3A, 0x33, 0x30), (0x46, 0x3E, 0x3A), (0x52, 0x49, 0x44)])
    raise ValueError(f"no texture for {bid}")


def tex_item_brimstone():
    """16x16 RGBA sprite: a jagged sulfur lump on a transparent background, in the
    brimstone_ore nugget palette (outline, dark/base facets, bright specks).
    Deterministic via rng_for, like every other texture."""
    from PIL import Image
    rng = rng_for("brimstone")
    outline = (0x6E, 0x5A, 0x14, 255)
    dark = (0xA8, 0x92, 0x28, 255)
    base = (0xC6, 0xB0, 0x33, 255)
    light = (0xE8, 0xD4, 0x4C, 255)
    hi = (0xFF, 0xF2, 0x8A, 255)
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    rows = {3: (6, 9), 4: (5, 11), 5: (4, 12), 6: (3, 12), 7: (3, 13), 8: (2, 13),
            9: (2, 13), 10: (3, 12), 11: (3, 12), 12: (4, 11), 13: (6, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (3, 13):
                color = outline
            elif x - x0 <= 2 and y <= 8:
                color = rng.choice([light, light, base])
            else:
                color = rng.choice([base, base, dark])
            img.putpixel((x, y), color)
    for x, y in [(6, 5), (7, 7), (5, 8), (9, 6), (8, 10), (11, 9)]:
        if rng.random() < 0.85:
            img.putpixel((x, y), hi)
    return img


def emit_textures() -> None:
    block_dir = ASSETS / "textures" / "block"
    block_dir.mkdir(parents=True, exist_ok=True)
    for bid in BLOCK_IDS:
        build_texture(bid).save(block_dir / f"{bid}.png")
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    tex_item_brimstone().save(item_dir / "brimstone.png")


# ---------------------------------------------------------------------------
# Recipes (data/copper_inferno/recipe/infernogeology/) and their handbook entries.
# Every recipe's input set contains at least one copper_inferno id (brimstone is new,
# sulfur_block is ours), so the canonical input sets cannot collide with vanilla or
# other mod recipes (devtools/check_recipe_collisions.py enforces this).
# ---------------------------------------------------------------------------
RECIPES = DATA / "recipe" / "infernogeology"


def shapeless_grid(ingredients: list) -> list:
    return ingredients + [""] * (9 - len(ingredients))


def emit_recipes() -> list:
    """Emits the 4 brimstone-consuming recipes; returns their handbook entry tuples
    (the 9-tuple shape java_handbook_class renders)."""
    bs = f"{NS}:brimstone"
    sb = f"{NS}:sulfur_block"
    entries = []

    # 3x3 brimstone -> 1 sulfur block (the storage-block pattern, like vanilla
    # lapis_block; category "building" like the vanilla storage blocks).
    genlib.emit_shaped(RECIPES, "sulfur_block_from_brimstone", {"B": bs},
                       ["BBB", "BBB", "BBB"], sb, 1)
    entries.append((
        "items", "infernogeology/sulfur_block_from_brimstone", sb,
        "infernogeology/sulfur_block_from_brimstone", [bs] * 9, sb, 1,
        "Craft 1x Sulfur Block from nine Brimstone at a crafting table - the storage "
        "block for a brimstone mining haul.",
        "Stellt 1x Schwefelblock aus neun Schwefelbrocken an der Werkbank her - der "
        "Lagerblock f\u00fcr die Schwefel-Ausbeute."))

    # 1 sulfur block -> 9 brimstone (the decompression pair, like vanilla
    # lapis_lazuli-from-block; category "misc" like the vanilla unpack recipes).
    genlib.emit_shapeless(RECIPES, "brimstone_from_sulfur_block", [sb], bs, 9,
                          category="misc")
    entries.append((
        "items", "infernogeology/brimstone_from_sulfur_block", bs,
        "infernogeology/brimstone_from_sulfur_block", shapeless_grid([sb]), bs, 9,
        "Break 1x Sulfur Block back into 9x Brimstone at a crafting table.",
        "Zerlegt 1x Schwefelblock an der Werkbank wieder in 9x Schwefelbrocken."))

    # brimstone + charcoal + gunpowder -> 3 fire charges (brimstone stands in for
    # blaze powder; the specific-charcoal ingredient keeps the input set distinct
    # from vanilla fire_charge, which uses blaze powder + the coals tag).
    genlib.emit_shapeless(RECIPES, "fire_charge_from_brimstone",
                          [bs, "minecraft:charcoal", "minecraft:gunpowder"],
                          "minecraft:fire_charge", 3, category="misc")
    entries.append((
        "items", "infernogeology/fire_charge_from_brimstone", "minecraft:fire_charge",
        "infernogeology/fire_charge_from_brimstone",
        shapeless_grid([bs, "minecraft:charcoal", "minecraft:gunpowder"]),
        "minecraft:fire_charge", 3,
        "Craft 3x Fire Charge from Brimstone, Charcoal and Gunpowder - the Inferno's "
        "blaze-powder substitute.",
        "Stellt 3x Feuerkugel aus Schwefelbrocken, Holzkohle und Schwarzpulver her - "
        "der Lohenstaub-Ersatz des Infernos."))

    # 2 brimstone + charcoal + ash pile -> 4 gunpowder (the classic black-powder mix:
    # sulfur + charcoal + saltpeter-ash).
    genlib.emit_shapeless(RECIPES, "gunpowder_from_brimstone",
                          [bs, bs, "minecraft:charcoal", f"{NS}:ash_pile"],
                          "minecraft:gunpowder", 4, category="misc")
    entries.append((
        "items", "infernogeology/gunpowder_from_brimstone", "minecraft:gunpowder",
        "infernogeology/gunpowder_from_brimstone",
        shapeless_grid([bs, bs, "minecraft:charcoal", f"{NS}:ash_pile"]),
        "minecraft:gunpowder", 4,
        "Craft 4x Gunpowder from two Brimstone, Charcoal and an Ash Pile - the classic "
        "black-powder mix.",
        "Stellt 4x Schwarzpulver aus zwei Schwefelbrocken, Holzkohle und einem "
        "Aschehaufen her - die klassische Schwarzpulver-Mischung."))

    return entries


# ---------------------------------------------------------------------------
# Java codegen (genlib.java_feature_class / java_handbook_class)
# ---------------------------------------------------------------------------

SETTINGS_METHODS = """\
\t/**
\t * Fresh settings per block ({@link ModBlocks#register} writes a registry key into the
\t * instance, so settings must never be shared). Requires-tool stone/ore profile, same
\t * shape as the infernodim terrain blocks.
\t */
\tprivate static AbstractBlock.Settings stoneSettings(MapColor color, BlockSoundGroup sounds,
\t\t\tfloat hardness, float resistance) {
\t\treturn AbstractBlock.Settings.create()
\t\t\t\t.mapColor(color)
\t\t\t\t.requiresTool()
\t\t\t\t.strength(hardness, resistance)
\t\t\t\t.sounds(sounds);
\t}

\t/** Soft sediments: no requiresTool (shovel-mineable by hand, like scorched_sand). */
\tprivate static AbstractBlock.Settings softSettings(MapColor color, BlockSoundGroup sounds,
\t\t\tfloat strength) {
\t\treturn AbstractBlock.Settings.create()
\t\t\t\t.mapColor(color)
\t\t\t\t.strength(strength)
\t\t\t\t.sounds(sounds);
\t}"""


def wiring_lines(features) -> list:
    """The literal BiomeModifications.addFeature wiring (one call per placed feature).
    addFeature signature verified via javap on fabric-biome-api-v1 16.1.0:
    (Predicate<BiomeSelectionContext>, GenerationStep.Feature, RegistryKey<PlacedFeature>).

    Two literal biome predicates: the 12 ore/debris veins and 4 replace-blobs run in
    ALL SEVEN Inferno biomes; the disks, springs and geyser basins only in the five
    lava-shore biomes (crystal_hollows stays clean, verdigris_jungle keeps its jungle
    floor). Every feature keeps its position in the single master order below (veins ->
    blobs -> disks -> springs -> basins), and lavaShoreBiomes selects a strict SUBSET of
    allInfernoBiomes, so every biome sees its features in the same relative order and
    the placed-feature ordering stays cycle-free (FeatureSorter single-master-order
    rule)."""
    lines = [
        "",
        "\t\t// Worldgen wiring (biome JSONs owned by infernodim/infernodim2; includeByKey",
        "\t\t// matches nothing until they load). Veins and replace-blobs run in all seven",
        "\t\t// Inferno biomes; disks, springs and geyser basins only in the five lava-shore",
        "\t\t// biomes (crystal_hollows stays clean, verdigris_jungle keeps its jungle floor).",
        "\t\t// Every feature keeps its position in the single master order below and",
        "\t\t// lavaShoreBiomes is a strict subset of allInfernoBiomes, so every biome sees",
        "\t\t// its features in the same relative order and the placed-feature ordering",
        "\t\t// stays cycle-free (FeatureSorter single-master-order rule).",
        "\t\tPredicate<BiomeSelectionContext> allInfernoBiomes = BiomeSelectors.includeByKey(",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"cinder_wastes\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"ember_grove\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"slag_sea\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"verdigris_jungle\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"molten_delta\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"soot_dunes\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"crystal_hollows\")));",
        "\t\tPredicate<BiomeSelectionContext> lavaShoreBiomes = BiomeSelectors.includeByKey(",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"cinder_wastes\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"ember_grove\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"slag_sea\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"molten_delta\")),",
        "\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"soot_dunes\")));",
    ]
    selectors = {"all": "allInfernoBiomes", "shore": "lavaShoreBiomes"}
    for f in features:
        lines.append(f"\t\tBiomeModifications.addFeature({selectors[f.sel]}, "
                     f"GenerationStep.Feature.{f.step},")
        lines.append(f"\t\t\t\tRegistryKey.of(RegistryKeys.PLACED_FEATURE, "
                     f"CopperInferno.id(\"{f.name}\")));")
    return lines


def emit_java(features, recipe_entries) -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)

    blocks = [(field_of(b.bid), b.bid, "Block::new", b.settings) for b in BLOCKS]
    items = [(field_of(i.iid), i.iid, "Item::new", "new Item.Settings()") for i in ITEMS]
    tab_entries = [field_of(b.bid) for b in BLOCKS]
    main_tab_entries = [field_of(i.iid) for i in ITEMS]

    feature_doc = [
        "Inferno geology: 25 data-driven worldgen features (10 ore veins, 2 scattered",
        "scorched-debris veins, 4 replace-blobs, 3 sediment disks, 3 lava springs, 3",
        "geyser basins) for the Inferno dimension, plus the 18 ore/deco blocks they",
        "place and the brimstone item (dropped by brimstone_ore; consumed by the",
        "recipes under {@code data/copper_inferno/recipe/infernogeology/}). The",
        "configured/placed feature JSON under",
        "{@code data/copper_inferno/worldgen/} is derived from the real vanilla 1.21.9",
        "schemas; generation is wired here via Fabric BiomeModifications with two",
        "literal predicates: veins + blobs into all seven copper_inferno biomes,",
        "disks/springs/basins into the five lava-shore biomes (biome JSONs owned by",
        "the infernodim/infernodim2 features).",
        "",
        "<p>Assets (blockstates, models, textures, item definitions, loot tables, EN+DE",
        "lang fragments), the recipes and the worldgen JSON are generated by",
        "{@code devtools/gen/infernogeology_gen.py}; the 8 real ores drop their",
        "refined product under Fortune and the ore block under Silk Touch (loot derived",
        "from the real vanilla tables); the tag fragment lives at",
        "{@code devtools/tagfrag/infernogeology.json}; handbook pages are registered by",
        "{@link InfernoGeologyHandbook}.",
    ]
    feature_src = genlib.java_feature_class(
        "infernogeology", "InfernoGeologyFeature", feature_doc,
        blocks=blocks,
        items=items,
        settings_methods=SETTINGS_METHODS,
        tabs=[("NATURE_KEY", tab_entries), ("MAIN_KEY", main_tab_entries)],
        extra_imports=(
            "java.util.function.Predicate",
            "net.fabricmc.fabric.api.biome.v1.BiomeModifications",
            "net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext",
            "net.fabricmc.fabric.api.biome.v1.BiomeSelectors",
            "net.minecraft.block.MapColor",
            "net.minecraft.registry.RegistryKey",
            "net.minecraft.registry.RegistryKeys",
            "net.minecraft.sound.BlockSoundGroup",
            "net.minecraft.world.gen.GenerationStep",
            "net.sonic0810.copperinferno.CopperInferno",
        ),
        extra_init_lines=wiring_lines(features),
        handbook_class="InfernoGeologyHandbook",
    )
    (FEATURE_DIR / "InfernoGeologyFeature.java").write_text(feature_src, encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the Inferno geology worldgen set: one \"dimension\" entry per",
        "worldgen feature explaining what generates where (the ore entries name their",
        "drops), plus one \"items\" recipe page per JSON under",
        "{@code data/copper_inferno/recipe/infernogeology/}. Texts mirror",
        "devtools/gen/infernogeology_gen.py; {@code devtools/check_handbook.py} parses",
        "the inline {@code new HandbookEntry(...)} literals positionally, so keep them",
        "inline.",
    ]
    entries = [("dimension", f"infernogeology_{f.name}", f"{NS}:{f.icon}", None, None,
                None, 0, f.en, f.de) for f in features]
    entries += recipe_entries
    handbook_src = genlib.java_handbook_class("infernogeology", "InfernoGeologyHandbook",
                                              handbook_doc, entries)
    (FEATURE_DIR / "InfernoGeologyHandbook.java").write_text(handbook_src, encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/infernogeology.txt; format per devtools/hooks/README.md)
# ---------------------------------------------------------------------------

def emit_hooks(feature_count: int, recipe_count: int, handbook_count: int) -> None:
    lines = ["# infernogeology feature hooks (format: devtools/hooks/README.md)", "",
             "[init]",
             "# After InfernoDimensionFeature.init() (the worldgen JSON targets cinderstone",
             "# and the placed features join the infernodim biomes' generation steps).",
             "import net.sonic0810.copperinferno.feature.infernogeology.InfernoGeologyFeature;",
             "\t\tInfernoGeologyFeature.init();", "",
             "[requires-tool]"]
    lines += [b.bid for b in BLOCKS if b.tool == "pickaxe"]
    lines += ["", "[recipe-dir]",
              "infernogeology"]
    lines += ["", "[counts]",
              f"worldgen-features: {feature_count}",
              f"blocks: {len(BLOCKS)}",
              f"items: {len(ITEMS)}",
              f"recipes: {recipe_count}",
              f"handbook-entries: {handbook_count}", ""]
    path = ROOT / "devtools" / "hooks" / "infernogeology.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Validation helpers
# ---------------------------------------------------------------------------

def collect_block_refs(node, acc: set) -> None:
    """Every copper_inferno block id referenced anywhere in a worldgen JSON tree:
    state dicts ({"Name": ...}), target block predicates and valid_blocks lists."""
    if isinstance(node, dict):
        for key, value in node.items():
            if key in ("Name", "block") and isinstance(value, str) and value.startswith(f"{NS}:"):
                acc.add(value.removeprefix(f"{NS}:"))
            elif key in ("blocks", "valid_blocks"):
                vals = value if isinstance(value, list) else [value]
                for v in vals:
                    if isinstance(v, str) and v.startswith(f"{NS}:"):
                        acc.add(v.removeprefix(f"{NS}:"))
            else:
                collect_block_refs(value, acc)
    elif isinstance(node, list):
        for value in node:
            collect_block_refs(value, acc)


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    features = build_features()

    # Worldgen JSON (configured + placed pairs, same file name).
    for f in features:
        write_json(CF_DIR / f"{f.name}.json", f.cf)
        write_json(PF_DIR / f"{f.name}.json", f.pf)

    # Block assets + loot + textures. The 8 real ores get silk-touch/fortune loot
    # derived from the real vanilla tables (ORE_LOOT); scorched_debris and the 9
    # deco/sediment blocks keep drop-self.
    for b in BLOCKS:
        genlib.emit_cube(ASSETS, b.bid)
        if b.bid in ORE_LOOT:
            source, drop_id, count = ORE_LOOT[b.bid]
            write_json(DATA / "loot_table" / "blocks" / f"{b.bid}.json",
                       ore_loot(b.bid, source, drop_id, count))
        else:
            genlib.emit_drop_self_loot(DATA, b.bid)

    # Item assets (the 1.21.9 two-file contract) + all textures.
    for i in ITEMS:
        genlib.emit_item_def(ASSETS, i.iid)
        genlib.emit_item_model(ASSETS, i.iid)
    emit_textures()

    # Recipes + the handbook entry tuples they document.
    recipe_entries = emit_recipes()

    # Lang fragments (EN + DE): 18 block keys + 1 item key.
    lang_en = {f"block.{NS}.{b.bid}": b.en for b in BLOCKS}
    lang_de = {f"block.{NS}.{b.bid}": b.de for b in BLOCKS}
    lang_en.update({f"item.{NS}.{i.iid}": i.en for i in ITEMS})
    lang_de.update({f"item.{NS}.{i.iid}": i.de for i in ITEMS})
    genlib.lang_fragments(ASSETS, "infernogeology", lang_en, lang_de)

    # Tag fragment (same shape as devtools/tagfrag/infernodim.json).
    write_json(ROOT / "devtools" / "tagfrag" / "infernogeology.json", {
        "block/mineable/pickaxe": sorted(f"{NS}:{b.bid}" for b in BLOCKS
                                         if b.tool == "pickaxe"),
        "block/mineable/shovel": sorted(f"{NS}:{b.bid}" for b in BLOCKS
                                        if b.tool == "shovel"),
    })

    emit_java(features, recipe_entries)
    handbook_count = len(features) + len(recipe_entries)
    emit_hooks(len(features), len(recipe_entries), handbook_count)

    # ------------------------------------------------------------------
    # Asserts (acceptance contract)
    # ------------------------------------------------------------------
    assert len(features) == 25, f"expected 25 features, got {len(features)}"
    assert len({f.name for f in features}) == 25, "duplicate feature names"
    assert len(BLOCKS) == 18 and len(set(BLOCK_IDS)) == 18, "expected 18 unique blocks"
    assert len(ITEMS) == 1 and ITEM_IDS == ["brimstone"], "expected exactly brimstone"
    assert len(recipe_entries) == 4, f"expected 4 recipes, got {len(recipe_entries)}"
    assert handbook_count == 29, f"expected 29 handbook entries, got {handbook_count}"

    # Biome wiring: 16 veins/blobs in all 7 biomes, 9 disks/springs/basins in the 5
    # lava-shore biomes.
    assert sum(1 for f in features if f.sel == "all") == 16
    assert sum(1 for f in features if f.sel == "shore") == 9
    assert {f.sel for f in features} == {"all", "shore"}

    # The 8 ore loot tables parse back and drop the mapped item (silk child = the ore
    # block itself, fortune child = the refined drop).
    assert len(ORE_LOOT) == 8 and set(ORE_LOOT) <= set(BLOCK_IDS)
    for bid, (_source, drop_id, _count) in ORE_LOOT.items():
        tbl = json.loads((DATA / "loot_table" / "blocks" / f"{bid}.json")
                         .read_text(encoding="utf-8"))
        silk, drop = tbl["pools"][0]["entries"][0]["children"]
        assert silk["name"] == f"{NS}:{bid}", f"{bid}: silk-touch drop"
        assert drop["name"] == drop_id, f"{bid}: fortune drop"

    # The 4 recipe JSONs exist, parse back and each consumes >= 1 copper_inferno id.
    recipe_files = sorted(p.name for p in RECIPES.glob("*.json"))
    assert recipe_files == ["brimstone_from_sulfur_block.json",
                            "fire_charge_from_brimstone.json",
                            "gunpowder_from_brimstone.json",
                            "sulfur_block_from_brimstone.json"], recipe_files
    for p in RECIPES.glob("*.json"):
        recipe = json.loads(p.read_text(encoding="utf-8"))
        inputs = (list(recipe.get("key", {}).values())
                  + list(recipe.get("ingredients", [])))
        assert any(isinstance(i, str) and i.startswith(f"{NS}:") for i in inputs), \
            f"{p.name}: no copper_inferno id among inputs"

    # Referenced items owned by other features have their item defs on disk.
    for iid in EXISTING_ITEMS:
        assert (ASSETS / "items" / f"{iid}.json").is_file(), \
            f"referenced item {iid} has no items/{iid}.json on disk"

    # Every emitted worldgen JSON parses back from disk, 25 + 25 files.
    for f in features:
        for path in (CF_DIR / f"{f.name}.json", PF_DIR / f"{f.name}.json"):
            json.loads(path.read_text(encoding="utf-8"))
        placed = json.loads((PF_DIR / f"{f.name}.json").read_text(encoding="utf-8"))
        assert placed["feature"] == f"{NS}:{f.name}", f"{f.name}: placed->configured ref"

    # Every copper_inferno block a configured feature places/targets is registered by
    # this feature or already exists on disk (blockstate check).
    refs = set()
    for f in features:
        collect_block_refs(f.cf, refs)
    known = set(BLOCK_IDS) | set(EXISTING_BLOCKS)
    unknown = refs - known
    assert not unknown, f"configured features reference unknown blocks: {sorted(unknown)}"
    assert set(BLOCK_IDS) <= refs, \
        f"registered blocks never placed: {sorted(set(BLOCK_IDS) - refs)}"
    for bid in sorted(refs):
        state = ASSETS / "blockstates" / f"{bid}.json"
        assert state.is_file(), f"referenced block {bid} has no blockstate on disk"

    print(f"infernogeology_gen: {len(features)} worldgen features (25 configured + 25 "
          f"placed), {len(BLOCKS)} blocks, {len(ITEMS)} item, {len(ORE_LOOT)} "
          f"silk/fortune ore loot tables, {len(recipe_entries)} recipes, "
          f"{handbook_count} handbook entries generated.")


if __name__ == "__main__":
    main()
