# CurseForge — Variables del proyecto

> **Proyecto aún NO creado en CurseForge.** El alta manual se hace cuando el mod tenga una build funcional para publicar (ver `docs/ROADMAP_SLAUGHTER_HIDE.md`, Fase de release). Esta ficha es la referencia lista para rellenar en ese momento.

## Datos para el alta manual (formulario "Create Project")

| Campo del formulario | Valor a usar |
|---|---|
| Project Name | `Slaughter & Hide` |
| Slug/URL | `slaughter-hide` (verificar disponibilidad; si está ocupado, `slaughter-and-hide`) |
| Summary (resumen corto) | `Realistically butcher everything in Minecraft — a Butchery port for NeoForge 26.2, used with the original author's permission.` |
| Category | Mobs / Decoration & Building (según disponibilidad — mismo tipo usado por Butchery original) |
| License | All Rights Reserved (port de Butchery por Jmods, usado con permiso expreso — ver `README.md`) |
| Game | Minecraft |
| Mod Loader | NeoForge |
| Client/Server | Both |
| Description | Contenido de `project_description.md` (HTML) |
| Relations — optional dependency | JEI |
| Issue tracker | URL del repo (GitHub, tras el mirror) |
| Source URL | URL del repo (GitHub, tras el mirror) |

## Icono / imagen del proyecto

Pendiente de diseñar (`assets/slaughter_hide/icon.png`) — no reutilizar ningún asset del `butchery-5.2-neoforge-26.1.2.jar` original (ver regla de "sin residuos" en el WORKFLOW). Definir prompt/estilo cuando se llegue a la fase de arte del roadmap.

## Proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | *(pendiente — se rellena al crear el proyecto)* |
| `mod_id` | `slaughter_hide` |
| `display_name` | `Slaughter & Hide` |

## Tokens

| API | Token | Uso |
|-----|-------|-----|
| Upload | *(mismo token de cuenta usado en el resto de mods — copiar desde `docs/curseforge/project_vars.md` de otro proyecto del grupo al crear este)* | Subir archivos JAR |
| Core (GET) | *(ídem)* | Consultar datos del mod |

Autenticación Upload: cabecera `X-Api-Token`
Autenticación Core: cabecera `x-api-key`

## Variables para script (lectura automática)

project_id =
api_token =
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

## Rama

```
minecraft/26.2/neoforge-26.2.0.45-beta/production
```

## Tag

Formato: `<mc-version>-<framework>-<version>`
Ejemplo (primera beta): `26.2-neoforge-beta.1`

## Nota

La **primera subida a CurseForge se hace manual** (proyecto por crear, sin archivos previos que verificar por API). A partir de la segunda subida se puede usar el script `codex-docs/scripts/curseforge-upload.ps1`, que lee `project_id`, `api_token` y `game_versions` de este archivo (rellenar tras el alta) y `mod_id`, `mod_name`, `minecraft_version`, `mod_version` de `gradle.properties`.
