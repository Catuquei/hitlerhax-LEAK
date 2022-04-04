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

public class HudWatermark
{
    @SubscribeEvent
    public void renderOverlay(final RenderGameOverlayEvent.Text event) {
        if (Main.moduleManager.getModule("Hud").toggled && event.getType() == RenderGameOverlayEvent.ElementType.TEXT && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "Watermark")).enabled) {
            FontUtils.drawStringWithShadow("HitlerHax 1.3.1", 2, 1, new ColorUtil(128, 0, 128, 255));
        }
    }
}
