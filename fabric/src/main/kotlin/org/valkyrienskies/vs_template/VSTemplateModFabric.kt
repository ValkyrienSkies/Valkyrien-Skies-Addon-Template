package org.valkyrienskies.vs_template

import net.fabricmc.api.ModInitializer

class VSTemplateModFabric : ModInitializer {
    override fun onInitialize() {
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.

        VSTemplateMod.LOG.info("Hello Fabric world!")
        VSTemplateMod.init()
    }
}