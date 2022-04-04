// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.notebot;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.block.BlockNote;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import java.util.ArrayList;
import net.minecraft.util.EnumFacing;
import javax.vecmath.Vector3d;
import net.minecraft.util.math.BlockPos;
import java.awt.Color;
import mod.hitlerhax.util.Globals;

public class NbMapper implements Globals
{
    private static NbInstrument inst1;
    private static NbInstrument inst2;
    private static NbInstrument inst3;
    private static NbInstrument inst4;
    private static NbInstrument inst5;
    private static NbInstrument inst6;
    private static NbInstrument inst7;
    private static NbInstrument inst8;
    private static NbInstrument inst9;
    private static NbInstrument inst10;
    private static NbInstrument inst11;
    private static NbInstrument inst12;
    private static NbInstrument inst13;
    private static NbInstrument inst14;
    private static NbInstrument inst15;
    private static NbInstrument inst16;
    
    public static NbInstrument GetInstrument(final int inst) {
        switch (inst) {
            case 1: {
                return NbMapper.inst1;
            }
            case 2: {
                return NbMapper.inst2;
            }
            case 3: {
                return NbMapper.inst3;
            }
            case 4: {
                return NbMapper.inst4;
            }
            case 5: {
                return NbMapper.inst5;
            }
            case 6: {
                return NbMapper.inst6;
            }
            case 7: {
                return NbMapper.inst7;
            }
            case 8: {
                return NbMapper.inst8;
            }
            case 9: {
                return NbMapper.inst9;
            }
            case 10: {
                return NbMapper.inst10;
            }
            case 11: {
                return NbMapper.inst11;
            }
            case 12: {
                return NbMapper.inst12;
            }
            case 13: {
                return NbMapper.inst13;
            }
            case 14: {
                return NbMapper.inst14;
            }
            case 15: {
                return NbMapper.inst15;
            }
            case 16: {
                return NbMapper.inst16;
            }
            default: {
                return null;
            }
        }
    }
    
    public static Color GetInstrumentColor(final int inst) {
        switch (inst) {
            case 1: {
                return new Color(188, 152, 98);
            }
            case 2: {
                return new Color(127, 127, 127);
            }
            case 3: {
                return new Color(255, 230, 150);
            }
            case 4: {
                return new Color(255, 255, 255);
            }
            case 5: {
                return new Color(108, 164, 93);
            }
            case 6: {
                return new Color(150, 164, 93);
            }
            case 7: {
                return new Color(28, 164, 93);
            }
            case 8: {
                return new Color(108, 55, 93);
            }
            case 9: {
                return new Color(108, 55, 255);
            }
            case 10: {
                return new Color(108, 255, 0);
            }
            case 11: {
                return new Color(108, 164, 100);
            }
            case 12: {
                return new Color(108, 64, 150);
            }
            case 13: {
                return new Color(8, 64, 93);
            }
            case 14: {
                return new Color(208, 164, 93);
            }
            case 15: {
                return new Color(208, 264, 93);
            }
            case 16: {
                return new Color(208, 264, 193);
            }
            default: {
                return null;
            }
        }
    }
    
    private static BlockPos GetPosition(final BlockPos center, final int forward, final int left) {
        final double cx = center.func_177958_n();
        final double cy = center.func_177956_o();
        final double cz = center.func_177952_p();
        final EnumFacing facing = NbMapper.mc.field_71439_g.func_174811_aO();
        final Vector3d forwardv = new Vector3d(0.0, 0.0, 0.0);
        final Vector3d leftv = new Vector3d(0.0, 0.0, 0.0);
        switch (facing) {
            case NORTH: {
                forwardv.z = -1.0;
                leftv.x = -1.0;
                break;
            }
            case EAST: {
                forwardv.x = 1.0;
                leftv.z = -1.0;
                break;
            }
            case SOUTH: {
                forwardv.z = 1.0;
                leftv.x = 1.0;
                break;
            }
            case WEST: {
                forwardv.x = -1.0;
                leftv.z = 1.0;
                break;
            }
        }
        final double forwardx = forwardv.x * forward;
        final double forwardz = forwardv.z * forward;
        final double leftx = leftv.x * left;
        final double leftz = leftv.z * left;
        return new BlockPos(cx + forwardx + leftx, cy, cz + forwardz + leftz);
    }
    
    public static void MapInstruments() {
        NbMapper.inst1 = new NbInstrument(1);
        ArrayList<Block> blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150344_f);
        addNoteLoop(NbMapper.inst1, blockList);
        NbMapper.inst2 = new NbInstrument(2);
        blockList = new ArrayList<Block>();
        blockList.add((Block)Blocks.field_150354_m);
        blockList.add(Blocks.field_150351_n);
        blockList.add(Blocks.field_192444_dS);
        addNoteLoop(NbMapper.inst2, blockList);
        NbMapper.inst3 = new NbInstrument(3);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150359_w);
        blockList.add(Blocks.field_180398_cJ);
        blockList.add((Block)Blocks.field_150461_bJ);
        addNoteLoop(NbMapper.inst3, blockList);
        NbMapper.inst4 = new NbInstrument(4);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150348_b);
        blockList.add(Blocks.field_150424_aL);
        blockList.add(Blocks.field_150343_Z);
        blockList.add(Blocks.field_150371_ca);
        blockList.add(Blocks.field_150322_A);
        blockList.add(Blocks.field_150365_q);
        blockList.add(Blocks.field_150482_ag);
        blockList.add(Blocks.field_150412_bA);
        blockList.add(Blocks.field_150352_o);
        blockList.add(Blocks.field_150366_p);
        blockList.add(Blocks.field_150369_x);
        blockList.add(Blocks.field_150439_ay);
        blockList.add(Blocks.field_150449_bY);
        blockList.add(Blocks.field_150450_ax);
        blockList.add(Blocks.field_150336_V);
        blockList.add(Blocks.field_150357_h);
        blockList.add(Blocks.field_192443_dR);
        addNoteLoop(NbMapper.inst4, blockList);
        NbMapper.inst5 = new NbInstrument(5);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150340_R);
        addNoteLoop(NbMapper.inst5, blockList);
        NbMapper.inst6 = new NbInstrument(6);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150435_aG);
        addNoteLoop(NbMapper.inst6, blockList);
        NbMapper.inst7 = new NbInstrument(7);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150403_cj);
        addNoteLoop(NbMapper.inst7, blockList);
        NbMapper.inst8 = new NbInstrument(8);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150325_L);
        addNoteLoop(NbMapper.inst8, blockList);
        NbMapper.inst9 = new NbInstrument(9);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_189880_di);
        addNoteLoop(NbMapper.inst9, blockList);
        NbMapper.inst10 = new NbInstrument(10);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150339_S);
        addNoteLoop(NbMapper.inst10, blockList);
        NbMapper.inst11 = new NbInstrument(11);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150425_aM);
        addNoteLoop(NbMapper.inst11, blockList);
        NbMapper.inst12 = new NbInstrument(12);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150423_aK);
        addNoteLoop(NbMapper.inst12, blockList);
        NbMapper.inst13 = new NbInstrument(13);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150475_bE);
        addNoteLoop(NbMapper.inst13, blockList);
        NbMapper.inst14 = new NbInstrument(14);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150407_cf);
        addNoteLoop(NbMapper.inst14, blockList);
        NbMapper.inst15 = new NbInstrument(15);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150426_aN);
        addNoteLoop(NbMapper.inst15, blockList);
        NbMapper.inst16 = new NbInstrument(16);
        blockList = new ArrayList<Block>();
        blockList.add(Blocks.field_150426_aN);
        blockList.add(Blocks.field_150407_cf);
        blockList.add(Blocks.field_150475_bE);
        blockList.add(Blocks.field_150423_aK);
        blockList.add(Blocks.field_150425_aM);
        blockList.add(Blocks.field_150339_S);
        blockList.add(Blocks.field_189880_di);
        blockList.add(Blocks.field_150325_L);
        blockList.add(Blocks.field_150403_cj);
        blockList.add(Blocks.field_150435_aG);
        blockList.add(Blocks.field_150340_R);
        blockList.add(Blocks.field_150348_b);
        blockList.add(Blocks.field_150424_aL);
        blockList.add(Blocks.field_150343_Z);
        blockList.add(Blocks.field_150371_ca);
        blockList.add(Blocks.field_150322_A);
        blockList.add(Blocks.field_150365_q);
        blockList.add(Blocks.field_150482_ag);
        blockList.add(Blocks.field_150412_bA);
        blockList.add(Blocks.field_150352_o);
        blockList.add(Blocks.field_150366_p);
        blockList.add(Blocks.field_150369_x);
        blockList.add(Blocks.field_150439_ay);
        blockList.add(Blocks.field_150449_bY);
        blockList.add(Blocks.field_150450_ax);
        blockList.add(Blocks.field_150336_V);
        blockList.add(Blocks.field_150357_h);
        blockList.add(Blocks.field_192443_dR);
        blockList.add(Blocks.field_150359_w);
        blockList.add(Blocks.field_180398_cJ);
        blockList.add((Block)Blocks.field_150461_bJ);
        blockList.add((Block)Blocks.field_150354_m);
        blockList.add(Blocks.field_150351_n);
        blockList.add(Blocks.field_192444_dS);
        blockList.add(Blocks.field_150344_f);
        final EntityPlayerSP ply = NbMapper.mc.field_71439_g;
        final BlockPos center = new BlockPos(ply.field_70165_t, (double)(int)ply.field_70163_u, ply.field_70161_v);
        int curpitch = 0;
        for (int i = -5; i <= 5; ++i) {
            for (int j = -5; j <= 5; ++j) {
                for (int k = -5; k <= 5; ++k) {
                    if (NbMapper.mc.field_71441_e.func_180495_p(GetPosition(center, i, j).func_177982_a(0, k, 0)).func_177230_c() instanceof BlockNote && !blockList.contains(NbMapper.mc.field_71441_e.func_180495_p(GetPosition(center, i, j).func_177982_a(0, k - 1, 0)).func_177230_c())) {
                        if (curpitch == 25) {
                            return;
                        }
                        NbMapper.inst16.AddNote(curpitch++, GetPosition(center, i, j).func_177982_a(0, k, 0));
                    }
                }
            }
        }
    }
    
    public static void addNoteLoop(final NbInstrument n, final ArrayList<Block> b) {
        final EntityPlayerSP ply = NbMapper.mc.field_71439_g;
        final BlockPos center = new BlockPos(ply.field_70165_t, (double)(int)ply.field_70163_u, ply.field_70161_v);
        int curpitch = 0;
        for (int i = -5; i <= 5; ++i) {
            for (int j = -5; j <= 5; ++j) {
                for (int k = -5; k <= 5; ++k) {
                    if (NbMapper.mc.field_71441_e.func_180495_p(GetPosition(center, i, j).func_177982_a(0, k, 0)).func_177230_c() instanceof BlockNote && b.contains(NbMapper.mc.field_71441_e.func_180495_p(GetPosition(center, i, j).func_177982_a(0, k - 1, 0)).func_177230_c())) {
                        if (curpitch == 25) {
                            return;
                        }
                        n.AddNote(curpitch++, GetPosition(center, i, j).func_177982_a(0, k, 0));
                    }
                }
            }
        }
    }
}
