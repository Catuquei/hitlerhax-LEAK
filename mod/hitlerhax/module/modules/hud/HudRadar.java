// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.hud;

import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.Main;
import mod.hitlerhax.setting.settings.IntSetting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import java.util.ArrayList;
import mod.hitlerhax.util.Globals;

public class HudRadar implements Globals
{
    private ArrayList<Blip> blips;
    private int radius;
    
    public HudRadar() {
        this.blips = new ArrayList<Blip>();
    }
    
    @SubscribeEvent
    public void renderOverlay(final RenderGameOverlayEvent event) {
        this.radius = ((IntSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "Radar Radius")).value;
        if (Main.moduleManager.getModule("Hud").toggled && event.getType() == RenderGameOverlayEvent.ElementType.TEXT && ((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "Radar")).enabled) {
            GL11.glDisable(2884);
            GL11.glDisable(3553);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
            GL11.glLineWidth(3.0f);
            GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.5f);
            GL11.glBegin(9);
            for (int ii = 0; ii < 100; ++ii) {
                final float theta = 6.283185f * ii / 100.0f;
                final float x = (float)(this.radius * Math.cos(theta));
                final float y = (float)(this.radius * Math.sin(theta));
                GL11.glVertex2f(x + this.radius + 10.0f, y + this.radius + 30.0f);
            }
            GL11.glEnd();
            GL11.glColor3f(0.6f, 1.0f, 0.4f);
            GL11.glBegin(2);
            for (int ii = 0; ii < 100; ++ii) {
                final float theta = 6.283185f * ii / 100.0f;
                final float x = (float)(this.radius * Math.cos(theta));
                final float y = (float)(this.radius * Math.sin(theta));
                GL11.glVertex2f(x + this.radius + 10.0f, y + this.radius + 30.0f);
            }
            GL11.glEnd();
            GL11.glLineWidth(1.0f);
            GL11.glBegin(2);
            for (int ii = 0; ii < 36; ++ii) {
                final float theta = 6.283185f * ii / 36.0f;
                final float x = (float)(this.radius * 0.75 * Math.cos(theta));
                final float y = (float)(this.radius * 0.75 * Math.sin(theta));
                GL11.glVertex2f(x + this.radius + 10.0f, y + this.radius + 30.0f);
            }
            GL11.glEnd();
            GL11.glBegin(2);
            for (int ii = 0; ii < 36; ++ii) {
                final float theta = 6.283185f * ii / 36.0f;
                final float x = (float)(this.radius * 0.5 * Math.cos(theta));
                final float y = (float)(this.radius * 0.5 * Math.sin(theta));
                GL11.glVertex2f(x + this.radius + 10.0f, y + this.radius + 30.0f);
            }
            GL11.glEnd();
            GL11.glBegin(2);
            for (int ii = 0; ii < 36; ++ii) {
                final float theta = 6.283185f * ii / 36.0f;
                final float x = (float)(this.radius * 0.25 * Math.cos(theta));
                final float y = (float)(this.radius * 0.25 * Math.sin(theta));
                GL11.glVertex2f(x + this.radius + 10.0f, y + this.radius + 30.0f);
            }
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glVertex2f(10.0f, (float)(this.radius + 30));
            GL11.glVertex2f((float)(this.radius * 2 + 10), (float)(this.radius + 30));
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glVertex2f((float)(this.radius + 10), 30.0f);
            GL11.glVertex2f((float)(this.radius + 10), (float)(this.radius * 2 + 30));
            GL11.glEnd();
            final int rotation = (int)(System.currentTimeMillis() % 2880L) / 8;
            GL11.glColor4f(0.6f, 1.0f, 0.4f, 1.0f);
            GL11.glBegin(1);
            GL11.glVertex2f(this.radius + 10 + (float)(this.radius * Math.cos((rotation - 6) * 0.017453292f)), this.radius + 30 + (float)(this.radius * Math.sin((rotation - 6) * 0.017453292f)));
            GL11.glVertex2f((float)(this.radius + 10), (float)(this.radius + 30));
            GL11.glEnd();
            GL11.glBegin(6);
            GL11.glColor4f(0.6f, 1.0f, 0.4f, 0.7f);
            GL11.glVertex2f((float)(this.radius + 10), (float)(this.radius + 30));
            for (float i = 0.7f; i > 0.0f; i -= 0.05f) {
                final int newRot = rotation - (int)((1.0f - i) * 20.0f);
                GL11.glColor4f(0.6f, 1.0f, 0.4f, i);
                GL11.glVertex2f(this.radius + 10 + (float)(this.radius * Math.cos(newRot * 0.017453292f)), this.radius + 30 + (float)(this.radius * Math.sin(newRot * 0.017453292f)));
            }
            GL11.glEnd();
            ArrayList<Blip> newBlips = (ArrayList<Blip>)this.blips.clone();
            for (final Blip b : this.blips) {
                if (System.currentTimeMillis() - b.creationTimeMS > 10000L) {
                    newBlips.remove(b);
                }
            }
            this.blips = newBlips;
            for (final EntityPlayer e : HudRadar.mc.field_71441_e.field_73010_i) {
                if (e != HudRadar.mc.field_71439_g) {
                    int posX = (int)(e.field_70165_t - HudRadar.mc.field_71439_g.field_70165_t);
                    int posZ = (int)(e.field_70161_v - HudRadar.mc.field_71439_g.field_70161_v);
                    posX *= 2;
                    posZ *= 2;
                    if (rotation - 6 <= (int)((Math.toDegrees(Math.atan2(posZ, posX)) > 360.0) ? (Math.toDegrees(Math.atan2(posZ, posX)) - 360 * (int)(Math.toDegrees(Math.atan2(posZ, posX)) / 360.0)) : ((Math.toDegrees(Math.atan2(posZ, posX)) < 0.0) ? (Math.toDegrees(Math.atan2(posZ, posX)) + ((int)(Math.toDegrees(Math.atan2(posZ, posX)) / 360.0) + 1) * 360) : Math.toDegrees(Math.atan2(posZ, posX)))) || rotation - 20 >= (int)((Math.toDegrees(Math.atan2(posZ, posX)) > 360.0) ? (Math.toDegrees(Math.atan2(posZ, posX)) - 360 * (int)(Math.toDegrees(Math.atan2(posZ, posX)) / 360.0)) : ((Math.toDegrees(Math.atan2(posZ, posX)) < 0.0) ? (Math.toDegrees(Math.atan2(posZ, posX)) + ((int)(Math.toDegrees(Math.atan2(posZ, posX)) / 360.0) + 1) * 360) : Math.toDegrees(Math.atan2(posZ, posX))))) {
                        continue;
                    }
                    newBlips = (ArrayList<Blip>)this.blips.clone();
                    for (final Blip b2 : this.blips) {
                        if (e.func_70005_c_().equals(b2.name)) {
                            newBlips.remove(b2);
                        }
                    }
                    this.blips = newBlips;
                    if (posX * posX + posZ * posZ > (this.radius - 25) * (this.radius - 25)) {
                        posX = 100000;
                    }
                    this.blips.add(new Blip(e.func_70005_c_(), posX, posZ));
                }
            }
            for (final Blip b : this.blips) {
                b.render();
            }
            GL11.glEnable(2884);
            GL11.glEnable(3553);
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
        }
    }
    
    private class Blip
    {
        public long creationTimeMS;
        public String name;
        public int x;
        public int z;
        
        public Blip(final String name, final int x, final int z) {
            this.name = name;
            this.x = x;
            this.z = z;
            this.creationTimeMS = System.currentTimeMillis();
        }
        
        public void render() {
            final float transparency = (2500.0f - (System.currentTimeMillis() - this.creationTimeMS)) / 2500.0f;
            GlStateManager.func_179090_x();
            GlStateManager.func_179147_l();
            GlStateManager.func_179118_c();
            GlStateManager.func_179103_j(7425);
            GlStateManager.func_179103_j(7424);
            GlStateManager.func_179084_k();
            GlStateManager.func_179141_d();
            GlStateManager.func_179098_w();
            GL11.glDisable(2884);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
            final ResourceLocation tex = new ResourceLocation("textures/jew.png");
            Globals.mc.func_110434_K().func_110577_a(tex);
            GL11.glPushMatrix();
            GL11.glBegin(7);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, transparency);
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex2f((float)(HudRadar.this.radius + 10 + this.x - 25), (float)(HudRadar.this.radius + 30 + this.z - 25));
            GL11.glTexCoord2f(1.0f, 0.0f);
            GL11.glVertex2f((float)(HudRadar.this.radius + 10 + this.x + 25), (float)(HudRadar.this.radius + 30 + this.z - 25));
            GL11.glTexCoord2f(1.0f, 1.0f);
            GL11.glVertex2f((float)(HudRadar.this.radius + 10 + this.x + 25), (float)(HudRadar.this.radius + 30 + this.z + 25));
            GL11.glTexCoord2f(0.0f, 1.0f);
            GL11.glVertex2f((float)(HudRadar.this.radius + 10 + this.x - 25), (float)(HudRadar.this.radius + 30 + this.z + 25));
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glDisable(3553);
        }
    }
}
