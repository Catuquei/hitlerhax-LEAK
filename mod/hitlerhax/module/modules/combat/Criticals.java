// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLivingBase;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketUseEntity;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class Criticals extends Module
{
    final ModeSetting mode;
    CPacketUseEntity packet;
    @EventHandler
    private final Listener<HtlrEventPacket.SendPacket> listener;
    
    public Criticals() {
        super("Criticals", "Makes normal hits critical hits", Category.COMBAT);
        this.mode = new ModeSetting("Mode", this, "NCPStrict", new String[] { "NCPStrict", "Packet", "Jump" });
        CPacketUseEntity packet;
        this.listener = new Listener<HtlrEventPacket.SendPacket>(event -> {
            if (event.get_packet() instanceof CPacketUseEntity) {
                packet = (CPacketUseEntity)event.get_packet();
                if (packet.func_149565_c() == CPacketUseEntity.Action.ATTACK && packet.func_149564_a((World)Criticals.mc.field_71441_e) instanceof EntityLivingBase && Criticals.mc.field_71439_g.field_70122_E && !Criticals.mc.field_71474_y.field_74314_A.func_151470_d()) {
                    if (this.mode.is("NCPStrict")) {
                        Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u + 0.062602401692772, Criticals.mc.field_71439_g.field_70161_v, false));
                        Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u + 0.0726023996066094, Criticals.mc.field_71439_g.field_70161_v, false));
                        Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u, Criticals.mc.field_71439_g.field_70161_v, false));
                    }
                    if (this.mode.is("Jump")) {
                        Criticals.mc.field_71439_g.func_70664_aZ();
                        Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u - 0.05, Criticals.mc.field_71439_g.field_70161_v, false));
                        Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u, Criticals.mc.field_71439_g.field_70161_v, false));
                    }
                    if (this.mode.is("Packet")) {
                        Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u + 0.10000000149011612, Criticals.mc.field_71439_g.field_70161_v, false));
                        Criticals.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Criticals.mc.field_71439_g.field_70165_t, Criticals.mc.field_71439_g.field_70163_u, Criticals.mc.field_71439_g.field_70161_v, false));
                    }
                }
            }
            return;
        }, (Predicate<HtlrEventPacket.SendPacket>[])new Predicate[0]);
        this.addSetting(this.mode);
    }
}
