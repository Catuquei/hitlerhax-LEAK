// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import net.minecraft.entity.Entity;
import me.zero.alpine.event.type.Cancellable;

public class HtlrEventPush extends Cancellable
{
    public Entity entity;
    public double x;
    public double y;
    public double z;
    public boolean airbone;
    public int stage;
    
    public HtlrEventPush(final Entity entity, final double x, final double y, final double z, final boolean airbone) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.airbone = airbone;
    }
    
    public HtlrEventPush(final int stage, final Entity entity) {
        this.entity = entity;
        this.stage = stage;
    }
    
    public HtlrEventPush(final int stage) {
        this.stage = stage;
    }
}
