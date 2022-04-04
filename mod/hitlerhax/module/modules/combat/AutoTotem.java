// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.util.Timer;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class AutoTotem extends Module
{
    private final IntSetting delay;
    private final BooleanSetting cancelMotion;
    private final Timer timer;
    private boolean hasTotem;
    
    public AutoTotem() {
        super("AutoTotem", "Puts totem in offhand", Category.COMBAT);
        this.delay = new IntSetting("Delay", this, 1);
        this.cancelMotion = new BooleanSetting("CancelMotion", this, false);
        this.timer = new Timer();
        this.hasTotem = false;
        this.addSetting(this.delay);
        this.addSetting(this.cancelMotion);
    }
    
    @Override
    public void onUpdate() {
        if (!this.hasTotem) {
            this.timer.reset();
        }
        if (AutoTotem.mc.field_71439_g == null || AutoTotem.mc.field_71441_e == null) {
            return;
        }
        if ((!(AutoTotem.mc.field_71462_r instanceof GuiContainer) || AutoTotem.mc.field_71462_r instanceof GuiInventory) && AutoTotem.mc.field_71439_g.func_184586_b(EnumHand.OFF_HAND).func_77973_b() != Items.field_190929_cY && !AutoTotem.mc.field_71439_g.func_184812_l_()) {
            for (int index = 44; index >= 9; --index) {
                if (AutoTotem.mc.field_71439_g.field_71069_bz.func_75139_a(index).func_75211_c().func_77973_b() == Items.field_190929_cY) {
                    this.hasTotem = true;
                    if (this.timer.getPassedTimeMs() >= this.delay.getValue() * 100.0f && AutoTotem.mc.field_71439_g.field_71071_by.func_70445_o().func_77973_b() != Items.field_190929_cY) {
                        if (this.cancelMotion.enabled && AutoTotem.mc.field_71439_g.field_70159_w * AutoTotem.mc.field_71439_g.field_70159_w + AutoTotem.mc.field_71439_g.field_70181_x * AutoTotem.mc.field_71439_g.field_70181_x + AutoTotem.mc.field_71439_g.field_70179_y * AutoTotem.mc.field_71439_g.field_70179_y >= 9.0E-4) {
                            AutoTotem.mc.field_71439_g.field_70159_w = 0.0;
                            AutoTotem.mc.field_71439_g.field_70181_x = 0.0;
                            AutoTotem.mc.field_71439_g.field_70179_y = 0.0;
                            return;
                        }
                        AutoTotem.mc.field_71442_b.func_187098_a(0, index, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.field_71439_g);
                    }
                    if (this.timer.getPassedTimeMs() >= this.delay.getValue() * 200.0f && AutoTotem.mc.field_71439_g.field_71071_by.func_70445_o().func_77973_b() == Items.field_190929_cY) {
                        if (this.cancelMotion.enabled && AutoTotem.mc.field_71439_g.field_70159_w * AutoTotem.mc.field_71439_g.field_70159_w + AutoTotem.mc.field_71439_g.field_70181_x * AutoTotem.mc.field_71439_g.field_70181_x + AutoTotem.mc.field_71439_g.field_70179_y * AutoTotem.mc.field_71439_g.field_70179_y >= 9.0E-4) {
                            AutoTotem.mc.field_71439_g.field_70159_w = 0.0;
                            AutoTotem.mc.field_71439_g.field_70181_x = 0.0;
                            AutoTotem.mc.field_71439_g.field_70179_y = 0.0;
                            return;
                        }
                        AutoTotem.mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.field_71439_g);
                        if (AutoTotem.mc.field_71439_g.field_71071_by.func_70445_o().func_190926_b()) {
                            this.hasTotem = false;
                            return;
                        }
                    }
                    if (this.timer.getPassedTimeMs() >= this.delay.getValue() * 300.0f && !AutoTotem.mc.field_71439_g.field_71071_by.func_70445_o().func_190926_b() && AutoTotem.mc.field_71439_g.func_184586_b(EnumHand.OFF_HAND).func_77973_b() == Items.field_190929_cY) {
                        if (this.cancelMotion.enabled && AutoTotem.mc.field_71439_g.field_70159_w * AutoTotem.mc.field_71439_g.field_70159_w + AutoTotem.mc.field_71439_g.field_70181_x * AutoTotem.mc.field_71439_g.field_70181_x + AutoTotem.mc.field_71439_g.field_70179_y * AutoTotem.mc.field_71439_g.field_70179_y >= 9.0E-4) {
                            AutoTotem.mc.field_71439_g.field_70159_w = 0.0;
                            AutoTotem.mc.field_71439_g.field_70181_x = 0.0;
                            AutoTotem.mc.field_71439_g.field_70179_y = 0.0;
                            return;
                        }
                        AutoTotem.mc.field_71442_b.func_187098_a(0, index, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.field_71439_g);
                        this.hasTotem = false;
                        return;
                    }
                }
            }
        }
    }
    
    public void onEnable() {
        this.hasTotem = false;
    }
}
