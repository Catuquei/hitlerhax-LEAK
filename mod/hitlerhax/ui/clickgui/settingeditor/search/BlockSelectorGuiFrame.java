// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui.settingeditor.search;

import org.lwjgl.opengl.GL11;
import java.util.Iterator;
import mod.hitlerhax.ui.guiitems.HtlrTextField;
import org.apache.commons.lang3.StringUtils;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import mod.hitlerhax.Main;
import java.util.ArrayList;
import mod.hitlerhax.util.Globals;

public class BlockSelectorGuiFrame implements Globals
{
    final int x;
    int y;
    final int width;
    final int height;
    final BlockSelectorGuiController controller;
    final String searchText;
    
    public BlockSelectorGuiFrame(final int x, final int y, final BlockSelectorGuiController controller, final String searchText) {
        this.x = x;
        this.y = y;
        this.width = 400;
        this.controller = controller;
        this.searchText = searchText;
        controller.field_146296_j = BlockSelectorGuiFrame.mc.func_175599_af();
        int offsetY = 2;
        offsetY += 20;
        controller.blocks = new ArrayList<BlockButton>();
        if (Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks") != null && controller.blocks.isEmpty()) {
            if (controller.searchField == null || controller.searchField.getText().equals("")) {
                for (final Block b : ForgeRegistries.BLOCKS.getValuesCollection()) {
                    controller.blocks.add(new BlockButton(Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks").parent, this.x + 2, this.y + offsetY + controller.scrollOffset, this, b));
                    offsetY += 20;
                }
            }
            else {
                for (final Block b : ForgeRegistries.BLOCKS.getValuesCollection()) {
                    if (StringUtils.containsIgnoreCase((CharSequence)b.func_149732_F(), (CharSequence)searchText)) {
                        controller.blocks.add(new BlockButton(Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks").parent, this.x + 2, this.y + offsetY + controller.scrollOffset, this, b));
                        offsetY += 20;
                    }
                }
            }
            final ArrayList<BlockButton> removeDuplicates = new ArrayList<BlockButton>();
            for (final BlockButton b2 : controller.blocks) {
                if (!removeDuplicates.contains(b2)) {
                    removeDuplicates.add(b2);
                }
                else {
                    offsetY -= 20;
                }
            }
            controller.blocks = removeDuplicates;
        }
        else {
            for (int i = 0; i < controller.blocks.size(); ++i) {
                offsetY += 20;
            }
        }
        this.height = offsetY;
        if (controller.searchField == null) {
            controller.searchField = new HtlrTextField(0, this.x + 4, this.y + 6, 380, BlockSelectorGuiFrame.mc.field_71466_p.field_78288_b + 4);
        }
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
        GL11.glVertex2f((float)(this.x + this.width), (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width), (float)this.y);
        GL11.glEnd();
        GL11.glColor3f(0.0f, 200.0f, 255.0f);
        GL11.glBegin(2);
        GL11.glVertex2f((float)this.x, (float)this.y);
        GL11.glVertex2f((float)this.x, (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width), (float)(this.y + this.height));
        GL11.glVertex2f((float)(this.x + this.width), (float)this.y);
        GL11.glEnd();
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        this.controller.searchField.y = this.y + 6;
        this.controller.searchField.setEnabled(true);
        this.controller.searchField.drawTextBox();
        for (final BlockButton b : this.controller.blocks) {
            if (b != null && b.y + b.height >= 0 && b.y <= BlockSelectorGuiFrame.mc.field_71440_d) {
                b.draw();
            }
        }
    }
    
    public void onClick(final int x, final int y, final int button) {
        final ArrayList<BlockButton> onScreen = new ArrayList<BlockButton>();
        for (final BlockButton b : this.controller.blocks) {
            if (b != null && b.y + b.height >= 0 && b.y <= BlockSelectorGuiFrame.mc.field_71440_d) {
                onScreen.add(b);
            }
            else {
                assert b != null;
                if (b.textFieldRed == null || b.textFieldGreen == null || b.textFieldBlue == null) {
                    continue;
                }
                b.textFieldRed.setFocused(false);
                b.textFieldGreen.setFocused(false);
                b.textFieldBlue.setFocused(false);
            }
        }
        for (final BlockButton b : onScreen) {
            if (b != null) {
                b.onClick(x, y, button);
            }
        }
        this.controller.searchField.mouseClicked(x, y, button);
    }
}
