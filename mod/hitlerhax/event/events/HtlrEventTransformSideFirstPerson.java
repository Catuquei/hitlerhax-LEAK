// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import net.minecraft.util.EnumHandSide;
import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventTransformSideFirstPerson extends HtlrEventCancellable
{
    private final EnumHandSide enumHandSide;
    
    public HtlrEventTransformSideFirstPerson(final EnumHandSide enumHandSide) {
        this.enumHandSide = enumHandSide;
    }
    
    public EnumHandSide getEnumHandSide() {
        return this.enumHandSide;
    }
}
