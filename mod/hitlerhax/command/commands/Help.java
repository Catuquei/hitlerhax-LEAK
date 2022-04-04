// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.command.commands;

import java.util.Iterator;
import mod.hitlerhax.Client;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.Main;
import mod.hitlerhax.command.Command;

public class Help extends Command
{
    @Override
    public String getAlias() {
        return "help";
    }
    
    @Override
    public String getDescription() {
        return "Returns help";
    }
    
    @Override
    public String getSyntax() {
        return ".help | .help [Module] | .help [Setting]";
    }
    
    @Override
    public void onCommand(final String command, final String[] args) {
        if (args[0].isEmpty()) {
            for (final Module m : Main.moduleManager.getModuleList()) {
                Client.addChatMessage("Module: " + m.getName() + " - " + m.getDescription());
            }
            for (final Command c : Main.cmdManager.getCommands()) {
                Client.addChatMessage("Command: " + c.getAlias() + " - " + c.getDescription());
            }
        }
        if (!args[0].isEmpty()) {
            for (final Module m : Main.moduleManager.getModuleList()) {
                if (args[0].equalsIgnoreCase(m.getName())) {
                    Client.addChatMessage("Module: " + m.getName() + " - " + m.getDescription());
                }
            }
            for (final Command c : Main.cmdManager.getCommands()) {
                if (args[0].equalsIgnoreCase(c.getAlias())) {
                    Client.addChatMessage("Command: " + c.getAlias() + " - " + c.getDescription());
                    Client.addChatMessage("syntax: " + c.getSyntax());
                }
            }
        }
    }
}
