// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import mod.hitlerhax.event.events.HtlrEventHorseSaddled;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import mod.hitlerhax.event.HtlrEventBus;
import mod.hitlerhax.event.events.HtlrEventSteerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.passive.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ AbstractHorse.class })
public class HtlrMixinAbstractHorse
{
    @Inject(method = { "canBeSteered" }, at = { @At("HEAD") }, cancellable = true)
    public void canBeSteered(final CallbackInfoReturnable<Boolean> cir) {
        final HtlrEventSteerEntity l_Event = new HtlrEventSteerEntity();
        HtlrEventBus.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            cir.cancel();
            cir.setReturnValue(true);
        }
    }
    
    @Inject(method = { "isHorseSaddled" }, at = { @At("HEAD") }, cancellable = true)
    public void isHorseSaddled(final CallbackInfoReturnable<Boolean> cir) {
        final HtlrEventHorseSaddled l_Event = new HtlrEventHorseSaddled();
        HtlrEventBus.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            cir.cancel();
            cir.setReturnValue(true);
        }
    }
}
