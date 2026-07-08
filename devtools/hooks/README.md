# devtools/hooks — feature hook files

Each new feature ships a hook file `devtools/hooks/<feature>.txt` describing exactly how
the feature plugs into the shared wiring (mod initializers) and the shared audit scripts
(`devtools/check_tags.py`, `devtools/check_handbook.py`). The integrator applies these
hooks; the audit scripts are extended from sections (b)/(c); section (d) is the
review-time content inventory.

`<feature>` is the feature's package leaf under `net.sonic0810.copperinferno.feature.`
(also the recipe dir name), e.g. `cinderstone`.

## File format

Plain UTF-8 text. Section headers are lines of the form `[section]`; everything until the
next header belongs to that section. Blank lines and lines starting with `#` are
comments/ignored. Sections, in order:

### `[init]` — common-side wiring (required)

Exactly two lines: the import and the init call to insert into
`src/main/java/net/sonic0810/copperinferno/CopperInferno.java`, verbatim (including the
leading tabs of the init line). The init call MUST be inserted BEFORE
`HandbookFeature.init();` (HandbookFeature stays LAST so it can reference every other
feature's content); the import goes into the existing alphabetically-sorted import block.

```
import net.sonic0810.copperinferno.feature.cinderstone.CinderStoneFeature;
		CinderStoneFeature.init();
```

### `[client-init]` — client-side wiring (only when the feature has client code)

Same contract for
`src/client/java/net/sonic0810/copperinferno/CopperInfernoClient.java`: one import line +
one init line, inserted BEFORE `HandbookFeatureClient.initClient();`. Omit the section
entirely for server/common-only features.

```
import net.sonic0810.copperinferno.feature.cinderstone.client.CinderStoneFeatureClient;
		CinderStoneFeatureClient.initClient();
```

### `[families]` — registerCubeFamily tuples (for check_tags.py extension)

One `name, stem` pair per line, mirroring every
`ModBlockFamilies.registerCubeFamily("name", "stem", ...)` call in the feature. Used to
extend the family lists in `devtools/check_tags.py` (each family = base + `<stem>_slab` +
`<stem>_stairs` + `<stem>_wall`, all requiresTool -> mineable/pickaxe; walls -> walls.json).

```
cinderstone_bricks, cinderstone_brick
cinderstone_tiles, cinderstone_tile
```

### `[requires-tool]` — single requiresTool block ids (for check_tags.py extension)

One block id per line: every NON-family block registered with settings that include
`requiresTool()`. These must appear in `data/minecraft/tags/block/mineable/pickaxe.json`
(via the feature's `devtools/tagfrag/<feature>.json` fragment) or the block drops NOTHING
in survival. Blocks without `requiresTool()` (e.g. glass) must NOT be listed.

```
cracked_cinderstone_bricks
chiseled_cinderstone_bricks
```

### `[recipe-dir]` — recipe dir name (for check_handbook.py FEATURE_PKGS)

A single line: the directory name under `data/copper_inferno/recipe/` this feature emits
recipes into (== the feature package leaf). Append it to `FEATURE_PKGS` in
`devtools/check_handbook.py` so every recipe JSON is required to have a handbook entry.

```
cinderstone
```

### `[counts]` — content inventory (review bookkeeping)

`key: number` lines summarizing what the feature adds. Use whichever keys apply:
`blocks`, `items`, `mobs`, `effects`, `sounds`, `recipes`, `handbook-entries`, ...
Numbers must match the feature's gen script asserts.

```
blocks: 56
items: 0
recipes: 62
handbook-entries: 64
```

## Rules

- Init lines are EXACT source lines (copy-paste ready); do not paraphrase.
- Ordering constraints beyond "before HandbookFeature" (e.g. "after MaterialsFeature
  because recipes consume copper_dust") go in a `#` comment inside `[init]`.
- Every id listed anywhere in the hook file must be a plain literal id that exists in the
  feature's registrations (audit check (f) symbolically evaluates registration ids, so
  the sources must use plain string literals too).
