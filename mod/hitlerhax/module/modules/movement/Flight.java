// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.module.Module;

public class Flight extends Module
{
    final FloatSetting hSpeed;
    final FloatSetting vSpeed;
    final FloatSetting glide;
    final BooleanSetting dmg;
    
    public Flight() {
        super("Flight", "Allows Flight", Category.MOVEMENT);
        this.hSpeed = new FloatSetting("Horizontal Speed", this, 1.0f);
        this.vSpeed = new FloatSetting("Vertical Speed", this, 1.0f);
        this.glide = new FloatSetting("Downward Glide Speed", this, 0.0f);
        this.dmg = new BooleanSetting("Packet Anti-FallDamage", this, false);
        this.addSetting(this.hSpeed);
        this.addSetting(this.vSpeed);
        this.addSetting(this.glide);
        this.addSetting(this.dmg);
    }
    
    @Override
    public void onUpdate() {
        final double[] pos = { Flight.mc.field_71439_g.field_70169_q, Flight.mc.field_71439_g.field_70167_r, Flight.mc.field_71439_g.field_70166_s };
        final float player_speed = 0.2873f;
        final float rotation_yaw = Flight.mc.field_71439_g.field_70177_z;
        final float move_forward = Flight.mc.field_71439_g.field_71158_b.field_192832_b;
        final float move_strafe = Flight.mc.field_71439_g.field_71158_b.field_78902_a;
        final double[] array = pos;
        final int n = 0;
        array[n] += (move_forward * player_speed * Math.cos(Math.toRadians(rotation_yaw + 90.0f)) + move_strafe * player_speed * Math.sin(Math.toRadians(rotation_yaw + 90.0f))) * this.hSpeed.value;
        final double[] array2 = pos;
        final int n2 = 2;
        array2[n2] += (move_forward * player_speed * Math.sin(Math.toRadians(rotation_yaw + 90.0f)) - move_strafe * player_speed * Math.cos(Math.toRadians(rotation_yaw + 90.0f))) * this.hSpeed.value;
        double vert = 0.0;
        if (Flight.mc.field_71439_g.field_71158_b.field_78901_c) {
            vert += 1.0 * this.vSpeed.value;
        }
        if (Flight.mc.field_71439_g.field_71158_b.field_78899_d) {
            vert -= 1.0 * this.vSpeed.value;
        }
        vert -= this.glide.value;
        final double[] array3 = pos;
        final int n3 = 1;
        array3[n3] += vert;
        Flight.mc.field_71439_g.func_70107_b(pos[0], pos[1], pos[2]);
        final float yaw = this.YawRotationUtility();
        if (Flight.mc.field_71439_g.field_71158_b.field_187256_d || Flight.mc.field_71439_g.field_71158_b.field_187255_c || Flight.mc.field_71439_g.field_71158_b.field_187257_e || Flight.mc.field_71439_g.field_71158_b.field_187258_f) {
            final EntityPlayerSP field_71439_g = Flight.mc.field_71439_g;
            field_71439_g.field_70159_w -= MathHelper.func_76126_a(yaw) * 0.017453292f * this.hSpeed.value;
            final EntityPlayerSP field_71439_g2 = Flight.mc.field_71439_g;
            field_71439_g2.field_70179_y += MathHelper.func_76134_b(yaw) * 0.017453292f * this.hSpeed.value;
            Flight.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Flight.mc.field_71439_g.field_70165_t, Flight.mc.field_71439_g.field_70163_u, Flight.mc.field_71439_g.field_70161_v, false));
        }
        if (this.dmg.enabled) {
            Flight.mc.field_71439_g.field_70143_R = 0.0f;
        }
    }
    
    private float YawRotationUtility() {
        float rotationYaw = Flight.mc.field_71439_g.field_70177_z;
        if (Flight.mc.field_71439_g.field_191988_bg < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (Flight.mc.field_71439_g.field_191988_bg < 0.0f) {
            n = -0.5f;
        }
        else if (Flight.mc.field_71439_g.field_191988_bg > 0.0f) {
            n = 0.5f;
        }
        if (Flight.mc.field_71439_g.field_70702_br > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (Flight.mc.field_71439_g.field_70702_br < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
}
