// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.gui.ScaledResolution;
import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventRender extends HtlrEventCancellable
{
    private final ScaledResolution res;
    private final Tessellator tessellator;
    private final Vec3d render_pos;
    private final float partialTicks;
    
    public HtlrEventRender(final Tessellator tessellator, final Vec3d pos, final float partialTicks) {
        this.res = new ScaledResolution(HtlrEventRender.mc);
        this.partialTicks = partialTicks;
        this.tessellator = tessellator;
        this.render_pos = pos;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public Tessellator get_tessellator() {
        return this.tessellator;
    }
    
    public Vec3d get_render_pos() {
        return this.render_pos;
    }
    
    public BufferBuilder get_buffer_build() {
        return this.tessellator.func_178180_c();
    }
    
    public void set_translation(final Vec3d pos) {
        this.get_buffer_build().func_178969_c(-pos.field_72450_a, -pos.field_72448_b, -pos.field_72449_c);
    }
    
    public void reset_translation() {
        this.set_translation(this.render_pos);
    }
    
    public double get_screen_width() {
        return this.res.func_78327_c();
    }
    
    public double get_screen_height() {
        return this.res.func_78324_d();
    }
}
