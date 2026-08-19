replacements = {
    # buildCAMEL
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? camelHanging(state) : camelLying(state),\n                Carcasses::camelLying,\n                Carcasses::camelHead,\n                Carcasses::camelHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? camelSkeletonHanging(state) : camelSkeletonLying(state),\n                // camel drops raw_camel_meat\n                java.util.List.of(net.minecraft.world.item.Items.BONE));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? camelHanging(state) : camelLying(state),\n                Carcasses::camelLying,\n                Carcasses::camelHead,\n                Carcasses::camelHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? camelSkeletonHanging(state) : camelSkeletonLying(state),\n                // camel drops raw_camel_meat\n                java.util.List.of(ModItems.RAW_CAMEL_MEAT));",

    # buildDONKEY
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? donkeyHanging(state) : donkeyLying(state),\n                Carcasses::donkeyLying,\n                Carcasses::donkeyHead,\n                Carcasses::donkeyHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? donkeySkeletonHanging(state) : donkeySkeletonLying(state),\n                // donkey drops raw_donkey_steak\n                java.util.List.of(ModItems.RAW_DONKEY_STEAK));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? donkeyHanging(state) : donkeyLying(state),\n                Carcasses::donkeyLying,\n                Carcasses::donkeyHead,\n                Carcasses::donkeyHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? donkeySkeletonHanging(state) : donkeySkeletonLying(state),\n                // donkey drops raw_donkey_steak\n                java.util.List.of(ModItems.RAW_DONKEY_STEAK));",

    # buildMULE
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? muleHanging(state) : muleLying(state),\n                Carcasses::muleLying,\n                Carcasses::muleHead,\n                Carcasses::muleHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? muleSkeletonHanging(state) : muleSkeletonLying(state),\n                // mule drops raw_mule_steak\n                java.util.List.of(ModItems.RAW_MULE_STEAK));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? muleHanging(state) : muleLying(state),\n                Carcasses::muleLying,\n                Carcasses::muleHead,\n                Carcasses::muleHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? muleSkeletonHanging(state) : muleSkeletonLying(state),\n                // mule drops raw_mule_steak\n                java.util.List.of(ModItems.RAW_MULE_STEAK));",

    # buildOCELOT
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? ocelotHanging(state) : ocelotLying(state),\n                Carcasses::ocelotLying,\n                Carcasses::ocelotHead,\n                Carcasses::ocelotHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? ocelotSkeletonHanging(state) : ocelotSkeletonLying(state),\n                // ocelot drops raw_ocelot_meat\n                java.util.List.of(ModItems.RAW_OCELOT_MEAT));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? ocelotHanging(state) : ocelotLying(state),\n                Carcasses::ocelotLying,\n                Carcasses::ocelotHead,\n                Carcasses::ocelotHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? ocelotSkeletonHanging(state) : ocelotSkeletonLying(state),\n                // ocelot drops raw_ocelot_meat\n                java.util.List.of(ModItems.RAW_OCELOT_MEAT));",

    # buildPANDA
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pandaHanging(state) : pandaLying(state),\n                Carcasses::pandaLying,\n                Carcasses::pandaHead,\n                Carcasses::pandaHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pandaSkeletonHanging(state) : pandaSkeletonLying(state),\n                // panda drops raw_panda_steak\n                java.util.List.of(ModItems.RAW_PANDA_STEAK));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pandaHanging(state) : pandaLying(state),\n                Carcasses::pandaLying,\n                Carcasses::pandaHead,\n                Carcasses::pandaHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? pandaSkeletonHanging(state) : pandaSkeletonLying(state),\n                // panda drops raw_panda_steak\n                java.util.List.of(ModItems.RAW_PANDA_STEAK));",

    # buildPOLAR_BEAR
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? polar_bearHanging(state) : polar_bearLying(state),\n                Carcasses::polar_bearLying,\n                Carcasses::polar_bearHead,\n                Carcasses::polar_bearHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? polar_bearSkeletonHanging(state) : polar_bearSkeletonLying(state),\n                // polar_bear drops raw_polar_bear_meat\n                java.util.List.of(ModItems.RAW_POLAR_BEAR_MEAT));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? polar_bearHanging(state) : polar_bearLying(state),\n                Carcasses::polar_bearLying,\n                Carcasses::polar_bearHead,\n                Carcasses::polar_bearHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? polar_bearSkeletonHanging(state) : polar_bearSkeletonLying(state),\n                // polar_bear drops raw_polar_bear_meat\n                java.util.List.of(ModItems.RAW_POLAR_BEAR_MEAT));",

    # buildHOGLIN
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? hoglinHanging(state) : hoglinLying(state),\n                Carcasses::hoglinLying,\n                Carcasses::hoglinHead,\n                Carcasses::hoglinHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? hoglinSkeletonHanging(state) : hoglinSkeletonLying(state),\n                // hoglin drops raw_hoglin_chunk\n                java.util.List.of(ModItems.RAW_HOGLIN_CHUNK));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? hoglinHanging(state) : hoglinLying(state),\n                Carcasses::hoglinLying,\n                Carcasses::hoglinHead,\n                Carcasses::hoglinHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? hoglinSkeletonHanging(state) : hoglinSkeletonLying(state),\n                // hoglin drops raw_hoglin_chunk\n                java.util.List.of(ModItems.RAW_HOGLIN_CHUNK));",

    # buildZOGLIN
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? zoglinHanging(state) : zoglinLying(state),\n                Carcasses::zoglinLying,\n                Carcasses::zoglinHead,\n                Carcasses::zoglinHeadMount,\n                Carcasses::zoglinLying,  // no skeleton\n                // zoglin drops minecraft:rotten_flesh\n                java.util.List.of(net.minecraft.world.item.Items.ROTTEN_FLESH));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? zoglinHanging(state) : zoglinLying(state),\n                Carcasses::zoglinLying,\n                Carcasses::zoglinHead,\n                Carcasses::zoglinHeadMount,\n                Carcasses::zoglinLying,  // no skeleton\n                // zoglin drops minecraft:rotten_flesh\n                java.util.List.of(net.minecraft.world.item.Items.ROTTEN_FLESH));",

    # buildDOLPHIN
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? dolphinHanging(state) : dolphinLying(state),\n                Carcasses::dolphinLying,\n                Carcasses::dolphinHead,\n                Carcasses::dolphinHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? dolphinSkeletonHanging(state) : dolphinSkeletonLying(state),\n                // dolphin drops raw_dolphin_meat\n                java.util.List.of(ModItems.RAW_DOLPHIN_MEAT));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? dolphinHanging(state) : dolphinLying(state),\n                Carcasses::dolphinLying,\n                Carcasses::dolphinHead,\n                Carcasses::dolphinHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? dolphinSkeletonHanging(state) : dolphinSkeletonLying(state),\n                // dolphin drops raw_dolphin_meat\n                java.util.List.of(ModItems.RAW_DOLPHIN_MEAT));",

    # buildBAT
    "true,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? batHanging(state) : batLying(state),\n                Carcasses::batLying,\n                Carcasses::batHead,\n                Carcasses::batHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? batSkeletonHanging(state) : batSkeletonLying(state),\n                // bat drops raw_bat_meat\n                java.util.List.of(net.minecraft.world.item.Items.BONE));": 
    "true,\n                3,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? batHanging(state) : batLying(state),\n                Carcasses::batLying,\n                Carcasses::batHead,\n                Carcasses::batHeadMount,\n                state -> state.getValue(CarcassBlockProperty.BLOCKSTATE) == 1 ? batSkeletonHanging(state) : batSkeletonLying(state),\n                // bat drops raw_bat_meat\n                java.util.List.of(net.minecraft.world.item.Items.BONE));",

    # buildSILVERFISH - no skin
    "true,\n                Carcasses::silverfishHanging,\n                Carcasses::silverfishLying,\n                Carcasses::silverfishHead,\n                Carcasses::silverfishHeadMount,\n                Carcasses::silverfishLying,  // no skeleton\n                // silverfish drops raw_silverfish_chunks\n                java.util.List.of(net.minecraft.world.item.Items.BONE));": 
    "false,\n                3,\n                Carcasses::silverfishHanging,\n                Carcasses::silverfishLying,\n                Carcasses::silverfishHead,\n                Carcasses::silverfishHeadMount,\n                Carcasses::silverfishLying,  // no skeleton\n                // silverfish drops raw_silverfish_chunks\n                java.util.List.of(net.minecraft.world.item.Items.BONE));",

    # buildENDERMITE - no skin
    "true,\n                Carcasses::endermiteHanging,\n                Carcasses::endermiteLying,\n                Carcasses::endermiteHead,\n                Carcasses::endermiteHeadMount,\n                Carcasses::endermiteLying,  // no skeleton\n                // endermite drops raw_endermite_chunks\n                java.util.List.of(net.minecraft.world.item.Items.BONE));": 
    "false,\n                3,\n                Carcasses::endermiteHanging,\n                Carcasses::endermiteLying,\n                Carcasses::endermiteHead,\n                Carcasses::endermiteHeadMount,\n                Carcasses::endermiteLying,  // no skeleton\n                // endermite drops raw_endermite_chunks\n                java.util.List.of(net.minecraft.world.item.Items.BONE));",

    # buildBEE - 2 cuts, no skin
    "false,\n                Carcasses::beeHanging,\n                Carcasses::beeLying,\n                Carcasses::beeHead,\n                Carcasses::beeLying,  // no head mount\n                Carcasses::beeLying,  // no skeleton\n                // bee drops honey_stomach\n                java.util.List.of(net.minecraft.world.item.Items.HONEY_BOTTLE));": 
    "false,\n                2,\n                Carcasses::beeHanging,\n                Carcasses::beeLying,\n                Carcasses::beeHead,\n                Carcasses::beeLying,  // no head mount\n                Carcasses::beeLying,  // no skeleton\n                // bee drops honey_stomach\n                java.util.List.of(net.minecraft.world.item.Items.HONEY_BOTTLE));",
}

with open("Carcasses.java", "r") as f:
    content = f.read()

for old, new in replacements.items():
    if old in content:
        content = content.replace(old, new)
        print("Replaced one")
    else:
        print("NOT FOUND:", old[:80])

with open("Carcasses.java", "w") as f:
    f.write(content)

print("Done!")