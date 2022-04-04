// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class Sprint extends Module
{
    final BooleanSetting strict;
    final BooleanSetting hungerSafe;
    
    public Sprint() {
        super("Sprint", "Automatically sprints", Category.MOVEMENT);
        this.strict = new BooleanSetting("Strict", this, true);
        this.hungerSafe = new BooleanSetting("HungerSafe", this, true);
        this.addSetting(this.strict);
        this.addSetting(this.hungerSafe);
    }
    
    @Override
    public void onUpdate() {
        if (Sprint.mc.field_71441_e != null) {
            if (Sprint.mc.field_71474_y.field_74351_w.func_151470_d()) {
                Sprint.mc.field_71439_g.func_70031_b((!Sprint.mc.field_71439_g.field_70123_F || !this.strict.isEnabled()) && (Sprint.mc.field_71439_g.func_71024_bL().func_75116_a() > 6 || !this.hungerSafe.isEnabled()) && !Sprint.mc.field_71439_g.func_70093_af() && !Sprint.mc.field_71439_g.func_184587_cr());
            }
            else {
                Sprint.mc.field_71439_g.func_70031_b(false);
            }
        }
    }
}
