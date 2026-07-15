# KUPFERBIENEN

A standalone Minecraft **Fabric** mod (author: Sonic0810, mod id `kupferbienen`) for **Minecraft 1.21.9**, **Yarn mappings** (`1.21.9+build.1`), Java 21. Copper bees make copper farmable: two `BeeEntity` subclasses (Kupferbiene, Gruenspanbiene), the Kupferbluete flower, the Kupferstock apiary block (produces Kupferwabe / Gruenspanpollen into a hopper-drainable inventory), and an oxidation brewing chain (Kupfersud -> Trank/Wurfphiole der Oxidation/Entoxidation) whose splash vials weather/scrape copper blocks and convert bees between the two species.

## Build / run

Java 21 is required; set `JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64` if the default differs.

- Build: `./gradlew build --no-daemon` (produces `build/libs/kupferbienen-1.0.0.jar`).
- Dedicated server: `echo "eula=true" > run/eula.txt` once, then `./gradlew runServer --no-daemon`. Type commands directly into the console (run it inside tmux to send keys).
- Headless client (software GL): `DISPLAY=:1 LIBGL_ALWAYS_SOFTWARE=1 GALLIUM_DRIVER=llvmpipe MESA_GL_VERSION_OVERRIDE=4.6 ./gradlew runClient --no-daemon`. Reaches the title screen in ~2 min, world load is several more minutes (llvmpipe is SLOW). `Failed to open OpenAL device` / ALSA errors and the Realms `SignedJWT: FabricMC` line are harmless (no sound card / offline dev auth).
- **Only ONE Gradle `run*` task can run at a time** — a second one fails with `SessionLock$AlreadyLockedException` on `run/world`. Stop the server before starting the client (and vice versa).

## Non-obvious findings (verified in-game on 1.21.9)

- **Console-only automation tests need `pause-when-empty-seconds=-1`** in `run/server.properties` (already set). With the default (60), an empty dedicated server pauses ticking, so block entities / projectiles / bees freeze and scripted tests silently hang.
- **Thrown vial NBT key is `Item`** (from vanilla `ThrownItemEntity`):
  `/summon kupferbienen:geworfene_phiole X Y Z {Item:{id:"kupferbienen:wurfphiole_oxidation",count:1},Motion:[0.0,-1.5,0.0]}` is the reliable way to test impacts from the console (in-game right-click throwing via synthetic X11 clicks is unreliable headless — xdotool clicks work on GUI screens/menus but in-world "use item" clicks are not delivered; use `/summon` instead).
- **Oxidizable method names (Yarn 1.21.9)**: advance one stage via the instance default `Oxidizable#getDegradationResult(BlockState)` (`Optional<BlockState>`); scrape via the static `Oxidizable.getDecreasedOxidationState(BlockState)`. Waxed copper is not `Oxidizable`, so the oxidation vial correctly skips it.
- **Bee/apiary radius mechanics** (`KupferstockBlockEntity`): every 20 ticks the Kupferstock counts living Kupfer-/Gruenspanbienen within **4 blocks** and requires >=1 Kupferbluete within **4 blocks**; progress += `20 * min(bees, 5)` toward a 600-unit product (so 2 bees ≈ 15 s per item; hold when the 3-slot inventory is full; 20% Gruenspanpollen chance once a Gruenspanbiene contributed). Summoned bees wander — in tests, re-`/tp` them next to the apiary every ~30 s or summon them with `{NoAI:1b}`.
- Hoppers can only extract (never insert) — `SidedInventory.canInsert` is always false. Empty-hand right-click ejects one stored stack.
- Splash vial blast: 5x5x5 block cube around impact, entity effects + bee conversion within 2.5 blocks of the impact point (oxidation converts Kupferbiene -> Gruenspanbiene, cleansing the reverse).
- Kupferbluete is injected into `#minecraft:bee_attractive`, `#minecraft:flowers` (blocks) and `#minecraft:bee_food` (item) via `data/minecraft/tags/` files — vanilla bee AI then pollinates it with no custom goals.
- Startup marker to grep for: `[KUPFERBIENEN] Registered oxidation brewing chain (5 item recipes)`. Recipe check: the server should log `Loaded 1467 recipes` (vanilla 1461 + this mod's 6 crafting recipes).
- Code layout: split source sets — common in `src/main/java`, client-only in `src/client/java` (entity textures in `src/client/resources`). Init order matters: `ModCreativeTab.init()` MUST precede feature inits (features hook `ItemGroupEvents` on its key), and `BeesFeature.init()` MUST precede `PotionsFeature.init()` (brewing chain references bee items).
- Lang: `en_us.json` and `de_de.json` must keep IDENTICAL key sets (block items use `block.kupferbienen.<id>` keys via `useBlockPrefixedTranslationKey()`).
