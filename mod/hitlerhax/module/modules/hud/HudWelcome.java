// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.Main;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import mod.hitlerhax.util.Globals;
import net.minecraft.client.gui.Gui;

public class HudWelcome extends Gui implements Globals
{
    @SubscribeEvent
    public void renderOverlay(final RenderGameOverlayEvent event) {
        if (Main.moduleManager.getModule("Hud").toggled && event.getType() == RenderGameOverlayEvent.ElementType.TEXT && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "Welcome")).enabled) {
            FontUtils.drawStringWithShadow("Welcome " + HudWelcome.mc.field_71439_g.func_70005_c_(), 450, 1, ColorUtil.getRainbow(300, 255));
        }
    }
}
