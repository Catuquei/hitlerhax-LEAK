// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import mod.hitlerhax.Main;
import me.zero.alpine.listener.Listenable;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class Jesus extends Module
{
    final ModeSetting mode;
    final FloatSetting speed;
    final BooleanSetting dmg;
    
    public Jesus() {
        super("Jesus", "makes you walk on water", Category.MOVEMENT);
        this.mode = new ModeSetting("Mode", this, "bounce", new String[] { "bounce", "packet", "spacebar" });
        this.speed = new FloatSetting("Float Speed", this, 1.0f);
        this.dmg = new BooleanSetting("Packet Anti-FallDamage", this, false);
        this.addSetting(this.mode);
        this.addSetting(this.speed);
        this.addSetting(this.dmg);
    }
    
    @Override
    protected void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        HtlrEventBus.EVENT_BUS.subscribe(this);
        Main.config.Save();
    }
    
    @Override
    public void onUpdate() {
        if (Jesus.mc.field_71439_g.func_184218_aH()) {
            return;
        }
        if (this.mode.getMode().equalsIgnoreCase("bounce") && Jesus.mc.field_71439_g.func_70090_H()) {
            if (!Jesus.mc.field_71439_g.func_70093_af()) {
                Jesus.mc.field_71439_g.field_70181_x = 0.03999999910593033 * this.speed.value;
            }
            else {
                Jesus.mc.field_71439_g.field_70181_x = -0.03999999910593033 * this.speed.value;
            }
        }
        if (this.mode.getMode().equalsIgnoreCase("packet") && !Jesus.mc.field_71439_g.func_70093_af()) {
            if (Jesus.mc.field_71441_e.func_180495_p(new BlockPos(Jesus.mc.field_71439_g.field_70165_t, Jesus.mc.field_71439_g.field_70163_u - 0.20000000298023224, Jesus.mc.field_71439_g.field_70161_v)).func_177230_c() == Block.func_149684_b("water") && Jesus.mc.field_71439_g.field_70181_x < 0.0) {
                final EntityPlayerSP field_71439_g = Jesus.mc.field_71439_g;
                field_71439_g.field_70163_u -= Jesus.mc.field_71439_g.field_70181_x;
                Jesus.mc.field_71439_g.field_70181_x = 0.0;
                if (this.dmg.enabled) {
                    Jesus.mc.field_71439_g.field_70143_R = 0.0f;
                }
            }
            if (Jesus.mc.field_71439_g.func_70090_H()) {
                Jesus.mc.field_71439_g.field_70181_x = 0.03999999910593033 * this.speed.value;
            }
        }
        if (this.mode.getMode().equalsIgnoreCase("spacebar") && Jesus.mc.field_71439_g.func_70090_H() && !Jesus.mc.field_71439_g.func_70093_af()) {
            final EntityPlayerSP field_71439_g2 = Jesus.mc.field_71439_g;
            field_71439_g2.field_70181_x += 0.03999999910593033;
        }
    }
}
