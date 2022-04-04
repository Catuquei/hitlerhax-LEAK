// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui;

import mod.hitlerhax.util.render.RenderUtil;
import java.awt.Color;
import mod.hitlerhax.util.font.FontUtils;
import mod.hitlerhax.util.render.ColorUtil;
import net.minecraft.client.Minecraft;
import mod.hitlerhax.util.Globals;
import net.minecraft.client.gui.GuiButton;
import mod.hitlerhax.Main;
import mod.hitlerhax.setting.settings.BooleanSetting;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiWorldSelection;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import java.util.Random;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class HtlrSplashScreen extends GuiScreen
{
    private final ArrayList<ResourceLocation> backgrounds;
    private ResourceLocation background;
    private int x;
    private int y;
    private float tempSound;
    
    public HtlrSplashScreen() {
        this.backgrounds = new ArrayList<ResourceLocation>();
    }
    
    public void func_146281_b() {
        this.field_146297_k.field_71474_y.func_186712_a(SoundCategory.MUSIC, this.tempSound);
    }
    
    public void func_73866_w_() {
        this.tempSound = this.field_146297_k.field_71474_y.func_186711_a(SoundCategory.MUSIC);
        this.field_146297_k.field_71474_y.func_186712_a(SoundCategory.MUSIC, 0.0f);
        this.backgrounds.add(new ResourceLocation("textures/drippler_wave.png"));
        this.backgrounds.add(new ResourceLocation("textures/krieg.jpg"));
        final Random random = new Random();
        this.background = this.backgrounds.get(random.nextInt(this.backgrounds.size()));
        this.field_146297_k.field_71474_y.field_74352_v = false;
        this.field_146297_k.field_71474_y.field_74350_i = 200;
        this.playMusic();
        this.x = this.field_146294_l / 32;
        this.y = this.field_146295_m / 32 + 10;
        this.field_146292_n.add(new SplashScreenButton(0, this.x, this.y, "Singleplayer"));
        this.field_146292_n.add(new SplashScreenButton(1, this.x, this.y + 22, "Multiplayer"));
        this.field_146292_n.add(new SplashScreenButton(2, this.x, this.y + 44, "Settings"));
        this.field_146292_n.add(new SplashScreenButton(2, this.x, this.y + 66, "Exit"));
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179103_j(7425);
        GlStateManager.func_179103_j(7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
    }
    
    public static void drawCompleteImage(final float posX, final float posY, final float width, final float height) {
        GL11.glPushMatrix();
        GL11.glTranslatef(posX, posY, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(width, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(width, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public static boolean isHovered(final int x, final int y, final int width, final int height, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY < y + height;
    }
    
    public void func_73864_a(final int mouseX, final int mouseY, final int mouseButton) {
        if (isHovered(this.x, this.y, this.field_146297_k.field_71466_p.func_78256_a("Singleplayer"), this.field_146297_k.field_71466_p.field_78288_b, mouseX, mouseY)) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiWorldSelection((GuiScreen)this));
        }
        else if (isHovered(this.x, this.y + 22, this.field_146297_k.field_71466_p.func_78256_a("Multiplayer"), this.field_146297_k.field_71466_p.field_78288_b, mouseX, mouseY)) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiMultiplayer((GuiScreen)this));
        }
        else if (isHovered(this.x, this.y + 44, this.field_146297_k.field_71466_p.func_78256_a("Settings"), this.field_146297_k.field_71466_p.field_78288_b, mouseX, mouseY)) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiOptions((GuiScreen)this, this.field_146297_k.field_71474_y));
        }
        else if (isHovered(this.x, this.y + 66, this.field_146297_k.field_71466_p.func_78256_a("Exit"), this.field_146297_k.field_71466_p.field_78288_b, mouseX, mouseY)) {
            this.field_146297_k.func_71400_g();
        }
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        this.x = this.field_146294_l / 32;
        this.y = this.field_146295_m / 32 + 10;
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        this.field_146297_k.func_110434_K().func_110577_a(this.background);
        drawCompleteImage(0.0f, 0.0f, (float)this.field_146294_l, (float)this.field_146295_m);
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }
    
    public void playMusic() {
        this.field_146297_k.field_147127_av.func_147690_c();
        if (((BooleanSetting)Main.settingManager.getSettingByName(Main.moduleManager.getModule("Hud"), "Menu")).enabled) {
            this.field_146297_k.field_147127_av.func_147682_a(Main.songManager.getMenuSong());
        }
    }
    
    private static class SplashScreenButton extends GuiButton implements Globals
    {
        public SplashScreenButton(final int buttonId, final int x, final int y, final String buttonText) {
            super(buttonId, x, y, SplashScreenButton.mc.field_71466_p.func_78256_a(buttonText), SplashScreenButton.mc.field_71466_p.field_78288_b, buttonText);
        }
        
        public void func_191745_a(final Minecraft mc, final int mouseX, final int mouseY, final float partialTicks) {
            if (this.field_146125_m) {
                this.field_146124_l = true;
                this.field_146123_n = (mouseX >= (float)this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
                FontUtils.drawStringWithShadow(this.field_146126_j, this.field_146128_h + 1, this.field_146129_i, new ColorUtil(255, 255, 255, 255));
                if (this.field_146123_n) {
                    RenderUtil.drawLine(this.field_146128_h - 5.0f, (float)(this.field_146129_i + 2 + mc.field_71466_p.field_78288_b), this.field_146128_h + 5.0f + mc.field_71466_p.func_78256_a(this.field_146126_j), (float)(this.field_146129_i + 2 + mc.field_71466_p.field_78288_b), 2.0f, Color.BLUE.getRGB());
                }
            }
        }
        
        public boolean func_146116_c(final Minecraft mc, final int mouseX, final int mouseY) {
            return this.field_146124_l && this.field_146125_m && mouseX >= this.field_146128_h - mc.field_71466_p.func_78256_a(this.field_146126_j) / 2.0f && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
        }
    }
}
