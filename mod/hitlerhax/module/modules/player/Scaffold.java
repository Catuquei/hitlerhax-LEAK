// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.player;

import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.util.EnumHand;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import mod.hitlerhax.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import mod.hitlerhax.util.InventoryUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;
import mod.hitlerhax.util.MathUtil;
import net.minecraft.util.math.Vec3d;
import mod.hitlerhax.event.HtlrEventCancellable;
import mod.hitlerhax.Main;
import me.zero.alpine.listener.Listenable;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.util.math.BlockPos;
import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import net.minecraft.util.EnumFacing;
import mod.hitlerhax.util.BlockUtil;
import mod.hitlerhax.util.EntityUtil;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventMotionUpdate;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.util.Timer;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class Scaffold extends Module
{
    final BooleanSetting refill;
    final BooleanSetting rotation;
    final BooleanSetting prerotate;
    private final Timer timer;
    private final int[] blackList;
    @EventHandler
    private final Listener<HtlrEventMotionUpdate> player_move;
    
    public Scaffold() {
        super("Scaffold", "Places Blocks Below You", Category.PLAYER);
        this.refill = new BooleanSetting("refill", this, true);
        this.rotation = new BooleanSetting("Rotation", this, true);
        this.prerotate = new BooleanSetting("Pre-Rotate", this, true);
        this.timer = new Timer();
        this.blackList = new int[] { 145, 130, 12, 252, 54, 146, 122, 13, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 50 };
        BlockPos playerBlock;
        final Object o;
        this.player_move = new Listener<HtlrEventMotionUpdate>(event -> {
            if (Scaffold.mc.field_71441_e == null || event.stage == 0) {
                return;
            }
            else {
                if (!Scaffold.mc.field_71474_y.field_74314_A.func_151470_d()) {
                    this.timer.reset();
                }
                playerBlock = EntityUtil.getPlayerPosWithEntity();
                if (BlockUtil.isScaffoldPos(((BlockPos)o).func_177982_a(0, -1, 0))) {
                    if (BlockUtil.isValidBlock(playerBlock.func_177982_a(0, -2, 0))) {
                        this.place(playerBlock.func_177982_a(0, -1, 0), EnumFacing.UP, event);
                    }
                    else if (BlockUtil.isValidBlock(playerBlock.func_177982_a(-1, -1, 0))) {
                        this.place(playerBlock.func_177982_a(0, -1, 0), EnumFacing.EAST, event);
                    }
                    else if (BlockUtil.isValidBlock(playerBlock.func_177982_a(1, -1, 0))) {
                        this.place(playerBlock.func_177982_a(0, -1, 0), EnumFacing.WEST, event);
                    }
                    else if (BlockUtil.isValidBlock(playerBlock.func_177982_a(0, -1, -1))) {
                        this.place(playerBlock.func_177982_a(0, -1, 0), EnumFacing.SOUTH, event);
                    }
                    else if (BlockUtil.isValidBlock(playerBlock.func_177982_a(0, -1, 1))) {
                        this.place(playerBlock.func_177982_a(0, -1, 0), EnumFacing.NORTH, event);
                    }
                    else if (BlockUtil.isValidBlock(playerBlock.func_177982_a(1, -1, 1))) {
                        if (BlockUtil.isValidBlock(playerBlock.func_177982_a(0, -1, 1))) {
                            this.place(playerBlock.func_177982_a(0, -1, 1), EnumFacing.NORTH, event);
                        }
                        this.place(playerBlock.func_177982_a(1, -1, 1), EnumFacing.EAST, event);
                    }
                    else if (BlockUtil.isValidBlock(playerBlock.func_177982_a(-1, -1, 1))) {
                        if (BlockUtil.isValidBlock(playerBlock.func_177982_a(-1, -1, 0))) {
                            this.place(playerBlock.func_177982_a(0, -1, 1), EnumFacing.WEST, event);
                        }
                        this.place(playerBlock.func_177982_a(-1, -1, 1), EnumFacing.SOUTH, event);
                    }
                    else if (BlockUtil.isValidBlock(playerBlock.func_177982_a(1, -1, 1))) {
                        if (BlockUtil.isValidBlock(playerBlock.func_177982_a(0, -1, 1))) {
                            this.place(playerBlock.func_177982_a(0, -1, 1), EnumFacing.SOUTH, event);
                        }
                        this.place(playerBlock.func_177982_a(1, -1, 1), EnumFacing.WEST, event);
                    }
                    else if (BlockUtil.isValidBlock(playerBlock.func_177982_a(1, -1, 1))) {
                        if (BlockUtil.isValidBlock(playerBlock.func_177982_a(0, -1, 1))) {
                            this.place(playerBlock.func_177982_a(0, -1, 1), EnumFacing.EAST, event);
                        }
                        this.place(playerBlock.func_177982_a(1, -1, 1), EnumFacing.NORTH, event);
                    }
                }
                return;
            }
        }, (Predicate<HtlrEventMotionUpdate>[])new Predicate[0]);
        this.addSetting(this.rotation);
        this.addSetting(this.refill);
        this.addSetting(this.prerotate);
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        HtlrEventBus.EVENT_BUS.subscribe(this);
        Main.config.Save();
        this.timer.reset();
    }
    
    public void place(final BlockPos posI, final EnumFacing face, final HtlrEventMotionUpdate event) {
        BlockPos pos = posI;
        if (face == EnumFacing.UP) {
            pos = pos.func_177982_a(0, -1, 0);
        }
        else if (face == EnumFacing.NORTH) {
            pos = pos.func_177982_a(0, 0, 1);
        }
        else if (face == EnumFacing.SOUTH) {
            pos = pos.func_177982_a(0, 0, -1);
        }
        else if (face == EnumFacing.EAST) {
            pos = pos.func_177982_a(-1, 0, 0);
        }
        else if (face == EnumFacing.WEST) {
            pos = pos.func_177982_a(1, 0, 0);
        }
        if (event.get_era() == HtlrEventCancellable.Era.EVENT_PRE && this.rotation.enabled && this.prerotate.enabled) {
            final float[] angle = MathUtil.calcAngle(Scaffold.mc.field_71439_g.func_174824_e(Scaffold.mc.func_184121_ak()), new Vec3d((double)(pos.func_177958_n() + 0.5f), (double)(pos.func_177956_o() - 0.5f), (double)(pos.func_177952_p() + 0.5f)));
            Scaffold.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(angle[0], (float)MathHelper.func_180184_b((int)angle[1], 360), Scaffold.mc.field_71439_g.field_70122_E));
        }
        final int oldSlot = Scaffold.mc.field_71439_g.field_71071_by.field_70461_c;
        int newSlot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = Scaffold.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (!InventoryUtil.isItemStackNull(stack) && stack.func_77973_b() instanceof ItemBlock && Block.func_149634_a(stack.func_77973_b()).func_176223_P().func_185913_b()) {
                newSlot = i;
                break;
            }
        }
        if (newSlot == -1) {
            return;
        }
        boolean crouched = false;
        if (!Scaffold.mc.field_71439_g.func_70093_af() && WorldUtil.RIGHTCLICKABLE_BLOCKS.contains(Scaffold.mc.field_71441_e.func_180495_p(pos).func_177230_c())) {
            Scaffold.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)Scaffold.mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
            crouched = true;
        }
        if (!(Scaffold.mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemBlock)) {
            Scaffold.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(newSlot));
            Scaffold.mc.field_71439_g.field_71071_by.field_70461_c = newSlot;
            Scaffold.mc.field_71442_b.func_78765_e();
        }
        if (Scaffold.mc.field_71474_y.field_74314_A.func_151470_d()) {
            final EntityPlayerSP field_71439_g = Scaffold.mc.field_71439_g;
            field_71439_g.field_70159_w *= 0.3;
            final EntityPlayerSP field_71439_g2 = Scaffold.mc.field_71439_g;
            field_71439_g2.field_70179_y *= 0.3;
            Scaffold.mc.field_71439_g.func_70664_aZ();
            if (this.timer.getPassedMillis(1500L)) {
                Scaffold.mc.field_71439_g.field_70181_x = -0.28;
                this.timer.reset();
            }
        }
        if (this.rotation.enabled && !this.prerotate.enabled) {
            final float[] angle2 = MathUtil.calcAngle(Scaffold.mc.field_71439_g.func_174824_e(Scaffold.mc.func_184121_ak()), new Vec3d((double)(pos.func_177958_n() + 0.5f), (double)(pos.func_177956_o() - 0.5f), (double)(pos.func_177952_p() + 0.5f)));
            Scaffold.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(angle2[0], (float)MathHelper.func_180184_b((int)angle2[1], 360), Scaffold.mc.field_71439_g.field_70122_E));
        }
        Scaffold.mc.field_71442_b.func_187099_a(Scaffold.mc.field_71439_g, Scaffold.mc.field_71441_e, pos, face, new Vec3d(0.5, 0.5, 0.5), EnumHand.MAIN_HAND);
        Scaffold.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        Scaffold.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(oldSlot));
        Scaffold.mc.field_71439_g.field_71071_by.field_70461_c = oldSlot;
        Scaffold.mc.field_71442_b.func_78765_e();
        final double[] dir = MathUtil.directionSpeed(1.0);
        if (crouched) {
            Scaffold.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)Scaffold.mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        else {
            final Vec3d block = this.getFirstBlock(dir);
            if (this.refill.isEnabled() && block == null) {
                final int slot = this.findStackHotbar();
                if (slot != -1) {
                    Scaffold.mc.field_71439_g.field_71071_by.field_70461_c = slot;
                    Scaffold.mc.field_71442_b.func_78765_e();
                }
                else {
                    final int invSlot = this.findStackInventory();
                    if (invSlot != -1) {
                        final int empty = this.findEmptyhotbar();
                        Scaffold.mc.field_71442_b.func_187098_a(Scaffold.mc.field_71439_g.field_71069_bz.field_75152_c, invSlot, (empty == -1) ? Scaffold.mc.field_71439_g.field_71071_by.field_70461_c : empty, ClickType.SWAP, (EntityPlayer)Scaffold.mc.field_71439_g);
                        Scaffold.mc.field_71442_b.func_78765_e();
                        Scaffold.mc.field_71439_g.func_70016_h(0.0, 0.0, 0.0);
                    }
                }
            }
        }
    }
    
    private int getBlockCount() {
        int count = 0;
        if (Scaffold.mc.field_71439_g == null) {
            return count;
        }
        for (int i = 0; i < 36; ++i) {
            final ItemStack stack = Scaffold.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (this.canPlace(stack) && stack.func_77973_b() instanceof ItemBlock) {
                count += stack.func_190916_E();
            }
        }
        return count;
    }
    
    private Vec3d getFirstBlock(final double[] dir) {
        final Vec3d pos = new Vec3d(Scaffold.mc.field_71439_g.field_70165_t, Scaffold.mc.field_71439_g.field_70163_u - 1.0, Scaffold.mc.field_71439_g.field_70161_v);
        final Vec3d dirpos = new Vec3d(Scaffold.mc.field_71439_g.field_70165_t + dir[0], Scaffold.mc.field_71439_g.field_70163_u - 1.0, Scaffold.mc.field_71439_g.field_70161_v + dir[1]);
        if (Scaffold.mc.field_71441_e.func_180495_p(new BlockPos(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c)).func_177230_c() == Blocks.field_150350_a) {
            return pos;
        }
        if (Scaffold.mc.field_71441_e.func_180495_p(new BlockPos(dirpos.field_72450_a, dirpos.field_72448_b, dirpos.field_72449_c)).func_177230_c() != Blocks.field_150350_a) {
            return null;
        }
        if (Scaffold.mc.field_71441_e.func_180495_p(new BlockPos(pos.field_72450_a, dirpos.field_72448_b, dirpos.field_72449_c)).func_177230_c() == Blocks.field_150350_a && Scaffold.mc.field_71441_e.func_180495_p(new BlockPos(dirpos.field_72450_a, dirpos.field_72448_b, pos.field_72449_c)).func_177230_c() == Blocks.field_150350_a) {
            return new Vec3d(dirpos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
        }
        return dirpos;
    }
    
    private int findEmptyhotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = Scaffold.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (stack.func_77973_b() == Items.field_190931_a) {
                return i;
            }
        }
        return -1;
    }
    
    private int findStackInventory() {
        for (int i = 9; i < 36; ++i) {
            final ItemStack stack = Scaffold.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (this.canPlace(stack) && stack.func_77973_b() instanceof ItemBlock) {
                return i;
            }
        }
        return -1;
    }
    
    private int findStackHotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = Scaffold.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (this.canPlace(stack) && stack.func_77973_b() instanceof ItemBlock) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean canPlace(final ItemStack stack) {
        for (final int i : this.blackList) {
            if (Item.func_150891_b(stack.func_77973_b()) == i) {
                return false;
            }
        }
        return true;
    }
}
