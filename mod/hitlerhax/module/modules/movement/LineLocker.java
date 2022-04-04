// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.module.Module;

public class LineLocker extends Module
{
    final FloatSetting x1;
    final FloatSetting z1;
    final FloatSetting x2;
    final FloatSetting z2;
    final FloatSetting speed;
    
    public LineLocker() {
        super("LineLocker", "Locks the player on a line between 2 points", Category.MOVEMENT);
        this.x1 = new FloatSetting("X1", this, 0.0f);
        this.z1 = new FloatSetting("Z1", this, 0.0f);
        this.x2 = new FloatSetting("X2", this, 0.0f);
        this.z2 = new FloatSetting("Z2", this, 0.0f);
        this.speed = new FloatSetting("Speed", this, 1.0f);
        this.addSetting(this.x1);
        this.addSetting(this.z1);
        this.addSetting(this.x2);
        this.addSetting(this.z2);
        this.addSetting(this.speed);
    }
    
    @Override
    public void onUpdate() {
        final double dxc = LineLocker.mc.field_71439_g.field_70165_t - this.x1.value;
        final double dzc = LineLocker.mc.field_71439_g.field_70161_v - this.z1.value;
        final double dxl = LineLocker.mc.field_71439_g.field_70165_t - this.x2.value;
        final double dzl = LineLocker.mc.field_71439_g.field_70161_v - this.z2.value;
        final double cross = (int)(dxc * dzl - dzc * dxl);
        if (cross != 0.0) {
            final double[] nearest = { nearestPointOnLine(this.x1.value, this.z1.value, this.x2.value, this.z2.value, LineLocker.mc.field_71439_g.field_70165_t, LineLocker.mc.field_71439_g.field_70161_v)[0], nearestPointOnLine(this.x1.value, this.z1.value, this.x2.value, this.z2.value, LineLocker.mc.field_71439_g.field_70165_t, LineLocker.mc.field_71439_g.field_70161_v)[1] };
            final double distX = LineLocker.mc.field_71439_g.field_70165_t - nearest[0];
            final double distZ = LineLocker.mc.field_71439_g.field_70161_v - nearest[1];
            if (!Double.isNaN(this.speed.value / 100.0f * (distX / Math.max(Math.abs(distX), Math.abs(distZ))))) {
                LineLocker.mc.field_71439_g.field_70159_w = -(this.speed.value / 100.0f) * (distX / Math.max(Math.abs(distX), Math.abs(distZ)));
                if (LineLocker.mc.field_71439_g.func_184218_aH() && LineLocker.mc.field_71439_g.field_184239_as != null && distX + distZ < 3.0) {
                    LineLocker.mc.field_71439_g.field_184239_as.func_70107_b(nearest[0], LineLocker.mc.field_71439_g.field_184239_as.field_70163_u, nearest[1]);
                }
            }
            if (!Double.isNaN(this.speed.value / 100.0f * (distZ / Math.max(Math.abs(distX), Math.abs(distZ))))) {
                LineLocker.mc.field_71439_g.field_70179_y = -(this.speed.value / 100.0f) * (distZ / Math.max(Math.abs(distX), Math.abs(distZ)));
                if (LineLocker.mc.field_71439_g.func_184218_aH() && LineLocker.mc.field_71439_g.field_184239_as != null && distX + distZ < 3.0) {
                    LineLocker.mc.field_71439_g.field_184239_as.func_70107_b(nearest[0], LineLocker.mc.field_71439_g.field_184239_as.field_70163_u, nearest[1]);
                }
            }
        }
    }
    
    public static double[] nearestPointOnLine(final double ax, final double ay, final double bx, final double by, final double px, final double py) {
        final double apx = px - ax;
        final double apy = py - ay;
        final double abx = bx - ax;
        final double aby = by - ay;
        final double ab2 = abx * abx + aby * aby;
        final double ap_ab = apx * abx + apy * aby;
        final double t = ap_ab / ab2;
        return new double[] { ax + abx * t, ay + aby * t };
    }
}
