// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import java.awt.Color;
import java.io.IOException;
import mod.hitlerhax.Client;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import mod.hitlerhax.util.UnicodeReader;
import java.io.FileInputStream;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextFormatting;
import mod.hitlerhax.Main;
import net.minecraft.nbt.NBTTagList;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import net.minecraft.client.gui.GuiButton;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.StringSetting;
import mod.hitlerhax.module.Module;

public class BookWriter extends Module
{
    public final StringSetting path;
    final IntSetting delay;
    public GuiButton reset;
    public GuiButton read;
    public GuiButton write;
    public String fileContents;
    public String written;
    static long timer;
    
    public BookWriter() {
        super("BookWriter", "Auto-writes books", Category.UTILITIES);
        this.path = new StringSetting("TXT Path", this, "C:\\example.txt");
        this.delay = new IntSetting("Typing Delay (ms)", this, 100);
        this.fileContents = "";
        this.written = "";
        this.addSetting(this.path);
        this.addSetting(this.delay);
        if (this.path.value == null) {
            this.path.value = "C:\\example.txt";
        }
    }
    
    public void addButtons(final int width) {
        this.reset = new GuiButton(69420, width / 8 * 7, 10, width / 8 - 10, 20, "Reset");
        this.read = new GuiButton(69421, width / 8 * 7, 40, width / 8 - 10, 20, "Read File");
        this.write = new GuiButton(69422, width / 8 * 7, 70, width / 8 - 10, 20, "Write Page");
    }
    
    public void actionPerformed(final GuiButton button, final NBTTagList bookPages, final int currPage) {
        if (button.field_146127_k == 69420) {
            this.fileContents = "";
            this.written = "";
        }
        else if (button.field_146127_k == 69421) {
            this.fileContents = readFile(((BookWriter)Main.moduleManager.getModule("BookWriter")).path.value);
        }
        else if (button.field_146127_k == 69422) {
            for (int i = 0; i < this.fileContents.length(); ++i) {
                final String currentPage = (bookPages != null && currPage >= 0 && currPage < bookPages.func_74745_c()) ? bookPages.func_150307_f(currPage) : "";
                final int height = BookWriter.mc.field_71466_p.func_78267_b(currentPage + i + "" + TextFormatting.BLACK + "_", 118);
                if (height > 110 || currentPage.length() >= 244) {
                    break;
                }
                if (bookPages != null && currPage >= 0 && currPage < bookPages.func_74745_c()) {
                    bookPages.func_150304_a(currPage, (NBTBase)new NBTTagString(currentPage + this.fileContents.charAt(0)));
                    this.written += this.fileContents.charAt(0);
                    this.fileContents = this.fileContents.substring(1);
                }
            }
        }
    }
    
    public void updateButtons(final boolean bookIsUnsigned) {
        if (this.isToggled() && bookIsUnsigned) {
            if (this.reset != null) {
                this.reset.field_146125_m = true;
            }
            if (this.read != null) {
                this.read.field_146125_m = true;
            }
            if (this.write != null) {
                this.write.field_146125_m = true;
            }
        }
        else {
            if (this.reset != null) {
                this.reset.field_146125_m = false;
            }
            if (this.read != null) {
                this.read.field_146125_m = false;
            }
            if (this.write != null) {
                this.write.field_146125_m = false;
            }
        }
    }
    
    private static String readFile(final String filePath) {
        final StringBuilder builder = new StringBuilder();
        try {
            final BufferedReader buffer = new BufferedReader(new UnicodeReader(new FileInputStream(filePath), "UTF-8"));
            String str;
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        }
        catch (IOException e) {
            if (System.currentTimeMillis() - BookWriter.timer > 500L) {
                Client.addChatMessage("Invalid File Path");
            }
            BookWriter.timer = System.currentTimeMillis();
            e.printStackTrace();
            return "";
        }
        return builder.toString();
    }
    
    public void drawScreen(final boolean bookIsUnsigned, final int width, final int height, final int mouseX, final int mouseY, final float partialTicks) {
        if (this.isToggled() && bookIsUnsigned) {
            this.reset.func_191745_a(BookWriter.mc, mouseX, mouseY, partialTicks);
            this.read.func_191745_a(BookWriter.mc, mouseX, mouseY, partialTicks);
            this.write.func_191745_a(BookWriter.mc, mouseX, mouseY, partialTicks);
            BookWriter.mc.field_71466_p.func_78279_b((this.written.length() < 100) ? this.written : this.written.substring(this.written.length() - 100), 10, 10, width / 8, new Color(0, 255, 0).getRGB());
            BookWriter.mc.field_71466_p.func_78279_b((this.fileContents.length() < 250) ? this.fileContents : this.fileContents.substring(0, 250), 10, BookWriter.mc.field_71466_p.func_78267_b((this.written.length() < 100) ? this.written : this.written.substring(this.written.length() - 100), width / 8) + 20, width / 8, new Color(255, 0, 0).getRGB());
        }
    }
}
