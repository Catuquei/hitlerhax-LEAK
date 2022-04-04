// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import mod.hitlerhax.Main;
import me.zero.alpine.listener.Listenable;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class LSD extends Module
{
    public LSD() {
        super("LSD", "Drugz", Category.RENDER);
    }
    
    public void onEnable() {
        if (LSD.mc.field_71441_e == null) {
            this.enabled = false;
            return;
        }
        LSD.mc.field_71438_f.func_72712_a();
        if (OpenGlHelper.field_148824_g && LSD.mc.func_175606_aa() instanceof EntityPlayer) {
            if (LSD.mc.field_71460_t.func_147706_e() != null) {
                LSD.mc.field_71460_t.func_147706_e().func_148021_a();
            }
            LSD.mc.field_71460_t.field_147713_ae = 19;
            if (LSD.mc.field_71460_t.field_147713_ae != EntityRenderer.field_147708_e) {
                LSD.mc.field_71460_t.func_175069_a(EntityRenderer.field_147712_ad[19]);
            }
            else {
                LSD.mc.field_71460_t.field_147707_d = null;
            }
        }
        MinecraftForge.EVENT_BUS.register((Object)this);
        HtlrEventBus.EVENT_BUS.subscribe(this);
        Main.config.Save();
    }
    
    public void onDisable() {
        this.enabled = false;
        if (LSD.mc.field_71460_t.func_147706_e() != null) {
            LSD.mc.field_71460_t.func_147706_e().func_148021_a();
            LSD.mc.field_71460_t.field_147707_d = null;
        }
        LSD.mc.field_71474_y.field_74326_T = false;
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        HtlrEventBus.EVENT_BUS.unsubscribe(this);
        Main.config.Save();
    }
    
    @Override
    public void onUpdate() {
        System.out.println("a");
        if (LSD.mc.field_71474_y.field_74320_O != 0) {
            this.toggle();
        }
    }
}
