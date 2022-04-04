// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;

public class InventoryUtil implements Globals
{
    public static boolean isItemStackNull(final ItemStack stack) {
        return stack == null || stack.func_77973_b() instanceof ItemAir;
    }
    
    public static int getBlockInHotbar(final Block block) {
        for (int i = 0; i < 9; ++i) {
            final Item item = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
            if (item instanceof ItemBlock && ((ItemBlock)item).func_179223_d().equals(block)) {
                return i;
            }
        }
        return -1;
    }
    
    public static void moveItemToOffhand(final int slot) {
        int returnSlot = -1;
        if (slot == -1) {
            return;
        }
        InventoryUtil.mc.field_71442_b.func_187098_a(0, (slot < 9) ? (slot + 36) : slot, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil.mc.field_71439_g);
        InventoryUtil.mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil.mc.field_71439_g);
        for (int i = 0; i < 45; ++i) {
            if (InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i).func_190926_b()) {
                returnSlot = i;
                break;
            }
        }
        if (returnSlot != -1) {
            InventoryUtil.mc.field_71442_b.func_187098_a(0, (returnSlot < 9) ? (returnSlot + 36) : returnSlot, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil.mc.field_71439_g);
        }
    }
    
    public static int getAnyBlockInHotbar() {
        for (int i = 0; i < 9; ++i) {
            final Item item = InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
            if (item instanceof ItemBlock) {
                return i;
            }
        }
        return -1;
    }
    
    public static void switchToSlot(final int slot) {
        if (slot != -1 && InventoryUtil.mc.field_71439_g.field_71071_by.field_70461_c != slot) {
            InventoryUtil.mc.field_71439_g.field_71071_by.field_70461_c = slot;
        }
    }
    
    public static void switchToSlot(final Item item) {
        if (getHotbarItemSlot(item) != -1 && InventoryUtil.mc.field_71439_g.field_71071_by.field_70461_c != getHotbarItemSlot(item)) {
            InventoryUtil.mc.field_71439_g.field_71071_by.field_70461_c = getHotbarItemSlot(item);
        }
    }
    
    public static void switchToSlotGhost(final int slot) {
        if (slot != -1 && InventoryUtil.mc.field_71439_g.field_71071_by.field_70461_c != slot) {
            InventoryUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(slot));
        }
    }
    
    public static void switchToSlotGhost(final Item item) {
        if (getHotbarItemSlot(item) != -1 && InventoryUtil.mc.field_71439_g.field_71071_by.field_70461_c != getHotbarItemSlot(item)) {
            switchToSlotGhost(getHotbarItemSlot(item));
        }
    }
    
    public static int getHotbarItemSlot(final Item item) {
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean getHeldItem(final Item item) {
        return InventoryUtil.mc.field_71439_g.func_184614_ca().func_77973_b().equals(item) || InventoryUtil.mc.field_71439_g.func_184592_cb().func_77973_b().equals(item);
    }
    
    public static int getInventoryItemSlot(final Item item, final boolean hotbar) {
        for (int i = hotbar ? 9 : 0; i < 45; ++i) {
            if (InventoryUtil.mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == item) {
                return i;
            }
        }
        return -1;
    }
}
