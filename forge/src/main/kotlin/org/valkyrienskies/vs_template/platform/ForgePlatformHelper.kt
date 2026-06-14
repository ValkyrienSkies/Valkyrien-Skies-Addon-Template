package org.valkyrienskies.vs_template.platform

import com.google.auto.service.AutoService
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.loading.FMLLoader

@AutoService(PlatformHelper::class)
class ForgePlatformHelper : PlatformHelper {
    override val platformName: String
        get() = "Forge"

    override fun isModLoaded(modId: String): Boolean {
        return ModList.get().isLoaded(modId)
    }

    override val isDevelopmentEnvironment: Boolean
        get() = !FMLLoader.isProduction()
}