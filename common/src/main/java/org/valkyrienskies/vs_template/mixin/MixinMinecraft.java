package org.valkyrienskies.vs_template.mixin;

import org.valkyrienskies.vs_template.VSTemplateMod;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        
        VSTemplateMod.LOG.info("This line is printed by an example mod common mixin!");
        VSTemplateMod.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}