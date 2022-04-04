// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import net.minecraft.client.entity.EntityPlayerSP;
import mod.hitlerhax.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import mod.hitlerhax.setting.Setting;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import me.zero.alpine.event.type.Cancellable;
import java.util.function.Predicate;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.event.events.HtlrEventPush;
import mod.hitlerhax.event.events.HtlrEventPacket;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import mod.hitlerhax.event.events.HtlrEventSetOpaqueCube;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventMove;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.module.Module;

public class Freecam extends Module
{
    final FloatSetting speedSetting;
    final BooleanSetting view;
    final BooleanSetting packetCancel;
    private AxisAlignedBB oldBoundingBox;
    private EntityOtherPlayerMP entity;
    private Vec3d position;
    private float yaw;
    private float pitch;
    @EventHandler
    private final Listener<HtlrEventMove> OnPlayerMove;
    @EventHandler
    private final Listener<HtlrEventSetOpaqueCube> OnEventSetOpaqueCube;
    @EventHandler
    private final Listener<EntityJoinWorldEvent> OnWorldEvent;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> PacketRecieveEvent;
    @EventHandler
    private final Listener<HtlrEventPacket.SendPacket> PacketSendEvent;
    @EventHandler
    private final Listener<HtlrEventPush> PushEvent;
    
    public Freecam() {
        super("Freecam", "Allows Spectator Mode Outside The Body", Category.RENDER);
        this.speedSetting = new FloatSetting("Speed", this, 1.0f);
        this.view = new BooleanSetting("View", this, false);
        this.packetCancel = new BooleanSetting("Packet Cancel", this, true);
        this.OnPlayerMove = new Listener<HtlrEventMove>(p_Event -> Freecam.mc.field_71439_g.field_70145_X = true, (Predicate<HtlrEventMove>[])new Predicate[0]);
        this.OnEventSetOpaqueCube = new Listener<HtlrEventSetOpaqueCube>(Cancellable::cancel, (Predicate<HtlrEventSetOpaqueCube>[])new Predicate[0]);
        this.OnWorldEvent = new Listener<EntityJoinWorldEvent>(p_Event -> {
            if (p_Event.getEntity() == Freecam.mc.field_71439_g) {
                this.toggle();
            }
            return;
        }, (Predicate<EntityJoinWorldEvent>[])new Predicate[0]);
        SPacketPlayerPosLook packet2;
        this.PacketRecieveEvent = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof SPacketPlayerPosLook) {
                packet2 = (SPacketPlayerPosLook)event.get_packet();
                if (this.packetCancel.enabled) {
                    if (this.entity != null) {
                        this.entity.func_70080_a(packet2.func_148932_c(), packet2.func_148928_d(), packet2.func_148933_e(), packet2.func_148931_f(), packet2.func_148930_g());
                    }
                    this.position = new Vec3d(packet2.func_148932_c(), packet2.func_148928_d(), packet2.func_148933_e());
                    Freecam.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketConfirmTeleport(packet2.func_186965_f()));
                }
                event.cancel();
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.PacketSendEvent = new Listener<HtlrEventPacket.SendPacket>(event -> {
            if (this.packetCancel.enabled) {
                if (event.get_packet() instanceof CPacketPlayer) {
                    event.cancel();
                }
            }
            else if (!(event.get_packet() instanceof CPacketUseEntity) && !(event.get_packet() instanceof CPacketPlayerTryUseItem) && !(event.get_packet() instanceof CPacketPlayerTryUseItemOnBlock) && !(event.get_packet() instanceof CPacketPlayer) && !(event.get_packet() instanceof CPacketVehicleMove) && !(event.get_packet() instanceof CPacketChatMessage) && !(event.get_packet() instanceof CPacketKeepAlive)) {
                event.cancel();
            }
            return;
        }, (Predicate<HtlrEventPacket.SendPacket>[])new Predicate[0]);
        this.PushEvent = new Listener<HtlrEventPush>(event -> {
            if (event.stage == 1) {
                event.cancel();
            }
            return;
        }, (Predicate<HtlrEventPush>[])new Predicate[0]);
        this.addSetting(this.speedSetting);
        this.addSetting(this.packetCancel);
        this.addSetting(this.view);
    }
    
    public void onEnable() {
        super.onEnable();
        if (Freecam.mc.field_71441_e == null) {
            this.toggle();
            return;
        }
        Freecam.mc.field_71439_g.func_184210_p();
        this.oldBoundingBox = Freecam.mc.field_71439_g.func_174813_aQ();
        Freecam.mc.field_71439_g.func_174826_a(new AxisAlignedBB(Freecam.mc.field_71439_g.field_70165_t, Freecam.mc.field_71439_g.field_70163_u, Freecam.mc.field_71439_g.field_70161_v, Freecam.mc.field_71439_g.field_70165_t, Freecam.mc.field_71439_g.field_70163_u, Freecam.mc.field_71439_g.field_70161_v));
        (this.entity = new EntityOtherPlayerMP((World)Freecam.mc.field_71441_e, Freecam.mc.func_110432_I().func_148256_e())).func_82149_j((Entity)Freecam.mc.field_71439_g);
        this.entity.field_70177_z = Freecam.mc.field_71439_g.field_70177_z;
        this.entity.field_70759_as = Freecam.mc.field_71439_g.field_70759_as;
        this.entity.field_71071_by.func_70455_b(Freecam.mc.field_71439_g.field_71071_by);
        Freecam.mc.field_71441_e.func_73027_a(69420, (Entity)this.entity);
        this.position = Freecam.mc.field_71439_g.func_174791_d();
        this.yaw = Freecam.mc.field_71439_g.field_70177_z;
        this.pitch = Freecam.mc.field_71439_g.field_70125_A;
        Freecam.mc.field_71439_g.field_70145_X = true;
    }
    
    public void onDisable() {
        super.onDisable();
        if (Freecam.mc.field_71441_e == null) {
            return;
        }
        Freecam.mc.field_71439_g.func_174826_a(this.oldBoundingBox);
        if (this.entity != null) {
            Freecam.mc.field_71441_e.func_72900_e((Entity)this.entity);
        }
        if (this.position != null) {
            Freecam.mc.field_71439_g.func_70107_b(this.position.field_72450_a, this.position.field_72448_b, this.position.field_72449_c);
        }
        Freecam.mc.field_71439_g.field_70177_z = this.yaw;
        Freecam.mc.field_71439_g.field_70125_A = this.pitch;
        Freecam.mc.field_71439_g.field_70145_X = false;
    }
    
    @Override
    public void onUpdate() {
        Freecam.mc.field_71439_g.field_70145_X = true;
        Freecam.mc.field_71439_g.func_70016_h(0.0, 0.0, 0.0);
        Freecam.mc.field_71439_g.field_70747_aH = this.speedSetting.getValue();
        final double[] dir = MathUtil.directionSpeed(this.speedSetting.getValue());
        if (Freecam.mc.field_71439_g.field_71158_b.field_78902_a != 0.0f || Freecam.mc.field_71439_g.field_71158_b.field_192832_b != 0.0f) {
            Freecam.mc.field_71439_g.field_70159_w = dir[0];
            Freecam.mc.field_71439_g.field_70179_y = dir[1];
        }
        else {
            Freecam.mc.field_71439_g.field_70159_w = 0.0;
            Freecam.mc.field_71439_g.field_70179_y = 0.0;
        }
        Freecam.mc.field_71439_g.func_70031_b(false);
        if (this.view.enabled && !Freecam.mc.field_71474_y.field_74311_E.func_151470_d() && !Freecam.mc.field_71474_y.field_74314_A.func_151470_d()) {
            Freecam.mc.field_71439_g.field_70181_x = this.speedSetting.getValue() * -MathUtil.degToRad(Freecam.mc.field_71439_g.field_70125_A) * Freecam.mc.field_71439_g.field_71158_b.field_192832_b;
        }
        if (Freecam.mc.field_71474_y.field_74314_A.func_151470_d()) {
            final EntityPlayerSP field_71439_g;
            final EntityPlayerSP player = field_71439_g = Freecam.mc.field_71439_g;
            field_71439_g.field_70181_x += this.speedSetting.getValue();
        }
        if (Freecam.mc.field_71474_y.field_74311_E.func_151470_d()) {
            final EntityPlayerSP field_71439_g2;
            final EntityPlayerSP player2 = field_71439_g2 = Freecam.mc.field_71439_g;
            field_71439_g2.field_70181_x -= this.speedSetting.getValue();
        }
    }
}
