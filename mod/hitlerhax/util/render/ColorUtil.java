// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.render;

import net.minecraft.client.renderer.GlStateManager;
import java.awt.Color;

public class ColorUtil extends Color
{
    private static final long serialVersionUID = 1L;
    
    public static ColorUtil getRainbow(final int incr, final int alpha) {
        final ColorUtil color = fromHSB((System.currentTimeMillis() + incr * 200) % 7200L / 7200.0f, 0.5f, 1.0f);
        return new ColorUtil(color.getRed(), color.getBlue(), color.getGreen(), alpha);
    }
    
    public ColorUtil(final int rgb) {
        super(rgb);
    }
    
    public ColorUtil(final int rgba, final boolean hasalpha) {
        super(rgba, hasalpha);
    }
    
    public ColorUtil(final int r, final int g, final int b) {
        super(r, g, b);
    }
    
    public ColorUtil(final int r, final int g, final int b, final int a) {
        super(r, g, b, a);
    }
    
    public ColorUtil(final Color color) {
        super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public ColorUtil(final ColorUtil color, final int a) {
        super(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
    
    public static ColorUtil fromHSB(final float hue, final float saturation, final float brightness) {
        return new ColorUtil(Color.getHSBColor(hue, saturation, brightness));
    }
    
    public void glColor() {
        GlStateManager.func_179131_c(this.getRed() / 255.0f, this.getGreen() / 255.0f, this.getBlue() / 255.0f, this.getAlpha() / 255.0f);
    }
}
