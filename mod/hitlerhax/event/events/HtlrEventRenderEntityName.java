// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import net.minecraft.client.entity.AbstractClientPlayer;
import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventRenderEntityName extends HtlrEventCancellable
{
    public final AbstractClientPlayer Entity;
    public double X;
    public double Y;
    public double Z;
    public final String Name;
    public final double DistanceSq;
    
    public HtlrEventRenderEntityName(final AbstractClientPlayer entityIn, final double x, final double y, final double z, final String name, final double distanceSq) {
        this.Entity = entityIn;
        this.Name = name;
        this.DistanceSq = distanceSq;
    }
}
