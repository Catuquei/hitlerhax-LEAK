// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import mod.hitlerhax.util.Globals;

public class HtlrInventory implements Globals
{
    public static void putInOffhand(final ItemStack stack) {
        final int slot = HtlrInventory.mc.field_71439_g.field_71071_by.func_184429_b(stack);
        HtlrInventory.mc.field_71442_b.func_187098_a(HtlrInventory.mc.field_71439_g.field_71069_bz.field_75152_c, (slot < 9) ? (slot + 36) : slot, 0, ClickType.PICKUP, (EntityPlayer)HtlrInventory.mc.field_71439_g);
        HtlrInventory.mc.field_71442_b.func_187098_a(HtlrInventory.mc.field_71439_g.field_71069_bz.field_75152_c, 45, 0, ClickType.PICKUP, (EntityPlayer)HtlrInventory.mc.field_71439_g);
        HtlrInventory.mc.field_71442_b.func_187098_a(HtlrInventory.mc.field_71439_g.field_71069_bz.field_75152_c, (slot < 9) ? (slot + 36) : slot, 0, ClickType.PICKUP, (EntityPlayer)HtlrInventory.mc.field_71439_g);
        HtlrInventory.mc.field_71442_b.func_78765_e();
    }
}
