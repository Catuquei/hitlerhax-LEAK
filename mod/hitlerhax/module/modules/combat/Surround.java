// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import java.awt.Color;
import mod.hitlerhax.util.render.RenderUtil;
import mod.hitlerhax.misc.RenderBuilder;
import mod.hitlerhax.event.events.HtlrEventRender;
import java.util.Iterator;
import mod.hitlerhax.util.BlockUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import mod.hitlerhax.util.PlayerUtil;
import mod.hitlerhax.Client;
import com.mojang.realmsclient.gui.ChatFormatting;
import mod.hitlerhax.util.InventoryUtil;
import net.minecraft.init.Blocks;
import mod.hitlerhax.setting.Setting;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.module.Category;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import mod.hitlerhax.setting.settings.ColorSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class Surround extends Module
{
    final ModeSetting mode;
    final ModeSetting disableMode;
    final ModeSetting centerPlayer;
    final FloatSetting blocksPerTick;
    final ModeSetting autoSwitch;
    final BooleanSetting timeout;
    final FloatSetting timeoutTick;
    final BooleanSetting raytrace;
    final BooleanSetting packet;
    final BooleanSetting swingArm;
    final BooleanSetting antiGlitch;
    final BooleanSetting rotate;
    final BooleanSetting strict;
    final BooleanSetting onlyObsidian;
    final BooleanSetting antiChainPop;
    final BooleanSetting chorusSave;
    final BooleanSetting renderSurround;
    final ColorSetting colorPicker;
    int obsidianSlot;
    public static boolean hasPlaced;
    Vec3d center;
    int blocksPlaced;
    BlockPos renderBlock;
    final List<Vec3d> standardSurround;
    final List<Vec3d> fullSurround;
    final List<Vec3d> antiCitySurround;
    
    public Surround() {
        super("Surround", "Places Obsidian Around You", Category.COMBAT);
        this.mode = new ModeSetting("Mode", this, "Standard", new String[] { "Standard", "Full", "Anti-City" });
        this.disableMode = new ModeSetting("Disable", this, "Off-Ground", new String[] { "Off-Ground", "Completion", "Never" });
        this.centerPlayer = new ModeSetting("Center", this, "Teleport", new String[] { "Teleport", "NCP", "None" });
        this.blocksPerTick = new FloatSetting("Blocks Per Tick", this, 0.0f);
        this.autoSwitch = new ModeSetting("Switch", this, "SwitchBack", new String[] { "SwitchBack", "Normal", "Packet", "None" });
        this.timeout = new BooleanSetting("Timeout", this, true);
        this.timeoutTick = new FloatSetting("Timeout Ticks", this, 1.0f);
        this.raytrace = new BooleanSetting("Raytrace", this, true);
        this.packet = new BooleanSetting("Packet", this, false);
        this.swingArm = new BooleanSetting("Swing Arm", this, true);
        this.antiGlitch = new BooleanSetting("Anti-Glitch", this, false);
        this.rotate = new BooleanSetting("Rotate", this, false);
        this.strict = new BooleanSetting("StrictRotation", this, true);
        this.onlyObsidian = new BooleanSetting("Only Obsidian", this, true);
        this.antiChainPop = new BooleanSetting("Anti-ChainPop", this, true);
        this.chorusSave = new BooleanSetting("Chorus Save", this, false);
        this.renderSurround = new BooleanSetting("Render", this, true);
        this.colorPicker = new ColorSetting("Color Picker", this, new ColorUtil(0, 121, 194, 100));
        this.center = Vec3d.field_186680_a;
        this.blocksPlaced = 0;
        this.standardSurround = new ArrayList<Vec3d>(Arrays.asList(new Vec3d(0.0, -1.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(0.0, 0.0, -1.0)));
        this.fullSurround = new ArrayList<Vec3d>(Arrays.asList(new Vec3d(0.0, -1.0, 0.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0)));
        this.antiCitySurround = new ArrayList<Vec3d>(Arrays.asList(new Vec3d(0.0, -1.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(0.0, 0.0, -2.0), new Vec3d(3.0, 0.0, 0.0), new Vec3d(-3.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 3.0), new Vec3d(0.0, 0.0, -3.0)));
        this.addSetting(this.mode);
        this.addSetting(this.disableMode);
        this.addSetting(this.blocksPerTick);
        this.addSetting(this.timeout);
        this.addSetting(this.timeoutTick);
        this.addSetting(this.raytrace);
        this.addSetting(this.packet);
        this.addSetting(this.swingArm);
        this.addSetting(this.antiGlitch);
        this.addSetting(this.rotate);
        this.addSetting(this.strict);
        this.addSetting(this.autoSwitch);
        this.addSetting(this.centerPlayer);
        this.addSetting(this.onlyObsidian);
        this.addSetting(this.antiChainPop);
        this.addSetting(this.chorusSave);
        this.addSetting(this.renderSurround);
        this.addSetting(this.colorPicker);
    }
    
    public void onEnable() {
        if (Surround.mc.field_71439_g == null || Surround.mc.field_71441_e == null) {
            return;
        }
        super.onEnable();
        this.obsidianSlot = InventoryUtil.getBlockInHotbar(Blocks.field_150343_Z);
        if (this.obsidianSlot == -1) {
            Client.addChatMessage("No Obsidian, " + ChatFormatting.RED + "Disabling!");
            this.setToggled(false);
        }
        else {
            Surround.hasPlaced = false;
            this.center = PlayerUtil.getCenter(Surround.mc.field_71439_g.field_70165_t, Surround.mc.field_71439_g.field_70163_u, Surround.mc.field_71439_g.field_70161_v);
            final String mode = this.centerPlayer.getMode();
            switch (mode) {
                case "Teleport": {
                    Surround.mc.field_71439_g.field_70159_w = 0.0;
                    Surround.mc.field_71439_g.field_70179_y = 0.0;
                    Surround.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(this.center.field_72450_a, this.center.field_72448_b, this.center.field_72449_c, true));
                    Surround.mc.field_71439_g.func_70107_b(this.center.field_72450_a, this.center.field_72448_b, this.center.field_72449_c);
                    break;
                }
                case "NCP": {
                    Surround.mc.field_71439_g.field_70159_w = (this.center.field_72450_a - Surround.mc.field_71439_g.field_70165_t) / 2.0;
                    Surround.mc.field_71439_g.field_70179_y = (this.center.field_72449_c - Surround.mc.field_71439_g.field_70161_v) / 2.0;
                    break;
                }
            }
        }
    }
    
    @Override
    public void onUpdate() {
        if (Surround.mc.field_71439_g == null || Surround.mc.field_71441_e == null) {
            return;
        }
        final String mode = this.disableMode.getMode();
        switch (mode) {
            case "Off-Ground": {
                if (!Surround.mc.field_71439_g.field_70122_E) {
                    this.setToggled(false);
                    break;
                }
                break;
            }
            case "Completion": {
                if (Surround.hasPlaced) {
                    this.setToggled(false);
                    break;
                }
                break;
            }
            case "Never": {
                if (this.timeout.isEnabled() && !this.mode.getMode().equals("Never") && Surround.mc.field_71439_g.field_70173_aa % this.timeoutTick.getValue() == 0.0f) {
                    this.setToggled(false);
                    break;
                }
                break;
            }
        }
        this.surroundPlayer();
        if (this.blocksPlaced == 0) {
            Surround.hasPlaced = true;
        }
    }
    
    public void surroundPlayer() {
        final int previousSlot = Surround.mc.field_71439_g.field_71071_by.field_70461_c;
        final String mode = this.autoSwitch.getMode();
        switch (mode) {
            case "SwitchBack":
            case "Normal": {
                InventoryUtil.switchToSlot(this.onlyObsidian.isEnabled() ? InventoryUtil.getBlockInHotbar(Blocks.field_150343_Z) : InventoryUtil.getAnyBlockInHotbar());
                break;
            }
            case "Packet": {
                InventoryUtil.switchToSlotGhost(this.onlyObsidian.isEnabled() ? InventoryUtil.getBlockInHotbar(Blocks.field_150343_Z) : InventoryUtil.getAnyBlockInHotbar());
                break;
            }
        }
        for (final Vec3d placePositions : this.getSurround()) {
            if (BlockUtil.getBlockResistance(new BlockPos(placePositions.func_178787_e(Surround.mc.field_71439_g.func_174791_d()))).equals(BlockUtil.BlockResistance.Blank)) {
                if (this.obsidianSlot != -1) {
                    BlockUtil.placeBlock(new BlockPos(placePositions.func_178787_e(Surround.mc.field_71439_g.func_174791_d())), this.rotate.isEnabled(), this.strict.isEnabled(), this.raytrace.isEnabled(), this.packet.isEnabled(), this.swingArm.isEnabled(), this.antiGlitch.isEnabled());
                }
                this.renderBlock = new BlockPos(placePositions.func_178787_e(Surround.mc.field_71439_g.func_174791_d()));
                ++this.blocksPlaced;
                if (this.blocksPlaced == this.blocksPerTick.getValue() && !this.disableMode.getMode().equals("Packet")) {
                    return;
                }
                continue;
            }
        }
        if (this.autoSwitch.getMode().equals("SwitchBack")) {
            InventoryUtil.switchToSlot(previousSlot);
        }
    }
    
    @Override
    public void render(final HtlrEventRender event) {
        if (this.renderSurround.isEnabled() && this.renderBlock != null) {
            RenderUtil.drawBoxBlockPos(this.renderBlock, 0.0, 0.0, 0.0, this.colorPicker.getColor(), RenderBuilder.RenderMode.Fill);
        }
    }
    
    List<Vec3d> getSurround() {
        final String mode = this.mode.getMode();
        switch (mode) {
            case "Standard": {
                return this.standardSurround;
            }
            case "Full": {
                return this.fullSurround;
            }
            case "Anti-City": {
                return this.antiCitySurround;
            }
            default: {
                return this.standardSurround;
            }
        }
    }
}
