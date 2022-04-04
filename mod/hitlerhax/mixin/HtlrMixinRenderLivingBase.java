// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import net.minecraft.entity.Entity;
import mod.hitlerhax.util.render.OutlineUtils;
import java.awt.Color;
import mod.hitlerhax.util.render.ColorUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.lwjgl.opengl.GL11;
import mod.hitlerhax.module.modules.render.Esp;
import mod.hitlerhax.Main;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.EntityLivingBase;

@Mixin({ RenderLivingBase.class })
public abstract class HtlrMixinRenderLivingBase<T extends EntityLivingBase> extends HtlrMixinRender<T>
{
    @Shadow
    protected ModelBase field_77045_g;
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V" }, at = { @At("HEAD") })
    private void injectChamsPre(final T a, final double b, final double c, final double d, final float e, final float f, final CallbackInfo g) {
        if (Main.moduleManager.getModule("Esp's") != null && Main.moduleManager.getModule("Esp's").isToggled() && ((Esp)Main.moduleManager.getModule("Esp's")).chams.isEnabled()) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -1000000.0f);
        }
    }
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V" }, at = { @At("RETURN") })
    private void injectChamsPost(final T a, final double b, final double c, final double d, final float e, final float f, final CallbackInfo g) {
        if (Main.moduleManager.getModule("Esp's") != null && Main.moduleManager.getModule("Esp's").isToggled() && ((Esp)Main.moduleManager.getModule("Esp's")).chams.isEnabled()) {
            GL11.glPolygonOffset(1.0f, 1000000.0f);
            GL11.glDisable(32823);
        }
    }
    
    @Inject(method = { "renderModel" }, at = { @At("HEAD") })
    protected void renderModel(final T entitylivingbaseIn, final float p_77036_2_, final float p_77036_3_, final float p_77036_4_, final float p_77036_5_, final float p_77036_6_, final float scaleFactor, final CallbackInfo g) {
        final boolean flag = !entitylivingbaseIn.func_82150_aj();
        final boolean flag2 = !flag && !entitylivingbaseIn.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        if (flag || flag2) {
            if (!this.func_180548_c(entitylivingbaseIn)) {
                return;
            }
            if (flag2) {
                GlStateManager.func_179094_E();
                GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.15f);
                GlStateManager.func_179132_a(false);
                GlStateManager.func_179147_l();
                GlStateManager.func_179112_b(770, 771);
                GlStateManager.func_179092_a(516, 0.003921569f);
            }
            if (Main.moduleManager.getModule("Esp's") != null && Main.moduleManager.getModule("Esp's").isToggled()) {
                if (entitylivingbaseIn instanceof EntityPlayer && entitylivingbaseIn != Minecraft.func_71410_x().field_71439_g && ((Esp)Main.moduleManager.getModule("Esp's")).entityMode.is("outline")) {
                    final Color n = new ColorUtil(((Esp)Main.moduleManager.getModule("Esp's")).playerColor.getColor());
                    OutlineUtils.setColor(n);
                    this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderOne((float)((Esp)Main.moduleManager.getModule("Esp's")).lineWidth.getValue());
                    this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderTwo();
                    this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour();
                    OutlineUtils.setColor(n);
                    this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderFive();
                    OutlineUtils.setColor(Color.WHITE);
                }
                else if (((Esp)Main.moduleManager.getModule("Esp's")).mob.isEnabled() && ((Esp)Main.moduleManager.getModule("Esp's")).entityMode.is("outline")) {
                    GL11.glLineWidth(5.0f);
                    this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderOne((float)((Esp)Main.moduleManager.getModule("Esp's")).lineWidth.getValue());
                    this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderTwo();
                    this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour();
                    this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderFive();
                }
            }
            this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
            if (flag2) {
                GlStateManager.func_179084_k();
                GlStateManager.func_179092_a(516, 0.1f);
                GlStateManager.func_179121_F();
                GlStateManager.func_179132_a(true);
            }
        }
    }
}
