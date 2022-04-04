// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import java.util.Iterator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class EntityUtil implements Globals
{
    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos((EntityUtil.mc.field_71439_g.func_184187_bx() != null) ? EntityUtil.mc.field_71439_g.func_184187_bx().field_70165_t : EntityUtil.mc.field_71439_g.field_70165_t, (EntityUtil.mc.field_71439_g.func_184187_bx() != null) ? EntityUtil.mc.field_71439_g.func_184187_bx().field_70163_u : EntityUtil.mc.field_71439_g.field_70163_u, (EntityUtil.mc.field_71439_g.func_184187_bx() != null) ? EntityUtil.mc.field_71439_g.func_184187_bx().field_70161_v : EntityUtil.mc.field_71439_g.field_70161_v);
    }
    
    public static boolean isPassive(final Entity e) {
        return (!(e instanceof EntityWolf) || !((EntityWolf)e).func_70919_bu()) && (e instanceof EntityAgeable || e instanceof EntityAmbientCreature || e instanceof EntitySquid || (e instanceof EntityIronGolem && ((EntityIronGolem)e).func_70643_av() == null));
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double x, final double y, final double z) {
        return new Vec3d((entity.field_70165_t - entity.field_70142_S) * x, 0.0 * y, (entity.field_70161_v - entity.field_70136_U) * z);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double ticks) {
        return getInterpolatedAmount(entity, ticks, ticks, ticks);
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float ticks) {
        return new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U).func_178787_e(getInterpolatedAmount(entity, ticks));
    }
    
    public static boolean isIntercepted(final BlockPos pos) {
        for (final Entity entity : EntityUtil.mc.field_71441_e.field_72996_f) {
            if (new AxisAlignedBB(pos).func_72326_a(entity.func_174813_aQ())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isNeutralMob(final Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }
    
    public static boolean isHostileMob(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity);
    }
    
    public static float getArmor(final EntityPlayer target) {
        float armorDurability = 0.0f;
        for (final ItemStack stack : target.func_184193_aE()) {
            if (stack != null) {
                if (stack.func_77973_b() == Items.field_190931_a) {
                    continue;
                }
                armorDurability += (stack.func_77958_k() - stack.func_77952_i()) / (float)stack.func_77958_k() * 100.0f;
            }
        }
        return armorDurability;
    }
}
