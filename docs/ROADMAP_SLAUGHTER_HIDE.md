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

## Fase 0 — Viabilidad y decompilación (bloqueante, siguiente paso)

1. Decompilar el jar completo (Vineflower o CFR — no hay decompilador instalado localmente, requiere descarga puntual) a `temp/butchery-src/` (no versionado).
2. Ollama (`qwen2.5-coder:7b`) para pre-filtrar y resumir los `procedures/*.java` decompilados más largos antes de que la sesión los lea completos (regla de `codex-docs/reference/CLAUDE.md` — no aplica al código del propio mod que se está portando, pero sí sirve para hacer un primer barrido de qué hace cada procedure antes de decidir el orden de port).
3. Catalogar: qué bloques/ítems son variantes simples (mismo patrón × N mobs, ej. "cabeza de X", "alfombra de X") vs mecánicas núcleo únicas (mesa de carnicero, curtido de pieles, etc.). Las variantes en serie son candidatas a generación asistida (script/plantilla), no a copiar clase a clase a mano.
4. Confirmar con el usuario si el port apunta a **paridad completa** o a un **subconjunto priorizado** (ver Fase 1+ — probablemente la decisión correcta dado el tamaño).

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

## Decisiones pendientes de confirmar con el usuario

1. **Alcance**: ¿paridad 100% con Butchery 5.2, o un subconjunto priorizado publicable antes? (recomendado dado el tamaño: subconjunto por fases con betas incrementales).
2. **Reutilización de assets**: dado que hay permiso expreso del autor, ¿se pueden reutilizar texturas/modelos/sonidos originales tal cual, o se prefiere arte propio igual que en los demás ports del workspace?
3. **Descarga de decompilador** (Vineflower o CFR, ~5-15 MB): requiere permiso explícito antes de descargar (regla de acciones que requieren confirmación).
4. **Modelo de OpenCode a usar** para las fases delegadas (se pregunta una vez al iniciar la primera delegación, según `codex-docs/reference/CLAUDE.md`).
