// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import mod.hitlerhax.container.HtlrInventory;
import net.minecraft.enchantment.Enchantment;
import java.util.Map;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.EnumHand;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class AutoFish extends Module
{
    long recastTimer;
    long reelTimer;
    long timeSinceCast;
    final BooleanSetting findRod;
    final BooleanSetting autoRepair;
    final IntSetting recastDelay;
    final IntSetting reelDelay;
    
    public AutoFish() {
        super("AutoFish", "Fishes Automatically", Category.UTILITIES);
        this.recastTimer = 0L;
        this.reelTimer = 0L;
        this.timeSinceCast = 0L;
        this.findRod = new BooleanSetting("Hotbar Rod Finder", this, true);
        this.autoRepair = new BooleanSetting("Offhand Mending Repair", this, true);
        this.recastDelay = new IntSetting("Recast Delay (ms)", this, 5000);
        this.reelDelay = new IntSetting("Reel Delay (ms)", this, 300);
        this.addSetting(this.findRod);
        this.addSetting(this.autoRepair);
        this.addSetting(this.recastDelay);
        this.addSetting(this.reelDelay);
    }
    
    @Override
    public void onUpdate() {
        try {
            if (this.autoRepair.enabled && !AutoFish.mc.field_71439_g.func_184586_b(EnumHand.OFF_HAND).func_77951_h()) {
                for (int i = 0; i < 36; ++i) {
                    final ItemStack stack = AutoFish.mc.field_71439_g.field_71071_by.func_70301_a(i);
                    if (stack.func_77973_b().isDamaged(stack) && AutoFish.mc.field_71439_g.func_184614_ca() != stack) {
                        for (final Map.Entry<?, ?> me : EnchantmentHelper.func_82781_a(stack).entrySet()) {
                            if (((Enchantment)me.getKey()).func_77320_a().equalsIgnoreCase("enchantment.mending")) {
                                HtlrInventory.putInOffhand(stack);
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (NullPointerException ex) {}
        boolean reelIn = false;
        if (AutoFish.mc.field_71439_g.field_71104_cf != null && AutoFish.mc.field_71439_g.field_71104_cf.func_70089_S()) {
            final double x = AutoFish.mc.field_71439_g.field_71104_cf.field_70159_w;
            final double y = AutoFish.mc.field_71439_g.field_71104_cf.field_70181_x;
            final double z = AutoFish.mc.field_71439_g.field_71104_cf.field_70179_y;
            if (y < -0.075 && AutoFish.mc.field_71439_g.field_71104_cf.func_70090_H() && x == 0.0 && z == 0.0) {
                this.reelTimer = System.currentTimeMillis();
                return;
            }
        }
        if (System.currentTimeMillis() - this.reelDelay.value <= this.reelTimer && this.reelTimer != 0L) {
            reelIn = true;
            this.reelTimer = 0L;
        }
        if (reelIn && this.timeSinceCast > 0L && System.currentTimeMillis() - this.timeSinceCast > 3000L) {
            AutoFish.mc.field_71442_b.func_187101_a((EntityPlayer)AutoFish.mc.field_71439_g, (World)AutoFish.mc.field_71441_e, EnumHand.MAIN_HAND);
            return;
        }
        boolean cast;
        if (AutoFish.mc.field_71439_g.field_71104_cf == null) {
            cast = false;
            if (this.recastTimer == 0L) {
                this.recastTimer = System.currentTimeMillis();
                return;
            }
            if (System.currentTimeMillis() - this.recastDelay.value >= this.recastTimer) {
                cast = true;
                this.recastTimer = 0L;
            }
        }
        else {
            cast = false;
        }
        if (cast) {
            if (AutoFish.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemFishingRod) {
                AutoFish.mc.field_71442_b.func_187101_a((EntityPlayer)AutoFish.mc.field_71439_g, (World)AutoFish.mc.field_71441_e, EnumHand.MAIN_HAND);
                this.timeSinceCast = System.currentTimeMillis();
            }
            else if (this.findRod.enabled && !(AutoFish.mc.field_71439_g.field_71071_by.func_70448_g().func_77973_b() instanceof ItemFishingRod)) {
                for (int j = 0; j < 9; ++j) {
                    final ItemStack stack2 = AutoFish.mc.field_71439_g.field_71071_by.func_70301_a(j);
                    if (stack2.func_77973_b() instanceof ItemFishingRod) {
                        AutoFish.mc.field_71439_g.field_71071_by.field_70461_c = j;
                        break;
                    }
                }
            }
        }
    }
}
