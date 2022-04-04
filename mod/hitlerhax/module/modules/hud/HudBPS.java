// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.util.render.ColorUtil;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.math.MathHelper;
import mod.hitlerhax.setting.settings.BooleanSetting;
import net.minecraft.client.gui.ScaledResolution;
import mod.hitlerhax.Main;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import java.text.DecimalFormat;
import mod.hitlerhax.util.Timer;
import mod.hitlerhax.util.Globals;

public class HudBPS implements Globals
{
    private final Timer timer;
    private double prevPosX;
    private double prevPosZ;
    final DecimalFormat formatter;
    
    public HudBPS() {
        this.timer = new Timer();
        this.formatter = new DecimalFormat("#.#");
    }
    
    @SubscribeEvent
    public void renderOverlay(final RenderGameOverlayEvent event) {
        if (Main.moduleManager.getModule("Hud").toggled) {
            final ScaledResolution sr = new ScaledResolution(HudBPS.mc);
            if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "BPS")).enabled) {
                if (this.timer.getPassedMillis(1000L)) {
                    this.prevPosX = HudBPS.mc.field_71439_g.field_70169_q;
                    this.prevPosZ = HudBPS.mc.field_71439_g.field_70166_s;
                }
                String bps = this.formatter.format(Math.floor(MathHelper.func_76133_a((HudBPS.mc.field_71439_g.field_70165_t - this.prevPosX) * (HudBPS.mc.field_71439_g.field_70165_t - this.prevPosX) + (HudBPS.mc.field_71439_g.field_70161_v - this.prevPosZ) * (HudBPS.mc.field_71439_g.field_70161_v - this.prevPosZ)) / 1000.0f / 1.3888889E-5f));
                bps += " km/h";
                final boolean isChatOpen = HudBPS.mc.field_71462_r instanceof GuiChat;
                final int heightBPS = isChatOpen ? (sr.func_78328_b() - 45) : (sr.func_78328_b() - 30);
                FontUtils.drawStringWithShadow(bps, 2, heightBPS, ColorUtil.getRainbow(300, 255));
            }
        }
    }
}
