// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import mod.hitlerhax.event.HtlrEventBus;
import mod.hitlerhax.event.events.HtlrEventPacket;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.Packet;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ NetworkManager.class })
public class HtlrMixinNetworkManager
{
    @Inject(method = { "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void receive(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo callback) {
        final HtlrEventPacket event_packet = new HtlrEventPacket.ReceivePacket(packet);
        HtlrEventBus.EVENT_BUS.post(event_packet);
        if (event_packet.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void send(final Packet<?> packet, final CallbackInfo callback) {
        final HtlrEventPacket event_packet = new HtlrEventPacket.SendPacket(packet);
        HtlrEventBus.EVENT_BUS.post(event_packet);
        if (event_packet.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "exceptionCaught" }, at = { @At("HEAD") }, cancellable = true)
    private void exception(final ChannelHandlerContext exc, final Throwable exc_, final CallbackInfo callback) {
        if (exc_ instanceof Exception) {
            callback.cancel();
        }
    }
}
