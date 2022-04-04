// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.MathHelper;
import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import java.util.Objects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventMove;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class Speed extends Module
{
    final ModeSetting mode;
    final FloatSetting multiplier;
    final BooleanSetting inLiquid;
    final BooleanSetting jump;
    @EventHandler
    private final Listener<HtlrEventMove> player_move;
    
    public Speed() {
        super("Speed", "Boosts the default walking speed", Category.MOVEMENT);
        this.mode = new ModeSetting("Mode", this, "strafe", new String[] { "strafe" });
        this.multiplier = new FloatSetting("Multiplier", this, 1.0f);
        this.inLiquid = new BooleanSetting("While In Liquids", this, true);
        this.jump = new BooleanSetting("Auto Jump", this, false);
        float player_speed;
        float move_forward;
        float move_strafe;
        float rotation_yaw;
        int amp;
        this.player_move = new Listener<HtlrEventMove>(event -> {
            if ((Speed.mc.field_71439_g.func_70090_H() || Speed.mc.field_71439_g.func_180799_ab()) && !this.inLiquid.enabled) {
                return;
            }
            else if (Speed.mc.field_71439_g.func_70093_af() || Speed.mc.field_71439_g.func_70617_f_() || Speed.mc.field_71439_g.func_180799_ab() || Speed.mc.field_71439_g.func_70090_H() || Speed.mc.field_71439_g.field_71075_bZ.field_75100_b) {
                return;
            }
            else {
                player_speed = 0.2873f * this.multiplier.value;
                move_forward = Speed.mc.field_71439_g.field_71158_b.field_192832_b;
                move_strafe = Speed.mc.field_71439_g.field_71158_b.field_78902_a;
                rotation_yaw = Speed.mc.field_71439_g.field_70177_z;
                if (Speed.mc.field_71439_g.func_70644_a(MobEffects.field_76424_c)) {
                    amp = Objects.requireNonNull(Speed.mc.field_71439_g.func_70660_b(MobEffects.field_76424_c)).func_76458_c();
                    player_speed *= 1.2f * (amp + 1);
                }
                if (move_forward == 0.0f && move_strafe == 0.0f) {
                    event.set_x(0.0);
                    event.set_z(0.0);
                }
                else {
                    if (move_forward != 0.0f) {
                        if (move_strafe > 0.0f) {
                            rotation_yaw += ((move_forward > 0.0f) ? -45 : 45);
                        }
                        else if (move_strafe < 0.0f) {
                            rotation_yaw += ((move_forward > 0.0f) ? 45 : -45);
                        }
                        move_strafe = 0.0f;
                        if (move_forward > 0.0f) {
                            move_forward = 1.0f;
                        }
                        else if (move_forward < 0.0f) {
                            move_forward = -1.0f;
                        }
                    }
                    event.set_x(move_forward * player_speed * Math.cos(Math.toRadians(rotation_yaw + 90.0f)) + move_strafe * player_speed * Math.sin(Math.toRadians(rotation_yaw + 90.0f)));
                    event.set_z(move_forward * player_speed * Math.sin(Math.toRadians(rotation_yaw + 90.0f)) - move_strafe * player_speed * Math.cos(Math.toRadians(rotation_yaw + 90.0f)));
                }
                event.cancel();
                return;
            }
        }, (Predicate<HtlrEventMove>[])new Predicate[0]);
        this.addSetting(this.mode);
        this.addSetting(this.multiplier);
        this.addSetting(this.inLiquid);
        this.addSetting(this.jump);
    }
    
    @Override
    public void onUpdate() {
        if ((Speed.mc.field_71439_g.func_70090_H() || Speed.mc.field_71439_g.func_180799_ab()) && !this.inLiquid.enabled) {
            return;
        }
        if (this.mode.getMode().equalsIgnoreCase("strafe")) {
            if (Speed.mc.field_71439_g.func_184218_aH()) {
                return;
            }
            final float yaw = this.YawRotationUtility();
            if ((Speed.mc.field_71439_g.field_71158_b.field_187256_d || Speed.mc.field_71439_g.field_71158_b.field_187255_c || Speed.mc.field_71439_g.field_71158_b.field_187257_e || Speed.mc.field_71439_g.field_71158_b.field_187258_f) && (this.jump.enabled || Speed.mc.field_71439_g.field_70122_E)) {
                final EntityPlayerSP field_71439_g = Speed.mc.field_71439_g;
                field_71439_g.field_70159_w -= MathHelper.func_76126_a(yaw) * 0.017453292f * this.multiplier.value;
                final EntityPlayerSP field_71439_g2 = Speed.mc.field_71439_g;
                field_71439_g2.field_70179_y += MathHelper.func_76134_b(yaw) * 0.017453292f * this.multiplier.value;
            }
            if ((Speed.mc.field_71474_y.field_74314_A.func_151470_d() && Speed.mc.field_71439_g.field_70122_E) || (Speed.mc.field_71439_g.field_70122_E && this.jump.enabled && (Speed.mc.field_71439_g.field_70159_w != 0.0 || Speed.mc.field_71439_g.field_70179_y != 0.0))) {
                Speed.mc.field_71439_g.field_70181_x = 0.4050000011920929;
            }
        }
    }
    
    private float YawRotationUtility() {
        float rotationYaw = Speed.mc.field_71439_g.field_70177_z;
        if (Speed.mc.field_71439_g.field_191988_bg < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (Speed.mc.field_71439_g.field_191988_bg < 0.0f) {
            n = -0.5f;
        }
        else if (Speed.mc.field_71439_g.field_191988_bg > 0.0f) {
            n = 0.5f;
        }
        if (Speed.mc.field_71439_g.field_70702_br > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (Speed.mc.field_71439_g.field_70702_br < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
}
