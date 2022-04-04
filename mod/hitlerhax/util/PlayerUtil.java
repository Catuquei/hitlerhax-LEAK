// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util;

import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemFood;

public class PlayerUtil implements Globals
{
    public static boolean IsEating() {
        return PlayerUtil.mc.field_71439_g != null && PlayerUtil.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemFood && PlayerUtil.mc.field_71439_g.func_184587_cr();
    }
    
    public static Vec3d getCenter(final double posX, final double posY, final double posZ) {
        return new Vec3d(Math.floor(posX) + 0.5, Math.floor(posY), Math.floor(posZ) + 0.5);
    }
    
    public static double getHealth() {
        return PlayerUtil.mc.field_71439_g.func_110143_aJ() + PlayerUtil.mc.field_71439_g.func_110139_bj();
    }
}
