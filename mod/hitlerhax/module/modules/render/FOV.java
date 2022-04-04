// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class FOV extends Module
{
    final IntSetting fov;
    float fovOld;
    
    public FOV() {
        super("FOV", "Changes your players view", Category.RENDER);
        this.fov = new IntSetting("Fov", this, 120);
        this.addSetting(this.fov);
    }
    
    public void onEnable() {
        this.fovOld = FOV.mc.field_71474_y.field_74334_X;
    }
    
    @Override
    public void onUpdate() {
        FOV.mc.field_71474_y.field_74334_X = (float)this.fov.getValue();
    }
    
    public void onDisable() {
        FOV.mc.field_71474_y.field_74334_X = this.fovOld;
    }
}
