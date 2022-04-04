// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import net.minecraft.item.Item;
import org.lwjgl.input.Mouse;
import mod.hitlerhax.util.InventoryUtil;
import mod.hitlerhax.util.PlayerUtil;
import mod.hitlerhax.Main;
import net.minecraft.init.Items;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class Offhand extends Module
{
    final ModeSetting mode;
    final ModeSetting fallbackMode;
    final FloatSetting health;
    final BooleanSetting checks;
    final BooleanSetting caFunction;
    final BooleanSetting elytraCheck;
    final BooleanSetting fallCheck;
    final BooleanSetting swordGap;
    final BooleanSetting forceGap;
    final BooleanSetting hotbar;
    
    public Offhand() {
        super("Offhand", "Switches items in the offhand to a totem when low on health", Category.COMBAT);
        this.mode = new ModeSetting("Mode", this, "Crystal", new String[] { "Crystal", "Gapple", "Bed", "Chorus", "Totem" });
        this.fallbackMode = new ModeSetting("Fallback", this, "Crystal", new String[] { "Crystal", "Gapple", "Bed", "Chorus", "Totem" });
        this.health = new FloatSetting("Health", this, 0.1f);
        this.checks = new BooleanSetting("Checks", this, true);
        this.caFunction = new BooleanSetting("ChecksAutoCrystal", this, false);
        this.elytraCheck = new BooleanSetting("ChecksElytra", this, false);
        this.fallCheck = new BooleanSetting("ChecksFalling", this, false);
        this.swordGap = new BooleanSetting("Sword Gapple", this, true);
        this.forceGap = new BooleanSetting("Force Gapple", this, false);
        this.hotbar = new BooleanSetting("Search Hotbar", this, false);
        this.addSetting(this.mode);
        this.addSetting(this.fallbackMode);
        this.addSetting(this.health);
        this.addSetting(this.checks);
        this.addSetting(this.swordGap);
        this.addSetting(this.forceGap);
        this.addSetting(this.hotbar);
    }
    
    @Override
    public void onUpdate() {
        if (Offhand.mc.field_71439_g == null || Offhand.mc.field_71441_e == null) {
            return;
        }
        Item searching = Items.field_190929_cY;
        if (Offhand.mc.field_71439_g.func_184613_cA() && this.checks.isEnabled() && this.elytraCheck.isEnabled()) {
            return;
        }
        if (Offhand.mc.field_71439_g.field_70143_R > 5.0f && this.checks.isEnabled() && this.fallCheck.isEnabled()) {
            return;
        }
        if (!Main.moduleManager.getModule("CrystalAura").isEnabled() && this.checks.isEnabled() && this.caFunction.isEnabled()) {
            return;
        }
        final String mode = this.mode.getMode();
        switch (mode) {
            case "Crystal": {
                searching = Items.field_185158_cP;
                break;
            }
            case "Gapple": {
                searching = Items.field_151153_ao;
                break;
            }
            case "Bed": {
                searching = Items.field_151104_aV;
                break;
            }
            case "Chorus": {
                searching = Items.field_185161_cS;
                break;
            }
        }
        if (this.health.getValue() > PlayerUtil.getHealth()) {
            searching = Items.field_190929_cY;
        }
        else if (InventoryUtil.getHeldItem(Items.field_151048_u) && this.swordGap.isEnabled()) {
            searching = Items.field_151153_ao;
        }
        else if (this.forceGap.isEnabled() && Mouse.isButtonDown(1)) {
            searching = Items.field_151153_ao;
        }
        if (Offhand.mc.field_71439_g.func_184592_cb().func_77973_b() == searching) {
            return;
        }
        if (Offhand.mc.field_71462_r != null) {
            return;
        }
        if (InventoryUtil.getInventoryItemSlot(searching, !this.hotbar.isEnabled()) != -1) {
            InventoryUtil.moveItemToOffhand(InventoryUtil.getInventoryItemSlot(searching, this.hotbar.isEnabled()));
            return;
        }
        final String mode2 = this.fallbackMode.getMode();
        switch (mode2) {
            case "Crystal": {
                searching = Items.field_185158_cP;
                break;
            }
            case "Gapple": {
                searching = Items.field_151153_ao;
                break;
            }
            case "Bed": {
                searching = Items.field_151104_aV;
                break;
            }
            case "Chorus": {
                searching = Items.field_185161_cS;
                break;
            }
            case "Totem": {
                searching = Items.field_190929_cY;
                break;
            }
        }
        if (Offhand.mc.field_71439_g.func_184592_cb().func_77973_b() == searching) {
            return;
        }
        if (InventoryUtil.getInventoryItemSlot(searching, !this.hotbar.isEnabled()) != -1) {
            InventoryUtil.moveItemToOffhand(InventoryUtil.getInventoryItemSlot(searching, this.hotbar.isEnabled()));
        }
    }
}
