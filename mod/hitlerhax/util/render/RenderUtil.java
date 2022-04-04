// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.render;

import mod.hitlerhax.misc.RenderBuilder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import mod.hitlerhax.util.font.FontUtils;
import net.minecraft.util.math.Vec3d;
import mod.hitlerhax.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderManager;
import mod.hitlerhax.util.MathUtil;
import java.awt.Color;
import javax.vecmath.Vector3d;
import java.util.Arrays;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.AxisAlignedBB;
import mod.hitlerhax.util.Globals;
import net.minecraft.client.renderer.Tessellator;

public class RenderUtil extends Tessellator implements Globals
{
    private static final AxisAlignedBB DEFAULT_AABB;
    public static final RenderUtil INSTANCE;
    public static final Tessellator tessellator;
    public static final BufferBuilder bufferbuilder;
    
    public RenderUtil() {
        super(2097152);
    }
    
    public static void prepare(final String mode_requested) {
        int mode = 0;
        if (mode_requested.equalsIgnoreCase("quads")) {
            mode = 7;
        }
        else if (mode_requested.equalsIgnoreCase("lines")) {
            mode = 1;
        }
        prepare_gl();
        begin(mode);
    }
    
    public static void prepare_gl() {
        GL11.glBlendFunc(770, 771);
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_187441_d(1.5f);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179124_c(1.0f, 1.0f, 1.0f);
    }
    
    public static void prepare_new() {
        GL11.glHint(3154, 4354);
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179103_j(7425);
        GlStateManager.func_179132_a(false);
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GL11.glEnable(2848);
        GL11.glEnable(34383);
    }
    
    public static void begin(final int mode) {
        RenderUtil.INSTANCE.func_178180_c().func_181668_a(mode, DefaultVertexFormats.field_181706_f);
    }
    
    public static void release() {
        render();
        release_gl();
    }
    
    public static void render() {
        RenderUtil.INSTANCE.func_78381_a();
    }
    
    public static void release_gl() {
        GlStateManager.func_179089_o();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179098_w();
        GlStateManager.func_179147_l();
        GlStateManager.func_179126_j();
    }
    
    public static void release_new() {
        GL11.glDisable(34383);
        GL11.glDisable(2848);
        GlStateManager.func_179141_d();
        GlStateManager.func_179089_o();
        GlStateManager.func_179145_e();
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
        GlStateManager.func_179084_k();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_187441_d(1.0f);
        GlStateManager.func_179103_j(7424);
        GL11.glHint(3154, 4352);
    }
    
    public static void drawLine(final float x, final float y, final float x1, final float y1, final float thickness, final int hex) {
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        final float alpha = (hex >> 24 & 0xFF) / 255.0f;
        GlStateManager.func_179094_E();
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GlStateManager.func_179103_j(7425);
        GL11.glLineWidth(thickness);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)x, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)x1, (double)y1, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179103_j(7424);
        GL11.glDisable(2848);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GlStateManager.func_179121_F();
    }
    
    public static void draw_cube(final BlockPos blockPos, final int argb, final String sides) {
        final int a = argb >>> 24 & 0xFF;
        final int r = argb >>> 16 & 0xFF;
        final int g = argb >>> 8 & 0xFF;
        final int b = argb & 0xFF;
        draw_cube(blockPos, r, g, b, a, sides);
    }
    
    public static void draw_cube(final float x, final float y, final float z, final int argb, final String sides) {
        final int a = argb >>> 24 & 0xFF;
        final int r = argb >>> 16 & 0xFF;
        final int g = argb >>> 8 & 0xFF;
        final int b = argb & 0xFF;
        draw_cube(RenderUtil.INSTANCE.func_178180_c(), x, y, z, 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
    }
    
    public static void draw_cube(final BlockPos blockPos, final int r, final int g, final int b, final int a, final String sides) {
        draw_cube(RenderUtil.INSTANCE.func_178180_c(), (float)blockPos.func_177958_n(), (float)blockPos.func_177956_o(), (float)blockPos.func_177952_p(), 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
    }
    
    public static void draw_cube_line(final BlockPos blockPos, final int argb, final String sides) {
        final int a = argb >>> 24 & 0xFF;
        final int r = argb >>> 16 & 0xFF;
        final int g = argb >>> 8 & 0xFF;
        final int b = argb & 0xFF;
        draw_cube_line(blockPos, r, g, b, a, sides);
    }
    
    public static void draw_cube_line(final float x, final float y, final float z, final int argb, final String sides) {
        final int a = argb >>> 24 & 0xFF;
        final int r = argb >>> 16 & 0xFF;
        final int g = argb >>> 8 & 0xFF;
        final int b = argb & 0xFF;
        draw_cube_line(RenderUtil.INSTANCE.func_178180_c(), x, y, z, 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
    }
    
    public static void draw_cube_line(final BlockPos blockPos, final int r, final int g, final int b, final int a, final String sides) {
        draw_cube_line(RenderUtil.INSTANCE.func_178180_c(), (float)blockPos.func_177958_n(), (float)blockPos.func_177956_o(), (float)blockPos.func_177952_p(), 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
    }
    
    public static BufferBuilder get_buffer_build() {
        return RenderUtil.INSTANCE.func_178180_c();
    }
    
    public static void draw_cube(final BufferBuilder buffer, final float x, final float y, final float z, final float w, final float h, final float d, final int r, final int g, final int b, final int a, final String sides) {
        if (Arrays.asList(sides.split("-")).contains("down") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("up") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("north") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("south") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("south") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("south") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
    }
    
    public static void draw_cube_line(final BufferBuilder buffer, final float x, final float y, final float z, final float w, final float h, final float d, final int r, final int g, final int b, final int a, final String sides) {
        if (Arrays.asList(sides.split("-")).contains("downwest") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("upwest") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("downeast") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("upeast") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("downnorth") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("upnorth") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("downsouth") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("upsouth") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("nortwest") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("norteast") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("southweast") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
        if (Arrays.asList(sides.split("-")).contains("southeast") || sides.equalsIgnoreCase("all")) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }
    }
    
    public static void Line(double sx, double sy, double sz, final double ex, final double ey, final double ez, final Vector3d plypos, final Color color, final float width) {
        final float r = MathUtil.interpolate((float)color.getRed(), 0.0f, 255.0f, 0.0f, 1.0f);
        final float g = MathUtil.interpolate((float)color.getGreen(), 0.0f, 255.0f, 0.0f, 1.0f);
        final float b = MathUtil.interpolate((float)color.getBlue(), 0.0f, 255.0f, 0.0f, 1.0f);
        final double rx = ex - sx;
        final double ry = ey - sy;
        final double rz = ez - sz;
        sx += -plypos.getX();
        sy += -plypos.getY();
        sz += -plypos.getZ();
        GL11.glPushMatrix();
        GL11.glTranslated(sx, sy, sz);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glClear(256);
        GL11.glLineWidth(width);
        GL11.glEnable(2848);
        GL11.glColor4f(r, g, b, 1.0f);
        GL11.glBegin(1);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(rx, ry, rz);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glBlendFunc(1, 0);
        GL11.glPopMatrix();
    }
    
    public static void Prism(double x, double y, double z, final double width, final double height, final double depth, final Vector3d plypos, final Color color) {
        final float r = MathUtil.interpolate((float)color.getRed(), 0.0f, 255.0f, 0.0f, 1.0f);
        final float g = MathUtil.interpolate((float)color.getGreen(), 0.0f, 255.0f, 0.0f, 1.0f);
        final float b = MathUtil.interpolate((float)color.getBlue(), 0.0f, 255.0f, 0.0f, 1.0f);
        final double wh = width / 2.0;
        final double hh = height / 2.0;
        final double dh = depth / 2.0;
        x += -plypos.getX();
        y += -plypos.getY();
        z += -plypos.getZ();
        GL11.glPushMatrix();
        GL11.glClear(256);
        GL11.glTranslated(x, y, z);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(r, g, b, 0.25f);
        GL11.glBegin(7);
        GL11.glVertex3d(wh, hh, -dh);
        GL11.glVertex3d(wh, -hh, -dh);
        GL11.glVertex3d(-wh, -hh, -dh);
        GL11.glVertex3d(-wh, hh, -dh);
        GL11.glVertex3d(wh, hh, dh);
        GL11.glVertex3d(wh, -hh, dh);
        GL11.glVertex3d(wh, -hh, -dh);
        GL11.glVertex3d(wh, hh, -dh);
        GL11.glVertex3d(-wh, hh, dh);
        GL11.glVertex3d(-wh, -hh, dh);
        GL11.glVertex3d(wh, -hh, dh);
        GL11.glVertex3d(wh, hh, dh);
        GL11.glVertex3d(-wh, hh, -dh);
        GL11.glVertex3d(-wh, -hh, -dh);
        GL11.glVertex3d(-wh, -hh, dh);
        GL11.glVertex3d(-wh, hh, dh);
        GL11.glVertex3d(wh, -hh, -dh);
        GL11.glVertex3d(wh, -hh, dh);
        GL11.glVertex3d(-wh, -hh, dh);
        GL11.glVertex3d(-wh, -hh, -dh);
        GL11.glVertex3d(-wh, hh, dh);
        GL11.glVertex3d(wh, hh, dh);
        GL11.glVertex3d(wh, hh, -dh);
        GL11.glVertex3d(-wh, hh, -dh);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glBlendFunc(1, 0);
        GL11.glPopMatrix();
    }
    
    public static void OutlinedPrism(double x, double y, double z, final double width, final double height, final double depth, final Vector3d plypos, final Color color, final float lwidth) {
        final float r = MathUtil.interpolate((float)color.getRed(), 0.0f, 255.0f, 0.0f, 1.0f);
        final float g = MathUtil.interpolate((float)color.getGreen(), 0.0f, 255.0f, 0.0f, 1.0f);
        final float b = MathUtil.interpolate((float)color.getBlue(), 0.0f, 255.0f, 0.0f, 1.0f);
        final double wh = width / 2.0;
        final double hh = height / 2.0;
        final double dh = depth / 2.0;
        x += -plypos.getX();
        y += -plypos.getY();
        z += -plypos.getZ();
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(lwidth);
        GL11.glEnable(2848);
        GL11.glColor4f(r, g, b, 1.0f);
        GL11.glBegin(3);
        GL11.glVertex3d(-wh, -hh, -dh);
        GL11.glVertex3d(-wh, hh, -dh);
        GL11.glVertex3d(wh, hh, -dh);
        GL11.glVertex3d(wh, -hh, -dh);
        GL11.glVertex3d(wh, -hh, dh);
        GL11.glVertex3d(wh, hh, dh);
        GL11.glVertex3d(-wh, hh, dh);
        GL11.glVertex3d(-wh, -hh, dh);
        GL11.glVertex3d(-wh, -hh, -dh);
        GL11.glVertex3d(wh, -hh, -dh);
        GL11.glVertex3d(wh, hh, -dh);
        GL11.glVertex3d(wh, hh, dh);
        GL11.glVertex3d(wh, -hh, dh);
        GL11.glVertex3d(-wh, -hh, dh);
        GL11.glVertex3d(-wh, hh, dh);
        GL11.glVertex3d(-wh, hh, -dh);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glBlendFunc(1, 0);
        GL11.glPopMatrix();
    }
    
    public static void Text(double x, double y, double z, final String text, final Vector3d plypos, final Color color) {
        final float r = MathUtil.interpolate((float)color.getRed(), 0.0f, 255.0f, 0.0f, 1.0f);
        final float g = MathUtil.interpolate((float)color.getGreen(), 0.0f, 255.0f, 0.0f, 1.0f);
        final float b = MathUtil.interpolate((float)color.getBlue(), 0.0f, 255.0f, 0.0f, 1.0f);
        final RenderManager rm = RenderUtil.mc.func_175598_ae();
        x += -plypos.getX();
        y += -plypos.getY();
        z += -plypos.getZ();
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(-rm.field_78735_i, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(rm.field_78732_j, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-0.05f, -0.05f, 0.05f);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glClear(256);
        GL11.glColor4f(r, g, b, 1.0f);
        RenderUtil.mc.field_71466_p.func_78276_b(text, -RenderUtil.mc.field_71466_p.func_78256_a(text) / 2, -RenderUtil.mc.field_71466_p.field_78288_b / 2, color.getRGB());
        GL11.glBlendFunc(1, 0);
        GL11.glPopMatrix();
    }
    
    public static void drawRect(final float x, final float y, final float w, final float h, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)x, (double)h, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)h, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)x, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawRect(final float x, final float y, final float w, final float h, final float r, final float g, final float b, final float a) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)x, (double)h, 0.0).func_181666_a(r / 255.0f, g / 255.0f, b / 255.0f, a).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)h, 0.0).func_181666_a(r / 255.0f, g / 255.0f, b / 255.0f, a).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)y, 0.0).func_181666_a(r / 255.0f, g / 255.0f, b / 255.0f, a).func_181675_d();
        bufferbuilder.func_181662_b((double)x, (double)y, 0.0).func_181666_a(r / 255.0f, g / 255.0f, b / 255.0f, a).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawLine3D(final float x, final float y, final float z, final float x1, final float y1, final float z1, final float thickness, final int hex) {
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        final float alpha = (hex >> 24 & 0xFF) / 255.0f;
        GlStateManager.func_179094_E();
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GlStateManager.func_179103_j(7425);
        GL11.glLineWidth(thickness);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GlStateManager.func_179097_i();
        GL11.glEnable(34383);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(1, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)x, (double)y, (double)z).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)x1, (double)y1, (double)z1).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179103_j(7424);
        GL11.glDisable(2848);
        GlStateManager.func_179126_j();
        GL11.glDisable(34383);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GlStateManager.func_179121_F();
    }
    
    private static void drawBorderedRect(final double x, final double y, final double x1, final ColorUtil inside, final ColorUtil border) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        inside.glColor();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        bufferbuilder.func_181662_b(x, 1.0, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x1, 1.0, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x1, y, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x, y, 0.0).func_181675_d();
        tessellator.func_78381_a();
        border.glColor();
        GlStateManager.func_187441_d(1.8f);
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        bufferbuilder.func_181662_b(x, y, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x, 1.0, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x1, 1.0, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x1, y, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x, y, 0.0).func_181675_d();
        tessellator.func_78381_a();
    }
    
    public static void drawNametag(final Entity entity, final String[] text, final ColorUtil color, final int type) {
        final Vec3d pos = EntityUtil.getInterpolatedPos(entity, RenderUtil.mc.func_184121_ak());
        drawNametag(pos.field_72450_a, pos.field_72448_b + entity.field_70131_O, pos.field_72449_c, text, color, type);
    }
    
    public static void drawNametag(final double x, final double y, final double z, final String[] text, final ColorUtil color, final int type) {
        final double dist = RenderUtil.mc.field_71439_g.func_70011_f(x, y, z);
        double scale = 1.0;
        double offset = 0.0;
        int start = 0;
        switch (type) {
            case 0: {
                scale = dist / 20.0 * Math.pow(1.2589254, 0.1 / ((dist < 25.0) ? 0.5 : 2.0));
                scale = Math.min(Math.max(scale, 0.5), 5.0);
                offset = ((scale > 2.0) ? (scale / 2.0) : scale);
                scale /= 40.0;
                start = 10;
                break;
            }
            case 1: {
                scale = -(int)dist / 6.0;
                if (scale < 1.0) {
                    scale = 1.0;
                }
                scale *= 0.02666666666666667;
                break;
            }
            case 2: {
                scale = 0.002 + 0.00349 * dist;
                if (dist <= 8.0) {
                    scale = 0.0265;
                }
                start = -8;
                break;
            }
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x - RenderUtil.mc.func_175598_ae().field_78730_l, y + offset - RenderUtil.mc.func_175598_ae().field_78731_m, z - RenderUtil.mc.func_175598_ae().field_78728_n);
        GlStateManager.func_179114_b(-RenderUtil.mc.func_175598_ae().field_78735_i, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(RenderUtil.mc.func_175598_ae().field_78732_j, (RenderUtil.mc.field_71474_y.field_74320_O == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179139_a(-scale, -scale, scale);
        if (type == 2) {
            double width = 0.0;
            final ColorUtil bcolor = new ColorUtil(255, 0, 200, 100);
            for (final String s : text) {
                final double w = FontUtils.getStringWidth(s) / 2;
                if (w > width) {
                    width = w;
                }
            }
            drawBorderedRect(-width - 1.0, -RenderUtil.mc.field_71466_p.field_78288_b, width + 3.0, new ColorUtil(110, 4, 0, 100), bcolor);
        }
        GlStateManager.func_179098_w();
        for (int i = 0; i < text.length; ++i) {
            FontUtils.drawStringWithShadow(text[i], -FontUtils.getStringWidth(text[i]) / 2, i * (RenderUtil.mc.field_71466_p.field_78288_b + 1) + start, color);
        }
        GlStateManager.func_179090_x();
        if (type != 2) {
            GlStateManager.func_179121_F();
        }
    }
    
    public static void drawSolidBox() {
        drawSolidBox(RenderUtil.DEFAULT_AABB);
    }
    
    public static void drawSolidBox(final AxisAlignedBB bb) {
        GL11.glBegin(7);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glEnd();
    }
    
    public static void drawOutlinedBox() {
        drawOutlinedBox(RenderUtil.DEFAULT_AABB);
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB bb) {
        GL11.glBegin(1);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glEnd();
    }
    
    public static void drawBlockESP(final BlockPos pos, final float red, final float green, final float blue) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        final double renderPosX = RenderUtil.mc.func_175598_ae().field_78730_l;
        final double renderPosY = RenderUtil.mc.func_175598_ae().field_78731_m;
        final double renderPosZ = RenderUtil.mc.func_175598_ae().field_78728_n;
        GL11.glTranslated(-renderPosX, -renderPosY, -renderPosZ);
        GL11.glTranslated((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p());
        GL11.glColor4f(red, green, blue, 0.3f);
        drawSolidBox();
        GL11.glColor4f(red, green, blue, 0.7f);
        drawOutlinedBox();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static String DF(final Number value, final int maxvalue) {
        final DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(maxvalue);
        String ret = df.format(value);
        if (!ret.contains(".")) {
            ret += ".0";
        }
        return ret;
    }
    
    public static void drawBox(final BlockPos blockPos, final double height, final ColorUtil color, final int sides) {
        drawBox(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), 1.0, height, 1.0, color, sides);
    }
    
    public static void drawBox(final AxisAlignedBB bb, final boolean check, final double height, final ColorUtil color, final int sides) {
        if (check) {
            drawBox(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bb.field_72336_d - bb.field_72340_a, bb.field_72337_e - bb.field_72338_b, bb.field_72334_f - bb.field_72339_c, color, sides);
        }
        else {
            drawBox(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bb.field_72336_d - bb.field_72340_a, height, bb.field_72334_f - bb.field_72339_c, color, sides);
        }
    }
    
    public static void drawBox(final double x, final double y, final double z, final double w, final double h, final double d, final ColorUtil color, final int sides) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        color.glColor();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        if ((sides & 0x1) != 0x0) {
            vertex(x + w, y, z, bufferbuilder);
            vertex(x + w, y, z + d, bufferbuilder);
            vertex(x, y, z + d, bufferbuilder);
            vertex(x, y, z, bufferbuilder);
        }
        if ((sides & 0x2) != 0x0) {
            vertex(x + w, y + h, z, bufferbuilder);
            vertex(x, y + h, z, bufferbuilder);
            vertex(x, y + h, z + d, bufferbuilder);
            vertex(x + w, y + h, z + d, bufferbuilder);
        }
        if ((sides & 0x4) != 0x0) {
            vertex(x + w, y, z, bufferbuilder);
            vertex(x, y, z, bufferbuilder);
            vertex(x, y + h, z, bufferbuilder);
            vertex(x + w, y + h, z, bufferbuilder);
        }
        if ((sides & 0x8) != 0x0) {
            vertex(x, y, z + d, bufferbuilder);
            vertex(x + w, y, z + d, bufferbuilder);
            vertex(x + w, y + h, z + d, bufferbuilder);
            vertex(x, y + h, z + d, bufferbuilder);
        }
        if ((sides & 0x10) != 0x0) {
            vertex(x, y, z, bufferbuilder);
            vertex(x, y, z + d, bufferbuilder);
            vertex(x, y + h, z + d, bufferbuilder);
            vertex(x, y + h, z, bufferbuilder);
        }
        if ((sides & 0x20) != 0x0) {
            vertex(x + w, y, z + d, bufferbuilder);
            vertex(x + w, y, z, bufferbuilder);
            vertex(x + w, y + h, z, bufferbuilder);
            vertex(x + w, y + h, z + d, bufferbuilder);
        }
        tessellator.func_78381_a();
    }
    
    private static void vertex(final double x, final double y, final double z, final BufferBuilder bufferbuilder) {
        bufferbuilder.func_181662_b(x - RenderUtil.mc.func_175598_ae().field_78730_l, y - RenderUtil.mc.func_175598_ae().field_78731_m, z - RenderUtil.mc.func_175598_ae().field_78728_n).func_181675_d();
    }
    
    public static void drawBoundingBox(final BlockPos bp, final double height, final float width, final ColorUtil color) {
        drawBoundingBox(getBoundingBox(bp, height), width, color);
    }
    
    public static void drawBoundingBox(final AxisAlignedBB bb, final float width, final ColorUtil color) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_187441_d(width);
        color.glColor();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertex(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c, bufferbuilder);
        tessellator.func_78381_a();
    }
    
    public static void drawBoundingBox(final AxisAlignedBB bb, final float width, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        drawBoundingBox(bb, width, red, green, blue, alpha);
    }
    
    public static void drawBoundingBox(final AxisAlignedBB bb, final float width, final float red, final float green, final float blue, final float alpha) {
        GL11.glLineWidth(width);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        tessellator.func_78381_a();
    }
    
    public static void drawFilledBox(final AxisAlignedBB bb, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
    }
    
    private static AxisAlignedBB getBoundingBox(final BlockPos bp, final double height) {
        final double x = bp.func_177958_n();
        final double y = bp.func_177956_o();
        final double z = bp.func_177952_p();
        return new AxisAlignedBB(x, y, z, x + 1.0, y + height, z + 1.0);
    }
    
    public static void playerEsp(final BlockPos bp, final double height, final float width, final ColorUtil color) {
        drawBoundingBox(getBoundingBox(bp, height), width, color);
    }
    
    public static void playerEsp(final AxisAlignedBB bb, final float width, final ColorUtil color) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_187441_d(width);
        color.glColor();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertex(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c, bufferbuilder);
        vertex(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c, bufferbuilder);
        tessellator.func_78381_a();
    }
    
    public static void drawPlayerBox(final AxisAlignedBB bb, final float width, final ColorUtil color, final int sides) {
        drawPlayerBox(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bb.field_72336_d - bb.field_72340_a, bb.field_72337_e - bb.field_72338_b, bb.field_72334_f - bb.field_72339_c, color, sides);
    }
    
    public static void drawPlayerBox(final double x, final double y, final double z, final double w, final double h, final double d, final ColorUtil color, final int sides) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        color.glColor();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        if ((sides & 0x1) != 0x0) {
            vertex(x + w, y, z, bufferbuilder);
            vertex(x + w, y, z + d, bufferbuilder);
            vertex(x, y, z + d, bufferbuilder);
            vertex(x, y, z, bufferbuilder);
        }
        if ((sides & 0x2) != 0x0) {
            vertex(x + w, y + h, z, bufferbuilder);
            vertex(x, y + h, z, bufferbuilder);
            vertex(x, y + h, z + d, bufferbuilder);
            vertex(x + w, y + h, z + d, bufferbuilder);
        }
        if ((sides & 0x4) != 0x0) {
            vertex(x + w, y, z, bufferbuilder);
            vertex(x, y, z, bufferbuilder);
            vertex(x, y + h, z, bufferbuilder);
            vertex(x + w, y + h, z, bufferbuilder);
        }
        if ((sides & 0x8) != 0x0) {
            vertex(x, y, z + d, bufferbuilder);
            vertex(x + w, y, z + d, bufferbuilder);
            vertex(x + w, y + h, z + d, bufferbuilder);
            vertex(x, y + h, z + d, bufferbuilder);
        }
        if ((sides & 0x10) != 0x0) {
            vertex(x, y, z, bufferbuilder);
            vertex(x, y, z + d, bufferbuilder);
            vertex(x, y + h, z + d, bufferbuilder);
            vertex(x, y + h, z, bufferbuilder);
        }
        if ((sides & 0x20) != 0x0) {
            vertex(x + w, y, z + d, bufferbuilder);
            vertex(x + w, y, z, bufferbuilder);
            vertex(x + w, y + h, z, bufferbuilder);
            vertex(x + w, y + h, z + d, bufferbuilder);
        }
        tessellator.func_78381_a();
    }
    
    public static void drawStorageBox(final BlockPos blockPos, final double height, final ColorUtil color, final int sides) {
        drawStorageBox(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), 1.0, height, 1.0, color, sides);
    }
    
    public static void drawStorageBox(final AxisAlignedBB bb, final boolean check, final double height, final ColorUtil color, final int sides) {
        if (check) {
            drawStorageBox(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bb.field_72336_d - bb.field_72340_a, bb.field_72337_e - bb.field_72338_b, bb.field_72334_f - bb.field_72339_c, color, sides);
        }
        else {
            drawStorageBox(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bb.field_72336_d - bb.field_72340_a, height, bb.field_72334_f - bb.field_72339_c, color, sides);
        }
    }
    
    public static void drawStorageBox(final double x, final double y, final double z, final double w, final double h, final double d, final ColorUtil color, final int sides) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        color.glColor();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        if ((sides & 0x1) != 0x0) {
            vertex(x + w - 0.06, y, z + 0.06, bufferbuilder);
            vertex(x + w - 0.06, y, z + d - 0.06, bufferbuilder);
            vertex(x + 0.06, y, z + d - 0.06, bufferbuilder);
            vertex(x + 0.06, y, z + 0.06, bufferbuilder);
        }
        if ((sides & 0x2) != 0x0) {
            vertex(x + w - 0.06, y + h, z + 0.06, bufferbuilder);
            vertex(x + 0.06, y + h, z + 0.06, bufferbuilder);
            vertex(x + 0.06, y + h, z + d - 0.06, bufferbuilder);
            vertex(x + w - 0.06, y + h, z + d - 0.06, bufferbuilder);
        }
        if ((sides & 0x4) != 0x0) {
            vertex(x + w - 0.06, y, z + 0.06, bufferbuilder);
            vertex(x + 0.06, y, z + 0.06, bufferbuilder);
            vertex(x + 0.06, y + h, z + 0.06, bufferbuilder);
            vertex(x + w - 0.06, y + h, z + 0.06, bufferbuilder);
        }
        if ((sides & 0x8) != 0x0) {
            vertex(x + 0.06, y, z + d - 0.06, bufferbuilder);
            vertex(x + w - 0.06, y, z + d - 0.06, bufferbuilder);
            vertex(x + w - 0.06, y + h, z + d - 0.06, bufferbuilder);
            vertex(x + 0.06, y + h, z + d - 0.06, bufferbuilder);
        }
        if ((sides & 0x10) != 0x0) {
            vertex(x + 0.06, y, z + 0.06, bufferbuilder);
            vertex(x + 0.06, y, z + d - 0.06, bufferbuilder);
            vertex(x + 0.06, y + h, z + d - 0.06, bufferbuilder);
            vertex(x + 0.06, y + h, z + 0.06, bufferbuilder);
        }
        if ((sides & 0x20) != 0x0) {
            vertex(x + w - 0.06, y, z + d - 0.06, bufferbuilder);
            vertex(x + w - 0.06, y, z + 0.06, bufferbuilder);
            vertex(x + w - 0.06, y + h, z + 0.06, bufferbuilder);
            vertex(x + w - 0.06, y + h, z + d - 0.06, bufferbuilder);
        }
        tessellator.func_78381_a();
    }
    
    public static void drawBoundingBoxWithSides(final BlockPos blockPos, final int width, final ColorUtil color, final int sides) {
        drawBoundingBoxWithSides(getBoundingBox(blockPos, 1.0), width, color, sides);
    }
    
    public static void drawBoundingBoxWithSides(final AxisAlignedBB axisAlignedBB, final int width, final ColorUtil color, final int sides) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_187441_d((float)width);
        color.glColor();
        final double w = axisAlignedBB.field_72336_d - axisAlignedBB.field_72340_a;
        final double h = axisAlignedBB.field_72337_e - axisAlignedBB.field_72338_b;
        final double d = axisAlignedBB.field_72334_f - axisAlignedBB.field_72339_c;
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        if ((sides & 0x20) != 0x0) {
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c + d, bufferbuilder);
        }
        if ((sides & 0x10) != 0x0) {
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, bufferbuilder);
        }
        if ((sides & 0x4) != 0x0) {
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, bufferbuilder);
        }
        if ((sides & 0x8) != 0x0) {
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c + d, bufferbuilder);
        }
        if ((sides & 0x2) != 0x0) {
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b + h, axisAlignedBB.field_72339_c, bufferbuilder);
        }
        if ((sides & 0x1) != 0x0) {
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c + d, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, bufferbuilder);
            vertex(axisAlignedBB.field_72340_a + w, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, bufferbuilder);
        }
        tessellator.func_78381_a();
    }
    
    public static void drawSelectionBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color color) {
        RenderUtil.bufferbuilder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
        addChainedFilledBoxVertices(RenderUtil.bufferbuilder, axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, axisAlignedBB.field_72336_d + length, axisAlignedBB.field_72337_e + height, axisAlignedBB.field_72334_f + width, color);
        RenderUtil.tessellator.func_78381_a();
    }
    
    public static void addChainedFilledBoxVertices(final BufferBuilder builder, final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final Color color) {
        builder.func_181662_b(minX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(minX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        builder.func_181662_b(maxX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color color) {
        RenderUtil.bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        addChainedBoundingBoxVertices(RenderUtil.bufferbuilder, axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, axisAlignedBB.field_72336_d + length, axisAlignedBB.field_72337_e + height, axisAlignedBB.field_72334_f + width, color);
        RenderUtil.tessellator.func_78381_a();
    }
    
    public static void addChainedBoundingBoxVertices(final BufferBuilder buffer, final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final Color color) {
        buffer.func_181662_b(minX, minY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, minY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
    }
    
    public static void drawSelectionGlowFilledBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color startColor, final Color endColor) {
        RenderUtil.bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        addChainedGlowBoxVertices(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, axisAlignedBB.field_72336_d + length, axisAlignedBB.field_72337_e + height, axisAlignedBB.field_72334_f + width, startColor, endColor);
        RenderUtil.tessellator.func_78381_a();
    }
    
    public static void addChainedGlowBoxVertices(final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final Color startColor, final Color endColor) {
        RenderUtil.bufferbuilder.func_181662_b(minX, minY, minZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, minY, minZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, minY, maxZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, minY, maxZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, maxY, minZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, maxY, maxZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, maxY, maxZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, maxY, minZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, minY, minZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, maxY, minZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, maxY, minZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, minY, minZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, minY, minZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, maxY, minZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, maxY, maxZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, minY, maxZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, minY, maxZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, minY, maxZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(maxX, maxY, maxZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, maxY, maxZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, minY, minZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, minY, maxZ).func_181666_a(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, maxY, maxZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
        RenderUtil.bufferbuilder.func_181662_b(minX, maxY, minZ).func_181666_a(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).func_181675_d();
    }
    
    public static void drawClawBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color color) {
        RenderUtil.bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        addChainedClawBoxVertices(RenderUtil.bufferbuilder, axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, axisAlignedBB.field_72336_d + length, axisAlignedBB.field_72337_e + height, axisAlignedBB.field_72334_f + width, color);
        RenderUtil.tessellator.func_78381_a();
    }
    
    public static void addChainedClawBoxVertices(final BufferBuilder buffer, final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final Color color) {
        buffer.func_181662_b(minX, minY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, minY, maxZ - 0.8).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, minY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, minY, minZ + 0.8).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, minY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, minY, maxZ - 0.8).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, minY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, minY, minZ + 0.8).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, minY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX - 0.8, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, minY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX - 0.8, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, minY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX + 0.8, minY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, minY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX + 0.8, minY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, minY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, minY + 0.2, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, minY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, minY + 0.2, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, minY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, minY + 0.2, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, minY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, minY + 0.2, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, maxY, maxZ - 0.8).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, maxY, minZ + 0.8).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, maxY, maxZ - 0.8).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, maxY, minZ + 0.8).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX - 0.8, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX - 0.8, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX + 0.8, maxY, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX + 0.8, maxY, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, maxY - 0.2, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(minX, maxY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(minX, maxY - 0.2, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, minZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, maxY - 0.2, minZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
        buffer.func_181662_b(maxX, maxY, maxZ).func_181666_a((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).func_181675_d();
        buffer.func_181662_b(maxX, maxY - 0.2, maxZ).func_181669_b(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
    }
    
    public static void drawBoxBlockPos(final BlockPos blockPos, final double height, final double length, final double width, final Color color, final RenderBuilder.RenderMode renderMode) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, blockPos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, blockPos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, blockPos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, blockPos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, blockPos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        RenderBuilder.glSetup();
        switch (renderMode) {
            case Fill: {
                drawSelectionBox(axisAlignedBB, height, length, width, color);
                break;
            }
            case Outline: {
                drawSelectionBoundingBox(axisAlignedBB, height, length, width, new Color(color.getRed(), color.getGreen(), color.getBlue(), 144));
                break;
            }
            case Both: {
                drawSelectionBox(axisAlignedBB, height, length, width, color);
                drawSelectionBoundingBox(axisAlignedBB, height, length, width, new Color(color.getRed(), color.getGreen(), color.getBlue(), 144));
                break;
            }
            case Glow: {
                RenderBuilder.glPrepare();
                drawSelectionGlowFilledBox(axisAlignedBB, height, length, width, color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));
                RenderBuilder.glRestore();
                break;
            }
            case Claw: {
                drawClawBox(axisAlignedBB, height, length, width, new Color(color.getRed(), color.getGreen(), color.getBlue(), 255));
                break;
            }
        }
        RenderBuilder.glRelease();
    }
    
    static {
        DEFAULT_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        INSTANCE = new RenderUtil();
        tessellator = Tessellator.func_178181_a();
        bufferbuilder = RenderUtil.tessellator.func_178180_c();
    }
}
