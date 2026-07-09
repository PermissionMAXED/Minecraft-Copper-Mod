#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4.1 "Ember Storm" feature.

Emits ONLY the two lang fragments (no blocks/items/recipes — the feature is pure
server-side weather logic in feature/emberstorm/EmberStormFeature.java):
  - assets/copper_inferno/lang/fragments/emberstorm.json    (EN)
  - assets/copper_inferno/lang/fragments_de/emberstorm.json (real German)

Keys cover the /emberstorm <start|stop|status> command feedback plus the storm
begin/end action-bar messages. The handbook entry's bilingual body text is inline in
EmberStormFeature.java (repo convention for "dimension" lore entries — see
feature/wildworld/RuinedForgesFeature.java), so no handbook body keys live here.

Idempotent: the dicts below are constant, so re-running writes identical bytes.
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from lib_gen import NS, write_json  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
ASSETS = ROOT / "src" / "main" / "resources" / "assets" / NS

LANG_EN = {
    "command.copper_inferno.emberstorm.started": "Ember storm started in the Inferno - %s remaining.",
    "command.copper_inferno.emberstorm.stopped": "Ember storm stopped - the Inferno calms (next storm in %s).",
    "command.copper_inferno.emberstorm.not_storming": "No ember storm is running.",
    "command.copper_inferno.emberstorm.status.calm": "Ember Storm: calm - next storm in %s.",
    "command.copper_inferno.emberstorm.status.storm": "Ember Storm: storm - %s remaining.",
    "command.copper_inferno.emberstorm.no_world": "The Inferno dimension is not loaded.",
    "message.copper_inferno.emberstorm.begin": "An ember storm sweeps across the Inferno - take cover!",
    "message.copper_inferno.emberstorm.end": "The ember storm dies down.",
}

LANG_DE = {
    "command.copper_inferno.emberstorm.started": "Glutsturm im Inferno gestartet - noch %s.",
    "command.copper_inferno.emberstorm.stopped": "Glutsturm gestoppt - das Inferno beruhigt sich (nächster Sturm in %s).",
    "command.copper_inferno.emberstorm.not_storming": "Es tobt gerade kein Glutsturm.",
    "command.copper_inferno.emberstorm.status.calm": "Glutsturm: ruhig - nächster Sturm in %s.",
    "command.copper_inferno.emberstorm.status.storm": "Glutsturm: Sturm - noch %s.",
    "command.copper_inferno.emberstorm.no_world": "Die Inferno-Dimension ist nicht geladen.",
    "message.copper_inferno.emberstorm.begin": "Ein Glutsturm fegt über das Inferno - such Schutz!",
    "message.copper_inferno.emberstorm.end": "Der Glutsturm flaut ab.",
}


def main() -> None:
    assert set(LANG_EN) == set(LANG_DE), "EN/DE fragments must cover identical keys"
    write_json(ASSETS / "lang" / "fragments" / "emberstorm.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "emberstorm.json", LANG_DE)
    print(f"[emberstorm_gen] wrote 2 lang fragments ({len(LANG_EN)} keys each)")


if __name__ == "__main__":
    main()
