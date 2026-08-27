# Changelog — Slaughter & Hide

## [0.0.0-beta.37] - 2026-08-27

### Fix

- **Cabeza de Oso Polar Montada sin textura** — el bloque `polar_bear_head_mount` **nunca se registraba**: `polar_bear` faltaba en el `Set` `HAS_HEAD_MOUNT_ASSETS` de `ModBlocks.java`, así que `mountFor("polar_bear")` devolvía `null` y el `blockItem` quedaba sin bloque. Añadido `polar_bear` al set. Además el modelo custom `polarbear_head_mount.json` tenía 4 UVs fuera de rango (se re-añadió el oso en beta.36, después del clampeo masivo de UVs de beta.35) → reemplazado por la versión ya clampeada. Loot tables `polar_bear_head_mount`/`polar_bear_skeleton` apuntaban a los ítems viejos sin guion bajo (`polarbear_*`, borrados) → corregidas.
- **Oso polar: texturas y modelos que faltaban tras re-añadirlo en beta.36** (rename `polarbear` → `polar_bear` a medias): `polar_bear_head_mount`/`polar_bear_skeleton` sin `items/<id>.json` con el nombre nuevo (creadas); `drained_polar_bear_carcass` en magenta porque `drained_polarbear(_hanging).png` no se copiaron del dump (copiadas).
- **Piel de cabra sin textura**: `models/item/goat_skin.json` referencia `slaughter_hide:item/goat_fur`, textura no copiada del original (solo estaba `goat_skin.png`, sin usar). Copiado `goat_fur.png`.

### Add

- **Traducciones del oso polar** en `en_us` + `es_es` (`polar_bear_carcass`, `drained_polar_bear_carcass`, `polar_bear_head`, `polar_bear_head_mount`, `polar_bear_skeleton`, `polar_bear_skin`, bloque+item). Faltaban todas menos `raw_polar_bear_meat` (el oso se re-añadió tras el sync de traducciones de beta.35). `raw_polar_bear_meat` en `es_es` traducido ("Carne de Oso Polar Cruda").
- **Integración del ciclo completo matar→colgar→sangrar→drenar→cortar para los 7 mobs** (delegado a OpenCode `mimo-v2.5`, verificado por Claude):
  - `cooked_polar_bear_meat` registrado (faltaba pese a existir el `raw_`): ítem + modelo + item-def + textura `cooked_bear_meat.png` (del dump) + 3 recetas (smelting 275t / smoking 137t / campfire 275t, grupo `polar_bear`) + lang en/es + tab creativa (raw + cooked).
  - `cooked_chicken_leg` / `cooked_chicken_wing` registrados (el original de Butchery sí los tiene y no se habían portado): ídem, 6 recetas (150/75/150t, grupo `chicken`), texturas md5-idénticas al dump. Conejo: el original no tiene cocinados, no se añade.
  - `drained_goat_carcass` / `drained_polar_bear_carcass`: loot tables de rotura de bloque que faltaban (stub sin pools, igual que las de los otros 5 mobs y que el dump).
  - `Carcasses.buildPOLAR_BEAR()`: `sweptVanillaItems` `[COD]` → `[COD, SALMON]` (el oso polar vanilla suelta ambos) + comentario corregido.
  - Verificado contra la fuente decompilada que pig/sheep/chicken/rabbit/goat tienen `CarcassDefinition`, loot tables, sets de assets y blockstates fieles al original — sin discrepancias.

### Removed

- **6 ítems `*_goat` fantasma** (`raw_leg_of_lamb_goat`, `raw_lamb_shoulder_goat`, `raw_lamb_rib_goat`, `raw_lamb_sirloin_goat`, `raw_lamb_loin_goat`, `hoof_goat`) — no existen en Butchery original, nada los usa (la cabra comparte los cortes de cordero y el `hoof` genérico). Eliminadas declaraciones en `ModItems.java`, refs en `ModCreativeTabs.java`, los 6 JSON stub y sus claves de idioma. `goat_skin` se mantiene.
- **Limpieza masiva de residuo del recorte 77→7 mobs** (mark-and-sweep contra los IDs realmente registrados): **1822 archivos** de `assets/`+`data/` — blockstates 174→70, models/block 638→198, models/item 346→159, models/custom 552→168, textures/block 222→95, textures/item 122→91, loot_table/blocks 377→109, recipes 376→96. Sanity check: toda blockstate/itemdef de bloque o ítem registrado se conserva. Incluye la feature "rug" muerta completa (`polarbear_rug`, `goat_rug`, `panda_rug` + sus recetas) y el residuo del rename `polarbear`.
- **22 claves de idioma huérfanas** (`en_us` + `es_es`) de bloques/ítems ya no registrados (`iron_golem_head_mount`, `ravager_head(_mount)`, `bone_*` tools; `cooked_polar_bear_meat` se elimina aquí y se vuelve a añadir en la sección Add al registrar el ítem).
- **3 clases Java muertas**: `IronGolemHeadMountBlock`, `RavagerHeadBlock`, `RavagerHeadMountBlock` (solo importadas en `ModBlocks.java`, nunca registradas) + sus imports.
- **28 ítems de carne/órganos de mobs fuera de alcance** que seguían registrados en `ModItems.java` sin ningún camino de juego para los 7 mobs (0 referencias en loot tables / recetas / handlers / tags): 16 `raw_<mob>_meat/steak/chunk/leg` (bat, camel, creeper×2, dolphin, enderman, endermite, fox, hoglin, ocelot, panda, pufferfish, silverfish, sniffer, strider, wolf) + 12 órganos (`heart`, `intestines`, `kidney`, `liver`, `lungs`, `stomach` + `rotten_*`). Eliminados: registros en `ModItems.java`, 54 `items/`+`models/item/` JSON, 26 texturas, 56 claves de idioma y `endermanheartrecipe.json`. `bird_foot`/`wishbone` se mantienen (los dropea el pollo). El subsistema `Corpse*` queda como código durmiente (bloques nunca registrados, `CorpseInteractionHandler` inalcanzable) — pendiente de decisión aparte.
- **7 archivos de scripts de migración one-shot** commiteados por error en sesiones antiguas: `add_corpse_builders.py`, `generate_carcasses.py`, `port_assets.py`, `carcasses_new.txt`, `update_carcasses2.py` / `update_carcasses_final.py` (estos 2 dentro de `src/main/java/`, no son código compilado), `__pycache__/*.pyc`. `.gitignore` ahora excluye `__pycache__/` y `*.pyc`.

### Docs

- `ROADMAP_SLAUGHTER_HIDE.md` actualizado al alcance real de **7 mobs** (nueva sección "ALCANCE ACTUAL"); las fases 3.3+ quedan marcadas como registro histórico. Documentada la respuesta "qué bloques del mod base faltan" (Meat Grinder / Pestle and Mortar / Taxidermy Table con GUI; sistema de sangre; mobiliario decorativo).

## [0.0.0-beta.36] - 2026-08-26

### Changed (BREAKING)

- **Scope cut from 13 to 7 mobs** — removed horse family (horse, donkey, mule) and 4 llama variants (brown/white/creamy/gray). Kept: cow, pig, sheep, chicken, rabbit, goat + polar bear (only non-farm kept per request). Orphaned carcass definitions, blocks, items (carcass/head/mount/skeleton), skin mappings, FOOD tab RAW steaks and 60 lang keys removed with them. Skin set is now 5 (cow/pig/sheep/goat/polar bear — chicken/rabbit produce meat directly).

### Fix

- **FOOD tab crash fixed** for 7-mob scope: removed `RAW_DONKEY_STEAK`/`RAW_MULE_STEAK` references that no longer exist in `ModItems` after the horse-family cut (`cannot find symbol` at `ModCreativeTabs.java:89`).
- **Carcasses.java 7-mob stabilization**: polar bear `buildPOLAR_BEAR()` + 6 voxel shapes correctly restored without duplicating dead-code shapes left over from the 77-mob dump at `a53a769`; static `register()` block now lists the 7 in consistent order. Previous attempts had `buildPOLAR_BEAR` duplicated and 12 polar shapes.
- **Lang sync**: `en_us.json`/`es_es.json` 300 → 240 keys (60 horse/llama keys removed).

## [0.0.0-beta.35] - 2026-08-26

### Changed (BREAKING)

- **Scope reduced to farm animals only (13 mobs)** — removed ~64 carcass families for hostile/strange/nether/end mobs (10 humanoid corpses with organ system, 11 cats, 5 axolotls, and 40+ other wild/hostile mobs). Kept only: cow, pig, sheep, chicken, rabbit, goat, horse, donkey, mule, and 4 llama variants. Orphaned item definitions, blockstate registrations and creative tab entries removed with them.

### Fix

- **6 legacy item models** that used pre-1.21.4 `parent` format directly in `items/` (bird_foot, raw_chicken_leg/wing, wishbone, silverfish/endermite chunks) now correctly split into `models/item/` geometry + `items/` definition — they were showing as missing textures despite having assets.
- **154 missing translation entries** added for remaining farm items (carcass/head/skeleton/skin variants etc.) — previously showed raw keys like `item.slaughter_hide.goat_carcass`.
- **681 out-of-bounds UVs** clamped across 95 custom models (closed freezer, every skin_rack variant, all head mounts) — MC 26.2 now bakes strictly and these had thrown `Cannot compute translucency out of bounds`, causing whole models to fail.

## [0.0.0-beta.34] - 2026-08-26

### Fix

- **Cientos de ítems sin textura en el creativo arreglados**: MC 26.2 exige una definición de modelo de ítem en `items/<id>.json` para renderizar cualquier ítem — tener solo `models/item/<id>.json` (formato pre-1.21.4) ya no basta y el juego lo ignoraba en silencio. Generadas las 308 definiciones que faltaban: 288 portadas del asset dump original de Butchery con remap de namespace, y 20 vía mapa de alias para ítems cuyo nombre difiere entre mods (`*_cat_skin`→`*_cat_fur`, `bshorthair_cat_*`→`british_shorthair_*`/`shorthair_cat_*`, caballo genérico→variante `regular_chestnut`, skeletons de axolotl de color→`axolotl_skeleton` compartido, `creeper_head`→modelo vanilla).
- **58 ítems sin assets ni en el original** quedan con icono de barrera como placeholder visible hasta decidir su contenido: mayormente `drained_*` de peces/slimes/magma cubes que nunca existieron como ítem en Butchery, los 5 skins de axolotl, cortes de cabra (`raw_lamb_*_goat`, `hoof_goat`) y los corpse items de ravager.
- **Crash del bake de modelos con UVs fuera de rango** (herencia MCreator): MC 26.2 hornea los modelos estrictamente y lanzaba `Cannot compute translucency out of bounds` en vez de tolerar coordenadas fuera de la textura como versiones anteriores — afectaba al Freezer cerrado, toda la familia `skin_rack` y todos los head mounts (mostraban modelo ausente en mano/inventario). Clampeadas 681 componentes UV en 95 modelos custom.

## [0.0.0-beta.33] - 2026-08-26

### Add

- **Mecanismo de corte real para los 9 mobs humanoides con corpse** (`drowned`, `evoker`, `husk`, `piglin`, `piglin_brute`, `skeleton`, `vindicator`, `witch`, `zombie`) — hasta ahora `CorpseBlock` no tenía ninguna lógica de despiece (clic derecho abría un cofre vacío heredado de MCreator, vestigial). Nuevo `CorpseInteractionHandler` con 8 etapas: cabeza → piel → 3 cortes (loot tables por mob, ya existentes) → 3 órganos (`organs_drop_1/2/3`, tabla compartida entre los 9 mobs). `CorpseBlock`/`CorpseBlockEntity` ahora llevan el `CarcassDefinition` del mob, igual que el sistema de carcasas de animales.
- **12 ítems de órgano nuevos** (`heart`, `intestines`, `kidney`, `liver`, `lungs`, `stomach` + variantes `rotten_`) con modelo/textura/lang — nunca se habían registrado pese a que las loot tables de corpse ya los referenciaban desde antes.
- `CorpseBlockEntity` reescrito eliminando el contenedor vestigial de 9 slots (`Container`/`WorldlyContainer`/`MenuProvider`/`ChestMenu`) que secuestraba cualquier clic derecho — mismo patrón ya aplicado a `CarcassBlockEntity`.

### Add (sesión 2026-08-26 — Freezer)

- **Freezer portado** (Fase 3.4b2b, primera de las 4 máquinas GUI): bloque con inventario real de 27 slots (3×9, mismo layout que el `FreezerinventoryMenu` original) persistido en su `FreezerBlockEntity`. Primer registro de `MenuType` del port (`ModMenus`) con pantalla propia (`FreezerScreen`) usando la textura GUI generada. Al clic derecho se abre la tapa (blockstate 0→1) con sonido de trampilla de hierro y ráfagas de humo frío, y todo se revierte al cerrar la GUI (`FreezerMenu.removed()`), igual que los procedures `Freezerguiopened/guisisclosed` originales — incluido el detalle de que **con shift pulsado la GUI se abre sin animación** (guard `!isShiftKeyDown` del procedure original).
- **Primer sistema de partículas del port**: `ModParticleTypes` + `freezersmoke` (SimpleParticleType) con provider cliente `FreezerSmokeParticle`, port 1:1 de la decompilada `FreezersmokeParticle` (gravedad −0.1, vida 32±8 ticks, ciclo de 8 sprites a razón de 1/6 tick, capa OPAQUE) y las 8 texturas originales. Se genera en las 4 posiciones/dispersiones exactas del procedure de apertura.
- Soporte de comparador: el freezer emite señal redstone proporcional al contenido (`getAnalogOutputSignal`, como el original).
- Assets completos para los **6 estados visuales** del blockstate (0-5): blockstate JSON con las 24 variantes facing×blockstate, modelos wrapper `freezer_0..4` sobre los customs `freezer_open`/`freezer_left(_open)`/`freezer_right(_open)` recién copiados. En el original solo 0-1 son alcanzables por código (abierto/cerrado); 2-5 existen para comandos/debug y se portan por fidelidad.
- Receta de crafteo (hierro ×2 + hormigón blanco ×5 + redstone, remapeada 1:1 del `freezercrafting.json` original), loot table de bloque e ítem con modelo 3D en mano/inventario.

### Fix (sesión 2026-08-26 — Freezer)

- Propiedades del bloque corregidas a las del original decompilado: `strength(1.0, 10.0)` + `noOcclusion()` + `isRedstoneConductor(false)` (la delegación había puesto `3.5/6.0` sin flags de oclusión). `propagatesSkylightDown=true` también portado. El sonido metálico y `mapColor(METAL)` se mantienen como mejora deliberada del port (el original no especifica soundType → piedra por defecto de MCreator).
- Fuente de sonido `NEUTRAL` (como los procedures originales), no `BLOCKS`.

### Delegación (nota de proceso)

Implementado con OpenCode Go tras varios intentos fallidos con distintos modelos: `ox-alpha-free` se colgó 4+ horas sin salida; `muse-spark-1.2-contributor` falló 2 veces de forma reproducible con un bug de la integración (`tool_search` mal formado, además dejando el historial de sesión "envenenado" e impidiendo reanudar con `-c`). Terminado con éxito por `mimo-v2.5`. Catálogo de modelos actualizado con ambos hallazgos en `codex-docs/reference/opencode-go-models/INDEX.md`.

> Nota sesión 2026-08-26: la delegación dejó el Freezer a medias (compilaba pero con blockstate 0-1 en vez de 0-5, sin comparador, sin partículas, sin guard de shift, propiedades incorrectas). Completado y corregido leyendo el original decompilado directamente.

## [0.0.0-beta.32] - 2026-08-25

### Add

- **7 bloques decorativos/con lógica ligera** portados (Fase 3.4b2, batch "simple" del batch B2): `basin` (recoge sal de las `salt_formation` con el tiempo), `brain` (atrae zombies cercanos), `cash_register_block` (cajón abre/cierra), `skin_rack` (percha para hasta 32 combinaciones de piel), `wooden_spit_rotisserie` (asador: crudo→cocido→quemado sobre una hoguera), `jar` (bote que muestra su contenido), `metal_tray` (decorativo puro). Sin `BlockEntity`/GUI real (mismo hallazgo que las carcasas: el contenedor de 9 slots es plantilla vestigial de MCreator). Delegado a Nvidia `nemotron-3-ultra-550b-a55b`, 6 reanudaciones por sobrecarga transitoria del servicio.
- Excluido del batch: el set `Irongolem`/`arms`/`body`/`head`/`legs` — su `IronGolemCutUpProcedure` decompilada tiene **2044 líneas** (más que Ravager), es otro mob-boss bespoke completo (cortar en piezas + reensamblaje vía `RepairgolemProcedure`), no un bloque decorativo. Descartado del alcance de esta sesión, mismo tratamiento que Ravager (documentado, no portado).

### Fix

- **`data/slaughter_hide/loot_tables/` (plural, ruta incorrecta para MC 26.2) contenía 154 loot tables que nunca se cargaban en el juego**, de las cuales 136 no existían en ningún otro sitio: prácticamente todos los mobs humanoides (`drowned`, `evoker`, `husk`, `piglin`, `piglin_brute`, `skeleton`, `vindicator`, `witch`, `zombie`) y los 4 mobs "especiales" (`enderman`, `endermite`, `sniffer`, `strider`, `turtle`) más los drops de `ravager` — es decir, ~14-15 mobs probablemente sin dropear nada real al despiezarlos desde que se portaron, en silencio (sin crash). Movidas a `data/slaughter_hide/loot_table/` (singular, la ruta real verificada contra el jar vanilla de MC 26.2). Las 18 restantes eran duplicados byte-idénticos ya presentes en la ruta correcta — eliminadas tras confirmar la identidad exacta del contenido, no contenido nuevo perdido.

### Known issues

- **Varios ítems que la delegación asumía existentes nunca se registraron en este port**: los 18 ítems de órgano (`heart`/`intestines`/`kidney`/`liver`/`stomach`/`lungs` y sus variantes podrida/enderman — pese a que el roadmap habla de un sistema "organ-harvesting" para humanoides, los corpses reales dropean `rotten_flesh`/`animal_fat` vanilla, no órganos), `coin` (moneda de la caja registradora), `crackling` (uno de los drops del asador), y ~15 pieles de caballo/llama/mooshroom para `skin_rack`. Las funciones que dependían de ellos se simplificaron para no referenciar ítems inexistentes (documentado inline en cada handler) en vez de inventar/registrar contenido nuevo — fuera de alcance de un pase de porting.
- **Descubrimiento importante sin resolver todavía**: la carpeta `data/slaughter_hide/loot_tables/` (plural) contiene cientos de loot tables ya existentes (todos los corpses humanoides, Enderman, Turtle, Sniffer, Strider, Ravager) que **probablemente nunca han funcionado** — el vanilla real de MC 26.2 usa `data/minecraft/loot_table/` (singular, verificado contra el jar), la misma convención que ya usan cow/pig/sheep y los bloques de esta sesión. Alcance y plan de arreglo pendientes de decidir con el usuario, ver ROADMAP.

### Fix

- `ENDERMAN`/`TURTLE` declaraban `numCuts=8`/`7` en `Carcasses.java`, pero `CarcassCutupHandler` siempre estuvo hardcodeado a exactamente 3 cortes — las loot tables `cut_4` en adelante eran inalcanzables desde el juego (sin crash, solo contenido incompleto). Corregido el dato a `numCuts=3` para reflejar la realidad; no se ha generalizado el handler (decisión deliberada, ver `docs/ROADMAP_SLAUGHTER_HIDE.md`, Fase 3.4b1.5).
- `ravager_carcass`/`pufferfish_carcass` se registraban sin blockstate/modelo real (el original nombra estos assets `ravager.json`/`pufferfish.json`, sin sufijo). Copiado/remapeado el asset con el nombre correcto para ambos; el ciclo de despiece bespoke real de Ravager (cabeza + 4 patas + cuerpo, con seguimiento por NBT) sigue sin portar — descartado deliberadamente, documentado como limitación conocida.

## [0.0.0-beta.31] - 2026-08-24

### Add

- **4 bloques con lógica real** portados (Fase 3.4, batch B1): `blood_splatter` (clic derecho con mano vacía cicla un estado 0-10, como si se fuera limpiando la mancha), `plastic_sheet`/`plastic_sheet_corner` (tick infinito que comprueba el bloque de encima y cambia entre estado "sellado"/"abierto"), `spike_trap` (daño genérico 1.0 + Fatiga de Minería 60 ticks al pisarlo). Handler nuevo `BloodSplatterInteractionHandler` (evento `PlayerInteractEvent.RightClickBlock`, mismo patrón que `CarcassInteractionHandler`/`HookPlacementHandler`). Delegado a Nvidia (`nemotron-3-ultra-550b-a55b`), con 6 reanudaciones seguidas por sobrecarga transitoria del servicio — el propio código quedó limpio y fiel al original, sin errores de compilación de la delegación (a diferencia del batch A).

- **26 bloques mecánicos simples** portados del catálogo original (Fase 3.4, batch A): `deepslate_sulfur_ore`, `sulfur_ore`, `diorite_bricks`/`diorite_brick_slab`/`diorite_brick_stairs`/`diorite_brick_wall`, `salt_formation_base`/`middle`/`tip`/`frustum`, `salt_block`, `dragon_scale_block`, `bone_barrel`, `cod_barrel`, `salmon_barrel`, `floorstanding_sign`, `butcher_statue`, `cling_film`, `photos`, `ravager_head`, `ravager_head_mount`, `iron_golem_head_mount`, `cooked_blood_sausages`, `cooked_sausages`, `raw_blood_sausages`, `raw_sausages` — con bloque + ítem + modelos/texturas/loot table/lang inglés, todos con assets originales reutilizados (namespace remapeado). Delegado a OpenCode Go (Kimi K2.7 Code, cuota agotada a mitad) y Nvidia (`nemotron-3-super-120b-a12b`, varias reanudaciones tras cortes transitorios del servicio).
- Nueva `CarcassDefinition` mínima para `iron_golem` en `Carcasses.java` (solo `hasHeadMount=true`), necesaria para que `IronGolemHeadMountBlock` reutilice el patrón genérico `HeadMountBlock` — no forma parte del sistema de despiece (iron golem no tiene carcasa/sangrado), es solo para el trofeo de pared.

### Fix

- `BloodSplatterBlock.BLOCKSTATE` se declaró con rango 0-10 (11 valores) y ciclaba con `% 11`, pero el original decompilado usa rango 0-9 (10 valores, `IntegerProperty.create("blockstate", 0, 9)`) y los assets de blockstate solo definen variantes 0-9 — con el rango incorrecto, el valor 10 habría producido "Missing model for variant" en el juego. Corregido el rango a 0-9; además, la propia lógica original del mod (`BloodsplatterrightclickProcedure`) nunca completa la transición 9→10 con éxito (el `IntegerProperty` original tampoco admite el valor 10, así que ese intento de `setBlock` es un no-op silencioso) — el comportamiento real del mod original es que el splatter se queda "atascado" en la etapa 9 tras limpiarlo del todo, no vuelve a ensuciarse. Portado fielmente: en vez de ciclar con módulo, el handler ahora tope a en 9 y no hace nada en clics posteriores.
- `BloodSplatterBlock` no importaba `HorizontalDirectionalBlock` pese a usar su campo estático `FACING` — arreglado.
- Duplicidad inofensiva pero confusa: el propio `BloodSplatterBlock` tenía un override de `useWithoutItem` que hacía exactamente lo mismo que el nuevo `BloodSplatterInteractionHandler` (el handler cancela el evento antes de que el override llegara a ejecutarse, así que no había doble incremento, pero era código muerto) — eliminado el override, se queda solo el handler.
- 63 errores de compilación en los 26 bloques nuevos (imports de `MapColor`/`SoundType`/`BlockPos` faltantes, paquete equivocado de `DirectionProperty`/`Rotation`, constantes de `MapColor` inexistentes en esta versión de Minecraft como `RED`/`YELLOW`/`COLOR_WHITE`/`TERRACOTTA`, falta de override `codec()` en subclases de `HorizontalDirectionalBlock`, constructor de `StairBlock` mal invocado) — corregidos a mano tras la delegación, que dejó el código sin compilar.
- Crash de arranque `NullPointerException: Trying to access unbound value` para `diorite_bricks`: `DIORITE_BRICK_STAIRS` estaba registrado en `ModBlocks.java` **antes** que `DIORITE_BRICKS`, del que depende (`StairBlock` necesita el `BlockState` del bloque base en su constructor) — NeoForge procesa el registro en el orden declarado, así que `DIORITE_BRICKS.get()` fallaba por no estar aún vinculado. Corregido reordenando las declaraciones.
- Referencia de textura de partícula rota heredada del **propio mod original** (`bone_barrel` apuntaba a `cow_break_particle.png`, que no existe en ningún sitio del asset dump extraído) — corregido reutilizando la textura del propio bloque como partícula, sin inventar arte nuevo.

### Known issues

- Comentarios dubitativos dejados por la delegación en `IronGolemHeadMountBlock.java` ("we assume it exists") limpiados tras confirmar que `Carcasses.IRON_GOLEM` compila y funciona correctamente.
- Verificación de arranque en cliente (`runClient`) pendiente — no se ha vuelto a lanzar tras el fix de orden de registro (ver incidente de permisos: `runClient` se lanzó sin autorización del usuario en la sesión del 2026-08-24, se detuvo y no se ha repetido).

## [0.0.0-beta.30] - 2026-08-24

### Add

- **71 mobs adicionales** sobre el trío de referencia (vaca/cerdo/oveja), siguiendo el patrón genérico de `CarcassDefinition` sin código nuevo por mob: 48 animales simples más (pollo, conejo, cabra, zorro, lobo, camello, burro, mula, ocelote, panda, oso polar, hoglin, zoglin, delfín, murciélago, gallineta de plata, endermite, abeja, bacalao, salmón, phantom, shulker, guardián/guardián anciano, caballos esqueleto/zombi/normal, llamas (4 colores), calamar/calamar brillante, creeper, araña/araña de cueva, ajolote (5 colores), pez globo, slime/slime mediano/pequeño, magma cube/mediano/pequeño); **10 humanoides** con bloque `Corpse`/`Skeleton` y cortes de órgano en vez de carcasa (zombi, esqueleto, ahogado, husk, vindicator, evoker, bruja, piglin, piglin brute, ravager); **4 mobs con tratamiento especial** (enderman, strider, sniffer, tortuga); **11 variantes de pelaje de gato**. Total: 74 definiciones funcionales.
- **Sistema `Rope`** como alternativa a `Hook` para colgar carcasas (`RopeBlock` + `RopePlacementHandler`) — versión simplificada de 1 clic, no la mecánica de "tensar la cuerda" con blockstate 0-7 del original (ver Roadmap, Fase 3, huecos conocidos).
- **246 recetas de cocinado** (`smelting`/`smoking`/`campfire_cooking`) para transformar cortes crudos en cocidos, cubriendo todos los mobs con carne portados.
- Colocación de `head_mount`/`skeleton` por el jugador vía comportamiento `BlockItem` vanilla estándar (sin lógica custom de interacción).
- Registro de los 24 ítems de herramienta (`cleaver`/`skinning_knife`/`hacksaw`/`hammer` × 6 tiers: iron/copper/gold/diamond/netherite/bone) con modelos, y fix de `ModCreativeTabs` para listar dinámicamente todos los ítems registrados en vez de una lista fija.

### Fix (esta sesión, 2026-08-24)

- `MEDIUM_MAGMA_CUBE`/`SMALL_MAGMA_CUBE`: estaban definidos en `Carcasses.java` pero nunca registrados (código muerto, inalcanzable desde el juego pese a tener ítems asociados). Añadidos al bloque `register()`.
- Texturas `iron_hacksaw.png`/`iron_hammer.png` no existían pese a que los ítems `IRON_HACKSAW`/`IRON_HAMMER` sí estaban registrados (mostraban textura ausente en el juego). Copiadas del placeholder compartido por el resto de tiers.
- `Block{[unregistered]}` al arrancar por registro de bloques/ítems condicionado a la existencia de sus assets, sin gating correcto.

### Known issues

- **Sangre visible sin implementar**: `CarcassBleedingHandler` solo genera partículas de humo (`ParticleTypes.SMOKE`), no hay bloques `Blood`/`Bloodgrate`/`Bloodpuddle` — asset `blood.json` (blockstate+modelo) existe pero está huérfano, sin clase Java que lo registre.
- **Texturas de tier de herramienta son placeholders**: copper/gold/diamond/netherite/bone comparten textura idéntica (mismo archivo) con iron tier por tipo de herramienta — pendiente de arte propio por tier.
- **Recetas de crafteo huérfanas** heredadas del MCreator original (`basinrecipe.json`, `bloodgraterecipe.json`, `butcherstatuerecipe.json`, `cashregisterrecipe.json`, `freezercrafting.json`, `meatgrinderrecipe.json`, `skinrackrecipe.json`, `spiketraprecipe.json`) apuntan a bloques/ítems que no existen en el port (los 54 bloques mecánicos únicos siguen sin portar, ver Roadmap Fase 3.4) — probablemente generan warnings al cargar el datapack. No eliminadas (regla del proyecto: no borrar sin permiso).
- **JEI/Patchouli**: sin integración (README los listaba como "opcional" pero no hay soporte real).
- **Historial de versiones intermedias no reconstruible**: entre beta.6 (2026-08-18) y beta.30 (2026-08-24) el repositorio git local solo conserva 3 commits (`b458aec`, `6f377e0`, `25d2af9`) — sin remoto configurado y en rama `master`, no en la convención `minecraft/26.2/neoforge-26.2.0.45-beta/production` del workflow. El desglose exacto de cambios por número de beta no se puede recuperar; esta entrada consolida el estado final observado en el código a fecha de hoy.

## [0.0.0-beta.6] - 2026-08-18

### Add

- **Oveja** como tercer mob completo: matar → colgar del `Hook` → sangrar → drenar → despiezar (cabeza, piel, 5 cortes de cordero: pierna, paletilla, costilla, solomillo, lomo).

## [0.0.0-beta.5] - 2026-08-18

### Add

- **Cerdo** como segundo mob completo: matar → colgar del `Hook` → sangrar → drenar → despiezar (cabeza, piel, 3 cortes: paleta/lomo/pierna/panceta/jamón) → bloque desaparece. Mismo sistema genérico que la vaca, sin código nuevo por mob.

### Fix

- Al romper (no despiezar limpiamente) una carcasa a medio cortar, ahora dropea el corte de carne correcto según el mob (antes siempre dropeaba ternera, incluso para cerdo).

## [0.0.0-beta.4] - 2026-08-18

### Change

- **Restringida la colocación de la carcasa**: ya no se puede colocar en cualquier sitio como un bloque normal. Ahora hay que colgarla de un nuevo bloque `Hook` (clic derecho con la carcasa en mano), igual que en el mod original.

### Known issues

- El bloque `Rope` del original (colgar sin necesidad de un Hook fijo, tras "tensarlo" con varios clics) no está portado — su lógica exacta no se pudo verificar con confianza desde el bytecode decompilado. Solo `Hook` es funcional por ahora.

## [0.0.0-beta.3] - 2026-08-15

### Fix

- La vaca ya no coloca la carcasa automáticamente colgada al morir: ahora suelta el ítem `Cow Carcass` para que el jugador lo coloque donde quiera (feedback de prueba en beta.2).
- `cow_head_mount` ya renderiza correctamente — el modelo mezclaba unidades UV (rejilla 0-16 vs píxeles), bug heredado del Butchery original, expuesto por la validación más estricta de NeoForge 26.2. Corregido comparando con el modelo hermano `cow_head`.
- Limpieza de geometría muerta invisible (`neck` element del modelo `cow_head`) y de una referencia de textura rota (`"missing": "slaughter_hide:block/"`).

### Add

- Traducción completa al castellano (`lang/es_es.json`).

## [0.0.0-beta.2] - 2026-08-15

### Add

- Sistema genérico de carcasas (bloques + block entity + handlers de sangrado/despiece), validado de punta a punta con la **vaca** como mob de referencia: matar → carcasa colgante → sangrar (cleaver) → drenar → despiezar en 5 pasos (cabeza, piel, 3 cortes de carne) → bloque desaparece.
- Assets/datos de la vaca migrados desde el original (`butchery:` → `slaughter_hide:`): blockstates, modelos, texturas, tablas de botín, lang, tags de herramienta.
- Herramientas tier Iron: `iron_cleaver`, `iron_skinning_knife`.
- Config `INSTANT_BLEED` (`SlaughterHide.toml`).

### Fix

- Crash de arranque: `ModBlockEntities` resolvía bloques diferidos antes de que estuvieran registrados (`NullPointerException: Trying to access unbound value`). Corregido moviendo la resolución dentro del supplier de registro.

### Known issues

- El modelo de `cow_head_mount` no compila en NeoForge 26.2 (bug heredado del asset original de Butchery, expuesto por validación UV más estricta) — bloque decorativo sin renderizar, no afecta al ciclo de despiece. Ver `docs/ROADMAP_SLAUGHTER_HIDE.md`, Fase 2.1.

## [0.0.0-beta.1] - 2026-08-14

### Add

- Andamiaje inicial del repositorio (fork/port de Butchery 5.2 por Jmods, NeoForge 26.1.2 → 26.2): `build.gradle`, `gradle.properties`, `settings.gradle`, plantilla `neoforge.mods.toml`, clase principal `SlaughterHide`, docs (`WORKFLOW`, `ROADMAP`, CurseForge stubs).
- Sin lógica de juego portada todavía — ver `docs/ROADMAP_SLAUGHTER_HIDE.md`.
