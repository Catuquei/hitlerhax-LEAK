// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import mod.hitlerhax.util.EntityUtil;
import net.minecraft.client.renderer.GlStateManager;
import mod.hitlerhax.util.render.RenderUtil;
import mod.hitlerhax.Main;
import mod.hitlerhax.event.events.HtlrEventRender;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.module.Module;

public class Esp2dHelper extends Module
{
    ColorUtil ppColor;
    int opacityGradient;
    
    public Esp2dHelper() {
        super("Esp2dHelper", "Esp2dHelper", Category.CLIENT);
        this.toggled = true;
    }
    
    @Override
    public void render(final HtlrEventRender event) {
        if (Main.moduleManager.getModule("Esp's") != null && Main.moduleManager.getModule("Esp's").isToggled() && ((Esp)Main.moduleManager.getModule("Esp's")).entityMode.is("outlineEsp2D")) {
            if (Esp2dHelper.mc.func_175598_ae().field_78733_k == null) {
                return;
            }
            final float viewerYaw = Esp2dHelper.mc.func_175598_ae().field_78735_i;
            final Vec3d pos;
            final float n;
            Esp2dHelper.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity != Esp2dHelper.mc.field_71439_g).forEach(e -> {
                RenderUtil.prepare_new();
                GlStateManager.func_179094_E();
                pos = EntityUtil.getInterpolatedPos(e, Esp2dHelper.mc.func_184121_ak());
                GlStateManager.func_179137_b(pos.field_72450_a - Esp2dHelper.mc.func_175598_ae().field_78725_b, pos.field_72448_b - Esp2dHelper.mc.func_175598_ae().field_78726_c, pos.field_72449_c - Esp2dHelper.mc.func_175598_ae().field_78723_d);
                GlStateManager.func_187432_a(0.0f, 1.0f, 0.0f);
                GlStateManager.func_179114_b(-n, 0.0f, 1.0f, 0.0f);
                GL11.glEnable(2848);
                if (e instanceof EntityPlayer) {
                    this.ppColor = new ColorUtil(((Esp)Main.moduleManager.getModule("Esp's")).playerColor.getColor());
                    GlStateManager.func_187441_d((float)((Esp)Main.moduleManager.getModule("Esp's")).lineWidth.getValue());
                    this.ppColor.glColor();
                    GL11.glBegin(2);
                    GL11.glVertex2d((double)(-e.field_70130_N), 0.0);
                    GL11.glVertex2d((double)(-e.field_70130_N), (double)(e.field_70131_O / 4.0f));
                    GL11.glVertex2d((double)(-e.field_70130_N), 0.0);
                    GL11.glVertex2d((double)(-e.field_70130_N / 4.0f * 2.0f), 0.0);
                    GL11.glEnd();
                    GL11.glBegin(2);
                    GL11.glVertex2d((double)(-e.field_70130_N), (double)e.field_70131_O);
                    GL11.glVertex2d((double)(-e.field_70130_N / 4.0f * 2.0f), (double)e.field_70131_O);
                    GL11.glVertex2d((double)(-e.field_70130_N), (double)e.field_70131_O);
                    GL11.glVertex2d((double)(-e.field_70130_N), (double)(e.field_70131_O / 2.5f * 2.0f));
                    GL11.glEnd();
                    GL11.glBegin(2);
                    GL11.glVertex2d((double)e.field_70130_N, (double)e.field_70131_O);
                    GL11.glVertex2d((double)(e.field_70130_N / 4.0f * 2.0f), (double)e.field_70131_O);
                    GL11.glVertex2d((double)e.field_70130_N, (double)e.field_70131_O);
                    GL11.glVertex2d((double)e.field_70130_N, (double)(e.field_70131_O / 2.5f * 2.0f));
                    GL11.glEnd();
                    GL11.glBegin(2);
                    GL11.glVertex2d((double)e.field_70130_N, 0.0);
                    GL11.glVertex2d((double)(e.field_70130_N / 4.0f * 2.0f), 0.0);
                    GL11.glVertex2d((double)e.field_70130_N, 0.0);
                    GL11.glVertex2d((double)e.field_70130_N, (double)(e.field_70131_O / 4.0f));
                    GL11.glEnd();
                }
                RenderUtil.release_new();
                GlStateManager.func_179121_F();
            });
        }
    }
    
    public boolean rangeEntityCheck(final Entity entity) {
        if (entity.func_70032_d((Entity)Esp2dHelper.mc.field_71439_g) > ((Esp)Main.moduleManager.getModule("Esp's")).range.getValue()) {
            return false;
        }
        if (entity.func_70032_d((Entity)Esp2dHelper.mc.field_71439_g) >= 180.0f) {
            this.opacityGradient = 50;
        }
        else if (entity.func_70032_d((Entity)Esp2dHelper.mc.field_71439_g) >= 130.0f && entity.func_70032_d((Entity)Esp2dHelper.mc.field_71439_g) < 180.0f) {
            this.opacityGradient = 100;
        }
        else if (entity.func_70032_d((Entity)Esp2dHelper.mc.field_71439_g) >= 80.0f && entity.func_70032_d((Entity)Esp2dHelper.mc.field_71439_g) < 130.0f) {
            this.opacityGradient = 150;
        }
        else if (entity.func_70032_d((Entity)Esp2dHelper.mc.field_71439_g) >= 30.0f && entity.func_70032_d((Entity)Esp2dHelper.mc.field_71439_g) < 80.0f) {
            this.opacityGradient = 200;
        }
        else {
            this.opacityGradient = 255;
        }
        return true;
    }
    
    public boolean rangeTileCheck(final TileEntity tileEntity) {
        if (tileEntity.func_145835_a(Esp2dHelper.mc.field_71439_g.field_70165_t, Esp2dHelper.mc.field_71439_g.field_70163_u, Esp2dHelper.mc.field_71439_g.field_70161_v) > ((Esp)Main.moduleManager.getModule("Esp's")).range.getValue() * ((Esp)Main.moduleManager.getModule("Esp's")).range.getValue()) {
            return false;
        }
        if (tileEntity.func_145835_a(Esp2dHelper.mc.field_71439_g.field_70165_t, Esp2dHelper.mc.field_71439_g.field_70163_u, Esp2dHelper.mc.field_71439_g.field_70161_v) >= 32400.0) {
            this.opacityGradient = 50;
        }
        else if (tileEntity.func_145835_a(Esp2dHelper.mc.field_71439_g.field_70165_t, Esp2dHelper.mc.field_71439_g.field_70163_u, Esp2dHelper.mc.field_71439_g.field_70161_v) >= 16900.0 && tileEntity.func_145835_a(Esp2dHelper.mc.field_71439_g.field_70165_t, Esp2dHelper.mc.field_71439_g.field_70163_u, Esp2dHelper.mc.field_71439_g.field_70161_v) < 32400.0) {
            this.opacityGradient = 100;
        }
        else if (tileEntity.func_145835_a(Esp2dHelper.mc.field_71439_g.field_70165_t, Esp2dHelper.mc.field_71439_g.field_70163_u, Esp2dHelper.mc.field_71439_g.field_70161_v) >= 6400.0 && tileEntity.func_145835_a(Esp2dHelper.mc.field_71439_g.field_70165_t, Esp2dHelper.mc.field_71439_g.field_70163_u, Esp2dHelper.mc.field_71439_g.field_70161_v) < 16900.0) {
            this.opacityGradient = 150;
        }
        else if (tileEntity.func_145835_a(Esp2dHelper.mc.field_71439_g.field_70165_t, Esp2dHelper.mc.field_71439_g.field_70163_u, Esp2dHelper.mc.field_71439_g.field_70161_v) >= 900.0 && tileEntity.func_145835_a(Esp2dHelper.mc.field_71439_g.field_70165_t, Esp2dHelper.mc.field_71439_g.field_70163_u, Esp2dHelper.mc.field_71439_g.field_70161_v) < 6400.0) {
            this.opacityGradient = 200;
        }
        else {
            this.opacityGradient = 255;
        }
        return true;
    }
}
