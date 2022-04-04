// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.InputUpdateEvent;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class AntiAFK extends Module
{
    final BooleanSetting walk;
    final IntSetting rotate;
    final BooleanSetting punch;
    final IntSetting punchTimer;
    long lastPunched;
    
    public AntiAFK() {
        super("AntiAFK", "Prevents AFK Kick", Category.UTILITIES);
        this.walk = new BooleanSetting("Walk", this, true);
        this.rotate = new IntSetting("Rotate", this, 1);
        this.punch = new BooleanSetting("Punch", this, true);
        this.punchTimer = new IntSetting("Punch Timer (s)", this, 30);
        this.lastPunched = 0L;
        this.addSetting(this.walk);
        this.addSetting(this.rotate);
        this.addSetting(this.punch);
        this.addSetting(this.punchTimer);
    }
    
    @SubscribeEvent
    public void onUpdateInput(final InputUpdateEvent event) {
        if (this.walk.enabled) {
            event.getMovementInput().field_192832_b = 1.0f;
        }
        final EntityPlayerSP field_71439_g = AntiAFK.mc.field_71439_g;
        field_71439_g.field_70177_z += this.rotate.value;
        if (this.punch.enabled && System.currentTimeMillis() - this.punchTimer.value * 1000L > this.lastPunched) {
            AntiAFK.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
            this.lastPunched = System.currentTimeMillis();
        }
    }
}
