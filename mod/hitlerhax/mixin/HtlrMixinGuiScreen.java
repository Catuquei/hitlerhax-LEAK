// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.GuiButton;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.Gui;

@Mixin({ GuiScreen.class })
public abstract class HtlrMixinGuiScreen extends Gui implements GuiYesNoCallback
{
    @Shadow
    public List<GuiButton> field_146292_n;
    @Shadow
    public int field_146294_l;
    @Shadow
    public int field_146295_m;
    @Shadow
    public FontRenderer field_146289_q;
    
    @Shadow
    protected abstract <T extends GuiButton> T func_189646_b(final T p0);
    
    public int getWidth() {
        return this.field_146294_l;
    }
    
    public int getHeight() {
        return this.field_146295_m;
    }
}
