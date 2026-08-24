# Roadmap — Slaughter & Hide (port de Butchery 5.2 → NeoForge 26.2)

> Documento vivo. Se actualiza según avanza el port. Ver `docs/WORKFLOW_SLAUGHTER_HIDE_26-2.md` para el flujo operativo del repo.

## Contexto y tamaño real del mod original

`butchery-5.2-neoforge-26.1.2.jar` está generado con [MCreator](https://mcreator.net/) (paquete `net.mcreator.butchery`). No hay fuente pública — solo el `.jar` compilado. Inventario extraído directamente del jar:

### Código (clases `.class`, sin contar internas `$`)

| Paquete | Clases | Qué es |
|---|---|---|
| `procedures/` | 798 | Lógica de "procedimientos" visuales de MCreator (bloques de código generado, muy verboso/no idiomático) |
| `block/` | 670 | Una clase por variante de bloque (carcasas, cabezas, mesas de carnicero, mostradores, alfombras...) |
| `item/` | 304 | Ítems (herramientas, pieles, cortes de carne, trofeos...) |
| `client/` | 133 | Renderers, pantallas, modelos de entidad |
| `init/` | 23 | Registro de bloques/ítems/etc. |
| `jei_recipes/` | 7 | Integración JEI |
| `world/`, `recipe/`, `network/`, `fluid/`, `potion/`, `entity/`, `configuration/`, `command/` | 21 | Resto |
| **Total** | **~1957** | |

### Assets (`assets/butchery/`)

| Tipo | Cantidad |
|---|---|
| `models/` | 3549 |
| `textures/` | 894 |
| `items/` (definiciones de item model 1.21+) | 792 |
| `blockstates/` | 456 |
| `patchouli_books/` | 120 (libro de documentación in-game vía mod Patchouli) |
| `sounds/` | 15 + `sounds.json` |
| `equipment/` | 12 |
| `particles/` | 7 |
| `lang/en_us.json` | 1 archivo, **82.7 KB** (miles de claves) |

### Datos (`data/butchery/`)

| Tipo | Cantidad |
|---|---|
| `loot_table/` | 976 |
| `recipe/` | 379 |
| `advancement/` | 238 |
| `tags/` | 142 |
| `villager_trade/` | 24 |
| `worldgen/`, `structure/`, `painting_variant/`, `loot_modifiers/`, `enchantment/` | 34 |

**Conclusión de tamaño**: esto no es un mod pequeño — es un content-pack grande, comparable en volumen a mods de referencia como Equivalent Legacy (~491 archivos Java) pero **4x más clases** y con generación MCreator (código no idiomático, muy repetitivo, con lógica de negocio embebida en `procedures/` en vez de en las clases de bloque/ítem). Un port 1:1 completo es un proyecto de varias semanas incluso delegando el grueso a OpenCode. Este roadmap está pensado en fases para poder liberar betas jugables incrementales en vez de bloquear todo hasta tener el 100%.

## Fase 0 — Viabilidad y decompilación (COMPLETADO 2026-08-15)

1. ✅ Decompilado el jar completo con CFR 0.152 (`%TEMP%\opencode\cfr.jar`, ya presente en el sistema de sesiones previas) a `temp/butchery-src/` (no versionado) — **1963 archivos `.java`**, 0 errores/excepciones en `temp/cfr-decompile.log`.
2. ✅ Assets y datos originales extraídos a `temp/butchery-assets/` (`assets/butchery/` + `data/butchery/` + `pack.mcmeta`, 8129 archivos) — se reutilizan tal cual (decisión confirmada, ver abajo).
3. ✅ Catálogo de bloques/ítems/procedures (series vs mecánicas únicas) — ver siguiente sección.
4. ✅ Alcance confirmado con el usuario: **subconjunto priorizado, betas incrementales** (no paridad 100% desde el inicio).

## Catálogo Fase 0 — series vs mecánicas únicas (COMPLETADO 2026-08-15)

Análisis de `temp/butchery-src/net/mcreator/butchery/` (fuente decompilada). Confirma la hipótesis: la inmensa mayoría del volumen es **repetición del mismo patrón × mob**, no lógica distinta. Esto es la base para decidir qué se porta a mano (una vez) y qué se genera por script/plantilla (N veces).

### `block/` (456 clases)

| Categoría | Cantidad aprox. | Patrón |
|---|---|---|
| `<Mob>Carcass` / `Drained<Mob>Carcass` | ~136 + variantes drenadas | Carcasa colgada/tirada por mob, con y sin sangre extraída |
| `<Mob>Head` / `<Mob>HeadMount` | ~76 + ~67 | Cabeza cortada y su versión montada en pared |
| `<Mob>Corpse` (humanoides: zombie, husk, piglin, witch, villager...) | ~27 | Variante "cadáver" para mobs humanoides en vez de carcasa animal |
| `<Mob>Skeleton` | ~24 | Esqueleto tras vaciar de carne |
| `<WoodType>Butcherstable` / `Butcherdisplay` / `Counter` | ~25 (×10 tipos de madera aprox.) | Mueble de carnicero, un set por madera vanilla |
| `Canopy<Color>` | serie por color de tinte | Toldo/carpa de puesto de carnicero |
| **Bloques mecánicos únicos (sin serie)** | **54** | Ver lista abajo — esto es lo que hay que diseñar/portar a mano, una vez cada uno |

Lista completa de los 54 bloques únicos (mecánica propia, no repetidos por mob/madera): `Basin, Blood, Bloodgrate, Bloodpuddle, Bloodsplatter, Bonebarrel, Brain, Butcherstatue, Cashregisterblock, Clingfilm, Codbarrel, Cookedbloodsausages, Cookedsausages, Deepslatesulfurore, Dioritebricks, DioriteBrickSlab, Dioritebrickstairs, DioriteBrickwall, Dragonscaleblock, Endermite, FloorstandingSign, Freezer, Hook, InfectedBlood, Irongolem(+arms/body/legs), Jar, Meatgrinder, Metaltray, Pestleandmortar, Photos, Plasticsheet(+corner), Pufferfish, Ravager, Rawbloodsausages, Rawsausages, Rope, Salmonbarrel, Saltblock, Saltformation(base/frustum/middle/tip), Sand, Skinrack, Spiketrap, Sulfurore, Taxidermytable, Woodenspitrotisserie`.

### `item/` (304 clases)

Mismo patrón: `<Mob>Skin`/`<Mob>Fur` (curtido), `Raw<Corte> de <Mob>` / `Cooked<Corte> de <Mob>` (con cortes específicos por tipo de mob — humanoides tienen "intestines/kidney/liver/lungs/stomach/heart", animales normales tienen cortes tipo "steak/chunk/mince"). El resto (herramientas: `Bonecleaver`, `Bonehacksaw`, `Bonehammer`, `Boneskinningknife`; vestimenta: `Butchersapron`, `Bloodybutchersapron`; economía: `Cashregister`, `Coin`, `Butcherspapers`; consumibles especiales: `Bottleofblood`, `Bottleofsulfuricacid`...) es contenido único, minoritario en cuenta pero es el que define la identidad jugable del mod.

### `procedures/` (798 clases)

| Patrón | Cantidad | Qué hace |
|---|---|---|
| `<Mob>CutUp` / `CutUp<Mob>` | 137 | Lógica de despiece: clic con herramienta sobre carcasa → genera drops de carne/piel/huesos según el mob |
| `<Mob>Carcassdrop` | 50 | Qué sueltan las carcasas al romperse sin despiece "limpio" |
| `<Mob>Carcassbleeding` | 36 | Tick de sangrado de la carcasa (mancha el suelo con `Blood`/`Bloodpuddle`) |
| `<Mob>Corpsedrop` | 10 | Equivalente a carcassdrop para cadáveres humanoides |
| `Placed<Mob>Carcass` / `Placed<Mob>Skeleton` / `Placed<Mob>Corpse` | ~60 | Lógica de spawn/colocación al morir el mob original |
| Resto (tick de muebles, recetas condicionales, JEI, red, comandos...) | ~305 | Lógica de soporte, no repetitiva |

**Conclusión operativa**: el "motor" real de Butchery son ~5-6 patrones (sangrado → despiece → drop → colocación de carcasa/cabeza/esqueleto) aplicados a ~60 tipos de mob distintos, más ~54 bloques y un puñado de ítems con mecánica propia (curtido, prensa de carne, salazón, taxidermia, caja registradora). Portar **un mob de referencia completo de punta a punta** (bloque carcasa + cabeza + head mount + esqueleto + ítems de piel/carne + las 4-5 procedures asociadas) fija el patrón; el resto de mobs se generan por plantilla a partir de ese patrón en vez de decompilar y traducir 700+ procedures a mano.

## Diseño técnico — sistema genérico de carcasas (COMPLETADO 2026-08-15, mob de referencia: vaca)

Lectura completa de `CowcarcassBlock`, `CowcarcassBlockEntity`, `CowcarcassbleedingProcedure`, `CowcutupProcedure`, `CowcarcassbrokenProcedure` en `temp/butchery-src/`. **Hallazgo clave**: la lógica de despiece es **idéntica para los ~120 mobs con carcasa** — lo único que cambia por mob es a qué loot table JSON se llama en cada etapa. Esto significa que el sistema entero de carcasas/cabezas/esqueletos se puede portar como **una única familia de clases genéricas parametrizadas por mob**, no como ~330 clases de bloque + ~293 procedures copiadas una a una.

### Mecánica observada (vaca, aplicable 1:1 al resto)

1. **Colocación**: al morir el mob, se coloca un `<mob>_carcass` (bloque colgante/tirado, blockstate `FACING` + `blockstate` entero, empieza en `blockstate=1`).
2. **Sangrado** (clic con cleaver sobre carcasa fresca, `blockstate==1`): marca NBT `isBleeding=true`, sonidos + partículas de sangre, rellena un `bloodgrate`/`bloodpuddle` cercano en un loop temporizado (22 × 45 ticks). Tras 900 ticks (45s) — o instantáneo si `INSTANT_BLEED` está activo en config — sustituye el bloque por `drained_<mob>_carcass` (mismo `blockstate` property pero solo acepta `{0,6,7,8,9}`; al no aceptar `1`, cae a `0` por defecto), preservando NBT del block entity. Marca `isBleeding=false`, `isDrained=true`.
3. **Despiece** (clic con cleaver/skinning knife sobre la carcasa **ya drenada**, requiere NBT `isDrained=true`):
   - `blockstate==0` + cleaver → tira loot table `<mob>_head_drop` → `blockstate=6`
   - `blockstate==6` + skinning knife → tira `<mob>_skin_drop` → `blockstate=7`
   - `blockstate==7` + cleaver → tira `<mob>_cut_1_drop` → `blockstate=8`
   - `blockstate==8` + cleaver → tira `<mob>_cut_2_drop` → `blockstate=9`
   - `blockstate==9` + cleaver → tira `<mob>_cut_3_drop` → bloque se limpia (`AIR`)
   - Cada golpe: daña 1 punto de durabilidad de la herramienta, sonido `item.axe.strip` + `block.honey_block.step`, partícula de rotura de bloque (`levelEvent 2001`).
   - Tags de herramienta usados (constantes en todo el mod, no varían por mob): `c:cleaver`/`forge:cleaver`, `c:skinning_knives`/`forge:skinning_knives`.
4. **Rotura no autorizada** (minar la carcasa en vez de despiezarla, sin modo creativo, solo en `blockstate` 0 o 1): dropea el propio bloque como ítem recolocable — antes de sangrar el bloque es "reubicable" como decoración, después de sangrar/despiezar ya no.
5. **`<mob>CarcassBlockEntity`**: es un contenedor genérico de 9 slots (`RandomizableContainerBlockEntity`) — vestigio de plantilla MCreator, no aporta lógica propia de la mecánica de despiece (el estado real vive en el `blockstate` property + NBT persistente `isBleeding`/`isDrained`).
6. **`<mob>_head`/`<mob>_head_mount`/`<mob>_skeleton`**: bloques de exhibición independientes, generados por el drop de `head_drop`/`cut_3_drop` respectivamente (colocables por el jugador) — sin mecánica propia más allá de blockstate `FACING`.

### Arquitectura idiomática propuesta (reemplaza ~330 clases de bloque + ~293 procedures)

| Original MCreator | Reemplazo idiomático |
|---|---|
| `<Mob>CarcassBlock` × ~65 | **1 clase** `CarcassBlock` (fresh) parametrizada por `CarcassDefinition` (record: mob id, bounding boxes si difieren, sonidos) |
| `Drained<Mob>CarcassBlock` × ~65 | **1 clase** `DrainedCarcassBlock`, misma idea |
| `<Mob>CarcassBlockEntity` × ~65 | **1 clase** `CarcassBlockEntity` genérica (guarda `mobId`, `isBleeding`, `isDrained` como datos tipados, no NBT suelto; sin inventario de 9 slots — no se usa) |
| `<Mob>carcassbleedingProcedure` × 36 | **1 handler** `CarcassBleedingHandler`, recibe `CarcassDefinition` |
| `<Mob>cutupProcedure` × 137 | **1 handler** `CarcassCutupHandler`, máquina de estados `{FRESH→DRAINED→HEAD_CUT→SKINNED→CUT_1→CUT_2→CUT_3(empty)}` genérica, resuelve el loot table por convención `<namespace>:blocks/<mob_id>_<stage>_drop` |
| `Placed<Mob>CarcassProcedure` × ~60 | **1 listener** de evento `LivingDeathEvent`, tabla `Map<EntityType, CarcassDefinition>` en vez de 60 clases |
| `<Mob>Head`/`HeadMount`/`Skeleton`Block × ~170 | **3 clases genéricas** (`TrophyHeadBlock`, `HeadMountBlock`, `SkeletonBlock`), un registro de datos por mob en vez de una clase java por mob |
| `data/butchery/loot_table/blocks/<mob>_*.json` (976 en total) | **Se reutilizan tal cual** (namespace `butchery:` → `slaughter_hide:`, decisión ya confirmada) — no hay que tocar su contenido, solo el namespace |
| `assets/butchery/{models,textures,blockstates}/` | **Se reutilizan tal cual** (misma decisión) |

Con esto, el trabajo real de "port" por mob se reduce a: **1 entrada en la tabla `CarcassDefinition`** (mob id + entity type + qué bloques tiene: carcasa/cabeza/mount/esqueleto/corpse) + copiar sus JSON de loot table/blockstate/modelo (mecánico, scripteable) — no escribir código Java nuevo por mob.

## Fase 2 — sistema genérico implementado (COMPLETADO 2026-08-15, mob de referencia: vaca)

Delegado a OpenCode (`opencode/deepseek-v4-flash-free`, OpenCode Zen). `./gradlew.bat build` pasa en verde y el jar `slaughter_hide-26.2-neoforge-26.2.0.45-beta-0.0.0-beta.1.jar` empaqueta clases + 116 entradas de assets/datos. Informe completo de la delegación en `temp/opencode-fase2-report.md` (no versionado).

**Ciclo de juego funcional end-to-end** (pendiente de smoke test manual en cliente — ver Fase 2.1): matar vaca → aparece `cow_carcass` colgante → cleaver → sangrado (900 ticks o instantáneo con `INSTANT_BLEED`) → `drained_cow_carcass` → cleaver (cabeza, `cow_head_drop`) → skinning knife (piel, `cow_skin_drop`) → cleaver ×3 (`cow_cut_1/2/3_drop`) → bloque desaparece. Máquina de estados genérica (`CarcassCutupHandler`, 110 líneas) reemplaza exactamente el comportamiento de las 1209 líneas decompiladas de `CowcutupProcedure` — verificado línea a línea contra el original antes de delegar.

**Clases nuevas** (paquete `com.skd.slaughterhide`): `CarcassDefinition`, `Carcasses` (tabla de definiciones — solo vaca poblada), `CarcassBlockProperty`; `block/{CarcassBlock,DrainedCarcassBlock,TrophyHeadBlock,HeadMountBlock,SkeletonBlock}`; `block/entity/CarcassBlockEntity`; `item/ButcherToolItem`; `handler/{CarcassDeathHandler,CarcassInteractionHandler,CarcassBleedingHandler,CarcassCutupHandler,CarcassLoot}`; `ServerWorkScheduler`; `config/SlaughterHideConfig`; `init/{ModBlocks,ModItems,ModBlockEntities,ModCreativeTabs}`; `tag/ModItemTags`.

**Desviaciones deliberadas respecto al original** (documentadas, no eliminan nada sin permiso):
- La muerte de la vaca coloca directamente el bloque colgante listo para despiezar, en vez del original (item + cuerda/gancho manual, estados 1-5 de `PlacecowcarcassProcedure`) — el sistema de cuerda/gancho queda pendiente para una fase posterior.
- El inventario vestigial de 9 slots (`RandomizableContainerBlockEntity`) del `CowcarcassBlockEntity` original **no se ha portado** — confirmado que ninguna lógica de despiece/sangrado lo usa, es plantilla MCreator sin función real.
- Tags de herramienta propios (`slaughter_hide:cleaver`/`slaughter_hide:skinning_knives`) en vez de `forge:`/`c:` — decisión tomada en el brief de delegación.
- Solo tier Iron de cleaver/skinning knife portado (el original tiene 6 tiers: bone/copper/iron/gold/diamond/netherite) — el resto queda para cuando se generalice a más mobs.

**Pendiente (fuera de alcance de esta fase, confirmado en el informe de OpenCode)**: sangre/rejilla/charco de sangre, colocación de `cow_head_mount`/`cow_skeleton` por el jugador, recetas de cocinado de los cortes de vaca.

## Fase 2.2 — Restricción de colocación con Hook (COMPLETADO 2026-08-18, implementado directamente por Claude)

El usuario probó la beta.3 y pidió restringir la colocación de la carcasa "tal cual el mod original" (en vez del bloque libre en cualquier superficie de la Fase 2). Dos intentos de delegar esto en OpenCode (`opencode/deepseek-v4-flash-free`) se colgaron sin producir ninguna salida durante horas (ver memoria `opencode_run_hangs_zero_output`) — tercer cuelgue de este tipo en el histórico del proyecto. Se abandonó la delegación para esta tarea concreta y se implementó directamente, ya que el mecanismo original (`PlacecowcarcassProcedure`, 654 líneas decompiladas) ya se había leído por completo.

**Implementado**: nuevo bloque `Hook` (`block/HookBlock.java`) — colocable libremente como cualquier bloque normal, pero **las carcasas ya NO se pueden colocar en ningún sitio**: `cow_carcass`/`drained_cow_carcass` pasaron de `BlockItem` normal a `CarcassPlacementItem`, cuyo único punto de colocación válido es clic derecho sobre un `Hook` (`handler/HookPlacementHandler.java`). Al colgarla: coloca el bloque de carcasa correcto un bloque por debajo del Hook, con la misma orientación, sonido `block.chain.hit`, y consume 1 ítem del inventario (salvo creativo).

**Simplificación deliberada y documentada**: el original también permite colgar de un bloque `Rope` tras "tensarlo" con varios clics (propiedad `blockstate` 0-7). Se decompiló y leyó `RopeBlock.java` completo, pero el flujo de control exacto de `PlacecowcarcassProcedure` para ese caso (si tensar la cuerda es un requisito previo o ocurre en el mismo clic que la colocación) no se pudo reconstruir con confianza suficiente desde el bytecode decompilado — los bloques `break`/fallthrough de CFR son ambiguos en ese tramo. En vez de arriesgar una reimplementación incorrecta, **`Rope` no se ha portado en esta fase**; solo `Hook` es funcional. Pendiente para una fase futura si se decide que merece la pena.

Verificado con `./gradlew.bat build` (verde) y arranque de cliente (`runClient`): 0 errores, 0 warnings de `hook` en el log.

### Fase 2.1 — Verificación de arranque (COMPLETADO 2026-08-15)

`./gradlew.bat runClient` ejecutado dos veces:
1. **1er intento**: crash de arranque — `ModBlockEntities.CARCASS` resolvía `ModBlocks.carcassBlocks()` (llamadas a `DeferredBlock::get`) en un inicializador de campo estático, antes de que el `RegisterEvent` de bloques hubiera corrido → `NullPointerException: Trying to access unbound value`. **Corregido**: la resolución se movió dentro del supplier lambda de `REGISTRY.register(...)`, que sí se ejecuta después del registro de bloques ([ModBlockEntities.java](../src/main/java/com/skd/slaughterhide/init/ModBlockEntities.java)).
2. **2º intento**: arranca sin errores fatales (0 `ERROR`, carga sonido/texturas/recursos de `mod/slaughter_hide`). Dos avisos no fatales, conocidos y documentados aquí (no bloquean el release beta):
   - **`cow_head_mount` no compila su modelo** (`IllegalArgumentException: Cannot compute translucency out of bounds`) — bug heredado del asset **original** de Butchery (JSON idéntico byte a byte, verificado con diff), expuesto porque el bakeador de modelos de MC 26.2 valida límites UV más estrictamente que 26.1.2. Solo afecta al renderizado de ese bloque decorativo (trofeo de cabeza montada); no afecta al ciclo de despiece. Pendiente de arreglo en una fase posterior (recalcular/recortar el UV de la cara "south" del elemento `head`).
   - **Avisos "Missing model for variant" para `drained_cow_carcass` en blockstate 1-5** — cosmético: el `IntegerProperty` Java declara el rango completo 0-9 (igual que el original) pero el blockstate JSON solo define variantes para {0,6,7,8,9}, que son los únicos valores que la máquina de estados llega a asignar. No afecta al juego, solo ruido de log.

Aún pendiente: prueba manual jugada (matar vaca → sangrar → despiezar) — el usuario la hará directamente tras la subida a CurseForge.

### Fase 2 — COMPLETADA (2026-08-18)

La vaca de referencia está validada de punta a punta en cliente real por el usuario: matar → colgar del `Hook` → sangrar → drenar → despiezar en 5 pasos → bloque desaparece. Confirmado funcionando correctamente (2026-08-18).

## Fase 1 — Setup del repositorio (COMPLETADO esta sesión)

- Estructura `slaughter_hide/neoforge/26.2/` según convención del workspace.
- `build.gradle`, `gradle.properties` (NeoForge `26.2.0.45-beta`), `settings.gradle`, plantilla `neoforge.mods.toml`.
- Clase principal `SlaughterHide` (paquete `com.skd.slaughterhide`).
- `docs/WORKFLOW_SLAUGHTER_HIDE_26-2.md`, este roadmap, `docs/curseforge/` (stubs, proyecto aún no creado en CurseForge).
- README/CHANGELOG con atribución a Jmods (permiso expreso, licencia original "All Rights Reserved").
- **Pendiente**: `git init`, primer commit, creación manual del repo GitLab + ramas `production`/`main` (`codex-docs/reference/REPO_SETUP.md`, on-demand solo aquí).

## Fase 2 — Núcleo de registro (bloques/ítems base, sin lógica)

- Sistema de registro idiomático NeoForge (`DeferredRegister`) para bloques/ítems, reemplazando el patrón MCreator de una clase gigante por elemento.
- Migrar primero las familias más simples y de mayor impacto visual (mesas/mostradores de carnicero, carcasas básicas) para tener algo renderizable pronto.
- Candidato fuerte para delegar en OpenCode (tarea mecánica de código NeoForge sustancial, caso 1 de la política de delegación) una vez el catálogo de Fase 0 esté listo.

## Fase 3.1 — Cerdo (COMPLETADO 2026-08-18, delegado a OpenCode)

Segundo mob añadido, siguiendo el patrón genérico sin ninguna clase Java nueva por mob (delegación mecánica exitosa, en contraste con el intento fallido de Hook/Rope — el cerdo es estructuralmente idéntico a la vaca, sin la ambigüedad del bytecode decompilado que causó los cuelgues). Informe completo en `temp/opencode-pig-report.md` (no versionado).

- 81 archivos de assets/datos migrados (namespace `butchery:` → `slaughter_hide:`), 8 archivos de código modificados, 0 clases Java nuevas.
- **Generalización real del sistema**: `CarcassDefinition` ganó un campo `sweptVanillaItems` (lista) — la vaca barre `[beef, leather]`, el cerdo solo `[porkchop]`, cada uno según su `PlacecowcarcassProcedure`/`PlacepigcarcassProcedure` original. `CarcassDeathHandler` pasó de hardcodear beef/leather a leer esto de la definición — sigue siendo 100% genérico.
- **Mismo bug de UV que `cow_head_mount`** encontrado y corregido en `pig_head_mount.json` (mezcla de unidades rejilla 0-16 vs píxeles) — mismo diagnóstico, mismo arreglo, aplicado proactivamente sin que Claude lo pidiera.
- **Corrección post-delegación**: `DrainedCarcassBlock.onDestroyedByPlayer` dropeaba `Items.BEEF` fijo al romper una carcasa a medio cortar, incluso para cerdo — el propio informe de OpenCode lo señaló como "fuera de alcance pero recomendable". Corregido para usar `definition.sweptVanillaItems().get(0)`.
- `./gradlew.bat build` verde. **Sin probar en cliente todavía** (recomendado por el propio informe: colocar en Hook, romper a medio cortar, verificar cabeza montada en tablón de roble oscuro, barrido de `porkchop` al morir).

## Fase 3.2 — Oveja (COMPLETADO 2026-08-18, delegado a OpenCode)

Tercer mob, mismo patrón mecánico exitoso que el cerdo (0 clases Java nuevas). Informe completo en `temp/opencode-sheep-report.md`.

- `sweptVanillaItems = [Items.MUTTON]`. Reutiliza las formas de colisión del cerdo para carcasa/esqueleto (cajas idénticas verificadas), con formas propias para `sheep_head`/`sheep_head_mount`.
- Mismo bug de UV (0-16 vs píxeles) encontrado en `sheep_head_mount.json` — esta vez corregido comparando contra el propio `sheep_head.json` en vez de copiar los números del cerdo (la cara no coincidía exactamente), buen criterio de la delegación.
- Ítems de carne con nomenclatura "lamb", no "sheep" (`raw_leg_of_lamb`, `raw_lamb_shoulder`, `raw_lamb_rib`, `raw_lamb_sirloin`, `raw_lamb_loin`) — verificado contra las loot tables reales, no asumido.
- `./gradlew.bat build` verde. Sin probar en cliente todavía.

## Fase 3.3 — Extensión masiva a 74 mobs + huecos parciales (COMPLETADO, sin fecha exacta reconstruible — auditado 2026-08-24)

**Nota de proceso**: este trabajo se hizo en sesiones anteriores cuyo detalle día a día no quedó documentado aquí ni en `CHANGELOG.md` (el repo git local solo conserva 3 commits recientes, ver known issues del changelog beta.30). Esta sección se reconstruyó auditando el código real el 2026-08-24, no un log de trabajo.

Con el patrón genérico validado (`CarcassDefinition` + `Hook`), el mod pasó de 3 a **74 mobs funcionales**:
- **51 animales simples** (mismo patrón que la vaca).
- **10 humanoides** (zombie, esqueleto, ahogado, husk, vindicator, evoker, bruja, piglin, piglin brute, ravager) — usan bloque `Corpse`/`Skeleton` en vez de `carcass`/`skeleton` genérico, con cortes tipo "intestines/kidney/liver/lungs/stomach/heart".
- **4 mobs con tratamiento especial** (enderman 8 cortes, strider 3, sniffer 3+piel, tortuga 7).
- **11 variantes de pelaje de gato** (comparten `EntityType` con ocelote, registradas solo en `BY_MOB_ID`).

De los huecos listados en la Fase 3 original:
- ✅ **Recetas de cocinado**: 246 recetas smelting/smoking/campfire para todos los cortes portados.
- ✅ **Colocación de head_mount/skeleton**: funcional vía `BlockItem` vanilla estándar (sin lógica custom, pero funciona).
- 🟡 **`Rope`**: implementado (`RopeBlock` + `RopePlacementHandler`), pero es una **versión simplificada de 1 clic** — no reproduce el ciclo de "tensar la cuerda" (blockstate 0-7) del original. Sigue sin decidirse si merece la pena reintentar la mecánica exacta.
- 🟡 **Tiers de herramienta**: las 6 tiers (iron/copper/gold/diamond/netherite/bone) × 4 herramientas están registradas y con modelo, pero **las texturas de copper/gold/diamond/netherite/bone son placeholders** (copia byte a byte de la textura iron) — falta arte propio por tier.
- ❌ **Sangre visible**: sigue sin implementar. `CarcassBleedingHandler` solo genera partículas de humo; no hay bloques `Blood`/`Bloodgrate`/`Bloodpuddle` (el asset `blood.json` existe pero está huérfano).

**Bugs encontrados y corregidos en la auditoría del 2026-08-24** (ver `CHANGELOG.md` beta.30): `MEDIUM_MAGMA_CUBE`/`SMALL_MAGMA_CUBE` definidos pero nunca registrados (código muerto), texturas `iron_hacksaw.png`/`iron_hammer.png` inexistentes.

**Pendiente sin resolver, no corregido por respetar la regla de no-borrado**: ~8 recetas de crafteo huérfanas del MCreator original apuntan a bloques nunca portados (`basinrecipe.json`, `bloodgraterecipe.json`, `butcherstatuerecipe.json`, `cashregisterrecipe.json`, `freezercrafting.json`, `meatgrinderrecipe.json`, `skinrackrecipe.json`, `spiketraprecipe.json`) — decidir si se eliminan o se implementan los bloques que faltan.

## Fase 3.4 — Pendiente real (SIGUIENTE)

Con la cobertura de mobs prácticamente agotada del catálogo de animales/humanoides comunes, lo que queda de la Fase 3 original es:

1. **Cerrar huecos parciales**: mecánica exacta de `Rope` (tensado progresivo) si se decide reintentar, sangre visible (`Blood`/`Bloodpuddle`/`Bloodgrate`), texturas propias por tier de herramienta (actualmente placeholders).
2. **Decidir sobre las recetas huérfanas** del MCreator original (implementar los bloques que faltan o eliminar las recetas — requiere confirmación del usuario para borrar).
3. **Los 54 bloques mecánicos únicos** del catálogo original (mesa de despiece, prensa de carne, salazón, taxidermia, caja registradora, trampa de pinchos...) — contenido nuevo, no repetición del patrón de carcasa. Ninguno está implementado todavía (0/54).
4. Mobs restantes del catálogo original no cubiertos por las 74 definiciones actuales (revisar contra el inventario completo de Fase 0 si se quiere paridad total con Butchery).

## Fase 4 — Cliente (renderers, pantallas, modelos de entidad)

- Puerto de `client/` (133 clases) — renderers de bloques especiales, pantallas de mesas/mostradores.
- Verificar compatibilidad de formato de modelo/blockstate 26.1.2 → 26.2 (posibles breaking changes de render pipeline entre versiones).

## Fase 5 — Datos (recipes, loot tables, tags, advancements)

- Migración de los ~1750 archivos de `data/butchery/` — en su mayoría transformación mecánica de JSON (namespace `butchery` → `slaughter_hide`, referencias de ítems/bloques actualizadas), buen candidato a script + revisión, no copia manual.
- Validar formato de loot table / recipe contra 26.2 (posibles cambios de schema).

## Fase 6 — Integraciones opcionales

- JEI (declarado opcional en el original) — confirmar versión compatible con NeoForge `26.2.0.45-beta` antes de portar `jei_recipes/`.
- Patchouli (libro de documentación in-game, 120 páginas) — confirmar si Patchouli tiene build para 26.2 antes de comprometerse a portar esta parte; si no, queda pendiente o se sustituye por un formato propio.

## Fase 7 — Arte propio y limpieza final

- Sustituir cualquier asset que no se pueda reutilizar 1:1 (regla "sin residuos del original" del WORKFLOW) — a confirmar cuánto del arte original es reutilizable dado el permiso expreso del autor (a diferencia de los ports MIT del workspace, aquí SÍ hay permiso para reutilizar assets, pendiente de que el usuario lo confirme explícitamente para el arte, no solo para el código).
- Icono del mod, `project_description.md` final, capturas para CurseForge.

## Fase 8 — QA y release

- Build limpio, smoke test en cliente/servidor dev.
- Alta manual en CurseForge (`docs/curseforge/project_vars.md`), primera subida manual, siguientes vía `curseforge-upload.ps1`.
- Tag `26.2-neoforge-beta.1` en adelante.

---

## Decisiones confirmadas por el usuario (2026-08-15)

1. **Alcance**: **subconjunto priorizado, betas incrementales**. Se empieza por las mecánicas núcleo (carnicería/curtido) y las familias de bloques más visibles, publicando betas jugables antes de llegar a paridad completa. No bloquear el primer release a tener el 100% del contenido original.
2. **Reutilización de assets**: **SÍ** — dado el permiso expreso del autor, texturas/modelos/sonidos/lang de Butchery se reutilizan tal cual (con atribución en README/CurseForge/`credits`), sin rehacer arte propio. Esto reduce muchísimo el esfuerzo de las Fases 4-5 frente a los demás ports del workspace (que sí exigen arte propio por ser forks MIT sin ese permiso).
3. **Decompilador**: se reutiliza el CFR 0.152 ya presente en `%TEMP%\opencode\cfr.jar` (mismo binario usado en sesiones previas para otros mods compilados del workspace — `dinamyc_combat`, `teleport_animation`) en vez de descargar una copia nueva. Salida en `temp/butchery-src/` (no versionado).
4. **Modelo de OpenCode**: pendiente de preguntar al usuario en la primera delegación real (Fase 2 en adelante), según el orden de proveedores de `codex-docs/reference/CLAUDE.md`.
