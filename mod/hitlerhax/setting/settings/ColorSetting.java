// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.setting.settings;

import mod.hitlerhax.Main;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.setting.Setting;

public class ColorSetting extends Setting
{
    private int red;
    private int green;
    private int blue;
    private boolean rainbow;
    
    public void setColor(final ColorUtil c) {
        this.red = c.getRed();
        this.green = c.getGreen();
        this.blue = c.getBlue();
    }
    
    public ColorUtil getColor() {
        if (this.rainbow) {
            return getRainbow(0, this.getColor().getAlpha());
        }
        return new ColorUtil(this.red, this.green, this.blue);
    }
    
    public ColorSetting(final String name, final Module parent, final ColorUtil value) {
        this.name = name;
        this.parent = parent;
        if (!Main.configLoaded) {
            this.red = value.getRed();
            this.green = value.getGreen();
            this.blue = value.getBlue();
        }
    }
    
    public static ColorUtil getRainbow(final int incr, final int alpha) {
        final ColorUtil color = ColorUtil.fromHSB((System.currentTimeMillis() + incr * 200) % 7200L / 7200.0f, 0.5f, 1.0f);
        return new ColorUtil(color.getRed(), color.getBlue(), color.getGreen(), alpha);
    }
}
