// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.hud;

import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class Hud extends Module
{
    final BooleanSetting menu;
    final BooleanSetting watermark;
    final BooleanSetting arraylist;
    final BooleanSetting coords;
    final BooleanSetting fps;
    final BooleanSetting armor;
    final BooleanSetting welcome;
    final BooleanSetting bps;
    final BooleanSetting radar;
    final IntSetting radarRadius;
    final IntSetting xCoordOffset;
    final IntSetting zCoordOffset;
    
    public Hud() {
        super("Hud", "In-Game Overlay", Category.HUD);
        this.menu = new BooleanSetting("Menu", this, true);
        this.watermark = new BooleanSetting("Watermark", this, true);
        this.arraylist = new BooleanSetting("ArrayList", this, false);
        this.coords = new BooleanSetting("Coordinates", this, false);
        this.fps = new BooleanSetting("FPS", this, false);
        this.armor = new BooleanSetting("Armor", this, false);
        this.welcome = new BooleanSetting("Welcome", this, false);
        this.bps = new BooleanSetting("BPS", this, false);
        this.radar = new BooleanSetting("Radar", this, false);
        this.radarRadius = new IntSetting("Radar Radius", this, 100);
        this.xCoordOffset = new IntSetting("XCoord-offset", this, 0);
        this.zCoordOffset = new IntSetting("ZCoord-offset", this, 0);
        this.addSetting(this.menu);
        this.addSetting(this.watermark);
        this.addSetting(this.arraylist);
        this.addSetting(this.coords);
        this.addSetting(this.fps);
        this.addSetting(this.armor);
        this.addSetting(this.welcome);
        this.addSetting(this.bps);
        this.addSetting(this.radar);
        this.addSetting(this.radarRadius);
        this.addSetting(this.xCoordOffset);
        this.addSetting(this.zCoordOffset);
    }
}
