package org.valkyrienskies.vs_template.platform

import org.valkyrienskies.vs_template.platform.services.ServiceHelper

interface PlatformHelper {
    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    val platformName: String

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    fun isModLoaded(modId: String): Boolean

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    val isDevelopmentEnvironment: Boolean

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    fun getEnvironmentName(): String {
        return if (this.isDevelopmentEnvironment) "development" else "production"
    }

    companion object {
        val INSTANCE: PlatformHelper = ServiceHelper.load<PlatformHelper>(PlatformHelper::class.java)
    }
}