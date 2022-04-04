// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import net.minecraft.client.renderer.ItemRenderer;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.module.Module;

public class LowOffHand extends Module
{
    final FloatSetting height;
    final ItemRenderer itemRenderer;
    
    public LowOffHand() {
        super("LowOffHand", "Lowers your offhand", Category.RENDER);
        this.height = new FloatSetting("Height", this, 0.7f);
        this.itemRenderer = LowOffHand.mc.field_71460_t.field_78516_c;
        this.addSetting(this.height);
    }
    
    @Override
    public void onUpdate() {
        this.itemRenderer.field_187471_h = this.height.getValue();
    }
}
