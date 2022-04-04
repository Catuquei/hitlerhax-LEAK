// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.player;

import net.minecraft.block.Block;
import mod.hitlerhax.util.InventoryUtil;
import net.minecraft.init.Items;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class FastPlace extends Module
{
    final IntSetting delay;
    final BooleanSetting blocks;
    final BooleanSetting crystal;
    final BooleanSetting fireworks;
    final BooleanSetting spawnEggs;
    
    public FastPlace() {
        super("FastPlace", "Allows you to place blocks & crystals faster", Category.PLAYER);
        this.delay = new IntSetting("Delay", this, 1);
        this.blocks = new BooleanSetting("Blocks", this, false);
        this.crystal = new BooleanSetting("Crystals", this, true);
        this.fireworks = new BooleanSetting("Fireworks", this, false);
        this.spawnEggs = new BooleanSetting("Spawn Eggs", this, false);
        this.addSetting(this.delay);
        this.addSetting(this.blocks);
        this.addSetting(this.crystal);
        this.addSetting(this.fireworks);
        this.addSetting(this.spawnEggs);
    }
    
    @Override
    public void onUpdate() {
        if ((InventoryUtil.getHeldItem(Items.field_185158_cP) && this.crystal.isEnabled()) || (InventoryUtil.getHeldItem(Items.field_151152_bP) && this.fireworks.isEnabled()) || (InventoryUtil.getHeldItem(Items.field_151063_bx) && this.spawnEggs.isEnabled())) {
            FastPlace.mc.field_71467_ac = this.delay.value;
        }
        if (Block.func_149634_a(FastPlace.mc.field_71439_g.func_184614_ca().func_77973_b()).func_176223_P().func_185913_b() && this.blocks.isEnabled()) {
            FastPlace.mc.field_71467_ac = this.delay.value;
        }
    }
}
