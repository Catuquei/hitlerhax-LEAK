// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.misc;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;

public class RenderBuilder
{
    public static void glSetup() {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.5f);
    }
    
    public static void glRelease() {
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void glPrepare() {
        GlStateManager.func_179129_p();
        GlStateManager.func_179118_c();
        GlStateManager.func_179103_j(7425);
    }
    
    public static void glRestore() {
        GlStateManager.func_179089_o();
        GlStateManager.func_179141_d();
        GlStateManager.func_179103_j(7424);
    }
    
    public enum RenderMode
    {
        Fill, 
        Outline, 
        Both, 
        Claw, 
        Glow;
    }
}
