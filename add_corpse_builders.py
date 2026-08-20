import re

with open("G:/Proyectos/Mods_Minecraft/slaughter_hide/neoforge/26.2/src/main/java/com/skd/slaughterhide/Carcasses.java", "r") as f:
    content = f.read()

corpse_builders = '''

// ===== CORPSE BLOCK BUILDERS (humanoid mobs with organ harvesting) =====
private static CarcassDefinition buildZOMBIE() {
    return new CarcassDefinition(
            "zombie",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:zombie")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::zombieHanging,
            Carcasses::zombieLying,
            Carcasses::zombieHead,
            Carcasses::zombieHeadMount,
            Carcasses::zombieLying,  // no skeleton
            // zombie drops rotten_flesh
            java.util.List.of(net.minecraft.world.item.Items.ROTTEN_FLESH),
            Carcasses::zombieCorpse);
}

private static CarcassDefinition buildSKELETON() {
    return new CarcassDefinition(
            "skeleton",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:skeleton")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::skeletonHanging,
            Carcasses::skeletonLying,
            Carcasses::skeletonHead,
            Carcasses::skeletonHeadMount,
            Carcasses::skeletonLying,  // no skeleton
            // skeleton drops bone
            java.util.List.of(net.minecraft.world.item.Items.BONE),
            Carcasses::skeletonCorpse);
}

private static CarcassDefinition buildDROWNED() {
    return new CarcassDefinition(
            "drowned",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:drowned")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::drownedHanging,
            Carcasses::drownedLying,
            Carcasses::drownedHead,
            Carcasses::drownedHeadMount,
            Carcasses::drownedLying,  // no skeleton
            // drowned drops rotten_flesh
            java.util.List.of(net.minecraft.world.item.Items.ROTTEN_FLESH),
            Carcasses::drownedCorpse);
}

private static CarcassDefinition buildHUSK() {
    return new CarcassDefinition(
            "husk",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:husk")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::huskHanging,
            Carcasses::huskLying,
            Carcasses::huskHead,
            Carcasses::huskHeadMount,
            Carcasses::huskLying,  // no skeleton
            // husk drops rotten_flesh
            java.util.List.of(net.minecraft.world.item.Items.ROTTEN_FLESH),
            Carcasses::huskCorpse);
}

private static CarcassDefinition buildVINDICATOR() {
    return new CarcassDefinition(
            "vindicator",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:vindicator")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::vindicatorHanging,
            Carcasses::vindicatorLying,
            Carcasses::vindicatorHead,
            Carcasses::vindicatorHeadMount,
            Carcasses::vindicatorLying,  // no skeleton
            // vindicator drops emerald
            java.util.List.of(net.minecraft.world.item.Items.EMERALD),
            Carcasses::vindicatorCorpse);
}

private static CarcassDefinition buildEVOKER() {
    return new CarcassDefinition(
            "evoker",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:evoker")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::evokerHanging,
            Carcasses::evokerLying,
            Carcasses::evokerHead,
            Carcasses::evokerHeadMount,
            Carcasses::evokerLying,  // no skeleton
            // evoker drops emerald
            java.util.List.of(net.minecraft.world.item.Items.EMERALD),
            Carcasses::evokerCorpse);
}

private static CarcassDefinition buildWITCH() {
    return new CarcassDefinition(
            "witch",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:witch")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::witchHanging,
            Carcasses::witchLying,
            Carcasses::witchHead,
            Carcasses::witchHeadMount,
            Carcasses::witchLying,  // no skeleton
            // witch drops potion
            java.util.List.of(net.minecraft.world.item.Items.POTION),
            Carcasses::witchCorpse);
}

private static CarcassDefinition buildPIGLIN() {
    return new CarcassDefinition(
            "piglin",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:piglin")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::piglinHanging,
            Carcasses::piglinLying,
            Carcasses::piglinHead,
            Carcasses::piglinHeadMount,
            Carcasses::piglinLying,  // no skeleton
            // piglin drops gold
            java.util.List.of(net.minecraft.world.item.Items.GOLD_INGOT),
            Carcasses::piglinCorpse);
}

private static CarcassDefinition buildPIGLIN_BRUTE() {
    return new CarcassDefinition(
            "piglin_brute",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:piglin_brute")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::piglinBruteHanging,
            Carcasses::piglinBruteLying,
            Carcasses::piglinBruteHead,
            Carcasses::piglinBruteHeadMount,
            Carcasses::piglinBruteLying,  // no skeleton
            // piglin_brute drops gold
            java.util.List.of(net.minecraft.world.item.Items.GOLD_INGOT),
            Carcasses::piglinBruteCorpse);
}

private static CarcassDefinition buildRAVAGER() {
    return new CarcassDefinition(
            "ravager",
            BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:ravager")))
                    .value(),
            true,
            true,
            false,
            true,
            3,
            Carcasses::ravagerHanging,
            Carcasses::ravagerLying,
            Carcasses::ravagerHead,
            Carcasses::ravagerHeadMount,
            Carcasses::ravagerLying,  // no skeleton
            // ravager drops saddle
            java.util.List.of(net.minecraft.world.item.Items.SADDLE),
            Carcasses::ravagerCorpse);
}
}

'''

with open("G:/Proyectos/Mods_Minecraft/slaughter_hide/neoforge/26.2/src/main/java/com/skd/slaughterhide/Carcasses.java", "r") as f:
    content = f.read()

# Find the last closing brace
last_brace = content.rfind("}")
if last_brace > 0:
    new_content = content[:last_brace] + "\n" + corpse_builders + "\n" + content[last_brace:]
    with open("G:/Proyectos/Mods_Minecraft/slaughter_hide/neoforge/26.2/src/main/java/com/skd/slaughterhide/Carcasses.java", "w") as f:
        f.write(new_content)
    print("Done!")
else:
    print("Could not find closing brace")