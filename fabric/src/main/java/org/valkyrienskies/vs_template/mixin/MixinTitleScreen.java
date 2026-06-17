package org.valkyrienskies.vs_template.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.valkyrienskies.vs_template.VSTemplateMod;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        
        VSTemplateMod.getLOG().info("This line is printed by an example mod mixin from Fabric!");
        VSTemplateMod.getLOG().info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}