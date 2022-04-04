// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui;

import org.lwjgl.input.Mouse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Collection;
import mod.hitlerhax.module.Category;
import java.util.ArrayList;
import mod.hitlerhax.ui.clickgui.settingeditor.SettingController;
import net.minecraft.client.gui.GuiScreen;

public class ClickGuiController extends GuiScreen
{
    private int scrollOffset;
    public static final ClickGuiController INSTANCE;
    public SettingController settingController;
    public static ArrayList<ClickGuiFrame> frames;
    
    public ClickGuiController() {
        if (ClickGuiController.frames == null || ClickGuiController.frames.isEmpty()) {
            ClickGuiController.frames = new ArrayList<ClickGuiFrame>();
            int offset = 0;
            for (final Category category : Category.values()) {
                ClickGuiController.frames.add(new ClickGuiFrame(category, 2 + offset, 20));
                offset += 76;
            }
        }
    }
    
    public static ArrayList<ModuleButton> getButtons() {
        final ArrayList<ModuleButton> mb = new ArrayList<ModuleButton>();
        for (final ClickGuiFrame f : ClickGuiController.frames) {
            mb.addAll(f.moduleButtons);
        }
        return mb;
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.scroller();
        for (final ClickGuiFrame frame : ClickGuiController.frames) {
            frame.render(mouseX, mouseY);
        }
    }
    
    protected void func_73864_a(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            for (final ClickGuiFrame frame : ClickGuiController.frames) {
                frame.onClick(mouseX, mouseY, mouseButton);
            }
        }
        if (mouseButton == 1) {
            this.settingController = null;
            for (final ModuleButton m : getButtons()) {
                if (mouseX >= m.x && mouseX <= m.x + m.width && mouseY >= m.y && mouseY <= m.y + m.height) {
                    this.settingController = new SettingController(mouseX, mouseY);
                }
            }
            if (this.settingController != null) {
                this.field_146297_k.func_147108_a((GuiScreen)this.settingController);
            }
        }
    }
    
    public boolean func_73868_f() {
        super.func_73868_f();
        return false;
    }
    
    protected void func_73869_a(final char typedChar, final int keyCode) throws IOException {
        super.func_73869_a(typedChar, keyCode);
        if (keyCode == 1) {
            for (final ClickGuiFrame c : ClickGuiController.frames) {
                c.y -= this.scrollOffset;
                for (final ModuleButton moduleButton : c.moduleButtons) {
                    final ModuleButton m = moduleButton;
                    moduleButton.y -= this.scrollOffset;
                }
            }
            this.scrollOffset = 0;
        }
    }
    
    private void scroller() {
        int lowest = Integer.MAX_VALUE;
        int highest = Integer.MIN_VALUE;
        for (final ClickGuiFrame c : ClickGuiController.frames) {
            if (c.y < lowest) {
                lowest = c.y;
            }
            if (c.y + c.height > highest) {
                highest = c.y + c.height;
            }
        }
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0 && highest > 0) {
            for (final ClickGuiFrame c2 : ClickGuiController.frames) {
                c2.y -= 10;
                for (final ModuleButton m : c2.moduleButtons) {
                    m.y -= 10;
                }
            }
            this.scrollOffset -= 10;
        }
        else if (dWheel > 0 && lowest <= this.field_146295_m) {
            for (final ClickGuiFrame c2 : ClickGuiController.frames) {
                c2.y += 10;
                for (final ModuleButton m : c2.moduleButtons) {
                    m.y += 10;
                }
            }
            this.scrollOffset += 10;
        }
    }
    
    public void func_146281_b() {
        for (final ClickGuiFrame frame : ClickGuiController.frames) {
            frame.onGuiClosed();
        }
    }
    
    static {
        INSTANCE = new ClickGuiController();
    }
}
