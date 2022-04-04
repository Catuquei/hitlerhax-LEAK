// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;
import java.awt.Color;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntityChest;
import java.util.function.Predicate;
import mod.hitlerhax.util.render.RenderUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityEnderCrystal;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import mod.hitlerhax.event.events.HtlrEventRender;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.util.render.ColorUtil;
import net.minecraft.entity.Entity;
import java.util.List;
import mod.hitlerhax.setting.settings.ColorSetting;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class Esp extends Module
{
    public final BooleanSetting chams;
    public final ModeSetting entityMode;
    public final ModeSetting storage;
    public final ModeSetting crystalMode;
    public final BooleanSetting mob;
    public final BooleanSetting item;
    public final IntSetting range;
    public final IntSetting lineWidth;
    public final ColorSetting playerColor;
    public final ColorSetting hostileMobColor;
    public final ColorSetting passiveMobColor;
    public final ColorSetting itemColor;
    public final ColorSetting chestColor;
    public final ColorSetting enderChestColor;
    public final ColorSetting shulkerBoxColor;
    public final ColorSetting otherColor;
    List<Entity> entities;
    ColorUtil playerC;
    ColorUtil playerCOutline;
    ColorUtil hostileMobC;
    ColorUtil passiveMobC;
    ColorUtil mainIntColor;
    ColorUtil containerColor;
    ColorUtil containerBox;
    int opacityGradient;
    
    public Esp() {
        super("Esp", "draws esp around players and storage blocks.", Category.RENDER);
        this.chams = new BooleanSetting("walls", this, false);
        this.entityMode = new ModeSetting("entity", this, "box", new String[] { "box", "highlight", "box+highlight", "outline", "outlineEsp2D", "glow", "off" });
        this.storage = new ModeSetting("storage", this, "outline", new String[] { "outline", "fill", "both", "off" });
        this.crystalMode = new ModeSetting("crystal", this, "pretty", new String[] { "pretty", "glow", "off" });
        this.mob = new BooleanSetting("mob", this, false);
        this.item = new BooleanSetting("item", this, true);
        this.range = new IntSetting("range", this, 100);
        this.lineWidth = new IntSetting("lineWidth", this, 3);
        this.playerColor = new ColorSetting("player", this, new ColorUtil(0, 121, 194, 100));
        this.hostileMobColor = new ColorSetting("hostileMob", this, new ColorUtil(255, 0, 0, 100));
        this.passiveMobColor = new ColorSetting("passiveMob", this, new ColorUtil(0, 255, 0, 100));
        this.itemColor = new ColorSetting("itemColor", this, new ColorUtil(0, 121, 194, 100));
        this.chestColor = new ColorSetting("chest", this, new ColorUtil(255, 255, 0, 50));
        this.enderChestColor = new ColorSetting("enderChest", this, new ColorUtil(255, 70, 200, 50));
        this.shulkerBoxColor = new ColorSetting("shulkerBox", this, new ColorUtil(255, 182, 193, 50));
        this.otherColor = new ColorSetting("other", this, new ColorUtil(150, 150, 150, 50));
        this.addSetting(this.entityMode, this.storage, this.crystalMode, this.mob, this.item, this.chams, this.range, this.lineWidth, this.playerColor, this.passiveMobColor, this.hostileMobColor, this.itemColor, this.chestColor, this.enderChestColor, this.shulkerBoxColor, this.otherColor);
    }
    
    @Override
    public void render(final HtlrEventRender event) {
        (this.entities = (List<Entity>)Esp.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity != Esp.mc.field_71439_g).collect(Collectors.toList())).forEach(entity -> {
            this.defineEntityColors(entity);
            if (!this.entityMode.is("glow") && !(entity instanceof EntityEnderCrystal)) {
                entity.func_184195_f(false);
            }
            if ((this.entityMode.is("glow") && !this.mob.isEnabled() && entity instanceof EntityCreature) || entity instanceof EntitySlime || entity instanceof EntityAnimal) {
                entity.func_184195_f(false);
            }
            if (this.entityMode.is("glow") && !this.item.isEnabled() && entity instanceof EntityItem) {
                entity.func_184195_f(false);
            }
            if (!this.crystalMode.is("glow") && entity instanceof EntityEnderCrystal) {
                entity.func_184195_f(false);
            }
            if (this.entityMode.is("box") && entity instanceof EntityPlayer) {
                RenderUtil.playerEsp(entity.func_174813_aQ(), (float)this.lineWidth.getValue(), this.playerCOutline);
            }
            if (this.entityMode.is("highlight") && entity instanceof EntityPlayer) {
                RenderUtil.drawPlayerBox(entity.func_174813_aQ(), (float)this.lineWidth.getValue(), this.playerC, 63);
            }
            if (this.entityMode.is("box+highlight") && entity instanceof EntityPlayer) {
                RenderUtil.playerEsp(entity.func_174813_aQ(), (float)this.lineWidth.getValue(), this.playerCOutline);
                RenderUtil.drawPlayerBox(entity.func_174813_aQ(), (float)this.lineWidth.getValue(), this.playerC, 63);
            }
            if (this.entityMode.is("glow") && entity instanceof EntityPlayer) {
                entity.func_184195_f(true);
            }
            if ((this.entityMode.is("glow") && this.mob.isEnabled() && entity instanceof EntityCreature) || entity instanceof EntitySlime) {
                entity.func_184195_f(true);
            }
            if (this.entityMode.is("glow") && this.mob.isEnabled() && entity instanceof EntityAnimal) {
                entity.func_184195_f(true);
            }
            if (this.entityMode.is("glow") && this.item.isEnabled() && entity instanceof EntityItem) {
                entity.func_184195_f(true);
            }
            if (this.crystalMode.is("glow") && entity instanceof EntityEnderCrystal) {
                entity.func_184195_f(true);
            }
            if (this.mob.isEnabled() && !this.entityMode.is("outline") && !this.entityMode.is("glow") && !this.entityMode.is("off") && (entity instanceof EntityCreature || entity instanceof EntitySlime)) {
                this.hostileMobC = new ColorUtil(this.hostileMobColor.getColor(), 255);
                RenderUtil.drawBoundingBox(entity.func_174813_aQ(), 2.0f, this.hostileMobC);
            }
            if (this.mob.isEnabled() && !this.entityMode.is("outline") && !this.entityMode.is("glow") && !this.entityMode.is("off") && entity instanceof EntityAnimal) {
                this.passiveMobC = new ColorUtil(this.passiveMobColor.getColor(), 255);
                RenderUtil.drawBoundingBox(entity.func_174813_aQ(), 2.0f, this.passiveMobC);
            }
            if (this.item.isEnabled() && !this.entityMode.is("off") && !this.entityMode.is("glow") && entity instanceof EntityItem) {
                this.mainIntColor = new ColorUtil(this.itemColor.getColor(), 255);
                RenderUtil.drawBoundingBox(entity.func_174813_aQ(), 2.0f, this.mainIntColor);
            }
            return;
        });
        if (this.storage.is("outline")) {
            Esp.mc.field_71441_e.field_147482_g.stream().filter(this::rangeTileCheck).forEach(tileEntity -> {
                if (tileEntity instanceof TileEntityChest) {
                    this.containerColor = new ColorUtil(this.chestColor.getColor(), 255);
                    RenderUtil.drawBoundingBox(Esp.mc.field_71441_e.func_180495_p(tileEntity.func_174877_v()).func_185918_c((World)Esp.mc.field_71441_e, tileEntity.func_174877_v()), 2.0f, this.containerColor);
                }
                if (tileEntity instanceof TileEntityEnderChest) {
                    this.containerColor = new ColorUtil(this.enderChestColor.getColor(), 255);
                    RenderUtil.drawBoundingBox(Esp.mc.field_71441_e.func_180495_p(tileEntity.func_174877_v()).func_185918_c((World)Esp.mc.field_71441_e, tileEntity.func_174877_v()), 2.0f, this.containerColor);
                }
                if (tileEntity instanceof TileEntityShulkerBox) {
                    this.containerColor = new ColorUtil(this.shulkerBoxColor.getColor(), 255);
                    RenderUtil.drawBoundingBox(Esp.mc.field_71441_e.func_180495_p(tileEntity.func_174877_v()).func_185918_c((World)Esp.mc.field_71441_e, tileEntity.func_174877_v()), 2.0f, this.containerColor);
                }
                if (tileEntity instanceof TileEntityDispenser || tileEntity instanceof TileEntityFurnace || tileEntity instanceof TileEntityHopper || tileEntity instanceof TileEntityDropper) {
                    this.containerColor = new ColorUtil(this.otherColor.getColor(), 255);
                    RenderUtil.drawBoundingBox(Esp.mc.field_71441_e.func_180495_p(tileEntity.func_174877_v()).func_185918_c((World)Esp.mc.field_71441_e, tileEntity.func_174877_v()), 2.0f, this.containerColor);
                }
                return;
            });
        }
        if (this.storage.is("both")) {
            Esp.mc.field_71441_e.field_147482_g.stream().filter(this::rangeTileCheck).forEach(tileEntity -> {
                if (tileEntity instanceof TileEntityChest) {
                    this.containerColor = new ColorUtil(this.chestColor.getColor(), 255);
                    this.containerBox = new ColorUtil(this.chestColor.getColor());
                    RenderUtil.drawBoundingBox(Esp.mc.field_71441_e.func_180495_p(tileEntity.func_174877_v()).func_185918_c((World)Esp.mc.field_71441_e, tileEntity.func_174877_v()), 2.0f, this.containerColor);
                    this.drawStorageBox(tileEntity.func_174877_v(), this.containerBox);
                }
                if (tileEntity instanceof TileEntityEnderChest) {
                    this.containerColor = new ColorUtil(this.enderChestColor.getColor(), 255);
                    this.containerBox = new ColorUtil(this.enderChestColor.getColor());
                    RenderUtil.drawBoundingBox(Esp.mc.field_71441_e.func_180495_p(tileEntity.func_174877_v()).func_185918_c((World)Esp.mc.field_71441_e, tileEntity.func_174877_v()), 2.0f, this.containerColor);
                    this.drawStorageBox(tileEntity.func_174877_v(), this.containerBox);
                }
                if (tileEntity instanceof TileEntityShulkerBox) {
                    this.containerColor = new ColorUtil(this.shulkerBoxColor.getColor(), 255);
                    this.containerBox = new ColorUtil(this.shulkerBoxColor.getColor());
                    RenderUtil.drawBoundingBox(Esp.mc.field_71441_e.func_180495_p(tileEntity.func_174877_v()).func_185918_c((World)Esp.mc.field_71441_e, tileEntity.func_174877_v()), 2.0f, this.containerColor);
                    this.drawBox(tileEntity.func_174877_v(), this.containerBox);
                }
                if (tileEntity instanceof TileEntityDispenser || tileEntity instanceof TileEntityFurnace || tileEntity instanceof TileEntityHopper || tileEntity instanceof TileEntityDropper) {
                    this.containerColor = new ColorUtil(this.otherColor.getColor(), 255);
                    this.containerBox = new ColorUtil(this.otherColor.getColor());
                    RenderUtil.drawBoundingBox(Esp.mc.field_71441_e.func_180495_p(tileEntity.func_174877_v()).func_185918_c((World)Esp.mc.field_71441_e, tileEntity.func_174877_v()), 2.0f, this.containerColor);
                    this.drawBox(tileEntity.func_174877_v(), this.containerBox);
                }
                return;
            });
        }
        if (this.storage.is("fill")) {
            Esp.mc.field_71441_e.field_147482_g.stream().filter(this::rangeTileCheck).forEach(tileEntity -> {
                if (tileEntity instanceof TileEntityChest) {
                    this.containerBox = new ColorUtil(this.chestColor.getColor());
                    this.drawStorageBox(tileEntity.func_174877_v(), this.containerBox);
                }
                if (tileEntity instanceof TileEntityEnderChest) {
                    this.containerBox = new ColorUtil(this.enderChestColor.getColor());
                    this.drawStorageBox(tileEntity.func_174877_v(), this.containerBox);
                }
                if (tileEntity instanceof TileEntityShulkerBox) {
                    this.containerBox = new ColorUtil(this.shulkerBoxColor.getColor());
                    this.drawBox(tileEntity.func_174877_v(), this.containerBox);
                }
                if (tileEntity instanceof TileEntityDispenser || tileEntity instanceof TileEntityFurnace || tileEntity instanceof TileEntityHopper || tileEntity instanceof TileEntityDropper) {
                    this.containerBox = new ColorUtil(this.otherColor.getColor());
                    this.drawBox(tileEntity.func_174877_v(), this.containerBox);
                }
            });
        }
    }
    
    private void drawStorageBox(final BlockPos blockPos, final ColorUtil color) {
        RenderUtil.drawStorageBox(blockPos, 0.88, color, 63);
    }
    
    private void drawBox(final BlockPos blockPos, final ColorUtil color) {
        RenderUtil.drawBox(blockPos, 1.0, color, 63);
    }
    
    public void onDisable() {
        if (this.entities != Esp.mc.field_71439_g) {
            this.entities.forEach(p -> p.func_184195_f(false));
        }
    }
    
    private void defineEntityColors(final Entity entity) {
        if (entity instanceof EntityPlayer) {
            this.playerC = new ColorUtil(this.playerColor.getColor());
            this.playerCOutline = new ColorUtil(this.playerColor.getColor(), 255);
        }
        if (entity instanceof EntityMob) {
            this.hostileMobC = new ColorUtil(this.hostileMobColor.getColor());
        }
        else if (entity instanceof EntityAnimal) {
            this.passiveMobC = new ColorUtil(this.passiveMobColor.getColor());
        }
        else {
            this.passiveMobC = new ColorUtil(this.passiveMobColor.getColor());
        }
        if (entity instanceof EntitySlime) {
            this.hostileMobC = new ColorUtil(this.hostileMobColor.getColor());
        }
        if (entity != null) {
            this.mainIntColor = new ColorUtil(this.itemColor.getColor());
        }
    }
    
    private boolean rangeTileCheck(final TileEntity tileEntity) {
        if (tileEntity.func_145835_a(Esp.mc.field_71439_g.field_70165_t, Esp.mc.field_71439_g.field_70163_u, Esp.mc.field_71439_g.field_70161_v) > this.range.getValue() * this.range.getValue()) {
            return false;
        }
        if (tileEntity.func_145835_a(Esp.mc.field_71439_g.field_70165_t, Esp.mc.field_71439_g.field_70163_u, Esp.mc.field_71439_g.field_70161_v) >= 32400.0) {
            this.opacityGradient = 50;
        }
        else if (tileEntity.func_145835_a(Esp.mc.field_71439_g.field_70165_t, Esp.mc.field_71439_g.field_70163_u, Esp.mc.field_71439_g.field_70161_v) >= 16900.0 && tileEntity.func_145835_a(Esp.mc.field_71439_g.field_70165_t, Esp.mc.field_71439_g.field_70163_u, Esp.mc.field_71439_g.field_70161_v) < 32400.0) {
            this.opacityGradient = 100;
        }
        else if (tileEntity.func_145835_a(Esp.mc.field_71439_g.field_70165_t, Esp.mc.field_71439_g.field_70163_u, Esp.mc.field_71439_g.field_70161_v) >= 6400.0 && tileEntity.func_145835_a(Esp.mc.field_71439_g.field_70165_t, Esp.mc.field_71439_g.field_70163_u, Esp.mc.field_71439_g.field_70161_v) < 16900.0) {
            this.opacityGradient = 150;
        }
        else if (tileEntity.func_145835_a(Esp.mc.field_71439_g.field_70165_t, Esp.mc.field_71439_g.field_70163_u, Esp.mc.field_71439_g.field_70161_v) >= 900.0 && tileEntity.func_145835_a(Esp.mc.field_71439_g.field_70165_t, Esp.mc.field_71439_g.field_70163_u, Esp.mc.field_71439_g.field_70161_v) < 6400.0) {
            this.opacityGradient = 200;
        }
        else {
            this.opacityGradient = 255;
        }
        return true;
    }
}
