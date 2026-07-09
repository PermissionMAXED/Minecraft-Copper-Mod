#!/usr/bin/env python3
"""Brews lang-fragment generator for COPPER INFERNO 1 (v4.1 Brews WP, mod id: copper_inferno).

The Brews feature (feature/brews/BrewsFeature.java) registers NO new items or blocks —
it registers three net.minecraft.potion.Potion entries (copper_inferno:fizz_potion,
copper_inferno:copperskin_potion, copper_inferno:doompepper_brew) that live inside the
VANILLA potion/splash/lingering/tipped-arrow item stacks. Their stack names therefore
come from the vanilla key patterns derived from each Potion's base name (verified against
assets/minecraft/lang/en_us.json in the 1.21.9 client jar):

  item.minecraft.potion.effect.<base>            e.g. "Potion of Leaping"
  item.minecraft.splash_potion.effect.<base>     e.g. "Splash Potion of Leaping"
  item.minecraft.lingering_potion.effect.<base>  e.g. "Lingering Potion of Leaping"
  item.minecraft.tipped_arrow.effect.<base>      e.g. "Arrow of Leaping"

Those minecraft-namespaced keys are the only assets this work package owns, emitted to

  src/main/resources/assets/copper_inferno/lang/fragments/brews.json     (EN)
  src/main/resources/assets/copper_inferno/lang/fragments_de/brews.json  (real German)

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

# base name -> (potion, splash, lingering, tipped arrow) per locale.
POTIONS = {
    "fizz_potion": {
        "en": ("Fizz Potion", "Splash Fizz Potion", "Lingering Fizz Potion",
               "Arrow of Fizz"),
        "de": ("Sprudeltrank", "Wurf-Sprudeltrank", "Verweil-Sprudeltrank",
               "Sprudelpfeil"),
    },
    "copperskin_potion": {
        "en": ("Copperskin Potion", "Splash Copperskin Potion",
               "Lingering Copperskin Potion", "Arrow of Copperskin"),
        "de": ("Kupferhauttrank", "Wurf-Kupferhauttrank",
               "Verweil-Kupferhauttrank", "Kupferhautpfeil"),
    },
    "doompepper_brew": {
        "en": ("Doompepper Brew", "Splash Doompepper Brew",
               "Lingering Doompepper Brew", "Arrow of Doompepper"),
        "de": ("Doompepper-Gebr\u00e4u", "Wurf-Doompepper-Gebr\u00e4u",
               "Verweil-Doompepper-Gebr\u00e4u", "Doompepper-Pfeil"),
    },
}

KEY_PATTERNS = (
    "item.minecraft.potion.effect.{}",
    "item.minecraft.splash_potion.effect.{}",
    "item.minecraft.lingering_potion.effect.{}",
    "item.minecraft.tipped_arrow.effect.{}",
)


def lang(locale: str) -> dict:
    out = {}
    for base, names in POTIONS.items():
        for pattern, name in zip(KEY_PATTERNS, names[locale]):
            out[pattern.format(base)] = name
    return out


def main() -> int:
    lang_en = lang("en")
    lang_de = lang("de")
    assert set(lang_en) == set(lang_de), "EN/DE brews lang keys out of sync"
    lib.write_json(LANG_DIR / "fragments" / "brews.json", lang_en)
    lib.write_json(LANG_DIR / "fragments_de" / "brews.json", lang_de)
    print(f"[brews_gen] wrote 2 lang fragments ({len(lang_en)} keys each)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
