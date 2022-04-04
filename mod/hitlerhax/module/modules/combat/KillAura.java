// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.List;
import mod.hitlerhax.Main;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class KillAura extends Module
{
    public final IntSetting range;
    public final BooleanSetting switchA;
    public final BooleanSetting swordOnly;
    public final BooleanSetting players;
    public final BooleanSetting passives;
    public final BooleanSetting hostiles;
    
    public KillAura() {
        super("KillAura", "Attacks nearby entities", Category.COMBAT);
        this.range = new IntSetting("range", this, 6);
        this.switchA = new BooleanSetting("switch", this, false);
        this.swordOnly = new BooleanSetting("swordOnly", this, false);
        this.players = new BooleanSetting("players", this, true);
        this.passives = new BooleanSetting("passives", this, false);
        this.hostiles = new BooleanSetting("hostiles", this, false);
        this.addSetting(this.range);
        this.addSetting(this.switchA);
        this.addSetting(this.swordOnly);
        this.addSetting(this.players);
        this.addSetting(this.passives);
        this.addSetting(this.hostiles);
    }
    
    @Override
    public void onUpdate() {
        if (KillAura.mc.field_71439_g == null || KillAura.mc.field_71439_g.field_70128_L || Main.moduleManager.getModule("Surround").isToggled()) {
            return;
        }
        final List<Entity> targets = (List<Entity>)KillAura.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity != KillAura.mc.field_71439_g).filter(entity -> KillAura.mc.field_71439_g.func_70032_d(entity) <= this.range.getValue()).filter(entity -> !entity.field_70128_L).filter(this::attackCheck).sorted(Comparator.comparing(s -> KillAura.mc.field_71439_g.func_70032_d(s))).collect(Collectors.toList());
        targets.forEach(this::attack);
    }
    
    public void attack(final Entity e) {
        if (KillAura.mc.field_71439_g.func_184825_o(0.0f) >= 1.0f) {
            KillAura.mc.field_71442_b.func_78764_a((EntityPlayer)KillAura.mc.field_71439_g, e);
            KillAura.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        }
    }
    
    private boolean attackCheck(final Entity entity) {
        if (this.players.isEnabled() && entity instanceof EntityPlayer && ((EntityPlayer)entity).func_110143_aJ() > 0.0f) {
            return true;
        }
        if (this.passives.isEnabled() && entity instanceof EntityAnimal) {
            return !(entity instanceof EntityTameable);
        }
        return this.hostiles.isEnabled() && entity instanceof EntityMob;
    }
}
