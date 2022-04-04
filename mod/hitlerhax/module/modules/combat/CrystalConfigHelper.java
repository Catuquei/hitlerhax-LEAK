// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import mod.hitlerhax.Main;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class CrystalConfigHelper extends Module
{
    public final BooleanSetting auto;
    public final ModeSetting server;
    public final IntSetting ping;
    public final BooleanSetting multiplace;
    
    public CrystalConfigHelper() {
        super("CrustalConfig", "Configs crystal aura based on your server", Category.COMBAT);
        this.auto = new BooleanSetting("autoConfig", this, true);
        this.server = new ModeSetting("server", this, "2b2tpvp", new String[] { "2b2tpvp", ".cc", "other" });
        this.ping = new IntSetting("averagePing", this, 40);
        this.multiplace = new BooleanSetting("multiplace", this, false);
        this.addSetting(this.auto, this.server, this.ping, this.multiplace);
    }
    
    public void onEnable() {
    }
    
    @Override
    public void onUpdate() {
        if (this.multiplace.isEnabled()) {
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).multiplace.setEnabled(true);
            if (this.ping.getValue() <= 1) {
                ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).multiplacePlus.setEnabled(false);
            }
            else if (this.ping.getValue() > 1) {
                ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).multiplacePlus.setEnabled(true);
            }
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).highPing.setEnabled(false);
            return;
        }
        if (this.server.is("2b2tpvp")) {
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).rotate.setEnabled(true);
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).spoofRotations.setEnabled(true);
        }
        if (this.server.is(".cc")) {
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).rotate.setEnabled(false);
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).spoofRotations.setEnabled(false);
        }
        if (this.server.is("other")) {
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).rotate.setEnabled(false);
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).spoofRotations.setEnabled(false);
        }
        if (this.ping.getValue() <= 20) {
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).breakType.setMode("swing");
        }
        else if (this.ping.getValue() > 20) {
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).breakType.setMode("packet");
        }
        if (this.ping.getValue() <= 5) {
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).highPing.setEnabled(false);
        }
        else if (this.ping.getValue() > 5) {
            ((CrystalAura)Main.moduleManager.getModule("CrystalAura")).highPing.setEnabled(true);
        }
    }
}
