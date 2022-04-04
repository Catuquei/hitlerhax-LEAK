// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.render;

import net.minecraft.util.EnumFacing;
import java.util.HashMap;

public final class Geometry
{
    public static final HashMap<EnumFacing, Integer> FACEMAP;
    
    static {
        (FACEMAP = new HashMap<EnumFacing, Integer>()).put(EnumFacing.DOWN, 1);
        Geometry.FACEMAP.put(EnumFacing.WEST, 16);
        Geometry.FACEMAP.put(EnumFacing.NORTH, 4);
        Geometry.FACEMAP.put(EnumFacing.SOUTH, 8);
        Geometry.FACEMAP.put(EnumFacing.EAST, 32);
        Geometry.FACEMAP.put(EnumFacing.UP, 2);
    }
    
    public static final class Quad
    {
        public static final int DOWN = 1;
        public static final int UP = 2;
        public static final int NORTH = 4;
        public static final int SOUTH = 8;
        public static final int WEST = 16;
        public static final int EAST = 32;
        public static final int ALL = 63;
    }
}
