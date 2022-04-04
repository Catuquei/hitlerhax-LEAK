// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui;

import java.awt.Color;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.util.render.ColorUtil;
import org.lwjgl.opengl.GL11;
import mod.hitlerhax.module.Module;

public class ModuleButton
{
    public int x;
    public int y;
    public final int width;
    public final int height;
    public final Module module;
    final ClickGuiFrame parent;
    boolean increasing;
    
    public ModuleButton(final Module module, final int x, final int y, final ClickGuiFrame parent) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = parent.width;
        this.height = 14;
    }
    
    public void draw(final int mouseX, final int mouseY) {
        if (this.module.toggled) {
            GL11.glDisable(2884);
            GL11.glDisable(3553);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
            GL11.glBegin(7);
            final Color c = ColorUtil.getRainbow(1000, 255);
            GL11.glColor4f(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, 0.6f);
            GL11.glVertex2f((float)this.x, (float)this.y);
            GL11.glVertex2f((float)(this.x + this.width), (float)this.y);
            GL11.glVertex2f((float)(this.x + this.width), (float)(this.y + this.height));
            GL11.glVertex2f((float)this.x, (float)(this.y + this.height));
            GL11.glEnd();
        }
        else {
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
        }
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        FontUtils.drawString(this.module.getName(), this.x + 2, this.y + 2, new ColorUtil(255, 255, 255));
    }
    
    public void onClick(final int x, final int y, final int button) {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height) {
            this.module.toggle();
        }
    }
}
