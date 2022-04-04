// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.setting;

import mod.hitlerhax.Main;
import java.util.Iterator;
import mod.hitlerhax.module.Module;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class SettingManager extends GuiScreen
{
    private final ArrayList<Setting> settings;
    
    public SettingManager() {
        this.settings = new ArrayList<Setting>();
    }
    
    public void addSetting(final Setting in) {
        this.settings.add(in);
    }
    
    public ArrayList<Setting> getSettings() {
        return this.settings;
    }
    
    public ArrayList<Setting> getSettingsByMod(final Module mod) {
        final ArrayList<Setting> out = new ArrayList<Setting>();
        for (final Setting s : this.getSettings()) {
            if (s.parent.equals(mod)) {
                out.add(s);
            }
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }
    
    public Setting getSettingByName(final Module mod, final String name) {
        for (final Module m : Main.moduleManager.modules) {
            for (final Setting set : m.settings) {
                if (set.name.equalsIgnoreCase(name) && set.parent == mod) {
                    return set;
                }
            }
        }
        return null;
    }
    
    public void clearSettings() {
        this.settings.clear();
    }
}
