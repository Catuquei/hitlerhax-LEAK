// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class AutoArmor extends Module
{
    public AutoArmor() {
        super("AutoArmor", "Automatically replaces armor", Category.COMBAT);
    }
    
    @Override
    public void onUpdate() {
        if (AutoArmor.mc.field_71439_g.field_70173_aa % 2 == 0) {
            return;
        }
        if (AutoArmor.mc.field_71462_r instanceof GuiContainer && !(AutoArmor.mc.field_71462_r instanceof InventoryEffectRenderer)) {
            return;
        }
        final int[] bestArmorSlots = new int[4];
        final int[] bestArmorValues = new int[4];
        for (int armorType = 0; armorType < 4; ++armorType) {
            final ItemStack oldArmor = AutoArmor.mc.field_71439_g.field_71071_by.func_70440_f(armorType);
            if (oldArmor != null && oldArmor.func_77973_b() instanceof ItemArmor) {
                bestArmorValues[armorType] = ((ItemArmor)oldArmor.func_77973_b()).field_77879_b;
            }
            bestArmorSlots[armorType] = -1;
        }
        for (int slot = 0; slot < 36; ++slot) {
            final ItemStack stack = AutoArmor.mc.field_71439_g.field_71071_by.func_70301_a(slot);
            if (stack.func_190916_E() <= 1) {
                if (stack != null) {
                    if (stack.func_77973_b() instanceof ItemArmor) {
                        final ItemArmor armor = (ItemArmor)stack.func_77973_b();
                        final int armorType2 = armor.field_77881_a.ordinal() - 2;
                        if (armorType2 != 2 || !AutoArmor.mc.field_71439_g.field_71071_by.func_70440_f(armorType2).func_77973_b().equals(Items.field_185160_cR)) {
                            final int armorValue = armor.field_77879_b;
                            if (armorValue > bestArmorValues[armorType2]) {
                                bestArmorSlots[armorType2] = slot;
                                bestArmorValues[armorType2] = armorValue;
                            }
                        }
                    }
                }
            }
        }
        for (int armorType = 0; armorType < 4; ++armorType) {
            int slot2 = bestArmorSlots[armorType];
            if (slot2 != -1) {
                final ItemStack oldArmor2 = AutoArmor.mc.field_71439_g.field_71071_by.func_70440_f(armorType);
                if (oldArmor2 == null || oldArmor2 != ItemStack.field_190927_a || AutoArmor.mc.field_71439_g.field_71071_by.func_70447_i() != -1) {
                    if (slot2 < 9) {
                        slot2 += 36;
                    }
                    AutoArmor.mc.field_71442_b.func_187098_a(0, 8 - armorType, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.field_71439_g);
                    AutoArmor.mc.field_71442_b.func_187098_a(0, slot2, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.field_71439_g);
                    break;
                }
            }
        }
    }
}
