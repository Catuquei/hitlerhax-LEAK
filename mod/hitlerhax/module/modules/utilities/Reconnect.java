// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import net.minecraft.client.multiplayer.ServerData;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class Reconnect extends Module
{
    public final IntSetting timer;
    public ServerData serverData;
    
    public Reconnect() {
        super("Reconnect", "Reconnects You Automatically", Category.UTILITIES);
        this.timer = new IntSetting("Timer", this, 5000);
        this.addSetting(this.timer);
    }
}
