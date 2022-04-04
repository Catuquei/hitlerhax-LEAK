// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import mod.hitlerhax.util.render.RenderUtil;
import java.awt.Color;
import java.util.function.BiConsumer;
import mod.hitlerhax.event.events.HtlrEventRender;
import net.minecraft.block.Block;
import java.util.Iterator;
import net.minecraft.util.math.Vec3i;
import net.minecraft.init.Blocks;
import java.util.ArrayList;
import java.util.List;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.module.Category;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.util.math.BlockPos;
import mod.hitlerhax.setting.settings.ColorSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.module.Module;

public class HoleEsp extends Module
{
    public final FloatSetting size;
    public final BooleanSetting outline;
    public final ColorSetting obbyColor;
    public final ColorSetting bedrockColor;
    private final BlockPos[] surroundOffset;
    private ConcurrentHashMap<BlockPos, Boolean> safeHoles;
    
    public HoleEsp() {
        super("HoleEsp", "Shows safe holes", Category.RENDER);
        this.size = new FloatSetting("size", this, 0.1f);
        this.outline = new BooleanSetting("outline", this, true);
        this.obbyColor = new ColorSetting("obbyColor", this, new ColorUtil(69, 48, 146, 110));
        this.bedrockColor = new ColorSetting("bedrockColor", this, new ColorUtil(0, 255, 0, 110));
        this.surroundOffset = new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
        this.addSetting(this.size);
        this.addSetting(this.outline);
    }
    
    public List<BlockPos> getSphere(final BlockPos loc, final float r, final int h, final boolean hollow, final boolean sphere, final int plus_y) {
        final List<BlockPos> circleblocks = new ArrayList<BlockPos>();
        final int cx = loc.func_177958_n();
        final int cy = loc.func_177956_o();
        final int cz = loc.func_177952_p();
        for (int x = cx - (int)r; x <= cx + r; ++x) {
            for (int z = cz - (int)r; z <= cz + r; ++z) {
                for (int y = sphere ? (cy - (int)r) : cy; y < (sphere ? (cy + r) : ((float)(cy + h))); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        final BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(HoleEsp.mc.field_71439_g.field_70165_t), Math.floor(HoleEsp.mc.field_71439_g.field_70163_u), Math.floor(HoleEsp.mc.field_71439_g.field_70161_v));
    }
    
    @Override
    public void onUpdate() {
        if (this.safeHoles == null) {
            this.safeHoles = new ConcurrentHashMap<BlockPos, Boolean>();
        }
        else {
            this.safeHoles.clear();
        }
        final int range = (int)Math.ceil(8.0);
        final List<BlockPos> blockPosList = this.getSphere(getPlayerPos(), (float)range, range, false, true, 0);
        for (final BlockPos pos : blockPosList) {
            if (!HoleEsp.mc.field_71441_e.func_180495_p(pos).func_177230_c().equals(Blocks.field_150350_a)) {
                continue;
            }
            if (!HoleEsp.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, 0)).func_177230_c().equals(Blocks.field_150350_a)) {
                continue;
            }
            if (!HoleEsp.mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 2, 0)).func_177230_c().equals(Blocks.field_150350_a)) {
                continue;
            }
            boolean isSafe = true;
            boolean isBedrock = true;
            for (final BlockPos offset : this.surroundOffset) {
                final Block block = HoleEsp.mc.field_71441_e.func_180495_p(pos.func_177971_a((Vec3i)offset)).func_177230_c();
                if (block != Blocks.field_150357_h) {
                    isBedrock = false;
                }
                if (block != Blocks.field_150357_h && block != Blocks.field_150343_Z && block != Blocks.field_150477_bB && block != Blocks.field_150467_bQ) {
                    isSafe = false;
                    break;
                }
            }
            if (!isSafe) {
                continue;
            }
            this.safeHoles.put(pos, isBedrock);
        }
    }
    
    @Override
    public void render(final HtlrEventRender event) {
        if (HoleEsp.mc.field_71439_g == null || this.safeHoles == null) {
            return;
        }
        if (this.safeHoles.isEmpty()) {
            return;
        }
        this.safeHoles.forEach(this::drawBox);
        this.safeHoles.forEach(this::drawOutline);
    }
    
    private ColorUtil getColor(final boolean isBedrock) {
        ColorUtil c;
        if (isBedrock) {
            c = this.bedrockColor.getColor();
        }
        else {
            c = this.obbyColor.getColor();
        }
        return new ColorUtil(c);
    }
    
    private void drawBox(final BlockPos blockPos, final boolean isBedrock) {
        final ColorUtil color = this.getColor(isBedrock);
        RenderUtil.drawBox(blockPos, this.size.getValue(), color, 63);
    }
    
    private void drawOutline(final BlockPos blockPos, final boolean isBedrock) {
        final ColorUtil color = this.getColor(isBedrock);
        if (this.outline.isEnabled()) {
            RenderUtil.drawBoundingBox(blockPos, this.size.getValue(), 2.0f, color);
        }
    }
}
