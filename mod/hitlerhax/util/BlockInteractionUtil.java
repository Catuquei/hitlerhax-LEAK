// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util;

public class BlockInteractionUtil implements Globals
{
    public static float[] getRotationsForPosition(final double x, final double y, final double z) {
        return getRotationsForPosition(x, y, z, BlockInteractionUtil.mc.field_71439_g.field_70165_t, BlockInteractionUtil.mc.field_71439_g.field_70163_u + BlockInteractionUtil.mc.field_71439_g.func_70047_e(), BlockInteractionUtil.mc.field_71439_g.field_70161_v);
    }
    
    public static float[] getRotationsForPosition(final double x, final double y, final double z, final double sourceX, final double sourceY, final double sourceZ) {
        final double deltaX = x - sourceX;
        final double deltaY = y - sourceY;
        final double deltaZ = z - sourceZ;
        final double v = Math.toDegrees(Math.atan(deltaZ / deltaX));
        double yawToEntity;
        if (deltaZ < 0.0 && deltaX < 0.0) {
            yawToEntity = 90.0 + v;
        }
        else if (deltaZ < 0.0 && deltaX > 0.0) {
            yawToEntity = -90.0 + v;
        }
        else {
            yawToEntity = Math.toDegrees(-Math.atan(deltaX / deltaZ));
        }
        final double distanceXZ = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        double pitchToEntity = -Math.toDegrees(Math.atan(deltaY / distanceXZ));
        yawToEntity = wrapAngleTo180((float)yawToEntity);
        pitchToEntity = wrapAngleTo180((float)pitchToEntity);
        yawToEntity = (Double.isNaN(yawToEntity) ? 0.0 : yawToEntity);
        pitchToEntity = (Double.isNaN(pitchToEntity) ? 0.0 : pitchToEntity);
        return new float[] { (float)yawToEntity, (float)pitchToEntity };
    }
    
    public static float wrapAngleTo180(float angle) {
        for (angle %= 360.0f; angle >= 180.0f; angle -= 360.0f) {}
        while (angle < -180.0f) {
            angle += 360.0f;
        }
        return angle;
    }
}
