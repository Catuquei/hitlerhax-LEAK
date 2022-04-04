// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.command.commands;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import mod.hitlerhax.Client;
import mod.hitlerhax.command.Command;

public class Clip extends Command
{
    @Override
    public String getAlias() {
        return "clip";
    }
    
    @Override
    public String getDescription() {
        return "Sets the player position";
    }
    
    @Override
    public String getSyntax() {
        return ".clip [x] [y] [z]";
    }
    
    @Override
    public void onCommand(final String command, final String[] args) {
        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty()) {
            Client.addChatMessage("No arguments found");
            Client.addChatMessage(this.getSyntax());
        }
        else {
            if (Clip.mc.field_71439_g.field_184239_as == null) {
                Clip.mc.field_71439_g.func_70107_b(Clip.mc.field_71439_g.field_70165_t + Double.parseDouble(args[0]), Clip.mc.field_71439_g.field_70163_u + Double.parseDouble(args[1]), Clip.mc.field_71439_g.field_70161_v + Double.parseDouble(args[2]));
            }
            else {
                Clip.mc.field_71439_g.field_184239_as.func_70107_b(Clip.mc.field_71439_g.field_184239_as.field_70165_t + Double.parseDouble(args[0]), Clip.mc.field_71439_g.field_184239_as.field_70163_u + Double.parseDouble(args[1]), Clip.mc.field_71439_g.field_184239_as.field_70161_v + Double.parseDouble(args[2]));
            }
            Clip.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(Clip.mc.field_71439_g.field_70165_t, Clip.mc.field_71439_g.field_70163_u, Clip.mc.field_71439_g.field_70161_v, false));
        }
    }
}
