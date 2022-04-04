// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.util.render.ColorUtil;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.Minecraft;
import mod.hitlerhax.setting.settings.BooleanSetting;
import net.minecraft.client.gui.ScaledResolution;
import mod.hitlerhax.Main;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import mod.hitlerhax.util.Globals;
import net.minecraft.client.gui.Gui;

public class HudFPS extends Gui implements Globals
{
    @SubscribeEvent
    public void renderOverlay(final RenderGameOverlayEvent event) {
        if (Main.moduleManager.getModule("Hud").toggled) {
            final ScaledResolution sr = new ScaledResolution(HudFPS.mc);
            if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "FPS")).enabled) {
                final String fps = "FPS: " + Minecraft.func_175610_ah();
                final boolean isChatOpen = HudFPS.mc.field_71462_r instanceof GuiChat;
                final int heightFPS = isChatOpen ? (sr.func_78328_b() - 35) : (sr.func_78328_b() - 20);
                FontUtils.drawStringWithShadow(fps, 2, heightFPS, ColorUtil.getRainbow(300, 255));
            }
        }
    }
}
