// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.Minecraft;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.Main;
import mod.hitlerhax.util.Timer;
import mod.hitlerhax.module.modules.utilities.Reconnect;
import net.minecraft.client.gui.GuiButton;

public class AutoReconnectButton extends GuiButton
{
    private final Reconnect Mod;
    private final Timer timer;
    private long reconnectTimer;
    
    public AutoReconnectButton(final int buttonId, final int x, final int y, final String buttonText) {
        super(buttonId, x, y, buttonText);
        (this.timer = new Timer()).reset();
        this.Mod = (Reconnect)Main.moduleManager.getModule("Reconnect");
        this.reconnectTimer = ((IntSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Reconnect"), "Timer")).value;
    }
    
    public void func_191745_a(final Minecraft mc, final int mouseX, final int mouseY, final float partialTicks) {
        super.func_191745_a(mc, mouseX, mouseY, partialTicks);
        if (this.field_146125_m && ((Reconnect)Main.moduleManager.getModule("Reconnect")).serverData != null) {
            this.reconnectTimer = ((IntSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Reconnect"), "Timer")).value;
            if (this.Mod.toggled && this.timer.getPassedTimeMs() < this.reconnectTimer) {
                this.field_146126_j = "AutoReconnect " + (this.reconnectTimer - this.timer.getPassedTimeMs());
            }
            else if (!this.Mod.toggled) {
                this.field_146126_j = "Enable AutoReconnect";
            }
            else {
                mc.func_147108_a((GuiScreen)new GuiConnecting((GuiScreen)null, mc, ((Reconnect)Main.moduleManager.getModule("Reconnect")).serverData));
            }
            if (this.timer.getPassedTimeMs() + 100L >= this.reconnectTimer && this.Mod.toggled && ((Reconnect)Main.moduleManager.getModule("Reconnect")).serverData != null) {
                mc.func_147108_a((GuiScreen)new GuiConnecting((GuiScreen)null, mc, ((Reconnect)Main.moduleManager.getModule("Reconnect")).serverData));
            }
        }
    }
    
    public void Clicked() {
        this.Mod.toggle();
        this.timer.reset();
    }
}
