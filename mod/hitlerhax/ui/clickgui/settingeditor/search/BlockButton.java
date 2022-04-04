// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui.settingeditor.search;

import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.init.Blocks;
import net.minecraft.block.BlockWall;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.BlockHorizontal;
import java.awt.Color;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.Main;
import mod.hitlerhax.setting.settings.SearchBlockSelectorSetting;
import net.minecraft.block.state.IBlockState;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.ui.guiitems.HtlrTextField;
import net.minecraft.block.Block;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.util.TileEntityFakeWorld;
import mod.hitlerhax.util.Globals;

public class BlockButton implements Globals
{
    private final TileEntityFakeWorld world;
    final int x;
    int y;
    final int width;
    final int height;
    final Module module;
    final BlockSelectorGuiFrame parent;
    final Block block;
    HtlrTextField textFieldRed;
    HtlrTextField textFieldGreen;
    HtlrTextField textFieldBlue;
    
    public BlockButton(final Module module, final int x, final int y, final BlockSelectorGuiFrame parent, final Block block) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = FontUtils.getStringWidth(block.func_149732_F() + 100);
        this.height = FontUtils.getFontHeight();
        this.block = block;
        this.world = new TileEntityFakeWorld(null, Globals.mc.field_71441_e.field_73011_w);
    }
    
    public void draw() {
        if (((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).blocks.contains(this.block)) {
            FontUtils.drawString(this.block.func_149732_F(), this.x + 20, this.y + 2, new ColorUtil(255, 150, 50));
        }
        else {
            FontUtils.drawString(this.block.func_149732_F(), this.x + 20, this.y + 2, new ColorUtil(180, 240, 255));
        }
        this.displayBlockFlat(this.x + 2, this.y + 2, this.block);
        if (((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).colorSettings && ForgeRegistries.BLOCKS.getValuesCollection().contains(this.block)) {
            final int textRed = (int)FontUtils.drawString(Integer.toString(new Color(((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).getColors().get(this.block)).getRed()), BlockButton.mc.field_71443_c / 2, this.y + 2, new ColorUtil(255, 0, 0));
            final int textGreen = (int)FontUtils.drawString(Integer.toString(new Color(((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).getColors().get(this.block)).getGreen()), BlockButton.mc.field_71443_c / 2 + 125, this.y + 2, new ColorUtil(0, 255, 0));
            final int textBlue = (int)FontUtils.drawString(Integer.toString(new Color(((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).getColors().get(this.block)).getBlue()), BlockButton.mc.field_71443_c / 2 + 250, this.y + 2, new ColorUtil(0, 0, 255));
            if (this.textFieldRed == null && this.textFieldGreen == null && this.textFieldBlue == null) {
                this.textFieldRed = new HtlrTextField(textRed, this.x + 200, this.y + 4, 50, FontUtils.getFontHeight() + 4);
                this.textFieldGreen = new HtlrTextField(textGreen, this.x + 265, this.y + 4, 50, FontUtils.getFontHeight() + 4);
                this.textFieldBlue = new HtlrTextField(textBlue, this.x + 330, this.y + 4, 50, FontUtils.getFontHeight() + 4);
            }
            assert this.textFieldRed != null;
            this.textFieldRed.setTextColor(new Color(255, 0, 0).getRGB());
            this.textFieldGreen.setTextColor(new Color(0, 255, 0).getRGB());
            this.textFieldBlue.setTextColor(new Color(0, 0, 254).getRGB());
            this.textFieldRed.setEnabled(true);
            this.textFieldGreen.setEnabled(true);
            this.textFieldBlue.setEnabled(true);
            if (!this.textFieldRed.isFocused()) {
                this.textFieldRed.setText(Integer.toString(new Color(((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).getColor(this.block)).getRed()));
            }
            if (!this.textFieldGreen.isFocused()) {
                this.textFieldGreen.setText(Integer.toString(new Color(((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).getColor(this.block)).getGreen()));
            }
            if (!this.textFieldBlue.isFocused()) {
                this.textFieldBlue.setText(Integer.toString(new Color(((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).getColor(this.block)).getBlue()));
            }
            this.textFieldRed.drawTextBox();
            this.textFieldGreen.drawTextBox();
            this.textFieldBlue.drawTextBox();
        }
    }
    
    public void displayBlockFlat(final int x, final int y, final Block block) {
        IBlockState state = block.func_176223_P();
        try {
            state = state.func_177226_a((IProperty)BlockHorizontal.field_185512_D, (Comparable)EnumFacing.SOUTH);
        }
        catch (IllegalArgumentException ex) {}
        try {
            state = state.func_177226_a((IProperty)BlockDirectional.field_176387_N, (Comparable)EnumFacing.SOUTH);
        }
        catch (IllegalArgumentException ex2) {}
        if (block instanceof BlockWall) {
            state = state.func_177226_a((IProperty)BlockWall.field_176256_a, (Comparable)Boolean.TRUE);
        }
        else if (block == Blocks.field_150324_C) {
            state = state.func_177226_a((IProperty)BlockHorizontal.field_185512_D, (Comparable)EnumFacing.NORTH);
        }
        this.displayBlockFlat(x, y, state);
    }
    
    public void displayBlockFlat(final int x, final int y, final IBlockState state) {
        this.displayBlockFlat(x, y, state, null);
    }
    
    public void displayBlockFlat(final int x, final int y, final IBlockState state, TileEntity tile) {
        if (state.func_177230_c() == Blocks.field_150350_a) {
            return;
        }
        final ItemStack stack = new ItemStack(state.func_177230_c());
        IBakedModel model = Globals.mc.func_175602_ab().func_184389_a(state);
        if (model == Globals.mc.func_175602_ab().func_175023_a().func_178126_b().func_174951_a()) {
            model = this.parent.controller.field_146296_j.func_184393_a(stack, (World)null, (EntityLivingBase)Globals.mc.field_71439_g);
        }
        GlStateManager.func_179094_E();
        Globals.mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
        Globals.mc.field_71446_o.func_110581_b(TextureMap.field_110575_b).func_174936_b(false, false);
        GlStateManager.func_179091_B();
        GlStateManager.func_179141_d();
        GlStateManager.func_179092_a(516, 0.1f);
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        RenderHelper.func_74520_c();
        GlStateManager.func_179109_b((float)x, (float)y, 150.0f);
        GlStateManager.func_179109_b(8.0f, 8.0f, 0.0f);
        GlStateManager.func_179152_a(1.0f, -1.0f, 1.0f);
        GlStateManager.func_179152_a(16.0f, 16.0f, 16.0f);
        GlStateManager.func_179140_f();
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b(-0.5f, -0.5f, -0.5f);
        if (model.func_188618_c() || model == Globals.mc.func_175602_ab().func_175023_a().func_178126_b().func_174951_a()) {
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.func_179091_B();
            if (tile == null) {
                tile = this.createTileEntity(state);
            }
            if (tile != null) {
                this.renderByTileEntity(tile, state);
            }
        }
        else {
            final Tessellator tessellator = Tessellator.func_178181_a();
            final BufferBuilder bufferbuilder = tessellator.func_178180_c();
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_176599_b);
            for (final EnumFacing enumfacing : EnumFacing.values()) {
                this.parent.controller.field_146296_j.func_191970_a(bufferbuilder, model.func_188616_a(state, enumfacing, 4242L), -1, stack);
            }
            this.parent.controller.field_146296_j.func_191970_a(bufferbuilder, model.func_188616_a(state, (EnumFacing)null, 4242L), -1, stack);
            tessellator.func_78381_a();
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179118_c();
        GlStateManager.func_179101_C();
        GlStateManager.func_179140_f();
        GlStateManager.func_179121_F();
        BlockButton.mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
        BlockButton.mc.field_71446_o.func_110581_b(TextureMap.field_110575_b).func_174935_a();
    }
    
    private void renderByTileEntity(final TileEntity tile, final IBlockState state) {
        this.world.func_180501_a(null, state, 0);
        final World save = TileEntityRendererDispatcher.field_147556_a.field_147550_f;
        TileEntityRendererDispatcher.field_147556_a.field_147550_f = this.world;
        if (tile instanceof TileEntityBanner || tile instanceof TileEntityEnderChest || tile instanceof TileEntityBed || tile instanceof TileEntityChest || tile instanceof TileEntityShulkerBox) {
            TileEntityRendererDispatcher.field_147556_a.func_192855_a(tile, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        }
        else if (tile instanceof TileEntitySkull) {
            if (TileEntitySkullRenderer.field_147536_b != null) {
                GlStateManager.func_179094_E();
                GlStateManager.func_179129_p();
                TileEntitySkullRenderer.field_147536_b.func_188190_a(0.0f, 0.0f, 0.0f, (EnumFacing)state.func_177229_b((IProperty)BlockDirectional.field_176387_N), ((TileEntitySkull)tile).func_145906_b() * 360 / 16.0f, ((TileEntitySkull)tile).func_145904_a(), Globals.mc.func_110432_I().func_148256_e(), -1, 0.0f);
                GlStateManager.func_179089_o();
                GlStateManager.func_179121_F();
            }
        }
        else {
            TileEntitySpecialRenderer<TileEntity> renderer;
            if (tile instanceof TileEntityEndGateway) {
                renderer = (TileEntitySpecialRenderer<TileEntity>)TileEntityRendererDispatcher.field_147556_a.func_147546_a((Class)TileEntityEndPortal.class);
            }
            else {
                renderer = (TileEntitySpecialRenderer<TileEntity>)TileEntityRendererDispatcher.field_147556_a.func_147546_a((Class)tile.getClass());
            }
            if (renderer != null) {
                renderer.func_192841_a(tile, 0.0, 0.0, 0.0, 0.0f, -1, 0.0f);
            }
        }
        TileEntityRendererDispatcher.field_147556_a.field_147550_f = save;
    }
    
    public TileEntity createTileEntity(final IBlockState state) {
        final TileEntity tile = state.func_177230_c().createTileEntity((World)Globals.mc.field_71441_e, state);
        if (tile == null) {
            return null;
        }
        tile.func_145834_a((World)this.world);
        return tile;
    }
    
    public void onClick(final int mouseX, final int mouseY, final int button) {
        final ArrayList<Block> removeDuplicates = new ArrayList<Block>();
        for (final Block b : ((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).blocks) {
            if (!removeDuplicates.contains(b)) {
                removeDuplicates.add(b);
            }
        }
        ((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).blocks = removeDuplicates;
        if (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
            if (((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).blocks.contains(this.block)) {
                ((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).blocks.remove(this.block);
            }
            else {
                ((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).blocks.add(this.block);
            }
            Main.config.Save();
            this.parent.controller.refresh();
        }
        if (this.textFieldRed != null) {
            this.textFieldRed.mouseClicked(mouseX, mouseY, button);
        }
        if (this.textFieldGreen != null) {
            this.textFieldGreen.mouseClicked(mouseX, mouseY, button);
        }
        if (this.textFieldBlue != null) {
            this.textFieldBlue.mouseClicked(mouseX, mouseY, button);
        }
        if (this.textFieldRed != null && this.textFieldRed.isFocused()) {
            if (Integer.parseInt(this.textFieldRed.getText()) <= 255 && Integer.parseInt(this.textFieldRed.getText()) >= 0) {
                for (final Map.Entry<Block, Integer> e : ((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).colors.entrySet()) {
                    if (e.getKey() == this.block) {
                        e.setValue(new Color(Integer.parseInt(this.textFieldRed.getText()), Integer.parseInt(this.textFieldGreen.getText()), Integer.parseInt(this.textFieldBlue.getText())).getRGB());
                    }
                }
            }
            else {
                this.textFieldRed.setText(Integer.toString(new Color(((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).getColor(this.block)).getRed()));
            }
        }
        if (this.textFieldGreen != null && this.textFieldGreen.isFocused()) {
            if (Integer.parseInt(this.textFieldGreen.getText()) <= 255 && Integer.parseInt(this.textFieldGreen.getText()) >= 0) {
                for (final Map.Entry<Block, Integer> e : ((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).colors.entrySet()) {
                    if (e.getKey() == this.block) {
                        e.setValue(new Color(Integer.parseInt(this.textFieldRed.getText()), Integer.parseInt(this.textFieldGreen.getText()), Integer.parseInt(this.textFieldBlue.getText())).getRGB());
                    }
                }
            }
            else {
                this.textFieldGreen.setText(Integer.toString(new Color(((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).getColor(this.block)).getGreen()));
            }
        }
        if (this.textFieldBlue != null && this.textFieldBlue.isFocused()) {
            if (Integer.parseInt(this.textFieldBlue.getText()) <= 255 && Integer.parseInt(this.textFieldBlue.getText()) >= 0) {
                for (final Map.Entry<Block, Integer> e : ((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).colors.entrySet()) {
                    if (e.getKey() == this.block) {
                        e.setValue(new Color(Integer.parseInt(this.textFieldRed.getText()), Integer.parseInt(this.textFieldGreen.getText()), Integer.parseInt(this.textFieldBlue.getText())).getRGB());
                    }
                }
            }
            else {
                this.textFieldBlue.setText(Integer.toString(new Color(((SearchBlockSelectorSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Search"), "Select Blocks")).getColor(this.block)).getBlue()));
            }
        }
    }
}
