// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.event.events.HtlrEventPush;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.module.Module;

public class Velocity extends Module
{
    final FloatSetting horizontal;
    final FloatSetting vertical;
    final BooleanSetting noPush;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> PacketEvent;
    @EventHandler
    private final Listener<HtlrEventPush> PushEvent;
    
    public Velocity() {
        super("Velocity", "Stops Knockback", Category.MOVEMENT);
        this.horizontal = new FloatSetting("Horizontal", this, 0.0f);
        this.vertical = new FloatSetting("Vertical", this, 0.0f);
        this.noPush = new BooleanSetting("No Push", this, true);
        SPacketEntityVelocity packet;
        this.PacketEvent = new Listener<HtlrEventPacket.ReceivePacket>(p_Event -> {
            if (p_Event.get_packet() instanceof SPacketEntityVelocity) {
                packet = (SPacketEntityVelocity)p_Event.get_packet();
                if (packet.func_149412_c() == Velocity.mc.field_71439_g.func_145782_y()) {
                    p_Event.cancel();
                    return;
                }
            }
            if (p_Event.get_packet() instanceof SPacketExplosion) {
                p_Event.cancel();
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.PushEvent = new Listener<HtlrEventPush>(event -> event.cancel(), (Predicate<HtlrEventPush>[])new Predicate[0]);
        this.addSetting(this.horizontal, this.vertical, this.noPush);
    }
    
    public void onPush(final HtlrEventPush event) {
        if (event.stage == 0 && this.noPush.isEnabled() && event.entity.equals((Object)Velocity.mc.field_71439_g)) {
            event.cancel();
        }
    }
}
