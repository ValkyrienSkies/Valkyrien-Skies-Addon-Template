package org.valkyrienskies.vs_template

import net.minecraftforge.fml.common.Mod

@Mod(VSTemplateMod.MOD_ID)
class VSTemplateModForge {
    init {
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.
        VSTemplateMod.LOG.info("Hello Forge world!")
        VSTemplateMod.init()
    }
}