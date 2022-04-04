// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovementInput;
import net.minecraft.entity.passive.AbstractHorse;
import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.event.events.HtlrEventHorseSaddled;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventSteerEntity;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class EntityRide extends Module
{
    final BooleanSetting jesus;
    final BooleanSetting control;
    final FloatSetting speed;
    final FloatSetting jitter;
    @EventHandler
    private final Listener<HtlrEventSteerEntity> OnSteerEntity;
    @EventHandler
    private final Listener<HtlrEventHorseSaddled> OnHorseSaddled;
    
    public EntityRide() {
        super("EntityRide", "Utilities For Rideable Entities", Category.MOVEMENT);
        this.jesus = new BooleanSetting("Jesus", this, true);
        this.control = new BooleanSetting("Control", this, true);
        this.speed = new FloatSetting("Speed", this, 0.5f);
        this.jitter = new FloatSetting("Jitter", this, 0.0f);
        this.OnSteerEntity = new Listener<HtlrEventSteerEntity>(p_Event -> {
            if (this.control.enabled) {
                p_Event.cancel();
            }
            return;
        }, (Predicate<HtlrEventSteerEntity>[])new Predicate[0]);
        this.OnHorseSaddled = new Listener<HtlrEventHorseSaddled>(p_Event -> {
            if (this.control.enabled) {
                p_Event.cancel();
            }
            return;
        }, (Predicate<HtlrEventHorseSaddled>[])new Predicate[0]);
        this.addSetting(this.jesus);
        this.addSetting(this.control);
        this.addSetting(this.speed);
        this.addSetting(this.jitter);
    }
    
    @Override
    public void onUpdate() {
        if (EntityRide.mc.field_71441_e == null || EntityRide.mc.field_71439_g.func_184187_bx() == null) {
            return;
        }
        if (EntityRide.mc.field_71439_g.func_184218_aH()) {
            final MovementInput movementInput = EntityRide.mc.field_71439_g.field_71158_b;
            double forward = movementInput.field_192832_b;
            double strafe = movementInput.field_78902_a;
            float yaw = EntityRide.mc.field_71439_g.field_70177_z;
            if (forward == 0.0 && strafe == 0.0) {
                EntityRide.mc.field_71439_g.func_184187_bx().field_70159_w = 0.0;
                EntityRide.mc.field_71439_g.func_184187_bx().field_70179_y = 0.0;
            }
            else {
                if (forward != 0.0) {
                    if (strafe > 0.0) {
                        yaw += ((forward > 0.0) ? -45 : 45);
                    }
                    else if (strafe < 0.0) {
                        yaw += ((forward > 0.0) ? 45 : -45);
                    }
                    strafe = 0.0;
                    if (forward > 0.0) {
                        forward = 1.0;
                    }
                    else if (forward < 0.0) {
                        forward = -1.0;
                    }
                }
                EntityRide.mc.field_71439_g.func_184187_bx().field_70159_w = forward * this.speed.getValue() * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * this.speed.getValue() * Math.sin(Math.toRadians(yaw + 90.0f));
                EntityRide.mc.field_71439_g.func_184187_bx().field_70179_y = forward * this.speed.getValue() * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * this.speed.getValue() * Math.cos(Math.toRadians(yaw + 90.0f));
            }
        }
        if (this.jesus.enabled && EntityRide.mc.field_71439_g.func_184187_bx().func_70090_H()) {
            final Entity func_184187_bx = EntityRide.mc.field_71439_g.func_184187_bx();
            func_184187_bx.field_70181_x += 0.03999999910593033;
        }
        if (this.jitter.value != 0.0f && EntityRide.mc.field_71439_g.func_110317_t()) {
            ((AbstractHorse)EntityRide.mc.field_71439_g.field_184239_as).func_191986_a(0.0f, this.jitter.value, 0.0f);
        }
    }
}
