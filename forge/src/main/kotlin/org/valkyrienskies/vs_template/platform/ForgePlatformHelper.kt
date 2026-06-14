package org.valkyrienskies.vs_template.platform

import com.fueledbycaffeine.autoservice.AutoService
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.loading.FMLLoader

@AutoService
open class ForgePlatformHelper : PlatformHelper {
    override val platformName: String
        get() = "Forge"

    override fun isModLoaded(modId: String): Boolean {
        return ModList.get().isLoaded(modId)
    }

    override val isDevelopmentEnvironment: Boolean
        get() = !FMLLoader.isProduction()
}