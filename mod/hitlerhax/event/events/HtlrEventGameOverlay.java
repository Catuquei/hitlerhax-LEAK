// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import net.minecraft.client.gui.ScaledResolution;
import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventGameOverlay extends HtlrEventCancellable
{
    public final float partial_ticks;
    private final ScaledResolution scaled_resolution;
    
    public HtlrEventGameOverlay(final float partial_ticks, final ScaledResolution scaled_resolution) {
        this.partial_ticks = partial_ticks;
        this.scaled_resolution = scaled_resolution;
    }
    
    public ScaledResolution get_scaled_resoltion() {
        return this.scaled_resolution;
    }
}
