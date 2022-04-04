// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.command;

import java.util.Iterator;
import mod.hitlerhax.Client;
import mod.hitlerhax.command.commands.Entity;
import mod.hitlerhax.command.commands.Say;
import mod.hitlerhax.command.commands.Clip;
import mod.hitlerhax.command.commands.Login;
import mod.hitlerhax.command.commands.Rotate;
import mod.hitlerhax.command.commands.Help;
import mod.hitlerhax.command.commands.Toggle;
import mod.hitlerhax.command.commands.Bind;
import java.util.ArrayList;

public class CommandManager
{
    private final ArrayList<Command> commands;
    
    public CommandManager() {
        this.commands = new ArrayList<Command>();
        this.addCommand(new Bind());
        this.addCommand(new Toggle());
        this.addCommand(new Help());
        this.addCommand(new Rotate());
        this.addCommand(new Login());
        this.addCommand(new Clip());
        this.addCommand(new Say());
        this.addCommand(new Entity());
    }
    
    public void addCommand(final Command c) {
        this.commands.add(c);
    }
    
    public ArrayList<Command> getCommands() {
        return this.commands;
    }
    
    public void callCommand(final String input) {
        final String[] split = input.split(" ");
        final String command = split[0];
        final String args = input.substring(command.length()).trim();
        for (final Command c : this.getCommands()) {
            if (c.getAlias().equalsIgnoreCase(command)) {
                try {
                    c.onCommand(args, args.split(" "));
                }
                catch (Exception e) {
                    Client.addChatMessage("Invalid command usage");
                    Client.addChatMessage(c.getSyntax());
                    e.printStackTrace();
                }
                return;
            }
        }
        Client.addChatMessage("no such command exists");
    }
}
