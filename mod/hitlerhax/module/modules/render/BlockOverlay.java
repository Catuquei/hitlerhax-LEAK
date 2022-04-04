// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import net.minecraft.util.math.BlockPos;
import mod.hitlerhax.util.render.RenderUtil;
import net.minecraft.block.Block;
import mod.hitlerhax.util.BlockUtil;
import net.minecraft.util.math.RayTraceResult;
import mod.hitlerhax.event.events.HtlrEventRender;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.Module;

public class BlockOverlay extends Module
{
    public BlockOverlay() {
        super("BlockOverlay", "Highlights the selected block", Category.RENDER);
    }
    
    @Override
    public void render(final HtlrEventRender event) {
        if (BlockOverlay.mc.field_71439_g == null || BlockOverlay.mc.field_71441_e == null) {
            return;
        }
        if (BlockOverlay.mc.field_71476_x.field_72313_a == RayTraceResult.Type.BLOCK) {
            final Block block = BlockUtil.getBlock(BlockOverlay.mc.field_71476_x.func_178782_a());
            final BlockPos blockPos = BlockOverlay.mc.field_71476_x.func_178782_a();
            if (Block.func_149682_b(block) == 0) {
                return;
            }
            RenderUtil.drawBlockESP(blockPos, 1.0f, 1.0f, 1.0f);
        }
        super.onRenderWorldLast(event);
    }
}
