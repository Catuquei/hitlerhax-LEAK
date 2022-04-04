// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import org.spongepowered.asm.mixin.injection.Redirect;
import mod.hitlerhax.event.HtlrEventBus;
import mod.hitlerhax.event.events.HtlrEventRenderCamera;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import mod.hitlerhax.Main;
import mod.hitlerhax.module.modules.render.NoRender;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityRenderer.class })
public class HtlrMixinEntityRenderer
{
    @Inject(method = { "hurtCameraEffect" }, at = { @At("HEAD") }, cancellable = true)
    public void hurtCameraEffect(final float ticks, final CallbackInfo info) {
        final NoRender noRender = (NoRender)Main.moduleManager.getModule("noRender");
        if (noRender.isToggled() && noRender.hurtCam.is("normal")) {
            info.cancel();
        }
    }
    
    @Redirect(method = { "orientCamera" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"), expect = 0)
    private RayTraceResult rayTraceBlocks(final WorldClient worldClient, final Vec3d start, final Vec3d end) {
        final HtlrEventRenderCamera event = new HtlrEventRenderCamera();
        HtlrEventBus.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            return null;
        }
        return worldClient.func_72933_a(start, end);
    }
}
