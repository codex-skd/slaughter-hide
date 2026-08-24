# Graph Report - 26.2  (2026-08-24)

## Corpus Check
- 2140 files · ~809,948 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 1338 nodes · 3252 edges · 99 communities (96 shown, 3 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 18 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `f6ea1c28`
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
- CarcassBlock
- CorpseBlock
- CarcassDeathHandler.java
- Changelog — Slaughter & Hide
- SlaughterHide.java
- port_assets.py
- TrophyHeadBlock.java
- Flujo de trabajo — Slaughter & Hide (NeoForge)
- CurseForge — Variables del proyecto
- generate_carcasses.py
- CarcassLoot.java
- CarcassDefinition.java
- CLAUDE.md — slaughter_hide (26.2)
- gradlew
- .stageMap
- .phantomHanging
- .spiderHead
- .axolotlLying
- CarcassBlock.java
- BloodSplatterBlock
- .phantomLying
- BoneBarrelBlock
- FloorStandingSignBlock
- .zombie_horseHanging
- .zombieHanging
- ModBlocks
- .ravagerHanging
- .silverfishHanging
- SkeletonBlock.java
- SaltFormationBaseBlock
- ModItems
- .handle
- DioriteBrickSlabBlock.java
- DioriteBrickStairsBlock.java
- DioriteBrickWallBlock.java
- ClingFilmBlock.java
- CookedBloodSausagesBlock.java
- CookedSausagesBlock.java
- DeepslateSulfurOreBlock.java
- DioriteBricksBlock.java
- DragonScaleBlock.java
- RawBloodSausagesBlock.java
- RawSausagesBlock.java
- SaltBlock.java
- SaltFormationFrustumBlock.java
- SaltFormationMiddleBlock.java
- SaltFormationTipBlock.java
- SulfurOreBlock.java
- .handle
- ServerWorkScheduler.java
- ButcherToolItem.java
- .onRightClickBlock
- ModItemTags.java

## God Nodes (most connected - your core abstractions)
1. `Carcasses` - 308 edges
2. `CarcassDefinition` - 123 edges
3. `ModBlocks` - 55 edges
4. `CorpseBlockEntity` - 26 edges
5. `Roadmap — Slaughter & Hide (port de Butchery 5.2 → NeoForge 26.2)` - 22 edges
6. `DrainedCarcassBlock` - 21 edges
7. `PlasticSheetBlock` - 21 edges
8. `PlasticSheetCornerBlock` - 21 edges
9. `BloodSplatterBlock` - 20 edges
10. `CarcassBlock` - 20 edges

## Surprising Connections (you probably didn't know these)
- `CarcassBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/CarcassBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java
- `DrainedCarcassBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/DrainedCarcassBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java
- `HeadMountBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/HeadMountBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java
- `RavagerHeadBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/RavagerHeadBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java
- `SkeletonBlock` --references--> `CarcassDefinition`  [EXTRACTED]
  src/main/java/com/skd/slaughterhide/block/SkeletonBlock.java → src/main/java/com/skd/slaughterhide/CarcassDefinition.java

## Import Cycles
- None detected.

## Communities (99 total, 3 thin omitted)

### Community 1 - "CarcassBlockEntity"
Cohesion: 0.16
Nodes (8): BlockEntity, ClientboundBlockEntityDataPacket, CompoundTag, Provider, CarcassBlockEntity, BlockPos, BlockState, Override

### Community 2 - "ModBlocks"
Cohesion: 0.14
Nodes (10): Item, HookPlacementHandler, Item, RightClickBlock, SubscribeEvent, Item, RightClickBlock, SubscribeEvent (+2 more)

### Community 4 - "CarcassDefinition"
Cohesion: 0.04
Nodes (5): CarcassDefinition, BlockState, EntityType, Item, VoxelShape

### Community 5 - "CorpseBlockEntity"
Cohesion: 0.09
Nodes (25): AbstractContainerMenu, BlockEntityType, Component, Container, CreativeModeTab, Inventory, MenuProvider, NonNullList (+17 more)

### Community 6 - "Block"
Cohesion: 0.20
Nodes (13): HookBlock, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Direction (+5 more)

### Community 7 - "Roadmap — Slaughter & Hide (port de Butchery 5.2 → NeoForge 26.2)"
Cohesion: 0.05
Nodes (37): Arquitectura idiomática propuesta (reemplaza ~330 clases de bloque + ~293 procedures), Assets (`assets/butchery/`), `block/` (456 clases), Catálogo Fase 0 — series vs mecánicas únicas (COMPLETADO 2026-08-15), Contexto y tamaño real del mod original, Código (clases `.class`, sin contar internas `$`), Datos (`data/butchery/`), Decisiones confirmadas por el usuario (2026-08-15) (+29 more)

### Community 8 - "DrainedCarcassBlock"
Cohesion: 0.17
Nodes (17): Items, DrainedCarcassBlock, BlockEntity, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder (+9 more)

### Community 9 - "CarcassBlock"
Cohesion: 0.10
Nodes (26): HeadMountBlock, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Mirror (+18 more)

### Community 10 - "CorpseBlock"
Cohesion: 0.19
Nodes (16): EntityBlock, CorpseBlock, BlockEntity, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder (+8 more)

### Community 11 - "CarcassDeathHandler.java"
Cohesion: 0.26
Nodes (6): LivingDeathEvent, EntityType, CarcassDeathHandler, Entity, ServerLevel, SubscribeEvent

### Community 12 - "Changelog — Slaughter & Hide"
Cohesion: 0.07
Nodes (26): [0.0.0-beta.1] - 2026-08-14, [0.0.0-beta.2] - 2026-08-15, [0.0.0-beta.30] - 2026-08-24, [0.0.0-beta.31] - 2026-08-24, [0.0.0-beta.3] - 2026-08-15, [0.0.0-beta.4] - 2026-08-18, [0.0.0-beta.5] - 2026-08-18, [0.0.0-beta.6] - 2026-08-18 (+18 more)

### Community 13 - "SlaughterHide.java"
Cohesion: 0.32
Nodes (5): ConfigValue, FMLConstructModEvent, ModConfigSpec, Builder, SlaughterHideConfig

### Community 14 - "port_assets.py"
Cohesion: 0.15
Nodes (18): apply_panda_rename(), apply_polar_bear_rename(), copy_file_with_remap(), find_matching_files(), find_matching_files_exact(), fix_drained_blockstate(), fix_fresh_blockstate(), main() (+10 more)

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

### Community 37 - "CLAUDE.md — slaughter_hide (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — slaughter_hide (26.2), Prioridad de instrucciones, Workflow del mod

### Community 38 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 40 - ".stageMap"
Cohesion: 0.09
Nodes (31): HorizontalDirectionalBlock, ButcherStatueBlock, Block, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder (+23 more)

### Community 41 - ".phantomHanging"
Cohesion: 0.16
Nodes (18): BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Direction, EnumProperty (+10 more)

### Community 42 - ".spiderHead"
Cohesion: 0.16
Nodes (18): BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Direction, EnumProperty (+10 more)

### Community 44 - ".axolotlLying"
Cohesion: 0.18
Nodes (16): BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Direction, Entity (+8 more)

### Community 61 - "CarcassBlock.java"
Cohesion: 0.18
Nodes (16): CarcassBlock, BlockEntity, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext (+8 more)

### Community 62 - "BloodSplatterBlock"
Cohesion: 0.19
Nodes (16): AttachFace, FaceAttachedHorizontalDirectionalBlock, BloodSplatterBlock, Block, BlockGetter, BlockPlaceContext, BlockPos, BlockState (+8 more)

### Community 63 - ".phantomLying"
Cohesion: 0.15
Nodes (10): Block, Identifier, IEventBus, Mod, SoundEvent, RavagerHeadMountBlock, BloodSplatterInteractionHandler, RightClickBlock (+2 more)

### Community 64 - "BoneBarrelBlock"
Cohesion: 0.17
Nodes (15): BoneBarrelBlock, Block, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext (+7 more)

### Community 65 - "FloorStandingSignBlock"
Cohesion: 0.17
Nodes (15): FloorStandingSignBlock, Block, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext (+7 more)

### Community 66 - ".zombie_horseHanging"
Cohesion: 0.17
Nodes (15): Block, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Direction (+7 more)

### Community 67 - ".zombieHanging"
Cohesion: 0.17
Nodes (15): Block, BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Direction (+7 more)

### Community 69 - "ModBlocks"
Cohesion: 0.22
Nodes (3): DeferredBlock, Block, ModBlocks

### Community 70 - ".ravagerHanging"
Cohesion: 0.20
Nodes (13): BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Direction, EnumProperty (+5 more)

### Community 71 - ".silverfishHanging"
Cohesion: 0.23
Nodes (11): BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Mirror, Override (+3 more)

### Community 72 - "SkeletonBlock.java"
Cohesion: 0.23
Nodes (11): BlockGetter, BlockPlaceContext, BlockPos, BlockState, Builder, CollisionContext, Mirror, Override (+3 more)

### Community 73 - "SaltFormationBaseBlock"
Cohesion: 0.23
Nodes (11): BlockGetter, BlockPlaceContext, BlockPos, BlockState, BooleanProperty, Builder, CollisionContext, FluidState (+3 more)

### Community 74 - "ModItems"
Cohesion: 0.37
Nodes (3): DeferredItem, Item, ModItems

### Community 76 - ".handle"
Cohesion: 0.44
Nodes (5): CarcassBleedingHandler, BlockPos, Level, Player, ServerLevel

### Community 77 - "DioriteBrickSlabBlock.java"
Cohesion: 0.27
Nodes (8): SlabBlock, DioriteBrickSlabBlock, BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape

### Community 78 - "DioriteBrickStairsBlock.java"
Cohesion: 0.27
Nodes (8): DioriteBrickStairsBlock, BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape, StairBlock

### Community 79 - "DioriteBrickWallBlock.java"
Cohesion: 0.27
Nodes (8): DioriteBrickWallBlock, BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape, WallBlock

### Community 80 - "ClingFilmBlock.java"
Cohesion: 0.33
Nodes (7): ClingFilmBlock, BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape

### Community 81 - "CookedBloodSausagesBlock.java"
Cohesion: 0.33
Nodes (7): CookedBloodSausagesBlock, BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape

### Community 82 - "CookedSausagesBlock.java"
Cohesion: 0.33
Nodes (7): CookedSausagesBlock, BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape

### Community 83 - "DeepslateSulfurOreBlock.java"
Cohesion: 0.33
Nodes (7): DeepslateSulfurOreBlock, BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape

### Community 84 - "DioriteBricksBlock.java"
Cohesion: 0.33
Nodes (7): DioriteBricksBlock, BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape

### Community 85 - "DragonScaleBlock.java"
Cohesion: 0.33
Nodes (7): DragonScaleBlock, BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape

### Community 86 - "RawBloodSausagesBlock.java"
Cohesion: 0.33
Nodes (7): BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape, RawBloodSausagesBlock

### Community 87 - "RawSausagesBlock.java"
Cohesion: 0.33
Nodes (7): BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape, RawSausagesBlock

### Community 88 - "SaltBlock.java"
Cohesion: 0.33
Nodes (7): BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape, SaltBlock

### Community 89 - "SaltFormationFrustumBlock.java"
Cohesion: 0.33
Nodes (7): BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape, SaltFormationFrustumBlock

### Community 90 - "SaltFormationMiddleBlock.java"
Cohesion: 0.33
Nodes (7): BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape, SaltFormationMiddleBlock

### Community 91 - "SaltFormationTipBlock.java"
Cohesion: 0.33
Nodes (7): BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape, SaltFormationTipBlock

### Community 92 - "SulfurOreBlock.java"
Cohesion: 0.33
Nodes (7): BlockGetter, BlockPos, BlockState, CollisionContext, Override, VoxelShape, SulfurOreBlock

### Community 93 - ".handle"
Cohesion: 0.31
Nodes (5): Blocks, CarcassCutupHandler, BlockPos, Level, Player

### Community 94 - "ServerWorkScheduler.java"
Cohesion: 0.33
Nodes (5): IntObjectPair, Post, SubscribeEvent, ServerWorkScheduler, TickTask

### Community 95 - "ButcherToolItem.java"
Cohesion: 0.33
Nodes (6): ItemInstance, ItemStackTemplate, ButcherToolItem, Override, TagKey, ToolMaterial

### Community 96 - ".onRightClickBlock"
Cohesion: 0.33
Nodes (3): CarcassInteractionHandler, RightClickBlock, SubscribeEvent

### Community 97 - "ModItemTags.java"
Cohesion: 0.60
Nodes (3): Item, TagKey, ModItemTags

## Knowledge Gaps
- **69 isolated node(s):** `Workflow del mod`, `Prioridad de instrucciones`, `Add`, `Fix`, `Known issues` (+64 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **3 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `CarcassDefinition` connect `CarcassDefinition` to `CarcassBlockEntity`, `ModBlocks`, `Carcasses`, `DrainedCarcassBlock`, `CarcassBlock`, `CarcassDeathHandler.java`, `TrophyHeadBlock.java`, `CarcassDefinition.java`, `.batHanging`, `.buildCAMEL`, `.buildChicken`, `.buildDOLPHIN`, `.buildDONKEY`, `.buildFox`, `.buildGoat`, `.buildHOGLIN`, `.buildMULE`, `.buildOCELOT`, `.buildPANDA`, `.buildPOLAR_BEAR`, `.buildRabbit`, `.buildWolf`, `.pigHanging`, `CarcassBlock.java`, `.phantomLying`, `ModBlocks`, `.silverfishHanging`, `SkeletonBlock.java`, `ModItems`, `.handle`, `.handle`, `.onRightClickBlock`?**
  _High betweenness centrality (0.240) - this node is a cross-community bridge._
- **Why does `Carcasses` connect `Carcasses` to `VoxelShape`, `CarcassDefinition`, `CarcassDeathHandler.java`, `.batHanging`, `.buildCAMEL`, `.buildChicken`, `.buildDOLPHIN`, `.buildDONKEY`, `.buildFox`, `.buildGoat`, `.buildHOGLIN`, `.buildMULE`, `.buildOCELOT`, `.buildPANDA`, `.buildPOLAR_BEAR`, `.buildRabbit`, `.buildWolf`, `.pigHanging`, `.huskHanging`, `.phantomLying`, `.onRightClickBlock`?**
  _High betweenness centrality (0.169) - this node is a cross-community bridge._
- **Why does `ModBlocks` connect `ModBlocks` to `Block`, `DrainedCarcassBlock`, `CarcassBlock`, `CorpseBlock`, `.stageMap`, `.phantomHanging`, `.spiderHead`, `.axolotlLying`, `CarcassBlock.java`, `BloodSplatterBlock`, `.phantomLying`, `BoneBarrelBlock`, `FloorStandingSignBlock`, `.zombie_horseHanging`, `.zombieHanging`, `.ravagerHanging`, `.silverfishHanging`, `SaltFormationBaseBlock`, `DioriteBrickSlabBlock.java`, `DioriteBrickStairsBlock.java`, `DioriteBrickWallBlock.java`, `ClingFilmBlock.java`, `CookedBloodSausagesBlock.java`, `CookedSausagesBlock.java`, `DeepslateSulfurOreBlock.java`, `DioriteBricksBlock.java`, `DragonScaleBlock.java`, `RawBloodSausagesBlock.java`, `RawSausagesBlock.java`, `SaltBlock.java`, `SaltFormationFrustumBlock.java`, `SaltFormationMiddleBlock.java`, `SaltFormationTipBlock.java`, `SulfurOreBlock.java`, `.handle`?**
  _High betweenness centrality (0.053) - this node is a cross-community bridge._
- **What connects `Format a box tuple as box(x1, y1, z1, x2, y2, z2)`, `Format a list of boxes for a facing direction.`, `Generate a static VoxelShape method.` to the rest of the system?**
  _81 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `VoxelShape` be split into smaller, more focused modules?**
  _Cohesion score 0.04698412698412698 - nodes in this community are weakly interconnected._
- **Should `ModBlocks` be split into smaller, more focused modules?**
  _Cohesion score 0.1368421052631579 - nodes in this community are weakly interconnected._
- **Should `Carcasses` be split into smaller, more focused modules?**
  _Cohesion score 0.05052790346907994 - nodes in this community are weakly interconnected._