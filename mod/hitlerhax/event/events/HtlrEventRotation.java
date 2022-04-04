// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventRotation extends HtlrEventCancellable
{
    float yaw;
    float pitch;
    
    public float getYaw() {
        return this.yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
}
