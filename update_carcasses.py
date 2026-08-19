# Update all build methods in Carcasses.java to add hasSkin and numCuts parameters

replacements = {
    # buildGoat - hasSkin=true, numCuts=3
    '''private static CarcassDefinition buildGoat() {
        return new CarcassDefinition(
                "goat",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:goat")))
                        .value(),
                true,
                true,
                true,
                // Goat drops mutton like sheep
                java.util.List.of(net.minecraft.world.item.Items.MUTTON));''': 
    '''private static CarcassDefinition buildGoat() {
        return new CarcassDefinition(
                "goat",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:goat")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // Goat drops mutton like sheep
                java.util.List.of(net.minecraft.world.item.Items.MUTTON));''',

    # buildFox
    '''private static CarcassDefinition buildFox() {
        return new CarcassDefinition(
                "fox",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:fox")))
                        .value(),
                true,
                true,
                true,
                // Fox drops raw_fox_meat
                java.util.List.of(net.minecraft.world.item.Items.SWEET_BERRIES)); // Fox doesn't have a vanilla meat drop, using sweet berries as placeholder''': 
    '''private static CarcassDefinition buildFox() {
        return new CarcassDefinition(
                "fox",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:fox")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // Fox drops raw_fox_meat
                java.util.List.of(net.minecraft.world.item.Items.SWEET_BERRIES)); // Fox doesn't have a vanilla meat drop, using sweet berries as placeholder''',

    # buildWolf
    '''private static CarcassDefinition buildWolf() {
        return new CarcassDefinition(
                "wolf",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:wolf")))
                        .value(),
                true,
                true,
                true,
                // Wolf drops wolf_pelt
                java.util.List.of(net.minecraft.world.item.Items.BONE));''': 
    '''private static CarcassDefinition buildWolf() {
        return new CarcassDefinition(
                "wolf",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:wolf")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // Wolf drops wolf_pelt
                java.util.List.of(net.minecraft.world.item.Items.BONE));''',

    # buildCAMEL
    '''private static CarcassDefinition buildCAMEL() {
        return new CarcassDefinition(
                "camel",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:camel")))
                        .value(),
                true,
                true,
                true,
                // camel drops raw_camel_meat
                java.util.List.of(ModItems.RAW_CAMEL_MEAT));''': 
    '''private static CarcassDefinition buildCAMEL() {
        return new CarcassDefinition(
                "camel",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:camel")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // camel drops raw_camel_meat
                java.util.List.of(ModItems.RAW_CAMEL_MEAT));''',

    # buildDONKEY
    '''private static CarcassDefinition buildDONKEY() {
        return new CarcassDefinition(
                "donkey",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:donkey")))
                        .value(),
                true,
                true,
                true,
                // donkey drops raw_donkey_steak
                java.util.List.of(ModItems.RAW_DONKEY_STEAK));''': 
    '''private static CarcassDefinition buildDONKEY() {
        return new CarcassDefinition(
                "donkey",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:donkey")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // donkey drops raw_donkey_steak
                java.util.List.of(ModItems.RAW_DONKEY_STEAK));''',

    # buildMULE
    '''private static CarcassDefinition buildMULE() {
        return new CarcassDefinition(
                "mule",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:mule")))
                        .value(),
                true,
                true,
                true,
                // mule drops raw_mule_steak
                java.util.List.of(ModItems.RAW_MULE_STEAK));''': 
    '''private static CarcassDefinition buildMULE() {
        return new CarcassDefinition(
                "mule",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:mule")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // mule drops raw_mule_steak
                java.util.List.of(ModItems.RAW_MULE_STEAK));''',

    # buildOCELOT
    '''private static CarcassDefinition buildOCELOT() {
        return new CarcassDefinition(
                "ocelot",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:ocelot")))
                        .value(),
                true,
                true,
                true,
                // ocelot drops raw_ocelot_meat
                java.util.List.of(ModItems.RAW_OCELOT_MEAT));''': 
    '''private static CarcassDefinition buildOCELOT() {
        return new CarcassDefinition(
                "ocelot",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:ocelot")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // ocelot drops raw_ocelot_meat
                java.util.List.of(ModItems.RAW_OCELOT_MEAT));''',

    # buildPANDA
    '''private static CarcassDefinition buildPANDA() {
        return new CarcassDefinition(
                "panda",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:panda")))
                        .value(),
                true,
                true,
                true,
                // panda drops raw_panda_steak
                java.util.List.of(ModItems.RAW_PANDA_STEAK));''': 
    '''private static CarcassDefinition buildPANDA() {
        return new CarcassDefinition(
                "panda",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:panda")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // panda drops raw_panda_steak
                java.util.List.of(ModItems.RAW_PANDA_STEAK));''',

    # buildPOLAR_BEAR
    '''private static CarcassDefinition buildPOLAR_BEAR() {
        return new CarcassDefinition(
                "polar_bear",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:polar_bear")))
                        .value(),
                true,
                true,
                true,
                // polar_bear drops raw_polar_bear_meat
                java.util.List.of(ModItems.RAW_POLAR_BEAR_MEAT));''': 
    '''private static CarcassDefinition buildPOLAR_BEAR() {
        return new CarcassDefinition(
                "polar_bear",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:polar_bear")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // polar_bear drops raw_polar_bear_meat
                java.util.List.of(ModItems.RAW_POLAR_BEAR_MEAT));''',

    # buildHOGLIN
    '''private static CarcassDefinition buildHOGLIN() {
        return new CarcassDefinition(
                "hoglin",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:hoglin")))
                        .value(),
                true,
                true,
                true,
                // hoglin drops raw_hoglin_chunk
                java.util.List.of(ModItems.RAW_HOGLIN_CHUNK));''': 
    '''private static CarcassDefinition buildHOGLIN() {
        return new CarcassDefinition(
                "hoglin",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:hoglin")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // hoglin drops raw_hoglin_chunk
                java.util.List.of(ModItems.RAW_HOGLIN_CHUNK));''',

    # buildZOGLIN
    '''private static CarcassDefinition buildZOGLIN() {
        return new CarcassDefinition(
                "zoglin",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:zoglin")))
                        .value(),
                true,
                true,
                false,
                // zoglin drops minecraft:rotten_flesh
                java.util.List.of(net.minecraft.world.item.Items.ROTTEN_FLESH));''': 
    '''private static CarcassDefinition buildZOGLIN() {
        return new CarcassDefinition(
                "zoglin",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:zoglin")))
                        .value(),
                true,
                true,
                false,
                true,
                3,
                // zoglin drops minecraft:rotten_flesh
                java.util.List.of(net.minecraft.world.item.Items.ROTTEN_FLESH));''',

    # buildDOLPHIN
    '''private static CarcassDefinition buildDOLPHIN() {
        return new CarcassDefinition(
                "dolphin",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:dolphin")))
                        .value(),
                true,
                true,
                true,
                // dolphin drops raw_dolphin_meat
                java.util.List.of(ModItems.RAW_DOLPHIN_MEAT));''': 
    '''private static CarcassDefinition buildDOLPHIN() {
        return new CarcassDefinition(
                "dolphin",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:dolphin")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // dolphin drops raw_dolphin_meat
                java.util.List.of(ModItems.RAW_DOLPHIN_MEAT));''',

    # buildBAT
    '''private static CarcassDefinition buildBAT() {
        return new CarcassDefinition(
                "bat",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:bat")))
                        .value(),
                true,
                true,
                true,
                // bat drops raw_bat_meat
                java.util.List.of(net.minecraft.world.item.Items.BONE));''': 
    '''private static CarcassDefinition buildBAT() {
        return new CarcassDefinition(
                "bat",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:bat")))
                        .value(),
                true,
                true,
                true,
                true,
                3,
                // bat drops raw_bat_meat
                java.util.List.of(net.minecraft.world.item.Items.BONE));''',

    # buildSILVERFISH - no skin
    '''private static CarcassDefinition buildSILVERFISH() {
        return new CarcassDefinition(
                "silverfish",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:silverfish")))
                        .value(),
                true,
                true,
                false,
                Carcasses::silverfishHanging,
                Carcasses::silverfishLying,
                Carcasses::silverfishHead,
                Carcasses::silverfishHeadMount,
                Carcasses::silverfishLying,  // no skeleton
                // silverfish drops raw_silverfish_chunks
                java.util.List.of(net.minecraft.world.item.Items.BONE));''': 
    '''private static CarcassDefinition buildSILVERFISH() {
        return new CarcassDefinition(
                "silverfish",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:silverfish")))
                        .value(),
                true,
                true,
                false,
                false,
                3,
                Carcasses::silverfishHanging,
                Carcasses::silverfishLying,
                Carcasses::silverfishHead,
                Carcasses::silverfishHeadMount,
                Carcasses::silverfishLying,  // no skeleton
                // silverfish drops raw_silverfish_chunks
                java.util.List.of(net.minecraft.world.item.Items.BONE));''',

    # buildENDERMITE - no skin
    '''private static CarcassDefinition buildENDERMITE() {
        return new CarcassDefinition(
                "endermite",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:endermite")))
                        .value(),
                true,
                true,
                false,
                Carcasses::endermiteHanging,
                Carcasses::endermiteLying,
                Carcasses::endermiteHead,
                Carcasses::endermiteHeadMount,
                Carcasses::endermiteLying,  // no skeleton
                // endermite drops raw_endermite_chunks
                java.util.List.of(net.minecraft.world.item.Items.BONE));''': 
    '''private static CarcassDefinition buildENDERMITE() {
        return new CarcassDefinition(
                "endermite",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:endermite")))
                        .value(),
                true,
                true,
                false,
                false,
                3,
                Carcasses::endermiteHanging,
                Carcasses::endermiteLying,
                Carcasses::endermiteHead,
                Carcasses::endermiteHeadMount,
                Carcasses::endermiteLying,  // no skeleton
                // endermite drops raw_endermite_chunks
                java.util.List.of(net.minecraft.world.item.Items.BONE));''',

    # buildBEE - 2 cuts, no skin
    '''private static CarcassDefinition buildBEE() {
        return new CarcassDefinition(
                "bee",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:bee")))
                        .value(),
                true,
                false,
                false,
                Carcasses::beeHanging,
                Carcasses::beeLying,
                Carcasses::beeHead,
                Carcasses::beeLying,  // no head mount
                Carcasses::beeLying,  // no skeleton
                // bee drops honey_stomach
                java.util.List.of(net.minecraft.world.item.Items.HONEY_BOTTLE));''': 
    '''private static CarcassDefinition buildBEE() {
        return new CarcassDefinition(
                "bee",
                BuiltInRegistries.ENTITY_TYPE.getOrThrow(
                                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.parse("minecraft:bee")))
                        .value(),
                true,
                false,
                false,
                false,
                2,
                Carcasses::beeHanging,
                Carcasses::beeLying,
                Carcasses::beeHead,
                Carcasses::beeLying,  // no head mount
                Carcasses::beeLying,  // no skeleton
                // bee drops honey_stomach
                java.util.List.of(net.minecraft.world.item.Items.HONEY_BOTTLE));''',
}

with open("src/main/java/com/skd/slaughterhide/Carcasses.java", "r") as f:
    content = f.read()

for old, new in replacements.items():
    if old in content:
        content = content.replace(old, new)
        print(f"Replaced one")
    else:
        print(f"NOT FOUND: {old[:50]}...")

with open("src/main/java/com/skd/slaughterhide/Carcasses.java", "w") as f:
    f.write(content)

print("Done!")