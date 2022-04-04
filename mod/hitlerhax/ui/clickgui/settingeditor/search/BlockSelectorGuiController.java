// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui.settingeditor.search;

import org.lwjgl.input.Mouse;
import mod.hitlerhax.Main;
import mod.hitlerhax.module.modules.render.Search;
import net.minecraft.block.Block;
import java.io.IOException;
import java.util.Iterator;
import mod.hitlerhax.ui.guiitems.HtlrTextField;
import mod.hitlerhax.setting.settings.SearchBlockSelectorSetting;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class BlockSelectorGuiController extends GuiScreen
{
    public ArrayList<BlockButton> blocks;
    public final boolean colorSettings;
    public final GuiScreen lastScreen;
    public int scrollOffset;
    public BlockSelectorGuiFrame frame;
    public final SearchBlockSelectorSetting setting;
    public HtlrTextField searchField;
    
    public BlockSelectorGuiController(final GuiScreen lastScreen, final boolean colorSettings, final SearchBlockSelectorSetting setting) {
        this.scrollOffset = 0;
        this.lastScreen = lastScreen;
        this.colorSettings = colorSettings;
        this.setting = setting;
        if (setting != null && setting.parent != null) {
            this.frame = new BlockSelectorGuiFrame(20, 20, this, "");
        }
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.scroller();
        if (this.frame == null) {
            this.func_146281_b();
            return;
        }
        this.frame.render(mouseX, mouseY);
    }
    
    public boolean func_73868_f() {
        super.func_73868_f();
        return false;
    }
    
    protected void func_73869_a(final char typedChar, final int keyCode) throws IOException {
        if (this.frame == null) {
            return;
        }
        super.func_73869_a(typedChar, keyCode);
        if (keyCode == 1) {
            final BlockSelectorGuiFrame frame = this.frame;
            frame.y -= this.scrollOffset;
            for (final BlockButton b : this.blocks) {
                if (b != null) {
                    final BlockButton blockButton = b;
                    blockButton.y -= this.scrollOffset;
                    if (b.textFieldRed == null || b.textFieldGreen == null || b.textFieldBlue == null) {
                        continue;
                    }
                    final HtlrTextField textFieldRed = b.textFieldRed;
                    textFieldRed.y -= this.scrollOffset;
                    final HtlrTextField textFieldGreen = b.textFieldGreen;
                    textFieldGreen.y -= this.scrollOffset;
                    final HtlrTextField textFieldBlue = b.textFieldBlue;
                    textFieldBlue.y -= this.scrollOffset;
                    b.textFieldRed.setVisible(false);
                    b.textFieldRed.setEnabled(false);
                    b.textFieldGreen.setVisible(false);
                    b.textFieldGreen.setEnabled(false);
                    b.textFieldBlue.setVisible(false);
                    b.textFieldBlue.setEnabled(false);
                }
            }
            if (this.searchField != null) {
                final HtlrTextField searchField = this.searchField;
                searchField.y -= this.scrollOffset;
                this.searchField.setVisible(false);
                this.searchField.setEnabled(false);
            }
            this.scrollOffset = 0;
            this.field_146297_k.func_147108_a(this.lastScreen);
            if (this.field_146297_k.field_71462_r == null) {
                this.field_146297_k.func_71381_h();
            }
        }
        final ArrayList<BlockButton> onScreen = new ArrayList<BlockButton>();
        for (final BlockButton b2 : this.blocks) {
            if (b2 != null && b2.y + b2.height >= 0 && b2.y <= this.field_146297_k.field_71440_d) {
                onScreen.add(b2);
            }
        }
        for (final BlockButton b2 : onScreen) {
            if (b2 != null && b2.textFieldRed != null && b2.textFieldGreen != null && b2.textFieldBlue != null) {
                b2.textFieldRed.textboxKeyTyped(typedChar, keyCode);
                b2.textFieldGreen.textboxKeyTyped(typedChar, keyCode);
                b2.textFieldBlue.textboxKeyTyped(typedChar, keyCode);
            }
        }
        if (this.searchField != null) {
            this.searchField.textboxKeyTyped(typedChar, keyCode);
            if (this.searchField.isFocused()) {
                this.refresh();
            }
        }
    }
    
    protected void func_73864_a(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (this.frame == null) {
            return;
        }
        if (mouseButton == 0) {
            this.frame.onClick(mouseX, mouseY, mouseButton);
        }
        if (this.setting.blocks != null) {
            for (final Block b : this.setting.blocks) {
                final int color = this.setting.getColor(b);
                ((Search)this.setting.parent).to_search.put(b, color);
            }
        }
        Main.config.Save();
    }
    
    private void scroller() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0 && this.frame.y + this.frame.height > 0) {
            for (final BlockButton b : this.blocks) {
                if (b != null) {
                    final BlockButton blockButton = b;
                    blockButton.y -= 10;
                    if (b.textFieldRed == null || b.textFieldGreen == null || b.textFieldBlue == null) {
                        continue;
                    }
                    final HtlrTextField textFieldRed = b.textFieldRed;
                    textFieldRed.y -= 10;
                    final HtlrTextField textFieldGreen = b.textFieldGreen;
                    textFieldGreen.y -= 10;
                    final HtlrTextField textFieldBlue = b.textFieldBlue;
                    textFieldBlue.y -= 10;
                    if (b.y + b.height >= 0 || (!b.textFieldRed.isFocused() && !b.textFieldGreen.isFocused() && !b.textFieldBlue.isFocused())) {
                        continue;
                    }
                    b.textFieldRed.setFocused(false);
                    b.textFieldGreen.setFocused(false);
                    b.textFieldBlue.setFocused(false);
                }
            }
            final BlockSelectorGuiFrame frame = this.frame;
            frame.y -= 10;
            this.scrollOffset -= 10;
        }
        else if (dWheel > 0 && this.frame.y <= this.field_146295_m) {
            for (final BlockButton b : this.blocks) {
                if (b != null) {
                    final BlockButton blockButton2 = b;
                    blockButton2.y += 10;
                    if (b.textFieldRed == null || b.textFieldGreen == null || b.textFieldBlue == null) {
                        continue;
                    }
                    final HtlrTextField textFieldRed2 = b.textFieldRed;
                    textFieldRed2.y += 10;
                    final HtlrTextField textFieldGreen2 = b.textFieldGreen;
                    textFieldGreen2.y += 10;
                    final HtlrTextField textFieldBlue2 = b.textFieldBlue;
                    textFieldBlue2.y += 10;
                    if (b.y <= this.field_146297_k.field_71440_d || (!b.textFieldRed.isFocused() && !b.textFieldGreen.isFocused() && !b.textFieldBlue.isFocused())) {
                        continue;
                    }
                    b.textFieldRed.setFocused(false);
                    b.textFieldGreen.setFocused(false);
                    b.textFieldBlue.setFocused(false);
                }
            }
            final BlockSelectorGuiFrame frame2 = this.frame;
            frame2.y += 10;
            this.scrollOffset += 10;
        }
    }
    
    public void refresh() {
        Main.config.Save();
        this.frame = new BlockSelectorGuiFrame(20, 20, this, this.searchField.getText());
        final BlockSelectorGuiFrame frame = this.frame;
        frame.y += this.scrollOffset;
    }
}
