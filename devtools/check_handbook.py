#!/usr/bin/env python3
"""Handbook coverage check for the v3 "Inferno Dimension" features.

Scans the v3 feature Java packages (main + client source sets) for
HandbookEntries.add(new HandbookEntry(...)) calls and extracts each entry's recipeId,
iconItemId, grid item ids and resultItemId string literals.

Fails (exit 1) when:
  - a recipe JSON under a v3 recipe dir (data/copper_inferno/recipe/{infernodim,cinderstone,
    infernoflora,copperdeco,infernium,infernofoods,infernofx,handbook,infernoboss,
    infernomobs}) has no handbook entry whose recipeId matches it — matching accepts the
    "dir/name" path or the bare file name, with or without the copper_inferno: prefix — or
  - a referenced copper_inferno item id has no assets/copper_inferno/items/<id>.json on disk.

Lenient by design: only recipe dirs that exist are considered, so a tree with no v3 recipe
dirs is green. Prints "[check_handbook] OK" when green.
"""
import os
import re
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
FEATURE_PKGS = ["infernodim", "cinderstone", "infernoflora", "copperdeco", "infernium",
                "infernomobs", "infernoboss", "infernofoods", "infernofx", "handbook",
                # v4 content wave
                "pyrestone", "moltenmetal", "smolderquartz", "nightslate", "charwood",
                "kilnstone", "forgeparts", "infernocuisine", "titanforge",
                # v5 content wave (mobs, bosses, worldgen; the worldgen features
                # infernogeology/infernogardens have no recipe dirs — the recipe scan
                # skips missing dirs, but their handbook entries still get item-checked)
                "ashhorde", "emberswarm", "moltenfauna", "slagfiends",
                "calamities", "archfiends", "infernogeology", "infernogardens",
                # v6: Inferno dimension expansion
                "infernodim2",
                # legacy coverage: the 12 pre-v3 recipe dirs, whose handbook entries are
                # generated into feature/legacyhandbook by
                # devtools/gen/legacyhandbook_gen.py (legacyhandbook itself has no recipe
                # dir; it is listed so its entry sources get scanned)
                "masonry", "sodablocks", "materials", "decostone", "foods", "inferno",
                "music", "utilityblocks", "glasslight", "gear", "extras", "statue",
                "legacyhandbook"]
JAVA_ROOTS = [
    os.path.join(ROOT, "src/main/java/net/sonic0810/copperinferno/feature"),
    os.path.join(ROOT, "src/client/java/net/sonic0810/copperinferno/feature"),
]
RECIPE_ROOT = os.path.join(ROOT, "src/main/resources/data/copper_inferno/recipe")
ITEM_DEFS = os.path.join(ROOT, "src/main/resources/assets/copper_inferno/items")

STRING_RE = re.compile(r'"((?:[^"\\]|\\.)*)"')

findings = []


def finding(msg: str) -> None:
    findings.append(msg)


def strip_comments(text: str) -> str:
    """Remove // and /* */ comments, string-aware."""
    out = []
    i = 0
    n = len(text)
    while i < n:
        c = text[i]
        if c == '"':
            j = i + 1
            while j < n and text[j] != '"':
                j += 2 if text[j] == "\\" else 1
            out.append(text[i:j + 1])
            i = j + 1
        elif text.startswith("//", i):
            i = text.find("\n", i)
            i = n if i == -1 else i
        elif text.startswith("/*", i):
            i = text.find("*/", i)
            i = n if i == -1 else i + 2
        else:
            out.append(c)
            i += 1
    return "".join(out)


def balanced_args(text: str, open_paren: int) -> str:
    """Return the text between the balanced parens whose '(' is at open_paren."""
    depth = 0
    i = open_paren
    while i < len(text):
        c = text[i]
        if c == '"':
            i += 1
            while i < len(text) and text[i] != '"':
                i += 2 if text[i] == "\\" else 1
        elif c == "(":
            depth += 1
        elif c == ")":
            depth -= 1
            if depth == 0:
                return text[open_paren + 1:i]
        i += 1
    raise ValueError("unbalanced parentheses")


def split_top_level(argstr: str) -> list[str]:
    """Split an argument list at top-level commas ((), {}, [] and string aware)."""
    args = []
    depth = 0
    start = 0
    i = 0
    while i < len(argstr):
        c = argstr[i]
        if c == '"':
            i += 1
            while i < len(argstr) and argstr[i] != '"':
                i += 2 if argstr[i] == "\\" else 1
        elif c in "({[":
            depth += 1
        elif c in ")}]":
            depth -= 1
        elif c == "," and depth == 0:
            args.append(argstr[start:i].strip())
            start = i + 1
        i += 1
    args.append(argstr[start:].strip())
    return args


def single_literal(arg: str) -> str | None:
    """The literal's value when the arg is exactly one string literal, else None."""
    m = STRING_RE.fullmatch(arg.strip())
    return m.group(1) if m else None


def main() -> int:
    # ---------- collect HandbookEntries.add(new HandbookEntry(...)) calls ----------
    recipe_ids: set[str] = set()   # normalized: copper_inferno: prefix stripped
    item_ids: dict[str, str] = {}  # item id -> "file:where" (first reference wins)
    call_count = 0

    for java_root in JAVA_ROOTS:
        for pkg in FEATURE_PKGS:
            pkg_dir = os.path.join(java_root, pkg)
            if not os.path.isdir(pkg_dir):
                continue
            for dirpath, _dirs, files in os.walk(pkg_dir):
                for fn in sorted(files):
                    if not fn.endswith(".java"):
                        continue
                    path = os.path.join(dirpath, fn)
                    rel = os.path.relpath(path, ROOT)
                    with open(path, encoding="utf-8") as f:
                        text = strip_comments(f.read())
                    for m in re.finditer(r"HandbookEntries\s*\.\s*add\s*\(", text):
                        call_count += 1
                        add_args = balanced_args(text, m.end() - 1)
                        ctor = re.search(r"new\s+HandbookEntry\s*\(", add_args)
                        if ctor is None:
                            print(f"[check_handbook] WARNING: cannot positionally parse "
                                  f"HandbookEntries.add(...) in {rel} (no inline `new HandbookEntry(`); skipping")
                            continue
                        args = split_top_level(balanced_args(add_args, ctor.end() - 1))
                        if len(args) != 9:
                            print(f"[check_handbook] WARNING: `new HandbookEntry(...)` in {rel} has "
                                  f"{len(args)} args (expected 9); skipping")
                            continue
                        # (category, id, iconItemId, recipeId, grid, resultItemId, resultCount, textEn, textDe)
                        icon = single_literal(args[2])
                        if icon:
                            item_ids.setdefault(icon, rel)
                        rid = single_literal(args[3])
                        if rid is not None:
                            recipe_ids.add(rid.removeprefix("copper_inferno:"))
                        if args[4] != "null":
                            for g in STRING_RE.findall(args[4]):
                                if g:
                                    item_ids.setdefault(g, rel)
                        result = single_literal(args[5])
                        if result:
                            item_ids.setdefault(result, rel)
    print(f"[check_handbook] {call_count} HandbookEntries.add calls scanned; "
          f"{len(recipe_ids)} recipe ids, {len(item_ids)} distinct item ids referenced")

    # ---------- every v3 recipe has a handbook entry ----------
    v3_recipe_dirs = [d for d in FEATURE_PKGS if os.path.isdir(os.path.join(RECIPE_ROOT, d))]
    recipe_count = 0
    for d in sorted(v3_recipe_dirs):
        base = os.path.join(RECIPE_ROOT, d)
        for dirpath, _dirs, files in os.walk(base):
            for fn in sorted(files):
                if not fn.endswith(".json"):
                    continue
                recipe_count += 1
                rel_id = os.path.relpath(os.path.join(dirpath, fn), RECIPE_ROOT)[:-5].replace(os.sep, "/")
                stem = os.path.splitext(fn)[0]
                if rel_id not in recipe_ids and stem not in recipe_ids:
                    finding(f"recipe data/copper_inferno/recipe/{rel_id}.json has no handbook entry "
                            f"(no HandbookEntries.add with recipeId {rel_id!r} or {stem!r})")
    print(f"[check_handbook] {recipe_count} recipes in v3 dirs {sorted(v3_recipe_dirs)} checked against handbook entries")

    # ---------- every referenced item id resolves to items/<id>.json ----------
    checked = 0
    for iid in sorted(item_ids):
        ns, _, path = iid.rpartition(":")
        if ns == "minecraft":
            continue  # vanilla items are not on our disk
        if ns not in ("", "copper_inferno"):
            finding(f"handbook entry in {item_ids[iid]} references unexpected namespace: {iid}")
            continue
        checked += 1
        if not os.path.isfile(os.path.join(ITEM_DEFS, path + ".json")):
            finding(f"handbook entry in {item_ids[iid]} references {iid} "
                    f"but assets/copper_inferno/items/{path}.json does not exist")
    print(f"[check_handbook] {checked} copper_inferno item ids resolved against items/")

    if findings:
        print(f"[check_handbook] {len(findings)} FINDINGS:")
        for f in findings:
            print("  - " + f)
        return 1
    print("[check_handbook] OK")
    return 0


if __name__ == "__main__":
    sys.exit(main())
