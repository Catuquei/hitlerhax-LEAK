// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.setting.settings;

import mod.hitlerhax.event.events.HtlrEventSettings;
import mod.hitlerhax.event.HtlrEventBus;
import mod.hitlerhax.Main;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.setting.Setting;

public class FloatSetting extends Setting
{
    public float value;
    
    public FloatSetting(final String name, final Module parent, final Float value) {
        this.name = name;
        this.parent = parent;
        if (!Main.configLoaded) {
            this.value = value;
        }
    }
    
    public float getValue() {
        return this.value;
    }
    
    public void setValue(final float value) {
        this.value = value;
        if (Main.config != null) {
            Main.config.Save();
        }
        HtlrEventBus.EVENT_BUS.post(new HtlrEventSettings(this, this.parent));
    }
}
