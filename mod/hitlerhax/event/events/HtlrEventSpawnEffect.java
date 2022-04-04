// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventSpawnEffect extends HtlrEventCancellable
{
    private int particleID;
    
    public HtlrEventSpawnEffect(final int particleID) {
        this.particleID = particleID;
    }
    
    public int getParticleID() {
        return this.particleID;
    }
    
    public void setParticleID(final int particleID) {
        this.particleID = particleID;
    }
}
