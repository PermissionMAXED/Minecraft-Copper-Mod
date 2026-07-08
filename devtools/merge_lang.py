#!/usr/bin/env python3
"""Merge lang fragments into a locale file and verify lang-key coverage.

Default locale (en_us) — behavior unchanged:
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

Non-en locale (e.g. --locale de_de):
- Merges assets/copper_inferno/lang/fragments_<prefix>/*.json (de_de -> fragments_de; the
  prefix is the locale's language part before the underscore) into
  assets/copper_inferno/lang/<code>.json (created if absent). Same conflict rules
  (existing locale keys win).
- Coverage: every key present in en_us.json but missing in the locale is filled from the EN
  value and reported as "WARNING untranslated: <key>".
- Branding: itemGroup.copper_inferno.main must equal "COPPER INFERNO 1" in EVERY locale;
  the effect.copper_inferno.dr_pepper_kick == "Dr.Pepper kick" assert applies to en_us only.
"""
import argparse
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


def merge_fragments(merged: dict, fragments_dir: str) -> bool:
    """Merge fragment files into `merged` in place (existing keys win). False on fatal error."""
    added_from_fragments = 0
    conflicts_kept = 0
    for fname in sorted(os.listdir(fragments_dir)):
        if not fname.endswith(".json"):
            continue
        path = os.path.join(fragments_dir, fname)
        try:
            with open(path, encoding="utf-8") as f:
                frag = json.load(f)
        except (json.JSONDecodeError, UnicodeDecodeError) as e:
            print(f"[merge_lang] FATAL: malformed fragment {fname}: {e}", file=sys.stderr)
            return False
        if not isinstance(frag, dict) or not all(
            isinstance(k, str) and isinstance(v, str) for k, v in frag.items()
        ):
            print(f"[merge_lang] FATAL: fragment {fname} is not a flat str->str object", file=sys.stderr)
            return False
        for k, v in frag.items():
            if k in merged:
                if merged[k] != v:
                    conflicts_kept += 1
                    print(f"[merge_lang] conflict on {k!r}: keeping existing {merged[k]!r} (fragment {fname} had {v!r})")
            else:
                merged[k] = v
                added_from_fragments += 1
    print(f"[merge_lang] added {added_from_fragments} keys from fragments; {conflicts_kept} conflicts kept existing value")
    return True


def merge_en() -> int:
    with open(LANG, encoding="utf-8") as f:
        base = json.load(f)
    print(f"[merge_lang] base en_us.json: {len(base)} keys")

    merged = dict(base)
    if not merge_fragments(merged, FRAGMENTS):
        return 1

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


def locale_fragments_dir(code: str) -> str:
    """de_de -> lang/fragments_de (generally the locale's short language prefix)."""
    return os.path.join(ASSETS, "lang", "fragments_" + code.split("_")[0])


def merge_locale(code: str) -> int:
    lang_path = os.path.join(ASSETS, "lang", code + ".json")
    if os.path.isfile(lang_path):
        with open(lang_path, encoding="utf-8") as f:
            base = json.load(f)
        print(f"[merge_lang] base {code}.json: {len(base)} keys")
    else:
        base = {}
        print(f"[merge_lang] {code}.json does not exist yet; starting from 0 keys")

    merged = dict(base)
    fragments_dir = locale_fragments_dir(code)
    if os.path.isdir(fragments_dir):
        if not merge_fragments(merged, fragments_dir):
            return 1
    else:
        print(f"[merge_lang] no fragment dir {os.path.relpath(fragments_dir, ROOT)}; nothing to merge")

    # --- coverage: every en_us key must exist; fill gaps from EN ---
    with open(LANG, encoding="utf-8") as f:
        en = json.load(f)
    untranslated = 0
    for key in sorted(en):
        if key not in merged:
            merged[key] = en[key]
            print(f"WARNING untranslated: {key}")
            untranslated += 1
    if untranslated:
        print(f"[merge_lang] filled {untranslated} untranslated keys from en_us")
    else:
        print("[merge_lang] coverage check: every en_us key is translated")

    # sanity: branding must not regress (brand name is identical in every locale)
    assert merged["itemGroup.copper_inferno.main"] == "COPPER INFERNO 1", "branding regressed!"

    with open(lang_path, "w", encoding="utf-8") as f:
        json.dump(dict(sorted(merged.items())), f, indent=2, ensure_ascii=False)
        f.write("\n")
    print(f"[merge_lang] wrote {lang_path}: {len(merged)} keys (sorted)")
    return 0


def main() -> int:
    parser = argparse.ArgumentParser(description="Merge lang fragments and verify lang-key coverage.")
    parser.add_argument("--locale", default="en_us",
                        help="locale code, e.g. en_us (default) or de_de")
    args = parser.parse_args()
    if args.locale == "en_us":
        return merge_en()
    return merge_locale(args.locale)


if __name__ == "__main__":
    sys.exit(main())
