// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.item.Item;
import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.util.EnumHand;
import mod.hitlerhax.event.HtlrEventCancellable;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.BooleanSetting;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.module.Module;

public class NoSlow extends Module
{
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> packetReceiveListener;
    final BooleanSetting sneak;
    private boolean sneaking;
    
    public NoSlow() {
        super("NoSlow", "Prevents slowdown effects", Category.MOVEMENT);
        Item item;
        this.packetReceiveListener = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof CPacketPlayerTryUseItem && event.get_era() == HtlrEventCancellable.Era.EVENT_POST) {
                item = NoSlow.mc.field_71439_g.func_184586_b(EnumHand.MAIN_HAND).func_77973_b();
                if ((item instanceof ItemFood || item instanceof ItemBow || item instanceof ItemPotion) && NoSlow.mc.field_71453_ak != null) {
                    NoSlow.mc.field_71453_ak.func_179290_a((Packet)new CPacketHeldItemChange(NoSlow.mc.field_71439_g.field_71071_by.field_70461_c));
                }
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.sneak = new BooleanSetting("AirStrict", this, true);
        this.sneaking = false;
        this.addSetting(this.sneak);
    }
    
    public void onDisable() {
        if (this.sneaking) {
            NoSlow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)NoSlow.mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneaking = false;
        }
    }
    
    @Override
    public void onUpdate() {
        if (!NoSlow.mc.field_71439_g.func_184587_cr() && this.sneak.isEnabled() && this.sneaking) {
            NoSlow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)NoSlow.mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneaking = false;
        }
    }
    
    @SubscribeEvent
    public void onUpdateInput(final InputUpdateEvent event) {
        if (NoSlow.mc.field_71439_g.func_184587_cr() && !NoSlow.mc.field_71439_g.func_184218_aH() && !this.sneak.isEnabled()) {
            final MovementInput movementInput = event.getMovementInput();
            movementInput.field_192832_b *= 5.0f;
            final MovementInput movementInput2 = event.getMovementInput();
            movementInput2.field_78902_a *= 5.0f;
        }
    }
    
    @SubscribeEvent
    public void onUseItem(final LivingEntityUseItemEvent event) {
        if (!this.sneaking && this.sneak.isEnabled()) {
            NoSlow.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)NoSlow.mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
            this.sneaking = true;
        }
    }
}
