// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.client;

import mod.hitlerhax.util.font.HtlrFontRenderer;
import java.awt.Font;
import mod.hitlerhax.Main;
import me.zero.alpine.listener.Listenable;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventSettings;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class ClientFont extends Module
{
    public final ModeSetting font;
    @EventHandler
    private final Listener<HtlrEventSettings> settingChange;
    
    public ClientFont() {
        super("ClientFont", "changes the font the client uses.", Category.CLIENT);
        this.font = new ModeSetting("font", this, "Comic Sans Ms", new String[] { "Comic Sans Ms", "Arial", "Verdana" });
        this.settingChange = new Listener<HtlrEventSettings>(event -> {
            if (event.module == this) {
                this.onEnable();
            }
            return;
        }, (Predicate<HtlrEventSettings>[])new Predicate[0]);
        this.addSetting(this.font);
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        HtlrEventBus.EVENT_BUS.subscribe(this);
        Main.config.Save();
        if (this.font.is("Comic Sans Ms")) {
            Main.customFontRenderer = new HtlrFontRenderer(new Font("Comic Sans MS", 0, 18), true, true);
        }
        if (this.font.is("Arial")) {
            Main.customFontRenderer = new HtlrFontRenderer(new Font("Arial", 0, 18), true, true);
        }
        if (this.font.is("Verdana")) {
            Main.customFontRenderer = new HtlrFontRenderer(new Font("Verdana", 0, 18), true, true);
        }
    }
}
