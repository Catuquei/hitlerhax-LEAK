// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import net.minecraft.network.play.server.SPacketChat;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.StringSetting;
import mod.hitlerhax.module.Module;

public class AFKReply extends Module
{
    final StringSetting msg;
    long lastSent;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> PacketRecieveEvent;
    
    public AFKReply() {
        super("AFKReply", "replies for you when you are AFK", Category.UTILITIES);
        this.msg = new StringSetting("Message", this, "[HitlerHax AFKReply]: I'm currently AFK!");
        this.lastSent = 0L;
        String m;
        this.PacketRecieveEvent = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof SPacketChat) {
                m = ((SPacketChat)event.get_packet()).func_148915_c().func_150254_d();
                if (m.contains("§d") && m.contains("whispers: ") && this.lastSent + 100L < System.currentTimeMillis()) {
                    AFKReply.mc.field_71439_g.func_71165_d("/r " + this.msg.value);
                    this.lastSent = System.currentTimeMillis();
                }
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.addSetting(this.msg);
    }
}
