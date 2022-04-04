// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.client;

import net.minecraft.client.gui.GuiScreen;
import mod.hitlerhax.ui.clickgui.ClickGuiController;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class ClickGui extends Module
{
    public ClickGui() {
        super("ClickGUI", "GUI interface to interact with modules", Category.CLIENT);
    }
    
    @Override
    protected void onEnable() {
        ClickGui.mc.func_147108_a((GuiScreen)ClickGuiController.INSTANCE);
        this.toggled = false;
    }
}
