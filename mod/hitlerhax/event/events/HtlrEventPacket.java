// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import net.minecraft.network.Packet;
import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventPacket extends HtlrEventCancellable
{
    private final Packet<?> packet;
    
    public HtlrEventPacket(final Packet<?> packet) {
        this.packet = packet;
    }
    
    public Packet<?> get_packet() {
        return this.packet;
    }
    
    public static class ReceivePacket extends HtlrEventPacket
    {
        public ReceivePacket(final Packet<?> packet) {
            super(packet);
        }
    }
    
    public static class SendPacket extends HtlrEventPacket
    {
        public SendPacket(final Packet<?> packet) {
            super(packet);
        }
    }
}
