// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.FontRenderer;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.Main;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import mod.hitlerhax.util.Globals;
import net.minecraft.client.gui.Gui;

public class Hud extends Gui implements Globals
{
    @SubscribeEvent
    public void renderOverlay(final RenderGameOverlayEvent event) {
        if (Main.moduleManager.getModule("Hud").toggled) {
            final FontRenderer fr = Hud.mc.field_71466_p;
            if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "Watermark")).enabled) {
                fr.func_175063_a("HitlerHax 1.3.1", 2.0f, 1.0f, 10879231);
            }
        }
    }
}
