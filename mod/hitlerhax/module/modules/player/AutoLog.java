// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.player;

import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.WorldClient;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class AutoLog extends Module
{
    final IntSetting health;
    
    public AutoLog() {
        super("AutoLog", "Logs on Low Health", Category.PLAYER);
        this.health = new IntSetting("Health", this, 10);
        this.addSetting(this.health);
    }
    
    @Override
    public void onUpdate() {
        if (!AutoLog.mc.func_71356_B() && AutoLog.mc.field_71439_g.func_110143_aJ() < this.health.value) {
            final boolean flag = AutoLog.mc.func_71387_A();
            final boolean flag2 = AutoLog.mc.func_181540_al();
            AutoLog.mc.field_71441_e.func_72882_A();
            AutoLog.mc.func_71403_a((WorldClient)null);
            if (flag) {
                AutoLog.mc.func_147108_a((GuiScreen)new GuiMainMenu());
            }
            else if (flag2) {
                final RealmsBridge realmsbridge = new RealmsBridge();
                realmsbridge.switchToRealms((GuiScreen)new GuiMainMenu());
            }
            else {
                AutoLog.mc.func_147108_a((GuiScreen)new GuiMultiplayer((GuiScreen)new GuiMainMenu()));
            }
        }
    }
}
