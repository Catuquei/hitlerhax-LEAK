// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.Minecraft;
import mod.hitlerhax.module.modules.utilities.Reconnect;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import mod.hitlerhax.Main;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import mod.hitlerhax.ui.AutoReconnectButton;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.GuiDisconnected;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { GuiDisconnected.class }, priority = Integer.MAX_VALUE)
public abstract class HtlrMixinGuiDisconnected extends HtlrMixinGuiScreen
{
    @Shadow
    private int field_175353_i;
    private AutoReconnectButton ReconnectingButton;
    
    @Inject(method = { "initGui" }, at = { @At("RETURN") })
    public void initGui(final CallbackInfo info) {
        if (Main.moduleManager.getModule("Reconnect").toggled) {
            this.field_146292_n.clear();
            this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 100, Math.min(this.field_146295_m / 2 + this.field_175353_i / 2 + this.field_146289_q.field_78288_b, this.field_146295_m - 30), I18n.func_135052_a("gui.toMenu", new Object[0])));
            this.field_146292_n.add(new GuiButton(421, this.field_146294_l / 2 - 100, Math.min(this.field_146295_m / 2 + this.field_175353_i / 2 + this.field_146289_q.field_78288_b + 20, this.field_146295_m - 10), "Reconnect"));
            this.field_146292_n.add(this.ReconnectingButton = new AutoReconnectButton(420, this.field_146294_l / 2 - 100, Math.min(this.field_146295_m / 2 + this.field_175353_i / 2 + this.field_146289_q.field_78288_b + 40, this.field_146295_m + 10), "AutoReconnect"));
        }
    }
    
    @Inject(method = { "actionPerformed" }, at = { @At("RETURN") })
    protected void actionPerformed(final GuiButton button, final CallbackInfo info) {
        if (Main.moduleManager.getModule("Reconnect").toggled) {
            if (button.field_146127_k == 420) {
                this.ReconnectingButton.Clicked();
            }
            else if (button.field_146127_k == 421 && ((Reconnect)Main.moduleManager.getModule("Reconnect")).serverData != null) {
                Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiConnecting((GuiScreen)null, Minecraft.func_71410_x(), ((Reconnect)Main.moduleManager.getModule("Reconnect")).serverData));
            }
        }
    }
    
    @Inject(method = { "drawScreen" }, at = { @At("RETURN") })
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo info) {
        if (Main.moduleManager.getModule("Reconnect").toggled && this.field_146292_n.size() > 3) {
            this.field_146292_n.clear();
            this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 100, Math.min(this.field_146295_m / 2 + this.field_175353_i / 2 + this.field_146289_q.field_78288_b, this.field_146295_m - 30), I18n.func_135052_a("gui.toMenu", new Object[0])));
            this.field_146292_n.add(new GuiButton(421, this.field_146294_l / 2 - 100, Math.min(this.field_146295_m / 2 + this.field_175353_i / 2 + this.field_146289_q.field_78288_b + 30, this.field_146295_m - 10), "Reconnect"));
            this.field_146292_n.add(this.ReconnectingButton = new AutoReconnectButton(420, this.field_146294_l / 2 - 100, Math.min(this.field_146295_m / 2 + this.field_175353_i / 2 + this.field_146289_q.field_78288_b + 60, this.field_146295_m + 10), "AutoReconnect"));
        }
    }
}
