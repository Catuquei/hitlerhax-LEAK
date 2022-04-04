// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.setting.settings;

import java.util.Map;
import mod.hitlerhax.event.events.HtlrEventSettings;
import mod.hitlerhax.event.HtlrEventBus;
import java.util.Iterator;
import java.awt.Color;
import net.minecraftforge.fml.common.registry.GameRegistry;
import mod.hitlerhax.Main;
import mod.hitlerhax.module.Module;
import java.util.HashMap;
import net.minecraft.block.Block;
import java.util.ArrayList;
import mod.hitlerhax.setting.Setting;

public class SearchBlockSelectorSetting extends Setting
{
    public final boolean colorSettings;
    public ArrayList<Block> blocks;
    public HashMap<Block, Integer> colors;
    
    public SearchBlockSelectorSetting(final String name, final Module parent, final boolean colorSettings, final ArrayList<Block> blocks, final HashMap<Block, Integer> colors) {
        this.blocks = new ArrayList<Block>();
        this.colors = new HashMap<Block, Integer>();
        this.name = name;
        this.parent = parent;
        this.colorSettings = colorSettings;
        if (!Main.configLoaded) {
            this.blocks = blocks;
            this.colors = colors;
        }
        for (final Block b : GameRegistry.findRegistry((Class)Block.class).getValuesCollection()) {
            colors.put(b, new Color(255, 255, 255).getRGB());
        }
    }
    
    public ArrayList<Block> getBlocks() {
        return this.blocks;
    }
    
    public void setBlocks(final ArrayList<Block> blocks) {
        this.blocks = blocks;
        HtlrEventBus.EVENT_BUS.post(new HtlrEventSettings(this, this.parent));
    }
    
    public HashMap<Block, Integer> getColors() {
        return this.colors;
    }
    
    public int getColor(final Block b) {
        for (final Map.Entry<Block, Integer> e : this.colors.entrySet()) {
            if (e.getKey() == b) {
                return e.getValue();
            }
        }
        return 0;
    }
    
    public void setColor(final Block b, final Integer c) {
        for (final Map.Entry<Block, Integer> e : this.colors.entrySet()) {
            if (e.getKey() == b) {
                e.setValue(c);
            }
        }
        HtlrEventBus.EVENT_BUS.post(new HtlrEventSettings(this, this.parent));
    }
}
