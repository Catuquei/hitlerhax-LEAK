// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import mod.hitlerhax.event.events.HtlrEventTotemPop;
import mod.hitlerhax.event.HtlrEventBus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityLivingBase.class })
public class HtlrMixinEntityLivingBase
{
    @Inject(method = { "checkTotemDeathProtection" }, at = { @At(value = "RETURN", ordinal = 1) })
    public void onTotemPop(final CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            HtlrEventBus.EVENT_BUS.post(new HtlrEventTotemPop());
        }
    }
}
