package org.valkyrienskies.vs_template.platform.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.valkyrienskies.vs_template.VSTemplateMod
import java.util.*
import java.util.function.Supplier

/**
 * This class was written by Electrisoma for VisceraLib.
 * It is licensed as MIT No attribution.
 * Many thanks to Electrisoma for providing this helper.
 */
object ServiceHelper {
    private val LOG: Logger = LoggerFactory.getLogger(VSTemplateMod.MOD_ID + "/ServiceHelper")

    /**
     * Loads an optional service that can exist or not.
     * Only logs that it doesn't exist.
     * No runtime exception.
     *
     * @param T The type of the service interface.
     * @param clazz The Class object of the service interface
     * @return An Optional containing the service instance if found, otherwise an empty Optional.
     */
    fun <T> find(clazz: Class<T>): Optional<T> {
        val loadedService = ServiceLoader
            .load(clazz)
            .findFirst()

        if (loadedService.isPresent) {
            LOG.debug(
                "Found implementation {} for service {}",
                loadedService.get().javaClass.getName(), clazz.getName()
            )
        } else {
            LOG.debug(
                "No implementation found for optional service {}",
                clazz.getName()
            )
        }

        return loadedService
    }

    /**
     * Loads a mandatory service that must exist.
     * Otherwise, it will throw a runtime exception.
     *
     * @param T The type of the service interface.
     * @param clazz The Class object of the service interface.
     * @return The first available implementation of the service.
     * @throws IllegalStateException if no implementation is found.
     */
    fun <T> load(clazz: Class<T>): T {
        return find(clazz).orElseThrow(Supplier { IllegalStateException("Failed to load mandatory service for class: " + clazz.getName()) }
        )
    }
}