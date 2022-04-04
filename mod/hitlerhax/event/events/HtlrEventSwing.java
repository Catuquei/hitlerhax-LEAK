// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import net.minecraft.util.EnumHand;
import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventSwing extends HtlrEventCancellable
{
    public final EnumHand hand;
    
    public HtlrEventSwing(final EnumHand hand) {
        this.hand = hand;
    }
}
