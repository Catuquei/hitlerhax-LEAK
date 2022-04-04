// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.config;

import mod.hitlerhax.module.Category;
import mod.hitlerhax.module.modules.render.Search;
import net.minecraftforge.fml.common.registry.GameRegistry;
import mod.hitlerhax.util.render.ColorUtil;
import java.util.Collection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import mod.hitlerhax.ui.clickgui.ClickGuiController;
import java.awt.Color;
import java.util.Map;
import net.minecraft.block.Block;
import mod.hitlerhax.setting.settings.SearchBlockSelectorSetting;
import mod.hitlerhax.setting.settings.ColorSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.setting.settings.StringSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.Main;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.Paths;
import java.io.File;
import mod.hitlerhax.ui.clickgui.ClickGuiFrame;
import java.util.ArrayList;
import java.nio.file.Path;
import mod.hitlerhax.util.Globals;

public class Config implements Globals
{
    public static Path modFolder;
    private final Path configFile;
    final ArrayList<ClickGuiFrame> frames;
    
    public Config() {
        this.frames = new ArrayList<ClickGuiFrame>();
        Config.modFolder = Config.mc.field_71412_D.toPath().resolve("hitlerhax");
        this.configFile = Paths.get(Config.modFolder + File.separator + "moduleConfig.txt", new String[0]);
        try {
            Files.createDirectories(Config.modFolder, (FileAttribute<?>[])new FileAttribute[0]);
            if (!Files.exists(this.configFile, new LinkOption[0])) {
                Files.createFile(this.configFile, (FileAttribute<?>[])new FileAttribute[0]);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void Save() {
        final ArrayList<String> save = new ArrayList<String>();
        for (final Module mod : Main.moduleManager.modules) {
            if (mod.getName().equalsIgnoreCase("Esp2dHelper") || mod.getName().equalsIgnoreCase("ClickGUI")) {
                save.add("module:" + mod.getName() + ":false:" + mod.getKey());
            }
            else {
                save.add("module:" + mod.getName() + ":" + mod.isToggled() + ":" + mod.getKey());
            }
        }
        for (final Module mod : Main.moduleManager.modules) {
            for (final Setting setting : mod.settings) {
                if (setting instanceof BooleanSetting) {
                    final BooleanSetting bool = (BooleanSetting)setting;
                    save.add("setting:" + mod.getName() + ":" + setting.name + ":" + bool.isEnabled());
                }
                if (setting instanceof IntSetting) {
                    final IntSetting num = (IntSetting)setting;
                    save.add("setting:" + mod.getName() + ":" + setting.name + ":" + num.getValue());
                }
                if (setting instanceof FloatSetting) {
                    final FloatSetting num2 = (FloatSetting)setting;
                    save.add("setting:" + mod.getName() + ":" + setting.name + ":" + num2.getValue());
                }
                if (setting instanceof StringSetting) {
                    final StringSetting val = (StringSetting)setting;
                    save.add("setting:" + mod.getName() + ":" + setting.name + ":" + val.getValue());
                }
                if (setting instanceof ModeSetting) {
                    final ModeSetting mode = (ModeSetting)setting;
                    save.add("setting:" + mod.getName() + ":" + setting.name + ":" + mode.getMode());
                }
                if (setting instanceof ColorSetting) {
                    final ColorSetting col = (ColorSetting)setting;
                    save.add("setting:" + mod.getName() + ":" + setting.name + ":" + col.getColor().getRed() + "," + col.getColor().getGreen() + "," + col.getColor().getBlue());
                }
                if (setting instanceof SearchBlockSelectorSetting) {
                    final SearchBlockSelectorSetting block = (SearchBlockSelectorSetting)setting;
                    final StringBuilder list = new StringBuilder();
                    final StringBuilder colorList = new StringBuilder();
                    for (final Block b : block.blocks) {
                        list.append(b.func_149732_F()).append("/");
                    }
                    for (final Map.Entry<Block, Integer> e : block.colors.entrySet()) {
                        colorList.append(new Color(e.getValue()).getRed()).append(".").append(new Color(e.getValue()).getGreen()).append(".").append(new Color(e.getValue()).getBlue()).append(",").append(e.getKey().func_149732_F()).append("/");
                    }
                    save.add("setting:" + mod.getName() + ":" + setting.name + ":" + (Object)list + ";" + (Object)colorList);
                }
            }
        }
        for (final ClickGuiFrame f : ClickGuiController.frames) {
            save.add("frame:" + f.category.toString() + ":" + f.x + ":" + f.y);
        }
        try {
            final PrintWriter pw = new PrintWriter(this.configFile.toFile());
            for (final String str : save) {
                pw.println(str);
            }
            pw.close();
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }
    
    public void Load() {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(this.configFile.toFile()));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                lines.add(line);
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (final String s : lines) {
            final String[] args = s.split(":");
            if (s.toLowerCase().startsWith("module:")) {
                final Module m = Main.moduleManager.getModule(args[1]);
                try {
                    m.setKey(Integer.parseInt(args[3]));
                    m.setToggled(Boolean.parseBoolean(args[2]));
                }
                catch (NullPointerException e2) {
                    System.out.println("Module in config file does not exist");
                    e2.printStackTrace();
                }
            }
            else {
                if (!s.toLowerCase().startsWith("setting:")) {
                    continue;
                }
                final Module m = Main.moduleManager.getModule(args[1]);
                if (m == null) {
                    continue;
                }
                final Setting setting = Main.settingManager.getSettingByName(m, args[2]);
                if (setting == null) {
                    continue;
                }
                Main.settingManager.addSetting(setting);
            }
        }
        final ArrayList<String> save = new ArrayList<String>(lines);
        for (final Module mod : Main.moduleManager.getModuleList()) {
            for (final Setting setting : mod.settings) {
                if (!Main.settingManager.getSettings().contains(setting)) {
                    if (setting instanceof BooleanSetting) {
                        final BooleanSetting bool = (BooleanSetting)setting;
                        save.add("setting:" + mod.getName() + ":" + setting.name + ":" + bool.isEnabled());
                    }
                    if (setting instanceof IntSetting) {
                        final IntSetting num = (IntSetting)setting;
                        save.add("setting:" + mod.getName() + ":" + setting.name + ":" + num.getValue());
                    }
                    if (setting instanceof FloatSetting) {
                        final FloatSetting num2 = (FloatSetting)setting;
                        save.add("setting:" + mod.getName() + ":" + setting.name + ":" + num2.getValue());
                    }
                    if (setting instanceof StringSetting) {
                        final StringSetting val = (StringSetting)setting;
                        save.add("setting:" + mod.getName() + ":" + setting.name + ":" + val.getValue());
                    }
                    if (setting instanceof ModeSetting) {
                        final ModeSetting mode = (ModeSetting)setting;
                        save.add("setting:" + mod.getName() + ":" + setting.name + ":" + mode.getMode());
                    }
                    if (setting instanceof ColorSetting) {
                        final ColorSetting col = (ColorSetting)setting;
                        save.add("setting:" + mod.getName() + ":" + setting.name + ":" + col.getColor().getRed() + "," + col.getColor().getGreen() + "," + col.getColor().getBlue());
                    }
                    if (!(setting instanceof SearchBlockSelectorSetting)) {
                        continue;
                    }
                    final SearchBlockSelectorSetting block = (SearchBlockSelectorSetting)setting;
                    final StringBuilder list = new StringBuilder();
                    final StringBuilder colorList = new StringBuilder();
                    for (final Block b : block.blocks) {
                        list.append(b.func_149732_F()).append("/");
                    }
                    for (final Map.Entry<Block, Integer> e3 : block.colors.entrySet()) {
                        colorList.append(new Color(e3.getValue()).getRed()).append(".").append(new Color(e3.getValue()).getGreen()).append(".").append(new Color(e3.getValue()).getBlue()).append(",").append(e3.getKey().func_149732_F()).append("/");
                    }
                    save.add("setting:" + mod.getName() + ":" + setting.name + ":" + (Object)list + ";" + (Object)colorList);
                }
            }
        }
        try {
            final PrintWriter pw = new PrintWriter(this.configFile.toFile());
            for (final String str : save) {
                pw.println(str);
            }
            pw.close();
        }
        catch (FileNotFoundException e4) {
            e4.printStackTrace();
        }
        Main.settingManager.clearSettings();
        lines = new ArrayList<String>();
        try {
            final BufferedReader reader2 = new BufferedReader(new FileReader(this.configFile.toFile()));
            for (String line2 = reader2.readLine(); line2 != null; line2 = reader2.readLine()) {
                lines.add(line2);
            }
            reader2.close();
        }
        catch (Exception e5) {
            e5.printStackTrace();
        }
        for (final String s2 : lines) {
            final String[] args2 = s2.split(":");
            if (s2.toLowerCase().startsWith("module:")) {
                final Module i = Main.moduleManager.getModule(args2[1]);
                if (i != null) {
                    i.setKey(Integer.parseInt(args2[3]));
                    i.setToggled(Boolean.parseBoolean(args2[2]));
                }
            }
            else if (s2.toLowerCase().startsWith("setting:")) {
                final Module i = Main.moduleManager.getModule(args2[1]);
                if (i != null) {
                    final Setting setting2 = Main.settingManager.getSettingByName(i, args2[2]);
                    if (setting2 != null) {
                        Main.settingManager.addSetting(setting2);
                        if (setting2 instanceof BooleanSetting) {
                            ((BooleanSetting)setting2).setEnabled(Boolean.parseBoolean(args2[3]));
                        }
                        if (setting2 instanceof IntSetting) {
                            ((IntSetting)setting2).setValue(Integer.parseInt(args2[3]));
                        }
                        if (setting2 instanceof FloatSetting) {
                            ((FloatSetting)setting2).setValue(Float.parseFloat(args2[3]));
                        }
                        if (setting2 instanceof StringSetting) {
                            ((StringSetting)setting2).setValue(args2[3]);
                        }
                        if (setting2 instanceof ModeSetting) {
                            ((ModeSetting)setting2).setMode(args2[3]);
                        }
                        if (setting2 instanceof ColorSetting) {
                            ((ColorSetting)setting2).setColor(new ColorUtil(Integer.parseInt(args2[3].split(",")[0]), Integer.parseInt(args2[3].split(",")[1]), Integer.parseInt(args2[3].split(",")[2])));
                        }
                        if (setting2 instanceof SearchBlockSelectorSetting) {
                            if (args2.length > 3) {
                                try {
                                    if (args2[3].contains(";")) {
                                        for (final String str2 : args2[3].split(";")[0].split("/")) {
                                            if (!str2.isEmpty()) {
                                                for (final Block b2 : GameRegistry.findRegistry((Class)Block.class).getValuesCollection()) {
                                                    if (str2.equalsIgnoreCase(b2.func_149732_F())) {
                                                        ((SearchBlockSelectorSetting)setting2).blocks.add(b2);
                                                    }
                                                }
                                            }
                                        }
                                        for (final String str2 : args2[3].split(";")[1].split("/")) {
                                            if (!str2.isEmpty()) {
                                                for (final Block b2 : GameRegistry.findRegistry((Class)Block.class).getValuesCollection()) {
                                                    if (str2.split(",")[1].equalsIgnoreCase(b2.func_149732_F())) {
                                                        final String colors = str2.split(",")[0];
                                                        ((SearchBlockSelectorSetting)setting2).setColor(b2, new Color(Integer.parseInt(colors.split("\\.")[0]), Integer.parseInt(colors.split("\\.")[1]), Integer.parseInt(colors.split("\\.")[2].split(",")[0])).getRGB());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        for (final String str2 : args2[3].split("/")) {
                                            if (!str2.isEmpty()) {
                                                for (final Block b2 : GameRegistry.findRegistry((Class)Block.class).getValuesCollection()) {
                                                    if (str2.equalsIgnoreCase(b2.func_149732_F())) {
                                                        ((SearchBlockSelectorSetting)setting2).blocks.add(b2);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                catch (Exception e6) {
                                    e6.printStackTrace();
                                    System.out.println("Config issue for search module, reverting to default search config");
                                }
                            }
                            ((Search)setting2.parent).to_search.clear();
                            for (final Block b3 : ((SearchBlockSelectorSetting)setting2).getBlocks()) {
                                final int color = ((SearchBlockSelectorSetting)setting2).getColor(b3);
                                ((Search)setting2.parent).to_search.put(b3, color);
                            }
                        }
                    }
                }
            }
            else if (s2.toLowerCase().startsWith("frame:")) {
                for (final Category category : Category.values()) {
                    if (args2[1].equalsIgnoreCase(category.name)) {
                        this.frames.add(new ClickGuiFrame(category, Integer.parseInt(args2[2]), Integer.parseInt(args2[3])));
                    }
                }
            }
            if (!this.frames.isEmpty()) {
                ClickGuiController.frames = this.frames;
            }
        }
        Main.configLoaded = true;
    }
}
