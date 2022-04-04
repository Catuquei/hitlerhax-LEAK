// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class AutoWalk extends Module
{
    public AutoWalk() {
        super("AutoWalk", "Walk Automatically", Category.MOVEMENT);
    }
    
    @SubscribeEvent
    public void onUpdateInput(final InputUpdateEvent event) {
        event.getMovementInput().field_192832_b = 1.0f;
    }
}
