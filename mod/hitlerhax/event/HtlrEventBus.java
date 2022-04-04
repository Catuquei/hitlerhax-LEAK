// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event;

import me.zero.alpine.bus.EventManager;
import me.zero.alpine.bus.EventBus;

public class HtlrEventBus
{
    public static final EventBus EVENT_BUS;
    
    static {
        EVENT_BUS = new EventManager();
    }
}
