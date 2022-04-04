// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.setting.settings;

import mod.hitlerhax.event.events.HtlrEventSettings;
import mod.hitlerhax.event.HtlrEventBus;
import mod.hitlerhax.Main;
import java.util.Arrays;
import mod.hitlerhax.module.Module;
import java.util.List;
import mod.hitlerhax.setting.Setting;

public class ModeSetting extends Setting
{
    public int index;
    public final List<String> modes;
    
    public ModeSetting(final String name, final Module parent, final String defaultMode, final String... modes) {
        this.name = name;
        this.parent = parent;
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);
    }
    
    public String getMode() {
        return this.modes.get(this.index);
    }
    
    public void setMode(final String mode) {
        this.index = this.modes.indexOf(mode);
        if (Main.config != null) {
            Main.config.Save();
        }
        HtlrEventBus.EVENT_BUS.post(new HtlrEventSettings(this, this.parent));
    }
    
    public boolean is(final String mode) {
        return this.index == this.modes.indexOf(mode);
    }
    
    public void cycle() {
        if (this.index < this.modes.size() - 1) {
            ++this.index;
        }
        else {
            this.index = 0;
        }
        if (Main.config != null) {
            Main.config.Save();
        }
        HtlrEventBus.EVENT_BUS.post(new HtlrEventSettings(this, this.parent));
    }
}
