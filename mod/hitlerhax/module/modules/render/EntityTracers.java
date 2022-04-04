// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import java.awt.Color;
import net.minecraft.entity.item.EntityItem;
import mod.hitlerhax.util.EntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Iterator;
import mod.hitlerhax.util.render.RenderUtil;
import net.minecraft.util.math.Vec3d;
import mod.hitlerhax.util.MathUtil;
import net.minecraft.entity.Entity;
import mod.hitlerhax.event.events.HtlrEventRender;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.ColorSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class EntityTracers extends Module
{
    IntSetting max;
    final BooleanSetting monster;
    final BooleanSetting passive;
    final BooleanSetting players;
    final BooleanSetting items;
    final BooleanSetting other;
    final ColorSetting monsterColor;
    final ColorSetting passiveColor;
    final ColorSetting playerColor;
    final ColorSetting itemColor;
    final ColorSetting otherColor;
    final FloatSetting width;
    
    public EntityTracers() {
        super("Tracers", "Traces a line to entities", Category.RENDER);
        this.max = new IntSetting("Maximum Tracers", this, 50);
        this.monster = new BooleanSetting("Monsters", this, true);
        this.passive = new BooleanSetting("Passive Mobs", this, true);
        this.players = new BooleanSetting("Players", this, true);
        this.items = new BooleanSetting("Items", this, true);
        this.other = new BooleanSetting("Other Entities", this, true);
        this.monsterColor = new ColorSetting("Monster Color", this, new ColorUtil(255, 255, 255, 255));
        this.passiveColor = new ColorSetting("Passive Color", this, new ColorUtil(255, 255, 255, 255));
        this.playerColor = new ColorSetting("Player Color", this, new ColorUtil(255, 255, 255, 255));
        this.itemColor = new ColorSetting("Item Color", this, new ColorUtil(255, 255, 255, 255));
        this.otherColor = new ColorSetting("Other Color", this, new ColorUtil(255, 255, 255, 255));
        this.width = new FloatSetting("Tracer Width", this, 1.0f);
        this.addSetting(this.monster);
        this.addSetting(this.passive);
        this.addSetting(this.players);
        this.addSetting(this.items);
        this.addSetting(this.other);
        this.addSetting(this.monsterColor);
        this.addSetting(this.passiveColor);
        this.addSetting(this.playerColor);
        this.addSetting(this.itemColor);
        this.addSetting(this.otherColor);
        this.addSetting(this.width);
    }
    
    @Override
    public void render(final HtlrEventRender event) {
        if (EntityTracers.mc.field_71441_e == null) {
            return;
        }
        for (final Entity entity : EntityTracers.mc.field_71441_e.field_72996_f) {
            if (this.checkShouldRenderTracers(entity)) {
                final Vec3d pos = MathUtil.interpolateEntity(entity, event.get_partial_ticks()).func_178786_a(EntityTracers.mc.func_175598_ae().field_78725_b, EntityTracers.mc.func_175598_ae().field_78726_c, EntityTracers.mc.func_175598_ae().field_78723_d);
                if (pos == null) {
                    continue;
                }
                final boolean bobbing = EntityTracers.mc.field_71474_y.field_74336_f;
                EntityTracers.mc.field_71474_y.field_74336_f = false;
                EntityTracers.mc.field_71460_t.func_78479_a(event.get_partial_ticks(), 0);
                final Vec3d forward = new Vec3d(0.0, 0.0, 1.0).func_178789_a(-(float)Math.toRadians(EntityTracers.mc.field_71439_g.field_70125_A)).func_178785_b(-(float)Math.toRadians(EntityTracers.mc.field_71439_g.field_70177_z));
                RenderUtil.drawLine3D((float)forward.field_72450_a, (float)forward.field_72448_b + EntityTracers.mc.field_71439_g.func_70047_e(), (float)forward.field_72449_c, (float)pos.field_72450_a, (float)pos.field_72448_b, (float)pos.field_72449_c, this.width.value, this.getColor(entity));
                EntityTracers.mc.field_71474_y.field_74336_f = bobbing;
                EntityTracers.mc.field_71460_t.func_78479_a(event.get_partial_ticks(), 0);
            }
        }
    }
    
    private boolean checkShouldRenderTracers(final Entity e) {
        if (e == EntityTracers.mc.field_71439_g) {
            return false;
        }
        if (e instanceof EntityPlayer) {
            return this.players.enabled;
        }
        if (EntityUtil.isHostileMob(e) || EntityUtil.isNeutralMob(e)) {
            return this.monster.enabled;
        }
        if (EntityUtil.isPassive(e)) {
            return this.passive.enabled;
        }
        if (e instanceof EntityItem) {
            return this.items.enabled;
        }
        return this.other.enabled;
    }
    
    private int getColor(final Entity e) {
        if (e instanceof EntityPlayer) {
            return new Color(this.playerColor.getColor().getRed() / 255.0f, this.playerColor.getColor().getGreen() / 255.0f, this.playerColor.getColor().getBlue() / 255.0f, 0.5f).getRGB();
        }
        if (EntityUtil.isHostileMob(e) || EntityUtil.isNeutralMob(e)) {
            return new Color(this.monsterColor.getColor().getRed() / 255.0f, this.monsterColor.getColor().getGreen() / 255.0f, this.monsterColor.getColor().getBlue() / 255.0f, 0.5f).getRGB();
        }
        if (EntityUtil.isPassive(e)) {
            return new Color(this.passiveColor.getColor().getRed() / 255.0f, this.passiveColor.getColor().getGreen() / 255.0f, this.passiveColor.getColor().getBlue() / 255.0f, 0.5f).getRGB();
        }
        if (e instanceof EntityItem) {
            return new Color(this.itemColor.getColor().getRed() / 255.0f, this.itemColor.getColor().getGreen() / 255.0f, this.itemColor.getColor().getBlue() / 255.0f, 0.5f).getRGB();
        }
        return new Color(this.otherColor.getColor().getRed() / 255.0f, this.otherColor.getColor().getGreen() / 255.0f, this.otherColor.getColor().getBlue() / 255.0f, 0.5f).getRGB();
    }
}
