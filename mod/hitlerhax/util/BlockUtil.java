// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util;

import java.util.Arrays;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.math.Vec3i;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import mod.hitlerhax.misc.RotationPriority;
import mod.hitlerhax.misc.Rotation;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import java.util.List;

public class BlockUtil implements Globals
{
    public static final List<Block> blackList;
    public static final List<Block> shulkerList;
    
    public static boolean isScaffoldPos(final BlockPos pos) {
        return BlockUtil.mc.field_71441_e.func_175623_d(pos) || BlockUtil.mc.field_71441_e.func_180495_p(pos).func_177230_c() == Blocks.field_150431_aC || BlockUtil.mc.field_71441_e.func_180495_p(pos).func_177230_c() == Blocks.field_150329_H || BlockUtil.mc.field_71441_e.func_180495_p(pos).func_177230_c() instanceof BlockLiquid;
    }
    
    public static boolean isValidBlock(final BlockPos pos) {
        final Block block = BlockUtil.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        return !(block instanceof BlockLiquid) && block.func_149688_o((IBlockState)null) != Material.field_151579_a;
    }
    
    public static IBlockState getState(final BlockPos pos) {
        return BlockUtil.mc.field_71441_e.func_180495_p(pos);
    }
    
    public static Block getBlock(final BlockPos pos) {
        return getState(pos).func_177230_c();
    }
    
    public static Block getBlock(final double x, final double y, final double z) {
        return BlockUtil.mc.field_71441_e.func_180495_p(new BlockPos(x, y, z)).func_177230_c();
    }
    
    public static EnumFacing getPlaceableSide(final BlockPos pos) {
        for (final EnumFacing side : EnumFacing.values()) {
            final BlockPos neighbour = pos.func_177972_a(side);
            if (BlockUtil.mc.field_71441_e.func_180495_p(neighbour).func_177230_c().func_176209_a(BlockUtil.mc.field_71441_e.func_180495_p(neighbour), false)) {
                final IBlockState blockState = BlockUtil.mc.field_71441_e.func_180495_p(neighbour);
                if (!blockState.func_185904_a().func_76222_j()) {
                    return side;
                }
            }
        }
        return null;
    }
    
    public static boolean canBeClicked(final BlockPos pos) {
        return !getBlock(pos).func_176209_a(getState(pos), false);
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec) {
        final float[] rotations = getNeededRotations(vec);
        BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(rotations[0], rotations[1], BlockUtil.mc.field_71439_g.field_70122_E));
    }
    
    private static float[] getNeededRotations(final Vec3d vec) {
        final Vec3d eyesPos = getEyesPos();
        final double diffX = vec.field_72450_a - eyesPos.field_72450_a;
        final double diffY = vec.field_72448_b - eyesPos.field_72448_b;
        final double diffZ = vec.field_72449_c - eyesPos.field_72449_c;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[] { BlockUtil.mc.field_71439_g.field_70177_z + MathHelper.func_76142_g(yaw - BlockUtil.mc.field_71439_g.field_70177_z), BlockUtil.mc.field_71439_g.field_70125_A + MathHelper.func_76142_g(pitch - BlockUtil.mc.field_71439_g.field_70125_A) };
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(BlockUtil.mc.field_71439_g.field_70165_t, BlockUtil.mc.field_71439_g.field_70163_u + BlockUtil.mc.field_71439_g.func_70047_e(), BlockUtil.mc.field_71439_g.field_70161_v);
    }
    
    public static BlockResistance getBlockResistance(final BlockPos block) {
        if (BlockUtil.mc.field_71441_e.func_175623_d(block)) {
            return BlockResistance.Blank;
        }
        if (BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().func_176195_g(BlockUtil.mc.field_71441_e.func_180495_p(block), (World)BlockUtil.mc.field_71441_e, block) != -1.0f && !BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().equals(Blocks.field_150343_Z) && !BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().equals(Blocks.field_150467_bQ) && !BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().equals(Blocks.field_150381_bn) && !BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().equals(Blocks.field_150477_bB)) {
            return BlockResistance.Breakable;
        }
        if (BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().equals(Blocks.field_150343_Z) || BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().equals(Blocks.field_150467_bQ) || BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().equals(Blocks.field_150381_bn) || BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().equals(Blocks.field_150477_bB)) {
            return BlockResistance.Resistant;
        }
        if (BlockUtil.mc.field_71441_e.func_180495_p(block).func_177230_c().equals(Blocks.field_150357_h)) {
            return BlockResistance.Unbreakable;
        }
        return null;
    }
    
    public static void placeBlock(final BlockPos pos, final boolean rotate, final boolean strict, final boolean raytrace, final boolean packet, final boolean swingArm, final boolean antiGlitch) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (getBlockResistance(pos.func_177972_a(enumFacing)) != BlockResistance.Blank && !EntityUtil.isIntercepted(pos)) {
                final Vec3d vec = new Vec3d(pos.func_177958_n() + 0.5 + enumFacing.func_82601_c() * 0.5, pos.func_177956_o() + 0.5 + enumFacing.func_96559_d() * 0.5, pos.func_177952_p() + 0.5 + enumFacing.func_82599_e() * 0.5);
                final float[] old = { BlockUtil.mc.field_71439_g.field_70177_z, BlockUtil.mc.field_71439_g.field_70125_A };
                if (strict) {
                    RotationUtil.rotationQueue.add(new Rotation((float)Math.toDegrees(Math.atan2(vec.field_72449_c - BlockUtil.mc.field_71439_g.field_70161_v, vec.field_72450_a - BlockUtil.mc.field_71439_g.field_70165_t)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(vec.field_72448_b - (BlockUtil.mc.field_71439_g.field_70163_u + BlockUtil.mc.field_71439_g.func_70047_e()), Math.sqrt((vec.field_72450_a - BlockUtil.mc.field_71439_g.field_70165_t) * (vec.field_72450_a - BlockUtil.mc.field_71439_g.field_70165_t) + (vec.field_72449_c - BlockUtil.mc.field_71439_g.field_70161_v) * (vec.field_72449_c - BlockUtil.mc.field_71439_g.field_70161_v))))), Rotation.RotationMode.Packet, RotationPriority.High));
                }
                if (rotate) {
                    BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(vec.field_72449_c - BlockUtil.mc.field_71439_g.field_70161_v, vec.field_72450_a - BlockUtil.mc.field_71439_g.field_70165_t)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(vec.field_72448_b - (BlockUtil.mc.field_71439_g.field_70163_u + BlockUtil.mc.field_71439_g.func_70047_e()), Math.sqrt((vec.field_72450_a - BlockUtil.mc.field_71439_g.field_70165_t) * (vec.field_72450_a - BlockUtil.mc.field_71439_g.field_70165_t) + (vec.field_72449_c - BlockUtil.mc.field_71439_g.field_70161_v) * (vec.field_72449_c - BlockUtil.mc.field_71439_g.field_70161_v))))), BlockUtil.mc.field_71439_g.field_70122_E));
                }
                BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
                if (packet) {
                    BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(pos, raytrace ? enumFacing : EnumFacing.UP, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                }
                else {
                    BlockUtil.mc.field_71442_b.func_187099_a(BlockUtil.mc.field_71439_g, BlockUtil.mc.field_71441_e, pos.func_177972_a(enumFacing), raytrace ? enumFacing.func_176734_d() : EnumFacing.UP, new Vec3d((Vec3i)pos), EnumHand.MAIN_HAND);
                }
                if (swingArm) {
                    BlockUtil.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
                }
                BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
                if (rotate) {
                    BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(old[0], old[1], BlockUtil.mc.field_71439_g.field_70122_E));
                }
                if (antiGlitch) {
                    BlockUtil.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos.func_177972_a(enumFacing), enumFacing.func_176734_d()));
                }
                return;
            }
        }
    }
    
    static {
        blackList = Arrays.asList(Blocks.field_150477_bB, (Block)Blocks.field_150486_ae, Blocks.field_150447_bR, Blocks.field_150462_ai, Blocks.field_150467_bQ, Blocks.field_150382_bo, (Block)Blocks.field_150438_bZ, Blocks.field_150409_cd, Blocks.field_150367_z);
        shulkerList = Arrays.asList(Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA);
    }
    
    public enum BlockResistance
    {
        Blank, 
        Breakable, 
        Resistant, 
        Unbreakable;
    }
}
