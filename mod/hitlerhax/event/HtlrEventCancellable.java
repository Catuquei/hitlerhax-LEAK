// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event;

import mod.hitlerhax.util.Globals;
import me.zero.alpine.event.type.Cancellable;

public class HtlrEventCancellable extends Cancellable implements Globals
{
    private boolean canceled;
    private final Era era_switch;
    private final float partial_ticks;
    
    public HtlrEventCancellable() {
        this.era_switch = Era.EVENT_PRE;
        this.partial_ticks = HtlrEventCancellable.mc.func_184121_ak();
    }
    
    public Era get_era() {
        return this.era_switch;
    }
    
    public float get_partial_ticks() {
        return this.partial_ticks;
    }
    
    public HtlrEventCancellable(final boolean canceled) {
        this.era_switch = Era.EVENT_PRE;
        this.canceled = canceled;
        this.partial_ticks = 0.0f;
    }
    
    public boolean isCanceled() {
        return this.canceled;
    }
    
    public void setCanceled(final boolean canceled) {
        this.canceled = canceled;
    }
    
    public enum Era
    {
        EVENT_PRE, 
        EVENT_POST;
    }
}
