// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.hud;

import java.util.function.ToIntFunction;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.client.renderer.RenderItem;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.util.font.FontUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.ScaledResolution;
import mod.hitlerhax.Main;
import mod.hitlerhax.setting.settings.BooleanSetting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import mod.hitlerhax.util.Globals;
import net.minecraft.client.gui.Gui;

public class HudArmor extends Gui implements Globals
{
    private int armourCompress;
    private int armourSpacing;
    
    @SubscribeEvent
    public void renderOverlay(final RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "Armor")).enabled) {
            final ScaledResolution resolution = new ScaledResolution(HudArmor.mc);
            final RenderItem itemRender = HudArmor.mc.func_175599_af();
            GlStateManager.func_179098_w();
            final int i = resolution.func_78326_a() / 2;
            int iteration = 0;
            final int y = resolution.func_78328_b() - 55 - (HudArmor.mc.field_71439_g.func_70090_H() ? 10 : 0);
            for (final ItemStack is : HudArmor.mc.field_71439_g.field_71071_by.field_70460_b) {
                ++iteration;
                if (is.func_190926_b()) {
                    continue;
                }
                final int x = i - 90 + (9 - iteration) * this.armourSpacing + this.armourCompress;
                GlStateManager.func_179126_j();
                itemRender.field_77023_b = 200.0f;
                itemRender.func_180450_b(is, x, y);
                itemRender.func_180453_a(HudArmor.mc.field_71466_p, is, x, y, "");
                itemRender.field_77023_b = 0.0f;
                GlStateManager.func_179098_w();
                GlStateManager.func_179140_f();
                GlStateManager.func_179097_i();
                final String s = (is.func_190916_E() > 50) ? (is.func_190916_E() + "") : "";
                FontUtils.drawStringWithShadow(s, x + 19 - 2 - FontUtils.getStringWidth(s), y + 9, new ColorUtil(255, 255, 255, 255));
                if (HudArmor.mc.field_71439_g.func_184812_l_()) {
                    final float green = (is.func_77958_k() - (float)is.func_77952_i()) / is.func_77958_k();
                    final float red = 1.0f - green;
                    final int dmg = 100 - (int)(red * 100.0f);
                    final int y2 = resolution.func_78328_b() - 40 - (HudArmor.mc.field_71439_g.func_70090_H() ? 10 : 0);
                    FontUtils.drawStringWithShadow(dmg + "%", x + 10 - FontUtils.getStringWidth(dmg + "%") / 2, y2 - 9, new ColorUtil(255, 255, 255, 255));
                    this.armourCompress = 2;
                    this.armourSpacing = 20;
                }
                else {
                    final float green = (is.func_77958_k() - (float)is.func_77952_i()) / is.func_77958_k();
                    final float red = 1.0f - green;
                    final int dmg = 100 - (int)(red * 100.0f);
                    FontUtils.drawStringWithShadow(dmg + "%", x + 10 - FontUtils.getStringWidth(dmg + "%") / 2, y - 8, new ColorUtil(255, 255, 255, 255));
                    this.armourCompress = 2;
                    this.armourSpacing = 20;
                    GlStateManager.func_179126_j();
                    GlStateManager.func_179140_f();
                }
            }
        }
    }
    
    int getItemsOffHand(final Item i) {
        return HudArmor.mc.field_71439_g.field_71071_by.field_184439_c.stream().filter(itemStack -> itemStack.func_77973_b() == i).mapToInt(ItemStack::func_190916_E).sum();
    }
}
