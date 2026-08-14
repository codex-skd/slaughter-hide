# Changelog — Slaughter & Hide

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
