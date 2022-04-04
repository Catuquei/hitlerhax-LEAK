// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax;

import mod.hitlerhax.ui.clickgui.ClickGuiController;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import mod.hitlerhax.command.CommandManager;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.util.Globals;

public class Client implements Globals
{
    public static final String commandPrefix = ".";
    public static volatile boolean getNextKeyPressForKeybinding;
    public static Module keybindModule;
    
    public static String getCommandPrefix() {
        return ".";
    }
    
    public Client() {
        new CommandManager();
    }
    
    public static void addChatMessage(final String s, final boolean doPrefixture) {
        String prefixture;
        if (doPrefixture) {
            prefixture = "§b[HitlerHax]: ";
        }
        else {
            prefixture = "";
        }
        Client.mc.field_71439_g.func_145747_a((ITextComponent)new TextComponentString(prefixture + s));
    }
    
    public static void addChatMessage(final String s) {
        Client.mc.field_71439_g.func_145747_a((ITextComponent)new TextComponentString("§b[HitlerHax]: " + s));
    }
    
    public static void waitForKeybindKeypress(final Module m) {
        Client.keybindModule = m;
        Client.getNextKeyPressForKeybinding = true;
    }
    
    public static void stopWaitForKeybindPress() {
        Client.getNextKeyPressForKeybinding = false;
        Client.keybindModule = null;
        ClickGuiController.INSTANCE.settingController.refresh(false);
    }
    
    static {
        Client.getNextKeyPressForKeybinding = false;
    }
}
