#!/usr/bin/env python3
"""Advancement-tree generator for the COPPER INFERNO v4.1 "Advancements" feature.

Emits, directly into src/main/resources:
  - data/copper_inferno/advancement/copper_inferno/<node>.json — a 20-node bilingual
    advancement tree (root -> copper -> armor/statue/gemalloys, the Dr.Pepper branch with
    its DOOM kick, the infernium -> Inferno -> boss branch and the scorchwood branch)
  - lang fragments: assets/copper_inferno/lang/fragments/advancements.json (EN) and
    fragments_de/advancements.json (real German) with the
    advancements.copper_inferno.<node>.title/.description keys every node references

Criterion formats are EXACT copies of the vanilla 1.21.9 files extracted from the
minecraft client jar (do NOT "improve" them):
  - minecraft:inventory_changed   <- data/minecraft/advancement/story/root.json
  - minecraft:placed_block        <- data/minecraft/advancement/husbandry/plant_seed.json
  - minecraft:changed_dimension   <- data/minecraft/advancement/story/enter_the_nether.json
  - minecraft:player_killed_entity<- data/minecraft/advancement/adventure/kill_a_mob.json
  - challenge frame + xp reward   <- data/minecraft/advancement/husbandry/balanced_diet.json

Requirements semantics (vanilla): one inner list = ANY listed criterion completes the
advancement; one singleton list PER criterion = ALL criteria are required.

Every copper_inferno item/block/dimension id referenced below is validated against the
tree on disk (items/<id>.json, blockstates/<id>.json, dimension/<id>.json) so a typo
fails loudly here instead of as a server datapack parsing error.

Idempotent: pure JSON from constant tables via lib_gen.write_json — re-running writes
byte-identical files.
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from lib_gen import NS, write_json  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
ADV_DIR = RES / "data" / NS / "advancement" / NS

# ---------------------------------------------------------------------------
# Content ids (all verified against the resource tree in main())
# ---------------------------------------------------------------------------

# The 12 gemalloy materials (devtools/gen/gemalloy_gen.py MATERIALS, same order).
GEMALLOYS = [
    "pyrium", "emberite", "cindralite", "slagbronze", "ashsteel", "voidsteel",
    "doomium", "pepperite", "fizzium", "vitrium", "smokequartz", "kilnite",
]

# The 8 scorchwood woods (feature/scorchwood/ScorchWoodFeature.java, same order).
SCORCHWOODS = [
    "emberwood", "ashwillow", "cinderpine", "charoak",
    "glowbirch", "sootmaple", "duskthorn", "pyrewood",
]

# Vanilla 1.21.9 copper armor (stage 0 of the mod's oxidation chains,
# feature/armor/CopperArmorFeature.java).
COPPER_ARMOR = ["copper_helmet", "copper_chestplate", "copper_leggings", "copper_boots"]

# Gemalloy chestplates registered by feature/arsenal/ArsenalFeature.java.
GEMALLOY_CHESTPLATES = ["pyrium", "emberite", "ashsteel", "voidsteel"]

# A broad any-of over Inferno bosses (ModEntities.register ids from
# feature/infernoboss + feature/bosspantheon + feature/bossdoom).
BOSSES = ["inferno_titan", "magma_leviathan", "ash_colossus", "kiln_archon", "dr_doompepper"]

INFERNO_DIMENSION = f"{NS}:inferno"


# ---------------------------------------------------------------------------
# Criterion builders (verbatim vanilla 1.21.9 shapes)
# ---------------------------------------------------------------------------


def inventory_changed(item_id: str) -> dict:
    """story/root.json: obtain the item."""
    return {
        "conditions": {"items": [{"items": item_id}]},
        "trigger": "minecraft:inventory_changed",
    }


def placed_block(block_id: str) -> dict:
    """husbandry/plant_seed.json: place the block."""
    return {
        "conditions": {
            "location": [{"block": block_id, "condition": "minecraft:block_state_property"}]
        },
        "trigger": "minecraft:placed_block",
    }


def changed_dimension(to_id: str) -> dict:
    """story/enter_the_nether.json: arrive in the dimension."""
    return {
        "conditions": {"to": to_id},
        "trigger": "minecraft:changed_dimension",
    }


def player_killed_entity(entity_id: str) -> dict:
    """adventure/kill_a_mob.json: kill one entity of the type."""
    return {
        "conditions": {
            "entity": [{
                "condition": "minecraft:entity_properties",
                "entity": "this",
                "predicate": {"type": entity_id},
            }]
        },
        "trigger": "minecraft:player_killed_entity",
    }


def advancement(node: str, parent: str | None, icon: str, criteria: dict,
                requirements: list, frame: str | None = None,
                background: str | None = None, rewards: dict | None = None) -> dict:
    """One advancement JSON; title/description always use the node's translate keys."""
    display = {
        "description": {"translate": f"advancements.{NS}.{node}.description"},
        "icon": {"count": 1, "id": icon},
        "title": {"translate": f"advancements.{NS}.{node}.title"},
    }
    if frame is not None:
        display["frame"] = frame
    if background is not None:
        # Vanilla roots carry the background and suppress chat/toast announcements.
        display["background"] = background
        display["announce_to_chat"] = False
        display["show_toast"] = False
    obj = {"criteria": criteria, "display": display, "requirements": requirements}
    if parent is not None:
        obj["parent"] = f"{NS}:{NS}/{parent}"
    if rewards is not None:
        obj["rewards"] = rewards
    return obj


def any_of(criteria: dict) -> list:
    return [list(criteria)]


def all_of(criteria: dict) -> list:
    return [[name] for name in criteria]


# ---------------------------------------------------------------------------
# The 20 nodes: {node: advancement JSON}
# ---------------------------------------------------------------------------


def build_advancements() -> dict:
    adv = {}

    # -- trunk -------------------------------------------------------------
    # Root fires on any inventory change (vanilla adventure/root.json style:
    # a bare trigger without conditions) so the tab appears immediately.
    adv["root"] = advancement(
        "root", None, f"{NS}:dr_pepper",
        {"anything": {"trigger": "minecraft:inventory_changed"}},
        [["anything"]],
        background="minecraft:block/copper_block")

    adv["get_copper"] = advancement(
        "get_copper", "root", "minecraft:copper_ingot",
        {"copper_ingot": inventory_changed("minecraft:copper_ingot")},
        [["copper_ingot"]])

    adv["copper_armor"] = advancement(
        "copper_armor", "get_copper", f"{NS}:exposed_copper_chestplate",
        {piece: inventory_changed(f"minecraft:{piece}") for piece in COPPER_ARMOR},
        any_of({piece: None for piece in COPPER_ARMOR}))

    adv["copper_statue"] = advancement(
        "copper_statue", "get_copper", f"{NS}:copper_player_statue",
        {"copper_player_statue": placed_block(f"{NS}:copper_player_statue")},
        [["copper_player_statue"]])

    adv["handbook"] = advancement(
        "handbook", "root", f"{NS}:copper_inferno_handbook",
        {"handbook": inventory_changed(f"{NS}:copper_inferno_handbook")},
        [["handbook"]])

    # -- gemalloy branch ----------------------------------------------------
    gem_criteria = {m: inventory_changed(f"{NS}:{m}_ingot") for m in GEMALLOYS}
    adv["gemalloy_ingot"] = advancement(
        "gemalloy_ingot", "get_copper", f"{NS}:pyrium_ingot",
        gem_criteria, any_of(gem_criteria))

    adv["all_gemalloys"] = advancement(
        "all_gemalloys", "gemalloy_ingot", f"{NS}:vitrium_ingot",
        gem_criteria, all_of(gem_criteria),
        frame="challenge", rewards={"experience": 100})

    plate_criteria = {f"{m}_chestplate": inventory_changed(f"{NS}:{m}_chestplate")
                      for m in GEMALLOY_CHESTPLATES}
    adv["gemalloy_armor"] = advancement(
        "gemalloy_armor", "gemalloy_ingot", f"{NS}:pyrium_chestplate",
        plate_criteria, any_of(plate_criteria))

    # -- Dr.Pepper branch ----------------------------------------------------
    adv["dr_pepper"] = advancement(
        "dr_pepper", "root", f"{NS}:dr_pepper",
        {"dr_pepper": inventory_changed(f"{NS}:dr_pepper")},
        [["dr_pepper"]])

    flavor_criteria = {f: inventory_changed(f"{NS}:{f}")
                       for f in ("cherry_dr_pepper", "dr_pepper_zero", "vanilla_dr_pepper")}
    adv["dr_pepper_variants"] = advancement(
        "dr_pepper_variants", "dr_pepper", f"{NS}:cherry_dr_pepper",
        flavor_criteria, any_of(flavor_criteria))

    adv["doom"] = advancement(
        "doom", "dr_pepper", f"{NS}:doom_syrup",
        {"doom_syrup": inventory_changed(f"{NS}:doom_syrup")},
        [["doom_syrup"]])

    adv["dr_pepper_golem"] = advancement(
        "dr_pepper_golem", "dr_pepper", f"{NS}:dr_pepper_golem",
        {"dr_pepper_golem": inventory_changed(f"{NS}:dr_pepper_golem")},
        [["dr_pepper_golem"]])

    # -- Inferno branch ------------------------------------------------------
    infernium_criteria = {
        "raw_infernium": inventory_changed(f"{NS}:raw_infernium"),
        "infernium_ingot": inventory_changed(f"{NS}:infernium_ingot"),
    }
    adv["infernium"] = advancement(
        "infernium", "get_copper", f"{NS}:infernium_ingot",
        infernium_criteria, any_of(infernium_criteria))

    adv["infernium_igniter"] = advancement(
        "infernium_igniter", "infernium", f"{NS}:infernium_igniter",
        {"infernium_igniter": inventory_changed(f"{NS}:infernium_igniter")},
        [["infernium_igniter"]])

    adv["enter_inferno"] = advancement(
        "enter_inferno", "infernium_igniter", f"{NS}:infernium_portal_frame",
        {"entered_inferno": changed_dimension(INFERNO_DIMENSION)},
        [["entered_inferno"]])

    adv["return_home"] = advancement(
        "return_home", "enter_inferno", "minecraft:red_bed",
        {"returned_to_overworld": changed_dimension("minecraft:overworld")},
        [["returned_to_overworld"]])

    boss_criteria = {f"{NS}:{b}": player_killed_entity(f"{NS}:{b}") for b in BOSSES}
    adv["kill_boss"] = advancement(
        "kill_boss", "enter_inferno", f"{NS}:doompepper_crown",
        boss_criteria, any_of(boss_criteria),
        frame="challenge", rewards={"experience": 100})

    adv["cinderstone"] = advancement(
        "cinderstone", "enter_inferno", f"{NS}:cobbled_cinderstone",
        {"cobbled_cinderstone": inventory_changed(f"{NS}:cobbled_cinderstone")},
        [["cobbled_cinderstone"]])

    # -- scorchwood branch -----------------------------------------------------
    sapling_criteria = {f"{w}_sapling": placed_block(f"{NS}:{w}_sapling") for w in SCORCHWOODS}
    adv["scorchwood_sapling"] = advancement(
        "scorchwood_sapling", "enter_inferno", f"{NS}:emberwood_sapling",
        sapling_criteria, any_of(sapling_criteria))

    log_criteria = {f"{w}_log": inventory_changed(f"{NS}:{w}_log") for w in SCORCHWOODS}
    adv["scorchwood_log"] = advancement(
        "scorchwood_log", "scorchwood_sapling", f"{NS}:emberwood_log",
        log_criteria, any_of(log_criteria))

    return adv


# ---------------------------------------------------------------------------
# Lang: advancements.copper_inferno.<node>.title/.description, EN + real German.
# German terminology matches the existing fragments (Zunderstein = cinderstone,
# Edellegierung = gemalloy, Infernium-Anz\u00fcnder = infernium igniter, ...).
# ---------------------------------------------------------------------------

TEXTS = {
    "root": (
        "The Copper Inferno",
        "Copper oxidizes, soda fizzes and the depths burn - welcome to the Copper Age",
        "Das Kupferinferno",
        "Kupfer oxidiert, Limonade sprudelt und die Tiefen brennen - willkommen im Kupferzeitalter",
    ),
    "get_copper": (
        "Coppering Up",
        "Obtain a copper ingot, the metal of the age",
        "Kupfer her!",
        "Erhalte einen Kupferbarren, das Metall dieses Zeitalters",
    ),
    "copper_armor": (
        "Suit of Verdigris",
        "Craft any piece of copper armor - and watch it oxidize on your back",
        "R\u00fcstung mit Patina",
        "Fertige ein beliebiges Kupferr\u00fcstungsteil - und sieh zu, wie es auf deinem R\u00fccken oxidiert",
    ),
    "copper_statue": (
        "Set in Copper",
        "Place a copper player statue of yourself",
        "In Kupfer verewigt",
        "Stelle eine Kupfer-Spielerstatue von dir auf",
    ),
    "handbook": (
        "Read the Manual",
        "Obtain the COPPER INFERNO handbook and study the age of copper",
        "Lies das Handbuch",
        "Erhalte das COPPER-INFERNO-Handbuch und studiere das Kupferzeitalter",
    ),
    "gemalloy_ingot": (
        "Alloy There!",
        "Smelt any one of the twelve gemalloy ingots",
        "Legierung ahoi!",
        "Erschmilz einen der zw\u00f6lf Edellegierungsbarren",
    ),
    "all_gemalloys": (
        "The Full Dozen",
        "Collect all twelve gemalloy ingots, from pyrium to kilnite",
        "Das volle Dutzend",
        "Sammle alle zw\u00f6lf Edellegierungsbarren, von Pyrium bis Kilnite",
    ),
    "gemalloy_armor": (
        "Dressed to Alloy",
        "Craft a chestplate from a gemalloy",
        "In Legierung gekleidet",
        "Fertige einen Harnisch aus einer Edellegierung",
    ),
    "dr_pepper": (
        "Doctor's Orders",
        "Obtain a bottle of Dr.Pepper, the Inferno's favorite refreshment",
        "Auf \u00e4rztliche Anweisung",
        "Erhalte eine Flasche Dr.Pepper, die Lieblingserfrischung des Infernos",
    ),
    "dr_pepper_variants": (
        "A Pepper for Every Palate",
        "Obtain one of the special Dr.Pepper flavors: Cherry, Zero or Vanilla",
        "F\u00fcr jeden Gaumen ein Pepper",
        "Erhalte eine der besonderen Dr.Pepper-Sorten: Kirsche, Zero oder Vanille",
    ),
    "doom": (
        "The DOOM Kick",
        "Obtain doom syrup, the ingredient that gives Dr.Pepper its infamous kick",
        "Der DOOM-Kick",
        "Erhalte Doom-Sirup, die Zutat, die Dr.Pepper den ber\u00fcchtigten Kick verleiht",
    ),
    "dr_pepper_golem": (
        "Fizzy Friend",
        "Obtain a Dr.Pepper golem spawn can",
        "Sprudelnder Freund",
        "Erhalte eine Dr.Pepper-Golem-Spawn-Dose",
    ),
    "infernium": (
        "Hot Stuff",
        "Obtain infernium, the metal that smolders from within",
        "Hei\u00dfes Eisen",
        "Erhalte Infernium, das Metall, das von innen gl\u00fcht",
    ),
    "infernium_igniter": (
        "Playing with Fire",
        "Craft an infernium igniter to light the portal to the depths",
        "Spiel mit dem Feuer",
        "Fertige einen Infernium-Anz\u00fcnder, um das Portal in die Tiefen zu entz\u00fcnden",
    ),
    "enter_inferno": (
        "Into the Inferno",
        "Step through the portal into the Inferno dimension",
        "Hinab ins Inferno",
        "Tritt durch das Portal in die Inferno-Dimension",
    ),
    "return_home": (
        "Out of the Frying Pan",
        "Return from the Inferno to the Overworld in one piece",
        "Aus der Glut zur\u00fcck",
        "Kehre aus dem Inferno heil in die Oberwelt zur\u00fcck",
    ),
    "kill_boss": (
        "Regicide in the Depths",
        "Slay one of the reigning bosses of the Inferno",
        "K\u00f6nigsmord in der Tiefe",
        "Erschlage einen der herrschenden Bosse des Infernos",
    ),
    "cinderstone": (
        "Cinder Miner",
        "Mine cinderstone and pick up the cobbled remains",
        "Zunder im Gep\u00e4ck",
        "Baue Zunderstein ab und sammle den Bruchzunderstein auf",
    ),
    "scorchwood_sapling": (
        "Scorched Gardener",
        "Plant any sapling of the scorched woods",
        "Versengter G\u00e4rtner",
        "Pflanze einen beliebigen Setzling der versengten H\u00f6lzer",
    ),
    "scorchwood_log": (
        "Burning Timber",
        "Harvest a log from one of the scorched trees",
        "Brennendes Nutzholz",
        "Ernte einen Stamm von einem der versengten B\u00e4ume",
    ),
}


# ---------------------------------------------------------------------------
# Validation: every referenced copper_inferno id must exist on disk.
# ---------------------------------------------------------------------------


def collect_strings(node, out: list) -> None:
    if isinstance(node, dict):
        for v in node.values():
            collect_strings(v, out)
    elif isinstance(node, list):
        for v in node:
            collect_strings(v, out)
    elif isinstance(node, str):
        out.append(node)


def validate(advancements: dict) -> list[str]:
    errors = []
    entity_ids = {f"{NS}:{b}" for b in BOSSES}
    for node, obj in advancements.items():
        icon = obj["display"]["icon"]["id"]
        ns, _, path = icon.rpartition(":")
        if ns == NS and not (ASSETS / "items" / f"{path}.json").is_file():
            errors.append(f"{node}: icon {icon} has no assets/{NS}/items/{path}.json")
        for crit_name, crit in obj["criteria"].items():
            where = f"{node}/{crit_name}"
            cond = crit.get("conditions", {})
            refs = []
            collect_strings(cond, refs)
            for ref in refs:
                ns, _, path = ref.rpartition(":")
                if ns != NS:
                    continue  # vanilla ids and predicate keywords: assumed OK
                if crit["trigger"] == "minecraft:inventory_changed":
                    if not (ASSETS / "items" / f"{path}.json").is_file():
                        errors.append(f"{where}: item {ref} has no items/{path}.json")
                elif crit["trigger"] == "minecraft:placed_block":
                    if not (ASSETS / "blockstates" / f"{path}.json").is_file():
                        errors.append(f"{where}: block {ref} has no blockstates/{path}.json")
                elif crit["trigger"] == "minecraft:changed_dimension":
                    if not (RES / "data" / NS / "dimension" / f"{path}.json").is_file():
                        errors.append(f"{where}: dimension {ref} has no data/{NS}/dimension/{path}.json")
                elif crit["trigger"] == "minecraft:player_killed_entity":
                    if ref not in entity_ids:
                        errors.append(f"{where}: entity {ref} not in the known boss id set")
        parent = obj.get("parent")
        if parent is not None and parent.removeprefix(f"{NS}:{NS}/") not in advancements:
            errors.append(f"{node}: parent {parent} is not an emitted node")
        # every criterion named in requirements must exist
        for group in obj["requirements"]:
            for name in group:
                if name not in obj["criteria"]:
                    errors.append(f"{node}: requirements reference unknown criterion {name!r}")
    for node in advancements:
        if node not in TEXTS:
            errors.append(f"{node}: no EN/DE title+description in TEXTS")
    for node in TEXTS:
        if node not in advancements:
            errors.append(f"TEXTS has stale node {node!r}")
    return errors


def main() -> int:
    advancements = build_advancements()
    errors = validate(advancements)
    if errors:
        print(f"[advancements_gen] {len(errors)} ERRORS:", file=sys.stderr)
        for e in errors:
            print("  - " + e, file=sys.stderr)
        return 1

    for node, obj in advancements.items():
        write_json(ADV_DIR / f"{node}.json", obj)

    lang_en, lang_de = {}, {}
    for node, (title_en, desc_en, title_de, desc_de) in TEXTS.items():
        lang_en[f"advancements.{NS}.{node}.title"] = title_en
        lang_en[f"advancements.{NS}.{node}.description"] = desc_en
        lang_de[f"advancements.{NS}.{node}.title"] = title_de
        lang_de[f"advancements.{NS}.{node}.description"] = desc_de
    write_json(ASSETS / "lang" / "fragments" / "advancements.json", lang_en)
    write_json(ASSETS / "lang" / "fragments_de" / "advancements.json", lang_de)

    print(f"[advancements_gen] wrote {len(advancements)} advancements under "
          f"data/{NS}/advancement/{NS}/ + {len(lang_en)} EN / {len(lang_de)} DE lang keys")
    return 0


if __name__ == "__main__":
    sys.exit(main())
