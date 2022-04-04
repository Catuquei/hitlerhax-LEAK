// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import mod.hitlerhax.module.modules.render.ExtraTab;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.Main;
import net.minecraft.client.network.NetworkPlayerInfo;
import java.util.List;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.Gui;

@Mixin({ GuiPlayerTabOverlay.class })
public class HtlrMixinGuiPlayerTabOverlay extends Gui
{
    @Redirect(method = { "renderPlayerlist" }, at = @At(value = "INVOKE", target = "Ljava/util/List;subList(II)Ljava/util/List;", remap = false))
    public List<NetworkPlayerInfo> subListHook(final List<NetworkPlayerInfo> list, final int fromIndex, final int toIndex) {
        return list.subList(fromIndex, Main.moduleManager.getModule("ExtraTab").isToggled() ? Math.min(((IntSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("ExtraTab"), "Size")).value, list.size()) : toIndex);
    }
    
    @Inject(method = { "getPlayerName" }, at = { @At("HEAD") }, cancellable = true)
    public void getPlayerNameHook(final NetworkPlayerInfo networkPlayerInfoIn, final CallbackInfoReturnable<String> info) {
        if (Main.moduleManager.getModule("ExtraTab").isToggled()) {
            info.setReturnValue(ExtraTab.getPlayerName(networkPlayerInfoIn));
        }
    }
}
