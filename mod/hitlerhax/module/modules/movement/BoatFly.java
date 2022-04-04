// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import net.minecraft.world.chunk.EmptyChunk;
import mod.hitlerhax.Main;
import me.zero.alpine.listener.Listenable;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import mod.hitlerhax.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import java.util.Collections;
import net.minecraft.network.play.server.SPacketSetPassengers;
import java.util.function.Predicate;
import net.minecraft.network.play.client.CPacketSteerBoat;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.entity.item.EntityBoat;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.event.events.HtlrEventPlayerTravel;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.module.Module;

public class BoatFly extends Module
{
    private final FloatSetting speed;
    private final FloatSetting upSpeed;
    private final FloatSetting glideSpeed;
    private final BooleanSetting antiStuck;
    private final BooleanSetting remount;
    private final BooleanSetting antiForceLook;
    private final BooleanSetting forceInteract;
    private final BooleanSetting teleportSpoof;
    private final BooleanSetting cancelPlayer;
    private final BooleanSetting antiDesync;
    @EventHandler
    private final Listener<HtlrEventPacket.SendPacket> PacketSendEvent;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> PacketReceiveEvent;
    @EventHandler
    private final Listener<HtlrEventPlayerTravel> playerTravel;
    
    public BoatFly() {
        super("BoatFly", "Allows Flight In Boats", Category.MOVEMENT);
        this.speed = new FloatSetting("Speed", this, 0.1f);
        this.upSpeed = new FloatSetting("Up Speed", this, 1.0f);
        this.glideSpeed = new FloatSetting("Glide Speed", this, 0.1f);
        this.antiStuck = new BooleanSetting("Anti Stuck", this, true);
        this.remount = new BooleanSetting("Remount", this, true);
        this.antiForceLook = new BooleanSetting("Anti Force Look", this, true);
        this.forceInteract = new BooleanSetting("Force Interact", this, true);
        this.teleportSpoof = new BooleanSetting("Teleport Spoof", this, false);
        this.cancelPlayer = new BooleanSetting("Cancel Player Packets", this, false);
        this.antiDesync = new BooleanSetting("Anti Desync", this, false);
        Entity ridingEntity;
        this.PacketSendEvent = new Listener<HtlrEventPacket.SendPacket>(event -> {
            if (BoatFly.mc.field_71439_g == null || BoatFly.mc.field_71441_e == null) {
                return;
            }
            else {
                ridingEntity = BoatFly.mc.field_71439_g.field_184239_as;
                if (!(ridingEntity instanceof EntityBoat) || !this.cancelPlayer.enabled) {
                    return;
                }
                else {
                    if (event.get_packet() instanceof CPacketPlayer || event.get_packet() instanceof CPacketInput || event.get_packet() instanceof CPacketSteerBoat) {
                        if (event.get_packet() instanceof CPacketInput) {
                            if (event.get_packet().equals(new CPacketInput(0.0f, 0.0f, false, true))) {
                                return;
                            }
                        }
                        event.cancel();
                    }
                    return;
                }
            }
        }, (Predicate<HtlrEventPacket.SendPacket>[])new Predicate[0]);
        Entity ridingEntity2;
        Entity entity;
        Entity entity2;
        this.PacketReceiveEvent = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (BoatFly.mc.field_71439_g == null || BoatFly.mc.field_71441_e == null) {
                return;
            }
            else {
                ridingEntity2 = BoatFly.mc.field_71439_g.field_184239_as;
                if (!(ridingEntity2 instanceof EntityBoat)) {
                    return;
                }
                else {
                    if (event.get_packet() instanceof SPacketSetPassengers) {
                        entity = BoatFly.mc.field_71441_e.func_73045_a(((SPacketSetPassengers)event.get_packet()).func_186972_b());
                        if (this.remount.enabled && entity != null) {
                            if (!Collections.singletonList(((SPacketSetPassengers)event.get_packet()).func_186971_a()).contains(BoatFly.mc.field_71439_g.func_145782_y()) && ridingEntity2.field_145783_c == ((SPacketSetPassengers)event.get_packet()).func_186972_b()) {
                                if (this.teleportSpoof.enabled) {
                                    event.cancel();
                                }
                                BoatFly.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(entity, EnumHand.OFF_HAND));
                            }
                            else if (!Collections.singletonList(((SPacketSetPassengers)event.get_packet()).func_186971_a()).isEmpty() && Collections.singletonList(((SPacketSetPassengers)event.get_packet()).func_186971_a()).contains(BoatFly.mc.field_71439_g.func_145782_y()) && this.antiForceLook.enabled) {
                                entity.field_70177_z = BoatFly.mc.field_71439_g.field_70126_B;
                                entity.field_70125_A = BoatFly.mc.field_71439_g.field_70127_C;
                            }
                        }
                    }
                    else if (event.get_packet() instanceof SPacketPlayerPosLook) {
                        if (this.antiForceLook.enabled) {
                            ((SPacketPlayerPosLook)event.get_packet()).field_148936_d = BoatFly.mc.field_71439_g.field_70177_z;
                            ((SPacketPlayerPosLook)event.get_packet()).field_148937_e = BoatFly.mc.field_71439_g.field_70125_A;
                        }
                    }
                    else if (event.get_packet() instanceof SPacketEntityTeleport) {
                        if (this.teleportSpoof.enabled && ((SPacketEntityTeleport)event.get_packet()).func_149451_c() == ridingEntity2.field_145783_c) {
                            if (BoatFly.mc.field_71439_g.func_174791_d().func_72438_d(new Vec3d(((SPacketEntityTeleport)event.get_packet()).func_186982_b(), ((SPacketEntityTeleport)event.get_packet()).func_186983_c(), ((SPacketEntityTeleport)event.get_packet()).func_186981_d())) > 20.0) {
                                entity2 = BoatFly.mc.field_71441_e.func_73045_a(((SPacketEntityTeleport)event.get_packet()).func_149451_c());
                                if (entity2 != null) {
                                    BoatFly.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(entity2, EnumHand.OFF_HAND));
                                }
                            }
                            else {
                                if (this.antiForceLook.enabled) {
                                    event.cancel();
                                }
                                ridingEntity2.field_70165_t = ((SPacketEntityTeleport)event.get_packet()).func_186982_b();
                                ridingEntity2.field_70163_u = ((SPacketEntityTeleport)event.get_packet()).func_186983_c();
                                ridingEntity2.field_70161_v = ((SPacketEntityTeleport)event.get_packet()).func_186981_d();
                            }
                        }
                    }
                    else if (event.get_packet() instanceof SPacketMoveVehicle && this.forceInteract.enabled) {
                        event.cancel();
                    }
                    return;
                }
            }
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        final Entity r;
        EntityBoat ridingEntity3;
        this.playerTravel = new Listener<HtlrEventPlayerTravel>(event -> {
            r = BoatFly.mc.field_71439_g.field_184239_as;
            if (!(r instanceof EntityBoat)) {
                return;
            }
            else {
                ridingEntity3 = (EntityBoat)r;
                ridingEntity3.field_70177_z = BoatFly.mc.field_71439_g.field_70177_z;
                ridingEntity3.func_184442_a(false, false, false, false);
                ridingEntity3.func_189654_d(true);
                ridingEntity3.field_70181_x = 0.0;
                if (this.glideSpeed.value > 0.0f && !BoatFly.mc.field_71474_y.field_74314_A.func_151470_d()) {
                    ridingEntity3.field_70181_x = -this.glideSpeed.value;
                }
                if (BoatFly.mc.field_71474_y.field_74314_A.func_151470_d()) {
                    ridingEntity3.field_70181_x = this.upSpeed.value;
                }
                if (BoatFly.mc.field_71474_y.field_74368_y.func_151470_d()) {
                    ridingEntity3.field_70181_x = -this.upSpeed.value;
                }
                this.steerEntity((Entity)ridingEntity3, this.speed.value, this.antiStuck.enabled);
                return;
            }
        }, (Predicate<HtlrEventPlayerTravel>[])new Predicate[0]);
        this.addSetting(this.speed);
        this.addSetting(this.upSpeed);
        this.addSetting(this.glideSpeed);
        this.addSetting(this.antiStuck);
        this.addSetting(this.remount);
        this.addSetting(this.antiForceLook);
        this.addSetting(this.forceInteract);
        this.addSetting(this.teleportSpoof);
        this.addSetting(this.cancelPlayer);
        this.addSetting(this.antiDesync);
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        HtlrEventBus.EVENT_BUS.unsubscribe(this);
        Main.config.Save();
        if (this.antiDesync.enabled) {
            BoatFly.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketInput(0.0f, 0.0f, false, true));
            BoatFly.mc.field_71439_g.func_184210_p();
        }
    }
    
    public void steerEntity(final Entity entity, final Float speed, final Boolean antiStuck) {
        final double yawRad = this.calcMoveYaw();
        final double motionX = -Math.sin(yawRad) * speed;
        final double motionZ = Math.cos(yawRad) * speed;
        if ((BoatFly.mc.field_71439_g.field_71158_b.field_192832_b != 0.0f || BoatFly.mc.field_71439_g.field_71158_b.field_78902_a != 0.0f) && !this.isBorderingChunk(entity, motionX, motionZ, antiStuck)) {
            BoatFly.mc.field_71439_g.field_184239_as.field_70159_w = motionX;
            BoatFly.mc.field_71439_g.field_184239_as.field_70179_y = motionZ;
        }
        else {
            BoatFly.mc.field_71439_g.field_184239_as.field_70159_w = 0.0;
            BoatFly.mc.field_71439_g.field_184239_as.field_70179_y = 0.0;
        }
    }
    
    public double calcMoveYaw() {
        final float yawIn = BoatFly.mc.field_71439_g.field_70177_z;
        final float moveForward = this.getRoundedMovementInput(BoatFly.mc.field_71439_g.field_71158_b.field_192832_b);
        final float moveString = this.getRoundedMovementInput(BoatFly.mc.field_71439_g.field_71158_b.field_78902_a);
        float strafe = 90.0f * moveString;
        strafe *= ((moveForward != 0.0f) ? (moveForward * 0.5f) : 1.0f);
        float yaw = yawIn - strafe;
        yaw -= ((moveForward < 0.0f) ? 180.0f : 0.0f);
        return Math.toRadians(yaw);
    }
    
    public float getRoundedMovementInput(final float input) {
        if (input > 0.0f) {
            return 1.0f;
        }
        if (input < 0.0f) {
            return -1.0f;
        }
        return 0.0f;
    }
    
    public boolean isBorderingChunk(final Entity entity, final double motionX, final double motionZ, final boolean antiStuck) {
        return antiStuck && BoatFly.mc.field_71441_e.func_72964_e((int)(entity.field_70165_t + motionX) >> 4, (int)(entity.field_70161_v + motionZ) >> 4) instanceof EmptyChunk;
    }
}
