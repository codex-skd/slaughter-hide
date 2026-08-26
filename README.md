# Slaughter & Hide

Slaughter & Hide is a Minecraft 26.2 (NeoForge) mod.

> This mod is a port of [Butchery](https://www.curseforge.com/minecraft/mc-mods/butchery) by Jmods, ported from NeoForge 26.1.2 to NeoForge 26.2, with all identifiers renamed to this project's convention. Used with explicit permission from the original author. See [docs/ROADMAP_SLAUGHTER_HIDE.md](docs/ROADMAP_SLAUGHTER_HIDE.md) for the porting plan.

## Status

Playable beta (`0.0.0-beta.33`). Core butchering loop is ported and generic across mobs: kill → hang from a `Hook` or `Rope` → bleed → drain → cut up in stages → drop meat/skin/head. **13 farm animals** are wired up (cow, pig, sheep, chicken, rabbit, goat, horse, donkey, mule, brown/white/creamy/gray llama). Cooking recipes for raw cuts are in place (246 smelting/smoking/campfire recipes). The first GUI workstation is in: the **Freezer** (27-slot cold storage with lid-open animation, particles and comparator support). The original mod is only available as a compiled JAR (no public source), so the port starts from decompiled bytecode. Scope intentionally limited to farm animals for simplicity; see [docs/ROADMAP_SLAUGHTER_HIDE.md](docs/ROADMAP_SLAUGHTER_HIDE.md) for what's still pending (visible blood, real tool tier textures, JEI/Patchouli).

## Requirements

| Component | Version |
|---|---|
| Minecraft | 26.2 |
| NeoForge | 26.2.0.57 |
| Java | 25+ |
| JEI | optional (not yet integrated) |

## Installation

1. Install [NeoForge](https://neoforge.net/) for Minecraft 26.2.
2. Download the mod jar and place it in your `mods/` folder.

## License

All Rights Reserved. Original mod "Butchery" by Jmods (All Rights Reserved), ported with the author's explicit permission. Not affiliated with or endorsed by the original author beyond that permission.
