// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import net.minecraft.block.BlockLiquid;
import net.minecraft.util.math.MathHelper;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.material.Material;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class Anchor extends Module
{
    private int packets;
    private boolean jumped;
    private final double[] oneblockPositions;
    
    public Anchor() {
        super("Anchor", "sucks you in holes.", Category.COMBAT);
        this.oneblockPositions = new double[] { 0.42, 0.75 };
    }
    
    @Override
    public void onUpdate() {
        if (Anchor.mc.field_71441_e == null || Anchor.mc.field_71439_g == null) {
            return;
        }
        if (!Anchor.mc.field_71439_g.field_70122_E) {
            if (Anchor.mc.field_71474_y.field_74314_A.func_151470_d()) {
                this.jumped = true;
            }
        }
        else {
            this.jumped = false;
        }
        if (!this.jumped && Anchor.mc.field_71439_g.field_70143_R < 0.5 && this.isInHole() && Anchor.mc.field_71439_g.field_70163_u - this.getNearestBlockBelow() <= 1.125 && Anchor.mc.field_71439_g.field_70163_u - this.getNearestBlockBelow() <= 0.95 && !this.isOnLiquid() && !this.isInLiquid()) {
            if (!Anchor.mc.field_71439_g.field_70122_E) {
                ++this.packets;
            }
            if (!Anchor.mc.field_71439_g.field_70122_E && !Anchor.mc.field_71439_g.func_70055_a(Material.field_151586_h) && !Anchor.mc.field_71439_g.func_70055_a(Material.field_151587_i) && !Anchor.mc.field_71474_y.field_74314_A.func_151470_d() && !Anchor.mc.field_71439_g.func_70617_f_() && this.packets > 0) {
                final BlockPos blockPos = new BlockPos(Anchor.mc.field_71439_g.field_70165_t, Anchor.mc.field_71439_g.field_70163_u, Anchor.mc.field_71439_g.field_70161_v);
                for (final double position : this.oneblockPositions) {
                    Anchor.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position((double)(blockPos.func_177958_n() + 0.5f), Anchor.mc.field_71439_g.field_70163_u - position, (double)(blockPos.func_177952_p() + 0.5f), true));
                }
                Anchor.mc.field_71439_g.func_70107_b((double)(blockPos.func_177958_n() + 0.5f), this.getNearestBlockBelow() + 0.1, (double)(blockPos.func_177952_p() + 0.5f));
                this.packets = 0;
            }
        }
    }
    
    private boolean isInHole() {
        final BlockPos blockPos = new BlockPos(Anchor.mc.field_71439_g.field_70165_t, Anchor.mc.field_71439_g.field_70163_u, Anchor.mc.field_71439_g.field_70161_v);
        final IBlockState blockState = Anchor.mc.field_71441_e.func_180495_p(blockPos);
        return this.isBlockValid(blockState, blockPos);
    }
    
    private double getNearestBlockBelow() {
        for (double y = Anchor.mc.field_71439_g.field_70163_u; y > 0.0; y -= 0.001) {
            if (!(Anchor.mc.field_71441_e.func_180495_p(new BlockPos(Anchor.mc.field_71439_g.field_70165_t, y, Anchor.mc.field_71439_g.field_70161_v)).func_177230_c() instanceof BlockSlab) && Anchor.mc.field_71441_e.func_180495_p(new BlockPos(Anchor.mc.field_71439_g.field_70165_t, y, Anchor.mc.field_71439_g.field_70161_v)).func_177230_c().func_176223_P().func_185890_d((IBlockAccess)Anchor.mc.field_71441_e, new BlockPos(0, 0, 0)) != null) {
                return y;
            }
        }
        return -1.0;
    }
    
    private boolean isBlockValid(final IBlockState blockState, final BlockPos blockPos) {
        return blockState.func_177230_c() == Blocks.field_150350_a && Anchor.mc.field_71439_g.func_174818_b(blockPos) >= 1.0 && Anchor.mc.field_71441_e.func_180495_p(blockPos.func_177984_a()).func_177230_c() == Blocks.field_150350_a && Anchor.mc.field_71441_e.func_180495_p(blockPos.func_177981_b(2)).func_177230_c() == Blocks.field_150350_a && (this.isBedrockHole(blockPos) || this.isObbyHole(blockPos) || this.isBothHole(blockPos) || this.isElseHole(blockPos));
    }
    
    private boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] array2;
        final BlockPos[] array = array2 = new BlockPos[] { blockPos.func_177978_c(), blockPos.func_177968_d(), blockPos.func_177974_f(), blockPos.func_177976_e(), blockPos.func_177977_b() };
        for (final BlockPos touching : array2) {
            final IBlockState touchingState = Anchor.mc.field_71441_e.func_180495_p(touching);
            if (touchingState.func_177230_c() == Blocks.field_150350_a || touchingState.func_177230_c() != Blocks.field_150343_Z) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] array2;
        final BlockPos[] array = array2 = new BlockPos[] { blockPos.func_177978_c(), blockPos.func_177968_d(), blockPos.func_177974_f(), blockPos.func_177976_e(), blockPos.func_177977_b() };
        for (final BlockPos touching : array2) {
            final IBlockState touchingState = Anchor.mc.field_71441_e.func_180495_p(touching);
            if (touchingState.func_177230_c() == Blocks.field_150350_a || touchingState.func_177230_c() != Blocks.field_150357_h) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] array2;
        final BlockPos[] array = array2 = new BlockPos[] { blockPos.func_177978_c(), blockPos.func_177968_d(), blockPos.func_177974_f(), blockPos.func_177976_e(), blockPos.func_177977_b() };
        for (final BlockPos touching : array2) {
            final IBlockState touchingState = Anchor.mc.field_71441_e.func_180495_p(touching);
            if (touchingState.func_177230_c() == Blocks.field_150350_a || (touchingState.func_177230_c() != Blocks.field_150357_h && touchingState.func_177230_c() != Blocks.field_150343_Z)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isElseHole(final BlockPos blockPos) {
        final BlockPos[] array2;
        final BlockPos[] array = array2 = new BlockPos[] { blockPos.func_177978_c(), blockPos.func_177968_d(), blockPos.func_177974_f(), blockPos.func_177976_e(), blockPos.func_177977_b() };
        for (final BlockPos touching : array2) {
            final IBlockState touchingState = Anchor.mc.field_71441_e.func_180495_p(touching);
            if (touchingState.func_177230_c() == Blocks.field_150350_a || !touchingState.func_185913_b()) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isOnLiquid() {
        final double y = Anchor.mc.field_71439_g.field_70163_u - 0.03;
        for (int x = MathHelper.func_76128_c(Anchor.mc.field_71439_g.field_70165_t); x < MathHelper.func_76143_f(Anchor.mc.field_71439_g.field_70165_t); ++x) {
            for (int z = MathHelper.func_76128_c(Anchor.mc.field_71439_g.field_70161_v); z < MathHelper.func_76143_f(Anchor.mc.field_71439_g.field_70161_v); ++z) {
                final BlockPos pos = new BlockPos(x, MathHelper.func_76128_c(y), z);
                if (Anchor.mc.field_71441_e.func_180495_p(pos).func_177230_c() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isInLiquid() {
        final double y = Anchor.mc.field_71439_g.field_70163_u + 0.01;
        for (int x = MathHelper.func_76128_c(Anchor.mc.field_71439_g.field_70165_t); x < MathHelper.func_76143_f(Anchor.mc.field_71439_g.field_70165_t); ++x) {
            for (int z = MathHelper.func_76128_c(Anchor.mc.field_71439_g.field_70161_v); z < MathHelper.func_76143_f(Anchor.mc.field_71439_g.field_70161_v); ++z) {
                final BlockPos pos = new BlockPos(x, (int)y, z);
                if (Anchor.mc.field_71441_e.func_180495_p(pos).func_177230_c() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
}
