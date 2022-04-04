// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.util.EnumFacing;
import mod.hitlerhax.util.BlockInteractionUtil;
import net.minecraft.util.math.BlockPos;
import mod.hitlerhax.container.HtlrInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemPickaxe;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.ui.guiitems.HtlrButton;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class ConcreteBot extends Module
{
    final IntSetting x;
    final IntSetting y;
    final IntSetting z;
    final HtlrButton setPos;
    private boolean breakBlock;
    
    @Override
    public void actionPerformed(final HtlrButton b) {
        if (b.id == 10001) {
            this.x.value = ConcreteBot.mc.field_71439_g.func_180425_c().func_177958_n();
            this.y.value = ConcreteBot.mc.field_71439_g.func_180425_c().func_177956_o();
            this.z.value = ConcreteBot.mc.field_71439_g.func_180425_c().func_177952_p();
        }
    }
    
    public ConcreteBot() {
        super("ConcreteBot", "Turns Powder Into Concrete", Category.UTILITIES);
        this.x = new IntSetting("X", this, 0);
        this.y = new IntSetting("Y", this, 0);
        this.z = new IntSetting("Z", this, 0);
        this.setPos = new HtlrButton(10001, 0, 0, 0, FontUtils.getFontHeight() + 2, "Set Current Position");
        this.addSetting(this.x);
        this.addSetting(this.y);
        this.addSetting(this.z);
        this.addButton(this.setPos);
    }
    
    @Override
    public void onUpdate() {
        if (!(ConcreteBot.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemPickaxe)) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack stack = ConcreteBot.mc.field_71439_g.field_71071_by.func_70301_a(i);
                if (stack.func_77973_b() instanceof ItemPickaxe) {
                    ConcreteBot.mc.field_71439_g.field_71071_by.field_70461_c = i;
                    break;
                }
            }
        }
        if (!(ConcreteBot.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemPickaxe)) {
            this.toggle();
            return;
        }
        if (!ConcreteBot.mc.field_71439_g.func_184586_b(EnumHand.OFF_HAND).func_82833_r().equals(new ItemStack(Item.func_150898_a(Blocks.field_192444_dS)).func_82833_r())) {
            for (int i = 0; i < 36; ++i) {
                final ItemStack stack = ConcreteBot.mc.field_71439_g.field_71071_by.func_70301_a(i);
                final Item block = Item.func_150898_a(Blocks.field_192444_dS);
                if (stack.func_82833_r().equals(new ItemStack(block).func_82833_r())) {
                    HtlrInventory.putInOffhand(stack);
                    break;
                }
            }
        }
        if (!ConcreteBot.mc.field_71439_g.func_184586_b(EnumHand.OFF_HAND).func_82833_r().equals(new ItemStack(Item.func_150898_a(Blocks.field_192444_dS)).func_82833_r())) {
            this.toggle();
            return;
        }
        int stage;
        if (ConcreteBot.mc.field_71441_e.func_180495_p(new BlockPos(this.x.value + 0.5, this.y.value + 0.5, this.z.value + 0.5)).func_177230_c().equals(Blocks.field_192443_dR)) {
            stage = 0;
        }
        else {
            stage = 1;
        }
        if (stage == 0) {
            final float[] rotation = BlockInteractionUtil.getRotationsForPosition(this.x.value + 0.5, this.y.value + 0.5, this.z.value + 0.5);
            ConcreteBot.mc.field_71439_g.field_70177_z = rotation[0];
            ConcreteBot.mc.field_71439_g.field_70125_A = rotation[1];
            ConcreteBot.mc.field_71439_g.field_70759_as = rotation[0];
            this.breakBlock = true;
            return;
        }
        if (stage == 1) {
            final float[] rotation = BlockInteractionUtil.getRotationsForPosition(this.x.value + 0.5, this.y.value + 0.5, this.z.value + 0.5);
            ConcreteBot.mc.field_71439_g.field_70177_z = rotation[0];
            ConcreteBot.mc.field_71439_g.field_70125_A = rotation[1];
            ConcreteBot.mc.field_71439_g.field_70759_as = rotation[0];
            ConcreteBot.mc.field_71442_b.func_187099_a(ConcreteBot.mc.field_71439_g, ConcreteBot.mc.field_71441_e, new BlockPos(this.x.value + 0.5, this.y.value + 0.5, this.z.value + 0.5), EnumFacing.DOWN, ConcreteBot.mc.field_71476_x.field_72307_f, EnumHand.OFF_HAND);
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        if (event.type != TickEvent.Type.CLIENT) {
            return;
        }
        if (ConcreteBot.mc.field_71441_e == null || ConcreteBot.mc.field_71439_g == null) {
            this.toggle();
            return;
        }
        if (this.breakBlock && ConcreteBot.mc.field_71441_e.func_180495_p(new BlockPos(this.x.value + 0.5, this.y.value + 0.5, this.z.value + 0.5)).func_177230_c().equals(Blocks.field_192443_dR)) {
            final RayTraceResult trace = ConcreteBot.mc.field_71476_x;
            final boolean isBlockTrace = trace != null && trace.field_72313_a == RayTraceResult.Type.BLOCK;
            if (isBlockTrace && ConcreteBot.mc.field_71442_b.func_180512_c(trace.func_178782_a(), trace.field_178784_b)) {
                ConcreteBot.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
            }
        }
    }
}
