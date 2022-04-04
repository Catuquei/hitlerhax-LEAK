// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import mod.hitlerhax.event.HtlrEventBus;
import mod.hitlerhax.event.events.HtlrEventEntity;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Entity.class })
public class HtlrMixinEntity
{
    @Shadow
    public double field_70159_w;
    @Shadow
    public double field_70181_x;
    @Shadow
    public double field_70179_y;
    
    @Redirect(method = { "applyEntityCollision" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void velocity(final Entity entity, final double x, final double y, final double z) {
        final HtlrEventEntity.ScEventCollision event = new HtlrEventEntity.ScEventCollision(entity, x, y, z);
        HtlrEventBus.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            return;
        }
        entity.field_70159_w += x;
        entity.field_70181_x += y;
        entity.field_70179_y += z;
        entity.field_70160_al = true;
    }
    
    @Shadow
    public void func_70091_d(final MoverType type, final double x, final double y, final double z) {
    }
}
