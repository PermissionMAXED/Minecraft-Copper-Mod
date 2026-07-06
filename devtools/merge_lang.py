#!/usr/bin/env python3
"""Merge lang fragments into en_us.json and verify lang-key coverage.

- Merges assets/copper_inferno/lang/fragments/*.json into assets/copper_inferno/lang/en_us.json.
- Existing en_us.json keys WIN on conflict (protects branding / curated names).
- New keys are added; output is alphabetically sorted.
- Fails loudly (non-zero exit) on malformed fragment JSON.
- Verifies every registered id has a lang key:
    block.copper_inferno.<id>   for every blockstate file
    item.copper_inferno.<id>    for every non-block item (items/*.json without a blockstate)
    itemGroup.copper_inferno.main + .blocks
    jukebox_song.copper_inferno.<id> for every data/copper_inferno/jukebox_song/*.json
  Missing keys are added with a readable name derived from the id.
"""
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
ASSETS = os.path.join(ROOT, "src/main/resources/assets/copper_inferno")
DATA = os.path.join(ROOT, "src/main/resources/data/copper_inferno")
LANG = os.path.join(ASSETS, "lang/en_us.json")
FRAGMENTS = os.path.join(ASSETS, "lang/fragments")


def readable(ident: str) -> str:
    words = ident.split("_")
    out = []
    for w in words:
        if w == "dr":
            out.append("Dr.")
        else:
            out.append(w.capitalize())
    name = " ".join(out)
    return name.replace("Dr. Pepper", "Dr.Pepper")


def main() -> int:
    with open(LANG, encoding="utf-8") as f:
        base = json.load(f)
    print(f"[merge_lang] base en_us.json: {len(base)} keys")

    merged = dict(base)
    added_from_fragments = 0
    conflicts_kept = 0
    for fname in sorted(os.listdir(FRAGMENTS)):
        if not fname.endswith(".json"):
            continue
        path = os.path.join(FRAGMENTS, fname)
        try:
            with open(path, encoding="utf-8") as f:
                frag = json.load(f)
        except (json.JSONDecodeError, UnicodeDecodeError) as e:
            print(f"[merge_lang] FATAL: malformed fragment {fname}: {e}", file=sys.stderr)
            return 1
        if not isinstance(frag, dict) or not all(
            isinstance(k, str) and isinstance(v, str) for k, v in frag.items()
        ):
            print(f"[merge_lang] FATAL: fragment {fname} is not a flat str->str object", file=sys.stderr)
            return 1
        for k, v in frag.items():
            if k in merged:
                if merged[k] != v:
                    conflicts_kept += 1
                    print(f"[merge_lang] conflict on {k!r}: keeping existing {merged[k]!r} (fragment {fname} had {v!r})")
            else:
                merged[k] = v
                added_from_fragments += 1
    print(f"[merge_lang] added {added_from_fragments} keys from fragments; {conflicts_kept} conflicts kept existing value")

    # --- coverage verification ---
    blockstates = sorted(
        os.path.splitext(f)[0]
        for f in os.listdir(os.path.join(ASSETS, "blockstates"))
        if f.endswith(".json")
    )
    items = sorted(
        os.path.splitext(f)[0]
        for f in os.listdir(os.path.join(ASSETS, "items"))
        if f.endswith(".json")
    )
    block_set = set(blockstates)
    jukebox = sorted(
        os.path.splitext(f)[0]
        for f in os.listdir(os.path.join(DATA, "jukebox_song"))
        if f.endswith(".json")
    )

    required = {}
    for b in blockstates:
        required[f"block.copper_inferno.{b}"] = readable(b)
    for i in items:
        if i not in block_set:
            required[f"item.copper_inferno.{i}"] = readable(i)
    required["itemGroup.copper_inferno.main"] = "COPPER INFERNO 1"
    required["itemGroup.copper_inferno.blocks"] = "COPPER INFERNO 1: Blocks"
    for j in jukebox:
        required[f"jukebox_song.copper_inferno.{j}"] = readable(j)

    missing_added = []
    for key, name in sorted(required.items()):
        if key not in merged:
            merged[key] = name
            missing_added.append(key)
    if missing_added:
        print(f"[merge_lang] added {len(missing_added)} MISSING keys with derived names:")
        for k in missing_added:
            print(f"  + {k} = {merged[k]!r}")
    else:
        print("[merge_lang] coverage check: all required keys present")

    # sanity: branding must not regress
    assert merged["itemGroup.copper_inferno.main"] == "COPPER INFERNO 1", "branding regressed!"
    assert merged["effect.copper_inferno.dr_pepper_kick"] == "Dr.Pepper kick", "effect name regressed!"

    with open(LANG, "w", encoding="utf-8") as f:
        json.dump(dict(sorted(merged.items())), f, indent=2, ensure_ascii=False)
        f.write("\n")
    print(f"[merge_lang] wrote {LANG}: {len(merged)} keys (sorted)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
