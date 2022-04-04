// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import mod.hitlerhax.Main;
import mod.hitlerhax.setting.settings.BooleanSetting;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import mod.hitlerhax.ui.HtlrSplashScreen;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import javax.annotation.Nullable;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Minecraft.class })
public abstract class HtlrMixinMinecraft
{
    @Shadow
    public EntityPlayerSP field_71439_g;
    @Shadow
    public PlayerControllerMP field_71442_b;
    
    @Shadow
    public abstract void func_147108_a(@Nullable final GuiScreen p0);
    
    @Inject(method = { "displayGuiScreen" }, at = { @At("HEAD") })
    private void displayGuiScreen(final GuiScreen screen, final CallbackInfo ci) {
        if (screen instanceof GuiMainMenu) {
            this.func_147108_a(new HtlrSplashScreen());
        }
    }
    
    @Inject(method = { "runTick()V" }, at = { @At("RETURN") })
    private void runTick(final CallbackInfo callbackInfo) {
        if (Minecraft.func_71410_x().field_71462_r instanceof GuiMainMenu && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "Menu")).enabled) {
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new HtlrSplashScreen());
        }
    }
}
