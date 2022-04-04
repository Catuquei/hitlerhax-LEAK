// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.module.Module;

public class ElytraFlight extends Module
{
    final FloatSetting downSpeed;
    final FloatSetting upSpeed;
    final FloatSetting baseSpeed;
    final BooleanSetting noVelocity;
    
    public ElytraFlight() {
        super("ElytraFlight", "Fly with Elytras", Category.MOVEMENT);
        this.downSpeed = new FloatSetting("DownSpeed", this, 0.15f);
        this.upSpeed = new FloatSetting("UpSpeed", this, 2.0f);
        this.baseSpeed = new FloatSetting("BaseSpeed", this, 0.15f);
        this.noVelocity = new BooleanSetting("noVelocity", this, true);
        this.addSetting(this.downSpeed);
        this.addSetting(this.upSpeed);
        this.addSetting(this.baseSpeed);
        this.addSetting(this.noVelocity);
    }
    
    @Override
    public void onUpdate() {
        if (!ElytraFlight.mc.field_71439_g.func_184613_cA()) {
            return;
        }
        final float yaw = ElytraFlight.mc.field_71439_g.field_70177_z;
        final float pitch = ElytraFlight.mc.field_71439_g.field_70125_A;
        if (ElytraFlight.mc.field_71474_y.field_74351_w.func_151470_d()) {
            final EntityPlayerSP field_71439_g = ElytraFlight.mc.field_71439_g;
            field_71439_g.field_70159_w -= Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * this.baseSpeed.getValue();
            final EntityPlayerSP field_71439_g2 = ElytraFlight.mc.field_71439_g;
            field_71439_g2.field_70179_y += Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * this.baseSpeed.getValue();
        }
        if (ElytraFlight.mc.field_71474_y.field_74314_A.func_151470_d()) {
            final EntityPlayerSP field_71439_g3 = ElytraFlight.mc.field_71439_g;
            field_71439_g3.field_70181_x += this.upSpeed.getValue();
        }
        if (ElytraFlight.mc.field_71474_y.field_74311_E.func_151470_d()) {
            final EntityPlayerSP field_71439_g4 = ElytraFlight.mc.field_71439_g;
            field_71439_g4.field_70181_x -= this.downSpeed.getValue();
        }
        if (this.noVelocity.isEnabled() && !ElytraFlight.mc.field_71474_y.field_74351_w.func_151470_d() && !ElytraFlight.mc.field_71474_y.field_74314_A.func_151470_d() && !ElytraFlight.mc.field_71474_y.field_74311_E.func_151470_d()) {
            ElytraFlight.mc.field_71439_g.func_70016_h(0.0, 0.0, 0.0);
        }
    }
}
