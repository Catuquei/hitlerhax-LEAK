// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui.settingeditor;

import org.lwjgl.opengl.GL11;
import java.util.Iterator;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.ui.guiitems.HtlrButton;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.Main;
import mod.hitlerhax.util.render.ColorUtil;
import java.util.ArrayList;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.util.Globals;

public class SettingFrame implements Globals
{
    final int x;
    int y;
    final int width;
    int height;
    final Module module;
    final ArrayList<SettingButton> settingButtons;
    final KeybindButton kbButton;
    final ColorUtil kbColor;
    
    public SettingFrame(final Module m, final int x, final int y, final ColorUtil kbColor) {
        this.x = x;
        this.y = y;
        this.width = 400;
        this.height = 0;
        this.module = m;
        this.kbColor = kbColor;
        this.settingButtons = new ArrayList<SettingButton>();
        int offsetY = 14;
        this.kbButton = new KeybindButton(this.module, this.module.getKey(), x, y + offsetY, this, this.kbColor);
        offsetY += 14;
        if (Main.settingManager.getSettingsByMod(m) != null) {
            for (final Setting s : Main.settingManager.getSettingsByMod(m)) {
                this.settingButtons.add(new SettingButton(this.module, s, x, y + offsetY, this));
                offsetY += 14;
            }
        }
        for (final HtlrButton b : m.buttons) {
            b.x = x;
            b.y = y + offsetY;
            b.width = FontUtils.getStringWidth(b.displayString);
            offsetY += 14;
        }
        this.height = offsetY;
    }
    
    public void render(final int mouseX, final int mouseY) {
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glLineWidth(1.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.4f);
        GL11.glBegin(7);
        GL11.glVertex2f((float)this.x, (float)this.y);
        GL11.glVertex2f((float)this.x, (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width + 100), (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width + 100), (float)this.y);
        GL11.glEnd();
        GL11.glColor3f(0.0f, 200.0f, 255.0f);
        GL11.glBegin(2);
        GL11.glVertex2f((float)this.x, (float)this.y);
        GL11.glVertex2f((float)this.x, (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width + 100), (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width + 100), (float)this.y);
        GL11.glEnd();
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        final ColorUtil moduleNameColor = new ColorUtil(255, 255, 255);
        if (Main.moduleManager.getModule("ClientFont").toggled) {
            FontUtils.drawString(this.module.getName(), this.x + 2, this.y + 2, moduleNameColor);
        }
        else {
            FontUtils.drawString(this.module.getName(), this.x + 2, this.y + 2, moduleNameColor);
        }
        this.kbButton.draw(mouseX, mouseY);
        for (final SettingButton s : this.settingButtons) {
            s.draw(mouseX, mouseY);
        }
        for (final HtlrButton b : this.module.buttons) {
            if (Main.moduleManager.getModule("ClientFont").toggled) {
                FontUtils.drawString(b.displayString, b.x + 2, b.y + 2, new ColorUtil(255, 255, 255));
            }
            else {
                FontUtils.drawString(b.displayString, b.x + 2, b.y + 2, new ColorUtil(255, 255, 255));
            }
        }
    }
    
    public void onClick(final int x, final int y, final int button) {
        for (final SettingButton s : this.settingButtons) {
            s.onClick(x, y, button);
        }
        for (final HtlrButton g : this.module.buttons) {
            g.mousePressed(x, y);
            g.mouseReleased(x, y);
        }
        this.kbButton.onClick(x, y, button);
    }
}
