// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.command.commands;

import java.util.Iterator;
import mod.hitlerhax.Client;
import mod.hitlerhax.module.Module;
import mod.hitlerhax.Main;
import mod.hitlerhax.command.Command;

public class Toggle extends Command
{
    @Override
    public String getAlias() {
        return "toggle";
    }
    
    @Override
    public String getDescription() {
        return "toggles modules";
    }
    
    @Override
    public String getSyntax() {
        return ".toggle [Module]";
    }
    
    @Override
    public void onCommand(final String command, final String[] args) {
        for (final Module m : Main.moduleManager.getModuleList()) {
            if (args[0].equalsIgnoreCase(m.getName())) {
                m.toggle();
                Client.addChatMessage(args[0] + " has been toggled");
                return;
            }
        }
        Client.addChatMessage("Module " + args[0] + " was not found");
        Client.addChatMessage(this.getSyntax());
    }
}
