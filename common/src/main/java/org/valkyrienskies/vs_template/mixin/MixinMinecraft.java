package org.valkyrienskies.vs_template.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.valkyrienskies.vs_template.VSTemplateMod;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        
        VSTemplateMod.getLOG().info("This line is printed by an example mod common mixin!");
        VSTemplateMod.getLOG().info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}