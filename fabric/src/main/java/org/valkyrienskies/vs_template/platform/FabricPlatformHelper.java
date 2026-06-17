package org.valkyrienskies.vs_template.platform;

import com.google.auto.service.AutoService;
import net.fabricmc.loader.api.FabricLoader;

@AutoService(PlatformHelper.class)
public class FabricPlatformHelper implements PlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
