# Slaughter & Hide

Slaughter & Hide is a Minecraft 26.2 (NeoForge) mod.

> This mod is a port of [Butchery](https://www.curseforge.com/minecraft/mc-mods/butchery) by Jmods, ported from NeoForge 26.1.2 to NeoForge 26.2, with all identifiers renamed to this project's convention. Used with explicit permission from the original author. See [docs/ROADMAP_SLAUGHTER_HIDE.md](docs/ROADMAP_SLAUGHTER_HIDE.md) for the porting plan.

## Status

Playable beta (`0.0.0-beta.30`). Core butchering loop is ported and generic across mobs: kill → hang from a `Hook` or `Rope` → bleed → drain → cut up in stages → drop meat/skin/head. **74 mobs** are wired up, including 51 simple animals, 10 humanoids (zombie, skeleton, drowned, husk, vindicator, evoker, witch, piglin, piglin brute, ravager) with corpse/organ drops instead of carcasses, 4 special-cased mobs (enderman, strider, sniffer, turtle), and 11 cat coat variants. Cooking recipes for raw cuts are in place (246 smelting/smoking/campfire recipes). The original mod is only available as a compiled JAR (no public source), so the port starts from decompiled bytecode. See [docs/ROADMAP_SLAUGHTER_HIDE.md](docs/ROADMAP_SLAUGHTER_HIDE.md) for what's still pending (visible blood, the 54 standalone mechanical blocks, real tool tier textures, JEI/Patchouli).

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
