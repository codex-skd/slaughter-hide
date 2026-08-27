# CurseForge — Variables del proyecto

> Proyecto creado: `project_id = 1652921`.

## Datos para el alta manual (formulario "Create Project")

| Campo del formulario | Valor a usar |
|---|---|
| Project Name | `Slaughter & Hide` |
| Slug/URL | `slaughter-hide` (verificar disponibilidad; si está ocupado, `slaughter-and-hide`) |
| Summary (resumen corto, campo "Summary" de CurseForge, máx. ~250 car.) | `Realistically butcher every mob in Minecraft: skin carcasses, cure hides into leather, cut meat, and cook it all at the butcher's table. An official port of Butchery (by Jmods) to NeoForge 26.2, used with the original author's permission.` |
| Category | Mobs / Decoration & Building (según disponibilidad — mismo tipo usado por Butchery original) |
| License | All Rights Reserved (port de Butchery por Jmods, usado con permiso expreso — ver `README.md`) |
| Game | Minecraft |
| Mod Loader | NeoForge |
| Client/Server | Both |
| Description | Contenido de `project_description.md` (HTML) |
| Relations — optional dependency | JEI |
| Issue tracker | `https://gitlab.com/stalking-dragons/minecraft/slaughter-hide/-/issues` |
| Source URL | `https://gitlab.com/stalking-dragons/minecraft/slaughter-hide` |

## Icono / imagen del proyecto

El jar original no trae un logo de proyecto (solo iconos de ítem sueltos: `elder_guardian_icon.png`, `sausage_icon.png`, etc. — no sirven como logo cuadrado de CurseForge). Hay que generar uno propio. Nota: esto **no contradice** la decisión de reutilizar assets in-game (texturas/modelos/sonidos de Butchery se reutilizan tal cual, ver WORKFLOW) — es solo que no existe un asset de logo que reutilizar.

Prompt (tema: carnicería/curtido rústico, sin texto):

```
Fantasy game project icon, a rustic wooden butcher's chopping block seen
from a slight top-down angle, a heavy cleaver stuck upright in the wood,
a cured animal hide draped over one corner and a small string of sausages
hanging from a meat hook in the background, warm dim tavern lighting,
deep browns and dried-blood reds, subtle steam rising from a fresh cut of
meat on the block, painterly digital art style matching a cozy rustic
Minecraft mod icon aesthetic, square composition, centered subject, no
text, no border, high detail, 1:1 aspect ratio
```

Generar en alta resolución (1024x1024 recomendado) y exportar dos tamaños: 64x64 para `assets/slaughter_hide/icon.png` (icono in-game, ver `logoFile` en `neoforge.mods.toml`) y una versión cuadrada (mínimo 256x256, PNG con fondo) para el logo del proyecto en CurseForge.

**Hecho** (2026-08-15): logo generado y aplicado.
- `src/main/resources/assets/slaughter_hide/icon.png` — 64×64, icono in-game.
- `docs/curseforge/assets/project_logo_256.png` — 256×256, para subir como logo del proyecto en CurseForge.
- `docs/curseforge/assets/project_logo.png` — original 1254×1254, referencia.

## Proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | `1652921` |
| `mod_id` | `slaughter_hide` |
| `display_name` | `Slaughter & Hide` |

## Tokens

| API | Token | Uso |
|-----|-------|-----|
| Upload | `ee776b0a-ee95-4850-b554-06be02a8657f` | Subir archivos JAR (token de cuenta, compartido entre proyectos) |
| Core (GET) | `$2a$10$yGwryAfmRkS9ZJsJUDf5YOKZpOIsmHB8Fji2D8JVCKBSZEKYlwmaO` | Consultar datos del mod |

Autenticación Upload: cabecera `X-Api-Token`
Autenticación Core: cabecera `x-api-key`

## Variables para script (lectura automática)

project_id = 1652921
api_token = ee776b0a-ee95-4850-b554-06be02a8657f
release_type = beta
game_versions = 9638, 9639, 16498, 10150
relations =

El script lee `project_id`, `api_token` y `game_versions` de este archivo, y `mod_id`, `mod_name`, `minecraft_version`, `mod_version` de `gradle.properties`. Sube automáticamente el JAR desde `build/libs/` con el changelog de `docs/curseforge/versions/<version>.md`.

## Versión actual

| Variable | Valor |
|----------|-------|
| `minecraft_version` | `26.2` |
| `framework` | `neoforge` |
| `java_version` | `25` |
| `environment` | `Client`, `Server` |

## Subidas

| Versión | CurseForge file ID | Fecha |
|---|---|---|
| `0.0.0-beta.37` | `8748477` | 2026-08-27 (CRASHEA al arrancar, sustituida por beta.38) |
| `0.0.0-beta.38` | `8748711` | 2026-08-27 (hotfix del crash de beta.37) |
| `0.0.0-beta.39` | `8748853` | 2026-08-27 (pulido sangre + tiers herramienta) |
| `0.0.0-beta.40` | `8748994` | 2026-08-27 (blood feel) |
| `0.0.0-beta.41` | `8749159` | 2026-08-27 (41 bloques decoración) |
| `0.0.0-beta.42` | `8749463` | 2026-08-27 (pose carcasa colgada) |
| `0.0.0-beta.43` | `8749739` | 2026-08-27 (mesa taxidermia 2 bloques) |

## Rama

```
minecraft/26.2/neoforge-26.2.0.57/production
```

## Tag

Formato: `<mc-version>-<framework>-<version>`
Ejemplo (primera beta): `26.2-neoforge-beta.1`

## Nota

La **primera subida a CurseForge se hace manual** (proyecto por crear, sin archivos previos que verificar por API). A partir de la segunda subida se puede usar el script `codex-docs/scripts/curseforge-upload.ps1`, que lee `project_id`, `api_token` y `game_versions` de este archivo (rellenar tras el alta) y `mod_id`, `mod_name`, `minecraft_version`, `mod_version` de `gradle.properties`.
