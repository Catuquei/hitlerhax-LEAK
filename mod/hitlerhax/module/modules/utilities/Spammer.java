// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import java.io.FileNotFoundException;
import mod.hitlerhax.Client;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.Paths;
import java.io.File;
import mod.hitlerhax.config.Config;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import java.util.ArrayList;
import mod.hitlerhax.util.Timer;
import java.nio.file.Path;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.module.Module;

public class Spammer extends Module
{
    final IntSetting delay;
    private Path spammerFile;
    final Timer timer;
    final ArrayList<String> lines;
    int index;
    
    public Spammer() {
        super("Spammer", "Spams in the Chat", Category.UTILITIES);
        this.delay = new IntSetting("Delay(s)", this, 300);
        this.spammerFile = null;
        this.timer = new Timer();
        this.lines = new ArrayList<String>();
        this.index = 0;
        this.addSetting(this.delay);
    }
    
    public void onEnable() {
        this.spammerFile = Paths.get(Config.modFolder.toString() + File.separator + "spammer.txt", new String[0]);
        try {
            Files.createDirectories(Config.modFolder, (FileAttribute<?>[])new FileAttribute[0]);
            if (!Files.exists(this.spammerFile, new LinkOption[0])) {
                Files.createFile(this.spammerFile, (FileAttribute<?>[])new FileAttribute[0]);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(this.spammerFile.toFile()));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                this.lines.add(line);
            }
            reader.close();
        }
        catch (FileNotFoundException e2) {
            this.toggle();
            this.spammerFile = Paths.get(Config.modFolder.toString() + File.separator + "spammer.txt", new String[0]);
            Client.addChatMessage("couldn't find the spammer file, turning spammer off.");
            e2.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.timer.reset();
    }
    
    @Override
    public void onUpdate() {
        if (this.spammerFile == null) {
            this.spammerFile = Paths.get(Config.modFolder.toString() + File.separator + "spammer.txt", new String[0]);
            return;
        }
        if (this.lines != null) {
            if (!this.lines.isEmpty()) {
                if (this.timer.getPassedTimeMs() / 1000L >= this.delay.value) {
                    this.timer.reset();
                    if (this.index == this.lines.size()) {
                        this.index = 0;
                    }
                    Spammer.mc.field_71439_g.func_71165_d((String)this.lines.get(this.index));
                    ++this.index;
                }
                return;
            }
        }
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(this.spammerFile.toFile()));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                this.lines.add(line);
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            this.toggle();
            this.spammerFile = Paths.get(Config.modFolder.toString() + File.separator + "spammer.txt", new String[0]);
            Client.addChatMessage("couldn't find the spammer file, turning spammer off.");
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
