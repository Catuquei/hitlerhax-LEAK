// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util;

import mod.hitlerhax.misc.RotationPriority;
import net.minecraft.network.play.client.CPacketPlayer;
import mod.hitlerhax.event.events.HtlrEventPacket;
import mod.hitlerhax.event.events.HtlrEventRotation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Comparator;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import mod.hitlerhax.misc.Rotation;
import java.util.concurrent.LinkedBlockingQueue;

public class RotationUtil
{
    public static final LinkedBlockingQueue<Rotation> rotationQueue;
    public static Rotation serverRotation;
    public static Rotation currentRotation;
    public static final float yawleftOver = 0.0f;
    public static final float pitchleftOver = 0.0f;
    public static int tick;
    
    public RotationUtil() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event) {
        RotationUtil.rotationQueue.stream().sorted(Comparator.comparing(rotation -> rotation.rotationPriority.getPriority()));
        if (RotationUtil.currentRotation != null) {
            RotationUtil.currentRotation = null;
        }
        if (!RotationUtil.rotationQueue.isEmpty()) {
            (RotationUtil.currentRotation = RotationUtil.rotationQueue.poll()).updateRotations();
        }
        ++RotationUtil.tick;
    }
    
    @SubscribeEvent
    public void onRotate(final HtlrEventRotation event) {
        try {
            if (RotationUtil.currentRotation != null && RotationUtil.currentRotation.mode.equals(Rotation.RotationMode.Packet)) {
                event.setCanceled(true);
                if (RotationUtil.tick == 1) {
                    event.setYaw(RotationUtil.currentRotation.yaw + 0.0f);
                    event.setPitch(RotationUtil.currentRotation.pitch + 0.0f);
                }
                else {
                    event.setYaw(RotationUtil.currentRotation.yaw);
                    event.setPitch(RotationUtil.currentRotation.pitch);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @SubscribeEvent
    public void onPacketSend(final HtlrEventPacket.SendPacket event) {
        if (RotationUtil.currentRotation != null && !RotationUtil.rotationQueue.isEmpty() && event.get_packet() instanceof CPacketPlayer && ((CPacketPlayer)event.get_packet()).field_149481_i) {
            RotationUtil.serverRotation = new Rotation(((CPacketPlayer)event.get_packet()).field_149476_e, ((CPacketPlayer)event.get_packet()).field_149473_f, Rotation.RotationMode.Packet, RotationPriority.Lowest);
        }
    }
    
    static {
        rotationQueue = new LinkedBlockingQueue<Rotation>();
        RotationUtil.serverRotation = null;
        RotationUtil.currentRotation = null;
        RotationUtil.tick = 5;
    }
}
