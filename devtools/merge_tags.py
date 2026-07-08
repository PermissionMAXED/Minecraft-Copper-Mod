#!/usr/bin/env python3
"""Merge tag fragments into the shared data/minecraft/tags JSONs.

Each v3 worker drops a devtools/tagfrag/<worker>.json shaped like:
    {"block/mineable/pickaxe": ["copper_inferno:x", ...],
     "block/walls": ["copper_inferno:y_wall", ...],
     "item/swords": ["copper_inferno:z_sword", ...]}
Every entry is merged into src/main/resources/data/minecraft/tags/<path>.json
(created as {"values": []} first if missing). Values are deduped and kept sorted, so the
script is idempotent — safe to re-run any number of times.

No devtools/tagfrag directory = no-op (exit 0).
"""
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
TAGFRAG = os.path.join(ROOT, "devtools/tagfrag")
TAGS = os.path.join(ROOT, "src/main/resources/data/minecraft/tags")


def main() -> int:
    if not os.path.isdir(TAGFRAG):
        print("[merge_tags] no devtools/tagfrag directory — nothing to merge")
        return 0

    additions: dict[str, set[str]] = {}  # tag path (e.g. "block/walls") -> ids to merge
    frag_count = 0
    for fname in sorted(os.listdir(TAGFRAG)):
        if not fname.endswith(".json"):
            continue
        path = os.path.join(TAGFRAG, fname)
        try:
            with open(path, encoding="utf-8") as f:
                frag = json.load(f)
        except (json.JSONDecodeError, UnicodeDecodeError) as e:
            print(f"[merge_tags] FATAL: malformed fragment {fname}: {e}", file=sys.stderr)
            return 1
        if not isinstance(frag, dict) or not all(
            isinstance(k, str)
            and isinstance(v, list)
            and all(isinstance(i, str) for i in v)
            for k, v in frag.items()
        ):
            print(f"[merge_tags] FATAL: fragment {fname} is not a str -> list-of-str object", file=sys.stderr)
            return 1
        frag_count += 1
        for tag_path, ids in frag.items():
            additions.setdefault(tag_path, set()).update(ids)

    written = 0
    for tag_path in sorted(additions):
        dest = os.path.join(TAGS, tag_path + ".json")
        if os.path.isfile(dest):
            with open(dest, encoding="utf-8") as f:
                data = json.load(f)
        else:
            data = {"values": []}
        old_values = data.get("values", [])
        merged = sorted(set(old_values) | additions[tag_path])
        added = len(merged) - len(set(old_values))
        if merged != old_values:
            data["values"] = merged
            os.makedirs(os.path.dirname(dest), exist_ok=True)
            with open(dest, "w", encoding="utf-8") as f:
                json.dump(data, f, indent=2, ensure_ascii=False)
                f.write("\n")
            written += 1
            print(f"[merge_tags] {tag_path}: +{added} new, {len(merged)} total (written)")
        else:
            print(f"[merge_tags] {tag_path}: +0 new, {len(merged)} total (unchanged)")

    print(f"[merge_tags] merged {frag_count} fragment files into {len(additions)} tags; {written} tag files written")
    return 0


if __name__ == "__main__":
    sys.exit(main())
