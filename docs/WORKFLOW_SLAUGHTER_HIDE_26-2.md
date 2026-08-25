# Flujo de trabajo — Slaughter & Hide (NeoForge)

> **Versión del workflow**: 1.17.0 (codex-docs)
> Este archivo pertenece al proyecto **Slaughter & Hide**. Cambios aquí solo afectan a este proyecto.
> **Trabaja directamente con este archivo**: es el workflow operativo del mod, autocontenido. No leas `codex-docs/WORKFLOW_AGENT.md` ni `WORKFLOW_GENERIC.md` de forma rutinaria.
> On-demand (solo si la tarea lo necesita): `codex-docs/reference/CURSEFORGE.md` (formato HTML al publicar), `codex-docs/reference/GRAPHIFY.md` (backend LLM de Graphify), `codex-docs/reference/REPO_SETUP.md` (setup único de repo).

## Específico del mod

| Dato | Valor |
|---|---|
| Mod ID (`gradle.properties`) | `slaughter_hide` |
| Clase principal | `SlaughterHide` |
| Display name (Title Case) | `Slaughter & Hide` |
| Versiones de Minecraft | `26.2` |
| Rama | `minecraft/26.2/neoforge-26.2.0.57/production` |

### Notas específicas de este mod

- **Es un port declarado**: de [Butchery](https://www.curseforge.com/minecraft/mc-mods/butchery) por Jmods (NeoForge 26.1.2 → 26.2), con todos los identificadores renombrados a la convención propia. Usado con **permiso expreso del autor original** (mod original bajo licencia "All Rights Reserved" — sin ese permiso este port no sería legítimo). Roadmap completo por fases: `docs/ROADMAP_SLAUGHTER_HIDE.md`.
- **package**: `com.skd.slaughterhide`
- **Minecraft / NeoForge**: `26.2` / `26.2.0.57` (no actualizar sin pedirlo explícitamente)
- **Origen MCreator**: el mod original está generado con [MCreator](https://mcreator.net/) (paquete `net.mcreator.butchery`). Solo existe el `.jar` compilado (`butchery-5.2-neoforge-26.1.2.jar`, sin fuente pública) — el port arranca desde bytecode decompilado, no desde un proyecto MCreator editable. Ver Fase 0 del roadmap.
- **Referencia en `lib_ext/`**: `butchery-5.2-neoforge-26.1.2.jar` (compilado, sin fuente) + su decompilación en `temp/butchery-src/` (no versionado, CFR 0.152 desde `%TEMP%\opencode\cfr.jar`) como base de lectura para portar clase a clase. `lib_ext/` y `temp/` no se versionan (ver `.gitignore`).
- **Atribución obligatoria**: mantener "port of Butchery by Jmods, used with permission" en `README.md`, `docs/curseforge/project_description.md` y `credits` de `neoforge.mods.toml` durante todo el desarrollo.
- **Assets SÍ se reutilizan** (decisión confirmada 2026-08-15, a diferencia de otros ports del workspace): texturas/modelos/sonidos/lang de Butchery se copian tal cual bajo `assets/slaughter_hide/`, dado el permiso expreso del autor. No aplica a nombres de paquete/clase Java (ver punto siguiente).
- **Sin residuos MCreator en el código**: eliminar todo rastro de `net.mcreator.butchery` (paquetes, clases, comentarios generados, `credits="Made with Mcreator"`) — reescribir a mano, no solo renombrar mecánicamente, salvo que el roadmap indique lo contrario para una fase concreta.
- **Alcance**: subconjunto priorizado con betas incrementales (no paridad 100% desde el inicio) — ver orden de fases en `docs/ROADMAP_SLAUGHTER_HIDE.md`.
- **Compat opcional a confirmar en Fase 0**: JEI (el original lo declara opcional en `neoforge.mods.toml`) — verificar versión compatible con NeoForge 26.2.0.57 antes de portar `compat/`. No asumir.

## Convenciones de nomenclatura

| Convención | Uso | Ejemplo |
|---|---|---|
| **snake_case** | `mod_id`, assets/, packages Java | `slaughter_hide` |
| **PascalCase** | Clases Java principales | `SlaughterHide` |
| **camelCase** | Variables, métodos, config keys | `slaughterHideConfig` |
| **Title Case** | Display name (README, CHANGELOG, docs, CurseForge) | `Slaughter & Hide` |

## Organización y ramas

- Un repo GitLab por mod, una rama `minecraft/<mc>/neoforge-<neo>/production` por versión. Este clon local trabaja en la rama `production` de esta versión.
- Carpetas: `<mod_id>/<framework>/<mc-version>/` — este clon vive en `slaughter_hide/neoforge/26.2/`.
- `*/main` y CI/CD: setup único al crear el repo (`codex-docs/reference/REPO_SETUP.md`) — no releer ni modificar.

## Estructura del proyecto

`build.gradle` · `gradle.properties` (mod_id, mod_version, mod_group_id, mod_framework) · `settings.gradle` · `src/main/java/<package>/` · `src/main/resources/assets/<mod_id>/` · `META-INF/neoforge.mods.toml` · `libs/` (versionado) · `lib_ext/` y `temp/` (no versionados) · `docs/` (WORKFLOW + ROADMAP + curseforge/) · `CHANGELOG.md` · `README.md` · `graphify-out/` (versionado).

## Versionado

- Beta `0.0.0-beta.X` · Release `X.Y.Z` (SemVer: MAJOR breaking / MINOR feature / PATCH fix)
- `mod_version` y `mod_framework` en `gradle.properties`. JAR: `<mod_id>-<mc>-<framework>-<loader>-<version>.jar`

## Commits (Conventional Commits)

`<tipo>[<ámbito>]: <descripción>` · tipos `feat fix refactor docs chore style perf test` · el mensaje incluye la versión (`v<version>`).

## Tags

Cada subida a CurseForge crea tag: beta `<mc>-neoforge-beta.X` · release `<mc>-neoforge-X.Y.Z`.

## Flujo por tarea

**0. Alcance** — si el mod tiene varias versiones, preguntar con la herramienta `question`: **"Todas"** o una versión. No asumir.

**1. Desarrollo**

```bash
git checkout minecraft/26.2/neoforge-26.2.0.57/production
./gradlew.bat build
git add -A
git commit -m "feat: <descripción>

v<version>"
git push
```

**2. CurseForge** — solo si el usuario confirma:
- Bump `mod_version` en gradle.properties → `./gradlew.bat clean build`
- Release notes `docs/curseforge/versions/<version>.md` (HTML) + actualizar `CHANGELOG.md`
- Commit `chore: bump version to <version>` → tag `<mc>-neoforge-<version>` → push
- Subir JAR: `powershell -File ../../codex-docs/scripts/curseforge-upload.ps1` (desde este repo)
- Formato HTML de descripciones/changelog: `codex-docs/reference/CURSEFORGE.md`

**3. Release estable** — bump `X.Y.Z` + tag.

**4. Graphify** — tras cada push a remoto. Versión 0.9.12: **`build` no existe**, usar `extract` (1ª vez) o `update . --force` (tras cambios):

```bash
GRAPHIFY="C:\Users\llagu\AppData\Local\Packages\PythonSoftwareFoundation.Python.3.13_qbz5n2kfra8p0\LocalCache\local-packages\Python313\Scripts\graphify.exe"
"$GRAPHIFY" update . --force
git add graphify-out/ && git commit -m "chore: update knowledge graph" && git push
```

Leer siempre `GRAPH_REPORT.md`, nunca `graph.json`/`graph.html` (pesan >1MB). Sin copias fechadas de `graphify-out/`. Backend LLM: `codex-docs/reference/GRAPHIFY.md`.

## Buenas prácticas

- Un commit por cambio lógico · commit+push tras cada cambio funcional y de docs
- `clean build` antes del JAR final · versionar antes de CurseForge · CHANGELOG al día
- Graphify actualizado tras cada release · nomenclatura consistente · sin basura en repo (`nul`, `*_errors.txt`, `TEMPLATE_LICENSE.txt`) · `.gitignore` excluye `temp/` y `lib_ext/`
- README en inglés siempre actualizado · sin residuos del original (paquetes `net.mcreator.butchery`, clases, toml, lang, assets)

## Idioma

| Ámbito | Idioma |
|---|---|
| código, logs, commits | en-US |
| README.md | en-US |
| docs internas (docs/, CHANGELOG, este archivo) | es-ES |
| CurseForge | en-US |
