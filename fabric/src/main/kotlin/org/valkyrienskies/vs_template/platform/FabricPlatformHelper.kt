package org.valkyrienskies.vs_template.platform

import com.google.auto.service.AutoService
import net.fabricmc.loader.api.FabricLoader

@AutoService(PlatformHelper::class)
class FabricPlatformHelper : PlatformHelper {
    override val platformName: String
        get() = "Fabric"

    override fun isModLoaded(modId: String): Boolean {
        return FabricLoader.getInstance().isModLoaded(modId)
    }

    override val isDevelopmentEnvironment: Boolean
        get() = FabricLoader.getInstance().isDevelopmentEnvironment
}