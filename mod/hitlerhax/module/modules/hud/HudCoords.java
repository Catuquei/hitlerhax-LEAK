// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.util.render.ColorUtil;
import net.minecraft.client.gui.GuiChat;
import mod.hitlerhax.util.render.RenderUtil;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import net.minecraft.client.gui.ScaledResolution;
import mod.hitlerhax.Main;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import mod.hitlerhax.util.Globals;
import net.minecraft.client.gui.Gui;

public class HudCoords extends Gui implements Globals
{
    @SubscribeEvent
    public void renderOverlay(final RenderGameOverlayEvent event) {
        if (Main.moduleManager.getModule("Hud").toggled) {
            final ScaledResolution sr = new ScaledResolution(HudCoords.mc);
            if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "Coordinates")).enabled) {
                final double x = HudCoords.mc.field_71439_g.field_70165_t + ((IntSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "XCoord-offset")).value;
                final double y = HudCoords.mc.field_71439_g.field_70163_u;
                final double z = HudCoords.mc.field_71439_g.field_70161_v + ((IntSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "ZCoord-offset")).value;
                String coords;
                if (HudCoords.mc.field_71439_g.field_71093_bK == 0) {
                    coords = String.format("X: %s Y: %s Z: %s [%s, %s, %s]", RenderUtil.DF(x, 1), RenderUtil.DF((int)y, 1), RenderUtil.DF(z, 1), (int)(x / 8.0), (int)y, (int)(z / 8.0));
                }
                else if (HudCoords.mc.field_71439_g.field_71093_bK == -1) {
                    coords = String.format("X: %s Y: %s Z: %s [%s, %s, %s]", RenderUtil.DF(x, 1), RenderUtil.DF((int)y, 1), RenderUtil.DF(z, 1), (int)(x * 8.0), (int)y, (int)(z * 8.0));
                }
                else {
                    coords = String.format("X: %s Y: %s Z: %s", RenderUtil.DF(x, 1), RenderUtil.DF((int)y, 1), RenderUtil.DF(z, 1));
                }
                final boolean isChatOpen = HudCoords.mc.field_71462_r instanceof GuiChat;
                final int heightCoords = isChatOpen ? (sr.func_78328_b() - 25) : (sr.func_78328_b() - 10);
                FontUtils.drawStringWithShadow(coords, 2, heightCoords, ColorUtil.getRainbow(300, 255));
            }
        }
    }
}
