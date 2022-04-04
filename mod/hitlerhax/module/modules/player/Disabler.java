// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.player;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.util.EnumFacing;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraftforge.client.event.InputUpdateEvent;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class Disabler extends Module
{
    public Disabler() {
        super("Disabler", "Prevent AntiCheat flags", Category.PLAYER);
    }
    
    @SubscribeEvent
    public void onUpdateInput(final InputUpdateEvent event) {
        if (!Disabler.mc.field_71439_g.func_184613_cA() && !Disabler.mc.field_71439_g.func_110317_t() && !Disabler.mc.field_71439_g.field_184844_co) {
            Disabler.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, GetLocalPlayerPosFloored(), EnumFacing.DOWN));
        }
    }
    
    private static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(Disabler.mc.field_71439_g.field_70165_t), Math.floor(Disabler.mc.field_71439_g.field_70163_u), Math.floor(Disabler.mc.field_71439_g.field_70161_v));
    }
}
