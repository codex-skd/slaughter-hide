# Graph Report - 26.2  (2026-08-20)

## Corpus Check
- 1415 files · ~660,332 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 805 nodes · 2118 edges · 69 communities (65 shown, 4 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 12 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `9ec910ba`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- VoxelShape
- CarcassBlockEntity
- ModBlocks
- Carcasses
- CarcassDefinition
- CorpseBlockEntity
- Block
- Roadmap — Slaughter & Hide (port de Butchery 5.2 → NeoForge 26.2)
- DrainedCarcassBlock
- CorpseBlock
- CarcassDeathHandler.java
- Changelog — Slaughter & Hide
- SlaughterHide.java
- port_assets.py
- SkeletonBlock.java
- TrophyHeadBlock.java
- Flujo de trabajo — Slaughter & Hide (NeoForge)
- CurseForge — Variables del proyecto
- generate_carcasses.py
- CarcassLoot.java
- CarcassDefinition.java
- .dropTable
- .batHanging
- CLAUDE.md — slaughter_hide (26.2)
- gradlew

## God Nodes (most connected - your core abstractions)
1. `Carcasses` - 299 edges
2. `CarcassDefinition` - 118 edges
3. `CorpseBlockEntity` - 26 edges
4. `ModBlocks` - 23 edges
5. `DrainedCarcassBlock` - 21 edges
6. `CarcassBlock` - 20 edges
7. `CorpseBlock` - 20 edges
8. `CarcassBlockEntity` - 18 edges
9. `Roadmap — Slaughter & Hide (port de Butchery 5.2 → NeoForge 26.2)` - 18 edges
10. `HookBlock` - 14 edges

## Surprising Connections (you probably didn't know these)
- `CarcassBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/CarcassBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java
- `DrainedCarcassBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/DrainedCarcassBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java
- `HeadMountBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/HeadMountBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java
- `SkeletonBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/SkeletonBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java
- `TrophyHeadBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/TrophyHeadBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java

## Import Cycles
- None detected.

## Communities (69 total, 4 thin omitted)

### Community 1 - "CarcassBlockEntity"
Cohesion: 0.06
Nodes (28): BlockEntity, Blocks, ClientboundBlockEntityDataPacket, CompoundTag, Provider, CarcassBlockEntity, BlockPos, BlockState (+20 more)

### Community 2 - "ModBlocks"
Cohesion: 0.19
Nodes (7): Item, SoundEvent, HookPlacementHandler, Item, RightClickBlock, SubscribeEvent, CarcassPlacementItem

### Community 5 - "CorpseBlockEntity"
Cohesion: 0.10
Nodes (21): AbstractContainerMenu, BlockEntityType, Component, Container, Inventory, MenuProvider, NonNullList, Nullable (+13 more)

### Community 6 - "Block"
Cohesion: 0.11
Nodes (25): Block, HeadMountBlock, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext (+17 more)

### Community 7 - "Roadmap — Slaughter & Hide (port de Butchery 5.2 → NeoForge 26.2)"
Cohesion: 0.06
Nodes (33): Arquitectura idiomática propuesta (reemplaza ~330 clases de bloque + ~293 procedures), Assets (`assets/butchery/`), `block/` (456 clases), Catálogo Fase 0 — series vs mecánicas únicas (COMPLETADO 2026-08-15), Contexto y tamaño real del mod original, Código (clases `.class`, sin contar internas `$`), Datos (`data/butchery/`), Decisiones confirmadas por el usuario (2026-08-15) (+25 more)

### Community 8 - "DrainedCarcassBlock"
Cohesion: 0.17
Nodes (17): Items, DrainedCarcassBlock, BlockEntity, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder (+9 more)

### Community 10 - "CorpseBlock"
Cohesion: 0.10
Nodes (19): DeferredBlock, EntityBlock, CorpseBlock, BlockEntity, BlockGetter, BlockPlaceContext, BlockPos, BlockState (+11 more)

### Community 11 - "CarcassDeathHandler.java"
Cohesion: 0.08
Nodes (21): DeferredItem, Entity, Identifier, IntObjectPair, ItemInstance, ItemStackTemplate, LivingDeathEvent, Post (+13 more)

### Community 12 - "Changelog — Slaughter & Hide"
Cohesion: 0.11
Nodes (18): [0.0.0-beta.1] - 2026-08-14, [0.0.0-beta.2] - 2026-08-15, [0.0.0-beta.3] - 2026-08-15, [0.0.0-beta.4] - 2026-08-18, [0.0.0-beta.5] - 2026-08-18, [0.0.0-beta.6] - 2026-08-18, Add, Add (+10 more)

### Community 13 - "SlaughterHide.java"
Cohesion: 0.15
Nodes (12): ConfigValue, CreativeModeTab, FMLConstructModEvent, IEventBus, Mod, ModConfigSpec, Builder, SlaughterHideConfig (+4 more)

### Community 14 - "port_assets.py"
Cohesion: 0.15
Nodes (18): apply_panda_rename(), apply_polar_bear_rename(), copy_file_with_remap(), find_matching_files(), find_matching_files_exact(), fix_drained_blockstate(), fix_fresh_blockstate(), main() (+10 more)

### Community 15 - "SkeletonBlock.java"
Cohesion: 0.23
Nodes (11): BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Mirror, Override (+3 more)

### Community 16 - "TrophyHeadBlock.java"
Cohesion: 0.23
Nodes (11): BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Mirror, Override (+3 more)

### Community 17 - "Flujo de trabajo — Slaughter & Hide (NeoForge)"
Cohesion: 0.15
Nodes (12): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Slaughter & Hide (NeoForge), Flujo por tarea, Idioma (+4 more)

### Community 18 - "CurseForge — Variables del proyecto"
Cohesion: 0.18
Nodes (10): CurseForge — Variables del proyecto, Datos para el alta manual (formulario "Create Project"), Icono / imagen del proyecto, Nota, Proyecto, Rama, Tag, Tokens (+2 more)

### Community 19 - "generate_carcasses.py"
Cohesion: 0.29
Nodes (9): format_box(), format_shape_list(), gen_build_method(), gen_shape_method(), main(), Format a box tuple as box(x1, y1, z1, x2, y2, z2), Format a list of boxes for a facing direction., Generate a static VoxelShape method. (+1 more)

### Community 20 - "CarcassLoot.java"
Cohesion: 0.39
Nodes (5): CarcassLoot, BlockPos, LootTable, ResourceKey, ServerLevel

### Community 21 - "CarcassDefinition.java"
Cohesion: 0.40
Nodes (4): BlockState, EntityType, Item, VoxelShape

### Community 23 - ".batHanging"
Cohesion: 0.18
Nodes (16): CarcassBlock, BlockEntity, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext (+8 more)

### Community 37 - "CLAUDE.md — slaughter_hide (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — slaughter_hide (26.2), Prioridad de instrucciones, Workflow del mod

### Community 38 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **59 isolated node(s):** `Workflow del mod`, `Prioridad de instrucciones`, `Add`, `Add`, `Fix` (+54 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **4 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `CarcassDefinition` connect `CarcassDefinition` to `CarcassBlockEntity`, `ModBlocks`, `Carcasses`, `Block`, `DrainedCarcassBlock`, `CorpseBlock`, `CarcassDeathHandler.java`, `SkeletonBlock.java`, `TrophyHeadBlock.java`, `CarcassDefinition.java`, `.dropTable`, `.batHanging`, `.buildCAMEL`, `.buildChicken`, `.buildDOLPHIN`, `.buildDONKEY`, `.buildFox`, `.buildGoat`, `.buildHOGLIN`, `.buildMULE`, `.buildOCELOT`, `.buildPANDA`, `.buildPOLAR_BEAR`, `.buildRabbit`, `.buildWolf`, `.pigHanging`, `.stageMap`?**
  _High betweenness centrality (0.369) - this node is a cross-community bridge._
- **Why does `Carcasses` connect `Carcasses` to `VoxelShape`, `CarcassBlockEntity`, `CarcassDefinition`, `CarcassBlock`, `CarcassDeathHandler.java`, `.buildCAMEL`, `.buildChicken`, `.buildDOLPHIN`, `.buildDONKEY`, `.buildFox`, `.buildGoat`, `.buildHOGLIN`, `.buildMULE`, `.buildOCELOT`, `.buildPANDA`, `.buildPOLAR_BEAR`, `.buildRabbit`, `.buildWolf`, `.pigHanging`, `.spiderLying`, `.horseHanging`, `.axolotlLying`, `.spiderHead`, `.spiderLying`, `.drownedHanging`, `.endermiteHanging`, `.horseHanging`, `.magma_cubeHanging`, `.phantomLying`, `.pufferfishHanging`, `.shulkerHanging`, `.zombie_horseHanging`, `.zombieHanging`?**
  _High betweenness centrality (0.282) - this node is a cross-community bridge._
- **Why does `CorpseBlockEntity` connect `CorpseBlockEntity` to `CarcassBlockEntity`?**
  _High betweenness centrality (0.041) - this node is a cross-community bridge._
- **What connects `Format a box tuple as box(x1, y1, z1, x2, y2, z2)`, `Format a list of boxes for a facing direction.`, `Generate a static VoxelShape method.` to the rest of the system?**
  _71 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `VoxelShape` be split into smaller, more focused modules?**
  _Cohesion score 0.0626858842370167 - nodes in this community are weakly interconnected._
- **Should `CarcassBlockEntity` be split into smaller, more focused modules?**
  _Cohesion score 0.06370543541788427 - nodes in this community are weakly interconnected._
- **Should `Carcasses` be split into smaller, more focused modules?**
  _Cohesion score 0.05580693815987934 - nodes in this community are weakly interconnected._