// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import java.util.Objects;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class EntityFly extends Module
{
    final ModeSetting mode;
    final FloatSetting multiplier;
    final FloatSetting glideHeight;
    final FloatSetting vSpeed;
    final FloatSetting hSpeed;
    final FloatSetting reduction;
    final FloatSetting jumpMovement;
    
    public EntityFly() {
        super("EntityFly", "Lets your riding entity fly", Category.MOVEMENT);
        this.mode = new ModeSetting("Mode", this, "y-axis motionless", new String[] { "y-axis motionless", "fall reduction", "packet", "HorseJump" });
        this.multiplier = new FloatSetting("Multiplier", this, 1.0f);
        this.glideHeight = new FloatSetting("Glide Height", this, 0.3f);
        this.vSpeed = new FloatSetting("Packet VSpeed", this, 1.0f);
        this.hSpeed = new FloatSetting("Packet HSpeed", this, 1.0f);
        this.reduction = new FloatSetting("Fall Reduction", this, 0.03f);
        this.jumpMovement = new FloatSetting("Horse Jump Factor", this, 1.0f);
        this.addSetting(this.mode);
        this.addSetting(this.multiplier);
        this.addSetting(this.glideHeight);
        this.addSetting(this.vSpeed);
        this.addSetting(this.hSpeed);
        this.addSetting(this.reduction);
        this.addSetting(this.jumpMovement);
    }
    
    @Override
    public void onUpdate() {
        if (EntityFly.mc.field_71441_e == null || EntityFly.mc.field_71439_g == null || EntityFly.mc.field_71439_g.field_184239_as == null) {
            return;
        }
        final double[] pos = { EntityFly.mc.field_71439_g.func_184187_bx().field_70169_q, EntityFly.mc.field_71439_g.func_184187_bx().field_70167_r, EntityFly.mc.field_71439_g.func_184187_bx().field_70166_s };
        final String mode = this.mode.getMode();
        switch (mode) {
            case "y-axis motionless": {
                EntityFly.mc.field_71439_g.field_184239_as.field_70181_x = 0.0;
                break;
            }
            case "fall reduction": {
                if (EntityFly.mc.field_71439_g.field_184239_as.field_70181_x >= 0.0) {
                    break;
                }
                final Entity field_184239_as = EntityFly.mc.field_71439_g.field_184239_as;
                field_184239_as.field_70181_x += this.reduction.value;
                if (EntityFly.mc.field_71439_g.field_184239_as.field_70181_x > 0.0) {
                    EntityFly.mc.field_71439_g.field_184239_as.field_70181_x = 0.0;
                    break;
                }
                break;
            }
            case "packet": {
                final float player_speed = 0.2873f;
                final float rotation_yaw = EntityFly.mc.field_71439_g.func_184187_bx().field_70177_z;
                final float move_forward = EntityFly.mc.field_71439_g.field_71158_b.field_192832_b;
                final float move_strafe = EntityFly.mc.field_71439_g.field_71158_b.field_78902_a;
                if (EntityFly.mc.field_71439_g.field_191988_bg > 0.0f) {
                    final double[] array = pos;
                    final int n2 = 0;
                    array[n2] += (move_forward * player_speed * Math.cos(Math.toRadians(rotation_yaw + 90.0f)) + move_strafe * player_speed * Math.sin(Math.toRadians(rotation_yaw + 90.0f))) * this.hSpeed.value;
                    final double[] array2 = pos;
                    final int n3 = 2;
                    array2[n3] += (move_forward * player_speed * Math.sin(Math.toRadians(rotation_yaw + 90.0f)) - move_strafe * player_speed * Math.cos(Math.toRadians(rotation_yaw + 90.0f))) * this.hSpeed.value;
                }
                double vert = 0.0;
                if (EntityFly.mc.field_71439_g.field_71158_b.field_78901_c) {
                    vert += 1.0 * this.vSpeed.value;
                }
                else if (EntityFly.mc.field_71439_g.field_191988_bg < 0.0f) {
                    vert -= 1.0 * this.vSpeed.value;
                }
                else {
                    EntityFly.mc.field_71439_g.field_70181_x = 0.0;
                    EntityFly.mc.field_71439_g.func_184187_bx().field_70181_x = 0.0;
                }
                final double[] array3 = pos;
                final int n4 = 1;
                array3[n4] += vert;
                EntityFly.mc.field_71439_g.func_184187_bx().func_70107_b(pos[0], pos[1], pos[2]);
                EntityFly.mc.field_71439_g.func_70107_b(pos[0], pos[1], pos[2]);
                final float yaw = this.YawRotationUtility();
                if (EntityFly.mc.field_71439_g.field_71158_b.field_187255_c) {
                    final Entity func_184187_bx = EntityFly.mc.field_71439_g.func_184187_bx();
                    func_184187_bx.field_70159_w -= MathHelper.func_76126_a(yaw) * 0.017453292f * this.hSpeed.value;
                    final Entity func_184187_bx2 = EntityFly.mc.field_71439_g.func_184187_bx();
                    func_184187_bx2.field_70179_y += MathHelper.func_76134_b(yaw) * 0.017453292f * this.hSpeed.value;
                    final EntityPlayerSP field_71439_g = EntityFly.mc.field_71439_g;
                    field_71439_g.field_70159_w -= MathHelper.func_76126_a(yaw) * 0.017453292f * this.hSpeed.value;
                    final EntityPlayerSP field_71439_g2 = EntityFly.mc.field_71439_g;
                    field_71439_g2.field_70179_y += MathHelper.func_76134_b(yaw) * 0.017453292f * this.hSpeed.value;
                    EntityFly.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(EntityFly.mc.field_71439_g.field_70165_t, EntityFly.mc.field_71439_g.field_70163_u, EntityFly.mc.field_71439_g.field_70161_v, false));
                    break;
                }
                break;
            }
            case "HorseJump": {
                if (EntityFly.mc.field_71439_g.func_110317_t()) {
                    ((EntityHorse)EntityFly.mc.field_71439_g.field_184239_as).field_70747_aH = this.jumpMovement.value;
                    break;
                }
                break;
            }
        }
    }
    
    private float YawRotationUtility() {
        final float rotationYaw = Objects.requireNonNull(EntityFly.mc.field_71439_g.func_184187_bx()).field_70177_z;
        return rotationYaw * 0.017453292f;
    }
}
