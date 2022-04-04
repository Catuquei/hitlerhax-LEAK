// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class NoHunger extends Module
{
    final ModeSetting mode;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> PacketEvent;
    
    public NoHunger() {
        super("NoHunger", "Prevents You From Losing Hunger", Category.UTILITIES);
        this.mode = new ModeSetting("Mode", this, "Packet", new String[] { "Packet", "Vanilla" });
        CPacketPlayer l_Packet;
        CPacketEntityAction l_Packet2;
        this.PacketEvent = new Listener<HtlrEventPacket.ReceivePacket>(p_Event -> {
            if (this.mode.getMode().equals("Packet")) {
                if (p_Event.get_packet() instanceof CPacketPlayer && !NoHunger.mc.field_71439_g.func_184613_cA()) {
                    l_Packet = (CPacketPlayer)p_Event.get_packet();
                    l_Packet.field_149474_g = (NoHunger.mc.field_71439_g.field_70143_R > 0.0f || NoHunger.mc.field_71442_b.field_78778_j);
                }
                if (p_Event.get_packet() instanceof CPacketEntityAction) {
                    l_Packet2 = (CPacketEntityAction)p_Event.get_packet();
                    if (l_Packet2.func_180764_b() == CPacketEntityAction.Action.START_SPRINTING || l_Packet2.func_180764_b() == CPacketEntityAction.Action.STOP_SPRINTING) {
                        p_Event.cancel();
                    }
                }
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.addSetting(this.mode);
    }
    
    @Override
    public void onUpdate() {
        if (this.mode.getMode().equals("Vanilla")) {
            NoHunger.mc.field_71439_g.func_71024_bL().func_75114_a(20);
        }
    }
}
