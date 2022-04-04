// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import mod.hitlerhax.Main;
import mod.hitlerhax.module.modules.render.NoRender;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ LayerBipedArmor.class })
public class HtlrMixinLayerBipedArmor
{
    @Inject(method = { "setModelSlotVisible(Lnet/minecraft/client/model/ModelBiped;Lnet/minecraft/inventory/EntityEquipmentSlot;)V" }, at = { @At("HEAD") }, cancellable = true)
    protected void setModelSlotVisible(final ModelBiped model, final EntityEquipmentSlot slot, final CallbackInfo callbackInfo) {
        final NoRender noRender = (NoRender)Main.moduleManager.getModule("noRender");
        if (noRender.isToggled() && noRender.armor.isEnabled()) {
            callbackInfo.cancel();
            switch (slot) {
                case HEAD: {
                    model.field_78116_c.field_78806_j = false;
                    model.field_178720_f.field_78806_j = false;
                }
                case CHEST: {
                    model.field_78115_e.field_78806_j = false;
                    model.field_178723_h.field_78806_j = false;
                    model.field_178724_i.field_78806_j = false;
                }
                case LEGS: {
                    model.field_78115_e.field_78806_j = false;
                    model.field_178721_j.field_78806_j = false;
                    model.field_178722_k.field_78806_j = false;
                }
                case FEET: {
                    model.field_178721_j.field_78806_j = false;
                    model.field_178722_k.field_78806_j = false;
                    break;
                }
            }
        }
    }
}
