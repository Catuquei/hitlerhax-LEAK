// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.font;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import java.awt.Color;
import mod.hitlerhax.util.render.ColorUtil;
import java.awt.Font;
import net.minecraft.client.renderer.texture.DynamicTexture;

public class HtlrFontRenderer extends HtlrFont
{
    protected final CharData[] boldChars;
    protected final CharData[] italicChars;
    protected final CharData[] boldItalicChars;
    private final int[] colorCode;
    protected DynamicTexture texBold;
    protected DynamicTexture texItalic;
    protected DynamicTexture texItalicBold;
    
    public HtlrFontRenderer(final Font font, final boolean antiAlias, final boolean fractionalMetrics) {
        super(font, antiAlias, fractionalMetrics);
        this.boldChars = new CharData[256];
        this.italicChars = new CharData[256];
        this.boldItalicChars = new CharData[256];
        this.colorCode = new int[32];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
    
    public float drawStringWithShadow(final String text, final double x, final double y, final ColorUtil color) {
        final float shadowWidth = this.drawString(text, x + 1.0, y + 1.0, color, true);
        return Math.max(shadowWidth, this.drawString(text, x, y, color, false));
    }
    
    public float drawString(final String text, final float x, final float y, final ColorUtil color) {
        return this.drawString(text, x, y, color, false);
    }
    
    public float drawString(final String text, double x, double y, final ColorUtil gsColor, final boolean shadow) {
        --x;
        y -= 2.0;
        ColorUtil color = new ColorUtil(gsColor);
        if (text == null) {
            return 0.0f;
        }
        if (color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255 && color.getAlpha() == 32) {
            color = new ColorUtil(255, 255, 255);
        }
        if (color.getAlpha() < 4) {
            color = new ColorUtil(color, 255);
        }
        if (shadow) {
            color = new ColorUtil(color.getRed() / 4, color.getGreen() / 4, color.getBlue() / 4, color.getAlpha());
        }
        CharData[] currentData = this.charData;
        boolean bold = false;
        boolean italic = false;
        boolean strikethrough = false;
        boolean underline = false;
        final boolean render = true;
        x *= 2.0;
        y *= 2.0;
        if (render) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179139_a(0.5, 0.5, 0.5);
            GlStateManager.func_179147_l();
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179131_c(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            final int size = text.length();
            GlStateManager.func_179098_w();
            GlStateManager.func_179144_i(this.tex.func_110552_b());
            for (int i = 0; i < size; ++i) {
                final char character = text.charAt(i);
                if (character == '§' && i < size) {
                    int colorIndex = 21;
                    try {
                        colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
                    }
                    catch (Exception ex) {}
                    if (colorIndex < 16) {
                        bold = false;
                        italic = false;
                        underline = false;
                        strikethrough = false;
                        GlStateManager.func_179144_i(this.tex.func_110552_b());
                        currentData = this.charData;
                        if (colorIndex < 0) {
                            colorIndex = 15;
                        }
                        if (shadow) {
                            colorIndex += 16;
                        }
                        final int colorcode = this.colorCode[colorIndex];
                        GlStateManager.func_179131_c((colorcode >> 16 & 0xFF) / 255.0f, (colorcode >> 8 & 0xFF) / 255.0f, (colorcode & 0xFF) / 255.0f, (float)color.getAlpha());
                    }
                    else if (colorIndex != 16) {
                        if (colorIndex == 17) {
                            bold = true;
                            if (italic) {
                                GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
                                currentData = this.boldItalicChars;
                            }
                            else {
                                GlStateManager.func_179144_i(this.texBold.func_110552_b());
                                currentData = this.boldChars;
                            }
                        }
                        else if (colorIndex == 18) {
                            strikethrough = true;
                        }
                        else if (colorIndex == 19) {
                            underline = true;
                        }
                        else if (colorIndex == 20) {
                            italic = true;
                            if (bold) {
                                GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
                                currentData = this.boldItalicChars;
                            }
                            else {
                                GlStateManager.func_179144_i(this.texItalic.func_110552_b());
                                currentData = this.italicChars;
                            }
                        }
                        else if (colorIndex == 21) {
                            bold = false;
                            italic = false;
                            underline = false;
                            strikethrough = false;
                            GlStateManager.func_179131_c(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
                            GlStateManager.func_179144_i(this.tex.func_110552_b());
                            currentData = this.charData;
                        }
                    }
                    ++i;
                }
                else if (character < currentData.length && character >= '\0') {
                    GlStateManager.func_187447_r(4);
                    this.drawChar(currentData, character, (float)x, (float)y);
                    GlStateManager.func_187437_J();
                    if (strikethrough) {
                        this.drawLine(x, y + currentData[character].height / 2, x + currentData[character].width - 8.0, y + currentData[character].height / 2, 1.0f);
                    }
                    if (underline) {
                        this.drawLine(x, y + currentData[character].height - 2.0, x + currentData[character].width - 8.0, y + currentData[character].height - 2.0, 1.0f);
                    }
                    final double n = x;
                    final int n2 = currentData[character].width - 8;
                    this.getClass();
                    x = n + (n2 + 0);
                }
            }
            GL11.glHint(3155, 4352);
            GlStateManager.func_179121_F();
        }
        return (float)x / 2.0f;
    }
    
    @Override
    public int getStringWidth(final String text) {
        if (text == null) {
            return 0;
        }
        int width = 0;
        CharData[] currentData = this.charData;
        boolean bold = false;
        boolean italic = false;
        for (int size = text.length(), i = 0; i < size; ++i) {
            final char character = text.charAt(i);
            if (character == '§' && i < size) {
                final int colorIndex = "0123456789abcdefklmnor".indexOf(character);
                if (colorIndex < 16) {
                    bold = false;
                    italic = false;
                }
                else if (colorIndex == 17) {
                    bold = true;
                    if (italic) {
                        currentData = this.boldItalicChars;
                    }
                    else {
                        currentData = this.boldChars;
                    }
                }
                else if (colorIndex == 20) {
                    italic = true;
                    if (bold) {
                        currentData = this.boldItalicChars;
                    }
                    else {
                        currentData = this.italicChars;
                    }
                }
                else if (colorIndex == 21) {
                    bold = false;
                    italic = false;
                    currentData = this.charData;
                }
                ++i;
            }
            else if (character < currentData.length && character >= '\0') {
                final int n = width;
                final int n2 = currentData[character].width - 8;
                this.getClass();
                width = n + (n2 + 0);
            }
        }
        return width / 2;
    }
    
    @Override
    public void setFont(final Font font) {
        super.setFont(font);
        this.setupBoldItalicIDs();
    }
    
    @Override
    public void setAntiAlias(final boolean antiAlias) {
        super.setAntiAlias(antiAlias);
        this.setupBoldItalicIDs();
    }
    
    @Override
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        super.setFractionalMetrics(fractionalMetrics);
        this.setupBoldItalicIDs();
    }
    
    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }
    
    private void drawLine(final double x, final double y, final double x1, final double y1, final float width) {
        GL11.glDisable(3553);
        GL11.glLineWidth(width);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x1, y1);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    private void setupMinecraftColorcodes() {
        for (int index = 0; index < 32; ++index) {
            final int noClue = (index >> 3 & 0x1) * 85;
            int red = (index >> 2 & 0x1) * 170 + noClue;
            int green = (index >> 1 & 0x1) * 170 + noClue;
            int blue = (index & 0x1) * 170 + noClue;
            if (index == 6) {
                red += 85;
            }
            if (index >= 16) {
                red /= 4;
                green /= 4;
                blue /= 4;
            }
            this.colorCode[index] = ((red & 0xFF) << 16 | (green & 0xFF) << 8 | (blue & 0xFF));
        }
    }
}
