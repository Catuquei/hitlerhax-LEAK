// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import mod.hitlerhax.event.events.HtlrEventRender;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import mod.hitlerhax.Main;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import mod.hitlerhax.ui.guiitems.HtlrButton;
import mod.hitlerhax.setting.Setting;
import java.util.List;
import mod.hitlerhax.util.Globals;
import me.zero.alpine.listener.Listenable;

public class Module implements Listenable, Globals
{
    public final String name;
    public String description;
    public int key;
    private final Category category;
    public boolean toggled;
    protected boolean enabled;
    public final List<Setting> settings;
    public final List<HtlrButton> buttons;
    
    public Module(final String name, final String description, final Category category) {
        this.settings = new ArrayList<Setting>();
        this.buttons = new ArrayList<HtlrButton>();
        this.name = name;
        this.description = description;
        this.key = 0;
        this.category = category;
        this.toggled = false;
        this.enabled = false;
    }
    
    public void addButton(final HtlrButton... buttons) {
        this.buttons.addAll(Arrays.asList(buttons));
    }
    
    public void addSetting(final Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
        Main.config.Save();
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    protected void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        HtlrEventBus.EVENT_BUS.subscribe(this);
        Main.config.Save();
    }
    
    public void enable() {
        if (!this.isEnabled()) {
            this.enabled = true;
            try {
                this.onEnable();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void disable() {
        if (this.isEnabled()) {
            this.enabled = false;
            try {
                this.onDisable();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    protected void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        HtlrEventBus.EVENT_BUS.unsubscribe(this);
        Main.config.Save();
    }
    
    public String getName() {
        return this.name;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public void onUpdate() {
    }
    
    public void render(final HtlrEventRender event) {
    }
    
    public void render() {
    }
    
    public void onKeyPress() {
    }
    
    public void actionPerformed(final HtlrButton b) {
    }
    
    public void onRenderWorldLast(final HtlrEventRender event) {
    }
    
    public void onWorldRender(final HtlrEventRender event) {
    }
    
    public void setup() {
    }
    
    public void onClientTick(final TickEvent.ClientTickEvent event) {
    }
}
