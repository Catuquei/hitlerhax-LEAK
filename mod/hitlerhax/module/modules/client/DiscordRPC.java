// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.client;

import mod.hitlerhax.util.HtlrDiscordRichPresence;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class DiscordRPC extends Module
{
    public final ModeSetting mode;
    
    public DiscordRPC() {
        super("DiscordRPC", "Rich Presence For Discord", Category.CLIENT);
        this.mode = new ModeSetting("Mode", this, "HitlerHax", new String[] { "Vanilla", "HitlerHax" });
        this.addSetting(this.mode);
    }
    
    public void onEnable() {
        HtlrDiscordRichPresence.start(this.mode.getMode());
    }
    
    public void onDisable() {
        HtlrDiscordRichPresence.stop();
    }
}
