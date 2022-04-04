// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.Block;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import mod.hitlerhax.util.BlockUtil;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockAir;
import java.util.Iterator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import net.minecraft.util.math.BlockPos;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class SelfFill extends Module
{
    public final ModeSetting mode;
    public final BooleanSetting autoSwitch;
    public final BooleanSetting rotations;
    public final FloatSetting offset;
    public final IntSetting rubberbandDelay;
    public final BooleanSetting autoDisable;
    private final double[] jump;
    private boolean placed;
    private boolean jumped;
    private BlockPos startPos;
    private int ticks;
    private int startSlot;
    
    public SelfFill() {
        super("SelfFill", "Puts you in a hole", Category.COMBAT);
        this.mode = new ModeSetting("mode", this, "instant", new String[] { "instant", "jump", "tp" });
        this.autoSwitch = new BooleanSetting("autoSwitch", this, true);
        this.rotations = new BooleanSetting("rotate", this, false);
        this.offset = new FloatSetting("offset", this, 4.0f);
        this.rubberbandDelay = new IntSetting("delay", this, 13);
        this.autoDisable = new BooleanSetting("autoDisable", this, true);
        this.jump = new double[] { 0.41999998688698, 0.7531999805211997, 1.00133597911214, 1.16610926093821 };
        this.addSetting(this.mode);
        this.addSetting(this.autoSwitch);
        this.addSetting(this.rotations);
        this.addSetting(this.offset);
        this.addSetting(this.rubberbandDelay);
        this.addSetting(this.autoDisable);
    }
    
    public void onEnable() {
        this.startPos = new BlockPos(SelfFill.mc.field_71439_g.field_70165_t, SelfFill.mc.field_71439_g.field_70163_u, SelfFill.mc.field_71439_g.field_70161_v);
        this.startSlot = SelfFill.mc.field_71439_g.field_71071_by.field_70461_c;
        if (this.intersectsWithEntity(this.startPos) || this.findBlockSlot() == -1) {
            this.onDisable();
            return;
        }
        if (this.autoSwitch.isEnabled()) {
            SelfFill.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.findBlockSlot()));
            SelfFill.mc.field_71442_b.func_78765_e();
        }
    }
    
    public void onDisable() {
        this.placed = false;
        this.jumped = false;
        this.ticks = 0;
    }
    
    @Override
    public void onUpdate() {
        if (SelfFill.mc.field_71439_g == null || SelfFill.mc.field_71441_e == null) {
            return;
        }
        if (!this.mode.is("instant")) {
            ++this.ticks;
            if (!this.jumped) {
                SelfFill.mc.field_71439_g.func_70664_aZ();
                this.jumped = true;
                if (this.ticks == this.rubberbandDelay.getValue() && !this.placed) {
                    this.placeBlock(this.startPos, this.rotations.isEnabled(), false, true);
                    this.placed = true;
                    if (this.mode.is("jump")) {
                        SelfFill.mc.field_71439_g.func_70664_aZ();
                    }
                    else {
                        SelfFill.mc.field_71439_g.field_70181_x = this.offset.getValue();
                    }
                    if (this.autoSwitch.isEnabled()) {
                        SelfFill.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.startSlot));
                        SelfFill.mc.field_71439_g.field_71071_by.field_70461_c = this.startSlot;
                        SelfFill.mc.field_71442_b.func_78765_e();
                    }
                    SelfFill.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)SelfFill.mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
                    if (this.autoDisable.isEnabled()) {
                        this.setToggled(false);
                    }
                }
            }
        }
        else {
            for (int i = 0; i < 4; ++i) {
                SelfFill.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(SelfFill.mc.field_71439_g.field_70165_t, SelfFill.mc.field_71439_g.field_70163_u + this.jump[i], SelfFill.mc.field_71439_g.field_70161_v, true));
            }
            this.placeBlock(this.startPos, this.rotations.isEnabled(), true, false);
            SelfFill.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(SelfFill.mc.field_71439_g.field_70165_t, SelfFill.mc.field_71439_g.field_70163_u + this.offset.getValue(), SelfFill.mc.field_71439_g.field_70161_v, false));
            if (this.autoSwitch.isEnabled()) {
                SelfFill.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.startSlot));
                SelfFill.mc.field_71439_g.field_71071_by.field_70461_c = this.startSlot;
                SelfFill.mc.field_71442_b.func_78765_e();
            }
            SelfFill.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)SelfFill.mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
            if (this.autoDisable.isEnabled()) {
                this.setToggled(false);
            }
        }
    }
    
    private boolean intersectsWithEntity(final BlockPos pos) {
        for (final Entity entity : SelfFill.mc.field_71441_e.field_72996_f) {
            if (entity.equals((Object)SelfFill.mc.field_71439_g)) {
                continue;
            }
            if (entity instanceof EntityItem) {
                continue;
            }
            if (new AxisAlignedBB(pos).func_72326_a(entity.func_174813_aQ())) {
                return true;
            }
        }
        return false;
    }
    
    private void placeBlock(final BlockPos pos, final boolean rotate, final boolean packet, boolean isSneaking) {
        final Block block = SelfFill.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return;
        }
        final EnumFacing side = BlockUtil.getPlaceableSide(pos);
        if (side == null) {
            return;
        }
        final BlockPos neighbour = pos.func_177972_a(side);
        final EnumFacing opposite = side.func_176734_d();
        if (BlockUtil.canBeClicked(neighbour)) {
            return;
        }
        final Vec3d hitVec = new Vec3d((Vec3i)neighbour).func_72441_c(0.5, 0.5, 0.5).func_178787_e(new Vec3d(opposite.func_176730_m()).func_186678_a(0.5));
        final Block neighbourBlock = SelfFill.mc.field_71441_e.func_180495_p(neighbour).func_177230_c();
        final int obsidianSlot = this.findBlockSlot();
        if (SelfFill.mc.field_71439_g.field_71071_by.field_70461_c != obsidianSlot && obsidianSlot != -1) {
            SelfFill.mc.field_71439_g.field_71071_by.field_70461_c = obsidianSlot;
        }
        if ((!isSneaking && BlockUtil.blackList.contains(neighbourBlock)) || BlockUtil.shulkerList.contains(neighbourBlock)) {
            SelfFill.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)SelfFill.mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
            isSneaking = true;
        }
        if (rotate) {
            BlockUtil.faceVectorPacketInstant(hitVec);
        }
        rightClickBlock(neighbour, hitVec, EnumHand.MAIN_HAND, opposite, true);
        SelfFill.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        SelfFill.mc.field_71467_ac = 4;
    }
    
    private int findBlockSlot() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = SelfFill.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (stack != ItemStack.field_190927_a) {
                if (stack.func_77973_b() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stack.func_77973_b()).func_179223_d();
                    if (block instanceof BlockObsidian || block instanceof BlockEnderChest) {
                        slot = i;
                        break;
                    }
                }
            }
        }
        return slot;
    }
    
    public static void rightClickBlock(final BlockPos pos, final Vec3d vec, final EnumHand hand, final EnumFacing direction, final boolean packet) {
        if (packet) {
            final float f = (float)(vec.field_72450_a - pos.func_177958_n());
            final float f2 = (float)(vec.field_72448_b - pos.func_177956_o());
            final float f3 = (float)(vec.field_72449_c - pos.func_177952_p());
            SelfFill.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f2, f3));
        }
        else {
            SelfFill.mc.field_71442_b.func_187099_a(SelfFill.mc.field_71439_g, SelfFill.mc.field_71441_e, pos, direction, vec, hand);
        }
        SelfFill.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        SelfFill.mc.field_71467_ac = 4;
    }
}
