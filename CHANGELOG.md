# Changelog — Slaughter & Hide

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
