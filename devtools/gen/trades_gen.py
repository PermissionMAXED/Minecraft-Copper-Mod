#!/usr/bin/env python3
"""Trades lang-fragment generator for COPPER INFERNO 1 (v4.1 Trades WP, mod id: copper_inferno).

The Trades feature (feature/trades/TradesFeature.java) registers NO new items/blocks —
every offer resolves existing ids (gemalloy, scorchwood, drpepper, infernofoods) via
runtime registry lookups, and its handbook entry carries bilingual text inline. So the
only assets this work package owns are the lang fragments below: a few handbook/UI keys
describing the trade lineup, emitted to

  src/main/resources/assets/copper_inferno/lang/fragments/trades.json     (EN)
  src/main/resources/assets/copper_inferno/lang/fragments_de/trades.json  (real German)

and merged into en_us.json / de_de.json later by devtools/merge_lang.py.

Idempotent: running it any number of times produces byte-identical output (sorted keys,
indent=2, trailing newline — the repo lang-fragment format written by lib_gen.write_json).
"""
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import lib_gen as lib

ROOT = Path(__file__).resolve().parent.parent.parent
LANG_DIR = ROOT / "src/main/resources/assets/copper_inferno/lang"

# Handbook/UI blurbs for the trades lineup. Nothing in the current UI hard-requires
# these keys (the handbook entry text is inline-bilingual), but they give screens /
# tooltips stable translatable keys for the trade lineup.
LANG_EN = {
    "handbook.copper_inferno.trades.title": "Villager & Wandering Trades",
    "handbook.copper_inferno.trades.toolsmith": "Toolsmiths buy raw gemalloy chunks and sell ingots and a lamp (levels 1-3).",
    "handbook.copper_inferno.trades.librarian": "Librarians sell an Ember Edge book once that enchantment exists.",
    "handbook.copper_inferno.trades.wanderer": "The Wandering Trader hawks a scorchwood sapling, Dr.Pepper, a nugget and jerky.",
}

LANG_DE = {
    "handbook.copper_inferno.trades.title": "Dorfbewohner- & Wanderh\u00e4ndler-Angebote",
    "handbook.copper_inferno.trades.toolsmith": "Werkzeugschmiede kaufen rohe Edellegierungsbrocken und verkaufen Barren und eine Lampe (Stufen 1-3).",
    "handbook.copper_inferno.trades.librarian": "Bibliothekare verkaufen ein Glutschneide-Buch, sobald diese Verzauberung existiert.",
    "handbook.copper_inferno.trades.wanderer": "Der fahrende H\u00e4ndler bietet einen Brandholz-Setzling, Dr.Pepper, einen Klumpen und D\u00f6rrfleisch.",
}


def main() -> int:
    assert set(LANG_EN) == set(LANG_DE), "EN/DE trades lang keys out of sync"
    lib.write_json(LANG_DIR / "fragments" / "trades.json", LANG_EN)
    lib.write_json(LANG_DIR / "fragments_de" / "trades.json", LANG_DE)
    print(f"[trades_gen] wrote 2 lang fragments ({len(LANG_EN)} keys each)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
