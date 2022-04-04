// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui.settingeditor;

import mod.hitlerhax.ui.clickgui.ClickGuiController;
import mod.hitlerhax.Client;
import mod.hitlerhax.util.font.FontUtils;
import org.lwjgl.input.Keyboard;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.module.Module;

public class KeybindButton
{
    final int x;
    int y;
    final int width;
    final int height;
    final Module module;
    final SettingFrame parent;
    final ColorUtil color;
    
    public KeybindButton(final Module module, final int key, final int x, final int y, final SettingFrame parent, final ColorUtil c) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = parent.width;
        this.height = 14;
        this.color = c;
    }
    
    public void draw(final int mouseX, final int mouseY) {
        FontUtils.drawString("Keybind: " + Keyboard.getKeyName(this.module.getKey()), this.x + 2, this.y + 2, this.color);
    }
    
    public void onClick(final int x, final int y, final int button) {
        if (x >= this.x && x <= this.x + FontUtils.getStringWidth("Keybind: ") + 50 && y >= this.y - 5 && y <= this.y + this.height - 5) {
            if (!Client.getNextKeyPressForKeybinding) {
                Client.waitForKeybindKeypress(this.module);
                ClickGuiController.INSTANCE.settingController.refresh(true);
            }
            else {
                Client.stopWaitForKeybindPress();
                ClickGuiController.INSTANCE.settingController.refresh(false);
            }
        }
    }
}
