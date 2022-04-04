// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.InputUpdateEvent;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class Parkour extends Module
{
    public Parkour() {
        super("Parkour", "Jump off the edge of blocks", Category.MOVEMENT);
    }
    
    @SubscribeEvent
    public void onUpdateInput(final InputUpdateEvent event) {
        if (Parkour.mc.field_71439_g.field_70122_E && !Parkour.mc.field_71439_g.func_70093_af() && !Parkour.mc.field_71474_y.field_74314_A.func_151468_f() && Parkour.mc.field_71441_e.func_184144_a((Entity)Parkour.mc.field_71439_g, Parkour.mc.field_71439_g.func_174813_aQ().func_72317_d(0.0, -0.5, 0.0).func_72321_a(-0.001, 0.0, -0.001)).isEmpty()) {
            Parkour.mc.field_71439_g.func_70664_aZ();
        }
    }
}
