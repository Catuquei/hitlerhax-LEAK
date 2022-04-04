// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.client.gui.FontRenderer;
import mod.hitlerhax.util.render.ColorUtil;
import java.util.ArrayList;
import mod.hitlerhax.setting.settings.BooleanSetting;
import net.minecraft.client.gui.ScaledResolution;
import mod.hitlerhax.Main;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.module.Module;
import java.util.Comparator;
import mod.hitlerhax.util.Globals;
import net.minecraft.client.gui.Gui;

public class HudArrayList extends Gui implements Globals
{
    final Comparator<Module> comparator;
    
    public HudArrayList() {
        final String firstName;
        final String secondName;
        final float dif;
        this.comparator = ((a, b) -> {
            firstName = a.getName();
            secondName = b.getName();
            dif = (float)(FontUtils.getStringWidth(secondName) - FontUtils.getStringWidth(firstName));
            return (dif != 0.0f) ? ((int)dif) : secondName.compareTo(firstName);
        });
    }
    
    @SubscribeEvent
    public void renderOverlay(final RenderGameOverlayEvent.Text event) {
        if (Main.moduleManager.getModule("Hud").toggled) {
            Main.moduleManager.modules.sort(new ModuleComparator());
            final ScaledResolution sr = new ScaledResolution(HudArrayList.mc);
            final FontRenderer fr = HudArrayList.mc.field_71466_p;
            if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "ArrayList")).enabled) {
                int y = 2;
                final ArrayList<Module> modules = new ArrayList<Module>();
                for (final Module mod : Main.moduleManager.getModuleList()) {
                    if (!mod.getName().equalsIgnoreCase("Esp2dHelper") && mod.isToggled() && !mod.getName().equalsIgnoreCase("Hud") && mod.isToggled() && !mod.getName().equalsIgnoreCase("ClientFont") && mod.isToggled()) {
                        modules.add(mod);
                    }
                }
                modules.sort(this.comparator);
                for (final Module m : modules) {
                    FontUtils.drawStringWithShadow(m.getName(), sr.func_78326_a() - FontUtils.getStringWidth(m.getName()) - 2, y, ColorUtil.getRainbow(300, 255));
                    y += fr.field_78288_b;
                }
            }
        }
    }
    
    public static String round(final double num) {
        return Integer.valueOf((int)num).toString() + "." + Integer.valueOf(Math.abs((int)(num % 1.0 * 10.0))).toString();
    }
    
    public static class ModuleComparator implements Comparator<Module>
    {
        @Override
        public int compare(final Module arg0, final Module arg1) {
            if (FontUtils.getStringWidth(arg0.getName()) > FontUtils.getStringWidth(arg1.getName())) {
                return -1;
            }
            if (FontUtils.getStringWidth(arg0.getName()) > FontUtils.getStringWidth(arg1.getName())) {
                return 1;
            }
            return 0;
        }
    }
}
