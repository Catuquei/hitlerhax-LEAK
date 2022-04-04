// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui;

import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.util.render.ColorUtil;
import org.lwjgl.opengl.GL11;
import java.util.Iterator;
import java.util.Objects;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.Main;
import java.util.ArrayList;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.util.Globals;

public class ClickGuiFrame implements Globals
{
    boolean dragging;
    int draggingX;
    int draggingY;
    int prevX;
    int prevY;
    public int x;
    public int y;
    final int width;
    int height;
    public final Category category;
    final ArrayList<ModuleButton> moduleButtons;
    
    public ClickGuiFrame(final Category category, final int x, final int y) {
        this.dragging = false;
        this.x = x;
        this.y = y;
        this.width = 73;
        this.height = 0;
        this.category = category;
        this.moduleButtons = new ArrayList<ModuleButton>();
        int offsetY = 14;
        for (final Module module : Main.moduleManager.getModulesByCategory(category)) {
            if (!Objects.equals(module.name, "Esp2dHelper")) {
                if (Objects.equals(module.name, "FootEXP")) {
                    continue;
                }
                this.moduleButtons.add(new ModuleButton(module, x, y + offsetY, this));
                offsetY += 14;
            }
        }
        this.height = offsetY;
    }
    
    public void render(final int mouseX, final int mouseY) {
        if (this.dragging) {
            this.x = mouseX - this.draggingX;
            this.y = mouseY - this.draggingY;
            for (final ModuleButton moduleButton2 : this.moduleButtons) {
                final ModuleButton b = moduleButton2;
                moduleButton2.x += this.x - this.prevX;
                final ModuleButton moduleButton3 = b;
                moduleButton3.y += this.y - this.prevY;
            }
            this.prevX = this.x;
            this.prevY = this.y;
        }
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
        GL11.glVertex2f((float)(this.x + this.width), (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width), (float)this.y);
        GL11.glEnd();
        for (final ModuleButton moduleButton : this.moduleButtons) {
            moduleButton.draw(mouseX, mouseY);
        }
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        if (!this.dragging) {
            GL11.glColor4f(0.0f, 0.8f, 0.8f, 0.8f);
        }
        else {
            GL11.glColor4f(0.8f, 0.8f, 0.8f, 0.8f);
        }
        GL11.glBegin(7);
        GL11.glVertex2f((float)this.x, (float)this.y);
        GL11.glVertex2f((float)(this.x + this.width), (float)this.y);
        GL11.glVertex2f((float)(this.x + this.width), (float)(this.y + 5 + ClickGuiFrame.mc.field_71466_p.field_78288_b));
        GL11.glVertex2f((float)this.x, (float)(this.y + 5 + ClickGuiFrame.mc.field_71466_p.field_78288_b));
        GL11.glEnd();
        GL11.glColor3f(0.0f, 1.0f, 1.0f);
        GL11.glBegin(2);
        GL11.glVertex2f((float)this.x, (float)this.y);
        GL11.glVertex2f((float)this.x, (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width), (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width), (float)this.y);
        GL11.glEnd();
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        final ColorUtil categoryColor = new ColorUtil(255, 255, 255);
        FontUtils.drawString(this.category.toString(), this.x + 2, this.y + 2, categoryColor);
    }
    
    public void onClick(final int x, final int y, final int button) {
        for (final ModuleButton moduleButton : this.moduleButtons) {
            moduleButton.onClick(x, y, button);
        }
        if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + 5 + ClickGuiFrame.mc.field_71466_p.field_78288_b) {
            this.dragging = !this.dragging;
            this.draggingX = x - this.x;
            this.draggingY = y - this.y;
            this.prevX = this.x;
            this.prevY = this.y;
        }
        Main.config.Save();
    }
    
    public void onGuiClosed() {
        if (this.dragging) {
            this.dragging = !this.dragging;
            this.draggingX = this.x - this.x;
            this.draggingY = this.y - this.y;
            this.prevX = this.x;
            this.prevY = this.y;
            Main.config.Save();
        }
    }
}
