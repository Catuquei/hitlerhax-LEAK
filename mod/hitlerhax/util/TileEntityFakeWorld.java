// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util;

import net.minecraft.block.Block;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.storage.WorldInfo;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.WorldProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class TileEntityFakeWorld extends World
{
    private IBlockState state;
    
    public TileEntityFakeWorld(final IBlockState state, final WorldProvider provider) {
        super((ISaveHandler)null, (WorldInfo)null, provider, (Profiler)null, false);
        this.state = state;
    }
    
    public IBlockState func_180495_p(final BlockPos position) {
        if (position.equals((Object)BlockPos.field_177992_a)) {
            return this.state;
        }
        return Blocks.field_150350_a.func_176223_P();
    }
    
    public boolean func_180501_a(final BlockPos position, final IBlockState state, final int updt) {
        this.state = state;
        return true;
    }
    
    public void func_184138_a(final BlockPos pos, final IBlockState oldState, final IBlockState newState, final int flags) {
    }
    
    public long func_82737_E() {
        if (this.field_72986_A == null) {
            return 0L;
        }
        return super.func_82737_E();
    }
    
    protected IChunkProvider func_72970_h() {
        return null;
    }
    
    protected boolean func_175680_a(final int paramInt1, final int paramInt2, final boolean paramBoolean) {
        return false;
    }
    
    public void func_190524_a(final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
    }
    
    public void func_190529_b(final BlockPos pos, final Block block, final BlockPos target) {
    }
}
