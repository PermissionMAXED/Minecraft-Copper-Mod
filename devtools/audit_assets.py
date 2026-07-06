#!/usr/bin/env python3
"""Full asset/data audit for COPPER INFERNO 1.

Checks (exit non-zero and print findings if any fail):
  (a) every .json under src/main/resources AND src/client/resources parses
  (b) every blockstate's referenced models exist on disk
  (c) every copper_inferno model's texture references resolve to a PNG
      (parent chains followed within our namespace; minecraft:* assumed OK)
  (d) every blockstate id has data/copper_inferno/loot_table/blocks/<id>.json
  (e) every items/<id>.json points to an existing model
  (f) every id string-literal registered via ModItems.register / ModBlocks.register /
      ModBlockFamilies.registerCubeFamily has an items/<id>.json
  (g) every sounds.json sound entry's .ogg exists and is nonempty
  (h) every copper_inferno:-namespaced result/ingredient id in
      data/copper_inferno/recipe/**.json exists in the registration set
"""
import json
import os
import re
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
MAIN_RES = os.path.join(ROOT, "src/main/resources")
CLIENT_RES = os.path.join(ROOT, "src/client/resources")
ASSETS = os.path.join(MAIN_RES, "assets/copper_inferno")
DATA = os.path.join(MAIN_RES, "data/copper_inferno")

findings = []


def finding(check: str, msg: str) -> None:
    findings.append(f"({check}) {msg}")


# ---------- (a) parse every json ----------
parsed = {}
json_count = 0
for res_root in (MAIN_RES, CLIENT_RES):
    for dirpath, _dirs, files in os.walk(res_root):
        for fn in files:
            if not fn.endswith(".json"):
                continue
            path = os.path.join(dirpath, fn)
            json_count += 1
            try:
                with open(path, encoding="utf-8") as f:
                    parsed[path] = json.load(f)
            except (json.JSONDecodeError, UnicodeDecodeError) as e:
                finding("a", f"JSON parse error in {os.path.relpath(path, ROOT)}: {e}")
print(f"[audit] (a) parsed {json_count} json files, {len([f for f in findings if f.startswith('(a)')])} parse errors")


def model_path(ref: str) -> str | None:
    """Filesystem path for a model reference, or None if not our namespace."""
    ns, _, p = ref.rpartition(":")
    if ns in ("", "minecraft"):
        return None
    if ns != "copper_inferno":
        finding("?", f"unexpected namespace in model ref {ref!r}")
        return None
    return os.path.join(ASSETS, "models", p + ".json")


def texture_path(ref: str) -> str | None:
    ns, _, p = ref.rpartition(":")
    if ns in ("", "minecraft"):
        return None
    if ns != "copper_inferno":
        finding("?", f"unexpected namespace in texture ref {ref!r}")
        return None
    return os.path.join(ASSETS, "textures", p + ".png")


# ---------- (b) blockstate models exist ----------
blockstate_dir = os.path.join(ASSETS, "blockstates")
blockstate_ids = sorted(
    os.path.splitext(f)[0] for f in os.listdir(blockstate_dir) if f.endswith(".json")
)
referenced_models = set()
for bid in blockstate_ids:
    path = os.path.join(blockstate_dir, bid + ".json")
    data = parsed.get(path)
    if data is None:
        continue  # parse failure already recorded
    specs = []
    if "variants" in data:
        for v in data["variants"].values():
            specs.extend(v if isinstance(v, list) else [v])
    if "multipart" in data:
        for part in data["multipart"]:
            a = part.get("apply")
            specs.extend(a if isinstance(a, list) else [a])
    if not specs:
        finding("b", f"blockstate {bid} has no variants/multipart")
    for spec in specs:
        m = spec.get("model")
        if not m:
            finding("b", f"blockstate {bid} has a variant without a model")
            continue
        referenced_models.add(m)
        mp = model_path(m)
        if mp is not None and not os.path.isfile(mp):
            finding("b", f"blockstate {bid} references missing model {m}")
print(f"[audit] (b) {len(blockstate_ids)} blockstates, {len(referenced_models)} distinct models referenced")

# ---------- (c) model texture references resolve (follow parent chain) ----------
models_dir = os.path.join(ASSETS, "models")
model_files = []
for dirpath, _dirs, files in os.walk(models_dir):
    for fn in files:
        if fn.endswith(".json"):
            model_files.append(os.path.join(dirpath, fn))


def model_ref_of(path: str) -> str:
    rel = os.path.relpath(path, models_dir)[:-5].replace(os.sep, "/")
    return "copper_inferno:" + rel


checked_textures = 0
for path in sorted(model_files):
    data = parsed.get(path)
    if data is None:
        continue
    ref = model_ref_of(path)
    # follow parent chain within our namespace, ensuring parents exist
    cur = data
    seen = {ref}
    while True:
        for tex in (cur.get("textures") or {}).values():
            if tex.startswith("#"):
                continue  # variable, bound elsewhere in the chain
            tp = texture_path(tex)
            checked_textures += 1
            if tp is not None and not os.path.isfile(tp):
                finding("c", f"model {ref} references missing texture {tex}")
        parent = cur.get("parent")
        if not parent:
            break
        pp = model_path(parent)
        if pp is None:
            break  # minecraft parent: assumed OK
        pref = "copper_inferno:" + parent.rpartition(":")[2]
        if pref in seen:
            finding("c", f"model {ref} has a parent cycle at {parent}")
            break
        seen.add(pref)
        if not os.path.isfile(pp):
            finding("c", f"model {ref} references missing parent {parent}")
            break
        cur = parsed.get(pp)
        if cur is None:
            break
print(f"[audit] (c) {len(model_files)} model files, {checked_textures} texture refs checked")

# ---------- (d) loot tables per blockstate ----------
loot_dir = os.path.join(DATA, "loot_table/blocks")
for bid in blockstate_ids:
    if not os.path.isfile(os.path.join(loot_dir, bid + ".json")):
        finding("d", f"blockstate {bid} missing loot table data/copper_inferno/loot_table/blocks/{bid}.json")
print(f"[audit] (d) loot tables checked for {len(blockstate_ids)} blocks")

# ---------- (e) item definitions point to existing models ----------
items_dir = os.path.join(ASSETS, "items")
item_ids = sorted(
    os.path.splitext(f)[0] for f in os.listdir(items_dir) if f.endswith(".json")
)


def collect_models(node, out):
    if isinstance(node, dict):
        for k, v in node.items():
            if k == "model" and isinstance(v, str):
                out.append(v)
            else:
                collect_models(v, out)
    elif isinstance(node, list):
        for v in node:
            collect_models(v, out)


item_model_refs = 0
for iid in item_ids:
    path = os.path.join(items_dir, iid + ".json")
    data = parsed.get(path)
    if data is None:
        continue
    refs = []
    collect_models(data.get("model", {}), refs)
    if not refs:
        finding("e", f"items/{iid}.json has no model reference")
    for m in refs:
        item_model_refs += 1
        mp = model_path(m)
        if mp is not None and not os.path.isfile(mp):
            finding("e", f"items/{iid}.json references missing model {m}")
print(f"[audit] (e) {len(item_ids)} item definitions, {item_model_refs} model refs checked")

# ---------- (f) java registrations have items/<id>.json ----------
# Registrations use literals directly, or pass computed names through prefix
# loops (STAGE_PREFIXES) and thin wrapper methods (registerPiece/registerSword/
# registerFamily/...). We symbolically evaluate the first argument of every
# ModItems.register / ModBlocks.register call: string literals concatenated
# with `prefix` (expanded to the file's STAGE_PREFIXES) and with String
# parameters of the enclosing wrapper method (bound from literal call sites).
STAGE_PREFIXES = ["", "exposed_", "weathered_", "oxidized_"]

java_files = []
java_root = os.path.join(ROOT, "src/main/java")
for dirpath, _dirs, files in os.walk(java_root):
    for fn in files:
        if fn.endswith(".java"):
            java_files.append(os.path.join(dirpath, fn))


def first_arg(text: str, start: int) -> str:
    """Extract the first argument expression of a call whose '(' is at start."""
    depth = 0
    i = start
    while i < len(text):
        c = text[i]
        if c == "(":
            depth += 1
        elif c == ")":
            depth -= 1
            if depth == 0:
                return text[start + 1:i]
        elif c == "," and depth == 1:
            return text[start + 1:i]
        elif c == '"':
            i += 1
            while text[i] != '"':
                i += 2 if text[i] == "\\" else 1
        i += 1
    raise ValueError("unterminated call")


def eval_expr(expr: str, bindings: dict[str, str]) -> list[str] | None:
    """Evaluate a `"lit" + prefix + param` concatenation; None if unresolvable."""
    results = [""]
    for tok in (t.strip() for t in expr.split("+")):
        if tok.startswith('"') and tok.endswith('"'):
            results = [r + tok[1:-1] for r in results]
        elif tok == "prefix":
            results = [r + p for r in results for p in STAGE_PREFIXES]
        elif tok in bindings:
            results = [r + bindings[tok] for r in results]
        else:
            return None
    return results


registered = set()
block_ids_registered = set()
wrapper_templates = {}  # method name -> (ordered String params, [(is_block, expr)])

CALL_RE = re.compile(r"Mod(Items|Blocks)\.register\(")
METHOD_RE = re.compile(r"(?:static|private|public|protected)[\w<>,\[\]\s]*?\b(\w+)\(([^)]*)\)\s*\{")

for path in java_files:
    with open(path, encoding="utf-8") as f:
        text = f.read()
    for m in CALL_RE.finditer(text):
        is_block = m.group(1) == "Blocks"
        expr = first_arg(text, m.end() - 1)
        ids = eval_expr(expr, {})
        if ids is not None:
            registered.update(ids)
            if is_block:
                block_ids_registered.update(ids)
            continue
        # Unresolved identifiers: attribute to the enclosing wrapper method.
        encl = None
        for mm in METHOD_RE.finditer(text, 0, m.start()):
            encl = mm
        if encl is None:
            finding("f", f"cannot resolve register id expression {expr!r} in {os.path.basename(path)}")
            continue
        params = [
            p.split()[-1]
            for p in encl.group(2).split(",")
            if p.strip().startswith("String")
        ]
        wrapper_templates.setdefault(encl.group(1), (params, []))[1].append((is_block, expr))

# registerCubeFamily is a wrapper too, but its wall registration is conditional
# on the withWall argument, so it gets explicit handling.
wrapper_templates.pop("registerCubeFamily", None)
all_java = "\n".join(open(p, encoding="utf-8").read() for p in java_files)
for m in re.finditer(
    r'registerCubeFamily\(\s*"([a-z0-9_/]+)"\s*,\s*"([a-z0-9_/]+)"\s*,[^;]*?,\s*(true|false)\s*\)',
    all_java,
    re.S,
):
    name, stem, with_wall = m.groups()
    fam = [name, stem + "_slab", stem + "_stairs"] + ([stem + "_wall"] if with_wall == "true" else [])
    registered.update(fam)
    block_ids_registered.update(fam)

for wname, (params, exprs) in wrapper_templates.items():
    lit_group = r'"([a-z0-9_/]+)"\s*[,)]\s*' * len(params)
    callsites = list(re.finditer(r"\b" + re.escape(wname) + r"\(\s*" + lit_group, all_java))
    if not callsites:
        finding("f", f"wrapper {wname} registers computed ids but no literal call sites found")
    for cs in callsites:
        bindings = dict(zip(params, cs.groups()))
        for is_block, expr in exprs:
            ids = eval_expr(expr, bindings)
            if ids is None:
                finding("f", f"cannot resolve {expr!r} in wrapper {wname} with bindings {bindings}")
                continue
            registered.update(ids)
            if is_block:
                block_ids_registered.update(ids)

for rid in sorted(registered):
    if rid not in item_ids:
        finding("f", f"registered id {rid} has no items/{rid}.json")
print(f"[audit] (f) {len(registered)} registered ids ({len(block_ids_registered)} blocks) checked against items/")

# extra sanity: registrations vs blockstates both ways
for bid in blockstate_ids:
    if bid not in block_ids_registered:
        finding("f", f"blockstate {bid} exists but no ModBlocks/registerCubeFamily registration found")
for rid in sorted(block_ids_registered):
    if rid not in blockstate_ids:
        finding("f", f"registered block {rid} has no blockstates/{rid}.json")

# ---------- (g) sounds.json oggs exist & nonempty ----------
sounds_path = os.path.join(ASSETS, "sounds.json")
sounds = parsed.get(sounds_path, {})
ogg_checked = 0
for event, spec in sounds.items():
    for s in spec.get("sounds", []):
        name = s["name"] if isinstance(s, dict) else s
        ns, _, p = name.rpartition(":")
        if ns and ns != "copper_inferno":
            continue
        ogg = os.path.join(ASSETS, "sounds", p + ".ogg")
        ogg_checked += 1
        if not os.path.isfile(ogg):
            finding("g", f"sounds.json event {event} references missing ogg {p}.ogg")
        elif os.path.getsize(ogg) == 0:
            finding("g", f"sounds.json event {event} ogg {p}.ogg is EMPTY")
print(f"[audit] (g) {len(sounds)} sound events, {ogg_checked} oggs checked")

# ---------- (h) recipe result/ingredient ids exist in registration set ----------
recipe_dir = os.path.join(DATA, "recipe")
recipe_files = []
for dirpath, _dirs, files in os.walk(recipe_dir):
    for fn in files:
        if fn.endswith(".json"):
            recipe_files.append(os.path.join(dirpath, fn))


def collect_ci_ids(node, out):
    if isinstance(node, str):
        if node.startswith("copper_inferno:"):
            out.add(node.split(":", 1)[1])
    elif isinstance(node, dict):
        for v in node.values():
            collect_ci_ids(v, out)
    elif isinstance(node, list):
        for v in node:
            collect_ci_ids(v, out)


recipe_id_refs = 0
for path in sorted(recipe_files):
    data = parsed.get(path)
    if data is None:
        continue
    ids = set()
    collect_ci_ids(data, ids)
    recipe_id_refs += len(ids)
    for rid in sorted(ids):
        if rid not in registered:
            finding("h", f"recipe {os.path.relpath(path, DATA)} references unregistered id copper_inferno:{rid}")
print(f"[audit] (h) {len(recipe_files)} recipes, {recipe_id_refs} copper_inferno id refs checked")

# ---------- report ----------
print()
if findings:
    print(f"[audit] {len(findings)} FINDINGS:")
    for f in findings:
        print("  - " + f)
    sys.exit(1)
print("[audit] ZERO findings — all checks green")
