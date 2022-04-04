// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.setting.settings;

import mod.hitlerhax.event.events.HtlrEventSettings;
import mod.hitlerhax.event.HtlrEventBus;
import java.util.function.Predicate;
import mod.hitlerhax.module.modules.render.Nametags;
import mod.hitlerhax.Main;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.setting.Setting;

public class IntSetting extends Setting
{
    public int value;
    
    public IntSetting(final String name, final Module parent, final int value) {
        this.name = name;
        this.parent = parent;
        if (!Main.configLoaded) {
            this.value = value;
        }
    }
    
    public IntSetting(final String string, final int i, final Nametags nametags, final Predicate<Integer> shown) {
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(final int value) {
        this.value = value;
        if (Main.config != null) {
            Main.config.Save();
        }
        HtlrEventBus.EVENT_BUS.post(new HtlrEventSettings(this, this.parent));
    }
}
