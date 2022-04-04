// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.command.commands;

import mod.hitlerhax.Client;
import mod.hitlerhax.command.Command;

public class Rotate extends Command
{
    @Override
    public String getAlias() {
        return "rotate";
    }
    
    @Override
    public String getDescription() {
        return "Sets the player rotation";
    }
    
    @Override
    public String getSyntax() {
        return ".rotate [pitch] [yaw]";
    }
    
    @Override
    public void onCommand(final String command, final String[] args) {
        if (args[0].isEmpty() || args[1].isEmpty()) {
            Client.addChatMessage("No arguments found");
            Client.addChatMessage(this.getSyntax());
        }
        else {
            Rotate.mc.field_71439_g.field_70125_A = Float.parseFloat(args[0]);
            Rotate.mc.field_71439_g.field_70177_z = Float.parseFloat(args[1]);
            Rotate.mc.field_71439_g.field_70759_as = Float.parseFloat(args[1]);
            Client.addChatMessage("Rotated player");
        }
    }
}
