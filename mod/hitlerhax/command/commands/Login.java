// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.command.commands;

import mod.hitlerhax.Client;
import mod.hitlerhax.util.login.LoginUtils;
import mod.hitlerhax.command.Command;

public class Login extends Command
{
    @Override
    public String getAlias() {
        return "login";
    }
    
    @Override
    public String getDescription() {
        return "Allows to login - for devs";
    }
    
    @Override
    public String getSyntax() {
        return ".login | .login [username] [password]";
    }
    
    @Override
    public void onCommand(final String command, final String[] args) {
        try {
            if (args.length > 1 || args[0].contains(":")) {
                String email;
                String password;
                if (args[0].contains(":")) {
                    final String[] split = args[0].split(":", 2);
                    email = split[0];
                    password = split[1];
                }
                else {
                    email = args[0];
                    password = args[1];
                }
                final String log = LoginUtils.loginAlt(email, password);
                Client.addChatMessage(log);
            }
            else {
                LoginUtils.changeCrackedName(args[0]);
                Client.addChatMessage("Logged [Cracked]: " + Login.mc.func_110432_I().func_111285_a());
            }
        }
        catch (Exception e) {
            Client.addChatMessage("Usage: " + this.getSyntax());
        }
    }
}
