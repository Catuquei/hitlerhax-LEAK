// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event;

import net.minecraft.util.math.MathHelper;
import java.util.Arrays;
import java.util.function.Predicate;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Listenable;

public class HtlrEventHandler implements Listenable
{
    public static HtlrEventHandler INSTANCE;
    static final float[] ticks;
    private long last_update_tick;
    private int next_index;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> receive_event_packet;
    
    public HtlrEventHandler() {
        this.next_index = 0;
        this.receive_event_packet = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof SPacketTimeUpdate) {
                HtlrEventHandler.INSTANCE.update_time();
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        HtlrEventBus.EVENT_BUS.subscribe(this);
        this.reset_tick();
    }
    
    public void reset_tick() {
        this.next_index = 0;
        this.last_update_tick = -1L;
        Arrays.fill(HtlrEventHandler.ticks, 0.0f);
    }
    
    public void update_time() {
        if (this.last_update_tick != -1L) {
            final float time = (System.currentTimeMillis() - this.last_update_tick) / 1000.0f;
            HtlrEventHandler.ticks[this.next_index % HtlrEventHandler.ticks.length] = MathHelper.func_76131_a(20.0f / time, 0.0f, 20.0f);
            ++this.next_index;
        }
        this.last_update_tick = System.currentTimeMillis();
    }
    
    static {
        ticks = new float[20];
    }
}
