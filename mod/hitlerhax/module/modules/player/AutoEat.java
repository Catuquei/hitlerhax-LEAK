// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.player;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemFood;
import mod.hitlerhax.util.PlayerUtil;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class AutoEat extends Module
{
    final IntSetting hunger;
    final IntSetting health;
    private boolean m_WasEating;
    
    public AutoEat() {
        super("AutoEat", "Eats Automatically", Category.PLAYER);
        this.hunger = new IntSetting("Hunger", this, 17);
        this.health = new IntSetting("Health", this, 16);
        this.m_WasEating = false;
        this.addSetting(this.hunger);
        this.addSetting(this.health);
    }
    
    public void onDisable() {
        super.onDisable();
        if (this.m_WasEating) {
            this.m_WasEating = false;
            AutoEat.mc.field_71474_y.field_74313_G.field_74513_e = false;
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.hunger.value > 20) {
            this.hunger.value = 0;
        }
        if ((!PlayerUtil.IsEating() && this.hunger.value >= AutoEat.mc.field_71439_g.func_71024_bL().func_75116_a()) || (!PlayerUtil.IsEating() && this.health.value <= AutoEat.mc.field_71439_g.func_110143_aJ())) {
            boolean l_CanEat = false;
            ItemStack l_Stack = null;
            for (int l_I = 0; l_I < 9; ++l_I) {
                l_Stack = AutoEat.mc.field_71439_g.field_71071_by.func_70301_a(l_I);
                if (!AutoEat.mc.field_71439_g.field_71071_by.func_70301_a(l_I).func_190926_b()) {
                    if (l_Stack.func_77973_b() instanceof ItemFood) {
                        l_CanEat = true;
                        AutoEat.mc.field_71439_g.field_71071_by.field_70461_c = l_I;
                        AutoEat.mc.field_71442_b.func_78765_e();
                        break;
                    }
                }
            }
            if (l_CanEat && l_Stack.func_77973_b() instanceof ItemFood) {
                AutoEat.mc.field_71474_y.field_74313_G.field_74513_e = true;
                AutoEat.mc.field_71442_b.func_187101_a((EntityPlayer)AutoEat.mc.field_71439_g, (World)AutoEat.mc.field_71441_e, EnumHand.MAIN_HAND);
                AutoEat.mc.field_71442_b.func_78765_e();
                this.m_WasEating = true;
            }
        }
        if (this.m_WasEating) {
            this.m_WasEating = false;
        }
    }
}
