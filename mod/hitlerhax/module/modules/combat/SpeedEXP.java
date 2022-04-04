// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import mod.hitlerhax.Main;
import me.zero.alpine.listener.Listenable;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.entity.player.EntityPlayer;
import mod.hitlerhax.util.EntityUtil;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import mod.hitlerhax.util.InventoryUtil;
import net.minecraft.init.Items;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class SpeedEXP extends Module
{
    final IntSetting delay;
    final BooleanSetting stopEXP;
    final BooleanSetting silent;
    final BooleanSetting feet;
    private int oldSlot;
    
    public SpeedEXP() {
        super("SpeedEXP", "Automatically switches to and throws EXP", Category.COMBAT);
        this.delay = new IntSetting("ThrowDelay", this, 0);
        this.stopEXP = new BooleanSetting("StopEXP", this, false);
        this.silent = new BooleanSetting("SilentSwap", this, false);
        this.feet = new BooleanSetting("Feet", this, false);
        this.oldSlot = -1;
        this.addSetting(this.delay, this.stopEXP, this.silent, this.feet);
    }
    
    @Override
    public void onUpdate() {
        if (SpeedEXP.mc.field_71439_g == null || SpeedEXP.mc.field_71441_e == null || SpeedEXP.mc.field_71462_r != null) {
            return;
        }
        this.oldSlot = SpeedEXP.mc.field_71439_g.field_71071_by.field_70461_c;
        final float oldPitch = SpeedEXP.mc.field_71439_g.field_70125_A;
        if (!this.silent.isEnabled()) {
            InventoryUtil.switchToSlot(Items.field_151062_by);
        }
        else {
            InventoryUtil.switchToSlotGhost(Items.field_151062_by);
        }
        if (this.feet.isEnabled()) {
            SpeedEXP.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(SpeedEXP.mc.field_71439_g.field_70177_z, 90.0f, SpeedEXP.mc.field_71439_g.field_70122_E));
        }
        SpeedEXP.mc.field_71467_ac = this.delay.getValue();
        SpeedEXP.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        SpeedEXP.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.oldSlot));
        if (this.stopEXP.isEnabled() && EntityUtil.getArmor((EntityPlayer)SpeedEXP.mc.field_71439_g) == 100.0f) {
            SpeedEXP.mc.field_71439_g.func_184597_cx();
            this.onDisable();
            return;
        }
        if (this.feet.isEnabled()) {
            SpeedEXP.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(SpeedEXP.mc.field_71439_g.field_70177_z, oldPitch, SpeedEXP.mc.field_71439_g.field_70122_E));
        }
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        HtlrEventBus.EVENT_BUS.unsubscribe(this);
        Main.config.Save();
        if (SpeedEXP.mc.field_71441_e == null) {
            return;
        }
        SpeedEXP.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.oldSlot));
        SpeedEXP.mc.field_71439_g.field_71071_by.field_70461_c = this.oldSlot;
        SpeedEXP.mc.field_71467_ac = 4;
    }
}
