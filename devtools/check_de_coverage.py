#!/usr/bin/env python3
"""German lang coverage check.

Fails (exit 1) if assets/copper_inferno/lang/de_de.json is missing any key present in
en_us.json. Warns (non-fatal) when a de value is byte-identical to the EN value — likely
untranslated, though brand names ("COPPER INFERNO 1") are intentionally identical.

If de_de.json does not exist yet (pre-integration, before
`devtools/merge_lang.py --locale de_de` has run), prints a warning and exits 0 so the
parallel worker phase is not blocked.
"""
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
LANG_DIR = os.path.join(ROOT, "src/main/resources/assets/copper_inferno/lang")
EN = os.path.join(LANG_DIR, "en_us.json")
DE = os.path.join(LANG_DIR, "de_de.json")


def main() -> int:
    with open(EN, encoding="utf-8") as f:
        en = json.load(f)

    if not os.path.isfile(DE):
        print("[check_de] WARNING: de_de.json not built yet "
              "(run devtools/merge_lang.py --locale de_de); skipping — non-blocking")
        return 0

    with open(DE, encoding="utf-8") as f:
        de = json.load(f)

    identical = sorted(k for k in en if k in de and de[k] == en[k])
    for k in identical:
        print(f"[check_de] WARNING identical to en_us (untranslated?): {k} = {en[k]!r}")

    missing = sorted(k for k in en if k not in de)
    if missing:
        print(f"[check_de] {len(missing)} keys MISSING from de_de.json:")
        for k in missing:
            print(f"  - {k}")
        return 1

    print("[check_de] ZERO missing")
    return 0


if __name__ == "__main__":
    sys.exit(main())
