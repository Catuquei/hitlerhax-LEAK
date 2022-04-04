// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.entity.MoverType;
import mod.hitlerhax.event.HtlrEventBus;
import mod.hitlerhax.event.events.HtlrEventPlayerTravel;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.EntityLivingBase;

@Mixin(value = { EntityPlayer.class }, priority = Integer.MAX_VALUE)
public abstract class HtlrMixinEntityPlayer extends EntityLivingBase
{
    public HtlrMixinEntityPlayer(final World worldIn) {
        super(worldIn);
    }
    
    @Inject(method = { "travel" }, at = { @At("HEAD") }, cancellable = true)
    public void travel(final float strafe, final float vertical, final float forward, final CallbackInfo info) {
        if (EntityPlayerSP.class.isAssignableFrom(this.getClass())) {
            final HtlrEventPlayerTravel event = new HtlrEventPlayerTravel();
            HtlrEventBus.EVENT_BUS.post(event);
            if (event.isCancelled()) {
                this.func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                info.cancel();
            }
        }
    }
}
