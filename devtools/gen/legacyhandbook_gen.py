#!/usr/bin/env python3
"""Handbook generator for the 12 LEGACY (pre-v3) recipe dirs of COPPER INFERNO.

The v1/v2 features (masonry, sodablocks, materials, decostone, foods, inferno, music,
utilityblocks, glasslight, gear, extras, statue) predate the in-game handbook, so their
~220 recipe JSONs under data/copper_inferno/recipe/<dir>/ have no handbook entries.
This generator READS those recipe JSONs (they stay authoritative; nothing under
data/ or assets/ is written) plus en_us.json/de_de.json and emits ONLY Java sources into
src/main/java/net/sonic0810/copperinferno/feature/legacyhandbook/:

  - one package-private Legacy<Dir>Handbook class per recipe dir (rendered via
    genlib.java_handbook_class, so every entry is an inline
    HandbookEntries.add(new HandbookEntry(...)) literal that devtools/check_handbook.py
    can parse positionally),
  - LegacyLoreHandbook with recipe-less lore pages for the flagship legacy features
    (Dr.Pepper brewing + DOOM kick, Dr.Pepper golems, copper armor/tool oxidation),
  - LegacyHandbookFeature whose init() calls every register(),

plus the integration hook file devtools/hooks/legacyhandbook.txt.

Idempotent: reruns produce byte-identical output (pure function of the recipe JSONs and
lang files; stale .java files in the package are removed).

NOT covered here: infernogeology (and every other dir already listed in
devtools/check_handbook.py FEATURE_PKGS) — those features own their handbook entries.
"""

import json
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT

RECIPE_ROOT = DATA / "recipe"
ITEM_DEFS = ASSETS / "items"
FEATURE_PKG = "legacyhandbook"
FEATURE_DIR = (ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno"
               / "feature" / FEATURE_PKG)

# ---------------------------------------------------------------------------
# The 12 legacy recipe dirs (order == handbook page order) and their categories.
# blocks: building sets; items: materials/foods/music/curios; gear: equipment.
# ---------------------------------------------------------------------------
LEGACY_DIRS = ["masonry", "sodablocks", "materials", "decostone", "foods", "inferno",
               "music", "utilityblocks", "glasslight", "gear", "extras", "statue"]

CATEGORY = {
    "masonry": "blocks", "decostone": "blocks", "sodablocks": "blocks",
    "utilityblocks": "blocks", "glasslight": "blocks", "inferno": "blocks",
    "materials": "items", "foods": "items", "music": "items", "extras": "items",
    "statue": "items",
    "gear": "gear",
}

EXPECTED_RECIPES = 220  # 51+30+20+20+18+18+17+16+14+9+6+1
EXPECTED_LORE = 4

# Tag ingredients ("#namespace:tag") are shown as one representative item in the
# 3x3 preview grid. Only tags that actually occur in the legacy recipe JSONs.
TAG_REPRESENTATIVE = {
    "#minecraft:planks": "minecraft:oak_planks",
    "#minecraft:eggs": "minecraft:egg",
}

# Vanilla display names (EN + official German localization) for the minecraft: ids that
# appear as legacy recipe results/ingredients; en_us.json/de_de.json only carry
# copper_inferno keys.
VANILLA_NAMES = {
    "minecraft:copper_axe": ("Copper Axe", "Kupferaxt"),
    "minecraft:copper_block": ("Block of Copper", "Kupferblock"),
    "minecraft:copper_boots": ("Copper Boots", "Kupferstiefel"),
    "minecraft:copper_chestplate": ("Copper Chestplate", "Kupferharnisch"),
    "minecraft:copper_helmet": ("Copper Helmet", "Kupferhelm"),
    "minecraft:copper_hoe": ("Copper Hoe", "Kupferhacke"),
    "minecraft:copper_ingot": ("Copper Ingot", "Kupferbarren"),
    "minecraft:copper_leggings": ("Copper Leggings", "Kupferbeinschutz"),
    "minecraft:copper_pickaxe": ("Copper Pickaxe", "Kupferspitzhacke"),
    "minecraft:copper_shovel": ("Copper Shovel", "Kupferschaufel"),
    "minecraft:copper_sword": ("Copper Sword", "Kupferschwert"),
    "minecraft:iron_axe": ("Iron Axe", "Eisenaxt"),
    "minecraft:iron_boots": ("Iron Boots", "Eisenstiefel"),
    "minecraft:iron_chestplate": ("Iron Chestplate", "Eisenharnisch"),
    "minecraft:iron_helmet": ("Iron Helmet", "Eisenhelm"),
    "minecraft:iron_hoe": ("Iron Hoe", "Eisenhacke"),
    "minecraft:iron_leggings": ("Iron Leggings", "Eisenbeinschutz"),
    "minecraft:iron_pickaxe": ("Iron Pickaxe", "Eisenspitzhacke"),
    "minecraft:iron_shovel": ("Iron Shovel", "Eisenschaufel"),
    "minecraft:iron_sword": ("Iron Sword", "Eisenschwert"),
    "minecraft:sugar": ("Sugar", "Zucker"),
}

# German masculine nominative adjectives occurring as the FIRST word of legacy display
# names -> accusative (needed after "Stellt ... her" / "ergibt ..."). All other names
# (feminine/neuter/plural/compound) are case-invariant in these sentence positions.
DE_MASC_NOM_TO_ACC = {
    "Angelaufener": "Angelaufenen",
    "Kandierter": "Kandierten",
    "Leuchtender": "Leuchtenden",
    "Oxidierter": "Oxidierten",
    "Verkohlter": "Verkohlten",
    "Verwitterter": "Verwitterten",
    "Zerdr\u00fcckter": "Zerdr\u00fcckten",
}

# ---------------------------------------------------------------------------
# Display-name lookup (en_us.json / de_de.json are authoritative for our namespace).
# ---------------------------------------------------------------------------
LANG_EN = json.loads((ASSETS / "lang" / "en_us.json").read_text(encoding="utf-8"))
LANG_DE = json.loads((ASSETS / "lang" / "de_de.json").read_text(encoding="utf-8"))


def display_name(item_id: str, lang: dict, which: int) -> str:
    ns, _, path = item_id.rpartition(":")
    for prefix in ("item", "block"):
        key = f"{prefix}.{ns}.{path}"
        if key in lang:
            return lang[key]
    if item_id in VANILLA_NAMES:
        return VANILLA_NAMES[item_id][which]
    raise KeyError(f"no display name for {item_id!r} (lang files + VANILLA_NAMES)")


def en(item_id: str) -> str:
    return display_name(item_id, LANG_EN, 0)


def de(item_id: str) -> str:
    return display_name(item_id, LANG_DE, 1)


def de_acc(name: str) -> str:
    """German accusative of a display name (only leading masculine adjectives inflect)."""
    first, _, rest = name.partition(" ")
    if rest and first in DE_MASC_NOM_TO_ACC:
        return DE_MASC_NOM_TO_ACC[first] + " " + rest
    return name


# ---------------------------------------------------------------------------
# Recipe JSON -> HandbookEntry derivation.
# ---------------------------------------------------------------------------
ITEM_IDS_USED = set()  # every item id placed in an icon/grid/result slot


def resolve_ingredient(value) -> str:
    """One representative item id for an ingredient value (str | list of alternatives)."""
    if isinstance(value, list):
        if not value:
            raise ValueError("empty ingredient alternatives list")
        value = value[0]
    if not isinstance(value, str):
        raise TypeError(f"unsupported ingredient form: {value!r}")
    if value.startswith("#"):
        if value not in TAG_REPRESENTATIVE:
            raise KeyError(f"tag ingredient {value!r} has no TAG_REPRESENTATIVE mapping")
        value = TAG_REPRESENTATIVE[value]
    ITEM_IDS_USED.add(value)
    return value


def grid_shaped(recipe: dict) -> list:
    grid = [""] * 9
    pattern = recipe["pattern"]
    if len(pattern) > 3:
        raise ValueError(f"pattern has {len(pattern)} rows")
    for r, row in enumerate(pattern):
        if len(row) > 3:
            raise ValueError(f"pattern row {row!r} longer than 3")
        for c, ch in enumerate(row):
            if ch == " ":
                continue
            grid[r * 3 + c] = resolve_ingredient(recipe["key"][ch])
    return grid


def grid_shapeless(recipe: dict) -> list:
    ingredients = recipe["ingredients"]
    if len(ingredients) > 9:
        raise ValueError(f"{len(ingredients)} shapeless ingredients")
    grid = [""] * 9
    for i, ing in enumerate(ingredients):
        grid[i] = resolve_ingredient(ing)
    return grid


def grid_center(item_id: str) -> list:
    return ["", "", "", "", item_id, "", "", "", ""]


def entry_for(d: str, stem: str, recipe: dict):
    """The 9-tuple genlib.java_handbook_class expects, derived from one recipe JSON."""
    rtype = recipe["type"].removeprefix("minecraft:")
    result_id = recipe["result"]["id"]
    count = int(recipe["result"].get("count", 1))
    ITEM_IDS_USED.add(result_id)
    res_en, res_de = en(result_id), de(result_id)

    if rtype == "crafting_shaped":
        grid = grid_shaped(recipe)
        text_en = f"Craft {count}x {res_en} at a crafting table."
        text_de = f"Stellt {count}x {de_acc(res_de)} an der Werkbank her."
    elif rtype == "crafting_shapeless":
        grid = grid_shapeless(recipe)
        text_en = f"Craft {count}x {res_en} at a crafting table."
        text_de = f"Stellt {count}x {de_acc(res_de)} an der Werkbank her."
    elif rtype == "smelting":
        ing = resolve_ingredient(recipe["ingredient"])
        grid = grid_center(ing)
        text_en = f"Smelting {en(ing)} in a furnace yields {res_en}."
        text_de = f"{de(ing)} im Ofen gebrannt ergibt {de_acc(res_de)}."
    elif rtype == "blasting":
        ing = resolve_ingredient(recipe["ingredient"])
        grid = grid_center(ing)
        text_en = f"Blasting {en(ing)} in a blast furnace yields {res_en}."
        text_de = f"{de(ing)} im Schmelzofen gebrannt ergibt {de_acc(res_de)}."
    elif rtype == "stonecutting":
        ing = resolve_ingredient(recipe["ingredient"])
        grid = grid_center(ing)
        text_en = f"Stonecutting: cut {count}x {res_en} from {en(ing)}."
        text_de = f"Steins\u00e4ge: {count}x {de_acc(res_de)} aus {de(ing)} schneiden."
    elif rtype == "smithing_transform":
        base = resolve_ingredient(recipe["base"])
        addition = resolve_ingredient(recipe["addition"])
        template = resolve_ingredient(recipe["template"])
        grid = grid_center(base)
        text_en = (f"Upgrade {en(base)} with {en(addition)} at a smithing table "
                   f"using the {en(template)} to obtain {res_en}.")
        text_de = (f"Wertet {de_acc(de(base))} am Schmiedetisch mit {de(addition)} "
                   f"und der {de(template)} zu {de(result_id)} auf.")
    else:
        raise ValueError(f"unsupported recipe type {recipe['type']!r} in {d}/{stem}")

    return (CATEGORY[d], f"{d}/{stem}", result_id, f"{d}/{stem}", grid, result_id,
            count, text_en, text_de)


# ---------------------------------------------------------------------------
# Lore entries (recipeId null, grid null) for the flagship recipe-less legacy features.
# Icons verified against assets/copper_inferno/items/ below like every other id.
# ---------------------------------------------------------------------------
LORE_ENTRIES = [
    ("items", "legacyhandbook/dr_pepper_brewing", f"{NS}:dr_pepper", None, None, None, 0,
     "Dr.Pepper is brewed at a brewing stand: a Water Bottle plus Black Dye makes the "
     "Dark Soda Base, Sugar turns it into Sweet Dark Brew, and Gunpowder finishes the "
     "can. Drinking it grants Speed V plus the Dr.Pepper kick, which unleashes the DOOM "
     "soundtrack and motion blur on the client.",
     "Dr.Pepper wird am Braustand gebraut: Eine Wasserflasche plus Schwarzer Farbstoff "
     "ergibt die Dunkle Limonadenbasis, Zucker macht daraus S\u00fc\u00dfes Dunkles "
     "Gebr\u00e4u und Schwarzpulver vollendet die Dose. Beim Trinken gibt es "
     "Schnelligkeit V und den Dr.Pepper-Kick, der clientseitig den DOOM-Soundtrack und "
     "die Bewegungsunsch\u00e4rfe entfesselt."),
    ("mobs", "legacyhandbook/dr_pepper_golem", f"{NS}:dr_pepper_golem", None, None, None, 0,
     "Right-click a vanilla Copper Golem with a Dr.Pepper to convert it into a "
     "Dr.Pepper Golem; health, name, equipment and effects carry over. Players standing "
     "close to the golem hear its DOOM proximity aura. The Dr.Pepper Golem Spawn Can "
     "places one directly.",
     "Rechtsklickt einen Kupfergolem mit einem Dr.Pepper, um ihn in einen "
     "Dr.Pepper-Golem zu verwandeln; Leben, Name, Ausr\u00fcstung und Effekte bleiben "
     "erhalten. Wer nahe beim Golem steht, h\u00f6rt seine DOOM-N\u00e4he-Aura. Die "
     "Dr.Pepper-Golem-Spawn-Dose setzt direkt einen Golem in die Welt."),
    ("gear", "legacyhandbook/copper_armor_oxidation", f"{NS}:exposed_copper_helmet",
     None, None, None, 0,
     "Copper armor oxidizes over time: each vanilla copper piece weathers through the "
     "exposed, weathered and oxidized stages, keeping enchantments, damage and name. "
     "Scrape a piece with an axe to remove one stage, or wax it with a honeycomb to "
     "freeze its patina.",
     "Kupferr\u00fcstung oxidiert mit der Zeit: Jedes Kupfer-R\u00fcstungsteil "
     "durchl\u00e4uft die Stufen angelaufen, verwittert und oxidiert und beh\u00e4lt "
     "dabei Verzauberungen, Haltbarkeit und Namen. Mit einer Axt kratzt ihr eine Stufe "
     "wieder ab, mit einer Honigwabe wachst ihr das Teil und friert die Patina ein."),
    ("gear", "legacyhandbook/copper_tool_oxidation", f"{NS}:exposed_copper_pickaxe",
     None, None, None, 0,
     "Copper tools oxidize just like the armor: sword, pickaxe, axe, shovel and hoe "
     "pass through the exposed, weathered and oxidized stages. An axe scrapes a stage "
     "off again, and a honeycomb waxes the tool to stop further oxidation.",
     "Kupferwerkzeuge oxidieren wie die R\u00fcstung: Schwert, Spitzhacke, Axt, "
     "Schaufel und Hacke durchlaufen die Stufen angelaufen, verwittert und oxidiert. "
     "Eine Axt kratzt eine Stufe wieder ab, und eine Honigwabe wachst das Werkzeug, "
     "sodass es nicht weiter oxidiert."),
]


# ---------------------------------------------------------------------------
# Java codegen.
# ---------------------------------------------------------------------------

def class_name_for(d: str) -> str:
    return "Legacy" + d.capitalize() + "Handbook"


def emit_feature_class(class_names: list) -> str:
    doc = genlib._javadoc([
        "Handbook coverage for the 12 legacy (pre-v3) recipe dirs plus lore pages for the",
        "recipe-less flagship features (Dr.Pepper brewing/DOOM, Dr.Pepper golems, copper",
        "armor/tool oxidation). Registers handbook entries ONLY - no blocks, items or",
        "recipes; the documented content is owned by the original legacy features.",
        "",
        "<p>Generated by {@code devtools/gen/legacyhandbook_gen.py}; must be initialized",
        "immediately before {@code HandbookFeature.init()} (which stays last).",
    ])
    out = [f"package {genlib.PKG_ROOT}.feature.{FEATURE_PKG};", "", doc,
           "public final class LegacyHandbookFeature {",
           "\tprivate LegacyHandbookFeature() {",
           "\t}", "",
           "\tpublic static void init() {"]
    out += [f"\t\t{name}.register();" for name in class_names]
    out += ["\t}", "}"]
    return "\n".join(out) + "\n"


def emit_hooks(total_entries: int) -> None:
    lines = [
        "# legacyhandbook feature hooks (format: devtools/hooks/README.md)",
        "",
        "[init]",
        "# Handbook-only feature (no blocks/items/recipes of its own): registers handbook",
        "# entries for the 12 legacy recipe dirs + 4 lore pages. Insert IMMEDIATELY BEFORE",
        "# HandbookFeature.init(); (HandbookFeature stays LAST).",
        "# devtools/check_handbook.py \"legacy coverage\" group:",
        '# FEATURE_PKGS += ["masonry", "sodablocks", "materials", "decostone", "foods",',
        '#                  "inferno", "music", "utilityblocks", "glasslight", "gear",',
        '#                  "extras", "statue", "legacyhandbook"]',
        "import net.sonic0810.copperinferno.feature.legacyhandbook.LegacyHandbookFeature;",
        "\t\tLegacyHandbookFeature.init();",
        "",
        "[counts]",
        f"handbook-entries: {total_entries}",
        "",
    ]
    path = ROOT / "devtools" / "hooks" / f"{FEATURE_PKG}.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)

    class_names = []
    recipe_count = 0
    expected_files = set()

    for d in LEGACY_DIRS:
        recipe_dir = RECIPE_ROOT / d
        entries = []
        for path in sorted(recipe_dir.glob("*.json")):
            recipe = json.loads(path.read_text(encoding="utf-8"))
            entries.append(entry_for(d, path.stem, recipe))
        if not entries:
            raise SystemExit(f"legacyhandbook_gen: no recipes found under {recipe_dir}")
        recipe_count += len(entries)

        cname = class_name_for(d)
        class_names.append(cname)
        noun = "entry" if len(entries) == 1 else "entries"
        doc = [
            f"Legacy handbook pages for the \"{d}\" recipe dir: one entry per JSON under",
            f"{{@code data/copper_inferno/recipe/{d}/}} ({len(entries)} {noun}, category",
            f"\"{CATEGORY[d]}\"). Grids/texts are derived from the recipe JSONs by",
            "{@code devtools/gen/legacyhandbook_gen.py}; {@code devtools/check_handbook.py}",
            "parses the inline {@code new HandbookEntry(...)} literals positionally, so keep",
            "them inline.",
        ]
        src = genlib.java_handbook_class(FEATURE_PKG, cname, doc, entries)
        (FEATURE_DIR / f"{cname}.java").write_text(src, encoding="utf-8")
        expected_files.add(f"{cname}.java")

    # Lore pages (recipeId/grid null); icon ids are item-checked like everything else.
    for entry in LORE_ENTRIES:
        ITEM_IDS_USED.add(entry[2])
    lore_doc = [
        "Lore pages (no recipeId/grid) for the flagship recipe-less legacy features:",
        "Dr.Pepper brewing + the DOOM kick, Dr.Pepper golems, and copper armor/tool",
        "oxidation. Generated by {@code devtools/gen/legacyhandbook_gen.py}.",
    ]
    lore_src = genlib.java_handbook_class(FEATURE_PKG, "LegacyLoreHandbook", lore_doc,
                                          LORE_ENTRIES)
    (FEATURE_DIR / "LegacyLoreHandbook.java").write_text(lore_src, encoding="utf-8")
    expected_files.add("LegacyLoreHandbook.java")
    class_names.append("LegacyLoreHandbook")

    feature_src = emit_feature_class(class_names)
    (FEATURE_DIR / "LegacyHandbookFeature.java").write_text(feature_src, encoding="utf-8")
    expected_files.add("LegacyHandbookFeature.java")

    # Idempotency: drop stale sources from earlier generator revisions.
    for path in FEATURE_DIR.glob("*.java"):
        if path.name not in expected_files:
            path.unlink()

    # Every referenced copper_inferno item id must have items/<id>.json (the same check
    # devtools/check_handbook.py enforces; failing here keeps the generator atomic).
    missing = []
    for item_id in sorted(ITEM_IDS_USED):
        ns, _, item_path = item_id.rpartition(":")
        if ns != NS:
            continue
        if not (ITEM_DEFS / f"{item_path}.json").is_file():
            missing.append(item_id)
    if missing:
        raise SystemExit(f"legacyhandbook_gen: no items/<id>.json for: {missing}")

    total = recipe_count + len(LORE_ENTRIES)
    assert recipe_count == EXPECTED_RECIPES, \
        f"expected {EXPECTED_RECIPES} legacy recipes, found {recipe_count}"
    assert len(LORE_ENTRIES) == EXPECTED_LORE
    emit_hooks(total)

    print(f"legacyhandbook_gen: {total} handbook entries ({recipe_count} recipe pages "
          f"across {len(LEGACY_DIRS)} legacy dirs + {len(LORE_ENTRIES)} lore pages) -> "
          f"{len(expected_files)} Java sources.")


if __name__ == "__main__":
    main()
