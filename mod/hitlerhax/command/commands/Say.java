// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.command.commands;

import mod.hitlerhax.Client;
import mod.hitlerhax.command.Command;

public class Say extends Command
{
    @Override
    public String getAlias() {
        return "say";
    }
    
    @Override
    public String getDescription() {
        return "Say something in chat without triggering commands";
    }
    
    @Override
    public String getSyntax() {
        return ".say [message]";
    }
    
    @Override
    public void onCommand(final String command, final String[] args) {
        if (args[0].isEmpty()) {
            Client.addChatMessage("No arguments found");
            Client.addChatMessage(this.getSyntax());
        }
        else {
            final StringBuilder msg = new StringBuilder();
            for (final String arg : args) {
                msg.append(arg).append(" ");
            }
            Say.mc.field_71439_g.func_71165_d(msg.toString());
        }
    }
}
