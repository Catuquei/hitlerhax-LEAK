// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import net.minecraft.client.entity.EntityPlayerSP;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class YawLock extends Module
{
    final BooleanSetting smooth;
    final IntSetting rotation;
    
    public YawLock() {
        super("YawLock", "Locks yaw to a specific value.", Category.UTILITIES);
        this.smooth = new BooleanSetting("Smooth Rotate", this, false);
        this.rotation = new IntSetting("Rotation", this, 0);
        this.addSetting(this.smooth);
        this.addSetting(this.rotation);
    }
    
    @Override
    public void onUpdate() {
        if (this.smooth.enabled) {
            if ((int)(YawLock.mc.field_71439_g.field_70177_z % 360.0f) - this.rotation.value > 0) {
                final EntityPlayerSP field_71439_g = YawLock.mc.field_71439_g;
                --field_71439_g.field_70177_z;
            }
            else if ((int)(YawLock.mc.field_71439_g.field_70177_z % 360.0f) - this.rotation.value < 0) {
                final EntityPlayerSP field_71439_g2 = YawLock.mc.field_71439_g;
                ++field_71439_g2.field_70177_z;
            }
        }
        else {
            YawLock.mc.field_71439_g.field_70177_z = (float)this.rotation.value;
        }
    }
}
