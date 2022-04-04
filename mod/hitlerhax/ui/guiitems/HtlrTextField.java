// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.ui.guiitems;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import mod.hitlerhax.util.font.FontUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ChatAllowedCharacters;
import java.util.function.Predicate;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mod.hitlerhax.util.Globals;
import net.minecraft.client.gui.Gui;

@SideOnly(Side.CLIENT)
public class HtlrTextField extends Gui implements Globals
{
    private final int id;
    public int x;
    public int y;
    public int width;
    public int height;
    private String text;
    private int maxStringLength;
    private int cursorCounter;
    private boolean enableBackgroundDrawing;
    private boolean canLoseFocus;
    private boolean isFocused;
    private boolean isEnabled;
    private int lineScrollOffset;
    private int cursorPosition;
    private int selectionEnd;
    private int enabledColor;
    private int disabledColor;
    private boolean visible;
    private GuiPageButtonList.GuiResponder guiResponder;
    private Predicate<String> validator;
    
    public HtlrTextField(final int componentId, final int x, final int y, final int par5Width, final int par6Height) {
        this.text = "";
        this.maxStringLength = 64;
        this.enableBackgroundDrawing = true;
        this.canLoseFocus = true;
        this.isEnabled = true;
        this.enabledColor = 14737632;
        this.disabledColor = 7368816;
        this.visible = true;
        this.validator = (s -> true);
        this.id = componentId;
        this.x = x;
        this.y = y;
        this.width = par5Width;
        this.height = par6Height;
    }
    
    public void setGuiResponder(final GuiPageButtonList.GuiResponder guiResponderIn) {
        this.guiResponder = guiResponderIn;
    }
    
    public void updateCursorCounter() {
        ++this.cursorCounter;
    }
    
    public void setText(final String textIn) {
        if (this.validator.test(textIn)) {
            if (textIn.length() > this.maxStringLength) {
                this.text = textIn.substring(0, this.maxStringLength);
            }
            else {
                this.text = textIn;
            }
            this.setCursorPositionEnd();
        }
    }
    
    public String getText() {
        return this.text;
    }
    
    public String getSelectedText() {
        final int i = Math.min(this.cursorPosition, this.selectionEnd);
        final int j = Math.max(this.cursorPosition, this.selectionEnd);
        return this.text.substring(i, j);
    }
    
    public void setValidator(final Predicate<String> theValidator) {
        this.validator = theValidator;
    }
    
    public void writeText(final String textToWrite) {
        String s = "";
        final String s2 = ChatAllowedCharacters.func_71565_a(textToWrite);
        final int i = Math.min(this.cursorPosition, this.selectionEnd);
        final int j = Math.max(this.cursorPosition, this.selectionEnd);
        final int k = this.maxStringLength - this.text.length() - (i - j);
        if (!this.text.isEmpty()) {
            s += this.text.substring(0, i);
        }
        int l;
        if (k < s2.length()) {
            s += s2.substring(0, k);
            l = k;
        }
        else {
            s += s2;
            l = s2.length();
        }
        if (!this.text.isEmpty() && j < this.text.length()) {
            s += this.text.substring(j);
        }
        if (this.validator.test(s)) {
            this.text = s;
            this.moveCursorBy(i - this.selectionEnd + l);
            this.setResponderEntryValue(this.id, this.text);
        }
    }
    
    public void setResponderEntryValue(final int idIn, final String textIn) {
        if (this.guiResponder != null) {
            this.guiResponder.func_175319_a(idIn, textIn);
        }
    }
    
    public void deleteWords(final int num) {
        if (!this.text.isEmpty()) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            }
            else {
                this.deleteFromCursor(this.getNthWordFromCursor(num) - this.cursorPosition);
            }
        }
    }
    
    public void deleteFromCursor(final int num) {
        if (!this.text.isEmpty()) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            }
            else {
                final boolean flag = num < 0;
                final int i = flag ? (this.cursorPosition + num) : this.cursorPosition;
                final int j = flag ? this.cursorPosition : (this.cursorPosition + num);
                String s = "";
                if (i >= 0) {
                    s = this.text.substring(0, i);
                }
                if (j < this.text.length()) {
                    s += this.text.substring(j);
                }
                if (this.validator.test(s)) {
                    this.text = s;
                    if (flag) {
                        this.moveCursorBy(num);
                    }
                    this.setResponderEntryValue(this.id, this.text);
                }
            }
        }
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getNthWordFromCursor(final int numWords) {
        return this.getNthWordFromPos(numWords, this.getCursorPosition());
    }
    
    public int getNthWordFromPos(final int n, final int pos) {
        return this.getNthWordFromPosWS(n, pos, true);
    }
    
    public int getNthWordFromPosWS(final int n, final int pos, final boolean skipWs) {
        int i = pos;
        final boolean flag = n < 0;
        for (int j = Math.abs(n), k = 0; k < j; ++k) {
            if (!flag) {
                final int l = this.text.length();
                i = this.text.indexOf(32, i);
                if (i == -1) {
                    i = l;
                }
                else {
                    while (skipWs && i < l && this.text.charAt(i) == ' ') {
                        ++i;
                    }
                }
            }
            else {
                while (skipWs && i > 0 && this.text.charAt(i - 1) == ' ') {
                    --i;
                }
                while (i > 0 && this.text.charAt(i - 1) != ' ') {
                    --i;
                }
            }
        }
        return i;
    }
    
    public void moveCursorBy(final int num) {
        this.setCursorPosition(this.selectionEnd + num);
    }
    
    public void setCursorPosition(final int pos) {
        this.cursorPosition = pos;
        final int i = this.text.length();
        this.setSelectionPos(this.cursorPosition = MathHelper.func_76125_a(this.cursorPosition, 0, i));
    }
    
    public void setCursorPositionZero() {
        this.setCursorPosition(0);
    }
    
    public void setCursorPositionEnd() {
        this.setCursorPosition(this.text.length());
    }
    
    public boolean textboxKeyTyped(final char typedChar, final int keyCode) {
        if (!this.isFocused) {
            return false;
        }
        if (GuiScreen.func_175278_g(keyCode)) {
            this.setCursorPositionEnd();
            this.setSelectionPos(0);
            return true;
        }
        if (GuiScreen.func_175280_f(keyCode)) {
            GuiScreen.func_146275_d(this.getSelectedText());
            return true;
        }
        if (GuiScreen.func_175279_e(keyCode)) {
            if (this.isEnabled) {
                this.writeText(GuiScreen.func_146277_j());
            }
            return true;
        }
        if (GuiScreen.func_175277_d(keyCode)) {
            GuiScreen.func_146275_d(this.getSelectedText());
            if (this.isEnabled) {
                this.writeText("");
            }
            return true;
        }
        switch (keyCode) {
            case 14: {
                if (GuiScreen.func_146271_m()) {
                    if (this.isEnabled) {
                        this.deleteWords(-1);
                    }
                }
                else if (this.isEnabled) {
                    this.deleteFromCursor(-1);
                }
                return true;
            }
            case 199: {
                if (GuiScreen.func_146272_n()) {
                    this.setSelectionPos(0);
                }
                else {
                    this.setCursorPositionZero();
                }
                return true;
            }
            case 203: {
                if (GuiScreen.func_146272_n()) {
                    if (GuiScreen.func_146271_m()) {
                        this.setSelectionPos(this.getNthWordFromPos(-1, this.getSelectionEnd()));
                    }
                    else {
                        this.setSelectionPos(this.getSelectionEnd() - 1);
                    }
                }
                else if (GuiScreen.func_146271_m()) {
                    this.setCursorPosition(this.getNthWordFromCursor(-1));
                }
                else {
                    this.moveCursorBy(-1);
                }
                return true;
            }
            case 205: {
                if (GuiScreen.func_146272_n()) {
                    if (GuiScreen.func_146271_m()) {
                        this.setSelectionPos(this.getNthWordFromPos(1, this.getSelectionEnd()));
                    }
                    else {
                        this.setSelectionPos(this.getSelectionEnd() + 1);
                    }
                }
                else if (GuiScreen.func_146271_m()) {
                    this.setCursorPosition(this.getNthWordFromCursor(1));
                }
                else {
                    this.moveCursorBy(1);
                }
                return true;
            }
            case 207: {
                if (GuiScreen.func_146272_n()) {
                    this.setSelectionPos(this.text.length());
                }
                else {
                    this.setCursorPositionEnd();
                }
                return true;
            }
            case 211: {
                if (GuiScreen.func_146271_m()) {
                    if (this.isEnabled) {
                        this.deleteWords(1);
                    }
                }
                else if (this.isEnabled) {
                    this.deleteFromCursor(1);
                }
                return true;
            }
            default: {
                if (ChatAllowedCharacters.func_71566_a(typedChar)) {
                    if (this.isEnabled) {
                        this.writeText(Character.toString(typedChar));
                    }
                    return true;
                }
                return false;
            }
        }
    }
    
    public boolean mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        final boolean flag = mouseX >= this.x && mouseX < this.x + this.width && mouseY >= this.y && mouseY < this.y + this.height;
        if (this.canLoseFocus) {
            this.setFocused(flag);
        }
        if (this.isFocused && flag && mouseButton == 0) {
            int i = mouseX - this.x;
            if (this.enableBackgroundDrawing) {
                i -= 4;
            }
            final String s = FontUtils.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
            this.setCursorPosition(FontUtils.trimStringToWidth(s, i).length() + this.lineScrollOffset);
            return true;
        }
        return false;
    }
    
    public void drawTextBox() {
        if (this.getVisible()) {
            if (this.getEnableBackgroundDrawing()) {
                func_73734_a(this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, -6250336);
                func_73734_a(this.x, this.y, this.x + this.width, this.y + this.height, -16777216);
            }
            final int i = this.isEnabled ? this.enabledColor : this.disabledColor;
            final int j = this.cursorPosition - this.lineScrollOffset;
            int k = this.selectionEnd - this.lineScrollOffset;
            final String s = FontUtils.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
            final boolean flag = j >= 0 && j <= s.length();
            final boolean flag2 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && flag;
            final int l = this.enableBackgroundDrawing ? (this.x + 4) : this.x;
            final int i2 = this.enableBackgroundDrawing ? (this.y + (this.height - 8) / 2) : this.y;
            int j2 = l;
            if (k > s.length()) {
                k = s.length();
            }
            if (!s.isEmpty()) {
                final String s2 = flag ? s.substring(0, j) : s;
                j2 = FontUtils.drawStringWithShadow(s2, (float)l, (float)i2, i);
            }
            final boolean flag3 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
            int k2 = j2;
            if (!flag) {
                k2 = ((j > 0) ? (l + this.width) : l);
            }
            else if (flag3) {
                k2 = j2 - 1;
                --j2;
            }
            if (!s.isEmpty() && flag && j < s.length()) {
                j2 = FontUtils.drawStringWithShadow(s.substring(j), (float)j2, (float)i2, i);
            }
            if (flag2) {
                if (flag3) {
                    Gui.func_73734_a(k2, i2 - 1, k2 + 1, i2 + 1 + FontUtils.getFontHeight(), -3092272);
                }
                else {
                    FontUtils.drawStringWithShadow("_", (float)k2, (float)i2, i);
                }
            }
            if (k != j) {
                final int l2 = l + FontUtils.getStringWidth(s.substring(0, k));
                this.drawSelectionBox(k2, i2 - 1, l2 - 1, i2 + 1 + FontUtils.getFontHeight());
            }
        }
    }
    
    private void drawSelectionBox(int startX, int startY, int endX, int endY) {
        if (startX < endX) {
            final int i = startX;
            startX = endX;
            endX = i;
        }
        if (startY < endY) {
            final int j = startY;
            startY = endY;
            endY = j;
        }
        if (endX > this.x + this.width) {
            endX = this.x + this.width;
        }
        if (startX > this.x + this.width) {
            startX = this.x + this.width;
        }
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179131_c(0.0f, 0.0f, 255.0f, 255.0f);
        GlStateManager.func_179090_x();
        GlStateManager.func_179115_u();
        GlStateManager.func_187422_a(GlStateManager.LogicOp.OR_REVERSE);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        bufferbuilder.func_181662_b((double)startX, (double)endY, 0.0).func_181675_d();
        bufferbuilder.func_181662_b((double)endX, (double)endY, 0.0).func_181675_d();
        bufferbuilder.func_181662_b((double)endX, (double)startY, 0.0).func_181675_d();
        bufferbuilder.func_181662_b((double)startX, (double)startY, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179134_v();
        GlStateManager.func_179098_w();
    }
    
    public void setMaxStringLength(final int length) {
        this.maxStringLength = length;
        if (this.text.length() > length) {
            this.text = this.text.substring(0, length);
        }
    }
    
    public int getMaxStringLength() {
        return this.maxStringLength;
    }
    
    public int getCursorPosition() {
        return this.cursorPosition;
    }
    
    public boolean getEnableBackgroundDrawing() {
        return this.enableBackgroundDrawing;
    }
    
    public void setEnableBackgroundDrawing(final boolean enableBackgroundDrawingIn) {
        this.enableBackgroundDrawing = enableBackgroundDrawingIn;
    }
    
    public void setTextColor(final int color) {
        this.enabledColor = color;
    }
    
    public void setDisabledTextColour(final int color) {
        this.disabledColor = color;
    }
    
    public void setFocused(final boolean isFocusedIn) {
        if (isFocusedIn && !this.isFocused) {
            this.cursorCounter = 0;
        }
        this.isFocused = isFocusedIn;
        if (HtlrTextField.mc.field_71462_r != null) {
            HtlrTextField.mc.field_71462_r.func_193975_a(isFocusedIn);
        }
    }
    
    public boolean isFocused() {
        return this.isFocused;
    }
    
    public void setEnabled(final boolean enabled) {
        this.isEnabled = enabled;
    }
    
    public int getSelectionEnd() {
        return this.selectionEnd;
    }
    
    public int getWidth() {
        return this.getEnableBackgroundDrawing() ? (this.width - 8) : this.width;
    }
    
    public void setSelectionPos(int position) {
        final int i = this.text.length();
        if (position > i) {
            position = i;
        }
        if (position < 0) {
            position = 0;
        }
        this.selectionEnd = position;
        if (this.lineScrollOffset > i) {
            this.lineScrollOffset = i;
        }
        final int j = this.getWidth();
        final String s = FontUtils.trimStringToWidth(this.text.substring(this.lineScrollOffset), j);
        final int k = s.length() + this.lineScrollOffset;
        if (position == this.lineScrollOffset) {
            this.lineScrollOffset -= FontUtils.trimStringToWidth(this.text, j, true).length();
        }
        if (position > k) {
            this.lineScrollOffset += position - k;
        }
        else if (position <= this.lineScrollOffset) {
            this.lineScrollOffset -= this.lineScrollOffset - position;
        }
        this.lineScrollOffset = MathHelper.func_76125_a(this.lineScrollOffset, 0, i);
    }
    
    public void setCanLoseFocus(final boolean canLoseFocusIn) {
        this.canLoseFocus = canLoseFocusIn;
    }
    
    public boolean getVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean isVisible) {
        this.visible = isVisible;
    }
}
