// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import java.util.function.Predicate;
import net.minecraft.network.play.server.SPacketChunkData;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.module.Module;

public class NewChunks extends Module
{
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> PacketEvent;
    
    public NewChunks() {
        super("NoNewChunks", "Hides new chunks", Category.RENDER);
        SPacketChunkData c_Packet;
        this.PacketEvent = new Listener<HtlrEventPacket.ReceivePacket>(p_Event -> {
            if (p_Event.get_packet() instanceof SPacketChunkData) {
                c_Packet = (SPacketChunkData)p_Event.get_packet();
                if (!c_Packet.func_149274_i()) {
                    p_Event.cancel();
                }
            }
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
    }
}
