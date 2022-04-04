// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import mod.hitlerhax.event.events.HtlrEventPush;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import mod.hitlerhax.event.events.HtlrEventSwing;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.util.math.AxisAlignedBB;
import mod.hitlerhax.event.events.HtlrEventMotionUpdate;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import mod.hitlerhax.event.HtlrEventBus;
import mod.hitlerhax.event.events.HtlrEventMove;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.MoverType;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayerSP.class })
public class HtlrMixinEntityPlayerSP extends HtlrMixinEntity
{
    @Inject(method = { "move" }, at = { @At("HEAD") }, cancellable = true)
    private void move(final MoverType type, final double x, final double y, final double z, final CallbackInfo info) {
        final HtlrEventMove event = new HtlrEventMove(type, x, y, z);
        HtlrEventBus.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            super.func_70091_d(type, event.get_x(), event.get_y(), event.get_z());
            info.cancel();
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    public void OnPreUpdateWalkingPlayer(final CallbackInfo p_Info) {
        final HtlrEventMotionUpdate l_Event = new HtlrEventMotionUpdate(0);
        HtlrEventBus.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Redirect(method = { "onUpdateWalkingPlayer" }, at = @At(value = "FIELD", target = "net/minecraft/util/math/AxisAlignedBB.minY:D"))
    private double minYHook(final AxisAlignedBB bb) {
        return bb.field_72338_b;
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") }, cancellable = true)
    public void OnPostUpdateWalkingPlayer(final CallbackInfo p_Info) {
        final HtlrEventMotionUpdate l_Event = new HtlrEventMotionUpdate(1);
        HtlrEventBus.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "swingArm" }, at = { @At("RETURN") }, cancellable = true)
    public void swingArm(final EnumHand p_Hand, final CallbackInfo p_Info) {
        final HtlrEventSwing l_Event = new HtlrEventSwing(p_Hand);
        HtlrEventBus.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "pushOutOfBlocks" }, at = { @At("HEAD") }, cancellable = true)
    private void pushOutOfBlocksHook(final double x, final double y, final double z, final CallbackInfoReturnable<Boolean> info) {
        final HtlrEventPush event = new HtlrEventPush(1);
        HtlrEventBus.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            info.setReturnValue(false);
        }
    }
}
