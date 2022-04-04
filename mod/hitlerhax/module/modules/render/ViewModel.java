// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHandSide;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventTransformSideFirstPerson;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class ViewModel extends Module
{
    public BooleanSetting cancelEating;
    public final FloatSetting LeftX;
    public final FloatSetting LeftY;
    public final FloatSetting LeftZ;
    public final FloatSetting RightX;
    public final FloatSetting RightY;
    public final FloatSetting RightZ;
    @EventHandler
    private final Listener<HtlrEventTransformSideFirstPerson> listener;
    
    public ViewModel() {
        super("ViewModel", "allows u to change how ur model look in 1st person.", Category.RENDER);
        this.cancelEating = new BooleanSetting("noEat", this, false);
        this.LeftX = new FloatSetting("LeftX", this, 0.0f);
        this.LeftY = new FloatSetting("LeftY", this, 0.0f);
        this.LeftZ = new FloatSetting("LeftZ", this, 0.0f);
        this.RightX = new FloatSetting("RightX", this, 0.0f);
        this.RightY = new FloatSetting("RightY", this, 0.0f);
        this.RightZ = new FloatSetting("RightZ", this, 0.0f);
        this.listener = new Listener<HtlrEventTransformSideFirstPerson>(event -> {
            if (event.getEnumHandSide() == EnumHandSide.RIGHT) {
                GlStateManager.func_179109_b(this.RightX.getValue(), this.RightY.getValue(), this.RightZ.getValue());
            }
            else if (event.getEnumHandSide() == EnumHandSide.LEFT) {
                GlStateManager.func_179109_b(this.LeftX.getValue(), this.LeftY.getValue(), this.LeftZ.getValue());
            }
            return;
        }, (Predicate<HtlrEventTransformSideFirstPerson>[])new Predicate[0]);
        this.addSetting(this.LeftX, this.LeftY, this.LeftZ, this.RightX, this.RightY, this.RightZ);
    }
}
