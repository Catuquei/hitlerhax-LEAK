// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.player;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import java.util.function.Consumer;
import net.minecraft.entity.EntityLivingBase;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class NoEntityTrace extends Module
{
    boolean focus;
    
    public NoEntityTrace() {
        super("NoEntityTrace", "Allows you to mine through entities", Category.PLAYER);
        this.focus = false;
    }
    
    @Override
    public void onUpdate() {
        if (NoEntityTrace.mc.field_71439_g == null || NoEntityTrace.mc.field_71441_e == null) {
            return;
        }
        NoEntityTrace.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity instanceof EntityLivingBase).filter(entity -> NoEntityTrace.mc.field_71439_g == entity).map(entity -> entity).filter(entity -> !entity.field_70128_L).forEach(this::processHit);
        final RayTraceResult normalResult = NoEntityTrace.mc.field_71476_x;
        if (normalResult != null) {
            this.focus = (normalResult.field_72313_a == RayTraceResult.Type.ENTITY);
        }
    }
    
    private void processHit(final EntityLivingBase event) {
        final RayTraceResult bypassResult = event.func_174822_a(6.0, NoEntityTrace.mc.func_184121_ak());
        if (bypassResult != null && this.focus && bypassResult.field_72313_a == RayTraceResult.Type.BLOCK) {
            final BlockPos pos = bypassResult.func_178782_a();
            if (NoEntityTrace.mc.field_71474_y.field_74312_F.func_151470_d()) {
                NoEntityTrace.mc.field_71442_b.func_180512_c(pos, EnumFacing.UP);
            }
        }
    }
}
