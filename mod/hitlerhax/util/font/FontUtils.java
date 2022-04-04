// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.font;

import mod.hitlerhax.Main;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.util.Globals;

public class FontUtils implements Globals
{
    public static void drawStringWithShadow(final String text, final int x, final int y, final ColorUtil color) {
        if (Main.moduleManager.getModule("ClientFont").toggled && Main.customFontRenderer != null) {
            Main.customFontRenderer.drawStringWithShadow(text, x, y, color);
        }
        else {
            FontUtils.mc.field_71466_p.func_175063_a(text, (float)x, (float)y, color.getRGB());
        }
    }
    
    public static int getStringWidth(final String string) {
        if (Main.moduleManager.getModule("ClientFont").toggled && Main.customFontRenderer != null) {
            return Main.customFontRenderer.getStringWidth(string);
        }
        return FontUtils.mc.field_71466_p.func_78256_a(string);
    }
    
    public static int getCharWidth(final char ch) {
        if (Main.moduleManager.getModule("ClientFont").toggled && Main.customFontRenderer != null) {
            return Main.customFontRenderer.getStringWidth(String.valueOf(ch));
        }
        return FontUtils.mc.field_71466_p.func_78263_a(ch);
    }
    
    public static int getFontHeight() {
        if (Main.moduleManager.getModule("ClientFont").toggled && Main.customFontRenderer != null) {
            return Main.customFontRenderer.getHeight();
        }
        return FontUtils.mc.field_71466_p.field_78288_b;
    }
    
    public static int drawStringWithShadow(final String text, final double x, final double y, final int color) {
        if (Main.moduleManager.getModule("ClientFont").toggled && Main.customFontRenderer != null) {
            return (int)Main.customFontRenderer.drawStringWithShadow(text, x, y, new ColorUtil(color));
        }
        return FontUtils.mc.field_71466_p.func_175063_a(text, (float)x, (float)y, color);
    }
    
    public static float drawString(final String text, final int x, final int y, final ColorUtil color) {
        if (Main.moduleManager.getModule("ClientFont").toggled && Main.customFontRenderer != null) {
            return Main.customFontRenderer.drawString(text, (float)x, (float)y, color);
        }
        return (float)FontUtils.mc.field_71466_p.func_78276_b(text, x, y, color.getRGB());
    }
    
    public static String trimStringToWidth(final String text, final int width) {
        return trimStringToWidth(text, width, false);
    }
    
    public static String trimStringToWidth(final String text, final int width, final boolean reverse) {
        final StringBuilder stringbuilder = new StringBuilder();
        int i = 0;
        final int j = reverse ? (text.length() - 1) : 0;
        final int k = reverse ? -1 : 1;
        boolean flag = false;
        boolean flag2 = false;
        for (int l = j; l >= 0 && l < text.length() && i < width; l += k) {
            final char c0 = text.charAt(l);
            final int i2 = getCharWidth(c0);
            if (flag) {
                flag = false;
                if (c0 != 'l' && c0 != 'L') {
                    if (c0 == 'r' || c0 == 'R') {
                        flag2 = false;
                    }
                }
                else {
                    flag2 = true;
                }
            }
            else if (i2 < 0) {
                flag = true;
            }
            else {
                i += i2;
                if (flag2) {
                    ++i;
                }
            }
            if (i > width) {
                break;
            }
            if (reverse) {
                stringbuilder.insert(0, c0);
            }
            else {
                stringbuilder.append(c0);
            }
        }
        return stringbuilder.toString();
    }
}
