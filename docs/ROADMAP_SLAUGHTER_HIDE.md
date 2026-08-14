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

### Próximo paso concreto — Fase 2 (delegado a OpenCode)

1. Implementar el sistema genérico (`CarcassBlock`, `DrainedCarcassBlock`, `CarcassBlockEntity`, `CarcassBleedingHandler`, `CarcassCutupHandler`, `CarcassDefinition`) usando la vaca como único caso de prueba end-to-end (bloques `cow_carcass`/`drained_cow_carcass`/`cow_head`/`cow_head_mount`/`cow_skeleton` + ítems `cow_skin`, cortes de vaca).
2. Migrar assets/datos de la vaca desde `temp/butchery-assets/` a `src/main/resources/` con namespace `slaughter_hide:` (blockstates, models, textures, loot tables — reutilización tal cual, ya aprobada).
3. Registro vía `DeferredRegister` de bloques/ítems/block entities usando la tabla `CarcassDefinition`, no clases individuales.
4. Validar en dev run (`./gradlew.bat runClient`) el ciclo completo: matar vaca → carcasa aparece → sangrar → drenar → cortar cabeza → despellejar → 3 cortes de carne → bloque desaparece.
5. Una vez validado, extender `CarcassDefinition` al resto de mobs "animales simples" (sin cortes de humanoide) copiando sus JSON — trabajo mecánico, no de diseño.

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

## Fase 3 — Mecánicas núcleo (`procedures/` → lógica idiomática)

- Reescribir (no traducir literalmente) la lógica de negocio real: despiece de mobs, curtido/procesado de pieles, recetas de carnicero, efectos de consumo de carne.
- Priorizar por lo que el usuario confirme como "mecánica principal" vs "contenido de relleno" en Fase 0.

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
