// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventMotionUpdate extends HtlrEventCancellable
{
    public final int stage;
    
    public HtlrEventMotionUpdate(final int stage) {
        this.stage = stage;
    }
    
    public int getStage() {
        return this.stage;
    }
}
