// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import mod.hitlerhax.module.Module;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventSettings extends HtlrEventCancellable
{
    public Setting setting;
    public Module module;
    
    public HtlrEventSettings(final Setting setting, final Module module) {
        this.setting = setting;
        this.module = module;
    }
}
