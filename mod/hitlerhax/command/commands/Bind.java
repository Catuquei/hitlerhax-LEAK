// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.command.commands;

import java.util.Iterator;
import mod.hitlerhax.Client;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.Main;
import org.lwjgl.input.Keyboard;
import mod.hitlerhax.command.Command;

public class Bind extends Command
{
    @Override
    public String getAlias() {
        return "bind";
    }
    
    @Override
    public String getDescription() {
        return "Changes keybinds";
    }
    
    @Override
    public String getSyntax() {
        return ".bind set [Module] [Key] | .bind clear [Module] | .bind get [Module]";
    }
    
    @Override
    public void onCommand(final String command, final String[] args) {
        if (args[0].equalsIgnoreCase("set")) {
            args[2] = args[2].toUpperCase();
            final int key = Keyboard.getKeyIndex(args[2]);
            for (final Module m : Main.moduleManager.getModuleList()) {
                if (args[1].equalsIgnoreCase(m.getName())) {
                    m.setKey(Keyboard.getKeyIndex(Keyboard.getKeyName(key)));
                    Client.addChatMessage(args[1] + " has been bound to " + Keyboard.getKeyName(key));
                }
            }
        }
        if (args[0].equalsIgnoreCase("clear")) {
            for (final Module i : Main.moduleManager.getModuleList()) {
                if (args[1].equalsIgnoreCase(i.getName())) {
                    i.setKey(0);
                    Client.addChatMessage(args[1] + " keybind has been cleared");
                }
            }
        }
        if (args[0].equalsIgnoreCase("get")) {
            for (final Module i : Main.moduleManager.getModuleList()) {
                if (i.getName().equalsIgnoreCase(args[1])) {
                    Client.addChatMessage(i.getName() + " is bound to key: \"" + Keyboard.getKeyName(i.getKey()) + "\"");
                    return;
                }
            }
            Client.addChatMessage("No such module exists");
        }
        if (args.length == 0) {
            Client.addChatMessage("No arguments");
            Client.addChatMessage(this.getSyntax());
        }
        if (!args[0].equalsIgnoreCase("set") && !args[0].equalsIgnoreCase("clear") && !args[0].equalsIgnoreCase("get")) {
            Client.addChatMessage("Invalid arguments");
            Client.addChatMessage(this.getSyntax());
        }
    }
}
