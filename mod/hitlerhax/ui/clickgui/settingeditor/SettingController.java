// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.clickgui.settingeditor;

import java.util.HashMap;
import org.lwjgl.input.Mouse;
import mod.hitlerhax.Main;
import mod.hitlerhax.misc.StringParser;
import mod.hitlerhax.setting.settings.ColorSetting;
import java.io.IOException;
import java.awt.Color;
import mod.hitlerhax.ui.guiitems.HtlrButton;
import java.util.Iterator;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.ui.clickgui.ModuleButton;
import mod.hitlerhax.ui.clickgui.ClickGuiController;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.ui.guiitems.HtlrTextField;
import java.util.Map;
import net.minecraft.client.gui.GuiScreen;

public class SettingController extends GuiScreen
{
    private int scrollOffset;
    public static final Map<HtlrTextField, Module> textFields;
    public static final Map<HtlrTextField[], Map.Entry<Module, Setting>> cTextFields;
    public SettingFrame frame;
    public Module module;
    
    public SettingController(final int mouseX, final int mouseY) {
        this.scrollOffset = 0;
        for (final ModuleButton m : ClickGuiController.getButtons()) {
            if (mouseX >= m.x && mouseX <= m.x + m.width && mouseY >= m.y && mouseY <= m.y + m.height) {
                this.module = m.module;
            }
        }
        if (this.module != null) {
            this.frame = new SettingFrame(this.module, 20, 20, new ColorUtil(255, 255, 255));
        }
    }
    
    public void refresh(final boolean kbClicked) {
        if (kbClicked) {
            this.frame = new SettingFrame(this.module, 20, 20, new ColorUtil(255, 0, 0));
        }
        else {
            this.frame = new SettingFrame(this.module, 20, 20, new ColorUtil(255, 255, 255));
        }
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.scroller();
        if (this.frame == null) {
            this.func_146281_b();
            return;
        }
        this.frame.render(mouseX, mouseY);
        for (final HtlrButton g : this.frame.module.buttons) {
            g.visible = true;
            g.enabled = true;
        }
        for (final Map.Entry<HtlrTextField, Module> entry : SettingController.textFields.entrySet()) {
            final HtlrTextField textField = entry.getKey();
            if (entry.getValue() == this.frame.module) {
                textField.drawTextBox();
            }
        }
        for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry2 : SettingController.cTextFields.entrySet()) {
            final HtlrTextField cTextFieldRed = entry2.getKey()[0];
            final HtlrTextField cTextFieldGreen = entry2.getKey()[1];
            final HtlrTextField cTextFieldBlue = entry2.getKey()[2];
            if (entry2.getValue().getKey() == this.frame.module) {
                cTextFieldRed.drawTextBox();
                cTextFieldGreen.drawTextBox();
                cTextFieldBlue.drawTextBox();
            }
        }
    }
    
    public boolean func_73868_f() {
        super.func_73868_f();
        return false;
    }
    
    protected void func_73869_a(final char typedChar, final int keyCode) throws IOException {
        if (this.frame == null) {
            return;
        }
        super.func_73869_a(typedChar, keyCode);
        if (keyCode == 1) {
            for (final HtlrButton g : this.frame.module.buttons) {
                g.visible = false;
                g.enabled = false;
            }
            for (final Map.Entry<HtlrTextField, Module> entry : SettingController.textFields.entrySet()) {
                final HtlrTextField textField = entry.getKey();
                if (entry.getValue() == this.frame.module) {
                    final HtlrTextField htlrTextField = textField;
                    htlrTextField.y -= this.scrollOffset;
                }
            }
            final SettingFrame frame = this.frame;
            frame.y -= this.scrollOffset;
            for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry2 : SettingController.cTextFields.entrySet()) {
                final HtlrTextField cTextFieldRed = entry2.getKey()[0];
                final HtlrTextField cTextFieldGreen = entry2.getKey()[1];
                final HtlrTextField cTextFieldBlue = entry2.getKey()[2];
                if (entry2.getValue().getKey() == this.frame.module) {
                    final HtlrTextField htlrTextField2 = cTextFieldRed;
                    htlrTextField2.y -= this.scrollOffset;
                    final HtlrTextField htlrTextField3 = cTextFieldGreen;
                    htlrTextField3.y -= this.scrollOffset;
                    final HtlrTextField htlrTextField4 = cTextFieldBlue;
                    htlrTextField4.y -= this.scrollOffset;
                }
            }
            final KeybindButton kbButton = this.frame.kbButton;
            kbButton.y -= this.scrollOffset;
            for (final SettingButton settingButton : this.frame.settingButtons) {
                final SettingButton s = settingButton;
                settingButton.y -= this.scrollOffset;
            }
            this.scrollOffset = 0;
            for (final HtlrTextField t : SettingController.textFields.keySet()) {
                t.setFocused(false);
                t.setTextColor(new Color(255, 255, 255).getRGB());
            }
            this.field_146297_k.func_147108_a((GuiScreen)ClickGuiController.INSTANCE);
            if (this.field_146297_k.field_71462_r == null) {
                this.field_146297_k.func_71381_h();
            }
        }
        for (final Map.Entry<HtlrTextField, Module> entry : SettingController.textFields.entrySet()) {
            final HtlrTextField textField = entry.getKey();
            if (entry.getValue() == this.frame.module) {
                textField.textboxKeyTyped(typedChar, keyCode);
            }
        }
        for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry2 : SettingController.cTextFields.entrySet()) {
            final HtlrTextField cTextFieldRed = entry2.getKey()[0];
            final HtlrTextField cTextFieldGreen = entry2.getKey()[1];
            final HtlrTextField cTextFieldBlue = entry2.getKey()[2];
            if (entry2.getValue().getKey() == this.frame.module) {
                cTextFieldRed.textboxKeyTyped(typedChar, keyCode);
                cTextFieldGreen.textboxKeyTyped(typedChar, keyCode);
                cTextFieldBlue.textboxKeyTyped(typedChar, keyCode);
            }
        }
    }
    
    protected void func_73864_a(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (this.frame == null) {
            return;
        }
        if (mouseButton == 0) {
            this.frame.onClick(mouseX, mouseY, mouseButton);
            for (final HtlrButton htlrButton : this.frame.module.buttons) {
                final HtlrButton g = htlrButton;
                htlrButton.y += this.scrollOffset;
                if (mouseX >= g.x && mouseX < g.x + g.width && mouseY >= g.y && mouseY < g.y + g.height) {
                    this.module.actionPerformed(g);
                }
            }
            final SettingFrame frame = this.frame;
            frame.y += this.scrollOffset;
            for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry : SettingController.cTextFields.entrySet()) {
                final HtlrTextField cTextFieldRed = entry.getKey()[0];
                final HtlrTextField cTextFieldGreen = entry.getKey()[1];
                final HtlrTextField cTextFieldBlue = entry.getKey()[2];
                if (entry.getValue().getKey() == this.frame.module) {
                    final HtlrTextField htlrTextField = cTextFieldRed;
                    htlrTextField.y += this.scrollOffset;
                    final HtlrTextField htlrTextField2 = cTextFieldGreen;
                    htlrTextField2.y += this.scrollOffset;
                    final HtlrTextField htlrTextField3 = cTextFieldBlue;
                    htlrTextField3.y += this.scrollOffset;
                }
            }
            final KeybindButton kbButton = this.frame.kbButton;
            kbButton.y += this.scrollOffset;
            for (final SettingButton settingButton : this.frame.settingButtons) {
                final SettingButton s = settingButton;
                settingButton.y += this.scrollOffset;
            }
        }
        for (final Map.Entry<HtlrTextField, Module> entry2 : SettingController.textFields.entrySet()) {
            final HtlrTextField textField = entry2.getKey();
            if (entry2.getValue() == this.frame.module && mouseX >= textField.x && mouseX < textField.x + textField.width && mouseY >= textField.y && mouseY < textField.y + textField.height) {
                textField.setFocused(true);
            }
            textField.mouseClicked(mouseX, mouseY, mouseButton);
        }
        for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry : SettingController.cTextFields.entrySet()) {
            final HtlrTextField cTextFieldRed = entry.getKey()[0];
            final HtlrTextField cTextFieldGreen = entry.getKey()[1];
            final HtlrTextField cTextFieldBlue = entry.getKey()[2];
            final ColorSetting cSetting = entry.getValue().getValue();
            if (!StringParser.isInteger(cTextFieldRed.getText()) || Integer.parseInt(cTextFieldRed.getText()) > 255 || Integer.parseInt(cTextFieldRed.getText()) < 0) {
                cTextFieldRed.setText(Integer.toString(cSetting.getColor().getRed()));
            }
            else {
                cSetting.setColor(new ColorUtil(Integer.parseInt(cTextFieldRed.getText()), cSetting.getColor().getGreen(), cSetting.getColor().getBlue()));
            }
            if (!StringParser.isInteger(cTextFieldGreen.getText()) || Integer.parseInt(cTextFieldGreen.getText()) > 255 || Integer.parseInt(cTextFieldGreen.getText()) < 0) {
                cTextFieldGreen.setText(Integer.toString(cSetting.getColor().getGreen()));
            }
            else {
                cSetting.setColor(new ColorUtil(cSetting.getColor().getRed(), Integer.parseInt(cTextFieldGreen.getText()), cSetting.getColor().getBlue()));
            }
            if (!StringParser.isInteger(cTextFieldBlue.getText()) || Integer.parseInt(cTextFieldBlue.getText()) > 255 || Integer.parseInt(cTextFieldBlue.getText()) < 0) {
                cTextFieldBlue.setText(Integer.toString(cSetting.getColor().getBlue()));
            }
            else {
                cSetting.setColor(new ColorUtil(cSetting.getColor().getRed(), cSetting.getColor().getGreen(), Integer.parseInt(cTextFieldBlue.getText())));
            }
            if (entry.getValue().getKey() == this.frame.module) {
                if (mouseX >= cTextFieldRed.x && mouseX < cTextFieldRed.x + cTextFieldRed.width && mouseY >= cTextFieldRed.y && mouseY < cTextFieldRed.y + cTextFieldRed.height) {
                    cTextFieldRed.setFocused(true);
                }
                if (mouseX >= cTextFieldGreen.x && mouseX < cTextFieldGreen.x + cTextFieldGreen.width && mouseY >= cTextFieldGreen.y && mouseY < cTextFieldGreen.y + cTextFieldGreen.height) {
                    cTextFieldGreen.setFocused(true);
                }
                if (mouseX < cTextFieldBlue.x || mouseX >= cTextFieldBlue.x + cTextFieldBlue.width || mouseY < cTextFieldBlue.y || mouseY >= cTextFieldBlue.y + cTextFieldBlue.height) {
                    continue;
                }
                cTextFieldBlue.setFocused(true);
            }
        }
        Main.config.Save();
    }
    
    public void func_73876_c() {
        super.func_73876_c();
        if (this.frame == null) {
            this.func_146281_b();
            return;
        }
        for (final Map.Entry<HtlrTextField, Module> entry : SettingController.textFields.entrySet()) {
            final HtlrTextField textField = entry.getKey();
            if (entry.getValue() == this.frame.module && textField.isFocused()) {
                textField.updateCursorCounter();
            }
        }
        for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry2 : SettingController.cTextFields.entrySet()) {
            final HtlrTextField cTextFieldRed = entry2.getKey()[0];
            final HtlrTextField cTextFieldGreen = entry2.getKey()[1];
            final HtlrTextField cTextFieldBlue = entry2.getKey()[2];
            if (entry2.getValue().getKey() == this.frame.module) {
                if (cTextFieldRed.isFocused()) {
                    cTextFieldRed.updateCursorCounter();
                }
                if (cTextFieldGreen.isFocused()) {
                    cTextFieldGreen.updateCursorCounter();
                }
                if (!cTextFieldBlue.isFocused()) {
                    continue;
                }
                cTextFieldBlue.updateCursorCounter();
            }
        }
    }
    
    private void scroller() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0 && this.frame.y + this.frame.height > 0) {
            System.out.println("debug1");
            for (final Map.Entry<HtlrTextField, Module> entry : SettingController.textFields.entrySet()) {
                final HtlrTextField textField = entry.getKey();
                if (entry.getValue() == this.frame.module) {
                    textField.y -= 10;
                }
            }
            this.frame.y -= 10;
            for (final SettingButton settingButton : this.frame.settingButtons) {
                final SettingButton s = settingButton;
                settingButton.y -= 10;
            }
            final KeybindButton kbButton = this.frame.kbButton;
            kbButton.y -= 10;
            for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry2 : SettingController.cTextFields.entrySet()) {
                final HtlrTextField cTextFieldRed = entry2.getKey()[0];
                final HtlrTextField cTextFieldGreen = entry2.getKey()[1];
                final HtlrTextField cTextFieldBlue = entry2.getKey()[2];
                if (entry2.getValue().getKey() == this.frame.module) {
                    final HtlrTextField htlrTextField = cTextFieldRed;
                    htlrTextField.y -= 10;
                    final HtlrTextField htlrTextField2 = cTextFieldGreen;
                    htlrTextField2.y -= 10;
                    final HtlrTextField htlrTextField3 = cTextFieldBlue;
                    htlrTextField3.y -= 10;
                }
            }
            for (final HtlrButton htlrButton : this.frame.module.buttons) {
                final HtlrButton g = htlrButton;
                htlrButton.y -= 10;
            }
            this.scrollOffset -= 10;
        }
        else if (dWheel > 0 && this.frame.y <= this.field_146295_m) {
            System.out.println("debug2");
            for (final Map.Entry<HtlrTextField, Module> entry : SettingController.textFields.entrySet()) {
                final HtlrTextField textField = entry.getKey();
                if (entry.getValue() == this.frame.module) {
                    textField.y += 10;
                }
            }
            this.frame.y += 10;
            for (final SettingButton settingButton2 : this.frame.settingButtons) {
                final SettingButton s = settingButton2;
                settingButton2.y += 10;
            }
            final KeybindButton kbButton2 = this.frame.kbButton;
            kbButton2.y += 10;
            for (final Map.Entry<HtlrTextField[], Map.Entry<Module, Setting>> entry2 : SettingController.cTextFields.entrySet()) {
                final HtlrTextField cTextFieldRed = entry2.getKey()[0];
                final HtlrTextField cTextFieldGreen = entry2.getKey()[1];
                final HtlrTextField cTextFieldBlue = entry2.getKey()[2];
                if (entry2.getValue().getKey() == this.frame.module) {
                    cTextFieldRed.y += 10;
                    cTextFieldGreen.y += 10;
                    cTextFieldBlue.y += 10;
                }
            }
            for (final HtlrButton htlrButton2 : this.frame.module.buttons) {
                final HtlrButton g = htlrButton2;
                htlrButton2.y += 10;
            }
            this.scrollOffset += 10;
        }
    }
    
    static {
        textFields = new HashMap<HtlrTextField, Module>();
        cTextFields = new HashMap<HtlrTextField[], Map.Entry<Module, Setting>>();
    }
}
