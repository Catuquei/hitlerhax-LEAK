// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.notebot;

import net.minecraft.util.EnumFacing;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.entity.EntityPlayerSP;
import mod.hitlerhax.util.Globals;

public class NbUtil implements Globals
{
    public static float LongInterpolate(final long val, final long fmin, final long fmax, final float tmin, final float tmax) {
        final long fdist = fmax - fmin;
        final float tdist = tmax - tmin;
        final float scaled = (val - fmin) / (float)fdist;
        return tmin + scaled * tdist;
    }
    
    public static void LookAt(final double x, final double y, final double z) {
        final EntityPlayerSP ply = NbUtil.mc.field_71439_g;
        final double px = ply.field_70165_t;
        final double py = ply.field_70163_u;
        final double pz = ply.field_70161_v;
        double dirx = x - px;
        double diry = y - py;
        double dirz = z - pz;
        final double dirlen = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
        dirx /= dirlen;
        diry /= dirlen;
        dirz /= dirlen;
        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);
        pitch = pitch * 180.0 / 3.141592653589793;
        yaw = yaw * 180.0 / 3.141592653589793;
        yaw -= 90.0;
        ply.field_70125_A = -(float)pitch;
        ply.field_70177_z = (float)yaw;
    }
    
    public static void RightClick(final BlockPos pos) {
        final EntityPlayerSP ply = NbUtil.mc.field_71439_g;
        final TileEntity tile = NbUtil.mc.field_71441_e.func_175625_s(pos);
        if (tile == null || tile.func_145838_q().func_149732_F().equals("tile.air")) {
            return;
        }
        LookAt(pos.func_177958_n() + 0.5f, pos.func_177956_o() - 1.0f, pos.func_177952_p() + 0.5f);
        EnumFacing side = ply.func_174811_aO();
        if (NbUtil.mc.field_71476_x != null) {
            side = NbUtil.mc.field_71476_x.field_178784_b;
        }
        ply.func_184609_a(EnumHand.MAIN_HAND);
        NbUtil.mc.field_71442_b.func_187099_a(ply, NbUtil.mc.field_71441_e, pos, side, NbUtil.mc.field_71476_x.field_72307_f, EnumHand.MAIN_HAND);
    }
    
    public static void LeftClick(final BlockPos pos) {
        final EntityPlayerSP ply = NbUtil.mc.field_71439_g;
        final TileEntity tile = NbUtil.mc.field_71441_e.func_175625_s(pos);
        if (tile == null || tile.func_145838_q().func_149732_F().equals("tile.air")) {
            return;
        }
        LookAt(pos.func_177958_n() + 0.5f, pos.func_177956_o() - 1.0f, pos.func_177952_p() + 0.5f);
        EnumFacing side = ply.func_174811_aO();
        if (NbUtil.mc.field_71476_x != null) {
            side = NbUtil.mc.field_71476_x.field_178784_b;
        }
        ply.func_184609_a(EnumHand.MAIN_HAND);
        NbUtil.mc.field_71442_b.func_180511_b(pos, side);
    }
    
    public static String GetRealNote(final int note) {
        final int noter = note % 12;
        switch (noter) {
            case 0: {
                return "F#";
            }
            case 1: {
                return "G";
            }
            case 2: {
                return "G#";
            }
            case 3: {
                return "A";
            }
            case 4: {
                return "A#";
            }
            case 5: {
                return "B";
            }
            case 6: {
                return "C";
            }
            case 7: {
                return "C#";
            }
            case 8: {
                return "D";
            }
            case 9: {
                return "D#";
            }
            case 10: {
                return "E";
            }
            case 11: {
                return "F";
            }
            default: {
                return "null";
            }
        }
    }
}
