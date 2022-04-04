// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.player;

import java.util.function.Predicate;
import net.minecraft.network.play.client.CPacketCloseWindow;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.module.Module;

public class XCarry extends Module
{
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> PacketRecieveEvent;
    @EventHandler
    private final Listener<HtlrEventPacket.SendPacket> PacketSendEvent;
    
    public XCarry() {
        super("XCarry", "Keep items in your crafting slot", Category.PLAYER);
        this.PacketRecieveEvent = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof CPacketCloseWindow) {
                event.cancel();
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.PacketSendEvent = new Listener<HtlrEventPacket.SendPacket>(event -> {
            if (event.get_packet() instanceof CPacketCloseWindow) {
                event.cancel();
            }
        }, (Predicate<HtlrEventPacket.SendPacket>[])new Predicate[0]);
    }
}
