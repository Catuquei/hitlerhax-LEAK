// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import mod.hitlerhax.module.modules.utilities.BookWriter;
import mod.hitlerhax.Main;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Final;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.GuiScreenBook;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.GuiScreen;

@Mixin({ GuiScreenBook.class })
public abstract class HtlrMixinGuiScreenBook extends GuiScreen
{
    @Shadow
    private int field_146484_x;
    @Shadow
    private NBTTagList field_146483_y;
    @Shadow
    @Final
    private boolean field_146475_i;
    
    @Inject(method = { "initGui" }, at = { @At("RETURN") })
    public void onInitGui(final CallbackInfo info) {
        if (this.field_146475_i && Main.moduleManager.getModule("BookWriter").toggled) {
            ((BookWriter)Main.moduleManager.getModule("BookWriter")).addButtons(this.field_146294_l);
            this.func_189646_b(((BookWriter)Main.moduleManager.getModule("BookWriter")).read);
            this.func_189646_b(((BookWriter)Main.moduleManager.getModule("BookWriter")).reset);
            this.func_189646_b(((BookWriter)Main.moduleManager.getModule("BookWriter")).write);
        }
    }
    
    @Inject(method = { "actionPerformed" }, at = { @At("RETURN") })
    protected void actionPerformed(final GuiButton button, final CallbackInfo info) {
        if (Main.moduleManager.getModule("BookWriter").toggled) {
            ((BookWriter)Main.moduleManager.getModule("BookWriter")).actionPerformed(button, this.field_146483_y, this.field_146484_x);
        }
    }
    
    @Inject(method = { "drawScreen" }, at = { @At("RETURN") })
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo info) {
        if (Main.moduleManager.getModule("BookWriter").toggled) {
            ((BookWriter)Main.moduleManager.getModule("BookWriter")).drawScreen(this.field_146475_i, this.field_146294_l, this.field_146295_m, mouseX, mouseY, partialTicks);
        }
    }
    
    @Inject(method = { "updateButtons" }, at = { @At("HEAD") })
    private void updateButtons(final CallbackInfo info) {
        if (Main.moduleManager.getModule("BookWriter").toggled) {
            ((BookWriter)Main.moduleManager.getModule("BookWriter")).updateButtons(this.field_146475_i);
        }
    }
}
