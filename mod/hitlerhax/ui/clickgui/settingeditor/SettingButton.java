// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui.settingeditor;

import net.minecraft.client.gui.GuiScreen;
import mod.hitlerhax.ui.clickgui.ClickGuiController;
import java.util.Iterator;
import java.util.AbstractMap;
import java.awt.Color;
import mod.hitlerhax.misc.StringParser;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.util.font.FontUtils;
import java.util.Map;
import mod.hitlerhax.ui.clickgui.settingeditor.search.BlockSelectorGuiController;
import mod.hitlerhax.setting.settings.SearchBlockSelectorSetting;
import mod.hitlerhax.setting.settings.ColorSetting;
import mod.hitlerhax.setting.settings.StringSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.ui.guiitems.HtlrTextField;
import mod.hitlerhax.util.Globals;

public class SettingButton implements Globals
{
    private HtlrTextField textField;
    private HtlrTextField cTextFieldRed;
    private HtlrTextField cTextFieldGreen;
    private HtlrTextField cTextFieldBlue;
    final int x;
    int y;
    final int width;
    final int height;
    final Module module;
    final SettingFrame parent;
    final Setting setting;
    ModeSetting mSetting;
    IntSetting iSetting;
    FloatSetting fSetting;
    BooleanSetting bSetting;
    StringSetting sSetting;
    ColorSetting cSetting;
    SearchBlockSelectorSetting blockSetting;
    BlockSelectorGuiController blockController;
    
    public SettingButton(final Module module, final Setting setting, final int x, final int y, final SettingFrame parent) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = parent.width;
        this.height = 14;
        this.setting = setting;
        if (setting instanceof ModeSetting) {
            this.mSetting = (ModeSetting)setting;
        }
        else if (setting instanceof IntSetting) {
            this.iSetting = (IntSetting)setting;
        }
        else if (setting instanceof FloatSetting) {
            this.fSetting = (FloatSetting)setting;
        }
        else if (setting instanceof StringSetting) {
            this.sSetting = (StringSetting)setting;
        }
        else if (setting instanceof BooleanSetting) {
            this.bSetting = (BooleanSetting)setting;
        }
        else if (setting instanceof ColorSetting) {
            this.cSetting = (ColorSetting)setting;
        }
        else if (setting instanceof SearchBlockSelectorSetting) {
            this.blockSetting = (SearchBlockSelectorSetting)setting;
        }
    }
    
    public void draw(final int mouseX, final int mouseY) {
        for (final Map.Entry<HtlrTextField, Module> entry : SettingController.textFields.entrySet()) {
            final HtlrTextField t = entry.getKey();
            if (entry.getValue() == this.module && t.height == FontUtils.getFontHeight() + 2 && t.width == 380 - FontUtils.getStringWidth(this.setting.name + ": ") && t.x == this.x + 2 + FontUtils.getStringWidth(this.setting.name + ": ") && t.y == this.y) {
                this.textField = t;
            }
        }
        for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry2 : SettingController.cTextFields.entrySet()) {
            final HtlrTextField[] t2 = entry2.getKey();
            final HtlrTextField r = t2[0];
            final HtlrTextField g = t2[1];
            final HtlrTextField b = t2[2];
            if (entry2.getValue().getKey() == this.module) {
                if (r.height == FontUtils.getFontHeight() + 2 && r.width == 380 - FontUtils.getStringWidth(this.setting.name + ": ") && r.x == this.x + 2 + FontUtils.getStringWidth(this.setting.name + ": ")) {
                    this.cTextFieldRed = r;
                }
                if (g.height == FontUtils.getFontHeight() + 2 && g.width == 380 - FontUtils.getStringWidth(this.setting.name + ": ") && g.x == this.x + 2 + FontUtils.getStringWidth(this.setting.name + ": ")) {
                    this.cTextFieldGreen = g;
                }
                if (b.height != FontUtils.getFontHeight() + 2 || b.width != 380 - FontUtils.getStringWidth(this.setting.name + ": ") || b.x != this.x + 2 + FontUtils.getStringWidth(this.setting.name + ": ")) {
                    continue;
                }
                this.cTextFieldBlue = b;
            }
        }
        if (this.setting instanceof ModeSetting) {
            FontUtils.drawString(this.mSetting.name + ": " + this.mSetting.getMode(), this.x + 2, this.y + 2, new ColorUtil(255, 255, 255));
        }
        else if (this.setting instanceof IntSetting) {
            FontUtils.drawString(this.iSetting.name + ": ", this.x + 2, this.y + 2, new ColorUtil(255, 255, 255));
            final int text = (int)FontUtils.drawString(Integer.toString(this.iSetting.value), this.x + 2 + FontUtils.getStringWidth(this.iSetting.name + ": "), this.y + 2, new ColorUtil(255, 255, 255));
            if (this.textField == null) {
                (this.textField = new HtlrTextField(text, this.x + 2 + FontUtils.getStringWidth(this.iSetting.name + ": "), this.y, 380 - FontUtils.getStringWidth(this.iSetting.name + ": "), FontUtils.getFontHeight() + 2)).setEnabled(true);
                SettingController.textFields.put(this.textField, this.module);
                this.textField.setText(Integer.toString(this.iSetting.value));
            }
            if (this.textField.isFocused()) {
                if (StringParser.isInteger(this.textField.getText())) {
                    this.iSetting.value = Integer.parseInt(this.textField.getText());
                    this.textField.setTextColor(new Color(0, 255, 0).getRGB());
                }
                else {
                    this.textField.setTextColor(new Color(255, 0, 0).getRGB());
                }
            }
        }
        else if (this.setting instanceof FloatSetting) {
            FontUtils.drawString(this.fSetting.name + ": ", this.x + 2, this.y + 2, new ColorUtil(255, 255, 255));
            final int text = (int)FontUtils.drawString(Float.toString(this.fSetting.value), this.x + 2 + FontUtils.getStringWidth(this.fSetting.name + ": "), this.y + 2, new ColorUtil(255, 255, 255));
            if (this.textField == null) {
                (this.textField = new HtlrTextField(text, this.x + 2 + FontUtils.getStringWidth(this.fSetting.name + ": "), this.y, 380 - FontUtils.getStringWidth(this.fSetting.name + ": "), FontUtils.getFontHeight() + 2)).setEnabled(true);
                SettingController.textFields.put(this.textField, this.module);
                this.textField.setText(Float.toString(this.fSetting.value));
            }
            if (this.textField.isFocused()) {
                if (StringParser.isFloat(this.textField.getText())) {
                    this.fSetting.value = Float.parseFloat(this.textField.getText());
                    this.textField.setTextColor(new Color(0, 255, 0).getRGB());
                }
                else {
                    this.textField.setTextColor(new Color(255, 0, 0).getRGB());
                }
            }
        }
        else if (this.setting instanceof StringSetting) {
            FontUtils.drawString(this.sSetting.name + ": ", this.x + 2, this.y + 2, new ColorUtil(255, 255, 255));
            final int text = (int)FontUtils.drawString(this.sSetting.value, this.x + 2 + FontUtils.getStringWidth(this.sSetting.name + ": "), this.y + 2, new ColorUtil(255, 255, 255));
            if (this.textField == null) {
                (this.textField = new HtlrTextField(text, this.x + 2 + FontUtils.getStringWidth(this.sSetting.name + ": "), this.y, 380 - FontUtils.getStringWidth(this.sSetting.name + ": "), FontUtils.getFontHeight() + 2)).setEnabled(true);
                SettingController.textFields.put(this.textField, this.module);
                this.textField.setText(this.sSetting.value);
            }
            if (this.textField.isFocused()) {
                this.sSetting.value = this.textField.getText();
                this.textField.setTextColor(new Color(0, 255, 0).getRGB());
            }
        }
        else if (this.setting instanceof BooleanSetting) {
            if (this.bSetting.enabled) {
                FontUtils.drawString(this.bSetting.name, this.x + 2, this.y + 2, new ColorUtil(255, 150, 50));
            }
            else {
                FontUtils.drawString(this.bSetting.name, this.x + 2, this.y + 2, new ColorUtil(180, 240, 255));
            }
        }
        else if (this.setting instanceof ColorSetting) {
            FontUtils.drawString(this.cSetting.name + ": ", this.x + 2, this.y + 2, new ColorUtil(this.cSetting.getColor().getRed(), this.cSetting.getColor().getGreen(), this.cSetting.getColor().getBlue()));
            final int textRed = (int)FontUtils.drawString(Integer.toString(this.cSetting.getColor().getRed()), this.x + 2 + FontUtils.getStringWidth(this.cSetting.name + ": "), this.y + 2, new ColorUtil(255, 0, 0));
            final int textGreen = (int)FontUtils.drawString(Integer.toString(this.cSetting.getColor().getGreen()), this.x + 2 + FontUtils.getStringWidth(this.cSetting.name + ": ") + 125, this.y + 2, new ColorUtil(0, 255, 0));
            final int textBlue = (int)FontUtils.drawString(Integer.toString(this.cSetting.getColor().getBlue()), this.x + 2 + FontUtils.getStringWidth(this.cSetting.name + ": ") + 250, this.y + 2, new ColorUtil(0, 0, 255));
            if (this.cTextFieldRed == null && this.cTextFieldGreen == null && this.cTextFieldBlue == null) {
                this.cTextFieldRed = new HtlrTextField(textRed, this.x + 2 + FontUtils.getStringWidth(this.cSetting.name + ": "), this.y, 50, FontUtils.getFontHeight() + 2);
                this.cTextFieldGreen = new HtlrTextField(textGreen, this.x + 2 + FontUtils.getStringWidth(this.cSetting.name + ": ") + 125, this.y, 50, FontUtils.getFontHeight() + 2);
                this.cTextFieldBlue = new HtlrTextField(textBlue, this.x + 2 + FontUtils.getStringWidth(this.cSetting.name + ": ") + 250, this.y, 50, FontUtils.getFontHeight() + 2);
                this.cTextFieldRed.setTextColor(new ColorUtil(255, 0, 0).getRGB());
                this.cTextFieldGreen.setTextColor(new ColorUtil(0, 255, 0).getRGB());
                this.cTextFieldBlue.setTextColor(new ColorUtil(0, 0, 255).getRGB());
                this.cTextFieldRed.setEnabled(true);
                this.cTextFieldGreen.setEnabled(true);
                this.cTextFieldBlue.setEnabled(true);
                final HtlrTextField[] array = { this.cTextFieldRed, this.cTextFieldGreen, this.cTextFieldBlue };
                SettingController.cTextFields.put(array, new AbstractMap.SimpleEntry<Module, Setting>(this.module, this.setting));
                this.cTextFieldRed.setText(Integer.toString(this.cSetting.getColor().getRed()));
                this.cTextFieldGreen.setText(Integer.toString(this.cSetting.getColor().getGreen()));
                this.cTextFieldBlue.setText(Integer.toString(this.cSetting.getColor().getBlue()));
                if (this.cTextFieldRed.isFocused() && StringParser.isInteger(this.cTextFieldRed.getText()) && Integer.parseInt(this.cTextFieldRed.getText()) <= 255 && Integer.parseInt(this.cTextFieldRed.getText()) >= 0) {
                    this.cSetting.setColor(new ColorUtil(Integer.parseInt(this.cTextFieldRed.getText()), this.cSetting.getColor().getGreen(), this.cSetting.getColor().getBlue()));
                }
                if (this.cTextFieldGreen.isFocused() && StringParser.isInteger(this.cTextFieldGreen.getText()) && Integer.parseInt(this.cTextFieldGreen.getText()) <= 255 && Integer.parseInt(this.cTextFieldGreen.getText()) >= 0) {
                    this.cSetting.setColor(new ColorUtil(this.cSetting.getColor().getRed(), Integer.parseInt(this.cTextFieldGreen.getText()), this.cSetting.getColor().getBlue()));
                }
                if (this.cTextFieldBlue.isFocused() && StringParser.isInteger(this.cTextFieldBlue.getText()) && Integer.parseInt(this.cTextFieldBlue.getText()) <= 255 && Integer.parseInt(this.cTextFieldBlue.getText()) >= 0) {
                    this.cSetting.setColor(new ColorUtil(this.cSetting.getColor().getRed(), this.cSetting.getColor().getGreen(), Integer.parseInt(this.cTextFieldBlue.getText())));
                }
            }
        }
        else if (this.setting instanceof SearchBlockSelectorSetting) {
            FontUtils.drawString(this.blockSetting.name, this.x + 2, this.y + 2, new ColorUtil(255, 255, 255));
        }
    }
    
    public void onClick(final int x, final int y, final int button) {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height) {
            if (this.setting instanceof ModeSetting) {
                this.mSetting.cycle();
                ClickGuiController.INSTANCE.settingController.refresh(false);
            }
            else if (this.setting instanceof BooleanSetting) {
                if (y >= this.y - 5 && y <= this.y + this.height - 5 && x <= this.x + FontUtils.getStringWidth(this.bSetting.name)) {
                    this.bSetting.setEnabled(!this.bSetting.enabled);
                }
                ClickGuiController.INSTANCE.settingController.refresh(false);
            }
            else if (this.setting instanceof ColorSetting) {
                FontUtils.drawString(this.cSetting.name + ": ", x + 2, y + 2, new ColorUtil(this.cSetting.getColor().getRed(), this.cSetting.getColor().getGreen(), this.cSetting.getColor().getBlue()));
            }
            else if (this.setting instanceof SearchBlockSelectorSetting) {
                final GuiScreen last = SettingButton.mc.field_71462_r;
                assert SettingButton.mc.field_71462_r != null;
                SettingButton.mc.field_71462_r.func_146281_b();
                this.blockController = new BlockSelectorGuiController(last, this.blockSetting.colorSettings, this.blockSetting);
                SettingButton.mc.func_147108_a((GuiScreen)this.blockController);
            }
        }
        else {
            if (this.textField != null && this.textField.isFocused()) {
                this.textField.setTextColor(new Color(255, 255, 255).getRGB());
                this.textField.setFocused(false);
            }
            if (this.textField != null) {
                this.textField.setTextColor(new Color(255, 255, 255).getRGB());
                if (this.setting instanceof IntSetting) {
                    this.textField.setText(Integer.toString(this.iSetting.value));
                }
                else if (this.setting instanceof FloatSetting) {
                    this.textField.setText(Float.toString(this.fSetting.value));
                }
                else if (this.setting instanceof StringSetting) {
                    this.textField.setText(this.sSetting.value);
                }
            }
        }
        if (this.cTextFieldRed != null && x <= this.cTextFieldRed.x && x >= this.cTextFieldRed.x + this.cTextFieldRed.width && y <= this.y && y >= this.y + this.height) {
            this.cTextFieldRed.setFocused(false);
            this.cTextFieldRed.setText(Integer.toString(this.cSetting.getColor().getRed()));
        }
        else {
            for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry : SettingController.cTextFields.entrySet()) {
                final HtlrTextField r = entry.getKey()[0];
                final HtlrTextField g = entry.getKey()[1];
                final HtlrTextField b = entry.getKey()[2];
                if (r != this.cTextFieldRed) {
                    r.setFocused(false);
                }
                g.setFocused(false);
                b.setFocused(false);
            }
        }
        if (this.cTextFieldGreen != null && x <= this.cTextFieldGreen.x && x >= this.cTextFieldGreen.x + this.cTextFieldGreen.width && y <= this.y && y >= this.y + this.height) {
            this.cTextFieldGreen.setFocused(false);
            this.cTextFieldGreen.setText(Integer.toString(this.cSetting.getColor().getGreen()));
        }
        else {
            for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry : SettingController.cTextFields.entrySet()) {
                final HtlrTextField r = entry.getKey()[0];
                final HtlrTextField g = entry.getKey()[1];
                final HtlrTextField b = entry.getKey()[2];
                r.setFocused(false);
                if (g != this.cTextFieldGreen) {
                    g.setFocused(false);
                }
                b.setFocused(false);
            }
        }
        if (this.cTextFieldBlue != null && x <= this.cTextFieldBlue.x && x >= this.cTextFieldBlue.x + this.cTextFieldBlue.width && y <= this.y && y >= this.y + this.height) {
            this.cTextFieldBlue.setFocused(false);
            this.cTextFieldBlue.setText(Integer.toString(this.cSetting.getColor().getBlue()));
        }
        else {
            for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry : SettingController.cTextFields.entrySet()) {
                final HtlrTextField r = entry.getKey()[0];
                final HtlrTextField g = entry.getKey()[1];
                final HtlrTextField b = entry.getKey()[2];
                r.setFocused(false);
                g.setFocused(false);
                if (b != this.cTextFieldBlue) {
                    b.setFocused(false);
                }
            }
        }
        ClickGuiController.INSTANCE.settingController.refresh(false);
    }
}
