# Changelog — Slaughter & Hide

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
