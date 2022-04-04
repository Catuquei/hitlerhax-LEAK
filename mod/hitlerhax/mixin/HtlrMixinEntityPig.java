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
import net.minecraft.entity.passive.EntityPig;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPig.class })
public class HtlrMixinEntityPig
{
    @Inject(method = { "canBeSteered" }, at = { @At("HEAD") }, cancellable = true)
    public void canBeSteered(final CallbackInfoReturnable<Boolean> cir) {
        final HtlrEventSteerEntity event = new HtlrEventSteerEntity();
        HtlrEventBus.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            cir.cancel();
            cir.setReturnValue(true);
        }
    }
    
    @Inject(method = { "getSaddled" }, at = { @At("HEAD") }, cancellable = true)
    public void getSaddled(final CallbackInfoReturnable<Boolean> cir) {
        final HtlrEventHorseSaddled event = new HtlrEventHorseSaddled();
        HtlrEventBus.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            cir.cancel();
            cir.setReturnValue(true);
        }
    }
}
