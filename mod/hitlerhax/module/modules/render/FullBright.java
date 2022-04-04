// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class FullBright extends Module
{
    private float lastGamma;
    
    public FullBright() {
        super("FullBright", "Makes Everything Brighter", Category.RENDER);
    }
    
    public void onEnable() {
        super.onEnable();
        this.lastGamma = FullBright.mc.field_71474_y.field_74333_Y;
    }
    
    public void onDisable() {
        super.onDisable();
        FullBright.mc.field_71474_y.field_74333_Y = Math.min(this.lastGamma, 1.0f);
    }
    
    @Override
    public void onUpdate() {
        FullBright.mc.field_71474_y.field_74333_Y = 1000.0f;
    }
}
